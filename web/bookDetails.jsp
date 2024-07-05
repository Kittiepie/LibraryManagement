<%-- 
    Document   : bookDetails
    Created on : Jun 30, 2024, 3:12:53 PM
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
    <style>
        .tag-featured {
            display: inline-block;
            padding: 5px 10px;
            background-color: blue;
            color: white;
            font-size: 12px;
            border-radius: 4px;
            margin-bottom: 10px;
        }
        .category-section {
            margin-bottom: 20px;
        }
        .category-section h5 {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <%@include file="/common/header.jsp" %>
    <%@include file="/common/sidebar.jsp" %>

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
                    <a href="BooksServlet" class="btn btn-secondary">Back to Books</a>
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

    <%@include file="/common/footer.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</body>
</html>