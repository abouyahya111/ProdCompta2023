package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailFactureAutresVenteDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFactureAutresVente;
import dao.entity.DetailFacturePF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public class DetailFactureAutresVenteDAOImpl implements DetailFactureAutresVenteDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailFactureAutresVente e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailFactureAutresVente edit(DetailFactureAutresVente e) {
		
	session.beginTransaction();
	DetailFactureAutresVente p= (DetailFactureAutresVente)session.merge(e);
	session.getTransaction().commit();
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailFactureAutresVente p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailFactureAutresVente> findAll() {
		return session.createQuery("select c from DetailFactureAutresVente c").list();
		}

	public DetailFactureAutresVente findById(int id) {
		return (DetailFactureAutresVente)session.get(DetailFactureAutresVente.class, id);
		}
	
	
	
	@Override
	public List<DetailFactureAutresVente> listeDetailFacturePFByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	
	@Override
	public List<DetailFactureAutresVente> listeDetailFacturePFByNumFacture(String Numfacture, Depot depot) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.id in (select id from FactureAutresVente where numFacture=:Numfacture and depot.id=:depot)");
		query.setParameter("Numfacture", Numfacture);
		query.setParameter("depot", depot.getId());
		return query.list();
		
		
	}

	
	
	
	@Override
	public List<DetailFactureAutresVente> listeDetailFacturePFByDate(Date dateDebut,Date dateFin , Depot depot) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.dateFacture between :dateDebut and :dateFin and factureautresaente.depot.id=:depot");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("depot", depot.getId());
		return query.list();

	}
	
	
	
	//liste des BL / factures entre deux date
	
	public List<DetailFactureAutresVente> findDetailBLEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Depot iddepot,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle , String type) {
		
		// TODO Auto-generated method stub
		Query query=null;
	if(type.equals(Constantes.TYPE_BON_LIVRAISON))
	{
		if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.type =:BL  and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.type =:BL  and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.type =:BL and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
			
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.type =:BL and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.type =:BL and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.type =:BL and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureautresaente.type =:BL and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureautresaente.type =:BL and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
			
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureautresaente.type =:BL and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  factureautresaente.type =:BL and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureautresaente.type =:BL and factureautresaente.depot.id=:iddepotorder by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
				
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and  factureautresaente.type =:BL and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("codeclient", client.getCode());	
		}
			
	}else if(type.equals(Constantes.TYPE_FACTURE))
	{

		if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepot and factureautresaente.type <>:BL order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepot and factureautresaente.type <>:BL order by factureautresaente.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepot and factureautresaente.type <>:BL order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
			
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepot and factureautresaente.type <>:BL order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepot and factureautresaente.type <>:BL order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepotand factureautresaente.type <>:BL order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepot and sousFamille.famileArticlePF.id=:famille and factureautresaente.type <>:BL order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureautresaente.depot.id=:iddepot and factureautresaente.type <>:BL order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
			
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureautresaente.depot.id=:iddepot and factureautresaente.type <>:BL order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and factureautresaente.depot.id=:iddepot and factureautresaente.type <>:BL order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureautresaente.depot.id=:iddepot and factureautresaente.type <>:BL order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
				
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and factureautresaente.depot.id=:iddepot and  factureautresaente.type <>:BL order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				query.setParameter("BL", "BL");
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("codeclient", client.getCode());	
		}
			
		
	}else
	{
		


		if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null && familleArticle==null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepot  order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				
				query.setParameter("codeclient", client.getCode());
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				
				query.setParameter("codeclient", client.getCode());	
			
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
			
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null &&  iddepot !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepot  order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				
				
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle==null && article==null && tousfamille==Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepot  order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and factureautresaente.depot.id=:iddepot and sousFamille.famileArticlePF.id=:famille  order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			

			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				
				query.setParameter("famille", familleArticle.getId());
				
			
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				
				query.setParameter("famille", familleArticle.getId());
				
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client==null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where  factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
			
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle!=null && article==null && tousfamille!=Constantes.TOUS && touarticle==Constantes.TOUS )
		{
			
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and factureautresaente.depot.id=:iddepot  order by factureautresaente.dateFacture");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
			
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("codeclient", client.getCode());	
				
		}else if(dateDebut!=null && datedeFin!=null && iddepot !=null && client!=null  && familleArticle!=null && article!=null && tousfamille!=Constantes.TOUS && touarticle!=Constantes.TOUS )
		{
			 query= session.createQuery("select c from DetailFactureAutresVente c where factureautresaente.clientPF.code=:codeclient and factureautresaente.dateFacture between :dateDebut and :datedeFin and sousFamille.famileArticlePF.id=:famille and article.id=:article and factureautresaente.depot.id=:iddepot order by factureautresaente.dateFacture ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("iddepot", iddepot.getId());
				
				query.setParameter("famille", familleArticle.getId());
				query.setParameter("article", article.getId());
				query.setParameter("codeclient", client.getCode());	
		}
			
		
	
		
		
		
		
	}
				
				return  query.list();
}	
	
	
	
	
	
	
	
	
	public void ViderSession()
	{
		
		if (session!=null)
		{
			session.clear();
			
		}
	
	}
	
	
	
	
	
	
	
	
	
	

}
