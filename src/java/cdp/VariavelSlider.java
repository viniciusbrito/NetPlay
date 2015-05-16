/*
 * VariavelSlider.java
 *
 * Created on 26 de Abril de 2006, 21:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitario.HibernateUtility;

/**
 *
 * @author Alexandre Luiz
 */
public class VariavelSlider extends Variavel{
    
    private float valorInicial;
    private float valorMinimo;
    private float valorMaximo;
    private float incremento;
    
    /** Creates a new instance of VariavelSlider */
    public VariavelSlider() {
    }
    
    public float getValorInicial(){
        return this.valorInicial;
    }
    
    public void setValorInicial(float valorInicial){
        this.valorInicial = valorInicial;
    }
    
    public float getValorMinimo(){
        return this.valorMinimo;
    }
    
    public void setValorMinimo(float valorMinimo){
        this.valorMinimo = valorMinimo;
    }
    
    public float getValorMaximo(){
        return this.valorMaximo;
    }
    
    public void setValorMaximo(float valorMaximo){
        this.valorMaximo = valorMaximo;
    }

     public float getIncremento(){
        return this.incremento;
    }
    
    public void setIncremento(float incremento){
        this.incremento = incremento;
    }
    
    public static VariavelSlider obterVariavelSlider(int idVariavel)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        VariavelSlider vrs = new VariavelSlider();
        Query select = sessao.getNamedQuery("ObterVariavelSlider");       
        select.setInteger("idVariavel",idVariavel);
        vrs = (VariavelSlider) select.uniqueResult(); 
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return vrs;
    }
}
