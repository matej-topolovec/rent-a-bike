package hr.tvz.rentabike.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import hr.tvz.rentabike.model.Bike;
import hr.tvz.rentabike.model.MembershipType;
import hr.tvz.rentabike.model.Reservation;
import hr.tvz.rentabike.model.User;

public class JdbcReservationRepository implements ReservationRepository{

	
	private final String FIND_ALL_QUERY = "select r.id as id, r.startTime as startTime, r.endTime as endTime, u.name as userName, b.name as bikeName, mt.discountRate as discountRate, mt.durationInMonths as durationInMonths"
			+ " from reservation r join user u on r.userId = u.id join bike b on r.bikeId = b.id join membershipType mt on u.membershipTypeId = mt.id ";
	
	
	
	private JdbcTemplate jdbc;
	private SimpleJdbcInsert reservationInserter;
	
	
	@Autowired
	public JdbcReservationRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.reservationInserter = new SimpleJdbcInsert(jdbc)
			.withTableName("reservation")
			.usingGeneratedKeyColumns("id");
	}
	
	
	
	
	@Override
	public Iterable<Reservation> findAll() {
		
		return jdbc.query(FIND_ALL_QUERY, this::mapRowToReservation);
		
	}

	@Override
	public Reservation findOne(String id) {
		
		return jdbc.queryForObject(FIND_ALL_QUERY + " where r.id = ?", this::mapRowToReservation, id);
		
	}

	@Override
	public Reservation save(Reservation reservation) {
		// TODO Auto-generated method stub
		return null;
		
	}
		
		 
   private Reservation mapRowToReservation(ResultSet rs, int rowNum) throws SQLException{
			Reservation reservation = new Reservation();
			
			reservation.setId(rs.getInt("id"));
			reservation.setStartTime(rs.getDate("startTime"));
			reservation.setStartTime(rs.getDate("endTime"));
			
			
			reservation.setUser(new User());
			reservation.getUser().setName(rs.getString("userName"));
		
			reservation.getUser().setMembershipType(new MembershipType());
			reservation.getUser().getMembershipType().setDiscountRate(rs.getInt("discountRate"));
			reservation.getUser().getMembershipType().setDurationInMonths(rs.getInt("durationInMonths"));
			
			reservation.setBike(new Bike());
			reservation.getBike().setName(rs.getString("bikeName"));
			
		
			
			
			return reservation;	
		
		
		
	}

}
