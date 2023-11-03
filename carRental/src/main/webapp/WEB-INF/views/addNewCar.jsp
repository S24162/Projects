<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<%@ page contentType="text/html; charset=UTF-8" %>

<%--Head--%>
<%@ include file="dynamic/head.jspf" %>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

  <!-- Sidebar -->
  <%@ include file="dynamic/sidebar.jspf" %>
  <!-- End of Sidebar -->

  <!-- Content Wrapper -->
  <div id="content-wrapper" class="d-flex flex-column">

    <!-- Main Content -->
    <div id="content">

      <!-- Topbar -->
      <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

        <!-- Sidebar Toggle (Topbar) -->
        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
          <i class="fa fa-bars"></i>
        </button>


        <!-- Topbar Navbar -->
        <ul class="navbar-nav ml-auto">

          <!-- Nav Item - Search Dropdown (Visible Only XS) -->
          <li class="nav-item dropdown no-arrow d-sm-none">
            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <i class="fas fa-search fa-fw"></i>
            </a>
            <!-- Dropdown - Messages -->
            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                 aria-labelledby="searchDropdown">
              <form class="form-inline mr-auto w-100 navbar-search">
                <div class="input-group">
                  <input type="text" class="form-control bg-light border-0 small"
                         placeholder="Search for..." aria-label="Search"
                         aria-describedby="basic-addon2">
                  <div class="input-group-append">
                    <button class="btn btn-primary" type="button">
                      <i class="fas fa-search fa-sm"></i>
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </li>


          <div class="topbar-divider d-none d-sm-block"></div>

          <!-- Nav Item - User Information -->
          <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <span class="mr-2 d-none d-lg-inline text-gray-600 small">Contact Us</span>

            </a>
            <!-- Dropdown - User Information -->
            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                 aria-labelledby="userDropdown">
              <a class="dropdown-item" href="#">
                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                Profile
              </a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                Logout
              </a>
            </div>
          </li>

        </ul>

      </nav>
      <!-- End of Topbar -->

      <!-- Begin Page Content -->
      <div class="container-fluid">
        <form method="post" action='<c:url value="/addCar"/>'>
          <!-- Content Row -->
          <div class="row">
            <div class="col-xl-12 col-md-12 mb-12">
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <div class="form-group row">
                    <label class="col-2 col-form-label">Vin Number</label>
                    <div class="col-10">
                      <input class="form-control" type="text" name = "vinNumber" placeholder="Provide a Vin Number">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-2 col-form-label">Brand</label>
                    <div class="col-10">
                      <input class="form-control" type="text" name = "brand" placeholder="Provide a brand">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-2 col-form-label">Model</label>
                    <div class="col-10">
                      <input class="form-control" type="text" name = "model" placeholder="Provide a model">
                    </div>
                  </div>

                  <div class="form-group row">
                    <label class="col-2 col-form-label">Body type</label>
                    <div class="col-10">
                      <input class="form-control" type="text" name = "bodyType" placeholder="Provide body type">
                    </div>
                  </div>


                  <div class="form-group row">
                    <label class="col-2 col-form-label">Color</label>
                    <div class="col-10">
                        <input class="form-control" type="text" name = "color" placeholder="Provide color">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-2 col-form-label">Year</label>
                    <div class="col-10">
                        <input class="form-control" type="text" name = "year" placeholder="Provide a year">
                    </div>
                  </div>
                  <div class="form-group row">
                    <label class="col-2 col-form-label">Mileage in km</label>
                    <div class="col-10">
                        <input class="form-control" type="text" name = "mileageKm" placeholder="Provide mileage in km">
                    </div>
                  </div>

                  <div class="form-group row">
                    <label class="col-2 col-form-label">Status</label>
                    <div class="col-10">
                        <input class="form-control" type="text" name = "status" placeholder="Provide status">
                    </div>
                  </div>

                  <div class="form-group row">
                    <label class="col-2 col-form-label">Price ($)</label>
                    <div class="col-10">
                        <input class="form-control" type="text" name = "price" placeholder="Provide price">
                    </div>
                  </div>


                </div>
              </div>
            </div>
          </div>
          <input class="btn btn-success pull-left" type="submit" value="Add car" id="searchButton"></input>

        </form>


      </div>
      <!-- /.container-fluid -->

    </div>
    <!-- End of Main Content -->

    <!-- Footer -->
    <%@ include file="dynamic/footer.jspf" %>
    <!-- End of Footer -->

  </div>
  <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
  <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<%@ include file="dynamic/logoutModal.jspf" %>

<%-- JavaScript --%>
<%@ include file="dynamic/javaScript.jspf" %>

</body>

</html>
