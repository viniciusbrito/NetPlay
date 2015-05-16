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
import="interfaceNetLogo.Traducao"
errorPage="" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
        String nome = "";
        String op = "";
        String status = "";
        Composicao a = (Composicao)session.getAttribute("composicao");

        status = request.getParameter("status");
        nome = request.getParameter("nome");
        op = request.getParameter("op");

        if(status == null)
            status = " ";

        if(nome == null)
            nome = a.getNome();

        if(op == null)
            op = a.getOperador();
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
            .style42 {color: #DCE7DE}
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
            } } } else if (test.charAt(0) == 'R') errors += '- '+nm+' is required.\n'; }
            } if (errors) alert('The following error(s) occurred:\n'+errors);
            document.MM_returnValue = (errors == '');
            }

            function MM_swapImgRestore() { //v3.0
            var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
            }

            function MM_preloadImages() { //v3.0
            var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
            var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
            if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
            }

            function MM_swapImage() { //v3.0
            var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
            if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
            }
            //-->
        </script>
    </head>

    <body onLoad="MM_preloadImages('imagens/del2.JPG')"> 
        <div align="center">
            <form action="controlador.ControladorWebCCA" method="post" name="incComp" id="incComp" onSubmit="MM_validateForm('nome','','R');return document.MM_returnValue">
                <table border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <th colspan="2" scope="col"><div align="center" class="style38">Inclus&atilde;o de Condi&ccedil;&otilde;es e A&ccedil;&otilde;es </div></th>
                    </tr>
                    <tr>
                        <th colspan="2" scope="col">&nbsp;</th>
                    </tr>
                    <tr>
                        <td colspan="2"><table width="480" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <th width="82" scope="col"><div align="center">Composi&ccedil;&atilde;o:</div></th>
                                <th width="240" scope="col"><input name="nome" type="text" class="style38" id="nome" size="40" maxlength="30" value="<%=nome%>" disabled></th>
                                <th width="110" scope="col">Operador L&oacute;gico: </th>
                                <th width="48" scope="col"><select name="op" disabled>
                                    <option selected="<%=op%>"><%=op%></option>
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
                        <td width="240"><div align="center">
                            <table width="210" border="1" cellpadding="1" cellspacing="1">
                                <tr>
                                    <td colspan="2"><div align="center">Condi&ccedil;&otilde;es</div></td>
                                </tr>
                                <%  Modelo m = (Modelo)session.getAttribute("modelo");
                                    String msg = "";
                                    if (nome == null)
                                        a = (Composicao)session.getAttribute("composicao");
                                    else
                                        a = AplCadastrarComposicao.obterComposicao(m.getIdModelo().intValue(), nome);
                                    List Conds = AplCadastrarCondicao.condicoesDaComposicao(a.getIdComposicao().intValue());
                                    if (Conds.size() == 0) { %>
                                <tr>
                                    <td width="15"><div align="right">&nbsp;</div></td>
                                    <td width="182"><div align="center" class="style42">Nenhuma Condi&ccedil;&atilde;o</div></td>
                                </tr>
                                <% } else {
                                            for(int i=0; i<Conds.size(); i++) {
                                                Condicao con = (Condicao)Conds.get(i);
                                                
                                                Session sessao = HibernateUtility.getSession();//Abrindo uma sessao
                                                Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
                                                
                                                //GAMBIARRA - procurando condicao no BD
                                                Query consulta = sessao.getNamedQuery("BuscarCondicaoPeloId");
                                                consulta.setParameter("idCondicao", con.getIdCondicao());
                                                Condicao condicao = (Condicao) consulta.list().get(0);%>
                                <tr>
                                    <%  String t = con.getTipo();
                                        if ( t.compareTo("Per") == 0 ) {
                                            Percepcao condPer = (Percepcao) condicao;
                                            msg = "Percep&ccedil;&atilde;o<br>" + condPer.getAgentePrincipal().getNome() +
                                                    " - " + condPer.getAgente().getNome() + 
                                                    "<br>Dist&acirc;ncia = " + condPer.getDistancia().intValue();%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="condconperc.jsp?id=<%=con.getIdCondicao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                    <%  }
                                        if ( t.compareTo("Pos") == 0 ) { 
                                            Posicionamento condPos = (Posicionamento) condicao;
                                            msg = "Posicionamento<br>" + condPos.getAgente().getNome();
                                            if(condPos.getAgentePrincipal() != null)
                                                msg += " - " + condPos.getAgentePrincipal().getNome();%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="condconpos.jsp?id=<%=con.getIdCondicao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                    <%  }
                                        if ( t.compareTo("CmpVar") == 0 ) {  
                                            CmpVariavel condCmpVar = (CmpVariavel) condicao; 
                                            msg = "Compara&ccedil;&atilde;o de Vari&aacute;veis<br>" +
                                                    condCmpVar.getVar1().getNome() + " - " + 
                                                    condCmpVar.getVar2().getNome();
                                            String expr = java.net.URLEncoder.encode(condCmpVar.getExpressao(), "utf-8");%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="condconcmpvar.jsp?id=<%=con.getIdCondicao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                    <%  }
                                        if ( t.compareTo("ValVar") == 0 ) {
                                            ValorVariavel condValVar = (ValorVariavel) condicao;
                                            msg = "Valor de Vari&aacute;vel<br>" + 
                                                    condValVar.getVar().getNome();
                                            String expr = java.net.URLEncoder.encode(condValVar.getExpr(), "utf-8");%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="condconvalvar.jsp?id=<%=con.getIdCondicao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                    <%  }
                                        if ( t.compareTo("CorAg") == 0 ) { 
                                            CorAgente condCor = (CorAgente) condicao;
                                            msg = "Cor do Agente<br>" + condCor.getAgente().getNome() +
                                                    "<br>" + Traducao.corPort(condCor.getCor());%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="condconcorag.jsp?id=<%=con.getIdCondicao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                </tr>
                                <% }
                                    transacao.commit(); //fechando transacao
                                    sessao.close(); //fechando sessao do hibernate
                                }} %>
                            </table>
                        </div></td>
                        <td width="240"><div align="center">
                            <table border="1" cellspacing="1" cellpadding="1">
                                <tr>
                                    <td colspan="2"><div align="center">A&ccedil;&otilde;es</div></td>
                                </tr>
                                <%  List Acts = AplCadastrarAcao.acoesDaComposicao(a.getIdComposicao().intValue());
                                    if (Acts.size() == 0) { %>
                                <tr>
                                    <td width="15"><div align="right">&nbsp;</div></td>
                                    <td width="182"><div align="center" class="style42">Nenhuma A&ccedil;&atilde;o </div></td>
                                </tr>
                                <% } else {
                                        for(int i=0; i<Acts.size(); i++) {
                                            Acao ac = (Acao)Acts.get(i);
                                            
                                            Session sessao = HibernateUtility.getSession();//Abrindo uma sessao
                                            Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
                                            
                                            //GAMBIARRA - procurando acao no BD
                                            Query consulta = sessao.getNamedQuery("BuscarAcaoPeloId");
                                            consulta.setParameter("idAcao", ac.getIdAcao());
                                            Acao acao = (Acao) consulta.list().get(0);%>
                                <tr>
                                    <%  String t = ac.getTipo();
                                        if ( t.compareTo("Criar") == 0 ) {
                                            GerarNovoObjeto acaoCriar = (GerarNovoObjeto) acao;
                                            msg = "Criar Elemento<br>" + acaoCriar.getAgente().getNome();%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="acao_con_criar.jsp?id=<%=ac.getIdAcao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                    <%  }
                                        if ( t.compareTo("Destruir") == 0 ) {
                                            Destruir acaoDestruir = (Destruir) acao;
                                            msg = "Destruir Elemento<br>" + acaoDestruir.getAgente().getNome();%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="acao_con_destruir.jsp?id=<%=ac.getIdAcao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                    <%  }
                                            if ( t.compareTo("AltCor") == 0 ) {
                                                AlterarCor acaoAltCor = (AlterarCor) acao;
                                                msg = "Alterar Cor<br>" + acaoAltCor.getAgente().getNome() + 
                                                        "<br>" + Traducao.corPort(acaoAltCor.getNovaCor());%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="acao_con_altcor.jsp?id=<%=ac.getIdAcao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                    <%  }
                                            if ( t.compareTo("AltFor") == 0 ) {
                                                AlterarForma condAltForm = (AlterarForma) acao;
                                                msg = "Alterar Forma<br>" + condAltForm.getAgenteMovel().getNome() + 
                                                        "<br>" + condAltForm.getNovaFormaHTML();%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="acao_con_altforma.jsp?id=<%=ac.getIdAcao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                    <%  }
                                            if ( t.compareTo("AltMov") == 0 ) {
                                                AlterarMovimento condAltMov = (AlterarMovimento) acao;
                                                msg = "Alterar Movimento<br>" + 
                                                        condAltMov.getAgenteMovel().getNome();%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="acao_con_altmov.jsp?id=<%=ac.getIdAcao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                    <%  }
                                            if ( t.compareTo("PararMov") == 0 ) {
                                                PararMovimento condPararMov = (PararMovimento) acao;
                                                msg = "Parar Movimento<br>" + 
                                                        condPararMov.getAgenteMovel().getNome();%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="acao_con_pararmov.jsp?id=<%=ac.getIdAcao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                    <%  }
                                            if ( t.compareTo("AltPos") == 0 ) {
                                                AlterarPosicao condAltPos = (AlterarPosicao) acao;
                                                msg = "Alterar Posi&ccedil;&atilde;o<br>" +
                                                        condAltPos.getAgenteMovel().getNome() + "<br>" +
                                                        "( " + condAltPos.getNovoX() + " , " +
                                                        condAltPos.getNovoY() + " )";%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="acao_con_altpos.jsp?id=<%=ac.getIdAcao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                    <%  }
                                            if ( t.compareTo("IncValVar") == 0 ) {
                                                IncrementarVariavel condIncVar = (IncrementarVariavel) acao;
                                                float valor = condIncVar.getIncremento().floatValue();
                                                if (valor < 0)
                                                    msg = "Decrementar";
                                                else
                                                    msg = "Incrementar";
                                                msg = msg + " Vari&aacute;vel<br>" + 
                                                        condIncVar.getVariavel().getNome() + 
                                                        "<br>Valor = " + valor;%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="acao_con_incvalvar.jsp?id=<%=ac.getIdAcao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                    <%  }
                                            if ( t.compareTo("RecValVar") == 0 ) {
                                                RecuperarValorVariavel condRecVar = (RecuperarValorVariavel) acao;
                                                msg = "Recuperar Valor Vari&aacute;vel<br>" +
                                                        condRecVar.getVariavel().getNome();%>
                                    <td width="182" bgcolor="#333300"><div align="center"><a href="acao_con_recvalvar.jsp?id=<%=ac.getIdAcao()%>&tipo=<%=t%>" class="style42"><%=msg%></a></div></td>
                                </tr>
                                <% }}} %>
                            </table>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center">
                            <input name="metodo" type="hidden" value="cadastroComposicao">
                            <input name="acao" type="hidden" value="consultar">
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center" class="style42"><%= status %></div></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>