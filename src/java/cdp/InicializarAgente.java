/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdp;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitario.HibernateUtility;

/**
 *
 * @author vinicius
 */
public class InicializarAgente {
    
    private int idInicializarAgente;
    private Agente agente;
    private Modelo modelo;
    private int quantidade;
    private String cor;
    private String forma;;
    private int tamanho;

    public InicializarAgente() {
    }

    public int getIdInicializarAgente() {
        return idInicializarAgente;
    }

    public void setIdInicializarAgente(int idInicializarAgente) {
        this.idInicializarAgente = idInicializarAgente;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    
    
    //busca pelo idInicializarAgente
    public static InicializarAgente obterIA(int idIni)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarIA");
        select.setInteger("idIni",idIni);
        
        InicializarAgente ia = new InicializarAgente();
        ia = (InicializarAgente)select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return ia;
    }
    
    //busca todas inicializações do modelo
    public static List obterIAM(int idModelo)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarIAM");
        select.setInteger("idModelo",idModelo);
        
        List ia = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return ia;
    }
    
    //busca pelo idAgente
    public static List obterIAA(int idAgente)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarIAA");
        select.setInteger("idAgente",idAgente);
        
        List ia = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return ia;
    }
    
}
