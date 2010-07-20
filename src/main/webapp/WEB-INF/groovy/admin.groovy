import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.*
import com.google.appengine.api.datastore.KeyFactory.Builder;
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import com.kyub.gaelyk.scaffold.conversion.*
import com.kyub.gaelyk.scaffold.validation.*;


/*
println '<h1>HELLO ADMIN  </h1>'
println params['actionName'] + ' 4 ' + params['entityName']

Entity entity = new Entity(params['entityName'])

// subscript notation, like when accessing a map
entity['name'] = "Guillaume Laforge at " + new Date().toString()
entity.save()

def query = new Query(params['entityName'])
PreparedQuery preparedQuery = datastore.prepare(query)
def entities = preparedQuery.asList( withLimit(10) )

println '<br/> size: ' + entities.size()
*/

//MOCK

def AdminRegistry registry = new AdminRegistry()

def ConversionEngine convertion = new ConversionEngine()

def ValidationEngine validation = new ValidationEngine()

def pogoDescr = registry.pogoLayouts[params['entityName']]

//def log = new GroovyLogger("adminLogger")

def String destination ="NOTSET";

if(pogoDescr == null){
	request['message'] = " Entity \'" + params['entityName'] +"\' not found in registry " 
	destination= '/admin/ajaxFail.gtpl'
	
}else{

request['entityDescriptor'] = pogoDescr

//log.debug("params['actionName']: " +params['actionName'])

switch (params['actionName']){
	
	case 'index':
		def query = new Query(pogoDescr.entityName)
		def PreparedQuery preparedQuery = datastore.prepare(query)
		def entities = preparedQuery.asList( withLimit(25) )
		request['entities'] = entities		
		destination= '/admin/list.gtpl'		
		break
				
	case 'ajaxlist':
		def query = new Query(pogoDescr.entityName)
		PreparedQuery preparedQuery = datastore.prepare(query)
		def entities = preparedQuery.asList( withLimit(25) )
		request['entities'] = entities
		destination= '/admin/listRows.gtpl'
		break
		
	case 'create':
		destination= '/admin/create.gtpl'
		break
	
	case 'insert':
		if(params['ajax'] != null){
			Entity entity = new Entity(pogoDescr.entityName)
			def convRes = convertion.convert(params,registry.pogos[pogoDescr.entityName])			
			def validationRes = validation.validate(convRes.convertedVals,registry.pogos[pogoDescr.entityName],convRes)
			if(validationRes.isValid()){
				entity << convRes.convertedVals
				entity.save()
				request['message'] = "New " + pogoDescr.entityName +" has been saved with id " + entity.key.id
				destination= '/admin/ajaxSuccess.gtpl'
			}else{
			
				System.err.println("Errors: " + convRes.getMessages())
			
				request['message'] = " Entity \'" + params['entityName'] +"\' Failed to save "
				request['errors'] = convRes.getMessages()
				
				destination= '/admin/create.gtpl'
			
			}
		}
		break
	case 'delete':
		
		if(params['id'] != null){
			def key =  new Builder(pogoDescr.entityName,new Long(params['id'])).getKey()
			datastore.withTransaction {
				datastore.delete(key)
			}
			
		}
		break
		
	case 'detail':
		
		if(params['id'] != null){
			def key =  new Builder(pogoDescr.entityName,new Long(params['id'])).getKey()
			Entity entity = datastore.get(key)
			request['entity'] = entity
			destination= '/admin/detail.gtpl'			
		}
		break
		
	case 'rowDetail':
		
		if(params['id'] != null){
			def key =  new Builder(pogoDescr.entityName ,new Long(params['id'])).getKey()
			Entity entity = datastore.get(key)
			request['entity'] = entity
			destination= '/admin/rowDetail.gtpl'			
		}
		break
		
	case 'updateForm':
		
		if(params['id'] != null){
			def key =  new Builder(pogoDescr.entityName,new Long(params['id'])).getKey()
			Entity entity = datastore.get(key)
			request['entity'] = entity
			destination= '/admin/update.gtpl'			
			
		}
		break
		
   case 'update':
   	   if(params['id'] != null){
		   def key =  new Builder(pogoDescr.entityName,new Long(params['id'])).getKey()
		   Entity entity = datastore.get(key)
		 
		   def convRes = convertion.convert(params,registry.pogos[pogoDescr.entityName])
		   def validationRes = validation.validate(convRes.convertedVals,registry.pogos[pogoDescr.entityName],convRes)
		   entity << convRes.convertedVals
		    if(validationRes.isValid()){
			    
		   
		    datastore.withTransaction {
			entity.save()   
			request['message'] = "Updated " + pogoDescr.entityName +" with id " + entity.key.id +" has been saved  "
			destination= '/admin/ajaxSuccess.gtpl'
		   }
		   }else{
		   
			  
			   request['entity'] = entity
			   request['message'] = " Entity \'" + params['entityName'] +"\' Failed to save "
			   request['errors'] = convRes.getMessages()
			   
			   destination= '/admin/update.gtpl'
		   
		   }
	  
	   
	   }
	   break
	   
   case 'searchSuggest':
		  def query = new Query(pogoDescr.entityName)
		  def PreparedQuery preparedQuery = datastore.prepare(query)
		  def pEntities = preparedQuery.asList( withLimit(500) )
		  def entities = new LinkedHashSet()
		  
		  
		  
		  if(params.term != null){
		  
		  pEntities.each{ entry ->			  
			  
			  pogoDescr.searchProperties.each {prop ->
				 
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
		  def query = new Query(pogoDescr.entityName)
		  def PreparedQuery preparedQuery = datastore.prepare(query)
		  def pEntities = preparedQuery.asList( withLimit(500) )
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

