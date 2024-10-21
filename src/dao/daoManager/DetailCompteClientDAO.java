/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.daoManager;

import dao.entity.Client;
import dao.entity.CompteClient;
import dao.entity.Depot;
import dao.entity.DetailCompteClient;
import java.util.List;

/**
 *
 * @author Hp
 */
public interface DetailCompteClientDAO {
    
                public DetailCompteClient findById(int id);
		
		public void add(DetailCompteClient detailCompteClient);
		
		public  DetailCompteClient edit(DetailCompteClient e);
		
		public  void delete(DetailCompteClient e); 
		
		public List<DetailCompteClient> findAll();
		 public DetailCompteClient findByFacture(int idfacture);      
		 public List<DetailCompteClient> findByFournisseurEtClient(CompteClient idcompteclient,Client idfournisseur) ;
		 public DetailCompteClient findByFactureAvoirClient(int idfacture);
		 public DetailCompteClient findByFactureAutresVente(int idfacture);
		 public List<DetailCompteClient> findAllByDepot(Depot depot);
}
