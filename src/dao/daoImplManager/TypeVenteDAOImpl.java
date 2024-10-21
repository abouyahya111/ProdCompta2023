package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.TypeVente;

public class TypeVenteDAOImpl implements TypeVenteDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	    @Override
	    public TypeVente findById(int id) {

	return (TypeVente)session.get(TypeVente.class, id);    }

	    @Override
	    public void add(TypeVente typeVente) {

	               session.beginTransaction();
			session.save(typeVente);
			
			session.getTransaction().commit();    }

	    @Override
	    public TypeVente edit(TypeVente e) {

	session.beginTransaction();
		TypeVente p= (TypeVente)session.merge(e);
		session.getTransaction().commit();
			return p;

	    }

	    @Override
	    public void delete(TypeVente e) {

	        session.beginTransaction();
			session.delete(e);
			session.getTransaction().commit();
	    }

	    @Override
	    public List<TypeVente> findAll() {

	return session.createQuery("select c from TypeVente c").list();
	    
	}

	      public TypeVente findTypeVenteByCodeTypeVente(String code) {
			
			Query query = session.createQuery("select u from TypeVente u where u.code=:code ");
	      query.setParameter("code",code);
	                return (TypeVente)query.uniqueResult();
	 }

	   
	  


}
