/*
 * Modelo.java
 *
 * Created on 26 de Abril de 2006, 19:27
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
public class Modelo {
    
    private Integer idModelo;
    private String nome;
    private String descricao;
    private String dtCriacao;
    
    private Set monitores;
    private Set textos;
    private Set plots;
    private Set agentes;
    private Set variaveis;
    private Set botoes;
    
    private Usuario usuario;
    private Modelo modeloPai;
    private Set modelosFilhos;
    
    /** Creates a new instance of Modelo */
    public Modelo() {
    }
    
     public Integer getIdModelo(){
        return this.idModelo;
    }
    
    public void setIdModelo(Integer idModelo){
        this.idModelo = idModelo;
    }
    
     public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

     public String getDtCriacao(){
        return this.dtCriacao;
    }
    
    public void setDtCriacao(String dtCriacao){
        this.dtCriacao = dtCriacao;
    }
    
    public Set getMonitores() {
        return this.monitores;
    }

    public void setMonitores(Set monitores) {
        this.monitores = monitores;
    }

    public Set getTextos() {
        return this.textos;
    }

    public void setTextos(Set textos) {
        this.textos = textos;
    }
    
    public Set getPlots() {
        return this.plots;
    }

    public void setPlots(Set plots) {
        this.plots = plots;
    }

    public Set getAgentes() {
        return this.agentes;
    }

    public void setAgentes(Set agentes) {
        this.agentes = agentes;
    }
    
    public Set getVariaveis() {
        return this.variaveis;
    }

    public void setVariaveis(Set variaveis) {
        this.variaveis = variaveis;
    }

    public Set getBotoes() {
        return botoes;
    }

    public void setBotoes(Set botoes) {
        this.botoes = botoes;
    }
        
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Modelo getModeloPai() {
        return this.modeloPai;
    }

    public void setModeloPai(Modelo modeloPai) {
        this.modeloPai = modeloPai;
    }
    
     public Set getModelosFilhos() {
        return this.modelosFilhos;
    }

    public void setModelosFilhos(Set modelosFilhos) {
        this.modelosFilhos = modelosFilhos;
    }
}

