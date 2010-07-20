<span style="width: 128px; text-align: center; font-weight: bold;">
			${request.entity.key.id}
		</span>	
		<span style="width: 512px">
		
			<% request.entityDescriptor.listProperties.each {p -> %> 
				<span>
				<%
						def widgetUrl = '/admin/widget/output/'+ request.entityDescriptor.entityStruct[p].typeName +'.gtpl'
					
						request['entity'] = entity
						request['widgetData'] = p
					%>
				
					<% include widgetUrl %>
				
				</span>
			<% } %>
		</span>	
		<span >
			<button id="detail_${request.entity.key.id}" style="font-size: small;" class="detailBtn" onclick="showEntity('${request.entity.key.id}')">Detail</button>
		</span>	
		
		<span >
			<button id="edit_${request.entity.key.id}" style="font-size: small;" class="editBtn" onclick="showUpdate('${request.entity.key.id}')" >Edit</button>
		</span>	
		<span >
			<button id="delete_${request.entity.key.id}" style="font-size: small;" class="deleteBtn" onclick="deleteEntity('${request.entity.key.id}')">Delete</button>
		</span>	