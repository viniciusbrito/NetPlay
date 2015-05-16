/*
 * Composicao.java
 *
 * Created on 30 de Maio de 2006, 14:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;

import java.util.Set;

/**
 *
 * @author Diego Velasco
 */
public class Composicao {
    
    private Integer idComposicao;
    private String operador;
    private String nome;
    
    private Modelo modelo;
    private Set acoes;
    private Set condicoes;
    
    private String tipo;
          
    /** Creates a new instance of Composicao */
    public Composicao() {
    }
 
    public Integer getIdComposicao(){
        return this.idComposicao;
    }
    
    public void setIdComposicao(Integer idComposicao){
        this.idComposicao = idComposicao;
    }
    
    public String getOperador(){
        return this.operador;
    }
    
    public void setOperador(String operador){
        this.operador = operador;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }

     public Modelo getModelo() {
        return this.modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Set getCondicoes() {
        return this.condicoes;
    }

    public void setCondicoes(Set c) {
        this.condicoes = c;
    }
    
    public Set getAcoes() {
        return this.acoes;
    }

    public void setAcoes(Set acoes) {
        this.acoes = acoes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
