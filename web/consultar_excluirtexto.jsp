<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
String nome = "";
String texto = "";
String acao= "";
String tipo ="";
nome = request.getParameter("nome");
texto = request.getParameter("texto");
acao = request.getParameter("acao");
tipo = request.getParameter("tipo");
%>

<html>
<head>
<title>NetPlay</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
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
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #DCE7DE;
}
a:hover {
	text-decoration: none;
	color: #A8C4AD;
}
a:active {
	text-decoration: none;
	color: #DCE7DE;
}
body {
	background-color: #DCE7DE;
}
.style1 {font-size: 14px}
.style5 {color: #FF0000}
.style2 {color: #DCE7DE}
-->
</style>
</head>

<body>

<div align="center">
  <table width="525" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><div align="center" class="style1">  <% if(acao.compareTo("Excluir")==0){%> 
      Exclus&atilde;o Texto 
          <%} else { %>Consulta Texto <%}%></div></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><div align="center">
        <table width="525" border="1" bgcolor="#BBD0BF">
          <tr>
            <td><div align="center">
                <table width="400" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td colspan="3">
                        <table width="400" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td><div align="center">
                                <table width="400" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                  <tr>
                                    <td width="160"><div align="left"> Nome</div></td>
                                    <td width="240"><input name="Nome" type="text" disabled id="Nome4" value="<%= nome %>" size="40"></td>
                                  </tr>
                                  <tr>
                                    <td> <div align="left">Texto</div></td>
                                    <td><textarea name="Texto" cols="30" rows="3" disabled id="texto2"><%= texto %></textarea></td>
                                  </tr>
                                  <tr>
                                    <td colspan="2">&nbsp;</td>
                                  </tr>
                                </table>
                            </div></td>
                          </tr>
                          <tr>
                            <td height="10"><div align="center">
                            </div></td>
                          </tr>
                          <tr>
                            <td height="10"><div align="center"></div></td>
                          </tr>
                        </table>
					  </td>
                  </tr>
                  <tr>
                    <% if(acao.compareTo("Excluir")==0){%>
					<td width="400"><div align="right">
                      <table width="80" height="25" border="1" cellpadding="0" cellspacing="0" bgcolor="#333300">
                        <tr>
                          <td><div align="center" class="style2"> </div>
                              <div align="center" class="style2"><a href="controlador.ControladorWeb?metodo=cadastroTexto&acao=excluirTexto&nome=<%=nome%>">Excluir</a></div></td>
                        </tr>
                      </table>
                      </div></td>
					  <%} else { %>
					  <td width="400"><div align="right">
                      </div></td>
     				  <%}%>

                  </tr>
                  <tr>
                    <td height="10" colspan="3"><div align="right"> </div></td>
                  </tr>
                </table>
            </div></td>
          </tr>
        </table>
      </div></td>
    </tr>
  </table>
</div>
</body>
</html>
