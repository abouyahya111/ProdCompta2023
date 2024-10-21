package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailFacturePF;
import dao.entity.DetailHistoriqueVenteVendeur;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface DetailHistoriqueVenteVendeurDAO {
	
	public  void add(DetailHistoriqueVenteVendeur e);
	
	public  DetailHistoriqueVenteVendeur edit(DetailHistoriqueVenteVendeur e);
	
	public  void delete(int id); 
	
	public List<DetailHistoriqueVenteVendeur> findAll();
	
	public DetailHistoriqueVenteVendeur findById(int id);
	
	public List<DetailHistoriqueVenteVendeur> listeDetailFacturePFByFacture(int idFacture);
	
	public List<DetailHistoriqueVenteVendeur> listeDetailFacturePFByDate(Date dateDebut,Date dateFin, Magasin magasin);
	public List<DetailHistoriqueVenteVendeur> findDetailBLEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle, String type);
	public List<DetailHistoriqueVenteVendeur> listeDetailFacturePFByArticle(Date dateDebut,Date dateFin , Articles article, SousFamilleArticlePF sousfamille , Magasin magasin);
	public List<DetailHistoriqueVenteVendeur> listeDetailFacturePFByNumFacture(String Numfacture, Magasin magasin);
	public List<Object[]> listeEtatMontantFacture(Date dateDebut,Date dateFin , Magasin magasin , String type);
	public void ViderSession();
	public List<Object[]> listeEtatVentePFParFamille(Date dateDebut,Date dateFin , Magasin magasin , String etat);
	public List<Object[]> listeEtatVentePFParClientPF(Date dateDebut,Date dateFin , Magasin magasin , ClientPF clientPF,String etat);
	public List<DetailHistoriqueVenteVendeur> findDetailBLEntreDeuxDateSansGratuite(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle , String type);
	public List<Object[]> listeEtatPrixMoyen(Date dateDebut,Date dateFin , Magasin magasin, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle, String type , SousFamilleArticlePF sousfamille , String tousSousfamille);
	public List<DetailHistoriqueVenteVendeur> listeFactureSansGratuiteParArticle(Articles article , Magasin magasin , String  numfacture) ;
	public List<DetailHistoriqueVenteVendeur> listeDetailFacturePFByRequete(String requete) ;
}
