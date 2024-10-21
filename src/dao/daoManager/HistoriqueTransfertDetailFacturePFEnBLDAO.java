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
import dao.entity.HistoriqueTransfertDetailFacturePFEnBL;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface HistoriqueTransfertDetailFacturePFEnBLDAO {
	
	public  void add(HistoriqueTransfertDetailFacturePFEnBL e);
	
	public  HistoriqueTransfertDetailFacturePFEnBL edit(HistoriqueTransfertDetailFacturePFEnBL e);
	
	public  void delete(int id); 
	
	public List<HistoriqueTransfertDetailFacturePFEnBL> findAll();
	
	public HistoriqueTransfertDetailFacturePFEnBL findById(int id);
	
	public List<HistoriqueTransfertDetailFacturePFEnBL> listeDetailFacturePFByFacture(int idFacture);
	
	public List<HistoriqueTransfertDetailFacturePFEnBL> listeDetailFacturePFByDate(Date dateDebut,Date dateFin, Magasin magasin);
	public List<HistoriqueTransfertDetailFacturePFEnBL> listeDetailFacturePFByArticle(Date dateDebut,Date dateFin , Articles article, SousFamilleArticlePF sousfamille , Magasin magasin);
	public List<HistoriqueTransfertDetailFacturePFEnBL> listeDetailFacturePFByNumFacture(String Numfacture, Magasin magasin);
	public void ViderSession();
		public List<HistoriqueTransfertDetailFacturePFEnBL> listeDetailFacturePFByRequete(String requete) ;
}
