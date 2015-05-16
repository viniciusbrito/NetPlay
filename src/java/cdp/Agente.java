/*
 * Agente.java
 *
 * Created on 26 de Abril de 2006, 20:31
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
public class Agente {
    
    private Integer idAgente;
    private String nome;
    private String cor;
    private String tipo;
    private String tipoPosicao;
    
    private Modelo modelo;
    private Set variaveis; 
    private Set posicoesiniciais;
          
    /** Creates a new instance of Agente */
    public Agente() {
    }
 
    public Integer getIdAgente(){
        return this.idAgente;
    }
    
    public void setIdAgente(Integer idAgente){
        this.idAgente = idAgente;
    }
    
     public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
     public String getCor(){
        return this.cor;
    }
    
    public void setCor(String cor){
        this.cor = cor;
    }

    public String getTipo(){
        return this.tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public String getTipoPosicao(){
        return this.tipoPosicao;
    }
    
    public void setTipoPosicao(String tipoPosicao){
        this.tipoPosicao = tipoPosicao;
    }
    
     public Modelo getModelo() {
        return this.modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    
    public Set getVariaveis() {
        return this.variaveis;
    }

    public void setVariaveis(Set variaveis) {
        this.variaveis = variaveis;
    }
    
    public Set getposicoesiniciais() {
        return this.posicoesiniciais;
    }

    public void setposicoesiniciais(Set posicoesiniciais) {
        this.posicoesiniciais = posicoesiniciais;
    }
}
