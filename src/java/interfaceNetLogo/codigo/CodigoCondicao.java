/*
 * CodigoCondicao.java
 *
 * Created on 3 de Outubro de 2006, 14:45
 *
 */

package interfaceNetLogo.codigo;

import cdp.*;
import cgt.*;
import interfaceNetLogo.Traducao;
import java.util.List;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class CodigoCondicao {
    
    Condicao condicao;
    
    String codigo;
    
    public CodigoCondicao (Condicao condicao) 
    {
        this.condicao = condicao;
        codigo = "";
        
        criaCodigoCondicao();
    }
    
    public String getCodigo ()
    {
        return codigo;
    }
    
    private void criaCodigoCondicao ()
    {
        //Condicao do tipo posicionamento
        if (condicao.getTipo().equals("Pos"))
        {
            criaCodigoPosicionamento();
            return;
        }
        
        //Condicao do tipo valor percepcao
        if (condicao.getTipo().equals("Per"))
        {
            criaCodigoPercepcao();
            return;
        }
        
        //Condicao do tipo cor de agente
        if (condicao.getTipo().equals("CorAg"))
        {
            criaCodigoCorAgente();
            return;
        }
        
        //Condicao do tipo comparacao de variaveis
        if (condicao.getTipo().equals("CmpVar"))
        {
            criaCodigoCmpVariavel();
            return;
        }
        
        //Condicao do tipo valor variavel
        if (condicao.getTipo().equals("ValVar"))
        {
            criaCodigoValorVariavel();
            return;
        }
        
        //Condicao do tipo valor variavel
        if (condicao.getTipo().equals("Tam"))
        {
            criaCodigoTamanhoAgente();
            return;
        }
    }
    
    private void criaCodigoPosicionamento ()
    {
        Posicionamento condicaoPosicionamento = (Posicionamento) condicao;
        
        //retorna a propria expressao
        
        Agente agente = condicaoPosicionamento.getAgente();
        Agente agentePrincipal = condicaoPosicionamento.getAgentePrincipal();
        
        String expr = condicaoPosicionamento.getExpressao();
        
        if (agente.getTipo().equals("Movel"))
        {
            expr = expr.replaceAll(agente.getNome() + ".xcor", "xcor");
            expr = expr.replaceAll(agente.getNome() + ".ycor", "ycor");
        }
        else
        {
            expr = expr.replaceAll(agente.getNome() + ".xcor", "pxcor");
            expr = expr.replaceAll(agente.getNome() + ".ycor", "pycor");
        }
        
        if(agentePrincipal != null)
        {
            if(agentePrincipal.getTipo().equals("Movel"))
            {
                expr = expr.replaceAll(agentePrincipal.getNome() + ".xcor", "xcor-of myself");
                expr = expr.replaceAll(agentePrincipal.getNome() + ".ycor", "ycor-of myself");
            }
            else
            {
                expr = expr.replaceAll(agentePrincipal.getNome() + ".xcor", "pxcor-of myself");
                expr = expr.replaceAll(agentePrincipal.getNome() + ".ycor", "pycor-of myself");
            }
        }
        
        codigo += expr;
        
        /*if (agente.getTipo().equals("Movel"))
            codigo += condicaoPosicionamento.getExpressao();
        else
        {
            String expr = condicaoPosicionamento.getExpressao();            
            if(expr.startsWith("xcor ") || expr.startsWith("ycor "))
                expr = "p" + expr;
            codigo += expr.replaceAll(
                    " xcor ", " pxcor ").replaceAll(" ycor ", " pycor ");
        }*/
    }
    
    private void criaCodigoPercepcao ()
    {
        Percepcao condicaoPercepcao = (Percepcao) condicao;
        
        //Agente movel
        Agente agentePrincipal = condicaoPercepcao.getAgentePrincipal();
        
        //Segundo agente
        Agente agente = condicaoPercepcao.getAgente();
        
        //distancia da percepcao
        int distancia = condicaoPercepcao.getDistancia().intValue();
        
        //modo da percepcao
        String modo = condicaoPercepcao.getModo();
        
        //Verificando se eh colisao
        if (distancia == 0)
        {
            //Se os dois forem moveis
            if (agente.getTipo().equals("Movel"))
            {
                if(modo.equals("normal"))
                {
                    //Se forem iguais
                    if (agente.getNome().equals(agentePrincipal.getNome()))
                        codigo += "any? other-conjunto_" + 
                                agente.getNome() + "-here";
                    else
                        codigo += "any? conjunto_" + 
                                agente.getNome() + "-here";   
                }
                else if(modo.equals("avancado"))
                {
                    //Se forem iguais
                    if (agente.getNome().equals(agentePrincipal.getNome())) {
                        codigo += "any? other-conjunto_" + 
                                agente.getNome() + "-here with [" + 
                                criaCodigoPercepcaoVariaveis(condicao.getIdCondicao(), "percebido") + " " +
                                criaCodigoPercepcaoCFT(condicao.getIdCondicao()) + " ]";
                    }
                    else {
                        codigo += "any? conjunto_" + 
                                agente.getNome() + "-here with [" + 
                                criaCodigoPercepcaoVariaveis(condicao.getIdCondicao(), "percebido") + " " +
                                criaCodigoPercepcaoCFT(condicao.getIdCondicao()) + " ]";
                    }
                }
            }
            else
                codigo += "pcolor = " + Traducao.cor(agente.getCor());
        }
        
        //distancia maior que 0
        else
        {
            //Se os dois forem moveis
            if (agente.getTipo().equals("Movel"))
            {
                if(modo.equals("normal"))
                {
                    //Se forem iguais
                    if (agente.getNome().equals(agentePrincipal.getNome()))
                        codigo += "any? conjunto_" + 
                                agente.getNome() + " with [self != myself]" +
                                " in-radius " + distancia;
                    else
                        codigo += "any? conjunto_" + 
                                agente.getNome() + " in-radius " + distancia;
                }
                else if(modo.equals("avancado"))
                {
                    //Se forem iguais
                    if (agente.getNome().equals(agentePrincipal.getNome())) {
                        codigo += "any? conjunto_" + 
                                agente.getNome() + " with [self != myself";
                                
                                String aux = criaCodigoPercepcaoVariaveis(condicao.getIdCondicao(), "percebido");
                                String aux2 = criaCodigoPercepcaoCFT(condicao.getIdCondicao());
                                if(aux.equals("") && aux2.equals(""))
                                    codigo += "] in-radius " + distancia;
                                else if(aux.equals("") && !aux2.equals(""))
                                    codigo += " and " + aux2 + "] in-radius " + distancia;
                                else if(!aux.equals("") && aux2.equals(""))
                                    codigo += " and " + aux + "] in-radius " + distancia;
                                else if(!aux.equals("") && !aux2.equals(""))
                                    codigo += " and " + aux2 + " and " + aux +"] in-radius " + distancia;
                    }
                    else {
                        String aux = criaCodigoPercepcaoVariaveis(condicao.getIdCondicao(), "percebido");
                        String aux2 = criaCodigoPercepcaoCFT(condicao.getIdCondicao());
                        String aux3 = " ";
                        if(!aux.equals("") && !aux2.equals(""))
                            aux3 = " and";
                        codigo += "any? conjunto_" + 
                                agente.getNome() + "with ["+aux+aux3+aux2+"] in-radius "+distancia;
                    }
                }
            }
            else
                codigo += "any? patches " + 
                        "with [ pcolor = " + Traducao.cor(agente.getCor()) + "]" +
                        " in-radius " + distancia;
        } 
    }
    
    private void criaCodigoCmpVariavel ()
    {
        CmpVariavel condicaoCmpVariavel = (CmpVariavel) condicao;
        
        //retorna a propria expressao
        codigo += condicaoCmpVariavel.getExpressao();
        
        //OBSERVACAO: SE FOR USAR DUAS VARIAVEIS DE AGENTES DIFERENTES SENDO
        //UM O AG PRINCIPAL => VAR DE AG PRINC TEM QUE TER OF-MYSELF
    }
    
    private void criaCodigoValorVariavel ()
    {
        ValorVariavel condicaoValorVariavel = (ValorVariavel) condicao;
        //Variavel variavel = condicaoValorVariavel.getVar();
        
        codigo += condicaoValorVariavel.getExpr();
    }
    
    private void criaCodigoCorAgente()
    {
        CorAgente condicaoCorAg = (CorAgente) condicao;
        
        if(condicaoCorAg.getAgente().getTipo().equals("Movel"))
            codigo += "color = ";
        else
            codigo += "pcolor = ";
        
        codigo += Traducao.cor(condicaoCorAg.getCor());
    }
    
    private String criaCodigoPercepcaoVariaveis(int idCondicao, String tipo)
    {
        String codigo = "";
        List PA = AplCadastrarCondicao.obterVariaveisPercepcaoAvancado(idCondicao);
        int i = 0;
        for (int k = 0; k < PA.size(); k++)
        {
            PercepcaoAvancado percAv = (PercepcaoAvancado) PA.get(k);
            if(percAv.getAgente().equals(tipo))
            {
                Variavel var = AplCadastrarVariavel.obterVariavel(percAv.getIdVariavel());
                if(i == 0)
                {
                    if(percAv.getValor().toString().contains(">"))
                        codigo += var.getNome() + " " + percAv.getValor();
                    else
                        codigo += var.getNome() + " = " + percAv.getValor();
                    i++;
                }
                else
                {
                    if(percAv.getValor().toString().contains(">"))
                        codigo += " and " + var.getNome() + " " + percAv.getValor();
                    else
                        codigo += " and " + var.getNome() + " = " + percAv.getValor();
                }
            }
            
        }
        return codigo;
    }
    
    private String criaCodigoPercepcaoCFT(int idCondicao)
    {
        String codigo = "";
        List PCFT = AplCadastrarCondicao.obterVariaveisPercepcaoCFT(idCondicao);
        int i = 0;
        for (int k = 0; k < PCFT.size(); k++)
        {
            PercepcaoCFT percCFT = (PercepcaoCFT) PCFT.get(k);
            if(i != 0)
                codigo += " and";
            if(percCFT.getVar().equals("cor")){
                codigo += " color = " + Traducao.cor(percCFT.getValor());
            }
            
            if(percCFT.getVar().equals("forma")) {
                codigo += " shape = \"" + percCFT.getValor() +"\"";
            }
            
            if(percCFT.getVar().equals("tamanho")) {
                codigo += " size " + Traducao.operador(percCFT.getOperador()) +" "+ percCFT.getValor();
            }
            i++;
        }               
        return codigo;
    }
    
    private void criaCodigoTamanhoAgente()
    {
        Tamanho condicaoTam = (Tamanho) condicao;
        
        codigo += "size ";
        codigo += Traducao.operador(condicaoTam.getOperador()) + " ";
        codigo += condicaoTam.getValor();
        
    }
}
