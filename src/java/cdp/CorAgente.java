/*
 * CorAgente.java
 *
 * Created on 23 de Outubro de 2006, 13:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Diego Velasco
 */
public class CorAgente extends Condicao {
    
    private String cor;
    
    private Agente agente;
    
    /** Creates a new instance of CorAgente */
    public CorAgente() {
    }
    
    public String getCor() {
        return this.cor;
    }
    
    public void setCor(String c) {
        this.cor = c;
    }
    
    public Agente getAgente(){
        return this.agente;
    }
    
    public void setAgente(Agente agente){
        this.agente = agente;
    }
    
}
