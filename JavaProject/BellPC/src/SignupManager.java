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
				System.out.println(name +"의 아이디는 '" + vector2.get(i).getId() + "' , 비밀번호는 '" + vector2.get(i).getPassWord() + "'입니다.");
			}
		}
	}
	public int check(String id, String password, Vector<Signup> vector2) {
		int a = -1;
		for(int i = 0; i<vector2.size(); i++) {
			if(vector2.get(i).getId().equals(id) && vector2.get(i).getPassWord().equals(password)) {
				System.out.println("아이디 확인, 비밀번호 확인");
				System.out.println(vector2.get(i).getName() +"님 환영합니다");
				a = i; 
				break;
			}
		}
		if(a== -1) { // false
			System.out.println("회원 목록에 존재하지 않습니다. 회원가입 먼저 해주십시오."); 
			return -1;
		}else { // true
			return a;
		}
	}

	public void timeAdd(String id, Vector<Signup> vector2, int time, int fee) {		// 시간 추가, 가격 추가
		for(int i = 0; i<vector2.size(); i++) {
			if(vector2.get(i).getId().equals(id)) {
				vector2.get(i).setTime(vector2.get(i).getTime()+ time);	// 원래 있던 시간 + time
				vector2.get(i).setFee(vector2.get(i).getFee()+ fee);	// 원래 있던 가격 + fee
			}
		}
	}
	
	public void order(String pname, ArrayList<Product> product, Vector<Signup> vector2, String id, int stock) {	// 로그인 후 상품 주문
		boolean check = false;
		int price = 0;
		for(int i = 0; i<product.size(); i++) {
			if(product.get(i).getPname().equals(pname)) {
				check = true;
				price = product.get(i).getPrice();
				// 원래있던 재고에서 입력받은 값을 뺐는데 0보다 아래면 재고없음
				if(product.get(i).getStock() - stock < 0) {
					System.out.println(product.get(i).getStock() + "개가 있어 입력값이 초과입니다. 또는 0개여서 재고가 없습니다.");
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
				// 원래있던 재고에서 입력받은 재고 값 뺴주기
				if(product.get(i).getPname().equals(pname)) {
					product.get(i).setStock(product.get(i).getStock() - stock);
				}
			}
			System.out.println("상품 주문이 완료 되었습니다.");
		}
		else {
			System.out.println("해당 상품이 없습니다.");
		}
	}

	public void usageTime(String id, Vector<Signup> vector2) {	// 로그인 후 화면
		for(int i = 0; i<vector2.size(); i++) {
			if(vector2.get(i).getId().equals(id)) {
				System.out.println(vector2.get(i).getName() + "님의 총 요금은 " + vector2.get(i).getFee() + "원 입니다.");
				if(vector2.get(i).getTime() == 0) {
					System.out.println("현재 남은 시간이 없습니다. 시간 충전 후 사용해 주시길 바랍니다.");
				}
				else {
					System.out.println(vector2.get(i).getName() + "님의 남은 시간은 " + vector2.get(i).getTime() + "시간 남았습니다.");
				}
			}
		}
	}

	public void remove(Vector<Signup> vector2, String memberId, String memberName) {	// 회원 삭제
		for(int i = 0; i<vector2.size(); i++) {
			if(vector2.get(i).getId().equals(memberId) && vector2.get(i).getName().equals(memberName)) {
				vector2.remove(i);
			}
		}
	}

	public void feeSet(String name, Vector<Signup> vector2) {	// 요금 계산
		boolean check = false;
		for(int i = 0; i < vector2.size(); i++) {
			if(vector2.get(i).getName().equals(name) && vector2.get(i).getFee() != 0 ) {
				check = true;
				System.out.println(name + "님의 요금은 " + vector2.get(i).getFee() + "원 입니다.");
				vector2.get(i).setFee(0);
			} else if(vector2.get(i).getName().equals(name) && vector2.get(i).getFee() == 0) { 	// 등록된 회원 비용이 0원이면 예외처리
				System.out.println("회원님의 등록된 비용이 0원입니다.");
			}
		}
		
		if(check) {
			System.out.println("요금 계산이 완료 되었습니다.");
		}
		else {
			System.out.println("둥록된 회원이 아닙니다. 또는 등록된 회원의 비용이 0원입니다.");
		}
	}

	// 아이디 중복 확인
	public int doubleCheck(Vector<Signup> vector2, String id) {
		boolean check = false;
		for(int i = 0; i < vector2.size(); i++) {
			if(vector2.get(i).getId().equals(id)) {
				System.out.println("아이디가 중복되었습니다. 다른 아이디를 입력해주세요.");
				check = true;
			}
		}
		
		if(check) {	// check가 true 이면 1을 반환
			return -1;
		}
		else { // -1 을 반환
			return 1;
		}
		
	}
	// 시간 사용하기
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
			System.out.println("시간 사용이 완료되었습니다.");
		}
		else {
			System.out.println("시간 충전후 사용해주시길 바랍니다.");
		}
		
	}
}
