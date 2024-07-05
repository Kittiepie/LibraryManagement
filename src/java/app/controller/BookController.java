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
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OwO
 */
@WebServlet("/BooksServlet")
public class BookController extends HttpServlet {

    private static final int BOOKS_PER_PAGE = 3;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOBook daoBook = new DAOBook();
        DAOCategory daoCategory = new DAOCategory();

        String search = request.getParameter("search");
        String[] categoryIds = request.getParameterValues("category");
        String view = request.getParameter("view");

        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        List<Book> books;
        if (search != null && !search.isEmpty()) {
            books = daoBook.searchBooksByTitle(search);
        } else if (categoryIds != null && categoryIds.length > 0) {
            books = new ArrayList<>();
            for (String categoryId : categoryIds) {
                books.addAll(daoBook.filterBooksByCategory(Integer.parseInt(categoryId)));
            }
        } else {
            books = daoBook.getAllBooks();
        }

        int totalBooks = books.size();
        int totalPages = (int) Math.ceil((double) totalBooks / BOOKS_PER_PAGE);

        int startIndex = (currentPage - 1) * BOOKS_PER_PAGE;
        int endIndex = Math.min(startIndex + BOOKS_PER_PAGE, totalBooks);
        List<Book> booksForPage = books.subList(startIndex, endIndex);

        List<Book> featuredBooks = daoBook.getFeaturedBooks();
        List<Category> categories = daoCategory.getAllCategories();

        request.setAttribute("books", booksForPage);
        request.setAttribute("featuredBooks", featuredBooks);
        request.setAttribute("categories", categories);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        if ("admin".equals(view)) {
            request.getRequestDispatcher("./admin/adminInterface.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("books.jsp").forward(request, response);
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
        return "BooksServlet";
    }
}
