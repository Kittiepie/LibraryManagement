<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </head>
    <body>
        <%@include file="/common/header.jsp" %>
        <%@include file="/common/sidebar.jsp" %>
        <div id="page-container">


            <div id="content-wrap" class="container">
                <!-- Carousel for Featured Books -->
                <h3>Featured Books</h3>
                <div id="featuredBooksCarousel" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner">
                        <c:forEach var="book" items="${featuredBooks}" varStatus="status">
                            <c:if test="${status.index % 3 == 0}">
                                <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
                                    <div class="row">
                                        <div style="margin-left: 50px" class="col-md-1">
                                        </div>
                                    </c:if>

                                    <div class="col-md-3">
                                        <div class="card">
                                            <img src="${book.bookCover}" class="card-img-top" alt="${book.bookTitle}">
                                            <div class="card-body">
                                                <h5 class="card-title">${book.bookTitle}</h5>
                                                <p class="card-text">${book.briefInfo}</p>
                                                <a href="BookDetailsServlet?bookId=${book.bookId}" class="btn btn-primary">View Details</a>
                                                <button type="button" class="btn btn-secondary" onclick="addToCart(${book.bookId})">
                                                    Add to List
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${status.index % 3 == 2 || status.index == featuredBooks.size() - 1}">
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#featuredBooksCarousel" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#featuredBooksCarousel" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>

                <!-- Book Shelf -->
                <div class="row mt-4">
                    <div class="col-md-3">
                        <!-- Search and Filter -->
                        <h4>Search</h4>
                        <form action="BooksServlet" method="get">
                            <input type="hidden" name="view" value="user">
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" placeholder="Search by title" name="search" value="${param.search}">
                                <button class="btn btn-outline-secondary" type="submit">Search</button>
                            </div>
                        </form>

                        <h4>Filter by Category</h4>
                        <form action="BooksServlet" method="get">
                            <input type="hidden" name="view" value="user">
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
                                            <a href="BookDetailsServlet?bookId=${book.bookId}" class="btn btn-primary">View Details</a>
                                            <button type="button" class="btn btn-secondary" onclick="addToCart(${book.bookId})">
                                                Add to List
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!-- Pagination Controls -->
                        <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-center">
                                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                    <a class="page-link" href="BooksServlet?view=user&page=${currentPage - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" href="BooksServlet?view=user&page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                    <a class="page-link" href="BooksServlet?view=user&page=${currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>

                    </div>
                </div>
            </div>
        </div>
        <%@include file="/common/footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
                                                function addToCart(bookId) {
                                                    $.post('CartServlet', {action: 'add', bookId: bookId}, function () {
                                                        alert('Book added to cart');
                                                    });
                                                }
        </script>
    </body>
</html>