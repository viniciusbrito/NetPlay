<%@ page contentType="text/html; charset=iso-8859-1"  language="java" import="java.sql.*" import="cdp.*"  errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String status = "";

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
	color: #333300;
	font-weight: bold;
	font-size: 11px;
}
.style40 {
	font-family: "Futura Md BT";
	font-size: 50px;
	color: #FFFFFF;
}
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
a:hover {
	color: #A8C4AD;
	text-decoration: none;
}
a:active {
	color: #DCE7DE;
	text-decoration: none;
}
a:link {
	text-decoration: none;
}
.style45 {
	font-family: "Times New Roman", Times, serif;
	font-style: italic;
	font-size: 14px;
}
.style46 {font-size: 14px}
.style44 {color: #DCE7DE; font-size: 14px; }
.style47 {color: #DCE7DE}
.style53 {
	color: #990000;
	font-size: 16px;
}
.style54 {font-size: 20px}
.style55 {color: #990000; font-size: 20px; }
.style5 {color: #FF0000}
-->
</style>
</head>
<body>
<table width="100%" height="401" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#333300">
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top"><div align="center">
        <table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="227"><div align="center" class="style40">NET<span class="style37">PLAY</span></div></td>
            <td width="515">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2"><div align="center">
                <table width="100%" height="5" border="0" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                  <tr>
                    <td>&nbsp;</td>
                  </tr>
                </table>
            </div></td>
          </tr>
        </table>
      </div>
        <div align="center">
          <table width="750" border="0" cellpadding="0" cellspacing="0" bgcolor="#333300">
            <tr>
              <td>&nbsp;</td>
            </tr>
          </table>
          <table width="100%" height="308" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
            <tr>
              <td width="160" height="308" valign="top"><div align="center">
                  <table width="160" height="308" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td valign="top"><div align="center"></div>
                          <div align="center">
                            <table width="160" height="350" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
                              <tr>
                                <td height="15" bgcolor="#FFFFFF">&nbsp;</td>
                              </tr>
                              <tr>
                                <td height="61" bgcolor="#FFFFFF"><table width="160" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td><div align="center" class="style53 style54"><u>ALTERA&Ccedil;&Otilde;ES</u></div></td>
                                    </tr>
                                    <tr>
                                      <td><div align="center" class="style55"><u>DA CONTA</u> </div></td>
                                    </tr>
                                </table></td>
                              </tr>
                              <tr>
                                <td height="50"><div align="center">
                                    <table width="150" height="40" border="1" cellpadding="0" cellspacing="0" bgcolor="#000000">
                                      <tr>
                                        <td><div align="center"><a href="perfil.jsp">Alterar Perfil de Usu&aacute;rio</a></div></td>
                                      </tr>
                                    </table>
                                </div></td>
                              </tr>
                              <tr>
                                <td height="50"><div align="center">
                                    <table width="150" height="40" border="1" cellpadding="0" cellspacing="0" bgcolor="#000000">
                                      <tr>
                                        <td><div align="center" class="style47"><a href="excluirconta.htm" target="excluir">Excluir Conta</a></div></td>
                                      </tr>
                                    </table>
                                </div></td>
                              </tr>
                              <tr>
                                <td height="130" align="center" bgcolor="#FFFFFF"><iframe src="" name="excluir" width="140" height="130" scrolling="auto"></iframe>
&nbsp;
                              <div align="center"></div>
                              <div align="center"></div>
                              <div align="center"></div></td>
                              </tr>
                            </table>
                        </div></td>
                      <td width="15" bgcolor="#333300">&nbsp;</td>
                    </tr>
                  </table>
              </div></td>
              <td width="590" valign="top"><div align="center">
                  <table width="100%" height="308" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td colspan="5">&nbsp;</td>
                    </tr>
                    <tr>
                      <td colspan="5"><div align="center" class="style46">DESCRITOR DE PROBLEMAS </div></td>
                    </tr>
                    <tr>
                      <td colspan="5">&nbsp;</td>
                    </tr>
                    <tr>
                      <% Usuario u = (Usuario)session.getAttribute("usuario");
					     session.removeAttribute("modelo"); %>
                      <td colspan="5"><div align="center" class="style45">
                        <% if (u.getSexo().compareTo("M") == 0) {%>
Bem Vindo
<% }
					else { %>
Bem Vinda
<% } %>
                        <span class="style45">"<%= u.getNome() %>"</span></div></td>
                    </tr>
                    <tr>
                      <td colspan="5">&nbsp;</td>
                    </tr>
                    <tr>
                      <td height="150" colspan="5"><div align="center">
                          <table width="600" height="40" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                            <tr>
                              <td width="120"><div align="center"><span class="style44"><a href="menu2_inclusaomodelo.jsp">Incluir Modelo</a></span></div></td>
                              <td width="120"><div align="center"><span class="style44"><a href="controlador.ControladorWeb?metodo=pesquisaModelo&acao=alteracao">Alterar Modelo</a></span></div></td>
                              <td width="120"><div align="center"><span class="style44"><a href="controlador.ControladorWeb?metodo=pesquisaModelo&acao=consultamodelos">Consultar Modelo </a></span></div></td>
                              <td width="120"><div align="center"><span class="style44"><a href="controlador.ControladorWeb?metodo=pesquisaModelo&acao=exclusao">Excluir Modelo </a></span></div></td>
                              <td width="120"><div align="center"><span class="style44"><a href="versoes.jsp">Consultar Vers&otilde;es do Modelo</a></span> </div></td>
                            </tr>
                          </table>
                      </div></td>
                    </tr>
                    <tr>
                      <td width="152" height="80"><div align="center">
                        </div></td>
                      <td width="297"><div align="center"><span class="style5"><%= status %></span></div></td>
                      <td width="75">&nbsp;</td>
                      <td width="61"><div align="center">
                          <table width="60" height="30" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td bgcolor="#333300"><div align="center"><a href="controlador.ControladorWeb?metodo=sair">sair</a></div></td>
                            </tr>
                          </table>
                      </div></td>
                      <td width="15"><div align="right"></div>
                          <div align="center"></div>
                          <div align="justify"></div>
                          <div align="right"></div></td>
                    </tr>
                  </table>
              </div></td>
            </tr>
          </table>
      </div></td>
    <td height="386" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td height="15" valign="top">&nbsp;</td>
  </tr>
</table>
</body>
</html>
