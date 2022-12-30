import java.io.*;
import java.util.*;
public class PcMain {
	public static ArrayList<Product> product = new ArrayList<>();
	public static Vector<Signup> vector = new Vector<>(); // 다시 실행했을때 전에 데이터가 남아있도록 해주는 vector
	public static Scanner in = new Scanner(System.in);
	public static MemberFile member = new MemberFile("Signup.txt");
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int menu;
		// 제품 파일에 있는 값 ArrayList로 값주기
		ProductFile manager = new ProductFile("bellpcproduct.txt"); 
		String filename = "bellpcproduct.txt";
		try {
			FileReader reader = new FileReader(filename);
			BufferedReader buf = new BufferedReader(reader);
			   String line;
			   while((line = buf.readLine()) != null) {
			       StringTokenizer tokenizer = new StringTokenizer(line, " ");
			       String pname = tokenizer.nextToken();
			       int price = Integer.parseInt(tokenizer.nextToken());
			       int stock = Integer.parseInt(tokenizer.nextToken());
			       //System.out.println(pname+ " " +price + " " + stock);
			       Product product2 = new Product(pname, price, stock);
			       product.add(product2);
			   }
			   buf.close();
			} catch (FileNotFoundException e) {
			   System.out.println("File open error : "+filename+"을 찾을 수 없습니다.");
			   e.printStackTrace();
			} catch (IOException e) {
			   e.printStackTrace();
			}
		// 회원목록 파일에 있는 값 vector로 값주기
		ProductFile manager2 = new ProductFile("Signup.txt");
		String filename2 = "Signup.txt";
		try {
			FileReader reader = new FileReader(filename2);
			BufferedReader buf = new BufferedReader(reader);
			   String line;
			   while((line = buf.readLine()) != null) {
			       StringTokenizer tokenizer = new StringTokenizer(line, " ");
			       String mname = tokenizer.nextToken();
			       String mid = tokenizer.nextToken();
			       String mpassword = tokenizer.nextToken();
			       String mg = tokenizer.nextToken();
			       String mbirth = tokenizer.nextToken();
			       String mphone = tokenizer.nextToken();
			       int time = Integer.parseInt(tokenizer.nextToken());
			       int fee = Integer.parseInt(tokenizer.nextToken());
			      // System.out.println(mname+ " " + mid + " " + mpassword + " " + mg + " " + mbirth + " "+ mphone + " " + time + " " + fee);
			       Signup member2 = new Signup(mname, mid, mpassword, mbirth, mg, mphone, time, fee);
			       vector.add(member2);
			   }
			   buf.close();
			} catch (FileNotFoundException e) {
			   System.out.println("File open error : "+filename+"을 찾을 수 없습니다.");
			   e.printStackTrace();
			} catch (IOException e) {
			   e.printStackTrace();
			}
		
		while(true) {
			System.out.println("Bell Pc에 어서오세요~");
			System.out.println("---------------------");
			System.out.println("1. 회원가입, 아이디 비밀번호 찾기 창으로 가기");
			System.out.println("2. 로그인 창으로 이동");
			System.out.println("3. 관리자 모드");
			System.out.println("0. PC 끄기");
			System.out.println("---------------------");
			System.out.print("메인 메뉴 >> ");
			menu = in.nextInt();
			if(menu == 0) {
				System.out.println("Bell PC를 이용해 주셔서 감사합니다.");
				break;
			}
			switch(menu) {
			case 1-> SignupMenu();
			case 2-> LogMenu();
			case 3-> RootMenu();
			}
		}
	
	}
	private static void MemberMenu() {	// 관리자모드  회원관리
		SignupManager manager = new SignupManager();
		while(true) {
		System.out.println("--------회원목록--------");
		System.out.println("1. 회원 목록");
		System.out.println("2. 회원 삭제");
		System.out.println("3. 시간 관리");
		System.out.println("0. 관리자 홈으로 가기");
		System.out.print(">> ");
		int select = in.nextInt();
		switch(select) {
			case 1:
				System.out.println("회원 목록입니다.");
				member.load();
				//member.showAll(vector);
				break;
			case 2:
				System.out.print("삭제할 회원의 이름 : ");
				String memberName = in.next();
				System.out.print("삭제할 회원의 아이디 : ");
				String memberId = in.next();
				manager.remove(vector, memberId, memberName);
				member.save(vector);
				break;
			case 3:
				System.out.println("------------------------------");
				for(int i = 0 ; i<vector.size(); i++) {
					System.out.println(vector.get(i).getName() + "님의 시간 : " + vector.get(i).getTime());
					System.out.println(vector.get(i).getName() + "님의 요금 : " + vector.get(i).getFee());
					System.out.println("------------------------------");
				}
				
			case 0:
				return;
			default:
				System.out.println("잘 못 입력하셨습니다.");
				break;
			}
		}
	}

	private static void LogMenu() {		// 로그인 메뉴
		SignupManager manager = new SignupManager();
		while(true) {
			System.out.print("아이디 : ");
			String id = in.next();
			System.out.print("비밀번호 : ");
			String password = in.next();
			int index = manager.check(id, password, vector);
			if(index == -1) {
				break;
			}
			else {
				while(true) {
				manager.usageTime(id, vector);
				System.out.println("1. 상품 주문");
				System.out.println("2. 시간 추가");
				System.out.println("3. 시간 사용 하기");
				System.out.println("0. 시스템 종료");
				System.out.print(">> ");
				int select = in.nextInt();
					switch(select) {
						case 1:
							Order(id);
							break;
						case 2:
							Time(id);
							break;
						case 3:
							System.out.print("시간 사용 : ");
							int time = in.nextInt();
							manager.use(id, vector, time);
							member.save(vector);
							break;
						case 0:
							System.exit(0);
						default:
							System.out.println("잘 못 입력하셨습니다.");
							break;
					}
				}
			}
		}
	}

	private static void Order(String id) {	// 상품 주문
		ProductFile manager = new ProductFile("bellpcproduct.txt");
		SignupManager manager2 = new SignupManager();
		while(true) {
			System.out.println("******** Bell Pc 상품 주문 **ㄱ******");
			System.out.println("1. 상품 보기");
			System.out.println("2. 주문 하기");
			System.out.println("0. 상품창 종료");
			System.out.print(">> ");
			int select = in.nextInt();
			switch(select) {
			case 1:
				manager.showAll(product);
				break;
			case 2:
				System.out.print("상품 이름 : ");
				String pname = in.next();
				System.out.print("상품 개수 : ");
				int stock = in.nextInt();
				manager2.order(pname, product, vector, id, stock);
				member.save(vector);
				manager.save(product);
				break;
			case 0:
				return;
			default:
				System.out.println("잘 못 입력하셨습니다.");
				break;
			}
		}
	}
	private static void Time(String id) {	// 시간 충전
		SignupManager manager = new SignupManager();
		System.out.println("1. 1시간 1000원");
		System.out.println("2. 2시간 2000원");
		System.out.println("3. 5시간 4500원");
		System.out.println("4. 7시간 6000원");
		System.out.println("5. 10시간 9000원");
		System.out.println("0. 시간 충전 창 닫기");
		System.out.print(">> ");
		int select2 = in.nextInt();
		switch(select2){
			case 1:
				manager.timeAdd(id, vector, 1, 1000);
				System.out.println("시간이 충전되었습니다.");
				member.save(vector);
				break;
			case 2:
				manager.timeAdd(id, vector, 2, 2000);
				System.out.println("시간이 충전되었습니다.");
				member.save(vector);
				break;
			case 3:
				manager.timeAdd(id, vector, 5, 4500);
				System.out.println("시간이 충전되었습니다.");
				member.save(vector);
				break;
			case 4:
				manager.timeAdd(id, vector, 7, 6000);
				System.out.println("시간이 충전되었습니다.");
				member.save(vector);
				break;
			case 5:
				manager.timeAdd(id, vector, 10, 9000);
				System.out.println("시간이 충전되었습니다.");
				member.save(vector);
				break;
			case 0:
				return;
			default:
				System.out.println("잘 못 입력하셨습니다.");
				break;
		}
		
	}
	private static void RootMenu() {	// 관리자 모드
		System.out.print("관리자 비밀번호를 입력해주세요 : ");
		String rootpassword = in.next();
		if(rootpassword.equals("root")) {
			SignupManager signManager = new SignupManager();
			ProductFile manager = new ProductFile("bellpcproduct.txt");
			while(true) {
					System.out.println("--------관리자모드--------");
					System.out.println("1. 회원 관리");
					System.out.println("2. 제품 목록");
					System.out.println("3. 제품 추가");
					System.out.println("4. 제품 삭제");
					System.out.println("5. 재고 관리");
					System.out.println("6. 회원 피시방 비용 계산");
					System.out.println("0. 관리자 모드 나가기");
					System.out.print(">> ");
					int select = in.nextInt();
					switch(select) {
					case 1: MemberMenu();
						break;
					case 2: 
						manager.load();
						//manager.showAll(product);
						break;
					case 3: 
						System.out.print("제품 입력 : ");
						String pname = in.next();
						int check = manager.check(pname, product);
						if(check == -1) {
							break;
						}
						System.out.print("가격 입력 : ");
						int price = in.nextInt();
						product.add(new Product(pname, price, 0));	// 재고는 0으로 추가
						manager.save(product);
						break;
					case 4:
						manager.delProduct(product);
						manager.save(product);
						break;
					case 5:
						System.out.print("재고 추가할 상품 : ");
						String stockPname = in.next();
						System.out.print("재고 추가 : ");
						int stock = in.nextInt();
						manager.stock(product, stockPname, stock);
						manager.save(product);
						break;
					case 6:
						System.out.print("피시방 비용 계산");
						System.out.print("회원 이름 : ");
						String name = in.next();
						signManager.feeSet(name, vector);
						member.save(vector);
						break;
					case 0: return;
					default:
						System.out.println("잘 못 입력하셨습니다.");
						break;
					}
			}
		}
		else {
			System.out.println("관리자 비밀번호가 맞지 않습니다.");
		}
	}


	public static void SignupMenu() {	// 회원가입 아이디, 비밀번호 찾기
		SignupManager manager = new SignupManager();
		while(true) {
			System.out.println("1. 회원가입, 2. 아이디 비밀번호 찾기, 0. 메인으로");
			System.out.print(">> ");
			int select = in.nextInt();
			if(select == 0) break;
			switch(select) {
			case 1:
				System.out.print("이름 : ");
				String name = in.next();
				System.out.print("아이디 : ");
				String id = in.next();
				// 아이디 중복확인
				int check = manager.doubleCheck(vector, id);
				if(check == -1) {	// 중복 확인이 되면 멈춤 반환값이 -1이면 멈춤
					break;
				}
				System.out.print("비밀번호 : ");
				String password = in.next();
				System.out.print("생년월일 : ");
				String birth = in.next();
				System.out.print("성별 : ");
				String gender = in.next();
				System.out.print("휴대폰 번호 : ");
				String phone = in.next();
				manager.add(new Signup(name, id,password, birth, gender, phone, 0, 0));
				vector.add(new Signup(name, id,password, birth, gender, phone, 0, 0)); // main vector 에도 데이터 추가
				member.addMember(name, id, password, birth, gender, phone, 0, 0);
				member.save(vector);
				System.out.println("회원가입 완료");
				break;
			case 2:
				System.out.print("이름 : ");
				name = in.next();
				System.out.print("성별 : ");
				gender = in.next();
				System.out.print("휴대폰 번호 : ");
				phone = in.next();
				manager.find(name,gender,phone, vector);
			}
		}
	}
}
