package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.MatierePremiereDAO;
import dao.entity.CategorieMp;
import dao.entity.MatierePremier;

public class MatierePremierDAOImpl implements MatierePremiereDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(MatierePremier e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public MatierePremier edit(MatierePremier e) {
		
	session.beginTransaction();
	MatierePremier p= (MatierePremier)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		MatierePremier p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<MatierePremier> findAll() {
		return session.createQuery("select c from MatierePremier c").list();
		}

	public MatierePremier findById(int id) {
		return (MatierePremier)session.get(MatierePremier.class, id);
		}

	@Override
	public MatierePremier findByCode(String code) {
		// TODO Auto-generated method stub
		MatierePremier matierPremiere= new MatierePremier();
		Query query= session.createQuery("select c from MatierePremier c where code=:code");
		query.setParameter("code", code);
		
		matierPremiere= (MatierePremier) query.uniqueResult();
		
		return matierPremiere;
	}

	@Override
	public List<MatierePremier> findMatierePremierSaufCatTHE() {
		// TODO Auto-generated method stub
		return session.createQuery("select c from MatierePremier c where categorieMp.id <>1 and categorieMp.id <> 2").list();
	}

	@Override
	public List<CategorieMp> listeCategorieTHE() {
		// TODO Auto-generated method stub
		return session.createQuery("select c from CategorieMp c where code ='CH001' or code = 'HB001'").list();
	}

	@Override
	public List<MatierePremier> listeMatierePremierByCategorie(String codeCat) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from MatierePremier c where categorieMp.code=:codeCat");
		query.setParameter("codeCat", codeCat);
		
		return query.list();
		
	}
	
	@Override
	public List<MatierePremier> listeMatierePremierBySousCategorie(int idsubcategorie) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from MatierePremier c where categorieMp.subCategorieMp.id=:idsubcategorie");
		query.setParameter("idsubcategorie", idsubcategorie);
		
		return query.list();
		
	}
	
	
	
	@Override
	public List<MatierePremier> listeMatierePremierByidcategorie(int idCat) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from MatierePremier c where id_cat=:idCat");
		query.setParameter("idCat", idCat);
		
		return query.list();
		
	}
	
	@Override
	public List<MatierePremier> findMatierePremierCatTHE() {
		// TODO Auto-generated method stub
		return session.createQuery("select c from MatierePremier c where categorieMp.id =1 or categorieMp.id = 2").list();
	}
	
	
	

}
