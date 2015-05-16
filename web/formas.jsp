<%@ page contentType="text/html; charset=iso-8859-1"  language="java" import="java.sql.*" errorPage="" %>
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
	color: #333300;
	font-weight: bold;
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
body {
	background-color: #DCE7DE;
}
.style1 {font-size: 14px}
.style5 {color: #FF0000}
.style2 {color: #DCE7DE}
-->
</style>
</head>

<body>
<div align="center">
  <table width="220" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td><div align="center" class="style1">LISTA DE FORMAS</div></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><div align="center">
        <table width="220" height="380" border="1" bgcolor="#BBD0BF">
          <tr>
            <td><div align="center">
              <table width="150" border="1" cellspacing="0" cellpadding="0">
                <tr>
                  <td><div align="center">AVIAO</div></td>
                  <td><img src="formas/airplane_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td width="104"><div align="center">SETA</div></td>
                  <td width="40"><img src="formas/arrow_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">CUBO</div></td>
                  <td height="10"><img src="formas/box_1.gif" width="40" height="38"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">INSETO</div></td>
                  <td height="10"><img src="formas/bug_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">BORBOLETA</div></td>
                  <td height="10"><img src="formas/buterfly_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">CARRO</div></td>
                  <td height="10"><img src="formas/car_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">CIRCULO 1 </div></td>
                  <td height="10"><img src="formas/circle_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">CIRCULO 2</div></td>
                  <td height="10"><img src="formas/circle2_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">VACA</div></td>
                  <td height="10"><img src="formas/cow_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">CILINDRO 3D</div></td>
                  <td height="10"><img src="formas/cylinder3D_1.gif" width="40" height="43"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">CILINDRO</div></td>
                  <td height="10"><img src="formas/cylinder_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">SETA PADRAO</div></td>
                  <td height="10"><img src="formas/default_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">PONTO</div></td>
                  <td height="10"><img src="formas/dot_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">FACE FELIZ</div></td>
                  <td height="10"><img src="formas/faceHappy_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">FACE NEUTRA</div></td>
                  <td height="10"><img src="formas/faceNeutral_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">FACE TRISTE</div></td>
                  <td height="10"><img src="formas/faceSad_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">PEIXE</div></td>
                  <td height="10"><img src="formas/fish_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">BANDEIRA</div></td>
                  <td height="10"><img src="formas/flag_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">FLOR</div></td>
                  <td height="10"><img src="formas/flower_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">CASA</div></td>
                  <td height="10"><img src="formas/house_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">FOLHA</div></td>
                  <td height="10"><img src="formas/leaf_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">LINHA </div></td>
                  <td height="10"><img src="formas/line_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">LINHA PEQUENA </div></td>
                  <td height="10"><img src="formas/lineHalf_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">PENTAGONO</div></td>
                  <td height="10"><img src="formas/pentagon_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">PESSOA</div></td>
                  <td height="10"><img src="formas/person_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">PLANTA</div></td>
                  <td height="10"><img src="formas/plant_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">QUADRADO 1 </div></td>
                  <td height="10"><img src="formas/square_1.gif" width="40" height="38"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">QUADRADO 2 </div></td>
                  <td height="10"><img src="formas/square2_1.gif" width="40" height="38"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">ESTRELA</div></td>
                  <td height="10"><img src="formas/star_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">ALVO</div></td>
                  <td height="10"><img src="formas/target_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">ARVORE</div></td>
                  <td height="10"><img src="formas/tree_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">TRIANGULO 1 </div></td>
                  <td height="10"><img src="formas/triangle_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">TRIANGULO 2 </div></td>
                  <td height="10"><img src="formas/triangle2_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">CAMINHAO</div></td>
                  <td height="10"><img src="formas/truck_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">TARTARUGA</div></td>
                  <td height="10"><img src="formas/turtle_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">RODA</div></td>
                  <td height="10"><img src="formas/wheel_1.gif" width="40" height="38"></td>
                </tr>
                <tr>
                  <td height="10"><div align="center">X</div></td>
                  <td height="10"><img src="formas/x_1.gif" width="40" height="37"></td>
                </tr>
                <tr>
                  <td height="10" colspan="2">&nbsp;</td>
                  </tr>
                <tr>
                  <td height="10" colspan="2"><div align="center">
                    <input name="Fechar" type="submit" id="Fechar" value="Fechar" onClick="javascript:window.close();">
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
</body>
</html>
