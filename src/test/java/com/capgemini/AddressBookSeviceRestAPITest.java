package com.capgemini;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.Service.AddressBookRestAPIService;
import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddressBookSeviceRestAPITest {
	@Before
	public void Setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3000;
	}

	@Test
	public void givenContactsInJSONServer_whenRetrieved_ShouldMatchTheCount() {
		ContactInfo[] contacts = getContactsList();
		AddressBookRestAPIService service;
		service = new AddressBookRestAPIService(Arrays.asList(contacts));
		long entries = service.countEntries();
		Assert.assertEquals(2, entries);
	}

	private ContactInfo[] getContactsList() {
		Response response = RestAssured.get("/Contacts");
		ContactInfo[] contacts = new Gson().fromJson(response.asString(), ContactInfo[].class);
		return contacts;
	}

	@Test
	public void givenNewContact_WhenAddedToJSONServer_ShouldMatchWith201StatusCodeAndCount() {
		ContactInfo[] contacts = getContactsList();
		AddressBookRestAPIService service;
		List<ContactInfo> contactList = Arrays.asList(contacts);
		service = new AddressBookRestAPIService(contactList);
		service = new AddressBookRestAPIService(contactList);
		Date d1 = Date.valueOf("2020-05-05");
		ContactInfo contact = new ContactInfo("3", "Mark", "Winston", "Civil Street 101", "Bhopal", "Madhya Pradesh",
				"909090", "91 9797979797", "mark@gmail.com", "Rohit", "Friend", d1);
		Response response = addContactToJsonServer(contact);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(201, statusCode);
		contact = new Gson().fromJson(response.asString(), ContactInfo.class);
		service.addContactToJsonServer(contact);
		long entries = service.countEntries();
		Assert.assertEquals(3, entries);
	}

	@Test
	public void givenMultipleContacts_WhenAddedToJSONServer_ShouldMatchWith201StatusCodeAndCount() {
		ContactInfo[] contacts = getContactsList();
		AddressBookRestAPIService service;
		List<ContactInfo> contactList = Arrays.asList(contacts);
		service = new AddressBookRestAPIService(contactList);
		Date d1 = Date.valueOf("2020-05-05");
		Date d2 = Date.valueOf("2020-04-04");
		Date d3 = Date.valueOf("2020-06-06");
		ContactInfo[] contactsArray = {
				new ContactInfo("4", "Mithilda", "Warner", "Civil Street 404", "Mumbai", "Maharshtra", "902290",
						"91 9797229797", "mith@gmail.com", "Robert", "Friend", d1),
				new ContactInfo("5", "Emily", "Watson", "Civil Street 202", "Mumbai", "Maharashtra", "909098",
						"91 9797974597", "emi@gmail.com", "Ritu", "Colleague", d2),
				new ContactInfo("6", "Poulami", "Dey", "Civil Street 303", "Amsterdam", "New York", "119090",
						"91 9797232797", "pol@gmail.com", "Clinton", "Family", d3), };
		for (ContactInfo contact1 : contactsArray) {
			Response response = addContactToJsonServer(contact1);
			int statusCode = response.getStatusCode();
			Assert.assertEquals(201, statusCode);
			contact1 = new Gson().fromJson(response.asString(), ContactInfo.class);
			service.addContactToJsonServer(contact1);
		}
		long entries = service.countEntries();
		Assert.assertEquals(6, entries);
	}

	private Response addContactToJsonServer(ContactInfo contact) {
		String empJson = new Gson().toJson(contact);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(empJson);
		return request.post("/Contacts");
	}
}
