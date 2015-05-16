/*
 * Plot.java
 *
 * Created on 26 de Abril de 2006, 19:36
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
public class Plot {
    
    private Integer idPlot;
    private String nomeGrafico;
    private float xMinimo;
    private float xMaximo;
    private float yMinimo;
    private float yMaximo;
    private String nomeEixoX;
    private String nomeEixoY;
    private String tipo;
    
    private Modelo modelo;
    private Set CoresVariaveisPlot;
    
    /** Creates a new instance of Plot */
    public Plot() {
    }
    
    public Integer getIdPlot(){
        return this.idPlot;
    }
    
    public void setIdPlot(Integer idPlot){
        this.idPlot = idPlot;
    }
    
     public String getNomeGrafico(){
        return this.nomeGrafico;
    }
    
    public void setNomeGrafico(String nomeGrafico){
        this.nomeGrafico = nomeGrafico;
    }
    
    public float getXMinimo(){
        return this.xMinimo;
    }
    
    public void setXMinimo(float xMinimo){
        this.xMinimo = xMinimo;
    }

    public float getXMaximo(){
        return this.xMaximo;
    }
    
    public void setXMaximo(float xMaximo){
        this.xMaximo = xMaximo;
    }
    
    public float getYMinimo(){
        return this.yMinimo;
    }
    
    public void setYMinimo(float yMinimo){
        this.yMinimo = yMinimo;
    }
    
     public float getYMaximo(){
        return this.yMaximo;
    }
    
    public void setYMaximo(float yMaximo){
        this.yMaximo = yMaximo;
    }

    public String getNomeEixoX(){
        return this.nomeEixoX;
    }
    
    public void setNomeEixoX(String nomeEixoX){
        this.nomeEixoX = nomeEixoX;
    }
    
    public String getNomeEixoY(){
        return this.nomeEixoY;
    }
    
    public void setNomeEixoY(String nomeEixoY){
        this.nomeEixoY = nomeEixoY;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public Modelo getModelo() {
        return this.modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    
    public Set getCoresVariaveisPlot() {
        return this.CoresVariaveisPlot;
    }

    public void setCoresVariaveisPlot(Set coresvariaveisplot) {
        this.CoresVariaveisPlot = coresvariaveisplot;
    }
}
