package bean;

public class Product {
	private String id;
	private String name;
	private String url;
	private String description;
	private double price;//��λС��!
	private int count = 0;//���ݿ��в����� �Ǿ�̬��Ϣ ��ҵ���߼����������Ϣ ��session�仯 ��СΪ1
	public Product() {
		//
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
