package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAchatMP;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureVenteMP;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;

public interface DetailFactureVenteMPDAO {
	
	public  void add(DetailFactureVenteMP e);
	
	public  DetailFactureVenteMP edit(DetailFactureVenteMP e);
	
	public  void delete(int id); 
	
	public List<DetailFactureVenteMP> findAll();
	
	public DetailFactureVenteMP findById(int id);
	
	public List<DetailFactureVenteMP> listeDetailFactureVenteByFacture(int idFacture);
	
	public List<DetailFactureVenteMP> listeDetailFactureVenteByDate(Date dateDebut,Date dateFin);
	public void delete(DetailFactureVenteMP p);

}
