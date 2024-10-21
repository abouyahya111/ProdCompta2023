package dao.daoManager;

import java.util.List;

import dao.entity.AdresseClientPF;
import dao.entity.Client;
import dao.entity.ClientPF;

public interface AdresseClientPFDAO {
	


	public  void add(AdresseClientPF e);
	
	public  AdresseClientPF edit(AdresseClientPF e);
	
	public  void delete(int id); 
	
	public List<AdresseClientPF> findAll();
	
	public AdresseClientPF findById(int id);

	public List<AdresseClientPF> findListAdresseClientByClient(ClientPF clientpf);

	 



}
