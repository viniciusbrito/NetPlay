/*
 * NovaAnaliseGrafica.java
 *
 * Created on 27 de Novembro de 2006, 21:44
 *
 */

package analiseGraficos;

import org.apache.commons.fileupload.FileItem;

import cdp.*;

import java.io.File;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import utilitario.*;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class NovaAnaliseGrafica {
    
    //Pasta onde estao os arquivos
    static String pasta = cgt.APICadastrarAnaliseGraficos.getPasta();
    
    //nome do novo arquivo contendo a analise grafica
    String nomeArquivo;
    
    public NovaAnaliseGrafica (int idModelo, FileItem item) 
    {
        //pegando nome do arquivo
        setNomeArquivo (idModelo);
        
        //criando arquivo
        criandoArquivo (item);
    }
    
    public String getNomeArquivo ()
    {
        return nomeArquivo + ".csv";
    }
    
    public static String getPasta()
    {
        return pasta;
    }
    
    private void criandoArquivo (FileItem item)
    {
        try {
            File arquivo = new File(pasta + nomeArquivo + ".csv");
            item.write(arquivo);
        } catch (java.lang.Exception e) {};
    }
    
    private void setNomeArquivo (int idModelo)
    {
        //Iniciando sessao do hibernate
        Session sessao = HibernateUtility.getSession();
        
        //Iniciando transacao
        Transaction t = sessao.beginTransaction();
       
        //Selecionando modelo
        Query consulta = sessao.createQuery("from Modelo m where m.idModelo= :id");
        consulta.setInteger("id", idModelo);
        List lista = consulta.list();
        Modelo modelo = (Modelo) lista.get(0);
        
        //nomeArquivo = modelo.getProximaAnalise();
        nomeArquivo = "teste";
        
        //Finalizando transacao e sessao
        t.commit();
        sessao.close();
    }
    
}
