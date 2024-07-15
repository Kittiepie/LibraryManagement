/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package app.controller;

import app.dal.DAOUser;
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
@WebServlet("/StaffServlet")
public class StaffServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DAOUser daoUser;

    public StaffServlet() {
        daoUser = new DAOUser();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recordsPerPage = 5;

        // Handle active staff
        int activePage = 1;
        if (request.getParameter("activePage") != null) {
            activePage = Integer.parseInt(request.getParameter("activePage"));
        }
        List<User> activeStaffList = daoUser.getStaffByRole(2, (activePage - 1) * recordsPerPage, recordsPerPage);
        int noOfActiveRecords = daoUser.getNoOfRecordsByRole(2);
        int noOfPagesActive = (int) Math.ceil(noOfActiveRecords * 1.0 / recordsPerPage);

        // Handle inactive staff
        int inactivePage = 1;
        if (request.getParameter("inactivePage") != null) {
            inactivePage = Integer.parseInt(request.getParameter("inactivePage"));
        }
        List<User> inactiveStaffList = daoUser.getStaffByRole(3, (inactivePage - 1) * recordsPerPage, recordsPerPage);
        int noOfInactiveRecords = daoUser.getNoOfRecordsByRole(3);
        int noOfPagesInactive = (int) Math.ceil(noOfInactiveRecords * 1.0 / recordsPerPage);

        request.setAttribute("activeStaffList", activeStaffList);
        request.setAttribute("noOfPagesActive", noOfPagesActive);
        request.setAttribute("currentPageActive", activePage);

        request.setAttribute("inactiveStaffList", inactiveStaffList);
        request.setAttribute("noOfPagesInactive", noOfPagesInactive);
        request.setAttribute("currentPageInactive", inactivePage);

        RequestDispatcher view = request.getRequestDispatcher("./admin/staff.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String service = request.getParameter("service");

        if ("addStaff".equals(service)) {
            addStaff(request, response);
        } else if ("updateStaffStatus".equals(service)) {
            updateStaffStatus(request, response);
        }
    }

    private void addStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        int genderId = Integer.parseInt(request.getParameter("genderId"));
        String mobile = request.getParameter("mobile");
        int roleId = Integer.parseInt(request.getParameter("roleId"));

        // Add new staff
        User newUser = new User(1, email, password, roleId, fullName, genderId, mobile, "public/images/anonymous-user.webp", true);
        daoUser.addUser(newUser);

        response.sendRedirect("StaffServlet");
    }

    private void updateStaffStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int roleId = Integer.parseInt(request.getParameter("roleId"));

        daoUser.updateUserRole(userId, roleId);

        response.sendRedirect("StaffServlet");
    }
}
