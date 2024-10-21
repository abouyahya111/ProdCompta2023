package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Depot;
import dao.entity.TransferStockMP;

public class TransferStockMPDAOImpl implements TransferStockMPDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(TransferStockMP e) {
		
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		//return p;
	}

	public TransferStockMP edit(TransferStockMP e) {
		
	session.beginTransaction();
	TransferStockMP p= (TransferStockMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		TransferStockMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<TransferStockMP> findAll() {
		return session.createQuery("select c from TransferStockMP c").list();
		}

	public TransferStockMP findById(int id) {
		return (TransferStockMP)session.get(TransferStockMP.class, id);
		}
	
	@Override
	public int maxIdStock() {
		// TODO Auto-generated method stub
		int id =0;
		Query query= session.createQuery("select max(id) from TransferStockMP");
		
		if(query.uniqueResult()==null)
			return id;
		else 
			id= (int)query.uniqueResult();
		
		return id;
	}
	
	
	public TransferStockMP findTransferByCode(String codeTransfer) {
		
		
		Query query= session.createQuery("select c from TransferStockMP c where CodeTransfer=:codeTransfer");
		query.setParameter("codeTransfer", codeTransfer);
		
		return (TransferStockMP)query.uniqueResult();
		}
	
	
	public TransferStockMP findTransferByCodeStatut(String codeTransfer , String statut) {
		
		
		Query query= session.createQuery("select c from TransferStockMP c where CodeTransfer=:codeTransfer and statut=:statut");
		query.setParameter("codeTransfer", codeTransfer);
		query.setParameter("statut", statut);
		return (TransferStockMP)query.uniqueResult();
		}
	
	
	public List<TransferStockMP> findTransferByStatut(String statut,Date dateDebut , Date dateFin , Depot depot) {
		
		
		Query query= session.createQuery("select c from TransferStockMP c where statut=:statut and dateTransfer between :dateDebut and :dateFin and depot=:depot");
		query.setParameter("statut", statut);
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("depot", depot);
		
		return query.list();
		}
	
public List<TransferStockMP> findTransferByStatutChargeouService(String charge,String service) {
		
		
		Query query= session.createQuery("select c from TransferStockMP c where statut=:charge or statut=:service");
		query.setParameter("charge", charge);
		query.setParameter("service", service);
		
		return query.list();
		}
	
	


}
