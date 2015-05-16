/*
 * AplCadastrarCondicao.java
 *
 * Created on 17 de Agosto de 2006, 11:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cgt;

import cdp.*;
import cdp.CmpVariavel;
import cdp.Condicao;
import cdp.Percepcao;
import cdp.Posicionamento;
import cdp.ValorVariavel;
import java.util.*;
import org.hibernate.sql.QuerySelect;

import utilitario.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;

/**
 *
 * @author Diego Velasco
 */
public class AplCadastrarCondicao {
    
    /** Creates a new instance of AplCadastrarCondicao */
    public AplCadastrarCondicao() {
    }
    
    public static int incluirNovaCondicaoPer(String agente, String agentePrincipal, int distancia, String modo, Composicao comp) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente( Integer.parseInt(agente) );
        Agente agPrincipal = AplCadastrarAgente.obterAgente( Integer.parseInt(agentePrincipal) );
        int id = -1;
        
        if(validacao != -1){
            Percepcao cond = new Percepcao();
            cond.setTipo("Per");
            cond.setComposicao(comp);
            cond.setAgente(a);
            cond.setIdAgentePrincipal(agPrincipal.getIdAgente());
            cond.setDistancia(Integer.valueOf(String.valueOf(distancia)));
            cond.setModo(modo);
            sessao.save(cond);
            id = cond.getIdCondicao();
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return id;
    }
    
    public static int incluirNovaCondicaoPos(String expressao, String agente, String agPrinc, int numAgentes, Composicao comp) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente( Integer.parseInt(agente) );
        
        if(validacao != -1){            
            Posicionamento cond = new Posicionamento();
            cond.setTipo("Pos");            
            cond.setComposicao(comp);            
            if (numAgentes == 2)
                cond.setIdAgentePrincipal(Integer.parseInt(agPrinc));  
            else
                cond.setIdAgentePrincipal(-1);            
            cond.setAgente(a);            
            cond.setExpressao(expressao);
            sessao.save(cond);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;

    }
    
    public static int incluirNovaCondicaoCorAg(String cor, String agente, Composicao comp) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente( Integer.parseInt(agente) );
        
        if(validacao != -1){
            CorAgente cond = new CorAgente();
            cond.setTipo("CorAg");
            cond.setComposicao(comp);
            cond.setAgente(a);
            cond.setCor(cor);
            sessao.save(cond);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;

    }
    
    public static int incluirNovaCondicaoCmpVar(String expressao, String var1, String var2, Modelo m, Composicao comp) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Variavel v1 = AplCadastrarVariavel.obterVariavel( Integer.parseInt(var1) );
        Variavel v2 = AplCadastrarVariavel.obterVariavel( Integer.parseInt(var2) );
        
        if(validacao != -1){
            CmpVariavel cond = new CmpVariavel();
            cond.setTipo("CmpVar");
            cond.setComposicao(comp);
            cond.setIdVar1(v1.getIdVariavel());
            cond.setIdVar2(v2.getIdVariavel());
            cond.setExpressao(expressao);
            sessao.save(cond);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
        
    }
    
    public static int incluirNovaCondicaoValVar(String expr, String var, Modelo m, Composicao comp) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Variavel v1 = AplCadastrarVariavel.obterVariavel( Integer.parseInt(var) );
        
        if(validacao != -1){
            ValorVariavel cond = new ValorVariavel();
            cond.setTipo("ValVar");
            cond.setComposicao(comp);
            cond.setVar(v1);
            cond.setExpr(expr);
            sessao.save(cond);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
        
    }
    
    public static int incluirNovaCondicaoTamanho(int idAgente, String operador, int valor, Composicao comp)
    {
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        Agente ag =  AplCadastrarAgente.obterAgente(idAgente);
        if(validacao != -1){
            Tamanho cond = new Tamanho();
            cond.setTipo("Tam");
            cond.setComposicao(comp);
            cond.setAgente(ag);
            cond.setOperador(operador);
            cond.setValor(valor);
            sessao.save(cond);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); 
        return validacao;
    }
    
    public static int alterarDadosCondicaoPer(Percepcao cond, int idAgente, int idAgentePrincipal, int distancia, String modo, Modelo m, Composicao comp) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente(idAgente);
        Agente ap = AplCadastrarAgente.obterAgente(idAgentePrincipal);
        int id = -1;
        Percepcao aux = obterPercepcao(cond.getIdCondicao());
        if(!aux.getAgente().getIdAgente().equals(idAgente)) {
            List PA = obterVariaveisPercepcaoAvancado(cond.getIdCondicao());
            for (int k = 0; k < PA.size(); k++) {
                PercepcaoAvancado pAv = (PercepcaoAvancado) PA.get(k);
                sessao.delete(pAv);
                
            }
            List PCFT = obterVariaveisPercepcaoCFT(cond.getIdCondicao());
            for (int k = 0; k < PCFT.size(); k++) {
                PercepcaoCFT cft = (PercepcaoCFT) PCFT.get(k);
                sessao.delete(cft);
                
            }
        }
            
        if(validacao != -1){
            cond.setTipo("Per");
            cond.setComposicao(comp);
            cond.setAgente(a);
            cond.setIdAgentePrincipal(ap.getIdAgente());
            cond.setDistancia(Integer.valueOf(String.valueOf(distancia)));
            cond.setModo(modo);
            sessao.update(cond);
            id = cond.getIdCondicao();
        }
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return id;
        
    }
    
    public static int alterarDadosCondicaoPos(Posicionamento cond, String expressao, int idAgente, int idAgPrinc, int numAgentes, Modelo m, Composicao comp) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente (idAgente);
        
       if(validacao != -1){
            cond.setTipo("Pos");
            cond.setComposicao(comp);
            if(numAgentes == 2)
                cond.setIdAgentePrincipal(idAgPrinc);
            else
                cond.setIdAgentePrincipal(-1);
            cond.setAgente(a);
            cond.setExpressao(expressao);
            sessao.update(cond);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
        
    }
    
    public static int alterarDadosCondicaoCorAg(CorAgente cond, String cor, int idAgente, Composicao comp) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        Agente a = AplCadastrarAgente.obterAgente (idAgente);
        
        if(validacao != -1){
            cond.setTipo("CorAg");
            cond.setComposicao(comp);
            cond.setAgente(a);
            cond.setCor(cor);
            sessao.update(cond);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;

    }
    
    public static int alterarDadosCondicaoCmpVar(CmpVariavel cond, String expressao, String var1, String var2, Modelo m, Composicao comp) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Variavel v1 = AplCadastrarVariavel.obterVariavel( Integer.parseInt(var1) );
        Variavel v2 = AplCadastrarVariavel.obterVariavel( Integer.parseInt(var2) );
        
        if(validacao != -1){
            cond.setTipo("CmpVar");
            cond.setComposicao(comp);
            cond.setIdVar1(v1.getIdVariavel());
            cond.setIdVar2(v2.getIdVariavel());
            cond.setExpressao(expressao);
            sessao.update(cond);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
        
    }
    
    public static int alterarDadosCondicaoValVar(ValorVariavel cond, String expr, String var, Modelo m, Composicao comp) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
                
        Variavel v1 = AplCadastrarVariavel.obterVariavel( Integer.parseInt(var) );
        
        if(validacao != -1){
            cond.setTipo("ValVar");
            cond.setComposicao(comp);
            cond.setVar(v1);
            cond.setExpr(expr);
            sessao.update(cond);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
        
    }    
    
    public static List condicoesDaComposicao(int idComposicao){

        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("BuscarCondicoesDaComposicao");
        select.setInteger("idComposicao",idComposicao);
        List conds = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return conds;
    }
    
    public static Condicao obterCondicao(int idCondicao){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("BuscarCondicaoPeloId");
        select.setInteger("idCondicao",idCondicao);
        
        Condicao cond = new Condicao();
        cond = (Condicao)select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return cond;
    }

    public static int excluirCondicao(Condicao cond){
         
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao

        sessao.delete(cond);
        transacao.commit();
        sessao.close();
        return 0;
    }
    
    //Percepcoes avancadas
    public static int inserirPercpcaoAvancadoVar(int idCondicao, int idVar, String valor, String agente)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao

        PercepcaoAvancado percAv = new PercepcaoAvancado();

        percAv.setIdCondicao(idCondicao);
        percAv.setIdVariavel(idVar);
        percAv.setValor(valor);
        percAv.setAgente(agente);
        
        sessao.save(percAv);
        transacao.commit();
        sessao.close();
        
        return 1;
    }
    
    public static List obterVariaveisPercepcaoAvancado(int idCondicao)
    {        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("ListaPA");
        select.setInteger("idCondicao",idCondicao);
                
        List vars = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close();
        return vars;
    }
    
    public static PercepcaoAvancado obterPercepcaoAvancado(int idPercepcao)
    {        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("ObterPA");
        select.setInteger("idPercepcao",idPercepcao);
                
        PercepcaoAvancado percAv = ( PercepcaoAvancado) select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close();
        return percAv;
    }
    
    public static int removerPercepcaoAvancadoVar(int idPerc)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        PercepcaoAvancado percAv = obterPercepcaoAvancado(idPerc);
        sessao.delete(percAv);
        transacao.commit(); //Finalizando a transacao
        sessao.close();
        return 1;
    }
    
    public static int alterarPercepcaoAvancadoVar(PercepcaoAvancado percAv, String valor)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        percAv.setValor(valor);
        sessao.update(percAv);
        transacao.commit(); //Finalizando a transacao
        sessao.close();
        return 1;
    }
    
    //Percepcao CFT
    public static int inserirPercpcaoCFT(int idCondicao, String var, int valor, String operador)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao

        PercepcaoCFT percCFT = new PercepcaoCFT();

        percCFT.setIdCondicao(idCondicao);
        percCFT.setVar(var);
        percCFT.setValor(Integer.toString(valor));
        percCFT.setOperador(operador);
        
        sessao.save(percCFT);
        transacao.commit();
        sessao.close();
        
        return 1;
    }
    
    public static int inserirPercpcaoCFT(int idCondicao, String var, String valor)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao

        PercepcaoCFT percCFT = new PercepcaoCFT();

        percCFT.setIdCondicao(idCondicao);
        percCFT.setVar(var);
        percCFT.setValor(valor);
        percCFT.setOperador(null);
        
        sessao.save(percCFT);
        transacao.commit();
        sessao.close();
        
        return 1;
    }
    
    public static List obterVariaveisPercepcaoCFT(int idCondicao)
    {        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("ListaPCFT");
        select.setInteger("idCondicao",idCondicao);
                
        List vars = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close();
        return vars;
    }
    
    public static PercepcaoCFT obterPercepcaoCFT(int idPercepcao)
    {        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("ObterPCFT");
        select.setInteger("idPercepcao",idPercepcao);
                
        PercepcaoCFT percCFT = ( PercepcaoCFT) select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close();
        return percCFT;
    }
    
    public static int alterarPercepcaoCFT(PercepcaoCFT percCFT, String valor, String operador)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        percCFT.setValor(valor);
        percCFT.setOperador(operador);
        sessao.update(percCFT);
        transacao.commit(); //Finalizando a transacao
        sessao.close();
        return 1;
    }
    
    public static int removerPercepcaoCFT(int idPerc)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        PercepcaoCFT percCFT = obterPercepcaoCFT(idPerc);
        sessao.delete(percCFT);
        transacao.commit(); //Finalizando a transacao
        sessao.close();
        return 1;
    }
    
    private static Percepcao obterPercepcao(int idCondicao) {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("ObterPercepcao");
        select.setInteger("idCondicao",idCondicao);
                
        Percepcao perc = ( Percepcao) select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close();
        return perc;
    }
    
    public static int alterarDadosCondicaoTamanho(Tamanho tam, int idAgente, String operador, int valor) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente ag = AplCadastrarAgente.obterAgente (idAgente);
        
       if(validacao != -1){
            tam.setTipo("Tam");
            tam.setAgente(ag);
            tam.setOperador(operador);
            tam.setValor(valor);
            sessao.update(tam);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
        
    }
    
    
}