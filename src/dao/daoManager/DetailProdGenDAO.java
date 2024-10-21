package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.DetailProdGen;

public interface DetailProdGenDAO {
	
	public  void add(DetailProdGen e);
	
	public  DetailProdGen edit(DetailProdGen e);
	
	public  void delete(int id); 
	
	public List<DetailProdGen> findAll();
	
	public DetailProdGen findById(int id);
	
	public List<DetailProdGen> findByDateProdPeriode(Date dateProd,String periode);
	
	public DetailProdGen findByDateProdPeriodeEmploye(Date dateProd,String periode,int idEmploye);

}
