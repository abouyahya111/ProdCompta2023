package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.CoutMP;
import dao.entity.DetailEstimation;
import dao.entity.Magasin;
import dao.entity.Production;

public interface CoutMPDAO {
	
	public  void add(CoutMP e);
	
	public  CoutMP edit(CoutMP e);
	
	public  void delete(int id); 
	
	public List<CoutMP> findAll();
	
	public CoutMP findById(int id);
	public List<Object[]> EtatTonnageProductionParMoisParMagasin( Date dateDebut, Date dateFin);
	public void ViderSession();
	public List<CoutMP> listeCoutMPByProductionByMagasin(Production production, Magasin magasin);
	public List<CoutMP> listeCoutMPByArticleByMagasin(Articles article, Magasin magasin);
	public List<CoutMP> listeCoutMPEnVracByArticleByMagasin(Articles article, Magasin magasin);
	public List<CoutMP> listeCoutMPEmballageByProductionByMagasin(Production production, Magasin magasin);
	public List<CoutMP> listeCoutMPByProduction(Production production);
}
