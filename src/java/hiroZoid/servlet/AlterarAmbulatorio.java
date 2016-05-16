/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiroZoid.servlet;

import hiroZoid.dao.AmbulatorioDAO;
import hiroZoid.entity.Ambulatorio;
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
@WebServlet(name = "alterarAmbulatorio", urlPatterns = {"/alterarAmbulatorio"})
public class AlterarAmbulatorio extends HttpServlet {

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

        Ambulatorio ambulatorio = new Ambulatorio();
        ambulatorio.setNroAmbulatorio(Integer.parseInt(request.getParameter("nroAmbulatorio")));
        if (Helper.INSTANCE.isSet(request.getParameter("andar"))) {
            ambulatorio.setAndar(Integer.parseInt(request.getParameter("andar")));
        }
        if (Helper.INSTANCE.isSet(request.getParameter("capacidade"))) {
            ambulatorio.setCapacidade(Integer.parseInt(request.getParameter("capacidade")));
        }

        AmbulatorioDAO ambulatorioDAO = new AmbulatorioDAO();
        if (ambulatorioDAO.alterarAmbulatorio(ambulatorio) == 0) {
            ambulatorioDAO.incluirAmbulatorio(ambulatorio);
        }

        response.sendRedirect("listarAmbulatorios.jsp");
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
