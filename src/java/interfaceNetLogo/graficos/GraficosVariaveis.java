/*
 * GraficosVariaveis.java
 *
 * Created on 8 de Setembro de 2006, 12:58
 *
 * Cria graficos para variaveis slider e logica
 * que nao estao relacionadas a agentes.
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
public class GraficosVariaveis {
    
    ArrayList variaveisSlider;
    ArrayList variaveisLogica;
    
    //Configuracoes da tela
    int xMinimo, xMaximo;
    int yMinimo, yMaximo;
    int xAtual, yAtual;
    
    String codigo;
    
    public GraficosVariaveis (Modelo modelo, int xi, int xf, int yi, int yf) 
    {
        xMinimo = xi;
        xMaximo = xf;
        yMinimo = yi;
        yMaximo = yf;
        xAtual = xi;
        yAtual = yi;
        
        codigo = "";
        
        variaveisLogica = new ArrayList();
        variaveisSlider = new ArrayList();
        
        criaGraficoVariavel(modelo);
    }
    
    public String getCodigo()
    {
        return codigo;
    }
    
    public int getAlturaAtual()
    {
        return yAtual + 33;
    }
    
    private void criaGraficoVariavel (Modelo modelo)
    {   Iterator iterator = modelo.getVariaveis().iterator();
        while(iterator.hasNext())
        {
            Variavel variavel = (Variavel) iterator.next();
            if (variavel.getAgente() == null)
            {
                //Variavel slider
                if(variavel.getTipo().equals("Slider"))
                    variaveisSlider.add((VariavelSlider) variavel);
                
                //Variavel logica 
                if(variavel.getTipo().equals("Logica"))
                    variaveisLogica.add((VariavelLogica) variavel);
            }
        }
        
        criaGraficoLogica();
        criaGraficoSlider();
    }
    
    //Comprimento minimo 90 
    //Se nome de variavel tiver mais de 7 caracteres => acrescentar 6 por letra ao comprimento
    //Altura 33
    private void criaGraficoLogica()
    {
        Iterator iterator = variaveisLogica.iterator();
        while(iterator.hasNext())
        {
            VariavelLogica variavel = (VariavelLogica) iterator.next();
            
            codigo += "SWITCH\n";
            
            //Determinando comprimento e altura
            int altura = 33;
            int comprimento = 90;
            if (variavel.getNome().length() > 7)
                comprimento += 6 * (variavel.getNome().length() - 7);
                            
            //Determinando posicionamento
            if (xAtual + comprimento > xMaximo)
            {
                yAtual += altura + 5;
                xAtual = xMinimo;
            }
            codigo += xAtual + "\n" + yAtual + "\n";
            codigo += (xAtual + comprimento) + "\n" + 
                    (yAtual + altura) + "\n";
            xAtual += comprimento + 5;
            
            //determinando nome
            codigo += variavel.getNome() + "\n" +
                    variavel.getNome() + "\n";
            
            //determinando valor inicial
            //1 eh falso e 0 eh verdadeiro
            if (variavel.getValorLogico())
                codigo += "0";
            else
                codigo += "1";
            
            codigo += "\n1\n-1000\n\n";
        }
    }
    
    //Comprimento minimo 90 
    //Se nome de variavel + valor tiver mais de 10 caracteres => acrescentar 6 por letra ao comprimento
    //Altura 33
    private void criaGraficoSlider()
    {
        Iterator iterator = variaveisSlider.iterator();
        while(iterator.hasNext())
        {
            VariavelSlider variavel = (VariavelSlider) iterator.next();
            
            codigo += "SLIDER\n";
            
            //Determinando comprimento e altura
            int altura = 33;
            int comprimento = 90;
            if (variavel.getNome().length() + 
                    String.valueOf(variavel.getValorMaximo()).length() > 10)
                comprimento += 6 * (variavel.getNome().length() - 10 +
                        String.valueOf(variavel.getValorMaximo()).length());
                
            //Determinando posicionamento
            if (xAtual + comprimento > xMaximo)
            {
                yAtual += altura + 5;
                xAtual = xMinimo;
            }
            codigo += xAtual + "\n" + yAtual + "\n";
            codigo += (xAtual + comprimento) + "\n" + 
                    (yAtual + altura) + "\n";
            xAtual += comprimento + 5;
            
            //determinando nome
            codigo += variavel.getNome() + "\n" +
                    variavel.getNome() + "\n";
            
            //determinando valores
            codigo += variavel.getValorMinimo() + "\n" +
                    variavel.getValorMaximo() + "\n" +
                    variavel.getValorInicial() + "\n" +
                    variavel.getIncremento() + "\n";
            
            codigo += "1\nNIL\n\n";
        }
    }
    
}
