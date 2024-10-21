package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.FamilleArticlePF;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;

public interface SousFamilleEnVracDAO {
	
	public  void add(SousFamilleEnVrac e);
	
	public  SousFamilleEnVrac edit(SousFamilleEnVrac e);
	
	public  void delete(int id); 
	
	public List<SousFamilleEnVrac> findAll();
	
	public SousFamilleEnVrac findById(int id);
	
	
	public SousFamilleEnVrac findByMP(MatierePremier mp);
	
	public List<SousFamilleEnVrac> listeMatierePremierByFamille(int idFamileArticlePF);
	
	public List<SousFamilleEnVrac> listeMatierePremierBySousFamille(int idSousFamileArticlePF);


}
