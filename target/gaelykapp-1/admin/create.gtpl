<% if(request.message != null){%>
	<% include '/admin/ajaxFail.gtpl' %>
<%}%>

<form id="insertForm" method="get" action="/adminajax/${request.entityDescriptor.entityName}/insert">
		<% request.entityDescriptor.insertProperties.each {p -> %> 
				<p>
				<label for="input_${p}">${p}</label>	
					<%
						def widgetUrl = '/admin/widget/input/'+ request.entityDescriptor.entityStruct[p].typeName +'.gtpl'
					
						request['widgetData'] = p
					%>
				
					<% include widgetUrl %>
					
					<%if (request.errors != null && request.errors[p] != null) { %>
						<span class="error">${request.errors[p]}</span>
					<%	} %>
				</p>
			<% } %>
		<input type="submit" value="Save" />
		</form>