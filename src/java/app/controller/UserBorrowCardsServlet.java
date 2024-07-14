/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package app.controller;

import app.dal.DAOBorrowCard;
import app.entity.BorrowCard;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author OwO
 */
@WebServlet("/UserBorrowCardsServlet")
public class UserBorrowCardsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAOBorrowCard daoBorrowCard;

    public UserBorrowCardsServlet() {
        daoBorrowCard = new DAOBorrowCard();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        // Assuming the userId is stored in the session
        int userId = (int) request.getSession().getAttribute("userId");

        List<BorrowCard> borrowCards = daoBorrowCard.getBorrowCardsByUserId(userId, (page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = daoBorrowCard.getNoOfRecordsByUserId(userId);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        // Get the active borrow card
        BorrowCard activeBorrowCard = daoBorrowCard.getActiveBorrowCardByUserId(userId);
        int activeBorrowCardId = activeBorrowCard != null ? activeBorrowCard.getBorrowCardId() : -1;

        request.setAttribute("borrowCardList", borrowCards);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("activeBorrowCardId", activeBorrowCardId);

        RequestDispatcher view = request.getRequestDispatcher("userborrowcard.jsp");
        view.forward(request, response);
    }
}
