package hr.tvz.rentabike.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import hr.tvz.rentabike.model.MembershipType;

import java.util.List;

@Repository
public class JdbcMembershipTypeRepository implements MembershipTypeRepository {

	private final String FIND_ALL_QUERY = "select id, name, discountRate, durationInMonths from membershipType";

	private JdbcTemplate jdbc;
	private SimpleJdbcInsert membershipInserter;

	@Autowired
	public JdbcMembershipTypeRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.membershipInserter = new SimpleJdbcInsert(jdbc).withTableName("membershipType").usingGeneratedKeyColumns("id");
	}

	@Override
	public List<MembershipType> findAll() {
		return jdbc.query(FIND_ALL_QUERY, this::mapRowToMembershipType);
	}

	@Override
	public MembershipType save(MembershipType c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MembershipType findOne(String id) {

		return jdbc.queryForObject(FIND_ALL_QUERY + " where c.id = ?", this::mapRowToMembershipType, id);

	}
	
	@Override
	public void deleteMembershipType(String id) {

		final String DELETE_QUERY = "DELETE FROM membershipType WHERE id = " + id;
		jdbc.execute(DELETE_QUERY);

	}

	private MembershipType mapRowToMembershipType(ResultSet rs, int rowNum) throws SQLException {
		MembershipType m = new MembershipType();

		m.setId(rs.getInt("id"));
		m.setName(rs.getString("name"));
		m.setDiscountRate(rs.getInt("discountRate"));
		m.setDurationInMonths(rs.getInt("durationInMonths"));
		
		return m;
	}
}
