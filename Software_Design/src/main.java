import java.io.IOException;
import java.util.*;

public class main {
//test
	public static void main(String[] args) throws IOException {
		 System.out.println("PHONE / CALLINGPLAN RECOMMAND SYSTEM");
		 int choose_mode=choosemenu(2, "1: Seller, 2:Buyer");
		
		 if(choose_mode == 1)
		 {
			 Seller seller = new Seller();
		 }
		 else if(choose_mode == 2)
		 {
			 Buyer buyer = new Buyer();
			 //기종 검색, 요금제 검색 추가.

			 main_recommand();
		 }
	}
	
	public static int choosemenu(int count, String condition)
	{
		int value;
		
		while(true)
		{
			value=0;

			 System.out.print("Choose Mode ("+condition+") : ");

			 Scanner s = new Scanner(System.in);
			 value = s.nextInt();
			 
			 for(int i =1;i<=count;i++)
			 {
				 if(value == i)
				 {
					 System.out.println(" "); // 정리용
					 return value;
				 }
			 }
			 
			 System.out.println("Input Wrong Number");
		
		}		
	
	}
	
	public static void main_recommand()
	{
		System.out.println("무슨 일 때문에 오셨습니까?");
		
		int choose_mode_inBuyer=choosemenu(3, "1: 기종검색, 2:요금제검색, 3: 종료");
	 
		if(choose_mode_inBuyer == 1)//기종검색
		{
			Phone_Recommend();
			
			System.out.println("요금제검색을 하시겠습니까?");
			
			int choose_CallingPlan=choosemenu(2, "1: 네, 2: 아니오");
			
			if(choose_CallingPlan == 1)
			{
				CallingPlan_Recommend();
			}
			else if(choose_CallingPlan == 2)
			{
				
			}
		}
		else if(choose_mode_inBuyer == 2)//요금제검색
		{
			CallingPlan_Recommend();
		}
		else if(choose_mode_inBuyer == 3)
		{
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
		}
	}
	
	public static void Phone_Recommend()
	{
		Phone_List plist = new Phone_List(2);
		
		plist.print_Phone_List();
		
		while(true)
		{
			System.out.println("원하시는 항목의 번호를 입력해주세요");
			
			Scanner input = new Scanner(System.in);
			int inputNUM = input.nextInt();
			
			if(inputNUM == plist.Get_Total_Phone_List_Size())//항목 마지막의 잘 모르겠다를 선택했을 경우
			{
				
				/////////////////Recommend (기종상세검색 선택)
				
				break;
			}
			else if(inputNUM < plist.Get_Total_Phone_List_Size() && inputNUM >=0) //항목에서 기종을 선택했을 경우
			{
				Phone selectPhone = plist.Get_SelectPhone(inputNUM);
				
				System.out.println("선택하신 기종은 " + selectPhone.getMODEL_NAME() + " 입니다.\n");
			
				selectPhone.printPhoneINFO();
				
				break;
			}
			else
			{
				System.out.println("잘못된 입력입니다. 다시 선택해주세요");
			}
		}
	}
	
	public static void CallingPlan_Recommend()
	{
		CallingPlan_List plist = new CallingPlan_List(2);
		
		plist.print_CallingPlan_List();

		while(true)
		{
			System.out.println("원하시는 항목의 번호를 입력해주세요");
				
			Scanner input = new Scanner(System.in);
			int inputNUM = input.nextInt();
			
			if(inputNUM == plist.Get_Total_CallingPlan_List_Size())//항목 마지막의 잘 모르겠다를 선택했을 경우
			{
					
				/////////////////Recommend (요금제상세검색 선택)
				
				break;
			}
			else if(inputNUM < plist.Get_Total_CallingPlan_List_Size() && inputNUM >=0)//항목에서 요금제를 선택했을 경우
			{
				CallingPlan selectCallingPlan = plist.Get_SelectCallingPlan(inputNUM);
				
				System.out.println("선택하신 기종은 " + selectCallingPlan.getPAY_NAME() + " 입니다.\n");					
					
				selectCallingPlan.printCallingPlanINFO();
				
				break;
			}
			else
			{
				System.out.println("잘못된 입력입니다. 다시 선택해주세요");
			}
		}
	}
}
