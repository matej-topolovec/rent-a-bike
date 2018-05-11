package hr.tvz.rentabike.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RentABikeController {
	@GetMapping("/rentabike")
	public String showForm(Model model) {
		return "homePage";
	}
}
