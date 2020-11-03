package com.capgemini.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.ContactInfo;
import com.capgemini.ContactRegistrationException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.opencsv.bean.CsvBindByName;

//UC15 - read and write json
public class AddressBookJSONService {

	// private static String ADDRESS_BOOK_CONTACT = "./AddressBookContacts.csv";
	private static String CONTACT_FILES = "./ContactsInfo.json";
	private static String fname;
	@CsvBindByName(column = "lname")
	private static String lname;
	private static String add;
	private static String state;
	private static String city;
	private static String zip;
	private static String phoneno;
	private static String email;

	public void readData() {

		try {
			Reader read = Files.newBufferedReader(Paths.get(CONTACT_FILES));
			JsonParser jsonparser = new JsonParser();
			Object obj = jsonparser.parse(read);
			JsonArray cList = (JsonArray) obj;
			for (Object o : cList) {
				System.out.println(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// reads json file

	public void writeData(List<ContactInfo> list) {
		Gson gsonObj = new Gson();
		String json = gsonObj.toJson(list);
		try (FileWriter file = new FileWriter("./AddressBookContacts.json")) {
			file.write(json);
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// write data into the file

	public void readDataWritten() {
		try {
			Reader read = Files.newBufferedReader(Paths.get("./AddressBookContacts.json"));
			JsonParser jsonparser = new JsonParser();
			Object obj = jsonparser.parse(read);
			JsonArray cList = (JsonArray) obj;
			System.out.println(cList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean FirstNameCheck(String firstName) throws ContactRegistrationException {
		Pattern p = Pattern.compile("^[A-Z]{1}[a-zA-Z]{2,}");
		Matcher m = p.matcher(firstName);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of first name !! please re-try!");
		return flag;
	}

	public static boolean LastNameCheck(String lastName) throws ContactRegistrationException {
		Pattern p = Pattern.compile("^[A-Z]{1}[a-zA-Z]{2,}");
		Matcher m = p.matcher(lastName);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of last name !! please re-try!");
		return flag;
	}

	public static boolean EmailCheck(String email) throws ContactRegistrationException {
		Pattern p = Pattern.compile("^[a-zA-Z0-9+_-]+([.][a-zA-Z0-9]+)*@([a-zA-Z0-9]+)([.][a-z]+)?[.][a-z]{2,}$");
		Matcher m = p.matcher(email);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of Email ID !! please re-try!");
		return flag;
	}

	public static boolean CityCheck(String city) throws ContactRegistrationException {
		Pattern p = Pattern.compile("^[A-Z]{1}[a-zA-Z]+");
		Matcher m = p.matcher(city);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of City !! please re-try!");
		return flag;
	}

	public static boolean AddressCheck(String add) throws ContactRegistrationException {
		Pattern p = Pattern.compile("[#.0-9a-zA-Z\s,-]+$");
		Matcher m = p.matcher(add);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of Address !! please re-try!");
		return flag;
	}

	public static boolean StateCheck(String state) throws ContactRegistrationException {
		Pattern p = Pattern.compile("^[A-Z]{1}[a-zA-Z]+");
		Matcher m = p.matcher(state);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of State !! please re-try!");
		return flag;
	}

	public static boolean ZIPCheck(String zip) throws ContactRegistrationException {
		Pattern p = Pattern.compile("[0-9]{6}");
		Matcher m = p.matcher(zip);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of ZIP !! please re-try!");
		return flag;
	}

	public static boolean MobileCheck(String mobile) throws ContactRegistrationException {
		Pattern p = Pattern.compile("[0-9]{2}[\\s][7-9][0-9]{9}");
		Matcher m = p.matcher(mobile);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of Mobile no. !! please re-try!");
		return flag;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AddressBookJSONService ad = new AddressBookJSONService();
		List<ContactInfo> cList = new LinkedList<ContactInfo>();
		boolean p = true;
		System.out.println(
				"Welcome to Address Book JSON Service! \n1.Read contacts in File \n2.Write contacts in File \n3.Exit");
		int choice = Integer.parseInt(sc.nextLine());
		switch (choice) {
		case 1:
			ad.readData();
			break;
		case 2:
			System.out.println("Enter the number of contacts you want to enter into Address book:");
			int no = Integer.parseInt(sc.nextLine());
			while (no > 0) {
				System.out.println("Add details of Contact:");
				while (true) {
					try {
						System.out.print("Enter first name: ");
						fname = sc.nextLine();
						if (FirstNameCheck(fname)) {
							break;
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
				}
				while (p) {
					try {
						System.out.print("Enter last name: ");
						lname = sc.nextLine();
						if (LastNameCheck(lname)) {
							break;
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
				}
				while (p) {
					try {
						System.out.print("Enter Address: ");
						add = sc.nextLine();
						if (AddressCheck(add)) {
							break;
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
				}
				while (p) {
					try {
						System.out.print("Enter State: ");
						state = sc.nextLine();
						if (StateCheck(state)) {
							break;
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
				}
				while (p) {
					try {
						System.out.print("Enter city: ");
						city = sc.nextLine();
						if (CityCheck(city)) {
							break;
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
				}
				while (true) {
					try {
						System.out.print("Enter ZIP: ");
						zip = sc.nextLine();
						if (ZIPCheck(zip)) {
							break;
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
				}
				while (true) {
					try {
						System.out.print("Enter Mobile no.: ");
						phoneno = sc.nextLine();
						if (MobileCheck(phoneno)) {
							break;
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
				}
				while (true) {
					try {
						System.out.print("Enter Email: ");
						email = sc.nextLine();
						if (EmailCheck(email)) {
							break;
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
				}
				ContactInfo con = new ContactInfo(fname, lname, add, city, state, zip, phoneno, email);
				cList.add(con);
				no--;
			}
			try {
				ad.writeData(cList);
				System.out.println("Contacts successfully written!");
				System.out.println("Do you want to read the contacts written into file ?");
				String ch = sc.nextLine();
				if (ch.equalsIgnoreCase("y")) {
					ad.readDataWritten();
				} else {
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		case 3:
			System.out.println("Thanks for using application!");
			break;
		}
	}

}
