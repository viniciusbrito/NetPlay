// responsavel por salvar modelo em um arquivo temporario, para depois executa-lo.

package interfaceNetLogo;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Lucio
 */
public class Execucao {
    
    public Execucao () 
    {        
    }
    
    //retorna nome completo do arquivo
    public static String salvarArquivo (int idModelo, String pasta)
    {
        //compilando modelo
        Arquivo arquivo = new Arquivo (idModelo);
        
        //definindo nome do arquivo        
        String nomeArquivo = "modelo" + 
                new java.util.Date() + ".nlogo";   
        nomeArquivo = nomeArquivo.replaceAll(":","").replaceAll(" ","");        
        
        //criando arquivo
        FileWriter arq;
        try{ 
            arq = new FileWriter (pasta + nomeArquivo);  
            PrintWriter output = new PrintWriter (arq);
            output.print(arquivo.getArquivo());
            output.close();
            arq.close();
        } catch (java.io.IOException e) {}  
        
        return nomeArquivo;
    }
}
