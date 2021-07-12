<%--
  Created by IntelliJ IDEA.
  User: 84936
  Date: 7/12/2021
  Time: 8:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Product Management</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
  <div class="container-fluid">
    <div class="row" style="height: 100px"></div>

    <div class="row">
      <form method="get">
        <label> Search
          <input type="search" placeholder="Product ID" name="p_name" style="width: 200px">
        </label>
        <input type="submit" name="action" value="Search">
        <a class="btn btn-light" style="background-color: #3b5998;" href="<c:url value="/product-manager"/>" role="button">Reset</a>
      </form>
    </div>
    <hr>
    <div class="row">
      <p>Insert New Product </p>
      <form method="post">
        <label> Product ID
          <input type="text" name="p_id">
        </label>
        <label> Product Name
          <input type="text" name="p_name">
        </label>
        <label> Product Price
          <input type="text" name="p_price">
        </label>
        <label> Stock Quantity
          <input type="text" name="quantity">
        </label>
        <label> Product Color
          <input type="text" name="color">
        </label>
        <label> Category
          <input type="text" name="catogery">
        </label>
        <input type="submit" name="action" value="Create">
      </form>
      <hr>
    </div>

    <div class="row"> Insert New Product into List
      <table  class="table table-bordered" style="text-align: center">
        <tr>
          <th style="width: 10%">Product ID</th>
          <th style="width: 10%;">Product Name</th>
          <th style="width: 10%">Product Price</th>
          <th style="width: 10%;">Stock Quantity</th>
          <th style="width: 10%">Color</th>
          <th style="width: 10%;">Category</th>
          <th>Action</th>
        </tr>
        <c:forEach var="product" items="${requestScope['products']}">
          <tr>
            <td>${product.p_id}</td>
            <td>${product.p_name}</td>
            <td>${product.price}</td>
            <td>${product.stock_quantity}</td>
            <td>${product.color}</td>
            <td>${product.category}</td>
            <td>
              <a class="btn btn-primary" style="background-color: #3b5998;" href="<c:url value="/product-manager?action=edit&p_id=${product.p_id}"/>" role="button">Edit</a>
              <a class="btn btn-primary" style="background-color: #55acee;" href="<c:url value="/product-manager?action=delete&p_id=${product.p_id}"/>" role="button">Delete</a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>

    </div>
  </div>




  HELL NO
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</html>
