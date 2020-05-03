package spring.phase2.controller;


import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class HomeController {
	private static final String WELCOME_MSG = "Welcome to KMS Technology !";
	
	@GetMapping("/home")
	public String home() {
		return WELCOME_MSG;
	}
}
