<%-- 
    Document   : editBook
    Created on : Jul 1, 2024, 2:43:54 PM
    Author     : OwO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Book</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    </head>
    <body>
        <% 
                if (session.getAttribute("userRoleId") == null || (!((Integer)session.getAttribute("userRoleId") == 2 || (Integer)session.getAttribute("userRoleId") == 4))) {
                    session.setAttribute("successMessage", "Not authorized!");
                    response.sendRedirect("./index.jsp");
                } 
        %>
        <div class="container mt-5">
            <h2>Edit Book</h2>
            <form action="EditBookServlet" method="post" onsubmit="return prepareImageForSubmit();">
                <input type="hidden" name="bookId" value="${book.bookId}">
                <div class="mb-3">
                    <label for="bookTitle" class="form-label">Book Title</label>
                    <input type="text" class="form-control" id="bookTitle" name="bookTitle" value="${book.bookTitle}" required>
                </div>
                <div class="mb-3">
                    <label for="categoryId" class="form-label">Category</label>
                    <select class="form-select" id="categoryId" name="categoryId" required>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.categoryId}" ${category.categoryId == book.categoryId ? 'selected' : ''}>${category.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="bookStatus" class="form-label">Status</label>
                    <select class="form-select" id="bookStatus" name="bookStatus" required>
                        <option value="true" ${book.bookStatus ? 'selected' : ''}>Available</option>
                        <option value="false" ${!book.bookStatus ? 'selected' : ''}>Unavailable</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="isFeatured" class="form-label">Featured</label>
                    <select class="form-select" id="isFeatured" name="isFeatured" required>
                        <option value="true" ${book.isFeatured ? 'selected' : ''}>Yes</option>
                        <option value="false" ${!book.isFeatured ? 'selected' : ''}>No</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="bookCoverFile" class="form-label">Book Cover Image</label>
                    <input type="file" class="form-control" id="bookCoverFile" name="bookCoverFile" onchange="previewAndResizeImage(event)">
                </div>
                <div class="mb-3">
                    <img style="max-width: 30%" id="bookCoverPreview" src="${book.bookCover}" alt="Book Cover Preview" style="width: 100%; margin-top: 10px;">
                </div>
                <div class="mb-3">
                    <input type="hidden" id="bookCoverBase64" name="bookCoverBase64" value="${book.bookCover}">
                </div>
                <div class="mb-3">
                    <label for="briefInfo" class="form-label">Brief Info</label>
                    <textarea class="form-control" id="briefInfo" name="briefInfo" rows="3" required>${book.briefInfo}</textarea>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <textarea class="form-control" id="description" name="description" rows="5" required>${book.description}</textarea>
                </div>
                <button type="submit" class="btn btn-primary">Save Changes</button>
            </form>
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