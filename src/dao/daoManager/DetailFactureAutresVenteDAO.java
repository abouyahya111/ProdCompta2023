package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.ClientPF;
import dao.entity.Depot;
import dao.entity.DetailFactureAutresVente;
import dao.entity.DetailFacturePF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.SousFamilleArticlePF;

public interface DetailFactureAutresVenteDAO {
	
	public  void add(DetailFactureAutresVente e);
	
	public  DetailFactureAutresVente edit(DetailFactureAutresVente e);
	
	public  void delete(int id); 
	
	public List<DetailFactureAutresVente> findAll();
	
	public DetailFactureAutresVente findById(int id);
	
	public List<DetailFactureAutresVente> listeDetailFacturePFByFacture(int idFacture);
	
	public List<DetailFactureAutresVente> listeDetailFacturePFByDate(Date dateDebut,Date dateFin, Depot depot);
	public List<DetailFactureAutresVente> listeDetailFacturePFByNumFacture(String Numfacture, Depot depot);
	public List<DetailFactureAutresVente> findDetailBLEntreDeuxDate(java.util.Date dateDebut,java.util.Date datedeFin , Depot depot,ClientPF client, FamilleArticlePF familleArticle, Articles article, String tousfamille, String touarticle , String type);
	public void ViderSession();
	}
