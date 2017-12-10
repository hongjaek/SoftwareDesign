package callingplan;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

public class CallingPlan_List {
	private ArrayList<CallingPlan> total_CallingPlan_list;
	private ArrayList<CallingPlan> output_CallingPlan_list;
	private CallingPlan thinkCallingPlan;
	private String Path = "./CallingPlan/";
	
	public CallingPlan_List()
	{
		
	}
	
	public CallingPlan_List(int mode)
	{
		total_CallingPlan_list = new ArrayList<CallingPlan>();
		
		if (mode == 1)	//�Ǹ��ڿ��� �ҷ� ���� ��.
		{
			boolean check_dir;
		
			check_dir=CheckExistDir("CallingPlan");
			
			if(check_dir)
			{
				ReadFile_CallingPlanList();
			}
			else	//CallingPlan�̶�� ���� ���� �� ����.
			{
				File dir_phone = new File("CallingPlan");
				if(!dir_phone.mkdirs())
				{
					System.err.println("MKDIR Error");
				}	
			}
		}
		else if(mode == 2)	//�����ڿ��Լ� �ҷ� ���� ��. 
		{
			boolean check_dir;
			
			check_dir=CheckExistDir("CallingPlan");
			
			if(check_dir)
			{
				ReadFile_CallingPlanList();
			}
			else
			{
				System.out.println("����� ��� ������ �����ϴ�.");
			}
		}
		else if(mode == 3)	//��õ Ŭ�������� ������� ��.
		{
			
		}
		else
		{
			
		}
	}
	
	public CallingPlan inputData_CallingPlan(String str)//�ε����� �о� CallingPlanŬ������ ������ ����
	{
		CallingPlan currentCallingPlan = new CallingPlan();
		
		///////////�����дºκ�/////////////////////////////////////////////////////////////////////
		File file = new File(str);
		FileReader fr;
		BufferedReader input; 
		try
		{
			fr = new FileReader(file);
			input = new BufferedReader(fr);
			String s = null;
			s = input.readLine();
			currentCallingPlan.setPAY_NAME(s);
			s = input.readLine();
			currentCallingPlan.setMESSAGE(s);
			s = input.readLine();
			currentCallingPlan.setCALL(s);
			s = input.readLine();
			currentCallingPlan.setDATA(s);
			s = input.readLine();
			currentCallingPlan.setPRICE(s);
			input.close();
		}
		catch (IOException e)
		{
			  e.printStackTrace();
		}
		/////////////////////////////////////////////////////////////////////////////////////////
		
		return currentCallingPlan;
	}
	
	public void print_CallingPlan_List() //����� �̸� ������� ����ϰ� ��� �������� �󼼰˻������ϰ� �س���
	{
		int num;
		
		if(total_CallingPlan_list.isEmpty())
		{
			System.out.println("����� ��� ������ �����ϴ�.");
		}
		else
		{
			for(num=0;num < total_CallingPlan_list.size();num++)
			{
				CallingPlan printCallingPlan = total_CallingPlan_list.get(num);
				System.out.println(num + ". " + printCallingPlan.getPAY_NAME());
				num++;
			}
			System.out.println(num + ". �� �𸣰ڴ�.(�󼼰˻�)");
		}
	}
	
	public int Get_Total_CallingPlan_List_Size() //����� ��� ������ ����
	{
		return total_CallingPlan_list.size();
	}
	
	public CallingPlan Get_SelectCallingPlan(int num) //����� ��� �׸��� �ϳ� �����ؼ� ����
	{
		CallingPlan tempC = total_CallingPlan_list.get(num);
		return tempC;
	}
	
	public boolean CheckExistFile(String filename)
	{
		File file = new File(filename);
		
		boolean isExists = file.exists();
		
		return isExists;
	}
	
	public boolean CheckExistDir(String dirname)
	{
		File dir = new File(dirname);
		
		boolean isExists = dir.isDirectory();
		
		return isExists;
	}
	public void insert_CallingPlan(String File)
	{
		Scanner s;
		s = new Scanner(System.in);
		
		String PAY_NAME;	//index 1
		System.out.print("PAY_NAME : ");
		PAY_NAME = s.nextLine();
		String MESSAGE;	//index 2
		System.out.print("MESSAGE : ");
		MESSAGE = s.nextLine();
		String CALL;		//index 3
		System.out.print("CALL : ");
		CALL = s.nextLine();
		String DATA;			//index 4
		System.out.print("DATA : ");
		DATA = s.nextLine();
		String PRICE;		//index 5
		System.out.print("PRICE : ");
		PRICE = s.nextLine();
		
		try {
			// �� �������� NULL�� ���� ���н�Ŵ.
			
			FileOutputStream out = new FileOutputStream(Path+File);
			
			out.write(PAY_NAME.getBytes());
			out.flush();
			out.write('\n');
			out.flush();
			
			out.write(MESSAGE.getBytes());
			out.flush();
			out.write('\n');
			out.flush();
			
			out.write(CALL.getBytes());
			out.flush();
			out.write('\n');
			out.flush();
			
			out.write(DATA.getBytes());
			out.flush();
			out.write('\n');
			out.flush();
			
			out.write(PRICE.getBytes());
			out.flush();
			out.write('\n');
			out.flush();
			
			out.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
	public void ReadFile_CallingPlanList()
	{
		if(!CheckExistFile(Path + "CallingPlanIndex"))
		{
			File file = new File(Path + "CallingPlanIndex");
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			FileInputStream fin = new FileInputStream(Path + "CallingPlanIndex");
			Reader reader = new InputStreamReader(fin, "euc-kr"); 
			BufferedReader in = new BufferedReader(reader);
			
			char b;
			int chc;
			
			int BUFFER_SIZE = 1000;
			String serialNumber = null;
			String CallingPlanName = null;
			
			in.mark(BUFFER_SIZE);
			while((chc = in.read() )!= -1)
			{

				in.reset();
				while((b = (char) in.read()) != '\0')
				{
					serialNumber += b;
				}
				
				while((b = (char) in.read()) != '\0')
				{
					CallingPlanName += b;
				}
				in.mark(BUFFER_SIZE);
				
				if(serialNumber == null)
				{
					break;
				}
				
				total_CallingPlan_list.add(inputData_CallingPlan(Path+serialNumber));
				
				serialNumber = null;
				CallingPlanName = null;
			}
			
			fin.close();
			in.close();
			
		} catch (IOException e) {	//���翩�� Ȯ���ϰ� �ͼ� �� �� ����.
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<CallingPlan> getTotal_CallingPlan_list() {
		return total_CallingPlan_list;
	}

	public void setTotal_CallingPlan_list(ArrayList<CallingPlan> total_CallingPlan_list) {
		this.total_CallingPlan_list = total_CallingPlan_list;
	}

	public ArrayList<CallingPlan> getOutput_CallingPlan_list() {
		return output_CallingPlan_list;
	}

	public void setOutput_CallingPlan_list(ArrayList<CallingPlan> output_CallingPlan_list) {
		this.output_CallingPlan_list = output_CallingPlan_list;
	}

	public CallingPlan getThinkCallingPlan() {
		return thinkCallingPlan;
	}

	public void setThinkCallingPlan(CallingPlan thinkCallingPlan) {
		this.thinkCallingPlan = thinkCallingPlan;
	}
	
}