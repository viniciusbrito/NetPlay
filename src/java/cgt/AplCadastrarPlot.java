/*
 * AplCadastrarPlot.java
 *
 * Created on 2 de Maio de 2006, 21:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cgt;

import cdp.*;
import java.util.*;

import utilitario.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;


/**
 *
 * @author Alexandre Andrade
 */
public class AplCadastrarPlot {
    
    /** Creates a new instance of AplCadastrarPlot */
    public AplCadastrarPlot() {
    }
    
    public static int incluirNovoPlot(String nome,String tipo,String nomeEixoX,String nomeEixoY,float xMinimo,float xMaximo,float yMinimo,float yMaximo,List variaveis,List cores,Modelo modelo){
            
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("BuscarPlotDoModelo");
        select.setInteger("idModelo",modelo.getIdModelo().intValue());
        select.setString("nome",nome);
            
        List objetos = select.list();
        if(objetos.size()>0) // Verificando se jah existe um texto com o mesmo nome num determinado modelo
        {
            transacao.commit(); //Finalizando a transacao
            sessao.close(); //Fechando a sessao
            return -1;
        }
        
        Plot plot = new Plot();
        plot.setNomeGrafico(nome);
        plot.setTipo(tipo);
        plot.setNomeEixoX(nomeEixoX);
        plot.setNomeEixoY(nomeEixoY);
        plot.setXMaximo(xMaximo);
        plot.setXMinimo(xMinimo);
        plot.setYMaximo(yMaximo);
        plot.setYMinimo(yMinimo);                      
        plot.setModelo(modelo);
        sessao.save(plot); // persistindo o objeto
        
        if(variaveis!=null){
            for(int i=0 ; i < variaveis.size(); i++){
                CorVariavelPlot cvp = new CorVariavelPlot();
                String cor = (String)cores.get(i);
                String nomeVariavel = (String)variaveis.get(i);
                VariavelValor  variavelValor;
                variavelValor = (VariavelValor)AplCadastrarVariavel.obterVariavel(modelo.getIdModelo().intValue(),nomeVariavel);
                
                cvp.setPlot(plot);
                cvp.setvariavelvalor(variavelValor);
                cvp.setCor(cor);
                sessao.save(cvp);
            }
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return 1;     
    }
    
        public static int alterarDadosPlot(Plot plot,Modelo modelo,String nome,String tipo,String nomeEixoX,String nomeEixoY,float xMinimo,float xMaximo,float yMinimo,float yMaximo,List variaveis,List cores){ 
    
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        List cvpAntigas = null;
        
        if(plot.getNomeGrafico().compareTo(nome)!=0) //se ele alterou nome, ve se nome ja existe para esse modelo
        {
            Query select = sessao.getNamedQuery("BuscarPlotDoModelo");
            select.setInteger("idModelo",modelo.getIdModelo().intValue());
            select.setString("nome",nome);
                   
            List objetos = select.list();
            if(objetos.size()>0) // Verificando se jah existe um texto com o mesmo nome num determinado modelo
            {
                transacao.commit(); //Finalizando a transacao
                sessao.close(); //Fechando a sessao
                return -1;
            }
        }
        
        plot.setNomeGrafico(nome);
        plot.setTipo(tipo);
        plot.setNomeEixoX(nomeEixoX);
        plot.setNomeEixoY(nomeEixoY);
        plot.setXMaximo(xMaximo);
        plot.setXMinimo(xMinimo);
        plot.setYMaximo(yMaximo);
        plot.setYMinimo(yMinimo);                      
        plot.setModelo(modelo);
        sessao.update(plot); // persistindo o objeto
                
        //apagando cores antigas
        cvpAntigas = AplCadastrarPlot.variaveisDoPlot(plot.getIdPlot().intValue());
             for(int i=0 ; i < cvpAntigas.size(); i++){
                 CorVariavelPlot cvp = (CorVariavelPlot)cvpAntigas.get(i);
                 sessao.delete(cvp);
             }
            
        for(int i=0 ; i < variaveis.size(); i++){
            CorVariavelPlot cvp = new CorVariavelPlot();
            String cor = (String)cores.get(i);
            String nomeVariavel = (String)variaveis.get(i);
            VariavelValor  variavelValor;
            variavelValor = (VariavelValor)AplCadastrarVariavel.obterVariavel(modelo.getIdModelo().intValue(),nomeVariavel);
            
            cvp.setPlot(plot);
            cvp.setvariavelvalor(variavelValor);
            cvp.setCor(cor);
            sessao.save(cvp);
        }        
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao                
        return 1;
    }
        
    
    public static List plotsDoModelo(int idModelo){
    
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarPlotsDoModelo");
        select.setInteger("idModelo",idModelo);
        List plots = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return plots; 
    }
    
    
    public static Plot obterPlot(int idModelo, String nome){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarPlotDoModelo");
        select.setInteger("idModelo",idModelo);
        select.setString("nome",nome);
        
        Plot plot = new Plot();
        plot=(Plot)select.uniqueResult(); 
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return plot;
    }  
    
    
    public static List corVariavelPlot(int idVariavel){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("VariavelGraficos");
        select.setInteger("idVariavel",idVariavel);
        List corVariavelPlot = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return corVariavelPlot;
    }
    
    
    public static List variaveisDoPlot(int idPlot){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("CoresVariaveisGrafico");
        select.setInteger("idPlot",idPlot);
        List corVariavelPlot = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return corVariavelPlot;
    }          
    
    
    public static void excluirPlot(Plot plot){
      
          Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
          Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
          
          List cvps = AplCadastrarPlot.variaveisDoPlot(plot.getIdPlot().intValue());
              for(int i=0; i< cvps.size(); i++){
                  CorVariavelPlot cvp = (CorVariavelPlot)cvps.get(i);
                  sessao.delete(cvp);
              }
                                       
          sessao.delete(plot);
          transacao.commit();
          sessao.close();
      }    
}
