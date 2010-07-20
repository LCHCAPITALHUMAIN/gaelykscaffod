<% request.entities.each { entity -> %>
	<div id="row_data_${entity.key.id}" > 
	<span style="width: 128px; text-align: center; font-weight: bold;">
			${entity.key.id}
		</span>	
		<span style="width: 512px">
		
			<% request.entityDescriptor.listProperties.each {p -> %> 
				<span>${entity[p]}</span>
			<% } %>
		</span>	
		<span >
			<button id="detail_${entity.key.id}" style="font-size: small;" class="detailBtn" onclick="showEntity('${entity.key.id}')">Detail</button>
		</span>	
		
		<span >
			<button id="edit_${entity.key.id}" style="font-size: small;" class="editBtn" onclick="showUpdate('${entity.key.id}')" >Edit</button>
		</span>	
		<span >
			<button id="delete_${entity.key.id}" style="font-size: small;" class="deleteBtn" onclick="deleteEntity('${entity.key.id}')">Delete</button>
		</span>	
		
	</div>
	<div id="row_detail_${entity.key.id}" style="display: none;">
	Unloaded Detail
	</div>
	<div id="row_edit_${entity.key.id}" style="display: none;">
	Unoloaded Edit form
	</div>
	<% } %>