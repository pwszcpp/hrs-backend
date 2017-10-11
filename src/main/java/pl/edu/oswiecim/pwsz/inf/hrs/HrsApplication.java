package pl.edu.oswiecim.pwsz.inf.hrs;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Pracownik;
import pl.edu.oswiecim.pwsz.inf.hrs.service.PracownikServiceImpl;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class HrsApplication{


	public static void main(String[] args) {

		SpringApplication.run(HrsApplication.class, args);

	}

}
