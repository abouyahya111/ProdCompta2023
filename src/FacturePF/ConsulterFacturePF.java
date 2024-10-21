package FacturePF;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.PrintException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailHistoriqueVenteVendeurDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.EnTeteDAOImpl;
import dao.daoImplManager.FactureAutresVenteDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceTransportDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.HistoriqueVenteVendeurDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoImplManager.TypeBLDAOImpl;
import dao.daoImplManager.TypeVenteDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailHistoriqueVenteVendeurDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.EnTeteDAO;
import dao.daoManager.FactureAutresVenteDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceTransportDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.HistoriqueVenteVendeurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.daoManager.TypeBLDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.Articles;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.CompteClient;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFacturePFCumule;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailHistoriqueVenteVendeur;
import dao.entity.DetailPrixArticle;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EnTete;
import dao.entity.FactureAchat;
import dao.entity.FactureAutresVente;
import dao.entity.FacturePF;
import dao.entity.FactureServiceTransport;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.HistoriqueVentevendeur;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Sequenceur;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockPF;
import dao.entity.TypeBL;
import dao.entity.TypeVente;
import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;



import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.JToggleButton;
import javax.swing.JCheckBox;


public class ConsulterFacturePF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;

	private JXTable  tableArticle = new JXTable();
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<ClientPF> listParClientPF =new ArrayList<ClientPF>();
	private List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
	private List<DetailFacturePFCumule> listDetailFacturePFCumule =new ArrayList<DetailFacturePFCumule>();
	private List<DetailFacturePF> listDetailFacturePFTransfererVersHistorique =new ArrayList<DetailFacturePF>();
	private List<DetailFacturePF> listDetailFacturePFTMP =new ArrayList<DetailFacturePF>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Articles> listArticleTmp =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<StockPF> listArticleStockPF =new ArrayList<StockPF>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
	private List<FacturePF> listFacturePFTmp =new ArrayList<FacturePF>();
	private List<FacturePF> listFacturePFMAJ =new ArrayList<FacturePF>();
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	private Map< String, SousFamilleArticlePF> mapsousfamilleOffre= new HashMap<>();
	private Map< String, Articles> mapArticleOffre = new HashMap<>();
	private Map< String, Articles> mapArticleOffreCode = new HashMap<>();
	private List<Articles> listArticleOffre =new ArrayList<Articles>();
	private List<SousFamilleArticlePF> listSousFamilleArticleOffre =new ArrayList<SousFamilleArticlePF>();
	private static SequenceurDAO sequenceurDAO=ProdLauncher.sequenceurDAO;
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Articles> mapArticlePromo = new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, ClientPF> mapClientPF= new HashMap<>();
	private Map< String, ClientPF> mapParClientPF= new HashMap<>();
	private Map< String, ClientPF> mapClientPFparCode= new HashMap<>();
	private Map< String, Client> mapFournisseur= new HashMap<>();
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	private Map< String, Boolean> maptransfereblfacture = new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgSelectAll;
	private ImageIcon imgDeselectAll;
	private ImageIcon imgImprimer;
	private JCheckBox checkttc = new JCheckBox("TTC");
	private  JButton btninitialiser = new JButton();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private  JComboBox combofamille ;
	private   JComboBox comboparclient = new JComboBox();
	 private JComboBox comboArticle;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FacturePFDAO facturepfdao;
	private FacturePF facturePF;
	private DetailTransferProduitFiniDAO detailTransferStockPFdao;
private DetailFacturePFDAO detailFacturePfdao;
private ClientPFDAO clientpfdao;
private ClientDAO fournisseurdao;
private FamilleArticlesPFDAO familleArticleDAO;
private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
private DetailPrixArticleDAO detailPrixArticleDAO;
TransferStockPF transferStock ;
private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
private TransferStockPFDAO transferStockPFDAO;
private List <Object[]> listeObject=new ArrayList<Object[]>();
	private JTextField txtcodearticle;
	ChargeProduction chargeproduction;
	private JTextField txtquantite;
	private JTextField txtnumbl;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	private ParametreDAO parametreDAO;
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	 private JDateChooser dateChooserfacture;
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private JTextField txtPrix;
	private JTextField txtmontant;
	private JTextField txttotalmontantTTC;
	private JTextField txttotalquantite;
	private  JButton btnModifier ;
	 private JButton btnSupprimer = new JButton();
	 private  JComboBox comboFournisseur = new JComboBox();
	private   JComboBox comboClientpf ;
	private JTextField txtparnumBL;
	private JRadioButton rdbtnDateFacture;
	private JDateChooser pardateChooser;
	private  JComboBox combopardepot;
	private JTextField txttotalmontantTVA;
	private JTextField txttotalmontantHT;
	private StockPFDAO stockpfDAO;
	private CompteClientDAO compteclientdao;
	private JTextField txtreduction;
	 private JComboBox combosousfamille ;
	 private  JComboBox comboparsousfamille ;
	private JComboBox comboparfamille;
	JCheckBox checkboxGratuits = new JCheckBox("Gratuit");
	private JTextField txtquantitepaquet;
	 JLabel labelPrixMin = new JLabel("");
	 JLabel labelPrixMax = new JLabel("");
	  JLabel stockDisponible = new JLabel("");
	  JLabel labelMarge = new JLabel("");
	  JButton supprimer_facture = new JButton();
	  JLabel lblPrix = new JLabel("Prix       :");
	  JLabel lblMontant = new JLabel("Montant  :");
	  JLabel lblRemise = new JLabel("Remise :");
	  JLabel labelpourcentage = new JLabel("%");
	  BigDecimal prixTTC=BigDecimal.ZERO;
	  JDateChooser datefacture = new JDateChooser();
	  JDateChooser datedebut = new JDateChooser();
	  BigDecimal StockFinale=BigDecimal.ZERO;
	  BigDecimal StockFinaleAnne=BigDecimal.ZERO;
	  private JTextField txtquantitetherres;
	  private JTextField txtquantiteverres;
	  BigDecimal stockfinaleTherres=BigDecimal.ZERO;
	  BigDecimal stockfinaleVerres=BigDecimal.ZERO;
	  BigDecimal stockfinaleArticlePromo=BigDecimal.ZERO;
	  JLabel lblOffreTherre = new JLabel("Offre Therre :");
	  JComboBox comboBoxtherres = new JComboBox();
	  JLabel lblOffreVerres = new JLabel("Offre Verres :");
	  JComboBox comboBoxverres = new JComboBox();
	  JLabel stockdisponibleoffretherres = new JLabel("");
	  JLabel stockdisponibleoffreverres = new JLabel("");
	  private JTextField txtquantitepromo;
	  JLabel lblOffrePromo = new JLabel("Offre Promo :");
	  JComboBox comboBoxPromo = new JComboBox();
	  JLabel stockdisponiblearticlepromo = new JLabel("");
	  private List<TypeBL> listTypeBL =new ArrayList<TypeBL>();
	  private Map< String, TypeBL> mapTypeBL= new HashMap<>();
	  private List<FacturePF> listFacturePFOrderByDate =new ArrayList<FacturePF>();
	  private List<FacturePF> listFacturePFOrderByNumFacture =new ArrayList<FacturePF>();
	  private JTextField txtlien;
	  JCheckBox checkboxSansTva = new JCheckBox("Sans TVA");
	  
	  private JFrame mainFrame;
	  private HistoriqueVenteVendeurDAO historiqueVenteVendeurDAO;
	private DetailHistoriqueVenteVendeurDAO detailHistoriqueVenteVendeurDAO;
	private List<DetailHistoriqueVenteVendeur> listDetailFactureHistorique =new ArrayList<DetailHistoriqueVenteVendeur>();
	
	private TypeBLDAO typeBLDAO;
	
/////////////////////////////////////////////////////////////// Liste Des Bl Non Facture ////////////////////////////////////////////////////////////////////////////////////////////////	  
	  
	  private List<FacturePF> listFacturePFBLNonFacturer =new ArrayList<FacturePF>();  
	  private List<FactureAutresVente> listFactureAutresVenteBLNonFacturer =new ArrayList<FactureAutresVente>(); 
	  private List<FactureServiceTransport> listFactureServiceTransportBLNonFacturer =new ArrayList<FactureServiceTransport>();
	  private List<Production> listProductionServiceLancerBLNonFacturer =new ArrayList<Production>();
	  FactureAutresVenteDAO factureAutresVenteDAO;
	  FactureServiceTransportDAO factureServiceTransportDAO;
	  ProductionDAO productionDAO;
	  
	  String MessageBlNonFacturer="";
	  private Map< Integer, FacturePF> mapImprimer = new HashMap<>();
	  
	  private List<ClientPF> listClientFacture =new ArrayList<ClientPF>();
	  private JComboBox comboParTypeBL= new JComboBox();
	  JComboBox comboTypeBL = new JComboBox();
	  JCheckBox chckbxPiece = new JCheckBox("Piece");
	  JComboBox comboEnTete = new JComboBox();
	  JLabel lblEnTete = new JLabel("En Tete :");
	  private  List<EnTete>listEntet=new ArrayList<EnTete>();
		 EnTeteDAO enTeteDAO;
		 private Map< String, EnTete> mapEntete= new HashMap<>();
		 JCheckBox checkEnTete = new JCheckBox("Avec En Tete");
		 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  
	  
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterFacturePF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));
         
		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1895, 1062);
      
        try{ 
        	
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
             imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
             detailTransferStockPFdao=new DetailTransferProduitFiniDAOImpl();
             transferStockPFDAO=new TransferStockPFDAOImpl();
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	facturepfdao=new FacturePFDAOImpl();
         	detailFacturePfdao=new DetailFacturePFDAOImpl();
         	clientpfdao=new ClientPFDAOImpl();
         	articleDAO=new ArticlesDAOImpl();
         	stockpfDAO=new StockPFDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         	compteclientdao=new CompteClientDAOImpl();
         	fournisseurdao=new ClientDAOImpl();
         	listArticle=articleDAO.findAll();
         	listClientPFParCode=clientpfdao.findAll();
        	familleArticleDAO=new FamilleArticlesPFDAOImpl();
         	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
         	listFamilleArticle=familleArticleDAO.findAll();
        	detailPrixArticleDAO=new DetailPrixArticleDAOImpl();
        	parametreDAO=new ParametreDAOImpl();
        	factureAutresVenteDAO=new FactureAutresVenteDAOImpl();
        	factureServiceTransportDAO=new FactureServiceTransportDAOImpl();
        	productionDAO=new ProductionDAOImpl();
        	historiqueVenteVendeurDAO=new HistoriqueVenteVendeurDAOImpl();
        	detailHistoriqueVenteVendeurDAO=new DetailHistoriqueVenteVendeurDAOImpl();
        	typeBLDAO=new TypeBLDAOImpl();
        	listTypeBL=typeBLDAO.findAll();
        	enTeteDAO=new EnTeteDAOImpl();
         	for(int i=0;i<listClientPFParCode.size();i++)
         	{
         		ClientPF clientpf=listClientPFParCode.get(i);
         		mapClientPFparCode.put(clientpf.getCode(), clientpf);
         	}
         	
          } catch (Exception exp){exp.printStackTrace();}
        tableArticle.setSortable(false);
        tableArticle.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		
       		if(tableArticle.getSelectedRow()!=-1)
       		{
       			
       			
       			Articles articleOffre=mapArticle.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
           		if(articleOffre!=null)
           		{
            		if(!articleOffre.getCodeArticle().equals(Constantes.OFFRE_THERRES) && !articleOffre.getCodeArticle().equals(Constantes.OFFRE_VERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().contains("_PFO"))
               		{
            			
            			

                   		if(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()).setScale(6,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,RoundingMode.HALF_UP)) && new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 6).toString()).setScale(6,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,RoundingMode.HALF_UP)))
                   		{
                   			boolean trouve=false;
                   			checkboxGratuits.setSelected(true);
                   			lblPrix.setVisible(false);
        	    			lblMontant.setVisible(false);
        	    			lblRemise.setVisible(false);
        	    			labelpourcentage.setVisible(false);
        	    			txtPrix.setVisible(false);
        	    			txtreduction.setVisible(false);
        	    			txtmontant.setVisible(false);
        	    			checkttc.setVisible(false);	
                   			
                   			
                   			
                   			
                   			combofamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0));
                       		combosousfamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
                       		comboArticle.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
                       		Articles article=mapArticle.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
                       		checkttc.setSelected(false);
                       		
                       		
                       		if(article.getCentreCout2()!=null)
                       		{
                       			if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
                       			{
                       			trouve=true;
                       			}
                       		}
                       		
                       		
                       		if(trouve==true)
                       		{
                       			if(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).compareTo(article.getConditionnement())<0)
                       			{
                       				txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());
                       			}else
                       			{
                       				txtquantitepaquet.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).divide(article.getConditionnement(),6,RoundingMode.HALF_UP)+"");
                       				String[] part=txtquantitepaquet.getText().split("\\.");
                       				txtquantitepaquet.setText(part[0]);
                       				txtquantite.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).subtract(new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement()))+"");
                       			}
                       		}else{
                       			txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());	
                       		}
                       		
                       		
                       		btnAjouter.setEnabled(false);
                   			
                   		}else
                   		{
                   			
                   			boolean offre1=false , offre2=false;
                   			
                   			boolean trouve=false;
                   			Articles article=mapArticle.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
                   			checkboxGratuits.setSelected(false);
                   			lblPrix.setVisible(true);
        	    			lblMontant.setVisible(true);
        	    			lblRemise.setVisible(true);
        	    			labelpourcentage.setVisible(true);
        	    			txtPrix.setVisible(true);
        	    			txtreduction.setVisible(true);
        	    			txtmontant.setVisible(true);
        	    			checkttc.setVisible(true);	
                   			
                   			
                   			combofamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0));
                       		combosousfamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
                       		comboArticle.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
                       		txtPrix.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString());
                       		checkttc.setSelected(false);
                       		
                       	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) 
                 		 {
                       		  
                       		if(!new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()).setScale(6,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,RoundingMode.HALF_UP)) && new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 7).toString()).setScale(6,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,RoundingMode.HALF_UP)))
                       		{
                       			checkboxSansTva.setSelected(true);
                       		}else
                       		{
                       			checkboxSansTva.setSelected(false);
                       		}
                 	    	
                 		 }
                       		
                       		
                       		
                       	
                       		
                      		
                           	// Traitement article Promo///////////////////////////
            			 			
            			 			if(article.getCentreCout5()!=null)
            			 			{
            			 			// centrecout5 = article promotion ou non
            			 				if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)))
            			 				{
            			 					if(tableArticle.getRowCount()-1>=tableArticle.getSelectedRow()+1)
            			 							{
            			 						
            			 						Articles articlesTherres=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow()+1, 2).toString());
            			 						Articles articlesVerres=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow()+1, 2).toString());
            			 						
            			 						if(articlesTherres!=null)
            			 						{
            			 							
            			 						if(articlesTherres.getCodeArticle().equals(Constantes.OFFRE_THERRES))
            			 						{
            			 							offre1=true;
            			 							txtquantitetherres.setText(tableArticle.getValueAt(tableArticle.getSelectedRow()+1, 4).toString());	
            			 						}
            			 						
            			 						
            			 						
            			 							
            			 						}
            			 						
            			 						if(articlesVerres!=null)
            			 						{
            			 							
            			 						if(articlesVerres.getCodeArticle().equals(Constantes.OFFRE_VERRES))
            			 						{
            			 							offre1=true;
            			 							txtquantiteverres.setText(tableArticle.getValueAt(tableArticle.getSelectedRow()+1, 4).toString());	
            			 						}
            			 						
            			 						}
            			 						
            			 						
            			 						
            			 						Articles articlesPromo=listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle();
            			 						if(articlesPromo.getCodeArticle().contains("_PFO") && !articlesPromo.getCodeArticle().equals(Constantes.OFFRE_VERRES) && !articlesPromo.getCodeArticle().equals(Constantes.OFFRE_THERRES))
            			 						{
            			 							offre1=true;
            			 							txtquantitepromo.setText(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite().toString());
            			 						}
            			 						        			 							
            			 						}
            			 					if(offre1==true)
            			 					{
            			 						
            			 						if(tableArticle.getRowCount()-1>=tableArticle.getSelectedRow()+2)
        			 							{
        			 						
        			 						Articles articlesTherres=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow()+2, 2).toString());
        			 						Articles articlesVerres=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow()+2, 2).toString());
        			 						
        			 						if(articlesTherres!=null)
        			 						{
        			 							
        			 						if(articlesTherres.getCodeArticle().equals(Constantes.OFFRE_THERRES))
        			 						{
        			 							offre2=true;
        			 							txtquantitetherres.setText(tableArticle.getValueAt(tableArticle.getSelectedRow()+2, 4).toString());	
        			 						}
        			 						
        			 						
        			 						
        			 							
        			 						}
        			 						
        			 						if(articlesVerres!=null)
        			 						{
        			 							
        			 						if(articlesVerres.getCodeArticle().equals(Constantes.OFFRE_VERRES))
        			 						{
        			 							offre2=true;
        			 							txtquantiteverres.setText(tableArticle.getValueAt(tableArticle.getSelectedRow()+2, 4).toString());	
        			 						}
        			 						
        			 						}
        			 						
        			 						
        			 						Articles articlesPromo=listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle();
        			 						if(articlesPromo.getCodeArticle().contains("_PFO") && !articlesPromo.getCodeArticle().equals(Constantes.OFFRE_VERRES) && !articlesPromo.getCodeArticle().equals(Constantes.OFFRE_THERRES))
        			 						{
        			 							offre2=true;
        			 							txtquantitepromo.setText(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite().toString());
        			 						}
        			 						
        			 						
        			 						
        			 						
        			 						
        			 							}
            			 					}
            			 					
            			 				
            			 					
            			 					
            			 					if(offre1==true && offre2==true)
            			 					{
            			 						
            			 						
            			 						if(tableArticle.getRowCount()-1>=tableArticle.getSelectedRow()+3)
        			 							{
        			 						
        			 						Articles articlesTherres=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow()+3, 2).toString());
        			 						Articles articlesVerres=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow()+3, 2).toString());
        			 						
        			 						if(articlesTherres!=null)
        			 						{
        			 							
        			 						if(articlesTherres.getCodeArticle().equals(Constantes.OFFRE_THERRES))
        			 						{
        			 							txtquantitetherres.setText(tableArticle.getValueAt(tableArticle.getSelectedRow()+3, 4).toString());	
        			 						}
        			 						
        			 						}
        			 						
        			 						if(articlesVerres!=null)
        			 						{
        			 							
        			 						if(articlesVerres.getCodeArticle().equals(Constantes.OFFRE_VERRES))
        			 						{
        			 							txtquantiteverres.setText(tableArticle.getValueAt(tableArticle.getSelectedRow()+3, 4).toString());	
        			 						}
        			 						
        			 						}
        			 						
        			 						
        			 						Articles articlesPromo=listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle();
        			 						if(articlesPromo.getCodeArticle().contains("_PFO") && !articlesPromo.getCodeArticle().equals(Constantes.OFFRE_VERRES) && !articlesPromo.getCodeArticle().equals(Constantes.OFFRE_THERRES))
        			 						{
        			 							txtquantitepromo.setText(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite().toString());
        			 						}
        			 						
        			 						}
            			 						
            			 						
            			 					}
            			 					
            			 					
            			 		
            			 					
            			 					
            			 				}else
            			 				{
            			 					txtquantitetherres.setText("");	
            		 						txtquantiteverres.setText("");	
            		 						txtquantitepromo.setText("");
            			 				}
            			 				
            			 			}else
            			 			{
            			 				
            			 				txtquantitetherres.setText("");	
            	 						txtquantiteverres.setText("");	
            	 						txtquantitepromo.setText("");
            			 			}
                       		
                       	
                       		if(article.getCentreCout2()!=null)
                       		{
                       			if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
                       			{
                       				trouve=true;	
                       			}
                       	
                       		}
                       		
                       		if(trouve==true)
                       		{
                       			if(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).compareTo(article.getConditionnement())<0)
                       			{
                       				txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());
                       			}else
                       			{
                       				txtquantitepaquet.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).divide(article.getConditionnement(),6,RoundingMode.HALF_UP)+"");
                       				String[] part=txtquantitepaquet.getText().split("\\.");
                       				txtquantitepaquet.setText(part[0]);
                       				txtquantite.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).subtract(new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement()))+"");
                       			}
                       		}else
                       		{
                       			txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());	
                       		}
                       		if(trouve==true)
                       		{
                       			txtreduction.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString());
                           		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
                           		txtmontant.setText((new BigDecimal(txtquantite.getText()).multiply(new BigDecimal(txtPrix.getText()))).add(new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement()).multiply(new BigDecimal(txtPrix.getText())))+"");
                           		
                       		}else
                       		{	

                           		txtreduction.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString());
                           		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
                           		txtmontant.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 6).toString());
                           		
                       		}
                       		btnAjouter.setEnabled(false);
                   		}
                   		
                   		if(!txtquantite.getText().equals(""))
                   		{
                   			if(new BigDecimal(txtquantite.getText()).equals(BigDecimal.ZERO))
                       		{
                       			txtquantite.setText("");
                       		}
                   		}
                   		
                   		if(!txtquantitepaquet.getText().equals(""))
                   		{
                   			if(new BigDecimal(txtquantitepaquet.getText()).equals(BigDecimal.ZERO))
                       		{
                       			txtquantitepaquet.setText("");
                       		}
                   		}
                   	
            			
            			
               		}
       			
       			
           		}
       			

           		
       		}
       			
       		
       		
       		}
       });
        
       tableArticle.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite", "Reduction", "Montant HT", "Montant TVA", "Montant TTC"
       	}
       ));
				  		
       tableArticle.setShowVerticalLines(false);
       tableArticle.setSelectionBackground(new Color(51, 204, 255));
       tableArticle.setRowHeightEnabled(true);
       tableArticle.setBackground(new Color(255, 255, 255));
       tableArticle.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableArticle.setColumnControlVisible(true);
       tableArticle.setForeground(Color.BLACK);
       tableArticle.setGridColor(new Color(0, 0, 255));
       tableArticle.setAutoCreateRowSorter(true);
       tableArticle.setBounds(2, 27, 411, 198);
       tableArticle.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableArticle);
				  		     	scrollPane.setBounds(10, 677, 1375, 228);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 649, 1458, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 499, 1375, 122);
				  		     	add(layeredPane);
		
		  JLabel lblCodeArticle = new JLabel("Code Article :");
		  lblCodeArticle.setBounds(531, 11, 82, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel lbllibelle = new JLabel("Libelle :");
		  lbllibelle.setBounds(704, 11, 48, 26);
		  layeredPane.add(lbllibelle);
		  lbllibelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		      
		      txtcodearticle = new JTextField();
		     
		      util.Utils.copycoller(txtcodearticle);
		      txtcodearticle.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {
	     			if(e.getKeyCode()==e.VK_ENTER)
			      		{
	     				
	     					
			      			if(!txtcodearticle.getText().equals(""))
			      			{
			      			
			      				Articles article=mapCodeArticle.get(txtcodearticle.getText());
					    		
					    		if(article!=null)
					    		{	
					    			comboArticle.setSelectedItem(article.getLiblle());
					    			
					    		}else
					    		{
					    			 JOptionPane.showMessageDialog(null, "Code Article Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
					    		
					    			
					    		}
			      				
			      				
			      		}else
			      		{
			      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code Article SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			      			
			      			
			      		}
	     				
	     				
			      		
	     				
	     				
			      		}
		     			
		     			
		     			
		     			
		     			
		     		}
		      });
		      
		      
		      
		      txtcodearticle.setColumns(10);
		      txtcodearticle.setBounds(599, 11, 95, 26);
		      layeredPane.add(txtcodearticle);
		    
		   
		       comboArticle = new JComboBox();
		       comboArticle.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {

		       		stockpfDAO.viderSessionStock();
  		     	 	if(comboArticle.getSelectedIndex()!=-1)
  			 		{
  			 			if(!comboArticle.getSelectedItem().equals(""))
  				 		{
  			 				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
  				 			Articles article=mapArticle.get(comboArticle.getSelectedItem());
  				 			ClientPF clientPF =mapClientPF.get(comboClientpf.getSelectedItem());
  				 			BigDecimal PrixMin=BigDecimal.ZERO;
  				 			BigDecimal PrixMax=BigDecimal.ZERO;
  				 			SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
  				 		 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasin.getId(),sousfamille.getId());
  				 			txtcodearticle.setText(article.getCodeArticle());
  				 			SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
  				 			String date="01/01/"+sdf.format(dateChooserfacture.getDate())+"";
  				 			String dateFin="31/12/"+sdf.format(dateChooserfacture.getDate())+"";
  				 			
  				 			Date dateDebut= new Date(date);
  				 			Date dateFinAnne= new Date(dateFin);
  				 		    StockFinale=BigDecimal.ZERO;
  				 		 StockFinaleAnne=BigDecimal.ZERO;
  				 		 
				 			List<Object[]> listestockfinale=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateChooserfacture.getDate(), magasin, article, sousfamille);
				 			List<Object[]> listestockfinaleAnne=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateFinAnne, magasin, article, sousfamille);
	  				 		
				 			for(int i=0;i<listestockfinale.size();i++)
				 			{
				 				
				 			 Object[] object=listestockfinale.get(i);
							
				 			 if((int) object[0]==article.getId() && (int)object[1]==sousfamille.getId())
				 			 {
				 				StockFinale=(BigDecimal)object[2];
				 			 }
				 			 	
				 			}
  				 			
				 			for(int i=0;i<listestockfinaleAnne.size();i++)
				 			{
				 				
				 			 Object[] object=listestockfinaleAnne.get(i);
							
				 			 if((int) object[0]==article.getId() && (int)object[1]==sousfamille.getId())
				 			 {
				 				StockFinaleAnne=(BigDecimal)object[2];
				 			 }
				 			 	
				 			}
				 			
				 			
				 			
				 			
				 			
                            // Traitement article Promo///////////////////////////
  				 			
  				 			if(article.getCentreCout5()!=null)
  				 			{


  	  				 			// centrecout5 = article promotion ou non
  	  				 				if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && checkboxGratuits.isSelected()==false  && !sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM)  )
  	  				 				{
  	  				 					
  	  				 				txtquantitepromo.setText("");
  				 					txtquantitetherres.setText("");
  				 					txtquantiteverres.setText("");
  	  				 					
  	  				 					comboBoxPromo.removeAllItems();
  	  				 					mapArticlePromo.clear();
  	  				 					Articles articlepromo=articleDAO.findByCode(article.getCodeArticle()+"_PFO");
  	  				 					if(articlepromo!=null)
  	  				 					{
  	  				 					
  	  				 					 StockPF stockpfPromo=stockpfDAO.findStockByMagasinPFBySousFamille(articlepromo.getId(), magasin.getId(),sousfamille.getId());	
  	  				 					 if(stockpfPromo!=null)
  	  				 					 {
  	  				 						comboBoxPromo.addItem(articlepromo.getLiblle());
  	  	  				 					mapArticlePromo.put(articlepromo.getLiblle(), articlepromo);
  	  	  				 				List<Object[]> listestockfinaleArticlePromo=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateChooserfacture.getDate(), magasin, articlepromo, sousfamille);
  	  	  				 					
  	  	  				 			for(int i=0;i<listestockfinaleArticlePromo.size();i++)
  	  	  				 			{
  	  	  				 				
  	  	  				 			 Object[] object=listestockfinaleArticlePromo.get(i);
  	  	  							
  	  	  				 			 if((int) object[0]==articlepromo.getId() && (int)object[1]==sousfamille.getId())
  	  	  				 			 {
  	  	  				 				stockfinaleArticlePromo=(BigDecimal)object[2];
  	  	  				 			 }
  	  	  				 			 	
  	  	  				 			}
  	  	  				 					
  	  				 					 }
  	  				 					 
  	  				 					 
  	  				 					 
  	  				 					stockdisponiblearticlepromo.setText("Stock Offre Disponible de "+articlepromo.getLiblle()+" est : "+stockfinaleArticlePromo.setScale(6, RoundingMode.HALF_UP) );	
  	  				 						
  	  				 					lblOffrePromo.setVisible(true);
  	  				 					comboBoxPromo.setVisible(true);
  	  				 					txtquantitepromo.setVisible(true);	
  	  				 					
  	  				 					
  	  				 					
  	  				 					}
  	  				 					
  	  				 					
  	  				 					Articles articlesTherresOffre=mapArticleOffreCode.get(Constantes.OFFRE_THERRES);
  	  				 					Articles articlesVerresOffre=mapArticleOffreCode.get(Constantes.OFFRE_VERRES);
  	  				 					SousFamilleArticlePF sousFamilleArticlePFOffreTherres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_THERRES);
  	  				 					SousFamilleArticlePF sousFamilleArticlePFOffreVerres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_VERRES);
  	  				 					 stockfinaleTherres=BigDecimal.ZERO;
  	  				 					 stockfinaleVerres=BigDecimal.ZERO;
  	  				 					
  	  				 					lblOffreTherre.setVisible(true);
  	  				 					comboBoxtherres.setVisible(true);
  	  				 					txtquantitetherres.setVisible(true);
  	  				 					lblOffreVerres.setVisible(true);
  	  				 					comboBoxverres.setVisible(true);
  	  				 					txtquantiteverres.setVisible(true);
  	  				 					
  	  		  				 			List<Object[]> listestockfinaleTherres=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateChooserfacture.getDate(), magasin, articlesTherresOffre, sousFamilleArticlePFOffreTherres);

  	  		  				 			List<Object[]> listestockfinaleverres=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateChooserfacture.getDate(), magasin, articlesVerresOffre, sousFamilleArticlePFOffreVerres);

  	  		  				 		for(int i=0;i<listestockfinaleTherres.size();i++)
  	  	  				 			{
  	  	  				 				
  	  	  				 			 Object[] object=listestockfinaleTherres.get(i);
  	  	  							
  	  	  				 			 if((int) object[0]==articlesTherresOffre.getId() && (int)object[1]==sousFamilleArticlePFOffreTherres.getId())
  	  	  				 			 {
  	  	  				 				stockfinaleTherres=(BigDecimal)object[2];
  	  	  				 			 }
  	  	  				 			 	
  	  	  				 			}
  	  				 					
  	  		  					for(int i=0;i<listestockfinaleverres.size();i++)
  	  				 			{
  	  				 				
  	  				 			 Object[] object=listestockfinaleverres.get(i);
  	  							
  	  				 			 if((int) object[0]==articlesVerresOffre.getId() && (int)object[1]==sousFamilleArticlePFOffreVerres.getId())
  	  				 			 {
  	  				 				stockfinaleVerres=(BigDecimal)object[2];
  	  				 			 }
  	  				 			 	
  	  				 			}	
  	  				 			
  	  				 					
  	  				 			stockdisponibleoffretherres.setText("Stock Offre Disponible de "+articlesTherresOffre.getLiblle()+" est : "+stockfinaleTherres.setScale(6, RoundingMode.HALF_UP) );		
  	  				 			stockdisponibleoffreverres.setText( "Stock Offre Disponible de "+articlesVerresOffre.getLiblle()+" est : "+stockfinaleVerres.setScale(6, RoundingMode.HALF_UP));		
  	  				 				
  	  				 				
  	  				 				
  	  				 				
  	  				 				}else if (article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && checkboxGratuits.isSelected()==false  &&  magasin.getCode().equals(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING_PF) )
  	  				 				{

  	  				 					
  	  	  				 				txtquantitepromo.setText("");
  	  				 					txtquantitetherres.setText("");
  	  				 					txtquantiteverres.setText("");
  	  	  				 					
  	  	  				 					comboBoxPromo.removeAllItems();
  	  	  				 					mapArticlePromo.clear();
  	  	  				 					Articles articlepromo=articleDAO.findByCode(article.getCodeArticle()+"_PFO");
  	  	  				 					if(articlepromo!=null)
  	  	  				 					{
  	  	  				 					
  	  	  				 					 StockPF stockpfPromo=stockpfDAO.findStockByMagasinPFBySousFamille(articlepromo.getId(), magasin.getId(),sousfamille.getId());	
  	  	  				 					 if(stockpfPromo!=null)
  	  	  				 					 {
  	  	  				 						comboBoxPromo.addItem(articlepromo.getLiblle());
  	  	  	  				 					mapArticlePromo.put(articlepromo.getLiblle(), articlepromo);
  	  	  	  				 				List<Object[]> listestockfinaleArticlePromo=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateChooserfacture.getDate(), magasin, articlepromo, sousfamille);
  	  	  	  				 					
  	  	  	  				 			for(int i=0;i<listestockfinaleArticlePromo.size();i++)
  	  	  	  				 			{
  	  	  	  				 				
  	  	  	  				 			 Object[] object=listestockfinaleArticlePromo.get(i);
  	  	  	  							
  	  	  	  				 			 if((int) object[0]==articlepromo.getId() && (int)object[1]==sousfamille.getId())
  	  	  	  				 			 {
  	  	  	  				 				stockfinaleArticlePromo=(BigDecimal)object[2];
  	  	  	  				 			 }
  	  	  	  				 			 	
  	  	  	  				 			}
  	  	  	  				 					
  	  	  				 					 }
  	  	  				 					 
  	  	  				 					 
  	  	  				 					 
  	  	  				 					stockdisponiblearticlepromo.setText("Stock Offre Disponible de "+articlepromo.getLiblle()+" est : "+stockfinaleArticlePromo.setScale(6, RoundingMode.HALF_UP) );	
  	  	  				 						
  	  	  				 					lblOffrePromo.setVisible(true);
  	  	  				 					comboBoxPromo.setVisible(true);
  	  	  				 					txtquantitepromo.setVisible(true);	
  	  	  				 					
  	  	  				 					
  	  	  				 					
  	  	  				 					}
  	  	  				 					
  	  	  				 					
  	  	  				 					Articles articlesTherresOffre=mapArticleOffreCode.get(Constantes.OFFRE_THERRES);
  	  	  				 					Articles articlesVerresOffre=mapArticleOffreCode.get(Constantes.OFFRE_VERRES);
  	  	  				 					SousFamilleArticlePF sousFamilleArticlePFOffreTherres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_THERRES);
  	  	  				 					SousFamilleArticlePF sousFamilleArticlePFOffreVerres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_VERRES);
  	  	  				 					 stockfinaleTherres=BigDecimal.ZERO;
  	  	  				 					 stockfinaleVerres=BigDecimal.ZERO;
  	  	  				 					
  	  	  				 					lblOffreTherre.setVisible(true);
  	  	  				 					comboBoxtherres.setVisible(true);
  	  	  				 					txtquantitetherres.setVisible(true);
  	  	  				 					lblOffreVerres.setVisible(true);
  	  	  				 					comboBoxverres.setVisible(true);
  	  	  				 					txtquantiteverres.setVisible(true);
  	  	  				 					
  	  	  		  				 			List<Object[]> listestockfinaleTherres=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateChooserfacture.getDate(), magasin, articlesTherresOffre, sousFamilleArticlePFOffreTherres);

  	  	  		  				 			List<Object[]> listestockfinaleverres=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateChooserfacture.getDate(), magasin, articlesVerresOffre, sousFamilleArticlePFOffreVerres);

  	  	  		  				 		for(int i=0;i<listestockfinaleTherres.size();i++)
  	  	  	  				 			{
  	  	  	  				 				
  	  	  	  				 			 Object[] object=listestockfinaleTherres.get(i);
  	  	  	  							
  	  	  	  				 			 if((int) object[0]==articlesTherresOffre.getId() && (int)object[1]==sousFamilleArticlePFOffreTherres.getId())
  	  	  	  				 			 {
  	  	  	  				 				stockfinaleTherres=(BigDecimal)object[2];
  	  	  	  				 			 }
  	  	  	  				 			 	
  	  	  	  				 			}
  	  	  				 					
  	  	  		  					for(int i=0;i<listestockfinaleverres.size();i++)
  	  	  				 			{
  	  	  				 				
  	  	  				 			 Object[] object=listestockfinaleverres.get(i);
  	  	  							
  	  	  				 			 if((int) object[0]==articlesVerresOffre.getId() && (int)object[1]==sousFamilleArticlePFOffreVerres.getId())
  	  	  				 			 {
  	  	  				 				stockfinaleVerres=(BigDecimal)object[2];
  	  	  				 			 }
  	  	  				 			 	
  	  	  				 			}	
  	  	  				 			
  	  	  				 					
  	  	  				 			stockdisponibleoffretherres.setText("Stock Offre Disponible de "+articlesTherresOffre.getLiblle()+" est : "+stockfinaleTherres.setScale(6, RoundingMode.HALF_UP) );		
  	  	  				 			stockdisponibleoffreverres.setText( "Stock Offre Disponible de "+articlesVerresOffre.getLiblle()+" est : "+stockfinaleVerres.setScale(6, RoundingMode.HALF_UP));		
  	  	  				 				
  	  	  				 				
  	  	  				 				
  	  	  				 				
  	  	  				 				
  	  				 				}
  	  				 				else
  	  				 				{
  	  				 					lblOffreTherre.setVisible(false);
  	  				 					comboBoxtherres.setVisible(false);
  	  				 					txtquantitetherres.setVisible(false);
  	  				 					lblOffreVerres.setVisible(false);
  	  				 					comboBoxverres.setVisible(false);
  	  				 					txtquantiteverres.setVisible(false);
  	  				 					txtquantitetherres.setText("");
  	  				 					txtquantiteverres.setText("");
  	  				 					lblOffrePromo.setVisible(false);
  					 					comboBoxPromo.setVisible(false);
  					 					txtquantitepromo.setVisible(false);	
  					 					txtquantitepromo.setText("");
  	  				 					stockdisponibleoffretherres.setText("");		
  	  		  				 			stockdisponibleoffreverres.setText("");		
  	  		  				 		stockdisponiblearticlepromo.setText("");
  	  		  				 	txtquantitepromo.setText("");
			 					txtquantitetherres.setText("");
			 					txtquantiteverres.setText("");
  	  		  				 	
  	  				 				}
  	  				 				
  	  				 				
  	  				 			
  				 			
  				 			
  				 			}
  				 			
  				 			
  				 			/////////////////////////////////
  				 			
  				 			
				 			
				 			
  				 			
  				 			if(article!=null)
  				 			{
  				 				boolean trouve=false;
				 					
				 						if(article.getCentreCout2()!=null)
				 						{
				 							if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
				 							{
				 							trouve=true;
				 							}
				 						}
				 					
				 					if(trouve==true)
				 					{
				 						txtquantitepaquet.setText("");
				 						txtquantitepaquet.setEnabled(true);
				 					}else
				 					{
				 						txtquantitepaquet.setText("");
				 						txtquantitepaquet.setEnabled(false);
				 					}
				 					
  				 			}
  				 			
  				 			DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , clientPF.getId());
	  				 			
		  				 		
		  				 		if(detailprixarticle!=null)
		  				 			{
		  				 				txtPrix.setText(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP)+"");
		  				 			}else
		  				 			{
		  				 				
	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId());

		  				 			if(detailprixarticleTmp!=null)	
		  				 			{
		  				 				
		  				 			txtPrix.setText(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP)+"");
		  				 				
		  				 			}else
		  				 			{
		  				 				
		  				 			txtPrix.setText(BigDecimal.ZERO+"");
		  				 			
		  				 			}
		  				 				
		  				 				
		  				 				
		  				 				
		  				 			}
  				 			
  				 			txtmontant.setText("");
  				 			
  				 			if(stockpf!=null)
	  				 			{
  				 				
  				 				
  				 				if(checkboxGratuits.isSelected()==true)
  				 				{
  				 				 if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)))
					        		 {
  				 					 if(stockpf.getStockOffre()!=null)
  				 					 {
  				 						PrixMin=stockpf.getPrixUnitaire().divide(new BigDecimal(0.2).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
  	  			  				 		PrixMax=stockpf.getPrixUnitaire().divide(new BigDecimal(0.3).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
  	  			  				 		labelPrixMin.setText("Prix Minimum :"+" "+PrixMin);
  	  			  				 	labelPrixMax.setText("Prix Maximum :"+" "+PrixMax);
  	  			  				 		
  	  			  				 	stockDisponible.setText("Stock Offre Disponible de "+article.getLiblle()+" est : "+stockpf.getStockOffre().setScale(6, RoundingMode.HALF_UP));
  	  				 					  
  				 					 }else
  				 					 {
  			  				 			stockDisponible.setText( "Stock Offre  de "+ article.getLiblle()+" indisponible "+magasin.getLibelle());
 
  				 					 }
  				 					
  				 					 
					        		 }else
					        		 {
					        			    PrixMin=stockpf.getPrixUnitaire().divide(new BigDecimal(0.2).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
		  			  				 		PrixMax=stockpf.getPrixUnitaire().divide(new BigDecimal(0.3).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
		  			  				 		labelPrixMin.setText("Prix Minimum :"+" "+PrixMin);
		  			  				 	    labelPrixMax.setText("Prix Maximum :"+" "+PrixMax);
		  			  				 		
		  			  				 	stockDisponible.setText("Stock Disponible de "+article.getLiblle()+" à ce jour est : "+StockFinale.setScale(6, RoundingMode.HALF_UP) +" ******* Stock Finale est : "+StockFinaleAnne.setScale(6, RoundingMode.HALF_UP));
		  			  			
					        		 }
  				 					
  				 					
  				 					
  				 				}else
  				 				{
  				 					PrixMin=stockpf.getPrixUnitaire().divide(new BigDecimal(0.2).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
  			  				 		PrixMax=stockpf.getPrixUnitaire().divide(new BigDecimal(0.3).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
  			  				 		labelPrixMin.setText("Prix Minimum :"+" "+PrixMin);
  			  				 	labelPrixMax.setText("Prix Maximum :"+" "+PrixMax);
  			  				 		
  			  				 	stockDisponible.setText("Stock Disponible de "+article.getLiblle()+" à ce jour est : "+StockFinale.setScale(6, RoundingMode.HALF_UP)+" ******* Stock Finale est : "+StockFinaleAnne.setScale(6, RoundingMode.HALF_UP));
  				 				}
  				 				
	  				 			
	  				 		
	  				 		 
	  				 			}else
	  				 			{
	  				 			stockDisponible.setText(article.getLiblle()+" n'existe pas au magasin "+magasin.getLibelle());
	  				 			}
	  				 			
  				 		  				 			
  				 		}else
  				 		{
  				 			txtcodearticle.setText("");
  				 			
  				 		}
  				 	
  			 		}
  			 		
  			 		
  		     	 	
		       	}
		       });
		      comboArticle.setBounds(751, 11, 242, 27);
		      layeredPane.add(comboArticle);
		      AutoCompleteDecorator.decorate(comboArticle);
		     
		     	
		      
		      JLabel lblQuantit = new JLabel("Quantite U :");
		      lblQuantit.setBounds(1003, 11, 72, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      util.Utils.copycoller(txtquantite);
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {
               		

               		if(e.getKeyCode()==e.VK_ENTER)
	{
               			
               				try {
                   				Articles article=mapArticle.get(comboArticle.getSelectedItem());
                   				if(article!=null)
                   				{
                   	 				if(!txtPrix.getText().equals(""))
                       				{
                       					if(!txtquantite.getText().equals(""))
                       					{
                       						if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
                       						{
                       							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
      					{
                       								
                       								if(!txtquantitepaquet.getText().equals(""))
        		                   					{
        		                   						if(new BigDecimal(txtquantitepaquet.getText()).compareTo(BigDecimal.ZERO)>0)
        		                   						{
        		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
    		      					{
        		                   								txtmontant.setText((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement())))+"");					
        		                   								
    		      					}
        		                   						}}else
        		                   						{
        		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))+"");
        		                   						}
      							
      					}
                       							
                       						}
                       						
                       					}
                       					
                       					
                       						      					
                       				}
                       				
                   				}
                   				
                   				
                   				
                   				if(article.getCentreCout5()!=null)
      				 			{
      				 			// centrecout5 = article promotion ou non
      				 				if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && checkboxGratuits.isSelected()==false )
      				 				{
      				 					
      				 					BigDecimal quantitetherres=BigDecimal.ZERO;
      				 					if(article.getCentreCout3()!=null)
      				 					{
      				 						quantitetherres=new BigDecimal(txtquantite.getText()).divide(article.getCentreCout3(), 6, RoundingMode.HALF_UP) ; // quantitetherre= quantitearticle / le poid de carton 
      				 						
      				 						quantitetherres=quantitetherres.subtract(quantitetherres.remainder(BigDecimal.ONE));
      				 					
      				 					txtquantitetherres.setText(quantitetherres.toString());
      				 					
      				 					txtquantiteverres.setText(quantitetherres.multiply(new BigDecimal(2)).toString());
      				 					
      				 					if(mapArticlePromo.get(comboBoxPromo.getSelectedItem())!=null)
      				 					{
      				 						
      				 						txtquantitepromo.setText(quantitetherres.divide(article.getConditionnement(), 6, RoundingMode.HALF_UP).toString());
      				 						
      				 					}
      				 					
      				 					
      				 					}
      				 					
      				 				}
                   				
                   				
      				 			}	
                   				
                   						
    	
    } catch (NumberFormatException e2) {JOptionPane.showMessageDialog(null, "Veuillez entrer les chiffres pour la quantité et le montant SVP !!!!!");
    	// TODO: handle exception
    }
      
               			
               			
               			             			
               			
               			
	}
               		
               		
               	

               		
               	}
		      });
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(1067, 11, 112, 26);
		      layeredPane.add(txtquantite);
		      
		       lblPrix = new JLabel("Prix       :");
		      lblPrix.setBounds(8, 47, 69, 26);
		      layeredPane.add(lblPrix);
		      
		      txtPrix = new JTextField();
		      txtPrix.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {
            		

               		if(e.getKeyCode()==e.VK_ENTER)
	{
               			

                   			try {
                   				Articles article=mapArticle.get(comboArticle.getSelectedItem());
                   				if(article!=null)
                   				{
                   	 				if(!txtPrix.getText().equals(""))
                       				{
                       					if(!txtquantite.getText().equals(""))
                       					{
                       						if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
                       						{
                       							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
      					{
                       								
                       								if(!txtquantitepaquet.getText().equals(""))
        		                   					{
        		                   						if(new BigDecimal(txtquantitepaquet.getText()).compareTo(BigDecimal.ZERO)>0)
        		                   						{
        		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
    		      					{
        		                   								txtmontant.setText((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement())))+"");					
        		                   								
    		      					}
        		                   						}}else
        		                   						{
        		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))+"");
        		                   						}
      							
      						
      						
      					}
                       							
                       						}
                       						
                       					}
                       					
                       					
                       						      					
                       				}
                   	 				
                   	 				
                   	 			if(!txtPrix.getText().equals(""))
                   				{
                   					if(!txtquantitepaquet.getText().equals(""))
                   					{
                   						if(new BigDecimal(txtquantitepaquet.getText()).compareTo(BigDecimal.ZERO)>0)
                   						{
                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
    					{
                   								
                   								if(!txtquantite.getText().equals(""))
    		                   					{
    		                   						if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
    		                   						{
    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
    	      					{
    		                   								txtmontant.setText((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement())))+"");					
    		                   								
    	      					}
    		                   						}
    		                   						}else
    		                   						{
    		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement()))+"");
    		                   						}
    							
    					}
                   							
                   						}
                   						
                   					}
                   					
                   					
                   						      					
                   				}
                       				
                   				}
                   				
                  
                   				
    	
    } catch (NumberFormatException e2) {JOptionPane.showMessageDialog(null, "Veuillez entrer les chiffres pour la quantité et le montant SVP !!!!!");
    	// TODO: handle exception
    }
               			
               			
               			
               			
	}	
            	}
		      });
		      txtPrix.setColumns(10);
		      txtPrix.setBounds(87, 47, 170, 26);
		      layeredPane.add(txtPrix);
		      
		    
		      
		       lblMontant = new JLabel("Montant  :");
		      lblMontant.setBounds(279, 47, 67, 26);
		      layeredPane.add(lblMontant);
		      
		      txtmontant = new JTextField();
		      txtmontant.setEditable(false);
		      txtmontant.setColumns(10);
		      txtmontant.setBounds(361, 47, 160, 26);
		      layeredPane.add(txtmontant);
		      
		       lblRemise = new JLabel("Remise :");
		      lblRemise.setBounds(541, 47, 57, 26);
		      layeredPane.add(lblRemise);
		      
		      txtreduction = new JTextField();
		      txtreduction.setColumns(10);
		      txtreduction.setBounds(610, 48, 101, 26);
		      layeredPane.add(txtreduction);
		      
		       labelpourcentage = new JLabel("%");
		      labelpourcentage.setFont(new Font("Tahoma", Font.BOLD, 11));
		      labelpourcentage.setBounds(721, 47, 26, 26);
		      layeredPane.add(labelpourcentage);
		      
		      JLabel label_7 = new JLabel("Famille Article :");
		      label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		      label_7.setBounds(8, 11, 87, 24);
		      layeredPane.add(label_7);
		      
		       combofamille = new JComboBox();
		       combofamille.addItemListener(new ItemListener() {
		       	public void itemStateChanged(ItemEvent e) {
	        		
	        		 if(e.getStateChange() == ItemEvent.SELECTED)
	     	 		 {
	        			 if(!combofamille.getSelectedItem().equals(""))
	        			 {
	        				 
	        					FamilleArticlePF famille=mapfamille.get(combofamille.getSelectedItem());
	    		        		if(famille!=null)
	    		        		{
	    		        			combosousfamille.removeAllItems();
	    		        			 combosousfamille.addItem("");
	    		        			listSousFamilleArticle=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePF(famille.getId());
	    		        			for(int i=0;i<listSousFamilleArticle.size();i++)
	    		        			{
	    		        			SousFamilleArticlePF sousfamille=listSousFamilleArticle.get(i);
	    		        			combosousfamille.addItem(sousfamille.getLiblle());
	    		        			mapsousfamille.put(sousfamille.getLiblle(), sousfamille);
	    		        				
	    		        			}
	    		        			
	    		        		}else
	    		        		{
	    		        			 combosousfamille.removeAllItems();
	    		        		}
	    		        			
	        				 
	        			 }else
	        			 {
	        				//combosousfamille.removeAllItems(); 
	        			 }
	        			 
	        			
	        	 
	        			
	        			 
	     	 		 }
	     	 			 
	        		
	        		
	        	}
		       });
		      combofamille.setSelectedIndex(-1);
		      combofamille.setBounds(90, 12, 167, 24);
		      layeredPane.add(combofamille);
		      
		      int p=0;
		      combofamille.addItem("");
		      while(p<listFamilleArticle.size())
		      {
		    	  
		    	  FamilleArticlePF famillearticle=listFamilleArticle.get(p);
		    	  combofamille.addItem(famillearticle.getLiblle());
		    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
		    	  p++;
		      }
		      
		      
		      
		      
		      JLabel label_9 = new JLabel("Sous Famille :");
		      label_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		      label_9.setBounds(279, 11, 87, 24);
		      layeredPane.add(label_9);
		      
		       combosousfamille = new JComboBox();
		      combosousfamille.addItemListener(new ItemListener() {
		      	public void itemStateChanged(ItemEvent e) {
	        		

	        		 if(e.getStateChange() == ItemEvent.SELECTED)
	     	 		 {
	        			 if(!combosousfamille.getSelectedItem().equals(""))
	        			 {
	        				
	        				 SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
	        				 Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	        				 if(magasin!=null)
	        				 {
	        					 
	        					 if(sousfamille!=null)
			        			 {
			        				 comboArticle.removeAllItems();
			        				 comboArticle.addItem("");
			        				 listArticleStockPF=stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
			        				 for(int i=0;i<listArticleStockPF.size();i++)
			        				 {
			        					 
			        					 
			        					Articles article= listArticleStockPF.get(i).getArticles();
			        					
			        					if(sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
			        					{
			        						
			        					/*	if(!article.getLiblle().contains("(OFFRE)"))
			        						{*/
			        							
			        							comboArticle.addItem(article.getLiblle());
					        					 mapArticle.put(article.getLiblle(), article);
					        					 mapCodeArticle.put(article.getCodeArticle(), article);
			        							
			        						/*}*/
			        						
			        						
			        					}else
			        					{

			        						comboArticle.addItem(article.getLiblle());
				        					 mapArticle.put(article.getLiblle(), article);
				        					 mapCodeArticle.put(article.getCodeArticle(), article);
			        						
			        					}
			        					
			        				
			        					 
			        				 }
			        				 
			        				 
			        				 
			        			 }else
			        			 {
			        				 comboArticle.removeAllItems();
			        				 txtcodearticle.setText("");
			        			 } 
	        				 }
		        	
	        				 
	        			 }else
	        			 {
	        				 comboArticle.removeAllItems();
	        				 txtcodearticle.setText("");
	        			 }
	        			 
	        		
	        			 
	     	 		 }
	        		
	        		
	        		
	        	}
		      });
		      combosousfamille.setSelectedIndex(-1);
		      combosousfamille.setBounds(361, 12, 160, 24);
		      layeredPane.add(combosousfamille);
		     
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal solde=BigDecimal.ZERO;
			 if(dateChooserfacture.getDate()==null)
				{

					JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
				}else if(combodepot.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
				}else if(combomagasin.getSelectedIndex()==-1 || combomagasin.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
				}else if(comboClientpf.getSelectedIndex()==-1 || comboClientpf.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le Client SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
					
				}else if(comboFournisseur.getSelectedIndex()==-1)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez choisir le Fournisseur SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
				}
				
				
				else if(listDetailFacturePF.size()==0)
				{
					 

					
					
					
					FacturePF facturePFTmp=listFacturePF.get(table.getSelectedRow());
					if(facturePFTmp!=null)
					{
						
						Depot depot=mapDepot.get(combodepot.getSelectedItem());
						Magasin magasin =mapMagasin.get(combomagasin.getSelectedItem());
						ClientPF clientPF =mapClientPF.get(comboClientpf.getSelectedItem());
						Client fournisseur=mapFournisseur.get(comboFournisseur.getSelectedItem());
						TypeBL typeBL=mapTypeBL.get(comboTypeBL.getSelectedItem());
						
						facturePFTmp.setModifierPar(utilisateur);
						//facturePFTmp.setNumBl(txtnumbl.getText());
						facturePFTmp.setClientPF(clientPF);
						facturePFTmp.setFournisseur(fournisseur);
						facturePFTmp.setDepot(depot);
						facturePFTmp.setMagasin(magasin);
						
						if(typeBL!=null)
						{
							facturePFTmp.setTypeBL(typeBL);
							
						}else
						{
							facturePFTmp.setTypeBL(null);
						}
						
						facturePFTmp.setImprimerPiece(chckbxPiece.isSelected());
						
						
						facturePFTmp.setDateFacture(dateChooserfacture.getDate());
						if(facturePFTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
						{
							facturePFTmp.setDateBl(dateChooserfacture.getDate());
						}
						facturePFTmp.setEtat(Constantes.ETAT_NON_REGLE);
						facturePFTmp.setType(Constantes.TYPE_BON_LIVRAISON);
						facturePFTmp.setMontantHT(BigDecimal.ZERO);
						facturePFTmp.setMontantTVA(BigDecimal.ZERO);
						facturePFTmp.setMontantTTC(BigDecimal.ZERO);
						facturePFTmp.setCheque(BigDecimal.ZERO);
						facturePFTmp.setCredit(BigDecimal.ZERO);
						facturePFTmp.setEspece(BigDecimal.ZERO);
						facturePFTmp.setModeReglement("");
						facturePFTmp.setNumCheque("");
						facturePFTmp.setNumtraite("");
						facturePFTmp.setNumVersement("");
						facturePFTmp.setTraite(BigDecimal.ZERO);
						facturePFTmp.setVersement(BigDecimal.ZERO);
						facturePFTmp.setVirement(BigDecimal.ZERO);					  
						facturePFTmp.setDateModifier(new Date());	
						facturePFTmp.setDetailFacturePF(listDetailFacturePF);
					    facturepfdao.edit(facturePFTmp);
					    
					    ////////////////////////////////// ajouter detail compte client par client facture ////////////////////////   
					    FacturePF facturePF=listFacturePF.get(table.getSelectedRow());
					    if(facturePF!=null)
					    {
					    	DetailCompteClient detailcompteclient=detailCompteClientdao.findByFacture(facturePF.getId());
						    detailcompteclient.getCompteClient().setSolde(detailcompteclient.getCompteClient().getSolde().subtract(detailcompteclient.getMontantDebit()));
							compteclientdao.edit(detailcompteclient.getCompteClient());
							detailcompteclient.setCompteClient(clientPF.getCompteClient());
							detailcompteclient.setUtilisateurMAJ(utilisateur);
							detailcompteclient.setDateMaj(new Date());
							detailcompteclient.setFournisseur(fournisseur);
							detailcompteclient.setMontantDebit(BigDecimal.ZERO);
							detailcompteclient.setDateOperation(dateChooserfacture.getDate());
							if(facturePF.getNumFacture()!=null)
							{
								detailcompteclient.setDesignation("Montant sur Facture N "+facturePF.getNumFacture());
							}else
							{
								detailcompteclient.setDesignation("Montant sur Facture N "+facturePF.getNumBl());
							}
							
							detailcompteclient.setFacturepf(facturePF);	
						    detailCompteClientdao.edit(detailcompteclient);
						    solde=clientPF.getCompteClient().getSolde().add(BigDecimal.ZERO);
						    clientPF.getCompteClient().setSolde(solde);
						    compteclientdao.edit(clientPF.getCompteClient());
					    }
					
					     
					    
					//// Reglement de Stock 
						 Date dateDebut;
						try {
							detailTransferStockPFdao.ViderSession();
							dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse("2019"+"-01-01");
							 listeObject=detailTransferStockPFdao.listeStockFinalePF(dateDebut, new Date(), magasin);
							 
									for(int t=0;t<listeObject.size();t++)
									{	
										 Object[] object=listeObject.get(t);
										
									System.out.println("Article : "+ (int) object[0] +"  Magasin : "+ magasin.getId()+"  Sous Famille : "+ (int)object[1]);
										StockPF stockPF=stockpfDAO.findStockByMagasinPFBySousFamille((int) object[0], magasin.getId(), (int)object[1]);
										if(stockPF!=null)
										{
											stockPF.setStock((BigDecimal)object[2]);
											stockpfDAO.edit(stockPF);	
										}
											
										
									}
								 
							
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						 
					    
					    
					    
					    JOptionPane.showMessageDialog(null, "Facture Modifier avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					    initialiserFacture();
					    initialiser();
					    listDetailTransferProduitFini=detailTransferStockPFdao.findAll();
					  
					    listDetailFacturePF.clear();
					    listDetailTransferProduitFini.clear();
						InitialiseTableau();
						chargerListeFacture();
						
						
					}
			
				
					
					
					
					
					
				}else
				{
					
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				Magasin magasin =mapMagasin.get(combomagasin.getSelectedItem());
				ClientPF clientPF =mapClientPF.get(comboClientpf.getSelectedItem());
				Client fournisseur=mapFournisseur.get(comboFournisseur.getSelectedItem());
				TypeBL typeBL=mapTypeBL.get(comboTypeBL.getSelectedItem());
				
				
				 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier La facture  ?", 
							"Satisfaction", JOptionPane.YES_NO_OPTION);
					 
					if(reponse == JOptionPane.YES_OPTION )
						
					{
						
						
						
						FacturePF facturePFTmp=listFacturePF.get(table.getSelectedRow());
						facturePFTmp.setModifierPar(utilisateur);
						//facturePFTmp.setNumBl(txtnumbl.getText());
						facturePFTmp.setClientPF(clientPF);
						facturePFTmp.setFournisseur(fournisseur);
						facturePFTmp.setDepot(depot);
						facturePFTmp.setMagasin(magasin);
						
						if(typeBL!=null)
						{
							facturePFTmp.setTypeBL(typeBL);
							
						}else
						{
							facturePFTmp.setTypeBL(null);
						}
						
						facturePFTmp.setImprimerPiece(chckbxPiece.isSelected());
						
						
						facturePFTmp.setDateFacture(dateChooserfacture.getDate());
						if(facturePFTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
						{
							facturePFTmp.setDateBl(dateChooserfacture.getDate());
						}
						facturePFTmp.setEtat(Constantes.ETAT_NON_REGLE);
						facturePFTmp.setType(Constantes.TYPE_BON_LIVRAISON);
						facturePFTmp.setMontantHT(new BigDecimal(txttotalmontantHT.getText()).setScale(6, RoundingMode.HALF_UP));
						facturePFTmp.setMontantTVA(new BigDecimal(txttotalmontantTVA.getText()).setScale(6, RoundingMode.HALF_UP));
						facturePFTmp.setMontantTTC((new BigDecimal(txttotalmontantTTC.getText())).setScale(6, RoundingMode.HALF_UP));	
						facturePFTmp.setDateModifier(new Date());	
						facturePFTmp.setDetailFacturePF(listDetailFacturePF);
						facturePFTmp.setCheque(BigDecimal.ZERO);
						facturePFTmp.setCredit(BigDecimal.ZERO);
						facturePFTmp.setEspece(BigDecimal.ZERO);
						facturePFTmp.setModeReglement("");
						facturePFTmp.setNumCheque("");
						facturePFTmp.setNumtraite("");
						facturePFTmp.setNumVersement("");
						facturePFTmp.setTraite(BigDecimal.ZERO);
						facturePFTmp.setVersement(BigDecimal.ZERO);
						facturePFTmp.setVirement(BigDecimal.ZERO);
						 
					    facturepfdao.edit(facturePFTmp);
					    
					    ////////////////////////////////// ajouter detail compte client par client facture ////////////////////////   
					    FacturePF facturePF=listFacturePF.get(table.getSelectedRow());
						DetailCompteClient detailcompteclient=detailCompteClientdao.findByFacture(facturePF.getId());
					    detailcompteclient.getCompteClient().setSolde(detailcompteclient.getCompteClient().getSolde().subtract(detailcompteclient.getMontantDebit()));
						compteclientdao.edit(detailcompteclient.getCompteClient());
						detailcompteclient.setCompteClient(clientPF.getCompteClient());
						detailcompteclient.setUtilisateurMAJ(utilisateur);
						detailcompteclient.setDateMaj(new Date());
						detailcompteclient.setFournisseur(fournisseur);
						detailcompteclient.setMontantDebit(new BigDecimal(txttotalmontantTTC.getText()));
						detailcompteclient.setDateOperation(dateChooserfacture.getDate());
						if(facturePF.getNumFacture()!=null)
						{
							detailcompteclient.setDesignation("Montant sur Facture N "+facturePF.getNumFacture());
						}else
						{
							detailcompteclient.setDesignation("Montant sur Facture N "+facturePF.getNumBl());
						}
						
						detailcompteclient.setFacturepf(facturePF);	
					    detailCompteClientdao.edit(detailcompteclient);
					    solde=clientPF.getCompteClient().getSolde().add(new BigDecimal(txttotalmontantTTC.getText()));
					    clientPF.getCompteClient().setSolde(solde);
					    compteclientdao.edit(clientPF.getCompteClient());
					    
					    // update transfer stock produit fini 
					    
					   for(int i=0;i<listDetailTransferProduitFini.size();i++)
					    {
					    	 DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(i);
					    	 detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
					    	 detailTransferStockPFdao.edit(detailTransferStockPF);
					    	 
					    }
					 
					    transferStock.setModifierPar(utilisateur);
					    transferStock.setDateTransfer(dateChooserfacture.getDate());
					    transferStock.setDateModifier(new Date());
					  // transferStock.setListDetailTransferProduitFini(listDetailTransferProduitFini);
					    transferStockPFDAO.edit(transferStock);
					    
						
						/*
						 * int i=0; while(i<listStockPF.size()) { StockPF
						 * stockPF=stockpfDAO.findById(listStockPF.get(i).getId());
						 * stockPF.setStockOffre(listStockPF.get(i).getStockOffre());
						 * stockpfDAO.edit(stockPF); i++; }
						 */
					    
					//// Reglement de Stock 
						 Date dateDebut;
						try {
							detailTransferStockPFdao.ViderSession();
							dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse("2019"+"-01-01");
							 listeObject=detailTransferStockPFdao.listeStockFinalePF(dateDebut, new Date(), magasin);
							 
									for(int t=0;t<listeObject.size();t++)
									{	
										 Object[] object=listeObject.get(t);
										
									System.out.println("Article : "+ (int) object[0] +"  Magasin : "+ magasin.getId()+"  Sous Famille : "+ (int)object[1]);
										StockPF stockPF=stockpfDAO.findStockByMagasinPFBySousFamille((int) object[0], magasin.getId(), (int)object[1]);
										if(stockPF!=null)
										{
											stockPF.setStock((BigDecimal)object[2]);
											stockpfDAO.edit(stockPF);	
										}
											
										
									}
								 
							
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						 
					    
					    
					    
					    JOptionPane.showMessageDialog(null, "Facture Modifier avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					    initialiserFacture();
					    initialiser();
					    listDetailTransferProduitFini=detailTransferStockPFdao.findAll();
					  
					    listDetailFacturePF.clear();
					    listDetailTransferProduitFini.clear();
						InitialiseTableau();
						chargerListeFacture();
							
					}
				
			
				}
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(779, 916, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 470, 1375, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 408, 1661, 51);
		add(layeredPane_1);
		
		JLabel label = new JLabel("Code  :");
		label.setBounds(241, 13, 89, 24);
		layeredPane_1.add(label);
		
		txtnumbl = new JTextField();
		txtnumbl.setEditable(false);
		txtnumbl.setColumns(10);
		txtnumbl.setBounds(288, 12, 129, 26);
		layeredPane_1.add(txtnumbl);
	
		JLabel label_1 = new JLabel("Date  :");
		label_1.setBounds(427, 13, 62, 24);
		layeredPane_1.add(label_1);
		
		 dateChooserfacture = new JDateChooser();
		dateChooserfacture.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {}
		});
		dateChooserfacture.setLocale(Locale.FRANCE);
		dateChooserfacture.setDateFormatString("dd/MM/yyyy");
		dateChooserfacture.setBounds(467, 11, 139, 26);
		layeredPane_1.add(dateChooserfacture);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(616, 13, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(664, 13, 144, 24);
		  layeredPane_1.add(combodepot);
		  combodepot.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		

 	 			
   	 		 if(e.getStateChange() == ItemEvent.SELECTED)
   	 		 {
   	 			int i=0;
   	 		
   	 				if(!combodepot.getSelectedItem().equals(""))
    			{
    				Depot depot=mapDepot.get(combodepot.getSelectedItem());
    				if(depot!=null)
    				{
    					
    					
    					
    						listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_PF);
		     				if(listMagasin.size()!=0)
		     				{
		     					combomagasin.removeAllItems();
		     					combomagasin.addItem("");
		     					while(i<listMagasin.size())
 		     				{
 		     					Magasin magasin=listMagasin.get(i);
 		     					combomagasin.addItem(magasin.getLibelle());
 		     					mapMagasin.put(magasin.getLibelle(), magasin);
 		     					i++;
 		     				}
		     					if(table.getSelectedRow()!=-1)
		     					{if(listFacturePF.size()!=0)
		     					{
		     						 facturePF=listFacturePF.get(table.getSelectedRow()) ;
			     						
			     						combomagasin.setSelectedItem(facturePF.getMagasin().getLibelle());
		     					}
		     						
		     						
		     					}
		     					
		     					
		     					
		     					
		     				}else
		     				{
		     					combomagasin.removeAllItems();
		     					
		     				}
		     				
		     				
		     				
		     				mapClientPF.clear();
		     				listClientPF=clientpfdao.findListClientByCodeDepot(depot.getCode());
		     				
		     				if(listClientPF.size()!=0)
		     				{
		     					comboClientpf.removeAllItems();
		     					comboClientpf.addItem("");
		     					for(int j=0;j<listClientPF.size();j++)
		     					{
		     						ClientPF clientpf=listClientPF.get(j);
		     						
		     						comboClientpf.addItem(clientpf.getNom());
		     						mapClientPF.put(clientpf.getNom(), clientpf);
		     						
		     						
		     					}
		     				}else
		     				{
		     					comboClientpf.removeAllItems();
		     				}
		     				
		     			}else
		     			{
		     				combomagasin.removeAllItems();
		     				
		     			}
		     				
    					
    				}
    				
    				
   	 		 }
   	 	
	}
		  });
		  
		  if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
		     	 combodepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		Magasin magasin=depotdao.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
		     		if(magasin!=null)
		     		{
		     			if(depot.getId()!=magasin.getDepot().getId())
			     		{
			     			combodepot.addItem(depot.getLibelle());
				     		
				     		mapDepot.put(depot.getLibelle(), depot);
				     	
				     		
			     			
			     		}
		     		}else
		     		{
		     			combodepot.addItem(depot.getLibelle());
			     		
			     		mapDepot.put(depot.getLibelle(), depot);
			     	
			     		
		     		}
		     		k++;
		     		
		     	}
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		 
	     	
		  
		  
		  
		  
		  combodepot.setSelectedIndex(-1);
		 
		  
		  JLabel label_4 = new JLabel("Magasin :");
		  label_4.setBounds(818, 13, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {

		  		
		  		 if(e.getStateChange() == ItemEvent.SELECTED)
   	 		 {
		  			 
		  			 if(combomagasin.getSelectedIndex()!=-1)
		  			 {
		  				 Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		  				 if(magasin!=null)
		  				 {
		  					Client fournisseur=fournisseurdao.findClientByCodeClient(magasin.getCodeMachine());
			  				mapFournisseur.put(fournisseur.getNom(), fournisseur);
			  				comboFournisseur.addItem(fournisseur.getNom());
		  				 }
		  				 
		  			 }
		  			 
		  			 
   	 		 }
		  		
		  		
		  	}
		  });
		  combomagasin.setBounds(872, 14, 183, 24);
		  layeredPane_1.add(combomagasin);
		  combomagasin.setSelectedIndex(-1);
		  
		   comboClientpf = new JComboBox();
		  comboClientpf.setSelectedIndex(-1);
		  comboClientpf.setBounds(1112, 13, 183, 24);
		  layeredPane_1.add(comboClientpf);
		  AutoCompleteDecorator.decorate(comboClientpf);
		  
		  JLabel lblClient = new JLabel("Client :");
		  lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblClient.setBounds(1065, 13, 56, 24);
		  layeredPane_1.add(lblClient);
		  
		  JLabel label_6 = new JLabel("Fournisseur :");
		  label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  label_6.setBounds(1305, 13, 79, 24);
		  layeredPane_1.add(label_6);
		  
		   comboFournisseur = new JComboBox();
		  comboFournisseur.setSelectedIndex(-1);
		  comboFournisseur.setBounds(1383, 14, 149, 24);
		  layeredPane_1.add(comboFournisseur);
		  
		   chckbxPiece = new JCheckBox("Piece");
		  chckbxPiece.setBounds(1557, 14, 83, 24);
		  layeredPane_1.add(chckbxPiece);
		  
		  JLabel label_12 = new JLabel("Type BL :");
		  label_12.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  label_12.setBounds(10, 13, 70, 24);
		  layeredPane_1.add(label_12);
		  
		   comboTypeBL = new JComboBox();
		  comboTypeBL.setSelectedIndex(-1);
		  comboTypeBL.setBounds(77, 14, 151, 24);
		  layeredPane_1.add(comboTypeBL);
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture");
		titledSeparator_2.setBounds(10, 375, 889, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(495, 625, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					if(checkboxGratuits.isSelected()==true)
					{
						
						boolean trouve=false;
						BigDecimal stock=BigDecimal.ZERO;
						BigDecimal marge=BigDecimal.ZERO;
						BigDecimal QuantiteUnit=BigDecimal.ZERO;
						BigDecimal QuantitePaquet=BigDecimal.ZERO;
						
						if(comboArticle.getSelectedItem().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtcodearticle.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtquantite.getText().equals("") && txtquantitepaquet.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)) ){
							
							
							JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						
						
					}else if(!txtquantitepaquet.getText().equals("") && ((new BigDecimal(txtquantitepaquet.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepaquet.getText()).compareTo(BigDecimal.ZERO)<0)) ){
						
					JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						
						}else
						{
							
							 Articles article=mapArticle.get(comboArticle.getSelectedItem());
					        
					         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
					         SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
					         
					         if(article.getCentreCout1()!=null)
					         {
					        	 BigDecimal quantite = new BigDecimal(txtquantite.getText()).setScale(3);
					        	 BigDecimal grammage = quantite.multiply(new BigDecimal(1000));
					        	 
					        	 
					        	if(article.getCentreCout1().equals(BigDecimal.ONE)) 
					        	{
					        		if(grammage.compareTo(BigDecimal.ZERO)!=0)
					        		{
					        			JOptionPane.showMessageDialog(null, "Grammage  "+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
					        			return;
					        		}
					        		
					        	}else
					        	{
					        		BigDecimal result=grammage.divide(article.getCentreCout1(), 6, RoundingMode.FLOOR);
					        	
					        		BigDecimal rest=result.subtract(result.setScale(0, RoundingMode.FLOOR)).movePointRight(result.scale());
					        	
					        		if(rest.compareTo(BigDecimal.ZERO)!=0)
					        		{
					        			JOptionPane.showMessageDialog(null, "Grammage d'"+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
					        			return;	
					        		}
					        		
					        	}
					        	 
					         }
					         
					         for(int i=0;i<listDetailFacturePF.size();i++)
					         {
					        	 DetailFacturePF detailFacturePF =listDetailFacturePF.get(i);
					        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()) && detailFacturePF.getPrixUnitaire().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))
					        			 
					        		&& 	 detailFacturePF.getMontantHT().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && detailFacturePF.getMontantTTC().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && detailFacturePF.getMontantTVA().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))
					        			 
					        			 )
					        	 {
					        		 trouve=true;
					        	 }
					         }
					         
					         if(trouve==false)
					         {
					        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasin.getId(),sousfamille.getId());
					        	 if(stockpf!=null)
					        	 {
					        		 if(!txtquantitepaquet.getText().equals(""))
					        		 {
					        			QuantitePaquet=new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement()) ;
					        		 }
					        		 if(!txtquantite.getText().equals(""))
					        		 {
					        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
					        		 }
					        		 
					        		 // Verifier le stock offre pour la gratuité du the Promotion
					        		 if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) && !sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM))
					        		 {
					        			 if((stockpf.getStockOffre()==null))
			        					 {
					        				 
					        				 
			        				 JOptionPane.showMessageDialog(null, "Stock Offre de "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
					        		 return;
			        					 }
					            	  	 if(stockpf.getStockOffre().compareTo(QuantiteUnit.add(QuantitePaquet))<0)
							        	 {
							        		 JOptionPane.showMessageDialog(null, "Stock Offre de "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
							        		 return;
							        	 }else
							        	 { 
							        		 ClientPF clientpf=mapClientPF.get(comboClientpf.getSelectedItem());
						        	 
						        	 DetailFacturePF detailFacture=new DetailFacturePF();
						        	 
							       
							         
						        	 
							          detailFacture.setArticle(article);
							          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
							          DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , clientpf.getId());
	  		  				 			
	  	  		  				 		
	  	  		  				 		if(detailprixarticle!=null)
	  	  		  				 			{
	  	  		  				 		detailFacture.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
	  	  		  				 				 
	  	  		  				 			}else
	  	  		  				 			{
	  	  		  				 				
	  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId());
		
	  	  		  				 			if(detailprixarticleTmp!=null)	
	  	  		  				 			{
	  	  		  				 			detailFacture.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
	  	  		  				 				
	  	  		  				 			 
	  	  		  				 				
	  	  		  				 			}else
	  	  		  				 			{
	  	  		  				 				
	  	  		  				 			detailFacture.setPrixUnitaire(BigDecimal.ZERO);
	  	  		  				 			
	  	  		  				 			}
	  	  		  				 				
	  	  		  				 				
	  	  		  				 				
	  	  		  				 				
	  	  		  				 			}
							          
							           detailFacture.setMontantHT(BigDecimal.ZERO);
								        detailFacture.setMontantTVA(BigDecimal.ZERO);
								        detailFacture.setMontantTTC(BigDecimal.ZERO);
								        detailFacture.setSousFamille(sousfamille);
								        detailFacture.setMontantTTC(BigDecimal.ZERO);
								        detailFacture.setTva(BigDecimal.ZERO);
								        detailFacture.setReduction(new BigDecimal(100));  
								        detailFacture.setRemiseCommerciale(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
								        detailFacture.setBrutHT(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
								         
								        
								        //detailFacture.setTypeVente(typevente);
							          
							           detailFacture.setFacturePF(facturePF);
							       //    detailFacture.setDateCreation(new Date());
							           
							           detailFacture.setUtilisateur(utilisateur);
							          listDetailFacturePF.add(detailFacture);
							         detailFacturePfdao.add(detailFacture);
							         
							           stock=stockpf.getStockOffre().subtract(QuantiteUnit.add(QuantitePaquet));
							           stockpf.setStockOffre(stock);
							           stockpfDAO.edit(stockpf);
							           listStockPF.add(stockpf);
							           
							          afficher_tableDetailFacturePF(listDetailFacturePF);
							          int i=0;
								        BigDecimal montanttotal=BigDecimal.ZERO;
								        BigDecimal sommequantite=BigDecimal.ZERO;
								        BigDecimal montanttotalHT=BigDecimal.ZERO;
								        BigDecimal montanttotalTVA=BigDecimal.ZERO;
								        BigDecimal lamarge=BigDecimal.ZERO;
								        BigDecimal compteur=BigDecimal.ZERO;
								        while(i<listDetailFacturePF.size())
								        {
								        	 DetailFacturePF detailFacturePF=listDetailFacturePF.get(i);
								        	  StockPF stockpfTmp=stockpfDAO.findStockByMagasinPFBySousFamille(detailFacturePF.getArticle().getId(),magasin.getId(),detailFacturePF.getSousFamille().getId());
									          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
									          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
									          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
									          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
									          if(!detailFacturePF.getPrixUnitaire().setScale(2,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP)))
									          {
									        	  compteur=compteur.add(BigDecimal.ONE);
									        	  lamarge=lamarge.add((detailFacturePF.getPrixUnitaire().subtract(stockpfTmp.getPrixUnitaire())).divide(detailFacturePF.getPrixUnitaire(), RoundingMode.HALF_UP));  
									          }
								            i++;
								        }
								       txttotalmontantTTC.setText(montanttotal+"");
								        txttotalquantite.setText(sommequantite+"");
								        txttotalmontantHT.setText(montanttotalHT+"");
							  			txttotalmontantTVA.setText(montanttotalTVA+"");
							  			
							  			 if(compteur!=BigDecimal.ZERO)
								  			{
								  			 Parametre margemax=parametredao.findByCode(Constantes.MARGE_MAX);
								        	 Parametre margemin=parametredao.findByCode(Constantes.MARGE_MIN);
								        	 
								  			 if((lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemax.getValeur())>0 || (lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemin.getValeur())<0)
								  			 {
								  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
								  				labelMarge.setBackground(Color.red);
								  			 }else
								  			 {
								  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
								  				labelMarge.setBackground(Color.GREEN);
								  			 }
								  			 
								  				
								  			}
							  			
							  		/// ajout transfer stock PF (Mouvement Stock PF)
							  			DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
							  			detailTransferStockPF.setArticle(article);
							  			detailTransferStockPF.setSousFamille(sousfamille);
										detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
										detailTransferStockPF.setMagasinDestination(magasin);
										detailTransferStockPF.setPrixUnitaire(BigDecimal.ZERO);
										detailTransferStockPF.setQuantite(QuantiteUnit.add(QuantitePaquet));
										detailTransferStockPF.setTransferStockPF(transferStock);
										detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
										detailTransferStockPFdao.add(detailTransferStockPF);
										listDetailTransferProduitFini.add(detailTransferStockPF);
							  			
								        initialiser();
										
							       }
					        			 
					        			 
					        		 }else
					        		 {
					        			 
					        			 
					            	  	 if(StockFinale.compareTo(QuantiteUnit.add(QuantitePaquet))<0 || stockpf.getStock().compareTo(QuantiteUnit.add(QuantitePaquet))<0)
							        	 {
							        		 JOptionPane.showMessageDialog(null, "Stock "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
							        		 return;
							        	 }else
							        	 { 
							        		 ClientPF clientpf=mapClientPF.get(comboClientpf.getSelectedItem());
						        	 
						        	 DetailFacturePF detailFacture=new DetailFacturePF();
						        	 
							        	 
							         
						        	 
							          detailFacture.setArticle(article);
							          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
							   		DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , clientpf.getId());
	  		  				 			
  	  		  				 		
  	  		  				 		if(detailprixarticle!=null)
  	  		  				 			{
  	  		  				 		detailFacture.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
  	  		  				 				 
  	  		  				 			}else
  	  		  				 			{
  	  		  				 				
  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId());
	
  	  		  				 			if(detailprixarticleTmp!=null)	
  	  		  				 			{
  	  		  				 			detailFacture.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
  	  		  				 				
  	  		  				 			 
  	  		  				 				
  	  		  				 			}else
  	  		  				 			{
  	  		  				 				
  	  		  				 			detailFacture.setPrixUnitaire(BigDecimal.ZERO);
  	  		  				 			
  	  		  				 			}
  	  		  				 				
  	  		  				 				
  	  		  				 				
  	  		  				 				
  	  		  				 			}
							           detailFacture.setMontantHT(BigDecimal.ZERO);
								        detailFacture.setMontantTVA(BigDecimal.ZERO);
								        detailFacture.setMontantTTC(BigDecimal.ZERO);
								        detailFacture.setSousFamille(sousfamille);
								        detailFacture.setMontantTTC(BigDecimal.ZERO);
								        detailFacture.setTva(BigDecimal.ZERO);
								        detailFacture.setReduction(new BigDecimal(100));  
								        detailFacture.setRemiseCommerciale(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
								        detailFacture.setBrutHT(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
								        
								        //detailFacture.setTypeVente(typevente);
							          
							           detailFacture.setFacturePF(facturePF);
							       //    detailFacture.setDateCreation(new Date());
							           
							           detailFacture.setUtilisateur(utilisateur);
							          listDetailFacturePF.add(detailFacture);
							         detailFacturePfdao.add(detailFacture);
							         
							           stock=stockpf.getStock().subtract(QuantiteUnit.add(QuantitePaquet));
							           stockpf.setStock(stock);
							           stockpfDAO.edit(stockpf);
							           listStockPF.add(stockpf);
							          afficher_tableDetailFacturePF(listDetailFacturePF);
							          int i=0;
								        BigDecimal montanttotal=BigDecimal.ZERO;
								        BigDecimal sommequantite=BigDecimal.ZERO;
								        BigDecimal montanttotalHT=BigDecimal.ZERO;
								        BigDecimal montanttotalTVA=BigDecimal.ZERO;
								        BigDecimal lamarge=BigDecimal.ZERO;
								        BigDecimal compteur=BigDecimal.ZERO;
								        while(i<listDetailFacturePF.size())
								        {
								        	 DetailFacturePF detailFacturePF=listDetailFacturePF.get(i);
								        	  StockPF stockpfTmp=stockpfDAO.findStockByMagasinPFBySousFamille(detailFacturePF.getArticle().getId(),magasin.getId(),detailFacturePF.getSousFamille().getId());
									          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
									          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
									          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
									          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
									          if(!detailFacturePF.getPrixUnitaire().setScale(2,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP)))
									          {
									        	  compteur=compteur.add(BigDecimal.ONE);
									        	  lamarge=lamarge.add((detailFacturePF.getPrixUnitaire().subtract(stockpfTmp.getPrixUnitaire())).divide(detailFacturePF.getPrixUnitaire(), RoundingMode.HALF_UP));  
									          }
								            i++;
								        }
								       txttotalmontantTTC.setText(montanttotal+"");
								        txttotalquantite.setText(sommequantite+"");
								        txttotalmontantHT.setText(montanttotalHT+"");
							  			txttotalmontantTVA.setText(montanttotalTVA+"");
							  			
							  			 if(compteur!=BigDecimal.ZERO)
								  			{
								  			 Parametre margemax=parametredao.findByCode(Constantes.MARGE_MAX);
								        	 Parametre margemin=parametredao.findByCode(Constantes.MARGE_MIN);
								        	 
								  			 if((lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemax.getValeur())>0 || (lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemin.getValeur())<0)
								  			 {
								  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
								  				labelMarge.setBackground(Color.red);
								  			 }else
								  			 {
								  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
								  				labelMarge.setBackground(Color.GREEN);
								  			 }
								  			 
								  				
								  			}
							  			
							  		/// ajout transfer stock PF (Mouvement Stock PF)
							  			DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
							  			detailTransferStockPF.setArticle(article);
							  			detailTransferStockPF.setSousFamille(sousfamille);
										detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
										detailTransferStockPF.setMagasinDestination(magasin);
										detailTransferStockPF.setPrixUnitaire(BigDecimal.ZERO);
										detailTransferStockPF.setQuantite(QuantiteUnit.add(QuantitePaquet));
										detailTransferStockPF.setTransferStockPF(transferStock);
										detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
										detailTransferStockPFdao.add(detailTransferStockPF);
										listDetailTransferProduitFini.add(detailTransferStockPF);
							  			
								        initialiser();
										
							       }
					        			 
					        		 }
					        			 
					        		 
					        		 
					    
					        	 }else
					        	 {
					        		 JOptionPane.showMessageDialog(null,  "Stock "+article.getLiblle()+" introuvable dans le magasin "+magasin.getLibelle(),"Attention",JOptionPane.ERROR_MESSAGE);
					        	 }
					        	 
					      
					       
									
						       }else
						       {
						    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						    	   return;
						       }
						       
					         }
						
						
					}else
					{
						

						boolean trouve=false;
						BigDecimal stock=BigDecimal.ZERO;
						BigDecimal stockTherresPromo=BigDecimal.ZERO;
						BigDecimal stockVerresPromo=BigDecimal.ZERO;
						BigDecimal marge=BigDecimal.ZERO;
						BigDecimal QuantiteUnit=BigDecimal.ZERO;
						BigDecimal QuantitePaquet=BigDecimal.ZERO;
						boolean promo=false;
						
						if(comboArticle.getSelectedItem().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtcodearticle.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtquantite.getText().equals("") && txtquantitepaquet.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)) ){
							
							
							JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						
						
					}else if(!txtquantitepaquet.getText().equals("") && ((new BigDecimal(txtquantitepaquet.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepaquet.getText()).compareTo(BigDecimal.ZERO)<0)) ){
						
					JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						
						
						}else if(txtPrix.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Erreur de prix","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtmontant.getText().equals("") && checkttc.isSelected()==false)
						{
							JOptionPane.showMessageDialog(null, "Erreur de Montant","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else
						{
							
							 Articles article=mapArticle.get(comboArticle.getSelectedItem());
					        
					         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
					         SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
					        if(article.getCentreCout1()!=null)
					         {
					        	 BigDecimal quantite = new BigDecimal(txtquantite.getText()).setScale(3);
					        	 BigDecimal grammage = quantite.multiply(new BigDecimal(1000));
					        	 
					        	 
					        	if(article.getCentreCout1().equals(BigDecimal.ONE)) 
					        	{
					        		if(grammage.compareTo(BigDecimal.ZERO)!=0)
					        		{
					        			JOptionPane.showMessageDialog(null, "Grammage  "+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
					        			return;
					        		}
					        		
					        	}else
					        	{
					        		BigDecimal result=grammage.divide(article.getCentreCout1(), 6, RoundingMode.FLOOR);
					        	
					        		BigDecimal rest=result.subtract(result.setScale(0, RoundingMode.FLOOR)).movePointRight(result.scale());
					        	
					        		if(rest.compareTo(BigDecimal.ZERO)!=0)
					        		{
					        			JOptionPane.showMessageDialog(null, "Grammage d'"+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
					        			return;	
					        		}
					        		
					        	}
					        	 
					         }
					         
					         for(int i=0;i<listDetailFacturePF.size();i++)
					         {
					        	 DetailFacturePF detailFacturePF =listDetailFacturePF.get(i);
					        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()) && !detailFacturePF.getPrixUnitaire().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))
					        			 
					        		&& 	 !detailFacturePF.getMontantHT().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !detailFacturePF.getMontantTTC().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !detailFacturePF.getMontantTVA().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))
					        			 )
					        	 {
					        		 trouve=true;
					        	 }
					         }
					         
					         if(trouve==false)
					         {
					        	 
			/////////////////////////////////////////////////////////////////////////////////////////  Pour Vente Offre verifier  Quantire l'Article Mere      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
					        	 
					             if(article.getCodeArticle().contains(Constantes.CODE_PRODUIT_FINI_OFFRE))
						         {
						        	 
						        //	JOptionPane.showMessageDialog(null, article.getCodeArticle().substring(0, article.getCodeArticle().lastIndexOf(Constantes.CODE_PRODUIT_FINI_OFFRE))  ); 
						        	 
						        	
						        	Articles articlePromotion=mapCodeArticle.get(article.getCodeArticle().substring(0, article.getCodeArticle().lastIndexOf(Constantes.CODE_PRODUIT_FINI_OFFRE)) );
						        	
						        	 if(articlePromotion!=null)
						        	 {
						        		 

				  				 		
				  				 			SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
				  				 			ClientPF clientpf=mapClientPF.get(comboClientpf.getSelectedItem());
				  				 		 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(articlePromotion.getId(), magasin.getId(),sousfamille.getId());
				  				 			String date="01/01/"+sdf.format(dateChooserfacture.getDate())+"";
				  				 			String dateFin="31/12/"+sdf.format(dateChooserfacture.getDate())+"";
				  				 			Date dateDebut= new Date(date);
				  				 			Date dateFinAnne= new Date(dateFin);
				  				 	BigDecimal	 StockFinaleArticlePromotion=BigDecimal.ZERO;
				  				 	BigDecimal	StockFinaleAnneArticlePromotion=BigDecimal.ZERO;
				  				 		List<Object[]> listestockfinale=listestockfinale=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateChooserfacture.getDate(), magasin, articlePromotion, sousfamille);
							 			List<Object[]> listestockfinaleAnne=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateFinAnne, magasin, articlePromotion, sousfamille);

				 			 			
							 			System.out.println("date :"+date);
				  				 		System.out.println("date debut :"+dateDebut);
				  				 		System.out.println("dateChooserfacture.getDate() :"+dateChooserfacture.getDate());
				  				 			
				  				 			for(int i=0;i<listestockfinale.size();i++)
				  				 			{
				  				 				
				  				 			 Object[] object=listestockfinale.get(i);
				  							
				  				 			 if((int) object[0]==article.getId() && (int)object[1]==sousfamille.getId())
				  				 			 {
				  				 				StockFinaleArticlePromotion=(BigDecimal)object[2];
				  				 			 }
				  				 			 	
				  				 			}
				  				 			
				  				 			for(int j=0;j<listDetailFacturePF.size();j++)
				  				 			{
				  				 				DetailFacturePF detailFacturePF=listDetailFacturePF.get(j);
				  				 				if(detailFacturePF.getArticle().getId()==article.getId() && detailFacturePF.getSousFamille().getId()==sousfamille.getId())
				  				 				{
				  				 					
				  				 					StockFinaleArticlePromotion=StockFinaleArticlePromotion.subtract(detailFacturePF.getQuantite());
				  				 					
				  				 				}
				  				 				
				  				 				
				  				 			}
				  				 			
				  				 			for(int i=0;i<listestockfinaleAnne.size();i++)
								 			{
								 				
								 			 Object[] object=listestockfinaleAnne.get(i);
											
								 			 if((int) object[0]==article.getId() && (int)object[1]==sousfamille.getId())
								 			 {
								 				StockFinaleAnneArticlePromotion=(BigDecimal)object[2];
								 			 }
								 			 	
								 			}
				  				 			
				  				 			
				  				 		 if(stockpf.getStock().compareTo(BigDecimal.ZERO)!=0 || StockFinaleArticlePromotion.compareTo(BigDecimal.ZERO)!=0)
							        	 {
							        		 JOptionPane.showMessageDialog(null, "Impossible de vendre l'Article  "+article.getLiblle() +" alors que le Stock de "+articlePromotion.getLiblle()+" est sufisant  !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
							        		 return;
							        	 }
				  				 			
				  				 			
				  				 			
				  				 			
				  				 			
				  				 			
				  				 			
						        		 
						        		 
						        		 
						        	 }
						        	 
						         } 
					        	 
					        	 
					        	 
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		        	 
					        	 
			
					        	 
					        	 
					        	 
					        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasin.getId(),sousfamille.getId());
					        	 if(stockpf!=null)
					        	 {
					        		 if(!txtquantitepaquet.getText().equals(""))
					        		 {
					        			QuantitePaquet=new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement()) ;
					        		 }
					        		 if(!txtquantite.getText().equals(""))
					        		 {
					        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
					        		 }
					        		 
					        	  	 if(StockFinale.compareTo(QuantiteUnit.add(QuantitePaquet))<0 || stockpf.getStock().compareTo(QuantiteUnit.add(QuantitePaquet))<0)
						        	 {
						        		 JOptionPane.showMessageDialog(null, "Stock "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
						        		 return;
						        	 }else
						        	 { 
						        		 
						        		 
						        		 SousFamilleArticlePF sousFamilleArticlePFOffreTherres=null; 
						        		 SousFamilleArticlePF sousFamilleArticlePFOffreVerres=null;
						        		 
						        	
										 BigDecimal stockArticlePromo=BigDecimal.ZERO;
						        		 Articles articlesOffreTherres=null;
						        		 Articles articlesOffreVerres=null;
						        		 StockPF stockpfOffreTherres=null;
						        		 StockPF stockpfOffreVerres=null;
						        		 StockPF stockpfArticlepromo=null;
						        		 
						        		
						        		 
						        		////////////////////////////////////////////////// Traitement Promotion  /////////////////////////////////////// 
						        		 
						        			if(article.getCentreCout5()!=null)
				  				 			{
				  				 			// centrecout5 = article promotion ou non
				  				 				if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) && !sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM))
				  				 				{
				  				 					
				  				 					 if(!txtquantitetherres.getText().equals("") )
				  				 					{
				  				 						 if(((new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)<0)))
				  				 						 {
				  				 							JOptionPane.showMessageDialog(null, "la quantite des Therres doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					  										return; 
				  				 						 }
				  				 						
				  									else if( new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)>0 )
				  				 						{
				  				 							
				  				 							sousFamilleArticlePFOffreTherres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_THERRES);
					  	  				 					
						  	  				 				articlesOffreTherres=mapArticleOffre.get(comboBoxtherres.getSelectedItem());
						  	  				 				stockpfOffreTherres=stockpfDAO.findStockByMagasinPFBySousFamille(articlesOffreTherres.getId(), magasin.getId(),sousFamilleArticlePFOffreTherres.getId());
						  				 						
						  	  				 			if(stockpfOffreTherres.getStock().compareTo(new BigDecimal(txtquantitetherres.getText()))<0 || stockfinaleTherres.compareTo(new BigDecimal(txtquantitetherres.getText()))<0)
											        	 {
											        		 JOptionPane.showMessageDialog(null, "Stock "+articlesOffreTherres.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
											        		 return;
											        	 }
						  	  				 				
				  				 							
				  				 						}
				  				 						
				  	  				 				
				  				 							}
				  				 					 
				  				 					 
				  				 					///////////////////////////////////////////////////////////////// 
				  				 					 
				  				 					 
				  				 					 if(!txtquantiteverres.getText().equals("") )
				  				 					{
				  				 						 if (((new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)<0)))
		                                               {
			                                      JOptionPane.showMessageDialog(null, "la quantite des Verres doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			                                 	return;
				
		                                              }else if(new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)>0 )
				  				 						{
				  				 							
				  				 						 sousFamilleArticlePFOffreVerres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_VERRES);
					  				 						
					  				 					  articlesOffreVerres=mapArticleOffre.get(comboBoxverres.getSelectedItem());
						  	  				 			     stockpfOffreVerres=stockpfDAO.findStockByMagasinPFBySousFamille(articlesOffreVerres.getId(), magasin.getId(),sousFamilleArticlePFOffreVerres.getId());	
						  	  				 			 if(stockpfOffreVerres.getStock().compareTo(new BigDecimal(txtquantiteverres.getText()))<0 || stockfinaleVerres.compareTo(new BigDecimal(txtquantiteverres.getText()))<0)
											        	 {
											        		 JOptionPane.showMessageDialog(null, "Stock "+articlesOffreTherres.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
											        		 return;
											        	 }
					  				 						
						  	  				 			 
				  				 						}
				  				 						
				  				 					}
				  				 					 
				  				 					 
				  				 					 //////////////////////////////////////////////////////////////////////////////////////////////////////
				  				 						
										        		 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
										        		 if(articlespromo!=null)
										        		 {
										        			if(!txtquantitepromo.getText().equals("")) 
										        			{
										        				
										        				stockpfArticlepromo=stockpfDAO.findStockByMagasinPFBySousFamille(articlespromo.getId(), magasin.getId(),sousfamille.getId());
										        				
										        				if(new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)<0 )
										        				{
										        					JOptionPane.showMessageDialog(null, "la quantite d'Article Promo doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							  										return;
										        				}
										        				
										        				if(stockpfArticlepromo!=null)
										        				{
										        					 if(stockpfArticlepromo.getStock().compareTo(new BigDecimal(txtquantitepromo.getText()))<0 || stockfinaleArticlePromo.compareTo(new BigDecimal(txtquantitepromo.getText()))<0)
														        	 {
														        		 JOptionPane.showMessageDialog(null, "Stock "+articlespromo.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
														        		 return;
														        	 }
										        					
										        				}
										        				
										        				
										        				
										        			}
										        			 
										        			 
										        		 }
										        		 
										        		 
										        		 
										        		 promo=true;
										        		 
										        	 }else if (article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && checkboxGratuits.isSelected()==false  &&  magasin.getCode().equals(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING_PF) )
						  				 				{
										        		 

						  				 					
					  				 					 if(!txtquantitetherres.getText().equals("") )
					  				 					{
					  				 						 if(((new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)<0)))
					  				 						 {
					  				 							JOptionPane.showMessageDialog(null, "la quantite des Therres doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						  										return; 
					  				 						 }
					  				 						
					  									else if( new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)>0 )
					  				 						{
					  				 							
					  				 							sousFamilleArticlePFOffreTherres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_THERRES);
						  	  				 					
							  	  				 				articlesOffreTherres=mapArticleOffre.get(comboBoxtherres.getSelectedItem());
							  	  				 				stockpfOffreTherres=stockpfDAO.findStockByMagasinPFBySousFamille(articlesOffreTherres.getId(), magasin.getId(),sousFamilleArticlePFOffreTherres.getId());
							  				 						
							  	  				 			if(stockpfOffreTherres.getStock().compareTo(new BigDecimal(txtquantitetherres.getText()))<0 || stockfinaleTherres.compareTo(new BigDecimal(txtquantitetherres.getText()))<0)
												        	 {
												        		 JOptionPane.showMessageDialog(null, "Stock "+articlesOffreTherres.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
												        		 return;
												        	 }
							  	  				 				
					  				 							
					  				 						}
					  				 						
					  	  				 				
					  				 							}
					  				 					 
					  				 					 
					  				 					///////////////////////////////////////////////////////////////// 
					  				 					 
					  				 					 
					  				 					 if(!txtquantiteverres.getText().equals("") )
					  				 					{
					  				 						 if (((new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)<0)))
			                                               {
				                                      JOptionPane.showMessageDialog(null, "la quantite des Verres doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
				                                 	return;
					
			                                              }else if(new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)>0 )
					  				 						{
					  				 							
					  				 						 sousFamilleArticlePFOffreVerres=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_VERRES);
						  				 						
						  				 					  articlesOffreVerres=mapArticleOffre.get(comboBoxverres.getSelectedItem());
							  	  				 			     stockpfOffreVerres=stockpfDAO.findStockByMagasinPFBySousFamille(articlesOffreVerres.getId(), magasin.getId(),sousFamilleArticlePFOffreVerres.getId());	
							  	  				 			 if(stockpfOffreVerres.getStock().compareTo(new BigDecimal(txtquantiteverres.getText()))<0 || stockfinaleVerres.compareTo(new BigDecimal(txtquantiteverres.getText()))<0)
												        	 {
												        		 JOptionPane.showMessageDialog(null, "Stock "+articlesOffreTherres.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
												        		 return;
												        	 }
						  				 						
							  	  				 			 
					  				 						}
					  				 						
					  				 					}
					  				 					 
					  				 					 
					  				 					 //////////////////////////////////////////////////////////////////////////////////////////////////////
					  				 						
											        		 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
											        		 if(articlespromo!=null)
											        		 {
											        			if(!txtquantitepromo.getText().equals("")) 
											        			{
											        				
											        				stockpfArticlepromo=stockpfDAO.findStockByMagasinPFBySousFamille(articlespromo.getId(), magasin.getId(),sousfamille.getId());
											        				
											        				if(new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)<0 )
											        				{
											        					JOptionPane.showMessageDialog(null, "la quantite d'Article Promo doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								  										return;
											        				}
											        				
											        				if(stockpfArticlepromo!=null)
											        				{
											        					 if(stockpfArticlepromo.getStock().compareTo(new BigDecimal(txtquantitepromo.getText()))<0 || stockfinaleArticlePromo.compareTo(new BigDecimal(txtquantitepromo.getText()))<0)
															        	 {
															        		 JOptionPane.showMessageDialog(null, "Stock "+articlespromo.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
															        		 return;
															        	 }
											        					
											        				}
											        				
											        				
											        				
											        			}
											        			 
											        			 
											        		 }
											        		 
											        		 
											        		 
											        		 promo=true;
											        		 
											        	 
										        		 
						  				 				}
						  				 					
										        		 
				  	  				 			    	
				  	  				 			  
				  				 			}
						        		 
						        		 
						        		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						        		 
						        		 
						        
						        		 
					        	 
						        		 marge=new BigDecimal(txtPrix.getText()).subtract(stockpf.getPrixUnitaire()).divide(new BigDecimal(txtPrix.getText()), 6, RoundingMode.HALF_UP);
				        				 
						        		 marge=marge.multiply(new BigDecimal(100));
							        	 Parametre margemax=parametredao.findByCode(Constantes.MARGE_MAX);
							        	 Parametre margemin=parametredao.findByCode(Constantes.MARGE_MIN);
							        	 if(marge.compareTo(margemax.getValeur())>0 || marge.compareTo(margemin.getValeur())<0)
							        	 {
							        		 
							        		 int reponse = JOptionPane.showConfirmDialog(null, " Erreur de la marge Vous voulez vraiment valider le prix  ?", 
														"Satisfaction", JOptionPane.YES_NO_OPTION);
												 
												if(reponse == JOptionPane.YES_OPTION )
													
													
												{
													
													
												}else
												{
													
													
													
													
													 return;
												}
							        	 }
					        	 
					        	 
					        	 
					        	 
					        	 DetailFacturePF detailFacture=new DetailFacturePF();
					        	 if(!txtreduction.getText().equals(""))
						          {
						        	  detailFacture.setReduction(new BigDecimal(txtreduction.getText()));
						          }else
						          {
						        	  detailFacture.setReduction(BigDecimal.ZERO);  
						          }
					        	 
						          detailFacture.setArticle(article);
						          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
						     if(checkttc.isSelected()==true)
						     {

								   detailFacture.setPrixUnitaire(prixTTC);
							          detailFacture.setBrutHT ((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
							          if(checkboxSansTva.isSelected()==true)
							          {
							        	  detailFacture.setMontantTVA(BigDecimal.ZERO);								         
								          detailFacture.setTva(BigDecimal.ZERO);
							          }else
							          {
							        	  detailFacture.setMontantTVA(((((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));								         
								          detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
							          }
							         
							         
							          detailFacture.setSousFamille(sousfamille);
							          if(!txtreduction.getText().equals(""))
							          {
							        	   if(checkboxSansTva.isSelected()==true)
									          {
							        		   
							        		   detailFacture.setRemiseCommerciale(((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP));
							        		   detailFacture.setMontantHT(((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).setScale(6, RoundingMode.HALF_UP));
							        		   detailFacture.setMontantTVA(BigDecimal.ZERO);
							        		   detailFacture.setTva(BigDecimal.ZERO);
							        		   detailFacture.setMontantTTC(((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).setScale(6, RoundingMode.HALF_UP));  

									          }else
									          {
								        		   detailFacture.setRemiseCommerciale(((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP));
								        		   detailFacture.setMontantHT(((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).setScale(6, RoundingMode.HALF_UP));
								        		   detailFacture.setMontantTVA((((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
								        		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
									        	  detailFacture.setMontantTTC((((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).add(((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));  

									        	  
									          }
							          
							          
							          }else
							          {
							        	  if(checkboxSansTva.isSelected()==true)
								          {
							        		   detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
							        		   detailFacture.setMontantHT(((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP));
							        		   detailFacture.setMontantTVA(BigDecimal.ZERO);
							        		   detailFacture.setTva(BigDecimal.ZERO);
								        	  detailFacture.setMontantTTC(((((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));

								          }else
								          {
								        	  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
								        	  detailFacture.setMontantHT(((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP));
								        	  detailFacture.setMontantTVA( (((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP) );
								        	  
								        	  detailFacture.setMontantTTC((((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP))).add( ((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP))).multiply(Constantes.TVA) )).setScale(6, RoundingMode.HALF_UP) );

								          }
							          }
							          
							      
							          
							 
							   
						     
						     }else
						     {


							      detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
						          detailFacture.setBrutHT ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
						          if(checkboxSansTva.isSelected()==true)
						          {
						        	  detailFacture.setMontantTVA(BigDecimal.ZERO);
							          detailFacture.setTva(BigDecimal.ZERO);
						          }else
						          {
						        	  detailFacture.setMontantTVA((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
							          detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
						          }
						         
						          detailFacture.setSousFamille(sousfamille);
						          if(!txtreduction.getText().equals(""))
						          {
						        	  if(checkboxSansTva.isSelected()==true)
							          {
						        		  detailFacture.setRemiseCommerciale( ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP) );
						        		  detailFacture.setMontantHT(( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );
						        		  detailFacture.setMontantTVA(BigDecimal.ZERO);
						        		  detailFacture.setTva(BigDecimal.ZERO);
						        		  detailFacture.setMontantTTC(( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).setScale(6, RoundingMode.HALF_UP) );  

							          }else
							          {
							        	  
						        		  detailFacture.setRemiseCommerciale( ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP) );
						        		  detailFacture.setMontantHT(( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );
						        		  detailFacture.setMontantTVA( (( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP) );
						        		  detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
							        	  
							        	  detailFacture.setMontantTTC( (( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).add( ( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).multiply(Constantes.TVA) )).setScale(6, RoundingMode.HALF_UP) );  

							          }
						          }else
						          {
						        	  
						        	  if(checkboxSansTva.isSelected()==true)
							          {
						        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
						        		  detailFacture.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
						        		  detailFacture.setMontantTVA(BigDecimal.ZERO);
						        		  detailFacture.setTva(BigDecimal.ZERO);
							        	  detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));

						        		  
							          }else
							          {
							        	  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
						        		  detailFacture.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
						        		  detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP) );
						        		  detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
						        		  detailFacture.setMontantTTC( ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).add((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA) )).setScale(6, RoundingMode.HALF_UP) );

							          }
						          
						          
						          }
						
						   
						     
						     }
						          
						          
							        //detailFacture.setTypeVente(typevente);
						          
						           detailFacture.setFacturePF(facturePF);
						       //    detailFacture.setDateCreation(new Date());
						           
						           detailFacture.setUtilisateur(utilisateur);
						           listDetailFacturePF.add(detailFacture);
						         detailFacturePfdao.add(detailFacture);
						         
						           stock=stockpf.getStock().subtract(QuantiteUnit.add(QuantitePaquet));
						           stockpf.setStock(stock);
						           stockpfDAO.edit(stockpf);
						           listStockPF.add(stockpf);
						           
							          if(promo==true)
							          {
							        	  
							        	   ////////////////////////////////////////////////////// Therres offre /////////////////////////////////////////////
						        		 
							        	  if(!txtquantitetherres.getText().equals(""))
							        	  {
							        		  
							        		  DetailFacturePF detailFactureTherres=new DetailFacturePF();
								        		 
								        		 detailFactureTherres.setArticle(articlesOffreTherres);
								        		 detailFactureTherres.setQuantite(new BigDecimal(txtquantitetherres.getText()));
								        		 
								        			DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articlesOffreTherres.getId(), magasin.getId(),sousFamilleArticlePFOffreTherres.getFamileArticlePF().getId() , sousFamilleArticlePFOffreTherres.getId() , facturePF.getClientPF().getId());
			  	  		  				 			
				  	  		  				 		
				  	  		  				 		if(detailprixarticle!=null)
				  	  		  				 			{
				  	  		  				 	 detailFactureTherres.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
				  	  		  				 			 
				  	  		  				 			}else
				  	  		  				 			{
				  	  		  				 				
				  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(articlesOffreTherres.getId(), magasin.getId(),sousFamilleArticlePFOffreTherres.getFamileArticlePF().getId() , sousFamilleArticlePFOffreTherres.getId());
					
				  	  		  				 			if(detailprixarticleTmp!=null)	
				  	  		  				 			{
				  	  		  				 		 detailFactureTherres.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
				  	  		  				 				 
				  	  		  				 				
				  	  		  				 			}else
				  	  		  				 			{
				  	  		  				 			detailFactureTherres.setPrixUnitaire(BigDecimal.ZERO);
				  	  		  				 		 
				  	  		  				 			
				  	  		  				 			}
				  	  		  				 				
				  	  		  				 				
				  	  		  				 				
				  	  		  				 				
				  	  		  				 			}
								        		 
								        		 
								        		 detailFactureTherres.setMontantHT(BigDecimal.ZERO);
								        		 detailFactureTherres.setMontantTVA(BigDecimal.ZERO);
								        		 detailFactureTherres.setTva(BigDecimal.ZERO);
								        		 detailFactureTherres.setRemiseCommerciale(detailFactureTherres.getPrixUnitaire().multiply(detailFactureTherres.getQuantite()));
								        		 detailFactureTherres.setBrutHT(detailFactureTherres.getPrixUnitaire().multiply(detailFactureTherres.getQuantite()));
								        		 detailFactureTherres.setSousFamille(sousFamilleArticlePFOffreTherres);
								        		 detailFactureTherres.setReduction(new BigDecimal(100));
								        		 detailFactureTherres.setMontantTTC(BigDecimal.ZERO);  
											         
											         
											         //  detailFacture.setTypeVente(typevente);
											        
								        		 detailFactureTherres.setFacturePF(facturePF);
											       //  detailFacture.setDateCreation(new Date());
											           
								        		 detailFactureTherres.setUtilisateur(utilisateur);
											         
											           listDetailFacturePF.add(detailFactureTherres);
											        detailFacturePfdao.add(detailFactureTherres);
											           stockTherresPromo=stockpfOffreTherres.getStock().subtract(new BigDecimal(txtquantitetherres.getText()));
											          
											           stockpfOffreTherres.setStock(stockTherresPromo);
											           stockpfDAO.edit(stockpfOffreTherres);
											           listStockPF.add(stockpfOffreTherres);
											           
											           
							        	  }
							        	  
							        	  
						        		
									           
									           
							          
									           ////////////////////////////////////////////////////// Verres offre /////////////////////////////////////////////
							        	  if(!txtquantiteverres.getText().equals(""))
							        	  {
							        		 

									           DetailFacturePF detailFactureVerres=new DetailFacturePF();
								        		 
								        		 detailFactureVerres.setArticle(articlesOffreVerres);
								        		 detailFactureVerres.setQuantite(new BigDecimal(txtquantiteverres.getText()));
								        		 
								        		   DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articlesOffreVerres.getId(), magasin.getId(),sousFamilleArticlePFOffreVerres.getFamileArticlePF().getId() , sousFamilleArticlePFOffreVerres.getId() , facturePF.getClientPF().getId());
			  	  		  				 			
				  	  		  				 		
				  	  		  				 		if(detailprixarticle!=null)
				  	  		  				 			{
				  	  		  				 		detailFactureVerres.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
				  	  		  				 			 
				  	  		  				 			}else
				  	  		  				 			{
				  	  		  				 				
				  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(articlesOffreVerres.getId(), magasin.getId(),sousFamilleArticlePFOffreVerres.getFamileArticlePF().getId() , sousFamilleArticlePFOffreVerres.getId());
					
				  	  		  				 			if(detailprixarticleTmp!=null)	
				  	  		  				 			{
				  	  		  				 			detailFactureVerres.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
				  	  		  				 				 
				  	  		  				 				
				  	  		  				 			}else
				  	  		  				 			{
				  	  		  				 			detailFactureVerres.setPrixUnitaire(BigDecimal.ZERO);
				  	  		  				 				
				  	  		  				 		 
				  	  		  				 			
				  	  		  				 			}
				  	  		  				 				
				  	  		  				 				
				  	  		  				 				
				  	  		  				 				
				  	  		  				 			}
								        		 detailFactureVerres.setMontantHT(BigDecimal.ZERO);
								        		 detailFactureVerres.setMontantTVA(BigDecimal.ZERO);
								        		 detailFactureVerres.setTva(BigDecimal.ZERO);
								        		 detailFactureVerres.setSousFamille(sousFamilleArticlePFOffreVerres);
								        		 detailFactureVerres.setReduction(new BigDecimal(100));
								        		 detailFactureVerres.setMontantTTC(BigDecimal.ZERO);  
								        		 detailFactureVerres.setBrutHT(detailFactureVerres.getPrixUnitaire().multiply(detailFactureVerres.getQuantite()));
								        		 detailFactureVerres.setRemiseCommerciale(detailFactureVerres.getPrixUnitaire().multiply(detailFactureVerres.getQuantite()));
											         
											         
											         //  detailFacture.setTypeVente(typevente);
											        
								        		 detailFactureVerres.setFacturePF(facturePF);
											       //  detailFacture.setDateCreation(new Date());
											           
								        		 detailFactureVerres.setUtilisateur(utilisateur);
											         
											           listDetailFacturePF.add(detailFactureVerres);
											           detailFacturePfdao.add(detailFactureVerres);
											           stockVerresPromo=stockpfOffreVerres.getStock().subtract(new BigDecimal(txtquantiteverres.getText()));
											          
											           stockpfOffreVerres.setStock(stockVerresPromo);
											           stockpfDAO.edit(stockpfOffreVerres);
											           listStockPF.add(stockpfOffreVerres); 
							        		  
							        	  }
							        	  
							        	  
											           
										//////////////////////////////////////////////// Article Promo ///////////////////////////////////////////////////////////	     
											           
											           Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
										        		 if(articlespromo!=null)
										        		 {
										        			if(!txtquantitepromo.getText().equals("")) 
										        			{
										        				
										        				if(new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)!=0 && new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)>0)
										        				{
										        					
										        					
										        					 DetailFacturePF detailFactureArticlePromo=new DetailFacturePF();
													        		 
										        					 detailFactureArticlePromo.setArticle(articlespromo);
										        					 detailFactureArticlePromo.setQuantite(new BigDecimal(txtquantitepromo.getText()));
										        					 
										        					 
										        					  DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articlespromo.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , facturePF.getClientPF().getId());
								  	  		  				 			
									  	  		  				 		
									  	  		  				 		if(detailprixarticle!=null)
									  	  		  				 			{
									  	  		  				 		detailFactureArticlePromo.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
									  	  		  				 			 
									  	  		  				 			}else
									  	  		  				 			{
									  	  		  				 				
									  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(articlespromo.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId());
										
									  	  		  				 			if(detailprixarticleTmp!=null)	
									  	  		  				 			{
									  	  		  				 			detailFactureArticlePromo.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
									  	  		  				 				 
									  	  		  				 				
									  	  		  				 			}else
									  	  		  				 			{
									  	  		  				 				
									  	  		  				 			detailFactureArticlePromo.setPrixUnitaire(BigDecimal.ZERO);
									  	  		  				 			
									  	  		  				 			}
									  	  		  				 				
									  	  		  				 				
									  	  		  				 				
									  	  		  				 				
									  	  		  				 			}
										        					 
										        					 detailFactureArticlePromo.setMontantHT(BigDecimal.ZERO);
										        					 detailFactureArticlePromo.setMontantTVA(BigDecimal.ZERO);
										        					 detailFactureArticlePromo.setTva(BigDecimal.ZERO);
										        					 detailFactureArticlePromo.setBrutHT(detailFactureArticlePromo.getQuantite().multiply(detailFactureArticlePromo.getPrixUnitaire()));
										        					 detailFactureArticlePromo.setRemiseCommerciale(detailFactureArticlePromo.getQuantite().multiply(detailFactureArticlePromo.getPrixUnitaire()));
										        					 detailFactureArticlePromo.setSousFamille(sousfamille);
										        					 detailFactureArticlePromo.setReduction(new BigDecimal(100));
										        					 detailFactureArticlePromo.setMontantTTC(BigDecimal.ZERO);  
																         
																         
																         //  detailFacture.setTypeVente(typevente);
																        
										        					 detailFactureArticlePromo.setFacturePF(facturePF);
																       //  detailFacture.setDateCreation(new Date());
																           
										        					 detailFactureArticlePromo.setUtilisateur(utilisateur);
																         
																           listDetailFacturePF.add(detailFactureArticlePromo);
																           detailFacturePfdao.add(detailFactureArticlePromo);
																           stockArticlePromo=stockpfArticlepromo.getStock().subtract(new BigDecimal(txtquantitepromo.getText()));
																          
																           stockpfArticlepromo.setStock(stockArticlePromo);
																           stockpfDAO.edit(stockpfArticlepromo);
																           listStockPF.add(stockpfArticlepromo);
										        					
										        					
										        					
										        				}
										        				
										        				
										        			}
										        		 }
											                  
							        
							          }
						           
						           
						           
						           
						          afficher_tableDetailFacturePF(listDetailFacturePF);
						          int i=0;
							        BigDecimal montanttotal=BigDecimal.ZERO;
							        BigDecimal sommequantite=BigDecimal.ZERO;
							        BigDecimal montanttotalHT=BigDecimal.ZERO;
							        BigDecimal montanttotalTVA=BigDecimal.ZERO;
							        BigDecimal lamarge=BigDecimal.ZERO;
							        BigDecimal compteur=BigDecimal.ZERO;
							        while(i<listDetailFacturePF.size())
							        {
							        	 DetailFacturePF detailFacturePF=listDetailFacturePF.get(i);
								          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
								          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
								          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
								          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
								          if(!detailFacturePF.getPrixUnitaire().setScale(2,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP)))
								          {
								        	  compteur=compteur.add(BigDecimal.ONE);
								        	  lamarge=lamarge.add((detailFacturePF.getPrixUnitaire().subtract(stockpf.getPrixUnitaire())).divide(detailFacturePF.getPrixUnitaire(), RoundingMode.HALF_UP));  
								          }
							            i++;
							        }
							       txttotalmontantTTC.setText(montanttotal+"");
							        txttotalquantite.setText(sommequantite+"");
							        txttotalmontantHT.setText(montanttotalHT+"");
						  			txttotalmontantTVA.setText(montanttotalTVA+"");
						  			
						  			 if(compteur!=BigDecimal.ZERO)
							  			{
							  			
							        	 
							  			 if((lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemax.getValeur())>0 || (lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemin.getValeur())<0)
							  			 {
							  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
							  				labelMarge.setBackground(Color.red);
							  			 }else
							  			 {
							  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
							  				labelMarge.setBackground(Color.GREEN);
							  			 }
							  			 
							  				
							  			}
						  			
						  		/// ajout transfer stock PF (Mouvement Stock PF)
						  			DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
						  			detailTransferStockPF.setArticle(article);
						  			detailTransferStockPF.setSousFamille(sousfamille);
									detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
									detailTransferStockPF.setMagasinDestination(magasin);
									if(checkttc.isSelected()==true)
									{
										 if(!txtreduction.getText().equals(""))
								          {
											  detailTransferStockPF.setPrixUnitaire(prixTTC.subtract(prixTTC.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											 
								          }else
								          {
								        	  detailTransferStockPF.setPrixUnitaire(prixTTC);
								          }
										
										
											
									}else
									{
										 if(!txtreduction.getText().equals(""))
								          {
											  detailTransferStockPF.setPrixUnitaire(new BigDecimal(txtPrix.getText()).subtract(new BigDecimal(txtPrix.getText()).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											 
								          }else
								          {
								        	  detailTransferStockPF.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
								          }
										 
									}
							      
									detailTransferStockPF.setQuantite(QuantiteUnit.add(QuantitePaquet));
									detailTransferStockPF.setTransferStockPF(transferStock);
									detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
									detailTransferStockPFdao.add(detailTransferStockPF);
									listDetailTransferProduitFini.add(detailTransferStockPF);
						  			
						            if( promo==true)
                                    {
                                   	 
                                  	  ////////////////////////////////////////////////////// Therres offre /////////////////////////////////////////////
                                  	 if(!txtquantitetherres.getText().equals(""))
							        	  {
                                  		 
                                  		 DetailTransferProduitFini detailTransferStockPFTherres=new DetailTransferProduitFini();
									           detailTransferStockPFTherres.setArticle(articlesOffreTherres);
									           detailTransferStockPFTherres.setSousFamille(sousFamilleArticlePFOffreTherres);
									           detailTransferStockPFTherres.setDateTransfer(dateChooserfacture.getDate());
									           detailTransferStockPFTherres.setMagasinDestination(magasin);
									 		 detailTransferStockPFTherres.setPrixUnitaire(BigDecimal.ZERO);
			  	  		  				 	detailTransferStockPFTherres.setQuantite(new BigDecimal(txtquantitetherres.getText()));
									           detailTransferStockPFTherres.setTransferStockPF(transferStock);
									           detailTransferStockPFTherres.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
												listDetailTransferProduitFini.add(detailTransferStockPFTherres);
												detailTransferStockPFdao.add(detailTransferStockPFTherres);
                                  		 
							        	  }
						        		
									          
									           
										 ////////////////////////////////////////////////////// Verres offre /////////////////////////////////////////////
								        		 
                                  	 if(!txtquantiteverres.getText().equals(""))
							        	  {
                                  		 
                                  		 DetailTransferProduitFini detailTransferStockPFVerres=new DetailTransferProduitFini();
									           detailTransferStockPFVerres.setArticle(articlesOffreVerres);
									           detailTransferStockPFVerres.setSousFamille(sousFamilleArticlePFOffreVerres);
									           detailTransferStockPFVerres.setDateTransfer(dateChooserfacture.getDate());
									           detailTransferStockPFVerres.setMagasinDestination(magasin);
									  		  detailTransferStockPFVerres.setPrixUnitaire(BigDecimal.ZERO);
			  	  		  				 	  detailTransferStockPFVerres.setQuantite(new BigDecimal(txtquantiteverres.getText()));
									           detailTransferStockPFVerres.setTransferStockPF(transferStock);
									           detailTransferStockPFVerres.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
											   listDetailTransferProduitFini.add(detailTransferStockPFVerres); 
                                  		       detailTransferStockPFdao.add(detailTransferStockPFVerres);
							        	  }
								        		 
											               
						        		 
						        	 
													   ////////////////////////////////////////////////////// Article promo /////////////////////////////////////////////
										        		 
													   Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
										        		 if(articlespromo!=null)
										        		 {
										        			if(!txtquantitepromo.getText().equals("")) 
										        			{
										        				
										        				if(new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)!=0 && new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)>0)
										        				{
										        					
										        					DetailTransferProduitFini detailTransferStockPFArticlePromo=new DetailTransferProduitFini();
															           detailTransferStockPFArticlePromo.setArticle(articlespromo);
															           detailTransferStockPFArticlePromo.setSousFamille(sousfamille);
															           detailTransferStockPFArticlePromo.setDateTransfer(dateChooserfacture.getDate());
															           detailTransferStockPFArticlePromo.setMagasinDestination(magasin);
															           detailTransferStockPFArticlePromo.setPrixUnitaire(BigDecimal.ZERO);
									  	  		  				 		detailTransferStockPFArticlePromo.setQuantite(new BigDecimal(txtquantitepromo.getText()));
															           detailTransferStockPFArticlePromo.setTransferStockPF(transferStock);
															           detailTransferStockPFArticlePromo.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
																	   listDetailTransferProduitFini.add(detailTransferStockPFArticlePromo);  
																	   detailTransferStockPFdao.add(detailTransferStockPFArticlePromo);
																	  
										        				}
										        				
										        			}
										        			
										        		 }
										        	
                                     }
									
									
							        initialiser();
									
						       }
					        	 }else
					        	 {
					        		 JOptionPane.showMessageDialog(null,  "Stock "+article.getLiblle()+" introuvable dans le magasin "+magasin.getLibelle(),"Attention",JOptionPane.ERROR_MESSAGE);
					        	 }
					        	 
					      
					       
									
						       }else
						       {
						    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						    	   return;
						       }
						       
					         }
						
					}
					
					
			
				        
						
					
					
				}catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité , Le Prix et la Remise doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
			}
				
			
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
		
		  
		   btnModifier = new JButton();
		   btnModifier.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		try {
		   			
		   			if(checkboxGratuits.isSelected()==true)
		   			{
		   				ClientPF clientpf=mapClientPF.get(comboClientpf.getSelectedItem());
				   		BigDecimal stock=BigDecimal.ZERO;
				   		BigDecimal stocktmp=BigDecimal.ZERO;
				   		BigDecimal marge=BigDecimal.ZERO;
				   		BigDecimal QuantiteUnit=BigDecimal.ZERO;
						BigDecimal QuantitePaquet=BigDecimal.ZERO;
						
				   		if(tableArticle.getSelectedRow()!=-1)
				   		{
				   			boolean trouve=false;
							
							if(comboArticle.getSelectedItem().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtquantite.getText().equals("") && txtquantitepaquet.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)) ){
								
								
								JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							
						}else if(!txtquantitepaquet.getText().equals("") && ((new BigDecimal(txtquantitepaquet.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepaquet.getText()).compareTo(BigDecimal.ZERO)<0)) ){
							
						JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} else 
							{
								
								 Articles article=mapArticle.get(comboArticle.getSelectedItem());
						        
						         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
						         SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
						         if(article.getCentreCout1()!=null)
						         {
						        	 BigDecimal quantite = new BigDecimal(txtquantite.getText()).setScale(3);
						        	 BigDecimal grammage = quantite.multiply(new BigDecimal(1000));
						        	 
						        	 
						        	if(article.getCentreCout1().equals(BigDecimal.ONE)) 
						        	{
						        		if(grammage.compareTo(BigDecimal.ZERO)!=0)
						        		{
						        			JOptionPane.showMessageDialog(null, "Grammage  "+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
						        			return;
						        		}
						        		
						        	}else
						        	{
						        		BigDecimal result=grammage.divide(article.getCentreCout1(), 6, RoundingMode.FLOOR);
						        	
						        		BigDecimal rest=result.subtract(result.setScale(0, RoundingMode.FLOOR)).movePointRight(result.scale());
						        	
						        		if(rest.compareTo(BigDecimal.ZERO)!=0)
						        		{
						        			JOptionPane.showMessageDialog(null, "Grammage d'"+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
						        			return;	
						        		}
						        		
						        	}
						        	 
						         }
						         
						         for(int i=0;i<listDetailFacturePF.size();i++)
						         {
						        	 if(i!=tableArticle.getSelectedRow())
						        	 {
						        		 
						        		 DetailFacturePF detailFacturePF =listDetailFacturePF.get(i);
							        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()) && detailFacturePF.getPrixUnitaire().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))
							        			 
							        			 && detailFacturePF.getMontantHT().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && detailFacturePF.getMontantTTC().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))
							        			 
							        			 )
							        	 {
							        		 trouve=true;
							        	 }
							        	  
						        	 }
						        	 
						        	 
						         }
						         
						         if(trouve==false)
						         {
						        	 
						        	 StockPF stockpftmp =listStockPF.get(tableArticle.getSelectedRow());
						        	
						        	 // Verifier le stock offre pour la gratuité du the Promotion
					        		
					        			 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getSousFamille().getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM))
					        			 {
					        				 stocktmp=stockpftmp.getStockOffre().add(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
								        	 stockpftmp.setStockOffre(stocktmp); 
								        	 stockpfDAO.edit(stockpftmp);
					        			 }else
					        			 {
					        				 stocktmp=stockpftmp.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
								        	 stockpftmp.setStock(stocktmp);  
								        	 stockpfDAO.edit(stockpftmp); 
					        			 }
					        		
						        	 
						        	
						        	 
						        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasin.getId(),sousfamille.getId());
						        	 
						        	 
						        	 
						        	 
						        	 if(stockpf!=null)
						        	 {
						        		 
						        		 if(!txtquantitepaquet.getText().equals(""))
						        		 {
						        			QuantitePaquet=new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement()) ;
						        		 }
						        		 if(!txtquantite.getText().equals(""))
						        		 {
						        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
						        		 }
						        		 
						        		 // Verifier le stock offre pour la gratuité du the promotion
						        		 if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) && !sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM))
						        		 {
						        			 if(stockpf.getStockOffre()==null)
						        			 {
						        				
						        				 stocktmp=stockpftmp.getStockOffre().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
									        	 stockpftmp.setStockOffre(stocktmp); 
									        	 stockpfDAO.edit(stockpftmp);
									        	 
						        				 JOptionPane.showMessageDialog(null, "Stock Offre de "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
								        		 return;
						        			 }
							        		 
							            	 if(stockpf.getStockOffre().compareTo(QuantiteUnit.add(QuantitePaquet))<0)
								        	 {
							            		 
							            		 stocktmp=stockpftmp.getStockOffre().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
									        	 stockpftmp.setStockOffre(stocktmp); 
									        	 stockpfDAO.edit(stockpftmp);
							            		 
								        		 JOptionPane.showMessageDialog(null, "Stck Offre de "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
								        		 return;
								        	 }else
								        	 {
								        		 
								        		
							        	 
							        	 DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(tableArticle.getSelectedRow());
							        	 DetailFacturePF detailFacture= listDetailFacturePF.get(tableArticle.getSelectedRow());
							        	 
								        	    
								          
							        	  
							        	  // Modifier Transfer Stock PF
									        detailTransferStockPF.setArticle(article);
									        detailTransferStockPF.setSousFamille(sousfamille);
											detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
											detailTransferStockPF.setMagasinDestination(magasin);
											detailTransferStockPF.setPrixUnitaire(BigDecimal.ZERO);
											detailTransferStockPF.setQuantite(QuantiteUnit.add(QuantitePaquet));		
											detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
											detailTransferStockPFdao.edit(detailTransferStockPF);
											listDetailTransferProduitFini.set(tableArticle.getSelectedRow(), detailTransferStockPF);
											
											
							        	 
								          detailFacture.setArticle(article);
								          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
								          DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , clientpf.getId());
	  	  		  				 			
		  	  		  				 		
		  	  		  				 		if(detailprixarticle!=null)
		  	  		  				 			{
		  	  		  				 		detailFacture.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
		  	  		  				 				 
		  	  		  				 			}else
		  	  		  				 			{
		  	  		  				 				
		  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId());
			
		  	  		  				 			if(detailprixarticleTmp!=null)	
		  	  		  				 			{
		  	  		  				 			detailFacture.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
		  	  		  				 				
		  	  		  				 			 
		  	  		  				 				
		  	  		  				 			}else
		  	  		  				 			{
		  	  		  				 				
		  	  		  				 			detailFacture.setPrixUnitaire(BigDecimal.ZERO);
		  	  		  				 			
		  	  		  				 			}
		  	  		  				 				
		  	  		  				 				
		  	  		  				 				
		  	  		  				 				
		  	  		  				 			}
		  	  		  				 	detailFacture.setReduction(new BigDecimal(100));
								           detailFacture.setMontantHT(BigDecimal.ZERO);
									       detailFacture.setMontantTVA(BigDecimal.ZERO);
									       detailFacture.setSousFamille(sousfamille);
									      detailFacture.setMontantTTC(BigDecimal.ZERO);  
									      detailFacture.setBrutHT(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
									      detailFacture.setRemiseCommerciale(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
									         
								          // detailFacture.setTypeVente(typevente);
								          
								           detailFacture.setFacturePF(facturePF);
								       //    detailFacture.setDateCreation(new Date());
								           
								           detailFacture.setUtilisateur(utilisateur);	
								           
								           stock=stockpf.getStockOffre().subtract(QuantiteUnit.add(QuantitePaquet));
								           stockpf.setStockOffre (stock);
								           listStockPF.set(tableArticle.getSelectedRow(), stockpf);
								           stockpfDAO.edit(stockpf);
								           listDetailFacturePF.set(tableArticle.getSelectedRow(), detailFacture);
								          detailFacturePfdao.edit(detailFacture);
								          afficher_tableDetailFacturePF(listDetailFacturePF);
								          int i=0;
									        BigDecimal montanttotal=BigDecimal.ZERO;
									        BigDecimal sommequantite=BigDecimal.ZERO;
									        BigDecimal montanttotalHT=BigDecimal.ZERO;
									        BigDecimal montanttotalTVA=BigDecimal.ZERO;
									        BigDecimal lamarge=BigDecimal.ZERO;
									        BigDecimal compteur=BigDecimal.ZERO;
									        while(i<listDetailFacturePF.size())
									        {
									        	 DetailFacturePF detailFacturePF=listDetailFacturePF.get(i);
									        	  StockPF stockpfTmp=stockpfDAO.findStockByMagasinPFBySousFamille(detailFacturePF.getArticle().getId(),magasin.getId(),detailFacturePF.getSousFamille().getId());
										          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
										          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
										          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
										          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
										          if(!detailFacturePF.getPrixUnitaire().setScale(2,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP)))
										          {
										        	  compteur=compteur.add(BigDecimal.ONE);
										        	  lamarge=lamarge.add((detailFacturePF.getPrixUnitaire().subtract(stockpfTmp.getPrixUnitaire())).divide(detailFacturePF.getPrixUnitaire(), RoundingMode.HALF_UP));  
										          }
									            i++;
									        }
									       txttotalmontantTTC.setText(montanttotal+"");
									        txttotalquantite.setText(sommequantite+"");
									        txttotalmontantHT.setText(montanttotalHT+"");
								  			txttotalmontantTVA.setText(montanttotalTVA+"");
								  			
								  			if(compteur!=BigDecimal.ZERO)
								  			{
									  			
								  				Parametre margemax=parametredao.findByCode(Constantes.MARGE_MAX);
									        	 Parametre margemin=parametredao.findByCode(Constantes.MARGE_MIN);
									  			 if((lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemax.getValeur())>0 || (lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemin.getValeur())<0)
									  			 {
									  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
									  				labelMarge.setBackground(Color.red);
									  			 }else
									  			 {
									  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
									  				labelMarge.setBackground(Color.GREEN);
									  			 }
									  			 
									  				
									  			}
								  			initialiser();
											
								  			
							        		 
								        	 } 
						        			 
						        		 }else
						        		 {
						        			
							        		 
							            	 if(StockFinale.compareTo(QuantiteUnit.add(QuantitePaquet))<0 || stockpf.getStock().compareTo(QuantiteUnit.add(QuantitePaquet))<0 )
								        	 {
							            		 
							            		 stocktmp=stockpftmp.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
									        	 stockpftmp.setStock(stocktmp);  
									        	 stockpfDAO.edit(stockpftmp);
								        		 JOptionPane.showMessageDialog(null, "Stock "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
								        		 return;
								        	 }else
								        	 {
								        		 
								        		
							        	 
							        	 DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(tableArticle.getSelectedRow());
							        	 DetailFacturePF detailFacture= listDetailFacturePF.get(tableArticle.getSelectedRow());
							        	 
								        	  
								          
							        	  
							        	  // Modifier Transfer Stock PF
									        detailTransferStockPF.setArticle(article);
									        detailTransferStockPF.setSousFamille(sousfamille);
											detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
											detailTransferStockPF.setMagasinDestination(magasin);
											detailTransferStockPF.setPrixUnitaire(BigDecimal.ZERO);
											detailTransferStockPF.setQuantite(QuantiteUnit.add(QuantitePaquet));		
											detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
											detailTransferStockPFdao.edit(detailTransferStockPF);
											listDetailTransferProduitFini.set(tableArticle.getSelectedRow(), detailTransferStockPF);
											
											
							        	 
								          detailFacture.setArticle(article);
								          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
								          
								          DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , clientpf.getId());
	  	  		  				 			
		  	  		  				 		
		  	  		  				 		if(detailprixarticle!=null)
		  	  		  				 			{
		  	  		  				 		detailFacture.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
		  	  		  				 				 
		  	  		  				 			}else
		  	  		  				 			{
		  	  		  				 				
		  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId());
			
		  	  		  				 			if(detailprixarticleTmp!=null)	
		  	  		  				 			{
		  	  		  				 			detailFacture.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
		  	  		  				 				
		  	  		  				 			 
		  	  		  				 				
		  	  		  				 			}else
		  	  		  				 			{
		  	  		  				 				
		  	  		  				 			detailFacture.setPrixUnitaire(BigDecimal.ZERO);
		  	  		  				 			
		  	  		  				 			}
		  	  		  				 				
		  	  		  				 				
		  	  		  				 				
		  	  		  				 				
		  	  		  				 			}
		  	  		  				 		
		  	  		  				  detailFacture.setReduction(new BigDecimal(100));  
		  	  		  				  
		  	  		  			detailFacture.setMontantHT(BigDecimal.ZERO);
							       detailFacture.setMontantTVA(BigDecimal.ZERO);
							       detailFacture.setSousFamille(sousfamille);
							      detailFacture.setMontantTTC(BigDecimal.ZERO);  
							      detailFacture.setBrutHT(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
							      detailFacture.setRemiseCommerciale(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
									       
									         
								          // detailFacture.setTypeVente(typevente);
								          
								           detailFacture.setFacturePF(facturePF);
								       //    detailFacture.setDateCreation(new Date());
								           
								           detailFacture.setUtilisateur(utilisateur);	
								           
								           stock=stockpf.getStock().subtract(QuantiteUnit.add(QuantitePaquet));
								           stockpf.setStock(stock);
								           stockpfDAO.edit(stockpf);
								           listStockPF.set(tableArticle.getSelectedRow(), stockpf);
								           listDetailFacturePF.set(tableArticle.getSelectedRow(), detailFacture);
								          detailFacturePfdao.edit(detailFacture);
								          afficher_tableDetailFacturePF(listDetailFacturePF);
								          int i=0;
									        BigDecimal montanttotal=BigDecimal.ZERO;
									        BigDecimal sommequantite=BigDecimal.ZERO;
									        BigDecimal montanttotalHT=BigDecimal.ZERO;
									        BigDecimal montanttotalTVA=BigDecimal.ZERO;
									        BigDecimal lamarge=BigDecimal.ZERO;
									        BigDecimal compteur=BigDecimal.ZERO;
									        while(i<listDetailFacturePF.size())
									        {
									        	 DetailFacturePF detailFacturePF=listDetailFacturePF.get(i);
									        	  StockPF stockpfTmp=stockpfDAO.findStockByMagasinPFBySousFamille(detailFacturePF.getArticle().getId(),magasin.getId(),detailFacturePF.getSousFamille().getId());
										          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
										          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
										          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
										          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
										          if(!detailFacturePF.getPrixUnitaire().setScale(2,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP)))
										          {
										        	  compteur=compteur.add(BigDecimal.ONE);
										        	  lamarge=lamarge.add((detailFacturePF.getPrixUnitaire().subtract(stockpfTmp.getPrixUnitaire())).divide(detailFacturePF.getPrixUnitaire(), RoundingMode.HALF_UP));  
										          }
									            i++;
									        }
									       txttotalmontantTTC.setText(montanttotal+"");
									        txttotalquantite.setText(sommequantite+"");
									        txttotalmontantHT.setText(montanttotalHT+"");
								  			txttotalmontantTVA.setText(montanttotalTVA+"");
								  			
								  			if(compteur!=BigDecimal.ZERO)
								  			{
									  			
								  				Parametre margemax=parametredao.findByCode(Constantes.MARGE_MAX);
									        	 Parametre margemin=parametredao.findByCode(Constantes.MARGE_MIN);
									  			 if((lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemax.getValeur())>0 || (lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemin.getValeur())<0)
									  			 {
									  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
									  				labelMarge.setBackground(Color.red);
									  			 }else
									  			 {
									  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
									  				labelMarge.setBackground(Color.GREEN);
									  			 }
									  			 
									  				
									  			}
								  			initialiser();
											
								  			
							        		 
								        	 }
						        		 }
						        	
						        		 
						        	 }else
						        	 {
						        		 
						        		 if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) && !sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM))
						        		 {
						        			 stocktmp=stockpftmp.getStockOffre().subtract (listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
								        	 stockpftmp.setStockOffre(stocktmp); 
								        	 stockpfDAO.edit(stockpftmp);
						        		 }else
						        		 {
						        			 stocktmp=stockpftmp.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
								        	 stockpftmp.setStock(stocktmp);  
								        	 stockpfDAO.edit(stockpftmp);
						        		 }
						        		 
						        		 
						        		 JOptionPane.showMessageDialog(null,  "Stock "+article.getLiblle()+" introuvable dans le magasin "+magasin.getLibelle(),"Attention",JOptionPane.ERROR_MESSAGE);
						        	 }
						        	 
						        	
										
							       }else
							       {
							    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							    	   return;
							       }
							       
									
						        	 
						         }
							}
				   			
		   				
		   				
		   				
		   			}else
		   			{
		   				

				   		BigDecimal stock=BigDecimal.ZERO;
				   		BigDecimal stocktmp=BigDecimal.ZERO;
				   		BigDecimal stocktmpoffretherres=BigDecimal.ZERO;
				   		BigDecimal stocktmpoffreverres=BigDecimal.ZERO;
				   		BigDecimal stocktmpoffreArticlePromo=BigDecimal.ZERO;
				   		BigDecimal marge=BigDecimal.ZERO;
				   		BigDecimal QuantiteUnit=BigDecimal.ZERO;
						BigDecimal QuantitePaquet=BigDecimal.ZERO;
						
				   		if(tableArticle.getSelectedRow()!=-1)
				   		{
				   			boolean trouve=false;
							
							if(comboArticle.getSelectedItem().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtcodearticle.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtquantite.getText().equals("") && txtquantitepaquet.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)) ){
								
								
								JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							
						}else if(!txtquantitepaquet.getText().equals("") && ((new BigDecimal(txtquantitepaquet.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepaquet.getText()).compareTo(BigDecimal.ZERO)<0)) ){
							
						JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							}else if(txtPrix.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Erreur de prix","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtmontant.getText().equals("") && checkttc.isSelected()==false)
							{
								JOptionPane.showMessageDialog(null, "Erreur de Montant","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else
							{
								
								
								Articles articleOffre=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
					       		if(articleOffre!=null)
					       		{
					       			if(articleOffre.getCodeArticle().equals(Constantes.OFFRE_THERRES)  || articleOffre.getCodeArticle().equals(Constantes.OFFRE_VERRES))
						       		{
						       			
						       			JOptionPane.showMessageDialog(null, "Veuillez Selectionner Article Promo SVP","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
						       			
						       			
						       		}
					       		}
								
								
								
								
								
								
								 Articles article=mapArticle.get(comboArticle.getSelectedItem());
						        
						         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
						         SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
						         if(article.getCentreCout1()!=null)
						         {
						        	 BigDecimal quantite = new BigDecimal(txtquantite.getText()).setScale(3);
						        	 BigDecimal grammage = quantite.multiply(new BigDecimal(1000));
						        	 
						        	 
						        	if(article.getCentreCout1().equals(BigDecimal.ONE)) 
						        	{
						        		if(grammage.compareTo(BigDecimal.ZERO)!=0)
						        		{
						        			JOptionPane.showMessageDialog(null, "Grammage  "+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
						        			return;
						        		}
						        		
						        	}else
						        	{
						        		BigDecimal result=grammage.divide(article.getCentreCout1(), 6, RoundingMode.FLOOR);
						        	
						        		BigDecimal rest=result.subtract(result.setScale(0, RoundingMode.FLOOR)).movePointRight(result.scale());
						        	
						        		if(rest.compareTo(BigDecimal.ZERO)!=0)
						        		{
						        			JOptionPane.showMessageDialog(null, "Grammage d'"+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
						        			return;	
						        		}
						        		
						        	}
						        	 
						         }
						         
						        
						         
						         for(int i=0;i<listDetailFacturePF.size();i++)
						         {
						        	 if(i!=tableArticle.getSelectedRow())
						        	 {
						        		 
						        		 DetailFacturePF detailFacturePF =listDetailFacturePF.get(i);
							        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()) )
							        	 {
							        		 if(!detailFacturePF.getPrixUnitaire().setScale(2 , RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP)))
							        		 {
							        			 
							        		 trouve=true;
							        		 
							        		 }
							        		 
							        		 }
							        	  
						        	 }
						        	 
						        	 
						         }
						         
						         if(trouve==false)
						         {
						        	 
						        	 
						        	/////////////////////////////////////// Traitement promotion ////////////////////////////////////////////
						        	 
	                                boolean offfrepromo =false ; 
						        	 
						        	 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5()!=null)
			  				 			{
			  				 			// centrecout5 = article promotion ou non
			  				 				if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getSousFamille(). getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM))
			  				 				{
			  				 					if(article.getCentreCout5()!=null)
			  				 					{
			  				 						if(!article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)))
			  				 						{
			  				 							
			  				 							JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			  				 							
			  				 							return;
			  				 							
			  				 						}else if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) &&  sousfamille. getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM) )
				  				 						
				  				 						{
				  				 							
	                                                       JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  				 							
				  				 							return;
				  				 							
				  				 						}
			  				 						
			  				 						else
			  				 						{
			  				 							
			  				 								
			  				 						offfrepromo=true;
			  				 							
			  				 							
			  				 						}
			  				 						
			  				 						
			  				 					}else
			  				 					{
			  				 						JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		  				 							
		  				 							return;
			  				 					}
			  				 					 
			  				 					
			  				 				}else 	if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && magasin.getCode().equals(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING_PF) )
			  				 				{
			  				 					if(article.getCentreCout5()!=null)
			  				 					{
			  				 						if(!article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)))
			  				 						{
			  				 							
			  				 							JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			  				 							
			  				 							return;
			  				 							
			  				 						}else
			  				 						
			  				 						{
			  				 							
			  				 					
			  				 									
			  				 									offfrepromo=true;
			  				 									
				  				 				
			  				 							
			  				 							
			  				 						}
			  				 						
			  				 						
			  				 					}else
			  				 					{
			  				 						JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		  				 							
		  				 							return;
			  				 					}
			  				 					 
			  				 					
			  				 				}
			  				 				
			  				 						
						        	
			  				 			}
						        	 
						          	 if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) && !sousfamille. getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM))
						        	 {
						        		 
						        		 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && listDetailFacturePF.get(tableArticle.getSelectedRow()).getSousFamille(). getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM) ) 
						        		 {
						        			 
						        				JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	  				 							
	  				 							return; 
						        			 
						        			 
						        		 }else if(!listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) )
						        		 {
						        			 JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	  				 							
	  				 							return; 
						        		 }
						        		 
						        		 
						        	 }else if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && magasin.getCode().equals(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING_PF))
						        	 {
						        		 
						        		 if(!listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  ) 
						        		 {
						        			 
						        				JOptionPane.showMessageDialog(null, "Les Articles promo Modifier par Autres Articles promo , Veuillez le Supprimer ou Modifier par un autre article promo SvP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	  				 							
	  				 							return; 
						        			 
						        			 
						        		 } 
						        		 
						        		 
						        	 }
						        	 
						        	 
						        	 
						        	 StockPF stockpfOffreTherres=null;
						        	 StockPF stockpfOffreVerres=null;
						        	 StockPF stockpfOffreArticlepromo=null;
						        	 boolean articlepromoexiste=false;
						        	 
						        	 
						        	 if(offfrepromo==true)
						        		 
						        	 {
						        		 
						        		 if(txtquantitetherres.getText().equals("") && txtquantiteverres.getText().equals("") && txtquantitepromo.getText().equals(""))
		  				 					{
		  				 						JOptionPane.showMessageDialog(null, "Veuillez saisir quantite des Offres SVP","Erreur",JOptionPane.ERROR_MESSAGE);
		  										return;
		  				 					}
						        		 
						        		 
						        		 
						        		 if(!txtquantitetherres.getText().equals("") )
		  				 					{
						        			 
						        			 if( ((new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitetherres.getText()).compareTo(BigDecimal.ZERO)<0)))
						        			 {
						        				 
						        				 JOptionPane.showMessageDialog(null, "la quantite des Therres doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			  										return;
			  										
						        			 }
		  				 						
		  				 						
		  				 					}
						        		 
						        		 
						        		 if(!txtquantiteverres.getText().equals("") )
		  				 					{
						        			 if( ((new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantiteverres.getText()).compareTo(BigDecimal.ZERO)<0)))
						        			 {
						        				 
						        					JOptionPane.showMessageDialog(null, "la quantite des Verres doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			  										return; 
						        				 
						        			 }
						        			 
		  				 					
		  				 						
		  				 					}
						        		 
						        		 
						        		if(!txtquantitepromo.getText().equals("") )
		  				 					{
						        			
						        			if(((new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)<0)))
						        			{

		  				 						JOptionPane.showMessageDialog(null, "la quantite de'Article Promo doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
		  										return;	
						        				
						        			}
						        			
		  				 						
		  				 					}
		  				 						if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
		  				 						{
		  				 							
		  				 							if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
		  				 							{
		  				 							
		  				 								if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
		  				 								{
		  				 									
		  				 									
		  				 								  //////////////////////////////////////// Therres /////////////////////////////
											        		  stockpfOffreTherres =listStockPF.get(tableArticle.getSelectedRow()+1);
											        		 stocktmpoffretherres=stockpfOffreTherres.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
											        		 stockpfOffreTherres.setStock(stocktmpoffretherres);
											        		 stockpfDAO.edit(stockpfOffreTherres);
											        		
											        		 StockPF stockpfTherres=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getId(), magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getSousFamille().getId());

											        		 if(!txtquantitetherres.getText().equals(""))
											        		 {
											        			 if(stockfinaleTherres.add(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite()).compareTo(new BigDecimal(txtquantitetherres.getText()))<0 || stockpfTherres.getStock().compareTo(new BigDecimal(txtquantitetherres.getText()))<0)
											        				{
											        					
																		
												        				stocktmpoffretherres=stockpfOffreTherres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
																		  stockpfOffreTherres.setStock(stocktmpoffretherres); 
																		  stockpfDAO.edit(stockpfOffreTherres);
																		 
											        					
											        					JOptionPane.showMessageDialog(null, "Stock "+listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
														        		 return;
											        					
											        				}
											        		 }
											        		 
											        	
		  				 									
		  				 									
		  				 								}
		  				 								
		  				 								
		  				 								if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
		  				 								{
		  				 									
		  				 								 //////////////////////////////////////////// Verres /////////////////////////////////
											        		 
											        		  stockpfOffreVerres =listStockPF.get(tableArticle.getSelectedRow()+1);
											        		 stocktmpoffreverres=stockpfOffreVerres.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
											        		 stockpfOffreVerres.setStock(stocktmpoffreverres);
											        		 stockpfDAO.edit(stockpfOffreVerres);
											        	
											        		 StockPF stockpfVerres=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getId(), magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getSousFamille().getId());
											        		 
											        		 if(!txtquantiteverres.getText().equals(""))
											        		 {
											        			 if(stockfinaleVerres.add(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite()).compareTo(new BigDecimal(txtquantiteverres.getText()))<0 || stockpfVerres.getStock().compareTo(new BigDecimal(txtquantiteverres.getText()))<0)
											        				{
											        					
																		
												        				stocktmpoffreverres=stockpfOffreVerres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
																		  stockpfOffreVerres.setStock(stocktmpoffreverres); 
																		  stockpfDAO.edit(stockpfOffreVerres);
																		 
											        					
											        					JOptionPane.showMessageDialog(null, "Stock "+listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
														        		 return;
											        					
											        				} 
											        			 
											        		 }
											        	
		  				 									
		  				 									
		  				 								}
		  				 								
		  				 								if (listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
		  				 								{
		  				 									
		  				 								
		  				 						  		 //////////////////////////////////////////// Article promo /////////////////////////////////
											        		 
											        		 
											        		 
											        		 
											        		 
											        		 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
											        		 {
											        			 
											        			 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle()+"_PFO"))
												        		 {
											        				 articlepromoexiste=true;
												        			
												        			
											        				 
													        		  stockpfOffreArticlepromo =listStockPF.get(tableArticle.getSelectedRow()+1);
													        		 stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
													        		 stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo);
													        		 stockpfDAO.edit(stockpfOffreArticlepromo);
													        		 
													        		 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
													        		 if(articlespromo!=null)
													        		 {
													        			if( articlespromo.getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle()))
													        					{
													        				
													        				 StockPF stockpfArticlePromo=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getId(), magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getSousFamille().getId());
															        		 
													        				 if(!txtquantitepromo.getText().equals(""))
													        				 {
													        					 
																        		 if(stockfinaleArticlePromo.add(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite()).compareTo(new BigDecimal(txtquantitepromo.getText()))<0 || stockpfArticlePromo.getStock().compareTo(new BigDecimal(txtquantitepromo.getText()))<0)
															        				{
															        					
																						
																        				stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
																						  stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo); 
																						  stockpfDAO.edit(stockpfOffreArticlepromo);
																						 
															        					
															        					JOptionPane.showMessageDialog(null, "Stock "+listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
																		        		 return;
															        					
															        				}  
													        					 
													        				 }
													        				
													        				
													        				
													        				
													        				
													        				
													        					}else
													        					{
													        						
													        						 StockPF stockpfArticlePromo=stockpfDAO.findStockByMagasinPFBySousFamille(articlespromo.getId(), magasin.getId(),sousfamille.getId());
																	        		 
													        						 if(!txtquantitepromo.getText().equals(""))
															        				 {
													        							 if(stockfinaleArticlePromo.compareTo(new BigDecimal(txtquantitepromo.getText()))<0 || stockpfArticlePromo.getStock().compareTo(new BigDecimal(txtquantitepromo.getText()))<0)
																	        				{
																	        					
																								
																		        				stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
																								  stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo); 
																								  stockpfDAO.edit(stockpfOffreArticlepromo);
																								 
																	        					
																	        					JOptionPane.showMessageDialog(null, "Stock "+articlespromo.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
																				        		 return;
																	        					
																	        				}
													        							 
															        				 }
																	        	 
													        						
													        						
													        						
													        					}
													        			 
													        		
													        			 
													        		 }
													        	
													        	 
												        			 
												        		 }
											        			 
											        		 }
		  				 									
		  				 									
		  				 									
		  				 									
		  				 									
		  				 									
		  				 									
		  				 								}
		  				 								
		  				 								
		  				 								
		  				 							}
		  				 						  
									        		
		  				 						}
		  				 						
		  				 						
		  				 						if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+2)
		  				 						{
		  				 							if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2)!=null)
		  				 							{
		  				 								
		  				 								if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
		  				 								{
		  				 									
		  				 								 //////////////////////////////////////// Therres /////////////////////////////
											        		  stockpfOffreTherres =listStockPF.get(tableArticle.getSelectedRow()+2);
											        		 stocktmpoffretherres=stockpfOffreTherres.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite());
											        		 stockpfOffreTherres.setStock(stocktmpoffretherres);
											        		 stockpfDAO.edit(stockpfOffreTherres);
											        		
											        		 StockPF stockpfTherres=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getId(), magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getSousFamille().getId());

											        		 
											        		 if(!txtquantitetherres.getText().equals(""))
											        		 {
											        			 if(stockfinaleTherres.add(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite()).compareTo(new BigDecimal(txtquantitetherres.getText()))<0 || stockpfTherres.getStock().compareTo(new BigDecimal(txtquantitetherres.getText()))<0)
											        				{
											        					
																		
												        				stocktmpoffretherres=stockpfOffreTherres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite());
																		  stockpfOffreTherres.setStock(stocktmpoffretherres); 
																		  stockpfDAO.edit(stockpfOffreTherres);
																		 
											        					
											        					JOptionPane.showMessageDialog(null, "Stock "+listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
														        		 return;
											        					
											        				} 
											        			 
											        		 }
											        		
		  				 									
		  				 									
		  				 								}
		  				 								
		  				 								if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
		  				 								{
		  				 								 //////////////////////////////////////////// Verres /////////////////////////////////
											        		 
											        		  stockpfOffreVerres =listStockPF.get(tableArticle.getSelectedRow()+2);
											        		 stocktmpoffreverres=stockpfOffreVerres.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite());
											        		 stockpfOffreVerres.setStock(stocktmpoffreverres);
											        		 stockpfDAO.edit(stockpfOffreVerres);
											        	
											        		 StockPF stockpfVerres=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getId(), magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getSousFamille().getId());
											        		 
											        		 if(!txtquantiteverres.getText().equals(""))
											        		 {
											        			 
											        			 if(stockfinaleVerres.add(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite()).compareTo(new BigDecimal(txtquantiteverres.getText()))<0 || stockpfVerres.getStock().compareTo(new BigDecimal(txtquantiteverres.getText()))<0)
											        				{
											        					
																		
												        				stocktmpoffreverres=stockpfOffreVerres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite());
																		  stockpfOffreVerres.setStock(stocktmpoffreverres); 
																		  stockpfDAO.edit(stockpfOffreVerres);
																		 
											        					
											        					JOptionPane.showMessageDialog(null, "Stock "+listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
														        		 return;
											        					
											        				} 
											        		 }
											        		 
											        	
		  				 									
		  				 									
		  				 								}
										        		 
                                            if(!listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
                                            {
                                            	

								        		 //////////////////////////////////////////// Article promo /////////////////////////////////
								        		 
								        		 
								        		 
								        		 
								        		 
								        		 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2)!=null)
								        		 {
								        			 
								        			 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle()+"_PFO"))
									        		 {
								        				 articlepromoexiste=true;
									        			
									        			
								        				 
										        		  stockpfOffreArticlepromo =listStockPF.get(tableArticle.getSelectedRow()+2);
										        		 stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite());
										        		 stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo);
										        		 stockpfDAO.edit(stockpfOffreArticlepromo);
										        		 
										        		 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
										        		 if(articlespromo!=null)
										        		 {
										        			if( articlespromo.getCodeArticle().equals(listStockPF.get(tableArticle.getSelectedRow()+2).getArticles().getCodeArticle()))
										        					{
										        				
										        				 StockPF stockpfArticlePromo=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getId(), magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getSousFamille().getId());
												        		 
										        				 if(!txtquantitepromo.getText().equals(""))
										        				 {
										        					 if(stockfinaleArticlePromo.add(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite()).compareTo(new BigDecimal(txtquantitepromo.getText()))<0 || stockpfArticlePromo.getStock().compareTo(new BigDecimal(txtquantitepromo.getText()))<0)
												        				{
												        					
																			
													        				stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite());
																			  stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo); 
																			  stockpfDAO.edit(stockpfOffreArticlepromo);
																			 
												        					
												        					JOptionPane.showMessageDialog(null, "Stock "+listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
															        		 return;
												        					
												        				}  
										        					 
										        				 }
										        				 
												        		
										        				
										        				
										        				
										        				
										        				
										        					}else
										        					{
										        						
										        						 StockPF stockpfArticlePromo=stockpfDAO.findStockByMagasinPFBySousFamille(articlespromo.getId(), magasin.getId(),sousfamille.getId());
														        		 
										        						 if(!txtquantitepromo.getText().equals(""))
										        						 {
										        							 if(stockfinaleArticlePromo.compareTo(new BigDecimal(txtquantitepromo.getText()))<0 || stockpfArticlePromo.getStock().compareTo(new BigDecimal(txtquantitepromo.getText()))<0)
														        				{
														        					
																					
															        				stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite());
																					  stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo); 
																					  stockpfDAO.edit(stockpfOffreArticlepromo);
																					 
														        					
														        					JOptionPane.showMessageDialog(null, "Stock "+articlespromo.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
																	        		 return;
														        					
														        				}  
										        							 
										        						 }
														        	
										        						
										        						
										        						
										        					}
										        			 
										        		
										        			 
										        		 }
										        	
										        	 
									        			 
									        		 }
								        			 
								        		 }
	
                                            }
										        		 
										        		 
			  				 							
		  				 								
		  				 								
		  				 							}
		  				 							
		  				 			
		  				 							
		  				 						}
		  				 						
		  				 						
		  				 						
		  				 						if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+3)
		  				 						{
		  				 							if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3)!=null)
		  				 							{
		  				 								
		  				 								if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
		  				 								{
		  				 									
		  				 								    //////////////////////////////////////// Therres /////////////////////////////
											        		  stockpfOffreTherres =listStockPF.get(tableArticle.getSelectedRow()+3);
											        		 stocktmpoffretherres=stockpfOffreTherres.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite());
											        		 stockpfOffreTherres.setStock(stocktmpoffretherres);
											        		 stockpfDAO.edit(stockpfOffreTherres);
											        		
											        		 StockPF stockpfTherres=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getId(), magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getSousFamille().getId());

											        		 
											        		 if(!txtquantitetherres.getText().equals(""))
											        		 {
											        			 if(stockfinaleTherres.add(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite()).compareTo(new BigDecimal(txtquantitetherres.getText()))<0 || stockpfTherres.getStock().compareTo(new BigDecimal(txtquantitetherres.getText()))<0)
											        				{
											        					
																		
												        				stocktmpoffretherres=stockpfOffreTherres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite());
																		  stockpfOffreTherres.setStock(stocktmpoffretherres); 
																		  stockpfDAO.edit(stockpfOffreTherres);
																		 
											        					
											        					JOptionPane.showMessageDialog(null, "Stock "+listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
														        		 return;
											        					
											        				}
											        			 
											        		 }
											        	
		  				 									
		  				 									
		  				 								}
		  				 								
		  				 								if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
		  				 								{
		  				 									
		  				 								 //////////////////////////////////////////// Verres /////////////////////////////////
											        		 
											        		  stockpfOffreVerres =listStockPF.get(tableArticle.getSelectedRow()+3);
											        		 stocktmpoffreverres=stockpfOffreVerres.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite());
											        		 stockpfOffreVerres.setStock(stocktmpoffreverres);
											        		 stockpfDAO.edit(stockpfOffreVerres);
											        	
											        		 StockPF stockpfVerres=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getId(), magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getSousFamille().getId());
											        		 
											        		 if(!txtquantiteverres.getText().equals(""))
											        		 {
											        			 if(stockfinaleVerres.add(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite()).compareTo(new BigDecimal(txtquantiteverres.getText()))<0 || stockpfVerres.getStock().compareTo(new BigDecimal(txtquantiteverres.getText()))<0)
											        				{
											        					
																		
												        				stocktmpoffreverres=stockpfOffreVerres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite());
																		  stockpfOffreVerres.setStock(stocktmpoffreverres); 
																		  stockpfDAO.edit(stockpfOffreVerres);
																		 
											        					
											        					JOptionPane.showMessageDialog(null, "Stock "+listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
														        		 return;
											        					
											        				}
											        			 
											        		 }
											        	
		  				 									
		  				 								}
										        		 
										        if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
										        {
										        	
										        	
										     		 //////////////////////////////////////////// Article promo /////////////////////////////////
									        		 
									        		 
									        		 
									        		 if(listStockPF.get(tableArticle.getSelectedRow()+3)!=null)
									        		 {
									        			 
									        			 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle()+"_PFO"))
										        		 {
									        				 articlepromoexiste=true;
										        			
										        			
									        				 
											        		  stockpfOffreArticlepromo =listStockPF.get(tableArticle.getSelectedRow()+3);
											        		 stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite());
											        		 stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo);
											        		 stockpfDAO.edit(stockpfOffreArticlepromo);
											        		 
											        		 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
											        		 if(articlespromo!=null)
											        		 {
											        			if( articlespromo.getCodeArticle().equals(listStockPF.get(tableArticle.getSelectedRow()+3).getArticles().getCodeArticle()))
											        					{
											        				
											        				 StockPF stockpfArticlePromo=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getId(), magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getSousFamille().getId());
													        		 if(!txtquantitepromo.getText().equals(""))
													        		 {
													        			 if(stockfinaleArticlePromo.add(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite()).compareTo(new BigDecimal(txtquantitepromo.getText()))<0 || stockpfArticlePromo.getStock().compareTo(new BigDecimal(txtquantitepromo.getText()))<0)
													        				{
													        					
																				
														        				stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite());
																				  stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo); 
																				  stockpfDAO.edit(stockpfOffreArticlepromo);
																				 
													        					
													        					JOptionPane.showMessageDialog(null, "Stock "+listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
																        		 return;
													        					
													        				}  
													        			 
													        		 }
													        		
											        				
											        				
											        				
											        				
											        				
											        					}else
											        					{
											        						
											        						 StockPF stockpfArticlePromo=stockpfDAO.findStockByMagasinPFBySousFamille(articlespromo.getId(), magasin.getId(),sousfamille.getId());
											        						 if(!txtquantitepromo.getText().equals(""))
															        		 {
											        							 
											        							 if(stockfinaleArticlePromo.compareTo(new BigDecimal(txtquantitepromo.getText()))<0 || stockpfArticlePromo.getStock().compareTo(new BigDecimal(txtquantitepromo.getText()))<0)
															        				{
															        					
																						
																        				stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite());
																						  stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo); 
																						  stockpfDAO.edit(stockpfOffreArticlepromo);
																						 
															        					
															        					JOptionPane.showMessageDialog(null, "Stock "+articlespromo.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
																		        		 return;
															        					
															        				} 
															        		 }
															        
											        						
											        						
											        						
											        					}
											        			 
											        		 }
											        	
											        	 
										        			 
										        		 }
									        			 
									        		 }
										        	
										        	
										        	
										        }
										        		 
										        		 
										   
			  				 							
		  				 								
		  				 								
		  				 							}
		  				 							
		  				 							
		  				 					
		  				 							
		  				 						}
		  				 						 
						        	 }
						        	 
						        	 //////////////////////////////////////////////////////////////////////////////////////////////////////////////
						        	 
						        	 
						        	 StockPF stockpftmp =listStockPF.get(tableArticle.getSelectedRow());
						        	
									
									 stocktmp=stockpftmp.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
									 stockpftmp.setStock(stocktmp);
									  stockpfDAO.edit(stockpftmp);
									 
						        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasin.getId(),sousfamille.getId());
						        	 if(stockpf!=null)
						        	 {
						        		 
						        		 if(!txtquantitepaquet.getText().equals(""))
						        		 {
						        			QuantitePaquet=new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement()) ;
						        		 }
						        		 if(!txtquantite.getText().equals(""))
						        		 {
						        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
						        		 }
						        		 
						            	 if(StockFinale.compareTo(QuantiteUnit.add(QuantitePaquet))<0 || stockpf.getStock().compareTo(QuantiteUnit.add(QuantitePaquet))<0)
							        	 {
						            		 
						            		 stocktmp=stockpftmp.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
											 stockpftmp.setStock(stocktmp);
											  stockpfDAO.edit(stockpftmp);
							        		 JOptionPane.showMessageDialog(null, "Stock "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
							        		 return;
							        	 }else
							        	 {
							        		 
							        		 marge=new BigDecimal(txtPrix.getText()).subtract(stockpf.getPrixUnitaire()).divide(new BigDecimal(txtPrix.getText()), 6, RoundingMode.HALF_UP);
					        				 
							        		 marge=marge.multiply(new BigDecimal(100));
								        	 Parametre margemax=parametredao.findByCode(Constantes.MARGE_MAX);
								        	 Parametre margemin=parametredao.findByCode(Constantes.MARGE_MIN);
								        	 if(marge.compareTo(margemax.getValeur())>0 || marge.compareTo(margemin.getValeur())<0)
								        	 {
								        		 
								        		 int reponse = JOptionPane.showConfirmDialog(null, " Erreur de la marge Vous voulez vraiment valider le prix  ?", 
															"Satisfaction", JOptionPane.YES_NO_OPTION);
													 
													if(reponse == JOptionPane.YES_OPTION )
														
														
													{
														
														
													}else
													{
														
									            		 stocktmp=stockpftmp.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
														 stockpftmp.setStock(stocktmp);
														  stockpfDAO.edit(stockpftmp);
														  
														  	 if(offfrepromo==true)
												        	 {
												        		 
												        			if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
							  				 						{
							  				 							if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
							  				 							{
							  				 								
							  				 								if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
							  				 								{
							  				 									
							  				//////////////////////////////////// therres //////////////////////////////
																        		 
																        		 stocktmpoffretherres=stockpfOffreTherres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
																				  stockpfOffreTherres.setStock(stocktmpoffretherres); 
																				  stockpfDAO.edit(stockpfOffreTherres);
							  				 									
							  				 									
							  				 								}
							  				 								
							  				 								if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
							  				 								{
							  				 									
							  				 								  //////////////////////////////////// verres ///////////////////////////////////
																				  
																				  stocktmpoffreverres=stockpfOffreVerres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
																				  stockpfOffreVerres.setStock(stocktmpoffreverres); 
																				  stockpfDAO.edit(stockpfOffreVerres);
							  				 									
							  				 									
							  				 								}
							  				 								
							  				 							 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
																	        {
							  				 								 
							  				 							   //////////////////////////////////// Article Promo ///////////////////////////////////
																			  if(listStockPF.get(tableArticle.getSelectedRow()+1)!=null)
																        		 {
																				  
																				  if( listStockPF.get(tableArticle.getSelectedRow()+1).getArticles().getCodeArticle().equals(listStockPF.get(tableArticle.getSelectedRow()).getArticles().getCodeArticle()+"_PFO"))
														        					{
																					  
																					  stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
																					  stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo); 
																					  stockpfDAO.edit(stockpfOffreArticlepromo);
														        					}
																				
																				  
																				  
																        		 }
							  				 								 
							  				 								 
							  				 								 
																	        }
							  				 								
							  				 								
							  				 								
							  				 								
							  				 							}
							  				 						}
												        		 
												        		
												        			if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+2)
							  				 						{
							  				 							if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2)!=null)
							  				 							{
							  				 								
							  				 								if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
							  				 								{
							  				 									
							  				//////////////////////////////////// therres //////////////////////////////
																        		 
																        		 stocktmpoffretherres=stockpfOffreTherres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite());
																				  stockpfOffreTherres.setStock(stocktmpoffretherres); 
																				  stockpfDAO.edit(stockpfOffreTherres);
							  				 									
							  				 									
							  				 								}
							  				 								
							  				 								if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
							  				 								{
							  				 									
							  				 								  //////////////////////////////////// verres ///////////////////////////////////
																				  
																				  stocktmpoffreverres=stockpfOffreVerres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite());
																				  stockpfOffreVerres.setStock(stocktmpoffreverres); 
																				  stockpfDAO.edit(stockpfOffreVerres);
							  				 									
							  				 									
							  				 								}
							  				 								
							  				 							 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
																	        {
							  				 								 
							  				 							   //////////////////////////////////// Article Promo ///////////////////////////////////
																			  if(listStockPF.get(tableArticle.getSelectedRow()+2)!=null)
																        		 {
																				  
																				  if( listStockPF.get(tableArticle.getSelectedRow()+2).getArticles().getCodeArticle().equals(listStockPF.get(tableArticle.getSelectedRow()).getArticles().getCodeArticle()+"_PFO"))
														        					{
																					  
																					  stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite());
																					  stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo); 
																					  stockpfDAO.edit(stockpfOffreArticlepromo);
														        					}
																				
																				  
																				  
																        		 }
							  				 								 
							  				 								 
							  				 								 
																	        }
							  				 								
							  				 								
							  				 								
							  				 								
							  				 							}
							  				 						}
																
												        			
												        			if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+3)
							  				 						{
							  				 							if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3)!=null)
							  				 							{
							  				 								
							  				 								if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
							  				 								{
							  				 									
							  				//////////////////////////////////// therres //////////////////////////////
																        		 
																        		 stocktmpoffretherres=stockpfOffreTherres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite());
																				  stockpfOffreTherres.setStock(stocktmpoffretherres); 
																				  stockpfDAO.edit(stockpfOffreTherres);
							  				 									
							  				 									
							  				 								}
							  				 								
							  				 								if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
							  				 								{
							  				 									
							  				 								  //////////////////////////////////// verres ///////////////////////////////////
																				  
																				  stocktmpoffreverres=stockpfOffreVerres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite());
																				  stockpfOffreVerres.setStock(stocktmpoffreverres); 
																				  stockpfDAO.edit(stockpfOffreVerres);
							  				 									
							  				 									
							  				 								}
							  				 								
							  				 							 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
																	        {
							  				 								 
							  				 							   //////////////////////////////////// Article Promo ///////////////////////////////////
																			  if(listStockPF.get(tableArticle.getSelectedRow()+3)!=null)
																        		 {
																				  
																				  if( listStockPF.get(tableArticle.getSelectedRow()+3).getArticles().getCodeArticle().equals(listStockPF.get(tableArticle.getSelectedRow()).getArticles().getCodeArticle()+"_PFO"))
														        					{
																					  
																					  stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite());
																					  stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo); 
																					  stockpfDAO.edit(stockpfOffreArticlepromo);
														        					}
																				
																				  
																				  
																        		 }
							  				 								 
							  				 								 
							  				 								 
																	        }
							  				 								
							  				 								
							  				 								
							  				 								
							  				 							}
							  				 						}
																  
								                               
																  
															
												        		  
												        	 }
														  
														  
														 return;
													}
								        	 
								        	 }
								        	 
						        	 
						        	 DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(tableArticle.getSelectedRow());
						        	 DetailFacturePF detailFacture= listDetailFacturePF.get(tableArticle.getSelectedRow());
						        	 
						        	 if(offfrepromo==true)
						        	 {
						        		 listStockPF.remove(tableArticle.getSelectedRow());
						        		 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
										 listDetailFacturePF.remove(tableArticle.getSelectedRow());
										 detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(tableArticle.getSelectedRow()).getId());
										 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
										 
										 	sousfamille=	 mapsousfamille.get(combosousfamille.getSelectedItem());
										    article=mapArticle.get(comboArticle.getSelectedItem());
										 
										 DetailFacturePF detailFactureTmp= new DetailFacturePF();
										 
										 
										 if(!txtreduction.getText().equals(""))
								          {
											 detailFactureTmp.setReduction(new BigDecimal(txtreduction.getText()));
								          }else
								          {
								        	  detailFactureTmp.setReduction(BigDecimal.ZERO);  
								          }
							        	  
							        	 		
							        	 
										 detailFactureTmp.setArticle(article);
										 detailFactureTmp.setQuantite(QuantiteUnit.add(QuantitePaquet));
										
								     if(checkttc.isSelected()==true)
								     {

						        		  detailFacture.setPrixUnitaire(prixTTC);
								           detailFacture.setBrutHT ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
									     if(checkboxSansTva.isSelected()==true)
									     {
									    	 
									           detailFacture.setMontantTVA(BigDecimal.ZERO);

									     }else
									     {
									           detailFacture.setMontantTVA((((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));

									    	 
									     }
									       detailFacture.setSousFamille(sousfamille);
									       if(!txtreduction.getText().equals(""))
									          {
									    	   if(checkboxSansTva.isSelected()==true)
											     {
									    		   detailFacture.setRemiseCommerciale(( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP) );
									    		   detailFacture.setMontantHT( (((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );
									    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
									    		   detailFacture.setTva(BigDecimal.ZERO);
									    		   detailFacture.setMontantTTC( (((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );  

									    		   
											     }else
											     {
										    		   detailFacture.setRemiseCommerciale( (((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP) );
										    		   detailFacture.setMontantHT( (((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );
										    		   detailFacture.setMontantTVA(( ( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP) );
										    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
												       detailFacture.setMontantTTC( (( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).add( ( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).multiply(Constantes.TVA) )).setScale(6, RoundingMode.HALF_UP) );  

													    

											     }
									    	   
									          }else
									          {
									        	  if(checkboxSansTva.isSelected()==true)
												     {
										    		   detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
										    		   detailFacture.setMontantHT(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
										    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
										    		   detailFacture.setTva(BigDecimal.ZERO);
										    		   detailFacture.setMontantTTC(((((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));

												     }else
												     {
												    	 detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
											    		   detailFacture.setMontantHT(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
											    		   detailFacture.setMontantTVA(( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP) );
											    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
											        	  detailFacture.setMontantTTC(( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).add( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA) )).setScale(6, RoundingMode.HALF_UP) );

												     }
									          }  
						        	  
								     }else
								     {


						        		  detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
								           detailFacture.setBrutHT ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
								           if(checkboxSansTva.isSelected()==true)
										     {
									           detailFacture.setMontantTVA(BigDecimal.ZERO);

										     }else
										     {
										           detailFacture.setMontantTVA((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));

										     }
									       detailFacture.setSousFamille(sousfamille);
									       if(!txtreduction.getText().equals(""))
									          {
									    	   if(checkboxSansTva.isSelected()==true)
											     {
									    		   detailFacture.setRemiseCommerciale(( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP) );
									    		   detailFacture.setMontantHT( ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );
									    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
									    		   detailFacture.setTva(BigDecimal.ZERO);
									    		   detailFacture.setMontantTTC(  ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );  

									    		   
											     }else
											     {
											    	 
											    	 detailFacture.setRemiseCommerciale( ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP) );
										    		   detailFacture.setMontantHT(( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );
										    		   detailFacture.setMontantTVA( (( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP) );
										    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
										        	  detailFacture.setMontantTTC(( ( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).add( ( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).multiply(Constantes.TVA) )).setScale(6, RoundingMode.HALF_UP) );  

											     }
									    	   
									          }else
									          {
									        	  if(checkboxSansTva.isSelected()==true)
												     {
												    	 detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
												    	 detailFacture.setMontantHT((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
												    	 detailFacture.setMontantTVA(BigDecimal.ZERO);
												    	 detailFacture.setTva(BigDecimal.ZERO);
										        	  detailFacture.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));

												     }else
												     {
												    	 detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
												    	 detailFacture.setMontantHT((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
												    	 detailFacture.setMontantTVA(( (((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP) );
												    	 detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
												    	 detailFacture.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).add((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));

												     }
									         
									          }  
						        	  
								     }
								        
								          // detailFacture.setTypeVente(typevente);
								          
								     detailFactureTmp.setFacturePF(facturePF);
								       //    detailFacture.setDateCreation(new Date());
								           
								     detailFactureTmp.setUtilisateur(utilisateur);	
								    
										
										 listDetailFacturePF.add (detailFactureTmp);
										 detailFacturePfdao.add(detailFactureTmp);
										 
										 DetailTransferProduitFini detailTransferStockPFTmp=new DetailTransferProduitFini();
										 
										 // Modifier Transfer Stock PF
										 detailTransferStockPFTmp.setArticle(article);
										 detailTransferStockPFTmp.setSousFamille(sousfamille);
										 detailTransferStockPFTmp.setDateTransfer(dateChooserfacture.getDate());
										 detailTransferStockPFTmp.setMagasinDestination(magasin);
											if(checkttc.isSelected()==true)
											{
												if(!txtreduction.getText().equals(""))
										          {
													detailTransferStockPFTmp.setPrixUnitaire(prixTTC.subtract(prixTTC.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))); 	
													
										          }else
										          {
										        		detailTransferStockPFTmp.setPrixUnitaire(prixTTC); 
										          }
											
											}else
											{
												if(!txtreduction.getText().equals(""))
										          {
													detailTransferStockPFTmp.setPrixUnitaire(new BigDecimal(txtPrix.getText()).subtract(new BigDecimal(txtPrix.getText()).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
													
										          }else
										          {
										        	  
										        	  detailTransferStockPFTmp.setPrixUnitaire(new BigDecimal(txtPrix.getText())); 
										          }
													
											}
									        	
									          
											detailTransferStockPFTmp.setQuantite(QuantiteUnit.add(QuantitePaquet));		
											detailTransferStockPFTmp.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
											
											detailTransferStockPFTmp.setTransferStockPF(transferStock);
											
										 listDetailTransferProduitFini.add(detailTransferStockPFTmp);
										 detailTransferStockPFdao.add(detailTransferStockPFTmp);
										 
						        		  stock=stockpf.getStock().subtract(QuantiteUnit.add(QuantitePaquet));
								          stockpf.setStock(stock);
								          stockpfDAO.edit(stockpf);
								          listStockPF.add(stockpf);
								          
						        		 
						        		 
						        		 
						        		 
						        	 }else
						        	 {
							        	  if(!txtreduction.getText().equals(""))
								          {
								        	  detailFacture.setReduction(new BigDecimal(txtreduction.getText()));
								          }else
								          {
								        	  detailFacture.setReduction(BigDecimal.ZERO);  
								          }
							        	  
							        	  // Modifier Transfer Stock PF
									        detailTransferStockPF.setArticle(article);
									        detailTransferStockPF.setSousFamille(sousfamille);
											detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
											detailTransferStockPF.setMagasinDestination(magasin);
											if(checkttc.isSelected()==true)
											{
												if(!txtreduction.getText().equals(""))
										          {
													detailTransferStockPF.setPrixUnitaire(prixTTC.subtract(prixTTC.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))); 	
													
										          }else
										          {
										        	  detailTransferStockPF.setPrixUnitaire(prixTTC); 
										          }
												  
											}else
											{
												
												if(!txtreduction.getText().equals(""))
										          {
													detailTransferStockPF.setPrixUnitaire(new BigDecimal(txtPrix.getText()).subtract(new BigDecimal(txtPrix.getText()).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))); 	
													
										          }else
										          {
										        	  detailTransferStockPF.setPrixUnitaire(new BigDecimal(txtPrix.getText())); 
										          }
												
												
												   	
											}
									        	
									          
											detailTransferStockPF.setQuantite(QuantiteUnit.add(QuantitePaquet));		
											detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
											detailTransferStockPFdao.edit(detailTransferStockPF);
											listDetailTransferProduitFini.set(tableArticle.getSelectedRow(), detailTransferStockPF);
											
											
							        	 
								          detailFacture.setArticle(article);
								          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
								     if(checkttc.isSelected()==true)
								     {

						        		  detailFacture.setPrixUnitaire(prixTTC);
								           detailFacture.setBrutHT ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
									     if(checkboxSansTva.isSelected()==true)
									     {
									    	 
									           detailFacture.setMontantTVA(BigDecimal.ZERO);

									     }else
									     {
									           detailFacture.setMontantTVA((((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));

									    	 
									     }
									       detailFacture.setSousFamille(sousfamille);
									       if(!txtreduction.getText().equals(""))
									          {
									    	   if(checkboxSansTva.isSelected()==true)
											     {
									    		   detailFacture.setRemiseCommerciale(( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP) );
									    		   detailFacture.setMontantHT( (((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );
									    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
									    		   detailFacture.setTva(BigDecimal.ZERO);
									    		   detailFacture.setMontantTTC(( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );  

									    		   
											     }else
											     {
										    		   detailFacture.setRemiseCommerciale( (((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP).setScale(6, RoundingMode.HALF_UP) );
										    		   detailFacture.setMontantHT(( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );
										    		   detailFacture.setMontantTVA( (( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP) );
										    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
												       detailFacture.setMontantTTC(( ( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).add( ( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).multiply(Constantes.TVA) )).setScale(6, RoundingMode.HALF_UP) );  

													    

											     }
									    	   
									          }else
									          {
									        	  if(checkboxSansTva.isSelected()==true)
												     {
										    		   detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
										    		   detailFacture.setMontantHT(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
										    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
										    		   detailFacture.setTva(BigDecimal.ZERO);
										    		   detailFacture.setMontantTTC(((((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));

												     }else
												     {
												    	 detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
											    		   detailFacture.setMontantHT(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
											    		   detailFacture.setMontantTVA(( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP) );
											    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
											        	  detailFacture.setMontantTTC( (((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).add( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA) )).setScale(6, RoundingMode.HALF_UP) );

												     }
									          }  
						        	  
								     
								     }else
								     {

						        		  detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
								           detailFacture.setBrutHT ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
								           if(checkboxSansTva.isSelected()==true)
										     {
									           detailFacture.setMontantTVA(BigDecimal.ZERO);

										     }else
										     {
										           detailFacture.setMontantTVA((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));

										     }
									       detailFacture.setSousFamille(sousfamille);
									       if(!txtreduction.getText().equals(""))
									          {
									    	   if(checkboxSansTva.isSelected()==true)
											     {
									    		   detailFacture.setRemiseCommerciale( ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP) );
									    		   detailFacture.setMontantHT(( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );
									    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
									    		   detailFacture.setTva(BigDecimal.ZERO);
									    		   detailFacture.setMontantTTC( ( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );  

									    		   
											     }else
											     {
											    	 
											    	 detailFacture.setRemiseCommerciale(( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP) );
										    		   detailFacture.setMontantHT( ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );
										    		   detailFacture.setMontantTVA( (( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP) );
										    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
										        	  detailFacture.setMontantTTC( (( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).add( ( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).multiply(Constantes.TVA) )).setScale(6, RoundingMode.HALF_UP) );  

											     }
									    	   
									          }else
									          {
									        	  if(checkboxSansTva.isSelected()==true)
												     {
												    	 detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
												    	 detailFacture.setMontantHT((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
												    	 detailFacture.setMontantTVA(BigDecimal.ZERO);
												    	 detailFacture.setTva(BigDecimal.ZERO);
										        	  detailFacture.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));

												     }else
												     {
												    	 detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
												    	 detailFacture.setMontantHT((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
												    	 detailFacture.setMontantTVA( ((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP) );
												    	 detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
												    	 detailFacture.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).add((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));

												     }
									         
									          }  
						        	  
								     
								     }
								        
								          // detailFacture.setTypeVente(typevente);
								          
								           detailFacture.setFacturePF(facturePF);
								       //    detailFacture.setDateCreation(new Date());
								           
								           detailFacture.setUtilisateur(utilisateur);	
								           
								           stock=stockpf.getStock().subtract(QuantiteUnit.add(QuantitePaquet));
								           stockpf.setStock(stock);
								          stockpfDAO.edit(stockpf);
								          listStockPF.set(tableArticle.getSelectedRow(), stockpf);
								          listDetailFacturePF.set(tableArticle.getSelectedRow(), detailFacture);
								          detailFacturePfdao.edit(detailFacture);
						        		 
						        	 }
					
							          
							          
							          
							          
							          
							          
							          
							 		 if(offfrepromo==true)
						        	 {
											
										 boolean offre1=false , offre2=false;
										 
										 
										 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow())
										 {
											 
											 if(listDetailFacturePF.get(tableArticle.getSelectedRow())!=null)
											 {
												 
												 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
												 {
													 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
													 listDetailFacturePF.remove(tableArticle.getSelectedRow());
													 detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(tableArticle.getSelectedRow()).getId());
													 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
													
													 listStockPF.remove(tableArticle.getSelectedRow());
													 offre1=true;
												 }
												 
												 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
												 {
													 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
													 listDetailFacturePF.remove(tableArticle.getSelectedRow());
													 detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(tableArticle.getSelectedRow()).getId());
													 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
													
													 listStockPF.remove(tableArticle.getSelectedRow());
													 offre1=true;
												 }
												 
												 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
												 {
													 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
													 listDetailFacturePF.remove(tableArticle.getSelectedRow());
													 detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(tableArticle.getSelectedRow()).getId());
													 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
													
													 listStockPF.remove(tableArticle.getSelectedRow());
													 offre1=true;
												 }
												 
												 
												 
												 
											 }
											 
											 
										 }
										 
										 if(offre1==true)
										 {
											 
											 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow())
											 {
												 
												 if(listDetailFacturePF.get(tableArticle.getSelectedRow())!=null)
												 {
													 
													 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
													 {
														 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
														 listDetailFacturePF.remove(tableArticle.getSelectedRow());
														 detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(tableArticle.getSelectedRow()).getId());
														 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
														
														 listStockPF.remove(tableArticle.getSelectedRow());
														 offre2=true;
													 }
													 
													 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
													 {
														 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
														 listDetailFacturePF.remove(tableArticle.getSelectedRow());
														 detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(tableArticle.getSelectedRow()).getId());
														 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
														
														 listStockPF.remove(tableArticle.getSelectedRow());
														 offre2=true;
													 }
													 
													 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
													 {
														 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
														 listDetailFacturePF.remove(tableArticle.getSelectedRow());
														 detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(tableArticle.getSelectedRow()).getId());
														 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
														
														 listStockPF.remove(tableArticle.getSelectedRow());
														 offre2=true;
													 }
													 
													 
													 
													 
												 }
												 
												 
											 } 
											 
											 
											 
											 
										 }
										 
										 if(offre1==true && offre2==true)
										 {
											 
											 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow())
											 {
												 
												 if(listDetailFacturePF.get(tableArticle.getSelectedRow())!=null)
												 {
													 
													 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
													 {
														 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
														 listDetailFacturePF.remove(tableArticle.getSelectedRow());
														 detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(tableArticle.getSelectedRow()).getId());
														 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
														
														 listStockPF.remove(tableArticle.getSelectedRow());
														
													 }
													 
													 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
													 {
														 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
														 listDetailFacturePF.remove(tableArticle.getSelectedRow());
														 detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(tableArticle.getSelectedRow()).getId());
														 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
														
														 listStockPF.remove(tableArticle.getSelectedRow());
														 
													 }
													 
													 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
													 {
														 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
														 listDetailFacturePF.remove(tableArticle.getSelectedRow());
														 detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(tableArticle.getSelectedRow()).getId());
														 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
														
														 listStockPF.remove(tableArticle.getSelectedRow());
														
													 }
													 
													 
													 
													 
												 }
												 
												 
											 } 
											 
											 
											 
											 
										 }
										 
										 DetailFacturePF facturePF=listDetailFacturePF.get(tableArticle.getSelectedRow());
											DetailTransferProduitFini TransferStockPF=listDetailTransferProduitFini.get(tableArticle.getSelectedRow());
											
										 if(!txtquantitetherres.getText().equals(""))
										 {
											 
									         SousFamilleArticlePF	sousfamilleoffretherre=	 mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_THERRES);
											 Articles articletherre=mapArticleOffre.get(comboBoxtherres.getSelectedItem());
											 
											 DetailFacturePF detailFacturetherres= new DetailFacturePF();
											 
											 detailFacturetherres.setQuantite(new BigDecimal(txtquantitetherres.getText()));
											 detailFacturetherres.setArticle(articletherre);
											 
												DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articletherre.getId(), magasin.getId(),sousfamilleoffretherre.getFamileArticlePF().getId() , sousfamilleoffretherre.getId() , facturePF.getFacturePF().getClientPF().getId());
		  	  		  				 			
			  	  		  				 		
			  	  		  				 		if(detailprixarticle!=null)
			  	  		  				 			{
			  	  		  				 		detailFacturetherres.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
			  	  		  				 			 
			  	  		  				 			}else
			  	  		  				 			{
			  	  		  				 				
			  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(articletherre.getId(), magasin.getId(),sousfamilleoffretherre.getFamileArticlePF().getId() , sousfamilleoffretherre.getId());
				
			  	  		  				 			if(detailprixarticleTmp!=null)	
			  	  		  				 			{
			  	  		  				 			detailFacturetherres.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
			  	  		  				 				 
			  	  		  				 				
			  	  		  				 			}else
			  	  		  				 			{
			  	  		  				 			detailFacturetherres.setPrixUnitaire(BigDecimal.ZERO);
			  	  		  				 		 
			  	  		  				 			
			  	  		  				 			}
			  	  		  				 				
			  	  		  				 				
			  	  		  				 				
			  	  		  				 				
			  	  		  				 			}
											 
										 
											 detailFacturetherres.setMontantHT(BigDecimal.ZERO);
											 detailFacturetherres.setMontantTVA(BigDecimal.ZERO);
											 detailFacturetherres.setMontantTTC(BigDecimal.ZERO);
											 detailFacturetherres.setReduction(new BigDecimal(100));
											 detailFacturetherres.setTva(BigDecimal.ZERO);	
											 detailFacturetherres.setBrutHT(detailFacturetherres.getPrixUnitaire().multiply(detailFacturetherres.getQuantite()));
											 detailFacturetherres.setRemiseCommerciale(detailFacturetherres.getPrixUnitaire().multiply(detailFacturetherres.getQuantite()));
											 detailFacturetherres.setSousFamille(sousfamilleoffretherre);
											 detailFacturetherres.setFacturePF(facturePF.getFacturePF());
											
											 listDetailFacturePF.add (detailFacturetherres);
											 detailFacturePfdao.add(detailFacturetherres);
											 
											 DetailTransferProduitFini detailTransferStockPFtherres=new DetailTransferProduitFini();
											 
											 detailTransferStockPFtherres.setQuantite(new BigDecimal(txtquantitetherres.getText()));
											 detailTransferStockPFtherres.setArticle(articletherre);
											 detailTransferStockPFtherres.setSousFamille(sousfamilleoffretherre);
											 detailTransferStockPFtherres.setDateTransfer(dateChooserfacture.getDate());
											 detailTransferStockPFtherres.setPrixUnitaire(BigDecimal.ZERO);
											 detailTransferStockPFtherres.setTransferStockPF(TransferStockPF.getTransferStockPF());
											 detailTransferStockPFtherres.setMagasinDestination(magasin);
											 detailTransferStockPFtherres.setTypeTransfer( ETAT_TRANSFER_STOCK_VENTE);
											 listDetailTransferProduitFini.add( detailTransferStockPFtherres);
											 detailTransferStockPFdao.add(detailTransferStockPFtherres);
											 
											 StockPF stockpfTherres=stockpfDAO.findStockByMagasinPFBySousFamille(articletherre.getId(), magasin.getId(), sousfamilleoffretherre.getId());
											 stockpfTherres.setStock(stockpfTherres.getStock().subtract(new BigDecimal(txtquantitetherres.getText())));
											 stockpfDAO.edit(stockpfTherres);
											 
											 listStockPF.add(stockpfTherres);
											 
											 
										 }
										 
											
										 if(!txtquantiteverres.getText().equals(""))
										 {
											 Articles articleverres=mapArticleOffre.get(comboBoxverres.getSelectedItem());
											 SousFamilleArticlePF	sousfamilleoffreVerre=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_VERRES);
											 DetailFacturePF detailFactureVerres= new DetailFacturePF();
											 detailFactureVerres.setQuantite(new BigDecimal(txtquantiteverres.getText()));
											 detailFactureVerres.setArticle(articleverres);
											    DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articleverres.getId(), magasin.getId(),sousfamilleoffreVerre.getFamileArticlePF().getId() , sousfamilleoffreVerre.getId() , facturePF.getFacturePF().getClientPF().getId());
		  	  		  				 			
			  	  		  				 		
			  	  		  				 		if(detailprixarticle!=null)
			  	  		  				 			{
			  	  		  				 		detailFactureVerres.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
			  	  		  				 			 
			  	  		  				 			}else
			  	  		  				 			{
			  	  		  				 				
			  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(articleverres.getId(), magasin.getId(),sousfamilleoffreVerre.getFamileArticlePF().getId() , sousfamilleoffreVerre.getId());
				
			  	  		  				 			if(detailprixarticleTmp!=null)	
			  	  		  				 			{
			  	  		  				 			detailFactureVerres.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
			  	  		  				 				 
			  	  		  				 				
			  	  		  				 			}else
			  	  		  				 			{
			  	  		  				 			detailFactureVerres.setPrixUnitaire(BigDecimal.ZERO);
			  	  		  				 				
			  	  		  				 		 
			  	  		  				 			
			  	  		  				 			}
			  	  		  				 				
			  	  		  				 				
			  	  		  				 				
			  	  		  				 				
			  	  		  				 			}
											 
											 
											 detailFactureVerres.setMontantHT(BigDecimal.ZERO);
											 detailFactureVerres.setMontantTVA(BigDecimal.ZERO);
											 detailFactureVerres.setMontantTTC(BigDecimal.ZERO);
											 detailFactureVerres.setReduction(new BigDecimal(100));
											 detailFactureVerres.setTva(BigDecimal.ZERO);	
											 detailFactureVerres.setBrutHT(detailFactureVerres.getPrixUnitaire().multiply(detailFactureVerres.getQuantite()));
											 detailFactureVerres.setRemiseCommerciale(detailFactureVerres.getPrixUnitaire().multiply(detailFactureVerres.getQuantite()));
											 detailFactureVerres.setSousFamille(sousfamilleoffreVerre);
											 detailFactureVerres.setFacturePF(facturePF.getFacturePF());
											
											 listDetailFacturePF.add (detailFactureVerres);
											 detailFacturePfdao.add(detailFactureVerres);
											 
											 DetailTransferProduitFini detailTransferStockPFVerres=new DetailTransferProduitFini();
											 
											 detailTransferStockPFVerres.setQuantite(new BigDecimal(txtquantiteverres.getText()));
											 detailTransferStockPFVerres.setArticle(articleverres);
											 detailTransferStockPFVerres.setSousFamille(sousfamilleoffreVerre);
											 detailTransferStockPFVerres.setDateTransfer(dateChooserfacture.getDate());
											 detailTransferStockPFVerres.setPrixUnitaire(BigDecimal.ZERO);
											 detailTransferStockPFVerres.setTransferStockPF(TransferStockPF.getTransferStockPF());
											 detailTransferStockPFVerres.setMagasinDestination(magasin);
											 detailTransferStockPFVerres.setTypeTransfer( ETAT_TRANSFER_STOCK_VENTE);
											 listDetailTransferProduitFini.add( detailTransferStockPFVerres);
											 detailTransferStockPFdao.add(detailTransferStockPFVerres);
											 
											 StockPF stockpfVerres=stockpfDAO.findStockByMagasinPFBySousFamille(articleverres.getId(), magasin.getId(), sousfamilleoffreVerre.getId());
											 stockpfVerres.setStock(stockpfVerres.getStock().subtract(new BigDecimal(txtquantiteverres.getText())));
											 stockpfDAO.edit(stockpfVerres);
											 listStockPF.add(stockpfVerres);
											 
										 }			
													
								
										 if(!txtquantitepromo.getText().equals(""))
										 {
											 
											 
											 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
											 
											 DetailFacturePF detailFacturepromo= new DetailFacturePF();
											 detailFacturepromo.setQuantite(new BigDecimal(txtquantitepromo.getText()));
											 detailFacturepromo.setArticle(articlespromo);
											  DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articlespromo.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , facturePF.getFacturePF().getClientPF().getId());
		  	  		  				 			
			  	  		  				 		
			  	  		  				 		if(detailprixarticle!=null)
			  	  		  				 			{
			  	  		  				 		detailFacturepromo.setPrixUnitaire(detailprixarticle.getPrix().setScale(6, RoundingMode.HALF_UP));
			  	  		  				 			 
			  	  		  				 			}else
			  	  		  				 			{
			  	  		  				 				
			  	  	  	  				 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(articlespromo.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId());
				
			  	  		  				 			if(detailprixarticleTmp!=null)	
			  	  		  				 			{
			  	  		  				 			detailFacturepromo.setPrixUnitaire(detailprixarticleTmp.getPrix().setScale(6, RoundingMode.HALF_UP));
			  	  		  				 				 
			  	  		  				 				
			  	  		  				 			}else
			  	  		  				 			{
			  	  		  				 				
			  	  		  				 			detailFacturepromo.setPrixUnitaire(BigDecimal.ZERO);
			  	  		  				 			
			  	  		  				 			}
			  	  		  				 				
			  	  		  				 				
			  	  		  				 				
			  	  		  				 				
			  	  		  				 			}
											 
											 
											 
											 
											 detailFacturepromo.setMontantHT(BigDecimal.ZERO);
											 detailFacturepromo.setMontantTVA(BigDecimal.ZERO);
											 detailFacturepromo.setMontantTTC(BigDecimal.ZERO);
											 detailFacturepromo.setReduction(new BigDecimal(100));
											 detailFacturepromo.setTva(BigDecimal.ZERO);	
											 detailFacturepromo.setBrutHT(detailFacturepromo.getPrixUnitaire().multiply(detailFacturepromo.getQuantite()));
											 detailFacturepromo.setRemiseCommerciale(detailFacturepromo.getPrixUnitaire().multiply(detailFacturepromo.getQuantite()));
											 detailFacturepromo.setSousFamille(sousfamille);
											 detailFacturepromo.setFacturePF(facturePF.getFacturePF());
											
											 listDetailFacturePF.add (detailFacturepromo);
											 detailFacturePfdao.add(detailFacturepromo);
											 
											 DetailTransferProduitFini detailTransferStockPFpromo=new DetailTransferProduitFini();
											 
											 detailTransferStockPFpromo.setQuantite(new BigDecimal(txtquantitepromo.getText()));
											 detailTransferStockPFpromo.setArticle(articlespromo);
											 detailTransferStockPFpromo.setSousFamille(sousfamille);
											 detailTransferStockPFpromo.setDateTransfer(dateChooserfacture.getDate());
											 detailTransferStockPFpromo.setPrixUnitaire(BigDecimal.ZERO);
											 detailTransferStockPFpromo.setTransferStockPF(TransferStockPF.getTransferStockPF());
											 detailTransferStockPFpromo.setMagasinDestination(magasin);
											 detailTransferStockPFpromo.setTypeTransfer( ETAT_TRANSFER_STOCK_VENTE);
											 listDetailTransferProduitFini.add( detailTransferStockPFpromo);
											 detailTransferStockPFdao.add(detailTransferStockPFpromo);
											
											 StockPF stockpfpromo=stockpfDAO.findStockByMagasinPFBySousFamille(articlespromo.getId(), magasin.getId(), sousfamille.getId());
											 stockpfpromo.setStock(stockpfpromo.getStock().subtract(new BigDecimal(txtquantitepromo.getText())));
											 stockpfDAO.edit(stockpfpromo);
											 listStockPF.add(stockpfpromo);
											 
							
											 
										 }	
										 
						        	 }
							          
							          
							          afficher_tableDetailFacturePF(listDetailFacturePF);
							          int i=0;
								        BigDecimal montanttotal=BigDecimal.ZERO;
								        BigDecimal sommequantite=BigDecimal.ZERO;
								        BigDecimal montanttotalHT=BigDecimal.ZERO;
								        BigDecimal montanttotalTVA=BigDecimal.ZERO;
								        
								        BigDecimal lamarge=BigDecimal.ZERO;
								        BigDecimal compteur=BigDecimal.ZERO;
								        while(i<listDetailFacturePF.size())
								        {
								        	 DetailFacturePF detailFacturePF=listDetailFacturePF.get(i);
								        	  StockPF stockpfTmp=stockpfDAO.findStockByMagasinPFBySousFamille(detailFacturePF.getArticle().getId(),magasin.getId(),detailFacturePF.getSousFamille().getId());
									          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
									          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
									          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
									          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
									          if(!detailFacturePF.getPrixUnitaire().setScale(2,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP)))
									          {
									        	  compteur=compteur.add(BigDecimal.ONE);
									        	  lamarge=lamarge.add((detailFacturePF.getPrixUnitaire().subtract(stockpfTmp.getPrixUnitaire())).divide(detailFacturePF.getPrixUnitaire(), RoundingMode.HALF_UP));  
									          }
								            i++;
								        }
								       txttotalmontantTTC.setText(montanttotal+"");
								        txttotalquantite.setText(sommequantite+"");
								        txttotalmontantHT.setText(montanttotalHT+"");
							  			txttotalmontantTVA.setText(montanttotalTVA+"");
							  			
							  			if(compteur!=BigDecimal.ZERO)
							  			{
								  			
							  			
								  			 if((lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemax.getValeur())>0 || (lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemin.getValeur())<0)
								  			 {
								  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
								  				labelMarge.setBackground(Color.red);
								  			 }else
								  			 {
								  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
								  				labelMarge.setBackground(Color.GREEN);
								  			 }
								  			 
								  				
								  			}
							  			
							  			initialiser();
										
							  			
						        		 
							        	 }
						        		 
						        	 }else
						        	 {
						        		 stocktmp=stockpftmp.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
										 stockpftmp.setStock(stocktmp);
										  stockpfDAO.edit(stockpftmp);
										  
									      	 if(offfrepromo==true)
								        	 {
								          		 
								          		 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
								          		 {
								          			 
								          			 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
								          			 {
								          				 
								          				//////////////////////////////////// therres //////////////////////////////
										        		 
										        		 stocktmpoffretherres=stockpfOffreTherres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
														  stockpfOffreTherres.setStock(stocktmpoffretherres); 
														  stockpfDAO.edit(stockpfOffreTherres); 
								          				 
								          			 }
								          			 
								          			 
								          			 
								          			 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
								          			 {
								          				 
								          				  //////////////////////////////////// verres ///////////////////////////////////
														  
														  stocktmpoffreverres=stockpfOffreVerres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
														  stockpfOffreVerres.setStock(stocktmpoffreverres); 
														  stockpfDAO.edit(stockpfOffreVerres);
								          				 
								          			 }
								          			  
								          			 
								          			 if(!listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO"))
								          			
								          			 {
								          				 
								          				  
														  ///////////////////////////////////// Article Promo ////////////////////////////////////////////////
														  
														  //////////////////////////////////// Article Promo ///////////////////////////////////
														  if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
											        		 {
															  
															  if( listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle()+"_PFO"))
									        					{
																  
																  stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
																  stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo); 
																  stockpfDAO.edit(stockpfOffreArticlepromo);
									        					}
															
															  
															  
											        		 }
								          				 
								          				 
								          			 }
								          			 
								          			 
								          			 
								          			 
								          			 
								          		 }
								        		 
								        	
								          		 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2)!=null)
								          		 {
								          			 
								          			 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
								          			 {
								          				 
								          				//////////////////////////////////// therres //////////////////////////////
										        		 
										        		 stocktmpoffretherres=stockpfOffreTherres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite());
														  stockpfOffreTherres.setStock(stocktmpoffretherres); 
														  stockpfDAO.edit(stockpfOffreTherres); 
								          				 
								          			 }
								          			 
								          			 
								          			 
								          			 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
								          			 {
								          				 
								          				  //////////////////////////////////// verres ///////////////////////////////////
														  
														  stocktmpoffreverres=stockpfOffreVerres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite());
														  stockpfOffreVerres.setStock(stocktmpoffreverres); 
														  stockpfDAO.edit(stockpfOffreVerres);
								          				 
								          			 }
								          			  
								          			 
								          			 if(!listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().contains("_PFO"))
								          			
								          			 {
								          				 
								          				  
														  ///////////////////////////////////// Article Promo ////////////////////////////////////////////////
														  
														  //////////////////////////////////// Article Promo ///////////////////////////////////
														  if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2)!=null)
											        		 {
															  
															  if( listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle()+"_PFO"))
									        					{
																  
																  stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getQuantite());
																  stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo); 
																  stockpfDAO.edit(stockpfOffreArticlepromo);
									        					}
															
															  
															  
											        		 }
								          				 
								          				 
								          			 }
								          			 
								          			 
								          			 
								          			 
								          			 
								          		 } 
								          		 
								          		 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3)!=null)
								          		 {
								          			 
								          			 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
								          			 {
								          				 
								          				//////////////////////////////////// therres //////////////////////////////
										        		 
										        		 stocktmpoffretherres=stockpfOffreTherres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite());
														  stockpfOffreTherres.setStock(stocktmpoffretherres); 
														  stockpfDAO.edit(stockpfOffreTherres); 
								          				 
								          			 }
								          			 
								          			 
								          			 
								          			 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
								          			 {
								          				 
								          				  //////////////////////////////////// verres ///////////////////////////////////
														  
														  stocktmpoffreverres=stockpfOffreVerres.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite());
														  stockpfOffreVerres.setStock(stocktmpoffreverres); 
														  stockpfDAO.edit(stockpfOffreVerres);
								          				 
								          			 }
								          			  
								          			 
								          			 if(!listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().contains("_PFO"))
								          			
								          			 {
								          				 
								          				  
														  ///////////////////////////////////// Article Promo ////////////////////////////////////////////////
														  
														  //////////////////////////////////// Article Promo ///////////////////////////////////
														  if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3)!=null)
											        		 {
															  
															  if( listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle()+"_PFO"))
									        					{
																  
																  stocktmpoffreArticlePromo=stockpfOffreArticlepromo.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getQuantite());
																  stockpfOffreArticlepromo.setStock(stocktmpoffreArticlePromo); 
																  stockpfDAO.edit(stockpfOffreArticlepromo);
									        					}
															
															  
															  
											        		 }
								          				 
								          				 
								          			 }
								          			 
								          			 
								          			 
								          			 
								          			 
								          		 }  
												  
												
										
												  
												  
								        		  
								        	 }
						        		 
						        		 JOptionPane.showMessageDialog(null,  "Stock "+article.getLiblle()+" introuvable dans le magasin "+magasin.getLibelle(),"Attention",JOptionPane.ERROR_MESSAGE);
						        	 }
						        	 
						        	
										
							       }else
							       {
							    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							    	   return;
							       }
							       
									
						        	 
						         }
							}
				   			
		   				
		   			}
		   			
		   			
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité , Le Prix et la Remise doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
		   	}
		   		
		   
		   });
		  btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  
		   btnSupprimer = new JButton();
		  btnSupprimer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		BigDecimal stocktmp=BigDecimal.ZERO;
		  		BigDecimal stockOffreTherre=BigDecimal.ZERO;
		  		BigDecimal stockOffreVerre=BigDecimal.ZERO;
		  		
		  		if(tableArticle.getSelectedRow()!=-1)
		  		{
		  			
		  			 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer l'article dans la facture  ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
							
						{
							
							
							
							Articles articleOffre=mapArticleOffre.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
				       		if(articleOffre!=null)
				       		{
				       			if(articleOffre.getCodeArticle().equals(Constantes.OFFRE_THERRES)  || articleOffre.getCodeArticle().equals(Constantes.OFFRE_VERRES))
					       		{
					       			
					       			JOptionPane.showMessageDialog(null, "Veuillez Selectionner Article Promo SVP","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
					       			
					       			
					       		}
				       		}
							
				       		if(tableArticle.getSelectedRow()-3>=0)
							{
				       			
				       			if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()-3).getArticle().getCodeArticle()+"_PFO"))
				       			{
				       				
				       				JOptionPane.showMessageDialog(null, "Veuillez Selectionner Article Promo SVP","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
				       				
				       				
				       			}
				       			
				       			
							}
				       		
							
							
				       	 Articles article=listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle();
							
							
							 Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
							 
							 
							 
							StockPF stockpftmp =stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getId(), magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()).getSousFamille().getId());
					     
							 // Verifier le stock offre pour la gratuité du the
							if(checkboxGratuits.isSelected()==true)
				   			{
								 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)))
			        			 {
			        				 stocktmp=stockpftmp.getStockOffre().add(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
						        	 stockpftmp.setStockOffre(stocktmp); 
						        	 stockpfDAO.edit(stockpftmp);
			        			 }else
			        			 {
			        				 
			        				 stocktmp=stockpftmp.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
								        stockpftmp.setStock(stocktmp);
								        stockpfDAO.edit(stockpftmp);
			        				 
			        			 }
								
				   			}else
				   			{
				   				
				   				
				   				stocktmp=stockpftmp.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
						        stockpftmp.setStock(stocktmp);
						        stockpfDAO.edit(stockpftmp);
				   				
				   				
				   			}
			        		
			        		 
							
							
					      
					        DetailFacturePF detailfacturepf=listDetailFacturePF.get(tableArticle.getSelectedRow());
					      
				        	 boolean offfrepromo =false ; 
				        	 
				        	 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5()!=null)
	  				 			{
	  				 			// centrecout5 = article promotion ou non
	  				 				if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)))
	  				 				{
	  				 					if(article.getCentreCout5()!=null)
	  				 					{
	  				 						if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)))
	  				 						{
	  				 									offfrepromo=true;
	  				 								
	  				 						}
	  				 						
	  				 						
	  				 					}
	  				 					 
	  				 					
	  				 				}
				        	
	  				 			}
				        	 
				        	 if(offfrepromo==true)
				        	 {
				        		 
				        		boolean offre1=false , offre2=false; 
				        		 
				        		 
				        		 
				        		 
				        		 if(tableArticle.getRowCount()-1>=tableArticle.getSelectedRow()+1)
				        		 {
				        			 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
				        			 {
				        				 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
					        			 {
					        				 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
								        		{
								        			
			/////////////////////////////////////////////////////// Therres ////////////////////////////////////////////////
									        		 
					        		 StockPF stockpfTherresOffre =stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getId(),magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getSousFamille().getId());
						        	 stockOffreTherre=stockpfTherresOffre.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
						        	 stockpfTherresOffre.setStock(stockOffreTherre);
						        	 stockpfDAO.edit(stockpfTherresOffre);
						        	 listStockPF.remove(tableArticle.getSelectedRow()+1);
						        	 
						        	 DetailFacturePF detailfacturepfTherres=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
						        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
						        	 detailFacturePfdao.delete(detailfacturepfTherres.getId());
						        	 DetailTransferProduitFini detailtransferstockPFTherres=listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+1);
						        	 detailTransferStockPFdao.delete(detailtransferstockPFTherres.getId());
									 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
								        			
									 offre1=true;
								        			
								        		} 
					        			 }
				        				 
				        			 }
				        			 
				        		
				        		  		
				        			 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
				        			 {
				        				 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
					        			 {
					        				 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
								        		{
								        			
			/////////////////////////////////////////////////////// Verres ////////////////////////////////////////////////
									        		 
					                StockPF stockpfVerresOffre =stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getId(),magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getSousFamille().getId());
			  	                stockOffreVerre=stockpfVerresOffre.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
			  	                stockpfVerresOffre.setStock(stockOffreVerre);
			  	                stockpfDAO.edit(stockpfVerresOffre);
			  	                listStockPF.remove(tableArticle.getSelectedRow()+1);
			  	                
			  	              DetailFacturePF detailfacturepfVerres=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
			 	        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
			 	        	 detailFacturePfdao.delete(detailfacturepfVerres.getId());
			 	        	 DetailTransferProduitFini detailtransferstockPFVerres=listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+1);
			 	        	 detailTransferStockPFdao.delete(detailtransferstockPFVerres.getId());
			 				 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
					                offre1=true;
					                
								        		} 
					        			 }
				        			 }
				        			 
				        		
				        			 
				        			 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
				        			 {
				        				 
				        				 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
					        			 {
					        					if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
								        		{
			/////////////////////////////////////////////////////// Article Promo ////////////////////////////////////////////////
									        		
					        				 StockPF stockpfArticlePromo =stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getId(),magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getSousFamille().getId());
					        				 stockpfArticlePromo.setStock(stockpfArticlePromo.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite()));
					     	                stockpfDAO.edit(stockpfArticlePromo);
					     	                listStockPF.remove(tableArticle.getSelectedRow()+1);
					     	                
					     	                
					     	               DetailFacturePF detailfacturepfPromo=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
					     		        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
					     		        	 detailFacturePfdao.delete(detailfacturepfPromo.getId());
					     		        	 DetailTransferProduitFini detailtransferstockPFPromo=listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+1);
					     		        	 detailTransferStockPFdao.delete(detailtransferstockPFPromo.getId());
					     					 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
					     	               offre1=true;
								        		} 
					        			 }
				        			 }
				        			 
				        			

						        	
				        			 
				        		 }
                                 if( offre1==true)
                                   {
                                	 
                               		 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
					        		 {
                               			 
                               			 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
					        			 {
                               				 
                               	 			if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
      					        			 {
                                      				
                                      				if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
       							        		{
       							        			
       		/////////////////////////////////////////////////////// Therres ////////////////////////////////////////////////
       								        		 
       				        		 StockPF stockpfTherresOffre =stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getId(),magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getSousFamille().getId());
       					        	 stockOffreTherre=stockpfTherresOffre.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
       					        	 stockpfTherresOffre.setStock(stockOffreTherre);
       					        	 stockpfDAO.edit(stockpfTherresOffre);
       					        	 listStockPF.remove(tableArticle.getSelectedRow()+1);


       					        	 DetailFacturePF detailfacturepfTherres=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
						        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
						        	 detailFacturePfdao.delete(detailfacturepfTherres.getId());
						        	 DetailTransferProduitFini detailtransferstockPFTherres=listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+1);
						        	 detailTransferStockPFdao.delete(detailtransferstockPFTherres.getId());
									 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
       								 
       								 
       								 
       								 offre2=true;
       							        			
       							        			
       							        		}
                                      				
      					        			 }
                               				 
					        			 }
                               			 
                               			if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
					        			 {
                               				
                               				if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
     					        			 {
                                  				if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
   							        		{
   							        			
   		/////////////////////////////////////////////////////// Verres ////////////////////////////////////////////////
   								        		 
   				                StockPF stockpfVerresOffre =stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getId(),magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getSousFamille().getId());
   		  	                stockOffreVerre=stockpfVerresOffre.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
   		  	                stockpfVerresOffre.setStock(stockOffreVerre);
   		  	                stockpfDAO.edit(stockpfVerresOffre);
   		  	                listStockPF.remove(tableArticle.getSelectedRow()+1);
   		  	                
   		  	                
   		  	           DetailFacturePF detailfacturepfVerres=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
		 	        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
		 	        	 detailFacturePfdao.delete(detailfacturepfVerres.getId());
		 	        	 DetailTransferProduitFini detailtransferstockPFVerres=listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+1);
		 	        	 detailTransferStockPFdao.delete(detailtransferstockPFVerres.getId());
		 				 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
   				                
   				                
   				                
   				                
   				           	 offre2=true;
   				                
   							        		}
                                  				
     					        			 }	
                               				
					        			 }
					        		  	
                               			if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
  					        			 {
                               				
                               				if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
    					        			 {
                                  				
                                  				if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
   							        		{
   		/////////////////////////////////////////////////////// Article Promo ////////////////////////////////////////////////
   								        		
   				        				 StockPF stockpfArticlePromo =stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getId(),magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getSousFamille().getId());
   				        				 stockpfArticlePromo.setStock(stockpfArticlePromo.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite()));
   				     	                stockpfDAO.edit(stockpfArticlePromo);
   				     	                listStockPF.remove(tableArticle.getSelectedRow()+1);


   				     	            DetailFacturePF detailfacturepfPromo=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
			     		        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
			     		        	 detailFacturePfdao.delete(detailfacturepfPromo.getId());
			     		        	 DetailTransferProduitFini detailtransferstockPFPromo=listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+1);
			     		        	 detailTransferStockPFdao.delete(detailtransferstockPFPromo.getId());
			     					 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
   				     	                
   				     	          	 offre2=true;
   				     	                
   							        		} 
                                  				
    					        			 }
                               				
  					        			 }
							        		 
                               	

							        	
					        			 
					        		 }


                                      }
				      
				     if(offre1==true && offre2==true)
				     {
				    	 
						 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
		        		 {
							 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
			        			 {
								 
								 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
			        			 {
								 
							 		if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
					        		{
					        			
/////////////////////////////////////////////////////// Therres ////////////////////////////////////////////////
						        		 
		        		 StockPF stockpfTherresOffre =stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getId(),magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getSousFamille().getId());
			        	 stockOffreTherre=stockpfTherresOffre.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite());
			        	 stockpfTherresOffre.setStock(stockOffreTherre);
			        	 stockpfDAO.edit(stockpfTherresOffre);
			        	 listStockPF.remove(tableArticle.getSelectedRow()+1);


			        	 DetailFacturePF detailfacturepfTherres=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
			        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
			        	 detailFacturePfdao.delete(detailfacturepfTherres.getId());
			        	 DetailTransferProduitFini detailtransferstockPFTherres=listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+1);
			        	 detailTransferStockPFdao.delete(detailtransferstockPFTherres.getId());
						 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
					        			
					        			
					        		}
								 
			        			 }
								 
			        			 }
							 
							 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
		        			 {
								 
								 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
			        			 {
									 
										if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
						        		{
						        			
	/////////////////////////////////////////////////////// Verres ////////////////////////////////////////////////
							        		 
			                StockPF stockpfVerresOffre =stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getId(),magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getSousFamille().getId());
	  	                stockOffreVerre=stockpfVerresOffre.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
	  	                stockpfVerresOffre.setStock(stockOffreVerre);
	  	                stockpfDAO.edit(stockpfVerresOffre);
	  	                listStockPF.remove(tableArticle.getSelectedRow()+1);
	  	                
	  	              DetailFacturePF detailfacturepfVerres=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
		 	        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
		 	        	 detailFacturePfdao.delete(detailfacturepfVerres.getId());
		 	        	 DetailTransferProduitFini detailtransferstockPFVerres=listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+1);
		 	        	 detailTransferStockPFdao.delete(detailtransferstockPFVerres.getId());
		 				 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
			                
			           	
			                
						        		} 
			        			 }
		        			 }
		        		 
							 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
		        			 {
								 
								 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
			        			 {
									 
										if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
						        		{
	/////////////////////////////////////////////////////// Article Promo ////////////////////////////////////////////////
							        		
			        				 StockPF stockpfArticlePromo =stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getId(),magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getSousFamille().getId());
			        				 stockpfArticlePromo.setStock(stockpfArticlePromo.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getQuantite()));
			     	                stockpfDAO.edit(stockpfArticlePromo);
			     	                listStockPF.remove(tableArticle.getSelectedRow()+1);
			     	                
			     	                
			     	               DetailFacturePF detailfacturepfPromo=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
			     		        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
			     		        	 detailFacturePfdao.delete(detailfacturepfPromo.getId());
			     		        	 DetailTransferProduitFini detailtransferstockPFPromo=listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+1);
			     		        	 detailTransferStockPFdao.delete(detailtransferstockPFPromo.getId());
			     					 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
			     	          	
			     	                
						        		} 
										
			        			 }
								 
		        			 }
				        		 
					

				        	
		        			 
		        		 }
				    	 
				    	 
				     }

				     				
				        	   
				                
				        	 }
				        	 
				        	 
				        	 
				        	 detailFacturePfdao.delete(detailfacturepf.getId());
					        listDetailFacturePF.remove(tableArticle.getSelectedRow());					  		
				  	    	DetailTransferProduitFini detailtransferstockPF=listDetailTransferProduitFini.get(tableArticle.getSelectedRow());
					  		detailTransferStockPFdao.delete(detailtransferstockPF.getId());
					  		listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
					  	  listStockPF.remove(tableArticle.getSelectedRow());
					        afficher_tableDetailFacturePF(listDetailFacturePF);
					          int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO;
						        BigDecimal lamarge=BigDecimal.ZERO;
						        BigDecimal compteur=BigDecimal.ZERO;
						        while(i<listDetailFacturePF.size())
						        {
						        	 DetailFacturePF detailFacturePF=listDetailFacturePF.get(i);
						        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(detailFacturePF.getArticle().getId(),magasin.getId(),detailFacturePF.getSousFamille().getId());
							          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
							          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
							          if(!detailFacturePF.getPrixUnitaire().setScale(2,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP)))
							          {
							        	  compteur=compteur.add(BigDecimal.ONE);
							        	  lamarge=lamarge.add((detailFacturePF.getPrixUnitaire().subtract(stockpf.getPrixUnitaire())).divide(detailFacturePF.getPrixUnitaire(), RoundingMode.HALF_UP));  
							          }
						            i++;
						        }
						       txttotalmontantTTC.setText(montanttotal+"");
						       txttotalquantite.setText(sommequantite+"");
					  			txttotalmontantHT.setText(montanttotalHT+"");
					  			txttotalmontantTVA.setText(montanttotalTVA+"");
					  			if(!compteur.equals(BigDecimal.ZERO))
					  			{
					  				 Parametre margemax=parametredao.findByCode(Constantes.MARGE_MAX);
						        	 Parametre margemin=parametredao.findByCode(Constantes.MARGE_MIN);
						  			
						  			 if((lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemax.getValeur())>0 || (lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemin.getValeur())<0)
						  			 {
						  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
						  				labelMarge.setBackground(Color.red);
						  			 }else
						  			 {
						  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
						  				labelMarge.setBackground(Color.GREEN);
						  			 }
						  			 
						  				
						  			}
					  			
					  			JOptionPane.showMessageDialog(null, "Article supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					  			initialiser();
						}
		  			
		  		}
		  		
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1395, 736, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(1119, 973, 112, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(1241, 973, 136, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(1012, 916, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(905, 916, 97, 26);
		add(lblTotalQuantite);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1395, 706, 73, 24);
		add(btnModifier);
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter les BL/Factures par :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(296, 11, 791, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1458, 218);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				boolean trouve=false;
				if(table.getSelectedRow()!=-1)
				{
					
				
						 int column = table.columnAtPoint( arg0.getPoint() );
		  		     	 int row =table.rowAtPoint(arg0.getPoint());
		  		     	 
						if(column==9)
						{
							if(listFacturePF.size()!=0)
							{
								listStockPF.clear();
								listDetailFacturePFCumule.clear(); 
							for(int j=0;j<table.getRowCount();j++){
								
								boolean regle=(boolean) table.getValueAt(j, 9);
								if(regle==true ){
									
									facturePF=listFacturePF.get(j) ;
									 //facturePF.getType().equals(Constantes.TYPE_FACTURE) && facturePF.getEtat().equals(Constantes.ETAT_REGLE)    
									 if(facturePF.getType().equals(Constantes.TYPE_FACTURE) || facturePF.getType().equals(Constantes.TRANSFERE_BL_FACTURE))
									 {
										
											btnAjouter.setEnabled(false);
											btnModifier.setEnabled(false);
											btnSupprimer.setEnabled(false);
											buttonvalider.setEnabled(false);
											btninitialiser.setEnabled(false);
											//supprimer_facture.setEnabled(true);
											trouve=true;
											
										}else if(facturePF.getType().equals(Constantes.TYPE_BON_LIVRAISON))
										{
											btnAjouter.setEnabled(true);
											btnModifier.setEnabled(true);
											btnSupprimer.setEnabled(true);
											buttonvalider.setEnabled(true);
											btninitialiser.setEnabled(true);
											//supprimer_facture.setEnabled(true);
											trouve=false;
										}
									 
									 transferStock=transferStockPFDAO.findByCodeTransfert(facturePF.getCodeTransfer());
										listDetailTransferProduitFini=detailTransferStockPFdao.findByTransferStockPF(transferStock.getId());
										
										txtnumbl.setText(facturePF.getNumBl());
										dateChooserfacture.setDate(facturePF.getDateFacture());
										
										combodepot.setSelectedItem(facturePF.getDepot().getLibelle());
										
										if(facturePF.getTypeBL()!=null)
										{
											comboTypeBL.setSelectedItem(facturePF.getTypeBL().getType());
											
										}else
										{
											comboTypeBL.setSelectedItem("");
										}
										
										
										 
										if(facturePF.getImprimerPiece()!=null)
										{
											
											chckbxPiece.setSelected(facturePF.getImprimerPiece());
											
										}
											
										 
										
										
										
										combomagasin.setSelectedItem(facturePF.getMagasin().getLibelle());
										comboClientpf.setSelectedItem(facturePF.getClientPF().getNom());
										listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(facturePF.getId());
										for(int t=0;t<listDetailFacturePF.size();t++)
										{
											
											DetailFacturePFCumule detailFacturePFCumule=new DetailFacturePFCumule();
											detailFacturePFCumule.setArticle(listDetailFacturePF.get(t).getArticle());
											detailFacturePFCumule.setFacturePF(listDetailFacturePF.get(t).getFacturePF());
											detailFacturePFCumule.setMontantHT(listDetailFacturePF.get(t).getMontantHT());
											detailFacturePFCumule.setMontantTTC(listDetailFacturePF.get(t).getMontantTTC());
											detailFacturePFCumule.setMontantTVA(listDetailFacturePF.get(t).getMontantTVA());
											detailFacturePFCumule.setPrixUnitaire(listDetailFacturePF.get(t).getPrixUnitaire());
											detailFacturePFCumule.setQuantite(listDetailFacturePF.get(t).getQuantite());
											detailFacturePFCumule.setReduction(listDetailFacturePF.get(t).getReduction());
											detailFacturePFCumule.setSousFamille(listDetailFacturePF.get(t).getSousFamille());
											detailFacturePFCumule.setTva(listDetailFacturePF.get(t).getTva());
											detailFacturePFCumule.setTypeVente(listDetailFacturePF.get(t).getTypeVente());
											detailFacturePFCumule.setUtilisateur(listDetailFacturePF.get(t).getUtilisateur());
											 listDetailFacturePFCumule.add(detailFacturePFCumule);
											
										}
									
									 
								}
								
							}	
						
						
						
						 
					
						 
					 
						
					//	listDetailFacturePF=facturePF.getDetailFacturePF();
						 int i=0;
					        BigDecimal montanttotal=BigDecimal.ZERO;
					        BigDecimal sommequantite=BigDecimal.ZERO;
					        BigDecimal montanttotalHT=BigDecimal.ZERO;
					        BigDecimal montanttotalTVA=BigDecimal.ZERO; 
					        BigDecimal lamarge=BigDecimal.ZERO;
					        BigDecimal compteur=BigDecimal.ZERO;
					        while(i<listDetailFacturePFCumule.size())
					        {
					        	 
					        	  DetailFacturePFCumule detailFacturePFCumule=listDetailFacturePFCumule.get(i);
						          montanttotal=  montanttotal.add(detailFacturePFCumule.getMontantTTC());
						          sommequantite= sommequantite.add(detailFacturePFCumule.getQuantite());
						          montanttotalHT=montanttotalHT.add(detailFacturePFCumule.getMontantHT());
						          montanttotalTVA=montanttotalTVA.add(detailFacturePFCumule.getMontantTVA());
						        
					            i++;
					        }
					       txttotalmontantTTC.setText(montanttotal+"");
					        txttotalquantite.setText(sommequantite+"");
					        txttotalmontantHT.setText(montanttotalHT+"");
				  			txttotalmontantTVA.setText(montanttotalTVA+"");
				  		 
				  		
						afficher_tableDetailFacturePFCumule(listDetailFacturePFCumule);
						
						if(trouve==false)
						{
							initialiser();
						}else
						{
							initialiserPourFacture();
						}
					}
					
			
						
						
							btnAjouter.setEnabled(false);
							btnModifier.setEnabled(false);
							btnSupprimer.setEnabled(false);
							buttonvalider.setEnabled(false);
							btninitialiser.setEnabled(false);
						
						
						
						
						
						
					}else
					{
						
						
						
						if(listFacturePF.size()!=0)
						{
							listStockPF.clear();
							 facturePF=listFacturePF.get(table.getSelectedRow()) ;
							 //facturePF.getType().equals(Constantes.TYPE_FACTURE) && facturePF.getEtat().equals(Constantes.ETAT_REGLE)    
							 if(facturePF.getType().equals(Constantes.TYPE_FACTURE) || facturePF.getType().equals(Constantes.TRANSFERE_BL_FACTURE))
							 {
								
									btnAjouter.setEnabled(false);
									btnModifier.setEnabled(false);
									btnSupprimer.setEnabled(false);
									buttonvalider.setEnabled(false);
									btninitialiser.setEnabled(false);
									//supprimer_facture.setEnabled(true);
									trouve=true;
									
								}else if(facturePF.getType().equals(Constantes.TYPE_BON_LIVRAISON))
								{
									btnAjouter.setEnabled(true);
									btnModifier.setEnabled(true);
									btnSupprimer.setEnabled(true);
									buttonvalider.setEnabled(true);
									btninitialiser.setEnabled(true);
									//supprimer_facture.setEnabled(true);
									trouve=false;
								}
							 
						 
							transferStock=transferStockPFDAO.findByCodeTransfert(facturePF.getCodeTransfer());
							listDetailTransferProduitFini=detailTransferStockPFdao.findByTransferStockPF(transferStock.getId());
							
							txtnumbl.setText(facturePF.getNumBl());
							dateChooserfacture.setDate(facturePF.getDateFacture());
							
							combodepot.setSelectedItem(facturePF.getDepot().getLibelle());
							
							if(facturePF.getTypeBL()!=null)
							{
								comboTypeBL.setSelectedItem(facturePF.getTypeBL().getType());
								
							}else
							{
								comboTypeBL.setSelectedItem("");
							}
							
							
							 
							if(facturePF.getImprimerPiece()!=null)
							{
								
								chckbxPiece.setSelected(facturePF.getImprimerPiece());
								
							}
								
							 
							
							
							
							combomagasin.setSelectedItem(facturePF.getMagasin().getLibelle());
							comboClientpf.setSelectedItem(facturePF.getClientPF().getNom());
							listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(facturePF.getId());
						//	listDetailFacturePF=facturePF.getDetailFacturePF();
							 int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO; 
						        BigDecimal lamarge=BigDecimal.ZERO;
						        BigDecimal compteur=BigDecimal.ZERO;
						        while(i<listDetailFacturePF.size())
						        {
						        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(i).getArticle().getId(),facturePF.getMagasin().getId(),listDetailFacturePF.get(i).getSousFamille().getId());
						        	 listStockPF.add(stockpf);
						        	  DetailFacturePF detailFacturePF=listDetailFacturePF.get(i);
							          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
							          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
							          if(detailFacturePF.getPrixUnitaire().compareTo(BigDecimal.ZERO)!=0)
							          {
							        	  compteur=compteur.add(BigDecimal.ONE);
							        	  lamarge=lamarge.add((detailFacturePF.getPrixUnitaire().subtract(stockpf.getPrixUnitaire())).divide(detailFacturePF.getPrixUnitaire(), RoundingMode.HALF_UP));  
							          }
						            i++;
						        }
						       txttotalmontantTTC.setText(montanttotal+"");
						        txttotalquantite.setText(sommequantite+"");
						        txttotalmontantHT.setText(montanttotalHT+"");
					  			txttotalmontantTVA.setText(montanttotalTVA+"");
					  			 if(compteur.compareTo(BigDecimal.ZERO)!=0)
						  			{
						  			 Parametre margemax=parametredao.findByCode(Constantes.MARGE_MAX);
						        	 Parametre margemin=parametredao.findByCode(Constantes.MARGE_MIN);
						        	 
						  			 if((lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemax.getValeur())>0 || (lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).compareTo(margemin.getValeur())<0)
						  			 {
						  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
						  				labelMarge.setBackground(Color.red);
						  			 }else
						  			 {
						  				labelMarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
						  				labelMarge.setBackground(Color.GREEN);
						  			 }
						  			 
						  				
						  			}
					  		
							afficher_tableDetailFacturePF(listDetailFacturePF);
							
							if(trouve==false)
							{
								initialiser();
							}else
							{
								initialiserPourFacture();
							}
						}
						
					}
					
					
	  		     		
	  		     		
	  		     		
	  		     		
			
					
				}
				
			}
		});
		
		
		scrollPane_1.setViewportView(table);
		table.setColumnControlVisible(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Type BL","Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Magasin", "Montant TTC","Transfere BL en Facture"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(136);
		table.getColumnModel().getColumn(7).setPreferredWidth(136);
		table.getColumnModel().getColumn(8).setPreferredWidth(136);
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(51, 204, 255));
		table.setRowHeightEnabled(true);
		table.setRowHeight(20);
		table.setGridColor(Color.BLUE);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setAutoCreateRowSorter(true);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		detailFacturePfdao.ViderSession();
	    		
	    		if(combopardepot.getSelectedItem().equals(""))
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}	
	    	if(comboparclient.getSelectedItem().equals("")
	    			&& txtparnumBL.getText().equals("") 
	    			&& pardateChooser.getDate()==null
	    			&& combopardepot.getSelectedItem().equals("") )
	    	{
	    		
	    		initialiser();
	    		initialiserFacture();
	    		InitialiseTableau();
	    		InitialiseTableauFacture();
	    		
	    	}else
	    	{
	    		
	    		chargerListeFacture();
	    		
	    		
	    	}
	    				
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(524, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLabel label_2 = new JLabel("Total Montant TVA :");
	    label_2.setBounds(1119, 942, 105, 26);
	    add(label_2);
	    
	    txttotalmontantTVA = new JTextField();
	    txttotalmontantTVA.setEditable(false);
	    txttotalmontantTVA.setColumns(10);
	    txttotalmontantTVA.setBounds(1241, 942, 136, 26);
	    add(txttotalmontantTVA);
	    
	    txttotalmontantHT = new JTextField();
	    txttotalmontantHT.setEditable(false);
	    txttotalmontantHT.setColumns(10);
	    txttotalmontantHT.setBounds(1241, 910, 136, 26);
	    add(txttotalmontantHT);
	    
	    JLabel label_5 = new JLabel("Total Montant HT :");
	    label_5.setBounds(1119, 910, 105, 26);
	    add(label_5);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1783, 51);
	    add(layeredPane_2);
	    
	    JLabel lblNumFacture = new JLabel("Num BL :");
	    lblNumFacture.setBounds(230, 12, 97, 24);
	    layeredPane_2.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtparnumBL = new JTextField();
	    txtparnumBL.setBounds(297, 12, 102, 26);
	    layeredPane_2.add(txtparnumBL);
	    util.Utils.copycoller(txtparnumBL);
	    txtparnumBL.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {}
	    });
	    txtparnumBL.setColumns(10);
	    
	    JLabel lblClient_1 = new JLabel("Client :");
	    lblClient_1.setBounds(409, 12, 56, 24);
	    layeredPane_2.add(lblClient_1);
	    lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    JLabel lblDateFacture = new JLabel("Au :");
	    lblDateFacture.setBounds(853, 14, 34, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     pardateChooser = new JDateChooser();
	     pardateChooser.setBounds(897, 15, 151, 26);
	     layeredPane_2.add(pardateChooser);
	     pardateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {}
	     });
	     pardateChooser.addKeyListener(new KeyAdapter() {
	     	@Override
	     	public void keyPressed(KeyEvent e) {
	     		
	     		
	     		
	     	}
	     });
	     pardateChooser.setLocale(Locale.FRANCE);
	     pardateChooser.setDateFormatString("dd/MM/yyyy");
	     
	     combopardepot = new JComboBox();
	     combopardepot.setBounds(1120, 15, 151, 24);
	     layeredPane_2.add(combopardepot);
	     combopardepot.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {}
	     });
	     combopardepot.setSelectedIndex(-1);
	     
	     JLabel lblDepot = new JLabel("Depot  :");
	     lblDepot.setBounds(1058, 14, 97, 24);
	     layeredPane_2.add(lblDepot);
	     lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     
	     JLabel label_10 = new JLabel("Famille Article :");
	     label_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     label_10.setBounds(1281, 14, 87, 24);
	     layeredPane_2.add(label_10);
	     comboparsousfamille = new JComboBox();
		    
	     comboparsousfamille.setBounds(1622, 15, 151, 24);
	     layeredPane_2.add(comboparsousfamille);
	     
	      comboparfamille = new JComboBox();
	     comboparfamille.addItemListener(new ItemListener() {
	     	public void itemStateChanged(ItemEvent e) {
        		
       		 if(e.getStateChange() == ItemEvent.SELECTED)
    	 		 {
       			 if(!comboparfamille.getSelectedItem().equals(""))
       			 {
       				 
       					FamilleArticlePF famille=mapfamille.get(comboparfamille.getSelectedItem());
   		        		if(famille!=null)
   		        		{
   		        			comboparsousfamille.removeAllItems();
   		        			 comboparsousfamille.addItem("");
   		        			listSousFamilleArticle=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePF(famille.getId());
   		        			for(int i=0;i<listSousFamilleArticle.size();i++)
   		        			{
   		        			SousFamilleArticlePF sousfamille=listSousFamilleArticle.get(i);
   		        			comboparsousfamille.addItem(sousfamille.getLiblle());
   		        			mapsousfamille.put(sousfamille.getLiblle(), sousfamille);
   		        				
   		        			}
   		        			
   		        		}else
   		        		{
   		        			 comboparsousfamille.removeAllItems();
   		        		}
   		        			
       				 
       			 }else
       			 {
       				comboparsousfamille.removeAllItems();
       			 }
       			 
       			
       	 
       			
       			 
    	 		 }
    	 			 
       		
       		
       	}
	     });
	     comboparfamille.setSelectedIndex(-1);
	     comboparfamille.setBounds(1363, 15, 167, 24);
	     layeredPane_2.add(comboparfamille);
	     
	     int l=0;
	      comboparfamille.addItem("");
	      while(l<listFamilleArticle.size())
	      {
	    	  
	    	  FamilleArticlePF famillearticle=listFamilleArticle.get(l);
	    	  comboparfamille.addItem(famillearticle.getLiblle());
	    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
	    	  l++;
	      }
	     
	     JLabel label_11 = new JLabel("Sous Famille :");
	     label_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     label_11.setBounds(1540, 15, 87, 24);
	     layeredPane_2.add(label_11);
	     
	      comboparclient = new JComboBox();
	     comboparclient.setSelectedIndex(-1);
	     comboparclient.setBounds(460, 13, 178, 24);
	     layeredPane_2.add(comboparclient);
	     
	     AutoCompleteDecorator.decorate(comboparclient);
	   
	      btninitialiser = new JButton("Initialiser");
	     btninitialiser.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     	initialiser();
	     	}
	     });
	     btninitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     btninitialiser.setBounds(622, 625, 106, 23);
	     add(btninitialiser);
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		

	     		
	     		txtparnumBL.setText("");
	     		comboparclient.setSelectedItem("");
	     		pardateChooser.setCalendar(null);
	     		combopardepot.setSelectedItem("");
	     		comboparfamille.setSelectedItem("");
	     		comboparsousfamille.setSelectedItem("");
	     	
	     		comboParTypeBL.setSelectedItem("");
	     		
	     		
	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(641, 120, 106, 23);
	     add(button);
	     
	     JButton btnTransfereBl = new JButton("Transfere BL en Facture");
	     btnTransfereBl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				/////////////////////////////////////////////////////////////////////////////////////// Numerotation 2020 //////////////////////////////////////////////////////////////////////
				
	               boolean transferEnBL=false;
		    		
	               
		    		maptransfereblfacture.clear();
		    		if(!remplirmaptransfereblfacture())	{
						JOptionPane.showMessageDialog(null, "Il faut remplir les BL à transferer au facture", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						
						if(maptransfereblfacture.size()>=2)
						{
							 int reponse = JOptionPane.showConfirmDialog(null, "Voulez Vous Facturer les BL en une seule facture ?", 
										"Satisfaction", JOptionPane.YES_NO_OPTION);
								 
								if(reponse == JOptionPane.YES_OPTION )		
									
								{
									
									
									boolean existe=true;
									
									ClientPF clientPF=listClientFacture.get(0);
									
									for(int j=0;j<listClientFacture.size();j++)
									{
										
									if(listClientFacture.get(j).getId()!=clientPF.getId())
									{
										existe=false;
									}
										
									}
									
									if(existe==false)
									{
										 JOptionPane.showMessageDialog (null, "Veuillez selectionner le Meme Client Pour Une Seule facture SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
	                             		 return;
									}
									
									
									
									/// Numerotation 2019
									SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
									
									///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
								
	                             //	FacturePF facturepfTmp=listFacturePF.get(line);
	                             	 String date =""; 
	                             	 String NumFacture="";
	                             	 if(datefacture.getDate()!=null)
	                             	 {
	                             		 
	                             		 
	                             		 
	                             		/* 
	                             		ChercherBLNonFacture(datefacture.getDate());	 
	                             		 if(!MessageBlNonFacturer.equals(""))
	                             		 {
	                             			 
	                             			JOptionPane.showMessageDialog(null, MessageBlNonFacturer); 
	                             			 return;
	                             			 
	                             		 }
	                             		 */

	                             		 
	                             		date= dcn.format(datefacture.getDate());
	                             		NumFacture=Utils.genererCodeFactureVente(date);
	                             		
	                             	 }else
	                             	 {
	                             		 JOptionPane.showMessageDialog (null, "Veuillez selectionner la date facture SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
	                             		 return;
	                             	 }
	                             	 
									for(int i=0;i<listFacturePF.size();i++)
									{
										FacturePF facturepf=listFacturePF.get(i);
										if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
										{
											if(maptransfereblfacture.containsKey(facturepf.getNumBl()))
											{
												if(facturepf.getNumFacture()==null)
												{
													facturepf.setNumFacture(NumFacture);
													facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
													facturepf.setDateFacture(datefacture.getDate());
													
													facturepf.setTraite(BigDecimal.ZERO);
													facturepf.setCheque(BigDecimal.ZERO);
													facturepf.setCredit(BigDecimal.ZERO);
													facturepf.setEspece(BigDecimal.ZERO);
													facturepf.setVersement(BigDecimal.ZERO);
													facturepf.setVirement(BigDecimal.ZERO);
													facturepf.setNumCheque("");
													facturepf.setNumtraite("");
													facturepf.setNumVersement("");
													
													facturepfdao.edit(facturepf);			
													transferEnBL=true;
												}else
												{
													facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
													facturepf.setTraite(BigDecimal.ZERO);
													facturepf.setCheque(BigDecimal.ZERO);
													facturepf.setCredit(BigDecimal.ZERO);
													facturepf.setEspece(BigDecimal.ZERO);
													facturepf.setVersement(BigDecimal.ZERO);
													facturepf.setVirement(BigDecimal.ZERO);
													facturepf.setNumCheque("");
													facturepf.setNumtraite("");
													facturepf.setNumVersement("");
													facturepfdao.edit(facturepf);	
												}
												
											}
										}
											
									}
									
									
									if(transferEnBL==true)
									{
										
										if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
											
										{
											Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_ETP);
											if(sequenceur!=null)
											{
												int valeur=sequenceur.getValeur()+1;
												sequenceur.setValeur(valeur);
												sequenceurDAO.edit(sequenceur);
											}else
											{
												Sequenceur  sequenceurTmp=new Sequenceur();
												sequenceurTmp.setCode(date);
												sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
												sequenceurTmp.setValeur(1);
												sequenceurDAO.add(sequenceurTmp);
											}
											
										}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
										{
											Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_AHB);
											if(sequenceur!=null)
											{
												int valeur=sequenceur.getValeur()+1;
												sequenceur.setValeur(valeur);
												sequenceurDAO.edit(sequenceur);
											}else
											{
												Sequenceur  sequenceurTmp=new Sequenceur();
												sequenceurTmp.setCode(date);
												sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
												sequenceurTmp.setValeur(1);
												sequenceurDAO.add(sequenceurTmp);
											}
											
										}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_ELALAOUI))
										{
											Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_ALAOUI);
											if(sequenceur!=null)
											{
												int valeur=sequenceur.getValeur()+1;
												sequenceur.setValeur(valeur);
												sequenceurDAO.edit(sequenceur);
											}else
											{
												Sequenceur  sequenceurTmp=new Sequenceur();
												sequenceurTmp.setCode(date);
												sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ALAOUI);
												sequenceurTmp.setValeur(1);
												sequenceurDAO.add(sequenceurTmp);
											}
											
										}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_FOURSSAN_ALJANOUB))
										{
											Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_FOURSSAN);
											if(sequenceur!=null)
											{
												int valeur=sequenceur.getValeur()+1;
												sequenceur.setValeur(valeur);
												sequenceurDAO.edit(sequenceur);
											}else
											{
												Sequenceur  sequenceurTmp=new Sequenceur();
												sequenceurTmp.setCode(date);
												sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_FOURSSAN);
												sequenceurTmp.setValeur(1);
												sequenceurDAO.add(sequenceurTmp);
											}
											
										}
										
										
										
										JOptionPane.showMessageDialog(null, "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
										listFacturePFMAJ=facturepfdao.findAll();
										
										chargerListeFacture();
									}else
									{
										JOptionPane.showMessageDialog(null, "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
										
									}
									
									
								}else
								{
									
									for(int i=0;i<listFacturePF.size();i++)
									{
										FacturePF facturepf=listFacturePF.get(i);
										
										if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
										{
											if(maptransfereblfacture.containsKey(facturepf.getNumBl()))
											{
												/// Numerotation 2019
												SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
												
												///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
												
												if(datefacture.getDate()!=null)
												{
												
													facturepf.setDateFacture(datefacture.getDate());	
													
												}
												
											
												 String date = dcn.format(facturepf.getDateFacture());
												 if(facturepf.getNumFacture()==null)
													{
													 
													 /*
													 
													 ChercherBLNonFacture(facturepf.getDateFacture());	 
				                             		 if(!MessageBlNonFacturer.equals(""))
				                             		 {
				                             			 
				                             			JOptionPane.showMessageDialog(null, MessageBlNonFacturer); 
				                             			 return;
				                             			 
				                             		 }
				                             		 */
													 
													 
													 
													 facturepf.setNumFacture(Utils.genererCodeFactureVente(date));
														facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
														facturepf.setTraite(BigDecimal.ZERO);
														facturepf.setCheque(BigDecimal.ZERO);
														facturepf.setCredit(BigDecimal.ZERO);
														facturepf.setEspece(BigDecimal.ZERO);
														facturepf.setVersement(BigDecimal.ZERO);
														facturepf.setVirement(BigDecimal.ZERO);
														facturepf.setNumCheque("");
														facturepf.setNumtraite("");
														facturepf.setNumVersement("");
														facturepfdao.edit(facturepf);
														if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
															
														{
															Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_ETP);
															if(sequenceur!=null)
															{
																int valeur=sequenceur.getValeur()+1;
																sequenceur.setValeur(valeur);
																sequenceurDAO.edit(sequenceur);
															}else
															{
																Sequenceur  sequenceurTmp=new Sequenceur();
																sequenceurTmp.setCode(date);
																sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
																sequenceurTmp.setValeur(1);
																sequenceurDAO.add(sequenceurTmp);
															}
															
														}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
														{
															Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_AHB);
															if(sequenceur!=null)
															{
																int valeur=sequenceur.getValeur()+1;
																sequenceur.setValeur(valeur);
																sequenceurDAO.edit(sequenceur);
															}else
															{
																Sequenceur  sequenceurTmp=new Sequenceur();
																sequenceurTmp.setCode(date);
																sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
																sequenceurTmp.setValeur(1);
																sequenceurDAO.add(sequenceurTmp);
															}
															
														}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_ELALAOUI))
														{
															Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_ALAOUI);
															if(sequenceur!=null)
															{
																int valeur=sequenceur.getValeur()+1;
																sequenceur.setValeur(valeur);
																sequenceurDAO.edit(sequenceur);
															}else
															{
																Sequenceur  sequenceurTmp=new Sequenceur();
																sequenceurTmp.setCode(date);
																sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ALAOUI);
																sequenceurTmp.setValeur(1);
																sequenceurDAO.add(sequenceurTmp);
															}
															
														}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_FOURSSAN_ALJANOUB))
														{
															Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_FOURSSAN);
															if(sequenceur!=null)
															{
																int valeur=sequenceur.getValeur()+1;
																sequenceur.setValeur(valeur);
																sequenceurDAO.edit(sequenceur);
															}else
															{
																Sequenceur  sequenceurTmp=new Sequenceur();
																sequenceurTmp.setCode(date);
																sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_FOURSSAN);
																sequenceurTmp.setValeur(1);
																sequenceurDAO.add(sequenceurTmp);
															}
															
														}
														
														transferEnBL=true;
													}else
													{
														
														facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
														facturepf.setTraite(BigDecimal.ZERO);
														facturepf.setCheque(BigDecimal.ZERO);
														facturepf.setCredit(BigDecimal.ZERO);
														facturepf.setEspece(BigDecimal.ZERO);
														facturepf.setVersement(BigDecimal.ZERO);
														facturepf.setVirement(BigDecimal.ZERO);
														facturepf.setNumCheque("");
														facturepf.setNumtraite("");
														facturepf.setNumVersement("");
														facturepfdao.edit(facturepf);
														transferEnBL=true;
														
													}
											
											}
										}
										
									}
									
									
									if(transferEnBL==true)
									{
										JOptionPane.showMessageDialog(null, "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
										listFacturePFMAJ=facturepfdao.findAll();
										
										chargerListeFacture();
									}else
									{
										JOptionPane.showMessageDialog(null, "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
										
									}
									
									
									
								}
							
							
						}else
						{
							
							
							for(int i=0;i<listFacturePF.size();i++)
							{
								FacturePF facturepf=listFacturePF.get(i);
								if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
								{
									
									if(maptransfereblfacture.containsKey(facturepf.getNumBl()))
									{
										/// Numerotation 2019
										SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
										
										///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
										
										
										if(datefacture.getDate()!=null)
										{
										
											facturepf.setDateFacture(datefacture.getDate());	
											
										}
										
										
									
										 String date = dcn.format(facturepf.getDateFacture());
										 
										 
										 if(facturepf.getNumFacture()==null)
											{
											
											 /*
											 ChercherBLNonFacture(facturepf.getDateFacture());	 
		                             		 if(!MessageBlNonFacturer.equals(""))
		                             		 {
		                             			 
		                             			JOptionPane.showMessageDialog(null, MessageBlNonFacturer); 
		                             			 return;
		                             			 
		                             		 }
		                             		 */
		                             		
											 
											 
											 facturepf.setNumFacture(Utils.genererCodeFactureVente(date));
												facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
												facturepf.setTraite(BigDecimal.ZERO);
												facturepf.setCheque(BigDecimal.ZERO);
												facturepf.setCredit(BigDecimal.ZERO);
												facturepf.setEspece(BigDecimal.ZERO);
												facturepf.setVersement(BigDecimal.ZERO);
												facturepf.setVirement(BigDecimal.ZERO);
												facturepf.setNumCheque("");
												facturepf.setNumtraite("");
												facturepf.setNumVersement("");
												facturepfdao.edit(facturepf);
												if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
													
												{
													Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_ETP);
													if(sequenceur!=null)
													{
														int valeur=sequenceur.getValeur()+1;
														sequenceur.setValeur(valeur);
														sequenceurDAO.edit(sequenceur);
													}else
													{
														Sequenceur  sequenceurTmp=new Sequenceur();
														sequenceurTmp.setCode(date);
														sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
														sequenceurTmp.setValeur(1);
														sequenceurDAO.add(sequenceurTmp);
													}
													
												}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
												{
													Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_AHB);
													if(sequenceur!=null)
													{
														int valeur=sequenceur.getValeur()+1;
														sequenceur.setValeur(valeur);
														sequenceurDAO.edit(sequenceur);
													}else
													{
														Sequenceur  sequenceurTmp=new Sequenceur();
														sequenceurTmp.setCode(date);
														sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
														sequenceurTmp.setValeur(1);
														sequenceurDAO.add(sequenceurTmp);
													}
													
												}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_ELALAOUI))
												{
													Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_ALAOUI);
													if(sequenceur!=null)
													{
														int valeur=sequenceur.getValeur()+1;
														sequenceur.setValeur(valeur);
														sequenceurDAO.edit(sequenceur);
													}else
													{
														Sequenceur  sequenceurTmp=new Sequenceur();
														sequenceurTmp.setCode(date);
														sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ALAOUI);
														sequenceurTmp.setValeur(1);
														sequenceurDAO.add(sequenceurTmp);
													}
													
												}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_FOURSSAN_ALJANOUB))
												{
													Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_FOURSSAN);
													if(sequenceur!=null)
													{
														int valeur=sequenceur.getValeur()+1;
														sequenceur.setValeur(valeur);
														sequenceurDAO.edit(sequenceur);
													}else
													{
														Sequenceur  sequenceurTmp=new Sequenceur();
														sequenceurTmp.setCode(date);
														sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_FOURSSAN);
														sequenceurTmp.setValeur(1);
														sequenceurDAO.add(sequenceurTmp);
													}
													
												}
												
												transferEnBL=true;
											 
											 
											 
											}else
											{
												
												facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
												facturepf.setTraite(BigDecimal.ZERO);
												facturepf.setCheque(BigDecimal.ZERO);
												facturepf.setCredit(BigDecimal.ZERO);
												facturepf.setEspece(BigDecimal.ZERO);
												facturepf.setVersement(BigDecimal.ZERO);
												facturepf.setVirement(BigDecimal.ZERO);
												facturepf.setNumCheque("");
												facturepf.setNumtraite("");
												facturepf.setNumVersement("");
												facturepfdao.edit(facturepf);
												transferEnBL=true;
											}
										
									}
									
								}
							
							}
							
							
							if(transferEnBL==true)
							{
								JOptionPane.showMessageDialog(null, "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
								listFacturePFMAJ=facturepfdao.findAll();
								
								chargerListeFacture();
							}else
							{
								JOptionPane.showMessageDialog(null, "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
								
							}
							
						}
						
						datefacture.setCalendar(null);
							
					}
		    		
				
				
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
				
				
//////////////////////////////////////////////////////////////////////////////////////// New Numerotation 2019 ///////////////////////////////////////////////				
				/*
				
				  // New Numerotation 2019
				  
				  boolean transferEnBL=false; Parametre
				  nombre_facture_par_jour=parametreDAO.findByCode(Constantes. NOMBRE_FACTURE_PAR_JOUR);
				  
				  maptransfereblfacture.clear(); 
				  if(!remplirmaptransfereblfacture()) { JOptionPane.showMessageDialog(null,
				  "Il faut remplir les BL à transferer au facture", "Erreur",
				  JOptionPane.ERROR_MESSAGE); }
				  else {
				  
				  if(maptransfereblfacture.size()>=2) { int reponse =
				  JOptionPane.showConfirmDialog(null,
				  "Voulez Vous Facturer les BL en une seule facture ?", "Satisfaction",
				  JOptionPane.YES_NO_OPTION);
				  
				  if(reponse == JOptionPane.YES_OPTION )
				  
				  {
				  
				  /// Numerotation 2019 //// 
					  SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				  
				  /// Numerotation 2018 SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				  
				  // FacturePF facturepfTmp=listFacturePF.get(line); 
					  String date =""; 
					  String NumFacture=""; Date d=null;
				  
				  if(datefacture.getDate()!=null) 
				  {
					  d=datefacture.getDate(); 
				  date= dcn.format(d); 
				  NumFacture=Utils.genererCodeFactureVente(d);
				  
				  if(NumFacture.equals("")) {
				  
				  d=util.DateUtils.ajoutNbJours(d, 1); 
				  Calendar cal=Calendar.getInstance();
				  cal.setTime(d);
				  
				  if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
				  d=util.DateUtils.ajoutNbJours(d, 1); 
				  } while(NumFacture.equals("")) {
				  
				  NumFacture=Utils.genererCodeFactureVente(d);
				  
				  if(!NumFacture.equals("")) { date = dcn.format(d); break;
				  
				  }else {
				  
				  d= util.DateUtils.ajoutNbJours(d, 1);
				  Calendar calTmp=Calendar.getInstance();
				  calTmp.setTime(d);
				  
				  if(calTmp.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
				  d=util.DateUtils.ajoutNbJours(d, 1); }
				  
				  
				  
				  }
				  
				  
				  };
				  
				  }
				  
				  }else { JOptionPane.showMessageDialog (null,
				  "Veuillez selectionner la date facture SVP !!!!!","Erreur",
				  JOptionPane.ERROR_MESSAGE); return; }
				  
				  
				  Calendar c = Calendar.getInstance();
				  
				  c.setTime(d); int nbrjrs=c.get(Calendar.DAY_OF_YEAR); 
				  int nbrsunday = nbrjrs / 7; 
				  int nbrjrssansweekend=nbrjrs-nbrsunday;
				  
				  
				  for(int i=0;i<listFacturePF.size();i++) { FacturePF
				  facturepf=listFacturePF.get(i);
				  if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
				  if(maptransfereblfacture.containsKey(facturepf.getNumBl())) {
				  if(facturepf.getNumFacture()==null) { facturepf.setNumFacture(NumFacture);
				  facturepf.setDateFacture(d);
				  facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
				  facturepfdao.edit(facturepf); transferEnBL=true; }else {
				  facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
				  
				  facturepfdao.edit(facturepf); }
				  
				  } }
				  
				  }
				  
				  
				  if(transferEnBL==true) {
				  
				  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
				  
				  { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
				  Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
				  valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
				  sequenceurDAO.edit(sequenceur); }else { int valeur=((nbrjrssansweekend-1) *
				  nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
				  sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
				  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
				  sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
				  
				  }else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
				  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
				  Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
				  valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
				  sequenceurDAO.edit(sequenceur); }else { int valeur=((nbrjrssansweekend-1) *
				  nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
				  sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
				  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
				  sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
				  
				  }
				  
				  
				  
				  JOptionPane.showMessageDialog(null,
				  "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.
				  INFORMATION_MESSAGE); listFacturePFMAJ=facturepfdao.findAll();
				  
				  chargerListeFacture(); }else {
				  JOptionPane.showMessageDialog(null,"Bon(s) de Livraison déja facturé"
				  ,"Erreur",JOptionPane.ERROR_MESSAGE); return;
				  
				  }
				  
				  
				  }else {
				  
				  for(int i=0;i<listFacturePF.size();i++) { FacturePF
				  facturepf=listFacturePF.get(i);
				  
				  if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
				  if(maptransfereblfacture.containsKey(facturepf.getNumBl())) { ///Numerotation 2019 /// 
					  SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				  
				  /// Numerotation 2018 SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				  Date d=null;
				  d=facturepf.getDateFacture(); String date = dcn.format(d);
				  if(facturepf.getNumFacture()==null) {
				  
				  Calendar c = Calendar.getInstance();
				  
				  String NumFacture=Utils.genererCodeFactureVente(d);
				  
				  if(NumFacture.equals("")) {
				  
				  d=util.DateUtils.ajoutNbJours(d, 1); Calendar cal=Calendar.getInstance();
				  cal.setTime(d);
				  
				  if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
				  d=util.DateUtils.ajoutNbJours(d, 1); }
				  
				  while(NumFacture.equals("")) {
				  
				  NumFacture=Utils.genererCodeFactureVente(d);
				  
				  if(!NumFacture.equals("")) { date = dcn.format(d);
				  facturepf.setDateFacture(d); break;
				  
				  }else {
				  
				  d= util.DateUtils.ajoutNbJours(d, 1);
				  
				  Calendar calTmp=Calendar.getInstance(); calTmp.setTime(d);
				  
				  if(calTmp.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
				  d=util.DateUtils.ajoutNbJours(d, 1); }
				  
				  }
				  
				  
				  };
				  
				  }
				  
				  
				  c.setTime(d); int nbrjrs=c.get(Calendar.DAY_OF_YEAR); int nbrsunday = nbrjrs
				  / 7; int nbrjrssansweekend=nbrjrs-nbrsunday;
				  
				  facturepf.setNumFacture(NumFacture);
				  facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
				  facturepfdao.edit(facturepf);
				  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
				  
				  { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
				  Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
				  valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
				  sequenceurDAO.edit(sequenceur); }else { int valeur=((nbrjrssansweekend-1) *
				  nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
				  sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
				  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
				  sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
				  
				  }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
				  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.
				  CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
				  valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
				  sequenceurDAO.edit(sequenceur); } else { int valeur=((nbrjrssansweekend-1) *
				  nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
				  sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
				  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
				  sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
				  
				  }
				  
				  transferEnBL=true; } else {
				  
				  facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
				  facturepfdao.edit(facturepf); transferEnBL=true;
				  
				  }
				  
				  } }
				  
				  }
				  
				  
				  if(transferEnBL==true) { JOptionPane.showMessageDialog(null,
				  "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.
				  INFORMATION_MESSAGE); listFacturePFMAJ=facturepfdao.findAll();
				  
				  chargerListeFacture(); } else { JOptionPane.showMessageDialog(null,
				  "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
				  return;
				  
				  }
				  
				  
				  
				  }
				  
				  
				  }else {
				  
				  
				  for(int i=0;i<listFacturePF.size();i++) { FacturePF
				  facturepf=listFacturePF.get(i);
				  if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
				  
				  if(maptransfereblfacture.containsKey(facturepf.getNumBl())) {
				  
				  //SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
				  
				  
				  SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd"); Date d=null;
				  d=facturepf.getDateFacture(); 
				  String date = dcn.format(d);
				  if(facturepf.getNumFacture()==null) {
				  
				  String NumFacture=Utils.genererCodeFactureVente(d);
				  
				  if(NumFacture.equals("")) {
				  
				  d =util.DateUtils.ajoutNbJours(facturepf.getDateFacture(), 1); Calendar
				  cal=Calendar.getInstance(); cal.setTime(d);
				  
				  if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
				  d=util.DateUtils.ajoutNbJours(d, 1); } while(NumFacture.equals("")) {
				  
				  NumFacture=Utils.genererCodeFactureVente(d);
				  
				  if(!NumFacture.equals("")) { date = dcn.format(d);
				  facturepf.setDateFacture(d); break; }else {
				  
				  d= util.DateUtils.ajoutNbJours(d, 1); Calendar calTmp=Calendar.getInstance();
				  calTmp.setTime(d);
				  
				  if(calTmp.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
				  d=util.DateUtils.ajoutNbJours(d, 1); }
				  
				  }
				  
				  
				  };
				  
				  }
				  
				  Calendar c = Calendar.getInstance();
				  
				  c.setTime(d); int nbrjrs=c.get(Calendar.DAY_OF_YEAR); int nbrsunday = nbrjrs
				  / 7; int nbrjrssansweekend=nbrjrs-nbrsunday;
				  
				  facturepf.setNumFacture(NumFacture);
				  facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
				  facturepfdao.edit(facturepf);
				  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
				  
				  { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
				  Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
				  valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
				  sequenceurDAO.edit(sequenceur); }else { int valeur=((nbrjrssansweekend-1) *
				  nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
				  sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
				  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
				  sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
				  
				  }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
				  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
				  Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
				  valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
				  sequenceurDAO.edit(sequenceur); }else { int valeur=((nbrjrssansweekend-1) *
				  nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
				  sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
				  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
				  sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
				  
				  }
				  
				  transferEnBL=true;
				  
				  
				  
				  }else {
				  
				  facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
				  facturepfdao.edit(facturepf); transferEnBL=true; }
				  
				  }
				  
				  }
				  
				  }
				  
				  
				  if(transferEnBL==true) { JOptionPane.showMessageDialog(
				  null,"Bon de Livraison Transférer en Facture avec succée","Bravo",
				  JOptionPane. INFORMATION_MESSAGE); listFacturePFMAJ=facturepfdao.findAll();
				  
				  chargerListeFacture(); }else {
				  JOptionPane.showMessageDialog(null,"Bon(s) de Livraison déja facturé"
				  ,"Erreur",JOptionPane.ERROR_MESSAGE); return;
				  
				  }
				  
				  }
				  
				  datefacture.setCalendar(null);
				  
				  }
				  
				  
				  */
				 
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			  
				  
				 
				

				/*Numerotation 2019
				 * 
				 * boolean transferEnBL=false;
				 * 
				 * 
				 * maptransfereblfacture.clear(); if(!remplirmaptransfereblfacture()) {
				 * JOptionPane.showMessageDialog(null,
				 * "Il faut remplir les BL à transferer au facture", "Erreur",
				 * JOptionPane.ERROR_MESSAGE); } else {
				 * 
				 * if(maptransfereblfacture.size()>=2) { int reponse =
				 * JOptionPane.showConfirmDialog(null,
				 * "Voulez Vous Facturer les BL en une seule facture ?", "Satisfaction",
				 * JOptionPane.YES_NO_OPTION);
				 * 
				 * if(reponse == JOptionPane.YES_OPTION )
				 * 
				 * {
				 * 
				 * /// Numerotation 2019 SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
				 * 
				 * ///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				 * 
				 * // FacturePF facturepfTmp=listFacturePF.get(line); String date =""; String
				 * NumFacture=""; if(datefacture.getDate()!=null) { date=
				 * dcn.format(datefacture.getDate());
				 * NumFacture=Utils.genererCodeFactureVente(date);
				 * 
				 * }else { JOptionPane.showMessageDialog (null,
				 * "Veuillez selectionner la date facture SVP !!!!!","Erreur",
				 * JOptionPane.ERROR_MESSAGE); return; }
				 * 
				 * for(int i=0;i<listFacturePF.size();i++) { FacturePF
				 * facturepf=listFacturePF.get(i);
				 * if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
				 * if(maptransfereblfacture.containsKey(facturepf.getNumBl())) {
				 * if(facturepf.getNumFacture()==null) {
				 * facturepf.setDateFacture(datefacture.getDate());
				 * facturepf.setNumFacture(NumFacture);
				 * facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
				 * facturepfdao.edit(facturepf); transferEnBL=true; }else {
				 * facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
				 * facturepfdao.edit(facturepf); }
				 * 
				 * } }
				 * 
				 * }
				 * 
				 * 
				 * if(transferEnBL==true) {
				 * 
				 * if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
				 * 
				 * { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
				 * Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
				 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
				 * sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
				 * Sequenceur(); sequenceurTmp.setCode(date);
				 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
				 * sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
				 * 
				 * }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
				 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
				 * Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
				 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
				 * sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
				 * Sequenceur(); sequenceurTmp.setCode(date);
				 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
				 * sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
				 * 
				 * }
				 * 
				 * 
				 * 
				 * JOptionPane.showMessageDialog(null,
				 * "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.
				 * INFORMATION_MESSAGE); listFacturePFMAJ=facturepfdao.findAll();
				 * 
				 * chargerListeFacture(); }else { JOptionPane.showMessageDialog(null,
				 * "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
				 * return;
				 * 
				 * }
				 * 
				 * 
				 * }else {
				 * 
				 * for(int i=0;i<listFacturePF.size();i++) { FacturePF
				 * facturepf=listFacturePF.get(i);
				 * 
				 * if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
				 * if(maptransfereblfacture.containsKey(facturepf.getNumBl())) { ///
				 * Numerotation 2019 SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
				 * 
				 * ///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				 * 
				 * String date = dcn.format(facturepf.getDateFacture());
				 * if(facturepf.getNumFacture()==null) {
				 * facturepf.setNumFacture(Utils.genererCodeFactureVente(date));
				 * facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
				 * facturepfdao.edit(facturepf);
				 * if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
				 * 
				 * { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
				 * Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
				 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
				 * sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
				 * Sequenceur(); sequenceurTmp.setCode(date);
				 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
				 * sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
				 * 
				 * }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
				 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
				 * Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
				 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
				 * sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
				 * Sequenceur(); sequenceurTmp.setCode(date);
				 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
				 * sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
				 * 
				 * }
				 * 
				 * transferEnBL=true; }else {
				 * 
				 * facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
				 * facturepfdao.edit(facturepf); transferEnBL=true;
				 * 
				 * }
				 * 
				 * } }
				 * 
				 * }
				 * 
				 * 
				 * if(transferEnBL==true) { JOptionPane.showMessageDialog(null,
				 * "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.
				 * INFORMATION_MESSAGE); listFacturePFMAJ=facturepfdao.findAll();
				 * 
				 * chargerListeFacture(); }else { JOptionPane.showMessageDialog(null,
				 * "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
				 * return;
				 * 
				 * }
				 * 
				 * 
				 * 
				 * }
				 * 
				 * 
				 * }else {
				 * 
				 * 
				 * for(int i=0;i<listFacturePF.size();i++) { FacturePF
				 * facturepf=listFacturePF.get(i);
				 * if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
				 * 
				 * if(maptransfereblfacture.containsKey(facturepf.getNumBl())) { ///
				 * Numerotation 2019 SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
				 * 
				 * ///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				 * 
				 * String date = dcn.format(facturepf.getDateFacture());
				 * if(facturepf.getNumFacture()==null) {
				 * 
				 * facturepf.setNumFacture(Utils.genererCodeFactureVente(date));
				 * facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
				 * facturepfdao.edit(facturepf);
				 * if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
				 * 
				 * { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
				 * Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
				 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
				 * sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
				 * Sequenceur(); sequenceurTmp.setCode(date);
				 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
				 * sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
				 * 
				 * }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
				 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
				 * Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
				 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
				 * sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
				 * Sequenceur(); sequenceurTmp.setCode(date);
				 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
				 * sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
				 * 
				 * }
				 * 
				 * transferEnBL=true;
				 * 
				 * 
				 * 
				 * }else {
				 * 
				 * facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
				 * facturepfdao.edit(facturepf); transferEnBL=true; }
				 * 
				 * }
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * if(transferEnBL==true) { JOptionPane.showMessageDialog(null,
				 * "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.
				 * INFORMATION_MESSAGE); listFacturePFMAJ=facturepfdao.findAll();
				 * 
				 * chargerListeFacture(); }else { JOptionPane.showMessageDialog(null,
				 * "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
				 * return;
				 * 
				 * }
				 * 
				 * }
				 * 
				 * datefacture.setCalendar(null);
				 * 
				 * }
				 */
		    		
		    		
			}
	     });
	     btnTransfereBl.setFont(new Font("Tahoma", Font.BOLD, 11));
	     btnTransfereBl.setBounds(1276, 375, 167, 30);
	     add(btnTransfereBl);
	     
	     JButton btnDeslectionnerTout = new JButton();
	     btnDeslectionnerTout.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		

	     		
	     		
	     		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(false, i, 9);
	     			table.setValueAt(false, i, 10);
	     		}
	     	
	     	}
	     });
	     btnDeslectionnerTout.setToolTipText("deselectionner Tout");
	     btnDeslectionnerTout.setBounds(1388, 119, 26, 26);
	     btnDeslectionnerTout.setIcon(imgDeselectAll);
	     add(btnDeslectionnerTout);
	     
	     JButton btnSelectionnertout = new JButton();
	     btnSelectionnertout.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {

	    		
	    		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(true, i, 9);
	     			table.setValueAt(true, i, 10);
	     		}
	    	
	     		
	     	}
	     });
	     btnSelectionnertout.setToolTipText("Selectionner Tout");
	     btnSelectionnertout.setBounds(1417, 119, 26, 26);
	     btnSelectionnertout.setIcon(imgDeselectAll);
	     add(btnSelectionnertout);
	   
	    if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
			  combopardepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		Magasin magasin=depotdao.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
		     		if(magasin!=null)
		     		{
		     			if(depot.getId()!=magasin.getDepot().getId())
			     		{
		     				combopardepot.addItem(depot.getLibelle());
				     		
		     				mapparDepot.put(depot.getLibelle(), depot);
				     	
				     		
			     			
			     		}
		     		}else
		     		{
		     			combopardepot.addItem(depot.getLibelle());
			     		
		     			mapparDepot.put(depot.getLibelle(), depot);
			     	
			     		
		     		}
		     		k++;
		     		
		     	}
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combopardepot.addItem("");
				  combopardepot.addItem(depot.getLibelle());
				  mapparDepot.put(depot.getLibelle(), depot);
		     	
			  }
		  }
	    
	    
	    comboArticle.addItem("");
	    
	    JLabel label_8 = new JLabel("Q Packet :");
	    label_8.setBounds(1189, 11, 72, 26);
	    layeredPane.add(label_8);
	    
	    txtquantitepaquet = new JTextField();
	    txtquantitepaquet.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
        		

           		

           		if(e.getKeyCode()==e.VK_ENTER)
{
           			
           			
           				
           				try {
               				Articles article=mapArticle.get(comboArticle.getSelectedItem());
               				if(article!=null)
               				{
               	 				if(!txtPrix.getText().equals(""))
                   				{
                   					if(!txtquantitepaquet.getText().equals(""))
                   					{
                   						if(new BigDecimal(txtquantitepaquet.getText()).compareTo(BigDecimal.ZERO)>0)
                   						{
                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
    					{
                   								
                   								if(!txtquantite.getText().equals(""))
    		                   					{
    		                   						if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
    		                   						{
    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
    	      					{
    		                   								txtmontant.setText((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement())))+"");					
    		                   								
    	      					}
    		                   						}}else
    		                   						{
    		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement()))+"");
    		                   						}
    							
    						
    						
    					}
                   							
                   						}
                   						
                   					}
                   					
                   					
                   						      					
                   				}
                   				
               				}
               				
              
               				

    } catch (NumberFormatException e2) {JOptionPane.showMessageDialog(null, "Veuillez entrer les chiffres pour la quantité et le montant SVP !!!!!");
    // TODO: handle exception
    }
     
           				
           			
           			          			
           			
           			
}
    
        	}
	    });
	    txtquantitepaquet.setColumns(10);
	    txtquantitepaquet.setBounds(1244, 11, 121, 26);
	    layeredPane.add(txtquantitepaquet);
	    
	     checkttc = new JCheckBox("TTC");
	     checkttc.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		
	     		if(checkttc.isSelected()==true)
         		{
         			if(!txtPrix.getText().equals(""))
         			{
         				prixTTC=new BigDecimal(txtPrix.getText());
         				txtPrix.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(1.2)).setScale(2, RoundingMode.CEILING)+"");
         			}

         		}else if(checkttc.isSelected()==false)
         		{
         			if(!txtPrix.getText().equals(""))
         			{
         				txtPrix.setText(new BigDecimal(txtPrix.getText()).divide(new BigDecimal(1.2),2, RoundingMode.CEILING)+"");
         			}
         		}
	     	}
	     });
	    checkttc.setBounds(761, 49, 73, 24);
	    layeredPane.add(checkttc);
	    
	     lblOffreTherre = new JLabel("Offre Therre :");
	    lblOffreTherre.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblOffreTherre.setBounds(8, 84, 87, 24);
	    layeredPane.add(lblOffreTherre);
	    lblOffreTherre.setVisible(false);
	     comboBoxtherres = new JComboBox();
	    comboBoxtherres.setBounds(97, 84, 242, 27);
	    layeredPane.add(comboBoxtherres);
	    comboBoxtherres.setVisible(false);
	    txtquantitetherres = new JTextField();
	    txtquantitetherres.setColumns(10);
	    txtquantitetherres.setBounds(361, 84, 95, 26);
	    layeredPane.add(txtquantitetherres);
	    txtquantitetherres.setVisible(false);
	     lblOffreVerres = new JLabel("Offre Verres :");
	    lblOffreVerres.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblOffreVerres.setBounds(466, 81, 87, 27);
	    layeredPane.add(lblOffreVerres);
	    lblOffreVerres.setVisible(false);
	     comboBoxverres = new JComboBox();
	    comboBoxverres.setBounds(545, 81, 242, 27);
	    layeredPane.add(comboBoxverres);
	    comboBoxverres.setVisible(false);
	    txtquantiteverres = new JTextField();
	    txtquantiteverres.setColumns(10);
	    txtquantiteverres.setBounds(797, 81, 95, 26);
	    layeredPane.add(txtquantiteverres);
	    txtquantiteverres.setVisible(false);
	     lblOffrePromo = new JLabel("Offre Promo :");
	    lblOffrePromo.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblOffrePromo.setBounds(902, 81, 87, 24);
	    layeredPane.add(lblOffrePromo);
	    lblOffrePromo.setVisible(false);
	     comboBoxPromo = new JComboBox();
	    comboBoxPromo.setSelectedIndex(-1);
	    comboBoxPromo.setBounds(984, 82, 208, 24);
	    layeredPane.add(comboBoxPromo);
	    comboBoxPromo.setVisible(false);
	    txtquantitepromo = new JTextField();
	    txtquantitepromo.setColumns(10);
	    txtquantitepromo.setBounds(1207, 81, 102, 26);
	    layeredPane.add(txtquantitepromo);
	    txtquantitepromo.setVisible(false);
	  
	    listArticleTmp=articleDAO.findAll();
				// stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
		 
		 for(int j=0;j<listArticleTmp.size();j++)
		 {
			Articles article= listArticleTmp.get(j);
			comboArticle.addItem(article.getLiblle());
			 mapArticle.put(article.getLiblle(), article);
			 mapCodeArticle.put(article.getCodeArticle(), article);
		 }
		
	    listParClientPF=clientpfdao.findListClientByCodeDepot(utilisateur.getCodeDepot());
	    comboparclient.addItem("");
	    
	     datedebut = new JDateChooser();
	    datedebut.setLocale(Locale.FRANCE);
	    datedebut.setDateFormatString("dd/MM/yyyy");
	    datedebut.setBounds(692, 12, 151, 26);
	    layeredPane_2.add(datedebut);
	    
	    JLabel lblDu = new JLabel("Du :");
	    lblDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblDu.setBounds(648, 14, 34, 24);
	    layeredPane_2.add(lblDu);
	    
	    JLabel lblTypeBl = new JLabel("Type BL :");
	    lblTypeBl.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblTypeBl.setBounds(0, 12, 70, 24);
	    layeredPane_2.add(lblTypeBl);
	    
	     comboParTypeBL = new JComboBox();
	    comboParTypeBL.setSelectedIndex(-1);
	    comboParTypeBL.setBounds(67, 13, 151, 24);
	    layeredPane_2.add(comboParTypeBL);
	    
	      listArticleOffre =articleDAO.listeArticleByCodeOffre(Constantes.OFFRE_THERRES, Constantes.OFFRE_VERRES);
	      
	      listSousFamilleArticleOffre=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePFOffre(Constantes.SOUS_FAMILLE_OFFRE_THERRES, Constantes.SOUS_FAMILLE_OFFRE_VERRES);
	      
	      for(int i=0;i<listArticleOffre.size();i++)
	    	  
	      {
	    	  
	    	Articles articles=listArticleOffre.get(i)  ;
	    	
	    	if(articles.getCodeArticle().equals(Constantes.OFFRE_THERRES))
	    	{
	    		comboBoxtherres.addItem(articles.getLiblle());
	    		mapArticleOffre.put(articles.getLiblle(), articles);
	    		mapArticleOffreCode.put(articles.getCodeArticle(), articles);
	    	}else if(articles.getCodeArticle().equals(Constantes.OFFRE_VERRES))
	    	{
	    		comboBoxverres.addItem(articles.getLiblle());
	    		mapArticleOffre.put(articles.getLiblle(), articles);
	    		mapArticleOffreCode.put(articles.getCodeArticle(), articles);
	    	}
	    	
	    	
	    	  
	      }
	      
	      for(int i=0;i<listSousFamilleArticleOffre.size();i++)
	      {
	    	SousFamilleArticlePF sousFamilleArticlePF=  listSousFamilleArticleOffre.get(i);
	    	
	    	mapsousfamilleOffre.put(sousFamilleArticlePF.getCode(), sousFamilleArticlePF);
	    	  
	      }
	    
	    
	     checkboxGratuits = new JCheckBox("Gratuit");
	    checkboxGratuits.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		if(checkboxGratuits.isSelected()==true)
	    		{
	    			lblPrix.setVisible(false);
	    			lblMontant.setVisible(false);
	    			lblRemise.setVisible(false);
	    			labelpourcentage.setVisible(false);
	    			txtPrix.setVisible(false);
	    			txtreduction.setVisible(false);
	    			txtmontant.setVisible(false);
	    			checkttc.setVisible(false);	
	    			comboArticle.setSelectedIndex(-1);
	    			comboArticle.removeAllItems();
	    			labelMarge.setText("");
	    			labelpourcentage.setText("");
	    			labelPrixMax.setText("");
	    			labelPrixMin.setText("");
	    			stockDisponible.setText("");
	    			initialiserGratuit();
	    			
	    		}else
	    		{			
	    			lblPrix.setVisible(true);
	    			lblMontant.setVisible(true);
	    			lblRemise.setVisible(true);
	    			labelpourcentage.setVisible(true);
	    			txtPrix.setVisible(true);
	    			txtreduction.setVisible(true);
	    			txtmontant.setVisible(true);
	    			checkttc.setVisible(true);	
	    			comboArticle.setSelectedIndex(-1);
	    			comboArticle.removeAllItems();
	    			labelMarge.setText("");
	    			labelpourcentage.setText("");
	    			labelPrixMax.setText("");
	    			labelPrixMin.setText("");
	    			stockDisponible.setText("");
	    			initialiserGratuit();
	    			
	    		}
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    	}
	    });
	    checkboxGratuits.setBounds(1391, 499, 77, 24);
	    add(checkboxGratuits);
	    
	     labelPrixMin = new JLabel("");
	    labelPrixMin.setForeground(new Color(50, 205, 50));
	    labelPrixMin.setFont(new Font("Tahoma", Font.BOLD, 13));
	    labelPrixMin.setBounds(1478, 499, 193, 30);
	    add(labelPrixMin);
	    
	     labelPrixMax = new JLabel("");
	    labelPrixMax.setForeground(Color.RED);
	    labelPrixMax.setFont(new Font("Tahoma", Font.BOLD, 13));
	    labelPrixMax.setBounds(1681, 499, 193, 30);
	    add(labelPrixMax);
	    
	     stockDisponible = new JLabel("");
	    stockDisponible.setForeground(Color.RED);
	    stockDisponible.setFont(new Font("Tahoma", Font.BOLD, 14));
	    stockDisponible.setBounds(10, 973, 1084, 30);
	    add(stockDisponible);
	    
	     labelMarge = new JLabel("");
	    labelMarge.setForeground(Color.RED);
	    labelMarge.setFont(new Font("Tahoma", Font.BOLD, 14));
	    labelMarge.setBounds(10, 1014, 708, 30);
	    add(labelMarge);
	    
	     supprimer_facture = new JButton();
	    supprimer_facture.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    	if(table.getSelectedRow()!=-1)
	    	{
	    		 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer la facture  ?", 
							"Satisfaction", JOptionPane.YES_NO_OPTION);
					 
					if(reponse == JOptionPane.YES_OPTION )
						
						
					{
					
						if(listFacturePF.size()!=0)
						{
							
							 facturePF=listFacturePF.get(table.getSelectedRow()) ;
							 
							 
							 if(facturePF.getType().equals(Constantes.TYPE_BON_LIVRAISON) || facturePF.getType().equals(Constantes.TYPE_FACTURE) || facturePF.getType().equals(Constantes.TRANSFERE_BL_FACTURE))
								{

								 transferStock=transferStockPFDAO.findByCodeTransfert(facturePF.getCodeTransfer());
							
								listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(facturePF.getId());
								 for(int i=0;i<listDetailFacturePF.size();i++)
								 {
						        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(i).getArticle().getId(),facturePF.getMagasin().getId(),listDetailFacturePF.get(i).getSousFamille().getId());

									 BigDecimal  stocktmp=stockpf.getStock().add(listDetailFacturePF.get(i).getQuantite());
									 stockpf.setStock(stocktmp);
								       
								  		stockpfDAO.edit(stockpf);
								  		DetailFacturePF detailfacturepf=listDetailFacturePF.get(i);
								  		detailFacturePfdao.delete(detailfacturepf.getId());
								  		
									 }
								 DetailCompteClient detailcompteclient=detailCompteClientdao.findByFacture(facturePF.getId());
								 if(detailcompteclient!=null)
								 {
									 CompteClient compteclient=detailcompteclient.getCompteClient();
									 if(detailcompteclient.getMontantCredit().setScale(2).equals(BigDecimal.ZERO.setScale(2)))
									 {
										 compteclient.setSolde(compteclient.getSolde().subtract(detailcompteclient.getMontantDebit()));
									 }
									
									 compteclientdao.edit(compteclient);
									 detailCompteClientdao.delete(detailcompteclient); 
									 
								 }
								 if(transferStock!=null)
								 {
									 listDetailTransferProduitFini=transferStock.getListDetailTransferProduitFini();
									 for(int j=0;j<listDetailTransferProduitFini.size();j++)
									 {
										 detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(j).getId());
									 }
									 transferStockPFDAO.delete(transferStock.getId()); 
								 }
								
								 facturepfdao.delete(facturePF.getId());
								 
								 JOptionPane.showMessageDialog(null, "BL Supprimer avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
								 
								 chargerListeFacture();
								 
								}
							 
						}
						
						
						
						
					}
					
					
	    	}
	    		
	    		
	    		
	    		
	    		
	    	}
	    });
	    supprimer_facture.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    supprimer_facture.setBounds(1478, 185, 73, 24);
	    supprimer_facture.setIcon(imgSupprimer);
	    add(supprimer_facture);
	    
	     datefacture = new JDateChooser();
	    datefacture.setLocale(Locale.FRANCE);
	    datefacture.setDateFormatString("dd/MM/yyyy");
	    datefacture.setBounds(1116, 379, 151, 26);
	    add(datefacture);
	    
	    JLabel lblDateFacture_1 = new JLabel("Date Facture:");
	    lblDateFacture_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblDateFacture_1.setBounds(1021, 381, 97, 24);
	    add(lblDateFacture_1);
	    
	    JButton btnInitialiser_1 = new JButton("Initialiser Date");
	    btnInitialiser_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		datefacture.setCalendar(null);
	    		
	    	}
	    });
	    btnInitialiser_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnInitialiser_1.setBounds(905, 378, 107, 24);
	    add(btnInitialiser_1);
	    
	     stockdisponibleoffretherres = new JLabel("");
	    stockdisponibleoffretherres.setHorizontalAlignment(SwingConstants.LEFT);
	    stockdisponibleoffretherres.setForeground(Color.RED);
	    stockdisponibleoffretherres.setFont(new Font("Tahoma", Font.BOLD, 14));
	    stockdisponibleoffretherres.setBounds(10, 910, 600, 24);
	    add(stockdisponibleoffretherres);
	    
	     stockdisponibleoffreverres = new JLabel("");
	    stockdisponibleoffreverres.setHorizontalAlignment(SwingConstants.LEFT);
	    stockdisponibleoffreverres.setForeground(Color.RED);
	    stockdisponibleoffreverres.setFont(new Font("Tahoma", Font.BOLD, 14));
	    stockdisponibleoffreverres.setBounds(10, 942, 600, 30);
	    add(stockdisponibleoffreverres);
	    
	     stockdisponiblearticlepromo = new JLabel("");
	    stockdisponiblearticlepromo.setHorizontalAlignment(SwingConstants.LEFT);
	    stockdisponiblearticlepromo.setForeground(Color.RED);
	    stockdisponiblearticlepromo.setFont(new Font("Tahoma", Font.BOLD, 12));
	    stockdisponiblearticlepromo.setBounds(1395, 585, 490, 30);
	    add(stockdisponiblearticlepromo);
	    
	    JButton btnOrdonnerLesFactures = new JButton("Ordonner Les factures");
	    btnOrdonnerLesFactures.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	    		listFacturePFOrderByDate=facturepfdao.listeFacturePFEntreDeuxDatesOrderByDate(datedebut.getDate(), pardateChooser.getDate(), magasin);
	    		listFacturePFOrderByNumFacture=facturepfdao.listeFacturePFEntreDeuxDatesOrderByNumFacture (datedebut.getDate(), pardateChooser.getDate(), magasin);
	    		
	    		for(int i=0;i<listFacturePFOrderByNumFacture.size() ; i++)
	    		{
	    			
	    			for(int j=0;j<listFacturePFOrderByDate.size();j++)
	    			{
	    				
	    			FacturePF facturePF=listFacturePFOrderByDate.get(j);
	    			
	    				if(i==j)
	    				{
	    					facturePF.setNumFacture(listFacturePFOrderByNumFacture.get(i).getNumFacture());
	    					DetailCompteClient detailcompteclient=detailCompteClientdao.findByFacture(facturePF.getId());
	    					 detailcompteclient.setDesignation("Montant sur Facture N "+listFacturePFOrderByNumFacture.get(i).getNumFacture());
	    					 facturepfdao.edit(facturePF);
	    					 detailCompteClientdao.edit(detailcompteclient);
	    					
	    				}
	    				
	    				
	    			}
	    			
	    		}
	    		
	    		
	    		
	    	}
	    });
	    btnOrdonnerLesFactures.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnOrdonnerLesFactures.setBounds(246, 6, 168, 40);
	    btnOrdonnerLesFactures.setVisible(false);
	    add(btnOrdonnerLesFactures);
	    
	    JButton button_1 = new JButton("Ajouter Gratuite");
	    button_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		
	    		Articles article=mapArticle.get(comboArticle.getSelectedItem());
	    		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	    		 List<DetailFacturePF> listDetailFacturePFSansGratuité =new ArrayList<DetailFacturePF>();
	    		 List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
	    		 List<DetailTransferProduitFini> listDetailTransferPF =new ArrayList<DetailTransferProduitFini>();
	    		
	    		
	    		 listDetailFacturePFSansGratuité=detailFacturePfdao.listeFactureSansGratuiteParArticle(article, magasin , txtparnumBL.getText() );
	    		 
	    	
	    		 JOptionPane.showMessageDialog(null, listDetailFacturePFSansGratuité.size()); 
	    		
	    		 for(int i=0;i<listDetailFacturePFSansGratuité.size();i++)
				{
					
					  
					 // if(listDetailFacturePFSansGratuité.get(i).getQuantite().compareTo(new BigDecimal(264))>=0) {
					  
					//int intvalue=(listDetailFacturePFSansGratuité.get(i).getQuantite().divide(new BigDecimal(10) , RoundingMode.FLOOR)).intValue();
					  
					  
					  listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture( listDetailFacturePFSansGratuité.get(i).getFacturePF().getId()); 
					  Boolean existe=false;
					  
					  for(int j=0;j<listDetailFacturePF.size();j++) {
					  
					  if(listDetailFacturePF.get(j).getArticle().getId()== listDetailFacturePFSansGratuité.get(i).getArticle().getId())
					  {
					  
					  if(listDetailFacturePF.get(j).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0) {
					  
					  listDetailFacturePF.get(j).setQuantite(new BigDecimal(txtquantite.getText()));
					  detailFacturePfdao.edit(listDetailFacturePF.get(j));
					  
					  TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(listDetailFacturePFSansGratuité.get(i).getFacturePF().getCodeTransfer());
					  listDetailTransferPF=detailTransferStockPFdao.findByTransferStockPF( transferStockPF.getId());
					  
					  for(int d=0;d<listDetailTransferPF.size();d++) {
					  
					  if(listDetailTransferPF.get(d).getArticle().getId()==listDetailFacturePFSansGratuité.get(i).getArticle().getId()) {
					  
					  if(listDetailTransferPF.get(d).getPrixUnitaire().compareTo(BigDecimal.ZERO)== 0) {
					  
					  listDetailTransferPF.get(d).setQuantite(new BigDecimal(txtquantite.getText()));
					  
					  detailTransferStockPFdao.edit(listDetailTransferPF.get(d));
					  
					  
					  }
					  
					  }
					  
					  }
					  
					  
					  
					  
					  
					  existe=true;
					  
					  
					  
					  }
					  
					  
					  }
					  
					  
					  }
					  
					  
					  
					  
					  if(existe==false) {
					  
					  DetailFacturePF detailFacturePF =new DetailFacturePF();
					  
					  detailFacturePF.setArticle(listDetailFacturePFSansGratuité.get(i).getArticle());
					  detailFacturePF.setFacturePF(listDetailFacturePFSansGratuité.get(i).getFacturePF()); 
					  detailFacturePF.setPrixUnitaire(BigDecimal.ZERO);
					  detailFacturePF.setSousFamille(listDetailFacturePFSansGratuité.get(i).getSousFamille());
					  detailFacturePF.setMontantHT(BigDecimal.ZERO);
					  detailFacturePF.setMontantTVA(BigDecimal.ZERO);
					  detailFacturePF.setMontantTTC(BigDecimal.ZERO);
					  detailFacturePF.setReduction(BigDecimal.ZERO);
					  detailFacturePF.setTva(BigDecimal.ZERO); 
					  detailFacturePF.setQuantite(new BigDecimal(txtquantite.getText()));
					  detailFacturePF.setUtilisateur(listDetailFacturePFSansGratuité.get(i).getUtilisateur());
					  
					  detailFacturePfdao.add(detailFacturePF);
					  
					  TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(
					  listDetailFacturePFSansGratuité.get(i).getFacturePF().getCodeTransfer());
					  
					  DetailTransferProduitFini detailTransferProduitFini=new DetailTransferProduitFini();
					  detailTransferProduitFini.setArticle(listDetailFacturePFSansGratuité.get(i).getArticle());
					  detailTransferProduitFini.setDateTransfer(listDetailFacturePFSansGratuité.get(i).getFacturePF().getDateFacture());
					  detailTransferProduitFini.setPrixUnitaire(BigDecimal.ZERO);
					  detailTransferProduitFini.setQuantite(new BigDecimal(txtquantite.getText()));
					  detailTransferProduitFini.setSousFamille(listDetailFacturePFSansGratuité.get(i).getSousFamille());
					  detailTransferProduitFini.setMagasinDestination(magasin);
					  detailTransferProduitFini.setTransferStockPF(transferStockPF);
					  detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
					  detailTransferStockPFdao.add(detailTransferProduitFini);
					  
					  
					  }
					  
					  
					  //}
					  
					 }
	    		
	    		
	    		JOptionPane.showMessageDialog(null, "La Gratuité de "+article.getLiblle() +" à été effectué");
	    		
	    		
	    		
	    		
	    		
	    	}
	    });
	    button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button_1.setBounds(30, 27, 112, 24);
	    button_1.setVisible(false);
	    add(button_1);
	    
	    JButton btnAjouterDetailTransfer = new JButton("Ajouter Detail Transfer");
	    btnAjouterDetailTransfer.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		listFacturePF=facturepfdao.findAll();
	    		for(int i=0;i<listFacturePF.size();i++)
	    		{
	    			FacturePF facturePF =listFacturePF.get(i);
	    			TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(facturePF.getCodeTransfer());
	    			listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(facturePF.getId());
	    			
	    			for(int j=0;j<listDetailFacturePF.size();j++)
	    			{
	    				
	    				DetailFacturePF detailFacturePF=listDetailFacturePF.get(j);
	    				
	    				DetailTransferProduitFini detailTransferProduitFini =new DetailTransferProduitFini();
	    				detailTransferProduitFini.setArticle(detailFacturePF.getArticle());
	    				detailTransferProduitFini.setDateTransfer(facturePF.getDateFacture());
	    				detailTransferProduitFini.setMagasinDestination(facturePF.getMagasin());
	    				detailTransferProduitFini.setPrixUnitaire(detailFacturePF.getPrixUnitaire());
	    				detailTransferProduitFini.setQuantite(detailFacturePF.getQuantite());
	    				detailTransferProduitFini.setSousFamille(detailFacturePF.getSousFamille());
	    				detailTransferProduitFini.setTransferStockPF(transferStockPF);
	    				detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
	    				detailTransferStockPFdao.add(detailTransferProduitFini);
	    			}
	    			
	    			
	    			
	    			
	    		}
	    		
	    		
	    		JOptionPane.showMessageDialog(null, "Insertion Valider !!!!");
	    		
	    		
	    	}
	    });
	    btnAjouterDetailTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAjouterDetailTransfer.setBounds(1519, 706, 173, 24);
	    btnAjouterDetailTransfer.setVisible(false);
	    add(btnAjouterDetailTransfer);
	    
	    JButton button_2 = new JButton("Lire Fichier excel");
	    button_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		

  				
  				
  				if(!txtlien.getText().equals(""))
  				{
  					
  					 File fileName = new File(txtlien.getText());
		  				
  					
  					 Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		  				try {
		  				 
		  					util.ExcelUtils.setExcelFile(fileName, magasin );
							
							
		  					//ExcelUtils.getCellData(fileName);
							
							
							
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							 JOptionPane.showMessageDialog(null, e.getMessage()); 
						}
		  				
  					
  				}
  			
  				
  			
  				
  			
	    		
	    		
	    		
	    	}
	    });
	    button_2.setBounds(1385, 807, 129, 36);
	    button_2.setVisible(false);
	    add(button_2);
	    
	    txtlien = new JTextField();
	    txtlien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
	    txtlien.setEditable(false);
	    txtlien.setColumns(10);
	    
	    txtlien.setBounds(1519, 806, 292, 36);
	    txtlien.setVisible(false);
	    add(txtlien);
	    
	    JButton button_3 = new JButton("Ouvrir");
	    button_3.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		 final JFileChooser  fileDialog = new JFileChooser();

  				
 				 int returnVal = fileDialog.showOpenDialog(mainFrame);
 	            
 				java.io.File file = fileDialog.getSelectedFile();     
 				
 				txtlien.setText(file.getAbsolutePath());
 				
 				
 				
 			
	    		
	    		
	    		
	    		
	    		
	    	}
	    });
	    button_3.setBounds(1821, 810, 74, 30);
	    button_3.setVisible(false);
	    add(button_3);
	    
	     checkboxSansTva = new JCheckBox("Sans TVA");
	    checkboxSansTva.setBounds(1391, 476, 83, 24);
	    add(checkboxSansTva);
	    
	    JButton btnTransfereBlVers = new JButton("Transfere BL Vers Historique Vente");
	    btnTransfereBlVers.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		boolean transferer=false;
	    		maptransfereblfacture.clear();
	    		if(!remplirmaptransfereblfacture())	{
					JOptionPane.showMessageDialog(null, "Il faut remplir les BL à transferer Vers Historique vente", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					
					for(int i=0;i<listFacturePF.size();i++)
					{
						FacturePF facturepf=listFacturePF.get(i);
						if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
						{
							if(maptransfereblfacture.containsKey(facturepf.getNumBl()))
							{
								listDetailFacturePFTransfererVersHistorique.clear();
								
								
								HistoriqueVentevendeur historiqueVentevendeurTmp=historiqueVenteVendeurDAO.findFactureVentePFByNumBLByDepot(facturepf.getNumBl(),facturepf.getDepot());
								
								if(historiqueVentevendeurTmp==null)
								{
									
									listDetailFacturePFTransfererVersHistorique	=detailFacturePfdao.listeDetailFacturePFByFacture(facturepf.getId());
									
									HistoriqueVentevendeur historiqueVentevendeur=new HistoriqueVentevendeur();
					                 
									historiqueVentevendeur.setNumBl(facturepf.getNumBl());
					                historiqueVentevendeur.setClientPF(facturepf.getClientPF());
					                historiqueVentevendeur.setFournisseur(facturepf.getFournisseur());
					                historiqueVentevendeur.setDepot(facturepf.getDepot());
					                historiqueVentevendeur.setMagasin(facturepf.getMagasin());
					                historiqueVentevendeur.setDateBl(facturepf.getDateBl());
					                historiqueVentevendeur.setDateFacture(facturepf.getDateFacture());
					                historiqueVentevendeur.setEtat(Constantes.ETAT_NON_REGLE);
					                historiqueVentevendeur.setType(Constantes.TYPE_BON_LIVRAISON);
					                historiqueVentevendeur.setMontantHT(facturepf.getMontantHT());
					                historiqueVentevendeur.setMontantTVA(facturepf.getMontantTVA());
					                historiqueVentevendeur.setMontantTTC(facturepf.getMontantTTC());
					                historiqueVentevendeur.setCodeTransfer(facturepf.getCodeTransfer());
					                historiqueVentevendeur.setDateSaisi(new Date());
					                historiqueVentevendeur.setCreerPar(utilisateur);
					                historiqueVentevendeur.setTypeBL(facturepf.getTypeBL());
					                historiqueVentevendeur.setDateBCommande(facturepf.getDateBCommande());
					                historiqueVentevendeur.setImprimerAvecNumBl(facturepf.getImprimerAvecNumBl());
					                historiqueVentevendeur.setImprimerAvecNumCommande(facturepf.getImprimerAvecNumCommande());
					                historiqueVentevendeur.setImprimerPiece(facturepf.getImprimerPiece());
					                historiqueVentevendeur.setAdresseClient(facturepf.getAdresseClient());
					                historiqueVenteVendeurDAO.add(historiqueVentevendeur);
									
									for(int t=0;t<listDetailFacturePFTransfererVersHistorique.size();t++)
									{
										
										
										DetailFacturePF detailFacturePF=listDetailFacturePFTransfererVersHistorique.get(t);
										
										DetailHistoriqueVenteVendeur detailHistoriqueVenteVendeur=new DetailHistoriqueVenteVendeur();
										
										detailHistoriqueVenteVendeur.setArticle(detailFacturePF.getArticle());
										detailHistoriqueVenteVendeur.setHistoriqueventevendeur(historiqueVentevendeur);
										detailHistoriqueVenteVendeur.setMontantHT(detailFacturePF.getMontantHT());
										detailHistoriqueVenteVendeur.setMontantTVA(detailFacturePF.getMontantTVA());
										detailHistoriqueVenteVendeur.setMontantTTC(detailFacturePF.getMontantTTC());
										detailHistoriqueVenteVendeur.setPrixUnitaire(detailFacturePF.getPrixUnitaire());
										detailHistoriqueVenteVendeur.setQuantite(detailFacturePF.getQuantite());
										detailHistoriqueVenteVendeur.setReduction(detailFacturePF.getReduction());
										detailHistoriqueVenteVendeur.setSousFamille(detailFacturePF.getSousFamille());
										detailHistoriqueVenteVendeur.setTva(detailFacturePF.getTva());
										detailHistoriqueVenteVendeur.setUtilisateur(detailFacturePF.getUtilisateur());
										detailHistoriqueVenteVendeurDAO.add(detailHistoriqueVenteVendeur);
									}
									
									transferer=true;
									
									
								}
							
								
								
								
								
								
							}
					
					
						}
					
					}
					
					
				if(transferer==true)
				{
					
					JOptionPane.showMessageDialog(null, "Les BL Transferer Avec Succée ","Information",JOptionPane.INFORMATION_MESSAGE);
					
					
				}else if(transferer==false)
				{
					JOptionPane.showMessageDialog(null, "Le(s) Num BL déja Transférer ou Facturé !!!!!!!!","Erreur",JOptionPane.WARNING_MESSAGE);
					return;
				}
					
					
					
					
					
				}
	    		}
	    });
	    btnTransfereBlVers.setFont(new Font("Tahoma", Font.BOLD, 11));
	    btnTransfereBlVers.setBounds(1478, 223, 238, 30);
	    add(btnTransfereBlVers);
	    for(int i=0;i<listParClientPF.size();i++)
	    {
	    	
	    ClientPF clientpf=listParClientPF.get(i);
	    comboparclient.addItem(clientpf.getNom());
	    mapParClientPF.put(clientpf.getNom(), clientpf);
	    supprimer_facture.setEnabled(false);
	    }
	    
	    
	    
	    if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) 
		 {
	    	checkboxSansTva.setSelected(false);
	    	checkboxSansTva.setEnabled(true);
		 }else {
			 checkboxSansTva.setSelected(true);
			 checkboxSansTva.setEnabled(false);
		 }
	    
	    
	    
	    
	    comboParTypeBL.addItem("");
	    comboTypeBL.addItem("");
	    for(int i=0;i<listTypeBL.size();i++)
	    {
	    	TypeBL typeBL=listTypeBL.get(i);
	    	
	    	comboParTypeBL.addItem(typeBL.getType());
	    	comboTypeBL.addItem(typeBL.getType());
	    	mapTypeBL.put(typeBL.getType(), typeBL);
	    }
	    
	    comboParTypeBL.setSelectedItem("");
	    comboTypeBL.setSelectedItem("");
	    
	    JButton button_4 = new JButton("Imprimer");
	    button_4.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		

				
				mapImprimer.clear();
	    		if(!remplirMapImprimer())	{
					JOptionPane.showMessageDialog(null, "Il faut remplir les Facture à imprimer SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					
					boolean apercu=false;
					
					 int reponse = JOptionPane.showConfirmDialog(null, "Voulez Vous Afficher l'aperçu Avant l'impression ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )		
							
						{
							apercu=true;
						}
					
					
					
					for(int k=0;k<listFacturePF.size();k++)
					{
						FacturePF facturepfTmp=listFacturePF.get(k);
						
						if(mapImprimer.containsKey(facturepfTmp.getId()))
						{
							
							///////////////////////////////////////////////
							
			detailFacturePfdao.ViderSession();
			 

			List<DetailFacturePF> listDetailFacturePFImprimer =new ArrayList<DetailFacturePF>();
			List<DetailFacturePF> listDetailFacturePFImprimerTmp =new ArrayList<DetailFacturePF>();
			   List<DetailFacturePF> listDetailFacturePFTmp =new ArrayList<DetailFacturePF>();
			 listFacturePFTmp.clear();
			 if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
			 {
			 
				 
				 listDetailFacturePFTmp=detailFacturePfdao.listeDetailFacturePFByFacture(facturepfTmp.getId());
				 
				 
			 } 
			   
		        
							 
							
							 if(listDetailFacturePFTmp.size()!=0)
							 {
								
								 
								 
								 BigDecimal quantitePieceCalculer=BigDecimal.ZERO; 
							       BigDecimal prixPieceCalculer=BigDecimal.ZERO; 
							       BigDecimal MontantHTPieceCalculer=BigDecimal.ZERO; 
							       BigDecimal MontantTVAPieceCalculer=BigDecimal.ZERO; 
							       BigDecimal MontantTTCPieceCalculer=BigDecimal.ZERO; 
							      
							       for(int i=0;i<listDetailFacturePFTmp.size();i++)
									 {
										 
										 
										    quantitePieceCalculer=BigDecimal.ZERO; 
									         prixPieceCalculer=BigDecimal.ZERO; 
									         MontantHTPieceCalculer=BigDecimal.ZERO; 
									         MontantTVAPieceCalculer=BigDecimal.ZERO; 
									         MontantTTCPieceCalculer=BigDecimal.ZERO; 
									         
									         
												if(listDetailFacturePFTmp.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePFTmp.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
												{
													if(listDetailFacturePFTmp.get(i).getFacturePF().getImprimerPiece()==true)
													{
														
														 quantitePieceCalculer=listDetailFacturePFTmp.get(i).getQuantite().multiply(new BigDecimal(1000)).divide(listDetailFacturePFTmp.get(i).getArticle().getCentreCout1(),0,RoundingMode.HALF_UP); 
												         prixPieceCalculer=listDetailFacturePFTmp.get(i).getPrixUnitaire().divide(listDetailFacturePFTmp.get(i).getArticle().getConditionnement(), 6, RoundingMode.HALF_UP); 
												         MontantHTPieceCalculer=quantitePieceCalculer.multiply(prixPieceCalculer) ; 
												        if(listDetailFacturePFTmp.get(i).getMontantTVA().compareTo(BigDecimal.ZERO)!=0)
												        {
												        	 MontantTVAPieceCalculer=MontantHTPieceCalculer.multiply(Constantes.TVA);
												        }
												         
												         MontantTTCPieceCalculer=MontantHTPieceCalculer.add(MontantTVAPieceCalculer);
														
												         listDetailFacturePFTmp.get(i).setQuantite(quantitePieceCalculer);
												         listDetailFacturePFTmp.get(i).setMontantHT(MontantHTPieceCalculer);
												         listDetailFacturePFTmp.get(i).setPrixUnitaire(listDetailFacturePFTmp.get(i).getMontantHT().divide(listDetailFacturePFTmp.get(i).getQuantite(), 6, RoundingMode.FLOOR));

												         listDetailFacturePFTmp.get(i).setMontantTVA(MontantTVAPieceCalculer);
												         listDetailFacturePFTmp.get(i).setMontantTTC(MontantTTCPieceCalculer);
												         			
												         listDetailFacturePFTmp.set(i, listDetailFacturePFTmp.get(i));
														
														
														
														
														
														
														 
														 
														
													}
													
													
												} 
									         
									         
									         
									 }
								 
								 
								
									
									
									
									 
							  			
						  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  	
								 String dateFacture="";
						  		   
								 
								 dateFacture=dateFormat.format(facturepfTmp.getDateFacture());
								/*
								 * if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
								 * 
								 * }else { dateFacture =facturepfTmp.getNumFacture().substring(4,
								 * 6)+"/"+facturepfTmp.getNumFacture().substring(2,
								 * 4)+"/"+20+facturepfTmp.getNumFacture().substring(0, 2); }
								 */
						  		 
									Map parameters = new HashMap();
								
									parameters.put("client", facturepfTmp.getClientPF().getNom());
									
									
										parameters.put("NumBL", facturepfTmp.getNumBl());
										
										if(facturepfTmp.getDateBl()!=null)
										{
											parameters.put("dateBl",dateFormat.format( facturepfTmp.getDateBl()));
										}else
										{
											parameters.put("dateBl", "");
										}
										
										if(facturepfTmp.getDateBCommande()!=null)
										{
											parameters.put("dateBC",dateFormat.format(facturepfTmp.getDateBCommande()) );
										}else
										{
											parameters.put("dateBC", "");
										}
										if(facturepfTmp.getNumCommande()!=null)
										{
											parameters.put("NumBC", facturepfTmp.getNumCommande());
										}else
										{
											parameters.put("NumBC", "");
										}
										
								 /*
									 
									if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
									{
										
										parameters.put("ice", Constantes.ICE_ETP);
										
									}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
									{
										
										
										parameters.put("ice", Constantes.ICE_AHLBRAHIM);
										
									}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_ELALAOUI))
									{
										
										
										parameters.put("ice", Constantes.ICE_ELALAOUI);
									}
									
									*/
									
										
										if(facturepfTmp.getAdresseClient()!=null)
										{
											parameters.put("adresse", facturepfTmp.getAdresseClient());
										}else
										{
											parameters.put("adresse", facturepfTmp.getClientPF().getAdresse());
											
										}
									
									
									parameters.put("code",facturepfTmp.getClientPF().getCode());
									
									if(facturepfTmp.getClientPF().getPatente()!=null)
									{
										parameters.put("patente", facturepfTmp.getClientPF().getPatente());
									}else
									{
										parameters.put("patente", "");
									}
									
									if(facturepfTmp.getClientPF().getIce()!=null)
									{
										parameters.put("iceclient",facturepfTmp.getClientPF().getIce());
									}else
									{
										parameters.put("iceclient","");
									}
									
							
									
									if(checkEnTete.isSelected()==true)
									{
										
									if(!comboEnTete.getSelectedItem().equals(""))
									{
										
										EnTete enTete=mapEntete.get(comboEnTete.getSelectedItem().toString());
										
										byte[] blobAsBytes = enTete.getUrl();
										

										InputStream input = new ByteArrayInputStream(blobAsBytes);
										
										parameters.put("logoImpression",input) ;
										
									}
										
										
										
									}
									
									
									
									
									
									
									try {
										
										
										
										 
											 
												 JasperUtils.imprimerBonDeLivraison(listDetailFacturePFTmp,parameters,apercu);
											   
											
											
									 
										
									} catch (PrintException | IOException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showMessageDialog(null, e1.getMessage());
									}
								
									
							 }else
							 {
								 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
							 }
								
							
						}
						
						
					}
					
					
				}
				
				

					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				///////////////////////////////////////////////
				
				/*
				
				detailFacturePfdao.ViderSession();
String sommetowords="";

listDetailFacturePF.clear();
listDetailFacturePFImprimer.clear();
listFacturePFTmp.clear();
listDetailFacturePFImprimer =new ArrayList<DetailFacturePF>();
 listDetailFacturePF=new ArrayList<DetailFacturePF>();
 listFacturePFTmp=new ArrayList<FacturePF>();
 FacturePF facturepfTmp=listFacturePF.get(table.getSelectedRow());
 listFacturePFTmp=facturepfdao.findByNumFacture(facturepfTmp.getNumFacture(),facturepfTmp.getDepot());
	for(int k=0;k<listFacturePFTmp.size();k++)
	{
		listDetailFacturePF.addAll(listFacturePFTmp.get(k).getDetailFacturePF());
	}

				boolean trouve=false;
				 if(listDetailFacturePF.size()!=0)
				 {
					 
					 for(int i=0;i<listDetailFacturePF.size();i++)
					 {
						 trouve=false;
						 
						 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
						 {
							if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePF.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2)) && !listDetailFacturePF.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2))) 
							{
								trouve=true;
								
								listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePF.get(i).getQuantite()));
								listDetailFacturePFImprimer.get(j).setPrixUnitaire((listDetailFacturePFImprimer.get(j).getPrixUnitaire().add(listDetailFacturePF.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
								listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePF.get(i).getMontantHT()));
								listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePF.get(i).getMontantTVA()));
								listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePF.get(i).getMontantTTC()));
								listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
								listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
								
							}
							
							 
						 }
						 if(trouve==false)
						 {
							 listDetailFacturePFImprimer.add(listDetailFacturePF.get(i)); 
						 }
						 
						 
					 }
					 
					 
			  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			  		  	String dateFacture=dateFormat.format(dateChooserfacture.getDate());
			  		    FacturePF facturepf=listFacturePF.get(table.getSelectedRow());
						
						Map parameters = new HashMap();
						parameters.put("dateFacture", dateFacture);
						String[]totalht=String.format("%,f", new BigDecimal(txttotalmontantHT.getText())).split(",");
						String[]totaltva=String.format("%,f",new BigDecimal(txttotalmontantTVA.getText())).split(",");
						String[]totalttc=String.format("%,f",new BigDecimal(txttotalmontantTTC.getText())).split(",");
						String[]timber=String.format("%,f",new BigDecimal(txttimbre.getText())).split(",");
						String[]netapayer=String.format("%,f",new BigDecimal(txtnetapayer.getText())).split(",");
						
						parameters.put("TotalHT", totalht[0]+","+ totalht[1].substring(0, 2));
						parameters.put("TotalTVA", totaltva[0]+","+ totaltva[1].substring(0, 2));
						parameters.put("TotalTTC",totalttc[0]+","+ totalttc[1].substring(0, 2));
						parameters.put("client", facturepf.getClientPF().getNom());
						
						if(facturepf.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepf.getType().equals(Constantes.TYPE_FACTURE))
						{
							parameters.put("NumFacture", facturepf.getNumFacture());
							parameters.put("type", "Facture N°    :");
							
						}else if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
						{
							parameters.put("NumFacture", facturepf.getNumBl());
							parameters.put("type", "BL N°    :");
						}
						
						
						parameters.put("tva", Constantes.TVA);
						if(utilisateur.getId()==1)
						{
							parameters.put("ice", Constantes.ICE_ETP);
						}else if (utilisateur.getId()==2)
						{
							parameters.put("ice", Constantes.ICE_AHLBRAHIM);
						}
						
						parameters.put("adresse", facturepf.getClientPF().getAdresse());
						parameters.put("timber",timber[0]+","+ timber[1].substring(0, 2));
						parameters.put("netapayer",netapayer[0]+","+ netapayer[1].substring(0, 2));
						parameters.put("code",facturepf.getClientPF().getCode());
						
						if(facturepf.getClientPF().getIce()!=null)
						{
							parameters.put("iceclient",facturepf.getClientPF().getIce());
						}else
						{
							parameters.put("iceclient","");
						}
						
						
						if(checkmodepaiement.isSelected()==true)
						{
							if( facturepf.getModeReglement()!=null)
							{
								parameters.put("modepaiement", facturepf.getModeReglement());
							}else
							{
								parameters.put("modepaiement", "");
							}
					
						
						}else
						{
						parameters.put("modepaiement", "");
						}
						
						//double totalttc=Double.valueOf(txtnetapayer.getText());
						String x=txtnetapayer.getText().replace(".", ",");
						
						sommetowords= ConverterNumberToWords.converter(x);
					
						parameters.put("NumberToWords",sommetowords);
						
						
						
						JasperUtils.imprimerFacturePF(listDetailFacturePFImprimer,parameters);
					
						
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				 }
					
					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				
			*/
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    	}
	    });
	    button_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button_4.setBounds(641, 912, 112, 32);
	    button_4.setIcon(imgImprimer);
	    add(button_4);
	    
	     checkEnTete = new JCheckBox("Avec En Tete");
	     checkEnTete.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		

	    		
	    		if(checkEnTete.isSelected()==true)
	    		{
	    			lblEnTete.setVisible(true);
	    			comboEnTete.setVisible(true);
	    			comboEnTete.setSelectedItem("");
	    			
	    		}else
	    		{
	    			lblEnTete.setVisible(false);
	    			comboEnTete.setVisible(false);
	    			comboEnTete.setSelectedItem("");
	    		}
	    		
	    		
	    	
	     		
	     		
	     	}
	     });
	    checkEnTete.setFont(new Font("Tahoma", Font.BOLD, 11));
	    checkEnTete.setBounds(1478, 268, 229, 30);
	    add(checkEnTete);
	    
	     comboEnTete = new JComboBox();
	    comboEnTete.setSelectedIndex(-1);
	    comboEnTete.setBounds(1540, 311, 205, 24);
	    add(comboEnTete);
	    
	     lblEnTete = new JLabel("En Tete :");
	    lblEnTete.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblEnTete.setBounds(1478, 310, 107, 24);
	    add(lblEnTete);
		
	    
	    lblEnTete.setVisible(false);
		comboEnTete.setVisible(false);
		comboEnTete.addItem("");
		   if(utilisateur.getLogin().equals("admin"))
			  {
		    	 listEntet=enTeteDAO.findAll();
		    	 
		    	  for(int i=0;i<listEntet.size();i++)
				  {
					  
					  EnTete enTete=listEntet.get(i);
					  comboEnTete.addItem(enTete.getVille());
					  mapEntete.put(enTete.getVille(), enTete);
					  
				  }
			      
			  }else
			  {
				  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
				  if(depot!=null)
				  {
					  listEntet=enTeteDAO.findEnTeteByDepot(depot); 
					  
					  for(int i=0;i<listEntet.size();i++)
					  {
						  
						  EnTete enTete=listEntet.get(i);
						  comboEnTete.addItem(enTete.getVille());
						  mapEntete.put(enTete.getVille(), enTete);
						  
					  }
			     	
				  }
			  }
	    
	    
		}
	

boolean remplirmaptransfereblfacture(){
	boolean trouve=false;
	int i=0;
	listClientFacture.clear();
	for(int j=0;j<table.getRowCount();j++){
		
		boolean regle=(boolean) table.getValueAt(j, 9);
		if(regle==true ){
			
			maptransfereblfacture.put(String.valueOf(table.getValueAt(j, 1).toString()), Boolean.valueOf(table.getValueAt(j, 9).toString()));
			FacturePF facturePF=listFacturePF.get(j);
			listClientFacture.add(facturePF.getClientPF());
			
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}
	

boolean remplirMapImprimer(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		boolean imprimer=(boolean) table.getValueAt(j, 10);
		if(imprimer==true ){
			FacturePF facturePF=listFacturePF.get(j);
			
			mapImprimer.put(facturePF.getId(), facturePF);
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}	
	
	void initialiserFacture()
	{
		txtnumbl.setText("");
		dateChooserfacture.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.removeAllItems();
		combomagasin.setSelectedIndex(-1);
		comboClientpf.setSelectedIndex(-1);
		txttotalmontantTTC.setText("");
		txttotalquantite.setText("");
		txttotalmontantHT.setText("");
		txttotalmontantTVA.setText("");
		comboFournisseur.removeAllItems();
		comboFournisseur.setSelectedIndex(-1);
		comboTypeBL.setSelectedItem("");
		chckbxPiece.setSelected(false);
	}

	void initialiser()
	{
		txtcodearticle.setText("");
		comboArticle.setSelectedIndex(-1);
	
	   txtPrix.setText("");
		txtquantite.setText("");
		txtmontant.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	     combofamille.setSelectedItem("");
	     combosousfamille.removeAllItems();
	     checkboxGratuits.setSelected(false);
	     txtquantitepaquet.setText("");
	     txtquantitepaquet.setEnabled(false);
	     checkttc.setSelected(false);
	 	stockDisponible.setText("");
		labelPrixMax.setText("");
		labelPrixMin.setText("");
		
		lblPrix.setVisible(true);
		lblMontant.setVisible(true);
		lblRemise.setVisible(true);
		labelpourcentage.setVisible(true);
		txtPrix.setVisible(true);
		txtreduction.setVisible(true);
		txtmontant.setVisible(true);
		checkttc.setVisible(true);
		
		lblOffreTherre.setVisible(false);
		comboBoxtherres.setVisible(false);
		txtquantitetherres.setVisible(false);
		lblOffreVerres.setVisible(false);
		comboBoxverres.setVisible(false);
		txtquantiteverres.setVisible(false);
		stockdisponibleoffretherres.setText("");
		stockdisponibleoffreverres.setText("");
		txtquantiteverres.setText("");
		txtquantitetherres.setText("");
		
		   lblOffrePromo.setVisible(false);
			comboBoxPromo.setVisible(false);
			txtquantitepromo.setVisible(false);	
			stockdisponiblearticlepromo.setText("");
			txtquantitepromo.setText("");
			
			comboBoxPromo.removeAllItems();
			mapArticlePromo.clear();
		
			txtquantitepromo.setText("");
				txtquantitetherres.setText("");
				txtquantiteverres.setText("");
				checkboxSansTva.setSelected(false);
				
				
				  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) 
					 {
				    	checkboxSansTva.setSelected(false);
				    	checkboxSansTva.setEnabled(true);
					 }else {
						 checkboxSansTva.setSelected(true);
						 checkboxSansTva.setEnabled(false);
					 }	
		
	}
	void initialiserPourFacture()
	{
		txtcodearticle.setText("");
		comboArticle.setSelectedIndex(-1);
	   txtPrix.setText("");
		txtquantite.setText("");
	     txtreduction.setText("");
	    
	}
	
	
	void initialiserGratuit()
	{
		txtcodearticle.setText("");
		comboArticle.setSelectedIndex(-1);
	
	   txtPrix.setText("");
		txtquantite.setText("");
		txtmontant.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	     combofamille.setSelectedItem("");
	     combosousfamille.removeAllItems();
	   
		
	}
	
	void InitialiseTableau()
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite", "Reduction", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		 tableArticle.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableArticle.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableArticle.getColumnModel().getColumn(2).setPreferredWidth(94);
		
	
}
	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Type BL",	"Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Magasin", "Montant HT","Transfere BL en Facture","Imprimer BL"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,String.class,Date.class,String.class,String.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class, Boolean.class
					};
				  public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelefacture);
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(136);
		table.getColumnModel().getColumn(7).setPreferredWidth(136);
		table.getColumnModel().getColumn(8).setPreferredWidth(136);
		table.getColumnModel().getColumn(9).setPreferredWidth(136);
}
	
	
	
	void afficher_tableDetailFacturePF(List<DetailFacturePF> listDetailFacture)
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite", "Reduction", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		int i=0;
		 
		while(i<listDetailFacture.size())
		{	
		DetailFacturePF detailfacturepf=listDetailFacture.get(i);
			
			Object []ligne={detailfacturepf.getSousFamille().getFamileArticlePF().getLiblle(),detailfacturepf.getSousFamille().getLiblle(), detailfacturepf.getArticle().getLiblle(),detailfacturepf.getPrixUnitaire(),detailfacturepf.getQuantite(),detailfacturepf.getReduction(), detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(), detailfacturepf.getMontantTTC()};

			modeleArticle.addRow(ligne);
			i++;
		}
}
	
	
	void afficher_tableDetailFacturePFCumule(List<DetailFacturePFCumule> listDetailFactureCumule)
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite", "Reduction", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		int i=0;
		 
		while(i<listDetailFactureCumule.size())
		{	
		DetailFacturePFCumule detailfacturepfCumule=listDetailFactureCumule.get(i);
			
			Object []ligne={detailfacturepfCumule.getSousFamille().getFamileArticlePF().getLiblle(),detailfacturepfCumule.getSousFamille().getLiblle(), detailfacturepfCumule.getArticle().getLiblle(),detailfacturepfCumule.getPrixUnitaire(),detailfacturepfCumule.getQuantite(),detailfacturepfCumule.getReduction(), detailfacturepfCumule.getMontantHT(),detailfacturepfCumule.getMontantTVA(), detailfacturepfCumule.getMontantTTC()};

			modeleArticle.addRow(ligne);
			i++;
		}
}
	
	
	
	void chargerListeFacture()
	{
		
		InitialiseTableauFacture();
		
		boolean exist=false;
		
		ClientPF clientpf=mapParClientPF.get(comboparclient.getSelectedItem());
		Depot depot=mapparDepot.get(combopardepot.getSelectedItem());
		
		initialiserFacture();
		initialiser();
		InitialiseTableau();
		listFacturePF.clear();
		listFacturePFTmp.clear();
		facturepfdao=ProdLauncher.facturePFDAO;
		if(datedebut.getDate()!=null && pardateChooser.getDate()!=null)
		{	
			String d1=((JTextField)datedebut.getDateEditor().getUiComponent()).getText();
			String d2=((JTextField)pardateChooser.getDateEditor().getUiComponent()).getText();
			
		if(!d1.equals(d2))
		{
			if(pardateChooser.getDate().compareTo(datedebut.getDate())<0)
			{
				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!");
				return;
			}
			
		}
		}
		
		if(datedebut.getDate()==null)
		{
			pardateChooser.setCalendar(null);
		}
		
		
		String typeBL="";
		if(comboParTypeBL.getSelectedIndex()!=-1)
		{
			
			if(!comboParTypeBL.getSelectedItem().toString().equals(""))
			{
				
				
				typeBL=comboParTypeBL.getSelectedItem().toString();
			}
		}
		
		
		//listFacturePFTmp=facturepfdao.findByNumFcatureClientDateFactureDepotEtatRegle(txtparnumBL.getText(),clientpf, pardateChooser.getDate(), depot,Constantes.ETAT_REGLE);	
		
			listFacturePFTmp=facturepfdao.findByNumFcatureClientDateFactureDepotEtatRegle(txtparnumBL.getText(),clientpf,datedebut.getDate(), pardateChooser.getDate(), depot,Constantes.ETAT_NON_REGLE,typeBL);
		
			if(!comboparfamille.getSelectedItem().equals(""))
			{
				if(!comboparsousfamille.getSelectedItem().equals(""))
				{
					
					SousFamilleArticlePF sousfamille=mapsousfamille.get(comboparsousfamille.getSelectedItem());
				
					int i=0;
					while (i<listFacturePFTmp.size())
					{
						FacturePF facturePF=listFacturePFTmp.get(i);
						exist=false;
						listDetailFacturePFTMP=detailFacturePfdao.listeDetailFacturePFByFacture(facturePF.getId());
						for(int j=0;j<listDetailFacturePFTMP.size();j++)
						{
							DetailFacturePF detailfacturePF=listDetailFacturePFTMP.get(j);
							
							if(detailfacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()))
							{
								
								exist=true;
								
							}
							
						}
						if(exist==true)
						{
							listFacturePF.add(facturePF);
						}
						
						i++;
						
					}
					
					
					
					if(listFacturePF.size()==0)
					{
						//initialiserFacture();
			    		initialiser();
			    		InitialiseTableau();
			    		InitialiseTableauFacture();
					}else
					{
						afficher_tableFacturePF(listFacturePF);
					}
				
				}else
				{
					listFacturePF.addAll(listFacturePFTmp);
					if(listFacturePF.size()==0)
					{
						//initialiserFacture();
						InitialiseTableauFacture();
			    		initialiser();
			    		InitialiseTableau();
					}else
					{
						
						afficher_tableFacturePF(listFacturePF);
					}
					
				}
				
			
				
			}else
			{
				listFacturePF.addAll(listFacturePFTmp);
				if(listFacturePF.size()==0)
				{
					//initialiserFacture();
					InitialiseTableauFacture();
		    		initialiser();
		    		InitialiseTableau();
				}else
				{
					
					afficher_tableFacturePF(listFacturePF);
				}
				
				
			}
			
			
		
		
	}
	
	
	
	
	void afficher_tableFacturePF(List<FacturePF> listFacture)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Type BL","Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Magasin", "Montant TTC","Transfere BL en Facture","Imprimer BL"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,true,true
				};
				Class[] columnTypes = new Class[] {
						String.class,String.class,Date.class,String.class,String.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class, Boolean.class
					};
				  public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelefacture);
		int i=0;
		BigDecimal MontantTTC=BigDecimal.ZERO;
		 String typeBL="";
		while(i<listFacture.size())
		{	
			typeBL="";
		MontantTTC=BigDecimal.ZERO;
		FacturePF facturepf=listFacture.get(i);
		
		Date datefacture=facturepf.getDateFacture();
			
		for(int j=0;j<facturepf.getDetailFacturePF().size();j++)
		{
			
			MontantTTC=MontantTTC.add(facturepf.getDetailFacturePF().get(j).getMontantTTC());
		}
		
		if(facturepf.getTypeBL()!=null)
		{
			typeBL=facturepf.getTypeBL().getType();
		}
		
		
			Object []ligne={typeBL,facturepf.getNumBl(),datefacture,facturepf.getType(),facturepf.getNumFacture(),facturepf.getClientPF().getNom(),facturepf.getDepot().getLibelle(),facturepf.getMagasin().getLibelle(),MontantTTC,false,false};

			modelefacture.addRow(ligne);
			i++;
		}
}
	
	void reglerTransferStockPF()
	{
		
		/* code pour regler le transfer stock produit fini pour que les articles de detail facture produits fini egal aux articles de detail transfer stock produit fini

		ClientPF clientpf=mapParClientPF.get(comboparclient.getSelectedItem());
		Depot depot=mapparDepot.get(combopardepot.getSelectedItem());
		 boolean trouve=false;
		 List<DetailFacturePF> listDetailFacturePFTmp =new ArrayList<DetailFacturePF>();
		 List<FacturePF> listFacturePFTmp =new ArrayList<FacturePF>();
		 List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
		
		 listFacturePFTmp= facturepfdao.findByNumFcatureClientDateFactureDepotEtatRegle(txtparnumBL.getText(),clientpf, pardateChooser.getDate(), depot,Constantes.ETAT_NON_REGLE);
		 
		 for(int i=0 ; i<listFacturePFTmp.size() ; i++)
		 {
			 
			 FacturePF facturepf=listFacturePFTmp.get(i);
			 listDetailFacturePFTmp=facturepf.getDetailFacturePF();
			 TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(facturepf.getCodeTransfer());
			 listDetailTransferProduitFini=transferStockPF.getListDetailTransferProduitFini();
			 for(int j=0;j<listDetailFacturePFTmp.size();j++)
			 {
				trouve=false;
				 DetailFacturePF detailfacturepf=listDetailFacturePFTmp.get(j);
				
				 for(int k=0;k<listDetailTransferProduitFini.size();k++)
				 {
					if(detailfacturepf.getArticle().getId()== listDetailTransferProduitFini.get(k).getArticle().getId())
						
					{
						
						if( detailfacturepf.getQuantite().setScale(2, RoundingMode.HALF_UP).equals(listDetailTransferProduitFini.get(k).getQuantite().setScale(2,RoundingMode.HALF_UP)))
						{
							trouve=true;
						}
						
						}
					 
				 }
				 
				 if(trouve==false)
				 {
					DetailTransferProduitFini detailTransferProduitFini=new DetailTransferProduitFini();
					detailTransferProduitFini.setArticle(detailfacturepf.getArticle());
					detailTransferProduitFini.setDateTransfer(transferStockPF.getDateTransfer());
					detailTransferProduitFini.setPrixUnitaire(detailfacturepf.getPrixUnitaire());
					detailTransferProduitFini.setQuantite(detailfacturepf.getQuantite());
					detailTransferProduitFini.setSousFamille(detailfacturepf.getSousFamille());
					detailTransferProduitFini.setTransferStockPF(transferStockPF);
					detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
					detailTransferProduitFini.setMagasinDestination(facturepf.getMagasin());
					detailTransferStockPFdao.add(detailTransferProduitFini);
				 }
				 
				 
			 }
			  
		 }
		 
		 JOptionPane.showMessageDialog(null, "La Modification Effectué avec succée");
		 
		 
		*/
		
	}
	
	
	
	
	
	
	
	public void ChercherBLNonFacture(Date date)
	{
		MessageBlNonFacturer="";
		Depot depot=mapparDepot.get(combopardepot.getSelectedItem());
		
		listFacturePFBLNonFacturer=facturepfdao.listeFacturePFNonFacturer(date, depot , Constantes.TYPE_BON_LIVRAISON);
		
		listFactureAutresVenteBLNonFacturer=factureAutresVenteDAO.listFactureAutresVenteNonFacturer(date, depot , Constantes.TYPE_BON_LIVRAISON);
		
		listFactureServiceTransportBLNonFacturer=factureServiceTransportDAO.listeFactureNonFacturer(date, depot , Constantes.TYPE_BON_LIVRAISON);
		
		listProductionServiceLancerBLNonFacturer=productionDAO.listeProductionServiceNonFacturer(Constantes.ETAT_OF_CREER, Constantes.ETAT_OF_LANCER, depot.getCode(), Constantes.PRODUCTION_SERVICE_OUI, date);
		
		if(listFacturePFBLNonFacturer.size()!=0)
		{
			MessageBlNonFacturer=MessageBlNonFacturer+"Il y a des Factures PF dont la date Facture est inférieur à "+date +" pas encore facturé"+"\n";
			
		}
		
		if(listFactureAutresVenteBLNonFacturer.size()!=0)
		{
			MessageBlNonFacturer=MessageBlNonFacturer+"Il y a des Factures Autres vente dont la date Facture est inférieur à "+date +" pas encore facturé"+"\n";

		}
		
		
		if(listFactureServiceTransportBLNonFacturer.size()!=0)
		{
			
			MessageBlNonFacturer=MessageBlNonFacturer+"Il y a des Factures Service Transport dont la date Facture est inférieur à "+date +" pas encore facturé"+"\n";

			
		}
		
		if(listProductionServiceLancerBLNonFacturer.size()!=0)
		{
			
			MessageBlNonFacturer=MessageBlNonFacturer+"Il y a des Productions Service Creer ou Lancer dont la date Production est inférieur à "+date +"  pas encore facturé  , Veuillez le terminer ou Bien Annulé"+"\n";

			
		}
		
		
	
		
		
	}
	}




