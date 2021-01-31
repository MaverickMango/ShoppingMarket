package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Product;

public class DBUtil {
	private static String databaseName="shoppingMarket";
    private static String username = "root";
    private static String password = "";
    
    public static String getDatabaseName() {
		return databaseName;
	}

	public static void setDatabaseName(String databaseName) {
		DBUtil.databaseName = databaseName;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		DBUtil.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		DBUtil.password = password;
	}

	/**
	 * 获取连接
	 */
	public static Connection getConn() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/" + databaseName
    			+ "?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, getUsername(), getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

	/**
	  * 插入记录
	 * @param p 用户表参数
     * @return 1 插入成功
	 */
    public static int insert(String account, String password) {
        Connection conn = getConn();
        int i = 0;
        String sql = "INSERT INTO `userinfo`(`account`, `password`) VALUES (?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, account);
            pstmt.setString(2, password);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    /**
     * 修改操作
     * @param p 参数
     * @return
     */
    public static int update(Product p) {
        Connection conn = getConn();
        int i = 0;
        String sql = "update productinfo set name='" + p.getName() + "',url='"+p.getUrl()+"',description='"+p.getDescription()+"' ,price='"+p.getPrice()+"'  where id='" + p.getId() + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 查询商品信息
     * @return
     */
    public static Product select(String id) {
    	Connection conn = getConn();
    	Product product = new Product();
        String sql = "SELECT * FROM `productinfo` WHERE id=?";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
//            	System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getDouble(5));
            	product.setId(rs.getString(1));
            	product.setName(rs.getString(2));
            	product.setUrl(rs.getString(3));
            	product.setDescription(rs.getString(4));
            	product.setPrice(rs.getDouble(5));
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    
    /**
     * 查询用户信息
     * @return 找到
     */
    public static String checkUser(String account) {
    	Connection conn = getConn();
    	String password = "";
		String sql = "SELECT * FROM `userinfo` WHERE `account`=?";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
       		pstmt.setString(1, account);
       		ResultSet rs = pstmt.executeQuery();
       		while (rs.next()) {
       			password = rs.getString(2);
       		}
       		pstmt.close();
       		conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
    }

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    public static int delete(String id) {
        Connection conn = getConn();
        int i = 0;
        String sql = "delete from productinfo where id='" + id + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
//            System.out.println("结果: " + i);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}