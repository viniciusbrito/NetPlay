<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="cgt.*"%>
<%@page import="cdp.*"%>
<%@page import="java.util.List"%>

<%
String status = "";
String nome;
String cor;
String tamanho;
String forma;
String quantidadeInicial;
String numeroQtdInicial;
String pos;
String passo = "";
String anguloMinimo = "";
String anguloMaximo = "";
String totalPosicoes;
int posicao;

tamanho = request.getParameter("tamanho");
nome = request.getParameter("nome");
cor = request.getParameter("cor");
forma = request.getParameter("forma");
quantidadeInicial = request.getParameter("quantidadeInicial");
numeroQtdInicial = request.getParameter("numeroQtdInicial");
passo = request.getParameter("passo");
anguloMinimo = request.getParameter("anguloMinimo");
anguloMaximo = request.getParameter("anguloMaximo");
status = request.getParameter("status");
pos = request.getParameter("posicao");
totalPosicoes = request.getParameter("totalPosicoes"); //total de valores preenchidos na tela de posicoes especificas

//toda vez que criar um novo agente =>limpar lista de posicoes
if (request.getParameter("inicio") != null)
    session.removeAttribute("posicoesMovel");

if(status == null)
	status = "";
if(totalPosicoes == null)
    totalPosicoes = "0";
if(nome == null)
	nome = "";
if(cor == null)
	cor = "";
if(tamanho == null)
	tamanho = "";
if(forma == null)
	forma = "";
if(quantidadeInicial == null)
	quantidadeInicial = "0";
if(numeroQtdInicial == null)
	numeroQtdInicial = "";
if(passo == null)
	passo = "";
if(anguloMinimo == null)
	anguloMinimo = "";
if(anguloMaximo == null)
	anguloMaximo = "";
if(pos == null)
	posicao = 1;
else
        posicao = Integer.parseInt(pos);
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
.style41 {font-size: 11px}
-->
</style>

<script>
    function linkPosicoesEspecificas(){
        num = validaQuantidade(document.movel.numeroQtdInicial.value);
        if (num == 1)
            window.location.href = 'selecionar_posicoesMovel.jsp?nome='+ document.movel.Nome.value + '&cor=' + document.movel.cor.value + '&acao=' + document.movel.acao.value + '&tamanho=' + document.movel.Tamanho.value + '&forma=' + document.movel.Forma.value + '&quantidadeInicial=<%=quantidadeInicial%>' + '&numeroQtdInicial=' + document.movel.numeroQtdInicial.value +'&passo=' + document.movel.passo.value + '&anguloMinimo=' + document.movel.anguloMinimo.value +  '&anguloMaximo=' + document.movel.anguloMaximo.value + '&tipoMovimento=Passear'
    }
</script>

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
          if (min==max && num!=min) errors+= '- Verificar posições específicas do agente.\n';
          else if (num<min || max<num) errors+='- '+nm+' deve conter um número entre '+min+' e '+max+'.\n';
    } } } else if (test.charAt(0) == 'R') errors += '- '+nm+' é obrigatório.\n'; }
  } if (errors) alert(' '+errors);
  document.MM_returnValue = (errors == '');
}

function validaQuantidade(quantidade){
	num = parseInt(quantidade);
	if (isNaN(num)&&quantidade!=""){
		alert("É obrigatório que o valor seja um valor numérico.");
		document.movel.numeroQtdInicial.value = "";
                return 0;
	}
        else if (num < 0)
        {
                alert("É obrigatório que o valor seja um valor numérico maior que zero.");
		document.movel.numeroQtdInicial.value = "";
                return 0;
        }
        return 1;
}

function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
</head>

<body>
<div align="center"></div>
<div align="center">
  <table width="525" border="0" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
    <tr>
      <td><div align="center">
        <table width="520" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="4"><div align="center" class="style38">Inclus&atilde;o de Agente </div></td>
          </tr>
          <tr>
            <td colspan="4">&nbsp;</td>
          </tr>
          <tr>
            <td height="15" colspan="4"><table width="520" height="20" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="155" height="20"><div align="center">
                      <table width="155" height="20" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                        <tr>
                          <td width="20"><div align="center">
                              <input name="agente" type="radio" onClick="window.location.href = 'movelpassear.jsp'" value="movel" checked>
                          </div></td>
                          <td width="140"><div align="center" class="style1">
                              <div align="center" class="style41">Agente M&oacute;vel </div>
                          </div></td>
                        </tr>
                      </table>
                  </div></td>
                  <td width="5">&nbsp;</td>
                  <td width="190"><div align="center">
                      <table width="190" height="20" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                        <tr>
                          <td width="20"><div align="center">
                              <input name="agente" type="radio" value="aleatorio" onclick="window.location.href =  'estacionarioaleatorio.jsp'">
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
                            <input name="agente" type="radio" value="borda" onclick="window.location.href = 'estacionarioborda.jsp'">
                        </div></td>
                        <td width="210"><div align="center"><span class="style40">Agente Borda </span></div></td>
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
                    <table width="393" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                      <%if (posicao == 3) { %>
                        <td><form action="controlador.ControladorWeb" method="post" name="movel" id="movel" onSubmit="MM_validateForm('Nome','','R','numeroQtdInicial','','RisNum','Passo','','RisNum','Angulo Minimo','','RinRange0:360','Angulo Maximo','','RinRange0:360','numeroQtdInicial','','<%="RinRange" + totalPosicoes + ":" + totalPosicoes%>');return document.MM_returnValue">
                      <%} else { %>
                        <td><form action="controlador.ControladorWeb" method="post" name="movel" id="movel" onSubmit="MM_validateForm('Nome','','R','numeroQtdInicial','','RisNum','Passo','','RisNum','Angulo Minimo','','RinRange0:360','Angulo Maximo','','RinRange0:360');return document.MM_returnValue">
                      <%}%>
                            <table width="393" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="420">&nbsp;</td>
                              </tr>
                              <tr>
                                <td height="10"><div align="center">
                                  <table width="393" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                    <tr>
                                      <td width="202"><div align="left">Nome</div></td>
                                      <td colspan="2"><div align="left">
                                        <input name="Nome" type="text" id="Nome" value="<%=nome%>" size="35" maxlength="30">
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td><div align="left"></div>
                                          <div align="left"></div>
                                          <div align="left">Cor </div></td>
                                      <td width="128"><div align="left">
                                          <select name="cor" id="cor" onchange="imagemCor.src='cores/' + this.value + '.jpg'">
                                            <% if(cor != ""){%>
                                                                                        <% int teste = 0;
											if(cor.compareTo("VERDELIMAO")==0){%>
											<option selected value="<%=cor%>">VERDE LIMAO</option>
											<%teste = 1;
											}%>
											<%if(cor.compareTo("AZULCLARO")==0){%>
											<option  selected value="<%=cor%>">AZUL CLARO</option>
											<%teste = 1;
											}%>
											<%if(cor.compareTo("VERDETURQUESA")==0){%>
											<option selected value="<%=cor%>">VERDE TURQUESA</option>
											<%teste = 1;
											}%>
                                                                                        <%if(cor.compareTo("MAGENTA2")==0){%>
											<option  selected value="<%=cor%>">MAGENTA</option>
											<%teste = 1;
											}%>
											<%if(teste==0){%>
											<option selected value="<%=cor%>"> <%=cor%></option>
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
                                            <%}else{%>
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
                                            <%}%>
                                          </select>
                                      </div></td>
                                      <td width="80"><table width="60" height="20" border="0" align="center" cellpadding="0" cellspacing="0">
                                        <tr>
                                          <% if (cor.equals("")) { %>
                                                <td width="121"><img name="imagemCor" src="cores/AMARELO.jpg" align="center" width="70" height="20"/></td>
                                          <% } else { %>
                                                <td width="121"><img name="imagemCor" src="<%="cores/" + cor + ".jpg"%>" align="center" width="70" height="20"/></td>
                                          <% } %>
                                          <!--<td width="121" bgcolor="#333300"><div align="center" class="style1"><a href="#" onClick="MM_openBrWindow('cores.jsp','cores','width=420,height=470')">Cores</a></div></td>-->
                                        </tr>
                                      </table></td>
                                    </tr>
                                    <tr>
                                      <td><div align="left">Tamanho </div>
                                          <div align="left"></div>
                                          <div align="left"></div></td>
                                      <td colspan="2"><div align="left">
                                          <select name="Tamanho" id="Tamanho">
                                            <% if(tamanho != ""){%>
                                            <option selected value= <%=tamanho%>> <%=tamanho%></option>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <%}else{%>
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <%}%>
                                          </select>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td><div align="left">Forma</div></td>
                                      <td><div align="left">
                                          <select name="Forma" id="Forma" onchange="imagemForma.src='formas/' + this.value + '.gif'">
                                                <% if(forma.equals("abelha")) { %>
                                                    <option selected value="abelha">abelha
                                                <% } else { %>
                                                    <option value="abelha">abelha<% } %>	   
                                                <% if(forma.equals("abobora")) { %>
                                                    <option selected value="abobora">ab&oacute;bora
                                                <% } else { %>
                                                    <option value="abobora">ab&oacute;bora<% } %>	   
                                                <% if(forma.equals("agua")) { %>
                                                    <option selected value="agua">agua
                                                <% } else { %>
                                                    <option value="agua">agua<% } %>	   
                                                <% if(forma.equals("alvo")) { %>
                                                    <option selected value="alvo">alvo
                                                <% } else { %>
                                                    <option value="alvo">alvo<% } %>	   
                                                <% if(forma.equals("ambulancia")) { %>
                                                    <option selected value="ambulancia">ambul&acirc;ncia
                                                <% } else { %>
                                                    <option value="ambulancia">ambul&acirc;ncia<% } %>	   
                                                <% if(forma.equals("aranha")) { %>
                                                    <option selected value="aranha">aranha
                                                <% } else { %>
                                                    <option value="aranha">aranha<% } %>	   
                                                <% if(forma.equals("arvore")) { %>
                                                    <option selected value="arvore">&aacute;rvore
                                                <% } else { %>
                                                    <option value="arvore">&aacute;rvore<% } %>	   
                                                <% if(forma.equals("aviao")) { %>
                                                    <option selected value="aviao">avi&atilde;o
                                                <% } else { %>
                                                    <option value="aviao">avi&atilde;o<% } %>	   
                                                <% if(forma.equals("aviaoantigo")) { %>
                                                    <option selected value="aviaoantigo">avi&atilde;o antigo
                                                <% } else { %>
                                                    <option value="aviaoantigo">avi&atilde;o antigo<% } %>	   
                                                <% if(forma.equals("balao")) { %>
                                                    <option selected value="balao">bal&atilde;o
                                                <% } else { %>
                                                    <option value="balao">bal&atilde;o<% } %>	   
                                                <% if(forma.equals("banana")) { %>
                                                    <option selected value="banana">banana
                                                <% } else { %>
                                                    <option value="banana">banana<% } %>	   
                                                <% if(forma.equals("bandeira")) { %>
                                                    <option selected value="bandeira">bandeira
                                                <% } else { %>
                                                    <option value="bandeira">bandeira<% } %>	   
                                                <% if(forma.equals("barco")) { %>
                                                    <option selected value="barco">barco
                                                <% } else { %>
                                                    <option value="barco">barco<% } %>	   
                                                <% if(forma.equals("barraca")) { %>
                                                    <option selected value="barraca">barraca
                                                <% } else { %>
                                                    <option value="barraca">barraca<% } %>	   
                                                <% if(forma.equals("bicicleta")) { %>
                                                    <option selected value="bicicleta">bicicleta
                                                <% } else { %>
                                                    <option value="bicicleta">bicicleta<% } %>	   
                                                <% if(forma.equals("boladebasquete")) { %>
                                                    <option selected value="boladebasquete">bola de basquete
                                                <% } else { %>
                                                    <option value="boladebasquete">bola de basquete<% } %>	   
                                                <% if(forma.equals("boladetenis")) { %>
                                                    <option selected value="boladetenis">bola de t&eacute;nis
                                                <% } else { %>
                                                    <option value="boladetenis">bola de t&eacute;nis<% } %>	   
                                                <% if(forma.equals("borboleta")) { %>
                                                    <option selected value="borboleta">borboleta
                                                <% } else { %>
                                                    <option value="borboleta">borboleta<% } %>	   
                                                <% if(forma.equals("cachorro")) { %>
                                                    <option selected value="cachorro">cachorro
                                                <% } else { %>
                                                    <option value="cachorro">cachorro<% } %>	   
                                                <% if(forma.equals("cactus")) { %>
                                                    <option selected value="cactus">cactus
                                                <% } else { %>
                                                    <option value="cactus">cactus<% } %>	   
                                                <% if(forma.equals("caixa")) { %>
                                                    <option selected value="caixa">caixa
                                                <% } else { %>
                                                    <option value="caixa">caixa<% } %>	   
                                                <% if(forma.equals("caminhao")) { %>
                                                    <option selected value="caminhao">caminh&atilde;o
                                                <% } else { %>
                                                    <option value="caminhao">caminh&atilde;o<% } %>	   
                                                <% if(forma.equals("canhao")) { %>
                                                    <option selected value="canhao">canh&atilde;o
                                                <% } else { %>
                                                    <option value="canhao">canh&atilde;o<% } %>	   
                                                <% if(forma.equals("carro")) { %>
                                                    <option selected value="carro">carro
                                                <% } else { %>
                                                    <option value="carro">carro<% } %>	   
                                                <% if(forma.equals("carro2")) { %>
                                                    <option selected value="carro2">carro 2
                                                <% } else { %>
                                                    <option value="carro2">carro 2<% } %>	   
                                                <% if(forma.equals("carta")) { %>
                                                    <option selected value="carta">carta
                                                <% } else { %>
                                                    <option value="carta">carta<% } %>	   
                                                <% if(forma.equals("casa")) { %>
                                                    <option selected value="casa">casa
                                                <% } else { %>
                                                    <option value="casa">casa<% } %>	   
                                                <% if(forma.equals("casa2")) { %>
                                                    <option selected value="casa2">casa 2
                                                <% } else { %>
                                                    <option value="casa2">casa 2<% } %>	   
                                                <% if(forma.equals("casa3")) { %>
                                                    <option selected value="casa3">casa 3
                                                <% } else { %>
                                                    <option value="casa3">casa 3<% } %>	   
                                                <% if(forma.equals("chave")) { %>
                                                    <option selected value="chave">chave
                                                <% } else { %>
                                                    <option value="chave">chave<% } %>	   
                                                <% if(forma.equals("cilindro")) { %>
                                                    <option selected value="cilindro">cilindro
                                                <% } else { %>
                                                    <option value="cilindro">cilindro<% } %>	   
                                                <% if(forma.equals("circulo1")) { %>
                                                    <option selected value="circulo1">c&iacute;rculo 1
                                                <% } else { %>
                                                    <option value="circulo1">c&iacute;rculo 1<% } %>	   
                                                <% if(forma.equals("circulo2")) { %>
                                                    <option selected value="circulo2">c&iacute;rculo 2
                                                <% } else { %>
                                                    <option value="circulo2">c&iacute;rculo 2<% } %>	   
                                                <% if(forma.equals("coelho")) { %>
                                                    <option selected value="coelho">coelho
                                                <% } else { %>
                                                    <option value="coelho">coelho<% } %>	   
                                                <% if(forma.equals("computador")) { %>
                                                    <option selected value="computador">computador
                                                <% } else { %>
                                                    <option value="computador">computador<% } %>	   
                                                <% if(forma.equals("coracao")) { %>
                                                    <option selected value="coracao">cora&ccedil;&atilde;o
                                                <% } else { %>
                                                    <option value="coracao">cora&ccedil;&atilde;o<% } %>	   
                                                <% if(forma.equals("coroa")) { %>
                                                    <option selected value="coroa">coroa
                                                <% } else { %>
                                                    <option value="coroa">coroa<% } %>	   
                                                <% if(forma.equals("cubo")) { %>
                                                    <option selected value="cubo">cubo
                                                <% } else { %>
                                                    <option value="cubo">cubo<% } %>	   
                                                <% if(forma.equals("dardo")) { %>
                                                    <option selected value="dardo">dardo
                                                <% } else { %>
                                                    <option value="dardo">dardo<% } %>	   
                                                <% if(forma.equals("dente")) { %>
                                                    <option selected value="dente">dente
                                                <% } else { %>
                                                    <option value="dente">dente<% } %>	   
                                                <% if(forma.equals("discovoador")) { %>
                                                    <option selected value="discovoador">disco voador
                                                <% } else { %>
                                                    <option value="discovoador">disco voador<% } %>	   
                                                <% if(forma.equals("dolar")) { %>
                                                    <option selected value="dolar">d&oacute;lar
                                                <% } else { %>
                                                    <option value="dolar">d&oacute;lar<% } %>	   
                                                <% if(forma.equals("domino1")) { %>
                                                    <option selected value="domino1">domin&oacute; 1
                                                <% } else { %>
                                                    <option value="domino1">domin&oacute; 1<% } %>	   
                                                <% if(forma.equals("domino2")) { %>
                                                    <option selected value="domino2">domin&oacute; 2
                                                <% } else { %>
                                                    <option value="domino2">domin&oacute; 2<% } %>	   
                                                <% if(forma.equals("domino3")) { %>
                                                    <option selected value="domino3">domin&oacute; 3
                                                <% } else { %>
                                                    <option value="domino3">domin&oacute; 3<% } %>	   
                                                <% if(forma.equals("domino4")) { %>
                                                    <option selected value="domino4">domin&oacute; 4
                                                <% } else { %>
                                                    <option value="domino4">domin&oacute; 4<% } %>	   
                                                <% if(forma.equals("domino5")) { %>
                                                    <option selected value="domino5">domin&oacute; 5
                                                <% } else { %>
                                                    <option value="domino5">domin&oacute; 5<% } %>	   
                                                <% if(forma.equals("domino6")) { %>
                                                    <option selected value="domino6">domin&oacute; 6
                                                <% } else { %>
                                                    <option value="domino6">domin&oacute; 6<% } %>	   
                                                <% if(forma.equals("escavadora")) { %>
                                                    <option selected value="escavadora">escavadora
                                                <% } else { %>
                                                    <option value="escavadora">escavadora<% } %>	   
                                                <% if(forma.equals("esquilo")) { %>
                                                    <option selected value="esquilo">esquilo
                                                <% } else { %>
                                                    <option value="esquilo">esquilo<% } %>	   
                                                <% if(forma.equals("estrela")) { %>
                                                    <option selected value="estrela">estrela
                                                <% } else { %>
                                                    <option value="estrela">estrela<% } %>	   
                                                <% if(forma.equals("exclamacao")) { %>
                                                    <option selected value="exclamacao">exclama&ccedil;&atilde;o
                                                <% } else { %>
                                                    <option value="exclamacao">exclama&ccedil;&atilde;o<% } %>	   
                                                <% if(forma.equals("facefeliz")) { %>
                                                    <option selected value="facefeliz">face feliz
                                                <% } else { %>
                                                    <option value="facefeliz">face feliz<% } %>	   
                                                <% if(forma.equals("faceneutra")) { %>
                                                    <option selected value="faceneutra">face neutra
                                                <% } else { %>
                                                    <option value="faceneutra">face neutra<% } %>	   
                                                <% if(forma.equals("facetriste")) { %>
                                                    <option selected value="facetriste">face triste
                                                <% } else { %>
                                                    <option value="facetriste">face triste<% } %>	   
                                                <% if(forma.equals("falcao")) { %>
                                                    <option selected value="falcao">falc&atilde;o
                                                <% } else { %>
                                                    <option value="falcao">falc&atilde;o<% } %>	   
                                                <% if(forma.equals("fantasma")) { %>
                                                    <option selected value="fantasma">fantasma
                                                <% } else { %>
                                                    <option value="fantasma">fantasma<% } %>	   
                                                <% if(forma.equals("flor")) { %>
                                                    <option selected value="flor">flor
                                                <% } else { %>
                                                    <option value="flor">flor<% } %>	   
                                                <% if(forma.equals("fogo")) { %>
                                                    <option selected value="fogo">fogo
                                                <% } else { %>
                                                    <option value="fogo">fogo<% } %>	   
                                                <% if(forma.equals("foguete")) { %>
                                                    <option selected value="foguete">foguete
                                                <% } else { %>
                                                    <option value="foguete">foguete<% } %>	   
                                                <% if(forma.equals("folha")) { %>
                                                    <option selected value="folha">folha
                                                <% } else { %>
                                                    <option value="folha">folha<% } %>	   
                                                <% if(forma.equals("formiga")) { %>
                                                    <option selected value="formiga">formiga
                                                <% } else { %>
                                                    <option value="formiga">formiga<% } %>	   
                                                <% if(forma.equals("garrafa")) { %>
                                                    <option selected value="garrafa">garrafa
                                                <% } else { %>
                                                    <option value="garrafa">garrafa<% } %>	   
                                                <% if(forma.equals("gato")) { %>
                                                    <option selected value="gato">gato
                                                <% } else { %>
                                                    <option value="gato">gato<% } %>	   
                                                <% if(forma.equals("gota")) { %>
                                                    <option selected value="gota">gota
                                                <% } else { %>
                                                    <option value="gota">gota<% } %>	   
                                                <% if(forma.equals("hexagono")) { %>
                                                    <option selected value="hexagono">hex&aacute;gono
                                                <% } else { %>
                                                    <option value="hexagono">hex&aacute;gono<% } %>	   
                                                <% if(forma.equals("ima")) { %>
                                                    <option selected value="ima">&iacute;m&atilde;
                                                <% } else { %>
                                                    <option value="ima">&iacute;m&atilde;<% } %>	   
                                                <% if(forma.equals("industria")) { %>
                                                    <option selected value="industria">ind&uacute;stria
                                                <% } else { %>
                                                    <option value="industria">ind&uacute;stria<% } %>	   
                                                <% if(forma.equals("inseto")) { %>
                                                    <option selected value="inseto">inseto
                                                <% } else { %>
                                                    <option value="inseto">inseto<% } %>	   
                                                <% if(forma.equals("lagarta")) { %>
                                                    <option selected value="lagarta">lagarta
                                                <% } else { %>
                                                    <option value="lagarta">lagarta<% } %>	   
                                                <% if(forma.equals("lapis")) { %>
                                                    <option selected value="lapis">l&aacute;pis
                                                <% } else { %>
                                                    <option value="lapis">l&aacute;pis<% } %>	   
                                                <% if(forma.equals("linha")) { %>
                                                    <option selected value="linha">linha
                                                <% } else { %>
                                                    <option value="linha">linha<% } %>	   
                                                <% if(forma.equals("linhapequena")) { %>
                                                    <option selected value="linhapequena">linha pequena
                                                <% } else { %>
                                                    <option value="linhapequena">linha pequena<% } %>	   
                                                <% if(forma.equals("livro")) { %>
                                                    <option selected value="livro">livro
                                                <% } else { %>
                                                    <option value="livro">livro<% } %>	   
                                                <% if(forma.equals("lixeira")) { %>
                                                    <option selected value="lixeira">lixeira
                                                <% } else { %>
                                                    <option value="lixeira">lixeira<% } %>	   
                                                <% if(forma.equals("lobo")) { %>
                                                    <option selected value="lobo">lobo
                                                <% } else { %>
                                                    <option value="lobo">lobo<% } %>	   
                                                <% if(forma.equals("loja")) { %>
                                                    <option selected value="loja">loja
                                                <% } else { %>
                                                    <option value="loja">loja<% } %>	   
                                                <% if(forma.equals("losango")) { %>
                                                    <option selected value="losango">losango
                                                <% } else { %>
                                                    <option value="losango">losango<% } %>	   
                                                <% if(forma.equals("lua")) { %>
                                                    <option selected value="lua">lua
                                                <% } else { %>
                                                    <option value="lua">lua<% } %>	   
                                                <% if(forma.equals("maca")) { %>
                                                    <option selected value="maca">ma&ccedil;&atilde;
                                                <% } else { %>
                                                    <option value="maca">ma&ccedil;&atilde;<% } %>	   
                                                <% if(forma.equals("madeira")) { %>
                                                    <option selected value="madeira">madeira
                                                <% } else { %>
                                                    <option value="madeira">madeira<% } %>	   
                                                <% if(forma.equals("moedacara")) { %>
                                                    <option selected value="moedacara">moeda cara
                                                <% } else { %>
                                                    <option value="moedacara">moeda cara<% } %>	   
                                                <% if(forma.equals("moedacoroa")) { %>
                                                    <option selected value="moedacoroa">moeda coroa
                                                <% } else { %>
                                                    <option value="moedacoroa">moeda coroa<% } %>	   
                                                <% if(forma.equals("monumento")) { %>
                                                    <option selected value="monumento">monumento
                                                <% } else { %>
                                                    <option value="monumento">monumento<% } %>	   
                                                <% if(forma.equals("morango")) { %>
                                                    <option selected value="morango">morango
                                                <% } else { %>
                                                    <option value="morango">morango<% } %>	   
                                                <% if(forma.equals("notamusical1")) { %>
                                                    <option selected value="notamusical1">nota musical 1
                                                <% } else { %>
                                                    <option value="notamusical1">nota musical 1<% } %>	   
                                                <% if(forma.equals("notamusical2")) { %>
                                                    <option selected value="notamusical2">nota musical 2
                                                <% } else { %>
                                                    <option value="notamusical2">nota musical 2<% } %>	   
                                                <% if(forma.equals("nuvem")) { %>
                                                    <option selected value="nuvem">nuvem
                                                <% } else { %>
                                                    <option value="nuvem">nuvem<% } %>	   
                                                <% if(forma.equals("olho")) { %>
                                                    <option selected value="olho">olho
                                                <% } else { %>
                                                    <option value="olho">olho<% } %>	   
                                                <% if(forma.equals("onibus")) { %>
                                                    <option selected value="onibus">&ocirc;nibus
                                                <% } else { %>
                                                    <option value="onibus">&ocirc;nibus<% } %>	   
                                                <% if(forma.equals("ovelha")) { %>
                                                    <option selected value="ovelha">ovelha
                                                <% } else { %>
                                                    <option value="ovelha">ovelha<% } %>	   
                                                <% if(forma.equals("ovo")) { %>
                                                    <option selected value="ovo">ovo
                                                <% } else { %>
                                                    <option value="ovo">ovo<% } %>	   
                                                <% if(forma.equals("passaro")) { %>
                                                    <option selected value="passaro">p&aacute;ssaro
                                                <% } else { %>
                                                    <option value="passaro">p&aacute;ssaro<% } %>	   
                                                <% if(forma.equals("pedras")) { %>
                                                    <option selected value="pedras">pedras
                                                <% } else { %>
                                                    <option value="pedras">pedras<% } %>	   
                                                <% if(forma.equals("pegada")) { %>
                                                    <option selected value="pegada">pegada
                                                <% } else { %>
                                                    <option value="pegada">pegada<% } %>	   
                                                <% if(forma.equals("peixe")) { %>
                                                    <option selected value="peixe">peixe
                                                <% } else { %>
                                                    <option value="peixe">peixe<% } %>	   
                                                <% if(forma.equals("pentagono")) { %>
                                                    <option selected value="pentagono">pent&aacute;gono
                                                <% } else { %>
                                                    <option value="pentagono">pent&aacute;gono<% } %>	   
                                                <% if(forma.equals("pessoa")) { %>
                                                    <option selected value="pessoa">pessoa
                                                <% } else { %>
                                                    <option value="pessoa">pessoa<% } %>	   
                                                <% if(forma.equals("pessoaempresario")) { %>
                                                    <option selected value="pessoaempresario">pessoa empres&aacute;rio
                                                <% } else { %>
                                                    <option value="pessoaempresario">pessoa empres&aacute;rio<% } %>	   
                                                <% if(forma.equals("pessoaestudante")) { %>
                                                    <option selected value="pessoaestudante">pessoa estudante
                                                <% } else { %>
                                                    <option value="pessoaestudante">pessoa estudante<% } %>	   
                                                <% if(forma.equals("pessoafazendeiro")) { %>
                                                    <option selected value="pessoafazendeiro">pessoa fazendeiro
                                                <% } else { %>
                                                    <option value="pessoafazendeiro">pessoa fazendeiro<% } %>	   
                                                <% if(forma.equals("pessoaformando")) { %>
                                                    <option selected value="pessoaformando">pessoa formando
                                                <% } else { %>
                                                    <option value="pessoaformando">pessoa formando<% } %>	   
                                                <% if(forma.equals("pessoagarcom")) { %>
                                                    <option selected value="pessoagarcom">pessoa gar&ccedil;om
                                                <% } else { %>
                                                    <option value="pessoagarcom">pessoa gar&ccedil;om<% } %>	   
                                                <% if(forma.equals("pessoagari")) { %>
                                                    <option selected value="pessoagari">pessoa gari
                                                <% } else { %>
                                                    <option value="pessoagari">pessoa gari<% } %>	   
                                                <% if(forma.equals("pessoalenhador")) { %>
                                                    <option selected value="pessoalenhador">pessoa lenhador
                                                <% } else { %>
                                                    <option value="pessoalenhador">pessoa lenhador<% } %>	   
                                                <% if(forma.equals("pessoamedico")) { %>
                                                    <option selected value="pessoamedico">pessoa m&eacute;dico
                                                <% } else { %>
                                                    <option value="pessoamedico">pessoa m&eacute;dico<% } %>	   
                                                <% if(forma.equals("pessoapolicial")) { %>
                                                    <option selected value="pessoapolicial">pessoa policial
                                                <% } else { %>
                                                    <option value="pessoapolicial">pessoa policial<% } %>	   
                                                <% if(forma.equals("pessoasoldado")) { %>
                                                    <option selected value="pessoasoldado">pessoa soldado
                                                <% } else { %>
                                                    <option value="pessoasoldado">pessoa soldado<% } %>	   
                                                <% if(forma.equals("pincel")) { %>
                                                    <option selected value="pincel">pincel
                                                <% } else { %>
                                                    <option value="pincel">pincel<% } %>	   
                                                <% if(forma.equals("pinheiro")) { %>
                                                    <option selected value="pinheiro">pinheiro
                                                <% } else { %>
                                                    <option value="pinheiro">pinheiro<% } %>	   
                                                <% if(forma.equals("pinodeboliche")) { %>
                                                    <option selected value="pinodeboliche">pino de boliche
                                                <% } else { %>
                                                    <option value="pinodeboliche">pino de boliche<% } %>	   
                                                <% if(forma.equals("planta")) { %>
                                                    <option selected value="planta">planta
                                                <% } else { %>
                                                    <option value="planta">planta<% } %>	   
                                                <% if(forma.equals("polvo")) { %>
                                                    <option selected value="polvo">polvo
                                                <% } else { %>
                                                    <option value="polvo">polvo<% } %>	   
                                                <% if(forma.equals("ponto")) { %>
                                                    <option selected value="ponto">ponto
                                                <% } else { %>
                                                    <option value="ponto">ponto<% } %>	   
                                                <% if(forma.equals("quadrado1")) { %>
                                                    <option selected value="quadrado1">quadrado 1
                                                <% } else { %>
                                                    <option value="quadrado1">quadrado 1<% } %>	   
                                                <% if(forma.equals("quadrado2")) { %>
                                                    <option selected value="quadrado2">quadrado 2
                                                <% } else { %>
                                                    <option value="quadrado2">quadrado 2<% } %>	   
                                                <% if(forma.equals("raio")) { %>
                                                    <option selected value="raio">raio
                                                <% } else { %>
                                                    <option value="raio">raio<% } %>	   
                                                <% if(forma.equals("rato")) { %>
                                                    <option selected value="rato">rato
                                                <% } else { %>
                                                    <option value="rato">rato<% } %>	   
                                                <% if(forma.equals("relogio")) { %>
                                                    <option selected value="relogio">rel&oacute;gio
                                                <% } else { %>
                                                    <option value="relogio">rel&oacute;gio<% } %>	   
                                                <% if(forma.equals("roda")) { %>
                                                    <option selected value="roda">roda
                                                <% } else { %>
                                                    <option value="roda">roda<% } %>	   
                                                <% if(forma.equals("sapo")) { %>
                                                    <option selected value="sapo">sapo
                                                <% } else { %>
                                                    <option value="sapo">sapo<% } %>	   
                                                <% if(forma.equals("seta")) { %>
                                                    <option selected value="seta">seta
                                                <% } else { %>
                                                    <option value="seta">seta<% } %>	   
                                                <% if(forma.equals("setafina")) { %>
                                                    <option selected value="setafina">seta fina
                                                <% } else { %>
                                                    <option value="setafina">seta fina<% } %>	   
                                                <% if(forma.equals("setapadrao")) { %>
                                                    <option selected value="setapadrao">seta padr&atilde;o
                                                <% } else { %>
                                                    <option value="setapadrao">seta padr&atilde;o<% } %>	   
                                                <% if(forma.equals("sol")) { %>
                                                    <option selected value="sol">sol
                                                <% } else { %>
                                                    <option value="sol">sol<% } %>	   
                                                <% if(forma.equals("tanque")) { %>
                                                    <option selected value="tanque">tanque
                                                <% } else { %>
                                                    <option value="tanque">tanque<% } %>	   
                                                <% if(forma.equals("tartaruga")) { %>
                                                    <option selected value="tartaruga">tartaruga
                                                <% } else { %>
                                                    <option value="tartaruga">tartaruga<% } %>	   
                                                <% if(forma.equals("telefone")) { %>
                                                    <option selected value="telefone">telefone
                                                <% } else { %>
                                                    <option value="telefone">telefone<% } %>	   
                                                <% if(forma.equals("tijolos")) { %>
                                                    <option selected value="tijolos">tijolos
                                                <% } else { %>
                                                    <option value="tijolos">tijolos<% } %>	   
                                                <% if(forma.equals("trem")) { %>
                                                    <option selected value="trem">trem
                                                <% } else { %>
                                                    <option value="trem">trem<% } %>	   
                                                <% if(forma.equals("triangulo1")) { %>
                                                    <option selected value="triangulo1">tri&acirc;ngulo 1
                                                <% } else { %>
                                                    <option value="triangulo1">tri&acirc;ngulo 1<% } %>	   
                                                <% if(forma.equals("triangulo2")) { %>
                                                    <option selected value="triangulo2">tri&acirc;ngulo 2
                                                <% } else { %>
                                                    <option value="triangulo2">tri&acirc;ngulo 2<% } %>	   
                                                <% if(forma.equals("tubarao")) { %>
                                                    <option selected value="tubarao">tubar&atilde;o
                                                <% } else { %>
                                                    <option value="tubarao">tubar&atilde;o<% } %>	   
                                                <% if(forma.equals("vaca")) { %>
                                                    <option selected value="vaca">vaca
                                                <% } else { %>
                                                    <option value="vaca">vaca<% } %>	   
                                                <% if(forma.equals("vagao")) { %>
                                                    <option selected value="vagao">vag&atilde;o
                                                <% } else { %>
                                                    <option value="vagao">vag&atilde;o<% } %>	   
                                                <% if(forma.equals("van")) { %>
                                                    <option selected value="van">van
                                                <% } else { %>
                                                    <option value="van">van<% } %>	   
                                                <% if(forma.equals("x")) { %>
                                                    <option selected value="x">x
                                                <% } else { %>
                                                    <option value="x">x<% } %>	   
                                                <% if(forma.equals("xadrezbispo")) { %>
                                                    <option selected value="xadrezbispo">xadrez bispo
                                                <% } else { %>
                                                    <option value="xadrezbispo">xadrez bispo<% } %>	   
                                                <% if(forma.equals("xadrezcavalo")) { %>
                                                    <option selected value="xadrezcavalo">xadrez cavalo
                                                <% } else { %>
                                                    <option value="xadrezcavalo">xadrez cavalo<% } %>	   
                                                <% if(forma.equals("xadrezpeao")) { %>
                                                    <option selected value="xadrezpeao">xadrez pe&atilde;o
                                                <% } else { %>
                                                    <option value="xadrezpeao">xadrez pe&atilde;o<% } %>	   
                                                <% if(forma.equals("xadrezrainha")) { %>
                                                    <option selected value="xadrezrainha">xadrez rainha
                                                <% } else { %>
                                                    <option value="xadrezrainha">xadrez rainha<% } %>	   
                                                <% if(forma.equals("xadrezrei")) { %>
                                                    <option selected value="xadrezrei">xadrez rei
                                                <% } else { %>
                                                    <option value="xadrezrei">xadrez rei<% } %>	   
                                                <% if(forma.equals("xadreztorre")) { %>
                                                    <option selected value="xadreztorre">xadrez torre
                                                <% } else { %>
                                                    <option value="xadreztorre">xadrez torre<% } %>                                       
                                          </select>
                                      </div></td>
                                      <td><table width="60" height="20" border="0" align="center" cellpadding="0" cellspacing="0">
                                        <tr>
                                          <% if (forma.equals("")) { %>
                                                <td><div align="center"><img name=imagemForma src="formas/abelha.gif" align="center" width="30" height="30"/></div></td>
                                          <% } else { %>
                                                <td><div align="center"><img name=imagemForma src="<%="formas/" + forma + ".gif"%>" align="center" width="30" height="30"/></div></td>
                                          <% } %>
                                          <!-- <td width="121" bgcolor="#333300"><div align="center" class="style1"><a href="#" onClick="MM_openBrWindow('formas.jsp','cores','scrollbars=yes,width=300,height=430')">Formas</a></div></td>-->
                                        </tr>
                                      </table></td>
                                    </tr>
                                    <tr>
                                      <td colspan="3">
                                         <div align="center">Quantidade Inicial</div></td>
                                    </tr>
                                    
                                   <tr>
                                            <td><div align="left">
                                                <table width="155" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                    <tr>
                                                        <td width="22"><div align="center">
                                                            <% if (quantidadeInicial.equals("0")) { %>
                                                                <input name="quantidadeInicial" type="radio" value="0" onclick="window.location.href = 'movelpassear.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&tamanho=' + Tamanho.value + '&forma=' + Forma.value + '&quantidadeInicial=0' + '&passo=' + passo.value + '&anguloMinimo=' + anguloMinimo.value +  '&anguloMaximo=' + anguloMaximo.value + '&posicao=<%=posicao%>'" checked>
                                                            <% } else { %>
                                                                <input name="quantidadeInicial" type="radio" value="0" onclick="window.location.href = 'movelpassear.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&tamanho=' + Tamanho.value + '&forma=' + Forma.value + '&quantidadeInicial=0' + '&passo=' + passo.value + '&anguloMinimo=' + anguloMinimo.value +  '&anguloMaximo=' + anguloMaximo.value + '&posicao=<%=posicao%>'"> <% } %>
                                                      </div></td>
                                                        <td width="126"><div align="center" class="style1">
                                                            N&uacute;mero
                                                      </div></td>
                                                    </tr>
                                                    <tr>
                                                        <td width="22"><div align="center">
                                                            <% if (quantidadeInicial.equals("1")) { %>
                                                                <input name="quantidadeInicial" type="radio" value="1" onclick="window.location.href = 'movelpassear.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&tamanho=' + Tamanho.value + '&forma=' + Forma.value + '&quantidadeInicial=1' + '&passo=' + passo.value + '&anguloMinimo=' + anguloMinimo.value +  '&anguloMaximo=' + anguloMaximo.value + '&posicao=<%=posicao%>'" checked>
                                                            <% } else { %>
                                                                <input name="quantidadeInicial" type="radio" value="1" onclick="window.location.href = 'movelpassear.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&tamanho=' + Tamanho.value + '&forma=' + Forma.value + '&quantidadeInicial=1' + '&passo=' + passo.value + '&anguloMinimo=' + anguloMinimo.value +  '&anguloMaximo=' + anguloMaximo.value + '&posicao=<%=posicao%>'"> <% } %>
                                                      </div></td>
                                                        <td width="126"><div align="center" class="style1">
                                                            Vari&aacute;vel Slider
                                                      </div></td>
                                                    </tr>
                                                </table>
                                            </div></td>
                                            <td colspan="2" align="middle">
                                                <% if (quantidadeInicial.equals("0")) { %>
                                                    <input name="numeroQtdInicial" id="Quantidade Inicial" type="text" value="<%=numeroQtdInicial%>" size="35" maxlength="10" onChange="validaQuantidade(numeroQtdInicial.value)">
                                                <% } else { %>
                                                    <% Modelo modelo = (Modelo)session.getAttribute("modelo");
                                                    List variaveis = AplCadastrarVariavel.variaveisSlidersGlobaisDoModelo(modelo.getIdModelo().intValue()); %>
                                                    <div align="center"><select name="numeroQtdInicial" id="Quantidade Inicial">
                                                           <% for(int i=0; i<variaveis.size(); i++){
		                                                        Variavel variavel = (Variavel)variaveis.get(i); 
                                                                        if (numeroQtdInicial.length() > 0 && numeroQtdInicial.equals(variavel.getIdVariavel().toString())) { %>
                                                                            <option selected value= <%= variavel.getIdVariavel() %>><%= variavel.getNome() %></option> 
                                                                        <% } else { %>
                                                                            <option value= <%= variavel.getIdVariavel() %>><%= variavel.getNome() %></option> 
                                                                        <%}%>
                                                            <%}%>
                                                    </div></select>
                                                <% } %>                                                
                                            </td>
                                    </tr>
                                    
				    <tr>
                                      <td colspan="3">
                                        <div align="center">Posi&ccedil;&atilde;o Inicial</div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="3"><div align="left"></div>
                                          <div align="left">
                                            <table width="393" border="1" cellpadding="0" cellspacing="0">
                                              <tr>
                                                <th width="98" scope="row">&nbsp;</th>
                                                <th width="190" scope="row"><table width="190" height="20" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                    <tr>
                                                      <td width="20"><div align="center">
                                                          <%if(posicao==1 || quantidadeInicial.equals("1")){%>
                                                          <input name="posicao" type="radio" onclick="window.location.href = 'movelpassear.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&tamanho=' + Tamanho.value + '&forma=' + Forma.value + '&quantidadeInicial=<%=quantidadeInicial%>' + '&numeroQtdInicial=' + numeroQtdInicial.value + '&passo=' + passo.value + '&anguloMinimo=' + anguloMinimo.value +  '&anguloMaximo=' + anguloMaximo.value + '&posicao=1'" value="aleatorio" checked>
                                                          <%}else{%>
                                                          <input name="posicao" type="radio" onclick="window.location.href = 'movelpassear.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&tamanho=' + Tamanho.value + '&forma=' + Forma.value + '&quantidadeInicial=<%=quantidadeInicial%>' + '&numeroQtdInicial=' + numeroQtdInicial.value + '&passo=' + passo.value + '&anguloMinimo=' + anguloMinimo.value +  '&anguloMaximo=' + anguloMaximo.value + '&posicao=1'" value="aleatorio">
                                                          <%}%>
                                                      </div></td>
                                                      <td width="230"><div align="center" class="style1">
                                                          <div align="center" class="style41">
                                                            <div align="center">Aleat&oacute;rio</div>
                                                          </div>
                                                      </div></td>
                                                    </tr>
                                                </table></th>
                                                <td width="97">&nbsp;</td>
                                              </tr>
                                              <%if (quantidadeInicial.equals("0")) {%>
                                              <tr>
                                                <th scope="row">&nbsp;</th>
                                                <th scope="row"><table width="190" height="20" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">                                  
                                                    <tr>
                                                      <td width="20" height="16"><div align="center">
                                                          <%if(posicao == 3){%>
                                                          <input name="posicao" type="radio" onclick="window.location.href = 'movelpassear.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&tamanho=' + Tamanho.value + '&forma=' + Forma.value + '&quantidadeInicial=<%=quantidadeInicial%>' +  '&numeroQtdInicial=' + numeroQtdInicial.value + '&passo=' + passo.value + '&anguloMinimo=' + anguloMinimo.value +  '&anguloMaximo=' + anguloMaximo.value + '&posicao=3'" value="posicaoInicial" checked>
                                                          <%}else{%>
                                                          <input name="posicao" type="radio" onclick="window.location.href = 'movelpassear.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&tamanho=' + Tamanho.value + '&forma=' + Forma.value + '&quantidadeInicial=<%=quantidadeInicial%>' +  '&numeroQtdInicial=' + numeroQtdInicial.value + '&passo=' + passo.value + '&anguloMinimo=' + anguloMinimo.value +  '&anguloMaximo=' + anguloMaximo.value + '&posicao=3'" value="posicaoInicial">
                                                          <%}%>
                                                      </div></td>
                                                      <td width="230"><div align="center" class="style1">
                                                          <div align="center" class="style41">Posi&ccedil;&atilde;o Inicial Espec&iacute;fica</div>
                                                      </div></td>
                                                    </tr>                                                    
                                                </table></th>
                                                <td>&nbsp;</td>
                                              </tr>
                                              <%}%>
                                              <tr>
                                                <th colspan="3" scope="row"> </th>
                                              </tr>
                                            </table>
                                        </div></td>
                                    </tr>
                                    <%if(posicao == 3){%>
                                    <tr>
                                      <td colspan="3"><table width="120" height="20" border="0" align="center" cellpadding="0" cellspacing="0">
                                          <tr>
                                            <!--<td bgcolor="#333300"><div align="center" class="style1" onclick="window.location.href = 'selecionar_posicoesMovel.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&acao=' + acao.value + '&tamanho=' + Tamanho.value + '&forma=' + Forma.value + '&quantidadeInicial=' + quantidadeInicial.value +'&passo=' + passo.value + '&anguloMinimo=' + anguloMinimo.value +  '&anguloMaximo=' + anguloMaximo.value + '&tipoMovimento=Passear'"><a href="#">Escolher Posi&ccedil;&otilde;es</a> </div></td>-->
                                            <td bgcolor="#333300"><div align="center" class="style1"><a href="javascript:linkPosicoesEspecificas()">Escolher Posi&ccedil;&otilde;es</a> </div></td>
                                          </tr>
                                      </table></td>
                                    </tr>
                                    <%}%>
                                    <tr>
                                      <td colspan="3"><div align="center"></div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="3"><div align="center">Movimento</div></td>
                                    </tr>
                                    <tr>
                                      <td>
                                        <div align="center">Tipo de Movimento </div></td>
                                      <td colspan="2"><div align="center">
                                          <table width="170" height="5" border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                              <td width="75"><table width="75" height="5" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                  <tr>
                                                    <td width="20"><input name="tipoMovimento" type="radio" onclick="window.location.href = 'movelpassear.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&tamanho=' + Tamanho.value + '&forma=' + Forma.value + '&quantidadeInicial=<%=quantidadeInicial%>' + '&numeroQtdInicial=' + numeroQtdInicial.value + '&posicao=<%=posicao%>'" value="Passear" checked></td>
                                                    <td width="55"><div align="center" class="style1">Passear</div></td>
                                                  </tr>
                                              </table></td>
                                              <td width="5">&nbsp;</td>
                                              <td width="70"><table width="70" height="5" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                  <tr>
                                                    <td width="20"><div align="center">
                                                        <input name="tipoMovimento" type="radio" onclick="window.location.href = 'movelrefletir.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&tamanho=' + Tamanho.value + '&forma=' + Forma.value + '&quantidadeInicial=<%=quantidadeInicial%>' + '&numeroQtdInicial=' + numeroQtdInicial.value + '&posicao=<%=posicao%>'" value="Refletir" >
                                                    </div></td>
                                                    <td width="50"><div align="center" class="style1">Refletir</div></td>
                                                  </tr>
                                              </table></td>
                                              <td width="5">&nbsp;</td>
                                              <td width="70"><table width="70" height="5" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                                  <tr>
                                                    <td width="20"><div align="center">
                                                        <input name="tipoMovimento" type="radio" onclick="window.location.href = 'movelparado.jsp?nome='+ Nome.value + '&cor=' + cor.value + '&tamanho=' + Tamanho.value + '&forma=' + Forma.value + '&quantidadeInicial=<%=quantidadeInicial%>' + '&numeroQtdInicial=' + numeroQtdInicial.value + '&posicao=<%=posicao%>'" value="Parado" >
                                                    </div></td>
                                                    <td width="50"><div align="center" class="style1">Parado</div></td>
                                                  </tr>
                                              </table></td>
                                            </tr>
                                          </table>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="3"><div align="center">
                                          <table width="393" border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                              <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                              <td><div align="center">
                                                  <table width="250" border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                      <td>
                                                        <div align="left">&Acirc;ngulo M&iacute;nimo</div></td>
                                                      <td><input name="anguloMinimo" type="text" id="Angulo Minimo" value="<%=anguloMinimo%>" size="20" maxlength="3"></td>
                                                    </tr>
                                                    <tr>
                                                      <td><div align="left">&Acirc;ngulo M&aacute;ximo </div></td>
                                                      <td><input name="anguloMaximo" type="text" id="Angulo Maximo" value="<%=anguloMaximo%>" size="20" maxlength="3"></td>
                                                    </tr>
                                                  </table>
                                              </div></td>
                                            </tr>
                                            <tr>
                                              <td>&nbsp;</td>
                                            </tr>
                                          </table>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td colspan="3"><div align="center">
                                          <input name="salvaragente" type="submit" id="salvaragente" value="Salvar Agente">
                                      </div></td>
                                    </tr>
                                  </table>
                                  </div></td>
                              </tr>
                              <tr>
                                <td><div align="center">
                                    <input name="totalValores" type="hidden" value="<%=totalPosicoes%>">
                                    <input name="metodo" type="hidden" value="cadastroAgente">
                                    <input name="acao" type="hidden" value="incluir">
                                    <input name="tipoAgente" type="hidden" id="tipoAgente" value="Movel">
                                    <input name="passo" type="hidden" id="passo" value="1">
</div></td>
                              </tr>
                              <tr>
                                <td><div align="center"><span class="style5"><%= status %></span> </div></td>
                              </tr>
                            </table>
                        </form></td>
                      </tr>
                      <tr>
                        <td width="400" height="10"><div align="right"> </div></td>
                      </tr>
                    </table>
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
</body>
</html>
