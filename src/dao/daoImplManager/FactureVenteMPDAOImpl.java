package dao.daoImplManager;

import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.FactureVenteMPDAO;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FactureVenteMP;
import dao.entity.SousFamilleArticlePF;

public class FactureVenteMPDAOImpl implements FactureVenteMPDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(FactureVenteMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public FactureVenteMP edit(FactureVenteMP e) {
		
	session.beginTransaction();
	FactureVenteMP p= (FactureVenteMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FactureVenteMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FactureVenteMP> findAll() {
		return session.createQuery("select c from FactureVenteMP c").list();
		}

	public FactureVenteMP findById(int id) {
		return (FactureVenteMP)session.get(FactureVenteMP.class, id);
		}
	
	public FactureVenteMP findFactureVenteMPByEtat(String etat) {
		Query query= session.createQuery("select c from FactureVenteMP c where etat=:etat");
		query.setParameter("etat", etat);
		
		return (FactureVenteMP) query.uniqueResult();
		
		}
	
	public FactureVenteMP findFactureVenteMPByNumBL(String NumBL) {
		Query query= session.createQuery("select c from FactureVenteMP c where NumBl=:NumBL");
		query.setParameter("NumBL", NumBL);
		
		return (FactureVenteMP) query.uniqueResult();
		
		}
	
	public FactureVenteMP findFactureVenteMPByNumBLByDepot(String NumBL, Depot depot) {
		Query query= session.createQuery("select c from FactureVenteMP c where NumBl=:NumBL and c.depotSource.id=:depot");
		query.setParameter("NumBL", NumBL);
		query.setParameter("depot", depot.getId());
		return (FactureVenteMP) query.uniqueResult();
		
		}
	
	@Override
	public List<FactureVenteMP> findByNumFacture(String NumFacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture");
				query.setParameter("NumFacture", NumFacture);
				
				
				return  query.list();
}
	
	 
	public List<FactureVenteMP> findByNumFacturByDepote(String NumFacture, Depot depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.depotSource.id=:depot");
				query.setParameter("NumFacture", NumFacture);
				query.setParameter("depot", depot.getId());
				
				return  query.list();
}
	
	@Override
	public List<FactureVenteMP> findByClient(String codeclient) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FacturePF c where c.client.code=:codeclient");
				query.setParameter("codeclient", codeclient);
				
				
				return  query.list();
}
	
	
	//liste des BL de type facture ou Facturé par client
	@Override
	public List<FactureVenteMP> findFactureByClient(String codeclient) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureVenteMP c where c.clientPF.code=:codeclient and c.type !=:BL");
				query.setParameter("codeclient", codeclient);
				query.setParameter("BL", "BL");
				
				return  query.list();
}
	
	@Override
	public List<FactureVenteMP> findByDate(java.util.Date datefacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureVenteMP c where c.dateFacture=:datefacture");
				query.setParameter("datefacture", datefacture);
				
				
				return  query.list();
}
	
	//liste des BL de type facture entre deux date
	@Override
	public List<FactureVenteMP> findFactureEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureVenteMP c where c.dateFacture between :dateDebut and :datedeFin and c.type !=:BL");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("BL", "BL");
				return  query.list();
}
	
	
	@Override
	public List<FactureVenteMP> findByDepot(int iddepot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureVenteMP c where c.depotSource.id=:iddepot");
				query.setParameter("iddepot", iddepot);
				
				
				return  query.list();
}

	
	@Override
	public List<FactureVenteMP> findByNumFactureClientDateFactureDepot(String NumFacture,Client client,java.util.Date datefacture,Depot depot) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && client!=null && datefacture!=null && depot !=null)
		{
			
			 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.client.code=:codeclient and  c.dateFacture=:datefacture and c.depotSource.id=:iddepot");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codeclient", client.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			
			
		}else if(!NumFacture.equals("") && client==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture ");
			query.setParameter("NumFacture", NumFacture);
		
		}else if(NumFacture.equals("") && client!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureVenteMP c where  c.client.code=:codeclient ");
			
			query.setParameter("codeclient", client.getCode());
			
				}else if(NumFacture.equals("") && client==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.dateFacture=:datefacture");
					query.setParameter("datefacture", datefacture);
				
				}else if(NumFacture.equals("")  && client==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureVenteMP c where  c.depotSource.id=:iddepot");
				
					query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && client!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.client.code=:codeclient ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", client.getCode());
						
						
				}else if(!NumFacture.equals("")  && client!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.client.code=:codeclient and  c.dateFacture=:datefacture");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
						
				}else if(!NumFacture.equals("")  && client==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
				
					
				}else if(!NumFacture.equals("")  && client==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture  and c.depotSource.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
					
				}else if(!NumFacture.equals("")  && client!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.client.code=:codeclient  and c.depotSource.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", client.getCode());
						
						query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && client==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depotSource.id=:iddepot");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					
				}else if(NumFacture.equals("") 
						&& client!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where  c.client.code=:codeclient and  c.dateFacture=:datefacture and c.depotSource.id=:iddepot");
						
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						
						
				}else if(NumFacture.equals("")
						&& client!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where  c.client.code=:codeclient and  c.dateFacture=:datefacture");
						
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
					
					
				}else if(NumFacture.equals("") && client!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureVenteMP c where c.client.code=:codeclient and c.depotSource.id=:iddepot");
					
						query.setParameter("codeclient", client.getCode());
						query.setParameter("iddepot", depot.getId());
				}else if(NumFacture.equals("") && client==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureVenteMP c where  c.dateFacture=:datefacture and c.depotSource.id=:iddepot");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
				}
				
				
				
				return  query.list();
}
	
	
	
	@Override
	public List<FactureVenteMP> findByNumFactureClientDateFactureDepotEtatRegle(String NumBl,Client client,java.util.Date datefacture,Depot depot,String etat,Depot depotSource) {
		
		Query query = null;
		
		if(!NumBl.equals("") && client!=null && datefacture!=null && depot !=null )
		{
			
			 query= session.createQuery("select c from FactureVenteMP c where c.NumBl=:NumFacture and c.client.code=:codeclient and  c.dateFacture=:datefacture and c.depotDestination.id=:iddepot and c.depotSource.id=:iddepotSource");
			query.setParameter("NumFacture", NumBl);
			query.setParameter("codeclient", client.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			query.setParameter("iddepotSource", depotSource.getId());
			//query.setParameter("etat", etat);
			
			
			
		}else if(!NumBl.equals("") && client==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureVenteMP c where c.NumBl=:NumFacture  and  c.depotSource.id=:iddepotSource ");
			query.setParameter("NumFacture", NumBl);
			//query.setParameter("etat", etat);
			
			query.setParameter("iddepotSource", depotSource.getId());
		
		}else if(NumBl.equals("") && client!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureVenteMP c where  c.client.code=:codeclient  and c.depotSource.id=:iddepotSource ");
			
			query.setParameter("codeclient", client.getCode());
		//	query.setParameter("etat", etat);
			
			query.setParameter("iddepotSource", depotSource.getId());
			
				}else if(NumBl.equals("") && client==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.dateFacture=:datefacture and  c.depotSource.id=:iddepotSource");
					query.setParameter("datefacture", datefacture);
					//query.setParameter("etat", etat);
					
					query.setParameter("iddepotSource", depotSource.getId());
				
				}else if(NumBl.equals("")  && client==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureVenteMP c where  c.depotDestination.id=:iddepot and c.depotSource.id=:iddepotSource ");
				
					query.setParameter("iddepot", depot.getId());
					//query.setParameter("etat", etat);
					
					query.setParameter("iddepotSource", depotSource.getId());
					
				}else if(!NumBl.equals("")  && client!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumBl=:NumFacture and c.client.code=:codeclient  and  c.depotSource.id=:iddepotSource ");
						query.setParameter("NumFacture", NumBl);
						query.setParameter("codeclient", client.getCode());
						//query.setParameter("etat", etat);
						
						query.setParameter("iddepotSource", depotSource.getId());
						
						
				}else if(!NumBl.equals("")  && client!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumBl=:NumFacture and c.client.code=:codeclient and  c.dateFacture=:datefacture and  c.depotSource.id=:iddepotSource ");
						query.setParameter("NumFacture", NumBl);
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
						//query.setParameter("etat", etat);
					
						query.setParameter("iddepotSource", depotSource.getId());
						
				}else if(!NumBl.equals("")  && client==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureVenteMP c where c.NumBl=:NumFacture  and  c.dateFacture=:datefacture and  c.depotSource.id=:iddepotSource");
						query.setParameter("NumFacture", NumBl);
						
						query.setParameter("datefacture", datefacture);
						//query.setParameter("etat", etat);
						
						query.setParameter("iddepotSource", depotSource.getId());
				
					
				}else if(!NumBl.equals("")  && client==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumBl=:NumFacture  and c.depotDestination.id=:iddepot and c.depotSource.id=:iddepotSource  ");
						query.setParameter("NumFacture", NumBl);
						
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("etat", etat);
					
						query.setParameter("iddepotSource", depotSource.getId());
					
				}else if(!NumBl.equals("")  && client!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumBl=:NumFacture and c.client.code=:codeclient  and c.depotDestination.id=:iddepot and c.depotSource.id=:iddepotSource ");
						query.setParameter("NumFacture", NumBl);
						query.setParameter("codeclient", client.getCode());
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						query.setParameter("BL", "BL");
						query.setParameter("iddepotSource", depotSource.getId());
						
				}else if(!NumBl.equals("")  && client==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureVenteMP c where c.NumBl=:NumFacture  and  c.dateFacture=:datefacture and c.depotDestination.id=:iddepot and c.depotSource.id=:iddepotSource ");
					query.setParameter("NumFacture", NumBl);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					//query.setParameter("etat", etat);
					
					query.setParameter("iddepotSource", depotSource.getId());
					
				}else if(NumBl.equals("") 
						&& client!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where  c.client.code=:codeclient and  c.dateFacture=:datefacture and c.depotDestination.id=:iddepot and c.depotSource.id=:iddepotSource ");
						
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("etat", etat);
						
						query.setParameter("iddepotSource", depotSource.getId());
						
						
				}else if(NumBl.equals("")
						&& client!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where  c.client.code=:codeclient and  c.dateFacture=:datefacture  and c.depotSource.id=:iddepotSource ");
						
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
						//query.setParameter("etat", etat);
						
						query.setParameter("iddepotSource", depotSource.getId());
					
					
				}else if(NumBl.equals("") && client!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureVenteMP c where c.client.code=:codeclient and c.depotDestination.id=:iddepot and c.depotSource.id=:iddepotSource ");
					
						query.setParameter("codeclient", client.getCode());
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("etat", etat);
						
						query.setParameter("iddepotSource", depotSource.getId());
						
				}else if(NumBl.equals("") && client==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureVenteMP c where  c.dateFacture=:datefacture and c.depotDestination.id=:iddepot and c.depotSource.id=:iddepotSource ");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("etat", etat);
						
						query.setParameter("iddepotSource", depotSource.getId());
				}
				
				
				
				return  query.list();
}	
	
	/*
	@Override
	public List<FacturePF> findByNumFcatureClientDateFactureFournisseurEtatRegle(String NumFacture,Client client,java.util.Date datefacture,Client fournisseur,String etat,Depot depot) {
		
		Query query = null;
		
		
		
		if(!NumFacture.equals("") && client!=null && datefacture!=null && fournisseur !=null && depot==null )
		{
			
			 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.client.code=:codeclient and  c.dateFacture=:datefacture and c.Fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codeclient", client.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("fournisseur", fournisseur.getId());
			query.setParameter("etat", etat);
			query.setParameter("Facture", "Facture");
		
			
		}else if(!NumFacture.equals("") && client==null && datefacture==null && fournisseur==null && depot==null)
		{
			 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.etat=:etat  and c.type=:Facture ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("etat", etat);
			query.setParameter("Facture", "Facture");
			
		}else if(NumFacture.equals("") && client!=null && datefacture==null && fournisseur==null && depot==null )
		{
			 query= session.createQuery("select c from FactureVenteMP c where  c.client.code=:codeclient and c.etat=:etat  and c.type=:Facture ");
			
			query.setParameter("codeclient", client.getCode());
			query.setParameter("etat", etat);
			query.setParameter("Facture", "Facture");
			
				}else if(NumFacture.equals("") && client==null && datefacture!=null && fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.dateFacture=:datefacture and c.etat=:etat  and c.type=:Facture");
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
				
				}else if(NumFacture.equals("")  && client==null && datefacture==null	&& fournisseur!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where  c.Fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture");
				
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					
					
				}else if(NumFacture.equals("")  && clientpf==null && datefacture==null	&& fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.etat=:etat  and c.type=:Facture");
				
					
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					
					
				}
				
				else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture and c.ClientPF.code=:codeclient and c.etat=:etat  and c.type=:Facture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getCode());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
						
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture!=null	&& fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture and c.ClientPF.code=:codeclient and  c.dateFacture=:datefacture and c.etat=:etat  and c.type=:Facture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& fournisseur==null && depot==null)
				{
					
					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture and c.etat=:etat  and c.type=:Facture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
				
					
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture==null	&& fournisseur!=null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture  and c.Fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& fournisseur!=null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture and c.ClientPF.code=:codeclient  and c.Fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getCode());
						
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& fournisseur!=null && depot==null)
				{

					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture and c.Fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture ");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					
				}else if(NumFacture.equals("") 
						&& clientpf!=null
						&& datefacture!=null	
						&& fournisseur!=null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where  c.ClientPF.code=:codeclient and  c.dateFacture=:datefacture and c.Fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture ");
						
						query.setParameter("codeclient", clientpf.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
						
				}else if(NumFacture.equals("")
						&& clientpf!=null
						&& datefacture!=null	&& fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where  c.ClientPF.code=:codeclient and  c.dateFacture=:datefacture and c.etat=:etat  and c.type=:Facture ");
						
						query.setParameter("codeclient", clientpf.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
					
					
				}else if(NumFacture.equals("") && clientpf!=null  && datefacture==null	&&  fournisseur!=null && depot==null)
		
				{
					
					 query= session.createQuery("select c from FacturePF c where c.ClientPF.code=:codeclient and c.Fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture ");
					
						query.setParameter("codeclient", clientpf.getCode());
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
				}else if(NumFacture.equals("") && clientpf==null  && datefacture!=null	&&  fournisseur!=null && depot==null)
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.dateFacture=:datefacture and c.Fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture ");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
				}else if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture and c.ClientPF.code=:codeclient and  c.dateFacture=:datefacture and c.Fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("codeclient", clientpf.getCode());
					query.setParameter("datefacture", datefacture);
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf==null && datefacture == null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
						
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture and c.ClientPF.code=:codeclient  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("codeclient", clientpf.getCode());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture and  c.dateFacture=:datefacture  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture and c.Fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture and c.ClientPF.code=:codeclient  and c.Fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("codeclient", clientpf.getCode());
				
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture and c.ClientPF.code=:codeclient and  c.dateFacture=:datefacture  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("codeclient", clientpf.getCode());
					query.setParameter("datefacture", datefacture);
					
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.NumFacture=:NumFacture and   c.dateFacture=:datefacture and c.Fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("datefacture", datefacture);
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.ClientPF.code=:codeclient and  c.dateFacture=:datefacture  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					
					query.setParameter("codeclient", clientpf.getCode());
					query.setParameter("datefacture", datefacture);
					
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.ClientPF.code=:codeclient  and c.Fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					
					query.setParameter("codeclient", clientpf.getCode());
					
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.ClientPF.code=:codeclient  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					
					query.setParameter("codeclient", clientpf.getCode());
					
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.ClientPF.code=:codeclient and  c.dateFacture=:datefacture and c.Fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					
					 query.setParameter("codeclient", clientpf.getCode());
					query.setParameter("datefacture", datefacture);
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}
				
				
				else if(NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.dateFacture=:datefacture  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.dateFacture=:datefacture and c.Fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}
				
				
				else if(NumFacture.equals("") && clientpf==null && datefacture==null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.Fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot");
					
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}
				
				
				
				return  query.list();
}	*/
	
	// listes des BL de type Facturé et Reglé
	
	@Override
	public List<FactureVenteMP> findByNumFactureClientDateFactureDepotEtatRegleFacturé(String NumFacture,Client client,java.util.Date datefacture,Depot depot,String etat) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && client!=null && datefacture!=null && depot !=null )
		{
			
			 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.client.code=:codeclient and  c.dateFacture=:datefacture and c.depotSource.id=:iddepot and c.etat=:etat and c.type=:Facturé");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codeclient", client.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			query.setParameter("etat", etat);
			query.setParameter("Facturé", "Facturé");
			
			
		}else if(!NumFacture.equals("") && client==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.etat=:etat and c.type=:Facturé ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("etat", etat);
			query.setParameter("Facturé", "Facturé");
		
		}else if(NumFacture.equals("") && client!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureVenteMP c where  c.client.code=:codeclient and c.etat=:etat and c.type=:Facturé");
			
			query.setParameter("codeclient", client.getCode());
			query.setParameter("etat", etat);
			query.setParameter("Facturé", "Facturé");
			
				}else if(NumFacture.equals("") && client==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.dateFacture=:datefacture and c.etat=:etat and c.type=:Facturé");
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
					query.setParameter("Facturé", "Facturé");
				
				}else if(NumFacture.equals("")  && client==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureVenteMP c where  c.depotSource.id=:iddepot and c.etat=:etat and c.type=:Facturé");
				
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facturé", "Facturé");
					
				}else if(!NumFacture.equals("")  && client!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.client.code=:codeclient and c.etat=:etat and c.type=:Facturé ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", client.getCode());
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
						
						
				}else if(!NumFacture.equals("")  && client!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.client.code=:codeclient and  c.dateFacture=:datefacture and c.etat=:etat and c.type=:Facturé ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
						
				}else if(!NumFacture.equals("")  && client==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture and c.etat=:etat and c.type=:Facturé");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
				
					
				}else if(!NumFacture.equals("")  && client==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture  and c.depotSource.id=:iddepot and c.etat=:etat and c.type=:Facturé ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
					
				}else if(!NumFacture.equals("")  && client!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.client.code=:codeclient  and c.depotSource.id=:iddepot and c.etat=:etat and c.type=:Facturé ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", client.getCode());
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
						
				}else if(!NumFacture.equals("")  && client==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depotSource.id=:iddepot and c.etat=:etat and c.type=:Facturé");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facturé", "Facturé");
					
				}else if(NumFacture.equals("") 
						&& client!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where  c.client.code=:codeclient and  c.dateFacture=:datefacture and c.depotSource.id=:iddepot and c.etat=:etat and c.type=:Facturé ");
						
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
						
						
				}else if(NumFacture.equals("")
						&& client!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where  c.cient.code=:codeclient and  c.dateFacture=:datefacture and c.etat=:etat and c.type=:Facturé");
						
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
					
					
				}else if(NumFacture.equals("") && client!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureVenteMP c where c.client.code=:codeclient and c.depotSource.id=:iddepot and c.etat=:etat and c.type=:Facturé");
					
						query.setParameter("codeclient", client.getCode());
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
						
				}else if(NumFacture.equals("") && client==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureVenteMP c where  c.dateFacture=:datefacture and c.depotSource.id=:iddepot and c.etat=:etat and c.type=:Facturé ");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
				}
				
				
				
				return  query.list();
}
	
	
	///liste des BL de type Facture
	
	public List<FactureVenteMP> findByNumFactureClientDateFactureDepotEtatRegleFacture(String NumFacture,Client client,java.util.Date datefacture,Depot depot,Depot depotDestination) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && client!=null && datefacture!=null && depotDestination !=null )
		{
			
			 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.client.code=:codeclient and  c.dateFacture=:datefacture and c.depotSource.id=:iddepotSource and c.depotDestination.id=:iddepotDestination order by c.dateFacture");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codeclient", client.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepotSource", depot.getId());
			query.setParameter("iddepotDestination", depotDestination.getId());
			//query.setParameter("Facture", "Facture");
			
			
		}else if(!NumFacture.equals("") && client==null && datefacture==null && depotDestination==null)
		{
			 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.depotSource.id=:iddepotSource order by c.dateFacture ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("iddepotSource", depot.getId());
		
			//query.setParameter("Facture", "Facture");
		
		}else if(NumFacture.equals("") && client!=null && datefacture==null && depotDestination==null)
		{
			 query= session.createQuery("select c from FactureVenteMP c where  c.client.code=:codeclient and c.depotSource.id=:iddepotSource order by c.dateFacture ");
			
			query.setParameter("codeclient", client.getCode());
			query.setParameter("iddepotSource", depot.getId());
			//query.setParameter("Facture", "Facture");
			
				}else if(NumFacture.equals("") && client==null && datefacture!=null && depotDestination==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.dateFacture=:datefacture and c.depotSource.id=:iddepotSource order by c.dateFacture ");
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepotSource", depot.getId());
					//query.setParameter("Facture", "Facture");
				
				}else if(NumFacture.equals("")  && client==null && datefacture==null	&& !depotDestination.equals(null))
				{
					 query= session.createQuery("select c from FactureVenteMP c where  c.depotSource.id=:iddepotSource  and c.depotDestination.id=:iddepotDestination order by c.dateFacture ");
				
					 query.setParameter("iddepotSource", depot.getId());
					 query.setParameter("iddepotDestination", depotDestination.getId());
					//query.setParameter("Facture", "Facture");
					
				}else if(!NumFacture.equals("")  && client!=null && datefacture==null	&& depotDestination==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.client.code=:codeclient and c.depotSource.id=:iddepotSource order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						 query.setParameter("iddepotSource", depot.getId());
						query.setParameter("codeclient", client.getCode());
						//query.setParameter("Facture", "Facture");
						
						
				}else if(!NumFacture.equals("")  && client!=null && datefacture!=null	&& depotDestination==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.client.code=:codeclient and c.dateFacture=:datefacture and c.depotSource.id=:iddepotSource order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepotSource", depot.getId());
						//query.setParameter("Facture", "Facture");
						
				}else if(!NumFacture.equals("")  && client==null && datefacture!=null	&& depotDestination==null)
				{
					
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depotSource.id=:iddepotSource order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("iddepotSource", depot.getId());
						query.setParameter("datefacture", datefacture);
						//query.setParameter("Facture", "Facture");
				
					
				}else if(!NumFacture.equals("")  && client==null && datefacture==null	&& !depotDestination.equals(null))
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture  and c.depotSource.id=:iddepotSource  and c.depotDestination.id=:iddepotDestination order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						 query.setParameter("iddepotDestination", depotDestination.getId());
						query.setParameter("iddepotSource", depot.getId());
						//query.setParameter("Facture", "Facture");
					
				}else if(!NumFacture.equals("")  && client!=null && datefacture==null	&& !depotDestination.equals(null))
				{
					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture and c.client.code=:codeclient  and c.depotSource.id=:iddepotSource and c.depotDestination.id=:iddepotDestination order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", client.getCode());
						 query.setParameter("iddepotDestination", depotDestination.getId());
						query.setParameter("iddepotSource", depot.getId());
						//query.setParameter("Facture", "Facture");
						
				}else if(!NumFacture.equals("")  && client==null && datefacture!=null	&& !depotDestination.equals(null))
				{

					 query= session.createQuery("select c from FactureVenteMP c where c.NumFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depotSource.id=:iddepotSource and c.depotDestination.id=:iddepotDestination order by c.dateFacture ");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					 query.setParameter("iddepotDestination", depotDestination.getId());
						query.setParameter("iddepotSource", depot.getId());
					//query.setParameter("Facture", "Facture");
					
				}else if(NumFacture.equals("") 
						&& client!=null
						&& datefacture!=null	
						&& depotDestination!=null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where  c.client.code=:codeclient and  c.dateFacture=:datefacture and c.depotSource.id=:iddepotSource and c.depotDestination.id=:iddepotDestination order by c.dateFacture ");
						
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
						 query.setParameter("iddepotDestination", depotDestination.getId());
							query.setParameter("iddepotSource", depot.getId());
					//	query.setParameter("Facture", "Facture");
						
						
				}else if(NumFacture.equals("")
						&& client!=null
						&& datefacture!=null	&& depotDestination==null)
				{
					 query= session.createQuery("select c from FactureVenteMP c where  c.client.code=:codeclient and  c.dateFacture=:datefacture and c.depotSource.id=:iddepotSource order by c.dateFacture ");
					 query.setParameter("iddepotSource", depot.getId());
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
						//query.setParameter("Facture", "Facture");
					
					
				}else if(NumFacture.equals("") && client!=null  && datefacture==null	&&  depotDestination!=null)
		
				{
					
					 query= session.createQuery("select c from FactureVenteMP c where c.client.code=:codeclient and c.depotSource.id=:iddepotSource and c.depotDestination.id=:iddepotDestination order by c.dateFacture ");
					
						query.setParameter("codeclient", client.getCode());
						 query.setParameter("iddepotDestination", depotDestination.getId());
							query.setParameter("iddepotSource", depot.getId());
						//query.setParameter("Facture", "Facture");
						
				}else if(NumFacture.equals("") && client==null  && datefacture!=null	&&  depotDestination!=null)
				{
					
					    query= session.createQuery("select c from FactureVenteMP c where  c.dateFacture=:datefacture and c.depotSource.id=:iddepotSource and c.depotDestination.id=:iddepotDestination order by c.dateFacture");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepotDestination", depotDestination.getId());
						query.setParameter("iddepotSource", depot.getId());
						//query.setParameter("Facture", "Facture");
				}
				
				
				
				return  query.list();
}
		
	public FactureVenteMP findFacturePFByCodeTransfer(String code) {
		Query query= session.createQuery("select c from FactureVenteMP c where codeTransfer=:code");
		query.setParameter("code", code);
		
		return (FactureVenteMP) query.uniqueResult();
		
		}
	
	
	
	


}
