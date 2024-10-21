package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.CompteMagasinier;
import dao.entity.DetailCompteMagasinier;
import dao.entity.DetailEstimation;

public interface CompteMagasinierDAO  {
	
	public  void add(CompteMagasinier e);
	
	public  CompteMagasinier edit(CompteMagasinier e);
	
	public  void delete(int id); 
	
	public List<CompteMagasinier> findAll();
	
	public CompteMagasinier findById(int id);
	
	public CompteMagasinier findByCode(String code);
	public List<DetailCompteMagasinier> findDetailCompteMagasinierByCompteMagasinierAndDate(int code,Date date);
	

}
