package com.capgemini.Service.DBService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.capgemini.ContactInfo;
import com.capgemini.Service.DBService.AddressBookServiceDBException.ExceptionType;

//UC18 - retreive contacts in the database within a date range
public class AddressBookDBService {

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

	public ContactInfo isAddressBookInSyncWithDB(String firstName) throws AddressBookServiceDBException {
		List<ContactInfo> tempList = this.readContactInfoFromDB();
		return tempList.stream().filter(contact -> contact.getFname().contentEquals(firstName)).findFirst()
				.orElse(null);
	}

}
