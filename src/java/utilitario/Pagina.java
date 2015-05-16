/*
 * pagina.java
 *
 * Created on 27 de Maio de 2006, 14:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package utilitario;

import cgt.*;
import cdp.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.aspectj.util.FileUtil;

/**
 *
 * @author Alexandre Andrade
 */

public class Pagina{
    
    /** Creates a new instance of pagina */
    public Pagina() {
    }
          
      public static void montaPaginaPesquisaModelo(HttpServletResponse response, String nome, String modo, List modelos, String checked, String loginUsuario) throws IOException{
   
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        
        montaPaginaPesquisaModeloParte1(response);
                
        out.println("<table width=\"450\" height=\"50\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"> \n" +
                    "<tr> \n" +
                    "<td><h1>&nbsp;</h1></td> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                    "<td height=\"30\" bgcolor=\"#333300\"><div align=\"center\" class=\"style48\">"+ nome +"</div></td> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                    "<td>&nbsp;</td> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                    "<td><h1>&nbsp;</h1></td> \n" +
                    "</tr> \n" +
                    "</table> \n");
                
                    //checkbox para selecionar somente os modelos do usuario
                    if(!modo.equals("Excluir"))
                    {
                        String modo2 = "alteracao";
                        if(modo.equals("Consultar"))
                            modo2 = "consultamodelos";
            
                        String checkbox = "";
                        if(checked == null)
                            checkbox = "<input type=\"checkbox\" name=\"checkbox\" value=\"checkbox\" onclick=\"javascript:window.location.href= 'controlador.ControladorWeb?metodo=pesquisaModelo&acao=" + modo2 + "&checked=0'\" /> \n";
                        else
                            checkbox = "<input type=\"checkbox\" checked name=\"checkbox\" value=\"checkbox\" onclick=\"javascript:window.location.href= 'controlador.ControladorWeb?metodo=pesquisaModelo&acao=" + modo2 + "'\" /> \n";
            
                       out.println("<label> \n" +
                               checkbox +    
                               "<span class=\"style43\">Somente os meus modelos</span></label> \n" +
                               "<br /> \n" +
                               "<br /> \n");
                    }
                
                    out.println("<table width=\"550\" border=\"1\" align=\"center\"> \n" + 
                    "<tr> \n" + 
                    "<th width=\"37\" class=\"style43\" scope=\"row\">#</th> \n" + 
                    "<td width=\"113\"><div align=\"left\"><span class=\"style43\">Nome</span></div></td> \n" + 
                    "<td width=\"274\"><div align=\"left\"><span class=\"style43\">Descri&ccedil;&atilde;o</span></div></td> \n" + 
                    "<td width=\"98\">&nbsp;</td> \n" + 
                    "</tr>");
        
        int j = 0;                         
        for(int i=0; i<modelos.size(); i++){            
            Modelo modelo = (Modelo) modelos.get(i);
            //System.out.println("NOME: " + modelo.getNome() + "\n\n");
            //System.out.println("NOME: " + java.net.URLDecoder.decode(modelo.getDescricao(), "utf-8") + "\n\n");
            String login = AplCadastrarModelo.obterUsuario(modelo.getNome());            
            if (checked == null || login.equals(loginUsuario))
            {
                j++;
                out.println("<tr> \n" +
                        "<th scope=\"row\"><div align=\"center\">1<span class=\"style43\">"+ j +"</span>6</div></th> \n" +
                        "<td><div align=\"left\"><span class=\"style43\">"+ modelo.getNome() +"</span></div></td> \n" +
                        "<td><div align=\"left\"><span class=\"style43\">"+ modelo.getDescricao() +"</span></div></td> \n" +
                        "<td bgcolor=\"#333300\"><div align=\"center\" class=\"style34\"> \n" +
                        "<div align=\"center\"><a href=\"controlador.ControladorWeb?metodo=cadastroModelo&acao="+ modo +"&nome="+ java.net.URLEncoder.encode(modelo.getNome(), "utf-8") + "\">\n" +
                        "<span class=\"style34\">"+ modo +"</span></div> \n" +
                        "</tr> \n");
            }
        }       
        out.println("</table> \n");
        
        montaPaginaPesquisaModeloParte2(response);
    }                   
    
    
    public static void montaPaginaPesquisaTexto(HttpServletResponse response, String nome, String modo, List textos) throws IOException{
        
         response.setContentType("text/html");
         ServletOutputStream out = response.getOutputStream();
         
         montaPaginaPesquisaGeralParte1(response,nome);
        
         out.println("<td width=\"39\"><div align=\"center\" class=\"style41\">#</div></td> \n" +
                    "<td width=\"236\"><div align=\"left\"><span class=\"style41\">Nome</span></div></td> \n" +
                    "<td width=\"96\">&nbsp;</td> \n" +     
                    "</tr> \n");
        
        int j = 0;                         
        for(int i=0; i<textos.size(); i++){
            j = i + 1;
            Texto texto = (Texto)textos.get(i);
            out.println("<tr><td><div align=\"center\" class=\"style41\">"+ j +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ texto.getNome() +"</div></td> \n" +
                    "<td bgcolor=\"#333300\"><div align=\"center\" class=\"style34\"> \n" +
                    "<div align=\"center\"><a href=\"controlador.ControladorWeb?metodo=cadastroTexto&acao="+ modo +"&nome="+ texto.getNome() +"\">\n" +
                    "<span class=\"style34\">"+ modo +"</span></div>");
        }
        
        montaPaginaPesquisaGeralParte2(response);
     }
     
     
    public static void montaPaginaPesquisaMonitor(HttpServletResponse response, String nome, String modo, List monitores) throws IOException{
        
         response.setContentType("text/html");
         ServletOutputStream out = response.getOutputStream();
         
         montaPaginaPesquisaGeralParte1(response,nome);
        
         out.println("<td width=\"39\"><div align=\"center\" class=\"style41\">#</div></td> \n" +
                    "<td width=\"236\"><div align=\"left\"><span class=\"style41\">Nome</span></div></td> \n" +
                    "<td width=\"96\">&nbsp;</td> \n" +     
                    "</tr> \n");
        
        int j = 0;                         
        for(int i=0; i<monitores.size(); i++){
            j = i + 1;
            Monitor monitor = (Monitor)monitores.get(i);
            out.println("<tr><td><div align=\"center\" class=\"style41\">"+ j +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ monitor.getNome() +"</div></td> \n" +
                    "<td bgcolor=\"#333300\"><div align=\"center\" class=\"style34\"> \n" +
                    "<div align=\"center\"><a href=\"controlador.ControladorWeb?metodo=cadastroMonitor&acao="+ modo +"&nome="+ monitor.getNome() + "\">\n" +
                    "<span class=\"style34\">"+ modo +"</span></div>");
        }
        montaPaginaPesquisaGeralParte2(response);
     }
     
    
    public static void montaPaginaPesquisaAgente(HttpServletResponse response, String nome, String modo, List agentes) throws IOException{
        
         response.setContentType("text/html");
         ServletOutputStream out = response.getOutputStream();
         
         montaPaginaPesquisaGeralParte1(response,nome);
        
         out.println("<td width=\"30\"><div align=\"center\" class=\"style41\">#</div></td> \n" +
                    "<td width=\"157\"><div align=\"left\"><span class=\"style41\">Nome</span></div></td> \n" +
                    "<td width=\"100\"><div align=\"left\"><span class=\"style41\">Tipo do Agente</span></div></td> \n" +
                    "<td width=\"80\">&nbsp;</td> \n" +     
                    "</tr> \n");
        
        int j = 0;                         
        for(int i=0; i<agentes.size(); i++){
            j = i + 1;
            Agente agente = (Agente)agentes.get(i);
            String tipoAgente;
            if (agente.getTipo().equals("Movel"))
                tipoAgente = "M&oacute;vel";
            else
                if (agente.getTipo().equals("Estacionario Aleatorio"))
                    tipoAgente = "Estacion&aacute;rio";
                else
                    tipoAgente = "Borda";
            out.println("<tr><td><div align=\"center\" class=\"style41\">"+ j +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ agente.getNome() +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ tipoAgente +"</div></td> \n" +
                    "<td bgcolor=\"#333300\"><div align=\"center\" class=\"style34\"> \n" +
                    "<div align=\"center\"><a href=\"controlador.ControladorWeb?metodo=cadastroAgente&acao="+ modo +"&tipo="+ agente.getTipo() +"&nome="+ agente.getNome() + "\">\n" +
                    "<span class=\"style34\">"+ modo +"</span></div>");
        }
        montaPaginaPesquisaGeralParte2(response);
     }
    
    
    public static void montaPaginaPesquisaVariavel(HttpServletResponse response, String nome, String modo, List variaveis) throws IOException{
        
         response.setContentType("text/html");
         ServletOutputStream out = response.getOutputStream();
         
         montaPaginaPesquisaGeralParte1(response,nome);
        
         out.println("<td width=\"30\"><div align=\"center\" class=\"style41\">#</div></td> \n" +
                    "<td width=\"157\"><div align=\"left\"><span class=\"style41\">Nome</span></div></td> \n" +
                    "<td width=\"100\"><div align=\"left\"><span class=\"style41\">Tipo da Variavel</span></div></td> \n" +
                    "<td width=\"80\">&nbsp;</td> \n" +     
                    "</tr> \n");
        
        int j = 0;                         
        for(int i=0; i<variaveis.size(); i++){
            j = i + 1;
            Variavel variavel = (Variavel)variaveis.get(i);
            out.println("<tr><td><div align=\"center\" class=\"style41\">"+ j +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ variavel.getNome() +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ variavel.getTipo() +"</div></td> \n" +
                    "<td bgcolor=\"#333300\"><div align=\"center\" class=\"style34\"> \n" +
                    "<div align=\"center\"><a href=\"controlador.ControladorWeb?metodo=cadastroVariavel&acao="+ modo +"&tipo="+ variavel.getTipo() +"&nome="+ variavel.getNome() + "\">\n" +
                    "<span class=\"style34\">"+ modo +"</span></div>");
        }
        montaPaginaPesquisaGeralParte2(response);
     }
      
    
    public static void montaPaginaPesquisaVersoesModelo(HttpServletResponse response, String nome, String modo, List modelos) throws IOException{
        
         response.setContentType("text/html");
         ServletOutputStream out = response.getOutputStream();
         
         montaPaginaPesquisaGeralParte1(response,nome);
        
         out.println("<td width=\"30\"><div align=\"center\" class=\"style41\">#</div></td> \n" +
                    "<td width=\"100\"><div align=\"left\"><span class=\"style41\">Nome</span></div></td> \n" +
                    "<td width=\"160\"><div align=\"left\"><span class=\"style41\">Descri&ccedil;&atilde;o</span></div></td> \n" +
                    "<td width=\"80\">&nbsp;</td> \n" +     
                    "</tr> \n");
        
        int j = 0;                         
        for(int i=0; i<modelos.size(); i++){
            j = i + 1;
            Modelo modelo = (Modelo)modelos.get(i);
            out.println("<tr><td><div align=\"center\" class=\"style41\">"+ j +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ modelo.getNome() +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ modelo.getDescricao() +"</div></td> \n" +
                    "<td bgcolor=\"#333300\"><div align=\"center\" class=\"style34\"> \n" +
                    "<div align=\"center\"><a href=\"controlador.ControladorWeb?metodo=consultaVersoesModelo&nome="+ modelo.getNome() + "\">\n" +
                    "<span class=\"style34\">"+ modo +"</span></div>");
        }
        montaPaginaPesquisaGeralParte2(response);
     }
      
    
    public static void montaPaginaPesquisaPlot(HttpServletResponse response, String nome, String modo, List plots) throws IOException{
        
         response.setContentType("text/html");
         ServletOutputStream out = response.getOutputStream();
         
         montaPaginaPesquisaGeralParte1(response,nome);
        
         out.println("<td width=\"30\"><div align=\"center\" class=\"style41\">#</div></td> \n" +
                    "<td width=\"157\"><div align=\"left\"><span class=\"style41\">Nome</span></div></td> \n" +
                    "<td width=\"100\"><div align=\"left\"><span class=\"style41\">Tipo do Grafico</span></div></td> \n" +
                    "<td width=\"80\">&nbsp;</td> \n" +     
                    "</tr> \n");
        
        int j = 0;                         
        for(int i=0; i<plots.size(); i++){
            j = i + 1;
            Plot plot = (Plot)plots.get(i);
            out.println("<tr><td><div align=\"center\" class=\"style41\">"+ j +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ plot.getNomeGrafico() +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ plot.getTipo() +"</div></td> \n" +
                    "<td bgcolor=\"#333300\"><div align=\"center\" class=\"style34\"> \n" +
                    "<div align=\"center\"><a href=\"controlador.ControladorWeb?metodo=cadastroPlot&acao="+ modo +"&tipo="+ plot.getTipo() +"&nome="+ plot.getNomeGrafico() + "\">\n" +
                    "<span class=\"style34\">"+ modo +"</span></div>");                      
        }
        montaPaginaPesquisaGeralParte2(response);
     }
      
    
    public static void montaPaginaPesquisaGeralParte1(HttpServletResponse response, String nome) throws IOException{
           
          response.setContentType("text/html");
          ServletOutputStream out = response.getOutputStream();
    
          //out.println("<%@ page contentType=\"text/html; charset=iso-8859-1\" language=\"java\" import=\"java.sql.*\" errorPage=\"\" %> \n" +
          out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> \n" +
                    "<html>\n" +
                     "<head>\n" +
                    "<title>Untitled Document</title>\n" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\n" +
                    "<style type=\"text/css\">\n" +
                    "<!--\n" +
                    "body,td,th {font-size: 11px; color: #333300; font-family: Arial, Helvetica, sans-serif;font-weight: bold;}\n" +
                    "body {background-color: #DCE7DE;}\n" +
                    "a {font-size: 12px;color: #DCE7DE;	font-weight: bold;}\n" +
                    "a:link {text-decoration: none;}\n" +
                    "a:visited {text-decoration: none;color: #DCE7DE;}\n" +
                    "a:hover {text-decoration: none;color: #A8C4AD;}\n" +
                    "a:active {	text-decoration: none;	color: #DCE7DE;}\n" +
                    ".style1 {color: #DCE7DE} \n" +
                    ".style38 {font-size: 14px} \n" +
                    ".style40 {color: #DCE7DE; font-size: 14px; } \n" +
                    ".style41 {color: #405B45} \n" +
                    ".style34 {color: #DCE7DE} \n" +
                    "-->\n" +
                    "</style>\n" +
                    "</head> \n" +
                    "<body> \n" +
                    "<div align=\"center\"> \n" +
                    "<table width=\"500\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> \n" +
                    "<tr> \n" +
                    "<td colspan=\"4\"><div align=\"center\" class=\"style38\"> \n" +
                    "<table width=\"500\" height=\"32\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"#DCE7DE\" bgcolor=\"#333300\"> \n" +
                    "<tr> \n" +
                    "<td width=\"457\" height=\"30\"><div align=\"center\"><span class=\"style40\">" + nome +"</span></div></td> \n" +
                    "</tr> \n" +
                    "</table> \n" +
                    "</div> \n" +
                    "</td> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                    "<td height=\"15\" colspan=\"4\"><div align=\"center\"> \n" +
                    "</div></td> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                    "<td height=\"15\" colspan=\"4\"><div align=\"center\"> </div></td> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                    "<td height=\"15\" colspan=\"4\"><div align=\"center\"> \n" +
                    "<table width=\"500\" border=\"1\" bgcolor=\"#BBD0BF\"> \n" +
                    "<tr> \n" +
                    "<td><div align=\"center\"> \n" +
                    "<table width=\"380\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> \n" +
                    "<tr> \n" +
                    "<td> \n" +
                    "<table width=\"380\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> \n" +
                    "<tr> \n" +
                    "<td width=\"380\" height=\"10\" valign=\"top\">&nbsp;</td> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                    "<td><div align=\"center\"> \n" +
                    "<table width=\"382\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#DCE7DE\"> \n" +
                    "<tr>");
                     response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
                     response.setHeader("Pragma","no-cache"); //HTTP 1.0
                     response.setDateHeader ("Expires", -1); //evita o caching no servidor proxy
     }
     
    
    public static void montaPaginaPesquisaGeralParte2(HttpServletResponse response) throws IOException{
     
         response.setContentType("text/html");
         ServletOutputStream out = response.getOutputStream();
         
         out.println("</table> \n" +
                     "</div></td> \n" +
                     "</tr> \n" +
                     "</table> \n" +
                     "</td> \n" +
                     "</tr> \n" +
                     "<tr> \n" +
                     "<td height=\"10\"><div align=\"right\"> \n" +
                     "</div></td> \n" +
                     "</tr> \n" +
                     "<tr> \n" +
                     "<td width=\"380\" height=\"10\"><div align=\"right\"> \n" +
                     "</div></td> \n" +
                     "</tr> \n" +
                     "</table> \n" +
                     "</div></td> \n" +
                     "</tr> \n" +
                     "</table> \n" +
                     "</div></td> \n" +
                     "</tr> \n" +
                     "</table> \n" +
                     "</div> \n" +
                     "<div align=\"center\"></div> \n" +
                     "</body> \n" +
                     "</html> \n");
     }    
    
    
    public static void VersoesModelos(HttpServletResponse response,Modelo modelo,String nome,String teste) throws IOException{
       
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        List modelosFilhos = null;
                
        out.println("<tr><td height=\"10\"><span class=\"style44\">"+ teste +"NOME DO MODELO : " + modelo.getNome() + "</span></td> \n" +
        "</tr><tr><td height=\"10\"><span class=\"style44\">"+ teste +"DATA DE CRIA&Ccedil;&Atilde;O : " + modelo.getDtCriacao() + "</span></td> \n" +
        "</tr><tr><td height=\"10\"><span class=\"style44\">"+ teste +"MODELO PAI : "+ nome +"</span></td></tr> \n"+
        "<td height=\"5\">&nbsp;</td>");
        teste = teste + ">>>>>>>>>>>>";       
                
        modelosFilhos = AplCadastrarModelo.modelosFilhos(modelo.getIdModelo().intValue());
        if(modelosFilhos.isEmpty())
            return;
        else{
            for(int i=0; i<modelosFilhos.size(); i++){
                Modelo modeloAux = (Modelo)modelosFilhos.get(i);
                VersoesModelos(response,modeloAux,modelo.getNome(),teste);
            }
        }
   }
   
    
    public static void montaPaginaVersoesModelo(HttpServletResponse response, Modelo modelo, String nome, String teste) throws IOException{
        
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        
            //out.println("<%@ page contentType=\"text/html; charset=iso-8859-1\" language=\"java\" import=\"java.sql.*\" errorPage=\"\" %> \n" +
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> \n" +
            "<html><head><title>Untitled Document</title> \n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\"> \n" +
            "<style type=\"text/css\"> \n" +
            "<!--body,td,th {font-size: 11px;color: #DCE7DE;font-family: Arial, Helvetica, sans-serif;font-weight: bold;}\n" +
            "body {background-color: #FFFFFF;}\n" +
            "a {font-family: Arial, Helvetica, sans-serif;font-size: 12px;color: #333300;}\n" +
            "a:visited {color: #DCE7DE;text-decoration: none;}\n" +
            "a:hover {color: #A8C4AD;text-decoration: none;}\n" +
            "a:active {color: #DCE7DE;text-decoration: none;}\n" +
            "body {background-color: #DCE7DE;} \n" +
            "a:link {text-decoration: none;color: #DCE7DE;font-weight: bold;}\n" +
            "h1 {font-size: 0px;}\n" +
            "h2 {font-size: 1px;}\n" +
            "h3 {font-size: 2mm;}\n" +
            ".style44 {color: #333300}\n"+
            "-->\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div align=\"center\">\n" +
            "<table width=\"200%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "<tr>\n" +
            "<th width=\"200%\" scope=\"row\"><table width=\"200%\" border=\"0\">\n" +
            "<tr>\n" +
            "<th scope=\"row\"><table width=\"200%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#BBD0BF\">\n" +
            "<tr>\n" +
            "<td><div align=\"center\">\n" +
            "</div></td>\n" +
            "</tr>\n" +
            "<tr>\n");       
            
            VersoesModelos(response,modelo,nome,teste);
            
            out.println("</tr></table></th></tr></table></th></tr></table><p>&nbsp;</p><p>&nbsp;</p></div></body></html>");          
   }   

    public static void montaPaginaPesquisaModeloParte1(HttpServletResponse response) throws IOException{
        
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> \n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<title>Untitled Document</title>\n" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\n" +
                    "<style type=\"text/css\">\n" +
                    "<!--\n" +
                    "body,td,th {font-size: 11px; color: #DCE7DE; font-family: Arial, Helvetica, sans-serif;font-weight: bold;}\n" +
                    "body {background-color: #FFFFFF;}\n" +
                    "a {font-family: Arial, Helvetica, sans-serif;font-size: 12px;color: #333300;}\n" +
                    "a:visited {color: #DCE7DE;text-decoration: none;}\n" +
                    "a:hover {color: #A8C4AD;text-decoration: none;}\n" +
                    "a:active {color: #DCE7DE;text-decoration: none;}\n" +
                    "a:link {text-decoration: none;color: #DCE7DE;font-weight: bold;}\n" +
                    "h1 {font-size: 0px;}\n" +
                    "h2 {font-size: 1px;}\n" +
                    "h3 {font-size: 2mm;}\n" +
                    ".style33 {font-family: \"Futura Md BT\";	font-size: 50px;color: #FFFFFF;}\n" +
                    ".style34 {color: #DCE7DE}\n" +
                    ".style37 {color: #FFFFFF}\n" +
                    ".style43 {color: #405B45}\n" +
                    ".style44 {font-size: 12px}\n" +
                    ".style48 {	color: #DCE7DE;	font-size: 16px;} \n" +
                    "-->\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<table width=\"100%\" height=\"390\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#333300\">\n" +
                    "<tr>\n" +
                    "<td valign=\"top\">&nbsp;</td>\n" +
                    "<td valign=\"top\"><div align=\"center\">\n" +
                    "<table width=\"100%\" height=\"50\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "<tr>\n" +
                    "<td width=\"227\"><div align=\"center\" class=\"style33\">NET<span class=\"style37\">PLAY</span></div></td>\n" +
                    "<td width=\"515\">&nbsp;</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td colspan=\"2\"><div align=\"center\">\n" +
                    "<table width=\"100%\" height=\"5\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#DCE7DE\">\n" +
                    "<tr>\n" +
                    "<td>&nbsp;</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "</div></td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "</div>\n" +
                    "<div align=\"center\">\n" +
                    "<table width=\"750\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#333300\">\n" +
                    "<tr>\n" +
                    "<td>&nbsp;</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "<table width=\"100%\" height=\"280\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#DCE7DE\">\n" +
                    "<tr>\n" +
                    "<td height=\"281\" valign=\"top\"><div align=\"center\">");
    }
    
    
    public static void montaPaginaPesquisaModeloParte2(HttpServletResponse response) throws IOException{
    
          response.setContentType("text/html");
          ServletOutputStream out = response.getOutputStream();
        
          out.println("<table width=\"540\" height=\"20\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"> \n" +
                      "<tr> \n" +
                      "<td height=\"34\"> \n" +
                      "<div align=\"left\"> \n" +
                      "<table width=\"50\" height=\"20\" border=\"1\" align=\"right\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#333300\"> \n" +
                      "<tr> \n" +
                      "<td bgcolor=\"#333300\"><div align=\"center\" class=\"style34\"> \n" +
                      "<div align=\"center\"><a href=\"menu.jsp\" class=\"style34\">Voltar</a></div> \n" +
                      "</div></td> \n" +
                      "</tr> \n" +
                      "</table> \n" +
                      "</div></td> \n" +
                      "</tr> \n" +
                      "</table> \n" +
                      "</div> \n" +
                      "<div align=\"right\"> \n" +
                      "<p>&nbsp;</p> \n" +
                      "<p align=\"left\">&nbsp;</p> \n" +
                      "</div></td> \n" +
                      "</tr> \n" +
                      "</table> \n" +
                      "</div></td> \n" +
                      "<td height=\"366\" valign=\"top\">&nbsp;</td> \n" +
                      "</tr> \n" +
                      "<tr> \n" +
                      "<td valign=\"top\">&nbsp;</td> \n" +
                      "<td valign=\"top\">&nbsp;</td> \n" +
                      "<td height=\"16\" valign=\"top\">&nbsp;</td> \n" +
                      "</tr> \n" +
                      "</table> \n" +
                      "</body> \n" +
                      "</html>");
    }

}


