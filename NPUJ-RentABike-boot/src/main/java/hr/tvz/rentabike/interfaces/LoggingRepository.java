package hr.tvz.rentabike.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import hr.tvz.rentabike.model.Logging;

public interface LoggingRepository {
	Logging save(Logging logging);
	List<Logging> findAll();
}
