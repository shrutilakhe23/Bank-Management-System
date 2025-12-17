import java.util.ArrayList;
public class AccountHolderTree 
{
		public void inorder(Node root)
		{
			if(root==null)
				return;
			inorder(root.left);
			System.out.print(root.accountNumber+ " ");
			inorder(root.right);
			
		}
		public Node creatNode(long accountNumber,int pin,String name,int age, long totalbalance,String date,long amount)
		{
			Node newnode = new Node();
			newnode.right=null;
			newnode.left=null;
			newnode.accountNumber=accountNumber;
			newnode.pin=pin;
			newnode.age=age;
			newnode.Name=name;
			newnode.totalBalance+=totalbalance;
			newnode.time.add(date);
			newnode.previous.add(amount);
			newnode.OpenDate=date;
			return newnode;
		}
		public Node insert(Node node,long accountNumber,int pin,String name,int age, long totalbalance,String date,long amount)
		{
			
			if(node==null)
			{
				return creatNode(accountNumber,pin,name,age,totalbalance,date, amount);			
			}
			if(accountNumber > node.accountNumber)
			{
				node.right=insert(node.right,accountNumber,pin,name,age,totalbalance,date, amount);
			}
			else
				node.left=insert(node.left,accountNumber,pin,name,age,totalbalance, date,amount);
			return node;
		}
		public Node search(Node root,long accountNumber)
		{
			Node current = root;
			if(root==null)
				return root;
			while(current!=null && current.accountNumber!=accountNumber)
			{
				if(current.accountNumber < accountNumber)
					current=current.right;
				else
					current=current.left;
			}
			return current;
		}
		
		
	

}

