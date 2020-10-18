package com.capgemini;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AddressBookIOService {

	public static String CONTACT_TEXT_FILE_FOR_ADDRESS_BOOK = "ContactsInAddressBook.txt";

	private List<ContactInfo> contactList;

	public AddressBookIOService() {

	}

	public AddressBookIOService(List<ContactInfo> contactList) {

		this.contactList = contactList;
	}

	public List<ContactInfo> readData() {
		List<ContactInfo> contactList = new LinkedList<ContactInfo>();
		try {
			Files.lines(new File(CONTACT_TEXT_FILE_FOR_ADDRESS_BOOK).toPath()).map(line -> line.trim())
					.forEach(line -> {
						String data = line.toString();
						String[] dataArr = line.split(",");
						for (int i = 0; i < dataArr.length; i++) {
							String fname = dataArr[i].replaceAll("First Name: ", "");
							i++;
							String lname = dataArr[i].replaceAll(" Last Name: ", "");
							i++;
							String add = dataArr[i].replaceAll(" Address: ", "");
							i++;
							String state = dataArr[i].replaceAll(" State: ", "");
							i++;
							String city = dataArr[i].replaceAll(" City: ", "");
							i++;
							String zip = dataArr[i].replaceAll(" ZIP: ", "");
							i++;
							String mobile = dataArr[i].replaceAll(" Mobile no.: ", "");
							i++;
							String email = dataArr[i].replaceAll(" Email: ", "");
							i++;
							ContactInfo ContactInfo = new ContactInfo(fname, lname, add, state, city, zip, mobile,
									email);
						}
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	// reads the contact data from file

	public void writeData(List<ContactInfo> contactList) {
		StringBuffer buffer = new StringBuffer();
		contactList.forEach(employee -> {
			String employeeDataString = employee.toString().concat("\n");
			buffer.append(employeeDataString);
		});
		try {
			Files.write(Paths.get(CONTACT_TEXT_FILE_FOR_ADDRESS_BOOK), buffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// writes the contact data into the file
	public long countEntries() {
		long entries = 0;
		try {
			entries = Files.lines(new File(CONTACT_TEXT_FILE_FOR_ADDRESS_BOOK).toPath()).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}

	// counts the number of entries

}
