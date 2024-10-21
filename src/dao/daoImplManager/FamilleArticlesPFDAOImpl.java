package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.FamilleArticlePF;

public class FamilleArticlesPFDAOImpl implements FamilleArticlesPFDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(FamilleArticlePF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FamilleArticlePF edit(FamilleArticlePF e) {
		
	session.beginTransaction();
	FamilleArticlePF p= (FamilleArticlePF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FamilleArticlePF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FamilleArticlePF> findAll() {
		return session.createQuery("select c from FamilleArticlePF c").list();
		}

	public FamilleArticlePF findById(int id) {
		return (FamilleArticlePF)session.get(FamilleArticlePF.class, id);
		}

	
	public FamilleArticlePF findByCode(String code) {
		// TODO Auto-generated method stub
		FamilleArticlePF famileArticlepf= new FamilleArticlePF();
		Query query= session.createQuery("select c from FamilleArticlePF c where code=:code");
		query.setParameter("code", code);
		
		famileArticlepf= (FamilleArticlePF) query.uniqueResult();
		
		return famileArticlepf;
	}
	
	public FamilleArticlePF findByLibelle(String libelle) {
		// TODO Auto-generated method stub
		FamilleArticlePF famileArticlepf= new FamilleArticlePF();
		Query query= session.createQuery("select c from FamilleArticlePF c where liblle=:libelle");
		query.setParameter("libelle", libelle);
		
		famileArticlepf= (FamilleArticlePF) query.uniqueResult();
		
		return famileArticlepf;
	}


}
