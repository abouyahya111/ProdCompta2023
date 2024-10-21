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
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.HistoriqueVenteVendeurDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
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
import dao.daoManager.FacturePFDAO;
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
import dao.entity.DetailFraisDepot;
import dao.entity.DetailHistoriqueVenteVendeur;
import dao.entity.DetailPrixArticle;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EnTete;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
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


public class ConsulterHistoriqueVenteVendeurPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;

	private JXTable  tableArticle = new JXTable();
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<ClientPF> listParClientPF =new ArrayList<ClientPF>();
	private List<DetailHistoriqueVenteVendeur> listDetailFacturePF =new ArrayList<DetailHistoriqueVenteVendeur>();
	private List<DetailHistoriqueVenteVendeur> listDetailFacturePFTMP =new ArrayList<DetailHistoriqueVenteVendeur>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Articles> listArticleTmp =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<DetailPrixArticle> listArticleStockPF =new ArrayList<DetailPrixArticle>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<HistoriqueVentevendeur> listFacturePF =new ArrayList<HistoriqueVentevendeur>();
	private List<HistoriqueVentevendeur> listFacturePFTmp =new ArrayList<HistoriqueVentevendeur>();
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
	private ImageIcon imgImprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgSelectAll;
	private ImageIcon imgDeselectAll;
	
	private  JButton btninitialiser = new JButton();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private   JComboBox comboparclient = new JComboBox();
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private HistoriqueVenteVendeurDAO facturepfdao;
	private HistoriqueVentevendeur facturePF;
	private DetailTransferProduitFiniDAO detailTransferStockPFdao;
private DetailHistoriqueVenteVendeurDAO detailFacturePfdao;
private ClientPFDAO clientpfdao;
private ClientDAO fournisseurdao;
private FamilleArticlesPFDAO familleArticleDAO;
private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
private DetailPrixArticleDAO detailPrixArticleDAO;
TransferStockPF transferStock ;
private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
private TransferStockPFDAO transferStockPFDAO;
private List <Object[]> listeObject=new ArrayList<Object[]>();
	ChargeProduction chargeproduction;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	private ParametreDAO parametreDAO;
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private JTextField txttotalmontantTTC;
	private JTextField txttotalquantite;
	
	 private  JComboBox comboFournisseur = new JComboBox();
	private JTextField txtparnumBL;
	private JRadioButton rdbtnDateFacture;
	private JDateChooser pardateChooser;
	private  JComboBox combopardepot;
	private JTextField txttotalmontantTVA;
	private JTextField txttotalmontantHT;
	private CompteClientDAO compteclientdao;
	 private  JComboBox comboparsousfamille ;
	private JComboBox comboparfamille;
	JCheckBox checkboxGratuits = new JCheckBox("Gratuit");
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
	  BigDecimal stockfinaleTherres=BigDecimal.ZERO;
	  BigDecimal stockfinaleVerres=BigDecimal.ZERO;
	  BigDecimal stockfinaleArticlePromo=BigDecimal.ZERO;
	  JLabel lblOffreTherre = new JLabel("Offre Therre :");
	 
	  JLabel lblOffreVerres = new JLabel("Offre Verres :");
	  JComboBox comboBoxverres = new JComboBox();
	  JLabel stockdisponibleoffretherres = new JLabel("");
	  JLabel stockdisponibleoffreverres = new JLabel("");
	  JLabel lblOffrePromo = new JLabel("Offre Promo :");
	  JButton btnModifier = new JButton();
	  JLabel stockdisponiblearticlepromo = new JLabel("");
	  JComboBox comboBoxtherres = new JComboBox();
	  private List<FacturePF> listFacturePFOrderByDate =new ArrayList<FacturePF>();
	  private List<FacturePF> listFacturePFOrderByNumFacture =new ArrayList<FacturePF>();
	  private JTextField txtlien;
	 
	  JCheckBox checkttc = new JCheckBox("TTC");
	  private JFrame mainFrame;
	  private JTextField txttimbre;
	  private JTextField txtnetapayer;
	  private JTextField txtPrix;
	  private JTextField txtcodearticle;
	  private JTextField txtquantite;
	  private JTextField txtquantitepaquet;
	  private JTextField txtmontant;
	  private JTextField txtreduction;
	  private JTextField txtquantitetherres;
	  private JTextField txtquantiteverres;
	  private JTextField txtquantitepromo;
	  JComboBox comboBoxPromo = new JComboBox();
	  JButton btnSupprimer = new JButton();
	  JButton btnAjouter = new JButton("Ajouter");
	  JComboBox combofamille = new JComboBox();
	  JComboBox combosousfamille = new JComboBox();
	  JComboBox comboArticle = new JComboBox();
	  JButton button_4 = new JButton("Valider ");
	  private JButton btn_valider;
	  private JTextField txtnumbl;
	  JComboBox comboClientpf = new JComboBox();
	  JDateChooser dateChooserfacture = new JDateChooser();
	   String ModePaiement = "";
		   String numpiece="";
		   JCheckBox checkSansmodepaiement = new JCheckBox("Imprimer Sans Mode de Paiement");
		   JCheckBox chckbxSansTimbre = new JCheckBox("Sans Timbre");
		   JComboBox comboParTypeBL = new JComboBox();
		   JComboBox comboTypeBL = new JComboBox();
		   private List<TypeBL> listTypeBL =new ArrayList<TypeBL>();
		   private Map< String, TypeBL> mapTypeBL= new HashMap<>();
		   private TypeBLDAO typeBLDAO;
		   JCheckBox chckbxPiece = new JCheckBox("Piece");
		   JComboBox comboEnTete = new JComboBox();
		   private  List<EnTete>listEntet=new ArrayList<EnTete>();
			 EnTeteDAO enTeteDAO;
			 private Map< String, EnTete> mapEntete= new HashMap<>();
			 JCheckBox checkEnTete = new JCheckBox("Avec En Tete");
			 JCheckBox chckbxImprimerNumBl = new JCheckBox("Imprimer Num BL");
			 JCheckBox chckbxImprimerNumBc = new JCheckBox("Imprimer Num BC");
			 JButton btnImprimerBl = new JButton("Imprimer BL");
			 JLabel lblEnTete = new JLabel("En Tete :");
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterHistoriqueVenteVendeurPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));
         
		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1895, 1169);
      
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
            
             
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	facturepfdao=new HistoriqueVenteVendeurDAOImpl();
         	detailFacturePfdao=new DetailHistoriqueVenteVendeurDAOImpl();
         	clientpfdao=new ClientPFDAOImpl();
         	articleDAO=new ArticlesDAOImpl();
         
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
				  		     	scrollPane.setBounds(10, 646, 1375, 228);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 618, 1369, 30);
				  		     	add(titledSeparator);
		      
		
		     
		
		JButton buttonvalider = new JButton("Imprimer Facture");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(listDetailFacturePF.size()!=0)
				{
	                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	                List<DetailHistoriqueVenteVendeur> listDetailFacturePFImprimer =new ArrayList<DetailHistoriqueVenteVendeur>();
	    			List<DetailHistoriqueVenteVendeur> listDetailFacturePFImprimerTmp =new ArrayList<DetailHistoriqueVenteVendeur>();
	                
	    			boolean trouve=false;
	    			 BigDecimal montanttotal=BigDecimal.ZERO;
	 		        BigDecimal montanttotalHT=BigDecimal.ZERO;
	 		        BigDecimal montanttotalTVA=BigDecimal.ZERO;
	 		      
	 		        BigDecimal quantitePieceCalculer=BigDecimal.ZERO; 
			       BigDecimal prixPieceCalculer=BigDecimal.ZERO; 
			       BigDecimal MontantHTPieceCalculer=BigDecimal.ZERO; 
			       BigDecimal MontantTVAPieceCalculer=BigDecimal.ZERO; 
			       BigDecimal MontantTTCPieceCalculer=BigDecimal.ZERO; 
	 		        
	 		        
	           	 for(int i=0;i<listDetailFacturePF.size();i++)
				 {
					 trouve=false;
					 
					 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
					 {
						if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePF.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6)) && !listDetailFacturePF.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6))) 
						{
							trouve=true;
							
							if(listDetailFacturePFImprimer.get(j).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePFImprimer.get(j).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
							{
								if(listDetailFacturePFImprimer.get(j).getHistoriqueventevendeur().getImprimerPiece()==true)
								{
									
									 quantitePieceCalculer=listDetailFacturePF.get(i).getQuantite().multiply(new BigDecimal(1000)).divide(listDetailFacturePF.get(i).getArticle().getCentreCout1(),0,RoundingMode.HALF_UP); 
							         prixPieceCalculer=listDetailFacturePF.get(i).getPrixUnitaire().divide(listDetailFacturePF.get(i).getArticle().getConditionnement(), 6, RoundingMode.HALF_UP); 
							         MontantHTPieceCalculer=quantitePieceCalculer.multiply(prixPieceCalculer) ; 
							        if(listDetailFacturePF.get(i).getMontantTVA().compareTo(BigDecimal.ZERO)!=0)
							        {
							        	 MontantTVAPieceCalculer=MontantHTPieceCalculer.multiply(Constantes.TVA);
							        }
							         
							         MontantTTCPieceCalculer=MontantHTPieceCalculer.add(MontantTVAPieceCalculer);
									
									listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(quantitePieceCalculer));
									listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(MontantHTPieceCalculer));
									listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

									listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(MontantTVAPieceCalculer));
									listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(MontantTTCPieceCalculer));
									listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
									listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
									
									
									
									
									
									
									 
									 
									
								}else
								{
									listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePF.get(i).getQuantite()));
									listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePF.get(i).getMontantHT()));
									listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

									listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePF.get(i).getMontantTVA()));
									listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePF.get(i).getMontantTTC()));
									listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
									listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
								}
								
								
							}else
							{
								
								listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePF.get(i).getQuantite()));
								listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePF.get(i).getMontantHT()));
								listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

								listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePF.get(i).getMontantTVA()));
								listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePF.get(i).getMontantTTC()));
								listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
								listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
								
							}
							
						
							
						}
						
						
					 }
					 if(trouve==false)
					 {
							if(listDetailFacturePF.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePF.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
							{
								if(listDetailFacturePF.get(i).getHistoriqueventevendeur().getImprimerPiece()==true)
								{
									
									 quantitePieceCalculer=listDetailFacturePF.get(i).getQuantite().multiply(new BigDecimal(1000)).divide(listDetailFacturePF.get(i).getArticle().getCentreCout1(),0,RoundingMode.HALF_UP); 
							         prixPieceCalculer=listDetailFacturePF.get(i).getPrixUnitaire().divide(listDetailFacturePF.get(i).getArticle().getConditionnement(), 6, RoundingMode.HALF_UP); 
							         MontantHTPieceCalculer=quantitePieceCalculer.multiply(prixPieceCalculer) ; 
							        if(listDetailFacturePF.get(i).getMontantTVA().compareTo(BigDecimal.ZERO)!=0)
							        {
							        	 MontantTVAPieceCalculer=MontantHTPieceCalculer.multiply(Constantes.TVA);
							        }
							         
							         MontantTTCPieceCalculer=MontantHTPieceCalculer.add(MontantTVAPieceCalculer);
							         
							         
							         listDetailFacturePF.get(i).setQuantite(quantitePieceCalculer);
							         listDetailFacturePF.get(i).setPrixUnitaire(prixPieceCalculer);
							         listDetailFacturePF.get(i).setMontantHT(MontantHTPieceCalculer);
							         listDetailFacturePF.get(i).setMontantTVA(MontantTVAPieceCalculer);
							         listDetailFacturePF.get(i).setMontantTTC(MontantTTCPieceCalculer);
							         listDetailFacturePFImprimer.add(listDetailFacturePF.get(i));
									
								}else
								{
									listDetailFacturePFImprimer.add(listDetailFacturePF.get(i)); 
								}
								
							}else
							{
								
								listDetailFacturePFImprimer.add(listDetailFacturePF.get(i));
								
							}
						 
						  
					 }
					  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePF.get(i).getMontantTTC().setScale(6, RoundingMode.DOWN)); 
			          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePF.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
			          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePF.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
					 
				 }
				 
				 if(listDetailFacturePFImprimer.size()!=0)
				 {
					 
					    montanttotal=BigDecimal.ZERO;
				         montanttotalHT=BigDecimal.ZERO;
				         montanttotalTVA=BigDecimal.ZERO;
					 
					 for(int i=0;i<listDetailFacturePFImprimer.size();i++)
					 {
						 
						 quantitePieceCalculer=BigDecimal.ZERO; 
				         prixPieceCalculer=BigDecimal.ZERO; 
				         MontantHTPieceCalculer=BigDecimal.ZERO; 
				         MontantTVAPieceCalculer=BigDecimal.ZERO; 
				         MontantTTCPieceCalculer=BigDecimal.ZERO;
						 trouve=false;
						 
						 for(int j=0;j<listDetailFacturePFImprimerTmp.size();j++)
						 {
							 
							 
							 
							 
							if(listDetailFacturePFImprimerTmp.get(j).getArticle().equals(listDetailFacturePFImprimer.get(i).getArticle()) && listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2)) && listDetailFacturePFImprimer.get(i).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2))) 
							{
								trouve=true;
								
								
									
									listDetailFacturePFImprimerTmp.get(j).setQuantite(listDetailFacturePFImprimerTmp.get(j).getQuantite().add(listDetailFacturePFImprimer.get(i).getQuantite()));
									listDetailFacturePFImprimerTmp.get(j).setPrixUnitaire((listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().add(listDetailFacturePFImprimer.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
									listDetailFacturePFImprimerTmp.get(j).setMontantHT(listDetailFacturePFImprimerTmp.get(j).getMontantHT().add(listDetailFacturePFImprimer.get(i).getMontantHT()));
									listDetailFacturePFImprimerTmp.get(j).setMontantTVA(listDetailFacturePFImprimerTmp.get(j).getMontantTVA().add(listDetailFacturePFImprimer.get(i).getMontantTVA()));
									listDetailFacturePFImprimerTmp.get(j).setMontantTTC(listDetailFacturePFImprimerTmp.get(j).getMontantTTC().add(listDetailFacturePFImprimer.get(i).getMontantTTC()));
									listDetailFacturePFImprimerTmp.get(j).setReduction(listDetailFacturePFImprimerTmp.get(j).getReduction().add(listDetailFacturePFImprimer.get(i).getReduction()));			
									listDetailFacturePFImprimerTmp.set(j, listDetailFacturePFImprimerTmp.get(j));
									
							
								
								
								
							}
							
							
						 }
						 if(trouve==false)
						 {
							 
							
									
									 listDetailFacturePFImprimerTmp.add(listDetailFacturePFImprimer.get(i)); 
								
							 
							
						 }
						  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTTC().setScale( 6, RoundingMode.DOWN)); 
				          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
				          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
						 
					 }  
				 }
	                
				
				 
				 /////////////////////////////////////////////////////////////// Traitement Reglemnt ///////////////////////////////////////////////////////////////
				 
				 
		  		    ModePaiement = "";
		  		    numpiece="";
	                boolean existe=false;   
	                BigDecimal montantcheque=BigDecimal.ZERO;  
	                BigDecimal montantespece=BigDecimal.ZERO;   
	                BigDecimal montantvirement=BigDecimal.ZERO;  
	                BigDecimal montanttraite=BigDecimal.ZERO;   
	                BigDecimal montantversement=BigDecimal.ZERO;  
	                String numcheque="";
	                String numversement="";
	                String numtraite="";
	                String modereglement="";
	             
	                
	                BigDecimal timber = BigDecimal.ZERO;
	               
	                    
	                  
	                
	                
	                
	                           if(facturePF.getEspece()!=null) 
	                           {
	                         	  if(!facturePF.getEspece().equals(""))
	                         	  {
	                         		  montantespece=facturePF.getEspece();
	                         		  
	                         		  if(BigDecimal.ZERO.compareTo(montantespece)!=0)
	                         		  {
	                         			  
	                         			 modereglement=Constantes.MODE_REGLEMENT_ESPECE;
		                         		  
		                         		 existe=true;
		                         		 
		                         		 
		                                timber = timber.add(Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP).multiply(montantespece)) ;
		                         		 
		                                ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_ESPECE;
	                         		  }
	                         		  
	                         		  
	                         	  }
	                         	 
	                           }
	                              
	                           if(facturePF.getCheque()!=null) 
	                           {
	                         	  if(!facturePF.getCheque().equals(""))
	                         	  {
	                         		  montantcheque=facturePF.getCheque();
	                         		 if(BigDecimal.ZERO.compareTo(montantcheque)!=0)
	                         		  {
	                         			  if(facturePF.getNumCheque()!=null)
		                         		  {
		                         			  if(!facturePF.getNumCheque().equals(""))
			                         		  {
			                         			  numcheque=facturePF.getNumCheque();
			                         			 numpiece=numpiece+" Cheque N° : "+numcheque;
			                         		  } 
		                         			  
		                         		  }
		                         				  
		                         		
		                         		  
		                         		  modereglement=Constantes.MODE_REGLEMENT_CHEQUE;
		                         		 ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_CHEQUE;
		                         		
		                         		 
		                         		 existe=true;
	                         		  }
	                         		  
	                         		
	                         		 
	                         		 
	                         		 
	                         	  }
	                         	 
	                           } 
	                              
	                           if(facturePF.getVirement()!=null) 
	                           {
	                         	  if(!facturePF.getVirement().equals(""))
	                         	  {
	                         		  montantvirement=facturePF.getVirement();
	                         		  
	                         		 if(BigDecimal.ZERO.compareTo(montantvirement)!=0)
	                         		  {
	                         			  modereglement=Constantes.MODE_REGLEMENT_VIREMENT;
			                         		 ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_VIREMENT;
			                         		 existe=true;
	                         			 
	                         		  }
	                         		
	                         	  }
	                         	 
	                           } 
	                           
	                           
	                           if(facturePF.getVersement()!=null) 
	                           {
	                         	  if(!facturePF.getVersement().equals(""))
	                         	  {
	                         		  montantversement=facturePF.getVersement();
	                         		  
	                         		 if(BigDecimal.ZERO.compareTo(montantversement)!=0)
	                         		  {
	                         			 modereglement=Constantes.MODE_REGLEMENT_VERSEMENT;
		                         		 ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_VERSEMENT;
		                         		 if(facturePF.getNumVersement()!=null)
		                         		 {
		                         			  if(!facturePF.getNumVersement().equals(""))
			                         		  {
			                         			  numversement=facturePF.getNumVersement();
			                         			  
			                         			 numpiece=numpiece+" Versement N° : "+numversement;
			                         		  } 
		                         		 }
		                         		
		                         		  
		                         		 existe=true;
		                         		 
		                         		 
		                                 timber = timber.add(Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP).multiply(montantversement)) ;

		                         		  
	                         			 
	                         		  }
	                         		  
	                         		 
	                         	  }
	                         	 
	                           }
	                           
	                           if(facturePF.getTraite()!=null) 
	                           {
	                         	  if(!facturePF.getTraite().equals(""))
	                         	  {
	                         		  
	                         		  montanttraite=facturePF.getTraite();
	                         		  
	                         		 if(BigDecimal.ZERO.compareTo(montanttraite)!=0)
	                         		  {
	                         			  modereglement=Constantes.MODE_REGLEMENT_TRAITE;
			                         		 ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_TRAITE;
			                         		  if(facturePF.getNumtraite()!=null)
			                         		  {
			                         			  if(!facturePF.getNumtraite().equals(""))
				                         		  {
				                         			  numtraite =facturePF.getNumtraite();
				                         			 numpiece=numpiece+" Traite N° : "+numtraite;
				                         		  }
				                         		   
			                         		  }
			                         		
			                         		 existe=true;
			                         		  
	                         			 
	                         		  }
	                         		  
	                         	
	                         	  }
	                         	 
	                           }
	                           
	                
	            if(chckbxSansTimbre.isSelected()==true)
	            {
	            	
	            	txttimbre.setText(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)+"");
	  				 txtnetapayer.setText(new BigDecimal(txttotalmontantTTC.getText()).setScale(2, RoundingMode.HALF_UP)+"");
	  				 
	  			
	            	
	            }else
	            {
	            	txttimbre.setText(timber.setScale(2, RoundingMode.HALF_UP)+"");
	  				 txtnetapayer.setText(new BigDecimal(txttotalmontantTTC.getText()).setScale(2, RoundingMode.HALF_UP).add(timber.setScale(2, RoundingMode.HALF_UP))+"");
	  				 
	  			
	            }
	                
	                      	 
				 
				 
	                
	                
	                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	                
	            
					
					DetailHistoriqueVenteVendeur historiqueVentevendeur=listDetailFacturePF.get(0);
					
					 String sommetowords="";
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			  		  	
						 String dateFacture="";
				  		   
						 
						 dateFacture=dateFormat.format( historiqueVentevendeur.getHistoriqueventevendeur().getDateFacture());
						
						Map parameters = new HashMap();
						parameters.put("dateFacture",dateFacture);
						
String x=txtnetapayer.getText().replace(".", ",");
						
						sommetowords= ConverterNumberToWords.converter(x);
					
						parameters.put("NumberToWords",sommetowords);
						
						String totalht=String.valueOf(new BigDecimal(txttotalmontantHT.getText()));
						String totaltva=String.valueOf(new BigDecimal(txttotalmontantTVA.getText()));
						String totalttc=String.valueOf(new BigDecimal(txttotalmontantTTC.getText()));
						String timbertmp=String.valueOf(new BigDecimal(txttimbre.getText()));
						String netapayerTmp=String.valueOf(new BigDecimal(txtnetapayer.getText()));
						
						parameters.put("TotalHT", totalht);
						parameters.put("TotalTVA", totaltva);
						parameters.put("TotalTTC",totalttc);
						parameters.put("client", historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getNom());
						
							parameters.put("NumFacture", historiqueVentevendeur.getHistoriqueventevendeur().getNumFacture());

 
		                     
		                     
		                     
		     				if(historiqueVentevendeur.getHistoriqueventevendeur().getImprimerAvecNumBl()!=null)
							{
								if(!historiqueVentevendeur.getHistoriqueventevendeur().getImprimerAvecNumBl().equals(""))
								{
									
									if(historiqueVentevendeur.getHistoriqueventevendeur().getImprimerAvecNumBl().equals(Constantes.CODE_OUI))
									{
										 parameters.put("NumBL", historiqueVentevendeur.getHistoriqueventevendeur().getNumBl());
											
											if(historiqueVentevendeur.getHistoriqueventevendeur().getDateBl()!=null)
											{
												parameters.put("dateBl",dateFormat.format( historiqueVentevendeur.getHistoriqueventevendeur().getDateBl()));
											}else
											{
												parameters.put("dateBl", "");
											}
										
										 
									}else if(historiqueVentevendeur.getHistoriqueventevendeur().getImprimerAvecNumBl().equals(Constantes.CODE_NON))
									
									{
										 
										 parameters.put("NumBL", "");
											
											 
												parameters.put("dateBl", "");
											 
										
									}
									
									 
									
								}else
								{
									
									if(chckbxImprimerNumBl.isSelected()==true)
									{
										parameters.put("NumBL", historiqueVentevendeur.getHistoriqueventevendeur().getNumBl());
										
										if(historiqueVentevendeur.getHistoriqueventevendeur().getDateBl()!=null)
										{
											parameters.put("dateBl",dateFormat.format( historiqueVentevendeur.getHistoriqueventevendeur().getDateBl()));
										}else
										{
											parameters.put("dateBl", "");
										}
										
									}else
									{
										
										 parameters.put("NumBL", "");
											
										 
											parameters.put("dateBl", "");
										 
										
									}
									
									
								}
							}else
							{

								if(chckbxImprimerNumBl.isSelected()==true)
								{
									parameters.put("NumBL", historiqueVentevendeur.getHistoriqueventevendeur().getNumBl());
									
									if(historiqueVentevendeur.getHistoriqueventevendeur().getDateBl()!=null)
									{
										parameters.put("dateBl",dateFormat.format( historiqueVentevendeur.getHistoriqueventevendeur().getDateBl()));
									}else
									{
										parameters.put("dateBl", "");
									}
									
								}else
								{
									
									 parameters.put("NumBL", "");
										
									 
										parameters.put("dateBl", "");
									 
									
								}
								
							}
							
							
							
		                 
							if(historiqueVentevendeur.getHistoriqueventevendeur().getImprimerAvecNumCommande()!=null)
							{
								if(!historiqueVentevendeur.getHistoriqueventevendeur().getImprimerAvecNumCommande().equals(""))
								{
									
									if(historiqueVentevendeur.getHistoriqueventevendeur().getImprimerAvecNumCommande().equals(Constantes.CODE_OUI))
									{
										if(historiqueVentevendeur.getHistoriqueventevendeur().getDateBCommande()!=null)
										{
											parameters.put("dateBC",dateFormat.format(historiqueVentevendeur.getHistoriqueventevendeur().getDateBCommande()) );
										}else
										{
											parameters.put("dateBC", "");
										}
										if(historiqueVentevendeur.getHistoriqueventevendeur().getNumCommande()!=null)
										{
											parameters.put("NumBC", historiqueVentevendeur.getHistoriqueventevendeur().getNumCommande());
										}else
										{
											parameters.put("NumBC", "");
										}
										
										 
									}else if(historiqueVentevendeur.getHistoriqueventevendeur().getImprimerAvecNumCommande().equals(Constantes.CODE_NON))
									
									{
										 
										 
											parameters.put("dateBC", "");
										 
										 
											parameters.put("NumBC", "");
										 
											 
										
									}
									
									 
									
								}else
								{
									
									if(chckbxImprimerNumBc.isSelected()==true)
									{
										if(historiqueVentevendeur.getHistoriqueventevendeur().getDateBCommande()!=null)
										{
											parameters.put("dateBC",dateFormat.format(historiqueVentevendeur.getHistoriqueventevendeur().getDateBCommande()) );
										}else
										{
											parameters.put("dateBC", "");
										}
										if(historiqueVentevendeur.getHistoriqueventevendeur().getNumCommande()!=null)
										{
											parameters.put("NumBC", historiqueVentevendeur.getHistoriqueventevendeur().getNumCommande());
										}else
										{
											parameters.put("NumBC", "");
										}
										
									}else
									{
										parameters.put("dateBC", "");
										 
										 
										parameters.put("NumBC", "");
										 
										
									}
									
									
								}
							}else
							{

								if(chckbxImprimerNumBc.isSelected()==true)
								{
								 
									if(historiqueVentevendeur.getHistoriqueventevendeur().getDateBCommande()!=null)
									{
										parameters.put("dateBC",dateFormat.format(historiqueVentevendeur.getHistoriqueventevendeur().getDateBCommande()) );
									}else
									{
										parameters.put("dateBC", "");
									}
									if(historiqueVentevendeur.getHistoriqueventevendeur().getNumCommande()!=null)
									{
										parameters.put("NumBC", historiqueVentevendeur.getHistoriqueventevendeur().getNumCommande());
									}else
									{
										parameters.put("NumBC", "");
									}
									
								}else
								{
									
									parameters.put("dateBC", "");
									 
									 
									parameters.put("NumBC", "");
									 
									
								}
								
							} 
		                     
		                     
							String datechance="";
							if(historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getDelaiPaiement()!=null)
							{
								if(historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getDelaiPaiement().compareTo(BigDecimal.ZERO)!=0)
								{
									
									if(historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getDelaiPaiementParBlOuFacture()!=null)
									{
										
										if(!historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getDelaiPaiementParBlOuFacture().equals(""))
										{
											if(historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getDelaiPaiementParBlOuFacture().equals(Constantes.TYPE_BON_LIVRAISON))
											{
												parameters.put("delai","Delai de paiement :  "+ historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getDelaiPaiement().intValue()+" jours date de bon livraison");
												
												datechance=dateFormat.format(DateUtils.ajoutNbJours(historiqueVentevendeur.getHistoriqueventevendeur().getDateBl(), historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getDelaiPaiement().intValue()));
												
												 
												
												parameters.put("dateechance","Date d'échance     :  "+datechance);
											
											}else if(historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getDelaiPaiementParBlOuFacture().equals(TRANSFERE_BL_FACTURE))
											{
												
												parameters.put("delai","Delai de paiement :  "+ historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getDelaiPaiement().intValue()+" jours date de facture");
											
		                                        datechance=dateFormat.format(DateUtils.ajoutNbJours(historiqueVentevendeur.getHistoriqueventevendeur().getDateFacture(), historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getDelaiPaiement().intValue()));
												
												parameters.put("dateechance","Date d'échance     :  "+datechance);
												
											 
											}else
											{
												parameters.put("delai","");
												parameters.put("dateechance","");
											}
											
											
											
											
										}else
										{
											parameters.put("delai","");
											parameters.put("dateechance","");
										}
									}else
									{
										parameters.put("delai","");
										parameters.put("dateechance","");
									}
									
									
								}else
								{
									parameters.put("delai","");
									parameters.put("dateechance","");
								}
								
								
							}else
							{
								parameters.put("delai","");
								parameters.put("dateechance","");
							}
							
		                     
		                     
						
						parameters.put("tva", Constantes.TVA);
						if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
						{
							
							parameters.put("ice", Constantes.ICE_ETP);
						}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
						{
							
							
							parameters.put("ice", Constantes.ICE_AHLBRAHIM);
						}
						
						if(historiqueVentevendeur.getHistoriqueventevendeur().getAdresseClient()!=null)
						{
							
							if(!historiqueVentevendeur.getHistoriqueventevendeur().getAdresseClient().equals(""))
							{
								parameters.put("adresse", historiqueVentevendeur.getHistoriqueventevendeur().getAdresseClient());
							}else
							{
								parameters.put("adresse", historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getAdresse());
							}
							
							
						}else
						{
							parameters.put("adresse", historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getAdresse());
						}
						 
						parameters.put("timber",timbertmp);
						parameters.put("netapayer",netapayerTmp);
						parameters.put("code",historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getCode());
						
						if(historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getPatente()!=null)
						{
							parameters.put("patente", historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getPatente());
						}else
						{
							parameters.put("patente", "");
						}
						
						
						if(historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getIce()!=null)
						{
							parameters.put("iceclient",historiqueVentevendeur.getHistoriqueventevendeur().getClientPF().getIce());
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
						
						
						
						
						if(checkSansmodepaiement.isSelected()==true)
						{
							ModePaiement="";
							numpiece="";
						}
						
						parameters.put("modepaiement", ModePaiement);
						
						if(!numpiece.equals(""))
						{
						if(numpiece!=null)
						{
							parameters.put("numcheque", numpiece);
						}
									
									
									
									
									
									
									
								}
						
						
						
						
						
						
						try {
							
						  	if(listDetailFacturePFImprimerTmp.size()!=0)
							{
						  		
						  		 if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
						  			JasperUtils.imprimerHistoriqueVenteVendeur(listDetailFacturePFImprimerTmp,parameters,true); 
						  		 }else
						  		 {
						  			JasperUtils.imprimerHistoriqueVenteVendeurSansTVA (listDetailFacturePFImprimerTmp,parameters,true); 
						  		 }
								
								
							}else
							{
								 if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
									 JasperUtils.imprimerHistoriqueVenteVendeur(listDetailFacturePFImprimer,parameters,true);
								 }else
								 {
									 JasperUtils.imprimerHistoriqueVenteVendeurSansTVA(listDetailFacturePFImprimer,parameters,true);
								 }
								
								
							}
							
						} catch (PrintException | IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					 
				
						 initialiserFacture();
						    initialiser();
						  
						    checkSansmodepaiement.setSelected(false);
						    listDetailFacturePF.clear();
						   
							InitialiseTableau();
							chargerListeFacture();
				
					
				}else
				{
					JOptionPane.showMessageDialog(null, "La liste Des Articles est vide !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}

				 
			}});
		buttonvalider.setIcon(imgImprimer);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(659, 892, 138, 24);
		add(buttonvalider);
		  

		  
		 
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(1127, 948, 112, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(1249, 948, 136, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(1020, 891, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(913, 891, 97, 26);
		add(lblTotalQuantite);
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter Historique Vente Vendeur par :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(296, 11, 1022, 35);
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
					
					detailFacturePfdao.ViderSession();
					
					if(listFacturePF.size()!=0)
					{
						checkSansmodepaiement.setSelected(false);
						
						 facturePF=listFacturePF.get(table.getSelectedRow()) ;
					
						 
						 if(facturePF.getType().equals(Constantes.TYPE_FACTURE) || facturePF.getType().equals(Constantes.TRANSFERE_BL_FACTURE))
						 {
							
							//	btnAjouter.setEnabled(false);
							//	btnModifier.setEnabled(false);
								//btnSupprimer.setEnabled(false);
								buttonvalider.setEnabled(true);
								//btn_valider.setEnabled(false);
								btninitialiser.setEnabled(false);
								//supprimer_facture.setEnabled(true);
								trouve=true;
								
							}else if(facturePF.getType().equals(Constantes.TYPE_BON_LIVRAISON))
							{
								//btnAjouter.setEnabled(true);
								//btnModifier.setEnabled(true);
								//btnSupprimer.setEnabled(true);
								buttonvalider.setEnabled(false);
								//btn_valider.setEnabled(true);
								btninitialiser.setEnabled(true);
								//supprimer_facture.setEnabled(true);
								trouve=false;
							}
						 
						 
						
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
					        	  DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
						          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
						          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
						          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
						          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
						      
					            i++;
					        }
					       txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
					        txttotalquantite.setText(sommequantite+"");
					        txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
				  			txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");

				  		///////////////////////////////////////////////////////////////////////// Traitement Reglemnt //////////////////////////////////////////////////////////////
				  			
				  			
				  		    ModePaiement = "";
				  		    numpiece="";
			                boolean existe=false;   
			                BigDecimal montantcheque=BigDecimal.ZERO;  
			                BigDecimal montantespece=BigDecimal.ZERO;   
			                BigDecimal montantvirement=BigDecimal.ZERO;  
			                BigDecimal montanttraite=BigDecimal.ZERO;   
			                BigDecimal montantversement=BigDecimal.ZERO;  
			                String numcheque="";
			                String numversement="";
			                String numtraite="";
			                String modereglement="";
			             
			                
			                BigDecimal timber = BigDecimal.ZERO;
			               
			                    
			                  
			                
			                
			                
			                           if(facturePF.getEspece()!=null) 
			                           {
			                         	  if(!facturePF.getEspece().equals(""))
			                         	  {
			                         		  montantespece=facturePF.getEspece();
			                         		  
			                         		  if(BigDecimal.ZERO.compareTo(montantespece)!=0)
			                         		  {
			                         			  
			                         			 modereglement=Constantes.MODE_REGLEMENT_ESPECE;
				                         		  
				                         		 existe=true;
				                         		 
				                         		 
				                                timber = timber.add(Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP).multiply(montantespece)) ;
				                         		 
				                                ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_ESPECE;
			                         		  }
			                         		  
			                         		  
			                         	  }
			                         	 
			                           }
			                              
			                           if(facturePF.getCheque()!=null) 
			                           {
			                         	  if(!facturePF.getCheque().equals(""))
			                         	  {
			                         		  montantcheque=facturePF.getCheque();
			                         		 if(BigDecimal.ZERO.compareTo(montantcheque)!=0)
			                         		  {
			                         			  if(facturePF.getNumCheque()!=null)
				                         		  {
				                         			  if(!facturePF.getNumCheque().equals(""))
					                         		  {
					                         			  numcheque=facturePF.getNumCheque();
					                         			 numpiece=numpiece+" Num Chéque : "+numcheque;
					                         		  } 
				                         			  
				                         		  }
				                         				  
				                         		
				                         		  
				                         		  modereglement=Constantes.MODE_REGLEMENT_CHEQUE;
				                         		 ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_CHEQUE;
				                         		
				                         		 
				                         		 existe=true;
			                         		  }
			                         		  
			                         		
			                         		 
			                         		 
			                         		 
			                         	  }
			                         	 
			                           } 
			                              
			                           if(facturePF.getVirement()!=null) 
			                           {
			                         	  if(!facturePF.getVirement().equals(""))
			                         	  {
			                         		  montantvirement=facturePF.getVirement();
			                         		  
			                         		 if(BigDecimal.ZERO.compareTo(montantvirement)!=0)
			                         		  {
			                         			  modereglement=Constantes.MODE_REGLEMENT_VIREMENT;
					                         		 ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_VIREMENT;
					                         		 existe=true;
			                         			 
			                         		  }
			                         		
			                         	  }
			                         	 
			                           } 
			                           
			                           
			                           if(facturePF.getVersement()!=null) 
			                           {
			                         	  if(!facturePF.getVersement().equals(""))
			                         	  {
			                         		  montantversement=facturePF.getVersement();
			                         		  
			                         		 if(BigDecimal.ZERO.compareTo(montantversement)!=0)
			                         		  {
			                         			 modereglement=Constantes.MODE_REGLEMENT_VERSEMENT;
				                         		 ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_VERSEMENT;
				                         		 if(facturePF.getNumVersement()!=null)
				                         		 {
				                         			  if(!facturePF.getNumVersement().equals(""))
					                         		  {
					                         			  numversement=facturePF.getNumVersement();
					                         			  
					                         			 numpiece=numpiece+" Num Versement : "+numversement;
					                         		  } 
				                         		 }
				                         		
				                         		  
				                         		 existe=true;
				                         		 
				                         		 
				                                 timber = timber.add(Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP).multiply(montantversement)) ;

				                         		  
			                         			 
			                         		  }
			                         		  
			                         		 
			                         	  }
			                         	 
			                           }
			                           
			                           if(facturePF.getTraite()!=null) 
			                           {
			                         	  if(!facturePF.getTraite().equals(""))
			                         	  {
			                         		  
			                         		  montanttraite=facturePF.getTraite();
			                         		  
			                         		 if(BigDecimal.ZERO.compareTo(montanttraite)!=0)
			                         		  {
			                         			  modereglement=Constantes.MODE_REGLEMENT_TRAITE;
					                         		 ModePaiement=ModePaiement+" - "+Constantes.MODE_REGLEMENT_TRAITE;
					                         		  if(facturePF.getNumtraite()!=null)
					                         		  {
					                         			  if(!facturePF.getNumtraite().equals(""))
						                         		  {
						                         			  numtraite =facturePF.getNumtraite();
						                         			 numpiece=numpiece+" Num Traite : "+numtraite;
						                         		  }
						                         		   
					                         		  }
					                         		
					                         		 existe=true;
					                         		  
			                         			 
			                         		  }
			                         		  
			                         	
			                         	  }
			                         	 
			                           }
			                           
			                
			            
			                
			                
			                
			                
				  			
				  			

				  		
				  			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				  			
				  			
				  			if(chckbxSansTimbre.isSelected()==true)
				  			{
				  				
				  				 txttimbre.setText(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)+"");
				  				 txtnetapayer.setText(new BigDecimal(txttotalmontantTTC.getText()).setScale(2, RoundingMode.HALF_UP)+"");
				  				
				  			}else
				  			{
				  				
				  				 txttimbre.setText(timber.setScale(2, RoundingMode.HALF_UP)+"");
				  				 txtnetapayer.setText(new BigDecimal(txttotalmontantTTC.getText()).setScale(2, RoundingMode.HALF_UP).add(timber.setScale(2, RoundingMode.HALF_UP))+"");
				  				
				  			}
				  				
				  				 
				  			
				  			 
				  			 
				  			 
				  			 
				  			 
				  		
						afficher_tableDetailFacturePF(listDetailFacturePF);
						
						if(trouve==false)
						{
							initialiser();
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
					"Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Magasin", "Montant TTC","Transfere BL en Facture"
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
	    			&& combopardepot.getSelectedItem().equals(""))
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
	    label_2.setBounds(1127, 917, 105, 26);
	    add(label_2);
	    
	    txttotalmontantTVA = new JTextField();
	    txttotalmontantTVA.setEditable(false);
	    txttotalmontantTVA.setColumns(10);
	    txttotalmontantTVA.setBounds(1249, 917, 136, 26);
	    add(txttotalmontantTVA);
	    
	    txttotalmontantHT = new JTextField();
	    txttotalmontantHT.setEditable(false);
	    txttotalmontantHT.setColumns(10);
	    txttotalmontantHT.setBounds(1249, 885, 136, 26);
	    add(txttotalmontantHT);
	    
	    JLabel label_5 = new JLabel("Total Montant HT :");
	    label_5.setBounds(1127, 885, 105, 26);
	    add(label_5);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1801, 53);
	    add(layeredPane_2);
	    
	    JLabel lblNumFacture = new JLabel("Num Facture :");
	    lblNumFacture.setBounds(226, 11, 102, 24);
	    layeredPane_2.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtparnumBL = new JTextField();
	    txtparnumBL.setBounds(328, 11, 97, 26);
	    layeredPane_2.add(txtparnumBL);
	    util.Utils.copycoller(txtparnumBL);
	    txtparnumBL.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {}
	    });
	    txtparnumBL.setColumns(10);
	    
	    JLabel lblClient_1 = new JLabel("Client :");
	    lblClient_1.setBounds(427, 11, 56, 24);
	    layeredPane_2.add(lblClient_1);
	    lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    JLabel lblDateFacture = new JLabel("Au :");
	    lblDateFacture.setBounds(871, 13, 34, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     pardateChooser = new JDateChooser();
	     pardateChooser.setBounds(915, 14, 151, 26);
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
	     combopardepot.setBounds(1138, 14, 151, 24);
	     layeredPane_2.add(combopardepot);
	     combopardepot.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {}
	     });
	     combopardepot.setSelectedIndex(-1);
	     
	     JLabel lblDepot = new JLabel("Depot  :");
	     lblDepot.setBounds(1076, 13, 97, 24);
	     layeredPane_2.add(lblDepot);
	     lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     
	     JLabel label_10 = new JLabel("Famille Article :");
	     label_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     label_10.setBounds(1299, 13, 87, 24);
	     layeredPane_2.add(label_10);
	     comboparsousfamille = new JComboBox();
		    
	     comboparsousfamille.setBounds(1640, 14, 151, 24);
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
	     comboparfamille.setBounds(1381, 14, 167, 24);
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
	     label_11.setBounds(1558, 14, 87, 24);
	     layeredPane_2.add(label_11);
	     
	      comboparclient = new JComboBox();
	     comboparclient.setSelectedIndex(-1);
	     comboparclient.setBounds(478, 12, 178, 24);
	     layeredPane_2.add(comboparclient);
	     
	     AutoCompleteDecorator.decorate(comboparclient);
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		

	     		
	     		txtparnumBL.setText("");
	     		comboparclient.setSelectedItem("");
	     		pardateChooser.setCalendar(null);
	     		combopardepot.setSelectedItem("");
	     		comboparfamille.setSelectedItem("");
	     		comboparsousfamille.setSelectedItem("");
	     	comboTypeBL.setSelectedItem("");
	     	
	     		
	     		
	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(641, 120, 106, 23);
	     add(button);
	     
	     JButton btnDeslectionnerTout = new JButton();
	     btnDeslectionnerTout.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		

	     		
	     		
	     		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(false, i, 9);
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
	    datedebut.setBounds(710, 11, 151, 26);
	    layeredPane_2.add(datedebut);
	    
	    JLabel lblDu = new JLabel("Du :");
	    lblDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblDu.setBounds(666, 13, 34, 24);
	    layeredPane_2.add(lblDu);
	    
	    JLabel label_22 = new JLabel("Type BL :");
	    label_22.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_22.setBounds(0, 10, 70, 24);
	    layeredPane_2.add(label_22);
	    
	     comboParTypeBL = new JComboBox();
	    comboParTypeBL.setSelectedIndex(-1);
	    comboParTypeBL.setBounds(67, 11, 151, 24);
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
				/*
				 * 
				 * if(table.getSelectedRow()!=-1) { int reponse =
				 * JOptionPane.showConfirmDialog(null,
				 * "Vous voulez vraiment supprimer la facture  ?", "Satisfaction",
				 * JOptionPane.YES_NO_OPTION);
				 * 
				 * if(reponse == JOptionPane.YES_OPTION )
				 * 
				 * 
				 * {
				 * 
				 * if(listFacturePF.size()!=0) {
				 * 
				 * facturePF=listFacturePF.get(table.getSelectedRow()) ;
				 * 
				 * 
				 * if(facturePF.getType().equals(Constantes.TYPE_BON_LIVRAISON) ||
				 * facturePF.getType().equals(Constantes.TYPE_FACTURE) ||
				 * facturePF.getType().equals(Constantes.TRANSFERE_BL_FACTURE)) {
				 * 
				 * transferStock=transferStockPFDAO.findByCodeTransfert(facturePF.
				 * getCodeTransfer());
				 * 
				 * listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(
				 * facturePF.getId()); for(int i=0;i<listDetailFacturePF.size();i++) { StockPF
				 * stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(
				 * i).getArticle().getId(),facturePF.getMagasin().getId(),listDetailFacturePF.
				 * get(i).getSousFamille().getId());
				 * 
				 * BigDecimal
				 * stocktmp=stockpf.getStock().add(listDetailFacturePF.get(i).getQuantite());
				 * stockpf.setStock(stocktmp);
				 * 
				 * stockpfDAO.edit(stockpf); DetailFacturePF
				 * detailfacturepf=listDetailFacturePF.get(i);
				 * detailFacturePfdao.delete(detailfacturepf.getId());
				 * 
				 * } DetailCompteClient
				 * detailcompteclient=detailCompteClientdao.findByFacture(facturePF.getId());
				 * if(detailcompteclient!=null) { CompteClient
				 * compteclient=detailcompteclient.getCompteClient();
				 * if(detailcompteclient.getMontantCredit().setScale(2).equals(BigDecimal.ZERO.
				 * setScale(2))) {
				 * compteclient.setSolde(compteclient.getSolde().subtract(detailcompteclient.
				 * getMontantDebit())); }
				 * 
				 * compteclientdao.edit(compteclient);
				 * detailCompteClientdao.delete(detailcompteclient);
				 * 
				 * } if(transferStock!=null) {
				 * listDetailTransferProduitFini=transferStock.getListDetailTransferProduitFini(
				 * ); for(int j=0;j<listDetailTransferProduitFini.size();j++) {
				 * detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(j).getId())
				 * ; } transferStockPFDAO.delete(transferStock.getId()); }
				 * 
				 * facturepfdao.delete(facturePF.getId());
				 * 
				 * JOptionPane.showMessageDialog(null,
				 * "BL Supprimer avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
				 * 
				 * chargerListeFacture();
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * }
				 * 
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * 
				 */}
	    });
	    supprimer_facture.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    supprimer_facture.setBounds(1478, 185, 73, 24);
	    supprimer_facture.setIcon(imgSupprimer);
	    add(supprimer_facture);
	    
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
				/*
				 * 
				 * Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				 * listFacturePFOrderByDate=facturepfdao.listeFacturePFEntreDeuxDatesOrderByDate
				 * (datedebut.getDate(), pardateChooser.getDate(), magasin);
				 * listFacturePFOrderByNumFacture=facturepfdao.
				 * listeFacturePFEntreDeuxDatesOrderByNumFacture (datedebut.getDate(),
				 * pardateChooser.getDate(), magasin);
				 * 
				 * for(int i=0;i<listFacturePFOrderByNumFacture.size() ; i++) {
				 * 
				 * for(int j=0;j<listFacturePFOrderByDate.size();j++) {
				 * 
				 * FacturePF facturePF=listFacturePFOrderByDate.get(j);
				 * 
				 * if(i==j) {
				 * facturePF.setNumFacture(listFacturePFOrderByNumFacture.get(i).getNumFacture()
				 * ); DetailCompteClient
				 * detailcompteclient=detailCompteClientdao.findByFacture(facturePF.getId());
				 * detailcompteclient.setDesignation("Montant sur Facture N "
				 * +listFacturePFOrderByNumFacture.get(i).getNumFacture());
				 * facturepfdao.edit(facturePF); detailCompteClientdao.edit(detailcompteclient);
				 * 
				 * }
				 * 
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * 
				 */}
	    });
	    btnOrdonnerLesFactures.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnOrdonnerLesFactures.setBounds(246, 6, 168, 40);
	    btnOrdonnerLesFactures.setVisible(false);
	    add(btnOrdonnerLesFactures);
	    
	    JButton button_1 = new JButton("Ajouter Gratuite");
	    button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * 
				 * 
				 * Articles article=mapArticle.get(comboArticle.getSelectedItem()); Magasin
				 * magasin=mapMagasin.get(combomagasin.getSelectedItem()); List<DetailFacturePF>
				 * listDetailFacturePFSansGratuité =new ArrayList<DetailFacturePF>();
				 * List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
				 * List<DetailTransferProduitFini> listDetailTransferPF =new
				 * ArrayList<DetailTransferProduitFini>();
				 * 
				 * 
				 * listDetailFacturePFSansGratuité=detailFacturePfdao.
				 * listeFactureSansGratuiteParArticle(article, magasin , txtparnumBL.getText()
				 * );
				 * 
				 * 
				 * JOptionPane.showMessageDialog(null, listDetailFacturePFSansGratuité.size());
				 * 
				 * for(int i=0;i<listDetailFacturePFSansGratuité.size();i++) {
				 * 
				 * 
				 * // if(listDetailFacturePFSansGratuité.get(i).getQuantite().compareTo(new
				 * BigDecimal(264))>=0) {
				 * 
				 * //int
				 * intvalue=(listDetailFacturePFSansGratuité.get(i).getQuantite().divide(new
				 * BigDecimal(10) , RoundingMode.FLOOR)).intValue();
				 * 
				 * 
				 * listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(
				 * listDetailFacturePFSansGratuité.get(i).getFacturePF().getId()); Boolean
				 * existe=false;
				 * 
				 * for(int j=0;j<listDetailFacturePF.size();j++) {
				 * 
				 * if(listDetailFacturePF.get(j).getArticle().getId()==
				 * listDetailFacturePFSansGratuité.get(i).getArticle().getId()) {
				 * 
				 * if(listDetailFacturePF.get(j).getPrixUnitaire().compareTo(BigDecimal.ZERO)==
				 * 0) {
				 * 
				 * listDetailFacturePF.get(j).setQuantite(new
				 * BigDecimal(txtquantite.getText()));
				 * detailFacturePfdao.edit(listDetailFacturePF.get(j));
				 * 
				 * TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(
				 * listDetailFacturePFSansGratuité.get(i).getFacturePF().getCodeTransfer());
				 * listDetailTransferPF=detailTransferStockPFdao.findByTransferStockPF(
				 * transferStockPF.getId());
				 * 
				 * for(int d=0;d<listDetailTransferPF.size();d++) {
				 * 
				 * if(listDetailTransferPF.get(d).getArticle().getId()==
				 * listDetailFacturePFSansGratuité.get(i).getArticle().getId()) {
				 * 
				 * if(listDetailTransferPF.get(d).getPrixUnitaire().compareTo(BigDecimal.ZERO)==
				 * 0) {
				 * 
				 * listDetailTransferPF.get(d).setQuantite(new
				 * BigDecimal(txtquantite.getText()));
				 * 
				 * detailTransferStockPFdao.edit(listDetailTransferPF.get(d));
				 * 
				 * 
				 * }
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * 
				 * existe=true;
				 * 
				 * 
				 * 
				 * }
				 * 
				 * 
				 * }
				 * 
				 * 
				 * }
				 * 
				 * 
				 * 
				 * 
				 * if(existe==false) {
				 * 
				 * DetailFacturePF detailFacturePF =new DetailFacturePF();
				 * 
				 * detailFacturePF.setArticle(listDetailFacturePFSansGratuité.get(i).getArticle(
				 * )); detailFacturePF.setFacturePF(listDetailFacturePFSansGratuité.get(i).
				 * getFacturePF()); detailFacturePF.setPrixUnitaire(BigDecimal.ZERO);
				 * detailFacturePF.setSousFamille(listDetailFacturePFSansGratuité.get(i).
				 * getSousFamille()); detailFacturePF.setMontantHT(BigDecimal.ZERO);
				 * detailFacturePF.setMontantTVA(BigDecimal.ZERO);
				 * detailFacturePF.setMontantTTC(BigDecimal.ZERO);
				 * detailFacturePF.setReduction(BigDecimal.ZERO);
				 * detailFacturePF.setTva(BigDecimal.ZERO); detailFacturePF.setQuantite(new
				 * BigDecimal(txtquantite.getText()));
				 * detailFacturePF.setUtilisateur(listDetailFacturePFSansGratuité.get(i).
				 * getUtilisateur());
				 * 
				 * detailFacturePfdao.add(detailFacturePF);
				 * 
				 * TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(
				 * listDetailFacturePFSansGratuité.get(i).getFacturePF().getCodeTransfer());
				 * 
				 * DetailTransferProduitFini detailTransferProduitFini=new
				 * DetailTransferProduitFini();
				 * detailTransferProduitFini.setArticle(listDetailFacturePFSansGratuité.get(i).
				 * getArticle());
				 * detailTransferProduitFini.setDateTransfer(listDetailFacturePFSansGratuité.get
				 * (i).getFacturePF().getDateFacture());
				 * detailTransferProduitFini.setPrixUnitaire(BigDecimal.ZERO);
				 * detailTransferProduitFini.setQuantite(new BigDecimal(txtquantite.getText()));
				 * detailTransferProduitFini.setSousFamille(listDetailFacturePFSansGratuité.get(
				 * i).getSousFamille());
				 * detailTransferProduitFini.setMagasinDestination(magasin);
				 * detailTransferProduitFini.setTransferStockPF(transferStockPF);
				 * detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
				 * detailTransferStockPFdao.add(detailTransferProduitFini);
				 * 
				 * 
				 * }
				 * 
				 * 
				 * //}
				 * 
				 * }
				 * 
				 * 
				 * JOptionPane.showMessageDialog(null, "La Gratuité de "+article.getLiblle()
				 * +" à été effectué");
				 * 
				 * 
				 * 
				 * 
				 * 
				 */}
	    });
	    button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button_1.setBounds(30, 27, 112, 24);
	    button_1.setVisible(false);
	    add(button_1);
	    
	    JButton btnAjouterDetailTransfer = new JButton("Ajouter Detail Transfer");
	    btnAjouterDetailTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * 
				 * listFacturePF=facturepfdao.findAll(); for(int i=0;i<listFacturePF.size();i++)
				 * { FacturePF facturePF =listFacturePF.get(i); TransferStockPF
				 * transferStockPF=transferStockPFDAO.findByCodeTransfert(facturePF.
				 * getCodeTransfer());
				 * listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(
				 * facturePF.getId());
				 * 
				 * for(int j=0;j<listDetailFacturePF.size();j++) {
				 * 
				 * DetailFacturePF detailFacturePF=listDetailFacturePF.get(j);
				 * 
				 * DetailTransferProduitFini detailTransferProduitFini =new
				 * DetailTransferProduitFini();
				 * detailTransferProduitFini.setArticle(detailFacturePF.getArticle());
				 * detailTransferProduitFini.setDateTransfer(facturePF.getDateFacture());
				 * detailTransferProduitFini.setMagasinDestination(facturePF.getMagasin());
				 * detailTransferProduitFini.setPrixUnitaire(detailFacturePF.getPrixUnitaire());
				 * detailTransferProduitFini.setQuantite(detailFacturePF.getQuantite());
				 * detailTransferProduitFini.setSousFamille(detailFacturePF.getSousFamille());
				 * detailTransferProduitFini.setTransferStockPF(transferStockPF);
				 * detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
				 * detailTransferStockPFdao.add(detailTransferProduitFini); }
				 * 
				 * 
				 * 
				 * 
				 * }
				 * 
				 * 
				 * JOptionPane.showMessageDialog(null, "Insertion Valider !!!!");
				 * 
				 * 
				 */}
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
	    
	    JLabel label_12 = new JLabel("Timbre 0,25%       :");
	    label_12.setBounds(1127, 981, 112, 26);
	    add(label_12);
	    
	    txttimbre = new JTextField();
	    txttimbre.setEditable(false);
	    txttimbre.setColumns(10);
	    txttimbre.setBounds(1249, 981, 136, 26);
	    add(txttimbre);
	    
	    txtnetapayer = new JTextField();
	    txtnetapayer.setEditable(false);
	    txtnetapayer.setColumns(10);
	    txtnetapayer.setBounds(1249, 1018, 136, 26);
	    add(txtnetapayer);
	    
	    JLabel label_13 = new JLabel("Net A Payer         :");
	    label_13.setBounds(1127, 1018, 112, 26);
	    add(label_13);
	    
	    JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
	    GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
	    gridBagLayout.rowWeights = new double[]{0.0};
	    gridBagLayout.rowHeights = new int[]{0};
	    gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
	    gridBagLayout.columnWidths = new int[]{0, 0, 0};
	    titledSeparator_1.setTitle("Informations Articles");
	    titledSeparator_1.setBounds(10, 413, 1458, 30);
	    add(titledSeparator_1);
	    
	    JLabel label = new JLabel("Famille Article :");
	    label.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    label.setBounds(20, 454, 87, 24);
	    add(label);
	    
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
	    combofamille.setBounds(102, 455, 167, 24);
	    add(combofamille);
	    
	    JLabel label_1 = new JLabel("Prix       :");
	    label_1.setBounds(10, 494, 69, 26);
	    add(label_1);
	    
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
	    txtPrix.setBounds(89, 494, 121, 26);
	    add(txtPrix);
	    
	    JLabel label_3 = new JLabel("Sous Famille :");
	    label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    label_3.setBounds(296, 453, 87, 24);
	    add(label_3);
	    
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
		        				 listArticleStockPF=detailPrixArticleDAO.findDetaileArticleBySousFamilleByMagasin(sousfamille.getId(), magasin.getId()) ;
		        				 for(int i=0;i<listArticleStockPF.size();i++)
		        				 {
		        					 
		        					 
		        					Articles article= listArticleStockPF.get(i).getArticles();
		        					
		        					if(sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_CHAARA) || sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_MKARKEB) || sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS))
		        					{
		        						
		        						if(!article.getLiblle().contains("(OFFRE)"))
		        						{
		        							
		        							comboArticle.addItem(article.getLiblle());
				        					 mapArticle.put(article.getLiblle(), article);
				        					 mapCodeArticle.put(article.getCodeArticle(), article);
		        							
		        						}
		        						
		        						
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
	    combosousfamille.setBounds(378, 454, 160, 24);
	    add(combosousfamille);
	    
	    JLabel label_4 = new JLabel("Code Article :");
	    label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    label_4.setBounds(576, 451, 82, 26);
	    add(label_4);
	    
	    txtcodearticle = new JTextField();
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
	    txtcodearticle.setBounds(644, 451, 95, 26);
	    add(txtcodearticle);
	    
	    JLabel label_6 = new JLabel("Libelle :");
	    label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    label_6.setBounds(749, 451, 48, 26);
	    add(label_6);
	    
	     comboArticle = new JComboBox();
	     comboArticle.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		


	       		
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
				 			txtcodearticle.setText(article.getCodeArticle());
				 			SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
				 			String date="01/01/"+sdf.format(dateChooserfacture.getDate())+"";
				 			String dateFin="31/12/"+sdf.format(dateChooserfacture.getDate())+"";
				 			
				 			Date dateDebut= new Date(date);
				 			Date dateFinAnne= new Date(dateFin);
				 		    StockFinale=BigDecimal.ZERO;
				 		 StockFinaleAnne=BigDecimal.ZERO;
				 		 
			 			
			 	
			 			
			 			
			 			
			 			
			 			
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
				 			
				 		
				 				
				 				
				 			
				 				
  				 			
  				 		
  				 		 
  				 		
  				 			
				 		  				 			
				 		}else
				 		{
				 			txtcodearticle.setText("");
				 			
				 		}
				 	
			 		}
			 		
			 		
		     	 	
	       	
	     		
	     		
	     		
	     		
	     		
	     	}
	     });
	    comboArticle.setBounds(796, 451, 242, 27);
	    add(comboArticle);
	    
	    JLabel label_7 = new JLabel("Quantite U :");
	    label_7.setBounds(1058, 452, 72, 26);
	    add(label_7);
	    
	    txtquantite = new JTextField();
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
	    txtquantite.setBounds(1122, 452, 112, 26);
	    add(txtquantite);
	    
	    JLabel label_8 = new JLabel("Q Packet :");
	    label_8.setBounds(1244, 452, 72, 26);
	    add(label_8);
	    
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
	    txtquantitepaquet.setBounds(1299, 452, 121, 26);
	    add(txtquantitepaquet);
	    
	    JLabel label_9 = new JLabel("Montant  :");
	    label_9.setBounds(296, 494, 67, 26);
	    add(label_9);
	    
	    txtmontant = new JTextField();
	    txtmontant.setEditable(false);
	    txtmontant.setColumns(10);
	    txtmontant.setBounds(378, 494, 160, 26);
	    add(txtmontant);
	    
	    JLabel label_14 = new JLabel("Remise :");
	    label_14.setBounds(558, 494, 57, 26);
	    add(label_14);
	    
	    txtreduction = new JTextField();
	    txtreduction.setColumns(10);
	    txtreduction.setBounds(627, 495, 101, 26);
	    add(txtreduction);
	    
	    JLabel label_15 = new JLabel("%");
	    label_15.setFont(new Font("Tahoma", Font.BOLD, 11));
	    label_15.setBounds(738, 473, 26, 26);
	    add(label_15);
	    
	     checkttc = new JCheckBox("TTC");
	    checkttc.setBounds(778, 496, 73, 24);
	    add(checkttc);
	    
	    lblOffreTherre = new JLabel("Offre Therre :");
	    lblOffreTherre.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblOffreTherre.setBounds(10, 542, 69, 26);
	    add(lblOffreTherre);
	    
	     comboBoxtherres = new JComboBox();
	    comboBoxtherres.setBounds(99, 542, 315, 27);
	    add(comboBoxtherres);
	    
	    txtquantitetherres = new JTextField();
	    txtquantitetherres.setColumns(10);
	    txtquantitetherres.setBounds(437, 545, 101, 26);
	    add(txtquantitetherres);
	    
	    lblOffreVerres = new JLabel("Offre Verres :");
	    lblOffreVerres.setBounds(553, 548, 78, 26);
	    add(lblOffreVerres);
	    
	     comboBoxverres = new JComboBox();
	    comboBoxverres.setBounds(627, 545, 315, 27);
	    add(comboBoxverres);
	    
	    txtquantiteverres = new JTextField();
	    txtquantiteverres.setColumns(10);
	    txtquantiteverres.setBounds(952, 545, 101, 26);
	    add(txtquantiteverres);
	    
	    lblOffrePromo = new JLabel("Offre Promo :");
	    lblOffrePromo.setBounds(1073, 542, 78, 26);
	    add(lblOffrePromo);
	    
	     comboBoxPromo = new JComboBox();
	    comboBoxPromo.setBounds(1147, 539, 315, 27);
	    add(comboBoxPromo);
	    
	    txtquantitepromo = new JTextField();
	    txtquantitepromo.setColumns(10);
	    txtquantitepromo.setBounds(1472, 539, 101, 26);
	    add(txtquantitepromo);
	    
	    JCheckBox checkBox_1 = new JCheckBox("Gratuit");
	    checkBox_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		

	    		
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
	    checkBox_1.setBounds(1519, 454, 77, 24);
	    add(checkBox_1);
	    
	     btnModifier = new JButton();
	     btnModifier.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {


		   		
		   		try {
		   			
		   			if(checkboxGratuits.isSelected()==true)
		   			{
		   				
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
						        		 
						        		 DetailHistoriqueVenteVendeur detailFacturePF =listDetailFacturePF.get(i);
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
						        			
								        		 
								        		
							        	 
							        	 DetailHistoriqueVenteVendeur detailFacture= listDetailFacturePF.get(tableArticle.getSelectedRow());
							        	 
								        	  detailFacture.setReduction(BigDecimal.ZERO);  
								          
							        	  
							        	 
											
							        	 
								          detailFacture.setArticle(article);
								          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
								           detailFacture.setPrixUnitaire(BigDecimal.ZERO);
								           detailFacture.setMontantHT(BigDecimal.ZERO);
									       detailFacture.setMontantTVA(BigDecimal.ZERO);
									       detailFacture.setTva(BigDecimal.ZERO);
									       detailFacture.setSousFamille(sousfamille);
									        detailFacture.setMontantTTC(BigDecimal.ZERO);
									        detailFacture.setBrutHT(BigDecimal.ZERO);
									        detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
									         
								          // detailFacture.setTypeVente(typevente);
								          
								           detailFacture.setHistoriqueventevendeur (facturePF);
								       //    detailFacture.setDateCreation(new Date());
								           
								           detailFacture.setUtilisateur(utilisateur);	
								           
								          
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
									        	 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
										          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
										          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
										          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
										          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());


									            i++;
									        }
									       txttotalmontantTTC.setText(montanttotal+"");
									        txttotalquantite.setText(sommequantite+"");
									        txttotalmontantHT.setText(montanttotalHT+"");
								  			txttotalmontantTVA.setText(montanttotalTVA+"");
								  			


								  			initialiser();
											
								  			
							        		 
								        	
						        			 
						        		 }else
						        		 {
						        			


								        		 
								        		
							        	 

							        	 DetailHistoriqueVenteVendeur detailFacture= listDetailFacturePF.get(tableArticle.getSelectedRow());
							        	 
								        	  detailFacture.setReduction(BigDecimal.ZERO);  
								          
							        	  
							        	 
											
											
							        	 
								          detailFacture.setArticle(article);
								          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
								           detailFacture.setPrixUnitaire(BigDecimal.ZERO);
								           detailFacture.setMontantHT(BigDecimal.ZERO);
									       detailFacture.setMontantTVA(BigDecimal.ZERO);
									       detailFacture.setTva(BigDecimal.ZERO);
									       detailFacture.setSousFamille(sousfamille);
									        detailFacture.setMontantTTC(BigDecimal.ZERO);
									        detailFacture.setBrutHT(BigDecimal.ZERO);
									        detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
									         
								          // detailFacture.setTypeVente(typevente);
								          
								           detailFacture.setHistoriqueventevendeur (facturePF);
								       //    detailFacture.setDateCreation(new Date());
								           
								           detailFacture.setUtilisateur(utilisateur);	
								           
								         
								         
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
									        	 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
										          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
										          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
										          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
										          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());


									            i++;
									        }
									       txttotalmontantTTC.setText(montanttotal+"");
									        txttotalquantite.setText(sommequantite+"");
									        txttotalmontantHT.setText(montanttotalHT+"");
								  			txttotalmontantTVA.setText(montanttotalTVA+"");
								  			


								  			initialiser();
											
								  			
							        		 
								        	 
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
						        		 
						        		 DetailHistoriqueVenteVendeur detailFacturePF =listDetailFacturePF.get(i);
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


		  				 								
		  				 								


		  				 								if (listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
		  				 								{
		  				 									
		  				 								
		  				 						  		 //////////////////////////////////////////// Article promo /////////////////////////////////
											        		 
											        		 
											        		 
											        		 
											        		 
											        		 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
											        		 {
											        			 
											        			 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle()+"_PFO"))
												        		 {
											        				 articlepromoexiste=true;
												        			
												        			
											        				 
													        		
													        		 
													        		 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
													        	
													        	
													        	 
												        			 
												        		 }
											        			 
											        		 }
		  				 									
		  				 									
		  				 									
		  				 									
		  				 									
		  				 									
		  				 									
		  				 								}
		  				 								
		  				 								
		  				 								
		  				 							}
		  				 						  
									        		
		  				 						}
		  				 						
		  				 						
		  				 						if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+2)
		  				 						{
		  				 							if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2)!=null)
		  				 							{
		  				 								
		  				 								
		  				 								
		  				 								
										        		 
                                            if(!listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
                                            {
                                            	

								        		 //////////////////////////////////////////// Article promo /////////////////////////////////
								        		 
								        		 
								        		 
								        		 
								        		 
								        		 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2)!=null)
								        		 {
								        			 
								        			 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle()+"_PFO"))
									        		 {
								        				 articlepromoexiste=true;
									        			
									        			
								        				 
										        		
										        		 
										        		 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());


										        	
										        	 
									        			 
									        		 }
								        			 
								        		 }
	
                                            }
										        		 
										        		 
			  				 							
		  				 								
		  				 								
		  				 							}
		  				 							
		  				 			
		  				 							
		  				 						}
		  				 						
		  				 						
		  				 						
		  				 						if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+3)
		  				 						{
		  				 							if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3)!=null)
		  				 							{
		  				 								
		  				 						
										        		 
										        if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
										        {
										        	
										        	
										     		 //////////////////////////////////////////// Article promo /////////////////////////////////
									        		 
									        		 
									        		 
									        		 if(listStockPF.get(tableArticle.getSelectedRow()+3)!=null)
									        		 {
									        			 
									        			 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3).getArticle().getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle()+"_PFO"))
										        		 {
									        				 articlepromoexiste=true;
										        			
										        			
									        				 
											        		
											        		 
											        		 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
											        	
											        	
											        	 
										        			 
										        		 }
									        			 
									        		 }
										        	
										        	
										        	
										        }
										        		 
										        		 
										   
			  				 							
		  				 								
		  				 								
		  				 							}
		  				 							
		  				 							
		  				 					
		  				 							
		  				 						}
		  				 						 
						        	 }
						        	 
						        	 //////////////////////////////////////////////////////////////////////////////////////////////////////////////
						        	 
						        	 
						        	
									
								
									 
						        	
						        		 
						        		 if(!txtquantitepaquet.getText().equals(""))
						        		 {
						        			QuantitePaquet=new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement()) ;
						        		 }
						        		 if(!txtquantite.getText().equals(""))
						        		 {
						        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
						        		 }
						        		 
						            	
							        		 
					        				 
							        		
								        	
								        		 


								        	
								        	 
						        	 
						        	 DetailHistoriqueVenteVendeur detailFacture= listDetailFacturePF.get(tableArticle.getSelectedRow());
						        	 
						        	 if(offfrepromo==true)
						        	 {
						        		 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
										 listDetailFacturePF.remove(tableArticle.getSelectedRow());
										
										 
										 	sousfamille=	 mapsousfamille.get(combosousfamille.getSelectedItem());
										    article=mapArticle.get(comboArticle.getSelectedItem());
										 
										 DetailHistoriqueVenteVendeur detailFactureTmp= new DetailHistoriqueVenteVendeur();
										 
										 
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
								           
								        
									       detailFacture.setSousFamille(sousfamille);
									       if(!txtreduction.getText().equals(""))
									          {
									    	   if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
									    		   
									    		   detailFacture.setRemiseCommerciale((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
									    		   detailFacture.setMontantHT((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
									    		   detailFacture.setMontantTVA(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA));
									    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
									    		   detailFacture.setMontantTTC(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).add(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA)) );  

									    	   }else
									    	   {
									    		   detailFacture.setRemiseCommerciale((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
									    		   detailFacture.setMontantHT((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
									    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
									    		   detailFacture.setTva(BigDecimal.ZERO);
                                                   detailFacture.setMontantTTC((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

									    	   }
									          }else
									          {
									        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
									        		  
										    		   detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
										    		   detailFacture.setMontantHT((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
										    		   detailFacture.setMontantTVA(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP));
										    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
										    		   detailFacture.setMontantTTC(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).add(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP)) );

									        	  }else
									        	  {
									        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
									        		  detailFacture.setMontantHT((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
										    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
										    		   detailFacture.setTva(BigDecimal.ZERO);
										    		   detailFacture.setMontantTTC((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));


									        	  }
									          }  
						        	  
								     }else
								     {

						        		  detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
						        		  
								           detailFacture.setBrutHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
								         
								           
								         
								           
								           
									       detailFacture.setSousFamille(sousfamille);
									       if(!txtreduction.getText().equals(""))
									          {
									    	   if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
									    		   
									    		   detailFacture.setRemiseCommerciale((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
									    		   detailFacture.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
									    		   detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP) );
									    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
									    		   detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).add(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP) ));  

									    	   }else
									    	   {
									    		   detailFacture.setRemiseCommerciale((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));

									    		   detailFacture.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
									    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
									    		   detailFacture.setTva(BigDecimal.ZERO);
									    		   detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

									    	   }
									          }else
									          {
									        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
									        		  
									        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
									        		  detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
									        		  detailFacture.setMontantTVA((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP));
									        		  detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
									        		  detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).add((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP)) );

									        	  }else
									        	  {
									        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
									        		  detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
									        		  detailFacture.setMontantTVA(BigDecimal.ZERO);
									        		  detailFacture.setTva(BigDecimal.ZERO);
									        		  detailFacture.setMontantTTC(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));

									        	  }
									          }  
						        	  
								     }
								        
								          // detailFacture.setTypeVente(typevente);
								          
								     detailFactureTmp.setHistoriqueventevendeur(facturePF);
								       //    detailFacture.setDateCreation(new Date());
								           
								     detailFactureTmp.setUtilisateur(utilisateur);	
								    
										
										 listDetailFacturePF.add (detailFactureTmp);
										 detailFacturePfdao.add(detailFactureTmp);
										 
										 
										
									        	
									          
										
											
										
										 
						        		
						        		 
						        		 
						        		 
						        		 
						        	 }else
						        	 {
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
						        		  
								           detailFacture.setBrutHT ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
								           
								        
									       detailFacture.setSousFamille(sousfamille);
									       if(!txtreduction.getText().equals(""))
									          {
									    	   if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
									    		   
									    		   detailFacture.setRemiseCommerciale((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
									    		   detailFacture.setMontantHT((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
									    		   detailFacture.setMontantTVA(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA));
									    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
									    		   detailFacture.setMontantTTC(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).add(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA)) );  

									    	   }else
									    	   {
									    		   detailFacture.setRemiseCommerciale((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
									    		   detailFacture.setMontantHT((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
									    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
									    		   detailFacture.setTva(BigDecimal.ZERO);
                                                   detailFacture.setMontantTTC((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).subtract((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

									    	   }
									          }else
									          {
									        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
									        		  
										    		   detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
										    		   detailFacture.setMontantHT((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
										    		   detailFacture.setMontantTVA(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP));
										    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
										    		   detailFacture.setMontantTTC(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).add(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP)) );

									        	  }else
									        	  {
									        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
									        		  detailFacture.setMontantHT((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
										    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
										    		   detailFacture.setTva(BigDecimal.ZERO);
										    		   detailFacture.setMontantTTC((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));


									        	  }
									          }  
						        	  }else
								     {

						        		  detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
						        		  
								           detailFacture.setBrutHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
								         
								           
								         
								           
								           
									       detailFacture.setSousFamille(sousfamille);
									       if(!txtreduction.getText().equals(""))
									          {
									    	   if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
									    		   
									    		   detailFacture.setRemiseCommerciale((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
									    		   detailFacture.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
									    		   detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP) );
									    		   detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
									    		   detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).add(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP) ));  

									    	   }else
									    	   {
									    		   detailFacture.setRemiseCommerciale((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));

									    		   detailFacture.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
									    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
									    		   detailFacture.setTva(BigDecimal.ZERO);
									    		   detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

									    	   }
									          }else
									          {
									        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
									        		  
									        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
									        		  detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
									        		  detailFacture.setMontantTVA((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP));
									        		  detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
									        		  detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).add((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP)) );

									        	  }else
									        	  {
									        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
									        		  detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
									        		  detailFacture.setMontantTVA(BigDecimal.ZERO);
									        		  detailFacture.setTva(BigDecimal.ZERO);
									        		  detailFacture.setMontantTTC(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));

									        	  }
									          }  
						        	  
								     }
								        
								          // detailFacture.setTypeVente(typevente);
								          
								           detailFacture.setHistoriqueventevendeur (facturePF);
								       //    detailFacture.setDateCreation(new Date());
								           
								           detailFacture.setUtilisateur(utilisateur);	
								           
								          
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
													
													
													 offre1=true;
												 }
												 
												 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
												 {
													 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
													 listDetailFacturePF.remove(tableArticle.getSelectedRow());
													
													 offre1=true;
												 }
												 
												 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
												 {
													 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
													 listDetailFacturePF.remove(tableArticle.getSelectedRow());
													
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
														
														 offre2=true;
													 }
													 
													 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
													 {
														 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
														 listDetailFacturePF.remove(tableArticle.getSelectedRow());
														
														 offre2=true;
													 }
													 
													 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
													 {
														 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
														 listDetailFacturePF.remove(tableArticle.getSelectedRow());
														
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
														
														
													 }
													 
													 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
													 {
														 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
														 listDetailFacturePF.remove(tableArticle.getSelectedRow());
														
														 
													 }
													 
													 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
													 {
														 detailFacturePfdao.delete(listDetailFacturePF.get(tableArticle.getSelectedRow()).getId());
														 listDetailFacturePF.remove(tableArticle.getSelectedRow());
														
														
													 }
													 
													 
													 
													 
												 }
												 
												 
											 } 
											 
											 
											 
											 
										 }
										 
										 DetailHistoriqueVenteVendeur facturePF=listDetailFacturePF.get(tableArticle.getSelectedRow());
											
										 if(!txtquantitetherres.getText().equals(""))
										 {
											 
									         SousFamilleArticlePF	sousfamilleoffretherre=	 mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_THERRES);
											 Articles articletherre=mapArticleOffre.get(comboBoxtherres.getSelectedItem());
											 
											 DetailHistoriqueVenteVendeur detailFacturetherres= new DetailHistoriqueVenteVendeur();
											 detailFacturetherres.setQuantite(new BigDecimal(txtquantitetherres.getText()));
											 detailFacturetherres.setArticle(articletherre);
											 detailFacturetherres.setPrixUnitaire(BigDecimal.ZERO);
											 detailFacturetherres.setMontantHT(BigDecimal.ZERO);
											 detailFacturetherres.setMontantTVA(BigDecimal.ZERO);
											 detailFacturetherres.setMontantTTC(BigDecimal.ZERO);
											 detailFacturetherres.setReduction(BigDecimal.ZERO);
											 detailFacturetherres.setTva(BigDecimal.ZERO);	
											 detailFacturetherres.setBrutHT(BigDecimal.ZERO);
											 detailFacturetherres.setRemiseCommerciale(BigDecimal.ZERO);
											 detailFacturetherres.setSousFamille(sousfamilleoffretherre);
											 detailFacturetherres.setHistoriqueventevendeur(facturePF.getHistoriqueventevendeur());
											
											 listDetailFacturePF.add (detailFacturetherres);
											 detailFacturePfdao.add(detailFacturetherres);
											 
											
											 
											
											 
											 
										 }
										 
											
										 if(!txtquantiteverres.getText().equals(""))
										 {
											 Articles articleverres=mapArticleOffre.get(comboBoxverres.getSelectedItem());
											 SousFamilleArticlePF	sousfamilleoffreVerre=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_VERRES);
											 DetailHistoriqueVenteVendeur detailFactureVerres= new DetailHistoriqueVenteVendeur();
											 detailFactureVerres.setQuantite(new BigDecimal(txtquantiteverres.getText()));
											 detailFactureVerres.setArticle(articleverres);
											 detailFactureVerres.setPrixUnitaire(BigDecimal.ZERO);
											 detailFactureVerres.setMontantHT(BigDecimal.ZERO);
											 detailFactureVerres.setMontantTVA(BigDecimal.ZERO);
											 detailFactureVerres.setMontantTTC(BigDecimal.ZERO);
											 detailFactureVerres.setReduction(BigDecimal.ZERO);
											 detailFactureVerres.setBrutHT(BigDecimal.ZERO);
											 detailFactureVerres.setRemiseCommerciale(BigDecimal.ZERO);
											 detailFactureVerres.setTva(BigDecimal.ZERO);																
											 detailFactureVerres.setSousFamille(sousfamilleoffreVerre);
											 detailFactureVerres.setHistoriqueventevendeur (facturePF.getHistoriqueventevendeur());
											
											 listDetailFacturePF.add (detailFactureVerres);
											 detailFacturePfdao.add(detailFactureVerres);
											 
											 
										 }			
													
								
										 if(!txtquantitepromo.getText().equals(""))
										 {
											 
											 
											 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
											 
											 DetailHistoriqueVenteVendeur detailFacturepromo= new DetailHistoriqueVenteVendeur();
											 detailFacturepromo.setQuantite(new BigDecimal(txtquantitepromo.getText()));
											 detailFacturepromo.setArticle(articlespromo);
											 detailFacturepromo.setPrixUnitaire(BigDecimal.ZERO);
											 detailFacturepromo.setMontantHT(BigDecimal.ZERO);
											 detailFacturepromo.setMontantTVA(BigDecimal.ZERO);
											 detailFacturepromo.setMontantTTC(BigDecimal.ZERO);
											 detailFacturepromo.setReduction(BigDecimal.ZERO);
											 detailFacturepromo.setTva(BigDecimal.ZERO);	
											 detailFacturepromo.setRemiseCommerciale(BigDecimal.ZERO);
											 detailFacturepromo.setBrutHT(BigDecimal.ZERO);
											 detailFacturepromo.setSousFamille(sousfamille);
											 detailFacturepromo.setHistoriqueventevendeur(facturePF.getHistoriqueventevendeur());
											
											 listDetailFacturePF.add (detailFacturepromo);
											 detailFacturePfdao.add(detailFacturepromo);
											
											
											
											 
							
											 
										 }	
										 
						        	 }
							          
							          
							          afficher_tableDetailFacturePF(listDetailFacturePF);
							          int i=0;
								        BigDecimal montanttotal=BigDecimal.ZERO;
								        BigDecimal sommequantite=BigDecimal.ZERO;
								        BigDecimal montanttotalHT=BigDecimal.ZERO;
								        BigDecimal montanttotalTVA=BigDecimal.ZERO;
								        
								      
								        while(i<listDetailFacturePF.size())
								        {
								        	 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
									          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
									          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
									          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
									          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
									     
								            i++;
								        }
								       txttotalmontantTTC.setText(montanttotal+"");
								        txttotalquantite.setText(sommequantite+"");
								        txttotalmontantHT.setText(montanttotalHT+"");
							  			txttotalmontantTVA.setText(montanttotalTVA+"");
							  			


							  			
							  			initialiser();
										
							  			
						        		 
							        	
						        		 
						        	

						        	 
						        	
										
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
	    btnModifier.setBounds(1395, 690, 73, 24);
	    btnModifier.setIcon(imgModifierr);
	    add(btnModifier);
	    
	     btnSupprimer = new JButton();
	     btnSupprimer.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		

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
							 
							 
							 
					     
							 // Verifier le stock offre pour la gratuité du the
						
			        		
			        		 
							
							
					      
					        DetailHistoriqueVenteVendeur detailfacturepf=listDetailFacturePF.get(tableArticle.getSelectedRow());
					      
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
									        		 
						        	
						        	 
						        	 DetailHistoriqueVenteVendeur detailfacturepfTherres=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
						        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
						        	 detailFacturePfdao.delete(detailfacturepfTherres.getId());
						        	
								        			
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
									        		 
			  	            
			  	                
			  	              DetailHistoriqueVenteVendeur detailfacturepfVerres=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
			 	        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
			 	        	 detailFacturePfdao.delete(detailfacturepfVerres.getId());
			 	        	
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
									        		
					        			
					     	                
					     	                
					     	               DetailHistoriqueVenteVendeur detailfacturepfPromo=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
					     		        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
					     		        	 detailFacturePfdao.delete(detailfacturepfPromo.getId());
					     		        	
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
       								        		 
       				        		


       					        	 DetailHistoriqueVenteVendeur detailfacturepfTherres=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
						        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
						        	 detailFacturePfdao.delete(detailfacturepfTherres.getId());
						        	
       								 
       								 
       								 
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
   								        		 
   				         
   		  	                
   		  	                
   		  	           DetailHistoriqueVenteVendeur detailfacturepfVerres=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
		 	        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
		 	        	 detailFacturePfdao.delete(detailfacturepfVerres.getId());
		 	        	
   				                
   				                
   				                
   				                
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
   								        		
   				        			

   				     	            DetailHistoriqueVenteVendeur detailfacturepfPromo=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
			     		        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
			     		        	 detailFacturePfdao.delete(detailfacturepfPromo.getId());
			     		        	
   				     	                
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
						        		 
		        		


			        	 DetailHistoriqueVenteVendeur detailfacturepfTherres=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
			        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
			        	 detailFacturePfdao.delete(detailfacturepfTherres.getId());
			        	
					        			
					        			
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
							        		 
			              
	  	                
	  	              DetailHistoriqueVenteVendeur detailfacturepfVerres=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
		 	        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
		 	        	 detailFacturePfdao.delete(detailfacturepfVerres.getId());
		 	        	
			                
			           	
			                
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
							        		
			        			
			     	                
			     	               DetailHistoriqueVenteVendeur detailfacturepfPromo=listDetailFacturePF.get(tableArticle.getSelectedRow()+1);
			     		        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
			     		        	 detailFacturePfdao.delete(detailfacturepfPromo.getId());
			     		        	
			     	          	
			     	                
						        		} 
										
			        			 }
								 
		        			 }
				        		 
					

				        	
		        			 
		        		 }
				    	 
				    	 
				     }

				     				
				        	   
				                
				        	 }
				        	 
					        
					        listDetailFacturePF.remove(tableArticle.getSelectedRow());
					  		detailFacturePfdao.delete(detailfacturepf.getId());
					  		
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
						        	 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
							          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
							          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());


						            i++;
						        }
						       txttotalmontantTTC.setText(montanttotal+"");
						       txttotalquantite.setText(sommequantite+"");
					  			txttotalmontantHT.setText(montanttotalHT+"");
					  			txttotalmontantTVA.setText(montanttotalTVA+"");


					  			
					  			JOptionPane.showMessageDialog(null, "Article supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					  			initialiser();
						}
		  			
		  		}
		  		
		  	
	     		
	     		
	     		
	     		
	     		
	     	}
	     });
	    btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnSupprimer.setBounds(1395, 720, 73, 24);
	    btnSupprimer.setIcon(imgSupprimer);
	    add(btnSupprimer);
	    
	     btnAjouter = new JButton("Ajouter");
	     btnAjouter.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		

				
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
				        	 DetailHistoriqueVenteVendeur detailFacturePF =listDetailFacturePF.get(i);
				        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()) && detailFacturePF.getPrixUnitaire().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))
				        			 
				        		&& 	 detailFacturePF.getMontantHT().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && detailFacturePF.getMontantTTC().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && detailFacturePF.getMontantTVA().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))
				        			 
				        			 )
				        	 {
				        		 trouve=true;
				        	 }
				         }
				         
				         if(trouve==false)
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
				        		
				            	 
					        	 
					        	 
					        	 DetailHistoriqueVenteVendeur detailFacture=new DetailHistoriqueVenteVendeur();
					        	 
						        detailFacture.setReduction(BigDecimal.ZERO);  
						         
					        	 
						          detailFacture.setArticle(article);
						          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
						           detailFacture.setPrixUnitaire(BigDecimal.ZERO);
						           detailFacture.setMontantHT(BigDecimal.ZERO);
							        detailFacture.setMontantTVA(BigDecimal.ZERO);
							        detailFacture.setMontantTTC(BigDecimal.ZERO);
							        detailFacture.setSousFamille(sousfamille);
							        detailFacture.setMontantTTC(BigDecimal.ZERO);
							        detailFacture.setTva(BigDecimal.ZERO);
							        detailFacture.setBrutHT(BigDecimal.ZERO);
							        detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
							         
							        
							        //detailFacture.setTypeVente(typevente);
						          
						           detailFacture.setHistoriqueventevendeur(facturePF);
						       //    detailFacture.setDateCreation(new Date());
						           
						           detailFacture.setUtilisateur(utilisateur);
						          listDetailFacturePF.add(detailFacture);
						         detailFacturePfdao.add(detailFacture);
						         
						          
						           
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
							        	 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
								          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
								          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
								          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
								          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());


							            i++;
							        }
							       txttotalmontantTTC.setText(montanttotal+"");
							        txttotalquantite.setText(sommequantite+"");
							        txttotalmontantHT.setText(montanttotalHT+"");
						  			txttotalmontantTVA.setText(montanttotalTVA+"");
						  			


						  			


						  			
							        initialiser();
									
						      
				        			 
				        			 
				        		 }else
				        		 {
				        			 
				        			 


					        	 
					        	 
					        	 DetailHistoriqueVenteVendeur detailFacture=new DetailHistoriqueVenteVendeur();
					        	 
						        	  detailFacture.setReduction(BigDecimal.ZERO);  
						         
					        	 
						          detailFacture.setArticle(article);
						          detailFacture.setQuantite(QuantiteUnit.add(QuantitePaquet));
						           detailFacture.setPrixUnitaire(BigDecimal.ZERO);
						           detailFacture.setMontantHT(BigDecimal.ZERO);
							        detailFacture.setMontantTVA(BigDecimal.ZERO);
							        detailFacture.setMontantTTC(BigDecimal.ZERO);
							        detailFacture.setSousFamille(sousfamille);
							        detailFacture.setMontantTTC(BigDecimal.ZERO);
							        detailFacture.setTva(BigDecimal.ZERO);
							        detailFacture.setBrutHT(BigDecimal.ZERO);
							        detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
							        
							        //detailFacture.setTypeVente(typevente);
						          
						           detailFacture.setHistoriqueventevendeur(facturePF);
						       //    detailFacture.setDateCreation(new Date());
						           
						           detailFacture.setUtilisateur(utilisateur);
						          listDetailFacturePF.add(detailFacture);
						         detailFacturePfdao.add(detailFacture);
						         

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
							        	 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
								          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
								          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
								          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
								          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
								      
							            i++;
							        }
							       txttotalmontantTTC.setText(montanttotal+"");
							        txttotalquantite.setText(sommequantite+"");
							        txttotalmontantHT.setText(montanttotalHT+"");
						  			txttotalmontantTVA.setText(montanttotalTVA+"");
						  			
						  		
						  			
					  			
							        initialiser();
									
						      
				        			 
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
				        	 DetailHistoriqueVenteVendeur detailFacturePF =listDetailFacturePF.get(i);
				        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()) && !detailFacturePF.getPrixUnitaire().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))
				        			 
				        		&& 	 !detailFacturePF.getMontantHT().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !detailFacturePF.getMontantTTC().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !detailFacturePF.getMontantTVA().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))
				        			 )
				        	 {
				        		 trouve=true;
				        	 }
				         }
				         
				         if(trouve==false)
				         {
				        	
				        		 if(!txtquantitepaquet.getText().equals(""))
				        		 {
				        			QuantitePaquet=new BigDecimal(txtquantitepaquet.getText()).multiply(article.getConditionnement()) ;
				        		 }
				        		 if(!txtquantite.getText().equals(""))
				        		 {
				        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
				        		 }
				        		 
				        	 
					        		 
					        		 
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
					  	  				 			
				  				 						
					  	  				 			 
			  				 						}
			  				 						
			  				 					}
			  				 					 
			  				 					 
			  				 					 //////////////////////////////////////////////////////////////////////////////////////////////////////
			  				 						
									        		 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
									        		 if(articlespromo!=null)
									        		 {
									        			if(!txtquantitepromo.getText().equals("")) 
									        			{
									        				
									        				
									        				if(new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)<0 )
									        				{
									        					JOptionPane.showMessageDialog(null, "la quantite d'Article Promo doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						  										return;
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
						  	  				 		
					  				 						
						  	  				 			 
				  				 						}
				  				 						
				  				 					}
				  				 					 
				  				 					 
				  				 					 //////////////////////////////////////////////////////////////////////////////////////////////////////
				  				 						
										        		 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
										        		 if(articlespromo!=null)
										        		 {
										        			if(!txtquantitepromo.getText().equals("")) 
										        			{
										        				
										        				
										        				if(new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)<0 )
										        				{
										        					JOptionPane.showMessageDialog(null, "la quantite d'Article Promo doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							  										return;
										        				}
										        				
										        			
										        				
										        				
										        				
										        			}
										        			 
										        			 
										        		 }
										        		 
										        		 
										        		 
										        		 promo=true;
										        		 
										        	 
									        		 
					  				 				}
					  				 					
									        		 
			  	  				 			    	
			  	  				 			  
			  				 			}
					        		 
					        		 
					        		 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					        		 
					        		 
					        
					        		 
				        	 
			        				 
						        	
						        
				        	 
				        	 
				        	 
				        	 
				        	 DetailHistoriqueVenteVendeur detailFacture=new DetailHistoriqueVenteVendeur();
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
						          detailFacture.setBrutHT(prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
						      
						          if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
						        	  detailFacture.setMontantTVA((((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
							          detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
						          }else
						          {
						        	  detailFacture.setMontantTVA((BigDecimal.ZERO).setScale(6, RoundingMode.HALF_UP));
							          detailFacture.setTva(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP));
						          }
						         
						          detailFacture.setSousFamille(sousfamille);
						          if(!txtreduction.getText().equals(""))
						          {
						        	  
						        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
						        		  
						        		  detailFacture.setRemiseCommerciale((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
						        		  detailFacture.setMontantHT((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
						        		  detailFacture.setMontantTVA((((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
								          detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
								          
							        	  detailFacture.setMontantTTC(((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).add((((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP)));  

						        	  }else
						        	  {
						        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
						        		  detailFacture.setMontantHT((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
						        		  detailFacture.setMontantTVA((BigDecimal.ZERO).setScale(6, RoundingMode.HALF_UP));
						        		  detailFacture.setTva(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP));
						        		 
						        		  detailFacture.setMontantTTC((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

						        	  }
						          }else
						          {
						        	  
						        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
						        		  
						        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
						        		  detailFacture.setMontantHT(prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
						        		  detailFacture.setMontantTVA(((prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
						        		  detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
						        		  detailFacture.setMontantTTC((((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).add(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));

						        	  }else
						        	  {
						        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
						        		  detailFacture.setMontantHT(prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
						        		  detailFacture.setMontantTVA((BigDecimal.ZERO).setScale(6, RoundingMode.HALF_UP));
						        		
						        		  detailFacture.setTva(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP));
							        	  detailFacture.setMontantTTC((((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).add(((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP));

						        	  }
						          }
						 
						   
					     
					     }else
					     {


						      detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
						      detailFacture.setBrutHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));

					         
					          if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
					              detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
						          detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
					          }else
					          {
					              detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
						          detailFacture.setTva(BigDecimal.ZERO);
					          }
					    
					          detailFacture.setSousFamille(sousfamille);
					          if(!txtreduction.getText().equals(""))
					          {
					        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
					        		  
					        		  detailFacture.setRemiseCommerciale((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
					        		  detailFacture.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
					        		  detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA));
					        		  detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
					        		  detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).add(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)))).multiply(Constantes.TVA)));  

					        	  }else
					        	  {
					        		  detailFacture.setRemiseCommerciale((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
					        		  detailFacture.setMontantHT((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
					        		  detailFacture.setMontantTVA(BigDecimal.ZERO);
							          detailFacture.setTva(BigDecimal.ZERO);
					        		  
					        		  detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

					        	  }
					          }else
					          {
					        	  if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) {
					        		  
					        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
					        		  detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
					        		  detailFacture.setMontantTVA((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP));
					        		  detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
					        		  detailFacture.setMontantTTC((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).add((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA).setScale(6, RoundingMode.HALF_UP)) );

					        	
					        	  
					        	  }else
					        	  {
					        		  
					        		  detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
					        		  detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
					        		  detailFacture.setMontantTVA(BigDecimal.ZERO);
					        		  detailFacture.setTva(BigDecimal.ZERO);
						        	  detailFacture.setMontantTTC(new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP).setScale(6, RoundingMode.HALF_UP));

					        	  }
					          }
					
					   
					     
					     
					     }
					          
					          
						        //detailFacture.setTypeVente(typevente);
					          
					           detailFacture.setHistoriqueventevendeur(facturePF);
					       //    detailFacture.setDateCreation(new Date());
					           
					           detailFacture.setUtilisateur(utilisateur);
					           listDetailFacturePF.add(detailFacture);
					         detailFacturePfdao.add(detailFacture);
					         
					          
						          if(promo==true)
						          {
						        	  
						        	   ////////////////////////////////////////////////////// Therres offre /////////////////////////////////////////////
					        		 
						        	  if(!txtquantitetherres.getText().equals(""))
						        	  {
						        		  
						        		  DetailHistoriqueVenteVendeur detailFactureTherres=new DetailHistoriqueVenteVendeur();
							        		 
							        		 detailFactureTherres.setArticle(articlesOffreTherres);
							        		 detailFactureTherres.setQuantite(new BigDecimal(txtquantitetherres.getText()));
							        		 detailFactureTherres.setPrixUnitaire(BigDecimal.ZERO);
							        		 detailFactureTherres.setMontantHT(BigDecimal.ZERO);
							        		 detailFactureTherres.setMontantTVA(BigDecimal.ZERO);
							        		 detailFactureTherres.setTva(BigDecimal.ZERO);
							        		 detailFactureTherres.setSousFamille(sousFamilleArticlePFOffreTherres);
							        		 detailFactureTherres.setReduction(BigDecimal.ZERO);
							        		 detailFactureTherres.setMontantTTC(BigDecimal.ZERO);  
							        		 detailFactureTherres.setBrutHT(BigDecimal.ZERO);
							        		 detailFactureTherres.setRemiseCommerciale(BigDecimal.ZERO);
										         
										         //  detailFacture.setTypeVente(typevente);
										        
							        		 detailFactureTherres.setHistoriqueventevendeur(facturePF);
										       //  detailFacture.setDateCreation(new Date());
										           
							        		 detailFactureTherres.setUtilisateur(utilisateur);
										         
										           listDetailFacturePF.add(detailFactureTherres);
										        detailFacturePfdao.add(detailFactureTherres);
										                  
										           
						        	  }
						        	  
						        	  
					        		
								           
								           
						          
								           ////////////////////////////////////////////////////// Verres offre /////////////////////////////////////////////
						        	  if(!txtquantiteverres.getText().equals(""))
						        	  {
						        		 

								           DetailHistoriqueVenteVendeur detailFactureVerres=new DetailHistoriqueVenteVendeur();
							        		 
							        		 detailFactureVerres.setArticle(articlesOffreVerres);
							        		 detailFactureVerres.setQuantite(new BigDecimal(txtquantiteverres.getText()));
							        		 detailFactureVerres.setPrixUnitaire(BigDecimal.ZERO);
							        		 detailFactureVerres.setMontantHT(BigDecimal.ZERO);
							        		 detailFactureVerres.setMontantTVA(BigDecimal.ZERO);
							        		 detailFactureVerres.setTva(BigDecimal.ZERO);
							        		 detailFactureVerres.setSousFamille(sousFamilleArticlePFOffreVerres);
							        		 detailFactureVerres.setReduction(BigDecimal.ZERO);
							        		 detailFactureVerres.setMontantTTC(BigDecimal.ZERO);  
							        		 detailFactureVerres.setBrutHT(BigDecimal.ZERO);
							        		 detailFactureVerres.setRemiseCommerciale(BigDecimal.ZERO);
										         
										         //  detailFacture.setTypeVente(typevente);
										        
							        		 detailFactureVerres.setHistoriqueventevendeur(facturePF);
										       //  detailFacture.setDateCreation(new Date());
										           
							        		 detailFactureVerres.setUtilisateur(utilisateur);
										         
										           listDetailFacturePF.add(detailFactureVerres);
										           detailFacturePfdao.add(detailFactureVerres);
										          
						        		  
						        	  }
						        	  
						        	  
										           
									//////////////////////////////////////////////// Article Promo ///////////////////////////////////////////////////////////	     
										           
										           Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
									        		 if(articlespromo!=null)
									        		 {
									        			if(!txtquantitepromo.getText().equals("")) 
									        			{
									        				
									        				if(new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)!=0 && new BigDecimal(txtquantitepromo.getText()).compareTo(BigDecimal.ZERO)>0)
									        				{
									        					
									        					
									        					 DetailHistoriqueVenteVendeur detailFactureArticlePromo=new DetailHistoriqueVenteVendeur();
												        		 
									        					 detailFactureArticlePromo.setArticle(articlespromo);
									        					 detailFactureArticlePromo.setQuantite(new BigDecimal(txtquantitepromo.getText()));
									        					 detailFactureArticlePromo.setPrixUnitaire(BigDecimal.ZERO);
									        					 detailFactureArticlePromo.setMontantHT(BigDecimal.ZERO);
									        					 detailFactureArticlePromo.setMontantTVA(BigDecimal.ZERO);
									        					 detailFactureArticlePromo.setTva(BigDecimal.ZERO);
									        					 detailFactureArticlePromo.setSousFamille(sousfamille);
									        					 detailFactureArticlePromo.setReduction(BigDecimal.ZERO);
									        					 detailFactureArticlePromo.setMontantTTC(BigDecimal.ZERO);  
									        					 detailFactureArticlePromo.setBrutHT(BigDecimal.ZERO);
									        					 detailFactureArticlePromo.setRemiseCommerciale(BigDecimal.ZERO);
															         
															         //  detailFacture.setTypeVente(typevente);
															        
									        					 detailFactureArticlePromo.setHistoriqueventevendeur(facturePF);
															       //  detailFacture.setDateCreation(new Date());
															           
									        					 detailFactureArticlePromo.setUtilisateur(utilisateur);
															         
															           listDetailFacturePF.add(detailFactureArticlePromo);
															           detailFacturePfdao.add(detailFactureArticlePromo);
															          
									        					
									        					
									        					
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
						        	 DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
							          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
							          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
							     
						            i++;
						        }
						       txttotalmontantTTC.setText(montanttotal+"");
						        txttotalquantite.setText(sommequantite+"");
						        txttotalmontantHT.setText(montanttotalHT+"");
					  			txttotalmontantTVA.setText(montanttotalTVA+"");


								
						        initialiser();
								


				        	 
				      
				       
								
					       }else
					       {
					    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					    	   return;
					       }
					       
				         }
					
				}
				
				
		
			        
					
				
				
			
	     		
	     		
	     		
	     		
	     	}
	     });
	    btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAjouter.setBounds(536, 583, 107, 24);
	    btnAjouter.setIcon(imgAjouter);
	    add(btnAjouter);
	    
	    JButton button_5 = new JButton("Initialiser");
	    button_5.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		
	    		initialiser();
	    	}
	    });
	    button_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button_5.setBounds(663, 583, 106, 23);
	    add(button_5);
	    
	     btn_valider = new JButton("Valider ");
	     btn_valider.addActionListener(new ActionListener() {
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
					JOptionPane.showMessageDialog(null, "Veuillez entrer les articles à facturé SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
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
						
						HistoriqueVentevendeur facturePFTmp=listFacturePF.get(table.getSelectedRow());
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
						facturePFTmp.setDetailHistoriqueVenteVendeur (listDetailFacturePF);
						
						
						
					    facturepfdao.edit(facturePFTmp);
					    
					    ////////////////////////////////// ajouter detail compte client par client facture ////////////////////////   
					    HistoriqueVentevendeur facturePF=listFacturePF.get(table.getSelectedRow());
					 
					
						
						
					   
					    
					
					 
					
						
						/*
						 * int i=0; while(i<listStockPF.size()) { StockPF
						 * stockPF=stockpfDAO.findById(listStockPF.get(i).getId());
						 * stockPF.setStockOffre(listStockPF.get(i).getStockOffre());
						 * stockpfDAO.edit(stockPF); i++; }
						 */
					    
				
					
						 
					    
					    
					    
					    JOptionPane.showMessageDialog(null, "Facture Modifier avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					    initialiserFacture();
					    initialiser();
					   
					  
					    listDetailFacturePF.clear();
					 
						InitialiseTableau();
						chargerListeFacture();
							
					}
				
			
				}
				
				
			
	     		
	     		
	     		
	     		
	     		
	     		
	     	}
	     });
	    btn_valider.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btn_valider.setBounds(519, 893, 112, 24);
	    btn_valider.setIcon(imgValider);
	    add(btn_valider);
	    
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane.setBounds(10, 368, 1674, 51);
	    add(layeredPane);
	    
	    JLabel label_16 = new JLabel("Code  :");
	    label_16.setBounds(238, 15, 89, 24);
	    layeredPane.add(label_16);
	    
	    txtnumbl = new JTextField();
	    txtnumbl.setEditable(false);
	    txtnumbl.setColumns(10);
	    txtnumbl.setBounds(285, 14, 129, 26);
	    layeredPane.add(txtnumbl);
	    
	    JLabel label_17 = new JLabel("Date  :");
	    label_17.setBounds(424, 15, 62, 24);
	    layeredPane.add(label_17);
	    
	     dateChooserfacture = new JDateChooser();
	    dateChooserfacture.setLocale(Locale.FRANCE);
	    dateChooserfacture.setDateFormatString("dd/MM/yyyy");
	    dateChooserfacture.setBounds(464, 13, 139, 26);
	    layeredPane.add(dateChooserfacture);
	    
	    JLabel label_18 = new JLabel("Depot :");
	    label_18.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    label_18.setBounds(613, 15, 56, 24);
	    layeredPane.add(label_18);
	    
	    combodepot = new JComboBox();
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
	    combodepot.setSelectedIndex(-1);
	    combodepot.setBounds(661, 15, 144, 24);
	    layeredPane.add(combodepot);
	    
	    JLabel label_19 = new JLabel("Magasin :");
	    label_19.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    label_19.setBounds(815, 15, 56, 24);
	    layeredPane.add(label_19);
	    
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
	    combomagasin.setSelectedIndex(-1);
	    combomagasin.setBounds(869, 16, 183, 24);
	    layeredPane.add(combomagasin);
	    
	     comboClientpf = new JComboBox();
	    comboClientpf.setSelectedIndex(-1);
	    comboClientpf.setBounds(1109, 15, 183, 24);
	    layeredPane.add(comboClientpf);
	    AutoCompleteDecorator.decorate(comboClientpf);
	    
	    JLabel label_20 = new JLabel("Client :");
	    label_20.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    label_20.setBounds(1062, 15, 56, 24);
	    layeredPane.add(label_20);
	    
	    JLabel label_21 = new JLabel("Fournisseur :");
	    label_21.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    label_21.setBounds(1302, 15, 79, 24);
	    layeredPane.add(label_21);
	    
	    comboFournisseur = new JComboBox();
	    comboFournisseur.setSelectedIndex(-1);
	    comboFournisseur.setBounds(1380, 16, 149, 24);
	    layeredPane.add(comboFournisseur);
	    AutoCompleteDecorator.decorate(comboFournisseur);
	    
	    for(int i=0;i<listParClientPF.size();i++)
	    {
	    	
	    ClientPF clientpf=listParClientPF.get(i);
	    comboparclient.addItem(clientpf.getNom());
	    mapParClientPF.put(clientpf.getNom(), clientpf);
	    supprimer_facture.setEnabled(false);
	    }
	    
	    
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
		  
		  JLabel label_23 = new JLabel("Type BL :");
		  label_23.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  label_23.setBounds(10, 13, 70, 24);
		  layeredPane.add(label_23);
		  
		   comboTypeBL = new JComboBox();
		  comboTypeBL.setSelectedIndex(-1);
		  comboTypeBL.setBounds(77, 14, 151, 24);
		  layeredPane.add(comboTypeBL);
	    
		  
		    int  p=0;
		      combofamille.addItem("");
		      
		       checkSansmodepaiement = new JCheckBox("Imprimer Sans Mode de Paiement");
		      checkSansmodepaiement.setFont(new Font("Tahoma", Font.BOLD, 11));
		      checkSansmodepaiement.setBounds(1391, 618, 245, 30);
		      add(checkSansmodepaiement);
		      
		       chckbxSansTimbre = new JCheckBox("Sans Timbre");
		      chckbxSansTimbre.setFont(new Font("Tahoma", Font.BOLD, 11));
		      chckbxSansTimbre.setBounds(1391, 651, 245, 30);
		      add(chckbxSansTimbre);
		      while(p<listFamilleArticle.size())
		      {
		    	  
		    	  FamilleArticlePF famillearticle=listFamilleArticle.get(p);
		    	  combofamille.addItem(famillearticle.getLiblle());
		    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
		    	  p++;
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
			    
			     chckbxPiece = new JCheckBox("Piece");
			    chckbxPiece.setBounds(1546, 16, 83, 24);
			    layeredPane.add(chckbxPiece);
			    
			     lblEnTete = new JLabel("En Tete :");
			    lblEnTete.setFont(new Font("Tahoma", Font.PLAIN, 12));
			    lblEnTete.setBounds(1478, 261, 87, 24);
			    add(lblEnTete);
			    
			     comboEnTete = new JComboBox();
			    comboEnTete.setSelectedIndex(-1);
			    comboEnTete.setBounds(1541, 262, 186, 24);
			    add(comboEnTete);
			    
			    
			    lblEnTete.setVisible(false);
				comboEnTete.setVisible(false);
				comboEnTete.addItem("");
				
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
				checkEnTete.setBounds(1474, 229, 229, 30);
				add(checkEnTete);
				
				 chckbxImprimerNumBl = new JCheckBox("Imprimer Num BL");
				chckbxImprimerNumBl.setFont(new Font("Tahoma", Font.BOLD, 11));
				chckbxImprimerNumBl.setBounds(1474, 292, 229, 30);
				add(chckbxImprimerNumBl);
				
				 chckbxImprimerNumBc = new JCheckBox("Imprimer Num BC");
				chckbxImprimerNumBc.setFont(new Font("Tahoma", Font.BOLD, 11));
				chckbxImprimerNumBc.setBounds(1474, 325, 229, 30);
				add(chckbxImprimerNumBc);
				
				 btnImprimerBl = new JButton("Imprimer BL");
				 btnImprimerBl.addActionListener(new ActionListener() {
				 	public void actionPerformed(ActionEvent arg0) {
				 		
				 		if(listDetailFacturePF.size()!=0)
						{
				 			
				 			
				 			boolean apercu=false;
							
							 int reponse = JOptionPane.showConfirmDialog(null, "Voulez Vous Afficher l'aperçu Avant l'impression ?", 
										"Satisfaction", JOptionPane.YES_NO_OPTION);
								 
								if(reponse == JOptionPane.YES_OPTION )		
									
								{
									apercu=true;
								}
							
 
					 

					List<DetailHistoriqueVenteVendeur> listDetailFacturePFImprimer =new ArrayList<DetailHistoriqueVenteVendeur>();
					List<DetailHistoriqueVenteVendeur> listDetailFacturePFImprimerTmp =new ArrayList<DetailHistoriqueVenteVendeur>();
					   List<DetailHistoriqueVenteVendeur> listDetailFacturePFTmp =new ArrayList<DetailHistoriqueVenteVendeur>();
				 
					   BigDecimal quantitePieceCalculer=BigDecimal.ZERO; 
				       BigDecimal prixPieceCalculer=BigDecimal.ZERO; 
				       BigDecimal MontantHTPieceCalculer=BigDecimal.ZERO; 
				       BigDecimal MontantTVAPieceCalculer=BigDecimal.ZERO; 
				       BigDecimal MontantTTCPieceCalculer=BigDecimal.ZERO; 
				        
					   listDetailFacturePFTmp.addAll(listDetailFacturePF);
									
									 if(listDetailFacturePFTmp.size()!=0)
									 {
										
										 
										  quantitePieceCalculer=BigDecimal.ZERO; 
									         prixPieceCalculer=BigDecimal.ZERO; 
									         MontantHTPieceCalculer=BigDecimal.ZERO; 
									         MontantTVAPieceCalculer=BigDecimal.ZERO; 
									         MontantTTCPieceCalculer=BigDecimal.ZERO; 
										for(int i=0;i<listDetailFacturePFTmp.size();i++)
										{
											
											 if(listDetailFacturePFTmp.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_CHAARA) || listDetailFacturePFTmp.get(i).getSousFamille().getFamileArticlePF().getCode().equals(Constantes.CODE_MKARKEB) )
												{
													if(listDetailFacturePFTmp.get(i).getHistoriqueventevendeur().getImprimerPiece()==true)
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
												         listDetailFacturePFTmp.get(i).setPrixUnitaire(prixPieceCalculer);
												         listDetailFacturePFTmp.get(i).setMontantHT(MontantHTPieceCalculer);
												         listDetailFacturePFTmp.get(i).setMontantTVA(MontantTVAPieceCalculer);
												         listDetailFacturePFTmp.get(i).setMontantTTC(MontantTTCPieceCalculer);
												         listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i));
														
													}else
													{
														listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
													}
													
												}else
												{
													 listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
												}
										}
										 
									        
											
											
											
											 
									  			
								  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
								  		  	
										 String dateFacture="";
								  		   
										 
										 dateFacture=dateFormat.format(listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getDateFacture());
										/*
										 * if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
										 * 
										 * }else { dateFacture =facturepfTmp.getNumFacture().substring(4,
										 * 6)+"/"+facturepfTmp.getNumFacture().substring(2,
										 * 4)+"/"+20+facturepfTmp.getNumFacture().substring(0, 2); }
										 */
								  		 
											Map parameters = new HashMap();
										
											parameters.put("client", listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getClientPF().getNom());
											
											
												parameters.put("NumBL", listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getNumBl());
												
												if(listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getDateBl()!=null)
												{
													parameters.put("dateBl",dateFormat.format( listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getDateBl()));
												}else
												{
													parameters.put("dateBl", "");
												}
												
												if(listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getDateBCommande()!=null)
												{
													parameters.put("dateBC",dateFormat.format(listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getDateBCommande()) );
												}else
												{
													parameters.put("dateBC", "");
												}
												if(listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getNumCommande()!=null)
												{
													parameters.put("NumBC", listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getNumCommande());
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
											
												
												if(listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getAdresseClient()!=null)
												{
													if(!listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getAdresseClient().equals(""))
													{
														parameters.put("adresse", listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getAdresseClient());
													}else
													{
														parameters.put("adresse", listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getClientPF().getAdresse());
													}
													
												}else
												{
													parameters.put("adresse", listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getClientPF().getAdresse());
													
												}
											
											
											parameters.put("code",listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getClientPF().getCode());
											
											if(listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getClientPF().getPatente()!=null)
											{
												parameters.put("patente", listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getClientPF().getPatente());
											}else
											{
												parameters.put("patente", "");
											}
											
											if(listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getClientPF().getIce()!=null)
											{
												parameters.put("iceclient",listDetailFacturePFImprimer.get(0).getHistoriqueventevendeur().getClientPF().getIce());
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
												
												
												JasperUtils.imprimerBonDeLivraisonHistorique(listDetailFacturePFImprimer,parameters,true);
												 
													 
														  
													   
													
													
											 
												
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
				 });
				btnImprimerBl.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnImprimerBl.setBounds(659, 938, 138, 24);
				btnImprimerBl.setIcon(imgImprimer);
				add(btnImprimerBl);
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
			
	for(int j=0;j<table.getRowCount();j++){
		
		boolean regle=(boolean) table.getValueAt(j, 8);
		if(regle==true ){
			
			maptransfereblfacture.put(String.valueOf(table.getValueAt(j, 0).toString()), Boolean.valueOf(table.getValueAt(j, 8).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}
	

	
	
	void initialiserFacture()
	{
		
		combodepot.setSelectedIndex(-1);
		combomagasin.removeAllItems();
		combomagasin.setSelectedIndex(-1);
		
		txttotalmontantTTC.setText("");
		txttotalquantite.setText("");
		txttotalmontantHT.setText("");
		txttotalmontantTVA.setText("");
		txttimbre.setText("");
		txtnetapayer.setText("");
		chckbxSansTimbre.setSelected(false);
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
						"Type BL","Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Magasin", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false
				};
				Class[] columnTypes = new Class[] {
						String.class,String.class,Date.class,String.class,String.class,String.class,String.class,String.class,BigDecimal.class
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
	
	void afficher_tableDetailFacturePF(List<DetailHistoriqueVenteVendeur> listDetailFacture)
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
		DetailHistoriqueVenteVendeur detailfacturepf=listDetailFacture.get(i);
			
			Object []ligne={detailfacturepf.getSousFamille().getFamileArticlePF().getLiblle(),detailfacturepf.getSousFamille().getLiblle(), detailfacturepf.getArticle().getLiblle(),detailfacturepf.getPrixUnitaire(),detailfacturepf.getQuantite(),detailfacturepf.getReduction(), detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(), detailfacturepf.getMontantTTC()};

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
		facturepfdao=new HistoriqueVenteVendeurDAOImpl();
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
		
			listFacturePFTmp=facturepfdao.findByNumFcatureClientDateFactureDepotEtatRegle(txtparnumBL.getText(),clientpf,datedebut.getDate(), pardateChooser.getDate(), depot,typeBL);
		
			if(!comboparfamille.getSelectedItem().equals(""))
			{
				if(!comboparsousfamille.getSelectedItem().equals(""))
				{
					
					SousFamilleArticlePF sousfamille=mapsousfamille.get(comboparsousfamille.getSelectedItem());
				
					int i=0;
					while (i<listFacturePFTmp.size())
					{
						HistoriqueVentevendeur facturePF=listFacturePFTmp.get(i);
						exist=false;
						listDetailFacturePFTMP=detailFacturePfdao.listeDetailFacturePFByFacture(facturePF.getId());
						for(int j=0;j<listDetailFacturePFTMP.size();j++)
						{
							DetailHistoriqueVenteVendeur detailfacturePF=listDetailFacturePFTMP.get(j);
							
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
	
	
	
	
	void afficher_tableFacturePF(List<HistoriqueVentevendeur> listFacture)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Type BL","Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Magasin", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false
				};
				Class[] columnTypes = new Class[] {
						String.class,String.class,Date.class,String.class,String.class,String.class,String.class,String.class,BigDecimal.class
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
		HistoriqueVentevendeur facturepf=listFacture.get(i);
		
		Date datefacture=facturepf.getDateFacture();
			
		for(int j=0;j<facturepf.getDetailHistoriqueVenteVendeur().size();j++)
		{
			
			MontantTTC=MontantTTC.add(facturepf.getDetailHistoriqueVenteVendeur().get(j).getMontantTTC());
		}
		
		if(facturepf.getTypeBL()!=null)
		{
			typeBL=facturepf.getTypeBL().getType();
		}
		
		
			Object []ligne={typeBL,facturepf.getNumBl(),datefacture,facturepf.getType(),facturepf.getNumFacture(),facturepf.getClientPF().getNom(),facturepf.getDepot().getLibelle(),facturepf.getMagasin().getLibelle(),MontantTTC};

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
	}




