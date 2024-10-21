package util;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.HabilitationDAOImpl;
import dao.daoImplManager.MenuDAOImpl;
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
import dao.daoManager.FacturePFDAO;
import dao.daoManager.HabilitationDAO;
import dao.daoManager.MenuDAO;
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
import dao.entity.Employe;
import dao.entity.FacturePF;
import dao.entity.Fournisseur;
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

public class Utils implements Constantes{
	
	private ResultSet rsetSqlServer;
	private Statement stxSqlServer;
	private String query;
	private Statement stx;   
	private static StockMPDAO stockMPDAO=new StockMPDAOImpl();
	private static StockPFDAO stockPFDAO=new StockPFDAOImpl();
	private static SequenceurDAO sequenceurDAO=new SequenceurDAOImpl();
	private static DepotDAO depotDAO=new DepotDAOImpl();
	private static CompteurNumDossierDAO compteurNumDossierDAO=new CompteurNumDossierDAOImpl();
	private static MenuDAO menuDAO =new MenuDAOImpl();
	private static HabilitationDAO habilitationDAO=new HabilitationDAOImpl();
	private static UtilisateurDAO utilisateurDAO=new UtilisateurDAOImpl();
	private static ParametreDAO parametreDAO=new ParametreDAOImpl();
	private static CompteurAbsenceEmployeDAO compteurAbsenceEmployeDAO=new CompteurAbsenceEmployeDAOImpl();
	private static CompteurTransferMPDAO compteurTransferMPDAO=new CompteurTransferMPDAOImpl();
	private static PrixClientMPDAO prixClientMPDAO=new PrixClientMPDAOImpl();
	private static final String LETTRE_PASSWORD = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
	private static final Random RANDOM = new SecureRandom();
	private static  Utilisateur utilisateur;
	private static FacturePFDAO facturepfdao;
	
	
	//Méthode permet de miger la table article d'une base de données SQLSERVER vers une base de données MySql
	   	public void migerTableArticle() {
	   		System.out.println("debut");
	   	query="select * from ARTICLES where CONDITIONNEMENT in ('1','2','4','5','10') and CENTRECOUT5 in ('100 G','200 G','250 G','500 G','1 KG')";
	  System.out.println(query);
	try {
		rsetSqlServer=stxSqlServer.executeQuery(query);
		
		while(rsetSqlServer.next()){
			String query2 ="INSERT INTO ARTICLES VALUES (null,'"+rsetSqlServer.getString("CODE_ARTICLE")+"','"+rsetSqlServer.getString("LIBELLE")+"','"+rsetSqlServer.getString("CENTRECOUT1")+"','"+rsetSqlServer.getString("CENTRECOUT2")+
					"','"+rsetSqlServer.getString("CENTRECOUT3")+"','"+rsetSqlServer.getString("CENTRECOUT4")+"','"+rsetSqlServer.getString("CENTRECOUT5")+"','"+rsetSqlServer.getString("CODE_FONCTION")+
					"','"+rsetSqlServer.getFloat("PRIX_VENTE")+"','"+rsetSqlServer.getFloat("PRIX_RETOUR")+"','"+rsetSqlServer.getFloat("TVA")+"','"+rsetSqlServer.getString("CONDITIONNEMENT")+"')";
			stx.executeUpdate(query2);
			
			 
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	System.out.println("Fin");
	
	   		
	   		
	   	}
	   	
public static String creerCodeOF(String codeArticle,String codeDepot){
	String code="";
	Depot depot=depotDAO.findByCode(codeDepot);
	Sequenceur sequenceur =sequenceurDAO.findByLibelle(codeDepot);
	if(sequenceur!=null)
	{
		code=codeDepot.substring(0, 1)+"_"+codeArticle+"_"+sequenceur.getValeur();
	}else
	{
		Sequenceur sequenceurTmp=new Sequenceur();
		code=codeDepot.substring(0, 1)+"_"+codeArticle+"_"+1;
		sequenceurTmp.setCode(code);
		sequenceurTmp.setLibelle(depot.getLibelle());
		sequenceurTmp.setValeur(1);
		sequenceurDAO.add(sequenceurTmp);
	}
	
	
	return code;
}

public static boolean genererStockByMagasinMP(List<MatierePremier> listMP,List<Magasin> listMagasin){ 
	StockMP stockMP = new StockMP();
	if(listMP!=null && listMagasin!=null){
	for(int i=0;i<listMP.size();i++)
		for(int j=0;j<listMagasin.size();j++){
			 
			MatierePremier matierePremiere =listMP.get(i);
			Magasin magasin =listMagasin.get(j);
			
			
			
			if(!matierePremiere.getCode().equals(MATIERE_PREMIERE_SERVICE_PRODUCTION))
				stockMP =stockMPDAO.findStockByMagasinMP(matierePremiere.getId(), magasin.getId());
		
			if(stockMP==null){
				stockMP = new StockMP();
				stockMP.setMagasin(magasin);
				stockMP.setMatierePremier(matierePremiere);
				stockMP.setQuantiteCommande(BigDecimal.ZERO);
				stockMP.setStock(BigDecimal.ZERO);
				stockMP.setStockMin(BigDecimal.ZERO);
				stockMP.setPrixUnitaire(BigDecimal.ZERO);
				stockMPDAO.add(stockMP);
			
			}
		}
	}
	return true ;
	
}

public static boolean genererStockProduitFiniByMagasin(List<Articles> listArticles,List<Magasin> listMagasin){ 
	StockPF stockPF = new StockPF();
	if(listArticles!=null && listMagasin!=null){
	for(int i=0;i<listArticles.size();i++)
		for(int j=0;j<listMagasin.size();j++){
			 
			Articles articles =listArticles.get(i);
			Magasin magasin =listMagasin.get(j);

			stockPF =stockPFDAO.findStockByMagasinPF(articles.getId(), magasin.getId());
			
			if(stockPF==null){
				stockPF = new StockPF();
				stockPF.setMagasin(magasin);
				stockPF.setArticles(articles);
				stockPF.setStock(BigDecimal.ZERO);
				stockPF.setStockMin(BigDecimal.ZERO);
				stockPF.setPrixUnitaire(BigDecimal.ZERO);
				stockPFDAO.add(stockPF);
			}
		}
	}
	return true ;
	
}
	

/*Générer mot de passe */

public static String genererPassword(){
	
	String pw ="";
	
	for(int i=0;i < Constantes.PASSWORD_LENGTH; i++){
		int index = (int) (RANDOM.nextDouble() * LETTRE_PASSWORD.length());
		pw += LETTRE_PASSWORD.substring(index, index + 1);
		
	}
	
	return pw;
}

public static String genererCode (String codeClasse){
	String code;
	Sequenceur  sequenceur=sequenceurDAO.findByLibelle(codeClasse);
	 code=sequenceur.getCode()+sequenceur.getValeur();
	
	return code;
	
}


public static String genererCodeFactureVente (String date){	
	
	 ///  Numerotation 2019
	 // Note changer string date to Date datefacture
		
		  String code=""; 
		  utilisateur=AuthentificationView.utilisateur;
		  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN)) {
			  
		  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_FACTURE_VENTE_ETP); 
		  if(sequenceur!=null) { 
			  int  valeur=sequenceur.getValeur()+1;
			  if(valeur<100 && valeur>=10) {
		  code="0"+valeur+"/"+date; 
		  }else if(valeur<10) 
		  { code="00"+valeur+"/"+date;
		  }else if(valeur>=100) 
		  { code=valeur+"/"+date; } 
			  }else
			  {
		  
		  code="00"+1+"/"+date;
		  
		  } }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
		  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_AHB); 
		  if(sequenceur!=null) { int valeur=sequenceur.getValeur()+1; 
		  if(valeur<100 && valeur>=10)
		  {
		  code="0"+valeur+"/"+date; }else if(valeur<10) 
		  { code="00"+valeur+"/"+date;
		  }else if(valeur>=100)
		  { code=valeur+"/"+date; } }else {
		  
		  code="00"+1+"/"+date;
		  
		  } }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_ELALAOUI)) {
			  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_ALAOUI); 
			  if(sequenceur!=null) { int valeur=sequenceur.getValeur()+1; 
			  if(valeur<100 && valeur>=10)
			  {
			  code="0"+valeur+"/"+date; }else if(valeur<10) 
			  { code="00"+valeur+"/"+date;
			  }else if(valeur>=100)
			  { code=valeur+"/"+date; } }else {
			  
			  code="00"+1+"/"+date;
			  
			  } }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_FOURSSAN_ALJANOUB)) {
				  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_FOURSSAN); 
				  if(sequenceur!=null) { int valeur=sequenceur.getValeur()+1; 
				  if(valeur<100 && valeur>=10)
				  {
				  code="0"+valeur+"/"+date; }else if(valeur<10) 
				  { code="00"+valeur+"/"+date;
				  }else if(valeur>=100)
				  { code=valeur+"/"+date; } }else {
				  
				  code="00"+1+"/"+date;
				  
				  } }  
		  
		  
		  return code;
		 
	
	
	
	
	///*****************************************************  New Numerotation 2019  ****************************************************************************
		/*
		
		  /// New Numerotation 2019 // Note changer string date to Date datefacture
	
		  SimpleDateFormat dcncode = new SimpleDateFormat("yyMMdd"); 
		  SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
		  
		  String year=dcn.format(datefacture); 
		  String date=dcncode.format(datefacture);
		  
		  Calendar c = Calendar.getInstance();
		  
		  c.setTime(datefacture);
		  int nbrjrs=c.get(Calendar.DAY_OF_YEAR);
		  int nbrsunday= nbrjrs / 7;
		  int nbrjrssansweekend=nbrjrs-nbrsunday; 
		  Parametre
		  nombre_facture_par_jour=parametreDAO.findByCode(Constantes. NOMBRE_FACTURE_PAR_JOUR);
		  
		  
		  String code=""; 
		  utilisateur=AuthentificationView.utilisateur;
		  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN)) {
		  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
		  Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) {
		  
		  int valeur=sequenceur.getValeur()+1;
		  
		  if(valeur> ((nbrjrssansweekend-1)
		  *nombre_facture_par_jour.getValeur().intValue())+nombre_facture_par_jour.
		  getValeur().intValue() ) {
		  
		  
		  }else { if(valeur<100 && valeur>=10) {
		  
		  code="0"+valeur+"/"+year;
		  
		  }else if(valeur<10) { code="00"+valeur+"/"+year; }else if(valeur>=100) {
		  code=valeur+"/"+year; } }
		  
		  
		  }else {
		  
		  int valeur=((nbrjrssansweekend-1) *
		  nombre_facture_par_jour.getValeur().intValue())+1;
		  
		  if(valeur> ((nbrjrssansweekend-1) *
		  nombre_facture_par_jour.getValeur().intValue())+nombre_facture_par_jour.
		  getValeur().intValue() ) {
		  
		  
		  }else { if(valeur<100 && valeur>=10) { code="0"+valeur+"/"+year; }else
		  if(valeur<10) { code="00"+valeur+"/"+year; } else if(valeur>=100) {
		  code=valeur+"/"+year; } }
		  
		  
		  
		  } }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
		  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.
		  CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) {
		  
		  int valeur=sequenceur.getValeur()+1;
		  
		  if(valeur> ((nbrjrssansweekend-1)
		  *nombre_facture_par_jour.getValeur().intValue())+nombre_facture_par_jour.
		  getValeur().intValue() ) {
		  
		  
		  
		  }else { if(valeur<100 && valeur>=10) { code="0"+valeur+"/"+year; }else
		  if(valeur<10) { code="00"+valeur+"/"+year; } else if(valeur>=100) {
		  code=valeur+"/"+year; } }
		  
		  
		  }else {
		  
		  int valeur=((nbrjrssansweekend-1) *
		  nombre_facture_par_jour.getValeur().intValue())+1;
		  
		  if(valeur> ((nbrjrssansweekend-1) *
		  nombre_facture_par_jour.getValeur().intValue())+nombre_facture_par_jour.
		  getValeur().intValue() ) {
		  
		  
		  }else { if(valeur<100 && valeur>=10) { code="0"+valeur+"/"+year; }else
		  if(valeur<10) { code="00"+valeur+"/"+year; }else if(valeur>=100) {
		  code=valeur+"/"+year; } }
		  
		  
		  
		  
		  } }
		  
		  
		  return code;
		  
		  
		  */
		 
	///*********************************************************************************************************************************	 	
	
		  
		  
		  
		  
	///Numerotation 2018
		/*
		 * String code=""; utilisateur=AuthentificationView.utilisateur;
		 * if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN)) {
		 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
		 * Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
		 * valeur=sequenceur.getValeur()+1; if(valeur<100 && valeur>=10) {
		 * code=sequenceur.getCode()+"0"+valeur; }else if(valeur<10) {
		 * code=sequenceur.getCode()+"00"+valeur; }else if(valeur>=100) {
		 * code=sequenceur.getCode()+valeur; } }else {
		 * 
		 * code=date+"00"+1;
		 * 
		 * } }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
		 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
		 * Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
		 * valeur=sequenceur.getValeur()+1; if(valeur<100 && valeur>=10) {
		 * code=sequenceur.getCode()+"0"+valeur; }else if(valeur<10) {
		 * code=sequenceur.getCode()+"00"+valeur; }else if(valeur>=100) {
		 * code=sequenceur.getCode()+valeur; } }else {
		 * 
		 * code=date+"00"+1;
		 * 
		 * } }
		 * 
		 * 
		 * return code;
		 */
	
		/*
		 * String code=""; utilisateur=AuthentificationView.utilisateur;
		 * if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN)) {
		 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
		 * Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
		 * valeur=sequenceur.getValeur()+1; if(valeur<100 && valeur>=10) {
		 * code="0"+valeur+"/"+date; }else if(valeur<10) { code="00"+valeur+"/"+date;
		 * }else if(valeur>=100) { code=valeur+"/"+date; } }else {
		 * 
		 * code="00"+1+"/"+date;
		 * 
		 * } }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
		 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
		 * Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
		 * valeur=sequenceur.getValeur()+1; if(valeur<100 && valeur>=10) {
		 * code="0"+valeur+"/"+date; }else if(valeur<10) { code="00"+valeur+"/"+date;
		 * }else if(valeur>=100) { code=valeur+"/"+date; } }else {
		 * 
		 * code="00"+1+"/"+date;
		 * 
		 * } }
		 * 
		 * 
		 * return code;
		 */
	
	
}





public static String genererCodeFactureAvoir (String date){	
	
	 ///  Numerotation 2019
	 // Note changer string date to Date datefacture
		
		  String code=""; 
		  utilisateur=AuthentificationView.utilisateur;
		  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN)) {
			  
		  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.CODE_FACTURE_AVOIR_ETP); 
		  if(sequenceur!=null) { 
			  int  valeur=sequenceur.getValeur()+1;
			  if(valeur<100 && valeur>=10) {
		  code="0"+valeur+"/"+date; 
		  }else if(valeur<10) 
		  { code="00"+valeur+"/"+date;
		  }else if(valeur>=100) 
		  { code=valeur+"/"+date; } 
			  }else
			  {
		  
		  code="00"+1+"/"+date;
		  
		  } }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
		  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_AVOIR_AHB); 
		  if(sequenceur!=null) { int valeur=sequenceur.getValeur()+1; 
		  if(valeur<100 && valeur>=10)
		  {
		  code="0"+valeur+"/"+date; }else if(valeur<10) 
		  { code="00"+valeur+"/"+date;
		  }else if(valeur>=100)
		  { code=valeur+"/"+date; } }else {
		  
		  code="00"+1+"/"+date;
		  
		  } }
		  
		  
		  return code;
		 
	
	
	
	
	///*****************************************************  New Numerotation 2019  ****************************************************************************
		/*
		
		  /// New Numerotation 2019 // Note changer string date to Date datefacture
	
		  SimpleDateFormat dcncode = new SimpleDateFormat("yyMMdd"); 
		  SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
		  
		  String year=dcn.format(datefacture); 
		  String date=dcncode.format(datefacture);
		  
		  Calendar c = Calendar.getInstance();
		  
		  c.setTime(datefacture);
		  int nbrjrs=c.get(Calendar.DAY_OF_YEAR);
		  int nbrsunday= nbrjrs / 7;
		  int nbrjrssansweekend=nbrjrs-nbrsunday; 
		  Parametre
		  nombre_facture_par_jour=parametreDAO.findByCode(Constantes. NOMBRE_FACTURE_PAR_JOUR);
		  
		  
		  String code=""; 
		  utilisateur=AuthentificationView.utilisateur;
		  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN)) {
		  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
		  Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) {
		  
		  int valeur=sequenceur.getValeur()+1;
		  
		  if(valeur> ((nbrjrssansweekend-1)
		  *nombre_facture_par_jour.getValeur().intValue())+nombre_facture_par_jour.
		  getValeur().intValue() ) {
		  
		  
		  }else { if(valeur<100 && valeur>=10) {
		  
		  code="0"+valeur+"/"+year;
		  
		  }else if(valeur<10) { code="00"+valeur+"/"+year; }else if(valeur>=100) {
		  code=valeur+"/"+year; } }
		  
		  
		  }else {
		  
		  int valeur=((nbrjrssansweekend-1) *
		  nombre_facture_par_jour.getValeur().intValue())+1;
		  
		  if(valeur> ((nbrjrssansweekend-1) *
		  nombre_facture_par_jour.getValeur().intValue())+nombre_facture_par_jour.
		  getValeur().intValue() ) {
		  
		  
		  }else { if(valeur<100 && valeur>=10) { code="0"+valeur+"/"+year; }else
		  if(valeur<10) { code="00"+valeur+"/"+year; } else if(valeur>=100) {
		  code=valeur+"/"+year; } }
		  
		  
		  
		  } }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
		  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.
		  CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) {
		  
		  int valeur=sequenceur.getValeur()+1;
		  
		  if(valeur> ((nbrjrssansweekend-1)
		  *nombre_facture_par_jour.getValeur().intValue())+nombre_facture_par_jour.
		  getValeur().intValue() ) {
		  
		  
		  
		  }else { if(valeur<100 && valeur>=10) { code="0"+valeur+"/"+year; }else
		  if(valeur<10) { code="00"+valeur+"/"+year; } else if(valeur>=100) {
		  code=valeur+"/"+year; } }
		  
		  
		  }else {
		  
		  int valeur=((nbrjrssansweekend-1) *
		  nombre_facture_par_jour.getValeur().intValue())+1;
		  
		  if(valeur> ((nbrjrssansweekend-1) *
		  nombre_facture_par_jour.getValeur().intValue())+nombre_facture_par_jour.
		  getValeur().intValue() ) {
		  
		  
		  }else { if(valeur<100 && valeur>=10) { code="0"+valeur+"/"+year; }else
		  if(valeur<10) { code="00"+valeur+"/"+year; }else if(valeur>=100) {
		  code=valeur+"/"+year; } }
		  
		  
		  
		  
		  } }
		  
		  
		  return code;
		  
		  
		  */
		 
	///*********************************************************************************************************************************	 	
	
		  
		  
		  
		  
	///Numerotation 2018
		/*
		 * String code=""; utilisateur=AuthentificationView.utilisateur;
		 * if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN)) {
		 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
		 * Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
		 * valeur=sequenceur.getValeur()+1; if(valeur<100 && valeur>=10) {
		 * code=sequenceur.getCode()+"0"+valeur; }else if(valeur<10) {
		 * code=sequenceur.getCode()+"00"+valeur; }else if(valeur>=100) {
		 * code=sequenceur.getCode()+valeur; } }else {
		 * 
		 * code=date+"00"+1;
		 * 
		 * } }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
		 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
		 * Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
		 * valeur=sequenceur.getValeur()+1; if(valeur<100 && valeur>=10) {
		 * code=sequenceur.getCode()+"0"+valeur; }else if(valeur<10) {
		 * code=sequenceur.getCode()+"00"+valeur; }else if(valeur>=100) {
		 * code=sequenceur.getCode()+valeur; } }else {
		 * 
		 * code=date+"00"+1;
		 * 
		 * } }
		 * 
		 * 
		 * return code;
		 */
	
		/*
		 * String code=""; utilisateur=AuthentificationView.utilisateur;
		 * if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN)) {
		 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
		 * Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
		 * valeur=sequenceur.getValeur()+1; if(valeur<100 && valeur>=10) {
		 * code="0"+valeur+"/"+date; }else if(valeur<10) { code="00"+valeur+"/"+date;
		 * }else if(valeur>=100) { code=valeur+"/"+date; } }else {
		 * 
		 * code="00"+1+"/"+date;
		 * 
		 * } }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
		 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
		 * Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
		 * valeur=sequenceur.getValeur()+1; if(valeur<100 && valeur>=10) {
		 * code="0"+valeur+"/"+date; }else if(valeur<10) { code="00"+valeur+"/"+date;
		 * }else if(valeur>=100) { code=valeur+"/"+date; } }else {
		 * 
		 * code="00"+1+"/"+date;
		 * 
		 * } }
		 * 
		 * 
		 * return code;
		 */
	
	
}



public static String genererCodeFactureService (String date){
	String code="";
	utilisateur=AuthentificationView.utilisateur;
	if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
	{
		Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_SERVICE_ETP);
		if(sequenceur!=null)
		{
			int valeur=sequenceur.getValeur()+1;
			if(valeur<100 && valeur>=10)
			{
				code=sequenceur.getCode()+"0"+valeur;
			}else if(valeur<10)
			{
				code=sequenceur.getCode()+"00"+valeur;
			}else if(valeur>=100)
			{
				code=sequenceur.getCode()+valeur;	
			}
		}else
		{
			
			code=date+"00"+1;
			
		}
	}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
	{
		Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_SERVICE_AHB);
		if(sequenceur!=null)
		{
			int valeur=sequenceur.getValeur()+1;
			if(valeur<100 && valeur>=10)
			{
				code=sequenceur.getCode()+"0"+valeur;
			}else if(valeur<10)
			{
				code=sequenceur.getCode()+"00"+valeur;
			}else if(valeur>=100)
			{
				code=sequenceur.getCode()+valeur;	
			}
		}else
		{
			
			code=date+"00"+1;
			
		}
	}

	
	
	
	
	
	
	return code;
	
}


public static String genererCodeFournisseur (){
	String code="";
	Sequenceur  sequenceur=sequenceurDAO.findByCode(Constantes.CODE_FOURNISSEUR);
	if(sequenceur!=null)
	{
		int valeur=sequenceur.getValeur()+1;
		
			code=sequenceur.getCode()+valeur;	
		}
	else
	{
		
		code=Constantes.CODE_FOURNISSEUR+1;
		
	}
	
	
	
	
	
	
	return code;
	
}




public static String genererCodeCompte (String codeCompte){
	utilisateur=AuthentificationView.utilisateur;
	String code="";
	Sequenceur  sequenceur=sequenceurDAO.findByLibelle(codeCompte);
	if(sequenceur!=null)
	{
		int valeur=sequenceur.getValeur()+1;
		sequenceur.setValeur(valeur);
		sequenceurDAO.edit(sequenceur);
		
		 code=sequenceur.getCode()+valeur+"_"+utilisateur.getId();
	}else
	{
		Sequenceur  sequenceurTMP =new Sequenceur();
		sequenceurTMP.setCode(Constantes.CODE_COMPTE);
		sequenceurTMP.setLibelle(Constantes.CODE_COMPTE);
		sequenceurTMP.setValeur(1);
		sequenceurDAO.add(sequenceurTMP);
		 code=sequenceur.getCode()+1+"_"+utilisateur.getId();
	}
	
	
	return code;
	
}




public static String genererCode (String code,String libelleClasse){
	String codeGenerer;
		Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(code, libelleClasse);
int valeur=sequenceur.getValeur()+1;
	sequenceur.setValeur(valeur);
	sequenceurDAO.edit(sequenceur);
	codeGenerer=sequenceur.getCode()+valeur;
	
	return codeGenerer;
	
}

public static void incrementerValeurSequenceur (String codeClasse){
	Sequenceur  sequenceur=sequenceurDAO.findByLibelle(codeClasse);
	int valeur=sequenceur.getValeur()+1;
	sequenceur.setValeur(valeur);
	sequenceurDAO.edit(sequenceur);
}	


public static String incrementerchargeVF (String codeClasse){
	String codeGenerer;
	Sequenceur  sequenceur=sequenceurDAO.findByCode(codeClasse);
	int valeur=sequenceur.getValeur()+1;
	sequenceur.setValeur(valeur);
	sequenceurDAO.edit(sequenceur);
	 codeGenerer=sequenceur.getCode()+valeur;
	return codeGenerer;
}


public static String  genererCodeMagasin(String codeClasse,String codeDepot){
	
	String code =codeDepot.substring(0, 3);
	return code+"_"+genererCode (codeClasse);
		
	
}

public static String  genererCodeMachine(String codeClasse,String codeDepot){
	
	String code =codeDepot.substring(0, 3);
	return code+"_"+genererCode (codeClasse);
		
	
}

public static String  genererCodeReferentiel(int valeur,String codeDepot){
	utilisateur=AuthentificationView.utilisateur;
	String code =codeDepot.substring(0, 3);
	return code+"_C"+valeur;
	
}



public static String  genererNumDossierEmploye(){
	 int compteur=0;
	 String numDossier;
	 Calendar cal = Calendar.getInstance();
     cal.setTime(new Date ());
     String  annee = cal.get(Calendar.YEAR)+"";
	 CompteurNumDossier compteurNumDossier=compteurNumDossierDAO.findNumByAnnee(annee);
	 
	 if(compteurNumDossier !=null){
		 compteur=compteurNumDossier.getCompteur()+1;
		 compteurNumDossier.setCompteur(compteur);	
		 compteurNumDossierDAO.edit(compteurNumDossier);
		 
	 }else{
		 compteur=1;
		 compteurNumDossier= new CompteurNumDossier();
		
		 compteurNumDossier.setCompteur(compteur);
		 compteurNumDossier.setAnnee(annee);;
		 compteurNumDossierDAO.add(compteurNumDossier);
		 
	 }
	 if(compteur<10)
	  numDossier=annee+"/00"+compteur;
	 else if (compteur<100)
		 numDossier=annee+"/0"+compteur;
	 else 
		 numDossier=annee+"/"+compteur;
	 
	 return numDossier;
}

public static String  retournerNumDossierEmploye(){
	int compteur=0;
	 String numDossier;
	 Calendar cal = Calendar.getInstance();
    cal.setTime(new Date ());
    String  annee = cal.get(Calendar.YEAR)+"";
	 CompteurNumDossier compteurNumDossier=compteurNumDossierDAO.findNumByAnnee(annee);
	 if(compteurNumDossier!=null){
			 compteur=compteurNumDossier.getCompteur();
	 if(compteur<10)
		  numDossier=annee+"/00"+compteur;
		 else if (compteur<100)
			 numDossier=annee+"/0"+compteur;
		 else 
			 numDossier=annee+"/"+compteur;
	 }else {
		  compteurNumDossier= new CompteurNumDossier();
		  compteurNumDossier.setAnnee(annee);
		  compteurNumDossier.setCompteur(1);
		  compteurNumDossierDAO.add(compteurNumDossier);
		  numDossier=annee+"/001";
		 
	 }
		 
		 return numDossier;
}


public static String  genererNumFactureProduction(String codeClasse){
	int compteur=0;
	 String numFacture="F-";
	 Calendar cal = Calendar.getInstance();
    cal.setTime(new Date ());
    String  annee = cal.get(Calendar.YEAR)+"";
	 Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(annee,codeClasse);
	 if(sequenceur!=null){
			 compteur=sequenceur.getValeur()+1;
			 sequenceur.setValeur(compteur);
			 sequenceurDAO.edit(sequenceur);
	
	 }else {
		 sequenceur= new Sequenceur();
		 sequenceur.setCode(annee);
		 sequenceur.setLibelle(codeClasse);
		 sequenceur.setValeur(1);
		 sequenceurDAO.add(sequenceur);
		 numFacture+=annee+"/001";
		 
	 }
	 
	 if(compteur!=0 && compteur<10)
		 numFacture+=annee+"/00"+compteur;
		 else if (compteur<100)
			 numFacture+=annee+"/0"+compteur;
		 else 
			 numFacture+=annee+"/"+compteur;
		 
		 return numFacture;
}



public static void genererMenuUtilisateur(){ 
	Habilitation habilitation = new Habilitation();
	List<Menu> listeMenu =menuDAO.findAll();
	List<Utilisateur> listUtilisateur = utilisateurDAO.findAll();
	
	
	if(listeMenu!=null && listUtilisateur!=null){
	for(int i=0;i<listeMenu.size();i++)
		for(int j=0;j<listUtilisateur.size();j++){
			 
			Menu menu =listeMenu.get(i);
			Utilisateur utilisateur =listUtilisateur.get(j);

			habilitation  =habilitationDAO.findByMenuUtilisateur(menu.getId(), utilisateur.getId());
			
			if(habilitation==null){
				habilitation = new Habilitation();
				habilitation.setMenu(menu);
				habilitation.setUtilisateur(utilisateur);
				habilitation.setAutorise(true);
				
				habilitationDAO.add(habilitation);
			}
		}
	}
  }


public static Map<String ,BigDecimal > listeParametre(){
	
	
	 Map<String, BigDecimal> mapParametre= new HashMap<>();
	List<Parametre>  listeParametre= parametreDAO.findAll();
	
	for(int i=0;i<listeParametre.size();i++){
		Parametre parametre=listeParametre.get(i) ;
		mapParametre.put(parametre.getCode(),parametre.getValeur());
		
	}
	 
	 
	 return mapParametre;
	
}


public static String  genererCodeTransfer(String codeDepot,String etatTransfer){
	utilisateur=AuthentificationView.utilisateur;
	facturepfdao=new FacturePFDAOImpl();
	 int compteur=0;
	 String codeTransfer="";
	 
	 	Date date = new Date();	
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		String dateJourString =dateFormat.format(date);

	 CompteurTransferMP compteurTransferMP=compteurTransferMPDAO.findByDepot(codeDepot);
	 
	 if(compteurTransferMP !=null){
		 compteur=compteurTransferMP.getCompteur()+1;
		 compteurTransferMP.setCompteur(compteur);	
		 compteurTransferMPDAO.edit(compteurTransferMP);
		 
	 }else{
		 compteur=1;
		 compteurTransferMP= new CompteurTransferMP();
		
		 compteurTransferMP.setCompteur(compteur);
		 compteurTransferMP.setCodeDepot(codeDepot);
		 compteurTransferMPDAO.add(compteurTransferMP);
	 }
	 if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
		{
		 
		 codeTransfer =etatTransfer+"_"+codeDepot.substring(0, 3)+"_"+dateJourString+"_"+compteur; 
		}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
		{
			
				 codeTransfer =etatTransfer+"_"+codeDepot.substring(0, 3)+"_"+dateJourString+"_"+compteur+"_"+facturepfdao.maxIdFacture(); 
			
		}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_ELALAOUI))
		{
			
			 codeTransfer =etatTransfer+"_"+codeDepot.substring(0, 3)+"_"+dateJourString+"_"+compteur+"_"+facturepfdao.maxIdFacture(); 
		
	      }


	 
	 return codeTransfer;
}

public static boolean genererPrixCientByMP(List<MatierePremier> listMP,List<Client> listClient,List<Fournisseur> listFournisseur){ 
	PrixClientMP prixClientMP = new PrixClientMP();
	if(listMP!=null && listClient!=null){
	for(int i=0;i<listMP.size();i++){
		MatierePremier matierePremiere =listMP.get(i);
		for(int j=0;j<listClient.size();j++){
				Client client =listClient.get(j);
			for(int k=0;k<listFournisseur.size();k++){
			
				Fournisseur fournisseur =listFournisseur.get(k);
				prixClientMP =prixClientMPDAO.findPrixByClientMP(matierePremiere.getCode(), client.getCode(),fournisseur.getCode());
			
			if(prixClientMP==null){
				prixClientMP = new PrixClientMP();
				prixClientMP.setClient(client);
				prixClientMP.setFournisseur(fournisseur);
				prixClientMP.setMatierePremier(matierePremiere);
				prixClientMP.setPrixUnitaire(BigDecimal.ZERO);
				prixClientMPDAO.add(prixClientMP);
			}
			}
		}
	}
	}
	return true ;
	
}



public static String genereCodeDateMoisAnnee(Date date) {
	String code="";
    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat simpleFormat1 = new SimpleDateFormat("MM");
    SimpleDateFormat simpleFormat2 = new SimpleDateFormat("dd");
    
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    
    int jour = calendar.get(GregorianCalendar.DATE);
    
    if(jour<=15)
    	code="15"+simpleFormat1.format(date)+simpleFormat.format(date);
    else
    	code="30"+simpleFormat1.format(date)+simpleFormat.format(date);
  

   return code;
}


public static void compterAbsenceEmploye(String code, Employe employe, Date dateProd){
	 int compteur=0;
	 CompteurAbsenceEmploye compteurAbsenceEmploye= compteurAbsenceEmployeDAO.findByDateAbsencePeriode(code, employe.getId());
	 
	 if(compteurAbsenceEmploye !=null){
		 compteur=compteurAbsenceEmploye.getCompteur()+1;
		 compteurAbsenceEmploye.setCompteur(compteur);	
		 compteurAbsenceEmployeDAO.edit(compteurAbsenceEmploye);
		 
	 }else{
		 compteurAbsenceEmploye= new CompteurAbsenceEmploye();
		 compteurAbsenceEmploye.setCode(code);
		 compteurAbsenceEmploye.setDateAbsence(dateProd);
		 compteurAbsenceEmploye.setEmploye(employe);
		 compteurAbsenceEmploye.setCompteur(1);
		 compteurAbsenceEmployeDAO.add(compteurAbsenceEmploye);
		 
	 }
	 
}

//Fuction Cpy Cut Past JtextField



public static void copycoller(JTextField text){
	
	JPopupMenu popup = new JPopupMenu();
	JMenuItem item = new JMenuItem(new DefaultEditorKit.CutAction());
	item.setText("Couper");
	popup.add(item);
	item = new JMenuItem(new DefaultEditorKit.CopyAction());
	item.setText("Copier");
	popup.add(item);
	item = new JMenuItem(new DefaultEditorKit.PasteAction());
	item.setText("Coller");
	popup.add(item);
	text.setComponentPopupMenu(popup);
	 
}

// fuction copy cut past cell jtable


public static void copycollercell(JTextComponent text){
	
	JPopupMenu popup = new JPopupMenu();
	JMenuItem item = new JMenuItem(new DefaultEditorKit.CutAction());
	item.setText("Couper");
	popup.add(item);
	item = new JMenuItem(new DefaultEditorKit.CopyAction());
	item.setText("Copier");
	popup.add(item);
	item = new JMenuItem(new DefaultEditorKit.PasteAction());
	item.setText("Coller");
	popup.add(item);
	text.setComponentPopupMenu(popup);
	 
}

}
