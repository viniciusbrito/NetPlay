<%-- 
    Document   : login
    Created on : 05/05/2015, 08:39:23
    Author     : vinicius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String status = "";
    String msg = "";

    status = request.getParameter("status");
    if (status == null) {
        status = "";
    }
    
    msg = request.getParameter("msg");
    if (msg == null) {
        msg = "danger";
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

    <title>NetPlay -- Login</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
  </head>

  <body>      
    <% if(!status.equals("")) { %>
            <div class="alert alert-<%= msg %> text-center" role="<%= msg %>"><%= status %></div>
    <% } %>
    <div class="container">

      <form class="form-signin" action="controlador.ControladorWeb" method="post" name="loginsenha" id="loginsenha" onSubmit="MM_validateForm('login', '', 'R', 'senha', '', 'R'); return document.MM_returnValue">
        <h2 class="form-signin-heading">Realizar Login</h2>
        <label for="inputEmail" class="sr-only">Login</label>
        <input name="login" type="text" class="form-control" placeholder="Login" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="senha" type="password" class="form-control" placeholder="Senha" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Lembrar me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
        <a class="btn btn-lg btn-info btn-block" href="cadastro.jsp" role="button">Cadastrar</a>
        <!-- hiddens -->
        <input name="metodo" type="hidden" value = "logar">
      </form>

    </div> <!-- /container -->
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

