package hr.tvz.rentabike.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import hr.tvz.rentabike.model.BikeType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import javax.transaction.Transactional;






@Repository
public class JdbcBikeTypeRepository implements BikeTypeRepository {

	private final String FIND_ALL_QUERY = "select id, name from bike_type";
	
	private JdbcTemplate jdbc;
	private SimpleJdbcInsert bikeTypeInserter;
	
	  
	@Autowired
	public JdbcBikeTypeRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
		this.bikeTypeInserter = new SimpleJdbcInsert(jdbc)
			.withTableName("bike_type")
			.usingGeneratedKeyColumns("id");
	}
	

	
	@Override
	public Iterable<BikeType> findAll() {
	
	return jdbc.query(FIND_ALL_QUERY, this::mapRowToBikeType);
	
		
	}

	@Override
	public BikeType findOne(String id) {
	
		return jdbc.queryForObject(FIND_ALL_QUERY + " where id = ?", this::mapRowToBikeType, id);
		
	}

	
	@Override
	public BikeType save(BikeType bike) {
		
		bike.setId(saveBikeTypeDetails(bike));	
		return bike;
			
	}

	
	private int saveBikeTypeDetails(BikeType bike) {
		Map<String, Object> values = new HashMap<>();
		values.put( "name", bike.getName());
		return bikeTypeInserter.executeAndReturnKey(values).intValue();
	}
	
	
	
	
	 
	private BikeType mapRowToBikeType(ResultSet rs, int rowNum) throws SQLException{
	    BikeType bike = new BikeType();
		bike.setId(rs.getInt("id"));
		bike.setName(rs.getString("name"));
		
		return bike;
	}
	
}
