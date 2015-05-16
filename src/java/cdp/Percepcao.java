/*
 * Percepcao.java
 *
 * Created on 30 de Maio de 2006, 13:53
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
public class Percepcao extends Condicao {
    
    private Integer distancia;
    
    private Agente agente;
    
    //private Integer idAgMovel;
    private Integer idAgentePrincipal;
    
    private String modo;
    
    /** Creates a new instance of Percepcao */
    public Percepcao() {
    }
    
    public Integer getDistancia(){
        return this.distancia;
    }
    
    public void setDistancia(Integer distancia){
        this.distancia = distancia;
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
        if(idAgentePrincipal == agente.getIdAgente())
            return agente;
        else
            return AplCadastrarAgente.obterAgente(idAgentePrincipal);
    }
    
    /*public void setAgenteMovel(AgenteMovel agenteMovel){
        this.agenteMovel = agenteMovel;
    }*/

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }    
    
}