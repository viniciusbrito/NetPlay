<%@ page contentType="text/html; charset=iso-8859-1"  language="java" 
import="java.sql.*" 
import="cgt.*" 
import="cdp.*" 
import="java.util.*"
import="utilitario.HibernateUtility"
import="org.hibernate.Session"
import="org.hibernate.Transaction"
import="org.hibernate.Query"
import="org.hibernate.SQLQuery"
import="org.hibernate.Criteria"
errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String id = request.getParameter("idAgente");
String nome = "";
String cor = "";
String acao = "";
int idAgente;
nome = request.getParameter("nome");
cor = request.getParameter("cor");
acao = request .getParameter("acao");
String tamanho = request.getParameter("tamanho");
String forma = request.getParameter("forma");
String qtdInicial = request.getParameter("numeroQtdInicial");
int quantidadeInicial;
String tipoQuantidadeInicial = request.getParameter("quantidadeInicial");
String tipoMovimento = request.getParameter("tipoMovimento");
String passo = "";
String anguloMinimo = "";
String anguloMaximo = "";
String anguloReflexao = ""; 

if(tipoMovimento.compareTo("Passear")==0){
	passo = request.getParameter("passo");
    anguloMinimo = request.getParameter("anguloMinimo");
    anguloMaximo = request.getParameter("anguloMaximo");
  }else{
    anguloReflexao = request.getParameter("anguloReflexao");
  }
  
if(qtdInicial=="")
	quantidadeInicial = 0;
else
	quantidadeInicial = Integer.parseInt(qtdInicial);  
   
if(id==null)
	idAgente = 0;
else
	idAgente = Integer.parseInt(id);

int totalPosicoes = 0; //indica total de posicoes ja definidas
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
function valida(quantidade,id) { //v4.01
	var idNum = parseInt(id);
	var x;
	texto = (document.formulario[idNum].value);
	num = parseInt(document.formulario[idNum].value);
	if (isNaN(num)&&texto!=""){
		alert("É obrigatório que o valor seja um valor numérico.");
		document.formulario[idNum].value = "";
	}
	else{
                total = 0;
		for (x = 1 ; x <= 2601 ; x++){ 
			if(document.formulario[x].value!=""){
				total = parseInt(total) + parseInt(document.formulario[x].value);
			}
		}
                if (texto!="" && document.formulario[idNum].value <= 0) {
                    alert('Não pode colocar um número menor ou igual a zero.');
                    document.formulario[idNum].value = "";
                }
                else 
                    if (texto!="" && total <= <%=quantidadeInicial%>)
                        document.formulario[0].value = quantidade - total;
                    else 
                        if((document.formulario[0].value == 0)&&(texto!="")){	
                            alert('Não existem mais agentes para serem inseridos.');
                            document.formulario[0].value = -(total - document.formulario[idNum].value - <%=quantidadeInicial%>);
                            document.formulario[idNum].value = "";
                        }
                        else{
                            if (document.formulario[0].value < 0){
                                alert('Favor retirar alguns agentes.');
                                document.formulario[0].value = -(total - document.formulario[idNum].value - <%=quantidadeInicial%>);
                                document.formulario[idNum].value = "";
                            }
                            else
                                if(document.formulario[0].value - document.formulario[idNum].value < 0){
                                    alert(' O número de agentes que pode ser inserido no momento não pode ser maior que '+ document.formulario[0].value);
                                    document.formulario[0].value = -(total - document.formulario[idNum].value - <%=quantidadeInicial%>);
                                    document.formulario[idNum].value = "";
                                }
                        }
	}
}

function validaTotal(valor){
	errors = '';
        if(valor>0){
		errors = 'Ainda restam ' + valor + ' agente(s) para ser(em) inserido(s).';
	}
	if (errors) 
		alert(' '+errors);
	document.MM_returnValue = (errors == '');
}
    
	
//-->
</script>
</head>
<body>
<div align="center">
  <table width="525" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="15"><table width="525" height="20" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="20"><div align="center">
                </div>              <div align="center"></div></td>
          </tr>
      </table></td>
    </tr>
    <tr>
      <td height="15">&nbsp;</td>
    </tr>
    <tr>
      <td height="15"><div align="center">
          <table width="525" border="1" bgcolor="#BBD0BF">
            <tr>
              <td><div align="center">
                <table width="430" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td>
                    <form action="controlador.ControladorWeb" method="post" name="formulario" target="principal" onSubmit="validaTotal(textfield.value);return document.MM_returnValue">
                        <table width="430" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="450" height="20"><div align="left">N&uacute;mero de Agentes que restam para serem inseridos
							<input name="textfield" type="text" value="<%=quantidadeInicial%>"  readonly="" size="3" maxlength="3">
                            </div></td>
                            <td width="904"><div align="center">POSICIONAMENTO DOS AGENTES NA TELA </div></td>
                            <td width="450">&nbsp;</td>
                          </tr>
                          <tr>
                            <td colspan="3"><div align="center">
                                  <table width="1800" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                    <tr>
                                      <td><div align="center"></div>
                                          <div align="left">
                                            <table width="1800" border="1" align="center" cellpadding="0" cellspacing="0">
                                              <tr>
                                                <th width="21" scope="row">&nbsp;</th>
                                                <% 
                                                  List posicoes = (List) session.getAttribute("posicoesMovel");
                                                  if (posicoes == null)
                                                      posicoes = AplCadastrarAgente.posicoesAgente(idAgente);
                                                  
                                                  int pos=0;
                                                  int tamanhoLista = posicoes.size();
                                                  for(int a=-25; a<26; a++){%>
                                                <td width="20"><div align="center"><%=a%></div></td>
                                                <%}%>
                                                <th width="20">&nbsp;</th>
                                              </tr>
                                              <% String text="";
												String linha= "";
												String coluna="";
												int numId = 0;
											int k = 25;
											for(int i=0; i<51; i++){
												if(i<10)
													linha = "0" + i;
												else
													linha = "" + i;
											%>
                                              <tr>
                                                <th width="20" scope="row"><div align="center"><%=k%></div></th>
                                                <%for(int j=0; j<51; j++){
                                                                                                        numId++;
											  		if(j<10)
														coluna = "0" + j;
													else
														coluna = "" + j;
											     	text = "text" + linha + coluna;	
                                                    if(pos< tamanhoLista){
                                                   PosicaoInicial posicao = (PosicaoInicial)posicoes.get(pos);                                                   
                                                  if((posicao.getXInicial().intValue()+25 == j)&&(-(posicao.getYInicial().intValue()-25) == i)){                                                   pos++;
											  %>
                                                <td><input name=<%=text%> type="text" size="1" maxlength="3" id = "<%=numId%>" title="Posi&ccedil;&atilde;o (<%=j-25%>, <%=25-i%>)" value=<%=posicao.getQuantidadePosicao()%> onChange="valida(textfield2.value,id)"></td>
                                                <%totalPosicoes += posicao.getQuantidadePosicao(); %>
                                                <%}else{%>
                                                <td><input name=<%=text%> type="text" size="1" maxlength="3" id = "<%=numId%>" title="Posi&ccedil;&atilde;o (<%=j-25%>, <%=25-i%>)" onChange="valida(textfield2.value,id)"></td>
                                                <%}}else{%>
                                                <td><input name=<%=text%> type="text" size="1" maxlength="3" id = "<%=numId%>" title="Posi&ccedil;&atilde;o (<%=j-25%>, <%=25-i%>)" onChange="valida(textfield2.value,id)"></td>
                                                <% }} //for das colunas%>
                                                <th width="20" scope="row"><div align="center"><%=k%></div></th>
                                                <%k--;%>
                                              </tr>
                                              <%}//for das linhas%>
                                              <tr>
                                                <th width="21" scope="row">&nbsp;</th>
                                                <%for(int a=-25; a<26; a++){%>
                                                <td width="20"><div align="center"><%=a%></div></td>
                                                <%}%>
                                                <th width="20">&nbsp;</th>
                                              </tr>
                                              <script>document.formulario.textfield.value="<%=quantidadeInicial - totalPosicoes%>"</script>
                                            </table>
   								          <div align="center">
   								            <input name="textfield2" type="hidden" value="<%=quantidadeInicial%>"  readonly="" size="3" maxlength="3">
                                              <input name="anguloReflexao" type="hidden" id="anguloReflexao" value="<%=anguloReflexao%>">
                                              <input name="anguloMinimo" type="hidden" id="anguloMinimo" value="<%=anguloMinimo%>">
                                              <input name="anguloMaximo" type="hidden" id="anguloMaximo" value="<%=anguloMaximo%>">
                                              <input name="passo" type="hidden" id="passo" value="<%=passo%>">
                                              <input name="tipoMovimento" type="hidden" id="nome" value="<%=tipoMovimento%>">
                                              <input name="nome" type="hidden" id="nome" value="<%=nome%>">
                                              <input name="retornar" type="submit" id="confirmar2" value="Retornar" onClick="javascript: history.go(-1)">
                                              <input name="metodo" type="hidden" value="posicaoAgenteMovel">
                                              <input name="confirmar" type="submit" id="confirmar" value="Confirmar Posicionamento">  
                                              <input name="cor" type="hidden" id="cor" value="<%=cor%>">
                                              <input name="acao" type="hidden" id="acao" value="<%=acao%>">
                                              <input name="idAgente" type="hidden" id="idAgente" value="<%=idAgente%>">
                                              <input name="tamanho" type="hidden" id="tamanho" value="<%=tamanho%>">
                                              <input name="forma" type="hidden" id="forma" value="<%=forma%>">
                                              <input name="numeroQtdInicial"  type="hidden" id="numeroQtdInicial" value="<%=qtdInicial%>">
                                              <input name="quantidadeInicial" type="hidden" id="quantidadeInicial" value="<%=tipoQuantidadeInicial%>">
										  </div>
                                          </div>
                                          <div align="left"> </div>
                                          <div align="left"></div>
                                          <div align="left"></div></td>
                                    </tr>
                                    <tr>
                                      <td><div align="center"> </div></td>
                                    </tr>
                                 </table>
			                 </div></td>
                          </tr>
                      </table></form></td>
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
