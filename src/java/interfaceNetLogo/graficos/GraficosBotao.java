/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceNetLogo.graficos;

import cdp.*;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitario.HibernateUtility;

/**
 *
 * @author vinicius
 */
public class GraficosBotao {
    
    //Configuracoes da tela
    int xMinimo, xMaximo;
    int yMinimo, yMaximo;
    int xAtual, yAtual;
    
    String codigo;

    public GraficosBotao(Modelo modelo, int xi, int xf, int yi, int yf) 
    {
        xMinimo = xi;
        xMaximo = xf;
        yMinimo = yi;
        yMaximo = yf;
        xAtual = xi;
        yAtual = yi;
        
        codigo = "";
        
        criaGraficoBotao (modelo);
    }
    
    public String getCodigo()
    {
        return codigo;
    }
    
    public int getAlturaAtual()
    {
        return yAtual + 49;
    }
    
    //Comprimento minimo 50 
    //Se nome de variavel tiver mais de 5 caracteres => acrescentar 6 por letra ao comprimento
    //Altura 49
    private void criaGraficoBotao (Modelo modelo)
    {
        Iterator iterator = getComposicoes(modelo.getIdModelo()).iterator();
        while (iterator.hasNext())
        {
            Composicao botao = (Composicao) iterator.next();
            if(botao.getTipo().equals("botao"))
            {
                codigo += "BUTTON\n";

                //Determinando comprimento e altura
                int altura = 49;
                int comprimento = 55;
                if (botao.getNome().length() > 5)
                    comprimento += 6 * (botao.getNome().length());

                //Determinando posicionamento
                if (xAtual + comprimento > xMaximo)
                {
                    yAtual += altura + 5;
                    xAtual = xMinimo;
                }
                codigo += xAtual + "\n" + yAtual + "\n";
                codigo += (xAtual + comprimento) + "\n" + 
                        (yAtual + altura) + "\n";
                xAtual += comprimento + 5;

                codigo += botao.getNome() + "\n";
                codigo += "COMANDO_" + botao.getNome() + "\n";

                //Determinando valores
                codigo += "1\n\n";
            }
        }
    }
    
    private List getComposicoes (int idModelo)
    {
        //Inicia sessao do hibernate
        Session sessao;
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
}
