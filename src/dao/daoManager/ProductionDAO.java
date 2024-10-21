package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Magasin;
import dao.entity.Production;

public interface ProductionDAO {
	
	public  void add(Production e);
	
public  Production edit(Production e);
	
	public  void delete(int id); 
	
	public List<Production> findAll();
	
	public Production findById(int id);
	
	public int maxIdProduction();
	
	public Production findByNumOF(String numOF,String codeDepot);
	
	public List<CoutMP>  listeCoutMP(int idPord);

	public List<Production> listeProductionByDateByPeriode(Date dateProd,String periode);

	public List<DetailProduction> listeDetailProduction(int idPord);

	public List<DetailProdGen> listeDetailProdGen(int idPord);
	
	public List<DetailResponsableProd> listeDetailResponsableProd(int idPord);

	public List<Production> listeProductionEntreDeuxDate(Date dateDebut, Date dateFin);
	public List<Production> listeProductionTerminerEntreDeuxDate(Date dateDebut, Date dateFin);
	public List<Production>  listeProductionTerminerbyDepotEntreDeuxDate(Date dateDebut,Date dateFin,String statut,String depot , String numof);
	public Production findByNumOFStatut(String numOF,String statut);
	public List<CoutMP> listeCoutMPBYNumOF(String NumOF);
	public List<Production> listeProductionServiceTerminerbyDepotEntreDeuxDate(Date dateDebut,Date dateFin,String statut,String depot , String service);
	public List<Production> listeProductionServiceTerminer(String statut,String depot , String service);
	public List<Production> listeProductionGroupByArticle(String statut,String depot , String service);
	public List<Production> listeProductionTerminerBydepotByReq(String depot , String req);
	public List<Production> listeProductionGroupByMagasinPF(String statut,String depot , String service);
	public List<CoutMP> listeCoutMPBYDateByMagasin(Date dateDebut,Date dateFin,String statut,Magasin magasin );
	public List<Production> listeProductionServiceNonFacturer(String creer,String lancer,String depot , String service , Date date);
	public List<Object[]> listeArticleFabriqueParMagasinParSousFamille( Magasin magasin) ;
	public List<Object[]> listeMPConsommeParArticleParMagasin  (Date dateDebut,Date dateFin,String statut,Magasin magasin,Magasin magasinMP );
	public List<Object[]> listeArticleFabriqueParDepotParMagasin(Date dateDebut,Date dateFin,String statut,Depot depot, Magasin magasinMP );
	public List<Production> listeProductionParArticleParDepotParMagasin(Date dateDebut,Date dateFin,String statut,Depot depot, Magasin magasinMP , Articles article);
	public void ViderSession();
}
