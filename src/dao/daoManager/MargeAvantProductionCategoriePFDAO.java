package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Magasin;
import dao.entity.MargeAvantProductionCategorieMP;
import dao.entity.MargeAvantProductionCategoriePF;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;

public interface MargeAvantProductionCategoriePFDAO {
	
	public  void add(MargeAvantProductionCategoriePF e);
	
	public  MargeAvantProductionCategoriePF edit(MargeAvantProductionCategoriePF e);
	
	public  void delete(int id); 
	
	public List<MargeAvantProductionCategoriePF> findAll();
	
	public MargeAvantProductionCategoriePF findById(int id);
	
	public void ViderSession();
}
