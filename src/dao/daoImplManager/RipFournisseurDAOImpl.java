package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.RipFournisseurDAO;
import dao.entity.DetailCommande;
import dao.entity.RipFournisseur;

public class RipFournisseurDAOImpl implements RipFournisseurDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(RipFournisseur e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public RipFournisseur edit(RipFournisseur e) {
		
	session.beginTransaction();
	RipFournisseur p= (RipFournisseur)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		RipFournisseur p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<RipFournisseur> findAll() {
		return session.createQuery("select c from RipFournisseur c").list();
		}

	public RipFournisseur findById(int id) {
		return (RipFournisseur)session.get(RipFournisseur.class, id);
		}

	

	

	public List<RipFournisseur> findRipByFournisseur(int idFournisseur) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
