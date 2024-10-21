package dao.daoManager;

import java.util.List;

import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.Ville;

public interface VilleDAO {
	


	public  void add(Ville e);
	
	public  Ville edit(Ville e);
	
	public  void delete(int id); 
	
	public List<Ville> findAll();
	
	public Ville findById(int id);

	

	public	Ville findVilleByCodeVille(String codeVille);
	public  List<Ville> findListVilleByRequete(String requete);
	public int maxIdVille();
	public  Ville findVilleByVille(String ville);



}
