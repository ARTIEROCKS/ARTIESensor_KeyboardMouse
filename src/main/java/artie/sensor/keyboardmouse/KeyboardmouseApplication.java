package artie.sensor.keyboardmouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KeyboardmouseApplication {
	
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "keyboardmouse");
		SpringApplication.run(KeyboardmouseApplication.class, args);
	}

}
