package dao.daoManager;

import java.sql.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FactureVenteMP;
import dao.entity.HistoriqueTransfertFacturePFEnBL;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface HistoriqueTransfertFacturePFEnBLDAO {
	
	public  void add(HistoriqueTransfertFacturePFEnBL e);
	
	public  HistoriqueTransfertFacturePFEnBL edit(HistoriqueTransfertFacturePFEnBL e);
	
	public  void delete(int id); 
	
	public List<HistoriqueTransfertFacturePFEnBL> findAll();
	
	public HistoriqueTransfertFacturePFEnBL findById(int id);
	
	public HistoriqueTransfertFacturePFEnBL findFacturePFByEtat(String etat);
	public List<HistoriqueTransfertFacturePFEnBL> findByNumFacture(String NumFacture, Depot depot);
	public List<HistoriqueTransfertFacturePFEnBL> findByClient(String codeclient);
	public List<HistoriqueTransfertFacturePFEnBL> findByDate(java.util.Date datefacture);
	public List<HistoriqueTransfertFacturePFEnBL> findByDepot(int iddepot);
		public List<HistoriqueTransfertFacturePFEnBL> findFactureEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin,Depot depot);
	
	public List<HistoriqueTransfertFacturePFEnBL> findFactureByClient(String codeclient);
	public List<HistoriqueTransfertFacturePFEnBL> findBLByClient(String codeclient);
	
	public HistoriqueTransfertFacturePFEnBL findFactureVentePFByNumBL(String NumBL);
	

	public List<HistoriqueTransfertFacturePFEnBL> listeFacturePFEntreDeuxDatesOrderByDate(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin);
	public List<HistoriqueTransfertFacturePFEnBL> listeFacturePFEntreDeuxDatesOrderByNumFacture(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin);
	public List<HistoriqueTransfertFacturePFEnBL> listeHistoriqueTransfertFacturePFEnBLByRequet(String req);
}
