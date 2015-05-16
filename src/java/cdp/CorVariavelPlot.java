/*
 * CorVariavelPlot.java
 *
 * Created on 26 de Abril de 2006, 19:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Alexandre Andrade
 */
public class CorVariavelPlot {
    
    private Integer idCorVariavelPlot;
    private String cor;
    
    private Plot plot;
    private VariavelValor variavelvalor;
    
    /** Creates a new instance of CorVariavelPlot */
    public CorVariavelPlot() {
    }
    
     public Integer getIdCorVariavelPlot(){
        return this.idCorVariavelPlot;
    }
    
    public void setIdCorVariavelPlot(Integer idCorVariavelPlot){
        this.idCorVariavelPlot = idCorVariavelPlot;
    }
    
    public String getCor(){
        return this.cor;
    }
    
    public void setCor(String cor){
        this.cor = cor;
    }

     public Plot getPlot(){
        return this.plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }
    
     public VariavelValor getvariavelvalor(){
        return this.variavelvalor;
    }

    public void setvariavelvalor(VariavelValor variavelvalor) {
        this.variavelvalor = variavelvalor;
    }
}
