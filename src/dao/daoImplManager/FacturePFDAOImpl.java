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
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FactureVenteMP;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public class FacturePFDAOImpl implements FacturePFDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(FacturePF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
		
	}

	public FacturePF edit(FacturePF e) {
		
	session.beginTransaction();
	FacturePF p= (FacturePF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FacturePF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FacturePF> findAll() {
		return session.createQuery("select c from FacturePF c").list();
		}
	
	

	public FacturePF findById(int id) {
		return (FacturePF)session.get(FacturePF.class, id);
		}
	
	public FacturePF findFacturePFByEtat(String etat) {
		Query query= session.createQuery("select c from FacturePF c where etat=:etat");
		query.setParameter("etat", etat);
		
		return (FacturePF) query.uniqueResult();
		
		}
	
	
	public FacturePF findFactureVentePFByNumBL(String NumBL) {
		Query query= session.createQuery("select c from FacturePF c where numBl=:NumBL and c.magasin.id=7");
		query.setParameter("NumBL", NumBL);
		
		return (FacturePF) query.uniqueResult();
		
		}
	
	public FacturePF findFactureVentePFByNumFacture(String numfacture) {
		Query query= session.createQuery("select c from FacturePF c where c.numFacture=:numfacture and c.magasin.id=7");
		query.setParameter("numfacture", numfacture);
		
		return (FacturePF) query.uniqueResult();
		
		}
	
	public FacturePF findFactureVentePFByNumBLByDepot(String NumBL, Depot depot) {
		Query query= session.createQuery("select c from FacturePF c where numBl=:NumBL and c.depot.id=:depot");
		query.setParameter("NumBL", NumBL);
		query.setParameter("depot", depot.getId());
		return (FacturePF) query.uniqueResult();
		
		}
	
	
	
	@Override
	public List<FacturePF> findByNumFacture(String NumFacture, Depot depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.depot.id=:depot");
				query.setParameter("NumFacture", NumFacture);
				query.setParameter("depot", depot.getId());
				
				
				return  query.list();
}
	
	
	@Override
	public List<FacturePF> findByNumFactureByMagasin(String NumFacture, int  magasin) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.magasin.id=:magasin");
				query.setParameter("NumFacture", NumFacture);
				query.setParameter("magasin", magasin);
				
				
				return  query.list();
}
	
	
	
	
	@Override
	public List<FacturePF> findByClient(String codeclient) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FacturePF c where c.clientPF.code=:codeclient");
				query.setParameter("codeclient", codeclient);
				
				
				return  query.list();
}
	
	
	//liste des BL de type facture ou Facturé par client
	@Override
	public List<FacturePF> findFactureByClient(String codeclient) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FacturePF c where c.clientPF.code=:codeclient and c.type !=:BL");
				query.setParameter("codeclient", codeclient);
				query.setParameter("BL", "BL");
				
				return  query.list();
}
	
	
	//liste des BL de type facture ou Facturé par client
	@Override
	public List<FacturePF> findBLByClient(String codeclient) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FacturePF c where c.clientPF.code=:codeclient and c.type =:BL");
				query.setParameter("codeclient", codeclient);
				query.setParameter("BL", "BL");
				
				return  query.list();
}
	
	@Override
	public List<FacturePF> findByDate(java.util.Date datefacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FacturePF c where c.dateFacture=:datefacture");
				query.setParameter("datefacture", datefacture);
				
				
				return  query.list();
}
	
	//liste des BL de type facture entre deux date
	@Override
	public List<FacturePF> findFactureEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Depot depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FacturePF c where c.dateFacture between :dateDebut and :datedeFin and c.type !=:BL  and c.depot.id=:depot");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("depot", depot.getId());
				query.setParameter("BL", "BL");
				return  query.list();
}
	
	
	
	@Override
	public List<FacturePF> findFacturePFByRequete(String req) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FacturePF c where "+req +" order by c.dateFacture");
				return  query.list();
}
	

		
	@Override
	public List<FacturePF> findByDepot(int iddepot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FacturePF c where c.depot.id=:iddepot");
				query.setParameter("iddepot", iddepot);
				
				
				return  query.list();
}

	
	@Override
	public List<FacturePF> findByNumFcatureClientDateFactureDepot(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && depot !=null)
		{
			
			 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			
			
		}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture ");
			query.setParameter("NumFacture", NumFacture);
		
		}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient ");
			
			query.setParameter("codeclient", clientpf.getId());
			
				}else if(NumFacture.equals("") && clientpf==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.dateFacture=:datefacture");
					query.setParameter("datefacture", datefacture);
				
				}else if(NumFacture.equals("")  && clientpf==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where  c.depot.id=:iddepot");
				
					query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						
						
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture=:datefacture");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
				
					
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  and c.depot.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient  and c.depot.id=:iddepot");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						
						query.setParameter("iddepot", depot.getId());
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					
				}else if(NumFacture.equals("") 
						&& clientpf!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						
						
				}else if(NumFacture.equals("")
						&& clientpf!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and  c.dateFacture=:datefacture");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
					
					
				}else if(NumFacture.equals("") && clientpf!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FacturePF c where c.clientPF.id=:codeclient and c.depot.id=:iddepot");
					
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("iddepot", depot.getId());
				}else if(NumFacture.equals("") && clientpf==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
				}
				
				
				
				return  query.list();
}
	
	
	
	@Override
	public List<FacturePF> findByNumFcatureClientDateFactureDepotEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacturedebu,java.util.Date datefacturefin, Depot depot,String etat , String typeBL) {
		
		Query query = null;
		
		
		
		String req="";
		if(typeBL!=null)
		{
			
			if(!typeBL.equals(""))
			{
				req=req+" and c.typeBL.type='"+typeBL+"' ";
			}
			
		}
		
		if(!NumFacture.equals("") && clientpf!=null && datefacturedebu!=null && depot !=null )
		{
			
			
			
			 query= session.createQuery("select c from FacturePF c where c.numBl=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture between :datefacture and :datefacturefin and c.depot.id=:iddepot and c.etat=:etat "+req+"  order by c.dateFacture ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("datefacture", datefacturedebu);
			query.setParameter("datefacturefin", datefacturefin);
			query.setParameter("iddepot", depot.getId());
			query.setParameter("etat", etat);
		
			
			
		}else if(!NumFacture.equals("") && clientpf==null && datefacturedebu==null && depot==null)
		{
			 query= session.createQuery("select c from FacturePF c where c.numBl=:NumFacture and c.etat=:etat "+req+" order by c.dateFacture");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("etat", etat);
			
		
		}else if(NumFacture.equals("") && clientpf!=null && datefacturedebu==null && depot==null)
		{
			 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and c.etat=:etat "+req+" order by c.dateFacture");
			
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("etat", etat);
			
			
				}else if(NumFacture.equals("") && clientpf==null && datefacturedebu!=null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.dateFacture between :datefacture and :datefacturefin and c.etat=:etat "+req+" order by c.dateFacture ");
					query.setParameter("datefacture", datefacturedebu);
					query.setParameter("datefacturefin", datefacturefin);
					query.setParameter("etat", etat);
					
				
				}else if(NumFacture.equals("")  && clientpf==null && datefacturedebu==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where  c.depot.id=:iddepot and c.etat=:etat "+req+" order by c.dateFacture ");
				
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
				
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacturedebu==null	&& depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numBl=:NumFacture and c.clientPF.id=:codeclient and c.etat=:etat "+req+" order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("etat", etat);
						
						
						
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacturedebu!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numBl=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture between :datefacture and :datefacturefin and c.etat=:etat "+req+" order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("etat", etat);
						
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacturedebu!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numBl=:NumFacture  and  c.dateFacture between :datefacture and :datefacturefin and c.etat=:etat "+req+" order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("etat", etat);
						
				
					
				}else if(!NumFacture.equals("")  && clientpf==null && datefacturedebu==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where c.numBl=:NumFacture  and c.depot.id=:iddepot and c.etat=:etat "+req+" order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacturedebu==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where c.numBl=:NumFacture and c.clientPF.id=:codeclient  and c.depot.id=:iddepot and c.etat=:etat "+req+" order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacturedebu!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FacturePF c where c.numBl=:NumFacture  and   c.dateFacture between :datefacture and :datefacturefin and c.depot.id=:iddepot and c.etat=:etat  "+req+" order by c.dateFacture ");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacturedebu);
					query.setParameter("datefacturefin", datefacturefin);
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					
					
				}else if(NumFacture.equals("") 
						&& clientpf!=null
						&& datefacturedebu!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and   c.dateFacture between :datefacture and :datefacturefin and c.depot.id=:iddepot and c.etat=:etat "+req+" order by c.dateFacture ");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
						
						
				}else if(NumFacture.equals("")
						&& clientpf!=null
						&& datefacturedebu!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and  c.dateFacture between :datefacture and :datefacturefin and c.etat=:etat  "+req+" order by c.dateFacture ");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("etat", etat);
						
					
					
				}else if(NumFacture.equals("") && clientpf!=null  && datefacturedebu==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FacturePF c where c.clientPF.id=:codeclient and c.depot.id=:iddepot and c.etat=:etat "+req+" order by c.dateFacture ");
					
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
						
				}else if(NumFacture.equals("") && clientpf==null  && datefacturedebu!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.dateFacture between :datefacture and :datefacturefin and c.depot.id=:iddepot and c.etat=:etat "+req+" order by c.dateFacture ");
						
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
				}
				
				
				
				return  query.list();
}	
	
	
	@Override
	public List<FacturePF> findByNumFcatureClientDateFactureFournisseurEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Client fournisseur,String etat,Depot depot) {
		
		Query query = null;
		
		
		
		if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur !=null && depot==null )
		{
			
			 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.Fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture  order by  (REPLACE(c.numFacture,'/','')+0)");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("datefacture", datefacture);
			query.setParameter("fournisseur", fournisseur.getId());
			query.setParameter("etat", etat);
			query.setParameter("Facture", "Facture");
		
			
		}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && fournisseur==null && depot==null)
		{
			 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("etat", etat);
			query.setParameter("Facture", "Facture");
			
		}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur==null && depot==null )
		{
			 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
			
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("etat", etat);
			query.setParameter("Facture", "Facture");
			
				}else if(NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.dateFacture=:datefacture and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
				
				}else if(NumFacture.equals("")  && clientpf==null && datefacture==null	&& fournisseur!=null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where  c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
				
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					
					
				}else if(NumFacture.equals("")  && clientpf==null && datefacture==null	&& fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");				
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					
					
				}
				
				else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
						
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture!=null	&& fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.etat=:etat  and c.type=:Facture  order by  (REPLACE(c.numFacture,'/','')+0)");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& fournisseur==null && depot==null)
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
				
					
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture==null	&& fournisseur!=null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  and c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& fournisseur!=null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient  and c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& fournisseur!=null && depot==null)
				{

					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
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
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
						
				}else if(NumFacture.equals("")
						&& clientpf!=null
						&& datefacture!=null	&& fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
					
					
				}else if(NumFacture.equals("") && clientpf!=null  && datefacture==null	&&  fournisseur!=null && depot==null)
		
				{
					
					 query= session.createQuery("select c from FacturePF c where c.clientPF.id=:codeclient and c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
					
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
				}else if(NumFacture.equals("") && clientpf==null  && datefacture!=null	&&  fournisseur!=null && depot==null)
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
				}else if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("codeclient", clientpf.getId());
					query.setParameter("datefacture", datefacture);
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf==null && datefacture == null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
						
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("codeclient", clientpf.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and  c.dateFacture=:datefacture  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient  and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("codeclient", clientpf.getId());
				
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture=:datefacture  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("codeclient", clientpf.getId());
					query.setParameter("datefacture", datefacture);
					
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and   c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("datefacture", datefacture);
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and  c.dateFacture=:datefacture  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					query.setParameter("codeclient", clientpf.getId());
					query.setParameter("datefacture", datefacture);
					
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient  and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					query.setParameter("codeclient", clientpf.getId());
					
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					query.setParameter("codeclient", clientpf.getId());
					
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					 query.setParameter("codeclient", clientpf.getId());
					query.setParameter("datefacture", datefacture);
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}
				
				
				else if(NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.dateFacture=:datefacture  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}
				
				
				else if(NumFacture.equals("") && clientpf==null && datefacture==null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}
				
				
				
				return  query.list();
}	
	
	// listes des BL de type Facturé et Reglé
	
	@Override
	public List<FacturePF> findByNumFcatureClientDateFactureDepotEtatRegleFacturé(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot,String etat) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && depot !=null )
		{
			
			 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat and c.type=:Facturé");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			query.setParameter("etat", etat);
			query.setParameter("Facturé", "Facturé");
			
			
		}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.etat=:etat and c.type=:Facturé ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("etat", etat);
			query.setParameter("Facturé", "Facturé");
		
		}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and c.etat=:etat and c.type=:Facturé");
			
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("etat", etat);
			query.setParameter("Facturé", "Facturé");
			
				}else if(NumFacture.equals("") && clientpf==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.dateFacture=:datefacture and c.etat=:etat and c.type=:Facturé");
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
					query.setParameter("Facturé", "Facturé");
				
				}else if(NumFacture.equals("")  && clientpf==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where  c.depot.id=:iddepot and c.etat=:etat and c.type=:Facturé");
				
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facturé", "Facturé");
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and c.etat=:etat and c.type=:Facturé ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
						
						
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.etat=:etat and c.type=:Facturé ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.etat=:etat and c.type=:Facturé");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
				
					
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  and c.depot.id=:iddepot and c.etat=:etat and c.type=:Facturé ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient  and c.depot.id=:iddepot and c.etat=:etat and c.type=:Facturé ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat and c.type=:Facturé");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facturé", "Facturé");
					
				}else if(NumFacture.equals("") 
						&& clientpf!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat and c.type=:Facturé ");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
						
						
				}else if(NumFacture.equals("")
						&& clientpf!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.etat=:etat and c.type=:Facturé");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
					
					
				}else if(NumFacture.equals("") && clientpf!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FacturePF c where c.clientPF.id=:codeclient and c.depot.id=:iddepot and c.etat=:etat and c.type=:Facturé");
					
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
						
				}else if(NumFacture.equals("") && clientpf==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.dateFacture=:datefacture and c.depot.id=:iddepot and c.etat=:etat and c.type=:Facturé ");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facturé", "Facturé");
				}
				
				
				
				return  query.list();
}
	
	
	///liste des BL de type Facture
	
	@Override
	public List<FacturePF> findByNumFcatureClientDateFactureDepotEtatRegleFacture(String NumFacture,ClientPF clientpf,java.util.Date datefacture,java.util.Date dateAu, Depot depot, String req) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && dateAu!=null && depot !=null )
		{
			
			 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient "+req+" and  c.dateFacture between :datefacture and :dateAu and c.depot.id=:iddepot  order by  (REPLACE(c.numFacture,'/','')+0) ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("datefacture", datefacture);
			query.setParameter("dateAu", dateAu);
			query.setParameter("iddepot", depot.getId());
			//query.setParameter("Facture", "Facture");
			
			
		}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture "+req+" order by  (REPLACE(c.numFacture,'/','')+0) ");
			query.setParameter("NumFacture", NumFacture);
		
			//query.setParameter("Facture", "Facture");
		
		}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient "+req+" order by  (REPLACE(c.numFacture,'/','')+0) ");
			
			query.setParameter("codeclient", clientpf.getId());
		//	query.setParameter("Facture", "Facture");
			
				}else if(NumFacture.equals("") && clientpf==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.dateFacture between :datefacture and :dateAu "+req+" order by  (REPLACE(c.numFacture,'/','')+0) ");
					query.setParameter("datefacture", datefacture);
					query.setParameter("dateAu", dateAu);
					//query.setParameter("Facture", "Facture");
				
				}else if(NumFacture.equals("")  && clientpf==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where  c.depot.id=:iddepot "+req+" order by  (REPLACE(c.numFacture,'/','')+0) ");
				
					query.setParameter("iddepot", depot.getId());
					//query.setParameter("Facture", "Facture");
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture "+req+" and c.clientPF.id=:codeclient order by  (REPLACE(c.numFacture,'/','')+0) ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						//query.setParameter("Facture", "Facture");
						
						
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture "+req+" and c.clientPF.id=:codeclient and c.dateFacture between :datefacture and :dateAu  order by  (REPLACE(c.numFacture,'/','')+0) ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("dateAu", dateAu);
						//query.setParameter("Facture", "Facture");
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture "+req+"  and  c.dateFacture between :datefacture and :dateAu order by  (REPLACE(c.numFacture,'/','')+0) ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("dateAu", dateAu);
						query.setParameter("datefacture", datefacture);
						//query.setParameter("Facture", "Facture");
				
					
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  "+req+" and c.depot.id=:iddepot order by  (REPLACE(c.numFacture,'/','')+0)");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("Facture", "Facture");
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  "+req+" and c.clientPF.id=:codeclient  and c.depot.id=:iddepot order by  (REPLACE(c.numFacture,'/','')+0)");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("Facture", "Facture");
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture "+req+"  and  c.dateFacture between :datefacture and :dateAu and c.depot.id=:iddepot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("dateAu", dateAu);
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					//query.setParameter("Facture", "Facture");
					
				}else if(NumFacture.equals("") 
						&& clientpf!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient "+req+" and  c.dateFacture between :datefacture and :dateAu and c.depot.id=:iddepot order by  (REPLACE(c.numFacture,'/','')+0)");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("dateAu", dateAu);
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("Facture", "Facture");
						
						
				}else if(NumFacture.equals("")
						&& clientpf!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient "+req+" and  c.dateFacture between :datefacture and :dateAu order by  (REPLACE(c.numFacture,'/','')+0)");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("dateAu", dateAu);
						//query.setParameter("Facture", "Facture");
					
					
				}else if(NumFacture.equals("") && clientpf!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FacturePF c where c.clientPF.id=:codeclient "+req+" and c.depot.id=:iddepot order by  (REPLACE(c.numFacture,'/','')+0)");
					
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("Facture", "Facture");
						
				}else if(NumFacture.equals("") && clientpf==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.dateFacture between :datefacture and :dateAu "+req+" and c.depot.id=:iddepot  order by  (REPLACE(c.numFacture,'/','')+0) ");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("dateAu", dateAu);
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("Facture", "Facture");
				}
				
				
				
				return  query.list();
}
	
	
// Liste des factures de Type != BL	
	
	@Override
	public List<FacturePF> findByNumFcatureClientDateFactureDepotTypeNonBL(String NumFacture,ClientPF clientpf,java.util.Date datefacturedebu,java.util.Date datefacturefin, Magasin magasin,String type) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && clientpf!=null && datefacturedebu!=null && magasin !=null )
		{
			
			 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and c.type!=:type and  c.dateFacture between :datefacture and :datefacturefin and c.magasin.id=:magasin order by c.dateFacture ,c.numFacture  ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("datefacture", datefacturedebu);
			query.setParameter("datefacturefin", datefacturefin);
			query.setParameter("magasin", magasin.getId());
			query.setParameter("type", type);
		
			
			
		}else if(!NumFacture.equals("") && clientpf==null && datefacturedebu==null && magasin==null)
		{
			 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.type!=:type   order by c.dateFacture,c.numFacture ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("type", type);
			
		
		}else if(NumFacture.equals("") && clientpf!=null && datefacturedebu==null && magasin==null)
		{
			 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and c.type!=:type  order by c.dateFacture,c.numFacture ");
			
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("type", type);
			
			
				}else if(NumFacture.equals("") && clientpf==null && datefacturedebu!=null && magasin==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.dateFacture between :datefacture and :datefacturefin and c.type!=:type  order by c.dateFacture ,c.numFacture ");
					query.setParameter("datefacture", datefacturedebu);
					query.setParameter("datefacturefin", datefacturefin);
					query.setParameter("type", type);
					
				
				}else if(NumFacture.equals("")  && clientpf==null && datefacturedebu==null	&& !magasin.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where  c.magasin.id=:magasin and c.type!=:type  order by c.dateFacture ,c.numFacture ");
				
					query.setParameter("magasin", magasin.getId());
					query.setParameter("type", type);
				
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacturedebu==null	&& magasin==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and c.type!=:type  order by c.dateFacture ,c.numFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("type", type);
						
						
						
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacturedebu!=null	&& magasin==null)
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture between :datefacture and :datefacturefin and c.type!=:type  order by c.dateFacture ,c.numFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("type", type);
						
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacturedebu!=null	&& magasin==null)
				{
					
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  and  c.dateFacture between :datefacture and :datefacturefin and c.type!=:type  order by c.dateFacture ,c.numFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("type", type);
						
				
					
				}else if(!NumFacture.equals("")  && clientpf==null && datefacturedebu==null	&& !magasin.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  and c.magasin.id=:magasin and c.type!=:type order by c.dateFacture ,c.numFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("magasin", magasin.getId());
						query.setParameter("type", type);
						
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacturedebu==null	&& !magasin.equals(null))
				{
					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient  and c.magasin.id=:magasin and c.type!=:type order by c.dateFacture ,c.numFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						
						query.setParameter("magasin", magasin.getId());
						query.setParameter("type", type);
						
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacturedebu!=null	&& !magasin.equals(null))
				{

					 query= session.createQuery("select c from FacturePF c where c.numFacture=:NumFacture  and   c.dateFacture between :datefacture and :datefacturefin and c.magasin.id=:magasin and c.type!=:type order by c.dateFacture ,c.numFacture ");
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
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and   c.dateFacture between :datefacture and :datefacturefin and c.magasin.id=:magasin and c.type!=:type order by c.dateFacture,c.numFacture  ");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("magasin", magasin.getId());
						query.setParameter("type", type);
						
						
						
				}else if(NumFacture.equals("")
						&& clientpf!=null
						&& datefacturedebu!=null	&& magasin==null)
				{
					 query= session.createQuery("select c from FacturePF c where  c.clientPF.id=:codeclient and  c.dateFacture between :datefacture and :datefacturefin and c.type!=:type order by c.dateFacture ,c.numFacture ");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("type", type);
						
					
					
				}else if(NumFacture.equals("") && clientpf!=null  && datefacturedebu==null	&&  magasin!=null)
		
				{
					
					 query= session.createQuery("select c from FacturePF c where c.clientPF.id=:codeclient and c.magasin.id=:magasin and c.type!=:type order by c.dateFacture ,c.numFacture ");
					
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("magasin", magasin.getId());
						query.setParameter("type", type);
						
						
				}else if(NumFacture.equals("") && clientpf==null  && datefacturedebu!=null	&&  magasin!=null)
				{
					
					 query= session.createQuery("select c from FacturePF c where  c.dateFacture between :datefacture and :datefacturefin and c.magasin.id=:magasin and c.type!=:type order by c.dateFacture ,c.numFacture ");
						
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("magasin", magasin.getId());
						query.setParameter("type", type);
						
				}
				
				
				
				return  query.list();
}
	
	
	
	
	
	
	
	
	
	
		
	public FacturePF findFacturePFByCodeTransfer(String code) {
		Query query= session.createQuery("select c from FacturePF c where codeTransfer=:code");
		query.setParameter("code", code);
		
		return (FacturePF) query.uniqueResult();
		
		}


	/// list des BL contient les gratuités
	
	@Override
	public List<FacturePF> listBLGratuite(Articles article) {
		
		// AIN ATLAS 3L
		//"select c from FacturePF c where c.NumBl not in ( select facturePF.NumBl from DetailFacturePF  where prixUnitaire=0 and facturePF.magasin.id=7 and facturePF.type='BL') and c.magasin.id=7 and c.type='BL' and c.id in (select facturePF.id from DetailFacturePF where article.id=:article) "
				Query query= session.createQuery("select c from FacturePF c where c.numBl in ( select facturePF.numBl from DetailFacturePF  where article.id=:article and prixUnitaire<>0 and facturePF.type='BL')");
				
				query.setParameter("article", article.getId());
				
				
				return  query.list();
}
	
	
	public int maxIdFacture() {
        int id =0;
		Query query= session.createQuery("select max(id) from FacturePF");
		
		if(query.uniqueResult()==null)
			id=1;
		else 
			id= (int)query.uniqueResult();
		
		return id;
    }
	

	public List<FacturePF> listeFacturePFByNumFacture(Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from FacturePF c where magasin.id=:magasin and c.numFacture in ('180531008','180531007','180531006','180502003','180511002','180531011','180531010')");
		query.setParameter("magasin", magasin.getId());
	
		
		return query.list();

	}
	
	public List<FacturePF> listeFacturePFBetweenNumFacture(Depot depot , String du) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from FacturePF c where depot.id=:depot and c.numFacture >= :du");
		query.setParameter("depot", depot.getId());
		query.setParameter("du", du);
	//	query.setParameter("au", au);
		
		return query.list();

	}
	
	
	public List<FacturePF> listeFacturePFEntreDeuxDatesOrderByDate(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from FacturePF c where magasin.id=:magasin c.dateFacture between :datedu and :dateAu order by c.dateFacture");
		query.setParameter("magasin", magasin.getId());
		query.setParameter("datedu", datedu);
		query.setParameter("dateAu", dateAu);
	
		
		return query.list();

	}
	
	
	public List<FacturePF> listeFacturePFNonFacturer(java.util.Date datedu,Depot depot , String types) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from FacturePF c where c.depot.id=:depot and c.dateFacture <:datedu and ( c.numFacture is null or c.numFacture=:vide) and c.type=:types order by c.dateFacture");
		query.setParameter("depot", depot.getId());
		query.setParameter("datedu", datedu);		
		query.setParameter("types", types);
		query.setParameter("vide", "");
		return query.list();

	}	
	
	
	
	
	public List<FacturePF> listeFacturePFEntreDeuxDatesOrderByNumFacture(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from FacturePF c where magasin.id=:magasin c.dateFacture between :datedu and :dateAu order by c.numFacture");
		query.setParameter("magasin", magasin.getId());
		query.setParameter("datedu", datedu);
		query.setParameter("dateAu", dateAu);
	
		
		return query.list();

	}
	
	
	

}
