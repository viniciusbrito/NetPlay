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
                                        <div align="left">Agente M&oacute;vel</div></td>
                                        <td width="201"><div align="left">
                                            <% List agentes;
                                                Percepcao cond = (Percepcao) c;
                                                Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
                                                Agente agente = cond.getAgente();
                                                Agente agenteM = AplCadastrarAgente.obterAgente(cond.getAgentePrincipal().getIdAgente());
                                                int distancia = cond.getDistancia().intValue();
                                            %>
                                            <select name="agenteMovel" id="agenteMovel">
                                                <%if (agenteM != null) {%>
                                                <option selected value= <%=agenteM.getIdAgente()%>> <%=agenteM.getNome()%> </option>
                                                <%}%>
                                                <%for(int i=0; i<agentes.size(); i++){
                                                        Agente a = (Agente)agentes.get(i);
                                                        if (a.getTipo().compareTo("Movel")==0){%>
                                                <option value= <%= a.getIdAgente() %>> <%= a.getNome() %></option>
                                                <%}}%>
                                            </select>
                                        </div></td>
                                    </tr>
                                    <tr>
                                        <td> <div align="left">
                                        Agente</div></td>
                                        <td><div align="left">
                                            <select name="agente" id="agente">
                                                <%if (agente != null) {%>
                                                <option selected value= <%=agente.getIdAgente()%>> <%=agente.getNome()%> </option>
                                                <%}%>
                                                <%for(int i=0; i<agentes.size(); i++){
                                                        Agente a1 = (Agente)agentes.get(i); %>
                                                <option value= <%= a1.getIdAgente() %>> <%= a1.getNome() %></option>
                                                <%}%>
                                            </select>
                                        </div></td>
                                    </tr>
                                    <tr>
                                        <td>Dist&acirc;ncia</td>
                                        <td><select name="distancia">
                                            <option selected value=<%=distancia%>> <%=distancia%> </option>
                                            <%for(int i=0; i<11; i++){%>
                                            <option value= <%=i%>> <%=i%></option>
                                            <%}%>
                                        </select></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><div align="center">
                                            <input name="salvar condicao" type="submit" id="salvar condicao" value="Salvar Condi&ccedil;&atilde;o">
                                        </div></td>
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
                                            <select name="agente" id="agente">
                                                <%if (agente != null) {%>
                                                <option selected value= <%=agente.getIdAgente()%>> <%=agente.getNome()%> </option>
                                                <%}%>    
                                                <%for(int i=0; i<agentes.size(); i++){
                                                Agente a = (Agente)agentes.get(i); %>
                                                <option value= <%= a.getIdAgente() %>> <%= a.getNome() %></option>
                                                <%}%>
                                            </select>
                                        </div></td>
                                    </tr>
                                    <tr>
                                        <td>Express&atilde;o</td>
                                        <td colspan="2"><textarea name="expressao" value="<%=expressao%>" cols="30" rows="3" id="expressao"></textarea></td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td width="138" bgcolor="#333300"><div align="center" class="style34"><a href="editarexp.jsp">Editar Express&atilde;o</a></div></td>
                                        <td width="126" bgcolor="#333300"><div align="center" class="style34"><a href="editarexp.jsp">Validar Express&atilde;o </a></div></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td colspan="3"><div align="center">
                                            <input name="salvar condicao" type="submit" id="salvar condicao" value="Salvar Condi&ccedil;&atilde;o">
                                        </div></td>
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
            if ( t.compareTo("CorAg") == 0 ) { %>
                <form action="controlador.ControladorWebCCA" method="post" name="cperc" id="cperc" onSubmit="MM_validateForm('nome','','R');return document.MM_returnValue">
                    <table width="380" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="380" height="10" valign="top">&nbsp;</td>
                        </tr>
                        <tr>
                            <td><div align="center">
                                <table width="307" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                    <tr>
                                        <td width="100"><div align="left"> Agente</div></td>
                                        <td width="201"><div align="left">
                                            <% List agentes;
                                                CorAgente cond = (CorAgente) c;
                                                Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
                                                Agente agente = cond.getAgente();
                                                String cor = cond.getCor();
                                            %>
                                            <select name="agente" id="agente">
                                                <%if (agente != null) {%>
                                                <option selected value= <%=agente%>> <%=agente%> </option>
                                                <%}%>
                                                <%for(int i=0; i<agentes.size(); i++){
                                                        Agente a1 = (Agente)agentes.get(i); %>
                                                <option value= <%= a1.getIdAgente() %>> <%= a1.getNome() %></option>
                                                <%}%>
                                            </select>
                                        </div></td>
                                    </tr>
                                    <tr>
                                        <td><div align="left">Cor</div></td>
                                        <td><div align="left">
                                            <select name="cor" id="cor">
                                                <% if(cor != null){%>
                                                <% int teste = 0;
                                                    if(cor.compareTo("VERDELIMAO")==0){%>
                                                <option selected value= <%=cor%>>VERDE LIMAO</option>
                                                <%teste = 1;
                                                    }%>
                                                <%if(cor.compareTo("AZULCLARO")==0){%>
                                                <option selected value= <%=cor%>>AZUL CLARO</option>
                                                <%teste = 1;
                                                    }%>
                                                <%if(cor.compareTo("VERDETURQUESA")==0){%>
                                                <option selected value= <%=cor%>>VERDE TURQUESA</option>
                                                <%teste = 1;
                                                    }%>
                                                <%if(teste==0){%>
                                                <option selected value= <%=cor%>> <%=cor%></option>
                                                <%}%>
                                                <option value="PRETO">PRETO</option>
                                                <option value="AZUL">AZUL</option>
                                                <option value="MARROM">MARROM</option>
                                                <option value="CIANO">CIANO</option>
                                                <option value="CINZA">CINZA</option>
                                                <option value="VERDE">VERDE</option>
                                                <option value="VERDELIMAO">VERDE LIMAO</option>
                                                <option value="MAGENTA">MAGENTA</option>
                                                <option value="LARANJA">LARANJA</option>
                                                <option value="ROSA">ROSA</option>
                                                <option value="VERMELHO">VERMELHO</option>
                                                <option value="AZULCLARO">AZUL CLARO</option>
                                                <option value="VERDETURQUESA">VERDE TURQUESA</option>
                                                <option value="VIOLETA">VIOLETA</option>
                                                <option value="BRANCO">BRANCO</option>
                                                <option value="AMARELO">AMARELO</option>
                                                <%}else{%>
                                                <option value="PRETO">PRETO</option>
                                                <option value="AZUL">AZUL</option>
                                                <option value="MARROM">MARROM</option>
                                                <option value="CIANO">CIANO</option>
                                                <option value="CINZA">CINZA</option>
                                                <option value="VERDE">VERDE</option>
                                                <option value="VERDELIMAO">VERDE LIMAO</option>
                                                <option value="MAGENTA">MAGENTA</option>
                                                <option value="LARANJA">LARANJA</option>
                                                <option value="ROSA">ROSA</option>
                                                <option value="VERMELHO">VERMELHO</option>
                                                <option value="AZULCLARO">AZUL CLARO</option>
                                                <option value="VERDETURQUESA">VERDE TURQUESA</option>
                                                <option value="VIOLETA">VIOLETA</option>
                                                <option value="BRANCO">BRANCO</option>
                                                <option value="AMARELO">AMARELO</option>
                                                <%}%>
                                            </select>
                                        </div></td>
                                        <td width="121" bgcolor="#333300"><div align="center" class="style1"><a href="#" onClick="MM_openBrWindow('cores.jsp','cores','width=420,height=470')">Cores</a></div></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><div align="center">
                                            <input name="salvar condicao" type="submit" id="salvar condicao" value="Salvar Condi&ccedil;&atilde;o">
                                        </div></td>
                                    </tr>
                                </table>
                            </div></td>
                        </tr>
                        <tr>
                            <td><div align="center">
                                <input name="metodo" type="hidden" value="cadastroCondicao">
                                <input name="acao" type="hidden" value="incluir">
                                <input name="TipoCondicao" type="hidden" id="TipoCondicao" value="CorAgente">
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
                                            <select name="var1" id="var1">
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
                                            <select name="var2" id="var2">
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
                                        <td colspan="2"><textarea name="expressao"  value="<%=expressao%>" cols="30" rows="3" id="expressao"></textarea></td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td width="130" bordercolor="#DCE7DE" bgcolor="#333300"><div align="center" class="style34"><a href="editarexp.jsp">Editar Express&atilde;o</a></div></td>
                                        <td width="128" bordercolor="#DCE7DE" bgcolor="#333300"><div align="center" class="style34"><a href="editarexp.jsp">Validar Express&atilde;o </a></div></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td colspan="3"><div align="center">
                                            <input name="salvar condicao" type="submit" id="salvar condicao" value="Salvar Condi&ccedil;&atilde;o">
                                        </div></td>
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
                                            <select name="variavel" id="variavel">
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
                                        <td colspan="2"><textarea name="expr" value="<%=expressao%>" cols="30" rows="3" id="expr"></textarea></td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td width="138" bgcolor="#333300"><div align="center" class="style34"><a href="editarexp.jsp">Editar Express&atilde;o</a></div></td>
                                        <td width="126" bgcolor="#333300"><div align="center" class="style34"><a href="editarexp.jsp">Validar Express&atilde;o </a></div></td>
                                    </tr>
                                    <tr>
                                        <td colspan="3">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td colspan="3"><div align="center">
                                            <input name="salvar condicao" type="submit" id="salvar condicao" value="Salvar Condi&ccedil;&atilde;o">
                                        </div></td>
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