package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;

public interface DetailTransferProduitFiniDAO {
	
	public  void add(DetailTransferProduitFini e);
	
	public  DetailTransferProduitFini edit(DetailTransferProduitFini e);
	
	public  void delete(int id); 
	
	public List<DetailTransferProduitFini> findAll();
	
	//public List<DetailTransferProduitFini> findStockByMagasinPF(int idArticle,int idMagasin);

	public List<DetailTransferProduitFini> findByArticle(String codeArticle);
	public List<DetailTransferProduitFini> findByTransferStockPF(int idtransferStockPF);
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYArticle(Date dateDebut , Date dateFin , Articles article);
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYArticleDistinct(Date dateDebut , Date dateFin , Articles article);
	public List<DetailTransferProduitFini> findBytypetransfer(String type,Magasin magasin) ;
	public List<DetailTransferProduitFini> findAllTransferStockPFOrderByDateTransfer(Magasin magasin);
	public List<DetailTransferProduitFini> findAllTransferStockPFGroupeByDateTransferByArticle(Magasin magasin);
	public DetailTransferProduitFini findTransferStockPFByArticleBytypetransfer(Articles article ,String type,Magasin magasin,SousFamilleArticlePF sousfamille);
	public List<DetailTransferProduitFini> findAllTransferStockPFOrderByDateTransferByArticleBySousFamille(Articles article ,Magasin magasin,SousFamilleArticlePF sousfamille);
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesService(Date dateDebut , Date dateFin ,Magasin magasin);
	
	public DetailTransferProduitFini findAllTransferStockPFByPFInitialiser(Articles article,String statut);
	public DetailTransferProduitFini findDetailTransferStockPFByPFByTransferPF(Articles article,TransferStockPF transferPF);
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin, FamilleArticlePF famille);
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin , FamilleArticlePF famille);
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin);
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin);
	public List<DetailTransferProduitFini> findAllTransferStockPFGroupeByByArticleIdSouFamille(Magasin magasin);
	public List<Object[]> listeStockFinalePF(Date dateDebut,Date dateFin , Magasin magasin );
	public List<Object[]> StockFinalePFParArticleSousFamille(Date dateDebut,Date dateFin , Magasin magasin , Articles article, SousFamilleArticlePF sousFamilleArticlePF );
	
	
	public List<Object[]> EtatAvoirFinancePF(Date dateDebut,Date dateFin , Magasin magasin , Articles article , FamilleArticlePF famille);
	public List<Object[]> EtatGratuiteFinancePF(Date dateDebut,Date dateFin , Magasin magasin , Articles article , FamilleArticlePF famille);
	
	public List<Object[]> EtatMConsommableRestePF(Date dateDebut,Date dateFin , Magasin magasin , Articles article , FamilleArticlePF famille);
	
	public void ViderSession();
	
	public List<DetailTransferProduitFini> listeDetailTransfertPFService(String requete);
	public List<DetailTransferProduitFini> listeDetailTransfertPFByRequete(String requete);
	
	public List<DetailTransferProduitFini> ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupBySousFamille(Date dateDebut , Date dateFin,Magasin magasin);
	public List<DetailTransferProduitFini> ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupBySousFamilleArticleDechet(Date dateDebut , Date dateFin,Magasin magasin);
	public List<DetailTransferProduitFini> ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupBySousFamilleArticleOffre(Date dateDebut , Date dateFin,Magasin magasin);
	public List<DetailTransferProduitFini> ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(Date dateDebut , Date dateFin , String statut, String requete);
	public List<DetailTransferProduitFini> ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupByArticleBySousFamille(Date dateDebut , Date dateFin,Magasin magasin);
	public List<DetailTransferProduitFini> listDetailTransfertPFProductionByStatut(String entrerproduction , String entrermp);
	public List<DetailTransferProduitFini> listeDetailTransfertPFByDateByMagasinByStatut(Date dateDebut,Date dateFin , Magasin magasin,String statut);
	public List<Object[]> EtatMoyenValeurisationproduction(String req);
	public List<Object[]> EtatMoyenValeurisationproductionOffreEtDechet(String req);
	public List<Object[]> EtatMoyenValeurisationproductionParSousFamille(String req);
	public List<Object[]> EtatMoyenValeurisationproductionParSousFamilleDechetOffre(String req);
	public List<Object[]> QuantiteFabriqueParArticleParSousFamille(String req);
}
