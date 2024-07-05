/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package app.controller;

import app.dal.DAOBorrowCard;
import app.dal.DAOBorrowCardContent;
import app.dal.DAOUser;
import app.entity.BorrowCard;
import app.entity.BorrowCardContent;
import app.entity.User;
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
@WebServlet("/SetActiveCardServlet")
public class SetActiveCard extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DAOUser daoUser;
    private DAOBorrowCard daoBorrowCard;
    private DAOBorrowCardContent daoBorrowCardContent;

    public SetActiveCard() {
        daoUser = new DAOUser();
        daoBorrowCard = new DAOBorrowCard();
        daoBorrowCardContent = new DAOBorrowCardContent();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = daoUser.getUserById(userId);

        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<BorrowCard> borrowCards = daoBorrowCard.getBorrowCardsByUserId(userId, (page - 1) * recordsPerPage, recordsPerPage);
        for (BorrowCard card : borrowCards) {
            List<BorrowCardContent> contents = daoBorrowCardContent.getBorrowCardContentByBorrowCardId(card.getBorrowCardId());
            card.setBorrowCardContents(contents);
        }

        int noOfRecords = daoBorrowCard.getNoOfRecordsByUserId(userId);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("user", user);
        request.setAttribute("borrowCards", borrowCards);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        RequestDispatcher view = request.getRequestDispatcher("./admin/setactivecard.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int borrowCardId = Integer.parseInt(request.getParameter("borrowCardId"));

        daoUser.updateBorrowCardId(userId, borrowCardId);

        response.sendRedirect("BorrowCardServlet");
    }
}