/*
 * CodigoAcao.java
 *
 * Created on 3 de Outubro de 2006, 15:45
 *
 */

package interfaceNetLogo.codigo;

import cdp.*;
import cgt.*;
import interfaceNetLogo.Traducao;

import java.util.Set;
import java.util.List;
import java.util.Iterator;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class CodigoAcao {
    
    Acao acao;
    Agente agentePrincipal;
    
    String codigo;
    
    public CodigoAcao (Acao acao, Agente agentePrincipal) 
    {
        this.acao = acao;
        this.agentePrincipal = agentePrincipal;
        codigo = "";
        
        criaCodigoAcao();
    }
    
    public String getCodigo()
    {
        return codigo;
    }
    
    private void criaCodigoAcao ()
    {
        //Acao do tipo recupera valor variavel
        if (acao.getTipo().equals("RecValVar"))
        {
            criaCodigoRecuperarValorVariavel();
            return;
        }
        
        //Acao do tipo incrementar variavel
        if (acao.getTipo().equals("IncValVar"))
        {
            criaCodigoIncrementarVariavel();
            return;
        }
        
        //Acao do tipo gerar novo objeto
        if (acao.getTipo().equals("Criar"))
        {
            criaCodigoCriar();
            return;
        }
        
        //Acao do tipo alterar movimento
        if (acao.getTipo().equals("AltMov"))
        {
            criaCodigoAlterarMovimento();
            return;
        }
        
        //Acao do tipo parar movimento
        if (acao.getTipo().equals("PararMov"))
        {
            criaCodigoPararMovimento();
            return;
        }
        
        //Acao do tipo alterar posicao
        if (acao.getTipo().equals("AltPos"))
        {
            criaCodigoAlterarPosicao();
            return;
        }
        
        //Acao do tipo destruir
        if (acao.getTipo().equals("Destruir"))
        {
            criaCodigoDestruir();
            return;
        }
        
        //Acao do tipo alterar forma
        if (acao.getTipo().equals("AltFor"))
        {
            criaCodigoAlterarForma();
            return;
        }
        
        //Acao do tipo alterar cor
        if (acao.getTipo().equals("AltCor"))
        {
            criaCodigoAlterarCor();
            return;
        }
        
        //Acao do tipo ocultar
        if (acao.getTipo().equals("Ocultar"))
        {
            criaCodigoOcultar();
            return;
        }
        
        //Acao do tipo mostrar
        if (acao.getTipo().equals("Mostrar"))
        {
            criaCodigoMostrar();
            return;
        }
        
        //Acao do tipo alterar tamanho
        if (acao.getTipo().equals("AltTam"))
        {
            criaCodigoAlterarTamanho();
            return;
        }
    }
    
    private void criaCodigoRecuperarValorVariavel()
    {
        RecuperarValorVariavel recValVar = (RecuperarValorVariavel) acao;
        Variavel variavel = recValVar.getVariavel();
        Agente ag1 = recValVar.getAgente();
        Agente ag2 = variavel.getAgente();
        
        //Compilando expressao
        String expressao = Traducao.expressao(recValVar.getExp(),
                variavel.getModelo());
        Set variaveis = null;
        if(ag1 != null && ag1.getNome().equals(agentePrincipal.getNome()))        
            variaveis = ag1.getVariaveis();
        if(ag2 != null && ag2.getNome().equals(agentePrincipal.getNome())) 
            variaveis = ag2.getVariaveis();
        if(variaveis != null && variaveis.size() > 0 && ag1 != null)
        {
            Iterator iterator = variaveis.iterator();
            while(iterator.hasNext())
            {
                Variavel var = (Variavel) iterator.next();
                String nome = var.getNome();
                expressao = expressao.replaceAll(" " + nome + " ", 
                        " " + nome + "-of myself ");
                if(expressao.endsWith(" " + nome))
                    expressao += "-of myself ";
                if(expressao.startsWith(nome + " "))
                    expressao = nome + "-of myself " + expressao.substring(
                            nome.length());
            }
        }
        
        //Verificando se variavel eh o ag principal
        String complemento;
        if(ag1 != null && ag2 != null && ag2.getNome().equals(agentePrincipal.getNome())) 
            complemento = "-of myself ";
        else
            complemento = " ";
        
        codigo += "set " + variavel.getNome() + complemento + expressao;
    }
    
    private void criaCodigoIncrementarVariavel ()
    {
        IncrementarVariavel incrVar = (IncrementarVariavel) acao;
        Variavel variavel = incrVar.getVariavel();
        
        codigo += "set " + variavel.getNome() + " " +
                "( " + variavel.getNome() +
                " + " + incrVar.getIncremento() + " )";
    }
    
    private void criaCodigoCriar ()
    {
        GerarNovoObjeto acaoCriar = (GerarNovoObjeto) acao;
        
        //determinando agente da acao
        Agente agAux = acaoCriar.getAgente();
        Agente agente = AplCadastrarAgente.obterAgente(agAux.getIdAgente());
        
        //se for movel
        if (agente.getTipo().equals("Movel"))
        {
            if (agentePrincipal != null &&
                    agente.getNome().equals(agentePrincipal.getNome()))
                codigo += "hatch ";
            else
                codigo += "hatch-conjunto_" + agente.getNome();
            
            AgenteMovel agMovel = (AgenteMovel) agente;
            if(acaoCriar.getPosicao().equals("proximo"))
            {
                codigo += " 1 [ setxy xcor + random 2 ycor + random 2 " +
                        "set color " + Traducao.cor(GerarNovoObjeto.buscar(acao.getIdAcao(), agMovel.getIdAgente()).getCor()) + " " +
                        "set size " + acaoCriar.getTamanho() + " " +
                        "set shape \"" + Traducao.forma(acaoCriar.getForma()) + "\"" + " " +
                        variaveisAgente(agMovel) + "]";   
            }
            
            if(acaoCriar.getPosicao().equals("aleatorio"))
            {
                codigo += " 1 [ setxy random-xcor random-ycor " +
                        "set color " + Traducao.cor(GerarNovoObjeto.buscar(acao.getIdAcao(), agMovel.getIdAgente()).getCor()) + " " +
                        "set size " + acaoCriar.getTamanho() + " " +
                        "set shape \"" + Traducao.forma(acaoCriar.getForma()) + "\"" + " " +
                        variaveisAgente(agMovel) + "]";
            }
        }
        //se for patch
        else
        {
            codigo += "set pcolor " +
                    Traducao.cor(agente.getCor());
        }
    }
    
    private void criaCodigoDestruir ()
    {
        Destruir acaoDestruir = (Destruir) acao;
        
        //agente dessa acao
        Agente agente = acaoDestruir.getAgente();
        
        if (agente.getTipo().equals("Movel"))
            codigo += "die";
        else
            codigo += "set pcolor black";
    }
    
    private void criaCodigoAlterarPosicao ()
    {
        AlterarPosicao altPos = (AlterarPosicao) acao;
        
        codigo += "set xcor " +
                altPos.getNovoX() + " " +
                "set ycor " +
                altPos.getNovoY();
    }
    
    private void criaCodigoAlterarForma ()
    {
        AlterarForma altFor = (AlterarForma) acao;
        
        codigo += "set shape \"" + 
                Traducao.forma(altFor.getNovaForma()) + "\"";
    }
    
    private void criaCodigoAlterarCor ()
    {
        AlterarCor altCor = (AlterarCor) acao;
        
        //recuperando agente
        Agente agente = altCor.getAgente();
        
        //Verificando tipo do agente
        if (agente.getTipo().equals("Movel"))
            codigo += "set color ";
        else
            codigo += "set pcolor ";
        
        codigo += Traducao.cor(altCor.getNovaCor());
    }
    
    private void criaCodigoAlterarMovimento ()
    {
        //Pegando agente movel da acao
        AlterarMovimento altMov = (AlterarMovimento) acao;
        AgenteMovel agente = (AgenteMovel) altMov.getAgenteMovel();
        
        //Agente que provocara alteracao de movimento
        Agente agenteBloqueador = null;
        
        //Procurando percepcoes do modelo envolvendo esse agente
        boolean achouPercepcao = false;
        Set condicoes = acao.getComposicao().getCondicoes();
        Iterator iterator = condicoes.iterator();
        while (iterator.hasNext())
        {
            Condicao condicao = (Condicao) iterator.next();
            if (condicao.getTipo().equals("Per"))
            {
                Percepcao percepcao = (Percepcao) condicao;
                if (percepcao.getAgentePrincipal().getNome().equals(agente.getNome()))
                    agenteBloqueador = percepcao.getAgente();
                if (percepcao.getAgente().getNome().equals(agente.getNome()))
                    agenteBloqueador = percepcao.getAgentePrincipal();
                
                //Se achou agente bloqueador
                if (agenteBloqueador != null)
                {
                    achouPercepcao = true;
                    
                    //Se tipo de movimento for refletir chama a funcao
                    if (agente.getTipoMovimento().equals("Refletir"))
                    {
                        //Se bloqueador nao for borda
                        if (!agenteBloqueador.getTipo().equals("Estacionario Borda"))
                        {
                            codigo += "fd -1 rt (180 + " +
                                    agente.getAngulo1() + ")";
                            break;
                        }
                        else
                        {
                            codigo += "bounce " + agente.getAngulo1() + " " +
                                    Traducao.cor(agenteBloqueador.getCor());
                            break;
                        }
                    }
                    else
                    {
                        //Soh eh ncessario voltar o movimento executado
                        codigo += "fd -1";
                        break;
                    }
                }
            }
        }
        
        if (!achouPercepcao)
            codigo += ";Nao existe nenhuma percepcao nessa composicao " +
                    "envolvendo o agente " + agente.getNome();
    }
    
    private void criaCodigoPararMovimento ()
    {
        //Pegando agente movel da acao
        PararMovimento altMov = (PararMovimento) acao;
        AgenteMovel agente = (AgenteMovel) altMov.getAgenteMovel();
        
        //cancela o movimento que o agente ja fez
        codigo += "fd -1";
    }
    
    public String bounce ()
    {
        String funcao = ";funcao responsavel pela reflexao de agentes moveis ao encontrarem uma borda\n" +
                "to bounce [teta cor]\n" +
                "\tif pcolor = cor and (pcolor-of patch-at-heading-and-distance 0 -1 = cor or pcolor-of patch-at-heading-and-distance 0 1 = cor)\n" +
                "\t[ fd -1\n" +
                "\t  ifelse heading > 0 and heading <= 90\n" +
                "\t    [set heading  (- teta) fd 1]\n" +
                "\t    [ifelse heading > 270 and heading < 360\n" +
                "\t      [set heading teta fd 1]\n" +
                "\t      [ifelse heading > 90  and heading < 180\n" +
                "\t        [set heading (180 + teta) fd 1]\n" +
                "\t        [set heading (180 - teta) fd 1]\n" +
                "\t      ]\n" +
                "\t    ]\n" +
                "\t]\n" +
                
                "\tif pcolor = cor and (pcolor-of patch-at-heading-and-distance 90 1 = cor or pcolor-of patch-at-heading-and-distance 90 -1 = cor)\n" +
                "\t[ fd -1\n" +
                "\t  ifelse heading >= 0 and heading < 90\n" + 
                "\t    [set heading  (180 - teta) fd 1]\n" +
                "\t    [ifelse heading > 270 and heading < 360\n" +
                "\t      [set heading (180 + teta) fd 1]\n" +
                "\t      [ifelse heading > 90  and heading <= 180\n" +
                "\t        [set heading teta fd 1]\n" +
                "\t        [set heading (- teta) fd 1]\n" +
                "\t      ]\n" +
                "\t    ]\n" +
                "\t]\n" +
                "end\n";
        
        return funcao;
    }
    
    //Retorna compilacao das variaveis locais do agentes => acao criar
    private String variaveisAgente (Agente agente)
    {
        codigo = "";
        
        List variaveis = 
                AplCadastrarVariavel.variaveisDoAgente(agente.getIdAgente());
        Iterator iterator = variaveis.iterator();
        int idAcao = acao.getIdAcao();
        String valor;
        while (iterator.hasNext())
        {
            Variavel variavel = (Variavel) iterator.next();
            codigo += " set " + variavel.getNome() + " ";
            
            if (variavel.getTipo().equals("Logica"))
            {
                valor = AplCadastrarAcao.buscarVarCriar(idAcao, variavel.getIdVariavel());
                codigo += Boolean.parseBoolean(valor);
            }
            if (variavel.getTipo().equals("Slider"))
            {
                VariavelSlider varSlider = (VariavelSlider) variavel;
                codigo += varSlider.getValorInicial();
            }
            if (variavel.getTipo().equals("Valor"))
            {
                VariavelValor varValor = (VariavelValor) variavel;
                valor = AplCadastrarAcao.buscarVarCriar(idAcao, variavel.getIdVariavel());
                codigo += Traducao.expressao(valor, varValor.getModelo());
            }
        }
        
        return codigo;    
    }
    
    private void criaCodigoOcultar()
    {
        OcultarAgente acaoOcultar = (OcultarAgente) acao;
        
        //agente dessa acao
        Agente agente = acaoOcultar.getAgente();
        codigo += "ht";
    }
    
    private void criaCodigoMostrar()
    {
        MostrarAgente acaoMostrar = (MostrarAgente) acao;
        
        //agente dessa acao
        Agente agente = acaoMostrar.getAgente();
        codigo += "st";
    }
    
    private void criaCodigoAlterarTamanho()
    {
        AlterarTamanho acaoAltTam = (AlterarTamanho) acao;
        
        //agente dessa acao
        Agente agente = acaoAltTam.getAgenteMovel();
        if(acaoAltTam.getTipoTamanho().equals("alterar")) {
            codigo += "set size " + acaoAltTam.getTamanho();
        }
        else if(acaoAltTam.getTipoTamanho().equals("incremental")) {
            codigo += "set size (size + " + acaoAltTam.getTamanho() + ")";
        }
    }
}
