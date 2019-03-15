function loadMenu(rollid){
var loading = true;
var $window = $(window);
var $content = $("body #generatedmenu");

// ajax inladen, afhankelijk van categorie, onderwijs type en kerndoel. 

            $.ajax({
                type       : "GET",
               // data       : {rollid : rollid, type : type, kerndoel : doel, pop: populair},
                data       : {rollid : rollid},
                dataType   : "html",
                url        : "getMenu.do", // this is in ActivityController.java file
                beforeSend : function() {
                          $content.fadeOut(100);
                          $content.append(
                            '<img src="/NextGenMed/resources/images/loading.gif" />'
                          );  

                   } 
                })
              .done(function(data) {
                        $content.hide();
                        $content.html(data);
                        $content.fadeIn(500, function() { 
                                loading = false;
                                  $("#temp_load").remove();
                                });
                        })
              .fail(function() {   $("#temp_load").remove(); alert("failed miserably"); });


    }