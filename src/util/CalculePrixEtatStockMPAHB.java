package util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

import main1.AuthentificationView;
import main1.ProdLauncher;
import dao.daoImplManager.CompteurAbsenceEmployeDAOImpl;
import dao.daoImplManager.CompteurNumDossierDAOImpl;
import dao.daoImplManager.CompteurTransferMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.HabilitationDAOImpl;
import dao.daoImplManager.MenuDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.PrixClientMPDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.UtilisateurDAOImpl;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.CompteurNumDossierDAO;
import dao.daoManager.CompteurTransferMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.HabilitationDAO;
import dao.daoManager.MenuDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.PrixClientMPDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.UtilisateurDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.CompteurAbsenceEmploye;
import dao.entity.CompteurNumDossier;
import dao.entity.CompteurProduction;
import dao.entity.CompteurTransferMP;
import dao.entity.Depot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatStockMP;
import dao.entity.EtatStockMPAHB;
import dao.entity.FacturePF;
import dao.entity.Habilitation;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Menu;
import dao.entity.Parametre;
import dao.entity.PrixClientMP;
import dao.entity.Sequenceur;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.Utilisateur;

public class CalculePrixEtatStockMPAHB implements Constantes{
	
private static List<EtatStockMPAHB> listEtatStockMP =new ArrayList<EtatStockMPAHB>();
	
	//******************************************* Listes Pour Mouvement de Stock Mp **********************************************//
	
	
	private static List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPBytypetransfer =new ArrayList<DetailTransferStockMP>();
	private static List<DetailMouvementStock> listMouvementStockMP =new ArrayList<DetailMouvementStock>();
	private static List<DetailMouvementStock> listMouvementStockMPAfficher =new ArrayList<DetailMouvementStock>();
	private static List<DetailMouvementStock> listMouvementStockMPAfficherMouvementTmp =new ArrayList<DetailMouvementStock>();
	
	//*******************************************************************************************************************************//
	
	// ************************************************
	
	private static List<DetailTransferStockMP> listDetailTransferStockMPInitial =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPAchat =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPAchatGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPSortie =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPSortieGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPAvoir =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPAvoirGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPChargeSupp =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPChargeSuppGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPService =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPServiceGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPVente =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPVenteGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPF =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFService =new ArrayList<DetailTransferStockMP>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFGroupebyMPService =new ArrayList<DetailTransferStockMP>();
	private static List<DetailMouvementStock> listMouvementStockMPAfficherTmp =new ArrayList<DetailMouvementStock>();
	private static List<DetailTransferStockMP> listDetailTransferStockMPAllMP =new ArrayList<DetailTransferStockMP>();
		private static  Date dateDebut=null;
		private static MouvementStockGlobalDAO mouvementStockGlobaleDAO;
		private static DetailTransferMPDAO detailTransferStockMPDAO;
		private static Magasin magasin = null;
	private static Date dateFinTmp=null;
	   	private static  String NumOF=null;
public static List<EtatStockMPAHB> CalculerPrixMP(Magasin magasinTmp,Date dateFin , String OF){
	  try {
		   NumOF=OF;
		  mouvementStockGlobaleDAO = new MouvementStockGlobalDAOImpl();
         detailTransferStockMPDAO = new DetailTransferMPDAOImpl();
		  DateFormat onlyyear=new SimpleDateFormat("yyyy");
         dateFinTmp=dateFin;
        String annee=  onlyyear.format(dateFin);
         
		dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse(annee + "-01-01");
		listEtatStockMP.clear();
		
		if (magasinTmp != null) {

			magasin=magasinTmp;
			RegleStock();



		} else {
			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ", "Erreur",
					JOptionPane.ERROR_MESSAGE);
		
		}
		
		
		
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	
	
	return listEtatStockMP;
}


 static void RegleStock()
{
	 

		detailTransferStockMPDAO.ViderSession();
		listEtatStockMP.clear();
		listDetailTransferStockMPInitial.clear() ;
		 listDetailTransferStockMPAchat.clear();
		 listDetailTransferStockMPAchatGroupebyMP.clear();
		 listDetailTransferStockMPSortie.clear();
		 listDetailTransferStockMPSortieGroupebyMP.clear();
		 listDetailTransferStockMPAvoir.clear();
		 listDetailTransferStockMPAvoirGroupebyMP.clear();
		 listDetailTransferStockMPChargeSupp.clear();
		 listDetailTransferStockMPChargeSuppGroupebyMP.clear();
		  listDetailTransferStockMPService.clear();
		 listDetailTransferStockMPServiceGroupebyMP.clear();
		 listDetailTransferStockMPVente.clear();
	     listDetailTransferStockMPVenteGroupebyMP.clear();
		 listDetailTransferStockMPAllMP.clear();
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		BigDecimal montantInitial=new BigDecimal(0);
		BigDecimal quantiteTotalInitial=new BigDecimal(0);
		BigDecimal PrixInitial=new BigDecimal(0);
		BigDecimal montantachat=new BigDecimal(0);
		BigDecimal quantiteTotalachat=new BigDecimal(0);
		BigDecimal montantavoir=new BigDecimal(0);
		BigDecimal quantiteTotalavoir=new BigDecimal(0);
		BigDecimal quantiteTotalsortie=new BigDecimal(0);
		BigDecimal montantTotalsortie=new BigDecimal(0);
		BigDecimal quantiteTotalFinale=new BigDecimal(0);
		BigDecimal montantFinale=new BigDecimal(0);
		BigDecimal montantchargesupp=new BigDecimal(0);
		BigDecimal quantiteTotalchargesupp=new BigDecimal(0);
		BigDecimal montantService=new BigDecimal(0);
		BigDecimal quantiteTotalService=new BigDecimal(0);
		BigDecimal montantVente=new BigDecimal(0);
		BigDecimal quantiteTotalVente=new BigDecimal(0);
		BigDecimal montantDechet=new BigDecimal(0);
		BigDecimal quantiteTotalDechet=new BigDecimal(0);
		BigDecimal montantDechetService=new BigDecimal(0);
		BigDecimal quantiteTotalDechetService=new BigDecimal(0);
		BigDecimal montantTransferMPPF=new BigDecimal(0);
		BigDecimal quantiteTotalTransferMPPF=new BigDecimal(0);
		BigDecimal quantiteDechetaSupprimer=new BigDecimal(0);
		BigDecimal quantiteOffreaSupprimer=new BigDecimal(0);
		BigDecimal quantiteDechetaSupprimerService=new BigDecimal(0);
		BigDecimal quantiteOffreaSupprimerService=new BigDecimal(0);
		BigDecimal montantOffreService=new BigDecimal(0);
		BigDecimal quantiteTotalOffreService=new BigDecimal(0);
		BigDecimal montantOffreProduction=new BigDecimal(0);
		BigDecimal quantiteTotalOffreProduction=new BigDecimal(0);
		
		boolean trouve=false;
		MatierePremier mp=null;
		
		
		if(magasin!=null)
		{
	
 		
 		listDetailTransferStockMPInitial=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutInitial(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_INITIAL,magasin);
 		listDetailTransferStockMPAchat=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_ACHAT,magasin);
 		
 		listDetailTransferStockMPAchatGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_ACHAT,magasin);
 		listDetailTransferStockMPSortie=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutCharge(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_CHARGE,magasin);
 		listDetailTransferStockMPSortieGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutCharge(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_CHARGE,magasin);
 		
 		listDetailTransferStockMPAvoir=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAvoir(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_AVOIR,magasin);
 		listDetailTransferStockMPAvoirGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAvoir(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_AVOIR,magasin);
 		
 		listDetailTransferStockMPChargeSupp=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutChargeSupp(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin);
 		listDetailTransferStockMPChargeSuppGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutChargeSupp(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin);
 		
 		listDetailTransferStockMPService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutService(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_SERVICE,magasin);
 		listDetailTransferStockMPServiceGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutService(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_SERVICE,magasin);
 		

 		listDetailTransferStockMPVente=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_VENTE,magasin);
 		listDetailTransferStockMPVenteGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_VENTE,magasin);
 		
 		listDetailTransferStockMPTransferMPPF=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_SORTIE_PF,magasin);
 		listDetailTransferStockMPTransferMPPFGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_SORTIE_PF,magasin);
 		
 		listDetailTransferStockMPTransferMPPFService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE,magasin);
 		listDetailTransferStockMPTransferMPPFGroupebyMPService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateDebut, dateFinTmp, mp, ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE,magasin);
 		
 		
 		
 		listDetailTransferStockMPAllMP=	detailTransferStockMPDAO.findAllTransferStockMPGroupeByMP(magasin);
 		
 		for(int p=0;p<listDetailTransferStockMPAllMP.size();p++)
 		{
 			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPAllMP.get(p);
 			EtatStockMPAHB etatstock=new EtatStockMPAHB();
 			etatstock.setMp(detailtransferstockmp.getMatierePremier());
 			etatstock.setQuantiteInitial(BigDecimal.ZERO);
 			etatstock.setPrixInitial(BigDecimal.ZERO);
 			etatstock.setMontantInitial(BigDecimal.ZERO);
 			etatstock.setQuantiteAchat(BigDecimal.ZERO);
 			etatstock.setPrixAchat(BigDecimal.ZERO);
 			etatstock.setMontantAchat(BigDecimal.ZERO);
 			etatstock.setQuantiteSortie(BigDecimal.ZERO);
 			etatstock.setPrixSortie(BigDecimal.ZERO);
 			etatstock.setMontantSortie(BigDecimal.ZERO);
 			etatstock.setQuantiteService(BigDecimal.ZERO);
 			etatstock.setPrixService(BigDecimal.ZERO);
 			etatstock.setMontantService(BigDecimal.ZERO);
 			etatstock.setQuantiteDechetService(BigDecimal.ZERO);
 			etatstock.setPrixDechetService(BigDecimal.ZERO);
 			etatstock.setMontantDechetService(BigDecimal.ZERO);
 			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
 			etatstock.setPrixAvoir(BigDecimal.ZERO);
 			etatstock.setMontantAvoir(BigDecimal.ZERO);
 			etatstock.setMarge(BigDecimal.ZERO);
 			etatstock.setQuantiteDechet(BigDecimal.ZERO);
 			etatstock.setPrixDechet(BigDecimal.ZERO);
 			etatstock.setMontantDechet(BigDecimal.ZERO);
 			etatstock.setQuantiteOffreService(BigDecimal.ZERO);
 			etatstock.setPrixOffreService(BigDecimal.ZERO);
 			etatstock.setMontantOffreService(BigDecimal.ZERO);
 			etatstock.setQuantiteOffreProduction(BigDecimal.ZERO);
 			etatstock.setPrixOffreProduction(BigDecimal.ZERO);
 			etatstock.setMontantOffreProduction(BigDecimal.ZERO);
 			etatstock.setMagasin(magasin);
 			listEtatStockMP.add(etatstock);
 			
 		}
 		
 		// charger list Etat stock MP initialiser les enregistrement des achats et ventes par zero
 		for(int i=0;i<listDetailTransferStockMPInitial.size();i++)
 		{
 			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPInitial.get(i);
 			
 			PrixInitial=detailtransferstockmp.getPrixUnitaire();
 			quantiteTotalInitial=detailtransferstockmp.getQuantite();
 			montantInitial=quantiteTotalInitial.multiply(PrixInitial);
 			
 			/*
 			EtatStockMP etatstock=new EtatStockMP();
 			etatstock.setMp(detailtransferstockmp.getMatierePremier());
 			etatstock.setQuantiteInitial(detailtransferstockmp.getQuantite());
 			etatstock.setPrixInitial(detailtransferstockmp.getPrixUnitaire());
 			etatstock.setMontantInitial(detailtransferstockmp.getQuantite().multiply(detailtransferstockmp.getPrixUnitaire()));
 			etatstock.setQuantiteAchat(BigDecimal.ZERO);
 			etatstock.setPrixAchat(BigDecimal.ZERO);
 			etatstock.setMontantAchat(BigDecimal.ZERO);
 			etatstock.setQuantiteSortie(BigDecimal.ZERO);
 			etatstock.setPrixSortie(BigDecimal.ZERO);
 			etatstock.setMontantSortie(BigDecimal.ZERO);
 			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
 			etatstock.setPrixAvoir(BigDecimal.ZERO);
 			etatstock.setMontantAvoir(BigDecimal.ZERO);
 			listEtatStockMP.add(etatstock);
 		*/
 			
 			if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
	    		{
	    			
	    			for(int p=0;p<listEtatStockMP.size();p++)
	    	    	{
	    				if(listEtatStockMP.get(p).getMp().equals(listDetailTransferStockMPInitial.get(i).getMatierePremier()))
		    			{
	    					EtatStockMPAHB etatstockmp=listEtatStockMP.get(p);
	    					if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
	    					{
	    						etatstockmp.setQuantiteInitial(quantiteTotalInitial.setScale(0, RoundingMode.CEILING));
			    				etatstockmp.setPrixInitial(PrixInitial);
			    				etatstockmp.setMontantInitial(quantiteTotalInitial.setScale(0, RoundingMode.CEILING).multiply(PrixInitial));
			    				listEtatStockMP.set(p, etatstockmp);
	    					}else
	    					{
	    						etatstockmp.setQuantiteInitial(quantiteTotalInitial);
			    				etatstockmp.setPrixInitial(PrixInitial);
			    				etatstockmp.setMontantInitial(quantiteTotalInitial.multiply(PrixInitial));
			    				listEtatStockMP.set(p, etatstockmp);
	    					}
		    				
		    			
		    			}
	    	    	}
	    			
	    		}
 		}
 		
 		
 		// calculer le prix moyen et quantite achat
 		
 		
 	for(int j=0;j<listDetailTransferStockMPAchatGroupebyMP.size();j++)
 	{
 		montantachat=new BigDecimal(0);
 		quantiteTotalachat=new BigDecimal(0);
 		
 	for(int k=0;k<listDetailTransferStockMPAchat.size();k++)
 	{
 		
 		if(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPAchat.get(k).getMatierePremier()))
 		{
 			
 			if(listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
 				montantachat=montantachat.add(listDetailTransferStockMPAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAchat.get(k).getQuantite()));
	    			quantiteTotalachat=quantiteTotalachat.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPAchat.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
				}else
				{
					montantachat=montantachat.add(listDetailTransferStockMPAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAchat.get(k).getQuantite()));
	    			quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockMPAchat.get(k).getQuantite());
				}
 
 			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
 			
 		}
 		
 		
 	}
 		if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
 		{
 			/*	    			
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
 			*/
 			
 			for(int i=0;i<listEtatStockMP.size();i++)
 	    	{
 				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier()))
	    			{
 					EtatStockMPAHB etatstockmp=listEtatStockMP.get(i);
 					
 					if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
 					{
 						etatstockmp.setQuantiteAchat(quantiteTotalachat.setScale(0, RoundingMode.CEILING));
		    				etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
		    				listEtatStockMP.set(i, etatstockmp);
 						
 						
 					}else
 					{
 						
		    				etatstockmp.setQuantiteAchat(quantiteTotalachat);
		    				etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
		    				listEtatStockMP.set(i, etatstockmp);
 					}
 					
	    				
	    				
	    			}
 	    	}
 			
 			
 		}
 		
 	}
 		
 	// charger les new value de stock achat dans la liste Etat Stock MP	
 	/*
 	for(int i=0;i<listEtatStockMP.size();i++)
 	{
 		for(int j=0;j<listDetailTransferStockMPAchatGroupebyMP.size();j++)
 		{
 			if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier()))
 			{
 				EtatStockMP etatstockmp=listEtatStockMP.get(i);
 				etatstockmp.setQuantiteAchat(listDetailTransferStockMPAchatGroupebyMP.get(j).getQuantite());
 				etatstockmp.setPrixAchat(listDetailTransferStockMPAchatGroupebyMP.get(j).getPrixUnitaire());
 				etatstockmp.setMontantAchat(listDetailTransferStockMPAchatGroupebyMP.get(j).getQuantite().multiply(listDetailTransferStockMPAchatGroupebyMP.get(j).getPrixUnitaire()));
 				listEtatStockMP.set(i, etatstockmp);
 				
 			}
 			
 			
 		}
 		
 	}
 	*/
 	
 	// calculer Quantite Sortie (charge)
 	
 	
 	
 	
 	for(int i=0;i<listDetailTransferStockMPSortieGroupebyMP.size();i++)
 	{
 		
 		quantiteTotalsortie=BigDecimal.ZERO;
 		montantTotalsortie=BigDecimal.ZERO;
 		quantiteTotalDechet=BigDecimal.ZERO;
 		quantiteTotalOffreProduction=BigDecimal.ZERO;
 		montantDechet=BigDecimal.ZERO;
 		montantOffreProduction=BigDecimal.ZERO;
 		
 		for(int j=0;j<listDetailTransferStockMPSortie.size();j++)
 		{
 			
 			if(listDetailTransferStockMPSortieGroupebyMP.get(i).getMatierePremier().equals(listDetailTransferStockMPSortie.get(j).getMatierePremier()))
 			{
 				if(listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
					{
 					quantiteTotalsortie=quantiteTotalsortie.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPSortie.get(j).getQuantite().setScale(0, RoundingMode.CEILING));
 					montantTotalsortie=montantTotalsortie.add(listDetailTransferStockMPSortie.get(j).getQuantite().setScale(0, RoundingMode.CEILING).multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));
 				
 					if(listDetailTransferStockMPSortie.get(j).getQuantiteDechet()!=null)
						{
	    					quantiteTotalDechet=quantiteTotalDechet.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPSortie.get(j).getQuantiteDechet().setScale(0, RoundingMode.CEILING));
	    					montantDechet=montantDechet.add(listDetailTransferStockMPSortie.get(j).getQuantiteDechet().setScale(0, RoundingMode.CEILING).multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));
					
						}
 					if(listDetailTransferStockMPSortie.get(j).getQuantiteOffre()!=null)
						{
 						quantiteTotalOffreProduction=quantiteTotalOffreProduction.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPSortie.get(j).getQuantiteOffre().setScale(0, RoundingMode.CEILING));
 						montantOffreProduction=montantOffreProduction.add(listDetailTransferStockMPSortie.get(j).getQuantiteOffre().setScale(0, RoundingMode.CEILING).multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));
						}
 					
					
					}else
					{
						quantiteTotalsortie=quantiteTotalsortie.add(listDetailTransferStockMPSortie.get(j).getQuantite());
						montantTotalsortie=montantTotalsortie.add(listDetailTransferStockMPSortie.get(j).getQuantite().multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));
					
						
						if(listDetailTransferStockMPSortie.get(j).getQuantiteDechet()!=null)
						{
							quantiteTotalDechet=quantiteTotalDechet.add(listDetailTransferStockMPSortie.get(j).getQuantiteDechet());
	    					montantDechet=montantDechet.add(listDetailTransferStockMPSortie.get(j).getQuantiteDechet().multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));

						}
						if(listDetailTransferStockMPSortie.get(j).getQuantiteOffre()!=null)
						{
							quantiteTotalOffreProduction=quantiteTotalOffreProduction.add(listDetailTransferStockMPSortie.get(j).getQuantiteOffre());
 						montantOffreProduction=montantOffreProduction.add(listDetailTransferStockMPSortie.get(j).getQuantiteOffre().multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));

						}
						
					}
 				
 				
 			}
 			
 			
 		}
 		
	    	
 		
 		if(!quantiteTotalsortie.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !montantTotalsortie.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
 		{
 			/*
 			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPSortieGroupebyMP.get(i);
 			detailtransferstockmp.setQuantite(quantiteTotalsortie);
 			listDetailTransferStockMPSortieGroupebyMP.set(i, detailtransferstockmp);
 			*/
 			
 			// charger les new valeurs de Stock Sortie (charge) dans la liste Etat Stock 
		    	
		    	for(int k=0;k<listEtatStockMP.size();k++)
		    	{
		    
		    		if(listEtatStockMP.get(k).getMp().equals(listDetailTransferStockMPSortieGroupebyMP.get(i).getMatierePremier()))
		    		{
		    			EtatStockMPAHB etatstockmp=listEtatStockMP.get(k);
		    			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
 					{
			    			etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantTotalsortie)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalsortie.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

		    				etatstockmp.setQuantiteSortie(quantiteTotalsortie.setScale(0, RoundingMode.CEILING));
			    			etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
			    		
			    			
 					}else
 					{
 						
			    			etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantTotalsortie)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalsortie),6,RoundingMode.HALF_UP));

 						etatstockmp.setQuantiteSortie(quantiteTotalsortie);
			    			etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));	
			    		
 					}
		    			
		    			
		    			listEtatStockMP.set(k, etatstockmp);
		    		}
		    		
		    	}
 			
 		}
 		
 		
 		
 		/// inserer la quantite dechet , prix dechet et montant dechet
 		
 		if(!quantiteTotalDechet.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !montantDechet.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
 		{
 			/*
 			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPSortieGroupebyMP.get(i);
 			detailtransferstockmp.setQuantite(quantiteTotalsortie);
 			listDetailTransferStockMPSortieGroupebyMP.set(i, detailtransferstockmp);
 			*/
 			
 			// charger les new valeurs de Stock Sortie (charge) dans la liste Etat Stock 
		    	
		    	for(int k=0;k<listEtatStockMP.size();k++)
		    	{
		    
		    		if(listEtatStockMP.get(k).getMp().equals(listDetailTransferStockMPSortieGroupebyMP.get(i).getMatierePremier()))
		    		{
		    			EtatStockMPAHB etatstockmp=listEtatStockMP.get(k);
		    			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
 					{
		    				etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().add(quantiteTotalDechet.setScale(0, RoundingMode.CEILING)) );
			    			etatstockmp.setPrixDechet(montantDechet.divide(quantiteTotalDechet.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
			    			etatstockmp.setMontantDechet (etatstockmp.getQuantiteDechet().multiply(etatstockmp.getPrixDechet()));
			    			
			    			
 					}else
 					{
 						etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().add(quantiteTotalDechet));
			    			etatstockmp.setPrixDechet(montantDechet.divide(quantiteTotalDechet,6,RoundingMode.HALF_UP));
			    			etatstockmp.setMontantDechet(etatstockmp.getQuantiteDechet().multiply(etatstockmp.getPrixDechet()));					    			
 					}
		    			
		    			
		    			listEtatStockMP.set(k, etatstockmp);
		    		}
		    		
		    		
		    	}
 			
 			
 		}
 		
 		
 		
		/// inserer la quantite Offre , prix Offre et montant Offre (production)
 		
 		if(!quantiteTotalOffreProduction.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !montantOffreProduction.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
 		{
 			/*
 			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPSortieGroupebyMP.get(i);
 			detailtransferstockmp.setQuantite(quantiteTotalsortie);
 			listDetailTransferStockMPSortieGroupebyMP.set(i, detailtransferstockmp);
 			*/
 			
 			// charger les new valeurs de Stock Sortie (charge) dans la liste Etat Stock 
		    	
		    	for(int k=0;k<listEtatStockMP.size();k++)
		    	{
		    
		    		if(listEtatStockMP.get(k).getMp().equals(listDetailTransferStockMPSortieGroupebyMP.get(i).getMatierePremier()))
		    		{
		    			EtatStockMPAHB etatstockmp=listEtatStockMP.get(k);
		    			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
 					{
		    				etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction().add(quantiteTotalOffreProduction.setScale(0, RoundingMode.CEILING)));
			    			etatstockmp.setPrixOffreProduction(montantOffreProduction.divide(quantiteTotalOffreProduction.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
			    			etatstockmp.setMontantOffreProduction (etatstockmp.getQuantiteOffreProduction().multiply(etatstockmp.getPrixOffreProduction()));
			    			
 					}else
 					{
 						etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction().add(quantiteTotalOffreProduction));
			    			etatstockmp.setPrixOffreProduction(montantOffreProduction.divide(quantiteTotalOffreProduction,6,RoundingMode.HALF_UP));
			    			etatstockmp.setMontantOffreProduction(etatstockmp.getQuantiteOffreProduction().multiply(etatstockmp.getPrixOffreProduction()));					    			
 					}
		    			
		    			listEtatStockMP.set(k, etatstockmp);
		    		}
		    		
		    	}
 			
 			
 		}
 		
 		
 		
 	}
 	
 
 
 	
 	// calculer le prix moyen et quantite Avoir
		
		
 	for(int j=0;j<listDetailTransferStockMPAvoirGroupebyMP.size();j++)
 	{
 		montantavoir=new BigDecimal(0);
 		quantiteTotalavoir=new BigDecimal(0);
 		
 	for(int k=0;k<listDetailTransferStockMPAvoir.size();k++)
 	{
 		
 		if(listDetailTransferStockMPAvoirGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPAvoir.get(k).getMatierePremier()))
 		{
 			if(listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
 				montantavoir=montantavoir.add(listDetailTransferStockMPAvoir.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAvoir.get(k).getQuantite()));
	    			quantiteTotalavoir=quantiteTotalavoir.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPAvoir.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
				}else
				{
					montantavoir=montantavoir.add(listDetailTransferStockMPAvoir.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAvoir.get(k).getQuantite()));
	    			quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockMPAvoir.get(k).getQuantite());
				}
 		
 			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
 			
 		}
 		
 		
 	}
 		if(!montantavoir.equals(BigDecimal.ZERO) && !quantiteTotalavoir.equals(BigDecimal.ZERO))
 		{
 			/*	    			
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
 			*/
 			
 			for(int i=0;i<listEtatStockMP.size();i++)
 	    	{
 				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPAvoirGroupebyMP.get(j).getMatierePremier()))
	    			{
 					EtatStockMPAHB etatstockmp=listEtatStockMP.get(i);
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
 					{
	    					etatstockmp.setQuantiteAvoir(quantiteTotalavoir.setScale(0, RoundingMode.CEILING));
		    				etatstockmp.setPrixAvoir(montantavoir.divide(quantiteTotalavoir.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantAvoir(etatstockmp.getQuantiteAvoir().multiply(etatstockmp.getPrixAvoir()));
		    				listEtatStockMP.set(i, etatstockmp);
 					}else
 					{
 						etatstockmp.setQuantiteAvoir(quantiteTotalavoir);
		    				etatstockmp.setPrixAvoir(montantavoir.divide(quantiteTotalavoir,6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantAvoir(etatstockmp.getQuantiteAvoir().multiply(etatstockmp.getPrixAvoir()));
		    				listEtatStockMP.set(i, etatstockmp);
 					}
		    			
	    				
	    			}
 	    	}
 				
 		}
 		
 	}
 	
 	
// calculer le prix moyen et quantite ChargeSupp
		
		
 	for(int j=0;j<listDetailTransferStockMPChargeSuppGroupebyMP.size();j++)
 	{
 		montantchargesupp=new BigDecimal(0);
 		quantiteTotalchargesupp=new BigDecimal(0);
 		
 	for(int k=0;k<listDetailTransferStockMPChargeSupp.size();k++)
 	{
 		
 		if(listDetailTransferStockMPChargeSuppGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPChargeSupp.get(k).getMatierePremier()))
 		{
 			if(listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
 				montantchargesupp=montantchargesupp.add(listDetailTransferStockMPChargeSupp.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPChargeSupp.get(k).getQuantite()));
	    			quantiteTotalchargesupp=quantiteTotalchargesupp.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPChargeSupp.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
				}else
				{
					montantchargesupp=montantchargesupp.add(listDetailTransferStockMPChargeSupp.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPChargeSupp.get(k).getQuantite()));
	    			quantiteTotalchargesupp=quantiteTotalchargesupp.add(listDetailTransferStockMPChargeSupp.get(k).getQuantite());
				}
 			
 			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
 			
 		}
 		
 		
 	}
 		if(!montantchargesupp.equals(BigDecimal.ZERO) && !quantiteTotalchargesupp.equals(BigDecimal.ZERO))
 		{
 			/*	    			
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
 			*/
 			
 			for(int i=0;i<listEtatStockMP.size();i++)
 	    	{
 				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPChargeSuppGroupebyMP.get(j).getMatierePremier()))
	    			{
 					EtatStockMPAHB etatstockmp=listEtatStockMP.get(i);
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
 					{
		    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantchargesupp)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalchargesupp.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

	    					etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalchargesupp.setScale(0, RoundingMode.CEILING)));
		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
		    				listEtatStockMP.set(i, etatstockmp);
	    					
 					}else
 					{
		    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantchargesupp)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalchargesupp),6,RoundingMode.HALF_UP));

 						etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalchargesupp));
		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
		    				listEtatStockMP.set(i, etatstockmp);
 					}
	    				
	    				
	    			}
 	    	}
 				
 		}
 		
 	}
 	
 	
 	
 	
//calculer le prix moyen et quantite Service
		
		
 	for(int j=0;j<listDetailTransferStockMPServiceGroupebyMP.size();j++)
 	{
 		montantService=new BigDecimal(0);
 		quantiteTotalService=new BigDecimal(0);
 		montantDechetService=new BigDecimal(0);
 		quantiteTotalDechetService=new BigDecimal(0);
 		montantOffreService=new BigDecimal(0);
 		quantiteTotalOffreService=new BigDecimal(0);
 		
 	for(int k=0;k<listDetailTransferStockMPService.size();k++)
 	{
 		
 		if(listDetailTransferStockMPServiceGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPService.get(k).getMatierePremier()))
 		{
 			if(listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
 				montantService=montantService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantite()));
	    			quantiteTotalService=quantiteTotalService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
	    			if(listDetailTransferStockMPService.get(k).getQuantiteDechet()!=null)
	    			{
	    				montantDechetService=montantDechetService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteDechet()));
		    			quantiteTotalDechetService=quantiteTotalDechetService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).getQuantiteDechet().setScale(0, RoundingMode.CEILING));
		    			
	    			}
	    			
	    			
	    			if(listDetailTransferStockMPService.get(k).getQuantiteOffre()!=null)
	    			{
	    				montantOffreService=montantOffreService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteOffre()));
		    			quantiteTotalOffreService=quantiteTotalOffreService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).getQuantiteOffre().setScale(0, RoundingMode.CEILING));
	    			}
	    			
 				
				}else
				{
					montantService=montantService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantite()));
	    			quantiteTotalService=quantiteTotalService.add(listDetailTransferStockMPService.get(k).getQuantite());
	    			if(listDetailTransferStockMPService.get(k).getQuantiteDechet()!=null)
	    			{
	    				montantDechetService=montantDechetService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteDechet()));
		    			quantiteTotalDechetService=quantiteTotalDechetService.add(listDetailTransferStockMPService.get(k).getQuantiteDechet());
	    			}
	    			
	    			
	    			if(listDetailTransferStockMPService.get(k).getQuantiteOffre()!=null)
	    			{
	    				montantOffreService=montantOffreService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteOffre()));
		    			quantiteTotalOffreService=quantiteTotalOffreService.add(listDetailTransferStockMPService.get(k).getQuantiteOffre());	
	    				
	    			}
	    			
	    			
	    			
				}
 			
 		
 			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
 			
 		}
 		
 		
 	}
 	
 	
 		if(!montantService.equals(BigDecimal.ZERO) && !quantiteTotalService.equals(BigDecimal.ZERO))
 		{
 			/*	    			
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
 			*/
 			
 			for(int i=0;i<listEtatStockMP.size();i++)
 	    	{
 				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPServiceGroupebyMP.get(j).getMatierePremier()))
	    			{
 					
 					
 					EtatStockMPAHB etatstockmp=listEtatStockMP.get(i);
	    				
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
 					{
	    					etatstockmp.setQuantiteService (etatstockmp.getQuantiteService().setScale(0, RoundingMode.CEILING).add(quantiteTotalService.setScale(0, RoundingMode.CEILING)));
		    				etatstockmp.setPrixService(montantService.divide(quantiteTotalService.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantService(etatstockmp.getQuantiteService().multiply(etatstockmp.getPrixService()));
		    				
		    				
		    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING).add(quantiteTotalDechetService.setScale(0, RoundingMode.CEILING)));
		    				if((etatstockmp.getQuantiteDechetService().add(quantiteTotalDechetService)).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		    				{
			    				etatstockmp.setPrixDechetService(new BigDecimal(0));

		    				}else
		    				{
			    				etatstockmp.setPrixDechetService(montantDechetService.divide(quantiteTotalDechetService.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));

		    				}
		    				etatstockmp.setMontantDechetService (etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
		    				
		    				listEtatStockMP.set(i, etatstockmp);
 					}else
 					{
 						etatstockmp.setQuantiteService(etatstockmp.getQuantiteService().add(quantiteTotalService));
		    				etatstockmp.setPrixService (montantService.divide(quantiteTotalService,6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantService(etatstockmp.getQuantiteService().multiply(etatstockmp.getPrixService()));
		    				
		    				
		    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().add(quantiteTotalDechetService));
		    				if((etatstockmp.getQuantiteDechetService().add(quantiteTotalDechetService)).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		    				{
			    				etatstockmp.setPrixDechetService(new BigDecimal(0));

		    				}else
		    				{
			    				etatstockmp.setPrixDechetService(montantDechetService.divide(quantiteTotalDechetService,6,RoundingMode.HALF_UP));

		    				}	
		    				etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
		    				
		    				listEtatStockMP.set(i, etatstockmp);
 					}
	    				
	    				
	    			}
 	    	}
 				
 		}
 		// Ajouter la Quantite , prix et monatant Offre service
 		if(!quantiteTotalOffreService.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
 		{
 			/*	    			
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
 			*/
 			
 			for(int i=0;i<listEtatStockMP.size();i++)
 	    	{
 				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPServiceGroupebyMP.get(j).getMatierePremier()))
	    			{
 					
 					
 					EtatStockMPAHB etatstockmp=listEtatStockMP.get(i);
	    				
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
 					{
	    					etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING).add(quantiteTotalOffreService.setScale(0, RoundingMode.CEILING)));
		    				etatstockmp.setPrixOffreService(montantOffreService.divide(quantiteTotalOffreService.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantOffreService (etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
		    				
		    				
		    				listEtatStockMP.set(i, etatstockmp);
 					}else
 					{
 						etatstockmp.setQuantiteOffreService (etatstockmp.getQuantiteOffreService().add(quantiteTotalOffreService));
		    				etatstockmp.setPrixOffreService (montantOffreService.divide(quantiteTotalOffreService,6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantOffreService (etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
		    				
		    				listEtatStockMP.set(i, etatstockmp);
 					}
	    				
	    				
	    			}
 	    	}
 				
 		}
 		
 		
 	}
 	
 	
// calculer le prix moyen et quantite Vente
		
		
		
 	for(int j=0;j<listDetailTransferStockMPVenteGroupebyMP.size();j++)
 	{
 		montantVente=new BigDecimal(0);
 		quantiteTotalVente=new BigDecimal(0);
 		
 	for(int k=0;k<listDetailTransferStockMPVente.size();k++)
 	{
 		
 		if(listDetailTransferStockMPVenteGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPVente.get(k).getMatierePremier()))
 		{
 			if(listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
 				montantVente=montantVente.add(listDetailTransferStockMPVente.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPVente.get(k).getQuantite().setScale(0, RoundingMode.CEILING)));
	    			quantiteTotalVente=quantiteTotalVente.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPVente.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
				}else
				{
					montantVente=montantVente.add(listDetailTransferStockMPVente.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPVente.get(k).getQuantite()));
	    			quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockMPVente.get(k).getQuantite());
				}
 	
 			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
 			
 		}
 		
 	}
 		if(!montantVente.equals(BigDecimal.ZERO) && !quantiteTotalVente.equals(BigDecimal.ZERO))
 		{
 			/*	    			
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
 			*/
 			
 			for(int i=0;i<listEtatStockMP.size();i++)
 	    	{
 				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPVenteGroupebyMP.get(j).getMatierePremier()))
	    			{
 					EtatStockMPAHB etatstockmp=listEtatStockMP.get(i);
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
 					{
		    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantVente)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalVente.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

	    					etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalVente.setScale(0, RoundingMode.CEILING)) );
		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
		    				listEtatStockMP.set(i, etatstockmp);
	    					
 					}else
 					{
		    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantVente)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalVente),6,RoundingMode.HALF_UP));

 						etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalVente));
		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
		    				listEtatStockMP.set(i, etatstockmp);	
 					}
	    				
	    				
	    			}
 	    	}
 			
 			
 			
 		}
 		
 	}
 	
 	
 	// calculer calculer le prix moyen et quantite TransferMPPF
 	
 	
	for(int j=0;j<listDetailTransferStockMPTransferMPPFGroupebyMP.size();j++)
 	{
 		montantTransferMPPF=new BigDecimal(0);
 		quantiteTotalTransferMPPF=new BigDecimal(0);
 		quantiteOffreaSupprimer=new BigDecimal(0);
 		quantiteDechetaSupprimer=new BigDecimal(0);
 		
 		
 	for(int k=0;k<listDetailTransferStockMPTransferMPPF.size();k++)
 	{
 		
 		if(listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier()))
 		{
 			if(listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
 				montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPF.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPF.get(k).getQuantite().setScale(0, RoundingMode.CEILING)));
	    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPF.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
	    		
	    			
	    			// calculer la somme de quantite dechet et offre a supprimer
	    			if(listDetailTransferStockMPTransferMPPF.get(k).getStockSource()!=null)
	    			{
	    				if(listDetailTransferStockMPTransferMPPF.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
	    				{
	    					quantiteDechetaSupprimer=quantiteDechetaSupprimer.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPF.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
	    				}else if(listDetailTransferStockMPTransferMPPF.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
	    				{
	    					quantiteOffreaSupprimer=quantiteOffreaSupprimer.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPF.get(k).getQuantite().setScale(0, RoundingMode.CEILING))	;
	    				}
	    				
	    			}
	    			
	    			////////////////////////////////////////////////////////////////////////////////// 
	    			
	    			
				}else
				{
					montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPF.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPF.get(k).getQuantite()));
	    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.add(listDetailTransferStockMPTransferMPPF.get(k).getQuantite());
	    			
	    			// calculer la somme de quantite dechet et offre a supprimer
	    			
	    			if(listDetailTransferStockMPTransferMPPF.get(k).getStockSource()!=null)
	    			{
	    				
	    				if(listDetailTransferStockMPTransferMPPF.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
	    				{
	    					quantiteDechetaSupprimer=quantiteDechetaSupprimer.add(listDetailTransferStockMPTransferMPPF.get(k).getQuantite());
	    				}else if(listDetailTransferStockMPTransferMPPF.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
	    				{
	    					quantiteOffreaSupprimer=quantiteOffreaSupprimer.add(listDetailTransferStockMPTransferMPPF.get(k).getQuantite())	;
	    				}
	    				
	    			}
	    			
                 //////////////////////////////////////////////////////////////////////////////////
	    			
				}
 	
 			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
 			
 		}
 		
 		
 	}
 	
 	
 	
 		if(!montantTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !quantiteTotalTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
 		{
 				    			
 			/*listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).setQuantite(quantiteTotalTransferMPPF);
 			listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).setPrixUnitaire(montantTransferMPPF.divide(quantiteTotalTransferMPPF,6,RoundingMode.HALF_UP));*/
 			
 			
 			for(int i=0;i<listEtatStockMP.size();i++)
 	    	{
 				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).getMatierePremier()))
	    			{
 					EtatStockMPAHB etatstockmp=listEtatStockMP.get(i);
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
 					{
	    					
		    				    etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

 							etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)) );
			    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
			    				// supprimer la quantite dechet et quantite offre
			    				etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().setScale(0, RoundingMode.CEILING).subtract(quantiteDechetaSupprimer.setScale(0, RoundingMode.CEILING)));
			    				
			    			/*	if(etatstockmp.getQuantiteDechet().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
			    				{
			    					etatstockmp.setPrixDechet(BigDecimal.ZERO);
			    				}*/
			    				etatstockmp.setMontantDechet(etatstockmp.getQuantiteDechet().multiply(etatstockmp.getPrixDechet()));
			    				
			    				etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction().setScale(0, RoundingMode.CEILING).subtract(quantiteOffreaSupprimer.setScale(0, RoundingMode.CEILING)));
			    			/*	if(etatstockmp.getQuantiteOffreProduction().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
			    				{
			    					etatstockmp.setPrixOffreProduction(BigDecimal.ZERO);	
			    				}*/
			    				etatstockmp.setMontantOffreProduction(etatstockmp.getQuantiteOffreProduction().multiply(etatstockmp.getPrixOffreProduction()));
 					
			    				
		    				listEtatStockMP.set(i, etatstockmp);
	    					
 					}else
 					{
 					     etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF),6,RoundingMode.HALF_UP));

 						etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF));
			    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
			    				// supprimer la quantite dechet et quantite offre
			    				etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().subtract(quantiteDechetaSupprimer));
			    				/*if(etatstockmp.getQuantiteDechet().setScale(2, RoundingMode.UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.UP)))
			    				{
			    					etatstockmp.setPrixDechet(BigDecimal.ZERO);
			    				}*/
			    				etatstockmp.setMontantDechet(etatstockmp.getQuantiteDechet().multiply(etatstockmp.getPrixDechet()));
			    				
			    				etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction().setScale(6, RoundingMode.HALF_UP).subtract(quantiteOffreaSupprimer.setScale(6, RoundingMode.HALF_UP)));
			    				/*if(etatstockmp.getQuantiteOffreProduction().setScale(2, RoundingMode.UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.UP)))
			    				{
			    					etatstockmp.setPrixOffreProduction(BigDecimal.ZERO);	
			    				}*/
			    				etatstockmp.setMontantOffreProduction(etatstockmp.getQuantiteOffreProduction().multiply(etatstockmp.getPrixOffreProduction()));
 						
		    				
		    				listEtatStockMP.set(i, etatstockmp);	
 					}
	    				
	    				
	    			}
 	    	}
 			
 		}
 		
 	}
 	
 	

	
	
 	
 	// calculer calculer le prix moyen et quantite TransferMPPFSERVICE
 	
 	
 	for(int j=0;j<listDetailTransferStockMPTransferMPPFGroupebyMPService.size();j++)
 	{
 		montantTransferMPPF=new BigDecimal(0);
 		quantiteTotalTransferMPPF=new BigDecimal(0);
 		quantiteOffreaSupprimerService=new BigDecimal(0);
 		quantiteDechetaSupprimerService=new BigDecimal(0);
 		
 		
 	for(int k=0;k<listDetailTransferStockMPTransferMPPFService.size();k++)
 	{
 		
 		if(listDetailTransferStockMPTransferMPPFGroupebyMPService.get(j).getMatierePremier().equals(listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier()))
 		{
 			if(listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
 				montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING)));
	    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
	    			
	    			
	    			// calculer la somme de quantite dechet et offre a supprimer
	    			if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource()!=null)
	    			{
	    				if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
	    				{
	    					quantiteDechetaSupprimerService=quantiteDechetaSupprimerService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
	    				}else if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
	    				{
	    					quantiteOffreaSupprimerService=quantiteOffreaSupprimerService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING))	;
	    				}
	    				
	    			}
	    			////////////////////////////////////////////////////////////////////////////////// 
	    			
	    			
				}else
				{
					montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite()));
	    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite());
	    			
	    			// calculer la somme de quantite dechet et offre a supprimer
	    			
	    			if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource()!=null)
	    			{
	    				
	    				if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
	    				{
	    					quantiteDechetaSupprimerService=quantiteDechetaSupprimerService.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite());
	    				}else if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
	    				{
	    					quantiteOffreaSupprimerService=quantiteOffreaSupprimerService.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite())	;
	    				}
	    				
	    			}
	    			
                 //////////////////////////////////////////////////////////////////////////////////
	    			
				}
 	
 			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
 			
 		}
 		
 		
 	}
 		if(!montantTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !quantiteTotalTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
 		{
 			/*	    			
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
 			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
 			*/
 			
 			for(int i=0;i<listEtatStockMP.size();i++)
 	    	{
 				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPTransferMPPFGroupebyMPService.get(j).getMatierePremier()))
	    			{
 					EtatStockMPAHB etatstockmp=listEtatStockMP.get(i);
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
 					{
	    					
		    				    etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

 							etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)) );
			    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
			    				// supprimer la quantite dechet et quantite offre
			    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING).subtract(quantiteDechetaSupprimerService.setScale(0, RoundingMode.CEILING)));
			    				if(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
			    				{
			    					etatstockmp.setPrixDechetService(BigDecimal.ZERO);	
			    				}
			    				etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
			    				
			    				etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING).subtract(quantiteOffreaSupprimerService.setScale(0, RoundingMode.CEILING)));
			    				if(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
			    				{
			    					etatstockmp.setPrixOffreService(BigDecimal.ZERO);
			    				}
			    				etatstockmp.setMontantOffreService(etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
 						
	    					
	    				
		    				
		    				listEtatStockMP.set(i, etatstockmp);
	    					
 					}else
 					{
 						
 							
 						
		    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF),6,RoundingMode.HALF_UP));

 						   etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF));
 					
			    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
			    				// supprimer la quantite dechet et quantite offre
			    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().subtract(quantiteDechetaSupprimerService));
			    				if(etatstockmp.getQuantiteDechetService().setScale(2, RoundingMode.UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.UP)))
			    				{
			    					etatstockmp.setPrixDechetService(BigDecimal.ZERO);	
			    				}
			    				etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
			    				
			    				etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().setScale(6, RoundingMode.HALF_UP).subtract(quantiteOffreaSupprimerService.setScale(6, RoundingMode.HALF_UP)));
			    				if(etatstockmp.getQuantiteOffreService().setScale(2, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)))
			    				{
			    					etatstockmp.setPrixOffreService(BigDecimal.ZERO);
			    				}
			    				etatstockmp.setMontantOffreService(etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
 					
		    				listEtatStockMP.set(i, etatstockmp);	
 					}
	    				
	    				
	    			}
 	    	}
 			
 		}
 		
 	}
 	
 	
 	// Calculer le Prix Moyen a partir de mouvement de stock 
 	
 	CalculerPrixMoyenFinaleParMouvementStock();
 	
 
 	// calculer le stock finale et le prix moyen finale
 	
 	
  	for(int i=0;i<listEtatStockMP.size();i++)
 	{
 		
  		quantiteTotalFinale=BigDecimal.ZERO;
	   		montantFinale=BigDecimal.ZERO;
 	    BigDecimal prixMoyen=BigDecimal.ZERO;
 	   EtatStockMPAHB etatstockmp=listEtatStockMP.get(i);
 			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
 				etatstockmp.setQuantiteFinale((etatstockmp.getQuantiteInitial().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteAchat().setScale(0, RoundingMode.CEILING))).subtract(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteService().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteAvoir().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteOffreProduction().setScale(0, RoundingMode.CEILING)))));
	    			quantiteTotalFinale=(quantiteTotalFinale.setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteInitial().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteAchat().setScale(0, RoundingMode.CEILING))));
	    			montantFinale=(montantFinale.setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantInitial().setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantAchat().setScale(6,RoundingMode.HALF_UP))));
	    			
	    		for(int j=0;j<listMouvementStockMP.size();j++)
	    		{
	    			if(etatstockmp.getMp().getId()==listMouvementStockMP.get(j).getMatierePremier().getId())
	    			{
	    				
	    				prixMoyen=listMouvementStockMP.get(j).getPrixFinaldb();
	    				
	    			}
	    			
	    		}
	    			
	    			
				
	    			etatstockmp.setPrixFinale(prixMoyen);
		    		etatstockmp.setMontantFinale(((etatstockmp.getQuantiteFinale().setScale(0, RoundingMode.CEILING))).multiply(etatstockmp.getPrixFinale().setScale(6,RoundingMode.HALF_UP)));
	    			
	    			if(etatstockmp.getPrixSortie().setScale(6, RoundingMode.HALF_UP)!=BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))
	    			{
	    				etatstockmp.setMarge((etatstockmp.getPrixSortie().subtract(etatstockmp.getPrixInitial())).divide(etatstockmp.getPrixSortie(), RoundingMode.HALF_UP));
	    			}else
	    			{
	    				etatstockmp.setMarge(BigDecimal.ZERO);
	    			}
				}else
				{
					etatstockmp.setQuantiteFinale((etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat())).subtract(etatstockmp.getQuantiteSortie().add(etatstockmp.getQuantiteService()).add(etatstockmp.getQuantiteAvoir().add(etatstockmp.getQuantiteOffreService()).add(etatstockmp.getQuantiteOffreProduction()))));
	    			quantiteTotalFinale=(quantiteTotalFinale.add(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat())));
	    			montantFinale=(montantFinale.setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantInitial().setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantAchat().setScale(6,RoundingMode.HALF_UP))));
	    			
	    		
	    			for(int j=0;j<listMouvementStockMP.size();j++)
		    		{
		    			if(etatstockmp.getMp().getId()==listMouvementStockMP.get(j).getMatierePremier().getId())
		    			{
		    				
		    				prixMoyen=listMouvementStockMP.get(j).getPrixFinaldb();
		    				
		    			}
		    			
		    		}
	    			   // prixMoyen=stockmp.getPrixUnitaire();
	    				etatstockmp.setPrixFinale(prixMoyen);
		    			etatstockmp.setMontantFinale((etatstockmp.getQuantiteFinale().setScale(6, RoundingMode.HALF_UP)).multiply(etatstockmp.getPrixFinale().setScale(6,RoundingMode.HALF_UP)));
	    			
	    			if(!etatstockmp.getPrixSortie().setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,  RoundingMode.DOWN)))
	    			{
	    				etatstockmp.setMarge((etatstockmp.getPrixSortie().subtract(etatstockmp.getPrixInitial())).divide(etatstockmp.getPrixSortie(), RoundingMode.HALF_UP));
	    			}else
	    			{
	    				etatstockmp.setMarge(BigDecimal.ZERO);
	    			}
				}
 			
 			listEtatStockMP.set(i, etatstockmp);
 			
 	}
 	
 	
 
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
	
	



}


	public static void CalculerPrixMoyenFinaleParMouvementStock() {
		   
		  

		  
		  

	  	   // detailTransferStockMPDAO.ViderSession();
	  		listDetailTransferStockMP.clear();
	  		listDetailTransferStockMPGroupebyMP.clear();
	  		listDetailTransferStockMPBytypetransfer.clear();
	  		
	  		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	  		listMouvementStockMP.clear();
	  		boolean trouve=false;
	  		MatierePremier mp=null;
	  		
	  		
	  		if(dateDebut==null && dateFinTmp==null)
	  		{
	  			JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP !!!");
					return;
	  		}else
	  		{
	  			if(magasin!=null)
		    		{
			    		
			    		
			    		listDetailTransferStockMP=detailTransferStockMPDAO.findAllTransferStockMPOrderByDateTransferEnterDeuxDate(magasin,dateDebut , dateFinTmp);
			    		listDetailTransferStockMPGroupebyMP=detailTransferStockMPDAO.findAllTransferStockMPGroupeByMP(magasin);
			    		//listDetailTransferStockMPBytypetransfer=detailTransferStockMPDAO.findBytypetransfer(ETAT_TRANSFER_STOCK_INITIAL,magasin);
			    		//String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.ETAT_TRANSFER_STOCK_CHARGE,ETAT_TRANSFER_STOCK_CHARGE_SUPP,ETAT_TRANSFER_STOCK_VENTE,ETAT_TRANSFER_STOCK_AVOIR,ETAT_TRANSFER_STOCK_SERVICE,ETAT_TRANSFER_STOCK_SORTIE_PF};
			    		BigDecimal achat=BigDecimal.ZERO;
			    		BigDecimal prixAchat=BigDecimal.ZERO;
			    		BigDecimal initial=BigDecimal.ZERO;
			    		BigDecimal prixInitial=BigDecimal.ZERO;
			    		BigDecimal production=BigDecimal.ZERO;
			    		BigDecimal avoir=BigDecimal.ZERO;
			    		
			    		BigDecimal stockfinal=BigDecimal.ZERO;
			    		BigDecimal vente=BigDecimal.ZERO;
			    		BigDecimal service=BigDecimal.ZERO;
			    		BigDecimal dechet=BigDecimal.ZERO;
			    		BigDecimal dechetService=BigDecimal.ZERO;
			    		BigDecimal offreService=BigDecimal.ZERO;
			    		BigDecimal offreProduction=BigDecimal.ZERO;
			    		BigDecimal transfert=BigDecimal.ZERO;
			    		BigDecimal prixmoyenne=BigDecimal.ZERO;
			    		BigDecimal quantitefinale=BigDecimal.ZERO;
			    		for(int i=0;i<listDetailTransferStockMPGroupebyMP.size();i++)
			    		{
			    			achat=new BigDecimal(0);
			    			prixAchat=new BigDecimal(0);
			    			initial=new BigDecimal(0);
			    			production=new BigDecimal(0);
			    			dechet=new BigDecimal(0);
			    			dechetService=new BigDecimal(0);
			    			service=new BigDecimal(0);
			    			vente=new BigDecimal(0);
			    			avoir=BigDecimal.ZERO;
			    			stockfinal=new BigDecimal(0);
			    			offreService=new BigDecimal(0);
			    			offreProduction=new BigDecimal(0);
			    			transfert=new BigDecimal(0);
			    			prixmoyenne=BigDecimal.ZERO;
			    			quantitefinale=BigDecimal.ZERO;
			    			for(int j=0;j<listDetailTransferStockMP.size();j++)
			    			{
			    				
			    				DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(j);
			    				
			    				if(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getId()==detailTransferStockMP.getMatierePremier().getId())
			    				{
			    					
			    				if(	listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getId()	==123)
			    				{
		    						System.out.println("Prix Moyenne : "+listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" - "+prixmoyenne);

			    					
			    				}
			    					
			    					
			    				if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_INITIAL))
			    				{
			    					
			    					quantitefinale=quantitefinale.add(detailTransferStockMP.getQuantite());
			    					prixmoyenne=detailTransferStockMP.getPrixUnitaire();
			    					
			    					
			    					
			    				}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_VENTE))
			    				{
			    					
			    					quantitefinale=quantitefinale.subtract(detailTransferStockMP.getQuantite());	
			    					
			    					
			    				}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_CHARGE) && !detailTransferStockMP.getTransferStockMP().getCodeTransfer().equals(NumOF))
			    				{
			    					
			    					quantitefinale=quantitefinale.subtract(detailTransferStockMP.getQuantite());	
			    					if(detailTransferStockMP.getQuantiteOffre()!=null)
			    					{
			    						quantitefinale=quantitefinale.subtract(detailTransferStockMP.getQuantiteOffre());
			    					}
			    					if(detailTransferStockMP.getQuantiteDechet()!=null)
			    					{
			    						quantitefinale=quantitefinale.subtract(detailTransferStockMP.getQuantiteDechet());
			    					}
			    					
			    					
			    				}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP))
			    				{
			    					
			    					quantitefinale=quantitefinale.subtract(detailTransferStockMP.getQuantite());		
			    					
			    				}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SERVICE) && !detailTransferStockMP.getTransferStockMP().getCodeTransfer().equals(NumOF))
			    				{
			    					
			    					quantitefinale=quantitefinale.subtract(detailTransferStockMP.getQuantite());	
			    					if(!detailTransferStockMP.getQuantiteOffre().equals(null))
			    					{
			    						quantitefinale=quantitefinale.subtract(detailTransferStockMP.getQuantiteOffre());
			    					}
			    					if(detailTransferStockMP.getQuantiteDechet()!=null)
			    					{
			    						quantitefinale=quantitefinale.subtract(detailTransferStockMP.getQuantiteDechet());
			    					}
			    					
			    					
			    				
			    				}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_ACHAT))
			    				{
			    					
			    					prixmoyenne=((quantitefinale.multiply(prixmoyenne)).add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getPrixUnitaire()))).divide(quantitefinale.add(detailTransferStockMP.getQuantite()),6 ,RoundingMode.HALF_UP );
			    					
			    					quantitefinale=quantitefinale.add(detailTransferStockMP.getQuantite());		
			    					
			    				} else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_AVOIR))
			    				{
			    					
			    					quantitefinale=quantitefinale.subtract(detailTransferStockMP.getQuantite());		
			    					
			    				}
			    					
			    					if(detailTransferStockMP.getTransferStockMP().getCodeTransfer().equals(NumOF))
			    					{
			    						
			    						System.out.println("Prix Moyenne : "+listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" - "+prixmoyenne);
			    					
			    						break;
			    					}
			    				
			    				}
			    					
                           }
			   
			    	
					
	    			//stockfinal=(initial.add(achat)).subtract(vente.add(production).add(avoir).add(service).add(dechet).add(dechetService));
	    			DetailMouvementStock mouvementstockMP=new DetailMouvementStock();
	    			mouvementstockMP.setDateCreation(listDetailTransferStockMPGroupebyMP.get(i).getTransferStockMP().getDateTransfer());
	    			mouvementstockMP.setProduction(production);
	    			mouvementstockMP.setDechet(dechet);
	    			mouvementstockMP.setOffreProduction(offreProduction);
	    			mouvementstockMP.setVente(vente);
	    			mouvementstockMP.setInitial(initial);
	    			mouvementstockMP.setPrixInitial(prixInitial);
	    			mouvementstockMP.setAchat(achat);
	    			mouvementstockMP.setPrixAchat(prixAchat);
	    			mouvementstockMP.setAvoir(avoir);
	    			mouvementstockMP.setService(service);
	    			mouvementstockMP.setDechetService(dechetService);
	    			mouvementstockMP.setOffreService(offreService);
	    			mouvementstockMP.setTransfert(transfert);
	    			mouvementstockMP.setMatierePremier(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier());
		
	    			mouvementstockMP.setPrixFinaldb(prixmoyenne);
	    			mouvementstockMP.setStockFinaldb((mouvementstockMP.getInitial().add(mouvementstockMP.getAchat())).subtract(mouvementstockMP.getVente().add(mouvementstockMP.getProduction().add(mouvementstockMP.getAvoir().add(mouvementstockMP.getService()).add(mouvementstockMP.getTransfert())))));
	    			listMouvementStockMP.add(mouvementstockMP);
			    	
			    	
			  
			    	
			    			
			    		}
			    		
			
			    		
			    			
			  
			               
			               
			    
			    	
			    			
		    		}else
		    		{
		    			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
		    			return;
		    		}
	  		}
	  
	  	
	  		
	  		   
			   
			   
			   
			   
			   
		
}






}
