package pl.edu.oswiecim.pwsz.inf.hrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.oswiecim.pwsz.inf.hrs.dao.PracownikDao;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Pracownik;

@Component
public class PracownikService {

    @Autowired
    private PracownikDao pracownikDao;

    @Transactional
    public void add(Pracownik p){
        pracownikDao.persist(p);
    }

}
