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
import dao.entity.HistoriqueVentevendeur;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface HistoriqueVenteVendeurDAO {
	
	public  void add(HistoriqueVentevendeur e);
	
	public  HistoriqueVentevendeur edit(HistoriqueVentevendeur e);
	
	public  void delete(int id); 
	
	public List<HistoriqueVentevendeur> findAll();
	
	public HistoriqueVentevendeur findById(int id);
	
	public HistoriqueVentevendeur findFacturePFByEtat(String etat);
	public HistoriqueVentevendeur findByNumFacture(String NumFacture, Depot depot);
	public List<HistoriqueVentevendeur> findByClient(String codeclient);
	public List<HistoriqueVentevendeur> findByDate(java.util.Date datefacture);
	public List<HistoriqueVentevendeur> findByDepot(int iddepot);
	public List<HistoriqueVentevendeur> findByNumFcatureClientDateFactureDepot(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot);
	public List<HistoriqueVentevendeur> findByNumFcatureClientDateFactureDepotEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacturedebut,java.util.Date datefacturefin,Depot depot,String typeBL );
	public List<HistoriqueVentevendeur> findByNumFcatureClientDateFactureFournisseurEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Client fournisseur,String etat,Depot depot);
	public List<HistoriqueVentevendeur> findByNumFcatureClientDateFactureDepotEtatRegleFacturé(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot,String etat);
//	public List<FacturePF> findByNumFcatureClientDateFactureDepotEtatRegleFacture(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Depot depot);
	public List<HistoriqueVentevendeur> findByNumFcatureClientDateFactureDepotTypeNonBL(String NumFacture,ClientPF clientpf,java.util.Date datefacturedebu,java.util.Date datefacturefin, Depot depot,String type);
	public List<HistoriqueVentevendeur> findByNumFcatureClientDateFactureDepotEtatRegleFacture(String NumFacture,ClientPF clientpf,java.util.Date datefacture,java.util.Date dateAu, Depot depot) ;
	public List<HistoriqueVentevendeur> findFactureEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin,Depot depot);
	
	public List<HistoriqueVentevendeur> findFactureByClient(String codeclient);
	public List<HistoriqueVentevendeur> findBLByClient(String codeclient);
	public HistoriqueVentevendeur findFacturePFByCodeTransfer(String code);
	public HistoriqueVentevendeur findFactureVentePFByNumBL(String NumBL);
	public List<HistoriqueVentevendeur> listBLGratuite(Articles article);
	public int maxIdFacture();
	public List<HistoriqueVentevendeur> listeFacturePFByNumFacture(Magasin magasin);
	public List<HistoriqueVentevendeur> listeFacturePFBetweenNumFacture(Depot depot , String du);
	public List<HistoriqueVentevendeur> listeFacturePFEntreDeuxDatesOrderByDate(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin);
	public List<HistoriqueVentevendeur> listeFacturePFEntreDeuxDatesOrderByNumFacture(java.util.Date datedu,java.util.Date dateAu ,Magasin magasin);
	public List<HistoriqueVentevendeur> findFactureByTypeByDepot(String type , Depot depot);
	public HistoriqueVentevendeur findFactureVentePFByNumBLByDepot(String NumBL, Depot depot);
}
