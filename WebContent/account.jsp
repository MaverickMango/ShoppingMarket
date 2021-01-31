<%@page import="bean.Product"%>
<%@page import="util.DBUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Basic page needs -->
<meta charset="utf-8">
<!--[if IE]>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<![endif]-->

<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>添加成功</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="style.css">
<style type="text/css">
@media (min-width: 1200px)
<style>
.col-lg-5 {
    width: 20%;
}
</style>
</head>

<body class="single-product-page">
<div id="page">
  <header>
    <div class="header-container">
      <div class="header-top">
        <div class="container">
          <div class="row">
            <div class="col-xs-6 hidden-xs">
              <!-- Header Top Links -->
              <div class="jtv-top-links">
                <div class="links">
                  <ul>
                    <li><a title="My Account" href="account.jsp"><span class="hidden-xs">我的账号</span></a></li>
                    <li><a title="Wishlist" href="cartIndex.jsp">购物车</a></li>
                    <li><a title="Checkout" href="LogoutServlet"><span class="hidden-xs">退出</span></a></li>
                  </ul>
                </div>
              </div>
              <!-- End Header Top Links -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>
  <!-- Main Container -->
  <section class="main-container col1-layout">
    <div class="main">
      <div class="container">
        <div class="row">
          <div class="col-main">
            <div class="product-view">
              <div class="product-essential">
                <form action="" method="post" id="product">
                <%
                String account = (String) session.getAttribute("account");
                if(account == null) {
                	//response.sendRedirect("exception.jsp");
                }
                %>
                  <div class="product-img-box col-lg-5 col-sm-6 col-xs-12">
                    <div class="product-image">
                      <div class="product-full">
                        <img id="product-zoom" src="img/head.jpg">
                      </div>
                    </div>
                  </div>
                  <div class="product-shop col-lg-7 col-sm-6 col-xs-12">
                    <div class="product-name">
                      <h1><%=account %></h1>
                    </div>

                    <div class="price-block">
                      <div class="price-box">
                        <p class="special-price"> <span class="price-label">change to VIP</span> <span id="product-price-48" class="price">您还不是会员。</span> </p>
                      </div>
                    </div>
                    <div class="short-description">
                      <p>###</p>
                    </div>
                    <div class="form-option">
                      <div class="add-to-box">
                        <div class="add-to-cart">
                          <button onClick="window.location.href='/ShoppingMarket/index.jsp'" class="button btn-cart" title="Back home" type="button">返回主页</span></button>
                          <button onClick="window.location.href='/ShoppingMarket/cartIndex.jsp'" class="button btn-cart" title="Go to Cart" type="button">我的购物车</span></button>
                        </div>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</body>
</html>
