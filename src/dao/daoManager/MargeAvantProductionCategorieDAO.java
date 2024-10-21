package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Magasin;
import dao.entity.MargeAvantProductionCategorieMP;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;

public interface MargeAvantProductionCategorieDAO {
	
	public  void add(MargeAvantProductionCategorieMP e);
	
	public  MargeAvantProductionCategorieMP edit(MargeAvantProductionCategorieMP e);
	
	public  void delete(int id); 
	
	public List<MargeAvantProductionCategorieMP> findAll();
	
	public MargeAvantProductionCategorieMP findById(int id);
	
	public void ViderSession();
}
