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
import dao.daoManager.HistoriqueTransfertFacturePFEnBLDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FactureVenteMP;
import dao.entity.HistoriqueTransfertFacturePFEnBL;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public class HistoriqueTransfertFacturePFEnBLDAOImpl implements HistoriqueTransfertFacturePFEnBLDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(HistoriqueTransfertFacturePFEnBL e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
		
	}

	public HistoriqueTransfertFacturePFEnBL edit(HistoriqueTransfertFacturePFEnBL e) {
		
	session.beginTransaction();
	HistoriqueTransfertFacturePFEnBL p= (HistoriqueTransfertFacturePFEnBL)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		HistoriqueTransfertFacturePFEnBL p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<HistoriqueTransfertFacturePFEnBL> findAll() {
		return session.createQuery("select c from HistoriqueTransfertFacturePFEnBL c").list();
		}
	
	

	public HistoriqueTransfertFacturePFEnBL findById(int id) {
		return (HistoriqueTransfertFacturePFEnBL)session.get(HistoriqueTransfertFacturePFEnBL.class, id);
		}
	
	public HistoriqueTransfertFacturePFEnBL findFacturePFByEtat(String etat) {
		Query query= session.createQuery("select c from HistoriqueTransfertFacturePFEnBL c where etat=:etat");
		query.setParameter("etat", etat);
		
		return (HistoriqueTransfertFacturePFEnBL) query.uniqueResult();
		
		}
	
	
	public HistoriqueTransfertFacturePFEnBL findFactureVentePFByNumBL(String NumBL) {
		Query query= session.createQuery("select c from HistoriqueTransfertFacturePFEnBL c where numBl=:NumBL");
		query.setParameter("NumBL", NumBL);
		
		return (HistoriqueTransfertFacturePFEnBL) query.uniqueResult();
		
		}
	
	
	
	
	public List<HistoriqueTransfertFacturePFEnBL> findByNumFacture(String NumFacture, Depot depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from HistoriqueTransfertFacturePFEnBL c where c.numFacture=:NumFacture and c.depot.id=:depot");
				query.setParameter("NumFacture", NumFacture);
				query.setParameter("depot", depot.getId());
				
				
				return  query.list();
}
	
	
	
	
	public List<HistoriqueTransfertFacturePFEnBL> findByClient(String codeclient) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from HistoriqueTransfertFacturePFEnBL c where c.clientPF.code=:codeclient");
				query.setParameter("codeclient", codeclient);
				
				
				return  query.list();
}
	
	
	//liste des BL de type facture ou Facturé par client
	public List<HistoriqueTransfertFacturePFEnBL> findFactureByClient(String codeclient) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from HistoriqueTransfertFacturePFEnBL c where c.clientPF.code=:codeclient and c.type !=:BL");
				query.setParameter("codeclient", codeclient);
				query.setParameter("BL", "BL");
				
				return  query.list();
}
	
	
	//liste des BL de type facture ou Facturé par client
	public List<HistoriqueTransfertFacturePFEnBL> findBLByClient(String codeclient) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from HistoriqueTransfertFacturePFEnBL c where c.clientPF.code=:codeclient and c.type =:BL");
				query.setParameter("codeclient", codeclient);
				query.setParameter("BL", "BL");
				
				return  query.list();
}
	
	public List<HistoriqueTransfertFacturePFEnBL> findByDate(java.util.Date datefacture) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from HistoriqueTransfertFacturePFEnBL c where c.dateFacture=:datefacture");
				query.setParameter("datefacture", datefacture);
				
				
				return  query.list();
}
	
	//liste des BL de type facture entre deux date
	public List<HistoriqueTransfertFacturePFEnBL> findFactureEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Depot depot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from HistoriqueTransfertFacturePFEnBL c where c.dateFacture between :dateDebut and :datedeFin and c.type !=:BL  and c.depot.id=:depot");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("datedeFin", datedeFin);
				query.setParameter("depot", depot.getId());
				query.setParameter("BL", "BL");
				return  query.list();
}
	

		
	
	public List<HistoriqueTransfertFacturePFEnBL> findByDepot(int iddepot) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from HistoriqueTransfertFacturePFEnBL c where c.depot.id=:iddepot");
				query.setParameter("iddepot", iddepot);
				
				
				return  query.list();
}

	


	
	
	
	public List<HistoriqueTransfertFacturePFEnBL> listeFacturePFEntreDeuxDatesOrderByDate(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from HistoriqueTransfertFacturePFEnBL c where magasin.id=:magasin c.dateFacture between :datedu and :dateAu order by c.dateFacture");
		query.setParameter("magasin", magasin.getId());
		query.setParameter("datedu", datedu);
		query.setParameter("dateAu", dateAu);
	
		
		return query.list();

	}
	
	
	public List<HistoriqueTransfertFacturePFEnBL> listeFacturePFEntreDeuxDatesOrderByNumFacture(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from HistoriqueTransfertFacturePFEnBL c where magasin.id=:magasin c.dateFacture between :datedu and :dateAu order by c.numFacture");
		query.setParameter("magasin", magasin.getId());
		query.setParameter("datedu", datedu);
		query.setParameter("dateAu", dateAu);
	
		
		return query.list();

	}
	
	
	public List<HistoriqueTransfertFacturePFEnBL> listeHistoriqueTransfertFacturePFEnBLByRequet(String req) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from HistoriqueTransfertFacturePFEnBL c where  "+req);			
		
		return query.list();

	}
	
	
	

}
