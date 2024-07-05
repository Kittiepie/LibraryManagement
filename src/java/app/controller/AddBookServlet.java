/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package app.controller;

import app.dal.DAOBook;
import app.dal.DAOCategory;
import app.entity.Book;
import app.entity.Category;
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
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {

    private DAOBook daoBook = new DAOBook();
    private DAOCategory daoCategory = new DAOCategory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = daoCategory.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("./admin/addBook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookTitle = request.getParameter("bookTitle");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        boolean bookStatus = Boolean.parseBoolean(request.getParameter("bookStatus"));
        boolean isFeatured = Boolean.parseBoolean(request.getParameter("isFeatured"));
        
        String bookCoverBase64 = request.getParameter("bookCoverBase64");
        String briefInfo = request.getParameter("briefInfo");
        String description = request.getParameter("description");

        Book book = new Book(0, bookTitle, categoryId, bookStatus, isFeatured, bookCoverBase64, briefInfo, description, null);
        daoBook.addBook(book);
        response.sendRedirect("BooksServlet?view=admin");
    }

    @Override
    public String getServletInfo() {
        return "AddBookServlet";
    }
}
