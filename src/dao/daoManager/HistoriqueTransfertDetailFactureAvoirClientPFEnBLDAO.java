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
import dao.entity.HistoriqueTransfertDetailFactureAvoirClientPFEnBL;
import dao.entity.HistoriqueTransfertDetailFacturePFEnBL;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface HistoriqueTransfertDetailFactureAvoirClientPFEnBLDAO {
	
	public  void add(HistoriqueTransfertDetailFactureAvoirClientPFEnBL e);
	
	public  HistoriqueTransfertDetailFactureAvoirClientPFEnBL edit(HistoriqueTransfertDetailFactureAvoirClientPFEnBL e);
	
	public  void delete(int id); 
	
	public List<HistoriqueTransfertDetailFactureAvoirClientPFEnBL> findAll();
	
	public HistoriqueTransfertDetailFactureAvoirClientPFEnBL findById(int id);
	
	public List<HistoriqueTransfertDetailFactureAvoirClientPFEnBL> listeDetailFactureAvoirClientPFByFacture(int idFacture);
	
	public List<HistoriqueTransfertDetailFactureAvoirClientPFEnBL> listeDetailFactureAvoirClientPFByDate(Date dateDebut,Date dateFin, Magasin magasin);
	public List<HistoriqueTransfertDetailFactureAvoirClientPFEnBL> listeDetailFactureAvoirClientPFByArticle(Date dateDebut,Date dateFin , Articles article, SousFamilleArticlePF sousfamille , Magasin magasin);
	public List<HistoriqueTransfertDetailFactureAvoirClientPFEnBL> listeDetailFactureAvoirClientPFByNumFacture(String Numfacture, Magasin magasin);
	public void ViderSession();
	public List<HistoriqueTransfertDetailFactureAvoirClientPFEnBL> listeDetailFactureAvoirClientPFByRequete(String requete) ;
}
