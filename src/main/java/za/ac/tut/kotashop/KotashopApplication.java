package za.ac.tut.kotashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "za.ac.tut.kotashop")
public class KotashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(KotashopApplication.class, args);
	}

}

