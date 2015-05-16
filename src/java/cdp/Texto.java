/*
 * Texto.java
 *
 * Created on 26 de Abril de 2006, 19:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Alexandre Andrade
 */
public class Texto {
    
    private Integer idTexto;
    private String nome;
    private String texto;
    
    private Modelo modelo;
    
    /** Creates a new instance of Texto */
    public Texto() {
    }
 
    public Integer getIdTexto(){
        return this.idTexto;
    }
    
    public void setIdTexto(Integer idTexto){
        this.idTexto = idTexto;
    }
    
     public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
        
    public String getTexto(){
        return this.texto;
    }
    
    public void setTexto(String texto){
        this.texto = texto;
    }
    
    public Modelo getModelo() {
        return this.modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    
    public String getTextoHtml() {
        String retorno = "";
        
        try {
            retorno = java.net.URLEncoder.encode(texto, "utf-8");
        } catch (java.io.UnsupportedEncodingException e) {}
        
        return retorno;
    }
}
