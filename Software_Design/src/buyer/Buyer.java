package buyer;
import java.io.IOException;
import java.util.*;


public class Buyer {
	private String name;
	private int age;
	private String Phone;
	
	public Buyer()
	{
		Scanner keyboard;
		
		keyboard  = new Scanner(System.in);
		System.out.print("Name : ");
		setName(keyboard.nextLine());

		System.out.print("Age : ");
		setAge(keyboard.nextInt());
		
		keyboard.nextLine(); 	//Flush Buffer

		System.out.print("PhoneNumber : ");
		setPhone(keyboard.nextLine());

		
		System.out.println("�������� �̸� : "+getName()+"\n���� : "+getAge()+"\n�ڵ��� ��ȣ : "+getPhone() + "\n");
	}

	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getPhone() {
		return Phone;
	}


	public void setPhone(String phone) {
		Phone = phone;
	}



}