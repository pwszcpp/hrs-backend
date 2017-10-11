package pl.edu.oswiecim.pwsz.inf.hrs.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

}
