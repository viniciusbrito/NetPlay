/*
 * Codigo.java
 *
 * Criado em 4 de Julho de 2006, 15:26
 */

package interfaceNetLogo;

import java.util.List;
import java.util.Set;
import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import utilitario.*;
import cdp.*;
import interfaceNetLogo.codigo.*;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class Codigo {
    
    Modelo modelo;
    String nomeUsuario;
    
    String comentariosIniciais;
    String declaracoes;
    String funcaoSetup;
    String funcaoGo;
    String outrasFuncoes;
    
    CodigoAgente codigoAg;
    CodigoVariavel codigoVar;
    CodigoPlot codigoPlot;
    CodigoComposicao codigoComposicao;
    CodigoBotao codigoBotao;
    
    Session sessao;
    
    //*******************************************************
    // FUNCOES DE USO GERAL
    //*******************************************************
    
    //Construtor da classe
    public Codigo(int idModelo) 
    {
        //Inicia sessao do hibernate
        sessao = HibernateUtility.getSession();
        
        //Procura modelo e nome do usuario no banco
        verModelo (idModelo);
        nomeUsuario = verNomeUsuario();
        
        //cria as funcoes em NetLogo
        codigoAg = new CodigoAgente(modelo);
        codigoVar = new CodigoVariavel(modelo);
        codigoPlot = new CodigoPlot (modelo);
        codigoComposicao = new CodigoComposicao (modelo);
        codigoBotao = new CodigoBotao(modelo);
        criaFuncoes();
        
        sessao.close();
    }
    
    //Retorna todo o codigo em NetLogo
    public String getCodigo ( )
    {
        String codigo;
        
        codigo = comentariosIniciais +
                declaracoes +
                funcaoSetup +
                funcaoGo +
                outrasFuncoes;
        
        return codigo;
    }
    
    //Transforma o codigo para linguagem HTML
    public String getCodigoHtml ( )
    {
        String codigoHtml = "";
        String codigo = getCodigo();
        boolean foiAlterado;
        
        
        for (int i=0; i<codigo.length(); i++)
        {
            char caracter = codigo.charAt(i);
            foiAlterado = false;
            
            //tabulacao
            if (caracter == '\t')
            {
                codigoHtml += "   ";
                foiAlterado = true;
            }
            
            //comentario colori de cinza a linha toda
            if (caracter == ';')
            {
                codigoHtml += "<font color=\"#666666\">";
                while (caracter != '\n')
                {
                    codigoHtml += caracter;
                    i++;
                    caracter = codigo.charAt(i);
                }
                codigoHtml += "</font>\n";
                foiAlterado = true;
            }
            
            if (!foiAlterado)
                codigoHtml += caracter;
        }
        
        return codigoHtml;
    }
    
    //Consulta modelo no banco
    private void verModelo (int idModelo)
    {
        //Inicializa transacao
        Transaction t = sessao.beginTransaction();
       
        //Consulta modelo pelo id
        Query consulta = sessao.createQuery("from Modelo m where m.idModelo= :id");
        consulta.setInteger("id", idModelo);
        List lista = consulta.list();
        modelo = (Modelo) lista.get(0);
        
        //Termina transacao
        t.commit();
    }
    
    //Consulta nome e sobrenome do usuario no banco
    private String verNomeUsuario ( )
    {
        Usuario usuario = modelo.getUsuario();
        String nomeUsuario = usuario.getNome() + " " + 
                usuario.getSobrenome();
        return nomeUsuario;
    }
    
    //Destrutor da classe
    /*protected void finalize( )
    {
        //Termina sessao do hibernate
        sessao.close();
    }*/
    
    //*******************************************************
    // FUNCOES PRINCIPAIS DO CODIGO NETLOGO
    //*******************************************************
    
    //Constroe as funcoes do codigo NetLogo
    private void criaFuncoes ( ) 
    {
        criaComentariosIniciais();
        criaDeclaracoes();
        criaFuncaoSetup();
        criaFuncaoGo();
        criaOutrasFuncoes();
    }
    
    //Comeca o codigo com nome do modelo, nome e sobrenome de usuario
    private void criaComentariosIniciais ( )
    {
        comentariosIniciais = ";Codigo do modelo " +
                modelo.getNome() + "\n";
        comentariosIniciais += ";Autor: " +
                nomeUsuario + "\n";
        comentariosIniciais += "\n";
    }
    
    //Declara variaveis globais
    private void criaDeclaracoes ( )
    {
        declaracoes = codigoVar.getDeclaracoes() + 
                codigoAg.getDeclaracoes();
    }
    
    //Cria codigo da funcao setup => inicializa ambiente, agentes e variaveis
    private void criaFuncaoSetup ( ) 
    {
        funcaoSetup =
                ";Funcao responsavel pela configuracao inicial do ambiente\n" +
                "to setup\n"+
                    "\t;Limpa a tela\n"+
                    "\tca\n\n";
        
        funcaoSetup += 
                codigoVar.getFuncaoSetupGeral() + //codigo de variaveis sem contador
                codigoAg.getFuncaoSetup() + //codigo de agentes
                codigoVar.getFuncaoSetupContador() + //codigo de variaveis com contador
                codigoPlot.getFuncaoSetup(); //codigo de plots 
        
        funcaoSetup += "end\n\n";
    }
    
    //Cria codigo da funcao go => faz a simulacao
    private void criaFuncaoGo ( )
    {
        funcaoGo = 
                ";Funcao responsavel por disparar o modelo\n" +
                "to go\n"+
                
                codigoAg.getFuncaoGo() + //codigo de agentes
                
                codigoComposicao.getCodigo() + //codigo de composicoes
                
                codigoPlot.getFuncaoGo() + //codigo de plots
                            
                "end\n\n";
    }
    
    private void criaOutrasFuncoes ()
    {
        outrasFuncoes = 
                codigoAg.getOutrasFuncoes() + //codigo de agentes
                codigoPlot.getOutrasFuncoes() +  //codigo de plots
                codigoBotao.getCodigo();
        
        //Se tiver acao envolvendo o alterar movimento
        if (codigoComposicao.getOutrasFuncoes().length() > 1)
            outrasFuncoes += codigoComposicao.getOutrasFuncoes();
    }
}
