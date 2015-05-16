/*
 * AplCadastrarAcao.java
 *
 * Created on 31 de Agosto de 2006, 13:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cgt;

import cdp.*;
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
public class AplCadastrarAcao {
    
    /** Creates a new instance of AplCadastrarAcao */
    public AplCadastrarAcao() {
    }

    public static int incluirAcaoCriar(String agente, String posicao, Composicao c) {
        
        int validacao = 1;
        int idAcao = 0;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente( Integer.parseInt(agente) );
        
        GerarNovoObjeto act = new GerarNovoObjeto();
        act.setTipo("Criar");
        act.setComposicao(c);
        act.setAgente(a);
        act.setCor(a.getCor());        
        act.setPosicao(posicao);
        
        if(a.getTipo().equals("Movel"))
        {
            String forma = AgenteMovel.buscarAgente(a.getIdAgente()).getForma();
            act.setForma(forma);
            int tam = AgenteMovel.buscarAgente(a.getIdAgente()).getTamanho();
            act.setTamanho(tam);
        }
        else
        {
            act.setForma(null);
        }
        
        sessao.save(act);
        idAcao = act.getIdAcao();
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        if(a.getTipo().equals("Movel"))
        {
            List variaveis = AplCadastrarVariavel.variaveisDoAgente( Integer.parseInt(agente) );
            for (Object variavei : variaveis) {
                Variavel var = (Variavel) variavei;
                if (var.getTipo().equals("Valor")) {
                    VariavelValor varValor = (VariavelValor) variavei;
                    incluirAcaoCriarVar(idAcao, varValor.getIdVariavel(), varValor.getValor());
                }
                if (var.getTipo().equals("Logica")) {
                    VariavelLogica varLog = (VariavelLogica) variavei;
                    incluirAcaoCriarVar(idAcao, varLog.getIdVariavel(), varLog.getValorLogico());
                }
            }
        }
        return idAcao;
        
    }
    public static int incluirAcaoCriarVar(int idAcao, int idVar, String valor)
    {
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao

        GerarNovoObjetoVariaveis vr = new GerarNovoObjetoVariaveis();
        vr.setIdAcao(idAcao);
        vr.setIdVariavel(idVar);
        vr.setValor(valor);
        sessao.save(vr);
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return 1;
    }    
    public static int incluirAcaoCriarVar(int idAcao, int idVar, Boolean valor)
    {
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao

        GerarNovoObjetoVariaveis vr = new GerarNovoObjetoVariaveis();
        vr.setIdAcao(idAcao);
        vr.setIdVariavel(idVar);
        vr.setValor(valor.toString());
        sessao.save(vr);
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return 1;
    }
    public static int alterarAcaoCriarVar(int idAcao, int idVar, String valor)
    {
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao

        GerarNovoObjetoVariaveis vr = AplCadastrarAcao.obterCriarVar(idAcao, idVar);

        vr.setValor(valor);
        sessao.update(vr);
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        return 1;
    }
    
    public static GerarNovoObjetoVariaveis obterCriarVar(int idAcao, int idVar)
    {
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarGerarNovo");
        select.setInteger("idAcao",idAcao);
        select.setInteger("idVar",idVar);
        
        GerarNovoObjetoVariaveis novo = new GerarNovoObjetoVariaveis();
        novo = (GerarNovoObjetoVariaveis)select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return novo;           
    }
    
    public static String buscarVarCriar(int idAcao, int idVar)
    {
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarGerarNovo");
        select.setInteger("idAcao",idAcao);
        select.setInteger("idVar",idVar);
        
        GerarNovoObjetoVariaveis novo = new GerarNovoObjetoVariaveis();
        novo = (GerarNovoObjetoVariaveis)select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return novo.getValor();
    }
        
    public static int incluirAcaoDestruir(String agente, Composicao c) {    
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente( Integer.parseInt(agente) );
                
        if(validacao != -1){
            Destruir act = new Destruir();
            act.setTipo("Destruir");
            act.setComposicao(c);
            act.setAgente(a);
            sessao.save(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int incluirAcaoOcultar(String agente, Composicao c) {    
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente( Integer.parseInt(agente) );
                
        if(validacao != -1){
            OcultarAgente act = new OcultarAgente();
            act.setTipo("Ocultar");
            act.setComposicao(c);
            act.setAgente(a);
            sessao.save(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int incluirAcaoMostrar(String agente, Composicao c) {    
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente( Integer.parseInt(agente) );
                
        if(validacao != -1){
            MostrarAgente act = new MostrarAgente();
            act.setTipo("Mostrar");
            act.setComposicao(c);
            act.setAgente(a);
            sessao.save(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int incluirAcaoAltCor(String agente, String novaCor, Composicao c) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente( Integer.parseInt(agente) );
                
        if(validacao != -1){
            AlterarCor act = new AlterarCor();
            act.setTipo("AltCor");
            act.setComposicao(c);
            act.setAgente(a);
            act.setNovaCor(novaCor);
            sessao.save(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int incluirAcaoAltFor(String agente, String novaFor, Composicao c) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        AgenteMovel a = (AgenteMovel) AplCadastrarAgente.obterAgente( Integer.parseInt(agente) );
                
        if(validacao != -1){
            AlterarForma act = new AlterarForma();
            act.setTipo("AltFor");
            act.setComposicao(c);
            act.setAgenteMovel(a);
            act.setNovaForma(novaFor);
            sessao.save(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int incluirAcaoAltMov(String agente, Composicao c ) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        AgenteMovel a = (AgenteMovel)AplCadastrarAgente.obterAgente( Integer.parseInt(agente) );
                
        if(validacao != -1){
            AlterarMovimento act = new AlterarMovimento();
            act.setTipo("AltMov");
            act.setComposicao(c);
            act.setAgenteMovel(a);
            sessao.save(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int incluirAcaoPararMov(String agente, Composicao c ) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        AgenteMovel a = (AgenteMovel)AplCadastrarAgente.obterAgente( Integer.parseInt(agente) );
                
        if(validacao != -1){
            PararMovimento act = new PararMovimento();
            act.setTipo("PararMov");            
            act.setComposicao(c);
            act.setAgenteMovel(a);
            sessao.save(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int incluirAcaoAltPos(String agente, Integer novoX, Integer novoY, Composicao c) {

        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        AgenteMovel a = (AgenteMovel) AplCadastrarAgente.obterAgente( Integer.parseInt(agente) );
                
        if(validacao != -1){
            AlterarPosicao act = new AlterarPosicao();
            act.setTipo("AltPos");
            act.setComposicao(c);
            act.setAgenteMovel(a);
            act.setNovoX(novoX);
            act.setNovoY(novoY);
            sessao.save(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int incluirAcaoIncValVar(Variavel var, float inc, Composicao c){
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        if(validacao != -1){
            IncrementarVariavel act = new IncrementarVariavel();
            act.setTipo("IncValVar");
            act.setComposicao(c);
            act.setVariavel(var);
            act.setIncremento(inc);
            
            sessao.save(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int incluirAcaoRecValVar(Variavel var, String exp, Composicao c, String tipo, String idAgente) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        if(validacao != -1){
            RecuperarValorVariavel act = new RecuperarValorVariavel();
            act.setTipo("RecValVar");
            act.setComposicao(c);
            act.setVariavel(var);
            act.setExp(exp);
            
            if(tipo.equals("2"))
            {
                Agente agente = AplCadastrarAgente.obterAgente(Integer.parseInt(idAgente));
                act.setAgente(agente);
            }
            
            sessao.save(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int incluirAcaoAlterarTamanho(String agente, int tamanho, String tipoTam, Composicao c ) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        AgenteMovel a = (AgenteMovel)AplCadastrarAgente.obterAgente( Integer.parseInt(agente) );
                
        if(validacao != -1){
            AlterarTamanho act = new AlterarTamanho();
            act.setTipo("AltTam");            
            act.setComposicao(c);
            act.setAgenteMovel(a);
            act.setTamanho(tamanho);
            act.setTipoTamanho(tipoTam);
            sessao.save(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int alterarDadosAcaoCriar(GerarNovoObjeto act, String idAgente, String posicao, Composicao c){
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
       // Agente a = AplCadastrarAgente.obterAgente( Integer.parseInt(idAgente) );
        Agente atualAgente = AplCadastrarAgente.obterAgente(act.getAgente().getIdAgente());
        Agente novoAgente = AplCadastrarAgente.obterAgente(Integer.parseInt(idAgente));
        if(novoAgente.getIdAgente().equals(atualAgente.getIdAgente()))
        {
            Acao acao = obterAcao(act.getIdAcao());
            if(!act.getPosicao().equals(posicao))
            {
                act.setPosicao(posicao);
                sessao.update(act);
                transacao.commit();
                sessao.close();
            }
            
            List variaveis = AplCadastrarVariavel.variaveisDoAgente(atualAgente.getIdAgente());
            for(Object variavei :  variaveis)
            {
                Variavel var = (Variavel) variavei;
                GerarNovoObjetoVariaveis aux = AplCadastrarAcao.obterCriarVar(acao.getIdAcao(), var.getIdVariavel());
                if(aux == null)
                {
                    if(var.getTipo().equals("Valor"))
                    {
                        VariavelValor varValor = (VariavelValor) var;
                        AplCadastrarAcao.incluirAcaoCriarVar(acao.getIdAcao(), var.getIdVariavel(), varValor.getValor());
                    }
                    
                    if(var.getTipo().equals("Logica"))
                    {
                        VariavelLogica varValor = (VariavelLogica) var;
                        AplCadastrarAcao.incluirAcaoCriarVar(acao.getIdAcao(), var.getIdVariavel(), varValor.getValorLogico());
                    }
                }
                
            }
            return acao.getIdAcao();
        }
        else
        {
            List variaveis = AplCadastrarVariavel.variaveisDoAgente(atualAgente.getIdAgente());
            //remover as variaveis do agente antigo
            if(atualAgente.getTipo().equals("Movel"))
            {
                for (Object variavei : variaveis) {
                    Variavel var = (Variavel) variavei;
                    excluirVarCriar(act.getIdAcao(), var.getIdVariavel());
                }   
            }
            
            //atualizar novo agente
            act.setTipo("Criar");
            act.setComposicao(c);
            act.setAgente(novoAgente);
            act.setCor(novoAgente.getCor());
            if(novoAgente.getTipo().equals("Movel")) {
                act.setForma(AgenteMovel.buscarAgente(novoAgente.getIdAgente()).getForma());
                act.setTamanho(AgenteMovel.buscarAgente(novoAgente.getIdAgente()).getTamanho());
            }
            else
                act.setForma(null);
            act.setPosicao(posicao);
            sessao.update(act);
            transacao.commit();
            sessao.close();
            
            int idAcao = act.getIdAcao();
            
            //insere novas variaveis de agente
            if(novoAgente.getTipo().equals("Movel"))
            {
                variaveis = AplCadastrarVariavel.variaveisDoAgente( novoAgente.getIdAgente() );
                for (Object variavei : variaveis) {
                    Variavel var = (Variavel) variavei;
                    if (var.getTipo().equals("Valor")) {
                        VariavelValor varValor = (VariavelValor) variavei;
                        incluirAcaoCriarVar(idAcao, varValor.getIdVariavel(), varValor.getValor());
                    }
                    if (var.getTipo().equals("Logica")) {
                        VariavelLogica varLog = (VariavelLogica) variavei;
                        incluirAcaoCriarVar(idAcao, varLog.getIdVariavel(), varLog.getValorLogico());
                    }
                }
            }
            return idAcao;
        }        
        
    }
    
    public static void excluirVarCriar(int idAcao, int idVar)
    {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        GerarNovoObjetoVariaveis a = obterCriarVar(idAcao, idVar);
        sessao.delete(a);
        transacao.commit();
        sessao.close();
    }
    
    public static int alterarCorAcaoCriar(GerarNovoObjeto act)
    {
        Session  sessao = HibernateUtility.getSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.update(act);
        transacao.commit();
        sessao.close();
        return 1;
    }
    
    public static int alterarDadosAcaoDestruir(Destruir act, String idAgente, Composicao c){
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente( Integer.parseInt(idAgente) );
                
        if(validacao != -1){
            act.setTipo("Destruir");
            act.setComposicao(c);
            act.setAgente(a);
            sessao.update(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int alterarDadosAcaoOcultar(OcultarAgente act, String idAgente, Composicao c){
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente( Integer.parseInt(idAgente) );
                
        if(validacao != -1){
            act.setTipo("Ocultar");
            act.setComposicao(c);
            act.setAgente(a);
            sessao.update(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int alterarDadosAcaoMostrar(MostrarAgente act, String idAgente, Composicao c){
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente( Integer.parseInt(idAgente) );
                
        if(validacao != -1){
            act.setTipo("Mostrar");
            act.setComposicao(c);
            act.setAgente(a);
            sessao.update(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int alterarDadosAcaoAltCor(AlterarCor act, String idAgente, String novaCor, Composicao c){
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Agente a = AplCadastrarAgente.obterAgente( Integer.parseInt(idAgente) );
                
        if(validacao != -1){
            act.setTipo("AltCor");
            act.setComposicao(c);
            act.setAgente(a);
            act.setNovaCor(novaCor);
            sessao.update(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int alterarDadosAcaoAltFor(AlterarForma act, String idAgente, String novaFor, Composicao c){
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        AgenteMovel a = (AgenteMovel) AplCadastrarAgente.obterAgente( Integer.parseInt(idAgente) );
                
        if(validacao != -1){
            act.setTipo("AltFor");
            act.setComposicao(c);
            act.setAgenteMovel(a);
            act.setNovaForma(novaFor);
            sessao.update(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int alterarDadosAcaoAltMov(AlterarMovimento act, String idAgente, Composicao c){
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        AgenteMovel a = (AgenteMovel)AplCadastrarAgente.obterAgente( Integer.parseInt(idAgente) );
                
        if(validacao != -1){
            act.setTipo("AltMov");
            act.setComposicao(c);
            act.setAgenteMovel(a);
            sessao.update(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int alterarDadosAcaoPararMov(PararMovimento act, String idAgente, Composicao c){
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        AgenteMovel a = (AgenteMovel)AplCadastrarAgente.obterAgente( Integer.parseInt(idAgente) );
                
        if(validacao != -1){
            act.setTipo("PararMov");
            act.setComposicao(c);
            act.setAgenteMovel(a);
            sessao.update(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int alterarDadosAcaoAltPos(AlterarPosicao act, String idAgente, Integer novoX, Integer novoY, Composicao c){
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        AgenteMovel a = (AgenteMovel) AplCadastrarAgente.obterAgente( Integer.parseInt(idAgente) );
                
        if(validacao != -1){
            act.setTipo("AltPos");
            act.setComposicao(c);
            act.setAgenteMovel(a);
            act.setNovoX(novoX);
            act.setNovoY(novoY);
            sessao.update(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }    
    
    public static int alterarDadosAcaoIncValVar(IncrementarVariavel act, Variavel var, float inc, Composicao c){
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        if(validacao != -1){
            act.setTipo("IncValVar");
            act.setComposicao(c);
            act.setVariavel(var);
            act.setIncremento(inc);
            
            sessao.update(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int alterarDadosAcaoRecValVar(RecuperarValorVariavel act, Variavel var, String exp, Composicao c, String tipo, String idAgente) {
        
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        if(validacao != -1){
            act.setTipo("RecValVar");
            act.setComposicao(c);
            act.setVariavel(var);
            act.setExp(exp);
            
            if(tipo.equals("2"))
            {
                Agente agente = AplCadastrarAgente.obterAgente(Integer.parseInt(idAgente));
                act.setAgente(agente);
            }
            else
                act.setAgente(null);
            
            sessao.update(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static int alterarDadosAcaoAlterarTamanho(AlterarTamanho act, int idAgente, int tamanho, String tipoTam, Composicao c){
        int validacao = 1;
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        AgenteMovel a = (AgenteMovel)AplCadastrarAgente.obterAgente(idAgente);
                
        if(validacao != -1){
            act.setTipo("AltTam");
            act.setComposicao(c);
            act.setAgenteMovel(a);
            act.setTamanho(tamanho);
            act.setTipoTamanho(tipoTam);
            sessao.update(act);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
    }
    
    public static List acoesDaComposicao(int idComposicao) {
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("BuscarAcoesDaComposicao");
        select.setInteger("idComposicao",idComposicao);
        List acoes = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return acoes;
    }
    
    public static Acao obterAcao(int idAcao){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
            
        Query select = sessao.getNamedQuery("BuscarAcaoPeloId");
        select.setInteger("idAcao",idAcao);
        
        Acao acao = new Acao();
        acao = (Acao)select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
            
        return acao;           
    }
    
    public static int excluirAcao(Acao a){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao

        sessao.delete(a);
        transacao.commit();
        sessao.close();
        return 0;
        
    }
}
