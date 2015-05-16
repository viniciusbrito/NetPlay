/*
 * PaginaCCA.java
 *
 * Created on 11 de Setembro de 2006, 09:54
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
 * @author Diego Velasco
 */
public class PaginaCCA {
    
    /** Creates a new instance of PaginaCCA */
    public PaginaCCA() {
    }
    
    public static void montaPaginaPesquisaComp(HttpServletResponse response, String nome, String modo, List comps) throws IOException{
        
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        
        montaPaginaPesquisaGeralParte1(response,nome);
        
        out.println("<td width=\"30\"><div align=\"center\" class=\"style41\">#</div></td> \n" +
                "<td width=\"157\"><div align=\"left\"><span class=\"style41\">Nome</span></div></td> \n" +
                "<td width=\"80\">&nbsp;</td> \n" +
                "</tr> \n");
        
        int j = 0;
        for(int i=0; i<comps.size(); i++){
            Composicao c = (Composicao)comps.get(i);
            if(c.getTipo().equals("composicao"))
            {
                j++;
                out.println("<tr><td><div align=\"center\" class=\"style41\">"+ j +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ c.getNome() +"</div></td> \n" +
                    "<td bgcolor=\"#333300\"><div align=\"center\" class=\"style34\"> \n" +
                    "<div align=\"center\"><a href=\"controlador.ControladorWebCCA?metodo=cadastroComp&acao="+ modo + "&nome=" + c.getNome() + "\">\n" +
                    "<span class=\"style34\">"+ modo +"</span></div>");   
            }
        }
        montaPaginaPesquisaGeralParte2(response);
    }
    
    public static void montaPaginaPesquisaBotao(HttpServletResponse response, String nome, String modo, List comps) throws IOException{
        
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        
        montaPaginaPesquisaGeralParte1(response,nome);
        
        out.println("<td width=\"30\"><div align=\"center\" class=\"style41\">#</div></td> \n" +
                "<td width=\"157\"><div align=\"left\"><span class=\"style41\">Nome</span></div></td> \n" +
                "<td width=\"80\">&nbsp;</td> \n" +
                "</tr> \n");
        
        int j = 0;
        for(int i=0; i<comps.size(); i++){
            Composicao c = (Composicao)comps.get(i);
            if(c.getTipo().equals("botao"))
            {
                j++;
                out.println("<tr><td><div align=\"center\" class=\"style41\">"+ j +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ c.getNome() +"</div></td> \n" +
                    "<td bgcolor=\"#333300\"><div align=\"center\" class=\"style34\"> \n" +
                    "<div align=\"center\"><a href=\"controlador.ControladorWebCCA?metodo=cadastroComp&acao="+ modo + "&nome=" + c.getNome() + "\">\n" +
                    "<span class=\"style34\">"+ modo +"</span></div>");   
            }
        }
        montaPaginaPesquisaGeralParte2(response);
    }
    
    
    public static void montaPaginaPesquisaGeralParte1(HttpServletResponse response, String nome) throws IOException{
        
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        
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
        response.setDateHeader("Expires", -1); //evita o caching no servidor proxy
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
    
}
