package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;

import dao.daoManager.MargeAvantProductionCategoriePFDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Magasin;
import dao.entity.MargeAvantProductionCategoriePF;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;

public class MargeAvantProductionCategoriePFDAOImpl implements  MargeAvantProductionCategoriePFDAO {
	//Session session=ProdLauncher.session;
	Session session=ProdLauncher.session;

	public void add(MargeAvantProductionCategoriePF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public MargeAvantProductionCategoriePF edit(MargeAvantProductionCategoriePF e) {
		
	session.beginTransaction();
	MargeAvantProductionCategoriePF p= (MargeAvantProductionCategoriePF)session.merge(e);
	
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		MargeAvantProductionCategoriePF p= findById(id);
		session.delete(p);
		
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<MargeAvantProductionCategoriePF> findAll() {
		return session.createQuery("select c from MargeAvantProductionCategoriePF c").list();
		
		}

	public MargeAvantProductionCategoriePF findById(int id) {
		return (MargeAvantProductionCategoriePF)session.get(MargeAvantProductionCategoriePF.class, id);
		}
	

	

	
	
	
	
	
	
public void ViderSession()
{
	if(session!=null)
	{
		session.clear();
	}
}
	
	
	

}
