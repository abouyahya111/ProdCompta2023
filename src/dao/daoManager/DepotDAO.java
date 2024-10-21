package dao.daoManager;

import java.util.List;

import dao.entity.Depot;
import dao.entity.Magasin;

public interface DepotDAO {
	
	public  void add(Depot e);
	
	public  Depot edit(Depot e);
	
	public  void delete(int id); 
	
	public List<Depot> findAll();
	
	public Depot findById(int id);
	
	public Depot findByCode(String code);
	
	public List<Magasin> listeMagasinByTypeMagasin(String codeType);
	
	public List<Magasin> listeMagasinByTypeMagasinDepot(int idDepot,String codeType);

	public List<Depot>  findDepotByCodeSaufEnParametre(String code);

	public Magasin magasinByCode(String codeMagasin);
	
	public List<Magasin> listeMagasinByTypeMagasinDepotMachine(int idDepot,String codeType,String codeMachine);

	public List<Magasin> findMagasinByCodeSaufEnParametre(int idDepot,String codeCatMagasin, String typeMagasin);
	public List<Magasin> listeMagasinByCategorieinDepot(int idDepot);
	public List<Magasin> findMagasinByCategorieTypeMagasinDepot(String codeCatMagasin,String typeMagasin,int idDepot);
	public Magasin MagasinByTypeMagasinStkAttente(String codeType) ;
	public List<Magasin> findMagasinMPAutreDepot(String typeMagasin,int idMagasin);
	public List<Magasin> findMagasinPFAutreDepot(String typeMagasin,int idDepot);
	public List<Magasin> findMagasinByCategorieByType( String codeCatMagasin,String typeMagasin) ;
	public Magasin magasinByCodeMachineByTypeMagasin(String codeType,String codeMachine);
	public Magasin MagasinByLibelle(String libelle);
}
