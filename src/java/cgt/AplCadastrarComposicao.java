/*
 * AplCadastrarComposicao.java
 *
 * Created on 18 de Agosto de 2006, 16:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cgt;

import cdp.*;
import cdp.Acao;
import cdp.Condicao;
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
public class AplCadastrarComposicao {
    
    /** Creates a new instance of AplCadastrarComposicao */
    public AplCadastrarComposicao() {
    }
    
    public static int incluirNovaComposicao(String nome, String oplog,  String tipo, Modelo m) {
        
        int validacao = 1;
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        validacao = validaNomeComposicao(nome,m,sessao);
        
        if(validacao != -1){
            Composicao c = new Composicao();
            c.setNome(nome);
            c.setOperador(oplog);
            c.setModelo(m);
            c.setTipo(tipo);
            sessao.save(c);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
        
    }
    
    public static int alterarDadosComposicao(Composicao c, String nome, String oplog, String tipo, Modelo m) {
        
        int validacao = 1;
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        if(c.getNome().compareTo(nome)!=0)
            validacao = validaNomeComposicao(nome,m,sessao);
        
        if(validacao != -1){
            c.setNome(nome);
            c.setOperador(oplog);
            c.setModelo(m);
            sessao.update(c);
        }
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return validacao;
        
    }
    
    public static List composicoesDoModelo(int idModelo){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("BuscarComposicoesDoModelo");
        select.setInteger("idModelo",idModelo);
        List comps = select.list();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return comps;
    }
    
    public static Composicao obterComposicao(int idComposicao){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("BuscarComposicaoPeloId");
        select.setInteger("idComposicao",idComposicao);
        
        Composicao comp = new Composicao();
        comp = (Composicao)select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return comp;
    }
    
    public static Composicao obterComposicao(int idModelo, String nome){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("BuscarComposicaoDoModelo");
        select.setString("nome",nome);
        select.setInteger("idModelo",idModelo);
        
        Composicao comp = new Composicao();
        comp = (Composicao)select.uniqueResult();
        
        transacao.commit(); //Finalizando a transacao
        sessao.close(); //Fechando a sessao
        
        return comp;
    }
    
    public static int validaNomeComposicao(String nome,Modelo modelo,Session sessao){
        
        Query select = sessao.getNamedQuery("buscarComposicaoPeloNome_Modelo");
        select.setString("nome",nome);
        select.setInteger("idModelo",modelo.getIdModelo().intValue());
         
        List objetos = select.list();
        if(objetos.size()>0)
            // Verificando se jah existe uma condicao com o mesmo nome num determinado modelo
            return -1;
        
        return 1;
    }
    
    public static int excluirComposicao(Composicao comp){
        
        Session sessao = HibernateUtility.getSession(); //Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        //Excluindo Condicoes
        Query select1 = sessao.getNamedQuery("BuscarCondicoesDaComposicao");
        select1.setInteger("idComposicao",comp.getIdComposicao().intValue());
        List conds = select1.list();
        for(int i=0; i< conds.size(); i++){
            Condicao c = (Condicao)conds.get(i);
            sessao.delete(c);
        }
        
        //Excluindo Acoes
        Query select2 = sessao.getNamedQuery("BuscarAcoesDaComposicao");
        select2.setInteger("idComposicao",comp.getIdComposicao().intValue());
        List acoes = select2.list();
        for(int i=0; i< acoes.size(); i++){
            Acao a = (Acao)acoes.get(i);
            sessao.delete(a);
        }
        
        sessao.delete(comp);
        transacao.commit();
        sessao.close();
        return 0;
    }
    
    public static void alterarModelo (int idModeloPai, Modelo modelo)
    {
        Session sessao = HibernateUtility.getSession();//Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        //Pegando composicoes do modelo pai
        List composicoes = AplCadastrarComposicao.composicoesDoModelo(idModeloPai);
        
        for(int i=0; i<composicoes.size(); i++) {
            Composicao c = (Composicao)composicoes.get(i);
            int retorno = AplCadastrarComposicao.incluirNovaComposicao(c.getNome(), c.getOperador(), c.getTipo(), modelo); //criando nova composicao no modelo filho
            Composicao composicaoNova = AplCadastrarComposicao.obterComposicao(modelo.getIdModelo(), c.getNome()); //pegando essa composicao criada
            
            //Condicoes
            List conds = AplCadastrarCondicao.condicoesDaComposicao(c.getIdComposicao().intValue());
            for(int j=0; j<conds.size(); j++) {                
                Condicao co = (Condicao)conds.get(j);
                
                //GAMBIARRA - procurando condicao no BD
                Query consulta = sessao.getNamedQuery("BuscarCondicaoPeloId");                
                consulta.setParameter("idCondicao", co.getIdCondicao().intValue());                
                Condicao condicao = (Condicao) consulta.list().get(0);
                
                //Percepcao
                if(condicao.getTipo().compareTo("Per") == 0) {
                    Percepcao p = (Percepcao) condicao;
                    Agente ag = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), p.getAgente().getNome());
                    Agente agPrincipal = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), p.getAgentePrincipal().getNome());
                    retorno = AplCadastrarCondicao.incluirNovaCondicaoPer(ag.getIdAgente().toString(),agPrincipal.getIdAgente().toString(),p.getDistancia().intValue(),p.getModo(),composicaoNova);
                    if(p.getModo().compareTo("avancado") == 0) {
                        //Variavels
                        List aux = AplCadastrarCondicao.obterVariaveisPercepcaoAvancado(p.getIdCondicao());
                        for(int k = 0; k <  aux.size(); k ++) {
                            PercepcaoAvancado perc = (PercepcaoAvancado) aux.get(k);
                            AplCadastrarCondicao.inserirPercpcaoAvancadoVar(retorno, perc.getIdVariavel(), perc.getValor(), perc.getAgente());
                        }
                        //CFT
                        aux = AplCadastrarCondicao.obterVariaveisPercepcaoCFT(p.getIdCondicao());
                        for(int k = 0; k <  aux.size(); k ++) {
                            PercepcaoCFT perc = (PercepcaoCFT) aux.get(k);
                            if(perc.getVar().equals("tamanho"))
                                AplCadastrarCondicao.inserirPercpcaoCFT(retorno, perc.getVar(), Integer.parseInt(perc.getValor()), perc.getOperador());
                            else
                                AplCadastrarCondicao.inserirPercpcaoCFT(retorno, perc.getVar(), perc.getValor());
                        }
                        
                    }
                    
                //Posicionamento
                } else if(condicao.getTipo().compareTo("Pos") == 0) {
                    Posicionamento p = (Posicionamento) condicao;
                    Agente ag = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), p.getAgente().getNome());
                    Agente agPrincipal = null;
                    int numAgentes = 1;
                    if(p.getAgentePrincipal() != null)
                    {
                        agPrincipal = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), p.getAgentePrincipal().getNome());
                        numAgentes = 2;
                        retorno = AplCadastrarCondicao.incluirNovaCondicaoPos(p.getExpressao(),ag.getIdAgente().toString(),agPrincipal.getIdAgente().toString(),numAgentes,composicaoNova);
                    }
                    else
                        retorno = AplCadastrarCondicao.incluirNovaCondicaoPos(p.getExpressao(),ag.getIdAgente().toString(),"",numAgentes,composicaoNova);
                //Cor de agente
                } else if(condicao.getTipo().compareTo("CorAg") == 0) {
                    CorAgente p = (CorAgente) condicao;
                    Agente ag = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), p.getAgente().getNome());
                    retorno = AplCadastrarCondicao.incluirNovaCondicaoCorAg(p.getCor(),ag.getIdAgente().toString(),composicaoNova);
                //comparacao de variaveis
                } else if(condicao.getTipo().compareTo("CmpVar") == 0) {
                    CmpVariavel p = (CmpVariavel) condicao;
                    Variavel var1 = AplCadastrarVariavel.obterVariavel(modelo.getIdModelo().intValue(), p.getVar1().getNome());
                    Variavel var2 = AplCadastrarVariavel.obterVariavel(modelo.getIdModelo().intValue(), p.getVar2().getNome());
                    retorno = AplCadastrarCondicao.incluirNovaCondicaoCmpVar(p.getExpressao(),var1.getIdVariavel().toString(),var2.getIdVariavel().toString(),modelo,composicaoNova);
                //valor de variavel
                } else if(condicao.getTipo().compareTo("ValVar") == 0) {
                    ValorVariavel p = (ValorVariavel) condicao;
                    Variavel var = AplCadastrarVariavel.obterVariavel(modelo.getIdModelo().intValue(), p.getVar().getNome());
                    retorno = AplCadastrarCondicao.incluirNovaCondicaoValVar(p.getExpr(),var.getIdVariavel().toString(),modelo,composicaoNova);
                }//tamanho
                else if(condicao.getTipo().compareTo("Tam") == 0) {
                    Tamanho p = (Tamanho) condicao;
                    retorno = AplCadastrarCondicao.incluirNovaCondicaoTamanho(p.getAgente().getIdAgente(), p.getOperador(), p.getValor(), composicaoNova);
                }
            }
            
            //acoes
            List acoes = AplCadastrarAcao.acoesDaComposicao(c.getIdComposicao().intValue());
            for(int j=0; j < acoes.size(); j++) {
                Acao a = (Acao)acoes.get(j);
                
                //GAMBIARRA - procurando condicao no BD
                Query consulta = sessao.getNamedQuery("BuscarAcaoPeloId");
                consulta.setParameter("idAcao", a.getIdAcao());
                Acao acao = (Acao) consulta.list().get(0);
                
                //Gerar Novo Objeto
                if(acao.getTipo().compareTo("Criar") == 0) {
                    GerarNovoObjeto x = (GerarNovoObjeto) acao;
                    Agente ag = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), x.getAgente().getNome());
                    retorno = AplCadastrarAcao.incluirAcaoCriar(ag.getIdAgente().toString(), x.getPosicao(), composicaoNova);
                    
                //Destruir agente
                } else if(acao.getTipo().compareTo("Destruir") == 0) {
                    Destruir x = (Destruir) acao;
                    Agente ag = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), x.getAgente().getNome());
                    retorno = AplCadastrarAcao.incluirAcaoDestruir(ag.getIdAgente().toString(),composicaoNova);
                //Alterar cor
                } else if(acao.getTipo().compareTo("AltCor") == 0) {
                    AlterarCor x = (AlterarCor) acao;
                    Agente ag = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), x.getAgente().getNome());
                    retorno = AplCadastrarAcao.incluirAcaoAltCor(ag.getIdAgente().toString(),x.getNovaCor(),composicaoNova);
                //Alterar forma
                } else if(acao.getTipo().compareTo("AltFor") == 0) {
                    AlterarForma x = (AlterarForma) acao;
                    Agente ag = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), x.getAgenteMovel().getNome());
                    retorno = AplCadastrarAcao.incluirAcaoAltFor(ag.getIdAgente().toString(),x.getNovaForma(),composicaoNova);
                //Alterar movimento
                } else if(acao.getTipo().compareTo("AltMov") == 0) {
                    AlterarMovimento x = (AlterarMovimento) acao;
                    Agente ag = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), x.getAgenteMovel().getNome());
                    retorno = AplCadastrarAcao.incluirAcaoAltMov(ag.getIdAgente().toString(),composicaoNova);
                //Alterar movimento
                } else if(acao.getTipo().compareTo("PararMov") == 0) {
                    PararMovimento x = (PararMovimento) acao;
                    Agente ag = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), x.getAgenteMovel().getNome());
                    retorno = AplCadastrarAcao.incluirAcaoPararMov(ag.getIdAgente().toString(),composicaoNova);
                //Alterar posicao
                } else if(acao.getTipo().compareTo("AltPos") == 0) {
                    AlterarPosicao x = (AlterarPosicao) acao;
                    Agente ag = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), x.getAgenteMovel().getNome());
                    retorno = AplCadastrarAcao.incluirAcaoAltPos(ag.getIdAgente().toString(),x.getNovoX(),x.getNovoY(),composicaoNova);
                //incrementar variavel
                } else if(acao.getTipo().compareTo("IncValVar") == 0) {
                    IncrementarVariavel x = (IncrementarVariavel) acao;
                    Variavel var = AplCadastrarVariavel.obterVariavel(modelo.getIdModelo().intValue(), x.getVariavel().getNome());
                    retorno = AplCadastrarAcao.incluirAcaoIncValVar(var,x.getIncremento(),composicaoNova);
                //recuperar valor de variavel
                } else if(acao.getTipo().compareTo("RecValVar") == 0) {
                    RecuperarValorVariavel x = (RecuperarValorVariavel) acao;
                    Variavel var = AplCadastrarVariavel.obterVariavel(modelo.getIdModelo().intValue(), x.getVariavel().getNome());
                    String idAgente = "";
                    String tipo = "1";
                    if (x.getAgente() != null)
                    {
                        idAgente = AplCadastrarAgente.obterAgente(modelo.getIdModelo(), 
                                x.getAgente().getNome()).getIdAgente().toString();
                        tipo = "2";
                    }
                    retorno = AplCadastrarAcao.incluirAcaoRecValVar(var,x.getExp(),composicaoNova, tipo, idAgente);
                //recuperar tamanho
                } else if(acao.getTipo().compareTo("AltTam") == 0) {
                    AlterarTamanho x = (AlterarTamanho) acao;
                    Agente ag = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), x.getAgenteMovel().getNome());
                    retorno = AplCadastrarAcao.incluirAcaoAlterarTamanho(ag.getIdAgente().toString(), x.getTamanho(), x.getTipoTamanho(), composicaoNova);
                //recuperar ocultar
                } else if(acao.getTipo().compareTo("Ocultar") == 0) {
                    OcultarAgente x = (OcultarAgente) acao;
                    Agente ag = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), x.getAgente().getNome());
                    retorno = AplCadastrarAcao.incluirAcaoOcultar(ag.getIdAgente().toString(), composicaoNova);
                //recuperar mostrar   
                }else if(acao.getTipo().compareTo("Mostrar") == 0) {
                    MostrarAgente x = (MostrarAgente) acao;
                    Agente ag = AplCadastrarAgente.obterAgente(modelo.getIdModelo().intValue(), x.getAgente().getNome());
                    retorno = AplCadastrarAcao.incluirAcaoMostrar(ag.getIdAgente().toString(), composicaoNova);
                }
                
            }                       
        }
        transacao.commit();
        sessao.close();
    }

    public static boolean agentePresenteComposicoes (Agente agente)
    {
        String nome = agente.getNome();
        
        Session sessao = HibernateUtility.getSession();//Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("BuscarComposicoesDoModelo");
        select.setParameter("idModelo", agente.getModelo().getIdModelo());
        List composicoes = select.list();
        
        //Composicoes do modelo
        for(int i=0; i<composicoes.size(); i++)
        {
            Composicao composicao = (Composicao) composicoes.get(i);
            
            //condicoes
            Iterator iterator = composicao.getCondicoes().iterator();
            while(iterator.hasNext())
            {
                Condicao condicao = (Condicao) iterator.next();
                
                //Percepcao
                if(condicao.getTipo().equals("Per"))
                {
                    Percepcao c = (Percepcao) condicao;
                    String nomeAg = c.getAgente().getNome();
                    String nomeAgPrincipal = c.getAgentePrincipal().getNome();                    
                    if (nome.equals(nomeAg) || nome.equals(nomeAgPrincipal))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
                
                //Posicionamento
                if(condicao.getTipo().equals("Pos"))
                {
                    Posicionamento c = (Posicionamento) condicao;
                    String nomeAg = c.getAgente().getNome();
                    String nomeAgPrinc = "";
                    if (c.getAgentePrincipal() != null)
                        nomeAgPrinc = c.getAgentePrincipal().getNome();
                    if (nome.equals(nomeAg) || nome.equals(nomeAgPrinc))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
                
                //Cor de agente
                if(condicao.getTipo().equals("CorAg"))
                {
                    CorAgente c = (CorAgente) condicao;
                    String nomeAg = c.getAgente().getNome();
                    if (nome.equals(nomeAg))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
            }
            
            //acoes
            iterator = composicao.getAcoes().iterator();
            while(iterator.hasNext())
            {
                Acao acao = (Acao) iterator.next();
                
                //criar
                if(acao.getTipo().equals("Criar"))
                {
                    GerarNovoObjeto c = (GerarNovoObjeto) acao;
                    String nomeAg = c.getAgente().getNome();
                    if (nome.equals(nomeAg))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
                
                //recuperar valor de variavel
                if(acao.getTipo().equals("RecValVar"))
                {
                    RecuperarValorVariavel a = (RecuperarValorVariavel) acao;
                    Agente ag = a.getAgente();
                    if(ag != null && nome.equals(ag.getNome()))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
                
                //destruir
                if(acao.getTipo().equals("Destruir"))
                {
                    Destruir c = (Destruir) acao;
                    String nomeAg = c.getAgente().getNome();
                    if (nome.equals(nomeAg))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
                
                //alterar cor
                if(acao.getTipo().equals("AltCor"))
                {
                    AlterarCor c = (AlterarCor) acao;
                    String nomeAg = c.getAgente().getNome();
                    if (nome.equals(nomeAg))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
                
                //alterar forma
                if(acao.getTipo().equals("AltFor"))
                {
                    AlterarForma c = (AlterarForma) acao;
                    String nomeAg = c.getAgenteMovel().getNome();
                    if (nome.equals(nomeAg))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
                
                //alterar posicao
                if(acao.getTipo().equals("AltPos"))
                {
                    AlterarPosicao c = (AlterarPosicao) acao;
                    String nomeAg = c.getAgenteMovel().getNome();
                    if (nome.equals(nomeAg))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
                
                //alterar movimento
                if(acao.getTipo().equals("AltMov"))
                {
                    AlterarMovimento c = (AlterarMovimento) acao;
                    String nomeAg = c.getAgenteMovel().getNome();
                    if (nome.equals(nomeAg))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
                
                //parar movimento
                if(acao.getTipo().equals("PararMov"))
                {
                    PararMovimento c = (PararMovimento) acao;
                    String nomeAg = c.getAgenteMovel().getNome();
                    if (nome.equals(nomeAg))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
            }
        }
        
        transacao.commit();
        sessao.close();
        
        return false;
    }
    
    public static boolean variavelPresenteComposicoes (Variavel variavel)
    {
        String nome = variavel.getNome();
        
        Session sessao = HibernateUtility.getSession();//Abrindo uma sessao
        Transaction transacao = sessao.beginTransaction(); //Iniciando uma transacao
        
        Query select = sessao.getNamedQuery("BuscarComposicoesDoModelo");
        select.setParameter("idModelo", variavel.getModelo().getIdModelo());
        List composicoes = select.list();
        
        //Composicoes do modelo
        for(int i=0; i<composicoes.size(); i++)
        {
            Composicao composicao = (Composicao) composicoes.get(i);
            
            //condicoes
            Iterator iterator = composicao.getCondicoes().iterator();
            while(iterator.hasNext())
            {
                Condicao condicao = (Condicao) iterator.next();
                
                //comparacao de variaveis
                if(condicao.getTipo().equals("CmpVar"))
                {
                    CmpVariavel c = (CmpVariavel) condicao;
                    String nomeVar1 = c.getVar1().getNome();
                    String nomeVar2 = c.getVar2().getNome();
                    if (nome.equals(nomeVar1) || nome.equals(nomeVar2))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
                
                //valor variavel
                if(condicao.getTipo().equals("ValVar"))
                {
                    ValorVariavel c = (ValorVariavel) condicao;
                    String nomeVar = c.getVar().getNome();
                    if (nome.equals(nomeVar))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
            }
            
            //acoes
            iterator = composicao.getAcoes().iterator();
            while(iterator.hasNext())
            {
                Acao acao = (Acao) iterator.next();
                
                //incrementar valor variavel
                if(acao.getTipo().equals("IncValVar"))
                {
                    IncrementarVariavel c = (IncrementarVariavel) acao;
                    String nomeVar = c.getVariavel().getNome();
                    if (nome.equals(nomeVar))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }
                
                //recuperar valor variavel
                if(acao.getTipo().equals("RecValVar"))
                {
                    RecuperarValorVariavel c = (RecuperarValorVariavel) acao;
                    String nomeVar = c.getVariavel().getNome();
                    if (nome.equals(nomeVar))
                    {
                        transacao.commit();
                        sessao.close();
                        return true;
                    }
                }                                
            }
        }
        
        transacao.commit();
        sessao.close();
        
        return false;
    }
        
}
