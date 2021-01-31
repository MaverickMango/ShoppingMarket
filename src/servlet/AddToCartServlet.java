package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Query;

import bean.Cart;
import bean.Product;
import util.DBUtil;

/**
 * Servlet implementation class AddToCartServlet
 * 将商品添加到购物车
 */
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 提交添加商品请求后在此获取购物车，添加成功后进行页面跳转
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		String id = request.getParameter("id");
//		System.out.println(id);
		//设置product属性 添加product到cart
		Product product = DBUtil.select(id);
		int count = cart.getItemCount(id) + 1;//原有数量加n 默认传递1个
		product.setCount(count);
		if(cart.addProduct(product)) {
			//跳转到成功添加页面
			response.sendRedirect("successfullyAdded.jsp?id="+id);
		}
		else {
			//
			System.out.print("未能成功添加");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
