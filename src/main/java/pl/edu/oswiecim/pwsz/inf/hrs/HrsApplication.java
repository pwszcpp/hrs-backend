package pl.edu.oswiecim.pwsz.inf.hrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class HrsApplication{


	public static void main(String[] args) {

		SpringApplication.run(HrsApplication.class, args);

	}
}
