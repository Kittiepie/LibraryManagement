/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package app.controller;

import app.dal.DAOBook;
import app.dal.DAOBorrowCard;
import app.dal.DAOBorrowCardContent;
import app.entity.Book;
import app.entity.BorrowCard;
import app.entity.BorrowCardContent;
import app.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OwO
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Book> cart = (List<Book>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        session.setAttribute("cart", cart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        List<Book> cart = (List<Book>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        int userId = (int) session.getAttribute("userId");
        DAOBorrowCard borrowCardDAO = new DAOBorrowCard();
        DAOBorrowCardContent borrowCardContentDAO = new DAOBorrowCardContent();

        // Get the active borrow card for the user
        BorrowCard activeBorrowCard = borrowCardDAO.getActiveBorrowCardByUserId(userId);

        if ("add".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            Book book = new DAOBook().getBookById(bookId); // Assuming DAOBook has a method to get a book by ID
            cart.add(book);
        } else if ("remove".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            cart.removeIf(book -> book.getBookId() == bookId);
        } else if (activeBorrowCard == null) {
            // Set error message and forward to cart page
            request.setAttribute("errorMessage", "It seems like you haven't had yet an active borrow card. Please make sure to inform a staff to make your own.");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
            return;
        } else if ("borrowAll".equals(action)) {
            for (Book book : cart) {
                BorrowCardContent content = new BorrowCardContent();
                content.setBorrowCardId(activeBorrowCard.getBorrowCardId());
                content.setBookId(book.getBookId());
                content.setBorrowDate(new java.util.Date());
                content.setReturnDate(new java.util.Date(content.getBorrowDate().getTime() + 7 * 24 * 60 * 60 * 1000)); // +7 days
                borrowCardContentDAO.addBorrowCardContent(content);
            }
            cart.clear();
        } else if ("borrowSingle".equals(action)) {
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            Book bookToBorrow = null;
            for (Book book : cart) {
                if (book.getBookId() == bookId) {
                    bookToBorrow = book;
                    break;
                }
            }
            if (bookToBorrow != null) {
                BorrowCardContent content = new BorrowCardContent();
                content.setBorrowCardId(activeBorrowCard.getBorrowCardId());
                content.setBookId(bookToBorrow.getBookId());
                content.setBorrowDate(new java.util.Date());
                content.setReturnDate(new java.util.Date(content.getBorrowDate().getTime() + 7 * 24 * 60 * 60 * 1000)); // +7 days
                borrowCardContentDAO.addBorrowCardContent(content);
                cart.remove(bookToBorrow);
            }
        }
        session.setAttribute("cart", cart);
        response.sendRedirect("CartServlet");
    }
}
