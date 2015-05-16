/*
 * GraficosPlots.java
 *
 * Created on 13 de Setembro de 2006, 18:12
 *
 */

package interfaceNetLogo.graficos;

import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import cdp.*;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class GraficosPlots {
    
    //Configuracoes da tela
    int altura, comprimento;
    int xAtual, yAtual;
    
    String codigo;
    
    public GraficosPlots(Modelo modelo, int y)
    {
        xAtual = 22;
        yAtual = y;
        
        altura = 150;
        comprimento = 300;
        
        codigo = "";
        
        Iterator iterator = modelo.getPlots().iterator();
        while (iterator.hasNext())
        {
            Plot plot = (Plot) iterator.next();
            criaGraficoPlot (plot);
        }
    }
    
    public String getCodigo()
    {
        return codigo;
    }
    
    public int getAlturaAtual()
    {
        return yAtual + altura;
    }
    
    //altura sempre 150
    //comprimento sempre 200
    private void criaGraficoPlot (Plot plot)
    {
        codigo += "PLOT\n";
        
        //determinando posicionamento
        codigo += xAtual + "\n" + yAtual + "\n";
        codigo += (xAtual + comprimento) + "\n" +
                (yAtual + altura) + "\n";
        yAtual += altura + 5;
        
        //determinando graficos e legendas
        codigo += plot.getNomeGrafico() + "\n" +
                plot.getNomeEixoX() + "\n" +
                plot.getNomeEixoY() + "\n";
        
        //determinando limites dos eixos
        codigo += plot.getXMinimo() + "\n" +
                plot.getXMaximo() + "\n" + 
                plot.getYMinimo() + "\n" +
                plot.getYMaximo() + "\n";
        
        codigo += "true\ntrue\n";
        
        //determinando nome e cor das pens
        codigo += "PENS\n";
        Iterator iterator = plot.getCoresVariaveisPlot().iterator();
        while(iterator.hasNext())
        {
            CorVariavelPlot corVariavelPlot = (CorVariavelPlot) iterator.next();
            codigo += "\"" + corVariavelPlot.getvariavelvalor().getNome() +
                    "\" 1.0 " +
                    tipoGrafico(plot.getTipo()) + " " +
                    corGrafico(corVariavelPlot.getCor()) +
                    " true\n";
        }
        
        codigo += "\n\n";
    }
    
    private String tipoGrafico (String tipo)
    {
        if (tipo.equals("linha"))
            return "0";
        else
            if (tipo.equals("barra"))
                return "1";
            else
                return "2";//ponto
    }
    
    private String corGrafico (String cor)
    {
        if (cor.equals("AMARELO"))
            return "-1184463";
        if (cor.equals("AZUL"))
            return "-13345367";
        if (cor.equals("AZULCLARO") || cor.equals("AZUL CLARO"))
            return "-13791810";
        if (cor.equals("BRANCO"))
            return "-1";
        if (cor.equals("CIANO"))
            return "-11221820";
        if (cor.equals("CINZA"))
            return "-7500403";
        if (cor.equals("LARANJA"))
            return "-955883";
        if (cor.equals("MAGENTA"))
            return "-5825686";
        if (cor.equals("MARROM"))
            return "-6459832";
        if (cor.equals("PRETO"))
            return "-16777216";
        if (cor.equals("ROSA"))
            return "-2064490";
        if (cor.equals("VERDE"))
            return "-10899396";
        if (cor.equals("VERDETURQUESA") || cor.equals("VERDE TURQUESA"))
            return "-14835848";
        if (cor.equals("VERMELHO"))
            return "-2674135";
        if (cor.equals("VIOLETA"))
            return "-8630108";
        return "-13840069"; //verde limao
    }
    
}
