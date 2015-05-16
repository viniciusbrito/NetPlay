<%@ page contentType="text/html; charset=iso-8859-1"  language="java" import="java.sql.*" import="cdp.*" errorPage="" %>
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
	font-size: 11px;
	color: #333300;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
}
body {
	background-color: #FFFFFF;
}
a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #333300;
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
	color: #DCE7DE;
	font-weight: bold;
}
h1 {
	font-size: 0px;
}
h2 {
	font-size: 1px;
}
h3 {
	font-size: 2mm;
}
.style33 {
	font-family: "Futura Md BT";
	font-size: 50px;
	color: #FFFFFF;
}
.style37 {color: #FFFFFF}
.style42 {font-size: 20px}
.style34 {color: #DCE7DE}
.style5 {color: #FF0000}
-->
</style>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_popupMsg(msg) { //v1.0
  alert(msg);
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_validateForm() { //v4.0
  var i,p,q,nm,test,num,min,max,errors='',args=MM_validateForm.arguments;
  for (i=0; i<(args.length-2); i+=3) { test=args[i+2]; val=MM_findObj(args[i]);
    if (val) { nm=val.name; if ((val=val.value)!="") {
      if (test.indexOf('isEmail')!=-1) { p=val.indexOf('@');
        if (p<1 || p==(val.length-1)) errors+='- '+nm+' deve conter um endereço de email.\n';
      } else if (test!='R') { num = parseFloat(val);
        if (isNaN(val)) errors+='- '+nm+' deve conter um número.\n';
        if (test.indexOf('inRange') != -1) { p=test.indexOf(':');
          min=test.substring(8,p); max=test.substring(p+1);
          if (num<min || max<num) errors+='- '+nm+' deve conter um número entre '+min+' e '+max+'.\n';
    } } } else if (test.charAt(0) == 'R') errors += '- '+nm+' é obrigatório.\n'; }
  } if (errors) alert(' '+errors);
  document.MM_returnValue = (errors == '');    
}

function aceitaAscii (texto)
{
	var validos = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-?#';
	var tam = texto.length;
	var i;
	var caracter;
	
	for(i=0; i<tam; i++)
	{
		caracter = texto.charAt(i);
		if (validos.indexOf(caracter, 0) == -1)
		{
			alert('Não use acentos no nome do modelo.');
			document.modelo.nome.value = '';
		}
	}
}
//-->
</script>
</head>
<body>
<table width="100%" height="386" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#333300">
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
          <table width="100%" height="304" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
            <tr>
              <td width="180" height="304" valign="top"><div align="center">
                <table width="180" height="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#BBD0BF">
                  <tr>
                    <td width="9" rowspan="2" valign="top">&nbsp;</td>
                    <td width="160" height="60" valign="middle"><div align="center" class="style42"><u>NOVO MODELO </u></div></td>
                    <td width="9" rowspan="2" valign="top">&nbsp;</td>
                    <td width="2" rowspan="2" valign="top" bgcolor="#333300">&nbsp;</td>
                  </tr>
                  <tr>
                    <td valign="top"><table width="160" height="210" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                        <tr>
                          <td width="150" height="30"><div align="center" class="style34"><a href="#" onClick="MM_popupMsg('As inclus&otilde;es desses elementos s&oacute; ser&atilde;o permitidas ap&oacute;s o salvamento das informa&ccedil;&otilde;es do modelo ')">Cadastrar Agente</a> </div></td>
                        </tr>
                        <tr>
                          <td width="150" height="30"><div align="center"><span class="style34"><a href="#" onClick="MM_popupMsg('As inclus&otilde;es desses elementos s&oacute; ser&atilde;o permitidas ap&oacute;s o salvamento das informa&ccedil;&otilde;es do modelo ')">Cadastrar Vari&aacute;vel </a></span></div></td>
                        </tr>
                        <tr>
                          <td width="150" height="30"><div align="center"><span class="style34"><a href="#" onClick="MM_popupMsg('As inclus&otilde;es desses elementos s&oacute; ser&atilde;o permitidas ap&oacute;s o salvamento das informa&ccedil;&otilde;es do modelo ')">Cadastrar Monitor </a></span></div></td>
                        </tr>
                        <tr>
                          <td width="150" height="30"><div align="center" class="style34"><a href="#" onClick="MM_popupMsg('As inclus&otilde;es desses elementos s&oacute; s&atilde;o permitidas ap&oacute;s o salvamento das informa&ccedil;&otilde;es do modelo ')">Cadastrar Gr&aacute;fico</a> </div></td>
                        </tr>
                        <tr>
                          <td width="150" height="30"><div align="center"><span class="style34"><a href="#" onClick="MM_popupMsg('As inclus&otilde;es desses elementos s&oacute; ser&atilde;o permitidas ap&oacute;s o salvamento das informa&ccedil;&otilde;es do modelo ')">Cadastrar Texto </a></span></div></td>
                        </tr>
                        <tr>
                          <td width="150" height="30"><div align="center"><span class="style34"><a href="#" onClick="MM_popupMsg('As inclus&otilde;es desses elementos s&oacute; ser&atilde;o permitidas ap&oacute;s o salvamento das informa&ccedil;&otilde;es do modelo ')">Cadastrar Composi&ccedil;&atilde;o</a> </span></div></td>
                        </tr>
                        <tr>
                          <td width="150" height="30"><div align="center"><span class="style34"><a href="#" onClick="MM_popupMsg('Essa op&ccedil;&atilde;o s&oacute; ser&aacute; permitida ap&oacute;s o salvamento das informa&ccedil;&otilde;es do modelo ')">Exportar Modelo</a> </span></div></td>
                        </tr>
                    </table></td>
                  </tr>
                </table>
                </div></td>
              <td width="783" valign="middle"><div align="center">
                <table width="440" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <th colspan="4" scope="row"><table width="440" border="1">
                      <tr>
                        <th scope="row"><table width="440" border="0" cellpadding="0" cellspacing="0" bgcolor="#BBD0BF">
                          <tr>
                            <td><div align="center">
                                <form action="controlador.ControladorWeb" method="post" name="modelo" id="modelo" onSubmit="MM_validateForm('nome2','','R','descricao','','R'); return document.MM_returnValue">
                                  <table width="400" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td height="10"><div align="center"> </div></td>
                                    </tr>
                                    <tr>
                                      <td><table width="440" border="0" cellspacing="0" cellpadding="0">
                                          <tr>
                                            <td width="50">&nbsp;</td>
                                            <td width="340"><div align="center">
                                                <table width="340" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                                  <tr>
                                                    <td width="120"><div align="left">Nome</div></td>
                                                    <td width="220"><input name="nome" type="text" id="nome2" size="40" maxlength="30" onChange="javascript:aceitaAscii(nome.value)"></td>
                                                  </tr>
                                                  <tr>
                                                    <td><div align="left">Descri&ccedil;&atilde;o</div></td>
                                                    <td>
                                                      <div align="left">
                                                        <textarea name="descricao" cols="30" rows="3" id="descricao"></textarea>
                                                    </div></td>
                                                  </tr>
                                                  <tr>
                                                    <td height="30" colspan="2"><div align="center">
                                                        <input name="salvarModelo" type="submit" id="enviar5" value="Salvar Modelo">
                                                    </div></td>
                                                  </tr>
                                                </table>
                                            </div></td>
                                            <td width="50"><input name="metodo" type="hidden" value="cadastroModelo">
                                                <input name="acao" type="hidden" value="incluir"></td>
                                          </tr>
                                      </table></td>
                                    </tr>
                                  </table>
                                </form>
                            </div></td>
                          </tr>
                          <tr>
                            <td><div align="center">
                                <p class="style5"><%= status %></p>
                            </div></td>
                          </tr>
                          <tr>
                            <td><div align="center"></div></td>
                          </tr>
                          <tr>
                            <td><div align="center"></div></td>
                          </tr>
                          <tr>
                            <td><div align="center"> </div></td>
                          </tr>
                        </table></th>
                      </tr>
                    </table></th>
                  </tr>
                  <tr>
                    <th colspan="4" scope="row">&nbsp;</th>
                  </tr>
                  <tr>
                    <th width="240" scope="row"><table width="240" height="30" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                      <tr>
                        <td width="120"><div align="center" class="style34"><a href="#" onClick="MM_popupMsg('Essa op&ccedil;&atilde;o s&oacute; ser&aacute; permitida ap&oacute;s o salvamento das informa&ccedil;&otilde;es do modelo ')">Visualizar Arquivo</a> </div></td>
                        <td width="120"><div align="center" class="style34"><a href="#" onClick="MM_popupMsg('Essa op&ccedil;&atilde;o s&oacute; ser&aacute; permitida ap&oacute;s o salvamento das informa&ccedil;&otilde;es do modelo ')">Gerar Arquivo </a></div></td>
                      </tr>
                    </table></th>
                    <th width="50" scope="row">&nbsp;</th>
                    <th width="20" scope="row"><div align="right">
                      </div></th>
                    <th width="140" scope="row"><table width="140" height="30" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                      <tr>
                        <td><div align="center"><a href="menu.jsp" target="_parent">Voltar Menu Principal </a></div></td>
                      </tr>
                    </table></th>
                  </tr>
                </table>
                </div></td>
            </tr>
          </table>
      </div></td>
    <td height="369" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td height="16" valign="top">&nbsp;</td>
  </tr>
</table>
</body>
</html>
