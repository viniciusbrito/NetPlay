/*
 * AplCadastrarUsuario.java
 *
 * Created on 27 de Abril de 2006, 14:47
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cgt;

import cdp.Usuario;
import java.util.*;

import utilitario.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;

/**
 *
 * @author Alexandre Andrade
 */
public class AplCadastrarUsuario {
    
    /** Creates a new instance of AplCadastrarUsuario */
    public AplCadastrarUsuario() {
    }
    
    public static int incluirNovoUsuario(String nome,String sobrenome,String email, String login, String senha, String instituicao, String areaInteresse, String dtNascimento, String sexo){
        
        if(loginDisponivel(login))
        {
            Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
            Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setSobrenome(sobrenome);
            usuario.setEmail(email);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            usuario.setInstituicao(instituicao);
            usuario.setAreaInteresse(areaInteresse);
            usuario.setDtNascimento(dtNascimento);
            usuario.setSexo(sexo);
        
            sessao.save(usuario); // persistindo o objeto
            transacao.commit(); //Finalizando a transacao
            sessao.close(); //Fechando a sessao

            return 1;
        }
        
        return -1;
    }

    public static int alterarDadosUsuario(Usuario usuario,String nome,String sobrenome,String email, String login, String senha, String instituicao, String areaInteresse, String dtNascimento, String sexo){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        //se o usuario alterou seu login verificar se seu novo login ja existe
        if(usuario.getLogin().compareTo(login)!=0)
        {
            Query select = sessao.getNamedQuery("buscarPerfilPeloLogin");
            select.setString("login", login);
        
            List objetos = select.list();
            if(objetos.size()>0) // Verificando se o nome de usu�rio ja existe
            {
                transacao.commit(); //Finalizando a transacao
                sessao.close(); //Fechando a sessao
                return -1;
            }
        }
          
         usuario.setNome(nome);
         usuario.setSobrenome(sobrenome);
         usuario.setEmail(email);
         usuario.setLogin(login);
         usuario.setSenha(senha);
         usuario.setInstituicao(instituicao);
         usuario.setAreaInteresse(areaInteresse);
         usuario.setDtNascimento(dtNascimento);
         usuario.setSexo(sexo);
               
         sessao.update(usuario); //persistindo o usuario
         transacao.commit(); //Finalizando a transacao
         sessao.close(); //Fechando a sessao
         return 1;
    }
    

    public static void excluirUsuario(Usuario usuario){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        sessao.delete(usuario);
        transacao.commit();
        sessao.close();
    }
    
    public static boolean loginDisponivel(String login) {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("buscarPerfilPeloLogin");
        select.setString("login", login);
        
        List objetos = select.list();
        if(objetos.size()>0) // Verificando se o nome de usu�rio ja existe
        {
            transacao.commit(); //Finalizando a transacao
            sessao.close(); //Fechando a sessao
            return false;
        }
        return true;
    }
}

