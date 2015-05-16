<%@ page contentType="text/html; charset=iso-8859-1" language="java"
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

<html>
    <head>
        <title>NETPLAY</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
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
		//-->
		</script>
    </head>

    <body>
    <div align="center">
            <p>QUADRO DE COMPOSI&Ccedil;&Otilde;ES</p>
        <table border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td><table width="439" border="1" align="center" bgcolor="#BBD0BF">
                        <tr>
                            <th width="300" scope="nome">Nome</th>
                            <th width="55" scope="op">Operador L&oacute;gico </th>
                            <th width="62" scope="conds">Condi&ccedil;&otilde;es &amp; A&ccedil;&otilde;es </th>
                        </tr>
                        <!-- Exibir composicoes do modelo e incluir nova -->
                        <% List comps;
                            Modelo m = (Modelo)session.getAttribute("modelo");
                            comps = AplCadastrarComposicao.composicoesDoModelo(m.getIdModelo().intValue());
                        %>
                        <%for (int i=0; i< comps.size(); i++){ %>
                        <!-- Inicio das linhas das composicoes antigas -->
                        <tr>
                            <td><input name="nome" type="text" class="style38" id="nomeC" size="50" maxlength="30" value=<%=comps[i].nome%> disabled></td>
                            <td><div align="center">
                                <select name="op">
                                    <option selected=<%=comps[i].operador%>> <%=comps[i].operador%></option>
                                </select>
                            </div></td>
                            <td><div align="center"><a href="comp_acoes_conds.jsp" class="style41" <% nomeComp = %>>Incluir</a></div></td>
                        </tr>
                        <!-- Fim das linhas das composições antigas -->
                        <%}%>
                    </table></td>
                </tr>
            </table>

        </div>
    </body>
</html>
