package dao.daoManager;

import java.sql.Date;
import java.util.List;

import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAchat;
import dao.entity.FactureAvoirPF;
import dao.entity.FactureAvoirPFComparaison;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.Fournisseur;

public interface FactureAvoirPFComparaisonDAO {
	
	public  void add(FactureAvoirPFComparaison e);
	
	public  FactureAvoirPFComparaison edit(FactureAvoirPFComparaison e);
	
	public  void delete(int id); 
	
	public List<FactureAvoirPFComparaison> findAll();
	
	public FactureAvoirPFComparaison findById(int id);
	
	public FactureAvoirPFComparaison findFactureAvoirByEtat(String etat);
	public FactureAvoirPFComparaison findByNumFacture(String NumFacture);
	public List<FactureAvoirPFComparaison> findByFournisseur(String codefournisseur);
	public List<FactureAvoirPFComparaison> findByDate(java.util.Date datefacture);
	public List<FactureAvoirPFComparaison> findByDepot(int iddepot);
	public List<FactureAvoirPFComparaison> findByNumFcatureFournisseurDateFactureDepot(String NumFacture,Fournisseur fournisseur,java.util.Date datefacture,Depot depot);
	public List<FactureAvoirPFComparaison> findByNumFcatureFournisseurDateFactureDepotEtatRegle(String NumFacture,Fournisseur fournisseur ,java.util.Date datefacture,Depot depot,String etat) ;
	public FactureAvoirPFComparaison findByNumFactureByDepot(String NumFacture, Depot depot);
	public FactureAvoirPFComparaison findFactureAvoirPFByCodeTransfer(String code);
}
