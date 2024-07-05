<%-- 
    Document   : cart
    Created on : Jul 5, 2024, 7:04:54 PM
    Author     : OwO
--%>


<%@ page import="java.util.List" %>
<%@ page import="app.entity.Book" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Books</title>
        <%@include file="/common/ImportBootstrap.jsp" %>
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
        <script>
            function confirmRemove() {
                return confirm("Are you sure you want to remove this book from your cart?");
            }
        </script>
    </head>
    <body>
        <%@include file="/common/header.jsp" %>
        <%@include file="/common/sidebar.jsp" %>
        <div id="page-container">
            <div id="content-wrap" class="container">
                <h3>Your Cart</h3>
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger">${errorMessage}</div>
                </c:if>
                <c:if test="${not empty cart}">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="book" items="${cart}">
                                <tr>
                                    <td>${book.getBookTitle()}</td>
                                    <td>
                                        <form action="CartServlet" method="post" onsubmit="return confirmRemove()">
                                            <input type="hidden" name="action" value="remove">
                                            <input type="hidden" name="bookId" value="${book.getBookId()}">
                                            <button type="submit" class="btn btn-danger">Remove</button>
                                        </form>
                                        <form action="CartServlet" method="post" style="display:inline;">
                                            <input type="hidden" name="action" value="borrowSingle">
                                            <input type="hidden" name="bookId" value="${book.getBookId()}">
                                            <button type="submit" class="btn btn-primary">Borrow</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <form action="CartServlet" method="post">
                        <input type="hidden" name="action" value="borrowAll">
                        <button type="submit" class="btn btn-primary">Borrow All</button>
                    </form>
                </c:if>
                <c:if test="${empty cart}">
                    <p>Your cart is empty</p>
                </c:if>
            </div>
        </div>
        <%@include file="/common/footer.jsp" %>
    </body>
</html>
