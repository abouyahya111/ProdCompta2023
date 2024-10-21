package dao.daoManager;

import java.sql.Date;
import java.util.List;

import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAchat;
import dao.entity.FactureAvoirPF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.Fournisseur;

public interface FactureAvoirPFDAO {
	
	public  void add(FactureAvoirPF e);
	
	public  FactureAvoirPF edit(FactureAvoirPF e);
	
	public  void delete(int id); 
	
	public List<FactureAvoirPF> findAll();
	
	public FactureAvoirPF findById(int id);
	
	public FactureAvoirPF findFactureAvoirByEtat(String etat);
	public FactureAvoirPF findByNumFacture(String NumFacture);
	public List<FactureAvoirPF> findByFournisseur(String codefournisseur);
	public List<FactureAvoirPF> findByDate(java.util.Date datefacture);
	public List<FactureAvoirPF> findByDepot(int iddepot);
	public List<FactureAvoirPF> findByNumFcatureFournisseurDateFactureDepot(String NumFacture,Fournisseur fournisseur,java.util.Date datefacture,Depot depot);
	public List<FactureAvoirPF> findByNumFcatureFournisseurDateFactureDepotEtatRegle(String NumFacture,Fournisseur fournisseur ,java.util.Date datefacture,Depot depot,String etat) ;
	public FactureAvoirPF findByNumFactureByDepot(String NumFacture, Depot depot);
	public FactureAvoirPF findFactureAvoirPFByCodeTransfer(String code);
}
