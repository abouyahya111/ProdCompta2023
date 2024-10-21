package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.AdresseClientPFDAO;
import dao.daoManager.ClientDAO;
import dao.entity.AdresseClientPF;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.Machine;

public class AdresseClientDAOImpl implements AdresseClientPFDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(AdresseClientPF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public AdresseClientPF edit(AdresseClientPF e) {
		
	session.beginTransaction();
	AdresseClientPF p= (AdresseClientPF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		AdresseClientPF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<AdresseClientPF> findAll() {
		return session.createQuery("select c from AdresseClientPF c").list();
		}

	public AdresseClientPF findById(int id) {
		return (AdresseClientPF)session.get(AdresseClientPF.class, id);
		}
	
 

	
	public  List<AdresseClientPF> findListAdresseClientByClient(ClientPF clientpf) {
		// TODO Auto-generated method stub
		Machine machine= new Machine();
		Query query= session.createQuery("select c from AdresseClientPF c where clientPF.id=:clientpf");
		query.setParameter("clientpf", clientpf.getId());
		
		
		return   query.list();
	}

	

}
