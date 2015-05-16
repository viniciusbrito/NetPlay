/*
 * AplCadastrarMonitorTexto.java
 *
 * Created on 1 de Maio de 2006, 10:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cgt;

import cdp.Modelo;
import cdp.Monitor;
import cdp.Texto;
import java.util.*;
import java.util.Iterator;
import java.util.Set;

import utilitario.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;

/**
 *
 * @author Alexandre Andrade
 */
public class AplCadastrarMonitorTexto {
    
    /** Creates a new instance of AplCadastrarMonitorTexto */
    public AplCadastrarMonitorTexto() {
    }
    
    
    public static int incluirNovoTexto(String nome,String texto, Modelo modelo){
    
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
                       
        Query select = sessao.getNamedQuery("TextosDoModelo");
        select.setInteger("idModelo",modelo.getIdModelo().intValue());
        select.setString("nome",nome);
            
        List objetos = select.list();
        if(objetos.size()>0) // Verificando se jah existe um texto com o mesmo nome num determinado modelo
        {
            transacao.commit(); //Finalizando a transacao
            sessao.close(); //Fechando a sessao
            return -1;
        }
                             
        Texto text = new Texto();
        text.setNome(nome);
        text.setTexto(texto);
        text.setModelo(modelo);
                
        sessao.save(text); // persistindo o objeto
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return 1;     
    }
  
   
    public static int alterarDadosTexto(Texto text,Modelo modelo,String nome,String texto){ 
    
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        if(text.getNome().compareTo(nome)!=0) //se ele alterou nome, ve se nome ja existe para esse modelo
        {
            Query select = sessao.getNamedQuery("TextosDoModelo");
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
        
        text.setTexto(texto);
        text.setNome(nome);
        text.setModelo(modelo);
          
        sessao.update(text); 
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return 1;
    }    
     
    
    public static void excluirTexto(Texto texto){
    
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        sessao.delete(texto);
        transacao.commit();
        sessao.close();
    }
   
    
    public static int incluirNovoMonitor(String nome,String expressao,Integer casasDecimais, Modelo modelo){
    
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("MonitoresDoModelo");
        select.setInteger("idModelo",modelo.getIdModelo().intValue());
        select.setString("nome", nome);
                
        List objetos = select.list();
        if(objetos.size()>0) // Verificando se existe um monitor com esse mesmo nome em um determinado modelo
        {
            transacao.commit(); //Finalizando a transacao
            sessao.close(); //Fechando a sessao
            return -1;
        }
        
        Monitor monitor = new Monitor();
        monitor.setNome(nome);
        monitor.setExpressao(expressao);
        monitor.setCasasDecimais(casasDecimais);
        monitor.setModelo(modelo);
        
        sessao.save(monitor); // persistindo o objeto
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return 1;     
    }
    
    
    public static int alterarDadosMonitor(Monitor monitor,Modelo modelo,String nome,String expressao,Integer casasDecimais){ 
    
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        if(monitor.getNome().compareTo(nome)!=0) //se ele alterou nome, ve se nome ja existe para esse modelo
        {
            Query select = sessao.getNamedQuery("MonitoresDoModelo");
            select.setInteger("idModelo",modelo.getIdModelo().intValue());
            select.setString("nome", nome);
        
            List objetos = select.list();
            if(objetos.size()>0) // Verificando se jah existe um monitor com o mesmo nome num determinado modelo
            {
                transacao.commit(); //Finalizando a transacao
                sessao.close(); //Fechando a sessao
                return -1;
            }
        }
        
        monitor.setNome(nome);
        monitor.setExpressao(expressao);
        monitor.setCasasDecimais(casasDecimais);
        monitor.setModelo(modelo);
          
        sessao.update(monitor); 
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return 1;
    }    

    
    public static void excluirMonitor(Monitor monitor){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        sessao.delete(monitor);
        transacao.commit();
        sessao.close();
    }
    
    
    public static List textosDoModelo(int idModelo){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        Query select = sessao.getNamedQuery("BuscarTextosDoModelo");
        select.setInteger("idModelo",idModelo);
        List textos = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return textos; 
    }
    
    
    public static List monitoresDoModelo(int idModelo){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarMonitoresDoModelo");
        select.setInteger("idModelo",idModelo);
        List monitores = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return monitores; 
    }
    
    
    public static Texto obterTexto(int idModelo, String nome){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarTextoDoModelo");
        select.setInteger("idModelo",idModelo);
        select.setString("nome",nome);
        
        Texto texto = new Texto();
        texto=(Texto)select.uniqueResult(); 
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return texto;
    }       
    
    
    public static Monitor obterMonitor(int idModelo, String nome){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarMonitorDoModelo");
        select.setInteger("idModelo",idModelo);
        select.setString("nome",nome);
        
        Monitor monitor = new Monitor();
        monitor=(Monitor)select.uniqueResult(); 
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return monitor;
    }  
}
