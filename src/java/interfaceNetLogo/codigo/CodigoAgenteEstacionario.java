/* codigoAgenteMovel.java
 *
 * Created on 7 de Setembro de 2006, 13:07
 */

package interfaceNetLogo.codigo;

import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import cdp.*;
import interfaceNetLogo.Traducao;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class CodigoAgenteEstacionario {
    
    ArrayList agentesEstacionarios;
    
    String declaracoes;
    String funcaoSetup;
    String funcaoGo;
    String outrasFuncoes;
    
    float porcentagemTotal;
    int numAgAleatorio;
    String codigoAgEstAleatorio;
    String codigoAgEstPosicaoDefinida;
    
    String codigoVariaveis;
    
    public CodigoAgenteEstacionario(ArrayList agEstacionario) 
    {
        declaracoes = "";
        funcaoSetup = "";
        funcaoGo = "";
        outrasFuncoes = "";
        
        agentesEstacionarios = agEstacionario;
        
        porcentagemTotal = 0;
        numAgAleatorio = 0;
        codigoAgEstAleatorio = "";
        codigoAgEstPosicaoDefinida = "";
        
        codigoVariaveis = "";
        
        codigoAgEstacionarios();
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
    
    private void codigoAgEstacionarios ()
    {
        //declarando variaveis
        declaracoes = ";Declarando variaveis de agentes estacionarios e borda\n" +
                "patches-own [ ";
        
        if (!agentesEstacionarios.isEmpty())
        {
            //funcao setup - chamada da funcao que cria agentes
            funcaoSetup += "\t;criando agentes estacionarios\n" +
                    "\task patches [ inicializaAgEstacionarios ]\n\n";
            
            Iterator iterator = agentesEstacionarios.iterator();
            while (iterator.hasNext())
            {
                AgenteEstAleatorio agente = (AgenteEstAleatorio) iterator.next();

                //declarando e inicializando variaveis
                if (!agente.getVariaveis().isEmpty())
                    codigoCriaVariaveisAgEstacionarios (agente);
            
                //criando agentes
                codigoCriaAgEstacionarios (agente);
            }
        
            //funcao que cria agentes
            funcaoInicializaAgEstacionarios();
        }
    }
    
    private void codigoCriaVariaveisAgEstacionarios (AgenteEstAleatorio agente)
    {
        codigoVariaveis = "\t;inicializando variaveis do agente estacionario " +
                agente.getNome() + "\n";
        
        Iterator iterator = agente.getVariaveis().iterator();
        while(iterator.hasNext())
        {
            Variavel variavel = (Variavel) iterator.next();
            
            declaracoes += variavel.getNome() + " ";
            
            codigoVariaveis += "\tset " + variavel.getNome() + " ";
            if (variavel.getTipo().equals("Logica"))
            {
                VariavelLogica varLogica = (VariavelLogica) variavel;
                codigoVariaveis += varLogica.getValorLogico();
            }
            if (variavel.getTipo().equals("Slider"))
            {
                VariavelSlider varSlider = (VariavelSlider) variavel;
                codigoVariaveis += varSlider.getValorInicial();
            }
            if (variavel.getTipo().equals("Valor"))
            {
                VariavelValor varValor = (VariavelValor) variavel;
                codigoVariaveis += Traducao.expressao(varValor.getValor(), varValor.getModelo());
            }
            codigoVariaveis += "\n";
        }
        codigoVariaveis += "\n";
    }
    
    private void codigoCriaAgEstacionarios (AgenteEstAleatorio agente)
    {
        //Se tiver posicao inicial do tipo aleatorio
        if (agente.getTipoPosicao().equals("aleatorio"))
        {
            porcentagemTotal += agente.getPorcentagem();
            numAgAleatorio++;
            codigoAgEstAleatorio += codigoCriaAgEstAleatorio(agente);
        }

        //Se tiver posicao inicial definida
        else
        {
            codigoAgEstPosicaoDefinida += codigoCriaAgEstPosicaoDefinida(agente);
        }
        
    }
    
    //Cria codigo para inicializar agentes estacionarios aleatorios
    private String codigoCriaAgEstAleatorio (AgenteEstAleatorio agente)
    {
        String codigo = "\t;criando agente estacionario " +
                agente.getNome() + "\n\t";
        
        if (numAgAleatorio > 1)
            codigo += "[";
        
        //Define porcentagem e cor do agente no ambiente 
        codigo += "ifelse (randomico < " +
                porcentagemTotal + " ) " +
                "[set pcolor " +
                Traducao.cor(agente.getCor()) + " ]\n";
        
        return codigo;
    }
    
    //Cria funcões para inicializar agentes estacionarios com posicao inicial definida
    private String codigoCriaAgEstPosicaoDefinida (AgenteEstAleatorio agente)
    {
        String codigo = "\t;criando agente estacionario " +
                agente.getNome() + "\n";
        
        Set posicoesIniciais = agente.getposicoesiniciais(); 
        Iterator iterator = posicoesIniciais.iterator();
        while ( iterator.hasNext() )
        {
            PosicaoInicial posicaoInicial = (PosicaoInicial) iterator.next();
            
            codigo += "\task patch " + posicaoInicial.getXInicial() + " " +
                    posicaoInicial.getYInicial() + "\n";
            codigo += "\t[\n";
            codigo += "\t\t\tset pcolor " + 
                    Traducao.cor(agente.getCor()) + "\n";
            codigo += "\t]\n";
        }
        
        codigo += "\n";
        return codigo;
    }
    
    //Cria funcao que inicializa todos os agentes estacionarios
    private void funcaoInicializaAgEstacionarios ()
    {
        //Definindo agentes estacionarios com posicao inicial aleatoria
        outrasFuncoes += 
                ";funcao que inicializa todos os agentes estacionarios e borda\n" +
                "to inicializaAgEstacionarios\n";
        
        if (codigoAgEstAleatorio.length() > 0)
        {
            outrasFuncoes += 
                    "\t;inicializando agentes estacionarios com posicao inicial aleatoria\n" +
                    "\tlet randomico random-float 100\n" +
                    codigoAgEstAleatorio +
                    "\t[set pcolor black";
        
            for (int i=0; i<numAgAleatorio; i++)
                outrasFuncoes += "]";
        
            outrasFuncoes += "\n\n";
        }
        
        //Definindo agentes estacionarios com posicao inicial definida
        if(codigoAgEstPosicaoDefinida.length() > 0)
            outrasFuncoes += "\t;inicializando agentes estacionarios com posicao inicial definida\n" +    
                    codigoAgEstPosicaoDefinida;
        
        //Inicializando variaveis
        outrasFuncoes += "\t;inicializando variaveis de agentes estacionarios\n";
        outrasFuncoes += codigoVariaveis;
        
        outrasFuncoes += "end\n\n";
    }
}
