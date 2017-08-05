package WuTangMemberList;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

//The Customer class is a template for every customer object
//that will be saved in a file called contacts.bin
//Each customer has name, sex, address, email, and phone
class Member implements Serializable{
 
 private String memberlastName;
 private String memberfirstName;
 private String memberphoneNumber;
 private String memberemail;
 
 //constructor
 Member(String lastName, String firstName,String phoneNumber, String email)
 {
  
  memberlastName=lastName;
  memberfirstName=firstName; 
  memberphoneNumber=phoneNumber;
  memberemail=email;  
  
 }
 //get customer name
 public String getLastName(){
  return memberlastName;
 }
 //get customer sex
 public String getFirstName(){
  return memberfirstName;
 }
 //get customer address
 public String getPhoneNumber(){
  return memberphoneNumber;
 }
 
 //get customer email
  public String getEmail(){
   return memberemail;
 }
 
 
 //print customer information
 public void printInfo(){
  System.out.println("Last Name:"+memberlastName+", First Name:"+memberfirstName+", "
  		+ "Phone Number:"+memberphoneNumber+", Email:"+memberemail);
 }
}

