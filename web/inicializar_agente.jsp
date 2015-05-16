<%-- 
    Document   : inicializar_agente
    Created on : 04/12/2014, 02:16:37
    Author     : vinicius
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java"
import="cgt.*"
import="cdp.*" 
import="java.util.*"
errorPage=""
%>

<!DOCTYPE html>
<%
    String status = "";
    String op = " ";
    int idAgente = 0;
    String quantidade = "";
    int idIni = 0;
    
    String x = request.getParameter("del");        
    status = request.getParameter("status");
    op = request.getParameter("op");
    
    if(op != null)
    {
        if(op.equals("alterar"))
        {
            idAgente = Integer.parseInt(request.getParameter("idAgente"));
            quantidade = request.getParameter("qtd");
            idIni = Integer.parseInt(request.getParameter("idIni"));
        }        
    }
    else
    {
       op = "agente";
    }
    
    if (x != null) {
        if(x.equals("deletar"))
        {
            idIni = Integer.parseInt(request.getParameter("idIni"));
            InicializarAgente ia = InicializarAgente.obterIA(idIni);
            AplCadastrarAgente.removerIniciarAgente(ia);
            status = "Removido com sucesso!";            
        }
    }

    if(status == null)
        status = " ";
    
    List agentes;
    boolean temAgMovel = false;
    Modelo m1 = (Modelo)session.getAttribute("modelo");
    agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
    
    if(agentes.size() == 0) { %>
        <p>N&atilde;o &eacute; poss&iacute;vel criar essa a&ccedil;&atilde;o devido a inexist&eacute;ncia de agentes nesse modelo</p>
    <% } else {
    List iniAgentes = InicializarAgente.obterIAM(m1.getIdModelo());
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
        </script>
    </head>

    <body>

        <div align="center">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="4"><div align="center" class="style38">Inicializar Agente</div></td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
 
                </tr>
                <tr>
                    <td height="15" colspan="4"><div align="center">
                        <table width="500" border="1" bgcolor="#BBD0BF">
                            <tr>
                                <td height="103"><div align="center">
                                    <form action="controlador.ControladorWeb" method="post" name="acCriar" id="acCriar" onSubmit="MM_validateForm('quantidade','','R');return document.MM_returnValue">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Agente</div></td>
                                                <td width="200"><div align="left">
                                                        <%if(op.equals("alterar")) { 
                                                            Agente agente = AplCadastrarAgente.obterAgente(idAgente); %>
                                                            <input type="text" value="<%= agente.getNome() %>" readOnly="true" name="nomeAgente"/>
                                                            <input type="hidden" value="<%= agente.getIdAgente() %>" name="agente" id="agente"/>
                                                        <%}
                                                        else
                                                        { %>
                                                            <select name="agente" id="agente">
                                                            <%
                                                            for(int i=0; i<agentes.size(); i++)
                                                            {
                                                                Agente agente = (Agente)agentes.get(i);
                                                                if(agente.getTipo().equals("Movel")) { %>
                                                                    <option value= <%= agente.getIdAgente() %> > <%= agente.getNome() %></option>
                                                                <%}
                                                            }
                                                        }%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td> <div align="left">Quantidade</div></td>
                                                <td width="200"><div align="left">
                                                        <input type="text" name="quantidade" value="<%= quantidade %>" id="quantidade"/>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2"><div align="center">
                                                    <input name="salvar condicao" type="submit" id="salvar condicao" value="Escolher Agente">
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2"><div align="center">
                                                    <input name="metodo" type="hidden" value="cadastroAgente">
                                                    <% if(op.equals("alterar")) {%>
                                                        <input name="op" type="hidden" value="agenteAlterar">
                                                        <input name="idIni" type="hidden" value="<%= idIni %>">
                                                    <%} else { %>
                                                        <input name="op" type="hidden" value="agente">
                                                    <% } %>
                                                    <input name="acao" type="hidden" value="inicializar">
                                                    <input name="TipoAcao" type="hidden" id="TipoAcao" value="Criar">
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td colspan="4"><div align="center"><span class="style5"><%= status %></span></div></td>
                                            </tr>
                                             <% if(op.equals("alterar")) {%>
                                                <tr>
                                                    <td colspan="3" align="center" bgcolor="#333300"><a href="inicializar_agente.jsp?status=">Cancelar</a></td>
                                                </tr>
                                             <%}%>
                                        </table>
                                    </form>
                                </div></td>
                            </tr>
                        </table>
                    </div></td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4"><div align="center" class="style38">Agentes já programados</div></td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                <tr>
                    <table width="500" border="1" bgcolor="#BBD0BF">
                        <tr align="center"><td>Agente</td><td>Quantidade</td><td colspan="2">Operações</td></tr>
                        <% InicializarAgente ia;
                        for(int j=0; j < iniAgentes.size(); j++)
                        {
                            ia = (InicializarAgente) iniAgentes.get(j);
                        %>                    
                        <tr><td> <%= AplCadastrarAgente.obterAgente(ia.getAgente().getIdAgente()).getNome() %> </td>
                            <td> <%= ia.getQuantidade() %></td>
                            <td align="center" bgcolor="#333300"><a href="inicializar_agente.jsp?idIni=<%= ia.getIdInicializarAgente() %>&idAgente=<%= ia.getAgente().getIdAgente() %>&qtd=<%= ia.getQuantidade() %>&op=alterar&status=Altera%26ccedil%3B%26atilde%3Bo de Inicializa%26ccedil%3B%26atilde%3Bo de agente!">Alterar</a></td>
                            <td align="center" bgcolor="#333300"><a href="inicializar_agente.jsp?del=deletar&idIni=<%= ia.getIdInicializarAgente() %>">Excluir</a></td>
                        </tr>
                      <%}%>
                    </table>
                </tr>
            </table>
                                                            
        </div>
    </body>
</html>
<%}%>