<%-- 
    Document   : error500
    Created on : Sep 2, 2013, 9:57:18 AM
    Author     : tharangar
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <center>
        <div>
            
            <%  
            out.print("<br />");
            out.print("<br />");
            out.print("<br />");
            out.print("<br />");
            out.print("<a href='main.jsp'><img src='images/500.PNG' width='600' height='600'></a>");
            out.print("<br />");
            out.print("System error (500) Please contact Bissiness System Development Team");
            out.print("<br>");
            %>
            
        </div>
    </center>
    </body>
</html>
