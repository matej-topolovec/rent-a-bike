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
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
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
import hr.tvz.rentabike.db.CustomerRepository;
import hr.tvz.rentabike.db.MembershipTypeRepository;
import hr.tvz.rentabike.db.LoggingRepository;
import hr.tvz.rentabike.db.RegistrationRepository;
import hr.tvz.rentabike.db.ReservationRepository;
import hr.tvz.rentabike.db.UserRepository;
import hr.tvz.rentabike.db.UserRoleRepository;
import hr.tvz.rentabike.helper.PasswordGenerator;
import hr.tvz.rentabike.model.Bike;
import hr.tvz.rentabike.model.Customer;
import hr.tvz.rentabike.model.Logging;
import hr.tvz.rentabike.model.User;
import hr.tvz.rentabike.model.UserRole;

@Controller
public class RentABikeController {

	@Autowired
	LoggingRepository loggingRepository;

	@Autowired
	BikeRepository BikeRepository;

	@Autowired
	hr.tvz.rentabike.db.JpaBikeRepository JpaBikeRepository;

	@Autowired
	BikeTypeRepository JdbcBikeTypeRepository;

	@Autowired
	UserRepository JdbcUserRepository;

	@Autowired
	CustomerRepository JdbcCustomerRepository;

	@Autowired
	MembershipTypeRepository JdbcMemberShipTypeRepository;

	@Autowired
	RegistrationRepository registrationRepository;

	@Autowired
	UserRoleRepository userRoleRepository;

	@Autowired
	ReservationRepository reservationRepository;

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
		log("Get request on homepage");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());
		return "homePage";
	}

	@GetMapping("/logging")
	@Secured({ "ROLE_DEMO", "ROLE_ADMIN" })
	public String loggingPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("logging", loggingRepository.findAll());
		return "logging";
	}

	// --------------BIKE ACTION-------------------------

	@RequestMapping(value = "/bikes", method = RequestMethod.GET)
	@Secured({ "ROLE_DEMO", "ROLE_ADMIN" })
	public String RentABike(Model model) {
		log("Get request on /bikes");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("bikes", BikeRepository.findAll());
		return "bike";

	}

	@GetMapping("/bike/create")
	public String showCreateBikeForm(Model model) {
		log("Get request on /bike/create");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("Bike", new Bike());
		model.addAttribute("BikeTypes", JdbcBikeTypeRepository.findAll());

		return "EditBike";
	}

	@RequestMapping(value = "/bike/create", method = RequestMethod.POST)
	public String processCreateBikeForm(@Valid @ModelAttribute("Bike") Bike bike, Errors errors, BindingResult bindingResult, Model model) {
		log("Post request on /bike/create");
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

		return "redirect:/bikes";

	}

	@RequestMapping(value = "/bike/delete/{id}", method = RequestMethod.GET)
	public String processDeleteBike(@PathVariable("id") Integer id) {
		log("Get request on /bike/delete/" + id);
		if (id != 0)
			try {
				BikeRepository.delete(id);
			}
		    catch(Exception e) {
		    	System.out.println(e);
		    	return "ErrorHandlerModal";
		    }
		return "redirect:/bikes";
	}

	@RequestMapping(value = "/bike/edit/{id}", method = RequestMethod.GET)
	public String processEditBike(@PathVariable("id") Integer id, Model model) {
		log("Get request on /bike/edit/" + id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		Bike bike = BikeRepository.findOne(id);
		if (bike != null) {
			model.addAttribute("Bike", bike);
			model.addAttribute("BikeTypes", JdbcBikeTypeRepository.findAll());
			return "EditBike";
		}

		return "redirect:/bikes";
	}

	@RequestMapping(value = "/bike/edit/{id}", method = RequestMethod.POST)
	public String processEditBikeForm(@Valid @ModelAttribute("Bike") Bike bike, Errors errors, BindingResult bindingResult) {
		log("Post request on /bike/edit/" + bike.getId());
		if (errors.hasErrors() || bike.getQuantity() < bike.getAvailable()) {
			System.out.println("Error : " + errors + bike.getQuantity() + " < " + bike.getAvailable());

			return "EditBike";
		
		}

		BikeRepository.updateBike(bike);

		return "redirect:/bikes";
	}

	@RequestMapping(value = "/bike/details/{id}")
	public String getInfo(@PathVariable("id") Integer id, Model model) {
		log("Get request on /bike/details/" + id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("bike", BikeRepository.findOne(id));
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
	public String showCustomers(Model model) {
		log("Get request on /customers");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("customers", JdbcCustomerRepository.findAll());

		return "customers";
	}

	@RequestMapping(value = "/customers/details/{id}")
	public String getInfo(@PathVariable("id") String id, Model model) {
		log("Get request on /customers/details/" + id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("customer", JdbcCustomerRepository.findOne(id));
		return "customersDetails";
	}

	@RequestMapping(value = "/customers/edit/{id}", method = RequestMethod.GET)
	public String editCustomer(@PathVariable("id") String id, Model model) {
		log("Get request on /customers/edit/" + id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("customer", JdbcCustomerRepository.findOne(id));
		model.addAttribute("membershipType", JdbcMemberShipTypeRepository.findAll());
		return "customersEdit";
	}

	@RequestMapping(value = "/customers/edit/{id}", method = RequestMethod.POST)
	public String editCustomer(@Valid @ModelAttribute("customer") Customer c, BindingResult bindingResult,
			Model model) {
		log("Post request on /customers/edit/" + c.getId());
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
		}
		JdbcCustomerRepository.updateCustomer(c);
		return "redirect:/customers";
	}

	@RequestMapping(value = "/customers/delete/{id}", method = RequestMethod.GET)
	public String deleteCustomer(@PathVariable("id") String id) {
		log("Get request on /customers/delete/" + id);
		if (id != "0")
			JdbcCustomerRepository.deleteCustomer(id);

		return "redirect:/customers";
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

	@GetMapping("/administrator")
	@Secured({ "ROLE_ADMIN" })
	public String getAdministrator(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		List<User> listUsera = registrationRepository.findAllUsers();
		model.addAttribute("Newuser", listUsera);
		return "administrator";
	}

	@PostMapping("/admindelete/{id}")
	@Secured({ "ROLE_ADMIN" })
	public String deletePostAdmin(@PathVariable int id, Model model) {
		System.out.println("Del je");

		User username = registrationRepository.findById(id);
		registrationRepository.delete(username);

		List<User> listUsera = registrationRepository.findAllUsers();
		model.addAttribute("Newuser", listUsera);
		return "redirect:/administrator";
	}

	@PostMapping("/adminadd/{id}")
	@Secured({ "ROLE_ADMIN" })
	public String addPostAdmin(@PathVariable int id, Model model) {
		System.out.println("Add je");
		User username = registrationRepository.findById(id);
		UserRole userRole = new UserRole();
		userRole.setUsername(username.getUsername());
		userRole.setRole("ROLE_USER");

		userRoleRepository.save(userRole);

		List<User> listUsera = registrationRepository.findAllUsers();
		model.addAttribute("Newuser", listUsera);
		return "redirect:/administrator";
	}

	/*
	 * @DeleteMapping(value = "/administrator/{id}/delete")
	 * 
	 * @ResponseStatus(value = HttpStatus.OK) public String
	 * deleteUser(@PathVariable("id") int idx, final RedirectAttributes
	 * redirectAttributes) {
	 * 
	 * redirectAttributes.addFlashAttribute("css", "Success");
	 * redirectAttributes.addFlashAttribute("msg", "The user is deleted");
	 * 
	 * // delete the user registrationRepository.delete(idx); return
	 * "redirect:/users/"; }
	 */

	/*
	 * @RequestMapping(value="/adminakcije", method=RequestMethod.POST) public
	 * String deleteUser(@RequestParam(value="buttonadd") int
	 * idAdd, @RequestParam(value="buttondel") int idDel, Model model) {
	 * 
	 * if(idDel != 0){ System.out.println("Del je"); } if(idAdd != 0){
	 * System.out.println("Add je"); }
	 * 
	 * return "administrator"; }
	 */

	@RequestMapping(value = "/reservations", method = RequestMethod.GET)
	@Secured({ "ROLE_DEMO", "ROLE_ADMIN" })
	public String Reservations(Model model) {
		log("Get request on /reservations");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user", auth.getName());

		model.addAttribute("reservations", reservationRepository.findAll());
		return "reservations";
	}
}