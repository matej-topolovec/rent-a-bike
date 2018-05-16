package hr.tvz.rentabike.db;

import org.springframework.data.jpa.repository.JpaRepository;
import hr.tvz.rentabike.model.Logging;

public interface LoggingRepository extends JpaRepository<Logging, Integer> {
	Logging save(Logging logging);
}
