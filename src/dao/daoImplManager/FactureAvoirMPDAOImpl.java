package dao.daoImplManager;

import java.sql.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FactureAvoirMPDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAchat;
import dao.entity.FactureAchatMP;
import dao.entity.FactureAvoirMP;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.Fournisseur;

public class FactureAvoirMPDAOImpl implements FactureAvoirMPDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(FactureAvoirMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FactureAvoirMP edit(FactureAvoirMP e) {
		
	session.beginTransaction();
	FactureAvoirMP p= (FactureAvoirMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FactureAvoirMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FactureAvoirMP> findAll() {
		return session.createQuery("select c from FactureAvoirMP c").list();
		}

	public FactureAvoirMP findById(int id) {
		return (FactureAvoirMP)session.get(FactureAvoirMP.class, id);
		}
	
	public FactureAvoirMP findFactureAvoirByEtat(String etat) {
		Query query= session.createQuery("select c from FactureAvoirMP c where etat=:etat");
		query.setParameter("etat", etat);
		
		return (FactureAvoirMP) query.uniqueResult();
		
		}
	
	
	
	@Override
	public FactureAvoirMP findByNumFacture(String NumFacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture");
				query.setParameter("NumFacture", NumFacture);
				
				
				return  (FactureAvoirMP) query.uniqueResult();
}
	
	
	@Override
	public FactureAvoirMP findByNumFactureByDepot(String NumFacture,Depot depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture and c.depot.id=:iddepot");
				query.setParameter("NumFacture", NumFacture);
				query.setParameter("iddepot", depot.getId());
				
				return  (FactureAvoirMP) query.uniqueResult();
}
	
	
	@Override
	public List<FactureAvoirMP> findByFournisseur(String codefournisseur) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirMP c where c.Fournisseur.code=:codefournisseur");
				query.setParameter("codefournisseur", codefournisseur);
				
				
				return  query.list();
}
	
	@Override
	public List<FactureAvoirMP> findByDate(java.util.Date datefacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirMP c where c.dateFacture=:datefacture");
				query.setParameter("datefacture", datefacture);
				
				
				return  query.list();
}
	
	@Override
	public List<FactureAvoirMP> findByDepot(int iddepot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirMP c where c.depot.id=:iddepot");
				query.setParameter("iddepot", iddepot);
				
				
				return  query.list();
}

	
	@Override
	public List<FactureAvoirMP> findByNumFcatureFournisseurDateFactureDepot(String NumFacture,Fournisseur fournisseur,java.util.Date datefacture,Depot depot) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && fournisseur!=null && datefacture!=null && depot !=null)
		{
			
			 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codefournisseur", fournisseur.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			
			
		}else if(!NumFacture.equals("") && fournisseur==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture ");
			query.setParameter("NumFacture", NumFacture);
		
		}else if(NumFacture.equals("") && fournisseur!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAvoirMP c where  c.Fournisseur.code=:codefournisseur ");
			
			query.setParameter("codefournisseur", fournisseur.getCode());
			
				}else if(NumFacture.equals("") && fournisseur==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirMP c where c.dateFacture=:datefacture");
					query.setParameter("datefacture", datefacture);
				
				}else if(NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirMP c where  c.depot.id=:iddepot");
				
					query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						
						
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
				
					
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture  and c.depot.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
					
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur  and c.depot.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						
						query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					
				}else if(NumFacture.equals("") 
						&& fournisseur!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureAvoirMP c where  c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						
						
				}else if(NumFacture.equals("")
						&& fournisseur!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirMP c where  c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
					
					
				}else if(NumFacture.equals("") && fournisseur!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureAvoirMP c where c.Fournisseur.code=:codefournisseur and c.depot.id=:iddepot");
					
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("iddepot", depot.getId());
				}else if(NumFacture.equals("") && fournisseur==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureAvoirMP c where  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
				}
				
				
				
				return  query.list();
}
	
	
	
	@Override
	public List<FactureAvoirMP> findByNumFcatureFournisseurDateFactureDepotEtatRegle(String NumFacture,Fournisseur fournisseur ,java.util.Date datefacture,Depot depot,String etat) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && fournisseur!=null && datefacture!=null && depot !=null)
		{
			
			 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codefournisseur", fournisseur.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			query.setParameter("etat", etat);
			
			
		}else if(!NumFacture.equals("") && fournisseur==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture and c.etat=:etat order by c.dateFacture ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("etat", etat);
		
		}else if(NumFacture.equals("") && fournisseur!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAvoirMP c where  c.Fournisseur.code=:codefournisseur and c.etat=:etat order by c.dateFacture ");
			
			query.setParameter("codefournisseur", fournisseur.getCode());
			query.setParameter("etat", etat);
			
				}else if(NumFacture.equals("") && fournisseur==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirMP c where c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture ");
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
				
				}else if(NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirMP c where  c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
				
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("etat", etat);
						
						
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
				
					
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture  and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
					
				}else if(!NumFacture.equals("")  && fournisseur!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture and c.Fournisseur.code=:codefournisseur  and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codefournisseur", fournisseur.getCode());
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
				}else if(!NumFacture.equals("")  && fournisseur==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureAvoirMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					
				}else if(NumFacture.equals("") 
						&& fournisseur!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureAvoirMP c where  c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
						
				}else if(NumFacture.equals("")
						&& fournisseur!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirMP c where  c.Fournisseur.code=:codefournisseur and  c.dateFacture=:datefacture and c.etat=:etat order by c.dateFacture");
						
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
					
					
				}else if(NumFacture.equals("") && fournisseur!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureAvoirMP c where c.Fournisseur.code=:codefournisseur and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
					
						query.setParameter("codefournisseur", fournisseur.getCode());
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
				}else if(NumFacture.equals("") && fournisseur==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureAvoirMP c where  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
				}
				
				return  query.list();
}	
	
	public FactureAvoirMP findFactureAvoirMPByCodeTransfer(String code) {
		Query query= session.createQuery("select c from FactureAvoirMP c where codeTransfer=:code");
		query.setParameter("code", code);
		
		return (FactureAvoirMP) query.uniqueResult();
		
		}
	
	


}
