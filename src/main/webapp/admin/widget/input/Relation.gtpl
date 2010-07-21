
<select name="${request.widgetData}">

<% def items = request[request.widgetData+'_entities_4_'+request.entityDescriptor.scaffoldName]
	items.each { item ->
	def selected = ''
	if (request.entity != null) {
	
		if (item.equals(request.entity[request.widgetData])){
		
			selected = 'selected'
		}
	
	}
	
%>
<option value="${item.id}" ${selected}>${item}</option>
<% } %>
</select>