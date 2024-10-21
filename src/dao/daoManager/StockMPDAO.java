package dao.daoManager;

import java.util.List;
import java.util.Map;

import dao.entity.Magasin;
import dao.entity.StockMP;

public interface StockMPDAO {
	
	public  void add(StockMP e);
	
	public  StockMP edit(StockMP e);
	
	public  void delete(int id); 
	
	public List<StockMP> findAll();
	
	public List<StockMP> findAllByMagasin(int idMagasin);
	
	public StockMP findById(int id);
	
	public StockMP findStockByMatierePremier(int id);
	
	public StockMP findStockByMagasinMP(int idMP,int idMagasin);
	
	public List<StockMP> listeStockNouveauMP(int idMagasin);
	
	public List<StockMP> findSockNonVideByMagasin(int idMagasin);
	public List<StockMP> findSockByMagasin(int idMagasin);

	public List<Magasin> findMagasinByCodeSaufEnParametre(int idMagasin,String codeCatMagasin, String typeMagasin);

	public List<StockMP> findStockMin(int  idDepot);
	public List<StockMP> listeStockMPByCategorie(int idMagasin , int idcategorieMP);

	//public Map<Integer, Float> findStockTotalByMagasin(int idDepot);

	
	

}
