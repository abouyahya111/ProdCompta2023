package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.Journee;

public interface JourneeDAO {
	
	 public Journee findById(int id);
		
		public void add(Journee journee);
		
		public  Journee edit(Journee e);
		
		public  void delete(Journee e); 
		
		public List<Journee> findAll();
		  public List<Journee> findByDateEtat(Date date, String etat);
		  public Journee findByDateEtatOuverte(Date date, String etat,int depot);
               
                public Journee findJourneeByDetailOverte( String etat,int depot) ;
}
