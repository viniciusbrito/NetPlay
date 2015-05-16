/*
 * AgenteEstBorda.java
 *
 * Created on 26 de Abril de 2006, 21:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Alexandre Andrade
 */
public class AgenteEstBorda extends Agente{
    
    private Integer tamanho;    
    
    /** Creates a new instance of AgenteEstBorda */
    public AgenteEstBorda() {
    }
 
    public Integer getTamanho(){
        return this.tamanho;
    }
    
    public void setTamanho(Integer tamanho){
        this.tamanho = tamanho;
    }
    
    
}
