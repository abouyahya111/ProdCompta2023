package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesClientDAO;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DetailArticlesClientDAO;
import dao.entity.Articles;
import dao.entity.ArticlesClient;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailArticlesClient;
import dao.entity.DetailEstimation;
import dao.entity.DetailFacturePF;

public class DetailArticlesClientDAOImpl implements DetailArticlesClientDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailArticlesClient e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailArticlesClient edit(DetailArticlesClient e) {
		
	session.beginTransaction();
	DetailArticlesClient p= (DetailArticlesClient)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailArticlesClient p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailArticlesClient> findAll() {
		return session.createQuery("select c from DetailArticlesClient c ").list();
		}

	public DetailArticlesClient findById(int id) {
		return (DetailArticlesClient)session.get(DetailArticlesClient.class, id);
		}


	@Override
	public List<DetailArticlesClient> listeDetailArticlesClientByArticlesClient(int articlesclient) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailArticlesClient c where articlesclient.id=:articlesclient");
		query.setParameter("articlesclient", articlesclient);
		
		return query.list();
		
		
	}


}
