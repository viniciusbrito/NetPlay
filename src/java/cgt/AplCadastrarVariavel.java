/*
 * AplCadastrarVariavel.java
 *
 * Created on 2 de Maio de 2006, 21:23
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
import java.lang.Object;

/**
 *
 * @author Alexandre Andrade
 * @editado Vinicius Fernandes 28/10/2014
 */
public class AplCadastrarVariavel {
    
    /** Creates a new instance of AplCadastrarVariavel */
    public AplCadastrarVariavel() {
    }
    
    public static int incluirNovaVariavelLogica(String nome,String tipo,boolean valorLogico,Modelo modelo, Agente agente){
    
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
                               
        validacao = validaNomeVariavel(nome,modelo,sessao);
     
        if(validacao == 1)
        {
            VariavelLogica variavel = new VariavelLogica();
            variavel.setNome(nome);
            variavel.setTipo(tipo);
            variavel.setValorLogico(valorLogico);
            System.out.println("AQUI => " + valorLogico + "\n");
            variavel.setModelo(modelo);
            variavel.setAgente(agente);
            
            sessao.save(variavel); // persistindo o objeto
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return validacao;     
    }
    
    
    public static int incluirNovaVariavelSlider(String nome,String tipo,float valorInicial,float valorMinimo,float valorMaximo,float incremento,Modelo modelo, Agente agente){
    
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
                       
        validacao = validaNomeVariavel(nome,modelo,sessao);
        
        if(validacao == 1)
        {
            VariavelSlider variavel = new VariavelSlider();
            variavel.setNome(nome);           
            variavel.setTipo(tipo);
            variavel.setValorInicial(valorInicial);
            variavel.setValorMinimo(valorMinimo);
            variavel.setValorMaximo(valorMaximo);
            variavel.setIncremento(incremento);
            variavel.setModelo(modelo);
            variavel.setAgente(agente);
            
            sessao.save(variavel); // persistindo o objeto
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return validacao;     
    }
    
    
    public static int incluirNovaVariavelValor(String nome,String tipo,String valor,Modelo modelo, Agente agente){
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
                       
        validacao = validaNomeVariavel(nome,modelo,sessao);
        
        if(validacao == 1)
        {
            VariavelValor variavel = new VariavelValor();
            variavel.setNome(nome);
            variavel.setTipo(tipo);
            variavel.setValor(valor);
            variavel.setValorCriacao(valor);
            variavel.setModelo(modelo);
            variavel.setAgente(agente);
            
            sessao.save(variavel); // persistindo o objeto
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return validacao;     
    }
      
    
    public static int alterarDadosVariavelValor(VariavelValor variavel,Modelo modelo,Agente agente,String nome,String tipo,String valor, String valorCriacao){
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao

        if(variavel.getNome().compareTo(nome)!=0) //se ele alterou nome, ve se nome ja existe para esse modelo
             validacao = validaNomeVariavel(nome,modelo,sessao);
        
        if(validacao == 1)
        {
            variavel.setNome(nome);
            variavel.setTipo(tipo);
            variavel.setValor(valor);
            variavel.setValorCriacao(valorCriacao);
            variavel.setModelo(modelo);
            variavel.setAgente(agente);
            
            sessao.update(variavel); // persistindo o objeto
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return validacao;
    }
    
    
    public static int alterarDadosVariavelLogica(VariavelLogica variavel,Modelo modelo, Agente agente,String nome,String tipo,boolean valorLogico){
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao

        if(variavel.getNome().compareTo(nome)!=0) //se ele alterou nome, ve se nome ja existe para esse modelo
             validacao = validaNomeVariavel(nome,modelo,sessao);
        
        if(validacao == 1)
        {
            variavel.setNome(nome);
            variavel.setTipo(tipo);
            variavel.setValorLogico(valorLogico);
            variavel.setModelo(modelo);
            variavel.setAgente(agente);
            
            sessao.update(variavel); // persistindo o objeto
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return validacao;
    }
    
    
    public static int alterarDadosVariavelSlider(VariavelSlider variavel, Modelo modelo, Agente agente, String nome,String tipo,float valorInicial,float valorMinimo,float valorMaximo,float incremento){
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao

        if(variavel.getNome().compareTo(nome)!=0) //se ele alterou nome, ve se nome ja existe para esse modelo
             validacao = validaNomeVariavel(nome,modelo,sessao);
        
        if(validacao == 1)
        {
            variavel.setNome(nome);           
            variavel.setTipo(tipo);
            variavel.setValorInicial(valorInicial);
            variavel.setValorMinimo(valorMinimo);
            variavel.setValorMaximo(valorMaximo);
            variavel.setIncremento(incremento);
            variavel.setModelo(modelo);
            variavel.setAgente(agente);
            
            sessao.update(variavel); // persistindo o objeto
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return validacao;     
    }
    
    
    public static int validaNomeVariavel(String nome,Modelo modelo,Session sessao){
        
        //verifica se nome tem somente letras e numeros
         if(!AplCadastrarAgente.verificaNome(nome))
             return 0;
    
          Query select = sessao.getNamedQuery("buscarVariavelPeloNomeModelo");
          select.setString("nome",nome);
          select.setInteger("idModelo",modelo.getIdModelo().intValue());
      
          List objetos = select.list();
          if(objetos.size()>0) // Verificando se jah existe um agente com o mesmo nome num determinado modelo
              return -1;
          
        return 1;
      }
      
      
      public static List variaveisDoModelo(int idModelo){
      
          Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
          Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
          Query select = sessao.getNamedQuery("BuscarVariaveisDoModelo");
          select.setInteger("idModelo",idModelo);
          List variaveis = select.list();
        
          transacao.commit(); //Finalizando a transacao
          sessao.close(); //Fechando a sessao
        
          return variaveis; 
    }                  
   
      
      public static void excluirVariavel(Variavel variavel){
      
          Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
          Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
          
          //Se variavel for valor apaga ela do grafico
          if(variavel.getTipo().compareTo("Valor")==0){
              List cvps = AplCadastrarPlot.corVariavelPlot(variavel.getIdVariavel().intValue());
              for(int i=0; i< cvps.size(); i++){
                  CorVariavelPlot cvp = (CorVariavelPlot)cvps.get(i);
                  sessao.delete(cvp);
              }
          }
          
          if(variavel.getTipo().equals("Slider"))
          {
              eliminarVarAgentes(variavel.getIdVariavel());
          }
                                       
          sessao.delete(variavel);
          transacao.commit();
          sessao.close();
      }
      
      
      public static int estahGrafico(VariavelValor variavel){
          
          Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
          Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
          
          Query select = sessao.getNamedQuery("VariavelGraficos");       
          select.setInteger("idVariavel",variavel.getIdVariavel().intValue());
          List variavelGraficos = select.list();
          transacao.commit();
          sessao.close();
          
          if(!variavelGraficos.isEmpty())
              return -1;
          
          return 1;    
      }
      
      public static Variavel obterVariavel(int idVariavel){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarVariavelPeloId");
        select.setInteger("idVariavel",idVariavel);
        
        Variavel v = new Variavel();
        v = (Variavel)select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return v;
        
      } 
  
      public static Variavel obterVariavel(int idModelo,String nome){
        
          Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
          Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
          
          Query select = sessao.getNamedQuery("BuscarVariavelDoModelo");       
          select.setInteger("idModelo",idModelo);
          select.setString("nome",nome);
        
          Variavel variavel = new Variavel();
          variavel=(Variavel)select.uniqueResult(); 
                       
          transacao.commit(); //Finalizando a transacao
          sessao.close(); //Fechando a sessao
          
          return variavel;
      }     
      
         
      public static String obterNomeAgenteVariavel(int idModelo,String nome){
        
          Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
          Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
          Agente agente = new Agente();
          String nomeAgente="";
          Variavel variavel = new Variavel();
                                     
          Query select = sessao.getNamedQuery("BuscarVariavelDoModelo");       
          select.setInteger("idModelo",idModelo);
          select.setString("nome",nome);
        
          variavel=(Variavel)select.uniqueResult(); 
          agente = variavel.getAgente();                    
          if(agente!=null)        
              nomeAgente = agente.getNome();
         
          transacao.commit(); //Finalizando a transacao
          sessao.close(); //Fechando a sessao
          return nomeAgente;
      }           
      
      
      public static String obterNomeVariavelPlot(int idCorVariavelPlot){
        
          Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
          Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
          
          VariavelValor variavel =  new VariavelValor();
          CorVariavelPlot cvp = new CorVariavelPlot();
          String nome = "";
                                     
          Query select = sessao.getNamedQuery("ObterCorVariavelPlot");       
          select.setInteger("idCorVariavelPlot",idCorVariavelPlot);
          cvp = (CorVariavelPlot)select.uniqueResult();
          
          nome = cvp.getvariavelvalor().getNome();        
                      
          transacao.commit(); //Finalizando a transacao
          sessao.close(); //Fechando a sessao
          return nome;
      }    
      
      
      public static List variaveisDoModeloPeloTipo(int idModelo, String tipo){
      
          Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
          Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
          Query select = sessao.getNamedQuery("BuscarVariaveisDoModeloPeloTipo");
          select.setInteger("idModelo",idModelo);
          select.setString("tipo",tipo);
          List variaveis = select.list();
        
          transacao.commit(); //Finalizando a transacao
          sessao.close(); //Fechando a sessao
        
          return variaveis; 
    } 
      
    public static List variaveisDoAgente(int idAgente){
      
          Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
          Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
          Query select = sessao.getNamedQuery("BuscarVariaveisDoAgente");
          select.setInteger("idAgente",idAgente);
          List variaveis = select.list();
        
          transacao.commit(); //Finalizando a transacao
          sessao.close(); //Fechando a sessao
        
          return variaveis; 
    } 
      
    public static List variaveisSlidersGlobaisDoModelo (int idModelo) {
        List variaveis = variaveisDoModeloPeloTipo (idModelo, "Slider");
        
        List variaveisGlobais = new ArrayList();
        Iterator iterator = variaveis.iterator();
        while (iterator.hasNext())
        {
            Variavel var = (Variavel) iterator.next();
            if (var.getAgente() == null)
                variaveisGlobais.add(var);
        }
        
        return variaveisGlobais;
    }
    
    private static void eliminarVarAgentes (Integer idVariavel)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        
        Query select = sessao.getNamedQuery("BuscarVariavelPeloId");
        select.setParameter("idVariavel", idVariavel);
        Variavel var = (Variavel) select.list().get(0);
        
        Set agentes = var.getModelo().getAgentes();
        
        Iterator i = agentes.iterator();
        while(i.hasNext())
        {
            Agente agente = (Agente) i.next();
            if (agente.getTipo().equals("Movel"))
            {
                //AgenteMovel ag = (AgenteMovel) AplCadastrarAgente.
                //        obterAgente(agente.getIdAgente());
                
                select = sessao.getNamedQuery("BuscarAgentePeloId");
                select.setParameter("idAgente", agente.getIdAgente());
                Agente ag2 = (Agente) select.list().get(0);
                AgenteMovel ag = (AgenteMovel) ag2;
                
                if (ag.getTipoQtdInicial().equals("Var") &&
                        ag.getVariavelSlider().getNome().equals(var.getNome()))
                {
                    ag.setTipoQtdInicial("Num");
                    ag.setVariavelSlider(null);
                    ag.setQuantidadeInicial(0);
                }                      
            }
        }
        
        transacao.commit();
        sessao.close();
    }
}