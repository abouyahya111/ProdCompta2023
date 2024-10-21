package dao.daoManager;

import java.util.List;

import dao.entity.CategorieMp;
import dao.entity.SubCategorieMp;

public interface SubCategorieMPDAO {
	


	public  void add(SubCategorieMp e);
	
	public  SubCategorieMp edit(SubCategorieMp e);
	
	public  void delete(Long id); 
	
	public List<SubCategorieMp> findAll();
	
	public SubCategorieMp findById(Long id);
	
	public SubCategorieMp findByCode(String code);

}
