/*
 * PararMovimento.java
 *
 * Created on 5 de Maio de 2007, 20:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Lucio Marcos Barbiero
 */
public class PararMovimento extends Acao {
    
    private AgenteMovel agenteMovel;
    
    public PararMovimento() {
    }
    
    public Agente getAgenteMovel(){
        return this.agenteMovel;
    }
    
    public void setAgenteMovel(AgenteMovel agente){
        this.agenteMovel = agente;
    }
        
}
