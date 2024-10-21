package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.FamilleArticlePF;

public interface FamilleArticlesPFDAO {
	
	public  void add(FamilleArticlePF e);
	
	public  FamilleArticlePF edit(FamilleArticlePF e);
	
	public  void delete(int id); 
	
	public List<FamilleArticlePF> findAll();
	
	public FamilleArticlePF findById(int id);
	
	public FamilleArticlePF findByCode(String code);
	
	public FamilleArticlePF findByLibelle(String libelle);
	


}
