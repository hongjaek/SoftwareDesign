package buyer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import callingplan.CallingPlan;
import callingplan.CallingPlan_List;
import phone.Phone;
import phone.Phone_List;

public class Recommand {
	Phone_List Reco_Phone = new Phone_List();
	Phone StandPhone = new Phone();
	Phone UserPhone = new Phone();
	CallingPlan_List Reco_Calling = new CallingPlan_List();
	CallingPlan StandCalling = new CallingPlan();
	CallingPlan UserCalling = new CallingPlan();
	private int[] arrPhone= new int[30];
	private int[] arrScore = new int[30];
	private int arrindex=0;
	private int Spec;
	//����Ʈ���� ������� ��ġ���� ���ѵ� ������ȯ���ϸ� 5���� ���ϱ����� ���̰� �����Ϸ� ���ʷ� ����Ѵ�.
	
	
	//PhoneStand vs PhoneUser	���
	
	public void Phone_Recommand(String Performance,String MF,String Price,String display,String Storage)
	{
		int i;
		UserPhone.setPERFORMANCE(Performance);
		UserPhone.setPRICE(Price);
		UserPhone.setDISPLAY(display);
		UserPhone.setSTORAGE(Storage);
			//UserPhone.setMANUFACTURE(MF);
		for(i=0;i<Reco_Phone.Get_Total_Phone_List_Size();i++)
		{
			Spec = 0;
			StandPhone = Reco_Phone.Get_SelectPhone(i);
			if(Double.parseDouble(StandPhone.getDISPLAY())==Double.parseDouble(UserPhone.getDISPLAY()))
				Spec++;
			if(Integer.parseInt(StandPhone.getPRICE())==Integer.parseInt(UserPhone.getPRICE()))
				Spec++;
			if(StandPhone.getPERFORMANCE()==UserPhone.getPERFORMANCE())
				Spec++;
			if(Integer.parseInt(StandPhone.getSTORAGE())==Integer.parseInt(UserPhone.getSTORAGE()))
				Spec++;
			//if(StandPhone.getMENUFACTURE()==UserPhone.getMANUFACTURE())
				//Spec++;
			if(Spec>2)
			{
				//3�̻��� ��ǰ���� �ϴ� ��������.
				arrScore[i]=Spec;
			}
					
		}
		sort(arrScore,5);
		System.out.println("��õ�ص帮�ڽ��ϴ�!");
		for(i=0;i<arrPhone.length;i++) {
			System.out.printf("���� ���� : ȭ��ũ�� > %10s��ġ\t��ǰ���� > %10s��\t�뷮ũ�� > %4sGB\t���� > %10s��\t������ > %10s\n",UserPhone.getDISPLAY(),UserPhone.getPERFORMANCE(),UserPhone.getSTORAGE(),UserPhone.getPRICE(),"apple");
			System.out.printf("��õ ���� : ȭ��ũ�� > %10s��ġ\t��ǰ���� > %10s��\t�뷮ũ�� > %4sGB\t���� > %10s��\t������ > %10s\n",Reco_Phone.Get_SelectPhone(i).getDISPLAY(),Reco_Phone.Get_SelectPhone(i).getPERFORMANCE(),Reco_Phone.Get_SelectPhone(i).getSTORAGE(),Reco_Phone.Get_SelectPhone(i).getPRICE(),"apple");
			i++;
			if(i==arrPhone.length)
				break;
			System.out.printf("��õ ���� : ȭ��ũ�� > %10s��ġ\t��ǰ���� > %10s��\t�뷮ũ�� > %4sGB\t���� > %10s��\t������ > %10s\n",Reco_Phone.Get_SelectPhone(i).getDISPLAY(),Reco_Phone.Get_SelectPhone(i).getPERFORMANCE(),Reco_Phone.Get_SelectPhone(i).getSTORAGE(),Reco_Phone.Get_SelectPhone(i).getPRICE(),"apple");
		}
			
			
		
		
	}
	public void Calling_Recommand(String CALL,String MESSAGE,String DATA,String PRICE)
	{
		int i;
		UserCalling.setCALL(CALL);
		UserCalling.setDATA(DATA);
		UserCalling.setMESSAGE(MESSAGE);
		UserCalling.setPRICE(PRICE);
			//UserPhone.setMANUFACTURE(MF);
		for(i=0;i<Reco_Calling.Get_Total_CallingPlan_List_Size();i++)
		{
			Spec = 0;
			StandCalling = Reco_Calling.Get_SelectCallingPlan(i);
			if(Integer.parseInt(StandCalling.getCALL())==Integer.parseInt(UserCalling.getCALL()))
				Spec++;
			if(Integer.parseInt(StandCalling.getPRICE())==Integer.parseInt(UserCalling.getPRICE()))
				Spec++;
			if(Integer.parseInt(StandCalling.getDATA())==Integer.parseInt(UserCalling.getDATA()))
				Spec++;
			if(Integer.parseInt(StandCalling.getMESSAGE())==Integer.parseInt(UserCalling.getMESSAGE()))
				Spec++;
		
			if(Spec>1)
			{
				//2�̻��� ��ǰ���� �ϴ� ��������.
				arrScore[i]=Spec;
			}
					
		}
		sort(arrScore,5);
		System.out.println("��õ�ص帮�ڽ��ϴ�!");
		for(i=0;i<arrPhone.length;i++) {
			System.out.printf("���� ����� : ��ȭ�� > %10s��\t���� > %5s��\t������ > %4sGB\t���� > %10s��\n",UserCalling.getCALL(),UserCalling.getMESSAGE(),UserCalling.getDATA(),UserCalling.getPRICE());
			System.out.printf("��õ ����� : ��ȭ�� > %10s��\t���� > %5s��\t������ > %4sGB\t���� > %10s��\n",Reco_Calling.Get_SelectCallingPlan(i).getCALL(),Reco_Calling.Get_SelectCallingPlan(i).getMESSAGE(),Reco_Calling.Get_SelectCallingPlan(i).getDATA(),Reco_Calling.Get_SelectCallingPlan(i).getPRICE());
			i++;
			if(Reco_Phone.Get_SelectPhone(i)==null)
				break;
			System.out.printf("��õ ����� : ��ȭ�� > %10s��\t���� > %5s��\t������ > %4sGB\t���� > %10s��\n",Reco_Calling.Get_SelectCallingPlan(i).getCALL(),Reco_Calling.Get_SelectCallingPlan(i).getMESSAGE(),Reco_Calling.Get_SelectCallingPlan(i).getDATA(),Reco_Calling.Get_SelectCallingPlan(i).getPRICE());
			
		}		
	}
	public void sort(int[] arr,int stand)
	{
		int i,j,AB=0;
		for(i=0;i<(stand/4)*3;i++)					//����Ȯ�� 15%
		{
			for(j=0;j<arr.length;j++)
			{
				if(stand == arr[i])
				{
					arrPhone[AB]=i;					//score������ ���� ������(index)�� ����ٰ�������.
					AB++;
					stand--;
				}
			}
			
		}	
	}
	/*public int[] sort(int[] arr)
	{
		int i,j,temp=0;
		for(i=0;i<arr.length;i++)
			for(j=i+1;j<arr.length;j++)
			{
				if(arr[i]<=arr[j])
				{
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		for(i=0;i<arr.length;i++)
			System.out.println(arr[i]);
		return arr;
	}*/
	
	
	
}