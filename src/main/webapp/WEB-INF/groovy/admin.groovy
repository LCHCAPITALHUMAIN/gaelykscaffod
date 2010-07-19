import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.*
import com.google.appengine.api.datastore.KeyFactory.Builder;
import static com.google.appengine.api.datastore.FetchOptions.Builder.*

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
			entity << params
			entity.save()
			request['message'] = "New " + pogoDescr.entityName +" has been saved with id " + entity.key.id
			forward '/admin/ajaxSuccess.gtpl'
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
	 
	   entity << params //TODO only update params
	  
	   try{
	    datastore.withTransaction {
		entity.save()   
		request['message'] = "Updated " + pogoDescr.entityName +" with id " + entity.key.id +" has been saved  "
		forward '/admin/ajaxSuccess.gtpl'
	   }
	   }catch(Exception e){
	   	   request['entity'] = entity
		   forward '/admin/update.gtpl'
	   }
	   
   }
		
		
	}
}

