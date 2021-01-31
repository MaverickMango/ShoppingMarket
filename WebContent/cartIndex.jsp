<%@page import="java.text.DecimalFormat"%>
<%@page import="bean.Product"%>
<%@page import="java.util.Vector"%>
<%@page import="bean.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="exception.jsp"%>
<!--
 * @Description  : 
 * @Author       : Maverick
 * @Date         : 2020-09-20 15:31:27
 * @LastEditors  : Maverick
 * @LastEditTime : 2020-11-01 17:15:39
-->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>购物车结算</title>
    <link rel="stylesheet" href="css/cartStyle.css">
</head>

<body>
    <div id="shopping">

        <div class="header">
            <div class="allSel">
                <input type="checkbox" name="selectAll" id="all">
                <label for="all"></label>
            </div>
            <p>全选</p>
            <div class="title">每日优鲜购物车</div>
            <a href="javascript:;" class="delAll">删除</a>
        </div>

        <!--获取购物车中的全部商品-->
        <%
        //购物车存在session里
        //获取当前用户购物车
        session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        Vector<Product> items=null;
       	items = cart.getCartItems();//为空就跳到错误界面了
        //向页面输出购物车中的商品
        if(items != null && items.size() != 0){
        	String pattern = "###,###,###.#";//用于价格输出的小数位数取1位
        	for(int i = 0; i < items.size(); i++) {//遍历当前购物车中的商品
        		Product tempProduct = items.get(i);
        %>
        <div class="product">
            <div class="btn">
                <input type="checkbox" name="item" id="cb<%=i+1 %>" class="cb"><label for="cb<%=i+1%>"></label>
            </div>
            <div class="hiddenID"><%=tempProduct.getId() %></div>
            <div class="img"><img src="<%=tempProduct.getUrl() %>" alt=""></div>
            <div class="des">
                <%=tempProduct.getDescription() %>
                <span>最快明日送达</span><!-- 暂时不变? -->
            </div>
            <%
            String priceString = new DecimalFormat(pattern).format(tempProduct.getPrice());
            %>
            <div class="price"><span>￥<%=priceString %></span>/份</div>
            <div class="count">
                <img src="img/minus.png" alt="" class="minus">
                <input type="text" name="amount" class="number" value="<%=tempProduct.getCount() %>" maxlength="3">
                <img src="img/add.png" alt="" class="add">
            </div>
            <%
            double sumPrice = tempProduct.getPrice()*tempProduct.getCount();
            priceString = new DecimalFormat(pattern).format(sumPrice);
            %>
            <div class="summary">￥<%=priceString %></div>
            <a href="javascript:;" class="delete">删除</a>
        </div>
        <%
            }
        }
        %>
        <div class="submit">
            <div class="total">
                合计：&nbsp;<span>￥0.0</span>&nbsp;
            </div>
            <a href="index.jsp">返回主界面</a>
        </div>

    </div>
</body>
<script src="js/cartController.js"></script>

</html>