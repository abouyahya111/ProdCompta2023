package dao.daoManager;

import java.util.List;

import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.TypeVente;

public interface TypeVenteDAO {
	
	 public void add(TypeVente tSypeVente);

	    public TypeVente findById(int id);

	    public TypeVente edit(TypeVente e);

	    public void delete(TypeVente e);

	    public List<TypeVente> findAll();
	    
	     public TypeVente findTypeVenteByCodeTypeVente(String code);
	      
	
	       
	      
}
