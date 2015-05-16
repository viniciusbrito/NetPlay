/*
 * AplCadastrarModelo.java
 *
 * Created on 2 de Maio de 2006, 21:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cgt;

import cdp.*;
import cgt.*;
import java.util.*;

import utilitario.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;


/**
 *
 * @author Alexandre Andrade
 *          Diego Velasco
 *
 */
public class AplCadastrarModelo {
    
    /** Creates a new instance of AplCadastrarModelo */
    public AplCadastrarModelo() {
    }
 
    
    public static Modelo incluirNovoModelo(String nome,String descricao,String dtCriacao,Usuario usuario,Modelo modeloPai){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("buscarModeloPeloNome");
        select.setString("nome",nome);
        
        List objetos = select.list();
        if(objetos.size()>0) // Verificando se jah existe um modelo com o mesmo nome
        {
            transacao.commit(); //Finalizando a transacao
            sessao.close(); //Fechando a sessao
            return null;
        }
        
        Modelo modelo = new Modelo();
        modelo.setNome(nome);
        modelo.setDescricao(descricao);
        modelo.setDtCriacao(dtCriacao);
        modelo.setModeloPai(modeloPai);
        modelo.setUsuario(usuario);
                
        sessao.save(modelo); // persistindo o objeto
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return modelo;     
    }
    
    
    public static Modelo incluirNovaVersaoModelo(String nome,String descricao,String dtCriacao,Usuario usuario,Modelo modeloPai){
             
        Modelo modelo;
        List textos=null;
        List monitores=null;
        List agentes=null;
        List variaveis=null;
        List plots=null;
        List posicoesAgente=null;
        List posicoesAgenteMovel=null;
        List variaveisGrafico = new ArrayList();
        List coresGrafico = new ArrayList();
        List cvp=null;
        String nomeVariavel="";
        Agente agente=null;
        VariavelValor vv;
        String nomeAgente = "";
        int idModeloPai = modeloPai.getIdModelo().intValue();
        int retorno = 1;
        
        List composicoes = null;
        
        modelo = incluirNovoModelo(nome,descricao,dtCriacao,usuario,modeloPai);
        if(modelo==null)
            return modelo;
        
        //Gravando os textos do modelo
        textos = AplCadastrarMonitorTexto.textosDoModelo(idModeloPai);
        for(int i=0; i < textos.size(); i++){
             Texto texto = (Texto)textos.get(i);
             retorno = AplCadastrarMonitorTexto.incluirNovoTexto(texto.getNome(),texto.getTexto(),modelo);
        }
                
        //Gravando os monitores do modelo
        monitores = AplCadastrarMonitorTexto.monitoresDoModelo(idModeloPai);
        for(int i=0; i < monitores.size(); i++){
             Monitor monitor = (Monitor)monitores.get(i);
             retorno = AplCadastrarMonitorTexto.incluirNovoMonitor(monitor.getNome(),monitor.getExpressao(),monitor.getCasasDecimais(),modelo);
        }
             
        //Gravando as variaveis do modelo
        variaveis = AplCadastrarVariavel.variaveisDoModelo(idModeloPai);
        for(int i=0; i < variaveis.size(); i++){
             Variavel variavel = (Variavel)variaveis.get(i);
             agente = AplCadastrarAgente.obterAgenteVariavel(idModeloPai,variavel.getNome());
             
             if(variavel.getTipo().compareTo("Logica")==0){
                 VariavelLogica variavelLogica = (VariavelLogica)variavel;
                 retorno = AplCadastrarVariavel.incluirNovaVariavelLogica(variavelLogica.getNome(),variavelLogica.getTipo(),variavelLogica.getValorLogico(),modelo,agente);
             }
             else{
                 if(variavel.getTipo().compareTo("Slider")==0){
                     VariavelSlider variavelSlider = (VariavelSlider)variavel;
                     retorno = AplCadastrarVariavel.incluirNovaVariavelSlider(variavelSlider.getNome(),variavelSlider.getTipo(),variavelSlider.getValorInicial(),variavelSlider.getValorMinimo(),variavelSlider.getValorMaximo(),variavelSlider.getIncremento(),modelo,agente);
                 }
                 else{
                     VariavelValor variavelValor = (VariavelValor)variavel;
                     retorno = AplCadastrarVariavel.incluirNovaVariavelValor(variavelValor.getNome(),variavelValor.getTipo(),variavelValor.getValor(),modelo,agente);
                 }
             }
        }
        
        //Gravando os plots do modelo
        plots = AplCadastrarPlot.plotsDoModelo(idModeloPai);
        for(int i=0; i < plots.size(); i++){
                Plot plot = (Plot)plots.get(i);
                cvp = AplCadastrarPlot.variaveisDoPlot(plot.getIdPlot().intValue());
                variaveisGrafico.clear();
                coresGrafico.clear();
                for(int j=0; j< cvp.size(); j++){
                    CorVariavelPlot cor = (CorVariavelPlot)cvp.get(j);
                    nomeVariavel = AplCadastrarVariavel.obterNomeVariavelPlot(cor.getIdCorVariavelPlot().intValue());
                    variaveisGrafico.add(nomeVariavel);
                    coresGrafico.add(cor.getCor());
                }           
                                                 
               retorno = AplCadastrarPlot.incluirNovoPlot(plot.getNomeGrafico(),plot.getTipo(),plot.getNomeEixoX(),plot.getNomeEixoY(),plot.getXMinimo(),plot.getXMaximo(),plot.getYMinimo(),plot.getYMaximo(),variaveisGrafico,coresGrafico,modelo);                
        }
        
        //Gravando os agentes do modelo
        agentes = AplCadastrarAgente.agentesDoModelo(idModeloPai);
        for(int i=0; i < agentes.size(); i++){
           Agente agenteAux = (Agente)agentes.get(i);
            
            if(agenteAux.getTipo().compareTo("Estacionario Borda")==0){
                AgenteEstBorda aeb = (AgenteEstBorda)agenteAux;
                retorno = AplCadastrarAgente.incluirNovoAgenteEstBorda(aeb.getNome(),aeb.getCor(),aeb.getTipo(),aeb.getTamanho(),modelo);
            }
            else{
               if(agenteAux.getTipo().compareTo("Estacionario Aleatorio")==0){
                   AgenteEstAleatorio aea = (AgenteEstAleatorio)agenteAux;
                   posicoesAgente = AplCadastrarAgente.posicoesAgente(aea.getIdAgente().intValue());
                   retorno = AplCadastrarAgente.incluirNovoAgenteEstAleatorio(aea.getNome(),aea.getCor(),aea.getTipo(),aea.getTipoPosicao(),aea.getPorcentagem(),posicoesAgente,modelo);
               }
               else{
                   AgenteMovel am = (AgenteMovel)agenteAux;
                   
                   posicoesAgenteMovel = AplCadastrarAgente.posicoesAgente(am.getIdAgente().intValue());
                   String nomeVar;
                   if (am.getTipoQtdInicial().equals("Num"))
                       nomeVar = null;
                   else
                       nomeVar = buscarNomeVar(am);
                   retorno = AplCadastrarAgente.incluirNovoAgenteMovel(am.getNome(),am.getCor(),am.getTipo(),am.getTipoPosicao(),am.getQuantidadeInicial(),am.getForma(),am.getTamanho(),am.getTipoMovimento(),am.getPasso(),am.getAngulo1(),am.getAngulo2(),am.getTipoQtdInicial(),nomeVar,0,posicoesAgenteMovel,modelo);                            
               }
            }
           atualizaVariaveis (agenteAux, modelo);
        }
        
        //Gravando as composicaes do modelo
        AplCadastrarComposicao.alterarModelo(idModeloPai, modelo);        
           
        return modelo;               
    }
       
    public static int alterarDadosModelo(Modelo modelo,Usuario usuario,String nome,String descricao){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        if(modelo.getNome().compareTo(nome)!=0) //se ele alterou nome, ve se nome ja existe
        {
            Query select = sessao.getNamedQuery("buscarModeloPeloNome");
            select.setString("nome",nome);
        
            List objetos = select.list();
            if(objetos.size()>0) // Verificando se jah existe um modelo com o mesmo nome
            {
                transacao.commit(); //Finalizando a transacao
                sessao.close(); //Fechando a sessao
                return -1;
            }
        }
        
        modelo.setNome(nome);
        modelo.setDescricao(descricao);
        modelo.setUsuario(usuario);       
                        
        sessao.update(modelo); //persistindo o modelo
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return 1;
    }    
    
    
    public static List meusModelos(int idUsuario){                
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("meusModelos");
        select.setInteger("idUsuario",idUsuario);
        List modelos = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return modelos;       
    }
    
    
    public static List todosModelos(int idUsuario){                
            
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("todosModelos");
        List modelos = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return modelos;       
    }
    
    
    public static List consultaModelos(String nome){                
            
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("consultaModelos");
        nome = nome + "%";        
        select.setString("nome",nome);
        List modelos = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return modelos;       
    }
    
    
        public static List modelosFilhos(int idModelo){                
            
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("modelosFilhos");
        select.setInteger("idModelo",idModelo);
        List modelos = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return modelos;       
    }
    
    
    public static Modelo obterModelo(String nome){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("buscarModeloPeloNome");
        select.setString("nome", nome);
        
        Modelo modelo = new Modelo();        
        modelo = (Modelo)select.uniqueResult();       
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return modelo;           
    }
    
    public static String obterUsuario(String nome){                
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("buscarModeloPeloNome");
        select.setString("nome", nome);
        
        Modelo modelo = new Modelo();        
        modelo = (Modelo)select.uniqueResult(); 
        //System.out.println(nome + "\n\n");
        String usuarioLogin = modelo.getUsuario().getLogin();
                
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return usuarioLogin;           
    }
    
        public static String obterNomeModeloPai(String nome){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        String nomePai = "";
        
        Query select = sessao.getNamedQuery("buscarModeloPeloNome");
        select.setString("nome", nome);
        
        Modelo modelo = new Modelo();
        modelo = (Modelo)select.uniqueResult();
        if(modelo.getModeloPai()!=null)
            nomePai = modelo.getModeloPai().getNome();
      
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao           
        return nomePai;           
    }
    
    public static int excluirModelo(Modelo modelo){
        
        List textos=null;
        List monitores=null;
        List agentes=null;
        List variaveis=null;
        List plots=null;
        List modelosFilhos = null;
        List comps = null;
        
        modelosFilhos = AplCadastrarModelo.modelosFilhos(modelo.getIdModelo().intValue());
        if(modelosFilhos.isEmpty()){        
            Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
            Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
            //deletando textos do modelo
            textos = AplCadastrarMonitorTexto.textosDoModelo(modelo.getIdModelo().intValue());
            for(int i=0; i < textos.size(); i++){
                Texto texto = (Texto)textos.get(i);
                AplCadastrarMonitorTexto.excluirTexto(texto);
            }
        
            //deletando os monitores do modelo
            monitores = AplCadastrarMonitorTexto.monitoresDoModelo(modelo.getIdModelo().intValue());
            for(int i=0; i < monitores.size(); i++){
                 Monitor monitor = (Monitor)monitores.get(i);
                 AplCadastrarMonitorTexto.excluirMonitor(monitor);
            }
            
            //deletando as composicoes do modelo
            comps = AplCadastrarComposicao.composicoesDoModelo(modelo.getIdModelo().intValue());
            for(int i=0; i < comps.size(); i++){
                Composicao c = (Composicao)comps.get(i);
                AplCadastrarComposicao.excluirComposicao(c);
            }
        
            //deletando as variaveis do modelo
            variaveis = AplCadastrarVariavel.variaveisDoModelo(modelo.getIdModelo().intValue());
            for(int i=0; i < variaveis.size(); i++){
                 Variavel variavel = (Variavel)variaveis.get(i);
                 AplCadastrarVariavel.excluirVariavel(variavel);
            }
        
            //deletando os plots do modelo
            plots = AplCadastrarPlot.plotsDoModelo(modelo.getIdModelo().intValue());
            for(int i=0; i < plots.size(); i++){
                 Plot plot = (Plot)plots.get(i);
                 AplCadastrarPlot.excluirPlot(plot);
            }
        
             //deletando os agentes do modelo
            agentes = AplCadastrarAgente.agentesDoModelo(modelo.getIdModelo().intValue());
            for(int i=0; i < agentes.size(); i++){
                 Agente agente = (Agente)agentes.get(i);
                 AplCadastrarAgente.excluirAgente(agente);
            }
            
            //deletando as analises de graficos do modelo
            APICadastrarAnaliseGraficos.excluirTodasAnalisesDoModelo(modelo);
            
            sessao.delete(modelo);
            transacao.commit();
            sessao.close();        
            return 1;
        }
        else
            return 0;
    }    
    
    private static String buscarNomeVar (AgenteMovel agenteMovel)
    {
        Session s = HibernateUtility.getSession();
        Transaction t = s.beginTransaction();
        
        Query q = s.createQuery("from Agente m where m.nome = :c and idModelo = :d");
        q.setParameter("c", agenteMovel.getNome());
        q.setParameter("d", agenteMovel.getModelo().getIdModelo());
       
        Agente ag = (Agente) q.list().get(0);
        VariavelSlider v = ((AgenteMovel) ag).getVariavelSlider();
        
        String x = v.getNome();
        
        t.commit();
        s.close();
        return x;
    }
    
    //Atualiza referencias de agentes e variaveis em nova versao de modelo
    //Paramentros: agente do modelo pai e modelo filho
    private static void atualizaVariaveis (Agente agente, Modelo modelo)
    {
        Session s = HibernateUtility.getSession();
        Transaction t = s.beginTransaction();
        
        //Pegando novo agente
        Query consulta = s.getNamedQuery("buscarAgentePeloNome_Modelo");
        consulta.setParameter("nome", agente.getNome());
        consulta.setParameter("idModelo", modelo.getIdModelo());
        
        if (consulta.list().size() > 0)
        {
            Agente novoAgente = (Agente) consulta.list().get(0);
                        
            //Atualizando referencia de variaveis locais
            consulta = s.getNamedQuery("BuscarVariaveisDoModelo");
            consulta.setParameter("idModelo",modelo.getIdModelo());
            if (consulta.list().size() > 0)
            {
               List variaveis = consulta.list();
               Iterator i = variaveis.iterator();
               while (i.hasNext())
               {
                   Variavel var = (Variavel) i.next();
                   if (var.getAgente() != null && 
                           var.getAgente().getNome().equals(novoAgente.getNome()))
                   {                    
                       var.setAgente(novoAgente);
                       novoAgente.getVariaveis().add(var);
                   }
               }
            
               //atualizando referencia de quantidade inicial
               if (novoAgente.getTipo().equals("Movel"))
               {
                   AgenteMovel agMovel = (AgenteMovel) novoAgente;
                   if (agMovel.getTipoQtdInicial().equals("Var"))
                   {
                       VariavelSlider varSlider = agMovel.getVariavelSlider();
                       i = variaveis.iterator();
                       while (i.hasNext())
                       {
                           Variavel var = (Variavel) i.next();
                           if (var.getNome().equals(varSlider.getNome()))
                               agMovel.setVariavelSlider((VariavelSlider) var);
                       }
                   }
               }
               s.update(novoAgente);
            }
        }
       
        t.commit();
        s.close();
    }
}