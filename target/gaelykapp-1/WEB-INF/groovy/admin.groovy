import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.*
import com.google.appengine.api.datastore.KeyFactory.Builder;
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import com.kyub.gaelyk.scaffold.conversion.*
import com.kyub.gaelyk.scaffold.validation.*;
import net.sf.json.*
import net.sf.json.groovy.*

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

if(pogoDescr == null){
	request['message'] = " Entity \'" + params['entityName'] +"\' not found in registry " 
	forward '/admin/ajaxFail.gtpl'
	
}else{

request['entityDescriptor'] = pogoDescr


switch (params['actionName']){
	
	case 'index':
		def query = new Query(pogoDescr.entityName)
		def PreparedQuery preparedQuery = datastore.prepare(query)
		def entities = preparedQuery.asList( withLimit(25) )
		request['entities'] = entities		
		forward '/admin/list.gtpl'	
		
	case 'searchSuggest':
		def query = new Query(pogoDescr.entityName)
		def PreparedQuery preparedQuery = datastore.prepare(query)
		def pEntities = preparedQuery.asList( withLimit(500) )
		def entities = new LinkedHashSet()
		def jsonBuilder = new JsonGroovyBuilder()
		pEntities.each{ entry ->
			pogoDescr.searchProperties.each {prop ->
			
				if(String.valueOf(entry[prop]).indexOf(params.searchparam) > -1){
					entities.add(entry[prop])
				}
			
			}
		}
		def jsonres = jsonBuilder.results {
			entities.each { entry ->			
				result {
						value = entry
					}
			}
		}
		
		print jsonres
		
		
		
		
	case 'ajaxlist':
		def query = new Query(pogoDescr.entityName)
		PreparedQuery preparedQuery = datastore.prepare(query)
		def entities = preparedQuery.asList( withLimit(25) )
		request['entities'] = entities
		forward '/admin/listRows.gtpl'
	
	case 'create':
		forward '/admin/create.gtpl'
	
	case 'insert':
		if(params['ajax'] != null){
			Entity entity = new Entity(pogoDescr.entityName)
			def convRes = convertion.convert(params,registry.pogos[pogoDescr.entityName])			
			def validationRes = validation.validate(convRes.convertedVals,registry.pogos[pogoDescr.entityName],convRes)
			if(validationRes.isValid()){
				entity << convRes.convertedVals
				entity.save()
				request['message'] = "New " + pogoDescr.entityName +" has been saved with id " + entity.key.id
				forward '/admin/ajaxSuccess.gtpl'
			}else{
			
				System.err.println("Errors: " + convRes.getMessages())
			
				request['message'] = " Entity \'" + params['entityName'] +"\' Failed to save "
				request['errors'] = convRes.getMessages()
				
				forward '/admin/create.gtpl'
			
			}
		}
		
	case 'delete':
		
		if(params['id'] != null){
			def key =  new Builder(pogoDescr.entityName,new Long(params['id'])).getKey()
			datastore.withTransaction {
				datastore.delete(key)
			}
			
		}
		println params['id']
		
	case 'detail':
		
		if(params['id'] != null){
			def key =  new Builder(pogoDescr.entityName,new Long(params['id'])).getKey()
			Entity entity = datastore.get(key)
			request['entity'] = entity
			forward '/admin/detail.gtpl'
			
			
		}
	case 'rowDetail':
		
		if(params['id'] != null){
			def key =  new Builder(pogoDescr.entityName ,new Long(params['id'])).getKey()
			Entity entity = datastore.get(key)
			request['entity'] = entity
			forward '/admin/rowDetail.gtpl'
			
			
		}
	case 'updateForm':
		
		if(params['id'] != null){
			def key =  new Builder(pogoDescr.entityName,new Long(params['id'])).getKey()
			Entity entity = datastore.get(key)
			request['entity'] = entity
			forward '/admin/update.gtpl'			
			
		}
		
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
		forward '/admin/ajaxSuccess.gtpl'
	   }
	   }else{
	   
		   System.err.println("Errors: " + convRes.getMessages())
		   request['entity'] = entity
		   request['message'] = " Entity \'" + params['entityName'] +"\' Failed to save "
		   request['errors'] = convRes.getMessages()
		   
		   forward '/admin/update.gtpl'
	   
	   }
	  
	   
   }
		
		
	}
}

