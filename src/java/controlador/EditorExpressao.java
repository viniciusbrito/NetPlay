/*
 * EditorExpressao.java
 *
 * Created on 16 de Janeiro de 2007, 14:35
 */

package controlador;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import cgt.*;
import cdp.*;

/**
 *
 * @author Diego Velasco
 * @version
 */
public class EditorExpressao extends HttpServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String NovaExpressao = new String();
        /* TODO output your page here
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet controlador</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet controlador at " + request.getContextPath () + "</h1>");
        out.println("</body>");
        out.println("</html>");
         */
        
        String antecessor = request.getParameter("pai");
        String expressao = request.getParameter("expr");
        String exp = expressao;
        
        //eliminando espacos em branco duplos da expressao        
        while (expressao.contains("  "))
            expressao = expressao.replaceAll("  ", " ");        
        
        //transformando em string entendida por HTML
        expressao = java.net.URLEncoder.encode(expressao, "utf-8");
           
        //Monitor
        if(antecessor.equals("monitor")) {
            response.sendRedirect("incluirmonitor.jsp?expr=" + expressao + 
                    "&nome=" + request.getParameter("nome") + 
                    "&casasDecimais=" + request.getParameter("casasDecimais"));
            out.close();
        } else if(antecessor.equals("altmonitor")) {
            response.sendRedirect("alterarmonitor.jsp?nome=" + 
                    request.getParameter("nome") + "&nomeAntigo=" + 
                    request.getParameter("nomeAntigo") + "&casasDecimais=" + 
                    request.getParameter("casasDecimais") + "&status=" + 
                    request.getParameter("status") + "&expressao=" + expressao);            
            out.close();
            
        //Condicao posicionamento
        } else if(antecessor.equals("condpos")) {
            response.sendRedirect("condpos.jsp?expr=" + expressao + 
                    "&agente=" + request.getParameter("agente") +
                    "&agentePrincipal=" + request.getParameter("agentePrincipal") +
                    "&numAgentes=" + request.getParameter("numAgentes") + 
                    "&idComposicao=" + request.getParameter("idComposicao"));
            out.close();
        } else if(antecessor.equals("altcondpos")) {
            response.sendRedirect("condaltpos.jsp?agente=" + 
                    request.getParameter("agente") + "&expr=" + expressao +
                    "&agentePrincipal=" + request.getParameter("agentePrincipal") +
                    "&numAgentes=" + request.getParameter("numAgentes") + 
                    "&idComposicao=" + request.getParameter("idComposicao") +
                    "&id=" + request.getParameter("id"));
            out.close();
            
        //Condicao comparacao de variaveis
        } else if(antecessor.equals("condcmpvar")) {
            response.sendRedirect("condcmpvar.jsp?expr=" + expressao +
                    "&var1=" + request.getParameter("idVar1") + 
                    "&var2=" + request.getParameter("idVar2"));
            out.close();
        } else if(antecessor.equals("altcondcmpvar")) {
            response.sendRedirect("condaltcmpvar.jsp?expr=" + expressao +
                    "&var1=" + request.getParameter("idVar1") + 
                    "&var2=" + request.getParameter("idVar2") +
                    "&id=" + request.getParameter("id"));
            out.close();
            
        //Condicao valor de variavel    
        } else if(antecessor.equals("condvalvar")) {
            response.sendRedirect("condvalvar.jsp?expr=" + expressao +
                    "&var=" + request.getParameter("var"));
            out.close();
        } else if(antecessor.equals("altcondvalvar")) {
            response.sendRedirect("condaltvalvar.jsp?expr=" + expressao +
                    "&var=" + request.getParameter("var") + 
                    "&id=" + request.getParameter("id"));
            out.close();
            
        //Acao recuperar valor de variavel
        } else if(antecessor.equals("acrecvalvar")) {
            String agente = request.getParameter("agente");
            if (agente.equals(""))
                response.sendRedirect("acao_recvalvar.jsp?expr=" + expressao +
                        "&idVar=" + request.getParameter("var"));
            else
                response.sendRedirect("acao_recvalvar.jsp?expr=" + expressao +
                        "&idVar=" + request.getParameter("var") +
                        "&agente=" + agente);
            out.close();
        } else if(antecessor.equals("altacrecvalvar")) {
            String agente = request.getParameter("agente");
            if (agente.equals(""))
                response.sendRedirect("acao_alt_recvalvar.jsp?expr=" + expressao +
                        "&idVar=" + request.getParameter("var") +
                        "&id=" + request.getParameter("id"));
            else
                response.sendRedirect("acao_alt_recvalvar.jsp?expr=" + expressao +
                        "&idVar=" + request.getParameter("var") +
                        "&id=" + request.getParameter("id")+
                        "&agente=" + agente);
            out.close();
        
        //Variavel valor
        } else if(antecessor.equals("variavel")) {
            String agente = request.getParameter("agente");
            if(agente.equals(""))
                response.sendRedirect("variavelvalor.jsp?expr=" + expressao +
                        "&nome=" + request.getParameter("nome"));
            else
                response.sendRedirect("variavelvalor_agente.jsp?" +
                        "expr=" + expressao +
                        "&nome=" + request.getParameter("nome") +
                        "&agente=" + agente);
            out.close();
        } else if(antecessor.equals("altvariavel")) {
            response.sendRedirect("alterar_variavelvalor.jsp?expr=" + expressao +
                    "&nome=" + request.getParameter("nome") +
                    "&nomeAntigo=" + request.getParameter("nomeAntigo") +
                    "&agente=" + request.getParameter("agente"));
            out.close();
        }
        
        //Criar elemento
        else if(antecessor.equals("acao_criar_conf")) {
            int retorno;
            int idVar = Integer.parseInt(request.getParameter("var"));
            int idAcao = Integer.parseInt(request.getParameter("idAcao"));
            String agente = request.getParameter("agente");
            
            retorno = AplCadastrarAcao.alterarAcaoCriarVar(idAcao, idVar, exp);
            response.sendRedirect("acao_criar_conf.jsp?idAcao="+idAcao+"&idAgente="+agente+"&op=criar&status=Variavel%20alterada%20com%20sucesso!");
            out.close();
        }
        
        //inicializar agente
        else if(antecessor.equals("inicializar_agente_conf"))
        {
            int retorno;
            int idVar = Integer.parseInt(request.getParameter("var"));
            int idIni = Integer.parseInt(request.getParameter("idIni"));
            String agente = request.getParameter("agente");
            InicializarAgente ia = InicializarAgente.obterIA(idIni);
            Variavel v = AplCadastrarVariavel.obterVariavel(idVar);
            
            AplCadastrarAgente.alterarInicializarAgenteVar(ia, v, exp);
            response.sendRedirect("inicializar_agente_conf.jsp?idIni="+idIni+"&idAgente="+agente+"&op=criar&status=Variavel%20alterada%20com%20sucesso!");
            out.close();
        }
        
        //percepcao avancado - salvar valor de variavel
        else if(antecessor.equals("percepcao_conf"))
        {
            int retorno;
            int idPerc = Integer.parseInt(request.getParameter("idPerc"));            

            PercepcaoAvancado percAv = AplCadastrarCondicao.obterPercepcaoAvancado(idPerc);
            int idCondicao = percAv.getIdCondicao();
            
            AplCadastrarCondicao.alterarPercepcaoAvancadoVar(percAv, exp);
            response.sendRedirect("percepcao_conf.jsp?idCondicao="+idCondicao);
            out.close();
        }
        out.close();
    }    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
