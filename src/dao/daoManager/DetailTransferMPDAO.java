package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;

public interface DetailTransferMPDAO {
	
	public  void add(DetailTransferStockMP e);
	
	public  DetailTransferStockMP edit(DetailTransferStockMP e);
	
	public  void delete(int id); 
	
	public List<DetailTransferStockMP> findAll();
	
	public DetailTransferStockMP findById(int id);
	//public List<DetailTransferProduitFini> findStockByMagasinPF(int idArticle,int idMagasin);

	public List<DetailTransferStockMP> findByMP(String codeMP);
	public List<DetailTransferStockMP> findByTransferStockMP(int idtransferStockMP);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMP(Date dateDebut , Date dateFin , MatierePremier mp);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinct(Date dateDebut , Date dateFin ,  MatierePremier mp);
	public List<DetailTransferStockMP> findBytypetransfer(String type,Magasin magasin) ;
	public List<DetailTransferStockMP> findAllTransferStockMPOrderByDateTransfer(Magasin magasin);
	
	public List<DetailTransferStockMP> findAllTransferStockMPOrderByDateTransferEnterDeuxDate(Magasin magasin , Date dateDebut , Date dateFin);
	public List<DetailTransferStockMP> findAllTransferStockMPGroupeByDateTransferByMP(Magasin magasin);
	
	public DetailTransferStockMP findAllTransferStockMPByMPInitialiser(MatierePremier mp,String statut,Depot depot,Magasin magasin);
	public DetailTransferStockMP findDetailTransferStockMPByMPByTransferMP(MatierePremier mp,TransferStockMP transferMP);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutInitial(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutCharge(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutCharge(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutAvoir(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAvoir(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutChargeSupp(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutChargeSupp(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) ;
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutVente(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin) ;
	
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPStatutService(Date dateDebut , Date dateFin , MatierePremier mp,String statut,Magasin magasin);
	public List<DetailTransferStockMP> ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutService(Date dateDebut , Date dateFin , MatierePremier mp,String statut, Magasin magasin);
	
	
	public List<DetailTransferStockMP> findAllTransferStockMPGroupeByMP(Magasin magasin);
	public List<DetailTransferStockMP> findDetailTransferMPByNumOFStatut(String NUmOF , Magasin magasin , String statut);
	public List<DetailTransferStockMP> findDetailTransferMPchargeouchargeservice(String charge , String  chargeservice);
	public List<Object[]> StockFinaleMPByMagasin(Date dateDebut,Date dateFin , Magasin magasin ,MatierePremier mp);
	public List<DetailTransferStockMP> listeDetailTransfertMP(String requete);
	public List<DetailTransferStockMP> findAllTransferStockMPBySubCategorieGroupeByMP(Magasin magasin , SubCategorieMp subCategorieMp);
	public List<DetailTransferStockMP> ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(Date dateDebut , Date dateFin ,String statut,Magasin magasin , SubCategorieMp subCategorieMp);
	public List<DetailTransferStockMP> ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(Date dateDebut , Date dateFin ,String statut,Magasin magasin,SubCategorieMp subCategorieMp);
	public List<DetailTransferStockMP> listeDetailTransfertMPByDateByMagasinByStatut(Date dateDebut,Date dateFin , Magasin magasin,String statut);
	public  DetailTransferStockMP findMPByTypeTransferStockMPByMagasinBayDate (String type, MatierePremier mp, Magasin magasin,Date dateDebut , Date dateFin );
	public  DetailTransferStockMP findInitialeMPByMPByMagasinBayDate (MatierePremier mp, Magasin magasin,Date dateDebut , Date dateFin ) ;
	public void DeleteTransfertStockMPByTransfertStockMP(TransferStockMP transferStockMP);
	public List<Object[]> listeMPBoxEtCartonConsommeParPF(String req);
	public void ViderSession();
}
