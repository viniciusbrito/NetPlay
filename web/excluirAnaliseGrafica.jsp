<%@ page contentType="text/html; charset=iso-8859-1"  language="java" import="java.sql.*" import="cdp.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page
   import="analiseGraficos.*"
   import="java.util.List"
   import="cgt.APICadastrarAnaliseGraficos"
%>

<html>
<head>
<title>Excluindo An&aacute;lises de Gr&aacute;ficos</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
.style34 {color: #DCE7DE}
body {
	background-color: #DCE7DE;
}
body,td,th {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #333300;
	font-weight: bold;
}
a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #DCE7DE;
	font-weight: bold;
}
a:visited {
	color: #DCE7DE;
	text-decoration: none;
}
a:active {
	color: #DCE7DE;
	text-decoration: none;
}
a:link {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
	color: #A8C4AD;
}
.style5 {color: #FF0000}
-->
</style>
</head>

<body>
<div align="center">
    
<%
   String idAnalise = request.getParameter("idAnalise");
   if (idAnalise != null) {
       cgt.APICadastrarAnaliseGraficos.excluir(Integer.parseInt(idAnalise));
%>
        <br><br><p style="color:#FF0000">An&aacute;lise exclu&iacute;da com sucesso.</p>
<%
   }
   else {
%>
    
<%
   String idModelo = request.getParameter("idModelo");
   String idUsuario = request.getParameter("idUsuario");
   
   ConsultaAnalisesGraficas analise = new ConsultaAnalisesGraficas (
           Integer.parseInt(idModelo), Integer.parseInt(idUsuario));
%>
    
  <p>&nbsp;</p>
  <p><h1>An&aacute;lises de Gr&aacute;ficos do Modelo <%= analise.getNomeModelo() %></h1></p>
  
  <br>
  
<%
   //pegando todas as analises desse modelo feita por esse usuario
   List analises = analise.getAnalisesModeloUsuario();
   
   if (analises.isEmpty())
   {
%>
        <br><br><p style="color:#FF0000">Esse modelo n&atilde;o tem an&aacute;lises gr&aacute;ficas feitas por voc&ecirc;</p>

<%
   }
   else
   {
%>

        <table border="1">
            <tr>
                <td>N&uacute;mero</td>
                <td>Data</td>
            </tr>
            
            <% for(int i=0; i<analises.size();i++) { 
                AnaliseGraficos ag = (AnaliseGraficos) analises.get(i); 
            %>
                <tr>
                    <td><%= i %></td>
                    <td><%= APICadastrarAnaliseGraficos.formataData(
                            ag.getDataCriacao()) %></td>
                    <td><%= " " %></td>
                    
                    <td valign="middle">
                        <form action="excluirAnaliseGrafica.jsp" method="post">
                            <input type="hidden" name="idAnalise" value="<%= ag.getIdAnalise() %>">
                            <input type="submit" value="Excluir">
                        </form>
                    </td>
                    
                </tr>
            <% } %>
        </table>

<% } 
   analise.finaliza();
}
%>
  
  
</div>
</body>
</html>
