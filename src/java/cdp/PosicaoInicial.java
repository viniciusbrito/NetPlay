/*
 * PosicaoInicial.java
 *
 * Created on 1 de Julho de 2006, 10:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Alexandre Andrade
 */
public class PosicaoInicial{
    
    private Integer idPosicaoInicial;
    private Integer xInicial;
    private Integer yInicial;
    private int quantidadePosicao;
    
    private Agente agente;
    
    
    
    /** Creates a new instance of PosicaoInicial */
    public PosicaoInicial() {
    }
    
    public Integer getIdPosicaoInicial(){
        return this.idPosicaoInicial;
    }
    
    public void setIdPosicaoInicial(Integer idPosicaoInicial){
        this.idPosicaoInicial = idPosicaoInicial;
    }
    
     public Integer getXInicial(){
        return this.xInicial;
    }
    
     public void setXInicial(Integer xInicial){
        this.xInicial = xInicial;
    }
     
     public Integer getYInicial(){
        return this.yInicial;
    }
    
     public void setYInicial(Integer yInicial){
        this.yInicial = yInicial;
    }
     
     public int getQuantidadePosicao(){
        return this.quantidadePosicao;
    }
    
     public void setQuantidadePosicao(int quantidadePosicao){
        this.quantidadePosicao = quantidadePosicao;
    }
    
     public Agente getAgente() {
        return this.agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }    
}
