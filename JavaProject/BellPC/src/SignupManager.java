import java.util.*;
public class SignupManager implements Manager {
	static Vector<Signup> vector = new Vector<Signup>();
	
	
	@Override
	public void show() {
		Iterator<Signup> it = vector.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
	}
	public void add(Signup s) {
		vector.add(s);
		
	}
	
	@Override
	public void remove(String s) {
		Vector<Signup> it = SignupManager.vector;
		
	}

	public void find(String name, String gender, String phone, Vector<Signup> vector2) { 
		for(int i = 0; i < vector2.size(); i++) {
			if(vector2.get(i).getName().equals(name) && vector2.get(i).getGender().equals(gender) && vector2.get(i).getPhone().equals(phone)) {
				System.out.println(name +"�� ���̵�� '" + vector2.get(i).getId() + "' , ��й�ȣ�� '" + vector2.get(i).getPassWord() + "'�Դϴ�.");
			}
		}
	}
	public int check(String id, String password, Vector<Signup> vector2) {
		int a = -1;
		for(int i = 0; i<vector2.size(); i++) {
			if(vector2.get(i).getId().equals(id) && vector2.get(i).getPassWord().equals(password)) {
				System.out.println("���̵� Ȯ��, ��й�ȣ Ȯ��");
				System.out.println(vector2.get(i).getName() +"�� ȯ���մϴ�");
				a = i; 
				break;
			}
		}
		if(a== -1) { // false
			System.out.println("ȸ�� ��Ͽ� �������� �ʽ��ϴ�. ȸ������ ���� ���ֽʽÿ�."); 
			return -1;
		}else { // true
			return a;
		}
	}

	public void timeAdd(String id, Vector<Signup> vector2, int time, int fee) {		// �ð� �߰�, ���� �߰�
		for(int i = 0; i<vector2.size(); i++) {
			if(vector2.get(i).getId().equals(id)) {
				vector2.get(i).setTime(vector2.get(i).getTime()+ time);	// ���� �ִ� �ð� + time
				vector2.get(i).setFee(vector2.get(i).getFee()+ fee);	// ���� �ִ� ���� + fee
			}
		}
	}
	
	public void order(String pname, ArrayList<Product> product, Vector<Signup> vector2, String id, int stock) {	// �α��� �� ��ǰ �ֹ�
		boolean check = false;
		int price = 0;
		for(int i = 0; i<product.size(); i++) {
			if(product.get(i).getPname().equals(pname)) {
				check = true;
				price = product.get(i).getPrice();
				// �����ִ� ����� �Է¹��� ���� ���µ� 0���� �Ʒ��� ������
				if(product.get(i).getStock() - stock < 0) {
					System.out.println(product.get(i).getStock() + "���� �־� �Է°��� �ʰ��Դϴ�. �Ǵ� 0������ ��� �����ϴ�.");
					check = false;
				}
			}
		}
		if (check) {
			for(int i = 0;i<vector2.size(); i++) {
				if(vector2.get(i).getId().equals(id)) {
					vector2.get(i).setFee(vector2.get(i).getFee() + price * stock);
				}
			}
			for(int i = 0;i<product.size(); i++) {
				// �����ִ� ����� �Է¹��� ��� �� ���ֱ�
				if(product.get(i).getPname().equals(pname)) {
					product.get(i).setStock(product.get(i).getStock() - stock);
				}
			}
			System.out.println("��ǰ �ֹ��� �Ϸ� �Ǿ����ϴ�.");
		}
		else {
			System.out.println("�ش� ��ǰ�� �����ϴ�.");
		}
	}

	public void usageTime(String id, Vector<Signup> vector2) {	// �α��� �� ȭ��
		for(int i = 0; i<vector2.size(); i++) {
			if(vector2.get(i).getId().equals(id)) {
				System.out.println(vector2.get(i).getName() + "���� �� ����� " + vector2.get(i).getFee() + "�� �Դϴ�.");
				if(vector2.get(i).getTime() == 0) {
					System.out.println("���� ���� �ð��� �����ϴ�. �ð� ���� �� ����� �ֽñ� �ٶ��ϴ�.");
				}
				else {
					System.out.println(vector2.get(i).getName() + "���� ���� �ð��� " + vector2.get(i).getTime() + "�ð� ���ҽ��ϴ�.");
				}
			}
		}
	}

	public void remove(Vector<Signup> vector2, String memberId, String memberName) {	// ȸ�� ����
		for(int i = 0; i<vector2.size(); i++) {
			if(vector2.get(i).getId().equals(memberId) && vector2.get(i).getName().equals(memberName)) {
				vector2.remove(i);
			}
		}
	}

	public void feeSet(String name, Vector<Signup> vector2) {	// ��� ���
		boolean check = false;
		for(int i = 0; i < vector2.size(); i++) {
			if(vector2.get(i).getName().equals(name) && vector2.get(i).getFee() != 0 ) {
				check = true;
				System.out.println(name + "���� ����� " + vector2.get(i).getFee() + "�� �Դϴ�.");
				vector2.get(i).setFee(0);
			} else if(vector2.get(i).getName().equals(name) && vector2.get(i).getFee() == 0) { 	// ��ϵ� ȸ�� ����� 0���̸� ����ó��
				System.out.println("ȸ������ ��ϵ� ����� 0���Դϴ�.");
			}
		}
		
		if(check) {
			System.out.println("��� ����� �Ϸ� �Ǿ����ϴ�.");
		}
		else {
			System.out.println("�շϵ� ȸ���� �ƴմϴ�. �Ǵ� ��ϵ� ȸ���� ����� 0���Դϴ�.");
		}
	}

	// ���̵� �ߺ� Ȯ��
	public int doubleCheck(Vector<Signup> vector2, String id) {
		boolean check = false;
		for(int i = 0; i < vector2.size(); i++) {
			if(vector2.get(i).getId().equals(id)) {
				System.out.println("���̵� �ߺ��Ǿ����ϴ�. �ٸ� ���̵� �Է����ּ���.");
				check = true;
			}
		}
		
		if(check) {	// check�� true �̸� 1�� ��ȯ
			return -1;
		}
		else { // -1 �� ��ȯ
			return 1;
		}
		
	}
	// �ð� ����ϱ�
	public void use(String id, Vector<Signup> vector2, int time) {
		boolean check = false;
		for(int i = 0; i < vector2.size(); i++) {
			if(vector2.get(i).getId().equals(id)) {
				if (vector2.get(i).getTime() - time >= 0) {
					vector2.get(i).setTime(vector2.get(i).getTime() - time);
					check = true;
				}
				else {
					check = false;
				}
			}
		}
		if(check) {
			System.out.println("�ð� ����� �Ϸ�Ǿ����ϴ�.");
		}
		else {
			System.out.println("�ð� ������ ������ֽñ� �ٶ��ϴ�.");
		}
		
	}
}
