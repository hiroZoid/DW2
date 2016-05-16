<%-- 
    Document   : index
    Created on : May 12, 2016, 9:43:27 PM
    Author     : hiroshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projeto Clínica</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Projeto Clínica</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Início</a></li>
                        <li><a href="listarAmbulatorios.jsp">Ambulatórios</a></li>
                        <li><a href="listarFuncionarios.jsp">Funcionários</a></li>
                        <li><a href="listarMedicos.jsp">Médicos</a></li>
                        <li><a href="listarPacientes.jsp">Pacientes</a></li>
                        <li><a href="listarConsultas.jsp">Consultas</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>

        <div class="container">

            <!-- Main component for a primary marketing message or call to action -->
            <!-- Carousel
                ================================================== -->
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img class="first-slide" src="img/1.jpg" alt="First slide">
                    </div>
                    <div class="item">
                        <img class="second-slide" src="img/2.jpg" alt="Second slide">
                    </div>
                    <div class="item">
                        <img class="third-slide" src="img/3.jpg" alt="Third slide">
                    </div>
                </div>
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div><!-- /.carousel -->            

        </div> <!-- /container -->
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
