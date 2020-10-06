package com.capgemini;

import java.util.Scanner;
import java.util.*;
import java.util.regex.*;

public class AddressBookMain extends ContactInfo {
	// UC6 - maintain multiple address books in one directory
	public static ContactInfo contact;
	private static Scanner sc;
	ArrayList<ContactInfo> Addbook;
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

	public AddressBookMain() {
		Addbook = new ArrayList<ContactInfo>();
	}

	public void addContact() {
		Addbook.add(contact);
		System.out.println("Contact Added successfully!!");
	}

	public void showContact() {
		for (ContactInfo c : Addbook) {
			c.show();
		}
	}

	public void editContact(String name) {
		for (ContactInfo c : Addbook) {
			if (c.getFname().equals(name)) {
				System.out.println("Contact found!!");
				System.out.println("Enter the field you want to edit: ");
				field = sc.nextLine();
				switch (field) {
				case "fname":
					try {
						System.out.print("Enter first name: ");
						fname = sc.nextLine();
						if (FirstNameCheck(fname)) {
							c.setFname(fname);
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
					break;
				case "lname":
					try {
						System.out.print("Enter last name: ");
						lname = sc.nextLine();
						if (LastNameCheck(lname)) {
							c.setLname(lname);
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
					break;
				case "address":
					// System.out.println("Enter the new address: ");
					// c.setAddress(sc.nextLine());
					try {
						System.out.print("Enter Address: ");
						add = sc.nextLine();
						if (AddressCheck(add)) {
							c.setAddress(add);
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
					break;
				case "state":
					// System.out.println("Enter the new state: ");
					// c.setState(sc.nextLine());
					try {
						System.out.print("Enter state: ");
						state = sc.nextLine();
						if (StateCheck(state)) {
							c.setState(state);
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
					break;
				case "city":
					// System.out.println("Enter the new city: ");
					// c.setCity(sc.nextLine());
					try {
						System.out.print("Enter city: ");
						city = sc.nextLine();
						if (CityCheck(city)) {
							c.setCity(city);
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
					break;
				case "zip":
					// System.out.println("Enter the new ZIP: ");
					// c.setZip(sc.nextLine());
					try {
						System.out.print("Enter ZIP: ");
						zip = sc.nextLine();
						if (ZIPCheck(zip)) {
							c.setZip(zip);
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
					break;
				case "phoneno":
					// System.out.println("Enter the new phone no.: ");
					// c.setPhoneno(sc.nextLine());
					try {
						System.out.print("Enter first name: ");
						phone = sc.nextLine();
						if (MobileCheck(phone)) {
							c.setPhoneno(phone);
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
					break;
				case "email":
					// System.out.println("Enter the new email: ");
					// c.setEmail(sc.nextLine());
					try {
						System.out.print("Enter Email ID: ");
						email = sc.nextLine();
						if (EmailCheck(email)) {
							c.setEmail(email);
						}
					} catch (ContactRegistrationException e) {
						System.out.println("contactregistrationexception thrown!!");
						System.out.println(e.getMessage());
						System.out.println();
					}
					break;
				default:
					System.out.println("No such field exists!!! ");
					break;
				}
				System.out.println("Contact edited successfully!!");
				System.out.println("The updated contact is :");
				c.show();
				break;
			}

		}
	}

	public void removeContact(String name) {
		for (ContactInfo c : Addbook) {
			if (c.getFname().equals(name)) {
				Addbook.remove(c);
				System.out.println("Contact removed successfully!!");
				break;
			}
		}
		for (ContactInfo p : Addbook) {
			p.show();
		}
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
		HashMap<String, AddressBookMain> Directory = new HashMap<String, AddressBookMain>();
		boolean con = true;
		String choice = "";
		while (p) {
			System.out.println(
					"Enter choice : \n 1.Add contact \n 2.Edit Contact \n 3.Delete Contact \n 4.Add Address Book");
			int ch = Integer.parseInt(sc.nextLine());
			switch (ch) {

			case 1:
				System.out.println("Enter the number of contacts you want to enter into Address book:");
				int n = Integer.parseInt(sc.nextLine());
				while (n > 0) {
					System.out.println("Add details of Contact:");
					// System.out.println("Do you want to create a contact in Address book? (Y/N)");
					// choice = sc.nextLine();
					// if (choice.equalsIgnoreCase("y")) {
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
					n--;
				}
				ab.showContact();
				break;
			case 2:
				System.out.println("Enter the first name of the contact to be edited :");
				String name = sc.nextLine();
				ab.editContact(name);
				break;
			case 3:
				System.out.println("Enter the first name of the contact to be deleted :");
				String nm = sc.nextLine();
				ab.removeContact(nm);
				break;
			case 4:
				System.out.println("Enter no. of address books you want to maintain: ");
				int m = Integer.parseInt(sc.nextLine());
				for (int k = 0; k < m; k++) {
					while (true) {
						System.out.println("Enter the name of the address book:");
						addname = sc.nextLine();
						int flag = 0;
						for (Map.Entry<String, AddressBookMain> entry : Directory.entrySet()) {
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
					AddressBookMain adbk = new AddressBookMain();
					System.out.println("Enter the number of contacts you want to enter into Address book:");
					int no = Integer.parseInt(sc.nextLine());
					while (no > 0) {
						System.out.println("Add details of Contact:");
						// System.out.println("Do you want to create a contact in Address book? (Y/N)");
						// choice = sc.nextLine();
						// if (choice.equalsIgnoreCase("y")) {
						while (true) {
							try {
								System.out.print("Enter first name: ");
								fname = sc.nextLine();
								if (adbk.FirstNameCheck(fname)) {
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
								if (adbk.LastNameCheck(lname)) {
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
								if (adbk.AddressCheck(add)) {
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
								if (adbk.StateCheck(state)) {
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
								if (adbk.CityCheck(city)) {
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
								if (adbk.ZIPCheck(zip)) {
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
								if (adbk.MobileCheck(phone)) {
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
								if (adbk.EmailCheck(email)) {
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
						adbk.addContact();
						no--;
					}
					Directory.put(addname, adbk);
				}
				for (Map.Entry mapElement : Directory.entrySet()) {
					String key = (String) mapElement.getKey();
					System.out.println("Address book " + key + " is :");
					AddressBookMain a = (AddressBookMain) mapElement.getValue();
					a.showContact();
				}
				break;
			default:
				System.out.println("Wrong choice entered! Please re-try:");
				break;
			}
			System.out.println("Do you wish to continue? (Y/N)");
			choice = sc.nextLine();
			if (choice.equalsIgnoreCase("n")) {
				System.out.println("Thanks for visiting!!");
				p = false;
				break;
			}
		}
	}
}
