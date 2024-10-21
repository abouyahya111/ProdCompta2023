package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailFactureAchatDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFacturePF;
import dao.entity.DetailTransferProduitFini;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public class DetailFactureAchatDAOImpl implements DetailFactureAchatDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailFactureAchat e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailFactureAchat edit(DetailFactureAchat e) {
		
	session.beginTransaction();
	DetailFactureAchat p= (DetailFactureAchat)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailFactureAchat p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailFactureAchat> findAll() {
		return session.createQuery("select c from DetailFactureAchat c").list();
		}

	public DetailFactureAchat findById(int id) {
		return (DetailFactureAchat)session.get(DetailFactureAchat.class, id);
		}
	
	
	
	@Override
	public List<DetailFactureAchat> listeDetailFactureAchatByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAchat c where factureAchat.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	@Override
	public List<DetailFactureAchat> listeDetailFactureAchatByDate(Date dateDebut,Date dateFin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAchat c where factureAchat.dateFacture between :dateDebut and :dateFin ");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		
		return query.list();

	}
	
	
	
	public List<DetailFactureAchat> listeDetailFactureAchatByDateByMagasin(Date dateDebut,Date dateFin , Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAchat c where factureAchat.dateFacture between :dateDebut and :dateFin  and factureAchat.magasin.id=:magasin");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		 query.setParameter("magasin", magasin.getId());
		return query.list();

	}
	
	
	
	
	public void ViderSession()
	{
		if (session!=null)
		{
			session.clear();
		}
	}
	
	
	
	// liste des Articles MConsommable de Statut x entre deux date 
	
	@Override
	public List<DetailFactureAchat> ListDetailFactureAchatPFEntreDeuxDates(Date dateDebut , Date dateFin , Articles article,Magasin magasin , FamilleArticlePF familleArticle) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && article!=null && familleArticle!=null)
		{
			 query= session.createQuery("select d from DetailFactureAchat d where factureAchat.dateFacture between :dateDebut and :dateFin and article.id =:article  and factureAchat.magasin.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("article", article.getId());				
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
			
		}else if(dateDebut!=null && dateFin!=null && article==null && familleArticle!=null)
		{
			 query= session.createQuery("select d from DetailFactureAchat d where  factureAchat.dateFacture between :dateDebut and :dateFin  and factureAchat.magasin.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
				
		}else if(dateDebut!=null && dateFin!=null && article!=null && familleArticle==null)
		{
			 query= session.createQuery("select d from DetailFactureAchat d where  factureAchat.dateFacture between :dateDebut and :dateFin and article.id =:article  and factureAchat.magasin.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("article", article.getId());
		}
		
		
		else if(dateDebut!=null && dateFin!=null && article==null && familleArticle==null)
		{
			 query= session.createQuery("select d from DetailFactureAchat d where  factureAchat.dateFacture between :dateDebut and :dateFin  and factureAchat.magasin.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("magasin", magasin.getId());
				
			
		}else if(dateDebut==null && dateFin==null && article!=null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailFactureAchat d where  article.id =:article  and factureAchat.magasin.id=:magasin");
			
			 query.setParameter("article", article.getId());
			 query.setParameter("magasin", magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && article!=null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailFactureAchat d where  factureAchat.dateFacture =:dateDebut and article.id =:article  and factureAchat.magasin.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && article!=null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailFactureAchat d where  factureAchat.dateFacture =:dateDebut and article.id =:article  and factureAchat.magasin.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}else if(dateDebut!=null && dateFin==null && article==null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailFactureAchat d where  factureAchat.dateFacture =:dateDebut  and factureAchat.magasin.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}
		
		
		
		
		else if(dateDebut!=null && dateFin==null && article==null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailFactureAchat d where  factureAchat.dateFacture =:dateDebut  and factureAchat.magasin.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("magasin", magasin.getId());
		}
				
			
				
				return query.list();
}
	
	
	
	
	//list des Articles MConsommable  entre deux date distinct
	
	@Override
	public List<DetailFactureAchat> ListDetailFactureAchatPFEntreDeuxDatesBYPFDistinct(Date dateDebut , Date dateFin , Articles article,Magasin magasin , FamilleArticlePF familleArticle) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && article!=null && familleArticle!=null)
		{
			 query= session.createQuery("select d from DetailFactureAchat d where factureAchat.dateFacture between :dateDebut and :dateFin and article.id =:article and factureAchat.magasin.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle  group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("article", article.getId());
				
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}else if(dateDebut!=null && dateFin!=null && article==null && familleArticle==null)
		{
			 query= session.createQuery("select d from DetailFactureAchat d where  factureAchat.dateFacture between :dateDebut and :dateFin and factureAchat.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				
				 query.setParameter("magasin", magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && article!=null && familleArticle==null)
		{
			 query= session.createQuery("select d from DetailFactureAchat d where  article.id =:article and  factureAchat.dateFacture between :dateDebut and :dateFin and factureAchat.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("article", article.getId());
		}else if(dateDebut!=null && dateFin!=null && article==null && familleArticle!=null)
		{
			 query= session.createQuery("select d from DetailFactureAchat d where  sousFamille.famileArticlePF.id =:familleArticle and  factureAchat.dateFacture between :dateDebut and :dateFin and factureAchat.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}
		else if(dateDebut==null && dateFin==null && article!=null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailFactureAchat d where  article.id=:article   and factureAchat.magasin.id=:magasin group by article,sousFamille");
			
			 query.setParameter("article", article.getId());
			
			 query.setParameter("magasin", magasin.getId());
			
		}
		else if(dateDebut==null && dateFin==null && article==null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailFactureAchat d where  sousFamille.famileArticlePF.id =:familleArticle   and factureAchat.magasin.id=:magasin group by article,sousFamille");
			
			 query.setParameter("familleArticle", familleArticle.getId());
			
			 query.setParameter("magasin", magasin.getId());
			
		}
		else if(dateDebut!=null && dateFin==null && article!=null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailFactureAchat d where factureAchat.dateFacture =:dateDebut and article.id=:article and  transferStockPF.statut=:statut and factureAchat.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				
				 query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && article==null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailFactureAchat d where factureAchat.dateFacture =:dateDebut  and factureAchat.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				
				 query.setParameter("magasin", magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && article==null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailFactureAchat d where sousFamille.famileArticlePF.id =:familleArticle and factureAchat.dateFacture =:dateDebut  and factureAchat.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
				 
		}else if(dateDebut!=null && dateFin==null && article!=null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailFactureAchat d where sousFamille.famileArticlePF.id =:familleArticle and article.id=:article and factureAchat.dateFacture =:dateDebut  and factureAchat.magasin.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("article", article.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}
				
			
				
				return query.list();
}	

	
	//liste des Facture Achat Article entre deux date
	@Override
	public List<DetailFactureAchat> findDetailAchatArticleEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,Fournisseur fournisseur, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle) {
		
		// TODO Auto-generated method stub
		Query query=null;
	
		if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur==null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAchat c where factureAchat.dateFacture between :dateDebut and :datedeFin   and factureAchat.magasin.id=:magasin order by factureAchat.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAchat c where factureAchat.fournisseur.id=:fournisseur and factureAchat.dateFacture between :dateDebut and :datedeFin   and factureAchat.magasin.id=:magasin order by factureAchat.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("fournisseur", fournisseur.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAchat c where factureAchat.fournisseur.id=:fournisseur and factureAchat.dateFacture between :dateDebut and :datedeFin  and factureAchat.magasin.id=:magasin order by factureAchat.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("fournisseur", fournisseur.getId());	
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureAchat c where factureAchat.fournisseur.id=:fournisseur and factureAchat.dateFacture between :dateDebut and :datedeFin  and factureAchat.magasin.id=:magasin order by factureAchat.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("fournisseur", fournisseur.getId());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAchat c where  factureAchat.dateFacture between :dateDebut and :datedeFin  and factureAchat.magasin.id=:magasin order by factureAchat.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAchat c where  factureAchat.dateFacture between :dateDebut and :datedeFin  and factureAchat.magasin.id=:magasin order by factureAchat.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureAchat c where factureAchat.fournisseur.id=:fournisseur and factureAchat.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille  and factureAchat.magasin.id=:magasin order by factureAchat.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("fournisseur", fournisseur.getId());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureAchat c where  factureAchat.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille  and factureAchat.magasin.id=:magasin order by factureAchat.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureAchat c where  factureAchat.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille  and factureAchat.magasin.id=:magasin order by factureAchat.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAchat c where  factureAchat.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article  and factureAchat.magasin.id=:magasin order by factureAchat.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureAchat c where factureAchat.fournisseur.id=:fournisseur and factureAchat.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille  and factureAchat.magasin.id=:magasin order by factureAchat.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("fournisseur", fournisseur.getId());	
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAchat c where factureAchat.fournisseur.id=:fournisseur and factureAchat.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article  and factureAchat.magasin.id=:magasin order by factureAchat.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("fournisseur", fournisseur.getId());	
		}
			
	
				
				return  query.list();
}
	
	
	
	
	//liste des Facture Achat PF
		@Override
		public List<Object[]> findDetailAchatPF(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,Fournisseur fournisseur) {
			
			// TODO Auto-generated method stub
			Query query=null;
		
			 if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur!=null )
			{
				 query= session.createQuery("select c.factureAchat.dateFacture, c.factureAchat.numFacture ,c.factureAchat.fournisseur.nom, sum(c.montantTTC)  from DetailFactureAchat c where factureAchat.fournisseur.id=:fournisseur and factureAchat.dateFacture between :dateDebut and :datedeFin   and factureAchat.magasin.id=:magasin group by c.factureAchat.numFacture order by factureAchat.dateFacture");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
					
					query.setParameter("fournisseur", fournisseur.getId());
			}else if(dateDebut!=null && datedeFin!=null && magasin !=null && fournisseur==null)
			{
				 query= session.createQuery("select c.factureAchat.dateFacture, c.factureAchat.numFacture ,c.factureAchat.fournisseur.nom,sum(c.montantTTC)  from DetailFactureAchat from DetailFactureAchat c where  factureAchat.dateFacture between :dateDebut and :datedeFin  and factureAchat.magasin.id=:magasin group by c.factureAchat.numFacture order by factureAchat.dateFacture");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
					
					
			}	
		
					
					return  query.list();
	}
		
		
		
		public List<Object[]> listeEtatPrixMoyen(Date dateDebut,Date dateFin , Magasin magasin, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle , SousFamilleArticlePF sousfamille , String tousSousfamille) {
			// TODO Auto-generated method stub
			Query query=null;
			

			
			if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,case when d.article.centreCout2=:packet then (sum(d.quantite) * d.article.conditionnement * d.article.conditionnementcaisse)  else sum(d.quantite) end ,case when d.article.centreCout2=:packet   then (sum(d.montantHT) / sum(d.quantite)  /d.article.conditionnement /d.article.conditionnementcaisse)  else (sum(d.montantHT) / sum(d.quantite)) end , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchat d where (factureAchat.dateFacture between :dateDebut and :dateFin) and factureAchat.magasin.id=:magasin  and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("packet", Constantes.ARTICLE_PACKET);	
				
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS  && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,case when d.article.centreCout2=:packet then (sum(d.quantite) * d.article.conditionnement * d.article.conditionnementcaisse)  else sum(d.quantite) end ,case when d.article.centreCout2=:packet   then (sum(d.montantHT) / sum(d.quantite) / d.article.conditionnement /d.article.conditionnementcaisse ) else (sum(d.montantHT) / sum(d.quantite)) end , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchat d where (factureAchat.dateFacture between :dateDebut and :dateFin) and factureAchat.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				query.setParameter("packet", Constantes.ARTICLE_PACKET);	
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS  )
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,case when d.article.centreCout2=:packet then (sum(d.quantite) * d.article.conditionnement * d.article.conditionnementcaisse)  else sum(d.quantite) end ,case when d.article.centreCout2=:packet   then (sum(d.montantHT) / sum(d.quantite) / d.article.conditionnement / d.article.conditionnementcaisse)  else (sum(d.montantHT) / sum(d.quantite)) end , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchat d where (factureAchat.dateFacture between :dateDebut and :dateFin) and factureAchat.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("packet", Constantes.ARTICLE_PACKET);	
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS )
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,case when d.article.centreCout2=:packet then (sum(d.quantite) * d.article.conditionnement * d.article.conditionnementcaisse)  else sum(d.quantite) end ,case when d.article.centreCout2=:packet   then (sum(d.montantHT) / sum(d.quantite) / d.article.conditionnement / d.article.conditionnementcaisse)  else (sum(d.montantHT) / sum(d.quantite)) end , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchat d where (factureAchat.dateFacture between :dateDebut and :dateFin) and factureAchat.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				query.setParameter("packet", Constantes.ARTICLE_PACKET);	
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS )
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,case when d.article.centreCout2=:packet then (sum(d.quantite) * d.article.conditionnement * d.article.conditionnementcaisse ) else sum(d.quantite) end ,case when d.article.centreCout2=:packet   then (sum(d.montantHT) / sum(d.quantite) / d.article.conditionnement /d.article.conditionnementcaisse)  else (sum(d.montantHT) / sum(d.quantite)) end , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchat d where (factureAchat.dateFacture between :dateDebut and :dateFin) and factureAchat.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille  and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("packet", Constantes.ARTICLE_PACKET);	
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS )
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,case when d.article.centreCout2=:packet then (sum(d.quantite) * d.article.conditionnement * d.article.conditionnementcaisse)  else sum(d.quantite) end ,case when d.article.centreCout2=:packet   then (sum(d.montantHT) / sum(d.quantite) / d.article.conditionnement / d.article.conditionnementcaisse ) else (sum(d.montantHT) / sum(d.quantite)) end , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchat d where (factureAchat.dateFacture between :dateDebut and :dateFin) and factureAchat.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				query.setParameter("packet", Constantes.ARTICLE_PACKET);	
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS )
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,case when d.article.centreCout2=:packet then (sum(d.quantite) * d.article.conditionnement * d.article.conditionnementcaisse)  else sum(d.quantite) end ,case when d.article.centreCout2=:packet   then (sum(d.montantHT) / sum(d.quantite) / d.article.conditionnement / d.article.conditionnementcaisse)  else (sum(d.montantHT) / sum(d.quantite) ) end , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchat d where (factureAchat.dateFacture between :dateDebut and :dateFin) and factureAchat.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("packet", Constantes.ARTICLE_PACKET);	
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
            query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,case when d.article.centreCout2=:packet then (sum(d.quantite) * d.article.conditionnement * d.article.conditionnementcaisse) else sum(d.quantite) end ,case when d.article.centreCout2=:packet   then (sum(d.montantHT) / sum(d.quantite) / d.article.conditionnement / d.article.conditionnementcaisse ) else (sum(d.montantHT) / sum(d.quantite)) end , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchat d where (factureAchat.dateFacture between :dateDebut and :dateFin) and factureAchat.magasin.id=:magasin  and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("packet", Constantes.ARTICLE_PACKET);	
			}
			
			
			
			return query.list();	
			
			
			
		}
		
		
	
	
	


}
