package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailFactureAvoirPFDAO;
import dao.daoManager.DetailFactureAvoirPFDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.Articles;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFacturePF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public class DetailFactureAvoirPFDAOImpl implements DetailFactureAvoirPFDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailFactureAvoirPF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailFactureAvoirPF edit(DetailFactureAvoirPF e) {
		
	session.beginTransaction();
	DetailFactureAvoirPF p= (DetailFactureAvoirPF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailFactureAvoirPF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailFactureAvoirPF> findAll() {
		return session.createQuery("select c from DetailFactureAvoirPF c").list();
		}

	public DetailFactureAvoirPF findById(int id) {
		return (DetailFactureAvoirPF)session.get(DetailFactureAvoirPF.class, id);
		}
	
	
	
	@Override
	public List<DetailFactureAvoirPF> listeDetailFactureAvoirByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAvoirPF c where factureAvoirPf.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	@Override
	public List<DetailFactureAvoirPF> listeDetailFactureAvoirByDate(Date dateDebut,Date dateFin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAvoirPF c where factureAvoirPf.dateFacture between :dateDebut and :dateFin ");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		
		return query.list();

	}
	
	
	
	@Override
	public List<DetailFactureAvoirPF> listeDetailFactureAvoirByDate(Date dateDebut,Date dateFin , Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAvoirPF c where factureAvoirPf.dateFacture between :dateDebut and :dateFin and factureAvoirPf.magasin.id=:magasin");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		return query.list();

	}
	
	
	//liste des Facture Avoir Article entre deux date
		@Override
		public List<DetailFactureAvoirPF> findDetailAvoirArticleEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,Fournisseur Fournisseur, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle) {
			
			// TODO Auto-generated method stub
			Query query=null;
		
			if(dateDebut!=null && datedeFin!=null && magasin !=null && Fournisseur==null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
			{
				 query= session.createQuery("select c from DetailFactureAvoirPF c where factureAvoirPf.dateFacture between :dateDebut and :datedeFin   and factureAvoirPf.magasin.id=:magasin order by factureAvoirPf.dateFacture ");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
					
			}else if(dateDebut!=null && datedeFin!=null && magasin !=null && Fournisseur!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
			{
				 query= session.createQuery("select c from DetailFactureAvoirPF c where factureAvoirPf.fournisseur.id=:Fournisseur and factureAvoirPf.dateFacture between :dateDebut and :datedeFin   and factureAvoirPf.magasin.id=:magasin order by factureAvoirPf.dateFacture");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
					
					query.setParameter("Fournisseur", Fournisseur.getId());
			}else if(dateDebut!=null && datedeFin!=null && magasin !=null && Fournisseur!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
			{
				 query= session.createQuery("select c from DetailFactureAvoirPF c where factureAvoirPf.fournisseur.id=:Fournisseur and factureAvoirPf.dateFacture between :dateDebut and :datedeFin  and factureAvoirPf.magasin.id=:magasin order by factureAvoirPf.dateFacture");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
					
					query.setParameter("Fournisseur", Fournisseur.getId());	
				
			}else if(dateDebut!=null && datedeFin!=null && magasin !=null && Fournisseur!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
			{
				
				 query= session.createQuery("select c from DetailFactureAvoirPF c where factureAvoirPf.fournisseur.id=:Fournisseur and factureAvoirPf.dateFacture between :dateDebut and :datedeFin  and factureAvoirPf.magasin.id=:magasin order by factureAvoirPf.dateFacture");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
				
					query.setParameter("Fournisseur", Fournisseur.getId());	
			}else if(dateDebut!=null && datedeFin!=null && magasin !=null && Fournisseur==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
			{
				 query= session.createQuery("select c from DetailFactureAvoirPF c where  factureAvoirPf.dateFacture between :dateDebut and :datedeFin  and factureAvoirPf.magasin.id=:magasin order by factureAvoirPf.dateFacture");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
					
					
			}else if(dateDebut!=null && datedeFin!=null && magasin !=null && Fournisseur==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
			{
				 query= session.createQuery("select c from DetailFactureAvoirPF c where  factureAvoirPf.dateFacture between :dateDebut and :datedeFin  and factureAvoirPf.magasin.id=:magasin order by factureAvoirPf.dateFacture");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
					
			}else if(dateDebut!=null && datedeFin!=null && magasin !=null && Fournisseur!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
			{
				

				 query= session.createQuery("select c from DetailFactureAvoirPF c where factureAvoirPf.fournisseur.id=:Fournisseur and factureAvoirPf.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille  and factureAvoirPf.magasin.id=:magasin order by factureAvoirPf.dateFacture");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
					
					query.setParameter("famille", familleArticle.getId());
					query.setParameter("Fournisseur", Fournisseur.getId());	
			}else if(dateDebut!=null && datedeFin!=null && magasin !=null && Fournisseur==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
			{
				

				 query= session.createQuery("select c from DetailFactureAvoirPF c where  factureAvoirPf.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille  and factureAvoirPf.magasin.id=:magasin order by factureAvoirPf.dateFacture");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
					
					query.setParameter("famille", familleArticle.getId());
					
				
			}else if(dateDebut!=null && datedeFin!=null && magasin !=null && Fournisseur==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
			{
				
				 query= session.createQuery("select c from DetailFactureAvoirPF c where  factureAvoirPf.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille  and factureAvoirPf.magasin.id=:magasin order by factureAvoirPf.dateFacture");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
					
					query.setParameter("famille", familleArticle.getId());
					
			}else if(dateDebut!=null && datedeFin!=null && magasin !=null && Fournisseur==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
			{
				 query= session.createQuery("select c from DetailFactureAvoirPF c where  factureAvoirPf.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article  and factureAvoirPf.magasin.id=:magasin order by factureAvoirPf.dateFacture");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
					
					query.setParameter("famille", familleArticle.getId());
					query.setParameter("article", article.getId());
			}else if(dateDebut!=null && datedeFin!=null && magasin !=null && Fournisseur!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
			{
				
				 query= session.createQuery("select c from DetailFactureAvoirPF c where factureAvoirPf.fournisseur.id=:Fournisseur and factureAvoirPf.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille  and factureAvoirPf.magasin.id=:magasin order by factureAvoirPf.dateFacture");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
					
					query.setParameter("famille", familleArticle.getId());
					query.setParameter("Fournisseur", Fournisseur.getId());	
					
			}else if(dateDebut!=null && datedeFin!=null && magasin !=null && Fournisseur!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
			{
				 query= session.createQuery("select c from DetailFactureAvoirPF c where factureAvoirPf.fournisseur.id=:Fournisseur and factureAvoirPf.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article  and factureAvoirPf.magasin.id=:magasin order by factureAvoirPf.dateFacture");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("datedeFin", datedeFin);
					query.setParameter("magasin", magasin.getId());
					
					query.setParameter("famille", familleArticle.getId());
					query.setParameter("article", article.getId());
					query.setParameter("Fournisseur", Fournisseur.getId());	
			}
				
		
					
					return  query.list();
	}
		
		
		@Override
		public List<Object[]> listeEtatPrixMoyen(Date dateDebut,Date dateFin , Magasin magasin, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle, String type , SousFamilleArticlePF sousfamille , String tousSousfamille) {
			// TODO Auto-generated method stub
			Query query=null;
		
				

				

				
				
				if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS)
				{
					
					query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAvoirPF d where (d.factureAvoirPf.dateFacture between :dateDebut and :dateFin) and d.factureAvoirPf.magasin.id=:magasin   GROUP BY  d.article.liblle , d.sousFamille.liblle");
					
					
					query.setParameter("magasin", magasin.getId());
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);		
					
					
					
				}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS  && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
				{
					
					query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAvoirPF d where (factureAvoirPf.dateFacture between :dateDebut and :dateFin) and factureAvoirPf.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille  GROUP BY  d.article.liblle , d.sousFamille.liblle");
					
					
					query.setParameter("magasin", magasin.getId());
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);		
					
					query.setParameter("famille", familleArticle.getId());
					query.setParameter("sousfamille", sousfamille.getId());
					
				}
				else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS  )
				{
					
					query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAvoirPF d where (factureAvoirPf.dateFacture between :dateDebut and :dateFin) and factureAvoirPf.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille    GROUP BY  d.article.liblle , d.sousFamille.liblle");
					
					
					query.setParameter("magasin", magasin.getId());
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);		
					
					query.setParameter("famille", familleArticle.getId());
					
					
				}
				
				else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS )
				{
					
					query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAvoirPF d where (factureAvoirPf.dateFacture between :dateDebut and :dateFin) and factureAvoirPf.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.article.id=:article   GROUP BY  d.article.liblle , d.sousFamille.liblle");
					
					
					query.setParameter("magasin", magasin.getId());
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);		
					
					query.setParameter("famille", familleArticle.getId());
					query.setParameter("article", article.getId());
					query.setParameter("sousfamille", sousfamille.getId());
					
				}
				else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS )
				{
					
					query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAvoirPF d where (factureAvoirPf.dateFacture between :dateDebut and :dateFin) and factureAvoirPf.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille  and d.article.id=:article   GROUP BY  d.article.liblle , d.sousFamille.liblle");
					
					
					query.setParameter("magasin", magasin.getId());
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);		
					
					query.setParameter("famille", familleArticle.getId());
					query.setParameter("article", article.getId());
					
					
				}
				
				else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS )
				{
					
	              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAvoirPF d where (factureAvoirPf.dateFacture between :dateDebut and :dateFin) and factureAvoirPf.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille   GROUP BY  d.article.liblle , d.sousFamille.liblle");
					
					
					query.setParameter("magasin", magasin.getId());
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);		
					
					query.setParameter("famille", familleArticle.getId());
					query.setParameter("sousfamille", sousfamille.getId());
					
				}
				else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS )
				{
					
	              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAvoirPF d where (factureAvoirPf.dateFacture between :dateDebut and :dateFin) and factureAvoirPf.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille    GROUP BY  d.article.liblle , d.sousFamille.liblle");
					
					
					query.setParameter("magasin", magasin.getId());
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);		
					
					query.setParameter("famille", familleArticle.getId());
					
					
				}
				
				else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS)
				{
					
	            query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAvoirPF d where (factureAvoirPf.dateFacture between :dateDebut and :dateFin) and factureAvoirPf.magasin.id=:magasin   GROUP BY  d.article.liblle , d.sousFamille.liblle");
					
					
					query.setParameter("magasin", magasin.getId());
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);		
					
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
