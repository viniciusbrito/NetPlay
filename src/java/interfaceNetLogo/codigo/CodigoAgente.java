/*
 * CodigoAgente.java
 *
 * Created on 7 de Setembro de 2006, 12:24
 *
 */

package interfaceNetLogo.codigo;

import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import cdp.*;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class CodigoAgente {
    
    ArrayList agentesMoveis;
    ArrayList agentesEstacionarios;
    ArrayList agentesBorda;
    
    String declaracoes;
    String funcaoSetup;
    String funcaoGo;
    String outrasFuncoes;
    
    //Cria uma nova instancia de CodigoAgente
    public CodigoAgente(Modelo modelo) 
    {
        declaracoes = "";
        funcaoSetup = "";
        funcaoGo = "";
        outrasFuncoes = "";
        
        agentesMoveis = new ArrayList();
        agentesEstacionarios = new ArrayList();
        agentesBorda = new ArrayList();
        
        //Extrai agentes do BD
        extraiAgentes (modelo);
        
        criaCodigoAgMoveis();
        criaCodigoAgEstacionarios();
        criaCodigoAgBorda();
    }
    
    public String getDeclaracoes()
    {
        return declaracoes;
    }
    
    public String getFuncaoSetup()
    {
        return funcaoSetup;
    }
    
    public String getFuncaoGo()
    {
        return funcaoGo;
    }
    
    public String getOutrasFuncoes()
    {
        return outrasFuncoes;
    }
    
    //Extrai agentes do banco
    private void extraiAgentes (Modelo modelo)
    {
        ArrayList agentesMoveis2 = new ArrayList();
        
        //Consultando agentes no banco
        Set conjuntoAgentes = modelo.getAgentes();
        Iterator iterator = conjuntoAgentes.iterator();
        while (iterator.hasNext())
        {
            Agente agente = (Agente) iterator.next();
    
            //agentes moveis
            if (agente.getTipo().equals("Movel"))
                agentesMoveis2.add(agente);
            
            //agentes estacionarios
            if (agente.getTipo().equals("Estacionario Aleatorio"))
                agentesEstacionarios.add(agente);
             
            //agentes borda
            if (agente.getTipo().equals("Estacionario Borda"))
                agentesBorda.add(agente);
        }
        
        ordenaAgMoveis(agentesMoveis2);
    }
    
    //Cria codigo de agentes moveis
    private void criaCodigoAgMoveis()
    {
        CodigoAgenteMovel codigo = new CodigoAgenteMovel(agentesMoveis);
        
        declaracoes += codigo.getDeclaracoes();
        funcaoSetup += codigo.getFuncaoSetup();
        funcaoGo += codigo.getFuncaoGo();
        outrasFuncoes += codigo.getOutrasFuncoes();
    }
    
    //Cria codigo de agentes estacionarioes
    private void criaCodigoAgEstacionarios()
    {
        CodigoAgenteEstacionario codigo = 
                new CodigoAgenteEstacionario(agentesEstacionarios);
        
        declaracoes += codigo.getDeclaracoes();
        funcaoSetup += codigo.getFuncaoSetup();
        funcaoGo += codigo.getFuncaoGo();
        outrasFuncoes += codigo.getOutrasFuncoes();
    }
    
    //Cria codigo de agentes borda
    private void criaCodigoAgBorda()
    {
        CodigoAgenteBorda codigo = 
                new CodigoAgenteBorda(agentesBorda);
        
        declaracoes += codigo.getDeclaracoes();
        funcaoSetup += codigo.getFuncaoSetup();
        funcaoGo += codigo.getFuncaoGo();
        outrasFuncoes += codigo.getOutrasFuncoes();
    }
    
    //ordena ag movei pelo tipoQtdInicial - primeiro Num depois var
    private void ordenaAgMoveis(ArrayList lista)
    {
        //Insere os agentes com tipoQtdInicial = Num
        for(int i=0; i<lista.size(); i++)
        {
            AgenteMovel ag = (AgenteMovel) lista.get(i);
            if (ag.getTipoQtdInicial().equals("Num"))
                agentesMoveis.add(ag);
        }
        
        //Insere os agentes com tipoQtdInicial = Var
        for(int i=0; i<lista.size(); i++)
        {
            AgenteMovel ag = (AgenteMovel) lista.get(i);
            if (ag.getTipoQtdInicial().equals("Var"))
                agentesMoveis.add(ag);
        }
    }
}
