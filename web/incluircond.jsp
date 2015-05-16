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
%>

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
.style40 {color: #DCE7DE; font-size: 11px; }
-->
</style>
<script type="text/JavaScript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
</head>

<body onLoad="MM_preloadImages('imagens/perc2.JPG','imagens/pos2.JPG','imagens/corag2.JPG','imagens/var2.JPG')">
<div align="center">
  <table width="300" border="0">
    <tr>
      <td><div align="center" class="style38">Escolha o Tipo de Condi&ccedil;&atilde;o </div></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><table width="300" border="0">
        <tr>
          <td width="125"><div align="center"><a href="condperc.jsp?idComposicao=<%=request.getParameter("idComposicao")%>" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Percepcao','','imagens/perc2.JPG',1)"><img src="imagens/perc.JPG" alt="Percepcao" name="Percepcao" width="50" height="50" border="0"></a></div></td>
          <td width="125"><div align="center"><a href="condpos.jsp?idComposicao=<%=request.getParameter("idComposicao")%>" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Posicionamento','','imagens/pos2.JPG',1)"><img src="imagens/pos.JPG" alt="Posicionamento" name="Posicionamento" width="50" height="50" border="0"></a></div></td>
          <td width="125"><div align="center"><a href="condcorag.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('CorAgente','','imagens/corag2.JPG',1)"><img src="imagens/corag.JPG" alt="Cor do Agente" name="CorAgente" width="50" height="50" border="0"></a></div></td>
        </tr>
        <tr>
          <td><div align="center">Percep&ccedil;&atilde;o </div></td>
          <td><div align="center">Posicionamento</div></td>
          <td><div align="center">Cor do Agente </div></td>
        </tr>
        <tr>
            <td width="125"><div align="center"><a href="condtamanho.jsp?idComposicao=<%=request.getParameter("idComposicao")%>" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('CorAgente','','imagens/corag2.JPG',1)"><img src="imagens/corag.JPG" alt="Tamanho do Agente" name="TamAgente" width="50" height="50" border="0"></a></div></td>
            <td><div align="center"><a href="condvalvar.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('ValorVariavel','','imagens/var2.JPG',1)"><img src="imagens/valvar.JPG" alt="Valor de Variavel" name="ValorVariavel" width="50" height="50" border="0"></a></div></td>
            <td><div align="center"><a href="condcmpvar.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('CmpVariavel','','imagens/var2.JPG',1)"><img src="imagens/cmpvar.JPG" alt="Comparacaoo de Vari�veis" name="CmpVariavel" width="50" height="50" border="0"></a></div></td>
        </tr>
        <tr>
            <td><div align="center">Tamanho do Agente </div></td>          
            <td><div align="center">Valor de Vari&aacute;vel </div></td>          
            <td><div align="center">Compara&ccedil;&atilde;o de Vari&aacute;veis </div></td>
        </tr>
      </table></td>
    </tr>
  </table>
</div>
</body>
</html>
