package hr.tvz.rentabike.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import hr.tvz.rentabike.model.Bike;
import hr.tvz.rentabike.model.BikeType;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class JdbcBikeRepository implements BikeRepository {

	
	
	private JdbcTemplate jdbc;
	private SimpleJdbcInsert bikeInserter;
	
	@Autowired
	public JdbcBikeRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.bikeInserter = new SimpleJdbcInsert(jdbc)
			.withTableName("bike")
			.usingGeneratedKeyColumns("id");
	}
	
	
	
	@Override
	public Iterable<Bike> findAll() {
	
		
	//	return jdbc.query("select id, name, dateAdded, quantity, available from bike",
		//		this::mapRowToBike);

	return jdbc.query("select b.id as id, b.name as name, b.dateAdded as dateAdded, b.quantity as quantity, b.available as available,"
			+ " tb.id as BikeTypeId , tb.name as BikeTypeName  from bike b JOIN type_bike tb ON b.typeid = tb.id",
				this::mapRowToBike);
		
		
	}

	@Override
	public Bike findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bike save(Bike bike) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private Bike mapRowToBike(ResultSet rs, int rowNum) throws SQLException{
		Bike bike = new Bike();
		
		bike.setId(rs.getInt("id"));
		bike.setName(rs.getString("name"));
		bike.setDate(rs.getDate("dateAdded"));
		bike.setQuantity(rs.getInt("quantity"));
		bike.setAvailable(rs.getInt("available"));
		
	    bike.setBikeType(new BikeType());
		bike.getBikeType().setId(rs.getInt("BikeTypeId"));
		bike.getBikeType().setName(rs.getString("BikeTypeName"));
		
		return bike;
	}
	
}
