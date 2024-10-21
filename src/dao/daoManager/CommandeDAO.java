package dao.daoManager;

import java.util.List;

import dao.entity.Commande;

public interface CommandeDAO {
	
	public  void add(Commande e);
	
public  Commande edit(Commande e);
	
	public  void delete(int id); 
	
	public List<Commande> findAll();
	
	public Commande findById(int id);

}
