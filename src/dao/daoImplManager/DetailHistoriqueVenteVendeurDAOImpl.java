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
import dao.daoManager.DetailHistoriqueVenteVendeurDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFacturePF;
import dao.entity.DetailHistoriqueVenteVendeur;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public class DetailHistoriqueVenteVendeurDAOImpl implements DetailHistoriqueVenteVendeurDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailHistoriqueVenteVendeur e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailHistoriqueVenteVendeur edit(DetailHistoriqueVenteVendeur e) {
		
	session.beginTransaction();
	DetailHistoriqueVenteVendeur p= (DetailHistoriqueVenteVendeur)session.merge(e);
	session.getTransaction().commit();
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailHistoriqueVenteVendeur p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailHistoriqueVenteVendeur> findAll() {
		return session.createQuery("select c from DetailHistoriqueVenteVendeur c").list();
		}

	public DetailHistoriqueVenteVendeur findById(int id) {
		return (DetailHistoriqueVenteVendeur)session.get(DetailHistoriqueVenteVendeur.class, id);
		}
	
	
	
	@Override
	public List<DetailHistoriqueVenteVendeur> listeDetailFacturePFByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	@Override
	public List<DetailHistoriqueVenteVendeur> listeDetailFacturePFByNumFacture(String Numfacture, Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.id in (select id from historiqueventevendeur where numFacture=:Numfacture and magasin.id=:magasin)");
		query.setParameter("Numfacture", Numfacture);
		query.setParameter("magasin", magasin.getId());
		return query.list();
		
		
	}
	
	
	
	@Override
	public List<DetailHistoriqueVenteVendeur> listeDetailFacturePFByDate(Date dateDebut,Date dateFin , Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.dateFacture between :dateDebut and :dateFin and historiqueventevendeur.magasin.id=:magasin");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		return query.list();

	}
	
	@Override
	public List<DetailHistoriqueVenteVendeur> listeDetailFacturePFByRequete(String requete) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailHistoriqueVenteVendeur c "+requete +" order by (REPLACE(historiqueventevendeur.numFacture,'/','')+0) ");
		
		return query.list();

	}
	
	
	
	
	
	//liste des BL / factures entre deux date
	@Override
	public List<DetailHistoriqueVenteVendeur> findDetailBLEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle , String type) {
		
		// TODO Auto-generated method stub
		Query query=null;
	if(type.equals(Constantes.TYPE_BON_LIVRAISON))
	{
		if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.type =:BL  and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.type =:BL  and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.type =:BL and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.type =:BL and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.type =:BL and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.type =:BL and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and historiqueventevendeur.type =:BL and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and historiqueventevendeur.type =:BL and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and historiqueventevendeur.type =:BL and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  historiqueventevendeur.type =:BL and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and historiqueventevendeur.type =:BL and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  historiqueventevendeur.type =:BL and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
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
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL order by article.id ,historiqueventevendeur.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin and sousFamille.famileArticlePF.id=:famille and historiqueventevendeur.type <>:BL order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and historiqueventevendeur.magasin.id=:magasin and  historiqueventevendeur.type <>:BL order by article.id ,historiqueventevendeur.dateFacture");
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
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin  order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("codeclient", client.getCode());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("codeclient", client.getCode());	
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin  order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin  order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and historiqueventevendeur.magasin.id=:magasin and sousFamille.famileArticlePF.id=:famille  order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and historiqueventevendeur.magasin.id=:magasin  order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and historiqueventevendeur.magasin.id=:magasin order by article.id ,historiqueventevendeur.dateFacture ");
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
	public List<DetailHistoriqueVenteVendeur> findDetailBLEntreDeuxDateSansGratuite(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle , String type) {
		
		// TODO Auto-generated method stub
		Query query=null;
	if(type.equals(Constantes.TYPE_BON_LIVRAISON))
	{
 if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  historiqueventevendeur.type =:BL and historiqueventevendeur.magasin.id=:magasin and prixUnitaire >0  order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  historiqueventevendeur.type =:BL and historiqueventevendeur.magasin.id=:magasin and prixUnitaire >0 order by article.id ,historiqueventevendeur.dateFacture");
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
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL and prixUnitaire >0 order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and historiqueventevendeur.magasin.id=:magasin and  historiqueventevendeur.type <>:BL and prixUnitaire >0 order by article.id ,historiqueventevendeur.dateFacture");
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
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and historiqueventevendeur.magasin.id=:magasin and prixUnitaire >0 order by article.id ,historiqueventevendeur.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.clientPF.code=:codeclient and historiqueventevendeur.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and historiqueventevendeur.magasin.id=:magasin and prixUnitaire >0 order by article.id ,historiqueventevendeur.dateFacture ");
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
	public List<DetailHistoriqueVenteVendeur> listeDetailFacturePFByArticle(Date dateDebut,Date dateFin , Articles article, SousFamilleArticlePF sousfamille , Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where historiqueventevendeur.dateFacture between :dateDebut and :dateFin and historiqueventevendeur.magasin.id=:magasin and article.id=:article and sousFamille.id=:sousfamille");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		query.setParameter("sousfamille", sousfamille.getId());
		query.setParameter("article", article.getId());
		
		return query.list();

	}
	
	@Override
	public List<Object[]> listeEtatMontantFacture(Date dateDebut,Date dateFin , Magasin magasin , String type) {
		// TODO Auto-generated method stub
		Query query=null;
		if(type.equals(Constantes.TYPE_FACTURE))
		{
			// query= session.createQuery("select case when facturePF.modeReglement is null  then 'Reglement Espece' else facturePF.modeReglement end, sum(d.montantHT) , sum(d.montantTVA) , sum(d.montantTTC), sum( case when facturePF.modeReglement=:especes or facturePF.modeReglement is null then d.montantTTC else 0 end)*(0.25/100) from DetailFacturePF d where  facturePF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin and (facturePF.modeReglement=:especes or facturePF.modeReglement=:Cheque or facturePF.modeReglement=:Virement or facturePF.modeReglement is null ) group by facturePF.modeReglement");
			
			query= session.createQuery("select sum(case when espece>0 then espece else 0 end) ,sum(case when etat=:etat then montantTTC else 0 end) ,sum(case when cheque>0  then cheque else 0 end),sum(case when versement>0  then versement else 0 end),sum(case when virement>0  then virement else 0 end),sum(case when traite>0  then traite else 0 end)   from historiqueventevendeur d where (dateFacture between :dateDebut and :dateFin) and magasin.id=:magasin");
			
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
	public List<Object[]> listeEtatVentePFParFamille(Date dateDebut,Date dateFin , Magasin magasin , String etat) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select d.sousFamille.famileArticlePF.liblle, sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.etat=:etat GROUP BY d.sousFamille.famileArticlePF.liblle ");
			
			
		query.setParameter("magasin", magasin.getId());
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("etat", etat);
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listeEtatVentePFParClientPF(Date dateDebut,Date dateFin , Magasin magasin , ClientPF clientPF,String etat) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select historiqueventevendeur.dateFacture,historiqueventevendeur.numFacture ,historiqueventevendeur.clientPF.nom,sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.etat=:etat and historiqueventevendeur.clientPF.id=:clientPF GROUP BY historiqueventevendeur.numFacture ");
			
			
		query.setParameter("magasin", magasin.getId());
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("clientPF", clientPF.getId());
		query.setParameter("etat", etat);
		return query.list();

	}
	
	
	@Override
	public List<Object[]> listeEtatPrixMoyen(Date dateDebut,Date dateFin , Magasin magasin, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle, String type , SousFamilleArticlePF sousfamille , String tousSousfamille) {
		// TODO Auto-generated method stub
		Query query=null;
		if(type.equals(Constantes.TYPE_BON_LIVRAISON))
		{
			
			
			if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle, sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type =:BL and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type =:BL and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				
				
			}
			
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
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
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				
				
			}
			
			
			
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type =:BL and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				
			}
			
			
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
            query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type =:BL and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				}
			
			
		
			
		}else if(type.equals(Constantes.TYPE_FACTURE))
		{
			

			
			
			if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
				
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
			
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL and d.sousFamille.famileArticlePF.id=:famille  and d.sousFamille.id=:sousfamille and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
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
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL and d.sousFamille.famileArticlePF.id=:famille  and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS  && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS  && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
            query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin and historiqueventevendeur.type <>:BL and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				}
			
			
		
			
		
			
			
		}else
		{
			

			

			
			
			if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin  and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS  && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS  )
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS )
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS )
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille  and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS )
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS )
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
            query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailHistoriqueVenteVendeur d where (historiqueventevendeur.dateFacture between :dateDebut and :dateFin) and historiqueventevendeur.magasin.id=:magasin  and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				}
			
			
			
		}
			
		
		return query.list();

	}
	
	public List<DetailHistoriqueVenteVendeur> listeFactureSansGratuiteParArticle(Articles article , Magasin magasin , String  numfacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailHistoriqueVenteVendeur c where  c.article.id=:article and c.prixUnitaire > 0 and c.historiqueventevendeur.numFacture =:numfacture and historiqueventevendeur.magasin.id=:magasin");
		query.setParameter("article", article.getId());
		query.setParameter("magasin", magasin.getId());
		query.setParameter("numfacture", numfacture);
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
