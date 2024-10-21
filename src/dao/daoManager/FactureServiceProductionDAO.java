package dao.daoManager;

import java.sql.Date;
import java.util.List;

import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FactureServiceProduction;
import dao.entity.FactureVenteMP;
import dao.entity.SousFamilleArticlePF;

public interface FactureServiceProductionDAO {
	
	public  void add(FactureServiceProduction e);
	
	public  FactureServiceProduction edit(FactureServiceProduction e);
	
	public  void delete(int id); 
	
	public List<FactureServiceProduction> findAll();
	
	public FactureServiceProduction findById(int id);
	
	public FactureServiceProduction findFactureServiceProductionByEtat(String etat);
	public List<FactureServiceProduction> findByNumFacture(String NumFacture);
	public List<FactureServiceProduction> findByClient(String codeclient);
	public List<FactureServiceProduction> findByDate(java.util.Date datefacture);
	public List<FactureServiceProduction> findByDepot(int iddepot);
	public List<FactureServiceProduction> findByNumFcatureClientDateFactureDepot(String NumFacture,Client clientpf,java.util.Date datefacture,Depot depot);
	//public List<FactureServiceProduction> findByNumFcatureClientDateFactureDepotEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot,String etat);
	//public List<FactureServiceProduction> findByNumFcatureClientDateFactureFournisseurEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Client fournisseur,String etat,Depot depot);
	//public List<FactureServiceProduction> findByNumFcatureClientDateFactureDepotEtatRegleFacturé(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot,String etat);
	//public List<FactureServiceProduction> findByNumFcatureClientDateFactureDepotEtatRegleFacture(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot);
	public List<FactureServiceProduction> findFactureEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin,Depot depot);
	//public List<FactureServiceProduction> findFactureByClient(String codeclient);
	//public FactureServiceProduction findFacturePFByCodeTransfer(String code);
	public FactureServiceProduction findByNumOF(String numOF);
	public List<FactureServiceProduction> findByNumFcatureClientDateFactureDepotEtatRegle(String NumFacture,Client client,java.util.Date datefacture,java.util.Date datefactureAu,Depot depot,String etat);
	public List<FactureServiceProduction> findBetweenNumFacture();
	public List<Object[]> listeFactureServiceClientAvecOuSansICE(java.util.Date date,java.util.Date date2 , Depot depot , String ice);

	public FactureServiceProduction findByNumFcatureByDate(String numFacture,java.util.Date datefacture);
	}
