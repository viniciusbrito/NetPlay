/*
 * CmpVariavel.java
 *
 * Created on 30 de Maio de 2006, 13:57
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;
import cgt.AplCadastrarVariavel;

/**
 *
 * @author Diego Velasco
 */
public class CmpVariavel extends Condicao{
    
    private String expressao;
    
    //private Variavel var1;
    //private Variavel var2;
    private Integer idVar1;
    private Integer idVar2;
    
    /** Creates a new instance of CmpVariavel */
    public CmpVariavel() {
    }
    
    public String getExpressao(){
        return this.expressao;
    }
    
    public void setExpressao(String expressao){
        this.expressao = expressao;
    }

    public Integer getIdVar1(){
        return this.idVar1;
    }
    
    public void setIdVar1(Integer id){
        this.idVar1 = id;
    }

    public Integer getIdVar2(){
        return this.idVar2;
    }
    
    public void setIdVar2(Integer id){
        this.idVar2 = id;
    }
    
    public Variavel getVar1(){
        return AplCadastrarVariavel.obterVariavel(idVar1);
    }
    
    public Variavel getVar2(){
        return AplCadastrarVariavel.obterVariavel(idVar2);
    }

}
