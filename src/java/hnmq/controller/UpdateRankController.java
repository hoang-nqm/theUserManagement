/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnmq.controller;

import hnqm.users.CreateUserErrorDTO;
import hnqm.users.UserDAO;
import hnqm.users.UserDTO;
import hqnm.promotion.PromotionDAO;
import hqnm.promotion.PromotionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Minh Hoang
 */
@WebServlet(name = "UpdateRankController", urlPatterns = {"/UpdateRankController"})
public class UpdateRankController extends HttpServlet {

    private static final String SUCCESS = "promotionList.jsp";
    private static final String ERROR = "promotionList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = ERROR;
            UserDAO userDAO = new UserDAO();
            PromotionDAO promotionDAO = new PromotionDAO();
            CreateUserErrorDTO err = new CreateUserErrorDTO();
            String userID = request.getParameter("userID");
            String rank = request.getParameter("rank");
            int rankInt = Integer.parseInt(rank);
            try {
                boolean check = true;
                if (rankInt < 1 || rankInt > 10) {
                    err.setRankError("Rank must be between 1 and 10");
                    check = false;
                }
                if (check) {
                    boolean rankCheck = promotionDAO.updateRank(userID, rankInt);
                    if (rankCheck) {
                        List<UserDTO> listUser = userDAO.getAllUser();
                        List<PromotionDTO> listPromotion = promotionDAO.getAllPromotion();
                        request.setAttribute("PROMOTION", listPromotion);
                        request.setAttribute("LIST_USER", listUser);
                        url = SUCCESS;
                    }

                } else {
                    request.setAttribute("ERROR_CREATE", err);
                    List<UserDTO> listUser = userDAO.getAllUser();
                    List<PromotionDTO> listPromotion = promotionDAO.getAllPromotion();
                    request.setAttribute("PROMOTION", listPromotion);
                    request.setAttribute("LIST_USER", listUser);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }

        }
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
