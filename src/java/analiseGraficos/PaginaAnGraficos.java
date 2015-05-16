/*
 * PaginaAnGraficos.java
 *
 * Criado em 7 de Janeiro de 2007, 11:40
 *
 * Cria as paginas de consulta e exclusao de analises de graficos
 *
 */

package analiseGraficos;

import java.util.List;
import cgt.APICadastrarAnaliseGraficos;
import cdp.AnaliseGraficos;

/**
 *
 * @autor Lucio Marcos Barbiero 
 */
public class PaginaAnGraficos {
    
    String pagina;
    
    boolean temAnalises;
    
    ConsultaAnalisesGraficas analise;
    
    public PaginaAnGraficos(String tipo, int idModelo, int idUsuario) 
    {
        analise = new ConsultaAnalisesGraficas (idModelo, idUsuario);
        
        temAnalises = true;
        
        //Se for consultar
        if (tipo.equals("consultar"))
            consultarAnGraficos(idModelo);
        else //Se for exclusao
            excluirAnGraficos();
    }
    
    public String getPagina (String tipo)
    {
        String titulo;
        
        //Se for consultar
        if (tipo.equals("consultar"))
            titulo ="Consultando An&aacute;lises de Gr&aacute;ficos";
        else //Se for exclusao
            titulo = "Excluindo An&aacute;lises de Gr&aacute;ficos";
        
        if (temAnalises)
            return inicioPagina(titulo) + pagina + fimPagina();
        else
            return paginaVazia(titulo);
    }
    
    private void consultarAnGraficos (int idModelo)
    {
        /*pagina = "<div align=\"center\">\n" +
                
                 "<p>&nbsp;</p>\n" +
                 "<p><h1>An&aacute;lises de Gr&aacute;ficos do Modelo " +
                 analise.getNomeModelo() + "</h1></p>\n" +
                 "<br>\n\n";*/
        
        //Pegando todas as analises desse modelo
        List analises = analise.getAnalisesModelo();
   
        if (analises.isEmpty()) //Se nao tiver analises graficas
        {
            pagina = "<br><br><p style=\"color:#FF0000\">Esse modelo " +
                    "n&atilde;o tem an&aacute;lises gr&aacute;ficas.</p>\n";
            temAnalises = false;
        }
        
        else //tem analises graficas
        {
            pagina ="<td width=\"30\"><div align=\"center\" class=\"style41\">#</div></td> \n" +
                    "<td width=\"157\"><div align=\"left\"><span class=\"style41\">Usu&aacute;rio</span></div></td> \n" +
                    "<td width=\"100\"><div align=\"left\"><span class=\"style41\">Data de cria&ccedil;&atilde;o</span></div></td> \n" +
                    "<td width=\"80\">&nbsp;</td> \n" +     
                    "</tr> \n";
            /*pagina = "<table border=\"1\">\n" +
                      "   <tr>\n" +
                      "      <td>N&uacute;mero</td>\n" +
                      "      <td>Usu&aacute;rio</td>\n" +
                      "      <td>Data</td>\n" +
                      "   </tr>\n";*/
            try{
            for(int i=1; i<analises.size() + 1;i++)
            {
                AnaliseGraficos ag = (AnaliseGraficos) analises.get(i-1); 
                
                pagina += "<tr><td><div align=\"center\" class=\"style41\">"+ i +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ ag.getUsuario().getLogin() +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ APICadastrarAnaliseGraficos.formataData(
                                           ag.getDataCriacao()) + "</div></td> \n" +
                    "<td bgcolor=\"#333300\"><div align=\"center\" class=\"style34\"> \n" +
                    "<div align=\"center\"><a href=\"analisandografico.jsp?id=" + idModelo + "&nomeArquivo=" + 
                        java.net.URLEncoder.encode(ag.getNomeArquivo(), "utf-8") + "\">\n" +
                    "<span class=\"style34\">Consultar</span></div>";
                                
            }
            } catch (java.io.UnsupportedEncodingException e) {}
            
            //pagina += "</table>\n";
        }
        
        analise.finaliza();
    }
    
    private void excluirAnGraficos ()
    {
        /*pagina = "<div align=\"center\">\n" +
                
                 "<p>&nbsp;</p>\n" +
                 "<p><h1>An&aacute;lises de Gr&aacute;ficos do Modelo " +
                        analise.getNomeModelo() + "</h1></p>\n" +
                "<br>\n";*/
        
        //pegando todas as analises desse modelo feita por esse usuario
        List analises = analise.getAnalisesModeloUsuario();
   
        if (analises.isEmpty()) //Se nao tem nenhuma analise feita pelo usuario
        {
            pagina = "<br><br><p style=\"color:#FF0000\">" +
                    "Esse modelo n&atilde;o tem an&aacute;lises " +
                    "gr&aacute;ficas feitas por voc&ecirc;</p>\n";
            temAnalises = false;
        }
        else //se tem analises feitas pelo usuario
        {
            pagina ="<td width=\"30\"><div align=\"center\" class=\"style41\">#</div></td> \n" +
                    //"<td width=\"157\"><div align=\"left\"><span class=\"style41\">Usu&aacute;rio</span></div></td> \n" +
                    "<td width=\"100\"><div align=\"left\"><span class=\"style41\">Data de cria&ccedil;&atilde;o</span></div></td> \n" +
                    "<td width=\"80\">&nbsp;</td> \n" +     
                    "</tr> \n";
            /*pagina = "<table border=\"1\">\n" +
                      "  <tr>\n" +
                      "      <td>N&uacute;mero</td>\n" +
                      "      <td>Data</td>\n" +
                      "   </tr>\n";*/
            
            for(int i=1; i<analises.size() + 1;i++) 
            {
                AnaliseGraficos ag = (AnaliseGraficos) analises.get(i-1);
                
                pagina += "<tr><td><div align=\"center\" class=\"style41\">"+ i +"</div></td> \n" +
                    //"<td><div align=\"left\" class=\"style41\">"+ variavel.getNome() +"</div></td> \n" +
                    "<td><div align=\"left\" class=\"style41\">"+ APICadastrarAnaliseGraficos.formataData(
                                           ag.getDataCriacao()) + "</div></td> \n" +
                    "<td bgcolor=\"#333300\"><div align=\"center\" class=\"style34\"> \n" +
                    "<div align=\"center\"><a href=\"excluirAnaliseGrafica.jsp?idAnalise=" + ag.getIdAnalise() + "\">\n" +
                    "<span class=\"style34\">Excluir</span></div>";
                
                /*pagina += "   <tr>\n" +
                          "      <td>" + i + "</td>\n" +
                          "      <td>" + APICadastrarAnaliseGraficos.formataData(
                                           ag.getDataCriacao()) + "</td>\n" +
                          "      <td> </td>\n" +
                    
                          "      <td valign=\"middle\">\n" +
                          "        <form action=\"excluirAnaliseGrafica.jsp\" method=\"post\">\n" +
                          "          <input type=\"hidden\" name=\"idAnalise\" value=\"" + ag.getIdAnalise() + "\">\n" +
                          "          <input type=\"submit\" value=\"Excluir\">\n" +
                          "        </form>\n" +
                          "      </td>\n" +                    
                          "   </tr>\n";*/
            }
            //pagina += "</table>\n";
        }
        analise.finaliza();
    }
    
    private String inicioPagina (String titulo)
    {
        return      "<html>\n" +
                    "<head>\n" +
                    "<title>" + titulo + "</title>\n" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\n" +
                    "<style type=\"text/css\">\n" +
                    "<!--\n" +
                    "body,td,th {font-size: 11px; color: #333300; font-family: Arial, Helvetica, sans-serif;font-weight: bold;}\n" +
                    "body {background-color: #DCE7DE;}\n" +
                    "a {font-size: 12px;color: #DCE7DE;	font-weight: bold;}\n" +
                    "a:link {text-decoration: none;}\n" +
                    "a:visited {text-decoration: none;color: #DCE7DE;}\n" +
                    "a:hover {text-decoration: none;color: #A8C4AD;}\n" +
                    "a:active {	text-decoration: none;	color: #DCE7DE;}\n" +
                    ".style1 {color: #DCE7DE} \n" +
                    ".style38 {font-size: 14px} \n" +
                    ".style40 {color: #DCE7DE; font-size: 14px; } \n" +
                    ".style41 {color: #405B45} \n" +
                    ".style34 {color: #DCE7DE} \n" +
                    "-->\n" +
                    "</style>\n" +
                    "</head> \n" +
                    "<body> \n" +
                    "<div align=\"center\"> \n" +
                    "<table width=\"500\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> \n" +
                    "<tr> \n" +
                    "<td colspan=\"4\"><div align=\"center\" class=\"style38\"> \n" +
                    "<table width=\"500\" height=\"32\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"#DCE7DE\" bgcolor=\"#333300\"> \n" +
                    "<tr> \n" +
                    "<td width=\"457\" height=\"30\"><div align=\"center\"><span class=\"style40\">" + 
                        "An&aacute;lises de Gr&aacute;ficos do Modelo " +
                        analise.getNomeModelo() + "</span></div></td> \n" +
                    "</tr> \n" +
                    "</table> \n" +
                    "</div> \n" +
                    "</td> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                    "<td height=\"15\" colspan=\"4\"><div align=\"center\"> \n" +
                    "</div></td> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                    "<td height=\"15\" colspan=\"4\"><div align=\"center\"> </div></td> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                    "<td height=\"15\" colspan=\"4\"><div align=\"center\"> \n" +
                    "<table width=\"500\" border=\"1\" bgcolor=\"#BBD0BF\"> \n" +
                    "<tr> \n" +
                    "<td><div align=\"center\"> \n" +
                    "<table width=\"380\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> \n" +
                    "<tr> \n" +
                    "<td> \n" +
                    "<table width=\"380\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> \n" +
                    "<tr> \n" +
                    "<td width=\"380\" height=\"10\" valign=\"top\">&nbsp;</td> \n" +
                    "</tr> \n" +
                    "<tr> \n" +
                    "<td><div align=\"center\"> \n" +
                    "<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#DCE7DE\"> \n" +
                    "<tr>";
    }
    
    private String fimPagina()
    {
        return       "</table> \n" +
                     "</div></td> \n" +
                     "</tr> \n" +
                     "</table> \n" +
                     "</td> \n" +
                     "</tr> \n" +
                     "<tr> \n" +
                     "<td height=\"10\"><div align=\"right\"> \n" +
                     "</div></td> \n" +
                     "</tr> \n" +
                     "<tr> \n" +
                     "<td width=\"380\" height=\"10\"><div align=\"right\"> \n" +
                     "</div></td> \n" +
                     "</tr> \n" +
                     "</table> \n" +
                     "</div></td> \n" +
                     "</tr> \n" +
                     "</table> \n" +
                     "</div></td> \n" +
                     "</tr> \n" +
                     "</table> \n" +
                     "</div> \n" +
                     "<div align=\"center\"></div> \n" +
                     "</body> \n" +
                     "</html> \n";
    }
    
    private String paginaVazia(String titulo)
    {
        return      "<html>\n" +
                    "<head>\n" +
                    "<title>" + titulo + "</title>\n" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\n" +
                    "<style type=\"text/css\">\n" +
                    "<!--\n" +
                    "body,td,th {font-size: 11px; color: #333300; font-family: Arial, Helvetica, sans-serif;font-weight: bold;}\n" +
                    "body {background-color: #DCE7DE;}\n" +
                    "a {font-size: 12px;color: #DCE7DE;	font-weight: bold;}\n" +
                    "a:link {text-decoration: none;}\n" +
                    "a:visited {text-decoration: none;color: #DCE7DE;}\n" +
                    "a:hover {text-decoration: none;color: #A8C4AD;}\n" +
                    "a:active {	text-decoration: none;	color: #DCE7DE;}\n" +
                    ".style1 {color: #DCE7DE} \n" +
                    ".style38 {font-size: 14px} \n" +
                    ".style40 {color: #DCE7DE; font-size: 14px; } \n" +
                    ".style41 {color: #405B45} \n" +
                    ".style34 {color: #DCE7DE} \n" +
                    "-->\n" +
                    "</style>\n" +
                    "</head> \n" +
                    "<body> \n" +
                pagina +
                    "</body>\n" +
                    "</html>\n";
    }
}
