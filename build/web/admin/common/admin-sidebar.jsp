<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<aside class="admin-aside bg-dark hide">
    <h5 class="admin-aside-title" data-title="Books Management">
        <i class="bi bi-book"></i> 
    </h5>
    <div class="admin-aside-links-container">
        <myTags:AdminLink icon="bi-list-ul" href="BooksServlet?view=admin" title="Book Shelf" />
        <myTags:AdminLink icon="bi bi-plus-square-fill" href="AddBookServlet" title="New Book" />
        <myTags:AdminLink icon="bi bi-arrow-through-heart-fill" href="admin/sliderlist.jsp" title="Slider List" />
    </div>
    <c:if test="${sessionScope.userRoleId == 4}">
        <h5 class="admin-aside-title" data-title="Staffs Management">
            <i class="bi bi-people"></i> 
        </h5>
        <div class="admin-aside-links-container">
            <myTags:AdminLink icon="bi-shield-lock-fill" href="StaffServlet" title="Staffs" />
        </div>
    </c:if>
    <h5 class="admin-aside-title" data-title="Return to reader page">
        <i class="bi bi-unlock-fill"></i> 
    </h5>
    <div class="admin-aside-links-container">
        <myTags:AdminLink icon="bi bi-asterisk" href="./index.jsp" title="Reader page" />
    </div>
    <h5 class="admin-aside-title" data-title="Reader Management">
        <i class="bi bi-arrow-right"></i>
    </h5>
    <div class="admin-aside-links-container">
        <myTags:AdminLink icon="bi-people-fill" href="BorrowCardServlet" title="Readers" />
    </div>
</aside>