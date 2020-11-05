package com.capgemini.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.ContactInfo;

//This class performs the Rest API service 
public class AddressBookRestAPIService {

	public static List<ContactInfo> contactList;

	public enum UpdateType {
		ADDRESS, CITY, STATE, ZIP, PHONE, EMAIL, CONTACT_NAME, CONTACT_TYPE, START_DATE;
	}

	UpdateType type;

	public AddressBookRestAPIService(List<ContactInfo> contacts) {
		contactList = new ArrayList<ContactInfo>(contacts);
	}

	public long countEntries() {
		return contactList.size();
	}

	public void addContactToJsonServer(ContactInfo contact1) {
		contactList.add(contact1);
	}

	public void updateContactInfoinJson(String id, String fname, String lname, String update, UpdateType type) {
		ContactInfo contact = getContactInfoFromAddBook(id, fname, lname);
		String type1 = type.toString();
		if (contact != null) {
			if (contact.getId().equals(id)) {
				switch (type1) {
				case "ADDRESS": {
					contact.setAddress(update);
					break;
				}
				case "CITY": {
					contact.setCity(update);
					break;
				}
				case "STATE": {
					contact.setState(update);
					break;
				}
				case "ZIP": {
					contact.setZip(update);
					break;
				}
				case "PHONE": {
					contact.setPhoneno(update);
					break;
				}
				case "EMAIL": {
					contact.setEmail(update);
					break;
				}
				case "CONTACT_NAME": {
					contact.setName(update);
					break;
				}
				case "CONTACT_TYPE": {
					contact.setType(update);
					break;
				}
				case "START_DATE": {
					Date d1 = Date.valueOf(update);
					contact.setStart(d1);
					break;
				}
				}
			}
		}
	}

	public ContactInfo getContactInfoFromAddBook(String id, String fname, String lname) {
		for (ContactInfo contact : contactList) {
			if (contact.getFname().equalsIgnoreCase(fname) && contact.getLname().equalsIgnoreCase(lname)
					&& contact.getId().equals(id)) {
				return contact;
			}
		}
		return null;
	}

	public void deleteContact(String id, String fname, String lname) {
		ContactInfo contact = getContactInfoFromAddBook(id, fname, lname);
		contactList.remove(contact);
	}
}
