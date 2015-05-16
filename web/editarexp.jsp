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
errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
    String pai = request.getParameter("pai");
    String nome = request.getParameter("nome");
    String casasDecimais = request.getParameter("casasDecimais");
    String expr = request.getParameter("expr");
    String nomeAntigo = request.getParameter("nomeAntigo");
    String status = request.getParameter("status");
    String agente = request.getParameter("agente"); //id de agente
    String agentePrincipal = request.getParameter("agentePrincipal"); //id de ag princ
    String nomeVar1 = ""; //nome de variavel
    String nomeVar2 = ""; //nome de variavel
    String id = request.getParameter("id"); //id do objeto em caso de alteracao
    String idVar = request.getParameter("var"); //id var (valvar)
    String idVar1 = request.getParameter("var1"); //id var1
    String idVar2 = request.getParameter("var2"); //id var2
    String idComposicao = request.getParameter("idComposicao"); //idComposicao para pos
    String numAgentes = request.getParameter("numAgentes"); //numAgentes para pos
    String acao = request.getParameter("acao"); //idAcao
    String idIni = request.getParameter("idIni"); //idIni
    String idPerc = request.getParameter("idPerc"); 

    if(pai == null)
        pai = "";
    if(nome == null)
        nome = "";
    if(casasDecimais == null)
        casasDecimais = "";    
    if (nomeAntigo == null)
        nomeAntigo = "";
    if(status == null)
        status = "";
    if(agente == null)
        agente = "";
    if(agentePrincipal == null)
        agentePrincipal = "";
    if(nomeVar1 == null)
        nomeVar1 = "";
    if(expr == null)
        expr = ""; 
    if(id == null)
        id = "";
    if(idVar1 == null)
        idVar1 = "";
    if(idVar2 == null)
        idVar2 = "";
    if(numAgentes == null)
        numAgentes = "";
    if(idComposicao == null)
        idComposicao = "";
    if(acao == null)
        acao = "";
    if(idIni == null)
        idIni = "";
    
    boolean logica = true;      //Expressao logica
    boolean vars = true;        //Botoes com nomes de variaveis
    boolean varsLocais = false; //variaveis locais
    boolean condPos = false;    //botoes de coordenadas x e y
    boolean variavel1 = false;   //quando expressao envolve 1 variavel
    boolean variavel2 = false;  //quando expressao envolve 2 variaveis
    
    //monitor
    if(pai.equals("monitor") || pai.equals("altmonitor"))
    {
        logica = false;  
    }
    else 
        //condicao posicionamento
        if(pai.equals("condpos") || pai.equals("altcondpos"))
        {         
            varsLocais = true;
            condPos = true;
        }
        else
            //condicao valor de variavel
            if(pai.equals("condvalvar") || pai.equals("altcondvalvar"))
            {         
                vars = false;
                variavel1 = true;
                nomeVar1 = AplCadastrarVariavel.obterVariavel(Integer.parseInt(idVar)).getNome();
            }
            else
                //condicao comparacao de variaveis
                if(pai.equals("condcmpvar") || pai.equals("altcondcmpvar"))
                {
                    vars = false;
                    variavel1 = true;
                    variavel2 = true;
                    nomeVar1 = AplCadastrarVariavel.obterVariavel(Integer.parseInt(idVar1)).getNome();
                    nomeVar2 = AplCadastrarVariavel.obterVariavel(Integer.parseInt(idVar2)).getNome();
                }
                else
                    //acao recuperar valor de variavel
                    if(pai.equals("acrecvalvar") || pai.equals("altacrecvalvar"))
                    {         
                        Variavel variavel = AplCadastrarVariavel.obterVariavel(Integer.parseInt(idVar));
                        Agente agRecValVar = variavel.getAgente();                        
                        if(agRecValVar != null)
                            agentePrincipal = agRecValVar.getIdAgente().toString();
                        
                        if (!variavel.getTipo().equals("Logica"))
                            logica = false;                        
                        
                        varsLocais = true;                        
                    }
                    else
                        //variavel valor
                        if(pai.equals("variavel") || pai.equals("altvariavel"))
                        {
                            logica = false;
                            varsLocais = true;
                        }
%>

<html>
    <head>
        <title>NETPLAY</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
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
            .tecla {width: 1cm;}
			.teclaDupla {width: 2cm;}
			.teclaDupla2 {width: 2cm; font-size:12px;}
			.texto{font-size-adjust:none; font-size:12px;}
			#dhtmltooltip{
			position: absolute;
			width: 150px;
                        border: 2px solid black;
			padding: 2px;
			background-color: #DCE7DE;
			visibility: hidden;
			z-index: 100;
			}
            -->
        </style>
		
	</head>

    <body onload="javascript:inicio();">
		
        <div id="dhtmltooltip"></div>

<script type="text/javascript">

/***********************************************
* Cool DHTML tooltip script- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

var offsetxpoint=-60 //Customize x offset of tooltip
var offsetypoint=20 //Customize y offset of tooltip
var ie=document.all
var ns6=document.getElementById && !document.all
var enabletip=false
if (ie||ns6)
var tipobj=document.all? document.all["dhtmltooltip"] : document.getElementById? document.getElementById("dhtmltooltip") : ""

function ietruebody(){
return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}

function ddrivetip(thetext, thecolor, thewidth){
if (ns6||ie){
if (typeof thewidth!="undefined") tipobj.style.width=thewidth+"px"
if (typeof thecolor!="undefined" && thecolor!="") tipobj.style.backgroundColor=thecolor
tipobj.innerHTML=thetext
enabletip=true
return false
}
}

function positiontip(e){
if (enabletip){
var curX=(ns6)?e.pageX : event.clientX+ietruebody().scrollLeft;
var curY=(ns6)?e.pageY : event.clientY+ietruebody().scrollTop;
//Find out how close the mouse is to the corner of the window
var rightedge=ie&&!window.opera? ietruebody().clientWidth-event.clientX-offsetxpoint : window.innerWidth-e.clientX-offsetxpoint-20
var bottomedge=ie&&!window.opera? ietruebody().clientHeight-event.clientY-offsetypoint : window.innerHeight-e.clientY-offsetypoint-20

var leftedge=(offsetxpoint<0)? offsetxpoint*(-1) : -1000

//if the horizontal distance isn't enough to accomodate the width of the context menu
if (rightedge<tipobj.offsetWidth)
//move the horizontal position of the menu to the left by it's width
tipobj.style.left=ie? ietruebody().scrollLeft+event.clientX-tipobj.offsetWidth+"px" : window.pageXOffset+e.clientX-tipobj.offsetWidth+"px"
else if (curX<leftedge)
tipobj.style.left="5px"
else
//position the horizontal position of the menu where the mouse is positioned
tipobj.style.left=curX+offsetxpoint+"px"

//same concept with the vertical position
if (bottomedge<tipobj.offsetHeight)
tipobj.style.top=ie? ietruebody().scrollTop+event.clientY-tipobj.offsetHeight-offsetypoint+"px" : window.pageYOffset+e.clientY-tipobj.offsetHeight-offsetypoint+"px"
else
tipobj.style.top=curY+offsetypoint+"px"
tipobj.style.visibility="visible"
}
}

function hideddrivetip(){
if (ns6||ie){
enabletip=false
tipobj.style.visibility="hidden"
tipobj.style.left="-1000px"
tipobj.style.backgroundColor=''
tipobj.style.width=''
}
}

document.onmousemove=positiontip

var comandos = new Array();

function agentes(){

	var t = comandos.length;
	if(t==0 || (comandos[t-1]!=' cont ' && comandos[t-1]!='cont ' && comandos[t-1]!='cont'))
	{
		alert('Tem que ser usado somente depois de contador (cont).');
		return;
	}

	if(document.EditorExpressao.condicao.value == 'nenhum') {                
		incluir('#' + document.EditorExpressao.agente2.value + ' ');
	}
	if(document.EditorExpressao.condicao.value == 'cor') {
		incluir('#' + document.EditorExpressao.agente2.value + '.cor=' + document.EditorExpressao.cor.value + ' ');
	}
	if(document.EditorExpressao.condicao.value == 'forma') {
		incluir('#' + document.EditorExpressao.agente2.value + '.forma=' + document.EditorExpressao.forma.value + ' ');
	}
	if(document.EditorExpressao.condicao.value == 'xcor' && ehPos()) {
		incluir('#' + document.EditorExpressao.agente2.value + '.xcor' + document.EditorExpressao.sinalPos.value + document.EditorExpressao.pos.value + ' ');
	}
	if(document.EditorExpressao.condicao.value == 'ycor' && ehPos()) {
		incluir('#' + document.EditorExpressao.agente2.value + '.ycor' + document.EditorExpressao.sinalPos.value + document.EditorExpressao.pos.value + ' ');
	}
	
	var tipo = document.EditorExpressao.condicao.value.substr(0,1);
	if(tipo == 'L') {
		var variavel = document.EditorExpressao.condicao.value.substring(1);
		incluir('#' + document.EditorExpressao.agente2.value + '.' + variavel + '=' + document.EditorExpressao.valorLogico.value + ' ');
	}
	if(tipo == 'V' && ehNumero(document.EditorExpressao.valor.value)) {
		var variavel = document.EditorExpressao.condicao.value.substring(1);
		incluir('#' + document.EditorExpressao.agente2.value + '.' + variavel + document.EditorExpressao.sinalValor.value + document.EditorExpressao.valor.value + ' ');
	}
}

function ehPos(){
	var num = document.EditorExpressao.pos.value;
	if(!ehNumero(num))
		return false;
	else
		if(num>25 || num<-25)
			return false;
		else
			return true;
}

function ehNumero(num){
	var nums = '0123456789';
	var separador = 0;
	
	if(num == "")
		return false;
	for(var i=0; i<num.length; i++)
	{
		if(nums.indexOf(num.charAt(i),0) == -1) {
			if(num.charAt(i) == '-' && i>0)
				return false;
			if(num.charAt(i) == '.') {
				separador++;
				if(separador > 1 || i==0 || i==num.length - 1)
					return false;
			}
		}
	}
	return true;
}

function verificarNumero(){
	if(!ehNumero(document.EditorExpressao.valor.value))
		alert('Opcao tem que ser um numero');
}

function verificarPos() {
	var num = document.EditorExpressao.pos.value;
	if(!ehNumero(num))
		alert('Opcao tem que ser um numero');
	else
		if(num>25 || num<-25)
			alert('Opcao tem que ser entre -25 e 25');
}

function inicio()
{
    var expr = '<%=expr%>';
    
    var novosComandos = new Array();
    novosComandos = expr.split(' ');
    for (i in novosComandos)
        novosComandos[i] = novosComandos[i] + ' ';
    comandos = novosComandos;
    
    if (comandos.length > 1) 
        expr = expr + ' ';
    
    document.EditorExpressao.expr.value = expr;
    document.EditorExpressao.expressao.value = expr;       
}

function incluir(str){
	var tam = comandos.length + 1;
	var novoComandos = new Array(tam);
	for(var i=0; i<comandos.length; i++)
		novoComandos[i] = comandos[i];
	
	if(document.EditorExpressao.expressao.value == "") 
	{
		if(str == " - ")
			str = "-";
		if(str.charAt(0) == " ")
			str = str.substr(1);
	}
        
	novoComandos[tam - 1] = str;
	comandos = novoComandos;
	
	document.EditorExpressao.expressao.value += str;
        document.EditorExpressao.expr.value += str;
}

function apaga()
{        
	var tam = comandos.length - 1;        
	var novoComandos = new Array(tam);
	var expressao = "";
	for(var i=0; i<tam; i++) 
	{
		novoComandos[i] = comandos[i];
		expressao += comandos[i];
	}  
	comandos[tam] = null;
	document.EditorExpressao.expressao.value = expressao;
        document.EditorExpressao.expr.value = expressao;
	comandos = novoComandos;
	
}

var encN=1;
function decodeTxt(s){
    var s1=unescape(s.substr(0,s.length-1));
    var t='';
    for(i=0;i<s1.length;i++)t+=String.fromCharCode(s1.charCodeAt(i)-s.substr(s.length-1,1));
    return unescape(t);
}

</script>

        <form name="EditorExpressao" action="EditorExpressao" method="POST">
            <div align="center">
                <table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td colspan="2"><div align="center" class="style38">Edi&ccedil;&atilde;o de Expressões </div></td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="2"><table width="400" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="80"><div align="center">Express&atilde;o:</div></td>
                                <td width="320"><div align="center">
                                    <textarea name="expressao" rows="3" cols="40" readonly="readonly" class="texto" onclick="javascript:teste()"></textarea>
                                    <input type="hidden" name="expr" value=" "/>
                                </div></td>
                            </tr>
                        </table></td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="200"><div align="center">
                            <table width="162" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="25"><div align="center"><input class="tecla" onmouseover="ddrivetip('N&uacute;mero 7','#DCE7DE',55)" onmouseout="hideddrivetip()" onclick="javascript:incluir('7');" type="button" value="7" name="t7" /></div></td>
                                    <td width="25"><div align="center"><input class="tecla" onmouseover="ddrivetip('N&uacute;mero 8','#DCE7DE',55)" onmouseout="hideddrivetip()" onclick="javascript:incluir('8');" type="button" value="8" name="t8" /></div></td>
                                    <td width="25"><div align="center"><input class="tecla" onmouseover="ddrivetip('N&uacute;mero 9','#DCE7DE',55)" onmouseout="hideddrivetip()" onclick="javascript:incluir('9')" type="button" value="9" name="t9" /></div></td>
                                    <td width="34"><div align="center"><input class="tecla" onmouseover="ddrivetip('Divis&acirc;o<br>Ex.: 3 / 2 = 1.5','#DCE7DE',70)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' / ')" type="button" value="/" name="/" /></div></td>                                    
                                    <td width="34"><div align="center"><input class="tecla" onmouseover="ddrivetip('Apaga &uacute;ltimo comando','#DCE7DE',130)" onmouseout="hideddrivetip()" onclick="javascript:apaga()" type="button" value="Del" name="del" /></div></td>                                    
                                </tr>
                                <tr>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('N&uacute;mero 4','#DCE7DE',55)" onmouseout="hideddrivetip()" onclick="javascript:incluir('4')" type="button" value="4" name="t4" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('N&uacute;mero 5','#DCE7DE',55)" onmouseout="hideddrivetip()" onclick="javascript:incluir('5')" type="button" value="5" name="t5" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('N&uacute;mero 6','#DCE7DE',55)" onmouseout="hideddrivetip()" onclick="javascript:incluir('6')" type="button" value="6" name="t6" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Multiplica&ccedil;&acirc;o<br>Ex.: 3 * 2 = 6','#DCE7DE',70)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' * ')" type="button" value="*" name="*" /></div></td>        
									<td><div align="center"><input class="tecla" onmouseover="ddrivetip('Espa&ccedil;o','#DCE7DE',55)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' ')" type="button" value=" " name="espaco" /></div></td>                             
                                </tr>
                                <tr>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('N&uacute;mero 1','#DCE7DE',55)" onmouseout="hideddrivetip()" onclick="javascript:incluir('1')" type="button" value="1" name="1" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('N&uacute;mero 2','#DCE7DE',55)" onmouseout="hideddrivetip()" onclick="javascript:incluir('2')" type="button" value="2" name="2" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('N&uacute;mero 3','#DCE7DE',55)" onmouseout="hideddrivetip()" onclick="javascript:incluir('3')" type="button" value="3" name="3" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Adi&ccedil;&acirc;o<br>Ex.: 3 + 2 = 5','#DCE7DE',70)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' + ')" type="button" value="+" name="+" /></div></td>                                    
                                </tr>
                                <tr>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('N&uacute;mero 0','#DCE7DE',55)" onmouseout="hideddrivetip()" onclick="javascript:incluir('0')" type="button" value="0" name="0" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Abre par&ecirc;nteses','#DCE7DE',70)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' ( ')" type="button" value="(" name="(" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Fecha par&ecirc;nteses','#DCE7DE',70)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' ) ')" type="button" value=")" name=")" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Subitra&ccedil;&acirc;o<br>Ex.: 3 - 2 = 1','#DCE7DE',70)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' - ')" type="button" value="-" name="-" /></div></td>                                    
                                </tr>
                                <tr>
                                    <td width="41"><div align="center"><input class="tecla" onmouseover="ddrivetip('Potencia&ccedil;&acirc;o<br>Ex.: 3 ^ 2 = 9','#DCE7DE',65)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' ^ ')" type="button" value="^" name="potencia" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('e = 2.718281','#DCE7DE',65)" onmouseout="hideddrivetip()" onclick="javascript:incluir('e')" type="button" value="e " name="e" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('&pi; = 3.141592','#DCE7DE',65)" onmouseout="hideddrivetip()" onclick="javascript:incluir('pi')" type="button" value="pi " name="pi" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Separador Decimal','#DCE7DE',110)" onmouseout="hideddrivetip()" onclick="javascript:incluir('.')" type="button" value="." name="." /></div></td>
                                </tr>
                                <tr>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Divis&atilde;o Inteira<br>Ex.: 3 mod 2 = 1','#DCE7DE',90)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' mod ')" type="button" value="mod" name="mod" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Valor Absoluto<br>Ex.: abs -4 = 4','#DCE7DE',90)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' abs ')" type="button" value="abs" name="abs" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Logar&iacute;tmo<br>Ex.: log 100 10 = log<sub>10</sub>100 = 2','#DCE7DE',150)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' log ')" type="button" value="log" name="log" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Raiz Quadrada<br>Ex.: sqrt 4 = 2','#DCE7DE',80)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' sqrt ')" type="button" value="sqrt" name="sqrt" /></div></td>                                   
                                </tr>
                                <tr>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Contador de Agentes<br>Ex.: cont #A.cor=branco retorna a quantidade de agentes A com cor branca','#DCE7DE',170)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' cont ')" type="button" value="cont" name="cont" /></div></td>   
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Seno de um &acirc;ngulo em graus<br>Ex.: sin 30 = 0.5','#DCE7DE',170)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' sin ')" type="button" value="sin" name="sin" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Cosseno de um &acirc;ngulo em graus<br>Ex.: cos 60 = 0.5','#DCE7DE',190)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' cos ')" type="button" value="cos" name="cos" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Tangente de um &acirc;ngulo em graus<br>Ex.: tan 45 = 1','#DCE7DE',190)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' tan ')" type="button" value="tan" name="tan"/></div></td>
                                </tr>
				<tr>
                                    <td colspan="2"><div align="center"><input class="teclaDupla" onmouseover="ddrivetip('N&uacute;mero Rand&ocirc;mico<br>Ex.: random 3 retorna 0, 1 ou 2','#DCE7DE',175)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' random ')" type="button" value="random" name="random" /></div></td>
                                    <td colspan="2"><div align="center"><input class="teclaDupla2" onmouseover="ddrivetip('N&uacute;mero Rand&ocirc;mico Flutuante<br>Ex.: random-float 3 retorna um n&uacute;mero flutuante menor que 3','#DCE7DE',180)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' random-float ')" type="button" value="random-float" name="random-float" /></div></td>
				</tr>
                                <% if (logica) { %>
                                <tr>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Sinal de Maior<br>Ex.: 1 > 0 &eacute; verdadeiro','#DCE7DE',120)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' > ')" type="button" value=">" name="maior" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Sinal de Menor<br>Ex.: 1 < 0 &eacute; falso','#DCE7DE',90)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' < ')" type="button" value="<" name="menor" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Sinal de Igual<br>Ex.: 1 = 0 &eacute; falso','#DCE7DE',90)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' = ')" type="button" value="=" name="igual" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Sinal de Diferente<br>Ex.: 1 != 0 &eacute; verdadeiro','#DCE7DE',130)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' != ')" type="button" value="!=" name="diferente"/></div></td>
                                </tr>
                                <tr>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Sinal de Maior ou Igual<br>Ex.: 1 >= 0 &eacute; verdadeiro','#DCE7DE',130)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' >= ')" type="button" value=">=" name="maiorIgual" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Sinal de Menor ou Igual<br>Ex.: 1 <= 0 &eacute; falso','#DCE7DE',130)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' <= ')" type="button" value="<=" name="menorIgual" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('E L&oacute;gico<br>Ex.: 1 > 0 and 2 > 1 &eacute; verdadeiro','#DCE7DE',170)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' and ')" type="button" value="and" name="eLogico" /></div></td>
                                    <td><div align="center"><input class="tecla" onmouseover="ddrivetip('Ou L&oacute;gico<br>Ex.: 1 > 0 or 2 < 1 &eacute; verdadeiro','#DCE7DE',160)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' or ')" type="button" value="or" name="ouLogico"/></div></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><div align="center"><input class="teclaDupla" onmouseover="ddrivetip('Verdadeiro (true)','#DCE7DE',130)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' true ')" type="button" value="TRUE" name="verdadeiro" /></div></td>
                                    <td colspan="2"><div align="center"><input class="teclaDupla" onmouseover="ddrivetip('Falso (false)','#DCE7DE',130)" onmouseout="hideddrivetip()" onclick="javascript:incluir(' false ')" type="button" value="FALSE" name="falso" /></div></td>
                                </tr>
                                <% } %>
                            </table>
                        </div></td>
						
                        <td width="200"><div align="center">
							<table width="200" cellpadding="0" cellspacing="0">
                                                            <% Modelo modelo = (Modelo) session.getAttribute("modelo");
                                                               if (vars) {                                                                                                                                                                                                        
                                                                    List variaveis = new ArrayList();                    
                                                                    List varsModelo = AplCadastrarVariavel.variaveisDoModelo(modelo.getIdModelo().intValue());
                                                                    Iterator i = varsModelo.iterator();
                                                                    while(i.hasNext())
                                                                    {
                                                                        Variavel var = (Variavel) i.next();
                                                                        Agente ag = var.getAgente();
                                                                        if(var.getTipo().equals("Valor") || var.getTipo().equals("Slider"))
                                                                        {
                                                                            if(ag == null)
                                                                                variaveis.add(var.getNome());
                                                                            else 
                                                                                if(varsLocais)
                                                                                { 
                                                                                    if((!agente.equals("") && ag.getIdAgente().intValue() == Integer.parseInt(agente)) ||
                                                                                            (!agentePrincipal.equals("") && ag.getIdAgente().intValue() == Integer.parseInt(agentePrincipal)))
                                                                                        variaveis.add(var.getNome());  
                                                                                }
                                                                        }
                                                                        else
                                                                            if (logica)
                                                                            {
                                                                                if(ag == null)
                                                                                    variaveis.add(var.getNome());
                                                                                else
                                                                                    if(varsLocais)
                                                                                    { 
                                                                                        if(agente.equals("")) //nao tem agente envolvido
                                                                                            variaveis.add(var.getNome());
                                                                                        else
                                                                                            if(ag.getIdAgente().intValue() == Integer.parseInt(agente) ||
                                                                                                (!agentePrincipal.equals("") && ag.getIdAgente().intValue() == Integer.parseInt(agentePrincipal)))
                                                                                                    variaveis.add(var.getNome());
                                                                                    }
                                                                            }
                                                                    }
                                                                    if(variaveis.size() > 0) {
                                                                %>
								<tr>
									<td><div align="center">
										<table width="200" cellpadding="1" cellspacing="1">
											<tr>
												<td><div align="center">
													Vari&aacute;veis
												</div></td>
											</tr>
											<tr>
												<td><div align="center">
													<select name="varGlobal">
                                                                                                                <%for(int j=0; j<variaveis.size(); j++) {%>
                                                                                                                    <%String nomeVar = (String) variaveis.get(j);%>
                                                                                                                    <option value="<%=nomeVar%>"><%=nomeVar%></option>														
                                                                                                                <%}%>
													</select>
												</div></td>
											</tr>
											<tr>
												<td><div align="center">
													<input type="button" name="enterVar" value="Enter" class="tecla" onClick="javascript:incluir(document.EditorExpressao.varGlobal.value + ' ')">
												</div></td>
											</tr>		
										</table>
									</div></td>
								</tr>
								
								<tr><td>&nbsp;</td></tr>
                                                                <%}}%>
								
                                                                <%if(condPos) {%>
                                                                <tr>
                                                                    <%String nomeAg1 = AplCadastrarAgente.obterAgente(Integer.parseInt(agente)).getNome();%>
                                                                    <td colspan="2" align="center">Agente <%=nomeAg1%></td>
                                                                </tr>
								<tr>
                                                                    <td colspan="2" align="center"><input name="botaoXCor" type="button" value="Coordenada X" onclick="javascript:incluir(' <%=nomeAg1 + ".xcor"%> ')"></td>									
								</tr>								
								<tr>
                                                                    <td colspan="2" align="center"><input name="botaoYCor" type="button" value="Coordenada Y" onclick="javascript:incluir(' <%=nomeAg1 + ".ycor"%> ')"></td>
								</tr>
                                                                    <% if(numAgentes.equals("2")) { %>
                                                                <tr><td>&nbsp;</td></tr>
                                                                <tr>
                                                                    <%String nomeAg2 = AplCadastrarAgente.obterAgente(Integer.parseInt(agentePrincipal)).getNome();%>
                                                                    <td colspan="2" align="center">Agente <%=nomeAg2%></td>
                                                                </tr>
								<tr>
                                                                    <td colspan="2" align="center"><input name="botaoXCor" type="button" value="Coordenada X" onclick="javascript:incluir(' <%=nomeAg2 + ".xcor"%> ')"></td>									
								</tr>								
								<tr>
                                                                    <td colspan="2" align="center"><input name="botaoYCor" type="button" value="Coordenada Y" onclick="javascript:incluir(' <%=nomeAg2 + ".ycor"%> ')"></td>
								</tr>
                                                                <%}%>
								
								<tr><td>&nbsp;</td></tr>
                                                                <%}%>
                                                                
                                                                <%if(variavel1) {%>
								<tr>
                                                                        <td colspan="2" align="center"><input name="var1" type="button" value="<%=nomeVar1%>" onclick="javascript:incluir(' <%=nomeVar1%> ')"></td>									
								</tr>	
                                                                <%if(variavel2) {%>
                                                                <tr>
                                                                        <td colspan="2" align="center"><input name="var2" type="button" value="<%=nomeVar2%>" onclick="javascript:incluir(' <%=nomeVar2%> ')"></td>									
								</tr>	
                                                                <%}%>
								<tr><td>&nbsp;</td></tr>
                                                                <%}%>
								
                                                                <%
                                                                    List agentes = AplCadastrarAgente.agentesDoModelo(modelo.getIdModelo().intValue());
                                                                    if(agentes != null && agentes.size() > 0) {
                                                                %>
								<tr>
									<td><div align="center">
										<table width="200" cellpadding="1" cellspacing="1">
											<tr>
												<td colspan="2"><div align="center">
													Agentes
												</div></td>
											</tr>
											<tr>
												<td colspan="2"><div align="center">
													<select name="agente2" id="t">
                                                                                                                <%for(int j=0; j<agentes.size(); j++){%>
                                                                                                                    <%String nomeAg = ((Agente) agentes.get(j)).getNome();%>
                                                                                                                        <option rel="<%="Ag" + nomeAg%>" value="<%=nomeAg%>"><%=nomeAg%></option>
                                                                                                                <%}%>
													</select>
												</div></td>
											</tr>
                                                                                        <%for(int j=0; j<agentes.size(); j++){%>
                                                                                        <%  Agente ag = (Agente) agentes.get(j);
                                                                                            String nomeAg = ag.getNome();%>
											<tr rel="<%="Ag" + nomeAg%>">
												<td><div align="center">
                                                                                                    <label for="<%="Ag" + nomeAg%>">
													Condi&ccedil;&atilde;o:
                                                                                                    </label>
												</div></td>
												<td><div align="center">
													<select name="condicao" id="<%="Ag" + nomeAg%>" onChange="javascript:mudaOpcao()">
														<option rel= "none" value="nenhum">Nenhuma</option>
                                                                                                                
                                                                                                                <option rel="cor" value="cor">Cor</option>                                                                                                                
                                                                                                                
                                                                                                                <%if(ag.getTipo().equals("Movel")) {%>
														<option rel="forma" value="forma">Forma</option>
                                                                                                                <%}%>
                                                                                                                
                                                                                                                <option rel="pos" value="xcor">Coordenada X</option>
														<option rel="pos" value="ycor">Coordenada Y</option>                                                                                                                
                                                                                                                
                                                                                                             <%   List variaveis2 = AplCadastrarVariavel.variaveisDoAgente(ag.getIdAgente().intValue());
                                                                                                                  for(int k=0; k<variaveis2.size(); k++)
                                                                                                                  {
                                                                                                                    Variavel var = (Variavel) variaveis2.get(k);
                                                                                                                    String nomeVar = var.getNome();
                                                                                                                    if(var.getTipo().equals("Logica")) {%>
                                                                                                                        <option value="<%="L" + nomeVar%>" rel="logica"><%=nomeVar%></option>
                                                                                                                    <%} else {%>
                                                                                                                        <option value="<%="V" + nomeVar%>" rel="valor"><%=nomeVar%></option>
                                                                                                                    <%}%>
                                                                                                                 <%}%>
											      </select>
												</div></td>
											</tr>
                                                                                        <%}%>
											<tr rel="cor">
												<td><div align="center">
													<label for="cor">
														Op&ccedil;&atilde;o:
													</label>
												</div></td>
												<td><div align="center">
													<select name="cor" id="cor">
														<option value="amarelo">Amarelo</option>
														<option value="azul">Azul</option>
														<option value="azulclaro">Azul Claro</option>
														<option value="branco">Branco</option>
														<option value="ciano">Ciano</option>
														<option value="cinza">Cinza</option>
														<option value="laranja">Laranja</option>
														<option value="magenta">Magenta</option>
														<option value="marrom">Marrom</option>
														<option value="preto">Preto</option>
														<option value="rosa">Rosa</option>
														<option value="verde">Verde</option>
														<option value="verdelimao">Verde Lim&atilde;o</option>
														<option value="verdeturquesa">Verde Turquesa</option>
														<option value="vermelho">Vermelho</option>
														<option value="violeta">Violeta</option>
													</select>
												</div></td>
											</tr>
											<tr rel="forma">
												<td><div align="center">
													<label for="forma">
														Op&ccedil;&atilde;o:
													</label>
												</div></td>
												<td><div align="center">
													<select name="forma" id="forma">
														<option value="alvo">Alvo</option>
														<option value="arvore">&Aacute;rvore</option>
														<option value="aviao">Avi&atilde;o</option>
														<option value="bandeira">Bandeira</option>
														<option value="borboleta">Borboleta</option>
														<option value="caminhao">Caminh&atilde;o</option>
														<option value="carro">Carro</option>
														<option value="casa">Casa</option>
														<option value="cilindro">Cilindro</option>
														<option value="cilindro3d">Cilindro 3D</option>
														<option value="circulo1">C&iacute;rculo 1</option>
														<option value="circulo2">C&iacute;rculo 2</option>
														<option value="cubo">Cubo</option>
														<option value="estrela">Estrela</option>
														<option value="faceFeliz">Face Feliz</option>
														<option value="faceNeutra">Face Neutra</option>
														<option value="faceTriste">Face Triste</option>
														<option value="flor">Flor</option>
														<option value="folha">Folha</option>
														<option value="inseto">Inseto</option>
														<option value="linha">Linha</option>
														<option value="linhaPequena">Linha Pequena</option>
														<option value="peixe">Peixe</option>
														<option value="pentagono">Pent&aacute;gono</option>
														<option value="pessoa">Pessoa</option>
														<option value="planta">Planta</option>
														<option value="ponto">Ponto</option>
														<option value="quadrado1">Quadrado 1</option>
														<option value="quadrado2">Quadrado 2</option>
														<option value="roda">Roda</option>
														<option value="seta">Seta</option>
														<option value="setaPadrao">Seta Padr&atilde;o</option>
														<option value="tartaruga">Tartaruga</option>
														<option value="triangulo1">Tri&acirc;ngulo 1</option>
														<option value="triangulo2">Tri&acirc;ngulo 2</option>
														<option value="vaca">Vaca</option>
														<option value="x">X</option>
													</select>
												</div></td>
											</tr>
											<tr rel="logica">
												<td><div align="center">
													<label for="valorLogio">
														Op&ccedil;&atilde;o:
													</label>
												</div></td>
												<td><div align="center">
													<select name="valorLogico" id="valorLogico">
														<option value="falso">Falso</option>
														<option value="verdadeiro">Verdadeiro</option>
													</select>
												</div></td>
											</tr>	
											<tr rel="valor">
												<td><div align="center">
													<label for="sinalValor">
														Sinal:
													</label>
												</div></td>
												<td><div align="center">
													<select name="sinalValor" id="sinalValor">
														<option value="=">=</option>
														<option value="!=">!=</option>
														<option value=">">></option>
														<option value="<"><</option>
														<option value=">=">>=</option>
														<option value="<="><=</option>
													</select>
												</div></td>
											</tr>		
											<tr rel="valor">
												<td><div align="center">
													<label for="valor">
														Op&ccedil;&atilde;o:
													</label>
												</div></td>
												<td><div align="center">
													<input type="text" id="valor" value="0" size="7" onChange="javascript:verificarNumero()">
												</div></td>
											</tr>	
											<tr rel="pos">
												<td><div align="center">
													<label for="sinalPos">
														Sinal:
													</label>
												</div></td>
												<td><div align="center">
													<select name="sinalPos" id="sinalPos">
														<option value="=">=</option>
														<option value="!=">!=</option>
														<option value=">">></option>
														<option value="<"><</option>
														<option value=">=">>=</option>
														<option value="<="><=</option>
													</select>
												</div></td>
											</tr>	
											<tr rel="pos">
												<td><div align="center">
													<label for="pos">
														Op&ccedil;&atilde;o:
													</label>
												</div></td>
												<td><div align="center">
													<input type="text"id id="pos" value="0" size="3" onChange="javascript:verificarPos()">
												</div></td>
											</tr>
																				
											
											<tr>
												<td colspan="2"><div align="center">                                                                                                    
													<input type="button" name="enterAgentes" value="Enter" class="tecla" onClick="javascript:agentes()">
												</div></td>
											</tr>		
										</table>
									</div></td>
								</tr>
                                                                <%}%>
								
						  </table>
						</div></td>
						
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center">
                            <input name="pai" type="hidden" id="pai" value="<%=pai%>">
                            <input type="hidden" name="nomeAntigo" value="<%=nomeAntigo%>"/>
                            <input type="hidden" name="status" value="<%=status%>"/>
                            <input type="hidden" name="nome" value="<%=nome%>"/>
                            <input type="hidden" name="agente" value="<%=agente%>"/>
                            <input type="hidden" name="agentePrincipal" value="<%=agentePrincipal%>"/>
                            <input type="hidden" name="var" value="<%=idVar%>"/>
                            <input type="hidden" name="idVar1" value="<%=idVar1%>"/>
                            <input type="hidden" name="idVar2" value="<%=idVar2%>"/>
                            <input type="hidden" name="casasDecimais" value="<%=casasDecimais%>"/>
                            <input type="hidden" name="id" value="<%=id%>">
                            <input type="hidden" name="numAgentes" value="<%=numAgentes%>">
                            <input type="hidden" name="idComposicao" value="<%=idComposicao%>">
                            <input type="hidden" name="idAcao" value="<%=acao%>">
                            <input type="hidden" name="idIni" value="<%=idIni%>">
                            <input type="hidden" name="idPerc" value="<%=idPerc%>">
                            <input name="salvarExp" type="submit" id="salvarExp" value="Salvar Expressão">
                        </div></td>
                    </tr>
                </table>
            </div>
        </form>
    </body>
</html>
