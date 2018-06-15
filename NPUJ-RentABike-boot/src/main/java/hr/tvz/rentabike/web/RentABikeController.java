package hr.tvz.rentabike.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hr.tvz.rentabike.db.BikeRepository;
import hr.tvz.rentabike.db.BikeTypeRepository;
import hr.tvz.rentabike.db.MembershipTypeRepository;
import hr.tvz.rentabike.db.UserRepository;
import hr.tvz.rentabike.helper.PasswordGenerator;
import hr.tvz.rentabike.interfaces.CustomerRepository;
import hr.tvz.rentabike.interfaces.LoggingRepository;
import hr.tvz.rentabike.interfaces.RegistrationRepository;
import hr.tvz.rentabike.interfaces.ReservationRepository;
import hr.tvz.rentabike.interfaces.UserRoleRepository;
import hr.tvz.rentabike.model.Bike;
import hr.tvz.rentabike.model.Customer;
import hr.tvz.rentabike.model.Logging;
import hr.tvz.rentabike.model.Reservation;
import hr.tvz.rentabike.model.User;
import hr.tvz.rentabike.model.UserRole;

@Controller
public class RentABikeController {

	@Autowired
	LoggingRepository loggingRepository;

	@Autowired
	BikeRepository BikeRepository;

	@Autowired
	CustomerRepository CustomerRepository;

	@Autowired
	hr.tvz.rentabike.db.JpaBikeRepository JpaBikeRepository;

	@Autowired
	BikeTypeRepository JdbcBikeTypeRepository;

	@Autowired
	UserRepository JdbcUserRepository;

	@Autowired
	MembershipTypeRepository JdbcMemberShipTypeRepository;

	@Autowired
	RegistrationRepository registrationRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	MessageSource messageSource;

	// @Autowired
	// ReservationRepository reservationRepository;

	public void log(String logMessage) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		Logging logging = new Logging();
		logging.setUsername(auth.getName());
		logging.setActionTime(date);
		logging.setActions(logMessage);
		loggingRepository.save(logging);
	}

	@GetMapping("/")
	public String home(Model model) {
		return showForm(model);
	}

	@GetMapping("/home")
	public String showForm(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());
		return "homePage";
	}

	@GetMapping("/logging")
	@Secured({ "ROLE_ADMIN" })
	public String loggingPage(Model model, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("logging", loggingRepository.findAll());

		String logMessage = messageSource.getMessage("logging.loggingPageGet", null, locale);
		log(logMessage);
		return "logging";
	}

	// --------------BIKE ACTION-------------------------

	@RequestMapping(value = "/bikes", method = RequestMethod.GET)
	@Secured({ "ROLE_DEMO", "ROLE_ADMIN" })
	public String RentABike(Model model, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("bikes", BikeRepository.findAll());

		String logMessage = messageSource.getMessage("logging.bikesPageGet", null, locale);
		log(logMessage);

		return "bike";

	}

	@GetMapping("/bike/create")
	public String showCreateBikeForm(Model model, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("Bike", new Bike());
		model.addAttribute("BikeTypes", JdbcBikeTypeRepository.findAll());

		String logMessage = messageSource.getMessage("logging.bikesCreateGet", null, locale);
		log(logMessage);
		return "EditBike";
	}

	@RequestMapping(value = "/bike/create", method = RequestMethod.POST)
	public String processCreateBikeForm(@Valid @ModelAttribute("Bike") Bike bike, Errors errors, BindingResult bindingResult, Model model,
			Locale locale) {
		if (errors.hasErrors() || bike.getQuantity() < bike.getAvailable()) {
			System.out.println("Error : " + errors + bike.getQuantity() + " < " + bike.getAvailable());
			model.addAttribute("BikeTypes",JdbcBikeTypeRepository.findAll());
			return "EditBike";
		}

		BikeRepository.save(bike);

		List<Bike> listabike = BikeRepository.findAll();

		for (Bike b : listabike) {

			System.out.println(b.getId() + " " + b.getName() + " " + b.getDate());
		}

		String logMessage = messageSource.getMessage("logging.bikesCreatePost", null, locale);
		log(logMessage);
		return "redirect:/bikes";

	}

	@RequestMapping(value = "/bike/delete/{id}", method = RequestMethod.GET)
	public String processDeleteBike(@PathVariable("id") Integer id, Locale locale) {
		if (id != 0)
			try {
				BikeRepository.delete(id);
			}
		    catch(Exception e) {
		    	System.out.println(e);
		    	return "ErrorHandlerModal";
		    }

		String logMessage = messageSource.getMessage("logging.bikesDelete", null, locale);
		log(logMessage);
		return "redirect:/bikes";
	}

	@RequestMapping(value = "/bike/edit/{id}", method = RequestMethod.GET)
	public String processEditBike(@PathVariable("id") Integer id, Model model, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		Bike bike = BikeRepository.findOne(id);
		if (bike != null) {
			model.addAttribute("Bike", bike);
			model.addAttribute("BikeTypes", JdbcBikeTypeRepository.findAll());
			return "EditBike";
		}

		String logMessage = messageSource.getMessage("logging.bikesEdit", null, locale);
		log(logMessage);
		return "redirect:/bikes";
	}

	@RequestMapping(value = "/bike/edit/{id}", method = RequestMethod.POST)
	public String processEditBikeForm(@Valid @ModelAttribute("Bike") Bike bike, Errors errors, BindingResult bindingResult, Locale locale) {
		if (errors.hasErrors() || bike.getQuantity() < bike.getAvailable()) {
			System.out.println("Error : " + errors + bike.getQuantity() + " < " + bike.getAvailable());

			return "EditBike";

		}

		BikeRepository.updateBike(bike);

		String logMessage = messageSource.getMessage("logging.bikesEditId", null, locale);
		log(logMessage);


		return "redirect:/bikes";
	}

	@RequestMapping(value = "/bike/details/{id}")
	public String getInfo(@PathVariable("id") Integer id, Model model, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());


		model.addAttribute("bike", BikeRepository.findOne(id));

		String logMessage = messageSource.getMessage("logging.bikesDetails", null, locale);
		log(logMessage);
		return "bikeDetails";
	}

	// prikaz datuma na ekranu i validacije
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true);
		binder.registerCustomEditor(Date.class, editor);


	}

	// Customer controllers
	@GetMapping("/customers")
	public String showCustomers(Model model, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("customers", CustomerRepository.findAll());

		String logMessage = messageSource.getMessage("logging.customersGet", null, locale);
		log(logMessage);
		return "customers";
	}

	@GetMapping("/customers/new")
	public String newCustomer(Model model, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("Customer", new Customer());
		model.addAttribute("membershipType", JdbcMemberShipTypeRepository.findAll());

		String logMessage = messageSource.getMessage("logging.customersCreateGet", null, locale);
		log(logMessage);
		return "customersEdit";
	}



	@RequestMapping(value = "/customers/new", method = RequestMethod.POST)
	public String newCustomerPost(@Valid @ModelAttribute("Customer") Customer c, Errors errors, BindingResult bindingResult, Model model,
			Locale locale) {
		if (errors.hasErrors()) {
			model.addAttribute("membershipType",JdbcMemberShipTypeRepository.findAll());
			return "customersEdit";
		}

		CustomerRepository.save(c);

		String logMessage = messageSource.getMessage("logging.customersCreatePost", null, locale);
		log(logMessage);
		return "redirect:/customers";

	}

	@RequestMapping(value = "/customers/details/{id}")
	public String getDetails(@PathVariable("id") Integer id, Model model, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("customer", CustomerRepository.findOne(id));

		String logMessage = messageSource.getMessage("logging.customersDetails", null, locale);
		log(logMessage);
		return "customersDetails";
	}

	@RequestMapping(value = "/customers/edit/{id}", method = RequestMethod.GET)
	public String editCustomer(@PathVariable("id") Integer id, Model model, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("Customer", CustomerRepository.findOne(id));
		model.addAttribute("membershipType", JdbcMemberShipTypeRepository.findAll());

		String logMessage = messageSource.getMessage("logging.customersEdit", null, locale);
		log(logMessage);
		return "customersEdit";
	}

	@RequestMapping(value = "/customers/edit/{id}", method = RequestMethod.POST)
	public String editCustomer(@Valid @ModelAttribute("Customer") Customer c,  BindingResult bindingResult,
			Model model, Locale locale) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
		}
		CustomerRepository.updateCustomer(c);

		String logMessage = messageSource.getMessage("logging.customersEditId", null, locale);
		log(logMessage);
		return "redirect:/customers";
	}

	@RequestMapping(value = "/customers/delete/{id}", method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable("id") Integer id, Locale locale) {
		if (id != 0)
			CustomerRepository.deleteCustomer(id);

		String logMessage = messageSource.getMessage("logging.customersDelete", null, locale);
		log(logMessage);
		return "redirect:/customers";
	}

	@GetMapping("/registration")
	public String getRegistration(Model model, Locale locale) {
		model.addAttribute("User", new User());

		String logMessage = messageSource.getMessage("logging.registrationGet", null, locale);
		log(logMessage);
		return "registration";
	}

	@PostMapping("/registration")
	public String RegistrationSave(@Valid @ModelAttribute("User") User user, BindingResult bindingResult,
			Model model, Locale locale) {
		String password = PasswordGenerator.hashPassword(user.getPassword());
		user.setPassword(password);

		if (bindingResult.hasErrors()) {
            return "registration";
        }

		if (registrationRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()) == null) {
			registrationRepository.save(user);

			List<User> listaUsera = registrationRepository.findAll();

			for (User user2 : listaUsera) {
				System.out.println(user2.getUsername() + " " + user2.getPassword());
			}

			String logMessage = messageSource.getMessage("logging.registrationPost", null, locale);
			log(logMessage);
			return "login";
		}

		else {
			model.addAttribute("ErrorUsername", "Username or email already in use !");
			return "registration";
		}
	}

	@GetMapping("/administrator")
	@Secured({ "ROLE_ADMIN" })
	public String getAdministrator(Model model, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		List<User> listUsera = registrationRepository.findAllUsers();
		model.addAttribute("Newuser", listUsera);

		String logMessage = messageSource.getMessage("logging.administratorGet", null, locale);
		log(logMessage);
		return "administrator";
	}

	@PostMapping("/admindelete/{id}")
	@Secured({ "ROLE_ADMIN" })
	public String deletePostAdmin(@PathVariable int id, Model model, Locale locale) {
		System.out.println("Del je");

		User username = registrationRepository.findById(id);
		registrationRepository.delete(username);

		List<User> listUsera = registrationRepository.findAllUsers();
		model.addAttribute("Newuser", listUsera);

		String logMessage = messageSource.getMessage("logging.administratorDelete", null, locale);
		log(logMessage);
		return "redirect:/administrator";
	}

	@PostMapping("/adminadd/{id}")
	@Secured({ "ROLE_ADMIN" })
	public String addPostAdmin(@PathVariable int id, Model model, Locale locale) {
		System.out.println("Add je");
		User username = registrationRepository.findById(id);
		UserRole userRole = new UserRole();
		userRole.setUsername(username.getUsername());
		userRole.setRole("ROLE_USER");

		userRoleRepository.save(userRole);

		List<User> listUsera = registrationRepository.findAllUsers();
		model.addAttribute("Newuser", listUsera);

		String logMessage = messageSource.getMessage("logging.administratorPost", null, locale);
		log(logMessage);
		return "redirect:/administrator";
	}

	@RequestMapping(value = "/reservations", method = RequestMethod.GET)
	@Secured({ "ROLE_DEMO", "ROLE_ADMIN" })
	public String Reservations(Model model, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());
		model.addAttribute("reservations", reservationRepository.findAll());

		String logMessage = messageSource.getMessage("logging.reservationsGet", null, locale);
		log(logMessage);
		return "reservations";
	}

	@RequestMapping(value = "/reservations/new", method = RequestMethod.GET)
	@Secured({ "ROLE_DEMO", "ROLE_ADMIN" })
	public String newReservations(Model model, Locale locale) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());
		model.addAttribute("Reservation", new Reservation());
		model.addAttribute("bike", BikeRepository.findAll());
		model.addAttribute("customer", CustomerRepository.findAll());

		String logMessage = messageSource.getMessage("logging.newReservation", null, locale);
		log(logMessage);
		return "reserveBike";
	}
	
	@RequestMapping(value = "/reservations/new", method = RequestMethod.POST)
	public String saveReservation(@Valid @ModelAttribute("Reservation") Reservation r, BindingResult bindingResult,
			Model model, Locale locale) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
		}
		reservationRepository.save(r);
		String logMessage = messageSource.getMessage("logging.saveReservation", null, locale);
		log(logMessage);
		return "redirect:/reservations";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String Contacts(Model model, Locale locale) {
		return "contacts";
	}

	@RequestMapping(value = "/geolocator", method = RequestMethod.GET)
	 @Secured({ "ROLE_DEMO", "ROLE_ADMIN" })
	 public String Geolocator(Model model)
	 {
		 return "geolocator";
	 }
}