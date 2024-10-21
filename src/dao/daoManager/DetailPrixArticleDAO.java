package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailPrixArticle;

public interface DetailPrixArticleDAO {
	
	public  void add(DetailPrixArticle e);
	
	public  DetailPrixArticle edit(DetailPrixArticle e);
	
	public  void delete(int id); 
	
	public List<DetailPrixArticle> findAll();
	public List<DetailPrixArticle>  findByIdArticle(int id);
	public DetailPrixArticle findById(int id);
	public DetailPrixArticle findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(int idarticle,int idmagasin , int idfamillearticle,int idfsousamillearticle);
	public DetailPrixArticle  findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient(int idarticle,int idmagasin , int idfamillearticle,int idfsousamillearticle , int idclient);
	public List<DetailPrixArticle>  findDetaileArticleByArticleByMagasin(int idarticle,int idmagasin);
	public List<DetailPrixArticle>  findDetaileArticleBySousFamilleByMagasin(int idsousfamille,int idmagasin) ;
}
