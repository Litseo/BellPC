
public class Product {
	private String pname; // �߰��� ��ǰ �̸�
	private int price; // ����
	private int stock; // ���
	public Product() {}
	public Product(String pname, int price, int stock) {
		super();
		this.pname = pname;
		this.price = price;
		this.stock = stock;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void printInfo() {
		System.out.println(pname + " " + price);
		
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Product [pname=" + pname + ", price=" + price + ", stock=" + stock + "]";
	}

}
