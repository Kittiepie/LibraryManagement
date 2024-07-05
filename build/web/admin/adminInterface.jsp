<%-- 
    Document   : adminInterface
    Created on : Jun 30, 2024, 1:26:11 PM
    Author     : OwO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <div id="content-wrap" class="container">
                        <div class="row mt-4">
                            <div class="col-md-3">
                                <!-- Search and Filter -->
                                <h4>Search</h4>
                                <form action="BooksServlet" method="get">
                                    <input type="hidden" name="view" value="admin">
                                    <div class="input-group mb-3">
                                        <input type="text" class="form-control" placeholder="Search by title" name="search" value="${param.search}">
                                        <button class="btn btn-outline-secondary" type="submit">Search</button>
                                    </div>
                                </form>

                                <h4>Filter by Category</h4>
                                <form action="BooksServlet" method="get">
                                    <input type="hidden" name="view" value="admin">
                                    <c:forEach var="category" items="${categories}">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="${category.categoryId}" name="category"
                                                   <c:forEach var="selectedCategory" items="${paramValues.category}">
                                                       <c:if test="${selectedCategory eq category.categoryId}">
                                                           checked
                                                       </c:if>
                                                   </c:forEach>
                                                   onchange="this.form.submit()">
                                            <label class="form-check-label">${category.categoryName}</label>
                                        </div>
                                    </c:forEach>
                                </form>
                            </div>
                            <div class="col-md-9">
                                <!-- Books List -->
                                <div class="row">
                                    <c:forEach var="book" items="${books}">
                                        <div class="col-md-4 mb-4">
                                            <div class="card">
                                                <img src="${book.bookCover}" class="card-img-top" alt="${book.bookTitle}">
                                                <div class="card-body">
                                                    <h5 class="card-title">${book.bookTitle}</h5>
                                                    <p class="card-text">${book.briefInfo}</p>
                                                    <a href="BookDetailsServlet?view=admin&bookId=${book.bookId}" class="btn btn-primary">View Details</a>
                                                    <a href="EditBookServlet?bookId=${book.bookId}" class="btn btn-secondary">Edit</a>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <!-- Pagination Controls -->
                                <nav aria-label="Page navigation">
                                    <ul class="pagination justify-content-center">
                                        <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                            <a class="page-link" href="BooksServlet?view=admin&page=${currentPage - 1}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                <a class="page-link" href="BooksServlet?view=admin&page=${i}">${i}</a>
                                            </li>
                                        </c:forEach>
                                        <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                            <a class="page-link" href="BooksServlet?view=admin&page=${currentPage + 1}" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <%@include file="/common/footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </body>
</html>
