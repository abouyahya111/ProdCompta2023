package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import main1.ProdLauncher;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.entity.Articles;
import dao.entity.DetailFacturePF;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;

public class DetailTransferProduitFiniDAOImpl implements DetailTransferProduitFiniDAO {
	//Session session=HibernateUtil.openSession();
	Session session=ProdLauncher.session;

	public void add(DetailTransferProduitFini e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		
		//return p;
	}

	public DetailTransferProduitFini edit(DetailTransferProduitFini e) {
		
	session.beginTransaction();
	DetailTransferProduitFini p= (DetailTransferProduitFini)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailTransferProduitFini p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<DetailTransferProduitFini> findAll() {
		return session.createQuery("select c from DetailTransferProduitFini c").list();
		}

	public DetailTransferProduitFini findById(int id) {
		return (DetailTransferProduitFini)session.get(DetailTransferProduitFini.class, id);
		}
	

	


	@Override
	public List<DetailTransferProduitFini> findByArticle(String codeArticle) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferProduitFini d where article.codeArticle=:codeArticle");
				query.setParameter("codeArticle", codeArticle);
			
				
				return query.list();
}

	@Override
	public List<DetailTransferProduitFini> findByTransferStockPF(int idtransferStockPF) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.id=:idtransferStockPF");
				query.setParameter("idtransferStockPF", idtransferStockPF);
			
				
				return query.list();
}
	

	
	@Override
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYArticle(Date dateDebut , Date dateFin , Articles article) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && article!=null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer between :dateDebut and :dateFin and article.id =:article");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("article", article.getId());
			
		}else if(dateDebut!=null && dateFin!=null && article==null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer between :dateDebut and :dateFin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				
			
		}else if(dateDebut==null && dateFin==null && article!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  article.id =:article");
			
				query.setParameter("article", article.getId());
			
		}else if(dateDebut!=null && dateFin==null && article!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer =:dateDebut and article.id =:article");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				
		}else if(dateDebut!=null && dateFin==null && article==null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer =:dateDebut");
				query.setParameter("dateDebut", dateDebut);
			
			
		}
				
			
				
				return query.list();
}
	
	
	// afficher list transfer stock produit fini contient les article nn redoublé ( groupe by article)
	@Override
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYArticleDistinct(Date dateDebut , Date dateFin , Articles article) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && article!=null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer between :dateDebut and :dateFin and article.id =:article group by article,dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("article", article.getId());
			
		}else if(dateDebut!=null && dateFin!=null && article==null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer between :dateDebut and :dateFin group by article,dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				
			
		}else if(dateDebut==null && dateFin==null && article!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  article.id =:article group by article,dateTransfer");
			
				query.setParameter("article", article.getId());
			
		}else if(dateDebut!=null && dateFin==null && article!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer =:dateDebut and article.id =:article group by article,dateTransfer");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				
		}else if(dateDebut!=null && dateFin==null && article==null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer =:dateDebut group by article,dateTransfer");
				query.setParameter("dateDebut", dateDebut);
			
			
		}
				
			
				
				return query.list();
}
	

	
	@Override
	public List<DetailTransferProduitFini> findBytypetransfer(String type,Magasin magasin) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferProduitFini d where typeTransfer=:type and magasinDestination.id=:magasin");
				query.setParameter("type", type);
				query.setParameter("magasin", magasin.getId());
				
				return query.list();
}
	
	
	public List<DetailTransferProduitFini> findAllTransferStockPFOrderByDateTransfer(Magasin magasin) {
		
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select d from DetailTransferProduitFini d where magasinDestination.id=:magasin order by dateTransfer,id");
		query.setParameter("magasin", magasin.getId());
		return query.list();
				
}
	
	
	public List<DetailTransferProduitFini> findAllTransferStockPFOrderByDateTransferByArticleBySousFamille(Articles article ,Magasin magasin,SousFamilleArticlePF sousfamille) {
		
		// TODO Auto-generated method stub
	
		Query query= session.createQuery("select d from DetailTransferProduitFini d where article.id =:article  and magasinDestination.id=:magasin and sousFamille.id=:sousfamille order by dateTransfer");
		query.setParameter("article", article.getId());
		query.setParameter("magasin", magasin.getId());
		query.setParameter("sousfamille", sousfamille.getId());
		return query.list();
				
}
	
	
	
	public List<DetailTransferProduitFini> findAllTransferStockPFGroupeByDateTransferByArticle(Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=  session.createQuery("select d from DetailTransferProduitFini d where magasinDestination.id=:magasin  group by article,sousFamille,dateTransfer");
		
		query.setParameter("magasin", magasin.getId());
		return query.list();	
}
	
	public List<DetailTransferProduitFini> findAllTransferStockPFGroupeByByArticleIdSouFamille(Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=  session.createQuery("select d from DetailTransferProduitFini d where magasinDestination.id=:magasin  group by article,sousFamille");
		
		query.setParameter("magasin", magasin.getId());
		return query.list();	
}
	
	

	public DetailTransferProduitFini findTransferStockPFByArticleBytypetransfer(Articles article ,String type,Magasin magasin,SousFamilleArticlePF sousfamille) {
		
		// TODO Auto-generated method stub
				Query query= session.createQuery("select d from DetailTransferProduitFini d where article.id =:article and typeTransfer=:type and magasinDestination.id=:magasin and sousFamille.id=:sousfamille");
				query.setParameter("type", type);
				query.setParameter("article", article.getId());
				query.setParameter("magasin", magasin.getId());
				query.setParameter("sousfamille", sousfamille.getId());
				
				return (DetailTransferProduitFini) query.uniqueResult();
}

	
	
	
	// liste des Articles de Statut x entre deux date
	
	@Override
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin , FamilleArticlePF familleArticle) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && article!=null && familleArticle!=null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer between :dateDebut and :dateFin and article.id =:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
			
		}else if(dateDebut!=null && dateFin!=null && article==null && familleArticle!=null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer between :dateDebut and :dateFin and  transferStockPF.statut=:statut and magasinDestination.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
				
		}else if(dateDebut!=null && dateFin!=null && article!=null && familleArticle==null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer between :dateDebut and :dateFin and article.id =:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("article", article.getId());
		}
		
		
		else if(dateDebut!=null && dateFin!=null && article==null && familleArticle==null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer between :dateDebut and :dateFin and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
			
		}else if(dateDebut==null && dateFin==null && article!=null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  article.id =:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
			
			 query.setParameter("article", article.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin", magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && article!=null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and article.id =:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && article!=null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and article.id =:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}else if(dateDebut!=null && dateFin==null && article==null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinDestination.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}
		
		
		
		
		else if(dateDebut!=null && dateFin==null && article==null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
		}
				
			
				
				return query.list();
}
	
	
	
/////////////////////////////////////////////////////////////// Les transfert PF entre deux date et magasin et statut  service /////////////////////////////////////////////////	
	
	
	
	
	@Override
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesService(Date dateDebut , Date dateFin ,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		
			 query= session.createQuery("select d  from DetailTransferProduitFini d , FactureServiceProduction f where d.transferStockPF.CodeTransfer=f.numOF and d.transferStockPF.dateTransfer between :dateDebut and :dateFin  and d.magasinDestination.id=:magasin ");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);		
				 query.setParameter("magasin", magasin.getId());
				
			
		
				
			
				
				return query.list();
}	
	
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	
	@Override
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && article!=null )
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer between :dateDebut and :dateFin and article.id =:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
			
		}else if(dateDebut!=null && dateFin!=null && article==null )
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer between :dateDebut and :dateFin and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 
				
		}else if(dateDebut==null && dateFin==null && article!=null )
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  article.id =:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
			
			 query.setParameter("article", article.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin", magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && article!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and article.id =:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && article==null )
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinDestination.id=:magasin");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("magasin", magasin.getId());
				
		}
		
				return query.list();
}
	
	
	
	
	
	
	
	//list des Articles de Statut X et entre deux date distinct
	
	@Override
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin , FamilleArticlePF familleArticle) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && article!=null && familleArticle!=null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer between :dateDebut and :dateFin and article.id =:article and magasinDestination.id=:magasin and sousFamille.famileArticlePF.id =:familleArticle and  transferStockPF.statut=:statut group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}else if(dateDebut!=null && dateFin!=null && article==null && familleArticle==null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.statut=:statut and  transferStockPF.dateTransfer between :dateDebut and :dateFin and magasinDestination.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
			
		}else if(dateDebut!=null && dateFin!=null && article!=null && familleArticle==null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.statut=:statut and article.id =:article and  transferStockPF.dateTransfer between :dateDebut and :dateFin and magasinDestination.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("article", article.getId());
		}else if(dateDebut!=null && dateFin!=null && article==null && familleArticle!=null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.statut=:statut and sousFamille.famileArticlePF.id =:familleArticle and  transferStockPF.dateTransfer between :dateDebut and :dateFin and magasinDestination.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}
		else if(dateDebut==null && dateFin==null && article!=null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  article.id=:article and  transferStockPF.statut=:statut  and magasinDestination.id=:magasin group by article,sousFamille");
			
			 query.setParameter("article", article.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin", magasin.getId());
			
		}
		else if(dateDebut==null && dateFin==null && article==null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  sousFamille.famileArticlePF.id =:familleArticle and  transferStockPF.statut=:statut  and magasinDestination.id=:magasin group by article,sousFamille");
			
			 query.setParameter("familleArticle", familleArticle.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin", magasin.getId());
			
		}
		else if(dateDebut!=null && dateFin==null && article!=null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer =:dateDebut and article.id=:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && article==null && familleArticle==null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinDestination.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
			
		}else if(dateDebut!=null && dateFin==null && article==null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where sousFamille.famileArticlePF.id =:familleArticle and transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinDestination.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
				 
		}else if(dateDebut!=null && dateFin==null && article!=null && familleArticle!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where sousFamille.famileArticlePF.id =:familleArticle and article.id=:article and transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinDestination.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				 query.setParameter("article", article.getId());
				 query.setParameter("familleArticle", familleArticle.getId());
		}
				
			
				
				return query.list();
}
	
	@Override
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin) {
		
		// TODO Auto-generated method stub
		Query query=null;
		
		if(dateDebut!=null && dateFin!=null && article!=null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer between :dateDebut and :dateFin and article.id =:article and magasinDestination.id=:magasin and  transferStockPF.statut=:statut group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && dateFin!=null && article==null)
		{
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.statut=:statut and  transferStockPF.dateTransfer between :dateDebut and :dateFin and magasinDestination.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				query.setParameter("dateFin", dateFin);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
			
		}
		else if(dateDebut==null && dateFin==null && article!=null )
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where  article.id=:article and  transferStockPF.statut=:statut  and magasinDestination.id=:magasin group by article,sousFamille");
			
			 query.setParameter("article", article.getId());
			 query.setParameter("statut",statut);
			 query.setParameter("magasin", magasin.getId());
			
		}
		else if(dateDebut==null && dateFin==null && article==null )
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.statut=:statut  and magasinDestination.id=:magasin group by article,sousFamille");
			
			 
			 query.setParameter("statut",statut);
			 query.setParameter("magasin", magasin.getId());
			
		}
		else if(dateDebut!=null && dateFin==null && article!=null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer =:dateDebut and article.id=:article and  transferStockPF.statut=:statut and magasinDestination.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				
				query.setParameter("article", article.getId());
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
				
		}else if(dateDebut!=null && dateFin==null && article==null)
		{
			
			 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer =:dateDebut and  transferStockPF.statut=:statut and magasinDestination.id=:magasin group by article,sousFamille");
				query.setParameter("dateDebut", dateDebut);
				 query.setParameter("statut",statut);
				 query.setParameter("magasin", magasin.getId());
			
		}
				
			
				
				return query.list();
}
	
	
	@Override
	public DetailTransferProduitFini findAllTransferStockPFByPFInitialiser( Articles article,String statut) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferProduitFini d  where article.id=:article and  transferStockPF.statut=:statut");
		query.setParameter("article", article.getId());
		 query.setParameter("statut",statut);
		 return (DetailTransferProduitFini) query.uniqueResult();
				
}
	
	@Override
	public DetailTransferProduitFini findDetailTransferStockPFByPFByTransferPF( Articles article,TransferStockPF transferPF) {
		
		// TODO Auto-generated method stub
		Query query= session.createQuery("select d from DetailTransferProduitFini d  where article.id=:article and  transferStockPF.id=:transferStockPF");
		 query.setParameter("article", article.getId());
		 query.setParameter("transferStockPF",transferPF.getId());
		 return (DetailTransferProduitFini) query.uniqueResult();
				
}
	
	@Override
	public List<Object[]> listeStockFinalePF(Date dateDebut,Date dateFin , Magasin magasin ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			// session.createQuery("select d.article.id , d.sousFamille.id , (sum( case when transferStockPF.statut='Initial' then d.quantite else 0 end) + sum( case when transferStockPF.statut='Achat' then d.quantite else 0 end)+ sum( case when transferStockPF.statut='ENTRE PRODUCTION' then d.quantite else 0 end)+ sum( case when transferStockPF.statut='ENTRER_MP' then d.quantite else 0 end))-(" sum( case when transferStockPF.statut='vente' then d.quantite else 0 end) +  sum( case when transferStockPF.statut='AVOIR' then d.quantite else 0 end)) from DetailTransferProduitFini d  where transferStockPF.dateTransfer between :dateDebut and :dateFin and (magasinDestination.id=:magasin or magasinSouce.id=:magasin)  group by article,sousFamille ");
	
			
			query= session.createQuery("select d.article.id , d.sousFamille.id , (sum( case when transferStockPF.statut='Initial' then d.quantite else 0 end) + sum( case when transferStockPF.statut='Achat' then d.quantite else 0 end)+ sum( case when transferStockPF.statut='ENTRE PRODUCTION' then d.quantite else 0 end)+ sum( case when transferStockPF.statut='ENTRER_MP' then d.quantite else 0 end) + sum( case when transferStockPF.statut='ENTRER_PF_MP' then d.quantite else 0 end))-(\r\n" + 
					" sum( case when transferStockPF.statut='vente' then d.quantite else 0 end) + sum( case when transferStockPF.statut='avoir' and d.sousFamille.famileArticlePF.id =8 then d.quantite else 0 end)) from DetailTransferProduitFini d  where transferStockPF.dateTransfer between :dateDebut and :dateFin and (magasinDestination.id=:magasin or magasinSouce.id=:magasin)  group by d.article.id,d.sousFamille.id ");
			
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		//query.setParameter("sousfamille", sousFamilleArticlePF.getId());
		
		
		return query.list();

	}
	
	
	
	// Stock Finale Produit Fini
	@Override
	public List<Object[]> StockFinalePFParArticleSousFamille(Date dateDebut,Date dateFin , Magasin magasin , Articles article, SousFamilleArticlePF sousFamilleArticlePF ) {
		// TODO Auto-generated method stub
		Query query=null;
		
			// query= session.createQuery("select case when facturePF.modeReglement is null  then 'Reglement Espece' else facturePF.modeReglement end, sum(d.montantHT) , sum(d.montantTVA) , sum(d.montantTTC), sum( case when facturePF.modeReglement=:especes or facturePF.modeReglement is null then d.montantTTC else 0 end)*(0.25/100) from DetailFacturePF d where  facturePF.dateFacture between :dateDebut and :dateFin and facturePF.magasin.id=:magasin and (facturePF.modeReglement=:especes or facturePF.modeReglement=:Cheque or facturePF.modeReglement=:Virement or facturePF.modeReglement is null ) group by facturePF.modeReglement");
			
			query= session.createQuery("select d.article.id , d.sousFamille.id , (sum( case when transferStockPF.statut='Initial' then d.quantite else 0 end) + sum( case when transferStockPF.statut='Achat' then d.quantite else 0 end)+ sum( case when transferStockPF.statut='ENTRE PRODUCTION' then d.quantite else 0 end)+ sum( case when transferStockPF.statut='ENTRER_MP' then d.quantite else 0 end)+ sum( case when transferStockPF.statut='ENTRER_PF_MP' then d.quantite else 0 end)+ sum( case when transferStockPF.statut='AVOIR_CLIENT' then d.quantite else 0 end) + sum( case when transferStockPF.statut='ENTRER_PF_PF' then d.quantite else 0 end) )-(\r\n" + 
					" sum( case when transferStockPF.statut='vente' then d.quantite else 0 end) + sum( case when transferStockPF.statut='avoir' and d.sousFamille.famileArticlePF.id =8 then d.quantite else 0 end)  + sum( case when transferStockPF.statut='SORTIE_PF_PF' then d.quantite else 0 end)) from DetailTransferProduitFini d  where transferStockPF.dateTransfer between :dateDebut and :dateFin and (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and d.article.id=:artcile and d.sousFamille.id=:sousfamille   group by article,sousFamille ");
			
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		query.setParameter("artcile", article.getId());
		query.setParameter("sousfamille", sousFamilleArticlePF.getId());
		
		return query.list();

	}
	
	
	
	// les avoirs finance sans Emballage
	@Override
	public List<Object[]> EtatAvoirFinancePF(Date dateDebut,Date dateFin , Magasin magasin , Articles article , FamilleArticlePF famille) {
		// TODO Auto-generated method stub
		Query query=null;
		
			if(article!=null)
			{
				query= session.createQuery("select d.article.id , d.sousFamille.id , sum(d.quantite) , avg(d.prixUnitaire) from DetailTransferProduitFini d  where d.typeTransfer ='AVOIR' and d.dateTransfer between :dateDebut and :dateFin and (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and d.article.id=:artcile and d.sousFamille.famileArticlePF.id !=:famille  group by article,sousFamille ");

				
			}else
			{
				query= session.createQuery("select d.article.id , d.sousFamille.id , sum(d.quantite) , avg(d.prixUnitaire) from DetailTransferProduitFini d  where d.typeTransfer ='AVOIR' and d.dateTransfer between :dateDebut and :dateFin and (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and d.sousFamille.famileArticlePF.id !=:famille  group by article,sousFamille ");

				
			}
			
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		if(article!=null)
		{
			query.setParameter("artcile", article.getId());
		}
		
		query.setParameter("famille", famille.getId());
		
		return query.list();

	}
	
	// les gratuites finance sans Emballage
	@Override
	public List<Object[]> EtatGratuiteFinancePF(Date dateDebut,Date dateFin , Magasin magasin , Articles article , FamilleArticlePF famille) {
		// TODO Auto-generated method stub
		Query query=null;
		
		if(article!=null)
		{
			query= session.createQuery("select d.article.id , d.sousFamille.id , sum(d.quantite) , avg(d.prixUnitaire) from DetailTransferProduitFini d  where d.typeTransfer ='vente' and d.prixUnitaire=0 and d.dateTransfer between :dateDebut and :dateFin and (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and d.article.id=:artcile and d.sousFamille.famileArticlePF.id !=:famille  group by article,sousFamille ");

			
		}else
		{
			query= session.createQuery("select d.article.id , d.sousFamille.id , sum(d.quantite) , avg(d.prixUnitaire) from DetailTransferProduitFini d  where d.typeTransfer ='vente' and d.prixUnitaire=0 and d.dateTransfer between :dateDebut and :dateFin and (magasinDestination.id=:magasin or magasinSouce.id=:magasin)  and d.sousFamille.famileArticlePF.id !=:famille  group by article,sousFamille ");

			
		}
			
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		if(article!=null)
		{
			query.setParameter("artcile", article.getId());
		}
		
		query.setParameter("famille", famille.getId());
		
		return query.list();

	}
	
	
	// les MConsommables Reste
	@Override
	public List<Object[]> EtatMConsommableRestePF(Date dateDebut,Date dateFin , Magasin magasin , Articles article , FamilleArticlePF famille) {
		// TODO Auto-generated method stub
		Query query=null;
		
			if(article!=null)
			{
				query= session.createQuery("select d.article.id , d.sousFamille.id , sum(d.quantite)  from DetailTransferProduitFini d  where d.typeTransfer ='RESTE' and d.dateTransfer between :dateDebut and :dateFin and (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and d.article.id=:artcile and d.sousFamille.famileArticlePF.id =:famille  group by article,sousFamille ");

				
			}else
			{
				query= session.createQuery("select d.article.id , d.sousFamille.id , sum(d.quantite)  from DetailTransferProduitFini d  where d.typeTransfer ='RESTE' and d.dateTransfer between :dateDebut and :dateFin and (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and d.sousFamille.famileArticlePF.id =:famille  group by article,sousFamille ");

				
			}
			
		query.setParameter("dateDebut", dateDebut);
		query.setParameter("dateFin", dateFin);
		query.setParameter("magasin", magasin.getId());
		if(article!=null)
		{
			query.setParameter("artcile", article.getId());
		}
		
		query.setParameter("famille", famille.getId());
		
		return query.list();

	}
	
		public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}

	
		public List<DetailTransferProduitFini> listeDetailTransfertPFService(String requete) {
			// TODO Auto-generated method stub
			Query query=null;
			
			 query= session.createQuery("select d  from DetailTransferProduitFini d , FactureServiceProduction f where d.transferStockPF.CodeTransfer=f.numOF  "+requete +" order by d.article.liblle");
		
			
			return query.list();	
			
			
			
		}	
		
		
		public List<DetailTransferProduitFini> listeDetailTransfertPFByRequete(String requete) {
			// TODO Auto-generated method stub
			Query query=null;
			
			 query= session.createQuery("select d  from DetailTransferProduitFini d  "+requete +"");
		
			
			return query.list();	
			
			
			
		}	
		
		
		
		
		/////////////////////////////////////////////////////// Liste DeatilTransfertPF group by Sous Famille ////////////////////////////////////////////
		
		
		public List<DetailTransferProduitFini> ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupBySousFamille(Date dateDebut , Date dateFin,Magasin magasin) {
			
			// TODO Auto-generated method stub
			Query query=null;
			
			
				 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer between :dateDebut and :dateFin and (magasinDestination.id=:magasin or magasinSouce.id=:magasin) group by sousFamille");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);			
					 query.setParameter("magasin", magasin.getId());
					
				
			
			
					return query.list();
	}
		
		
		/////////////////////////////////////////////////////// Liste DeatilTransfertPF group by Sous Famille (Article Dechet) ////////////////////////////////////////////
		
		
		public List<DetailTransferProduitFini> ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupBySousFamilleArticleDechet(Date dateDebut , Date dateFin,Magasin magasin) {
			
			// TODO Auto-generated method stub
			Query query=null;
			
			
				 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer between :dateDebut and :dateFin and (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and article.codeArticle like '%_PFD%' group by sousFamille");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);			
					 query.setParameter("magasin", magasin.getId());
					
				
			
			
					return query.list();
	}

		/////////////////////////////////////////////////////// Liste DeatilTransfertPF group by Sous Famille (Article Offre) ////////////////////////////////////////////
		
		
		public List<DetailTransferProduitFini> ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupBySousFamilleArticleOffre(Date dateDebut , Date dateFin,Magasin magasin) {
			
			// TODO Auto-generated method stub
			Query query=null;
			
			
				 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer between :dateDebut and :dateFin and (magasinDestination.id=:magasin or magasinSouce.id=:magasin) and article.codeArticle like '%_PFO%' group by sousFamille");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);			
					 query.setParameter("magasin", magasin.getId());
					
				
			
			
					return query.list();
	}	
		
		/////////////////////////////////////////////////////// Liste DeatilTransfertPF group by  article by Sous Famille ////////////////////////////////////////////
		
		
		public List<DetailTransferProduitFini> ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupByArticleBySousFamille(Date dateDebut , Date dateFin,Magasin magasin) {
			
			// TODO Auto-generated method stub
			Query query=null;
			
			
				 query= session.createQuery("select d from DetailTransferProduitFini d where dateTransfer between :dateDebut and :dateFin and (magasinDestination.id=:magasin or magasinSouce.id=:magasin) group by article,sousFamille order by sousFamille.famileArticlePF ,sousFamille ");
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);			
					 query.setParameter("magasin", magasin.getId());
					
				
			
			
					return query.list();
	}
		
		
		/////////////////////////////////////////////// Initial PF By Famille //////////////////////////////////////////
		
		
		
		//list des article by requete entre deux dates 
		
		@Override
		public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(Date dateDebut , Date dateFin , String statut, String requete) {
			
			// TODO Auto-generated method stub
			Query query=null;
			
		
				 query= session.createQuery("select d from DetailTransferProduitFini d where transferStockPF.dateTransfer between :dateDebut and :dateFin    and transferStockPF.statut=:statut "+requete);
					query.setParameter("dateDebut", dateDebut);
					query.setParameter("dateFin", dateFin);
					 query.setParameter("statut",statut);
					

				
				
					
					return query.list();
	}
		
		public List<DetailTransferProduitFini> listDetailTransfertPFProductionByStatut(String entrerproduction , String entrermp) {
			
			// TODO Auto-generated method stub
			Query query=  session.createQuery("select d from DetailTransferProduitFini d where (d.transferStockPF.statut=:entrerproduction or  d.transferStockPF.statut=:entrermp ) order by  d.transferStockPF.dateTransfer");
			
			query.setParameter("entrerproduction", entrerproduction);
			query.setParameter("entrermp", entrermp);
			return query.list();	
	}	
		
		
		
		@Override
		public List<Object[]> EtatMoyenValeurisationproduction(String req) {
			// TODO Auto-generated method stub
			Query query=null;
			
				if(req!="")
				{
					query= session.createQuery("select d.article.codeArticle ,d.article.liblle,d.sousFamille.famileArticlePF.liblle, d.sousFamille.liblle , sum(d.quantite),sum(d.quantite * d.prixUnitaire)  from DetailTransferProduitFini d  where "+ req +" group by article,sousFamille ");

					
				}
				
			
			
			return query.list();

		}	
		
		
		@Override
		public List<Object[]> EtatMoyenValeurisationproductionOffreEtDechet(String req) {
			// TODO Auto-generated method stub
			Query query=null;
			
				if(req!="")
				{
					query= session.createQuery("select d.article.codeArticle ,d.article.liblle,d.sousFamille.famileArticlePF.liblle, d.sousFamille.liblle , sum(d.quantite),avg(d.prixUnitaire)  from DetailTransferProduitFini d  where "+ req +" group by article,sousFamille ");

					
				}
				
			
			
			return query.list();

		}	
		
		
		@Override
		public List<Object[]> EtatMoyenValeurisationproductionParSousFamille(String req) {
			// TODO Auto-generated method stub
			Query query=null;
			
				if(req!="")
				{
					query= session.createQuery("select  d.sousFamille.famileArticlePF.liblle, d.sousFamille.liblle , sum(d.quantite),avg(d.prixUnitaire)  from DetailTransferProduitFini d  where "+ req +" group by d.sousFamille.famileArticlePF.liblle, d.sousFamille.liblle  ");

					
				}
				
			
			
			return query.list();

		}
		
		
		@Override
		public List<Object[]> EtatMoyenValeurisationproductionParSousFamilleDechetOffre(String req) {
			// TODO Auto-generated method stub
			Query query=null;
			
				if(req!="")
				{
					query= session.createQuery("select  d.sousFamille.famileArticlePF.liblle, d.sousFamille.liblle , sum(d.quantite),avg(d.prixUnitaire)  from DetailTransferProduitFini d  where "+ req +" group by d.sousFamille.famileArticlePF.liblle, d.sousFamille.liblle  ");

					
				}
				
			
			
			return query.list();

		}
		
		
		
		@Override
		public List<DetailTransferProduitFini> listeDetailTransfertPFByDateByMagasinByStatut(Date dateDebut,Date dateFin , Magasin magasin,String statut) {
			// TODO Auto-generated method stub
			
			Query query= session.createQuery("select c from DetailTransferProduitFini c where transferStockPF.dateTransfer between :dateDebut and :dateFin and magasinDestination.id=:magasin and transferStockPF.statut=:statut");
			query.setParameter("dateDebut", dateDebut);
			query.setParameter("dateFin", dateFin);
			query.setParameter("magasin", magasin.getId());
			 query.setParameter("statut",statut);
			return query.list();

		}
		
		@Override
		public List<Object[]> QuantiteFabriqueParArticleParSousFamille(String req) {
			// TODO Auto-generated method stub
			Query query=null;
			
				if(req!="")
				{
					query= session.createQuery("select d.article, d.sousFamille , sum(d.quantite)  from DetailTransferProduitFini d  where "+ req +" group by d.article, d.sousFamille order by d.article.id  ");

					
				}
				
			
			
			return query.list();

		}	
		
		

}
