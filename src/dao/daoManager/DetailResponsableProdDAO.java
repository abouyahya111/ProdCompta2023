package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.DetailProdGen;
import dao.entity.DetailResponsableProd;

public interface DetailResponsableProdDAO {
	
	public  void add(DetailResponsableProd e);
	
	public  DetailResponsableProd edit(DetailResponsableProd e);
	
	public  void delete(int id); 
	
	public List<DetailResponsableProd> findAll();
	
	public DetailResponsableProd findById(int id);
	
	
}
