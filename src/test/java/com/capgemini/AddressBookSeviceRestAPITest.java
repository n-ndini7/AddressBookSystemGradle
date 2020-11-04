package com.capgemini;

import java.sql.Date;
import java.util.Arrays;

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
		System.out.println("CONTACTS ENTRIES IN JSONServer:\n" + response.asString());
		ContactInfo[] contacts = new Gson().fromJson(response.asString(), ContactInfo[].class);
		return contacts;
	}

	@Test
	public void givenNewContact_WhenAddedToJSONServer_ShouldMatchWith201StatusCodeAndCount() {
		ContactInfo[] contacts = getContactsList();
		AddressBookRestAPIService service;
		service = new AddressBookRestAPIService(Arrays.asList(contacts));
		Date d1 = Date.valueOf("2020-05-05");
		ContactInfo contact = new ContactInfo("Mark", "Winston", "Civil Street 101", "Bhopal", "Madhya Pradesh",
				"909090", "91 9797979797", "mark@gmail.com", "Rohit", "Friend", d1);
		Response response = addPersonToJsonServer(contact);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(201, statusCode);
		contact = new Gson().fromJson(response.asString(), ContactInfo.class);
		service.addContactToJsonServer(contact);
		long entries = service.countEntries();
		Assert.assertEquals(3, entries);
	}

	private Response addPersonToJsonServer(ContactInfo contact) {
		String empJson = new Gson().toJson(contact);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(empJson);
		return request.post("/Contacts");
	}
}
