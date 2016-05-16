<%-- 
    Document   : listarPacientes
    Created on : May 12, 2016, 9:45:08 PM
    Author     : hiroshi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="hiroZoid.dao.PacienteDAO" />
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
                        <li class="active"><a href="listarPacientes.jsp">Pacientes</a></li>
                        <li><a href="listarConsultas.jsp">Consultas</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>

        <div class="container">
            <div class="jumbotron">
                <h1>
                    Pacientes
                    <a href="javascript:openNewForm()" data-toggle="tooltip" title="Adicionar">
                        <span class="glyphicon glyphicon-new-window"></span>
                    </a>
                </h1>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Nome</th>
                            <th>Idade</th>
                            <th>Cidade</th>
                            <th>Plano</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="tuple" items="${dao.listarPacientes()}">
                            <c:set var="regexp" value="${regexp}${regexp==null?'':'|'}^0*${tuple.codigoPaciente}$"/>
                            <c:set var="referenciado" value="${dao.referencias(tuple.codigoPaciente) > 0}"/>
                            <c:set var="tooltip" value="${referenciado ? 'Não é possível excluir, esse objeto foi referenciado!' : 'Remover'}"/>
                            <tr>
                                <td>${tuple.codigoPaciente}</td>
                                <td>${tuple.nome}</td>
                                <td>${tuple.idade}</td>
                                <td>${tuple.cidade}</td>
                                <td>${tuple.plano}</td>
                                <td>
                                    <button data-toggle="tooltip" title="Editar" 
                                            onclick="javascript:openEditForm({
                                                        codigoPaciente: ${tuple.codigoPaciente},
                                                        nome: '${tuple.nome}',
                                                        idade: ${tuple.idade},
                                                        cidade: '${tuple.cidade}',
                                                        plano: '${tuple.plano}',
                                                    }, 'codigoPaciente')">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </button>
                                    <form action="deletarPaciente" style="display: inline">
                                        <input type="hidden" name="codigoPaciente" value="${tuple.codigoPaciente}"/>
                                        <button ${referenciado?'disabled':''} data-toggle="tooltip" title="${tooltip}">
                                            <span class="glyphicon glyphicon-remove"></span>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:set var="regexp" value="[0-9]*^(?!(${regexp}))[0-9]*$"/>
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
                    <form action="alterarPaciente" method="GET">
                        <div class="modal-body">
                            <fieldset class="form-group">
                                <label>Paciente #</label>
                                <input type="text" name="codigoPaciente" required class="form-control" 
                                       placeholder="Entre o número do paciente" pattern="${regexp}"
                                       title="Chave primária já cadastrada! Escolha outra"/>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Nome</label>
                                <input type="text" name="nome" class="form-control" 
                                       placeholder="Entre o nome"/>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Idade</label>
                                <input type="number" name="idade" min="0" class="form-control" 
                                       placeholder="Entre a idade"/>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Cidade</label>
                                <input type="text" name="cidade" class="form-control" 
                                       placeholder="Entre a cidade"/>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Plano de saúde</label>
                                <input type="text" name="plano" class="form-control" 
                                       placeholder="Entre o plano de saúde"/>
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
