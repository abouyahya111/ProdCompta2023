package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Depot;
import dao.entity.TransferStockInitialMPVersStockInitialAutreMP;
import dao.entity.TransferStockMP;

public interface TransferStockInitialMPVersAutreMPDAO {
	
	public  void add(TransferStockInitialMPVersStockInitialAutreMP e);
	
public  TransferStockInitialMPVersStockInitialAutreMP edit(TransferStockInitialMPVersStockInitialAutreMP e);
	
	public  void delete(int id); 
	
	public List<TransferStockInitialMPVersStockInitialAutreMP> findAll();
	
	public TransferStockInitialMPVersStockInitialAutreMP findById(int id);

	public int maxIdStock();
	public TransferStockInitialMPVersStockInitialAutreMP findTransferByCode(String codeTransfer);
 
	 
}
