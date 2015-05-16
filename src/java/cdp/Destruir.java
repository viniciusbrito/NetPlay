/*
 * Destruir.java
 *
 * Created on 30 de Maio de 2006, 14:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Velasco
 */
public class Destruir extends Acao {
    
    private Agente agente;
    
    /** Creates a new instance of Destruir */
    public Destruir() {
    }
    
    public Agente getAgente(){
        return this.agente;
    }
    
    public void setAgente(Agente agente){
        this.agente = agente;
    }
    
}
