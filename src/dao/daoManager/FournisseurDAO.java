package dao.daoManager;

import java.util.List;

import dao.entity.Depot;
import dao.entity.Fournisseur;

public interface FournisseurDAO {
	


	public  void add(Fournisseur e);
	
	public  Fournisseur edit(Fournisseur e);
	
	public  void delete(int id); 
	
	public List<Fournisseur> findAll();
	public List<Fournisseur> findFournisseurByDepot(Depot depot);
	public Fournisseur findById(int id);
	public Fournisseur findFournisseurByNom(String nom);




}
