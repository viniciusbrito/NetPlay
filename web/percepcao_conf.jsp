<%-- 
    Document   : percepcao_conf
    Created on : 18/12/2014, 03:40:07
    Author     : vinicius
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java"
import="cgt.*" 
import="cdp.*"
import="java.util.*"
errorPage="" %>
<!DOCTYPE html>
<%
    int idCondicao = Integer.parseInt(request.getParameter("idCondicao"));
    Percepcao percepcao = (Percepcao) AplCadastrarCondicao.obterCondicao(idCondicao);
    //Agente agentePrincipal = AplCadastrarAgente.obterAgente(percepcao.getIdAgentePrincipal());
    Agente agente = AplCadastrarAgente.obterAgente(percepcao.getAgente().getIdAgente());
    Boolean exibirCor = true;
    Boolean exibirForma = true;
    Boolean exibirTamanho = true;
    String cor  = "";
    String forma = "";
    int tamanho = 0;
    
    //variaveis do agente principal
    //List APVariaveis = AplCadastrarVariavel.variaveisDoAgente(agentePrincipal.getIdAgente());
    //variaveis do agente
    List AVariaveis = AplCadastrarVariavel.variaveisDoAgente(agente.getIdAgente());
    //variaveis PercepcaoAvancado
    List PAVariaveis = AplCadastrarCondicao.obterVariaveisPercepcaoAvancado(idCondicao);
    //variaveis PercepcaoCFT
    List PCFTVariaveis = AplCadastrarCondicao.obterVariaveisPercepcaoCFT(idCondicao);
    
    List PAV = new ArrayList();
    //Remover da lista as variaveis usadas
    for (int k = 0; k < PAVariaveis.size(); k++)
    {
        PercepcaoAvancado percAv = (PercepcaoAvancado) PAVariaveis.get(k);
        /*if(percAv.getAgente().equals("principal"))
        {
            Variavel var = AplCadastrarVariavel.obterVariavel(percAv.getIdVariavel());
            for (int i = 0; i < APVariaveis.size(); i++)
            {
                Variavel var2 = (Variavel) APVariaveis.get(i);
                if(var.getIdVariavel().equals(var2.getIdVariavel()))
                    APVariaveis.remove(i);
            }
        } */
        
        if(percAv.getAgente().equals("percebido"))
        {
            Variavel var = AplCadastrarVariavel.obterVariavel(percAv.getIdVariavel());
            for (int i = 0; i < AVariaveis.size(); i++)
            {
                Variavel var2 = (Variavel) AVariaveis.get(i);
                if(var.getIdVariavel().equals(var2.getIdVariavel()))
                    AVariaveis.remove(i);
            }
        }
    }
    
    for(int k = 0; k < PCFTVariaveis.size(); k++)
    {
        PercepcaoCFT percCFT = (PercepcaoCFT) PCFTVariaveis.get(k);
        if(percCFT.getVar().equals("cor"))
            exibirCor = false;
        if(percCFT.getVar().equals("forma"))
            exibirForma = false;
        if(percCFT.getVar().equals("tamanho"))
            exibirTamanho = false;
    }

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Percepção Avançada</title>
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
            #va1 {float: left}
            #va2 {float: right;}
            #va3 {float: left}
            #va4 {float: right;}
            .cont {float:left; width: 100%;}
            -->
        </style>
        <script language="JavaScript" type="text/JavaScript">
            function encode(s){                
            var t = '';
            for(var i=0;i<s.length;i++)
            {
            var l = s.charAt(i);
            if(l == '-') t += '%2D';
            else if(l == '@') t += '%40';
            else if(l == '*') t += '%2A';
            else if(l == '+') t += '%2B';
            else if(l == '_') t += '%5F';
            else if(l == '/') t += '%2F';
            else if(l == '.') t += '%2E';
            else t += escape(l);
            }
            return t;       
            }

            function expressao(idPerc, expressao)
            {

                window.location.href='editarexp.jsp?pai=percepcao_conf&expr=' + encode(expressao) + '&idPerc='+idPerc;
            }
            
            function alterar(idPerc, expressao)
            {
                window.location.href='controlador.ControladorWebCCA?metodo=cadPercAvan&acao=alterar&idPerc='+idPerc+'&expressao='+expressao;
            }
            function alterarCFT(idPerc, operador, valor)
            {
                window.location.href='controlador.ControladorWebCCA?metodo=cadPercCFT&acao=alterar&idPerc='+idPerc+'&operador='+operador+'&valor='+valor;
            }
        </script>
    </head>
    <body>
        <h1>Condi&ccedil;&otilde;es do agente percebido</h1>
        <div class="cont">    
            <div id="va3">
                <table  width="400" border="1" bgcolor="#BBD0BF">
                    <tr> <td colspan="3"><div align="center" class="style38"> Variaveis do agente: <%= agente.getNome() %> </div></td> </tr>
                    <%
                        for (int k = 0; k < AVariaveis.size(); k++)
                        {
                            Variavel var = (Variavel) AVariaveis.get(k);
                            PAVariaveis.contains(var.getIdVariavel());
                            String nomeVar = var.getNome();
                            int idVar = var.getIdVariavel();
                            String valor = "";

                            if(var.getTipo().equals("Valor"))
                            {
                                VariavelValor varV = (VariavelValor) var;
                                valor = varV.getValor();
                            }

                            if(var.getTipo().equals("Logica"))
                            {
                                VariavelLogica varL = (VariavelLogica) var;
                                Boolean v;
                                v = varL.getValorLogico();
                                valor = v.toString();
                            }
                        %>
                            <tr>
                                <td><%=nomeVar%></td><td bgcolor="#333300" align="center"><a href="controlador.ControladorWebCCA?metodo=cadPercAvan&acao=incluir&idVar=<%=idVar%>&idCondicao=<%=idCondicao%>&valor=<%=valor%>&agente=percebido">Adicionar</a></td>
                            </tr>
                        <%
                        }
                    if(agente.getTipo().equals("Movel")) { 
                        if(exibirCor) { %>
                            <tr>
                                <td>Cor</td><td bgcolor="#333300" align="center"><a href="controlador.ControladorWebCCA?metodo=cadPercCFT&acao=incluir&var=cor&idCondicao=<%=idCondicao%>&idAgente=<%=agente.getIdAgente()%>">Adicionar</a></td>
                            </tr>
                        <% }
                        if(exibirForma) { %>
                            <tr>
                                <td>Forma</td><td bgcolor="#333300" align="center"><a href="controlador.ControladorWebCCA?metodo=cadPercCFT&acao=incluir&var=forma&idCondicao=<%=idCondicao%>&idAgente=<%=agente.getIdAgente()%>">Adicionar</a></td>
                            </tr>
                        <%}
                        if(exibirTamanho) { %>
                        <tr>
                            <td>Tamanho</td><td bgcolor="#333300" align="center"><a href="controlador.ControladorWebCCA?metodo=cadPercCFT&acao=incluir&var=tamanho&idCondicao=<%=idCondicao%>&idAgente=<%=agente.getIdAgente()%>">Adicionar</a></td>
                        </tr>
                <%      }
                    }
                    %>
                </table>
            </div>
            
            <div id="va4">
                <table  width="400" border="1" bgcolor="#BBD0BF">
                    <tr> <td colspan="3"><div align="center" class="style38"> Variaveis usadas no agente: <%= agente.getNome() %> </div></td> </tr>            
                <%
                    for (int k = 0; k < PAVariaveis.size(); k++)
                    {
                        PercepcaoAvancado percAv = (PercepcaoAvancado) PAVariaveis.get(k);
                        if(percAv.getAgente().equals("percebido"))
                        {
                            Variavel var = AplCadastrarVariavel.obterVariavel(percAv.getIdVariavel());
                            String nomeVar = var.getNome();
                            int idVar = var.getIdVariavel();
                            int idPerc = percAv.getIdPercepcaoAvancado();
                            String valor = percAv.getValor();
                            if(var.getTipo().equals("Valor"))
                            {
                                %>
                                    <tr>
                                        <td><%=nomeVar%></td>
                                        <td><input readonly="true" type="text" value="<%=valor%>" name="<%=nomeVar%>"/></td>
                                        <td bgcolor="#333300" align="center"><a href="javascript:expressao(<%=idPerc%>, '<%=valor%>')">Editar Express&atilde;o</a></td>
                                        <td bgcolor="#333300" align="center"><a href="controlador.ControladorWebCCA?metodo=cadPercAvan&acao=excluir&idPerc=<%=idPerc%>&idCondicao=<%=idCondicao%>">Remover</a></td>
                                    </tr>
                                <%
                            }

                            if(var.getTipo().equals("Logica"))
                            {
                                %>
                                    <tr>
                                        <td><%=nomeVar%></td>
                                        <td>
                                            <select name="<%=nomeVar%>" onchange="javascript:alterar(<%=idPerc%>, this.value)">
                                                <option <%= (valor.equals("true")? "selected" :  "") %> value="true">Verdadeiro</option>
                                                <option <%= (valor.equals("false")? "selected" :  " ") %> value="false">Falso</option>
                                            </select>
                                        </td>
                                            <td bgcolor="#333300" align="center"><a href="controlador.ControladorWebCCA?metodo=cadPercAvan&acao=excluir&idPerc=<%=idPerc%>&idCondicao=<%=idCondicao%>">Remover</a></td>
                                    </tr>
                        <%
                            }
                        }
                    }
                for(int k = 0; k < PCFTVariaveis.size(); k++)
                {
                    PercepcaoCFT percCFT = (PercepcaoCFT) PCFTVariaveis.get(k);
                    int idPerc = percCFT.getIdPercepcaoCFT();
                    if(percCFT.getVar().equals("forma")) {
                        forma = percCFT.getValor();
                        %>
                        <rt>
                            <td>Forma:</td>                            
                            <td>
                                <select name="forma" id="Forma" onchange="javascript:alterarCFT(<%=idPerc%>, 'null', this.value); imagemForma.src = 'formas/' + this.value + '.gif'">
                                    <option <%= (forma.equals("abelha") ? "selected" :  " ") %> value="abelha">abelha</option>
                                    <option <%= (forma.equals("abobora") ? "selected" :  " ") %> value="abobora">abobora</option>
                                    <option <%= (forma.equals("agua") ? "selected" :  " ") %> value="agua">agua</option>
                                    <option <%= (forma.equals("alvo") ? "selected" :  " ") %> value="alvo">alvo</option>
                                    <option <%= (forma.equals("ambulancia") ? "selected" :  " ") %> value="ambulancia">ambulancia</option>
                                    <option <%= (forma.equals("aranha") ? "selected" :  " ") %> value="aranha">aranha</option>
                                    <option <%= (forma.equals("arvore") ? "selected" :  " ") %> value="arvore">arvore</option>
                                    <option <%= (forma.equals("aviao") ? "selected" :  " ") %> value="aviao">aviao</option>
                                    <option <%= (forma.equals("aviaoantigo") ? "selected" :  " ") %> value="aviaoantigo">aviaoantigo</option>
                                    <option <%= (forma.equals("balao") ? "selected" :  " ") %> value="balao">balao</option>
                                    <option <%= (forma.equals("banana") ? "selected" :  " ") %> value="banana">banana</option>
                                    <option <%= (forma.equals("bandeira") ? "selected" :  " ") %> value="bandeira">bandeira</option>
                                    <option <%= (forma.equals("barco") ? "selected" :  " ") %> value="barco">barco</option>
                                    <option <%= (forma.equals("barraca") ? "selected" :  " ") %> value="barraca">barraca</option>
                                    <option <%= (forma.equals("bicicleta") ? "selected" :  " ") %> value="bicicleta">bicicleta</option>
                                    <option <%= (forma.equals("boladebasquete") ? "selected" :  " ") %> value="boladebasquete">boladebasquete</option>
                                    <option <%= (forma.equals("boladetenis") ? "selected" :  " ") %> value="boladetenis">boladetenis</option>
                                    <option <%= (forma.equals("borboleta") ? "selected" :  " ") %> value="borboleta">borboleta</option>
                                    <option <%= (forma.equals("cachorro") ? "selected" :  " ") %> value="cachorro">cachorro</option>
                                    <option <%= (forma.equals("cactus") ? "selected" :  " ") %> value="cactus">cactus</option>
                                    <option <%= (forma.equals("caixa") ? "selected" :  " ") %> value="caixa">caixa</option>
                                    <option <%= (forma.equals("caminhao") ? "selected" :  " ") %> value="caminhao">caminhao</option>
                                    <option <%= (forma.equals("canhao") ? "selected" :  " ") %> value="canhao">canhao</option>
                                    <option <%= (forma.equals("carro") ? "selected" :  " ") %> value="carro">carro</option>
                                    <option <%= (forma.equals("carro2") ? "selected" :  " ") %> value="carro2">carro2</option>
                                    <option <%= (forma.equals("carta") ? "selected" :  " ") %> value="carta">carta</option>
                                    <option <%= (forma.equals("casa") ? "selected" :  " ") %> value="casa">casa</option>
                                    <option <%= (forma.equals("casa2") ? "selected" :  " ") %> value="casa2">casa2</option>
                                    <option <%= (forma.equals("casa3") ? "selected" :  " ") %> value="casa3">casa3</option>
                                    <option <%= (forma.equals("chave") ? "selected" :  " ") %> value="chave">chave</option>
                                    <option <%= (forma.equals("cilindro") ? "selected" :  " ") %> value="cilindro">cilindro</option>
                                    <option <%= (forma.equals("circulo1") ? "selected" :  " ") %> value="circulo1">circulo1</option>
                                    <option <%= (forma.equals("circulo2") ? "selected" :  " ") %> value="circulo2">circulo2</option>
                                    <option <%= (forma.equals("coelho") ? "selected" :  " ") %> value="coelho">coelho</option>
                                    <option <%= (forma.equals("computador") ? "selected" :  " ") %> value="computador">computador</option>
                                    <option <%= (forma.equals("coracao") ? "selected" :  " ") %> value="coracao">coracao</option>
                                    <option <%= (forma.equals("coroa") ? "selected" :  " ") %> value="coroa">coroa</option>
                                    <option <%= (forma.equals("cubo") ? "selected" :  " ") %> value="cubo">cubo</option>
                                    <option <%= (forma.equals("dardo") ? "selected" :  " ") %> value="dardo">dardo</option>
                                    <option <%= (forma.equals("dente") ? "selected" :  " ") %> value="dente">dente</option>
                                    <option <%= (forma.equals("discovoador") ? "selected" :  " ") %> value="discovoador">discovoador</option>
                                    <option <%= (forma.equals("dolar") ? "selected" :  " ") %> value="dolar">dolar</option>
                                    <option <%= (forma.equals("domino1") ? "selected" :  " ") %> value="domino1">domino1</option>
                                    <option <%= (forma.equals("domino2") ? "selected" :  " ") %> value="domino2">domino2</option>
                                    <option <%= (forma.equals("domino3") ? "selected" :  " ") %> value="domino3">domino3</option>
                                    <option <%= (forma.equals("domino4") ? "selected" :  " ") %> value="domino4">domino4</option>
                                    <option <%= (forma.equals("domino5") ? "selected" :  " ") %> value="domino5">domino5</option>
                                    <option <%= (forma.equals("domino6") ? "selected" :  " ") %> value="domino6">domino6</option>
                                    <option <%= (forma.equals("escavadora") ? "selected" :  " ") %> value="escavadora">escavadora</option>
                                    <option <%= (forma.equals("esquilo") ? "selected" :  " ") %> value="esquilo">esquilo</option>
                                    <option <%= (forma.equals("estrela") ? "selected" :  " ") %> value="estrela">estrela</option>
                                    <option <%= (forma.equals("exclamacao") ? "selected" :  " ") %> value="exclamacao">exclamacao</option>
                                    <option <%= (forma.equals("facefeliz") ? "selected" :  " ") %> value="facefeliz">facefeliz</option>
                                    <option <%= (forma.equals("faceneutra") ? "selected" :  " ") %> value="faceneutra">faceneutra</option>
                                    <option <%= (forma.equals("facetriste") ? "selected" :  " ") %> value="facetriste">facetriste</option>
                                    <option <%= (forma.equals("falcao") ? "selected" :  " ") %> value="falcao">falcao</option>
                                    <option <%= (forma.equals("fantasma") ? "selected" :  " ") %> value="fantasma">fantasma</option>
                                    <option <%= (forma.equals("flor") ? "selected" :  " ") %> value="flor">flor</option>
                                    <option <%= (forma.equals("fogo") ? "selected" :  " ") %> value="fogo">fogo</option>
                                    <option <%= (forma.equals("foguete") ? "selected" :  " ") %> value="foguete">foguete</option>
                                    <option <%= (forma.equals("folha") ? "selected" :  " ") %> value="folha">folha</option>
                                    <option <%= (forma.equals("formiga") ? "selected" :  " ") %> value="formiga">formiga</option>
                                    <option <%= (forma.equals("garrafa") ? "selected" :  " ") %> value="garrafa">garrafa</option>
                                    <option <%= (forma.equals("gato") ? "selected" :  " ") %> value="gato">gato</option>
                                    <option <%= (forma.equals("gota") ? "selected" :  " ") %> value="gota">gota</option>
                                    <option <%= (forma.equals("hexagono") ? "selected" :  " ") %> value="hexagono">hexagono</option>
                                    <option <%= (forma.equals("ima") ? "selected" :  " ") %> value="ima">ima</option>
                                    <option <%= (forma.equals("industria") ? "selected" :  " ") %> value="industria">industria</option>
                                    <option <%= (forma.equals("inseto") ? "selected" :  " ") %> value="inseto">inseto</option>
                                    <option <%= (forma.equals("lagarta") ? "selected" :  " ") %> value="lagarta">lagarta</option>
                                    <option <%= (forma.equals("lapis") ? "selected" :  " ") %> value="lapis">lapis</option>
                                    <option <%= (forma.equals("linha") ? "selected" :  " ") %> value="linha">linha</option>
                                    <option <%= (forma.equals("linhapequena") ? "selected" :  " ") %> value="linhapequena">linhapequena</option>
                                    <option <%= (forma.equals("livro") ? "selected" :  " ") %> value="livro">livro</option>
                                    <option <%= (forma.equals("lixeira") ? "selected" :  " ") %> value="lixeira">lixeira</option>
                                    <option <%= (forma.equals("lobo") ? "selected" :  " ") %> value="lobo">lobo</option>
                                    <option <%= (forma.equals("loja") ? "selected" :  " ") %> value="loja">loja</option>
                                    <option <%= (forma.equals("losango") ? "selected" :  " ") %> value="losango">losango</option>
                                    <option <%= (forma.equals("lua") ? "selected" :  " ") %> value="lua">lua</option>
                                    <option <%= (forma.equals("maca") ? "selected" :  " ") %> value="maca">maca</option>
                                    <option <%= (forma.equals("madeira") ? "selected" :  " ") %> value="madeira">madeira</option>
                                    <option <%= (forma.equals("moedacara") ? "selected" :  " ") %> value="moedacara">moedacara</option>
                                    <option <%= (forma.equals("moedacoroa") ? "selected" :  " ") %> value="moedacoroa">moedacoroa</option>
                                    <option <%= (forma.equals("monumento") ? "selected" :  " ") %> value="monumento">monumento</option>
                                    <option <%= (forma.equals("morango") ? "selected" :  " ") %> value="morango">morango</option>
                                    <option <%= (forma.equals("notamusical1") ? "selected" :  " ") %> value="notamusical1">notamusical1</option>
                                    <option <%= (forma.equals("notamusical2") ? "selected" :  " ") %> value="notamusical2">notamusical2</option>
                                    <option <%= (forma.equals("nuvem") ? "selected" :  " ") %> value="nuvem">nuvem</option>
                                    <option <%= (forma.equals("olho") ? "selected" :  " ") %> value="olho">olho</option>
                                    <option <%= (forma.equals("onibus") ? "selected" :  " ") %> value="onibus">onibus</option>
                                    <option <%= (forma.equals("ovelha") ? "selected" :  " ") %> value="ovelha">ovelha</option>
                                    <option <%= (forma.equals("ovo") ? "selected" :  " ") %> value="ovo">ovo</option>
                                    <option <%= (forma.equals("passaro") ? "selected" :  " ") %> value="passaro">passaro</option>
                                    <option <%= (forma.equals("pedras") ? "selected" :  " ") %> value="pedras">pedras</option>
                                    <option <%= (forma.equals("pegada") ? "selected" :  " ") %> value="pegada">pegada</option>
                                    <option <%= (forma.equals("peixe") ? "selected" :  " ") %> value="peixe">peixe</option>
                                    <option <%= (forma.equals("pentagono") ? "selected" :  " ") %> value="pentagono">pentagono</option>
                                    <option <%= (forma.equals("pessoa") ? "selected" :  " ") %> value="pessoa">pessoa</option>
                                    <option <%= (forma.equals("pessoaempresario") ? "selected" :  " ") %> value="pessoaempresario">pessoaempresario</option>
                                    <option <%= (forma.equals("pessoaestudante") ? "selected" :  " ") %> value="pessoaestudante">pessoaestudante</option>
                                    <option <%= (forma.equals("pessoafazendeiro") ? "selected" :  " ") %> value="pessoafazendeiro">pessoafazendeiro</option>
                                    <option <%= (forma.equals("pessoaformando") ? "selected" :  " ") %> value="pessoaformando">pessoaformando</option>
                                    <option <%= (forma.equals("pessoagarcom") ? "selected" :  " ") %> value="pessoagarcom">pessoagarcom</option>
                                    <option <%= (forma.equals("pessoagari") ? "selected" :  " ") %> value="pessoagari">pessoagari</option>
                                    <option <%= (forma.equals("pessoalenhador") ? "selected" :  " ") %> value="pessoalenhador">pessoalenhador</option>
                                    <option <%= (forma.equals("pessoamedico") ? "selected" :  " ") %> value="pessoamedico">pessoamedico</option>
                                    <option <%= (forma.equals("pessoapolicial") ? "selected" :  " ") %> value="pessoapolicial">pessoapolicial</option>
                                    <option <%= (forma.equals("pessoasoldado") ? "selected" :  " ") %> value="pessoasoldado">pessoasoldado</option>
                                    <option <%= (forma.equals("pincel") ? "selected" :  " ") %> value="pincel">pincel</option>
                                    <option <%= (forma.equals("pinheiro") ? "selected" :  " ") %> value="pinheiro">pinheiro</option>
                                    <option <%= (forma.equals("pinodeboliche") ? "selected" :  " ") %> value="pinodeboliche">pinodeboliche</option>
                                    <option <%= (forma.equals("planta") ? "selected" :  " ") %> value="planta">planta</option>
                                    <option <%= (forma.equals("polvo") ? "selected" :  " ") %> value="polvo">polvo</option>
                                    <option <%= (forma.equals("ponto") ? "selected" :  " ") %> value="ponto">ponto</option>
                                    <option <%= (forma.equals("quadrado1") ? "selected" :  " ") %> value="quadrado1">quadrado1</option>
                                    <option <%= (forma.equals("quadrado2") ? "selected" :  " ") %> value="quadrado2">quadrado2</option>
                                    <option <%= (forma.equals("raio") ? "selected" :  " ") %> value="raio">raio</option>
                                    <option <%= (forma.equals("rato") ? "selected" :  " ") %> value="rato">rato</option>
                                    <option <%= (forma.equals("relogio") ? "selected" :  " ") %> value="relogio">relogio</option>
                                    <option <%= (forma.equals("roda") ? "selected" :  " ") %> value="roda">roda</option>
                                    <option <%= (forma.equals("sapo") ? "selected" :  " ") %> value="sapo">sapo</option>
                                    <option <%= (forma.equals("seta") ? "selected" :  " ") %> value="seta">seta</option>
                                    <option <%= (forma.equals("setafina") ? "selected" :  " ") %> value="setafina">setafina</option>
                                    <option <%= (forma.equals("setapadrao") ? "selected" :  " ") %> value="setapadrao">setapadrao</option>
                                    <option <%= (forma.equals("sol") ? "selected" :  " ") %> value="sol">sol</option>
                                    <option <%= (forma.equals("tanque") ? "selected" :  " ") %> value="tanque">tanque</option>
                                    <option <%= (forma.equals("tartaruga") ? "selected" :  " ") %> value="tartaruga">tartaruga</option>
                                    <option <%= (forma.equals("telefone") ? "selected" :  " ") %> value="telefone">telefone</option>
                                    <option <%= (forma.equals("tijolos") ? "selected" :  " ") %> value="tijolos">tijolos</option>
                                    <option <%= (forma.equals("trem") ? "selected" :  " ") %> value="trem">trem</option>
                                    <option <%= (forma.equals("triangulo1") ? "selected" :  " ") %> value="triangulo1">triangulo1</option>
                                    <option <%= (forma.equals("triangulo2") ? "selected" :  " ") %> value="triangulo2">triangulo2</option>
                                    <option <%= (forma.equals("tubarao") ? "selected" :  " ") %> value="tubarao">tubarao</option>
                                    <option <%= (forma.equals("vaca") ? "selected" :  " ") %> value="vaca">vaca</option>
                                    <option <%= (forma.equals("vagao") ? "selected" :  " ") %> value="vagao">vagao</option>
                                    <option <%= (forma.equals("van") ? "selected" :  " ") %> value="van">van</option>
                                    <option <%= (forma.equals("x") ? "selected" :  " ") %> value="x">x</option>
                                    <option <%= (forma.equals("xadrezbispo") ? "selected" :  " ") %> value="xadrezbispo">xadrezbispo</option>
                                    <option <%= (forma.equals("xadrezcavalo") ? "selected" :  " ") %> value="xadrezcavalo">xadrezcavalo</option>
                                    <option <%= (forma.equals("xadrezpeao") ? "selected" :  " ") %> value="xadrezpeao">xadrezpeao</option>
                                    <option <%= (forma.equals("xadrezrainha") ? "selected" :  " ") %> value="xadrezrainha">xadrezrainha</option>
                                    <option <%= (forma.equals("xadrezrei") ? "selected" :  " ") %> value="xadrezrei">xadrezrei</option>
                                    <option <%= (forma.equals("xadreztorre") ? "selected" :  " ") %> value="xadreztorre">xadreztorre</option>
                                </select>
                            </td>
                            <td>
                                <table width="60" height="20" border="0" align="center" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <% if (forma.equals("")) { %>
                                        <td><div align="center"><img name=imagemForma src="formas/abelha.gif" align="center" width="30" height="30"/></div></td>
                                                <% } else { %>
                                        <td><div align="center"><img name=imagemForma src="<%="formas/" + forma + ".gif"%>" align="center" width="30" height="30"/></div></td>
                                                <% } %>
                                    </tr>
                                </table>
                            </td>
                            <td bgcolor="#333300" align="center"><a href="controlador.ControladorWebCCA?metodo=cadPercCFT&acao=excluir&idPerc=<%=idPerc%>&idCondicao=<%=idCondicao%>">Remover</a></td>
                        </tr>
                    <%}
                    
                    if(percCFT.getVar().equals("cor")) {
                        cor = percCFT.getValor();
                        %>
                        <tr>
                            <td>Cor</td>
                            <td>
                                <select name="cor" id="cor" onchange="javascript:alterarCFT(<%=idPerc%>, 'null', this.value)">
                                    <option <%= (cor.equals("AMARELO") ? "selected" : " ") %> value="AMARELO">AMARELO</option>
                                    <option <%= (cor.equals("AZUL") ? "selected" : " ") %> value="AZUL">AZUL</option>
                                    <option <%= (cor.equals("AZULCLARO") ? "selected" : " ") %> value="AZULCLARO">AZULCLARO</option>
                                    <option <%= (cor.equals("BRANCO") ? "selected" : " ") %> value="BRANCO">BRANCO</option>
                                    <option <%= (cor.equals("CIANO") ? "selected" : " ") %> value="CIANO">CIANO</option>
                                    <option <%= (cor.equals("CINZA") ? "selected" : " ") %> value="CINZA">CINZA</option>
                                    <option <%= (cor.equals("LARANJA") ? "selected" : " ") %> value="LARANJA">LARANJA</option>
                                    <option <%= (cor.equals("MAGENTA2") ? "selected" : " ") %> value="MAGENTA2">MAGENTA2</option>
                                    <option <%= (cor.equals("MARROM") ? "selected" : " ") %> value="MARROM">MARROM</option>
                                    <option <%= (cor.equals("PRETO") ? "selected" : " ") %> value="PRETO">PRETO</option>
                                    <option <%= (cor.equals("ROSA") ? "selected" : " ") %> value="ROSA">ROSA</option>
                                    <option <%= (cor.equals("VERDE") ? "selected" : " ") %> value="VERDE">VERDE</option>
                                    <option <%= (cor.equals("VERDELIMAO") ? "selected" : " ") %> value="VERDELIMAO">VERDELIMAO</option>
                                    <option <%= (cor.equals("VERDETURQUESA") ? "selected" : " ") %> value="VERDETURQUESA">VERDETURQUESA</option>
                                    <option <%= (cor.equals("VERMELHO") ? "selected" : " ") %> value="VERMELHO">VERMELHO</option>
                                    <option <%= (cor.equals("VIOLETA") ? "selected" : " ") %> value="VIOLETA">VIOLETA</option>
                                </select>
                            </td>
                            <td bgcolor="#333300" align="center"><a href="controlador.ControladorWebCCA?metodo=cadPercCFT&acao=excluir&idPerc=<%=idPerc%>&idCondicao=<%=idCondicao%>">Remover</a></td>
                        </tr>
                    <%}
                    
                    if(percCFT.getVar().equals("tamanho")) { 
                        int tam = Integer.parseInt(percCFT.getValor());
                        String operador = percCFT.getOperador();
                    %>
                        <tr>
                            <td>Tamanho</td>
                            <td>
                                <select name="operador" onchange="javascript:alterarCFT(<%=idPerc%>, this.value, <%=tam%>)">
                                    <option <%= (operador.equals("igual") ? "selected" :  " ") %> value="igual"> = </option>
                                    <option <%= (operador.equals("diferente") ? "selected" : " " ) %> value="diferente"> != </option>
                                    <option <%= (operador.equals("maior") ? "selected" :  " ") %> value="maior"> > </option>
                                    <option <%= (operador.equals("menor") ? "selected" :  " ") %> value="menor"> < </option>
                                    <option <%= (operador.equals("maiorIgual") ? "selected" :  " ") %> value="maiorIgual"> >= </option>
                                    <option <%= (operador.equals("menorIgual") ? "selected" :  " ") %> value="menorIgual"> <= </option>
                                </select>
                                <select name="tamanho" onchange="javascript:alterarCFT(<%=idPerc%>, '<%=operador%>', this.value)">
                                    <%
                                        for(int z = 1; z <= 5; z ++) {%>
                                            <option <%= ((tam == z) ? "selected" :  " ") %> value="<%=z%>"><%=z%></option>
                                    <%  } %>
                                </select>
                            </td>
                            <td bgcolor="#333300" align="center"><a href="controlador.ControladorWebCCA?metodo=cadPercCFT&acao=excluir&idPerc=<%=idPerc%>&idCondicao=<%=idCondicao%>">Remover</a></td>
                        </tr>
                    <%}
                }
                %>
                </table>
            </div>
        </div>
        <div class="cont" style="width: 40px; margin-top: 20px; background-color: #333300; text-align: center"> 
            <a class="style1" href="incluircomp.jsp?status=Acao criada com sucesso!">Salvar</a>
        </div>
    </body>
</html>
