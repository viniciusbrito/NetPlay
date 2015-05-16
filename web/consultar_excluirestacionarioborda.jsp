<%@ page contentType="text/html; charset=iso-8859-1"  language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String status = "";
String nome = "";
String acao = "";
String cor = "";
String tamanho = "";
status = request.getParameter("status");
nome = request.getParameter("nome");
acao = request.getParameter("acao");
cor = request.getParameter("cor");
tamanho = request.getParameter("tamanho");

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
.style1 {color: #DCE7DE}
.style5 {color: #FF0000}
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
	color: #DCE7DE;
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
.style38 {font-size: 14px}
.style40 {color: #DCE7DE; font-size: 11px; }
.style41 {font-size: 11px}
-->
</style>
</head>

<body>

<div align="center">
  <table width="525" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><div align="center" class="style38">
        <% if(acao.compareTo("Excluir")==0){%>
Exclus&atilde;o Agente Borda
<%} else { %>
Consulta Agente Borda
<%}%>
</div></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td height="15">&nbsp;</td>
    </tr>
    <tr>
      <td height="15"><div align="center">
          <table width="525" border="1" bgcolor="#BBD0BF">
            <tr>
              <td><div align="center">
                <table width="430" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td colspan="3">
                        <table width="430" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td height="10"><div align="center">
                              </div></td>
                          </tr>
                          <tr>
                            <td><div align="center">
                              <table width="430" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                <tr>
                                  <td width="190"><div align="left">&nbsp;Nome</div></td>
                                  <td width="240"><input name="Nome" type="text"  disabled id="Nome2" value="<%=nome%>" size="40" maxlength="30"></td>
                                </tr>
                                <tr>
                                  <td width="190"><div align="left">&nbsp;Cor</div></td>
                                  <td width="240"><div align="left">
                                    <select name="cor" id="select7" disabled >
		                             <% if(cor.equals("MAGENTA2")) { %>
                                                    <option selected value= <%=cor%>>MAGENTA</option>
                                             <% } else { %>
                                                    <option selected value= <%=cor%>> <%=cor%></option>
                                             <% } %>
                                    </select>
                                  </div></td>
                                </tr>
                                <tr>
                                  <td width="190">
                                    <div align="left">&nbsp;Tamanho da Borda </div></td>
                                  <td width="240">
                                      <div align="left">
                                        <select name="Tamanho da Borda" id="select8" disabled>
	     	                                <option selected value= <%=tamanho%>> <%=tamanho%></option>
                                        </select>
                                      </div></td>
                                </tr>
                                <tr>
                                  <td colspan="2"><div align="center"></div>
                                      <div align="center"> </div></td>
                                </tr>
                              </table>
                            </div></td>
                          </tr>
                          <tr>
                            <td height="10"><div align="center">
                                </div></td>
                          </tr>
                          <tr>
                            <td height="10"><div align="center">
                            </div></td>
                          </tr>
                        </table>
						</td>
                  </tr>
                  <tr>
                  <% if(acao.compareTo("Excluir")==0){%>
                    <td width="431"><div align="right">
                      <table width="80" height="25" border="1" cellpadding="0" cellspacing="0" bgcolor="#333300">
                        <tr>
                          <td><div align="center" class="style1"> </div>
                              <div align="center" class="style1"><a href="controlador.ControladorWeb?metodo=cadastroAgente&acao=excluirAgente&nome=<%=nome%>">Excluir</a></div></td>
                        </tr>
                      </table>
                      </div></td>
					  <%} else { %>
					  <td width="431"><div align="right">
                      </div></td>
	  			      <%}%>
                  </tr>
                  <tr>
                    <td height="10" colspan="3">
                      <div align="right">                        </div></td>
                  </tr>
                </table>
                  </div></td>
            </tr>
          </table>
      </div></td>
    </tr>
  </table>
</div>
<div align="center"></div>
</body>
</html>
