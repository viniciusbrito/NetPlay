<%@ page contentType="text/html; charset=UTF-8"  language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String status = "";

    status = request.getParameter("status");
    if(status == null)
        status = " ";
%>
<html>
    <head>
    <title>NetPlay</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <style type="text/css">
        <!--
        body {
        background-color: #DCE7DE;
        }
        .style6 {font-size: 12px; color: #FF0000;}
        -->
    </style></head>

    <body>
        <table>
            <tr>
                <td colspan="2"><div align="center"><span class="style5"><%= status %></span></div></td>
            </tr>
        </table>
    </body>
</html>
