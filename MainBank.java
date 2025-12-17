import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

class MainBank 
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("		'Welcome to Bank Managemnet System'     	");
		Main b1 = new Main();
		while(b1.noOfChoices)
		{
			b1.Option();
		}
				
	}

}

class BankDetails
{
	private String name;
	private int pin;
	private int age;
	public 	long AccountNumber;
	private long TotalBalance;
	private String time; // = new ArrayList<String>();
	private long previous; // = new ArrayList<Long>();
	private ArrayList<Long> account_num= new ArrayList<Long>();
	public BankDetails()
	{
		
		this.name = null;
		this.age=0;
		this.pin = 0;
		AccountNumber = 0;
		TotalBalance = 0;
		this.time = null;
		this.previous = 0;//new ArrayList<Long>();
	}
	public BankDetails(String name,int age, int pin, long accountNumber, long totalBalance, String time,long previous)
	{
		this.age=age;
		this.name = name;
		this.pin = pin;
		AccountNumber = accountNumber;
		TotalBalance = totalBalance;
		this.time = time;
		this.previous = previous;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getage() {
		return age;
	}
	public void setage(int age) {
		this.age = age;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public long getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		AccountNumber = accountNumber;
	}
	public long getTotalBalance() {
		return TotalBalance;
	}
	public void setTotalBalance(long totalBalance) {
		TotalBalance = totalBalance;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time=time;
	}
	public long getPrevious() {
		return previous;
	}
	public void setPrevious(long previous) {
		this.previous = previous;
	}
	public long generateAccountNumber()
	{
		Random a = new Random();
		int low = 10;
		int high = 100;
		long accountNo = a.nextInt(999999-100000) + 1000000;
		if(account_num.contains(accountNo))
			generateAccountNumber();
		else
		{
			account_num.add(accountNo);
		}
		return accountNo;
	}
}


class Main
{
	public boolean noOfChoices=true;
	
	AccountHolderTree b= new AccountHolderTree();
	Node root =new Node();
	Scanner sc=new Scanner(System.in);	
	//private void automaticAdd()
	//{
		//BankDetails BD1 = new BankDetails();
		///root=b.insert(root,BD1.generateAccountNumber(),8569,"Asumtosh Mohapatra",21,0,"10:30:52		19-07-2015",0);
		//root=b.insert(root,BD1.generateAccountNumber(),7978,"Nithin Thota",20,0,"08:09:05		0-02-1999",0);
		//root=b.insert(root,BD1.generateAccountNumber(),1978,"Rahul Yadav",60,0,"14:58:60		30-02-2001",0);
		
	//}
	private void OpenNewAccount()
	{
		BankDetails BD = new BankDetails();
		sc.nextLine();
		System.out.printf("\nEnter Your Name:	");
		BD.setName(sc.nextLine());
		System.out.printf("\nEnter 4 digit Pin:  ");
		BD.setPin(sc.nextInt());
		System.out.printf("Enter Age:  ");
		BD.setage(sc.nextInt());
		BD.setAccountNumber(BD.generateAccountNumber());	
		LocalDateTime d= LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern(" HH:mm:ss	dd-MM-yyyy");
		BD.setTime(d.format(format));
		//System.out.printf("\n Enter Amount to be deposited    ");
		long amount=0;//sc.nextLong();
		System.out.println("\nCreating Acoount.........  \n\n'Your Account Details:'");
		root=b.insert(root,BD.getAccountNumber(),BD.getPin(),BD.getName(),BD.getage(),BD.getTotalBalance(),BD.getTime(),amount);
		System.out.format("**********************\nName  "+BD.getName()+"\nAge   "+BD.getage()+"\nAccountNumber  "+BD.getAccountNumber()+"\nDate	"
		+BD.getTime()+"\n**********************\n");
		System.out.println("Thank You!");
		
	}
	private void check()
	{
		try
		{
			System.out.printf("\nEnter Account Number:  ");
			Long accountNo=sc.nextLong();
			Node current=b.search(root,accountNo);
			if(current==null)
				System.out.println("No Account Found");
			else
			{
				System.out.printf("\nEnter 4 Digita Pin:  ");
				int pin1=sc.nextInt();
				if(current.pin==pin1)
					{
						
						System.out.printf("Checking Balance........\n\ncTotal Balance = %s\n\n\n",current.totalBalance);
					}
				else
					{
						System.out.printf("\n 'Incorrect Pin' ");
					}
				
			}
		}
		catch(Exception e)
		{
			System.out.println("\n"+"Invalid Input. Enter Only Numbers");
		}
	}
	
	private void Deposite()
	{
		try
		{
			System.out.printf("\nEnter Account Number:  ");
			Long accountNo=sc.nextLong();
			Node current=b.search(root,accountNo);
			if(current==null)
				{
					System.out.println("No Account Found Please try again");
					Deposite();
				}
			else
			{
				System.out.printf("\nEnter Amount:  ");
				long amount=sc.nextLong();
				current.totalBalance+=amount;
				current.previous.add(amount);
				LocalDateTime d= LocalDateTime.now();
				DateTimeFormatter format = DateTimeFormatter.ofPattern(" HH:mm:ss	dd-MM-yyyy");
				current.time.add(d.format(format));
				System.out.println("Money Deposited Successfully");
			}
		}
		catch(Exception e)
		{
			System.out.println("\n"+"Invalid Input. Enter Only Numbers");
		}
	}
	
	private void Withdraw()
	{
		try
		{
			System.out.printf("\nEnter Account Number:  ");
			Long accountNo=sc.nextLong();
			Node current=b.search(root,accountNo);
			if(current==null)
				System.out.println("No Account Found");
			else
			{
				System.out.printf("\nEnter 4 Digita Pin:  ");
				int pin1=sc.nextInt();
				if(current.pin==pin1)
				{
					System.out.printf("\nEnter Amount:  ");
					long amount=sc.nextLong();
					if(amount <= current.totalBalance)
					{
						current.totalBalance-=amount;
						current.previous.add(-1*amount);
						LocalDateTime d= LocalDateTime.now();
						DateTimeFormatter format = DateTimeFormatter.ofPattern(" HH:mm:ss	dd-MM-yyyy");
						current.time.add(d.format(format));
						System.out.printf(amount+"  Deducted from your Account");
					}
					else
					{
						System.out.println("Insufficient Balance");
					}
				}
				else
				{
					System.out.printf("\n 'Incorrect Pin' ");
				}
				
			}
			
		}
		catch(Exception e)
		{
			System.out.println("\n"+"Invalid Input. Enter Only Numbers");
		}
			
	}
	
	private void PreviousTranstion()
	{
		try
		{

			System.out.printf("\nEnter Account Number:  ");
			Long accountNo=sc.nextLong();
			Node current=b.search(root,accountNo);
			if(current==null)
				{
					System.out.println("No Account Found");
					PreviousTranstion();
				}
			
			else
			{
				System.out.printf("\nEnter 4 Digita Pin:  ");
				int pin1=sc.nextInt();
				if(current.pin==pin1)
				{
					System.out.printf("Account Created on    "+current.OpenDate);
					System.out.printf("\n\n  Time    "+"	 Date   	"+ "   Amount\n");
					for(int i=0;i<current.previous.size();i++)
					{
						System.out.print(current.time.get(current.previous.size()-1-i)+"	    ");
						System.out.print(current.previous.get(current.previous.size()-1-i)+"\n");
					}
				}
				else
					System.out.printf("\n 'Incorrect Pin' ");
			}
		}
		catch(Exception e)
		{
			System.out.println("\n"+"Invalid Input. Enter Only Numbers");
		}
			
	}
	public void Exit()
	{
		System.out.println("Exiting");
		noOfChoices=false;
	}
	void Option()
	{
		System.out.println("\n\nChoose an Option\n********************\n");
		System.out.println("N: To Open New Account");
		System.out.println("V: View Balance");
		System.out.println("D: Deposit money");
		System.out.println("W: Withdraw");
		System.out.println("P: Previous Transaction");
		System.out.println("E: Exit\n");
		System.out.println("********************");
		System.out.print("Enter an Option:  ");
		char c=sc.next().charAt(0);
		c=Character.toUpperCase(c);
		switch(c)
		{
			
			case 'N':
			{
				OpenNewAccount();
				break;
			}
			case 'V':
			{
				check();
				break;
			}
			case 'D':
			{
				Deposite();
				break;
			}
			case 'W':
			{
				Withdraw();
				break;
			}
			case 'P':
			{
				PreviousTranstion();
				break;
			}
			case 'E':
			{
				Exit();
				break;
			}
			case 'I':
			{
				b.inorder(root);
				break;
			}
			default:
			{
				System.out.println("\n  'Choose correct option'  ");
				break;
			}
		}
		
	}
	
	
}



