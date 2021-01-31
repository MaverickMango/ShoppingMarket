package bean;

//import java.util.ArrayList;
import java.util.Vector;

public class Cart {//对购物车进行操作的业务bean
	private Vector<Product> cartItems;
//	private ArrayList<Product> cart;
	public Cart() {
		cartItems = new Vector<Product>(120);//单个购物车容量上限120 可扩充
	}
	
	public Vector<Product> getCartItems() {
		return cartItems;
	}
	
	public boolean addProduct(Product product) {//向购物车cart添加商品product true则跳转到成功添加页面,false则添加失败
		Product oldProduct = null;//购物车中原本存在的商品
		if (product != null) {//正确传递商品
//			System.out.println(cartItems.size());
			for (int i = 0; i < cartItems.size(); i++) {//遍历当前购物车
				oldProduct = cartItems.get(i);
				if (oldProduct.getId().equals(product.getId())) {//原本就有该商品
					oldProduct.setCount(product.getCount());//重置商品数量
					return true;
				}
			}//否则新增购物车商品项
			cartItems.addElement(product);
			return true;
		}
		else {
			//未能成功获取商品
			return false;
		}
	}
	//查找当前购物车内某商品的数量 没有则为0
	public int getItemCount(String id) {
		if(cartItems.size() != 0) {
			Product product = null;
			for (int i = 0; i < cartItems.size(); i++) {
				product = cartItems.get(i);
				if (product.getId().equals(id))
					return product.getCount();
			}
		}
		return 0;
	}
	//更新购物车中某商品的数量
	public boolean setItemCount(String id, int count) {
		if (cartItems.size() != 0) {
			Product product = null;
			for (int i = 0; i < cartItems.size(); i++) {
				product = cartItems.get(i);
				if (product.getId().equals(id)) {
					product.setCount(count);
					return true;
				}
			}
		}
		return false;
	}
	//删除购物车中的商品
	public boolean removeProduct(String id) {
		Product product = null;
		for (int i = 0; i < cartItems.size(); i++) {
			product = cartItems.get(i);
			if (product.getId().equals(id)) {
				cartItems.remove(i);
				return true;
			}
		}
		return false;
	}
	//清空购物车
	public void clearCart() {
		cartItems.clear();
	}
}
