<?xml version="1.0" encoding="UTF-8" ?>


<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type='text/javascript' src='/Serve/js/Jquery1-8.js'> </script>
        <script type="text/javascript" src="/Serve/js/tinymce/jscripts/tiny_mce/tiny_mce.js" ></script>


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

        <title>Create your CV</title>
    </head>
    <body>
        
        <div class="page">
            <div style="display:inline-block; vertical-align: top;">
                <div onclick="location.href='http://localhost:8080/Serve/Index'" class="roundedDiv portfolio" style="cursor: pointer;">
                    <a href="http://localhost:8080/Serve/Index" title="Portfolio">Portfolio</a>
                </div>

                <div class="roundedDiv userControl gradientBlue">
                    
                </div>
            </div>
         
            <div class="roundedDiv CVbackground gradientBlue">
                <div align="center" id="CVProgressBar" class="roundedDiv progressBar">
                    <input style="margin:5px;" type="button" onclick="cvContent('CoverLetter')" value="Cover Letter" />
                    <input style="margin:5px;" type="button" onclick="cvContent('Education')" value="Education" />
                    <input style="margin:5px;" type="button" onclick="cvContent('Work')" value="Work Experience" />
                    <input style="margin:5px;" type="button" onclick="cvContent('Interests')" value="Interests / Hobbies" />
                </div>

                <div id="cvContent" class="CV roundedDiv">
                    
                </div>
            </div>
        </div>
    </body>
</html>
