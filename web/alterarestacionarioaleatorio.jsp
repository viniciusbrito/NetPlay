<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String status = "";
    String nome = "";
    String cor = "";
    String grauDispersao = "";
    String pos = "";
    String nomeAntigo = "";
    int idAgente;
    idAgente = Integer.parseInt(request.getParameter("idAgente"));
    grauDispersao = request.getParameter("grauDispersao");
    nome = request.getParameter("nome");
    nomeAntigo = nome;
    cor = request.getParameter("cor");            
    status = request.getParameter("status");
    pos = request.getParameter("pos");

    if(status == null)
        status = "";
    if(grauDispersao == null)
        grauDispersao = "";



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
            .style1 {color: #DCE7DE}
            .style5 {color: #FF0000}
            a {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 12px;
            color: #DCE7DE;
            font-weight: bold;
            }
            a:visited {
            color: #DCE7DE;
            text-decoration: none;
            }
            a:active {
            color: #DCE7DE;
            text-decoration: none;
            }
            a:link {
            text-decoration: none;
            }
            a:hover {
            text-decoration: none;
            color: #A8C4AD;
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
            if (val) { nm=val.id; if ((val=val.value)!="") {
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
                    <td colspan="4"><div align="center" class="style38">Altera&ccedil;&atilde;o Agente Est&aacute;cionario</div></td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
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
                                            <td><form action="controlador.ControladorWeb" method="post" name="aleatorio" id="aleatorio" onSubmit="MM_validateForm('Nome','','R','Grau de Dispersao','','RinRange0:100');return document.MM_returnValue">
                                                <table width="430" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td height="20"><div align="center">
                                                        </div></td>
                                                    </tr>
                                                    <tr>
                                                        <td><div align="center">
                                                            <table width="430" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                                                <tr>
                                                                    <td width="186"><div align="left"> &nbsp;Nome</div></td>
                                                                    <td colspan="2"><div align="left">
                                                                        <input name="Nome" type="text" id="Nome" value="<%=nome%>" size="40">
                                                                    </div></td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="186">
                                                                    <div align="left">&nbsp;Cor</div></td>
                                                                    <td width="155"><div align="left">
                                                                        <select name="cor" id="cor" onchange="imagemCor.src='cores/' + this.value + '.jpg'">
                                                                            <% int teste = 0;
                                                                                if(cor.compareTo("VERDELIMAO")==0){%>
                                                                            <option selected value= <%=cor%>>VERDE LIMAO</option>
                                                                            <%teste = 1;
                                                                                    }%>
                                                                            <%if(cor.compareTo("AZULCLARO")==0){%>
                                                                            <option selected value= <%=cor%>>AZUL CLARO</option>
                                                                            <%teste = 1;
                                                                                }%>
                                                                            <%if(cor.compareTo("VERDETURQUESA")==0){%>
                                                                            <option selected value= <%=cor%>>VERDE TURQUESA</option>
                                                                            <%teste = 1;
                                                                                }%>
                                                                            <%if(cor.compareTo("MAGENTA2")==0){%>
										<option  selected value="<%=cor%>">MAGENTA</option>
										<%teste = 1;
									    }%>
                                                                            <%if(teste==0){%>
                                                                            <option selected value= <%=cor%>> <%=cor%></option>
                                                                            <%}%>
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
                                                                            <% if (cor.equals("")) { %>
                                                                                <td width="121"><img name="imagemCor" src="cores/AMARELO.jpg" align="center" width="70" height="20"/></td>
                                                                            <% } else { %>
                                                                                <td width="121"><img name="imagemCor" src="<%="cores/" + cor + ".jpg"%>" align="center" width="70" height="20"/></td>
                                                                            <% } %>
                                                                        </tr>
                                                                    </table></td>
                                                                </tr>
                                                                <%if(pos.compareTo("aleatorio")==0){%>
                                                                <tr>
                                                                    <td width="186">
                                                                    <div align="left">&nbsp;Grau de Dispers&atilde;o (%) </div></td>
                                                                    <td colspan="2">
                                                                    <div align="left">
                                                                        <input name="grauDispersao" type="text" id="Grau de Dispersao" value="<%=grauDispersao%>" size="10">
                                                                    </div></td>
                                                                </tr>
                                                                <%}%>  	
                                                                <tr>
                                                                    <td colspan="3"><div align="center">
                                                                        <table width="430" border="0" cellpadding="0" cellspacing="0">
                                                                            <tr>
                                                                                <th height="20" scope="row">Posicionamento Inicial</th>
                                                                            </tr>
                                                                            <tr>
                                                                                <th scope="row"><table width="430" border="1" cellpadding="0" cellspacing="0">
                                                                                    <tr>
                                                                                        <th width="116" scope="row">&nbsp;</th>
                                                                                        <th width="190" scope="row"><table width="190" height="20" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                                                            <tr>
                                                                                                <td width="20"><div align="center">
                                                                                                    <%if(pos.compareTo("aleatorio")==0){%>
                                                                                                    <input name="posicao" type="radio" onclick="window.location.href = 'alterarestacionarioaleatorio.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&idAgente=' + idAgente.value + '&pos=aleatorio'" value="aleatorio" checked>
                                                                                                    <%}else{%> 
                                                                                                    <input name="posicao" type="radio" onclick="window.location.href = 'alterarestacionarioaleatorio.jsp?nome='+ Nome.value + '&cor=' + cor.value  + '&idAgente=' + idAgente.value + '&pos=aleatorio'" value="aleatorio"> 
                                                                                                    <%}%>
                                                                                                </div></td>
                                                                                                <td width="230"><div align="center" class="style1">
                                                                                                    <div align="center" class="style41">
                                                                                                        <div align="center">Aleat&oacute;rio</div>
                                                                                                    </div>
                                                                                                </div></td>
                                                                                            </tr>
                                                                                        </table></th>
                                                                                        <td width="116">&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th scope="row">&nbsp;</th>
                                                                                        <th scope="row"><table width="190" height="20" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                                                            <tr>
                                                                                                <td width="20" height="16"><div align="center">
                                                                                                    <%if(pos.compareTo("posicaoInicial")==0){%>
                                                                                                    <input name="posicao" type="radio" onclick="window.location.href = 'alterarestacionarioaleatorio.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&idAgente=' + idAgente.value + '&pos=posicaoInicial'" value="posicaoInicial" checked>
                                                                                                    <%}else{%>
                                                                                                    <input name="posicao" type="radio" onclick="window.location.href = 'alterarestacionarioaleatorio.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&idAgente=' + idAgente.value + '&pos=posicaoInicial'" value="posicaoInicial" >
                                                                                                    <%}%>
                                                                                                </div></td>
                                                                                                <td width="230"><div align="center" class="style1">
                                                                                                    <div align="center" class="style41">Posi&ccedil;&atilde;o Inicial Espec&iacute;fica</div>
                                                                                                </div></td>
                                                                                            </tr>
                                                                                        </table></th>
                                                                                        <td>&nbsp;</td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <th colspan="3" scope="row">
                                                                                        </th>
                                                                                    </tr>
                                                                                </table></th>
                                                                            </tr>
                                                                        </table>
                                                                    </div></td>
                                                                </tr>
                                                                <%if(pos.compareTo("posicaoInicial")==0){%>
                                                                <tr>
                                                                    <td height="10" colspan="3">
                                                                        <table width="120" height="20" border="0" align="center" cellpadding="0" cellspacing="0">
                                                                            <tr>
                                                                                <!--<td bgcolor="#333300"><div align="center" class="style1" onclick="window.location.href = 'selecionar_posicoes.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&idAgente=' + idAgente.value + '&acao=' + acao.value" ><a href="#">Escolher Posi&ccedil;&otilde;es </a></div></td>-->
                                                                                <td bgcolor="#333300"><div align="center" class="style1"><a href="javascript:window.location = 'selecionar_posicoes.jsp?nome='+ document.aleatorio.Nome.value + '&cor=' + document.aleatorio.cor.value + '&idAgente=' + document.aleatorio.idAgente.value + '&acao=' + document.aleatorio.acao.value">Escolher Posi&ccedil;&otilde;es </a></div></td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                                <%}%>
                                                                <tr>
                                                                    <td height="10" colspan="3">&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                    <td colspan="3"><div align="center">
                                                                        <input name="alteraragente" type="submit" id="salvaragente2" value="Alterar Agente">
                                                                    </div></td>
                                                                </tr>
                                                            </table>
                                                        </div></td>
                                                    </tr>
                                                    <tr>
                                                        <td><div align="center">
                                                            <input name="metodo" type="hidden" value="cadastroAgente">
                                                            <input name="acao" type="hidden" value="alterar">
                                                            <input name="tipoAgente" type="hidden" id="tipoAgente" value="Estacionario Aleatorio">
                                                            <input name="nomeAntigo" type="hidden" id="nomeAntigo" value="<%= nomeAntigo%>">
                                                            <input name="idAgente" type="hidden" id="idAgente" value="<%=idAgente%>">
                                                        </div></td>
                                                    </tr>
                                                    <tr>
                                                        <td><div align="center"><span class="style5"><%= status %></span></div></td>
                                                    </tr>
                                                </table>
                                            </form></td>
                                        </tr>
                                        <tr>
                                            <td width="430" height="10"><div align="right">
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
    </body>
</html>
