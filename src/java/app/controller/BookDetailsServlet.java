/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package app.controller;

import app.dal.DAOBook;
import app.entity.Book;
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
@WebServlet("/BookDetailsServlet")
public class BookDetailsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOBook daoBook = new DAOBook();
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String view = request.getParameter("view");
        Book book = daoBook.getBookById(bookId);

        request.setAttribute("book", book);
        
        if ("admin".equals(view)) {
            request.getRequestDispatcher("./admin/bookDetailsforAdmin.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "BookDetailsServlet";
    }
}
