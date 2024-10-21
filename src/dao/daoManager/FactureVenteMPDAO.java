package dao.daoManager;

import java.sql.Date;
import java.util.List;

import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAchat;
import dao.entity.FactureAchatMP;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FactureVenteMP;
import dao.entity.Fournisseur;

public interface FactureVenteMPDAO {
	
	public  void add(FactureVenteMP e);
	
	public  FactureVenteMP edit(FactureVenteMP e);
	
	public  void delete(int id); 
	
	public List<FactureVenteMP> findAll();
	
	public FactureVenteMP findById(int id);
	
	public FactureVenteMP findFactureVenteMPByEtat(String etat);
	public List<FactureVenteMP> findByNumFacture(String NumFacture);
	public List<FactureVenteMP> findByClient(String codeclient);
	public List<FactureVenteMP> findByDate(java.util.Date datefacture);
	public List<FactureVenteMP> findByDepot(int iddepot);
	public List<FactureVenteMP> findByNumFactureClientDateFactureDepot(String NumFacture,Client client,java.util.Date datefacture,Depot depot);
	public List<FactureVenteMP> findByNumFactureClientDateFactureDepotEtatRegle(String NumFacture,Client client,java.util.Date datefacture,Depot depotDestination,String etat,Depot depotSource);
	//public List<FactureVenteMP> findByNumFcatureClientDateFactureFournisseurEtatRegle(String NumFacture,Client client,java.util.Date datefacture,Client fournisseur,String etat,Depot depot);
	public List<FactureVenteMP> findByNumFactureClientDateFactureDepotEtatRegleFacturé(String NumFacture,Client client,java.util.Date datefacture,Depot depot,String etat);
	public List<FactureVenteMP> findByNumFactureClientDateFactureDepotEtatRegleFacture(String NumFacture,Client client,java.util.Date datefacture,Depot depotSource,Depot depotDestination);
	public List<FactureVenteMP> findFactureEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin);
	public List<FactureVenteMP> findFactureByClient(String codeclient);
	public FactureVenteMP findFactureVenteMPByNumBL(String NumBL);
	public FactureVenteMP findFacturePFByCodeTransfer(String code);
	public List<FactureVenteMP> findByNumFacturByDepote(String NumFacture, Depot depot);
	public FactureVenteMP findFactureVenteMPByNumBLByDepot(String NumBL, Depot depot);
	
}
