package dao.daoManager;

import java.sql.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureServiceTransport;
import dao.entity.FactureProduction;
import dao.entity.FactureServiceTransport;
import dao.entity.FactureVenteMP;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface FactureServiceTransportDAO {
	
	public  void add(FactureServiceTransport e);
	
	public  FactureServiceTransport edit(FactureServiceTransport e);
	
	public  void delete(int id); 
	
	public List<FactureServiceTransport> findAll();
	
	public FactureServiceTransport findById(int id);
	
	public FactureServiceTransport findFactureServiceTransportByEtat(String etat);
	public List<FactureServiceTransport> findByNumFacture(String NumFacture, Depot depot);
	public List<FactureServiceTransport> findByClient(String codeclient);
	public List<FactureServiceTransport> findByDate(java.util.Date datefacture);
	public List<FactureServiceTransport> findByDepot(int iddepot);
	public List<FactureServiceTransport> findByNumFcatureClientDateFactureDepot(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot);
	public List<FactureServiceTransport> findByNumFcatureClientDateFactureDepotEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacturedebut,java.util.Date datefacturefin,Depot depot,String etat);
	public List<FactureServiceTransport> findByNumFcatureClientDateFactureFournisseurEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Client fournisseur,String etat,Depot depot);
	public List<FactureServiceTransport> findByNumFcatureClientDateFactureDepotEtatRegleFacturé(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot,String etat);
//	public List<FactureServiceTransport> findByNumFcatureClientDateFactureDepotEtatRegleFacture(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot);
	public List<FactureServiceTransport> findByNumFcatureClientDateFactureDepotTypeNonBL(String NumFacture,ClientPF clientpf,java.util.Date datefacturedebu,java.util.Date datefacturefin, Magasin magasin,String type);
	public List<FactureServiceTransport> findByNumFcatureClientDateFactureDepotEtatRegleFacture(String NumFacture,ClientPF clientpf,java.util.Date datefacture,java.util.Date dateAu, Depot depot) ;
	public List<FactureServiceTransport> findFactureEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin,Depot depot);
	
	public List<FactureServiceTransport> findFactureByClient(String codeclient);
	public List<FactureServiceTransport> findBLByClient(String codeclient);
	public FactureServiceTransport findFactureServiceTransportByCodeTransfer(String code);
	public FactureServiceTransport findFactureVentePFByNumBL(String NumBL);
	public List<FactureServiceTransport> listBLGratuite(Articles article);
	public int maxIdFacture();
	public List<FactureServiceTransport> listeFactureServiceTransportByNumFacture(Magasin magasin);
	public List<FactureServiceTransport> listeFactureServiceTransportBetweenNumFacture(Depot depot , String du);
	public List<FactureServiceTransport> listeFactureServiceTransportEntreDeuxDatesOrderByDate(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin);
	public List<FactureServiceTransport> listeFactureServiceTransportEntreDeuxDatesOrderByNumFacture(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin);
	public List<FactureServiceTransport> findFactureServiceTransportByRequete(String req);
	public List<FactureServiceTransport> listeFactureNonFacturer(java.util.Date datedu,Depot depot , String type);
}
