package dao.daoManager;

import java.util.List;

import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.EnTete;
import dao.entity.TypeBL;
import dao.entity.Ville;

public interface EnTeteDAO {
	


	public  void add(EnTete e);
	
	public  EnTete edit(EnTete e);
	
	public  void delete(int id); 
	
	public List<EnTete> findAll();
	
	public EnTete findById(int id);

 
	public  EnTete findEnTeteByVille(String ville);
	public  List<EnTete> findEnTeteByDepot(Depot depot);



}
