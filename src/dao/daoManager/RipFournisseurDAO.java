package dao.daoManager;

import java.util.List;

import dao.entity.Fournisseur;
import dao.entity.RipFournisseur;

public interface RipFournisseurDAO {
	
	public  void add(RipFournisseur e);
	
	public  RipFournisseur edit(RipFournisseur e);
	
	public  void delete(int id); 
	
	public List<RipFournisseur> findAll();
	
	public RipFournisseur findById(int id);
	
	public List<RipFournisseur> findRipByFournisseur(int idFournisseur);

}
