package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.DetailEstimationMPDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailPrixArticle;
import dao.entity.Production;

public class DetailPrixArticleDAOImpl implements DetailPrixArticleDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailPrixArticle e) {
	
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		
		//return p;
	}

	public DetailPrixArticle edit(DetailPrixArticle e) {
		
		session.beginTransaction();
		DetailPrixArticle p= (DetailPrixArticle)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailPrixArticle p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailPrixArticle> findAll() {
		
		Query query= session.createQuery("select c from DetailPrixArticle c");
		List<DetailPrixArticle> list=query.list();
	
		return list;
				
		}

	public DetailPrixArticle findById(int id) {
		
		DetailPrixArticle detailPrixArticle= (DetailPrixArticle)session.get(DetailPrixArticle.class, id);
		
		return detailPrixArticle;
		}

	public List<DetailPrixArticle>  findByIdArticle(int id) {
		
		Query query= session.createQuery("select c from DetailPrixArticle c where articles.id=:idarticle");
		query.setParameter("idarticle", id);
		List<DetailPrixArticle> list=query.list();
		
		return list;
		
		}
	
	@Override
	public DetailPrixArticle  findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(int idarticle,int idmagasin , int idfamillearticle,int idfsousamillearticle) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select d from DetailPrixArticle d where articles.id=:idarticle and magasin.id=:idmagasin and familleArticlePF.id=:idfamillearticle and sousFamilleArticlePF.id=:idfsousamillearticle and clientPF=null");
		query.setParameter("idarticle", idarticle);
		query.setParameter("idmagasin", idmagasin);
		query.setParameter("idfamillearticle", idfamillearticle);
		query.setParameter("idfsousamillearticle", idfsousamillearticle);
		
		return (DetailPrixArticle) query.uniqueResult();
		
	}
	
	@Override
	public DetailPrixArticle  findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient(int idarticle,int idmagasin , int idfamillearticle,int idfsousamillearticle , int idclient) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select d from DetailPrixArticle d where articles.id=:idarticle and magasin.id=:idmagasin and familleArticlePF.id=:idfamillearticle and sousFamilleArticlePF.id=:idfsousamillearticle and clientPF.id=:idclient");
		query.setParameter("idarticle", idarticle);
		query.setParameter("idclient", idclient);
		query.setParameter("idmagasin", idmagasin);
		query.setParameter("idfamillearticle", idfamillearticle);
		query.setParameter("idfsousamillearticle", idfsousamillearticle);
		
		return (DetailPrixArticle) query.uniqueResult();
		
	}
	
	@Override
	public List<DetailPrixArticle>  findDetaileArticleByArticleByMagasin(int idarticle,int idmagasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select d from DetailPrixArticle d where articles.id=:idarticle and magasin.id=:idmagasin");
		query.setParameter("idarticle", idarticle);
		query.setParameter("idmagasin", idmagasin);
		List<DetailPrixArticle> list=query.list();
		
		return list;
		
	}
	
	@Override
	public List<DetailPrixArticle>  findDetaileArticleBySousFamilleByMagasin(int idsousfamille,int idmagasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select d from DetailPrixArticle d where sousFamilleArticlePF.id=:idsousfamille and magasin.id=:idmagasin");
		query.setParameter("idmagasin", idmagasin);
		query.setParameter("idsousfamille", idsousfamille);
		List<DetailPrixArticle> list=query.list();
		
		return list;
		
	}


}
