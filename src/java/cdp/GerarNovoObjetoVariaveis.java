/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdp;

/*
 *
 * @author vinicius
 */
public class GerarNovoObjetoVariaveis {


     private int idGerar;
     private int idAcao;
     private int idVariavel;
     private String valor;

    public GerarNovoObjetoVariaveis() {
    }

    public GerarNovoObjetoVariaveis(int idGerar, int idAcao, int idVariavel, String valor) {
        this.idGerar = idGerar;
        this.idAcao = idAcao;
        this.idVariavel = idVariavel;
        this.valor = valor;
    }

    public int getIdGerar() {
        return idGerar;
    }

    public void setIdGerar(int idGerar) {
        this.idGerar = idGerar;
    }

    public int getIdAcao() {
        return idAcao;
    }

    public void setIdAcao(int idAcao) {
        this.idAcao = idAcao;
    }

    public int getIdVariavel() {
        return idVariavel;
    }

    public void setIdVariavel(int idVariavel) {
        this.idVariavel = idVariavel;
    }


    public String getValor() {
        return this.valor;
    }
    
    public void setValor(String valor) {
        this.valor = valor;
    }
}
