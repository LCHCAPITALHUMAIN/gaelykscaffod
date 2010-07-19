<h3>Edit</h3>
<form id="updateForm_${request.entity.key.id}" method="get" action="/adminajax/${request.entityDescriptor.entityName}/update">
		<% request.entityDescriptor.editPropperties.each {p -> %> 
				<p>
				<label for="input_${p}">${p}</label>	
				<input type="text" name="${p}" id="input_${p}" value="${request.entity[p]}" />
				</p>
			<% } %>
		<input type="hidden" value="${request.entity.key.id}" name="id"/>	
		<input type="submit" value="Save" />
		</form>