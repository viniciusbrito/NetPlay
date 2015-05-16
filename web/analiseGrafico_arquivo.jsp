<%@ page contentType="text/html; charset=iso-8859-1"  language="java" import="java.sql.*" import="cdp.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page
   import="java.io.*" 
   import="java.util.*" 
   import="org.apache.commons.fileupload.servlet.*"
   import="org.apache.commons.fileupload.*"
   import="org.apache.commons.fileupload.disk.*"
   import="analiseGraficos.*"
%>

<html>
<head>
<title>Analisando Gr&aacute;ficos</title>
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
</head>

<body>
<div align="center">
  <p>&nbsp;</p>
  <p><h1>An&aacute;lise de Gr&aacute;ficos</h1></p>
  
  <br>
  
  <table>
      <div align="left">
  Para analisar dados dos gr&aacute;ficos de um modelo:<br>
     1. Construa o modelo no NetPlay.<br>
     2. Clique no bot&atilde;o Gerar Arquivo e salve o modelo em seu computador.<br>
     3. Execute o modelo usando o NetLogo.<br>
     4. Ao final da simula&ccedil;&atilde;o, clique em File -> Export -> All Plots... no NetLogo.<br>
     5. Salve o arquivo com extens&aacute;o csv.<br>
     6. Clique no botão abaixo para anexar esse arquivo.<br>
      </div>
  </table>
  
  <% String id = request.getParameter("id");
     String anexou = request.getParameter("anexou"); 
     
     String pagina = "analiseGrafico_arquivo.jsp?anexou=1" + 
             "&id=" + id;
     
     if (anexou == null) {
  %>
  
  <form method="POST" action="<%= pagina %>" ENCTYPE="multipart/form-data"> 
        <input type="file" name="arquivo"><br>
        <input type="submit" value="Analisar">  
  </form>
  
  <% }
     else
     {
        if (FileUpload.isMultipartContent(request))
        {
            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();
        
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
            List items = upload.parseRequest(request);
    
            // Process the uploaded items
            Iterator iter = items.iterator();
            while (iter.hasNext()) 
            {
                FileItem item = (FileItem) iter.next();

                //eh um arquivo
                if (!item.isFormField()) 
                {
                    String nome = item.getName();
                    if (nome.equals(""))
                    {
  %>
  
  <form method="POST" action="<%= pagina %>" ENCTYPE="multipart/form-data"> 
        <input type="file" name="arquivo"><br>
        <input type="submit" value="Analisar">  
  </form>
  
  <br><br><p style="color:#FF0000">Erro na abertura do arquivo!!!</p>
  
  <%
                        
                    }
                    
                    else
                    { 
                        //Criando arquivo e inserindo referencia no BD
                        NovaAnaliseGrafica analise = 
                                new NovaAnaliseGrafica (Integer.parseInt(id),
                                item);
                        
                        //Chamando pagina que trata os dados dos graficos
  %>
                        <jsp:forward page = "analisandografico.jsp" >
                            <jsp:param name = "id" value = "<%= id %>" />
                            <jsp:param name = "nomeArquivo"
                                       value = "<%= analise.getNomeArquivo() %>" />
                        </jsp:forward>
  <%
                    }
                }
            }
        }
        else
        {
  %>
  
  <form method="POST" action="<%= pagina %>" ENCTYPE="multipart/form-data"> 
        <input type="file" name="arquivo"><br>
        <input type="submit" value="Analisar">  
  </form>
  
  <br><br><p style="color:#FF0000">Erro na abertura do arquivo!!!</p>
  
  <%           
        }
     }
  %>
  
  
</div>
</body>
</html>
