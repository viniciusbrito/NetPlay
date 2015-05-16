/*
 * ExportaArquivo.java
 *
 * Criado em 7 de Novembro de 2006, 17:33
 *
 */

package analiseGraficos;

import java.util.List;
import java.util.Iterator;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class ExportaArquivo {
    
    //conteudo do arquivo csv
    String arquivo;
    
    //nome e valor das variaveis do modelo
    String[] nomeVariaveis;
    String[] valorVariaveis;
    
    //nome das curvas do grafico
    List pens;
    
    //pontos do grafico
    List valores;
    
    //Para analise de maximo e minimo
    List maxMinValores;
    
    public ExportaArquivo (String nomeModelo, String nomeGrafico, 
            String[] nomeVariaveis, String[] valorVariaveis, List pens) 
    {
        arquivo = "MODELO;" + nomeModelo + "\n" +
                "GRAFICO;" + nomeGrafico + "\n\n";
        
        this.nomeVariaveis = nomeVariaveis;
        this.valorVariaveis = valorVariaveis;
        
        this.pens = pens;
        
        if (nomeVariaveis != null)
            arquivo += incluindoVariaveis();
    }
    
    public String getArquivo()
    {
        return arquivo;
    }
    
    public void setValores (List valores, String tipoAnalise)
    {
        this.valores = valores;
        arquivo += incluindoValores(tipoAnalise);
    }
    
    public void setValores (List maxMinValores, List valores, String tipoAnalise)
    {
        this.valores = valores;
        this.maxMinValores = maxMinValores;
        arquivo += incluindoValores(tipoAnalise);
    }
    
    private String incluindoVariaveis()
    {
        String arquivo = "VARIAVEIS DO MODELO\n";
        
        //incluindo nomes e valores das variaveis no arquivo
        for(int i=0; i<nomeVariaveis.length; i++)
            arquivo += nomeVariaveis[i] + ";" +
                    valorVariaveis[i] + "\n";
        arquivo += "\n";
        
        return arquivo;
    }
    
    private String incluindoValores (String tipoAnalise)
    {
        if (tipoAnalise.equals("Todos os pontos"))
            return "TODOS OS PONTOS DO GRAFICO\n" +
                    incluindoTodosPontos();
        
        if (tipoAnalise.equals("Principais pontos"))
            return "PRINCIPAIS PONTOS DO GRAFICO\n" +
                    incluindoPrincipaisPontos();
        
        if (tipoAnalise.equals("Maximo e minimo"))
            return "PONTOS MAXIMO e MINIMO DO GRAFICO\n\n\n" +
                    incluindoPontosMaxMin();
        
        if (tipoAnalise.equals("Intersecao"))
            return "PONTOS DE INTERSECAO\n" +
                    incluindoPontosIntersecao();
        
        return null;
    }
    
    private String incluindoTodosPontos()
    {
        String arquivo = "";
        
        //adicionando nome das curvas no arquivo
        Iterator iterator = pens.iterator();
        while(iterator.hasNext())
        {
            String pen = (String) iterator.next();
            arquivo += pen + ";";
        }
        arquivo += "\n";
        
        //adicionando os pontos do grafico no arquivo
        iterator = valores.iterator();
        while(iterator.hasNext())
        {
            for(int i=0; i<pens.size(); i++)
            {
                String valor = (String) iterator.next();
                arquivo += valor + ";";
            }
            arquivo += "\n";
        }
        
        return arquivo;
    }
    
    private String incluindoPrincipaisPontos()
    {
        String arquivo = "";
        
        Iterator i = pens.iterator();
        
        //Eliminando o nome do eixo x
        String pen = (String) i.next();
        
        while (i.hasNext())
        {
            pen = (String) i.next();
            arquivo += "CURVA;" + pen + "\n";
            
            //Imprimindo valores dessa curva
            Iterator j = valores.iterator();
            while(j.hasNext())
            {
                String x = (String) j.next();
                String y = (String) j.next();
                String curva = (String) j.next();
                if (curva.equals(pen))
                    arquivo += x + ";" + y + "\n";
            }
            arquivo += "\n";
        }
        
        return arquivo;
    }
    
    private String incluindoPontosMaxMin ()
    {
        String arquivo = "";
        
        Iterator i = pens.iterator();
           
        //Eliminando o nome do eixo x
        String pen = (String) i.next();
        
        while(i.hasNext())
        {
            pen = (String) i.next();
            boolean maximo = false; //indica que nao pegamos primeiro pto. max. dessa curva
            boolean minimo = false; //indica que nao pegamos primeiro pto. min. dessa curva
            
            //Imprimindo nome da curva
            arquivo += "CURVA;" + pen + "\n";
            
            Iterator j = maxMinValores.iterator();
            while(j.hasNext())
            {
                String tipo = (String) j.next();
                String x = (String) j.next();
                String y = (String) j.next();
                   
                if (pen.equals((String) j.next()))
                {
                    //Achando primeiro ponto maximo da curva
                    if(tipo.equals("maximo") && !maximo)
                    {
                            maximo = true;
                            arquivo += "Pontos(s) Maximo(s)\n";
         
                            //Imprimindo legenda
                            for(int k=0; k<pens.size(); k++)
                                arquivo += (String) pens.get(k) + ";";
                            arquivo += "\n";
                    }
                    
                    //Achando primeiro ponto minimo da curva
                    if(tipo.equals("minimo") && !minimo)
                    {
                            minimo = true;
                            arquivo += "Pontos(s) Minimo(s)\n";
         
                            //Imprimindo legenda
                            for(int k=0; k<pens.size(); k++)
                                arquivo += (String) pens.get(k) + ";";
                            arquivo += "\n";
                    }
                    
                    //Imprimindo valores
                    int posicao = pens.size() * (int) Double.parseDouble(x);
                    for (int k=posicao; k<pens.size() + posicao; k++)
                        arquivo += (String) valores.get(k) + ";";
                    arquivo += "\n";
                }
            }
            arquivo += "\n";
        }
        
        return arquivo;
    }
    
    private String incluindoPontosIntersecao()
    {
        String arquivo = "";
        
        if (valores.size() == 0)
            return "Nao ha intersecao entre as curvas desse grafico\n";
        
        arquivo += "Eixo x;Eixo y; Curva 1; Curva 2\n";
        
        Iterator iterator = valores.iterator();
        while(iterator.hasNext())
        {
            for (int i=0; i<4; i++)
            {
                String valor = (String) iterator.next();
                arquivo += valor + ";";
            }
            arquivo += "\n";
        }
        
        return arquivo;
    }
    
}
