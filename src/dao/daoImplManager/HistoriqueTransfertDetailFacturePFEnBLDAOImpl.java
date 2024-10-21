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
import dao.daoManager.HistoriqueTransfertDetailFacturePFEnBLDAO;
import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFacturePF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.HistoriqueTransfertDetailFacturePFEnBL;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public class HistoriqueTransfertDetailFacturePFEnBLDAOImpl implements HistoriqueTransfertDetailFacturePFEnBLDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(HistoriqueTransfertDetailFacturePFEnBL e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public HistoriqueTransfertDetailFacturePFEnBL edit(HistoriqueTransfertDetailFacturePFEnBL e) {
		
	session.beginTransaction();
	HistoriqueTransfertDetailFacturePFEnBL p= (HistoriqueTransfertDetailFacturePFEnBL)session.merge(e);
	session.getTransaction().commit();
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		HistoriqueTransfertDetailFacturePFEnBL p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<HistoriqueTransfertDetailFacturePFEnBL> findAll() {
		return session.createQuery("select c from HistoriqueTransfertDetailFacturePFEnBL c").list();
		}

	public HistoriqueTransfertDetailFacturePFEnBL findById(int id) {
		return (HistoriqueTransfertDetailFacturePFEnBL)session.get(HistoriqueTransfertDetailFacturePFEnBL.class, id);
		}
	
	
	
	
	public List<HistoriqueTransfertDetailFacturePFEnBL> listeDetailFacturePFByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from HistoriqueTransfertDetailFacturePFEnBL c where facturePF.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	
	public List<HistoriqueTransfertDetailFacturePFEnBL> listeDetailFacturePFByNumFacture(String Numfacture, Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from HistoriqueTransfertDetailFacturePFEnBL c where facturePF.id in (select id from HistoriqueTransfertFacturePFEnBL where numFacture=:Numfacture and magasin.id=:magasin)");
		query.setParameter("Numfacture", Numfacture);
		query.setParameter("magasin", magasin.getId());
		return query.list();
		
		
	}
	
	
	
	
	public List<HistoriqueTransfertDetailFacturePFEnBL> listeDetailFacturePFByDate(Date dateDebut,Date dateFin , Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from HistoriqueTransfertDetailFacturePFEnBL c where facturePF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		return query.list();

	}
	
	
	public List<HistoriqueTransfertDetailFacturePFEnBL> listeDetailFacturePFByRequete(String requete) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from HistoriqueTransfertDetailFacturePFEnBL c "+requete +" order by c.facturePF.numFacture");
		
		return query.list();

	}
	
	
	
	
// la Facture/BL par article
	

	public List<HistoriqueTransfertDetailFacturePFEnBL> listeDetailFacturePFByArticle(Date dateDebut,Date dateFin , Articles article, SousFamilleArticlePF sousfamille , Magasin magasin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from HistoriqueTransfertDetailFacturePFEnBL c where facturePF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin and article.id=:article and sousFamille.id=:sousfamille");
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
