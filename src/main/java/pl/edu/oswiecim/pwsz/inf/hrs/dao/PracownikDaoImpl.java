package pl.edu.oswiecim.pwsz.inf.hrs.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.oswiecim.pwsz.inf.hrs.model.Pracownik;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PracownikDaoImpl implements PracownikDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void persist(Pracownik p){
        sessionFactory.openSession().save(p);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Pracownik> listPracownik() {
        Criteria criteria = sessionFactory.openSession().createCriteria(Pracownik.class);
        return criteria.list();
    }

}
