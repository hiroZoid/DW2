<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="medicoDao" class="hiroZoid.dao.MedicoDAO" />
<jsp:useBean id="pacienteDao" class="hiroZoid.dao.PacienteDAO" />
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
                        <li><a href="index.jsp">Início</a></li>
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
            <c:set var="medico" value="${medicoDao.buscar(param.codigoMedico)}"/>
            <c:set var="paciente" value="${pacienteDao.buscar(param.codigoPaciente)}"/>
            <div class="jumbotron">
                <h1>Consulta já marcada!</h1>
                <p>Data: ${param.data}</p>
                <p>Médico: ${medico.nome} / ${medico.idade} anos / ${medico.especialidade} / ${medico.cidade}-${medico.estado} / Ambulatório ${medico.nroAmbulatorio}</p>
                <p>Paciente: ${paciente.nome} / ${paciente.idade} anos / ${paciente.cidade} / ${paciente.plano}</p>
                <p>
                    <a class="btn btn-lg btn-primary" href="listarConsultas.jsp" role="button">Voltar para consultas &raquo;</a>
                </p>
            </div>

        </div> <!-- /container -->
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
