package week11;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1234L;
	//person attributes
	String name;
	String phoneNum;
	String DOB;
	String email;
	public int entryNum;
	
	public Person(String name, String phoneNum, String dOB, String email, int entryNum) {
		this.name = name;
		this.phoneNum = phoneNum;
		DOB = dOB;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person" + "\nName: " + name + "\nPhone Number: " + phoneNum + "\nDOB: " + DOB + "\nEmail: " + email;
	}

	
	
	
	
}
public class Test {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		ArrayList<Person> p = new ArrayList<Person>();
		String name, phoneNum, DOB, email;
		int choice;
		
		System.out.println("Please choose an option from the menu");
		System.out.println();
		//create menu
		do {
			System.out.println("1) Add information into a file.");
			System.out.println("2) Retrieve information from a file and display them.");
			System.out.println("3) Delete information.");
			System.out.println("4) Update information");
			System.out.println("5) Exit.");
			choice = kb.nextInt();
			
			switch(choice) {
				case 1: //add person info
					System.out.println("Enter an entry number");
					int entry = kb.nextInt();
					
					System.out.println("Enter a name: ");
					name = kb.nextLine();
					
					System.out.println("Enter a phone number: ");
					phoneNum = kb.nextLine();
					
					System.out.println("Enter a date of birth: ");
					DOB = kb.nextLine();
					
					System.out.println("Enter an email address");
					email = kb.nextLine();
					
					p.add(new Person(name, phoneNum, DOB, email, entry));
					
					//serialize
					try {
						writeToFile(p);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				break;
				case 2://retrieve info and display
					//deserialize
					try {
						readFile();
					} catch (ClassNotFoundException | IOException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3: //delete info
					System.out.println("Enter the entry number of the person you want to delete");
					int entryNum = kb.nextInt();
					Iterator<Person> itr = p.iterator();
					while(itr.hasNext()) {
						Person person = (Person)itr.next();
						if(person.entryNum == entryNum) {// if the entry number matches the number the user has input delete that entry
							itr.remove();
						}
					}
					
					System.out.println("File deleted");
					break;
				case 4: //update info
					System.out.println("Enter the entry number of the person you want to update");
					int entNum = kb.nextInt();
					 Iterator<Person> iterate = p.listIterator();
					while(iterate.hasNext()) {
						Person person = (Person)iterate.next();
						if(person.entryNum == entNum) {// if the entry number matches the number the user has input update that entry
							System.out.println("Enter an entry number");
							int newEntry = kb.nextInt();
							
							System.out.println("Enter a new name: ");
							String newName = kb.nextLine();
							
							System.out.println("Enter a new phone number: ");
							String newPhoneNum = kb.nextLine();
							
							System.out.println("Enter a new date of birth: ");
							String newDOB = kb.nextLine();
							
							System.out.println("Enter a new email address");
							String newEmail = kb.nextLine();
							
							((ListIterator<Person>) iterate).set(new Person(newName, newPhoneNum, newDOB, newEmail, newEntry));
							
						}
					}
						
			}
		}while(choice != 5);
		
	}
	
	//method to serialize
	public static void writeToFile(ArrayList<Person> p) throws  IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Person.bin"));
		
		objectOutputStream.writeObject(p);
		objectOutputStream.close();
	}

	//method to deserialize
	public static void readFile() throws IOException, ClassNotFoundException {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Person.bin"));
		
		Person name = (Person) objectInputStream.readObject();
		System.out.println(name);
		
		Person phoneNum = (Person) objectInputStream.readObject();
		System.out.println(phoneNum);
		
		Person DOB = (Person) objectInputStream.readObject();
		System.out.println(DOB);
		
		Person email = (Person) objectInputStream.readObject();
		System.out.println(email);
		
		objectInputStream.close();
	}
}
