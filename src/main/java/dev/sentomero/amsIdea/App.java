package dev.sentomero.amsIdea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = dev.sentomero.amsIdea.App.class)
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
