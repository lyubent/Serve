<%-- 
    Document   : index
    Created on : 17-Sep-2012, 10:19:59
    Author     : danieldeak
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type='text/javascript' src='/Serve/js/Jquery1-8.js'> </script>
        <%
            if (request.getAttribute("css") != null)
            {
                out.println("<link rel='stylesheet' type='text/css' href='"+request.getAttribute("css")+"' />");
            }

            if (request.getAttribute("js") != null)
            {
                List jsFiles = (List) request.getAttribute("js");

                for (int i=0; i < jsFiles.size(); i++)
                {
                    out.println("<script type='text/javascript' src='"+(String)jsFiles.get(i)+"'></script>");
                }
            }
        %>
        <title>Index</title>
    </head>
    <body>
        <div class="site">
            <div class="book" align="center">
                <div class="border">
                    <div class="page">
                        testing
                    </div>

                    <div class="pageSeparator">

                    </div>

                    <div class="page2">
                        Testing 2
                    </div>
                </div>

                <div id="helpDiv" style="display:inline-block; vertical-align: top;">
                    <img id="helpImage" src="images/help.png" title="Help" />
                </div>
            </div>

            <div class="tabs">
                <div class="One singleTab">
                    <p id="firstTabText">
                       FirstTab
                    </p>
                </div>
                <div class="Two singleTab">
                    <p>
                        SecondTab
                    </p>
                </div>
            </div>
        </div>

    </body>
</html>
