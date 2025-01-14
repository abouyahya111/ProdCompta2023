package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProductionDAO;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;

public class DetailProductionDAOImpl implements DetailProductionDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailProduction e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailProduction edit(DetailProduction e) {
		
	session.beginTransaction();
	DetailProduction p= (DetailProduction)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailProduction p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailProduction> findAll() {
		return session.createQuery("select c from DetailProduction c").list();
		}

	public DetailProduction findById(int id) {
		return (DetailProduction)session.get(DetailProduction.class, id);
		}
	

}
