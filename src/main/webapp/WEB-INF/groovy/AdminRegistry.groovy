import com.kyub.gaelyk.scaffold.ScaffoldRegistar;
import com.kyub.gaelyk.scaffold.meta.*;


public class AdminRegistry {
	

	def Map pogos = ['pizza' : ['name':new FieldDescriptor(mandatory:true),'price':new FieldDescriptor(typeName:'Double'),'description':new FieldDescriptor(lenght:1024)],
					 'order': ['dataRef':new FieldDescriptor(mandatory:true, typeName:'Date'),'issueDate':new FieldDescriptor(typeName:'TimeStamp'),'customerMail':new FieldDescriptor(),'desiredPizza': new RelationDescriptor(targetPogo:'com.kyub.pizzeria.model.Pizza')]]
	
	/*
	
	def Map pogoLayouts = [	'pizza' : new EntityDescriptor(
								entityName : 'pizza',
								entityStruct : pogos['pizza'],
								listProperties:['name','price'] , 
								insertProperties:['name','price','description'] ,
								editProperties:['price','description'] ),
							'order' : new EntityDescriptor(
								entityName : 'order',
								entityStruct : pogos['order'],
								listProperties:['customerMail','refDate'] , insertProperties:['customerMail','refDate','desiredPizza'] ,editProperties:['customerMail','refDate'], detailProperties:['customerMail','refDate','issueDate','desiredPizza'],searchProperties : ['customerMail'] )]
	*/
	def pizzaScaffold = ScaffoldRegistar.registerEntity('pizza',  new EntityDescriptor(
														entityName : 'com.kyub.pizzeria.model.Pizza',
														entityStruct : pogos['pizza'],
														listProperties:['name','price'] , 
														insertProperties:['name','price','description'] ,
														editProperties:['price','description'],
														detailProperties:['name','price','description'],
														searchProperties:['name','description'] )
														)
	
	def orderScaffold = ScaffoldRegistar.registerEntity('order',  new EntityDescriptor(
														entityName : 'com.kyub.pizzeria.model.Order',
														entityStruct : pogos['order'],
														listProperties:['customerMail','dataRef'] , 
														insertProperties:['customerMail','dataRef','desiredPizza'] ,
														editProperties:['customerMail','dataRef','desiredPizza'], 
														detailProperties:['customerMail','dataRef','issueDate','desiredPizza'],
														searchProperties : ['customerMail'] )

														)
		
	
}