package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailFacturePF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface DetailFacturePFDAO {
	
	public  void add(DetailFacturePF e);
	
	public  DetailFacturePF edit(DetailFacturePF e);
	
	public  void delete(int id); 
	
	public List<DetailFacturePF> findAll();
	
	public DetailFacturePF findById(int id);
	
	public List<DetailFacturePF> listeDetailFacturePFByFacture(int idFacture);
	
	public List<DetailFacturePF> listeDetailFacturePFByDate(Date dateDebut,Date dateFin, Magasin magasin);
	public List<DetailFacturePF> findDetailBLEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle, String type);
	public List<DetailFacturePF> listeDetailFacturePFByArticle(Date dateDebut,Date dateFin , Articles article, SousFamilleArticlePF sousfamille , Magasin magasin);
	public List<DetailFacturePF> listeDetailFacturePFByNumFacture(String Numfacture, Magasin magasin);
	public List<Object[]> listeEtatMontantFactureAvecTVA(Date dateDebut,Date dateFin , Magasin magasin , String type);
	public List<Object[]> listeEtatMontantFactureSansTVA(Date dateDebut,Date dateFin , Magasin magasin , String type);
	public void ViderSession();
	public List<Object[]> listeEtatVentePFParClientPF(Date dateDebut,Date dateFin , Magasin magasin , ClientPF clientPF,String etat);
	public List<DetailFacturePF> findDetailBLEntreDeuxDateSansGratuite(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle , String type);
	public List<DetailFacturePF> listeEtatPrixMoyen(Date dateDebut,Date dateFin , Magasin magasin, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle, String type , SousFamilleArticlePF sousfamille , String tousSousfamille);
	public List<DetailFacturePF> listeFactureSansGratuiteParArticle(Articles article , Magasin magasin , String  numfacture) ;
	public List<DetailFacturePF> listeDetailFacturePFByRequete(String requete) ;
	public List<Object[]> listeEtatVentePFParFamilleParMois(Date dateDebut,Date dateFin , Magasin magasin ) ;
	public List<Object[]> listeFactureVenteClientAvecOuSansICE(Date dateDebut,Date dateFin , Magasin magasin , String ice);
	public List<Object[]> listeEtatChiffreAffaireClientAvecICE(String req );
	public List<Object[]> listeEtatChiffreAffaireClientSansICE(String req );
	public List<Object[]> listeEtatVentePFParFamilleParFacture(Date dateDebut,Date dateFin , Magasin magasin );
}
