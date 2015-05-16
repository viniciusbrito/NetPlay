/*
 * VariavelValor.java
 *
 * Created on 26 de Abril de 2006, 20:07
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
public class VariavelValor extends Variavel{
    
    private String valor;
    
    private Set coresvariaveisplot;
    
    private String valorCriacao; //Usado quando for criar um novo objeto

    public String getValorCriacao() {
        return valorCriacao;
    }

    public void setValorCriacao(String valorCriacao) {
        this.valorCriacao = valorCriacao;
    }
    
    /** Creates a new instance of VariavelValor */
    public VariavelValor() {
    }
    
    public String getValor(){
        return this.valor;
    }
    
    public void setValor(String valor){
        this.valor = valor;
    }
    
     public Set getcoresvariaveisplot() {
        return this.coresvariaveisplot;
    }

    public void setcoresvariaveisplot(Set coresvariaveisplot) {
        this.coresvariaveisplot = coresvariaveisplot;
    }
    
    public String getValorHTML(){
        String retorno = "";
        
        try {
            retorno = java.net.URLEncoder.encode(valor, "utf-8");
        } catch (java.io.UnsupportedEncodingException e) {}
        
        return retorno;
    }
}
