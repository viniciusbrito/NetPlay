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
if(id==null)
	idAgente = 0;
else
	idAgente = Integer.parseInt(id);
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
                        <table width="430" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td height="20"><div align="center">POSICIONAMENTO DOS AGENTES NA TELA </div></td>
                          </tr>
                          <tr>
                            <td><div align="center">
                                <form action="controlador.ControladorWeb" method="post" name="form1" target="principal">
                                  <table width="1200" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                    <tr>
                                      <td><div align="center"></div>
                                          <div align="left">
                                            <table width="1200" border="1" align="center" cellpadding="0" cellspacing="0">
                                              <tr>
                                                <th width="21" scope="row">&nbsp;</th>
                                                <% List posicoes = null;
											      posicoes = AplCadastrarAgente.posicoesAgente(idAgente);
                                                                                              System.out.println("Pos " + posicoes.size() + "\n");
                                                                                              for(int jk=0; jk<posicoes.size(); jk++)
                                                                                              {
                                                                                                  PosicaoInicial p = (PosicaoInicial) posicoes.get(jk);
                                                                                                  System.out.println("elem " + jk + " => " + p.getXInicial() + 
                                                                                                          " " + p.getYInicial());
                                                                                              }
                                                                                              System.out.println("\n");
                                                                                          int pos=0;
                                                                                          int tamanho = posicoes.size();
                                                                                          for(int a=-25; a<26; a++){%>
                                                <td width="20"><div align="center"><%=a%></div></td>
                                                <%}%>
                                                <th width="20">&nbsp;</th>
                                              </tr>
                                              <% String nomecheckbox="";
												String linha= "";
												String coluna="";
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
											  		if(j<10)
														coluna = "0" + j;
													else
														coluna = "" + j;
											     	nomecheckbox = "checkbox" + linha + coluna;	
                                                                                                if(pos< tamanho){
                                                                                                    PosicaoInicial posicao = (PosicaoInicial)posicoes.get(pos);
                                                                                                    System.out.println(pos + " " + i + " " + j + " _ " + (posicao.getXInicial().intValue()+25));
                                                                                                    if((posicao.getXInicial().intValue()+25 == j)&&(-(posicao.getYInicial().intValue()-25) == i)){
                                                                                                           System.out.println("pos " + pos);
                                                                                                           pos++;
											  %>
                                                <td><input type="checkbox" name=<%=nomecheckbox%> value="ON" checked></td>
                                                <%}else{%>
                                                <td><input type="checkbox" name=<%=nomecheckbox%> value="ON"></td>
                                                <%}}else{%>
                                                <td><input type="checkbox" name=<%=nomecheckbox%> value="ON"></td>
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
                                            </table>
                                            <div align="center">
                                              <input name="nome" type="hidden" id="nome2" value="<%=nome%>">
                                              <input name="retornar" type="submit" id="confirmar2" value="Retornar" onClick="javascript: history.go(-1)">
                                              <input name="metodo" type="hidden" value="posicaoAgente">
                                              <input name="confirmar" type="submit" id="confirmar" value="Confirmar Posicionamento">
                                              <input name="cor" type="hidden" id="cor" value="<%=cor%>">
                                              <input name="acao" type="hidden" id="acao" value="<%=acao%>">
                                              <input name="idAgente" type="hidden" id="idAgente" value="<%=idAgente%>">
                                              <input name="pos" type="hidden" id="pos" value="posicaoInicial">
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
                                </form>
                            </div></td>
                          </tr>
                      </table></td>
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
