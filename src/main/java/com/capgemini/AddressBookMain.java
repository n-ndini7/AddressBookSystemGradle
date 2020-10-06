package com.capgemini;

import java.util.*;
import java.util.regex.*;
import java.util.Collections;

//UC11 - sort contacts aplhabetically according to first name in an Address Book using java streams
public class AddressBookMain {
	Scanner sc = new Scanner(System.in);
	public static List<ContactInfo> addbook = new LinkedList<ContactInfo>();
	public static HashMap<String, List<ContactInfo>> Directory = new HashMap<String, List<ContactInfo>>();

	public void addContact(ContactInfo contactObj) {
		ContactInfo contact;
		boolean isPresent = addbook.stream().anyMatch(obj -> obj.equals(contactObj));
		if (isPresent == false)
			addbook.add(contactObj);
		else
			System.out.println("Contact already present. Duplication not allowed!");
	}

	public void addAddressBook(String listName, List<ContactInfo> ab) {
		// List<ContactInfo> newAddressBook = new LinkedList<ContactInfo>();
		Directory.put(listName, ab);
		System.out.println("Address Book added!!");
	}

	public void searchPersonAcrossCityState(String searchPerson, int searchChoice, String cityOrState) {
		for (Map.Entry<String, List<ContactInfo>> entry : Directory.entrySet()) {
			List<ContactInfo> list = entry.getValue();
			if (searchChoice == 8)
				list.stream()
						.filter(obj -> ((obj.getCity().equals(cityOrState)) && (obj.getFname().equals(searchPerson))))
						.forEach(System.out::println);
			else if (searchChoice == 9)
				list.stream()
						.filter(obj -> ((obj.getState().equals(cityOrState)) && (obj.getFname().equals(searchPerson))))
						.forEach(System.out::println);
		}
	}

	private void viewPersonsByCityState(String cityOrState, int searchChoice) {
		for (Map.Entry<String, List<ContactInfo>> entry : Directory.entrySet()) {
			List<ContactInfo> list = entry.getValue();
			if (searchChoice == 8)
				list.stream().filter(obj -> obj.getCity().equals(cityOrState)).forEach(System.out::println);
			else if (searchChoice == 9)
				list.stream().filter(obj -> obj.getState().equals(cityOrState)).forEach(System.out::println);
		}
	}

	private List<ContactInfo> sortAddressBookByName(List<ContactInfo> sortList) {
		Collections.sort(sortList, new ContactInfo());
		return sortList;
	}

	private long getCountByCityState(String cityOrState, int searchChoice) {
		long count = 0;
		for (Map.Entry<String, List<ContactInfo>> entry : Directory.entrySet()) {
			List<ContactInfo> list = entry.getValue();
			if (searchChoice == 8)
				count += list.stream().filter(obj -> obj.getCity().equals(cityOrState)).count();
			else if (searchChoice == 9)
				count += list.stream().filter(obj -> obj.getState().equals(cityOrState)).count();
		}
		return count;
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
		String fname = "";
		String lname = "";
		String add = "";
		String city = "";
		String state = "";
		String zip = "";
		String phoneno = "";
		String email = "";
		String addname;
		AddressBookMain ab = new AddressBookMain();
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
					for (Map.Entry<String, List<ContactInfo>> entry : Directory.entrySet()) {
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
					ab.addContact(con);
					no--;
				}
				ab.addAddressBook(addname, addbook);
			}
			/*
			 * System.out.println("Do you want to perform search operation? (Y/N)"); String
			 * choice = sc.nextLine(); if (choice.equalsIgnoreCase("y")) {
			 * System.out.println("Enter first name of person to search"); String
			 * searchPerson = sc.nextLine();
			 * System.out.println("Enter the name of city or state you want to search in:");
			 * String cityOrState = sc.nextLine(); System.out.
			 * println("Enter 8 if you entered name of a city \nEnter 9 if you entered name of a state"
			 * ); int searchChoice = Integer.parseInt(sc.nextLine());
			 * ab.searchPersonAcrossCityState(searchPerson, searchChoice, cityOrState); }
			 */
			/*
			 * System.out.println("Do you want to view contacts by state or city? (Y/N)");
			 * String choice = sc.nextLine(); if (choice.equalsIgnoreCase("y")) {
			 * System.out.println("Enter the name of city or state"); String cityOrState =
			 * sc.nextLine(); System.out.
			 * println("Enter 8 if you entered name of a city \nEnter 9 if you entered name of a state"
			 * ); int searchChoice = Integer.parseInt(sc.nextLine());
			 * ab.viewPersonsByCityState(cityOrState, searchChoice); }
			 */
			/*
			 * System.out.println("Do you want to count contacts by state or city? (Y/N)");
			 * String choice = sc.nextLine(); if (choice.equalsIgnoreCase("y")) {
			 * System.out.println("Enter the name of city or state: "); String cityOrState =
			 * sc.nextLine(); System.out.
			 * println("Enter 8 if you entered name of a city \nEnter 9 if you entered name of a state"
			 * ); int searchChoice = Integer.parseInt(sc.nextLine()); System.out.println(
			 * "Total persons in " + cityOrState + " = " +
			 * ab.getCountByCityState(cityOrState, searchChoice)); }
			 */
			System.out.println("Do you want to sort contacts in Address Book? (Y/N)");
			String choice = sc.nextLine();
			if (choice.equalsIgnoreCase("y")) {
				List<ContactInfo> sortedEntriesList = ab.sortAddressBookByName(ab.addbook);
				System.out.println("Entries sorted in current address book!! Sorted Address Book Entries are:");
				System.out.println(sortedEntriesList);
			}
			System.out.println("Thankyou for visiting!!");
			System.exit(0);

		}
	}
}
