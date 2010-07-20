import com.kyub.gaelyk.scaffold.meta.*;


public class AdminRegistry {
	

	def Map pogos = ['pizza' : ['name':new FieldDescriptor(mandatory:true),'price':new FieldDescriptor(typeName:'Double'),'description':new FieldDescriptor(lenght:1024)],
					 'order': ['refDate':new FieldDescriptor(mandatory:true, typeName:'Date'),'issueDate':new FieldDescriptor(typeName:'TimeStamp'),'customerMail':new FieldDescriptor()]]
	
	
	
	def Map pogoLayouts = [	'pizza' : new EntityDescriptor(
								entityName : 'pizza',
								entityStruct : pogos['pizza'],
								listProperties:['name','price'] , insertProperties:['name','price','description'] ,editProperties:['price','description'] ),
							'order' : new EntityDescriptor(
								entityName : 'order',
								entityStruct : pogos['order'],
								listProperties:['customerMail','refDate'] , insertProperties:['customerMail','refDate'] ,editProperties:['customerMail','refDate'], detailProperties:['customerMail','refDate','issueDate'],searchProperties : ['customerMail'] )]
		
	
}