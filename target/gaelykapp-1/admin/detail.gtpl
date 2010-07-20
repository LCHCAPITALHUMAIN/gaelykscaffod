 <h3>Detail:</h3>
 
 <% request.entityDescriptor.detailProperties.each {p -> %> 
				<p>
				<strong for="input_${p}">${p}</strong>	
				<span>
					<%
						def widgetUrl = '/admin/widget/output/'+ request.entityDescriptor.entityStruct[p].typeName +'.gtpl'
					
						request['entity'] = entity
						request['widgetData'] = p
					%>
				
					<% include widgetUrl %>
				</span>
				
				
				</p>
			<% } %>