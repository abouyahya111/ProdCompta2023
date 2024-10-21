package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.FamilleArticlePF;
import dao.entity.SousFamilleArticlePF;

public class SousFamilleArticlesPFDAOImpl implements SousFamilleArticlesPFDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(SousFamilleArticlePF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public SousFamilleArticlePF edit(SousFamilleArticlePF e) {
		
	session.beginTransaction();
	SousFamilleArticlePF p= (SousFamilleArticlePF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		SousFamilleArticlePF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<SousFamilleArticlePF> findAll() {
		return session.createQuery("select c from SousFamilleArticlePF c").list();
		}

	public SousFamilleArticlePF findById(int id) {
		return (SousFamilleArticlePF)session.get(SousFamilleArticlePF.class, id);
		}

	
	public SousFamilleArticlePF findByCode(String code) {
		// TODO Auto-generated method stub
		SousFamilleArticlePF SousFamileArticlepf= new SousFamilleArticlePF();
		Query query= session.createQuery("select c from SousFamilleArticlePF c where code=:code");
		query.setParameter("code", code);
		
		SousFamileArticlepf= (SousFamilleArticlePF) query.uniqueResult();
		
		return SousFamileArticlepf;
	}
	
	public SousFamilleArticlePF findByLibelle(String libelle) {
		// TODO Auto-generated method stub
		SousFamilleArticlePF SousFamileArticlepf= new SousFamilleArticlePF();
		Query query= session.createQuery("select c from SousFamilleArticlePF c where liblle=:libelle");
		query.setParameter("libelle", libelle);
		
		SousFamileArticlepf= (SousFamilleArticlePF) query.uniqueResult();
		
		return SousFamileArticlepf;
	}
	
	
	@Override
	public List<SousFamilleArticlePF> listeSousFamillePFByFamilleArticlePF(int idFamileArticlePF) {
		// TODO Auto-generated method stub
		SousFamilleArticlePF sousFamileArticlepf= new SousFamilleArticlePF();
		Query query= session.createQuery("select c from SousFamilleArticlePF c where famileArticlePF.id=:idFamileArticlePF");
		query.setParameter("idFamileArticlePF", idFamileArticlePF);
		return query.list();
		
		
	}

	@Override
	public List<SousFamilleArticlePF> listeSousFamillePFByFamilleArticlePFOffre(String codetherres, String codeverres) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from SousFamilleArticlePF c where code=:codetherres or code=:codeverres");
		query.setParameter("codetherres", codetherres);
		query.setParameter("codeverres", codeverres);
		return query.list();
		
		
	}
	
	


}
