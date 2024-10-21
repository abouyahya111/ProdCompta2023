package dao.daoManager;

import java.sql.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.FactureAutresVente;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FactureVenteMP;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface FactureAutresVenteDAO {
	
	public  void add(FactureAutresVente e);
	
	public  FactureAutresVente edit(FactureAutresVente e);
	
	public  void delete(int id); 
	
	public List<FactureAutresVente> findAll();
	
	public FactureAutresVente findById(int id);
	
	public FactureAutresVente findFacturePFByEtat(String etat);
	public List<FactureAutresVente> findByNumFacture(String NumFacture, Depot depot);
	public List<FactureAutresVente> findByClient(String codeclient);
	public List<FactureAutresVente> findByDate(java.util.Date datefacture);
	public List<FactureAutresVente> findByDepot(int iddepot);
	public List<FactureAutresVente> findBLByClient(String codeclient);
	public List<FactureAutresVente> findFactureEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Depot depot);
	public List<FactureAutresVente> findFacturePFByRequete(String req);
	public List<FactureAutresVente> findFactureByClient(String codeclient);
	public List<FactureAutresVente> findByNumFcatureClientDateFactureDepotEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacturedebu,java.util.Date datefacturefin, Depot depot,String etat);
	public List<FactureAutresVente> findByNumFcatureClientDateFactureDepotEtatRegleFacture(String NumFacture,ClientPF clientpf,java.util.Date datefacture,java.util.Date dateAu, Depot depot);
	public List<FactureAutresVente> findByNumFcatureClientDateFactureFournisseurEtatRegle(String NumFacture,ClientPF clientpf,java.util.Date datefacture,Client fournisseur,String etat,Depot depot) ;
	public List<FactureAutresVente> listFactureAutresVenteNonFacturer(java.util.Date datedu,Depot depot , String type);
	public FactureAutresVente findFactureVentePFByNumBL(String NumBL);
}
