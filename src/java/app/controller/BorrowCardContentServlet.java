/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package app.controller;

import app.dal.DAOBorrowCard;
import app.dal.DAOBorrowCardContent;
import app.entity.BorrowCard;
import app.entity.BorrowCardContent;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author OwO
 */
@WebServlet("/BorrowCardContentServlet")
public class BorrowCardContentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DAOBorrowCard daoBorrowCard;
    private DAOBorrowCardContent daoBorrowCardContent;

    public BorrowCardContentServlet() {
        daoBorrowCard = new DAOBorrowCard();
        daoBorrowCardContent = new DAOBorrowCardContent();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int borrowCardId = Integer.parseInt(request.getParameter("borrowCardId"));
        BorrowCard borrowCard = daoBorrowCard.getBorrowCardById(borrowCardId);
        List<BorrowCardContent> borrowCardContents = daoBorrowCardContent.getBorrowCardContentByBorrowCardId(borrowCardId);

        

        Map<Integer, String> bookTitles = new HashMap<>();
        for (BorrowCardContent content : borrowCardContents) {
            String title = daoBorrowCardContent.getBookTitleByBookId(content.getBookId());
//            System.out.println("Book ID: " + content.getBookId() + " - Title: " + title);
            bookTitles.put(content.getBookId(), title);
        }
        
        request.setAttribute("borrowCard", borrowCard);
        request.setAttribute("borrowCardContents", borrowCardContents);
        request.setAttribute("bookTitles", bookTitles);
        
        HttpSession session = request.getSession();
        String user_role = (Integer) session.getAttribute("userRoleId") + "";
        if (user_role != null) {
            if (user_role.contains("1")) {
                RequestDispatcher view = request.getRequestDispatcher("readerborrowcardcontent.jsp");
                view.forward(request, response);
            } else {
                RequestDispatcher view = request.getRequestDispatcher("./admin/borrowcardcontent.jsp");
                view.forward(request, response);
            }
        }
    }
}
