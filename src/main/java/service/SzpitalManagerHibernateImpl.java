package service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.*;

@Component
@Transactional
public class SzpitalManagerHibernateImpl implements SzpitalManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	
	@Override
	public void addBadanie(Badanie badanie){
		badanie.setId(0);
		sessionFactory.getCurrentSession().persist(badanie);
	}
	
	@Override
	public void deleteBadanie(Badanie badanie){
		badanie = (Badanie) sessionFactory.getCurrentSession().get(Badanie.class,badanie.getId());		
		sessionFactory.getCurrentSession().delete(badanie);
	}
	
	@Override
	public void editBadanie(Badanie badanie1, Badanie badanie2){
		badanie1.setNazwa(badanie2.getNazwa());
		badanie1.setOpis(badanie2.getOpis());
		badanie1.setKoszt(badanie2.getKoszt());
		badanie1.setGabinety(badanie2.getGabinety());
		sessionFactory.getCurrentSession().update(badanie1);	
	}
	
	
	
	
	@Override
	public Badanie getOneBadanie(long id) {
		return (Badanie) sessionFactory.getCurrentSession().getNamedQuery("Badanie.getOneBadanie").setLong("id", id).uniqueResult();	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Badanie> getAllBadania() {
		return sessionFactory.getCurrentSession().getNamedQuery("Badanie.getAllBadania").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Badanie> kosztBadanie(String koszt) {
		return sessionFactory.getCurrentSession().getNamedQuery("Badanie.kosztBadanie").setString("koszt", koszt).list();
	}
	
	@Override
	public List<Gabinet> getAllGabinetyFromBadanie(Badanie badanie) {
		return badanie.getGabinety();
	}
	
	
	
	
	@Override
	public void addGabinet(Gabinet gabinet) {
		gabinet.setId(0);
		sessionFactory.getCurrentSession().persist(gabinet);
	}
	
	@Override
	public void deleteGabinet(Gabinet gabinet) {
		sessionFactory.getCurrentSession().delete(gabinet);
		
	}
	
	@Override
	public void editGabinet(Gabinet gabinet1, Gabinet gabinet2) {
		gabinet1.setNumer(gabinet2.getNumer());
		gabinet1.setPietro(gabinet2.getPietro());
		gabinet1.setLekarz(gabinet2.getLekarz());
		sessionFactory.getCurrentSession().update(gabinet1);
		
	}
	
	@Override
	public Gabinet getOneGabinet(long id) {
		return (Gabinet) sessionFactory.getCurrentSession().getNamedQuery("Gabinet.getOneGabinet").setLong("id", id).uniqueResult();
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gabinet> getAllGabinety() {
		return sessionFactory.getCurrentSession().getNamedQuery("Gabinet.getAllGabinety").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gabinet> lekarzGabinet(String lekarz) {
		return sessionFactory.getCurrentSession().getNamedQuery("Gabinet.lekarzGabinet").setString("lekarz", lekarz).list();
	}
	
	
	
}
