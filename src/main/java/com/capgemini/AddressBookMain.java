package com.capgemini;

import java.util.Scanner;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class AddressBookMain extends ContactInfo {
	// UC9- count by city or state
	private static Scanner sc;
	// public static ArrayList<ContactInfo> Addbook = new ArrayList<ContactInfo>();
	public static HashMap<String, ArrayList<AddressBookMain>> Directory = new HashMap<String, ArrayList<AddressBookMain>>();
	public static String field;
	public static String fname;
	public static String lname;
	public static String add;
	public static String city;
	public static String state;
	public static String zip;
	public static String phone;
	public static String email;
	public static String addname;
	public static ArrayList<AddressBookMain> addbook = new ArrayList<AddressBookMain>();

	public AddressBookMain(String FirstName, String LastName, String Email, String Address, String City, String State,
			String Zip, String phoneNo) {
		fname = FirstName;
		lname = LastName;
		email = Email;
		add = Address;
		city = City;
		state = State;
		zip = Zip;
		phone = phoneNo;
	}

	public String toString() {
		return fname + " " + lname + " " + add + " " + city + " " + state + " " + zip + " " + phone + " " + email;
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
		sc = new Scanner(System.in);
		System.out.println("Welcome to Address Book System!");
		System.out.println("-------------------------------");
		boolean p = true;
		// boolean con = true;
		// String choice = "";
		while (p) {
			System.out.println("Enter no. of address books you want to maintain: ");
			int m = Integer.parseInt(sc.nextLine());
			for (int k = 0; k < m; k++) {
				while (true) {
					System.out.println("Enter the name of the address book:");
					addname = sc.nextLine();
					int flag = 0;
					for (Map.Entry<String, ArrayList<AddressBookMain>> entry : Directory.entrySet()) {
						if (entry.getKey().equals(addname)) {
							flag++;
						}
					}
					if (flag == 0) {
						break;
					} else {
						System.out.println("Address book name already exists !! Try another.");
					}
				}
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
							phone = sc.nextLine();
							if (MobileCheck(phone)) {
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
					AddressBookMain ab = new AddressBookMain(fname, lname, email, add, city, state, zip, phone);
					addbook.add(ab);
					no--;
				}
				Directory.put(addname, addbook);
			}
			/*
			 * for (Map.Entry mapElement : Directory.entrySet()) { String key = (String)
			 * mapElement.getKey(); System.out.println("Address book " + key + " is :");
			 * List<ContactInfo> a = (List<ContactInfo>) mapElement.getValue();
			 * ab.showContact(); }
			 */
			System.out.println("Do you want to perform search?(Y/N)");
			String in = sc.nextLine();
			if (in.equalsIgnoreCase("y")) {
				System.out.println("Enter 8 to perform search in a city \nEnter 9 to perform serach in a state ");
				int k = Integer.parseInt(sc.nextLine());
				if (k == 8) {
					System.out.println("Enter the name of the city you want to search for");
					String searchCity = sc.nextLine();
					for (Map.Entry<String, ArrayList<AddressBookMain>> entry : Directory.entrySet()) {
						for (AddressBookMain a : entry.getValue()) {
							if (a.city.equals(searchCity)) {
								System.out.println(a);
							}
						}
					}
				}
				if (k == 9) {
					System.out.println("Enter the name of the state you want to search for");
					String searchState = sc.nextLine();
					for (Map.Entry<String, ArrayList<AddressBookMain>> entry : Directory.entrySet()) {
						for (AddressBookMain a : entry.getValue()) {
							if (a.state.equals(searchState)) {
								System.out.println(a);
							}
						}
					}
				}
			} else {
				break;
			}
			HashMap<String, ArrayList<AddressBookMain>> cityContacts = new HashMap<>();
			for (Map.Entry<String, ArrayList<AddressBookMain>> entry : Directory.entrySet()) {
				for (AddressBookMain a : entry.getValue()) {
					String cityName = a.city;
					for (Map.Entry<String, ArrayList<AddressBookMain>> e : Directory.entrySet()) {
						for (AddressBookMain add : e.getValue()) {
							if (add.city.equalsIgnoreCase(cityName)) {
								e.getValue().add(add);
								cityContacts.put(cityName, e.getValue());
							}
						}
					}
				}
			}
			for (Map.Entry<String, ArrayList<AddressBookMain>> e : cityContacts.entrySet()) {
				System.out.println("The contacts in city " + e.getKey() + " are:");
				int count1 = 0;
				for (ContactInfo add : e.getValue()) {
					System.out.println(add);
					count1++;
				}
				System.out.println(count1 + " are in " + e.getKey() + " city");
			}
			HashMap<String, ArrayList<AddressBookMain>> stateContacts = new HashMap<>();
			for (Map.Entry<String, ArrayList<AddressBookMain>> entry : Directory.entrySet()) {
				for (AddressBookMain a : entry.getValue()) {
					String stateName = a.state;
					for (Map.Entry<String, ArrayList<AddressBookMain>> e : Directory.entrySet()) {
						for (AddressBookMain add : e.getValue()) {
							if (add.city.equalsIgnoreCase(stateName)) {
								e.getValue().add(add);
								stateContacts.put(stateName, e.getValue());
							}
						}
					}
				}
			}
			for (Map.Entry<String, ArrayList<AddressBookMain>> e : stateContacts.entrySet()) {
				System.out.println("The contacts in state " + e.getKey() + " are:");
				int count2 = 0;
				for (AddressBookMain add : e.getValue()) {
					System.out.println(add);
					count2++;
				}
				System.out.println(count2 + " are in state" + e.getKey());
			}
		}

		System.out.println("Thanks for visiting!!");
	}
}
