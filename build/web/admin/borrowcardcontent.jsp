<%-- 
    Document   : borrowcardcontent
    Created on : Jul 4, 2024, 10:46:38 AM
    Author     : OwO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <h4>Borrow Card Details</h4>
                    <h5>Borrow Card ID: ${borrowCard.getBorrowCardId()}</h5>
                    <h5>Create Date: ${borrowCard.getCreateDate()}</h5>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Book ID</th>
                                <th>Title</th>
                                <th>Borrow Date</th>
                                <th>Return Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${empty borrowCardContents}">
                                    <tr>
                                        <td colspan="3">No books borrowed</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="content" items="${borrowCardContents}">
                                        <tr>
                                            <td>${content.getBookId()}</td>
                                            <td>${bookTitles[content.getBookId()]}</td>
                                            <td>${content.getBorrowDate()}</td>
                                            <td>${content.getReturnDate()}</td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
            </main>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </body>
</html>
