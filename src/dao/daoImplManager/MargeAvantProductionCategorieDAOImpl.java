package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.MargeAvantProductionCategorieDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Magasin;
import dao.entity.MargeAvantProductionCategorieMP;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;

public class MargeAvantProductionCategorieDAOImpl implements  MargeAvantProductionCategorieDAO {
	//Session session=ProdLauncher.session;
	Session session=ProdLauncher.session;

	public void add(MargeAvantProductionCategorieMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public MargeAvantProductionCategorieMP edit(MargeAvantProductionCategorieMP e) {
		
	session.beginTransaction();
	MargeAvantProductionCategorieMP p= (MargeAvantProductionCategorieMP)session.merge(e);
	
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		MargeAvantProductionCategorieMP p= findById(id);
		session.delete(p);
		
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<MargeAvantProductionCategorieMP> findAll() {
		return session.createQuery("select c from MargeAvantProductionCategorie c").list();
		
		}

	public MargeAvantProductionCategorieMP findById(int id) {
		return (MargeAvantProductionCategorieMP)session.get(MargeAvantProductionCategorieMP.class, id);
		}
	

	

	
	
	
	
	
	
public void ViderSession()
{
	if(session!=null)
	{
		session.clear();
	}
}
	
	
	

}
