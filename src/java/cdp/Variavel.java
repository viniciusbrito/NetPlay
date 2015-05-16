/*
 * Variavel.java
 *
 * Created on 26 de Abril de 2006, 20:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

/**
 *
 * @author Alexandre Andrade
 */
public class Variavel {
    
    private Integer idVariavel;
    private String nome;
    private String tipo;
    
    private Agente agente;
    private Modelo modelo;
      
    /** Creates a new instance of Variavel */
    public Variavel() {
    }
    
    public Integer getIdVariavel(){
        return this.idVariavel;
    }
    
    public void setIdVariavel(Integer idVariavel){
        this.idVariavel = idVariavel;
    }
    
     public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
        
    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public Agente getAgente() {
        return this.agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }
    
    public Modelo getModelo() {
        return this.modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
