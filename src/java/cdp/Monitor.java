/*
 * Monitor.java
 *
 * Created on 26 de Abril de 2006, 15:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Alexandre Andrade
 */
public class Monitor {
    
    private Integer idMonitor;
    private String nome;
    private String expressao;
    private Integer casasDecimais;
    
    private Modelo modelo;
    
    /** Creates a new instance of Monitor */
    public Monitor() {
    }
    
    public Integer getIdMonitor(){
        return this.idMonitor;
    }
    
    public void setIdMonitor(Integer idMonitor){
        this.idMonitor = idMonitor;
    }
    
     public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
     public String getExpressao(){
        return this.expressao;
    }
    
    public void setExpressao(String expressao){
        this.expressao = expressao;
    }
    
    public Integer getCasasDecimais(){
        return this.casasDecimais;
    }
    
    public void setCasasDecimais(Integer casasDecimais){
        this.casasDecimais = casasDecimais;
    }
    
    public Modelo getModelo() {
        return this.modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    
    public String getExpressaoHtml() {
        String retorno = "";
        
        try {
            retorno = java.net.URLEncoder.encode(expressao, "utf-8");
        } catch (java.io.UnsupportedEncodingException e) {}
        
        return retorno;
    }
}
