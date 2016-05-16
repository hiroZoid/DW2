<%-- 
    Document   : listarConsultas
    Created on : May 12, 2016, 9:44:40 PM
    Author     : hiroshi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="consultaDao" class="hiroZoid.dao.ConsultaDAO" />
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
                        <li class="active"><a href="listarConsultas.jsp">Consultas</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>

        <div class="container">
            <div class="jumbotron">
                <h1>
                    Consultas
                    <a href="javascript:openNewForm()" data-toggle="tooltip" title="Adicionar">
                        <span class="glyphicon glyphicon-new-window"></span>
                    </a>
                </h1>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Data</th>
                            <th>Paciente</th>
                            <th>Médico</th>
                            <th>Ambulatório</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="tuple" items="${consultaDao.listarConsultas()}">
                            <c:set var="referenciado" value="${false}"/>
                            <c:set var="tooltip" value="${referenciado ? 'Não é possível excluir, esse objeto foi referenciado!' : 'Remover'}"/>
                            <c:set var="medico" value="${medicoDao.buscar(tuple.codigoMedico)}"/>
                            <c:set var="paciente" value="${pacienteDao.buscar(tuple.codigoPaciente)}"/>
                            <tr>
                                <td>${tuple.data}</td>
                                <td>${paciente.nome}</td>
                                <td>${medico.nome}</td>
                                <td>${medico.nroAmbulatorio}</td>
                                <td>
                                    <button data-toggle="tooltip" title="Editar" 
                                            onclick="javascript:openEditForm({
                                                        pkCodigoMedico: ${tuple.codigoMedico},
                                                        pkCodigoPaciente: ${tuple.codigoPaciente},
                                                        pkData: '${tuple.data}',
                                                        codigoMedico: ${tuple.codigoMedico},
                                                        codigoPaciente: ${tuple.codigoPaciente},
                                                        data: '${tuple.data}',
                                                    })">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </button>
                                    <form action="deletarConsulta" style="display: inline">
                                        <input type="hidden" name="codigoMedico" value="${tuple.codigoMedico}"/>
                                        <input type="hidden" name="codigoPaciente" value="${tuple.codigoPaciente}"/>
                                        <input type="hidden" name="data" value="${tuple.data}"/>
                                        <button ${referenciado?'disabled':''} data-toggle="tooltip" title="${tooltip}">
                                            <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div> <!-- /container -->

        <!-- Modal -->
        <div id="editEntityModalWindow" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title"></h4>
                    </div>
                    <form action="alterarConsulta" method="GET" onsubmit="return canParseDate()">
                        <input type="hidden" name="pkCodigoMedico"/>
                        <input type="hidden" name="pkCodigoPaciente"/>
                        <input type="hidden" name="pkData"/>
                        <div class="modal-body">
                            <fieldset class="form-group">
                                <label>Data</label>
                                <input type="date" name="data" class="form-control" required pattern="^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$" title="Entre a data no formato yyyy-mm-dd"
                                       placeholder="Entre a data"/>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Medico</label>
                                <select class="form-control" name="codigoMedico" required placeholder="Escolha o médico">
                                    <c:forEach var="tupla" items="${medicoDao.listarMedicos()}">
                                        <option value="${tupla.codigo}">${tupla.nome} / ${tupla.idade} anos / ${tupla.especialidade} / ${tupla.cidade}-${tupla.estado} / Ambulatório ${tupla.nroAmbulatorio}</option>
                                    </c:forEach>
                                </select>
                            </fieldset>                        
                            <fieldset class="form-group">
                                <label>Paciente</label>
                                <select class="form-control" name="codigoPaciente" required placeholder="Escolha o paciente">
                                    <c:forEach var="tupla" items="${pacienteDao.listarPacientes()}">
                                        <option value="${tupla.codigoPaciente}">${tupla.nome} / ${tupla.idade} anos / ${tupla.cidade} / ${tupla.plano}</option>
                                    </c:forEach>
                                </select>
                            </fieldset>                        
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-default"/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>

        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <script src="js/saude.js" type="text/javascript"></script>
    </body>
</html>