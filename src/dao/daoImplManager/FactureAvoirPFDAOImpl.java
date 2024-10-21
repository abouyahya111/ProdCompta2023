package dao.daoImplManager;

import java.sql.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAvoirPFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAchat;
import dao.entity.FactureAvoirPF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.Fournisseur;

public class FactureAvoirPFDAOImpl implements FactureAvoirPFDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(FactureAvoirPF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FactureAvoirPF edit(FactureAvoirPF e) {
		
	session.beginTransaction();
	FactureAvoirPF p= (FactureAvoirPF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FactureAvoirPF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FactureAvoirPF> findAll() {
		return session.createQuery("select c from FactureAvoirPF c").list();
		}

	public FactureAvoirPF findById(int id) {
		return (FactureAvoirPF)session.get(FactureAvoirPF.class, id);
		}
	
	public FactureAvoirPF findFactureAvoirByEtat(String etat) {
		Query query= session.createQuery("select c from FactureAvoirPF c where etat=:etat");
		query.setParameter("etat", etat);
		
		return (FactureAvoirPF) query.uniqueResult();
		
		}
	
	
	
	@Override
	public FactureAvoirPF findByNumFacture(String NumFacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture");
				query.setParameter("NumFacture", NumFacture);
				
				
				return  (FactureAvoirPF) query.uniqueResult();
}
	
	@Override
	public FactureAvoirPF findByNumFactureByDepot(String NumFacture, Depot depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture and c.depot.id=:iddepot");
				query.setParameter("NumFacture", NumFacture);
				query.setParameter("iddepot", depot.getId());
				
				return  (FactureAvoirPF) query.uniqueResult();
}
	
	@Override
	public List<FactureAvoirPF> findByFournisseur(String codefournisseur) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirPF c where c.Fournisseur.code=:codefournisseur");
				query.setParameter("codefournisseur", codefournisseur);
				
				
				return  query.list();
}
	
	@Override
	public List<FactureAvoirPF> findByDate(java.util.Date datefacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirPF c where c.dateFacture=:datefacture");
				query.setParameter("datefacture", datefacture);
				
				
				return  query.list();
}
	
	@Override
	public List<FactureAvoirPF> findByDepot(int iddepot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirPF c where c.depot.id=:iddepot");
				query.setParameter("iddepot", iddepot);
				
				
				return  query.list();
}

	
	@Override
	public List<FactureAvoirPF> findByNumFcatureFournisseurDateFactureDepot(String NumFacture,Fournisseur fournisseur,java.util.Date datefacture,Depot depot) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && fournisseur!=null && datefacture!=null && depot !=null)
		{
			
			 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codefournisseur", fournisseur.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			
			
		}else if(!NumFacture.equals("") && fournisseur==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture ");
			query.setParameter("NumFacture", NumFacture);
		
		}else if(NumFacture.equals("") && fournisseur!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAvoirPF c where  c.Fournisseur.code=:codefournisseur ");
			
			query.setParameter("codefournisseur", fournisseur.getCode());
			
				}else if(NumFacture.equals("") && fournisseur==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirPF c where c.dateFacture=:datefacture");
					query.setParameter("datefacture", datefacture);
				
				}else if(NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirPF c where  c.depot.id=:iddepot");
				
					query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture and c.Fournisseur.code=:codefournisseur ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						
						
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
				
					
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture  and c.depot.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
					
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture and c.Fournisseur.code=:codefournisseur  and c.depot.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						
						query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					
				}else if(NumFacture.equals("") 
						&& fournisseur!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureAvoirPF c where  c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						
						
				}else if(NumFacture.equals("")
						&& fournisseur!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirPF c where  c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
					
					
				}else if(NumFacture.equals("") && fournisseur!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureAvoirPF c where c.Fournisseur.code=:codefournisseur and c.depot.id=:iddepot");
					
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("iddepot", depot.getId());
				}else if(NumFacture.equals("") && fournisseur==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureAvoirPF c where  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
				}
				
				
				
				return  query.list();
}
	
	
	
	@Override
	public List<FactureAvoirPF> findByNumFcatureFournisseurDateFactureDepotEtatRegle(String NumFacture,Fournisseur fournisseur ,java.util.Date datefacture,Depot depot,String etat) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && fournisseur!=null && datefacture!=null && depot !=null)
		{
			
			 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codefournisseur", fournisseur.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			query.setParameter("etat", etat);
			
			
		}else if(!NumFacture.equals("") && fournisseur==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture and c.etat=:etat order by c.dateFacture ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("etat", etat);
		
		}else if(NumFacture.equals("") && fournisseur!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAvoirPF c where  c.Fournisseur.code=:codefournisseur and c.etat=:etat order by c.dateFacture ");
			
			query.setParameter("codefournisseur", fournisseur.getCode());
			query.setParameter("etat", etat);
			
				}else if(NumFacture.equals("") && fournisseur==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirPF c where c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture");
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
				
				}else if(NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirPF c where  c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture");
				
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("etat", etat);
						
						
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
				
					
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture  and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
					
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture and c.Fournisseur.code=:codefournisseur  and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureAvoirPF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					
				}else if(NumFacture.equals("") 
						&& fournisseur!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureAvoirPF c where  c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
						
				}else if(NumFacture.equals("")
						&& fournisseur!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirPF c where  c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
					
					
				}else if(NumFacture.equals("") && fournisseur!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureAvoirPF c where c.Fournisseur.code=:codefournisseur and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
					
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
				}else if(NumFacture.equals("") && fournisseur==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureAvoirPF c where  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
				}
				
				
				return  query.list();
}	
	
	public FactureAvoirPF findFactureAvoirPFByCodeTransfer(String code) {
		Query query= session.createQuery("select c from FactureAvoirPF c where codeTransfer=:code");
		query.setParameter("code", code);
		
		return (FactureAvoirPF) query.uniqueResult();
		
		}
	
	


}
