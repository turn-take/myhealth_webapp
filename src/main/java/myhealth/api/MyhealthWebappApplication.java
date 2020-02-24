package myhealth.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
/**
 * Springbootアプリの起動クラス
 *
 */
import org.springframework.context.annotation.PropertySource;
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class MyhealthWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyhealthWebappApplication.class, args);
	}

}
