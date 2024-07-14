<%-- 
    Document   : borrowcard
    Created on : Jul 3, 2024, 10:58:19 AM
    Author     : OwO
--%>


<%@ page import="java.util.Vector" %>
<%@ page import="app.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Admin Interface</title>
        <%@include file="/common/ImportBootstrap.jsp" %>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminInterface.css" />
        <link rel="stylesheet" href="admin/common/admin-common.css">
        <script src="admin/common/admin-common.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <style>
            html {
                min-height: 100%;
                display: flex;
            }

            body {
                flex-grow: 1;
                display: flex;
                flex-direction: column;
                position: relative;
            }

            main {
                flex-grow: 1;
            }

            #page-container {
                display: flex;
                flex-direction: column;
                min-height: 100vh;
            }
            #content-wrap {
                flex: 1;
            }
            .footer {
                background-color: #f8f9fa;
                padding: 20px 0;
                text-align: center;
            }
            .carousel-item {
                display: flex;
                justify-content: center;
                align-items: center;
                background-color: #f0f0f0;
                padding: 20px;
            }
            .carousel-item .card {
                margin: 10px 3px;
            }
            .carousel-item img {
                height: 200px;
                object-fit: contain;
            }
        </style>
    </head>
    <body>
        <% 
            if (session.getAttribute("userRoleId") == null || (!((Integer)session.getAttribute("userRoleId") == 2 || (Integer)session.getAttribute("userRoleId") == 4))) {
                session.setAttribute("successMessage", "Not authorized!");
                response.sendRedirect("./index.jsp");
            } 
        %>
        <div class="admin-layout">
            <%@include file="/admin/common/admin-header.jsp" %>
            <%@include file="/admin/common/admin-sidebar.jsp" %>

            <main class="admin-main">
                <div id="page-container">
                    <h4>Manage Borrow Cards</h4>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Email</th>
                                <th>Full Name</th>
                                <th>Gender</th>
                                <th>Mobile</th>
                                <th>Active</th>
                                <th>Current Borrow card ID</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${userList}">
                                <tr>
                                    <td>${user.getEmail()}</td>
                                    <td>${user.getFullName()}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.getGenderId() == 0}">Male</c:when>
                                            <c:when test="${user.getGenderId() == 1}">Female</c:when>
                                            <c:otherwise>Other</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${user.getMobile()}</td>
                                    <td><c:choose><c:when test="${user.isIsActive()}">Yes</c:when><c:otherwise>No</c:otherwise></c:choose></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.convertBorrowcardIdToInt(user.getBorrowcardId()) == 0}">None</c:when>
                                            <c:otherwise>${user.convertBorrowcardIdToInt(user.getBorrowcardId())}</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <form action="BorrowCardServlet" method="post">
                                            <input type="hidden" name="userId" value="${user.getUserId()}"/>
                                            <button type="submit" class="btn btn-primary">Create Borrow Card</button>
                                        </form>
                                        <form action="SetActiveCardServlet" method="get">
                                            <input type="hidden" name="userId" value="${user.getUserId()}"/>
                                            <button type="submit" class="btn btn-secondary">Set Active Card</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:forEach var="i" begin="1" end="${noOfPages}">
                                <li class="page-item ${i == currentPage ? 'active' : ''}">
                                    <a class="page-link" href="BorrowCardServlet?page=${i}">${i}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </nav>
                </div>
            </main>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </body>
</html>