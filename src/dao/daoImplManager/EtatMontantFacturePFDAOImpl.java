package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.entity.EtatMontantFacturePF;
import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.EtatMontantFacturePFDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;

public class EtatMontantFacturePFDAOImpl implements EtatMontantFacturePFDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(EtatMontantFacturePF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public EtatMontantFacturePF edit(EtatMontantFacturePF e) {
		
	session.beginTransaction();
	EtatMontantFacturePF p= (EtatMontantFacturePF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		EtatMontantFacturePF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<EtatMontantFacturePF> findAll() {
		return session.createQuery("select c from EtatMontantFacturePF c").list();
		}

	public EtatMontantFacturePF findById(int id) {
		return (EtatMontantFacturePF)session.get(EtatMontantFacturePF.class, id);
		}

	
}
