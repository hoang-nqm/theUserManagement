/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hnmq.controller;

import hnqm.users.CreateUserErrorDTO;
import hnqm.users.UserDAO;
import hnqm.users.UserDTO;
import hnqm.utils.EncryptSHA256;
import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Minh Hoang
 */
@MultipartConfig
public class InsertController extends HttpServlet {

    private static final String ERROR = "insert.jsp";
    private static final String SUCCESS = "GetAllController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        CreateUserErrorDTO err = new CreateUserErrorDTO();
        UserDAO userDAO = new UserDAO();

        try {
            String userId = request.getParameter("txtUserId");
            String fullName = request.getParameter("txtFullName");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            Part part = request.getPart("file");
            String fileName = "";
            if (part.getSize() != 0) {
                fileName = part.getSubmittedFileName();
                for (Part filePart : request.getParts()) {
                    filePart.write("C:\\Users\\Minh Hoang\\Desktop\\TheUserManagement\\web\\image\\" + fileName);
                }
            }
            String phone = request.getParameter("txtPhone");
            boolean check = true;

            if (userDAO.checkEmail(userId)) {
                err.setUserIDError("UserID with this mail already existed !");
                check = false;
            } else {
                if (userId.isEmpty()) {
                    err.setUserIDError("UserID not empty");
                    check = false;
                } else {
                    String regex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(userId);
                    if (!matcher.find()) {
                        err.setUserIDError("UserID as mail");
                        check = false;
                    }
                }
            }
            if (fullName.isEmpty()) {
                err.setFullNameError("Fullname not empty");
                check = false;
            }
            if (password.isEmpty()) {
                err.setPasswordError("Pasword not empty");
                check = false;
            }
            if (password.length() < 6 || password.length() > 11) {
                err.setPasswordError("Password length from 6 to 11");
                check = false;
            }
            if (!confirm.equals(password)) {
                err.setConfirmError("Confirm not match password");
                check = false;
            }
            String regexPhone = "(03|09[2|6|8|9])+([0-9]{8})\\b";
            Pattern pattern = Pattern.compile(regexPhone);
            Matcher matcher = pattern.matcher(phone);
            if (!matcher.find()) {
                err.setPhoneError("Phease input phone number valid format [0356449764] Length[10] ");
                check = false;
            }
            if (check) {
                UserDTO user = new UserDTO(userId, fullName, EncryptSHA256.toHexString(EncryptSHA256.getSHA(password)), "2", fileName, "Active", phone, "Enable");
                userDAO.createUser(user);
                request.setAttribute("SUCCESS", "Register successully");
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR_CREATE", err);
                UserDTO user = new UserDTO(userId, fullName, password, phone, fileName);
                request.setAttribute("confirm", confirm);
                request.setAttribute("USER", user);
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
