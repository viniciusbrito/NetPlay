<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String nome = "";
String cor = "";
nome = request.getParameter("nome");
cor = request.getParameter("cor");

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
	color: #333300;
	font-weight: bold;
}
body {
	background-color: #DCE7DE;
}
.style1 {color: #DCE7DE}
.style5 {color: #FF0000}
a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #DCE7DE;
	font-weight: bold;
}
a:visited {
	color: #DCE7DE;
	text-decoration: none;
}
a:active {
	color: #DCE7DE;
	text-decoration: none;
}
a:link {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
	color: #A8C4AD;
}
.style38 {font-size: 14px}
.style40 {color: #DCE7DE; font-size: 11px; }
.style41 {font-size: 11px}
-->
</style>
</head>
<body>
<div align="center">
  <table width="525" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="15"><table width="525" height="20" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td height="20"><div align="center">
                </div>              <div align="center"></div></td>
          </tr>
      </table></td>
    </tr>
    <tr>
      <td height="15">&nbsp;</td>
    </tr>
    <tr>
      <td height="15"><div align="center">
          <table width="525" border="1" bgcolor="#BBD0BF">
            <tr>
              <td><div align="center">
                <table width="430" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td>
                        <table width="430" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td height="20"><div align="center">POSICIONAMENTO DO AGENTE NA TELA </div></td>
                          </tr>
                          <tr>
                            <td><div align="center">
                              <table width="430" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                <tr>
                                  <td><div align="left"> </div>
                                    <div align="left">&nbsp;
<form action="controlador.ControladorWeb" method="post" name="formulario" target="principal" id="formulario">
<table width="267" border="1" align="center" cellpadding="0" cellspacing="0">
                                            <tr>
                                              <th width="21" scope="row">&nbsp;</th>
                                              <td width="20">
                                                <div align="center">-5 </div></td>
                                              <td width="20"><div align="center">-4</div></td>
                                              <td width="20"><div align="center">-3</div></td>
                                              <td width="20"><div align="center">-2 </div></td>
                                              <td width="20"><div align="center">-1</div></td>
                                              <td width="20"><div align="center">0 </div></td>
                                              <td width="20"><div align="center">1</div></td>
                                              <td width="20"><div align="center">2</div></td>
                                              <td width="20"><div align="center">3</div></td>
                                              <td width="20"><div align="center">4</div></td>
                                              <td width="20"><div align="center">5</div></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"><div align="center">5</div></th>
                                              <td><input type="checkbox" name="checkbox0000" value="ON"></td>
											  <td><input type="checkbox" name="checkbox0001" value="ON"></td>
                                              <td><input type="checkbox" name="checkbox3" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox4" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox5" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox6" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox7" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox8" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox9" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox10" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox11" value="checkbox"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"><div align="center">4</div></th>
                                              <td><input type="checkbox" name="checkbox12" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox13" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox14" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox15" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox16" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox17" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox18" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox19" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox20" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox21" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox22" value="checkbox"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"><div align="center">3</div></th>
                                              <td><input type="checkbox" name="checkbox23" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox24" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox25" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox26" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox27" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox28" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox29" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox30" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox31" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox32" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox33" value="checkbox"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"><div align="center">2</div></th>
                                              <td><input type="checkbox" name="checkbox34" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox35" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox36" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox37" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox38" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox39" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox40" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox41" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox42" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox43" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox44" value="checkbox"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"><div align="center">1</div></th>
                                              <td><input type="checkbox" name="checkbox45" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox46" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox47" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox48" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox49" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox50" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox51" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox52" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox53" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox54" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox55" value="checkbox"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"><div align="center">0</div></th>
                                              <td><input type="checkbox" name="checkbox6" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox332" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox333" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox334" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox335" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox336" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox337" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox338" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox339" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox340" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox341" value="checkbox"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"><div align="center">-1</div></th>
                                              <td><input type="checkbox" name="checkbox7" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox342" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox343" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox344" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox345" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox346" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox347" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox348" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox349" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox350" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox351" value="checkbox"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"><div align="center">-2</div></th>
                                              <td><input type="checkbox" name="checkbox8" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox361" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox360" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox359" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox358" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox357" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox356" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox355" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox353" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox354" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox352" value="checkbox"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"><div align="center">-3</div></th>
                                              <td><input type="checkbox" name="checkbox9" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox362" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox365" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox368" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox371" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox374" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox375" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox376" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox385" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox377" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox378" value="checkbox"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"><div align="center">-4</div></th>
                                              <td><input type="checkbox" name="checkbox10" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox363" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox366" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox369" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox372" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox386" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox387" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox382" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox381" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox380" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox379" value="checkbox"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"><div align="center">-5</div></th>
                                              <td><input type="checkbox" name="checkbox11" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox364" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox367" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox370" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox373" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox388" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox384" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox389" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox383" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox390" value="checkbox"></td>
                                              <td><input type="checkbox" name="checkbox1010" value="checkbox"></td>
                                            </tr>
                                          </table>
                                        <div align="center">
                                          <input name="nome" type="hidden" id="nome" value="<%=nome%>">
                                          <input name="metodo" type="hidden" value="posicaoAgente">
                                          <input name="confirmar" type="submit" id="confirmar2" value="Confirmar Posicionamento">
                                          <input name="cor" type="hidden" id="cor" value="<%=cor%>">
                                        </div>
</form>
                                      </div>                                    
                                    <div align="left">
                                    </div>
                                    <div align="left"></div>
                                    <div align="left">                                  </div></td>
                                  </tr>
                                <tr>
                                  <td><div align="center">
                                    </div></td>
                                </tr>
                              </table>
                            </div></td>
                          </tr>
                      </table>                  </td>
                  </tr>
                  <tr>
                    <td width="430" height="10"><div align="right">
                        </div></td>
                  </tr>
                </table>
                  </div></td>
            </tr>
          </table>
      </div></td>
    </tr>
  </table>
</div>
<div align="center"></div>
</body>
</html>
