package hr.tvz.rentabike.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import hr.tvz.rentabike.db.BikeRepository;
import hr.tvz.rentabike.db.JdbcBikeRepository;
import hr.tvz.rentabike.db.LoggingRepository;
import hr.tvz.rentabike.db.RegistrationRepository;
import hr.tvz.rentabike.db.UserRoleRepository;
import hr.tvz.rentabike.helper.PasswordGenerator;
import hr.tvz.rentabike.model.Bike;
import hr.tvz.rentabike.model.Logging;
import hr.tvz.rentabike.model.User;
import hr.tvz.rentabike.model.UserRole;

@Controller
public class RentABikeController {

	@Autowired
	LoggingRepository loggingRepository;

	@Autowired
	JdbcBikeRepository JdbcBikeRepository;

	@Autowired
	BikeRepository BikeRepository;
	
	
	@Autowired
	hr.tvz.rentabike.db.JdbcBikeTypeRepository JdbcBikeTypeRepository;

	@Autowired
	hr.tvz.rentabike.db.JdbcUserRepository JdbcUserRepository;

	@Autowired
	hr.tvz.rentabike.db.JdbcCustomerRepository JdbcCustomerRepository;

	@Autowired
	RegistrationRepository registrationRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;

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

	
	

	
	//  --------------BIKE ACTION-------------------------
	
	@RequestMapping(value = "/bikes", method = RequestMethod.GET)
	@Secured({ "ROLE_DEMO", "ROLE_ADMIN" })
	public String RentABike(Model model) {
		model.addAttribute("bikes", BikeRepository.findAll());
		return "bike";

	}

	
	
	
	@GetMapping("/CreateBike")
	public String showCreateBikeForm(Model model) {

		model.addAttribute("Bike", new Bike());
		model.addAttribute("BikeTypes", JdbcBikeTypeRepository.findAll());
		
         
		return "EditBike";
	}


	@RequestMapping(value = "/CreateBike", method = RequestMethod.POST)
	public String processCreateBikeForm(@Valid @ModelAttribute("Bike") Bike bike , Errors errors, Model model) {

		if (errors.hasErrors() || bike.getQuantity() < bike.getAvailable() ) {

			System.out.println("Error : " + errors + bike.getQuantity() + " < " + bike.getAvailable() );
		//	return "EditBike";
		}

	    BikeRepository.save(bike);
	  
		List<Bike> listabike = BikeRepository.findAll();
		
		for(Bike b : listabike){

			System.out.println( b.getId()+ " " + b.getName() + " " + b.getDate() );
		}
	  

		return "EditBike";
		
	}

	
	
	@RequestMapping(value = "/DeleteBike/{id}", method = RequestMethod.GET)
	public String processDeleteBike(@PathVariable("id") Integer id) {
		if(id != 0) 
		  BikeRepository.delete(id);
		
		return "redirect:/bikes";		
	}
	
	
	
	@RequestMapping(value = "/EditBike/{id}", method = RequestMethod.GET)
	public String processEditBike(@PathVariable("id") Integer id, Model model) {
		
		
	    Bike bike = BikeRepository.findOne(id);
	    if(bike != null) {
		model.addAttribute("Bike", bike);
	    model.addAttribute("BikeTypes", JdbcBikeTypeRepository.findAll());
		 return "EditBike";
		}
		
		return "redirect:/bikes";
	}
	
	
	
	
	@RequestMapping(value = "/EditBike/{id}", method = RequestMethod.POST)
	public String processEditBikeForm( @ModelAttribute("Bike") Bike bike, Errors errors, Model model) {

		if (errors.hasErrors() || bike.getQuantity() < bike.getAvailable() ) {

			//return "EditBike";
		}

		 
		JdbcBikeRepository.updateBike(bike);
	

		return "EditBike";
	}

	
	@RequestMapping(value = "/BikeDetails/{id}")
	public String getInfo(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("bike", BikeRepository.findOne(id));
		return "bikeDetails";
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
	
	@GetMapping("/administrator")
	public String getAdministrator(Model model){
		List<User> listUsera = registrationRepository.findAllUsers();
		model.addAttribute("Newuser", listUsera);
		return "administrator";
	}
	
	
	@PostMapping("/admindelete/{id}")
    public String deletePostAdmin(@PathVariable int id, Model model){        
		System.out.println("Del je"); 
		
		User username = registrationRepository.findById(id);
		registrationRepository.delete(username);
		
		List<User> listUsera = registrationRepository.findAllUsers();
		model.addAttribute("Newuser", listUsera);
        return "redirect:/administrator";
    }
	
	@PostMapping("/adminadd/{id}")
    public String addPostAdmin(@PathVariable int id, Model model){        
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
	
	/*@DeleteMapping(value = "/administrator/{id}/delete")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteUser(@PathVariable("id") int idx, final RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("css", "Success");
        redirectAttributes.addFlashAttribute("msg", "The user is deleted");

        // delete the user
        registrationRepository.delete(idx);
        return "redirect:/users/";
    }*/
	
	/*@RequestMapping(value="/adminakcije", method=RequestMethod.POST)
	public String deleteUser(@RequestParam(value="buttonadd") int idAdd, @RequestParam(value="buttondel") int idDel, Model model) {
		
		if(idDel != 0){
			System.out.println("Del je");
		}
		if(idAdd != 0){
			System.out.println("Add je");
		}
		
		return "administrator";
	}*/
	
	
	
	

}