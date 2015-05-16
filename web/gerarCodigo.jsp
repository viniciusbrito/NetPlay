<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Código em NetLogo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
<!--
body,td,th {
font-size: 11px;
color: #333300;
font-family: Arial, Helvetica, sans-serif;
font-weight: bold;
}
body {
	background-color: #FFFFFF;
}
a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #333300;
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
	color: #DCE7DE;
	font-weight: bold;
}
h1 {
	font-size: 0px;
}
h2 {
	font-size: 1px;
}
h3 {
	font-size: 2mm;
}
.style33 {
	font-family: "Futura Md BT";
	font-size: 50px;
	color: #FFFFFF;
}
.style37 {
	color: #FFFFFF
}
.style42 {
	font-size: 20px
}
.style34 {
	color: #DCE7DE
}
.style5 {
	color: #FF0000
}
-->
</style>
</head>

<body>
	<table width="100%" height="386" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#333300">
		<tr>
			<td valign="top">&nbsp;</td>
			<td valign="top">
				<div align="center">
					<table width="100%" height="50" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="227">
								<div align="center" class="style33">NET<span class="style37">PLAY</span>
								</div>
							</td>
							<td width="515">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<table width="100%" height="5" border="0" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
										<tr>
											<td>&nbsp;</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div align="center">
					<table width="750" border="0" cellpadding="0" cellspacing="0" bgcolor="#333300">
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>
					<table width="100%" height="304" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#DCE7DE">
						<tr>
							<td width="100%" height="304" valign="middle">
								<div align="center">
									<table width="80%" height="80%" border="2" bgcolor="#BBD0BF">
										<tr>
											<td>
                                                                                                <% String id = request.getParameter("id");
                                                                                                   String gerarCodigo = "controlador.Exporta"+
                                                                                                        "?metodo=gerarCodigo&id="+
                                                                                                        id; %>
                                                                                                <iframe src=<%= gerarCodigo %>  width="100%" height="240" scrolling="auto">
												</iframe>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
			<td height="369" valign="top">&nbsp;</td>
		</tr>
		<tr>
			<td valign="top">&nbsp;</td>
			<td valign="top">&nbsp;</td>
			<td height="16" valign="top">&nbsp;</td>
		</tr>
	</table>
</body>

</html>