<%@ page contentType="text/html; charset=iso-8859-1"  language="java" 
import="java.sql.*" 
import="cgt.*" 
import="cdp.*" 
import="java.util.*"
import="utilitario.HibernateUtility"
import="org.hibernate.Session"
import="org.hibernate.Transaction"
import="org.hibernate.Query"
import="org.hibernate.SQLQuery"
import="org.hibernate.Criteria"
errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String status = "";
String nome = "";
String acao = "";
String tipoGrafico = "";
String nomeEixoX = "";
String nomeEixoY = "";
String xMinimo = "";
String xMaximo = "";
String yMinimo = "";
String yMaximo = "";
int id;
nome = request.getParameter("nome");
acao = request.getParameter("acao");
tipoGrafico = request.getParameter("tipoGrafico");
nomeEixoX = request.getParameter("nomeEixoX");
nomeEixoY = request.getParameter("nomeEixoY");
xMinimo = request.getParameter("xMinimo");
xMaximo = request.getParameter("xMaximo");
yMinimo = request.getParameter("yMinimo");
yMaximo = request.getParameter("yMaximo");
id = Integer.parseInt(request.getParameter("id"));

status = request.getParameter("status");
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
.style3 {color: #BBD0BF}
.style2 {color: #DCE7DE}
.style5 {color: #FF0000}
-->
</style>
</head>
<body>
<div align="center">
  <table width="525" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><div align="center" class="style1">
        <% if(acao.compareTo("Excluir")==0){%>
Exclus&atilde;o Gr&aacute;fico
<%} else { %>
Consulta Gr&aacute;fico
<%}%>
      </div></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><div align="center"></div></td>
    </tr>
    <tr>
      <td><div align="center">
        <table width="525" border="1" bgcolor="#BBD0BF">
          <tr>
            <th scope="row"><table width="400" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <th height="10" scope="row">&nbsp;</th>
                </tr>
                <tr>
                  <th scope="row"><table width="400" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td>
                            <table width="400" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td><table width="400" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                    <tr>
                                      <td width="160"> &nbsp; Nome do Gr&aacute;fico </td>
                                      <td width="240"><input name="Nome" type="text" disabled id="nome" value="<%=nome%>" size="40"></td>
                                    </tr>
                                    <tr>
                                      <td>&nbsp; Tipo do Gr&aacute;fico </td>
                                      <td><div align="left">
                                        <select name="tipo do grafico" id="select8" disabled>
                                          <option selected value= <%=tipoGrafico%>> <%=tipoGrafico%></option>
                                        </select>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td>&nbsp; Nome Eixo X </td>
                                      <td><div align="left">
                                        <input name="Eixo x" type="text" disabled id="Eixo x" value="<%=nomeEixoX%>" size="40">
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td>&nbsp; Nome Eixo Y </td>
                                      <td><div align="left">
                                        <input name="Eixo y" type="text" disabled id="Eixo y" value="<%=nomeEixoY%>" size="40">
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td>&nbsp; Coordenada X M&iacute;nima </td>
                                      <td><div align="left">
                                        <input name="X minimo" type="text" disabled id="X minimo" value="<%=xMinimo%>" size="40">
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td>&nbsp; Coordenada Y M&iacute;nima </td>
                                      <td><div align="left">
                                        <input name="Y minimo" type="text" disabled id="Y minimo" value="<%=yMinimo%>" size="40">
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td>&nbsp; Coordenada X M&aacute;xima</td>
                                      <td><div align="left">
                                        <input name="X maximo" type="text" disabled id="X maximo" value="<%=xMaximo%>" size="40">
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td>&nbsp; Coordenada Y M&aacute;xima</td>
                                      <td><div align="left">
                                        <input name="Y maximo" type="text" disabled id="Y maximo" value="<%=yMaximo%>" size="40">
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><div align="center">Elementos do Gr&aacute;fico </div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><table width="400" border="0" cellpadding="0" cellspacing="0">
                                          <tr>
                                            <th width="200" scope="row">Vari&aacute;vel </th>
                                            <td width="126">
                                              <div align="center">Cor </div></td>
                                            <td width="58">&nbsp;</td>
                                          </tr>
                                      </table></td>
                                    </tr>
                                    <tr> <% List cor; 
    				            cor = AplCadastrarPlot.variaveisDoPlot(id);
                                            String nomeVariavel;
					  for(int i=0; i<cor.size(); i++){
                                              CorVariavelPlot cvp = (CorVariavelPlot)cor.get(i);
                                              nomeVariavel = AplCadastrarVariavel.obterNomeVariavelPlot(cvp.getIdCorVariavelPlot().intValue());
                                              %>
                                     <td colspan="2">
                                        <table width="400" border="0" cellpadding="0" cellspacing="0">
                                          <tr>                                   
                                           <td>                                          
                                          </tr>
                                          <tr>
                                            <th width="20" scope="row"><span class="style5">
                                            </span></th>
                                            <td width="187"><div align="left">
                                          <input name="variavel" type="text" disabled id="Variavel" value="<%=nomeVariavel%>" size="25">
                                           </div></td>
                                            <td width="193"><div align="left">
                                              <input name="cor" type="text" disabled id="Cor" value="<%=cvp.getCor()%>" size="25">
                                            </div>                                          </td>
                                          </tr>
                                      </table></td>
                                    </tr><%}%>
                                    <tr>
                                      <td colspan="2">&nbsp;</td>
                                    </tr>
                                </table></td>
                              </tr>
                              <tr>
                                <td height="10"><div align="center">
                                </div></td>
                              </tr>
                              <tr>
                                <td height="10"><div align="center">
			                    <% if(acao.compareTo("Excluir")==0){%>
                                  <table width="80" height="25" border="1" cellpadding="0" cellspacing="0" bgcolor="#333300">
                                    <tr>
                                      <td><div align="center" class="style2"> </div>
                                          <div align="center" class="style2"><a href="controlador.ControladorWeb?metodo=cadastroPlot&acao=excluirPlot&nome=<%=nome%>">Excluir</a></div></td>
                                    </tr>
                                  </table>
                                </div></td>
								<%} else { %>
							  <td width="400"><div align="right">
        		              </div></td>
     						  <%}%>
                              </tr>
                            </table>
                        </td>
                      </tr>
                  </table></th>
                </tr>
                <tr>
                  <th height="10" scope="row">&nbsp;</th>
                </tr>
            </table></th>
          </tr>
        </table>
      </div></td>
    </tr>
  </table>
</div>
</body>
</html>
