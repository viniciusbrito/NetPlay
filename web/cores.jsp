<%@ page contentType="text/html; charset=iso-8859-1"  language="java" import="java.sql.*" errorPage="" %>
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
.style5 {color: #FF0000}
.style2 {color: #DCE7DE}
-->
</style>
</head>

<body>
<div align="center">
  <table width="400" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td><div align="center" class="style1">LISTA DE CORES </div></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><div align="center">
        <table width="400" height="380" border="1" bgcolor="#BBD0BF">
          <tr>
            <td><div align="center">
              <table width="292" border="1" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="97"><div align="left">Amarelo</div></td>
                  <td width="189"><img src="cores/AMARELO.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Azul</div></td>
                  <td height="10"><img src="cores/AZUL.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Azul Claro</div></td>
                  <td height="10"><img src="cores/AZULCLARO.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Branco</div></td>
                  <td height="10"><img src="cores/BRANCO.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Ciano</div></td>
                  <td height="10"><img src="cores/CIANO.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Cinza</div></td>
                  <td height="10"><img src="cores/CINZA.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Laranja</div></td>
                  <td height="10"><img src="cores/LARANJA.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Magenta</div></td>
                  <td height="10"><img src="cores/MAGENTA2.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Marrom</div></td>
                  <td height="10"><img src="cores/MARROM.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Preto</div></td>
                  <td height="10"><img src="cores/PRETO.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Rosa</div></td>
                  <td height="10"><img src="cores/ROSA.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Verde</div></td>
                  <td height="10"><img src="cores/VERDE.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Verde Lim&atilde;o</div></td>
                  <td height="10"><img src="cores/VERDELIMAO.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Verde Turquesa</div></td>
                  <td height="10"><img src="cores/VERDETURQUESA.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Vermelho</div></td>
                  <td height="10"><img src="cores/VERMELHO.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10"><div align="left">Violeta</div></td>
                  <td height="10"><img src="cores/VIOLETA.jpg" width="189" height="20"></td>
                </tr>
                <tr>
                  <td height="10" colspan="2">&nbsp;</td>
                  </tr>
                <tr>
                  <td height="10" colspan="2"><div align="center">
                    <input name="Fechar" type="submit" id="Fechar" value="Fechar" onClick="javascript:window.close();">
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
