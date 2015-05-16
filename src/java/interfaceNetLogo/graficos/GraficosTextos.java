/*
 * GraficosTextos.java
 *
 * Created on 13 de Setembro de 2006, 17:59
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
public class GraficosTextos {
    
    //Configuracoes da tela
    int xMinimo, xMaximo;
    int yMinimo, yMaximo;
    int xAtual, yAtual;
    
    String codigo;
    
    public GraficosTextos(Modelo modelo, int xi, int xf, int yi, int yf)
    {
        xMinimo = xi;
        xMaximo = xf;
        yMinimo = yi;
        yMaximo = yf;
        xAtual = xi;
        yAtual = yi;
        
        codigo = "";
        
        Iterator iterator = modelo.getTextos().iterator();
        while (iterator.hasNext())
        {
            Texto texto = (Texto) iterator.next();
            criaGraficoTexto (texto);
        }
    }
    
    public String getCodigo()
    {
        return codigo;
    }
    
    public int getAlturaAtual()
    {
        return yAtual + 33;
    }
    
    //cada linha tem altura 13
    //cada caracter tem comprimento 7
    private void criaGraficoTexto (Texto texto)
    {
        codigo += "TEXTBOX\n";
        
        //posicionamento inicial
        if (xAtual + 7 * texto.getTexto().length() > xMaximo)
        {
            if (xAtual != xMinimo)
                yAtual += 13 + 5;
            xAtual = xMinimo;
        }
        codigo += xAtual + "\n" +
                yAtual + "\n";
        
        //Divide o texto em linhas
        String conteudoTexto = divideTextoLinhas (texto.getTexto(), 7, 13);
        
        //posicionamento final
        codigo += xAtual + "\n" +
                yAtual + "\n";
        
        codigo += conteudoTexto + "\n\n";
    }
    
    private String divideTextoLinhas (String texto, int comprimentoCaracter, 
            int alturaLinha)
    {
        int caracteresSobrando = (xMaximo - xAtual) / comprimentoCaracter;
        
        //Cabe em uma linha
        if (texto.length() <= caracteresSobrando)
        {
            xAtual += 5 + 7 * texto.length();
            return texto;
        }
        else
        {
            xAtual = xMinimo;
            caracteresSobrando = (xMaximo - xAtual) / comprimentoCaracter;
       
            String textoFinal = "";
            
            int i, j, numLinhas = 1;
            String palavra = "";
            for (i=0, j=0; i<texto.length(); i++)
            {
                if (texto.charAt(i) == ' ')
                {
                    if (i - j - 1 > caracteresSobrando)
                    {
                        textoFinal += texto.substring(j, i) + "\\n";
                        numLinhas++;
                        j = i + 1;
                    }
                }
            }
            textoFinal += texto.substring(j, i);
            
            yAtual += alturaLinha * numLinhas;
            xAtual = xMaximo;
            return textoFinal;
        }
        
        
    }
    
}
