package dao.daoManager;

import java.util.List;

import dao.entity.DetailCommande;

public interface DetailCommandeDAO {
	
	public  void add(DetailCommande e);
	
	public  DetailCommande edit(DetailCommande e);
	
	public  void delete(int id); 
	
	public List<DetailCommande> findAll();
	
	public DetailCommande findById(int id);
	
	public List findByMP(int idMP);

}
