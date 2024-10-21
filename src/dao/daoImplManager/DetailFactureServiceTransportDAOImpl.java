package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailFactureServiceTransportDAO;
import dao.daoManager.FactureServiceTransportDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFactureServiceTransport;
import dao.entity.DetailFactureServiceTransport;
import dao.entity.FactureServiceTransport;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public class DetailFactureServiceTransportDAOImpl implements DetailFactureServiceTransportDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailFactureServiceTransport e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailFactureServiceTransport edit(DetailFactureServiceTransport e) {
		
	session.beginTransaction();
	DetailFactureServiceTransport p= (DetailFactureServiceTransport)session.merge(e);
	session.getTransaction().commit();
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailFactureServiceTransport p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailFactureServiceTransport> findAll() {
		return session.createQuery("select c from DetailFactureServiceTransport c").list();
		}

	public DetailFactureServiceTransport findById(int id) {
		return (DetailFactureServiceTransport)session.get(DetailFactureServiceTransport.class, id);
		}
	
	
	
	@Override
	public List<DetailFactureServiceTransport> listeDetailFactureServiceTransportByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	@Override
	public List<DetailFactureServiceTransport> listeDetailFactureServiceTransportByNumFacture(String Numfacture, Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.id in (select id from FactureServiceTransport where numFacture=:Numfacture and magasin.id=:magasin)");
		query.setParameter("Numfacture", Numfacture);
		query.setParameter("magasin", magasin.getId());
		return query.list();
		
		
	}
	
	
	
	@Override
	public List<DetailFactureServiceTransport> listeDetailFactureServiceTransportByDate(Date dateDebut,Date dateFin , Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureServiceTransport c where FactureServiceTransport.dateFacture between :dateDebut and :dateFin and factureServiceTransport.magasin.id=:magasin");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		return query.list();

	}
	
	@Override
	public List<DetailFactureServiceTransport> listeDetailFactureServiceTransportByRequete(String requete) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureServiceTransport c "+requete +" order by c.factureServiceTransport.numFacture");
		
		return query.list();

	}
	
	
	
	
	
	//liste des BL / factures entre deux date
	@Override
	public List<DetailFactureServiceTransport> findDetailBLEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle , String type) {
		
		// TODO Auto-generated method stub
		Query query=null;
	if(type.equals(Constantes.TYPE_BON_LIVRAISON))
	{
		if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where FactureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.type =:BL  and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.type =:BL  and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.type =:BL and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.type =:BL and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.type =:BL and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.type =:BL and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureServiceTransport.type =:BL and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureServiceTransport.type =:BL and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureServiceTransport.type =:BL and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  factureServiceTransport.type =:BL and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureServiceTransport.type =:BL and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  factureServiceTransport.type =:BL and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
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
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL order by article.id ,factureServiceTransport.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin and sousFamille.famileArticlePF.id=:famille and factureServiceTransport.type <>:BL order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and factureServiceTransport.magasin.id=:magasin and  factureServiceTransport.type <>:BL order by article.id ,factureServiceTransport.dateFacture");
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
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin  order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("codeclient", client.getCode());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("codeclient", client.getCode());	
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin  order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin  order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and factureServiceTransport.magasin.id=:magasin and sousFamille.famileArticlePF.id=:famille  order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				
			
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				
				query.setParameter("famille", familleArticle.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureServiceTransport.magasin.id=:magasin  order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
				
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and factureServiceTransport.magasin.id=:magasin order by article.id ,factureServiceTransport.dateFacture ");
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
	public List<DetailFactureServiceTransport> findDetailBLEntreDeuxDateSansGratuite(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle , String type) {
		
		// TODO Auto-generated method stub
		Query query=null;
	if(type.equals(Constantes.TYPE_BON_LIVRAISON))
	{
 if(dateDebut!=null && datedeFin!=null && magasin !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  factureServiceTransport.type =:BL and factureServiceTransport.magasin.id=:magasin and prixUnitaire >0  order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  factureServiceTransport.type =:BL and factureServiceTransport.magasin.id=:magasin and prixUnitaire >0 order by article.id ,factureServiceTransport.dateFacture");
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
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL and prixUnitaire >0 order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and factureServiceTransport.magasin.id=:magasin and  factureServiceTransport.type <>:BL and prixUnitaire >0 order by article.id ,factureServiceTransport.dateFacture");
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
			 query= session.createQuery("select c from DetailFactureServiceTransport c where  factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and factureServiceTransport.magasin.id=:magasin and prixUnitaire >0 order by article.id ,factureServiceTransport.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("magasin", magasin.getId());
			
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && magasin !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.clientPF.code=:codeclient and factureServiceTransport.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and factureServiceTransport.magasin.id=:magasin and prixUnitaire >0 order by article.id ,factureServiceTransport.dateFacture ");
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
	public List<DetailFactureServiceTransport> listeDetailFactureServiceTransportByArticle(Date dateDebut,Date dateFin , Articles article, SousFamilleArticlePF sousfamille , Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureServiceTransport c where factureServiceTransport.dateFacture between :dateDebut and :dateFin and factureServiceTransport.magasin.id=:magasin and article.id=:article and sousFamille.id=:sousfamille");
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
			// query= session.createQuery("select case when FactureServiceTransport.modeReglement is null  then 'Reglement Espece' else FactureServiceTransport.modeReglement end, sum(d.montantHT) , sum(d.montantTVA) , sum(d.montantTTC), sum( case when FactureServiceTransport.modeReglement=:especes or FactureServiceTransport.modeReglement is null then d.montantTTC else 0 end)*(0.25/100) from DetailFactureServiceTransport d where  FactureServiceTransport.dateFacture between :dateDebut and :dateFin and FactureServiceTransport.magasin.id=:magasin and (FactureServiceTransport.modeReglement=:especes or FactureServiceTransport.modeReglement=:Cheque or FactureServiceTransport.modeReglement=:Virement or FactureServiceTransport.modeReglement is null ) group by FactureServiceTransport.modeReglement");
			
			query= session.createQuery("select sum(case when espece>0 then espece else 0 end) ,sum(case when etat=:etat then montantTTC else 0 end) ,sum(case when cheque>0  then cheque else 0 end),sum(case when versement>0  then versement else 0 end),sum(case when virement>0  then virement else 0 end),sum(case when traite>0  then traite else 0 end),sum(case when credit>0  then credit else 0 end)   from FactureServiceTransport d where (dateFacture between :dateDebut and :dateFin) and magasin.id=:magasin");
			
			//query= session.createQuery("select FactureServiceTransport.modeReglement, sum(d.montantHT) , sum(d.montantTVA) , sum(d.montantTTC)  from DetailFactureServiceTransport d where (d.FactureServiceTransport.dateFacture between :dateDebut and :dateFin) and d.FactureServiceTransport.modeReglement is not null and d.FactureServiceTransport.magasin.id=:magasin GROUP BY d.FactureServiceTransport.modeReglement");
			
			
			// query.setParameter("Virement", "Virement");
			/* query.setParameter("Factures", "Facturé");
				query.setParameter("Facture", "Facture");
				query.setParameter("BL", "BL");*/
		
		}else
		{
			/* query= session.createQuery("select sum(d.montantHT) , sum(d.montantTVA) , sum(d.montantTTC), sum(d.montantTTC)*(0.25/100)  from DetailFactureServiceTransport d where  FactureServiceTransport.dateFacture between :dateDebut and :dateFin and FactureServiceTransport.magasin.id=:magasin and (FactureServiceTransport.modeReglement=:especes or FactureServiceTransport.type =:type or FactureServiceTransport.type =:Facture )");
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
	public List<Object[]> listeEtatVentePFParClientPF(Date dateDebut,Date dateFin , Magasin magasin , ClientPF clientPF,String etat) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select factureServiceTransport.dateFacture,factureServiceTransport.numFacture ,factureServiceTransport.clientPF.nom,sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.etat=:etat and factureServiceTransport.clientPF.id=:clientPF GROUP BY factureServiceTransport.numFacture ");
			
			
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
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle, sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type =:BL and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (FactureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type =:BL and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				
				
			}
			
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
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
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				
				
			}
			
			
			
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type =:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type =:BL and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				
			}
			
			
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
            query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type =:BL and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				}
			
			
		
			
		}else if(type.equals(Constantes.TYPE_FACTURE))
		{
			

			
			
			if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
				
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
			
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL and d.sousFamille.famileArticlePF.id=:famille  and d.sousFamille.id=:sousfamille and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
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
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL and d.sousFamille.famileArticlePF.id=:famille  and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS  && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS  && sousfamille==null && tousSousfamille==Constantes.TOUS)
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				query.setParameter("famille", familleArticle.getId());
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
            query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and factureServiceTransport.type <>:BL and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				query.setParameter("BL", "BL");	
				}
			
			
		
			
		
			
			
		}else
		{
			

			

			
			
			if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin  and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				
				
			}else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS  && sousfamille!=null && tousSousfamille!=Constantes.TOUS)
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS  )
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS )
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS )
			{
				
				query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille  and d.article.id=:article and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille!=null && tousSousfamille!=Constantes.TOUS )
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille and d.sousFamille.id=:sousfamille and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
			}
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS && sousfamille==null && tousSousfamille==Constantes.TOUS )
			{
				
              query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin  and d.sousFamille.famileArticlePF.id=:famille  and d.prixUnitaire>0  GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				query.setParameter("famille", familleArticle.getId());
				
				
			}
			
			else if(dateDebut!=null && dateFin!=null && magasin !=null &&  familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS)
			{
				
            query= session.createQuery("select d.article.liblle,d.sousFamille.famileArticlePF.liblle ,d.sousFamille.liblle,sum(d.quantite),sum(d.montantHT)/sum(d.quantite), sum(d.montantHT), sum(d.montantTVA), sum(d.montantTTC) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin  and d.prixUnitaire>0 GROUP BY  d.article.liblle , d.sousFamille.famileArticlePF.liblle");
				
				
				query.setParameter("magasin", magasin.getId());
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				
				}
			
			
			
		}
			
		
		return query.list();

	}
	
	public List<DetailFactureServiceTransport> listeFactureSansGratuiteParArticle(Articles article , Magasin magasin , String  numfacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureServiceTransport c where  c.article.id=:article and c.prixUnitaire > 0 and c.factureServiceTransport.numFacture =:numfacture and factureServiceTransport.magasin.id=:magasin");
		query.setParameter("article", article.getId());
		query.setParameter("magasin", magasin.getId());
		query.setParameter("numfacture", numfacture);
		return query.list();
		
		
	}
	
	
	
	@Override
	public List<Object[]> listeEtatVentePFParFamilleParMois(Date dateDebut,Date dateFin , Magasin magasin ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select d.sousFamille.famileArticlePF.liblle ,sum( case when Month(factureServiceTransport.dateFacture)=1  then d.montantHT else 0 end),sum( case when Month(factureServiceTransport.dateFacture)=2  then d.montantHT else 0 end),sum( case when Month(factureServiceTransport.dateFacture)=3  then d.montantHT else 0 end),sum( case when Month(factureServiceTransport.dateFacture)=4  then d.montantHT else 0 end),sum( case when Month(factureServiceTransport.dateFacture)=5  then d.montantHT else 0 end) ,sum( case when Month(factureServiceTransport.dateFacture)=6  then d.montantHT else 0 end),sum( case when Month(factureServiceTransport.dateFacture)=7  then d.montantHT else 0 end),sum( case when Month(factureServiceTransport.dateFacture)=8  then d.montantHT else 0 end),sum( case when Month(factureServiceTransport.dateFacture)=9  then d.montantHT else 0 end),sum( case when Month(factureServiceTransport.dateFacture)=10  then d.montantHT else 0 end),sum( case when Month(factureServiceTransport.dateFacture)=11  then d.montantHT else 0 end),sum( case when Month(factureServiceTransport.dateFacture)=12  then d.montantHT else 0 end) FROM DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin  GROUP BY d.sousFamille.famileArticlePF.liblle  ");
			
			
		query.setParameter("magasin", magasin.getId());
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		
		return query.list();

	}
	
	
	
	
	
	
	public void ViderSession()
	{
		
		if (session!=null)
		{
			session.clear();
			
		}
	
	}
	
	
	
	
	@Override
	public List<Object[]> listeFactureServiceClientAvecOuSansICE(java.util.Date dateDebut,java.util.Date dateFin , Magasin magasin , String ice) {
		// TODO Auto-generated method stub
		Query query=null;
		if(!ice.equals(""))
		{
			
			
			if(ice.equals(Constantes.CODE_OUI))
			{
				query= session.createQuery("select  d.factureServiceTransport.dateFacture,d.factureServiceTransport.numFacture, d.factureServiceTransport.clientPF.code,d.factureServiceTransport.clientPF.nom,d.factureServiceTransport.clientPF.ice,sum(d.montantHT),sum(d.montantTVA),sum(d.montantTTC)  from DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and d.factureServiceTransport.clientPF.ice is not null and d.factureServiceTransport.clientPF.ice <>'' group by d.factureServiceTransport.numFacture order by d.factureServiceTransport.dateFacture");

				
			}else if(ice.equals(Constantes.CODE_NON))
			{
				
				query= session.createQuery("select  d.factureServiceTransport.dateFacture,d.factureServiceTransport.numFacture, d.factureServiceTransport.clientPF.code,d.factureServiceTransport.clientPF.nom,d.factureServiceTransport.clientPF.ice,sum(d.montantHT),sum(d.montantTVA),sum(d.montantTTC)  from DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin and (d.factureServiceTransport.clientPF.ice is null or d.factureServiceTransport.clientPF.ice ='') group by d.factureServiceTransport.numFacture order by d.factureServiceTransport.dateFacture");

				
			}
			
		
		}else
		{
			
			
			query= session.createQuery("select  d.factureServiceTransport.dateFacture,d.factureServiceTransport.numFacture, d.factureServiceTransport.clientPF.code,d.factureServiceTransport.clientPF.nom,d.factureServiceTransport.clientPF.ice,sum(d.montantHT),sum(d.montantTVA),sum(d.montantTTC)  from DetailFactureServiceTransport d where (factureServiceTransport.dateFacture between :dateDebut and :dateFin) and factureServiceTransport.magasin.id=:magasin group by d.factureServiceTransport.numFacture order by d.factureServiceTransport.dateFacture");

		}

		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		
		
		return query.list();

	}
	
	
	
	@Override
	public List<Object[]> listeEtatChiffreAffaireClientAvecICETransportService(String req ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select c.factureServiceTransport.clientPF.nom, c.factureServiceTransport.clientPF.ice,sum(c.montantHT) ,sum(c.montantTTC)  from DetailFactureServiceTransport c  "+req +" and c.factureServiceTransport.clientPF.ice is not null and c.factureServiceTransport.clientPF.ice!=' ' group by c.factureServiceTransport.clientPF.nom, c.factureServiceTransport.clientPF.ice");
			
			
		 
		
		return query.list();

	}
	
	@Override
	public List<Object[]> listeEtatChiffreAffaireClientSansICETransportService(String req ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  sum(c.montantHT),sum(c.montantTTC) from DetailFactureServiceTransport c  "+req +" and (c.factureServiceTransport.clientPF.ice is null or c.factureServiceTransport.clientPF.ice=' ') ");
			
			
		 
		
		return query.list();

	}
	

}
