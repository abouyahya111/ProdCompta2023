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


public class ConsulterMargeDeStockSousFamille extends JLayeredPane implements Constantes{
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
	private DetailTransferProduitFiniDAO detailTransferStockPFdao;
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
			private List<EtatMargeDeStockArticlePF> listEtatMargeDeStockArticlePF =new ArrayList<EtatMargeDeStockArticlePF>();
			private List<DetailFacturePF> listDetailFactureVentePF =new ArrayList<DetailFacturePF>();
			private List<DetailFacturePF> listDetailFactureVentePFTmp =new ArrayList<DetailFacturePF>();
			private  DetailFacturePFDAO detailfacturedao;
			private List<DetailFactureAvoirPF> listDetailFactureAvoirPF =new ArrayList<DetailFactureAvoirPF>();
			private List<DetailFactureAvoirPF> listDetailFactureAvoirPFTmp =new ArrayList<DetailFactureAvoirPF>();
			private List<EtatVenteParFamilleArticle> listEtatAvoir =new ArrayList<EtatVenteParFamilleArticle>();
			private  DetailFactureAvoirPFDAO detailfactureAvoirdao;
			private List<DetailFactureAvoirClientPF> listDetailFactureAvoirClientPF =new ArrayList<DetailFactureAvoirClientPF>();
			private List<DetailFactureAvoirClientPF> listDetailFactureAvoirClientPFTmp =new ArrayList<DetailFactureAvoirClientPF>();
			private List<EtatVenteParFamilleArticle> listEtatAvoirClientPF =new ArrayList<EtatVenteParFamilleArticle>();
			private  DetailFactureAvoirClientPFDAO detailfactureAvoirClientPFdao;
			private SubCategorieMPDAO subCategorieMPDAO;


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
		
JLabel labelmarge = new JLabel((String) null);
JLabel labelTotalInitial = new JLabel("");
JLabel labelTotalService = new JLabel("");
JLabel labelTotalGratuite = new JLabel("");
JLabel labelTotalAchat = new JLabel("");
JLabel labelTotalCA = new JLabel("");
JLabel labelTotalAvoir = new JLabel("");
 JLabel labelTotalTransfert = new JLabel("");
JLabel labelTotalFinale = new JLabel("");
JLabel labelTotalAchatRevendu = new JLabel("");
 JLabel labelpercentage = new JLabel("");
 JLabel labelTotalAvoirClientPF = new JLabel("");
 
 
/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterMargeDeStockSousFamille() {
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
				  		     	titledSeparator.setTitle("Valeurisation Etat Stock par Sous Famille");
				  		     	titledSeparator.setBounds(10, 154, 1465, 30);
				  		     	add(titledSeparator);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Etat Marge De Stock Par Sous Famille");
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
	    		
	    		listPrixMoyenStockMP.clear();
	    		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    		
	    	  	boolean existe=false;
		    		Magasin magasinMP=mapMagasin.get(comboMagasinMP.getSelectedItem());
		    	
		    		String requete="";
		    		if(magasinMP!=null)
		    		{
		    			
		    			requete=" d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_INITIAL+"' ";
		    			
		    			requete=requete+"and  d.magasinDestination.id = '"+magasinMP.getId()+"' ";
		    			
		    			if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
			    		{
		    				
			    		if(	DateUtils.nbJoursEntre(dateChooserdebut.getDate(), dateChooserfin.getDate())>=0)
			    		{
			    			
			    			String dateDu=formatter.format(dateChooserdebut.getDate());
			    			String dateAu=formatter.format(dateChooserfin.getDate());
			    			
			    			
			    			requete=requete+"and  d.transferStockMP.dateTransfer between '"+dateDu +"' and '"+dateAu+"' ";
			    			

			    			
			    			
			    			
			    			
				  	       	listDetailTransfertStockMP=detailTransferStockMPDAO.listeDetailTransfertMP(requete);
				  	       	
				  	       	boolean exist=false;
				  	       	
				  			 for(int i=0;i<listDetailTransfertStockMP.size();i++)
				  			 {
				  				exist=false;
				  				 
				  				DetailTransferStockMP detailTransferStockMP= listDetailTransfertStockMP.get(i);
				  				
				  				SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(detailTransferStockMP.getMatierePremier());
				  				for(int t=0;t<listPrixMoyenStockMP.size();t++)
				  				{
				  					
				  					PrixMoyenStockMP prixMoyenStockMPTmp=listPrixMoyenStockMP.get(t);
				  					
				  					if(sousFamilleEnVrac!=null)
				  					{
				  						
				  						if(prixMoyenStockMPTmp.getSousfamille().equals(sousFamilleEnVrac.getSousfamile().getLiblle()))
					  					{
					  						
					  						exist=true;
					  						
					  						prixMoyenStockMPTmp.setPrixInitial(detailTransferStockMP.getPrixUnitaire());
					  						prixMoyenStockMPTmp.setQuantiteInitial(prixMoyenStockMPTmp.getQuantiteInitial().add(detailTransferStockMP.getQuantite()) );
					  						prixMoyenStockMPTmp.setMontantInitial(prixMoyenStockMPTmp.getPrixInitial().multiply(prixMoyenStockMPTmp.getQuantiteInitial()));
					  						prixMoyenStockMPTmp.setQuantiteAchat(BigDecimal.ZERO);	
					  						prixMoyenStockMPTmp.setPrixAchat(BigDecimal.ZERO);
					  						prixMoyenStockMPTmp.setMontantAchat(BigDecimal.ZERO);
					  						prixMoyenStockMPTmp.setQuantiteFinale(prixMoyenStockMPTmp.getQuantiteInitial());
					  						prixMoyenStockMPTmp.setPrixMoyen(prixMoyenStockMPTmp.getPrixInitial());
					  						prixMoyenStockMPTmp.setMontantHTFinale(prixMoyenStockMPTmp.getPrixInitial().multiply(prixMoyenStockMPTmp.getQuantiteInitial()));
								  		
					  						listPrixMoyenStockMP.set(t, prixMoyenStockMPTmp);
					  						
					  					}
				  						
				  					}else
				  					{
				  						
				  						if(prixMoyenStockMPTmp.getSousfamille().equals(detailTransferStockMP.getMatierePremier().getCategorieMp().getNom()))
					  					{
					  						
					  						exist=true;
					  						
					  						prixMoyenStockMPTmp.setPrixInitial(detailTransferStockMP.getPrixUnitaire());
					  						prixMoyenStockMPTmp.setQuantiteInitial(prixMoyenStockMPTmp.getQuantiteInitial().add(detailTransferStockMP.getQuantite()) );
					  						prixMoyenStockMPTmp.setMontantInitial(prixMoyenStockMPTmp.getPrixInitial().multiply(prixMoyenStockMPTmp.getQuantiteInitial()));
					  						prixMoyenStockMPTmp.setQuantiteAchat(BigDecimal.ZERO);	
					  						prixMoyenStockMPTmp.setPrixAchat(BigDecimal.ZERO);
					  						prixMoyenStockMPTmp.setMontantAchat(BigDecimal.ZERO);
					  						prixMoyenStockMPTmp.setQuantiteFinale(prixMoyenStockMPTmp.getQuantiteInitial());
					  						prixMoyenStockMPTmp.setPrixMoyen(prixMoyenStockMPTmp.getPrixInitial());
					  						prixMoyenStockMPTmp.setMontantHTFinale(prixMoyenStockMPTmp.getPrixInitial().multiply(prixMoyenStockMPTmp.getQuantiteInitial()));
								  		
					  						listPrixMoyenStockMP.set(t, prixMoyenStockMPTmp);
					  						
					  					}
				  						
				  					}
				  					
				  					
				  					
				  				}
				  				
				  				if(exist==false)
				  				{
				  					
				  					
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
				  		
				  				 
				  				 
				  			 }
				  			 
				  			 
				  			 
				  			CalculerPrixMoyenInitialMP();	
			  			
			  				
			  				
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
				  			 
				  			 
				  			 
				  			 
				  				  
				  				
				  				listeObject=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dateChooserdebut.getDate(), dateChooserfin.getDate(),magasinMP,null,null, "","", null , "");
				  				
				  				
				  				
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
				  				
				  				
				  	for(int i=0;i<listPrixMoyenStockMP.size();i++)
				  	{
				  		
				  		PrixMoyenStockMP moyenStockMP=listPrixMoyenStockMP.get(i);
				  		
				  		
				  		EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=new EtatValeurisationStockParSousFamille();
				  		
				  		etatValeurisationStockParSousFamille.setMatierePremiere(moyenStockMP.getMp().getNom());
				  		etatValeurisationStockParSousFamille.setType(MAGASIN_CODE_TYPE_MP);
				  		etatValeurisationStockParSousFamille.setFamille(moyenStockMP.getFamille());
				  		etatValeurisationStockParSousFamille.setSousFamille(moyenStockMP.getSousfamille());
				  		etatValeurisationStockParSousFamille.setQuantiteInitial(moyenStockMP.getQuantiteInitial());
				  		etatValeurisationStockParSousFamille.setPrixInitial(moyenStockMP.getPrixInitial());
				  		etatValeurisationStockParSousFamille.setMontantInitial(etatValeurisationStockParSousFamille.getPrixInitial().multiply(etatValeurisationStockParSousFamille.getQuantiteInitial()));
				  		etatValeurisationStockParSousFamille.setQuantiteAchat(moyenStockMP.getQuantiteAchat());
				  		etatValeurisationStockParSousFamille.setPrixAchat(moyenStockMP.getPrixAchat());
				  		etatValeurisationStockParSousFamille.setMontantAchat(etatValeurisationStockParSousFamille.getPrixAchat().multiply(etatValeurisationStockParSousFamille.getQuantiteAchat()));
				  		etatValeurisationStockParSousFamille.setQuantiteSortie(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixSortie(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantSortie(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteDechet(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixDechet(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantDechet(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteOffreProduction(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixOffreProduction(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantOffreProduction(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteDechetService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixDechetService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantDechetService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteOffreService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixOffreService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantOffreService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantitevente(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixvente(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantvente(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteGratuit(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixGratuit(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantGratuit(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteAvoir(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixAvoir(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantAvoir(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteAvoirClientPF(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixAvoirClientPF(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantAvoirClientPF(BigDecimal.ZERO);
				  		
				  		etatValeurisationStockParSousFamille.setQuantiteTransfertMPPF(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixTransfertMPPF(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantTransfertMPPF(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteFinale(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()).subtract(etatValeurisationStockParSousFamille.getQuantiteSortie().add(etatValeurisationStockParSousFamille.getQuantiteDechet().add(etatValeurisationStockParSousFamille.getQuantiteOffreProduction().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatValeurisationStockParSousFamille.getQuantiteOffreService().add(etatValeurisationStockParSousFamille.getQuantiteGratuit().add(etatValeurisationStockParSousFamille.getQuantiteAvoir().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()))))))))));
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
				  				
				  				
				  ConsulterEtatQuantiteMPTransformeParsousfamille();	
				  
				  listEtatValeurisationStockParSousFamilleMPTransformerEnPF.clear();
				  
				  		boolean trouver=false;		
				  for(int d=0;d<listEtatStockMPParSousFamille.size();d++)	
				  {
					  trouver=false;	
					EtatStockMP etatStockMP=  listEtatStockMPParSousFamille.get(d);
					  
				for(int j=0;j<listEtatValeurisationStockParSousFamille.size();j++)
				{
					
					EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=listEtatValeurisationStockParSousFamille.get(j);
					if(etatStockMP.getMp().getNom().equals(etatValeurisationStockParSousFamille.getMatierePremiere()))
					{
						trouver=true;	
						etatValeurisationStockParSousFamille.setQuantiteSortie(etatValeurisationStockParSousFamille.getQuantiteSortie().add(etatStockMP.getQuantiteSortie()));
						etatValeurisationStockParSousFamille.setPrixSortie(etatValeurisationStockParSousFamille.getPrixFinale());
						etatValeurisationStockParSousFamille.setMontantSortie(etatValeurisationStockParSousFamille.getQuantiteSortie().multiply(etatValeurisationStockParSousFamille.getPrixSortie()));
						
						etatValeurisationStockParSousFamille.setQuantiteDechet(etatValeurisationStockParSousFamille.getQuantiteDechet().add(etatStockMP.getQuantiteDechet()));
						etatValeurisationStockParSousFamille.setPrixDechet(etatValeurisationStockParSousFamille.getPrixFinale());
						etatValeurisationStockParSousFamille.setMontantDechet(etatValeurisationStockParSousFamille.getQuantiteDechet().multiply(etatValeurisationStockParSousFamille.getPrixDechet()));
						
						etatValeurisationStockParSousFamille.setQuantiteOffreProduction (etatValeurisationStockParSousFamille.getQuantiteOffreProduction ().add(etatStockMP.getQuantiteOffreProduction()));
						etatValeurisationStockParSousFamille.setPrixOffreProduction (etatValeurisationStockParSousFamille.getPrixFinale());
						etatValeurisationStockParSousFamille.setMontantOffreProduction (etatValeurisationStockParSousFamille.getQuantiteOffreProduction ().multiply(etatValeurisationStockParSousFamille.getPrixOffreProduction ()));
						
						etatValeurisationStockParSousFamille.setQuantiteService(etatValeurisationStockParSousFamille.getQuantiteService().add(etatStockMP.getQuantiteService()));
						etatValeurisationStockParSousFamille.setPrixService(etatValeurisationStockParSousFamille.getPrixFinale());
						etatValeurisationStockParSousFamille.setMontantService(etatValeurisationStockParSousFamille.getQuantiteService().multiply(etatValeurisationStockParSousFamille.getPrixService()));
					
						etatValeurisationStockParSousFamille.setQuantiteDechetService(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatStockMP.getQuantiteDechetService()));
						etatValeurisationStockParSousFamille.setPrixDechetService(etatValeurisationStockParSousFamille.getPrixFinale());
						etatValeurisationStockParSousFamille.setMontantDechetService(etatValeurisationStockParSousFamille.getQuantiteDechetService().multiply(etatValeurisationStockParSousFamille.getPrixDechetService()));
					
						etatValeurisationStockParSousFamille.setQuantiteOffreService(etatValeurisationStockParSousFamille.getQuantiteOffreService().add(etatStockMP.getQuantiteOffreService()));
						etatValeurisationStockParSousFamille.setPrixOffreService(etatValeurisationStockParSousFamille.getPrixFinale());
						etatValeurisationStockParSousFamille.setMontantOffreService(etatValeurisationStockParSousFamille.getQuantiteOffreService().multiply(etatValeurisationStockParSousFamille.getPrixOffreService()));
					
						
						etatValeurisationStockParSousFamille.setQuantiteAvoir (etatValeurisationStockParSousFamille.getQuantiteAvoir().add(etatStockMP.getQuantiteAvoir()));
						etatValeurisationStockParSousFamille.setPrixAvoir(etatValeurisationStockParSousFamille.getPrixFinale());
						etatValeurisationStockParSousFamille.setMontantAvoir(etatValeurisationStockParSousFamille.getQuantiteAvoir().multiply(etatValeurisationStockParSousFamille.getPrixAvoir()));
						
						
						etatValeurisationStockParSousFamille.setQuantiteTransfertMPPF (etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF().add(etatStockMP.getQuantiteTransfertMPPF()));
						etatValeurisationStockParSousFamille.setPrixTransfertMPPF(etatValeurisationStockParSousFamille.getPrixFinale());
						etatValeurisationStockParSousFamille.setMontantTransfertMPPF(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF().multiply(etatValeurisationStockParSousFamille.getPrixTransfertMPPF()));
						
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
					  
					if(trouver==false)
					{
						
						
                        EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=new EtatValeurisationStockParSousFamille();
				  		
				  		etatValeurisationStockParSousFamille.setMatierePremiere(etatStockMP.getMp().getNom());
				  		etatValeurisationStockParSousFamille.setType(MAGASIN_CODE_TYPE_MP);
				  		etatValeurisationStockParSousFamille.setFamille(etatStockMP.getFamille());
				  		etatValeurisationStockParSousFamille.setSousFamille(etatStockMP.getSousFamille());
				  		etatValeurisationStockParSousFamille.setQuantiteInitial(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixInitial(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantInitial(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteAchat(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixAchat(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantAchat(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteSortie(etatStockMP.getQuantiteSortie());
				  		etatValeurisationStockParSousFamille.setPrixSortie(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantSortie(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteDechet(etatStockMP.getQuantiteDechet());
				  		etatValeurisationStockParSousFamille.setPrixDechet(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantDechet(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteOffreProduction(etatStockMP.getQuantiteOffreProduction());
				  		etatValeurisationStockParSousFamille.setPrixOffreProduction(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantOffreProduction(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteService(etatStockMP.getQuantiteService());
				  		etatValeurisationStockParSousFamille.setPrixService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteDechetService(etatStockMP.getQuantiteDechetService());
				  		etatValeurisationStockParSousFamille.setPrixDechetService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantDechetService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteOffreService(etatStockMP.getQuantiteOffreService());
				  		etatValeurisationStockParSousFamille.setPrixOffreService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantOffreService(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantitevente(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixvente(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantvente(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteGratuit(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixGratuit(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantGratuit(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteAvoir(etatStockMP.getQuantiteAvoir());
				  		etatValeurisationStockParSousFamille.setPrixAvoir(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantAvoir(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteAvoirClientPF(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixAvoirClientPF(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantAvoirClientPF(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteTransfertMPPF(etatStockMP.getQuantiteTransfertMPPF());
				  		etatValeurisationStockParSousFamille.setPrixTransfertMPPF(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantTransfertMPPF(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setQuantiteFinale(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()).subtract(etatValeurisationStockParSousFamille.getQuantiteSortie().add(etatValeurisationStockParSousFamille.getQuantiteDechet().add(etatValeurisationStockParSousFamille.getQuantiteOffreProduction().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatValeurisationStockParSousFamille.getQuantiteOffreService().add(etatValeurisationStockParSousFamille.getQuantiteGratuit().add(etatValeurisationStockParSousFamille.getQuantiteAvoir().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()))))))))));
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
				
				
				
				
					  
					  
					  
					  
				  }
				  				  
				  			  
				  	for(int p=0;p<listEtatValeurisationStockParSousFamille.size();p++)		
				  	{
				  		
				  		EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=listEtatValeurisationStockParSousFamille.get(p);
				  		
				  		etatValeurisationStockParSousFamille.setPrixSortie(etatValeurisationStockParSousFamille.getPrixFinale());
				  		etatValeurisationStockParSousFamille.setMontantSortie(etatValeurisationStockParSousFamille.getPrixSortie().multiply(etatValeurisationStockParSousFamille.getQuantiteSortie()));
				  		
				  		etatValeurisationStockParSousFamille.setPrixDechet(etatValeurisationStockParSousFamille.getPrixFinale());
				  		etatValeurisationStockParSousFamille.setMontantDechet(etatValeurisationStockParSousFamille.getPrixDechet().multiply(etatValeurisationStockParSousFamille.getQuantiteDechet()));
				  		
				  		etatValeurisationStockParSousFamille.setPrixOffreProduction(etatValeurisationStockParSousFamille.getPrixFinale());
				  		etatValeurisationStockParSousFamille.setMontantOffreProduction(etatValeurisationStockParSousFamille.getPrixOffreProduction().multiply(etatValeurisationStockParSousFamille.getQuantiteOffreProduction()));
				  		
				  		etatValeurisationStockParSousFamille.setPrixService (etatValeurisationStockParSousFamille.getPrixFinale());
				  		etatValeurisationStockParSousFamille.setMontantService(etatValeurisationStockParSousFamille.getPrixService().multiply(etatValeurisationStockParSousFamille.getQuantiteService()));
				  		
				  		
				  		etatValeurisationStockParSousFamille.setPrixDechetService (etatValeurisationStockParSousFamille.getPrixFinale());
				  		etatValeurisationStockParSousFamille.setMontantDechetService(etatValeurisationStockParSousFamille.getPrixDechetService().multiply(etatValeurisationStockParSousFamille.getQuantiteDechetService()));
				  		
				  		etatValeurisationStockParSousFamille.setPrixOffreService (etatValeurisationStockParSousFamille.getPrixFinale());
				  		etatValeurisationStockParSousFamille.setMontantOffreService(etatValeurisationStockParSousFamille.getPrixOffreService().multiply(etatValeurisationStockParSousFamille.getQuantiteOffreService()));
				  		
				  		etatValeurisationStockParSousFamille.setPrixOffreService (etatValeurisationStockParSousFamille.getPrixFinale());
				  		etatValeurisationStockParSousFamille.setMontantOffreService(etatValeurisationStockParSousFamille.getPrixOffreService().multiply(etatValeurisationStockParSousFamille.getQuantiteOffreService()));
				  		
				  		etatValeurisationStockParSousFamille.setPrixGratuit(etatValeurisationStockParSousFamille.getPrixFinale());
				  		etatValeurisationStockParSousFamille.setMontantGratuit(etatValeurisationStockParSousFamille.getPrixGratuit().multiply(etatValeurisationStockParSousFamille.getQuantiteGratuit()));
				  		
				  		etatValeurisationStockParSousFamille.setPrixAvoir (etatValeurisationStockParSousFamille.getPrixFinale());
				  		etatValeurisationStockParSousFamille.setMontantAvoir(etatValeurisationStockParSousFamille.getPrixAvoir().multiply(etatValeurisationStockParSousFamille.getQuantiteAvoir()));
				  		
				  		
				  		etatValeurisationStockParSousFamille.setPrixTransfertMPPF (etatValeurisationStockParSousFamille.getPrixFinale());
				  		etatValeurisationStockParSousFamille.setMontantTransfertMPPF(etatValeurisationStockParSousFamille.getPrixTransfertMPPF().multiply(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF()));
				  		
				  		
				  		
				  		if(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF().compareTo(BigDecimal.ZERO)!=0)
				  		{
				  			listEtatValeurisationStockParSousFamilleMPTransformerEnPF.add(etatValeurisationStockParSousFamille);
				  		}
				  		
				  		
				  		listEtatValeurisationStockParSousFamille.set(p, etatValeurisationStockParSousFamille);
				  		
				  		
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
		    
		    		
	    	
	  
	    		
	    		
	    		
	    		
	    	
	    		
	    		
	    		
	    		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////  Etat  PF //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    		
	    		ConsulterEtatPrixMoyenStockInitialPF();
		    		
	    		for(int i=0;i<listEtatVente.size();i++)
	    		{
	    			
	    			EtatVenteParFamilleArticle etatVenteParFamilleArticle=listEtatVente.get(i);
					
					
                    EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=new EtatValeurisationStockParSousFamille();
			  		
                    etatValeurisationStockParSousFamille.setFamille(etatVenteParFamilleArticle.getFamilleArticlePF().getLiblle());
			  		if(etatVenteParFamilleArticle.getFamilleArticlePF().equals(FamilleOffre))
			  		{
			  			
			  			etatValeurisationStockParSousFamille.setMatierePremiere(etatVenteParFamilleArticle.getFamilleArticlePF().getLiblle());
			  			
			  			
			  			
			  		}else
			  		{
			  			etatValeurisationStockParSousFamille.setMatierePremiere(etatVenteParFamilleArticle.getSousFamilleArticlePF().getLiblle());
			  			
			  		
			  		}
			  		
			  		etatValeurisationStockParSousFamille.setType(MAGASIN_CODE_TYPE_PF);
			  		etatValeurisationStockParSousFamille.setSousFamille(etatVenteParFamilleArticle.getSousFamilleArticlePF().getLiblle());
			  		etatValeurisationStockParSousFamille.setQuantiteInitial(etatVenteParFamilleArticle.getTotalVente());
			  		etatValeurisationStockParSousFamille.setPrixInitial(etatVenteParFamilleArticle.getPrixMoyen());
			  		etatValeurisationStockParSousFamille.setMontantInitial(etatValeurisationStockParSousFamille.getQuantiteInitial().multiply(etatValeurisationStockParSousFamille.getPrixInitial()));
			  		etatValeurisationStockParSousFamille.setQuantiteAchat(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixAchat(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantAchat(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteDechetService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixDechetService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantDechetService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteOffreService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixOffreService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantOffreService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantitevente(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixvente(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantvente(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteGratuit(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixGratuit(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantGratuit(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteAvoir(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixAvoir(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantAvoir(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteAvoirClientPF(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixAvoirClientPF(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantAvoirClientPF(BigDecimal.ZERO);
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
	    		
	    		
	    	EtatPrixMoyenAchatPF();	
	    		boolean trouve=false;
	    	for(int i=0;i<listEtatVente.size();i++)	
	    	{
	    		trouve=false;
	    		EtatVenteParFamilleArticle etatAchatParFamilleArticle=listEtatVente.get(i);
	    		
	    		
	    		
	    	for(int j=0;j<listEtatValeurisationStockParSousFamille.size();j++)	
	    	{
	    		
	    		  EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille	=listEtatValeurisationStockParSousFamille.get(j);
	    		
	    		  if(etatAchatParFamilleArticle.getSousFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getMatierePremiere()))
	    		  {
	    			  
	    		
	    			  
	    			  
	    			  if(etatAchatParFamilleArticle.getFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getFamille()))
		    		  {
	    				  
	    				  
	    				  trouve=true;
	    				  
	    				  etatValeurisationStockParSousFamille.setQuantiteAchat(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatAchatParFamilleArticle.getTotalVente()));
	    				  if(etatValeurisationStockParSousFamille.getQuantiteAchat().add(etatAchatParFamilleArticle.getTotalVente()).compareTo(BigDecimal.ZERO)!=0)
	    				  {
	    					  
		    				  etatValeurisationStockParSousFamille.setPrixAchat(etatAchatParFamilleArticle.getPrixMoyen());

	    				  }else
	    				  {
	    					  
		    				  etatValeurisationStockParSousFamille.setPrixAchat(BigDecimal.ZERO);

	    				  }
	    				  
	    				  etatValeurisationStockParSousFamille.setMontantAchat(etatValeurisationStockParSousFamille.getQuantiteAchat().multiply(etatValeurisationStockParSousFamille.getPrixAchat()));
	    				  
	    				  listEtatValeurisationStockParSousFamille.set(j, etatValeurisationStockParSousFamille) ;
	    				  
	    				  
	    				  
	    				  
	    				  
	    				  
		    		  }
		    			 
	    				  
	    			  
	    		  }
	    		  
	    		
	    		
	    	}
	    		
	    		
	    		if(trouve==false)
	    		{
	    			

					
                    EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=new EtatValeurisationStockParSousFamille();
                    etatValeurisationStockParSousFamille.setType(MAGASIN_CODE_TYPE_PF);
			  		etatValeurisationStockParSousFamille.setMatierePremiere(etatAchatParFamilleArticle.getSousFamilleArticlePF().getLiblle());
			  		etatValeurisationStockParSousFamille.setFamille(etatAchatParFamilleArticle.getFamilleArticlePF().getLiblle());
			  		etatValeurisationStockParSousFamille.setSousFamille(etatAchatParFamilleArticle.getSousFamilleArticlePF().getLiblle());
			  		etatValeurisationStockParSousFamille.setQuantiteInitial(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixInitial(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantInitial(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteAchat(etatAchatParFamilleArticle.getTotalVente());
			  		etatValeurisationStockParSousFamille.setPrixAchat(etatAchatParFamilleArticle.getPrixMoyen());
			  		etatValeurisationStockParSousFamille.setMontantAchat(etatValeurisationStockParSousFamille.getPrixAchat().multiply(etatValeurisationStockParSousFamille.getQuantiteAchat()));
			  		etatValeurisationStockParSousFamille.setQuantiteSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteDechetService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixDechetService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantDechetService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteOffreService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixOffreService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantOffreService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantitevente(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixvente(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantvente(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteGratuit(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixGratuit(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantGratuit(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteAvoir(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixAvoir(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantAvoir(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteAvoirClientPF(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixAvoirClientPF(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantAvoirClientPF(BigDecimal.ZERO);
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
	    		
	    		
	    		
	    		
	    		
	    		
	    	}
	    		
	    		
	    		
	    		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Service  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    		
	    		
	    	EtatMoyenValeurisationProductionparSousFamille();
	    	boolean existant=false;
	    	for(int i=0;i<listEtatPrixMoyenValeurisationProduction.size();i++)
	    	{
	    		existant=false;
	    		EtatPrixMoyenMP etatPrixMoyenMP=listEtatPrixMoyenValeurisationProduction.get(i);
	    		
	    		
	    		
	    		
	    	for(int j=0;j<listEtatValeurisationStockParSousFamille.size();j++)	
	    	{
	    		
	    		  EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille	=listEtatValeurisationStockParSousFamille.get(j);
	    		
	    		  
	    		  if(etatPrixMoyenMP.getFamille().equals(FamilleOffre.getLiblle()))
	    		  {
	    			  
	    			  
	    			  if(etatPrixMoyenMP.getFamille().equals(etatValeurisationStockParSousFamille.getMatierePremiere()))
		    		  {
		    			  
		    			  
		    			  if(etatPrixMoyenMP.getSousfamille().equals(etatValeurisationStockParSousFamille.getSousFamille()))
			    		  {
		    				  
			    			  if(etatPrixMoyenMP.getFamille().equals(etatValeurisationStockParSousFamille.getFamille()))
				    		  {
                               existant=true;
			    				  
			    				  etatValeurisationStockParSousFamille.setQuantiteOffreService(etatValeurisationStockParSousFamille.getQuantiteOffreService().add(etatPrixMoyenMP.getQuantite()));
			    				  if(etatValeurisationStockParSousFamille.getQuantiteOffreService().add(etatPrixMoyenMP.getQuantite()).compareTo(BigDecimal.ZERO)!=0)
			    				  {
			    					  
				    				  etatValeurisationStockParSousFamille.setPrixOffreService(etatPrixMoyenMP.getPrix());

			    				  }else
			    				  {
			    					  
				    				  etatValeurisationStockParSousFamille.setPrixOffreService (BigDecimal.ZERO);

			    				  }
			    				  
			    				  etatValeurisationStockParSousFamille.setMontantOffreService (etatValeurisationStockParSousFamille.getQuantiteOffreService().multiply(etatValeurisationStockParSousFamille.getPrixOffreService()));
			    				  
			    				  listEtatValeurisationStockParSousFamille.set(j, etatValeurisationStockParSousFamille) ;  
			    				  
				    		  }
		    					  
		    					  
		    					  
		    				  
		    				  
			    		  }
			    			 
		    				  
		    			  
		    		  }
	    			  
	    			  
	    			  
	    		  }else 	  
		    		  if( etatPrixMoyenMP.getFamille().equals(FamilleDechet.getLiblle()))
		    		  {
		    			  
		    			  
		    			  if(etatPrixMoyenMP.getFamille().equals(etatValeurisationStockParSousFamille.getMatierePremiere()))
			    		  {
			    			  
			    			  
			    			  if(etatPrixMoyenMP.getSousfamille().equals(etatValeurisationStockParSousFamille.getSousFamille()))
				    		  {
			    				  
				    			  if(etatPrixMoyenMP.getFamille().equals(etatValeurisationStockParSousFamille.getFamille()))
					    		  {
	                               existant=true;
				    				  
				    				  etatValeurisationStockParSousFamille.setQuantiteDechetService(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatPrixMoyenMP.getQuantite()));
				    				  if(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatPrixMoyenMP.getQuantite()).compareTo(BigDecimal.ZERO)!=0)
				    				  {
				    					  
					    				  etatValeurisationStockParSousFamille.setPrixDechetService(etatPrixMoyenMP.getPrix());

				    				  }else
				    				  {
				    					  
					    				  etatValeurisationStockParSousFamille.setPrixDechetService (BigDecimal.ZERO);

				    				  }
				    				  
				    				  etatValeurisationStockParSousFamille.setMontantDechetService(etatValeurisationStockParSousFamille.getQuantiteDechetService().multiply(etatValeurisationStockParSousFamille.getPrixDechetService()));
				    				  
				    				  listEtatValeurisationStockParSousFamille.set(j, etatValeurisationStockParSousFamille) ;  
				    				  
					    		  }
			    					  
			    					  
			    					  
			    				  
			    				  
				    		  }
				    			 
			    				  
			    			  
			    		  }
		    			  
		    			  
		    			  
		    		  }
	    		  
	    		  
	    		  
	    		  
	    		  else
	    		  {
	    			  
	    			  
	    			  
	    			  
	    			  if(etatPrixMoyenMP.getSousfamille().equals(etatValeurisationStockParSousFamille.getMatierePremiere()))
		    		  {
		    			  
		    			  
		    			  if(etatPrixMoyenMP.getSousfamille().equals(etatValeurisationStockParSousFamille.getSousFamille()))
			    		  {
		    				  if(etatPrixMoyenMP.getFamille().equals(etatValeurisationStockParSousFamille.getFamille()))
				    		  {
		  existant=true;
			    				  
			    				  etatValeurisationStockParSousFamille.setQuantiteService (etatValeurisationStockParSousFamille.getQuantiteService().add(etatPrixMoyenMP.getQuantite()));
			    				  if(etatValeurisationStockParSousFamille.getQuantiteService().add(etatPrixMoyenMP.getQuantite()).compareTo(BigDecimal.ZERO)!=0)
			    				  {
			    					  
				    				  etatValeurisationStockParSousFamille.setPrixService(etatPrixMoyenMP.getPrix());

			    				  }else
			    				  {
			    					  
				    				  etatValeurisationStockParSousFamille.setPrixService (BigDecimal.ZERO);

			    				  }
			    				  
			    				  etatValeurisationStockParSousFamille.setMontantService(etatValeurisationStockParSousFamille.getQuantiteService().multiply(etatValeurisationStockParSousFamille.getPrixService()));
			    				  
			    				  listEtatValeurisationStockParSousFamille.set(j, etatValeurisationStockParSousFamille) ; 
		    					    
				    		  }
		    					  
		    			
		    				  
		    				  
			    		  }
			    			 
		    				  
		    			  
		    		  }
	    			  
	    			  
	    			  
	    			  
	    			  
	    			  
	    		  }
	    		  
	    		
	    		  
	    		
	    		
	    	}
	    		
	    		
	    		if(existant==false)
	    		{
	    			

					
                    EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=new EtatValeurisationStockParSousFamille();
                    etatValeurisationStockParSousFamille.setType(MAGASIN_CODE_TYPE_PF);
                    if(etatPrixMoyenMP.getFamille().equals(FamilleOffre.getLiblle()))
    	    		{
                    	etatValeurisationStockParSousFamille.setMatierePremiere(FamilleOffre.getLiblle());
                    	
                    	etatValeurisationStockParSousFamille.setQuantiteOffreService(etatPrixMoyenMP.getQuantite());
    			  		etatValeurisationStockParSousFamille.setPrixOffreService(etatPrixMoyenMP.getPrix());
    			  		etatValeurisationStockParSousFamille.setMontantOffreService(etatValeurisationStockParSousFamille.getQuantiteOffreService().multiply(etatValeurisationStockParSousFamille.getPrixOffreService()));
                    	
    			  		etatValeurisationStockParSousFamille.setQuantiteService(BigDecimal.ZERO);
    			  		etatValeurisationStockParSousFamille.setPrixService(BigDecimal.ZERO);
    			  		etatValeurisationStockParSousFamille.setMontantService(BigDecimal.ZERO);
    			  		
    			  		etatValeurisationStockParSousFamille.setQuantiteDechetService(BigDecimal.ZERO);
    			  		etatValeurisationStockParSousFamille.setPrixDechetService(BigDecimal.ZERO);
    			  		etatValeurisationStockParSousFamille.setMontantDechetService(BigDecimal.ZERO);
    			  		
    	    		}else if(etatPrixMoyenMP.getFamille().equals(FamilleDechet.getLiblle()))
    	    		{
    	    			etatValeurisationStockParSousFamille.setMatierePremiere(FamilleDechet.getLiblle());
    	    			
    	    			etatValeurisationStockParSousFamille.setQuantiteDechetService(etatPrixMoyenMP.getQuantite());
    			  		etatValeurisationStockParSousFamille.setPrixDechetService(etatPrixMoyenMP.getPrix());
    			  		etatValeurisationStockParSousFamille.setMontantDechetService(etatValeurisationStockParSousFamille.getQuantiteDechetService().multiply(etatValeurisationStockParSousFamille.getPrixDechetService()));
    	    			
    			  		etatValeurisationStockParSousFamille.setQuantiteOffreService(BigDecimal.ZERO);
    			  		etatValeurisationStockParSousFamille.setPrixOffreService(BigDecimal.ZERO);
    			  		etatValeurisationStockParSousFamille.setMontantOffreService(BigDecimal.ZERO);
    			  		
    			  		etatValeurisationStockParSousFamille.setQuantiteService(BigDecimal.ZERO);
    			  		etatValeurisationStockParSousFamille.setPrixService(BigDecimal.ZERO);
    			  		etatValeurisationStockParSousFamille.setMontantService(BigDecimal.ZERO);
    			  		
    			  		
    	    			
    	    		}else
    	    		{
    	    			etatValeurisationStockParSousFamille.setMatierePremiere(etatPrixMoyenMP.getSousfamille());
    	    			
    	    			etatValeurisationStockParSousFamille.setQuantiteService(etatPrixMoyenMP.getQuantite());
    			  		etatValeurisationStockParSousFamille.setPrixService(etatPrixMoyenMP.getPrix());
    			  		etatValeurisationStockParSousFamille.setMontantService(etatValeurisationStockParSousFamille.getQuantiteService().multiply(etatValeurisationStockParSousFamille.getPrixService()));
    			  		
    			  		etatValeurisationStockParSousFamille.setQuantiteOffreService(BigDecimal.ZERO);
    			  		etatValeurisationStockParSousFamille.setPrixOffreService(BigDecimal.ZERO);
    			  		etatValeurisationStockParSousFamille.setMontantOffreService(BigDecimal.ZERO);
    			  		
    			  		etatValeurisationStockParSousFamille.setQuantiteDechetService(BigDecimal.ZERO);
    			  		etatValeurisationStockParSousFamille.setPrixDechetService(BigDecimal.ZERO);
    			  		etatValeurisationStockParSousFamille.setMontantDechetService(BigDecimal.ZERO);
    	    			
    	    			
    	    		}
			  		
			  		etatValeurisationStockParSousFamille.setFamille(etatPrixMoyenMP.getFamille());
			  		etatValeurisationStockParSousFamille.setSousFamille(etatPrixMoyenMP.getSousfamille());			  		
			  		etatValeurisationStockParSousFamille.setQuantiteInitial(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixInitial(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantInitial(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteAchat(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixAchat(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantAchat(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantOffreProduction(BigDecimal.ZERO);			  				  	
			  		etatValeurisationStockParSousFamille.setQuantitevente(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixvente(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantvente(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteGratuit(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixGratuit(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantGratuit(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteAvoir(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixAvoir(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantAvoir(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteAvoirClientPF(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixAvoirClientPF(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantAvoirClientPF(BigDecimal.ZERO);
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
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    	}
	    	
	    	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Vente  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	
	    	
	    	CalculerEtatPrixMoyenventePFparSousFamille();
	    	
	    	boolean trouver=false;
	    	for(int i=0;i<listEtatVente.size();i++)
	    	{
	    		trouver=false;
	    		EtatVenteParFamilleArticle etatVenteParFamilleArticle=listEtatVente.get(i);
	    		
	    		
	    		
	    		
	    	for(int j=0;j<listEtatValeurisationStockParSousFamille.size();j++)	
	    	{
	    		
	    		  EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille	=listEtatValeurisationStockParSousFamille.get(j);
	    		if(!etatVenteParFamilleArticle.getFamilleArticlePF().getLiblle().equals(FamilleOffre.getLiblle()))
	    		{
	    			  if(etatVenteParFamilleArticle.getSousFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getMatierePremiere()))
		    		  {
		    			  
		    			  
		    			  if(etatVenteParFamilleArticle.getSousFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getSousFamille()))
			    		  {
		    				  
		    				
		    					
		    					  trouver=true;
			    				  
			    				  etatValeurisationStockParSousFamille.setQuantitevente (etatValeurisationStockParSousFamille.getQuantitevente().add(etatVenteParFamilleArticle.getTotalVente()));
			    				  if(etatValeurisationStockParSousFamille.getQuantitevente().add(etatVenteParFamilleArticle.getTotalVente()).compareTo(BigDecimal.ZERO)!=0)
			    				  {
			    					  
				    				  etatValeurisationStockParSousFamille.setPrixvente (etatVenteParFamilleArticle.getPrixMoyen());

			    				  }else
			    				  {
			    					  
				    				  etatValeurisationStockParSousFamille.setPrixvente (BigDecimal.ZERO);

			    				  }
			    				  
			    				  etatValeurisationStockParSousFamille.setMontantvente (etatValeurisationStockParSousFamille.getQuantitevente().multiply(etatValeurisationStockParSousFamille.getPrixvente()));
			    				  
			    				  listEtatValeurisationStockParSousFamille.set(j, etatValeurisationStockParSousFamille) ;
			    				  
			    				  
		    					
		    				
		    			
		    				  
		    				  
		    				  
		    				  
			    		  }
			    			 
		    				  
		    			  
		    		  }
	    			
	    		}else
	    		{
	    			
	    			if(etatVenteParFamilleArticle.getSousFamilleArticlePF().getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || etatVenteParFamilleArticle.getSousFamilleArticlePF().getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || etatVenteParFamilleArticle.getSousFamilleArticlePF().getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) || etatVenteParFamilleArticle.getSousFamilleArticlePF().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_CADEAU) )
	    			{
	    				
	    				if(etatVenteParFamilleArticle.getFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getMatierePremiere()))
			    		  {
			    			  
			    			  
			    			  if(etatVenteParFamilleArticle.getSousFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getSousFamille()))
				    		  {
			    				 
			    					  
			    					  
				    				  trouver=true;
				    				  
				    				  etatValeurisationStockParSousFamille.setQuantiteGratuit(etatValeurisationStockParSousFamille.getQuantiteGratuit().add(etatVenteParFamilleArticle.getTotalVente()));
				    				  if(etatValeurisationStockParSousFamille.getQuantiteGratuit().add(etatVenteParFamilleArticle.getTotalVente()).compareTo(BigDecimal.ZERO)!=0)
				    				  {
				    					  
					    				  etatValeurisationStockParSousFamille.setPrixGratuit (etatVenteParFamilleArticle.getPrixMoyen());

				    				  }else
				    				  {
				    					  
					    				  etatValeurisationStockParSousFamille.setPrixGratuit (BigDecimal.ZERO);

				    				  }
				    				  
				    				  etatValeurisationStockParSousFamille.setMontantGratuit (etatValeurisationStockParSousFamille.getQuantiteGratuit().multiply(etatValeurisationStockParSousFamille.getPrixGratuit()));
				    				  
				    				  listEtatValeurisationStockParSousFamille.set(j, etatValeurisationStockParSousFamille) ;
				    				  
				    				  
			    					  
			    				  
			    				 
			    				  
			    				  
			    				  
			    				  
				    		  }
				    			 
			    				  
			    			  
			    		  }
	    				
	    				
	    			}else
	    			{
	    				
	    				

	    				
	    				if(etatVenteParFamilleArticle.getSousFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getMatierePremiere()))
			    		  {
			    			  
			    			  
			    			  if(etatVenteParFamilleArticle.getSousFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getSousFamille()))
				    		  {
			    				  
			    				 
			    					  
			    					  trouver=true;
				    				  
				    				  etatValeurisationStockParSousFamille.setQuantiteGratuit(etatValeurisationStockParSousFamille.getQuantiteGratuit().add(etatVenteParFamilleArticle.getTotalVente()));
				    				  if(etatValeurisationStockParSousFamille.getQuantiteGratuit().add(etatVenteParFamilleArticle.getTotalVente()).compareTo(BigDecimal.ZERO)!=0)
				    				  {
				    					  
					    				  etatValeurisationStockParSousFamille.setPrixGratuit (etatVenteParFamilleArticle.getPrixMoyen());

				    				  }else
				    				  {
				    					  
					    				  etatValeurisationStockParSousFamille.setPrixGratuit (BigDecimal.ZERO);

				    				  }
				    				  
				    				  etatValeurisationStockParSousFamille.setMontantGratuit (etatValeurisationStockParSousFamille.getQuantiteGratuit().multiply(etatValeurisationStockParSousFamille.getPrixGratuit()));
				    				  
				    				  listEtatValeurisationStockParSousFamille.set(j, etatValeurisationStockParSousFamille) ;
				    				  
				    				  
				    				   
			    				 
			    		
			    				  
			    				  
			    				  
				    		  }
				    			 
			    				  
			    			  
			    		  }
	    				
	    				
	    			
	    				
	    				
	    			}
	    	
	    			
	    			
	    			
	    			
	    		}
	    		
	    		  
	    		
	    		
	    	}
	    		
	    		
	    		if(trouver==false)
	    		{
	    			

					
                    EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=new EtatValeurisationStockParSousFamille();
			  		
                    etatValeurisationStockParSousFamille.setType(MAGASIN_CODE_TYPE_PF);
			  		if(!etatVenteParFamilleArticle.getFamilleArticlePF().getLiblle().equals(FamilleOffre.getLiblle()))
		    		{
			  			etatValeurisationStockParSousFamille.setMatierePremiere(etatVenteParFamilleArticle.getSousFamilleArticlePF().getLiblle());
			  			etatValeurisationStockParSousFamille.setFamille(etatVenteParFamilleArticle.getFamilleArticlePF().getLiblle());
				  		etatValeurisationStockParSousFamille.setSousFamille(etatVenteParFamilleArticle.getSousFamilleArticlePF().getLiblle());
				  		
				  		etatValeurisationStockParSousFamille.setQuantitevente(etatVenteParFamilleArticle.getTotalVente());
				  		etatValeurisationStockParSousFamille.setPrixvente(etatVenteParFamilleArticle.getPrixMoyen());
				  		etatValeurisationStockParSousFamille.setMontantvente(etatVenteParFamilleArticle.getMontant());
				  		
				  		etatValeurisationStockParSousFamille.setQuantiteGratuit(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setPrixGratuit(BigDecimal.ZERO);
				  		etatValeurisationStockParSousFamille.setMontantGratuit(BigDecimal.ZERO);
				  
				  		
		    		}else
		    		{
		    			
		    			if(etatVenteParFamilleArticle.getSousFamilleArticlePF().getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || etatVenteParFamilleArticle.getSousFamilleArticlePF().getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || etatVenteParFamilleArticle.getSousFamilleArticlePF().getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) || etatVenteParFamilleArticle.getSousFamilleArticlePF().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_CADEAU) )
		    			{
		    				etatValeurisationStockParSousFamille.setMatierePremiere(etatVenteParFamilleArticle.getFamilleArticlePF().getLiblle());
		    				etatValeurisationStockParSousFamille.setFamille(etatVenteParFamilleArticle.getFamilleArticlePF().getLiblle());
					  		etatValeurisationStockParSousFamille.setSousFamille(etatVenteParFamilleArticle.getSousFamilleArticlePF().getLiblle());
					  		etatValeurisationStockParSousFamille.setQuantiteGratuit(etatVenteParFamilleArticle.getTotalVente());
					  		etatValeurisationStockParSousFamille.setPrixGratuit(etatVenteParFamilleArticle.getPrixMoyen());
					  		etatValeurisationStockParSousFamille.setMontantGratuit(etatVenteParFamilleArticle.getMontant());
		    				
					  		etatValeurisationStockParSousFamille.setQuantitevente(BigDecimal.ZERO);
					  		etatValeurisationStockParSousFamille.setPrixvente(BigDecimal.ZERO);
					  		etatValeurisationStockParSousFamille.setMontantvente(BigDecimal.ZERO);
					  		
		    				
		    			}else
		    			{
		    				etatValeurisationStockParSousFamille.setMatierePremiere(etatVenteParFamilleArticle.getSousFamilleArticlePF().getLiblle());
		    				etatValeurisationStockParSousFamille.setFamille(etatVenteParFamilleArticle.getFamilleArticlePF().getLiblle());
					  		etatValeurisationStockParSousFamille.setSousFamille(etatVenteParFamilleArticle.getSousFamilleArticlePF().getLiblle());
					  		etatValeurisationStockParSousFamille.setQuantiteGratuit(etatVenteParFamilleArticle.getTotalVente());
					  		etatValeurisationStockParSousFamille.setPrixGratuit(etatVenteParFamilleArticle.getPrixMoyen());
					  		etatValeurisationStockParSousFamille.setMontantGratuit(etatVenteParFamilleArticle.getMontant());
					  		
					  		etatValeurisationStockParSousFamille.setQuantitevente(BigDecimal.ZERO);
					  		etatValeurisationStockParSousFamille.setPrixvente(BigDecimal.ZERO);
					  		etatValeurisationStockParSousFamille.setMontantvente(BigDecimal.ZERO);
		    				
		    				
		    				
		    			}
		    			
		    			
		    			
		    		}
			  		
			  		etatValeurisationStockParSousFamille.setQuantiteInitial(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixInitial(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantInitial(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteAchat(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixAchat(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantAchat(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteDechetService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixDechetService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantDechetService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteOffreService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixOffreService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantOffreService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteAvoir(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixAvoir(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantAvoir(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteAvoirClientPF(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixAvoirClientPF(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantAvoirClientPF(BigDecimal.ZERO);
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
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    	}
	    	
	    	
	    	
	    	
	    	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  Avoir  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	
	    	
	    	CalculerEtatPrixMoyenAvoirPFParFamille();
	    	
	    	boolean exist=false;
	    	for(int i=0;i<listEtatAvoir.size();i++)
	    	{
	    		exist=false;
	    		EtatVenteParFamilleArticle etatAvoir=listEtatAvoir.get(i);
	    		
	    		
	    		
	    		
	    	for(int j=0;j<listEtatValeurisationStockParSousFamille.size();j++)	
	    	{
	    		
	    		  EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille	=listEtatValeurisationStockParSousFamille.get(j);
	    		
	    		  if(etatAvoir.getSousFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getMatierePremiere()))
	    		  {
	    			  
	    			  
	    			  if(etatAvoir.getSousFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getSousFamille()))
		    		  {
	    				 
	    					  
	    					  exist=true;
		    				  
		    				  etatValeurisationStockParSousFamille.setQuantiteAvoir (etatValeurisationStockParSousFamille.getQuantiteAvoir().add(etatAvoir.getTotalVente()));
		    				  if(etatValeurisationStockParSousFamille.getQuantiteService().add(etatAvoir.getTotalVente()).compareTo(BigDecimal.ZERO)!=0)
		    				  {
		    					  
			    				  etatValeurisationStockParSousFamille.setPrixAvoir(etatAvoir.getPrixMoyen());

		    				  }else
		    				  {
		    					  
			    				  etatValeurisationStockParSousFamille.setPrixAvoir (BigDecimal.ZERO);

		    				  }
		    				  
		    				  etatValeurisationStockParSousFamille.setMontantAvoir (etatValeurisationStockParSousFamille.getQuantiteAvoir().multiply(etatValeurisationStockParSousFamille.getPrixAvoir()));
		    				  
		    				  listEtatValeurisationStockParSousFamille.set(j, etatValeurisationStockParSousFamille) ;
		    				  
	    					  
	    				 
	    				  
	    			
	    				  
	    				  
	    				  
	    				  
	    				  
		    		  }
		    			 
	    				  
	    			  
	    		  }
	    		  
	    		
	    		
	    	}
	    		
	    		
	    		if(exist==false)
	    		{
	    			

					
                    EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=new EtatValeurisationStockParSousFamille();
                    etatValeurisationStockParSousFamille.setType(MAGASIN_CODE_TYPE_PF);
			  		etatValeurisationStockParSousFamille.setMatierePremiere(etatAvoir.getSousFamilleArticlePF().getLiblle());
			  		etatValeurisationStockParSousFamille.setFamille(etatAvoir.getFamilleArticlePF().getLiblle());
			  		etatValeurisationStockParSousFamille.setSousFamille(etatAvoir.getSousFamilleArticlePF().getLiblle());			  		
			  		etatValeurisationStockParSousFamille.setQuantiteInitial(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixInitial(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantInitial(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteAchat(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixAchat(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantAchat(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantSortie(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantDechet(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantOffreProduction(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteDechetService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixDechetService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantDechetService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteOffreService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixOffreService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantOffreService(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantitevente(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixvente(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantvente(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteGratuit(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixGratuit(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantGratuit(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setQuantiteAvoir(etatAvoir.getTotalVente());
			  		etatValeurisationStockParSousFamille.setPrixAvoir(etatAvoir.getPrixMoyen());
			  		etatValeurisationStockParSousFamille.setMontantAvoir(etatAvoir.getMontant());
			  		etatValeurisationStockParSousFamille.setQuantiteAvoirClientPF(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setPrixAvoirClientPF(BigDecimal.ZERO);
			  		etatValeurisationStockParSousFamille.setMontantAvoirClientPF(BigDecimal.ZERO);
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
	    		
	    		
	    		
	    	}
	    	
	    	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Avoir Client PF ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	

CalculerEtatPrixMoyenAvoirClientPFParFamille();

boolean exister=false;
for(int i=0;i<listEtatAvoirClientPF.size();i++)
{
exister=false;
EtatVenteParFamilleArticle etatAvoir=listEtatAvoirClientPF.get(i);




for(int j=0;j<listEtatValeurisationStockParSousFamille.size();j++)	
{

EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille	=listEtatValeurisationStockParSousFamille.get(j);

if(etatAvoir.getSousFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getMatierePremiere()))
{


if(etatAvoir.getSousFamilleArticlePF().getLiblle().equals(etatValeurisationStockParSousFamille.getSousFamille()))
{


exister=true;

etatValeurisationStockParSousFamille.setQuantiteAvoirClientPF (etatValeurisationStockParSousFamille.getQuantiteAvoirClientPF().add(etatAvoir.getTotalVente()));
if(etatValeurisationStockParSousFamille.getQuantiteService().add(etatAvoir.getTotalVente()).compareTo(BigDecimal.ZERO)!=0)
{

etatValeurisationStockParSousFamille.setPrixAvoirClientPF (etatAvoir.getPrixMoyen());

}else
{

etatValeurisationStockParSousFamille.setPrixAvoirClientPF(BigDecimal.ZERO);

}

etatValeurisationStockParSousFamille.setMontantAvoirClientPF (etatValeurisationStockParSousFamille.getQuantiteAvoirClientPF().multiply(etatValeurisationStockParSousFamille.getPrixAvoirClientPF()));

listEtatValeurisationStockParSousFamille.set(j, etatValeurisationStockParSousFamille) ;










}



}



}


if(exister==false)
{



EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=new EtatValeurisationStockParSousFamille();
etatValeurisationStockParSousFamille.setType(MAGASIN_CODE_TYPE_PF);
etatValeurisationStockParSousFamille.setMatierePremiere(etatAvoir.getSousFamilleArticlePF().getLiblle());
etatValeurisationStockParSousFamille.setFamille(etatAvoir.getFamilleArticlePF().getLiblle());
etatValeurisationStockParSousFamille.setSousFamille(etatAvoir.getSousFamilleArticlePF().getLiblle());			  		
etatValeurisationStockParSousFamille.setQuantiteInitial(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setPrixInitial(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setMontantInitial(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setQuantiteAchat(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setPrixAchat(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setMontantAchat(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setQuantiteSortie(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setPrixSortie(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setMontantSortie(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setQuantiteDechet(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setPrixDechet(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setMontantDechet(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setQuantiteOffreProduction(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setPrixOffreProduction(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setMontantOffreProduction(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setQuantiteService(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setPrixService(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setMontantService(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setQuantiteDechetService(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setPrixDechetService(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setMontantDechetService(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setQuantiteOffreService(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setPrixOffreService(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setMontantOffreService(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setQuantitevente(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setPrixvente(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setMontantvente(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setQuantiteGratuit(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setPrixGratuit(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setMontantGratuit(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setQuantiteAvoir(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setPrixAvoir(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setMontantAvoir(BigDecimal.ZERO);
etatValeurisationStockParSousFamille.setQuantiteAvoirClientPF(etatAvoir.getTotalVente());
etatValeurisationStockParSousFamille.setPrixAvoirClientPF(etatAvoir.getPrixMoyen());
etatValeurisationStockParSousFamille.setMontantAvoirClientPF(etatAvoir.getMontant());

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



}	    	
	    	
	    	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  Transfert MP PF  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    	
	    	
	    	boolean find=false;
	    	
	    	for(int t=0;t<listEtatValeurisationStockParSousFamilleMPTransformerEnPF.size();t++)
	    	{
	    		find=false;
	    		
	    		  EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamille=listEtatValeurisationStockParSousFamilleMPTransformerEnPF.get(t);
	    		
	    		
	    		for(int d=0;d<listEtatValeurisationStockParSousFamille.size();d++)
		    	{
	    			
	    			  EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamilleTmp=listEtatValeurisationStockParSousFamille.get(d);
	    			
	    			
	    			if(etatValeurisationStockParSousFamille.getSousFamille().equals(etatValeurisationStockParSousFamilleTmp.getMatierePremiere()))
	    			{
	    				
	    			
	    				
	    				find=true;
	    				etatValeurisationStockParSousFamilleTmp.setQuantiteTransfertMPPF(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF().add(etatValeurisationStockParSousFamilleTmp.getQuantiteTransfertMPPF()));
	    				if(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF().add(etatValeurisationStockParSousFamilleTmp.getQuantiteTransfertMPPF()).compareTo(BigDecimal.ZERO)!=0)
	    				{
	    					etatValeurisationStockParSousFamilleTmp.setPrixTransfertMPPF(etatValeurisationStockParSousFamille.getPrixTransfertMPPF());
	    					
	    				}else
	    				{
	    					etatValeurisationStockParSousFamilleTmp.setPrixTransfertMPPF(BigDecimal.ZERO);
	    				}
	    				
	    				etatValeurisationStockParSousFamilleTmp.setMontantTransfertMPPF(etatValeurisationStockParSousFamilleTmp.getQuantiteTransfertMPPF().multiply(etatValeurisationStockParSousFamilleTmp.getPrixTransfertMPPF()));
	    				
	    				etatValeurisationStockParSousFamilleTmp.setArticle(etatValeurisationStockParSousFamille.getMatierePremiere());
	    				
	    			
	    				
	    		
	    			}
	    			
	    			
	    			
		    	}
	    		
	    		if(find==false)
	    		{
	    			
	    			

	    			

					
                    EtatValeurisationStockParSousFamille etatValeurisationStockParSousFamilleTm=new EtatValeurisationStockParSousFamille();
                    etatValeurisationStockParSousFamilleTm.setType(MAGASIN_CODE_TYPE_PF);
                    etatValeurisationStockParSousFamilleTm.setMatierePremiere(etatValeurisationStockParSousFamille.getSousFamille());
                    etatValeurisationStockParSousFamilleTm.setFamille(etatValeurisationStockParSousFamille.getFamille());
                    etatValeurisationStockParSousFamilleTm.setSousFamille(etatValeurisationStockParSousFamille.getSousFamille());		
                    etatValeurisationStockParSousFamilleTm.setArticle(etatValeurisationStockParSousFamille.getMatierePremiere());
                    etatValeurisationStockParSousFamilleTm.setQuantiteInitial(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setPrixInitial(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setMontantInitial(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setQuantiteAchat(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setPrixAchat(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setMontantAchat(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setQuantiteSortie(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setPrixSortie(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setMontantSortie(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setQuantiteDechet(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setPrixDechet(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setMontantDechet(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setQuantiteOffreProduction(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setPrixOffreProduction(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setMontantOffreProduction(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setQuantiteService(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setPrixService(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setMontantService(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setQuantiteDechetService(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setPrixDechetService(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setMontantDechetService(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setQuantiteOffreService(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setPrixOffreService(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setMontantOffreService(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setQuantitevente(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setPrixvente(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setMontantvente(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setQuantiteGratuit(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setPrixGratuit(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setMontantGratuit(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setQuantiteAvoir(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setPrixAvoir(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setMontantAvoir(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setQuantiteAvoirClientPF(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setPrixAvoirClientPF(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setMontantAvoirClientPF(BigDecimal.ZERO);
                    etatValeurisationStockParSousFamilleTm.setQuantiteTransfertMPPF(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF());
                    etatValeurisationStockParSousFamilleTm.setPrixTransfertMPPF(etatValeurisationStockParSousFamille.getPrixTransfertMPPF());
                    etatValeurisationStockParSousFamilleTm.setMontantTransfertMPPF(etatValeurisationStockParSousFamilleTm.getQuantiteTransfertMPPF().multiply(etatValeurisationStockParSousFamilleTm.getPrixTransfertMPPF()));
                    etatValeurisationStockParSousFamilleTm.setQuantiteFinale(etatValeurisationStockParSousFamilleTm.getQuantiteInitial().add(etatValeurisationStockParSousFamilleTm.getQuantiteAchat().add(etatValeurisationStockParSousFamilleTm.getQuantiteService().add(etatValeurisationStockParSousFamilleTm.getQuantiteTransfertMPPF()).add(etatValeurisationStockParSousFamilleTm.getQuantiteAvoirClientPF()))).subtract(etatValeurisationStockParSousFamilleTm.getQuantiteSortie().add(etatValeurisationStockParSousFamilleTm.getQuantiteDechet().add(etatValeurisationStockParSousFamilleTm.getQuantiteOffreProduction().add(etatValeurisationStockParSousFamilleTm.getQuantiteDechetService().add(etatValeurisationStockParSousFamilleTm.getQuantiteOffreService().add(etatValeurisationStockParSousFamilleTm.getQuantitevente()).add(etatValeurisationStockParSousFamilleTm.getQuantiteGratuit().add(etatValeurisationStockParSousFamilleTm.getQuantiteAvoir()))))))));
			  		if(etatValeurisationStockParSousFamilleTm.getQuantiteInitial().add(etatValeurisationStockParSousFamilleTm.getQuantiteAchat().add(etatValeurisationStockParSousFamilleTm.getQuantiteService().add(etatValeurisationStockParSousFamilleTm.getQuantiteTransfertMPPF()).add(etatValeurisationStockParSousFamilleTm.getQuantiteAvoirClientPF()))).compareTo(BigDecimal.ZERO)!=0)
			  		{
			  			
			  			etatValeurisationStockParSousFamilleTm.setPrixFinale((etatValeurisationStockParSousFamilleTm.getMontantInitial().add(etatValeurisationStockParSousFamilleTm.getMontantAchat().add(etatValeurisationStockParSousFamille.getMontantService().add(etatValeurisationStockParSousFamilleTm.getMontantTransfertMPPF()).add(etatValeurisationStockParSousFamilleTm.getMontantAvoirClientPF())))).divide(etatValeurisationStockParSousFamilleTm.getQuantiteInitial().add(etatValeurisationStockParSousFamilleTm.getQuantiteAchat().add(etatValeurisationStockParSousFamilleTm.getQuantiteService().add(etatValeurisationStockParSousFamilleTm.getQuantiteTransfertMPPF()).add(etatValeurisationStockParSousFamilleTm.getQuantiteAvoirClientPF()))), 6, RoundingMode.HALF_UP));
			  			
			  		}else
			  		{
			  			etatValeurisationStockParSousFamilleTm.setPrixFinale(BigDecimal.ZERO);
			  		}
			  		
			  		
			  		etatValeurisationStockParSousFamilleTm.setMontantFinale(etatValeurisationStockParSousFamilleTm.getPrixFinale().multiply(etatValeurisationStockParSousFamilleTm.getQuantiteFinale()));
			  		
			  		listEtatValeurisationStockParSousFamille.add(etatValeurisationStockParSousFamilleTm);

					
	    			
	    		
	    			
	    			
	    			
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
		  			
		  		}else
		  		{
		  			etatValeurisationStockParSousFamille.setPrixFinale(BigDecimal.ZERO);
		  		}
			  
			  
			  
			  
			  
		 
		 
		 }else if(etatValeurisationStockParSousFamille.getType().equals(Constantes.MAGASIN_CODE_TYPE_MP))
		 {
			 
			 if(etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || etatValeurisationStockParSousFamille.getFamille().equals(Constantes.LIBELLE_FAMILLE_ELNASS) )
 			{
				 
				 etatValeurisationStockParSousFamille.setMatierePremiere(Constantes.NOM_ENVRAC +etatValeurisationStockParSousFamille.getSousFamille()); 
				 
 			}else
 			{
 				if(!etatValeurisationStockParSousFamille.getFamille().equals(Constantes.FAMILLE_CADEAU) )
 				{
 					 etatValeurisationStockParSousFamille.setMatierePremiere(etatValeurisationStockParSousFamille.getSousFamille()); 
 				}
 				
 				
 				 
 				
 			}
			 
			  etatValeurisationStockParSousFamille.setQuantiteFinale(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()).subtract(etatValeurisationStockParSousFamille.getQuantiteSortie().add(etatValeurisationStockParSousFamille.getQuantiteService().add(etatValeurisationStockParSousFamille.getQuantiteTransfertMPPF())).add(etatValeurisationStockParSousFamille.getQuantiteDechet().add(etatValeurisationStockParSousFamille.getQuantiteOffreProduction().add(etatValeurisationStockParSousFamille.getQuantiteDechetService().add(etatValeurisationStockParSousFamille.getQuantiteOffreService().add(etatValeurisationStockParSousFamille.getQuantitevente()).add(etatValeurisationStockParSousFamille.getQuantiteGratuit().add(etatValeurisationStockParSousFamille.getQuantiteAvoir()))))))));
			  
			  if(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()).compareTo(BigDecimal.ZERO)!=0)
		  		{
		  			
		  			etatValeurisationStockParSousFamille.setPrixFinale((etatValeurisationStockParSousFamille.getMontantInitial().add(etatValeurisationStockParSousFamille.getMontantAchat())).divide(etatValeurisationStockParSousFamille.getQuantiteInitial().add(etatValeurisationStockParSousFamille.getQuantiteAchat()), 6, RoundingMode.HALF_UP));
		  			
		  		}else
		  		{
		  			etatValeurisationStockParSousFamille.setPrixFinale(BigDecimal.ZERO);
		  		}
			 
		 }
	  		
		  
		
	  		
	  		
	  		etatValeurisationStockParSousFamille.setMontantFinale(etatValeurisationStockParSousFamille.getPrixFinale().multiply(etatValeurisationStockParSousFamille.getQuantiteFinale()));
	
		  
	  		
	  		
	  		
		
		if(  etatValeurisationStockParSousFamille.getQuantiteGratuit().compareTo(BigDecimal.ZERO)!=0 && etatValeurisationStockParSousFamille.getType().equals(Constantes.MAGASIN_CODE_TYPE_PF))
				{
			etatValeurisationStockParSousFamille.setPrixGratuit(etatValeurisationStockParSousFamille.getPrixFinale());
			etatValeurisationStockParSousFamille.setMontantGratuit(etatValeurisationStockParSousFamille.getQuantiteGratuit().multiply(etatValeurisationStockParSousFamille.getPrixGratuit()));
			
			
				}
		
		if(  etatValeurisationStockParSousFamille.getQuantiteAvoir().compareTo(BigDecimal.ZERO)!=0 && etatValeurisationStockParSousFamille.getType().equals(Constantes.MAGASIN_CODE_TYPE_PF))
		{
	etatValeurisationStockParSousFamille.setPrixAvoir(etatValeurisationStockParSousFamille.getPrixFinale());
	etatValeurisationStockParSousFamille.setMontantAvoir(etatValeurisationStockParSousFamille.getQuantiteAvoir().multiply(etatValeurisationStockParSousFamille.getPrixAvoir()));
	
	
		}
		
		
		listEtatValeurisationStockParSousFamille.set(r, etatValeurisationStockParSousFamille);
		
		
	}
	    	
	    	
	    	
	    	

	
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
	    		
	    		String titre="Etat Marge De Stock Par Sous Famille "+mapMagasin.get(comboMagasinMP.getSelectedItem()).getLibelle() +" Et "+mapMagasin.get(comboMagasinPF.getSelectedItem()).getLibelle();
	    		String titrefeuille="Etat Marge De Stock Par Sous Famille ";
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
	    
	    JLabel label_2 = new JLabel("Total Initial :");
	    label_2.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_2.setBounds(186, 804, 112, 24);
	    add(label_2);
	    
	     labelTotalInitial = new JLabel("");
	    labelTotalInitial.setForeground(Color.RED);
	    labelTotalInitial.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalInitial.setBounds(308, 804, 257, 24);
	    add(labelTotalInitial);
	    
	    JLabel label_4 = new JLabel("Total Service :");
	    label_4.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_4.setBounds(176, 856, 167, 24);
	    add(label_4);
	    
	     labelTotalService = new JLabel("");
	    labelTotalService.setForeground(Color.RED);
	    labelTotalService.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalService.setBounds(314, 856, 221, 24);
	    add(labelTotalService);
	    
	    JLabel label_7 = new JLabel("Total Gratuite :");
	    label_7.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_7.setBounds(186, 916, 112, 24);
	    add(label_7);
	    
	     labelTotalGratuite = new JLabel("");
	    labelTotalGratuite.setForeground(Color.RED);
	    labelTotalGratuite.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalGratuite.setBounds(367, 916, 198, 24);
	    add(labelTotalGratuite);
	    
	    JLabel label_9 = new JLabel("Total Avoir :");
	    label_9.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_9.setBounds(605, 916, 112, 24);
	    add(label_9);
	    
	    JLabel label_10 = new JLabel("CA :");
	    label_10.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_10.setBounds(618, 856, 41, 24);
	    add(label_10);
	    
	    JLabel label_11 = new JLabel("Total Achat:");
	    label_11.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_11.setBounds(618, 804, 112, 24);
	    add(label_11);
	    
	     labelTotalAchat = new JLabel("");
	    labelTotalAchat.setForeground(Color.RED);
	    labelTotalAchat.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalAchat.setBounds(778, 804, 223, 24);
	    add(labelTotalAchat);
	    
	     labelTotalCA = new JLabel("");
	    labelTotalCA.setForeground(Color.RED);
	    labelTotalCA.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalCA.setBounds(669, 856, 198, 24);
	    add(labelTotalCA);
	    
	     labelTotalAvoir = new JLabel("");
	    labelTotalAvoir.setForeground(Color.RED);
	    labelTotalAvoir.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalAvoir.setBounds(786, 916, 227, 24);
	    add(labelTotalAvoir);
	    
	    JLabel label_15 = new JLabel("%:");
	    label_15.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_15.setBounds(1059, 997, 179, 24);
	    add(label_15);
	    
	    JLabel label_16 = new JLabel("Marge:");
	    label_16.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_16.setBounds(1059, 955, 179, 24);
	    add(label_16);
	    
	    JLabel label_17 = new JLabel("Total Achat Revendu:");
	    label_17.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_17.setBounds(1059, 910, 179, 24);
	    add(label_17);
	    
	    JLabel label_18 = new JLabel("Total Finale :");
	    label_18.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_18.setBounds(1059, 856, 112, 24);
	    add(label_18);
	    
	    JLabel label_19 = new JLabel("Total transfert:");
	    label_19.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_19.setBounds(1059, 804, 156, 24);
	    add(label_19);
	    
	     labelTotalTransfert = new JLabel("");
	    labelTotalTransfert.setForeground(Color.RED);
	    labelTotalTransfert.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalTransfert.setBounds(1240, 804, 235, 24);
	    add(labelTotalTransfert);
	    
	     labelTotalFinale = new JLabel("");
	    labelTotalFinale.setForeground(Color.RED);
	    labelTotalFinale.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalFinale.setBounds(1240, 856, 235, 24);
	    add(labelTotalFinale);
	    
	     labelTotalAchatRevendu = new JLabel("");
	    labelTotalAchatRevendu.setForeground(Color.RED);
	    labelTotalAchatRevendu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalAchatRevendu.setBounds(1240, 910, 235, 24);
	    add(labelTotalAchatRevendu);
	    
	     labelmarge = new JLabel((String) null);
	    labelmarge.setForeground(Color.RED);
	    labelmarge.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelmarge.setBounds(1240, 955, 235, 24);
	    add(labelmarge);
	    
	     labelpercentage = new JLabel("");
	    labelpercentage.setForeground(Color.RED);
	    labelpercentage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelpercentage.setBounds(1240, 997, 235, 24);
	    add(labelpercentage);
	    
	    JLabel lblTotalAvoirClientpf = new JLabel("Total Avoir ClientPF :");
	    lblTotalAvoirClientpf.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblTotalAvoirClientpf.setBounds(605, 955, 147, 24);
	    add(lblTotalAvoirClientpf);
	    
	     labelTotalAvoirClientPF = new JLabel("");
	    labelTotalAvoirClientPF.setForeground(Color.RED);
	    labelTotalAvoirClientPF.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelTotalAvoirClientPF.setBounds(786, 955, 227, 24);
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
						"ARTICLES PF (sources MP) ","Articles","Famille","Sous famille","Montant Initial","Montant Achat","Montant Service","Montant Vente","Montant Gratuit","Montant Avoir","Montant Avoir ClientPF","Montant Transfert MP PF","Montant Finale","Achat Revendu","Marge","%"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
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
			
			
			
			
		        Object []ligne={article, etatMargeStockPF.getMatierePremiere(), etatMargeStockPF.getFamille() , etatMargeStockPF.getMatierePremiere(),etatMargeStockPF.getMontantInitial(),etatMargeStockPF.getMontantAchat(),etatMargeStockPF.getMontantService(),etatMargeStockPF.getMontantvente(),etatMargeStockPF.getMontantGratuit(),etatMargeStockPF.getMontantAvoir(),etatMargeStockPF.getMontantAvoirClientPF(), etatMargeStockPF.getMontantTransfertMPPF(),etatMargeStockPF.getMontantFinale(),etatMargeStockPF.getAchatRevendu(),etatMargeStockPF.getMarge(),etatMargeStockPF.getPercentage()};

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



public void ConsulterEtatQuantiteMPTransformeParsousfamille()
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
	Magasin magasin=mapMagasin.get(comboMagasinMP.getSelectedItem());
	
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
		
		
		listDetailTransferStockMPInitial=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutInitial(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_INITIAL,magasin);
		listDetailTransferStockMPAchat=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_ACHAT,magasin);
		
		listDetailTransferStockMPAchatGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_ACHAT,magasin);
		listDetailTransferStockMPSortie=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE,magasin);
		listDetailTransferStockMPSortieGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE,magasin);
		
		listDetailTransferStockMPAvoir=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAvoir(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_AVOIR,magasin);
		listDetailTransferStockMPAvoirGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAvoir(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_AVOIR,magasin);
		
		listDetailTransferStockMPChargeSupp=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutChargeSupp(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin);
		listDetailTransferStockMPChargeSuppGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutChargeSupp(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin);
		
		listDetailTransferStockMPService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutService(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SERVICE,magasin);
		listDetailTransferStockMPServiceGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutService(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SERVICE,magasin);
		

		listDetailTransferStockMPVente=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_VENTE,magasin);
		listDetailTransferStockMPVenteGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_VENTE,magasin);
		
		listDetailTransferStockMPTransferMPPFProduction=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF,magasin);
		listDetailTransferStockMPTransferMPPFProductionGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF,magasin);
		
		listDetailTransferStockMPTransferMPPF=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasin);
		listDetailTransferStockMPTransferMPPFGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasin);
		
		
		
		listDetailTransferStockMPTransferMPPFService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE,magasin);
		listDetailTransferStockMPTransferMPPFGroupebyMPService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE,magasin);
		
		
		
		listDetailTransferStockMPAllMP=	detailTransferStockMPDAO.findAllTransferStockMPGroupeByMP(magasin);
		
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
//	// calculer calculer le prix moyen et quantite TransferMPPFSERVICE
//	
//	
//	for(int j=0;j<listDetailTransferStockMPTransferMPPFGroupebyMPService.size();j++)
//	{
//		montantTransferMPPF=new BigDecimal(0);
//		quantiteTotalTransferMPPF=new BigDecimal(0);
//		quantiteOffreaSupprimerService=new BigDecimal(0);
//		quantiteDechetaSupprimerService=new BigDecimal(0);
//		
//		
//	for(int k=0;k<listDetailTransferStockMPTransferMPPFService.size();k++)
//	{
//		
//		if(listDetailTransferStockMPTransferMPPFGroupebyMPService.get(j).getMatierePremier().equals(listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier()))
//		{
//			if(listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
//			{
//				montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING)));
//    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
//    			
//    			
//    			// calculer la somme de quantite dechet et offre a supprimer
//    			if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource()!=null)
//    			{
//    				if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
//    				{
//    					quantiteDechetaSupprimerService=quantiteDechetaSupprimerService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
//    				}else if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
//    				{
//    					quantiteOffreaSupprimerService=quantiteOffreaSupprimerService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING))	;
//    				}
//    				
//    			}
//    			////////////////////////////////////////////////////////////////////////////////// 
//    			
//    			
//			}else
//			{
//				montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite()));
//    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite());
//    			
//    			// calculer la somme de quantite dechet et offre a supprimer
//    			
//    			if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource()!=null)
//    			{
//    				
//    				if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
//    				{
//    					quantiteDechetaSupprimerService=quantiteDechetaSupprimerService.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite());
//    				}else if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
//    				{
//    					quantiteOffreaSupprimerService=quantiteOffreaSupprimerService.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite())	;
//    				}
//    				
//    			}
//    			
//                //////////////////////////////////////////////////////////////////////////////////
//    			
//			}
//	
//			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
//			
//		}
//		
//		
//	}
//		if(!montantTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !quantiteTotalTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
//		{
//			/*	    			
//			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
//			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
//			*/
//			
//			for(int i=0;i<listEtatStockMP.size();i++)
//	    	{
//				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPTransferMPPFGroupebyMPService.get(j).getMatierePremier()))
//    			{
//    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
//    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
//					{
//    					
//	    				    etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));
//
//							etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)) );
//		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
//		    				// supprimer la quantite dechet et quantite offre
//		    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING).subtract(quantiteDechetaSupprimerService.setScale(0, RoundingMode.CEILING)));
//		    				if(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
//		    				{
//		    					etatstockmp.setPrixDechetService(BigDecimal.ZERO);	
//		    				}
//		    				etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
//		    				
//		    				etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING).subtract(quantiteOffreaSupprimerService.setScale(0, RoundingMode.CEILING)));
//		    				if(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
//		    				{
//		    					etatstockmp.setPrixOffreService(BigDecimal.ZERO);
//		    				}
//		    				etatstockmp.setMontantOffreService(etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
//						
//    					
//    				
//	    				
//	    				listEtatStockMP.set(i, etatstockmp);
//    					
//					}else
//					{
//						
//							
//						
//	    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF),6,RoundingMode.HALF_UP));
//
//						   etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF));
//					
//		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
//		    				// supprimer la quantite dechet et quantite offre
//		    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().subtract(quantiteDechetaSupprimerService));
//		    				if(etatstockmp.getQuantiteDechetService().setScale(2, RoundingMode.UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.UP)))
//		    				{
//		    					etatstockmp.setPrixDechetService(BigDecimal.ZERO);	
//		    				}
//		    				etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
//		    				
//		    				etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().setScale(6, RoundingMode.HALF_UP).subtract(quantiteOffreaSupprimerService.setScale(6, RoundingMode.HALF_UP)));
//		    				if(etatstockmp.getQuantiteOffreService().setScale(2, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)))
//		    				{
//		    					etatstockmp.setPrixOffreService(BigDecimal.ZERO);
//		    				}
//		    				etatstockmp.setMontantOffreService(etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
//					
//	    				listEtatStockMP.set(i, etatstockmp);	
//					}
//    				
//    				
//    			}
//	    	}
//			
//		}
//		
//	}
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
		    					System.out.println(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficherMouvementTmp.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficherMouvementTmp.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficherMouvementTmp.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb() ) ;

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
		    					System.out.println(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficher.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficher.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficher.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficher.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficher.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficher.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficher.get(l).getPrixFinaldb() ) ;
		    					
	    					}
	    					
	    				}
	    			}
	    			
	    		}
    			
    			StockMP stockmp=stockMPDAO.findStockByMagasinMP(etatstockmp.getMp().getId(),magasin.getId());
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
		    					System.out.println(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficherMouvementTmp.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficherMouvementTmp.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficherMouvementTmp.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb() ) ;
		    					
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
		    					System.out.println(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficher.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficher.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficher.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficher.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficher.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficher.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficher.get(l).getPrixFinaldb() ) ;
		    				
	    					}
	    					
	    				}
	    			}
	    			
	    		}
    			
    			StockMP stockmp=stockMPDAO.findStockByMagasinMP(etatstockmp.getMp().getId(),magasin.getId());
    			
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
	
	boolean existe=false;
 	for(int j=0;j<listEtatStockMP.size();j++)
 		{
 		existe=false;
 		
 		EtatStockMP etatStockMP=listEtatStockMP.get(j);
 		
 		for(int t=0;t<listEtatStockMPParSousFamille.size();t++)
 		{
 			
 			EtatStockMP etatStockMPParSousFamille=listEtatStockMPParSousFamille.get(t);
 			
 		if(etatStockMP.getSousFamille().equals(etatStockMPParSousFamille.getSousFamille()))
 		{
 			existe=true;
 			
 			etatStockMPParSousFamille.setQuantiteSortie(etatStockMPParSousFamille.getQuantiteSortie().add(etatStockMP.getQuantiteSortie()));
 			etatStockMPParSousFamille.setQuantiteDechet(etatStockMPParSousFamille.getQuantiteDechet().add(etatStockMP.getQuantiteDechet()));
 			etatStockMPParSousFamille.setQuantiteOffreProduction(etatStockMPParSousFamille.getQuantiteOffreProduction().add(etatStockMP.getQuantiteOffreProduction()));
 			etatStockMPParSousFamille.setQuantiteService(etatStockMPParSousFamille.getQuantiteService().add(etatStockMP.getQuantiteService()));
 			etatStockMPParSousFamille.setQuantiteDechetService(etatStockMPParSousFamille.getQuantiteDechetService().add(etatStockMP.getQuantiteDechetService()));
 			etatStockMPParSousFamille.setQuantiteOffreService(etatStockMPParSousFamille.getQuantiteOffreService().add(etatStockMP.getQuantiteOffreService()));
 			etatStockMPParSousFamille.setQuantiteTransfertMPPF(etatStockMPParSousFamille.getQuantiteTransfertMPPF().add(etatStockMP.getQuantiteTransfertMPPF()));
 			
 			listEtatStockMPParSousFamille.set(t, etatStockMPParSousFamille);
 		
 		}
 			
 			
 			
 			
 			
 		}
 		
 		if(existe==false)
 		{
 			
 			listEtatStockMPParSousFamille.add(etatStockMP);
 			
 		}
 		
 		
 		
 		
 		
 		
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
	Magasin magasin=mapMagasin.get(comboMagasinMP.getSelectedItem());
	
	if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null)
	{
		JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP !!!");
			return;
	}else
	{
		if(magasin!=null)
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
	    		
	    		if(mp!=null)
	    		{
	    			titre="Etat Quantite MP Transforme Par sous Famille "+mp.getNom() +" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
	    		}else
	    		{
	    			titre="Etat Quantite MP Transforme Par sous Famille de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
	    		}
	    		
	    		}
	    		
	    		if(dateChooserdebut.getDate()==null )
	    		{
	    			dateChooserfin.setCalendar(null);
	    			titre="Etat Quantite MP Transforme Par sous Famille  magasin : "+magasin.getLibelle();
	    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
	    		{
	    			String d1=sdf.format(dateChooserdebut.getDate());
	    			titre="Etat Quantite MP Transforme Par sous Famille de "+mp.getNom() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
	    			
	    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
	    		{
	    			String d1=sdf.format(dateChooserdebut.getDate());
	    			titre="Etat Quantite MP Transforme Par sous Famille de magasin : "+magasin.getLibelle()+ "entre "+d1 +" et "+d1;
	    		}
	    		
	    		
	    		listDetailTransferStockMP=detailTransferStockMPDAO.findAllTransferStockMPOrderByDateTransfer(magasin);
	    		listDetailTransferStockMPGroupebyMP=detailTransferStockMPDAO.findAllTransferStockMPGroupeByDateTransferByMP(magasin);
	    		listDetailTransferStockMPBytypetransfer=detailTransferStockMPDAO.findBytypetransfer(ETAT_TRANSFER_STOCK_INITIAL,magasin);
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
	    				if(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getId()==94)
	    				{
	    					System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom());
	    				}
	    				
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
			    			System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : Prix Moyenne :"+ achat +" * "+prixAchat+ " + " +quantitefinale +" * "+prixmoyenne +" / "+ "("+quantitefinale +achat+" )" );

	    					prixmoyenne=(achat.multiply(prixAchat).add(quantitefinale.multiply(prixmoyenne))).divide(quantitefinale.add(achat), RoundingMode.HALF_UP);

		    			}else
		    			{
		    				prixmoyenne=BigDecimal.ZERO;

		    			}
	    				
	    				
	    				System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : quantitefinale :"+quantitefinale +" :achat "+achat +" vente: "+vente +" production :"+production + " avoir:" +avoir +" service: "+service +" transfert:"+transfert );

	    				quantitefinale=(quantitefinale.add(achat)).subtract(vente.add(production.add(avoir.add(service).add(transfert).add(offreService).add(dechetService).add(dechet).add(offreProduction))));
	    				
	    			}else
	    			{
	    				
	    				
	    				if(!initial.add(achat).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		    			{
			    			System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : Prix Moyenne :"+ achat +" * "+prixAchat+ " + " +initial +" * "+prixInitial +" / "+ "("+initial +" + "+achat+")" );

	    					prixmoyenne=(achat.multiply(prixAchat).add(initial.multiply(prixInitial))).divide(initial.add(achat), RoundingMode.HALF_UP);

		    			}else
		    			{
		    				prixmoyenne=BigDecimal.ZERO;

		    			}
	    				
	    				
	    				quantitefinale=(initial.add(achat)).subtract(vente.add(production.add(avoir.add(service).add(transfert).add(offreService).add(dechetService).add(dechet).add(offreProduction))));
		    			System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : quantitefinale :"+quantitefinale +" :achat "+achat +" vente: "+vente +" production :"+production + " avoir:" +avoir +" service: "+service +" transfert:"+transfert );

	    				
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
							 if(listDetailTransferProduitFini.get(i).getSousFamille().getId()==detailTransferProduitFini.getSousFamille().getId())
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
						
						
						
						if(listDetailTransferProduitFiniTmp.get(j).getSousFamille().getId()==sousfamilleArticle.getId())
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
					etatventeFamilleArticle.setPrixMoyen(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
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
				if(prixMoyenStockMP.getMp().getCode().equals("MP_37"))
				{
					
				System.out.println("yes");	
					
				}
				
				for(int j=0;j<listEtatprixMoyenServiceAfficher.size();j++)
				{
					
					PrixMoyenStockPF prixMoyenStockPF=listEtatprixMoyenServiceAfficher.get(j);
					
					
					if(prixMoyenStockMP.getSousfamille()!=null)
					{
						System.out.println(prixMoyenStockMP.getMp().getCode());	
        		    
						
						
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








////////////////////////////////////////////////////////////////////////////////// Etat Moyen Vente par Sous Famille ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void CalculerEtatPrixMoyenventePFparSousFamille()
{
		Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem().toString());
		
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
			listDetailFactureVentePFTmp=detailfacturedao.listeDetailFacturePFByDate(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasin);
		
			if(listDetailFactureVentePFTmp.size()!=0)
			{
				
				
				 for(int k=0;k<listDetailFactureVentePFTmp.size();k++)
				 {
					 DetailFacturePF detailfacture=listDetailFactureVentePFTmp.get(k);
					 trouve=false;
					
					 if(listDetailFactureVentePF.size()!=0)
					 {
						
						 for(int i=0;i<listDetailFactureVentePF.size();i++)
						 {
							 if(listDetailFactureVentePF.get(i).getSousFamille().getId()==detailfacture.getSousFamille().getId())
							 {
								 trouve=true;
							 }
							 
						 }
						 
					 }else
					 {
						 listDetailFactureVentePF.add(detailfacture);
						trouve=true;
					 }
					 
					 if(trouve==false)
					 {
						 listDetailFactureVentePF.add(detailfacture);
					 }
				 }
				
				
				
			boolean existe=false;
			boolean exist=false;
				
				if(listDetailFactureVentePF.size()!=0)
				{
					for(int i=0;i<listDetailFactureVentePF.size();i++)
					{
						SousFamilleArticlePF sousfamilleArticle =listDetailFactureVentePF.get(i).getSousFamille();
						quantite=BigDecimal.ZERO;
						quantitefoiprix=BigDecimal.ZERO;
						quantiteOffre=BigDecimal.ZERO;
						quantiteOffrefoiprix=BigDecimal.ZERO;
						
						existe=false;
						exist=false;
					for(int j=0;j<listDetailFactureVentePFTmp.size();j++)	
					{
						
						
						
						if(listDetailFactureVentePFTmp.get(j).getSousFamille().getId()== sousfamilleArticle.getId())
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
						
						System.out.println(etatventeFamilleArticle.getSousFamilleArticlePF() +" --- "+etatventeFamilleArticle.getPrixMoyen() + " --- "+etatventeFamilleArticle.getMontant());
						
						
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
	

/////////////////////////////////////////////////////////////////////////  Avoir   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void CalculerEtatPrixMoyenAvoirPFParFamille()
{
	
	listDetailFactureAvoirPF.clear();
	listDetailFactureAvoirPFTmp.clear();
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
			
			listEtatAvoir.clear();
			listDetailFactureAvoirPFTmp=detailfactureAvoirdao.listeDetailFactureAvoirByDate(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasin);
		
			if(listDetailFactureAvoirPFTmp.size()!=0)
			{
				
				
				 for(int k=0;k<listDetailFactureAvoirPFTmp.size();k++)
				 {
					 DetailFactureAvoirPF detailfacture=listDetailFactureAvoirPFTmp.get(k);
					 trouve=false;
					
					 if(listDetailFactureAvoirPF.size()!=0)
					 {
						
						 for(int i=0;i<listDetailFactureAvoirPF.size();i++)
						 {
							 if(listDetailFactureAvoirPF.get(i).getSousFamille().equals(detailfacture.getSousFamille()))
							 {
								 trouve=true;
							 }
							 
						 }
						 
					 }else
					 {
						 listDetailFactureAvoirPF.add(detailfacture);
						trouve=true;
					 }
					 
					 if(trouve==false)
					 {
						 listDetailFactureAvoirPF.add(detailfacture);
					 }
				 }
				
				
				
			boolean existe=false;
		
				
				if(listDetailFactureAvoirPF.size()!=0)
				{
					for(int i=0;i<listDetailFactureAvoirPF.size();i++)
					{
						SousFamilleArticlePF sousfamilleArticle =listDetailFactureAvoirPF.get(i).getSousFamille();
						quantite=BigDecimal.ZERO;
						quantitefoiprix=BigDecimal.ZERO;
						quantiteOffre=BigDecimal.ZERO;
						quantiteOffrefoiprix=BigDecimal.ZERO;
						
						existe=false;
						
					for(int j=0;j<listDetailFactureAvoirPFTmp.size();j++)	
					{
						
						
						
						if(listDetailFactureAvoirPFTmp.get(j).getSousFamille().equals(sousfamilleArticle))
						{
						  
						   		
						  	quantitefoiprix=quantitefoiprix.add(listDetailFactureAvoirPFTmp.get(j).getMontantHT());
							quantite=quantite.add(listDetailFactureAvoirPFTmp.get(j).getQuantite());
							existe=true;
						   	
						   	
							
							
							
						}
						
						
					}
					
		
					if(existe==true)
					{
						
					
						EtatVenteParFamilleArticle etatventeFamilleArticle=new EtatVenteParFamilleArticle();
						etatventeFamilleArticle.setSousFamilleArticlePF(sousfamilleArticle);
						etatventeFamilleArticle.setFamilleArticlePF(sousfamilleArticle.getFamileArticlePF());
						etatventeFamilleArticle.setTotalVente(quantite);
						etatventeFamilleArticle.setPrixMoyen(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
						etatventeFamilleArticle.setMontant(etatventeFamilleArticle.getPrixMoyen().multiply(etatventeFamilleArticle.getTotalVente()).setScale(6, RoundingMode.FLOOR));
						listEtatAvoir.add(etatventeFamilleArticle);
					}
					
					
				
					
						
					}
					
					
					
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
					listEtatAvoir.clear();
					
					return;
				}
				
			}
			
			
		}
				
			}
			

		
			
		
	
	
	
}

/////////////////////////////////////////////////////////////////////////  Avoir  Client PF ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public void CalculerEtatPrixMoyenAvoirClientPFParFamille()
{

listDetailFactureAvoirClientPF.clear();
listDetailFactureAvoirClientPFTmp.clear();
Magasin magasin=mapMagasin.get(comboMagasinPF.getSelectedItem());

if(magasin!=null)
{

BigDecimal quantite=BigDecimal.ZERO;
BigDecimal quantitefoiprix=BigDecimal.ZERO;
BigDecimal quantiteOffre=BigDecimal.ZERO;
BigDecimal quantiteOffrefoiprix=BigDecimal.ZERO;



boolean trouver=false;

String dateDebut=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
String dateFin=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
if(dateDebut.equals(""))	{
JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
} else if(dateFin.equals("")){
JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);


}else {

listEtatAvoirClientPF.clear();
listDetailFactureAvoirClientPFTmp=detailfactureAvoirClientPFdao.listeDetailFactureAvoirByDate(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasin);

if(listDetailFactureAvoirClientPFTmp.size()!=0)
{


for(int k=0;k<listDetailFactureAvoirClientPFTmp.size();k++)
{
DetailFactureAvoirClientPF detailfacture=listDetailFactureAvoirClientPFTmp.get(k);
trouver=false;

if(listDetailFactureAvoirClientPF.size()!=0)
{

for(int i=0;i<listDetailFactureAvoirClientPF.size();i++)
{
if(listDetailFactureAvoirClientPF.get(i).getSousFamille().getId() ==detailfacture.getSousFamille().getId())
{
	trouver=true;
}

}

}else
{
listDetailFactureAvoirClientPF.add(detailfacture);
trouver=true;
}

if(trouver==false)
{
listDetailFactureAvoirClientPF.add(detailfacture);
}
}



boolean existe=false;


if(listDetailFactureAvoirClientPF.size()!=0)
{
for(int i=0;i<listDetailFactureAvoirClientPF.size();i++)
{
SousFamilleArticlePF sousfamilleArticle =listDetailFactureAvoirClientPF.get(i).getSousFamille();
quantite=BigDecimal.ZERO;
quantitefoiprix=BigDecimal.ZERO;
quantiteOffre=BigDecimal.ZERO;
quantiteOffrefoiprix=BigDecimal.ZERO;

existe=false;

for(int j=0;j<listDetailFactureAvoirClientPFTmp.size();j++)	
{



if(listDetailFactureAvoirClientPFTmp.get(j).getSousFamille().getId()==sousfamilleArticle.getId())
{


quantitefoiprix=quantitefoiprix.add(listDetailFactureAvoirClientPFTmp.get(j).getMontantHT());
quantite=quantite.add(listDetailFactureAvoirClientPFTmp.get(j).getQuantite());
existe=true;





}


}


if(existe==true)
{


EtatVenteParFamilleArticle etatventeFamilleArticle=new EtatVenteParFamilleArticle();
etatventeFamilleArticle.setSousFamilleArticlePF(sousfamilleArticle);
etatventeFamilleArticle.setFamilleArticlePF(sousfamilleArticle.getFamileArticlePF());
etatventeFamilleArticle.setTotalVente(quantite);
etatventeFamilleArticle.setPrixMoyen(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
etatventeFamilleArticle.setMontant(etatventeFamilleArticle.getPrixMoyen().multiply(etatventeFamilleArticle.getTotalVente()).setScale(6, RoundingMode.FLOOR));
listEtatAvoirClientPF.add(etatventeFamilleArticle);

}





}




}else
{
JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
listEtatAvoir.clear();

return;
}

}


}

}








}
	}


