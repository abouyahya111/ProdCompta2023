package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DetailProdGenDAO;
import dao.entity.DetailProdGen;

public class DetailProdGenDAOImpl implements DetailProdGenDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailProdGen e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailProdGen edit(DetailProdGen e) {
		
	session.beginTransaction();
	DetailProdGen p= (DetailProdGen)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailProdGen p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailProdGen> findAll() {
		return session.createQuery("select c from DetailProdGen c").list();
		}

	public DetailProdGen findById(int id) {
		return (DetailProdGen)session.get(DetailProdGen.class, id);
		}
	

	@Override
	public List<DetailProdGen> findByDateProdPeriode(Date dateProd,String periode) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailProdGen d where dateProd=:dateProd and periode=:periode");
				query.setParameter("dateProd", dateProd);
				query.setParameter("periode", periode);
				
				return query.list();
}

	@Override
	public DetailProdGen findByDateProdPeriodeEmploye(Date dateProd,
			String periode, int idEmploye) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailProdGen d where dateProd=:dateProd and periode=:periode and employe.id=:idEmploye");
				query.setParameter("dateProd", dateProd);
				query.setParameter("periode", periode);
				query.setParameter("idEmploye", idEmploye);
				
				return (DetailProdGen) query.uniqueResult();
}

	

	

}
