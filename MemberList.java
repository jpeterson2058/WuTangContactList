
package WuTangMemberList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;
	
	
public class MemberList
{
 static TreeMap<String,Member> ls; 
 public static void main(String[] args){
    ls=readList(); //read all contacts and place them in the list
    int ch;
    char con='y';
    Scanner sc=new Scanner(System.in); //create scanner object to receive choice input
    
    while(con=='y'){
     showMenu(); //show menu
     System.out.println("Enter your choice:");
     ch=sc.nextInt();    
     switch(ch){
      case 1:viewAll();break;
      case 2:addToList();break;
      case 3:deleteFromList();break;
      case 4:try {
			readOldList();
		} catch (IOException e) {
			
			e.printStackTrace();
		} break;
      case 5:System.exit(0);break;
      default: System.out.println("Invalid choice");
      }
    
     try{
      //prompt for continuing the program
      InputStreamReader isr=new InputStreamReader(System.in);
      System.out.println("Press y to continue:");
      con=(char)isr.read();
     }catch(IOException ie){}
    }
    
   }
   
 public static void readOldList() throws IOException 
 {
	 BufferedReader reader = null;
	try {
		reader = new BufferedReader(new FileReader("ContactList.txt"));
	} catch (FileNotFoundException e) 
	
	{

		e.printStackTrace();
	}
     Map<String, String> map=new TreeMap<String, String>();
     String line="";
     while((line=reader.readLine())!=null)
     {
         map.put(getField(line),line);
     }
     reader.close();
     FileWriter writer = new FileWriter("UpdatedList.txt");
     for(String ls : map.values()){
         writer.write(ls);  
         writer.write('\n');
     }
     writer.close();
 }

 private static String getField(String line) {
     return line.split(" ")[0];//extract value you want to sort on
 }

  
 //The viewAll method displays all customers in the list
   public static void viewAll(){
    
    if(ls!=null){
     Set<String> set=ls.keySet(); //get all customer names
     //based these names all contacts can be retrieved
     Iterator iterator=(Iterator) set.iterator();
     while(iterator.next())
     {
      Member cu=ls.get(iterator.next());
      cu.printInfo();
      }
     }
    
}
   
 //The addToList method is able to add each customer to the list
   public static void addToList(){
    //If the ls null, allocate memory for it so it is ready to get the new item
    if(ls==null) ls=new TreeMap<String,Member>();
    try{
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter last name:");
    String lastName=br.readLine();
    System.out.println("Enter first name:");
    String firstname=br.readLine();
    System.out.println("Enter phone number:");
    String phoneNumber=br.readLine();
    System.out.println("Enter email:");
    String email=br.readLine(); 
    Member st=new Member(lastName,firstname,phoneNumber,email);
    ls.put(lastName,st); //add new customer to the list
    writeIt(ls); //save the update list
    }catch(IOException e){}
   }
   
   //The deleteFromList method is able to delete a contact when customer name 
   //is correctly input
   public static void deleteFromList(){
    if(ls!=null){
     int si=ls.size(); //number of contacts in the list before a contact is removed
     try{
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     System.out.println("Customer name:");
     String key=br.readLine();
     ls.remove(key); //remove the contact
     if(ls.size()<si){ //removing is successful
      writeIt(ls);
      System.out.println("The contact has been deleted.");
     }
     else
      System.out.println("Wrong customer name");
     }catch(IOException ie){}
     
     
    }
  }
  
   
   //Write the TreeMap object representing the contact list to the file
   public static void writeIt(TreeMap<String,Member> obj){
    try{
    FileOutputStream fos=new FileOutputStream("UpdatedContactList.txt");
    ObjectOutputStream oos=new ObjectOutputStream(fos);
    oos.writeObject(obj);
    oos.flush();
    oos.close();
    }catch(IOException ie){}
    
   }
   
   //The readList method has code to read all contacts from the file
   
   public static TreeMap<String,Member> readList(){
    TreeMap<String,Member> lObject=null;
    try{
    FileInputStream fis=new FileInputStream("NewContactList.txt");
    ObjectInputStream ois=new ObjectInputStream(fis);
    lObject=(TreeMap<String,Member>)ois.readObject();
    ois.close();
   
    }catch(Exception ie){}
    return lObject;
    
   }
   
    
   
   //This method display a list of choices
   public static void showMenu(){
    System.out.println("1. View all contacts");
    System.out.println("2. Add to contacts list");
    System.out.println("3. Remove from contacts list");
    System.out.println("4. Read list");
    System.out.println("5. Exit");
   }
   
}
  