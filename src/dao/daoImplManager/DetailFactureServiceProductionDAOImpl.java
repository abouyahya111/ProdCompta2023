package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;

public class DetailFactureServiceProductionDAOImpl implements DetailFactureServiceProductionDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailFactureServiceProduction e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailFactureServiceProduction edit(DetailFactureServiceProduction e) {
		
	session.beginTransaction();
	DetailFactureServiceProduction p= (DetailFactureServiceProduction)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailFactureServiceProduction p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailFactureServiceProduction> findAll() {
		return session.createQuery("select c from DetailFactureServiceProduction c").list();
		}

	public DetailFactureServiceProduction findById(int id) {
		return (DetailFactureServiceProduction)session.get(DetailFactureServiceProduction.class, id);
		}
	
	
	
	public List<DetailFactureServiceProduction> listeDetailFactureServiceProductionByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureServiceProduction c where factureService.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	public List<DetailFactureServiceProduction> listeDetailFactureServiceProductionByDate(Date dateDebut,Date dateFin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureServiceProduction c where factureService.dateFacture between :dateDebut and :dateFin ");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		
		return query.list();

	}
	
	
	
	public List<DetailFactureServiceProduction> listeDetailFactureServiceProductionByDepotByReq(int idDepot,String req) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureServiceProduction c where factureService.depot.id=:idDepot  "+req);
		query.setParameter("idDepot", idDepot);
		
		
		return query.list();

	}
	
	
	
	public DetailFactureServiceProduction DetailFactureServiceProductionByFactureByArticle(int idfacture,String article) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.id=:idfacture and  c.article=:article ");
		query.setParameter("idfacture", idfacture);
		query.setParameter("article", article);
		
		return (DetailFactureServiceProduction) query.uniqueResult();

	}
	
	

	@Override
	public List<DetailFactureServiceProduction> findByNumFcatureClientDateFactureDepotEtatRegle(String numFacture,Client client,java.util.Date datedebut,java.util.Date datefin ,Depot depot) {
		
		Query query = null;
		
		if(!numFacture.equals("") && client!=null && datedebut!=null && datefin!=null && depot !=null )
		{
			
			 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture and c.factureService.client.id=:client and  c.factureService.dateFacture between :datedebut and :datefin and c.factureService.depot.id=:iddepot  order by c.factureService.dateFacture , c.factureService.numFacture ");
			query.setParameter("numFacture", numFacture);
			query.setParameter("client", client.getId());
			query.setParameter("datedebut", datedebut);
			query.setParameter("datefin", datefin);
			query.setParameter("iddepot", depot.getId());
		
		
			
			
		}else if(!numFacture.equals("") && client==null && datedebut==null && datefin==null && depot==null)
		{
			 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture  order by c.factureService.dateFacture , c.factureService.numFacture ");
			query.setParameter("numFacture", numFacture);
			
			
		
		}else if(numFacture.equals("") && client!=null && datedebut==null && datefin==null && depot==null)
		{
			 query= session.createQuery("select c from DetailFactureServiceProduction c where  c.factureService.client.id=:client  order by c.factureService.dateFacture , c.factureService.numFacture ");
			
			 query.setParameter("client", client.getId());
			
			
			
				}else if(numFacture.equals("") && client==null && datedebut!=null && datefin!=null && depot==null)
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.dateFacture between :datedebut and :datefin    order by c.factureService.dateFacture , c.factureService.numFacture ");
					 query.setParameter("datedebut", datedebut);
						query.setParameter("datefin", datefin);
				
					
				
				}else if(numFacture.equals("")  && client==null && datedebut==null && datefin==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where  c.factureService.depot.id=:iddepot  order by c.factureService.dateFacture , c.factureService.numFacture ");
				
					query.setParameter("iddepot", depot.getId());
				
				
					
				}else if(!numFacture.equals("")  && client!=null && datedebut==null && datefin==null	&& depot==null)
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture and c.factureService.client.id=:client  order by c.factureService.dateFacture , c.factureService.numFacture ");
						query.setParameter("numFacture", numFacture);
						query.setParameter("client", client.getId());
						
						
						
						
				}else if(!numFacture.equals("")  && client!=null && datedebut!=null && datefin!=null	&& depot==null)
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture and c.factureService.client.id=:client and  c.factureService.dateFacture between :datedebut and :datefin  order by c.factureService.dateFacture , c.factureService.numFacture ");
						query.setParameter("numFacture", numFacture);
						query.setParameter("client", client.getId());
						 query.setParameter("datedebut", datedebut);
							query.setParameter("datefin", datefin);
					
						
						
				}else if(!numFacture.equals("")  && client==null && datedebut!=null && datefin!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture  and  c.factureService.dateFacture between :datedebut and :datefin  order by c.factureService.dateFacture , c.factureService.numFacture ");
						query.setParameter("numFacture", numFacture);						
						 query.setParameter("datedebut", datedebut);
							query.setParameter("datefin", datefin);
					
						
				
					
				}else if(!numFacture.equals("")  && client==null && datedebut==null && datefin==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture  and c.factureService.depot.id=:iddepot  order by c.factureService.dateFacture , c.factureService.numFacture ");
						query.setParameter("numFacture", numFacture);
						
						query.setParameter("iddepot", depot.getId());
					
						
					
				}else if(!numFacture.equals("")  && client!=null && datedebut==null && datefin==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture and c.factureService.client.id=:client  and c.factureService.depot.id=:iddepot  order by c.factureService.dateFacture , c.factureService.numFacture ");
						query.setParameter("numFacture", numFacture);
						query.setParameter("client", client.getId());
						
						query.setParameter("iddepot", depot.getId());
						
						
						
				}else if(!numFacture.equals("")  && client==null && datedebut!=null && datefin!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture  and  c.factureService.dateFacture  between :datedebut and :datefin and c.factureService.depot.id=:iddepot  order by c.factureService.dateFacture , c.factureService.numFacture ");
					query.setParameter("numFacture", numFacture);
					
					 query.setParameter("datedebut", datedebut);
						query.setParameter("datefin", datefin);
					query.setParameter("iddepot", depot.getId());
				
					
					
				}else if(numFacture.equals("") 
						&& client!=null
						&& datedebut!=null && datefin!=null
						&& depot!=null)
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where  c.factureService.client.id=:client and  c.factureService.dateFacture between :datedebut and :datefin and c.factureService.depot.id=:iddepot  order by c.factureService.dateFacture , c.factureService.numFacture ");
						
					 query.setParameter("client", client.getId());
						 query.setParameter("datedebut", datedebut);
							query.setParameter("datefin", datefin);
						query.setParameter("iddepot", depot.getId());
						
						
						
						
				}else if(numFacture.equals("")
						&& client!=null
						&& datedebut!=null && datefin!=null		&& depot==null)
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where  c.factureService.client.id=:client and  c.factureService.dateFacture between :datedebut and :datefin  order by c.factureService.dateFacture , c.factureService.numFacture ");
						
					 query.setParameter("client", client.getId());
						 query.setParameter("datedebut", datedebut);
							query.setParameter("datefin", datefin);
						
						
					
					
				}else if(numFacture.equals("") && client!=null  && datedebut==null && datefin==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.client.id=:client and c.factureService.depot.id=:iddepot  order by c.factureService.dateFacture , c.factureService.numFacture ");
					
					 query.setParameter("client", client.getId());
						query.setParameter("iddepot", depot.getId());
					
						
						
				}else if(numFacture.equals("") && client==null  && datedebut!=null && datefin!=null		&&  depot!=null)
				{
					
					 query= session.createQuery("select c from DetailFactureServiceProduction c where  c.factureService.dateFacture between :datedebut and :datefin and c.factureService.depot.id=:iddepot  order by c.factureService.dateFacture , c.factureService.numFacture ");
						
					 query.setParameter("datedebut", datedebut);
						query.setParameter("datefin", datefin);
						query.setParameter("iddepot", depot.getId());
					
						
				}
				
				
				
				return  query.list();
}	
	
	
	@Override
	public List<DetailFactureServiceProduction> findByNumFcatureClientDateFactureDepotEtatRegleOrdreByArticleBySousFamille(String numFacture,Client client,java.util.Date datedebut,java.util.Date datefin ,Depot depot) {
		
		Query query = null;
		
		if(!numFacture.equals("") && client!=null && datedebut!=null && datefin!=null && depot !=null )
		{
			
			 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture and c.factureService.client.id=:client and  c.factureService.dateFacture between :datedebut and :datefin and c.factureService.depot.id=:iddepot  order by c.article");
			query.setParameter("numFacture", numFacture);
			query.setParameter("client", client.getId());
			query.setParameter("datedebut", datedebut);
			query.setParameter("datefin", datefin);
			query.setParameter("iddepot", depot.getId());
		
		
			
			
		}else if(!numFacture.equals("") && client==null && datedebut==null && datefin==null && depot==null)
		{
			 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture   order by c.article ");
			query.setParameter("numFacture", numFacture);
			
			
		
		}else if(numFacture.equals("") && client!=null && datedebut==null && datefin==null && depot==null)
		{
			 query= session.createQuery("select c from DetailFactureServiceProduction c where  c.factureService.client.id=:client   order by c.article ");
			
			 query.setParameter("client", client.getId());
			
			
			
				}else if(numFacture.equals("") && client==null && datedebut!=null && datefin!=null && depot==null)
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.dateFacture between :datedebut and :datefin     order by c.article ");
					 query.setParameter("datedebut", datedebut);
						query.setParameter("datefin", datefin);
				
					
				
				}else if(numFacture.equals("")  && client==null && datedebut==null && datefin==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where  c.factureService.depot.id=:iddepot   order by c.article ");
				
					query.setParameter("iddepot", depot.getId());
				
				
					
				}else if(!numFacture.equals("")  && client!=null && datedebut==null && datefin==null	&& depot==null)
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture and c.factureService.client.id=:client   order by c.article ");
						query.setParameter("numFacture", numFacture);
						query.setParameter("client", client.getId());
						
						
						
						
				}else if(!numFacture.equals("")  && client!=null && datedebut!=null && datefin!=null	&& depot==null)
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture and c.factureService.client.id=:client and  c.factureService.dateFacture between :datedebut and :datefin   order by c.article ");
						query.setParameter("numFacture", numFacture);
						query.setParameter("client", client.getId());
						 query.setParameter("datedebut", datedebut);
							query.setParameter("datefin", datefin);
					
						
						
				}else if(!numFacture.equals("")  && client==null && datedebut!=null && datefin!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture  and  c.factureService.dateFacture between :datedebut and :datefin   order by c.article ");
						query.setParameter("numFacture", numFacture);						
						 query.setParameter("datedebut", datedebut);
							query.setParameter("datefin", datefin);
					
						
				
					
				}else if(!numFacture.equals("")  && client==null && datedebut==null && datefin==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture  and c.factureService.depot.id=:iddepot   order by c.article ");
						query.setParameter("numFacture", numFacture);
						
						query.setParameter("iddepot", depot.getId());
					
						
					
				}else if(!numFacture.equals("")  && client!=null && datedebut==null && datefin==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture and c.factureService.client.id=:client  and c.factureService.depot.id=:iddepot   order by c.article ");
						query.setParameter("numFacture", numFacture);
						query.setParameter("client", client.getId());
						
						query.setParameter("iddepot", depot.getId());
						
						
						
				}else if(!numFacture.equals("")  && client==null && datedebut!=null && datefin!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.numFacture=:numFacture  and  c.factureService.dateFacture  between :datedebut and :datefin and c.factureService.depot.id=:iddepot   order by c.article ");
					query.setParameter("numFacture", numFacture);
					
					 query.setParameter("datedebut", datedebut);
						query.setParameter("datefin", datefin);
					query.setParameter("iddepot", depot.getId());
				
					
					
				}else if(numFacture.equals("") 
						&& client!=null
						&& datedebut!=null && datefin!=null
						&& depot!=null)
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where  c.factureService.client.id=:client and  c.factureService.dateFacture between :datedebut and :datefin and c.factureService.depot.id=:iddepot  order by c.article ");
						
					 query.setParameter("client", client.getId());
						 query.setParameter("datedebut", datedebut);
							query.setParameter("datefin", datefin);
						query.setParameter("iddepot", depot.getId());
						
						
						
						
				}else if(numFacture.equals("")
						&& client!=null
						&& datedebut!=null && datefin!=null		&& depot==null)
				{
					 query= session.createQuery("select c from DetailFactureServiceProduction c where  c.factureService.client.id=:client and  c.factureService.dateFacture between :datedebut and :datefin   order by c.article ");
						
					 query.setParameter("client", client.getId());
						 query.setParameter("datedebut", datedebut);
							query.setParameter("datefin", datefin);
						
						
					
					
				}else if(numFacture.equals("") && client!=null  && datedebut==null && datefin==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from DetailFactureServiceProduction c where c.factureService.client.id=:client and c.factureService.depot.id=:iddepot   order by c.article ");
					
					 query.setParameter("client", client.getId());
						query.setParameter("iddepot", depot.getId());
					
						
						
				}else if(numFacture.equals("") && client==null  && datedebut!=null && datefin!=null		&&  depot!=null)
				{
					
					 query= session.createQuery("select c from DetailFactureServiceProduction c where  c.factureService.dateFacture between :datedebut and :datefin and c.factureService.depot.id=:iddepot   order by c.article ");
						
					 query.setParameter("datedebut", datedebut);
						query.setParameter("datefin", datefin);
						query.setParameter("iddepot", depot.getId());
					
						
				}
				
				
				
				return  query.list();
}	
	
	
	
	public void ViderSession()
{
	if(session!=null)
	{
		session.clear();
	}
}
	
	
	
	public List<Object[]> SommeMontantParArticleService(Client client ,java.util.Date datedebut,java.util.Date datefin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c.article, d.sousFamille.code, sum(c.montantHT), sum(c.quantite) from DetailFactureServiceProduction c  , DetailTransferProduitFini d where d.transferStockPF.CodeTransfer=c.factureService.numOF and c.article=d.article.liblle and c.factureService.client.id=:client and c.factureService.dateFacture between :datedebut and :datefin  group by c.article, d.sousFamille.code");
		 query.setParameter("client", client.getId());
		 query.setParameter("datedebut", datedebut);
			query.setParameter("datefin", datefin);
		return  query.list();

		
	}
	
	
	
	@Override
	public List<Object[]> listeEtatChiffreAffaireClientAvecICEProductionService(String req ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select c.factureService.client.nom, c.factureService.client.ice,sum(c.montantHT) ,sum( case when c.factureService.montantTVA=0  then c.montantHT else (c.montantHT*1.2) end) from DetailFactureServiceProduction c  "+req +" and c.factureService.client.ice is not null and c.factureService.client.ice!=' ' group by c.factureService.client.nom, c.factureService.client.ice");
			
			
		 
		
		return query.list();

	}
	
	@Override
	public List<Object[]> listeEtatChiffreAffaireClientSansICEProductionService(String req ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select  sum(round( c.montantHT,2)),sum( case when c.factureService.montantTVA=0  then round(c.montantHT,2) else (round( c.montantHT,2)*1.2) end) from DetailFactureServiceProduction c  "+req +" and (c.factureService.client.ice is null or c.factureService.client.ice=' ') ");
			
			
		 
		
		return query.list();

	}
	
	
	
	


}
