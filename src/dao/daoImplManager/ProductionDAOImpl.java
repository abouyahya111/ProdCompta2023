package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.ProductionDAO;
import dao.entity.Articles;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Magasin;
import dao.entity.Production;

public class ProductionDAOImpl implements ProductionDAO {
	//Session session=HibernateUtil.openSession();
		Session session=ProdLauncher.session;

	public void add(Production e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public Production edit(Production e) {
		
	session.beginTransaction();
	Production p= (Production)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		Production p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<Production> findAll() {
		return session.createQuery("select c from Production c order by c.id").list();
		}

	public Production findById(int id) {
		return (Production)session.get(Production.class, id);
		}

	@Override
	public int maxIdProduction() {
		// TODO Auto-generated method stub
		int id =0;
		Query query= session.createQuery("select max(id) from Production");
		
		if(query.uniqueResult()==null)
			id=1;
		else 
			id= (int)query.uniqueResult();
		
		return id;
	}

	@Override
	public Production findByNumOF(String numOF,String codeDepot) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select p from Production p where numOF =:numOF and codeDepot=:codeDepot");
		query.setParameter("numOF", numOF);
		query.setParameter("codeDepot", codeDepot);
		
		return (Production)query.uniqueResult();
	}

	@Override
	public List<CoutMP> listeCoutMP(int idPord) {
		
		Query query= session.createQuery("select p from CoutMP p where prodcutionCM.id =:idPord");
		query.setParameter("idPord", idPord);
		
		return query.list();

	}
	
	@Override
	public List<Production> listeProductionByDateByPeriode(Date dateProd,String periode) {
		
		Query query= session.createQuery("select p from Production p where date =:dateProd and periode=:periode");
		query.setParameter("dateProd", dateProd);
		query.setParameter("periode", periode);
		
		return query.list();

	}
	
	@Override
	public List<DetailProduction> listeDetailProduction(int idPord) {
		
		Query query= session.createQuery("select p from DetailProduction p where production.id =:idPord");
		query.setParameter("idPord", idPord);
		
		return query.list();

	}
	
	@Override
	public List<DetailProdGen> listeDetailProdGen(int idPord) {
		
		Query query= session.createQuery("select p from DetailProdGen p where productionGen.id =:idPord");
		query.setParameter("idPord", idPord);
		
		return query.list();

	}
	
	@Override
	public List<DetailResponsableProd> listeDetailResponsableProd(int idPord) {
		
		Query query= session.createQuery("select p from DetailResponsableProd p where productionDRP.id =:idPord");
		query.setParameter("idPord", idPord);
		
		return query.list();

	}
	
	@Override
	public List<Production> listeProductionEntreDeuxDate(Date dateDebut,Date dateFin) {
		
		Query query= session.createQuery("select p from Production p where date >=:dateDebut and date <=:dateFin");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		
		
		return query.list();

	}
	
	@Override
	public List<Production> listeProductionTerminerEntreDeuxDate(Date dateDebut,Date dateFin) {
		
		Query query= session.createQuery("select p from Production p where date >=:dateDebut and date <=:dateFin and statut =:statut");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("statut", Constantes.ETAT_OF_TERMINER);
		
		return query.list();

	}
	
	
	
	@Override
	public List<Production> listeProductionTerminerBydepotByReq(String depot , String req) {
		
		Query query= session.createQuery("select p from Production p where code_depot=:depot and statut =:statut "+req);
		query.setParameter("depot", depot);
		query.setParameter("statut", Constantes.ETAT_OF_TERMINER);
		
		return query.list();

	}
	
	
	
	
	
	@Override
	public List<Production> listeProductionTerminerbyDepotEntreDeuxDate(Date dateDebut,Date dateFin,String statut,String depot , String numof) {
		Query query=null;
		
		
		if(dateDebut!=null && dateFin!=null && depot!=null && !numof.equals("") )
		{
			query= session.createQuery("select p from Production p where date >=:dateDebut and date <=:dateFin and statut =:statut and code_depot=:depot and numOF=:numof");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("numof", numof);
		}else if(dateDebut!=null && dateFin==null && depot!=null && !numof.equals("") )
		{
			query= session.createQuery("select p from Production p where date =:dateDebut and statut =:statut and code_depot=:depot and numOF=:numof");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("numof", numof);
			
		}else if(dateDebut==null && dateFin==null && depot!=null && !numof.equals("") )
		{
			query= session.createQuery("select p from Production p where statut =:statut and code_depot=:depot and numOF=:numof");
			
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("numof", numof);
			
			
		}else if(dateDebut!=null && dateFin!=null && depot!=null && numof.equals("") )
		{
			query= session.createQuery("select p from Production p where date >=:dateDebut and date <=:dateFin and statut =:statut and code_depot=:depot");
			
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
			
			
		}else if(dateDebut!=null && dateFin==null && depot!=null && numof.equals("") )
		{
			
			
	query= session.createQuery("select p from Production p where date =:dateDebut  and statut =:statut and code_depot=:depot");
			
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		}else if(dateDebut==null && dateFin==null && depot!=null && numof.equals("") )
		{
			
			query= session.createQuery("select p from Production p where statut =:statut and code_depot=:depot");
			
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			
		}
			
		
		
		
	
		List<Production> list=query.list();
		
		return list;

	}
	
	
	@Override
	public Production findByNumOFStatut(String numOF,String statut) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select p from Production p where numOF =:numOF and statut=:statut");
		query.setParameter("numOF", numOF);
		query.setParameter("statut", statut);
		
		return (Production)query.uniqueResult();
	}

	
	@Override
	public List<CoutMP> listeCoutMPBYNumOF(String NumOF) {
		
		Query query= session.createQuery("select p from CoutMP p where prodcutionCM.numOF =:NumOF");
		query.setParameter("NumOF", NumOF);
		
		return query.list();

	}
	
	
	
	@Override
	public List<Production> listeProductionServiceTerminerbyDepotEntreDeuxDate(Date dateDebut,Date dateFin,String statut,String depot , String service) {
		List<Production> list=null;
		if(dateDebut !=null && dateFin !=null && depot !=null)
		{
		Query query= session.createQuery("select p from Production p where date >=:dateDebut and date <=:dateFin and statut =:statut and code_depot=:depot and service=:service  ");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("statut", statut);
		query.setParameter("depot", depot);
		query.setParameter("service", service);
		list=query.list();
		}else if(dateDebut !=null && dateFin ==null && depot !=null)
		{

			Query query= session.createQuery("select p from Production p where date >=:dateDebut and date <=:dateDebut and statut =:statut and code_depot=:depot and service=:service ");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("service", service);
			list=query.list();
			
		}else if(dateDebut ==null && dateFin ==null && depot !=null)
		{

			Query query= session.createQuery("select p from Production p where statut =:statut and code_depot=:depot and service=:service ");
			query.setParameter("statut", statut);
			query.setParameter("depot", depot);
			query.setParameter("service", service);
			list=query.list();
			
		
		}
			
		return list;

	}
	
	
	
	@Override
	public List<Production> listeProductionServiceTerminer(String statut,String depot , String service) {
		
	
		Query query= session.createQuery("select p from Production p where  statut =:statut and code_depot=:depot and service=:service ");
	
		query.setParameter("statut", statut);
		query.setParameter("depot", depot);
		query.setParameter("service", service);
		List<Production> list=query.list();

			
		return list;

	}
	
	
	
	@Override
	public List<Production> listeProductionServiceNonFacturer(String creer,String lancer,String depot , String service , Date date) {
		
	
		Query query= session.createQuery("select p from Production p where   (statut =:creer or statut =:lancer)  and code_depot=:depot and service=:service and date_debFabPre <:date ");
	
		query.setParameter("creer", creer);
		query.setParameter("lancer", lancer);
		query.setParameter("depot", depot);
		query.setParameter("service", service);
		query.setParameter("date", date);
		List<Production> list=query.list();

			
		return list;

	}
	
	

	public List<Production> listeProductionGroupByArticle(String statut,String depot , String service) {
		
	
		Query query= session.createQuery("select p from Production p where  statut =:statut and code_depot=:depot and service=:service group by articles ");
	
		query.setParameter("statut", statut);
		query.setParameter("depot", depot);
		query.setParameter("service", service);
		List<Production> list=query.list();

			
		return list;

	}
	
	
	public List<Production> listeProductionGroupByMagasinPF(String statut,String depot , String service) {
		
		
		Query query= session.createQuery("select p from Production p where  statut =:statut and code_depot=:depot and service=:service group by magasinPF ");
	
		query.setParameter("statut", statut);
		query.setParameter("depot", depot);
		query.setParameter("service", service);
		List<Production> list=query.list();

			
		return list;

	}
	
	
	public List<CoutMP> listeCoutMPBYDateByMagasin(Date dateDebut,Date dateFin,String statut,Magasin magasin ) {
		
		List<CoutMP> list=null;
		
		if(!statut.equals(""))
		{
			Query query= session.createQuery("select p from CoutMP p where p.prodcutionCM.date >=:dateDebut and p.prodcutionCM.date <=:dateFin  and p.prodcutionCM.statut =:statut and p.magasin.id =:magasin" );
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("magasin", magasin.getId());
			list=query.list();
			
		}else
		{
			Query query= session.createQuery("select p from CoutMP p where p.prodcutionCM.date >=:dateDebut and p.prodcutionCM.date <=:dateFin  and p.magasin.id =:magasin" );
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);			
			query.setParameter("magasin", magasin.getId());
			list=query.list();
			
			
		}
	
		
		return list;

	}
	
	
	public List<Object[]> listeArticleFabriqueParMagasinParSousFamille( Magasin magasin)  {
		
		Query query=null;
		
		
		query= session.createQuery("select  a.codeArticle ,a.liblle , c.matierePremier.nom, s.sousfamile.code  FROM Production p , Articles a, CoutMP c , SousFamilleEnVrac s  where p.articles.id=a.id and  p.id=c.prodcutionCM.id and p.magasinPF.id=:magasin and p.statut =:statut and c.matierePremier.categorieMp.subCategorieMp=1 and s.matierePremier.id=c.matierePremier.id   group by a.codeArticle ,a.liblle , c.matierePremier.nom, s.sousfamile.code ");
		query.setParameter("magasin", magasin.getId());
		query.setParameter("statut", Constantes.ETAT_OF_TERMINER);
	return query.list();
	
		
	 

	}
	
	
	public List<Object[]> listeMPConsommeParArticleParMagasin  (Date dateDebut,Date dateFin,String statut,Magasin magasin, Magasin magasinMP ) {
		
		Query query=null;
		
		if(magasinMP!=null)
		{
			 
			
			query= session.createQuery("select p.prodcutionCM.articles.codeArticle,p.matierePremier.code,d.sousFamille.code, sum(p.quantConsomme), sum(p.quantiteOffre) from CoutMP p , DetailTransferProduitFini d where  p.prodcutionCM.date >=:dateDebut and p.prodcutionCM.date <=:dateFin  and p.prodcutionCM.statut =:statut and p.prodcutionCM.magasinPF.id =:magasin and p.magasin.id =:magasinMP  and p.prodcutionCM.numOF=d.transferStockPF.CodeTransfer group by p.prodcutionCM.articles.codeArticle,p.matierePremier.code,d.sousFamille.code order by p.matierePremier.categorieMp.subCategorieMp.id" );
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("magasin", magasin.getId());
			query.setParameter("magasinMP", magasinMP.getId());
			
		}else
		{
			
			query= session.createQuery("select p.prodcutionCM.articles.codeArticle,p.matierePremier.code,d.sousFamille.code, sum(p.quantConsomme), sum(p.quantiteOffre) from CoutMP p , DetailTransferProduitFini d where  p.prodcutionCM.date >=:dateDebut and p.prodcutionCM.date <=:dateFin  and p.prodcutionCM.statut =:statut and p.prodcutionCM.magasinPF.id =:magasin and p.prodcutionCM.numOF=d.transferStockPF.CodeTransfer group by p.prodcutionCM.articles.codeArticle,p.matierePremier.code,d.sousFamille.code order by p.matierePremier.categorieMp.subCategorieMp.id" );
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("magasin", magasin.getId());
			
		}
			
			 
	 
	
		
			return query.list();

	}
	
	
	public List<Object[]> listeArticleFabriqueParDepotParMagasin(Date dateDebut,Date dateFin,String statut,Depot depot, Magasin magasinMP ) {
		
		Query query=null;
		
	 
			 
			
			query= session.createQuery("select p.articles.codeArticle, p.articles.liblle,sum(quantiteReel) from Production p  where  p.date >=:dateDebut and p.date <=:dateFin  and p.statut =:statut and code_depot=:depot and p.id in ( select c.prodcutionCM.id from CoutMP c where c.magasin.id =:magasinMP) group by  p.articles.codeArticle, p.articles.liblle"  );
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("magasinMP", magasinMP.getId());
			query.setParameter("depot", depot.getCode());
			
		 
			
			 
	 
	
		
			return query.list();

	}
	
	
	public List<Production> listeProductionParArticleParDepotParMagasin(Date dateDebut,Date dateFin,String statut,Depot depot, Magasin magasinMP , Articles article) {
		
		Query query=null;
		
	 
			 
			
			query= session.createQuery("select p  from Production p  where  p.date >=:dateDebut and p.date <=:dateFin  and p.statut =:statut and code_depot=:depot and p.id in ( select c.prodcutionCM.id from CoutMP c where c.magasin.id =:magasinMP) and  p.articles.id=:article "  );
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("statut", statut);
			query.setParameter("magasinMP", magasinMP.getId());
			query.setParameter("depot", depot.getCode());
			query.setParameter("article", article.getId());
			
		 
			
			 
	 
	
		
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
