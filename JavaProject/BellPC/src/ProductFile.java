import java.io.*;
import java.util.*;
public class ProductFile {
	private String filename;
	private Scanner in = new Scanner(System.in);
	public ProductFile(String file) {
		this.filename = file;
	}

	

	public void delProduct(ArrayList<Product> products) {
		System.out.print("������ ��ǰ �Է� : ");
		String pname = in.next();
		int pos = findLocation(products, pname);
		if(pos != -1) {
			products.remove(pos);
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");
		}else {
			System.out.println("Can't find");
		}
	}
	public int findLocation(ArrayList<Product> products, String pname) {
		for(int i = 0;i<products.size();i++) {
			if(products.get(i).getPname().equals(pname)) {
				return i;
			}
		}
		return -1;
	}
	// ��ǰ �����ֱ�
	public void showAll(ArrayList<Product> products) {
		for(Product p :  products) {
			// ����� ���忡�� �����ֱ� ������ ���� ���� �������� ����.
			System.out.println("��ǰ�̸� : " + p.getPname() + ", ���� : " + p.getPrice() + "��");
		}
	}
	
	public void save(ArrayList<Product> products) {		// ��ǰ�� �߰� �Ǵ� ���� �� ���� �����
		try {
			FileWriter writer = new FileWriter(filename, false);
		    BufferedWriter buf = new BufferedWriter(writer);
			// ���Ͽ� ������ ����ϱ�
			for(Product product : products) {
				buf.write(product.getPname() + " ");
				buf.write(Integer.toString(product.getPrice()) + " ");
				buf.write(Integer.toString(product.getStock()));
				buf.newLine();
			}
			buf.close();
			//System.out.println("��ǰ ������ ���Ͽ� �����߽��ϴ�. ���ϸ� : " + filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// bellproduct ���� �б�
	public void load() { 
		try {
			String line = null;
			FileReader reader = new FileReader(filename);
			BufferedReader buf = new BufferedReader(reader);
			while((line = buf.readLine()) != null) {
				System.out.println(line);
			}
			buf.close();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(filename + "������ �������� �ʽ��ϴ�.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	// ��� �߰�
	public void stock(ArrayList<Product> product, String stockPname, int stock) {
		boolean check = false;
		for(int i = 0;i<product.size();i++) {
			if(product.get(i).getPname().equals(stockPname)) {
				// ���� �ִ� ��� + ��� �߰� ��
				product.get(i).setStock(product.get(i).getStock() + stock); 
				check = true;
			}
		}
		if(check) {
			System.out.println("��� �߰��� �Ϸ�Ǿ����ϴ�.");
		}
		else {
			System.out.println("�ش� ��ǰ�� �̸��� ���� ��� �߰��� ���� �ʾҽ��ϴ�.");
		}
	}


	// �Ȱ��� ��ǰ�� ������ �߰� �ȵǰ� üũ�ؼ� �ȵǰ� �ϱ�
	public int check(String pname, ArrayList<Product> product) {
		boolean check = false;
		for(int i = 0;i<product.size();i++) {
			if(product.get(i).getPname().equals(pname)) {
				System.out.println("�Ȱ��� �̸��� ��ǰ�� �ֽ��ϴ�. �ٽ� �Է��� �ֽʽÿ�.");
				check = true;
			}
		}
		if(check) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
