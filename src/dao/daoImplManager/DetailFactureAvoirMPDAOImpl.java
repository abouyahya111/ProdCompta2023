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
import dao.daoManager.DetailFactureAvoirMPDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.entity.Articles;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAchatMP;
import dao.entity.DetailFactureAvoirMP;
import dao.entity.DetailFacturePF;
import dao.entity.DetailTransferStockMP;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;

public class DetailFactureAvoirMPDAOImpl implements DetailFactureAvoirMPDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailFactureAvoirMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailFactureAvoirMP edit(DetailFactureAvoirMP e) {
		
	session.beginTransaction();
	DetailFactureAvoirMP p= (DetailFactureAvoirMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailFactureAvoirMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}
	
public void delete(DetailFactureAvoirMP p) {
		
		session.beginTransaction();	
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailFactureAvoirMP> findAll() {
		return session.createQuery("select c from DetailFactureAvoirMP c").list();
		}

	public DetailFactureAvoirMP findById(int id) {
		return (DetailFactureAvoirMP)session.get(DetailFactureAvoirMP.class, id);
		}
	
	
	
	@Override
	public List<DetailFactureAvoirMP> listeDetailFactureAvoirByFacture(int idFacture) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAvoirMP c where factureAvoirMP.id=:idFacture");
		query.setParameter("idFacture", idFacture);
		
		return query.list();
		
		
	}
	
	@Override
	public List<DetailFactureAvoirMP> listeDetailFactureAvoirByDate(Date dateDebut,Date dateFin) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailFactureAvoirMP c where factureAvoirMP.dateFacture between :dateDebut and :dateFin ");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		
		return query.list();

	}
	
	
	
	


}
