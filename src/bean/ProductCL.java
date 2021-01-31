package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBUtil;

public class ProductCL {
	ArrayList<Product> allProducts = new ArrayList<>();
    Connection conn;
    public ArrayList<Product> getAllProducts() {
    	conn = DBUtil.getConn();
    	String sql = "select * from productinfo";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        	Product tempProduct;
            while (rs.next()) {
            	tempProduct = new Product();
            	tempProduct.setId(rs.getString(1));
            	tempProduct.setName(rs.getString(2));
            	tempProduct.setUrl(rs.getString(3));
            	tempProduct.setDescription(rs.getString(4));
            	tempProduct.setPrice(rs.getDouble(5));
            	allProducts.add(tempProduct);
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allProducts;
    }
}
