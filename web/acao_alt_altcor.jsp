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
    String status = request.getParameter("status");         
    if(status == null)
        status = "";
    
    String id = request.getParameter("id");
    AlterarCor acao = (AlterarCor) AplCadastrarAcao.obterAcao(Integer.parseInt(id));
    int idAgente = acao.getAgente().getIdAgente().intValue();
    String cor = acao.getNovaCor();
%>

<html>
    <head>
        <title>NETPLAY</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            -->
        </style>
        <script language="JavaScript" type="text/JavaScript">
            <!--

            function MM_openBrWindow(theURL,winName,features) { //v2.0
            window.open(theURL,winName,features);
            }
            //-->
        </script>
    </head>

    <body>

        <div align="center">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="4"><div align="center" class="style38">Alterar Cor </div></td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
 
                </tr>
                <tr>
                    <td height="15" colspan="4"><div align="center">
                        <table width="500" border="1" bgcolor="#BBD0BF">
                            <tr>
                                <td height="103"><div align="center">
                                    <form action="controlador.ControladorWebCCA" method="post" name="acAltMov" id="acAltMov">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Agente</div></td>
                                                <td colspan="2"><div align="left">
                                                    <% List agentes;
                                                        Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                        agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
                                                    %>
                                                    <select name="agente" id="agente">
                                                        <%for(int i=0; i<agentes.size(); i++){
                                                                Agente agente = (Agente)agentes.get(i);
                                                                if(idAgente == agente.getIdAgente().intValue()) { %>
                                                                    <option selected value=<%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                                <% } else { %>
                                                                    <option value=<%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                        <%}}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                <div align="left"> Nova Cor </div></td>
                                                <td width="125"><div align="left">
                                                    <select name="cor" id="cor" onchange="imagemCor.src='cores/' + this.value + '.jpg'">
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
                                                                            <%if(cor.compareTo("MAGENTA2")==0){%>
											<option  selected value="<%=cor%>">MAGENTA</option>
                                                                            <%teste = 1;
									    }%>
                                                                            <%if(teste==0){%>
                                                                            <option selected value= <%=cor%>> <%=cor%></option>
                                                                            <%}%>
                                                                            <option value="AMARELO">AMARELO</option>
                                                                            <option value="AZUL">AZUL</option>
                                                                            <option value="AZULCLARO">AZUL CLARO</option>
                                                                            <option value="BRANCO">BRANCO</option>
                                                                            <option value="CIANO">CIANO</option>                                            
                                                                            <option value="CINZA">CINZA</option>
                                                                            <option value="LARANJA">LARANJA</option>
                                                                            <option value="MAGENTA2">MAGENTA</option>
                                                                            <option value="MARROM">MARROM</option>
                                                                            <option value="PRETO">PRETO</option>
                                                                            <option value="ROSA">ROSA</option>
                                                                            <option value="VERDE">VERDE</option>
                                                                            <option value="VERDELIMAO">VERDE LIMAO</option>
                                                                            <option value="VERDETURQUESA">VERDE TURQUESA</option>
                                                                            <option value="VERMELHO">VERMELHO</option>
                                                                            <option value="VIOLETA">VIOLETA</option>
                                                                            <%}else{%>
                                                                            <option value="AMARELO">AMARELO</option>
                                                                            <option value="AZUL">AZUL</option>
                                                                            <option value="AZULCLARO">AZUL CLARO</option>
                                                                            <option value="BRANCO">BRANCO</option>
                                                                            <option value="CIANO">CIANO</option>                                            
                                                                            <option value="CINZA">CINZA</option>
                                                                            <option value="LARANJA">LARANJA</option>
                                                                            <option value="MAGENTA2">MAGENTA</option>
                                                                            <option value="MARROM">MARROM</option>
                                                                            <option value="PRETO">PRETO</option>
                                                                            <option value="ROSA">ROSA</option>
                                                                            <option value="VERDE">VERDE</option>
                                                                            <option value="VERDELIMAO">VERDE LIMAO</option>
                                                                            <option value="VERDETURQUESA">VERDE TURQUESA</option>
                                                                            <option value="VERMELHO">VERMELHO</option>
                                                                            <option value="VIOLETA">VIOLETA</option>
                                                                            <%}%>
                                                                        </select>
                                                                    </div></td>
                                                                    <% if (cor.equals("")) { %>
                                                                         <td align="center" width="121"><img name="imagemCor" src="cores/AMARELO.jpg" align="center" width="70" height="20"/></td>
                                                                    <% } else { %>
                                                                        <td width="121"><img name="imagemCor" src="<%="cores/" + cor + ".jpg"%>" align="center" width="70" height="20"/></td>
                                                                    <% } %>
                                            </tr>
                                            <tr>
                                                <td colspan="3">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="3"><div align="center">
                                                    <input name="salvar condicao" type="submit" id="salvar condicao" value="Salvar Ação">
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td><div align="center">
                                                    <input name="id" type="hidden" value="<%=id%>">
                                                    <input name="metodo" type="hidden" value="cadastroAcao">
                                                    <input name="acao" type="hidden" value="alterar">
                                                    <input name="TipoAcao" type="hidden" id="TipoAcao" value="AltCor">
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td><div align="center"><span class="style5"><%= status %></span></div></td>
                                            </tr>
                                        </table>
                                    </form>
                                </div></td>
                            </tr>
                        </table>
                    </div></td>
                </tr>
            </table>
        </div>
    </body>
</html>
