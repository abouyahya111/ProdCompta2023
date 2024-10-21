package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFactureAvoirPFComparaison;
import dao.entity.DetailFacturePF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;

public interface DetailFactureAvoirPFComparaisonDAO {
	
	public  void add(DetailFactureAvoirPFComparaison e);
	
	public  DetailFactureAvoirPFComparaison edit(DetailFactureAvoirPFComparaison e);
	
	public  void delete(int id); 
	
	public List<DetailFactureAvoirPFComparaison> findAll();
	
	public DetailFactureAvoirPFComparaison findById(int id);
	
	public List<DetailFactureAvoirPFComparaison> listeDetailFactureAvoirByFacture(int idFacture);
	
	public List<DetailFactureAvoirPFComparaison> listeDetailFactureAvoirByDate(Date dateDebut,Date dateFin);

	public List<DetailFactureAvoirPFComparaison> ListDetailFactureAvoirPFComparaisonEntreDeuxDatesBYPFStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin , FamilleArticlePF familleArticle);
	
	public List<DetailFactureAvoirPFComparaison> ListDetailFactureAvoirPFComparaisonEntreDeuxDatesBYPFDistinctByStatutX(Date dateDebut , Date dateFin , Articles article,String statut,Magasin magasin , FamilleArticlePF familleArticle);
	
	public void ViderSession();
}
