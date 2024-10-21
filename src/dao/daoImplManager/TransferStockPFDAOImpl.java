package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.Magasin;
import dao.entity.TransferStockPF;

public class TransferStockPFDAOImpl implements TransferStockPFDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(TransferStockPF e) {
		
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public TransferStockPF edit(TransferStockPF e) {
		
	session.beginTransaction();
	TransferStockPF p= (TransferStockPF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		TransferStockPF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<TransferStockPF> findAll() {
		return session.createQuery("select c from TransferStockPF c").list();
		}

	public TransferStockPF findById(int id) {
		return (TransferStockPF)session.get(TransferStockPF.class, id);
		}
	
	public TransferStockPF findByCodeTransfert(String codeTransfert) {
		Query query= session.createQuery("select c from TransferStockPF c where CodeTransfer=:codeTransfert");
		query.setParameter("codeTransfert", codeTransfert);
		
		return (TransferStockPF)query.uniqueResult();
		}
	
	
	public TransferStockPF findByCodeTransfertByStatut(String codeTransfert , String statut) {
		Query query= session.createQuery("select c from TransferStockPF c where CodeTransfer=:codeTransfert and statut=:statut");
		query.setParameter("codeTransfert", codeTransfert);
		query.setParameter("statut", statut);
		return (TransferStockPF)query.uniqueResult();
		}
	
	
	
	public void deleteObject(TransferStockPF p) {
		
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		
	}
	


	
	


}
