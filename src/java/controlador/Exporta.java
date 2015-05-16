/*
 * Exporta.java
 *
 * Criado em 26 de Junho de 2006, 17:25
 */

package controlador;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import utilitario.*;

import cdp.Modelo;
import interfaceNetLogo.*;
import analiseGraficos.*;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class Exporta extends HttpServlet {
    
    int idModelo;
    PrintWriter out;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {    
        String metodo = request.getParameter("metodo"); 
        
        //gerando somente procedimentos do NetLogo
        if (metodo.compareTo("gerarCodigo") == 0)
        {
            idModelo = Integer.parseInt(request.getParameter("id"));
            
            response.setContentType("text/html");
            out = response.getWriter();
            
            gerarCodigo();
            
            response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
            response.setHeader("Pragma","no-cache"); //HTTP 1.0
            response.setDateHeader ("Expires", -1); //evita o caching no servidor proxy
           
            out.close();
        }
        
        //compilando modelo e gerando arquivo com extensao nlogo
        if (metodo.compareTo("gerarArquivo") == 0)
        {
            idModelo = Integer.parseInt(request.getParameter("id"));
            
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "filename=" + verNomeModelo() + ".nlogo"); 
            out = response.getWriter();
            
            gerarArquivo();
            
            response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
            response.setHeader("Pragma","no-cache"); //HTTP 1.0
            response.setDateHeader ("Expires", -1); //evita o caching no servidor proxy
            
            out.close();
        }
        
        //consultando analises graficas
        if (metodo.compareTo("consultarAnalises") == 0)
        {
            //pegando parametros
            String idModelo = request.getParameter("idModelo");
            String idUsuario = request.getParameter("idUsuario");
            
            response.setContentType("text/html");
            out = response.getWriter();
            
            analisarGraficos("consultar", Integer.parseInt(idModelo), 
                    Integer.parseInt(idUsuario));
            
            response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
            response.setHeader("Pragma","no-cache"); //HTTP 1.0
            response.setDateHeader ("Expires", -1); //evita o caching no servidor proxy
           
            out.close();
        }
        
        //excluindo analises graficas
        if (metodo.compareTo("excluirAnalises") == 0)
        {
            //pegando parametros
            String idModelo = request.getParameter("idModelo");
            String idUsuario = request.getParameter("idUsuario");
            
            response.setContentType("text/html");
            out = response.getWriter();
            
            analisarGraficos("excluir", Integer.parseInt(idModelo), 
                    Integer.parseInt(idUsuario));
            
            response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
            response.setHeader("Pragma","no-cache"); //HTTP 1.0
            response.setDateHeader ("Expires", -1); //evita o caching no servidor proxy
           
            out.close();
        }
    }  
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {
        //pegando parametro nome do arquivo
        String nomeArquivo = request.getParameter("nome");
        
        //pegando parametro conteudo do arquivo
        String conteudoArquivo = request.getParameter("conteudo");
        
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "filename=" + nomeArquivo + ".csv"); 
        out = response.getWriter();
            
        out.print(conteudoArquivo);
            
        out.close();
    }
    
    private void gerarCodigo ()
    {        
        Codigo codigo = new Codigo (idModelo);
        geraHtml ("Codigo em NetLogo", codigo.getCodigoHtml());
    }
    
    private void gerarArquivo () 
    {
        Arquivo arquivo = new Arquivo (idModelo);
        
        out.print(arquivo.getArquivo());
    }
    
    private void analisarGraficos (String tipo, int idModelo, int idUsuario)
    {
        PaginaAnGraficos pagina = new PaginaAnGraficos (tipo, idModelo, 
                idUsuario);
        
        out.print (pagina.getPagina(tipo));
    }

    private String verNomeModelo ( )
    {
        Session sessao = HibernateUtility.getSession();
        Transaction t = sessao.beginTransaction();
       
        Query consulta = sessao.createQuery("from Modelo m where m.idModelo= :id");
        consulta.setInteger("id", idModelo);
        List lista = consulta.list();
        Modelo modelo = (Modelo) lista.get(0);
        
        String nomeModelo = modelo.getNome();
        
        t.commit();
        sessao.close();
        
        return nomeModelo;
    }
    
    private void geraHtml (String titulo, String codigoHtml)
    {
        out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n\n" +
            "<html>\n" + 
            "<head>\n" + 
            
            "<title>" + titulo + "</title>\n" +
                
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\n" +
            "<style type=\"text/css\">\n" +
            "<!--\n" +
            "body {\n" +
                "\tbackground-color: #BBD0BF;}\n" +
            "-->\n"+
            "</style>\n"+
            "</head>\n"+
            "<body>\n"+
            "<pre>\n" + 
                
            codigoHtml +
                
            "</pre>\n" +
            "</body>\n" +
            "</html>\n");

        out.close();
    }   
}
