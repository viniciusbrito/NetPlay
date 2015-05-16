/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdp;

/**
 *
 * @author vinicius
 */
public class PercepcaoAvancado {
    
    private int idPercepcaoAvancado;
    private int idCondicao;
    private int idVariavel;
    private String valor;
    private String agente;

    public PercepcaoAvancado() {
    }

    public int getIdPercepcaoAvancado() {
        return idPercepcaoAvancado;
    }

    public void setIdPercepcaoAvancado(int idPercepcaoAvancado) {
        this.idPercepcaoAvancado = idPercepcaoAvancado;
    }

    public int getIdCondicao() {
        return idCondicao;
    }

    public void setIdCondicao(int idCondicao) {
        this.idCondicao = idCondicao;
    }

    public int getIdVariavel() {
        return idVariavel;
    }

    public void setIdVariavel(int idVariavel) {
        this.idVariavel = idVariavel;
    }        

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }
    
    
    
}
