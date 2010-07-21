
<select name="${request.widgetData}">

<% def items = request[request.widgetData+'_entities_4_'+request.entityDescriptor.scaffoldName]
	items.each { item ->
	
%>
<option value="${item.key.id}">${item.key.id} - ${item.name}</option>
<% } %>
</select>