<%@ page contentType="text/html; charset=UTF-8" language="java"
import="java.sql.*" 
import="cgt.*" 
import="cdp.*" 
import="java.util.*"
import="utilitario.HibernateUtility"
import="org.hibernate.Session"
import="org.hibernate.Transaction"
import="org.hibernate.Query"
import="org.hibernate.Criteria"
errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
    String pai = "";
    String logica = "";

    pai = request.getParameter("pai");
    logica = request.getParameter("logica");

    if(pai == null)
        pai = "";
    
    boolean ehLogica;
    if(logica == null || logica.equals("1"))
        ehLogica = true;
    else
        ehLogica = false;

%>

<html>
    <head>
        <title>NETPLAY</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            <!--
            body,td,th {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 11px;
            color: #333300;
            font-weight: bold;
            }
            body {
            background-color: #DCE7DE;
            }
            a {
            font-family: Arial, Helvetica, sans-serif;
            font-size: 12px;
            color: #DCE7DE;
            font-weight: bold;
            }
            a:link {
            text-decoration: none;
            }
            a:visited {
            text-decoration: none;
            color: #DCE7DE;
            }
            a:hover {
            text-decoration: none;
            color: #A8C4AD;
            }
            a:active {
            text-decoration: none;
            color: #DCE7DE;
            }
            .style1 {color: #DCE7DE}
            .style5 {color: #FF0000}
            .style38 {font-size: 14px}
            .style40 {color: #DCE7DE; font-size: 11px; }
            -->
        </style>
        <script language="JavaScript" type="text/JavaScript">
        </script>
    </head>

    <body>
        <form name="EditorExpressao" action="EditorExpressao" method="POST">
            <div align="center">
                <table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td colspan="2"><div align="center" class="style38">Edi&ccedil;&atilde;o de Expressões </div></td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="2"><table width="400" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="80"><div align="center">Express&atilde;o:</div></td>
                                <td width="320"><div align="center">
                                    <textarea name="expressao" rows="4" cols="50" disabled></textarea>
                                </div></td>
                            </tr>
                        </table></td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="200"><div align="center">
                            <table width="162" border="1" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="25"><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'N&uacute;mero 7'" onclick="javascript:document.EditorExpressao.expressao.value += '7'" type="button" value="7" name="7" /></div></td>
                                    <td width="25"><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'N&uacute;mero 8'" onclick="javascript:document.EditorExpressao.expressao.value += '8'" type="button" value="8" name="8" /></div></td>
                                    <td width="25"><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'N&uacute;mero 9'" onclick="javascript:document.EditorExpressao.expressao.value += '9'" type="button" value="9" name="9" /></div></td>
                                    <td width="34"><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Divis&atilde;o\nEx.: 3 / 2 = 1.5'" onclick="javascript:document.EditorExpressao.expressao.value += ' / '" type="button" value="/" name="/" /></div></td>                                    
                                </tr>
                                <tr>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'N&uacute;mero 4'" onclick="javascript:document.EditorExpressao.expressao.value += '4'" type="button" value="4" name="4" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'N&uacute;mero 5'" onclick="javascript:document.EditorExpressao.expressao.value += '5'" type="button" value="5" name="5" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'N&uacute;mero 6'" onclick="javascript:document.EditorExpressao.expressao.value += '6'" type="button" value="6" name="6" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Multiplica&ccedil;&atilde;o\nEx.: 3 * 2 = 6'" onclick="javascript:document.EditorExpressao.expressao.value += ' * '" type="button" value="*" name="*" /></div></td>                                    
                                </tr>
                                <tr>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'N&uacute;mero 1'" onclick="javascript:document.EditorExpressao.expressao.value += '1'" type="button" value="1" name="1" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'N&uacute;mero 2'" onclick="javascript:document.EditorExpressao.expressao.value += '2'" type="button" value="2" name="2" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'N&uacute;mero 3'" onclick="javascript:document.EditorExpressao.expressao.value += '3'" type="button" value="3" name="3" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Adi&ccedil;&atilde;o\nEx.: 1 + 1 = 2'" onclick="javascript:document.EditorExpressao.expressao.value += ' + '" type="button" value="+" name="+" /></div></td>                                    
                                </tr>
                                <tr>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'N&uacute;mero 0'" onclick="javascript:document.EditorExpressao.expressao.value += ' 0 '" type="button" value="0" name="0" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Abre par&ecirc;nteses'" onclick="javascript:document.EditorExpressao.expressao.value += ' ( '" type="button" value="(" name="(" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Fecha par&ecirc;nteses'" onclick="javascript:document.EditorExpressao.expressao.value += ' ) '" type="button" value=")" name=")" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Subtra&ccedil;&atilde;o\nEx.: 3 - 2 = 1'" onclick="javascript:document.EditorExpressao.expressao.value += ' - '" type="button" value="-" name="-" /></div></td>                                    
                                </tr>
                                <tr>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Potencia&ccedil;&atilde;o\nEx.: 3 ^ 2 = 9'" onclick="javascript:document.EditorExpressao.expressao.value += ' ^ '" type="button" value="^" name="potencia" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Raiz quadrada\nEx.: sqrt 4 = 2'" onclick="javascript:document.EditorExpressao.expressao.value += ' sqrt '" type="button" value="sqrt" name="sqrt" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'pi = 3.141592'" onclick="javascript:document.EditorExpressao.expressao.value += 'pi'" type="button" value="pi" name="pi" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Separador decimal'" onclick="javascript:document.EditorExpressao.expressao.value += '.'" type="button" value="." name="." /></div></td>
                                </tr>
                                <tr>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Divis&atilde;o inteira\nEx.: 3 mod 2 = 1'" onclick="javascript:document.EditorExpressao.expressao.value += ' mod '" type="button" value="mod" name="mod" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Valor absoluto\nEx.: abs -1 = 1'" onclick="javascript:document.EditorExpressao.expressao.value += ' abs '" type="button" value="abs" name="abs" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Logar&iacute;timo\nEx.: log 100 10 = 2'" onclick="javascript:document.EditorExpressao.expressao.value += ' log '" type="button" value="log" name="log" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Contador de agentes\nEx.: cont A#cor=vermelho para contar o n&uacute;mero de agentes A com cor vermelha'" onclick="javascript:document.EditorExpressao.expressao.value += ' cont '" type="button" value="cont" name="cont" /></div></td>                                   
                                </tr>
                                <tr>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'N&uacute;mero rand&ocirc;mico\nEx.: rand 5 gera um n&uacute;mero inteiro entre 0 e 4'" onclick="javascript:document.EditorExpressao.expressao.value += ' rand '" type="button" value="rand" name="rand" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Seno de um &acirc;ngulo em graus\nEx.: sen 30 = 0.5'" onclick="javascript:document.EditorExpressao.expressao.value += ' sin '" type="button" value="sin" name="sin" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Cosseno de um &acirc;ngulo em graus\nEx.: cos 90 = 0'" onclick="javascript:document.EditorExpressao.expressao.value += ' cos '" type="button" value="cos" name="cos" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Tangente de um &acirc;ngulo em graus\nEx.: tan 45 = 1'" onclick="javascript:document.EditorExpressao.expressao.value += ' tan '" type="button" value="tan" name="tan"/></div></td>
                                </tr>
                                <% if (ehLogica) { %>
                                <tr>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Operador lógico maior\nEx.: 1 > 0 &eacute; verdadeiro'" onclick="javascript:document.EditorExpressao.expressao.value += ' > '" type="button" value=">" name="maior" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Operador lógico menor\nEx.: 1 < 0 &eacute; falso'" onclick="javascript:document.EditorExpressao.expressao.value += ' < '" type="button" value="<" name="menor" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Operador lógico igual\nEx.: 1 = 0 &eacute; falso'" onclick="javascript:document.EditorExpressao.expressao.value += ' = '" type="button" value="=" name="igual" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Operador lógico diferente\nEx.: 1 != 0 &eacute; verdadeiro'" onclick="javascript:document.EditorExpressao.expressao.value += ' != '" type="button" value="!=" name="diferente"/></div></td>
                                </tr>
                                <tr>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Operador lógico maior ou igual\nEx.: 1 >= 0 &eacute; verdadeiro'" onclick="javascript:document.EditorExpressao.expressao.value += ' >= '" type="button" value=">=" name="maiorIgual" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Operador lógico menor ou igual\nEx.: 1 <= 0 &eacute; falso'" onclick="javascript:document.EditorExpressao.expressao.value += ' <= '" type="button" value="<=" name="menorIgual" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'E lógico\nEx.: 1 > 0 and 2 > 1 &eacute; verdadeiro'" onclick="javascript:document.EditorExpressao.expressao.value += ' and '" type="button" value="and" name="eLogico" /></div></td>
                                    <td><div align="center"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'Ou lógico\nEx.: 1 < 0 or 2 > 1 &eacute; verdadeiro'" onclick="javascript:document.EditorExpressao.expressao.value += ' or '" type="button" value="or" name="ouLogico"/></div></td>
                                </tr>
                                <% } %>
                            </table>
                        </div></td>
                        
                        <td width="25" valign="top" align="left"><div align="left"><input onmouseover="javascript:document.EditorExpressao.comentarios.value = 'N&uacute;mero 7'" onclick="javascript:document.EditorExpressao.expressao.value += '7'" type="button" value="7" name="7" /></div></td>
                        
                        <td width="200"><div align="center">
                            <%  %>
                            <table width="100" border="1" cellspacing="0" cellpadding="0">
                                <% %>
                                <tr>
                                    <td><div align="center"><input onclick="javascript:document.EditorExpressao.expressao.value += ' xcor '" type="button" value="Coordenada X" name="coordX" /></div></td>
                                </tr>
                                <tr>
                                    <td><div align="center"><input onclick="javascript:document.EditorExpressao.expressao.value += ' ycor '" type="button" value="Coordenada Y" name="coordY" /></div></td>
                                </tr>
                                <% %>
                                <% %>
                                <tr>
                                    <td><div align="center"><input onclick="javascript:document.EditorExpressao.expressao.value += ' var1 '" type="button" value="Variavel 1" name="var1" /></div></td>
                                </tr>
                                <tr>
                                    <td><div align="center"><input onclick="javascript:document.EditorExpressao.expressao.value += ' var2 '" type="button" value="Variavel 2" name="var2" /></div></td>
                                </tr>
                                <% %>
                            </table>
                        </div></td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                   
                    <tr>
                        <td colspan="2"><div align="center">
                            <input name="pai" type="hidden" id="pai" value="<%=pai%>"/>
                            <input name="expr" type="hidden" value="javascript:document.EditorExpressao.expressao.value"/>
                            <input name="salvarExp" type="submit" id="salvarExp" value="Salvar Expressão">
                        </div></td>
                    </tr>
                    
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="2"><div align="center">
                            <textarea name="comentarios" disabled rows="3" cols="30"></textarea> 
                        </div></td>
                   </tr>
                    
                </table>
            </div>
        </form>
    </body>
</html>
