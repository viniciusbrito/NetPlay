/* CodigoAgenteMovel.java
 *
 * Created on 7 de Setembro de 2006, 13:07
 */

package interfaceNetLogo.codigo;

import java.util.ArrayList;
import java.util.Iterator;
import cdp.*;
import interfaceNetLogo.Traducao;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class CodigoAgenteBorda {
    
    ArrayList agentesBorda;
    
    String declaracoes;
    String funcaoSetup;
    String funcaoGo;
    String outrasFuncoes;
    
    String codigoVariaveis;
    
    public CodigoAgenteBorda (ArrayList agBorda) 
    {
        declaracoes = "";
        funcaoSetup = "";
        funcaoGo = "";
        outrasFuncoes = "";
        
        codigoVariaveis = "";
        
        agentesBorda = agBorda;
        
        codigoAgBorda();
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
    
    private void codigoAgBorda ()
    {
        if (agentesBorda.isEmpty())
        {
            //termina de declarar variaveis de patches 
            declaracoes += "]\n\n";
            return;
        }
        
        Iterator iterator = agentesBorda.iterator();
        while (iterator.hasNext())
        {
            AgenteEstBorda agente = (AgenteEstBorda) iterator.next();
            
            //declarando e inicializando variaveis
            if (!agente.getVariaveis().isEmpty())
                codigoCriaVariaveisAgBorda (agente);

            //criando agentes
            codigoCriaAgBorda (agente);
        }
        
        //termina de declarar variaveis de patches 
        declaracoes += "]\n\n";
    }
    
    //Cria codigo para inicializar agentes borda
    private void codigoCriaAgBorda (AgenteEstBorda agente)
    {
        String codigo = "\t;criando agente borda " +
                agente.getNome() + "\n";
        
        //Define local da borda
        codigo += 
                "\task patches\n\t[\n" +
                "\t\tif (abs(pxcor) = " + agente.getTamanho() + 
                        " and abs(pycor) <= " + agente.getTamanho() + 
                        ") or\n" +
                "\t\t   (abs(pxcor) <= " + agente.getTamanho() + 
                        " and abs(pycor) = " + agente.getTamanho() + 
                        ")\n" +
                "\t\t\t[\n" +
                "\t\t\t\tset pcolor " + 
                        Traducao.cor(agente.getCor()) + "\n" +
                codigoVariaveis + 
                "\t\t\t]\n\t]\n\n";
        
        funcaoSetup += codigo;
    }
    
    private void codigoCriaVariaveisAgBorda (AgenteEstBorda agente)
    {
        Iterator iterator = agente.getVariaveis().iterator();
        while(iterator.hasNext())
        {
            Variavel variavel = (Variavel) iterator.next();
            
            declaracoes += variavel.getNome() + " ";
            
            codigoVariaveis += "\t\t\t\tset " + variavel.getNome() + " ";
            if (variavel.getTipo().equals("Logica"))
            {
                VariavelLogica varLogica = (VariavelLogica) variavel;
                codigoVariaveis += varLogica.getValorLogico();
            }
            if (variavel.getTipo().equals("Slider"))
            {
                VariavelSlider varSlider = (VariavelSlider) variavel;
                codigoVariaveis += varSlider.getValorInicial();
            }
            if (variavel.getTipo().equals("Valor"))
            {
                VariavelValor varValor = (VariavelValor) variavel;
                codigoVariaveis += Traducao.expressao(varValor.getValor(), varValor.getModelo());
            }
            codigoVariaveis += "\n";
        }
    }
    
}
