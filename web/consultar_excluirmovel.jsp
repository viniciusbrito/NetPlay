<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String nome;
String cor;
String tamanho;
String forma;
String quantidadeInicial;
String tipoQtdInicial;
String posicao;
String tipoMovimento;
String passo = "";
String anguloMinimo = "";
String anguloMaximo = "";
String anguloReflexao ="";
String acao = "";
int idAgente;

idAgente = Integer.parseInt(request.getParameter("idAgente"));
acao = request.getParameter("acao");
tamanho = request.getParameter("tamanho");
nome = request.getParameter("nome");
cor = request.getParameter("cor");
forma = request.getParameter("forma");
quantidadeInicial = request.getParameter("quantidadeInicial");
tipoQtdInicial = request.getParameter("tipoQtdInicial");

tipoMovimento = request.getParameter("tipoMovimento");
if(tipoMovimento.compareTo("Passear")==0){
	anguloMinimo = request.getParameter("anguloMinimo");
	anguloMaximo = request.getParameter("anguloMaximo");
	passo = request.getParameter("passo");
}
else{
	anguloReflexao = request.getParameter("anguloReflexao");
}
posicao = request.getParameter("tipoPosicao");

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
.style1 {color: #DCE7DE}
.style5 {color: #FF0000}
.style38 {font-size: 14px}
.style40 {color: #DCE7DE; font-size: 11px; }
.style41 {font-size: 11px}
-->
</style>
</head>

<body>
<div align="center"></div>
<div align="center">
  <table width="525" border="0" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
    <tr>
      <td><div align="center">
        <table width="520" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="4"><div align="center" class="style38">
              <% if(acao.compareTo("Excluir")==0){%>
Exclus&atilde;o Agente M&oacute;vel 
<%} else { %>
Consulta Agente M&oacute;vel 
<%}%>
            </div></td>
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
                    <table width="393" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>
                            <table width="393" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td>&nbsp;</td>
                              </tr>
                              <tr>
                                <td height="10"><div align="center">
                                  <table width="393" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                    <tr>
                                      <td width="170"><div align="left">Nome</div></td>
                                      <td width="240"><input name="Nome" type="text" id="Nome" value="<%=nome%>" disabled size="35"></td>
                                    </tr>
                                    <tr>
                                      <td><div align="left"></div>
                                          <div align="left"></div>
                                          <div align="left">Cor </div></td>
                                      <td><div align="left">
                                          <select name="cor" id="cor" disabled>
                                            <% if(cor != ""){
                                                if(cor.equals("MAGENTA2")) { %>
                                                    <option selected value= <%=cor%>>MAGENTA</option>
                                                <% } else { %>
                                                    <option selected value= <%=cor%>> <%=cor%></option>
                                                <% } %>
                                            <option value="cor">cor</option>
                                            <%}else{%>
                                            <option value="cor2">cor2</option>
                                            <%}%>
                                          </select>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td><div align="left">Tamanho </div>
                                          <div align="left"></div>
                                          <div align="left"></div></td>
                                      <td><div align="left">
                                          <select name="Tamanho" id="Tamanho" disabled>
                                            <% if(tamanho != ""){%>
                                            <option selected value= <%=tamanho%>> <%=tamanho%></option>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <%}else{%>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <%}%>
                                          </select>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td><div align="left">Forma</div></td>
                                      <td><div align="left">
                                          <select name="Forma" id="Forma" disabled>
                                            <% if(forma != ""){%>
                                            <option selected value= <%=forma%>> <%=forma%></option>
                                            <option value="figura">figura</option>
                                            <%}else{%>
                                            <option value="figura2">figura2</option>
                                            <%}%>
                                          </select>
                                      </div></td>
                                    </tr>
                                    
                                    <!--<tr>
                                      <td><div align="left">Quantidade Inicial </div></td>
                                      <td><input name="quantidadeInicial" type="text" id="Quantidade Inicial" disabled value="<%=quantidadeInicial%>" size="35"></td>
                                    </tr>-->
                                    
                                    <tr>
                                      <td colspan="3">
                                         <div align="center">Quantidade Inicial</div></td>
                                    </tr>
                                    
                                   <tr>
                                            <td><div align="left">
                                                <table width="155" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                    <tr>
                                                        <td width="22"><div align="center">
                                                            <% if (tipoQtdInicial.equals("Num")) { %>
                                                                <input name="quantidadeInicial" type="radio" disabled value="0" checked>
                                                            <% } else { %>
                                                                <input name="quantidadeInicial" type="radio" disabled value="0"> <% } %>
                                                      </div></td>
                                                        <td width="126"><div align="center" class="style1">
                                                            N&uacute;mero
                                                      </div></td>
                                                    </tr>
                                                    <tr>
                                                        <td width="22"><div align="center">
                                                            <% if (tipoQtdInicial.equals("Var")) { %>
                                                                <input name="quantidadeInicial" type="radio" disabled value="1"checked>
                                                            <% } else { %>
                                                                <input name="quantidadeInicial" type="radio" disabled value="1"> <% } %>
                                                      </div></td>
                                                        <td width="126"><div align="center" class="style1">
                                                            Vari&aacute;vel Slider
                                                      </div></td>
                                                    </tr>
                                                </table>
                                            </div></td>
                                            <td colspan="2" align="middle">
                                                <% if (tipoQtdInicial.equals("Num")) { %>
                                                    <input name="numeroQtdInicial" id="Quantidade Inicial" type="text" disabled value="<%=quantidadeInicial%>" size="35" maxlength="10">
                                                <% } else { %>
                                                    <div align="center"><select name="numeroQtdInicial" id="Quantidade Inicial" disabled>
                                                           <option selected value="1"><%=quantidadeInicial%></option>     
                                                    </div></select>
                                                <% } %>                                                
                                            </td>
                                    </tr>
                                    
                                    <tr>
                                      <td colspan="2">
                                        <div align="center">Posi&ccedil;&atilde;o Inicial</div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><div align="left"></div>
                                          <div align="left">
                                            <table width="393" border="1" cellpadding="0" cellspacing="0">
                                              <tr>
                                                <th width="98" scope="row">&nbsp;</th>
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
                                                <td width="97">&nbsp;</td>
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
                                                <th colspan="3" scope="row"> </th>
                                              </tr>
                                            </table>
                                        </div></td>
                                    </tr>
									<%if(posicao.compareTo("posicaoInicial")==0){%>                                    
									<tr>
                                      <td colspan="2"><input name="idAgente" type="hidden" id="idAgente" value="<%= idAgente%>">                                                <table width="120" height="20" border="0" align="center" cellpadding="0" cellspacing="0">
                                          <tr>
                                            <td bgcolor="#333300"><div align="center" class="style1"><a href="javascript:window.location.href = 'consulta_posicoesMovel.jsp?idAgente=' + <%=idAgente%>">Consultar Posi&ccedil;&otilde;es</a> </div></td>
                                          </tr>
                                      </table></td>
                                    </tr>
                                    <%}%>
                                    <tr>
                                      <td colspan="2"><div align="center"></div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><div align="center">Movimento</div></td>
                                    </tr>
                                    <tr>
                                      <td>
                                        <div align="center">Tipo de Movimento </div></td>
                                      <td><div align="center">
                                          <table width="170" height="5" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                              <td width="75"><table width="75" height="5" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                  <tr>
                                                    <td width="20">
                                                      <%if(tipoMovimento.compareTo("Passear")==0){%>
                                                      <input name="tipoMovimento" type="radio" checked disabled>
                                                      <%}else{%>
                                                      <input name="tipoMovimento" type="radio" disabled>
                                                      <%}%>													
                                                    <td width="55"><div align="center" class="style1">Passear</div></td>
                                                  </tr>
                                              </table></td>
                                              <td width="10">&nbsp;</td>
                                              <td width="70"><table width="70" height="5" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                  <tr>
                                                    <td width="20"><div align="center">
						      <%if(tipoMovimento.compareTo("Refletir")==0){%>
                                                      <input name="tipoMovimento" type="radio" checked disabled>
                                                      <%}else{%>
                                                      <input name="tipoMovimento" type="radio" disabled>
                                                      <%}%>	
                                                    </div></td>
                                                    <td width="50"><div align="center" class="style1">Refletir</div></td>
                                                  </tr>
                                              </table></td>
                                              <td width="10">&nbsp;</td>
                                              <td width="70"><table width="70" height="5" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                  <tr>
                                                    <td width="20"><div align="center">
						      <%if(tipoMovimento.compareTo("Parado")==0){%>
                                                      <input name="tipoMovimento" type="radio" checked disabled>
                                                      <%}else{%>
                                                      <input name="tipoMovimento" type="radio" disabled>
                                                      <%}%>	
                                                    </div></td>
                                                    <td width="50"><div align="center" class="style1">Parado</div></td>
                                                  </tr>
                                              </table></td>
                                            </tr>
                                          </table>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><div align="center">
                                          <table width="393" border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                              <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                              <td><div align="center">
						<%if(tipoMovimento.compareTo("Passear")==0){%>
                                                  <table width="250" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                      <td width="130">
                                                        <div align="left">Passo</div></td>
                                                      <td width="120"><input name="passo" type="text" id="Passo" value="<%=passo%>" size="20" disabled></td>
                                                    </tr>
                                                    <tr>
                                                      <td>
                                                        <div align="left">&Acirc;ngulo M&iacute;nimo</div></td>
                                                      <td><input name="anguloMinimo" type="text" id="Angulo Minimo" disabled value="<%=anguloMinimo%>" size="20"></td>
                                                    </tr>
                                                    <tr>
                                                      <td><div align="left">&Acirc;ngulo M&aacute;ximo </div></td>
                                                      <td><input name="anguloMaximo" type="text" id="Angulo Maximo" disabled value="<%=anguloMaximo%>" size="20"></td>
                                                    </tr>
                                                  </table>
                                                  <%}if(tipoMovimento.compareTo("Refletir")==0) {%>
						  <table width="250" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                      <td width="130"><div align="center">&Acirc;ngulo Reflex&atilde;o</div></td>
                                                      <td width="120"><input name="anguloReflexao" type="text" id="Angulo Reflexao" value="<%=anguloReflexao%>" size="20" maxlength="2" disabled></td>
                                                    </tr>
                                                  </table>
                                                  <%}%>
                                              </div></td>
                                            </tr>
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
                                <td height="10"><div align="center"></div></td>
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
                            </table>						</td>
                      </tr>

                  <tr>
                        <td width="456" height="10"><div align="right"> </div></td>
                      </tr>
                    </table>
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
</body>
</html>
