/*
 * AlterarPosicao.java
 *
 * Created on 30 de Maio de 2006, 14:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Diego Velasco
 */
public class AlterarPosicao  extends Acao {
    
    private Integer novoX;
    private Integer novoY; /*Esses atributos naum seriam expressoes??*/
    
    private AgenteMovel agenteMovel;
    
    /** Creates a new instance of AlterarPosicao */
    public AlterarPosicao() {
    }
    
    public Integer getNovoX(){
        return this.novoX;
    }
    
    public void setNovoX(Integer novoX){
        this.novoX = novoX;
    }
    
    public Integer getNovoY(){
        return this.novoY;
    }
    
    public void setNovoY(Integer novoY){
        this.novoY = novoY;
    }

    public AgenteMovel getAgenteMovel(){
        return this.agenteMovel;
    }
    
    public void setAgenteMovel(AgenteMovel agente){
        this.agenteMovel = agente;
    }

}