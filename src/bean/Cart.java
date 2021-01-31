package bean;

//import java.util.ArrayList;
import java.util.Vector;

public class Cart {//�Թ��ﳵ���в�����ҵ��bean
	private Vector<Product> cartItems;
//	private ArrayList<Product> cart;
	public Cart() {
		cartItems = new Vector<Product>(120);//�������ﳵ��������120 ������
	}
	
	public Vector<Product> getCartItems() {
		return cartItems;
	}
	
	public boolean addProduct(Product product) {//���ﳵcart�����Ʒproduct true����ת���ɹ����ҳ��,false�����ʧ��
		Product oldProduct = null;//���ﳵ��ԭ�����ڵ���Ʒ
		if (product != null) {//��ȷ������Ʒ
//			System.out.println(cartItems.size());
			for (int i = 0; i < cartItems.size(); i++) {//������ǰ���ﳵ
				oldProduct = cartItems.get(i);
				if (oldProduct.getId().equals(product.getId())) {//ԭ�����и���Ʒ
					oldProduct.setCount(product.getCount());//������Ʒ����
					return true;
				}
			}//�����������ﳵ��Ʒ��
			cartItems.addElement(product);
			return true;
		}
		else {
			//δ�ܳɹ���ȡ��Ʒ
			return false;
		}
	}
	//���ҵ�ǰ���ﳵ��ĳ��Ʒ������ û����Ϊ0
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
	//���¹��ﳵ��ĳ��Ʒ������
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
	//ɾ�����ﳵ�е���Ʒ
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
	//��չ��ﳵ
	public void clearCart() {
		cartItems.clear();
	}
}
