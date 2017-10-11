package pl.edu.oswiecim.pwsz.inf.hrs.dao;

import org.springframework.stereotype.Component;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Pracownik;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class PracownikDaoImpl implements PracownikDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void persist(Pracownik p){
        em.persist(p);
    }

    @Override
    public List<Pracownik> list() {
        List<Pracownik> listPracownik;
        return listPracownik = em.createQuery("from Pracownik", Pracownik.class).getResultList();
    }

}
