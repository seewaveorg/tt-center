	
$(function() {
        // Easy pie charts
        $('.chart').easyPieChart({animate: 1000});
 });	
	
	
$(function(){
  $(".radio538").click(function(){
    $("#div3").load("/includes/about-info.html");
  });
 });

//function to check username availability  
function check_availability(){  
  
        //get the username  
        var parameter = $('#firstname').val();  
        var attribute = "firstname";
        
        $.ajax({
               
            type: "post",
            url: "GetPerson.do", //this is my servlet
            data: "param="+parameter+"&attrib="+attribute,
            success: function(msg){   
            	 $('#search').html(msg);
            	 //$("#loading").hide();
            	
            	// $("#decoder").val(" Ready To accept Data Requests ");
            }
        });

       
        //use ajax to run the check  
       // $.post("GetPerson.do", { param: parameter , attrib: attrib:  },  
          //  function(result){  
                //if the result is 1  
             //   if(result == 1){  
                    //show that the username is available  
               //     $('#serach').html(username + ' is Available');  
              //  }else{  
                    //show that the username is NOT available  
                //    $('#search').html(username + ' is not Available');  
              //  }  
     //   });  
  
}  

function PrintElem(elem, height, width)
{

    Popup($(elem).html(), height, width);
    //Popup($(elem).html());
}

function Popup(data, height, width) 
{
    //var mywindow = window.open('', 'my div', 'height=400,width=600');
    var mywindow = window.open('', 'Print Me', 'height='+height+',width='+width);
    mywindow.document.write('<html><head><title>my div</title>');
    /*optional stylesheet*/ //mywindow.document.write('<link rel="stylesheet" href="main.css" type="text/css" />');
    mywindow.document.write('</head><body >');
    mywindow.document.write(data);
    mywindow.document.write('</body></html>');

    mywindow.document.close(); // necessary for IE >= 10
    mywindow.focus(); // necessary for IE >= 10

    mywindow.print();
    mywindow.close();

    return true;
}

