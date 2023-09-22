package org.proyecto.medijoven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClinicamedijovenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicamedijovenApplication.class, args);
	}

}
