package artie.sensor.keyboardmouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("artie.sensor")
@EntityScan("artie.sensor")
@ComponentScan(basePackages = "artie.sensor")
public class KeyboardmouseApplication {
	public static void main(String[] args) {
		SpringApplication.run(KeyboardmouseApplication.class, args);
	}
}
