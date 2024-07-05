<header class="admin-header">
    <div class="container-fluid admin-header-container bg-secondary d-flex align-items-center">
        <div class="row flex-grow-1 gx-1">
            <div class="col-11 d-flex align-items-center">
                <button onclick="adminShowHideSidebar()" type="button" class="btn btn-outline text-white hamburger-btn" aria-label="Close">
                    <i class="bi bi-list"></i>
                </button>
                <h5 class="admin-header-title">Management Console</h5>
            </div>
            <div class="col-1 d-flex justify-content-end">
                <form method="post" action="loginviewofAn">
                    <button type="submit" class="btn btn-dark">Logout</button></li>
                    <input type="hidden" name="service" value="logout"/>
                </form>
            </div>
        </div>
    </div>
</header>