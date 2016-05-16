<%-- 
    Document   : listarMedicos
    Created on : May 12, 2016, 9:44:58 PM
    Author     : hiroshi
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="medicoDAO" class="hiroZoid.dao.MedicoDAO" />
<jsp:useBean id="ambulatorioDAO" class="hiroZoid.dao.AmbulatorioDAO" />
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
                        <li class="active"><a href="listarMedicos.jsp">Médicos</a></li>
                        <li><a href="listarPacientes.jsp">Pacientes</a></li>
                        <li><a href="listarConsultas.jsp">Consultas</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>

        <div class="container">
            <div class="jumbotron">
                <h1>
                    Médicos
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
                            <th>Especialidade</th>
                            <th>Cidade</th>
                            <th>Estado</th>
                            <th>Ambulatório</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="tuple" items="${medicoDAO.listarMedicos()}">
                            <c:set var="regexp" value="${regexp}${regexp==null?'':'|'}^0*${tuple.codigo}$"/>
                            <c:set var="referenciado" value="${medicoDAO.referencias(tuple.codigo) > 0}"/>
                            <c:set var="tooltip" value="${referenciado ? 'Não é possível excluir, esse objeto foi referenciado!' : 'Remover'}"/>
                            <tr>
                                <td>${tuple.codigo}</td>
                                <td>${tuple.nome}</td>
                                <td>${tuple.idade}</td>
                                <td>${tuple.especialidade}</td>
                                <td>${tuple.cidade}</td>
                                <td>${tuple.estado}</td>
                                <td>${tuple.nroAmbulatorio}</td>
                                <td>
                                    <button data-toggle="tooltip" title="Editar" 
                                            onclick="javascript:openEditForm({
                                                        codigo: ${tuple.codigo},
                                                        nome: '${tuple.nome}',
                                                        idade: ${tuple.idade},
                                                        especialidade: '${tuple.especialidade}',
                                                        cidade: '${tuple.cidade}',
                                                        estado: '${tuple.estado}',
                                                        nroAmbulatorio: '${tuple.nroAmbulatorio}'
                                                    }, 'codigo')">
                                        <span class="glyphicon glyphicon-edit"></span>
                                    </button>
                                    <form action="deletarMedico" style="display: inline">
                                        <input type="hidden" name="codigo" value="${tuple.codigo}"/>
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
                    <form action="alterarMedico" method="GET">
                        <div class="modal-body">
                            <fieldset class="form-group">
                                <label>Medico #</label>
                                <input type="text" name="codigo" required class="form-control" 
                                       placeholder="Entre o número do médico" pattern="${regexp}"
                                       title="Chave primária já cadastrada! Escolha outra"/>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Nome</label>
                                <input type="text" name="nome" class="form-control" 
                                       placeholder="Entre o nome"/>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Idade</label>
                                <input type="number" name="idade" min="0" step="1" class="form-control" 
                                       placeholder="Entre a idade"/>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Especialidade</label>
                                <input type="text" name="especialidade" class="form-control" 
                                       placeholder="Entre a especialidade"/>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Cidade</label>
                                <input type="text" name="cidade" class="form-control" 
                                       placeholder="Entre a cidade"/>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Estado</label>
                                <input type="text" name="estado" class="form-control" 
                                       placeholder="Entre o estado"/>
                            </fieldset>
                            <fieldset class="form-group">
                                <label>Ambulatório #</label>
                                <select class="form-control" name="nroAmbulatorio" required>
                                    <c:forEach var="tupla" items="${ambulatorioDAO.listarAmbulatorios()}">
                                        <option value="${tupla.nroAmbulatorio}">${tupla.nroAmbulatorio}</option>
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
