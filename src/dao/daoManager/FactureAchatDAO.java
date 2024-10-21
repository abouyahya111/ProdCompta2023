package dao.daoManager;

import java.sql.Date;
import java.util.List;

import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.Fournisseur;

public interface FactureAchatDAO {
	
	public  void add(FactureAchat e);
	
	public  FactureAchat edit(FactureAchat e);
	
	public  void delete(int id); 
	
	public List<FactureAchat> findAll();
	
	public FactureAchat findById(int id);
	
	public FactureAchat findFactureAchatByEtat(String etat);
	public FactureAchat findByNumFacture(String NumFacture);
	public List<FactureAchat> findByFournisseur(String codefournisseur);
	public List<FactureAchat> findByDate(java.util.Date datefacture);
	public List<FactureAchat> findByDepot(int iddepot);
	public List<FactureAchat> findByNumFcatureFournisseurDateFactureDepot(String NumFacture,Fournisseur fournisseur,java.util.Date datefacture,Depot depot);
	public List<FactureAchat> findByNumFcatureFournisseurDateFactureDepotEtatRegle(String NumFacture,Fournisseur fournisseur ,java.util.Date datefacture,Depot depot,String etat) ;
	public FactureAchat findByNumFactureBydepot(String NumFacture , Depot depot);
	public FactureAchat findFacturePFByCodeTransfer(String code);
	public List<FactureAchat> findFacturePFBetweenNumFacture(String du );
}
