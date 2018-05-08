import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import hotel.dao.AddressDAO;
import hotel.dao.CustomerDAO;
import hotel.model.Address;
import hotel.model.Customer;

public class Main {

	public static void main(String[] args) throws SQLException {
		String jdbcURL = "jdbc:h2:D:\\dbeaver\\db\\curs;AUTO_SERVER=TRUE";
		// String jdbcURLPostgresql = jdbc:postgresql://host:port/database
		String userName = "xx";
		String userPasswd = "xx";
		
		// DE ACI NU MAI STIU CE BAZA E
		Connection conn = DriverManager.getConnection(jdbcURL, userName, userPasswd); 
		conn.setAutoCommit(false);
		AddressDAO addrDAO = new AddressDAO(conn);
		Address addr = new Address();
		addr.setCity("Bucharest");
		addr.setCountry("RO");
		addr.setNumber("14");
		addr.setStreet("Banu andronache");
		System.out.println("Inainte de insert:" + addr);
		addr = addrDAO.insert(addr);
		System.out.println("Dupa insert:" + addr);
		
		addr = new Address();
		addr.setCity("Bucharest");
		addr.setCountry("RO");
		addr.setNumber("19");
		addr.setStreet("Banu andronache");
		System.out.println("Inainte de insert:" + addr);
		addr = addrDAO.insert(addr);
		System.out.println("Dupa insert:" + addr);
		conn.commit();
		System.out.println(addrDAO.loadById(1L));
		System.out.println(addrDAO.listAll());
		
		conn.setAutoCommit(false);
		addr = new Address();
		addr.setCity("BUCURESTI");
		addr.setCountry("RO");
		addr.setNumber("99");
		addr.setStreet("Banu andronache");
		Customer cust = new Customer();
		cust.setAddress(addr);
		cust.setLegalId("112233");
		cust.setLegalIdType("BI");
		cust.setName("Vasile");
		cust.setSex("M");
		CustomerDAO cdao = new CustomerDAO(conn);
		cust = cdao.insert(cust);
		System.out.println(cust);
		conn.commit();

		conn.close();
	}

}
