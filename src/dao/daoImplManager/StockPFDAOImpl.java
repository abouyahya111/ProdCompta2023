package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.StockPFDAO;
import dao.entity.StockMP;
import dao.entity.StockPF;

public class StockPFDAOImpl implements StockPFDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(StockPF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public StockPF edit(StockPF e) {
		
	session.beginTransaction();
	StockPF p= (StockPF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		StockPF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<StockPF> findAll() {
		return session.createQuery("select c from StockPF c").list();
		}

	public StockPF findById(int id) {
		return (StockPF)session.get(StockPF.class, id);
		}

	

	@Override
	public StockPF findStockByArticle(int idArticle) {
		// TODO Auto-generated method stub
		StockPF stockMP= new StockPF();
		Query query= session.createQuery("select c from StockPF c where articles.id=:idArticle");
		query.setParameter("idArticle", idArticle);
		
		
		stockMP= (StockPF) query.uniqueResult();
		
		return stockMP;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StockPF> findStockProduitFiniByMagasin(int idMagasin) {

		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from StockPF c where magasin.id=:idMagasin");
		query.setParameter("idMagasin", idMagasin);
		
		
		return query.list();
		
	
	}
	
	public List<StockPF> findStockProduitFiniByMagasinGroupByArticle(int idMagasin) {

		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from StockPF c where magasin.id=:idMagasin group by articles.id");
		query.setParameter("idMagasin", idMagasin);
		
		
		return query.list();
		
	
	}

	
	@Override
	public List<StockPF> findSockNonVideByMagasin(int idMagasin) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Query query= session.createQuery("select c from StockPF c where stock <> 0 and magasin.id=:idMagasin");
				query.setParameter("idMagasin", idMagasin);
				return query.list();
	}
	
	@Override
	public StockPF findStockByMagasinPF(int idArticle, int idMagasin) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from StockPF c where articles.id=:idArticle and magasin.id=:idMagasin");
		query.setParameter("idMagasin", idMagasin);
		query.setParameter("idArticle", idArticle);
		
		
		return (StockPF) query.uniqueResult();
		
	}
	
	
	@Override
	public StockPF findStockByMagasinPFBySousFamille(int idArticle, int idMagasin,int sousfamille) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from StockPF c where articles.id=:idArticle and magasin.id=:idMagasin and sousFamille.id=:sousfamille");
		query.setParameter("idMagasin", idMagasin);
		query.setParameter("idArticle", idArticle);
		query.setParameter("sousfamille", sousfamille);
		
		return (StockPF) query.uniqueResult();
		
	}

	@Override
	public List<StockPF> findStockArticleByMagasinPFBySousFamille(int idMagasin,int sousfamille) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from StockPF c where magasin.id=:idMagasin and sousFamille.id=:sousfamille");
		query.setParameter("idMagasin", idMagasin);
		
		query.setParameter("sousfamille", sousfamille);
		
		return  query.list();
		
	}


	@Override
	public List<StockPF> findStockArticleByMagasinPFByFamille(int idMagasin,int famille) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from StockPF c where magasin.id=:idMagasin and sousFamille.famileArticlePF.id=:famille group by articles.id");
		query.setParameter("idMagasin", idMagasin);
		
		query.setParameter("famille", famille);
		
		return  query.list();
		
	}
	public void viderSessionStock()
	{
		if(session!=null)
		{
			session.clear();
		}
	}
	
	


}