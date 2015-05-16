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
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
.style34 {color: #DCE7DE}
body {
	background-color: #DCE7DE;
}
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
a:visited {
	color: #DCE7DE;
	text-decoration: none;
}
a:active {
	color: #DCE7DE;
	text-decoration: none;
}
a:link {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
	color: #A8C4AD;
}
.style5 {color: #FF0000}
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
  var i,p,q,nm,test,num,min,max,errors='',args=MM_validateForm.arguments;
  for (i=0; i<(args.length-2); i+=3) { test=args[i+2]; val=MM_findObj(args[i]);
    if (val) { nm=val.name; if ((val=val.value)!="") {
      if (test.indexOf('isEmail')!=-1) { p=val.indexOf('@');
        if (p<1 || p==(val.length-1)) errors+='- '+nm+' must contain an e-mail address.\n';
      } else if (test!='R') { num = parseFloat(val);
        if (isNaN(val)) errors+='- '+nm+' must contain a number.\n';
        if (test.indexOf('inRange') != -1) { p=test.indexOf(':');
          min=test.substring(8,p); max=test.substring(p+1);
          if (num<min || max<num) errors+='- '+nm+' must contain a number between '+min+' and '+max+'.\n';
    } } } else if (test.charAt(0) == 'R') errors += '- '+nm+' é obrigatório.\n'; }
  } if (errors) alert(' '+errors);
  document.MM_returnValue = (errors == '');
}
//-->
</script>
</head>

<body>
<div align="center">
  <p>&nbsp;</p>
  <table width="440" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th colspan="4" scope="row"><table width="440" border="1">
        <tr>
          <th scope="row"><table width="440" border="0" cellpadding="0" cellspacing="0" bgcolor="#BBD0BF">
            <tr>
              <td><div align="center">
                  <form action="../controlador.ControladorWeb" method="post" name="modelo" id="modelo" onSubmit="MM_validateForm('descricao','','R');return document.MM_returnValue">
                    <table width="400" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td height="10"><div align="center"> </div></td>
                      </tr>
                      <tr>
                        <td><table width="440" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td width="50"><% Modelo m = (Modelo)session.getAttribute("modelo");%></td>
                              <td width="340"><div align="center">
                                  <table width="340" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                    <tr>
                                      <td width="120"><div align="left">Nome</div></td>
                                      <td width="220"><input name="nome" disabled = "disabled" type="text" id="nome2" value="<%=m.getNome()%>" size="40"></td>
                                    </tr>
                                    <tr>
                                      <td><div align="left">Descri&ccedil;&atilde;o</div></td>
                                      <td>
                                        <div align="left">
                                          <textarea name="descricao" disabled = "disabled" cols="30" rows="3" id="descricao"><%= m.getDescricao() %></textarea>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td height="10" colspan="2"><div align="center">
                                      </div></td>
                                    </tr>
                                  </table>
                              </div></td>
                              <td width="50"><input name="metodo" type="hidden" value="cadastroModelo">
                                  <input name="acao" type="hidden" value="alterar">
                              </td>
                            </tr>
                        </table></td>
                      </tr>
                    </table>
                  </form>
              </div></td>
            </tr>
            <tr>
              <td><div align="center"> </div></td>
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
      <th width="50" scope="row">&nbsp;</th>
      <th width="240" scope="row"><table width="240" height="30" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
        <tr>
          <td width="120"><div align="center" class="style34"><a href="controlador.ControladorWeb">Visualizar Arquivo</a> </div></td>
          <td width="120"><div align="center" class="style34"><a href="controlador.ControladorWeb">Gerar Arquivo </a></div></td>
        </tr>
      </table></th>
      <th width="100" scope="row"><div align="right">
        <table width="60" height="30" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
          <tr>
            <td><div align="center"><a href="menu.jsp" target="_parent">Voltar</a></div></td>
          </tr>
        </table>
      </div></th>
      <th width="50" scope="row">&nbsp;</th>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</div>
</body>
</html>
