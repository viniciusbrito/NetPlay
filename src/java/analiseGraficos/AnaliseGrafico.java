/*
 * AnaliseGrafico.java
 *
 * Criado em 23 de Outubro de 2006, 23:00
 *
 */

package analiseGraficos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import utilitario.*;
import cdp.*;
import java.io.RandomAccessFile;
import java.util.Formatter;
import java.util.Set;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Comparator;
import java.text.DecimalFormat;

/**
 *
 * @author Lucio Marcos Barbiero
 */
public class AnaliseGrafico {
    
    int idModelo;
    
    //Pasta e nome do arquivo com os dados dos graficos
    String pasta = cgt.APICadastrarAnaliseGraficos.getPasta();
    String nomeArquivo;
    
    RandomAccessFile arquivo;
    
    //nome do modelo
    String nomeModelo;
    
    //Variaveis do modelo
    String[] nomeVariaveis = null;
    String[] valorVariaveis = null;
    
    //Nome dos plots do modelo
    String[] plots;
    
    //Para um grafico determinado:
    List pens;
    List valores; 
    
    //Nome das legendas de um grafico determinado
    String legendaX;
    String legendaY;
    
    public AnaliseGrafico (int idModelo, String nomeArquivo) 
    {
        this.idModelo = idModelo;
        this.nomeArquivo = nomeArquivo;
        
        //Pesquisa nome do modelo e abre arquivo com os valores do grafico
        abrirArquivo(idModelo);
        
        //pegando nomes e valores das variaveis do modelo
        getVariaveis();
    }
    
    public String getNomeModelo()
    {
        return nomeModelo;
    }
    
    public List getPens()
    {
        return pens;
    }
    
    public List getValores()
    {
        return valores;
    }
    
    public String[] getNomeVariaveis()
    {
        return nomeVariaveis;
    }
    
    public String[] getValorVariaveis()
    {
        return valorVariaveis;
    }
    
    public String[] getNomeGraficos()
    {
        return plots;
    }
    
    public String getLegendaX() 
    {
        return legendaX;
    }
    
    public String getLegendaY()
    {
        return legendaY;
    }
    
    //Pesquisa nome do modelo e variaveis do mesmo
    private void configuracoesIniciais (int idModelo)
    {
        //Pesquisa nome do modelo e abre arquivo
        abrirArquivo(idModelo);
        
        //pegando valores das variaveis do modelo
        getVariaveis();
    }
    
    protected void finalize()
    {
        fecharArquivo();
    }
    
    private void abrirArquivo (int idModelo)
    {
        //Inicia sessao do hibernate
        Session sessao = HibernateUtility.getSession();
        
        //Inicializa transacao
        Transaction t = sessao.beginTransaction();
       
        //Consulta modelo pelo id
        Query consulta = sessao.createQuery("from Modelo m where m.idModelo= :id");
        consulta.setInteger("id", idModelo);
        List lista = consulta.list();
        Modelo modelo = (Modelo) lista.get(0);
        
        //Pegando nome do modelo
        nomeModelo = modelo.getNome();
        
        //Abre arquivo para somente leitura
        try {
            arquivo = new RandomAccessFile(
                    pasta + nomeArquivo + ".csv", "r");             
        } catch (java.io.IOException e) {}        
               
        //Nome dos plots do modelo
        getPlots(modelo);
        
        //Termina transacao
        t.commit();
        
        //Termina sessao do hibernate
        sessao.close();
    }
    
    public void fecharArquivo ()
    {
        try {
            arquivo.close();
        } catch (java.io.IOException e) {}
    }
    
    private void getPlots (Modelo modelo)
    {
        //pegando os graficos do modelo
        Set graficos = modelo.getPlots();
        
        //Inicializando lista com os nomes dos graficos
        plots = new String[graficos.size()];
        
        //Pegando o nome de cada plot e inserindo na lista
        int i=0;
        Iterator iterator = graficos.iterator();
        while (iterator.hasNext())
        {
            Plot grafico = (Plot) iterator.next();
            plots[i] = grafico.getNomeGrafico();
            i++;
        }
        
        ordenaPlots();
    }
    
    private void ordenaPlots()
    {
        //ordena o vetor com os nomes dos plots
        Arrays.sort( plots,
                new Comparator() {
                    public int compare( Object obj1, Object obj2 ) {
                        return ((String)obj1).compareTo((String)obj2);
                    }
                }
        );
    }
    
    private void getVariaveis()
    {
        String linha = " ";
        
        try{
            //Nome das variaveis esta na 2a. linha apos a 1a. linha em branco
            while(!linha.equals(""))
            {
                linha = arquivo.readLine();
            }
            linha = arquivo.readLine();
            linha = arquivo.readLine();
            
            //Separando as variaveis e tirando as aspas
            if (!linha.equals(""))
                nomeVariaveis = (linha.replaceAll("\"", "")).split(",");
            
            linha = arquivo.readLine();
            valorVariaveis = (linha.replaceAll("\"", "")).split(",");
                       
        } catch (java.io.IOException e) {}
    }
    
    private void definindoLegendas (String plot)
    {
        //Inicia sessao do hibernate
        Session sessao = HibernateUtility.getSession();
        
        //Inicializa transacao
        Transaction t = sessao.beginTransaction();
       
        //Consulta modelo pelo id
        Query consulta = sessao.createQuery("from Modelo m where m.idModelo= :id");
        consulta.setInteger("id", idModelo);
        List lista = consulta.list();
        Modelo modelo = (Modelo) lista.get(0);
        
        //procurando grafico com nome "plot"
        Set graficos = modelo.getPlots();
        Iterator iterator = graficos.iterator();
        while(iterator.hasNext())
        {
            Plot grafico = (Plot) iterator.next();
            if (grafico.getNomeGrafico().equals(plot))
            {
                //pegado legenda dos eixos x e y
                legendaX = grafico.getNomeEixoX();
                legendaY = grafico.getNomeEixoY();
            }
        }
        
        //Termina transacao
        t.commit();
        
        //Termina sessao do hibernate
        sessao.close();
    }
    
    //Pega todos os pontos do grafico
    public void verPontos (String plot)
    {
        definindoLegendas (plot);
        
        try {        
            acharLinhaGrafico (plot);     
            
            //pula x linhas -> x = numero de pens do grafico
            String linha = "algo diferente de espaco";
            while (!(linha.equals("")))
            {
                linha = arquivo.readLine();
            }
            
            //le o nome dos pens
            String[] penSpace = arquivo.readLine().replaceAll("\"","").split(",");
            pens = new ArrayList();
            pens.add("Eixo x");
            for (int i=0; i<penSpace.length; i++)
                if (!penSpace[i].equals(""))
                    pens.add(penSpace[i]);
            
            //pula 1 linha
            linha = arquivo.readLine();
            
            //construindo tabela com os valores do grafico
            valores = new ArrayList();
            while (!(linha.equals("")))
            {
                linha = arquivo.readLine();
                if (linha == null || linha.length() == 0)
                    break;

                String[] val = linha.replaceAll("\"", "").split(",");
                
                valores.add(val[0]);
                for (int k=1, j=1; k<val.length; k+=4, j++)
                {
                    valores.add(val[k]);
                }
            }
            
            /*System.out.println("Imprimindo valores");
            Iterator i = valores.iterator();
            while (i.hasNext())
            {
                String a = (String) i.next();
                double b = Double.parseDouble(a);
                
            }*/
            
        } catch (java.io.IOException e) {}
          catch (java.lang.NullPointerException e) {}
    }
    
    public List verPrincipaisPontos()
    {
        //Lista contendo os pontos de maximo e minimo locais do grafico
        //Contem 3 elemento (x, y, pen)
        List princValores = new ArrayList();
        
        Iterator i = pens.iterator();
        //Eliminando o pen eixo x
        String pen = (String) i.next();
        
        int pos = 1;
        while(i.hasNext())
        {
            //Nome do pen
            pen = (String) i.next();
            
            char estado = '-'; //quer dizer que tem que armazenar o primeiro ponto
            double ultimoValorX = 0;
            double ultimoValorY = 0;
            
            //Se houver 50 pontosNaoCapturados => armazena esse ponto
            int pontosNaoCapturados = 0;
            
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
                        //Verifica se tem que adicinar
                        if (estado == '-')
                        {
                            princValores.add(String.valueOf(x));
                            princValores.add(String.valueOf(y));
                            princValores.add(pen);
                            estado = '=';
                        }
                        else
                            if (estado == '=')
                            {
                                if (y != ultimoValorY)
                                {
                                    princValores.add(String.valueOf(ultimoValorX));
                                    princValores.add(String.valueOf(ultimoValorY));
                                    princValores.add(pen);
                                    if (y > ultimoValorY)
                                        estado = '>';
                                    else
                                        estado = '<';
                                    pontosNaoCapturados = 0;
                                }
                            }
                            else
                                if (estado == '>')
                                {
                                    if (y <= ultimoValorY)
                                    {
                                        princValores.add(String.valueOf(ultimoValorX));
                                        princValores.add(String.valueOf(ultimoValorY));
                                        princValores.add(pen);
                                        if (y < ultimoValorY)
                                            estado = '<';
                                        else
                                            estado = '=';
                                        pontosNaoCapturados = 0;
                                    }
                                }
                                else
                                    if (estado == '<')
                                    {
                                        if (y >= ultimoValorY)
                                        {
                                            princValores.add(String.valueOf(ultimoValorX));
                                            princValores.add(String.valueOf(ultimoValorY));
                                            princValores.add(pen);
                                            if (y > ultimoValorY)
                                                estado = '>';
                                            else
                                                estado = '=';
                                            pontosNaoCapturados = 0;
                                        }
                                    }
                        ultimoValorX = x;
                        ultimoValorY = y;
                        
                        //Se houver mais 50 pontos nao caputurados => armazena
                        pontosNaoCapturados++;
                        if (pontosNaoCapturados > 30)
                        {
                            princValores.add(String.valueOf(ultimoValorX));
                            princValores.add(String.valueOf(ultimoValorY));
                            princValores.add(pen);
                            pontosNaoCapturados = 0;
                        }
                    }
                }
            }
            //Armazenando ultimo ponto da curva
            princValores.add(String.valueOf(ultimoValorX));
            princValores.add(String.valueOf(ultimoValorY));
            princValores.add(pen);
            
            pos++;
        }
        
        return eliminaRepetidos(princValores);
    }
    
    public List verMaxMin ()
    {
        //Lista com os pontos maximo e minimo absolutos de cada curva
        //ex.: (maximo, x, y, nome da curva)
        List novosValores = new ArrayList();
        
        double xMaximo, xMinimo, yMaximo, yMinimo;
        
        Iterator i = pens.iterator();
        //Eliminando o pen eixo x
        String pen = (String) i.next();
        
        int pos = 1;
        while(i.hasNext())
        {
            //Nome do pen
            pen = (String) i.next();
            
            //definindo maximo e minimo
            yMaximo = Double.MIN_VALUE;
            yMinimo = Double.MAX_VALUE;
            xMinimo = 0;
            xMaximo = 0;
            List xMinimos = new ArrayList();
            List xMaximos = new ArrayList();
            
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
                        if (y == yMinimo)
                            xMinimos.add(String.valueOf(x));
                        if (y == yMaximo)
                            xMaximos.add(String.valueOf(x));
                        
                        if (y > yMaximo)
                        {
                            yMaximo = y;
                            xMaximo = x;
                            xMaximos.clear();
                            xMaximos.add(String.valueOf(x));
                        }
                        if (y < yMinimo)
                        {
                            yMinimo = y;
                            xMinimo = x;
                            xMinimos.clear();
                            xMinimos.add(String.valueOf(x));
                        }
                    }
                }
            }
            pos++;
            
            //Adicionando os pontos de maximo a lista
            j = xMaximos.iterator();
            while(j.hasNext())
            {
                novosValores.add("maximo");
                novosValores.add(j.next());
                novosValores.add(String.valueOf(yMaximo));
                novosValores.add(pen);
            }
            
            //Adicionando os pontos de minimo a lista
            j = xMinimos.iterator();
            while(j.hasNext())
            {
                novosValores.add("minimo");
                novosValores.add(j.next());
                novosValores.add(String.valueOf(yMinimo));
                novosValores.add(pen);
            }
        }
                
        return novosValores;
    }
    
    public List verIntersecao ()
    {
        //(x, y, curva1, curva2)
        List pontosIntersecao = new ArrayList();
        
        float[] pontos = new float[pens.size()-1];
        float[] pontosAnteriores = new float[pens.size()-1];
        
        for (int i=0; i<valores.size();)
        {
            //Valor do eixo x
            float x = Float.parseFloat((String) valores.get(i));
            
            i++;
            for (int k=0; k<pens.size() - 1; k++, i++)
            {
                //Valores anteriores das curvas
                if(x > 0)
                    pontosAnteriores[k] = pontos[k];
                
                //Valores das curvas
                pontos[k] = Float.parseFloat((String)valores.get(i));
            }
            
            acharIntersecao(pontos, pontosAnteriores, x, pontosIntersecao);
        }
        
        return eliminaRepitidos (pontosIntersecao);
    }
    
    private List eliminaRepitidos (List pontosIntersecao)
    {
        List pontos = new ArrayList();
        
        String[] elementosAnteriores = new String[4];
        boolean ehPrimeiroElemento = true;
        
        Iterator i = pontosIntersecao.iterator();
        while(i.hasNext())
        {
            String[] elementosAtuais = new String[4];
            for (int j=0; j<4; j++)
                elementosAtuais[j] = (String) i.next();
            
            if (!ehPrimeiroElemento)
            {   
                if (!(elementosAtuais[0].equals(elementosAnteriores[0])) ||
                    !(elementosAtuais[1].equals(elementosAnteriores[1])) ||
                    !(elementosAtuais[2].equals(elementosAnteriores[2])) ||
                    !(elementosAtuais[3].equals(elementosAnteriores[3])))
                {
                    pontos.add(elementosAtuais[0]);
                    pontos.add(elementosAtuais[1]);
                    pontos.add(elementosAtuais[2]);
                    pontos.add(elementosAtuais[3]);
                }
            }
            else
            {
                pontos.add(elementosAtuais[0]);
                pontos.add(elementosAtuais[1]);
                pontos.add(elementosAtuais[2]);
                pontos.add(elementosAtuais[3]);
            }
            
            ehPrimeiroElemento = false;
            
            elementosAnteriores = elementosAtuais;
        }
        
        return pontos;
    }
    
    private void acharIntersecao (float[] pontos, float[] pontosAnteriores,
            float x, List pontosIntersecao)
    {
        //Calculando equacao das retas envolvendo dois pontos y = ax + b
        float[] a = new float[pontos.length];
        float[] b = new float[pontos.length];
        float xAnterior = x - 1;
        
        //Se pontosAnteriores for nulo
        if (x == 0)
            pontosAnteriores = pontos;
        
        //Calculando a e b de cada curva
        for(int i=0; i<pontos.length; i++)
        {
            float y1 = pontosAnteriores[i];
            float y2 = pontos[i];
            
            a[i] = (y2 - y1) / (x - xAnterior);
            b[i] = y1 - a[i] * xAnterior;
        }
        
        //Verificando se ha intersecao entre as curvas
        for(int i=0; i<pontos.length; i++)
            for(int j=i; j<pontos.length; j++)
            {
                //xp = -db / da
                float da = a[i] - a[j];
                float db = b[i] - b[j];
                
                //ha intersecao
                if (da != 0 || db == 0)
                {
                    //Verifica se xp esta entre x e xAnterior
                    float xp = -db / da;
                    
                    if (xp >= xAnterior && xp <= x)
                    {
                        DecimalFormat casas = new DecimalFormat("0.00");
                        
                        float yp = a[i] * xp + b[i];
                        pontosIntersecao.add(casas.format(xp));
                        pontosIntersecao.add(casas.format(yp));
                        pontosIntersecao.add(pens.get(i+1));
                        pontosIntersecao.add(pens.get(j+1));
                    }
                }
            }
    }
    
    private List eliminaRepetidos (List lista)
    {
        String x_anterior = "";
        String y_anterior = "";
        String pen_anterior = "";
        
        List novaLista = new ArrayList();
        
        Iterator iterator = lista.iterator();
        while(iterator.hasNext())
        {
            String x = (String) iterator.next();
            String y = (String) iterator.next();
            String pen = (String) iterator.next();
            
            if (!(x.equals(x_anterior) && y.equals(y_anterior) && 
                    pen.equals(pen_anterior)))
            {
                novaLista.add(x);
                novaLista.add(y);
                novaLista.add(pen);
                
                x_anterior = x;
                y_anterior = y;
                pen_anterior = pen;
            }
        }
        
        return novaLista;
    }
    
    private void acharLinhaGrafico (String plot)
    {
        try{
            //vai ao comeco do arquivo
            arquivo.seek(0);
            
            //vai ate a linha onde tem o primeiro grafico => linha 8
            String linha = null;
            for (int i=0; i<=8; i++)
                linha = arquivo.readLine();
            
            //Procurando o grafico que se quer analisar
            while (!(linha.replaceAll("\"", "").equals(plot)))
            {
                //Proximo grafico vai estar depois de 3 linhas em branco
                for (int j=0; j<3;)
                {
                    linha = arquivo.readLine();
                    if (linha.equals(""))
                        j++;
                }
                linha = arquivo.readLine();
            }
            
            //Pula mais 6 linhas => linha com os nomes das pens
            for (int i=0; i<4; i++)
                linha = arquivo.readLine();
            
        } catch (java.io.IOException e) {}
    }
    
    //retorna conteudo do arquivo csv com os pontos analisados
    public String getArquivo (String tipoAnalise, String nomeGrafico, List pontos)
    {
        ExportaArquivo exportaArquivo = new ExportaArquivo(nomeModelo, 
                nomeGrafico, nomeVariaveis, valorVariaveis, pens);
        
        if (tipoAnalise.equals("Todos os pontos"))
            exportaArquivo.setValores(pontos, tipoAnalise);
        
        if (tipoAnalise.equals("Principais pontos"))
            exportaArquivo.setValores(pontos, tipoAnalise);
        
        if (tipoAnalise.equals("Maximo e minimo"))
            exportaArquivo.setValores(pontos, valores, tipoAnalise);
        
        if (tipoAnalise.equals("Intersecao"))
            exportaArquivo.setValores(pontos, tipoAnalise);
        
        return exportaArquivo.getArquivo();
    }
}
