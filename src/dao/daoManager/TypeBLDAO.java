package dao.daoManager;

import java.util.List;

import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.TypeBL;
import dao.entity.Ville;

public interface TypeBLDAO {
	


	public  void add(TypeBL e);
	
	public  TypeBL edit(TypeBL e);
	
	public  void delete(int id); 
	
	public List<TypeBL> findAll();
	
	public TypeBL findById(int id);

 
	public  TypeBL findTypeBLByType(String type);



}
