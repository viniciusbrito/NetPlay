<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" import="cdp.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
String status = "";

status = request.getParameter("status");
if(status == null)
	status = " ";
%>

<html>
<head>
<title>NetPlay</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
body,td,th {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #FFFFFF;
	font-weight: bold;
}
h1 {
	font-size: 1mm;
}
.style44 {	font-family: "Futura Md BT";
	font-size: 50px;
}
.style46 {color: #333300}
h2 {
	font-size: 5mm;
}
h3 {
	font-size: 2mm;
}
.style48 {
	color: #DCE7DE;
	font-size: 16px;
}
.style49 {color: #DCE7DE}
a:link {
	color: #DCE7DE;
	text-decoration: none;
}
.style5 {color: #FF0000}

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
a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
}
-->
</style>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_validateForm() { //v4.0
  var i,p,q,nm,test,num,min,max,errors='',args=MM_validateForm.arguments;
  for (i=0; i<(args.length-2); i+=3) { test=args[i+2]; val=MM_findObj(args[i]);
    if (val) { nm=val.name; if ((val=val.value)!="") {
      if (test.indexOf('isEmail')!=-1) { p=val.indexOf('@');
        if (p<1 || p==(val.length-1)) errors+='- '+nm+ ' deve conter um endereco de email.\n';
      } else if (test!='R') { num = parseFloat(val);
        if (isNaN(val)) errors+='- '+nm+' deve conter um numero.\n';
        if (test.indexOf('inRange') != -1) { p=test.indexOf(':');
          min=test.substring(8,p); max=test.substring(p+1);
          if (num<min || max<num) errors+='- '+nm+ ' deve conter um numero entre '+min+' e '+max+'.\n';
    } } } else if (test.charAt(0) == 'R') errors += '- '+nm+ ' eh obrigatorio.\n'; }
  } 
  if(MM_findObj(args[15]).value != MM_findObj(args[18]).value)
     errors += 'Os campos confirmar senha e senha nao coincidem.\n';
  if (errors) alert(''+errors);
  document.MM_returnValue = (errors == '');
}
//-->
</script>
</head>

<body>
<table width="100%" height="576" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#333300">
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top"><div align="center">
        <table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="227"><div align="center" class="style44">NET<span class="style34 style37">PLAY</span></div></td>
            <td width="515">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2"><div align="center">
                <table width="100%" height="5" border="0" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                  <tr>
                    <td>&nbsp;</td>
                  </tr>
                </table>
            </div></td>
          </tr>
        </table>
      </div>
      <div align="center">
          <table width="750" border="0" cellpadding="0" cellspacing="0" bgcolor="#333300">
            <tr>
              <td>&nbsp;</td>
            </tr>
          </table>
  <table width="100%" height="413" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
<tr>
              <td width="16%" rowspan="3" valign="top" bordercolor="#333300">&nbsp;</td>
              <td width="68%" height="264" rowspan="3" valign="top" bordercolor="#333300"><div align="center">
                  <table width="450" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td><h1>&nbsp;</h1></td>
                    </tr>
                    <tr>
                      <td height="30" bgcolor="#333300"><div align="center" class="style48">
                        MEU PERFIL </div></td>
                    </tr>
                    <tr>
                      <td><h1>&nbsp;</h1></td>
                    </tr>
                </table>
                  <div align="right"></div>
                  <div align="right"></div>
                  <table width="450" height="429" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#333300">
                    <tr>
                      <td height="295" valign="top"><div align="center">
                        <form action="controlador.ControladorWeb" method="post" name="cadastrousuario" id="cadastrousuario" onSubmit="MM_validateForm('nome','','R','sobrenome6','','R','email5','','RisEmail','login6','','R','datanascimento2','','R','senha6','','R','confirmarsenha2','','R');return document.MM_returnValue">
                          <table width="450" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td height="20"><div align="center">
                            <div align="center"><span class="style5"><%= status %>
                                                                     <% Usuario u = (Usuario)session.getAttribute("usuario"); %>			  
                                </span>
								</div>
                              </div></td>
                            </tr>
                          </table>
                          <table width="400" height="340" border="1" align="center" cellpadding="0" cellspacing="0">
                            <tr bordercolor="#333300" bgcolor="#DCE7DE">
                              <td width="154"><div align="left" class="style46">Nome</div></td>
                              <td width="240" colspan="3" bgcolor="#DCE7DE">
							    <div align="right">
							      <input name="nome" type="text" id="nome" value="<%= u.getNome() %>" size="40">
					          </div></td>
                            </tr>
                            <tr bordercolor="#333300" bgcolor="#DCE7DE">
                              <td><div align="left" class="style46">Sobrenome</div></td>
                              <td colspan="3"><div align="center">
                                  <p>
                                    <input name="sobrenome" type="text" id="sobrenome6" value="<%= u.getSobrenome() %>" size="40">
                                  </p>
                              </div></td>
                            </tr>
                            <tr bordercolor="#333300" bgcolor="#DCE7DE">
                              <td><div align="left" class="style46">E-mail</div></td>
                              <td colspan="3"><div align="center">
                                  <input name="email" type="text" id="email5" value="<%= u.getEmail() %>" size="40" maxlength="50">
                              </div></td>
                            </tr>
                            <tr bordercolor="#333300" bgcolor="#DCE7DE">
                              <td><div align="left" class="style46">Login</div></td>
                              <td colspan="3"><div align="center">
                                  <input name="login" type="text" id="login6" value="<%= u.getLogin() %>"  size="40" maxlength="30">
                              </div></td>
                            </tr>
                            <tr bordercolor="#333300" bgcolor="#DCE7DE">
                              <td><div align="left" class="style46">Senha</div></td>
                              <td colspan="3"><div align="center">
                                  <input name="senha" type="password" id="senha6" value= "<%= u.getSenha() %>" size="40" maxlength="20">
                              </div></td>
                            </tr>
                            <tr bordercolor="#333300" bgcolor="#DCE7DE">
                              <td><div align="left" class="style46">Confirmar Senha </div></td>
                              <td colspan="3"><div align="center">
                                  <input name="confirmarsenha" type="password" id="confirmarsenha2" value= "<%= u.getSenha() %>" size="40" maxlength="20">
                              </div></td>
                            </tr>
                            <tr bordercolor="#333300" bgcolor="#DCE7DE">
                              <td><div align="left" class="style46">Institui&ccedil;&atilde;o</div></td>
                              <td colspan="3"><div align="center">
                                <textarea name="instituicao" cols="30" rows="3" id="instituicao">"<%= u.getInstituicao() %>"</textarea>
                              </div></td>
                            </tr>
                            <tr bordercolor="#333300" bgcolor="#DCE7DE">
                              <td><div align="left" class="style46">&Aacute;rea de Interesse </div></td>
                              <td colspan="3"><div align="center">
                                <textarea name="areaInteresse" cols="30" rows="3" id="areainteresse">"<%= u.getAreaInteresse() %>"</textarea>
                              </div></td>
                            </tr>                                           
                              <tr bordercolor="#333300" bgcolor="#DCE7DE">
                              <td><div align="left" class="style46">Data de nascimento </div></td>
                              <td>
                                <div align="left">
                                <% String data = u.getDtNascimento(); 
                                String dia, mes, ano;
                                     
                                dia = data.substring(8,10); 
                                mes = data.substring(5,7);
                                ano = data.substring(0,4);  %>
                                                                                           
                                  <select name="dia" id="dia">
                                  <option selected value= <%= dia %>> <%=dia%></option>
                                  <option  value=01>01</option>
                                  <option  value=02>02</option>
                                  <option  value=03>03</option>
								<option  value=04>04</option>
								<option  value=05>05</option>
								<option  value=06>06</option>
								<option  value=07>07</option>
								<option  value=08>08</option>
								<option  value=09>09</option>
								<option  value=10>10</option>
								<option  value=11>11</option>
								<option  value=12>12</option>
								<option  value=13>13</option>
								<option  value=14>14</option>
								<option  value=15>15</option>
								<option  value=16>16</option>
								<option  value=17>17</option>
								<option  value=18>18</option>
								<option  value=19>19</option>
								<option  value=20>20</option>
								<option  value=21>21</option>
								<option  value=22>22</option>
								<option  value=23>23</option>
								<option  value=24>24</option>
								<option  value=25>25</option>
								<option  value=26>26</option>
								<option  value=27>27</option>
								<option  value=28>28</option>
								<option  value=29>29</option>
								<option  value=30>30</option>
								<option  value=31>31</option>						
                                  </select>
                                </div></td>
                              <td><select name="mes" id="mes">
                                <option selected value= <%= mes %>> <%=mes%></option>
								<option  value=01>01</option>
								<option  value=02>02</option>
								<option  value=03>03</option>
								<option  value=04>04</option>
								<option  value=05>05</option>
								<option  value=06>06</option>
								<option  value=07>07</option>
								<option  value=08>08</option>
								<option  value=09>09</option>
								<option  value=10>10</option>
								<option  value=11>11</option>
								<option  value=12>12</option>				
                              </select></td>
                              <td><select name="ano" id="ano">
									<option selected value= <%= ano %>> <%=ano%></option>
									<option  value=1940>1940</option>
									<option  value=1941>1941</option>
									<option  value=1942>1942</option>
									<option  value=1943>1943</option>
									<option  value=1944>1944</option>
									<option  value=1945>1945</option>
									<option  value=1946>1946</option>
									<option  value=1947>1947</option>
									<option  value=1948>1948</option>
									<option  value=1949>1949</option>
									<option  value=1950>1950</option>
									<option  value=1951>1951</option>
									<option  value=1952>1952</option>
									<option  value=1953>1953</option>
									<option  value=1954>1954</option>
									<option  value=1955>1955</option>
									<option  value=1956>1956</option>
									<option  value=1957>1957</option>
									<option  value=1958>1958</option>
									<option  value=1959>1959</option>
									<option  value=1960>1960</option>
									<option  value=1961>1961</option>
									<option  value=1962>1962</option>
									<option  value=1963>1963</option>
									<option  value=1964>1964</option>
									<option  value=1965>1965</option>
									<option  value=1966>1966</option>
									<option  value=1967>1967</option>
									<option  value=1968>1968</option>
									<option  value=1969>1969</option>
									<option  value=1970>1970</option>
									<option  value=1971>1971</option>
									<option  value=1972>1972</option>
									<option  value=1973>1973</option>
									<option  value=1974>1974</option>
									<option  value=1975>1975</option>
									<option  value=1976>1976</option>
									<option  value=1977>1977</option>
									<option  value=1978>1978</option>
									<option  value=1979>1979</option>
									<option  value=1980>1980</option>
									<option  value=1981>1981</option>
									<option  value=1982>1982</option>
									<option  value=1983>1983</option>
									<option  value=1984>1984</option>
									<option  value=1985>1985</option>
									<option  value=1986>1986</option>
									<option  value=1987>1987</option>
									<option  value=1988>1988</option>
									<option  value=1989>1989</option>
									<option  value=1990>1990</option>
									<option  value=1991>1991</option>
									<option  value=1992>1992</option>
									<option  value=1993>1993</option>
									<option  value=1994>1994</option>
									<option  value=1995>1995</option>
									<option  value=1996>1996</option>
									<option  value=1997>1997</option>
									<option  value=1998>1998</option>
									<option  value=1999>1999</option>
									<option  value=2000>2000</option>
									<option  value=2001>2001</option>
									<option  value=2002>2002</option>
									<option  value=2003>2003</option>
									<option  value=2004>2004</option>
									<option  value=2005>2005</option>
									<option  value=2006>2006</option>
									<option  value=2007>2007</option>
									<option  value=2008>2008</option>
									<option  value=2009>2009</option>
									<option  value=2010>2010</option>
									<option  value=2011>2011</option>
									<option  value=2012>2012</option>
									<option  value=2013>2013</option>
									<option  value=2014>2014</option>
									<option  value=2015>2015</option>
									<option  value=2016>2016</option>
									<option  value=2017>2017</option>
									<option  value=2018>2018</option>
									<option  value=2019>2019</option>
									<option  value=2020>2020</option>
									<option  value=2021>2021</option>
									<option  value=2022>2022</option>
									<option  value=2023>2023</option>
									<option  value=2024>2024</option>
									<option  value=2025>2025</option>
									<option  value=2026>2026</option>
									<option  value=2027>2027</option>
									<option  value=2028>2028</option>
									<option  value=2029>2029</option>
									<option  value=2030>2030</option>
									<option  value=2031>2031</option>
									<option  value=2032>2032</option>
									<option  value=2033>2033</option>
									<option  value=2034>2034</option>
									<option  value=2035>2035</option>
									<option  value=2036>2036</option>
									<option  value=2037>2037</option>
									<option  value=2038>2038</option>
									<option  value=2039>2039</option>
									<option  value=2040>2040</option>
									<option  value=2041>2041</option>
									<option  value=2042>2042</option>
									<option  value=2043>2043</option>
									<option  value=2044>2044</option>
									<option  value=2045>2045</option>
									<option  value=2046>2046</option>
									<option  value=2047>2047</option>
									<option  value=2048>2048</option>
									<option  value=2049>2049</option>
									<option  value=2050>2050</option>
									<option  value=2051>2051</option>
									<option  value=2052>2052</option>
									<option  value=2053>2053</option>
									<option  value=2054>2054</option>
									<option  value=2055>2055</option>
									<option  value=2056>2056</option>
									<option  value=2057>2057</option>
									<option  value=2058>2058</option>
									<option  value=2059>2059</option>
									<option  value=2060>2060</option>
									<option  value=2061>2061</option>
									<option  value=2062>2062</option>
									<option  value=2063>2063</option>
									<option  value=2064>2064</option>
									<option  value=2065>2065</option>
									<option  value=2066>2066</option>
									<option  value=2067>2067</option>
									<option  value=2068>2068</option>
									<option  value=2069>2069</option>
									<option  value=2070>2070</option>
									<option  value=2071>2071</option>
									<option  value=2072>2072</option>
									<option  value=2073>2073</option>
									<option  value=2074>2074</option>
									<option  value=2075>2075</option>
									<option  value=2076>2076</option>
									<option  value=2077>2077</option>
									<option  value=2078>2078</option>
									<option  value=2079>2079</option>
									<option  value=2080>2080</option>
									<option  value=2081>2081</option>
									<option  value=2082>2082</option>
									<option  value=2083>2083</option>
									<option  value=2084>2084</option>
									<option  value=2085>2085</option>
									<option  value=2086>2086</option>
									<option  value=2087>2087</option>
									<option  value=2088>2088</option>
									<option  value=2089>2089</option>
									<option  value=2090>2090</option>
									<option  value=2091>2091</option>
									<option  value=2092>2092</option>
									<option  value=2093>2093</option>
									<option  value=2094>2094</option>
									<option  value=2095>2095</option>
									<option  value=2096>2096</option>
									<option  value=2097>2097</option>
									<option  value=2098>2098</option>
									<option  value=2099>2099</option>
									<option  value=2100>2100</option>
                              </select></td>
                            </tr>
                            <tr bordercolor="#333300" bgcolor="#DCE7DE">
                              <td height="26"><div align="left" class="style46">Sexo</div></td>
                              <td colspan="3">
                                <div align="left">
                                    <select name="sexo" id="select4">
                                    <% if (u.getSexo().compareTo("M") == 0) {%>
                                    <option value="M">Masculino</option>
                                    <option value="F">Feminino</option>
                                    <% }
                                    else { %>
                                    <option value="F">Feminino</option>
                                    <option value="M">Masculino</option>
                                    <% } %>
                                    </select>
                                </div></td>
                            </tr>
                          </table>
                          <table width="400" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td colspan="2"><div align="center">
                                <h3>&nbsp;</h3>
                              </div></td>
                            </tr>
                            <tr>
                              <td width="140"><div align="center">
                                <input name="metodo" type="hidden" value="cadastroUsuario">
                                <input name="acao" type="hidden" value="alterar"> 
                                </div></td>
                              <td bgcolor="#333300">
                                <div align="right">
                                  <table width="100" height="30" border="0" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                    <tr>
                                      <td><div align="center">
                                          <input name="enviar" type="submit" id="enviar5" value="Enviar">
                                      </div></td>
                                    </tr>
                                  </table>
                                </div></td></tr>
                          </table>
                        </form>
                        </div></td>
                    </tr>
                    <tr>
                      <td height="16" valign="top" bgcolor="#DCE7DE"><div align="center"></div></td>
                    </tr>
                </table>
              </div>
                <div align="right"></div>
                <div align="right"></div></td>
              <td width="16%" height="331" valign="baseline" bordercolor="#333300">&nbsp;</td>
          </tr>
            <tr>
              <td height="24" valign="baseline" bordercolor="#333300">
                <div align="left"></div>                <table width="60" height="25" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td bgcolor="#333300"><div align="center" class="style49"><a href="menu.jsp">voltar</a></div></td>
                  </tr>
                                </table></td>
            </tr>
            <tr>
              <td height="16" valign="baseline" bordercolor="#333300">&nbsp;</td>
            </tr>
        </table>
      </div></td>
    <td height="560" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td height="16" valign="top">&nbsp;</td>
  </tr>
</table>
</body>
</html>
