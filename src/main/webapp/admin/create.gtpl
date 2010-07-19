<form id="insertForm" method="get" action="/adminajax/${request.entityDescriptor.entityName}/insert">
		<% request.entityDescriptor.insertPropperties.each {p -> %> 
				<p>
				<label for="input_${p}">${p}</label>	
					<%
						def widgetUrl = '/admin/widget/'+ request.entityDescriptor.entityStruct[p].typeName +'.gtpl'
					
						request['widgetData'] = p
					%>
				
					<% include widgetUrl %>
				</p>
			<% } %>
		<input type="submit" value="Save" />
		</form>