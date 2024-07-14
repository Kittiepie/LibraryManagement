<%-- 
    Document   : userborrowcard
    Created on : Jul 5, 2024, 7:34:25 PM
    Author     : OwO
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>User Borrow Cards</title>
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
    </head>
    <body>
        <%@include file="/common/header.jsp" %>
        <%@include file="/common/sidebar.jsp" %>
        <div id="page-container">
            <div id="content-wrap" class="container">
                <h4>Your Borrow Cards</h4>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Borrow Card ID</th>
                            <th>Create Date</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="borrowCard" items="${borrowCardList}">
                            <tr>
                                <td>
                                    ${borrowCard.getBorrowCardId()}
                                    <c:if test="${borrowCard.getBorrowCardId() == activeBorrowCardId}">
                                        (active)
                                    </c:if>
                                </td>
                                <td>${borrowCard.getCreateDate()}</td>
                                <td>
                                    <form action="BorrowCardContentServlet" method="get">
                                        <input type="hidden" name="borrowCardId" value="${borrowCard.getBorrowCardId()}"/>
                                        <button type="submit" class="btn btn-secondary">View Details</button>
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
                                <a class="page-link" href="UserBorrowCardsServlet?page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
        <%@include file="/common/footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </body>
</html>