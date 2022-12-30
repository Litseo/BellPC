
public class Signup {
	private String name;		// �̸�
	private String id; 			// ID
	private String passWord;	// ��й�ȣ
	private String birthDay;	// ����
	private String gender;		// ����
	private String phone;		// �޴��� ��ȣ
	private int time;			// �ð�
	private int fee;			// ������� �� ���
	public Signup(String name, String id, String passWord, String birthDay, String gender, String phone, int time, int fee) {
		super();
		this.name = name;
		this.id = id;
		this.passWord = passWord;
		this.birthDay = birthDay;
		this.gender = gender;
		this.phone = phone;
		this.time = time;
		this.fee = fee;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	
	@Override
	public String toString() {
		return "Signup [name=" + name + ", id=" + id + ", passWord=" + passWord + ", birthDay=" + birthDay + ", gender="
				+ gender + ", phone=" + phone + ", time=" + time + ", fee=" + fee + "]";
	}
	
	
	
}
