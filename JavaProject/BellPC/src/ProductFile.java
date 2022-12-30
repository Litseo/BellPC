import java.io.*;
import java.util.*;
public class ProductFile {
	private String filename;
	private Scanner in = new Scanner(System.in);
	public ProductFile(String file) {
		this.filename = file;
	}

	

	public void delProduct(ArrayList<Product> products) {
		System.out.print("삭제할 제품 입력 : ");
		String pname = in.next();
		int pos = findLocation(products, pname);
		if(pos != -1) {
			products.remove(pos);
			System.out.println("삭제가 완료되었습니다.");
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
	// 상품 보여주기
	public void showAll(ArrayList<Product> products) {
		for(Product p :  products) {
			// 사용자 입장에서 보여주기 때문에 남은 재고는 보여주지 않음.
			System.out.println("상품이름 : " + p.getPname() + ", 가격 : " + p.getPrice() + "원");
		}
	}
	
	public void save(ArrayList<Product> products) {		// 제품을 추가 또는 삭제 후 파일 덮어쓰기
		try {
			FileWriter writer = new FileWriter(filename, false);
		    BufferedWriter buf = new BufferedWriter(writer);
			// 파일에 데이터 기록하기
			for(Product product : products) {
				buf.write(product.getPname() + " ");
				buf.write(Integer.toString(product.getPrice()) + " ");
				buf.write(Integer.toString(product.getStock()));
				buf.newLine();
			}
			buf.close();
			//System.out.println("제품 데이터 파일에 저장했습니다. 파일명 : " + filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// bellproduct 파일 읽기
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
			System.out.println(filename + "파일이 존재하지 않습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	// 재고 추가
	public void stock(ArrayList<Product> product, String stockPname, int stock) {
		boolean check = false;
		for(int i = 0;i<product.size();i++) {
			if(product.get(i).getPname().equals(stockPname)) {
				// 원래 있던 재고 + 재고 추가 값
				product.get(i).setStock(product.get(i).getStock() + stock); 
				check = true;
			}
		}
		if(check) {
			System.out.println("재고 추가가 완료되었습니다.");
		}
		else {
			System.out.println("해당 제품의 이름이 없어 재고 추가가 되지 않았습니다.");
		}
	}


	// 똑같은 상품이 있으면 추가 안되게 체크해서 안되게 하기
	public int check(String pname, ArrayList<Product> product) {
		boolean check = false;
		for(int i = 0;i<product.size();i++) {
			if(product.get(i).getPname().equals(pname)) {
				System.out.println("똑같은 이름의 상품이 있습니다. 다시 입력해 주십시오.");
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
