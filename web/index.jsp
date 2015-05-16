<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
    String status = "";

    status = request.getParameter("status");
    if (status == null) {
        status = " ";
    }
%>

<html lang="pt">
    <head>
        <meta charset="utf-8">
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

    <body>

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

                <form class="navbar-form navbar-right" action="controlador.ControladorWeb" method="post" name="loginsenha" id="loginsenha" onSubmit="MM_validateForm('login', '', 'R', 'senha', '', 'R'); return document.MM_returnValue">
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

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
        <div class="container">
            <h1>Bem Vindo ao NetPlay!</h1>
            <p>O estudo de sistemas complexos utilizando a tecnologia de sistemas multiagente
                tem crescido e permitido que pesquisadores e estudantes tenham onde criar e simular
                seu modelos. Porém as ferramentas mais abrangentes para criação de modelos baseado
                em agentes requer do modelador conhecimento de alguma linguagem de programação.
                O NetPlay surgiu como uma alternativa a essa necessidade, ou seja, é uma ferramenta
                que possibilita a criação de modelos dinâmicos e de sistemas complexos, subsidiados
                pela tecnologia multiagente e não exigindo do usuário o conhecimento de linguagem de
                programação textual, pois adota o paradigma da linguagem visual e uma nova metáfora
                para a criação dos modelos.</p>
            <p><a class="btn btn-primary btn-lg" href="#" role="button">Leia mais &raquo;</a></p>
        </div>
    </div>

    <div class="container">
        <!-- Example row of columns -->
        <div class="row">
            <div class="col-md-4">
                <h2>Publicações</h2>
                <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                <p><a class="btn btn-default" href="#" role="button">Veja Detalhes &raquo;</a></p>
            </div>
        </div>

        <hr>

        <footer>
            <p>&copy; NetPlay 2015</p>
        </footer>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
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
        if (p<1 || p==(val.length-1)) errors+='- '+nm+' must contain an e-mail address.\n';
        } else if (test!='R') { num = parseFloat(val);
        if (isNaN(val)) errors+='- '+nm+' must contain a number.\n';
        if (test.indexOf('inRange') != -1) { p=test.indexOf(':');
        min=test.substring(8,p); max=test.substring(p+1);
        if (num<min || max<num) errors+='- '+nm+' must contain a number between '+min+' and '+max+'.\n';
        } } } else if (test.charAt(0) == 'R') errors += '- '+nm+ ' obrigatorio.\n'; }
        } if (errors) alert(''+errors);
        document.MM_returnValue = (errors == '');
        }
        //-->
    </script>
</body>
</html>
