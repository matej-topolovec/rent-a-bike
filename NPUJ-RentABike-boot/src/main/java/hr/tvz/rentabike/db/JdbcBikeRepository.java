package hr.tvz.rentabike.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import hr.tvz.rentabike.model.Bike;
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
		// TODO Auto-generated method stub
		
		return jdbc.query("select id, name, quantity from bike",
				this::mapRowToBike);
	//	return null;
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
		bike.setQuantity(rs.getInt("quantity"));
		//predavac.setPozicija(Predavac.Pozicija.valueOf(rs.getString("pozicija")));
		
		return bike;
	}
	
}
