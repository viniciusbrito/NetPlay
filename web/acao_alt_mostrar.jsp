<%-- 
    Document   : acao_alt_mostrar
    Created on : 27/11/2014, 03:41:38
    Author     : vinicius
--%>

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
    String status = "";

    status = request.getParameter("status");
    if(status == null)
        status = " ";
    
    String id = request.getParameter("id");
    Acao acao = AplCadastrarAcao.obterAcao(Integer.parseInt(id));
    int idAgente = ((MostrarAgente) acao).getAgente().getIdAgente().intValue();
    
    List agentes;
    boolean temAgMovel = false;
    Modelo m1 = (Modelo)session.getAttribute("modelo");
    agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
    
    if(agentes.size() == 0) { %>
        <p>N&atilde;o &eacute; poss&iacute;vel criar essa a&ccedil;&atilde;o devido a inexist&eacute;ncia de agentes m&oacute;veis nesse modelo</p>
    <% } else {
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
        </script>
    </head>

    <body>

        <div align="center">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="4"><div align="center" class="style38">Mostrar Elemento </div></td>
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
                                    <form action="controlador.ControladorWebCCA" method="post" name="acMostrar" id="acMostrar">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Agente</div></td>
                                                <td width="200"><div align="left">
                                                    <select name="agente" id="agente">
                                                        <%for(int i=0; i<agentes.size(); i++){
                                                            Agente agente = (Agente)agentes.get(i);
                                                            if(idAgente == agente.getIdAgente().intValue()) {%>
                                                                <option selected value= <%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                            <% } else { %>
                                                                <option value= <%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                        <%}}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="2"><div align="center">
                                                    <input name="salvar condicao" type="submit" id="salvar condicao" value="Salvar Ação">
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td><div align="center">
                                                    <input name="id" type="hidden" value="<%=id%>">
                                                    <input name="metodo" type="hidden" value="cadastroAcao">
                                                    <input name="acao" type="hidden" value="alterar">
                                                    <input name="TipoAcao" type="hidden" id="TipoAcao" value="Mostrar">
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
<%}%>
