/*
 * ConsultaAnalisesGraficas.java
 *
 * Criado em 28 de Novembro de 2006, 19:58
 *
 */

package analiseGraficos;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import utilitario.*;
import cdp.*;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class ConsultaAnalisesGraficas {
    
    Modelo modelo;
    Usuario usuario;
    
    //sessao do hibernate
    Session sessao;
    
    public ConsultaAnalisesGraficas(int idModelo, int idUsuario) 
    {
        //Inicia sessao do hibernate
        sessao = HibernateUtility.getSession();
   
        //Inicializa transacao
        Transaction t = sessao.beginTransaction();
       
        //Consulta modelo pelo id
        Query consulta = sessao.createQuery("from Modelo m where m.idModelo= :id");
        consulta.setInteger("id", idModelo);
        List lista = consulta.list();
        modelo = (Modelo) lista.get(0);
        
        //Consulta usuario pelo id
        consulta = sessao.createQuery("from Usuario m where m.idUsuario= :id");
        consulta.setInteger("id", idUsuario);
        lista = consulta.list();
        usuario = (Usuario) lista.get(0);
        
        //Termina transacao
        t.commit();
    }
    
    public String getNomeModelo ()
    {
        return modelo.getNome();
    }
    
    public Usuario getUsuario ()
    {
        return usuario;
    }
    
    public List getAnalisesModelo ()
    {
        List analises = null;
        
        //Inicializa transacao
        Transaction t = sessao.beginTransaction();
        
        //Consulta usuario pelo id
        Query consulta = sessao.createQuery("from AnaliseGraficos m where m.modelo = :modelo");
        consulta.setParameter("modelo", modelo);
        analises = consulta.list();
        
        //Termina transacao
        t.commit();
        
        return analises;
    }
    
    public List getAnalisesModeloUsuario ()
    {
        List analises = null;
        
        //Inicializa transacao
        Transaction t = sessao.beginTransaction();
        
        //Consulta usuario pelo id
        Query consulta = sessao.createQuery("from AnaliseGraficos m where m.modelo = :modelo and m.usuario = :usuario");
        consulta.setParameter("modelo", modelo);
        consulta.setParameter("usuario", usuario);
        analises = consulta.list();
        
        //Termina transacao
        t.commit();
        
        return analises;
    }
    
    //Destrutor da classe
    public void finaliza( )
    {
        //Termina sessao do hibernate
        sessao.close();
    }
    
}
