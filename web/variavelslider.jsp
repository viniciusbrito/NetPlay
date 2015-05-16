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
body {
	background-color: #DCE7DE;
}
a {
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
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_validateForm() { //v4.0
  var i,p,q,nm,test,num,min,max,minimo, maximmo, inicial, errors='',args=MM_validateForm.arguments;
  for (i=0; i<(args.length-2); i+=3) { test=args[i+2]; val=MM_findObj(args[i]);
    if (val) { nm=val.id; if ((val=val.value)!="") {
      if (test.indexOf('isEmail')!=-1) { p=val.indexOf('@');
        if (p<1 || p==(val.length-1)) errors+='- '+nm+' deve conter um endereço de email.\n';
      } else if (test!='R') { num = parseFloat(val);
        if (isNaN(val)) errors+='- '+nm+' deve conter um número.\n';
        if (test.indexOf('inRange') != -1) { p=test.indexOf(':');
          min=test.substring(8,p); max=test.substring(p+1);
          if (num<min || max<num) errors+='- '+nm+' deve conter um número entre '+min+' e '+max+'.\n';
    } } } else if (test.charAt(0) == 'R') errors += '- '+nm+' é obrigatório.\n'; }
  }
  min = parseInt(MM_findObj(args[6]).value);
  max = parseInt(MM_findObj(args[9]).value);
  num = parseInt(MM_findObj(args[3]).value);
  if(num <  min) 
        errors += '- Valor Inicial deve estar entre o valor  mínimo e máximo.\n';
  if (num > max)
        errors += '- Valor Inicial deve estar entre o valor  mínimo e máximo.\n';
  if(min > max) 
	    errors += '- Valor mínimo deve ser menor que o valor máximo.\n'; 
  if (errors) alert(' '+errors);
  document.MM_returnValue = (errors == '');
}
//-->
</script>
</head>

<body>

<div align="center">
  <table width="500" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td colspan="4"><div align="center" class="style38">Inclus&atilde;o de Vari&aacute;vel</div></td>
    </tr>
    <tr>
      <td colspan="4">&nbsp;</td>
    </tr>
    <tr>
      <td height="15" colspan="4"><div align="center">
          <table width="500" height="18" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="180" height="18"><div align="center">
                  <table width="180" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                    <tr>
                      <td width="20"><div align="center">
                          <input name="variavel" type="radio" value="logica" onClick="window.location.href = 'variavellogicaInicial.jsp'">
                      </div></td>
                      <td width="160">&nbsp;<span class="style40">Vari&aacute;vel L&oacute;gica (Switch) </span></td>
                    </tr>
                  </table>
              </div></td>
              <td width="20">&nbsp;</td>
              <td width="140"><div align="center">
                  <table width="140" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                    <tr>
                      <td width="20"><div align="center">
                          <input name="variavel" type="radio" onClick="window.location.href = 'variavelsliderInicial.jsp'" value="slider" checked>
                      </div></td>
                      <td width="130">&nbsp;<span class="style40"> Vari&aacute;vel Slider </span></td>
                    </tr>
                  </table>
              </div></td>
              <td width="20">&nbsp;</td>
              <td width="140"><table width="140" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                  <tr>
                    <td width="20"><div align="center">
                        <input name="variavel" type="radio" value="valor" onClick="window.location.href = 'variavelvalorInicial.jsp'">
                    </div></td>
                    <td width="130">&nbsp;<span class="style40">Vari&aacute;vel Valor </span></td>
                  </tr>
              </table></td>
            </tr>
          </table>
      </div></td>
    </tr>
    <tr>
      <td height="15" colspan="4"><div align="center"> </div></td>
    </tr>
    <tr>
      <td height="15" colspan="4"><div align="center">
          <table width="500" border="1" bgcolor="#BBD0BF">
            <tr>
              <td><div align="center">
                  <table width="380" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td><form action="controlador.ControladorWeb" method="post" name="logica" id="logica" onSubmit="MM_validateForm('Nome','','R','Valor Inicial','','RisNum','Valor Minimo','','RisNum','Valor Maximo','','RisNum','Incremento','','RisNum');return document.MM_returnValue">
                          <table width="380" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td width="380" height="10" valign="top">&nbsp;</td>
                            </tr>
                            <tr>
                              <td><div align="center">
                                  <table width="380" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                    <tr>
                                      <td width="140"> <div align="left"></div>
                                      <div align="left">Nome </div></td>
                                      <td width="240"><input name="Nome" type="text" id="Nome" value="<%= request.getParameter("nome")%>" size="40" maxlength="50"></td>
                                    </tr>
                                    <tr>
                                      <td> <div align="left">Valor Inicial </div></td>
                                      <td><input name="valorinicial" type="text" id="Valor Inicial" value="<%= request.getParameter("valorinicial")%>" size="40" maxlength="30"></td>
                                    </tr>
                                    <tr>
                                      <td> <div align="left">Valor M&iacute;nimo </div></td>
                                      <td><input name="valorminimo" type="text" id="Valor Minimo" value="<%= request.getParameter("valorminimo")%>" size="40" maxlength="30"></td>
                                    </tr>
                                    <tr>
                                      <td> <div align="left">Valor M&aacute;ximo </div></td>
                                      <td><input name="valormaximo" type="text" id="Valor Maximo" value="<%= request.getParameter("valormaximo")%>" size="40" maxlength="30"></td>
                                    </tr>
                                    <tr>
                                      <td> <div align="left"></div>
                                      <div align="left">Incremento </div></td>
                                      <td><input name="incremento" type="text" id="Incremento" value="<%= request.getParameter("incremento")%>" size="40" maxlength="30"></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2">&nbsp;</td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><div align="center">Vari&aacute;vel associada a algum agente? </div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2">&nbsp;</td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><div align="center">
                                          <table width="150" border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                              <td width="60"><table width="60" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                  <tr>
                                                    <td width="20"><div align="center">
                                                        <input name="variavel" type="radio" value="sim" onclick="window.location.href = 'variavelslider_agente.jsp?nome='+Nome.value + '&valorinicial='+valorinicial.value + '&valorminimo='+valorminimo.value +'&valormaximo='+valormaximo.value + '&incremento='+incremento.value">
                                                    </div></td>
                                                    <td width="40"><div align="center" disabled  class="style1">SIM</div></td>
                                                  </tr>
                                              </table></td>
                                              <td width="30">&nbsp;</td>
                                              <td width="60"><table width="60" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                  <tr>
                                                    <td width="20"><div align="center">
                                                        <input name="variavel" disabled  type="radio" onclick="window.location.href = 'variavelslider.jsp'" value="n&atilde;o" checked>
                                                    </div></td>
                                                    <td width="40"><div align="center" class="style1">N&Atilde;O</div></td>
                                                  </tr>
                                              </table></td>
                                            </tr>
                                          </table>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2">&nbsp;</td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><div align="center">
                                          <input name="salvar variavel" type="submit" id="salvar variavel" value="Salvar Vari&aacute;vel">
                                      </div></td>
                                    </tr>
                                  </table>
                              </div></td>
                            </tr>
                            <tr>
                              <td><div align="center">
                                  <input name="metodo" type="hidden" value="cadastroVariavel">
                                  <input name="acao" type="hidden" value="incluir">
                                  <input name="TipoVariavel" type="hidden" id="TipoVariavel" value="Slider">
</div></td>
                            </tr>
                            <tr>
                              <td><div align="center"><span class="style5"><%= status %></span></div></td>
                            </tr>
                          </table>
                      </form></td>
                    </tr>
                    <tr>
                      <td width="380" height="10"><div align="right"> </div></td>
                    </tr>
                  </table>
              </div></td>
            </tr>
          </table>
      </div></td>
    </tr>
  </table>
</div>
<div align="center"></div>
</body>
</html>
