package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.CategorieMp;
import dao.entity.SubCategorieMp;

public class SubCategorieMPAOImpl implements SubCategorieMPDAO {
	
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(SubCategorieMp e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public SubCategorieMp edit(SubCategorieMp e) {
		
	session.beginTransaction();
	SubCategorieMp p= (SubCategorieMp)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(Long id) {
		
		session.beginTransaction();
		SubCategorieMp p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<SubCategorieMp> findAll() {
		return session.createQuery("select c from SubCategorieMp c").list();
		}

	public SubCategorieMp findById(Long id) {
		return (SubCategorieMp)session.get(SubCategorieMp.class, id);
		}
	
	@Override
	public SubCategorieMp findByCode(String code) {
		Query query= session.createQuery("select c from SubCategorieMp c where code=:code");
		query.setParameter("code", code);
		return (SubCategorieMp) query.uniqueResult();
		}
	
	
	

}
