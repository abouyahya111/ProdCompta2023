package dao.daoManager;

import java.util.List;

import dao.entity.CategorieMp;
import dao.entity.MatierePremier;

public interface MatierePremiereDAO {
	


	public  void add(MatierePremier e);
	
	public  MatierePremier edit(MatierePremier e);
	
	public  void delete(int id); 
	
	public List<MatierePremier> findAll();
	
	public MatierePremier findById(int id);
	
	public MatierePremier findByCode(String code);
	
	public List<MatierePremier> findMatierePremierSaufCatTHE();
	
	public  List<CategorieMp>  listeCategorieTHE();
	
	public List<MatierePremier> listeMatierePremierByCategorie(String idCat);
	public List<MatierePremier> listeMatierePremierByidcategorie(int idCat) ;
	public List<MatierePremier> findMatierePremierCatTHE();
	public List<MatierePremier> listeMatierePremierBySousCategorie(int idsubcategorie);
}
