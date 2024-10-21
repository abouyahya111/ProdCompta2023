package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ClientDAO;
import dao.daoManager.TypeBLDAO;
import dao.daoManager.VilleDAO;
import dao.entity.Client;
import dao.entity.Ville;
import dao.entity.Machine;
import dao.entity.TypeBL;
import dao.entity.Ville;

public class TypeBLDAOImpl implements TypeBLDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(TypeBL e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public TypeBL edit(TypeBL e) {
		
	session.beginTransaction();
	TypeBL p= (TypeBL)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		TypeBL p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<TypeBL> findAll() {
		return session.createQuery("select c from TypeBL c order by type ").list();
		}

	public TypeBL findById(int id) {
		return (TypeBL)session.get(TypeBL.class, id);
		}
	


	
 
	
	
	
	@Override
	public  TypeBL findTypeBLByType(String type) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from TypeBL c where type=:type");
		query.setParameter("type", type);
		
		
		return (TypeBL) query.uniqueResult();
	}
	
	
	
 
	
	
	
 



}
