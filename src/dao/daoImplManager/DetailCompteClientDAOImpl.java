/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.daoImplManager;

import util.HibernateUtil;
import dao.entity.Charges;
import dao.entity.Client;
import dao.entity.CompteClient;
import dao.entity.Depot;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.FicheEmploye;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.daoManager.DetailCompteClientDAO;

/**
 *
 * @author Hp
 */
public class DetailCompteClientDAOImpl implements DetailCompteClientDAO {
    // Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;
  
     
     
     
     public DetailCompteClient findById(int id) {
		return (DetailCompteClient)session.get(DetailCompteClient.class, id);
		}

 
    
	public void add(DetailCompteClient detailCompteClient) {
		session.beginTransaction();
		session.save(detailCompteClient);
		
		session.getTransaction().commit();
		//return p;
	}


	public DetailCompteClient edit(DetailCompteClient e) {
		
	session.beginTransaction();
	DetailCompteClient p= (DetailCompteClient)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}


	public void delete(DetailCompteClient e) {
		
		session.beginTransaction();
		session.delete(e);
		session.getTransaction().commit();
		
	}

        
    public List<DetailCompteClient> findAll() {
              return session.createQuery("select c from DetailCompteClient c").list();
    }
    
    public List<DetailCompteClient> findAllByDepot(Depot depot) {
    	Query query= session.createQuery("select v from DetailCompteClient v where v.facturepf.depot.id =:depot ");	
		query.setParameter("depot", depot.getId());
		return  query.list();
         
}
    
    public DetailCompteClient findByFacture(int idfacture) {
		Query query= session.createQuery("select v from DetailCompteClient v where v.facturepf.id=:idfacture");	
		query.setParameter("idfacture", idfacture);
		return (DetailCompteClient)query.uniqueResult();
		}
    
    public DetailCompteClient findByFactureAvoirClient(int idfacture) {
 		Query query= session.createQuery("select v from DetailCompteClient v where v.factureAvoirClientpf.id=:idfacture");	
 		query.setParameter("idfacture", idfacture);
 		return (DetailCompteClient)query.uniqueResult();
 		}
    
    
    public DetailCompteClient findByFactureAutresVente(int idfacture) {
 		Query query= session.createQuery("select v from DetailCompteClient v where v.factureAutresVente.id=:idfacture");	
 		query.setParameter("idfacture", idfacture);
 		return (DetailCompteClient)query.uniqueResult();
 		}
    
    
	@Override
	public List<DetailCompteClient> findByFournisseurEtClient(CompteClient idcompteclient,Client idfournisseur) {
		
		// TODO Auto-generated method stub
		Query query = null;
		if(idcompteclient!=null && idfournisseur!=null)
		{
			 query= session.createQuery("select v from DetailCompteClient v where v.compteClient.id=:idcompteclient and v.fournisseur.id=:idfournisseur");	
				query.setParameter("idcompteclient", idcompteclient.getId());
				query.setParameter("idfournisseur", idfournisseur.getId());
		}else if(idcompteclient!=null && idfournisseur==null)
		{
			
			 query= session.createQuery("select v from DetailCompteClient v where v.compteClient.id=:idcompteclient");	
				query.setParameter("idcompteclient", idcompteclient.getId());
				
		}else if(idcompteclient==null && idfournisseur!=null)
		{
			
			 query= session.createQuery("select v from DetailCompteClient v where v.fournisseur.id=:idfournisseur");	
				
				query.setParameter("idfournisseur", idfournisseur.getId());
			
		}
		
				
		return  query.list();
}
    

     
}
