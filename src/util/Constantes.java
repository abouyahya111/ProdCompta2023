package util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JMenu;

public interface Constantes {

	public static final int PASSWORD_LENGTH = 8;

	public static String CODE_OUI = "OUI";
	public static String CODE_NON = "NON";

	/* ADMIN LOGIN AND MOT DE PASSE */

	public static String ADMIN_CONNEXION = "admin";

	/* etats ordre de fabrication */
	public static final String ETAT_OF_CREER = "Crée";
	public static final String ETAT_OF_LANCER = "Lancé";
	public static final String ETAT_OF_TERMINER = "Terminé";
	public static final String ETAT_OF_ANNULER = "Annulé";

	/* Etats commande */
	public static final String ETAT_COMMANDE_CREER = "Crée";
	public static final String ETAT_COMMANDE_LIVRER = "Livrée";
	public static final String ETAT_COMMANDE_RECU = "Reçu";

	/* Etat transfer transfert stock */

	public static final String ETAT_TRANSFER_STOCK_TRANSFERE = "TRANSFER";

	/* Transfer d'un magasin à un autre dansle memedépot */

	public static final String ETAT_TRANSFER_STOCK_ENTRE = "ENTRE";

	public static final String ETAT_TRANSFER_STOCK_CHARGE = "CHARGE";
	public static final String ETAT_TRANSFER_STOCK_CHARGE_SUPP = "CHARGE_SUPP";

	/* Stock entré d'un autre dépot */

	public static final String ETAT_TRANSFER_STOCK_SORTIE = "SORTIE";

	// Etat transfer Stock Type Vente

	public static final String ETAT_TRANSFER_STOCK_VENTE = "VENTE";

	// Etat transfer Stock Type Achat

	public static final String ETAT_TRANSFER_STOCK_ACHAT = "ACHAT";

	// Etat transfer Stock Type Service

	public static final String ETAT_TRANSFER_STOCK_SERVICE = "SERVICE";

	// Etat transfer Stock Type Avoir

	public static final String ETAT_TRANSFER_STOCK_AVOIR = "AVOIR";
	
	
	// Etat transfer Stock Type Avoir cLIENT

		public static final String ETAT_TRANSFER_STOCK_AVOIR_CLIENT = "AVOIR_CLIENT";
	
	
	// Etat transfer Stock Type Avoir Annee Precedente

		public static final String ETAT_TRANSFER_STOCK_AVOIR_R = "AVOIR_R";
		
		
		
		// Etat transfer Stock Type Reste

		public static final String ETAT_TRANSFER_STOCK_RESTE = "RESTE";
				

	// Etat transfer Stock Type Initial

	public static final String ETAT_TRANSFER_STOCK_INITIAL = "INITIAL";

	/* Stock sortie vers un autre dépot */

	public static final String ETAT_TRANSFER_STOCK_AJOUT = "AJOUT";

	/* Stock sortie vers un autre dépot */

	public static final String ETAT_TRANSFER_STOCK_SORTIE_PF = "SORTIE_PF";
	
	public static final String ETAT_TRANSFER_STOCK_SORTIE_MP_PF = "SORTIE_MP_PF";
	
	public static final String ETAT_TRANSFER_STOCK_SORTIE_PF_PF = "SORTIE_PF_PF";
	
	public static final String ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE = "SORTIE_PF_S";

	/* Stock sortie vers un magasin produit fini */

	public static final String ETAT_TRANSFER_STOCK_ENTRER_MP = "ENTRER_MP";
	
	public static final String ETAT_TRANSFER_STOCK_ENTRER_PF_MP = "ENTRER_PF_MP";
	
	public static final String ETAT_TRANSFER_STOCK_ENTRER_PF_PF = "ENTRER_PF_PF";

	/* production service oui */
	public static final String PRODUCTION_SERVICE_OUI = "SERVICE";

	/* production service non */
	public static final String PRODUCTION_SERVICE_NON = "PRODUCTION";

	/* production service facture */
	public static final String PRODUCTION_SERVICE_FACTURE = "F";

	/* coutMP transfer oui */
	public static final String COUT_MP_TRANSFER_OUI = "O";

	/* coutMP transfer non */
	public static final String COUT_MP_TRANSFER_NON = "N";

	/* CODE BOX */
	public static final String CODE_BOX = "BOX001";

	/* CODE CARTON */
	public static final String CODE_CARTON = "CA001";

	/* CODE TAMPON */
	public static final String CODE_TAMPON = "TMP001";

	/* CODE STICKERS */
	public static final String CODE_STICKERS = "STI001";

	/* CODE THERRES VERRES */
	public static final String CODE_THERRES_VERRES = "CAD001";
	
	/* CODE SACHET */
	public static final String CODE_SACHET = "SACH001";
	
	/* CODE PIECE */
	public static final String CODE_PIECE = "PIEC001";
	
	/* UNITE PIECE */
	public static final String UNITE_PIECE = "Piece";
	
	/* CODE CHAARA */
	public static final String CODE_CHAARA = "F001";
	
	/* CODE MKARKAB */
	public static final String CODE_MKARKEB = "F002";
	
	/* CODE OFFRE */
	public static final String CODE_CADEAU = "F010";
	
	/* FAMILLE CADEAU */
	public static final String FAMILLE_CADEAU = "OFFRE";
	
	/* FAMILLE DECHET */
	public static final String FAMILLE_DECHET = "DECHET";
	
	/* FAMILLE EMBALLAGE */
	public static final String FAMILLE_EMBALLAGE = "EMBALLAGE";
	
	/* FAMILLE CONSOMMABLE */
	public static final String FAMILLE_CONSOMMABLE = "MC";
	
	/*CODE SOUS FAMILLE CONSOMMABLE */
	public static final String SOUS_FAMILLE_CONSOMMABLE = "MC";
	
	/*LIBELLE FAMILLE CHAARA */
	public static final String LIBELLE_FAMILLE_CHAARA = "CHAARA";
	
	/*LIBELLE FAMILLE MKARKEB */
	public static final String LIBELLE_FAMILLE_MKARKEB = "MKARKEB";
	
	/*LIBELLE FAMILLE EL NASS */
	public static final String LIBELLE_FAMILLE_ELNASS = "EL NASS";
	
	
	/*LIBELLE FAMILLE ADMINISTRATION */
	public static final String LIBELLE_FAMILLE_ADMINISTRATION = "ADMINISTRATION";
	
	/*LIBELLE FAMILLE EAU */
	public static final String LIBELLE_FAMILLE_EAU = "EAU";
	
	/*LIBELLE FAMILLE EAU */
	public static final String LIBELLE_FAMILLE_JUS = "JUS";
	
	/*LIBELLE FAMILLE POISSON */
	public static final String LIBELLE_FAMILLE_POISSON = "POISSON";
	
	/*LIBELLE FAMILLE CERAMICA */
	public static final String LIBELLE_FAMILLE_CERAMICA = "CERAMICA";
	
	/*LIBELLE FAMILLE TOMATES */
	public static final String LIBELLE_FAMILLE_TOMATES = "TOMATES";
	
	/*LIBELLE FAMILLE ALMANDS */
	public static final String LIBELLE_FAMILLE_ALMANDS = "ALMANDS";
	
	/*LIBELLE FAMILLE ALMANDS */
	public static final String LIBELLE_FAMILLE_RIZ = "RIZ";
	
	
	/*LIBELLE FAMILLE EAU GAZEUSE */
	public static final String LIBELLE_FAMILLE_EAU_GAZEUSE = "EAU GAZEUSE";
	
	/* Stock entrer d 'un magasin MP */

	/* Responsabilité */
	public static final String EMPLOYE_RESPONSABILITE_AIDE_TECHNITIEN = "ATEC";
	public static final String EMPLOYE_RESPONSABILITE_TECHNITIEN = "TEC";
	public static final String TYPE_EMPLOYE_RESPONSABLE_EQUIPE = "REE";
	public static final String TYPE_EMPLOYE_CHEF_PRODUCTION = "REP";
	public static final String TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION = "MOP";
	public static final String TYPE_EMPLOYE_MAIN_OUVRE_EQUIPE = "MOE";
	public static final String TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC = "MOV";

	/* type d'equipe */
	public static final String TYPE_EQUIPE_CODE_PRPDUCTION = "E_PROD";
	public static final String TYPE_EQUIPE_CODE_GENERIQUE = "E_GEN";

	/* Periode */

	public static final String PRPDUCTION_PERIODE_MATIN = "MATIN";
	public static final String PRPDUCTION_PERIODE_SOIR = "SOIR";

	/* TYPE TRANSFER PRODUIT FINI */
	public static final String STATUT_TRANSFER_PRODUIT_FINI_ENTRE = "ENTRE";
	public static final String STATUT_TRANSFER_PRODUIT_FINI_SORTIE = "SORTIE";

	public static final String TYPE_TRANSFER_PRODUIT_FINI_ENTRE = "ENTRE PRODUCTION";
	public static final String TYPE_TRANSFER_PRODUIT_FINI_SORTIE = "SORTIE COMMERCIAL";

	/* CODE PARAMETRE */
	public static final String PARAMETRE_CODE_QUANTITE_TOUNAGE_200G = "PARAM_1";
	public static final String PARAMETRE_CODE_QUANTITE_TOUNAGE_100G = "PARAM_2";
	public static final String PARAMETRE_CODE_QUANTITE_TOUNAGE_500G = "PARAM_3";
	public static final String PARAMETRE_CODE_COUT_HORAIRE = "PARAM_4";
	public static final String PARAMETRE_CODE_COUT_HORAIRE_CNSS = "PARAM_5";
	public static final String PARAMETRE_CODE_TAUX_CNSS_226 = "PARAM_6";
	public static final String PARAMETRE_CODE_TAUX_CNSS_448 = "PARAM_7";
	public static final String PARAMETRE_CODE_REMISE_CHEF_PROD = "PARAM_8";
	public static final String PARAMETRE_CODE_REMISE_TECHNICIEN = "PARAM_9";
	public static final String PARAMETRE_CODE_REMISE_AIDE_TECHNICIEN = "PARAM_10";
	public static final String PARAMETRE_CODE_REMISE_CHEF_EQUIPE = "PARAM_11";
	public static final String PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION = "PARAM_12";
	public static final String PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE = "PARAM_13";
	public static final String PARAMETRE_CODE_REMISE_EQUIPE_GENERIQUE = "PARAM_14";
	public static final String PARAMETRE_CODE_STOCK_MINIMAL_FILM_NORMAL_100G = "PARAM_15";

	
	
	
	
	/* TYPE MAGASIN */
	public static final String MAGASIN_CODE_TYPE_MP = "MP";
	public static final String MAGASIN_LIBELLE_TYPE_MP = "Matière Première";
	public static final String MAGASIN_CODE_TYPE_PF = "PF";
	public static final String MAGASIN_LIBELLE_TYPE_PF = "Produit Fini";
	public static final String MAGASIN_LIBELLE_TYPE_MP_ATT = "Matière Première Att";
	public static final String MAGASIN_CODE_TYPE_MP_ATT = "MP_ATT";
	
	/*  CODE   MAGASIN AHL BRAHIM      */
	public static final String MAGASIN_AHB_MP = "TAN_MAG_6";
	public static final String MAGASIN_PF_AHL_BRAHIM ="TAN_MAG_7";
	
	
	/* TYPE MP */
	
	String TYPE_MP[] = { "STOCK", "DECHET", "OFFRE"};
	
	public static final String MP_STOCK = "STOCK";
	public static final String MP_STOCK_DECHET = "DECHET";
	public static final String MP_STOCK_OFFRE = "OFFRE";
	
	
	/* TYPE MAGASIN */
	public static final String MAGASIN_CODE_CATEGORIE_STOCKAGE = "STK";
	public static final String MAGASIN_LIBELLE_CATEGORIE_STOCKAGE = "Magasin Stockage";
	public static final String MAGASIN_CODE_CATEGORIE_PRODUCTION = "PROD";
	public static final String MAGASIN_LIBELLE_CATEGORIE_PRODUCTION = "Magasin Production";
	public static final String MAGASIN_LIBELLE_CATEGORIE_STOCKAGE_ATT = "Magasin Stockage Att";
	public static final String MAGASIN_CODE_CATEGORIE_STOCKAGE_ATT = "STK ATT";
	
	
	
	
	/* SEQUENCEUR LIBELLE CLASSE POUR GENERER LES CODES */

	public static final String PARAMETRE_LIBELLE = "Parametre";
	public static final String MAGASIN_LIBELLE = "Magasin";
	public static final String MATIERE_PREMIERE_LIBELLE = "MatierePremier";
	public static final String MACHINE_LIBELLE = "Machine";
	public static final String LIGNE_MACHINE_PREMIERE_LIBELLE = "LigneMachine";
	public static final String EQUIPE_PREMIERE_LIBELLE = "Equipe";
	public static final String EMPLOYE_NUM_DOSSIER_LIBELLE = "Employe";
	public static final String PRODUCTION_LIBELLE = "Production";
	public static final String CLIENT_LIBELLE = "Client";
	public static final String FACTURE_PRODUCTION_LIBELLE = "FactureProduction";

	/* Libelle charges sequenseur */
	public static final String CHARGES = "charges";
	
	
	// Production MP
	public static final String ETAT_TRANSFER_STOCK_FABRIQUE = "FABRIQUE";

	/* code TYPE charges */
	public static final String CODE_CHARGES_FIXE = "F";
	public static final String CODE_CHARGES_VARIABLE = "V";
	
	
	/* code Commerciale Article */

	public static final String CODE_COMMERCIALE_ARTICLE = "ART";
	public static final String LIBELLE_COMMERCIALE_ARTICLE = "CODE_COMMERCIALE";
	
	

	/* code Facture ETP */

	public static final String CODE_FACTURE_VENTE_ETP = "FACTURE_VENTE_ETP";
	

	/* code Facture AHB */

	public static final String CODE_FACTURE_VENTE_AHB = "FACTURE_VENTE_AHB";
	
	/* code Facture ALOUI */

	public static final String CODE_FACTURE_VENTE_ALAOUI = "FACTURE_VENTE_ALAOUI";
	
	/* code Facture FOURSSAN ALJANOUB */

	public static final String CODE_FACTURE_VENTE_FOURSSAN = "FACTURE_VENTE_FOURSSAN";
	
	/* code Facture Avoir ALAOUI */

	public static final String CODE_FACTURE_AVOIR_ALAOUI = "FACTURE_AVOIR_ALAOUI";
	
	/* code Facture Avoir ALAOUI */

	public static final String CODE_FACTURE_AVOIR_FOURSSAN = "FACTURE_AVOIR_FOURSSAN";
	
	/* code Facture Avoir ETP */

	public static final String CODE_FACTURE_AVOIR_ETP = "FACTURE_AVOIR_ETP";
	
	
	/* code Facture Avoir AHB */

	public static final String CODE_FACTURE_AVOIR_AHB = "FACTURE_AVOIR_AHB";
	
	
	/* code Facture SERVICE ETP */

	public static final String CODE_FACTURE_SERVICE_ETP = "FACTURE_SERVICE_ETP";

	/* code Facture SERVICE AHB */

	public static final String CODE_FACTURE_SERVICE_AHB = "FACTURE_SERVICE_AHB";

	/* TYPE CHARGES */
	public static final String CLIENT_LIBELLE_EXTERNE = "EXTERNE";
	public static final String CLIENT_CLIENT_INTERNE = "INTERNE";

	/* GENERER CODE CHARGES */
	public static final String CHARGEST_FIX = "F";
	public static final String CHARGEST_VARIABLE = "V";

	/* FORMAT DATE */
	public static final String DATE_FORMAT = "dd/MM/yyyy";

	public static final String DATE_FORMAT_YEAR = "yyyy";

	/* CODE MENU */
	public static final String MENU_DEPOT = "depotMenu";
	public static final String MENU_MATIERE_PREMIERE = "MatierePremierMenu";
	public static final String MENU_ARTICLE = "articleMenu";
	public static final String MENU_STOCK_MATIERE_PREMIERE = "stockMPMenu";
	public static final String MENU_MACHINE = "uniteFabMenu";
	public static final String MENU_EQUIPE = "equipeFabMenu";
	public static final String MENU_ORDRE_FABRICATION = "ordreFabMenu";
	public static final String MENU_STOCK_PRODUIT_FINI = "stockPFMenu";
	public static final String MENU_PARAMETRE = "parametreMenu";
	public static final String MENU_CLIENT = "clientMenu";
	
	
	public static final String MENU_fournisseur="fournisseurMenu";
    public static final String MENU_prodCart="prodcartMenu";
	public static final String MENU_Referentiel="referentielMenu";
	public static final String MENU_FacturePF="facturepfMenu";
	public static final String MENU_Reglement="reglementMenu";
	
	public static final String MENU_FactureAchat="FactureAchatMenu";
	public static final String MENU_FactureAchatMP="factureachatmpMenu";
	public static final String MENU_FactureServiceProduction="factureserviceproductionMenu";
	public static final String MENU_EtatsDeVerification="etatdeverificationMenu";
	public static final String MENU_Etats="etatsMenu";
	public static final String MENU_Administration="administrationMenu";
	
	
	
	
	

	public static final String SERVICE_EMPLOYE = "PRODUCTION";

	/* CODE MATIETRE PREMIERE */
	public static final String MATIERE_PREMIERE_FILM_NORMAL_2OOG = "MP_57";
	public static final String MATIERE_PREMIERE_FILM_GOLD_2OOG = "MP_54";  
	public static final String MATIERE_PREMIERE_SCOTCH_1000M = "MP_61";
	public static final String MATIERE_PREMIERE_SERVICE_PRODUCTION = "MP_SERV";
	public static final String MATIERE_PREMIERE_THERRES = "MP_67";
	public static final String MATIERE_PREMIERE_VERRES = "MP_70";
	

	/* CODE SOUS CATEGORIE MATIETRE PREMIERE */
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_THE = "TH001"; 
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_NORMAL = "FN001";  
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_GOLD = "FG001";    
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE = "CEN001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_SCOTCH = "SCO001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON = "CA001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_PAPIER_INTERNE = "PAP001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_BOX = "BOX001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU = "CAD001";
	public static final String SOUS_CATEGORIE_MATIERE_PREMIERE_ADHESIF = "ADH001"; 

	/* PRIORITE ESTIMATION MATIERE PREMIERE */
	public static final int PRIORITE_ESTIMATION_0 = 0;
	public static final int PRIORITE_ESTIMATION_1 = 1;
	public static final int PRIORITE_ESTIMATION_2 = 2;

	/* CODE CATEGORIE MATIERE PREMIERE */
	public static final String CATEGORIE_MATIERE_PREMIERE_SCOTCH = "CAT001";
	public static final String CATEGORIE_MATIERE_PREMIERE_CADEAU = "CCAD001";

	/* CODE MAGASIN */
	
	public static final String CODE_MAGASIN_FOURNISSEUR = "SIE_MAG_23";
	public static final String CODE_MAGASIN_STOCKAGE_EN_ATTENTE = "STO_MAG_3";
	public static final String CODE_MAGASIN_ELNASS_TEA_PACKING = "TAN_MAG_1";
	public static final String CODE_MAGASIN_ELNASS_TEA_PACKING_PF = "TAN_MAG_3";
	public static final String CODE_MAGASIN_PRODUCTION = "TAN_MAG_2";
	public static final String TRANSFERE_COD_PRODUCTION_PR = "SORTIE_PF";

	/* CODE DEPOT */
	public static final String CODE_DEPOT_SIEGE = "SIEGE";
	public static final String CODE_DEPOT_STOCK_EN_ATTENTE = "SIEGE";

	/* CODE MACHINE LIER AU MAGASIN STOCKAGE : c'est une machine vertuelle */
	public static final String CODE_MACHINE_STOCKAGE = "M0";
	public static final String LIBELLE_MACHINE_STOCKAGE = "MAGASIN STOCKAGE";

	public static final BigDecimal COUT_HEURE_SUPPLEMENTAIRE_25 = new BigDecimal(
			16.825);

	public static final BigDecimal COUT_HEURE_SUPPLEMENTAIRE_50 = new BigDecimal(
			20.19);

	/* Désignation service pour la facturation des services production */

	/* CODE DEPOT PRODUCTION */

	public static final String CODE_DEPOT_PRODUCTION_TANTAN = "TANTAN";
	public static final String CODE_DEPOT_PRODUCTION_LAAYOUNE = "LAAYOUNE";

	public static final String DEBUT_NUM_OF_PRODUCTION_TANTAN = "T";
	public static final String DEBUT_NUM_OF_PRODUCTION_LAAYOUNE = "L";

	public static final String CODE_CLIENT_FOURNISSEUR_SERVICE_PRODUCTION_TANTAN = "TAN_C1";
	public static final String CODE_CLIENT_FOURNISSEUR_SERVICE_PRODUCTION_LAAYOUNE = "LAA_C1";

	// pourcentage
	public static final String POURCENTAGE = "%";

	// Pourcentage_DH

	public static final String POURCENTAGEDH = "DH";

	// Promotion offre
	public static final String PROMOTION_OFFRE = "P";

	// Code charge Depot variable à changer
	public static final String CODE_FRAIS_DEPOT = "V1";

	// Bon Fixe
	public static final String BON_FIXE = "BF";

	// Bon Variable
	public static final String BON_VARIABLE = "BV";

	// Etat Type Vente Article
	public static String ETAT_TYPE_VENTE_D = "D";
	public static String ETAT_TYPE_VENTE_G = "G";

	// Etat Facture PF
	public static String ETAT_NON_REGLE = "Non Reglé";
	public static String ETAT_REGLE = "Reglé";
	public static String ETAT_IMPORTATION_EXCEL = "IMPORTATION_EXCEL";

	// TYPE FACTURE
	public static String TYPE_BON_LIVRAISON = "BL";
	public static String TYPE_FACTURE = "Facturé";
	public static String TRANSFERE_BL_FACTURE = "Facture";
	public static String TYPE_BON_LIVRAISON_REGULARISATION = "BL_REGULARISATION";

	// MODE REGLEMENT
	public static String MODE_REGLEMENT_CHEQUE = "Chèque";
	public static String MODE_REGLEMENT_ESPECE = "Espèces";
	public static String MODE_REGLEMENT_VIREMENT = "Virement";
	public static String MODE_REGLEMENT_VERSEMENT = "Versement";
	public static String MODE_REGLEMENT_TRAITE = "Traite";
	public static String MODE_REGLEMENT_CREDIT = "Crédit";
	// TVA
	public static BigDecimal TVA = new BigDecimal(0.20);
	
	// TVA TRANSPORT
		public static BigDecimal TVA_TRANSPORT = new BigDecimal(0.14);

	// ICE FACTURE VENTE
	public static String ICE_AHLBRAHIM = "001536022000047";
	
	
		public static String ICE_ELALAOUI = "002003477000014";
	
	public static String ICE_ETP= "001564063000025";

	// TIMBER
	public static BigDecimal TIMBER = new BigDecimal(0.25);

	// MARGE MIN
	public static String MARGE_MIN = "PARAM_17";

	// MARGE MAX
	public static String MARGE_MAX = "PARAM_16";

	// CODE COMPTE

	public static String CODE_COMPTE = "COMPTE";

	// ETAT STATUT JOURNEE

	public static String ETAT_STATUT_OVERTE = "Ouverte";
	public static String ETAT_STATUT_FERMER = "Fermer";

	// CODE FOURNISSEUR
	public static String CODE_FOURNISSEUR = "Four_";
	public static String LIBELLE_FOURNISSEUR = "FOURNISSEUR";

	// // CODE SOUS FAMILLE
	public static String CODE_SOUS_FAMILLE_3505_A = "MP_5";
	public static String CODE_SOUS_FAMILLE_3505_A_5KG = "MP_59";
	public static String CODE_SOUS_FAMILLE_4011 = "MP_6";
	public static String CODE_SOUS_FAMILLE_401022 = "MP_7";
	public static String CODE_SOUS_FAMILLE_9366 = "MP_8";
	public static String CODE_SOUS_FAMILLE_9367 = "MP_9";
	public static String CODE_SOUS_FAMILLE_9368 = "MP_10";
	public static String CODE_SOUS_FAMILLE_9371 = "MP_11";
	public static String CODE_SOUS_FAMILLE_9475 = "MP_12";
	public static String CODE_SOUS_FAMILLE_9575 = "MP_13";
	public static String CODE_SOUS_FAMILLE_9369 = "MP_18";
	public static String CODE_SOUS_FAMILLE_3505_AA = "MP_19";
	public static String CODE_SOUS_FAMILLE_3505_AA_5KG = "MP_64";
	public static String CODE_SOUS_FAMILLE_3505_AAA_5KG = "MP_63";
	public static String CODE_SOUS_FAMILLE_3505_B = "MP_20";
	public static String CODE_SOUS_FAMILLE_3505_C = "MP_21";
	public static String CODE_SOUS_FAMILLE_3505_D = "MP_22";
	public static String CODE_SOUS_FAMILLE_3505 = "MP_29";
	public static String CODE_SOUS_FAMILLE_4011_NAZHA = "MP_43";
	public static String CODE_SOUS_FAMILLE_4011_MAHA = "MP_63";
	

	// // CODE MP
	public static String CODE_MP_3505_A = "3505A";
	public static String CODE_MP_4011 = "4011";
	public static String CODE_MP_401022 = "41022";
	public static String CODE_MP_9366 = "9366";
	public static String CODE_MP_9367 = "9367";
	public static String CODE_MP_9368 = "9368";
	public static String CODE_MP_9371 = "9371";
	public static String CODE_MP_9475 = "9475";
	public static String CODE_MP_9575 = "9575";
	public static String CODE_MP_9369 = "9369";
	public static String CODE_MP_3505_AA = "3505AA";
	public static String CODE_MP_3505_AAA = "3505AAA";
	public static String CODE_MP_3505_B = "3505B";
	public static String CODE_MP_3505_C = "3505C";
	public static String CODE_MP_3505_D = "3505D";
	public static String CODE_MP_3505 = "3505";
	
	public static String ARTICLE_PACKET = "PACKET";
	public static String ARTICLE_PIECE = "PIECE";
	public static String ARTICLE_CONSOMMABLE = "MC";
	
	
	public static String CODE_DEPOT_TANTAN = "TANTAN";
	
	public static String CODE_DEPOT_AGADIR = "AGADIR";
	
	public static String CODE_DEPOT_ELALAOUI= "TANTAN_1";
	
	public static String CODE_DEPOT_FOURSSAN_ALJANOUB= "TANTAN_4";
	
	// Cout Service
	public static String COUT_SERVICE = "PARAM_18";
	
	// PERCENTAGE PRIX THERRES
		public static String PERCENTAGE_PRIX_OFFRE = "PARAM_19";
		
		// PERCENTAGE PRIX VERRES
		//public static String PERCENTAGE_PRIX_VERRES = "PARAM_20";
		
		// NOMBRE DES FACTURES PAR JOURS
		public static String NOMBRE_FACTURE_PAR_JOUR = "PARAM_20";
		

	// le grammage
	String grammagebox[] = {"100", "160", "200", "500", "1"};
	// le grammage
		String grammagecarton[] = {"10", "160", "200", "500", "1"};
	
	// tous
	public static final String TOUS = "Tous";
	
	
	// article offre les therres et les verres
	
	public static final String OFFRE_THERRES = "MP_67_PFO";
	public static final String OFFRE_VERRES = "MP_70_PFO";
	
	// sous famille les therres et les verres
	
		public static final String SOUS_FAMILLE_OFFRE_THERRES = "MP_67_O";
		public static final String SOUS_FAMILLE_OFFRE_VERRES = "MP_70_O";
	
		// Statut Les factures Avoir Comparaison Marjan / Normal
		
			public static final String STATUT_FACTURE_AVOIR_COMPARAISON_MARJAN = "MARJAN";
			public static final String STATUT_FACTURE_AVOIR_COMPARAISON_NORMAL = "NORMAL";
		
		// DESIGNATION FACTURE SERVICE TRANSPORT
			
			public static final String DESIGNATION_FACTURE_SERVICE_TRANSPORT = "SERVICE TRANSPORT";
			
			public static final String MOIS = "  Mois  ";
			
			// CODE VILLE
			
						public static final String CODE_VILLE = "VILLE_00";
			
			
			
						// CODE PRODUIT FINI OFFRE  DE PRODUCTION
						
						public static final String CODE_PRODUIT_FINI_OFFRE = "_PFO";	
// CODE PRODUIT FINI DECHET  DE PRODUCTION
						
						public static final String CODE_PRODUIT_FINI_DECHET = "_PFD";
		
						
// DESIGNATION FACTURE SERVICE PRODUCTION 
						
						public static final String DESIGNATION_FACTURE_SERVICE_PRODUCTION  = "SERVICE CONDITIONNEMENT DU THE MARQUE CLIENT : ";		
						
						
// DESIGNATION FACTURE OFFRE SERVICE PRODUCTION 
						
						public static final String DESIGNATION_FACTURE_OFFRE_SERVICE_PRODUCTION  = " (OFFRE DANS LE CARTON)";		
						
						
						// NOM EN VRAC
						
						public static final String NOM_ENVRAC = "THE EN VRAC ";					
						
// AUTRE CLIENTS
						
						public static final String AUTRE_CLIENT = "AUTRE CLIENT";	
						
// SERVICES
						
						public static final String SERVICE_VENTE = "VENTE";	
						public static final String SERVICE_PRODUCTION_TRANSPORT = "PRODUCTION/TRANSPORT";	
						
}
