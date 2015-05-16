/*
 * CodigoPlot.java
 */

package interfaceNetLogo.codigo;

import java.util.Set;
import java.util.Iterator;
import cdp.*;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class CodigoPlot {
    
    String funcaoSetup;
    String funcaoGo;
    String outrasFuncoes;
    
    public CodigoPlot(Modelo modelo) 
    {
        boolean temGraficos = false;
        
        funcaoSetup = "";
        funcaoGo = "";
        outrasFuncoes = "";
        
        String pasta = "C:\\\\ProjetoFinal\\\\web\\\\graficos\\\\";
               
        Iterator iterator = modelo.getPlots().iterator();
        while(iterator.hasNext())
        {
            Plot plot = (Plot) iterator.next();
            outrasFuncoes += criaCodigoPlots (plot);
            temGraficos = true;
        }
        
        if (temGraficos)
        {
            funcaoSetup = "\tconstroi-plot\t;desenha grafico\n\n";
            funcaoGo = funcaoSetup;
            
            outrasFuncoes = ";funca" +
                    "o que desenha o grafico\n"+
                    "to constroi-plot\n" +
                    outrasFuncoes + 
                    "end\n\n";
        }
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
    
    private String criaCodigoPlots (Plot plot)
    {
        String codigo = "\t;desenhando grafico " + 
                plot.getNomeGrafico() + "\n";
        
        codigo += "\tset-current-plot \"" + 
                plot.getNomeGrafico() + "\"\n";
                
        //desenhando variaveis do plot
        Iterator iterator = plot.getCoresVariaveisPlot().iterator();
        while (iterator.hasNext())
        {
            CorVariavelPlot corVariavelPlot = (CorVariavelPlot) iterator.next();
            VariavelValor variavel = corVariavelPlot.getvariavelvalor();
            
            codigo += "\tset-current-plot-pen \"" +
                    variavel.getNome() + "\"\n";
            codigo += "\tplot " +
                    variavel.getNome() + "\n";
        }
        
        return codigo;
    }
    
}
