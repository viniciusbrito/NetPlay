<%@ page contentType="text/html; charset=iso-8859-1"  language="java" import="java.sql.*" import="cdp.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page
   import="analiseGraficos.*"
   import="java.util.List"
%>

<html>
<head>
<title>Analisando Gr&aacute;ficos</title>
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
   List analises = analise.getAnalisesModelo();
   
   if (analises.isEmpty())
   {
%>
        <br><br><p style="color:#FF0000">Esse modelo n&atilde;o tem an&aacute;lises gr&aacute;ficas.</p>

<%
   }
   else
   {
%>

        <table border="1">
            <tr>
                <td>N&uacute;mero</td>
                <td>Usu&aacute;rio</td>
                <td>Data</td>
            </tr>
            
            <% for(int i=0; i<analises.size();i++) { 
                AnaliseGraficos ag = (AnaliseGraficos) analises.get(i); 
            %>
                <tr>
                    <td><%= i %></td>
                    <td><%= ag.getUsuario().getLogin() %></td>
                    <td><%= ag.getDataCriacao() %></td>
                    <td><%= " " %></td>
                    
                    <td valign="middle">
                        <form action="analisandografico.jsp" method="post">
                            <input type="hidden" name="id" value="<%= idModelo %>">
                            <input type="hidden" name="nomeArquivo" value="<%= ag.getNomeArquivo() %>">
                            <input type="submit" value="Consultar">
                        </form>
                    </td>
                    
                    <% if (ag.getUsuario() == analise.getUsuario()) { %>
                    <td valign="middle">
                        <form action="consultarAnaliseGrafica.jsp" method="post">
                            <input type="hidden" name="idAnalise" value="<%= ag.getIdAnalise() %>">
                            <input type="submit" value="Excluir">
                        </form>
                    </td>
                    <% } else { %>
                        <td><%= " " %></td>
                    <% } %>
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
