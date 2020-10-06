package com.capgemini;

public class ContactInfo {

	// This class maintains contact information
	String fname;
	String lname;
	String address;
	String city;
	String state;
	String zip;
	String phoneno;
	String email;

	public ContactInfo(String fname, String lname, String address, String city, String state, String zip,
			String phoneno, String email) {
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.state = state;
		this.city = city;
		this.zip = zip;
		this.phoneno = phoneno;
		this.email = email;
	}

	public ContactInfo() {

	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void show() {
		System.out.println("Contact details are as: \n First name:" + fname + " \n Last name: " + lname
				+ " \n Address: " + address + " \n City: " + city + " \n State: " + state + " \n ZIP: " + zip
				+ " \n Mobile no: " + phoneno + " \n Email id : " + email);
		System.out.println();
	}
}