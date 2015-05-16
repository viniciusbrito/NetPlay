/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceNetLogo.codigo;

import cdp.*;
import java.util.*;
import org.hibernate.sql.QuerySelect;

import utilitario.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import interfaceNetLogo.Traducao;

/**
 *
 * @author vinicius
 */
public class CodigoBotao {
    
    Modelo modelo;
    
    String codigo;
        
    String outrasFuncoes;
    
    Agente agentePrincipal = null;
    
    //Sessao do hibernate
    Session sessao;
    
    public CodigoBotao(Modelo m) 
    {
        modelo = m;
        codigo = "";
        outrasFuncoes = "";
        criaCodigoComposicao();
        sessao.close();
    }
    
   
    public String getCodigo()
    {
        return codigo;
    }

    
    public String getOutrasFuncoes()
    {
        //Verificando se teve alguma acao altera movimento
        int idModelo = modelo.getIdModelo().intValue();
        Iterator i = getComposicoes(idModelo).iterator();
        while (i.hasNext())
        {
            Composicao composicao = (Composicao) i.next();
            Iterator j = composicao.getAcoes().iterator();
            while (j.hasNext())
            {
                Acao acao = (Acao) j.next();
                if (acao.getTipo().equals("AltMov"))
                {
                    CodigoAcao codAcao = new CodigoAcao (acao, null);
                    return codAcao.bounce();
                }
            }
        }
        
        return "";
    }
    
    //Destrutor da classe
    /*protected void finalize( )
    {
        //Termina sessao do hibernate
        sessao.close();
    }*/
    
    /***************************************************************
     * COMPOSICOES
     **************************************************************/
    
    private void criaCodigoComposicao()
    {
        int idModelo = modelo.getIdModelo().intValue();
        Iterator iterator = getComposicoes(idModelo).iterator();
        while (iterator.hasNext())
        {
            Composicao composicao = (Composicao) iterator.next();
            if(composicao.getTipo().equals("botao"))
            {
                codigo += ";Botao " + composicao.getNome() + "\n"
                        + "to COMANDO_" + composicao.getNome() + "\n";

                //determina agente principal da composicao, se tiver
                agentePrincipal = determinaAgentePrincipal (composicao);
                boolean existeAgPrinc = false;
                if (agentePrincipal != null)
                    existeAgPrinc = true;


                if (existeAgPrinc)
                {
                    codigo += "\task ";
                    if (agentePrincipal.getTipo().equals("Movel"))
                        codigo += "conjunto_" + 
                                agentePrincipal.getNome();
                    else
                    {
                        codigo += "patches with [pcolor = " +
                                Traducao.cor(agentePrincipal.getCor()) + 
                                " ]";
                    }

                    codigo += "\n\t[\n";
                }

                //Verificar se existe gerarnovoobjeto c/ patches
                else
                {
                    Iterator it = composicao.getAcoes().iterator();
                    while (it.hasNext())
                    {
                        Acao acao = (Acao) it.next();
                        if (acao.getTipo().equals("Criar"))
                        {
                            existeAgPrinc = true;
                            break;
                        }
                    }
                    if (existeAgPrinc)
                        codigo += "\task patches\n\t[\n";
                }

                //condicoes
                criaCodigoCondicaoAcao (composicao);

                if (composicao.getCondicoes().size() > 0)
                    codigo += "\t\t]\n"; //fechando colchete de acoes

                if (existeAgPrinc)
                    codigo += "\t]\n"; //fechando colchete do ask
                codigo += "end\n";
            }
        }
    }
    
    private List getComposicoes (int idModelo)
    {
        //Inicia sessao do hibernate
        sessao = HibernateUtility.getSession();
        
        //Inicializa transacao
        Transaction t = sessao.beginTransaction();
       
        //Consulta coposicoes do modelo
        Query select = sessao.getNamedQuery("BuscarComposicoesDoModelo");
        select.setInteger("idModelo", idModelo);
        List composicoes = select.list();
        
        //Termina transacao
        t.commit();        
        
        return composicoes;
    }
    
    //determina agente principal da composicao, se houver
    private Agente determinaAgentePrincipal (Composicao composicao)
    {
        //agente principal da condicao
        Agente agentePrincipal = null;
        
        //procurando agente nas condicoes
        Iterator iterator = composicao.getCondicoes().iterator();
        while (iterator.hasNext())
        {
            Condicao condicao = (Condicao) iterator.next();
            
            //Se for percepcao => agente principal eh o agente movel
            if (condicao.getTipo().equals("Per"))
            {
                return ((Percepcao) condicao).getAgentePrincipal();
            }
            
            //Se for posicionamento => agente eh o agente principal
            if (condicao.getTipo().equals("Pos"))
            {
                Posicionamento p = (Posicionamento) condicao;
                agentePrincipal = p.getAgente();
                if(p.getAgentePrincipal() != null)
                    return p.getAgentePrincipal();
            }
            
            //Se for cor de agente => agente eh o agente principal
            if (condicao.getTipo().equals("CorAg"))
            {
                agentePrincipal = ((CorAgente) condicao).getAgente();
            }
            
            //Se for valorVariavel ver se variavel eh local a um agente
            if (condicao.getTipo().equals("ValVar"))
            {
                Variavel variavel = ((ValorVariavel) condicao).getVar();
                Agente agente = variavel.getAgente();
                if (agente != null)
                    agentePrincipal =  agente;
            }
            
            //Se for Comparacao de Variaveis verificar se eh local a um agente
            if (condicao.getTipo().equals("CmpVar"))
            {
                Variavel var1 = ((CmpVariavel) condicao).getVar1();
                Agente agente = getAgente(var1);
                if (agente != null)
                    agentePrincipal =  agente;   
                
                Variavel var2 = ((CmpVariavel) condicao).getVar2();
                agente = getAgente(var2);
                if (agente != null)
                    agentePrincipal = agente;
            }
        }
        
        if (agentePrincipal != null)
            return agentePrincipal;
        
        //procurando agente nas condicoes
        iterator = composicao.getAcoes().iterator();
        while (iterator.hasNext())
        {
            Acao acao = (Acao) iterator.next();
            
            //Acao do tipo recupera valor variavel
            if (acao.getTipo().equals("RecValVar"))
            {
                RecuperarValorVariavel rec = (RecuperarValorVariavel) acao;
                Agente ag1 = rec.getVariavel().getAgente();
                Agente ag2 = rec.getAgente();
                if(ag2 != null)
                    agentePrincipal = ag2;
                else
                    if(ag1 != null)
                        agentePrincipal = ag1;
            }
        
            //Acao do tipo incrementar variavel
            if (acao.getTipo().equals("IncValVar"))
            {
                IncrementarVariavel inc = (IncrementarVariavel) acao;
                if (inc.getVariavel().getAgente() != null)
                    return inc.getVariavel().getAgente();
            }
        
            //Acao do tipo gerar novo objeto
            if (acao.getTipo().equals("Criar"))
            {
                GerarNovoObjeto acaoCriar = (GerarNovoObjeto) acao;
                if(acaoCriar.getAgente().getTipo().equals("Movel"))
                    return acaoCriar.getAgente();
            }
        
            //Acao do tipo alterar movimento
            if (acao.getTipo().equals("AltMov"))
            {
                AlterarMovimento altMov = (AlterarMovimento) acao;
                return altMov.getAgenteMovel();
            }
            
            //Acao do tipo parar movimento
            if (acao.getTipo().equals("PararMov"))
            {
                PararMovimento pararMov = (PararMovimento) acao;
                return pararMov.getAgenteMovel();
            }
        
            //Acao do tipo alterar posicao
            if (acao.getTipo().equals("AltPos"))
            {
                AlterarPosicao altPos = (AlterarPosicao) acao;
                return altPos.getAgenteMovel();
            }
        
            //Acao do tipo destruir
            if (acao.getTipo().equals("Destruir"))
            {
                Destruir acaoDestruir = (Destruir) acao;
                return acaoDestruir.getAgente();
            }
        
            //Acao do tipo alterar forma
            if (acao.getTipo().equals("AltFor"))
            {
                AlterarForma altFor = (AlterarForma) acao;
                return altFor.getAgenteMovel();
            }
        
            //Acao do tipo alterar cor
            if (acao.getTipo().equals("AltCor"))
            {
                AlterarCor altCor = (AlterarCor) acao;
                return altCor.getAgente();
            }
        }
        
        return agentePrincipal;
    }
    
    private List ordenaAcoesPeloId (Set acoes, Composicao composicao)
    {
        //iniciando transacao
        Transaction transacao = sessao.beginTransaction();
        
        Query select = sessao.createQuery("from Acao where composicao = :composicao order by idAcao");
        select.setParameter("composicao", composicao);
        List objetos = select.list();
        
        transacao.commit();
        return objetos;
    }
    
    private void criaCodigoCondicaoAcao (Composicao composicao)
    {
        //Extraindo condicoes e acoes
        Set condicoes = composicao.getCondicoes();
        List acoes = ordenaAcoesPeloId(composicao.getAcoes(), composicao);
        
        //traduzindo o operador logico
        String operadorLogico;
        if (composicao.getOperador().equals("e"))
            operadorLogico = "and";
        else
            operadorLogico = "or";
        
        //lista com os agentes inseridos no codigo da composicao
        List agentes = new ArrayList();
        
        //Pegando condicoes de todos os agentes
        criaCodigoCondicao (condicoes, acoes, operadorLogico, agentes); 
    }
    
    /***************************************************************
     * CONDICOES E ACOES
     **************************************************************/
    
    private void criaCodigoCondicao (Set condicoes, List acoes, 
            String operadorLogico, List agentes)
    {
        String codigoCondicoes = "\t\tif\n";
        int numeroCondicoes = 0;
        
        String codigoAcoes = "";
        if (condicoes.size() > 0)
            codigoAcoes += "\t\t[\n";
        
        String condicoesEspecificas = "";
        String acoesEspecificas;
        Iterator iterator = acoes.iterator();
        while (iterator.hasNext())
        {
            Acao acao = (Acao) iterator.next();
            
            //Retornando o agente envolvido na condicao
            Agente agente = agenteEnvolvidoAcao (acao);
        
            //Variavel global
            if (agente == null)
            {
                CodigoAcao codigoAcao = new CodigoAcao (acao, agentePrincipal);
                codigoAcoes += "\t\t\t" + codigoAcao.getCodigo() + "\n";
            }
                
            else
            {
                //Verifica se agente esta na lista
                if (!agentes.contains(agente.getNome()))
                {
                    //Pega todas as condicoes que envolve esse agente
                    condicoesEspecificas = criaCodigoCondicaoUmAgente(agente, 
                            condicoes, operadorLogico);
                    
                    //Pega todas as acoes que envove esse agente
                    acoesEspecificas = criaCodigoAcao(agente, acoes);
                                        
                    //Inserindo agente na lista
                    agentes.add(agente.getNome());
                    
                    //Se nao tiver condicoes
                    if (condicoesEspecificas.length() == 1)
                    {
                        if(agentePrincipal == null || !agente.getNome().equals(agentePrincipal.getNome()))
                        {
                            //Se for uma unica do tipo criar
                            if (acoes.size() == 1 && 
                                    ((Acao) acoes.get(0)).getTipo().equals("Criar"))
                                codigoAcoes += acoesEspecificas;
                            
                            else    
                            {
                                if (agente.getTipo().equals("Movel"))
                                    codigoAcoes += "\t\t\task conjunto_" + agente.getNome();
                                else
                                    codigoAcoes += "\t\t\task patches with [pcolor = " + 
                                            Traducao.cor(agente.getCor()) + " ]";
                           
                                codigoAcoes += " [ " + 
                                        acoesEspecificas.replaceAll("\n", " ").replaceAll("\t","") 
                                        + " ] \n";
                            }
                        }
                        else 
                            codigoAcoes += "\t\t\t" + 
                                    acoesEspecificas.replaceAll("\n", " ").replaceAll("\t","") 
                                    + "\n";
                    }
                    
                    else
                    {
                        //Verificando se eh agente principal
                        if (agentePrincipal == null || 
                                !agente.getNome().equals(agentePrincipal.getNome()))
                        {
                            codigoAcoes += condicoesEspecificas.replaceAll(
                                    "   ###", "\task").replaceAll("\n"," ") + "[ " + 
                                    acoesEspecificas.replaceAll("\n", " ").replaceAll("\t","").replaceAll("hatch-conjunto_" + agente.getNome(), "hatch")  +
                                    " ]\n";
                            condicoesEspecificas = condicoesEspecificas.replaceAll(
                                    "###", "any?");
                        }
                        else
                            codigoAcoes += acoesEspecificas;
                        codigoCondicoes += condicoesEspecificas.replaceAll("\n",
                                " " + operadorLogico + "\n");  
                    }
                }
            }
        }
        
        //pegando condicoes de agentes que nao estao na lista e com variaveis globais
        codigoCondicoes += criaCodigoCondicoesNaoUsadas (agentes, condicoes, 
                operadorLogico);
      
        //se so tem acoes
        if (codigoCondicoes.equals("\t\tif\n"))
        {
            codigo += codigoAcoes.replaceAll("\t\t\t", "\t");
        }
        else
        {
            //retirando ultimo operador logico de condicoes
            if (operadorLogico.equals("or"))
                codigoCondicoes = codigoCondicoes.substring(0, 
                        codigoCondicoes.length() - 3) + "\n";
            else
                codigoCondicoes = codigoCondicoes.substring(0, 
                        codigoCondicoes.length() - 4) + "\n";
            
            //verificando se tem agentePrincipal
            if (agentePrincipal == null)
                codigo += codigoCondicoes.replaceAll("\t\t\t","\t\t").replaceAll("\t\tif","\tif") +
                        codigoAcoes.replaceAll("\t\t\t","\t\t");
            else
                codigo += codigoCondicoes + codigoAcoes;
        }
    }
    
    private String criaCodigoCondicaoUmAgente(Agente agente, Set condicoes, 
            String operadorLogico)
    {
        String codigo = "";
        
        if (agentePrincipal == null) 
            return codigo;
        
        List condicoesEspecificas = new ArrayList();
        
        //Separando todas as condicoes desse agente
        Iterator iterator = condicoes.iterator();
        while(iterator.hasNext())
        {
            Condicao condicao = (Condicao) iterator.next();
            
            //Verifica se condicao envolve soh esse agente
            if (envolveSohAgente (condicao, agente))
            {
                condicoesEspecificas.add(condicao);
            }
        }
        
        //Verificando se eh agente principal
        if (agente.getNome().equals(agentePrincipal.getNome()))
        {
            for (int i=0; i<condicoesEspecificas.size(); i++)
            {
                Condicao condicao = (Condicao) condicoesEspecificas.get(i);
                CodigoCondicao codigoCondicao =
                        new CodigoCondicao (condicao);
                codigo += "\t\t   " + codigoCondicao.getCodigo();
                
                //if (i < condicoesEspecificas.size() - 1)
                //    codigo += " " + operadorLogico;
                codigo += "\n";
            }
        }
        
        else
        {
            //Procurando por percepcao
            int distancia = Integer.MIN_VALUE;
            Percepcao percepcaoMaiorDistancia = null; //percepcao com maior distancia
            String codigoEspecifico = "";
            for (int i=0; i<condicoesEspecificas.size(); i++)
            {
                Condicao condicao = (Condicao) condicoesEspecificas.get(i);
                
                if (condicao.getTipo().equals("Per"))
                {
                    Percepcao percepcao = (Percepcao) condicao;
                    if (distancia < percepcao.getDistancia().intValue())
                    {
                        distancia = percepcao.getDistancia().intValue();
                        percepcaoMaiorDistancia = percepcao;
                    }
                }
                else
                {
                    CodigoCondicao codigoCondicao =
                            new CodigoCondicao (condicao);
                    if (codigoEspecifico.length() > 0)
                        codigoEspecifico += " " + operadorLogico + " ";
                    codigoEspecifico += codigoCondicao.getCodigo();
                }
            }
            
            if (percepcaoMaiorDistancia != null)
            {
                if (distancia == 0)
                {
                    if (agente.getTipo().equals("Movel"))
                        codigo += "\t\t   ### conjunto_" + agente.getNome() + 
                                "-here";
                    else
                        codigo += "\t\t   ### turtles-here " +
                                "with [ pcolor = " + 
                                Traducao.cor(agente.getCor()) + " ]";
                }
                else
                {
                    if (agente.getTipo().equals("Movel"))
                        codigo += "\t\t   ### conjunto_" + agente.getNome() + 
                                " in-radius " + distancia;
                    else
                        codigo += "\t\t   ### patches with [ pcolor = " + 
                                Traducao.cor(agente.getCor()) + " ]" +
                                " in-radius " + distancia;
                }
            }
            
            if (!codigoEspecifico.equals(""))
            {
                if (codigo.equals(""))
                {
                    if (agente.getTipo().equals("Movel"))
                        codigo += "\t\t   ### conjunto_" + agente.getNome();
                    else
                        codigo += "\t\t   ### patches with [ pcolor = " + 
                                Traducao.cor(agente.getCor()) + " ]";
                }
                
                codigo += " with [ " + codigoEspecifico + " ]";
            }
            codigo += "\n";
                
        }
        
        return codigo;
    }
    
    //Cria codigo de condicoes com variaveis globais e agentes que nao estao na lista
    private String criaCodigoCondicoesNaoUsadas (List agentes, 
            Set condicoes, String operadorLogico)
    {
        String codigo = "";
        
        Iterator iterator = condicoes.iterator();
        while (iterator.hasNext())
        {
            Condicao condicao = (Condicao) iterator.next();
            
            Agente agente = agenteEnvolvidoCondicao (condicao);
            
            //Se for condicao global
            if (agente == null)
            {
                CodigoCondicao codigoCondicao = new CodigoCondicao (condicao);
                codigo += "\t\t   " + codigoCondicao.getCodigo();
                
                //operador logico
                codigo += " " + operadorLogico + "\n";
            }
            else if (!agentes.contains(agente.getNome()))
            {
                //Verifica se agente esta na lista
                if (!agentes.contains(agente.getNome()))
                {
                    //Pega todas as condicoes que envolve esse agente
                    String condicoesEspecificas = criaCodigoCondicaoUmAgente(
                            agente, condicoes, operadorLogico);
                    
                    //Inserindo agente na lista
                    agentes.add(agente.getNome());
                    
                    //Verificando se eh agente principal
                    if (!agente.getNome().equals(agentePrincipal.getNome()))
                    {
                        condicoesEspecificas = condicoesEspecificas.replaceAll(
                                "###", "any?");
                    }
                    codigo += condicoesEspecificas.replaceAll("\n",
                            " " + operadorLogico + "\n");
                }
            }
        }
        
        return codigo;        
    }
    
    private boolean envolveSohAgente (Condicao condicao, Agente agente)
    {
        //Se for o agente de percepcao
        if (condicao.getTipo().equals("Per"))
        {
            if (agente.getNome().equals(((Percepcao) condicao).getAgente().getNome()))
                return true;
        }
        
        //Se for o agente de posicionamento
        if (condicao.getTipo().equals("Pos"))
        {
            if (agente.getNome().equals(((Posicionamento) condicao).getAgente().getNome()))
                return true;
        }
        
        //Se for o agente de cor
        if (condicao.getTipo().equals("CorAg"))
        {
            if (agente.getNome().equals(((CorAgente) condicao).getAgente().getNome()))
                return true;
        }

        //Se for valorVariavel ver se variavel eh local ao agente
        if (condicao.getTipo().equals("ValVar"))
        {
            Variavel variavel = ((ValorVariavel) condicao).getVar();
            if (variavel.getAgente() != null && 
                    agente.getNome().equals(variavel.getAgente().getNome()))
                return true;
        }
            
        //Se for Comparacao de Variaveis verificar se eh local a um agente
        if (condicao.getTipo().equals("CmpVar"))
        {
            Variavel var1 = ((CmpVariavel) condicao).getVar1();
            Agente agente1 = getAgente(var1);
                
            Variavel var2 = ((CmpVariavel) condicao).getVar2();
            Agente agente2 = getAgente(var2);
            
            if (agente1 != null && agente1.getNome().equals(agente.getNome()) && 
                    (agente2 == null || 
                     agente2.getNome().equals(agentePrincipal.getNome())))
                return true;
            
            if (agente2 != null && agente2.getNome().equals(agente.getNome()) && 
                    (agente1 == null || 
                     agente1.getNome().equals(agentePrincipal.getNome())))
                return true;
        }
        
        return false;
    }
    
    private Agente agenteEnvolvidoCondicao (Condicao condicao)
    {
        //Se for o agente de percepcao
        if (condicao.getTipo().equals("Per"))
        {
            return ((Percepcao) condicao).getAgente();
        }
        
        //Se for o agente de posicionamento
        if (condicao.getTipo().equals("Pos"))
        {
            return ((Posicionamento) condicao).getAgente();
        }

        //Se for valorVariavel ver se variavel eh local ao agente
        if (condicao.getTipo().equals("ValVar"))
        {
            Variavel variavel = ((ValorVariavel) condicao).getVar();
            return variavel.getAgente();
        }
        
        if (condicao.getTipo().equals("CorAg"))
        {
            return ((CorAgente) condicao).getAgente();
        }
            
        //Se for Comparacao de Variaveis verificar se eh local a um agente
        if (condicao.getTipo().equals("CmpVar"))
        {
            Variavel var1 = ((CmpVariavel) condicao).getVar1();
            Agente agente1 = getAgente(var1);
                
            Variavel var2 = ((CmpVariavel) condicao).getVar2();
            Agente agente2 = getAgente(var2);
            
            if (agente1 == null)
                return agente2;
            if (agente2 == null)
                return agente1;
            if (agente1.getNome().equals(agentePrincipal.getNome()))
                return agente2;
            if (agente2.getNome().equals(agentePrincipal.getNome()))
                return agente1;
        }
        
        return null;
    }
    
    /***************************************************************
     * ACOES
     **************************************************************/
    
    private String criaCodigoAcao (Agente agente, List acoes)
    {
        String codigo = "";
        
        Iterator iterator = acoes.iterator();
        while (iterator.hasNext())
        {
            Acao acao = (Acao) iterator.next();
            
            //Verificando se a acao envolve somente o agente
            if (EnvolveSohAgente(acao, agente))
            {
                CodigoAcao codigoAcao = 
                    new CodigoAcao (acao, agentePrincipal);
                codigo += "\t\t\t" + codigoAcao.getCodigo() + "\n";
            }
        }
        
        return codigo;
    }
    
    private boolean EnvolveSohAgente(Acao acao, Agente agente)
    {
        //Se for alterar Movimento
        if (acao.getTipo().equals("AltMov"))
            return ((AlterarMovimento) acao).getAgenteMovel().getNome().equals(
                    agente.getNome());
        
        //Se for parar Movimento
        if (acao.getTipo().equals("PararMov"))
            return ((PararMovimento) acao).getAgenteMovel().getNome().equals(
                    agente.getNome());
            
        //Se for alterar posicao
        if (acao.getTipo().equals("AltPos"))
            return ((AlterarPosicao) acao).getAgenteMovel().getNome().equals(
                    agente.getNome());
            
        //Se for criar agente
        if (acao.getTipo().equals("Criar"))            
            return ((GerarNovoObjeto) acao).getAgente().getNome().equals(
                    agente.getNome());
        
        //Se for destruir agente
        if (acao.getTipo().equals("Destruir"))
            return ((Destruir) acao).getAgente().getNome().equals(
                    agente.getNome());
            
        //Se for alterar forma
        if (acao.getTipo().equals("AltFor"))
            return ((AlterarForma) acao).getAgenteMovel().getNome().equals(
                    agente.getNome());
            
        //Se for alterar cor
        if (acao.getTipo().equals("AltCor"))
            return ((AlterarCor) acao).getAgente().getNome().equals(
                    agente.getNome());
            
        //Se for incrementar variavel
        if (acao.getTipo().equals("IncValVar"))
        {
            Variavel variavel = ((IncrementarVariavel) acao).getVariavel();
            if (variavel.getAgente() == null)
                return false;
            else
                return agente.getNome().equals(variavel.getAgente().getNome());
        }
            
        //Se for recuperar valor de variavel
        if (acao.getTipo().equals("RecValVar"))
        {
            RecuperarValorVariavel a = (RecuperarValorVariavel) acao;
            Agente ag1 = a.getVariavel().getAgente();
            Agente ag2 = a.getAgente();
            if(ag1 != null && agente.getNome().equals(ag1.getNome()))
                return true;
            else
                if(ag2 != null && agente.getNome().equals(ag2.getNome()))
                    return true;
                else 
                    return false;
        }
        
        return false;
    }
    
    private Agente agenteEnvolvidoAcao (Acao acao)
    {
        //Se for alterar Movimento
        if (acao.getTipo().equals("AltMov"))
            return ((AlterarMovimento) acao).getAgenteMovel();
        
        //Se for parar Movimento
        if (acao.getTipo().equals("PararMov"))
            return ((PararMovimento) acao).getAgenteMovel();
            
        //Se for alterar posicao
        if (acao.getTipo().equals("AltPos"))
            return ((AlterarPosicao) acao).getAgenteMovel();
            
        //Se for criar agente
        if (acao.getTipo().equals("Criar"))
        {
            Agente ag = ((GerarNovoObjeto) acao).getAgente();
            if(ag.getTipo().equals("Movel"))
                return ag;
            else
                return null;
        }
        
        //Se for destruir agente
        if (acao.getTipo().equals("Destruir"))
            return ((Destruir) acao).getAgente();
            
        //Se for alterar forma
        if (acao.getTipo().equals("AltFor"))
            return ((AlterarForma) acao).getAgenteMovel();
            
        //Se for alterar cor
        if (acao.getTipo().equals("AltCor"))
            return ((AlterarCor) acao).getAgente();
            
        //Se for incrementar variavel
        if (acao.getTipo().equals("IncValVar"))
        {
            Variavel variavel = ((IncrementarVariavel) acao).getVariavel();
            return variavel.getAgente();
        }
            
        //Se for recuperar valor de variavel
        if (acao.getTipo().equals("RecValVar"))
        {
            RecuperarValorVariavel a = (RecuperarValorVariavel) acao;
            Agente ag1 = a.getVariavel().getAgente();
            Agente ag2 = a.getAgente();
            if(ag1 == null)
                return ag2;
            else
            {
                if(ag2 == null)
                    return ag1;
                else
                {
                    if(agentePrincipal != null &&
                            agentePrincipal.getNome().equals(ag2.getNome()))
                        return ag1;
                    else
                        return ag2;
                }
            }
        }
        
        return null;
    }
    
    //bixeira de cmpvariavel nao deixa procurar agente da variavel
    private Agente getAgente (Variavel variavel)
    {
        Agente agente = null;
        
        //Inicializa transacao
        Transaction t = sessao.beginTransaction();
       
        //Consulta coposicoes do modelo
        Query select = sessao.getNamedQuery("BuscarVariavelPeloId");
        select.setInteger("idVariavel", variavel.getIdVariavel().intValue());
        List lista = select.list();
        Variavel var = (Variavel) lista.get(0);
        agente = var.getAgente();
        
        //Termina transacao
        t.commit();
        
        return agente;
    } 
}
