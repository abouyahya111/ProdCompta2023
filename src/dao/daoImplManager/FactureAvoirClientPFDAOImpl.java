package dao.daoImplManager;

import java.sql.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAvoirClientPFDAO;
import dao.daoManager.FactureAvoirPFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAchat;
import dao.entity.FactureAvoirClientPF;
import dao.entity.FactureAvoirPF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.Fournisseur;
import dao.entity.Magasin;

public class FactureAvoirClientPFDAOImpl implements FactureAvoirClientPFDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(FactureAvoirClientPF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FactureAvoirClientPF edit(FactureAvoirClientPF e) {
		
	session.beginTransaction();
	FactureAvoirClientPF p= (FactureAvoirClientPF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FactureAvoirClientPF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FactureAvoirClientPF> findAll() {
		return session.createQuery("select c from FactureAvoirClientPF c").list();
		}

	public FactureAvoirClientPF findById(int id) {
		return (FactureAvoirClientPF)session.get(FactureAvoirClientPF.class, id);
		}
	
	public FactureAvoirClientPF findFactureAvoirByEtat(String etat) {
		Query query= session.createQuery("select c from FactureAvoirClientPF c where etat=:etat");
		query.setParameter("etat", etat);
		
		return (FactureAvoirClientPF) query.uniqueResult();
		
		}
	
	
	
	public FactureAvoirClientPF findByNumFacture(String NumFacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture");
				query.setParameter("NumFacture", NumFacture);
				
				
				return  (FactureAvoirClientPF) query.uniqueResult();
}
	
	public List<FactureAvoirClientPF> findByNumFactureByDepot(String NumFacture, Depot depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.depot.id=:iddepot");
				query.setParameter("NumFacture", NumFacture);
				query.setParameter("iddepot", depot.getId());
				
				return   query.list();
}
	
	
	
	public FactureAvoirClientPF findByNumBLByDepot(String NumBL, Depot depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirClientPF c where c.numBL=:NumBL and c.depot.id=:iddepot");
				query.setParameter("NumBL", NumBL);
				query.setParameter("iddepot", depot.getId());
				
				return  (FactureAvoirClientPF) query.uniqueResult();
}
	
	public List<FactureAvoirClientPF> findByClientPF(ClientPF clientPF) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirClientPF c where c.clientPF.id=:clientPF");
				query.setParameter("clientPF", clientPF.getIce());
				
				
				return  query.list();
}
	
	@Override
	public List<FactureAvoirClientPF> findByDate(java.util.Date datefacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirClientPF c where c.dateFacture=:datefacture");
				query.setParameter("datefacture", datefacture);
				
				
				return  query.list();
}
	
	public List<FactureAvoirClientPF> findByDepot(int iddepot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAvoirClientPF c where c.depot.id=:iddepot");
				query.setParameter("iddepot", iddepot);
				
				
				return  query.list();
}

	
	public List<FactureAvoirClientPF> findByNumFcatureClientPFDateFactureDepot(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && depot !=null)
		{
			
			 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.clientPF.id=:clientPF and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("clientPF", clientpf.getIce());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			
			
		}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture ");
			query.setParameter("NumFacture", NumFacture);
		
		}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAvoirClientPF c where  c.clientPF.id=:clientPF ");
			
			query.setParameter("clientPF", clientpf.getId());
			
				}else if(NumFacture.equals("") && clientpf==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.dateFacture=:datefacture");
					query.setParameter("datefacture", datefacture);
				
				}else if(NumFacture.equals("")  && clientpf==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where  c.depot.id=:iddepot");
				
					query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.clientPF.id=:clientPF ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("clientPF", clientpf.getId());
						
						
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.clientPF.id=:clientPF and  c.dateFacture=:datefacture");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("clientPF", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
				
					
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture  and c.depot.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.clientPF.id=:clientPF  and c.depot.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("clientPF", clientpf.getId());
						
						query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					
				}else if(NumFacture.equals("") 
						&& clientpf!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where  c.clientPF.id=:clientPF and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("clientPF", clientpf.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						
						
				}else if(NumFacture.equals("")
						&& clientpf!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where  c.clientPF.id=:clientPF and  c.dateFacture=:datefacture");
						
						query.setParameter("clientPF", clientpf.getId());
						query.setParameter("datefacture", datefacture);
					
					
				}else if(NumFacture.equals("") && clientpf!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.clientPF.id=:clientPF and c.depot.id=:iddepot");
					
						query.setParameter("clientPF", clientpf.getId());
						query.setParameter("iddepot", depot.getId());
				}else if(NumFacture.equals("") && clientpf==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureAvoirClientPF c where  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
				}
				
				
				
				return  query.list();
}
	
	
	
	public List<FactureAvoirClientPF> findByNumFcatureClientPFDateFactureDepotEtatRegle(String NumFacture,ClientPF clientpf ,java.util.Date datefacture,Depot depot) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && depot !=null)
		{
			
			 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.clientPF.id=:clientPF and  c.dateFacture=:datefacture and c.depot.id=:iddepot  order by c.dateFacture ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("clientPF", clientpf.getId());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			
			
			
		}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture  order by c.dateFacture ");
			query.setParameter("NumFacture", NumFacture);
			
		
		}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAvoirClientPF c where  c.clientPF.id=:clientPF  order by c.dateFacture ");
			
			query.setParameter("clientPF", clientpf.getId());
			
			
				}else if(NumFacture.equals("") && clientpf==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.dateFacture=:datefacture order by c.dateFacture");
					query.setParameter("datefacture", datefacture);
					
				
				}else if(NumFacture.equals("")  && clientpf==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where  c.depot.id=:iddepot  order by c.dateFacture");
				
					query.setParameter("iddepot", depot.getId());
					
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.clientPF.id=:clientPF  order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("clientPF", clientpf.getId());
						
						
						
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.clientPF.id=:clientPF and  c.dateFacture=:datefacture  order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("clientPF", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture  order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
						
				
					
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture  and c.depot.id=:iddepot  order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
						
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.clientPF.id=:clientPF  and c.depot.id=:iddepot  order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("clientPF", clientpf.getId());
						
						query.setParameter("iddepot", depot.getId());
						
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depot.id=:iddepot  order by c.dateFacture");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
				
					
				}else if(NumFacture.equals("") 
						&& clientpf!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where  c.clientPF.id=:clientPF and  c.dateFacture=:datefacture and c.depot.id=:iddepot  order by c.dateFacture ");
						
						query.setParameter("clientPF", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						
						
						
				}else if(NumFacture.equals("")
						&& clientpf!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAvoirClientPF c where  c.clientPF.id=:clientPF and  c.dateFacture=:datefacture  order by c.dateFacture");
						
						query.setParameter("clientPF", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						
					
					
				}else if(NumFacture.equals("") && clientpf!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureAvoirClientPF c where c.clientPF.id=:clientPF and c.depot.id=:iddepot  order by c.dateFacture ");
					
						query.setParameter("clientPF", clientpf.getId());
						query.setParameter("iddepot", depot.getId());
						
						
				}else if(NumFacture.equals("") && clientpf==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureAvoirClientPF c where  c.dateFacture=:datefacture and c.depot.id=:iddepot  order by c.dateFacture ");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
					
				}
				
				
				return  query.list();
}	
	
	public FactureAvoirClientPF findFactureAvoirPFByCodeTransfer(String code) {
		Query query= session.createQuery("select c from FactureAvoirClientPF c where codeTransfer=:code");
		query.setParameter("code", code);
		
		return (FactureAvoirClientPF) query.uniqueResult();
		
		}
	
	
	// Liste des factures de Type != BL	
	
	
		public List<FactureAvoirClientPF> findByNumFcatureClientDateFactureDepotTypeNonBL(String NumFacture,ClientPF clientpf,java.util.Date datefacturedebu,java.util.Date datefacturefin, Magasin magasin,String type) {
			
			Query query = null;
			
			if(!NumFacture.equals("") && clientpf!=null && datefacturedebu!=null && magasin !=null )
			{
				
				 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and c.type!=:type and  c.dateFacture between :datefacture and :datefacturefin and c.magasin.id=:magasin order by c.dateFacture ,c.numFacture  ");
				query.setParameter("NumFacture", NumFacture);
				query.setParameter("codeclient", clientpf.getId());
				query.setParameter("datefacture", datefacturedebu);
				query.setParameter("datefacturefin", datefacturefin);
				query.setParameter("magasin", magasin.getId());
				query.setParameter("type", type);
			
				
				
			}else if(!NumFacture.equals("") && clientpf==null && datefacturedebu==null && magasin==null)
			{
				 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.type!=:type   order by c.dateFacture,c.numFacture ");
				query.setParameter("NumFacture", NumFacture);
				query.setParameter("type", type);
				
			
			}else if(NumFacture.equals("") && clientpf!=null && datefacturedebu==null && magasin==null)
			{
				 query= session.createQuery("select c from FactureAvoirClientPF c where  c.clientPF.id=:codeclient and c.type!=:type  order by c.dateFacture,c.numFacture ");
				
				query.setParameter("codeclient", clientpf.getId());
				query.setParameter("type", type);
				
				
					}else if(NumFacture.equals("") && clientpf==null && datefacturedebu!=null && magasin==null)
					{
						 query= session.createQuery("select c from FactureAvoirClientPF c where c.dateFacture between :datefacture and :datefacturefin and c.type!=:type  order by c.dateFacture ,c.numFacture ");
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("type", type);
						
					
					}else if(NumFacture.equals("")  && clientpf==null && datefacturedebu==null	&& !magasin.equals(null))
					{
						 query= session.createQuery("select c from FactureAvoirClientPF c where  c.magasin.id=:magasin and c.type!=:type  order by c.dateFacture ,c.numFacture ");
					
						query.setParameter("magasin", magasin.getId());
						query.setParameter("type", type);
					
						
					}else if(!NumFacture.equals("")  && clientpf!=null && datefacturedebu==null	&& magasin==null)
					{
						 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and c.type!=:type  order by c.dateFacture ,c.numFacture ");
							query.setParameter("NumFacture", NumFacture);
							query.setParameter("codeclient", clientpf.getId());
							query.setParameter("type", type);
							
							
							
					}else if(!NumFacture.equals("")  && clientpf!=null && datefacturedebu!=null	&& magasin==null)
					{
						 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture between :datefacture and :datefacturefin and c.type!=:type  order by c.dateFacture ,c.numFacture ");
							query.setParameter("NumFacture", NumFacture);
							query.setParameter("codeclient", clientpf.getId());
							query.setParameter("datefacture", datefacturedebu);
							query.setParameter("datefacturefin", datefacturefin);
							query.setParameter("type", type);
							
							
					}else if(!NumFacture.equals("")  && clientpf==null && datefacturedebu!=null	&& magasin==null)
					{
						
						 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture  and  c.dateFacture between :datefacture and :datefacturefin and c.type!=:type  order by c.dateFacture ,c.numFacture ");
							query.setParameter("NumFacture", NumFacture);
							
							query.setParameter("datefacture", datefacturedebu);
							query.setParameter("datefacturefin", datefacturefin);
							query.setParameter("type", type);
							
					
						
					}else if(!NumFacture.equals("")  && clientpf==null && datefacturedebu==null	&& !magasin.equals(null))
					{
						 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture  and c.magasin.id=:magasin and c.type!=:type order by c.dateFacture ,c.numFacture ");
							query.setParameter("NumFacture", NumFacture);
							
							query.setParameter("magasin", magasin.getId());
							query.setParameter("type", type);
							
						
					}else if(!NumFacture.equals("")  && clientpf!=null && datefacturedebu==null	&& !magasin.equals(null))
					{
						 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient  and c.magasin.id=:magasin and c.type!=:type order by c.dateFacture ,c.numFacture ");
							query.setParameter("NumFacture", NumFacture);
							query.setParameter("codeclient", clientpf.getId());
							
							query.setParameter("magasin", magasin.getId());
							query.setParameter("type", type);
							
							
					}else if(!NumFacture.equals("")  && clientpf==null && datefacturedebu!=null	&& !magasin.equals(null))
					{

						 query= session.createQuery("select c from FactureAvoirClientPF c where c.numFacture=:NumFacture  and   c.dateFacture between :datefacture and :datefacturefin and c.magasin.id=:magasin and c.type!=:type order by c.dateFacture ,c.numFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("magasin", magasin.getId());
						query.setParameter("type", type);
						
						
					}else if(NumFacture.equals("") 
							&& clientpf!=null
							&& datefacturedebu!=null	
							&& magasin!=null)
					{
						 query= session.createQuery("select c from FactureAvoirClientPF c where  c.clientPF.id=:codeclient and   c.dateFacture between :datefacture and :datefacturefin and c.magasin.id=:magasin and c.type!=:type order by c.dateFacture,c.numFacture  ");
							
							query.setParameter("codeclient", clientpf.getId());
							query.setParameter("datefacture", datefacturedebu);
							query.setParameter("datefacturefin", datefacturefin);
							query.setParameter("magasin", magasin.getId());
							query.setParameter("type", type);
							
							
							
					}else if(NumFacture.equals("")
							&& clientpf!=null
							&& datefacturedebu!=null	&& magasin==null)
					{
						 query= session.createQuery("select c from FactureAvoirClientPF c where  c.clientPF.id=:codeclient and  c.dateFacture between :datefacture and :datefacturefin and c.type!=:type order by c.dateFacture ,c.numFacture ");
							
							query.setParameter("codeclient", clientpf.getId());
							query.setParameter("datefacture", datefacturedebu);
							query.setParameter("datefacturefin", datefacturefin);
							query.setParameter("type", type);
							
						
						
					}else if(NumFacture.equals("") && clientpf!=null  && datefacturedebu==null	&&  magasin!=null)
			
					{
						
						 query= session.createQuery("select c from FactureAvoirClientPF c where c.clientPF.id=:codeclient and c.magasin.id=:magasin and c.type!=:type order by c.dateFacture ,c.numFacture ");
						
							query.setParameter("codeclient", clientpf.getId());
							query.setParameter("magasin", magasin.getId());
							query.setParameter("type", type);
							
							
					}else if(NumFacture.equals("") && clientpf==null  && datefacturedebu!=null	&&  magasin!=null)
					{
						
						 query= session.createQuery("select c from FactureAvoirClientPF c where  c.dateFacture between :datefacture and :datefacturefin and c.magasin.id=:magasin and c.type!=:type order by c.dateFacture ,c.numFacture ");
							
							query.setParameter("datefacture", datefacturedebu);
							query.setParameter("datefacturefin", datefacturefin);
							query.setParameter("magasin", magasin.getId());
							query.setParameter("type", type);
							
					}
					
					
					
					return  query.list();
	}
	


}
