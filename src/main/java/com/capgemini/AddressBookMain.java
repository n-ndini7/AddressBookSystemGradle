package com.capgemini;

import java.util.Scanner;
import java.util.*;
import java.util.regex.*;

public class AddressBookMain {
	// UC1 - create a contact in address book
	public static ContactInfo contact;
	private static Scanner sc;
	ArrayList<ContactInfo> Addbook;

	public AddressBookMain() {
		Addbook = new ArrayList<ContactInfo>();
	}

	public void addContact() {
		Addbook.add(contact);
		System.out.println("Contact Added successfully!!");
	}

	public boolean FirstNameCheck(String firstName) throws ContactRegistrationException {
		Pattern p = Pattern.compile("^[A-Z]{1}[a-zA-Z]{2,}");
		Matcher m = p.matcher(firstName);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of first name !! please re-try!");
		return flag;
	}

	public boolean LastNameCheck(String lastName) throws ContactRegistrationException {
		Pattern p = Pattern.compile("^[A-Z]{1}[a-zA-Z]{2,}");
		Matcher m = p.matcher(lastName);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of last name !! please re-try!");
		return flag;
	}

	public boolean EmailCheck(String email) throws ContactRegistrationException {
		Pattern p = Pattern.compile("^[a-zA-Z0-9+_-]+([.][a-zA-Z0-9]+)*@([a-zA-Z0-9]+)([.][a-z]+)?[.][a-z]{2,}$");
		Matcher m = p.matcher(email);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of Email ID !! please re-try!");
		return flag;
	}

	public boolean CityCheck(String city) throws ContactRegistrationException {
		Pattern p = Pattern.compile("^[A-Z]{1}[a-zA-Z]+");
		Matcher m = p.matcher(city);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of City !! please re-try!");
		return flag;
	}

	public boolean AddressCheck(String add) throws ContactRegistrationException {
		Pattern p = Pattern.compile("[#.0-9a-zA-Z\s,-]+$");
		Matcher m = p.matcher(add);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of Address !! please re-try!");
		return flag;
	}

	public boolean StateCheck(String state) throws ContactRegistrationException {
		Pattern p = Pattern.compile("^[A-Z]{1}[a-zA-Z]+");
		Matcher m = p.matcher(state);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of State !! please re-try!");
		return flag;
	}

	public boolean ZIPCheck(String zip) throws ContactRegistrationException {
		Pattern p = Pattern.compile("[0-9]{6}");
		Matcher m = p.matcher(zip);
		boolean flag = m.matches();
		if (!flag)
			throw new ContactRegistrationException("Invalid entry of ZIP !! please re-try!");
		return flag;
	}

	public boolean MobileCheck(String mobile) throws ContactRegistrationException {
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
		AddressBookMain ab = new AddressBookMain();
		boolean p = true;
		String fname = "";
		String lname = "";
		String add = "";
		String city = "";
		String state = "";
		String zip = "";
		String phone = "";
		String email = "";
		String choice = "";
		while (p) {
			System.out.println("Do you want to create a contact in Address book? (Y/N)");
			choice = sc.nextLine();
			if (choice.equalsIgnoreCase("y")) {
				while (true) {
					try {
						System.out.print("Enter first name: ");
						fname = sc.nextLine();
						if (ab.FirstNameCheck(fname)) {
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
						if (ab.LastNameCheck(lname)) {
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
						if (ab.AddressCheck(add)) {
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
						if (ab.StateCheck(state)) {
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
						if (ab.CityCheck(city)) {
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
						if (ab.ZIPCheck(zip)) {
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
						if (ab.MobileCheck(phone)) {
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
						if (ab.EmailCheck(email)) {
							break;
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
				}
				contact = new ContactInfo();
				contact.setFname(fname);
				contact.setLname(lname);
				contact.setAddress(add);
				contact.setCity(city);
				contact.setState(state);
				contact.setZip(zip);
				contact.setEmail(email);
				contact.setPhoneno(phone);
				ab.addContact();
				break;
			} else {
				System.out.println("Thankyou for visiting!");
				System.exit(0);
			}
		}
	}
}
