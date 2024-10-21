package dao.daoManager;

import java.sql.Date;
import java.util.List;

import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAchat;
import dao.entity.FactureAchatMP;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.Fournisseur;

public interface FactureAchatMPDAO {
	
	public  void add(FactureAchatMP e);
	
	public  FactureAchatMP edit(FactureAchatMP e);
	
	public  void delete(int id); 
	
	public List<FactureAchatMP> findAll();
	
	public FactureAchatMP findById(int id);
	
	public FactureAchatMP findFactureAchatByEtat(String etat);
	public FactureAchatMP findByNumFacture(String NumFacture);
	public List<FactureAchatMP> findByFournisseur(String codefournisseur);
	public List<FactureAchatMP> findByDate(java.util.Date datefacture);
	public List<FactureAchatMP> findByDepot(int iddepot);
	public List<FactureAchatMP> findByNumFcatureFournisseurDateFactureDepot(String NumFacture,Fournisseur fournisseur,java.util.Date datefacture,Depot depot);
	public List<FactureAchatMP> findByNumFcatureFournisseurDateFactureDepotEtatRegle(String NumFacture,Fournisseur fournisseur ,java.util.Date datefacture,Depot depot,String etat) ;
	public FactureAchatMP findByNumFactureByDepot(String NumFacture , Depot depot);
	public FactureAchatMP findFacturePFByCodeTransfer(String code);
}
