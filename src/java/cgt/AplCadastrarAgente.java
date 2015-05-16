/*
 * AplCadastrarAgente.java
 *
 * Created on 2 de Maio de 2006, 21:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cgt;

import cdp.*;
import java.util.*;
import org.hibernate.sql.QuerySelect;

import utilitario.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;


/**
 *
 * @author Alexandre Andrade
 */
public class AplCadastrarAgente {
    
    /** Creates a new instance of AplCadastrarAgente */
    public AplCadastrarAgente() {
    }
    
     public static int incluirNovoAgenteEstAleatorio(String nome,String cor, String tipo,String tipoPosicao,float porcentagem,List posicoes,Modelo modelo){
    
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
                       
        validacao = validaNomeAgente(nome,modelo,sessao);
        
        if(validacao == 1)
        {
            AgenteEstAleatorio agente = new AgenteEstAleatorio();
            agente.setNome(nome);
            agente.setCor(cor);
            agente.setTipo(tipo);
            agente.setTipoPosicao(tipoPosicao);
            agente.setPorcentagem(porcentagem);
            agente.setModelo(modelo);  
            sessao.save(agente); // persistindo o objeto
            if(posicoes != null){
                for(int i=0 ; i < posicoes.size(); i++){
                    PosicaoInicial pos = (PosicaoInicial)posicoes.get(i);
                    pos.setAgente(agente);
                    sessao.save(pos);
                }                 
            }
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return validacao;     
    }
    
     public static int incluirNovoAgenteEstBorda(String nome,String cor,String tipo,Integer tamanho,Modelo modelo){
    
        int validacao = 1;
        List agentes = null;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
       
        validacao = validaNomeAgente(nome,modelo,sessao);
        
        //verificando se ja existe algum agente borda com o mesmo tamanho de borda
        agentes = agentesDoModelo(modelo.getIdModelo().intValue());
        for(int i=0; i< agentes.size(); i++){
            Agente agenteAux = (Agente)agentes.get(i);
            if(agenteAux.getTipo().compareTo("Estacionario Borda")==0){
                AgenteEstBorda agenteEstBorda = (AgenteEstBorda)agenteAux;
                if(agenteEstBorda.getTamanho().compareTo(tamanho)==0)
                    return -2;
            }                  
        }
        
        if(validacao == 1){
            AgenteEstBorda agente = new AgenteEstBorda();
            agente.setNome(nome);
            agente.setCor(cor);
            agente.setTipo(tipo);
            agente.setTamanho(tamanho);
            agente.setModelo(modelo);  
            
            sessao.save(agente); // persistindo o objeto
         }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao          
        return validacao;     
    }
     
     
    public static int incluirNovoAgenteMovel(String nome,String cor,String tipo,String tipoPosicao,Integer quantidadeInicial,String forma,Integer tamanho,String tipoMovimento,Integer passo,float angulo1,float angulo2,String tipoQtdInicial,String nomeVariavel,int idVariavel,List posicoes,Modelo modelo){ 
        int validacao = 1; 
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
                       
       validacao = validaNomeAgente(nome,modelo,sessao);
       
        if(validacao == 1){
           AgenteMovel agente = new AgenteMovel();
           agente.setNome(nome);
           agente.setCor(cor);
           agente.setTipo(tipo);
           agente.setTipoPosicao(tipoPosicao);
           agente.setQuantidadeInicial(quantidadeInicial);
           agente.setForma(forma);
           agente.setTamanho(tamanho);
           agente.setTipoMovimento(tipoMovimento);
           agente.setPasso(passo);
           agente.setAngulo1(angulo1);
           agente.setAngulo2(angulo2);
           agente.setModelo(modelo);  
           
           if (tipoQtdInicial.equals("0") || tipoQtdInicial.equals("Num")) //Se qtdInicial for numero
               tipoQtdInicial = "Num";
           else
               tipoQtdInicial = "Var";
           agente.setTipoQtdInicial(tipoQtdInicial);
           
           //Se quantidade inicial for igual ao valor de uma variavel slider
           if(tipoQtdInicial.equals("Var")) //Se for o valor de var slider
           {
               if (nomeVariavel != null)
               {
                   Query select = sessao.getNamedQuery("BuscarVariavelDoModelo");
                   select.setInteger("idModelo",modelo.getIdModelo());
                   select.setString("nome",nomeVariavel);
                   Variavel var = (Variavel) select.list().get(0);
                   agente.setVariavelSlider((VariavelSlider) var);
               }
               else
               {
                   Query select = sessao.getNamedQuery("BuscarVariavelPeloId");
                   select.setInteger("idVariavel",idVariavel);
                   Variavel var = (Variavel) select.list().get(0);
                   agente.setVariavelSlider((VariavelSlider) var);
               }
           }
                
           sessao.save(agente); // persistindo o objeto
           if(posicoes != null){
               for(int i=0 ; i < posicoes.size(); i++){
                   PosicaoInicial pos = (PosicaoInicial)posicoes.get(i);
                   pos.setAgente(agente);
                   sessao.save(pos);
               }
           }
        }
       
       transacao.commit(); //Finalizando a transacao
       sessao.close(); //Fechando a sessao       
       return validacao;     
    }
     
   
     public static int alterarDadosAgenteEstAleatorio(AgenteEstAleatorio agente,Modelo modelo,String nome,String cor,String tipo,String tipoPosicao,float porcentagem, List posicoes){
    
         int validacao = 1;
         List posicoesAntigas = null;
         Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
         Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
         if(agente.getNome().compareTo(nome)!=0) //se ele alterou nome, ve se nome ja existe para esse modelo
             validacao = validaNomeAgente(nome,modelo,sessao);
             
         if(validacao != -1){     //agente pode ser alterado
             agente.setNome(nome);
             agente.setCor(cor);
             agente.setTipo(tipo);
             agente.setTipoPosicao(tipoPosicao);
             agente.setPorcentagem(porcentagem);
             agente.setModelo(modelo);  
             sessao.update(agente);             
             
             //apagando posicoes antigas
             posicoesAntigas = AplCadastrarAgente.posicoesAgente(agente.getIdAgente().intValue());
             for(int i=0 ; i < posicoesAntigas.size(); i++){
                 PosicaoInicial pos = (PosicaoInicial)posicoesAntigas.get(i);
                 sessao.delete(pos);
             }
             //Inserindo as novas posicoes
             for(int i=0 ; i < posicoes.size(); i++){
                 PosicaoInicial pos = (PosicaoInicial)posicoes.get(i);
                 pos.setAgente(agente);
                 sessao.save(pos);
             }          
         }
       
         transacao.commit(); //Finalizando a transacao
         sessao.close(); //Fechando a sessao
         return validacao;
     }

     
     public static int alterarDadosAgenteEstBorda(AgenteEstBorda agente,Modelo modelo,String nome,String cor,String tipo,Integer tamanho){
         
         int validacao = 1;
         Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
         Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
         List agentes = null;
        
         if(agente.getNome().compareTo(nome)!=0) //se ele alterou nome, ve se nome ja existe para esse modelo
             validacao = validaNomeAgente(nome,modelo,sessao);
         
         //verificando se ja existe algum agente borda com o mesmo tamanho de borda
         agentes = agentesDoModelo(modelo.getIdModelo().intValue());
         for(int i=0; i< agentes.size(); i++){
             Agente agenteAux = (Agente)agentes.get(i);
             if(agenteAux.getTipo().compareTo("Estacionario Borda")==0){
                 AgenteEstBorda agenteEstBorda = (AgenteEstBorda)agenteAux;
                 if(agenteEstBorda.getIdAgente().intValue() != agente.getIdAgente().intValue())
                     if(agenteEstBorda.getTamanho().compareTo(tamanho)==0)
                         return -2;
             }                  
         }
             
         if(validacao != -1){     //agente pode ser alterado
             agente.setNome(nome);
             agente.setCor(cor);
             agente.setTipo(tipo);
             agente.setTamanho(tamanho);
             agente.setModelo(modelo);  
                    
             sessao.update(agente);
         }
       
         transacao.commit(); //Finalizando a transacao
         sessao.close(); //Fechando a sessao
         return validacao;
     }
     
    
public static int alterarDadosAgenteMovel(AgenteMovel agente,Modelo modelo,String nome,String cor,String tipo,String tipoPosicao,Integer quantidadeInicial,String forma,Integer tamanho,String tipoMovimento,Integer passo,float angulo1,float angulo2,List posicoes,String tipoQtdInicial,Integer idVariavel){
        
          int validacao = 1;
          List posicoesAntigas = null;
          Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
          Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
          if(agente.getNome().compareTo(nome)!=0) //se ele alterou nome, ve se nome ja existe para esse modelo
              validacao = validaNomeAgente(nome,modelo,sessao);
             
          if(validacao != -1){     //agente pode ser alterado
              agente.setNome(nome);
              agente.setCor(cor);
              agente.setTipo(tipo);
              agente.setTipoPosicao(tipoPosicao);
              agente.setQuantidadeInicial(quantidadeInicial);
              agente.setForma(forma);
              agente.setTamanho(tamanho);
              agente.setTipoMovimento(tipoMovimento);
              agente.setPasso(passo);
              agente.setAngulo1(angulo1);
              agente.setAngulo2(angulo2);
              agente.setModelo(modelo); 
              
              if (tipoQtdInicial.equals("0")) //Se qtdInicial for numero
               tipoQtdInicial = "Num";
              else
                  tipoQtdInicial = "Var";
              agente.setTipoQtdInicial(tipoQtdInicial);
           
              //Se quantidade inicial for igual ao valor de uma variavel slider
              if(tipoQtdInicial.equals("Var")) //Se for o valor de var slider
              {
                  Query select = sessao.getNamedQuery("BuscarVariavelPeloId");
                  select.setInteger("idVariavel",idVariavel);
                  Variavel var = (Variavel) select.list().get(0);
                  agente.setVariavelSlider((VariavelSlider) var);
              }
                    
              sessao.update(agente);
             
              if (posicoes != null)
              {
                  //apagando posicoes antigas
                  posicoesAntigas = AplCadastrarAgente.posicoesAgente(agente.getIdAgente().intValue());
                  for(int i=0 ; i < posicoesAntigas.size(); i++){
                      PosicaoInicial pos = (PosicaoInicial)posicoesAntigas.get(i);
                      sessao.delete(pos);
                  }
                  //Inserindo as novas posicoes
                  for(int i=0 ; i < posicoes.size(); i++){
                      PosicaoInicial pos = (PosicaoInicial)posicoes.get(i);
                      pos.setAgente(agente);
                      sessao.save(pos);
                  }  
              }
          }
         
          transacao.commit(); //Finalizando a transacao
          sessao.close(); //Fechando a sessao
          return validacao;
      }
     
      
     public static int validaNomeAgente(String nome,Modelo modelo,Session sessao){     
         
         //verifica se nome tem somente letras e numeros
         if(!verificaNome(nome))
             return 0;
         
         Query select = sessao.getNamedQuery("buscarAgentePeloNome_Modelo");
         select.setString("nome",nome);
         select.setInteger("idModelo",modelo.getIdModelo().intValue());                  
     
         List objetos = select.list();
         if(objetos.size()>0) // Verificando se jah existe um agente com o mesmo nome num determinado modelo
             return -1;
         
         return 1;
     }
     
     
     public static List agentesDoModelo(int idModelo){
     
         Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
         Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
         Query select = sessao.getNamedQuery("BuscarAgentesDoModelo");
         select.setInteger("idModelo",idModelo);
         List agentes = select.list(); 
         
         transacao.commit(); //Finalizando a transacao
         sessao.close(); //Fechando a sessao
         
         return agentes; 
    }
     
     
     public static Agente obterAgente(int idAgente){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarAgentePeloId");
        select.setInteger("idAgente",idAgente);
        
        Agente agente = new Agente();
        agente = (Agente)select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return agente;           
    }
     
     
     public static Agente obterAgente(int idModelo, String nome){
         
         Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
         Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
         
         Query select = sessao.getNamedQuery("BuscarAgenteDoModelo");
         select.setInteger("idModelo",idModelo);
         select.setString("nome",nome);
        
         Agente agente = new Agente();
         agente = (Agente)select.uniqueResult();
        
         transacao.commit(); //Finalizando a transacao
         sessao.close(); //Fechando a sessao
         
         return agente;           
    }
     
     
     public static Agente obterAgenteVariavel(int idModelo,String nome){
        
          Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
          Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
          Agente agente = new Agente();
          Variavel variavel = new Variavel();
                                     
          Query select = sessao.getNamedQuery("BuscarVariavelDoModelo");       
          select.setInteger("idModelo",idModelo);
          select.setString("nome",nome);
        
          variavel=(Variavel)select.uniqueResult(); 
          agente = variavel.getAgente();                    
          
          transacao.commit(); //Finalizando a transacao
          sessao.close(); //Fechando a sessao
          return agente;
      }    
     
     
     public static List posicoesAgente(int idAgente){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("posicoesDoAgente");
        select.setInteger("idAgente",idAgente);
        List posicoesAgente = select.list();
        
        //quicksort(0, posicoesAgente.size() - 1, posicoesAgente);
         
         transacao.commit(); //Finalizando a transacao
         sessao.close(); //Fechando a sessao
         
         return posicoesAgente;              
    }

     
     public static int excluirAgente(Agente agente){       
         Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
         Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
               
         //buscando variaveis do agente
         Query select = sessao.getNamedQuery("BuscarVariaveisDoAgente");
         select.setInteger("idAgente",agente.getIdAgente().intValue());
        
         List variaveis = select.list(); 
         if(variaveis.isEmpty() && 
                 !AplCadastrarComposicao.agentePresenteComposicoes(agente)){
             List posicoesAgente = AplCadastrarAgente.posicoesAgente(agente.getIdAgente().intValue());
             for(int i=0; i< posicoesAgente.size(); i++){
                  PosicaoInicial pi = (PosicaoInicial)posicoesAgente.get(i);
                  sessao.delete(pi);
              }     
             sessao.delete(agente);
             
             transacao.commit();             
             
             sessao.close();
             return 1;
         }
         
         transacao.commit();
         sessao.close();
         return 0;
    }
     
     //Verifica se nome contem soh letras e numeros
     public static boolean verificaNome(String nome)
     {
         for(int i=0; i<nome.length(); i++)
         {
             char caracter = nome.charAt(i);
             //tem que comecar com letra
             if(i == 0)
             {
                 if (!((caracter >= 'A' && caracter <= 'Z') ||
                     (caracter >= 'a' && caracter <= 'z')))
                        return false;             
             }
             else
             {
                 if (!((caracter >= 'A' && caracter <= 'Z') ||
                         (caracter >= 'a' && caracter <= 'z') ||
                         (caracter >= '0' && caracter <= '9') ||
                         (caracter == '?')))
                     return false;
             }
         }
         return true;
     }
     
     //Quicksort
     public static void quicksort(int p, int q, List array)
     {
         if (p < q){
             int x = particao(p, q, array);
             quicksort(p, x - 1, array);
             quicksort(x + 1, q, array);
         }
     }
     
     public static int particao(int p, int q, List array)
     {
         int j = p - 1;
         PosicaoInicial aux1 = (PosicaoInicial) array.get(q);
         for (int i = p; i <= q; i++)
         {
             PosicaoInicial aux2 = (PosicaoInicial) array.get(i);
             if ((aux1.getYInicial() > aux2.getYInicial()) ||
                     (aux2.getYInicial() == aux1.getYInicial() && aux2.getXInicial() < aux1.getXInicial()));
             //if (array.get(i) <= aux) 
                 troca(array, i, ++j);
         }
         return j;
     }
     
     public static void troca(List array, int i, int j)
     {
         PosicaoInicial aux1 = (PosicaoInicial) array.get(i);
         PosicaoInicial aux2 = (PosicaoInicial) array.get(j);
         array.set(i, aux2);
         array.set(j, aux1);
         
         
         //int aux = array[i];
         //array[i] = array[j];
         //array[j] = aux;
     }

    public static int inicializarAgente(Agente agente, int quantidade, Modelo modelo)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        InicializarAgente ia = new InicializarAgente();
        ia.setAgente(agente);
        ia.setQuantidade(quantidade);
        ia.setModelo(modelo);
        ia.setCor(agente.getCor());
        ia.setForma(AgenteMovel.buscarAgente(agente.getIdAgente()).getForma());
        ia.setTamanho(AgenteMovel.buscarAgente(agente.getIdAgente()).getTamanho());
        try
        {
            sessao.save(ia);
            int id = ia.getIdInicializarAgente();
            transacao.commit(); //Finalizando a transacao
            sessao.close();
            
            //inserindo as variaveis do agente na tabela de inicializacao
            List variaveis = AplCadastrarVariavel.variaveisDoAgente( agente.getIdAgente() );
            for (Object variavei : variaveis) {
                Variavel var = (Variavel) variavei;
                if (var.getTipo().equals("Valor")) {
                    VariavelValor varValor = (VariavelValor) variavei;
                    inicializarAgenteVar(ia, var, varValor.getValor());
                }
                if (var.getTipo().equals("Logica")) {
                    VariavelLogica varLog = (VariavelLogica) variavei;
                    inicializarAgenteVar(ia, var, varLog.getValorLogico());
                }
            }
            return id;
        }
        catch(org.hibernate.HibernateException e)
        {
            return 0;
        }
    }
    
    public static void inicializarAgenteVar(InicializarAgente ia, Variavel var, String valor)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        InicializarAgenteVariaveis iav = new InicializarAgenteVariaveis();
        iav.setInicializarAgente(ia);
        iav.setVariavel(var);
        iav.setValor(valor);
        sessao.save(iav);        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
    }
    
    public static void inicializarAgenteVar(InicializarAgente ia, Variavel var, Boolean valor)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        InicializarAgenteVariaveis iav = new InicializarAgenteVariaveis();
        iav.setInicializarAgente(ia);
        iav.setVariavel(var);
        iav.setValor(valor.toString());
        sessao.save(iav);        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a ses
    }
    
    public static int alterarInicializarAgenteVar(InicializarAgente ia, Variavel var, String valor)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        InicializarAgenteVariaveis iav = InicializarAgenteVariaveis.obterIAV(ia.getIdInicializarAgente(), var.getIdVariavel());
        iav.setInicializarAgente(ia);
        iav.setVariavel(var);
        iav.setValor(valor);
        sessao.update(iav);        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a ses
        return 1;
    }
    
    public static int alterarInicializarAgenteVar(InicializarAgente ia, Variavel var, Boolean valor)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        InicializarAgenteVariaveis iav = InicializarAgenteVariaveis.obterIAV(ia.getIdInicializarAgente(), var.getIdVariavel());
        iav.setInicializarAgente(ia);
        iav.setVariavel(var);
        iav.setValor(valor.toString());
        sessao.update(iav);        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a ses
        return 1;
    }
    
    public static int alterarInicializarAgenteCor(InicializarAgente ia, String cor)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        ia.setCor(cor);
         sessao.update(ia);
        transacao.commit();
        sessao.close();
        return 1;
    }
    
    public static int alterarInicializarAgenteForma(InicializarAgente ia, String forma)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        ia.setForma(forma);
         sessao.update(ia);
        transacao.commit();
        sessao.close();
        return 1;
    }
    
    public static int alterarInicializarAgenteTamanho(InicializarAgente ia, int tamanho)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        ia.setTamanho(tamanho);
         sessao.update(ia);
        transacao.commit();
        sessao.close();
        return 1;
    }
    
    public static int removerIniciarAgente(InicializarAgente ia)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        sessao.delete(ia);
        transacao.commit();
        sessao.close();
        return 1;   
    }
    
    public static int validarQuantidade(int idAgente, int quantidade)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        AgenteMovel agente = new AgenteMovel();
        Query select = sessao.getNamedQuery("BuscarAgenteMovel");       
        select.setInteger("idAgente",idAgente);
        agente = (AgenteMovel)select.uniqueResult(); 
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        switch (agente.getTipoQtdInicial())
        {
            case "Num":
                if(agente.getQuantidadeInicial() >= quantidade)
                    return 1;
                else
                    return 0;
            case "Var":
                VariavelSlider vrs = VariavelSlider.obterVariavelSlider(agente.getVariavelSlider().getIdVariavel());

                if(vrs.getValorInicial() >= quantidade)
                    return 1;
                else
                    return 0;
        }
        return 0;
    }
    
    public static int alterarInicializarAgente(int idInicializar, int quantidade)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        InicializarAgente ia = InicializarAgente.obterIA(idInicializar);
        ia.setQuantidade(quantidade);
        sessao.update(ia);
        transacao.commit();
        sessao.close();
        return 1;
    }
}
   