<%@ page contentType="text/html; charset=iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%  
    String id = request.getParameter("id");
    String gerarArquivo = "controlador.Exporta"+
            "?metodo=gerarArquivo&id=" + id;
%>

<html>
<head>
<title>Executando o Modelo no NetLogo</title>
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
	color: #006600;
	font-weight: bold;
}
a:visited {
	color: #006600;
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
        color: #FFFFFF;
        font-weight: bold;
}
.style56 {color: #FFFFFF}
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
          <table width="100%" height="733" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
            <tr>
              <td height="66" valign="top" align="left">
<p>Est&aacute; p&aacute;gina foi gerada com o NetLogo 3.1.2 ( <a target="_blank"  href="http://ccl.northwestern.edu/netlogo/">P&aacute;gina oficial - NetLogo</a> ).                </p>
                <p>&Eacute; necess&aacute;rio Java 1.4.1 ou maior. N&atilde;o executar&aacute; em Windows 95 e MAC OS 8 e 9. Usu&aacute;rios MAC t&ecirc;m que ter OS X 10.2.6 ou maior e usar um browser que suporte Java 1.4. Para outros sistemas operacionais obter a &uacute;ltima vers&atildeo do Java plugin em <a target="_blank" href="http://java.sun.com/getjava/download.html">Sun - Java</a>.</p>
			  </td>
            </tr>
            <tr>
              <td width="100%" height="100%" valign="top" align="center">
                <p>
                  <applet code="org.nlogo.window.Applet"
        archive="NetLogoLite.jar"
        width="975" height="673">
                    <param name="DefaultModel"
       value="<%= gerarArquivo%>">
                  </applet>
                </p>
                
                
              <p>&nbsp;</p></td>
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
