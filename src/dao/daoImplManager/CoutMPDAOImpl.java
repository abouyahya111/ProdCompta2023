package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CoutMPDAO;
import dao.entity.Articles;
import dao.entity.CoutMP;
import dao.entity.DetailEstimation;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.Production;

public class CoutMPDAOImpl implements CoutMPDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(CoutMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CoutMP edit(CoutMP e) {
		
	session.beginTransaction();
	CoutMP p= (CoutMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CoutMP p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<CoutMP> findAll() {
		return session.createQuery("select c from CoutMP c").list();
		}

	public CoutMP findById(int id) {
		return (CoutMP)session.get(CoutMP.class, id);
		}

	
	
	@Override
	public List<CoutMP> listeCoutMPByProductionByMagasin(Production production, Magasin magasin) {
		
		Query query= session.createQuery("select p from CoutMP p where prodcutionCM.id =:production and magasin.id=:magasin");
		query.setParameter("production", production.getId());
		query.setParameter("magasin", magasin.getId());
		return query.list();

	}
	
	@Override
	public List<CoutMP> listeCoutMPEmballageByProductionByMagasin(Production production, Magasin magasin) {
		
		Query query= session.createQuery("select p from CoutMP p where p.matierePremier.categorieMp.subCategorieMp.id<>1 and prodcutionCM.id =:production and magasin.id=:magasin");
		query.setParameter("production", production.getId());
		query.setParameter("magasin", magasin.getId());
		return query.list();

	}
	
	
	@Override
	public List<CoutMP> listeCoutMPByArticleByMagasin(Articles article, Magasin magasin) {
		
		Query query= session.createQuery("select p from CoutMP p where prodcutionCM.articles.id =:article and magasin.id=:magasin");
		query.setParameter("article", article.getId());
		query.setParameter("magasin", magasin.getId());
		return query.list();

	}
	
	@Override
	public List<CoutMP> listeCoutMPEnVracByArticleByMagasin(Articles article, Magasin magasin) {
		
		Query query= session.createQuery("select p from CoutMP p where  p.matierePremier.categorieMp.subCategorieMp.id=1 and prodcutionCM.articles.id =:article and magasin.id=:magasin");
		query.setParameter("article", article.getId());
		query.setParameter("magasin", magasin.getId());
		return query.list();

	}
	
	@Override
	public List<CoutMP> listeCoutMPByProduction(Production production) {
		
		Query query= session.createQuery("select p from CoutMP p where  p.prodcutionCM.id =:production");
		query.setParameter("production", production.getId());
		 
		return query.list();

	}
	
	
	@Override
	public List<Object[]> EtatTonnageProductionParMoisParMagasin( Date dateDebut, Date dateFin) {
		// TODO Auto-generated method stub
		Query query=null;
		 
				query= session.createQuery("select month(c.prodcutionCM.date), sum(c.quantConsomme),c.magasin.libelle  from CoutMP c  where  c.matierePremier.categorieMp.subCategorieMp.id=1 and c.prodcutionCM.statut='"+Constantes.ETAT_OF_TERMINER+"' and c.prodcutionCM.date between :dateDebut and:dateFin group by month(c.prodcutionCM.date),c.magasin.libelle");

				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
			
		 
		
		return query.list();

	}
	
	
	
	
	public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}
	
	
	
	
	

	


}
