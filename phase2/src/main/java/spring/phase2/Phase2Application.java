package spring.phase2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Phase2Application {
	public static void main(String[] args) {
		SpringApplication.run(Phase2Application.class, args);
	}
}
