package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFacturePF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public class DetailFacturePFDAOImpl implements DetailFacturePFDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailFacturePF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailFacturePF edit(DetailFacturePF e) {
		
	session.beginTransaction();
	DetailFacturePF p= (DetailFacturePF)session.merge(e);
	session.getTransaction().commit();
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailFacturePF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailFacturePF> findAll() {
		return session.createQuery("select c from DetailFacturePF c").list();
		}

	public DetailFacturePF findById(int id) {
		return (DetailFacturePF)session.get(DetailFacturePF.class, id);
		}
	
	
	
	@Override
	public List<DetailFacturePF> listeDetailFacturePFByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFacturePF c where facturePF.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	@Override
	public List<DetailFacturePF> listeDetailFacturePFByNumFacture(String Numfacture, Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFacturePF c where facturePF.id in (select id from FacturePF where numFacture=:Numfacture and magasin.id=:magasin)");
		query.setParameter("Numfacture", Numfacture);
		query.setParameter("magasin", magasin.getId());
		return query.list();
		
		
	}
	
	
	
	@Override
	public List<DetailFacturePF> listeDetailFacturePFByDate(Date dateDebut,Date dateFin , Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFacturePF c where facturePF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		return query.list();

	}
	
	@Override
	public List<DetailFacturePF> listeDetailFacturePFByRequete(String requete) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFacturePF c "+requete +" order by  (REPLACE(c.facturePF.numFacture,'/','')+0)");
		
		return query.list();

	}
	
	
	
	
	
	//liste des BL / factures entre deux date
	@Override
	public List<DetailFacturePF> findDetailBLEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle , String type) {
		
		// TODO Auto-generated method stub
		Query query=null;
	if(type.equals(Constantes.TYPE_BON_LIVRAISON))
	{
		if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.type =:BL  and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.type =:BL  and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.type =:BL and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.type =:BL and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.type =:BL and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.type =:BL and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and facturePF.type =:BL and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and facturePF.type =:BL and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and facturePF.type =:BL and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  facturePF.type =:BL and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and facturePF.type =:BL and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  facturePF.type =:BL and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("codeclient", client.getCode());	
		}
			
	}else if(type.equals(Constantes.TYPE_FACTURE))
	{

		if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin and facturePF.type <>:BL order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin and facturePF.type <>:BL order by article.id ,facturePF.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin and facturePF.type <>:BL order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin and facturePF.type <>:BL order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin and facturePF.type <>:BL order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin and facturePF.type <>:BL order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin and sousFamille.famileArticlePF.id=:famille and facturePF.type <>:BL order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and facturePF.magasin.id=:magasin and facturePF.type <>:BL order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and facturePF.magasin.id=:magasin and facturePF.type <>:BL order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and facturePF.magasin.id=:magasin and facturePF.type <>:BL order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and facturePF.magasin.id=:magasin and facturePF.type <>:BL order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and facturePF.magasin.id=:magasin and  facturePF.type <>:BL order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("codeclient", client.getCode());	
		}
			
		
	}else
	{
		


		if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin  order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("codeclient", client.getCode());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("codeclient", client.getCode());	
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin  order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin  order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and facturePF.magasin.id=:magasin and sousFamille.famileArticlePF.id=:famille  order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and facturePF.magasin.id=:magasin  order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and facturePF.magasin.id=:magasin order by article.id ,facturePF.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("codeclient", client.getCode());	
		}
			
		
	
		
		
		
		
	}
				
				return  query.list();
}
	
	
	//liste des BL/Factures Sans Gratuite pour calculer le prix Moyen
	@Override
	public List<DetailFacturePF> findDetailBLEntreDeuxDateSansGratuite(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle , String type) {
		
		// TODO Auto-generated method stub
		Query query=null;
	if(type.equals(Constantes.TYPE_BON_LIVRAISON))
	{
 if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  facturePF.type =:BL and facturePF.magasin.id=:magasin and prixUnitaire >0  order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  facturePF.type =:BL and facturePF.magasin.id=:magasin and prixUnitaire >0 order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("codeclient", client.getCode());	
		}
			
	}else if(type.equals(Constantes.TYPE_FACTURE))
	{

 if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and facturePF.magasin.id=:magasin and facturePF.type <>:BL and prixUnitaire >0 order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and facturePF.magasin.id=:magasin and  facturePF.type <>:BL and prixUnitaire >0 order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("codeclient", client.getCode());	
		}
			
		
	}else
	{
		


		 if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where  facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and facturePF.magasin.id=:magasin and prixUnitaire >0 order by article.id ,facturePF.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFacturePF c where facturePF.clientPF.code=:codeclient and facturePF.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and facturePF.magasin.id=:magasin and prixUnitaire >0 order by article.id ,facturePF.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("codeclient", client.getCode());	
		}
			
		
		
	}
				
				return  query.list();
}
	
	
	
	
	
	
	
	
	
	
	
// la Facture/BL par article
	
	@Override
	public List<DetailFacturePF> listeDetailFacturePFByArticle(Date dateDebut,Date dateFin , Articles article, SousFamilleArticlePF sousfamille , Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFacturePF c where facturePF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin and article.id=:article and sousFamille.id=:sousfamille");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		query.setParameter("sousfamille", sousfamille.getId());
		query.setParameter("article", article.getId());
		
		return query.list();

	}
	
	@Override
	public List<Object[]> listeEtatMontantFactureAvecTVA(Date dateDebut,Date dateFin , Magasin magasin , String type) {
		// TODO Auto-generated method stub
		Query query=null;
		if(type.equals(Constantes.TYPE_FACTURE))
		{
			// query= session.createQuery("select case when facturePF.modeReglement is null  then 'Reglement Espece' else facturePF.modeReglement end, sum(d.montantHT) , sum(d.montantTVA) , sum(d.montantTTC), sum( case when facturePF.modeReglement=:especes or facturePF.modeReglement is null then d.montantTTC else 0 end)*(0.25/100) from DetailFacturePF d where  facturePF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin and (facturePF.modeReglement=:especes or facturePF.modeReglement=:Cheque or facturePF.modeReglement=:Virement or facturePF.modeReglement is null ) group by facturePF.modeReglement");
			
			query= session.createQuery("select sum(case when espece>0 then espece else 0 end) ,sum(case when etat=:etat then montantTTC else 0 end) ,sum(case when cheque>0  then cheque else 0 end),sum(case when versement>0  then versement else 0 end),sum(case when virement>0  then virement else 0 end),sum(case when traite>0  then traite else 0 end),sum(case when credit>0  then credit else 0 end)   from FacturePF d where (dateFacture between :dateDebut and :dateFin) and magasin.id=:magasin and d.montantTVA > 0 and d.id in ( select d.facturePF.id from DetailFacturePF d )");
			
			//query= session.createQuery("select facturePF.modeReglement, sum(d.montantHT) , sum(d.montantTVA) , sum(d.montantTTC)  from DetailFacturePF d where (d.facturePF.dateFacture between :dateDebut and :dateFin) and d.facturePF.modeReglement is not null and d.facturePF.magasin.id=:magasin GROUP BY d.facturePF.modeReglement");
			
			
			// query.setParameter("Virement", "Virement");
			/* query.setParameter("Factures", "Facturé");
				query.setParameter("Facture", "Facture");
				query.setParameter("BL", "BL");*/
		
		}else
		{
			/* query= session.createQuery("select sum(d.montantHT) , sum(d.montantTVA) , sum(d.montantTTC), sum(d.montantTTC)*(0.25/100)  from DetailFacturePF d where  facturePF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin and (facturePF.modeReglement=:especes or facturePF.type =:type or facturePF.type =:Facture )");
			 query.setParameter("type", type);
			 query.setParameter("Facture", "Facture");*/
		}

		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		query.setParameter("etat", Constantes.ETAT_NON_REGLE);
		
		return query.list();

	}
	
	
	
	@Override
	public List<Object[]> listeEtatMontantFactureSansTVA(Date dateDebut,Date dateFin , Magasin magasin , String type) {
		// TODO Auto-generated method stub
		Query query=null;
		if(type.equals(Constantes.TYPE_FACTURE))
		{
			// query= session.createQuery("select case when facturePF.modeReglement is null  then 'Reglement Espece' else facturePF.modeReglement end, sum(d.montantHT) , sum(d.montantTVA) , sum(d.montantTTC), sum( case when facturePF.modeReglement=:especes or facturePF.modeReglement is null then d.montantTTC else 0 end)*(0.25/100) from DetailFacturePF d where  facturePF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin and (facturePF.modeReglement=:especes or facturePF.modeReglement=:Cheque or facturePF.modeReglement=:Virement or facturePF.modeReglement is null ) group by facturePF.modeReglement");
			
			query= session.createQuery("select sum(case when espece>0 then espece else 0 end) ,sum(case when etat=:etat then montantTTC else 0 end) ,sum(case when cheque>0  then cheque else 0 end),sum(case when versement>0  then versement else 0 end),sum(case when virement>0  then virement else 0 end),sum(case when traite>0  then traite else 0 end),sum(case when credit>0  then credit else 0 end)   from FacturePF d where (dateFacture between :dateDebut and :dateFin) and magasin.id=:magasin and montantTVA=0");
			
			//query= session.createQuery("select facturePF.modeReglement, sum(d.montantHT) , sum(d.montantTVA) , sum(d.montantTTC)  from DetailFacturePF d where (d.facturePF.dateFacture between :dateDebut and :dateFin) and d.facturePF.modeReglement is not null and d.facturePF.magasin.id=:magasin GROUP BY d.facturePF.modeReglement");
			
			
			// query.setParameter("Virement", "Virement");
			/* query.setParameter("Factures", "Facturé");
				query.setParameter("Facture", "Facture");
				query.setParameter("BL", "BL");*/
		
		}else
		{
			/* query= session.createQuery("select sum(d.montantHT) , sum(d.montantTVA) , sum(d.montantTTC), sum(d.montantTTC)*(0.25/100)  from DetailFacturePF d where  facturePF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin and (facturePF.modeReglement=:especes or facturePF.type =:type or facturePF.type =:Facture )");
			 query.setParameter("type", type);
			 query.setParameter("Facture", "Facture");*/
		}

		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		query.setParameter("etat", Constantes.ETAT_NON_REGLE);
		
		return query.list();

	}
	
	
	
	@Override
	public List<Object[]> listeFactureVenteClientAvecOuSansICE(Date dateDebut,Date dateFin , Magasin magasin , String ice) {
		// TODO Auto-generated method stub
		Query query=null;
		if(!ice.equals(""))
		{
			
			
			if(ice.equals(Constantes.CODE_OUI))
			{
				query= session.createQuery("select  d.facturePF.dateFacture,d.facturePF.numFacture, d.facturePF.clientPF.code,d.facturePF.clientPF.nom,d.facturePF.clientPF.ice,sum(d.montantHT),sum(d.montantTVA),sum(d.montantTTC)  from DetailFacturePF d where (d.facturePF.dateFacture between :dateDebut and :dateFin) and d.facturePF.magasin.id=:magasin and d.facturePF.clientPF.ice is not null and d.facturePF.clientPF.ice <>'' group by d.facturePF.numFacture order by d.facturePF.dateFacture ");

				
			}else if(ice.equals(Constantes.CODE_NON))
			{
				
				query= session.createQuery("select  d.facturePF.dateFacture,d.facturePF.numFacture, d.facturePF.clientPF.code,d.facturePF.clientPF.nom,d.facturePF.clientPF.ice,sum(d.montantHT),sum(d.montantTVA),sum(d.montantTTC)  from DetailFacturePF d where (d.facturePF.dateFacture between :dateDebut and :dateFin) and d.facturePF.magasin.id=:magasin and (d.facturePF.clientPF.ice is null or d.facturePF.clientPF.ice ='') group by d.facturePF.numFacture order by d.facturePF.dateFacture");

				
			}
			
		
		}else
		{
			
			
			query= session.createQuery("select  d.facturePF.dateFacture,d.facturePF.numFacture, d.facturePF.clientPF.code,d.facturePF.clientPF.nom,d.facturePF.clientPF.ice,sum(d.montantHT),sum(d.montantTVA),sum(d.montantTTC)  from DetailFacturePF d where (d.facturePF.dateFacture between :dateDebut and :dateFin) and d.facturePF.magasin.id=:magasin group by d.facturePF.numFacture order by d.facturePF.dateFacture");

		}

		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		
		
		return query.list();

	}
	

	
	
	@Override
	public List<Object[]> listeEtatVentePFParClientPF(Date dateDebut,Date dateFin , Magasin magasin , ClientPF clientPF,String etat) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select facturePF.dateFacture,facturePF.numFacture ,facturePF.clientPF.nom,sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.etat=:etat and facturePF.clientPF.id=:clientPF GROUP BY facturePF.numFacture ");
			
			
		query.setParameter("magasin", magasin.getId());
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("clientPF", clientPF.getId());
		query.setParameter("etat", etat);
		return query.list();

	}
	
	
	@Override
	public List<DetailFacturePF> listeEtatPrixMoyen(Date dateDebut,Date dateFin , Magasin magasin, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle, String type , SousFamilleArticlePF sousfamille , String tousSousfamille) {
		// TODO Auto-generated method stub
		Query query=null;
		if(type.equals(Constantes.TYPE_BON_LIVRAISON))
		{
			
			
			if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type =:BL ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type =:BL and d.sousFamille.famileArticlePF.id=:famille  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select  d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
				query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type =:BL and d.sousFamille.famileArticlePF.id=:famille   ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				
				
			}
			
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.article.id=:article  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}	
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
				query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.article.id=:article  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				
				
			}
			
			
			
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
              query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
              query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type =:BL and d.sousFamille.famileArticlePF.id=:famille   ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				
			}
			
			
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
            query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type =:BL  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				}
			
			
		
			
		}else if(type.equals(Constantes.TYPE_FACTURE))
		{
			

			
			
			if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type <>:BL  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type <>:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
				
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
				query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type <>:BL and d.sousFamille.famileArticlePF.id=:famille   ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
			
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type <>:BL and d.sousFamille.famileArticlePF.id=:famille  and d.sousFamille.id=:sousfamille and d.article.id=:article  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
				query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type <>:BL and d.sousFamille.famileArticlePF.id=:famille  and d.article.id=:article  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS  && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
              query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type <>:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS  && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
              query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type <>:BL and d.sousFamille.famileArticlePF.id=:famille   ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
            query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin and facturePF.type <>:BL  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				}
			
			
		
			
		
			
			
		}else
		{
			

			

			
			
			if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d  FROM DetailFacturePF d where (d.facturePF.dateFacture between :dateDebut and :dateFin) and d.facturePF.magasin.id=:magasin  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS  && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS  )
			{
				
				query= session.createQuery("select d  FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS )
			{
				
				query= session.createQuery("select d  FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.article.id=:article  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS )
			{
				
				query= session.createQuery("select d  FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille  and d.article.id=:article  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS )
			{
				
              query= session.createQuery("select d  FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille  ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS )
			{
				
              query= session.createQuery("select d  FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille   ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
            query= session.createQuery("select d  FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin   ");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				}
			
			
			
		}
			
		
		return query.list();

	}
	
	public List<DetailFacturePF> listeFactureSansGratuiteParArticle(Articles article , Magasin magasin , String  numfacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFacturePF c where  c.article.id=:article and c.prixUnitaire > 0 and c.facturePF.numFacture =:numfacture and facturePF.magasin.id=:magasin");
		query.setParameter("article", article.getId());
		query.setParameter("magasin", magasin.getId());
		query.setParameter("numfacture", numfacture);
		return query.list();
		
		
	}
	
	
	
	@Override
	public List<Object[]> listeEtatVentePFParFamilleParMois(Date dateDebut,Date dateFin , Magasin magasin ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select d.sousFamille.famileArticlePF.liblle ,sum( case when Month(facturePF.dateFacture)=1  then d.montantHT else 0 end),sum( case when Month(facturePF.dateFacture)=2  then d.montantHT else 0 end),sum( case when Month(facturePF.dateFacture)=3  then d.montantHT else 0 end),sum( case when Month(facturePF.dateFacture)=4  then d.montantHT else 0 end),sum( case when Month(facturePF.dateFacture)=5  then d.montantHT else 0 end) ,sum( case when Month(facturePF.dateFacture)=6  then d.montantHT else 0 end),sum( case when Month(facturePF.dateFacture)=7  then d.montantHT else 0 end),sum( case when Month(facturePF.dateFacture)=8  then d.montantHT else 0 end),sum( case when Month(facturePF.dateFacture)=9  then d.montantHT else 0 end),sum( case when Month(facturePF.dateFacture)=10  then d.montantHT else 0 end),sum( case when Month(facturePF.dateFacture)=11  then d.montantHT else 0 end),sum( case when Month(facturePF.dateFacture)=12  then d.montantHT else 0 end) FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin  GROUP BY d.sousFamille.famileArticlePF.liblle  ");
			
			
		query.setParameter("magasin", magasin.getId());
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		
		return query.list();

	}
	
	
	
 
	public List<Object[]> listeEtatVentePFParFamilleParFacture(Date dateDebut,Date dateFin , Magasin magasin ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  d.facturePF.dateFacture,d.facturePF.numFacture,d.sousFamille.famileArticlePF.liblle,sum(d.montantHT),sum(d.montantTVA),sum(d.montantTTC) FROM DetailFacturePF d where (facturePF.dateFacture between :dateDebut and :dateFin) and facturePF.magasin.id=:magasin  GROUP BY d.facturePF.numFacture, d.sousFamille.famileArticlePF.liblle  order by  (REPLACE(d.facturePF.numFacture,'/','')+0)");
			
			
		query.setParameter("magasin", magasin.getId());
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listeEtatChiffreAffaireClientAvecICE(String req ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select c.facturePF.clientPF.nom, c.facturePF.clientPF.ice,sum(c.montantHT),sum(c.montantTTC) from DetailFacturePF c  "+req +" and c.facturePF.clientPF.ice is not null and c.facturePF.clientPF.ice!=' ' group by c.facturePF.clientPF.nom, c.facturePF.clientPF.ice");
			
			
		 
		
		return query.list();

	}
	
	@Override
	public List<Object[]> listeEtatChiffreAffaireClientSansICE(String req ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  sum(c.montantHT),sum(c.montantTTC) from DetailFacturePF c  "+req +" and (c.facturePF.clientPF.ice is null or c.facturePF.clientPF.ice=' ') ");
			
			
		 
		
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
