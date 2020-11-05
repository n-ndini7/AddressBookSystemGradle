package com.capgemini.Service;

import java.util.ArrayList;
import java.util.List;
import com.capgemini.ContactInfo;

public class AddressBookRestAPIService {

	public List<ContactInfo> contactList = new ArrayList<ContactInfo>();

	public AddressBookRestAPIService(List<ContactInfo> contacts) {
		contactList = contacts;
	}

	public long countEntries() {
		return contactList.size();
	}

	public void addContactToJsonServer(ContactInfo contact1) {
		contactList.add(contact1);
		System.out.println(contactList);
	}
}
