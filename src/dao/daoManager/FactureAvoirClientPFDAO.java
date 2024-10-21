package dao.daoManager;

import java.sql.Date;
import java.util.List;

import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAchat;
import dao.entity.FactureAvoirClientPF;
import dao.entity.FactureAvoirPF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.Fournisseur;
import dao.entity.Magasin;

public interface FactureAvoirClientPFDAO {
	
	public  void add(FactureAvoirClientPF e);
	
	public  FactureAvoirClientPF edit(FactureAvoirClientPF e);
	
	public  void delete(int id); 
	
	public List<FactureAvoirClientPF> findAll();
	
	public FactureAvoirClientPF findById(int id);
	
	public FactureAvoirClientPF findFactureAvoirByEtat(String etat);
	public FactureAvoirClientPF findByNumFacture(String NumFacture);
	public List<FactureAvoirClientPF> findByClientPF(ClientPF clientpf);
	public List<FactureAvoirClientPF> findByDate(java.util.Date datefacture);
	public List<FactureAvoirClientPF> findByDepot(int iddepot);
	public List<FactureAvoirClientPF> findByNumFcatureClientPFDateFactureDepot(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot);
	public List<FactureAvoirClientPF> findByNumFcatureClientPFDateFactureDepotEtatRegle(String NumFacture,ClientPF clientpf ,java.util.Date datefacture,Depot depot) ;
	public List<FactureAvoirClientPF> findByNumFactureByDepot(String NumFacture, Depot depot);
	public FactureAvoirClientPF findFactureAvoirPFByCodeTransfer(String code);
	public FactureAvoirClientPF findByNumBLByDepot(String NumBL, Depot depot);
	public List<FactureAvoirClientPF> findByNumFcatureClientDateFactureDepotTypeNonBL(String NumFacture,ClientPF clientpf,java.util.Date datefacturedebu,java.util.Date datefacturefin, Magasin magasin,String type);
}
