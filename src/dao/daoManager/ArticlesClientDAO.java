package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.ArticlesClient;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailEstimation;

public interface ArticlesClientDAO {
	
	public  void add(ArticlesClient e);
	
	public  ArticlesClient edit(ArticlesClient e);
	
	public  void delete(int id); 
	
	public List<ArticlesClient> findAll();
	
	public ArticlesClient findById(int id);
	public ArticlesClient findByClientByDepot(ClientPF clientPF , Depot depot);
	
}
