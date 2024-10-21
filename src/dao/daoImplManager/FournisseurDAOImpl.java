package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.FournisseurDAO;
import dao.entity.ChargeProduction;
import dao.entity.Depot;
import dao.entity.Fournisseur;

public class FournisseurDAOImpl implements FournisseurDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(Fournisseur e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Fournisseur edit(Fournisseur e) {
		
	session.beginTransaction();
	Fournisseur p= (Fournisseur)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Fournisseur p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Fournisseur> findAll() {
		return session.createQuery("select c from Fournisseur c").list();
		}
	
	public List<Fournisseur> findFournisseurByDepot(Depot depot) {
		Query query= session.createQuery("select c from Fournisseur c where depot.id =:depot");
		query.setParameter("depot", depot.getId());
		return query.list();

		}
	
	public Fournisseur findFournisseurByNom(String nom) {
		Query query= session.createQuery("select c from Fournisseur c where nom =:nom");
		query.setParameter("nom", nom);
		return (Fournisseur)query.uniqueResult();

		}

	public Fournisseur findById(int id) {
		return (Fournisseur)session.get(Fournisseur.class, id);
		}



}
