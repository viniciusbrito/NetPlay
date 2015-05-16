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
public class PercepcaoCFT {
    
    private int idPercepcaoCFT;
    private int idCondicao;
    private String var;
    private String valor;
    private String operador;

    public PercepcaoCFT() {
    }

    public int getIdPercepcaoCFT() {
        return idPercepcaoCFT;
    }

    public void setIdPercepcaoCFT(int idPercepcaoCFT) {
        this.idPercepcaoCFT = idPercepcaoCFT;
    }

    public int getIdCondicao() {
        return idCondicao;
    }

    public void setIdCondicao(int idCondicao) {
        this.idCondicao = idCondicao;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }
    
}
