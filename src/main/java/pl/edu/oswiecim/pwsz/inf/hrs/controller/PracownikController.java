package pl.edu.oswiecim.pwsz.inf.hrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Pracownik;
import pl.edu.oswiecim.pwsz.inf.hrs.service.PracownikService;

@Controller
public class PracownikController {

    @Autowired
    private PracownikService pracownikService;

    @RequestMapping("/add")
    @ResponseBody
    public void create() {
        Pracownik p = new Pracownik();
        p.setImie("Janusz");
        p.setNazwisko("Tracz");
        p.setPensja(5000);
        p.setPlec("mezczyzna");
        p.setWiek(43);
        pracownikService.addPracownik(p);

    }

}
