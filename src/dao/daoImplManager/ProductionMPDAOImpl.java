package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ProductionDAO;
import dao.daoManager.ProductionMPDAO;
import dao.entity.CoutMP;
import dao.entity.CoutProdMP;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.DetailResponsableProd;
import dao.entity.Production;
import dao.entity.ProductionMP;

public class ProductionMPDAOImpl implements ProductionMPDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(ProductionMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public ProductionMP edit(ProductionMP e) {
		
	session.beginTransaction();
	ProductionMP p= (ProductionMP)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		ProductionMP p=findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<ProductionMP> findAll() {
		return session.createQuery("select c from ProductionMP c").list();
		}

	public ProductionMP findById(int id) {
		return (ProductionMP)session.get(ProductionMP.class, id);
		}

	@Override
	public int maxIdProductionMP() {
		// TODO Auto-generated method stub
		int id =0;
		Query query= session.createQuery("select max(id) from ProductionMP");
		
		if(query.uniqueResult()==null)
			id=1;
		else 
			id= (int)query.uniqueResult();
		
		return id;
	}

	@Override
	public ProductionMP findByNumOFMP(String numOF,String codeDepot) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select p from ProductionMP p where numOFMP =:numOF and codeDepot=:codeDepot");
		query.setParameter("numOF", numOF);
		query.setParameter("codeDepot", codeDepot);
		
		return (ProductionMP)query.uniqueResult();
	}

	@Override
	public List<CoutProdMP> listeCoutProdMP(int idPord) {
		
		Query query= session.createQuery("select p from CoutProdMP p where prodcutionCM.id =:idPord");
		query.setParameter("idPord", idPord);
		
		return query.list();

	}
	
	@Override
	public List<ProductionMP> listeProductionMPByDateByPeriode(Date dateProd,String periode) {
		
		Query query= session.createQuery("select p from ProductionMP p where date =:dateProd and periode=:periode");
		query.setParameter("dateProd", dateProd);
		query.setParameter("periode", periode);
		
		return query.list();

	}
	
	@Override
	public List<DetailProductionMP> listeDetailProduction(int idPord) {
		
		Query query= session.createQuery("select p from DetailProductionMP p where productionMP.id =:idPord");
		query.setParameter("idPord", idPord);
		
		return query.list();

	}

	
	@Override
	public List<ProductionMP> listeProductionMPEntreDeuxDate(Date dateDebut,Date dateFin) {
		
		Query query= session.createQuery("select p from ProductionMP p where date >=:dateDebut and date <=:dateFin");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		
		
		return query.list();

	}


}
