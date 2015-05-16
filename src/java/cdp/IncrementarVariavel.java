/*
 * IncrementarVariavel.java
 *
 * Created on 30 de Maio de 2006, 14:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Diego Velasco
 */
public class IncrementarVariavel extends Acao {
    
    private Float incremento;
    
    private Variavel variavel;
    
    /** Creates a new instance of IncrementarVariavel */
    public IncrementarVariavel() {
    }
    
    public Float getIncremento(){
        return this.incremento;
    }
    
    public void setIncremento(Float incremento){        
        this.incremento = incremento;
    }
    
    public Variavel getVariavel(){
        return this.variavel;
    }
    
    public void setVariavel(Variavel variavel){
        this.variavel = variavel;
    }
    
}
