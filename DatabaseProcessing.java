import java.util.*;
import java.io.*;
public class DatabaseProcessing{
	
	static ArrayList<PeopleRecord> LoadData(String filename) throws Exception{//this method load every data for each person in class PeopleRecord,it returns an arraylist that contains all people's PeopleRecord
		ArrayList<PeopleRecord> data= new ArrayList<>();//create an arratlist to store data
		File file=new File(filename);     //open file
		FileReader fr= new FileReader(file); //read file
		BufferedReader br=new BufferedReader(fr);
		String line;
		while((line=br.readLine())!=null) { //read the file line by line
			String[] ddata=line.split(";"); //split each line's data by ";"
			PeopleRecord a=new PeopleRecord(); 
				a.GivenName=ddata[0];
				a.FamilyName=ddata[1];
				a.CompanyName=ddata[2];
				a.address=ddata[3];
				a.city=ddata[4];
				a.country=ddata[5];
				a.state=ddata[6];
				a.zip=ddata[7];
				a.phone1=ddata[8];
				a.phone2=ddata[9];
				a.email=ddata[10];
				a.web=ddata[11];
				a.birthday=ddata[12];
				data.add(a);// store the whole data of a person into PeopleRecord
				}
		br.close();//finish reading the file
		return data;//return the arraylist
	}
	static void getMostFrequentWords(int count, int len)throws Exception{// this method prints the top n words as required, it returns nothing but will print directly
		if(len<3) {
			throw new Exception("ShortLengthException");//if the len does not reach the requirement, throw shortlengthexception
		}
		ArrayList<PeopleRecord> data=LoadData("your_path/people.txt"); //an arraylist that store all people's data through class PeopleRecord
		 ArrayList<String> ans= new ArrayList<>();//create an arraylist for all the info that are needed to be processed
		 for(int i=0;i<data.size();i++) {
			 if(data.get(i)!=null) {
				 ans.add(data.get(i).GivenName);
				 ans.add(data.get(i).FamilyName);
				 ans.add(data.get(i).CompanyName);
				 ans.add(data.get(i).address);
				 ans.add(data.get(i).city);
				 ans.add(data.get(i).country);
				 ans.add(data.get(i).state); // add all the data into the arraylist
			 }
		 }
			ArrayList<String> AfterProcessing=new ArrayList<>();//create an arraylist to store data that is processing(all alphabet,len as required)
			for(int i=0;i<ans.size();i++) {
				String[] words=ans.get(i).split(" ");
				for(int j=0;j<words.length;j++) { //loop every word split by blank
					if(words[j].length()<len) continue; //ignore the word smaller than len
					boolean flag=true;
					for(int k=0;k<words[j].length();k++) {//loop every element of the word
						int character= (int) (words[j].charAt(k));
						if((character>=65&&character<=90)||(character>=97&&character<=122)) { //check whether is alphabet
							
						}
						else {
							flag=false;
						}
						
					}
					if(flag==true) AfterProcessing.add(words[j]);//input the filtered data into the new arraylist
				}
			}
			QuadraticHashMap<String,Integer> dict=new QuadraticHashMap<>();//create a haspmap to continue process data
			for(int i=0;i<AfterProcessing.size();i++) {
				dict.put(AfterProcessing.get(i));//put all data into the hashmap
			}
			String[] FinalAns=dict.process(count); //the array stores top n data as required
			for(int i=0;i<FinalAns.length;i++) {
				if(FinalAns[i]!=null) {
					System.out.println("The "+(i+1)+" most frequent word is: "+FinalAns[i]);//print out the top n words
				}

			}
			
		
	}
	static PeopleRecord[] search(ArrayList<PeopleRecord> data,String x,String y) {
		MyBST<MyBST.Node> tree1=new MyBST<>();//create a BST
		for(int i=0;i<data.size();i++) {
			tree1.insert(data.get(i),"name");//input data
		}
		
		PeopleRecord[] ans=tree1.search("James", "Miller");
		return ans;
	}
	static PeopleRecord[] sort(MyBST<MyBST.Node> tree1,String s) throws Exception{
		MyHeap<MyHeap.Node> heap1=new MyHeap<>();
		ArrayList<PeopleRecord> data=new ArrayList<>();
		tree1.getAllData(tree1.root, data);
		if(s.equals("name")) {
			for(int i=0;i<data.size();i++) {
			heap1.insert(data.get(i),"name");//input the data
		}
		int count2=heap1.HeapList.size();
		PeopleRecord[] ans=new PeopleRecord[count2];
		for(int i=0;i<count2;i++) {
			MyHeap.Node testa=heap1.remove("name");
			//put the data in descending order
			ans[i]=testa.val;
		}
		return ans;
		}
		else if(s.equals("Birthday")) {
			for(int i=0;i<data.size();i++) {
			heap1.insert(data.get(i),"Birthday");//input the data
		}
		int count2=heap1.HeapList.size();
		PeopleRecord[] ans=new PeopleRecord[count2];
		for(int i=0;i<count2;i++) {
			MyHeap.Node testa=heap1.remove("Birthday");
			//put the data in descending order
			ans[i]=testa.val;
		}
		return ans;
		}
		return null;
	}
	public static void main(String[] args) throws Exception{
		
		/* 1.this section check whether the loaddata is corrent*/
		ArrayList<PeopleRecord> data=LoadData("C:/Users/28621/eclipse-workspace/CS201 Final Project/src/people.txt");
		System.out.println("The datas are: ");
		for(int i=0;i<data.size();i++) {
			System.out.print("("+data.get(i).GivenName+","+data.get(i).FamilyName+") ");//take name as an example, it should print out all the name;
		}
		System.out.println();
		System.out.println();
		/*loaddata check complete*/
		
		
		/*2.this section check whether the BST can work*/
		MyBST<MyBST.Node> tree1=new MyBST<>();//create a BST according to name order
		for(int i=0;i<data.size();i++) {
			tree1.insert(data.get(i),"name");//input data
		}
		//check the method "getinfo" is correct or not,it should print out the height and number of node
		int[] info=tree1.getInfo();
		System.out.println("The height of name-order tree is: "+info[0]);
		System.out.println("The number of the node of name-order tree is: "+info[1]);
		System.out.println();
		System.out.println("The name-order tree structure through pre-order is: ");
		tree1.preOrder(tree1.root);
		System.out.println();
		System.out.println();
		//getinfo check complete//
		System.out.println("The search result of James Miller is: ");
		PeopleRecord[] a=search(data,"James","Miller");
		if(a!=null) {
					for(int i=0;i<a.length;i++) {
			System.out.print("("+a[i].GivenName+","+a[i].FamilyName+") ");//print out the search result of james miller
		}
		}
		else {
			System.out.print("Null");
		}
		int check1=0;
		for(int i=0;i<data.size();i++) {
			if(data.get(i).GivenName.equals("James")&&data.get(i).FamilyName.equals("Miller")) {check1++;}//through linear search, it should have 3 james miller
		}
		System.out.print(", the number of James Miller should be "+check1);
		System.out.println();//check the method "search" complete
		System.out.println();
		MyBST<MyBST.Node> tree2=new MyBST<>();//create a BST according to birthday order
		for(int i=0;i<data.size();i++) {
			tree2.insert(data.get(i),"Birthday");//input data
		}
		int[] info2=tree2.getInfo();
		System.out.println("The height of birthday-order tree is: "+info2[0]);
		System.out.println("The number of the node of birthday-irder tree is: "+info2[1]);
		System.out.println();
		System.out.println("The birthday-order tree structure through pre-order is: ");
		tree2.preOrderBirthday(tree2.root);
		System.out.println();
		System.out.println();
		//getinfo check complete//
		System.out.println();
		System.out.print("The people has the birthday of 02/14/1954 should be :");
		PeopleRecord[] b=tree2.searchBirthday("02/14/1954");
		if(b!=null) {
					for(int i=0;i<b.length;i++) {
			System.out.print("("+b[i].GivenName+" "+b[i].FamilyName+","+b[i].birthday+") ");//print out the search result of the certain birthday
		}
		}
		else {
			System.out.print("Null");
		}
		System.out.println();
		//check the method "search" is correct or not//
		
		/*BST check complete*/
		
		System.out.println();
		/*3.this section check sort is correct or not*/
		System.out.println("Check whether the data comes out in descending order(name): ");
		PeopleRecord[] check2=sort(tree1,"name");
		for(int i=0;i<check2.length;i++) {
			System.out.print("("+check2[i].GivenName+","+check2[i].FamilyName+") ");//check whether the data comes out in descending order(name)
		}
		System.out.println();
		System.out.println();
		System.out.println("Check whether the data comes out in descending order(Birthday): ");
		PeopleRecord[] check3=sort(tree1,"Birthday");
		for(int i=0;i<check3.length;i++) {
			System.out.print("("+check3[i].GivenName+","+check3[i].FamilyName+","+check3[i].birthday+") ");//check whether the data comes out in descending order(Birthday)
		}
		System.out.println();
		/*sort check complete*/
		
		
		/*4.this section check QuadraticHaspMap is correct or not*/
		QuadraticHashMap<String,PeopleRecord> QMap=new QuadraticHashMap<>();//create a new haspmap
		for(int i=0;i<data.size();i++) {
			QMap.put(data.get(i));//input the data
		}
		//check the method"search"
		System.out.println();
		System.out.println("Check if there is James Miller:");
		System.out.println(QMap.search("James","Miller"));//should print true since there exist james miller cause we just proved before
		System.out.println();
		System.out.println("Check if there is A A:");
		System.out.println(QMap.search("A","A"));//should print false because no such person
		//method "search" check complete
		System.out.println();
		//check the method "get"
		System.out.println("Check if there is James Miller and print out all people named this: ");
		PeopleRecord[] name1=QMap.get("James","Miller");//should get 3 james miller since we already know there are 3 james miller proved before
		for(int i=0;i<name1.length;i++) {
			System.out.print("("+name1[i].GivenName+","+name1[i].FamilyName+") ");//print out the get result
		}
		System.out.println();
		System.out.println();
		//check the method "get" complete
		
		//check the method "remove"
		System.out.println("Check if there is Butt:");
		System.out.println(QMap.search("James","Butt"));//prove that we have james butt now
		System.out.println();
		QMap.remove("James", "Butt");
		System.out.println("Check if there is Butt after remove:");
		System.out.println(QMap.search("James","Butt"));//prove that we don't have james butt now because we successfully removed it
		System.out.println();
		//check the method "remove" complete
		/*QuadraticHashMap check complete*/
		
		
		/*5.this section check the method "getMostFrequentWords"*/
		System.out.println("Check the top n words that meet the requirement: ");
		getMostFrequentWords(10, 3);//should print out as required
		
		
		
	}
}
class PeopleRecord implements Comparable<PeopleRecord>{
	 String GivenName,FamilyName,CompanyName,address, city, country, state, zip, phone1, phone2, email, web, birthday;
	 PeopleRecord(String GivenName, String FamilyName, String CompanyName, String address, String city, String country, String state, String zip, String phone1, String phone2, String email, String web, String birthday) {
		this.GivenName=GivenName;
		this.FamilyName=FamilyName;
		this.CompanyName=CompanyName;
		this.address=address;
		this.city=city;
		this.country=country;
		this.state=state;
		this.zip=zip;
		this.phone1=phone1;
		this.phone2=phone2;     // add all the parameters into the constructor and initialization them
		this.email=email;
		this.web=web;
		this.birthday=birthday;	
	}
	 PeopleRecord(){
		                       // an empty constructor 
	 }
	 public int compareTo(PeopleRecord a) {//name comparator
		 String GivenName1=GivenName.toLowerCase();  // change all the element into lower case
		 String GivenName2=a.GivenName.toLowerCase();
		 for(int i=0;i<GivenName1.length();i++) {
			 if(i==GivenName2.length()) return 1;   //  givenname1 completely equals to givenname2 
			 else {
				 if(((int) GivenName1.charAt(i))==((int) GivenName2.charAt(i))) continue;       // consider the givenname1 and givenname2 might overlap at the beginning and differ at the end, 
				 else {
					 if(((int) GivenName1.charAt(i))<((int) GivenName2.charAt(i))) return -1;  // compare every element of the given name
					 else return 1;
				 }
			 }
		 }
		 String FamilyName1=FamilyName.toLowerCase();
		 String FamilyName2=a.FamilyName.toLowerCase();
		 for(int i=0;i<FamilyName1.length();i++) {
			 if(i==FamilyName2.length()) return 1;
			 else {
				 if(((int) FamilyName1.charAt(i))==((int) FamilyName2.charAt(i))) continue;
				 else {
					 if(((int) FamilyName1.charAt(i))<((int) FamilyName2.charAt(i))) return -1;
					 else return 1;
				 }
			 }
		 }
		 return 0;  //if given name and family name are all the same
	 }
	 public int compareTo2(PeopleRecord a) {//birthday comparator
		 String[] Birthday1=birthday.split("/");
		 String[] Birthday2=a.birthday.split("/");
				 for(int j=0;j<4;j++) {
					 int char1= (int) (Birthday1[2].charAt(j));
					 int char2= (int) (Birthday2[2].charAt(j));
					 if (char1==char2) continue;
					 else if(char1<char2) {return 1;}
					 else {return -1;}
				 }
			 
				 for(int j=0;j<2;j++) {
					 int char1= (int) (Birthday1[0].charAt(j));
					 int char2= (int) (Birthday2[0].charAt(j));
					 if (char1==char2) continue;
					 else if(char1<char2) {return 1;}
					 else {return -1;}
				 }
				 for(int j=0;j<2;j++) {
					 int char1= (int) (Birthday1[1].charAt(j));
					 int char2= (int) (Birthday2[1].charAt(j));
					 if (char1==char2) continue;
					 else if(char1<char2) {return 1;}
					 else {return -1;}
				 }
		 
		 return 0;
	 }
}
class MyBST<Node>{
	Node root;
	int countsize=0;
	static class Node{
		PeopleRecord val;
		Node left;
		Node right;            //create basic unit for tree
		Node(PeopleRecord p){
			this.val=p;
		}
	}
	public void insert(PeopleRecord x,String s) {//insert data
		countsize++;
		root=insert(root,x,s);
	}
	private Node insert(Node root, PeopleRecord x,String s) {
		if (s.equals("name")) {
			if(root==null) {
				root=new Node(x);
				return root;           // check if root is empty, if root is empty, just insert into the root position
			}
			if(x.compareTo(root.val)==-1) root.left=insert(root.left,x,s);
			else if (x.compareTo(root.val)==1) root.right=insert(root.right,x,s);  //else using recursion to compare insert value to its parentnode
			else {root.left=insert(root.left,x,s);}  //  if the insert value equals to its parentnode, default insert is to its left
			return root;			
		}
		else if (s.equals("Birthday")) {
			if(root==null) {
				root=new Node(x);
				return root;           // check if root is empty, if root is empty, just insert into the root position
			}
			if(x.compareTo2(root.val)==-1) root.left=insert(root.left,x,s);
			else if (x.compareTo2(root.val)==1) root.right=insert(root.right,x,s);  //else using recursion to compare insert value to its parentnode
			else {root.left=insert(root.left,x,s);}  //  if the insert value equals to its parentnode, default insert is to its left
			return root;			
		}
		return null;

	}
	public void delete(PeopleRecord x,String s) {//delete data
		countsize--;
		root=delete(root,x,s);
	}
	private Node delete(Node root,PeopleRecord x,String s) {
		if(s.equals("name")) {
			if(root==null) return null;   
			if(x.compareTo(root.val)<0) root.left=delete(root.left,x,s);
			else if(x.compareTo(root.val)>0) root.right=delete(root.right,x,s);
			else {
				if(root.left==null) return root.right;
				else if(root.right==null) return root.left;
				Node minNode=findMin(root.right);
				root.val=minNode.val;
				root.right=delete(root.right,root.val,s);
		}
			return root;			
		}
		if(s.equals("Birthday")) {
			if(root==null) return null;   
			if(x.compareTo2(root.val)<0) root.left=delete(root.left,x,s);
			else if(x.compareTo2(root.val)>0) root.right=delete(root.right,x,s);
			else {
				if(root.left==null) return root.right;
				else if(root.right==null) return root.left;
				Node minNode=findMin(root.right);
				root.val=minNode.val;
				root.right=delete(root.right,root.val,s);
		}
			return root;			
		}
		return null;

}
	private Node findMin(Node n) {//find the minimum side of the BST
		while(n.left!=null) {
			n=n.left;
		}
		return n;
	}
	public PeopleRecord[] search(String x,String y) {
		PeopleRecord[] s=search(root,x,y);  // put all the record into array
		return s;
	}
	public PeopleRecord[] searchBirthday(String str) {
		PeopleRecord[] s=searchBirthday(root,str);
		return s;
	}
	private PeopleRecord[] searchBirthday(Node root,String s) {
		PeopleRecord temp= new PeopleRecord(null,null,null,null,null,null,null,null,null,null,null,null,s); //only consider the given name and family name
		PeopleRecord[] ans=new PeopleRecord[countsize];
		int c=countsize;
		int index=0;
		if(root==null) return null;
		while(c>=0) {
			c--; //loop util all the same record all found
			if(root.val.compareTo2(temp)==0) {ans[index]=root.val;index++;root=root.left;} //find all the record equal to temp and add all them into an array
			else if (root.val.compareTo2(temp)<0) root=root.right; //if temp is bigger than root serach the right tree
			else {root=root.left;}
			if(root==null) {
				break;
			}
		}
		if(ans[0]==null) return null;
		else{
			int count2=0;
			for(int i=0;i<ans.length;i++) {
				if(ans[i]!=null) count2++;
			}
			PeopleRecord[] FinalAns=new PeopleRecord[count2]; //check if there is anything null in the ans, and add all to another array after deleting null
			for(int i=0;i<count2;i++) {
				FinalAns[i]=ans[i];
			}
			return FinalAns;
		}
	}
	private PeopleRecord[] search(Node root,String x,String y) {
		PeopleRecord temp= new PeopleRecord(x,y,null,null,null,null,null,null,null,null,null,null,null); //only consider the given name and family name
		PeopleRecord[] ans=new PeopleRecord[countsize];
		int c=countsize;
		int index=0;
		if(root==null) return null;
		while(c>=0) {
			c--; //loop util all the same record all found
			if(root.val.compareTo(temp)==0) {ans[index]=root.val;index++;root=root.left;} //find all the record equal to temp and add all them into an array
			else if (root.val.compareTo(temp)<0) root=root.right; //if temp is bigger than root serach the right tree
			else {root=root.left;}
			if(root==null) {
				break;
			}
		}
		if(ans[0]==null) return null;
		else{
			int count2=0;
			for(int i=0;i<ans.length;i++) {
				if(ans[i]!=null) count2++;
			}
			PeopleRecord[] FinalAns=new PeopleRecord[count2]; //check if there is anything null in the ans, and add all to another array after deleting null
			for(int i=0;i<count2;i++) {
				FinalAns[i]=ans[i];
			}
			return FinalAns;
		}
	}
	public void preOrder(Node n) {
		System.out.print("("+n.val.GivenName+","+n.val.FamilyName+")-");
		Node Lnode=n.left;
		if(Lnode!=null) preOrder(Lnode);    // preorder traversal of BST
		Node Rnode=n.right;
		if(Rnode!=null) preOrder(Rnode);
	}
	public void preOrderBirthday(Node n) {
		System.out.print("("+n.val.GivenName+","+n.val.FamilyName+","+n.val.birthday+")-");
		Node Lnode=n.left;
		if(Lnode!=null) preOrder(Lnode);    // preorder traversal of BST
		Node Rnode=n.right;
		if(Rnode!=null) preOrder(Rnode);
	}
	public void getAllData(Node n,ArrayList<PeopleRecord> a){//return a arraylist that contains all data
		a.add(n.val);
		Node Lnode=n.left;
		if(Lnode!=null) getAllData(Lnode,a);    // preorder traversal of BST
		Node Rnode=n.right;
		if(Rnode!=null) getAllData(Rnode,a);
		
	}
	private int getHeight(Node root) {
		if(root==null) {
			return 0;
		}
		int left=getHeight(root.left);
		int right=getHeight(root.right);  //use recursion to lefttree and righttree height
		if (right>left) {
			return right+1;   // return the highest tree height
		 }else {
			return left+1;
		 }
	}
	public int[] getInfo() {
		int[] ans=new int[2];
		ans[0]=getHeight(root);//return the number of the node and height
		ans[1]=countsize;
		return ans;
	}
	}
class MyHeap<Node>{
	ArrayList<Node> HeapList=new ArrayList<>();
	public MyHeap() {
		
	}
	static class Node{
		PeopleRecord val;
		Node(PeopleRecord p){
			this.val=p;       //basic unit for MyHeap
		}
	}
	private int getParent(int index) throws Exception {
		if(index==0) throw new IllegalArgumentException("No parent");//get parent index
		return (index-1)/2;
	}
	private int getLChild(int index) {
		return index*2+2;//get left child index
	}
	private int getRChild(int index) {
		return index*2+1;//get right child index
	}
	public void insert(PeopleRecord a,String s) throws Exception{
		Node n=new Node(a);
		HeapList.add(n);//first add new data the the end
		if(s.equals("name")) {
			updateUp(HeapList.size()-1);
		}
		if(s.equals("Birthday")){
			updateUpBirthday(HeapList.size()-1);
		}// the update the heap
	}
	public Node remove(String s) {
		Node n=HeapList.get(0);
		HeapList.set(0, HeapList.get(HeapList.size()-1)); // swap the root with the last node
		HeapList.set(HeapList.size()-1, n);  
		HeapList.remove(HeapList.size()-1);  //remove the last node
		if(s.equals("name")) {
			updateDown(0);
		}
		if(s.equals("Birthday")) {
			updateDownBirthday(0);
		} // swiftdown the root
		return n;
	}
	private void updateDown(int i) {
		while(getLChild(i)<HeapList.size()) { //if there is child, siftdown
			int j=getLChild(i); //assume leftchild is bigger
			if(getRChild(i)<HeapList.size()&&HeapList.get(getRChild(i)).val.compareTo(HeapList.get(j).val)>=0) {
				j=getRChild(i); // the right one is bigger, than bigger child will be the right one
			}
			if(HeapList.get(i).val.compareTo(HeapList.get(j).val)>0) break; //achieve maxheap
			Node temp=HeapList.get(j);
			HeapList.set(j, HeapList.get(i));  // swipe bigger child and parent
			HeapList.set(i, temp);
			i=j;
		}
	}
	private void updateDownBirthday(int i) {
		while(getLChild(i)<HeapList.size()) { //if there is child, siftdown
			int j=getLChild(i); //assume leftchild is bigger
			if(getRChild(i)<HeapList.size()&&HeapList.get(getRChild(i)).val.compareTo2(HeapList.get(j).val)>=0) {
				j=getRChild(i); // the right one is bigger, than bigger child will be the right one
			}
			if(HeapList.get(i).val.compareTo2(HeapList.get(j).val)>0) break; //achieve maxheap
			Node temp=HeapList.get(j);
			HeapList.set(j, HeapList.get(i));  // swipe bigger child and parent
			HeapList.set(i, temp);
			i=j;
		}
	}
	private void updateUp(int i) throws Exception{
		while (i>0&&HeapList.get(i).val.compareTo(HeapList.get(getParent(i)).val)>0) {
			Node temp=HeapList.get(i);
			HeapList.set(i, HeapList.get(getParent(i)));  //if child node bigger than parent node, siftup
			HeapList.set(getParent(i), temp);
			i=getParent(i);  // renew the index
		}
	}
	private void updateUpBirthday(int i) throws Exception{
		while (i>0&&HeapList.get(i).val.compareTo2(HeapList.get(getParent(i)).val)>0) {
			Node temp=HeapList.get(i);
			HeapList.set(i, HeapList.get(getParent(i)));  //if child node bigger than parent node, siftup
			HeapList.set(getParent(i), temp);
			i=getParent(i);  // renew the index
		}
	}
	
}
class QuadraticHashMap<K,V>{
	static final int DEFAULT_CAPACITY=16;
	static final float LOAD_FACTOR=0.75f;
	int size;
	int capacity;
	int threshold;
	String[] keys;
	PeopleRecord[] values;
	int[] value;
	public QuadraticHashMap() {
		 size=0;
		 this.capacity=DEFAULT_CAPACITY;
		 threshold= (int) (DEFAULT_CAPACITY*LOAD_FACTOR);//to check the limit
		  keys=new String[capacity];
		  values=new PeopleRecord[capacity];
		  value=new int[capacity];
	 }
	 public int hash(PeopleRecord p) {
		 String hashName=p.GivenName+p.FamilyName;
		 return (hashName.hashCode()&Integer.MAX_VALUE)%capacity;  // avoid hash result to be negative
	 }
	 private int hash(String key) {
		 return (key.hashCode()&Integer.MAX_VALUE)%capacity;//get the hashvalue
	 }
	 private void enlarge() {
		 this.capacity*=2;
		 this.threshold=(int) (capacity*LOAD_FACTOR); // if the key reach the threshold, map enlarge (x2)
		 String[] newKeys=new String[capacity];
		 PeopleRecord[] newValues=new PeopleRecord[capacity];
		 int[] newValue=new int[capacity];
		 for(int i=0;i<keys.length;i++) {  //copy all the old keys, values into the new array
			 newKeys[i]=keys[i];
		 }
		 for(int i=0;i<values.length;i++) {
			 newValues[i]=values[i];
		 }
		 for(int i=0;i<value.length;i++) {
			 newValue[i]=value[i];
		 }
		 keys=newKeys;
		 values=newValues;
		 value=newValue;
		 
	 }
	 public void put(String s) {
		 if(search(s)==true) {
			 for(int i=0;i<keys.length;i++) {
				 if(keys[i]!=null) {
				 if(keys[i].equals(s)) { value[i]++;break;}	//put the data in the map			 
				 }
			 }
		 }
		 else {
			 int hashValue=hash(s);
			 int site=hashValue;
			 int quadraticIndex=1;
			 if(size>=threshold) enlarge();
			 while(keys[site]!=null) {
				 site=(site+quadraticIndex*quadraticIndex++)%capacity;//if occupied, the use quadratic method to find an another available place
			 }
			 keys[site]=s;
			 value[site]=1;
			 size++;
			 return;			 
		 }

	 }
	 public void put(PeopleRecord val){
		 int hashValue = hash(val);
		 int site=hashValue;
		 int quadraticIndex=1;
		 if(size>=threshold) enlarge();// enlarge the map if it's size reaches the threshold
		 while(keys[site]!=null) {
			 site=(site+quadraticIndex*quadraticIndex++)%capacity;  //hash until it find an empty space for it
		 }
		 keys[site] = val.GivenName+val.FamilyName; //store the data into array
		 values[site] = val;
		 size++;
		 return;}
	 public String[] process(int count) {
		 String[] newKeys=new String[keys.length];
		 int[] newValue=new int[value.length];
		 int num=count;
		 for(int i=0;i<num;i++) {
			 if(count==0) break;
			 int maxi=-1;
			 int index=-1;
			 for(int j=0;j<value.length;j++) {
				 if(value[j]>maxi&&value[j]!=-1) {
					 maxi=value[j]; //record the highest frequent word, and index
					 index=j;
				 }
			 }
			 for(int k=0;k<newValue.length;k++) {
				 if(newValue[k]==0) {
					 newValue[k]=maxi;
					 newKeys[k]=keys[index];//add most frequent word into array
					 count--;
					 break;
				 }
			 }
			 keys[index]=null;  //after put most frequent word into array, manually delete it
			 value[index]=-1;
		 }
		 return newKeys;
		 
		 
	 }
	 public PeopleRecord[] get(String x,String y) {
		 ArrayList<PeopleRecord> ans=new ArrayList<>();
		 if(search(x,y)==true) {//check whether key exist
			 for(int i=0;i<keys.length;i++) {
				 if(keys[i]!=null) {
					 if(keys[i].equals(x+y)) ans.add(values[i]); // if key exist,add the value to an new arraylist
				 }
			 }
		 }
		 PeopleRecord[] FinalAns=new PeopleRecord[ans.size()];
		 for(int i=0;i<ans.size();i++) { //change the data type
			 FinalAns[i]=ans.get(i);
		 }
		 return FinalAns;
	 }
	 public boolean search(String s) {
		 for(int i=0;i<keys.length;i++) {
			 if(keys[i]!=null) {
				 if(keys[i].equals(s)) return true; 
			 }
		 }
		 return false;
	 }
	 public boolean search(String x,String y) {
		 for(int i=0;i<keys.length;i++) {  //loop the keys array to find out whether the key exists
			 if(keys[i]!=null) {
				 if(keys[i].equals(x+y)) return true; 
			 }
		 }
		 return false;
	 }
	  public void remove(String x,String y) {
		  String key=x+y;
		  if (!search(x,y)) return; //search for key
		  int site=hash(key), quadraticIndex=1;
		  while (!key.equals(keys[site])) {
			  site=(site +quadraticIndex*quadraticIndex++)%capacity;       //search for index
		  }
		  keys[site]=null;  //manually remove the key and value after first find it, in order to remove all the record
		  values[site]=null;
		  for(int i=site;i<keys.length-1;i++) {
			  keys[site]=keys[site+1]; // the gaps in the map are filled by the data behind it
			  values[site]=values[site+1];
		  }
		  size--;
	     }}
