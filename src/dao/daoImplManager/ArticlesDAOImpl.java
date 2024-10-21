package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;

public class ArticlesDAOImpl implements ArticlesDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(Articles e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Articles edit(Articles e) {
		
	session.beginTransaction();
	Articles p= (Articles)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Articles p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Articles> findAll() {
		return session.createQuery("select c from Articles c order by c.liblle").list();
		}

	public Articles findById(int id) {
		return (Articles)session.get(Articles.class, id);
		}

	@Override
	public Articles findByCode(String code) {
		// TODO Auto-generated method stub
		Articles articles= new Articles();
		 Query query= session.createQuery("select c from Articles c where codeArticle=:code");
		 
		query.setParameter("code", code);
		
		articles= (Articles) query.uniqueResult();
		
		return articles;
	}
	
	
	@Override
	public Articles findBylibelle(String liblle) {
		// TODO Auto-generated method stub
		Articles articles= new Articles();
		Query query= session.createQuery("select c from Articles c where liblle=:liblle");
		query.setParameter("liblle", liblle);
		
		articles= (Articles) query.uniqueResult();
		
		return articles;
	}
	
	@Override
	public List<DetailEstimation> listeMatierePremierByArticleByMP(int id,String codeSubCat, int priorite) {
		// TODO Auto-generated method stub
		Articles articles= new Articles();
		Query query= session.createQuery("select c from DetailEstimation c where articles.id=:id and matierePremier.categorieMp.subCategorieMp.code=:codeSubCat and priorite=:priorite");
		query.setParameter("id", id);
		query.setParameter("codeSubCat", codeSubCat);
		query.setParameter("priorite", priorite);
		return query.list();
		
		
	}
	
	@Override
	public List<Articles> listeArticleBySousFamille(int id_sousfamille) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from Articles c where sousFamilleArticle.id=:id_sousfamille");
		query.setParameter("id_sousfamille", id_sousfamille);
		
		return query.list();
		
		
	}
	
	
	
	@Override
	public List<Articles> listeArticleMConsommable(String MConsommable) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from Articles c where codeFonction=:MConsommable");
		query.setParameter("MConsommable", MConsommable);
		
		return query.list();
		
		
	}
	
	
	@Override
	public List<Articles> listeArticleGratuite() {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from Articles c where id in (113,114,115,124,125,126,127,128,130,131,132,135,136,137,143,144,145,148,154,156,160)");
		
		
		return query.list();
		
		
	}
	
	
	@Override
	public Articles findByCodeBysousFamille(String code,int id_sousfamille) {
		// TODO Auto-generated method stub
		Articles articles= new Articles();
		Query query= session.createQuery("select c from Articles c where codeArticle=:code and sousFamilleArticle.id=:id_sousfamille");
		query.setParameter("code", code);
		query.setParameter("id_sousfamille", id_sousfamille);
		
		articles= (Articles) query.uniqueResult();
		
		return articles;
	}
	
	public List<Articles> listeArticleByCodeOffre(String codeTherre,String codeVerre) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from Articles c where codeArticle=:codetherres or codeArticle=:codeVerres");
		query.setParameter("codetherres", codeTherre);
		query.setParameter("codeVerres", codeVerre);
		
		return query.list();
	}


}
