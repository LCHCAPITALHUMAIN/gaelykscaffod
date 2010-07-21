
import com.kyub.gaelyk.scaffold.*;
import com.kyub.gaelyk.scaffold.meta.*;


ScaffoldRegistar.registerEntity('pizza',new EntityDescriptor(
								entityName : 'com.kyub.pizeria.model.Pizza',
								entityStruct : ['name':new FieldDescriptor(mandatory:true),'price':new FieldDescriptor(typeName:'Double'),'description':new FieldDescriptor(lenght:1024)],
								listProperties:['name','price'] , insertProperties:['name','price','description'] ,editProperties:['price','description'] ))