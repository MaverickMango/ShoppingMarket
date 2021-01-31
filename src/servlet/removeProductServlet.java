package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Cart;
import bean.Product;

/**
 * Servlet implementation class removeProductServlet
 */
public class removeProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");//获取当前购物车
		if (cart != null) {
			String[] id = request.getParameterValues("id");
//			for (String string : id) {
//				System.out.println(string + "-" + string.length());
//			}
			if (id[0].equals("all")) {
				cart.clearCart();
			}
			else {
				for (int i = 0; i < id.length; i++) {
					cart.removeProduct(id[i]);
				}
			}
		}
		else {
			System.out.print("没有获取到购物车");
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
