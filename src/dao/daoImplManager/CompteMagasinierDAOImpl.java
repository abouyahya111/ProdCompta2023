package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.CompteMagasinierDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.CompteMagasinier;
import dao.entity.CoutMP;
import dao.entity.DetailCompteMagasinier;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.MatierePremier;
import dao.entity.Production;

public class CompteMagasinierDAOImpl implements CompteMagasinierDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(CompteMagasinier e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CompteMagasinier edit(CompteMagasinier e) {
		
	session.beginTransaction();
	CompteMagasinier p= (CompteMagasinier)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CompteMagasinier p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<CompteMagasinier> findAll() {
		return session.createQuery("select c from CompteMagasinier c").list();
		}

	public CompteMagasinier findById(int id) {
		return (CompteMagasinier)session.get(CompteMagasinier.class, id);
		}


	
	public CompteMagasinier findByCode(String code) {
		// TODO Auto-generated method stub
		CompteMagasinier CompteMagasinier= new CompteMagasinier();
		Query query= session.createQuery("select c from CompteMagasinier c where code=:code");
		query.setParameter("code", code);
		
		CompteMagasinier= (CompteMagasinier) query.uniqueResult();
		
		return CompteMagasinier;
	}
	
	public List<DetailCompteMagasinier> findDetailCompteMagasinierByCompteMagasinierAndDate(int code,Date date) {
		
		Query query= session.createQuery("select c from DetailCompteMagasinier c where compteMagasinier.id=:code and dateoperation=:date");
		query.setParameter("code", code);
		query.setParameter("date", date);
		return query.list();
		
		}
	
	

	



}
