package dao.daoImplManager;

import java.sql.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAchat;
import dao.entity.FactureAchatMP;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.Fournisseur;

public class FactureAchatMPDAOImpl implements FactureAchatMPDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(FactureAchatMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FactureAchatMP edit(FactureAchatMP e) {
		
	session.beginTransaction();
	FactureAchatMP p= (FactureAchatMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FactureAchatMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FactureAchatMP> findAll() {
		return session.createQuery("select c from FactureAchatMP c").list();
		}

	public FactureAchatMP findById(int id) {
		return (FactureAchatMP)session.get(FactureAchatMP.class, id);
		}
	
	public FactureAchatMP findFactureAchatByEtat(String etat) {
		Query query= session.createQuery("select c from FactureAchatMP c where etat=:etat");
		query.setParameter("etat", etat);
		
		return (FactureAchatMP) query.uniqueResult();
		
		}
	
	
	
	@Override
	public FactureAchatMP findByNumFacture(String NumFacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture");
				query.setParameter("NumFacture", NumFacture);
				
				
				return (FactureAchatMP) query.uniqueResult();
}
	
	
	public FactureAchatMP findByNumFactureByDepot(String NumFacture , Depot depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture and c.depot.id=:iddepot");
				query.setParameter("NumFacture", NumFacture);
				query.setParameter("iddepot", depot.getId());
				
				return (FactureAchatMP) query.uniqueResult();
}
	
	@Override
	public List<FactureAchatMP> findByFournisseur(String codefournisseur) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAchatMP c where c.Fournisseur.code=:codefournisseur");
				query.setParameter("codefournisseur", codefournisseur);
				
				
				return  query.list();
}
	
	@Override
	public List<FactureAchatMP> findByDate(java.util.Date datefacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAchatMP c where c.dateFacture=:datefacture");
				query.setParameter("datefacture", datefacture);
				
				
				return  query.list();
}
	
	@Override
	public List<FactureAchatMP> findByDepot(int iddepot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAchatMP c where c.depot.id=:iddepot");
				query.setParameter("iddepot", iddepot);
				
				
				return  query.list();
}

	
	@Override
	public List<FactureAchatMP> findByNumFcatureFournisseurDateFactureDepot(String NumFacture,Fournisseur fournisseur,java.util.Date datefacture,Depot depot) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && fournisseur!=null && datefacture!=null && depot !=null)
		{
			
			 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codefournisseur", fournisseur.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			
			
		}else if(!NumFacture.equals("") && fournisseur==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture ");
			query.setParameter("NumFacture", NumFacture);
		
		}else if(NumFacture.equals("") && fournisseur!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAchatMP c where  c.Fournisseur.code=:codefournisseur ");
			
			query.setParameter("codefournisseur", fournisseur.getCode());
			
				}else if(NumFacture.equals("") && fournisseur==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAchatMP c where c.dateFacture=:datefacture");
					query.setParameter("datefacture", datefacture);
				
				}else if(NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAchatMP c where  c.depot.id=:iddepot");
				
					query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						
						
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
				
					
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture  and c.depot.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
					
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur  and c.depot.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						
						query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					
				}else if(NumFacture.equals("") 
						&& fournisseur!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureAchatMP c where  c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						
						
				}else if(NumFacture.equals("")
						&& fournisseur!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAchatMP c where  c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
					
					
				}else if(NumFacture.equals("") && fournisseur!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureAchatMP c where c.Fournisseur.code=:codefournisseur and c.depot.id=:iddepot");
					
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("iddepot", depot.getId());
				}else if(NumFacture.equals("") && fournisseur==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureAchatMP c where  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
				}
				
				
				
				return  query.list();
}
	
	
	
	@Override
	public List<FactureAchatMP> findByNumFcatureFournisseurDateFactureDepotEtatRegle(String NumFacture,Fournisseur fournisseur ,java.util.Date datefacture,Depot depot,String etat) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && fournisseur!=null && datefacture!=null && depot !=null)
		{
			
			 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codefournisseur", fournisseur.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			query.setParameter("etat", etat);
			
			
		}else if(!NumFacture.equals("") && fournisseur==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture and c.etat=:etat order by c.dateFacture ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("etat", etat);
		
		}else if(NumFacture.equals("") && fournisseur!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAchatMP c where  c.Fournisseur.code=:codefournisseur and c.etat=:etat order by c.dateFacture");
			
			query.setParameter("codefournisseur", fournisseur.getCode());
			query.setParameter("etat", etat);
			
				}else if(NumFacture.equals("") && fournisseur==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAchatMP c where c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture ");
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
				
				}else if(NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAchatMP c where  c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture");
				
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and c.etat=:etat order by c.dateFacture");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("etat", etat);
						
						
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
				
					
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture  and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
					
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur  and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureAchatMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					
				}else if(NumFacture.equals("") 
						&& fournisseur!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureAchatMP c where  c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
						
				}else if(NumFacture.equals("")
						&& fournisseur!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAchatMP c where  c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
					
					
				}else if(NumFacture.equals("") && fournisseur!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureAchatMP c where c.Fournisseur.code=:codefournisseur and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
					
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
				}else if(NumFacture.equals("") && fournisseur==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureAchatMP c where  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat  order by c.dateFacture");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
				}
				
				
				
				return  query.list();
}	
	
	public FactureAchatMP findFacturePFByCodeTransfer(String code) {
		Query query= session.createQuery("select c from FactureAchatMP c where codeTransfer=:code");
		query.setParameter("code", code);
		
		return (FactureAchatMP) query.uniqueResult();
		
		}
	
	


}
