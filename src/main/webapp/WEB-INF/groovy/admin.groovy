import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.*
import com.google.appengine.api.datastore.KeyFactory.Builder;
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import com.kyub.gaelyk.scaffold.conversion.*
import com.kyub.gaelyk.scaffold.validation.*
import  com.kyub.gaelyk.scaffold.meta.*
import  com.kyub.gaelyk.scaffold.*
import com.googlecode.objectify.*






def ConversionEngine convertion = new ConversionEngine()

def ValidationEngine validation = new ValidationEngine()

def registry = new AdminRegistry()

def pogoDescr = ScaffoldRegistar.getEntityDescriptor(params['entityName'])

//def log = new GroovyLogger("adminLogger")


def String destination ="NOTSET";

if(pogoDescr == null){
	request['message'] = " Entity \'" + params['entityName'] +"\' not found in registry " 
	destination= '/admin/ajaxFail.gtpl'
	
}else{


def Objectify ofy = ObjectifyService.begin();
request['entityDescriptor'] = pogoDescr

log.info("params['actionName']: " +params['actionName'])

switch (params['actionName']){
	
	case 'index':		
		def entities = ofy.query(pogoDescr.getEntityClass())
		request['entities'] = entities		
		destination= '/admin/list.gtpl'		
		break
				
	case 'ajaxlist':
		def entities = ofy.query(pogoDescr.getEntityClass())
		request['entities'] = entities
		destination= '/admin/listRows.gtpl'
		break
		
	case 'create':
		pogoDescr.entityStruct.each() { key, value -> 
			if(value instanceof RelationDescriptor){
				System.out.println("value.targetPogo: " + value.targetPogo)
				def entities = ofy.query(Class.forName(value.targetPogo))
				request[key+'_entities_4_'+pogoDescr.scaffoldName] = entities		
				
			}
			
			}
		destination= '/admin/create.gtpl'
		break
	
	case 'insert':
		if(params['ajax'] != null){
			def entity = Class.forName(pogoDescr.entityName).newInstance()
			def convRes = convertion.convert(params,pogoDescr.entityStruct)			
			def validationRes = validation.validate(convRes.convertedVals,pogoDescr.entityStruct,convRes)
			convRes.convertedVals.each {key , value ->
				
				entity[key] = value
				
				}
			
			if(validationRes.isValid()){				
				ofy.put(entity)
				request['message'] = "New " + pogoDescr.entityName +" has been saved with id " + entity.id
				destination= '/admin/ajaxSuccess.gtpl'
			}else{
			
				System.err.println("Errors: " + convRes.getMessages())
			
				request['message'] = " Entity \'" + pogoDescr.entityName +"\' Failed to save "
				request['errors'] = convRes.getMessages()
				
				destination= '/admin/create.gtpl'
			
			}
		}
		break
	case 'delete':
		
		if(params['id'] != null){
				ofy.delete(ofy.get(pogoDescr.getEntityClass(),new Long(params['id'])))
		}
		request['message'] = "Deleted " + pogoDescr.entityName 
		destination= '/admin/ajaxSuccess.gtpl'
		break
		
	case 'detail':
		
		if(params['id'] != null){
			
			def entity = ofy.get(pogoDescr.getEntityClass(),new Long(params['id']))
			request['entity'] = entity
			destination= '/admin/detail.gtpl'			
		}
		break
		
	case 'rowDetail':
		
		if(params['id'] != null){
			def entity = ofy.get(pogoDescr.getEntityClass(),new Long(params['id']))
			request['entity'] = entity
			destination= '/admin/rowDetail.gtpl'			
		}
		break
		
	case 'updateForm':
		
		if(params['id'] != null){
			
			pogoDescr.entityStruct.each() { key, value ->
				if(value instanceof RelationDescriptor){
					System.out.println("value.targetPogo: " + value.targetPogo)
					def entities = ofy.query(Class.forName(value.targetPogo))
					request[key+'_entities_4_'+pogoDescr.scaffoldName] = entities
					
				}
				
				}
			
			def entity = ofy.get(pogoDescr.getEntityClass(),new Long(params['id']))
			request['entity'] = entity
			destination= '/admin/update.gtpl'			
			
		}
		break
		
   case 'update':
   	   if(params['id'] != null){
		  def entity = ofy.get(pogoDescr.getEntityClass(),new Long(params['id']))
			def convRes = convertion.convert(params,pogoDescr.entityStruct.subMap(pogoDescr.editProperties)	)		
			def validationRes = validation.validate(convRes.convertedVals,pogoDescr.entityStruct,convRes)
			convRes.convertedVals.each {key , value ->
				log.info('prop ' + key )
				entity[key] = value
				
				}
		    if(validationRes.isValid()){
			    ofy.put(entity)  
				request['message'] = "Updated " + pogoDescr.entityName +" with id " + entity.id +" has been saved  "
				destination= '/admin/ajaxSuccess.gtpl'
		   
		   }else{
		   
			  
			   request['entity'] = entity
			   request['message'] = " Entity \'" + params['entityName'] +"\' Failed to save "
			   request['errors'] = convRes.getMessages()
			   
			   destination= '/admin/update.gtpl'
		   
		   }
	  
	   
	   }
	   break
	   
   case 'searchSuggest':
		 def pEntities = ofy.query(pogoDescr.getEntityClass())
		  def entities = new LinkedHashSet() 
		  
		  log.info("params.term " + params.term)
		  if(params.term != null){
		  
		  pEntities.each{ entry ->			  
			 
			  pogoDescr.searchProperties.each {prop ->
				  log.info("prop " + prop + " " + String.valueOf(entry[prop]))
				  if(String.valueOf(entry[prop]).indexOf(params.term) > -1){
					  entities.add(entry[prop])
				  }
			  
			  }
		  }
		  }
		  
		  request['results'] = entities
		  
		  destination= '/admin/searchSuggest.gtpl'
		  break
		  
	case 'search':
		  def pEntities = ofy.query(pogoDescr.getEntityClass())
		  def entities = new LinkedHashSet() 
		  		  	  
		  if(params.term != null){
		  
		  pEntities.each{ entry ->
						  
			  pogoDescr.searchProperties.each {prop ->
				
				  if(String.valueOf(entry[prop]).indexOf(params.term) > -1){
					  entities.add(entry)
				  }
			  }
		  }
		  }
		 
		  request['entities'] = entities
		  
		  destination= '/admin/listRows.gtpl'
		  break
		
	}
}
//log.debug("destination: " +destination)

forward destination

