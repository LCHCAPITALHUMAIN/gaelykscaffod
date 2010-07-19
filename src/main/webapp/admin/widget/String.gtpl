<% if (request.entityDescriptor.entityStruct[request.widgetData].lenght > 100) { %>
<textarea rows="10" cols="100" name="${request.widgetData}"	id="input_${request.widgetData}">	</textarea>
<% } else { %>
<input type="text" name="${request.widgetData}" id="input_${request.widgetData}" />
<% } %>
