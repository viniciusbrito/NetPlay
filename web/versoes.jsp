<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" import="cdp.*" errorPage="" %>
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
	font-size: 11px;
	color: #333300;
	font-weight: bold;
}
.style33 {	font-family: "Futura Md BT";
	font-size: 50px;
	color: #FFFFFF;
}
.style34 {color: #DCE7DE}
.style37 {color: #FFFFFF}
a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #DCE7DE;
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
.style39 {font-size: 16px}
h1,h2,h3,h4,h5,h6 {
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
}
h1 {
	font-size: 1mm;
}
h2 {
	font-size: 2mm;
}
.style5 {color: #FF0000}
.style42 {font-size: 14px}
-->
</style>
</head>

<body>
<table width="100%" height="290" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#333300">
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top"><div align="center">
        <table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="227"><div align="center" class="style33">NET<span class="style37">PLAY</span></div></td>
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
          <table width="100%" height="293" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
              <tr>
                <td width="219" height="290" rowspan="2" valign="top"><div align="center">
                    <table width="201" height="165%" border="0" cellpadding="0" cellspacing="0" bordercolor="#333300" bgcolor="#BBD0BF">
                      <tr>
                        <td width="198" valign="top"><table width="180" height="106%" border="5" bordercolor="#333300">
                          <tr>
                            <th height="302" valign="top" scope="row"><table width="180" height="86" border="0" cellspacing="0">
                                <tr>
                                  <th scope="row">&nbsp;</th>
                                </tr>
                                <tr>
                                  <th scope="row"><table width="180" border="5" bordercolor="#333300">
                                      <tr>
                                        <th scope="row"><span class="style39">PESQUISA MODELO </span></th>
                                      </tr>
                                  </table></th>
                                </tr>
                                <tr>
                                  <th scope="row">&nbsp;</th>
                                </tr>
                                <tr>
                                  <th scope="row"><span class="style42">Pesquisar modelo </span></th>
                                </tr>
                                <tr>
                                  <th scope="row"><span class="style42">come&ccedil;ando com:</span></th>
                                </tr>
                                <tr>
                                  <th height="16" scope="row"><form action="controlador.ControladorWeb" method="post" name="pesquisa" target="versoes" id="pesquisa">
                                      <table width="180" border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                          <th scope="row"><input name="O campo" type="text" id="O campo" size="25"></th>
                                        </tr>
                                        <tr>
                                          <th scope="row">&nbsp;</th>
                                        </tr>
                                        <tr>
                                          <th scope="row"><input name="pesquisar" type="submit" id="pesquisar4" value="Pesquisar"></th>
                                        </tr>
                                        <tr>
                                          <th scope="row"><input name="metodo" type="hidden" value="versoesModelo">
                                              <input name="acao" type="hidden" value="pesquisar"></th>
                                        </tr>
                                      </table>
                                  </form></th>
                                </tr>
                            </table></th>
                          </tr>
                        </table></td>
                        <td width="10" valign="top" bgcolor="#333300">&nbsp;</td>
                      </tr>
                  </table>
                </div></td>
                <td width="531" height="14" colspan="3" align="center" valign="middle">&nbsp;</td>
              </tr>
            <tr>
              <td height="247" colspan="3" align="center" valign="middle"><table width="520" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <th colspan="3" scope="row">&nbsp;</th>
                </tr>
                <tr>
                  <th colspan="3" scope="row"><span class="style39">CONTROLE DE VERS&Otilde;ES DO MODELO </span></th>
                </tr>
                <tr>
                  <th colspan="3" scope="row">&nbsp;</th>
                </tr>
                <tr>
                  <th height="10" colspan="3" scope="row"><iframe src="telaverde.htm" name="versoes" width="570" height="350" scrolling="auto"></iframe>
              &nbsp;</th>
                </tr>
                <tr>
                  <th width="255" scope="row">&nbsp;</th>
                  <th width="140" scope="row"><table width="140" height="30" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                    <tr>
                      <td width="133"><div align="center"><a href="menu.jsp" target="_parent">Voltar Menu Principal </a></div></td>
                    </tr>
                  </table></th>
                  <th width="125" scope="row">&nbsp;</th>
                </tr>
                <tr>
                  <th colspan="3" scope="row">&nbsp;</th>
                </tr>
              </table>
</td>
            </tr>
          </table>
      </div></td>
    <td height="378" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td height="16" valign="top">&nbsp;</td>
  </tr>
</table>
</body>
</html>
