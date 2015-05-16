/* CodigoVariavel.java
 *
 * Criado em 7 de Setembro de 2006, 14:55
 */

package interfaceNetLogo.codigo;

import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;
import cdp.*;
import interfaceNetLogo.Traducao;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class CodigoVariavel {
    
    String funcaoSetupGeral;    //codifo das vars sem contador
    String funcaoSetupContador; //codigo das vars com contador
    String declaracoes;
    
    ArrayList variaveisValor;
    
    public CodigoVariavel (Modelo modelo) 
    {
        funcaoSetupGeral = "";
        funcaoSetupContador = "";
        declaracoes = "";
        variaveisValor = new ArrayList();
        
        Iterator iterator = modelo.getVariaveis().iterator();
        while(iterator.hasNext())
        {
            Variavel variavel = (Variavel) iterator.next();
            //Se nao for relacionada a agente e for do tipo valor ...
            if ((variavel.getAgente() == null) && 
                    (variavel.getTipo().equals("Valor")))
                variaveisValor.add(variavel);
        }
        
        if (!variaveisValor.isEmpty())
            codigoInicializaVariaveis();
    }
    
    public String getFuncaoSetupGeral ()
    {
        return funcaoSetupGeral;
    }
    
    public String getFuncaoSetupContador ()
    {
        return funcaoSetupContador;
    }
    
    public String getDeclaracoes ()
    {
        return declaracoes;
    }
    
    private void codigoInicializaVariaveis ()
    {
        boolean temContador = false;
        boolean temGeral = false;
        
        declaracoes += ";declarando variaveis globais\n";
        declaracoes += "globals [ ";
                
        Iterator iterator = variaveisValor.iterator();
        String codigo;
        while(iterator.hasNext())
        {
            Variavel variavel = (Variavel) iterator.next();
            
            //declarando variavel
            declaracoes += variavel.getNome() + " ";
            
            //inicializando variavel
            VariavelValor variavelValor = (VariavelValor) variavel;
            codigo = "\tset " + 
                    variavel.getNome() + " " +
                    Traducao.expressao(variavelValor.getValor(), variavelValor.getModelo()) + "\n";
            
            //verificando se variavel tem contador
            if(codigo.startsWith("count ") || codigo.endsWith(" count") || 
                    codigo.contains(" count "))
            {
                funcaoSetupContador += codigo;
                temContador = true;
            }
            else
            {
                funcaoSetupGeral += codigo;
                temGeral = true;
            }
        }
        
        declaracoes += "]\n\n";
        
        if(temGeral)     
            funcaoSetupGeral = "\t;inicializando variaveis globais que nao " +
                "envolvam contador de agentes\n" + funcaoSetupGeral + "\n";
        
        if(temContador)        
            funcaoSetupContador = "\t;inicializando variaveis globais que " +
                "envolvam contador de agentes\n" + funcaoSetupContador + "\n";     
    }
    
}
