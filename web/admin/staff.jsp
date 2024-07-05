<%-- 
    Document   : staff
    Created on : Jul 3, 2024, 1:28:41 PM
    Author     : OwO
--%>

<%@ page import="java.util.List" %>
<%@ page import="app.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Staff</title>
    <%@include file="/common/ImportBootstrap.jsp" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminInterface.css" />
    <link rel="stylesheet" href="admin/common/admin-common.css">
    <script src="admin/common/admin-common.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div class="admin-layout">
        <%@include file="/admin/common/admin-header.jsp" %>
        <%@include file="/admin/common/admin-sidebar.jsp" %>

        <main class="admin-main">
            <div id="page-container">
                <h2>Manage Staff</h2>
                <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addStaffModal">Add Staff</button>

                <!-- Active Staff Section -->
                <h3>Active Staff</h3>
                <table class="table table-striped">
                    <!-- Table Header -->
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>Full Name</th>
                            <th>Gender</th>
                            <th>Mobile</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <!-- Active Staff List -->
                    <tbody>
                        <c:forEach var="user" items="${activeStaffList}">
                            <tr>
                                <td>${user.getEmail()}</td>
                                <td>${user.getFullName()}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.getGenderId() == 0}">Male</c:when>
                                        <c:when test="${user.getGenderId() == 1}">Female</c:when>
                                        <c:otherwise>Other</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${user.getMobile()}</td>
                                <td>
                                    <form action="StaffServlet" method="post">
                                        <input type="hidden" name="userId" value="${user.getUserId()}"/>
                                        <input type="hidden" name="roleId" value="3"/>
                                        <button type="submit" class="btn btn-warning">Set Inactive</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Pagination for Active Staff -->
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:forEach var="i" begin="1" end="${noOfPagesActive}">
                            <li class="page-item ${i == currentPageActive ? 'active' : ''}">
                                <a class="page-link" href="StaffServlet?view=active&page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>

                <!-- Inactive Staff Section -->
                <h3>Inactive Staff</h3>
                <table class="table table-striped">
                    <!-- Table Header -->
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>Full Name</th>
                            <th>Gender</th>
                            <th>Mobile</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <!-- Inactive Staff List -->
                    <tbody>
                        <c:forEach var="user" items="${inactiveStaffList}">
                            <tr>
                                <td>${user.getEmail()}</td>
                                <td>${user.getFullName()}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.getGenderId() == 0}">Male</c:when>
                                        <c:when test="${user.getGenderId() == 1}">Female</c:when>
                                        <c:otherwise>Other</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${user.getMobile()}</td>
                                <td>
                                    <form action="StaffServlet" method="post">
                                        <input type="hidden" name="userId" value="${user.getUserId()}"/>
                                        <input type="hidden" name="roleId" value="2"/>
                                        <button type="submit" class="btn btn-success">Set Active</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Pagination for Inactive Staff -->
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:forEach var="i" begin="1" end="${noOfPagesInactive}">
                            <li class="page-item ${i == currentPageInactive ? 'active' : ''}">
                                <a class="page-link" href="StaffServlet?view=inactive&page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </main>
    </div>

    <!-- Add Staff Modal -->
    <div class="modal fade" id="addStaffModal" tabindex="-1" aria-labelledby="addStaffModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addStaffModalLabel">Add Staff</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="StaffServlet" method="post">
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <div class="mb-3">
                            <label for="fullName" class="form-label">Full Name</label>
                            <input type="text" class="form-control" id="fullName" name="fullName" required>
                        </div>
                        <div class="mb-3">
                            <label for="gender" class="form-label">Gender</label>
                            <select class="form-select" id="gender" name="genderId" required>
                                <option value="0">Male</option>
                                <option value="1">Female</option>
                                <option value="2">Other</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="mobile" class="form-label">Mobile</label>
                            <input type="text" class="form-control" id="mobile" name="mobile" required>
                        </div>
                        <div class="mb-3">
                            <label for="roleId" class="form-label">Role</label>
                            <select class="form-select" id="roleId" name="roleId" required>
                                <option value="2">Active Staff</option>
                                <option value="3">Inactive Staff</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Add Staff</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
