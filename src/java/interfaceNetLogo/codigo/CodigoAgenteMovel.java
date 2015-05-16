/* CodigoAgenteMovel.java
 *
 * Created on 7 de Setembro de 2006, 13:07
 */

package interfaceNetLogo.codigo;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;
import cdp.*;
import interfaceNetLogo.Traducao;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class CodigoAgenteMovel {
    
    //id do proximo agente movel a ser criado
    int numeroAgMoveis;
    
    ArrayList agentesMoveis;
    
    String declaracoes;
    String funcaoSetup;
    String funcaoGo;
    String outrasFuncoes;
    
    //Cria uma nova instancia de CodigoAgenteMovel
    public CodigoAgenteMovel (ArrayList agMoveis) {
        numeroAgMoveis = 0;
        
        declaracoes = "";
        funcaoSetup = "";
        funcaoGo = "";
        outrasFuncoes = "";
        
        agentesMoveis = agMoveis;
                
        if (!agentesMoveis.isEmpty())
            codigoAgMoveis();
    }
    
    public String getDeclaracoes()
    {
        return declaracoes;
    }
    
    public String getFuncaoSetup()
    {
        return funcaoSetup;
    }
    
    public String getFuncaoGo()
    {
        return funcaoGo;
    }
    
    public String getOutrasFuncoes()
    {
        return outrasFuncoes;
    }
    
    //Cria codigo para agentes moveis
    private void codigoAgMoveis ()
    {
        Iterator iterator = agentesMoveis.iterator();
        while (iterator.hasNext())
        {
            AgenteMovel agente = (AgenteMovel) iterator.next();
            
            //declarando agente
            declaracoes += ";declarando agente movel "+ agente.getNome() + "\n" +
                        "breed [conjunto_" + agente.getNome() + 
                        " " + agente.getNome() + "]\n\n";
            
            //declarando variaveis
            if (!agente.getVariaveis().isEmpty())
                codigoDeclaraVariaveisAgMoveis (agente);
            
            //funcao Setup - criando agentes
            codigoCriaAgMoveis (agente);
            
            // ask n-of 
            inicializarAgente(agente);
            
            //funcao Go - movimento de agentes
            codigoMovimentoAgMoveis (agente);
        }
    }
    
    //Declara variaveis relativas a agentes moveis
    private void codigoDeclaraVariaveisAgMoveis (AgenteMovel agente)
    {
        declaracoes += ";declarando variaveis do agente movel " +
                agente.getNome() + "\n" +
                "conjunto_" + agente.getNome() + "-own[ ";
        
        Iterator iterator = (agente.getVariaveis()).iterator();
        while (iterator.hasNext())
        {
            Variavel variavel = (Variavel) iterator.next();
            declaracoes += variavel.getNome() + " ";
        }
        
        declaracoes += "]\n\n";
    }
    
    //Cria agentes moveis na funcao setup
    private void codigoCriaAgMoveis (AgenteMovel agente)
    {
        funcaoSetup += "\t;criando agente movel " +
                agente.getNome() + "\n";
        
        //Define forma do agente
        funcaoSetup += "\tset-default-shape conjunto_" + 
                agente.getNome() + " " + "\"" + 
                Traducao.forma(agente.getForma()) + "\"" + "\n";
        
        //Define quantidade inicial de turtles dessa especie
        funcaoSetup += "\tcreate-custom-conjunto_" +
                agente.getNome() + " ";
                //agente.getQuantidadeInicial() + "\n";
        
        if (agente.getTipoQtdInicial().equals("Num"))
            funcaoSetup += agente.getQuantidadeInicial();
        else
            funcaoSetup += agente.getVariavelSlider().getNome();
                
        funcaoSetup += "\n\t[\n";
        
        //Define cor do agente
        funcaoSetup += "\t\tset color " + 
                Traducao.cor(agente.getCor()) + "\n";
        
        //Define tamanho do agente
        funcaoSetup += "\t\tset size " +
                agente.getTamanho() + "\n";
        
        //Define posicao inicial do tipo aleatoria dos turtles dessa especie
        if (agente.getTipoPosicao().equals("aleatorio"))
        {
            //incrementa numero de agentes moveis ja criados
            if (agente.getTipoQtdInicial().equals("Num"))
                numeroAgMoveis += agente.getQuantidadeInicial().intValue();
            
            funcaoSetup += "\t\tsetxy random-xcor\n";
            funcaoSetup += "\t\t      random-ycor\n";
        }
        
        //Define posicao inicial do tipo definida dos turtles dessa especie
        else
        {
            funcaoSetup += "\t\tdefinePosicaoInicial_" +
                    agente.getNome() + "\n";
            criaFuncaoDefinePosicaoInicialAgente (agente);
        }
        
        //Inicializa variaveis relativas a agentes moveis
        if (!agente.getVariaveis().isEmpty())
            funcaoSetup += codigoInicializaVariaveisAgMoveis (agente);
        
        funcaoSetup += "\t]\n\n";
    }
    
    //Define movimento de agentes moveis na funcao go
    private void codigoMovimentoAgMoveis (AgenteMovel agente)
    {
        String codigo = "";
        
        if(!agente.getTipoMovimento().equals("Parado"))
        {
            codigo += "\t;movimento do agente movel " +   
                    agente.getNome() + "\n";
            codigo += "\task conjunto_" +
                    agente.getNome() + "\n\t[\n";
            
            //Movimento passear
            if (agente.getTipoMovimento().equals("Passear"))
            {
                codigo += "\t\trt (random-float abs ( " +
                        agente.getAngulo1() + " - " +
                        agente.getAngulo2() + ")) + ";
                if (agente.getAngulo1() > agente.getAngulo2())
                    codigo += agente.getAngulo2();
                else
                    codigo += agente.getAngulo1();
                
                codigo += "\n\t\tfd " +
                        agente.getPasso() + "\n";
            }

            //Movimento refletir
            else
            {
                codigo += "\t\tfd 1\n";
            }
        
            codigo += "\t]\n\n";
        }
        
            funcaoGo += codigo;
    }
    
    //Cria funcoes responsaveis pelo posicionamento inicial de agentes moveis
    private void criaFuncaoDefinePosicaoInicialAgente (AgenteMovel agente)
    {
        String codigo = "";
        codigo += ";Funcao responsavel pela definicao da posicao inicial" +
                "do agente movel " + agente.getNome() + "\n";
        codigo += "to definePosicaoInicial_" + 
                agente.getNome() + "\n";
        
        Set posicoesIniciais = agente.getposicoesiniciais(); 
        Iterator iterator = posicoesIniciais.iterator();
        while ( iterator.hasNext() )
        {
            PosicaoInicial posicaoInicial = (PosicaoInicial) iterator.next();
            
            for (int i=0; i<posicaoInicial.getQuantidadePosicao(); i++)
            {
                codigo += "\task turtle " + numeroAgMoveis + "\n";
                codigo += "\t[\n";
                codigo += "\t\t\tset xcor " + posicaoInicial.getXInicial() + "\n";
                codigo += "\t\t\tset ycor " + posicaoInicial.getYInicial() + "\n";
                codigo += "\t]\n";
                numeroAgMoveis ++;
            }
        }
        
        codigo += "end\n\n";
        outrasFuncoes += codigo;
    }
    
    //Inicializa variáveis relativas a agentes móveis
    private String codigoInicializaVariaveisAgMoveis (AgenteMovel agente)
    {
        String codigo = "\t\t;inicializando variaveis do agente movel " +
                agente.getNome() + "\n";
        
        Iterator iterator = (agente.getVariaveis()).iterator();
        while (iterator.hasNext())
        {
            Variavel variavel = (Variavel) iterator.next();
            codigo += "\t\tset " + variavel.getNome() + " ";
            
            if (variavel.getTipo().equals("Logica"))
            {
                VariavelLogica varLogica = (VariavelLogica) variavel;
                codigo += varLogica.getValorLogico();
            }
            if (variavel.getTipo().equals("Slider"))
            {
                VariavelSlider varSlider = (VariavelSlider) variavel;
                codigo += varSlider.getValorInicial();
            }
            if (variavel.getTipo().equals("Valor"))
            {
                VariavelValor varValor = (VariavelValor) variavel;
                codigo += Traducao.expressao(varValor.getValor(), varValor.getModelo());
            }
            
            codigo += "\n";
        }
        
        return codigo;
    }
    
    private void inicializarAgente(AgenteMovel agente)
    {
        String codigo;
        codigo = "\t;inicializando agentes " +
                agente.getNome() + "\n";
        List iniAgentes = InicializarAgente.obterIAA(agente.getIdAgente());
        InicializarAgente ia;
        InicializarAgenteVariaveis iav;
        for (Object iniAgente : iniAgentes) {
            ia = (InicializarAgente) iniAgente;
            codigo += "\task n-of "+ ia.getQuantidade() +" conjunto_"+ agente.getNome() +" [ set color "+ Traducao.cor(ia.getCor()) +" ";
            codigo += "set size " + ia.getTamanho() + " ";
            codigo += "set shape \"" + Traducao.forma(ia.getForma()) + "\" ";
            codigo += codigoInicializaAgenteVariaveis(agente, ia);
            codigo += "]\n\n";
        }
        
        funcaoSetup += codigo;
    }
    
    //Inicializa variaveis dos agentes inicializados 
    private static String codigoInicializaAgenteVariaveis (AgenteMovel agente, InicializarAgente ia)
    {
        String codigo = "";
        
        Iterator iterator = (agente.getVariaveis()).iterator();
        InicializarAgenteVariaveis iav;
        while (iterator.hasNext())
        {
            Variavel variavel = (Variavel) iterator.next();
            codigo += "set " + variavel.getNome() + " ";
            iav = InicializarAgenteVariaveis.obterIAV(ia.getIdInicializarAgente(), variavel.getIdVariavel());
            codigo += Traducao.expressao(iav.getValor(), variavel.getModelo());            
            codigo += "";
        }
        
        return codigo;
    }
}
