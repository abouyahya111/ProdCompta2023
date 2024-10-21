package dao.daoImplManager;

import java.sql.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.Fournisseur;

public class FactureAchatDAOImpl implements FactureAchatDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(FactureAchat e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FactureAchat edit(FactureAchat e) {
		
	session.beginTransaction();
	FactureAchat p= (FactureAchat)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FactureAchat p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FactureAchat> findAll() {
		return session.createQuery("select c from FactureAchat c").list();
		}

	public FactureAchat findById(int id) {
		return (FactureAchat)session.get(FactureAchat.class, id);
		}
	
	public FactureAchat findFactureAchatByEtat(String etat) {
		Query query= session.createQuery("select c from FactureAchat c where etat=:etat");
		query.setParameter("etat", etat);
		
		return (FactureAchat) query.uniqueResult();
		
		}
	
	
	
	@Override
	public FactureAchat findByNumFacture(String NumFacture) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture");
		query.setParameter("NumFacture", NumFacture);
				
        return (FactureAchat) query.uniqueResult();
}
	
	
	@Override
	public FactureAchat findByNumFactureBydepot(String NumFacture , Depot depot) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture and c.depot.id=:iddepot");
		query.setParameter("NumFacture", NumFacture);
		query.setParameter("iddepot", depot.getId());
				
        return (FactureAchat) query.uniqueResult();
}
	
	
	@Override
	public List<FactureAchat> findByFournisseur(String codefournisseur) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAchat c where c.fournisseur.code=:codefournisseur");
				query.setParameter("codefournisseur", codefournisseur);
				
				
				return  query.list();
}
	
	@Override
	public List<FactureAchat> findByDate(java.util.Date datefacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAchat c where c.dateFacture=:datefacture");
				query.setParameter("datefacture", datefacture);
				
				
				return  query.list();
}
	
	@Override
	public List<FactureAchat> findByDepot(int iddepot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAchat c where c.depot.id=:iddepot");
				query.setParameter("iddepot", iddepot);
				
				
				return  query.list();
}

	
	@Override
	public List<FactureAchat> findByNumFcatureFournisseurDateFactureDepot(String NumFacture,Fournisseur fournisseur,java.util.Date datefacture,Depot depot) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && fournisseur!=null && datefacture!=null && depot !=null)
		{
			
			 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture and c.fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codefournisseur", fournisseur.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			
			
		}else if(!NumFacture.equals("") && fournisseur==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture ");
			query.setParameter("NumFacture", NumFacture);
		
		}else if(NumFacture.equals("") && fournisseur!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAchat c where  c.fournisseur.code=:codefournisseur ");
			
			query.setParameter("codefournisseur", fournisseur.getCode());
			
				}else if(NumFacture.equals("") && fournisseur==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAchat c where c.dateFacture=:datefacture");
					query.setParameter("datefacture", datefacture);
				
				}else if(NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAchat c where  c.depot.id=:iddepot");
				
					query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture and c.fournisseur.code=:codefournisseur ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						
						
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture and c.fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
				
					
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture  and c.depot.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
					
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture and c.fournisseur.code=:codefournisseur  and c.depot.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						
						query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					
				}else if(NumFacture.equals("") 
						&& fournisseur!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureAchat c where  c.fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						
						
				}else if(NumFacture.equals("")
						&& fournisseur!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAchat c where  c.fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
					
					
				}else if(NumFacture.equals("") && fournisseur!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureAchat c where c.fournisseur.code=:codefournisseur and c.depot.id=:iddepot");
					
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("iddepot", depot.getId());
				}else if(NumFacture.equals("") && fournisseur==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureAchat c where  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
				}
				
				
				
				return  query.list();
}
	
	
	
	@Override
	public List<FactureAchat> findByNumFcatureFournisseurDateFactureDepotEtatRegle(String NumFacture,Fournisseur fournisseur ,java.util.Date datefacture,Depot depot,String etat) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && fournisseur!=null && datefacture!=null && depot !=null)
		{
			
			 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture and c.fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codefournisseur", fournisseur.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			query.setParameter("etat", etat);
			
			
		}else if(!NumFacture.equals("") && fournisseur==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture and c.etat=:etat order by c.dateFacture ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("etat", etat);
		
		}else if(NumFacture.equals("") && fournisseur!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAchat c where  c.fournisseur.code=:codefournisseur and c.etat=:etat order by c.dateFacture ");
			
			query.setParameter("codefournisseur", fournisseur.getCode());
			query.setParameter("etat", etat);
			
				}else if(NumFacture.equals("") && fournisseur==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAchat c where c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture");
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
				
				}else if(NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAchat c where  c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture");
				
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture and c.fournisseur.code=:codefournisseur and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("etat", etat);
						
						
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture and c.fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
				
					
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture  and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
					
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture and c.fournisseur.code=:codefournisseur  and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureAchat c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					
				}else if(NumFacture.equals("") 
						&& fournisseur!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureAchat c where  c.fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
						
				}else if(NumFacture.equals("")
						&& fournisseur!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAchat c where  c.fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture ");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
					
					
				}else if(NumFacture.equals("") && fournisseur!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureAchat c where c.fournisseur.code=:codefournisseur and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
					
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
				}else if(NumFacture.equals("") && fournisseur==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureAchat c where  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat  order by c.dateFacture ");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
				}
				
				
				
				return  query.list();
}	
	
	public FactureAchat findFacturePFByCodeTransfer(String code) {
		Query query= session.createQuery("select c from FactureAchat c where codeTransfer=:code");
		query.setParameter("code", code);
		
		return (FactureAchat) query.uniqueResult();
		
		}
	
	
	public List<FactureAchat> findFacturePFBetweenNumFacture(String du) {
		Query query= session.createQuery("select c from FactureAchat c where  c.numFacture >=:du  and c.magasin.id=7 and c.fournisseur.id=1");
		query.setParameter("du", du);
		//query.setParameter("au", au);
		return  query.list();
		
		}
	
	


}
