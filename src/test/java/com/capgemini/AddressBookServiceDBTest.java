package com.capgemini;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.Service.DBService.AddressBookDBService;
import com.capgemini.Service.DBService.AddressBookServiceDBException;
import java.util.List;

public class AddressBookServiceDBTest {
	@Test
	public void givenAddressBookiInDB_ShouldReturnTheListOfRecordsStored() throws AddressBookServiceDBException {
		AddressBookDBService a1 = new AddressBookDBService();
		List<ContactInfo> contactList = a1.readContactInfoFromDB();
		Assert.assertEquals(9, contactList.size());
	}
}
