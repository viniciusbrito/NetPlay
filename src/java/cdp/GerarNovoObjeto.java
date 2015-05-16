/*
 * GerarNovoObjeto.java
 *
 * Created on 30 de Maio de 2006, 14:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitario.HibernateUtility;

/**
 *
 * @author Diego Velasco
 */
public class GerarNovoObjeto extends Acao {
    
    private Agente agente;
    private String cor;
    private String forma;
    private int tamanho;
    private String posicao;
    
    /** Creates a new instance of GerarNovoObjeto */
    public GerarNovoObjeto() {
    }
    
    public Agente getAgente(){
        return this.agente;
    }
    
    public void setAgente(Agente agente){
        this.agente = agente;
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

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    
    
    public static GerarNovoObjeto buscar(int idAcao, int idAgente)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("GerarNovoBuscar");
        select.setInteger("idAcao",idAcao);
        select.setInteger("idAgente",idAgente);
        
        GerarNovoObjeto novo;
        novo = (GerarNovoObjeto)select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return novo;
    }
}
