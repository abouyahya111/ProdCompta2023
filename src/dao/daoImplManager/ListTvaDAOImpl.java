package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ListTvaDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.ListTva;

public class ListTvaDAOImpl implements ListTvaDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(ListTva e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ListTva edit(ListTva e) {
		
	session.beginTransaction();
	ListTva p= (ListTva)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ListTva p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<ListTva> findAll() {
		return session.createQuery("select c from ListTva c").list();
		}

	public ListTva findById(int id) {
		return (ListTva)session.get(ListTva.class, id);
		}

	@Override
	public ListTva findByCode(String code) {
		// TODO Auto-generated method stub
		ListTva ListTva= new ListTva();
		Query query= session.createQuery("select c from ListTva c where codeTva=:code");
		query.setParameter("code", code);
		
		ListTva= (ListTva) query.uniqueResult();
		
		return ListTva;
	}
	
	

}
