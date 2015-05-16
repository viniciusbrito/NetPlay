/*
 * AlterarCor.java
 *
 * Created on 27 de Agosto de 2006, 08:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Diego Velasco
 */
public class AlterarCor extends Acao {

    private Agente agente;
    private String novaCor;
    /*Incluir dados do novo movimento!!*/
    
    /** Creates a new instance of AlterarCor */
    public AlterarCor() {
    }
    
    public Agente getAgente(){
        return this.agente;
    }
    
    public void setAgente(Agente agente){
        this.agente = agente;
    }

    public String getNovaCor(){
        return this.novaCor;
    }
    
    public void setNovaCor(String cor){
        this.novaCor = cor;
    }
    
}
