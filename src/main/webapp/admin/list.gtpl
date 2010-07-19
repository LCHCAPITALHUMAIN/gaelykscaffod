<head>
<script type="text/javascript">

function decorateRowButtons(){


	\$(".editBtn").button({
        icons: {
            primary: 'ui-icon-pencil'
        }
    })
    \$(".detailBtn").button({
        icons: {
            primary: 'ui-icon-zoomin'
        }
    })
    \$(".deleteBtn").button({
        icons: {
            primary: 'ui-icon-trash'
        }
    })
	
}

function deleteEntity(id){

	if(confirm('Are you sure?')){


		\$.ajax({
			  url: '/adminajax/${request.entityDescriptor.entityName}/delete?id='+id,
			  success: function(data) {
				\$('#row_data_'+id).slideUp('slow', function() {
			    	// Animation complete.
			  	});
			  }
			});

		
		
	}
	
}

function showEntity(id){
	
	 \$('#row_detail_'+id).load('/adminajax/${request.entityDescriptor.entityName}/detail?id='+id, function() {
			\$('#row_detail_'+id).slideToggle('slow', function() {
    			// Animation complete.
  			});
	 });	
}
function showUpdate(id){
	
	 \$('#row_edit_'+id).load('/adminajax/${request.entityDescriptor.entityName}/updateForm?id='+id, function() {
		 \$('#updateForm_'+id).ajaxify({    						
			 'complete': function() {
			    \$('#row_data_'+id).load('/adminajax/${request.entityDescriptor.entityName}/rowDetail?id='+id, function() {
			    	decorateRowButtons();
			    	\$('#row_edit_'+id).fadeOut(1800, function() {
				    	// Animation complete.
				  		});
			    });
				
	         },
			}); 
			\$('#row_edit_'+id).slideToggle('slow', function() {
   			// Animation complete.
 			});
	 });	
}

\$(function() {


	\$(".datepicker").datepicker({
		showOn: 'button',
		buttonImage: 'images/page_go.png',
		buttonImageOnly: true
	});

	
	\$("#insert_btn").button({
        icons: {
            primary: 'ui-icon-plus'
        }
    }).click(
    		function() {
    			 \$('#insertFormContainer').load('/adminajax/${request.entityDescriptor.entityName}/create', function() {
    				\$('#insertForm').ajaxify({    						
    						 'complete': function() {
    						    \$('#dataContainer').load('/adminajax/${request.entityDescriptor.entityName}/ajaxlist', function() {
    						    	decorateRowButtons();
    						    	\$('#insertFormContainer').fadeOut(1800, function() {
    							    	// Animation complete.
    							  		});
    						    });
    							
    				         },
    						}); 
    			 	\$('#insertFormContainer').slideToggle('slow', function() {
    				    // Animation complete.
    				  });
    			 });
			}
    	    );

	decorateRowButtons()

    

}
	
)
</script>

</head>

<body>
	
	
	<div>
		<p><button id="insert_btn" style="font-size: small;">Insert</button></p>
	</div>
	
	<div id="insertFormContainer" style="display: none;">	
	
	</div>
	<div id="dataContainer">
		<% include '/admin/listRows.gtpl' %>
	</div>
</body>