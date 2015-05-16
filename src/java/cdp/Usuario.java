/*
 * Usuario.java
 *
 * Created on 26 de Abril de 2006, 15:02
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
public class Usuario {
    
    private Integer idUsuario;
    private String nome;
    private String sobrenome;            
    private String email;
    private String login;
    private String senha;
    private String sexo;
    private String instituicao;
    private String areaInteresse;
    private String dtNascimento;
    
    private Set modelos;
            
    /** Creates a new instance of Usuario */
    public Usuario() {
    }
 
    public Integer getIdUsuario(){
        return this.idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario){
        this.idUsuario = idUsuario;
    }
    
       public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getSobrenome(){
        return this.sobrenome;
    }
    
    public void setSobrenome(String sobrenome){
        this.sobrenome = sobrenome;
    }

    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }

    public String getLogin(){
        return this.login;
    }
    
    public void setLogin(String login){
        this.login = login;
    }
    
    public String getSenha(){
        return this.senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getSexo(){
        return this.sexo;
    }
    
    public void setSexo(String sexo){
        this.sexo = sexo;
    }
    
    public String getInstituicao(){
        return this.instituicao;
    }
    
    public void setInstituicao(String instituicao){
        this.instituicao = instituicao;
    }
    
    public String getAreaInteresse(){
        return this.areaInteresse;
    }
    
    public void setAreaInteresse(String areaInteresse){
        this.areaInteresse = areaInteresse;
    }
    
    public String getDtNascimento(){
        return this.dtNascimento;
    }
    
    public void setDtNascimento(String dtNascimento){
        this.dtNascimento = dtNascimento;
    }
    
    public Set getModelos() {
        return this.modelos;
    }

    public void setModelos(Set modelos) {
        this.modelos = modelos;
    }
    
    
    
    
    
    
 }
