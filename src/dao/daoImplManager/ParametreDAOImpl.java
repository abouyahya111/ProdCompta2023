package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ParametreDAO;
import dao.entity.Parametre;

public class ParametreDAOImpl implements ParametreDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(Parametre e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Parametre edit(Parametre e) {
		
	session.beginTransaction();
	Parametre p= (Parametre)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Parametre p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<Parametre> findAll() {
		return session.createQuery("select c from Parametre c").list();
		}

	public Parametre findById(int id) {
		return (Parametre)session.get(Parametre.class, id);
		}

	@Override
	public Parametre findByCode(String code) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Parametre c where code=:code");
		query.setParameter("code", code);
		
		return (Parametre) query.uniqueResult();
		
	}
	
	@Override
	public Parametre findByLibelle(String libelle) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Parametre c where libelle=:libelle");
		query.setParameter("libelle", libelle);
		
		return (Parametre) query.uniqueResult();
		
	}


}
