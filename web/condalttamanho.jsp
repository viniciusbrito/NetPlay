<%-- 
    Document   : condtamanho
    Created on : 03/01/2015, 14:44:11
    Author     : vinicius
--%>

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
   
   int idComposicao = Integer.parseInt(request.getParameter("idComposicao"));
   int idCondicao = Integer.parseInt(request.getParameter("idCondicao"));
   int idAgente = Integer.parseInt(request.getParameter("agente"));
   String operador = request.getParameter("operador");
   int valor = Integer.parseInt(request.getParameter("valor"));
    
   List agentes;
   Modelo m1 = (Modelo)session.getAttribute("modelo");
   agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
   
    Boolean flag = false;
    for(int i = 0; i < agentes.size(); i++)
    {
        Agente a = (Agente)agentes.get(i);
        if(a.getTipo().equals("Movel"))
            flag = true;
    }
    //Verificando se existem agentes no modelo
   if (flag == false)
   { %>
       <p>
        N&atilde;o &eacute; poss&iacute;vel criar uma perep&ccedil;&atilde;o devido a inexist&ecirc;ncia de agentes nesse modelo.
       </p>
   <% } else { 
       
    //Verificando o agente principal
    Agente agente = null;
    List condicoes = AplCadastrarCondicao.condicoesDaComposicao(idComposicao);
    for (int i=0; i<condicoes.size(); i++)
    {
        Condicao condicao = (Condicao) condicoes.get(i);
        if(condicao.getTipo().equals("Per"))
        {
            Percepcao percepcao = (Percepcao) condicao;
            if(percepcao.getAgentePrincipal() != null)
            {
                agente = AplCadastrarAgente.obterAgente(percepcao.getAgentePrincipal().getIdAgente());
            }
            break;
        }
        if(condicao.getTipo().equals("Pos"))
        {
            Posicionamento pos = (Posicionamento) condicao;
            if(pos.getAgentePrincipal() != null)
            {
                agente = AplCadastrarAgente.obterAgente(pos.getAgentePrincipal().getIdAgente());
                break;
            }
        }
        if(condicao.getTipo().equals("Tam"))
        {
            Tamanho tam = (Tamanho) condicao;
            if(tam.getAgente() != null)
            {
                agente = AplCadastrarAgente.obterAgente(tam.getAgente().getIdAgente());
                break;
            }
        }
    }
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
    } } } else if (test.charAt(0) == 'R') errors += '- '+nm+' é obrigatorio.\n'; }
  } if (errors) alert('The following error(s) occurred:\n'+errors);
  document.MM_returnValue = (errors == '');
}
//-->
</script>
    </head>

    <body>

        <div align="center">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="4"><div align="center" class="style38">Inclus&atilde;o de Condi&ccedil;&atilde;o de Tamanho </div></td>
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
                                            <td><form action="controlador.ControladorWebCCA" method="post" name="cperc" id="cperc" onSubmit="MM_validateForm('valor','','R');return document.MM_returnValue">
                                                <table width="380" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td width="380" height="10" valign="top">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td><div align="center">
                                                            <table width="307" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                                                <tr>
                                                                    <td width="100">
                                                                    <div align="left">Agente</div></td>
                                                                    <td width="201"><div align="left">
                                                                        <select name="agente" id="agente">
                                                                            <%
                                                                                for(int i = 0; i < agentes.size(); i++)
                                                                                {
                                                                                    Agente a = (Agente)agentes.get(i); 
                                                                                    if(a.getTipo().equals("Movel"))
                                                                                    {
                                                                                        if(a.getIdAgente().equals(idAgente))
                                                                                        {
                                                                            %>
                                                                                            <option selected value= <%= a.getIdAgente() %>> <%= a.getNome() %></option>
                                                                            <%
                                                                                        }
                                                                                        else
                                                                                        {
                                                                            %>
                                                                                            <option value= <%= a.getIdAgente() %>> <%= a.getNome() %></option>
                                                                            <%
                                                                                        }
                                                                                    }
                                                                                }
                                                                            %>
                                                                        </select>
                                                                    </div></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Operador</td>
                                                                    <td>
                                                                        <select name="operador">
                                                                            <option <%= (operador.equals("igual") ? "selected" : " ") %> value="igual"> = </option>
                                                                            <option <%= (operador.equals("diferente") ? "selected" : " ") %> value="diferente"> != </option>
                                                                            <option <%= (operador.equals("maior") ? "selected" : " ") %> value="maior"> > </option>
                                                                            <option <%= (operador.equals("menor") ? "selected" : " ") %> value="menor"> < </option>
                                                                            <option <%= (operador.equals("maiorIgual") ? "selected" : " ") %> value="maiorIgual"> >= </option>
                                                                            <option <%= (operador.equals("menorIgual") ? "selected" : " ") %> value="menorIgual"> <= </option>
                                                                        </select>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Valor</td>
                                                                    <td>
                                                                        <input name="valor" id="valor" type="text" value="<%=valor%>"/>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td colspan="2"><div align="center">
                                                                        <input name="salvar condicao" type="submit" id="salvar condicao" value="Salvar Condi&ccedil;&atilde;o">
                                                                    </div></td>
                                                                </tr>
                                                            </table>
                                                        </div></td>
                                                    </tr>
                                                    <tr>
                                                        <td><div align="center">
                                                                <input name="idCondicao" type="hidden" value="<%=idCondicao%>"/>
                                                            <input name="metodo" type="hidden" value="cadastroCondicao">
                                                            <input name="acao" type="hidden" value="alterar">
                                                            <input name="TipoCondicao" type="hidden" id="TipoCondicao" value="Tamanho">
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