import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class MemberFile {
	private ArrayList<Signup> member;
	private String filename;
	private Scanner in = new Scanner(System.in);
	
	public MemberFile(String file) {
		filename = file;
		member = new ArrayList<>();
	}
	public void addMember(String id, String name, String password, String birth, String gender, String phone, int time, int fee) {
		member.add(new Signup(id, name, password, birth, gender, phone, time, fee));
	}
	
	public void showAll(Vector<Signup> vector2) {
		for(Signup vector : vector2) {
			System.out.println(vector);
		}
	}
	
	
	public void save(Vector<Signup> vector) {
		try {
			FileWriter writer = new FileWriter(filename, false);
		    BufferedWriter buf = new BufferedWriter(writer);
			// 파일에 데이터 기록하기
			for(Signup member : vector) {
				buf.write(member.getName() + " ");
				buf.write(member.getId() + " ");
				buf.write(member.getPassWord()+ " ");
				buf.write(member.getGender()+ " ");
				buf.write(member.getBirthDay()+ " ");
				buf.write(member.getPhone() + " ");
				buf.write(Integer.toString(member.getTime()) + " ");
				buf.write(Integer.toString(member.getFee()));
				buf.newLine();
			}
			buf.close();
			//System.out.println("제품 데이터 파일에 저장했습니다. 파일명 : " + filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void load() { // 데이터 읽기
		String line = null;
		try {
			FileReader reader = new FileReader(filename);
			BufferedReader bufReader = new BufferedReader(reader);
			while((line = bufReader.readLine()) != null) {
				System.out.println(line);
			}
			bufReader.close();
		}catch(FileNotFoundException e) {
			System.out.println("파일 열기에 실패 했습니다. 파일명 : " + filename);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
