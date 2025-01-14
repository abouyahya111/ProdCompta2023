package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailResponsableProdDAO;
import dao.entity.DetailProdGen;
import dao.entity.DetailResponsableProd;

public class DetailResponsableProdDAOImpl implements DetailResponsableProdDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailResponsableProd e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailResponsableProd edit(DetailResponsableProd e) {
		
	session.beginTransaction();
	DetailResponsableProd p= (DetailResponsableProd)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailResponsableProd p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailResponsableProd> findAll() {
		return session.createQuery("select c from DetailResponsableProd c").list();
		}

	public DetailResponsableProd findById(int id) {
		return (DetailResponsableProd)session.get(DetailResponsableProd.class, id);
		}
	

	

	

	

}
