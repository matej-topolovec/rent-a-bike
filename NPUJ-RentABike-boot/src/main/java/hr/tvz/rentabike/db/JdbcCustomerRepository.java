package hr.tvz.rentabike.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import hr.tvz.rentabike.model.MembershipType;
import hr.tvz.rentabike.model.Customer;

import java.util.List;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {

	private final String FIND_ALL_QUERY = "select c.id as id, c.name as name, c.surname as surname, c.OIB as OIB, c.birthdate as birthdate,"
			+ " c.email as email, c.address as address, c.phone as phone, m.id as MembershipTypeId, m.name as MembershipTypeName,"
			+ " m.discountRate as MembershipTypeDiscountRate, m.durationInMonths as MembershipTypeDurationInMonths  from customer c JOIN membershipType m ON c.membershipTypeId = m.id";

	private JdbcTemplate jdbc;
	private SimpleJdbcInsert customerInserter;

	@Autowired
	public JdbcCustomerRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.customerInserter = new SimpleJdbcInsert(jdbc).withTableName("customer").usingGeneratedKeyColumns("id");
	}

	@Override
	public List<Customer> findAll() {
		return jdbc.query(FIND_ALL_QUERY, this::mapRowToCustomer);
	}

	@Override
	public Customer save(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findOne(String id) {

		return jdbc.queryForObject(FIND_ALL_QUERY + " where c.id = ?", this::mapRowToCustomer, id);

	}

	private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
		Customer c = new Customer();

		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		c.setSurname(rs.getString("surname"));
		c.setOIB(rs.getString("OIB"));
		c.setBirthdate(rs.getDate("birthdate"));
		c.setEmail(rs.getString("email"));
		c.setAddress(rs.getString("address"));
		c.setPhone(rs.getString("phone"));

		c.setMembershipType(new MembershipType());
		c.getMembershipType().setId(rs.getInt("MembershipTypeId"));
		c.getMembershipType().setName(rs.getString("MembershipTypeName"));
		c.getMembershipType().setDiscountRate(rs.getInt("MembershipTypeDiscountRate"));
		c.getMembershipType().setDurationInMonths(rs.getInt("MembershipTypeDurationInMonths"));
		return c;
	}
}
