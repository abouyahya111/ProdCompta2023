package dao.daoManager;

import java.util.List;


import dao.entity.ListTva;

public interface ListTvaDAO {
	
	public  void add(ListTva e);
	
	public  ListTva edit(ListTva e);
	
	public  void delete(int id); 
	
	public List<ListTva> findAll();
	
	public ListTva findById(int id);
	
	public ListTva findByCode(String code);
	
	

}
