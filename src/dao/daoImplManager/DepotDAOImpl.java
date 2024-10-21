package dao.daoImplManager;

import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.DepotDAO;
import dao.entity.Depot;
import dao.entity.Magasin;

public class DepotDAOImpl implements DepotDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(Depot e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Depot edit(Depot e) {
		
	session.beginTransaction();
	Depot p= (Depot)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Depot p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<Depot> findAll() {
		return session.createQuery("select c from Depot c").list();
		}

	public Depot findById(int id) {
		return (Depot)session.get(Depot.class, id);
		}

	@Override
	public Depot findByCode(String code) {
		// TODO Auto-generated method stub
		Depot depot= new Depot();
		Query query= session.createQuery("select c from Depot c where code=:code");
		query.setParameter("code", code);
		
		depot= (Depot) query.uniqueResult();
		
		return depot;
	}
	
	@Override
	public List<Depot> findDepotByCodeSaufEnParametre(String code) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Depot c where code <> :code ");
		query.setParameter("code", code);
		//query.setParameter("code", "STKATT");
		
		
		return query.list();
	}


	@Override
	public List<Magasin> listeMagasinByTypeMagasin(String codeType) {
		Query query= session.createQuery("select c from Magasin c where typeMagasin=:codeType");
		query.setParameter("codeType", codeType);
		return query.list();
	}

	@Override
	public List<Magasin> listeMagasinByTypeMagasinDepot(int idDepot,String codeType) {
		Query query= session.createQuery("select c from Magasin c where typeMagasin=:codeType and  depot.id=:idDepot ");
		query.setParameter("idDepot", idDepot);
		query.setParameter("codeType", codeType);
		// TODO Auto-generated method stub
		return query.list();
	}
	
	@Override
	public Magasin magasinByCode(String codeMagasin) {
		Query query= session.createQuery("select c from Magasin c where code=:codeMagasin");
		query.setParameter("codeMagasin", codeMagasin);
		// TODO Auto-generated method stub
		return (Magasin)query.uniqueResult();
	}
	
	
	
	public Magasin magasinByCodeMachineByTypeMagasin(String codeType,String codeMachine) {
		Query query= session.createQuery("select c from Magasin c where codeMachine=:codeMachine and typeMagasin=:typeMagasin");
		query.setParameter("typeMagasin", codeType);
		query.setParameter("codeMachine", codeMachine);
		return (Magasin)query.uniqueResult();
	}
	
	@Override
	public List<Magasin> listeMagasinByTypeMagasinDepotMachine(int idDepot,String codeType,String codeMachine) {
	//	Query query= session.createQuery("select c from Magasin c where typeMagasin=:codeType and (codeMachine=:codeMachine and depot.id=:idDepot ) or  (codeMachine=:codeMachineSte and depot.id=:idDepotSiege)");
		Query query= session.createQuery("select c from Magasin c where typeMagasin=:codeType and (codeMachine=:codeMachine or catMagasin =:codeMachine) and depot.id=:idDepot ");
		query.setParameter("idDepot", idDepot);
		query.setParameter("codeType", codeType);
		query.setParameter("codeMachine", codeMachine);
	//	query.setParameter("codeMachineSte", "M1");
	//	query.setParameter("idDepotSiege", 1);
		// TODO Auto-generated method stub
		return query.list();
	}
	
	@Override
	public List<Magasin> findMagasinByCodeSaufEnParametre(int idDepot,String codeCatMagasin,String typeMagasin) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Magasin c where code <> :code and catMagasin =:codeCatMagasin and typeMagasin=:typeMagasin");
		//query.setParameter("code", code);
		query.setParameter("codeCatMagasin", codeCatMagasin);
		query.setParameter("typeMagasin",typeMagasin);
		
		
		return query.list();
	}
	
	
	
	@Override
	public List<Magasin> findMagasinByCategorieByType( String codeCatMagasin,String typeMagasin) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Magasin c where  catMagasin =:codeCatMagasin and typeMagasin=:typeMagasin");
		 
		query.setParameter("codeCatMagasin", codeCatMagasin);
		query.setParameter("typeMagasin",typeMagasin);
		
		
		return query.list();
	}
	
	
	@Override
	public List<Magasin> findMagasinByCategorieTypeMagasinDepot(String codeCatMagasin,String typeMagasin,int idDepot) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Magasin c where  catMagasin =:codeCatMagasin and typeMagasin=:typeMagasin and depot.id=:idDepot");
		query.setParameter("idDepot", idDepot);
		query.setParameter("codeCatMagasin", codeCatMagasin);
		query.setParameter("typeMagasin",typeMagasin);
		
		
		return query.list();
	}
	
	
	

	@Override
	public List<Magasin> listeMagasinByCategorieinDepot(int idDepot) {
		Query query= session.createQuery("select c from Magasin c where catMagasin=:cat and  depot.id=:idDepot ");
		query.setParameter("idDepot", idDepot);
		query.setParameter("cat","STK");
		// TODO Auto-generated method stub
		return query.list();
	}
	
	
	@Override
	public Magasin MagasinByTypeMagasinStkAttente(String codeType) {
		Query query= session.createQuery("select c from Magasin c where typeMagasin=:codeType");
		query.setParameter("codeType", codeType);
		return (Magasin) query.uniqueResult();
	}
	
	
	@Override
	public Magasin MagasinByLibelle(String libelle) {
		Query query= session.createQuery("select c from Magasin c where libelle=:libelle");
		query.setParameter("libelle", libelle);
		return (Magasin) query.uniqueResult();
	}
	
	
	@Override
	public List<Magasin> findMagasinMPAutreDepot(String typeMagasin,int idMagasin) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Magasin c where  typeMagasin=:typeMagasin and c.id !=:idMagasin and catMagasin=:cat" );
		//query.setParameter("code", code);
		query.setParameter("idMagasin", idMagasin);
		query.setParameter("typeMagasin",typeMagasin);
		query.setParameter("cat","STK");

		return query.list();
	}
	
	@Override
	public List<Magasin> findMagasinPFAutreDepot(String typeMagasin,int idDepot) {
		// TODO Auto-generated method stub
		Query query= session.createQuery("select c from Magasin c where  typeMagasin=:typeMagasin and depot.id=:idDepot and catMagasin=:cat" );
		//query.setParameter("code", code);
		query.setParameter("idDepot", idDepot);
		query.setParameter("typeMagasin",typeMagasin);
		query.setParameter("cat","STK");

		return query.list();
	}
	

}
