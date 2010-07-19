public class AdminRegistry {
	

	def Map pogos = ['pizza' : ['name':new FieldDescriptor(mandatory:true),'price':new FieldDescriptor(typeName:'Double'),'description':new FieldDescriptor(lenght:1024)] ]
	
	def Map pogoLayouts = ['car' : new EntityDescriptor(
								entityName : 'car',
								entityStruct : pogos['car'],
								listPropperties:['name'] , insertPropperties:['name'] ,editPropperties:['name'] ),
					'pizza' : new EntityDescriptor(
								entityName : 'pizza',
								entityStruct : pogos['pizza'],
								listPropperties:['name','price'] , insertPropperties:['name','price','description'] ,editPropperties:['price','description'] )]
		
	
}