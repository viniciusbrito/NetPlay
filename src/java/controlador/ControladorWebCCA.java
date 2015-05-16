/*
 * ControladorWebCCA.java
 *
 * Created on 16 de Agosto de 2006, 15:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package controlador;

import cdp.*;
import cgt.*;
import java.sql.Array;
import org.hibernate.Session;
import utilitario.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Diego Velasco
 */
public class ControladorWebCCA extends HttpServlet{
    
    //Usuario usuario;
    Modelo m;
    String composicao;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }
    
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        HttpSession session = request.getSession(true);
        
        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        
        String metodo = request.getParameter("metodo");
        String acao = "";
        int retorno;
        
//*******************************************************************************************//
//***************PESQUISA DE COMPOSICOES PARA ALTERACAO, CONSULTA OU EXCLUSAO****************//
//*******************************************************************************************//
        if(metodo.compareTo("pesquisaComp")==0){
            
            acao = request.getParameter("acao");
            List comps = null;
            m = (Modelo)session.getAttribute("modelo");
            comps = AplCadastrarComposicao.composicoesDoModelo(m.getIdModelo().intValue());
            
            if(acao.compareTo("alteracao")==0){
                if(comps.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui composicoes.");
                else
                    utilitario.PaginaCCA.montaPaginaPesquisaComp(response,"SELECIONE A COMPOSICAO A SER ALTERADA","Alterar",comps);
                return;
            }
            
            if(acao.compareTo("consulta")==0){
                if(comps.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui composicoes.");
                else
                    utilitario.PaginaCCA.montaPaginaPesquisaComp(response,"SELECIONE A COMPOSICAO A SER CONSULTADA","Consultar",comps);
                return;
            }
            
            if(acao.compareTo("exclusao")==0){
                if(comps.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui composicoes.");
                else
                    utilitario.PaginaCCA.montaPaginaPesquisaComp(response,"SELECIONE A COMPOSICAO A SER EXCLUIADA","Excluir",comps);
                return;
            }
        }
//*******************************************************************************************//
//***************PESQUISA DE BOTOES PARA ALTERACAO, CONSULTA OU EXCLUSAO****************//
//*******************************************************************************************//
        if(metodo.compareTo("pesquisaBotao")==0){
            
            acao = request.getParameter("acao");
            List comps = null;
            m = (Modelo)session.getAttribute("modelo");
            comps = AplCadastrarComposicao.composicoesDoModelo(m.getIdModelo().intValue());
            
            if(acao.compareTo("alteracao")==0){
                if(comps.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui Botoes.");
                else
                    utilitario.PaginaCCA.montaPaginaPesquisaBotao(response,"SELECIONE O BOTAO A SER ALTERADA","Alterar",comps);
                return;
            }
            
            if(acao.compareTo("consulta")==0){
                if(comps.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui botoes.");
                else
                    utilitario.PaginaCCA.montaPaginaPesquisaBotao(response,"SELECIONE O BOTAO A SER CONSULTADA","Consultar",comps);
                return;
            }
            
            if(acao.compareTo("exclusao")==0){
                if(comps.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui composicoes.");
                else
                    utilitario.PaginaCCA.montaPaginaPesquisaBotao(response,"SELECIONE O BOTAO A SER EXCLUIADO","Excluir",comps);
                return;
            }
        }
        
        
//*******************************************************************************************//
//******************CADASTRO DE COMPOSICAO. CONSULTA, EXCLUSAO, ALTERACAO********************//
//*******************************************************************************************//
        if(metodo.compareTo("cadastroComp")==0){
            
            acao = request.getParameter("acao");
            
            //declarando as variaveis de composicao
            String nome = "";
            Composicao c;
            
            nome = request.getParameter("nome");
            
            c = AplCadastrarComposicao.obterComposicao(m.getIdModelo().intValue(),nome);
            
            if(acao.compareTo("Alterar")==0){
                response.sendRedirect("alterarcomp.jsp?nome="+ nome +"&op="+c.getOperador());
                return;
            }
            
            if((acao.compareTo("Consultar")==0)){
                response.sendRedirect("consultar_comp.jsp?nome="+ nome +"&op="+c.getOperador());
                return;
            }
            
            if(acao.compareTo("Excluir")==0){
                AplCadastrarComposicao.excluirComposicao(c);
                response.sendRedirect("incluirmodelo.jsp?status="+"Composicao excluida com sucesso.");
                return;
            }
        }
        
//*******************************************************************************************//
//***************************** PERCEPCAO AVANCADAO ****************************************//
//*******************************************************************************************        
        if(metodo.compareTo("cadPercAvan")==0)
        {
            acao = request.getParameter("acao");
            
            if(acao.equals("incluir"))
            {
                int idCondicao = Integer.parseInt(request.getParameter("idCondicao"));
                int idVar = Integer.parseInt(request.getParameter("idVar"));
                String valor  = request.getParameter("valor");
                String agente = request.getParameter("agente");
                
                retorno = AplCadastrarCondicao.inserirPercpcaoAvancadoVar(idCondicao, idVar, valor, agente);
                response.sendRedirect("percepcao_conf.jsp?idCondicao="+idCondicao+"&status="+"Add");
            }
            if(acao.equals("alterar"))
            {
                int idPerc = Integer.parseInt(request.getParameter("idPerc"));            
                String exp = request.getParameter("expressao");
                
                PercepcaoAvancado percAv = AplCadastrarCondicao.obterPercepcaoAvancado(idPerc);
                int idCondicao = percAv.getIdCondicao();

                AplCadastrarCondicao.alterarPercepcaoAvancadoVar(percAv, exp);
                response.sendRedirect("percepcao_conf.jsp?idCondicao="+idCondicao);
            }
            if(acao.equals("excluir"))
            {
                int idCondicao = Integer.parseInt(request.getParameter("idCondicao"));
                int idPerc = Integer.parseInt(request.getParameter("idPerc"));
                retorno = AplCadastrarCondicao.removerPercepcaoAvancadoVar(idPerc);
                response.sendRedirect("percepcao_conf.jsp?idCondicao="+idCondicao+"&status="+"Add");
            }
        }
        
        if(metodo.compareTo("cadPercCFT")==0)
        {
            acao = request.getParameter("acao");
            
            if(acao.equals("incluir"))
            {
                int idCondicao = Integer.parseInt(request.getParameter("idCondicao"));
                int idAgente = Integer.parseInt(request.getParameter("idAgente"));
                String var  = request.getParameter("var");
                switch(var){
                    case "cor":
                        String cor = AplCadastrarAgente.obterAgente(idAgente).getCor();
                        AplCadastrarCondicao.inserirPercpcaoCFT(idCondicao, var, cor);
                    break;
                    
                    case "forma":
                        String forma = AgenteMovel.buscarAgente(idAgente).getForma();
                        AplCadastrarCondicao.inserirPercpcaoCFT(idCondicao, var, forma);
                    break;
                    
                    case "tamanho":
                        int tamanho = AgenteMovel.buscarAgente(idAgente).getTamanho();
                        AplCadastrarCondicao.inserirPercpcaoCFT(idCondicao, var, tamanho, "igual");
                    break;
                }
                response.sendRedirect("percepcao_conf.jsp?idCondicao="+idCondicao+"&status="+"Add");
            }
            if(acao.equals("alterar"))
            {
                int idPerc = Integer.parseInt(request.getParameter("idPerc"));
                String operador = request.getParameter("operador");
                String valor = request.getParameter("valor");
                
                PercepcaoCFT percCFT = AplCadastrarCondicao.obterPercepcaoCFT(idPerc);
                int idCondicao = percCFT.getIdCondicao();

                AplCadastrarCondicao.alterarPercepcaoCFT(percCFT, valor, operador);
                response.sendRedirect("percepcao_conf.jsp?idCondicao="+idCondicao);
            }
            if(acao.equals("excluir"))
            {
                int idCondicao = Integer.parseInt(request.getParameter("idCondicao"));
                int idPerc = Integer.parseInt(request.getParameter("idPerc"));
                retorno = AplCadastrarCondicao.removerPercepcaoCFT(idPerc);
                response.sendRedirect("percepcao_conf.jsp?idCondicao="+idCondicao+"&status="+"Add");
            }
        }
        
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        HttpSession session = request.getSession(true);
        
        //Declarando variaveis auxiliares
        int retorno;
        m = (Modelo)session.getAttribute("modelo");
        
        //Declarando variaveis de controle
        String metodo=request.getParameter("metodo");
        String acao="";
        
//*******************************************************************************************//
//******************CADASTRO DE CONDICOES. INCLUSAO E ALTERACAO******************************//
//*******************************************************************************************//
        if(metodo.compareTo("cadastroCondicao") == 0) {
            acao = request.getParameter("acao");
            Composicao c = AplCadastrarComposicao.obterComposicao(m.getIdModelo().intValue(),composicao);
            session.setAttribute("composicao", c);           
            
            //Atributos da condicao
            String nome = "";
            String tipoCond = "";
            //Condicao de Percepcao
            String agente = "";
            String agentePrincipal = "";
            int distancia = 0;
            String modo = "";
            //Condicao de Posicionamento
            String expressao = "";
            //Condicao de Comparacao de Variaveis
            String var1 = "";
            String var2 = "";
            //Condicao de Valor de Variavel
            String val;
            String var = "";
            
            if(acao.compareTo("incluir") == 0) {
                tipoCond = request.getParameter("TipoCondicao");
                
                if(tipoCond.compareTo("Percepcao") == 0) {
                    agente = request.getParameter("agente");
                    agentePrincipal = request.getParameter("agentePrincipal");
                    distancia = Integer.parseInt(request.getParameter("distancia"));
                    modo = request.getParameter("modo");
                    
                    retorno = AplCadastrarCondicao.incluirNovaCondicaoPer(agente,agentePrincipal,distancia,modo,c);
                    if(retorno > 0){
                        if(modo.equals("normal"))
                            response.sendRedirect("incluircomp.jsp?status="+"Condicao salva com sucesso!");
                        else if(modo.equals("avancado"))
                            response.sendRedirect("percepcao_conf.jsp?idCondicao="+retorno+"&status="+"Condicao salva com sucesso!");
                    }else{
                        if(retorno == -1)
                            response.sendRedirect("condperc.jsp?status="+"Ja existe uma CONDICAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoCond.compareTo("Posicionamento") == 0) {
                    expressao = request.getParameter("expressao");
                    int numAgentes = Integer.parseInt(request.getParameter("numAgentes"));
                    agente = request.getParameter("agente");
                    
                    if(numAgentes == 2)
                        agentePrincipal = request.getParameter("agentePrincipal");
                    else
                        agentePrincipal = "";
                    
                    retorno = AplCadastrarCondicao.incluirNovaCondicaoPos(expressao,agente,agentePrincipal,numAgentes,c);
                    
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Condicao salva com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("condperc.jsp?status="+"Ja existe uma CONDICAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoCond.compareTo("CorAgente") == 0) {
                    String cor = request.getParameter("cor");
                    agente = request.getParameter("agente");
                    
                    retorno = AplCadastrarCondicao.incluirNovaCondicaoCorAg(cor,agente,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Condicao salva com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("condcorag.jsp?status="+"Ja existe uma CONDICAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoCond.compareTo("CmpVar") == 0) {
                    expressao = request.getParameter("expressao");
                    var1 = request.getParameter("var1");
                    var2 = request.getParameter("var2");
                    
                    retorno = AplCadastrarCondicao.incluirNovaCondicaoCmpVar(expressao,var1, var2,m,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Condicao salva com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("condpos.jsp?status="+"Ja existe uma CONDICAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoCond.compareTo("ValVar") == 0) {
                    val = request.getParameter("expr");
                    var = request.getParameter("variavel");

                    retorno = AplCadastrarCondicao.incluirNovaCondicaoValVar(val,var,m,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Condicao salva com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("condvalvar.jsp?status="+"Ja existe uma CONDICAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoCond.compareTo("Tamanho") == 0) {
                   
                    int idAgente = Integer.parseInt(request.getParameter("agente"));
                    String operador = request.getParameter("operador");
                    int tam = Integer.parseInt(request.getParameter("valor"));
                    retorno = AplCadastrarCondicao.incluirNovaCondicaoTamanho(idAgente, operador, tam, c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Condicao salva com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("condtamanho.jsp?status="+"Ja existe uma CONDICAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                }
                
            } else if(acao.compareTo("alterar") == 0) {
                tipoCond = request.getParameter("TipoCondicao");
                                
                if(tipoCond.compareTo("Percepcao") == 0) {
                    Percepcao x = (Percepcao) AplCadastrarCondicao.obterCondicao(Integer.parseInt(request.getParameter("id")));
                    agente = request.getParameter("agente");
                    agentePrincipal = request.getParameter("agentePrincipal");
                    distancia = Integer.parseInt(request.getParameter("distancia"));
                    modo = request.getParameter("modo");
                    
                    int idAgente = Integer.parseInt(agente);
                    int idAgentePrincipal = Integer.parseInt(agentePrincipal);
                    
                    retorno = AplCadastrarCondicao.alterarDadosCondicaoPer(x,idAgente,idAgentePrincipal,distancia,modo,m,c);
                        
                    
                    if(retorno > 0){
                        if(modo.equals("normal"))
                            response.sendRedirect("incluircomp.jsp?status="+"Condicao salva com sucesso!");
                        else if(modo.equals("avancado"))
                            response.sendRedirect("percepcao_conf.jsp?idCondicao="+retorno+"&status="+"Condicao salva com sucesso!");
                    }else{
                        if(retorno == -1)
                            response.sendRedirect("condaltperc.jsp?status="+"Ja existe uma CONDICAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoCond.compareTo("Posicionamento") == 0) {
                    Posicionamento x = (Posicionamento) AplCadastrarCondicao.obterCondicao(Integer.parseInt(request.getParameter("id")));
                    expressao = request.getParameter("expressao");
                    agente = request.getParameter("agente");
                    agentePrincipal = request.getParameter("agentePrincipal");
                    int idAgPrinc = 0;
                    int idAgente = Integer.parseInt(agente);
                    int numAgentes = Integer.parseInt(request.getParameter("numAgentes"));
                    
                    if(numAgentes == 2)
                        idAgPrinc = Integer.parseInt(agentePrincipal);
                    
                    retorno = AplCadastrarCondicao.alterarDadosCondicaoPos(x,expressao,idAgente,idAgPrinc,numAgentes,m,c);
                    retorno = 1;
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Condicao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("condaltperc.jsp?status="+"Ja existe uma CONDICAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoCond.compareTo("CorAgente") == 0) {
                    CorAgente x = (CorAgente) AplCadastrarCondicao.obterCondicao(Integer.parseInt(request.getParameter("id")));
                    String cor = request.getParameter("cor");
                    
                    agente = request.getParameter("agente");
                    int idAgente = Integer.parseInt(agente);
                    
                    retorno = AplCadastrarCondicao.alterarDadosCondicaoCorAg(x,cor,idAgente,c);
                    retorno = 1;
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Condicao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("condaltcorag.jsp?status="+"Ja existe uma CONDICAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoCond.compareTo("CmpVar") == 0) {
                    CmpVariavel x = (CmpVariavel) AplCadastrarCondicao.obterCondicao(Integer.parseInt(request.getParameter("id")));
                    expressao = request.getParameter("expressao");
                    var1 = request.getParameter("var1");
                    var2 = request.getParameter("var2");
                    
                    retorno = AplCadastrarCondicao.alterarDadosCondicaoCmpVar(x,expressao,var1, var2,m,c);
                    retorno = 1;
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Condicao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("condaltpos.jsp?status="+"Ja existe uma CONDICAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoCond.compareTo("ValVar") == 0) {
                    ValorVariavel x = (ValorVariavel) AplCadastrarCondicao.obterCondicao(Integer.parseInt(request.getParameter("id")));
                    val = request.getParameter("expr");
                    var = request.getParameter("variavel");
                    
                    retorno = AplCadastrarCondicao.alterarDadosCondicaoValVar(x,val,var,m,c);
                    retorno = 1;
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Condicao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("condaltvalvar.jsp?status="+"Ja existe uma CONDICAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoCond.compareTo("Tamanho") == 0) {
                    int idCondicao = Integer.parseInt(request.getParameter("idCondicao"));
                    int idAgente = Integer.parseInt(request.getParameter("agente"));
                    String operador = request.getParameter("operador");
                    int valor = Integer.parseInt(request.getParameter("valor"));
                    Tamanho tam = (Tamanho) AplCadastrarCondicao.obterCondicao(idCondicao);
                    retorno = AplCadastrarCondicao.alterarDadosCondicaoTamanho(tam, idAgente, operador, valor);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Condicao salva com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("condtamanho.jsp?status="+"Ja existe uma CONDICAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                }
                
            } else if(acao.compareTo("consultar") == 0) {
                response.sendRedirect("incluircomp.jsp");
              return;
            }
        }
//*******************************************************************************************//
//******************CADASTRO DE ACOES. INCLUSAO E ALTERACAO**********************************//
//*******************************************************************************************//
        else if(metodo.compareTo("cadastroAcao") == 0){
            String tipoAcao = "";
            Composicao c = AplCadastrarComposicao.obterComposicao(m.getIdModelo().intValue(),composicao);
            session.setAttribute("composicao", c);
            
            acao = request.getParameter("acao");
            retorno = 1;
            
            if(acao.compareTo("incluir") == 0) {
                tipoAcao = request.getParameter("TipoAcao");
                
                if(tipoAcao.compareTo("Criar") == 0) {
                    String op = request.getParameter("op");
                    String a = request.getParameter("agente");
                    String pos = request.getParameter("posicao");
                    Agente ag = AplCadastrarAgente.obterAgente(Integer.parseInt(a));
                    
                    if(op.compareTo("cadastroVars") == 0)
                    {
                        String vars = request.getParameter("stringVar");
                        String valor;
                        int idAcao = Integer.parseInt(request.getParameter("idAcao"));
                        int r;
                        GerarNovoObjeto gn = new GerarNovoObjeto();
                        gn.setIdAcao(idAcao);
                        gn.setTipo("Criar");
                        gn.setComposicao(c);
                        gn.setAgente(ag);
                        gn.setCor(request.getParameter("cor"));
                        gn.setForma(request.getParameter("forma"));
                        gn.setTamanho(Integer.parseInt(request.getParameter("tamanho")));
                        gn.setPosicao(pos);
                        AplCadastrarAcao.alterarCorAcaoCriar(gn);
                        if(!vars.equals(""))
                        {
                            for(String var: vars.split(";"))
                            {
                                valor = request.getParameter(var);
                                r = AplCadastrarAcao.alterarAcaoCriarVar(idAcao, Integer.parseInt(var), valor);
                                if(r != 1)
                                    retorno = -1;
                            }
                        }
                        if(retorno == 1)
                        {
                            response.sendRedirect("incluircomp.jsp?status=Acao criada com sucesso!");
                        }
                    }
                    else
                    {
                        retorno = AplCadastrarAcao.incluirAcaoCriar(a,pos,c);
                        if(retorno != -1)
                            if(ag.getTipo().equals("Movel"))
                                response.sendRedirect("acao_criar_conf.jsp?idAcao="+retorno+"&idAgente="+a+"&posicao="+pos+"&op=criar&status="+"Acao criada com sucesso!");
                            else
                                response.sendRedirect("incluircomp.jsp?status=Acao criada com sucesso!");
                        else{
                            if(retorno == -1)
                                response.sendRedirect("acao_criar.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                        }
                    }
                    return;
                } else if(tipoAcao.compareTo("Destruir") == 0) {
                    String a = request.getParameter("agente");
                    
                    retorno = AplCadastrarAcao.incluirAcaoDestruir(a,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao criada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_destruir.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("Ocultar") == 0) {
                    String a = request.getParameter("agente");
                    
                    retorno = AplCadastrarAcao.incluirAcaoOcultar(a,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao criada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_ocultar_agente.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("Mostrar") == 0) {
                    String a = request.getParameter("agente");
                    
                    retorno = AplCadastrarAcao.incluirAcaoMostrar(a,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao criada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_mostrar_agente.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("AltCor") == 0) {
                    String a = request.getParameter("agente");
                    String cor = request.getParameter("cor");
                    
                    retorno = AplCadastrarAcao.incluirAcaoAltCor(a,cor,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao criada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_altcor.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("AltFor") == 0) {
                    String a = request.getParameter("agente");
                    String forma = request.getParameter("forma");
                    
                    retorno = AplCadastrarAcao.incluirAcaoAltFor(a,forma,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao criada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_altfor.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("AltMov") == 0) {
                    String a = request.getParameter("agenteMovel");
                    
                    retorno = AplCadastrarAcao.incluirAcaoAltMov(a,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao criada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_altmov.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("AltPos") == 0) {
                    String a = request.getParameter("agente");
                    Integer novoX = Integer.parseInt(request.getParameter("coordX"));
                    Integer novoY = Integer.parseInt(request.getParameter("coordY"));
                    
                    retorno = AplCadastrarAcao.incluirAcaoAltPos(a,novoX,novoY,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao criada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_altpos.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("IncValVar") == 0) {
                    String var = request.getParameter("var");
                    Variavel v = AplCadastrarVariavel.obterVariavel(m.getIdModelo().intValue(),var);
                    float inc = Float.parseFloat(request.getParameter("inc"));
                    
                    retorno = AplCadastrarAcao.incluirAcaoIncValVar(v,inc,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao criada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_incvalvar.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("RecValVar") == 0) {
                    String var = request.getParameter("variavel"); //idVar
                    Variavel v = AplCadastrarVariavel.obterVariavel (Integer.parseInt(var));
                    String exp = request.getParameter("expressao");
                    String tipo = request.getParameter("tipo");
                    String idAgente = request.getParameter("agente");
                    
                    retorno = AplCadastrarAcao.incluirAcaoRecValVar(v,exp,c,tipo,idAgente);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao criada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_recvalvar.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("PararMov") == 0) {
                    String a = request.getParameter("agenteMovel");
                    
                    retorno = AplCadastrarAcao.incluirAcaoPararMov(a,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao criada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_pararmov.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                }
                else if(tipoAcao.compareTo("AltTam") == 0) {
                    String agente = request.getParameter("agenteMovel");
                    int tamanho = Integer.parseInt(request.getParameter("tamanho"));
                    String tipoTam = request.getParameter("tipoTam");
                    
                    retorno = AplCadastrarAcao.incluirAcaoAlterarTamanho(agente, tamanho, tipoTam, c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao criada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_tamanho.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                }
                    
            } else if(acao.compareTo("alterar") == 0) {
                tipoAcao = request.getParameter("TipoAcao");
                
                if(tipoAcao.compareTo("Criar") == 0) {
                    GerarNovoObjeto a = (GerarNovoObjeto) AplCadastrarAcao.obterAcao(Integer.parseInt(request.getParameter("idAcao")));
                    String ag = request.getParameter("agente"); //id de agente
                    String op = request.getParameter("op");
                    String pos = request.getParameter("posicao");
                    
                    if(op.compareTo("cadastroVars") == 0)
                    {
                        String vars = request.getParameter("stringVar");
                        String valor;
                        int idAcao = Integer.parseInt(request.getParameter("idAcao"));
                        int r;
                        for(String var: vars.split(";"))
                        {
                            valor = request.getParameter(var);
                            r = AplCadastrarAcao.alterarAcaoCriarVar(idAcao, Integer.parseInt(var), valor);
                            if(r != 1)
                                retorno = -1;
                        }
                        if(retorno == 1)
                        {
                            response.sendRedirect("incluircomp.jsp?status=Acao atualizada com sucesso!");
                        }
                    }
                    else
                    {
                        Agente agn = AplCadastrarAgente.obterAgente(Integer.parseInt(ag));
                        retorno = AplCadastrarAcao.alterarDadosAcaoCriar(a,ag,pos,c);
                        if(retorno != -1)
                            if(agn.getTipo().equals("Movel"))
                                response.sendRedirect("acao_criar_conf.jsp?idAcao="+retorno+"&idAgente="+ag+"&posicao="+pos+"&op=alterar&status="+"Acao atualizada com sucesso!");
                            else
                                response.sendRedirect("incluircomp.jsp?status=Acao atualizada com sucesso!");
                        else{
                            if(retorno == -1)
                                response.sendRedirect("acao_criar.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                        }   
                    }
                    return;
                } else if(tipoAcao.compareTo("Destruir") == 0) {
                    Destruir a = (Destruir) AplCadastrarAcao.obterAcao(Integer.parseInt(request.getParameter("id")));
                    String ag = request.getParameter("agente");//id Agente
                    
                    retorno = AplCadastrarAcao.alterarDadosAcaoDestruir(a,ag,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_alt_destruir.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("Ocultar") == 0) {
                    OcultarAgente a = (OcultarAgente) AplCadastrarAcao.obterAcao(Integer.parseInt(request.getParameter("id")));
                    String ag = request.getParameter("agente");//id Agente
                    
                    retorno = AplCadastrarAcao.alterarDadosAcaoOcultar(a,ag,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_alt_ocultar.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("Mostrar") == 0) {
                    MostrarAgente a = (MostrarAgente) AplCadastrarAcao.obterAcao(Integer.parseInt(request.getParameter("id")));
                    String ag = request.getParameter("agente");//id Agente
                    
                    retorno = AplCadastrarAcao.alterarDadosAcaoMostrar(a,ag,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_alt_mostrar.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("AltCor") == 0) {
                    AlterarCor a = (AlterarCor) AplCadastrarAcao.obterAcao(Integer.parseInt(request.getParameter("id")));
                    String ag = request.getParameter("agente"); //id agente
                    String novacor = request.getParameter("cor");
                    
                    retorno = AplCadastrarAcao.alterarDadosAcaoAltCor(a,ag,novacor,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_alt_altcor.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("AltFor") == 0) {
                    AlterarForma a = (AlterarForma) AplCadastrarAcao.obterAcao(Integer.parseInt(request.getParameter("id")));
                    String ag = request.getParameter("agente"); //id agente
                    String novafor = request.getParameter("forma");
                    
                    retorno = AplCadastrarAcao.alterarDadosAcaoAltFor(a,ag,novafor,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_alt_altfor.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("AltMov") == 0) {
                    AlterarMovimento a = (AlterarMovimento) AplCadastrarAcao.obterAcao(Integer.parseInt(request.getParameter("id")));
                    String ag = request.getParameter("agente"); //idAgente
                    
                    retorno = AplCadastrarAcao.alterarDadosAcaoAltMov(a,ag,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_alt_altmov.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("PararMov") == 0) {
                    PararMovimento a = (PararMovimento) AplCadastrarAcao.obterAcao(Integer.parseInt(request.getParameter("id")));
                    String ag = request.getParameter("agente"); //idAgente
                    
                    retorno = AplCadastrarAcao.alterarDadosAcaoPararMov(a,ag,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_alt_pararmov.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("AltPos") == 0) {
                    AlterarPosicao a = (AlterarPosicao) AplCadastrarAcao.obterAcao(Integer.parseInt(request.getParameter("id")));
                    String ag = request.getParameter("agente"); //idAgente
                    Integer novox = Integer.valueOf(request.getParameter("novoX"));
                    Integer novoy = Integer.valueOf(request.getParameter("novoY"));
                    
                    retorno = AplCadastrarAcao.alterarDadosAcaoAltPos(a,ag,novox,novoy,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_alt_altpos.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("IncValVar") == 0) {
                    IncrementarVariavel a = (IncrementarVariavel) AplCadastrarAcao.obterAcao(Integer.parseInt(request.getParameter("id")));
                    Variavel var = AplCadastrarVariavel.obterVariavel(Integer.parseInt(request.getParameter("variavel")));
                    float inc = Float.parseFloat(request.getParameter("incremento"));
                    
                    retorno = AplCadastrarAcao.alterarDadosAcaoIncValVar(a,var,inc,c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_alt_incvalvar.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("RecValVar") == 0) {
                    RecuperarValorVariavel a = (RecuperarValorVariavel) AplCadastrarAcao.obterAcao(Integer.parseInt(request.getParameter("id")));
                    Variavel var = AplCadastrarVariavel.obterVariavel(Integer.parseInt(request.getParameter("variavel")));
                    String exp = request.getParameter("expressao");
                    String tipo = request.getParameter("tipo");
                    String idAgente = request.getParameter("agente");
                    
                    retorno = AplCadastrarAcao.alterarDadosAcaoRecValVar(a,var,exp,c, tipo, idAgente);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_alt_recvalvar.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                } else if(tipoAcao.compareTo("AltTam") == 0) {
                    AlterarTamanho ac = (AlterarTamanho) AplCadastrarAcao.obterAcao(Integer.parseInt(request.getParameter("id")));
                    int idAgente = Integer.parseInt(request.getParameter("agente")); //idAgente
                    int tamanho = Integer.parseInt(request.getParameter("tamanho"));
                    String tipoTam = request.getParameter("tipoTam");
                    
                    
                    retorno = AplCadastrarAcao.alterarDadosAcaoAlterarTamanho(ac, idAgente, tamanho, tipoTam, c);
                    if(retorno==1)
                        response.sendRedirect("incluircomp.jsp?status="+"Acao alterada com sucesso!");
                    else{
                        if(retorno == -1)
                            response.sendRedirect("acao_alt_tamanho.jsp?status="+"Ja existe uma ACAO com esse mesmo nome nesse modelo!");
                    }
                    return;
                }
            } else if(acao.compareTo("consultar") == 0) {
                response.sendRedirect("incluircomp.jsp");
                return;
            }
        }
//*******************************************************************************************//
//******************CADASTRO DE COMPOSICOES. INCLUSAO E ALTERACAO****************************//
//*******************************************************************************************//
        else if(metodo.compareTo("cadastroComposicao") == 0){
            acao = request.getParameter("acao");
            String nome = " ";
            String op = " ";
            String tipo = " ";
            if(acao.compareTo("incluir") == 0) {
                
                nome = request.getParameter("nome");
                composicao = request.getParameter("nome");
                op = request.getParameter("op");
                tipo = request.getParameter("tipo");
                retorno = AplCadastrarComposicao.incluirNovaComposicao(nome, op, tipo, m);
                if(retorno==1)
                    response.sendRedirect("incluircomp.jsp?status="+"Composicao criada com sucesso!&nome="+ nome +"&op="+ op);
                else{
                    if(retorno == -1)
                        response.sendRedirect("incluircompantes.jsp?status="+"Ja existe uma COMPOSICAO com esse mesmo nome nesse modelo!");
                }
                return;
            } else if(acao.compareTo("alterar") == 0) {
                
                nome = request.getParameter("nome");
                composicao = request.getParameter("nomeAnt");
                Composicao c = AplCadastrarComposicao.obterComposicao(m.getIdModelo().intValue(),composicao);
                op = request.getParameter("op");
                tipo = request.getParameter("tipo");
                retorno = AplCadastrarComposicao.alterarDadosComposicao(c, nome, op, tipo, m);
                
                
                composicao = nome;
                c = AplCadastrarComposicao.obterComposicao(c.getIdComposicao().intValue());
                session.setAttribute("composicao", c);
                
                if(retorno==1)
                    response.sendRedirect("incluircomp.jsp?status="+"Composicao alterada com sucesso!&nome="+ nome +"&op="+ op);
                else{
                    if(retorno == -1)
                        response.sendRedirect("alterarcomp.jsp?status="+"Ja existe uma COMPOSICAO com esse mesmo nome nesse modelo!");
                }
                return;
            }
        }
    }
    
}