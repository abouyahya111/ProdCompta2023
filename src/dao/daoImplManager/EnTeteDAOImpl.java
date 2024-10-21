package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ClientDAO;
import dao.daoManager.EnTeteDAO;
import dao.daoManager.TypeBLDAO;
import dao.daoManager.VilleDAO;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.EnTete;
import dao.entity.Ville;
import dao.entity.Machine;
import dao.entity.TypeBL;
import dao.entity.Ville;

public class EnTeteDAOImpl implements EnTeteDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(EnTete e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public EnTete edit(EnTete e) {
		
	session.beginTransaction();
	EnTete p= (EnTete)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		EnTete p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<EnTete> findAll() {
		return session.createQuery("select c from EnTete c order by ville ").list();
		}

	public EnTete findById(int id) {
		return (EnTete)session.get(EnTete.class, id);
		}
	


	
 
	
	
	
	@Override
	public  EnTete findEnTeteByVille(String ville) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from EnTete c where ville=:ville");
		query.setParameter("ville", ville);
		
		
		return (EnTete) query.uniqueResult();
	}
	
	
	
 
	@Override
	public  List<EnTete> findEnTeteByDepot(Depot depot) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from EnTete c where depot.id=:depot");
		query.setParameter("depot", depot.getId());
		
		
		return   query.list();
	}
	
	
 



}
