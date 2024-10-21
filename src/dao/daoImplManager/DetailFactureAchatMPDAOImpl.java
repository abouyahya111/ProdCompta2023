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
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAchatMP;
import dao.entity.DetailFacturePF;
import dao.entity.DetailTransferStockMP;
import dao.entity.FactureAchatMP;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SubCategorieMp;

public class DetailFactureAchatMPDAOImpl implements DetailFactureAchatMPDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailFactureAchatMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailFactureAchatMP edit(DetailFactureAchatMP e) {
		
	session.beginTransaction();
	DetailFactureAchatMP p= (DetailFactureAchatMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailFactureAchatMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}
	
public void delete(DetailFactureAchatMP p) {
		
		session.beginTransaction();	
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailFactureAchatMP> findAll() {
		return session.createQuery("select c from DetailFactureAchatMP c").list();
		}

	public DetailFactureAchatMP findById(int id) {
		return (DetailFactureAchatMP)session.get(DetailFactureAchatMP.class, id);
		}
	
	
	
	@Override
	public List<DetailFactureAchatMP> listeDetailFactureAchatByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAchatMP c where factureMP.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	@Override
	public List<DetailFactureAchatMP> listeDetailFactureAchatByDate(Date dateDebut,Date dateFin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAchatMP c where factureMP.dateFacture between :dateDebut and :dateFin ");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		
		return query.list();

	}
	
	public List<Object[]> listeEtatPrixMoyenMP(Date dateDebut,Date dateFin , Magasin magasin, SubCategorieMp souscategoriemp, MatierePremier mp, String tousSousCategorie, String touarticle , CategorieMp categorie , String touscategorie) {
		// TODO Auto-generated method stub
		Query query=null;
		

		
		if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp==null && mp==null && tousSousCategorie!=Constantes.TOUS && touarticle!=Constantes.TOUS)
		{
			
			query= session.createQuery("select d.matierePremiere.code, d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.prixUnitaire>0 GROUP BY  d.matierePremiere.nom , d.matierePremiere.categorieMp.subCategorieMp.nom");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			
			
		}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp!=null && mp==null && tousSousCategorie!=Constantes.TOUS && touarticle!=Constantes.TOUS  && categorie!=null && touscategorie!=Constantes.TOUS)
		{
			
			query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC)  FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.matierePremiere.categorieMp.subCategorieMp.id=:souscategoriemp and d.matierePremiere.categorieMp.id=:categorie and d.prixUnitaire>0  GROUP BY  d.matierePremiere.nom , d.matierePremiere.categorieMp.subCategorieMp.nom");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			query.setParameter("souscategoriemp", souscategoriemp.getId());
			query.setParameter("categorie", categorie.getId());
				
		}
		else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp!=null && mp==null && tousSousCategorie!=Constantes.TOUS && touarticle!=Constantes.TOUS  )
		{
			
			query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC)  FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.matierePremiere.categorieMp.subCategorieMp.id=:souscategoriemp  and d.prixUnitaire>0  GROUP BY  d.matierePremiere.nom ,  d.matierePremiere.categorieMp.subCategorieMp.nom");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			query.setParameter("souscategoriemp", souscategoriemp.getId());
			
		}
		
		else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp!=null && mp!=null && tousSousCategorie!=Constantes.TOUS && touarticle!=Constantes.TOUS && categorie!=null && touscategorie!=Constantes.TOUS )
		{
			
			query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC)  FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.matierePremiere.categorieMp.subCategorieMp.id=:souscategoriemp and d.matierePremiere.categorieMp.id=:categorie and d.matierePremiere.id=:mp and d.prixUnitaire>0  GROUP BY  d.matierePremiere.nom , d.matierePremiere.categorieMp.subCategorieMp.nom");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			query.setParameter("souscategoriemp", souscategoriemp.getId());
			query.setParameter("categorie", categorie.getId());
			query.setParameter("mp", mp.getId());
			
			
		}
		else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp!=null && mp!=null && tousSousCategorie!=Constantes.TOUS && touarticle!=Constantes.TOUS && categorie==null && touscategorie==Constantes.TOUS )
		{
			
			query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.matierePremiere.categorieMp.subCategorieMp.id=:souscategoriemp  and d.matierePremiere.id=:mp and d.prixUnitaire>0  GROUP BY  d.matierePremiere.nom , d.matierePremiere.categorieMp.subCategorieMp.nom");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			query.setParameter("souscategoriemp", souscategoriemp.getId());
			query.setParameter("mp", mp.getId());	
			
		}
		
		else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp!=null && mp==null && tousSousCategorie!=Constantes.TOUS && touarticle==Constantes.TOUS && categorie!=null && touscategorie!=Constantes.TOUS )
		{
			
          query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.matierePremiere.categorieMp.subCategorieMp.id=:souscategoriemp and d.matierePremiere.categorieMp.id=:categorie and d.prixUnitaire>0  GROUP BY  d.matierePremiere.nom , d.matierePremiere.categorieMp.subCategorieMp.nom");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			query.setParameter("souscategoriemp", souscategoriemp.getId());
			query.setParameter("categorie", categorie.getId());
			
		}
		else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp!=null && mp==null && tousSousCategorie!=Constantes.TOUS && touarticle==Constantes.TOUS && categorie==null && touscategorie==Constantes.TOUS )
		{
			
          query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.matierePremiere.categorieMp.subCategorieMp.id=:souscategoriemp  and d.prixUnitaire>0  GROUP BY   d.matierePremiere.nom , d.matierePremiere.categorieMp.subCategorieMp.nom");
						
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			query.setParameter("souscategoriemp", souscategoriemp.getId());
				
			
		}
		
		else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp==null && mp==null && tousSousCategorie==Constantes.TOUS && touarticle!=Constantes.TOUS)
		{
			
        query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.prixUnitaire>0 GROUP BY  d.matierePremiere.nom , d.matierePremiere.categorieMp.subCategorieMp.nom");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
		
		}
		
		
		
		return query.list();	
		
		
		
	}
	
	
	
	public List<Object[]> listeEtatPrixMoyenMPParMP(Date dateDebut,Date dateFin , Magasin magasin, SubCategorieMp souscategoriemp, MatierePremier mp, String tousSousCategorie, String touarticle , CategorieMp categorie , String touscategorie) {
		// TODO Auto-generated method stub
		Query query=null;
		

		
		if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp==null && mp==null && tousSousCategorie!=Constantes.TOUS && touarticle!=Constantes.TOUS)
		{
			
			query= session.createQuery("select d.matierePremiere.code, d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.prixUnitaire>0 GROUP BY  d.matierePremiere.nom  ");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			
			
		}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp!=null && mp==null && tousSousCategorie!=Constantes.TOUS && touarticle!=Constantes.TOUS  && categorie!=null && touscategorie!=Constantes.TOUS)
		{
			
			query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC)  FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.matierePremiere.categorieMp.subCategorieMp.id=:souscategoriemp and d.matierePremiere.categorieMp.id=:categorie and d.prixUnitaire>0  GROUP BY  d.matierePremiere.nom  ");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			query.setParameter("souscategoriemp", souscategoriemp.getId());
			query.setParameter("categorie", categorie.getId());
				
		}
		else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp!=null && mp==null && tousSousCategorie!=Constantes.TOUS && touarticle!=Constantes.TOUS  )
		{
			
			query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC)  FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.matierePremiere.categorieMp.subCategorieMp.id=:souscategoriemp  and d.prixUnitaire>0  GROUP BY  d.matierePremiere.nom  ");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			query.setParameter("souscategoriemp", souscategoriemp.getId());
			
		}
		
		else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp!=null && mp!=null && tousSousCategorie!=Constantes.TOUS && touarticle!=Constantes.TOUS && categorie!=null && touscategorie!=Constantes.TOUS )
		{
			
			query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC)  FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.matierePremiere.categorieMp.subCategorieMp.id=:souscategoriemp and d.matierePremiere.categorieMp.id=:categorie and d.matierePremiere.id=:mp and d.prixUnitaire>0  GROUP BY  d.matierePremiere.nom  ");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			query.setParameter("souscategoriemp", souscategoriemp.getId());
			query.setParameter("categorie", categorie.getId());
			query.setParameter("mp", mp.getId());
			
			
		}
		else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp!=null && mp!=null && tousSousCategorie!=Constantes.TOUS && touarticle!=Constantes.TOUS && categorie==null && touscategorie==Constantes.TOUS )
		{
			
			query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.matierePremiere.categorieMp.subCategorieMp.id=:souscategoriemp  and d.matierePremiere.id=:mp and d.prixUnitaire>0  GROUP BY  d.matierePremiere.nom  ");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			query.setParameter("souscategoriemp", souscategoriemp.getId());
			query.setParameter("mp", mp.getId());	
			
		}
		
		else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp!=null && mp==null && tousSousCategorie!=Constantes.TOUS && touarticle==Constantes.TOUS && categorie!=null && touscategorie!=Constantes.TOUS )
		{
			
          query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.matierePremiere.categorieMp.subCategorieMp.id=:souscategoriemp and d.matierePremiere.categorieMp.id=:categorie and d.prixUnitaire>0  GROUP BY  d.matierePremiere.nom  ");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			query.setParameter("souscategoriemp", souscategoriemp.getId());
			query.setParameter("categorie", categorie.getId());
			
		}
		else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp!=null && mp==null && tousSousCategorie!=Constantes.TOUS && touarticle==Constantes.TOUS && categorie==null && touscategorie==Constantes.TOUS )
		{
			
          query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.matierePremiere.categorieMp.subCategorieMp.id=:souscategoriemp  and d.prixUnitaire>0  GROUP BY   d.matierePremiere.nom  ");
						
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
			
			query.setParameter("souscategoriemp", souscategoriemp.getId());
				
			
		}
		
		else if(dateDebut!=null && dateFin!=null && magasin !=null &&  souscategoriemp==null && mp==null && tousSousCategorie==Constantes.TOUS && touarticle!=Constantes.TOUS)
		{
			
        query= session.createQuery("select  d.matierePremiere.code,d.matierePremiere.nom,d.matierePremiere.categorieMp.subCategorieMp.nom ,d.matierePremiere.categorieMp.nom, sum(d.quantite) , (sum(d.montantHT) / sum(d.quantite)) , sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureAchatMP d where (factureMP.dateFacture between :dateDebut and :dateFin) and factureMP.magasin.id=:magasin  and d.prixUnitaire>0 GROUP BY  d.matierePremiere.nom  ");
			
			
			query.setParameter("magasin", magasin.getId());
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);		
		
		}
		
		
		
		return query.list();	
		
		
		
	}
	
	
	@Override
	public List<DetailFactureAchatMP> listeDetailFactureAchatByRequete(String requete) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAchatMP c "+requete+" order by c.factureMP.dateFacture");
		
		
		return query.list();
		
		
	}
	
	@Override
	public void DelateDetailfactureAchatMPByFactureAchatMP(FactureAchatMP factureAchatMP) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("delete from DetailFactureAchatMP where  factureMP.id=:factureAchatMP ");
		
		query.setParameter("factureAchatMP", factureAchatMP.getId());	
		 
		int i=query.executeUpdate(); 
		
		
	}
	


}
