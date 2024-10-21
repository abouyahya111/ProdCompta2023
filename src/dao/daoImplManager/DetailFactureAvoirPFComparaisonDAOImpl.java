package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DetailFactureAchatDAO;
import dao.daoManager.DetailFactureAvoirPFComparaisonDAO;
import dao.daoManager.DetailFactureAvoirPFDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.Articles;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFactureAvoirPFComparaison;
import dao.entity.DetailFacturePF;
import dao.entity.DetailTransferProduitFini;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;

public class DetailFactureAvoirPFComparaisonDAOImpl implements DetailFactureAvoirPFComparaisonDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailFactureAvoirPFComparaison e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailFactureAvoirPFComparaison edit(DetailFactureAvoirPFComparaison e) {
		
	session.beginTransaction();
	DetailFactureAvoirPFComparaison p= (DetailFactureAvoirPFComparaison)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailFactureAvoirPFComparaison p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailFactureAvoirPFComparaison> findAll() {
		return session.createQuery("select c from DetailFactureAvoirPFComparaison c").list();
		}

	public DetailFactureAvoirPFComparaison findById(int id) {
		return (DetailFactureAvoirPFComparaison)session.get(DetailFactureAvoirPFComparaison.class, id);
		}
	
	
	
	@Override
	public List<DetailFactureAvoirPFComparaison> listeDetailFactureAvoirByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAvoirPFComparaison c where factureAvoirPf.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	@Override
	public List<DetailFactureAvoirPFComparaison> listeDetailFactureAvoirByDate(Date dateDebut,Date dateFin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAvoirPFComparaison c where factureAvoirPf.dateFacture between :dateDebut and :dateFin ");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		
		return query.list();

	}
	
	// liste des Articles de Statut x entre deux date
	
	@Override
	public List<DetailFactureAvoirPFComparaison> ListDetailFactureAvoirPFComparaisonEntreDeuxDatesBYPFStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin , FamilleArticlePF familleArticle) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && article!=null && familleArticle!=null)
		{
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where factureAvoirPf.dateFacture between :dateDebut and :dateFin and article.id =:article and  factureAvoirPf.statut=:statut and factureAvoirPf.magasin.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
			
		}else if(dateDebut!=null && dateFin!=null && article==null && familleArticle!=null)
		{
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where  factureAvoirPf.dateFacture between :dateDebut and :dateFin and  factureAvoirPf.statut=:statut and factureAvoirPf.magasin.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
				
		}else if(dateDebut!=null && dateFin!=null && article!=null && familleArticle==null)
		{
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where  factureAvoirPf.dateFacture between :dateDebut and :dateFin and article.id =:article and  factureAvoirPf.statut=:statut and factureAvoirPf.magasin.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("article", article.getId());
		}
		
		
		else if(dateDebut!=null && dateFin!=null && article==null && familleArticle==null)
		{
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where  factureAvoirPf.dateFacture between :dateDebut and :dateFin and  factureAvoirPf.statut=:statut and factureAvoirPf.magasin.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
			
		}else if(dateDebut==null && dateFin==null && article!=null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where  article.id =:article and  factureAvoirPf.statut=:statut and factureAvoirPf.magasin.id=:magasin");
			
			 query.setParameter("article", article.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin", magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && article!=null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where  factureAvoirPf.dateFacture =:dateDebut and article.id =:article and  factureAvoirPf.statut=:statut and factureAvoirPf.magasin.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && article!=null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where  factureAvoirPf.dateFacture =:dateDebut and article.id =:article and  factureAvoirPf.statut=:statut and factureAvoirPf.magasin.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}else if(dateDebut!=null && dateFin==null && article==null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where  factureAvoirPf.dateFacture =:dateDebut and  factureAvoirPf.statut=:statut and factureAvoirPf.magasin.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}
		
		
		
		
		else if(dateDebut!=null && dateFin==null && article==null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where  factureAvoirPf.dateFacture =:dateDebut and  factureAvoirPf.statut=:statut and factureAvoirPf.magasin.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
		}
				
			
				
				return query.list();
}
	
	
	
	//list des Articles de Statut X et entre deux date distinct
	
	@Override
	public List<DetailFactureAvoirPFComparaison> ListDetailFactureAvoirPFComparaisonEntreDeuxDatesBYPFDistinctByStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin , FamilleArticlePF familleArticle) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && article!=null && familleArticle!=null)
		{
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where factureAvoirPf.dateFacture between :dateDebut and :dateFin and article.id =:article and factureAvoirPf.magasin.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle and  factureAvoirPf.statut=:statut group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}else if(dateDebut!=null && dateFin!=null && article==null && familleArticle==null)
		{
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where factureAvoirPf.statut=:statut and  factureAvoirPf.dateFacture between :dateDebut and :dateFin and factureAvoirPf.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && article!=null && familleArticle==null)
		{
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where factureAvoirPf.statut=:statut and article.id =:article and  factureAvoirPf.dateFacture between :dateDebut and :dateFin and factureAvoirPf.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("article", article.getId());
		}else if(dateDebut!=null && dateFin!=null && article==null && familleArticle!=null)
		{
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where factureAvoirPf.statut=:statut and sousFamille.famileArticlePF.id =:familleArticle and  factureAvoirPf.dateFacture between :dateDebut and :dateFin and factureAvoirPf.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}
		else if(dateDebut==null && dateFin==null && article!=null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where  article.id=:article and  factureAvoirPf.statut=:statut  and factureAvoirPf.magasin.id=:magasin group by article,sousFamille");
			
			 query.setParameter("article", article.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin", magasin.getId());
			
		}
		else if(dateDebut==null && dateFin==null && article==null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where  sousFamille.famileArticlePF.id =:familleArticle and  factureAvoirPf.statut=:statut  and factureAvoirPf.magasin.id=:magasin group by article,sousFamille");
			
			 query.setParameter("familleArticle", familleArticle.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin", magasin.getId());
			
		}
		else if(dateDebut!=null && dateFin==null && article!=null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where factureAvoirPf.dateFacture =:dateDebut and article.id=:article and  factureAvoirPf.statut=:statut and factureAvoirPf.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && article==null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where factureAvoirPf.dateFacture =:dateDebut and  factureAvoirPf.statut=:statut and factureAvoirPf.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && article==null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where sousFamille.famileArticlePF.id =:familleArticle and factureAvoirPf.dateFacture =:dateDebut and  factureAvoirPf.statut=:statut and factureAvoirPf.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
				 
		}else if(dateDebut!=null && dateFin==null && article!=null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailFactureAvoirPFComparaison d where sousFamille.famileArticlePF.id =:familleArticle and article.id=:article and factureAvoirPf.dateFacture =:dateDebut and  factureAvoirPf.statut=:statut and factureAvoirPf.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("article", article.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}
				
			
				
				return query.list();
}
	
	
	
	
	
	public void ViderSession()
	{
		if (session!=null)
		{
			session.clear();
		}
	}
	


}
