$(document).ready(function() {
    	  
    	  //the min chars for username  
          var min_chars = 4;  
        
          //result texts  
          var characters_error = 'Minimum amount of character is 4';  
          var checking_html = 'Checking...';  
          
          // load the menu
          loadMenu(<%= rollid %>);
    
          
          // load the menu
          $('#mymenu').click(function () {   
   			$.ajax({
             
              type: "get",
              url: "getMenu.do", //this is my servlet
              data: "rollid="+rollid,
              success: function(msg){   
                  $('#generatedmenu').html(msg);
                    	 //$("#loading").hide();

                    }
             });
   			
          });
          
          
          //when button is clicked  
          $('#firstname').change(function(){  
              //run the character number check  
              if($('#firstname').val().length < min_chars){  
                  //if it's bellow the minimum show characters_error text '  
                  $('#serach').html(characters_error);  
              }else{  
                  //else show the cheking_text and run the function to check  
                  $('#search').html(checking_html);  
                  check_availability();  
              }  
          });  
    
    	 
          $('#person').click(function () {
          		   	//$("#loading").show();
          		   	var tem= 1;
                  	$("#msg").val(" Data Request is processing ! ");
                      $.ajax({
                   
                          type: "get",
                          url: "person.do", //this is my servlet
                          data: "userid="+tem,
                          success: function(msg){   
                          	 $('#container').html(msg);
                          	 //$("#loading").hide();
                          	
                          	// $("#decoder").val(" Ready To accept Data Requests ");
                          }
                      });
               
                  });
          
          
              $('#webcam').click(function () {
        		   	//$("#loading").show();
        		   	var tem= 1;
                	$("#msg").val(" Data Request is processing ! ");
                    $.ajax({
                 
                        type: "get",
                        url: "personImageUpload.do", //this is my servlet
                        data: "userid="+tem,
                        success: function(msg){   
                        	 $('#container').html(msg);
                        	 //$("#loading").hide();
                        	
                        	// $("#decoder").val(" Ready To accept Data Requests ");
                        }
                    });
             
                });
              
          
        		  $('#patient').click(function () {
            		   	//$("#loading").show();
            		   	var tem= 1;
                    	$("#msg").val(" Data Request is processing ! ");
                        $.ajax({
                     
                            type: "get",
                            url: "patient.do", //this is my servlet
                            data: "person=0",
                            success: function(msg){   
                            	 $('#container').html(msg);
                            	 //$("#loading").hide();
                            	
                            	// $("#decoder").val(" Ready To accept Data Requests ");
                            }
                        });
                 
                    });		  
        		  
          
          $('#docUpload').click(function () {
    		   	//$("#loading").show();
    		   	var tem= 1;
            	$("#msg").val(" Document uploading Interface ! ");
                $.ajax({
             
                    type: "get",
                    url: "fileUpload.do", //this is my servlet
                    data: "userid="+tem,
                    success: function(msg){   
                    	 $('#container').html(msg);
                    	 //$("#loading").hide();
                    	
                    	// $("#decoder").val(" Ready To accept Data Requests ");
                    }
                });
         
            });
          
          $('#emailUsButton').click(function () {
  		   	//$("#loading").show();
  		   	alert('goint to contact by email');
  		   	var tem= 1;
          	$("#msg").val(" Send a email to us ! ");
              $.ajax({
           
                  type: "get",
                  url: "contactByEmail.do", //this is my servlet
                 // data: "userid="+tem,
                  success: function(msg){   
                  	 $('#container').html(msg);
                  	 //$("#loading").hide();
                  	
                  	// $("#decoder").val(" Ready To accept Data Requests ");
                  }
              });
       
          });
          
          
          
          //bellow function can be used to open a popup window
     		jQuery('.example3demo').popupWindow({ 
    			//height:500, 
    			//width:800, 
    			centerScreen:1  
    		}); 
     
     		$('#b_print').click(function () { 
     			 alert('i was clicked');
     			 var w = window.open('', '', 'width=400,height=400,resizeable,scrollbars');
				 w.document.write(document.getElementById('div_print').innerHTML);
				 w.document.close(); // needed for chrome and safari
     		}); 
       
});    // end of document ready