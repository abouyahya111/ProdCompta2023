package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFacturePF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface DetailFactureAvoirPFDAO {
	
	public  void add(DetailFactureAvoirPF e);
	
	public  DetailFactureAvoirPF edit(DetailFactureAvoirPF e);
	
	public  void delete(int id); 
	
	public List<DetailFactureAvoirPF> findAll();
	
	public DetailFactureAvoirPF findById(int id);
	
	public List<DetailFactureAvoirPF> listeDetailFactureAvoirByFacture(int idFacture);
	
	public List<DetailFactureAvoirPF> listeDetailFactureAvoirByDate(Date dateDebut,Date dateFin);
	public List<DetailFactureAvoirPF> findDetailAvoirArticleEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,Fournisseur Fournisseur, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle);
	public List<DetailFactureAvoirPF> listeDetailFactureAvoirByDate(Date dateDebut,Date dateFin , Magasin magasin);
	public void ViderSession();
	public List<Object[]> listeEtatPrixMoyen(Date dateDebut,Date dateFin , Magasin magasin, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle, String type , SousFamilleArticlePF sousfamille , String tousSousfamille);
}
