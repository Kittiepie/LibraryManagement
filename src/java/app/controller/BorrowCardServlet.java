/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package app.controller;

import app.dal.DAOBorrowCard;
import app.dal.DAOUser;
import app.entity.BorrowCard;
import app.entity.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author OwO
 */

@WebServlet("/BorrowCardServlet")
public class BorrowCardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAOUser daoUser;
    private DAOBorrowCard daoBorrowCard;

    public BorrowCardServlet() {
        daoUser = new DAOUser();
        daoBorrowCard = new DAOBorrowCard();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<User> list = daoUser.getUsers((page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = daoUser.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("userList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        RequestDispatcher view = request.getRequestDispatcher("./admin/borrowcard.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        // Create a new BorrowCard object
        BorrowCard borrowCard = new BorrowCard();
        borrowCard.setUserId(userId);
        borrowCard.setCreateDate(new Date(System.currentTimeMillis()));

        // Add the new borrow card to the database
        daoBorrowCard.addBorrowCard(borrowCard);

        // Redirect back to the borrow card list page
        response.sendRedirect("BorrowCardServlet");
    }
}