package dao.daoImplManager;

import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.FactureAutresVenteDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAutresVente;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FactureServiceTransport;
import dao.entity.FactureVenteMP;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public class FactureAutresVenteDAOImpl implements FactureAutresVenteDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(FactureAutresVente e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
		
	}

	public FactureAutresVente edit(FactureAutresVente e) {
		
	session.beginTransaction();
	FactureAutresVente p= (FactureAutresVente)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FactureAutresVente p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FactureAutresVente> findAll() {
		return session.createQuery("select c from FactureAutresVente c").list();
		}
	
	

	public FactureAutresVente findById(int id) {
		return (FactureAutresVente)session.get(FactureAutresVente.class, id);
		}
	
	public FactureAutresVente findFacturePFByEtat(String etat) {
		Query query= session.createQuery("select c from FactureAutresVente c where etat=:etat");
		query.setParameter("etat", etat);
		
		return (FactureAutresVente) query.uniqueResult();
		
		}
	
	
	public FactureAutresVente findFactureVentePFByNumBL(String NumBL) {
		Query query= session.createQuery("select c from FactureAutresVente c where numBl=:NumBL");
		query.setParameter("NumBL", NumBL);
		
		return (FactureAutresVente) query.uniqueResult();
		
		}
	
	
	
	@Override
	public List<FactureAutresVente> findByNumFacture(String NumFacture, Depot depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.depot.id=:depot");
				query.setParameter("NumFacture", NumFacture);
				query.setParameter("depot", depot.getId());
				
				
				return  query.list();
}
	
	
	
	@Override
	public List<FactureAutresVente> listFactureAutresVenteNonFacturer(java.util.Date datedu,Depot depot , String type) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAutresVente c where depot.id=:depot and c.dateFacture < :datedu and  (c.numFacture is null or c.numFacture='') and c.type=:type order by c.dateFacture");
				
				query.setParameter("depot", depot.getId());
				query.setParameter("datedu", datedu);		
				query.setParameter("type", type);
				
				
				return  query.list();
}
	
	
	
	@Override
	public List<FactureAutresVente> findByClient(String codeclient) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAutresVente c where c.clientPF.code=:codeclient");
				query.setParameter("codeclient", codeclient);
				
				
				return  query.list();
}
	
	
	//liste des BL de type facture ou Facturé par client
	@Override
	public List<FactureAutresVente> findFactureByClient(String codeclient) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAutresVente c where c.clientPF.code=:codeclient and c.type !=:BL");
				query.setParameter("codeclient", codeclient);
				query.setParameter("BL", "BL");
				
				return  query.list();
}
	
	
	//liste des BL de type facture ou Facturé par client
	@Override
	public List<FactureAutresVente> findBLByClient(String codeclient) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAutresVente c where c.clientPF.code=:codeclient and c.type =:BL");
				query.setParameter("codeclient", codeclient);
				query.setParameter("BL", "BL");
				
				return  query.list();
}
	
	@Override
	public List<FactureAutresVente> findByDate(java.util.Date datefacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAutresVente c where c.dateFacture=:datefacture");
				query.setParameter("datefacture", datefacture);
				
				
				return  query.list();
}
	
	//liste des BL de type facture entre deux date
	@Override
	public List<FactureAutresVente> findFactureEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Depot depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAutresVente c where c.dateFacture between :dateDebut and :datedeFin and c.type !=:BL  and c.depot.id=:depot");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("depot", depot.getId());
				query.setParameter("BL", "BL");
				return  query.list();
}
	
	
	
	@Override
	public List<FactureAutresVente> findFacturePFByRequete(String req) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAutresVente c where "+req +" order by c.dateFacture");
				return  query.list();
}
	

		
	@Override
	public List<FactureAutresVente> findByDepot(int iddepot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureAutresVente c where c.depot.id=:iddepot");
				query.setParameter("iddepot", iddepot);
				
				
				return  query.list();
}
	
	
	
	@Override
	public List<FactureAutresVente> findByNumFcatureClientDateFactureDepotEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacturedebu,java.util.Date datefacturefin, Depot depot,String etat) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && clientpf!=null && datefacturedebu!=null && depot !=null )
		{
			
			 query= session.createQuery("select c from FactureAutresVente c where c.numBl=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture between :datefacture and :datefacturefin and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("datefacture", datefacturedebu);
			query.setParameter("datefacturefin", datefacturefin);
			query.setParameter("iddepot", depot.getId());
			query.setParameter("etat", etat);
		
			
			
		}else if(!NumFacture.equals("") && clientpf==null && datefacturedebu==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAutresVente c where c.numBl=:NumFacture and c.etat=:etat  order by c.dateFacture");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("etat", etat);
			
		
		}else if(NumFacture.equals("") && clientpf!=null && datefacturedebu==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAutresVente c where  c.clientPF.id=:codeclient and c.etat=:etat order by c.dateFacture");
			
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("etat", etat);
			
			
				}else if(NumFacture.equals("") && clientpf==null && datefacturedebu!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.dateFacture between :datefacture and :datefacturefin and c.etat=:etat order by c.dateFacture ");
					query.setParameter("datefacture", datefacturedebu);
					query.setParameter("datefacturefin", datefacturefin);
					query.setParameter("etat", etat);
					
				
				}else if(NumFacture.equals("")  && clientpf==null && datefacturedebu==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAutresVente c where  c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
				
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
				
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacturedebu==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.numBl=:NumFacture and c.clientPF.id=:codeclient and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("etat", etat);
						
						
						
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacturedebu!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.numBl=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture between :datefacture and :datefacturefin and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("etat", etat);
						
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacturedebu!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.numBl=:NumFacture  and  c.dateFacture between :datefacture and :datefacturefin and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("etat", etat);
						
				
					
				}else if(!NumFacture.equals("")  && clientpf==null && datefacturedebu==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.numBl=:NumFacture  and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacturedebu==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.numBl=:NumFacture and c.clientPF.id=:codeclient  and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacturedebu!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureAutresVente c where c.numBl=:NumFacture  and   c.dateFacture between :datefacture and :datefacturefin and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
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
					 query= session.createQuery("select c from FactureAutresVente c where  c.clientPF.id=:codeclient and   c.dateFacture between :datefacture and :datefacturefin and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
						
						
				}else if(NumFacture.equals("")
						&& clientpf!=null
						&& datefacturedebu!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where  c.clientPF.id=:codeclient and  c.dateFacture between :datefacture and :datefacturefin and c.etat=:etat order by c.dateFacture ");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("etat", etat);
						
					
					
				}else if(NumFacture.equals("") && clientpf!=null  && datefacturedebu==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.clientPF.id=:codeclient and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
					
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
						
				}else if(NumFacture.equals("") && clientpf==null  && datefacturedebu!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where  c.dateFacture between :datefacture and :datefacturefin and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture ");
						
						query.setParameter("datefacture", datefacturedebu);
						query.setParameter("datefacturefin", datefacturefin);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
				}
				
				
				
				return  query.list();
}	


	
	///liste des BL de type Facture
	
	@Override
	public List<FactureAutresVente> findByNumFcatureClientDateFactureDepotEtatRegleFacture(String NumFacture,ClientPF clientpf,java.util.Date datefacture,java.util.Date dateAu, Depot depot) {
		
		Query query = null;
		
		if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && dateAu!=null && depot !=null )
		{
			
			 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture between :datefacture and :dateAu and c.depot.id=:iddepot order by c.dateFacture");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("datefacture", datefacture);
			query.setParameter("dateAu", dateAu);
			query.setParameter("iddepot", depot.getId());
			//query.setParameter("Facture", "Facture");
			
			
		}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture order by c.dateFacture ");
			query.setParameter("NumFacture", NumFacture);
		
			//query.setParameter("Facture", "Facture");
		
		}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAutresVente c where  c.clientPF.id=:codeclient order by c.dateFacture ");
			
			query.setParameter("codeclient", clientpf.getId());
		//	query.setParameter("Facture", "Facture");
			
				}else if(NumFacture.equals("") && clientpf==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.dateFacture between :datefacture and :dateAu order by c.dateFacture ");
					query.setParameter("datefacture", datefacture);
					query.setParameter("dateAu", dateAu);
					//query.setParameter("Facture", "Facture");
				
				}else if(NumFacture.equals("")  && clientpf==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAutresVente c where  c.depot.id=:iddepot order by c.dateFacture ");
				
					query.setParameter("iddepot", depot.getId());
					//query.setParameter("Facture", "Facture");
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						//query.setParameter("Facture", "Facture");
						
						
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and c.dateFacture between :datefacture and :dateAu  order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("dateAu", dateAu);
						//query.setParameter("Facture", "Facture");
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture  and  c.dateFacture between :datefacture and :dateAu order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("dateAu", dateAu);
						query.setParameter("datefacture", datefacture);
						//query.setParameter("Facture", "Facture");
				
					
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture  and c.depot.id=:iddepot order by c.dateFacture ");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("Facture", "Facture");
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient  and c.depot.id=:iddepot order by c.dateFacture");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("Facture", "Facture");
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture  and  c.dateFacture between :datefacture and :dateAu and c.depot.id=:iddepot order by c.dateFacture");
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
					 query= session.createQuery("select c from FactureAutresVente c where  c.clientPF.id=:codeclient and  c.dateFacture between :datefacture and :dateAu and c.depot.id=:iddepot order by c.dateFacture");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("dateAu", dateAu);
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("Facture", "Facture");
						
						
				}else if(NumFacture.equals("")
						&& clientpf!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where  c.clientPF.id=:codeclient and  c.dateFacture between :datefacture and :dateAu order by c.dateFacture");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("dateAu", dateAu);
						//query.setParameter("Facture", "Facture");
					
					
				}else if(NumFacture.equals("") && clientpf!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.clientPF.id=:codeclient and c.depot.id=:iddepot order by c.dateFacture");
					
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("Facture", "Facture");
						
				}else if(NumFacture.equals("") && clientpf==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where  c.dateFacture between :datefacture and :dateAu and c.depot.id=:iddepot  order by c.dateFacture ");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("dateAu", dateAu);
						query.setParameter("iddepot", depot.getId());
						//query.setParameter("Facture", "Facture");
				}
				
				
				
				return  query.list();
}	
	
	
	
	
	@Override
	public List<FactureAutresVente> findByNumFcatureClientDateFactureFournisseurEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Client fournisseur,String etat,Depot depot) {
		
		Query query = null;
		
		
		
		if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur !=null && depot==null )
		{
			
			 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.Fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("datefacture", datefacture);
			query.setParameter("fournisseur", fournisseur.getId());
			query.setParameter("etat", etat);
			query.setParameter("Facture", "Facture");
		
			
		}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && fournisseur==null && depot==null)
		{
			 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
			query.setParameter("NumFacture", NumFacture);
			query.setParameter("etat", etat);
			query.setParameter("Facture", "Facture");
			
		}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur==null && depot==null )
		{
			 query= session.createQuery("select c from FactureAutresVente c where  c.clientPF.id=:codeclient and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
			
			query.setParameter("codeclient", clientpf.getId());
			query.setParameter("etat", etat);
			query.setParameter("Facture", "Facture");
			
				}else if(NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.dateFacture=:datefacture and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
				
				}else if(NumFacture.equals("")  && clientpf==null && datefacture==null	&& fournisseur!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where  c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
				
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					
					
				}else if(NumFacture.equals("")  && clientpf==null && datefacture==null	&& fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");				
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					
					
				}
				
				else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
						
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture!=null	&& fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FactureServiceTransport c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& fournisseur==null && depot==null)
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
				
					
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture==null	&& fournisseur!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture  and c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						query.setParameter("NumFacture", NumFacture);
						
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
					
				}else if(!NumFacture.equals("")  && clientpf!=null && datefacture==null	&& fournisseur!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient  and c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						query.setParameter("NumFacture", NumFacture);
						query.setParameter("codeclient", clientpf.getId());
						
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
				}else if(!NumFacture.equals("")  && clientpf==null && datefacture!=null	&& fournisseur!=null && depot==null)
				{

					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture  and  c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
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
					 query= session.createQuery("select c from FactureAutresVente c where  c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
						
				}else if(NumFacture.equals("")
						&& clientpf!=null
						&& datefacture!=null	&& fournisseur==null && depot==null)
				{
					 query= session.createQuery("select c from FactureAutresVente c where  c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
					
					
				}else if(NumFacture.equals("") && clientpf!=null  && datefacture==null	&&  fournisseur!=null && depot==null)
		
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.clientPF.id=:codeclient and c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
					
						query.setParameter("codeclient", clientpf.getId());
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						
				}else if(NumFacture.equals("") && clientpf==null  && datefacture!=null	&&  fournisseur!=null && depot==null)
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where  c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat  and c.type=:Facture order by  (REPLACE(c.numFacture,'/','')+0)");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("fournisseur", fournisseur.getId());
						query.setParameter("etat", etat);
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
				}else if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("codeclient", clientpf.getId());
					query.setParameter("datefacture", datefacture);
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf==null && datefacture == null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where  c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
						
						query.setParameter("etat", etat);
						query.setParameter("Facture", "Facture");
						query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("codeclient", clientpf.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and  c.dateFacture=:datefacture  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf==null && datefacture==null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient  and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("codeclient", clientpf.getId());
				
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and c.clientPF.id=:codeclient and  c.dateFacture=:datefacture  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("codeclient", clientpf.getId());
					query.setParameter("datefacture", datefacture);
					
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(!NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.numFacture=:NumFacture and   c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					query.setParameter("NumFacture", NumFacture);
					query.setParameter("datefacture", datefacture);
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where  c.clientPF.id=:codeclient and  c.dateFacture=:datefacture  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					query.setParameter("codeclient", clientpf.getId());
					query.setParameter("datefacture", datefacture);
					
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where  c.clientPF.id=:codeclient  and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					query.setParameter("codeclient", clientpf.getId());
					
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf!=null && datefacture==null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where  c.clientPF.id=:codeclient  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					query.setParameter("codeclient", clientpf.getId());
					
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf!=null && datefacture!=null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where c.clientPF.id=:codeclient and  c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					 query.setParameter("codeclient", clientpf.getId());
					query.setParameter("datefacture", datefacture);
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}
				
				
				else if(NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur ==null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where  c.dateFacture=:datefacture  and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}else if(NumFacture.equals("") && clientpf==null && datefacture!=null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where  c.dateFacture=:datefacture and c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}
				
				
				else if(NumFacture.equals("") && clientpf==null && datefacture==null && fournisseur !=null && depot!=null )
				{
					
					 query= session.createQuery("select c from FactureAutresVente c where  c.fournisseur.id=:fournisseur and c.etat=:etat and c.type=:Facture and c.depot.id=:depot order by  (REPLACE(c.numFacture,'/','')+0)");
					
					query.setParameter("fournisseur", fournisseur.getId());
					query.setParameter("etat", etat);
					query.setParameter("Facture", "Facture");
					query.setParameter("depot", depot.getId());
					
				}
				
				
				
				return  query.list();
}	
	
	
	

}
