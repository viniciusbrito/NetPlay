<%@ page contentType="text/html; charset=iso-8859-1"  language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>NetPlay</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
body,td,th {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #990000;
}
a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #990000;
	font-weight: bold;
}
a:visited {
	color: #FFFFFF;
	text-decoration: none;
}
a:hover {
	color: #FF1A1A;
	text-decoration: none;
}
a:active {
	color: #FFFFFF;
	text-decoration: none;
}
.style2 {font-family: "Abadi MT Condensed Extra Bold"}
body {
	background-color: #FFFFFF;
}
.style5 {
	color: #333300;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
}
.style7 {color: #990000; font-family: Arial, Helvetica, sans-serif; font-weight: bold; }
.style8 {color: #990000}
.style9 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
}
.style10 {color: #FFFFFF}
.style11 {font-family: Arial, Helvetica, sans-serif; font-size: 14px; color: #FFFFFF; }
a:link {
	color: #FFFFFF;
	text-decoration: none;
}
-->
</style></head>

<body>
<div align="center">
  <table width="110" height="90" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
    <tr>
      <td height="45" colspan="2"><div align="center" class="style2 style5">
        <div align="center" class="style8">Tem certeza que deseja excluir essa conta? </div>
      </div>        
      <div align="center"></div></td>
    </tr>
    <tr>
      <td width="60" height="25" bgcolor="#FFFFFF"><div align="center" class="style2 style5 style8">
        <table width="50" height="22" border="0" cellpadding="0" cellspacing="0" bgcolor="#990000">
          <tr>
            <td><div align="center" class="style9 style10"><a href="controlador.ControladorWeb?metodo=excluirUsuario" target="_parent">SIM</a></div></td>
          </tr>
        </table>
      </div></td>
      <td width="60" height="25" bgcolor="#FFFFFF"><div align="center" class="style7">
        <table width="50" height="22" border="0" cellpadding="0" cellspacing="0" bgcolor="#990000">
          <tr>
            <td><div align="center" class="style11"><a href="verde.htm">N&Atilde;O</a></div></td>
          </tr>
        </table>
      </div></td>
    </tr>
  </table>
</div>
</body>
</html>
