package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.HistoriqueTransfertDetailFactureAvoirClientPFEnBLDAO;
import dao.daoManager.HistoriqueTransfertDetailFacturePFEnBLDAO;
import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFacturePF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.HistoriqueTransfertDetailFactureAvoirClientPFEnBL;
import dao.entity.HistoriqueTransfertDetailFacturePFEnBL;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public class HistoriqueTransfertDetailFactureAvoirClientPFEnBLDAOImpl implements HistoriqueTransfertDetailFactureAvoirClientPFEnBLDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(HistoriqueTransfertDetailFactureAvoirClientPFEnBL e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public HistoriqueTransfertDetailFactureAvoirClientPFEnBL edit(HistoriqueTransfertDetailFactureAvoirClientPFEnBL e) {
		
	session.beginTransaction();
	HistoriqueTransfertDetailFactureAvoirClientPFEnBL p= (HistoriqueTransfertDetailFactureAvoirClientPFEnBL)session.merge(e);
	session.getTransaction().commit();
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		HistoriqueTransfertDetailFactureAvoirClientPFEnBL p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<HistoriqueTransfertDetailFactureAvoirClientPFEnBL> findAll() {
		return session.createQuery("select c from HistoriqueTransfertDetailFactureAvoirClientPFEnBL c").list();
		}

	public HistoriqueTransfertDetailFactureAvoirClientPFEnBL findById(int id) {
		return (HistoriqueTransfertDetailFactureAvoirClientPFEnBL)session.get(HistoriqueTransfertDetailFactureAvoirClientPFEnBL.class, id);
		}
	
	
	
	
	public List<HistoriqueTransfertDetailFactureAvoirClientPFEnBL> listeDetailFactureAvoirClientPFByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from HistoriqueTransfertDetailFactureAvoirClientPFEnBL c where factureAvoirClientPF.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	
	public List<HistoriqueTransfertDetailFactureAvoirClientPFEnBL> listeDetailFactureAvoirClientPFByNumFacture(String Numfacture, Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from HistoriqueTransfertDetailFactureAvoirClientPFEnBL c where factureAvoirClientPF.id in (select id from HistoriqueTransfertFactureAvoirClientPFEnBL where numFacture=:Numfacture and magasin.id=:magasin)");
		query.setParameter("Numfacture", Numfacture);
		query.setParameter("magasin", magasin.getId());
		return query.list();
		
		
	}
	
	
	
	
	public List<HistoriqueTransfertDetailFactureAvoirClientPFEnBL> listeDetailFactureAvoirClientPFByDate(Date dateDebut,Date dateFin , Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from HistoriqueTransfertDetailFactureAvoirClientPFEnBL c where factureAvoirClientPF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		return query.list();

	}
	
	
	public List<HistoriqueTransfertDetailFactureAvoirClientPFEnBL> listeDetailFactureAvoirClientPFByRequete(String requete) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from HistoriqueTransfertDetailFactureAvoirClientPFEnBL c "+requete +" order by c.factureAvoirClientPF.numFacture");
		
		return query.list();

	}
	
	
	
	
// la Facture/BL par article
	

	public List<HistoriqueTransfertDetailFactureAvoirClientPFEnBL> listeDetailFactureAvoirClientPFByArticle(Date dateDebut,Date dateFin , Articles article, SousFamilleArticlePF sousfamille , Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from HistoriqueTransfertDetailFactureAvoirClientPFEnBL c where factureAvoirClientPF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin and article.id=:article and sousFamille.id=:sousfamille");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		query.setParameter("sousfamille", sousfamille.getId());
		query.setParameter("article", article.getId());
		
		return query.list();

	}
	


	
	

	
	
	public void ViderSession()
	{
		
		if (session!=null)
		{
			session.clear();
			
		}
	
	}
	
	
	
	
	
	
	
	
	
	

}
