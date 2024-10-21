package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DetailFactureAchatDAO;
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureVenteMPDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.Articles;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAchatMP;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureVenteMP;
import dao.entity.DetailTransferStockMP;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;

public class DetailFactureVenteMPDAOImpl implements DetailFactureVenteMPDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailFactureVenteMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailFactureVenteMP edit(DetailFactureVenteMP e) {
		
	session.beginTransaction();
	DetailFactureVenteMP p= (DetailFactureVenteMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailFactureVenteMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}
	
public void delete(DetailFactureVenteMP p) {
		
		session.beginTransaction();	
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailFactureVenteMP> findAll() {
		return session.createQuery("select c from DetailFactureVenteMP c").list();
		}

	public DetailFactureVenteMP findById(int id) {
		return (DetailFactureVenteMP)session.get(DetailFactureVenteMP.class, id);
		}
	
	
	
	@Override
	public List<DetailFactureVenteMP> listeDetailFactureVenteByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureVenteMP c where factureVenteMP.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	@Override
	public List<DetailFactureVenteMP> listeDetailFactureVenteByDate(Date dateDebut,Date dateFin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureVenteMP c where factureVenteMP.dateFacture between :dateDebut and :dateFin ");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		
		return query.list();

	}
	
	
	
	


}
