<%@ page contentType="text/html; charset=iso-8859-1"  language="java" 
import="java.sql.*" 
errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
String nome = "";
String acao = "";
String valorMinimo="";
String valorMaximo="";
String incremento="";
String valorInicial="";
String nomeAgente = "";
nome = request.getParameter("nome");
valorMinimo = request.getParameter("valorMinimo");
valorMaximo = request.getParameter("valorMaximo");
valorInicial = request.getParameter("valorInicial");
incremento = request.getParameter("incremento");
nomeAgente = request.getParameter("nomeAgente");
acao = request.getParameter("acao");
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
body {
	background-color: #DCE7DE;
}
a {
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
.style1 {color: #DCE7DE}
.style5 {color: #FF0000}
.style38 {font-size: 14px}
.style40 {color: #DCE7DE; font-size: 11px; }
-->
</style>
</head>

<body>

<div align="center">
  <table width="500" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td colspan="4"><div align="center" class="style38">
        <% if(acao.compareTo("Excluir")==0){%>
Exclus&atilde;o Vari&aacute;vel Slider
<%} else { %>
Consulta Vari&aacute;vel Slider
<%}%>
</div></td>
    </tr>
    <tr>
      <td colspan="4">&nbsp;</td>
    </tr>
    <tr>
      <td height="15" colspan="4"><div align="center"> </div></td>
    </tr>
    <tr>
      <td height="15" colspan="4"><div align="center">
          <table width="500" border="1" bgcolor="#BBD0BF">
            <tr>
              <td><div align="center">
                  <table width="380" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td colspan="3">
                          <table width="380" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td width="380" height="10" valign="top">&nbsp;</td>
                            </tr>
                            <tr>
                              <td><div align="center">
                                  <table width="380" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                    <tr>
                                      <td width="140"> <div align="left">Nome </div></td>
                                      <td width="240"><input name="nome" type="text" id="Nome" value="<%=nome%>" size="40" disabled></td>
                                    </tr>
                                    <tr>
                                      <td> <div align="left">Valor Inicial </div></td>
                                      <td><input name="valorinicial" type="text" id="Valor Inicial" value="<%=valorInicial%>" size="40" disabled></td>
                                    </tr>
                                    <tr>
                                      <td> <div align="left">Valor M&iacute;nimo </div></td>
                                      <td><input name="valorminimo" type="text" id="Valor Minimo" value="<%=valorMinimo%>" size="40" disabled></td>
                                    </tr>
                                    <tr>
                                      <td> <div align="left">Valor M&aacute;ximo </div></td>
                                      <td><input name="valormaximo" type="text" id="Valor Maximo" value="<%=valorMaximo%>" size="40" disabled></td>
                                    </tr>
                                    <tr>
                                      <td> <div align="left">Incremento </div></td>
                                      <td><input name="incremento" type="text" id="Incremento" value="<%=incremento%>" size="40" disabled></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2">&nbsp;</td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><div align="center">Vari&aacute;vel associada a algum agente? </div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2">&nbsp;</td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><div align="center">
                                          <table width="150" border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                              <td width="60"><table width="60" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                  <tr>
                                                    <td width="20"><div align="center">
	   	                                          <% if (nomeAgente != ""){%>
                                                  <input name="variavel" type="radio" disabled value="sim" checked>
												  <%} else{ %>	
        		                                   <input name="variavel" type="radio" disabled value="sim">
												    <%}%>
                                                    </div></td>
                                                    <td width="40"><div align="center" class="style1">SIM</div></td>
                                                  </tr>
                                              </table></td>
                                              <td width="30">&nbsp;</td>
                                              <td width="60"><table width="60" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                  <tr>
                                                    <td width="20"><div align="center">
     												   <% if (nomeAgente != ""){%>
                                                       <input name="variavel" type="radio" disabled value="n&atilde;o">
													   <%} else{ %>	
                                           		      <input name="variavel" type="radio" disabled value="n&atilde;o" checked>
		         	 	   						       <%}%>
                                                    </div></td>
                                                    <td width="40"><div align="center" class="style1">N&Atilde;O</div></td>
                                                  </tr>
                                              </table></td>
                                            </tr>
                                          </table>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><div align="center">
                                        <table width="380" border="0" cellspacing="0" cellpadding="0">
                                          <tr>
                                            <td>&nbsp;</td>
                                          </tr>
										  <% if(nomeAgente != ""){ %>
                                          <tr>
                                            <td><div align="center">
                                              <table width="250" height="20" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                  <td width="125">
                                                    <div align="center">
                                                      Agente </div></td>
                                                  <td width="125">
                                                      <div align="left">
                                                      <select name="escolha do agente" id="escolha do agente">
        				                              <option selected value= <%=nomeAgente%>> <%=nomeAgente%></option>
                                                      </select>
                                                    </div></td></tr>
                                              </table>
                                            </div></td>
                                          </tr>
      									 <%}%>
                                           <tr>
                                            <td>&nbsp;</td>
                                          </tr>
                                        </table>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><div align="center">
                                      </div></td>
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
                      <td width="400"><div align="right">
                        <table width="80" height="25" border="1" cellpadding="0" cellspacing="0" bgcolor="#333300">
                          <tr>
                            <td><div align="center" class="style1"> </div>
                                <div align="center" class="style1"><a href="controlador.ControladorWeb?metodo=cadastroVariavel&acao=excluirVariavel&nome=<%=nome%>">Excluir</a></div></td>
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
<div align="center"></div>
</body>
</html>
