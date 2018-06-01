package hr.tvz.rentabike.db;



import hr.tvz.rentabike.model.BikeType;


public interface BikeTypeRepository{

	Iterable<BikeType> findAll();
	
	BikeType findOne(String id);
	
	BikeType save(BikeType biketype);

  

}
