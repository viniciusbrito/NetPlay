<%@ page contentType="text/html; charset=iso-8859-1"  language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String nome = "";
int idAgente;
String cor = "";
String grauDispersao= "";
String acao= "";
String status = "";
String posicao="";
posicao = request.getParameter("tipoPosicao");
nome = request.getParameter("nome");
cor = request.getParameter("cor");
grauDispersao = request.getParameter("grauDispersao");
acao = request.getParameter("acao");
status = request.getParameter("status");
idAgente = Integer.parseInt(request.getParameter("idAgente"));
if(status == null)
	status = " ";

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
.style1 {color: #DCE7DE}
.style5 {color: #FF0000}
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
.style38 {font-size: 14px}
.style40 {color: #DCE7DE; font-size: 11px; }
.style41 {font-size: 11px}
-->
</style>
</head>
<body>
<form name="est">
<div align="center">
  <table width="525" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td colspan="4"><div align="center" class="style38"><% if(acao.compareTo("Excluir")==0){%> 
        Exclus&atilde;o Agente Estacion&aacute;rio 
            <%} else { %>
      Consulta Agente Estacion&aacute;rio<%}%></div></td>
    </tr>
    <tr>
      <td colspan="4">&nbsp;</td>
    </tr>
    <tr>
      <td height="15" colspan="4">&nbsp;</td>
    </tr>
    <tr>
      <td height="15" colspan="4"><div align="center">
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
                                  <td width="190"><div align="left"> &nbsp;Nome</div></td>
                                  <td width="240"><input name="Nome" type="text" disabled id="Nome" value="<%=nome%>" size="40" maxlength="30"></td>
                                </tr>
                                <tr>
                                  <td width="190">
                                    <div align="left">&nbsp;Cor</div></td>
                                  <td width="240"><div align="left">
                                    <select name="cor" id="cor" disabled>
   	                                <% if(cor.equals("MAGENTA2")) { %>
                                                    <option selected value= <%=cor%>>MAGENTA</option>
                                        <% } else { %>
                                                    <option selected value= <%=cor%>> <%=cor%></option>
                                        <% } %>                                    
				    </select>
                                  </div></td>
                                </tr>
                               <%if(posicao.compareTo("aleatorio")==0){%>
                                <tr>
                                  <td width="190">
                                    <div align="left">&nbsp;Grau de Dispers&atilde;o (%) </div></td>
                                  <td width="240">
                                    <div align="left">
                                      <input name="Grau de Dispersao" type="text" disabled id="graudispersao" value="<%=grauDispersao%>" size="10" maxlength="3">
                                 </div></td>
                                </tr>
								<%}%>
                                <tr>
                                  <td colspan="2"><table width="430" border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                      <th height="20" scope="row">Posicionamento Inicial</th>
                                    </tr>
                                    <tr>
                                      <th scope="row"><table width="430" border="1" cellpadding="0" cellspacing="0">
                                          <tr>
                                            <th width="116" scope="row">&nbsp;</th>
                                            <th width="190" scope="row"><table width="190" height="20" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                <tr>
                                                  <td width="20"><div align="center">
                                                      <%if(posicao.compareTo("aleatorio")==0){%>
                                                      <input name="posicao" type="radio" checked disabled>
                                                      <%}else{%>
                                                      <input name="posicao" type="radio" disabled>
                                                      <%}%>
                                                  </div></td>
                                                  <td width="230"><div align="center" class="style1">
                                                      <div align="center" class="style41">
                                                        <div align="center">Aleat&oacute;rio</div>
                                                      </div>
                                                  </div></td>
                                                </tr>
                                            </table></th>
                                            <td width="116">&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <th scope="row">&nbsp;</th>
                                            <th scope="row"><table width="190" height="20" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                <tr>
                                                  <td width="20" height="16"><div align="center">
                                                      <%if(posicao.compareTo("posicaoInicial")==0){%>
                                                      <input name="posicao" type="radio"  checked disabled>
                                                      <%}else{%>
                                                      <input name="posicao" type="radio"  disabled>
                                                      <%}%>
                                                  </div></td>
                                                  <td width="230"><div align="center" class="style1">
                                                      <div align="center" class="style41">Posi&ccedil;&atilde;o Inicial Espec&iacute;fica</div>
                                                  </div></td>
                                                </tr>
                                            </table></th>
                                            <td>&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <th colspan="3" scope="row">
                                              </th>
                                          </tr>
                                      </table></th>
                                    </tr>
                                  </table></td>
                                </tr>
                               <%if(posicao.compareTo("posicaoInicial")==0){%>
                                <tr>
                                  <td colspan="2">
                                 <input name="idAgente" type="hidden" id="idAgente" value="<%= idAgente%>">								  
                                 <table width="120" height="20" border="0" align="center" cellpadding="0" cellspacing="0">
                                    <tr>
                                      <td bgcolor="#333300"><div align="center" class="style1"><a href="javascript:window.location = 'consulta_posicoes.jsp?nome='+ document.est.Nome.value + '&cor=' + document.est.cor.value + '&idAgente=' + document.est.idAgente.value">Consultar Posi&ccedil;&otilde;es</a> </div></td>
                                    </tr>
                                  </table>
								  </td>
                                </tr>
        						  <%}%>
                                <tr>
                                  <td colspan="2"><div align="center"> </div></td>
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
                  <td width="456"><div align="right">
                      <table width="80" height="25" border="1" cellpadding="0" cellspacing="0" bgcolor="#333300">
                        <tr>
                          <td><div align="center" class="style1"> </div>
                              <div align="center" class="style1"><a href="controlador.ControladorWeb?metodo=cadastroAgente&acao=excluirAgente&nome=<%=nome%>">Excluir</a></div></td>
                        </tr>
                      </table>
                      </div></td>
     			    <%} else { %>
					 <td width="456"><div align="right">
                      </div></td>
					  <%}%>
                  </tr>
                  <tr>
                    <td height="10" colspan="3"><div align="right">
                        </div></td>
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
</form>
</body>
</html>
