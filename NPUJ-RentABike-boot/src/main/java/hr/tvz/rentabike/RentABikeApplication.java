package hr.tvz.rentabike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import hr.tvz.rentabike.db.HibernateConfig;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class })
@EnableGlobalMethodSecurity(securedEnabled= true)
public class RentABikeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RentABikeApplication.class, args);
	}
}
