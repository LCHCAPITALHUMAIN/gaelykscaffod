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

function decorateDate(){

	\$(".datepicker").datepicker({
		showOn: 'button',
		buttonImage: '/images/page_go.png',
		buttonImageOnly: true,
		dateFormat:'dd/mm/yy'
	});

	\$(".dateTimepicker").datepicker({
		showOn: 'button',
		buttonImage: '/images/page_go.png',
		buttonImageOnly: true,
		dateFormat:'dd/mm/yy HH:mm'
	});
}

function deleteEntity(id){

	if(confirm('Are you sure?')){


		\$.ajax({
			  url: '/adminajax/${request.entityDescriptor.scaffoldName}/delete?id='+id,
			  success: function(data) {
				
				\$('#row_data_'+id).slideUp('slow', function() {
			    	// Animation complete.
			  	});
			  }
			});

		
		
	}
	
}

function showEntity(id){
	
	 \$('#row_detail_'+id).load('/adminajax/${request.entityDescriptor.scaffoldName}/detail?id='+id, function() {
			\$('#row_detail_'+id).slideToggle('slow', function() {
    			// Animation complete.
  			});
	 });	
}
function showUpdate(id){
	
	 \$('#row_edit_'+id).load('/adminajax/${request.entityDescriptor.scaffoldName}/updateForm?id='+id, function() {
		 decorateDate()
		 \$('#updateForm_'+id).ajaxify({    						
			 'complete': function() {
			 if( \$('#row_edit_'+id).find('.ui-widget').size() > 0){
				 decorateDate()
				 }else{
			    \$('#row_data_'+id).load('/adminajax/${request.entityDescriptor.scaffoldName}/rowDetail?id='+id, function() {
			    	decorateRowButtons();
			    	\$('#row_edit_'+id).fadeOut(1800, function() {
				    	// Animation complete.
				  		});
			    });
				
	         }
		 	},
			}); 
			\$('#row_edit_'+id).slideToggle('slow', function() {
   			// Animation complete.
 			});
	 });	
}

\$(function() {


	
	\$("#search_btn").button({
        icons: {
            primary: 'ui-icon-search'
        }
    }).click(
    		function() {

    			\$('#dataContainer').fadeOut('slow', function() {
            			
    			 \$('#dataContainer').load('/adminajax/${request.entityDescriptor.scaffoldName}/search?term='+\$("#searchParam").val(), function() {
    				 decorateRowButtons();
    				 \$('#dataContainer').fadeIn('fast');
        			 });

    			});
    			 
        		}
    );

	\$("#searchParam").autocomplete({
		source: "/adminajax/${request.entityDescriptor.scaffoldName}/searchSuggest",
		minLength: 2,
		select: function(event, ui) {
			//alert(ui.item.value);
		}
	});
	

	
	\$("#insert_btn").button({
        icons: {
            primary: 'ui-icon-plus'
        }
    }).click(
    		function() {
    			 \$('#insertFormContainer').load('/adminajax/${request.entityDescriptor.scaffoldName}/create', function() {
    				 decorateDate();
     				\$('#insertForm').ajaxify({    						
    						 'complete': function() {
						 		if( \$('#insertFormContainer').find('.ui-widget').size() > 0){}else{
    						    \$('#dataContainer').load('/adminajax/${request.entityDescriptor.scaffoldName}/ajaxlist', function() {
    						    	decorateRowButtons();
    						    	\$('#insertFormContainer').fadeOut(1800, function() {
    							    	// Animation complete.
    							  		});
    						    });
    							
    				         }
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
	
	<div class="ui-corner-all ui-bordered "	>
		
		<p >
			<input id="searchParam" name="searchparam" type="text" class="ui-autocomplete-input " autocomplete="off">
			<button type="button" id="search_btn" style="font-size: small;">Search</button>
			<button id="insert_btn" style="font-size: small;">Insert</button>
		</p>
	</div>
	
	
			
	
	<div id="insertFormContainer" class="ui-corner-all ui-bordered "	style="display: none;">	
	
	</div>
	<div id="dataContainer" class="ui-corner-all ui-bordered "	>
		<% include '/admin/listRows.gtpl' %>
	</div>
</body>