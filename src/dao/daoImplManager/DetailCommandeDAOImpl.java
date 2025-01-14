package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DetailCommandeDAO;
import dao.entity.DetailCommande;

public class DetailCommandeDAOImpl implements DetailCommandeDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailCommande e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailCommande edit(DetailCommande e) {
		
	session.beginTransaction();
	DetailCommande p= (DetailCommande)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailCommande p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailCommande> findAll() {
		return session.createQuery("select c from DetailCommande c").list();
		}

	public DetailCommande findById(int id) {
		return (DetailCommande)session.get(DetailCommande.class, id);
		}
	
	@SuppressWarnings("unchecked")
	public  List findByMP(int idMP) {
		

		// TODO Auto-generated method stub
		Query query= session.createQuery("select distinct c.prixUnitaire from DetailCommande c where matierePremier.id=:idMP");
		query.setParameter("idMP", idMP);
		
		return query.list();
	
		}

	

	

}
