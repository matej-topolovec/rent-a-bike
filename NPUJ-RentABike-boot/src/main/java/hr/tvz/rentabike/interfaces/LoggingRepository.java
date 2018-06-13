package hr.tvz.rentabike.interfaces;

import java.util.List;

import hr.tvz.rentabike.model.Logging;

public interface LoggingRepository {
	Logging save(Logging logging);
	List<Logging> findAll();
}
