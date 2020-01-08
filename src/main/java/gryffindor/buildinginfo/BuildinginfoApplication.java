package gryffindor.buildinginfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Runs the whole application
 * @author Griffindor
 */
@SpringBootApplication
@EnableWebMvc
public class BuildinginfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuildinginfoApplication.class, args);
	}

}
