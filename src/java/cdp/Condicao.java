/*
 * Condicao.java
 *
 * Created on 30 de Maio de 2006, 13:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Diego Velasco
 */
public class Condicao {
    
    private Integer idCondicao;
    private String tipo;
    
    private Composicao composicao;
    
    /** Creates a new instance of Condicao */
    public Condicao() {
    }
    
    public Integer getIdCondicao(){
        return this.idCondicao;
    }
    
    public void setIdCondicao(Integer idCondicao){
        this.idCondicao = idCondicao;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public Composicao getComposicao() {
        return this.composicao;
    }

    public void setComposicao(Composicao c) {
        this.composicao = c;
    }    
    
}
