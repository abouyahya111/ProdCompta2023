package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAvoirClientPF;
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFacturePF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface DetailFactureAvoirClientPFDAO {
	
	public  void add(DetailFactureAvoirClientPF e);
	
	public  DetailFactureAvoirClientPF edit(DetailFactureAvoirClientPF e);
	
	public  void delete(int id); 
	
	public List<DetailFactureAvoirClientPF> findAll();
	
	public DetailFactureAvoirClientPF findById(int id);
	
	public List<DetailFactureAvoirClientPF> listeDetailFactureAvoirByFacture(int idFacture);
	public List<DetailFactureAvoirClientPF> listeDetailFactureAvoirClientPFByNumFacture(String Numfacture, Magasin magasin) ;
	
	public List<DetailFactureAvoirClientPF> listeDetailFactureAvoirByDate(Date dateDebut,Date dateFin);
	public List<DetailFactureAvoirClientPF> findDetailAvoirArticleEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle);
	public List<DetailFactureAvoirClientPF> listeDetailFactureAvoirByDate(Date dateDebut,Date dateFin , Magasin magasin);
	public void ViderSession();
	public List<Object[]> listeEtatPrixMoyen(Date dateDebut,Date dateFin , Magasin magasin, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle, String type , SousFamilleArticlePF sousfamille , String tousSousfamille);
	public List<DetailFactureAvoirClientPF> listeEtatPrixMoyenAvoirClientPF(Date dateDebut,Date dateFin , Magasin magasin, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle, String type , SousFamilleArticlePF sousfamille , String tousSousfamille);
	

}
