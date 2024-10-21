package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.Machine;

public class ClientPFDAOImpl implements ClientPFDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(ClientPF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ClientPF edit(ClientPF e) {
		
	session.beginTransaction();
	ClientPF p= (ClientPF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ClientPF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<ClientPF> findAll() {
		return session.createQuery("select c from ClientPF c order by nom ").list();
		}

	public ClientPF findById(int id) {
		return (ClientPF)session.get(ClientPF.class, id);
		}
	
	@Override
	public  List<ClientPF> findListClientByCodeDepot(String codeDepot) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from ClientPF c where codeDepot=:codeDepot order by nom");
		query.setParameter("codeDepot", codeDepot);
		
		
		return query.list();
	}

	
	@Override
	public  ClientPF findClientByCodeClient(String codeClient) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from ClientPF c where code=:codeClient");
		query.setParameter("codeClient", codeClient);
		
		
		return (ClientPF) query.uniqueResult();
	}
	
	
	
	@Override
	public  List<ClientPF> findListClientByRequete(String requete) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from ClientPF c where "+requete+" order by nom");
		
		
		
		return query.list();
	}
	
	
	
	public int maxIdClientPF() {
        int id =0;
		Query query= session.createQuery("select max(id) from ClientPF");
		
		if(query.uniqueResult()==null)
			id=1;
		else 
			id= (int)query.uniqueResult();
		
		return id+217;
    }



}
