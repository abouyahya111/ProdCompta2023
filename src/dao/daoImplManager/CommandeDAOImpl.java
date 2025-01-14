package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.CommandeDAO;
import dao.entity.Commande;
import dao.entity.MatierePremier;

public class CommandeDAOImpl implements CommandeDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(Commande e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Commande edit(Commande e) {
		
	session.beginTransaction();
	Commande p= (Commande)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Commande p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Commande> findAll() {
		return session.createQuery("select c from Commande c").list();
		}

	public Commande findById(int id) {
		return (Commande)session.get(Commande.class, id);
		}


}
