package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.daoManager.SousFamilleEnVracDAO;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;
import util.HibernateUtil;


public class SousFamilleEnVracDAOImpl implements SousFamilleEnVracDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(SousFamilleEnVrac e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public SousFamilleEnVrac edit(SousFamilleEnVrac e) {
		
	session.beginTransaction();
	SousFamilleEnVrac p= (SousFamilleEnVrac)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		SousFamilleEnVrac p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<SousFamilleEnVrac> findAll() {
		return session.createQuery("select c from SousFamilleEnVrac c").list();
		}

	public SousFamilleEnVrac findById(int id) {
		return (SousFamilleEnVrac)session.get(SousFamilleEnVrac.class, id);
		}

	public SousFamilleEnVrac findByMP(MatierePremier mp) {
		
		SousFamilleEnVrac SousFamileArticlepf= new SousFamilleEnVrac();
		Query query= session.createQuery("select c from SousFamilleEnVrac c where matierePremier.id=:mp");
		query.setParameter("mp", mp.getId());
		
		SousFamileArticlepf= (SousFamilleEnVrac) query.uniqueResult();
		
		return SousFamileArticlepf;
		
		
		
	}
	
	
	

	@Override
	public List<SousFamilleEnVrac> listeMatierePremierByFamille(int idFamileArticlePF) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from SousFamilleEnVrac c where sousfamile.famileArticlePF.id=:idFamileArticlePF");
		query.setParameter("idFamileArticlePF", idFamileArticlePF);
		return query.list();
		
		
	}
	
	
	@Override
	public List<SousFamilleEnVrac> listeMatierePremierBySousFamille(int idSousFamileArticlePF) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from SousFamilleEnVrac c where sousfamile.id=:idSousFamileArticlePF");
		query.setParameter("idSousFamileArticlePF", idSousFamileArticlePF);
		return query.list();
		
		
	}
	
	
	

}
