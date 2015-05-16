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
String expressao = "";
String var1;
String var2;

expressao = request.getParameter("expr");
var1 = request.getParameter("var1"); //id de var1
var2 = request.getParameter("var2"); //id de var2

int idVar1 = -1;
int idVar2 = -1;
if (var1 != null)
    idVar1 = Integer.parseInt(var1);
if (var2 != null)
    idVar2 = Integer.parseInt(var2);

if(expressao == null)
	expressao = "";

List vars2;
Modelo m2 = (Modelo)session.getAttribute("modelo");
vars2 = AplCadastrarVariavel.variaveisDoModelo(m2.getIdModelo().intValue());

 //Verificando se existem variaveis no modelo
   if (vars2.size() == 0)
   { %>
       <p>
        N&atilde;o &eacute; poss&iacute;vel criar essa condi&ccedil;&atilde;o devido a inexist&ecirc;ncia de vari&aacute;veis nesse modelo.
       </p>
   <% } else { 

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
    } } } else if (test.charAt(0) == 'R') errors += '- '+nm+' eh requerido.\n'; }
  } if (errors) alert('Erros:\n'+errors);
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
  <table border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td colspan="4"><div align="center" class="style38">Inclus&atilde;o de Condi&ccedil;&atilde;o de Comparação de Variáveis </div></td>
    </tr>
    
    
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
                  <td><form action="controlador.ControladorWebCCA" method="post" name="ccmpvar" id="ccmpvar" onSubmit="MM_validateForm('expressao','','R');return document.MM_returnValue">
                      <table width="380" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="380" height="10" valign="top">&nbsp;</td>
                        </tr>
                        <tr>
                          <td><div align="center">
                            <table width="366" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                              <tr>
                                <td width="100">
                                  <div align="left">
                                     <% List vars1;
                                        Modelo m1 = (Modelo)session.getAttribute("modelo");
                                        vars1 = AplCadastrarVariavel.variaveisDoModelo(m1.getIdModelo().intValue());
                                     %>
                                  Vari&aacute;vel 1 </div></td>
                                <td colspan="2"><div align="left">
                                    <select name="var1" id="var1">
                                      <%for(int i=0; i<vars1.size(); i++){
                                            Variavel v = (Variavel)vars1.get(i);
                                            if(idVar1 == v.getIdVariavel().intValue()) { %>
                                                <option selected value= <%= v.getIdVariavel() %>> <%= v.getNome() %></option>
                                            <%} else {%>
                                                <option value= <%= v.getIdVariavel() %>> <%= v.getNome() %></option>
                                      <%}}%>
                                    </select>
                                </div></td>
                              </tr>
                              <tr>
                                <td> <div align="left">
                                     Vari&aacute;vel 2 </div></td>
                                <td colspan="2"><div align="left">
                                    <select name="var2" id="var2">
                                      <%for(int i=0; i<vars2.size(); i++){
                                              Variavel v = (Variavel)vars2.get(i);
                                              if(idVar2 == v.getIdVariavel().intValue()) { %>
                                                <option selected value= <%= v.getIdVariavel() %>> <%= v.getNome() %></option>
                                            <%} else {%>
                                                <option value= <%= v.getIdVariavel() %>> <%= v.getNome() %></option>
                                      <%}}%>
                                    </select>
                                </div></td>
                              </tr>
                              <tr>
                                <td align="left">Express&atilde;o</td>
                                <td colspan="2"><textarea name="expressao"  cols="30" rows="3" id="expressao" readonly="readonly"><%=expressao%></textarea></td>
                              </tr>
                              <tr>
                                <td colspan="3" bordercolor="#DCE7DE" bgcolor="#333300"><div align="center" class="style34"><a href="javascript:window.location.href='editarexp.jsp?pai=condcmpvar&var1=' + document.ccmpvar.var1.value + '&var2=' + document.ccmpvar.var2.value + '&expr=' + encode('<%= expressao%>')">Editar Express&atilde;o</a></div></td>
                              </tr>
                              <tr>
                                <td colspan="3">&nbsp;</td>
                              </tr>
                              <tr>
                                <td colspan="3"><div align="center">
                                    <input name="salvar condicao" type="submit" id="salvar condicao" value="Salvar Condi&ccedil;&atilde;o">
                                </div></td>
                              </tr>
                            </table>
                          </div></td>
                        </tr>
                        <tr>
                          <td><div align="center">
                              <input name="metodo" type="hidden" value="cadastroCondicao">
                              <input name="acao" type="hidden" value="incluir">
                              <input name="TipoCondicao" type="hidden" id="TipoCondicao" value="CmpVar">
			  </div></td>
                        </tr>
                      </table>
                  </form></td>
                </tr>
                <tr>
                  <td width="380" height="10"><div align="right">
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
<div align="center"></div>
<div align="center"></div>
</body>
</html>

<% } %>