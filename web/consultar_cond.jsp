<%@ page contentType="text/html; charset=UTF-8" language="java"
import="java.sql.*" 
import="cgt.*" 
import="cdp.*" 
import="java.util.*"
import="utilitario.HibernateUtility"
import="org.hibernate.Session"
import="org.hibernate.Transaction"
import="org.hibernate.Query"
import="org.hibernate.Criteria"
errorPage="" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
        int id;
        Condicao c;
        String t;

        id = Integer.parseInt(request.getParameter("id"));
        c = AplCadastrarCondicao.obterCondicao(id);
        t = c.getTipo();
%>

<html>
    <head>
        <title>NETPLAY</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            <!--
            .style38 {font-size: 14px}
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
            .style41 {color: #009900}
            -->
        </style>
        <script language="JavaScript" type="text/JavaScript">
            <!--
			-->
        </script>
    </head>

<body>
        <div align="center">
            <form action="controlador.ControladorWebCCA" method="post" name="altAcao" id="altAcao" >
	        <% if ( t.compareTo("Per") == 0 ) { %>
			<form action="controlador.ControladorWebCCA" method="post" name="cperc" id="cperc">
                                                <table width="380" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td width="380" height="10" valign="top">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td><div align="center">
                                                            <table width="307" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                                                <tr>
                                                                    <td width="100">
                                                                    <div align="left">Agente Principal</div></td>
                                                                    <td width="201"><div align="left">
                                                                        <% List agentes;
                                                                           Percepcao cond = (Percepcao) c;
                                                                           Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                                           agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
                                                                           Agente agente = cond.getAgente();
                                                                           Agente agentePrincipal = cond.getAgentePrincipal();
                                                                           int distancia = cond.getDistancia();
                                                                        %>
                                                                        <select name="agentePrincipal" id="agentePrincipal" disabled>
                                                                            <option selected value= <%=agentePrincipal.getIdAgente()%>> <%=agentePrincipal.getNome()%> </option>
                                                                        </select>
                                                                    </div></td>
                                                                </tr>
                                                                <tr>
                                                                    <td> <div align="left">
                                                                    Agente</div></td>
                                                                    <td><div align="left">
                                                                        <select name="agente" id="agente" disabled>
                                                                            <option selected value= <%=agente.getIdAgente()%>> <%=agente.getNome()%> </option>
                                                                        </select>
                                                                    </div></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Dist&acirc;ncia</td>
                                                                    <td><select name="distancia" disabled >
                                                                        <option selected value=<%=distancia%>> <%=distancia%> </option>
                                                                    </select></td>
                                                                </tr>
                                                            </table>
                                                        </div></td>
                                                    </tr>
                                                    <tr>
                                                        <td><div align="center">
                                                            <input name="metodo" type="hidden" value="cadastroCondicao">
                                                            <input name="acao" type="hidden" value="alterar">
                                                            <input name="TipoCondicao" type="hidden" id="TipoCondicao" value="Percepcao">
                                                        </div></td>
                                                    </tr>
                                                </table>
                                            </form>
    	           <%  }
	          	if ( t.compareTo("Pos") == 0 ) { %>
				<form action="controlador.ControladorWebCCA" method="post" name="posicionamento" id="posicionamento" onSubmit="MM_validateForm('Nome','','R','expressao','','R');return document.MM_returnValue">
                      <table width="380" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="380" height="10" valign="top">&nbsp;</td>
                        </tr>
                        <tr>
                          <td><div align="center">
                            <table width="356" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                              <tr>
                                <td width="84"><div align="left">
									<% List agentes;
									Posicionamento cond = (Posicionamento) c;
                                    Modelo m = (Modelo)session.getAttribute("modelo");
                                    agentes = AplCadastrarAgente.agentesDoModelo(m.getIdModelo().intValue());
									Agente agente = cond.getAgente();
									String expressao = cond.getExpressao();
                                    %>
                                    Agente</div></td>
                                <td colspan="2"><div align="left">
                                  <select name="agente" id="agente" disabled>
                                    <option selected value= <%=agente.getIdAgente()%>> <%=agente.getNome()%> </option>
                                  </select>
                                </div></td>
                              </tr>
                              <tr>
                                <td>Express&atilde;o</td>
                                <td colspan="2"><textarea name="expressao" value="<%=expressao%>" cols="30" rows="3" id="expressao" disabled></textarea></td>
                              </tr>
                            </table>
                          </div></td>
                        </tr>
                        <tr>
                          <td><div align="center">
                              <input name="metodo" type="hidden" value="cadastroCondicao">
                              <input name="acao" type="hidden" value="alterar">
                              <input name="TipoCondicao" type="hidden" id="TipoCondicao" value="Posicionamento">
						  </div></td>
                        </tr>
                      </table>
                  </form>
	               <%  }
	            if ( t.compareTo("CmpVar") == 0 ) { %>
				<form action="controlador.ControladorWebCCA" method="post" name="ccmpvar" id="ccmpvar">
                      <table width="380" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="380" height="10" valign="top">&nbsp;</td>
                        </tr>
                        <tr>
                          <td><div align="center">
                            <table width="366" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                              <tr>
                                <td width="100">
                                  <div align="left">
                                     <% List vars1;
									 	CmpVariavel cond = (CmpVariavel) c;
                                        Modelo m1 = (Modelo)session.getAttribute("modelo");
                                        vars1 = AplCadastrarVariavel.variaveisDoModelo(m1.getIdModelo().intValue());
										Variavel var1 = cond.getVar1();
										Variavel var2 = cond.getVar2();
										String expressao = cond.getExpressao();
                                     %>
                                  Vari&aacute;vel 1 </div></td>
                                <td colspan="2"><div align="left">
                                    <select name="var1" id="var1" disabled>
                                      <%if (var1 != null) {%>
                                        <option selected value= <%=var1.getNome()%>> <%=var1.getNome()%> </option>
                                      <%}%>
                                      <%for(int i=0; i<vars1.size(); i++){
                                       Variavel v = (Variavel)vars1.get(i); %>
                                      <option value= <%= v.getNome() %>> <%= v.getNome() %></option>
                                      <%}%>
                                    </select>
                                </div></td>
                              </tr>
                              <tr>
                                <td> <div align="left">
                                        <% List vars2;
                                          Modelo m2 = (Modelo)session.getAttribute("modelo");
                                          vars2 = AplCadastrarVariavel.variaveisDoModelo(m2.getIdModelo().intValue());
                                        %>
                                     Vari&aacute;vel 2 </div></td>
                                <td colspan="2"><div align="left">
                                    <select name="var2" id="var2" disabled>
                                      <%if (var2 != null) {%>
                                        <option selected value= <%=var2.getNome()%>> <%=var2.getNome()%> </option>
                                      <%}%>
                                      <%for(int i=0; i<vars2.size(); i++){
                                       Variavel v = (Variavel)vars2.get(i); %>
                                      <option value= <%= v.getNome() %>> <%= v.getNome() %></option>
                                      <%}%>
                                    </select>
                                </div></td>
                              </tr>
                              <tr>
                                <td>Express&atilde;o</td>
                                <td colspan="2"><textarea name="expressao"  value="<%=expressao%>" cols="30" rows="3" id="expressao" disabled></textarea></td>
                              </tr>
                            </table>
                          </div></td>
                        </tr>
                        <tr>
                          <td><div align="center">
                              <input name="metodo" type="hidden" value="cadastroCondicao">
                              <input name="acao" type="hidden" value="alterar">
                              <input name="TipoCondicao" type="hidden" id="TipoCondicao" value="CmpVar">
						  </div></td>
                        </tr>
                      </table>
                  </form>
                   <%  }
	            if ( t.compareTo("ValVar") == 0 ) {%>	                
				<form action="controlador.ControladorWebCCA" method="post" name="valvar" id="valvar">
                                                <table width="380" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td width="380" height="10" valign="top">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td><div align="center">
                                                            <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                                                <tr>
                                                                    <td width="100">
                                                                    <div align="left">
                                                                    <% List vars1;
																		ValorVariavel cond = (ValorVariavel) c;
                                                                        Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                                        vars1 = AplCadastrarVariavel.variaveisDoModelo(m1.getIdModelo().intValue());
                                                                        Variavel var = cond.getVar();
                                                                        String expressao = cond.getExpr();
                                                                    %>
                                                                    Vari&aacute;vel</div></td>
                                                                    <td width="200" colspan="2"><div align="left">
                                                                        <select name="variavel" id="variavel" disabled>
                                                                            <%if (var != null) {%>
                                                                            <option selected value= <%=var%>> <%=var%> </option>
                                                                            <%}%>
                                                                            <%for(int i=0; i<vars1.size(); i++){
                                                                            Variavel v1 = (Variavel)vars1.get(i);%>
                                                                            <option value= <%= v1.getNome() %>> <%= v1.getNome() %></option>
                                                                            <%}%>
                                                                        </select>
                                                                    </div></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Express&atilde;o</td>
                                                                    <td colspan="2"><textarea name="expr" value="<%=expressao%>" cols="30" rows="3" id="expr" disabled ></textarea></td>
                                                                </tr>
                                                            </table>
                                                        </div></td>
                                                    </tr>
                                                    <tr>
                                                        <td><div align="center">
                                                            <input name="metodo" type="hidden" value="cadastroCondicao">
                                                            <input name="acao" type="hidden" value="alterar">
                                                            <input name="TipoCondicao" type="hidden" id="TipoCondicao" value="ValVar">
                                                        </div></td>
                                                    </tr>
                                                </table>
                                            </form>
          <%  } %>					
            </form>
        </div>
    </body>
</html>