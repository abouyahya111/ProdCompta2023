package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Depot;
import dao.entity.TransferStockMP;

public interface TransferStockMPDAO {
	
	public  void add(TransferStockMP e);
	
public  TransferStockMP edit(TransferStockMP e);
	
	public  void delete(int id); 
	
	public List<TransferStockMP> findAll();
	
	public TransferStockMP findById(int id);

	public int maxIdStock();
	public TransferStockMP findTransferByCode(String codeTransfer);
	public TransferStockMP findTransferByCodeStatut(String codeTransfer , String statut);
	public List<TransferStockMP> findTransferByStatut(String statut,Date dateDebut , Date dateFin , Depot depot) ;
	public List<TransferStockMP> findTransferByStatutChargeouService(String charge,String service);
}
