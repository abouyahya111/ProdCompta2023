package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ClientDAO;
import dao.daoManager.VilleDAO;
import dao.entity.Client;
import dao.entity.Ville;
import dao.entity.Machine;
import dao.entity.Ville;

public class VilleDAOImpl implements VilleDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(Ville e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Ville edit(Ville e) {
		
	session.beginTransaction();
	Ville p= (Ville)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Ville p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Ville> findAll() {
		return session.createQuery("select c from Ville c order by ville ").list();
		}

	public Ville findById(int id) {
		return (Ville)session.get(Ville.class, id);
		}
	


	
	@Override
	public  Ville findVilleByCodeVille(String codeVille) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from Ville c where code=:codeVille");
		query.setParameter("codeVille", codeVille);
		
		
		return (Ville) query.uniqueResult();
	}
	
	
	
	@Override
	public  Ville findVilleByVille(String ville) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from Ville c where ville=:ville");
		query.setParameter("ville", ville);
		
		
		return (Ville) query.uniqueResult();
	}
	
	
	
	@Override
	public  List<Ville> findListVilleByRequete(String requete) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from Ville c where "+requete+" order by ville");
		
		
		
		return query.list();
	}
	
	
	
	public int maxIdVille() {
        int id =0;
		Query query= session.createQuery("select max(id) from Ville");
		
		if(query.uniqueResult()==null)
			id=1;
		else 
			id= (int)query.uniqueResult();
		
		return id+217;
    }



}
