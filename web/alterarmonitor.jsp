<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
String nome = "";
String expressao = "";
String casasDecimais= "";
String nomeAntigo = "";

nome = request.getParameter("nome");
expressao = request.getParameter("expressao");
nomeAntigo = request.getParameter("nomeAntigo");
casasDecimais = request.getParameter("casasDecimais");

if (nomeAntigo == null)
    nomeAntigo = nome;
System.out.println("xxx " + nomeAntigo);

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
        if (p<1 || p==(val.length-1)) errors+='- '+nm+' deve conter um endere�o de email.\n';
      } else if (test!='R') { num = parseFloat(val);
        if (isNaN(val)) errors+='- '+nm+' deve conter um n�mero.\n';
        if (test.indexOf('inRange') != -1) { p=test.indexOf(':');
          min=test.substring(8,p); max=test.substring(p+1);
          if (num<min || max<num) errors+='- '+nm+' deve conter um n�mero entre '+min+' e '+max+'.\n';
    } } } else if (test.charAt(0) == 'R') errors += '- '+nm+' � obrigat�rio.\n'; }
  } if (errors) alert(' '+errors);
  document.MM_returnValue = (errors == '');
}
  
  function encode(s){
    var t = '';
    for(var i=0;i<s.length;i++)
    {
        var l = s.charAt(i);
        if(l == '-') t += '%2D';
        else if(l == '@') t += '%40';
             else if(l == '*') t += '%2A';
                  else if(l == '+') t += '%2B';
                       else if(l == '_') t += '%5F';
                            else if(l == '/') t += '%2F';
                                 else if(l == '.') t += '%2E';
                                      else t += escape(l);
    }
    return t;       
}
//-->
</script>
</head>

<body>

<div align="center">
  <table width="525" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td><div align="center" class="style1">Altera&ccedil;&atilde;o Monitor</div></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><div align="center">
        <table width="525" border="1" bgcolor="#BBD0BF">
          <tr>
            <td><div align="center">
              <table width="400" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><form action="controlador.ControladorWeb" method="post" name="monitor" id="monitor" onSubmit="MM_validateForm('Nome','','R','textarea','','R');return document.MM_returnValue">
                      <table width="400" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td height="10">&nbsp;</td>
                        </tr>
                        <tr>
                          <td><div align="center">
                            <table width="400" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                              <tr>
                                <td width="160">  <div align="left">Nome </div></td>
                                <td width="240"><input name="Nome" type="text" id="Nome" value="<%=nome%>" size="40"></td>
                              </tr>
                              <tr>
                                <td> <div align="left">Express&atilde;o </div></td>
                                <td><textarea name="Expressao" cols="30" rows="3" id="textarea" readonly="readonly"><%=expressao%></textarea></td>
                              </tr>
							  <tr>
                                  <td colspan="2"><table width="398" bgcolor="#DCE7DE">
                                  <tr>
                                  
                                    <td width="398" bordercolor="#DCE7DE" bgcolor="#333300"><div align="center" class="style34"><a href="javascript:window.location.href='editarexp.jsp?pai=altmonitor&nomeAntigo=<%=nomeAntigo%>&status=<%=status%>&expr=' + encode('<%=expressao%>') + '&nome=' + document.monitor.Nome.value + '&casasDecimais=' + document.monitor.elements[2].value">Editar Express&atilde;o</a></div></td>
                                     <!-- <td width="150" bordercolor="#DCE7DE" bgcolor="#333300"><div align="center" class="style34"><a href="">Validar Express&atilde;o </a></div></td> -->
                                  </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td> <div align="left">N&ordm; de Casas Decimais</div></td>
                                <td><div align="left">
                                    <select name="Casas Decimais" id="casas decimais">
	                                <option selected value= <%= casasDecimais%>> <%=casasDecimais%></option>
                                      <option value="0">0</option>
                                      <option value="1">1</option>
                                      <option value="2">2</option>
                                      <option value="3">3</option>
                                      <option value="4">4</option>
                                      <option value="5">5</option>
                                      <option value="6">6</option>
                                      <option value="7">7</option>
                                      <option value="8">8</option>
                                      <option value="9">9</option>
                                      <option value="10">10</option>
                                    </select>
                                </div></td>
                              </tr>
                              <tr>
                                <td colspan="2">&nbsp;</td>
                              </tr>
                              <tr>
                                <td colspan="2"><div align="center">
                                    <input name="alterarmonitor" type="submit" id="alterarmonitor" value="Alterar Monitor">
                                </div></td>
                              </tr>
                            </table>
                          </div></td>
                        </tr>
                        <tr>
                          <td><div align="center">
                              <input name="metodo" type="hidden" value="cadastroMonitor">
                              <input name="acao" type="hidden" value="alterar">
                              <input name="nomeAntigo" type="hidden" id="nomeAntigo" value="<%= nomeAntigo %>">
</div></td>
                        </tr>
                        <tr>
                          <td><div align="center"><span class="style5"><%= status %></span></div></td>
                        </tr>
                      </table>
                  </form></td>
                </tr>
                <tr>
                  <td height="10"><div align="right">
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
