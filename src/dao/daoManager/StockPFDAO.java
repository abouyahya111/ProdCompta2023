package dao.daoManager;

import java.util.List;

import dao.entity.StockPF;

public interface StockPFDAO {
	
	public  void add(StockPF e);
	
	public  StockPF edit(StockPF e);
	
	public  void delete(int id); 
	
	public List<StockPF> findAll();
	
	public StockPF findStockByArticle(int idArticle );
	
	public StockPF findById(int id);

	public	List<StockPF> findStockProduitFiniByMagasin(int idMagasin);
	public List<StockPF> findStockProduitFiniByMagasinGroupByArticle(int idMagasin);

	public	List<StockPF> findSockNonVideByMagasin(int idMagasin);

	public StockPF findStockByMagasinPF(int idArticle, int idMagasin);
	public StockPF findStockByMagasinPFBySousFamille(int idArticle, int idMagasin,int sousfamille);
	public List<StockPF> findStockArticleByMagasinPFBySousFamille(int idMagasin,int sousfamille);
	public List<StockPF> findStockArticleByMagasinPFByFamille(int idMagasin,int famille);
	public void viderSessionStock();
}
