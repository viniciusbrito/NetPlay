/*
 * AnaliseGraficos.java
 *
 * Criado em 28 de Novembro de 2006, 17:15
 *
 */

package cdp;

/**
 *
 * @autor Lucio Marcos Barbiero
 */
public class AnaliseGraficos {
    
    private Integer idAnalise;
    
    private Usuario usuario;
    
    private Modelo modelo;
    
    private java.util.Date dataCriacao;
    
    private String nomeArquivo;
    
    //Pasta onde estao os arquivos
    //static String pasta = "C:\\Lucio\\NetPlay ATUAL\\ProjetoFinal\\web\\graficos\\";
    static String pasta = "/usr/share/tomcat5/webapps/ProjetoFinal/graficos/";
    
    public AnaliseGraficos() 
    {
    }
    
    public void setIdAnalise (Integer idAnalise)
    {
        this.idAnalise = idAnalise;
    }
    
    public void setUsuario (Usuario usuario)
    {
        this.usuario = usuario;
    }
    
    public void setModelo (Modelo modelo)
    {
        this.modelo = modelo;
    }
    
    public void setNomeArquivo (String nomeArquivo)
    {
        this.nomeArquivo = nomeArquivo;
    }
    
    public void setDataCriacao (java.util.Date dataCriacao)
    {
        this.dataCriacao = dataCriacao;
    }
    
    public Integer getIdAnalise()
    {
        return idAnalise;
    }
    
    public Usuario getUsuario() 
    {
        return usuario;
    }
    
    public Modelo getModelo()
    {
        return modelo;
    }
    
    public String getNomeArquivo()
    {
        return nomeArquivo;
    }
    
    public java.util.Date getDataCriacao()
    {
        return dataCriacao;
    }
    
    public static String getPasta()
    {
        return pasta;
    }
}
