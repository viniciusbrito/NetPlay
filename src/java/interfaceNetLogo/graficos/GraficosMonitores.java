/*
 * GraficosMonitores.java
 *
 * Created on 11 de Setembro de 2006, 18:32
 */

package interfaceNetLogo.graficos;

import java.util.Set;
import java.util.Iterator;
import cdp.*;
import interfaceNetLogo.Traducao;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class GraficosMonitores {
    
    //Configuracoes da tela
    int xMinimo, xMaximo;
    int yMinimo, yMaximo;
    int xAtual, yAtual;
    
    String codigo;
    
    public GraficosMonitores(Modelo modelo, int xi, int xf, int yi, int yf) 
    {
        xMinimo = xi;
        xMaximo = xf;
        yMinimo = yi;
        yMaximo = yf;
        xAtual = xi;
        yAtual = yi;
        
        codigo = "";
        
        criaGraficoMonitor (modelo);
    }
    
    public String getCodigo()
    {
        return codigo;
    }
    
    public int getAlturaAtual()
    {
        return yAtual + 49;
    }
    
    //Comprimento minimo 50 
    //Se nome de variavel tiver mais de 5 caracteres => acrescentar 6 por letra ao comprimento
    //Altura 49
    private void criaGraficoMonitor (Modelo modelo)
    {
        Iterator iterator = modelo.getMonitores().iterator();
        while (iterator.hasNext())
        {
            Monitor monitor = (Monitor) iterator.next();
            
            codigo += "MONITOR\n";
            
            //Determinando comprimento e altura
            int altura = 49;
            int comprimento = 50;
            if (monitor.getNome().length() > 5)
                comprimento += 6 * (monitor.getNome().length() - 5);
                
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
            
            codigo += monitor.getNome() + "\n";
            
            //Determinando valores
            codigo += Traducao.expressao(monitor.getExpressao(), modelo) + "\n" + 
                    monitor.getCasasDecimais() + "\n" +
                    "1\n\n";
        }
    }
    
}
