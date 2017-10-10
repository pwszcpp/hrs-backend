package pl.edu.oswiecim.pwsz.inf.hrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Pracownik;
import pl.edu.oswiecim.pwsz.inf.hrs.service.PracownikService;

@SpringBootApplication
public class HrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrsApplication.class, args);

		Pracownik p = new Pracownik();
		p.setImie("Janusz");
		p.setNazwisko("Tracz");
		p.setPensja(5000);
		p.setPlec("mezczyzna");
		p.setWiek(43);
		
		PracownikService pracownikService = new PracownikService();

		pracownikService.add(p);

	}
}
