package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.TransferStockInitialMPVersAutreMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Depot;
import dao.entity.TransferStockInitialMPVersStockInitialAutreMP;
import dao.entity.TransferStockMP;

public class TransferStockInitialMPVersAutreMPDAOImpl implements TransferStockInitialMPVersAutreMPDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(TransferStockInitialMPVersStockInitialAutreMP e) {
		
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		//return p;
	}

	public TransferStockInitialMPVersStockInitialAutreMP edit(TransferStockInitialMPVersStockInitialAutreMP e) {
		
	session.beginTransaction();
	TransferStockInitialMPVersStockInitialAutreMP p= (TransferStockInitialMPVersStockInitialAutreMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		TransferStockInitialMPVersStockInitialAutreMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<TransferStockInitialMPVersStockInitialAutreMP> findAll() {
		return session.createQuery("select c from TransferStockInitialMPVersStockInitialAutreMP c").list();
		}

	public TransferStockInitialMPVersStockInitialAutreMP findById(int id) {
		return (TransferStockInitialMPVersStockInitialAutreMP)session.get(TransferStockMP.class, id);
		}
	
	@Override
	public int maxIdStock() {
		// TODO Auto-generated method stub
		int id =0;
		Query query= session.createQuery("select max(id) from TransferStockInitialMPVersStockInitialAutreMP");
		
		if(query.uniqueResult()==null)
			return id;
		else 
			id= (int)query.uniqueResult();
		
		return id;
	}
	
	
	public TransferStockInitialMPVersStockInitialAutreMP findTransferByCode(String codeTransfer) {
		
		
		Query query= session.createQuery("select c from TransferStockInitialMPVersStockInitialAutreMP c where CodeTransfer=:codeTransfer");
		query.setParameter("codeTransfer", codeTransfer);
		
		return (TransferStockInitialMPVersStockInitialAutreMP)query.uniqueResult();
		}
	
	
	
	


}
