package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.FamilleArticlePF;
import dao.entity.SousFamilleArticlePF;

public interface SousFamilleArticlesPFDAO {
	
	public  void add(SousFamilleArticlePF e);
	
	public  SousFamilleArticlePF edit(SousFamilleArticlePF e);
	
	public  void delete(int id); 
	
	public List<SousFamilleArticlePF> findAll();
	
	public SousFamilleArticlePF findById(int id);
	
	public SousFamilleArticlePF findByCode(String code);
	
	public List<SousFamilleArticlePF> listeSousFamillePFByFamilleArticlePF(int idFamileArticlePF);
	
	public List<SousFamilleArticlePF> listeSousFamillePFByFamilleArticlePFOffre(String codetherres, String codeverres);

	public SousFamilleArticlePF findByLibelle(String libelle);
}
