/*
 * Graficos.java
 *
 * Criado em 4 de Julho de 2006, 19:01
 */

package interfaceNetLogo;

import java.util.List;
import interfaceNetLogo.graficos.*;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import utilitario.*;
import cdp.*;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class Graficos {
    
    Modelo modelo;
    
    //Configura��es da tela
    int xMinimo, xMaximo;
    int yMinimo, yMaximo;
    
    //Sessao do Hibernate
    Session sessao;
    
    public Graficos(int idModelo) 
    {
        //Inicia sessao do hibernate
        sessao = HibernateUtility.getSession();
        
        modelo = getModelo (idModelo);
        
        xMinimo = 5;
        yMinimo = 50;
        
        xMaximo = 345;
        yMaximo = 535;
    }
    
    public String getGraficos ( )
    {
        String graficos = configuracoesTela() +
                botaoSetup() +
                botaoGo();
        
        //Variaveis
        GraficosVariaveis graficosVariaveis = new GraficosVariaveis 
                (modelo, xMinimo, xMaximo, yMinimo, yMaximo);
        String codigo = graficosVariaveis.getCodigo();
        if (codigo.length() > 1)
        {
            graficos += codigo;
            yMinimo = graficosVariaveis.getAlturaAtual() + 5; 
        }
        
        //Monitores
        GraficosMonitores graficosMonitores = new GraficosMonitores
                (modelo, xMinimo, xMaximo, yMinimo, yMaximo);
        codigo = graficosMonitores.getCodigo();
        if (codigo.length() > 1)
        {
            graficos += codigo;
            yMinimo = graficosMonitores.getAlturaAtual() + 5; 
        }
        
        //Textos
        GraficosTextos graficosTextos = new GraficosTextos
                (modelo, xMinimo, xMaximo, yMinimo, yMaximo);
        codigo += graficosTextos.getCodigo();
        if (codigo.length() > 1)
        {
            graficos += codigo;
            yMinimo = graficosTextos.getAlturaAtual() + 5; 
        }
        
        //Plots
        GraficosPlots graficosPlots = new GraficosPlots
                (modelo, yMinimo);
        codigo = graficosPlots.getCodigo();
        if (codigo.length() > 1)
        {
            graficos += codigo;
            yMinimo = graficosPlots.getAlturaAtual() + 5;
        }
        
        //Botões
        GraficosBotao graficosBotao = new GraficosBotao(modelo, xMinimo, xMaximo, yMinimo, yMaximo);
        codigo = graficosBotao.getCodigo();
        if (codigo.length() > 1)
        {
            graficos += codigo;
            yMinimo = graficosBotao.getAlturaAtual() + 5;
        }
        
        sessao.close();
        
        return graficos;
    }
    
    private String configuracoesTela ( )
    {
        return "GRAPHICS-WINDOW\n"+
                "352\n"+
                "10\n" +
                "974\n"+
                "653\n"+
                "25\n"+
                "25\n"+
                "12.0\n"+
                "1\n"+
                "10\n"+
                "1\n"+
                "1\n"+
                "1\n"+
                "0\n"+
                "1\n"+
                "1\n"+
                "1\n" +
                "-25\n" +
                "25\n" +
                "-25\n" +
                "25\n\n"+
                "CC-WINDOW\n"+
                "5\n"+
                "667\n"+
                "983\n"+
                "762\n"+
                "Command Center\n"+
                "0\n\n";
    }
    
    private String botaoSetup ( )
    {
        return "BUTTON\n"+
                "72\n10\n135\n43\n" +
                "NIL\nsetup\nNIL\n" +
                "1\nT\nOBSERVER\nT\nNIL\n\n";
    }
    
    private String botaoGo ( )
    {
        return "BUTTON\n"+
                "207\n10\n270\n43\n"+
                "NIL\ngo\nT\n"+
                "1\nT\nOBSERVER\nT\nNIL\n\n";
    }
    
    private Modelo getModelo (int idModelo)
    {
        //Inicializa transa��o
        Transaction t = sessao.beginTransaction();
       
        //Consulta modelo pelo id
        Query consulta = sessao.createQuery("from Modelo m where m.idModelo= :id");
        consulta.setInteger("id", idModelo);
        List lista = consulta.list();
        Modelo modelo = (Modelo) lista.get(0);
        
        //Termina transa��o
        t.commit();
        
        return modelo;
    }
    
    //Destrutor da classe
    /*protected void finalize( )
    {
        //Termina sessao do hibernate
        sessao.close();
    }*/
}
