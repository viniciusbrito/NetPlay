<%@ page contentType="text/html; charset=iso-8859-1"  language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String status = "";

status = request.getParameter("status");
if(status == null)
	status = " ";

String nome = request.getParameter("nome");
String tamanho = request.getParameter("tamanho");
String cor = request.getParameter("cor");

if(nome == null)
    nome = "";
if(tamanho == null)
    tamanho = "";
if(cor == null)
    cor = "";
%>

<html>
<head>
<title>NetPlay</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
body {
	background-color: #DCE7DE;
}
.style1 {color: #DCE7DE}
.style5 {color: #FF0000}
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
	color: #DCE7DE;
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
.style38 {font-size: 14px}
.style40 {color: #DCE7DE; font-size: 11px; }
.style41 {font-size: 11px}
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

function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
</head>

<body>

<div align="center">
  <table width="525" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td colspan="4"><div align="center" class="style38">Inclus&atilde;o de Agente </div></td>
    </tr>
    <tr>
      <td colspan="4">&nbsp;</td>
    </tr>
    <tr>
      <td height="15" colspan="4"><table width="525" height="20" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="155" height="20"><div align="center">
                <table width="155" height="20" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                  <tr>
                    <td width="20"><div align="center">
                        <input name="agente" type="radio" value="movel"  onClick="window.location.href = 'movelpassear.jsp'">
                    </div></td>
                    <td width="140"><div align="center" class="style1">
                        <div align="center" class="style41">Agente M&oacute;vel</div>
                    </div></td>
                  </tr>
                </table>
            </div></td>
            <td width="5">&nbsp;</td>
            <td width="190"><div align="center">
                <table width="190" height="20" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                  <tr>
                    <td width="20"><div align="center">
                      <input name="agente" type="radio" value="aleatorio" onClick="window.location.href = 'estacionarioaleatorio.jsp'">
</div></td>
                    <td width="230"><div align="center" class="style1">
                        <div align="center" class="style41">Agente Estacion&aacute;rio</div>
                    </div></td>
                  </tr>
                </table>
            </div></td>
            <td width="5">&nbsp;</td>
            <td width="170"><table width="170" height="20" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                <tr>
                  <td width="20"><div align="center">
                      <input name="agente" type="radio" onclick="window.location.href = 'estacionarioborda.jsp'" value="borda" checked>
                  </div></td>
                  <td width="210"><div align="center"><span class="style40">Agente Borda</span></div></td>
                </tr>
            </table></td>
          </tr>
      </table></td>
    </tr>
    <tr>
      <td height="15" colspan="4">&nbsp;</td>
    </tr>
    <tr>
      <td height="15" colspan="4"><div align="center">
          <table width="525" border="1" bgcolor="#BBD0BF">
            <tr>
              <td><div align="center">
                <table width="430" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><form action="controlador.ControladorWeb" method="post" name="borda" id="borda" onSubmit="MM_validateForm('Nome2','','R');return document.MM_returnValue">
                        <table width="430" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td height="20"><div align="center">
                              </div></td>
                          </tr>
                          <tr>
                            <td><div align="center">
                              <table width="430" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                <tr>
                                  <td width="177"><div align="left">&nbsp;Nome</div></td>
                                  <td colspan="2"><div align="left">
                                    <input name="Nome" type="text" value="<%=nome%>" id="Nome2" size="40" maxlength="30">
                                  </div></td>
                                </tr>
                                <tr>
                                  <td width="177"><div align="left">&nbsp;Cor</div></td>
                                  <td width="159"><div align="left">
                                    <select name="cor" id="select7" onchange="imagemCor.src='cores/' + this.value + '.jpg'">
                                            <% if (!cor.equals("")) { %>
                                            <% if (cor.equals("MAGENTA2")) { %>
}
                                                <option selected value="MAGENTA2">MAGENTA</option>
                                            <% } else { %>
                                                <option selected value="<%= cor%>"> <%=cor%></option>
                                            <% }} %>
                                            <option value="AMARELO">AMARELO</option>
                                            <option value="AZUL">AZUL</option>
                                            <option value="AZULCLARO">AZUL CLARO</option>
                                            <option value="BRANCO">BRANCO</option>
                                            <option value="CIANO">CIANO</option>
                                            <option value="CINZA">CINZA</option>
                                            <option value="LARANJA">LARANJA</option>
                                            <option value="MAGENTA2">MAGENTA</option>
                                            <option value="MARROM">MARROM</option>
                                            <option value="PRETO">PRETO</option>
                                            <option value="ROSA">ROSA</option>
                                            <option value="VERDE">VERDE</option>
                                            <option value="VERDELIMAO">VERDE LIMAO</option>
                                            <option value="VERDETURQUESA">VERDE TURQUESA</option>
                                            <option value="VERMELHO">VERMELHO</option>
                                            <option value="VIOLETA">VIOLETA</option>																					
                                    </select>
                                  </div></td>
                                  <td width="86"><table width="60" height="20" border="0" align="center" cellpadding="0" cellspacing="0">
                                    <tr>
                                      <td width="121"><img name="imagemCor" src="cores/AMARELO.jpg" align="center" width="70" height="20"/></td>                                      
                                    </tr>
                                  </table></td>
                                </tr>
                                <tr>
                                  <td width="177">
                                    <div align="left">&nbsp;Tamanho da Borda </div></td>
                                  <td colspan="2">
                                      <div align="left">
                                        <select name="Tamanho da Borda" id="select8">
                                            <% if (!tamanho.equals("")) { %>
                                                <option selected value="<%=tamanho%>"><%=tamanho%></option>
                                            <%}%>
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
                                            <option value="11">11</option>
                                            <option value="12">12</option>
                                            <option value="13">13</option>
                                            <option value="14">14</option>
                                            <option value="15">15</option>
                                            <option value="16">16</option>
                                            <option value="17">17</option>
                                            <option value="18">18</option>
                                            <option value="19">19</option>
                                            <option value="20">20</option>
                                            <option value="21">21</option>
                                            <option value="22">22</option>
                                            <option value="23">23</option>
                                            <option value="24">24</option>
                                            <option value="25">25</option>
                                        </select>
                                      </div></td>
                                </tr>
                                <tr>
                                  <td colspan="3"><div align="center"></div>
                                      <div align="center"> </div></td>
                                </tr>
                                <tr>
                                  <td colspan="3"><div align="center">
                                      <input name="salvaragente" type="submit" id="salvaragente3" value="Salvar Agente">
                                  </div></td>
                                </tr>
                              </table>
                            </div></td>
                          </tr>
                          <tr>
                            <td><div align="center">
                                <input name="metodo" type="hidden" value="cadastroAgente">
                                <input name="acao" type="hidden" value="incluir">
                                <input name="tipoAgente" type="hidden" id="tipoAgente" value="Estacionario Borda">
</div></td>
                          </tr>
                          <tr>
                            <td><div align="center"><span class="style5"><%= status %></span></div></td>
                          </tr>
                        </table>
                    </form></td>
                  </tr>
                  <tr>
                    <td width="430" height="10">
                      <div align="right">                        </div></td>
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
