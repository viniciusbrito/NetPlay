<%@ page contentType="text/html; charset=iso-8859-1"  language="java" import="java.sql.*" import="cdp.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>NetPlay</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
.style34 {color: #DCE7DE}
body {
	background-color: #DCE7DE;
}
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
.style5 {color: #FF0000}
-->
</style>
</head>

<body>
<div align="center">
  <p>&nbsp;</p>
  <table width="440" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th colspan="4" scope="row"><table width="440" border="1">
        <tr>
          <th scope="row"><table width="440" border="0" cellpadding="0" cellspacing="0" bgcolor="#BBD0BF">
            <tr>
              <td><div align="center">
                    <table width="400" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td height="10"><div align="center"> </div></td>
                      </tr>
                      <tr>
                        <td><table width="440" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td width="50"><% Modelo m = (Modelo)session.getAttribute("modelo");%></td>
                              <td width="340"><div align="center">
                                  <table width="340" border="1" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
                                    <tr>
                                      <td width="120"><div align="left">Nome</div></td>
                                      <td width="220"><input name="nome" disabled  type="text" id="nome2" value="<%=m.getNome()%>" size="40"></td>
                                    </tr>
                                    <tr>
                                      <td><div align="left">Descri&ccedil;&atilde;o</div></td>
                                      <td>
                                        <div align="left">
                                          <textarea name="descricao" disabled cols="30" rows="3" id="descricao"><%= m.getDescricao() %></textarea>
                                      </div></td>
                                    </tr>
                                    <tr>
                                      <td height="10" colspan="2"><div align="center">
                                      </div></td>
                                    </tr>
                                  </table>
                              </div></td>
                              <td width="50">&nbsp;                              </td>
                            </tr>
                        </table></td>
                      </tr>
                    </table>
              </div></td>
            </tr>
            <tr>
              <td height="10">&nbsp;</td>
            </tr>
            <tr>
              </tr>
   		    <tr>
              <td height="10"><div align="center"> </div></td>
            </tr>
          </table></th>
        </tr>
      </table></th>
    </tr>
    <tr>
      <th colspan="4" scope="row">&nbsp;</th>
    </tr>
    <tr>
      <th width="240" scope="row"><table width="240" height="30" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
        <tr>
          <% String gerarCodigo = "gerarCodigo.jsp"+
                                               "?id="+
                                               m.getIdModelo(); %>
                        <td width="120"><div align="center" class="style34"><a href=<%=gerarCodigo%> target="_blank">Visualizar Arquivo</a> </div></td>
						
						<% String gerarArquivo = "controlador.Exporta"+
                                               "?metodo=gerarArquivo&id=" 
                                               + m.getIdModelo(); %>
                        <td width="120"><div align="center" class="style34"><a href=<%=gerarArquivo%>>Gerar Arquivo </a></div></td>
        </tr>
      </table></th>
      <th width="50" scope="row">&nbsp;</th>
      <th width="20" scope="row"><div align="right">
        </div></th>
      <th width="140" scope="row"><table width="140" height="30" border="1" cellpadding="0" cellspacing="0" bordercolor="#DCE7DE" bgcolor="#333300">
        <tr>
          <td><div align="center"><a href="menu.jsp" target="_parent">Voltar Menu Principal </a></div></td>
        </tr>
      </table></th>
    </tr>
  </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</div>
</body>
</html>
