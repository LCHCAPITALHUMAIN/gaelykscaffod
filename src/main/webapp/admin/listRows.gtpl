<!-- 
<div id="row_header" style="width: 1024px; clear: both; margin-bottom: 12px; "> 
<div>
		<div style=" float: left; width: 32px; text-align: center; font-weight: bold;">
			ID
		</div>	
		<% request.entityDescriptor.listProperties.each {p -> %> 
				<div style="float: left; margin-left: 12px; width: 240px; text-align: center; font-weight: bold;">
					${p}
				</div>
	    <% } %>
				
</div>
</div>
-->
<% request.entities.each { entity -> %>
	<div id="row_data_${entity.id}" class="table-row"> 		
		<div style="font-weight: bold;" class="table-cell">
			${entity.id}
		</div>	
			<% request.entityDescriptor.listProperties.each {p -> %> 
				<div class="table-cell">
					<%
						def widgetUrl = '/admin/widget/output/'+ request.entityDescriptor.entityStruct[p].typeName +'.gtpl'
					
						request['entity'] = entity
						request['widgetData'] = p
					%>
				
					<% include widgetUrl %>
				</div>
			<% } %>
		
		<div class="table-cell">
		<span >
			<button id="detail_${entity.id}" style="font-size: small;" class="detailBtn" onclick="showEntity('${entity.id}')">Detail</button>
		</span>	
		
		<span >
			<button id="edit_${entity.id}" style="font-size: small;" class="editBtn" onclick="showUpdate('${entity.id}')" >Edit</button>
		</span>	
		<span >
			<button id="delete_${entity.id}" style="font-size: small;" class="deleteBtn" onclick="deleteEntity('${entity.id}')">Delete</button>
		</span>	
		</div>
	</div>
	
	<div class="table-clear"> &nbsp;</div>
	
	<div id="row_detail_${entity.id}" style="display: none;">
	Unloaded Detail
	</div>
	<div id="row_edit_${entity.id}" style="display: none;">
	Unoloaded Edit form
	</div>
	
	<% } %>