<%@page import="bean.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.ProductCL"%>
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
    <title>首页</title>

    <!-- Mobile specific metas  -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- CSS Style -->
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="cms-index-index cms-home-page">
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
                                        <li><a title="My Cart" href="cartIndex.jsp">购物车</a></li>
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
    <div class="content-page">
        <div class="container">
            <!-- Product Tabs-->
            <div class="category-product">
                <div style="margin-bottom: 50px" class="jtv-title">
                    <h3>欢迎光顾每日优鲜</h3>
                </div>
                <div class="tab-container">
                    <!-- tab product -->
                    <div class="tab-panel active" id="tab-1">
                        <div class="category-products">
                            <ul class="products-grid">
                            <%
                            ProductCL pdCl = new ProductCL();
                            ArrayList<Product> allpds = pdCl.getAllProducts();
                            for(Product pd: allpds) {
                            %>
                                <li class="item col-lg-3 col-md-3 col-sm-4 col-xs-6">
                                    <div class="item-inner">
                                        <div class="item-img">
                                            <div class="item-img-info">
                                                <a class="product-image" title="<%=pd.getDescription() %>" href="AddToCartServlet?id=<%=pd.getId()%>">
                                                    <img alt="添加到购物车" src="<%=pd.getUrl()%>">
                                                </a>
                                            </div>
                                        </div>
                                        <div class="item-info">
                                            <div class="info-inner">
                                                <div class="item-title">
                                                    <h6><a href="AddToCartServlet?id=<%=pd.getId()%>">
                                                    	<%=pd.getName() %>
                                                        </a>
                                                    </h6>
                                                </div>
                                                <div class="item-content">
                                                    <div class="item-price">
                                                        <div class="price-box"><span class="regular-price"> <span
                                                                    class="price">￥<%=pd.getPrice() %></span> </span></div>
                                                    </div>
                                                    <div class="action">
                                                        <button class="button btn-cart" type="button" title=""
                                                            data-original-title="添加购物车">
                                                            <a href="AddToCartServlet?id=<%=pd.getId()%>">添加购物车</a>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            <%
                            }
                            %>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>