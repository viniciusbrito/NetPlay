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

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
</head>

<body onLoad="MM_preloadImages('imagens/criar2.JPG','imagens/altmov2.JPG','imagens/incvar2.JPG','imagens/incvar.JPG','imagens/altpos2.JPG','imagens/cor2.JPG','imagens/forma12.JPG')">

<div align="center">
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="591" colspan="4"><div align="center" class="style38">Selecione o tipo de A&ccedil;&atilde;o a ser criada: </div></td>
    </tr>
    <tr>
      <td colspan="4">&nbsp;</td>
    </tr>
    <tr>
<td colspan="4"><div align="center">
  <table border="1" cellspacing="1" cellpadding="1">
    <tr bgcolor="#BBD0BF">
      <td width="125"><div align="center"><a href="acao_criar.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('CriarElem','','imagens/criar2.JPG',1)"><img src="imagens/criar.JPG" alt="Criar Elemento" name="CriarElem" width="50" height="50" border="0"></a></div></td>
      <td><div align="center"><a href="acao_destruir.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('DestruirElem','','imagens/destruir2.JPG',0)"><img src="imagens/destruir.JPG" alt="Destruir Elemento" name="DestruirElem" width="50" height="50" border="0"></a></div></td>
      <td width="125"><div align="center"><a href="acao_altcor.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('AltCor','','imagens/cor2.JPG',1)"><img src="imagens/cor1.JPG" alt="Alterar Cor" name="AltCor" width="50" height="50" border="0"></a></div></td>
      <td width="125"><div align="center"><a href="acao_altforma.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('AltFor','','imagens/forma12.JPG',1)"><img src="imagens/forma1.JPG" alt="Alterar Forma" name="AltFor" width="50" height="50" border="0"></a></div></td>
    </tr>
    <tr bgcolor="#BBD0BF">
      <td><div align="center">Criar Elemento </div></td>
      <td><div align="center">Destruir Elemento </div></td>
      <td><div align="center">Alterar Cor </div></td>
      <td><div align="center">Alterar Forma </div></td>
    </tr>
    <tr bgcolor="#BBD0BF">
      <td><div align="center"><a href="acao_incvalvar.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('IncValVar','','imagens/incvar2.JPG',1)"><img src="imagens/incvar.JPG" alt="Incrementar Valor de Variável" name="IncValVar" width="50" height="50" border="0"></a></div></td>
      <td><div align="center"><a href="acao_recvalvar.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('RecValVar','','imagens/incvar.JPG',1)"><img src="imagens/incvar2.JPG" alt="Recuperar Valor de Variável" name="RecValVar" width="50" height="50" border="0"></a></div></td>
      <td><div align="center"><a href="acao_altpos.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('AltPos1','','imagens/altpos2.JPG',1)"><img src="imagens/altpos.JPG" alt="Alterar Posicionamento" name="AltPos1" width="50" height="50" border="0" id="AltPos1"></a></div></td>
      <td><div align="center"><a href="acao_altmov.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('AltMov1','','imagens/altmov2.JPG',1)"><img src="imagens/altmov.JPG" alt="Alterar Movimento" name="AltMov1" width="50" height="50" border="0" id="AltMov1"></a></div></td>      
    </tr>
    <tr bgcolor="#BBD0BF">
      <td><div align="center">Incrementar / Decrementar Vari&aacute;vel </div></td>
      <td><div align="center">Recuperar Valor de Vari&aacute;vel </div></td>
      <td><div align="center">Alterar Posi&ccedil;&atilde;o </div></td>
      <td><div align="center">Alterar Movimento </div></td>
    </tr>
     <tr bgcolor="#BBD0BF">
        <td><div align="center"><a href="acao_mostrar_agente.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('AltMov1','','imagens/altmov2.JPG',1)"><img src="imagens/altmov.JPG" alt="Mostrar Elemento" name="MostEle" width="50" height="50" border="0" id="MostEle1"></a></div></td>
        <td><div align="center"><a href="acao_ocultar_agente.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('AltMov1','','imagens/altmov2.JPG',1)"><img src="imagens/altmov.JPG" alt="Ocultar Elemento" name="OcultEle" width="50" height="50" border="0" id="OcultEle1"></a></div></td>
        <td><div align="center"><a href="acao_pararmov.jsp"><img src="imagens/parar.JPG" alt="Parar Movimento" name="AltParar" width="50" height="50" border="0" id="AltParar"></a></div></td>
        <td><div align="center"><a href="acao_tamanho.jsp" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('AltTam1','','imagens/altmov2.JPG',1)"><img src="imagens/altmov.JPG" alt="Alterar Tamanho" name="AltTam1" width="50" height="50" border="0" id="AltTam1"></a></div></td>      
    </tr>
    <tr bgcolor="#BBD0BF">
        <td><div align="center">Mostrar Elemento </div></td>
        <td><div align="center">Ocultar Elemento </div></td>
        <td><div align="center">Parar Movimento </div></td>
        <td><div align="center">Alterar Tamanho </div></td>
    </tr>
  </table>
</div></td>
    </tr>
    </tr>
  </table>
</div>
</body>
</html>