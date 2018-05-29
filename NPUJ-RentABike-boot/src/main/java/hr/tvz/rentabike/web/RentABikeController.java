package hr.tvz.rentabike.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import hr.tvz.rentabike.db.JdbcBikeRepository;
import hr.tvz.rentabike.db.LoggingRepository;
import hr.tvz.rentabike.db.RegistrationRepository;
import hr.tvz.rentabike.helper.PasswordGenerator;
import hr.tvz.rentabike.model.Bike;
import hr.tvz.rentabike.model.Logging;
import hr.tvz.rentabike.model.User;

@Controller
@SessionAttributes({ "biketype" })
public class RentABikeController {

	@Autowired
	LoggingRepository loggingRepository;

	@Autowired
	JdbcBikeRepository JdbcBikeRepository;

	@Autowired
	hr.tvz.rentabike.db.JdbcBikeTypeRepository JdbcBikeTypeRepository;

	@Autowired
	hr.tvz.rentabike.db.JdbcUserRepository JdbcUserRepository;

	@Autowired
	hr.tvz.rentabike.db.JdbcCustomerRepository JdbcCustomerRepository;

	@Autowired
	RegistrationRepository registrationRepository;

	@GetMapping("/home")
	public String showForm(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		Logging logging = new Logging();
		logging.setUsername(auth.getName());
		logging.setActionTime(date);
		logging.setActions("Get request on homepage");

		loggingRepository.save(logging);

		return "homePage";
	}

	@GetMapping("/logging")
	@Secured({ "ROLE_DEMO", "ROLE_ADMIN" })
	public String loggingPage(Model model) {
		model.addAttribute("logging", loggingRepository.findAll());
		return "logging";
	}

	@RequestMapping(value = "/bikes", method = RequestMethod.GET)
	@Secured({ "ROLE_DEMO", "ROLE_ADMIN" })
	public String RentABike(Model model) {
		model.addAttribute("bikes", JdbcBikeRepository.findAll());
		return "bike";
	}

	@GetMapping("/CreateBike")
	public String showCreateBikeForm(Model model) {

		model.addAttribute("Bike", new Bike());
		model.addAttribute("BikeType", JdbcBikeTypeRepository.findAll());

		return "EditBike";
	}

	@PostMapping("/CreateBike")
	public String processCreateBikeForm(@Valid Bike bike, Errors errors, Model model) {

		if (errors.hasErrors()) {

			return "EditBike";
		}

		// JdbcBikeRepository.save(bike);

		return "EditBike";
	}

	@RequestMapping(value = "/EditBike", method = RequestMethod.GET)
	public String processEditBikeForm(@RequestParam(value = "id", required = false) Integer id) {
		// findbyid

		//
		return "EditBike";
	}

	// prikaz datuma na ekranu
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@GetMapping("/customers")
	public String showCustomers(Model model) {

		model.addAttribute("customers", JdbcCustomerRepository.findAll());

		return "customers";
	}

	@RequestMapping(value = "/customers/details/{id}")
	public String getInfo(@PathVariable("id") String id, Model model) {
		model.addAttribute("customer", JdbcCustomerRepository.findOne(id));
		return "customersDetails";
	}

	@GetMapping("/registration")
	public String getRegistration(Model model) {
		model.addAttribute("User", new User());
		return "registration";
	}

	@PostMapping("/registration")
	public String RegistrationSave(@ModelAttribute("User") User user, Model model) {
		String password = PasswordGenerator.hashPassword(user.getPassword());
		user.setPassword(password);

		if (registrationRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()) == null) {
			registrationRepository.save(user);

			List<User> listaUsera = registrationRepository.findAll();

			for (User user2 : listaUsera) {
				System.out.println(user2.getUsername() + " " + user2.getPassword());
			}

			return "login";
		}

		else {
			model.addAttribute("ErrorUsername", "Username or email already in use !");
			return "registration";
		}
	}

}