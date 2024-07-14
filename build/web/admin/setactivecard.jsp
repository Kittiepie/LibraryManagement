<%-- 
    Document   : setactivecard
    Created on : Jul 4, 2024, 10:12:37 AM
    Author     : OwO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
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
                    <h4>Set Active Borrow Card for ${user.getFullName()}</h4>
                    <h5>User Email: ${user.getEmail()}</h5>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Borrow Card ID</th>
                                <th>Create Date</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="borrowCard" items="${borrowCards}">
                                <tr>
                                    <td>${borrowCard.getBorrowCardId()}</td>
                                    <td>${borrowCard.getCreateDate()}</td>
                                    <td>
                                        <form action="SetActiveCardServlet" method="post" style="display: inline;">
                                            <input type="hidden" name="userId" value="${user.getUserId()}"/>
                                            <input type="hidden" name="borrowCardId" value="${borrowCard.getBorrowCardId()}"/>
                                            <button type="submit" class="btn btn-primary">Set as Active</button>
                                        </form>
                                        <form action="BorrowCardContentServlet" method="get" style="display: inline;">
                                            <input type="hidden" name="borrowCardId" value="${borrowCard.getBorrowCardId()}"/>
                                            <button type="submit" class="btn btn-info">View Details</button>
                                        </form>
                                        <form action="DeleteBorrowCardServlet" method="post" style="display: inline;" onsubmit="return confirm('Are you sure you want to delete this borrow card?');">
                                            <input type="hidden" name="borrowCardId" value="${borrowCard.getBorrowCardId()}"/>
                                            <input type="hidden" name="userId" value="${user.getUserId()}"/>
                                            <button type="submit" class="btn btn-danger">Delete</button>
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
                                    <a class="page-link" href="SetActiveCardServlet?userId=${user.getUserId()}&page=${i}">${i}</a>
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
