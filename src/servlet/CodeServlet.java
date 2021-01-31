package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class CodeServlet
 * 生成验证码图片
 */
public class CodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("image/jpeg");
		Random random = new Random();
		String allCharString = "0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] veriCode = new char[5];
		BufferedImage myImage = new BufferedImage(90, 35, BufferedImage.TYPE_INT_RGB);//获取画布?
		Graphics pic = myImage.getGraphics(); //画笔)
		pic.setColor(Color.white);
		pic.fillRect(0, 0, 90, 35);
		pic.setColor(Color.red);
		Font myFont = new Font("黑体", Font.ITALIC, 20);
		pic.setFont(myFont);
		for (int i = 0; i < 5; i++) {//随机字符
			int position = random.nextInt(allCharString.length());
			veriCode[i] = allCharString.charAt(position);
			pic.drawChars(veriCode, i, 1, 18*i+3, 23);
		}
		for (int i = 0; i < 10; i++) {//随机线条
			Color myColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
			pic.setColor(myColor);
			pic.drawLine(random.nextInt(90), random.nextInt(35), random.nextInt(90), random.nextInt(35));
		}
		
		HttpSession session = request.getSession();//此时sessin才被创建,生命周期开始
		session.setAttribute("veriCode", String.valueOf(veriCode));//留存验证码的值
		
		ImageIO.write(myImage, "jpeg", response.getOutputStream());//输出到servlet
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
