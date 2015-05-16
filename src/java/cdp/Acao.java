/*
 * Acao.java
 *
 * Created on 30 de Maio de 2006, 14:01
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Diego Velasco
 */
public class Acao {
    
    private Integer idAcao;
    private String tipo;
    
    private Composicao composicao;
    
    /** Creates a new instance of Acao */
    public Acao() {
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public Integer getIdAcao(){
        return this.idAcao;
    }
    
    public void setIdAcao(Integer idAcao){
        this.idAcao = idAcao;
    }
    
    public Composicao getComposicao(){
        return this.composicao;
    }
    
    public void setComposicao(Composicao c){
        this.composicao = c;
    }
    
}
