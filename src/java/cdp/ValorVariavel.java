/*
 * ValorVariavel.java
 *
 * Created on 16 de Agosto de 2006, 15:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Diego Velasco
 */
public class ValorVariavel extends Condicao {
    
    private Variavel var;
    private String expr;
    
    /** Creates a new instance of ValorVariavel */
    public ValorVariavel() {
    }
    
    public Variavel getVar(){
        return this.var;
    }
    
    public void setVar(Variavel var){
        this.var = var;
    }
    
    public String getExpr(){
        return this.expr;
    }
    
    public void setExpr(String expressao){
        this.expr = expressao;
    }
    
}
