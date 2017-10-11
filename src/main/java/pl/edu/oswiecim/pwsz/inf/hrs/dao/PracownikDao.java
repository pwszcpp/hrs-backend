package pl.edu.oswiecim.pwsz.inf.hrs.dao;

import pl.edu.oswiecim.pwsz.inf.hrs.model.Pracownik;

import java.util.List;

public interface PracownikDao {
    void persist(Pracownik p);
    List<Pracownik> listPracownik();
}
