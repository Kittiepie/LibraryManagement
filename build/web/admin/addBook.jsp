<%-- 
    Document   : addBook
    Created on : Jul 1, 2024, 11:33:18 PM
    Author     : OwO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add New Book</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
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
                    <h2>Add New Book</h2>
                    <form action="AddBookServlet" method="post">
                        <div class="mb-3">
                            <label for="bookTitle" class="form-label">Book Title</label>
                            <input type="text" class="form-control" id="bookTitle" name="bookTitle" required>
                        </div>
                        <div class="mb-3">
                            <label for="categoryId" class="form-label">Category</label>
                            <select class="form-select" id="categoryId" name="categoryId" required>
                                <c:forEach var="category" items="${categories}">
                                    <option value="${category.categoryId}">${category.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="bookStatus" class="form-label">Status</label>
                            <select class="form-select" id="bookStatus" name="bookStatus" required>
                                <option value="true">Available</option>
                                <option value="false">Unavailable</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="isFeatured" class="form-label">Featured</label>
                            <select class="form-select" id="isFeatured" name="isFeatured" required>
                                <option value="true">Yes</option>
                                <option value="false">No</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="bookCoverFile" class="form-label">Book Cover Image</label>
                            <input type="file" class="form-control" id="bookCoverFile" name="bookCoverFile" onchange="previewAndResizeImage(event)">
                        </div>
                        <div class="mb-3">
                            <img style="max-width: 30%" id="bookCoverPreview" alt="Book Cover Preview" style="width: 100%; margin-top: 10px;">
                        </div>
                        <div class="mb-3">
                            <input type="hidden" id="bookCoverBase64" name="bookCoverBase64">
                        </div>
                        <div class="mb-3">
                            <label for="briefInfo" class="form-label">Brief Info</label>
                            <textarea class="form-control" id="briefInfo" name="briefInfo" rows="3" required></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label">Description</label>
                            <textarea class="form-control" id="description" name="description" rows="5" required></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Add Book</button>
                    </form>
                </div>
            </main>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                                function previewAndResizeImage(event) {
                                    var file = event.target.files[0];
                                    var reader = new FileReader();
                                    reader.onload = function (e) {
                                        var img = new Image();
                                        img.onload = function () {
                                            var canvas = document.createElement('canvas');
                                            var ctx = canvas.getContext('2d');
                                            canvas.width = 200;  // Set desired width
                                            canvas.height = 300; // Set desired height
                                            ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
                                            var dataURL = canvas.toDataURL('image/jpeg');
                                            document.getElementById('bookCoverPreview').src = dataURL;
                                            document.getElementById('bookCoverBase64').value = dataURL;
                                        };
                                        img.src = e.target.result;
                                    };
                                    reader.readAsDataURL(file);
                                }

                                function prepareImageForSubmit() {
                                    var fileInput = document.getElementById('bookCoverFile');
                                    if (fileInput.files.length > 0) {
                                        var file = fileInput.files[0];
                                        var reader = new FileReader();
                                        reader.onload = function (e) {
                                            var img = new Image();
                                            img.onload = function () {
                                                var canvas = document.createElement('canvas');
                                                var ctx = canvas.getContext('2d');
                                                canvas.width = 200;  // Set desired width
                                                canvas.height = 300; // Set desired height
                                                ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
                                                var dataURL = canvas.toDataURL('image/jpeg');
                                                document.getElementById('bookCoverBase64').value = dataURL;
                                                document.forms[0].submit();
                                            };
                                            img.src = e.target.result;
                                        };
                                        reader.readAsDataURL(file);
                                        return false;  // Prevent form submission to wait for file reading
                                    }
                                    return true;  // No file selected, proceed with form submission
                                }
        </script>
    </body>
</html>