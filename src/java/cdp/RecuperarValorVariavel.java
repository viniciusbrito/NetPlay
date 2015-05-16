/*
 * RecuperarValorVariavel.java
 *
 * Created on 30 de Maio de 2006, 14:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Velasco
 */
public class RecuperarValorVariavel extends Acao {
    
    private String exp;
    
    private Variavel variavel;
    
    private Agente agente;
    
    /** Creates a new instance of RecuperarValorVariavel */
    public RecuperarValorVariavel() {
    }
    
    public String getExp(){
        return this.exp;
    }
    
    public void setExp(String e){
        this.exp = e;
    }
    
    public Variavel getVariavel(){
        return this.variavel;
    }
    
    public void setVariavel(Variavel variavel){
        this.variavel = variavel;
    }
    
    public Agente getAgente(){
        return this.agente;        
    }
    
    public void setAgente (Agente agente) {
        this.agente = agente;
    }
    
}
