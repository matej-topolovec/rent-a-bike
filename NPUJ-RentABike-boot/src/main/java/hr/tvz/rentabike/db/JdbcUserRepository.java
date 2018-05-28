package hr.tvz.rentabike.db;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import hr.tvz.rentabike.model.MembershipType;
import hr.tvz.rentabike.model.User;

import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {

	
	
	
	private final String FIND_ALL_QUERY = "select u.id as id, u.username as username, u.name as name, u.surname as surname  , u.address as address, u.OIB as OIB,"
			+ " u.phone as phone , u.birhtdate as birhtdate , u.email as email , m.id as MembershipTypeId, m.name as MembershipTypeName,"
			+ " m.discountRate as MembershipTypeDiscountRate, m.durationInMonths as MembershipTypeDurationInMonths  from user u JOIN membershipType m ON u.membershipTypeId = m.id";
	
	
	
	
	private JdbcTemplate jdbc;
	private SimpleJdbcInsert userInserter;
	
	
	@Autowired
	public JdbcUserRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.userInserter = new SimpleJdbcInsert(jdbc)
			.withTableName("user")
			.usingGeneratedKeyColumns("id");
	}
	
	
	
	@Override
	public List<User> findAll() {
	
	return jdbc.query(FIND_ALL_QUERY, this::mapRowToUser);
		
	}

	
	
	
	@Override
	public User save(User bike) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public User findOne(String id) {
	
		return jdbc.queryForObject(FIND_ALL_QUERY + " where u.id = ?", this::mapRowToUser, id);
		
	}

	
	
	
	

	 
	private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException{
		User user = new User();
		
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setName(rs.getString("name"));
		user.setSurname(rs.getString("surname"));
		user.setAddress(rs.getString("address"));
		user.setOIB(rs.getString("OIB"));
		user.setPhone(rs.getString("phone"));
		user.setBirhtDate(rs.getDate("birhtdate"));
		user.setEmail(rs.getString("email"));
		
		
	    user.setMembershipType(new MembershipType());
		user.getMembershipType().setId(rs.getInt("MembershipTypeId"));
		user.getMembershipType().setName(rs.getString("MembershipTypeName"));
		user.getMembershipType().setDiscountRate(rs.getInt("MembershipTypeDiscountRate"));
		user.getMembershipType().setDurationInMonths(rs.getInt("MembershipTypeDurationInMonths"));
		return user;
	}





	
	
	
	

}
