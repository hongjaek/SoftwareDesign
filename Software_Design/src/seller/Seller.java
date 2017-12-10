package seller;
import java.util.*;

import callingplan.CallingPlan_List;
import phone.Phone_List;

import java.io.*;

public class Seller {
	private String marketInfo;
	private String sellerInfo;
	private int businessNumber;
	private Phone_List phonelist;
	private CallingPlan_List callingplanlist;
	
	public Seller()
	{
		//���� ���� ���� Ȯ��.
		if(!CheckExistFile("SellerInfo"))
		{
			System.out.println("���ʽ����Դϴ�. �����Է��� �ʿ��մϴ�.");
			GetFileInfo();
		}
		else
		{
			Scanner s;
			s = new Scanner(System.in);
			
			try {
				FileInputStream fin = new FileInputStream("SellerInfo");
				Reader reader = new InputStreamReader(fin, "euc-kr"); 
				BufferedReader in = new BufferedReader(reader);
				
				char b;
				String ID = "", PASSWD = "";
				String inputID, inputPASSWD;
				String temp = "";
				
				while((b = (char) in.read()) != '\0')
				{
					ID += b;
				}
				
				while((b = (char) in.read()) != '\0')
				{
					PASSWD += b;
				}
				
				while(true)
				{
					/// ����ȭ �ʿ�
					System.out.print("���̵� : ");
					inputID = s.nextLine();
					System.out.print("��й�ȣ : ");
					inputPASSWD = s.nextLine();
					
					if(ID.equals(inputID) && PASSWD.equals(inputPASSWD))
						break;
					else
						System.out.println("Ʋ�Ƚ��ϴ�. �ٽ� �ѹ� �õ����ּ���");
				}
				
				while((b = (char) in.read()) != '\0')
				{
					temp += b;
				}

				setBusinessNumber(Integer.parseInt(temp));
				
				temp = "";
				while((b = (char) in.read()) != '\0')
				{
					temp += b;
				}

				setMarketInfo(temp);
				
				temp = "";
				while((b = (char) in.read()) != '\0')
				{
					temp += b;
				}
				
				setSellerInfo(temp);
				
				System.out.println("����� ��ȣ : " + getBusinessNumber());
				System.out.println("�븮�� ���� : " + getMarketInfo());
				System.out.println("�Ǹ��� ���� : " + getSellerInfo());
				in.close();
				
			} catch (IOException e) {	//���翩�� Ȯ���ϰ� �ͼ� �� �� ����.
				e.printStackTrace();
			}
			
			while(true)
			{
				int selectadmin = choosemenu(4,"1: Phone, 2: CallingPlan, 3: Market, 4: Exit");
				
				if(selectadmin ==1)
				{
					//Phone admin
					phonelist = new Phone_List(1);
					phoneAdmin();
				}
				else if(selectadmin ==2)
				{
					//CallingPlan admin
					callingplanAdmin();
				}
				else if(selectadmin == 3)
				{
					// Market admin, ID, PASSWD ���� ������ ���� ����. �� �޴��� Ŭ���ϰ� �Ǹ� SellerInfo ����� �ٽ� ����� �ȴ�.
					marketAdmin();
				}
				else if(selectadmin == 4)
				{
					System.exit(0);
				}
			}
			// Admin_mode
		}
	}
	
	public boolean CheckExistFile(String filename)
	{
		File file = new File(filename);
		
		boolean isExists = file.exists();
		
		return isExists;
	}

	public void GetFileInfo()
	{
		Scanner s;
		s = new Scanner(System.in);
		
		System.out.println("����� ID�� PASSWIORD�� �Է��ϼ���.");
		System.out.print("���̵� : ");
		String s_ID = s.nextLine();
		
		String s_PASS1, s_PASS2;
		
		while(true)
		{
			/// ����ȭ �ʿ�
			System.out.print("��й�ȣ : ");
			s_PASS1 = s.nextLine();
			System.out.print("��й�ȣ Ȯ��: ");
			s_PASS2 = s.nextLine();
			
			if(s_PASS1.equals(s_PASS2))
				break;
			else
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� �Է����ּ���");
		}
		
		System.out.print("����� ��ȣ�� �Է��ϼ���. : ");
		String temp = s.nextLine();
		int bn = Integer.parseInt(temp);
		setBusinessNumber(bn);
		
		System.out.print("�븮�� ������ �Է��ϼ���. : ");
		String minfo = s.nextLine();
		setMarketInfo(minfo);
		
		System.out.print("�Ǹ��� ������ �Է��ϼ���. : ");
		String sinfo = s.nextLine();
		setSellerInfo(sinfo);
		
		try {
			// �� �������� NULL�� ���� ���н�Ŵ.
			
			FileOutputStream out = new FileOutputStream("SellerInfo");
			
			out.write(s_ID.getBytes());
			out.flush();
			out.write(0x00);
			out.flush();
			
			out.write(s_PASS1.getBytes());
			out.flush();
			out.write(0x00);
			out.flush();
			
			out.write(Integer.toString(bn).getBytes());
			out.flush();
			out.write(0x00);	//NULL. NULL�� ���� ���� ������ �������� �Է�.
			out.flush();
			
			out.write(minfo.getBytes());
			out.flush();
			out.write(0x00);
			out.flush();
			
			out.write(sinfo.getBytes());
			out.flush();
			out.write(0x00);
			out.flush();
			
			out.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
			
	public int choosemenu(int count, String condition)
	{
		int value;
		Scanner s = new Scanner(System.in);
		
		while(true)
		{
			value=0;

			 System.out.print("Choose Menu ("+condition+") : ");

			
			 value = s.nextInt();
			 
			 for(int i =1;i<=count;i++)
			 {
				 if(value == i)
				 {
					 return value;
				 }
			 }
			 
			 System.out.println("Input Wrong Number");
		}	
	}
	
	public void phoneAdmin()	//���� �Է� admin
	{
		while(true)
		{
			int mode_phone=choosemenu(3,"1: Insert, 2: Modify, Delete, 3: Quit");
			
			if(mode_phone == 1)
			{
				System.out.println("Insert Phone");
				phonelist.insert_Phone();
			}
			else if(mode_phone == 2)
			{
				System.out.println("Modify, Delete Phone");
				phonelist.Modifyanddelete();
			}
			else if(mode_phone == 3)
			{
				try {
					phonelist.WriteFile_PhoneList();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Quit");
				break;
			}
		}
		//sgadgafdgdfs
	}
	
	public void callingplanAdmin()	//����� �Է� admin
	{
		while(true)
		{
			int mode_callingplan=choosemenu(3,"1: Insert, 2: Modify, Delete, 3: Quit");
			Scanner s = new Scanner(System.in);
			
			if(mode_callingplan == 1)
			{
				System.out.println("����� �Է��� �����ϼ̽��ϴ�.");
				String Payname, Message, Call, Data, Price;
				
				System.out.print("����� �� : ");
				Payname = s.nextLine();
				System.out.print("���ڷ� : ");
				Message = s.nextLine();
				System.out.print("��ȭ�� : ");
				Call = s.nextLine();
				System.out.print("������ : ");
				Data = s.nextLine();
				System.out.print("��� : ");
				Price = s.nextLine();
			}
			else if(mode_callingplan == 2)
			{
				
			}
			else if(mode_callingplan == 3)
			{
				System.out.println("Quit");
				break;
			}
		}
	}
	
	public void marketAdmin()
	{
		String s = "SellerInfo";
	    File f = new File(s);

	    if (f.delete()) {
	      System.out.println("Success Delete: " + s);
	    } else {
	      System.err.println("Fail Delete: " + s);
	    }
	    
	    GetFileInfo();

	}

	public int getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(int businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(String sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public String getMarketInfo() {
		return marketInfo;
	}

	public void setMarketInfo(String marketInfo) {
		this.marketInfo = marketInfo;
	}		
			
	
}