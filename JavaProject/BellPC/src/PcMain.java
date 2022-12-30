import java.io.*;
import java.util.*;
public class PcMain {
	public static ArrayList<Product> product = new ArrayList<>();
	public static Vector<Signup> vector = new Vector<>(); // �ٽ� ���������� ���� �����Ͱ� �����ֵ��� ���ִ� vector
	public static Scanner in = new Scanner(System.in);
	public static MemberFile member = new MemberFile("Signup.txt");
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int menu;
		// ��ǰ ���Ͽ� �ִ� �� ArrayList�� ���ֱ�
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
			   System.out.println("File open error : "+filename+"�� ã�� �� �����ϴ�.");
			   e.printStackTrace();
			} catch (IOException e) {
			   e.printStackTrace();
			}
		// ȸ����� ���Ͽ� �ִ� �� vector�� ���ֱ�
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
			   System.out.println("File open error : "+filename+"�� ã�� �� �����ϴ�.");
			   e.printStackTrace();
			} catch (IOException e) {
			   e.printStackTrace();
			}
		
		while(true) {
			System.out.println("Bell Pc�� �������~");
			System.out.println("---------------------");
			System.out.println("1. ȸ������, ���̵� ��й�ȣ ã�� â���� ����");
			System.out.println("2. �α��� â���� �̵�");
			System.out.println("3. ������ ���");
			System.out.println("0. PC ����");
			System.out.println("---------------------");
			System.out.print("���� �޴� >> ");
			menu = in.nextInt();
			if(menu == 0) {
				System.out.println("Bell PC�� �̿��� �ּż� �����մϴ�.");
				break;
			}
			switch(menu) {
			case 1-> SignupMenu();
			case 2-> LogMenu();
			case 3-> RootMenu();
			}
		}
	
	}
	private static void MemberMenu() {	// �����ڸ��  ȸ������
		SignupManager manager = new SignupManager();
		while(true) {
		System.out.println("--------ȸ�����--------");
		System.out.println("1. ȸ�� ���");
		System.out.println("2. ȸ�� ����");
		System.out.println("3. �ð� ����");
		System.out.println("0. ������ Ȩ���� ����");
		System.out.print(">> ");
		int select = in.nextInt();
		switch(select) {
			case 1:
				System.out.println("ȸ�� ����Դϴ�.");
				member.load();
				//member.showAll(vector);
				break;
			case 2:
				System.out.print("������ ȸ���� �̸� : ");
				String memberName = in.next();
				System.out.print("������ ȸ���� ���̵� : ");
				String memberId = in.next();
				manager.remove(vector, memberId, memberName);
				member.save(vector);
				break;
			case 3:
				System.out.println("------------------------------");
				for(int i = 0 ; i<vector.size(); i++) {
					System.out.println(vector.get(i).getName() + "���� �ð� : " + vector.get(i).getTime());
					System.out.println(vector.get(i).getName() + "���� ��� : " + vector.get(i).getFee());
					System.out.println("------------------------------");
				}
				
			case 0:
				return;
			default:
				System.out.println("�� �� �Է��ϼ̽��ϴ�.");
				break;
			}
		}
	}

	private static void LogMenu() {		// �α��� �޴�
		SignupManager manager = new SignupManager();
		while(true) {
			System.out.print("���̵� : ");
			String id = in.next();
			System.out.print("��й�ȣ : ");
			String password = in.next();
			int index = manager.check(id, password, vector);
			if(index == -1) {
				break;
			}
			else {
				while(true) {
				manager.usageTime(id, vector);
				System.out.println("1. ��ǰ �ֹ�");
				System.out.println("2. �ð� �߰�");
				System.out.println("3. �ð� ��� �ϱ�");
				System.out.println("0. �ý��� ����");
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
							System.out.print("�ð� ��� : ");
							int time = in.nextInt();
							manager.use(id, vector, time);
							member.save(vector);
							break;
						case 0:
							System.exit(0);
						default:
							System.out.println("�� �� �Է��ϼ̽��ϴ�.");
							break;
					}
				}
			}
		}
	}

	private static void Order(String id) {	// ��ǰ �ֹ�
		ProductFile manager = new ProductFile("bellpcproduct.txt");
		SignupManager manager2 = new SignupManager();
		while(true) {
			System.out.println("******** Bell Pc ��ǰ �ֹ� **��******");
			System.out.println("1. ��ǰ ����");
			System.out.println("2. �ֹ� �ϱ�");
			System.out.println("0. ��ǰâ ����");
			System.out.print(">> ");
			int select = in.nextInt();
			switch(select) {
			case 1:
				manager.showAll(product);
				break;
			case 2:
				System.out.print("��ǰ �̸� : ");
				String pname = in.next();
				System.out.print("��ǰ ���� : ");
				int stock = in.nextInt();
				manager2.order(pname, product, vector, id, stock);
				member.save(vector);
				manager.save(product);
				break;
			case 0:
				return;
			default:
				System.out.println("�� �� �Է��ϼ̽��ϴ�.");
				break;
			}
		}
	}
	private static void Time(String id) {	// �ð� ����
		SignupManager manager = new SignupManager();
		System.out.println("1. 1�ð� 1000��");
		System.out.println("2. 2�ð� 2000��");
		System.out.println("3. 5�ð� 4500��");
		System.out.println("4. 7�ð� 6000��");
		System.out.println("5. 10�ð� 9000��");
		System.out.println("0. �ð� ���� â �ݱ�");
		System.out.print(">> ");
		int select2 = in.nextInt();
		switch(select2){
			case 1:
				manager.timeAdd(id, vector, 1, 1000);
				System.out.println("�ð��� �����Ǿ����ϴ�.");
				member.save(vector);
				break;
			case 2:
				manager.timeAdd(id, vector, 2, 2000);
				System.out.println("�ð��� �����Ǿ����ϴ�.");
				member.save(vector);
				break;
			case 3:
				manager.timeAdd(id, vector, 5, 4500);
				System.out.println("�ð��� �����Ǿ����ϴ�.");
				member.save(vector);
				break;
			case 4:
				manager.timeAdd(id, vector, 7, 6000);
				System.out.println("�ð��� �����Ǿ����ϴ�.");
				member.save(vector);
				break;
			case 5:
				manager.timeAdd(id, vector, 10, 9000);
				System.out.println("�ð��� �����Ǿ����ϴ�.");
				member.save(vector);
				break;
			case 0:
				return;
			default:
				System.out.println("�� �� �Է��ϼ̽��ϴ�.");
				break;
		}
		
	}
	private static void RootMenu() {	// ������ ���
		System.out.print("������ ��й�ȣ�� �Է����ּ��� : ");
		String rootpassword = in.next();
		if(rootpassword.equals("root")) {
			SignupManager signManager = new SignupManager();
			ProductFile manager = new ProductFile("bellpcproduct.txt");
			while(true) {
					System.out.println("--------�����ڸ��--------");
					System.out.println("1. ȸ�� ����");
					System.out.println("2. ��ǰ ���");
					System.out.println("3. ��ǰ �߰�");
					System.out.println("4. ��ǰ ����");
					System.out.println("5. ��� ����");
					System.out.println("6. ȸ�� �ǽù� ��� ���");
					System.out.println("0. ������ ��� ������");
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
						System.out.print("��ǰ �Է� : ");
						String pname = in.next();
						int check = manager.check(pname, product);
						if(check == -1) {
							break;
						}
						System.out.print("���� �Է� : ");
						int price = in.nextInt();
						product.add(new Product(pname, price, 0));	// ���� 0���� �߰�
						manager.save(product);
						break;
					case 4:
						manager.delProduct(product);
						manager.save(product);
						break;
					case 5:
						System.out.print("��� �߰��� ��ǰ : ");
						String stockPname = in.next();
						System.out.print("��� �߰� : ");
						int stock = in.nextInt();
						manager.stock(product, stockPname, stock);
						manager.save(product);
						break;
					case 6:
						System.out.print("�ǽù� ��� ���");
						System.out.print("ȸ�� �̸� : ");
						String name = in.next();
						signManager.feeSet(name, vector);
						member.save(vector);
						break;
					case 0: return;
					default:
						System.out.println("�� �� �Է��ϼ̽��ϴ�.");
						break;
					}
			}
		}
		else {
			System.out.println("������ ��й�ȣ�� ���� �ʽ��ϴ�.");
		}
	}


	public static void SignupMenu() {	// ȸ������ ���̵�, ��й�ȣ ã��
		SignupManager manager = new SignupManager();
		while(true) {
			System.out.println("1. ȸ������, 2. ���̵� ��й�ȣ ã��, 0. ��������");
			System.out.print(">> ");
			int select = in.nextInt();
			if(select == 0) break;
			switch(select) {
			case 1:
				System.out.print("�̸� : ");
				String name = in.next();
				System.out.print("���̵� : ");
				String id = in.next();
				// ���̵� �ߺ�Ȯ��
				int check = manager.doubleCheck(vector, id);
				if(check == -1) {	// �ߺ� Ȯ���� �Ǹ� ���� ��ȯ���� -1�̸� ����
					break;
				}
				System.out.print("��й�ȣ : ");
				String password = in.next();
				System.out.print("������� : ");
				String birth = in.next();
				System.out.print("���� : ");
				String gender = in.next();
				System.out.print("�޴��� ��ȣ : ");
				String phone = in.next();
				manager.add(new Signup(name, id,password, birth, gender, phone, 0, 0));
				vector.add(new Signup(name, id,password, birth, gender, phone, 0, 0)); // main vector ���� ������ �߰�
				member.addMember(name, id, password, birth, gender, phone, 0, 0);
				member.save(vector);
				System.out.println("ȸ������ �Ϸ�");
				break;
			case 2:
				System.out.print("�̸� : ");
				name = in.next();
				System.out.print("���� : ");
				gender = in.next();
				System.out.print("�޴��� ��ȣ : ");
				phone = in.next();
				manager.find(name,gender,phone, vector);
			}
		}
	}
}
