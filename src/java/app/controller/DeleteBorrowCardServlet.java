/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package app.controller;

import app.dal.DAOBorrowCard;
import app.dal.DAOBorrowCardContent;
import app.dal.DAOUser;
import app.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author OwO
 */
@WebServlet("/DeleteBorrowCardServlet")
public class DeleteBorrowCardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAOBorrowCardContent daoBorrowCardContent;
    private DAOBorrowCard daoBorrowCard;
    private DAOUser daoUser;

    public DeleteBorrowCardServlet() {
        daoBorrowCardContent = new DAOBorrowCardContent();
        daoBorrowCard = new DAOBorrowCard();
        daoUser = new DAOUser();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int borrowCardId = Integer.parseInt(request.getParameter("borrowCardId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        daoBorrowCardContent.deleteBorrowCard(borrowCardId);
        daoBorrowCard.deleteBorrowCard(borrowCardId);

        // Check if the deleted borrow card was the active borrow card and update the user if necessary
        User user = daoUser.getUserById(userId);
        if (user.getBorrowcardId() != null && user.getBorrowcardId().equals(borrowCardId)) {
            daoUser.updateBorrowCardId(userId, 0);
        }

        response.sendRedirect("SetActiveCardServlet?userId=" + userId);
    }
}
