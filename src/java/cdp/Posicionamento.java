/*
 * Posicionamento.java
 *
 * Created on 30 de Maio de 2006, 13:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cdp;
import cgt.AplCadastrarAgente;

/**
 *
 * @author Diego Velasco
 */
public class Posicionamento extends Condicao {
    
    private String expressao;
    
    private Agente agente;
    
    private Integer idAgentePrincipal;
    
    /** Creates a new instance of Posicionamento */
    public Posicionamento() {
    }
    
    public String getExpressao(){
        return this.expressao;
    }
    
    public void setExpressao(String expressao){
        this.expressao = expressao;
    }

    public Agente getAgente(){
        return this.agente;
    }
    
    public void setAgente(Agente agente){
        this.agente = agente;
    }
    
    public Integer getIdAgentePrincipal(){
        return this.idAgentePrincipal;
    }
    
    public void setIdAgentePrincipal(Integer id){
        this.idAgentePrincipal = id;
    }
    
    public Agente getAgentePrincipal(){
        if (idAgentePrincipal == -1)
            return null;
        return AplCadastrarAgente.obterAgente(idAgentePrincipal);
    }
}
