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
        String nome = "";
        String op = "";
        String status = "";

        status = request.getParameter("status");
        nome = request.getParameter("nome");
        op = request.getParameter("op");

        if(status == null)
            status = " ";

        if( nome == null)
            nome = " ";

        if( op == null)
            op = " ";
%>

<html>
    <head>
        <title>NETPLAY</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            <!--
            .style38 {font-size: 14px}
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
            .style41 {color: #009900}
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
            //-->
        </script>
    </head>

    <body>
        <div align="center">
            <form action="controlador.ControladorWebCCA" method="post" name="incComp" id="incComp" onSubmit="MM_validateForm('nome','','R');return document.MM_returnValue">
                <table border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <th width="480" colspan="2" scope="col"><div align="center" class="style38">Inclus&atilde;o de Composi&ccedil;&otilde;es </div></th>
                    </tr>
                    <tr>
                        <th colspan="2" scope="col">&nbsp;</th>
                    </tr>
                    <tr>
                        <td colspan="2"><table width="480" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <th width="82" scope="col"><div align="center">Composi&ccedil;&atilde;o:</div></th>
                                <th width="240" scope="col"><input name="nome" type="text" class="style38" id="nome" size="40" maxlength="30"></th>
                                <th width="110" scope="col">Operador L&oacute;gico: </th>
                                <th width="48" scope="col"><select name="op">
                                    <option value="e">e</option>
                                    <option value="ou">ou</option>
                                </select></th>
                            </tr>
                        </table>
                        <div align="center"></div></td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center">
                            <input name="salvarCond" type="submit" id="salvarCond" value="Salvar Composi&ccedil;&atilde;o">
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center">
                            <input name="metodo" type="hidden" value="cadastroComposicao">
                            <input name="acao" type="hidden" value="incluir">
                            <input name="Composicao" type="hidden" value="<%=nome%>">
                            <input name="tipo" type="hidden" value="composicao">
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center"><span class="style5"><%= status %></span></div></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>