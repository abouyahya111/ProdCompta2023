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
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface FacturePFDAO {
	
	public  void add(FacturePF e);
	
	public  FacturePF edit(FacturePF e);
	
	public  void delete(int id); 
	
	public List<FacturePF> findAll();
	
	public FacturePF findById(int id);
	
	public FacturePF findFacturePFByEtat(String etat);
	public List<FacturePF> findByNumFacture(String NumFacture, Depot depot);
	public List<FacturePF> findByClient(String codeclient);
	public List<FacturePF> findByDate(java.util.Date datefacture);
	public List<FacturePF> findByDepot(int iddepot);
	public List<FacturePF> findByNumFcatureClientDateFactureDepot(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot);
	public List<FacturePF> findByNumFcatureClientDateFactureDepotEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacturedebut,java.util.Date datefacturefin,Depot depot,String etat,String TypeBL);
	public List<FacturePF> findByNumFcatureClientDateFactureFournisseurEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Client fournisseur,String etat,Depot depot);
	public List<FacturePF> findByNumFcatureClientDateFactureDepotEtatRegleFacturé(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot,String etat);
//	public List<FacturePF> findByNumFcatureClientDateFactureDepotEtatRegleFacture(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot);
	public List<FacturePF> findByNumFcatureClientDateFactureDepotTypeNonBL(String NumFacture,ClientPF clientpf,java.util.Date datefacturedebu,java.util.Date datefacturefin, Magasin magasin,String type);
	public List<FacturePF> findByNumFcatureClientDateFactureDepotEtatRegleFacture(String NumFacture,ClientPF clientpf,java.util.Date datefacture,java.util.Date dateAu, Depot depot, String req) ;
	public List<FacturePF> findFactureEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin,Depot depot);
	
	public List<FacturePF> findFactureByClient(String codeclient);
	public List<FacturePF> findBLByClient(String codeclient);
	public FacturePF findFacturePFByCodeTransfer(String code);
	public FacturePF findFactureVentePFByNumBL(String NumBL);
	public FacturePF findFactureVentePFByNumFacture(String numfacture);
	public List<FacturePF> listBLGratuite(Articles article);
	public int maxIdFacture();
	public List<FacturePF> listeFacturePFByNumFacture(Magasin magasin);
	public List<FacturePF> listeFacturePFBetweenNumFacture(Depot depot , String du);
	public List<FacturePF> listeFacturePFEntreDeuxDatesOrderByDate(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin);
	public List<FacturePF> listeFacturePFEntreDeuxDatesOrderByNumFacture(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin);
	public List<FacturePF> findFacturePFByRequete(String req);
	public List<FacturePF> listeFacturePFNonFacturer(java.util.Date datedu,Depot depot , String type);
	public FacturePF findFactureVentePFByNumBLByDepot(String NumBL, Depot depot);
	public List<FacturePF> findByNumFactureByMagasin(String NumFacture, int  magasin);
}
