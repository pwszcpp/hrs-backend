package pl.edu.oswiecim.pwsz.inf.hrs.dao;

import org.springframework.stereotype.Component;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Pracownik;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class PracownikDao {

    @PersistenceContext
    private EntityManager em;

    public void persist(Pracownik p){
        em.persist(p);
    }

}
