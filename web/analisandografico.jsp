
<%@ page contentType="text/html; charset=iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
         
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>

<%@page import="analiseGraficos.AnaliseGrafico" %>

<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>

<% 
    String nomeGrafico = request.getParameter("grafico");
    String tipoAnalise = request.getParameter("analise");
    
    if(nomeGrafico == null)
        nomeGrafico = "";
    if(tipoAnalise == null)
        tipoAnalise = "";
%>

<html>
<head>

<title>Analisando gráficos</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
.style34 {color: #DCE7DE}
body {
	background-color: #DCE7DE;
}
body,td,th {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #333300;
	font-weight: bold;
}
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
.style5 {color: #FF0000}
-->
</style>

<script>
    function link(pagina)
    {
        window.location = pagina;
    }
</script>

</head>
                
<body>

<div align="center">
                             
<% 
    //Buscando parametro id do modelo dos graficos
    int idModelo = Integer.parseInt(request.getParameter("id"));
    String nomeArquivo = request.getParameter("nomeArquivo");
    
    AnaliseGrafico analiseGrafico = new AnaliseGrafico(idModelo, nomeArquivo);
    
    //Pegando nome do modelo
    String nomeModelo = analiseGrafico.getNomeModelo();
    
    //Pegando nome dos graficos
    String[] graficos = analiseGrafico.getNomeGraficos();
%>

<h3>Modelo <%= nomeModelo %></h3>

<% if (graficos.length == 0) { %>
    <p> N&atilde;o h&aacute; gr&aacute;ficos nesse modelo. </p>
<% } else { %>
    
<!-- Variaveis do modelo -->
<%
   //Imprimindo nome e valores das variaveis do modelo
    String[] nomeVariaveis = analiseGrafico.getNomeVariaveis();
    String[] valorVariaveis = analiseGrafico.getValorVariaveis();
    
    if (nomeVariaveis != null) {
%>
<table border=1>
    <tr><th colspan=2>Vari&aacute;veis</th></tr>
<%
    for (int i=0; i<nomeVariaveis.length; i++)
    {
%>
        <tr><td align="left"><%= nomeVariaveis[i] %></td>
        <td align="center"><%= valorVariaveis[i] %></td></tr>
<%
    }
%>
        </table>
<% } %>
        
        <form name="tipoNomeGraf">
            <table><tr>
            <td>Gr&aacute;fico: </td> 
            <td align="left"><select name="grafico" id="grafico">
                <%
                   //Nome dos graficos presentes no modelo
                   for (int i=0; i<graficos.length; i++)
                   {
                        if(nomeGrafico.equals(String.valueOf(i))) {
                %>
                            <option  selected value="<%= i %>"><%= graficos[i] %>
                <%}      else {%>
                            <option value="<%= i %>"><%= graficos[i] %>
                <% }} %>
            </select></td></tr>
            
            <tr><td>Analisar: </td>
            <td><select name="analise" id="analise">
                <% if (tipoAnalise.equals("0")) {%>
                    <option selected value="0">Todos os pontos
                <%} else {%>
                    <option value="0">Todos os pontos
                <%} if (tipoAnalise.equals("1")) {%>
                    <option selected value="1">Pontos principais
                <%} else {%>
                    <option value="1">Pontos principais
                <%} if (tipoAnalise.equals("2")) {%>
                    <option selected value="2">Pontos m&aacute;ximo e m&iacute;nimo
                <%} else {%>
                    <option value="2">Pontos m&aacute;ximo e m&iacute;nimo 
                <%} if (tipoAnalise.equals("3")) {%>
                    <option selected value="3">Pontos de interse&ccedil;&atilde;o
                <%} else {%>
                    <option value="3">Pontos de interse&ccedil;&atilde;o <%}%>
            </select></td></tr>
            </table>
            <input name="analisar" type="button" value="Analisar" onclick="javascript:link('analisandografico.jsp?id=<%=idModelo%>&nomeArquivo=<%=java.net.URLEncoder.encode (nomeArquivo,"utf-8")%>&grafico=' + document.tipoNomeGraf.grafico.value + '&analise=' + document.tipoNomeGraf.analise.value)">
        </form
   
<!-- Inicializando classe que ira mostrar o grafico -->
<jsp:useBean id="figuraGrafico" class="analiseGraficos.Grafico"/>
     
<% 
   // pegando os pontos do grafico escolhido
   if (!nomeGrafico.equals(""))
       analiseGrafico.verPontos (graficos[Integer.parseInt(nomeGrafico)]);
   
   // pegando as listas com nomes dos pens e valores
   List pens = analiseGrafico.getPens();
   List valores = analiseGrafico.getValores();
   
   // Colocando essas listas na classe para desenhar grafico
   figuraGrafico.setPens(pens);
   figuraGrafico.setValores(valores);
   
   //Pegando legenda dos eixos x e y
   String legendaX = analiseGrafico.getLegendaX();
   String legendaY = analiseGrafico.getLegendaY();
   
    //Se escolheu ver todos os pontos ...
    if (tipoAnalise.equals("0"))
    {
        figuraGrafico.setTipoGrafico("Todos os pontos");
                
        //desenhando grafico
%>

        <cewolf:chart     
            id="graficoTodosPontos"     
            title="<%= graficos[Integer.parseInt(nomeGrafico)] %>"     
            type="xy"     
            xaxislabel="<%= legendaX %>"     
            yaxislabel="<%= legendaY %>">    
            <cewolf:data>        
                <cewolf:producer id="figuraGrafico">    
                </cewolf:producer>
            </cewolf:data>
        </cewolf:chart>
        
        <cewolf:img chartid="graficoTodosPontos" renderer="cewolf" width="400" height="300"/>
        
        <br>
        <br>
        
        <form action="controlador.Exporta" method="post">
            <input type="hidden" name="conteudo" value="<%= analiseGrafico.getArquivo("Todos os pontos", graficos[Integer.parseInt(nomeGrafico)], valores) %>">
            <input type="hidden" name="nome" value="<%= nomeModelo %>">
            <input type="submit" value="Exportar An&aacute;lise Gr&aacute;fica">
        </form>
        
        <br>
        <br>
<%
        //Imprimindo valores
        Iterator iterator = pens.iterator();
%>
        <table border=1>
            <tr>
                <% while(iterator.hasNext()) { %>
                    <td><%= (String) iterator.next() %></td>
                <% } %>
            </tr>
            <% iterator = valores.iterator();
                while (iterator.hasNext()) { %>
                   <tr>
                   <% for (int i=0; i<pens.size(); i++) { %>
                        <td><%= (String) iterator.next() %></td>
                   <% } %>
                   </tr>
             <% } %>
        </table>
                   
<%  
    }

   //Pegando pontos principais do grafico
   if (tipoAnalise.equals("1"))
   {
       //Pegando principais pontos do grafico
       List princValores = analiseGrafico.verPrincipaisPontos();
       
       figuraGrafico.setValores(princValores);
       figuraGrafico.setTipoGrafico("Principais pontos");
                
       //desenhando grafico
%>

    <cewolf:chart     
            id="graficoPrincipaisPontos"     
            title="<%= graficos[Integer.parseInt(nomeGrafico)] %>"     
            type="xy"     
            xaxislabel="<%= legendaX %>"     
            yaxislabel="<%= legendaY %>">   
            <cewolf:data>        
                <cewolf:producer id="figuraGrafico">    
                </cewolf:producer>
            </cewolf:data>
        </cewolf:chart>
        
        <cewolf:img chartid="graficoPrincipaisPontos" renderer="cewolf" width="400" height="300"/>
        
        <br>
        <br>
        
        <form action="controlador.Exporta" method="post">
            <input type="hidden" name="conteudo" value="<%= analiseGrafico.getArquivo("Principais pontos", graficos[Integer.parseInt(nomeGrafico)], princValores) %>">
            <input type="hidden" name="nome" value="<%= nomeModelo %>">
            <input type="submit" value="Exportar An&aacute;lise Gr&aacute;fica">
        </form>
        
        <br>
        <br>
        
        <table><tr>
        <%
            //imprimindo valores
            Iterator i = pens.iterator();
        
            //Eliminando o nome do eixo x
            String pen = (String) i.next();
        
            while (i.hasNext())
            {
                pen = (String) i.next();
        %>
                <td valign="top"><table border=1>
                    <tr><th colspan=2><%= "Curva " + pen %></th></tr>
            
             <% 
                //Imprimindo valores dessa curva
                Iterator j = princValores.iterator();
                while(j.hasNext())
                {
                    String x = (String) j.next();
                    String y = (String) j.next();
                    String curva = (String) j.next();
                    if (curva.equals(pen)) { 
             %>
                        <tr><td><%= x %></td><td> <%= y %></td></tr>
            <% 
                    }
                }
            %>
                </table></td><td> </td>
            <%
            }
            %>
                </tr></table>

<% 
   }
   
   //Pegando pontos de maximo e minimo do grafico
   if (tipoAnalise.equals("2"))
   {
       //Pegando principais pontos do grafico
       List maxMinValores = analiseGrafico.verMaxMin();
       
       figuraGrafico.setValores(valores, maxMinValores);
       figuraGrafico.setTipoGrafico("Maximo e minimo");
       
       //Renderizando grafico
       pageContext.setAttribute("renderizacao", figuraGrafico.destacaPontos()); 
       
       //desenhando grafico
%>

    <cewolf:chart     
            id="graficoMaxMin"     
            title="<%= graficos[Integer.parseInt(nomeGrafico)] %>"     
            type="xy"     
            xaxislabel="<%= legendaX %>"     
            yaxislabel="<%= legendaY %>">    
            <cewolf:data>        
                <cewolf:chartpostprocessor id="renderizacao"> 
                </cewolf:chartpostprocessor>
                <cewolf:producer id="figuraGrafico">    
                </cewolf:producer>
            </cewolf:data>
        </cewolf:chart>
        
        <cewolf:img chartid="graficoMaxMin" renderer="cewolf" width="400" height="300"/>
        
        <br>
        <br>
        
        <form action="controlador.Exporta" method="post">
            <input type="hidden" name="conteudo" value="<%= analiseGrafico.getArquivo("Maximo e minimo", graficos[Integer.parseInt(nomeGrafico)], maxMinValores) %>">
            <input type="hidden" name="nome" value="<%= nomeModelo %>">
            <input type="submit" value="Exportar An&aacute;lise Gr&aacute;fica">
        </form>
        
        <br>
        <br>
        
        <table><tr>
        <% //Imprimindo valores
           Iterator i = pens.iterator();
           
           //Eliminando o nome do eixo x
           String pen = (String) i.next();
           
           while(i.hasNext())
           {
               pen = (String) i.next();
               boolean maximo = false;
               boolean minimo = false;
        %>
        
                <td valign="top"><table border=1>
                    <tr><th colspan=<%= pens.size() %>><%= "Curva " + pen %></th></tr>
               
         <%    
               Iterator j = maxMinValores.iterator();
               while(j.hasNext())
               {
                   String tipo = (String) j.next();
                   String x = (String) j.next();
                   String y = (String) j.next();
                   
                   if (pen.equals((String) j.next()))
                   {
         %>
                       <tr>
         <%
                        if(tipo.equals("maximo") && !maximo)
                        {
                            maximo = true;
         %>
                       <tr></tr><tr></tr>
                       <tr><td><table border=1>
                           <tr><th colspan=<%= pens.size() %>>Ponto(s) M&aacute;ximo(s)</th></tr>
                           <tr>
                           <% for(int k=0; k<pens.size(); k++) { %>
                                <td><%= (String) pens.get(k) %></td> <% } %>
                           </tr>
         <%
                        }
                   
                        if(tipo.equals("minimo") && !minimo)
                        {
                            minimo = true;
         %>
                       </table></td></tr><tr></tr><tr></tr>
                       <tr><td><table border=1>
                           <tr><th colspan=<%= pens.size() %>>Ponto(s) M&iacute;nimo(s)</th></tr>
                           <tr>
                           <% for(int k=0; k<pens.size(); k++) { %>
                                <td><%= (String) pens.get(k) %></td> <% } %>
                           </tr>
         <%
                        }

                       int posicao = pens.size() * (int) Double.parseDouble(x);
                       for (int k=posicao; k<pens.size() + posicao; k++)
                       {
         %>
                            <td><%= (String) valores.get(k) %></td>
         <%
                       }
         %>
                       </tr>
         <%
                   }
               }
         %>
                       </table></td></tr></table></td><td></td>
         <%
           }
        %>
        </tr></table>

<%
   }
   
   //Pegando pontos de intersecao do grafico
   if (tipoAnalise.equals("3"))
   {
       //Pegando principais pontos do grafico
       List pontosIntersecao = analiseGrafico.verIntersecao();
       
       figuraGrafico.setValores(valores);
       figuraGrafico.setTipoGrafico("Todos os pontos");
       
       //desenhando grafico
%>

       <cewolf:chart     
            id="graficoTodosPontos"     
            title="<%= graficos[Integer.parseInt(nomeGrafico)] %>"     
            type="xy"     
            xaxislabel="<%= legendaX %>"     
            yaxislabel="<%= legendaY %>">    
            <cewolf:data>        
                <cewolf:producer id="figuraGrafico">    
                </cewolf:producer>
            </cewolf:data>
        </cewolf:chart>
        
        <cewolf:img chartid="graficoTodosPontos" renderer="cewolf" width="400" height="300"/>
        
        <br>
        <br>
        
        <form action="controlador.Exporta" method="post">
            <input type="hidden" name="conteudo" value="<%= analiseGrafico.getArquivo("Intersecao", graficos[Integer.parseInt(nomeGrafico)], pontosIntersecao) %>">
            <input type="hidden" name="nome" value="<%= nomeModelo %>">
            <input type="submit" value="Exportar An&aacute;lise Gr&aacute;fica">
        </form>
        
        <br>
        <br>
        
        <%
           //Imprimindo os valores
           if(pontosIntersecao.size() > 0)
           {
        %>
            <table border=1>
                <tr><th colspan=4>Pontos de Interse&ccedil;&atilde;o</th></tr>
                <tr>
                    <td>Eixo x</td>
                    <td>Eixo y</td>
                    <td colspan=2>Curvas</td>
                </tr>
        <%
                Iterator iterator = pontosIntersecao.iterator();
                while(iterator.hasNext())
                {
        %>
                    <tr>
        <%
                    for(int i=0; i<4; i++)
                    {
        %>
                        <td><%= (String) iterator.next() %></td>  
                     
        <%
                    }
        %>
                    </tr>
        <%
                }
        %>
            </table>
        <%
           }
           else
           {
        %>
                Não h&aacute; interse&ccedil;&atilde;o entre as curvas do gr&aacute;fico 
<%
           }
   }
}
    analiseGrafico.fecharArquivo();
%>
         
</div>
</body>
</html>