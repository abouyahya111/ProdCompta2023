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
import dao.entity.HistoriqueTransfertFactureAvoirClientPFEnBL;
import dao.entity.HistoriqueTransfertFacturePFEnBL;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface HistoriqueTransfertFactureAvoirClientPFEnBLDAO {
	
	public  void add(HistoriqueTransfertFactureAvoirClientPFEnBL e);
	
	public  HistoriqueTransfertFactureAvoirClientPFEnBL edit(HistoriqueTransfertFactureAvoirClientPFEnBL e);
	
	public  void delete(int id); 
	
	public List<HistoriqueTransfertFactureAvoirClientPFEnBL> findAll();
	
	public HistoriqueTransfertFactureAvoirClientPFEnBL findById(int id);
	
	public HistoriqueTransfertFactureAvoirClientPFEnBL findFactureAvoirClientPFByEtat(String etat);
	public List<HistoriqueTransfertFactureAvoirClientPFEnBL> findByNumFacture(String NumFacture, Depot depot);
	public List<HistoriqueTransfertFactureAvoirClientPFEnBL> findByClient(String codeclient);
	public List<HistoriqueTransfertFactureAvoirClientPFEnBL> findByDate(java.util.Date datefacture);
	public List<HistoriqueTransfertFactureAvoirClientPFEnBL> findByDepot(int iddepot);
		public List<HistoriqueTransfertFactureAvoirClientPFEnBL> findFactureEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin,Depot depot);
	
	public List<HistoriqueTransfertFactureAvoirClientPFEnBL> findFactureByClient(String codeclient);
	public List<HistoriqueTransfertFactureAvoirClientPFEnBL> findBLByClient(String codeclient);
	
	public HistoriqueTransfertFactureAvoirClientPFEnBL findFactureAvoirClientPFByNumBL(String NumBL);
	

	public List<HistoriqueTransfertFactureAvoirClientPFEnBL> listeFacturePFEntreDeuxDatesOrderByDate(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin);
	public List<HistoriqueTransfertFactureAvoirClientPFEnBL> listeFacturePFEntreDeuxDatesOrderByNumFacture(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin);
	public List<HistoriqueTransfertFactureAvoirClientPFEnBL> listeHistoriqueTransfertFactureAvoirClientPFEnBLByRequet(String req);
}
