package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.oswiecim.pwsz.inf.hrs.dao.PracownikDao;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Pracownik;

import java.util.List;

@Service
public class PracownikServiceImpl implements PracownikService{

    @Autowired
    private PracownikDao pracownikDao;

    @Override
    public void addPracownik(Pracownik p) {
        pracownikDao.persist(p);
    }

    @Override
    public List<Pracownik> listPracownik() {
        return pracownikDao.listPracownik();
    }
}
