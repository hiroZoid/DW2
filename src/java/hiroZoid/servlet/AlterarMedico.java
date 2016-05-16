/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiroZoid.servlet;

import hiroZoid.dao.MedicoDAO;
import hiroZoid.entity.Medico;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hiroshi
 */
@WebServlet(name = "alterarMedico", urlPatterns = {"/alterarMedico"})
public class AlterarMedico extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(request.getRequestURL().toString());

        Medico medico = new Medico();
        medico.setCodigo(Integer.parseInt(request.getParameter("codigo")));
        if (Helper.INSTANCE.isSet(request.getParameter("nome"))) {
            medico.setNome(request.getParameter("nome"));
        }
        if (Helper.INSTANCE.isSet(request.getParameter("idade"))) {
            medico.setIdade(Integer.parseInt(request.getParameter("idade")));
        }
        if (Helper.INSTANCE.isSet(request.getParameter("especialidade"))) {
            medico.setEspecialidade(request.getParameter("especialidade"));
        }
        if (Helper.INSTANCE.isSet(request.getParameter("cidade"))) {
            medico.setCidade(request.getParameter("cidade"));
        }
        if (Helper.INSTANCE.isSet(request.getParameter("estado"))) {
            medico.setEstado(request.getParameter("estado"));
        }
        if (Helper.INSTANCE.isSet(request.getParameter("nroAmbulatorio"))) {
            medico.setNroAmbulatorio(Integer.parseInt(request.getParameter("nroAmbulatorio")));
        }

        MedicoDAO medicoDAO = new MedicoDAO();
        if (medicoDAO.alterar(medico) == 0) {
            medicoDAO.inserir(medico);
        }

        response.sendRedirect("listarMedicos.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
