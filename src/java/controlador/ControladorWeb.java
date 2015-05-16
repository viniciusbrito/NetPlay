/*
 * ControladorWeb.java
 *
 * Created on 1 de Maio de 2006, 10:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 * Editado em 28 de Outubro de 2014, 20:00
 */

package controlador;

import cdp.*;
import cgt.*;
import java.sql.Array;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitario.*;
import java.io.*;
import java.lang.Integer;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Alexandre Andrade
 * @editado Vinicius Fernandes
 */
public class ControladorWeb extends HttpServlet{
    
    Usuario usuario;
    Modelo modelo;
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        
        // Logando no sistema passando o login como parametro (AProSiMa)    
        if(metodo.compareTo("logar") == 0) {
            
            String loginDigitado = request.getParameter("login");
            
            System.out.println("VEIUO2\n\n\n");
            usuario = AplControleUsuario.efetuarLogin(loginDigitado);
            session.setAttribute("usuario", usuario);
            response.sendRedirect("menu.jsp");            
        } 
    
        if(metodo.compareTo("sair") == 0) {
            session.removeAttribute("usuario");
            response.sendRedirect("index.jsp");
            return;
        }
    
        if(metodo.compareTo("excluirUsuario") == 0) {  
            AplCadastrarUsuario.excluirUsuario(usuario);
            response.sendRedirect("index.jsp?status="+"Sua conta foi excluida com sucesso!");
            return;
        }      
        
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        //System.out.println("Usuario - " + usuario.getLogin() + "\n\n");

        
//*******************************************************************************************//           
//******************PESQUISA DE MODELOS PARA ALTERACAO, CONSULTA OU EXCLUSAO*****************//
//*******************************************************************************************//   
        if(metodo.compareTo("pesquisaModelo")==0){
           
            acao = request.getParameter("acao");
            String checked = request.getParameter("checked");
            List modelos = null;
            Modelo m = (Modelo)session.getAttribute("modelo");
            
            if(acao.compareTo("alteracao")==0){
                
                modelos = AplCadastrarModelo.todosModelos(usuario.getIdUsuario().intValue());                
                utilitario.Pagina.montaPaginaPesquisaModelo(response,"SELECIONE O MODELO A SER ALTERADO","Alterar",modelos, checked, usuario.getLogin());
                return;
            }   
            
            if(acao.compareTo("consultamodelos")==0){
                
                modelos = AplCadastrarModelo.todosModelos(usuario.getIdUsuario().intValue());                
                utilitario.Pagina.montaPaginaPesquisaModelo(response,"SELECIONE O MODELO A SER CONSULTADO","Consultar",modelos, checked, usuario.getLogin());
                return;
            }
           
            if(acao.compareTo("exclusao")==0){
                
                modelos = AplCadastrarModelo.meusModelos(usuario.getIdUsuario().intValue());                
                utilitario.Pagina.montaPaginaPesquisaModelo(response,"SELECIONE O MODELO A SER EXCLUIDO","Excluir",modelos, checked, usuario.getLogin());
                return;
            }
        }      
        
//********************************************************************************************//           
//******************PESQUISA DE TEXTOS PARA ALTERACAO, CONSULTA OU EXCLUSAO*******************//
//********************************************************************************************//  
        if(metodo.compareTo("pesquisaTexto")==0){
            
            acao = request.getParameter("acao");
            List textos = null;
            modelo = (Modelo)session.getAttribute("modelo");
            textos = AplCadastrarMonitorTexto.textosDoModelo(modelo.getIdModelo().intValue());
            
            if(acao.compareTo("consulta")==0){
                            
                if(textos.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui textos."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaTexto(response,"SELECIONE O TEXTO A SER CONSULTADO","Consultar",textos);
                return;
            }          
            
            if(acao.compareTo("alteracao")==0){
            
                if(textos.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui textos."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaTexto(response,"SELECIONE O TEXTO A SER ALTERADO","Alterar",textos);
                return;
            }          
            
            if(acao.compareTo("exclusao")==0){
            
                if(textos.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui textos."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaTexto(response,"SELECIONE O TEXTO A SER EXCLUIDO","Excluir",textos);
                return;
            }                               
        }
            
//********************************************************************************************//           
//******************PESQUISA DE MONITORES PARA ALTERACAO, CONSULTA OU EXCLUSAO*******************//
//********************************************************************************************//              
        if(metodo.compareTo("pesquisaMonitor")==0){
            
            acao = request.getParameter("acao");
            List monitores = null;
            modelo = (Modelo)session.getAttribute("modelo");
            monitores = AplCadastrarMonitorTexto.monitoresDoModelo(modelo.getIdModelo().intValue());
            
            if(acao.compareTo("consulta")==0){
            
                if(monitores.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui monitores."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaMonitor(response,"SELECIONE O MONITOR A SER CONSULTADO","Consultar",monitores);
                return;
            }               
            
            if(acao.compareTo("alteracao")==0){
            
                if(monitores.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui monitores."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaMonitor(response,"SELECIONE O MONITOR A SER ALTERADO","Alterar",monitores);
                return;
            }               
            
            if(acao.compareTo("exclusao")==0){
            
                if(monitores.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui monitores."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaMonitor(response,"SELECIONE O MONITOR A SER EXCLUIDO","Excluir",monitores);
                return;
            }               
        }
     
        
//********************************************************************************************//           
//******************PESQUISA DE AGENTES PARA ALTERACAO, CONSULTA OU EXCLUSAO******************//
//********************************************************************************************//              
        if(metodo.compareTo("pesquisaAgente")==0){
        
            acao = request.getParameter("acao");
            List agentes = null;
            modelo = (Modelo)session.getAttribute("modelo");
            agentes = AplCadastrarAgente.agentesDoModelo(modelo.getIdModelo().intValue());
            
            if(acao.compareTo("consulta")==0){
                            
                if(agentes.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui agentes."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaAgente(response,"SELECIONE O AGENTE A SER CONSULTADO","Consultar",agentes);
                return;
            }               
            
            if(acao.compareTo("alteracao")==0){
            
                if(agentes.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui agentes."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaAgente(response,"SELECIONE O AGENTE A SER ALTERADO","Alterar",agentes);
                return;
            }               
            
            if(acao.compareTo("exclusao")==0){
            
                if(agentes.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui agentes."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaAgente(response,"SELECIONE O AGENTE A SER EXCLUIDO","Excluir",agentes);
                return;
            }               
        }
                
        
//********************************************************************************************//           
//******************PESQUISA DE VARIAVEIS PARA ALTERACAO, CONSULTA OU EXCLUSAO****************//
//********************************************************************************************//             
        if(metodo.compareTo("pesquisaVariavel")==0){
        
            acao = request.getParameter("acao");
            List variaveis = null;
            modelo = (Modelo)session.getAttribute("modelo");
            variaveis = AplCadastrarVariavel.variaveisDoModelo(modelo.getIdModelo().intValue());
            
            if(acao.compareTo("consulta")==0){
                            
                if(variaveis.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui variaveis."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaVariavel(response,"SELECIONE A VARIAVEL A SER CONSULTADA","Consultar",variaveis);
                return;
            }               
            
            if(acao.compareTo("alteracao")==0){
            
                if(variaveis.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui variaveis."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaVariavel(response,"SELECIONE A VARIAVEL A SER ALTERADA","Alterar",variaveis);
                return;
            }               
            
            if(acao.compareTo("exclusao")==0){
            
                if(variaveis.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui variaveis."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaVariavel(response,"SELECIONE A VARIAVEL A SER EXCLUIDA","Excluir",variaveis);
                return;
            }               
        }
        
        
//********************************************************************************************//           
//******************PESQUISA DE PLOTS PARA ALTERACAO, CONSULTA OU EXCLUSAO********************//
//********************************************************************************************//              
        if(metodo.compareTo("pesquisaPlot")==0){
        
            acao = request.getParameter("acao");
            
            List plots = null;
            modelo = (Modelo)session.getAttribute("modelo");
            plots = AplCadastrarPlot.plotsDoModelo(modelo.getIdModelo().intValue());
            
            if(acao.compareTo("consulta")==0){
                            
                if(plots.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui graficos."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaPlot(response,"SELECIONE O GRAFICO A SER CONSULTADO","Consultar",plots);
                return;
            }               
            
            if(acao.compareTo("alteracao")==0){
            
                if(plots.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui graficos."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaPlot(response,"SELECIONE O GRAFICO A SER ALTERADO","Alterar",plots);
                return;
            }               
            
            if(acao.compareTo("exclusao")==0){
            
                if(plots.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Esse modelo nao possui graficos."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaPlot(response,"SELECIONE O GRAFICO A SER EXCLUIDO","Excluir",plots);
                return;
            }               
        }

        
//*******************************************************************************************//        
//********************CADASTRO DE AGENTE. CONSULTA E EXCLUSAO********************************//
//*******************************************************************************************//        
        if(metodo.compareTo("cadastroAgente")==0){
        
            acao = request.getParameter("acao");
                   
            //declarando as variaveis de Agente
            String nome = "";
            Agente agente;

            nome = request.getParameter("nome");
             modelo = (Modelo)session.getAttribute("modelo");
            agente = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(),nome);       
            
            if((acao.compareTo("Consultar")==0) || (acao.compareTo("Excluir")==0)){
                if(agente.getTipo().compareTo("Estacionario Borda")==0){
                    AgenteEstBorda agenteEstBorda = (AgenteEstBorda)agente;
                    response.sendRedirect("consultar_excluirestacionarioborda.jsp?nome="+ nome +"&tamanho="+ agenteEstBorda.getTamanho() +"&cor="+ agenteEstBorda.getCor() +"&acao="+acao);    
                    return;        
                }
                else{
                    if(agente.getTipo().compareTo("Estacionario Aleatorio")==0){                
                        AgenteEstAleatorio agenteEstAleatorio = (AgenteEstAleatorio)agente;
                        response.sendRedirect("consultar_excluirestacionarioaleatorio.jsp?nome="+ nome +"&grauDispersao="+ agenteEstAleatorio.getPorcentagem() +"&cor="+ agenteEstAleatorio.getCor() +"&idAgente="+ agenteEstAleatorio.getIdAgente() +"&tipoPosicao="+ agenteEstAleatorio.getTipoPosicao() +"&acao="+acao);    
                        return;        
                    }
                    else{
                        AgenteMovel agenteMovel = (AgenteMovel)agente;
                        
                        String quantidadeInicial;
                        if (agenteMovel.getTipoQtdInicial().equals("Var"))
                            quantidadeInicial = pesquisaNomeVariavelAgente(agenteMovel, "consultar");
                        else
                            quantidadeInicial = agenteMovel.getQuantidadeInicial().toString();
                        
                        if(agenteMovel.getTipoMovimento().compareTo("Passear")==0)
                            response.sendRedirect("consultar_excluirmovel.jsp?nome="+ nome +"&tamanho="+ agenteMovel.getTamanho() +"&cor="+ agenteMovel.getCor() +"&acao="+acao +"&forma="+agenteMovel.getForma() +"&quantidadeInicial="+ quantidadeInicial + "&tipoQtdInicial=" + agenteMovel.getTipoQtdInicial() +"&tipoMovimento="+agenteMovel.getTipoMovimento() +"&anguloMinimo="+agenteMovel.getAngulo1() +"&anguloMaximo="+agenteMovel.getAngulo2()+"&passo="+agenteMovel.getPasso()+"&tipoPosicao="+agenteMovel.getTipoPosicao()+"&idAgente="+agenteMovel.getIdAgente());    
                        else
                            response.sendRedirect("consultar_excluirmovel.jsp?nome="+ nome +"&tamanho="+ agenteMovel.getTamanho() +"&cor="+ agenteMovel.getCor() +"&acao="+acao +"&forma="+agenteMovel.getForma() +"&quantidadeInicial="+ quantidadeInicial + "&tipoQtdInicial=" + agenteMovel.getTipoQtdInicial() +"&tipoMovimento="+agenteMovel.getTipoMovimento() +"&anguloReflexao="+agenteMovel.getAngulo1() +"&passo="+agenteMovel.getPasso()+"&tipoPosicao="+agenteMovel.getTipoPosicao()+"&idAgente="+agenteMovel.getIdAgente());    
                        return;    
                    }
                }
            }
                        
            if(acao.compareTo("Alterar")==0){
                if(agente.getTipo().compareTo("Estacionario Borda")==0){
                    AgenteEstBorda agenteEstBorda = (AgenteEstBorda)agente;
                    response.sendRedirect("alterarestacionarioborda.jsp?nome="+ nome +"&tamanho="+ agenteEstBorda.getTamanho() +"&cor="+ agenteEstBorda.getCor());
                    return;       
                }
               if(agente.getTipo().compareTo("Estacionario Aleatorio")==0){
                    AgenteEstAleatorio agenteEstAleatorio = (AgenteEstAleatorio)agente;
                    response.sendRedirect("alterarestacionarioaleatorio.jsp?nome="+ nome +"&grauDispersao="+ agenteEstAleatorio.getPorcentagem() +"&cor="+ agenteEstAleatorio.getCor() +"&pos="+ agenteEstAleatorio.getTipoPosicao() +"&idAgente="+ agenteEstAleatorio.getIdAgente());
                    return;       
                }
                if(agente.getTipo().compareTo("Movel")==0)
                {
                    AgenteMovel agenteMovel = (AgenteMovel)agente;
                    
                    String quantidadeInicial;
                    if (agenteMovel.getTipoQtdInicial().equals("Var"))
                        quantidadeInicial = pesquisaNomeVariavelAgente(agenteMovel, "alterar");
                    else
                        quantidadeInicial = agenteMovel.getQuantidadeInicial().toString();
                    
                    //Para controlar posicoes especificas
                    String totalPosicoes = null;
                    if (agente.getTipoPosicao().equals("posicaoInicial"))
                        totalPosicoes = quantidadeInicial;
                                        
                    if(agenteMovel.getTipoMovimento().compareTo("Passear")==0)
                        response.sendRedirect("alterar_movel.jsp?inicio=1&nome="+ nome +"&tamanho="+ agenteMovel.getTamanho() +"&cor="+ agenteMovel.getCor() +"&acao="+acao +"&forma="+agenteMovel.getForma() +"&quantidadeInicial="+ quantidadeInicial + "&tipoQtdInicial=" + agenteMovel.getTipoQtdInicial() +"&tipoMovimento="+agenteMovel.getTipoMovimento() +"&anguloMinimo="+agenteMovel.getAngulo1() +"&anguloMaximo="+agenteMovel.getAngulo2()+"&passo="+agenteMovel.getPasso()+"&tipoPosicao="+agenteMovel.getTipoPosicao()+"&idAgente="+agenteMovel.getIdAgente() + "&totalPosicoes=" + totalPosicoes);    
                    else if(agenteMovel.getTipoMovimento().compareTo("Refletir")==0)
                        response.sendRedirect("alterar_movel.jsp?inicio=1&nome="+ nome +"&tamanho="+ agenteMovel.getTamanho() +"&cor="+ agenteMovel.getCor() +"&acao="+acao +"&forma="+agenteMovel.getForma() +"&quantidadeInicial="+ quantidadeInicial + "&tipoQtdInicial=" + agenteMovel.getTipoQtdInicial() +"&tipoMovimento="+agenteMovel.getTipoMovimento() +"&anguloReflexao="+agenteMovel.getAngulo1() +"&passo="+agenteMovel.getPasso()+"&tipoPosicao="+agenteMovel.getTipoPosicao()+"&idAgente="+agenteMovel.getIdAgente() + "&totalPosicoes=" + totalPosicoes);    
                    else if(agenteMovel.getTipoMovimento().compareTo("Parado")==0)
                        response.sendRedirect("alterar_movel.jsp?inicio=1&nome="+ nome +"&tamanho="+ agenteMovel.getTamanho() +"&cor="+ agenteMovel.getCor() +"&acao="+acao +"&forma="+agenteMovel.getForma() +"&quantidadeInicial="+ quantidadeInicial + "&tipoQtdInicial=" + agenteMovel.getTipoQtdInicial() +"&tipoMovimento="+agenteMovel.getTipoMovimento() +"&tipoPosicao="+agenteMovel.getTipoPosicao()+"&idAgente="+agenteMovel.getIdAgente() + "&totalPosicoes=" + totalPosicoes);    
                    return;         
                }
            }

            if(acao.compareTo("excluirAgente")==0){
                                
                retorno = AplCadastrarAgente.excluirAgente(agente);      
                if(retorno==1)
                    response.sendRedirect("incluirmodelo.jsp?status="+"Agente excluido com sucesso.");
                else
                    response.sendRedirect("incluirmodelo.jsp?status="+"Nao foi possivel excluir o agente "+nome+" pois ele possui variavel(is) associada(s) a ele ou esta presente em alguma composicao.");
                return;
            }
        }
        
        
        
//*******************************************************************************************//        
//********************CADASTRO DE VARIAVEL. CONSULTA E EXCLUSAO******************************//
//*******************************************************************************************//        
        
        if(metodo.compareTo("cadastroVariavel")==0){
            
            acao = request.getParameter("acao");
            
            //declarando as variaveis de Variavel
            String nome= "";
            String nomeAgente= "";
            String status= "";
            Variavel variavel;
            
            if((acao.compareTo("Consultar")==0) || (acao.compareTo("Excluir")==0)){          
              
                nome = request.getParameter("nome");
                modelo = (Modelo)session.getAttribute("modelo");
                variavel = AplCadastrarVariavel.obterVariavel(modelo.getIdModelo().intValue(),nome);
                nomeAgente  = AplCadastrarVariavel.obterNomeAgenteVariavel(modelo.getIdModelo().intValue(),nome);
                
                if(variavel.getTipo().compareTo("Logica")==0){
                    VariavelLogica variavelLogica = (VariavelLogica)variavel;
                    response.sendRedirect("consultar_excluirvariavellogica.jsp?nome="+ nome +"&valorLogico="+ variavelLogica.getValorLogico() +"&nomeAgente="+ nomeAgente +"&acao="+acao);    
                    return;        
                }
                else{
                    if(variavel.getTipo().compareTo("Valor")==0){                
                        VariavelValor variavelValor = (VariavelValor)variavel;
                        retorno = AplCadastrarVariavel.estahGrafico(variavelValor);
                        if((retorno == -1)&&(acao.compareTo("Excluir")==0))
                            status = "Essa variavel esta contida em um ou mais graficos do modelo. Se exclui-la ela sera automaticamente retirada de seus respectivo(s) grafico(s). Se ainda quiser exclui-la clique em \" Excluir \"";                        
                        response.sendRedirect("consultar_excluirvariavelvalor.jsp?nome="+ nome +"&expr="+ variavelValor.getValorHTML() +"&agente="+ java.net.URLEncoder.encode(nomeAgente, "utf-8") +"&acao="+acao +"&status="+status);    
                        return;        
                    }
                    else{
                        VariavelSlider variavelSlider = (VariavelSlider)variavel;
                        response.sendRedirect("consultar_excluirvariavelslider.jsp?nome="+ nome +"&valorInicial="+ variavelSlider.getValorInicial() + "&valorMinimo="+ variavelSlider.getValorMinimo() + "&valorMaximo="+ variavelSlider.getValorMaximo() + "&incremento="+ variavelSlider.getIncremento()+"&nomeAgente="+ nomeAgente + "&acao="+acao);    
                        return;                                
                    }
                }            
            }
          
            if(acao.compareTo("Alterar")==0){
                nome = request.getParameter("nome");
                modelo = (Modelo)session.getAttribute("modelo");
                variavel = AplCadastrarVariavel.obterVariavel(modelo.getIdModelo().intValue(),nome);
                nomeAgente  = AplCadastrarVariavel.obterNomeAgenteVariavel(modelo.getIdModelo().intValue(),nome);
                
                if(variavel.getTipo().compareTo("Logica")==0){
                    VariavelLogica variavelLogica = (VariavelLogica)variavel;
                    response.sendRedirect("alterar_variavellogica.jsp?nome="+ nome +"&valorLogico="+ variavelLogica.getValorLogico() +"&nomeAgente="+ nomeAgente);
                    return;       
                }
                
                if(variavel.getTipo().compareTo("Valor")==0){
                    VariavelValor variavelValor = (VariavelValor)variavel;
                    String agente = "";
                    if(variavelValor.getAgente() != null)
                        agente = variavelValor.getAgente().getIdAgente().toString();
                    response.sendRedirect("alterar_variavelvalor.jsp?nome="+ nome +"&expr="+ variavelValor.getValorHTML() +"&agente="+ agente);    
                    return;
                }
                
                if(variavel.getTipo().compareTo("Slider")==0){
                    VariavelSlider variavelSlider = (VariavelSlider)variavel;
                    response.sendRedirect("alterar_variavelslider.jsp?nome="+ nome +"&valorInicial="+ variavelSlider.getValorInicial() + "&valorMinimo="+ variavelSlider.getValorMinimo() + "&valorMaximo="+ variavelSlider.getValorMaximo() + "&incremento="+ variavelSlider.getIncremento()+"&nomeAgente="+ nomeAgente);    
                    return;                                
                }
            }
            
            if(acao.compareTo("excluirVariavel")==0){
                nome = request.getParameter("nome");
                modelo = (Modelo)session.getAttribute("modelo");
                variavel = AplCadastrarVariavel.obterVariavel(modelo.getIdModelo().intValue(),nome);
                nomeAgente  = AplCadastrarVariavel.obterNomeAgenteVariavel(modelo.getIdModelo().intValue(),nome);
                
                if(!AplCadastrarComposicao.variavelPresenteComposicoes(variavel))
                {
                    AplCadastrarVariavel.excluirVariavel(variavel);
                    response.sendRedirect("incluirmodelo.jsp?status="+"Variavel excluida com sucesso.");    
                }
                else
                    response.sendRedirect("incluirmodelo.jsp?status="+"Variavel nao pode ser excluida porque esta presente em alguma composicao.");    
                return;
            }
        }         
       
        
//*******************************************************************************************//        
//******************CADASTRO DE TEXTO. CONSULTA, EXCLUSAO, ALTERACAO*************************//
//*******************************************************************************************//        
        if(metodo.compareTo("cadastroTexto")==0){
            
            acao = request.getParameter("acao");
                        
            //declarando as variaveis de texto
            String nome = "";
            Texto texto;

            nome = request.getParameter("nome");
            texto = AplCadastrarMonitorTexto.obterTexto(modelo.getIdModelo().intValue(),nome);
                
            if(acao.compareTo("Alterar")==0){
                response.sendRedirect("alterartexto.jsp?nome="+ nome +"&texto="+texto.getTextoHtml());    
                return;       
            }
            
            if((acao.compareTo("Consultar")==0) || (acao.compareTo("Excluir")==0)){
                response.sendRedirect("consultar_excluirtexto.jsp?nome="+ nome +"&texto="+texto.getTextoHtml() +"&acao="+acao);    
                return;       
            }
            
            if(acao.compareTo("excluirTexto")==0){
                AplCadastrarMonitorTexto.excluirTexto(texto);
                response.sendRedirect("incluirmodelo.jsp?status="+"Texto excluido com sucesso.");    
                return;
            }
        }
        
        
//*******************************************************************************************//        
//*****************CADASTRO DE MONITOR. CONSULTA, EXCLUSAO E ALTERACAO***********************//
//*******************************************************************************************//        
        if(metodo.compareTo("cadastroMonitor")==0){
            
            acao = request.getParameter("acao");
            
            //declarando as variaveis de monitor
            String nome = "";
            Monitor monitor;
            
            nome = request.getParameter("nome");
             modelo = (Modelo)session.getAttribute("modelo");
            monitor = AplCadastrarMonitorTexto.obterMonitor(modelo.getIdModelo().intValue(),nome);  
            
            if(acao.compareTo("Alterar")==0){
                response.sendRedirect("alterarmonitor.jsp?nome="+ nome +"&expressao="+monitor.getExpressaoHtml()+"&casasDecimais="+ monitor.getCasasDecimais());    
                return;       
            }
                                   
            if((acao.compareTo("Consultar")==0) || (acao.compareTo("Excluir")==0)){
                response.sendRedirect("consultar_excluirmonitor.jsp?nome="+ nome +"&expressao="+monitor.getExpressaoHtml()+"&numeroCasasDecimais="+ monitor.getCasasDecimais() +"&acao="+acao);    
                return;       
            }
            
            if(acao.compareTo("excluirMonitor")==0){
                AplCadastrarMonitorTexto.excluirMonitor(monitor);
                response.sendRedirect("incluirmodelo.jsp?status="+"Monitor excluido com sucesso.");    
                return;
            }
        }
        
        
//*******************************************************************************************//        
//******************CADASTRO DE PLOTS. CONSULTA, EXCLUSAO, ALTERACAO*************************//
//*******************************************************************************************//        
        if(metodo.compareTo("cadastroPlot")==0){
            
            acao = request.getParameter("acao");
                        
            //declarando as variaveis do plot
            String nome = "";
            Plot plot;

            nome = request.getParameter("nome");
             modelo = (Modelo)session.getAttribute("modelo");
            plot = AplCadastrarPlot.obterPlot(modelo.getIdModelo().intValue(),nome);
                
            if(acao.compareTo("Alterar")==0){
                response.sendRedirect("alterargrafico.jsp?nome="+ nome +"&tipoGrafico="+plot.getTipo()+"&nomeEixoX="+plot.getNomeEixoX()+"&nomeEixoY="+plot.getNomeEixoY()+"&xMinimo="+plot.getXMinimo()+"&xMaximo="+plot.getXMaximo()+"&yMinimo="+plot.getYMinimo()+"&yMaximo="+plot.getYMaximo()+"&id="+plot.getIdPlot());    
                return;       
            }
            
            if((acao.compareTo("Consultar")==0) || (acao.compareTo("Excluir")==0)){
                response.sendRedirect("consultar_excluirgrafico.jsp?nome="+ nome +"&tipoGrafico="+plot.getTipo()+"&nomeEixoX="+plot.getNomeEixoX()+"&nomeEixoY="+plot.getNomeEixoY()+"&xMinimo="+plot.getXMinimo()+"&xMaximo="+plot.getXMaximo()+"&yMinimo="+plot.getYMinimo()+"&yMaximo="+plot.getYMaximo()+"&id="+plot.getIdPlot()+"&acao="+acao);    
                return;       
            }
            
            if(acao.compareTo("excluirPlot")==0){
                AplCadastrarPlot.excluirPlot(plot);
                response.sendRedirect("incluirmodelo.jsp?status="+"Grafico excluido com sucesso.");    
                return;
            }
        }
        
        
//*******************************************************************************************//        
//*****************CADASTRO DE MODELO. CONSULTA, EXCLUSAO E ALTERACAO************************//
//*******************************************************************************************//   
        if(metodo.compareTo("cadastroModelo")==0){
        
            String nome = "";
            acao = request.getParameter("acao");
            nome = request.getParameter("nome");
            
            modelo = AplCadastrarModelo.obterModelo(nome);
            
            session.setAttribute("modelo", modelo);
            
            if(acao.compareTo("Alterar")==0){
               response.sendRedirect("menu2_alteracaomodelo.jsp?status="+ java.net.URLEncoder.encode("Se for criar uma nova vers&atilde;o do modelo, favor colocar novo <br>nome e nova descri&ccedil;&atilde;o (opcional))", "utf-8"));
               return;
            }       
            
            if(acao.compareTo("Consultar")==0){
                response.sendRedirect("menu2_consultamodelo.jsp");    
                return;       
            }
            
            if(acao.compareTo("Excluir")==0){
                response.sendRedirect("menu2_exclusaomodelo.jsp?status="+"Na exclusao de um modelo todos os elementos do modelo tambem serao excluidos");    
                return;       
            }
            
            if(acao.compareTo("excluirModelo")==0){
                retorno = AplCadastrarModelo.excluirModelo(modelo);
                if(retorno==1)
                    response.sendRedirect("menu.jsp?status="+"Modelo Excluido com sucesso.");    
                else
                    response.sendRedirect("menu.jsp?status="+"O modelo \""+ modelo.getNome() +"\" nao pode ser excluido pois possui modelos filhos.");    
                return;
            }
        }
      
        
//********************************************************************************************//                
//*******************************CONTROLE DE VERSOES DO MODELO********************************//        
//********************************************************************************************//                
        if(metodo.compareTo("consultaVersoesModelo")==0){
            
            Modelo modeloAux= null;
            Modelo pai = null;
            String nomeModelo="";
            String teste="";
            String nome="";

            nomeModelo = request.getParameter("nome");            
            modeloAux = AplCadastrarModelo.obterModelo(nomeModelo);
            nome = AplCadastrarModelo.obterNomeModeloPai(nomeModelo);
            utilitario.Pagina.montaPaginaVersoesModelo(response,modeloAux,nome,teste);            
            return;
        }               
    }

        
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        HttpSession session = request.getSession(true);
        modelo = (Modelo)session.getAttribute("modelo");        
        
        //Declarando variaveis auxiliares
        int retorno;  
        
        //Declarando variaveis de controle
        String metodo="";
        String acao="";
                      
        //Pegando o metodo
        metodo = request.getParameter("metodo");        
        
        // Logando no sistema     
        if(metodo.compareTo("logar") == 0) {
                
            System.out.println("VEIO\n\n\n");
            
            String loginDigitado;
            String senhaDigitada;
                      
            loginDigitado = request.getParameter("login");
            senhaDigitada = request.getParameter("senha");
            
            usuario = AplControleUsuario.efetuarLogin(loginDigitado,senhaDigitada);
                      
            if( usuario == null){
                //Login e senha invalidos
                response.sendRedirect("login.jsp?status="+"Erro ao realizar login, verifique o seu login e senha!");
            }
            else
            {
                //Login e senha validos
                session.setAttribute("usuario", usuario);
                response.sendRedirect("menu.jsp");
            }    
            return;
        } 
        
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        //System.out.println("Usuario - " + usuario.getLogin() + "\n\n");
        
//*******************************************************************************************//           
//******************CADASTRO DE USUARIO. INCLUSAO E ALTERACAO********************************//
//*******************************************************************************************//
        if(metodo.compareTo("cadastroUsuario") == 0) {
            
            acao = request.getParameter("acao"); 
            
            //Declarando variaveis de usuario
            String nome="";
            String sobrenome="";
            String sexo="";
            String login="";
            String senha="";
            String confirmarSenha="";
            String email="";
            String areaInteresse="";
            String instituicao="";
            String dia_nascimento="";
            String mes_nascimento="";
            String ano_nascimento="";
            String dtNascimento="";
            Boolean erro = false;
            String msg = "";
        
            if(acao.compareTo("incluir") == 0) {
                
                nome = request.getParameter("nome");
                sobrenome = request.getParameter("sobrenome").trim();
                email = request.getParameter("email").trim();
                login = request.getParameter("login").trim();
                senha = request.getParameter("senha").trim();
                confirmarSenha = request.getParameter("confirmarsenha").trim();
                instituicao = request.getParameter("instituicao").trim();
                areaInteresse = request.getParameter("areaInteresse").trim();
                sexo = request.getParameter("sexo").trim();
                dtNascimento = request.getParameter("dataNasc").trim();
                
                if(nome.isEmpty() || nome.length() < 3) {
                    erro = true;
                    msg = "Erro no campo Nome!";
                }
                if(sobrenome.isEmpty() || sobrenome.length() < 3) {
                    erro = true;
                    msg = "Erro no campo Sobrenome!";
                }
                if(email.isEmpty()) {
                    erro = true;
                    msg = "Erro no campo E-mail!";
                }
                if(login.isEmpty() || login.length() < 3) {
                    erro = true;
                    msg = "Erro no campo Login!";
                }
                if(senha.isEmpty() || confirmarSenha.isEmpty()){
                    erro = true;
                    msg = "Erro no campo Senha!";
                }
                if(!senha.equals(confirmarSenha)){
                    erro = true;
                    msg = "As senhas não conferem!";
                }              
                if(instituicao.isEmpty()) {
                    erro = true;
                    msg = "Erro no campo Instituição!";
                }
                if(areaInteresse.isEmpty()) {
                    erro = true;
                    msg = "Erro no campo Area de Interesse!";
                }
                if(sexo.isEmpty()) {
                    erro = true;
                    msg = "Erro no campo Sexo!";
                }
                if(dtNascimento.isEmpty()) {
                    erro = true;
                    msg = "Erro no campo Data de Nascimento!";
                }
                
                if(erro == false)
                {
                    retorno = AplCadastrarUsuario.incluirNovoUsuario(nome,sobrenome,email,login,senha,instituicao,areaInteresse,dtNascimento,sexo);

                    if(retorno == 1)
                    {
                        response.sendRedirect("login.jsp?status="+"Cadastro efetuado com sucesso!&msg=success");
                    }
                    else
                    {
                        response.sendRedirect("cadastro.jsp?status="+"Há erros no formulário!"+
                            "&nome=" + java.net.URLEncoder.encode(nome, "ISO-8859-1") + 
                            "&sobrenome=" + java.net.URLEncoder.encode(sobrenome, "ISO-8859-1") +
                            "&email=" + java.net.URLEncoder.encode(email, "ISO-8859-1") + 
                            "&login=" + java.net.URLEncoder.encode(login, "ISO-8859-1") +
                            "&instituicao=" + java.net.URLEncoder.encode(instituicao, "ISO-8859-1") + 
                            "&areaInteresse=" + java.net.URLEncoder.encode(areaInteresse, "ISO-8859-1") +
                            "&sexo=" + sexo + 
                            "&dataNasc=" + java.net.URLEncoder.encode(dtNascimento, "ISO-8859-1"));
                    }  
                }
                else
                {
                    response.sendRedirect("cadastro.jsp?status="+msg+
                        "&nome=" + java.net.URLEncoder.encode(nome, "ISO-8859-1") + 
                        "&sobrenome=" + java.net.URLEncoder.encode(sobrenome, "ISO-8859-1") +
                        "&email=" + java.net.URLEncoder.encode(email, "ISO-8859-1") + 
                        "&login=" + java.net.URLEncoder.encode(login, "ISO-8859-1") +
                        "&instituicao=" + java.net.URLEncoder.encode(instituicao, "ISO-8859-1") + 
                        "&areaInteresse=" + java.net.URLEncoder.encode(areaInteresse, "ISO-8859-1") +
                        "&sexo=" + sexo + 
                        "&dataNasc=" + java.net.URLEncoder.encode(dtNascimento, "ISO-8859-1"));
                }
                return; 
            }
            
            // Alterando dados do usuario
            if(acao.compareTo("alterar") == 0) {     
                   
                nome = request.getParameter("nome");
                sobrenome = request.getParameter("sobrenome");
                email = request.getParameter("email");
                login = request.getParameter("login");
                senha = request.getParameter("senha");
                instituicao = request.getParameter("instituicao");
                areaInteresse = request.getParameter("areaInteresse");
                sexo = request.getParameter("sexo");
                dia_nascimento = request.getParameter("dia");
                mes_nascimento = request.getParameter("mes");
                ano_nascimento = request.getParameter("ano");
                          
                dtNascimento = ano_nascimento + "-" + mes_nascimento + "-" + dia_nascimento;
                
                retorno = AplCadastrarUsuario.alterarDadosUsuario(usuario,nome,sobrenome,email,login,senha,instituicao,areaInteresse,dtNascimento,sexo);
                if(retorno==1){
                    session.setAttribute("usuario", usuario);
                    response.sendRedirect("menu.jsp");
                }
                else
                    response.sendRedirect("perfil.jsp?status="+"Login ja existente");     
                return;
            }
        }

        
//*******************************************************************************************//           
//******************CADASTRO DE AGENTE. INCLUSAO E ALTERACAO*********************************//
//*******************************************************************************************//
        if(metodo.compareTo("cadastroAgente")==0){
            
            acao = request.getParameter("acao");
            
            //declarando variaveis de agente 
            String nome = "";
            String cor = "";
            String tipoAgente = "";
            //variaveis especificas do agente movel
            Integer tamanho = null;
            String forma = "";
            Integer quantidadeInicial = null;
            String tipoMovimento = "";
            Integer passo = null;
            float angulo1 = 0;
            float angulo2 = 0;
            Integer tamanhoBorda = null; //variavel especifica do agente est. borda
            float grauDispersao = 0;         //variavel especifica do agente est. aleatorio
            String tipoPosicao = "";
            String nomeAntigo = "";
            List posicoes = new ArrayList();
            
                        
            if(acao.compareTo("incluir")==0){
            
                nome = request.getParameter("Nome");
                cor = request.getParameter("cor");
                tipoAgente = request.getParameter("tipoAgente");
                
                if(tipoAgente.compareTo("Estacionario Borda")==0){
                    
                    tamanhoBorda = new Integer(Integer.parseInt(request.getParameter("Tamanho da Borda")));
                    retorno = AplCadastrarAgente.incluirNovoAgenteEstBorda(nome,cor,tipoAgente,tamanhoBorda,modelo);
                  
                    if(retorno==1)
                        response.sendRedirect("incluirmodelo.jsp?status="+"Agente salvo com sucesso!");    
                    else{
                        if(retorno == -1)                    
                            response.sendRedirect("estacionarioborda.jsp?status="+"Ja existe um AGENTE com esse mesmo nome nesse modelo!");
                        else
                        {
                            if (retorno == 0)
                                response.sendRedirect("estacionarioborda.jsp?status="+"Nome de um AGENTE soh pode ter letras e numeros!");
                            else
                                response.sendRedirect("estacionarioborda.jsp?status="+"Ja existe um AGENTE ESTACIONARIO BORDA com tamanho de borda igual a " + tamanhoBorda + " nesse modelo!");
                        }
                    }
                    return;
                } 
            
                if(tipoAgente.compareTo("Estacionario Aleatorio")==0){
                    
                    tipoPosicao = request.getParameter("posicao");                
                    
                    if(tipoPosicao.compareTo("aleatorio")==0)
                        grauDispersao = Float.parseFloat(request.getParameter("grauDispersao"));
                                        
                    if(tipoPosicao.compareTo("posicaoInicial")==0)
                        posicoes = (List)session.getAttribute("posicoes");
                                                  
                    retorno = AplCadastrarAgente.incluirNovoAgenteEstAleatorio(nome,cor,tipoAgente,tipoPosicao,grauDispersao,posicoes,modelo);
                    session.removeAttribute("posicoes");
                    
                    if(retorno==1)
                        response.sendRedirect("incluirmodelo.jsp?status="+"Agente salvo com sucesso!");    
                    else
                        if (retorno == 0)
                            response.sendRedirect("estacionarioaleatorio.jsp?status="+"Nome de um AGENTE soh pode ter letras e numeros!");
                        else
                            response.sendRedirect("estacionarioaleatorio.jsp?status="+"Ja existe um AGENTE com esse mesmo nome nesse modelo!");
                    return;
                }
                
                if(tipoAgente.compareTo("Movel")==0){
             
                    tipoPosicao = request.getParameter("posicao");                
                    tamanho = new Integer(Integer.parseInt(request.getParameter("Tamanho")));
                    forma = request.getParameter("Forma");
                    //quantidadeInicial = new Integer(Integer.parseInt(request.getParameter("quantidadeInicial")));
                    tipoMovimento = request.getParameter("tipoMovimento");
                    
                    //quantidade Inicial
                    String tipoQtdInicial = request.getParameter("quantidadeInicial");
                    Integer idVariavel = new Integer(0);
                    String nomeVariavel = null;
                    if (tipoQtdInicial.equals("0")) //Qtd Inicial numerico
                        quantidadeInicial = Integer.parseInt(request.getParameter("numeroQtdInicial"));
                    else
                        idVariavel = Integer.parseInt(request.getParameter("numeroQtdInicial"));
                 
                    if(tipoMovimento.compareTo("Passear")==0){
                        passo = new Integer(Integer.parseInt(request.getParameter("passo")));
                        angulo1 = Float.parseFloat(request.getParameter("anguloMinimo"));
                        angulo2 = Float.parseFloat(request.getParameter("anguloMaximo"));
                    }
                    else
                        if(tipoMovimento.compareTo("Refletir")==0){
                            angulo1 = Float.parseFloat(request.getParameter("anguloReflexao"));
                        }
                                                    
                    if(tipoPosicao.compareTo("posicaoInicial")==0)
                        posicoes = (List)session.getAttribute("posicoesMovel");
                        
                    retorno = AplCadastrarAgente.incluirNovoAgenteMovel(nome,cor,tipoAgente,tipoPosicao,quantidadeInicial,forma,tamanho,tipoMovimento,passo,angulo1,angulo2,tipoQtdInicial,nomeVariavel,idVariavel,posicoes,modelo);
                    session.removeAttribute("posicoesMovel");
                    
                    if(retorno==1)
                        response.sendRedirect("incluirmodelo.jsp?status="+"Agente salvo com sucesso!");    
                    else
                    {
                        if(tipoMovimento.compareTo("Passear")==0)
                        {
                            if (retorno == 0)  
                                response.sendRedirect("movelpassear.jsp?status="+"Nome de um AGENTE soh pode ter letras e numeros!");
                            else
                                response.sendRedirect("movelpassear.jsp?status="+"Ja existe um AGENTE com esse mesmo nome nesse modelo!");
                        }
                        else
                            if(tipoMovimento.compareTo("Refletir")==0)
                            {
                                if (retorno == 0)  
                                    response.sendRedirect("movelrefletir.jsp?status="+"Nome de um AGENTE soh pode ter letras e numeros!");
                                else
                                    response.sendRedirect("movelrefletir.jsp?status="+"Ja existe um AGENTE com esse mesmo nome nesse modelo!");
                            }
                            else
                            {
                                if (retorno == 0)  
                                    response.sendRedirect("movelparado.jsp?status="+"Nome de um AGENTE soh pode ter letras e numeros!");
                                else
                                    response.sendRedirect("movelparado.jsp?status="+"Ja existe um AGENTE com esse mesmo nome nesse modelo!");
                            }
                    }
                    return;
                } 
            }            
            
            if(acao.compareTo("alterar")==0){
                
                nomeAntigo = request.getParameter("nomeAntigo");
                Agente agente;
                nome = request.getParameter("Nome");
                cor = request.getParameter("cor");
                                
                agente = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(),nomeAntigo);
                                              
                if(agente.getTipo().compareTo("Estacionario Borda")==0){  
                    tamanhoBorda = new Integer(Integer.parseInt(request.getParameter("Tamanho da Borda")));
                    AgenteEstBorda agenteEstBorda = (AgenteEstBorda)agente;
                
                    retorno = AplCadastrarAgente.alterarDadosAgenteEstBorda(agenteEstBorda,modelo,nome,cor,agenteEstBorda.getTipo(),tamanhoBorda);
                                        
                    if(retorno==1)
                        response.sendRedirect("incluirmodelo.jsp?status="+"Agente alterado com sucesso!");    
                    else{
                        if(retorno == -1)
                            response.sendRedirect("alterarestacionarioborda.jsp?nome="+ nomeAntigo + "&tamanho="+ agenteEstBorda.getTamanho() +"&cor="+ agenteEstBorda.getCor()+"&status="+"Ja existe uma agente com o nome " +nome+" nesse modelo!");
                        else
                        {
                            if(retorno == 0)
                                response.sendRedirect("alterarestacionarioborda.jsp?nome="+ nomeAntigo + "&tamanho="+ agenteEstBorda.getTamanho() +"&cor="+ agenteEstBorda.getCor()+"&status="+"Nome de AGENTE soh pode conter letra e numeros");
                            else
                                response.sendRedirect("alterarestacionarioborda.jsp?nome="+ nomeAntigo + "&tamanho="+ agenteEstBorda.getTamanho() +"&cor="+ agenteEstBorda.getCor()+"&status="+"Ja existe um AGENTE ESTACIONARIO BORDA com tamanho de borda igual a " + tamanhoBorda + " nesse modelo!");
                        }
                        return;
                    }  
                }
           
                if(agente.getTipo().compareTo("Estacionario Aleatorio")==0){
                    tipoPosicao = request.getParameter("posicao");                
                    AgenteEstAleatorio agenteEstAleatorio = (AgenteEstAleatorio)agente;
                    
                    if(tipoPosicao.compareTo("aleatorio")==0)
                        grauDispersao = Float.parseFloat(request.getParameter("grauDispersao"));
                                        
                    if(tipoPosicao.compareTo("posicaoInicial")==0)
                        posicoes = (List)session.getAttribute("posicoes");
                                                   
                    retorno = AplCadastrarAgente.alterarDadosAgenteEstAleatorio(agenteEstAleatorio,modelo,nome,cor,agenteEstAleatorio.getTipo(),tipoPosicao,grauDispersao,posicoes);
                    session.removeAttribute("posicoes");
                            
                    if(retorno==1)
                        response.sendRedirect("incluirmodelo.jsp?status="+"Agente alterado com sucesso!");    
                    else
                    {
                        if (retorno == 0)
                            response.sendRedirect("alterarestacionarioaleatorio.jsp?nome="+ nomeAntigo + "&cor="+ agenteEstAleatorio.getCor() + "&idAgente="+ agenteEstAleatorio.getIdAgente() +"&grauDispersao="+agenteEstAleatorio.getPorcentagem()+"&tipoPosicao="+agenteEstAleatorio.getTipoPosicao()+"&status="+"Nome de AGENTE soh pode ter letras e numeros");
                        else
                            response.sendRedirect("alterarestacionarioaleatorio.jsp?nome="+ nomeAntigo + "&cor="+ agenteEstAleatorio.getCor() + "&idAgente="+ agenteEstAleatorio.getIdAgente() +"&grauDispersao="+agenteEstAleatorio.getPorcentagem()+"&tipoPosicao="+agenteEstAleatorio.getTipoPosicao()+"&status="+"Ja existe uma agente com o nome " +nome+" nesse modelo!");
                    }
                    return;
                }
                
                if(agente.getTipo().compareTo("Movel")==0){
                    
                    tamanho = new Integer(Integer.parseInt(request.getParameter("Tamanho")));
                    forma = request.getParameter("Forma");
                    //quantidadeInicial = new Integer(Integer.parseInt(request.getParameter("quantidadeInicial")));
                    tipoMovimento = request.getParameter("tipoMovimento");
                    
                    //quantidade Inicial
                    String tipoQtdInicial = request.getParameter("tipoQtdInicial");
                    Integer idVariavel = null;
                    if (tipoQtdInicial.equals("0")) //Qtd Inicial numerico
                        quantidadeInicial = Integer.parseInt(request.getParameter("quantidadeInicial"));
                    else
                        idVariavel = Integer.parseInt(request.getParameter("quantidadeInicial"));
                 
                    if(tipoMovimento.compareTo("Passear")==0){
                        passo = new Integer(Integer.parseInt(request.getParameter("passo")));
                        angulo1 = Float.parseFloat(request.getParameter("anguloMinimo"));
                        angulo2 = Float.parseFloat(request.getParameter("anguloMaximo"));
                    }
                    if(tipoMovimento.compareTo("Refletir")==0)
                        angulo1 = Float.parseFloat(request.getParameter("anguloReflexao"));
                    
                    tipoPosicao = request.getParameter("posicao");
                    AgenteMovel agenteMovel = (AgenteMovel)agente;
                    
                    if(tipoPosicao.compareTo("posicaoInicial")==0)
                        posicoes = (List)session.getAttribute("posicoesMovel");
                    
                    retorno = AplCadastrarAgente.alterarDadosAgenteMovel(agenteMovel,modelo,nome,cor,agenteMovel.getTipo(),tipoPosicao,quantidadeInicial,forma,tamanho,tipoMovimento,passo,angulo1,angulo2,posicoes,tipoQtdInicial,idVariavel);
                    
                    if(retorno==1)
                        response.sendRedirect("incluirmodelo.jsp?status="+"Agente alterado com sucesso!");    
                    else{
                        if(tipoMovimento.compareTo("Passear")==0)
                        {
                            if(retorno == 0)
                                response.sendRedirect("alterar_movel.jsp?nome="+ nomeAntigo +"&tamanho="+ agenteMovel.getTamanho() +"&cor="+ agenteMovel.getCor() +"&acao="+acao +"&forma="+agenteMovel.getForma() +"&quantidadeInicial="+agenteMovel.getQuantidadeInicial() +"&tipoMovimento="+agenteMovel.getTipoMovimento() +"&anguloMinimo="+agenteMovel.getAngulo1() +"&anguloMaximo="+agenteMovel.getAngulo2()+"&passo="+agenteMovel.getPasso()+"&tipoPosicao="+agenteMovel.getTipoPosicao()+"&idAgente="+agenteMovel.getIdAgente()+"&status="+"Nome de AGENTE soh pode ter letras e numeros");
                            else
                                response.sendRedirect("alterar_movel.jsp?nome="+ nomeAntigo +"&tamanho="+ agenteMovel.getTamanho() +"&cor="+ agenteMovel.getCor() +"&acao="+acao +"&forma="+agenteMovel.getForma() +"&quantidadeInicial="+agenteMovel.getQuantidadeInicial() +"&tipoMovimento="+agenteMovel.getTipoMovimento() +"&anguloMinimo="+agenteMovel.getAngulo1() +"&anguloMaximo="+agenteMovel.getAngulo2()+"&passo="+agenteMovel.getPasso()+"&tipoPosicao="+agenteMovel.getTipoPosicao()+"&idAgente="+agenteMovel.getIdAgente()+"&status="+"Ja existe uma agente com o nome " +nome+" nesse modelo!");
                        }
                        else if(tipoMovimento.compareTo("Refletir")==0)
                        {
                            if(retorno == 0)
                                response.sendRedirect("alterar_movel.jsp?nome="+ nomeAntigo +"&tamanho="+ agenteMovel.getTamanho() +"&cor="+ agenteMovel.getCor() +"&acao="+acao +"&forma="+agenteMovel.getForma() +"&quantidadeInicial="+agenteMovel.getQuantidadeInicial() +"&tipoMovimento="+agenteMovel.getTipoMovimento() +"&anguloReflexao="+agenteMovel.getAngulo1() +"&passo="+agenteMovel.getPasso()+"&tipoPosicao="+agenteMovel.getTipoPosicao()+"&idAgente="+agenteMovel.getIdAgente()+"&status="+"Nome de AGENTE soh pode ter letras e numeros"); 
                            else
                                response.sendRedirect("alterar_movel.jsp?nome="+ nomeAntigo +"&tamanho="+ agenteMovel.getTamanho() +"&cor="+ agenteMovel.getCor() +"&acao="+acao +"&forma="+agenteMovel.getForma() +"&quantidadeInicial="+agenteMovel.getQuantidadeInicial() +"&tipoMovimento="+agenteMovel.getTipoMovimento() +"&anguloReflexao="+agenteMovel.getAngulo1() +"&passo="+agenteMovel.getPasso()+"&tipoPosicao="+agenteMovel.getTipoPosicao()+"&idAgente="+agenteMovel.getIdAgente()+"&status="+"Ja existe uma agente com o nome " +nome+" nesse modelo!"); 
                        }
                        else if(tipoMovimento.compareTo("Parado")==0)
                        {
                            if(retorno == 0)
                                response.sendRedirect("alterar_movel.jsp?nome="+ nomeAntigo +"&tamanho="+ agenteMovel.getTamanho() +"&cor="+ agenteMovel.getCor() +"&acao="+acao +"&forma="+agenteMovel.getForma() +"&quantidadeInicial="+agenteMovel.getQuantidadeInicial() +"&tipoMovimento="+agenteMovel.getTipoMovimento() + "&tipoPosicao="+agenteMovel.getTipoPosicao()+"&idAgente="+agenteMovel.getIdAgente()+"&status="+"Nome de AGENTE soh pode ter letras e numeros"); 
                            else
                                response.sendRedirect("alterar_movel.jsp?nome="+ nomeAntigo +"&tamanho="+ agenteMovel.getTamanho() +"&cor="+ agenteMovel.getCor() +"&acao="+acao +"&forma="+agenteMovel.getForma() +"&quantidadeInicial="+agenteMovel.getQuantidadeInicial() +"&tipoMovimento="+agenteMovel.getTipoMovimento() + "&tipoPosicao="+agenteMovel.getTipoPosicao()+"&idAgente="+agenteMovel.getIdAgente()+"&status="+"Ja existe uma agente com o nome " +nome+" nesse modelo!"); 
                        }
                    }
                    return;                                                   
                }
            }
            //Inicializar agente
            if(acao.compareTo("inicializar") == 0)
            {
                String op = request.getParameter("op");
                if(op.compareTo("agente") == 0)
                {
                    int idAgente = Integer.parseInt(request.getParameter("agente"));
                    int qtd = Integer.parseInt(request.getParameter("quantidade"));
                    Agente ag = AplCadastrarAgente.obterAgente(idAgente);
                    int retornoQ = AplCadastrarAgente.validarQuantidade(idAgente, qtd);
                    if(retornoQ == 1)
                    {
                        modelo = (Modelo)session.getAttribute("modelo");
                        retorno = AplCadastrarAgente.inicializarAgente(ag,qtd,modelo);

                        if(retorno > 0)
                            response.sendRedirect("inicializar_agente_conf.jsp?idIni="+retorno+"&idAgente="+idAgente+"&op=incluir&status=Agente escolhido com sucesso!");
                        else
                            response.sendRedirect("inicializar_agente.jsp?status=Erro ao salvar!");   
                    }
                    else
                    {
                        response.sendRedirect("inicializar_agente.jsp?status=Quantidade inválida!<br/>A quantidade deve ser menor que o valor inicial!");   
                    }
                }
                
                if(op.compareTo("agenteAlterar") == 0)
                {
                    int idAgente = Integer.parseInt(request.getParameter("agente"));
                    int qtd = Integer.parseInt(request.getParameter("quantidade"));
                    int idIni = Integer.parseInt(request.getParameter("idIni"));
                    int retornoQ = AplCadastrarAgente.validarQuantidade(idAgente, qtd);
                    if(retornoQ == 1)
                    {
                        retorno = AplCadastrarAgente.alterarInicializarAgente(idIni, qtd);

                        if(retorno > 0)
                            response.sendRedirect("inicializar_agente_conf.jsp?idIni="+idIni+"&idAgente="+idAgente+"&op=incluir&status=Agente escolhido com sucesso!");
                        else
                            response.sendRedirect("inicializar_agente.jsp?status=Erro ao salvar!");   
                    }
                    else
                    {
                        response.sendRedirect("inicializar_agente.jsp?idIni="+idIni+"&idAgente="+idAgente+"&op=alterar&qtd="+qtd+"&status=Quantidade inválida!<br/>A quantidade deve ser menor que o valor inicial!");   
                    }
                }
                
                if(op.compareTo("variaveis") == 0)
                {
                    String vars = request.getParameter("stringVar");
                    String valor;
                    int idIni = Integer.parseInt(request.getParameter("idIni"));
                    cor = request.getParameter("cor");
                    forma = request.getParameter("forma");
                    tamanho = Integer.parseInt(request.getParameter("tamanho"));
                    int r;
                    InicializarAgente ia = InicializarAgente.obterIA(idIni);
                    AplCadastrarAgente.alterarInicializarAgenteCor(ia, cor);
                    AplCadastrarAgente.alterarInicializarAgenteForma(ia, forma);
                    AplCadastrarAgente.alterarInicializarAgenteTamanho(ia, tamanho);
                    retorno = 1;
                    //TRATAR COR
                    if(!vars.equals(""))
                    {
                        for(String var: vars.split(";"))
                        {
                            valor = request.getParameter(var);
                            Variavel v = AplCadastrarVariavel.obterVariavel(Integer.parseInt(var));
                            r = AplCadastrarAgente.alterarInicializarAgenteVar(ia, v, valor);
                            if(r != 1)
                                retorno = -1;
                        }
                    }
                    if(retorno == 1)
                    {
                        response.sendRedirect("inicializar_agente.jsp?status=Inicalização de agente concluída com sucesso!");
                    }
                }
            }
        }
                
        
//*******************************************************************************************//        
//********************CADASTRO DE VARIAVEIS. INCLUSAO E ALTERACAO****************************//
//*******************************************************************************************//        
        if(metodo.compareTo("cadastroVariavel")==0){
            
            acao = request.getParameter("acao");
            
            //declarando as variaveis de variavel
            String nome = "";
            String nomeAntigo = "";
            String tipoVariavel = "";
            boolean valorLogico;  //variavel de valor logico
            //variaveis de slider
            float valorInicial;
            float valorMinimo;
            float valorMaximo;
            float incremento;
            String valor; //virou expressao
            String idAgente = "";
            int id;
            Agente agente = null;
            
            nome = request.getParameter("Nome");
            idAgente = request.getParameter("Agente");
            
            if(idAgente != null){
                id = Integer.parseInt(idAgente);
                agente = AplCadastrarAgente.obterAgente(id);
            }
            
            if(acao.compareTo("incluir")==0){
            
                tipoVariavel = request.getParameter("TipoVariavel");
                                                
                if(tipoVariavel.compareTo("Logica")==0){
                    
                    valorLogico = Boolean.parseBoolean(request.getParameter("valorLogico"));
                             
                    retorno = AplCadastrarVariavel.incluirNovaVariavelLogica(nome,tipoVariavel,valorLogico,modelo,agente);
                    if(retorno==1)
                        response.sendRedirect("incluirmodelo.jsp?status="+"Variavel salva com sucesso!");    
                    else
                    {
                        if(retorno == 0)
                            response.sendRedirect("variavellogicaInicial.jsp?status="+"Nome de VARIAVEL soh pode ter letras e numeros!");
                        else
                            response.sendRedirect("variavellogicaInicial.jsp?status="+"Ja existe uma VARIAVEL com esse mesmo nome nesse modelo!");
                    }
                    return;
                }
                
                if(tipoVariavel.compareTo("Slider")==0){
                    
                    valorInicial = Float.parseFloat(request.getParameter("valorinicial"));
                    valorMinimo = Float.parseFloat(request.getParameter("valorminimo"));
                    valorMaximo = Float.parseFloat(request.getParameter("valormaximo"));
                    incremento = Float.parseFloat(request.getParameter("incremento"));
                              
                    retorno = AplCadastrarVariavel.incluirNovaVariavelSlider(nome,tipoVariavel,valorInicial,valorMinimo,valorMaximo,incremento,modelo,agente);
                    if(retorno==1)
                        response.sendRedirect("incluirmodelo.jsp?status="+"Variavel salva com sucesso!");    
                    else
                    {
                        if(retorno == 0)
                            response.sendRedirect("variavelsliderInicial.jsp?status="+"Nome de VARIAVEL soh pode ter letras e numeros!");
                        else
                            response.sendRedirect("variavelsliderInicial.jsp?status="+"Ja existe uma VARIAVEL com esse mesmo nome nesse modelo!");
                    }
                    return;
                }
                
                 if(tipoVariavel.compareTo("Valor")==0){
                    
                    valor = request.getParameter("Valor");
           
                    retorno = AplCadastrarVariavel.incluirNovaVariavelValor(nome,tipoVariavel,valor,modelo,agente);                        
                    if(retorno==1)
                        response.sendRedirect("incluirmodelo.jsp?status="+"Variavel salva com sucesso!");    
                    else
                    {
                        if(retorno == 0)
                            response.sendRedirect("variavelvalorInicial.jsp?status="+"Nome de VARIAVEL soh pode ter letras e numeros!");
                        else
                            response.sendRedirect("variavelvalorInicial.jsp?status="+"Ja existe uma VARIAVEL com esse mesmo nome nesse modelo!");
                    }
                    return;
                }
            }
                              
            if(acao.compareTo("alterar")==0){
                
                nomeAntigo = request.getParameter("nomeAntigo");
                Variavel variavel;
                variavel = AplCadastrarVariavel.obterVariavel(modelo.getIdModelo().intValue(),nomeAntigo);
                
                if(variavel.getTipo().compareTo("Logica")==0){  
                    VariavelLogica variavelLogica = (VariavelLogica)variavel;
                    valorLogico = Boolean.parseBoolean(request.getParameter("valorLogico"));
                    retorno = AplCadastrarVariavel.alterarDadosVariavelLogica(variavelLogica,modelo,agente,nome,variavelLogica.getTipo(),valorLogico);
                                        
                    if(retorno==1)
                        response.sendRedirect("incluirmodelo.jsp?status="+"Variavel alterada com sucesso!");    
                    else
                    {
                        if(retorno == 0)
                            response.sendRedirect("alterar_variavellogica.jsp?nome="+ nomeAntigo +"&valorLogico="+variavelLogica.getValorLogico()+"&status="+"Nome de VARIAVEL soh pode ter letras e numeros!");                    
                        else
                            response.sendRedirect("alterar_variavellogica.jsp?nome="+ nomeAntigo +"&valorLogico="+variavelLogica.getValorLogico()+"&status="+"Ja existe uma VARIAVEL com o nome " +nome+" nesse modelo!");                    
                    }
                    return; 
                }  
                
                if(variavel.getTipo().compareTo("Slider")==0){
                    VariavelSlider variavelSlider = (VariavelSlider)variavel;
                    valorInicial = Float.parseFloat(request.getParameter("valorinicial"));
                    valorMinimo = Float.parseFloat(request.getParameter("valorminimo"));
                    valorMaximo = Float.parseFloat(request.getParameter("valormaximo"));
                    incremento = Float.parseFloat(request.getParameter("incremento"));
                    retorno = AplCadastrarVariavel.alterarDadosVariavelSlider(variavelSlider,modelo,agente,nome,variavelSlider.getTipo(),valorInicial,valorMinimo,valorMaximo,incremento);
                    
                    if(retorno==1)
                        response.sendRedirect("incluirmodelo.jsp?status="+"Variavel alterada com sucesso!");    
                    else
                    {
                        if(retorno == 0)
                            response.sendRedirect("alterar_variavelslider.jsp?nome="+ nomeAntigo +"&valorInicial="+ variavelSlider.getValorInicial()+"&valorMinimo="+ variavelSlider.getValorMinimo() +"&valorMaximo="+ variavelSlider.getValorMaximo()+"&incremento="+ variavelSlider.getIncremento() +"&status="+"Nome de VARIAVEL so pode ter letras e numeros!");                    
                        else
                            response.sendRedirect("alterar_variavelslider.jsp?nome="+ nomeAntigo +"&valorInicial="+ variavelSlider.getValorInicial()+"&valorMinimo="+ variavelSlider.getValorMinimo() +"&valorMaximo="+ variavelSlider.getValorMaximo()+"&incremento="+ variavelSlider.getIncremento() +"&status="+"Ja existe uma variavel com o nome " +nome+" nesse modelo!");                    
                    }
                    return; 
                }                    
                
                if(variavel.getTipo().compareTo("Valor")==0){
                    VariavelValor variavelValor = (VariavelValor)variavel;
                    valor = request.getParameter("Valor");                                                
                    retorno = AplCadastrarVariavel.alterarDadosVariavelValor(variavelValor,modelo,agente,nome,variavelValor.getTipo(),valor,variavelValor.getValorCriacao());
                                     
                    if(retorno==1)
                        response.sendRedirect("incluirmodelo.jsp?status="+"Variavel alterada com sucesso!");    
                    else
                    {
                        if(retorno == 0)
                            response.sendRedirect("alterar_variavelvalor.jsp?nome="+ nomeAntigo +"&valor="+variavelValor.getValor()+"&status="+"Nome de VARIAVEL so pode ter letras e numeros!");                    
                        else
                            response.sendRedirect("alterar_variavelvalor.jsp?nome="+ nomeAntigo +"&valor="+variavelValor.getValor()+"&status="+"Ja existe uma variavel com o nome " +nome+" nesse modelo!");                    
                    }
                    return; 
                }
            }
        }           
        
//*******************************************************************************************//           
//******************CADASTRO DE MODELO. INCLUSAO E ALTERACAO*********************************//
//*******************************************************************************************//        
        if(metodo.compareTo("cadastroModelo")==0){
           
            acao = request.getParameter("acao");
            
            //declarando as variaveis de modelo
            String nome = "";
            String descricao = "";
            String dtCriacao = "";
            Modelo modeloPai = null;
                                             
            if(acao.compareTo("incluir")==0){
                
                nome = request.getParameter("nome");
                descricao = request.getParameter("descricao");
                dtCriacao = ((new Date().getYear())+1900)+"-"+((new Date().getMonth())+1)+"-"+new Date().getDate()+" "+new Date().getHours()+":"+new Date().getMinutes()+":"+new Date().getSeconds();

                modelo = AplCadastrarModelo.incluirNovoModelo(nome,descricao,dtCriacao,usuario,modeloPai);
                
                if(modelo!=null){
                    session.setAttribute("modelo", modelo);
                    response.sendRedirect("menu2_inclusao.jsp?status="+"");
                }
                else
                    response.sendRedirect("menu2_inclusaomodelo.jsp?status="+"Ja existe um modelo com este mesmo nome.");
                return;      
            }
            
            if(acao.compareTo("incluirNovaVersao")==0){
                Modelo modeloAux = (Modelo)session.getAttribute("modelo");
                nome = request.getParameter("nome");
                descricao = request.getParameter("descricao");                                
                dtCriacao = ((new Date().getYear())+1900)+"-"+((new Date().getMonth())+1)+"-"+new Date().getDate()+" "+new Date().getHours()+":"+new Date().getMinutes()+":"+new Date().getSeconds();
                
                boolean alterarVersao = true;
                if (request.getParameter("alterarVersao") == null)
                    alterarVersao = false;
                
                if(alterarVersao)
                    modelo = AplCadastrarModelo.incluirNovaVersaoModelo(nome,descricao,dtCriacao,usuario,modeloAux);
                else
                    modelo = (Modelo) session.getAttribute("modelo");
                
                if(!alterarVersao || modelo!=null){
                    session.setAttribute("modelo", modelo);
                    response.sendRedirect("menu2_inclusao.jsp?status="+" ");
                }
                else
                    response.sendRedirect("menu2_alteracaomodelo.jsp?status="+"Ja existe um modelo com este mesmo nome.");
                return;      
            }
            
            if(acao.compareTo("alterar") == 0){                       
                
                nome = request.getParameter("nome");
                descricao = request.getParameter("descricao");
                                            
                retorno = AplCadastrarModelo.alterarDadosModelo(modelo,usuario,nome,descricao);
             
                if(retorno==1){
                    session.setAttribute("modelo", modelo);
                    response.sendRedirect("incluirmodelo.jsp?status="+"Modelo alterado com sucesso!");
                }
                else
                    response.sendRedirect("incluirmodelo.jsp?status="+"Ja existe um modelo com este mesmo nome.");     
                return;
            }
        }
        
//*******************************************************************************************//        
//********************CADASTRO DE TEXTO. INCLUSAO E ALTERACAO********************************//
//*******************************************************************************************//        
        if(metodo.compareTo("cadastroTexto")==0){
            
            acao = request.getParameter("acao");
            
            //declarando as variaveis de texto
            String nome = "";
            String nomeAntigo = "";
            String texto = "";
            
            nome = request.getParameter("Nome");
            texto = request.getParameter("Texto");    
            
            if(acao.compareTo("incluir")==0){
                                      
                retorno = AplCadastrarMonitorTexto.incluirNovoTexto(nome,texto,modelo);
                if(retorno==1)
                    response.sendRedirect("incluirmodelo.jsp?status="+"Texto salvo com sucesso!");    
                else
                    response.sendRedirect("incluirtexto.jsp?status="+"Ja existe um texto com esse mesmo nome nesse modelo!");                    
                return;       
            }
            
            if(acao.compareTo("alterar")==0){
                
                nomeAntigo = request.getParameter("nomeAntigo");
                Texto text;
                text = AplCadastrarMonitorTexto.obterTexto(modelo.getIdModelo().intValue(),nomeAntigo);
                retorno = AplCadastrarMonitorTexto.alterarDadosTexto(text,modelo,nome,texto);
                 if(retorno==1)
                    response.sendRedirect("incluirmodelo.jsp?status="+"Texto alterado com sucesso!");    
                else
                    response.sendRedirect("alterartexto.jsp?nome="+ nomeAntigo +"&texto="+texto+"&status="+"Ja existe um texto com o nome " +nome+" nesse modelo!");                    
                return;     
            }
        }
        
//*******************************************************************************************//                
//******************CADASTRO DE MONITOR. INCLUSAO E ALTERACAO********************************//        
//*******************************************************************************************//                
        if(metodo.compareTo("cadastroMonitor")==0){
            
            acao = request.getParameter("acao");            
            
            //declarando as variaveis de monitores
            String nome = "";
            String nomeAntigo = "";
            String expressao = "";
            int casasDecimaisAuxiliar = 0;
            Integer casasDecimais = null;
            
            nome = request.getParameter("Nome");
            expressao = request.getParameter("Expressao");
            casasDecimaisAuxiliar = Integer.parseInt(request.getParameter("Casas Decimais"));
            casasDecimais = new Integer(casasDecimaisAuxiliar);
                        
            if(acao.compareTo("incluir")==0){
                                                                            
                retorno = AplCadastrarMonitorTexto.incluirNovoMonitor(nome,expressao,casasDecimais,modelo);
                if(retorno==1){
                    response.sendRedirect("incluirmodelo.jsp?status="+"Monitor salvo com sucesso!");    
                }
                else
                    response.sendRedirect("incluirmonitor.jsp?status="+"Ja existe um monitor com esse mesmo nome nesse modelo!");                    
                return;                   
            }
            
            if(acao.compareTo("alterar")==0){
                
                nomeAntigo = request.getParameter("nomeAntigo");
                Monitor monitor;
                monitor = AplCadastrarMonitorTexto.obterMonitor(modelo.getIdModelo().intValue(),nomeAntigo);
                retorno = AplCadastrarMonitorTexto.alterarDadosMonitor(monitor,modelo,nome,expressao,casasDecimais);
                 if(retorno==1)
                    response.sendRedirect("incluirmodelo.jsp?status="+"Monitor alterado com sucesso!");    
                else
                    response.sendRedirect("alterarmonitor.jsp?nome="+ nomeAntigo +"&casasDecimais="+casasDecimais+"&expressao="+expressao+"&status="+"Ja existe um monitor com o nome " +nome+" nesse modelo!");                    
                return;     
            }
        }
        
        
//*******************************************************************************************//                
//******************CADASTRO DE GR�FICO. INCLUSAO E ALTERACAO********************************//        
//*******************************************************************************************//                
        if(metodo.compareTo("cadastroPlot")==0){
            
            acao = request.getParameter("acao");            
            
            //declarando as variaveis plot
            String nomeGrafico = "";
            String nomeAntigoGrafico = "";
            String tipoGrafico = "";
            String nomeEixoX = "";
            String nomeEixoY = "";
            int tamanho;
            float xMinimo,xMaximo,yMinimo,yMaximo;
            String nomeVariavel = "";
            String cor = "";
            String check = "";
            String parametro1 = "";
            String parametro2 = "";
            String parametro3 = "";
            List variaveis = new ArrayList();
            List cores = new ArrayList();
            
            tamanho = Integer.parseInt(request.getParameter("tamanho"));
            nomeGrafico = request.getParameter("Nome");
            tipoGrafico = request.getParameter("tipoGrafico");
            nomeEixoX = request.getParameter("Eixo x");
            nomeEixoY = request.getParameter("Eixo y");
            xMinimo = Float.parseFloat(request.getParameter("X minimo"));
            xMaximo = Float.parseFloat(request.getParameter("X maximo"));
            yMinimo = Float.parseFloat(request.getParameter("Y minimo"));
            yMaximo = Float.parseFloat(request.getParameter("Y maximo"));
            
            for(int contador=1 ; contador< tamanho+1; contador++){              
                   parametro1 = "variavel" + contador;
                   parametro2 = "cor" + contador;
                   parametro3 = "checkbox" + contador;
                   nomeVariavel = request.getParameter(parametro1);
                   cor = request.getParameter(parametro2);
                   check = request.getParameter(parametro3);
                   
                   if(check != null){
                        variaveis.add(nomeVariavel);
                        cores.add(cor);                                             
                   }
            }                        
                        
            if(acao.compareTo("incluir")==0){
                            
                retorno = AplCadastrarPlot.incluirNovoPlot(nomeGrafico,tipoGrafico,nomeEixoX,nomeEixoY,xMinimo,xMaximo,yMinimo,yMaximo,variaveis,cores,modelo);
                if(retorno==1)
                    response.sendRedirect("incluirmodelo.jsp?status="+"Grafico salvo com sucesso!");    
                else
                    response.sendRedirect("incluirgrafico.jsp?status="+"Ja existe um grafico com esse mesmo nome nesse modelo!");                    
                return;                   
            }
            
             if(acao.compareTo("alterar")==0){
                
                nomeAntigoGrafico = request.getParameter("nomeAntigo");
                Plot plot;
                plot = AplCadastrarPlot.obterPlot(modelo.getIdModelo().intValue(),nomeAntigoGrafico);
                retorno = AplCadastrarPlot.alterarDadosPlot(plot,modelo,nomeGrafico,tipoGrafico,nomeEixoX,nomeEixoY,xMinimo,xMaximo,yMinimo,yMaximo,variaveis,cores);
                if(retorno==1)
                    response.sendRedirect("incluirmodelo.jsp?status="+"Grafico alterado com sucesso!");    
                else
                    response.sendRedirect("alterargrafico.jsp?status="+"Ja existe um grafico com o nome " +nomeGrafico+" nesse modelo!");                    
                return;                               
            }
        }  
        
        
//********************************************************************************************//                
//*******************************CONTROLE DE VERSOES DO MODELO********************************//        
//********************************************************************************************//                
        if(metodo.compareTo("versoesModelo")==0){
            
            String pesquisa = "";
            List modelos = null;
            
            acao = request.getParameter("acao");            
            pesquisa = request.getParameter("O campo");            
            
            modelos = AplCadastrarModelo.consultaModelos(pesquisa);
                                            
            if(acao.compareTo("pesquisar")==0){
                if(modelos.isEmpty())
                    response.sendRedirect("telaverde.jsp?status="+"Nao existe nenhum modelo com essas iniciais."); 
                else
                    utilitario.Pagina.montaPaginaPesquisaVersoesModelo(response,"SELECIONE O MODELO","Consultar",modelos);
                return;
            }                                    
        }

        
//********************************************************************************************//                
//*************ARMAZENA DADOS REFERENTES A POSICAO DO AGENTE ESTACIONARIO NA TELA*************//        
//********************************************************************************************//   
        
        if(metodo.compareTo("posicaoAgente")==0){
           session.removeAttribute("posicoes");
           String nome = request.getParameter("nome");
           String cor = request.getParameter("cor");
           String idAgente = request.getParameter("idAgente");
           acao = request.getParameter("acao");
           String parametro = "";
           List posicoes = new ArrayList();
           String linha = "";
           String coluna = "";
           String valor = "";
           
           for(int l=0 ; l<51; l++){
               if(l<10)
                   linha = "0" + l;
               else
                   linha = "" + l;
               for(int c=0; c<51; c++){
                   if(c<10)
                       coluna = "0" + c;   
                   else
                       coluna = "" + c;
                   parametro = "checkbox" + linha + coluna;
                   valor = request.getParameter(parametro);
                   if(valor != null){
                       PosicaoInicial pi = new PosicaoInicial();
                       pi.setYInicial(new Integer(25-Integer.parseInt(linha)));
                       pi.setXInicial(new Integer(Integer.parseInt(coluna)-25));
                       posicoes.add(pi);                                             
                   }
               }               
           }
           session.setAttribute("posicoes", posicoes);
           if(acao.compareTo("incluir")==0)
               response.sendRedirect("estacionarioaleatorio.jsp?nome="+ nome +"&cor="+ cor+"&posicao=3"+"&status="+"Para as alteracoes do posicionamento serem afetuadas e necessario clicar em \"Salvar Agente\""); 
           else
               response.sendRedirect("alterarestacionarioaleatorio.jsp?nome="+ nome +"&cor="+ cor+ "&pos=posicaoInicial" + "&idAgente="+ idAgente+"&posicao=posicaoInicial"+"&status="+"Para as alteracoes do posicionamento serem afetuadas e necessario clicar em \"Alterar Agente\"");            
           return;
        }
        
        
//********************************************************************************************//                
//*****************ARMAZENA DADOS REFERENTES A POSICAO DO AGENTE MOVEL NA TELA****************//        
//********************************************************************************************//   
        if(metodo.compareTo("posicaoAgenteMovel")==0){
            session.removeAttribute("posicoesMovel");
            String nome = request.getParameter("nome");           
            String cor = request.getParameter("cor");
            String tamanho = request.getParameter("tamanho");
            String forma = request.getParameter("forma");
            String quantidadeInicial =request.getParameter("quantidadeInicial");
            String numeroQtdInicial = request.getParameter("numeroQtdInicial");
            String tipoMovimento = request.getParameter("tipoMovimento");
            acao = request.getParameter("acao");
            String idAgente = request.getParameter("idAgente");
            String parametro = "";
            List posicoesMovel = new ArrayList();
            String linha = "";
            String coluna = "";
            String valor = "";
            String passo, angulo1, angulo2;
                               
            int totalValores = 0;
            for(int l=0 ; l<51; l++){
                if(l<10)
                    linha = "0" + l;
                else
                    linha = "" + l;
                for(int c=0; c<51; c++){
                    if(c<10)
                        coluna = "0" + c;   
                   else
                       coluna = "" + c;
                    parametro = "text" + linha + coluna;
                    valor = request.getParameter(parametro);
                    if(valor != ""){
                        PosicaoInicial pi = new PosicaoInicial();
                        pi.setYInicial(new Integer(25-Integer.parseInt(linha)));
                        pi.setXInicial(new Integer(Integer.parseInt(coluna)-25));
                        pi.setQuantidadePosicao(Integer.parseInt(valor));
                        totalValores += Integer.parseInt(valor);
                        posicoesMovel.add(pi);                                             
                   }
               }               
           }
            
           session.setAttribute("posicoesMovel", posicoesMovel);
           if(acao.compareTo("incluir")==0){
               if(tipoMovimento.compareTo("Passear")==0){
                   passo = request.getParameter("passo");
                   angulo1 = request.getParameter("anguloMinimo");
                   angulo2 = request.getParameter("anguloMaximo");
                   response.sendRedirect("movelpassear.jsp?nome="+ nome +"&cor="+ cor + "&tamanho="+ tamanho + "&forma="+ forma + "&quantidadeInicial="+ quantidadeInicial + "&numeroQtdInicial=" + numeroQtdInicial + "&tipoMovimento="+ tipoMovimento + "&passo="+ passo + "&anguloMinimo="+ angulo1 + "&anguloMaximo="+ angulo2 + "&totalPosicoes=" + totalValores + "&posicao=3"+"&status="+"Para as alteracoes do posicionamento serem afetuadas e necessario clicar em \"Salvar Agente\""); 
               }else{
                   angulo1 = request.getParameter("anguloReflexao");
                   response.sendRedirect("movelrefletir.jsp?nome="+ nome +"&cor="+ cor + "&tamanho="+ tamanho + "&forma="+ forma + "&quantidadeInicial="+ quantidadeInicial + "&numeroQtdInicial=" + numeroQtdInicial + "&tipoMovimento="+ tipoMovimento + "&anguloReflexao="+ angulo1 +"&posicao=3"+  "&totalPosicoes=" + totalValores +"&status="+"Para as alteracoes do posicionamento serem afetuadas e necessario clicar em \"Salvar Agente\""); 
               }
           }
           else{
               if(tipoMovimento.compareTo("Passear")==0){
                   passo = request.getParameter("passo");
                   angulo1 = request.getParameter("anguloMinimo");
                   angulo2 = request.getParameter("anguloMaximo");
                   response.sendRedirect("alterar_movel.jsp?nome="+ nome + "&idAgente="+ idAgente + "&cor="+ cor + "&tamanho="+ tamanho + "&forma="+ forma + "&quantidadeInicial=" + numeroQtdInicial + "&tipoQtdInicial=" + quantidadeInicial + "&tipoMovimento="+ tipoMovimento + "&passo="+ passo + "&anguloMinimo="+ angulo1 + "&anguloMaximo="+ angulo2 + "&totalPosicoes=" + totalValores + "&tipoPosicao=posicaoInicial"+"&status="+"Para as alteracoes do posicionamento serem afetuadas e necessario clicar em \"Alterar Agente\"");
               }else{
                   angulo1 = request.getParameter("anguloReflexao");
                   response.sendRedirect("alterar_movel.jsp?nome="+ nome + "&idAgente="+ idAgente + "&cor="+ cor + "&tamanho="+ tamanho + "&forma="+ forma + "&quantidadeInicial="+ numeroQtdInicial + "&tipoQtdInicial=" + quantidadeInicial + "&tipoMovimento="+ tipoMovimento + "&anguloReflexao="+ angulo1 +"&tipoPosicao=posicaoInicial" + "&totalPosicoes=" + totalValores +"&status="+"Para as alteracoes do posicionamento serem afetuadas e necessario clicar em \"Salvar Agente\""); 
               }
           }
           return;
        }
    }
    
    private String pesquisaNomeVariavelAgente(AgenteMovel agenteMovel, String acao)
    {//Gambiarra => agenteMovel.getVariavelSlider().getNome() dah erro
        
        Session s = HibernateUtility.getSession();
        Transaction t = s.beginTransaction();
        
        Query q = s.createQuery("from AgenteMovel m where m.nome = :c");
        q.setParameter("c", agenteMovel.getNome());
       
        List agentes = q.list();
        VariavelSlider v = null;
        for (int i=0; i<agentes.size(); i++)
        {
            AgenteMovel ag = (AgenteMovel) agentes.get(i);
            if (modelo.getNome().equals(ag.getModelo().getNome()))
            {
                v = ag.getVariavelSlider();
                break;
            }
        }
               
        String x = "";
        if (acao.equals("consultar"))
            x = v.getNome();
        else
            x = v.getIdVariavel().toString();
        
        t.commit();
        s.close();
        return x;
    }
}
