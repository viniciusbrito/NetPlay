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
        int id;
        Acao a;
        String tipo;

        id = Integer.parseInt(request.getParameter("id"));
        a = AplCadastrarAcao.ObterAcao(id);
        tipo = a.getTipo();

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
            .style41 {color: #009900}
            -->
        </style>
        <script language="JavaScript" type="text/JavaScript">
            <!--
            -->
        </script>
    </head>

    <body>
        <div align="center">
            <form action="controlador.ControladorWebCCA" method="post" name="altAcao" id="altAcao" >
	        <% if ( t.compareTo("Criar") == 0 ) { %>
    	           <form action="controlador.ControladorWebCCA" method="post" name="acCriar" id="acCriar">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Agente</div></td>
                                                <td width="200"><div align="left">
                                                    <% List agentes;
														GerarNovoObjeto act = (GerarNovoObjeto) a;
                                                        Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                        agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
														
                                                    %>
                                                    <select name="agente" id="agente" disabled>
														<option selected value=<%=act.getAgente().getIdAgente()%>> <%=act.getAgente().getNome()%></option>
                                                        <%for(int i=0; i<agentes.size(); i++){
                                                            Agente agente = (Agente)agentes.get(i); %>
                                                        <option value= <%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                        <%}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                        </table>
                                    </form>
        	       <%  }
	          	if ( t.compareTo("Destruir") == 0 ) { %>
	               <form action="controlador.ControladorWebCCA" method="post" name="acDestruir" id="acDestruir">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Agente</div></td>
                                                <td width="200"><div align="left">
                                                    <% List agentes;
														Destruir act = (Destruir) a;
                                                        Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                        agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
                                                    %>

                                                    <select name="agente" id="agente" disabled>
														<option selected value="<%=act.getAgente().getIdAgente()%>"><%=act.getAgente().getNome()%></option>
                                                        <%for(int i=0; i<agentes.size(); i++){
                                                            Agente agente = (Agente)agentes.get(i); %>
                                                        <option value= <%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                        <%}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                        </table>
                                    </form>                
				<%  }
	            if ( t.compareTo("AltCor") == 0 ) { %>
	                <form action="controlador.ControladorWebCCA" method="post" name="acAltMov" id="acAltMov">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Agente</div></td>
                                                <td colspan="2"><div align="left">
                                                    <% List agentes;
														AlterarCor act = (AlterarCor) a;
                                                        Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                        agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
														cor = act.getCor();
                                                    %>
                                                    <select name="agente" id="agente" disabled >
                                                        <option select value=<%= act.getAgente().getIdAgente() %>> <%= act.getAgente().getNome() %></option>
														<%for(int i=0; i<agentes.size(); i++){
                                                                Agente agente = (Agente)agentes.get(i);%>
                                                        <option value=<%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                        <%}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                <div align="left"> Nova Cor </div></td>
                                                <td width="125"><div align="left">
                                                    <select name="cor" id="cor" disabled>
                                                        <% if(cor != ""){%>
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
                                                        <%if(teste==0){%>
                                                        <option selected value= <%=cor%>> <%=cor%></option>
                                                        <%}%>
                                                        <option value="PRETO">PRETO</option>
                                                        <option value="AZUL">AZUL</option>
                                                        <option value="MARROM">MARROM</option>
                                                        <option value="CIANO">CIANO</option>
                                                        <option value="CINZA">CINZA</option>
                                                        <option value="VERDE">VERDE</option>
                                                        <option value="VERDELIMAO">VERDE LIMAO</option>
                                                        <option value="MAGENTA">MAGENTA</option>
                                                        <option value="LARANJA">LARANJA</option>
                                                        <option value="ROSA">ROSA</option>
                                                        <option value="VERMELHO">VERMELHO</option>
                                                        <option value="AZULCLARO">AZUL CLARO</option>
                                                        <option value="VERDETURQUESA">VERDE TURQUESA</option>
                                                        <option value="VIOLETA">VIOLETA</option>
                                                        <option value="BRANCO">BRANCO</option>
                                                        <option value="AMARELO">AMARELO</option>
                                                        <%}else{%>
                                                        <option value="PRETO">PRETO</option>
                                                        <option value="AZUL">AZUL</option>
                                                        <option value="MARROM">MARROM</option>
                                                        <option value="CIANO">CIANO</option>
                                                        <option value="CINZA">CINZA</option>
                                                        <option value="VERDE">VERDE</option>
                                                        <option value="VERDELIMAO">VERDE LIMAO</option>
                                                        <option value="MAGENTA">MAGENTA</option>
                                                        <option value="LARANJA">LARANJA</option>
                                                        <option value="ROSA">ROSA</option>
                                                        <option value="VERMELHO">VERMELHO</option>
                                                        <option value="AZULCLARO">AZUL CLARO</option>
                                                        <option value="VERDETURQUESA">VERDE TURQUESA</option>
                                                        <option value="VIOLETA">VIOLETA</option>
                                                        <option value="BRANCO">BRANCO</option>
                                                        <option value="AMARELO">AMARELO</option>
                                                        <%}%>
                                                    </select>
                                                </div></td>
                                                <td width="121" bgcolor="#333300"><div align="center" class="style1"><a href="#" onClick="MM_openBrWindow('cores.jsp','cores','width=420,height=470')">Cores</a></div></td>
                                            </tr>
                                        </table>
                                    </form>                
				<%  }
	            if ( t.compareTo("AltFor") == 0 ) {%>
	                <form action="controlador.ControladorWebCCA" method="post" name="acAltMov" id="acAltMov">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Agente</div></td>
                                                <td colspan="2"><div align="left">
                                                    <% List agentes;
														AlterarForma act = (AlterarForma) a;
														forma = act.getForma();
                                                        Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                        agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
                                                    %>
                                                    <select name="agente" id="agente" disabled >
														<option selected value= <%= act.getAgente().getIdAgente() %>> <%= act.getAgente().getNome() %></option>
                                                        <%for(int i=0; i<agentes.size(); i++){
                                                            Agente agente = (Agente)agentes.get(i); %>
                                                        <option value= <%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                        <%}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                <div align="left"> Nova Forma</div></td>
                                                <td width="116"><div align="left">
                                                  <select name="forma" id="forma" disabled >
                                                    <% if(forma != ""){%>
                                                    <% int teste2 = 0;
											if(forma.compareTo("CIRCULO1")==0){%>
                                                    <option selected value= <%=forma%>>CIRCULO 1</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("CIRCULO2")==0){%>
                                                    <option selected value= <%=forma%>>CIRCULO 2</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("CILINDRO3D")==0){%>
                                                    <option selected value= <%=forma%>>CILINDRO 3D</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("SETAPADRAO")==0){%>
                                                    <option selected value= <%=forma%>>SETA PADRAO</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("FACEFELIZ")==0){%>
                                                    <option selected value= <%=forma%>>FACE FELIZ</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("FACENEUTRA")==0){%>
                                                    <option selected value= <%=forma%>>FACE NEUTRA</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("FACETRISTE")==0){%>
                                                    <option selected value= <%=forma%>>FACE TRISTE</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("LINHAPEQUENA")==0){%>
                                                    <option selected value= <%=forma%>>LINHA PEQUENA</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("QUADRADO1")==0){%>
                                                    <option selected value= <%=forma%>>QUADRADO 1</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("QUADRADO2")==0){%>
                                                    <option selected value= <%=forma%>>QUADRADO 2</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("TRIANGULO1")==0){%>
                                                    <option selected value= <%=forma%>>TRIANGULO 1 </option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("TRIANGULO2")==0){%>
                                                    <option selected value= <%=forma%>>TRIANGULO 2 </option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(teste2==0){%>
                                                    <option selected value= <%=forma%>><%=forma%></option>
                                                    <%}%>
                                                    <option value="AVIAO">AVIAO</option>
                                                    <option value="SETA">SETA</option>
                                                    <option value="CUBO">CUBO</option>
                                                    <option value="INSETO">INSETO</option>
                                                    <option value="BORBOLETA">BORBOLETA</option>
                                                    <option value="CARRO">CARRO</option>
                                                    <option value="CIRCULO1 ">CIRCULO 1</option>
                                                    <option value="CIRCULO2">CIRCULO 2</option>
                                                    <option value="VACA">VACA</option>
                                                    <option value="CILINDRO3D">CILINDRO 3D</option>
                                                    <option value="CILINDRO">CILINDRO</option>
                                                    <option value="SETAPADRAO">SETA PADRAO</option>
                                                    <option value="PONTO">PONTO</option>
                                                    <option value="FACEFELIZ">FACE FELIZ</option>
                                                    <option value="FACENEUTRA">FACE NEUTRA</option>
                                                    <option value="FACETRISTE">FACE TRISTE</option>
                                                    <option value="PEIXE">PEIXE</option>
                                                    <option value="BANDEIRA">BANDEIRA</option>
                                                    <option value="FLOR">FLOR</option>
                                                    <option value="CASA">CASA</option>
                                                    <option value="FOLHA">FOLHA</option>
                                                    <option value="LINHA">LINHA</option>
                                                    <option value="LINHAPEQUENA">LINHA PEQUENA</option>
                                                    <option value="PENTAGONO">PENTAGONO</option>
                                                    <option value="PESSOA">PESSOA</option>
                                                    <option value="PLANTA">PLANTA</option>
                                                    <option value="QUADRADO1">QUADRADO 1</option>
                                                    <option value="QUADRADO2">QUADRADO 2</option>
                                                    <option value="ESTRELA">ESTRELA</option>
                                                    <option value="ALVO">ALVO</option>
                                                    <option value="ARVORE">ARVORE</option>
                                                    <option value="TRIANGULO1">TRIANGULO 1</option>
                                                    <option value="TRIANGULO2">TRIANGULO 2</option>
                                                    <option value="CAMINHAO">CAMINHAO</option>
                                                    <option value="TARTARUGA">TARTARUGA</option>
                                                    <option value="RODA">RODA</option>
                                                    <option value="X">X</option>
                                                    <%}else{%>
                                                    <option value="AVIAO">AVIAO</option>
                                                    <option value="SETA">SETA</option>
                                                    <option value="CUBO">CUBO</option>
                                                    <option value="INSETO">INSETO</option>
                                                    <option value="BORBOLETA">BORBOLETA</option>
                                                    <option value="CARRO">CARRO</option>
                                                    <option value="CIRCULO1 ">CIRCULO 1</option>
                                                    <option value="CIRCULO2">CIRCULO 2</option>
                                                    <option value="VACA">VACA</option>
                                                    <option value="CILINDRO3D">CILINDRO 3D</option>
                                                    <option value="CILINDRO">CILINDRO</option>
                                                    <option value="SETAPADRAO">SETA PADRAO</option>
                                                    <option value="PONTO">PONTO</option>
                                                    <option value="FACEFELIZ">FACE FELIZ</option>
                                                    <option value="FACENEUTRA">FACE NEUTRA</option>
                                                    <option value="FACETRISTE">FACE TRISTE</option>
                                                    <option value="PEIXE">PEIXE</option>
                                                    <option value="BANDEIRA">BANDEIRA</option>
                                                    <option value="FLOR">FLOR</option>
                                                    <option value="CASA">CASA</option>
                                                    <option value="FOLHA">FOLHA</option>
                                                    <option value="LINHA">LINHA</option>
                                                    <option value="LINHAPEQUENA">LINHA PEQUENA</option>
                                                    <option value="PENTAGONO">PENTAGONO</option>
                                                    <option value="PESSOA">PESSOA</option>
                                                    <option value="PLANTA">PLANTA</option>
                                                    <option value="QUADRADO1">QUADRADO 1</option>
                                                    <option value="QUADRADO2">QUADRADO 2</option>
                                                    <option value="ESTRELA">ESTRELA</option>
                                                    <option value="ALVO">ALVO</option>
                                                    <option value="ARVORE">ARVORE</option>
                                                    <option value="TRIANGULO1">TRIANGULO 1</option>
                                                    <option value="TRIANGULO2">TRIANGULO 2</option>
                                                    <option value="CAMINHAO">CAMINHAO</option>
                                                    <option value="TARTARUGA">TARTARUGA</option>
                                                    <option value="RODA">RODA</option>
                                                    <option value="X">X</option>
                                                    <%}%>
                                                  </select>
                                                </div></td>
                                            <td width="121" bgcolor="#333300"><div align="center" class="style1"><a href="#" onClick="MM_openBrWindow('formas.jsp','cores','scrollbars=yes,width=300,height=430')">Formas</a></div></td>
                                            </tr>
                                        </table>
                                  </form>                
				<%  }
	            if ( t.compareTo("AltMov") == 0 ) { %>
	                <form action="controlador.ControladorWebCCA" method="post" name="acAltMov" id="acAltMov">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Agente</div></td>
                                                <td colspan="2"><div align="left">
                                                    <% List agentes;
														AlterarForma act = (AlterarForma) a;
														forma = act.getForma();
                                                        Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                        agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
                                                    %>
                                                    <select name="agente" id="agente" disabled >
														<option selected value= <%= act.getAgente().getIdAgente() %>> <%= act.getAgente().getNome() %></option>
                                                        <%for(int i=0; i<agentes.size(); i++){
                                                            Agente agente = (Agente)agentes.get(i); %>
                                                        <option value= <%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                        <%}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td>
                                                <div align="left"> Nova Forma</div></td>
                                                <td width="116"><div align="left">
                                                  <select name="forma" id="forma" disabled >
                                                    <% if(forma != ""){%>
                                                    <% int teste2 = 0;
											if(forma.compareTo("CIRCULO1")==0){%>
                                                    <option selected value= <%=forma%>>CIRCULO 1</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("CIRCULO2")==0){%>
                                                    <option selected value= <%=forma%>>CIRCULO 2</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("CILINDRO3D")==0){%>
                                                    <option selected value= <%=forma%>>CILINDRO 3D</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("SETAPADRAO")==0){%>
                                                    <option selected value= <%=forma%>>SETA PADRAO</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("FACEFELIZ")==0){%>
                                                    <option selected value= <%=forma%>>FACE FELIZ</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("FACENEUTRA")==0){%>
                                                    <option selected value= <%=forma%>>FACE NEUTRA</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("FACETRISTE")==0){%>
                                                    <option selected value= <%=forma%>>FACE TRISTE</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("LINHAPEQUENA")==0){%>
                                                    <option selected value= <%=forma%>>LINHA PEQUENA</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("QUADRADO1")==0){%>
                                                    <option selected value= <%=forma%>>QUADRADO 1</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("QUADRADO2")==0){%>
                                                    <option selected value= <%=forma%>>QUADRADO 2</option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("TRIANGULO1")==0){%>
                                                    <option selected value= <%=forma%>>TRIANGULO 1 </option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(forma.compareTo("TRIANGULO2")==0){%>
                                                    <option selected value= <%=forma%>>TRIANGULO 2 </option>
                                                    <%teste2 = 1;
											}%>
                                                    <%if(teste2==0){%>
                                                    <option selected value= <%=forma%>><%=forma%></option>
                                                    <%}%>
                                                    <option value="AVIAO">AVIAO</option>
                                                    <option value="SETA">SETA</option>
                                                    <option value="CUBO">CUBO</option>
                                                    <option value="INSETO">INSETO</option>
                                                    <option value="BORBOLETA">BORBOLETA</option>
                                                    <option value="CARRO">CARRO</option>
                                                    <option value="CIRCULO1 ">CIRCULO 1</option>
                                                    <option value="CIRCULO2">CIRCULO 2</option>
                                                    <option value="VACA">VACA</option>
                                                    <option value="CILINDRO3D">CILINDRO 3D</option>
                                                    <option value="CILINDRO">CILINDRO</option>
                                                    <option value="SETAPADRAO">SETA PADRAO</option>
                                                    <option value="PONTO">PONTO</option>
                                                    <option value="FACEFELIZ">FACE FELIZ</option>
                                                    <option value="FACENEUTRA">FACE NEUTRA</option>
                                                    <option value="FACETRISTE">FACE TRISTE</option>
                                                    <option value="PEIXE">PEIXE</option>
                                                    <option value="BANDEIRA">BANDEIRA</option>
                                                    <option value="FLOR">FLOR</option>
                                                    <option value="CASA">CASA</option>
                                                    <option value="FOLHA">FOLHA</option>
                                                    <option value="LINHA">LINHA</option>
                                                    <option value="LINHAPEQUENA">LINHA PEQUENA</option>
                                                    <option value="PENTAGONO">PENTAGONO</option>
                                                    <option value="PESSOA">PESSOA</option>
                                                    <option value="PLANTA">PLANTA</option>
                                                    <option value="QUADRADO1">QUADRADO 1</option>
                                                    <option value="QUADRADO2">QUADRADO 2</option>
                                                    <option value="ESTRELA">ESTRELA</option>
                                                    <option value="ALVO">ALVO</option>
                                                    <option value="ARVORE">ARVORE</option>
                                                    <option value="TRIANGULO1">TRIANGULO 1</option>
                                                    <option value="TRIANGULO2">TRIANGULO 2</option>
                                                    <option value="CAMINHAO">CAMINHAO</option>
                                                    <option value="TARTARUGA">TARTARUGA</option>
                                                    <option value="RODA">RODA</option>
                                                    <option value="X">X</option>
                                                    <%}else{%>
                                                    <option value="AVIAO">AVIAO</option>
                                                    <option value="SETA">SETA</option>
                                                    <option value="CUBO">CUBO</option>
                                                    <option value="INSETO">INSETO</option>
                                                    <option value="BORBOLETA">BORBOLETA</option>
                                                    <option value="CARRO">CARRO</option>
                                                    <option value="CIRCULO1 ">CIRCULO 1</option>
                                                    <option value="CIRCULO2">CIRCULO 2</option>
                                                    <option value="VACA">VACA</option>
                                                    <option value="CILINDRO3D">CILINDRO 3D</option>
                                                    <option value="CILINDRO">CILINDRO</option>
                                                    <option value="SETAPADRAO">SETA PADRAO</option>
                                                    <option value="PONTO">PONTO</option>
                                                    <option value="FACEFELIZ">FACE FELIZ</option>
                                                    <option value="FACENEUTRA">FACE NEUTRA</option>
                                                    <option value="FACETRISTE">FACE TRISTE</option>
                                                    <option value="PEIXE">PEIXE</option>
                                                    <option value="BANDEIRA">BANDEIRA</option>
                                                    <option value="FLOR">FLOR</option>
                                                    <option value="CASA">CASA</option>
                                                    <option value="FOLHA">FOLHA</option>
                                                    <option value="LINHA">LINHA</option>
                                                    <option value="LINHAPEQUENA">LINHA PEQUENA</option>
                                                    <option value="PENTAGONO">PENTAGONO</option>
                                                    <option value="PESSOA">PESSOA</option>
                                                    <option value="PLANTA">PLANTA</option>
                                                    <option value="QUADRADO1">QUADRADO 1</option>
                                                    <option value="QUADRADO2">QUADRADO 2</option>
                                                    <option value="ESTRELA">ESTRELA</option>
                                                    <option value="ALVO">ALVO</option>
                                                    <option value="ARVORE">ARVORE</option>
                                                    <option value="TRIANGULO1">TRIANGULO 1</option>
                                                    <option value="TRIANGULO2">TRIANGULO 2</option>
                                                    <option value="CAMINHAO">CAMINHAO</option>
                                                    <option value="TARTARUGA">TARTARUGA</option>
                                                    <option value="RODA">RODA</option>
                                                    <option value="X">X</option>
                                                    <%}%>
                                                  </select>
                                                </div></td>
                                            <td width="121" bgcolor="#333300"><div align="center" class="style1"><a href="#" onClick="MM_openBrWindow('formas.jsp','cores','scrollbars=yes,width=300,height=430')">Formas</a></div></td>
                                            </tr>
                                        </table>
                                  </form>                
				<%  }
	            if ( t.compareTo("AltMov") == 0 ) { %>
	                <form action="controlador.ControladorWebCCA" method="post" name="acAltMov" id="acAltMov" onSubmit="MM_validateForm();return document.MM_returnValue">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Agente M&oacute;vel </div></td>
                                                <td width="200"><div align="left">
                                                    <% List agentes;
														AlterarMovimento act = (AlterarMovimento) a;
                                                        Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                        agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
                                                    %>
                                                    <select name="agenteMovel" id="agenteMovel" disabled >
														<option selected value=<%=act.getAgenteMovel().getIdAgente()%>><%=act.getAgenteMovel().getNome()%></option>
                                                        <%for(int i=0; i<agentes.size(); i++){
                                                            Agente agente = (Agente)agentes.get(i);
                                                            if (agente.getTipo().compareTo("Movel")==0){%>
                                                        <option value= <%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                        <%}}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                        </table>
                                  </form>
                <%  }
	            if ( t.compareTo("AltPos") == 0 ) { %>
	                <form action="controlador.ControladorWebCCA" method="post" name="acCriar" id="acCriar">
                                    <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                        <tr>
                                            <td width="100"> <div align="left">
                                            Agente M&oacute;vel </div></td>
                                            <td width="200"><div align="left">
                                                <% List agentes;
													AlterarPosicao act = (AlterarPosicao) a;
                                                    Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                    agentes = AplCadastrarAgente.agentesDoModelo(m1.getIdModelo().intValue());
                                                %>
                                                <select name="agente" id="agente" disabled >
													<option selected value= <%= act.getAgente().getIdAgente() %>> <%= act.getAgente().getNome() %></option>
                                                    <%for(int i=0; i<agentes.size(); i++){
                                                        Agente agente = (Agente)agentes.get(i); %>
                                                    <option value= <%= agente.getIdAgente() %>> <%= agente.getNome() %></option>
                                                    <%}%>
                                                </select>
                                            </div></td>
                                        </tr>
                                        <tr>
                                            <td><div align="left">Coordenada X </div></td>
                                            <td><div align="left">
                                                <select name="coordX" id="coordX" disabled >
													<option selected value= <%= act.getNovoX() %>> <%= act.getNovoX() %></option>
                                                    <%for(int i=-25; i<26; i++){%>
                                                    <option value= <%=i%>> <%=i%></option>
                                                    <%}%>
                                                </select>
                                            </div></td>
                                        </tr>
                                        <tr>
                                            <td><div align="left">Coordenada Y </div></td>
                                            <td><div align="left">
                                                <select name="coordY" id="coordY" disabled >
												<option selected value= <%= act.getNovoY() %>> <%= act.getNovoY() %></option>
                                                    <%for(int i=-25; i<26; i++){%>
                                                    <option value= <%=i%>> <%=i%></option>
                                                    <%}%>
                                                </select>
                                            </div></td>
                                        </tr>
                                    </table>
                                </form>
                <%  }
	            if ( t.compareTo("IncValVar") == 0 ) {%>
	                <form action="controlador.ControladorWebCCA" method="post" name="acIncValVar" id="acIncValVar" onSubmit="MM_validateForm('inc','','RisNum');return document.MM_returnValue">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Vari&aacute;vel</div></td>
                                                <td width="200"><div align="left">
                                                    <% List vars1;
														IncrementarVariavel act = (IncrementarVariavel) a;
                                                        Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                        vars1 = AplCadastrarVariavel.variaveisDoModelo(m1.getIdModelo().intValue());
                                                    %>
                                                    <select name="var" id="var" disabled >
                                                        <option selected value= <%= act.getVar1.getNome() %>> <%= act.getVar1.getNome() %></option>
														<%for(int i=0; i<vars1.size(); i++){
                                                                Variavel var1 = (Variavel)vars1.get(i);
                                                                if (var1.getNome().compareTo("Logica") != 0) {%>
                                                        <option value= <%= var1.getNome() %>> <%= var1.getNome() %></option>
                                                        <%}}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Incremento</div></td>
                                                <td width="200"><div align="left">
                                                    <input name="inc" type="text" id="inc" value="<%=act.getIncremento()%>" size="10" maxlength="6" disabled >
                                                </div></td>
                                            </tr>
                                        </table>
                                    </form>
                <%  }
	            if ( t.compareTo("RecValVar") == 0 ) {%>
	                <form action="controlador.ControladorWebCCA" method="post" name="acRecValVar" id="acRecValVar" onSubmit="MM_validateForm('val','','RisNum');return document.MM_returnValue">
                                        <table width="306" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Vari&aacute;vel</div></td>
                                                <td width="200"><div align="left">
                                                    <% List vars1;
														RecuperarValorVariavel act = (RecuperarValorVariavel) a;
                                                        Modelo m1 = (Modelo)session.getAttribute("modelo");
                                                        vars1 = AplCadastrarVariavel.variaveisDoModelo(m1.getIdModelo().intValue());
                                                    %>
                                                    <select name="var" id="var" disabled >
                                                        <option selected value= <%= act.getVar1().getNome() %>> <%= act.getVar1().var1.getNome() %></option>
                                                        <%for(int i=0; i<vars1.size(); i++){
                                                            Variavel var1 = (Variavel)vars1.get(i); %>
                                                        <option value= <%= var1.getNome() %>> <%= var1.getNome() %></option>
                                                        <%}%>
                                                    </select>
                                                </div></td>
                                            </tr>
                                            <tr>
                                                <td width="100"> <div align="left">
                                                Valor</div></td>
                                                <td width="200"><div align="left">
                                                    <input name="val" type="text" id="val" value="1" size="10" maxlength="6" disabled>
                                                </div></td>
                                            </tr>
                                        </table>
                      </form>
   	            <%  } %>					
            </form>
        </div>
    </body>
</html>