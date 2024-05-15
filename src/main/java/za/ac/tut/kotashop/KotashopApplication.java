package za.ac.tut.kotashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import za.ac.tut.kotashop.config.StorageProperty;


@SpringBootApplication(scanBasePackages = "za.ac.tut.kotashop")
@EnableConfigurationProperties(StorageProperty.class)
public class KotashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(KotashopApplication.class, args);
	}

}

