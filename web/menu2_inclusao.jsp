<%@ page contentType="text/html; charset=iso-8859-1"  language="java" import="java.sql.*" import="cdp.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%  Modelo m = (Modelo)session.getAttribute("modelo"); 
    String executarModelo = "executarModelo2.jsp" + "?id=" + m.getIdModelo();
    String gerarArquivo = "controlador.Exporta" + "?metodo=gerarArquivo&id=" + m.getIdModelo();
    String visualizarArquivo = "gerarCodigo.jsp" + "?id=" + m.getIdModelo();

    Usuario u = (Usuario) session.getAttribute("usuario");
    String novaAnalise = "inserirAnaliseGrafica.jsp" + "?idModelo=" + m.getIdModelo() +
            "&idUsuario=" + u.getIdUsuario();
    String consultarAnalise = "controlador.Exporta" + "?metodo=consultarAnalises" +  //"consultarAnaliseGrafica.jsp" + 
            "&idModelo=" + m.getIdModelo() +
            "&idUsuario=" + u.getIdUsuario();
    String excluirAnalise = "controlador.Exporta" + "?metodo=excluirAnalises" + //"excluirAnaliseGrafica.jsp" + 
            "&idModelo=" + m.getIdModelo() +
            "&idUsuario=" + u.getIdUsuario();
%>

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
.style33 {	font-family: "Futura Md BT";
	font-size: 50px;
	color: #FFFFFF;
}
.style34 {color: #DCE7DE}
.style37 {color: #FFFFFF}
a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #DCE7DE;
}
a:visited {
	color: #DCE7DE;
	text-decoration: none;
}
a:hover {
	color: #A8C4AD;
	text-decoration: none;
}
a:active {
	color: #DCE7DE;
	text-decoration: none;
}
a:link {
	text-decoration: none;
}
.style39 {font-size: 16px}
.style41 {
	font-size: 12px;
	color: #D1D1A5;
}
.style44 {font-size: 20px}
h1,h2,h3,h4,h5,h6 {
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
}
h1 {
	font-size: 1mm;
}
h2 {
	font-size: 2mm;
}
.style5 {color: #FF0000}
.style45 {font-size: 12px}
-->
</style>
<script language="JavaScript">
<!--


function mmLoadMenus() {
  if (window.mm_menu_0510181915_0) return;
          window.mm_menu_0510181915_0 = new Menu("root",129,18,"Arial, Helvetica, sans-serif",12,"#DCE7DE","#DCE7DE","#333300","#557D5C","center","middle",3,0,1000,-5,7,true,true,true,0,true,true);
  mm_menu_0510181915_0.addMenuItem("Incluir&nbsp;Agente","window.open('incluiragente.htm', 'principal');");
  mm_menu_0510181915_0.addMenuItem("Consultar&nbsp;Agente","window.open('controlador.ControladorWeb?metodo=pesquisaAgente&acao=consulta', 'principal');");
  mm_menu_0510181915_0.addMenuItem("Alterar&nbsp;Agente","window.open('controlador.ControladorWeb?metodo=pesquisaAgente&acao=alteracao', 'principal');");
  mm_menu_0510181915_0.addMenuItem("Excluir&nbsp;Agente","window.open('controlador.ControladorWeb?metodo=pesquisaAgente&acao=exclusao', 'principal');");
  mm_menu_0510181915_0.addMenuItem("Inicializar&nbsp;Agente","window.open('inicializar_agente.jsp', 'principal');");
   mm_menu_0510181915_0.hideOnMouseOut=true;
   mm_menu_0510181915_0.bgColor='#DCE7DE';
   mm_menu_0510181915_0.menuBorder=1;
   mm_menu_0510181915_0.menuLiteBgColor='#FFFFFF';
   mm_menu_0510181915_0.menuBorderBgColor='#DCE7DE';
window.mm_menu_0510184332_0 = new Menu("root",136,18,"Arial, Helvetica, sans-serif",12,"#DCE7DE","#DCE7DE","#333300","#557D5C","center","middle",3,0,1000,-5,7,true,true,true,0,true,true);
  mm_menu_0510184332_0.addMenuItem("Incluir&nbsp;Vari�vel","window.open('incluirvariavel.htm', 'principal');");
  mm_menu_0510184332_0.addMenuItem("Consultar&nbsp;Vari�vel","window.open('controlador.ControladorWeb?metodo=pesquisaVariavel&acao=consulta', 'principal');");
  mm_menu_0510184332_0.addMenuItem("Alterar&nbsp;Vari�vel","window.open('controlador.ControladorWeb?metodo=pesquisaVariavel&acao=alteracao', 'principal');");
  mm_menu_0510184332_0.addMenuItem("Excluir&nbsp;Vari�vel","window.open('controlador.ControladorWeb?metodo=pesquisaVariavel&acao=exclusao', 'principal');");
   mm_menu_0510184332_0.hideOnMouseOut=true;
   mm_menu_0510184332_0.bgColor='#555555';
   mm_menu_0510184332_0.menuBorder=1;
   mm_menu_0510184332_0.menuLiteBgColor='#FFFFFF';
   mm_menu_0510184332_0.menuBorderBgColor='#777777';
window.mm_menu_0510184627_0 = new Menu("root",135,18,"Arial, Helvetica, sans-serif",12,"#DCE7DE","#DCE7DE","#333300","#557D5C","center","middle",3,0,1000,-5,7,true,true,true,0,true,true);
  mm_menu_0510184627_0.addMenuItem("Incluir&nbsp;Monitor","window.open('incluirmonitor.jsp', 'principal');");
  mm_menu_0510184627_0.addMenuItem("Consultar&nbsp;Monitor","window.open('controlador.ControladorWeb?metodo=pesquisaMonitor&acao=consulta', 'principal');");
  mm_menu_0510184627_0.addMenuItem("Alterar&nbsp;Monitor","window.open('controlador.ControladorWeb?metodo=pesquisaMonitor&acao=alteracao', 'principal');");
  mm_menu_0510184627_0.addMenuItem("Excluir&nbsp;Monitor","window.open('controlador.ControladorWeb?metodo=pesquisaMonitor&acao=exclusao', 'principal');");
   mm_menu_0510184627_0.hideOnMouseOut=true;
   mm_menu_0510184627_0.bgColor='#555555';
   mm_menu_0510184627_0.menuBorder=1;
   mm_menu_0510184627_0.menuLiteBgColor='#FFFFFF';
   mm_menu_0510184627_0.menuBorderBgColor='#777777';
        window.mm_menu_0510185111_0 = new Menu("root",131,18,"Arial, Helvetica, sans-serif",12,"#DCE7DE","#DCE7DE","#333300","#557D5C","center","middle",3,0,1000,-5,7,true,true,true,0,true,true);
  mm_menu_0510185111_0.addMenuItem("Incluir&nbsp;Gr�fico","window.open('incluirgrafico.jsp', 'principal');");
  mm_menu_0510185111_0.addMenuItem("Consultar&nbsp;Gr�fico","window.open('controlador.ControladorWeb?metodo=pesquisaPlot&acao=consulta', 'principal');");
  mm_menu_0510185111_0.addMenuItem("Alterar&nbsp;Gr�fico","window.open('controlador.ControladorWeb?metodo=pesquisaPlot&acao=alteracao', 'principal');");
  mm_menu_0510185111_0.addMenuItem("Excluir&nbsp;Gr�fico","window.open('controlador.ControladorWeb?metodo=pesquisaPlot&acao=exclusao', 'principal');");
   mm_menu_0510185111_0.hideOnMouseOut=true;
   mm_menu_0510185111_0.bgColor='#555555';
   mm_menu_0510185111_0.menuBorder=1;
   mm_menu_0510185111_0.menuLiteBgColor='#FFFFFF';
   mm_menu_0510185111_0.menuBorderBgColor='#777777';
  window.mm_menu_0510185230_0 = new Menu("root",121,18,"Arial, Helvetica, sans-serif",12,"#DCE7DE","#DCE7DE","#333300","#557D5C","center","middle",3,0,1000,-5,7,true,true,true,0,true,true);
  mm_menu_0510185230_0.addMenuItem("Incluir&nbsp;Texto","window.open('incluirtexto.jsp', 'principal');");
  mm_menu_0510185230_0.addMenuItem("Consultar&nbsp;Texto","window.open('controlador.ControladorWeb?metodo=pesquisaTexto&acao=consulta', 'principal');");
  mm_menu_0510185230_0.addMenuItem("Alterar&nbsp;Texto","window.open('controlador.ControladorWeb?metodo=pesquisaTexto&acao=alteracao', 'principal');");
  mm_menu_0510185230_0.addMenuItem("Excluir&nbsp;Texto","window.open('controlador.ControladorWeb?metodo=pesquisaTexto&acao=exclusao', 'principal');");
   mm_menu_0510185230_0.hideOnMouseOut=true;
   mm_menu_0510185230_0.bgColor='#555555';
   mm_menu_0510185230_0.menuBorder=1;
   mm_menu_0510185230_0.menuLiteBgColor='#FFFFFF';
   mm_menu_0510185230_0.menuBorderBgColor='#777777';
window.mm_menu_0510185825_0 = new Menu("root",163,18,"Arial, Helvetica, sans-serif",12,"#DCE7DE","#DCE7DE","#333300","#557D5C","center","middle",3,0,1000,-5,7,true,true,true,0,true,true);
  mm_menu_0510185825_0.addMenuItem("Incluir&nbsp;Composi��o","window.open('incluircompantes.jsp', 'principal');");
  mm_menu_0510185825_0.addMenuItem("Consultar&nbsp;Composi��o","window.open('controlador.ControladorWebCCA?metodo=pesquisaComp&acao=consulta', 'principal');");
  mm_menu_0510185825_0.addMenuItem("Alterar&nbsp;Composi��o","window.open('controlador.ControladorWebCCA?metodo=pesquisaComp&acao=alteracao', 'principal');");
  mm_menu_0510185825_0.addMenuItem("Excluir&nbsp;Composi��o","window.open('controlador.ControladorWebCCA?metodo=pesquisaComp&acao=exclusao', 'principal');");
   mm_menu_0510185825_0.hideOnMouseOut=true;
   mm_menu_0510185825_0.bgColor='#555555';
   mm_menu_0510185825_0.menuBorder=1;
   mm_menu_0510185825_0.menuLiteBgColor='#FFFFFF';
   mm_menu_0510185825_0.menuBorderBgColor='#777777';
//--------   
window.mm_menu_0510185826_0 = new Menu("root",163,18,"Arial, Helvetica, sans-serif",12,"#DCE7DE","#DCE7DE","#333300","#557D5C","center","middle",3,0,1000,-5,7,true,true,true,0,true,true);
  mm_menu_0510185826_0.addMenuItem("Incluir&nbsp;Bot�o","window.open('incluirbotao.jsp', 'principal');");
  mm_menu_0510185826_0.addMenuItem("Consultar&nbsp;Bot�o","window.open('controlador.ControladorWebCCA?metodo=pesquisaBotao&acao=consulta', 'principal');");
  mm_menu_0510185826_0.addMenuItem("Alterar&nbsp;Bot�o","window.open('controlador.ControladorWebCCA?metodo=pesquisaBotao&acao=alteracao', 'principal');");
  mm_menu_0510185826_0.addMenuItem("Excluir&nbsp;Bot�o","window.open('controlador.ControladorWebCCA?metodo=pesquisaBotao&acao=exclusao', 'principal');");
   mm_menu_0510185826_0.hideOnMouseOut=true;
   mm_menu_0510185826_0.bgColor='#555555';
   mm_menu_0510185826_0.menuBorder=1;
   mm_menu_0510185826_0.menuLiteBgColor='#FFFFFF';
   mm_menu_0510185826_0.menuBorderBgColor='#777777';
//-------------   
   window.mm_menu_0814171259_1 = new Menu("root",137,18,"Arial, Helvetica, sans-serif",12,"#DCE7DE","#DCE7DE","#333300","#557D5C","center","middle",3,0,1000,-5,7,true,true,true,0,true,true);
  mm_menu_0814171259_1.addMenuItem("Executar Modelo", "window.open('<%= executarModelo %>', '_blank');");
  mm_menu_0814171259_1.addMenuItem("Gerar Arquivo", "window.open('<%= gerarArquivo %>', '_self');");
  mm_menu_0814171259_1.addMenuItem("Visualizar Arquivo", "window.open('<%= visualizarArquivo %>', '_blank');");
   mm_menu_0814171259_1.hideOnMouseOut=true;
   mm_menu_0814171259_1.bgColor='#555555';
   mm_menu_0814171259_1.menuBorder=1;
   mm_menu_0814171259_1.menuLiteBgColor='#FFFFFF';
   mm_menu_0814171259_1.menuBorderBgColor='#777777';
   
                       window.mm_menu_1127204303_0 = new Menu("root",136,18,"Arial, Helvetica, sans-serif",12,"#DCE7DE","#DCE7DE","#333300","#557D5C","center","middle",3,0,1000,-5,7,true,true,true,0,true,true);
  mm_menu_1127204303_0.addMenuItem("Incluir&nbsp;Analise","window.open('<%= novaAnalise %>', 'principal');");
  mm_menu_1127204303_0.addMenuItem("Consultar&nbsp;Analises","window.open('<%= consultarAnalise %>', 'principal');");
  mm_menu_1127204303_0.addMenuItem("Excluir&nbsp;Analises","window.open('<%= excluirAnalise %>', 'principal');");
   mm_menu_1127204303_0.hideOnMouseOut=true;
   mm_menu_1127204303_0.bgColor='#555555';
   mm_menu_1127204303_0.menuBorder=1;
   mm_menu_1127204303_0.menuLiteBgColor='#FFFFFF';
   mm_menu_1127204303_0.menuBorderBgColor='#777777';
mm_menu_0510185825_0.writeMenus();
} // mmLoadMenus()
//-->
</script>
<script language="JavaScript" src="mm_menu.js"></script>
</head>

<body>
<script language="JavaScript1.2">mmLoadMenus();</script>
<table width="100%" height="290" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#333300">
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top"><div align="center">
        <table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="227"><div align="center" class="style33">NET<span class="style37">PLAY</span></div></td>
            <td width="515"> %>&nbsp;</td>
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
          <table width="100%" height="293" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
              <tr>
                <td width="183" height="290" rowspan="4" valign="top"><div align="center">
                    <table width="183" height="140%" border="0" cellpadding="0" cellspacing="0" bordercolor="#333300" bgcolor="#BBD0BF">
                      <tr>
                        <td valign="top"><table width="180" height="229" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                              <td width="180" height="229" valign="top"><table width="180" height="100%" border="0" cellpadding="0" cellspacing="0">
                                  <tr>
                                    <td height="32" rowspan="4">&nbsp;</td>
                                    <td height="10">&nbsp;</td>
                                    <td rowspan="4">&nbsp;</td>
                                  </tr>
                                  <tr>
                                    <td height="30"><span class="style44"><u>NOVO MODELO</u></span></td>
                                  </tr>
                                  <tr>
                                    <td height="10"><span class="style5">Nome: <%= m.getNome()%></span></td>
                                  </tr>
                                  <tr>
                                    <td height="10">&nbsp;</td>
                                  </tr>
                                  <tr>
                                    <td width="10" height="229">&nbsp;</td>
                                    <td width="160" valign="top"><table width="160" height="210" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
                                        <tr>
                                          <td width="150" height="30"><div align="center"><span class="style34"><a href="incluirmodelo.jsp" name="link9" target="principal" id="link1" onMouseOver="MM_showMenu(window.mm_menu_0510181915_0,134,-5,null,'link9')" onMouseOut="MM_startTimeout();">Cadastrar Agente</a></span></div></td>
                                        </tr>
                                        <tr>
                                          <td width="150" height="30"><div align="center"><span class="style34"><a href="incluirmodelo.jsp" name="link4" target="principal" id="link3" onMouseOver="MM_showMenu(window.mm_menu_0510184332_0,136,-5,null,'link4')" onMouseOut="MM_startTimeout();">Cadastrar Vari&aacute;vel</a></span></div></td>
                                        </tr>
                                        <tr>
                                          <td width="150" height="30"><div align="center"><span class="style34"><a href="incluirmodelo.jsp" name="link12" target="principal" id="link5" onMouseOver="MM_showMenu(window.mm_menu_0510184627_0,130,-5,null,'link12')" onMouseOut="MM_startTimeout();">Cadastrar Monitor</a></span></div></td>
                                        </tr>
                                        <tr>
                                          <td width="150" height="30"><div align="center"><span class="style34"><a href="incluirmodelo.jsp" name="link13" target="principal" id="link6" onMouseOver="MM_showMenu(window.mm_menu_0510185111_0,124,-5,null,'link13')" onMouseOut="MM_startTimeout();">Cadastrar Gr&aacute;fico</a></span></div></td>
                                        </tr>
                                        <tr>
                                          <td width="150" height="30"><div align="center"><span class="style34"><a href="incluirmodelo.jsp" name="link10" target="principal" id="link7" onMouseOver="MM_showMenu(window.mm_menu_0510185230_0,132,0,null,'link10')" onMouseOut="MM_startTimeout();">&nbsp;Cadastrar Texto</a></span></div></td>
                                        </tr>
                                        <tr>
                                          <td width="150" height="30"><div align="center"><span class="style34"><a href="incluirmodelo.jsp" name="link14" target="principal" id="link11" onMouseOver="MM_showMenu(window.mm_menu_0510185825_0,150,-5,null,'link14')" onMouseOut="MM_startTimeout();">Cadastrar Composi&ccedil;&atilde;o</a></span></div></td>
                                        </tr>
                                        <tr>
                                          <td width="150" height="30"><div align="center"><span class="style34"><a href="incluirmodelo.jsp" name="link141" target="principal" id="link11" onMouseOver="MM_showMenu(window.mm_menu_0510185826_0,150,-5,null,'link14')" onMouseOut="MM_startTimeout();">Cadastrar Bot&atilde;o</a></span></div></td>
                                        </tr>
                                        <tr>
                                          <td width="150" height="30"><div align="center"><span class="style34"><a href="incluirmodelo.jsp" name="link18" target="principal" id="link20" onMouseOver="MM_showMenu(window.mm_menu_0814171259_1,130,-5,null,'link18')" onMouseOut="MM_startTimeout();">Exportar Modelo</a></span></div></td>
                                        </tr>
                                        <tr>
                                          <td width="150" height="30"><div align="center"><span class="style34"><a href="incluirmodelo.jsp" name="link15" target="principal" id="link8" onMouseOver="MM_showMenu(window.mm_menu_1127204303_0,130,-5,null,'link15')" onMouseOut="MM_startTimeout();">Analisar Gr&aacute;ficos</a></span></div></td>
					</tr>
                                    </table></td>
                                    <td width="10">&nbsp;</td>
                                  </tr>
                              </table></td>
                            </tr>
                        </table></td>
                        <td width="3" valign="top" bgcolor="#333300">&nbsp;</td>
                      </tr>
                  </table>
                </div></td>
                <td height="14" align="center" valign="middle">&nbsp;</td>
              </tr>
            <tr>
                <td height="450" align="center" valign="middle"><iframe align="center" src="incluirmodelo.jsp" name="principal" width="900" height="450" scrolling="yes" frameborder="0"></iframe>
&nbsp;</td>
            </tr>
            <tr>
              <td height="5" align="center" valign="middle"><div align="center">
                <table width="570" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <th width="150" scope="row">&nbsp;</th>
                    <td width="310"><div align="center"><span class="style5"><%= status %></span></div></td>
                    <td width="110"><table width="80" height="25" border="0" align="left" cellpadding="0" cellspacing="0" bgcolor="#333300">
                      <tr>
                        <td><div align="center" class="style34"> </div>
                            <div align="center" class="style34"><a href="incluirmodelo.jsp" target="principal">Voltar</a></div></td>
                      </tr>
                    </table></td>
                  </tr>
                </table>
              </div></td>
            </tr>
            <tr>
              <td height="14" align="center" valign="middle">&nbsp;</td>
            </tr>
          </table>
      </div></td>
    <td height="378" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td height="16" valign="top">&nbsp;</td>
  </tr>
</table>
</body>
</html>
