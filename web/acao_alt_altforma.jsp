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
    String status = request.getParameter("status");
    if(status == null)
        status = " ";

    String id = request.getParameter("id");
    AlterarForma acao = (AlterarForma) AplCadastrarAcao.obterAcao(Integer.parseInt(id));
    String forma = acao.getNovaForma();
    int idAgente = acao.getAgenteMovel().getIdAgente().intValue();
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
                                                    <% List agentes;
                                                        Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                        agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
                                                    %>
                                                    <select name="agente" id="agente">
                                                        <%for(int i=0; i<agentes.size(); i++){
                                                            Agente agente = (Agente)agentes.get(i);
                                                            if (agente.getTipo().compareTo("Movel") == 0) {
                                                                if(idAgente == agente.getIdAgente().intValue()) { %>
                                                                    <option selected value= <%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                                <% } else { %>
                                                                    <option value= <%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                        <%}}}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                <div align="left"> Nova Forma</div></td>
                                                <td width="116"><div align="left">
                                                    <select name="forma" id="forma" onchange="imagemForma.src='formas/' + this.value + '.gif'">
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
                                                <% if (forma.equals("")) { %>
                                                <td><div align="center"><img name=imagemForma src="formas/abelha.gif" align="center" width="30" height="30"/></div></td>
                                          <% } else { %>
                                                <td><div align="center"><img name=imagemForma src="<%="formas/" + forma + ".gif"%>" align="center" width="30" height="30"/></div></td>
                                          <% } %>
                                            </tr>
                                            <tr>
                                                <td colspan="3">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="3"><div align="center">
                                                    <input name="salvar condicao" type="submit" id="salvar condicao" value="Salvar Ação">
                                                    <input name="id" type="hidden" value="<%=id%>">
                                                    <input name="metodo" type="hidden" value="cadastroAcao">
                                                    <input name="acao" type="hidden" value="alterar">
                                                    <input name="TipoAcao" type="hidden" id="TipoAcao" value="AltFor">
                                                </div></td>
                                            </tr>
                                            <!--<tr>
                                                <td><div align="center">
                                                    <input name="id" type="hidden" value="<%=id%>">
                                                    <input name="metodo" type="hidden" value="cadastroAcao">
                                                    <input name="acao" type="hidden" value="alterar">
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
