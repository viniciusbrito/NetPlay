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
    String expressao = "";
    String idVariavel = "";
    String idAgente = "";

    status = request.getParameter("status");
    if(status == null)
        status = " ";

    expressao = request.getParameter("expr");
    if(expressao == null)
        expressao = "";
    
    idVariavel = request.getParameter("idVar");
    if(idVariavel == null)
        idVariavel = "-1";
    
    idAgente = request.getParameter("agente");
    if(idAgente == null)
        idAgente = "-1";
    
    List vars1;
    Modelo m1 = (Modelo)session.getAttribute("modelo");
    vars1 = AplCadastrarVariavel.variaveisDoModelo(m1.getIdModelo().intValue());
    
    List agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
    
    if(vars1.size() == 0) { %>
        <p>N&atilde;o &eacute; poss&iacute;vel criar essa a&ccedil;&atilde;o devido a inexist&eacute;ncia de vari&aacute;veis nesse modelo</p>
    <% } else {

%>

<html>
    <head>
        <title>NETPLAY</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="usableforms.js"></script>
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
            
            function expressao()
            {
                if (document.acRecValVar.tipo.value == 1)
                {
                    window.location.href='editarexp.jsp?pai=acrecvalvar&expr=' + encode('<%=expressao%>') + '&var=' + document.acRecValVar.variavel.value;
                }
                else
                {
                    window.location.href='editarexp.jsp?pai=acrecvalvar&expr=' + encode('<%=expressao%>') + '&var=' + document.acRecValVar.variavel.value + '&agente=' + document.acRecValVar.agente.value;
                }
            }
            //-->
        </script>
    </head>

    <body>

        <div align="center">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="4"><div align="center" class="style38">Recuperar Valor de Vari&aacute;vel</div></td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
 
                </tr>
                <tr>
                    <td height="15" colspan="4"><div align="center">
                        <form action="controlador.ControladorWebCCA" method="post" name="acRecValVar" id="acRecValVar" onSubmit="MM_validateForm('expressao','','R');return document.MM_returnValue">
                            <table width="500" border="1" bgcolor="#BBD0BF">
                                <tr>
                                    <td height="125"><div align="center">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Vari&aacute;vel</div></td>
                                                <td width="200"><div align="left">
                                                    <select name="variavel" id="var">
                                                        <%for(int i=0; i<vars1.size(); i++){
                                                            Variavel var1 = (Variavel)vars1.get(i);
                                                            if (var1.getIdVariavel().intValue() == Integer.parseInt(idVariavel)) {%>
                                                                <option selected value="<%= var1.getIdVariavel() %>"> <%= var1.getNome() %></option>
                                                            <% } else { %>
                                                                <option value="<%= var1.getIdVariavel() %>"> <%= var1.getNome() %></option>
                                                        <%}}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                            <tr>
                                              <td>Tipo</td>
                                              <td colspan="2">
                                                <select name="tipo">
                                                  <option selected value="1" rel="none" <% if (idAgente.equals("-1")) { %> selected <% } %>>
                                                        Express&atilde;o Geral</option>
                                                  <% if(agentes.size() > 0) { %>
                                                    <option value="2" rel="ag" <% if (!idAgente.equals("-1")) { %> selected <% } %>>
                                                        Express&atilde;o envolvendo vari&aacute;veis locais de algum agente</option>
                                                  <%}%>
                                                </select>											  </td>
                                            </tr>
                                            <tr rel="ag">
                                              <td>Agente</td>
                                              <td colspan="2">
                                                <select name="agente">
                                                    <%for (int i=0; i<agentes.size(); i++) {
                                                        Agente ag = (Agente) agentes.get(i); 
                                                        if (ag.getIdAgente().intValue() == Integer.parseInt(idAgente)) { %>
                                                            <option selected value="<%= ag.getIdAgente()%>"><%=ag.getNome()%>
                                                        <%} else { %>
                                                            <option value="<%= ag.getIdAgente()%>"><%=ag.getNome()%>
                                                    <%}}%>
                                                </select>                                              </td>
                                            </tr>
                                            <tr>
                                                <td>Express&atilde;o</td>
                                                <td colspan="2"><textarea name="expressao" cols="30" rows="3" id="expressao" readonly="readonly"><%=expressao%></textarea></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" bgcolor="#333300"><div align="center" class="style34"><a href="javascript:expressao()">Editar Express&atilde;o</a></div></td>                                                
                                            </tr>
                                            <tr>
                                                <td colspan="2">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="2"><div align="center">
                                                    <input name="salvar condicao" type="submit" id="salvar condicao" value="Salvar Ação">
                                                    <input name="metodo" type="hidden" value="cadastroAcao">
                                                    <input name="acao" type="hidden" value="incluir">
                                                    <input name="TipoAcao" type="hidden" id="TipoAcao" value="RecValVar">
                                                </div></td>
                                            </tr>
                                            <!--<tr>
                                                <td><div align="center">
                                                    <input name="metodo" type="hidden" value="cadastroAcao">
                                                    <input name="acao" type="hidden" value="incluir">
                                                    <input name="TipoAcao" type="hidden" id="TipoAcao" value="RecValVar">
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td><div align="center"><span class="style5"><%= status %></span></div></td>
                                            </tr>-->
                                        </table>
                                    </div></td>
                                </tr>
                            </table>
                        </form>
                    </div></td>
                </tr>
            </table>
        </div>
    </body>
</html>
<%}%>
