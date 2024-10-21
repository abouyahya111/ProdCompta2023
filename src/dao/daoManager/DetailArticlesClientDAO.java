package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.ArticlesClient;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailArticlesClient;
import dao.entity.DetailEstimation;

public interface DetailArticlesClientDAO {
	
	public  void add(DetailArticlesClient e);
	
	public  DetailArticlesClient edit(DetailArticlesClient e);
	
	public  void delete(int id); 
	
	public List<DetailArticlesClient> findAll();
	
	public DetailArticlesClient findById(int id);
	public List<DetailArticlesClient> listeDetailArticlesClientByArticlesClient(int articlesclient);
	
}
