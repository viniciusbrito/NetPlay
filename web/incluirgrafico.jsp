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
import ="javax.servlet.http.HttpServletResponse"
errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String status = "";
int tamanho=0;

status = request.getParameter("status");
if(status == null)
	status = " ";
    response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", -1); //evita o caching no servidor proxy
	
%>
<html>
<head>
<title>Untitled Document</title>
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
.style6 {color: #DCE7DE}
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
  } 
  if (errors) alert(' '+errors);
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
      <td><div align="center" class="style1">Inclus&atilde;o de Novo Gr&aacute;fico </div></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><div align="center">
        <table width="525" border="1" bgcolor="#BBD0BF">
          <tr>
            <th scope="row"><table width="400" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <th height="10" scope="row">&nbsp;</th>
                </tr>
                <tr>
                  <th scope="row"><table width="400" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><form action="controlador.ControladorWeb" method="post" name="plot" id="plot" onSubmit="MM_validateForm('nome','','R','eixo x2','','R','eixo y2','','R','xminimo2','','RisNum','yminimo2','','RisNum','xmaximo2','','RisNum','ymaximo2','','RisNum');return document.MM_returnValue">
                            <div align="center">
                              <table width="400" border="0" cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td><table width="400" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                        <tr>
                                          <td width="160">  <div align="left">Nome do Gr&aacute;fico </div></td>
                                          <td width="240"><input name="Nome" type="text" id="nome" size="40" maxlength="30"></td>
                                        </tr>
                                        <tr>
                                          <td> <div align="left">Tipo do Gr&aacute;fico </div></td>
                                          <td><div align="left">
                                            <select name="tipoGrafico" id="select8">
                                                <option value="linha">Linha</option>
                                                <option value="barra">Barra</option>
                                                <option value="ponto">Ponto</option>
                                            </select>
                                          </div></td>
                                        </tr>
                                        <tr>
                                          <td> <div align="left">Nome Eixo X </div></td>
                                          <td><input name="Eixo x" type="text" id="eixo x2" size="40" maxlength="30"></td>
                                        </tr>
                                        <tr>
                                          <td> <div align="left">Nome Eixo Y </div></td>
                                          <td><input name="Eixo y" type="text" id="eixo y2" size="40" maxlength="30"></td>
                                        </tr>
                                        <tr>
                                          <td> <div align="left">Coordenada X M&iacute;nima </div></td>
                                          <td><input name="X minimo" type="text" id="xminimo2" size="40" maxlength="30"></td>
                                        </tr>
                                        <tr>
                                          <td> <div align="left">Coordenada Y M&iacute;nima </div></td>
                                          <td><input name="Y minimo" type="text" id="yminimo2" size="40" maxlength="30"></td>
                                        </tr>
                                        <tr>
                                          <td> <div align="left">Coordenada X M&aacute;xima</div></td>
                                          <td><input name="X maximo" type="text" id="xmaximo2" size="40" maxlength="30"></td>
                                        </tr>
                                        <tr>
                                          <td> <div align="left">Coordenada Y M&aacute;xima</div></td>
                                          <td><input name="Y maximo" type="text" id="ymaximo2" size="40" maxlength="30"></td>
                                        </tr>
                                        <tr>
                                          <td colspan="2"><div align="center">Elementos do Gr&aacute;fico </div></td>
                                         <% List variaveisValor = new ArrayList(); 
                                            List variaveisValor2 = null;
                 		     Modelo m = (Modelo)session.getAttribute("modelo");
                                     variaveisValor2 = AplCadastrarVariavel.variaveisDoModeloPeloTipo(m.getIdModelo().intValue(),"Valor");
                                     for (int i=0; i<variaveisValor2.size(); i++)
                                     {
                                         Variavel var = (Variavel) variaveisValor2.get(i);
                                         if (var.getAgente() == null)
                                             variaveisValor.add(var);
                                     } %>
                                        </tr>
					<%if(variaveisValor.size()>5){
                                         tamanho = 5;%>
                                        <tr>
                                          <td colspan="2"><table width="400" border="1" cellpadding="0" cellspacing="0">
                                            <tr>
                                              <th width="83" scope="row">Ativar Vari&aacute;vel no Gr&aacute;fico </th>
                                              <td width="159"> <div align="center">Vari&aacute;vel</div></td>
                                              <td width="158"><div align="center">Cor 
                                                <table width="100" height="20" border="0" align="center" cellpadding="0" cellspacing="0">
                                                  <tr>
                                                    <td width="121" bgcolor="#333300"><div align="center" class="style6"><a href="#" onClick="MM_openBrWindow('cores.jsp','cores','width=420,height=470')">Lista de Cores</a></div></td>
                                                  </tr>
                                                </table>
                                              </div></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"><input name="checkbox1" type="checkbox" id="checkbox1" value="ON"></th>
                                              <td><div align="center">
                                                <select name="variavel1" id="variavel1">
                                                <% for(int i=0; i<variaveisValor.size(); i++){
		                                 VariavelValor vv = (VariavelValor)variaveisValor.get(i); %>
                                                     <option value= <%= vv.getNome() %>> <%= vv.getNome() %></option> 
                                                <%}%>   
                                                </select>
                                              </div></td>
                                              <td><div align="center">
                                                <select name="cor1" id="cor1">
                                            <option value="AMARELO">AMARELO</option>
                                            <option value="AZUL">AZUL</option>
                                            <option value="AZULCLARO">AZUL CLARO</option>
                                            <option value="BRANCO">BRANCO</option>
                                            <option value="CIANO">CIANO</option>
                                            <option value="CINZA">CINZA</option>
                                            <option value="LARANJA">LARANJA</option>
                                            <option value="MAGENTA">MAGENTA</option>
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
                                            </tr>
                                            <tr>
                                              <th scope="row"><input type="checkbox" name="checkbox2" value="ON"></th>
                                              <td><div align="center">
                                                <select name="variavel2" id="variavel2">
                                                <% for(int i=0; i<variaveisValor.size(); i++){
		                                 VariavelValor vv = (VariavelValor)variaveisValor.get(i); %>
                                                     <option value= <%= vv.getNome() %>> <%= vv.getNome() %></option> 
                                                <%}%>
                                                </select>
                                              </div></td>
                                              <td><div align="center">
                                                <select name="cor2" id="cor2">
                                            <option value="AMARELO">AMARELO</option>
                                            <option value="AZUL">AZUL</option>
                                            <option value="AZULCLARO">AZUL CLARO</option>
                                            <option value="BRANCO">BRANCO</option>
                                            <option value="CIANO">CIANO</option>
                                            <option value="CINZA">CINZA</option>
                                            <option value="LARANJA">LARANJA</option>
                                            <option value="MAGENTA">MAGENTA</option>
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
                                            </tr>
                                            <tr>
                                              <th scope="row"><input type="checkbox" name="checkbox3" value="ON"></th>
                                              <td><div align="center">
                                                <select name="variavel3" id="variavel3">
                                                <% for(int i=0; i<variaveisValor.size(); i++){
		                                 VariavelValor vv = (VariavelValor)variaveisValor.get(i); %>
                                                     <option value= <%= vv.getNome() %>> <%= vv.getNome() %></option> 
                                                <%}%>       
                                                </select>
                                              </div></td>
                                              <td><div align="center">
                                                <select name="cor3" id="cor3">
                                            <option value="AMARELO">AMARELO</option>
                                            <option value="AZUL">AZUL</option>
                                            <option value="AZULCLARO">AZUL CLARO</option>
                                            <option value="BRANCO">BRANCO</option>
                                            <option value="CIANO">CIANO</option>
                                            <option value="CINZA">CINZA</option>
                                            <option value="LARANJA">LARANJA</option>
                                            <option value="MAGENTA">MAGENTA</option>
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
                                            </tr>
                                            <tr>
                                              <th scope="row"><input type="checkbox" name="checkbox4" value="ON"></th>
                                              <td><div align="center">
                                                <select name="variavel4" id="variavel4">
                                                <% for(int i=0; i<variaveisValor.size(); i++){
		                                 VariavelValor vv = (VariavelValor)variaveisValor.get(i); %>
                                                     <option value= <%= vv.getNome() %>> <%= vv.getNome() %></option> 
                                                <%}%>   
                                                </select>
                                              </div></td>
                                              <td><div align="center">
                                                <select name="cor4" id="cor4">
                                            <option value="AMARELO">AMARELO</option>
                                            <option value="AZUL">AZUL</option>
                                            <option value="AZULCLARO">AZUL CLARO</option>
                                            <option value="BRANCO">BRANCO</option>
                                            <option value="CIANO">CIANO</option>
                                            <option value="CINZA">CINZA</option>
                                            <option value="LARANJA">LARANJA</option>
                                            <option value="MAGENTA">MAGENTA</option>
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
                                            </tr>
                                            <tr>
                                              <th scope="row"><input type="checkbox" name="checkbox5" value="ON"></th>
                                              <td><div align="center">
                                                <select name="variavel5" id="variavel5">
                                                <% for(int i=0; i<variaveisValor.size(); i++){
		                                 VariavelValor vv = (VariavelValor)variaveisValor.get(i); %>
                                                     <option value= <%= vv.getNome() %>> <%= vv.getNome() %></option> 
                                                <%}%>   
                                                </select>
                                              </div></td>
                                              <td><div align="center">
                                                <select name="cor5" id="cor5">
                                            <option value="AMARELO">AMARELO</option>
                                            <option value="AZUL">AZUL</option>
                                            <option value="AZULCLARO">AZUL CLARO</option>
                                            <option value="BRANCO">BRANCO</option>
                                            <option value="CIANO">CIANO</option>
                                            <option value="CINZA">CINZA</option>
                                            <option value="LARANJA">LARANJA</option>
                                            <option value="MAGENTA">MAGENTA</option>
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
                                            </tr>
                                          </table></td>
                                        </tr>
                                        <%}else{
                                            if(!variaveisValor.isEmpty()){%>
										   <tr>
                                          <td colspan="2"><table width="400" border="1" cellpadding="0" cellspacing="0">
                                            <tr>
                                              <th width="83" scope="row">Ativar Vari&aacute;vel no Gr&aacute;fico </th>
                                              <td width="159"> <div align="center">Vari&aacute;vel</div></td>
                                              <td width="158"><div align="center">Cor
                                                <table width="100" height="20" border="0" align="center" cellpadding="0" cellspacing="0">
                                                  <tr>
                                                    <td width="121" bgcolor="#333300"><div align="center" class="style6"><a href="#" onClick="MM_openBrWindow('cores.jsp','cores','width=420,height=430')">Lista de Cores</a></div></td>
                                                  </tr>
                                                </table>
                                              </div></td>
                                            </tr>
                                            
											<% 	String check = "";
												String variavel = "";
												String cor = "";
												tamanho = variaveisValor.size();
												for(int i=1; i<=variaveisValor.size(); i++){
				    							VariavelValor vv = (VariavelValor)variaveisValor.get(i-1); 
												variavel = "variavel" + i;
												cor = "cor" + i;
												check = "checkbox" + i;
											%>
											<tr>
                                              <th scope="row"><input name=<%=check%> type="checkbox" value="ON"></th>
                                              <td><div align="center">
                                                <input name=<%=variavel%> value=<%=vv.getNome()%>  readonly=""  type="text" size="20">
                                              </div></td>
                                              <td><div align="center">
                                                <select name=<%=cor%> id=<%=cor%>>
                                            <option value="AMARELO">AMARELO</option>
                                            <option value="AZUL">AZUL</option>
                                            <option value="AZULCLARO">AZUL CLARO</option>
                                            <option value="BRANCO">BRANCO</option>
                                            <option value="CIANO">CIANO</option>
                                            <option value="CINZA">CINZA</option>
                                            <option value="LARANJA">LARANJA</option>
                                            <option value="MAGENTA">MAGENTA</option>
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
                                            </tr>                                  
                                         <%}%>       
                                        </table></td>
                                        </tr>      
                                        <%}else{%>    
					<tr>
                                          <td colspan="2"><div align="center"><span class="style5">Esse modelo n&atilde;o possui vari&aacute;veis (tipo valor) para serem inseridas no Gr&aacute;fico </span></div></td>
                                        </tr>									    
					<%}}%>                                            
                                        <tr>
                                        </tr>
                                        <tr>
                                          <td colspan="2">&nbsp;</td>
                                        </tr>
                                        <tr>
                                          <td colspan="2"><div align="center">
                                            <input name="salvarplot" type="submit" id="salvarplot" value="Salvar Gr&aacute;fico">
                                          </div></td>
                                        </tr>
                                    </table>                                  </td>
                                  </tr>
                                  <tr>
                                    <td><div align="center">
                                        <input name="metodo" type="hidden" value="cadastroPlot">
                                        <input name="acao" type="hidden" value="incluir">
                                      <input name="tamanho" type="hidden" id = "tamanho" value="<%=tamanho%>">
</div></td>
                                  </tr>
                                  <tr>
                                    <td><div align="center"><span class="style5"><%= status %></span></div></td>
                                  </tr>
                              </table>
                            </div>
                        </form></td>
                      </tr>
                  </table></th>
                </tr>
                <tr>
                  <th height="10" scope="row">&nbsp;</th>
                </tr>
            </table></th>
          </tr>
        </table>
      </div></td>
    </tr>
  </table>
</div>
</body>
</html>
