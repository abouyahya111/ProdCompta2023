package Referentiel;

import groovy.lang.Sequence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureAchatDAOImpl;
import dao.daoImplManager.DetailFactureAchatMPDAOImpl;
import dao.daoImplManager.DetailFactureAvoirClientPFDAOImpl;
import dao.daoImplManager.DetailFactureAvoirPFDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailFactureAchatDAO;
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFactureAvoirClientPFDAO;
import dao.daoManager.DetailFactureAvoirPFDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.ClientPF;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAvoirClientPF;
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatInitialParSousCtaegorieMP;
import dao.entity.EtatMargeDeStockArticlePF;
import dao.entity.EtatPrixMoyen;
import dao.entity.EtatPrixMoyenMP;
import dao.entity.EtatStockMP;
import dao.entity.EtatStockPF;
import dao.entity.EtatValeurSystem;
import dao.entity.EtatValeurisationStockParSousFamille;
import dao.entity.EtatVenteParFamilleArticle;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FactureServiceProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.FicheEmploye;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.MouvementStockProduitsFini;
import dao.entity.Parametre;
import dao.entity.PrixMoyenStockMP;
import dao.entity.PrixMoyenStockPF;
import dao.entity.Production;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.TypeVente;
import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;










































import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextPane;


public class ConsulterMargeDeStockArticlePF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEtatStock;
	private DefaultTableModel	 modeleMouvementStock;

	private JXTable  tableEtatStock = new JXTable();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	private List<EtatStockPF> listEtatStockPF =new ArrayList<EtatStockPF>();
	private List<EtatStockPF> listEtatStockPFImprimer =new ArrayList<EtatStockPF>();
	private List<FamilleArticlePF> listFamilleArticlesF =new ArrayList<FamilleArticlePF>();
	private Map< String, FamilleArticlePF> mapFamilleArticles= new HashMap<>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	
	//**********************************************************************listes Mouvemement Stock***************************************************
	
	private List<DetailTransferProduitFini> listDetailTransferStockPF =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFGroupebyArticle =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFBytypetransfer =new ArrayList<DetailTransferProduitFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPF =new ArrayList<MouvementStockProduitsFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPFAfficher =new ArrayList<MouvementStockProduitsFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPFAfficherTmp =new ArrayList<MouvementStockProduitsFini>();
	
	
	
	//*************************************************************************************************************************************************
	
	
private List<EtatStockMP> listEtatStockMP =new ArrayList<EtatStockMP>();
	
	private List<DetailTransferStockMP> listDetailTransferStockMPTmp =new ArrayList<DetailTransferStockMP>();
	
	//******************************************* Listes Pour Mouvement de Stock Mp **********************************************//
	
	
	private List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPBytypetransfer =new ArrayList<DetailTransferStockMP>();
	private List<DetailMouvementStock> listMouvementStockMP =new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficher =new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficherMouvementTmp =new ArrayList<DetailMouvementStock>();
	
	//*******************************************************************************************************************************//
	
	// ************************************************
	
	private List<DetailTransferStockMP> listDetailTransferStockMPInitial =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPAchat =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPAchatGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPSortie =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPSortieGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPAvoir =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPAvoirGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeSupp =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeSuppGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPService =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPServiceGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPVente =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPVenteGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPF =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFProduction =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFProductionGroupebyMP =new ArrayList<DetailTransferStockMP>();
	
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFService =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFGroupebyMPService =new ArrayList<DetailTransferStockMP>();
	
	private List<DetailTransferStockMP> listDetailTransferStockMPAllMP =new ArrayList<DetailTransferStockMP>();
	
	//***************************************************
	private List<EtatMargeDeStockArticlePF> listEtatMargeDeStockArticlePF =new ArrayList<EtatMargeDeStockArticlePF>();
	private List<EtatValeurisationStockParSousFamille> listEtatValeurisationStockParSousFamille =new ArrayList<EtatValeurisationStockParSousFamille>();
	private List<EtatValeurisationStockParSousFamille> listEtatValeurisationStockParSousFamilleMPTransformerEnPF =new ArrayList<EtatValeurisationStockParSousFamille>();
	
	private List<EtatInitialParSousCtaegorieMP> listEtatInitialMP =new ArrayList<EtatInitialParSousCtaegorieMP>();
	
	
	
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();

	private Map< String, Articles> mapArticle= new HashMap<>();
	private Map< String, Articles> mapCodeArticle= new HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	 JComboBox combomp = new JComboBox();
	 
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
private DetailTransferProduitFiniDAO detailTransferStockPFDAO;
private DetailFactureAchatDAO detailFactureAchatdao;
	private JTextField txtlibelle=new JTextField();
	  JComboBox comboMagasinMP = new JComboBox();
	
	private DepotDAO depotdao;
	 JDateChooser dateChooserdebut = new JDateChooser();
	 JDateChooser dateChooserfin = new JDateChooser();
	 private JDateChooser dateChooser = new JDateChooser();
private FamilleArticlesPFDAO familleArticlesDAO;
	 JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockPFDAO stockpfDAO;
	private CompteClientDAO compteclientdao;
	private ArticlesDAO ArticleDAO;
	String titre="";
	private TransferStockPFDAO transferStockPFDAO;
	private FactureAchatDAO factureAchatdao;
	
	 JTextArea textArea = new JTextArea();
	 JLabel lblDateInitial = new JLabel("Date Initial :");
	 JButton btnTransfert = new JButton("Transfert");
	 JDateChooser dateinitiale = new JDateChooser();
	 private DetailTransferMPDAO detailTransferStockMPDAO;
	 private TransferStockMPDAO transferStockMPDAO;
	 private SousFamilleEnVracDAO sousFamilleEnvracDAo;
	 private StockMPDAO stockMPDAO;
	 JComboBox comboMagasinPF = new JComboBox();
		private List <Object[]> listeObjectPrixMoyenAchat=new ArrayList<Object[]>();
		private List<DetailTransferStockMP> listDetailTransfertStockMP =new ArrayList<DetailTransferStockMP>();
		private DetailFactureAchatMPDAO detailFactureAchatMPdao;
		private List<PrixMoyenStockMP> listPrixMoyenStockMP =new ArrayList<PrixMoyenStockMP>();
		private List <Object[]> listeObject=new ArrayList<Object[]>();
		private MatierePremiereDAO MatierPremierDAO;
		private List<EtatStockMP> listEtatStockMPParSousFamille =new ArrayList<EtatStockMP>();
		private List<DetailFactureAchat> listDetailFacture =new ArrayList<DetailFactureAchat>();
		private List<DetailFactureAchat> listDetailFactureTmp =new ArrayList<DetailFactureAchat>();
		private List<DetailTransferProduitFini> listDetailTransferProduitFiniTmp =new ArrayList<DetailTransferProduitFini>();
		private List<EtatVenteParFamilleArticle> listEtatVente =new ArrayList<EtatVenteParFamilleArticle>();
		FamilleArticlePF FamilleOffre;
		private  DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;
		private  DetailFactureAchatDAO detailfactureAchatdao;
		private List<PrixMoyenStockPF> listEtatprixMoyenServiceAfficher =new ArrayList<PrixMoyenStockPF>();
		 private List<EtatPrixMoyenMP> listEtatPrixMoyenValeurisationProduction =new ArrayList<EtatPrixMoyenMP>();
		 private List<EtatVenteParFamilleArticle> listEtatPrixMoyenserviceParSousFamille =new ArrayList<EtatVenteParFamilleArticle>();
			private List<EtatPrixMoyen> listEtatPrixMoyenOffreDechet =new ArrayList<EtatPrixMoyen>();
			private List<PrixMoyenStockPF> listEtatprixMoyenService =new ArrayList<PrixMoyenStockPF>();
			private List <Object[]> listeObjectOffreDechet=new ArrayList<Object[]>();
		 FamilleArticlePF FamilleDechet; 
		 private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
			private List<CoutMP> listCoutMPTmp =new ArrayList<CoutMP>();
			private DetailFactureServiceProductionDAO detailFactureServiceProductionDAO;
			private FactureServiceProductionDAO factureServiceProductionDAO;
			ProductionDAO productionDAO;
			private List<DetailFacturePF> listDetailFactureVentePF =new ArrayList<DetailFacturePF>();
			private List<DetailFacturePF> listDetailFactureVentePFTmp =new ArrayList<DetailFacturePF>();
			private  DetailFacturePFDAO detailfacturedao;
			private List<DetailFactureAvoirPF> listDetailFactureAvoirPF =new ArrayList<DetailFactureAvoirPF>();
			private List<DetailFactureAvoirPF> listDetailFactureAvoirPFTmp =new ArrayList<DetailFactureAvoirPF>();
			private List<EtatVenteParFamilleArticle> listEtatAvoir =new ArrayList<EtatVenteParFamilleArticle>();
			private List<DetailFactureAvoirClientPF> listDetailFactureAvoirClientPF =new ArrayList<DetailFactureAvoirClientPF>();
			private List<DetailFactureAvoirClientPF> listDetailFactureAvoirClientPFTmp =new ArrayList<DetailFactureAvoirClientPF>();
			private List<EtatVenteParFamilleArticle> listEtatAvoirClientPF =new ArrayList<EtatVenteParFamilleArticle>();
			
			private  DetailFactureAvoirClientPFDAO detailfactureAvoirClientPFdao;
			private  DetailFactureAvoirPFDAO detailfactureAvoirdao;
			private SubCategorieMPDAO subCategorieMPDAO;
			private List<EtatPrixMoyen> listEtatPrixMoyen =new ArrayList<EtatPrixMoyen>();
			private List<EtatPrixMoyen> listEtatPrixMoyenAfficher =new ArrayList<EtatPrixMoyen>();
			private List <DetailFacturePF> listeDetailFacturePF=new ArrayList<DetailFacturePF>();
			private List <DetailFactureAvoirClientPF> listeDetailFactureAvoirClientPF=new ArrayList<DetailFactureAvoirClientPF>();
			private DetailFacturePFDAO detailFacturePfdao;
///////////////////////////////////////////////////////////////////////  Calcule Excel  ////////////////////////////////////////////////////////////////////////////////////////////////////////			
		
		BigDecimal StockInitial=BigDecimal.ZERO;
		BigDecimal StockInitialEmballage=BigDecimal.ZERO;
		BigDecimal StockInitialSansEmballage=BigDecimal.ZERO;
		
		
//////////////////////////////////////////////////////////////////////////////////////		
		
		BigDecimal StockAchat=BigDecimal.ZERO;
		BigDecimal StockAchatEmballage=BigDecimal.ZERO;
		BigDecimal StockAchatSansEmballage=BigDecimal.ZERO;
		
//////////////////////////////////////////////////////////////////////////////////////
		
BigDecimal ProductionEntrer=BigDecimal.ZERO;
BigDecimal productionSortie=BigDecimal.ZERO;
BigDecimal Prixservice=BigDecimal.ZERO;


//////////////////////////////////////////////////////////////////////////////////////

BigDecimal StockGratuite=BigDecimal.ZERO;
BigDecimal StockGratuiteEmballage=BigDecimal.ZERO;
BigDecimal StockGratuiteSansEmballage=BigDecimal.ZERO;


//////////////////////////////////////////////////////////////////////////////////////

BigDecimal StockAvoir=BigDecimal.ZERO;
BigDecimal StockAvoirEmballage=BigDecimal.ZERO;
BigDecimal StockAvoirSansEmballage=BigDecimal.ZERO;

//////////////////////////////////////////////////////////////////////////////////////

BigDecimal StockFinale=BigDecimal.ZERO;
BigDecimal StockFinaleEmballage=BigDecimal.ZERO;
BigDecimal StockFinaleSansEmballage=BigDecimal.ZERO;
		
//////////////////////////////////////////////////////////////////////////////////////

BigDecimal CA=BigDecimal.ZERO;
/////////////////////////////////////////////////////////////////////////////////////

BigDecimal initial=BigDecimal.ZERO;
BigDecimal achat=BigDecimal.ZERO;
BigDecimal service=BigDecimal.ZERO;
BigDecimal vente=BigDecimal.ZERO;
BigDecimal gratuite=BigDecimal.ZERO;
BigDecimal avoir=BigDecimal.ZERO;
BigDecimal avoirClientPF=BigDecimal.ZERO;
BigDecimal transfertMPPF=BigDecimal.ZERO;
BigDecimal finale=BigDecimal.ZERO;
BigDecimal achatrevendu=BigDecimal.ZERO;
BigDecimal marge=BigDecimal.ZERO;
BigDecimal percentage=BigDecimal.ZERO;
		

JLabel labelInitial = new JLabel("");
private JLabel labelTotalInitial;
JLabel labelInitialEmballage = new JLabel("");
JLabel labelInitialSansEmballage = new JLabel("");
  JLabel labelAchatSansEmballage = new JLabel("");
JLabel labelAchatEmballage = new JLabel("");
JLabel labelAchat = new JLabel("");
private JLabel labelTotalAchat;
JLabel labelPrixService = new JLabel("");
private JLabel labelTotalService;
 JLabel labelProductionSortie = new JLabel("");
JLabel labelProductionEntrer = new JLabel("");
JLabel labelCA = new JLabel("");
private JLabel labelTotalCA;
JLabel labelGratuiteSansEmballage = new JLabel("");
JLabel labelGratuiteEmballage = new JLabel("");
JLabel labelGratuite = new JLabel("");
private JLabel labelTotalGratuite;
JLabel labelAvoirSansEmballage = new JLabel("");
JLabel labelAvoirEmballage = new JLabel("");
JLabel labelAvoir = new JLabel("");
private JLabel labelTotalAvoir;
JLabel labelStockFinaleEmballage = new JLabel("");
private JLabel labelTotalTransfert;
JLabel labelStockFinale = new JLabel("");
private JLabel labelTotalFinale;
JLabel labelStockfinaleSansEmballage = new JLabel("");	
JLabel labelmarge = new JLabel("");
JLabel labelpercentage = new JLabel("");
private JLabel labelTotalAchatRevendu;
JLabel labelTotalAvoirClientPF = new JLabel("");

/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterMargeDeStockArticlePF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(Color.RED);

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1511, 1062);
      
	
        try{ 
        	
        	
        	 imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	mouvementStockGlobaleDAO=new MouvementStockGlobalDAOImpl();
         	ArticleDAO=new ArticlesDAOImpl();
         	familleArticlesDAO=new FamilleArticlesPFDAOImpl();
         	detailTransferStockPFDAO=new DetailTransferProduitFiniDAOImpl();
         	transferStockPFDAO=new TransferStockPFDAOImpl();
         	detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
         	transferStockMPDAO=new TransferStockMPDAOImpl();
        	sousFamilleEnvracDAo = new SousFamilleEnVracDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
         listArticle=ArticleDAO.findAll();
         	listFamilleArticlesF=familleArticlesDAO.findAll();
         	  detailFactureAchatMPdao=new DetailFactureAchatMPDAOImpl();
         	 MatierPremierDAO=new MatierePremierDAOImpl();
         	FamilleOffre=familleArticlesDAO.findByLibelle(FAMILLE_CADEAU);
         	  FamilleDechet=familleArticlesDAO.findByLibelle(FAMILLE_DECHET);
         	  detailTransferProduitFiniDAO=new DetailTransferProduitFiniDAOImpl();
         	 detailfactureAchatdao=new DetailFactureAchatDAOImpl();
         	 detailFactureServiceProductionDAO=new DetailFactureServiceProductionDAOImpl();
      	   factureServiceProductionDAO=new FactureServiceProductionDAOImpl();
      	 productionDAO=new ProductionDAOImpl();
      	  detailfacturedao=new DetailFacturePFDAOImpl();
      	  detailfactureAvoirdao=new DetailFactureAvoirPFDAOImpl();
      	subCategorieMPDAO=new SubCategorieMPAOImpl();
      	detailfactureAvoirClientPFdao=new DetailFactureAvoirClientPFDAOImpl();
      	 detailFacturePfdao=new DetailFacturePFDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableEtatStock.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
				"ARTICLES PF (sources MP) ","Articles","Famille","Sous famille","Montant Initial","Montant Achat","Montant Service","Montant Vente","Montant Gratuit","Montant Avoir","Montant Transfert MP PF","Montant Finale","Achat Revendu","Marge","%"
       	}
       ));
       tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
       tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(6).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(92);
       tableEtatStock.getColumnModel().getColumn(8).setPreferredWidth(95);
       tableEtatStock.getColumnModel().getColumn(9).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(10).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(11).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(12).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(13).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(14).setPreferredWidth(99);
       
       tableEtatStock.setShowVerticalLines(false);
       tableEtatStock.setSelectionBackground(new Color(51, 204, 255));
       tableEtatStock.setRowHeightEnabled(true);
       tableEtatStock.setBackground(new Color(255, 255, 255));
       tableEtatStock.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableEtatStock.setColumnControlVisible(true);
       tableEtatStock.setForeground(Color.BLACK);
       tableEtatStock.setGridColor(new Color(0, 0, 255));
       tableEtatStock.setAutoCreateRowSorter(true);
       tableEtatStock.setBounds(2, 27, 411, 198);
       tableEtatStock.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableEtatStock);
				  		     	scrollPane.setBounds(10, 195, 1465, 539);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Valeurisation Etat Stock Par Article");
				  		     	titledSeparator.setBounds(10, 154, 1465, 30);
				  		     	add(titledSeparator);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Etat Marge De Stock Par Article");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 1080, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {

	    		listEtatValeurisationStockParSousFamille.clear();
	    		
	/////////////////////////////////////////////////////////////////////////////////////// Etat MP    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    		
	    		listEtatStockMP.clear();
	    		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    		
	    	  	boolean existe=false;
		    		Magasin magasinMP=mapMagasin.get(comboMagasinMP.getSelectedItem());
		    	
    ConsulterEtatStockMP();
    
    
		
for(int i=0;i<listEtatStockMP.size();i++)
{
	
	EtatStockMP moyenStockMP=listEtatStockMP.get(i);
	
	
	EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=new EtatValeurisationStockParSousFamille();
	
	etatValeurisationStockParSousFamille.setMatierePremiere(moyenStockMP.getMp().getNom());
	etatValeurisationStockParSousFamille.setCodeArticle(moyenStockMP.getMp().getCode());
	etatValeurisationStockParSousFamille.setType(MAGASIN_CODE_TYPE_MP);
	etatValeurisationStockParSousFamille.setFamille(moyenStockMP.getFamille());
	etatValeurisationStockParSousFamille.setSousFamille(moyenStockMP.getSousFamille());
	
	etatValeurisationStockParSousFamille.setQuantiteInitial(moyenStockMP.getQuantiteInitial());
	etatValeurisationStockParSousFamille.setPrixInitial(moyenStockMP.getPrixInitial());
	etatValeurisationStockParSousFamille.setMontantInitial(moyenStockMP.getMontantInitial());
	
	etatValeurisationStockParSousFamille.setQuantiteAchat(moyenStockMP.getQuantiteAchat());
	etatValeurisationStockParSousFamille.setPrixAchat(moyenStockMP.getPrixAchat());
	etatValeurisationStockParSousFamille.setMontantAchat(moyenStockMP.getMontantAchat());
	
	etatValeurisationStockParSousFamille.setQuantiteSortie(moyenStockMP.getQuantiteSortie());
	etatValeurisationStockParSousFamille.setPrixSortie(moyenStockMP.getPrixSortie());
	etatValeurisationStockParSousFamille.setMontantSortie(moyenStockMP.getMontantSortie());
	
	etatValeurisationStockParSousFamille.setQuantiteDechet(moyenStockMP.getQuantiteDechet());
	etatValeurisationStockParSousFamille.setPrixDechet(moyenStockMP.getPrixDechet());
	etatValeurisationStockParSousFamille.setMontantDechet(moyenStockMP.getMontantDechet());
	
	etatValeurisationStockParSousFamille.setQuantiteOffreProduction(moyenStockMP.getQuantiteOffreProduction());
	etatValeurisationStockParSousFamille.setPrixOffreProduction(moyenStockMP.getPrixOffreProduction());
	etatValeurisationStockParSousFamille.setMontantOffreProduction(moyenStockMP.getMontantOffreProduction());
	
	etatValeurisationStockParSousFamille.setQuantiteService(moyenStockMP.getQuantiteService());
	etatValeurisationStockParSousFamille.setPrixService(moyenStockMP.getPrixService());
	etatValeurisationStockParSousFamille.setMontantService(moyenStockMP.getMontantService());
	
	etatValeurisationStockParSousFamille.setQuantiteDechetService(moyenStockMP.getQuantiteDechetService());
	etatValeurisationStockParSousFamille.setPrixDechetService(moyenStockMP.getPrixDechetService());
	etatValeurisationStockParSousFamille.setMontantDechetService(moyenStockMP.getMontantDechetService());
	
	etatValeurisationStockParSousFamille.setQuantiteOffreService(moyenStockMP.getQuantiteOffreService());
	etatValeurisationStockParSousFamille.setPrixOffreService(moyenStockMP.getPrixOffreService());
	etatValeurisationStockParSousFamille.setMontantOffreService(moyenStockMP.getMontantOffreService());
	
	etatValeurisationStockParSousFamille.setQuantitevente(BigDecimal.ZERO);
	etatValeurisationStockParSousFamille.setPrixvente(BigDecimal.ZERO);
	etatValeurisationStockParSousFamille.setMontantvente(BigDecimal.ZERO);
	
	etatValeurisationStockParSousFamille.setQuantiteGratuit(BigDecimal.ZERO);
	etatValeurisationStockParSousFamille.setPrixGratuit(BigDecimal.ZERO);
	etatValeurisationStockParSousFamille.setMontantGratuit(BigDecimal.ZERO);
	
	etatValeurisationStockParSousFamille.setQuantiteAvoir(moyenStockMP.getQuantiteAvoir());
	etatValeurisationStockParSousFamille.setPrixAvoir(moyenStockMP.getPrixAvoir());
	etatValeurisationStockParSousFamille.setMontantAvoir(moyenStockMP.getMontantAvoir());
	
	etatValeurisationStockParSousFamille.setQuantiteAvoirClientPF(BigDecimal.ZERO);
	etatValeurisationStockParSousFamille.setPrixAvoirClientPF(BigDecimal.ZERO);
	etatValeurisationStockParSousFamille.setMontantAvoirClientPF(BigDecimal.ZERO);
	
	etatValeurisationStockParSousFamille.setQuantiteTransfertMPPF(moyenStockMP.getQuantiteTransfertMPPF());
	etatValeurisationStockParSousFamille.setPrixTransfertMPPF(moyenStockMP.getPrixTransfertMPPF());
	etatValeurisationStockParSousFamille.setMontantTransfertMPPF(moyenStockMP.getMontantTransfertMPPF());
	
	etatValeurisationStockParSousFamille.setQuantiteFinale(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()).subtract(etatValeurisationStockParSousFamille.getQuantiteSortie().add(etatValeurisationStockParSousFamille.getQuantiteDechet().add(etatValeurisationStockParSousFamille.getQuantiteOffreProduction().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatValeurisationStockParSousFamille.getQuantiteOffreService().add(etatValeurisationStockParSousFamille.getQuantiteGratuit().add(etatValeurisationStockParSousFamille.getQuantiteAvoir().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()))))))))));
	
	if(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF().compareTo(BigDecimal.ZERO)!=0)
		{
			listEtatValeurisationStockParSousFamilleMPTransformerEnPF.add(etatValeurisationStockParSousFamille);
		}
	
	
	
	if(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()).compareTo(BigDecimal.ZERO)!=0)
	{
		
		etatValeurisationStockParSousFamille.setPrixFinale((etatValeurisationStockParSousFamille.getMontantInitial().add(etatValeurisationStockParSousFamille.getMontantAchat())).divide(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()), 6, RoundingMode.HALF_UP));
		
	}else
	{
		etatValeurisationStockParSousFamille.setPrixFinale(BigDecimal.ZERO);
	}
	
	
	etatValeurisationStockParSousFamille.setMontantFinale(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteFinale()));
	
	listEtatValeurisationStockParSousFamille.add(etatValeurisationStockParSousFamille);
}  
 

CalculerPrixMoyenInitialMP();	

for(int i=0;i<listEtatInitialMP.size();i++)	
	{
		
		EtatInitialParSousCtaegorieMP etatInitialParSousCtaegorieMP=listEtatInitialMP.get(i);
		
	for(int j=0;j<listEtatValeurisationStockParSousFamille.size();j++)	
	{
		
		EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=listEtatValeurisationStockParSousFamille.get(j);
		
		if(etatInitialParSousCtaegorieMP.getSousFamilleArticlePF().equals(etatValeurisationStockParSousFamille.getSousFamille()))
		{
			if(etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
			{
				
				etatValeurisationStockParSousFamille.setPrixInitial(etatInitialParSousCtaegorieMP.getPrixMoyen());
				
				etatValeurisationStockParSousFamille.setMontantInitial(etatInitialParSousCtaegorieMP.getPrixMoyen().multiply(etatValeurisationStockParSousFamille.getQuantiteInitial()));
				
				
				etatValeurisationStockParSousFamille.setQuantiteFinale(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()).subtract(etatValeurisationStockParSousFamille.getQuantiteSortie().add(etatValeurisationStockParSousFamille.getQuantiteDechet().add(etatValeurisationStockParSousFamille.getQuantiteOffreProduction().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatValeurisationStockParSousFamille.getQuantiteOffreService().add(etatValeurisationStockParSousFamille.getQuantiteGratuit().add(etatValeurisationStockParSousFamille.getQuantiteAvoir().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()))))))))));
		  		if(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()).compareTo(BigDecimal.ZERO)!=0)
		  		{
		  			
		  			etatValeurisationStockParSousFamille.setPrixFinale((etatValeurisationStockParSousFamille.getMontantInitial().add(etatValeurisationStockParSousFamille.getMontantAchat())).divide(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()), 6, RoundingMode.HALF_UP));
		  			
		  		}else
		  		{
		  			etatValeurisationStockParSousFamille.setPrixFinale(BigDecimal.ZERO);
		  		}
		  		
		  		
		  		etatValeurisationStockParSousFamille.setMontantFinale(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteFinale()));
		
			
			
		  		listEtatValeurisationStockParSousFamille.set(j, etatValeurisationStockParSousFamille);
				
				
			}
			
			

			
		
		
		
		}
		
		
		
	}
		
		
		
		
		
	}

CalculerPrixMoyenAchatMP();

for(int i=0;i<listeObjectPrixMoyenAchat.size();i++)	
	{
		
		 Object[] object=listeObjectPrixMoyenAchat.get(i);
	 
	 String famille="";
	 String sousFamille="";

	 MatierePremier matierePremier=MatierPremierDAO.findByCode(object[0].toString());
	 
	 SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(matierePremier);
	 
	 if(sousFamilleEnVrac!=null)
	 {
		 
		famille=sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle(); 
		sousFamille=sousFamilleEnVrac.getSousfamile().getLiblle();
		 
	 }else
	 {
		 
		 famille=matierePremier.getCategorieMp().getSubCategorieMp().getNom(); 
			sousFamille=matierePremier.getCategorieMp().getNom();
		 
		 
	 }
		
	for(int j=0;j<listEtatValeurisationStockParSousFamille.size();j++)	
	{
		
		EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=listEtatValeurisationStockParSousFamille.get(j);
		
		if(sousFamille.equals(etatValeurisationStockParSousFamille.getSousFamille()))
		{
			
			if(etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
			{
				
				etatValeurisationStockParSousFamille.setPrixAchat (new BigDecimal(object[5].toString()));
				
				etatValeurisationStockParSousFamille.setMontantAchat(new BigDecimal(object[5].toString()).multiply(etatValeurisationStockParSousFamille.getQuantiteAchat()));
				
					
				etatValeurisationStockParSousFamille.setQuantiteFinale(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()).subtract(etatValeurisationStockParSousFamille.getQuantiteSortie().add(etatValeurisationStockParSousFamille.getQuantiteDechet().add(etatValeurisationStockParSousFamille.getQuantiteOffreProduction().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatValeurisationStockParSousFamille.getQuantiteOffreService().add(etatValeurisationStockParSousFamille.getQuantiteGratuit().add(etatValeurisationStockParSousFamille.getQuantiteAvoir().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()))))))))));
		  		if(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()).compareTo(BigDecimal.ZERO)!=0)
		  		{
		  			
		  			etatValeurisationStockParSousFamille.setPrixFinale((etatValeurisationStockParSousFamille.getMontantInitial().add(etatValeurisationStockParSousFamille.getMontantAchat())).divide(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()), 6, RoundingMode.HALF_UP));
		  			
		  		}else
		  		{
		  			etatValeurisationStockParSousFamille.setPrixFinale(BigDecimal.ZERO);
		  		}
		  		
		  		
		  		etatValeurisationStockParSousFamille.setMontantFinale(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteFinale()));
		
			
			
		  		listEtatValeurisationStockParSousFamille.set(j, etatValeurisationStockParSousFamille);
				
			}
		
			
		}
		
		
		
	}
		
		
		
		
		
	}


//////////////////////////////////////////////////////////////////////////// set Prix Finale dans les autres champs sauf prix achat et initial  /////////////////////////////////////////////////////////////////////////////////////////////////

    
for(int j=0;j<listEtatValeurisationStockParSousFamille.size();j++)	
{
	
	EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=listEtatValeurisationStockParSousFamille.get(j);
	

		
		
		etatValeurisationStockParSousFamille.setPrixAvoir(etatValeurisationStockParSousFamille.getPrixFinale());
		etatValeurisationStockParSousFamille.setMontantAvoir(etatValeurisationStockParSousFamille.getQuantiteAvoir().multiply(etatValeurisationStockParSousFamille.getPrixFinale()));
		etatValeurisationStockParSousFamille.setPrixDechet(etatValeurisationStockParSousFamille.getPrixFinale());
		etatValeurisationStockParSousFamille.setMontantDechet(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteDechet()));
		etatValeurisationStockParSousFamille.setPrixDechetService(etatValeurisationStockParSousFamille.getPrixFinale());
		etatValeurisationStockParSousFamille.setMontantDechetService(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteDechetService()));
		etatValeurisationStockParSousFamille.setPrixGratuit(etatValeurisationStockParSousFamille.getPrixFinale());
		etatValeurisationStockParSousFamille.setMontantGratuit(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteGratuit()));
		etatValeurisationStockParSousFamille.setPrixOffreProduction(etatValeurisationStockParSousFamille.getPrixFinale());
		etatValeurisationStockParSousFamille.setMontantOffreProduction(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteOffreProduction()));
		etatValeurisationStockParSousFamille.setPrixOffreService(etatValeurisationStockParSousFamille.getPrixFinale());
		etatValeurisationStockParSousFamille.setMontantOffreService(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteOffreService()));
		etatValeurisationStockParSousFamille.setPrixService(etatValeurisationStockParSousFamille.getPrixFinale());
		etatValeurisationStockParSousFamille.setMontantService(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteService()));
		etatValeurisationStockParSousFamille.setPrixSortie(etatValeurisationStockParSousFamille.getPrixFinale());
		etatValeurisationStockParSousFamille.setMontantSortie(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteSortie()));
		etatValeurisationStockParSousFamille.setPrixTransfertMPPF(etatValeurisationStockParSousFamille.getPrixFinale());
		etatValeurisationStockParSousFamille.setMontantTransfertMPPF(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()));
		etatValeurisationStockParSousFamille.setPrixvente(etatValeurisationStockParSousFamille.getPrixFinale());
		etatValeurisationStockParSousFamille.setMontantvente(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantitevente()));
		

	
	
  		listEtatValeurisationStockParSousFamille.set(j, etatValeurisationStockParSousFamille);
		
	
	
	
	
}
    
    
/////////////////////////////////////////////////////////////////////////////////////////  Etat Stock PF //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	
   
    
    
	ConsulterEtatStockPF();   
	
	
	for(int i=0;i<listEtatStockPFImprimer.size();i++)
	{
		
		EtatStockPF etatStockPF=listEtatStockPFImprimer.get(i);
		
		
        EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=new EtatValeurisationStockParSousFamille();
  		
        etatValeurisationStockParSousFamille.setFamille(etatStockPF.getFamilleArticle().getLiblle());
  	
  			etatValeurisationStockParSousFamille.setMatierePremiere(etatStockPF.getArticle().getLiblle());
  			etatValeurisationStockParSousFamille.setCodeArticle(etatStockPF.getArticle().getCodeArticle());
  		
  	
  		
  		etatValeurisationStockParSousFamille.setType(MAGASIN_CODE_TYPE_PF);
  		etatValeurisationStockParSousFamille.setSousFamille(etatStockPF.getSousFamille().getLiblle());
  		etatValeurisationStockParSousFamille.setQuantiteInitial(etatStockPF.getQuantiteInitial());
  		etatValeurisationStockParSousFamille.setPrixInitial(etatStockPF.getPrixInitial());
  		etatValeurisationStockParSousFamille.setMontantInitial(etatStockPF.getMontantInitial());
  		etatValeurisationStockParSousFamille.setQuantiteAchat(etatStockPF.getQuantiteAchat());
  		etatValeurisationStockParSousFamille.setPrixAchat(etatStockPF.getPrixAchat());
  		etatValeurisationStockParSousFamille.setMontantAchat(etatStockPF.getMontantAchat());
  		etatValeurisationStockParSousFamille.setQuantiteSortie(BigDecimal.ZERO);
  		etatValeurisationStockParSousFamille.setPrixSortie(BigDecimal.ZERO);
  		etatValeurisationStockParSousFamille.setMontantSortie(BigDecimal.ZERO);
  		
  		
  		if(etatStockPF.getQuantiteEntrer().compareTo(BigDecimal.ZERO)!=0)
  		{
  			
  			if(etatStockPF.getArticle().getCodeArticle().contains(Constantes.CODE_PRODUIT_FINI_OFFRE))
  			{
  				
  				
  				etatValeurisationStockParSousFamille.setQuantiteOffreService(etatStockPF.getQuantiteEntrer());
  		  		etatValeurisationStockParSousFamille.setPrixOffreService(etatStockPF.getPrixEntrer());
  		  		etatValeurisationStockParSousFamille.setMontantOffreService(etatStockPF.getMontantEntrer());
  		  		
  		  	etatValeurisationStockParSousFamille.setQuantiteDechet(BigDecimal.ZERO);
  	  		etatValeurisationStockParSousFamille.setPrixDechet(BigDecimal.ZERO);
  	  		etatValeurisationStockParSousFamille.setMontantDechet(BigDecimal.ZERO);
  	  		
  	  		etatValeurisationStockParSousFamille.setQuantiteOffreProduction(BigDecimal.ZERO);
  	  		etatValeurisationStockParSousFamille.setPrixOffreProduction(BigDecimal.ZERO);
  	  		etatValeurisationStockParSousFamille.setMontantOffreProduction(BigDecimal.ZERO); 	
  	  		
  	  		etatValeurisationStockParSousFamille.setQuantiteDechetService(BigDecimal.ZERO);
  	  		etatValeurisationStockParSousFamille.setPrixDechetService(BigDecimal.ZERO);
  	  		etatValeurisationStockParSousFamille.setMontantDechetService(BigDecimal.ZERO);
  	  		
  	  	etatValeurisationStockParSousFamille.setQuantiteService(BigDecimal.ZERO);
	  		etatValeurisationStockParSousFamille.setPrixService(BigDecimal.ZERO);
	  		etatValeurisationStockParSousFamille.setMontantService(BigDecimal.ZERO);
  				
  				
  			}else if(etatStockPF.getArticle().getCodeArticle().contains(Constantes.CODE_PRODUIT_FINI_DECHET))
  			{
  				
  				etatValeurisationStockParSousFamille.setQuantiteDechet(BigDecimal.ZERO);
  		  		etatValeurisationStockParSousFamille.setPrixDechet(BigDecimal.ZERO);
  		  		etatValeurisationStockParSousFamille.setMontantDechet(BigDecimal.ZERO);
  		  		
  		  		etatValeurisationStockParSousFamille.setQuantiteOffreProduction(BigDecimal.ZERO);
  		  		etatValeurisationStockParSousFamille.setPrixOffreProduction(BigDecimal.ZERO);
  		  		etatValeurisationStockParSousFamille.setMontantOffreProduction(BigDecimal.ZERO); 
  		  		
  		  		etatValeurisationStockParSousFamille.setQuantiteDechetService(etatStockPF.getQuantiteProduction());
  		  		etatValeurisationStockParSousFamille.setPrixDechetService(etatStockPF.getPrixProduction());
  		  		etatValeurisationStockParSousFamille.setMontantDechetService(etatStockPF.getMontantProduction());
  		  		
  		    	etatValeurisationStockParSousFamille.setQuantiteOffreService(BigDecimal.ZERO);
		  		etatValeurisationStockParSousFamille.setPrixOffreService(BigDecimal.ZERO);
		  		etatValeurisationStockParSousFamille.setMontantOffreService(BigDecimal.ZERO);
		  		
		  		
		  		etatValeurisationStockParSousFamille.setQuantiteService(etatStockPF.getQuantiteProduction());
  		  		etatValeurisationStockParSousFamille.setPrixService(etatStockPF.getPrixProduction());
  		  		etatValeurisationStockParSousFamille.setMontantService(etatStockPF.getMontantProduction());
  				
  			}else
  			{
  				

  				
  				etatValeurisationStockParSousFamille.setQuantiteDechet(BigDecimal.ZERO);
  		  		etatValeurisationStockParSousFamille.setPrixDechet(BigDecimal.ZERO);
  		  		etatValeurisationStockParSousFamille.setMontantDechet(BigDecimal.ZERO);
  		  		
  		  		etatValeurisationStockParSousFamille.setQuantiteOffreProduction(BigDecimal.ZERO);
  		  		etatValeurisationStockParSousFamille.setPrixOffreProduction(BigDecimal.ZERO);
  		  		etatValeurisationStockParSousFamille.setMontantOffreProduction(BigDecimal.ZERO);
  		  		
  		  		etatValeurisationStockParSousFamille.setQuantiteService(etatStockPF.getQuantiteProduction());
  		  		etatValeurisationStockParSousFamille.setPrixService(etatStockPF.getPrixProduction());
  		  		etatValeurisationStockParSousFamille.setMontantService(etatStockPF.getMontantProduction());
  		  		
  		  		etatValeurisationStockParSousFamille.setQuantiteDechetService(BigDecimal.ZERO);
  		  		etatValeurisationStockParSousFamille.setPrixDechetService(BigDecimal.ZERO);
  		  		etatValeurisationStockParSousFamille.setMontantDechetService(BigDecimal.ZERO);
  		  		
  		  	    etatValeurisationStockParSousFamille.setQuantiteOffreService(BigDecimal.ZERO);
		  		etatValeurisationStockParSousFamille.setPrixOffreService(BigDecimal.ZERO);
		  		etatValeurisationStockParSousFamille.setMontantOffreService(BigDecimal.ZERO);
  				
  			
  				
  				
  			}
  			
  			
  			
  			
  		}else
  		{
  			
  			

				

				
				etatValeurisationStockParSousFamille.setQuantiteDechet(BigDecimal.ZERO);
		  		etatValeurisationStockParSousFamille.setPrixDechet(BigDecimal.ZERO);
		  		etatValeurisationStockParSousFamille.setMontantDechet(BigDecimal.ZERO);
		  		
		  		etatValeurisationStockParSousFamille.setQuantiteOffreProduction(BigDecimal.ZERO);
		  		etatValeurisationStockParSousFamille.setPrixOffreProduction(BigDecimal.ZERO);
		  		etatValeurisationStockParSousFamille.setMontantOffreProduction(BigDecimal.ZERO);
		  		
		  		etatValeurisationStockParSousFamille.setQuantiteService(etatStockPF.getQuantiteProduction());
		  		etatValeurisationStockParSousFamille.setPrixService(etatStockPF.getPrixProduction());
		  		etatValeurisationStockParSousFamille.setMontantService(etatStockPF.getMontantProduction());
		  		
		  		etatValeurisationStockParSousFamille.setQuantiteDechetService(BigDecimal.ZERO);
		  		etatValeurisationStockParSousFamille.setPrixDechetService(BigDecimal.ZERO);
		  		etatValeurisationStockParSousFamille.setMontantDechetService(BigDecimal.ZERO);
		  		
		     	etatValeurisationStockParSousFamille.setQuantiteOffreService(BigDecimal.ZERO);
	  		    etatValeurisationStockParSousFamille.setPrixOffreService(BigDecimal.ZERO);
	  		    etatValeurisationStockParSousFamille.setMontantOffreService(BigDecimal.ZERO);
				
			
				
				
			
  			
  			
  		}
  		
  		
  		
  		etatValeurisationStockParSousFamille.setQuantiteEntrer(etatStockPF.getQuantiteEntrer());
  		etatValeurisationStockParSousFamille.setPrixEntrer(etatStockPF.getPrixEntrer());
  		etatValeurisationStockParSousFamille.setMontantEntrer(etatValeurisationStockParSousFamille.getQuantiteEntrer().multiply(etatValeurisationStockParSousFamille.getPrixEntrer()));
  		
  		etatValeurisationStockParSousFamille.setQuantitevente(etatStockPF.getQuantiteSortie());
  		etatValeurisationStockParSousFamille.setPrixvente(etatStockPF.getPrixSortie());
  		etatValeurisationStockParSousFamille.setMontantvente(etatStockPF.getMontantSortie());
  		etatValeurisationStockParSousFamille.setQuantiteGratuit(etatStockPF.getQuantiteGratuit());
  		etatValeurisationStockParSousFamille.setPrixGratuit(etatStockPF.getPrixGratuit());
  		etatValeurisationStockParSousFamille.setMontantGratuit(etatStockPF.getMontantGratuit());
  		etatValeurisationStockParSousFamille.setQuantiteAvoir(etatStockPF.getQuantiteAvoir());
  		etatValeurisationStockParSousFamille.setPrixAvoir(etatStockPF.getPrixAvoir());
  		etatValeurisationStockParSousFamille.setMontantAvoir(etatStockPF.getMontantAvoir());
  		etatValeurisationStockParSousFamille.setQuantiteAvoirClientPF(etatStockPF.getQuantiteAvoirClient());
  		etatValeurisationStockParSousFamille.setPrixAvoirClientPF(etatStockPF.getPrixAvoirClient());
  		etatValeurisationStockParSousFamille.setMontantAvoirClientPF(etatStockPF.getMontantAvoirClient());
  		etatValeurisationStockParSousFamille.setQuantiteTransfertMPPF(BigDecimal.ZERO);
  		etatValeurisationStockParSousFamille.setPrixTransfertMPPF(BigDecimal.ZERO);
  		etatValeurisationStockParSousFamille.setMontantTransfertMPPF(BigDecimal.ZERO);
  		etatValeurisationStockParSousFamille.setQuantiteFinale(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()).add(etatValeurisationStockParSousFamille.getQuantiteAvoirClientPF()))).subtract(etatValeurisationStockParSousFamille.getQuantiteSortie().add(etatValeurisationStockParSousFamille.getQuantiteDechet().add(etatValeurisationStockParSousFamille.getQuantiteOffreProduction().add(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatValeurisationStockParSousFamille.getQuantiteOffreService().add(etatValeurisationStockParSousFamille.getQuantitevente()).add(etatValeurisationStockParSousFamille.getQuantiteGratuit().add(etatValeurisationStockParSousFamille.getQuantiteAvoir()))))))));
  		if(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()).add(etatValeurisationStockParSousFamille.getQuantiteAvoirClientPF()))).compareTo(BigDecimal.ZERO)!=0)
  		{
  			
  			etatValeurisationStockParSousFamille.setPrixFinale((etatValeurisationStockParSousFamille.getMontantInitial().add(etatValeurisationStockParSousFamille.getMontantAchat().add(etatValeurisationStockParSousFamille.getMontantService().add(etatValeurisationStockParSousFamille.getMontantTransfertMPPF()).add(etatValeurisationStockParSousFamille.getMontantAvoirClientPF())))).divide(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()).add(etatValeurisationStockParSousFamille.getQuantiteAvoirClientPF()))), 6, RoundingMode.HALF_UP));
  			
  		}else
  		{
  			etatValeurisationStockParSousFamille.setPrixFinale(BigDecimal.ZERO);
  		}
  		
  		
  		etatValeurisationStockParSousFamille.setMontantFinale(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteFinale()));
  		
  		listEtatValeurisationStockParSousFamille.add(etatValeurisationStockParSousFamille);

		
	}
	
	
	
	
	    	
	ConsulterEtatPrixMoyenStockInitialPF();
	
	
	for(int i=0;i<listEtatVente.size();i++)
	{
		
		EtatVenteParFamilleArticle etatVenteParFamilleArticle=listEtatVente.get(i);
	
		
		
		
		for(int j=0;j<listEtatValeurisationStockParSousFamille.size();j++)	
		{
			
			EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=listEtatValeurisationStockParSousFamille.get(j);
			if(etatValeurisationStockParSousFamille.getType().equals(MAGASIN_CODE_TYPE_PF))
			{
				if(etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
				{
					
				if(etatVenteParFamilleArticle.getFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getFamille()))	
				{
					if(etatVenteParFamilleArticle.getSousFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getSousFamille()))	
					{
						
						etatValeurisationStockParSousFamille.setPrixInitial(etatVenteParFamilleArticle.getPrixMoyen());
						etatValeurisationStockParSousFamille.setMontantInitial(etatValeurisationStockParSousFamille.getQuantiteInitial().multiply(etatVenteParFamilleArticle.getPrixMoyen()));
						
					  		if(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()))).compareTo(BigDecimal.ZERO)!=0)
			{
				
				etatValeurisationStockParSousFamille.setPrixFinale((etatValeurisationStockParSousFamille.getMontantInitial().add(etatValeurisationStockParSousFamille.getMontantAchat().add(etatValeurisationStockParSousFamille.getMontantService().add(etatValeurisationStockParSousFamille.getMontantTransfertMPPF())))).divide(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()))), 6, RoundingMode.HALF_UP));
				
			}else
			{
				etatValeurisationStockParSousFamille.setPrixFinale(BigDecimal.ZERO);
			}
			
			
			etatValeurisationStockParSousFamille.setMontantFinale(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteFinale()));
			
			listEtatValeurisationStockParSousFamille. set (j,etatValeurisationStockParSousFamille);

						
					}
					
					
					
		
				
				}
					
					
					
					
					
					
					
					
				}
				
					
				
			}
		  		

			
			
		}
  		
   

		
	}
	
	
	EtatPrixMoyenInitialParArticle();
	
	for(int i=0;i<listEtatPrixMoyen.size();i++)
	{
		
		EtatPrixMoyen etatVenteParFamilleArticle=listEtatPrixMoyen.get(i);
	
		
		
		
		for(int j=0;j<listEtatValeurisationStockParSousFamille.size();j++)	
		{
			
			EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=listEtatValeurisationStockParSousFamille.get(j);
			if(etatValeurisationStockParSousFamille.getType().equals(MAGASIN_CODE_TYPE_PF))
			{
				if(!etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_CHAARA) && !etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) && !etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
				{
					
				
					if(etatVenteParFamilleArticle.getCodeArticle().equals(etatValeurisationStockParSousFamille.getCodeArticle()))	
					{
						if(etatVenteParFamilleArticle.getSousfamille().equals(etatValeurisationStockParSousFamille.getSousFamille()))	
						{
							etatValeurisationStockParSousFamille.setPrixInitial(etatVenteParFamilleArticle.getPrix());
							etatValeurisationStockParSousFamille.setMontantInitial(etatValeurisationStockParSousFamille.getQuantiteInitial().multiply(etatValeurisationStockParSousFamille.getPrixInitial()));
							
						  		if(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()))).compareTo(BigDecimal.ZERO)!=0)
				{
					
					etatValeurisationStockParSousFamille.setPrixFinale((etatValeurisationStockParSousFamille.getMontantInitial().add(etatValeurisationStockParSousFamille.getMontantAchat().add(etatValeurisationStockParSousFamille.getMontantService().add(etatValeurisationStockParSousFamille.getMontantTransfertMPPF())))).divide(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()))), 6, RoundingMode.HALF_UP));
					
				}else
				{
					etatValeurisationStockParSousFamille.setPrixFinale(BigDecimal.ZERO);
				}
				
				
				etatValeurisationStockParSousFamille.setMontantFinale(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteFinale()));
				
				listEtatValeurisationStockParSousFamille. set (j,etatValeurisationStockParSousFamille);

							
						}
		
						
					}
					
					
					
		
				
				
					
					
					
					
					
					
					
					
				}
				
					
				
			}
		  		

			
			
		}
  		
   

		
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////// Prix Moyen Achat PF //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	EtatPrixMoyenAchatPF();	
	

for(int i=0;i<listEtatVente.size();i++)	
{
	
	EtatVenteParFamilleArticle etatAchatParFamilleArticle=listEtatVente.get(i);
	
	
	
for(int j=0;j<listEtatValeurisationStockParSousFamille.size();j++)	
{
	
	  EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille	=listEtatValeurisationStockParSousFamille.get(j);
	
	
		  
	if(etatValeurisationStockParSousFamille.getType().equals(MAGASIN_CODE_TYPE_PF))
	{
		
		if(etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
		{
			
			
			  
			  if(etatAchatParFamilleArticle.getFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getFamille()))
			  {
				  if(etatAchatParFamilleArticle.getSousFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getSousFamille()))
				  {
				         etatValeurisationStockParSousFamille.setPrixAchat(etatAchatParFamilleArticle.getPrixMoyen());

							
						  
						  etatValeurisationStockParSousFamille.setMontantAchat(etatValeurisationStockParSousFamille.getQuantiteAchat().multiply(etatValeurisationStockParSousFamille.getPrixAchat()));
						  				
					  		if(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()))).compareTo(BigDecimal.ZERO)!=0)
				{
					
					etatValeurisationStockParSousFamille.setPrixFinale((etatValeurisationStockParSousFamille.getMontantInitial().add(etatValeurisationStockParSousFamille.getMontantAchat().add(etatValeurisationStockParSousFamille.getMontantService().add(etatValeurisationStockParSousFamille.getMontantTransfertMPPF())))).divide(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()))), 6, RoundingMode.HALF_UP));
					
				}else
				{
					etatValeurisationStockParSousFamille.setPrixFinale(BigDecimal.ZERO);
					
					
				}
							etatValeurisationStockParSousFamille.setMontantFinale(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteFinale()));
							
							listEtatValeurisationStockParSousFamille. set (j,etatValeurisationStockParSousFamille);
		 
						  
				  }

					  
          
				  
			  }
			
			
			
			
			
		}
		
		
		
		
	}
		  
}
}
	

EtatPrixMoyenAchatParArticle();

for(int i=0;i<listeObject.size();i++)	
{

	Object[] etatAchatParFamilleArticle=listeObject.get(i);



for(int j=0;j<listEtatValeurisationStockParSousFamille.size();j++)	
{

  EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille	=listEtatValeurisationStockParSousFamille.get(j);


	  
if(etatValeurisationStockParSousFamille.getType().equals(MAGASIN_CODE_TYPE_PF))
{
	
	if(!etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_CHAARA) && !etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) && !etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
	{
		
		
		  
		  if(etatAchatParFamilleArticle[1].equals(etatValeurisationStockParSousFamille.getFamille()))
		  {
			  if(etatAchatParFamilleArticle[2].equals(etatValeurisationStockParSousFamille.getSousFamille()))
			  {
				  if(etatAchatParFamilleArticle[0].equals(etatValeurisationStockParSousFamille.getMatierePremiere()))
				  {
				       etatValeurisationStockParSousFamille.setPrixAchat(new BigDecimal(etatAchatParFamilleArticle[4].toString()));

						
						  
						  etatValeurisationStockParSousFamille.setMontantAchat(etatValeurisationStockParSousFamille.getQuantiteAchat().multiply(etatValeurisationStockParSousFamille.getPrixAchat()));
						  				
					  		if(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()))).compareTo(BigDecimal.ZERO)!=0)
				{
					
					etatValeurisationStockParSousFamille.setPrixFinale((etatValeurisationStockParSousFamille.getMontantInitial().add(etatValeurisationStockParSousFamille.getMontantAchat().add(etatValeurisationStockParSousFamille.getMontantService().add(etatValeurisationStockParSousFamille.getMontantTransfertMPPF())))).divide(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()))), 6, RoundingMode.HALF_UP));
					
				}else
				{
					etatValeurisationStockParSousFamille.setPrixFinale(BigDecimal.ZERO);
					
					
				}
							etatValeurisationStockParSousFamille.setMontantFinale(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteFinale()));
							
							listEtatValeurisationStockParSousFamille. set (j,etatValeurisationStockParSousFamille);
		 
					  
				  }
			  
					  
			  }

				  
      
			  
		  }
		
		
		
		
		
	}
	
	
	
	
}
	  
}
}

	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////Service  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    		

EtatMoyenValeurisationProductionparSousFamille();

for(int d=0;d<listEtatPrixMoyenValeurisationProduction.size();d++)
{

EtatPrixMoyenMP etatPrixMoyenMP=listEtatPrixMoyenValeurisationProduction.get(d);




for(int f=0;f<listEtatValeurisationStockParSousFamille.size();f++)	
{

EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamilleTmp	=listEtatValeurisationStockParSousFamille.get(f);


if(etatValeurisationStockParSousFamilleTmp.getType().equals(MAGASIN_CODE_TYPE_PF))
{
	
	if(etatValeurisationStockParSousFamilleTmp.getFamille().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || etatValeurisationStockParSousFamilleTmp.getFamille().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || etatValeurisationStockParSousFamilleTmp.getFamille().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
	{
		
		
		  
		  if(etatPrixMoyenMP.getFamille().equals(etatValeurisationStockParSousFamilleTmp.getFamille()))
		  {
			  
			  if(etatPrixMoyenMP.getSousfamille().equals(etatValeurisationStockParSousFamilleTmp.getSousFamille()))
			  {
				  
				  etatValeurisationStockParSousFamilleTmp.setPrixService(etatPrixMoyenMP.getPrix()); ;

					
				  
				  etatValeurisationStockParSousFamilleTmp.setMontantService (etatValeurisationStockParSousFamilleTmp.getQuantiteService().multiply(etatValeurisationStockParSousFamilleTmp.getPrixService()));
				  				
			  		if(etatValeurisationStockParSousFamilleTmp.getQuantiteInitial().add(etatValeurisationStockParSousFamilleTmp.getQuantiteAchat().add(etatValeurisationStockParSousFamilleTmp.getQuantiteService().add(etatValeurisationStockParSousFamilleTmp.getQuantiteTransfertMPPF()))).compareTo(BigDecimal.ZERO)!=0)
		{
			
			  			etatValeurisationStockParSousFamilleTmp.setPrixFinale((etatValeurisationStockParSousFamilleTmp.getMontantInitial().add(etatValeurisationStockParSousFamilleTmp.getMontantAchat().add(etatValeurisationStockParSousFamilleTmp.getMontantService().add(etatValeurisationStockParSousFamilleTmp.getMontantTransfertMPPF())))).divide(etatValeurisationStockParSousFamilleTmp.getQuantiteInitial().add(etatValeurisationStockParSousFamilleTmp.getQuantiteAchat().add(etatValeurisationStockParSousFamilleTmp.getQuantiteService().add(etatValeurisationStockParSousFamilleTmp.getQuantiteTransfertMPPF()))), 6, RoundingMode.HALF_UP));
			
		}else
		{
			etatValeurisationStockParSousFamilleTmp.setPrixFinale(BigDecimal.ZERO);
			
			
		}
			  		etatValeurisationStockParSousFamilleTmp.setMontantFinale(etatValeurisationStockParSousFamilleTmp.getPrixFinale().multiply(etatValeurisationStockParSousFamilleTmp.getQuantiteFinale()));
					
					listEtatValeurisationStockParSousFamille. set (f,etatValeurisationStockParSousFamilleTmp);

			  }
				  
	
			  
			  
		  }
		
		
		
		
		
	}
	
	
	
	
} 

}




}

EtatPixMoyenserviceParArticle();

for(int d=0;d<listEtatPrixMoyenAfficher.size();d++)
{

	EtatPrixMoyen etatPrixMoyenMP=listEtatPrixMoyenAfficher.get(d);





for(int f=0;f<listEtatValeurisationStockParSousFamille.size();f++)	
{

EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamilleTmp	=listEtatValeurisationStockParSousFamille.get(f);


if(etatValeurisationStockParSousFamilleTmp.getType().equals(MAGASIN_CODE_TYPE_PF))
{
	
	if(!etatValeurisationStockParSousFamilleTmp.getFamille().equals(Constantes.LIBELLE_FAMILLE_CHAARA) && !etatValeurisationStockParSousFamilleTmp.getFamille().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) && !etatValeurisationStockParSousFamilleTmp.getFamille().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
	{
		
		
		  
		  if(etatPrixMoyenMP.getFamille().equals(etatValeurisationStockParSousFamilleTmp.getFamille()))
		  {
			  
			  if(etatPrixMoyenMP.getSousfamille().equals(etatValeurisationStockParSousFamilleTmp.getSousFamille()))
			  {
				  if(etatPrixMoyenMP.getCodeArticle().equals(etatValeurisationStockParSousFamilleTmp.getCodeArticle()))
				  {
					  
					  if(etatValeurisationStockParSousFamilleTmp.getQuantiteOffreService().compareTo(BigDecimal.ZERO)!=0)
					  {

						  
						  etatValeurisationStockParSousFamilleTmp.setPrixOffreService(etatPrixMoyenMP.getPrix()); ;

							
						  
						  etatValeurisationStockParSousFamilleTmp.setMontantOffreService (etatValeurisationStockParSousFamilleTmp.getQuantiteOffreService().multiply(etatValeurisationStockParSousFamilleTmp.getPrixOffreService()));
						  				
					  		if(etatValeurisationStockParSousFamilleTmp.getQuantiteInitial().add(etatValeurisationStockParSousFamilleTmp.getQuantiteAchat().add(etatValeurisationStockParSousFamilleTmp.getQuantiteService().add(etatValeurisationStockParSousFamilleTmp.getQuantiteTransfertMPPF()))).compareTo(BigDecimal.ZERO)!=0)
				{
					
					  			etatValeurisationStockParSousFamilleTmp.setPrixFinale((etatValeurisationStockParSousFamilleTmp.getMontantInitial().add(etatValeurisationStockParSousFamilleTmp.getMontantAchat().add(etatValeurisationStockParSousFamilleTmp.getMontantService().add(etatValeurisationStockParSousFamilleTmp.getMontantTransfertMPPF())))).divide(etatValeurisationStockParSousFamilleTmp.getQuantiteInitial().add(etatValeurisationStockParSousFamilleTmp.getQuantiteAchat().add(etatValeurisationStockParSousFamilleTmp.getQuantiteService().add(etatValeurisationStockParSousFamilleTmp.getQuantiteTransfertMPPF()))), 6, RoundingMode.HALF_UP));
					
				}else
				{
					etatValeurisationStockParSousFamilleTmp.setPrixFinale(BigDecimal.ZERO);
					
					
				}
					  		etatValeurisationStockParSousFamilleTmp.setMontantFinale(etatValeurisationStockParSousFamilleTmp.getPrixFinale().multiply(etatValeurisationStockParSousFamilleTmp.getQuantiteFinale()));
							
							listEtatValeurisationStockParSousFamille. set (f,etatValeurisationStockParSousFamilleTmp);

						   
						  
					  }
					  
				
					  
				  }
				  
		
			  }
				  
	
			  
			  
		  }
		
		
		
		
		
	}
	
	
	
	
} 

}




}



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  Vente  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	

CalculerPrixMoyenVenteParArticle();

for(int d=0;d<listEtatPrixMoyenAfficher.size();d++)
{

	EtatPrixMoyen etatPrixMoyenMP=listEtatPrixMoyenAfficher.get(d);


for(int f=0;f<listEtatValeurisationStockParSousFamille.size();f++)	
{

EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamilleTmp	=listEtatValeurisationStockParSousFamille.get(f);

  
		  if(etatPrixMoyenMP.getFamille().equals(etatValeurisationStockParSousFamilleTmp.getFamille()))
		  {
			  
			  if(etatPrixMoyenMP.getSousfamille().equals(etatValeurisationStockParSousFamilleTmp.getSousFamille()))
			  {
				  if(etatPrixMoyenMP.getArticle().equals(etatValeurisationStockParSousFamilleTmp.getMatierePremiere()))
				  {
					  
					  if(etatValeurisationStockParSousFamilleTmp.getQuantitevente().compareTo(BigDecimal.ZERO)!=0)
					  {

						  System.out.println( etatPrixMoyenMP.getArticle() +" ***** " + etatPrixMoyenMP.getFamille() +" ***** " + etatPrixMoyenMP.getSousfamille()+" ***** " +etatPrixMoyenMP.getQuantite()+" ***** " +etatPrixMoyenMP.getPrix());

						  etatValeurisationStockParSousFamilleTmp.setPrixvente(etatPrixMoyenMP.getPrix()); ;

							
						  
						  etatValeurisationStockParSousFamilleTmp.setMontantvente(etatValeurisationStockParSousFamilleTmp.getQuantitevente().multiply(etatValeurisationStockParSousFamilleTmp.getPrixvente()));
						  				
					  		if(etatValeurisationStockParSousFamilleTmp.getQuantiteInitial().add(etatValeurisationStockParSousFamilleTmp.getQuantiteAchat().add(etatValeurisationStockParSousFamilleTmp.getQuantiteService().add(etatValeurisationStockParSousFamilleTmp.getQuantiteTransfertMPPF()))).compareTo(BigDecimal.ZERO)!=0)
				{
					
					  			etatValeurisationStockParSousFamilleTmp.setPrixFinale((etatValeurisationStockParSousFamilleTmp.getMontantInitial().add(etatValeurisationStockParSousFamilleTmp.getMontantAchat().add(etatValeurisationStockParSousFamilleTmp.getMontantService().add(etatValeurisationStockParSousFamilleTmp.getMontantTransfertMPPF())))).divide(etatValeurisationStockParSousFamilleTmp.getQuantiteInitial().add(etatValeurisationStockParSousFamilleTmp.getQuantiteAchat().add(etatValeurisationStockParSousFamilleTmp.getQuantiteService().add(etatValeurisationStockParSousFamilleTmp.getQuantiteTransfertMPPF()))), 6, RoundingMode.HALF_UP));
					
				}else
				{
					etatValeurisationStockParSousFamilleTmp.setPrixFinale(BigDecimal.ZERO);
					
					
				}
					  		etatValeurisationStockParSousFamilleTmp.setMontantFinale(etatValeurisationStockParSousFamilleTmp.getPrixFinale().multiply(etatValeurisationStockParSousFamilleTmp.getQuantiteFinale()));
							
							listEtatValeurisationStockParSousFamille. set (f,etatValeurisationStockParSousFamilleTmp);

						   
						  
					  }
					  
					  
				  }
				  
				  
				 
			  }
				  
	
			 
		
	}
	
	

}




}





///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  Transfert MP PF  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	







for(int t=0;t<listEtatValeurisationStockParSousFamilleMPTransformerEnPF.size();t++)
{


EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=listEtatValeurisationStockParSousFamilleMPTransformerEnPF.get(t);
MatierePremier matierePremier=MatierPremierDAO.findByCode(etatValeurisationStockParSousFamille.getCodeArticle());

DetailTransferStockMP detailTransferStockMP=detailTransferStockMPDAO.findMPByTypeTransferStockMPByMagasinBayDate(ETAT_TRANSFER_STOCK_SORTIE_MP_PF, matierePremier, magasinMP, dateChooserdebut.getDate(), dateChooserfin.getDate());

if(detailTransferStockMP!=null)
{
	TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(detailTransferStockMP.getTransferStockMP().getCodeTransfer());
	if(transferStockPF!=null)
	{
		
		List<DetailTransferProduitFini> listdetailTransferPF=detailTransferStockPFDAO.findByTransferStockPF(transferStockPF.getId()) ;
		
		
		for(int d=0;d<listEtatValeurisationStockParSousFamille.size();d++)
		{

		EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamilleTmp=listEtatValeurisationStockParSousFamille.get(d);

if(listdetailTransferPF.size()!=0)
{
	
	if(etatValeurisationStockParSousFamilleTmp.getCodeArticle().equals(listdetailTransferPF.get(0).getArticle().getCodeArticle()) && etatValeurisationStockParSousFamilleTmp.getSousFamille().equals(listdetailTransferPF.get(0).getSousFamille().getLiblle()))
	{
		
		etatValeurisationStockParSousFamilleTmp.setArticle(etatValeurisationStockParSousFamille.getMatierePremiere());	
		etatValeurisationStockParSousFamilleTmp.setQuantiteTransfertMPPF(etatValeurisationStockParSousFamilleTmp.getQuantiteEntrer());
		etatValeurisationStockParSousFamilleTmp.setPrixTransfertMPPF(etatValeurisationStockParSousFamilleTmp.getPrixEntrer());
		etatValeurisationStockParSousFamilleTmp.setMontantTransfertMPPF(etatValeurisationStockParSousFamilleTmp.getMontantEntrer());
		
		etatValeurisationStockParSousFamilleTmp.setQuantiteEntrer(BigDecimal.ZERO);
		etatValeurisationStockParSousFamilleTmp.setPrixEntrer(BigDecimal.ZERO);
		etatValeurisationStockParSousFamilleTmp.setMontantEntrer(BigDecimal.ZERO);
		listEtatValeurisationStockParSousFamille. set (d,etatValeurisationStockParSousFamilleTmp);
		
	}
	
	
	
}




		}
		
		
		
		
	}

	
	
	
}











}






/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   Calculer Prix Finale   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	


for(int r=0;r<listEtatValeurisationStockParSousFamille.size();r++)
{

EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=listEtatValeurisationStockParSousFamille.get(r);


if(etatValeurisationStockParSousFamille.getType().equals(Constantes.MAGASIN_CODE_TYPE_PF)) 
{

etatValeurisationStockParSousFamille.setQuantiteFinale(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF().add(etatValeurisationStockParSousFamille.getQuantiteSortie().add(etatValeurisationStockParSousFamille.getQuantiteDechet().add(etatValeurisationStockParSousFamille.getQuantiteOffreProduction().add(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatValeurisationStockParSousFamille.getQuantiteOffreService()).add(etatValeurisationStockParSousFamille.getQuantiteAvoirClientPF())))).subtract((etatValeurisationStockParSousFamille.getQuantitevente()).add(etatValeurisationStockParSousFamille.getQuantiteGratuit().add(etatValeurisationStockParSousFamille.getQuantiteAvoir()))))))));


if((etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF().add(etatValeurisationStockParSousFamille.getQuantiteSortie().add(etatValeurisationStockParSousFamille.getQuantiteDechet().add(etatValeurisationStockParSousFamille.getQuantiteOffreProduction().add(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatValeurisationStockParSousFamille.getQuantiteOffreService()).add(etatValeurisationStockParSousFamille.getQuantiteAvoirClientPF()))))))))).compareTo(BigDecimal.ZERO)!=0)
{

etatValeurisationStockParSousFamille.setPrixFinale((etatValeurisationStockParSousFamille.getMontantInitial().add(etatValeurisationStockParSousFamille.getMontantAchat().add(etatValeurisationStockParSousFamille.getMontantService().add(etatValeurisationStockParSousFamille.getMontantTransfertMPPF().add(etatValeurisationStockParSousFamille.getMontantSortie().add(etatValeurisationStockParSousFamille.getMontantDechet().add(etatValeurisationStockParSousFamille.getMontantOffreProduction().add(etatValeurisationStockParSousFamille.getMontantDechetService().add(etatValeurisationStockParSousFamille.getMontantOffreService()).add(etatValeurisationStockParSousFamille.getMontantAvoirClientPF()))))))))).divide(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF().add(etatValeurisationStockParSousFamille.getQuantiteSortie().add(etatValeurisationStockParSousFamille.getQuantiteDechet().add(etatValeurisationStockParSousFamille.getQuantiteOffreProduction().add(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatValeurisationStockParSousFamille.getQuantiteOffreService()).add(etatValeurisationStockParSousFamille.getQuantiteAvoirClientPF())))))))), 6, RoundingMode.HALF_UP));

//System.out.println(etatValeurisationStockParSousFamille.getMatierePremiere() +" **** "+etatValeurisationStockParSousFamille.getSousFamille() +" :  "+etatValeurisationStockParSousFamille.getMontantInitial() +"  +  "+etatValeurisationStockParSousFamille.getMontantAchat()+"  +  "+etatValeurisationStockParSousFamille.getMontantService()+"  +  "+etatValeurisationStockParSousFamille.getMontantTransfertMPPF()+"  +  "+etatValeurisationStockParSousFamille.getMontantSortie()+"  +  "+etatValeurisationStockParSousFamille.getMontantDechet()+"  +  "+etatValeurisationStockParSousFamille.getMontantOffreProduction()+"  +  "+etatValeurisationStockParSousFamille.getMontantDechetService()+"  +  "+etatValeurisationStockParSousFamille.getMontantOffreService() +" / "+etatValeurisationStockParSousFamille.getQuantiteInitial()+" + "+etatValeurisationStockParSousFamille.getQuantiteAchat()+" + "+etatValeurisationStockParSousFamille.getQuantiteService()+" + "+etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()+" + "+etatValeurisationStockParSousFamille.getQuantiteSortie()+" + "+etatValeurisationStockParSousFamille.getQuantiteDechet()+" + "+etatValeurisationStockParSousFamille.getQuantiteOffreProduction()+" + "+etatValeurisationStockParSousFamille.getQuantiteDechetService()+" + "+etatValeurisationStockParSousFamille.getQuantiteOffreService());



}else
{
etatValeurisationStockParSousFamille.setPrixFinale(BigDecimal.ZERO);
}

if(etatValeurisationStockParSousFamille.getQuantiteGratuit().compareTo(BigDecimal.ZERO)!=0)
{
	etatValeurisationStockParSousFamille.setPrixGratuit(etatValeurisationStockParSousFamille.getPrixFinale());
	etatValeurisationStockParSousFamille.setMontantGratuit(etatValeurisationStockParSousFamille.getPrixGratuit().multiply(etatValeurisationStockParSousFamille.getQuantiteGratuit()));
	
	
}

if(etatValeurisationStockParSousFamille.getQuantiteAvoir().compareTo(BigDecimal.ZERO)!=0)
{
	etatValeurisationStockParSousFamille.setPrixAvoir(etatValeurisationStockParSousFamille.getPrixFinale());
	etatValeurisationStockParSousFamille.setMontantAvoir (etatValeurisationStockParSousFamille.getPrixAvoir().multiply(etatValeurisationStockParSousFamille.getQuantiteAvoir()));
	
	
}


}else if(etatValeurisationStockParSousFamille.getType().equals(Constantes.MAGASIN_CODE_TYPE_MP))
{



etatValeurisationStockParSousFamille.setQuantiteFinale(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()).subtract(etatValeurisationStockParSousFamille.getQuantiteSortie().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF())).add(etatValeurisationStockParSousFamille.getQuantiteDechet().add(etatValeurisationStockParSousFamille.getQuantiteOffreProduction().add(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatValeurisationStockParSousFamille.getQuantiteOffreService().add(etatValeurisationStockParSousFamille.getQuantitevente()).add(etatValeurisationStockParSousFamille.getQuantiteGratuit().add(etatValeurisationStockParSousFamille.getQuantiteAvoir()))))))));

if(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()).compareTo(BigDecimal.ZERO)!=0)
{

etatValeurisationStockParSousFamille.setPrixFinale((etatValeurisationStockParSousFamille.getMontantInitial().add(etatValeurisationStockParSousFamille.getMontantAchat())).divide(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()), 6, RoundingMode.HALF_UP));

}else
{
etatValeurisationStockParSousFamille.setPrixFinale(BigDecimal.ZERO);
}

if(etatValeurisationStockParSousFamille.getQuantiteGratuit().compareTo(BigDecimal.ZERO)!=0)
{
	etatValeurisationStockParSousFamille.setPrixGratuit(etatValeurisationStockParSousFamille.getPrixFinale());
	etatValeurisationStockParSousFamille.setMontantGratuit(etatValeurisationStockParSousFamille.getPrixGratuit().multiply(etatValeurisationStockParSousFamille.getQuantiteGratuit()));
	
	
}


if(etatValeurisationStockParSousFamille.getQuantiteAvoir().compareTo(BigDecimal.ZERO)!=0)
{
	etatValeurisationStockParSousFamille.setPrixAvoir(etatValeurisationStockParSousFamille.getPrixFinale());
	etatValeurisationStockParSousFamille.setMontantAvoir (etatValeurisationStockParSousFamille.getPrixAvoir().multiply(etatValeurisationStockParSousFamille.getQuantiteAvoir()));
	
	
}

}


etatValeurisationStockParSousFamille.setMontantFinale(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteFinale()));


listEtatValeurisationStockParSousFamille.set(r, etatValeurisationStockParSousFamille);


}




	    	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	

	    		



	    	
/////////////////////////////////////////////////////////////////////////////////////////////////////  Calculer La Marge Et Achat Revendu et Percentage///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	
listEtatMargeDeStockArticlePF.clear();

 initial=BigDecimal.ZERO;
 achat=BigDecimal.ZERO;
 service=BigDecimal.ZERO;
 vente=BigDecimal.ZERO;
 gratuite=BigDecimal.ZERO;
 avoir=BigDecimal.ZERO;
 avoirClientPF=BigDecimal.ZERO;
 transfertMPPF=BigDecimal.ZERO;
 finale=BigDecimal.ZERO;
 achatrevendu=BigDecimal.ZERO;
 marge=BigDecimal.ZERO;
 percentage=BigDecimal.ZERO;
	 for(int r=0;r<listEtatValeurisationStockParSousFamille.size();r++)   	
	 {
		 
		 EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=listEtatValeurisationStockParSousFamille.get(r); 
		 
		 if(etatValeurisationStockParSousFamille.getType().equals(Constantes.MAGASIN_CODE_TYPE_PF))
		 {
			 
			if(etatValeurisationStockParSousFamille.getMontantvente().compareTo(BigDecimal.ZERO)!=0)
			{
				
				
				EtatMargeDeStockArticlePF etatMargeDeStockArticlePF=new EtatMargeDeStockArticlePF();
				etatMargeDeStockArticlePF.setArticle(etatValeurisationStockParSousFamille.getArticle());
				etatMargeDeStockArticlePF.setFamille(etatValeurisationStockParSousFamille.getFamille());
				etatMargeDeStockArticlePF.setSousFamille(etatValeurisationStockParSousFamille.getSousFamille());
				etatMargeDeStockArticlePF.setMatierePremiere(etatValeurisationStockParSousFamille.getMatierePremiere());
				etatMargeDeStockArticlePF.setMontantInitial(etatValeurisationStockParSousFamille.getMontantInitial());
				etatMargeDeStockArticlePF.setMontantAchat(etatValeurisationStockParSousFamille.getMontantAchat());
				etatMargeDeStockArticlePF.setMontantService(etatValeurisationStockParSousFamille.getMontantService());
				etatMargeDeStockArticlePF.setMontantvente(etatValeurisationStockParSousFamille.getMontantvente());
				etatMargeDeStockArticlePF.setMontantGratuit(etatValeurisationStockParSousFamille.getMontantGratuit());
				etatMargeDeStockArticlePF.setMontantAvoir(etatValeurisationStockParSousFamille.getMontantAvoir());
				etatMargeDeStockArticlePF.setMontantAvoirClientPF(etatValeurisationStockParSousFamille.getMontantAvoirClientPF());
				etatMargeDeStockArticlePF.setMontantTransfertMPPF(etatValeurisationStockParSousFamille.getMontantTransfertMPPF());
				etatMargeDeStockArticlePF.setMontantFinale(etatValeurisationStockParSousFamille.getMontantFinale());
				
				etatMargeDeStockArticlePF.setAchatRevendu((etatMargeDeStockArticlePF.getMontantInitial().add(etatMargeDeStockArticlePF.getMontantAchat().add(etatMargeDeStockArticlePF.getMontantService().add(etatMargeDeStockArticlePF.getMontantTransfertMPPF()).add(etatMargeDeStockArticlePF.getMontantAvoirClientPF())))).subtract(etatMargeDeStockArticlePF.getMontantGratuit().add(etatMargeDeStockArticlePF.getMontantAvoir().add(etatMargeDeStockArticlePF.getMontantFinale()))));
				etatMargeDeStockArticlePF.setMarge(etatMargeDeStockArticlePF.getMontantvente().subtract(etatMargeDeStockArticlePF.getAchatRevendu()));
				etatMargeDeStockArticlePF.setPercentage(etatMargeDeStockArticlePF.getMarge().divide(etatMargeDeStockArticlePF.getMontantvente(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
				
				listEtatMargeDeStockArticlePF.add(etatMargeDeStockArticlePF);
				
				
				initial=initial.add(etatValeurisationStockParSousFamille.getMontantInitial());
				achat=achat.add(etatValeurisationStockParSousFamille.getMontantAchat());
				service=service.add(etatValeurisationStockParSousFamille.getMontantService());
				vente=vente.add(etatValeurisationStockParSousFamille.getMontantvente());
				gratuite=gratuite.add(etatValeurisationStockParSousFamille.getMontantGratuit());
				avoir=avoir.add(etatValeurisationStockParSousFamille.getMontantAvoir());
				avoirClientPF=avoirClientPF.add(etatValeurisationStockParSousFamille.getMontantAvoirClientPF());
				transfertMPPF=transfertMPPF.add(etatValeurisationStockParSousFamille.getMontantTransfertMPPF());
				finale=finale.add(etatValeurisationStockParSousFamille.getMontantFinale());
				
				
			}
			 
			 
			 
			 
			 
		 }
		 
		 
		 
		 }

	 
	 achatrevendu=(initial.add(achat.add(service.add(transfertMPPF).add(avoirClientPF)))).subtract(gratuite.add(avoir.add(finale)));
	 marge=vente.subtract(achatrevendu);
	 if(vente.compareTo(BigDecimal.ZERO)!=0)
	 {
		 
		 percentage=marge.divide(vente, 6, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
		 
	 }
	 
	 


	    	afficher_tableEtatStock(listEtatMargeDeStockArticlePF);
	    		
	    		
	    		
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(534, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane.setBounds(10, 57, 1491, 51);
	    add(layeredPane);
	    
	    JLabel label = new JLabel("Date Debut :");
	    label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label.setBounds(10, 11, 136, 24);
	    layeredPane.add(label);
	    
	     dateChooserdebut = new JDateChooser();
	    dateChooserdebut.setLocale(Locale.FRANCE);
	    dateChooserdebut.setDateFormatString("dd/MM/yyyy");
	    dateChooserdebut.setBounds(101, 11, 163, 26);
	    layeredPane.add(dateChooserdebut);
	    
	    JLabel label_1 = new JLabel("Date Fin :");
	    label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_1.setBounds(274, 9, 106, 24);
	    layeredPane.add(label_1);
	    
	     dateChooserfin = new JDateChooser();
	    dateChooserfin.setLocale(Locale.FRANCE);
	    dateChooserfin.setDateFormatString("dd/MM/yyyy");
	    dateChooserfin.setBounds(348, 9, 169, 26);
	    layeredPane.add(dateChooserfin);

	    
	    JLabel lblMagasinMp = new JLabel("Magasin MP  :");
	    lblMagasinMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblMagasinMp.setBounds(527, 8, 80, 26);
	    layeredPane.add(lblMagasinMp);
	    
	     comboMagasinMP = new JComboBox();
	    comboMagasinMP.setBounds(617, 11, 219, 27);
	    layeredPane.add(comboMagasinMP);
	    try {
			  
			 
	          Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)util.DateUtils.getCurrentYear()+"-01-01");
	          dateChooserdebut.setDate(date);
	          dateChooserfin.setDate(new Date());
			  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	    JButton button = new JButton("Initialiser");
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		

	     		
	     		
	     		dateChooserdebut.setCalendar(null);
	     		dateChooserfin.setCalendar(null);
	     		
	     		comboMagasinMP.setSelectedItem("");
	     	
	     		comboMagasinPF.setSelectedItem("");
	    	}
	    });
	    button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button.setBounds(668, 119, 107, 24);
	    add(button);
	    
	    JButton btnExporterExcel = new JButton("Exporter Excel");
	    btnExporterExcel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Magasin magasinMP=mapMagasin.get(comboMagasinMP.getSelectedItem());
	    		Magasin magasinPF=mapMagasin.get(comboMagasinPF.getSelectedItem());
	    		if(magasinMP!=null && magasinPF!=null )
	    		{
	    		
	    		String titre="Etat Marge De Stock Par Article "+mapMagasin.get(comboMagasinMP.getSelectedItem()).getLibelle() +" Et "+mapMagasin.get(comboMagasinPF.getSelectedItem()).getLibelle();
	    		String titrefeuille="Etat Marge De Stock Par Article ";
	    		ExporterTableVersExcel.tabletoexcelEtatMargeStockParArticle(tableEtatStock, titre,titrefeuille,initial,achat,service,vente,gratuite,avoir,transfertMPPF,finale,achatrevendu,marge,percentage);
	    		
	    		}else
	    		{


	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin PF Et MP SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		
	    		}
	    	}
	    });
	    btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnExporterExcel.setBounds(704, 745, 123, 32);
	    btnExporterExcel.setIcon(imgExcel);
	    add(btnExporterExcel);
	    
	     comboMagasinPF = new JComboBox();
	    comboMagasinPF.setBounds(962, 14, 202, 27);
	    layeredPane.add(comboMagasinPF);
	    
	    JLabel lblMagasinPf = new JLabel("Magasin PF  :");
	    lblMagasinPf.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblMagasinPf.setBounds(872, 11, 94, 26);
	    layeredPane.add(lblMagasinPf);
	    
	    JLabel lblStockInitial = new JLabel("Total Initial :");
	    lblStockInitial.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblStockInitial.setBounds(20, 793, 112, 24);
	    add(lblStockInitial);
	    
	     labelTotalInitial = new JLabel("");
	     labelTotalInitial.setForeground(Color.RED);
	    labelTotalInitial.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalInitial.setBounds(142, 793, 257, 24);
	    add(labelTotalInitial);
	    
	    JLabel lblStockAchat = new JLabel("Total Achat:");
	    lblStockAchat.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblStockAchat.setBounds(452, 793, 112, 24);
	    add(lblStockAchat);
	    
	     labelTotalAchat = new JLabel("");
	     labelTotalAchat.setForeground(Color.RED);
	    labelTotalAchat.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalAchat.setBounds(612, 793, 223, 24);
	    add(labelTotalAchat);
	    
	    JLabel lblPrixService = new JLabel("Total Service :");
	    lblPrixService.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblPrixService.setBounds(10, 845, 167, 24);
	    add(lblPrixService);
	    
	     labelTotalService = new JLabel("");
	     labelTotalService.setForeground(Color.RED);
	    labelTotalService.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalService.setBounds(148, 845, 221, 24);
	    add(labelTotalService);
	    
	    JLabel lblCa = new JLabel("CA :");
	    lblCa.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblCa.setBounds(452, 845, 41, 24);
	    add(lblCa);
	    
	     labelTotalCA = new JLabel("");
	     labelTotalCA.setForeground(Color.RED);
	    labelTotalCA.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalCA.setBounds(503, 845, 198, 24);
	    add(labelTotalCA);
	    
	    JLabel lblGratuite = new JLabel("Total Gratuite :");
	    lblGratuite.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblGratuite.setBounds(20, 905, 112, 24);
	    add(lblGratuite);
	    
	     labelTotalGratuite = new JLabel("");
	     labelTotalGratuite.setForeground(Color.RED);
	    labelTotalGratuite.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalGratuite.setBounds(201, 905, 198, 24);
	    add(labelTotalGratuite);
	    
	    JLabel lblAvoir = new JLabel("Total Avoir :");
	    lblAvoir.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblAvoir.setBounds(439, 905, 112, 24);
	    add(lblAvoir);
	    
	     labelTotalAvoir = new JLabel("");
	     labelTotalAvoir.setForeground(Color.RED);
	    labelTotalAvoir.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalAvoir.setBounds(620, 905, 227, 24);
	    add(labelTotalAvoir);
	    
	     labelTotalAchatRevendu = new JLabel("");
	     labelTotalAchatRevendu.setForeground(Color.RED);
	    labelTotalAchatRevendu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalAchatRevendu.setBounds(1074, 899, 235, 24);
	    add(labelTotalAchatRevendu);
	    
	    JLabel lblFinaleSansEmballage = new JLabel("Total Achat Revendu:");
	    lblFinaleSansEmballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblFinaleSansEmballage.setBounds(893, 899, 179, 24);
	    add(lblFinaleSansEmballage);
	    
	    JLabel lblStockEmballage_1 = new JLabel("Total transfert:");
	    lblStockEmballage_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblStockEmballage_1.setBounds(893, 793, 156, 24);
	    add(lblStockEmballage_1);
	    
	    JLabel lblFinale = new JLabel("Total Finale :");
	    lblFinale.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblFinale.setBounds(893, 845, 112, 24);
	    add(lblFinale);
	    
	     labelTotalTransfert = new JLabel("");
	     labelTotalTransfert.setForeground(Color.RED);
	    labelTotalTransfert.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalTransfert.setBounds(1074, 793, 235, 24);
	    add(labelTotalTransfert);
	    
	     labelTotalFinale = new JLabel("");
	     labelTotalFinale.setForeground(Color.RED);
	    labelTotalFinale.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalFinale.setBounds(1074, 845, 235, 24);
	    add(labelTotalFinale);
	    
	     labelmarge = new JLabel((String) null);
	    labelmarge.setForeground(Color.RED);
	    labelmarge.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelmarge.setBounds(1074, 944, 235, 24);
	    add(labelmarge);
	    
	    JLabel lblMarge = new JLabel("Marge:");
	    lblMarge.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblMarge.setBounds(893, 944, 179, 24);
	    add(lblMarge);
	    
	    JLabel lblPercentage = new JLabel("%:");
	    lblPercentage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblPercentage.setBounds(893, 986, 179, 24);
	    add(lblPercentage);
	    
	     labelpercentage = new JLabel("");
	    labelpercentage.setForeground(Color.RED);
	    labelpercentage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelpercentage.setBounds(1074, 986, 235, 24);
	    add(labelpercentage);
	    
	    JLabel lblTotalAvoirClientpf = new JLabel("Total Avoir ClientPF :");
	    lblTotalAvoirClientpf.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblTotalAvoirClientpf.setBounds(439, 944, 150, 24);
	    add(lblTotalAvoirClientpf);
	    
	     labelTotalAvoirClientPF = new JLabel("");
	    labelTotalAvoirClientPF.setForeground(Color.RED);
	    labelTotalAvoirClientPF.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalAvoirClientPF.setBounds(620, 944, 227, 24);
	    add(labelTotalAvoirClientPF);
	    
	    //button_1.setVisible(false);
	    
	
	     if(utilisateur.getLogin().equals("admin"))
	 		  {
	 	    	listMagasin =depotdao.listeMagasinByTypeMagasin(MAGASIN_CODE_TYPE_PF);
	 			  int k=0;
	 			 comboMagasinPF.addItem("");
	 		     	while (k<listMagasin.size())
	 		     	{
	 		     		Magasin magasin=listMagasin.get(k);
	 		     		
	 		     		
	 		     		comboMagasinPF.addItem(magasin.getLibelle());
	 				     		
	 				     		mapMagasin.put(magasin.getLibelle(), magasin);
	 				     	
	 		     		k++;
	 		     		
	 		     	}
	 		     	
	 		     	listMagasin =depotdao.listeMagasinByTypeMagasin(MAGASIN_CODE_TYPE_MP);
		 			  int j=0;
		 			 comboMagasinMP.addItem("");
		 		     	while (j<listMagasin.size())
		 		     	{
		 		     		Magasin magasin=listMagasin.get(j);
		 		     		
		 		     		
		 		     		comboMagasinMP.addItem(magasin.getLibelle());
		 				     		
		 				     		mapMagasin.put(magasin.getLibelle(), magasin);
		 				     	
		 		     		j++;
		 		     		
		 		     	}
	 		     	
	 		     	
	 		     	
	 		     	
	 		      
	 		  }else
	 		  {
	 			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	 			  if(depot!=null)
	 			  {
	 				  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_PF);
	 				  int k=0;
	 				 comboMagasinPF.addItem("");
	 			     	while (k<listMagasin.size())
	 			     	{
	 			     		Magasin magasin=listMagasin.get(k);
	 			     		
	 			     		
	 			     		comboMagasinPF.addItem(magasin.getLibelle());
	 					     		
	 					     		mapMagasin.put(magasin.getLibelle(), magasin);
	 					     	
	 			     		k++;
	 			     		
	 			     	}
	 			     	
	 			     	
	 			 	  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
	 				  int j=0;
	 				 comboMagasinMP.addItem("");
	 			     	while (j<listMagasin.size())
	 			     	{
	 			     		Magasin magasin=listMagasin.get(j);
	 			     		
	 			     		
	 			     		comboMagasinMP.addItem(magasin.getLibelle());
	 					     		
	 					     		mapMagasin.put(magasin.getLibelle(), magasin);
	 					     	
	 			     		j++;
	 			     		
	 			     	}
	 			     	
	 			     	
	 			     	
	 			     	
	 				 
	 			  }
	 		  }
	    
	    
	    
		
		}
	
	

	
	
	

	
	void InitialiseTableauDetailMouvementStock()
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article","Famille","Sous Famille", "Quantite Initial", "Prix Initial","Montant Initial", "Quantite Achat", "Prix Achat","Montant Achat","Quantite Production", "Prix Production","Montant Production","Quantite Transfer Entrer", "Prix Transfer Entrer","Montant Transfer Entrer","Quantite Vente", "Prix Vente","Montant Vente", "Quantite Gratuit", "Prix Gratuit","Montant Gratuit","Quantite Avoir", "Prix Avoir","Montant Avoir","Quantite Finale", "Prix Finale","Montant Finale","Marge"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modeleEtatStock);
		 tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
	       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
	       tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
	       tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
	       tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
	       tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(118);
	       tableEtatStock.getColumnModel().getColumn(6).setPreferredWidth(132);
	       tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(92);
	       tableEtatStock.getColumnModel().getColumn(8).setPreferredWidth(95);
	       tableEtatStock.getColumnModel().getColumn(9).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(10).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(10).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(11).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(12).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(13).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(14).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(15).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(16).setPreferredWidth(99);
		
}
	
	
/*	void InitialiseTableauMouvementStock()
	{
		modeleMouvementStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date Mouvement", "Depot", "Magasin"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modeleMouvementStock);
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		
}*/
	
	
	
	
	
	void afficher_tableEtatStock(List<EtatMargeDeStockArticlePF> listEtatMargeDeStockPF)
	{
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(' ');
		DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
		dfDecimal.setDecimalFormatSymbols(symbols);
		dfDecimal.setGroupingSize(3);
		dfDecimal.setGroupingUsed(true);
		

		
 
 
 
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ARTICLES PF (sources MP) ","Articles","Famille","Sous famille","Montant Initial","Montant Achat","Montant Service","Montant Vente","Montant Gratuit","Montant Avoir","Montant Avoir Client PF","Montant Transfert MP PF","Montant Finale","Achat Revendu","Marge","%"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modeleEtatStock);
		int i=0;
		
		String article="";
		
		while(i<listEtatMargeDeStockPF.size())
		{
			
			EtatMargeDeStockArticlePF etatMargeStockPF=listEtatMargeDeStockPF.get(i);
		
			article="";
			if(etatMargeStockPF.getArticle()!=null)
			{
				article=etatMargeStockPF.getArticle();
				
			}
			
			
			
			
		        Object []ligne={article, etatMargeStockPF.getMatierePremiere(), etatMargeStockPF.getFamille() ,etatMargeStockPF.getSousFamille(),etatMargeStockPF.getMontantInitial(),etatMargeStockPF.getMontantAchat(),etatMargeStockPF.getMontantService(),etatMargeStockPF.getMontantvente(),etatMargeStockPF.getMontantGratuit(),etatMargeStockPF.getMontantAvoir(),etatMargeStockPF.getMontantAvoirClientPF(), etatMargeStockPF.getMontantTransfertMPPF(),etatMargeStockPF.getMontantFinale(),etatMargeStockPF.getAchatRevendu(),etatMargeStockPF.getMarge(),etatMargeStockPF.getPercentage()};

				modeleEtatStock.addRow(ligne);
			
			
			i++;
		}
		
		
	
	
	    labelTotalInitial.setText(dfDecimal.format(initial.setScale(6, RoundingMode.HALF_UP))+"");	
		
		labelTotalAchat.setText(dfDecimal.format(achat.setScale(6, RoundingMode.HALF_UP))+"");	
		
		
		
		labelTotalService.setText(dfDecimal.format(service.setScale(6, RoundingMode.HALF_UP))+"");
		
		labelTotalCA.setText(dfDecimal.format(vente.setScale(6, RoundingMode.HALF_UP))+"");
		
		labelTotalGratuite.setText(dfDecimal.format(gratuite.setScale(6, RoundingMode.HALF_UP))+"");
		
		
		labelTotalAvoir.setText(dfDecimal.format(avoir.setScale(6, RoundingMode.HALF_UP))+"");
		labelTotalAvoirClientPF.setText(dfDecimal.format(avoirClientPF.setScale(6, RoundingMode.HALF_UP))+"");
		
		labelTotalFinale.setText(dfDecimal.format(finale.setScale(6, RoundingMode.HALF_UP))+"");
		labelTotalTransfert.setText(dfDecimal.format(transfertMPPF.setScale(6, RoundingMode.HALF_UP))+"");
		labelTotalAchatRevendu.setText(dfDecimal.format(achatrevendu.setScale(6, RoundingMode.HALF_UP))+"");
		labelmarge.setText(dfDecimal.format(marge.setScale(6, RoundingMode.HALF_UP))+"");
		labelpercentage.setText(dfDecimal.format(percentage.setScale(6, RoundingMode.HALF_UP))+"");
		
}
	
	public void ConsulterEtatStockMP()	

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
		Magasin magasinMP=mapMagasin.get(comboMagasinMP.getSelectedItem());
		
		if(magasinMP!=null)
		{
	 		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
    		{	
    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
    			String d2=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
    			
    		if(!d1.equals(d2))
    		{
    			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
    			{
    				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!");
    				return;
    			}
    			
    		}
    		
    	
    		
    		}
    		
    		
    		if(dateChooserdebut.getDate()==null && mp!=null)
    		{
    			dateChooserfin.setCalendar(null);
    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
    		{
    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
    			
    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
    		{
    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
    		}	
    		
    		
    		listDetailTransferStockMPInitial=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutInitial(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_INITIAL,magasinMP);
    		listDetailTransferStockMPAchat=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_ACHAT,magasinMP);
    		
    		listDetailTransferStockMPAchatGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_ACHAT,magasinMP);
    		listDetailTransferStockMPSortie=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE,magasinMP);
    		listDetailTransferStockMPSortieGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE,magasinMP);
    		
    		listDetailTransferStockMPAvoir=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAvoir(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_AVOIR,magasinMP);
    		listDetailTransferStockMPAvoirGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAvoir(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_AVOIR,magasinMP);
    		
    		listDetailTransferStockMPChargeSupp=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutChargeSupp(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasinMP);
    		listDetailTransferStockMPChargeSuppGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutChargeSupp(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasinMP);
    		
    		listDetailTransferStockMPService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutService(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SERVICE,magasinMP);
    		listDetailTransferStockMPServiceGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutService(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SERVICE,magasinMP);
    		

    		listDetailTransferStockMPVente=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_VENTE,magasinMP);
    		listDetailTransferStockMPVenteGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_VENTE,magasinMP);
    		
    		listDetailTransferStockMPTransferMPPFProduction=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF,magasinMP);
    		listDetailTransferStockMPTransferMPPFProductionGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF,magasinMP);
    		
    		listDetailTransferStockMPTransferMPPF=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasinMP);
    		listDetailTransferStockMPTransferMPPFGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasinMP);
    		
    		
    		
    		listDetailTransferStockMPTransferMPPFService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE,magasinMP);
    		listDetailTransferStockMPTransferMPPFGroupebyMPService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE,magasinMP);
    		
    		
    		
    		listDetailTransferStockMPAllMP=	detailTransferStockMPDAO.findAllTransferStockMPGroupeByMP(magasinMP);
    		
    		for(int p=0;p<listDetailTransferStockMPAllMP.size();p++)
    		{
    			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPAllMP.get(p);
    			EtatStockMP etatstock=new EtatStockMP();
    			
    			etatstock.setMp(detailtransferstockmp.getMatierePremier());
    			
    			if(detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
    			{
    			
    				SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(detailtransferstockmp.getMatierePremier());
    				
    				
    				if(sousFamilleEnVrac!=null)
    				{
    					
    					etatstock.setSousFamille(sousFamilleEnVrac.getSousfamile().getLiblle());
    					etatstock.setFamille(sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle());
    				}else
    				{
    					
    					etatstock.setSousFamille(detailtransferstockmp.getMatierePremier().getCategorieMp().getNom());
    					etatstock.setFamille(detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
    					
    				}
    				
    				
    				
    			}else
    			{
    				
    				etatstock.setSousFamille(detailtransferstockmp.getMatierePremier().getCategorieMp().getNom());
					etatstock.setFamille(detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
    				
    				
    			}
    			
    			
    			
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
    			etatstock.setQuantiteTransfertMPPF(BigDecimal.ZERO);
    			etatstock.setPrixTransfertMPPF(BigDecimal.ZERO);
    			etatstock.setMontantTransfertMPPF(BigDecimal.ZERO);
    			
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
	    					EtatStockMP etatstockmp=listEtatStockMP.get(p);
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
    					EtatStockMP etatstockmp=listEtatStockMP.get(i);
    					
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
		    			EtatStockMP etatstockmp=listEtatStockMP.get(k);
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
		    			EtatStockMP etatstockmp=listEtatStockMP.get(k);
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
		    			EtatStockMP etatstockmp=listEtatStockMP.get(k);
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
	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
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
	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
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
	    			
	    			montantDechetService=montantDechetService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteDechet()));
	    			quantiteTotalDechetService=quantiteTotalDechetService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).getQuantiteDechet().setScale(0, RoundingMode.CEILING));
	    			if(listDetailTransferStockMPService.get(k).getQuantiteOffre()!=null)
	    			{
	    				montantOffreService=montantOffreService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteOffre()));
		    			quantiteTotalOffreService=quantiteTotalOffreService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).getQuantiteOffre().setScale(0, RoundingMode.CEILING));
	    			}
	    			
    				
				}else
				{
					montantService=montantService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantite()));
	    			quantiteTotalService=quantiteTotalService.add(listDetailTransferStockMPService.get(k).getQuantite());
	    			
	    			montantDechetService=montantDechetService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteDechet()));
	    			quantiteTotalDechetService=quantiteTotalDechetService.add(listDetailTransferStockMPService.get(k).getQuantiteDechet());
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
    					
    					
	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
	    				
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
    					
    					
	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
	    				
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
	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
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
    	
    		
    		
    	for(int k=0;k<listDetailTransferStockMPTransferMPPF.size();k++)
    	{
    		
		if(listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier()))
    		{
    			if(listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
    				montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPF.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPF.get(k).getQuantite().setScale(0, RoundingMode.CEILING)));
	    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPF.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
    	
	    			
				}else
				{
					montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPF.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPF.get(k).getQuantite()));
	    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.add(listDetailTransferStockMPTransferMPPF.get(k).getQuantite());
	    			
	    
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
	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
    					{
	    					
		    				    etatstockmp.setPrixTransfertMPPF((etatstockmp.getQuantiteTransfertMPPF().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixTransfertMPPF()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteTransfertMPPF().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

    							etatstockmp.setQuantiteTransfertMPPF(etatstockmp.getQuantiteTransfertMPPF().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)) );
			    				etatstockmp.setMontantTransfertMPPF(etatstockmp.getQuantiteTransfertMPPF().multiply(etatstockmp.getPrixTransfertMPPF()));
			    				
		    				listEtatStockMP.set(i, etatstockmp);
	    					
    					}else
    					{
    					     etatstockmp.setPrixTransfertMPPF((etatstockmp.getQuantiteTransfertMPPF().multiply(etatstockmp.getPrixTransfertMPPF()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteTransfertMPPF().add(quantiteTotalTransferMPPF),6,RoundingMode.HALF_UP));

    						etatstockmp.setQuantiteTransfertMPPF(etatstockmp.getQuantiteTransfertMPPF().add(quantiteTotalTransferMPPF));
			    				etatstockmp.setMontantTransfertMPPF(etatstockmp.getQuantiteTransfertMPPF().multiply(etatstockmp.getPrixTransfertMPPF()));
			    							
		    				
		    				listEtatStockMP.set(i, etatstockmp);	
    					}
	    				
	    				
	    			}
    	    	}
    			
    		}
    		
    	}
    	
//    	
//
//	
//	
//    	
//    	// calculer calculer le prix moyen et quantite TransferMPPFSERVICE
//    	
//    	
//    	for(int j=0;j<listDetailTransferStockMPTransferMPPFGroupebyMPService.size();j++)
//    	{
//    		montantTransferMPPF=new BigDecimal(0);
//    		quantiteTotalTransferMPPF=new BigDecimal(0);
//    		quantiteOffreaSupprimerService=new BigDecimal(0);
//    		quantiteDechetaSupprimerService=new BigDecimal(0);
//    		
//    		
//    	for(int k=0;k<listDetailTransferStockMPTransferMPPFService.size();k++)
//    	{
//    		
//    		if(listDetailTransferStockMPTransferMPPFGroupebyMPService.get(j).getMatierePremier().equals(listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier()))
//    		{
//    			if(listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
//				{
//    				montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING)));
//	    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
//	    			
//	    			
//	    			// calculer la somme de quantite dechet et offre a supprimer
//	    			if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource()!=null)
//	    			{
//	    				if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
//	    				{
//	    					quantiteDechetaSupprimerService=quantiteDechetaSupprimerService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
//	    				}else if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
//	    				{
//	    					quantiteOffreaSupprimerService=quantiteOffreaSupprimerService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING))	;
//	    				}
//	    				
//	    			}
//	    			////////////////////////////////////////////////////////////////////////////////// 
//	    			
//	    			
//				}else
//				{
//					montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite()));
//	    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite());
//	    			
//	    			// calculer la somme de quantite dechet et offre a supprimer
//	    			
//	    			if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource()!=null)
//	    			{
//	    				
//	    				if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
//	    				{
//	    					quantiteDechetaSupprimerService=quantiteDechetaSupprimerService.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite());
//	    				}else if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
//	    				{
//	    					quantiteOffreaSupprimerService=quantiteOffreaSupprimerService.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite())	;
//	    				}
//	    				
//	    			}
//	    			
//                    //////////////////////////////////////////////////////////////////////////////////
//	    			
//				}
//    	
//    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
//    			
//    		}
//    		
//    		
//    	}
//    		if(!montantTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !quantiteTotalTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
//    		{
//    			/*	    			
//    			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
//    			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
//    			*/
//    			
//    			for(int i=0;i<listEtatStockMP.size();i++)
//    	    	{
//    				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPTransferMPPFGroupebyMPService.get(j).getMatierePremier()))
//	    			{
//	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
//	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
//    					{
//	    					
//		    				    etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));
//
//    							etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)) );
//			    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
//			    				// supprimer la quantite dechet et quantite offre
//			    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING).subtract(quantiteDechetaSupprimerService.setScale(0, RoundingMode.CEILING)));
//			    				if(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
//			    				{
//			    					etatstockmp.setPrixDechetService(BigDecimal.ZERO);	
//			    				}
//			    				etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
//			    				
//			    				etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING).subtract(quantiteOffreaSupprimerService.setScale(0, RoundingMode.CEILING)));
//			    				if(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
//			    				{
//			    					etatstockmp.setPrixOffreService(BigDecimal.ZERO);
//			    				}
//			    				etatstockmp.setMontantOffreService(etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
//    						
//	    					
//	    				
//		    				
//		    				listEtatStockMP.set(i, etatstockmp);
//	    					
//    					}else
//    					{
//    						
//    							
//    						
//		    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF),6,RoundingMode.HALF_UP));
//
//    						   etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF));
//    					
//			    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
//			    				// supprimer la quantite dechet et quantite offre
//			    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().subtract(quantiteDechetaSupprimerService));
//			    				if(etatstockmp.getQuantiteDechetService().setScale(2, RoundingMode.UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.UP)))
//			    				{
//			    					etatstockmp.setPrixDechetService(BigDecimal.ZERO);	
//			    				}
//			    				etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
//			    				
//			    				etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().setScale(6, RoundingMode.HALF_UP).subtract(quantiteOffreaSupprimerService.setScale(6, RoundingMode.HALF_UP)));
//			    				if(etatstockmp.getQuantiteOffreService().setScale(2, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)))
//			    				{
//			    					etatstockmp.setPrixOffreService(BigDecimal.ZERO);
//			    				}
//			    				etatstockmp.setMontantOffreService(etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
//    					
//		    				listEtatStockMP.set(i, etatstockmp);	
//    					}
//	    				
//	    				
//	    			}
//    	    	}
//    			
//    		}
//    		
//    	}
//    	
//    	
    	// Calculer le Prix Moyen a partir de mouvement de stock 
    	
    	CalculerPrixMoyenFinaleParMouvementStock();
    	
    
    	// calculer le stock finale et le prix moyen finale
    	
    	
     	for(int i=0;i<listEtatStockMP.size();i++)
    	{
    		
     		quantiteTotalFinale=BigDecimal.ZERO;
	   		montantFinale=BigDecimal.ZERO;
    	    BigDecimal prixMoyen=BigDecimal.ZERO;
    			EtatStockMP etatstockmp=listEtatStockMP.get(i);
    			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
    				etatstockmp.setQuantiteFinale((etatstockmp.getQuantiteInitial().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteAchat().setScale(0, RoundingMode.CEILING))).subtract(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteService().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteAvoir().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteOffreProduction().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteDechet().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteTransfertMPPF().setScale(0, RoundingMode.CEILING)))));
	    			quantiteTotalFinale=(quantiteTotalFinale.setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteInitial().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteAchat().setScale(0, RoundingMode.CEILING))));
	    			montantFinale=(montantFinale.setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantInitial().setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantAchat().setScale(6,RoundingMode.HALF_UP))));
	    			
	    			if(listMouvementStockMPAfficherMouvementTmp.size()!=0)
		    		{
		    			for(int l=0;l<listMouvementStockMPAfficherMouvementTmp.size();l++)
		    			{
		    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom()))
		    				{
		    					if(listMouvementStockMPAfficherMouvementTmp.get(l).getDateCreation().compareTo(dateChooserfin.getDate())<=0 )
		    					{
		    						prixMoyen=listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb();
			    					//System.out.println(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficherMouvementTmp.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficherMouvementTmp.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficherMouvementTmp.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb() ) ;

		    					}
		    									    					
		    				}
		    				
		    			}
		    			
		    		}else
		    		{
		    			for(int l=0;l<listMouvementStockMPAfficher.size();l++)
		    			{
		    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom()))
		    				{
		    					if(listMouvementStockMPAfficher.get(l).getDateCreation().compareTo(dateChooserfin.getDate())<=0 )
		    					{
		    						prixMoyen=listMouvementStockMPAfficher.get(l).getPrixFinaldb();
			    					//System.out.println(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficher.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficher.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficher.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficher.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficher.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficher.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficher.get(l).getPrixFinaldb() ) ;
			    					
		    					}
		    					
		    				}
		    			}
		    			
		    		}
	    			
	    			StockMP stockmp=stockMPDAO.findStockByMagasinMP(etatstockmp.getMp().getId(),magasinMP.getId());
					/*
					 * if(etatstockmp.getQuantiteAchat().setScale(0,
					 * RoundingMode.CEILING).add(etatstockmp.getQuantiteInitial().setScale(0,
					 * RoundingMode.CEILING)).compareTo(BigDecimal.ZERO)>0) {
					 * prixMoyen=(etatstockmp.getMontantInitial().add(etatstockmp.getMontantAchat())
					 * ).divide(etatstockmp.getQuantiteAchat().setScale(0,
					 * RoundingMode.CEILING).add(etatstockmp.getQuantiteInitial().setScale(0,
					 * RoundingMode.CEILING)), 6, RoundingMode.HALF_UP);
					 * 
					 * }
					 */
	    			//prixMoyen=stockmp.getPrixUnitaire();
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
					etatstockmp.setQuantiteFinale((etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat())).subtract(etatstockmp.getQuantiteSortie().add(etatstockmp.getQuantiteService()).add(etatstockmp.getQuantiteAvoir().add(etatstockmp.getQuantiteOffreService()).add(etatstockmp.getQuantiteOffreProduction()).add(etatstockmp.getQuantiteDechet()).add(etatstockmp.getQuantiteDechetService()).add(etatstockmp.getQuantiteTransfertMPPF()))));
	    			quantiteTotalFinale=(quantiteTotalFinale.add(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat())));
	    			montantFinale=(montantFinale.setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantInitial().setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantAchat().setScale(6,RoundingMode.HALF_UP))));
	    			
	    			if(listMouvementStockMPAfficherMouvementTmp.size()!=0)
		    		{
		    			for(int l=0;l<listMouvementStockMPAfficherMouvementTmp.size();l++)
		    			{
		    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom()))
		    				{
		    					if(listMouvementStockMPAfficherMouvementTmp.get(l).getDateCreation().compareTo(dateChooserfin.getDate())<=0 )
		    					{
		    						prixMoyen=listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb();
			    				//	System.out.println(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficherMouvementTmp.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficherMouvementTmp.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficherMouvementTmp.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb() ) ;
			    					
		    					}
		    					
		    				}
		    				
		    			}
		    			
		    		}else
		    		{
		    			for(int l=0;l<listMouvementStockMPAfficher.size();l++)
		    			{
		    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom()))
		    				{
		    					if(listMouvementStockMPAfficher.get(l).getDateCreation().compareTo(dateChooserfin.getDate())<=0 )
		    					{
		    						prixMoyen=listMouvementStockMPAfficher.get(l).getPrixFinaldb();
			    					//System.out.println(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficher.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficher.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficher.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficher.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficher.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficher.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficher.get(l).getPrixFinaldb() ) ;
			    				
		    					}
		    					
		    				}
		    			}
		    			
		    		}
	    			
	    			StockMP stockmp=stockMPDAO.findStockByMagasinMP(etatstockmp.getMp().getId(),magasinMP.getId());
	    			
					/*
					 * if(etatstockmp.getQuantiteAchat().add(etatstockmp.getQuantiteInitial()).
					 * compareTo(BigDecimal.ZERO)>0) {
					 * prixMoyen=(etatstockmp.getMontantInitial().add(etatstockmp.getMontantAchat())
					 * ).divide(etatstockmp.getQuantiteAchat().add(etatstockmp.getQuantiteInitial())
					 * , 6, RoundingMode.HALF_UP);
					 * 
					 * }
					 */
	    			
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
	
	
	  public void CalculerPrixMoyenFinaleParMouvementStock()
{
		   
		  

		  
		  

	   // detailTransferStockMPDAO.ViderSession();
		listDetailTransferStockMP.clear();
		listDetailTransferStockMPGroupebyMP.clear();
		listDetailTransferStockMPBytypetransfer.clear();
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		listMouvementStockMP.clear();
		boolean trouve=false;
		MatierePremier mp=null;
		Magasin magasinMP=mapMagasin.get(comboMagasinMP.getSelectedItem());
		
		if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null)
		{
			JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP !!!");
				return;
		}else
		{
			if(magasinMP!=null)
	    		{
	    			if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    			String d2=sdf.format(dateChooserfin.getDate());
		    			
		    		if(!d1.equals(d2))
		    		{
		    			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
		    			{
		    				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!");
		    				return;
		    			}
		    			
		    		}
		    		
		    	
		    		
		    		}
		    		
		    		if(dateChooserdebut.getDate()==null )
		    		{
		    			dateChooserfin.setCalendar(null);
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    		}
		    		
		    		
		    		listDetailTransferStockMP=detailTransferStockMPDAO.findAllTransferStockMPOrderByDateTransfer(magasinMP);
		    		listDetailTransferStockMPGroupebyMP=detailTransferStockMPDAO.findAllTransferStockMPGroupeByDateTransferByMP(magasinMP);
		    		listDetailTransferStockMPBytypetransfer=detailTransferStockMPDAO.findBytypetransfer(ETAT_TRANSFER_STOCK_INITIAL,magasinMP);
		    		String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.ETAT_TRANSFER_STOCK_CHARGE,ETAT_TRANSFER_STOCK_CHARGE_SUPP,ETAT_TRANSFER_STOCK_VENTE,ETAT_TRANSFER_STOCK_AVOIR,ETAT_TRANSFER_STOCK_SERVICE,ETAT_TRANSFER_STOCK_SORTIE_PF,ETAT_TRANSFER_STOCK_SORTIE_MP_PF};
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
		    			
		    			if(i!=0)
		    			{
		    				if(!listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().equals(listDetailTransferStockMPGroupebyMP.get(i-1).getMatierePremier()))
		    				{
		    					prixmoyenne=new BigDecimal(0);
				    			quantitefinale=new BigDecimal(0);
		    					
		    				}
		    				
		    			}else
		    			{
		    				prixmoyenne=new BigDecimal(0);
			    			quantitefinale=new BigDecimal(0);
		    			}
		    			
		    			
		    				
		    			for(int j=0;j<listDetailTransferStockMP.size();j++)
		    			{
		    				for(int k=0;k<typetransfer.length;k++)
			    			{
		    				
		    			if(listDetailTransferStockMPGroupebyMP.get(i).getTransferStockMP().getDateTransfer().equals(listDetailTransferStockMP.get(j).getTransferStockMP().getDateTransfer()) 
		    					&& listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().equals(listDetailTransferStockMP.get(j).getMatierePremier()) && listDetailTransferStockMP.get(j).getTransferStockMP().getStatut().equals(typetransfer[k]))	
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
		    			
		    				
		    			if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ACHAT))
		    			{
		    				
                   if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                       {
                	   if(!achat.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING)).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
                	   {
                    	   prixAchat=(prixAchat.multiply(achat).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING).multiply(listDetailTransferStockMP.get(j).getPrixUnitaire()))).divide(achat.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING)), RoundingMode.HALF_UP);

                	   }else
                	   {
                		   prixAchat=BigDecimal.ZERO;
                	   }
                	   achat=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
                	   
                        }else
                        {
                        	if(!listDetailTransferStockMP.get(j).getQuantite().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
                        	{
	                        	   prixAchat=(prixAchat.multiply(achat).add(listDetailTransferStockMP.get(j).getQuantite().multiply(listDetailTransferStockMP.get(j).getPrixUnitaire()))).divide(achat.add(listDetailTransferStockMP.get(j).getQuantite()), RoundingMode.HALF_UP);

                        	}else
                        	{
	                        	   prixAchat=BigDecimal.ZERO;

                        	}
                        	

                        	achat=listDetailTransferStockMP.get(j).getQuantite();
                        }
		    				
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_INITIAL))
		    			{
		    				   if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					    prixInitial=listDetailTransferStockMP.get(j).getPrixUnitaire();
		    						initial=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
                           }else
                           {
                        	    prixInitial=listDetailTransferStockMP.get(j).getPrixUnitaire();
                        		initial=listDetailTransferStockMP.get(j).getQuantite(); 
                           }
		    			
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE) || typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE_SUPP))
		    			{
		    				 if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					 production=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
		    					 
		    					 if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE))
			    				 {
			    					
			    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
			    					{
			    						
			    						dechet=listDetailTransferStockMP.get(j).getQuantiteDechet().setScale(0, RoundingMode.CEILING);
			    					}
			    					
			    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
			    					{
			    						
			    						offreProduction=listDetailTransferStockMP.get(j).getQuantiteOffre().setScale(0, RoundingMode.CEILING);
			    					}
			    					
			    				 }
		    					 
		    					 
                           }else
                           {
                        	  // System.out.println("listDetailTransferStockMP.get(j).getQuantite() :"+listDetailTransferStockMP.get(j).getQuantite());
                        	   production=listDetailTransferStockMP.get(j).getQuantite(); 
                        	   if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE))
				    				 {
				    					
				    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
				    					{
				    						
				    						dechet=listDetailTransferStockMP.get(j).getQuantiteDechet();
				    					}
				    					
				    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
				    					{
				    						
				    						offreProduction=listDetailTransferStockMP.get(j).getQuantiteOffre();
				    					}
				    					 
				    				 }
                           }
		    				 
		    				
		    				 
		    			}
		    			
		    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_VENTE))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					vente=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
                           }else
                           {
                        	   vente=listDetailTransferStockMP.get(j).getQuantite();
                           }
		    					
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					avoir=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
                           }else
                           {
                        	   avoir=listDetailTransferStockMP.get(j).getQuantite();
                           }
		    					
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SERVICE))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					service=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);	
		    					
			    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
			    					{
			    						
			    						dechetService=listDetailTransferStockMP.get(j).getQuantiteDechet().setScale(0, RoundingMode.CEILING);
			    					}
			    					
			    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
			    					{
			    						
			    						offreService=listDetailTransferStockMP.get(j).getQuantiteOffre().setScale(0, RoundingMode.CEILING);
			    					}
			    					 
			    				
                           }else
                           {
                        	   service=listDetailTransferStockMP.get(j).getQuantite();	
                        	   
				    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
				    					{
				    						
				    						dechetService=listDetailTransferStockMP.get(j).getQuantiteDechet();
				    					}
				    					
				    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
				    					{
				    						
				    						offreService=listDetailTransferStockMP.get(j).getQuantiteOffre();
				    					}
				    					 
                           }
		    				
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SORTIE_PF))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					transfert=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);	
		    					 
			    				
                           }else
                           {
                        	   transfert=listDetailTransferStockMP.get(j).getQuantite();	
                        	  	 
                           }
		    				
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SORTIE_MP_PF))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					transfert=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);	
		    					 
			    				
                           }else
                           {
                        	   transfert=listDetailTransferStockMP.get(j).getQuantite();	
                        	  	 
                           }
		    				
		    			}
		    			
		    			
		    			if(!quantitefinale.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		    			{
			    			

		    				if(!quantitefinale.add(achat).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
			    			{
				    			//System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : Prix Moyenne :"+ achat +" * "+prixAchat+ " + " +quantitefinale +" * "+prixmoyenne +" / "+ "("+quantitefinale +achat+" )" );

		    					prixmoyenne=(achat.multiply(prixAchat).add(quantitefinale.multiply(prixmoyenne))).divide(quantitefinale.add(achat), RoundingMode.HALF_UP);

			    			}else
			    			{
			    				prixmoyenne=BigDecimal.ZERO;

			    			}
		    				
		    				
		    				//System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : quantitefinale :"+quantitefinale +" :achat "+achat +" vente: "+vente +" production :"+production + " avoir:" +avoir +" service: "+service +" transfert:"+transfert );

		    				quantitefinale=(quantitefinale.add(achat)).subtract(vente.add(production.add(avoir.add(service).add(transfert).add(offreService).add(dechetService).add(dechet).add(offreProduction))));
		    				
		    			}else
		    			{
		    				
		    				
		    				if(!initial.add(achat).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
			    			{
				    			//System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : Prix Moyenne :"+ achat +" * "+prixAchat+ " + " +initial +" * "+prixInitial +" / "+ "("+initial +" + "+achat+")" );

		    					prixmoyenne=(achat.multiply(prixAchat).add(initial.multiply(prixInitial))).divide(initial.add(achat), RoundingMode.HALF_UP);

			    			}else
			    			{
			    				prixmoyenne=BigDecimal.ZERO;

			    			}
		    				
		    				
		    				quantitefinale=(initial.add(achat)).subtract(vente.add(production.add(avoir.add(service).add(transfert).add(offreService).add(dechetService).add(dechet).add(offreProduction))));
			    			//System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : quantitefinale :"+quantitefinale +" :achat "+achat +" vente: "+vente +" production :"+production + " avoir:" +avoir +" service: "+service +" transfert:"+transfert );

		    				
		    			}
		    			

		    				
		    			}
		    			
		    			
		    			
		    			
		    			}
		    			}
		    			
		    			
		    			if(listMouvementStockMP.size()!=0)
		    			{
		    				for(int d=0;d<listMouvementStockMP.size();d++)
		    				{
		    					
		    					if(listMouvementStockMP.get(d).getMatierePremier().equals(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier()))
		    					{
		    						initial=listMouvementStockMP.get(d).getStockFinaldb();
		    						prixInitial=listMouvementStockMP.get(d).getPrixFinaldb();
		    						trouve=true;
		    						
		    					}
		    				}
		    				
		    				
		    			}
		    			if(trouve==false)
		    			{
		    				for(int l=0;l<listDetailTransferStockMPBytypetransfer.size();l++)
		    				{
		    	if(listDetailTransferStockMPBytypetransfer.get(l).getMatierePremier().equals(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier()))
		    	{
		    		initial=listDetailTransferStockMPBytypetransfer.get(l).getQuantite();
		    		
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
					/*
					 * if(!mouvementstockMP.getInitial().add(mouvementstockMP.getAchat()).setScale(
					 * 6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
					 * RoundingMode.HALF_UP))) {
					 * mouvementstockMP.setPrixFinaldb((mouvementstockMP.getAchat().multiply(
					 * mouvementstockMP.getPrixAchat()).add(mouvementstockMP.getInitial().multiply(
					 * mouvementstockMP.getPrixInitial()))).divide(mouvementstockMP.getInitial().add
					 * (mouvementstockMP.getAchat()), RoundingMode.HALF_UP));
					 * 
					 * }else { mouvementstockMP.setPrixFinaldb(BigDecimal.ZERO);
					 * 
					 * }
					 */
		    			mouvementstockMP.setPrixFinaldb(prixmoyenne);
		    			mouvementstockMP.setStockFinaldb((mouvementstockMP.getInitial().add(mouvementstockMP.getAchat())).subtract(mouvementstockMP.getVente().add(mouvementstockMP.getProduction().add(mouvementstockMP.getAvoir().add(mouvementstockMP.getService()).add(mouvementstockMP.getTransfert())))));
		    			listMouvementStockMP.add(mouvementstockMP);
		    			
		    		}
		    		
		    		// detailtransfer entre deux date et par article
		    		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && mp!=null)
		    		{
		    			listMouvementStockMPAfficher.clear();
		    			listMouvementStockMPAfficherMouvementTmp.clear();
		    			
		    		
		    		for(int i=0;i<listMouvementStockMP.size();i++)	
		    		{
		    			String ddebut=sdf.format(dateChooserdebut.getDate());
		    			String ddebutTmp=sdf.format(listMouvementStockMP.get(i).getDateCreation());
		    			
		    			if(listMouvementStockMP.get(i).getDateCreation().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut)   )
		    				
		    			{
		    			if(listMouvementStockMP.get(i).getMatierePremier().getNom().equals(mp.getNom()))
		    			{
		    				listMouvementStockMPAfficher.add(listMouvementStockMP.get(i));	
		    			}
		    				
		    			}
		    			
		    		}
		    			
		    		for(int j=0;j<listMouvementStockMPAfficher.size();j++)	
		    		{
		    			
		    			String dfin=sdf.format(dateChooserfin.getDate());
		    			String dfinTmp=sdf.format(listMouvementStockMPAfficher.get(j).getDateCreation());
		    			if(listMouvementStockMPAfficher.get(j).getDateCreation().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin) )
		    			{
		    			if(listMouvementStockMPAfficher.get(j).getMatierePremier().getNom().equals(mp.getNom()))
		    			{
		    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMPAfficher.get(j));
		    			}
		    				
		    			}
		    			
		    		}
		    		
		    			
		    		// detailtransfer entre deux date (date fin null) et par article 
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
		    		{
		    			listMouvementStockMPAfficherMouvementTmp.clear();
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    		
		    			
		    			for(int i=0;i<listMouvementStockMP.size();i++)	
			    		{
		    				String ddbut=sdf.format(listMouvementStockMP.get(i).getDateCreation());
			    			if(ddbut.equals(d1) && listMouvementStockMP.get(i).getMatierePremier().equals(mp) )
			    			{
			    			
			    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    		
		    			
		    			// detailtransfer entre deux date (date fin null)  
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
		    		{
		    			
		    			listMouvementStockMPAfficherMouvementTmp.clear();
	                      
	                      String d1=sdf.format(dateChooserdebut.getDate());
	  	    			
		    			
		    			for(int i=0;i<listMouvementStockMP.size();i++)	
			    		{
		    				String ddbut=sdf.format(listMouvementStockMP.get(i).getDateCreation());
			    			if(ddbut.equals(d1) )
			    			{
			    			
			    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    			
		    			
		    			
		    			// detailtransfer par article
		    		}else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null && mp!=null)
		    		{
		    			listMouvementStockMPAfficherMouvementTmp.clear();
		             
		               
		               
		    			for(int i=0;i<listMouvementStockMP.size();i++)	
			    		{
			    			if(listMouvementStockMP.get(i).getMatierePremier().getNom().equals(mp.getNom()) )
			    			{
			    			
			    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    			
		    			// detailtransfer entre deux date  
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && mp==null)
		    		{
		    			listMouvementStockMPAfficher.clear();
		    			listMouvementStockMPAfficherMouvementTmp.clear();
		    		
		    		for(int i=0;i<listMouvementStockMP.size();i++)	
		    		{
		    			String ddebut=sdf.format(dateChooserdebut.getDate());
		    			String ddebutTmp=sdf.format(listMouvementStockMP.get(i).getDateCreation());
		    			if(listMouvementStockMP.get(i).getDateCreation().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut))
		    			{
		    			
		    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
		    			
		    				
		    			}
		    			
		    		}
		    		for(int j=0;j<listMouvementStockMPAfficher.size();j++)	
		    		{
		    			
		    			String dfin=sdf.format(dateChooserfin.getDate());
		    			String dfinTmp=sdf.format(listMouvementStockMPAfficher.get(j).getDateCreation());
		    			
		    			if(listMouvementStockMPAfficher.get(j).getDateCreation().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin)  )
		    			{
		    			
		    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMPAfficher.get(j));
		    			
		    				
		    			}
		    			
		    		}
		    		
		    	/*	if(listMouvementStockMPAfficherTmp.size()!=0)
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficherTmp);
		    			
		    		}else
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficher);
		    		}*/
		    		
		    		}
		    			
	    		}else
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
		}

	
		
		   
		   
		   
		   
		   
		   
	
}
	
	

	  
	  public void ConsulterEtatStockPF()
	  {
		  

  		
  		detailTransferStockPFDAO.ViderSession();
  		
  		listEtatStockPFImprimer.clear();
  		listEtatStockPF.clear();
  		 List<DetailTransferProduitFini> listDetailTransferStockPFInitial =new ArrayList<DetailTransferProduitFini>();
  		 List<DetailTransferProduitFini> listDetailTransferStockPFAchat =new ArrayList<DetailTransferProduitFini>();
  		 List<DetailTransferProduitFini> listDetailTransferStockPFAchatGroupebyPF =new ArrayList<DetailTransferProduitFini>();
  		 
  		 List<DetailTransferProduitFini> listDetailTransferStockPFProduction =new ArrayList<DetailTransferProduitFini>();
  		 List<DetailTransferProduitFini> listDetailTransferStockPFProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
  		 
  		 List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new ArrayList<DetailTransferProduitFini>();
  		 List<DetailTransferProduitFini> listDetailTransferStockPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
  		 
  		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrer =new ArrayList<DetailTransferProduitFini>();
  		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
  		 
  		 
  		 List<DetailTransferProduitFini> listDetailTransferStockPFPFSortie =new ArrayList<DetailTransferProduitFini>();
  		 List<DetailTransferProduitFini> listDetailTransferStockPFPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
  		 
  		 List<DetailTransferProduitFini> listDetailTransferStockPFPFEntrer =new ArrayList<DetailTransferProduitFini>();
  		 List<DetailTransferProduitFini> listDetailTransferStockPFPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
  		
  		 
  		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerProduction =new ArrayList<DetailTransferProduitFini>();
  		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
  		 
  		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirMarajne =new ArrayList<DetailTransferProduitFini>();
  		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirMarajneGroupebyPF =new ArrayList<DetailTransferProduitFini>();
  		 
  		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoir =new ArrayList<DetailTransferProduitFini>();
  		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirGroupebyPF =new ArrayList<DetailTransferProduitFini>();
  		 
  		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirClientPF =new ArrayList<DetailTransferProduitFini>();
  		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirClientPFGroupebyPF =new ArrayList<DetailTransferProduitFini>();
  		 
  		 List<DetailTransferProduitFini> listDetailTransferStockPFAllPFTransfer =new ArrayList<DetailTransferProduitFini>();
  		
  	
  		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
  		BigDecimal montantInitial=new BigDecimal(0);
  		BigDecimal quantiteTotalInitial=new BigDecimal(0);
  		BigDecimal PrixInitial=new BigDecimal(0);
  		BigDecimal montantachat=new BigDecimal(0);
  		BigDecimal quantiteTotalachat=new BigDecimal(0);
  		BigDecimal quantiteTotalvente=new BigDecimal(0);
  		BigDecimal montantvente=new BigDecimal(0);
  		BigDecimal quantiteTotalavoir=new BigDecimal(0);
  		BigDecimal montantavoir=new BigDecimal(0);
  		BigDecimal quantiteTotalavoirClientPF=new BigDecimal(0);
  		BigDecimal montantavoirClientPF=new BigDecimal(0);
  		BigDecimal quantiteTotalEntrer=new BigDecimal(0);
  		BigDecimal montantEntrer=new BigDecimal(0);
  		BigDecimal quantiteTotalGratuit=new BigDecimal(0);
  		BigDecimal montantGratuit=new BigDecimal(0);
  		BigDecimal quantiteTotalproduction=new BigDecimal(0);
  		BigDecimal prixmoyenproduction=new BigDecimal(0);
  		BigDecimal prixmoyenachat=new BigDecimal(0);
  		BigDecimal prixmoyenvente=new BigDecimal(0);
  		BigDecimal prixmoyentransferentrer=new BigDecimal(0);
  		BigDecimal prixmoyenavoir=new BigDecimal(0);
  		BigDecimal prixmoyenavoirClientPF=new BigDecimal(0);
  		BigDecimal montantproduction=new BigDecimal(0);
  		
  		BigDecimal quantiteTotalFinale=new BigDecimal(0);
  		BigDecimal montantFinale=new BigDecimal(0);
  		boolean trouve=false;
  		FamilleArticlePF familleArticle=null;
  		Articles article=null;
  		Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
  		if(magasin!=null)
  		{
  			
	    		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
	    		{
	    			
	    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
	    			String d2=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
	    			
	    			
	    		if(!d1.equals(d2))
	    		{
	    			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
	    			{
	    				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!");
	    				return;
	    			}
	    			
	    		}
	    		
	    		if(article!=null)
	    		{
	    			titre="Etat de Stock de "+article.getLiblle()+" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
	    		}else
	    		{
	    			titre="Etat de Stock de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
	    		}
	    		
	    		}
	    		
	    				    		
	    		if(dateChooserdebut.getDate()==null )
	    		{
	    			dateChooserfin.setCalendar(null);
	    			titre="Mouvement de Stock de "+article.getLiblle()+" au magasin : "+magasin.getLibelle();
	    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article!=null)
	    		{
	    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
	    			titre="Mouvement de Stock de "+article.getLiblle() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
	    			
	    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article==null)
	    		{
	    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
	    			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d1;
	    		}
	    		
	    		listDetailTransferStockPFInitial=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_INITIAL,magasin, familleArticle);
	    		
	    		listDetailTransferStockPFAchat=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
	    		listDetailTransferStockPFAchatGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
	    		
	    		listDetailTransferStockPFProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
	    		listDetailTransferStockPFProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
	    		
	    		
	    		listDetailTransferStockPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
	    		listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
	    		
	    		
	    		listDetailTransferStockPFPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_SORTIE_PF_PF,magasin, familleArticle);
	    		listDetailTransferStockPFPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_SORTIE_PF_PF,magasin, familleArticle);
	    	
	    		

	    		listDetailTransferStockPFEntrerProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
	    		listDetailTransferStockPFEntrerProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
	    		
	    		listDetailTransferStockPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_PF_MP,magasin, familleArticle);
	    		listDetailTransferStockPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_PF_MP,magasin, familleArticle);
	    		
	    		
	    		listDetailTransferStockPFPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_PF_PF,magasin, familleArticle);
	    		listDetailTransferStockPFPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_PF_PF,magasin, familleArticle);
	    		
	    		
	    		
	    		
	    		listDetailTransferStockPFAvoir=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR,magasin, familleArticle);
	    		listDetailTransferStockPFAvoirGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR,magasin, familleArticle);
	    		
	    		listDetailTransferStockPFAvoirClientPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR_CLIENT,magasin, familleArticle);
	    		listDetailTransferStockPFAvoirClientPFGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR_CLIENT,magasin, familleArticle);

	    		
	    		
	    		listDetailTransferStockPFAvoirMarajne=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR_R,magasin, familleArticle);
	    		listDetailTransferStockPFAvoirMarajneGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR_R,magasin, familleArticle);
	    		
	    		listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);
	    		
	    		for(int d=0;d<listDetailTransferStockPFAllPFTransfer.size();d++)
	    		{
	    			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFAllPFTransfer.get(d);
	    			EtatStockPF etatstock=new EtatStockPF();
	    			etatstock.setArticle(detailtransferstockpf.getArticle());
	    			etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
	    			etatstock.setFamilleArticle(detailtransferstockpf.getSousFamille().getFamileArticlePF());
	    			etatstock.setQuantiteInitial(BigDecimal.ZERO);
	    			etatstock.setPrixInitial(BigDecimal.ZERO);
	    			etatstock.setMontantInitial(BigDecimal.ZERO);
	    			etatstock.setQuantiteAchat(BigDecimal.ZERO);
	    			etatstock.setPrixAchat(BigDecimal.ZERO);
	    			etatstock.setMontantAchat(BigDecimal.ZERO);
	    			etatstock.setQuantiteProduction(BigDecimal.ZERO);
	    			etatstock.setPrixProduction(BigDecimal.ZERO);
	    			etatstock.setMontantProduction(BigDecimal.ZERO);
	    			etatstock.setQuantiteSortie(BigDecimal.ZERO);
	    			etatstock.setPrixSortie(BigDecimal.ZERO);
	    			etatstock.setMontantSortie(BigDecimal.ZERO);
	    			etatstock.setQuantiteEntrer(BigDecimal.ZERO);
	    			etatstock.setPrixEntrer(BigDecimal.ZERO);
	    			etatstock.setMontantEntrer(BigDecimal.ZERO);
	    			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
	    			etatstock.setPrixAvoir(BigDecimal.ZERO);
	    			etatstock.setMontantAvoir(BigDecimal.ZERO);
	    			etatstock.setQuantiteAvoirClient(BigDecimal.ZERO);
	    			etatstock.setPrixAvoirClient(BigDecimal.ZERO);
	    			etatstock.setMontantAvoirClient(BigDecimal.ZERO);
	    			etatstock.setMarge(BigDecimal.ZERO);
	    			etatstock.setQuantiteGratuit(BigDecimal.ZERO);
	    			etatstock.setPrixGratuit(BigDecimal.ZERO);
	    			etatstock.setMontantGratuit(BigDecimal.ZERO);
	    			etatstock.setQuantiteFinale(BigDecimal.ZERO);
	    			etatstock.setPrixFinale(BigDecimal.ZERO);
	    			etatstock.setMontantFinale(BigDecimal.ZERO);
	    			listEtatStockPF.add(etatstock);
	    			
	    		}
	    		
	    		// charger list Etat stock Articles initialiser les enregistrement des achats et ventes par zero
	    		for(int i=0;i<listDetailTransferStockPFInitial.size();i++)
	    		{
	    			
	    			
	    			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFInitial.get(i);
	    			/*
	    			EtatStockPF etatstock=new EtatStockPF();
	    			etatstock.setArticle(detailtransferstockpf.getArticle());
	    			etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
	    			etatstock.setQuantiteInitial(detailtransferstockpf.getQuantite());
	    			*/
	    		
	    				
	    			
	    				PrixInitial=detailtransferstockpf.getPrixUnitaire();
	    			
	    				montantInitial=detailtransferstockpf.getQuantite().multiply(detailtransferstockpf.getPrixUnitaire());
	    			
	    			   quantiteTotalInitial=detailtransferstockpf.getQuantite();
	    			
	    			/*
	    			etatstock.setQuantiteAchat(BigDecimal.ZERO);
	    			etatstock.setPrixAchat(BigDecimal.ZERO);
	    			etatstock.setMontantAchat(BigDecimal.ZERO);
	    			etatstock.setQuantiteProduction(BigDecimal.ZERO);
	    			etatstock.setPrixProduction(BigDecimal.ZERO);
	    			etatstock.setMontantProduction(BigDecimal.ZERO);
	    			etatstock.setQuantiteSortie(BigDecimal.ZERO);
	    			etatstock.setPrixSortie(BigDecimal.ZERO);
	    			etatstock.setMontantSortie(BigDecimal.ZERO);
	    			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
	    			etatstock.setPrixAvoir(BigDecimal.ZERO);
	    			etatstock.setMontantAvoir(BigDecimal.ZERO);
	    			
	    			listEtatStockPF.add(etatstock);
	    			*/
	    			if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
		    		{
		    			
		    			for(int p=0;p<listEtatStockPF.size();p++)
		    	    	{
		    				if(listEtatStockPF.get(p).getArticle().equals(listDetailTransferStockPFInitial.get(i).getArticle()) && listEtatStockPF.get(p).getSousFamille().equals(listDetailTransferStockPFInitial.get(i).getSousFamille()) )
			    			{
			    				EtatStockPF etatstockpf=listEtatStockPF.get(p);
			    				etatstockpf.setQuantiteInitial(quantiteTotalInitial);
			    				etatstockpf.setPrixInitial(PrixInitial);
			    				etatstockpf.setMontantInitial(montantInitial);
			    				listEtatStockPF.set(p, etatstockpf);
			    			
			    				
			    			}
		    	    	}
		    			
		    			
		    			
		    		}
	    			
	    			
	    			
	    		}
	    		
	    		
	    		// calculer le prix moyen et quantite achat
	    		
	    		
	    	for(int j=0;j<listDetailTransferStockPFAchatGroupebyPF.size();j++)
	    	{
	    		montantachat=new BigDecimal(0);
	    		quantiteTotalachat=new BigDecimal(0);
	    		prixmoyenachat= new BigDecimal(0);
	    		
	    		boolean existe=false;
	    			
	    	for(int k=0;k<listDetailTransferStockPFAchat.size();k++)
	    	{
	    		
	    		if(listDetailTransferStockPFAchatGroupebyPF.get(j).getArticle().equals(listDetailTransferStockPFAchat.get(k).getArticle()) && listDetailTransferStockPFAchatGroupebyPF.get(j).getSousFamille().equals(listDetailTransferStockPFAchat.get(k).getSousFamille()))
	    		{
	    			montantachat=montantachat.add(listDetailTransferStockPFAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFAchat.get(k).getQuantite()));
  				prixmoyenachat=((prixmoyenachat.multiply(quantiteTotalachat)).add(listDetailTransferStockPFAchat.get(k).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFAchat.get(k).getQuantite()))).divide(quantiteTotalachat.add(listDetailTransferStockPFAchat.get(k).getQuantite()),6, RoundingMode.DOWN) ;

	    			quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockPFAchat.get(k).getQuantite());
	    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
	    			
	    		}
	    		
	    		
	    	}
	    		if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
	    		{
	    			
	    			for(int i=0;i<listEtatStockPF.size();i++)
	    	    	{
	    				if(listEtatStockPF.get(i).getArticle().equals(listDetailTransferStockPFAchatGroupebyPF.get(j).getArticle()) && listEtatStockPF.get(i).getSousFamille().equals(listDetailTransferStockPFAchatGroupebyPF.get(j).getSousFamille()) )
		    			{
		    				EtatStockPF etatstockpf=listEtatStockPF.get(i);
		    				etatstockpf.setQuantiteAchat(quantiteTotalachat);
		    				etatstockpf.setPrixAchat((prixmoyenachat));
		    				etatstockpf.setMontantAchat(etatstockpf.getQuantiteAchat().multiply(etatstockpf.getPrixAchat()));
		    				listEtatStockPF.set(i, etatstockpf);
		    			
		    				
		    			}
	    	    	}
	    			
	    			
	    			
	    		}
	    		
	    	}
	    	
	    	
  	// calculer Quantite Production et le prix moyen
	    	
	    
	    	
	    	for(int i=0;i<listDetailTransferStockPFProductionGroupebyPF.size();i++)
	    	{
	    		quantiteTotalproduction=BigDecimal.ZERO;
	    		montantproduction=new BigDecimal(0);
	    		prixmoyenproduction=BigDecimal.ZERO;
	    		
	    		for(int j=0;j<listDetailTransferStockPFProduction.size();j++)
	    		{
	    			
	    			if(listDetailTransferStockPFProductionGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFProduction.get(j).getArticle()) && listDetailTransferStockPFProductionGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFProduction.get(j).getSousFamille()))
	    			{
	    				
	    				montantproduction=montantproduction.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPFProduction.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFProduction.get(j).getQuantite())).setScale(6, RoundingMode.DOWN);
	    				
	    				prixmoyenproduction=((prixmoyenproduction.multiply(quantiteTotalproduction)).add(listDetailTransferStockPFProduction.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFProduction.get(j).getQuantite()))).divide(quantiteTotalproduction.add(listDetailTransferStockPFProduction.get(j).getQuantite()),6, RoundingMode.DOWN) ;
	    				quantiteTotalproduction=quantiteTotalproduction.add(listDetailTransferStockPFProduction.get(j).getQuantite());
	    				
	    			
	    				
	    			}
	    			
	    			
	    		}
	    		
	    		if(!montantproduction.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !quantiteTotalproduction.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
	    		{
	    			
	    			
	    		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
	    	    		
	    	    	
	    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFProductionGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFProductionGroupebyPF.get(i).getSousFamille()))
	    	    		{
	    	    			
	    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    	    			etatstockpf.setQuantiteProduction(quantiteTotalproduction);
	    	    			etatstockpf.setPrixProduction(prixmoyenproduction);
	    	    			etatstockpf.setMontantProduction(quantiteTotalproduction.multiply(prixmoyenproduction));
	    	    			
	    	    			listEtatStockPF.set(k, etatstockpf);
	    	    			

	    	    			
	    	    		}
	    	    		
	    	    		
	    	    	}
	    			
	    		}
	    		
	    		
	    		
	    	}
	    	
	    	
	    	
	    	
	    	// calculer Quantite Vente et le prix moyen
	    	
	    	
	    	
	    	for(int i=0;i<listDetailTransferStockPFSortieGroupebyPF.size();i++)
	    	{
	    		quantiteTotalvente=BigDecimal.ZERO;
	    		quantiteTotalGratuit=BigDecimal.ZERO;
	    		montantvente=new BigDecimal(0);
	    		prixmoyenvente=new BigDecimal(0);
	    		
	    		for(int j=0;j<listDetailTransferStockPFSortie.size();j++)
	    		{
	    			
	    			if(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFSortie.get(j).getArticle()) && listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFSortie.get(j).getSousFamille()))
	    			{
	    				
	    				
	    				// la quantite Gratuité
	    				if(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
	    				{
	    					
	    					quantiteTotalGratuit=quantiteTotalGratuit.add(listDetailTransferStockPFSortie.get(j).getQuantite());
	    					
	    					
	    					
	    				}else
	    				{
	    					
	    					// la quantite Vente
	    					
	    					montantvente=montantvente.add(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFSortie.get(j).getQuantite()));
		    				prixmoyenvente=((prixmoyenvente.multiply(quantiteTotalvente)).add(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFSortie.get(j).getQuantite()))).divide(quantiteTotalvente.add(listDetailTransferStockPFSortie.get(j).getQuantite()),6, RoundingMode.DOWN) ;

	    					quantiteTotalvente=quantiteTotalvente.add(listDetailTransferStockPFSortie.get(j).getQuantite());
	    					
	    					
	    				}
	    				
	    				
	    			}
	    			
	    			
	    		}
	    		
	    		if(!quantiteTotalvente.equals(BigDecimal.ZERO))
	    		{
	    			
	    			
	    		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
	    	    		
	    	    	
	    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille()))
	    	    		{
	    	    			
	    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    	    			etatstockpf.setQuantiteSortie(quantiteTotalvente);
	    	    			etatstockpf.setPrixSortie(prixmoyenvente);
	    	    			etatstockpf.setMontantSortie(quantiteTotalvente.multiply(prixmoyenvente));
	    	    		
	    	    			listEtatStockPF.set(k, etatstockpf);
	    	    		}
	    	    		
	    	    		
	    	    	}
	    			
	    			
	    		}
	    		
	    		
	    		if(!quantiteTotalGratuit.equals(BigDecimal.ZERO))
	    		{
	    			
	    			
	    		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
	    	    		
	    	    	
	    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille()))
	    	    		{
	    	    			
	    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    	    			etatstockpf.setQuantiteGratuit(quantiteTotalGratuit.setScale(6));
	    	    			if(etatstockpf.getPrixAchat().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
	    	    			{
	    	    				etatstockpf.setPrixGratuit(etatstockpf.getPrixInitial().setScale(6));
	    	    			}else
	    	    			{
	    	    				etatstockpf.setPrixGratuit(etatstockpf.getPrixAchat().setScale(6));
	    	    			}
	    	    			
	    	    			etatstockpf.setMontantGratuit(etatstockpf.getQuantiteGratuit().setScale(6).multiply(etatstockpf.getPrixGratuit().setScale(6)));;
	    	    			
	    	    			 // ajouter la quantite gratuité et le prix gratuité(prix d'achat)
	    	    			
	    	    			
	    	    			listEtatStockPF.set(k, etatstockpf);
	    	    		}
	    	    		
	    	    	}
	    			
	    			
	    		}
	    		
	    		
	    	}
	    	
	    	
	    	
	    	// calculer Quantite Transfer Sorte et le prix moyen (Les PF transfer En PF )
	    	
	    	
	    	
	    	for(int i=0;i<listDetailTransferStockPFPFSortieGroupebyPF.size();i++)
	    	{
	    		quantiteTotalvente=BigDecimal.ZERO;
	    		quantiteTotalGratuit=BigDecimal.ZERO;
	    		montantvente=new BigDecimal(0);
	    		prixmoyenvente=new BigDecimal(0);
	    		
	    		for(int j=0;j<listDetailTransferStockPFPFSortie.size();j++)
	    		{
	    			
	    			if(listDetailTransferStockPFPFSortieGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFPFSortie.get(j).getArticle()) && listDetailTransferStockPFPFSortieGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFPFSortie.get(j).getSousFamille()))
	    			{
	    				
	    				 
	    					
	    					montantvente=montantvente.add(listDetailTransferStockPFPFSortie.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFPFSortie.get(j).getQuantite()));
		    				prixmoyenvente=((prixmoyenvente.multiply(quantiteTotalvente)).add(listDetailTransferStockPFPFSortie.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFPFSortie.get(j).getQuantite()))).divide(quantiteTotalvente.add(listDetailTransferStockPFPFSortie.get(j).getQuantite()),6, RoundingMode.DOWN) ;

	    					quantiteTotalvente=quantiteTotalvente.add(listDetailTransferStockPFPFSortie.get(j).getQuantite());
	    					
	    				 
	    				
	    				
	    			}
	    			
	    			
	    		}
	    		
	    		if(!quantiteTotalvente.equals(BigDecimal.ZERO))
	    		{
	    			
	    			
	    		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
	    	    		
	    	    	
	    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFPFSortieGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFPFSortieGroupebyPF.get(i).getSousFamille()))
	    	    		{
	    	    			
	    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    	    			etatstockpf.setQuantiteSortie(quantiteTotalvente);
	    	    			etatstockpf.setPrixSortie(prixmoyenvente);
	    	    			etatstockpf.setMontantSortie(quantiteTotalvente.multiply(prixmoyenvente));
	    	    		
	    	    			listEtatStockPF.set(k, etatstockpf);
	    	    		}
	    	    		
	    	    		
	    	    	}
	    			
	    			
	    		}
	    		
	    		
	    		
	    		
	    	}    	
	    	
	    	
	    	
	    	
	    	
	// calculer Quantite Transfer Entrer et le prix moyen les MP Transfert EN PF vien de Production
	    	
	    	
	    	
	    	for(int i=0;i<listDetailTransferStockPFEntrerProductionGroupebyPF.size();i++)
	    	{
	    		quantiteTotalEntrer=BigDecimal.ZERO;
	    		montantEntrer=new BigDecimal(0);
	    		prixmoyentransferentrer=new BigDecimal(0);
	    		
	    		for(int j=0;j<listDetailTransferStockPFEntrerProduction.size();j++)
	    		{
	    			
	    			if(listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFEntrerProduction.get(j).getArticle().getId() && listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getSousFamille().getId()==listDetailTransferStockPFEntrerProduction.get(j).getSousFamille().getId())
	    			{
	    				
	    				montantEntrer=montantEntrer.add(listDetailTransferStockPFEntrerProduction.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFEntrerProduction.get(j).getQuantite()));
	    				prixmoyentransferentrer=((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(listDetailTransferStockPFEntrerProduction.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFEntrerProduction.get(j).getQuantite()))).divide(quantiteTotalEntrer.add(listDetailTransferStockPFEntrerProduction.get(j).getQuantite()),6, RoundingMode.DOWN) ;

	    				quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrerProduction.get(j).getQuantite());
	    				
	    				
	    			}
	    			
	    			
	    		}
	    		
	    		if(!quantiteTotalEntrer.equals(BigDecimal.ZERO))
	    		{
	    			
	    			
	    		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
	    	    		
	    	    	
	    	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getSousFamille().getId())
	    	    		{
	    	    			
	    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    	    			
	    	    			etatstockpf.setPrixEntrer(((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(etatstockpf.getPrixEntrer().setScale(6, RoundingMode.DOWN).multiply(etatstockpf.getQuantiteEntrer()))).divide(quantiteTotalEntrer.add(etatstockpf.getQuantiteEntrer()),6, RoundingMode.DOWN));

	    	    			etatstockpf.setQuantiteEntrer(etatstockpf.getQuantiteEntrer().add(quantiteTotalEntrer) );
	    	    			
	    	    			etatstockpf.setMontantEntrer(etatstockpf.getQuantiteEntrer().multiply(etatstockpf.getPrixEntrer()));
	    	    			
	    	    			listEtatStockPF.set(k, etatstockpf);
	    	    		}
	    	    		
	    	    		
	    	    	}
	    			
	    			
	    		}
	    		
	    	}
	    	
	    	
	// calculer Quantite Transfer Entrer et le prix moyen (Les MP transfer En PF )
	    	
	    	
	    	
	    	for(int i=0;i<listDetailTransferStockPFEntrerGroupebyPF.size();i++)
	    	{
	    		quantiteTotalEntrer=BigDecimal.ZERO;
	    		montantEntrer=new BigDecimal(0);
	    		prixmoyentransferentrer=new BigDecimal(0);
	    		
	    		for(int j=0;j<listDetailTransferStockPFEntrer.size();j++)
	    		{
	    			
	    			if(listDetailTransferStockPFEntrerGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFEntrer.get(j).getArticle().getId() && listDetailTransferStockPFEntrerGroupebyPF.get(i).getSousFamille().getId()== listDetailTransferStockPFEntrer.get(j).getSousFamille().getId())
	    			{
	    				
	    				montantEntrer=montantEntrer.add(listDetailTransferStockPFEntrer.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFEntrer.get(j).getQuantite()));
	    				prixmoyentransferentrer=((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(listDetailTransferStockPFEntrer.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFEntrer.get(j).getQuantite()))).divide(quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(j).getQuantite()),6, RoundingMode.DOWN) ;

	    				quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(j).getQuantite());
	    				
	    				
	    			}
	    			
	    			
	    		}
	    		
	    		if(!quantiteTotalEntrer.equals(BigDecimal.ZERO))
	    		{
	    			
	    			
	    		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
	    	    		
	    	    	
	    	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFEntrerGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFEntrerGroupebyPF.get(i).getSousFamille().getId())
	    	    		{
	    	    			
	    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    	    			
	    	    			etatstockpf.setPrixEntrer(((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(etatstockpf.getPrixEntrer().setScale(6, RoundingMode.DOWN).multiply(etatstockpf.getQuantiteEntrer()))).divide(quantiteTotalEntrer.add(etatstockpf.getQuantiteEntrer()),6, RoundingMode.DOWN));

	    	    			etatstockpf.setQuantiteEntrer(etatstockpf.getQuantiteEntrer().add(quantiteTotalEntrer) );
	    	    			
	    	    			etatstockpf.setMontantEntrer(etatstockpf.getQuantiteEntrer().multiply(etatstockpf.getPrixEntrer()));
	    	    			    			
	    	    			listEtatStockPF.set(k, etatstockpf);
	    	    		}
	    	    		
	    	    		
	    	    	}
	    			
	    			
	    		}
	    		
	    	}    	
	    	
	    	
	     	
	    	// calculer Quantite Transfer Entrer et le prix moyen (Les PF transfer En PF )
	    	    	
	    	    	
	    	    	
	    	    	for(int i=0;i<listDetailTransferStockPFPFEntrerGroupebyPF.size();i++)
	    	    	{
	    	    		quantiteTotalEntrer=BigDecimal.ZERO;
	    	    		montantEntrer=new BigDecimal(0);
	    	    		prixmoyentransferentrer=new BigDecimal(0);
	    	    		
	    	    		for(int j=0;j<listDetailTransferStockPFPFEntrer.size();j++)
	    	    		{
	    	    			
	    	    			if(listDetailTransferStockPFPFEntrerGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFPFEntrer.get(j).getArticle().getId() && listDetailTransferStockPFPFEntrerGroupebyPF.get(i).getSousFamille().getId()== listDetailTransferStockPFPFEntrer.get(j).getSousFamille().getId())
	    	    			{
	    	    				
	    	    				montantEntrer=montantEntrer.add(listDetailTransferStockPFPFEntrer.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFPFEntrer.get(j).getQuantite()));
	    	    				prixmoyentransferentrer=((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(listDetailTransferStockPFPFEntrer.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFPFEntrer.get(j).getQuantite()))).divide(quantiteTotalEntrer.add(listDetailTransferStockPFPFEntrer.get(j).getQuantite()),6, RoundingMode.DOWN) ;

	    	    				quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFPFEntrer.get(j).getQuantite());
	    	    				
	    	    				
	    	    			}
	    	    			
	    	    			
	    	    		}
	    	    		
	    	    		if(!quantiteTotalEntrer.equals(BigDecimal.ZERO))
	    	    		{
	    	    			
	    	    			
	    	    		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	    	{
	    	    	    		
	    	    	    	
	    	    	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFPFEntrerGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFPFEntrerGroupebyPF.get(i).getSousFamille().getId())
	    	    	    		{
	    	    	    			
	    	    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    	    	    			
	    	    	    			etatstockpf.setPrixEntrer(((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(etatstockpf.getPrixEntrer().setScale(6, RoundingMode.DOWN).multiply(etatstockpf.getQuantiteEntrer()))).divide(quantiteTotalEntrer.add(etatstockpf.getQuantiteEntrer()),6, RoundingMode.DOWN));

	    	    	    			etatstockpf.setQuantiteEntrer(etatstockpf.getQuantiteEntrer().add(quantiteTotalEntrer) );
	    	    	    			
	    	    	    			etatstockpf.setMontantEntrer(etatstockpf.getQuantiteEntrer().multiply(etatstockpf.getPrixEntrer()));
	    	    	    			    			
	    	    	    			listEtatStockPF.set(k, etatstockpf);
	    	    	    		}
	    	    	    		
	    	    	    		
	    	    	    	}
	    	    			
	    	    			
	    	    		}
	    	    		
	    	    	}    	
	    	    		
	    	
	    	
	    	
	    	
// calculer Quantite avoir et le prix moyen
	    	
	    	
	    	
	    	for(int i=0;i<listDetailTransferStockPFAvoirGroupebyPF.size();i++)
	    	{
	    		quantiteTotalavoir=BigDecimal.ZERO;
	    		montantavoir=new BigDecimal(0);
	    		prixmoyenavoir=BigDecimal.ZERO;
	    		for(int j=0;j<listDetailTransferStockPFAvoir.size();j++)
	    		{
	    			
	    			if(listDetailTransferStockPFAvoirGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFAvoir.get(j).getArticle().getId() && listDetailTransferStockPFAvoirGroupebyPF.get(i).getSousFamille().getId()== listDetailTransferStockPFAvoir.get(j).getSousFamille().getId() && listDetailTransferStockPFAvoir.get(j).getSousFamille().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_EMBALLAGE))
	    			{
	    				
	    				montantavoir=montantavoir.add(listDetailTransferStockPFAvoir.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoir.get(j).getQuantite()));
	    				prixmoyenavoir=((prixmoyenavoir.multiply(quantiteTotalavoir)).add(listDetailTransferStockPFAvoir.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFAvoir.get(j).getQuantite()))).divide(quantiteTotalavoir.add(listDetailTransferStockPFAvoir.get(j).getQuantite()),6, RoundingMode.DOWN) ;

	    				quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockPFAvoir.get(j).getQuantite());
	    				
	    				
	    			}
	    			
	    			
	    		}
	    		
	    		if(!quantiteTotalavoir.equals(BigDecimal.ZERO))
	    		{
	    			
	    			
	    		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
	    	    		
	    	    	
	    	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFAvoirGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFAvoirGroupebyPF.get(i).getSousFamille().getId() && listEtatStockPF.get(k).getSousFamille().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_EMBALLAGE))
	    	    		{
	    	    			
	    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    	    			etatstockpf.setQuantiteAvoir(quantiteTotalavoir);
	    	    			etatstockpf.setPrixAvoir(prixmoyenavoir);
	    	    			etatstockpf.setMontantAvoir(quantiteTotalavoir.multiply(prixmoyenavoir));
	    	    			
	    	    			listEtatStockPF.set(k, etatstockpf);
	    	    		}
	    	    		
	    	    		
	    	    	}
	    			
	    			
	    		}
	    		
	    		
	    		
	    	}
	    	
	    	
// calculer Quantite avoir Client PF et le prix moyen
	    	
	    	
	    	
	    	for(int i=0;i<listDetailTransferStockPFAvoirClientPFGroupebyPF.size();i++)
	    	{
	    		quantiteTotalavoir=BigDecimal.ZERO;
	    		montantavoir=new BigDecimal(0);
	    		prixmoyenavoir=BigDecimal.ZERO;
	    		for(int j=0;j<listDetailTransferStockPFAvoirClientPF.size();j++)
	    		{
	    			
	    			if(listDetailTransferStockPFAvoirClientPFGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFAvoirClientPF.get(j).getArticle().getId() && listDetailTransferStockPFAvoirClientPFGroupebyPF.get(i).getSousFamille().getId()== listDetailTransferStockPFAvoirClientPF.get(j).getSousFamille().getId())
	    			{
	    				
	    				montantavoir=montantavoir.add(listDetailTransferStockPFAvoirClientPF.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoirClientPF.get(j).getQuantite()));
	    				prixmoyenavoir=((prixmoyenavoir.multiply(quantiteTotalavoir)).add(listDetailTransferStockPFAvoirClientPF.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFAvoirClientPF.get(j).getQuantite()))).divide(quantiteTotalavoir.add(listDetailTransferStockPFAvoirClientPF.get(j).getQuantite()),6, RoundingMode.DOWN) ;

	    				quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockPFAvoirClientPF.get(j).getQuantite());
	    				
	    				
	    			}
	    			
	    			
	    		}
	    		
	    		if(!quantiteTotalavoir.equals(BigDecimal.ZERO))
	    		{
	    			
	    			
	    		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
	    	    		
	    	    	
	    	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFAvoirClientPFGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFAvoirClientPFGroupebyPF.get(i).getSousFamille().getId() )
	    	    		{
	    	    			
	    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    	    			etatstockpf.setQuantiteAvoirClient (quantiteTotalavoir);
	    	    			etatstockpf.setPrixAvoirClient(prixmoyenavoir);
	    	    			etatstockpf.setMontantAvoirClient(quantiteTotalavoir.multiply(prixmoyenavoir));
	    	    			
	    	    			listEtatStockPF.set(k, etatstockpf);
	    	    			
	    	    			
	    	    			
	    	    			
	    	    		}
	    	    		
	    	    		
	    	    	}
	    			
	    			
	    		}
	    		
	    		
	    		
	    	}	    	
	    	
// calculer Quantite avoir Marjane et le prix moyen
	    	
	    	
	    	
	    	for(int i=0;i<listDetailTransferStockPFAvoirMarajneGroupebyPF.size();i++)
	    	{
	    		quantiteTotalavoir=BigDecimal.ZERO;
	    		montantavoir=new BigDecimal(0);
	    		prixmoyenavoir=BigDecimal.ZERO;
	    		for(int j=0;j<listDetailTransferStockPFAvoirMarajne.size();j++)
	    		{
	    			
	    			if(listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFAvoirMarajne.get(j).getArticle()) && listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFAvoirMarajne.get(j).getSousFamille()) && listDetailTransferStockPFAvoirMarajne.get(j).getTransferStockPF().getCodeTransfer().contains("_R"))
	    			{
	    				
	    				montantavoir=montantavoir.add(listDetailTransferStockPFAvoirMarajne.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite()));
	    				prixmoyenavoir=((prixmoyenavoir.multiply(quantiteTotalavoir)).add(listDetailTransferStockPFAvoirMarajne.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite()))).divide(quantiteTotalavoir.add(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite()),6, RoundingMode.DOWN) ;

	    				quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite());
	    				
	    				
	    			}
	    			
	    			
	    		}
	    		
	    		if(!quantiteTotalavoir.equals(BigDecimal.ZERO))
	    		{
	    			
	    			
	    		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
	    	    		
	    	    	
	    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getSousFamille()) )
	    	    		{
	    	    			
	    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    	    			etatstockpf.setQuantiteAvoir(quantiteTotalavoir);
	    	    			etatstockpf.setPrixAvoir(prixmoyenavoir);
	    	    			etatstockpf.setMontantAvoir(quantiteTotalavoir.multiply(prixmoyenavoir));
	    	    			
	    	    			listEtatStockPF.set(k, etatstockpf);
	    	    		}
	    	    		
	    	    		
	    	    	}
	    			
	    			
	    		}
	    		
	    		
	    		
	    	} 	
	    	
	    	
	    	
	    	
	    	
	    	
	    	Calculer_Prix_Moyenne_PF(); // Calculer le Prix Moyen a partir de mouvement de stock 
	    	
	    	
	    	// Calculer Stock Finale
	    	
	    	
		   	for(int i=0;i<listEtatStockPF.size();i++)
	    	{
		   	 BigDecimal prixMoyen=BigDecimal.ZERO;
		   	 BigDecimal QuantiteTotal=BigDecimal.ZERO;
		   		   quantiteTotalFinale=BigDecimal.ZERO;
		   		    montantFinale=BigDecimal.ZERO;
	    			EtatStockPF etatstockpf=listEtatStockPF.get(i);
	    			etatstockpf.setQuantiteFinale((etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer()).add(etatstockpf.getQuantiteAvoirClient()))).subtract(etatstockpf.getQuantiteSortie().add(etatstockpf.getQuantiteAvoir()).add(etatstockpf.getQuantiteGratuit())));
	    			quantiteTotalFinale=quantiteTotalFinale.add(etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer()).add(etatstockpf.getQuantiteAvoirClient())).subtract(etatstockpf.getQuantiteAvoir().add(etatstockpf.getQuantiteSortie()).add(etatstockpf.getQuantiteGratuit())));
	    			montantFinale=etatstockpf.getMontantInitial().add(etatstockpf.getMontantAchat().add(etatstockpf.getMontantProduction().add(etatstockpf.getMontantEntrer()).add(etatstockpf.getMontantAvoirClient())));
	    			QuantiteTotal=etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction().add(etatstockpf.getQuantiteEntrer()).add(etatstockpf.getQuantiteAvoirClient())));
					/*
					 * if(!QuantiteTotal.setScale(2,
					 * RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2,
					 * RoundingMode.HALF_UP))) { prixMoyen=montantFinale.divide(QuantiteTotal, 6,
					 * RoundingMode.DOWN); etatstockpf.setPrixFinale(prixMoyen.setScale(6,
					 * RoundingMode.DOWN));
					 * etatstockpf.setMontantFinale(etatstockpf.getPrixFinale().multiply(etatstockpf
					 * .getQuantiteFinale())); }else {
					 * etatstockpf.setPrixFinale(prixMoyen.setScale(6, RoundingMode.DOWN));
					 * 
					 * }
					 */
	    			
	    			if(listMouvementStockPFAfficherTmp.size()!=0)
		    		{
		    			for(int l=0;l<listMouvementStockPFAfficherTmp.size();l++)
		    			{
		    				
		    				
		    				
		    				if(etatstockpf.getArticle().getId() ==listMouvementStockPFAfficherTmp.get(l).getArticle().getId() && etatstockpf.getSousFamille().getId()==listMouvementStockPFAfficherTmp.get(l).getSousFamille().getId())
		    				{
		    					
		    				
		    					
		    					prixMoyen=listMouvementStockPFAfficherTmp.get(l).getPrixFinal();
		    					//System.out.println(listMouvementStockPFAfficherTmp.get(l).getArticle().getLiblle() + listMouvementStockPFAfficherTmp.get(l).getSousFamille().getLiblle() +" : Initial "+listMouvementStockPFAfficherTmp.get(l).getInitial() + " Prix Initial: "+listMouvementStockPFAfficherTmp.get(l).getPrixInitial()+ " Achat : "+listMouvementStockPFAfficherTmp.get(l).getAchat() + " prix Achat : "+listMouvementStockPFAfficherTmp.get(l).getPrixAchat()+ " Production : "+listMouvementStockPFAfficherTmp.get(l).getEntrerProduction() + " Transfer Enter : "+listMouvementStockPFAfficherTmp.get(l).getTransferEntrer() + " Vente : "+listMouvementStockPFAfficherTmp.get(l).getVente() + " Quantite Finale : "+listMouvementStockPFAfficherTmp.get(l).getStockFinal() + "  prix Moyen : "+listMouvementStockPFAfficherTmp.get(l).getPrixFinal() ) ;
		    					
		    				}
		    				
		    			}
		    			
		    		}else
		    		{
		    			for(int l=0;l<listMouvementStockPFAfficher.size();l++)
		    			{
		    				if(etatstockpf.getArticle().getId() ==listMouvementStockPFAfficher.get(l).getArticle().getId() && etatstockpf.getSousFamille().getId()==listMouvementStockPFAfficher.get(l).getSousFamille().getId())
		    				{
		    					
		    				
		    					
		    					prixMoyen=listMouvementStockPFAfficher.get(l).getPrixFinal();
		    					//System.out.println(listMouvementStockPFAfficher.get(l).getArticle().getLiblle() + listMouvementStockPFAfficher.get(l).getSousFamille().getLiblle() +" : Initial "+listMouvementStockPFAfficher.get(l).getInitial() + " Prix Initial: "+listMouvementStockPFAfficher.get(l).getPrixInitial()+ " Achat : "+listMouvementStockPFAfficher.get(l).getAchat() + " prix Achat : "+listMouvementStockPFAfficher.get(l).getPrixAchat()+ " Production : "+listMouvementStockPFAfficher.get(l).getEntrerProduction() + " Transfer Enter : "+listMouvementStockPFAfficher.get(l).getTransferEntrer()+ " Vente : "+listMouvementStockPFAfficher.get(l).getVente() + " Quantite Finale : "+listMouvementStockPFAfficher.get(l).getStockFinal() + "  prix Moyen : "+listMouvementStockPFAfficher.get(l).getPrixFinal() ) ;
		    					
		    				}
		    			}
		    			
		    		}
	    			
	    			
	    			if(etatstockpf.getQuantiteFinale().compareTo(BigDecimal.ZERO)==0)
	    			{
	    				prixMoyen=BigDecimal.ZERO;
	    			}
	    			
	    			etatstockpf.setPrixFinale(prixMoyen);
		    		etatstockpf.setMontantFinale(((etatstockpf.getQuantiteFinale())).multiply(etatstockpf.getPrixFinale()));
	    		
	    			
	    			
	    			if(etatstockpf.getPrixSortie().setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,  RoundingMode.DOWN)))
	    			{
	    				etatstockpf.setMarge(BigDecimal.ZERO);
	    				
	    			}else
	    			{
	    				etatstockpf.setMarge((etatstockpf.getPrixSortie().subtract(etatstockpf.getPrixInitial())).divide(etatstockpf.getPrixSortie(), RoundingMode.DOWN));
	    			}
	    			
	    			
	    			listEtatStockPF.set(i, etatstockpf);
	    		
	    		
	    	}
		   	
		   	for(int k=0;k<listEtatStockPF.size();k++)
		   	{
		   		
		   	EtatStockPF etatStockPF=listEtatStockPF.get(k);
		   	
		   	if(etatStockPF.getArticle().getCodeArticle().contains(CODE_PRODUIT_FINI_OFFRE))
		   	{
		   		etatStockPF.setFamilleArticle(FamilleOffre);
		   	}
		   	
		   	
		   		if(etatStockPF.getQuantiteInitial().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteAchat().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteAvoir().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteEntrer().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteProduction().setScale(6).equals(BigDecimal.ZERO.setScale(6)) &&  etatStockPF.getQuantiteSortie().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteFinale().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
		   		{
		   			
		   		}else
		   		{
		   			listEtatStockPFImprimer.add(etatStockPF);
		   		}
		   		
		   	}
		   	
	    	//afficher_tableEtatStock(listEtatStockPFImprimer);
	    	
	    	
  			
  		}else
  		{
  			

  			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
  			return;
  		}
  		
  	
  	  
		  
		  
		  
		  
	  }
	  
	  
		void Calculer_Prix_Moyenne_PF()
		{
			

			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			listMouvementStockPF.clear();
			detailTransferStockPFDAO.ViderSession();
			boolean trouve=false;
			Articles article=null;
			Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
			if(magasin!=null)
			{
				
		   		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
	    		{
	    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
	    			String d2=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
	    			
	    		if(!d1.equals(d2))
	    		{
	    			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
	    			{
	    				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!");
	    				return;
	    			}
	    			
	    		}
	    		
	    		if(article!=null)
	    		{
	    			titre="Mouvement de Stock de "+article.getLiblle() +" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
	    		}else
	    		{
	    			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
	    		}
	    		
	    		}
	    		
	    		if(dateChooserdebut.getDate()==null )
	    		{
	    			dateChooserfin.setCalendar(null);
	    			titre="Mouvement de Stock de "+article.getLiblle()+" au magasin : "+magasin.getLibelle();
	    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article!=null)
	    		{
	    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
	    			titre="Mouvement de Stock de "+article.getLiblle() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
	    			
	    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article==null)
	    		{
	    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
	    			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ "entre "+d1 +" et "+d1;
	    		}
	    		
	    		listDetailTransferStockPF=detailTransferStockPFDAO.findAllTransferStockPFOrderByDateTransfer(magasin);
	    		listDetailTransferStockPFGroupebyArticle=detailTransferStockPFDAO.findAllTransferStockPFGroupeByDateTransferByArticle(magasin);
	    		listDetailTransferStockPFBytypetransfer=detailTransferStockPFDAO.findBytypetransfer(ETAT_TRANSFER_STOCK_INITIAL,magasin);
	    		String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_VENTE,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,Constantes.ETAT_TRANSFER_STOCK_AVOIR ,ETAT_TRANSFER_STOCK_ENTRER_MP,ETAT_TRANSFER_STOCK_ENTRER_PF_MP,ETAT_TRANSFER_STOCK_AVOIR_R,ETAT_TRANSFER_STOCK_AVOIR_CLIENT,ETAT_TRANSFER_STOCK_ENTRER_PF_PF,ETAT_TRANSFER_STOCK_SORTIE_PF_PF};
	    		BigDecimal achat=BigDecimal.ZERO;
	    		BigDecimal Prixachat=BigDecimal.ZERO;
	    		BigDecimal vente=BigDecimal.ZERO;
	    		BigDecimal TransferEntrer=BigDecimal.ZERO;
	    		BigDecimal PrixTransferEntrer=BigDecimal.ZERO;
	    		BigDecimal avoir=BigDecimal.ZERO;
	    		BigDecimal avoirClientPF=BigDecimal.ZERO;
	    		BigDecimal initial=BigDecimal.ZERO;
	    		BigDecimal prixinitial=BigDecimal.ZERO;
	    		BigDecimal production=BigDecimal.ZERO;
	    		BigDecimal Prixproduction=BigDecimal.ZERO;
	    		BigDecimal PrixAvoirClientPF=BigDecimal.ZERO;
	    		BigDecimal stockfinal=BigDecimal.ZERO;
	    		BigDecimal prixmoyenne=BigDecimal.ZERO;
	    		BigDecimal quantitefinale=BigDecimal.ZERO;
	    		for(int i=0;i<listDetailTransferStockPFGroupebyArticle.size();i++)
	    		{
	    			
	    			achat=new BigDecimal(0);
	    			vente=new BigDecimal(0);
	    			initial=new BigDecimal(0);
	    			production=new BigDecimal(0);
	    			stockfinal=new BigDecimal(0);
	    			avoir=new BigDecimal(0);
	    			avoirClientPF=new BigDecimal(0);
	    			TransferEntrer=new BigDecimal(0);
	    			Prixachat=new BigDecimal(0);
	    			 PrixTransferEntrer=new BigDecimal(0);
	    			 prixinitial=new BigDecimal(0);
	    			 Prixproduction=new BigDecimal(0);
	    			 PrixAvoirClientPF=new BigDecimal(0);
	    			
	    				if(i!=0)
		    			{
		    				if(listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() != listDetailTransferStockPFGroupebyArticle.get(i-1).getArticle().getId())
		    				{
		    					prixmoyenne=new BigDecimal(0);
				    			quantitefinale=new BigDecimal(0);
		    					
		    				}
		    				if(listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() == listDetailTransferStockPFGroupebyArticle.get(i-1).getArticle().getId())
		    				{
		    				if( listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId() != listDetailTransferStockPFGroupebyArticle.get(i-1).getSousFamille().getId())
		    				{
		    					prixmoyenne=new BigDecimal(0);
				    			quantitefinale=new BigDecimal(0);
		    				}
		    				}
		    				
		    				
		    			}else
		    			{
		    				prixmoyenne=new BigDecimal(0);
			    			quantitefinale=new BigDecimal(0);
		    			}
	    			 
	    			 
	    			 
	    			for(int j=0;j<listDetailTransferStockPF.size();j++)
	    			{
	    				

	    				for(int k=0;k<typetransfer.length;k++)
	        			{
	    					
	    				
	    			if(listDetailTransferStockPFGroupebyArticle.get(i).getTransferStockPF().getDateTransfer().equals(listDetailTransferStockPF.get(j).getTransferStockPF().getDateTransfer()) 
	    					&& listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId()== listDetailTransferStockPF.get(j).getArticle().getId() && listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId()== listDetailTransferStockPF.get(j).getSousFamille().getId() && listDetailTransferStockPF.get(j).getTypeTransfer().equals(typetransfer[k]))	
	    			{
	    				
	    			
	    		
	    			
	    				
	    				achat=new BigDecimal(0);
	        			vente=new BigDecimal(0);
	        			initial=new BigDecimal(0);
	        			production=new BigDecimal(0);
	        			stockfinal=new BigDecimal(0);
	        			avoir=new BigDecimal(0);
	        			avoirClientPF=new BigDecimal(0);
	        			TransferEntrer=new BigDecimal(0);
	        			Prixachat=new BigDecimal(0);
	        			 PrixTransferEntrer=new BigDecimal(0);
	        			 prixinitial=new BigDecimal(0);
	        			 Prixproduction=new BigDecimal(0);
	        			 PrixAvoirClientPF=new BigDecimal(0);
	    				
	    				
	    			if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ACHAT))
	    			{
	    				
	    		    	   if(!achat.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
	                  	   {
	                      	   Prixachat=(Prixachat.multiply(achat).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(achat.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)), RoundingMode.DOWN);

	                  	   }else
	                  	   {
	                  		   Prixachat=BigDecimal.ZERO;
	                  	   }
	    				
	    				achat=achat.add(listDetailTransferStockPF.get(j).getQuantite());
	    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_VENTE))
	    			{
	    				vente=vente.add(listDetailTransferStockPF.get(j).getQuantite());
	    				
	    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SORTIE_PF_PF))
	    			{
	    				vente=vente.add(listDetailTransferStockPF.get(j).getQuantite());
	    				
	    			}
	    			
	    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_INITIAL))
	    			{
	    				 prixinitial=listDetailTransferStockPF.get(j).getPrixUnitaire();
	    				initial=initial.add(listDetailTransferStockPF.get(j).getQuantite());
	    				
	    			}else if(typetransfer[k].equals(TYPE_TRANSFER_PRODUIT_FINI_ENTRE))
	    			{
	    				   if(!production.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6, RoundingMode.DOWN)))
	                  	   {
	                      	   Prixproduction=(Prixproduction.multiply(production).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(production.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)),RoundingMode.DOWN);

	                  	   }else
	                  	   {
	                  		   Prixachat=BigDecimal.ZERO;
	                  	   }
	    				
	    				production=production.add(listDetailTransferStockPF.get(j).getQuantite());	
	    				
	    				
	    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR))
	    			{ 
	    				if(listDetailTransferStockPF.get(j).getSousFamille().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_EMBALLAGE))
	    				{
	    					
	    					
	    					avoir=avoir.add(listDetailTransferStockPF.get(j).getQuantite());	
	    				}
	    				
	    				
	    				
	    				
	    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR_CLIENT))
	    			{ 
	    				if(!avoirClientPF.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
	                  	   {
	    					PrixAvoirClientPF=(PrixAvoirClientPF.multiply(avoirClientPF).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(avoirClientPF.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)), RoundingMode.DOWN);

	                  	   }else
	                  	   {
	                  		 PrixTransferEntrer=BigDecimal.ZERO;
	                  	   }
	    					avoirClientPF=avoirClientPF.add(listDetailTransferStockPF.get(j).getQuantite());	
	    				
	    				
	    				
	    				
	    				
	    			}
	    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR_R))
	    			{ 
	    				
	    					avoir=avoir.add(listDetailTransferStockPF.get(j).getQuantite());	
	    				
	    				
	    				
	    				
	    				
	    			}
	    			
	    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_MP))
	    			{ 
	    				   if(!TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
	                  	   {
	                      	   PrixTransferEntrer=(PrixTransferEntrer.multiply(TransferEntrer).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)), RoundingMode.DOWN);

	                  	   }else
	                  	   {
	                  		 PrixTransferEntrer=BigDecimal.ZERO;
	                  	   }
	    				
	    				TransferEntrer=TransferEntrer.add(listDetailTransferStockPF.get(j).getQuantite());	
	    				
	    				
	    				
	    			}
	    			
	    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_PF_MP))
	    			{ 
	 				   if(!TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
	               	   {
	                   	   PrixTransferEntrer=(PrixTransferEntrer.multiply(TransferEntrer).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)), RoundingMode.DOWN);

	               	   }else
	               	   {
	               		 PrixTransferEntrer=BigDecimal.ZERO;
	               	   }
	 				
	 				TransferEntrer=TransferEntrer.add(listDetailTransferStockPF.get(j).getQuantite());	
	 				
	 				
	 				
	 			}	else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_PF_PF))
    			{ 
	 				   if(!TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
	               	   {
	                   	   PrixTransferEntrer=(PrixTransferEntrer.multiply(TransferEntrer).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)), RoundingMode.DOWN);

	               	   }else
	               	   {
	               		 PrixTransferEntrer=BigDecimal.ZERO;
	               	   }
	 				
	 				TransferEntrer=TransferEntrer.add(listDetailTransferStockPF.get(j).getQuantite());	
	 				
	 				
	 				
	 			}
	    			
	    			
	    			
	    			if(!quantitefinale.setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6, RoundingMode.DOWN)))
	    			{
		    			

	    				if(!quantitefinale.add(achat).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6, RoundingMode.DOWN)))
		    			{
			    			
	if(quantitefinale.add(achat).add(TransferEntrer).add(production).add(avoirClientPF).compareTo(BigDecimal.ZERO)!=0)
	{
		prixmoyenne=(achat.multiply(Prixachat).add(quantitefinale.multiply(prixmoyenne)).add(TransferEntrer.multiply(PrixTransferEntrer)).add(production.multiply(Prixproduction)).add(avoirClientPF.multiply(PrixAvoirClientPF))).divide(quantitefinale.add(achat).add(TransferEntrer).add(production).add(avoirClientPF),6, RoundingMode.DOWN);
		//System.out.println(listDetailTransferStockPF.get(j).getArticle().getLiblle() + " : "+ listDetailTransferStockPF.get(j).getSousFamille().getLiblle() +" : Prix Moyenne :"+ prixmoyenne );

	}
	    					
		    			}else
		    			{
		    				prixmoyenne=BigDecimal.ZERO;

		    			}
	    				
	    				

	    				quantitefinale=(quantitefinale.add(achat).add(TransferEntrer).add(production).add(avoirClientPF)).subtract(vente.add(avoir));
	    				
	    			}else
	    			{
	    				
	    				
	    				if(!initial.add(achat).add(production).add(TransferEntrer).add(avoirClientPF).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
		    			{

	    					prixmoyenne=(achat.multiply(Prixachat).add(initial.multiply(prixinitial)).add(PrixTransferEntrer.multiply(TransferEntrer).add(Prixproduction.multiply(production)).add(PrixAvoirClientPF.multiply(avoirClientPF)))).divide(initial.add(achat).add(production).add(TransferEntrer).add(avoirClientPF), 6, RoundingMode.DOWN);
	    					
		    			
		    			}else
		    			{
		    				prixmoyenne=BigDecimal.ZERO;

		    			}
	    				
	    				
	    				quantitefinale=(initial.add(achat).add(TransferEntrer).add(production).add(avoirClientPF)).subtract(vente.add(avoir));

	    				
	    			}
	    			
	    						    				
	    			}
	    					    			
	    			}
	    			}
	    			
	    			if(listMouvementStockPF.size()!=0)
	    			{
	    				for(int d=0;d<listMouvementStockPF.size();d++)
	    				{
	    					
	    					if(listMouvementStockPF.get(d).getArticle().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() && listMouvementStockPF.get(d).getSousFamille().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId())
	    					{
	    						initial=listMouvementStockPF.get(d).getStockFinal();
	    						prixinitial=listMouvementStockPF.get(d).getPrixFinal();
	    						trouve=true;
	    						
	    					}
	    				}
	    			
	    				
	    			}
	    			if(trouve==false)
	    			{
	    				for(int l=0;l<listDetailTransferStockPFBytypetransfer.size();l++)
	    				{
	    	if(listDetailTransferStockPFBytypetransfer.get(l).getArticle().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() && listDetailTransferStockPFBytypetransfer.get(l).getSousFamille().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId())
	    	{
	    		initial=listDetailTransferStockPFBytypetransfer.get(l).getQuantite();
	    			
	    	}
	    				}
	    				
					
	    			}
	    			
	    		
	    			
	    			stockfinal=(initial.add(production).add(achat).add(TransferEntrer).add(avoirClientPF)).subtract(vente.add(avoir));
	    			MouvementStockProduitsFini mouvementstockPF=new MouvementStockProduitsFini();
	    			mouvementstockPF.setDateStockPF(listDetailTransferStockPFGroupebyArticle.get(i).getTransferStockPF().getDateTransfer());
	    			mouvementstockPF.setAchat(achat);
	    			mouvementstockPF.setPrixAchat(Prixachat);
	    			mouvementstockPF.setVente(vente);
	    			mouvementstockPF.setAvoir(avoir);
	    			mouvementstockPF.setAvoirClientPF(avoirClientPF);
	    			mouvementstockPF.setInitial(initial);
	    			mouvementstockPF.setPrixInitial(prixinitial);
	    			mouvementstockPF.setEntrerProduction(production);
	    			mouvementstockPF.setPrixProduction(Prixproduction);
	    			mouvementstockPF.setTransferEntrer(TransferEntrer);
	    			mouvementstockPF.setPrixTransferEntrer(PrixTransferEntrer);
	    			mouvementstockPF.setSousFamille(listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille());
	    			mouvementstockPF.setArticle(listDetailTransferStockPFGroupebyArticle.get(i).getArticle());
	    			
	    			
	    			
					/*
					 * if(listDetailTransferStockPFGroupebyArticle.get(i).getArticle().
					 * getCodeArticle().equals("NP1000")) {
					 * 
					 * if((mouvementstockPF.getAchat().add(mouvementstockPF.getInitial()).add(
					 * mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.
					 * getEntrerProduction())).setScale(6,
					 * RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6,
					 * RoundingMode.HALF_UP))!=0) {
					 * prixmoyenne=(mouvementstockPF.getAchat().multiply(mouvementstockPF.
					 * getPrixAchat()).add(mouvementstockPF.getInitial().multiply(mouvementstockPF.
					 * getPrixInitial())).add(mouvementstockPF.getPrixTransferEntrer().multiply(
					 * mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.getPrixProduction(
					 * ).multiply(mouvementstockPF.getEntrerProduction())))).divide(mouvementstockPF
					 * .getInitial().add(mouvementstockPF.getAchat()).add(mouvementstockPF.
					 * getEntrerProduction()).add(mouvementstockPF.getTransferEntrer()),
					 * RoundingMode.HALF_UP);
					 * 
					 * //JOptionPane.showMessageDialog(null,
					 * "Prix Moyenne calculé "+listDetailTransferStockPFGroupebyArticle.get(i).
					 * getArticle().getLiblle()); //JOptionPane.showMessageDialog(null,
					 * "Prix Moyenne calculé est : "+mouvementstockPF.getAchat()
					 * +"*"+mouvementstockPF.getPrixAchat() + "+"+mouvementstockPF.getPrixInitial()
					 * +"*"+mouvementstockPF.getInitial()
					 * +" + "+mouvementstockPF.getPrixTransferEntrer()+"*"+mouvementstockPF.
					 * getTransferEntrer() +" + "+mouvementstockPF.getPrixProduction() +" * "+
					 * mouvementstockPF.getEntrerProduction());
					 * 
					 * //JOptionPane.showMessageDialog(null,
					 * "Prix Moyenne calculé est : "+prixmoyenne); }else {
					 * prixmoyenne=BigDecimal.ZERO; //JOptionPane.showMessageDialog(null,
					 * "Prix Moyenne Zero"); }
					 * 
					 * 
					 * 
					 * 
					 * }
					 */
	    			
	    			
					/*
					 * if((mouvementstockPF.getAchat().add(mouvementstockPF.getInitial()).add(
					 * mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.
					 * getEntrerProduction())).setScale(6,
					 * RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6,
					 * RoundingMode.HALF_UP))!=0) {
					 * prixmoyenne=(mouvementstockPF.getAchat().multiply(mouvementstockPF.
					 * getPrixAchat()).add(mouvementstockPF.getInitial().multiply(mouvementstockPF.
					 * getPrixInitial())).add(mouvementstockPF.getPrixTransferEntrer().multiply(
					 * mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.getPrixProduction(
					 * ).multiply(mouvementstockPF.getEntrerProduction())))).divide(mouvementstockPF
					 * .getInitial().add(mouvementstockPF.getAchat()).add(mouvementstockPF.
					 * getEntrerProduction()).add(mouvementstockPF.getTransferEntrer()),
					 * RoundingMode.HALF_UP);
					 * 
					 * }else { prixmoyenne=BigDecimal.ZERO; }
					 */

				//	System.out.println( "new "+mouvementstockPF.getArticle().getLiblle() + " : "+mouvementstockPF.getSousFamille().getLiblle() +" : Prix Moyenne :"+ prixmoyenne );

	    			mouvementstockPF.setPrixFinal(prixmoyenne); 
	    			mouvementstockPF.setStockFinal((mouvementstockPF.getInitial().add(mouvementstockPF.getAchat()).add(mouvementstockPF.getEntrerProduction()).add(mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.getAvoirClientPF())).subtract(mouvementstockPF.getVente().add(mouvementstockPF.getAvoir())));
	    			
	    			listMouvementStockPF.add(mouvementstockPF);
	    			
	    			
	    			

	    		}
	    		
	    	
	    		
	    		
	    		
	    		// detailtransfer entre deux date et par article
	    		
	    		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && article!=null)
	    		{
	    			listMouvementStockPFAfficher.clear();
	    			listMouvementStockPFAfficherTmp.clear();
	    			
	    		
	    		for(int i=0;i<listMouvementStockPF.size();i++)	
	    		{
	    			String ddebut=sdf.format(dateChooserdebut.getDate());
	    			String ddebutTmp=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
	    			
	    			if(listMouvementStockPF.get(i).getDateStockPF().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut)   )
	    				
	    			{
	    			if(listMouvementStockPF.get(i).getArticle().getLiblle().equals(article.getLiblle()))
	    			{

	    				listMouvementStockPFAfficher.add(listMouvementStockPF.get(i));	
	    			}
	    				
	    			
	    			}
	    			
	    		}
	    			
	    		for(int j=0;j<listMouvementStockPFAfficher.size();j++)	
	    		{
	    			
	    			String dfin=sdf.format(dateChooserfin.getDate());
	    			String dfinTmp=sdf.format(listMouvementStockPFAfficher.get(j).getDateStockPF());
	    			if(listMouvementStockPFAfficher.get(j).getDateStockPF().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin) )
	    			{
	    			if(listMouvementStockPFAfficher.get(j).getArticle().getLiblle().equals(article.getLiblle()))
	    			{
	    				listMouvementStockPFAfficherTmp.add(listMouvementStockPFAfficher.get(j));
	    			}
	    				
	    			}
	    			
	    		}
	    		
	    	
	    			
	    		// detailtransfer entre deux date (date fin null) et par article 
	    			
	    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article!=null)
	    		{
	    			listMouvementStockPFAfficherTmp.clear();
	    			String d1=sdf.format(dateChooserdebut.getDate());
	    		
	    			
	    			for(int i=0;i<listMouvementStockPF.size();i++)	
		    		{
	    				String ddbut=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
		    			if(ddbut.equals(d1) && listMouvementStockPF.get(i).getArticle().equals(article) )
		    			{
		    			
		    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
		    			
		    			}
		    			
		    		}
	    			
	    			
	    			
	    			// detailtransfer entre deux date (date fin null)  
	    			
	    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article==null)
	    		{
	    			
	    			listMouvementStockPFAfficherTmp.clear();
	                  
	                  String d1=sdf.format(dateChooserdebut.getDate());
		    			
	    			
	    			for(int i=0;i<listMouvementStockPF.size();i++)	
		    		{
	    				String ddbut=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
		    			if(ddbut.equals(d1) )
		    			{
		    			
		    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
		    			
		    			}
		    		
		    		}
	    			
	    		
	    			
	    			// detailtransfer par article
	    		}else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null && article!=null)
	    		{
	    			listMouvementStockPFAfficherTmp.clear();
	             
	               
	               
	    			for(int i=0;i<listMouvementStockPF.size();i++)	
		    		{
		    			if(listMouvementStockPF.get(i).getArticle().getLiblle().equals(article.getLiblle()) )
		    			{
		    			
		    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
		    			
		    				
		    			}
		    			
		    		}
	    			
	    			
	    			
	    			
	    			
	    			// detailtransfer entre deux date  
	    			
	    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && article==null)
	    		{
	    			listMouvementStockPFAfficher.clear();
	    			listMouvementStockPFAfficherTmp.clear();
	    		
	    		for(int i=0;i<listMouvementStockPF.size();i++)	
	    		{
	    			String ddebut=sdf.format(dateChooserdebut.getDate());
	    			String ddebutTmp=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
	    			if(listMouvementStockPF.get(i).getDateStockPF().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut))
	    			{
	    				listMouvementStockPFAfficher.add(listMouvementStockPF.get(i));
	    			
	    				
	    			}
	    			
	    		}
	    			
	    		
	    		
	    		for(int j=0;j<listMouvementStockPFAfficher.size();j++)	
	    		{
	    			
	    			String dfin=sdf.format(dateChooserfin.getDate());
	    			String dfinTmp=sdf.format(listMouvementStockPFAfficher.get(j).getDateStockPF());
	    			
	    			if(listMouvementStockPFAfficher.get(j).getDateStockPF().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin)  )
	    			{
	    			
	    				listMouvementStockPFAfficherTmp.add(listMouvementStockPFAfficher.get(j));
	    			
	    			}
	    			
	    		}
	    		
	    	
	    				    			
	    		}
	    		
			
			
			}else
			{
				JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
				return;
			}

			
		
			
			
		}	
		
		
		

		public void CalculerPrixMoyenInitialMP()	

		{
			
			

					Magasin magasinMP=mapMagasin.get(comboMagasinMP.getSelectedItem());
					
					if(magasinMP!=null)
					{
						
					BigDecimal quantite=BigDecimal.ZERO;
					BigDecimal quantitefoiprix=BigDecimal.ZERO;
				BigDecimal quantiteOffre=BigDecimal.ZERO;
				BigDecimal quantiteOffrefoiprix=BigDecimal.ZERO;
					
					
					
						boolean trouve=false;
						
					String dateDebut=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			
				}else {
					
					listEtatInitialMP.clear();
					listDetailTransferStockMPTmp=detailTransferStockMPDAO.listeDetailTransfertMPByDateByMagasinByStatut(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasinMP,Constantes.ETAT_TRANSFER_STOCK_INITIAL);
				
					if(listDetailTransferStockMPTmp.size()!=0)
					{
						
						
						 for(int k=0;k<listDetailTransferStockMPTmp.size();k++)
						 {
							 DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPTmp.get(k);
							SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(detailTransferStockMP.getMatierePremier())	;
							 
							 trouve=false;
							
							 if(listDetailTransferStockMP.size()!=0)
							 {
								
								 for(int i=0;i<listDetailTransferStockMP.size();i++)
								 {
									 if(sousFamilleEnVrac!=null)
									 {
										SousFamilleEnVrac sousFamilleEnVracTmp=sousFamilleEnvracDAo.findByMP(listDetailTransferStockMP.get(i).getMatierePremier())	;
										 if(sousFamilleEnVracTmp!=null)
										 {
											 
											 if(sousFamilleEnVracTmp.getSousfamile().getId()==sousFamilleEnVrac.getSousfamile().getId())
											 {
												 
												 trouve=true;
												 
											 }
											 
											 
										 }
										 
										 
										 
										 
									 }else
									 {
										 
										 if(listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp().equals(detailTransferStockMP.getMatierePremier().getCategorieMp()))
										 {
											 trouve=true;
										 } 
										 
									 }
									 
									
									 
									 
									 
									 
								 }
								 
							 }else
							 {
								listDetailTransferStockMP.add(detailTransferStockMP);
								trouve=true;
							 }
							 
							 if(trouve==false)
							 {
								listDetailTransferStockMP.add(detailTransferStockMP);
							 }
						 }
						
						
						
					boolean existe=false;
					
					String famille="";
					String sousFamille="";
						if(listDetailTransferStockMP.size()!=0)
						{
							for(int i=0;i<listDetailTransferStockMP.size();i++)
							{
								CategorieMp sousCategorieMP =listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp();
								quantite=BigDecimal.ZERO;
								quantitefoiprix=BigDecimal.ZERO;
								
							SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(listDetailTransferStockMP.get(i).getMatierePremier())	;
							
							if(sousFamilleEnVrac==null)
							{
								famille=listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getNom();
								sousFamille=listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp().getNom();
								
							}else
							{
								
								famille=sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle();
								sousFamille=sousFamilleEnVrac.getSousfamile().getLiblle();
								
								
							}
								
								existe=false;
							for(int j=0;j<listDetailTransferStockMPTmp.size();j++)	
							{
								
								
								/*
								if(listDetailTransferStockMPTmp.get(j).getMatierePremier().getCategorieMp().equals(sousCategorieMP))
								{*/
								 
								   		if(sousFamilleEnVrac!=null)
								   		{
								   			
								   		SousFamilleEnVrac sousFamilleEnVracTmp=sousFamilleEnvracDAo.findByMP(listDetailTransferStockMPTmp.get(j).getMatierePremier())	;
								   		
								   		if(sousFamilleEnVracTmp!=null)
								   		{
								   			if(sousFamilleEnVracTmp.getSousfamile().getId()==sousFamilleEnVrac.getSousfamile().getId())
								   			{
								   			quantitefoiprix=quantitefoiprix.add(listDetailTransferStockMPTmp.get(j).getQuantite().multiply(listDetailTransferStockMPTmp.get(j).getPrixUnitaire()));
											quantite=quantite.add(listDetailTransferStockMPTmp.get(j).getQuantite());
											existe=true;
								   			}
								   	
										
										
								   		}
								   	 
								   			
								   		}else
								   		{
								   		if(listDetailTransferStockMPTmp.get(j).getMatierePremier().getCategorieMp().equals(sousCategorieMP))
								   		{
								   		quantitefoiprix=quantitefoiprix.add(listDetailTransferStockMPTmp.get(j).getQuantite().multiply(listDetailTransferStockMPTmp.get(j).getPrixUnitaire()));
										quantite=quantite.add(listDetailTransferStockMPTmp.get(j).getQuantite());
										existe=true;
								   		}
								   			
								   	
								   			
								   			
								   		}
								 
								   	
								   
									
									
									
							/*	}*/
								
								
							}
							
			
							if(existe==true)
							{
								
							
								EtatInitialParSousCtaegorieMP etatInitialParSousCtaegorieMP=new EtatInitialParSousCtaegorieMP();
								etatInitialParSousCtaegorieMP.setSousCategorie(sousCategorieMP);
								etatInitialParSousCtaegorieMP.setCategorie(sousCategorieMP.getSubCategorieMp());
								etatInitialParSousCtaegorieMP.setFamilleArticlePF(famille);
								etatInitialParSousCtaegorieMP.setSousFamilleArticlePF(sousFamille);
								etatInitialParSousCtaegorieMP.setTotalInitial(quantite);
								etatInitialParSousCtaegorieMP.setPrixMoyen(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
								etatInitialParSousCtaegorieMP.setMontant(etatInitialParSousCtaegorieMP.getPrixMoyen().multiply(etatInitialParSousCtaegorieMP.getTotalInitial()).setScale(6, RoundingMode.FLOOR));
								listEtatInitialMP.add(etatInitialParSousCtaegorieMP);
							}
							
							
						
							
								
							}
							
							
							
							
						}else
						{
							JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
							
							
							return;
						}
						
					}
					
					
				}
						
					}
					

				
					
				
			
			
			
			
			
			
			
			
		}
		
		
		public void CalculerPrixMoyenAchatMP()	
		{
			
			
			

			
			
		  	
			Magasin magasin=mapMagasin.get(comboMagasinMP.getSelectedItem());
			String TousSubCategorie="";
			String Touscategorie="";
			String TousArticle="";
			if(magasin!=null)
			{
				if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
				{
					
				if(	DateUtils.nbJoursEntre(dateChooserdebut.getDate(), dateChooserfin.getDate())>=0)
				{
					
					CategorieMp categorieMP=null;
					SubCategorieMp souscategorieMP=null;
			
					MatierePremier mp=null;
				  
		  				
					listeObjectPrixMoyenAchat=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dateChooserdebut.getDate(), dateChooserfin.getDate(),magasin,souscategorieMP,mp, TousSubCategorie,TousArticle, categorieMP , Touscategorie);
		  				
		  				  
					
				}else
				{
					JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				}
			}else
			{
				JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				return;
			}

			







			
			
			
			
			
		}
		
		


public void ConsulterEtatPrixMoyenStockInitialPF()
{
	

			Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
			
			if(magasin!=null)
			{
				
			BigDecimal quantite=BigDecimal.ZERO;
			BigDecimal quantitefoiprix=BigDecimal.ZERO;
		BigDecimal quantiteOffre=BigDecimal.ZERO;
		BigDecimal quantiteOffrefoiprix=BigDecimal.ZERO;
			
			
			
				boolean trouve=false;
				
			String dateDebut=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
		String dateFin=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
	if(dateDebut.equals(""))	{
		JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
	} else if(dateFin.equals("")){
		JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
		
	
		}else {
			
			listEtatVente.clear();
			listDetailTransferProduitFiniTmp=detailTransferProduitFiniDAO.listeDetailTransfertPFByDateByMagasinByStatut(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasin,Constantes.ETAT_TRANSFER_STOCK_INITIAL);
		
			if(listDetailTransferProduitFiniTmp.size()!=0)
			{
				
				
				 for(int k=0;k<listDetailTransferProduitFiniTmp.size();k++)
				 {
					 DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFiniTmp.get(k);
					 trouve=false;
					
					 if(listDetailTransferProduitFini.size()!=0)
					 {
						
						 for(int i=0;i<listDetailTransferProduitFini.size();i++)
						 {
							 if(listDetailTransferProduitFini.get(i).getSousFamille().equals(detailTransferProduitFini.getSousFamille()))
							 {
								 trouve=true;
							 }
							 
						 }
						 
					 }else
					 {
						listDetailTransferProduitFini.add(detailTransferProduitFini);
						trouve=true;
					 }
					 
					 if(trouve==false)
					 {
						listDetailTransferProduitFini.add(detailTransferProduitFini);
					 }
				 }
				
				
				
			
			boolean exist=false;
			boolean existe=false;
			
				if(listDetailTransferProduitFini.size()!=0)
				{
					for(int i=0;i<listDetailTransferProduitFini.size();i++)
					{
						SousFamilleArticlePF sousfamilleArticle =listDetailTransferProduitFini.get(i).getSousFamille();
						quantite=BigDecimal.ZERO;
						quantitefoiprix=BigDecimal.ZERO;
						quantiteOffre=BigDecimal.ZERO;
						quantiteOffrefoiprix=BigDecimal.ZERO;
						
						exist=false;
						existe=false;
						
					for(int j=0;j<listDetailTransferProduitFiniTmp.size();j++)	
					{
						
						
						
						if(listDetailTransferProduitFiniTmp.get(j).getSousFamille().equals(sousfamilleArticle))
						{
						   
						   if(listDetailTransferProduitFiniTmp.get(j).getArticle().getCodeArticle().contains(Constantes.CODE_PRODUIT_FINI_OFFRE))	
						   {
							   	quantiteOffrefoiprix=quantiteOffrefoiprix.add(listDetailTransferProduitFiniTmp.get(j).getQuantite().multiply(listDetailTransferProduitFiniTmp.get(j).getPrixUnitaire()));
								  quantiteOffre=quantiteOffre.add(listDetailTransferProduitFiniTmp.get(j).getQuantite());
								   	
							   
							 existe=true;
							   
							  
						   }else
						   {
							  	quantitefoiprix=quantitefoiprix.add(listDetailTransferProduitFiniTmp.get(j).getQuantite().multiply(listDetailTransferProduitFiniTmp.get(j).getPrixUnitaire()));
								quantite=quantite.add(listDetailTransferProduitFiniTmp.get(j).getQuantite());
								exist=true; 
						   }
						  	
						  
							
							
							
						}
						
						
					}
					
					if(existe==true)
					{

						
	////////////////////////////////////////////////////////////// Offre ///////////////////////////////////////////////////////////////////////////////////////					
						
						
						EtatVenteParFamilleArticle etatventeFamilleArticleTmp=new EtatVenteParFamilleArticle();
						etatventeFamilleArticleTmp.setSousFamilleArticlePF(sousfamilleArticle);
						etatventeFamilleArticleTmp.setFamilleArticlePF(FamilleOffre);
						etatventeFamilleArticleTmp.setTotalVente(quantiteOffre);
						etatventeFamilleArticleTmp.setPrixMoyen(quantiteOffrefoiprix.divide(quantiteOffre, 6, RoundingMode.HALF_UP));
						etatventeFamilleArticleTmp.setMontant(etatventeFamilleArticleTmp.getPrixMoyen().multiply(etatventeFamilleArticleTmp.getTotalVente()).setScale(6, RoundingMode.FLOOR));
						listEtatVente.add(etatventeFamilleArticleTmp);
						
						
						
					}
					
			
					if(exist==true)
					{
						
					
						EtatVenteParFamilleArticle etatventeFamilleArticle=new EtatVenteParFamilleArticle();
						etatventeFamilleArticle.setSousFamilleArticlePF(sousfamilleArticle);
						etatventeFamilleArticle.setFamilleArticlePF(sousfamilleArticle.getFamileArticlePF());
						etatventeFamilleArticle.setTotalVente(quantite);
						etatventeFamilleArticle.setPrixMoyen(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
						etatventeFamilleArticle.setMontant(etatventeFamilleArticle.getPrixMoyen().multiply(etatventeFamilleArticle.getTotalVente()).setScale(6, RoundingMode.FLOOR));
						listEtatVente.add(etatventeFamilleArticle);
					}
					
					
				
					
						
					}
					
				
					
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
					listEtatVente.clear();
					
					return;
				}
				
			}
			
			
		}
				
			}
			

		
			
			
	
}



public void EtatPrixMoyenInitialParArticle()
{
	



	
	
  	
	Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
	String TousFamille="";
	String TousSousFamille="";
	String TousArticle="";
	
	
	String req="";
	
	
	if(magasin!=null)
	{
		
		req=req+"  d.magasinDestination.id='"+magasin.getId()+"' and d.transferStockPF.statut='"+Constantes.ETAT_TRANSFER_STOCK_INITIAL+"' ";
		
		
		
		
		
		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
		{
			
			
		if(	DateUtils.nbJoursEntre(dateChooserdebut.getDate(), dateChooserfin.getDate())>=0)
		{
			
			if(dateChooserdebut.getDate()!=null)
			{
				dateChooserdebut.setDateFormatString("yyyy-MM-dd");
			}
			if(dateChooserfin.getDate()!=null)
			{
				dateChooserfin.setDateFormatString("yyyy-MM-dd");
			}
			
			
			String dateDu=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			
			String dateAu=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
			
			req=req+" and d.transferStockPF.dateTransfer between '"+dateDu+"' and '"+dateAu+"' ";
			

			
    			
			
		 
    	
		
			
		
		
    				
			
  		
  			 
  				  
  				
  				listeObject=detailTransferProduitFiniDAO.EtatMoyenValeurisationproduction(req);
  				
  				
  				listEtatPrixMoyen.clear();
  				
			
  			
  			
  				
  				
  				
  				for(int i=0;i<listeObject.size();i++)
				{	
					 Object[] object=listeObject.get(i);
					 
					EtatPrixMoyen etatPrixMoyen = new EtatPrixMoyen();
					etatPrixMoyen.setCodeArticle(object[0].toString());
					etatPrixMoyen.setArticle(object[1].toString());
					if(etatPrixMoyen.getCodeArticle().contains(Constantes.CODE_PRODUIT_FINI_OFFRE))
					{
						etatPrixMoyen.setFamille(FamilleOffre.getLiblle());
						
					}else if(etatPrixMoyen.getCodeArticle().contains(Constantes.CODE_PRODUIT_FINI_DECHET))
					{
						
						etatPrixMoyen.setFamille(FamilleDechet.getLiblle());
						
					}else
					{
						etatPrixMoyen.setFamille(object[2].toString());
					}
					
					etatPrixMoyen.setSousfamille(object[3].toString());
					
					etatPrixMoyen.setQuantite(new BigDecimal(object[4].toString()));
					if(new BigDecimal(object[4].toString()).compareTo(BigDecimal.ZERO)!=0)
					{
						etatPrixMoyen.setPrix(new BigDecimal(object[5].toString()).divide(new BigDecimal(object[4].toString()), 6, RoundingMode.HALF_UP));	
					}else
					{
						etatPrixMoyen.setPrix(BigDecimal.ZERO);	
					}
					
					etatPrixMoyen.setMontantHT(etatPrixMoyen.getQuantite().multiply(etatPrixMoyen.getPrix()));
					listEtatPrixMoyen.add(etatPrixMoyen);
					
					
				}
  				
  				
  				

  		

  				
  				
  				
  		
  			  
  		 
  			  
  		
  		  
			
		}else
		{
			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		}
	}else
	{
		JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}

	










	
	
}


public void EtatPrixMoyenAchatPF()
{
	

			Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
			
			if(magasin!=null)
			{
				
			BigDecimal quantite=BigDecimal.ZERO;
			BigDecimal quantitefoiprix=BigDecimal.ZERO;
				boolean trouve=false;
				
			String dateDebut=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
		String dateFin=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
	if(dateDebut.equals(""))	{
		JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
	} else if(dateFin.equals("")){
		JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
		
	
		}else {
			
			listEtatVente.clear();
			listDetailFactureTmp=detailfactureAchatdao.listeDetailFactureAchatByDateByMagasin(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasin);
		
			if(listDetailFactureTmp.size()!=0)
			{
				
				
				 for(int k=0;k<listDetailFactureTmp.size();k++)
				 {
					 DetailFactureAchat detailfacture=listDetailFactureTmp.get(k);
					 trouve=false;
					
					 if(listDetailFacture.size()!=0)
					 {
						
						 for(int i=0;i<listDetailFacture.size();i++)
						 {
							 if(listDetailFacture.get(i).getSousFamille().equals(detailfacture.getSousFamille()))
							 {
								 trouve=true;
							 }
							 
						 }
						 
					 }else
					 {
						listDetailFacture.add(detailfacture);
						trouve=true;
					 }
					 
					 if(trouve==false)
					 {
						listDetailFacture.add(detailfacture);
					 }
				 }
				
				
				
			
				
				if(listDetailFacture.size()!=0)
				{
					for(int i=0;i<listDetailFacture.size();i++)
					{
						SousFamilleArticlePF sousfamilleArticle =listDetailFacture.get(i).getSousFamille();
						quantite=BigDecimal.ZERO;
						quantitefoiprix=BigDecimal.ZERO;
						
					for(int j=0;j<listDetailFactureTmp.size();j++)	
					{
						
						
						
						if(listDetailFactureTmp.get(j).getSousFamille().equals(sousfamilleArticle))
						{
							if(listDetailFactureTmp.get(j).getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
							{
								if(listDetailFactureTmp.get(j).getArticle().getConditionnementcaisse()!=null)
								{
									
									
									quantitefoiprix=quantitefoiprix.add(listDetailFactureTmp.get(j).getMontantHT());
									//quantitefoiprix=quantitefoiprix.add(listDetailFactureTmp.get(j).getQuantite().multiply(listDetailFactureTmp.get(j).getArticle().getConditionnement().multiply(listDetailFactureTmp.get(j).getArticle().getConditionnementcaisse())).multiply(listDetailFactureTmp.get(j).getPrixUnitaire().divide(listDetailFactureTmp.get(j).getArticle().getConditionnement(), 6, RoundingMode.FLOOR).divide(listDetailFactureTmp.get(j).getArticle().getConditionnementcaisse(), 6, RoundingMode.FLOOR)));
									quantite=quantite.add(listDetailFactureTmp.get(j).getQuantite().multiply(listDetailFactureTmp.get(j).getArticle().getConditionnement().multiply(listDetailFactureTmp.get(j).getArticle().getConditionnementcaisse())));
								
								}else
								{
									//quantitefoiprix=quantitefoiprix.add(listDetailFactureTmp.get(j).getQuantite().multiply(listDetailFactureTmp.get(j).getArticle().getConditionnement()).multiply(listDetailFactureTmp.get(j).getPrixUnitaire().divide(listDetailFactureTmp.get(j).getArticle().getConditionnement(), 6, RoundingMode.FLOOR)));
								
									
									quantitefoiprix=quantitefoiprix.add(listDetailFactureTmp.get(j).getMontantHT());
									quantite=quantite.add(listDetailFactureTmp.get(j).getQuantite().multiply(listDetailFactureTmp.get(j).getArticle().getConditionnement()));
								
								}
							
								
							}else
							{
								//quantitefoiprix=quantitefoiprix.add(listDetailFactureTmp.get(j).getQuantite().multiply(listDetailFactureTmp.get(j).getPrixUnitaire()));
								quantitefoiprix=quantitefoiprix.add(listDetailFactureTmp.get(j).getMontantHT());
								quantite=quantite.add(listDetailFactureTmp.get(j).getQuantite());
							}
							
							
							
						}
						
						
					}
					
					
					EtatVenteParFamilleArticle etatventeFamilleArticle=new EtatVenteParFamilleArticle();
					etatventeFamilleArticle.setSousFamilleArticlePF(sousfamilleArticle);
					etatventeFamilleArticle.setFamilleArticlePF(sousfamilleArticle.getFamileArticlePF());
					etatventeFamilleArticle.setTotalVente(quantite);
					if(quantite.compareTo(BigDecimal.ZERO)!=0)
					{
						etatventeFamilleArticle.setPrixMoyen(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));

						
					}else
					{
						etatventeFamilleArticle.setPrixMoyen(BigDecimal.ZERO);

					}
					etatventeFamilleArticle.setMontant(etatventeFamilleArticle.getPrixMoyen().multiply(etatventeFamilleArticle.getTotalVente()));
					listEtatVente.add(etatventeFamilleArticle);
					
						
					}
					
				
					
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
					listEtatVente.clear();
					
					return;
				}
				
			}
			
			
		}
				
			}
			

		
			
		
	
	
}


public void EtatPrixMoyenAchatParArticle()
{
	

	
	
	Articles article=null;
	Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
	String TousFamille="";
	String TousSousFamille="";
	String TousArticle="";
	if(magasin!=null)
	{
		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
		{
			
		if(	DateUtils.nbJoursEntre(dateChooserdebut.getDate(), dateChooserfin.getDate())>=0)
		{
			ClientPF clientpf=	null;
			FamilleArticlePF famillearticle=null;
			SousFamilleArticlePF sousfamillearticle=null;
			
		
			
			
		
			
		
			
		
			
  		
  			 
  				  
  				
  				listeObject=detailfactureAchatdao.listeEtatPrixMoyen(dateChooserdebut.getDate(), dateChooserfin.getDate(),magasin,famillearticle,article, TousFamille,TousArticle, sousfamillearticle , TousSousFamille);
  				
  				  
  			  
  			  
  		 
  			  
  		
  		  
			
		}else
		{
			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		}
	}else
	{
		JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}

	







	
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Etat Moyen Valeurisation Production Par Sous Famille //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void EtatMoyenValeurisationProductionparSousFamille()
{
	

	
	
  	
	Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
	String TousFamille="";
	String TousSousFamille="";
	String TousArticle="";
	String reqOffreDechet="";
	
	String req="";
	
	if(magasin!=null)
	{
		
		req=req+"  d.magasinDestination.id='"+magasin.getId()+"' and d.transferStockPF.statut='"+Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE+"' ";
		
		reqOffreDechet=reqOffreDechet+"  d.magasinDestination.id='"+magasin.getId()+"' and d.transferStockPF.statut='"+Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP+"' ";
		
		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
		{
			
			
		if(	DateUtils.nbJoursEntre(dateChooserdebut.getDate(), dateChooserfin.getDate())>=0)
		{
			
			if(dateChooserdebut.getDate()!=null)
			{
				dateChooserdebut.setDateFormatString("yyyy-MM-dd");
			}
			if(dateChooserfin.getDate()!=null)
			{
				dateChooserfin.setDateFormatString("yyyy-MM-dd");
			}
			
			
			String dateDu=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			
			String dateAu=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
			
			req=req+" and d.transferStockPF.dateTransfer between '"+dateDu+"' and '"+dateAu+"' ";
			reqOffreDechet=reqOffreDechet+" and d.transferStockPF.dateTransfer between '"+dateDu+"' and '"+dateAu+"' ";
			
			
			FamilleArticlePF famillearticle=null;
			SousFamilleArticlePF sousfamillearticle=null;
			
			
    			    		
			
			
  		
  			
  				  
  				
  				listeObject=detailTransferProduitFiniDAO.EtatMoyenValeurisationproductionParSousFamille(req);
  				
  				listeObjectOffreDechet=detailTransferProduitFiniDAO.EtatMoyenValeurisationproductionOffreEtDechet(reqOffreDechet);
  				
  				
  				
  				listEtatPrixMoyenValeurisationProduction.clear();
  				
  				for(int i=0;i<listeObjectOffreDechet.size();i++)
				{	
					 Object[] object=listeObjectOffreDechet.get(i);
					 
					EtatPrixMoyen etatPrixMoyen = new EtatPrixMoyen();
					etatPrixMoyen.setCodeArticle(object[0].toString());
					etatPrixMoyen.setArticle(object[1].toString());
					if(etatPrixMoyen.getCodeArticle().contains(Constantes.CODE_PRODUIT_FINI_OFFRE))
					{
						etatPrixMoyen.setFamille(FamilleOffre.getLiblle());
						
					}else if(etatPrixMoyen.getCodeArticle().contains(Constantes.CODE_PRODUIT_FINI_DECHET))
					{
						
						etatPrixMoyen.setFamille(FamilleDechet.getLiblle());
						
					}else
					{
						etatPrixMoyen.setFamille(object[2].toString());
					}
					
					etatPrixMoyen.setSousfamille(object[3].toString());
					
					etatPrixMoyen.setQuantite(new BigDecimal(object[4].toString()));
					etatPrixMoyen.setPrix(new BigDecimal(object[5].toString()));	
					etatPrixMoyen.setMontantHT(etatPrixMoyen.getQuantite().multiply(etatPrixMoyen.getPrix()));
					listEtatPrixMoyenOffreDechet.add(etatPrixMoyen);
					
					
				}
  				
  				
  				for(int i =0;i<listeObject.size();i++)
				{
  					
  				EtatPrixMoyenMP	etatPrixMoyenMP=new EtatPrixMoyenMP();
  					
					 Object[] object=listeObject.get(i);
					 etatPrixMoyenMP.setFamille(object[0].toString());
					 etatPrixMoyenMP.setSousfamille(object[1].toString());
					 etatPrixMoyenMP.setQuantite(new BigDecimal(object[2].toString()));
					 etatPrixMoyenMP.setPrix( new BigDecimal(object[3].toString()));
					 etatPrixMoyenMP.setMontantHT(new BigDecimal(object[2].toString()).multiply(new BigDecimal(object[3].toString())));
					 
					 listEtatPrixMoyenValeurisationProduction.add(etatPrixMoyenMP);
				}
  				
  				boolean existe=false;
  				
  				for(int d=0;d<listEtatPrixMoyenOffreDechet.size();d++)
  				{
  					
  					EtatPrixMoyen etatPrixMoyen=listEtatPrixMoyenOffreDechet.get(d);
  					
  					existe=false;
  					for(int j=0;j<listEtatPrixMoyenValeurisationProduction.size();j++)
  					{
  						
  						EtatPrixMoyenMP etatPrixMoyenMP=listEtatPrixMoyenValeurisationProduction.get(j);
  						
  						if(etatPrixMoyenMP.getFamille().equals(etatPrixMoyen.getFamille()))
  						{
  							
  							if(etatPrixMoyenMP.getSousfamille().equals(etatPrixMoyen.getSousfamille()))
	  						{
  								existe=true;
	  							
  								etatPrixMoyenMP.setQuantite(etatPrixMoyenMP.getQuantite().add(etatPrixMoyen.getQuantite()));
  								etatPrixMoyenMP.setPrix((etatPrixMoyenMP.getPrix().multiply(etatPrixMoyenMP.getQuantite()).add(etatPrixMoyen.getPrix().multiply(etatPrixMoyen.getQuantite()))).divide(etatPrixMoyenMP.getQuantite().add(etatPrixMoyen.getQuantite()), 6, RoundingMode.HALF_UP));
  								etatPrixMoyenMP.setMontantHT(etatPrixMoyenMP.getQuantite().multiply(etatPrixMoyenMP.getPrix()));
  								listEtatPrixMoyenValeurisationProduction.set(j, etatPrixMoyenMP);
	  							
	  						}
  							
  							
  							
  							
  						}
  						
  						
  						
  					}
  					
  					if(existe==false)
  					{
  						
  						
  						
  						EtatPrixMoyenMP	etatPrixMoyenMP=new EtatPrixMoyenMP();
	  					
						
						 etatPrixMoyenMP.setFamille(etatPrixMoyen.getFamille());
						 etatPrixMoyenMP.setSousfamille(etatPrixMoyen.getSousfamille());
						 etatPrixMoyenMP.setQuantite(etatPrixMoyen.getQuantite());
						 etatPrixMoyenMP.setPrix(etatPrixMoyen.getPrix());
						 etatPrixMoyenMP.setMontantHT(etatPrixMoyen.getMontantHT());
						 
						 listEtatPrixMoyenValeurisationProduction.add(etatPrixMoyenMP);
  						
  						
  						
  					}
  					
  					
  					
  					
  					
  					
  					
  				}
  				
  				
  				
  				
  				
  				
  				
  				
  				CalculerPrixMoyenPF();
  				
  			
  				
  				
  				for(int j=0;j<listEtatprixMoyenServiceAfficher.size();j++)
  				{
  					
  					PrixMoyenStockPF moyenStockPF=listEtatprixMoyenServiceAfficher.get(j);
  					for(int d=0;d<listEtatPrixMoyenValeurisationProduction.size();d++)
  					{
  						
  					EtatPrixMoyenMP etatPrixMoyenMP=	listEtatPrixMoyenValeurisationProduction.get(d);
  						
  						if(moyenStockPF.getSousFamille()!=null && etatPrixMoyenMP.getSousfamille()!=null)
  						{
  							if(moyenStockPF.getSousFamille().getLiblle().equals(etatPrixMoyenMP.getSousfamille()))
	  						{
	  							
	  							
	  							etatPrixMoyenMP.setPrix(moyenStockPF.getPrixMoyenFinale());
	  							etatPrixMoyenMP.setMontantHT(etatPrixMoyenMP.getPrix().multiply(etatPrixMoyenMP.getQuantite()));
	  							listEtatPrixMoyenValeurisationProduction.set(d, etatPrixMoyenMP);
	  							
	  						}
  						}
  					
  						
  						
  						
  					}
  					
  					
  					
  				}
  				
  				
  				
  				
  				
  				  
  			  
  			  
  		 
  			  
  		
  		  
			
		}else
		{
			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		}
	}else
	{
		JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}

	







	
	
	
	
}



public void EtatPixMoyenserviceParArticle()
{
	

	
	
  	
	Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
	String TousFamille="";
	String TousSousFamille="";
	String TousArticle="";
	
	
	String req="";
	String reqOffreDechet="";
	
	if(magasin!=null)
	{
		
		req=req+"  d.magasinDestination.id='"+magasin.getId()+"' and d.transferStockPF.statut='"+Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE+"' ";
		
		reqOffreDechet=reqOffreDechet+"  d.magasinDestination.id='"+magasin.getId()+"' and d.transferStockPF.statut='"+Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP+"' ";
		
		
		
		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
		{
			
			
		if(	DateUtils.nbJoursEntre(dateChooserdebut.getDate(), dateChooserfin.getDate())>=0)
		{
			
			if(dateChooserdebut.getDate()!=null)
			{
				dateChooserdebut.setDateFormatString("yyyy-MM-dd");
			}
			if(dateChooserfin.getDate()!=null)
			{
				dateChooserfin.setDateFormatString("yyyy-MM-dd");
			}
			
			
			String dateDu=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			
			String dateAu=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
			
			req=req+" and d.transferStockPF.dateTransfer between '"+dateDu+"' and '"+dateAu+"' ";
			
			reqOffreDechet=reqOffreDechet+" and d.transferStockPF.dateTransfer between '"+dateDu+"' and '"+dateAu+"' ";
			
			FamilleArticlePF famillearticle=null;
			SousFamilleArticlePF sousfamillearticle=null;
			Articles article=null;
			
    				
    		/*
			if(famillearticle!=null)
			{
				req=req+" and d.sousFamille.famileArticlePF.id='"+famillearticle.getId()+"' ";
				reqOffreDechet=reqOffreDechet+" and d.sousFamille.famileArticlePF.id='"+famillearticle.getId()+"' ";
				
			}
			*/
			
		/*	
		if(sousfamillearticle!=null)
		{
			req=req+" and d.sousFamille.id='"+sousfamillearticle.getId()+"' ";
			reqOffreDechet=reqOffreDechet+" and d.sousFamille.id='"+sousfamillearticle.getId()+"' ";
		}
		 */
    	
		
			
		
		
    			
    			/*	
    				if(article!=null)
		    		{
		    			req=req+" and d.article.id='"+article.getId()+"' ";
		    			reqOffreDechet=reqOffreDechet+" and d.article.id='"+article.getId()+"' ";
		    		}
			*/
  		
  			 
  				  
  				
  				listeObject=detailTransferProduitFiniDAO.EtatMoyenValeurisationproduction(req);
  				
  				
  				listEtatPrixMoyen.clear();
  				
			
  			
  				listeObjectOffreDechet=detailTransferProduitFiniDAO.EtatMoyenValeurisationproductionOffreEtDechet(reqOffreDechet);
  				
  				listeObject.addAll(listeObjectOffreDechet);
  				
  				for(int i=0;i<listeObject.size();i++)
				{	
					 Object[] object=listeObject.get(i);
					 
					EtatPrixMoyen etatPrixMoyen = new EtatPrixMoyen();
					etatPrixMoyen.setCodeArticle(object[0].toString());
					etatPrixMoyen.setArticle(object[1].toString());
					if(etatPrixMoyen.getCodeArticle().contains(Constantes.CODE_PRODUIT_FINI_OFFRE))
					{
						etatPrixMoyen.setFamille(FamilleOffre.getLiblle());
						
					}else if(etatPrixMoyen.getCodeArticle().contains(Constantes.CODE_PRODUIT_FINI_DECHET))
					{
						
						etatPrixMoyen.setFamille(FamilleDechet.getLiblle());
						
					}else
					{
						etatPrixMoyen.setFamille(object[2].toString());
					}
					
					etatPrixMoyen.setSousfamille(object[3].toString());
					
					etatPrixMoyen.setQuantite(new BigDecimal(object[4].toString()));
					etatPrixMoyen.setPrix(new BigDecimal(object[5].toString()));	
					etatPrixMoyen.setMontantHT(etatPrixMoyen.getQuantite().multiply(etatPrixMoyen.getPrix()));
					listEtatPrixMoyen.add(etatPrixMoyen);
					
					
				}
  				
  				
  	
CalculerPrixMoyenPFService();
  				
	
  			
  				
  				for(int j=0;j<listEtatprixMoyenServiceAfficher.size();j++)
  				{
  					
  					PrixMoyenStockPF moyenStockPF=listEtatprixMoyenServiceAfficher.get(j);
  					for(int d=0;d<listEtatPrixMoyen.size();d++)
  					{
  						
  					EtatPrixMoyen etatPrixMoyen=listEtatPrixMoyen.get(d);
  						
  						if(moyenStockPF.getSousFamille()!=null && etatPrixMoyen.getSousfamille()!=null)
  						{
  							if(moyenStockPF.getSousFamille().getLiblle().equals(etatPrixMoyen.getSousfamille()))
	  						{
	  							if(!etatPrixMoyen.getFamille().equals(FamilleDechet.getLiblle()))
	  							{
	  								
	  								etatPrixMoyen.setPrix(moyenStockPF.getPrixMoyenFinale());
	  								etatPrixMoyen.setMontantHT(etatPrixMoyen.getPrix().multiply(etatPrixMoyen.getQuantite()));
	  								listEtatPrixMoyen.set(d, etatPrixMoyen);
	  								
	  							}
	  							
	  							
	  							
	  						}
  						}
  					
  						
  						
  						
  					}
  					
  					
  					
  				}
  				
  				
  				
  				
  				
  				
  				
  				
  				listEtatPrixMoyenAfficher.clear();
  			  
  			for(int d=0;d<listEtatPrixMoyen.size();d++)	
  			{
  				
  				EtatPrixMoyen etatPrixMoyen=listEtatPrixMoyen.get(d);
  				
  				
  				if(famillearticle !=null && sousfamillearticle==null && article==null)
  				{
  					
  					if(etatPrixMoyen.getFamille().equals(famillearticle.getLiblle()))
  					{
  						
  						listEtatPrixMoyenAfficher.add(etatPrixMoyen);
  					}
  					
  					
  					
  				}else if(famillearticle !=null && sousfamillearticle!=null && article==null)
					{
						
						
  					if(etatPrixMoyen.getFamille().equals(famillearticle.getLiblle()) && etatPrixMoyen.getSousfamille().equals(sousfamillearticle.getLiblle()))
  					{
  						
  						listEtatPrixMoyenAfficher.add(etatPrixMoyen);
  					}
  					
						
						
						
					}else if(famillearticle !=null && sousfamillearticle!=null && article!=null)
					{
						
						
  					if(etatPrixMoyen.getFamille().equals(famillearticle.getLiblle()) && etatPrixMoyen.getSousfamille().equals(sousfamillearticle.getLiblle())  && etatPrixMoyen.getArticle().equals(article.getLiblle()))
  					{
  						
  						listEtatPrixMoyenAfficher.add(etatPrixMoyen);
  					}
  					
						
						
						
					}else
					{
						
						listEtatPrixMoyenAfficher.add(etatPrixMoyen);
						
					}
					
  				
  				
  				
  				
  				
  				
  				
  				
  			}
  				
  				
  				
  		
  			  
  		 
  			  
  		
  		  
			
		}else
		{
			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		}
	}else
	{
		JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}

	







	
	
}



	
public void CalculerPrixMoyenPF()
{
	
	

	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	listPrixMoyenStockMP.clear();
	listEtatprixMoyenService.clear();
	listDetailTransferProduitFiniTmp.clear();
	listDetailTransferProduitFini.clear();
	listDetailTransfertStockMP.clear();
  	boolean existe=false;
		Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
	
		
		Magasin magasinMP=mapMagasin.get(comboMagasinMP.getSelectedItem());
		String TousSubCategorie="";
		String Touscategorie="";
		String TousArticle="";
		String requete="";
		String requeteMP="";
		CategorieMp categorieMP=null;
		SubCategorieMp souscategorieMP=null;
		MatierePremier mp=null;
		
		if(magasin!=null && magasinMP!=null)
		{
			
			FamilleArticlePF famille=null;
			SousFamilleArticlePF sousFamille=null;
			
			requete="and  d.magasinDestination.id = '"+magasin.getId()+"' ";
			
			
			
			if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
    		{
				
    		if(	DateUtils.nbJoursEntre(dateChooserdebut.getDate(), dateChooserfin.getDate())>=0)
    		{
    			
    			String dateDu=formatter.format(dateChooserdebut.getDate());
    			String dateAu=formatter.format(dateChooserfin.getDate());
    			
    			
    			requete=requete+"and  d.transferStockPF.dateTransfer between '"+dateDu +"' and '"+dateAu+"' ";
    			
    			

    			
    			if(famille!=null)
    			{
    				requete=requete+"and  d.sousFamille.famileArticlePF.id = '"+famille.getId()+"' ";
    				
    			}
    			
    			if(sousFamille!=null)
    			{
    				requete=requete+"and  d.sousFamille.id = '"+sousFamille.getId()+"' ";
    				
    			}
    			
    		
    			
    			Articles articles=null;
    		
    			
    			if(articles!=null)
    			{
    				
    				requete=requete+"and  d.article.id = '"+articles.getId()+"' ";	
    				
    				
    			}
    			
    			boolean trouve=false;
    			BigDecimal quantite=BigDecimal.ZERO;
					BigDecimal quantitefoiprix=BigDecimal.ZERO;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
				listDetailTransferProduitFiniTmp=detailTransferProduitFiniDAO.listeDetailTransfertPFService(requete);
			
				if(listDetailTransferProduitFiniTmp.size()!=0)
				{
					
					
					 for(int k=0;k<listDetailTransferProduitFiniTmp.size();k++)
					 {
						 DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFiniTmp.get(k);
						 trouve=false;
						 
						MatierePremier matierePremier=null;
						TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(listDetailTransferProduitFiniTmp.get(k).getTransferStockPF().getCodeTransfer());
						Production production=productionDAO.findByNumOF (listDetailTransferProduitFiniTmp.get(k).getTransferStockPF().getCodeTransfer(), transferStockMP.getDepot().getCode());
						listCoutMP=productionDAO.listeCoutMP(production.getId());
						for(int t=0;t<listCoutMP.size();t++)
						{
							if(listCoutMP.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	                         {
								matierePremier=listCoutMP.get(t).getMatierePremier();
	                         }
							
							
						}
						
						 if(listDetailTransferProduitFini.size()!=0)
						 {
							
							 for(int i=0;i<listDetailTransferProduitFini.size();i++)
							 {
								 
								MatierePremier matierePremierTmp=null;
  								TransferStockMP transferStockMPTmp=transferStockMPDAO.findTransferByCode(listDetailTransferProduitFini.get(i).getTransferStockPF().getCodeTransfer());
  								Production productionTmp=productionDAO.findByNumOF(listDetailTransferProduitFini.get(i).getTransferStockPF().getCodeTransfer(), transferStockMPTmp.getDepot().getCode());
  								listCoutMPTmp=productionDAO.listeCoutMP(productionTmp.getId());
								 
  								for(int t=0;t<listCoutMPTmp.size();t++)
  								{
  									if(listCoutMPTmp.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
	  		                         {
  										matierePremierTmp=listCoutMPTmp.get(t).getMatierePremier();
	  		                         }
  									
  									
  								}
  								
  								
								 if(listDetailTransferProduitFini.get(i).getSousFamille().getId()==detailTransferProduitFini.getSousFamille().getId())
								 {
									 
									 if(matierePremierTmp!=null && matierePremier!=null)
									 {
										 
										if(matierePremierTmp.getId()==matierePremier.getId())
	  									{
											
											 trouve=true;
											
	  									}
									 }
									
									 
									
									 
									 
									 
									 
								 }
								 
							 }
							 
						 }else
						 {
							 listDetailTransferProduitFini.add(detailTransferProduitFini);
							trouve=true;
						 }
						 
						 if(trouve==false)
						 {
							 listDetailTransferProduitFini.add(detailTransferProduitFini);
						 }
					 }
					
					
					
					 CalculerPrixMoyenservice();
					
					if(listDetailTransferProduitFini.size()!=0)
					{
						for(int i=0;i<listDetailTransferProduitFini.size();i++)
						{
							SousFamilleArticlePF sousfamilleArticle =listDetailTransferProduitFini.get(i).getSousFamille();
							Articles article=listDetailTransferProduitFini.get(i).getArticle();
							quantite=BigDecimal.ZERO;
							quantitefoiprix=BigDecimal.ZERO;
							MatierePremier matierePremier=null;
							TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(listDetailTransferProduitFini.get(i).getTransferStockPF().getCodeTransfer());
							Production production=productionDAO.findByNumOF (listDetailTransferProduitFini.get(i).getTransferStockPF().getCodeTransfer(), transferStockMP.getDepot().getCode());
							listCoutMP=productionDAO.listeCoutMP(production.getId());
							for(int t=0;t<listCoutMP.size();t++)
							{
								if(listCoutMP.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
  		                         {
									matierePremier=listCoutMP.get(t).getMatierePremier();
  		                         }
								
								
							}
							
							
						for(int j=0;j<listDetailTransferProduitFiniTmp.size();j++)	
						{
							
							MatierePremier matierePremierTmp=null;
							TransferStockMP transferStockMPTmp=transferStockMPDAO.findTransferByCode(listDetailTransferProduitFiniTmp.get(j).getTransferStockPF().getCodeTransfer());
							Production productionTmp=productionDAO.findByNumOF(listDetailTransferProduitFiniTmp.get(j).getTransferStockPF().getCodeTransfer(), transferStockMPTmp.getDepot().getCode());
							listCoutMPTmp=productionDAO.listeCoutMP(productionTmp.getId());
							
							for(int t=0;t<listCoutMPTmp.size();t++)
							{
								if(listCoutMPTmp.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
  		                         {
									matierePremierTmp=listCoutMPTmp.get(t).getMatierePremier();
  		                         }
								
								
							}
							
							
							if(listDetailTransferProduitFiniTmp.get(j).getSousFamille().getId()== sousfamilleArticle.getId())
							{
								 if(matierePremierTmp!=null && matierePremier!=null)
								 {
									 
									if(matierePremierTmp.getId()==matierePremier.getId())
  									{
  										
  										FactureServiceProduction factureServiceProduction=factureServiceProductionDAO.findByNumOF(listDetailTransferProduitFiniTmp.get(j).getTransferStockPF().getCodeTransfer());
	  									DetailFactureServiceProduction detailFactureServiceProduction= detailFactureServiceProductionDAO.DetailFactureServiceProductionByFactureByArticle(factureServiceProduction.getId(), listDetailTransferProduitFiniTmp.get(j).getArticle().getLiblle());
	  									
	  									quantitefoiprix=quantitefoiprix.add(detailFactureServiceProduction.getQuantite().multiply(detailFactureServiceProduction.getPrix()));
	  									quantite=quantite.add(detailFactureServiceProduction.getQuantite());
	  									
	  									
  									}
								 }
							
								
							
								
							}
							
							
						}
						
						
						
					
						
						PrixMoyenStockPF prixMoyenStockPF=new PrixMoyenStockPF();
						
						prixMoyenStockPF.setSousFamille(sousfamilleArticle);		  							
						prixMoyenStockPF.setQuantiteService(quantite);
						boolean trouver=false;
							for(int d=0;d<listEtatPrixMoyenserviceParSousFamille.size();d++)
							{
								
								if(listEtatPrixMoyenserviceParSousFamille.get(d).getSousFamilleArticlePF().getId()==prixMoyenStockPF.getSousFamille().getId())
								{
									
									prixMoyenStockPF.setPrixMoyenService(listEtatPrixMoyenserviceParSousFamille.get(d).getPrixMoyen());
									trouver=true;
									
								}
								
								
								
								
							}
							
							
							if(trouver==false)
							{
							
								prixMoyenStockPF.setPrixMoyenService(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
							}
							
						
						
					
						
						
						
						prixMoyenStockPF.setMontantHTService(prixMoyenStockPF.getPrixMoyenService().multiply(prixMoyenStockPF.getQuantiteService()));
						
						
						prixMoyenStockPF.setMp(matierePremier);
						prixMoyenStockPF.setQuantiteMP(BigDecimal.ZERO);
						prixMoyenStockPF.setPrixMoyenMP(BigDecimal.ZERO);
						prixMoyenStockPF.setMontantHTMP(BigDecimal.ZERO);
						
						prixMoyenStockPF.setQuantiteFinale(prixMoyenStockPF.getQuantiteService());
						prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenService());
						prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getMontantHTService());
						listEtatprixMoyenService.add(prixMoyenStockPF);
						
							
						}
						
						
						
		 ////////////////////////////////////////////////////////////////////////// MP /////////////////////////////////////////////////////////////////////////////////
		  				
						
						requeteMP=" d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_INITIAL+"' ";
		    			
						requeteMP=requeteMP+"and  d.magasinDestination.id = '"+magasinMP.getId()+"' ";
						
						requeteMP=requeteMP+"and  d.transferStockMP.dateTransfer between '"+dateDu +"' and '"+dateAu+"' ";

						
				///////////////////////////////////////////////////////////////////////// MP ///////////////////////////////////////////////////////////////		
						
			  	       	listDetailTransfertStockMP=detailTransferStockMPDAO.listeDetailTransfertMP(requeteMP);
			  			 for(int i=0;i<listDetailTransfertStockMP.size();i++)
			  			 {
			  				 
			  				DetailTransferStockMP detailTransferStockMP= listDetailTransfertStockMP.get(i);
			  				
			  			PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
			  			prixMoyenStockMP.setMp(detailTransferStockMP.getMatierePremier());
			  			prixMoyenStockMP.setPrixInitial(detailTransferStockMP.getPrixUnitaire());
			  			prixMoyenStockMP.setQuantiteInitial(detailTransferStockMP.getQuantite());
			  			prixMoyenStockMP.setMontantInitial(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
			  			prixMoyenStockMP.setQuantiteAchat(BigDecimal.ZERO);	
			  			prixMoyenStockMP.setPrixAchat(BigDecimal.ZERO);
			  			prixMoyenStockMP.setMontantAchat(BigDecimal.ZERO);
			  			prixMoyenStockMP.setQuantiteFinale(detailTransferStockMP.getQuantite());
			  			prixMoyenStockMP.setPrixMoyen(detailTransferStockMP.getPrixUnitaire());
			  			prixMoyenStockMP.setMontantHTFinale(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
			  			listPrixMoyenStockMP.add(prixMoyenStockMP);
			  				 
			  				 
			  			 }
			  				  
			  				
			  				listeObject=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dateChooserdebut.getDate(), dateChooserfin.getDate(),magasinMP,souscategorieMP,mp, TousSubCategorie,TousArticle, categorieMP , Touscategorie);
			  				
			  				
			  				
			  				for(int j=0;j<listeObject.size();j++)
			  				{
			  					existe=false;
			  					 Object[] object=listeObject.get(j);
			  					 
			  					 for(int k=0;k<listPrixMoyenStockMP.size();k++)
			  					 {
			  						 
			  						PrixMoyenStockMP prixMoyenStockMP=listPrixMoyenStockMP.get(k); 
			  						 
			  						 
			  						 
			  						if(object[0].toString().equals(prixMoyenStockMP.getMp().getCode()))
			  						{
			  							
			  							existe=true;
			  							
			  							prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
							  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
							  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
							  			prixMoyenStockMP.setQuantiteFinale(prixMoyenStockMP.getQuantiteAchat().add(prixMoyenStockMP.getQuantiteInitial()));
							  			prixMoyenStockMP.setPrixMoyen((prixMoyenStockMP.getMontantInitial().add(prixMoyenStockMP.getMontantAchat())).divide(prixMoyenStockMP.getQuantiteAchat().add(prixMoyenStockMP.getQuantiteInitial()), 6, RoundingMode.HALF_UP));
							  			prixMoyenStockMP.setMontantHTFinale(prixMoyenStockMP.getQuantiteFinale().multiply(prixMoyenStockMP.getPrixMoyen()));
							  			listPrixMoyenStockMP.set(k, prixMoyenStockMP);
			  							
			  						}
			  						
			  						 
			  					 }
			  					
			  					 if(existe==false)
			  					 {
			  						
			  						MatierePremier matierePremier=MatierPremierDAO.findByCode(object[0].toString()) ;
			  						 
			  						PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
						  			prixMoyenStockMP.setMp(matierePremier);
						  			prixMoyenStockMP.setPrixInitial(BigDecimal.ZERO);
						  			prixMoyenStockMP.setQuantiteInitial(BigDecimal.ZERO);
						  			prixMoyenStockMP.setMontantInitial(BigDecimal.ZERO);
						  			prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
						  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
						  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
						  			prixMoyenStockMP.setQuantiteFinale(new BigDecimal(object[4].toString()));
						  			prixMoyenStockMP.setPrixMoyen(new BigDecimal(object[5].toString()));
						  			prixMoyenStockMP.setMontantHTFinale(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
						  			listPrixMoyenStockMP.add(prixMoyenStockMP); 
			  						 
			  						 
			  						 
			  						 
			  						 
			  						 
			  					 }
			  					 
			  					 
			  					 
			  					 
			  					 
			  					
			  				}	
						
						
				
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
			  				boolean trouvesousfamille=false;
			  			boolean trouvemp=false;
			  				
			  			for(int i=0;i<listPrixMoyenStockMP.size();i++)	
			  			{
			  			trouvesousfamille=false;
			  		trouvemp=false;
			  			PrixMoyenStockMP prixMoyenStockMP=listPrixMoyenStockMP.get(i);	
			  				
			  			SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(prixMoyenStockMP.getMp());
			  			
			  			for(int j=0;j<listEtatprixMoyenService.size();j++)	
			  			{
			  				
			  			PrixMoyenStockPF prixMoyenStockPF=listEtatprixMoyenService.get(j);
			  				if(sousFamilleEnVrac!=null)
			  				{
			  					if(prixMoyenStockPF.getSousFamille()!=null)
			  					{
			  						
			  						
			  						if(sousFamilleEnVrac.getSousfamile().getId()==prixMoyenStockPF.getSousFamille().getId())
				  					{
				  						
				  						
				  					if(prixMoyenStockPF.getMp()!=null)
				  					{
				  						
				  						if(prixMoyenStockPF.getMp().getId()==sousFamilleEnVrac.getMatierePremier().getId())
				  						{
				  							
				  							
				  						prixMoyenStockPF.setMp(prixMoyenStockMP.getMp());
  		  					  			prixMoyenStockPF.setQuantiteMP(prixMoyenStockMP.getQuantiteFinale());
  		  					  		prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
  		  					  	prixMoyenStockPF.setMontantHTMP(prixMoyenStockMP.getMontantHTFinale());	
  		  					  prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenFinale().add(prixMoyenStockPF.getPrixMoyenMP()));
  		  					prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getQuantiteFinale().multiply(prixMoyenStockPF.getPrixMoyenFinale()));
  		  					listEtatprixMoyenService.set(j, prixMoyenStockPF);
				  							
  		  				trouvesousfamille=true;
	  						
	  						
		  					trouvemp=true;	
				  							
				  							
				  							
				  							
				  						}
				  						
				  						
				  						
				  						
				  					}else
				  					{
				  						
				  					
				  						
				  					prixMoyenStockPF.setMp(prixMoyenStockMP.getMp());
	  					  			prixMoyenStockPF.setQuantiteMP(prixMoyenStockMP.getQuantiteFinale());
	  					  		prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
	  					  	prixMoyenStockPF.setMontantHTMP(prixMoyenStockMP.getMontantHTFinale());	
	  					  prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenFinale().add(prixMoyenStockPF.getPrixMoyenMP()));
	  					prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getQuantiteFinale().multiply(prixMoyenStockPF.getPrixMoyenFinale()));
	  					listEtatprixMoyenService.set(j, prixMoyenStockPF);
				  						
	  				trouvesousfamille=true;
  						
  						
	  					trouvemp=true;
				  					}
				  					
	  					  			
				  							
				  						
				  						
				  						
				  					
		  							
				  						
				  			
				  		
				  					}
			  						
			  						
			  						
			  						
			  						
			  							
			  						
			  			
			  					
			  					
			  					
			  					
			  					}
			  			
			  					
			  					
			  				}
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  				
			  			}
			  			
			  			if(trouvesousfamille==false && trouvemp==false)
			  			{
			  				
			  				
			  			PrixMoyenStockPF prixMoyenStockPF=new PrixMoyenStockPF();
						
			  		SousFamilleEnVrac sousFamilleEnVracTmp=sousFamilleEnvracDAo.findByMP(prixMoyenStockMP.getMp());
			  			if(sousFamilleEnVracTmp!=null)
			  			{
			  			prixMoyenStockPF.setSousFamille(sousFamilleEnVracTmp.getSousfamile());	
			  			}else
			  			{
			  			prixMoyenStockPF.setSousFamille(null);	
			  			}
							
						
							  							
						prixMoyenStockPF.setQuantiteService(BigDecimal.ZERO);
						prixMoyenStockPF.setPrixMoyenService(BigDecimal.ZERO);
						prixMoyenStockPF.setMontantHTService(BigDecimal.ZERO);
								  							
						prixMoyenStockPF.setMp(prixMoyenStockMP.getMp());
						prixMoyenStockPF.setQuantiteMP(prixMoyenStockMP.getQuantiteFinale());
						prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
						prixMoyenStockPF.setMontantHTMP(prixMoyenStockMP.getMontantHTFinale());
						
						prixMoyenStockPF.setQuantiteFinale(prixMoyenStockPF.getQuantiteMP());
						prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenMP());
						prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getMontantHTMP());
						listEtatprixMoyenService.add(prixMoyenStockPF);
			  				
			  			
			  			}
			  			
			  			}
			  			
			  				
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
						
					}else
					{
						JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
						listEtatprixMoyenService.clear();
					//	afficher_tableEtatVenteArticle(listEtatprixMoyenService);
						return;
					}
					
				}
				
				
							  
	  			  
	  			  
	  		 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	  			  
	  		
	  		  
    			
    		}else
    		{
    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    		
    		}
			
			listEtatprixMoyenServiceAfficher.clear();
			
			for(int t=0;t<listEtatprixMoyenService.size();t++)
			{
				
				PrixMoyenStockPF prixMoyenStockPF=listEtatprixMoyenService.get(t);
				if(famille!=null)
				{
					
				if(sousFamille!=null)	
				{
				
					if(prixMoyenStockPF.getSousFamille()!=null)
					{
						
						if(prixMoyenStockPF.getSousFamille().getId()==sousFamille.getId())
						{
							listEtatprixMoyenServiceAfficher.add(prixMoyenStockPF);
							
							
						}
						
						
					}
					
					
					
				}else
				{
					
					if(prixMoyenStockPF.getSousFamille()!=null)
					{
						
						if(prixMoyenStockPF.getSousFamille().getFamileArticlePF().getId()==famille.getId())
						{
							listEtatprixMoyenServiceAfficher.add(prixMoyenStockPF);
							
							
						}
						
						
					}
					
					
					
				}
					
					
					
				}else
				{
					
					listEtatprixMoyenServiceAfficher.add(prixMoyenStockPF);
					
				}
				
				
				
			}
			
			
			
			
			CalculerPrixMoyenMP();
			
			for(int t=0;t<listPrixMoyenStockMP.size();t++)
			{
				
				
				PrixMoyenStockMP prixMoyenStockMP=listPrixMoyenStockMP.get(t);
			
				
				for(int j=0;j<listEtatprixMoyenServiceAfficher.size();j++)
				{
					
					PrixMoyenStockPF prixMoyenStockPF=listEtatprixMoyenServiceAfficher.get(j);
					
					
					if(prixMoyenStockMP.getSousfamille()!=null)
					{
						//System.out.println(prixMoyenStockMP.getMp().getCode());	
        		    
						
						
						if(prixMoyenStockPF.getSousFamille()!=null)
						{
							
							if(prixMoyenStockPF.getSousFamille().getLiblle().equals(prixMoyenStockMP.getSousfamille()))
							{
								
								prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
								prixMoyenStockPF.setMontantHTMP(prixMoyenStockPF.getQuantiteMP().multiply(prixMoyenStockMP.getPrixMoyen()));
								prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenMP().add(prixMoyenStockPF.getPrixMoyenService()));
								prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getPrixMoyenFinale().multiply(prixMoyenStockPF.getQuantiteFinale()));
								listEtatprixMoyenServiceAfficher.set(j, prixMoyenStockPF);
							}
							
							
							
						}else
						{
							
							if(prixMoyenStockPF.getMp().getId()==prixMoyenStockMP.getMp().getId())
							{
								
								prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
								prixMoyenStockPF.setMontantHTMP(prixMoyenStockPF.getQuantiteMP().multiply(prixMoyenStockMP.getPrixMoyen()));
								prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenMP().add(prixMoyenStockPF.getPrixMoyenService()));
								prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getPrixMoyenFinale().multiply(prixMoyenStockPF.getQuantiteFinale()));
								listEtatprixMoyenServiceAfficher.set(j, prixMoyenStockPF);
								
								
							}
							
							
							
							
							
							
						}
						
						
						
						
						
					}else
					{
						
						if(prixMoyenStockPF.getSousFamille()==null)
						{
						
							if(prixMoyenStockPF.getMp().getId()==prixMoyenStockMP.getMp().getId())
							{
								
								
								prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
								prixMoyenStockPF.setMontantHTMP(prixMoyenStockPF.getQuantiteMP().multiply(prixMoyenStockMP.getPrixMoyen()));
								prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenMP().add(prixMoyenStockPF.getPrixMoyenService()));
								prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getPrixMoyenFinale().multiply(prixMoyenStockPF.getQuantiteFinale()));
								listEtatprixMoyenServiceAfficher.set(j, prixMoyenStockPF);
								
								
							}
							
							
							
						}
						
						
						
						
					}
					
					
					
					
					
				}
				
				
				
				
				
				
			}
			
			
			
			
			
			
			
			
		}else
		{
			JOptionPane.showMessageDialog(null, "Veuillez selectionner les magasin PF et MP SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}

		


	
	
	
	

	
	
	
	
}	




public void CalculerPrixMoyenMP()
{
	
	

	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	listPrixMoyenStockMP.clear();
  	boolean existe=false;
  	

		
		Magasin magasin=mapMagasin.get(comboMagasinMP.getSelectedItem().toString());
		String TousSubCategorie="";
		String Touscategorie="";
		String TousArticle="";
		String requete="";
		if(magasin!=null)
		{
			
			requete=" d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_INITIAL+"' ";
			
			requete=requete+"and  d.magasinDestination.id = '"+magasin.getId()+"' ";
			
			if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
    		{
				
    		if(	DateUtils.nbJoursEntre(dateChooserdebut.getDate(), dateChooserfin.getDate())>=0)
    		{
    			
    			String dateDu=formatter.format(dateChooserdebut.getDate());
    			String dateAu=formatter.format(dateChooserfin.getDate());
    			
    			
    			requete=requete+"and  d.transferStockMP.dateTransfer between '"+dateDu +"' and '"+dateAu+"' ";
    			
    			
    			CategorieMp categorieMP=null;
    			SubCategorieMp souscategorieMP=null;
    			
    	
    			
    			
    		
    			
    			if(souscategorieMP!=null)
    			{
    				requete=requete+"and  d.matierePremier.categorieMp.subCategorieMp.id = '"+souscategorieMP.getId()+"' ";
    				
    			}
    			
    			if(categorieMP!=null)
    			{
    				requete=requete+"and  d.matierePremier.categorieMp.id = '"+categorieMP.getId()+"' ";
    				
    			}
    			
    		
    			
    			MatierePremier mp=null;
    		
    			
    			if(mp!=null)
    			{
    				
    				requete=requete+"and  d.matierePremier.id = '"+mp.getId()+"' ";	
    				
    				
    			}
    			
    			
    			
    			
	  	       	listDetailTransfertStockMP=detailTransferStockMPDAO.listeDetailTransfertMP(requete);
	  			 for(int i=0;i<listDetailTransfertStockMP.size();i++)
	  			 {
	  				 
	  				DetailTransferStockMP detailTransferStockMP= listDetailTransfertStockMP.get(i);
	  				
	  			PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
	  			prixMoyenStockMP.setMp(detailTransferStockMP.getMatierePremier());
	  			prixMoyenStockMP.setPrixInitial(detailTransferStockMP.getPrixUnitaire());
	  			prixMoyenStockMP.setQuantiteInitial(detailTransferStockMP.getQuantite());
	  			prixMoyenStockMP.setMontantInitial(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
	  			prixMoyenStockMP.setQuantiteAchat(BigDecimal.ZERO);	
	  			prixMoyenStockMP.setPrixAchat(BigDecimal.ZERO);
	  			prixMoyenStockMP.setMontantAchat(BigDecimal.ZERO);
	  			prixMoyenStockMP.setQuantiteFinale(detailTransferStockMP.getQuantite());
	  			prixMoyenStockMP.setPrixMoyen(detailTransferStockMP.getPrixUnitaire());
	  			prixMoyenStockMP.setMontantHTFinale(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
	  			
	  			
	  			SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(detailTransferStockMP.getMatierePremier());
	  			if(sousFamilleEnVrac!=null)
	  			{
	  				
	  				prixMoyenStockMP.setFamille(sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle());
	  				prixMoyenStockMP.setSousfamille(sousFamilleEnVrac.getSousfamile().getLiblle());
	  				
	  			}else
	  			{
	  				
	  				
	  				prixMoyenStockMP.setFamille(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
	  				prixMoyenStockMP.setSousfamille(detailTransferStockMP.getMatierePremier().getCategorieMp().getNom());
	  				
	  				
	  			}
	  			
	  			listPrixMoyenStockMP.add(prixMoyenStockMP);
	  				 
	  				 
	  			 }
	  			 
	  			CalculerPrixMoyenInitial()	;
  			
  				
  				
  			for(int i=0;i<listEtatInitialMP.size();i++)	
  			{
  				
  				EtatInitialParSousCtaegorieMP etatInitialParSousCtaegorieMP=listEtatInitialMP.get(i);
  				
  			for(int j=0;j<listPrixMoyenStockMP.size();j++)	
  			{
  				
  				PrixMoyenStockMP moyenStockMP=listPrixMoyenStockMP.get(j);
  				
  				if(etatInitialParSousCtaegorieMP.getSousFamilleArticlePF().equals(moyenStockMP.getSousfamille()))
  				{
  					
  					
  					moyenStockMP.setPrixInitial(etatInitialParSousCtaegorieMP.getPrixMoyen());
  					
  					moyenStockMP.setMontantInitial(etatInitialParSousCtaegorieMP.getPrixMoyen().multiply(moyenStockMP.getQuantiteInitial()));
  					moyenStockMP.setPrixMoyen(etatInitialParSousCtaegorieMP.getPrixMoyen());
  					moyenStockMP.setMontantHTFinale(etatInitialParSousCtaegorieMP.getPrixMoyen().multiply(moyenStockMP.getQuantiteInitial()));
  					
  					listPrixMoyenStockMP.set(j, moyenStockMP);
  					
  				}
  				
  				
  				
  			}
  				
  				
  				
  				
  				
  			}
	  			 
	  			 
	  			 
	  			 
	  				  
	  				
	  				listeObject=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dateChooserdebut.getDate(), dateChooserfin.getDate(),magasin,souscategorieMP,mp, TousSubCategorie,TousArticle, categorieMP , Touscategorie);
	  				
	  				
	  				
	  				for(int j=0;j<listeObject.size();j++)
	  				{
	  					existe=false;
	  					 Object[] object=listeObject.get(j);
	  					 
	  					 for(int k=0;k<listPrixMoyenStockMP.size();k++)
	  					 {
	  						 
	  						PrixMoyenStockMP prixMoyenStockMP=listPrixMoyenStockMP.get(k); 
	  						 
	  						 
	  						 
	  						if(object[0].toString().equals(prixMoyenStockMP.getMp().getCode()))
	  						{
	  							
	  							existe=true;
	  							
	  							prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
					  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
					  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
					  			prixMoyenStockMP.setQuantiteFinale(prixMoyenStockMP.getQuantiteAchat().add(prixMoyenStockMP.getQuantiteInitial()));
					  			prixMoyenStockMP.setPrixMoyen((prixMoyenStockMP.getMontantInitial().add(prixMoyenStockMP.getMontantAchat())).divide(prixMoyenStockMP.getQuantiteAchat().add(prixMoyenStockMP.getQuantiteInitial()), 6, RoundingMode.HALF_UP));
					  			prixMoyenStockMP.setMontantHTFinale(prixMoyenStockMP.getQuantiteFinale().multiply(prixMoyenStockMP.getPrixMoyen()));
					  			listPrixMoyenStockMP.set(k, prixMoyenStockMP);
	  							
	  						}
	  						
	  						 
	  					 }
	  					
	  					 if(existe==false)
	  					 {
	  						
	  						MatierePremier matierePremier=MatierPremierDAO.findByCode(object[0].toString()) ;
	  						 
	  						SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(matierePremier);
	  						
	  						
	  						
	  						PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
				  			prixMoyenStockMP.setMp(matierePremier);
				  			prixMoyenStockMP.setPrixInitial(BigDecimal.ZERO);
				  			prixMoyenStockMP.setQuantiteInitial(BigDecimal.ZERO);
				  			prixMoyenStockMP.setMontantInitial(BigDecimal.ZERO);
				  			prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
				  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
				  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
				  			prixMoyenStockMP.setQuantiteFinale(new BigDecimal(object[4].toString()));
				  			prixMoyenStockMP.setPrixMoyen(new BigDecimal(object[5].toString()));
				  			prixMoyenStockMP.setMontantHTFinale(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
				  			
				  			if(sousFamilleEnVrac!=null)
				  			{
				  				
				  				prixMoyenStockMP.setFamille(sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle());
				  				prixMoyenStockMP.setSousfamille(sousFamilleEnVrac.getSousfamile().getLiblle());
				  				
				  			}else
				  			{
				  				
				  				
				  				prixMoyenStockMP.setFamille(matierePremier.getCategorieMp().getSubCategorieMp().getNom());
				  				prixMoyenStockMP.setSousfamille(matierePremier.getCategorieMp().getNom());
				  				
				  				
				  			}
				  			
				  			
				  			listPrixMoyenStockMP.add(prixMoyenStockMP); 
	  						 
	  						 
	  						 
	  						 
	  						 
	  						 
	  					 }
	  					 
	  					 
	  					 
	  					 
	  					 
	  					
	  				}
	  				
	  				
	  				CalculerPrixMoyenAchat();
	  				
	  				
	  				for(int i=0;i<listeObjectPrixMoyenAchat.size();i++)	
		  			{
		  				
	  					 Object[] object=listeObjectPrixMoyenAchat.get(i);
						 
						 String famille="";
						 String sousFamille="";
					
						 MatierePremier matierePremier=MatierPremierDAO.findByCode(object[0].toString());
						 
						 SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(matierePremier);
						 
						 if(sousFamilleEnVrac!=null)
						 {
							 
							famille=sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle(); 
							sousFamille=sousFamilleEnVrac.getSousfamile().getLiblle();
							 
						 }else
						 {
							 
							 famille=matierePremier.getCategorieMp().getSubCategorieMp().getNom(); 
								sousFamille=matierePremier.getCategorieMp().getNom();
							 
							 
						 }
		  				
		  			for(int j=0;j<listPrixMoyenStockMP.size();j++)	
		  			{
		  				
		  				PrixMoyenStockMP moyenStockMP=listPrixMoyenStockMP.get(j);
		  				
		  				if(sousFamille.equals(moyenStockMP.getSousfamille()))
		  				{
		  					
		  					
		  					moyenStockMP.setPrixAchat (new BigDecimal(object[5].toString()));
		  					
		  					moyenStockMP.setMontantAchat(new BigDecimal(object[5].toString()).multiply(moyenStockMP.getQuantiteAchat()));
		  					
		  					moyenStockMP.setQuantiteFinale(moyenStockMP.getQuantiteAchat().add(moyenStockMP.getQuantiteInitial()));
		  					moyenStockMP.setPrixMoyen((moyenStockMP.getMontantInitial().add(moyenStockMP.getMontantAchat())).divide(moyenStockMP.getQuantiteAchat().add(moyenStockMP.getQuantiteInitial()), 6, RoundingMode.HALF_UP));
		  					moyenStockMP.setMontantHTFinale(moyenStockMP.getQuantiteFinale().multiply(moyenStockMP.getPrixMoyen()));
		  					
		  					listPrixMoyenStockMP.set(j, moyenStockMP);
		  					
		  				}
		  				
		  				
		  				
		  			}
		  				
		  				
		  				
		  				
		  				
		  			}
	  				
	  				
	  				
	  				
	  				
	  				
	  				
	  			
	  				  
	  			  
	  			  
	  		 
	  			  
	  		
	  		  
    			
    		}else
    		{
    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    		
    		}
		}else
		{
			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}

		


	
	
	
	

	
	
	
	
}





public void CalculerPrixMoyenAchat()	
{


	
Magasin magasin=mapMagasin.get(comboMagasinMP.getSelectedItem().toString());
String TousSubCategorie="";
String Touscategorie="";
String TousArticle="";
if(magasin!=null)
{
	if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
	{
		
	if(	DateUtils.nbJoursEntre(dateChooserdebut.getDate(), dateChooserfin.getDate())>=0)
	{
		
		CategorieMp categorieMP=null;
		SubCategorieMp souscategorieMP=null;
		
	

		
	
		
		MatierePremier mp=null;

		
		
			 
				  
				
		listeObjectPrixMoyenAchat=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dateChooserdebut.getDate(), dateChooserfin.getDate(),magasin,souscategorieMP,mp, TousSubCategorie,TousArticle, categorieMP , Touscategorie);
				
				  
		
	}else
	{
		JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}
	
	}
}else
{
	JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	return;
}














}


public void CalculerPrixMoyenInitial()	

{



		Magasin magasin=mapMagasin.get(comboMagasinMP.getSelectedItem().toString());
		
		if(magasin!=null)
		{
			
		BigDecimal quantite=BigDecimal.ZERO;
		BigDecimal quantitefoiprix=BigDecimal.ZERO;
	BigDecimal quantiteOffre=BigDecimal.ZERO;
	BigDecimal quantiteOffrefoiprix=BigDecimal.ZERO;
		
		
		
			boolean trouve=false;
			
		String dateDebut=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
	String dateFin=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
if(dateDebut.equals(""))	{
	JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
} else if(dateFin.equals("")){
	JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
	

	}else {
		
		listEtatInitialMP.clear();
		listDetailTransferStockMPTmp=detailTransferStockMPDAO.listeDetailTransfertMPByDateByMagasinByStatut(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasin,Constantes.ETAT_TRANSFER_STOCK_INITIAL);
	
		if(listDetailTransferStockMPTmp.size()!=0)
		{
			
			
			 for(int k=0;k<listDetailTransferStockMPTmp.size();k++)
			 {
				 DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPTmp.get(k);
				SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(detailTransferStockMP.getMatierePremier())	;
				 
				 trouve=false;
				
				 if(listDetailTransferStockMP.size()!=0)
				 {
					
					 for(int i=0;i<listDetailTransferStockMP.size();i++)
					 {
						 if(sousFamilleEnVrac!=null)
						 {
							SousFamilleEnVrac sousFamilleEnVracTmp=sousFamilleEnvracDAo.findByMP(listDetailTransferStockMP.get(i).getMatierePremier())	;
							 if(sousFamilleEnVracTmp!=null)
							 {
								 
								 if(sousFamilleEnVracTmp.getSousfamile().getId()==sousFamilleEnVrac.getSousfamile().getId())
								 {
									 
									 trouve=true;
									 
								 }
								 
								 
							 }
							 
							 
							 
							 
						 }else
						 {
							 
							 if(listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp().equals(detailTransferStockMP.getMatierePremier().getCategorieMp()))
							 {
								 trouve=true;
							 } 
							 
						 }
						 
						
						 
						 
						 
						 
					 }
					 
				 }else
				 {
					listDetailTransferStockMP.add(detailTransferStockMP);
					trouve=true;
				 }
				 
				 if(trouve==false)
				 {
					listDetailTransferStockMP.add(detailTransferStockMP);
				 }
			 }
			
			
			
		boolean existe=false;
		
		String famille="";
		String sousFamille="";
			if(listDetailTransferStockMP.size()!=0)
			{
				for(int i=0;i<listDetailTransferStockMP.size();i++)
				{
					CategorieMp sousCategorieMP =listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp();
					quantite=BigDecimal.ZERO;
					quantitefoiprix=BigDecimal.ZERO;
					
				SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(listDetailTransferStockMP.get(i).getMatierePremier())	;
				
				if(sousFamilleEnVrac==null)
				{
					famille=listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getNom();
					sousFamille=listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp().getNom();
					
				}else
				{
					
					famille=sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle();
					sousFamille=sousFamilleEnVrac.getSousfamile().getLiblle();
					
					
				}
					
					existe=false;
				for(int j=0;j<listDetailTransferStockMPTmp.size();j++)	
				{
					
					
					/*
					if(listDetailTransferStockMPTmp.get(j).getMatierePremier().getCategorieMp().equals(sousCategorieMP))
					{*/
					 
					   		if(sousFamilleEnVrac!=null)
					   		{
					   			
					   		SousFamilleEnVrac sousFamilleEnVracTmp=sousFamilleEnvracDAo.findByMP(listDetailTransferStockMPTmp.get(j).getMatierePremier())	;
					   		
					   		if(sousFamilleEnVracTmp!=null)
					   		{
					   			if(sousFamilleEnVracTmp.getSousfamile().getId()==sousFamilleEnVrac.getSousfamile().getId())
					   			{
					   			quantitefoiprix=quantitefoiprix.add(listDetailTransferStockMPTmp.get(j).getQuantite().multiply(listDetailTransferStockMPTmp.get(j).getPrixUnitaire()));
								quantite=quantite.add(listDetailTransferStockMPTmp.get(j).getQuantite());
								existe=true;
					   			}
					   	
							
							
					   		}
					   	 
					   			
					   		}else
					   		{
					   		if(listDetailTransferStockMPTmp.get(j).getMatierePremier().getCategorieMp().equals(sousCategorieMP))
					   		{
					   		quantitefoiprix=quantitefoiprix.add(listDetailTransferStockMPTmp.get(j).getQuantite().multiply(listDetailTransferStockMPTmp.get(j).getPrixUnitaire()));
							quantite=quantite.add(listDetailTransferStockMPTmp.get(j).getQuantite());
							existe=true;
					   		}
					   			
					   	
					   			
					   			
					   		}
					 
					   	
					   
						
						
						
				/*	}*/
					
					
				}
				

				if(existe==true)
				{
					
				
					EtatInitialParSousCtaegorieMP etatInitialParSousCtaegorieMP=new EtatInitialParSousCtaegorieMP();
					etatInitialParSousCtaegorieMP.setSousCategorie(sousCategorieMP);
					etatInitialParSousCtaegorieMP.setCategorie(sousCategorieMP.getSubCategorieMp());
					etatInitialParSousCtaegorieMP.setFamilleArticlePF(famille);
					etatInitialParSousCtaegorieMP.setSousFamilleArticlePF(sousFamille);
					etatInitialParSousCtaegorieMP.setTotalInitial(quantite);
					etatInitialParSousCtaegorieMP.setPrixMoyen(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
					etatInitialParSousCtaegorieMP.setMontant(etatInitialParSousCtaegorieMP.getPrixMoyen().multiply(etatInitialParSousCtaegorieMP.getTotalInitial()).setScale(6, RoundingMode.FLOOR));
					listEtatInitialMP.add(etatInitialParSousCtaegorieMP);
				}
				
				
			
				
					
				}
				
				
				
				
			}else
			{
				JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
				listEtatInitialMP.clear();
				
				return;
			}
			
		}
		
		
	}
			
		}
		

	
		
	








}



public void CalculerPrixMoyenservice()
{


		Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
		
		if(magasin!=null)
		{
			
		BigDecimal quantite=BigDecimal.ZERO;
		BigDecimal quantitefoiprix=BigDecimal.ZERO;
			boolean trouve=false;
			
		String dateDebut=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
	String dateFin=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
if(dateDebut.equals(""))	{
	JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
} else if(dateFin.equals("")){
	JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
	

	}else {
		
		listEtatPrixMoyenserviceParSousFamille.clear();
		listDetailTransferProduitFiniTmp=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesService (dateChooserdebut.getDate(), dateChooserfin.getDate(),magasin);
	
		if(listDetailTransferProduitFiniTmp.size()!=0)
		{
			
			
			 for(int k=0;k<listDetailTransferProduitFiniTmp.size();k++)
			 {
				 DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFiniTmp.get(k);
				 trouve=false;
				
				 if(listDetailTransferProduitFini.size()!=0)
				 {
					
					 for(int i=0;i<listDetailTransferProduitFini.size();i++)
					 {
						 if(listDetailTransferProduitFini.get(i).getSousFamille().equals(detailTransferProduitFini.getSousFamille()))
						 {
							 trouve=true;
						 }
						 
					 }
					 
				 }else
				 {
					 listDetailTransferProduitFini.add(detailTransferProduitFini);
					trouve=true;
				 }
				 
				 if(trouve==false)
				 {
					 listDetailTransferProduitFini.add(detailTransferProduitFini);
				 }
			 }
			
			
			
		
			
			if(listDetailTransferProduitFini.size()!=0)
			{
				for(int i=0;i<listDetailTransferProduitFini.size();i++)
				{
					SousFamilleArticlePF sousfamilleArticle =listDetailTransferProduitFini.get(i).getSousFamille();
					quantite=BigDecimal.ZERO;
					quantitefoiprix=BigDecimal.ZERO;
					
				for(int j=0;j<listDetailTransferProduitFiniTmp.size();j++)	
				{
					
					
					
					if(listDetailTransferProduitFiniTmp.get(j).getSousFamille().equals(sousfamilleArticle))
					{
						
						FactureServiceProduction factureServiceProduction=factureServiceProductionDAO.findByNumOF(listDetailTransferProduitFiniTmp.get(j).getTransferStockPF().getCodeTransfer());
						DetailFactureServiceProduction detailFactureServiceProduction= detailFactureServiceProductionDAO.DetailFactureServiceProductionByFactureByArticle(factureServiceProduction.getId(), listDetailTransferProduitFiniTmp.get(j).getArticle().getLiblle());
						
						quantite=quantite.add(detailFactureServiceProduction.getQuantite());
						
						Production production=productionDAO.findByNumOF(detailFactureServiceProduction.getFactureService().getNumOF(),detailFactureServiceProduction.getFactureService().getDepot().getCode());
						
						List<CoutMP> ListeCoutMP=productionDAO.listeCoutMP(production.getId());
						
						
						for(int d=0;d<ListeCoutMP.size();d++)
						{
							
							CoutMP coutMP =ListeCoutMP.get(d);
						if(	coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						{
							
							
							if(coutMP.getQuantiteOffre().compareTo(BigDecimal.ZERO)!=0)						
								
							{
								
							
								quantitefoiprix=quantitefoiprix.add((detailFactureServiceProduction.getQuantite().subtract(coutMP.getQuantiteOffre())).multiply(detailFactureServiceProduction.getPrix()));

								
	
								
							}else
							{
								quantitefoiprix=quantitefoiprix.add(detailFactureServiceProduction.getQuantite().multiply(detailFactureServiceProduction.getPrix()));

							}
							
							
							
						}
							
							
							
							
						}
						
						
						
						
						
						
					}
					
					
				}
				
				EtatVenteParFamilleArticle etatventeFamilleArticle=new EtatVenteParFamilleArticle();
				etatventeFamilleArticle.setSousFamilleArticlePF(sousfamilleArticle);
				etatventeFamilleArticle.setTotalVente(quantite);
				if(quantite.compareTo(BigDecimal.ZERO)==0)
				{
					etatventeFamilleArticle.setPrixMoyen(quantite);
				}else
				{
					etatventeFamilleArticle.setPrixMoyen(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
				}
				
				etatventeFamilleArticle.setMontant(etatventeFamilleArticle.getPrixMoyen().multiply(etatventeFamilleArticle.getTotalVente()));
				listEtatPrixMoyenserviceParSousFamille.add(etatventeFamilleArticle);
				
					
				}
				
				
				
				
			}else
			{
				JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
				listEtatPrixMoyenserviceParSousFamille.clear();
			
				return;
			}
			
		}
		
		
	}
			
		}
		

	
		
	


}
		public void CalculerPrixMoyenPFService()
		{
			

			
			

			  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			listPrixMoyenStockMP.clear();
			listEtatprixMoyenService.clear();
listDetailTransferProduitFini.clear();
listDetailTransferProduitFiniTmp.clear();
			listDetailTransfertStockMP.clear();
		  	boolean existe=false;
	  		Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
	  		
	  		
	  		Magasin magasinMP=mapMagasin.get(comboMagasinMP.getSelectedItem());;
	  		String TousSubCategorie="";
	  		String Touscategorie="";
	  		String TousArticle="";
	  		String requete="";
	  		String requeteMP="";
	  		CategorieMp categorieMP=null;
				SubCategorieMp souscategorieMP=null;
				MatierePremier mp=null;
				
	  		if(magasin!=null && magasinMP!=null)
	  		{
	  			
	  			FamilleArticlePF famille=null;
	  			SousFamilleArticlePF sousFamille=null;
	  			
	  			requete="and  d.magasinDestination.id = '"+magasin.getId()+"' ";
	  			
	  			
	  			
	  			if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
		    		{
	  				
		    		if(	DateUtils.nbJoursEntre(dateChooserdebut.getDate(), dateChooserfin.getDate())>=0)
		    		{
		    			
		    			String dateDu=formatter.format(dateChooserdebut.getDate());
		    			String dateAu=formatter.format(dateChooserfin.getDate());
		    			
		    			
		    			requete=requete+"and  d.transferStockPF.dateTransfer between '"+dateDu +"' and '"+dateAu+"' ";
		    			
		    			

		    		/*	
		    			if(famille!=null)
		    			{
		    				requete=requete+"and  d.sousFamille.famileArticlePF.id = '"+famille.getId()+"' ";
		    				
		    			}
		    			
		    			if(sousFamille!=null)
		    			{
		    				requete=requete+"and  d.sousFamille.id = '"+sousFamille.getId()+"' ";
		    				
		    			}
		    			*/
		    		
		    			
		    			Articles articles=null;
		    		/*
		    			
		    			if(articles!=null)
		    			{
		    				
		    				requete=requete+"and  d.article.id = '"+articles.getId()+"' ";	
		    				
		    				
		    			}
		    			*/
		    			
		    			boolean trouve=false;
		    			BigDecimal quantite=BigDecimal.ZERO;
	   					BigDecimal quantitefoiprix=BigDecimal.ZERO;

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				
						listDetailTransferProduitFiniTmp=detailTransferProduitFiniDAO.listeDetailTransfertPFService(requete);
					
						if(listDetailTransferProduitFiniTmp.size()!=0)
						{
							
							
							 for(int k=0;k<listDetailTransferProduitFiniTmp.size();k++)
							 {
								 DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFiniTmp.get(k);
								 trouve=false;
								 
								MatierePremier matierePremier=null;
								TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(listDetailTransferProduitFiniTmp.get(k).getTransferStockPF().getCodeTransfer());
								Production production=productionDAO.findByNumOF (listDetailTransferProduitFiniTmp.get(k).getTransferStockPF().getCodeTransfer(), transferStockMP.getDepot().getCode());
								listCoutMP=productionDAO.listeCoutMP(production.getId());
								for(int t=0;t<listCoutMP.size();t++)
								{
									if(listCoutMP.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			                         {
										matierePremier=listCoutMP.get(t).getMatierePremier();
			                         }
									
									
								}
								
								 if(listDetailTransferProduitFini.size()!=0)
								 {
									
									 for(int i=0;i<listDetailTransferProduitFini.size();i++)
									 {
										 
										MatierePremier matierePremierTmp=null;
		  								TransferStockMP transferStockMPTmp=transferStockMPDAO.findTransferByCode(listDetailTransferProduitFini.get(i).getTransferStockPF().getCodeTransfer());
		  								Production productionTmp=productionDAO.findByNumOF(listDetailTransferProduitFini.get(i).getTransferStockPF().getCodeTransfer(), transferStockMPTmp.getDepot().getCode());
		  								listCoutMPTmp=productionDAO.listeCoutMP(productionTmp.getId());
										 
		  								for(int t=0;t<listCoutMPTmp.size();t++)
		  								{
		  									if(listCoutMPTmp.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			  		                         {
		  										matierePremierTmp=listCoutMPTmp.get(t).getMatierePremier();
			  		                         }
		  									
		  									
		  								}
		  								
		  								
										 if(listDetailTransferProduitFini.get(i).getSousFamille().getId()==detailTransferProduitFini.getSousFamille().getId())
										 {
											 
											 if(matierePremierTmp!=null && matierePremier!=null)
											 {
												 
												if(matierePremierTmp.getId()==matierePremier.getId())
			  									{
													
													 trouve=true;
													
			  									}
											 }
											
											 
											
											 
											 
											 
											 
										 }
										 
									 }
									 
								 }else
								 {
									 listDetailTransferProduitFini.add(detailTransferProduitFini);
									trouve=true;
								 }
								 
								 if(trouve==false)
								 {
									 listDetailTransferProduitFini.add(detailTransferProduitFini);
								 }
							 }
							
							
							
							 CalculerPrixMoyenservice();
							
							if(listDetailTransferProduitFini.size()!=0)
							{
								for(int i=0;i<listDetailTransferProduitFini.size();i++)
								{
									SousFamilleArticlePF sousfamilleArticle =listDetailTransferProduitFini.get(i).getSousFamille();
									Articles article=listDetailTransferProduitFini.get(i).getArticle();
									quantite=BigDecimal.ZERO;
									quantitefoiprix=BigDecimal.ZERO;
									MatierePremier matierePremier=null;
									TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(listDetailTransferProduitFini.get(i).getTransferStockPF().getCodeTransfer());
									Production production=productionDAO.findByNumOF (listDetailTransferProduitFini.get(i).getTransferStockPF().getCodeTransfer(), transferStockMP.getDepot().getCode());
									listCoutMP=productionDAO.listeCoutMP(production.getId());
									for(int t=0;t<listCoutMP.size();t++)
									{
										if(listCoutMP.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  		                         {
											matierePremier=listCoutMP.get(t).getMatierePremier();
		  		                         }
										
										
									}
									
									
								for(int j=0;j<listDetailTransferProduitFiniTmp.size();j++)	
								{
									
									MatierePremier matierePremierTmp=null;
									TransferStockMP transferStockMPTmp=transferStockMPDAO.findTransferByCode(listDetailTransferProduitFiniTmp.get(j).getTransferStockPF().getCodeTransfer());
									Production productionTmp=productionDAO.findByNumOF(listDetailTransferProduitFiniTmp.get(j).getTransferStockPF().getCodeTransfer(), transferStockMPTmp.getDepot().getCode());
									listCoutMPTmp=productionDAO.listeCoutMP(productionTmp.getId());
									
									for(int t=0;t<listCoutMPTmp.size();t++)
									{
										if(listCoutMPTmp.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  		                         {
											matierePremierTmp=listCoutMPTmp.get(t).getMatierePremier();
		  		                         }
										
										
									}
									
									
									if(listDetailTransferProduitFiniTmp.get(j).getSousFamille().getId()== sousfamilleArticle.getId())
									{
										 if(matierePremierTmp!=null && matierePremier!=null)
										 {
											 
											if(matierePremierTmp.getId()==matierePremier.getId())
		  									{
		  										
		  										FactureServiceProduction factureServiceProduction=factureServiceProductionDAO.findByNumOF(listDetailTransferProduitFiniTmp.get(j).getTransferStockPF().getCodeTransfer());
			  									DetailFactureServiceProduction detailFactureServiceProduction= detailFactureServiceProductionDAO.DetailFactureServiceProductionByFactureByArticle(factureServiceProduction.getId(), listDetailTransferProduitFiniTmp.get(j).getArticle().getLiblle());
			  									
			  									quantitefoiprix=quantitefoiprix.add(detailFactureServiceProduction.getQuantite().multiply(detailFactureServiceProduction.getPrix()));
			  									quantite=quantite.add(detailFactureServiceProduction.getQuantite());
			  									
			  									
		  									}
										 }
									
										
									
										
									}
									
									
								}
					
								
								PrixMoyenStockPF prixMoyenStockPF=new PrixMoyenStockPF();
								
								prixMoyenStockPF.setSousFamille(sousfamilleArticle);		  							
								prixMoyenStockPF.setQuantiteService(quantite);
								boolean trouver=false;
	  							for(int d=0;d<listEtatPrixMoyenserviceParSousFamille.size();d++)
	  							{
	  								
	  								if(listEtatPrixMoyenserviceParSousFamille.get(d).getSousFamilleArticlePF().getId()==prixMoyenStockPF.getSousFamille().getId())
	  								{
	  									
	  									prixMoyenStockPF.setPrixMoyenService(listEtatPrixMoyenserviceParSousFamille.get(d).getPrixMoyen());
	  									trouver=true;
	  									
	  								}
	  								
	  								
	  								
	  								
	  							}
	  							
	  							
	  							if(trouver==false)
	  							{
	  							
	  								prixMoyenStockPF.setPrixMoyenService(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
	  							}
								
							
								
								
								
								prixMoyenStockPF.setMontantHTService(prixMoyenStockPF.getPrixMoyenService().multiply(prixMoyenStockPF.getQuantiteService()));
								
								
								prixMoyenStockPF.setMp(matierePremier);
								prixMoyenStockPF.setQuantiteMP(BigDecimal.ZERO);
								prixMoyenStockPF.setPrixMoyenMP(BigDecimal.ZERO);
								prixMoyenStockPF.setMontantHTMP(BigDecimal.ZERO);
								
								prixMoyenStockPF.setQuantiteFinale(prixMoyenStockPF.getQuantiteService());
								prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenService());
								prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getMontantHTService());
								listEtatprixMoyenService.add(prixMoyenStockPF);
								
									
								}
								
								
								
				 ////////////////////////////////////////////////////////////////////////// MP /////////////////////////////////////////////////////////////////////////////////
				  				
								
								requeteMP=" d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_INITIAL+"' ";
				    			
								requeteMP=requeteMP+"and  d.magasinDestination.id = '"+magasinMP.getId()+"' ";
								
								requeteMP=requeteMP+"and  d.transferStockMP.dateTransfer between '"+dateDu +"' and '"+dateAu+"' ";

								
						///////////////////////////////////////////////////////////////////////// MP ///////////////////////////////////////////////////////////////		
								
					  	       	listDetailTransfertStockMP=detailTransferStockMPDAO.listeDetailTransfertMP(requeteMP);
					  			 for(int i=0;i<listDetailTransfertStockMP.size();i++)
					  			 {
					  				 
					  				DetailTransferStockMP detailTransferStockMP= listDetailTransfertStockMP.get(i);
					  				
					  			PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
					  			prixMoyenStockMP.setMp(detailTransferStockMP.getMatierePremier());
					  			prixMoyenStockMP.setPrixInitial(detailTransferStockMP.getPrixUnitaire());
					  			prixMoyenStockMP.setQuantiteInitial(detailTransferStockMP.getQuantite());
					  			prixMoyenStockMP.setMontantInitial(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
					  			prixMoyenStockMP.setQuantiteAchat(BigDecimal.ZERO);	
					  			prixMoyenStockMP.setPrixAchat(BigDecimal.ZERO);
					  			prixMoyenStockMP.setMontantAchat(BigDecimal.ZERO);
					  			prixMoyenStockMP.setQuantiteFinale(detailTransferStockMP.getQuantite());
					  			prixMoyenStockMP.setPrixMoyen(detailTransferStockMP.getPrixUnitaire());
					  			prixMoyenStockMP.setMontantHTFinale(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
					  			listPrixMoyenStockMP.add(prixMoyenStockMP);
					  				 
					  				 
					  			 }
					  				  
					  				
					  				listeObject=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dateChooserdebut.getDate(), dateChooserfin.getDate(),magasinMP,souscategorieMP,mp, TousSubCategorie,TousArticle, categorieMP , Touscategorie);
					  				
					  				
					  				
					  				for(int j=0;j<listeObject.size();j++)
					  				{
					  					existe=false;
					  					 Object[] object=listeObject.get(j);
					  					 
					  					 for(int k=0;k<listPrixMoyenStockMP.size();k++)
					  					 {
					  						 
					  						PrixMoyenStockMP prixMoyenStockMP=listPrixMoyenStockMP.get(k); 
					  						 
					  						 
					  						 
					  						if(object[0].toString().equals(prixMoyenStockMP.getMp().getCode()))
					  						{
					  							
					  							existe=true;
					  							
					  							prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
									  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
									  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
									  			prixMoyenStockMP.setQuantiteFinale(prixMoyenStockMP.getQuantiteAchat().add(prixMoyenStockMP.getQuantiteInitial()));
									  			prixMoyenStockMP.setPrixMoyen((prixMoyenStockMP.getMontantInitial().add(prixMoyenStockMP.getMontantAchat())).divide(prixMoyenStockMP.getQuantiteAchat().add(prixMoyenStockMP.getQuantiteInitial()), 6, RoundingMode.HALF_UP));
									  			prixMoyenStockMP.setMontantHTFinale(prixMoyenStockMP.getQuantiteFinale().multiply(prixMoyenStockMP.getPrixMoyen()));
									  			listPrixMoyenStockMP.set(k, prixMoyenStockMP);
					  							
					  						}
					  						
					  						 
					  					 }
					  					
					  					 if(existe==false)
					  					 {
					  						
					  						MatierePremier matierePremier=MatierPremierDAO.findByCode(object[0].toString()) ;
					  						 
					  						PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
								  			prixMoyenStockMP.setMp(matierePremier);
								  			prixMoyenStockMP.setPrixInitial(BigDecimal.ZERO);
								  			prixMoyenStockMP.setQuantiteInitial(BigDecimal.ZERO);
								  			prixMoyenStockMP.setMontantInitial(BigDecimal.ZERO);
								  			prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
								  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
								  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
								  			prixMoyenStockMP.setQuantiteFinale(new BigDecimal(object[4].toString()));
								  			prixMoyenStockMP.setPrixMoyen(new BigDecimal(object[5].toString()));
								  			prixMoyenStockMP.setMontantHTFinale(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
								  			listPrixMoyenStockMP.add(prixMoyenStockMP); 
					  						 
					  						 
					  						 
					  						 
					  						 
					  						 
					  					 }
					  					 
					  					 
					  					 
					  					 
					  					 
					  					
					  				}	
								
								
						
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								
					  				boolean trouvesousfamille=false;
					  			boolean trouvemp=false;
					  				
					  			for(int i=0;i<listPrixMoyenStockMP.size();i++)	
					  			{
					  			trouvesousfamille=false;
					  		trouvemp=false;
					  			PrixMoyenStockMP prixMoyenStockMP=listPrixMoyenStockMP.get(i);	
					  				
					  			SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(prixMoyenStockMP.getMp());
					  			
					  			for(int j=0;j<listEtatprixMoyenService.size();j++)	
					  			{
					  				
					  			PrixMoyenStockPF prixMoyenStockPF=listEtatprixMoyenService.get(j);
					  				if(sousFamilleEnVrac!=null)
					  				{
					  					if(prixMoyenStockPF.getSousFamille()!=null)
					  					{
					  						
					  						
					  						if(sousFamilleEnVrac.getSousfamile().getId()==prixMoyenStockPF.getSousFamille().getId())
						  					{
						  						
						  						
						  					if(prixMoyenStockPF.getMp()!=null)
						  					{
						  						
						  						if(prixMoyenStockPF.getMp().getId()==sousFamilleEnVrac.getMatierePremier().getId())
						  						{
						  							
						  							
						  						prixMoyenStockPF.setMp(prixMoyenStockMP.getMp());
		  		  					  			prixMoyenStockPF.setQuantiteMP(prixMoyenStockMP.getQuantiteFinale());
		  		  					  		prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
		  		  					  	prixMoyenStockPF.setMontantHTMP(prixMoyenStockMP.getMontantHTFinale());	
		  		  					  prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenFinale().add(prixMoyenStockPF.getPrixMoyenMP()));
		  		  					prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getQuantiteFinale().multiply(prixMoyenStockPF.getPrixMoyenFinale()));
		  		  					listEtatprixMoyenService.set(j, prixMoyenStockPF);
						  							
		  		  				trouvesousfamille=true;
			  						
			  						
				  					trouvemp=true;	
						  							
						  							
						  							
						  							
						  						}
						  						
						  						
						  						
						  						
						  					}else
						  					{
						  						
						  					
						  						
						  					prixMoyenStockPF.setMp(prixMoyenStockMP.getMp());
			  					  			prixMoyenStockPF.setQuantiteMP(prixMoyenStockMP.getQuantiteFinale());
			  					  		prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
			  					  	prixMoyenStockPF.setMontantHTMP(prixMoyenStockMP.getMontantHTFinale());	
			  					  prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenFinale().add(prixMoyenStockPF.getPrixMoyenMP()));
			  					prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getQuantiteFinale().multiply(prixMoyenStockPF.getPrixMoyenFinale()));
			  					listEtatprixMoyenService.set(j, prixMoyenStockPF);
						  						
			  				trouvesousfamille=true;
		  						
		  						
			  					trouvemp=true;
						  					}
						  					
			  					  			
						  							
						  						
						  						
						  						
						  					
				  							
						  						
						  			
						  		
						  					}
					  						
					  						
					  						
					  						
					  						
					  							
					  						
					  			
					  					
					  					
					  					
					  					
					  					}
					  			
					  					
					  					
					  				}
					  				
					  				
					  				
					  				
					  				
					  				
					  				
					  				
					  			}
					  			
					  			if(trouvesousfamille==false && trouvemp==false)
					  			{
					  				
					  				
					  			PrixMoyenStockPF prixMoyenStockPF=new PrixMoyenStockPF();
								
					  		SousFamilleEnVrac sousFamilleEnVracTmp=sousFamilleEnvracDAo.findByMP(prixMoyenStockMP.getMp());
					  			if(sousFamilleEnVracTmp!=null)
					  			{
					  			prixMoyenStockPF.setSousFamille(sousFamilleEnVracTmp.getSousfamile());	
					  			}else
					  			{
					  			prixMoyenStockPF.setSousFamille(null);	
					  			}
									
								
									  							
								prixMoyenStockPF.setQuantiteService(BigDecimal.ZERO);
								prixMoyenStockPF.setPrixMoyenService(BigDecimal.ZERO);
								prixMoyenStockPF.setMontantHTService(BigDecimal.ZERO);
										  							
								prixMoyenStockPF.setMp(prixMoyenStockMP.getMp());
								prixMoyenStockPF.setQuantiteMP(prixMoyenStockMP.getQuantiteFinale());
								prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
								prixMoyenStockPF.setMontantHTMP(prixMoyenStockMP.getMontantHTFinale());
								
								prixMoyenStockPF.setQuantiteFinale(prixMoyenStockPF.getQuantiteMP());
								prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenMP());
								prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getMontantHTMP());
								listEtatprixMoyenService.add(prixMoyenStockPF);
					  				
					  			
					  			}
					  			
					  			}
					  			
					  				
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
								
							}else
							{
								JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
								listEtatprixMoyenService.clear();
							//	afficher_tableEtatVenteArticle(listEtatprixMoyenService);
								return;
							}
							
						}
						
						
									  
			  			  
			  			  
			  		 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			  			  
			  		
			  		  
		    			
		    		}else
		    		{
		    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		    			return;
		    		}
		    		
		    		}
	  			
	  			listEtatprixMoyenServiceAfficher.clear();
	  			
	  			for(int t=0;t<listEtatprixMoyenService.size();t++)
	  			{
	  				
	  				PrixMoyenStockPF prixMoyenStockPF=listEtatprixMoyenService.get(t);
	  				if(famille!=null)
	  				{
	  					
	  				if(sousFamille!=null)	
	  				{
	  				
	  					if(prixMoyenStockPF.getSousFamille()!=null)
	  					{
	  						
	  						if(prixMoyenStockPF.getSousFamille().getId()==sousFamille.getId())
	  						{
	  							listEtatprixMoyenServiceAfficher.add(prixMoyenStockPF);
	  							
	  							
	  						}
	  						
	  						
	  					}
	  					
	  					
	  					
	  				}else
	  				{
	  					
	  					if(prixMoyenStockPF.getSousFamille()!=null)
	  					{
	  						
	  						if(prixMoyenStockPF.getSousFamille().getFamileArticlePF().getId()==famille.getId())
	  						{
	  							listEtatprixMoyenServiceAfficher.add(prixMoyenStockPF);
	  							
	  							
	  						}
	  						
	  						
	  					}
	  					
	  					
	  					
	  				}
	  					
	  					
	  					
	  				}else
	  				{
	  					
	  					listEtatprixMoyenServiceAfficher.add(prixMoyenStockPF);
	  					
	  				}
	  				
	  				
	  				
	  			}
	  			
	  			
	  			
	  			
	  			CalculerPrixMoyenMP();
	  			
	  			for(int t=0;t<listPrixMoyenStockMP.size();t++)
	  			{
	  				
	  				
	  				PrixMoyenStockMP prixMoyenStockMP=listPrixMoyenStockMP.get(t);
	  				
	  				
	  				for(int j=0;j<listEtatprixMoyenServiceAfficher.size();j++)
	  				{
	  					
	  					PrixMoyenStockPF prixMoyenStockPF=listEtatprixMoyenServiceAfficher.get(j);
	  					
	  					
	   					if(prixMoyenStockMP.getSousfamille()!=null)
	  					{
	  						//System.out.println(prixMoyenStockMP.getMp().getCode());	
	            		    
	  						
	  						
	  						if(prixMoyenStockPF.getSousFamille()!=null)
	  						{
	  							
	  							if(prixMoyenStockPF.getSousFamille().getLiblle().equals(prixMoyenStockMP.getSousfamille()))
	  							{
	  								
	  								prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
	  								prixMoyenStockPF.setMontantHTMP(prixMoyenStockPF.getQuantiteMP().multiply(prixMoyenStockMP.getPrixMoyen()));
	  								prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenMP().add(prixMoyenStockPF.getPrixMoyenService()));
	  								prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getPrixMoyenFinale().multiply(prixMoyenStockPF.getQuantiteFinale()));
	  								listEtatprixMoyenServiceAfficher.set(j, prixMoyenStockPF);
	  							}
	  							
	  							
	  							
	  						}else
	  						{
	  							
	  							if(prixMoyenStockPF.getMp().getId()==prixMoyenStockMP.getMp().getId())
	  							{
	  								
	  								prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
	  								prixMoyenStockPF.setMontantHTMP(prixMoyenStockPF.getQuantiteMP().multiply(prixMoyenStockMP.getPrixMoyen()));
	  								prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenMP().add(prixMoyenStockPF.getPrixMoyenService()));
	  								prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getPrixMoyenFinale().multiply(prixMoyenStockPF.getQuantiteFinale()));
	  								listEtatprixMoyenServiceAfficher.set(j, prixMoyenStockPF);
	  								
	  								
	  							}
	  							
	  							
	  							
	  							
	  							
	  							
	  						}
	  						
	  						
	  						
	  						
	  						
	  					}else
	  					{
	  						
	  						if(prixMoyenStockPF.getSousFamille()==null)
	  						{
	  						
	  							if(prixMoyenStockPF.getMp().getId()==prixMoyenStockMP.getMp().getId())
	  							{
	  								
	  								
	  								prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
	  								prixMoyenStockPF.setMontantHTMP(prixMoyenStockPF.getQuantiteMP().multiply(prixMoyenStockMP.getPrixMoyen()));
	  								prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenMP().add(prixMoyenStockPF.getPrixMoyenService()));
	  								prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getPrixMoyenFinale().multiply(prixMoyenStockPF.getQuantiteFinale()));
	  								listEtatprixMoyenServiceAfficher.set(j, prixMoyenStockPF);
	  								
	  								
	  							}
	  							
	  							
	  							
	  						}
	  						
	  						
	  						
	  						
	  					}
	  					
	  					
	  					
	  					
	  					
	  				}
	  				
	  				
	  				
	  				
	  				
	  				
	  			}
	  			
	  			
	  			
	  			
	  			
	  			
	  			
	  			
	  		}else
	  		{
	  			JOptionPane.showMessageDialog(null, "Veuillez selectionner les magasin PF et MP SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	  			return;
	  		}
	  
	  		
		

			
			
			
			
		
			
			
			
			
		
			
			
			
		}
		
		
		
		public void CalculerPrixMoyenVenteParArticle()
		{
			
			

    		
    		
    	  	
    		Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
    		String TousFamille="";
    		String TousSousFamille="";
    		String TousArticle="";
    		if(magasin!=null)
    		{
    			if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
	    		{
    				
	    		if(	DateUtils.nbJoursEntre(dateChooserdebut.getDate(), dateChooserfin.getDate())>=0)
	    		{
	    			ClientPF clientpf=	null;
	    			FamilleArticlePF famillearticle=null;
	    			SousFamilleArticlePF sousfamillearticle=null;
	    			
	    		
	    			
	    			
	    		
	    			
	    		
	    			
	    			Articles article=null;
	    			
	    			
		  	
		  			 
		  				  
		  				String type="";
		  				listDetailFactureVentePFTmp=detailFacturePfdao.listeEtatPrixMoyen(dateChooserdebut.getDate(), dateChooserfin.getDate(),magasin,famillearticle,article, TousFamille,TousArticle,type, sousfamillearticle , TousSousFamille);
		  				
		  				int i=0;
						boolean trouve=false;
						BigDecimal quantite=BigDecimal.ZERO;
		 					BigDecimal quantitefoiprix=BigDecimal.ZERO;
		 					
		 				BigDecimal quantiteOffre=BigDecimal.ZERO;
							BigDecimal quantiteOffrefoiprix=BigDecimal.ZERO;
						
						 for(int k=0;k<listDetailFactureVentePFTmp.size();k++)
							 {
								 DetailFacturePF detailfacture=listDetailFactureVentePFTmp.get(k);
								 SousFamilleArticlePF sousfamilleArticle =listDetailFactureVentePFTmp.get(k).getSousFamille();
								 
								 trouve=false;
								
								 if(listeDetailFacturePF.size()!=0)
								 {
									
									 for(int j=0;j<listeDetailFacturePF.size();j++)
									 {
										 if(listeDetailFacturePF.get(j).getArticle().getId()== detailfacture.getArticle().getId())
										 {
											 if(listeDetailFacturePF.get(j).getSousFamille().getId()==sousfamilleArticle.getId())
											 {
												 trouve=true; 
											 }
												 
										 }
										 
									 }
									 
								 }else
								 {
									 listeDetailFacturePF.add(detailfacture);
									trouve=true;
								 }
								 
								 if(trouve==false)
								 {
									 listeDetailFacturePF.add(detailfacture);
								 }
							 }
						
						
						 listEtatPrixMoyenAfficher.clear();
							boolean existe=false;
							boolean exist=false;
								
								if(listeDetailFacturePF.size()!=0)
								{
									for(int t=0;t<listeDetailFacturePF.size();t++)
									{
										Articles articleTmp =listeDetailFacturePF.get(t).getArticle();
										SousFamilleArticlePF sousfamilleArticle =listeDetailFacturePF.get(t).getSousFamille();
										quantite=BigDecimal.ZERO;
										quantitefoiprix=BigDecimal.ZERO;
										
										quantiteOffre=BigDecimal.ZERO;
										quantiteOffrefoiprix=BigDecimal.ZERO;
										
										existe=false;
										exist=false;
									for(int j=0;j<listDetailFactureVentePFTmp.size();j++)	
									{
										
										
										
										if(listDetailFactureVentePFTmp.get(j).getArticle().getId()==articleTmp.getId())
										{
											if(listDetailFactureVentePFTmp.get(j).getSousFamille().getId()==sousfamilleArticle.getId())
											{
											   	if(listDetailFactureVentePFTmp.get(j).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
											   	{
											   	quantiteOffrefoiprix=quantiteOffrefoiprix.add(listDetailFactureVentePFTmp.get(j).getQuantite().multiply(listDetailFactureVentePFTmp.get(j).getPrixUnitaire()));
											  quantiteOffre=quantiteOffre.add(listDetailFactureVentePFTmp.get(j).getQuantite());
											   		
											existe=true;
											   	}else
											   	{
											   		
											  	quantitefoiprix=quantitefoiprix.add(listDetailFactureVentePFTmp.get(j).getQuantite().multiply(listDetailFactureVentePFTmp.get(j).getPrixUnitaire()));
												quantite=quantite.add(listDetailFactureVentePFTmp.get(j).getQuantite());
												
												
												exist=true;
											   	
											   	}
												
											}
											
									
											
											
											
										}
										
										
									}
									
									if(existe==true)
									{

			  							
			  		////////////////////////////////////////////////////////////// Offre ///////////////////////////////////////////////////////////////////////////////////////					
			  							
			  							
										EtatPrixMoyen etatPrixMoyen = new EtatPrixMoyen();
										etatPrixMoyen.setArticle(articleTmp.getLiblle());
										etatPrixMoyen.setFamille(FamilleOffre.getLiblle());
										etatPrixMoyen.setSousfamille(sousfamilleArticle.getLiblle());
										
										etatPrixMoyen.setQuantite(quantiteOffre);
										etatPrixMoyen.setPrix(quantiteOffrefoiprix.divide(quantiteOffre, 6, RoundingMode.HALF_UP));
										etatPrixMoyen.setMontantHT(BigDecimal.ZERO);
										etatPrixMoyen.setMontantTVA(BigDecimal.ZERO);
										etatPrixMoyen.setMontantTTC(BigDecimal.ZERO);
										listEtatPrixMoyenAfficher.add(etatPrixMoyen);
			  							
										
										
									}
									if(exist==true)
									{
										
									
										EtatPrixMoyen etatPrixMoyen = new EtatPrixMoyen();
										etatPrixMoyen.setArticle(articleTmp.getLiblle());
										etatPrixMoyen.setFamille(sousfamilleArticle.getFamileArticlePF().getLiblle());
										etatPrixMoyen.setSousfamille(sousfamilleArticle.getLiblle());
										
										etatPrixMoyen.setQuantite(quantite);
										etatPrixMoyen.setPrix(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
										etatPrixMoyen.setMontantHT(etatPrixMoyen.getPrix().multiply(etatPrixMoyen.getQuantite()).setScale(6, RoundingMode.FLOOR));
										etatPrixMoyen.setMontantTVA((etatPrixMoyen.getPrix().multiply(etatPrixMoyen.getQuantite()).setScale(6, RoundingMode.FLOOR).multiply(new BigDecimal(0.2))).setScale(6, RoundingMode.FLOOR));
										etatPrixMoyen.setMontantTTC((etatPrixMoyen.getMontantHT().add(etatPrixMoyen.getMontantTVA())).setScale(6, RoundingMode.FLOOR));
										listEtatPrixMoyenAfficher.add(etatPrixMoyen);
			  							
									}
									
									
								
									
										
									}
									
									
									
									
								}else
								{
									JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
									
									return;
								}
						 
		  			  
		  			  
		  		 
		  			  
		  		
		  		  
	    			
	    		}else
	    		{
	    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		
	    		}
    		}else
    		{
    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    
    		
	

		
		
		
		
	
			
			
		}
		
		
		public void CalculerPrixMoyenAvoirClientPFParArticle()
		{
			
			

    		
    		
    	  	
    		Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());
    		String TousFamille="";
    		String TousSousFamille="";
    		String TousArticle="";
    		if(magasin!=null)
    		{
    			if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
	    		{
    				
	    		if(	DateUtils.nbJoursEntre(dateChooserdebut.getDate(), dateChooserfin.getDate())>=0)
	    		{
	    			ClientPF clientpf=	null;
	    			FamilleArticlePF famillearticle=null;
	    			SousFamilleArticlePF sousfamillearticle=null;
	    			
	    		
	    			
	    			
	    		
	    			
	    		
	    			
	    			Articles article=null;
	    			
	    			
		  	
		  			 
		  				  
		  				String type="";
		  				listDetailFactureAvoirClientPFTmp=detailfactureAvoirClientPFdao.listeEtatPrixMoyenAvoirClientPF(dateChooserdebut.getDate(), dateChooserfin.getDate(),magasin,famillearticle,article, TousFamille,TousArticle,type, sousfamillearticle , TousSousFamille);
		  				
		  				int i=0;
						boolean trouve=false;
						BigDecimal quantite=BigDecimal.ZERO;
		 					BigDecimal quantitefoiprix=BigDecimal.ZERO;
		 					
		 				BigDecimal quantiteOffre=BigDecimal.ZERO;
							BigDecimal quantiteOffrefoiprix=BigDecimal.ZERO;
						
						 for(int k=0;k<listDetailFactureAvoirClientPFTmp.size();k++)
							 {
								 DetailFactureAvoirClientPF detailfacture=listDetailFactureAvoirClientPFTmp.get(k);
								 SousFamilleArticlePF sousfamilleArticle =listDetailFactureAvoirClientPFTmp.get(k).getSousFamille();
								 
								 trouve=false;
								
								 if(listeDetailFactureAvoirClientPF.size()!=0)
								 {
									
									 for(int j=0;j<listeDetailFactureAvoirClientPF.size();j++)
									 {
										 if(listeDetailFactureAvoirClientPF.get(j).getArticle().getId()== detailfacture.getArticle().getId())
										 {
											 if(listeDetailFactureAvoirClientPF.get(j).getSousFamille().getId()==sousfamilleArticle.getId())
											 {
												 trouve=true; 
											 }
												 
										 }
										 
									 }
									 
								 }else
								 {
									 listeDetailFactureAvoirClientPF.add(detailfacture);
									trouve=true;
								 }
								 
								 if(trouve==false)
								 {
									 listeDetailFactureAvoirClientPF.add(detailfacture);
								 }
							 }
						
						
						 listEtatPrixMoyenAfficher.clear();
							boolean existe=false;
							boolean exist=false;
								
								if(listeDetailFactureAvoirClientPF.size()!=0)
								{
									for(int t=0;t<listeDetailFactureAvoirClientPF.size();t++)
									{
										Articles articleTmp =listeDetailFactureAvoirClientPF.get(t).getArticle();
										SousFamilleArticlePF sousfamilleArticle =listeDetailFactureAvoirClientPF.get(t).getSousFamille();
										quantite=BigDecimal.ZERO;
										quantitefoiprix=BigDecimal.ZERO;
										
										quantiteOffre=BigDecimal.ZERO;
										quantiteOffrefoiprix=BigDecimal.ZERO;
										
										existe=false;
										exist=false;
									for(int j=0;j<listDetailFactureAvoirClientPFTmp.size();j++)	
									{
										
										
										
										if(listDetailFactureAvoirClientPFTmp.get(j).getArticle().getId()==articleTmp.getId())
										{
											if(listDetailFactureAvoirClientPFTmp.get(j).getSousFamille().getId()==sousfamilleArticle.getId())
											{
											   	if(listDetailFactureAvoirClientPFTmp.get(j).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
											   	{
											   	quantiteOffrefoiprix=quantiteOffrefoiprix.add(listDetailFactureAvoirClientPFTmp.get(j).getQuantite().multiply(listDetailFactureAvoirClientPFTmp.get(j).getPrixUnitaire()));
											  quantiteOffre=quantiteOffre.add(listDetailFactureAvoirClientPFTmp.get(j).getQuantite());
											   		
											existe=true;
											   	}else
											   	{
											   		
											  	quantitefoiprix=quantitefoiprix.add(listDetailFactureAvoirClientPFTmp.get(j).getQuantite().multiply(listDetailFactureAvoirClientPFTmp.get(j).getPrixUnitaire()));
												quantite=quantite.add(listDetailFactureVentePFTmp.get(j).getQuantite());
												
												
												exist=true;
											   	
											   	}
												
											}
											
									
											
											
											
										}
										
										
									}
									
									if(existe==true)
									{

			  							
			  		////////////////////////////////////////////////////////////// Offre ///////////////////////////////////////////////////////////////////////////////////////					
			  							
			  							
										EtatPrixMoyen etatPrixMoyen = new EtatPrixMoyen();
										etatPrixMoyen.setArticle(articleTmp.getLiblle());
										etatPrixMoyen.setFamille(FamilleOffre.getLiblle());
										etatPrixMoyen.setSousfamille(sousfamilleArticle.getLiblle());
										
										etatPrixMoyen.setQuantite(quantiteOffre);
										etatPrixMoyen.setPrix(quantiteOffrefoiprix.divide(quantiteOffre, 6, RoundingMode.HALF_UP));
										etatPrixMoyen.setMontantHT(BigDecimal.ZERO);
										etatPrixMoyen.setMontantTVA(BigDecimal.ZERO);
										etatPrixMoyen.setMontantTTC(BigDecimal.ZERO);
										listEtatPrixMoyenAfficher.add(etatPrixMoyen);
			  							
										
										
									}
									if(exist==true)
									{
										
									
										EtatPrixMoyen etatPrixMoyen = new EtatPrixMoyen();
										etatPrixMoyen.setArticle(articleTmp.getLiblle());
										etatPrixMoyen.setFamille(sousfamilleArticle.getFamileArticlePF().getLiblle());
										etatPrixMoyen.setSousfamille(sousfamilleArticle.getLiblle());
										
										etatPrixMoyen.setQuantite(quantite);
										etatPrixMoyen.setPrix(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
										etatPrixMoyen.setMontantHT(etatPrixMoyen.getPrix().multiply(etatPrixMoyen.getQuantite()).setScale(6, RoundingMode.FLOOR));
										etatPrixMoyen.setMontantTVA((etatPrixMoyen.getPrix().multiply(etatPrixMoyen.getQuantite()).setScale(6, RoundingMode.FLOOR).multiply(new BigDecimal(0.2))).setScale(6, RoundingMode.FLOOR));
										etatPrixMoyen.setMontantTTC((etatPrixMoyen.getMontantHT().add(etatPrixMoyen.getMontantTVA())).setScale(6, RoundingMode.FLOOR));
										listEtatPrixMoyenAfficher.add(etatPrixMoyen);
			  							
									}
									
									
								
									
										
									}
									
									
									
									
								}else
								{
									JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
									
									return;
								}
						 
		  			  
		  			  
		  		 
		  			  
		  		
		  		  
	    			
	    		}else
	    		{
	    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		
	    		}
    		}else
    		{
    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    
    		
	

		
		
		
		
	
			
			
		}	

	
	

	}


