package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailFactureServiceTransport;
import dao.entity.DetailFactureServiceTransport;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface DetailFactureServiceTransportDAO {
	
	public  void add(DetailFactureServiceTransport e);
	
	public  DetailFactureServiceTransport edit(DetailFactureServiceTransport e);
	
	public  void delete(int id); 
	
	public List<DetailFactureServiceTransport> findAll();
	
	public DetailFactureServiceTransport findById(int id);
	
	public List<DetailFactureServiceTransport> listeDetailFactureServiceTransportByFacture(int idFacture);
	
	public List<DetailFactureServiceTransport> listeDetailFactureServiceTransportByDate(Date dateDebut,Date dateFin, Magasin magasin);
	public List<DetailFactureServiceTransport> findDetailBLEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle, String type);
	public List<DetailFactureServiceTransport> listeDetailFactureServiceTransportByArticle(Date dateDebut,Date dateFin , Articles article, SousFamilleArticlePF sousfamille , Magasin magasin);
	public List<DetailFactureServiceTransport> listeDetailFactureServiceTransportByNumFacture(String Numfacture, Magasin magasin);
	public List<Object[]> listeEtatMontantFacture(Date dateDebut,Date dateFin , Magasin magasin , String type);
	public void ViderSession();
	public List<Object[]> listeEtatVentePFParClientPF(Date dateDebut,Date dateFin , Magasin magasin , ClientPF clientPF,String etat);
	public List<DetailFactureServiceTransport> findDetailBLEntreDeuxDateSansGratuite(java.util.Date dateDebut,java.util.Date datedeFin , Magasin magasin,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle , String type);
	public List<Object[]> listeEtatPrixMoyen(Date dateDebut,Date dateFin , Magasin magasin, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle, String type , SousFamilleArticlePF sousfamille , String tousSousfamille);
	public List<DetailFactureServiceTransport> listeFactureSansGratuiteParArticle(Articles article , Magasin magasin , String  numfacture) ;
	public List<DetailFactureServiceTransport> listeDetailFactureServiceTransportByRequete(String requete) ;
	public List<Object[]> listeEtatVentePFParFamilleParMois(Date dateDebut,Date dateFin , Magasin magasin ) ;
	public List<Object[]> listeFactureServiceClientAvecOuSansICE(java.util.Date dateDebut,java.util.Date dateFin , Magasin magasin , String ice);
	public List<Object[]> listeEtatChiffreAffaireClientSansICETransportService(String req );
	public List<Object[]> listeEtatChiffreAffaireClientAvecICETransportService(String req );
}
