/*
 * AplControleUsuario.java
 *
 * Created on 28 de Abril de 2006, 18:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cgt;

import cdp.Usuario;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Set;

import utilitario.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;

/**
 *
 * @author Alexandre Andrade
 */
public class AplControleUsuario {
    
    /** Creates a new instance of AplControleUsuario */
    public AplControleUsuario() {
    }
    
    public static Usuario efetuarLogin(String login,String senha){
       
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
                
        Query select = sessao.getNamedQuery("buscarPerfilPeloLogin");
        select.setString("login", login);
        
        Usuario usuario = new Usuario();
        usuario=(Usuario)select.uniqueResult();        
        
        if(usuario!=null)
        {
            if(senha.compareTo(usuario.getSenha()) == 0)
            {
                transacao.commit(); //Finalizando a transacao
                sessao.close(); //Fechando a sessao
                return usuario;
            }
        }
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return null;
    }
    
    public static Usuario efetuarLogin(String login)
    {
       
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
           
        Query select = sessao.getNamedQuery("buscarPerfilPeloLogin");
        select.setString("login", login);
        
        Usuario usuario = new Usuario();
        usuario=(Usuario)select.uniqueResult();        
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return usuario;            
    }
}
