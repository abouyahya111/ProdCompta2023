package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAchatMP;
import dao.entity.DetailFactureAvoirMP;
import dao.entity.DetailFacturePF;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;

public interface DetailFactureAvoirMPDAO {
	
	public  void add(DetailFactureAvoirMP e);
	
	public  DetailFactureAvoirMP edit(DetailFactureAvoirMP e);
	
	public  void delete(int id); 
	
	public List<DetailFactureAvoirMP> findAll();
	
	public DetailFactureAvoirMP findById(int id);
	
	public List<DetailFactureAvoirMP> listeDetailFactureAvoirByFacture(int idFacture);
	
	public List<DetailFactureAvoirMP> listeDetailFactureAvoirByDate(Date dateDebut,Date dateFin);
	public void delete(DetailFactureAvoirMP p);

}
