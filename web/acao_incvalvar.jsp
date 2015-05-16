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
    
    List vars1;
    boolean temVarDifLog = false;
    Modelo m1 = (Modelo)session.getAttribute("modelo");
    vars1 = AplCadastrarVariavel.variaveisDoModelo(m1.getIdModelo().intValue());
    
    for(int i=0; i<vars1.size(); i++)
    {
        Variavel var1 = (Variavel)vars1.get(i);
        if (var1.getTipo().compareTo("Logica") != 0)
            temVarDifLog = true;
    }
    
    if(!temVarDifLog) { %>
        <p>N&atilde;o &eacute; poss&iacute;vel criar essa a&ccedil;&atilde;o devido a inexist&eacute;ncia de vari&aacute;veis sliders e valor nesse modelo</p>
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
            if (isNaN(val)) errors+='- Valor deve ser um numero.\n';
            if (test.indexOf('inRange') != -1) { p=test.indexOf(':');
            min=test.substring(8,p); max=test.substring(p+1);
            if (num<min || max<num) errors+='- '+nm+' must contain a number between '+min+' and '+max+'.\n';
            } } } else if (test.charAt(0) == 'R') errors += '- Valor eh requerido.\n'; }
            } 
            
            
            if (errors) alert('Erros:\n'+errors);
            document.MM_returnValue = (errors == '');
            }
            //-->
        </script>
    </head>

    <body>

        <div align="center">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="4"><div align="center" class="style38">Incrementar / Decrementar Valor de Vari&aacute;vel</div></td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
 
                </tr>
                <tr>
                    <td height="15" colspan="4"><div align="center">
                        <table width="500" border="1" bgcolor="#BBD0BF">
                            <tr>
                                <td height="127"><div align="center">
                                    <form action="controlador.ControladorWebCCA" method="post" name="acIncValVar" id="acIncValVar" onSubmit="MM_validateForm('inc','','RisNum');return document.MM_returnValue">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Vari&aacute;vel</div></td>
                                                <td width="200"><div align="left">
                                                    <select name="var" id="var">
                                                        <%for(int i=0; i<vars1.size(); i++){
                                                                Variavel var1 = (Variavel)vars1.get(i);
                                                                if (var1.getTipo().compareTo("Logica") != 0) {%>
                                                        <option value= <%= var1.getNome() %>> <%= var1.getNome() %></option>
                                                        <%}}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Valor</div></td>
                                                <td width="200"><div align="left">
                                                    <input name="inc" type="text" id="valor" value="1" size="10" maxlength="6">
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="2"><div align="center">
                                                    <input name="salvar condicao" type="submit" id="salvar condicao" value="Salvar Ação">
                                                </div></td>
                                            </tr>
                                            <!--<tr>
                                                <td><div align="center">
                                                    <input name="metodo" type="hidden" value="cadastroAcao">
                                                    <input name="acao" type="hidden" value="incluir">
                                                    <input name="TipoAcao" type="hidden" id="TipoAcao" value="IncValVar">
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td><div align="center"><span class="style5"><%= status %></span></div></td>
                                            </tr>-->
                                        </table>
                                        <input name="metodo" type="hidden" value="cadastroAcao">
                                        <input name="acao" type="hidden" value="incluir">
                                        <input name="TipoAcao" type="hidden" id="TipoAcao" value="IncValVar">
                                    </form>
                                </div></td>
                            </tr>
                        </table>
                    </div></td>
                </tr>
            </table>
        </div>
    </body>
</html>
<% } %>