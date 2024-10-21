package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;

public interface DetailFactureServiceProductionDAO {
	
	public  void add(DetailFactureServiceProduction e);
	
	public  DetailFactureServiceProduction edit(DetailFactureServiceProduction e);
	
	public  void delete(int id); 
	
	public List<DetailFactureServiceProduction> findAll();
	
	public DetailFactureServiceProduction findById(int id);
	
	public List<DetailFactureServiceProduction> listeDetailFactureServiceProductionByFacture(int idFacture);
	
	public List<DetailFactureServiceProduction> listeDetailFactureServiceProductionByDate(Date dateDebut,Date dateFin);
	public List<DetailFactureServiceProduction> findByNumFcatureClientDateFactureDepotEtatRegle(String NumFacture,Client client,java.util.Date datedebut,java.util.Date datefin ,Depot depot);
	public DetailFactureServiceProduction DetailFactureServiceProductionByFactureByArticle(int idfacture,String article);
	public List<DetailFactureServiceProduction> listeDetailFactureServiceProductionByDepotByReq(int idDepot,String req);
	public void ViderSession();
	public List<Object[]>  SommeMontantParArticleService(Client client ,java.util.Date datedebut,java.util.Date datefin);
	public List<Object[]> listeEtatChiffreAffaireClientSansICEProductionService(String req );
	public List<Object[]> listeEtatChiffreAffaireClientAvecICEProductionService(String req );
	public List<DetailFactureServiceProduction> findByNumFcatureClientDateFactureDepotEtatRegleOrdreByArticleBySousFamille(String numFacture,Client client,java.util.Date datedebut,java.util.Date datefin ,Depot depot);
}
