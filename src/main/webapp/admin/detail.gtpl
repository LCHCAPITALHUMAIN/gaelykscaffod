 <h3>Detail:</h3>
 
 <% request.entityDescriptor.detailProperties.each {p -> %> 
				<p>
				<strong for="input_${p}">${p}</strong>	${request.entity[p]}
				</p>
			<% } %>