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
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.FactureAchatMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;

public class DetailTransferMPDAOImpl implements DetailTransferMPDAO {
	//Session session=ProdLauncher.session;
	Session session=ProdLauncher.session;

	public void add(DetailTransferStockMP e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailTransferStockMP edit(DetailTransferStockMP e) {
		
	session.beginTransaction();
	DetailTransferStockMP p= (DetailTransferStockMP)session.merge(e);
	
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailTransferStockMP p= findById(id);
		session.delete(p);
		
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailTransferStockMP> findAll() {
		return session.createQuery("select c from DetailTransferStockMP c").list();
		
		}

	public DetailTransferStockMP findById(int id) {
		return (DetailTransferStockMP)session.get(DetailTransferStockMP.class, id);
		}
	

	


	@Override
	public List<DetailTransferStockMP> findByMP(String codeMP) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferStockMP d where matierePremier.code=:codeMP");
				query.setParameter("codeMP", codeMP);
			
				
				return query.list();
}

	@Override
	public List<DetailTransferStockMP> findByTransferStockMP(int idtransferStockMP) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.id=:idtransferStockMP");
				query.setParameter("idtransferStockMP", idtransferStockMP);
			
				
				return query.list();
}
	
	
	
	@Override
	public  DetailTransferStockMP findMPByTypeTransferStockMPByMagasinBayDate (String type, MatierePremier mp, Magasin magasin,Date dateDebut , Date dateFin ) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:type and magasinSouce.id=:magasin and transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp group by matierePremier");
				
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("magasin",magasin.getId());
				 query.setParameter("type", type);
				return (DetailTransferStockMP) query.uniqueResult();
}	
	
	@Override
	public  DetailTransferStockMP findInitialeMPByMPByMagasinBayDate (MatierePremier mp, Magasin magasin,Date dateDebut , Date dateFin ) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:type and magasinDestination.id=:magasin and transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp group by matierePremier");
				
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("magasin",magasin.getId());
				 query.setParameter("type", Constantes.ETAT_TRANSFER_STOCK_INITIAL);
				return (DetailTransferStockMP) query.uniqueResult();
}		
	

	
	@Override
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMP(Date dateDebut , Date dateFin , MatierePremier mp) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp");
			
			 query.setParameter("mp", mp.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut");
				query.setParameter("dateDebut", dateDebut);
			
			
		}
				
			
				
				return query.list();
}
	
	
	// afficher list transfer stock produit fini contient les article nn redoublé ( groupe by article)
	@Override
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinct(Date dateDebut , Date dateFin , MatierePremier mp) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id  =:mp group by mp,transferStockMP.dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin group by matierePremier,transferStockMP.dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp group by matierePremier,transferStockMP.dateTransfer");
			
			 query.setParameter("mp", mp.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp group by matierePremier,transferStockMP.dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut group by matierePremier,transferStockMP.dateTransfer");
				query.setParameter("dateDebut", dateDebut);
			
			
		}
				
			
				
				return query.list();
}
	

	
	@Override
	public List<DetailTransferStockMP> findBytypetransfer(String type,Magasin magasin) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferStockMP  d where transferStockMP.statut=:type and magasinSouce.id=:magasin");
				query.setParameter("type", type);
				 query.setParameter("magasin",magasin.getId());
				
				return query.list();
}
	
	@Override
	public List<DetailTransferStockMP> findAllTransferStockMPOrderByDateTransfer(Magasin magasin) {
		
		// TODO Auto-generated method stub
		
		Query query=  session.createQuery("select d from DetailTransferStockMP d where (magasinSouce.id=:magasin or magasinDestination.id=:magasin) order by transferStockMP.dateTransfer , id");
		 query.setParameter("magasin",magasin.getId());
		 return query.list();
				
}
	
	@Override
	public List<DetailTransferStockMP> findAllTransferStockMPOrderByDateTransferEnterDeuxDate(Magasin magasin , Date dateDebut , Date dateFin) {
		
		// TODO Auto-generated method stub
		
		Query query=  session.createQuery("select d from DetailTransferStockMP d where (magasinSouce.id=:magasin or magasinDestination.id=:magasin) and transferStockMP.dateTransfer between :dateDebut and :dateFin  order by transferStockMP.dateTransfer , id");
		 query.setParameter("magasin",magasin.getId());
		 query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
		 return query.list();
				
}
	
	
	@Override
	public List<DetailTransferStockMP> findAllTransferStockMPGroupeByDateTransferByMP(Magasin magasin) {
	//,transferStockMP.dateTransfer
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d where (magasinSouce.id=:magasin or magasinDestination.id=:magasin)  group by matierePremier,transferStockMP.dateTransfer");
		 query.setParameter("magasin",magasin.getId());
		 return query.list();
				
}
	
	
	public List<DetailTransferStockMP> findAllTransferStockMPGroupeByMP(Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d where (magasinSouce.id=:magasin or magasinDestination.id=:magasin)  group by matierePremier");
		 query.setParameter("magasin",magasin.getId());
		 return query.list();
				
}
	
	public List<DetailTransferStockMP> findAllTransferStockMPBySubCategorieGroupeByMP(Magasin magasin , SubCategorieMp subCategorieMp) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d where (magasinSouce.id=:magasin or magasinDestination.id=:magasin) and matierePremier.categorieMp.subCategorieMp.id=:subCategorieMp  group by matierePremier");
		 query.setParameter("magasin",magasin.getId());
		 query.setParameter("subCategorieMp",subCategorieMp.getId());
		 return query.list();
				
}
	
	

	@Override
	public DetailTransferStockMP findAllTransferStockMPByMPInitialiser(MatierePremier mp,String statut,Depot depot,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d  where matierePremier.id=:mp and  transferStockMP.statut=:statut and transferStockMP.depot.id =:depot and (magasinSouce.id=:magasin or magasinDestination.id=:magasin)");
		 query.setParameter("mp", mp.getId());
		 query.setParameter("statut",statut);
		 query.setParameter("depot",depot.getId());
		 query.setParameter("magasin",magasin.getId());
		 return (DetailTransferStockMP) query.uniqueResult();
				
}


	@Override
	public DetailTransferStockMP findDetailTransferStockMPByMPByTransferMP(MatierePremier mp,TransferStockMP transferMP) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d  where matierePremier.id=:mp and  transferStockMP.id=:transferMP");
		 query.setParameter("mp", mp.getId());
		 query.setParameter("transferMP",transferMP.getId());
		 return (DetailTransferStockMP) query.uniqueResult();
				
}
	
	
	
	// liste des MP de Statut x entre deux date
	
	@Override
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	@Override
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutCharge(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	@Override
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutVente(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	
	
	@Override
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutAvoir(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	@Override
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutChargeSupp(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}	


	
	// Transfer Stock MP Service 
	
	@Override
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutService(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer between :dateDebut and :dateFin and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	
	
	
	
	//list des MP de Statut X et entre deux date distinct
	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutInitial(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	

	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
		
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutCharge(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}

	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutChargeSupp(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	// transfer Stock MP Service Distinct
	

	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutService(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAvoir(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && mp!=null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id =:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && mp==null)
		{
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.statut=:statut and  transferStockMP.dateTransfer between :dateDebut and :dateFin and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut==null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where  matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
			
			 query.setParameter("mp", mp.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin",magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && mp!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and matierePremier.id=:mp and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("mp", mp.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && mp==null)
		{
			
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer =:dateDebut and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin  group by matierePremier");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	
	// list detail transfer stock MP 
	
	public List<DetailTransferStockMP> findDetailTransferMPByNumOFStatut(String NUmOF , Magasin magasin,String statut) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.CodeTransfer=:NUmOF and (magasinSouce.id=:magasin or magasinDestination.id=:magasin) and transferStockMP.statut=:statut ");
		 query.setParameter("magasin",magasin.getId());
		 query.setParameter("NUmOF",NUmOF);
		 query.setParameter("statut",statut);
		 return query.list();
				
}

	// list detail transfer stock MP charge ou cherge service
	public List<DetailTransferStockMP> findDetailTransferMPchargeouchargeservice(String charge , String  chargeservice) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferStockMP d where  transferStockMP.statut=:charge or transferStockMP.statut=:chargeservice");
		 query.setParameter("charge",charge);
		 query.setParameter("chargeservice",chargeservice);
		 return query.list();
				
}
	
	
	
	@Override
	public List<Object[]> StockFinaleMPByMagasin(Date dateDebut,Date dateFin , Magasin magasin ,MatierePremier mp) {
		// TODO Auto-generated method stub
		Query query=null;
		
			// query= session.createQuery("select case when facturePF.modeReglement is null  then 'Reglement Espece' else facturePF.modeReglement end, sum(d.montantHT) , sum(d.montantTVA) , sum(d.montantTTC), sum( case when facturePF.modeReglement=:especes or facturePF.modeReglement is null then d.montantTTC else 0 end)*(0.25/100) from DetailFacturePF d where  facturePF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin and (facturePF.modeReglement=:especes or facturePF.modeReglement=:Cheque or facturePF.modeReglement=:Virement or facturePF.modeReglement is null ) group by facturePF.modeReglement");
			
			query= session.createQuery("select d.matierePremier.id ,(sum( case when d.transferStockMP.statut='Initial' then d.quantite else 0 end) + sum( case when d.transferStockMP.statut='FABRIQUE' then d.quantite else 0 end) + sum( case when d.transferStockMP.statut='Achat' then d.quantite else 0 end))-(sum( case when d.transferStockMP.statut='charge' then d.quantite else 0 end) + sum( case when d.transferStockMP.statut='CHARGE_SUPP' then d.quantite else 0 end) + \r\n" + 
					"sum( case when d.transferStockMP.statut='SORTIE_PF' then d.quantite else 0 end) + sum( case when d.transferStockMP.statut='SORTIE_PF_S' then d.quantite else 0 end) + sum( case when d.transferStockMP.statut='vente' then d.quantite else 0 end) + sum( case when d.transferStockMP.statut='SERVICE' then d.quantite else 0 end) + sum( case when d.transferStockMP.statut='AVOIR' then d.quantite else 0 end))\r\n" + 
					"from DetailTransferStockMP d\r\n" + 
					"where (magasinSouce.id=:magasin or magasinDestination.id=:magasin) \r\n" + 
					"and\r\n" + 
					"transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.id=:mp \r\n" + 
					"group by matierePremier");
			
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		query.setParameter("mp", mp.getId());
	
		
		return query.list();

	}
	
	
	
	// liste des MP Achat ou Avoir entre deux date by subcategorie
	
	@Override
	public List<DetailTransferStockMP> ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(Date dateDebut , Date dateFin ,String statut,Magasin magasin , SubCategorieMp subCategorieMp) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin  and  transferStockMP.statut=:statut and  magasinDestination.id=:magasin and matierePremier.categorieMp.subCategorieMp.id=:subCategorieMp ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				 query.setParameter("subCategorieMp",subCategorieMp.getId());
				
			
				
				return query.list();
}
	

	// Listes des MP Charge   Pour la Production ou Vente ou Transfert MP en PF By SubCategorie
	
	@Override
	public List<DetailTransferStockMP> ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(Date dateDebut , Date dateFin ,String statut,Magasin magasin,SubCategorieMp subCategorieMp) {
		
		// TODO Auto-generated method stub
		Query query=null;
	
			 query= session.createQuery("select d from DetailTransferStockMP d where transferStockMP.dateTransfer between :dateDebut and :dateFin and matierePremier.categorieMp.subCategorieMp.id=:subCategorieMp and  transferStockMP.statut=:statut and  magasinSouce.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin",magasin.getId());
				 query.setParameter("subCategorieMp",subCategorieMp.getId());
		
				
			
				
				return query.list();
}
	
	
	
	
	
	
	
	
	
	
	
	
	public List<DetailTransferStockMP> listeDetailTransfertMP(String requete) {
		// TODO Auto-generated method stub
		Query query=null;
		
		 query= session.createQuery("select d from DetailTransferStockMP d where "+requete +" order by d.matierePremier.nom");
	
		
		return query.list();	
		
		
		
	}
	
	
	
	@Override
	public List<DetailTransferStockMP> listeDetailTransfertMPByDateByMagasinByStatut(Date dateDebut,Date dateFin , Magasin magasin,String statut) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from DetailTransferStockMP c where transferStockMP.dateTransfer between :dateDebut and :dateFin and magasinDestination.id=:magasin and transferStockMP.statut=:statut");
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		 query.setParameter("statut",statut);
		return query.list();

	}
	
	
	@Override
	public void DeleteTransfertStockMPByTransfertStockMP(TransferStockMP transferStockMP) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("delete from DetailTransferStockMP where  transferStockMP.id=:transferStockMP ");
		
		query.setParameter("transferStockMP", transferStockMP.getId());	
		 
		int i=query.executeUpdate(); 
		
		
	}
	
	
	
	@Override
	public List<Object[]> listeMPBoxEtCartonConsommeParPF(String req) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select d.article,d.sousFamille,c.matierePremier,sum(c.quantite) from DetailTransferStockMP c , DetailTransferProduitFini d where c.transferStockMP.CodeTransfer=d.transferStockPF.CodeTransfer "+req);
		 
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
