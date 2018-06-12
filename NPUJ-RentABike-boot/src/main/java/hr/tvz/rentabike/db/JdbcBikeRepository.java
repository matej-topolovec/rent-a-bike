package hr.tvz.rentabike.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import hr.tvz.rentabike.model.Bike;
import hr.tvz.rentabike.model.BikeType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@Repository
public class JdbcBikeRepository implements BikeRepository{

	private final String FIND_ALL_QUERY = "select b.id as id, b.name as name, b.dateadded as dateAdded, b.quantity as quantity, b.available as available,"
			+ " tb.id as BikeTypeId , tb.name as BikeTypeName  from bike b  JOIN bike_type tb ON b.typeid = tb.id";
	
	private final String SQL_UPDATE_BIKE = "update bike set name = ?, dateadded = ?, quantity = ? , available  = ? , typeid = ?  where id = ?";
	
	private final  String SQL_DELETE_BIKE = "delete from Bike where id = ?";
	
	
	
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
	public List<Bike> findAll() {
	
	return jdbc.query(FIND_ALL_QUERY, this::mapRowToBike);
		
	}

	@Override
	public Bike findOne(Integer id) {
	
		return jdbc.queryForObject(FIND_ALL_QUERY + " where b.id = ?", this::mapRowToBike, id);
		
	}

	@Override
	public void updateBike(Bike bike) {
		 jdbc.update(SQL_UPDATE_BIKE, bike.getName(), bike.getDate(), bike.getQuantity(), bike.getAvailable() , bike.biketype.getId(), bike.getId());
	}

	
	
	   @Override
	   public void delete(Integer id){
		    
		      jdbc.update(SQL_DELETE_BIKE, id);
		      System.out.println("Deleted Record with ID = " + id );
		      return;
		   }

	
	   @Override
	public Bike save(Bike bike) {
		
		bike.setId(saveBikeDetails(bike));	
		return bike;
		
		
	}

	
	
	private int saveBikeDetails(Bike bike) {
		Map<String, Object> values = new HashMap<>();
		
        int i = bike.biketype.getId();
		
		values.put( "name", bike.getName());
		values.put( "dateAdded", bike.getDate());
	    values.put( "quantity", bike.getQuantity());
		values.put( "available", bike.getAvailable());
		values.put( "typeid", bike.biketype.getId());
		return bikeInserter.executeAndReturnKey(values).intValue();
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
