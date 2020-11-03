package com.capgemini;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.Service.DBService.AddressBookDBService;
import com.capgemini.Service.DBService.AddressBookServiceDBException;
import com.capgemini.Service.DBService.AddressBookDBService.RetrievalType;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class AddressBookServiceDBTest {
	AddressBookDBService a1 = new AddressBookDBService();

	@Test
	public void givenAddressBookiInDB_ShouldReturnTheListOfRecordsStored() throws AddressBookServiceDBException {
		List<ContactInfo> contactList = a1.readContactInfoFromDB();
		Assert.assertEquals(10, contactList.size());
	}

	@Test
	public void givenAddressBookDB_WhenContactUpdatedShouldSyncWithDB() {
		try {
			a1.updateContactInfoInAddressbook("Alicia", "Key", "91 9876543210");
			ContactInfo contact = a1.isAddressBookInSyncWithDB("Alicia");
			Assert.assertEquals("91 9876543210", contact.getPhoneno());
		} catch (AddressBookServiceDBException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenAddressBookInDataBase_RetrieveContactsWithinADateRange() {
		Date startDate = Date.valueOf("2019-01-01");
		Date endDate = Date.valueOf(LocalDate.now());
		int contacts;
		try {
			contacts = a1.getContactsWithinADateRange(startDate, endDate);
			Assert.assertEquals(6, contacts);
		} catch (AddressBookServiceDBException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void retrieveContactsInTheAddressBookDatabase_fromAcity() {
		int result = 0;
		try {
			result = a1.getContactsWithinACityOrState("Mumbai", RetrievalType.CITY);
			Assert.assertEquals(3, result);
		} catch (AddressBookServiceDBException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void retrieveContactsInTheAddressBookDatabase_fromAstate() {
		int result = 0;
		try {
			result = a1.getContactsWithinACityOrState("New York", RetrievalType.STATE);
			Assert.assertEquals(3, result);
		} catch (AddressBookServiceDBException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addContactToTheAddress_BookAndSyncItWithDB() {
		Date d1 = Date.valueOf("2020-05-05");
		ContactInfo c1 = new ContactInfo("Mark", "Winston", "Civil Street 101", "Bhopal", "Madhya Pradesh", "909090",
				"91 9797979797", "mark@gmail.com", "Rohit", "Friend", d1);
		try {
			a1.addContactsToAddressBook(c1);
			List<ContactInfo> contactList = a1.readContactInfoFromDB();
			Assert.assertEquals(10, contactList.size());
		} catch (AddressBookServiceDBException e) {
			e.printStackTrace();
		}
	}
}
