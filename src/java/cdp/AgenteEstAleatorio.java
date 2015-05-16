/*
 * AgenteEstAleatorio.java
 *
 * Created on 26 de Abril de 2006, 21:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;
import java.util.Set;

/**
 *
 * @author Alexandre Andrade
 */
public class AgenteEstAleatorio extends Agente{
    
   private float porcentagem;
       
    /** Creates a new instance of AgenteEstAleatorio */
    public AgenteEstAleatorio() {
    }
    
    public float getPorcentagem(){
        return this.porcentagem;
    }
    
    public void setPorcentagem(float porcentagem){
        this.porcentagem = porcentagem;
    }
}
