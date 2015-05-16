/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitario.HibernateUtility;
import java.util.List;

/**
 *
 * @author vinicius
 */
public class InicializarAgenteVariaveis {

    private int id;
    private InicializarAgente inicializarAgente;
    private Variavel variavel;
    private String valor;

    public InicializarAgenteVariaveis() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InicializarAgente getInicializarAgente() {
        return inicializarAgente;
    }

    public void setInicializarAgente(InicializarAgente inicializarAgente) {
        this.inicializarAgente = inicializarAgente;
    }

    public Variavel getVariavel() {
        return variavel;
    }

    public void setVariavel(Variavel variavel) {
        this.variavel = variavel;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public static InicializarAgenteVariaveis obterIAV(int idIni, int idVar)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarIAV");
        select.setInteger("idIni",idIni);
        select.setInteger("idVar",idVar);
        
        InicializarAgenteVariaveis iav = new InicializarAgenteVariaveis();
        iav = (InicializarAgenteVariaveis)select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return iav;
    }    
}
