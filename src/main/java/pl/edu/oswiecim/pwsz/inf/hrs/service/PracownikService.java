package pl.edu.oswiecim.pwsz.inf.hrs.service;

import pl.edu.oswiecim.pwsz.inf.hrs.model.Pracownik;

import java.util.List;

public interface PracownikService {
    void addPracownik(Pracownik p);
    List<Pracownik> listPracownik();
}
