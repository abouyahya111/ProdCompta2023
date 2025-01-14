package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.EmployeDAO;
import dao.entity.Employe;

public class EmployeDAOImpl implements EmployeDAO {
//	Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(Employe e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Employe edit(Employe e) {
		
	session.beginTransaction();
	Employe p= (Employe)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Employe p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Employe> findAll() {
		Query query= session.createQuery("select c from Employe c where actif=:actif");
		query.setParameter("actif", true);
		
		return query.list();
		}
	public List<Employe> findByDepot(String depot) {
		Query query= session.createQuery("select c from Employe c where actif=:actif and c.depot.code=:depot");
		query.setParameter("actif", true);
		query.setParameter("depot", depot);
		return query.list();
		}

	public Employe findById(int id) {
		return (Employe)session.get(Employe.class, id);
		}

	@Override
	public Employe findByCode(String code, String numDossier,int depot) {
		// TODO Auto-generated method stub
		
		Employe articles= new Employe();
		Query query= session.createQuery("select c from Employe c where (numDossier=:numDossier or matricule=:code) and actif=:actif and ID_DEPOT=:depot");
		query.setParameter("code", code);
		query.setParameter("numDossier", numDossier);
		query.setParameter("actif", true);
		query.setParameter("depot", depot);
		
		articles= (Employe) query.uniqueResult();
		
		return articles;
	}
	
	public List<Employe> findEmployeByType(String codeType,String depot) {
		Query query= session.createQuery("select c from Employe c where typeResEmploye.code=:codeType and actif=:actif and Employe.Depot.code=:depot");
		query.setParameter("actif", true);
		query.setParameter("codeType",codeType);
		query.setParameter("depot", depot);
		return query.list();
		}
	
	public List<Employe> findAutreEmployeByType(String codeType) {
		Query query= session.createQuery("select c from Employe c where typeResEmploye.code=:codeType and actif=:actif and c.equipe is null");
		query.setParameter("actif", true);
		query.setParameter("codeType",codeType);
		
		return query.list();
		}
	
	public List<Employe> findAutreEmploye() {
		Query query= session.createQuery("select c from Employe c where actif=:actif and c.equipe is null");
		query.setParameter("actif", true);
		
		return query.list();
		}
	
	
	
	public List<Employe> findByNUmDossier_Matricule_Nom(String numdossier,String matricule,String nom) {
		Query query= session.createQuery("select c from Employe c where numDossier like:numdossier and matricule like:matricule and nom like:nom");
		query.setParameter("numdossier", numdossier.trim()+"%");
		query.setParameter("matricule", matricule.trim()+"%");
		query.setParameter("nom", nom.trim()+"%");
		
		return query.list();
		}


}
