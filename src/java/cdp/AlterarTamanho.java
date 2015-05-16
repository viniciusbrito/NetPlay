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
public class AlterarTamanho extends Acao
{
    private AgenteMovel agenteMovel;
    private int tamanho;
    private String tipoTamanho;

    public AlterarTamanho() {
    }

    public AgenteMovel getAgenteMovel() {
        return agenteMovel;
    }

    public void setAgenteMovel(AgenteMovel agenteMovel) {
        this.agenteMovel = agenteMovel;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String getTipoTamanho() {
        return tipoTamanho;
    }

    public void setTipoTamanho(String tipoTamanho) {
        this.tipoTamanho = tipoTamanho;
    }
    
    
    
}
