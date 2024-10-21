package dao.daoManager;

import java.util.List;

import dao.entity.Client;
import dao.entity.ClientPF;

public interface ClientPFDAO {
	


	public  void add(ClientPF e);
	
	public  ClientPF edit(ClientPF e);
	
	public  void delete(int id); 
	
	public List<ClientPF> findAll();
	
	public ClientPF findById(int id);

	public List<ClientPF> findListClientByCodeDepot(String codeDepot);

	public	ClientPF findClientByCodeClient(String codeClient);
	public  List<ClientPF> findListClientByRequete(String requete);
	public int maxIdClientPF();



}
