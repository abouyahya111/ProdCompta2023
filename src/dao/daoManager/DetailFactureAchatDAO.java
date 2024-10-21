package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFacturePF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface DetailFactureAchatDAO {
	
	public  void add(DetailFactureAchat e);
	
	public  DetailFactureAchat edit(DetailFactureAchat e);
	
	public  void delete(int id); 
	
	public List<DetailFactureAchat> findAll();
	
	public DetailFactureAchat findById(int id);
	
	public List<DetailFactureAchat> listeDetailFactureAchatByFacture(int idFacture);
	
	public List<DetailFactureAchat> listeDetailFactureAchatByDate(Date dateDebut,Date dateFin);
	public List<DetailFactureAchat> listeDetailFactureAchatByDateByMagasin(Date dateDebut,Date dateFin , Magasin magasin);
	public List<DetailFactureAchat> ListDetailFactureAchatPFEntreDeuxDates(Date dateDebut , Date dateFin , Articles article,Magasin magasin , FamilleArticlePF familleArticle);
	public List<DetailFactureAchat> ListDetailFactureAchatPFEntreDeuxDatesBYPFDistinct(Date dateDebut , Date dateFin , Articles article,Magasin magasin , FamilleArticlePF familleArticle);
	public List<DetailFactureAchat> findDetailAchatArticleEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,Fournisseur fournisseur, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle ) ;
	public List<Object[]> findDetailAchatPF(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,Fournisseur fournisseur);
	public List<Object[]> listeEtatPrixMoyen(Date dateDebut,Date dateFin , Magasin magasin, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle , SousFamilleArticlePF sousfamille , String tousSousfamille);
	
	public void ViderSession();
	
}
