package dao.daoManager;

import java.util.List;

import dao.entity.EtatMontantFacturePF;
import dao.entity.Articles;
import dao.entity.DetailEstimation;

public interface EtatMontantFacturePFDAO {
	
	public  void add(EtatMontantFacturePF e);
	
	public  EtatMontantFacturePF edit(EtatMontantFacturePF e);
	
	public  void delete(int id); 
	
	public List<EtatMontantFacturePF> findAll();
	
	public EtatMontantFacturePF findById(int id);
	

}
