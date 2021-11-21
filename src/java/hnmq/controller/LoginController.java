/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnmq.controller;

import hnqm.role.RoleDAO;
import hnqm.role.RoleDTO;
import hnqm.users.UserDAO;
import hnqm.users.UserDTO;
import hnqm.utils.EncryptSHA256;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Minh Hoang
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String USER = "user.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String ERROR = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String err = "";

        try {

            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            UserDAO userDAO = new UserDAO();
            RoleDAO roleDAO = new RoleDAO();
            boolean checkEmail = userDAO.checkEmail(email);
            HttpSession session = request.getSession();
            String pas = EncryptSHA256.toHexString(EncryptSHA256.getSHA(password));
            if (email.isEmpty() || password.isEmpty()) {
                err = "Email and Password must not empty";
                request.setAttribute("ERROR_LOGIN", err);
            } else {
                if (checkEmail) {
                    UserDTO user = userDAO.checkLogin(email, EncryptSHA256.toHexString(EncryptSHA256.getSHA(password)));
                    if (user != null) {
                        if (user.getRoleID().equals("2")) {
                            if (user.getStatus().equalsIgnoreCase("Active")) {
                                url = USER;
                                UserDTO user1 = userDAO.getUserByID(email);
                                request.setAttribute("USER", user1);
                            }
                        } else if (user.getRoleID().equals("1")) {
                            List<UserDTO> listUser = userDAO.getAllUser();
                            List<RoleDTO> listRole = roleDAO.getAllRole();
                            request.setAttribute("LIST_ROLE", listRole);
                            request.setAttribute("LIST_USER", listUser);
                            url = ADMIN;
                        }
                        Cookie cookie = new Cookie(email, password);
                        cookie.setMaxAge(60 * 60 * 24 * 7);
                        response.addCookie(cookie);
                        session.setAttribute("LOGIN_USER", user);
                    } else {

                        err = "Wrong password";
                        request.setAttribute("EMAIL", email);
                        request.setAttribute("ERROR", err);

                    }

                } else {
                    err = "Not found account";
                    request.setAttribute("ERROR", err);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
