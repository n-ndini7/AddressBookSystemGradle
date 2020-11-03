package com.capgemini.Service.DBService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capgemini.ContactInfo;
import com.capgemini.Service.DBService.AddressBookServiceDBException.ExceptionType;

//UC21 - add multiple contacts to the address book database
public class AddressBookDBService {

	public enum RetrievalType {
		CITY, STATE;
	}

	RetrievalType type;

	private Connection getConnection() throws AddressBookServiceDBException {
		String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service?allowPublicKeyRetrieval=true&&useSSL=false";
		String userName = "root";
		String password = "Rn@11041997#";
		try {
			return DriverManager.getConnection(jdbcURL, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddressBookServiceDBException(AddressBookServiceDBException.ExceptionType.UNABLE_TO_CONNECT,
					e.getMessage());
		}
	}

	public List<ContactInfo> readContactInfoFromDB() throws AddressBookServiceDBException {
		String sql = "select * from address_book;";
		List<ContactInfo> contactList = new ArrayList<ContactInfo>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			contactList = getContactInfo(result);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddressBookServiceDBException(ExceptionType.UNABLE_TO_CONNECT, e.getMessage());
		}
		return contactList;
	}

	private List<ContactInfo> getContactInfo(ResultSet result) throws AddressBookServiceDBException {
		List<ContactInfo> list1 = new ArrayList<ContactInfo>();
		try {
			while (result.next()) {
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String address = result.getString("address");
				String city = result.getString("city");
				String state = result.getString("state");
				String zip = result.getString("zip_code");
				String phoneNo = result.getString("phone");
				String email = result.getString("email");
				String name = result.getString("name");
				String type = result.getString("type");
				list1.add(new ContactInfo(firstName, lastName, address, city, state, zip, phoneNo, email, name, type));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddressBookServiceDBException(ExceptionType.UNABLE_TO_CONNECT, e.getMessage());
		}
		return list1;
	}

	public int updateContactInfoInAddressbook(String firstname, String lastname, String update)
			throws AddressBookServiceDBException {
		String sql = String.format("update address_book set phone= '%s' where first_name = '%s' and last_name='%s';",
				update, firstname, lastname);
		int res = 0;
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			res = statement.executeUpdate(sql);
		} catch (SQLException e) {
			throw new AddressBookServiceDBException(ExceptionType.UPDATE_FAILED, e.getMessage());
		} catch (AddressBookServiceDBException e1) {
			e1.printStackTrace();
		}
		return res;
	}

	public int getContactsWithinADateRange(Date startDate, Date endDate) throws AddressBookServiceDBException {
		String sql = String.format("SELECT * FROM address_book WHERE start BETWEEN '%s' AND '%s';", startDate, endDate);
		int noOfContacts = 0;
		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				noOfContacts++;
			}
		} catch (SQLException e) {
			throw new AddressBookServiceDBException(ExceptionType.RETREIVAL_FAILED, e.getMessage());
		} catch (AddressBookServiceDBException e1) {
			e1.printStackTrace();
		}
		return noOfContacts;
	}

	public int getContactsWithinACityOrState(String key, RetrievalType type) throws AddressBookServiceDBException {
		String type1 = type.toString();
		int contacts = 0;
		String sql = null;
		if (type1.equalsIgnoreCase("city"))
			sql = String.format("SELECT * FROM address_book WHERE city = '%s';", key);
		else if (type1.equalsIgnoreCase("state"))
			sql = String.format("SELECT * FROM address_book WHERE state = '%s';", key);
		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				contacts++;
			}
		} catch (SQLException e) {
			throw new AddressBookServiceDBException(ExceptionType.RETREIVAL_FAILED, e.getMessage());
		} catch (AddressBookServiceDBException e1) {
			e1.printStackTrace();
		}
		return contacts;

	}

	public void addContactsToAddressBook(ContactInfo c1) throws AddressBookServiceDBException {
		String sql = String.format(
				"INSERT INTO address_book(first_name,last_name,address,city,state,phone,email,name,type,zip_code,start) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
				c1.getFname(), c1.getLname(), c1.getAddress(), c1.getCity(), c1.getState(), c1.getPhoneno(),
				c1.getEmail(), c1.getName(), c1.getType(), c1.getZip(), c1.getStart());
		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			int res = statement.executeUpdate(sql);
		} catch (SQLException e) {
			throw new AddressBookServiceDBException(ExceptionType.INSERT_FAILED, e.getMessage());
		} catch (AddressBookServiceDBException e1) {
			e1.printStackTrace();
		}
	}

	public void addMultipleContactInTheDatabase(List<ContactInfo> contactList) throws AddressBookServiceDBException {
		Map<Integer, Boolean> statusMap = new HashMap<Integer, Boolean>();
		contactList.forEach(c1 -> {
			statusMap.put(c1.hashCode(), false);
			Runnable task = () -> {
				try {
					this.addContactsToAddressBook(c1);
				} catch (AddressBookServiceDBException e) {
					e.printStackTrace();
				}
				statusMap.put(c1.hashCode(), true);
			};
			Thread thread = new Thread(task, c1.getFname());
			thread.start();
		});
		while (statusMap.containsValue(false)) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				throw new AddressBookServiceDBException(ExceptionType.INSERT_FAILED, e.getMessage());
			}
		}
	}

	public ContactInfo isAddressBookInSyncWithDB(String firstName) throws AddressBookServiceDBException {
		List<ContactInfo> tempList = this.readContactInfoFromDB();
		return tempList.stream().filter(contact -> contact.getFname().contentEquals(firstName)).findFirst()
				.orElse(null);
	}

}
