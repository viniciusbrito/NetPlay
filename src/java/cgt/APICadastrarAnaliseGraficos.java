/*
 * APICadastrarAnaliseGraficos.java
 *
 * Criado em 28 de Novembro de 2006, 18:37
 *
 */

package cgt;

import cdp.*;

import java.util.List;
import java.util.Date;
import java.io.File;
import org.apache.commons.fileupload.FileItem;

import utilitario.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class APICadastrarAnaliseGraficos {
    
    //Pasta onde estao os arquivos
    //static String pasta = "C:\\L�cio\\NetPlay ATUAL\\ProjetoFinal\\web\\graficos\\";
    static String pasta = "/usr/lib/apache-tomcat/webapps/NetPlay/graficos";
    
    public APICadastrarAnaliseGraficos() {
    }
    
    public static String incluirNovaAnalise (int idModelo, int idUsuario,
            FileItem item)
    {
        String nomeArquivo;
        
        //Iniciando sessao do hibernate
        Session sessao = HibernateUtility.getSession();
        
        //Iniciando transacao
        Transaction t = sessao.beginTransaction();
       
        //Selecionando modelo
        Query consulta = sessao.createQuery("from Modelo m where m.idModelo= :id");
        consulta.setInteger("id", idModelo);
        List lista = consulta.list();
        Modelo modelo = (Modelo) lista.get(0);
        
        //Selecionando usuario
        consulta = sessao.createQuery("from Usuario u where u.idUsuario= :id");
        consulta.setInteger("id", idUsuario);
        lista = consulta.list();
        Usuario usuario = (Usuario) lista.get(0);
        
        //Data de criacao
        Date dataCriacao = new Date();
        
        //selecionando nome do arquivo        
        nomeArquivo = modelo.getIdModelo() + "#" + dataCriacao.getTime();  
        
        //Inserindo analise no banco
        AnaliseGraficos analise = new AnaliseGraficos();
        analise.setModelo(modelo);
        analise.setUsuario(usuario);
        analise.setNomeArquivo(nomeArquivo);
        analise.setDataCriacao(dataCriacao);
        sessao.save(analise);
        
        //Finalizando transacao e sessao
        t.commit();
        sessao.close();
        
        //salvando arquivo contendo os pontos dos graficos do modelo
        criarArquivo (item, nomeArquivo);
        
        return nomeArquivo;
    }
    
    public static void excluir (int idAnalise)
    {
        //Iniciando sessao do hibernate
        Session sessao = HibernateUtility.getSession();
        
        //Iniciando transacao
        Transaction t = sessao.beginTransaction();
        
        //selecionando analise
        Query consulta = sessao.createQuery("from AnaliseGraficos a where a.idAnalise = :id");
        consulta.setInteger("id", idAnalise);
        List lista = consulta.list();
        AnaliseGraficos analise = (AnaliseGraficos) lista.get(0);
        
        analise.setModelo(null);
        analise.setUsuario(null);
        analise.setDataCriacao(null);
        
        //apagando arquivo
        System.out.println(pasta + analise.getNomeArquivo() + ".csv\n\n");
        File arquivo = new File(pasta + analise.getNomeArquivo() + ".csv");
        arquivo.delete();
        analise.setNomeArquivo(null);
        
        //deletando analise
        sessao.delete(analise);
        
        //Finalizando transacao e sessao
        t.commit();
        sessao.close();
    }
    
    public static String getPasta()
    {
        return pasta;
    }
    
    private static void criarArquivo (FileItem item, String nomeArquivo)
    {
        try {
            File arquivo = new File(pasta + nomeArquivo + ".csv");
            item.write(arquivo);
        } catch (java.lang.Exception e) {};
    }
    
    public static String formataData(Date data)
    {
        String dataString = data.toString();
        
        String ano = dataString.split("-")[0];
        String mes = dataString.split("-")[1];
        String dia = dataString.split("-")[2].split(" ")[0];
        String hora = dataString.split("-")[2].split(" ")[1];
        
        return dia + "/" + mes + "/" + ano + " " + 
                hora.substring(0,hora.length() - 2);
    }
    
    public static void excluirTodasAnalisesDoModelo(Modelo modelo)
    {
        //Iniciando sessao do hibernate
        Session sessao = HibernateUtility.getSession();
        
        //Iniciando transacao
        Transaction t = sessao.beginTransaction();
        
        //selecionando analise
        Query consulta = sessao.createQuery("from AnaliseGraficos a where a.modelo = :m");
        consulta.setParameter("m", modelo);
        List analises = consulta.list();
        
        //deletando cada analise do modelo
        for(int i=0; i<analises.size(); i++)
        {
            AnaliseGraficos an = (AnaliseGraficos) analises.get(i);
            excluir(an.getIdAnalise());
        }
        
        //Finalizando transacao e sessao
        t.commit();
        sessao.close();
    }
    
}
