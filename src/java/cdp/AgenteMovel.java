/*
 * AgenteMovel.java
 *
 * Created on 26 de Abril de 2006, 21:33
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
 * @author Alexandre Andrade
 */
public class AgenteMovel extends Agente {
    
    private Integer quantidadeInicial;
    private String forma;
    private Integer tamanho;
    private String tipoMovimento;
    private Integer passo;
    private float angulo1;
    private float angulo2;
    private String tipoQtdInicial;
    private VariavelSlider variavelSlider;
    
    
    /** Creates a new instance of AgenteMovel */
    public AgenteMovel() {
    }
    
    public Integer getQuantidadeInicial(){
        return this.quantidadeInicial;
    }
    
    public void setQuantidadeInicial(Integer quantidadeInicial){
        this.quantidadeInicial = quantidadeInicial;
    }
    
    public String getForma(){
        return this.forma;
    }
    
    public void setForma(String forma){
        this.forma = forma;
    }

    public Integer getTamanho(){
        return this.tamanho;
    }
    
    public void setTamanho(Integer tamanho){
        this.tamanho = tamanho;
    }
    
    public String getTipoMovimento(){
        return this.tipoMovimento;
    }
    
    public void setTipoMovimento(String tipoMovimento){
        this.tipoMovimento = tipoMovimento;
    }

    public Integer getPasso(){
        return this.passo;
    }
    
    public void setPasso(Integer passo){
        this.passo = passo;
    }

     public float getAngulo1(){
        return this.angulo1;
    }
    
    public void setAngulo1(float angulo1){
        this.angulo1 = angulo1;
    }

    public float getAngulo2(){
        return this.angulo2;
    }
    
    public void setAngulo2(float angulo2){
        this.angulo2 = angulo2;
    }
    
    public String getTipoQtdInicial(){
        return this.tipoQtdInicial;
    }
    
    public void setTipoQtdInicial(String tipoQtdInicial){
        this.tipoQtdInicial = tipoQtdInicial;
    }
    
    public VariavelSlider getVariavelSlider(){
        return this.variavelSlider;
    }
    
    public void setVariavelSlider(VariavelSlider variavelSlider){
        this.variavelSlider = variavelSlider;
    }
    
    public static AgenteMovel buscarAgente(int idAgente)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarAgenteMovel");
        select.setInteger("idAgente",idAgente);
        
        AgenteMovel novo;
        novo = (AgenteMovel)select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return novo;
    }
}
