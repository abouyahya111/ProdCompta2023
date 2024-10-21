package dao.daoManager;

import java.sql.Date;
import java.util.List;

import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAchat;
import dao.entity.FactureAchatMP;
import dao.entity.FactureAvoirMP;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.Fournisseur;

public interface FactureAvoirMPDAO {
	
	public  void add(FactureAvoirMP e);
	
	public  FactureAvoirMP edit(FactureAvoirMP e);
	
	public  void delete(int id); 
	
	public List<FactureAvoirMP> findAll();
	
	public FactureAvoirMP findById(int id);
	
	public FactureAvoirMP findFactureAvoirByEtat(String etat);
	public FactureAvoirMP findByNumFacture(String NumFacture);
	public List<FactureAvoirMP> findByFournisseur(String codefournisseur);
	public List<FactureAvoirMP> findByDate(java.util.Date datefacture);
	public List<FactureAvoirMP> findByDepot(int iddepot);
	public List<FactureAvoirMP> findByNumFcatureFournisseurDateFactureDepot(String NumFacture,Fournisseur fournisseur,java.util.Date datefacture,Depot depot);
	public List<FactureAvoirMP> findByNumFcatureFournisseurDateFactureDepotEtatRegle(String NumFacture,Fournisseur fournisseur ,java.util.Date datefacture,Depot depot,String etat) ;
	public FactureAvoirMP findByNumFactureByDepot(String NumFacture,Depot depot);
	public FactureAvoirMP findFactureAvoirMPByCodeTransfer(String code);
}
