<%@ page contentType="text/html; charset=ISO-8859-1" language="java" 
import="java.sql.*"
import="java.net.*"
errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%

    String nome = request.getParameter("nome");
    String sobrenome = request.getParameter("sobrenome");
    String email = request.getParameter("email");
    String login = request.getParameter("login");
    String instituicao = request.getParameter("instituicao");
    String areaInteresse = request.getParameter("areaInteresse");
    String dataNasc = request.getParameter("dataNasc");
    String sexo = request.getParameter("sexo");

    String status = "";

    status = request.getParameter("status");
    if (status == null) {
        status = "";
        nome = "";
        sobrenome = "";
        email = "";
        login = "";
        instituicao = "";
        areaInteresse = "";
        dataNasc = "";
        sexo = "";
    }
%>
<html lang="pt">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">

        <title>NetPlay</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body style="padding-top: 40px;">

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="./">NetPlay!</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-file"></span> Produções <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#pd1">Produção 1</a></li>
                                <li><a href="#pd2">Produção 2</a></li>
                                <li><a href="#pd3">Produção 3</a></li>
                                <li><a href="#pd4">Produção 3</a></li>
                            </ul>
                        </li>
                        <li><a href="#"><span class="glyphicon glyphicon-envelope"></span> Contato</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-download-alt"></span><!--<img src="imagens/GitHub-Mark-Light-32px.png" alt="Github">--> Github</a></li>
                    </ul>

                    <form class="navbar-form navbar-right" action="controlador.ControladorWeb" method="post" name="loginsenha" id="loginsenha">
                        <div class="form-group">
                            <input name="login" type="text" placeholder="Login" class="form-control" required pattern=".{3,}">
                        </div>
                        <div class="form-group">
                            <input name="senha" type="password" placeholder="Senha" class="form-control" required pattern=".{6,}">
                        </div>
                        <button type="submit" class="btn btn-success">Entrar</button>
                        <a class="btn btn-info" href="cadastro.jsp" role="button">Cadastrar</a>
                        <!-- hiddens -->
                        <input name="metodo" type="hidden" value = "logar">
                    </form>
                </div><!--/.navbar-collapse -->
            </div>
        </nav>

        
        <% if(!status.equals("") || status == null) { %>
            <div class="alert alert-danger text-center" role="alert"><%= status %></div>     
        <% } %>
        
        <div class="container">
            <h1>Cadastro de Usu&aacute;rio</h1>
            <form class="form-horizontal" action="controlador.ControladorWeb" method="post" name="cadastrousuario" id="cadastrousuario">

                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <div class="input-group">
                                <label for="inputNome">Nome</label>
                                <input name="nome" type="text" class="form-control" id="inputNome" autofocus required pattern="[a-zA-ZãõÃÕáéíóúÁÉÍÓÚâêîôûÂÊÎÔÛçÇ\s]+$" value="<%= nome %>">
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="form-group">
                            <div class="input-group">
                                <label for="inputSobrenome">Sobrenome</label>
                                <input name="sobrenome" type="text" class="form-control" id="inputSobrenome" required pattern="[a-zA-ZãõÃÕáéíóúÁÉÍÓÚâêîôûÂÊÎÔÛçÇ\s]+$" value="<%= sobrenome %>">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <div class="input-group">
                                <label for="inputEmail">Email</label>
                                <input name="email" type="email" class="form-control" id="inputEmail" placeholder="exemplo@exemplo.com" required pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$" value="<%= email %>">
                            </div>            
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="form-group">
                            <div class="input-group">
                                <label for="inputLogin">Login</label>
                                <input name="login" type="text" class="form-control" id="inputLogin" required pattern="[a-zA-Z0-9\s].{3,15}" value="<%= login %>">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <div class="input-group">
                                <label for="inputSenha">Senha</label>
                                <input name="senha" type="password" class="form-control" id="inputSenha" placeholder="Minimo 6 caracteres!" required  pattern=".{6,}">
                            </div>            
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="form-group">
                            <div class="input-group">
                                <label for="inputCSenha">Confirmar Senha</label>
                                <input name="confirmarsenha" type="password" class="form-control" id="inputCSenha" placeholder="Repita a senha!" required pattern=".{6,}">
                            </div>            
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <div class="input-group">
                                <label for="inputInst">Institui&ccedil;&atilde;o</label>
                                <input name="instituicao" type="text" class="form-control" id="inputInst" required pattern=".{3,}" value="<%= instituicao %>">
                            </div>            
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="form-group">
                            <div class="input-group">
                                <label for="inputArea">&Aacute;rea de Interesse</label>
                                <input name="areaInteresse" type="text" class="form-control" id="inputArea" required pattern=".{3,}" value="<%= areaInteresse %>">
                            </div>            
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <div class="input-group">
                                <label for="inputNasc">Data de Nascimento</label>
                                <input name="dataNasc" type="date" class="form-control" id="inputNasc" required  value="<%= dataNasc %>">
                            </div>            
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="form-group">
                            <div class="input-group">
                                <label for="selectSexo">Sexo</label>
                                <select name="sexo" class="form-control" id="selectSexo" required>
                                    <option value="">Selecionar</option>
                                    <option value="F" <%= (sexo.equals("F")) ? "selected": "" %> >Femenino</option>
                                    <option value="M" <%= (sexo.equals("M")) ? "selected": "" %> >Masculino</option>
                                </select>
                            </div>            
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="form-group">
                        <input type="submit" class="btn btn-info" value="Cadastre-se">
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="form-group">
                        <a href="index.jsp" type="button" class="btn btn-xs btn-link class-right">Voltar</a>
                    </div>
                </div>
        <!-- hiddens -->
                <div class="col-md-3">
                    <div class="form-group">
                        <input name="metodo" type="hidden" value="cadastroUsuario">
                        <input name="acao" type="hidden" value="incluir"> 
                    </div>
                </div>
            </form>
        </div><!-- container -->

        <hr>
        <footer>
            <p>&copy; NetPlay 2007 - 2015</p>
        </footer>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
