package dao.daoImplManager;

import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.entity.Client;

import dao.entity.CompteStockMP;
import dao.entity.Depot;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FactureServiceProduction;
import dao.entity.FactureVenteMP;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public class FactureServiceProductionDAOImpl implements FactureServiceProductionDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(FactureServiceProduction e) {
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		//return p;
	}

	public FactureServiceProduction edit(FactureServiceProduction e) {

	session.beginTransaction();
	FactureServiceProduction p= (FactureServiceProduction)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		FactureServiceProduction p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<FactureServiceProduction> findAll() {
		return session.createQuery("select c from FactureServiceProduction c order by c.id").list();
		}
	
public FactureServiceProduction findByNumFcatureByDate(String numFacture,java.util.Date datefacture) {
		
		Query query = null;
		
		
			
			 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture and  c.dateFacture=:datefacture");
			query.setParameter("numFacture", numFacture);
			query.setParameter("datefacture", datefacture);
			
		return (FactureServiceProduction) query.uniqueResult();
}

	public FactureServiceProduction findById(int id) {
		return (FactureServiceProduction)session.get(FactureServiceProduction.class, id);
		}
	
	public FactureServiceProduction findFactureServiceProductionByEtat(String etat) {
		Query query= session.createQuery("select c from FactureServiceProduction c where etat=:etat");
		query.setParameter("etat", etat);
		
		return (FactureServiceProduction) query.uniqueResult();
		
		}
	

	
	
	
	public List<FactureServiceProduction> findByNumFacture(String numFacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture");
				query.setParameter("numFacture", numFacture);
				
				
				return  query.list();
}
	
	
	public FactureServiceProduction findByNumOF(String numOF) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureServiceProduction c where c.numOF=:numOF");
				query.setParameter("numOF", numOF);
				
				
				return  (FactureServiceProduction) query.uniqueResult();
}
	
	
	
	
	
	public List<FactureServiceProduction> findByClient(String codeclient) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureServiceProduction c where c.client.code=:codeclient");
				query.setParameter("codeclient", codeclient);
				
				
				return  query.list();
}
	
	

	
	
	public List<FactureServiceProduction> findByDate(java.util.Date datefacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureServiceProduction c where c.dateFacture=:datefacture");
				query.setParameter("datefacture", datefacture);
				
				
				return  query.list();
}
	
	//liste des facture entre deux date
	
	public List<FactureServiceProduction> findFactureEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Depot depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureServiceProduction c where c.dateFacture between :dateDebut and :datedeFin and c.depot.id=:depot");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("depot", depot.getId());
				
				return  query.list();
}
		
	
	public List<FactureServiceProduction> findByDepot(int iddepot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureServiceProduction c where c.depot.id=:iddepot");
				query.setParameter("iddepot", iddepot);
				
				
				return  query.list();
}
	
	
	


	
	
	public List<FactureServiceProduction> findByNumFcatureClientDateFactureDepot(String numFacture,Client client,java.util.Date datefacture,Depot depot) {
		
		Query query = null;
		
		if(!numFacture.equals("") && client!=null && datefacture!=null && depot !=null)
		{
			
			 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture and c.client.code=:codeclient and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
			query.setParameter("numFacture", numFacture);
			query.setParameter("codeclient", client.getCode());
			query.setParameter("datefacture", datefacture);
			query.setParameter("iddepot", depot.getId());
			
			
		}else if(!numFacture.equals("") && client==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture ");
			query.setParameter("numFacture", numFacture);
		
		}else if(numFacture.equals("") && client!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureServiceProduction c where  c.client.code=:codeclient ");
			
			query.setParameter("codeclient", client.getCode());
			
				}else if(numFacture.equals("") && client==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureServiceProduction c where c.dateFacture=:datefacture");
					query.setParameter("datefacture", datefacture);
				
				}else if(numFacture.equals("")  && client==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureServiceProduction c where  c.depot.id=:iddepot");
				
					query.setParameter("iddepot", depot.getId());
				}else if(!numFacture.equals("")  && client!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture and c.client.code=:codeclient ");
						query.setParameter("numFacture", numFacture);
						query.setParameter("codeclient", client.getCode());
						
						
				}else if(!numFacture.equals("")  && client!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture and c.client.code=:codeclient and  c.dateFacture=:datefacture");
						query.setParameter("numFacture", numFacture);
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
						
				}else if(!numFacture.equals("")  && client==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture  and  c.dateFacture=:datefacture ");
						query.setParameter("numFacture", numFacture);
						
						query.setParameter("datefacture", datefacture);
				
					
				}else if(!numFacture.equals("")  && client==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture  and c.depot.id=:iddepot");
						query.setParameter("numFacture", numFacture);
						
						query.setParameter("iddepot", depot.getId());
					
				}else if(!numFacture.equals("")  && client!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture and c.client.code=:codeclient  and c.depot.id=:iddepot");
						query.setParameter("numFacture", numFacture);
						query.setParameter("codeclient", client.getCode());
						
						query.setParameter("iddepot", depot.getId());
				}else if(!numFacture.equals("")  && client==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture  and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
					query.setParameter("numFacture", numFacture);
					
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					
				}else if(numFacture.equals("") 
						&& client!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureServiceProduction c where  c.client.code=:codeclient and  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
						
						
				}else if(numFacture.equals("")
						&& client!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureServiceProduction c where  c.client.code=:codeclient and  c.dateFacture=:datefacture");
						
						query.setParameter("codeclient", client.getCode());
						query.setParameter("datefacture", datefacture);
					
					
				}else if(numFacture.equals("") && client!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureServiceProduction c where c.client.code=:codeclient and c.depot.id=:iddepot");
					
						query.setParameter("codeclient", client.getCode());
						query.setParameter("iddepot", depot.getId());
				}else if(numFacture.equals("") && client==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureServiceProduction c where  c.dateFacture=:datefacture and c.depot.id=:iddepot");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("iddepot", depot.getId());
				}
				
				
				
				return  query.list();
}
	
	
	@Override
	public List<FactureServiceProduction> findByNumFcatureClientDateFactureDepotEtatRegle(String numFacture,Client client,java.util.Date datefacture,java.util.Date datefactureAu,Depot depot,String etat) {
		
		Query query = null;
		
		if(!numFacture.equals("") && client!=null && datefacture!=null && depot !=null )
		{
			
			 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture and c.client.id=:client and  c.dateFacture between :datefacture and :datefactureAu and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture , c.numFacture ");
			query.setParameter("numFacture", numFacture);
			query.setParameter("client", client.getId());
			query.setParameter("datefacture", datefacture);
			query.setParameter("datefactureAu", datefactureAu);
			query.setParameter("iddepot", depot.getId());
			query.setParameter("etat", etat);
		
			
			
		}else if(!numFacture.equals("") && client==null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture and c.etat=:etat order by c.dateFacture , c.numFacture ");
			query.setParameter("numFacture", numFacture);
			query.setParameter("etat", etat);
			
		
		}else if(numFacture.equals("") && client!=null && datefacture==null && depot==null)
		{
			 query= session.createQuery("select c from FactureServiceProduction c where  c.client.id=:client and c.etat=:etat order by c.dateFacture , c.numFacture ");
			
			 query.setParameter("client", client.getId());
			query.setParameter("etat", etat);
			
			
				}else if(numFacture.equals("") && client==null && datefacture!=null && depot==null)
				{
					 query= session.createQuery("select c from FactureServiceProduction c where c.dateFacture between :datefacture and :datefactureAu  and c.etat=:etat order by c.dateFacture , c.numFacture ");
					query.setParameter("datefacture", datefacture);
					query.setParameter("datefactureAu", datefactureAu);
					query.setParameter("etat", etat);
					
				
				}else if(numFacture.equals("")  && client==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureServiceProduction c where  c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture , c.numFacture ");
				
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
				
					
				}else if(!numFacture.equals("")  && client!=null && datefacture==null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture and c.client.id=:client and c.etat=:etat order by c.dateFacture , c.numFacture ");
						query.setParameter("numFacture", numFacture);
						query.setParameter("client", client.getId());
						query.setParameter("etat", etat);
						
						
						
				}else if(!numFacture.equals("")  && client!=null && datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture and c.client.id=:client and  c.dateFacture between :datefacture and :datefactureAu and c.etat=:etat order by c.dateFacture , c.numFacture ");
						query.setParameter("numFacture", numFacture);
						query.setParameter("client", client.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("datefactureAu", datefactureAu);
						query.setParameter("etat", etat);
						
						
				}else if(!numFacture.equals("")  && client==null && datefacture!=null	&& depot==null)
				{
					
					 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture  and  c.dateFacture between :datefacture and :datefactureAu and c.etat=:etat order by c.dateFacture , c.numFacture ");
						query.setParameter("numFacture", numFacture);
						query.setParameter("datefactureAu", datefactureAu);
						query.setParameter("datefacture", datefacture);
						query.setParameter("etat", etat);
						
				
					
				}else if(!numFacture.equals("")  && client==null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture  and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture , c.numFacture ");
						query.setParameter("numFacture", numFacture);
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
					
				}else if(!numFacture.equals("")  && client!=null && datefacture==null	&& !depot.equals(null))
				{
					 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture and c.client.id=:client  and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture , c.numFacture ");
						query.setParameter("numFacture", numFacture);
						query.setParameter("client", client.getId());
						
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
						
				}else if(!numFacture.equals("")  && client==null && datefacture!=null	&& !depot.equals(null))
				{

					 query= session.createQuery("select c from FactureServiceProduction c where c.numFacture=:numFacture  and  c.dateFacture between :datefacture and :datefactureAu and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture , c.numFacture ");
					query.setParameter("numFacture", numFacture);
					query.setParameter("datefactureAu", datefactureAu);
					query.setParameter("datefacture", datefacture);
					query.setParameter("iddepot", depot.getId());
					query.setParameter("etat", etat);
					
					
				}else if(numFacture.equals("") 
						&& client!=null
						&& datefacture!=null	
						&& depot!=null)
				{
					 query= session.createQuery("select c from FactureServiceProduction c where  c.client.id=:client and  c.dateFacture between :datefacture and :datefactureAu and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture , c.numFacture ");
						
					 query.setParameter("client", client.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("datefactureAu", datefactureAu);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
						
						
				}else if(numFacture.equals("")
						&& client!=null
						&& datefacture!=null	&& depot==null)
				{
					 query= session.createQuery("select c from FactureServiceProduction c where  c.client.id=:client and  c.dateFacture between :datefacture and :datefactureAu and c.etat=:etat order by c.dateFacture , c.numFacture ");
						
					 query.setParameter("client", client.getId());
						query.setParameter("datefacture", datefacture);
						query.setParameter("datefactureAu", datefactureAu);
						query.setParameter("etat", etat);
						
					
					
				}else if(numFacture.equals("") && client!=null  && datefacture==null	&&  depot!=null)
		
				{
					
					 query= session.createQuery("select c from FactureServiceProduction c where c.client.id=:client and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture , c.numFacture ");
					
					 query.setParameter("client", client.getId());
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
						
				}else if(numFacture.equals("") && client==null  && datefacture!=null	&&  depot!=null)
				{
					
					 query= session.createQuery("select c from FactureServiceProduction c where  c.dateFacture  between :datefacture and :datefactureAu and c.depot.id=:iddepot and c.etat=:etat order by c.dateFacture , c.numFacture ");
						
						query.setParameter("datefacture", datefacture);
						query.setParameter("datefactureAu", datefactureAu);
						query.setParameter("iddepot", depot.getId());
						query.setParameter("etat", etat);
						
				}
				
				
				
				return  query.list();
}	
	
	
	public List<FactureServiceProduction> findBetweenNumFacture( ) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from FactureServiceProduction c where c.numFacture not in ('002/2019',\r\n" + 
						"'003/2019',\r\n" + 
						"'004/2019',\r\n" + 
						"'005/2019',\r\n" + 
						"'006/2019',\r\n" + 
						"'007/2019',\r\n" + 
						"'008/2019',\r\n" + 
						"'009/2019',\r\n" + 
						"'009/2019',\r\n" + 
						"'009/2019',\r\n" + 
						"'010/2019',\r\n" + 
						"'011/2019',\r\n" + 
						"'013/2019',\r\n" + 
						"'014/2019',\r\n" + 
						"'015/2019',\r\n" + 
						"'016/2019',\r\n" + 
						"'017/2019',\r\n" + 
						"'018/2019',\r\n" + 
						"'019/2019',\r\n" + 
						"'020/2019',\r\n" + 
						"'020/2019',\r\n" + 
						"'020/2019',\r\n" + 
						"'021/2019',\r\n" + 
						"'022/2019',\r\n" + 
						"'023/2019',\r\n" + 
						"'024/2019',\r\n" + 
						"'025/2019',\r\n" + 
						"'026/2019',\r\n" + 
						"'027/2019',\r\n" + 
						"'028/2019',\r\n" + 
						"'029/2019',\r\n" + 
						"'030/2019',\r\n" + 
						"'030/2019',\r\n" + 
						"'030/2019',\r\n" + 
						"'031/2019',\r\n" + 
						"'032/2019',\r\n" + 
						"'033/2019',\r\n" + 
						"'033/2019',\r\n" + 
						"'033/2019',\r\n" + 
						"'034/2019',\r\n" + 
						"'035/2019',\r\n" + 
						"'036/2019',\r\n" + 
						"'037/2019',\r\n" + 
						"'038/2019',\r\n" + 
						"'039/2019',\r\n" + 
						"'040/2019',\r\n" + 
						"'042/2019',\r\n" + 
						"'043/2019',\r\n" + 
						"'044/2019',\r\n" + 
						"'045/2019',\r\n" + 
						"'046/2019',\r\n" + 
						"'047/2019',\r\n" + 
						"'052/2019',\r\n" + 
						"'053/2019',\r\n" + 
						"'054/2019',\r\n" + 
						"'055/2019',\r\n" + 
						"'056/2019',\r\n" + 
						"'057/2019',\r\n" + 
						"'058/2019',\r\n" + 
						"'059/2019',\r\n" + 
						"'060/2019',\r\n" + 
						"'061/2019',\r\n" + 
						"'062/2019',\r\n" + 
						"'062/2019',\r\n" + 
						"'062/2019',\r\n" + 
						"'063/2019',\r\n" + 
						"'064/2019',\r\n" + 
						"'065/2019',\r\n" + 
						"'066/2019',\r\n" + 
						"'066/2019',\r\n" + 
						"'066/2019',\r\n" + 
						"'067/2019',\r\n" + 
						"'067/2019',\r\n" + 
						"'067/2019',\r\n" + 
						"'068/2019',\r\n" + 
						"'069/2019',\r\n" + 
						"'071/2019',\r\n" + 
						"'072/2019',\r\n" + 
						"'073/2019',\r\n" + 
						"'073/2019',\r\n" + 
						"'073/2019',\r\n" + 
						"'074/2019',\r\n" + 
						"'075/2019',\r\n" + 
						"'076/2019',\r\n" + 
						"'077/2019',\r\n" + 
						"'078/2019',\r\n" + 
						"'078/2019',\r\n" + 
						"'078/2019',\r\n" + 
						"'079/2019',\r\n" + 
						"'081/2019',\r\n" + 
						"'084/2019',\r\n" + 
						"'085/2019',\r\n" + 
						"'086/2019',\r\n" + 
						"'086/2019',\r\n" + 
						"'086/2019',\r\n" + 
						"'087/2019',\r\n" + 
						"'087/2019',\r\n" + 
						"'087/2019',\r\n" + 
						"'088/2019',\r\n" + 
						"'089/2019',\r\n" + 
						"'089/2019',\r\n" + 
						"'089/2019',\r\n" + 
						"'090/2019',\r\n" + 
						"'091/2019',\r\n" + 
						"'091/2019',\r\n" + 
						"'091/2019',\r\n" + 
						"'092/2019',\r\n" + 
						"'093/2019',\r\n" + 
						"'094/2019',\r\n" + 
						"'094/2019',\r\n" + 
						"'094/2019',\r\n" + 
						"'095/2019',\r\n" + 
						"'096/2019',\r\n" + 
						"'105/2019',\r\n" + 
						"'106/2019',\r\n" + 
						"'107/2019',\r\n" + 
						"'107/2019',\r\n" + 
						"'107/2019',\r\n" + 
						"'108/2019',\r\n" + 
						"'108/2019',\r\n" + 
						"'108/2019',\r\n" + 
						"'109/2019',\r\n" + 
						"'110/2019',\r\n" + 
						"'113/2019',\r\n" + 
						"'114/2019',\r\n" + 
						"'114/2019',\r\n" + 
						"'114/2019',\r\n" + 
						"'115/2019',\r\n" + 
						"'116/2019',\r\n" + 
						"'116/2019',\r\n" + 
						"'116/2019',\r\n" + 
						"'117/2019',\r\n" + 
						"'118/2019',\r\n" + 
						"'118/2019',\r\n" + 
						"'118/2019',\r\n" + 
						"'124/2019',\r\n" + 
						"'127/2019',\r\n" + 
						"'129/2019',\r\n" + 
						"'132/2019',\r\n" + 
						"'133/2019',\r\n" + 
						"'134/2019',\r\n" + 
						"'135/2019',\r\n" + 
						"'136/2019',\r\n" + 
						"'137/2019',\r\n" + 
						"'138/2019',\r\n" + 
						"'140/2019',\r\n" + 
						"'141/2019',\r\n" + 
						"'142/2019',\r\n" + 
						"'143/2019',\r\n" + 
						"'144/2019',\r\n" + 
						"'145/2019',\r\n" + 
						"'146/2019',\r\n" + 
						"'147/2019',\r\n" + 
						"'148/2019',\r\n" + 
						"'149/2019',\r\n" + 
						"'157/2019',\r\n" + 
						"'158/2019',\r\n" + 
						"'162/2019',\r\n" + 
						"'163/2019',\r\n" + 
						"'170/2019',\r\n" + 
						"'174/2019',\r\n" + 
						"'178/2019',\r\n" + 
						"'179/2019',\r\n" + 
						"'180/2019',\r\n" + 
						"'181/2019',\r\n" + 
						"'182/2019',\r\n" + 
						"'183/2019',\r\n" + 
						"'184/2019',\r\n" + 
						"'189/2019',\r\n" + 
						"'190/2019',\r\n" + 
						"'191/2019',\r\n" + 
						"'192/2019',\r\n" + 
						"'196/2019',\r\n" + 
						"'199/2019',\r\n" + 
						"'200/2019',\r\n" + 
						"'206/2019',\r\n" + 
						"'207/2019',\r\n" + 
						"'208/2019',\r\n" + 
						"'210/2019',\r\n" + 
						"'211/2019',\r\n" + 
						"'212/2019',\r\n" + 
						"'213/2019',\r\n" + 
						"'214/2019',\r\n" + 
						"'215/2019',\r\n" + 
						"'218/2019',\r\n" + 
						"'220/2019',\r\n" + 
						"'224/2019',\r\n" + 
						"'226/2019',\r\n" + 
						"'227/2019',\r\n" + 
						"'231/2019',\r\n" + 
						"'232/2019',\r\n" + 
						"'233/2019',\r\n" + 
						"'234/2019',\r\n" + 
						"'237/2019',\r\n" + 
						"'238/2019',\r\n" + 
						"'242/2019',\r\n" + 
						"'243/2019',\r\n" + 
						"'244/2019',\r\n" + 
						"'245/2019',\r\n" + 
						"'246/2019',\r\n" + 
						"'247/2019',\r\n" + 
						"'248/2019',\r\n" + 
						"'251/2019',\r\n" + 
						"'254/2019',\r\n" + 
						"'268/2019',\r\n" + 
						"'276/2019',\r\n" + 
						"'277/2019',\r\n" + 
						"'280/2019',\r\n" + 
						"'286/2019',\r\n" + 
						"'287/2019',\r\n" + 
						"'300/2019',\r\n" + 
						"'301/2019',\r\n" + 
						"'302/2019',\r\n" + 
						"'305/2019',\r\n" + 
						"'306/2019',\r\n" + 
						"'312/2019',\r\n" + 
						"'318/2019',\r\n" + 
						"'323/2019',\r\n" + 
						"'324/2019',\r\n" + 
						"'325/2019',\r\n" + 
						"'326/2019',\r\n" + 
						"'327/2019',\r\n" + 
						"'328/2019',\r\n" + 
						"'332/2019',\r\n" + 
						"'333/2019',\r\n" + 
						"'336/2019',\r\n" + 
						"'337/2019',\r\n" + 
						"'338/2019',\r\n" + 
						"'340/2019',\r\n" + 
						"'341/2019',\r\n" + 
						"'344/2019',\r\n" + 
						"'345/2019',\r\n" + 
						"'346/2019',\r\n" + 
						"'349/2019',\r\n" + 
						"'350/2019',\r\n" + 
						"'351/2019',\r\n" + 
						"'352/2019',\r\n" + 
						"'353/2019',\r\n" + 
						"'354/2019',\r\n" + 
						"'355/2019',\r\n" + 
						"'356/2019',\r\n" + 
						"'357/2019',\r\n" + 
						"'358/2019',\r\n" + 
						"'359/2019',\r\n" + 
						"'360/2019',\r\n" + 
						"'363/2019',\r\n" + 
						"'367/2019',\r\n" + 
						"'371/2019',\r\n" + 
						"'373/2019',\r\n" + 
						"'375/2019',\r\n" + 
						"'380/2019',\r\n" + 
						"'383/2019',\r\n" + 
						"'394/2019',\r\n" + 
						"'398/2019',\r\n" + 
						"'399/2019',\r\n" + 
						"'408/2019',\r\n" + 
						"'409/2019',\r\n" + 
						"'410/2019',\r\n" + 
						"'411/2019',\r\n" + 
						"'413/2019',\r\n" + 
						"'414/2019',\r\n" + 
						"'415/2019',\r\n" + 
						"'416/2019',\r\n" + 
						"'421/2019',\r\n" + 
						"'423/2019',\r\n" + 
						"'424/2019',\r\n" + 
						"'428/2019',\r\n" + 
						"'429/2019',\r\n" + 
						"'434/2019',\r\n" + 
						"'439/2019',\r\n" + 
						"'442/2019',\r\n" + 
						"'444/2019',\r\n" + 
						"'445/2019',\r\n" + 
						"'447/2019',\r\n" + 
						"'449/2019',\r\n" + 
						"'450/2019',\r\n" + 
						"'453/2019',\r\n" + 
						"'455/2019',\r\n" + 
						"'457/2019',\r\n" + 
						"'458/2019',\r\n" + 
						"'460/2019',\r\n" + 
						"'461/2019',\r\n" + 
						"'464/2019',\r\n" + 
						"'467/2019',\r\n" + 
						"'473/2019',\r\n" + 
						"'476/2019',\r\n" + 
						"'481/2019',\r\n" + 
						"'482/2019',\r\n" + 
						"'483/2019',\r\n" + 
						"'488/2019',\r\n" + 
						"'490/2019',\r\n" + 
						"'493/2019',\r\n" + 
						"'499/2019',\r\n" + 
						"'502/2019',\r\n" + 
						"'506/2019',\r\n" + 
						"'511/2019',\r\n" + 
						"'517/2019',\r\n" + 
						"'520/2019',\r\n" + 
						"'526/2019',\r\n" + 
						"'528/2019',\r\n" + 
						"'529/2019',\r\n" + 
						"'530/2019',\r\n" + 
						"'539/2019',\r\n" + 
						"'542/2019',\r\n" + 
						"'545/2019',\r\n" + 
						"'549/2019',\r\n" + 
						"'550/2019',\r\n" + 
						"'551/2019',\r\n" + 
						"'557/2019',\r\n" + 
						"'558/2019',\r\n" + 
						"'560/2019',\r\n" + 
						"'562/2019',\r\n" + 
						"'565/2019',\r\n" + 
						"'569/2019',\r\n" + 
						"'571/2019',\r\n" + 
						"'573/2019',\r\n" + 
						"'575/2019',\r\n" + 
						"'576/2019',\r\n" + 
						"'582/2019',\r\n" + 
						"'586/2019',\r\n" + 
						"'588/2019',\r\n" + 
						"'592/2019',\r\n" + 
						"'595/2019',\r\n" + 
						"'597/2019',\r\n" + 
						"'598/2019',\r\n" + 
						"'605/2019',\r\n" + 
						"'612/2019',\r\n" + 
						"'618/2019',\r\n" + 
						"'619/2019',\r\n" + 
						"'620/2019',\r\n" + 
						"'621/2019',\r\n" + 
						"'622/2019',\r\n" + 
						"'623/2019')");
			
				//query.setParameter("du", du);
				//query.setParameter("au", au);
				
				
				return  query.list();
}
	
	
	
	
	
	
	@Override
	public List<Object[]> listeFactureServiceClientAvecOuSansICE(java.util.Date dateDebut,java.util.Date dateFin , Depot depot , String ice) {
		// TODO Auto-generated method stub
		Query query=null;
		if(!ice.equals(""))
		{
			
			
			if(ice.equals(Constantes.CODE_OUI))
			{
				query= session.createQuery("select  d.dateFacture,d.numFacture, d.client.code,d.client.nom,d.client.ice,sum(d.montantHT),sum(d.montantTVA),sum(d.montantTTC)  from FactureServiceProduction d where (dateFacture between :dateDebut and :dateFin) and depot.id=:depot and d.client.ice is not null and d.client.ice <>'' group by numFacture order by dateFacture");

				
			}else if(ice.equals(Constantes.CODE_NON))
			{
				
				query= session.createQuery("select  d.dateFacture,d.numFacture, d.client.code,d.client.nom,d.client.ice,sum(d.montantHT),sum(d.montantTVA),sum(d.montantTTC)  from FactureServiceProduction d where (dateFacture between :dateDebut and :dateFin) and depot.id=:depot and (d.client.ice is null or d.client.ice ='') group by numFacture order by dateFacture");

				
			}
			
		
		}else
		{
			
			
			query= session.createQuery("select  d.dateFacture,d.numFacture, d.client.code,d.client.nom,d.client.ice,sum(d.montantHT),sum(d.montantTVA),sum(d.montantTTC)  from FactureServiceProduction d where (dateFacture between :dateDebut and :dateFin) and depot.id=:depot group by numFacture order by dateFacture");

		}

		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("depot", depot.getId());
		
		
		return query.list();

	}
	
	


}
