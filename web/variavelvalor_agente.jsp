<%@ page contentType="text/html; charset=iso-8859-1" language="java" 
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

String nome = request.getParameter("nome");
if (nome == null)
    nome = "";

String expressao = request.getParameter("expr");
if (expressao == null)
    expressao = "";

String idAgente = request.getParameter("agente");
if (idAgente == null)
    idAgente = "-1";
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
                          <input name="variavel" type="radio" value="slider" onClick="window.location.href = 'variavelsliderInicial.jsp'">
                      </div></td>
                      <td width="130">&nbsp;<span class="style40"> Vari&aacute;vel Slider </span></td>
                    </tr>
                  </table>
              </div></td>
              <td width="20">&nbsp;</td>
              <td width="140"><table width="140" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                  <tr>
                    <td width="20"><div align="center">
                        <input name="variavel" type="radio" onClick="window.location.href = 'variavelvalorInicial.jsp'" value="valor" checked>
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
                      <td><form action="controlador.ControladorWeb" method="post" name="logica" id="logica" onSubmit="MM_validateForm('Nome4','','R','valor','','R');return document.MM_returnValue">
                          <table width="380" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td width="380" height="10" valign="top">&nbsp;</td>
                            </tr>
                            <tr>
                              <td><div align="center">
                                  <table width="380" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                    <tr>
                                      <td width="140"> <div align="left">Nome </div></td>
                                      <td width="240"><input name="Nome" type="text" id="Nome4" value="<%=nome%>" size="40" maxlength="50"></td>
                                    </tr>
                                    <tr>
                                      <td> <div align="left"></div>
                                      <div align="left">Valor</div></td>
                                      <td><textarea name="Valor" id="valor" cols="30" rows="3" readonly="readonly"><%= expressao%></textarea></td>
                                    </tr>
                                                                <tr>
                                                                    <td colspan="2"><table width="380" bgcolor="#DCE7DE">
                                                                        <tr>
                                                                            <td colspan="3" bordercolor="#DCE7DE" bgcolor="#333300"><div align="center" class="style34"><a href="javascript:window.location.href='editarexp.jsp?pai=variavel&nome=' + encode(document.logica.Nome.value) + '&expr=' + encode('<%=expressao%>') + '&agente=' + document.logica.Agente.value">Editar Express&atilde;o</a></div></td>
                                                                        </tr>
                                                                    </table></td>
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
                                                        <input name="variavel" type="radio" onclick="window.location.href = 'variavelvalor_agente.jsp?nome='+Nome.value +'&expr='+ encode(Valor.value) + '&agente=' + Agente.value" value="sim" checked>
                                                    </div></td>
                                                    <td width="40"><div align="center" class="style1">SIM</div></td>
                                                  </tr>
                                              </table></td>
                                              <td width="30">&nbsp;</td>
                                              <td width="60"><table width="60" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                  <tr>
                                                    <td width="20"><div align="center">
                                                        <input name="variavel" type="radio" onclick="window.location.href = 'variavelvalor.jsp?nome='+Nome.value +'&expr='+ encode(Valor.value)" value="n&atilde;o">
                                                    </div></td>
                                                    <td width="40"><div align="center" class="style1">N&Atilde;O</div></td>
                                                  </tr>
                                              </table></td>
                                            </tr>
                                          </table>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><div align="center">
                                        <table width="380" border="0" cellspacing="0" cellpadding="0">
                                          <tr>
                                            <td>&nbsp;</td>
                                          </tr>
                                          <tr>
                                            <td><div align="center">
                                              <table width="250" height="20" border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                  <td width="125">
                                                    <div align="center">
                                                      <% List agentes; 
							 Modelo m = (Modelo)session.getAttribute("modelo");
							 agentes = AplCadastrarAgente.agentesDoModelo(m.getIdModelo().intValue());
                                                       %>
                                                      Escolha o agente </div></td>
                                                  <td width="125">
                                                        <div align="left">
                                                          <select name="Agente" id="escolha do agente">
                                                           <% for(int i=0; i<agentes.size(); i++){
		                                                        Agente agente = (Agente)agentes.get(i);
                                                                        if(idAgente.equals(agente.getIdAgente().toString())) { %>
                                                                            <option selected value= <%= agente.getIdAgente() %>> <%= agente.getNome() %></option> 
                                                                        <% } else { %>
                                                                            <option value= <%= agente.getIdAgente() %>> <%= agente.getNome() %></option> 
                                                            <%}}%>
                                                          </select>
                                                      </div></td></tr>
                                              </table>
                                            </div></td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                          </tr>
                                        </table>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="2"><div align="center">
                                          <input name="salvar variavel" type="submit" id="salvar variavel4" value="Salvar Vari&aacute;vel">
                                      </div></td>
                                    </tr>
                                  </table>
                              </div></td>
                            </tr>
                            <tr>
                              <td><div align="center">
                                  <input name="metodo" type="hidden" value="cadastroVariavel">
                                  <input name="acao" type="hidden" value="incluir">
                                  <input name="TipoVariavel" type="hidden" id="TipoVariavel" value="Valor">
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
