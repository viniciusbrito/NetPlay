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
    String forma = "";

    status = request.getParameter("status");
    status = request.getParameter("forma");

    if(status == null)
        status = " ";

    if(forma == null)
        forma = "";
    
    List agentes;
    boolean temAgMovel = false;
    Modelo m1 = (Modelo)session.getAttribute("modelo");
    agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
    
    for(int i=0; i<agentes.size(); i++){
        Agente agente = (Agente)agentes.get(i);
        if (agente.getTipo().compareTo("Movel") == 0)
            temAgMovel = true;
    }
    
    if(!temAgMovel) { %>
        <p>N&atilde;o &eacute; poss&iacute;vel criar essa a&ccedil;&atilde;o devido a inexist&eacute;ncia de agentes m&oacute;veis nesse modelo</p>
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

            function MM_openBrWindow(theURL,winName,features) { //v2.0
            window.open(theURL,winName,features);
            }
            //-->
        </script>
    </head>

    <body>

        <div align="center">
            <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="4"><div align="center" class="style38">Alterar Forma </div></td>
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
                                    <form action="controlador.ControladorWebCCA" method="post" name="acAltMov" id="acAltMov">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Agente</div></td>
                                                <td colspan="2"><div align="left">
                                                    <select name="agente" id="agente">
                                                        <%for(int i=0; i<agentes.size(); i++){
                                                            Agente agente = (Agente)agentes.get(i);
                                                            if (agente.getTipo().compareTo("Movel") == 0) {%>
                                                        <option value= <%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                        <%}}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                <div align="left"> Nova Forma</div></td>
                                                <td width="116"><div align="left">
                                                    <select name="forma" id="forma"  onchange="imagemForma.src='formas/' + this.value + '.gif'">                                                        
                                                        <option value="abelha">abelha
                                                        <option value="abobora">ab&oacute;bora
                                                        <option value="agua">agua
                                                        <option value="alvo">alvo
                                                        <option value="ambulancia">ambul&acirc;ncia
                                                        <option value="aranha">aranha
                                                        <option value="arvore">&aacute;rvore
                                                        <option value="aviao">avi&atilde;o
                                                        <option value="aviaoantigo">avi&atilde;o antigo
                                                        <option value="balao">bal&atilde;o
                                                        <option value="banana">banana
                                                        <option value="bandeira">bandeira
                                                        <option value="barco">barco
                                                        <option value="barraca">barraca
                                                        <option value="bicicleta">bicicleta
                                                        <option value="boladebasquete">bola de basquete
                                                        <option value="boladetenis">bola de t&eacute;nis
                                                        <option value="borboleta">borboleta
                                                        <option value="cachorro">cachorro
                                                        <option value="cactus">cactus
                                                        <option value="caixa">caixa
                                                        <option value="caminhao">caminh&atilde;o
                                                        <option value="canhao">canh&atilde;o
                                                        <option value="carro">carro
                                                        <option value="carro2">carro 2
                                                        <option value="carta">carta
                                                        <option value="casa">casa
                                                        <option value="casa2">casa 2
                                                        <option value="casa3">casa 3
                                                        <option value="chave">chave
                                                        <option value="cilindro">cilindro
                                                        <option value="circulo1">c&iacute;rculo 1
                                                        <option value="circulo2">c&iacute;rculo 2
                                                        <option value="coelho">coelho
                                                        <option value="computador">computador
                                                        <option value="coracao">cora&ccedil;&atilde;o
                                                        <option value="coroa">coroa
                                                        <option value="cubo">cubo
                                                        <option value="dardo">dardo
                                                        <option value="dente">dente
                                                        <option value="discovoador">disco voador
                                                        <option value="dolar">d&oacute;lar
                                                        <option value="domino1">domin&oacute; 1
                                                        <option value="domino2">domin&oacute; 2
                                                        <option value="domino3">domin&oacute; 3
                                                        <option value="domino4">domin&oacute; 4
                                                        <option value="domino5">domin&oacute; 5
                                                        <option value="domino6">domin&oacute; 6
                                                        <option value="escavadora">escavadora
                                                        <option value="esquilo">esquilo
                                                        <option value="estrela">estrela
                                                        <option value="exclamacao">exclama&ccedil;&atilde;o
                                                        <option value="facefeliz">face feliz
                                                        <option value="faceneutra">face neutra
                                                        <option value="facetriste">face triste
                                                        <option value="falcao">falc&atilde;o
                                                        <option value="fantasma">fantasma
                                                        <option value="flor">flor
                                                        <option value="fogo">fogo
                                                        <option value="foguete">foguete
                                                        <option value="folha">folha
                                                        <option value="formiga">formiga
                                                        <option value="garrafa">garrafa
                                                        <option value="gato">gato
                                                        <option value="gota">gota
                                                        <option value="hexagono">hex&aacute;gono
                                                        <option value="ima">&iacute;m&atilde;
                                                        <option value="industria">ind&uacute;stria
                                                        <option value="inseto">inseto
                                                        <option value="lagarta">lagarta
                                                        <option value="lapis">l&aacute;pis
                                                        <option value="linha">linha
                                                        <option value="linhapequena">linha pequena
                                                        <option value="livro">livro
                                                        <option value="lixeira">lixeira
                                                        <option value="lobo">lobo
                                                        <option value="loja">loja
                                                        <option value="losango">losango
                                                        <option value="lua">lua
                                                        <option value="maca">ma&ccedil;&atilde;
                                                        <option value="madeira">madeira
                                                        <option value="moedacara">moeda cara
                                                        <option value="moedacoroa">moeda coroa
                                                        <option value="monumento">monumento
                                                        <option value="morango">morango
                                                        <option value="notamusical1">nota musical 1
                                                        <option value="notamusical2">nota musical 2
                                                        <option value="nuvem">nuvem
                                                        <option value="olho">olho
                                                        <option value="onibus">&ocirc;nibus
                                                        <option value="ovelha">ovelha
                                                        <option value="ovo">ovo
                                                        <option value="passaro">p&aacute;ssaro
                                                        <option value="pedras">pedras
                                                        <option value="pegada">pegada
                                                        <option value="peixe">peixe
                                                        <option value="pentagono">pent&aacute;gono
                                                        <option value="pessoa">pessoa
                                                        <option value="pessoaempresario">pessoa empres&aacute;rio
                                                        <option value="pessoaestudante">pessoa estudante
                                                        <option value="pessoafazendeiro">pessoa fazendeiro
                                                        <option value="pessoaformando">pessoa formando
                                                        <option value="pessoagarcom">pessoa gar&ccedil;om
                                                        <option value="pessoagari">pessoa gari
                                                        <option value="pessoalenhador">pessoa lenhador
                                                        <option value="pessoamedico">pessoa m&eacute;dico
                                                        <option value="pessoapolicial">pessoa policial
                                                        <option value="pessoasoldado">pessoa soldado
                                                        <option value="pincel">pincel
                                                        <option value="pinheiro">pinheiro
                                                        <option value="pinodeboliche">pino de boliche
                                                        <option value="planta">planta
                                                        <option value="polvo">polvo
                                                        <option value="ponto">ponto
                                                        <option value="quadrado1">quadrado 1
                                                        <option value="quadrado2">quadrado 2
                                                        <option value="raio">raio
                                                        <option value="rato">rato
                                                        <option value="relogio">rel&oacute;gio
                                                        <option value="roda">roda
                                                        <option value="sapo">sapo
                                                        <option value="seta">seta
                                                        <option value="setafina">seta fina
                                                        <option value="setapadrao">seta padr&atilde;o
                                                        <option value="sol">sol
                                                        <option value="tanque">tanque
                                                        <option value="tartaruga">tartaruga
                                                        <option value="telefone">telefone
                                                        <option value="tijolos">tijolos
                                                        <option value="trem">trem
                                                        <option value="triangulo1">tri&acirc;ngulo 1
                                                        <option value="triangulo2">tri&acirc;ngulo 2
                                                        <option value="tubarao">tubar&atilde;o
                                                        <option value="vaca">vaca
                                                        <option value="vagao">vag&atilde;o
                                                        <option value="van">van
                                                        <option value="x">x
                                                        <option value="xadrezbispo">xadrez bispo
                                                        <option value="xadrezcavalo">xadrez cavalo
                                                        <option value="xadrezpeao">xadrez pe&atilde;o
                                                        <option value="xadrezrainha">xadrez rainha
                                                        <option value="xadrezrei">xadrez rei
                                                        <option value="xadreztorre">xadrez torre
                                                </select>
                                                </div></td>
                                                <td><div align="center"><img name=imagemForma src="formas/abelha.gif" align="center" width="30" height="30"/></div></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="3"><div align="center">
                                                    <input name="salvar condicao" type="submit" id="salvar condicao" value="Salvar Ação">
                                                    <input name="metodo" type="hidden" value="cadastroAcao">
                                                    <input name="acao" type="hidden" value="incluir">
                                                    <input name="TipoAcao" type="hidden" id="TipoAcao" value="AltFor">
                                                </div></td>
                                            </tr>
                                            <!--<tr>
                                                <td><div align="center">
                                                    <input name="metodo" type="hidden" value="cadastroAcao">
                                                    <input name="acao" type="hidden" value="incluir">
                                                    <input name="TipoAcao" type="hidden" id="TipoAcao" value="AltFor">
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td><div align="center"><span class="style5"><%= status %></span></div></td>
                                            </tr>-->
                                        </table>
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
