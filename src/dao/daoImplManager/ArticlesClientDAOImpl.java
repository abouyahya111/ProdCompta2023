package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesClientDAO;
import dao.daoManager.ArticlesDAO;
import dao.entity.Articles;
import dao.entity.ArticlesClient;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailEstimation;

public class ArticlesClientDAOImpl implements ArticlesClientDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(ArticlesClient e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ArticlesClient edit(ArticlesClient e) {
		
	session.beginTransaction();
	ArticlesClient p= (ArticlesClient)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ArticlesClient p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<ArticlesClient> findAll() {
		return session.createQuery("select c from ArticlesClient c ").list();
		}

	public ArticlesClient findById(int id) {
		return (ArticlesClient)session.get(ArticlesClient.class, id);
		}

	public ArticlesClient findByClientByDepot(ClientPF clientPF , Depot depot) {
		
		Query query= session.createQuery("select c from ArticlesClient c where clientPF.id=:clientPF and depot.id=:depot");
		query.setParameter("depot", depot.getId());
		query.setParameter("clientPF", clientPF.getId());
		
		
		return (ArticlesClient) query.uniqueResult();
		
		}
	


}
