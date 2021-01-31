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
 * ����Ʒ��ӵ����ﳵ
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
	 * �ύ�����Ʒ������ڴ˻�ȡ���ﳵ����ӳɹ������ҳ����ת
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		String id = request.getParameter("id");
//		System.out.println(id);
		//����product���� ���product��cart
		Product product = DBUtil.select(id);
		int count = cart.getItemCount(id) + 1;//ԭ��������n Ĭ�ϴ���1��
		product.setCount(count);
		if(cart.addProduct(product)) {
			//��ת���ɹ����ҳ��
			response.sendRedirect("successfullyAdded.jsp?id="+id);
		}
		else {
			//
			System.out.print("δ�ܳɹ����");
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
