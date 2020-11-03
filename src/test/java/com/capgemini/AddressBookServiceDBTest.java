package com.capgemini;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.Service.DBService.AddressBookDBService;
import com.capgemini.Service.DBService.AddressBookServiceDBException;
import java.util.List;

public class AddressBookServiceDBTest {
	AddressBookDBService a1 = new AddressBookDBService();

	@Test
	public void givenAddressBookiInDB_ShouldReturnTheListOfRecordsStored() throws AddressBookServiceDBException {
		List<ContactInfo> contactList = a1.readContactInfoFromDB();
		Assert.assertEquals(9, contactList.size());
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
}
