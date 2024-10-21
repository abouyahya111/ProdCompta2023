package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.CategorieMp;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAchatMP;
import dao.entity.DetailFacturePF;
import dao.entity.FactureAchatMP;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;

public interface DetailFactureAchatMPDAO {
	
	public  void add(DetailFactureAchatMP e);
	
	public  DetailFactureAchatMP edit(DetailFactureAchatMP e);
	
	public  void delete(int id); 
	
	public List<DetailFactureAchatMP> findAll();
	
	public DetailFactureAchatMP findById(int id);
	
	public List<DetailFactureAchatMP> listeDetailFactureAchatByFacture(int idFacture);
	
	public List<DetailFactureAchatMP> listeDetailFactureAchatByDate(Date dateDebut,Date dateFin);
	public void delete(DetailFactureAchatMP p);
	
	public List<DetailFactureAchatMP> listeDetailFactureAchatByRequete(String requete);                                            
	public List<Object[]> listeEtatPrixMoyenMP(Date dateDebut,Date dateFin , Magasin magasin, SubCategorieMp souscategoriemp, MatierePremier mp, String tousSousCategorie, String touarticle , CategorieMp categorie , String touscategorie);
	public List<Object[]> listeEtatPrixMoyenMPParMP(Date dateDebut,Date dateFin , Magasin magasin, SubCategorieMp souscategoriemp, MatierePremier mp, String tousSousCategorie, String touarticle , CategorieMp categorie , String touscategorie);
	public void DelateDetailfactureAchatMPByFactureAchatMP(FactureAchatMP factureAchatMP);
	
}
