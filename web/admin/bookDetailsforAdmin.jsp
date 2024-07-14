<%-- 
    Document   : bookDetailsforAdmin
    Created on : Jul 1, 2024, 1:54:10 PM
    Author     : OwO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Book Details</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
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

            <div class="container mt-4">
                <div class="row">
                    <div class="col-md-2">
                        <div class="mt-3">
                            <c:if test="${book.featured}">
                                <div class="tag-featured">Featured</div>
                            </c:if>
                            <div class="category-section">
                                <h5>Category</h5>
                                <p>${book.categoryName}</p>
                            </div>
                            <a href="BooksServlet?view=admin" class="btn btn-secondary">Back to Books</a>
                            <a href="EditBookServlet?bookId=${book.bookId}" class="btn btn-secondary">Edit</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <img src="${book.bookCover}" class="img-fluid" alt="${book.bookTitle}">

                    </div>
                    <div class="col-md-6">
                        <h1>${book.bookTitle}</h1>
                        <p>${book.briefInfo}</p>
                        <p>${book.description}</p>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="/common/footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </body>
</html>
