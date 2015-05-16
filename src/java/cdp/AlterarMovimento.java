/*
 * AlterarMovimento.java
 *
 * Created on 30 de Maio de 2006, 14:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Diego Velasco
 */
public class AlterarMovimento extends Acao {
    
    /*Acrescentar as variaveis que correspondem ao movimento no agente!!*/
    
    private AgenteMovel agenteMovel;
    /*Incluir dados do novo movimento!!*/
    
    /** Creates a new instance of AlterarMovimento */
    public AlterarMovimento() {
    }
    
    public Agente getAgenteMovel(){
        return this.agenteMovel;
    }
    
    public void setAgenteMovel(AgenteMovel agente){
        this.agenteMovel = agente;
    }
        
}
