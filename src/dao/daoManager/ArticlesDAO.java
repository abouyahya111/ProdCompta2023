package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;

public interface ArticlesDAO {
	
	public  void add(Articles e);
	
	public  Articles edit(Articles e);
	
	public  void delete(int id); 
	
	public List<Articles> findAll();
	
	public Articles findById(int id);
	
	public Articles findByCode(String code);
	
	public List<DetailEstimation> listeMatierePremierByArticleByMP(int id,String codeSubCat, int priotite);
	public List<Articles> listeArticleBySousFamille(int id_sousfamille);
	public Articles findByCodeBysousFamille(String code,int id_sousfamille);
	public List<Articles> listeArticleGratuite();
	public List<Articles> listeArticleByCodeOffre(String codeTherre,String codeVerre);
	public List<Articles> listeArticleMConsommable(String MConsommable);
	public Articles findBylibelle(String liblle);
}
