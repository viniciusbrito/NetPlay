/*
 * Grafico.java
 *
 * Criado em 28 de Outubro de 2006, 15:19
 *
 */

package analiseGraficos;

import java.util.Map;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;
import de.laures.cewolf.ChartPostProcessor;

/**
 *
 * @author Lucio Marcos Barbiero
 */
public class Grafico implements DatasetProducer, Serializable {
    
    List pens;
    List valores;
    
    List valoresMaxMin;
    
    String tipoGrafico = null;
    
     public Object produceDataset(Map params) throws DatasetProduceException {
         if (tipoGrafico.equals("Todos os pontos"))
            return setarTodosValoresGrafico();
         
         if (tipoGrafico.equals("Principais pontos"))
            return setarPrincipaisValoresGrafico();
         
         if (tipoGrafico.equals("Maximo e minimo"))
            return setarMaximoMinimo();
         
         return null;         
     }
    
    public void setPens (List pens2)
    {
        pens = pens2;
    }
    
    public void setValores (List valores2)
    {
        valores = valores2;
    }
    
    public void setValores (List valores, List valoresMaxMin)
    {
        this.valores = valores;
        this.valoresMaxMin = valoresMaxMin;
    }
    
    public void setTipoGrafico (String tipo)
    {
        tipoGrafico = tipo;
    }
    
    private XYSeriesCollection setarTodosValoresGrafico ()
    {
        //Criando dataSet
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        Iterator i = pens.iterator();
        
        //Eliminando o pen eixo x
        String pen = (String) i.next();
        int pos = 1;
        while(i.hasNext())
        {
            //Criando uma curva no grafico
            pen = (String) i.next();
            XYSeries serie = new XYSeries(pen);
            
            //Colocando os valores na curva
            Iterator j = valores.iterator();
            while(j.hasNext())
            {
                //lendo valor do eixo x
                double x = Double.parseDouble((String) j.next());
                
                //lendo valor do eixo y
                double y = 0;
                for (int k = 0; k < pens.size() - 1; k++)
                {
                    y = Double.parseDouble((String) j.next());
                    
                    if (k == pos - 1)
                    {
                        //adicionando a curva
                        serie.add(x, y);
                    }
                }
            }
            
            //Adicionando curva no grafico
            dataset.addSeries(serie);
            
            pos++;
        }
        
        return dataset;
    }
    
    private XYSeriesCollection setarPrincipaisValoresGrafico ()
    {
        //Criando dataSet
        XYSeriesCollection dataset = new XYSeriesCollection();
                
        Iterator i = pens.iterator();
        
        //Eliminando o pen eixo x
        String pen = (String) i.next();
        int pos = 1;
        while(i.hasNext())
        {
            //Criando uma curva no grafico
            pen = (String) i.next();
            XYSeries serie = new XYSeries(pen);
            
            //Colocando os valores na curva
            Iterator j = valores.iterator();
            while(j.hasNext())
            {
                //lendo valor dos eixos x e y
                double x = Double.parseDouble((String) j.next());
                double y = Double.parseDouble((String) j.next());
                
                //lendo o pen dos pontos
                if (pen.equals((String) j.next()))
                    serie.add(x, y);
            }
            
            //Adicionando curva no grafico
            dataset.addSeries(serie);
            
            pos++;
        }
        
        return dataset;
    }
    
    private XYSeriesCollection setarMaximoMinimo ()
    {
        //Criando dataSet
        XYSeriesCollection dataset = setarTodosValoresGrafico();
        
        Iterator i = pens.iterator();
        //Eleminando nome do eixo X
        String pen = (String) i.next();
        while(i.hasNext())
        {
            pen = (String) i.next();
            boolean maximo = false;
            boolean minimo = false;
            
            //Criando outra curva com os ptos. maximo e minimo dessa curva
            XYSeries serie = new XYSeries("Maximo e minimo de " + pen);
            
            //Pegando os ptos. de maximo e minimo dessa curva
            Iterator j = valoresMaxMin.iterator();
            while(j.hasNext())
            {
                String tipo = (String) j.next();
                double x = Double.parseDouble((String) j.next());
                double y = Double.parseDouble((String) j.next());
                
                //Inserindo na curva
                if (((String) j.next()).equals(pen))
                {
                    if(tipo.equals("maximo") && !maximo)
                    {
                        serie.add(x, y);
                        maximo = true;
                    }
                    if(tipo.equals("minimo") && !minimo)
                    {
                        serie.add(x, y);
                        minimo = true;
                    }
                }
            }
            
            //Inserindo no grafico
            dataset.addSeries(serie);
        }
        
        return dataset;
    }
    
    //define tempo maximo de 5 segundos para mostrar o grafico
    public boolean hasExpired(Map params, Date since) 
    {       
        return (System.currentTimeMillis() - since.getTime())  > 5000;
    }
    
    //define id para classe
    public String getProducerId() 
    {
        return "PageViewCountData DatasetProducer";
    }
    
    //Destaca pontos no grafico
    public ChartPostProcessor destacaPontos()
    {
        ChartPostProcessor grafico = new ChartPostProcessor() {
            public void processChart(Object obj, java.util.Map map) 
            {
                //Capturando o grafico
                JFreeChart chart = (JFreeChart) obj;
        
                //Pegando o plot do grafico
                XYPlot plot = (XYPlot) chart.getPlot();
        
                //Determinando a renderizacao do grafico
                XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
                
                //Para as curvas originais => nao destacar os pontos
                for (int i=0; i<pens.size() - 1; i++)
                    renderer.setSeriesShapesVisible(i, false);
                
                //Para as curvas de max e min => destacar os pontos
                for (int i=0; i<pens.size() - 1; i++)
                    renderer.setSeriesLinesVisible(i + pens.size() - 1, false);
            
                //renderizando o grafico
                plot.setRenderer(renderer);
            }
        };
        
        return grafico;
    }
}
