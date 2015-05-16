/*
 * VariavelLogica.java
 *
 * Created on 26 de Abril de 2006, 21:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Alexandre Andrade
 */
public class VariavelLogica extends Variavel{
    
    private boolean valorLogico;
    
    /** Creates a new instance of VariavelLogica */
    public VariavelLogica() {
    }
    
    public boolean getValorLogico(){
        return this.valorLogico;
    }
    
    public void setValorLogico(boolean valorLogico){
        this.valorLogico = valorLogico;
    }
}
