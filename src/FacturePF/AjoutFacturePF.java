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
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import presentation.stockMP.TransfererStockMP;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.AdresseClientDAOImpl;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.FactureVenteMPDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoImplManager.TypeBLDAOImpl;
import dao.daoImplManager.TypeVenteDAOImpl;
import dao.daoManager.AdresseClientPFDAO;
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
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.FactureVenteMPDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.daoManager.TypeBLDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.AdresseClientPF;
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
import dao.entity.DetailPrixArticle;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.Employe;
import dao.entity.Facture;
import dao.entity.FacturePF;
import dao.entity.FactureServiceProduction;
import dao.entity.FactureVenteMP;
import dao.entity.FamilleArticlePF;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Sequenceur;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.TypeBL;
import dao.entity.TypeVente;
import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import com.lowagie.text.pdf.events.IndexEvents.Entry;
import com.toedter.calendar.JDateChooser;


import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;


public class AjoutFacturePF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleChargefixe;

	private JXTable  tableArticle = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private List<Articles> listArticleOffre =new ArrayList<Articles>();
	private List<SousFamilleArticlePF> listSousFamilleArticleOffre =new ArrayList<SousFamilleArticlePF>();
	private List<StockPF> listArticleStockPF =new ArrayList<StockPF>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	private List<TransferStockPF> listTransferStockPF =new ArrayList<TransferStockPF>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private List<TypeBL> listTypeBL =new ArrayList<TypeBL>();
	
	private TransferStockPF transferStock = new TransferStockPF();
	private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	private TransferStockPFDAO transferStockPFDAO;
	
	private Map< String, Articles> mapArticleOffre = new HashMap<>();
	private Map< String, Articles> mapArticlePromo = new HashMap<>();
	private Map< String, Articles> mapArticleOffreCode = new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, ClientPF> mapClientPF= new HashMap<>();
	private Map< String, Client> mapFournisseur= new HashMap<>();
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamilleOffre= new HashMap<>();
	private Map< String, TypeBL> mapTypeBL= new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private    JComboBox combofamille =new JComboBox();
	 private JComboBox comboArticle;
	 private  JComboBox combosousfamille ;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FacturePFDAO facturepfdao;
	private FacturePF facturePF=new FacturePF();
	private DetailCompteClientDAO detailCompteClientdao;
private DetailFacturePFDAO detailFacturePfdao;
private ClientPFDAO clientpfdao;
private StockPFDAO stockpfDAO;
	private JTextField txtcodearticle;
	ChargeProduction chargeproduction;
	private JTextField txtquantite;
	private JTextField txtnumBL;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	private CompteClientDAO compteclientdao;
	private ClientDAO fournisseurdao;
	private TypeBLDAO typeBLDAO;
	private FamilleArticlesPFDAO familleArticleDAO;
	private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
	private DetailPrixArticleDAO detailPrixArticleDAO;
	private static SequenceurDAO sequenceurDAO=ProdLauncher.sequenceurDAO;
	private DetailTransferProduitFiniDAO detailTransferStockPFdao;
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
	private  JButton btnSupprimer = new JButton();
	private   JComboBox comboFournisseur = new JComboBox();
	private   JComboBox comboClientpf ;
	private JTextField txttotalmontantHT;
	private JTextField txttotalmontantTVA;
	private JTextField txtreduction;
	private  JCheckBox checkboxGratuits = new JCheckBox("Gratuit");
	 JComboBox comboFamilleGratuit = new JComboBox();
	 JComboBox comboSousFamilleGratuit = new JComboBox();
	 JComboBox comboArticleGratuit = new JComboBox();
	 JLayeredPane layerArticle = new JLayeredPane();
	 JLabel labelSousFamillegratuit = new JLabel("Sous Famille :");
	 JLabel labelcodearticlegratuit = new JLabel("Code Article :");
	 JLabel labelfamillearticlegratuit = new JLabel("Famille Article :");
	 JLabel labelarticlegratuit = new JLabel("Libelle :");
	 JLabel labelquantitegratuit = new JLabel("Quantite :");
	  JLabel lblPrix = new JLabel("Prix  :");
	  JLabel lblMontant = new JLabel("Montant  :");
	  JLabel lblReduction = new JLabel("Remise :");
	  JLabel labelpourcentage = new JLabel("%");
	  private FactureVenteMPDAO factureVenteMPdao;
		private FacturePFDAO factureVentePFdao;
		private JTextField txtquantitepacket;
		  JCheckBox checkttc = new JCheckBox("TTC");
		  JLabel labelmarge = new JLabel("marge");
		  private JLabel stockdisponible;
		  JLabel labelprixmin = new JLabel("New label");
		  JLabel labelPrixMax = new JLabel("New label");
		  BigDecimal prixTTC=BigDecimal.ZERO;
		  BigDecimal StockFinale=BigDecimal.ZERO;
		  BigDecimal StockFinaleAnne=BigDecimal.ZERO;
		  BigDecimal stockfinaleTherres=BigDecimal.ZERO;
		  BigDecimal stockfinaleVerres=BigDecimal.ZERO;
		  BigDecimal stockfinaleArticlePromo=BigDecimal.ZERO;
		  private JTextField txtquantitetherres;
		  private JTextField txtquantiteverres;
		  JLabel lblOffreTherre = new JLabel("Offre Therre :");
		  JComboBox comboBoxtherres = new JComboBox();
		  JLabel lbloffreverres = new JLabel("Offre Verres :");
		  JComboBox comboBoxverres = new JComboBox();
		  JLabel stockdisponibleoffre = new JLabel("");
		  private JLabel stockdisponibleoffretherres;
		  JLabel stockdisponibleoffreverres = new JLabel("");
		  private JTextField txtquantitepromo;
		  JComboBox comboBoxPromo = new JComboBox();
		  JLabel lblOffrePromo = new JLabel("Offre Promo :");
		  JLabel stockdisponiblearticlepromo = new JLabel("");
		  JCheckBox checkboxSansTva = new JCheckBox("Sans TVA");
		  JCheckBox chckbxPiece = new JCheckBox("Piece");
		  JComboBox comboTypeBl = new JComboBox();
	//////////////////////////////  Read file Excel  ////////////////////////////////////////	  
		  
		  private JTextField txtlien;
		  private JFrame mainFrame;
		  private static XSSFSheet ExcelWSheet;
		    private static XSSFWorkbook ExcelWBook;
		    private static XSSFCell CellOldNumFacture;
		    private static XSSFCell CellDatefacture;
		    private static XSSFCell CellNewNumFacture;
		    private	List<FacturePF> listFacturePF= new ArrayList<FacturePF>();
		    
		    private FactureServiceProductionDAO factureServiceProductiondao;
		    private List<FactureServiceProduction> listFactureServiceProduction =new ArrayList<FactureServiceProduction>();
		    private List<DetailTransferProduitFini> listDetailTransferStockPF =new ArrayList<DetailTransferProduitFini>();
		    private ProductionDAO productionDAO;
		    private TransferStockMPDAO transferStockMPDAO;
		    private JTextField txtnumCommande;
		    JComboBox comboAdresse = new JComboBox();
		    JDateChooser dateChooserBCommande = new JDateChooser();
		    private List<AdresseClientPF> listAdresseClient =new ArrayList<AdresseClientPF>();
		    AdresseClientPFDAO adresseClientPFDAO;
	/////////////////////////////////////////////////////////////////////////	  
		  
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutFacturePF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1707, 947);
      
	
        try{ 
        	
        	 
        	 
      //  facturePF=new FacturePF();
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
           
            utilisateur=AuthentificationView.utilisateur;
        	transferStockPFDAO=new TransferStockPFDAOImpl();
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	facturepfdao=new FacturePFDAOImpl();
         	stockpfDAO=new StockPFDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailFacturePfdao=new DetailFacturePFDAOImpl();
         	clientpfdao=new ClientPFDAOImpl();
         	articleDAO=new ArticlesDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         	compteclientdao=new CompteClientDAOImpl();
         	fournisseurdao=new ClientDAOImpl();
         	detailPrixArticleDAO=new DetailPrixArticleDAOImpl();
         	familleArticleDAO=new FamilleArticlesPFDAOImpl();
         	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
         	listFamilleArticle=familleArticleDAO.findAll();
         	factureVentePFdao=new FacturePFDAOImpl();
         	factureVenteMPdao=new FactureVenteMPDAOImpl();
         	detailTransferStockPFdao=new DetailTransferProduitFiniDAOImpl();
        	factureServiceProductiondao=new FactureServiceProductionDAOImpl();
        	productionDAO=new ProductionDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	adresseClientPFDAO=new AdresseClientDAOImpl();
        	typeBLDAO=new TypeBLDAOImpl();
        	listTypeBL=typeBLDAO.findAll();
        	
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
            	            labelpourcentage.setVisible(false);
            		 		txtPrix.setVisible(false);
            		 		txtreduction.setVisible(false);
            		 		txtmontant.setVisible(false);
            		 		lblMontant.setVisible(false);
            		 		lblReduction.setVisible(false);	
            		 		lblPrix.setVisible(false);	
            		 		checkttc.setVisible(false);
                   			
                   			
                   			combofamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0));
                       		combosousfamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
                       		Articles article=mapArticle.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
                       		comboArticle.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
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
                       				txtquantitepacket.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).divide(article.getConditionnement(),6,RoundingMode.HALF_UP)+"");
                       				String part[]=txtquantitepacket.getText().split("\\.");
                       				txtquantitepacket.setText(part[0]);
                       				txtquantite.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).subtract(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()))+"");
                       			}
                       		}else
                       		{
                       			txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());	
                       		}
                       		
                       		
                       		btnAjouter.setEnabled(false);
                   			
                   		}else
                   		{
                   			boolean offre1=false , offre2=false;
                   			
                   			boolean trouve=false;
                   			checkboxGratuits.setSelected(false);
                   			labelpourcentage.setVisible(false);
            		 		txtPrix.setVisible(true);
            		 		txtreduction.setVisible(true);
            		 		txtmontant.setVisible(true);
            		 		lblMontant.setVisible(true);
            		 		lblReduction.setVisible(true);	
            		 		lblPrix.setVisible(true);	
            		 		checkttc.setVisible(true);
            		 		
                   			Articles article=mapArticle.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
                   			combofamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0));
                       		combosousfamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
                       		comboArticle.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
                       		txtPrix.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString());
                       		checkttc.setSelected(false);
                       		
                       		if(!new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()).setScale(6,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,RoundingMode.HALF_UP)) && new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 7).toString()).setScale(6,RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,RoundingMode.HALF_UP)))
                       		{
                       			checkboxSansTva.setSelected(true);
                       		}else
                       		{
                       			checkboxSansTva.setSelected(false);
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
                       				txtquantitepacket.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).divide(article.getConditionnement(),6,RoundingMode.HALF_UP)+"");
                       				
                       				String[] part=txtquantitepacket.getText().split("\\.");
                       				
                       				txtquantitepacket.setText(part[0]);
                       				txtquantite.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).subtract(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()))+"");
                       			}
                       		}else
                       		{
                       			txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());	
                       		}
                       		
                       		if(trouve==true)
                       		{
                       			txtreduction.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString());
                           		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
                           		txtmontant.setText((new BigDecimal(txtquantite.getText()).multiply(new BigDecimal(txtPrix.getText()))).add(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()).multiply(new BigDecimal(txtPrix.getText())))+"");
                           		
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
                   		
            	
            if(!txtquantitepacket.getText().equals(""))
            {
            	if(new BigDecimal(txtquantitepacket.getText()).equals(BigDecimal.ZERO))
            		{
            			txtquantitepacket.setText("");
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
       			"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Reduction","Montant HT", "Montant TVA", "Montant TTC"
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
				  		     	scrollPane.setBounds(10, 493, 1031, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 452, 1027, 30);
				  		     	add(titledSeparator);
		       
		    
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal solde=BigDecimal.ZERO;
				SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				 String date = dcn.format(dateChooserfacture.getDate());
				 
				if(txtnumBL.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Erreur Num BL !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(dateChooserfacture.getDate()==null)
				{

					JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
				}else if(combodepot.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
				}else if(combomagasin.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
					
				}else if(comboFournisseur.getSelectedIndex()==-1)
				{

					JOptionPane.showMessageDialog(null, "Veuillez choisir le Fournisseur SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
					
				}else if(comboClientpf.getSelectedIndex()==-1 || comboClientpf.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le Client SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
					
				}else if(listDetailFacturePF.size()==0)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer les articles à facturé SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(listDetailTransferProduitFini.size()==0)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer les articles à facturé SVP (Transfer Produit Fini) !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					Depot depot=mapDepot.get(combodepot.getSelectedItem());
					
					FactureVenteMP factureventetmp=factureVenteMPdao.findFactureVenteMPByNumBLByDepot(txtnumBL.getText(), depot) ;
					FacturePF factureventetPF=factureVentePFdao.findFactureVentePFByNumBLByDepot(txtnumBL.getText(), depot);
					
					if(factureventetmp!=null || factureventetPF!=null)
					{
						JOptionPane.showMessageDialog(null, "Num BL existe déja ","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					
				
				Magasin magasin =mapMagasin.get(combomagasin.getSelectedItem());
				ClientPF clientPF =mapClientPF.get(comboClientpf.getSelectedItem());
				Client fournisseur=mapFournisseur.get(comboFournisseur.getSelectedItem());
				String codeTransfert=Utils.genererCodeTransfer(depot.getCode(),ETAT_TRANSFER_STOCK_VENTE);
				
			facturePF.setNumBl(txtnumBL.getText());
			facturePF.setClientPF(clientPF);
			facturePF.setFournisseur(fournisseur);
			facturePF.setDepot(depot);
			facturePF.setMagasin(magasin);
			facturePF.setDateBl(dateChooserfacture.getDate());
			facturePF.setDateFacture(dateChooserfacture.getDate());
			facturePF.setEtat(Constantes.ETAT_NON_REGLE);
			facturePF.setType(Constantes.TYPE_BON_LIVRAISON);
			facturePF.setMontantHT((new BigDecimal(txttotalmontantHT.getText())).setScale(6, RoundingMode.HALF_UP));
			facturePF.setMontantTVA((new BigDecimal(txttotalmontantTVA.getText())).setScale(6, RoundingMode.HALF_UP));
			facturePF.setMontantTTC((new BigDecimal(txttotalmontantTTC.getText())).setScale(6, RoundingMode.HALF_UP));	
			facturePF.setCodeTransfer(codeTransfert);
			facturePF.setDateSaisi(new Date());
			facturePF.setEspece(BigDecimal.ZERO);
			facturePF.setCheque(BigDecimal.ZERO);
			facturePF.setVirement(BigDecimal.ZERO);
			facturePF.setTraite(BigDecimal.ZERO);
			facturePF.setCredit(BigDecimal.ZERO);
			facturePF.setImprimerPiece(chckbxPiece.isSelected());
			facturePF.setNumCommande(txtnumCommande.getText());
			facturePF.setDateBCommande(dateChooserBCommande.getDate());
			
			if(comboAdresse.getSelectedIndex()!=-1)
			{
				if(!comboAdresse.getSelectedItem().equals(""))
				{
					facturePF.setAdresseClient(comboAdresse.getSelectedItem().toString());
				}else
				{
					facturePF.setAdresseClient(clientPF.getAdresse());
				}
				
				
				
			}else
			{
				facturePF.setAdresseClient(clientPF.getAdresse());
			}
			
			if(comboTypeBl.getSelectedIndex()!=-1)
			{
				if(!comboTypeBl.getSelectedItem().equals(""))
				{
					
					TypeBL typeBL=mapTypeBL.get(comboTypeBl.getSelectedItem().toString());
					if(typeBL!=null)
					{
						
						facturePF.setTypeBL(typeBL);
						
					}
					
					
				}
			} 
			
			
			//facturePF.setDetailFacturePF(listDetailFacturePF);
			
		    facturePF.setCreerPar(utilisateur);
		    facturepfdao.add(facturePF);
		
		  
		 for(int i=0;i<listDetailFacturePF.size();i++)
		 {
			 detailFacturePfdao.add(listDetailFacturePF.get(i));
		 }
		   
		    ////////////////////////////////// ajouter detail compte client par client facture ////////////////////////   
		    
		    DetailCompteClient detailcompteclient=new DetailCompteClient();
		    detailcompteclient.setCompteClient(clientPF.getCompteClient());
		    detailcompteclient.setUtilisateurCreation(utilisateur);
		    detailcompteclient.setDateCreation(new Date());
		    detailcompteclient.setDateOperation(dateChooserfacture.getDate());
		    detailcompteclient.setMontantDebit(new BigDecimal(txttotalmontantTTC.getText()));
		    detailcompteclient.setMontantCredit(BigDecimal.ZERO);
		    detailcompteclient.setDesignation("Montant sur Facture N "+txtnumBL.getText());
		    detailcompteclient.setFacturepf(facturePF);
		    detailcompteclient.setFournisseur(fournisseur);
		    detailCompteClientdao.add(detailcompteclient);
		    solde=clientPF.getCompteClient().getSolde().add(new BigDecimal(txttotalmontantTTC.getText()));
		    clientPF.getCompteClient().setSolde(solde);
		    compteclientdao.edit(clientPF.getCompteClient());
		   
		    
	/*	    Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE);
		    if(sequenceur!=null)
		    {
		    	int valeur=sequenceur.getValeur()+1;
			    sequenceur.setValeur(valeur);
			    sequenceurDAO.edit(sequenceur);
		    }else
		    {
		    	Sequenceur sequenseur=new Sequenceur();
				sequenseur.setLibelle( Constantes.CODE_FACTURE_VENTE);
				sequenseur.setCode(date);
				sequenseur.setValeur(1);
				sequenceurDAO.add(sequenseur);
		    }*/
		    
		    // ajouter Transfer Stock PF (Mouvement Stock PF )
		  
			transferStock.setCodeTransfer(codeTransfert);
			transferStock.setCreerPar(utilisateur);
			transferStock.setDate(new Date());
			transferStock.setDateTransfer(dateChooserfacture.getDate());
			transferStock.setListDetailTransferProduitFini(listDetailTransferProduitFini);
			transferStock.setStatut(ETAT_TRANSFER_STOCK_VENTE);
			 
			transferStockPFDAO.add(transferStock);
			
			 for(int j=0;j<listDetailTransferProduitFini.size();j++)
			 {
				 detailTransferStockPFdao.add(listDetailTransferProduitFini.get(j));
			 }
			
			
			/*
			listDetailTransferProduitFini=transferStockPFDAO.findById(transferStock.getId()).getListDetailTransferProduitFini();
			listDetailTransferProduitFini.clear();
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
		
			 
		    JOptionPane.showMessageDialog(null, "Facture Ajouté avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
		    initialiserFacture();
		    initialiser();
		  chckbxPiece.setSelected(false);
		  facturePF=new FacturePF();
		
		  listDetailFacturePF.clear();
		  listDetailTransferProduitFini.clear();
			 transferStock=new TransferStockPF();
			 InitialiseTableau();
			 labelmarge.setText("");
				}
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(385, 768, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 202, 1031, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1485, 118);
		add(layeredPane_1);
		
		JLabel lblNBl = new JLabel("N\u00B0 BL  :");
		lblNBl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNBl.setBounds(602, 12, 73, 24);
		layeredPane_1.add(lblNBl);
		
		txtnumBL = new JTextField();
		txtnumBL.setColumns(10);
		txtnumBL.setBounds(685, 13, 183, 26);
		layeredPane_1.add(txtnumBL);
		
		JLabel lblDateBl = new JLabel("Date  BL :");
		lblDateBl.setBounds(878, 13, 62, 24);
		layeredPane_1.add(lblDateBl);
		
		 dateChooserfacture = new JDateChooser();
		dateChooserfacture.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {/*
				if(dateChooserfacture.getDate()!=null)
				{
				SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				 String date = dcn.format(dateChooserfacture.getDate());
				
				 
				txtnumfacture.setText(Utils.genererCodeFactureVente(date));
				}
			*/}
		});
		dateChooserfacture.setLocale(Locale.FRANCE);
		dateChooserfacture.setDateFormatString("dd/MM/yyyy");
		dateChooserfacture.setBounds(939, 11, 181, 26);
		layeredPane_1.add(dateChooserfacture);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(10, 52, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(62, 53, 143, 24);
		  layeredPane_1.add(combodepot);
		  combodepot.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		

 	 			
    	 		 if(e.getStateChange() == ItemEvent.SELECTED)
    	 		 {
    	 			int i=0;
    	 		
    	 				if(!combodepot.getSelectedItem().equals(""))
     			{
    	 					
    	 					
    	 					 if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) 
    	 					 {
    	 						checkboxSansTva.setSelected(false);
    	 						checkboxSansTva.setEnabled(true);
    	 					 }else {
    	 						checkboxSansTva.setSelected(true);
    	 						checkboxSansTva.setEnabled(false);
    	 					 }
    	 						 
    	 					
    	 					
    	 					
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
		  label_4.setBounds(215, 51, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  		 if(e.getStateChange() == ItemEvent.SELECTED)
    	 		 {
		  			
		  			 if(combomagasin.getSelectedIndex()!=-1)
		  			 {
		  				 if(!combomagasin.getSelectedItem().equals(""))
		  				 {
		  					comboFournisseur.removeAllItems();
		  					 Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			  				 Client fournisseur=fournisseurdao.findClientByCodeClient(magasin.getCodeMachine());
			  				mapFournisseur.put(fournisseur.getNom(), fournisseur);
			  				comboFournisseur.addItem(fournisseur.getNom()); 
		  				 }
		  				
		  				 
		  			 }
		  			 
		  			 
    	 		 }
		  		
		  		
		  		
		  		
		  	}
		  });
		  combomagasin.setBounds(281, 52, 183, 24);
		  layeredPane_1.add(combomagasin);
		  combomagasin.setSelectedIndex(-1);
		  
		   comboClientpf = new JComboBox();
		   comboClientpf.addItemListener(new ItemListener() {
		   	public void itemStateChanged(ItemEvent arg0) {
		   		
		   		if(comboClientpf.getSelectedIndex()!=-1)
		   		{
		   			
		   			if(!comboClientpf.getSelectedItem().equals(""))
		   			{
		   				
		   				ClientPF clientPF=mapClientPF.get(comboClientpf.getSelectedItem());
		   				if(clientPF!=null)
		   				{
		   					
		   					comboAdresse.removeAllItems();
				   			comboAdresse.addItem("");
				   			listAdresseClient=adresseClientPFDAO.findListAdresseClientByClient(clientPF)  ;
				   			if(listAdresseClient.size()!=0)
				   			{
				   				comboAdresse.addItem(clientPF.getAdresse());
				   				for(int i=0;i<listAdresseClient.size();i++)
				   				{
				   					comboAdresse.addItem(listAdresseClient.get(i).getAdresse());
				   				}
				   				
				   				
				   			}else
				   			{
				   				comboAdresse.addItem(clientPF.getAdresse());
				   			}
				   			
				   			
				   			
				   			 
				   			comboAdresse.setSelectedIndex(1);
				   			
				   			
				   			
				   			
		   				}else
		   				{
		   					comboAdresse.removeAllItems();
				   			comboAdresse.addItem("");
				   			comboAdresse.setSelectedIndex(0);
		   				}
		   				
		   				
		   			}else
		   			{
		   				
		   				
		   				comboAdresse.removeAllItems();
			   			comboAdresse.addItem("");
			   			comboAdresse.setSelectedIndex(0);
			   			
		   			}
		   			
		   			
		   			
		   		}else
		   		{
		   			comboAdresse.removeAllItems();
		   			comboAdresse.addItem("");
		   			comboAdresse.setSelectedIndex(0);
		   			 
		   		}
		   		
		   		
		   		
		   	}
		   });
		  comboClientpf.setSelectedIndex(-1);
		  comboClientpf.setBounds(529, 52, 240, 24);
		  layeredPane_1.add(comboClientpf);
		  
		  AutoCompleteDecorator.decorate(comboClientpf);
		  
		  JLabel lblClient = new JLabel("Client :");
		  lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblClient.setBounds(474, 51, 56, 24);
		  layeredPane_1.add(lblClient);
		  
		  JLabel lblFournisseur = new JLabel("Fournisseur :");
		  lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblFournisseur.setBounds(1210, 52, 79, 24);
		  layeredPane_1.add(lblFournisseur);
		  
		   comboFournisseur = new JComboBox();
		  comboFournisseur.setSelectedIndex(-1);
		  comboFournisseur.setBounds(1288, 53, 183, 24);
		  layeredPane_1.add(comboFournisseur);
		  
		  JLabel lblNCommande = new JLabel("N\u00B0 Commande  :");
		  lblNCommande.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblNCommande.setBounds(303, 12, 134, 24);
		  layeredPane_1.add(lblNCommande);
		  
		  txtnumCommande = new JTextField();
		  txtnumCommande.setColumns(10);
		  txtnumCommande.setBounds(427, 12, 167, 26);
		  layeredPane_1.add(txtnumCommande);
		  
		  JLabel lblTypeBl = new JLabel("Type BL :");
		  lblTypeBl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblTypeBl.setBounds(10, 11, 79, 24);
		  layeredPane_1.add(lblTypeBl);
		  
		   comboTypeBl = new JComboBox();
		  comboTypeBl.setSelectedIndex(-1);
		  comboTypeBl.setBounds(88, 12, 183, 24);
		  layeredPane_1.add(comboTypeBl);
		  
		  JLabel lblAdresse = new JLabel("Adresse :");
		  lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblAdresse.setBounds(779, 47, 56, 24);
		  layeredPane_1.add(lblAdresse);
		  
		    comboAdresse = new JComboBox();
		  comboAdresse.setSelectedIndex(-1);
		  comboAdresse.setBounds(840, 52, 363, 24);
		  layeredPane_1.add(comboAdresse);
		  
		  JLabel lblDateBcm = new JLabel("Date  BCM :");
		  lblDateBcm.setBounds(1130, 14, 62, 24);
		  layeredPane_1.add(lblDateBcm);
		  
		   dateChooserBCommande = new JDateChooser();
		  dateChooserBCommande.setLocale(Locale.FRANCE);
		  dateChooserBCommande.setDateFormatString("dd/MM/yyyy");
		  dateChooserBCommande.setBounds(1191, 12, 181, 26);
		  layeredPane_1.add(dateChooserBCommande);
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture");
		titledSeparator_2.setBounds(10, 11, 1229, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(385, 417, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					
					if(checkboxGratuits.isSelected()==true)
					{
						
// ajouter les articles gratuits
						

						boolean trouve =false;
						
						BigDecimal stock=BigDecimal.ZERO;
						BigDecimal marge=BigDecimal.ZERO;
						BigDecimal QuantiteUnit=BigDecimal.ZERO;
						BigDecimal QuantitePaquet=BigDecimal.ZERO;
					
						if(dateChooserfacture.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(comboArticle.getSelectedItem().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtcodearticle.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtquantite.getText().equals("") && txtquantitepacket.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)) ){
							
							
								JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							
						}else if(!txtquantitepacket.getText().equals("") && ((new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)<0)) ){
							
							
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
					        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()) && detailFacturePF.getPrixUnitaire().equals(BigDecimal.ZERO) && detailFacturePF.getMontantHT().equals(BigDecimal.ZERO)
					        			 
					        			&&  detailFacturePF.getMontantTVA().equals(BigDecimal.ZERO) && detailFacturePF.getMontantTTC().equals(BigDecimal.ZERO) && detailFacturePF.getReduction().equals(BigDecimal.ZERO) && detailFacturePF.getTva().equals(BigDecimal.ZERO)
					        			 
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
					        		 
					        		 if(!txtquantitepacket.getText().equals(""))
					        		 {
					        			QuantitePaquet=new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()) ;
					        		 }
					        		 if(!txtquantite.getText().equals(""))
					        		 {
					        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
					        		 }
					        		 
					        		
					        		 
					        		 
					        		 
					        		 
					        		 
					        		 // Verifier le stock offre pour la gratuité du the Promotion
					        		 if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) && !sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM) )
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
							        	 } else
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
									          detailFacture.setTva(BigDecimal.ZERO);
									          detailFacture.setSousFamille(sousfamille);
									          detailFacture.setReduction(new BigDecimal(100));
									          detailFacture.setMontantTTC(BigDecimal.ZERO);  
									          detailFacture.setBrutHT(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
									          detailFacture.setRemiseCommerciale(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite())); 

									         //  detailFacture.setTypeVente(typevente);
									        
									           detailFacture.setFacturePF(facturePF);
									       //  detailFacture.setDateCreation(new Date());
									           
									           detailFacture.setUtilisateur(utilisateur);
									         
									           listDetailFacturePF.add(detailFacture);
									        
									           stock=stockpf.getStockOffre().subtract(QuantiteUnit.add(QuantitePaquet));
									           stockpf.setStockOffre (stock);
									           stockpfDAO.edit(stockpf);
									           listStockPF.add(stockpf);
									          afficher_tableDetailFacturePF(listDetailFacturePF);
									          int i=0;
										        BigDecimal montanttotal=BigDecimal.ZERO;
										        BigDecimal montanttotalHT=BigDecimal.ZERO;
										        BigDecimal montanttotalTVA=BigDecimal.ZERO;
										        BigDecimal sommequantite=BigDecimal.ZERO;
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
										          if(detailFacturePF.getPrixUnitaire().compareTo(BigDecimal.ZERO)!=0)
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
									  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
									  				labelmarge.setBackground(Color.red);
									  			 }else
									  			 {
									  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
									  				labelmarge.setBackground(Color.GREEN);
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
												listDetailTransferProduitFini.add(detailTransferStockPF);
									  			
									  		  initialiser(); 
							        	 
							        		 
							        	 }
					        		 }else
					        		 {
					        			 
					        			 
					        			 if(StockFinale.compareTo(QuantiteUnit.add(QuantitePaquet))<0  || stockpf.getStock().compareTo(QuantiteUnit.add(QuantitePaquet))<0 )
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
									          detailFacture.setTva(BigDecimal.ZERO);
									          detailFacture.setSousFamille(sousfamille);
									          detailFacture.setReduction(new BigDecimal(100));
									          detailFacture.setMontantTTC(BigDecimal.ZERO);  
									          detailFacture.setBrutHT(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
									          detailFacture.setRemiseCommerciale(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite())); 
									         
									         
									         //  detailFacture.setTypeVente(typevente);
									        
									           detailFacture.setFacturePF(facturePF);
									       //  detailFacture.setDateCreation(new Date());
									           
									           detailFacture.setUtilisateur(utilisateur);
									         
									           listDetailFacturePF.add(detailFacture);
									        
									           stock=stockpf.getStock().subtract(QuantiteUnit.add(QuantitePaquet));
									          
									           stockpf.setStock(stock);
									           stockpfDAO.edit(stockpf);
									           listStockPF.add(stockpf);
									          afficher_tableDetailFacturePF(listDetailFacturePF);
									          int i=0;
										        BigDecimal montanttotal=BigDecimal.ZERO;
										        BigDecimal montanttotalHT=BigDecimal.ZERO;
										        BigDecimal montanttotalTVA=BigDecimal.ZERO;
										        BigDecimal sommequantite=BigDecimal.ZERO;
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
										          if(detailFacturePF.getPrixUnitaire().compareTo(BigDecimal.ZERO)!=0)
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
									  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
									  				labelmarge.setBackground(Color.red);
									  			 }else
									  			 {
									  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
									  				labelmarge.setBackground(Color.GREEN);
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
						BigDecimal stockArticlePromo=BigDecimal.ZERO;
						BigDecimal marge=BigDecimal.ZERO;
						BigDecimal QuantiteUnit=BigDecimal.ZERO;
						BigDecimal QuantitePaquet=BigDecimal.ZERO;
						
						boolean promo=false;
						
						if(dateChooserfacture.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(comboArticle.getSelectedItem().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtcodearticle.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtquantite.getText().equals("") && txtquantitepacket.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))){
							
							
								JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
						}else if(!txtquantitepacket.getText().equals("") &&  ((new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)<0))){
							
							
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
					        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()))
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
					        		 if(!txtquantitepacket.getText().equals(""))
					        		 {
					        			QuantitePaquet=new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()) ;
					        		 }
					        		 if(!txtquantite.getText().equals(""))
					        		 {
					        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
					        		 }
					        		 
					        		
					        	  	 if(stockpf.getStock().compareTo(QuantitePaquet.add(QuantiteUnit))<0 || StockFinale.compareTo(QuantitePaquet.add(QuantiteUnit))<0)
						        	 {
						        		 JOptionPane.showMessageDialog(null, "Stock "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
						        		 return;
						        	 }else
						        	 {
						        		 
						        		 SousFamilleArticlePF sousFamilleArticlePFOffreTherres=null; 
						        		 SousFamilleArticlePF sousFamilleArticlePFOffreVerres=null;
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
								          detailFacture.setBrutHT (prixTTC.multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
								          if(checkboxSansTva.isSelected()==true)
								          {
								        	  detailFacture.setMontantTVA(BigDecimal.ZERO);								         
									          detailFacture.setTva(BigDecimal.ZERO);
								          }else
								          {
								        	  detailFacture.setMontantTVA((((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));								         
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
								          detailFacture.setBrutHT (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP));
								          if(checkboxSansTva.isSelected()==true)
								          {
								        	  detailFacture.setMontantTVA(BigDecimal.ZERO);
									          detailFacture.setTva(BigDecimal.ZERO);
								          }else
								          {
								        	  detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
									          detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
								          }
								         
								          detailFacture.setSousFamille(sousfamille);
								          if(!txtreduction.getText().equals(""))
								          {
								        	  if(checkboxSansTva.isSelected()==true)
									          {
								        		  detailFacture.setRemiseCommerciale( ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP) );
								        		  detailFacture.setMontantHT( ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );
								        		  detailFacture.setMontantTVA(BigDecimal.ZERO);
								        		  detailFacture.setTva(BigDecimal.ZERO);
								        		  detailFacture.setMontantTTC( ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).setScale(6, RoundingMode.HALF_UP) );  

									          }else
									          {
									        	  
								        		  detailFacture.setRemiseCommerciale(( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ).setScale(6, RoundingMode.HALF_UP));
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
								        		  detailFacture.setMontantTTC(( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).add((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA) )).setScale(6, RoundingMode.HALF_UP) );

									          }
								          
								          
								          }
								
								   }
								        	 
								       
								         //  detailFacture.setTypeVente(typevente);
								        
								           detailFacture.setFacturePF(facturePF);
								       //  detailFacture.setDateCreation(new Date());
								           
								           detailFacture.setUtilisateur(utilisateur);
								         
								           listDetailFacturePF.add(detailFacture);
								           stock=stockpf.getStock().subtract(QuantiteUnit.add(QuantitePaquet));
								           stockpf.setStock(stock);
								           listStockPF.add(stockpf);
								          stockpfDAO.edit(stockpf);
								           
								          if(promo==true)
								          {
								        	  ClientPF clientpf=mapClientPF.get(comboClientpf.getSelectedItem());
								        	   ////////////////////////////////////////////////////// Therres offre /////////////////////////////////////////////
							        		 
								        	  if(!txtquantitetherres.getText().equals(""))
								        	  {
								        		  
								        		  DetailFacturePF detailFactureTherres=new DetailFacturePF();
									        		 
									        		 detailFactureTherres.setArticle(articlesOffreTherres);
									        		 detailFactureTherres.setQuantite(new BigDecimal(txtquantitetherres.getText()));
									        		
									        			DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articlesOffreTherres.getId(), magasin.getId(),sousFamilleArticlePFOffreTherres.getFamileArticlePF().getId() , sousFamilleArticlePFOffreTherres.getId() , clientpf.getId());
				  	  		  				 			
					  	  		  				 		
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
									        		 detailFactureTherres.setSousFamille(sousFamilleArticlePFOffreTherres);
									        		 detailFactureTherres.setReduction(new BigDecimal(100));
									        		 detailFactureTherres.setMontantTTC(BigDecimal.ZERO);  
									        		 detailFactureTherres.setRemiseCommerciale(detailFactureTherres.getPrixUnitaire().multiply(detailFactureTherres.getQuantite()));
									        		 detailFactureTherres.setBrutHT(detailFactureTherres.getPrixUnitaire().multiply(detailFactureTherres.getQuantite()));
												         
												         
												         //  detailFacture.setTypeVente(typevente);
												        
									        		 detailFactureTherres.setFacturePF(facturePF);
												       //  detailFacture.setDateCreation(new Date());
												           
									        		 detailFactureTherres.setUtilisateur(utilisateur);
												         
												           listDetailFacturePF.add(detailFactureTherres);
												        
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
	                                                 DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articlesOffreVerres.getId(), magasin.getId(),sousFamilleArticlePFOffreVerres.getFamileArticlePF().getId() , sousFamilleArticlePFOffreVerres.getId() , clientpf.getId());
				  	  		  				 			
					  	  		  				 		
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
									        		 detailFactureVerres.setRemiseCommerciale(detailFactureVerres.getPrixUnitaire().multiply(detailFactureVerres.getQuantite()));
									        		 detailFactureVerres.setBrutHT(detailFactureVerres.getPrixUnitaire().multiply(detailFactureVerres.getQuantite()));
												         
												         
												         //  detailFacture.setTypeVente(typevente);
												        
									        		 detailFactureVerres.setFacturePF(facturePF);
												       //  detailFacture.setDateCreation(new Date());
												           
									        		 detailFactureVerres.setUtilisateur(utilisateur);
												         
												           listDetailFacturePF.add(detailFactureVerres);
												        
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
											        					  DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articlespromo.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , clientpf.getId());
									  	  		  				 			
										  	  		  				 		
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
											        					 detailFactureArticlePromo.setSousFamille(sousfamille);
											        					 detailFactureArticlePromo.setReduction(new BigDecimal(100));
											        					 detailFactureArticlePromo.setMontantTTC(BigDecimal.ZERO);  
											        					 detailFactureArticlePromo.setBrutHT(detailFactureArticlePromo.getQuantite().multiply(detailFactureArticlePromo.getPrixUnitaire()));
											        					 detailFactureArticlePromo.setRemiseCommerciale(detailFactureArticlePromo.getQuantite().multiply(detailFactureArticlePromo.getPrixUnitaire()));
																	         
																	         
																	         //  detailFacture.setTypeVente(typevente);
																	        
											        					 detailFactureArticlePromo.setFacturePF(facturePF);
																	       //  detailFacture.setDateCreation(new Date());
																	           
											        					 detailFactureArticlePromo.setUtilisateur(utilisateur);
																	         
																	           listDetailFacturePF.add(detailFactureArticlePromo);
																	        
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
									        BigDecimal montanttotalHT=BigDecimal.ZERO;
									        BigDecimal montanttotalTVA=BigDecimal.ZERO;
									        BigDecimal sommequantite=BigDecimal.ZERO;
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
									          if(detailFacturePF.getPrixUnitaire()!=BigDecimal.ZERO)
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
									  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
									  				labelmarge.setBackground(Color.red);
									  			 }else
									  			 {
									  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
									  				labelmarge.setBackground(Color.GREEN);
									  			 }
									  			 
									  				
									  			}
								  			
								  			/// ajout transfer stock PF (Mouvement Stock PF)
								  			DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
								  			detailTransferStockPF.setArticle(article);
								  			detailTransferStockPF.setSousFamille(sousfamille);
											detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
											detailTransferStockPF.setMagasinDestination(magasin);
											 if(checkttc.isSelected()==true)
											   {  if(!txtreduction.getText().equals(""))
										          {
												   
												   detailTransferStockPF.setPrixUnitaire(prixTTC.subtract(prixTTC.multiply( (new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))))  ;
												   
										          }else
										          {
										        	  detailTransferStockPF.setPrixUnitaire(prixTTC); 
										          }
									        	  
											   }else
											   {
												   if(!txtreduction.getText().equals(""))
											          {
													   
													   detailTransferStockPF.setPrixUnitaire(new BigDecimal(txtPrix.getText()).subtract(new BigDecimal(txtPrix.getText()).multiply( (new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  
											         
											          }else
											          {
											        	  detailTransferStockPF.setPrixUnitaire(new BigDecimal(txtPrix.getText()));  
											          }
												  
											   }
									          
											
											detailTransferStockPF.setQuantite(QuantiteUnit.add(QuantitePaquet));
											detailTransferStockPF.setTransferStockPF(transferStock);
											detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
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
					
					
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité , Le Prix et la Remise doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
			}
				
			
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(516, 416, 106, 23);
		  add(btnInitialiser);
		  btnInitialiser.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	
		  	    initialiser();
		  		
		  	}
		  });
		  btnInitialiser.setIcon(imgInit);
		  btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JButton button = new JButton("Initialiser");
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		initialiserFacture();
		  	
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(443, 168, 106, 23);
		  add(button);
		  
		   btnModifier = new JButton();
		   btnModifier.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		try {
		   			
		   			ClientPF clientpf=mapClientPF.get(comboClientpf.getSelectedItem());
		   			
		   			
		   			if(checkboxGratuits.isSelected()==true)
		   			{

				   		BigDecimal stock=BigDecimal.ZERO;
				   		BigDecimal stocktmp=BigDecimal.ZERO;
				   		BigDecimal QuantiteUnit=BigDecimal.ZERO;
						BigDecimal QuantitePaquet=BigDecimal.ZERO;
						
				   		if(tableArticle.getSelectedRow()!=-1)
				   		{
				   			boolean trouve=false;
							
				   		 if(dateChooserfacture.getDate()==null)
							{

								JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
								return;	
							}else if(comboArticle.getSelectedItem().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtcodearticle.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtquantite.getText().equals("") && txtquantitepacket.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)) ){
								
								
									JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								
								
							}else if(!txtquantitepacket.getText().equals("") && ((new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)<0)) ){
								
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
						        	 if(i!=tableArticle.getSelectedRow())
						        	 {
						        		 
						        		 DetailFacturePF detailFacturePF =listDetailFacturePF.get(i);
							        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle())  && detailFacturePF.getPrixUnitaire().equals(BigDecimal.ZERO) && detailFacturePF.getMontantHT().equals(BigDecimal.ZERO)
							        			 
								        			&&  detailFacturePF.getMontantTVA().equals(BigDecimal.ZERO) && detailFacturePF.getMontantTTC().equals(BigDecimal.ZERO) && detailFacturePF.getReduction().equals(BigDecimal.ZERO) && detailFacturePF.getTva().equals(BigDecimal.ZERO)
								        			   )
							        	 {
							        		 trouve=true;
							        	 }
							        	  
						        		 
						        	 }
						        	 
						        	 
						         }
						         
						         if(trouve==false)
						         {
						        	 
						        	 
						        	 StockPF stockpftmp =listStockPF.get(tableArticle.getSelectedRow());
						        	 
						        	 // Verifier le stock offre pour la gratuité du the promotion
					        		 if( listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  )
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
						        		 if(!txtquantitepacket.getText().equals(""))
						        		 {
						        			QuantitePaquet=new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()) ;
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
						        				 
						        				 stocktmp=stockpftmp.getStockOffre().subtract (listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());									        	 
									        	 stockpftmp.setStockOffre(stocktmp);
									        	 stockpfDAO.edit(stockpftmp);
						        				 
						        				 JOptionPane.showMessageDialog(null, "Stock Offre de "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
								        		 return;
						        			 }
						        			 
						        			 if(stockpf.getStockOffre().compareTo(QuantiteUnit.add(QuantitePaquet))<0)
								        	 {
						        				 stocktmp=stockpftmp.getStockOffre().subtract (listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());									        	 
									        	 stockpftmp.setStockOffre(stocktmp);
									        	 stockpfDAO.edit(stockpftmp);
						        				 
								        		 JOptionPane.showMessageDialog(null, "Stock Offre de "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
								        		 return;
								        	 } else
								        	 {

								        		 
									        	 DetailFacturePF detailFacture= listDetailFacturePF.get(tableArticle.getSelectedRow());
									        	 
									        	
										          
										         
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
											         
											       
										         //  detailFacture.setTypeVente(typevente);
										          
										           detailFacture.setFacturePF(facturePF);
										       //    detailFacture.setDateCreation(new Date());
										           
										           detailFacture.setUtilisateur(utilisateur);
										           stock=stockpf.getStockOffre().subtract(QuantiteUnit.add(QuantitePaquet));
										           stockpf.setStockOffre(stock);
										           stockpfDAO.edit(stockpf);
										         listStockPF.set(tableArticle.getSelectedRow(), stockpf);
										           listDetailFacturePF.set(tableArticle.getSelectedRow(), detailFacture);
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
												          if(detailFacturePF.getPrixUnitaire()!=BigDecimal.ZERO)
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
											  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
											  				labelmarge.setBackground(Color.red);
											  			 }else
											  			 {
											  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
											  				labelmarge.setBackground(Color.GREEN);
											  			 }
											  			 
											  				
											  			}
										  			
										  			// Modifier transfer Stock PF
										  		
										  			DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(tableArticle.getSelectedRow());
													
													detailTransferStockPF.setArticle(article);
													detailTransferStockPF.setSousFamille(sousfamille);
													detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
													detailTransferStockPF.setMagasinDestination(magasin);
													detailTransferStockPF.setPrixUnitaire(BigDecimal.ZERO);
													detailTransferStockPF.setQuantite(QuantiteUnit.add(QuantitePaquet));		
													detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
													listDetailTransferProduitFini.set(tableArticle.getSelectedRow(), detailTransferStockPF);
										  			
								        		initialiser();
								        	
								        	 
								        	 }
						        		 }else
						        		 {
						        			 DetailFacturePF detailFacture= listDetailFacturePF.get(tableArticle.getSelectedRow());
						        			 
						        			 if(detailFacture.getArticle().getId()==article.getId() && detailFacture.getSousFamille().getId()==sousfamille.getId() )
						        			 {
						        				 
						        				if(StockFinale.add(detailFacture.getQuantite()).compareTo((QuantiteUnit.add(QuantitePaquet)))<0 || stockpf.getStock().compareTo(QuantiteUnit.add(QuantitePaquet))<0 )
						        				{
						        					 stocktmp=stockpftmp.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
		        						        	 stockpftmp.setStock(stocktmp);
		        						        	 stockpfDAO.edit(stockpftmp);
						        					JOptionPane.showMessageDialog(null, "Stock "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
									        		 return;
						        					
						        				}
						        				 
						        				 
						        			 }else
						        			 {
						        				 
						        					if(StockFinale.compareTo((QuantiteUnit.add(QuantitePaquet)))<0 || stockpf.getStock().compareTo(QuantiteUnit.add(QuantitePaquet))<0)
							        				{
						        						 stocktmp=stockpftmp.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
			        						        	 stockpftmp.setStock(stocktmp);
			        						        	 stockpfDAO.edit(stockpftmp);
							        					 JOptionPane.showMessageDialog(null, "Stock "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
										        		 return;
							        					
							        				}
						        				 
						        			 }
						        			 
									        	
										         detailFacture.setReduction(new BigDecimal(100));  
										         
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
											       detailFacture.setSousFamille(sousfamille);
											      detailFacture.setMontantTTC(BigDecimal.ZERO);  
											      detailFacture.setBrutHT(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
											      detailFacture.setRemiseCommerciale(detailFacture.getPrixUnitaire().multiply(detailFacture.getQuantite()));
											         
											       
										         //  detailFacture.setTypeVente(typevente);
										          
										           detailFacture.setFacturePF(facturePF);
										       //    detailFacture.setDateCreation(new Date());
										           
										           detailFacture.setUtilisateur(utilisateur);
										           stock=stockpf.getStock().subtract(QuantiteUnit.add(QuantitePaquet));
										           stockpf.setStock(stock);
										           stockpfDAO.edit(stockpf);
										           listStockPF.set(tableArticle.getSelectedRow(), stockpf);
										           listDetailFacturePF.set(tableArticle.getSelectedRow(), detailFacture);
										        
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
												          if(detailFacturePF.getPrixUnitaire()!=BigDecimal.ZERO)
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
											  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
											  				labelmarge.setBackground(Color.red);
											  			 }else
											  			 {
											  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
											  				labelmarge.setBackground(Color.GREEN);
											  			 }
											  			 
											  				
											  			}
										  			
										  			// Modifier transfer Stock PF
										  			
										  			
										  			DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(tableArticle.getSelectedRow());
													
													detailTransferStockPF.setArticle(article);
													detailTransferStockPF.setSousFamille(sousfamille);
													detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
													detailTransferStockPF.setMagasinDestination(magasin);
													detailTransferStockPF.setPrixUnitaire(BigDecimal.ZERO);
													detailTransferStockPF.setQuantite(QuantiteUnit.add(QuantitePaquet));		
													detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
													listDetailTransferProduitFini.set(tableArticle.getSelectedRow(), detailTransferStockPF);
													afficher_tableDetailFacturePF(listDetailFacturePF);
								        		    initialiser();
								        	
								        	 
						        		 }
						        		 
						            	
						        		 
						        	 }else
						        	 {
						        		 
						        		 
						        		 if( listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) )
						        		 {
						        			 
						        			 stocktmp=stockpftmp.getStockOffre().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
								        	 
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
							
				   		 if(dateChooserfacture.getDate()==null)
							{

								JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
								return;	
							}else if(comboArticle.getSelectedItem().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtcodearticle.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtquantite.getText().equals("") && txtquantitepacket.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)) ){
								
								
									JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
									return;
								
								
							}else if(!txtquantitepacket.getText().equals("") && ((new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)<0)) ){
								
								
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
					       	
								
					       		if(tableArticle.getSelectedRow()-3>=0)
					       		{
					       			
					       			if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCodeArticle().equals(listDetailFacturePF.get(tableArticle.getSelectedRow()-3).getArticle().getCodeArticle()+"_PFO"))
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
							        	 if(detailFacturePF.getArticle().getLiblle().equals(article.getLiblle()) && detailFacturePF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()) && !detailFacturePF.getPrixUnitaire().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) )
							        	 {
							        		 trouve=true;
							        	 }
							        	  
						        		 
						        	 }
						        	 
						        	 
						         }
						         
						         if(trouve==false)
						         {
						        	 boolean offfrepromo =false ; 
						        	 
						        	 if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5()!=null)
			  				 			{
			  				 			// centrecout5 = article promotion ou non
			  				 				if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && !listDetailFacturePF.get(tableArticle.getSelectedRow()).getSousFamille(). getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM) )
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
			  				 				else 	if(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && magasin.getCode().equals(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING_PF) )
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
						        	 
						        	 
						        	 StockPF stockpftmp =listStockPF.get(tableArticle.getSelectedRow());
						        	 stocktmp=stockpftmp.getStock().add(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
						        	 
						        	 stockpftmp.setStock(stocktmp);
						        	 stockpfDAO.edit(stockpftmp);
						        	 
						        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasin.getId(),sousfamille.getId());
						        	 if(stockpf!=null)
						        	 {
						        		 if(!txtquantitepacket.getText().equals(""))
						        		 {
						        			QuantitePaquet=new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()) ;
						        		 }
						        		 if(!txtquantite.getText().equals(""))
						        		 {
						        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
						        		 }
						        		 
						        		 
						        		 DetailFacturePF detailFacture= listDetailFacturePF.get(tableArticle.getSelectedRow());
					        			 
					        			 if(detailFacture.getArticle().getId()==article.getId() && detailFacture.getSousFamille().getId()==sousfamille.getId() )
					        			 {
					        				 
					        				if(StockFinale.add(detailFacture.getQuantite()).compareTo((QuantiteUnit.add(QuantitePaquet)))<0 || stockpf.getStock().compareTo(QuantiteUnit.add(QuantitePaquet))<0)
					        				{
					        					
												
												  stocktmp=stockpftmp.getStock().subtract(listDetailFacturePF.get(
												  tableArticle.getSelectedRow()).getQuantite());
												  stockpftmp.setStock(stocktmp); 
												  stockpfDAO.edit(stockpftmp);
												 
					        					
					        					JOptionPane.showMessageDialog(null, "Stock "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
								        		 return;
					        					
					        				}
					        				 
					        				 
					        			 }else
					        			 {
					        				 
					        					if(StockFinale.compareTo((QuantiteUnit.add(QuantitePaquet)))<0 || stockpf.getStock().compareTo(QuantiteUnit.add(QuantitePaquet))<0)
						        				{
						        					
					        						 stocktmp=stockpftmp.getStock().subtract(listDetailFacturePF.get(tableArticle.getSelectedRow()).getQuantite());
										        	 stockpftmp.setStock(stocktmp);
										        	 stockpfDAO.edit(stockpftmp);
					        						
						        					JOptionPane.showMessageDialog(null, "Stock "+article.getLiblle()+" insufisant !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
									        		 return;
						        					
						        				}
					        				 
					        			 }
						        		
							        		 
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
											           detailFacture.setBrutHT (((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
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
												    		   detailFacture.setMontantHT(( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );
												    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
												    		   detailFacture.setTva(BigDecimal.ZERO);
												    		   detailFacture.setMontantTTC(( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );  

												    		   
														     }else
														     {
													    		   detailFacture.setRemiseCommerciale(( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP) );
													    		   detailFacture.setMontantHT(( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( ((prixTTC).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) ) ).setScale(6, RoundingMode.HALF_UP));
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
												    		   detailFacture.setRemiseCommerciale(( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP) );
												    		   detailFacture.setMontantHT( ((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );
												    		   detailFacture.setMontantTVA(BigDecimal.ZERO);
												    		   detailFacture.setTva(BigDecimal.ZERO);
												    		   detailFacture.setMontantTTC( ( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).subtract( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)) )).setScale(6, RoundingMode.HALF_UP) );  

												    		   
														     }else
														     {
														    	 
														    	 detailFacture.setRemiseCommerciale(( (new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))).setScale(6, RoundingMode.HALF_UP) );
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
															    	 detailFacture.setMontantHT((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
															    	 detailFacture.setMontantTVA(BigDecimal.ZERO);
															    	 detailFacture.setTva(BigDecimal.ZERO);
													        	  detailFacture.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));

															     }else
															     {
															    	 detailFacture.setRemiseCommerciale(BigDecimal.ZERO);
															    	 detailFacture.setMontantHT((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));
															    	 detailFacture.setMontantTVA( ((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet)))).setScale(6, RoundingMode.HALF_UP)).multiply(Constantes.TVA) ).setScale(6, RoundingMode.HALF_UP));
															    	 detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
															    	 detailFacture.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).add((new BigDecimal(txtPrix.getText()).multiply(QuantiteUnit.add(QuantitePaquet))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).setScale(6, RoundingMode.HALF_UP));

															     }
												         
												          }  
									        	  }
									        	 
									        	  
									         
									          
									         //  detailFacture.setTypeVente(typevente);
									          
									           detailFacture.setFacturePF(facturePF);
									       //    detailFacture.setDateCreation(new Date());
									           
									           detailFacture.setUtilisateur(utilisateur);
									           stock=stockpf.getStock().subtract(QuantiteUnit.add(QuantitePaquet));
									           stockpf.setStock(stock);
									          stockpfDAO.edit(stockpf);
									          listStockPF.set(tableArticle.getSelectedRow(), stockpf);
									           listDetailFacturePF.set(tableArticle.getSelectedRow(), detailFacture);
									       	DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(tableArticle.getSelectedRow());
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
											listDetailTransferProduitFini.set(tableArticle.getSelectedRow(), detailTransferStockPF);
											
											 if(offfrepromo==true)
								        	 {
												
												 boolean offre1=false , offre2=false;
												 
												 
												 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
												 {
													 
													 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
													 {
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
														 {
															 
															 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
															 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
															 listStockPF.remove(tableArticle.getSelectedRow()+1);
															 offre1=true;
														 }
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
														 {
															 
															 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
															 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
															 listStockPF.remove(tableArticle.getSelectedRow()+1);
															 offre1=true;
														 }
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
														 {
															 
															 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
															 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
															 listStockPF.remove(tableArticle.getSelectedRow()+1);
															 offre1=true;
														 }
														 
														 
														 
														 
													 }
													 
													 
												 }
												 
												 if(offre1==true)
												 {
													 
													 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
													 {
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
														 {
															 
															 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
															 {
																 
																 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
																 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
																 listStockPF.remove(tableArticle.getSelectedRow()+1);
																 offre2=true;
															 }
															 
															 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
															 {
																 
																 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
																 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
																 listStockPF.remove(tableArticle.getSelectedRow()+1);
																 offre2=true;
															 }
															 
															 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
															 {
																 
																 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
																 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
																 listStockPF.remove(tableArticle.getSelectedRow()+1);
																 offre2=true;
															 }
															 
															 
															 
															 
														 }
														 
														 
													 } 
													 
													 
													 
													 
												 }
												 
												 if(offre1==true && offre2==true)
												 {
													 
													 if(listDetailFacturePF.size()-1>=tableArticle.getSelectedRow()+1)
													 {
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1)!=null)
														 {
															 
															 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES))
															 {
																 
																 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
																 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
																 listStockPF.remove(tableArticle.getSelectedRow()+1);
																
															 }
															 
															 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
															 {
																 
																 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
																 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
																 listStockPF.remove(tableArticle.getSelectedRow()+1);
																 
															 }
															 
															 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO") && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
															 {
																 
																 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
																 listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
																 listStockPF.remove(tableArticle.getSelectedRow()+1);
																
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
		        		                      	DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articletherre.getId(), magasin.getId(),sousfamilleoffretherre.getFamileArticlePF().getId() , sousfamilleoffretherre.getId() , clientpf.getId());
				  	  		  				 			
					  	  		  				 		
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
													 detailFacturetherres.setRemiseCommerciale(detailFacturetherres.getPrixUnitaire().multiply(detailFacturetherres.getQuantite()));
													 detailFacturetherres.setBrutHT(detailFacturetherres.getPrixUnitaire().multiply(detailFacturetherres.getQuantite()));
													 detailFacturetherres.setSousFamille(sousfamilleoffretherre);
													 detailFacturetherres.setFacturePF(facturePF.getFacturePF());
													
													 listDetailFacturePF.add (detailFacturetherres);
													 
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
													
													 StockPF stockpfTherres=stockpfDAO.findStockByMagasinPFBySousFamille(articletherre.getId(), magasin.getId(), sousfamilleoffretherre.getId());
													 stockpfTherres.setStock(stockpfTherres.getStock().subtract(new BigDecimal(txtquantitetherres.getText())));
													 stockpfDAO.edit(stockpfTherres);
													 
													 listStockPF.add(stockpfTherres);
													 
													 
													 DetailFacturePF detailFacturePF=listDetailFacturePF.get(listDetailFacturePF.size()-1);
													 listDetailFacturePF.set(listDetailFacturePF.size()-1, listDetailFacturePF.get(tableArticle.getSelectedRow()+1));
													 listDetailFacturePF.set(tableArticle.getSelectedRow()+1, detailFacturePF);
													 
													 DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(listDetailTransferProduitFini.size()-1);
													 listDetailTransferProduitFini.set(listDetailTransferProduitFini.size()-1, listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+1));
													 listDetailTransferProduitFini.set(tableArticle.getSelectedRow()+1, detailTransferProduitFini);
													 
													 StockPF stockpfTherresTmp=listStockPF.get(listStockPF.size()-1);
													 listStockPF.set(listStockPF.size()-1, listStockPF.get(tableArticle.getSelectedRow()+1));
													 listStockPF.set(tableArticle.getSelectedRow()+1, stockpfTherresTmp);
													 
													 
												 }
												 
													
												 if(!txtquantiteverres.getText().equals(""))
												 {
													 Articles articleverres=mapArticleOffre.get(comboBoxverres.getSelectedItem());
													 SousFamilleArticlePF	sousfamilleoffreVerre=mapsousfamilleOffre.get(Constantes.SOUS_FAMILLE_OFFRE_VERRES);
													 DetailFacturePF detailFactureVerres= new DetailFacturePF();
													 detailFactureVerres.setQuantite(new BigDecimal(txtquantiteverres.getText()));
													 detailFactureVerres.setArticle(articleverres);
  	                                                    DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articleverres.getId(), magasin.getId(),sousfamilleoffreVerre.getFamileArticlePF().getId() , sousfamilleoffreVerre.getId() , clientpf.getId());
				  	  		  				 			
					  	  		  				 		
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
													 detailFactureVerres.setRemiseCommerciale(detailFactureVerres.getPrixUnitaire().multiply(detailFactureVerres.getQuantite()));
													 detailFactureVerres.setBrutHT(detailFactureVerres.getPrixUnitaire().multiply(detailFactureVerres.getQuantite()));
													 detailFactureVerres.setSousFamille(sousfamilleoffreVerre);
													 detailFactureVerres.setFacturePF(facturePF.getFacturePF());
													
													 listDetailFacturePF.add (detailFactureVerres);
													 
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
													
													 StockPF stockpfVerres=stockpfDAO.findStockByMagasinPFBySousFamille(articleverres.getId(), magasin.getId(), sousfamilleoffreVerre.getId());
													 stockpfVerres.setStock(stockpfVerres.getStock().subtract(new BigDecimal(txtquantiteverres.getText())));
													 stockpfDAO.edit(stockpfVerres);
													 listStockPF.add(stockpfVerres);
													 
													 if(!listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().contains("_PFO"))
													 {
														 
														 DetailFacturePF detailFacturePF=listDetailFacturePF.get(listDetailFacturePF.size()-1);
														 listDetailFacturePF.set(listDetailFacturePF.size()-1, listDetailFacturePF.get(tableArticle.getSelectedRow()+1));
														 listDetailFacturePF.set(tableArticle.getSelectedRow()+1, detailFacturePF);
														 
														 DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(listDetailTransferProduitFini.size()-1);
														 listDetailTransferProduitFini.set(listDetailTransferProduitFini.size()-1, listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+1));
														 listDetailTransferProduitFini.set(tableArticle.getSelectedRow()+1, detailTransferProduitFini); 
														 
														 StockPF stockpfVerresTmp=listStockPF.get(listStockPF.size()-1);
														 listStockPF.set(listStockPF.size()-1, listStockPF.get(tableArticle.getSelectedRow()+1));
														 listStockPF.set(tableArticle.getSelectedRow()+1, stockpfVerresTmp);
														 
														 
														 
														 
													 }else {
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2)!=null)
														 {
															 
															 
															 DetailFacturePF detailFacturePF=listDetailFacturePF.get(listDetailFacturePF.size()-1);
															 listDetailFacturePF.set(listDetailFacturePF.size()-1, listDetailFacturePF.get(tableArticle.getSelectedRow()+2));
															 listDetailFacturePF.set(tableArticle.getSelectedRow()+2, detailFacturePF);
															 
															 DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(listDetailTransferProduitFini.size()-1);
															 listDetailTransferProduitFini.set(listDetailTransferProduitFini.size()-1, listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+2));
															 listDetailTransferProduitFini.set(tableArticle.getSelectedRow()+2, detailTransferProduitFini);  
															 
															 StockPF stockpfVerresTmp=listStockPF.get(listStockPF.size()-1);
															 listStockPF.set(listStockPF.size()-1, listStockPF.get(tableArticle.getSelectedRow()+2));
															 listStockPF.set(tableArticle.getSelectedRow()+2, stockpfVerresTmp);
															 
															 
															 
														 }
														 
													 }
													 
													 
													
													 
												 }			
															
										
												 if(!txtquantitepromo.getText().equals(""))
												 {
													 
													 
													 Articles articlespromo=mapArticlePromo.get(comboBoxPromo.getSelectedItem());
													 
													 DetailFacturePF detailFacturepromo= new DetailFacturePF();
													 detailFacturepromo.setQuantite(new BigDecimal(txtquantitepromo.getText()));
													 detailFacturepromo.setArticle(articlespromo);
												      DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (articlespromo.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , clientpf.getId());
				  	  		  				 			
					  	  		  				 		
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
													 detailFacturepromo.setRemiseCommerciale(detailFacturepromo.getPrixUnitaire().multiply(detailFacturepromo.getQuantite()));
													 detailFacturepromo.setBrutHT(detailFacturepromo.getPrixUnitaire().multiply(detailFacturepromo.getQuantite()));
													 detailFacturepromo.setSousFamille(sousfamille);
													 detailFacturepromo.setFacturePF(facturePF.getFacturePF());
													
													 listDetailFacturePF.add (detailFacturepromo);
													 
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
													
													 StockPF stockpfpromo=stockpfDAO.findStockByMagasinPFBySousFamille(articlespromo.getId(), magasin.getId(), sousfamille.getId());
													 stockpfpromo.setStock(stockpfpromo.getStock().subtract(new BigDecimal(txtquantitepromo.getText())));
													 stockpfDAO.edit(stockpfpromo);
													 listStockPF.add(stockpfpromo);
													 
													 if(!listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+1).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
													 {
														 
														 DetailFacturePF detailFacturePF=listDetailFacturePF.get(listDetailFacturePF.size()-1);
														 listDetailFacturePF.set(listDetailFacturePF.size()-1, listDetailFacturePF.get(tableArticle.getSelectedRow()+1));
														 listDetailFacturePF.set(tableArticle.getSelectedRow()+1, detailFacturePF);
														 
														 DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(listDetailTransferProduitFini.size()-1);
														 listDetailTransferProduitFini.set(listDetailTransferProduitFini.size()-1, listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+1));
														 listDetailTransferProduitFini.set(tableArticle.getSelectedRow()+1, detailTransferProduitFini); 
														 
														 StockPF stockpfArticlePromoTmp=listStockPF.get(listStockPF.size()-1);
														 listStockPF.set(listStockPF.size()-1, listStockPF.get(tableArticle.getSelectedRow()+1));
														 listStockPF.set(tableArticle.getSelectedRow()+1, stockpfArticlePromoTmp);
														 
														 
														 
													 }else {
														 
														 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+2)!=null)
														 {
															 if(!listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_THERRES) && !listDetailFacturePF.get(tableArticle.getSelectedRow()+2).getArticle().getCodeArticle().equals(Constantes.OFFRE_VERRES))
															 {
																 
																 DetailFacturePF detailFacturePF=listDetailFacturePF.get(listDetailFacturePF.size()-1);
																 listDetailFacturePF.set(listDetailFacturePF.size()-1, listDetailFacturePF.get(tableArticle.getSelectedRow()+2));
																 listDetailFacturePF.set(tableArticle.getSelectedRow()+2, detailFacturePF);
																 
																 DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(listDetailTransferProduitFini.size()-1);
																 listDetailTransferProduitFini.set(listDetailTransferProduitFini.size()-1, listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+2));
																 listDetailTransferProduitFini.set(tableArticle.getSelectedRow()+2, detailTransferProduitFini);   
																 
																 StockPF stockpfArticlePromoTmp=listStockPF.get(listStockPF.size()-1);
																 listStockPF.set(listStockPF.size()-1, listStockPF.get(tableArticle.getSelectedRow()+2));
																 listStockPF.set(tableArticle.getSelectedRow()+2, stockpfArticlePromoTmp);
																 
															 }else
															 {
																 if(listDetailFacturePF.get(tableArticle.getSelectedRow()+3)!=null)
																 {
																	 
																	 DetailFacturePF detailFacturePF=listDetailFacturePF.get(listDetailFacturePF.size()-1);
																	 listDetailFacturePF.set(listDetailFacturePF.size()-1, listDetailFacturePF.get(tableArticle.getSelectedRow()+3));
																	 listDetailFacturePF.set(tableArticle.getSelectedRow()+3, detailFacturePF);
																	 
																	 DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(listDetailTransferProduitFini.size()-1);
																	 listDetailTransferProduitFini.set(listDetailTransferProduitFini.size()-1, listDetailTransferProduitFini.get(tableArticle.getSelectedRow()+3));
																	 listDetailTransferProduitFini.set(tableArticle.getSelectedRow()+3, detailTransferProduitFini); 
																	 
																	 
																	 StockPF stockpfArticlePromoTmp=listStockPF.get(listStockPF.size()-1);
																	 listStockPF.set(listStockPF.size()-1, listStockPF.get(tableArticle.getSelectedRow()+3));
																	 listStockPF.set(tableArticle.getSelectedRow()+3, stockpfArticlePromoTmp);
																	 
																 }
																 
																 
															 }
															 
															
															 
															 
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
										        	  StockPF stockpfTmp=stockpfDAO.findStockByMagasinPFBySousFamille(detailFacturePF.getArticle().getId(),magasin.getId(),detailFacturePF.getSousFamille().getId());
											          montanttotal=  montanttotal.add(detailFacturePF.getMontantTTC());
											          sommequantite= sommequantite.add(detailFacturePF.getQuantite());
											          montanttotalHT=montanttotalHT.add(detailFacturePF.getMontantHT());
											          montanttotalTVA=montanttotalTVA.add(detailFacturePF.getMontantTVA());
											          if(detailFacturePF.getPrixUnitaire()!=BigDecimal.ZERO)
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
										  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
										  				labelmarge.setBackground(Color.red);
										  			 }else
										  			 {
										  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
										  				labelmarge.setBackground(Color.GREEN);
										  			 }
										  			 
										  				
										  			}
									  			
									  			// Modifier transfer Stock PF
									  			
									  			
												
							        		    initialiser();
							        	
							        	
						        		 
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
		  		boolean trouve=false;
		  		
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
								 StockPF stockpftmp =stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getId(),magasin.getId(),listDetailFacturePF.get(tableArticle.getSelectedRow()).getSousFamille().getId());
								 if(checkboxGratuits.isSelected()==true)
						   			{
									 
									 // Verifier le stock offre pour la gratuité du the Promotion  
					        		 if( listDetailFacturePF.get(tableArticle.getSelectedRow()).getArticle().getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0)) )
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
							        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
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
				  	                listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
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
						     	                listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
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
	           					        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
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
	       		  	                listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
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
	       				     	                listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
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
				        	 listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
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
		  	                listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
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
				     	                listDetailFacturePF.remove(tableArticle.getSelectedRow()+1);
				     	                listDetailTransferProduitFini.remove(tableArticle.getSelectedRow()+1);
				     	                
				     	          	
				     	                
							        		} 
											
				        			 }
									 
			        			 }
					        		 
						

					        	
			        			 
			        		 }
					    	 
					    	 
					     }

					     				
					        	   
					                
					        	 }
					        	 
					        	 
					        	 
					        	 
					  		listDetailFacturePF.remove(tableArticle.getSelectedRow());
					  	
					  		 listStockPF.remove(tableArticle.getSelectedRow());
					  		
					  		listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
					  		
					  		
					  		
					  		
					  		
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
							          if(!detailFacturePF.getPrixUnitaire().equals(BigDecimal.ZERO))
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
						  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
						  				labelmarge.setBackground(Color.red);
						  			 }else
						  			 {
						  				labelmarge.setText("la Marge est :" +lamarge.divide(compteur, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)+"%");
						  				labelmarge.setBackground(Color.GREEN);
						  			 }
						  			 
						  				
						  			}
					  			JOptionPane.showMessageDialog(null, "Article supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					  			initialiser();
							}
		  			
		  		}
		  		
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1057, 543, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(779, 825, 105, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(903, 825, 134, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(662, 762, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(555, 762, 97, 26);
		add(lblTotalQuantite);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1057, 513, 73, 24);
		add(btnModifier);
		
		JLabel lblTotalMontantHt = new JLabel("Total Montant HT :");
		lblTotalMontantHt.setBounds(779, 763, 105, 26);
		add(lblTotalMontantHt);
		
		txttotalmontantHT = new JTextField();
		txttotalmontantHT.setEditable(false);
		txttotalmontantHT.setColumns(10);
		txttotalmontantHT.setBounds(903, 763, 134, 26);
		add(txttotalmontantHT);
		
		JLabel lblTotalMontantTva = new JLabel("Total Montant TVA :");
		lblTotalMontantTva.setBounds(779, 795, 105, 26);
		add(lblTotalMontantTva);
		
		txttotalmontantTVA = new JTextField();
		txttotalmontantTVA.setEditable(false);
		txttotalmontantTVA.setColumns(10);
		txttotalmontantTVA.setBounds(903, 795, 134, 26);
		add(txttotalmontantTVA);
		 
		  checkboxGratuits = new JCheckBox("Gratuit");
		 checkboxGratuits.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if(checkboxGratuits.isSelected()==true)
		 		{	
		 		labelpourcentage.setVisible(false);
		 		txtPrix.setVisible(false);
		 		txtreduction.setVisible(false);
		 		txtmontant.setVisible(false);
		 		lblMontant.setVisible(false);
		 		lblReduction.setVisible(false);	
		 		lblPrix.setVisible(false);	
		 		checkttc.setVisible(false);	
		 		comboArticle.setSelectedIndex(-1);
		 		comboArticle.removeAllItems();
		 		labelmarge.setText("");
    			labelpourcentage.setText("");
    			labelPrixMax.setText("");
    			labelprixmin.setText("");
    			checkboxSansTva.setSelected(false);
		 		initialiserGratuit();
		 		}else if(checkboxGratuits.isSelected()==false)
		 		{
		 			labelpourcentage.setVisible(false);
			 		txtPrix.setVisible(true);
			 		txtreduction.setVisible(true);
			 		txtmontant.setVisible(true);
			 		lblMontant.setVisible(true);
			 		lblReduction.setVisible(true);	
			 		lblPrix.setVisible(true);	
			 		checkttc.setVisible(true);	
			 		comboArticle.setSelectedIndex(-1);
			 		comboArticle.removeAllItems();
			 		labelmarge.setText("");
	    			labelpourcentage.setText("");
	    			labelPrixMax.setText("");
	    			labelprixmin.setText("");
		 		    initialiserGratuit();
		 		}
		 		
		 		
		 	}
		 });
		 checkboxGratuits.setBounds(1047, 243, 83, 24);
		 add(checkboxGratuits);
		
		                
 

		       		 
		 
				// stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
		     
		       		 
		       		   
		       		    layerArticle = new JLayeredPane();
		       		    layerArticle.setBounds(20, 243, 1021, 163);
		       		    add(layerArticle);
		       		    layerArticle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		              
		              // comboArticle.addItem("");
		               
		              
		                
               
		                JLayeredPane layerArticleGratuit = new JLayeredPane();
		                layerArticleGratuit.setBounds(0, 0, 1021, 163);
		                layerArticle.add(layerArticleGratuit);
		                layerArticleGratuit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	

		    		                
		    		                   combofamille = new JComboBox();
		    		                   combofamille.setBounds(91, 12, 167, 24);
		    		                   layerArticleGratuit.add(combofamille);
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
		    		                   combofamille.addItem("");
		    		                   
		    		                   txtquantite = new JTextField();
		    		                   txtquantite.setBounds(36, 56, 99, 26);
		    		                   layerArticleGratuit.add(txtquantite);
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
				    		                   								
				    		                   								if(!txtquantitepacket.getText().equals(""))
				    		    		                   					{
				    		    		                   						if(new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)>0)
				    		    		                   						{
				    		    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
				    				      					{
				    		    		                   								txtmontant.setText((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement())))+"");					
				    		    		                   								
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
		    		                   
		    		                    lblPrix = new JLabel("Prix  :");
		    		                    lblPrix.setBounds(322, 56, 36, 26);
		    		                    layerArticleGratuit.add(lblPrix);
		    		                    
		      
		    		                    
		    		                    JLabel labelFamilleArticle = new JLabel("Famille Article :");
		    		                    labelFamilleArticle.setBounds(0, 11, 87, 24);
		    		                    layerArticleGratuit.add(labelFamilleArticle);
		    		                    labelFamilleArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    		                    
		    		                    
		    		                    
		    		                    
		       
		       JLabel lblSousFamille = new JLabel("Sous Famille :");
		       lblSousFamille.setBounds(268, 11, 87, 24);
		       layerArticleGratuit.add(lblSousFamille);
		       lblSousFamille.setFont(new Font("Tahoma", Font.PLAIN, 12));
		       
		       
		
 		
		       
		       
		       
		       
		       
		           combosousfamille = new JComboBox();
		           combosousfamille.setBounds(342, 12, 167, 24);
		           layerArticleGratuit.add(combosousfamille);
		           
		           txtcodearticle = new JTextField();
		           txtcodearticle.setBounds(590, 11, 112, 26);
		           layerArticleGratuit.add(txtcodearticle);
		           util.Utils.copycoller(txtcodearticle);
		           txtcodearticle.addKeyListener(new KeyAdapter() {
		           	@Override
		           	public void keyPressed(KeyEvent e) {
		             			if(e.getKeyCode()==e.VK_ENTER)
  			      		{
		             				
		             					
  			      			if(!txtcodearticle.getText().equals(""))
  			      			{
  			      				//SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
  			      				Articles article=mapCodeArticle.get(txtcodearticle.getText().toUpperCase());
  					    		
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
		           
		             JLabel labellCodeArticle = new JLabel("Code Article :");
		             labellCodeArticle.setBounds(519, 11, 82, 26);
		             layerArticleGratuit.add(labellCodeArticle);
		             labellCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		             
		             JLabel labelarticle = new JLabel("Libelle :");
		             labelarticle.setBounds(712, 11, 57, 26);
		             layerArticleGratuit.add(labelarticle);
		             labelarticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		             
		             txtreduction = new JTextField();
		             txtreduction.setBounds(794, 56, 112, 26);
		             layerArticleGratuit.add(txtreduction);
		             txtreduction.setColumns(10);
		             
		              labelpourcentage = new JLabel("%");
		              labelpourcentage.setBounds(916, 56, 26, 26);
		              layerArticleGratuit.add(labelpourcentage);
		              labelpourcentage.setFont(new Font("Tahoma", Font.BOLD, 11));
		              
		               lblReduction = new JLabel("Remise :");
		               lblReduction.setBounds(736, 56, 57, 26);
		               layerArticleGratuit.add(lblReduction);
		               
		     
		               
		                lblMontant = new JLabel("Montant  :");
		                lblMontant.setBounds(527, 56, 64, 26);
		                layerArticleGratuit.add(lblMontant);
		                
		                txtmontant = new JTextField();
		                txtmontant.setBounds(601, 56, 125, 26);
		                layerArticleGratuit.add(txtmontant);
		                txtmontant.setEditable(false);
		                txtmontant.setColumns(10);
		                
		                txtPrix = new JTextField();
		                txtPrix.setBounds(368, 56, 135, 26);
		                layerArticleGratuit.add(txtPrix);
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
	    		                   								
	    		                   								if(!txtquantitepacket.getText().equals(""))
	    		    		                   					{
	    		    		                   						if(new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)>0)
	    		    		                   						{
	    		    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
	    				      					{
	    		    		                   								txtmontant.setText((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement())))+"");					
	    		    		                   								
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
			                   					if(!txtquantitepacket.getText().equals(""))
			                   					{
			                   						if(new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)>0)
			                   						{
			                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
		      					{
			                   								
			                   								if(!txtquantite.getText().equals(""))
			    		                   					{
			    		                   						if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
			    		                   						{
			    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
					      					{
			    		                   								txtmontant.setText((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement())))+"");					
			    		                   								
					      					}
			    		                   						}
			    		                   						}else
			    		                   						{
			    		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()))+"");
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
		                
		                JLabel lblQPacket = new JLabel("Q Packet :");
		                lblQPacket.setBounds(145, 56, 72, 26);
		                layerArticleGratuit.add(lblQPacket);
		                
		                txtquantitepacket = new JTextField();
		                txtquantitepacket.addKeyListener(new KeyAdapter() {
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
	    		                   					if(!txtquantitepacket.getText().equals(""))
	    		                   					{
	    		                   						if(new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)>0)
	    		                   						{
	    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
			      					{
	    		                   								
	    		                   								if(!txtquantite.getText().equals(""))
	    		    		                   					{
	    		    		                   						if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
	    		    		                   						{
	    		    		                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
	    				      					{
	    		    		                   								txtmontant.setText((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement())))+"");					
	    		    		                   								
	    				      					}
	    		    		                   						}}else
	    		    		                   						{
	    		    		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()))+"");
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
		                txtquantitepacket.setColumns(10);
		                txtquantitepacket.setBounds(200, 56, 112, 26);
		                layerArticleGratuit.add(txtquantitepacket);
		                
		                 
		                     	
		                      
		                      JLabel labelQuantit = new JLabel("QU :");
		                      labelQuantit.setBounds(4, 56, 42, 26);
		                      layerArticleGratuit.add(labelQuantit);
		                      // combofamille.addItem("");
		       
		                           
	
	
		                           
		    		                         
		                      
		                           
		                            comboArticle = new JComboBox();
		                            comboArticle.setBounds(755, 11, 258, 27);
		                            layerArticleGratuit.add(comboArticle);
		                            comboArticle.setSelectedIndex(-1);
		                            comboArticle.addActionListener(new ActionListener() {
		                            	public void actionPerformed(ActionEvent arg0) {
		                            		stockpfDAO.viderSessionStock();
		     	 		
  		     	 	if(comboArticle.getSelectedIndex()!=-1)
  			 		{
  			 			if(!comboArticle.getSelectedItem().equals(""))
  				 		{
  			 				
  			 				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
  				 			Articles article=mapArticle.get(comboArticle.getSelectedItem());
  				 			SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
  				 			BigDecimal PrixMin=BigDecimal.ZERO;
  				 			BigDecimal PrixMax=BigDecimal.ZERO;
  				 			SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
  				 			ClientPF clientpf=mapClientPF.get(comboClientpf.getSelectedItem());
  				 		 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasin.getId(),sousfamille.getId());
  				 			String date="01/01/"+sdf.format(dateChooserfacture.getDate())+"";
  				 			String dateFin="31/12/"+sdf.format(dateChooserfacture.getDate())+"";
  				 			Date dateDebut= new Date(date);
  				 			Date dateFinAnne= new Date(dateFin);
  				 		 StockFinale=BigDecimal.ZERO;
  				 		StockFinaleAnne=BigDecimal.ZERO;
  				 		List<Object[]> listestockfinale=listestockfinale=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateChooserfacture.getDate(), magasin, article, sousfamille);
			 			List<Object[]> listestockfinaleAnne=detailTransferStockPFdao.StockFinalePFParArticleSousFamille(dateDebut, dateFinAnne, magasin, article, sousfamille);

 			 			
			 			System.out.println("date :"+date);
  				 		System.out.println("date debut :"+dateDebut);
  				 		System.out.println("dateChooserfacture.getDate() :"+dateChooserfacture.getDate());
  				 			
  				 			for(int i=0;i<listestockfinale.size();i++)
  				 			{
  				 				
  				 			 Object[] object=listestockfinale.get(i);
  							
  				 			 if((int) object[0]==article.getId() && (int)object[1]==sousfamille.getId())
  				 			 {
  				 				StockFinale=(BigDecimal)object[2];
  				 			 }
  				 			 	
  				 			}
  				 			
  				 			for(int j=0;j<listDetailFacturePF.size();j++)
  				 			{
  				 				DetailFacturePF detailFacturePF=listDetailFacturePF.get(j);
  				 				if(detailFacturePF.getArticle().getId()==article.getId() && detailFacturePF.getSousFamille().getId()==sousfamille.getId())
  				 				{
  				 					
  				 					StockFinale=StockFinale.subtract(detailFacturePF.getQuantite());
  				 					
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
  				 					lbloffreverres.setVisible(true);
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
  				 					
  		  					
  		  				for(int j=0;j<listDetailFacturePF.size();j++)
				 			{
				 				DetailFacturePF detailFacturePF=listDetailFacturePF.get(j);
				 				if(detailFacturePF.getArticle().getId()==articlesTherresOffre.getId() && detailFacturePF.getSousFamille().getId()==sousFamilleArticlePFOffreTherres.getId())
				 				{
				 					
				 					stockfinaleTherres=stockfinaleTherres.subtract(detailFacturePF.getQuantite());
				 					
				 				}
				 				
				 				if(detailFacturePF.getArticle().getId()==articlesVerresOffre.getId() && detailFacturePF.getSousFamille().getId()==sousFamilleArticlePFOffreVerres.getId())
				 				{
				 					
				 					stockfinaleVerres=stockfinaleVerres.subtract(detailFacturePF.getQuantite());
				 					
				 				}
				 				
				 				
				 				
				 				
				 			}
  		  					
  				 					
  				 			stockdisponibleoffretherres.setText("<html>  Stock Offre Disponible de "+articlesTherresOffre.getLiblle()+" est : "+stockfinaleTherres.setScale(6, RoundingMode.HALF_UP) +"</html>");		
  				 			stockdisponibleoffreverres.setText( "<html>  Stock Offre Disponible de "+articlesVerresOffre.getLiblle()+" est : "+stockfinaleVerres.setScale(6, RoundingMode.HALF_UP)+"</html>");		
  				 				
  				 			
  				 			
  				 			
  				 			
  				 				
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
  				 					lbloffreverres.setVisible(true);
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
  				 					
  		  					
  		  				for(int j=0;j<listDetailFacturePF.size();j++)
				 			{
				 				DetailFacturePF detailFacturePF=listDetailFacturePF.get(j);
				 				if(detailFacturePF.getArticle().getId()==articlesTherresOffre.getId() && detailFacturePF.getSousFamille().getId()==sousFamilleArticlePFOffreTherres.getId())
				 				{
				 					
				 					stockfinaleTherres=stockfinaleTherres.subtract(detailFacturePF.getQuantite());
				 					
				 				}
				 				
				 				if(detailFacturePF.getArticle().getId()==articlesVerresOffre.getId() && detailFacturePF.getSousFamille().getId()==sousFamilleArticlePFOffreVerres.getId())
				 				{
				 					
				 					stockfinaleVerres=stockfinaleVerres.subtract(detailFacturePF.getQuantite());
				 					
				 				}
				 				
				 				
				 				
				 				
				 			}
  		  					
  				 					
  				 			stockdisponibleoffretherres.setText("<html>  Stock Offre Disponible de "+articlesTherresOffre.getLiblle()+" est : "+stockfinaleTherres.setScale(6, RoundingMode.HALF_UP) +"</html>");		
  				 			stockdisponibleoffreverres.setText( "<html>  Stock Offre Disponible de "+articlesVerresOffre.getLiblle()+" est : "+stockfinaleVerres.setScale(6, RoundingMode.HALF_UP)+"</html>");		
  				 				
  				 			
  				 			
  				 			
  				 			
  				 				
  				 				
  				 				}
  				 					
  				 				
  				 				else
  				 				{
  				 					lblOffreTherre.setVisible(false);
  				 					comboBoxtherres.setVisible(false);
  				 					txtquantitetherres.setVisible(false);
  				 					lbloffreverres.setVisible(false);
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
  				 			
  				 			
  				 			
  				 			
  				 			if(magasin!=null)
  				 			{
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
  				 						txtquantitepacket.setText("");
  				 						txtquantitepacket.setEnabled(true);
  				 					}else
  				 					{
  				 						txtquantitepacket.setText("");
  				 						txtquantitepacket.setEnabled(false);
  				 					}
  				 					
  				 					
  				 					
  	  				 				txtcodearticle.setText(article.getCodeArticle());
  	  				 				
  	  				 			 
  	  				 				
  	  	  				 			
  	  				 			DetailPrixArticle detailprixarticle=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamilleByClient (article.getId(), magasin.getId(),sousfamille.getFamileArticlePF().getId() , sousfamille.getId() , clientpf.getId());
  	  		  				 			
  	  		  				 		
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
  	  		  				 			 if(article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  && !sousfamille.getFamileArticlePF().getLiblle().equals(Constantes.LIBELLE_FAMILLE_ELNASS) && magasin.getCode().equals(Constantes.MAGASIN_PF_AHL_BRAHIM))
  						        		 {
  	  		  				 				 
  	  		  				 				if(stockpf.getStockOffre()!=null) 
  	  		  				 				{

  	  	  		  				 				PrixMin=stockpf.getPrixUnitaire().divide(new BigDecimal(0.2).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
  	  	    	  		  				 		PrixMax=stockpf.getPrixUnitaire().divide(new BigDecimal(0.3).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
  	  	    	  		  				 		labelprixmin.setText("Prix Minimum :"+" "+PrixMin);
  	  	    	  		  				 	    labelPrixMax.setText("Prix Maximum :"+" "+PrixMax);
  	  	    	  		  				 		
  	  	    	  		  				 	stockdisponible.setText("<html> Stock Offre Disponible de "+article.getLiblle()+" est : "+stockpf.getStockOffre().setScale(2, RoundingMode.HALF_UP)+"</html>");
  	  	  		  				 				
  	  		  				 					
  	  		  				 					
  	  		  				 					
  	  		  				 				}else
  	  		  				 				{
  	  		  				 					stockdisponible.setText("<html> Stock Offre de " +article.getLiblle()+" indisponible au "+magasin.getLibelle()+"</html>");
  	  		  				 					
  	  		  				 				}
  	  		  				 				 
  	  		  				 				 
  	  		  				 				 
  						        		 }else if (article.getCentreCout5().setScale(0).equals(BigDecimal.ONE.setScale(0))  &&  magasin.getCode().equals(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING_PF) )
  						        		 {

  	  		  				 				 
   	  		  				 				if(stockpf.getStockOffre()!=null) 
   	  		  				 				{

   	  	  		  				 				PrixMin=stockpf.getPrixUnitaire().divide(new BigDecimal(0.2).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
   	  	    	  		  				 		PrixMax=stockpf.getPrixUnitaire().divide(new BigDecimal(0.3).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
   	  	    	  		  				 		labelprixmin.setText("Prix Minimum :"+" "+PrixMin);
   	  	    	  		  				 	    labelPrixMax.setText("Prix Maximum :"+" "+PrixMax);
   	  	    	  		  				 		
   	  	    	  		  				 	stockdisponible.setText("<html> Stock Offre Disponible de "+article.getLiblle()+" est : "+stockpf.getStockOffre().setScale(2, RoundingMode.HALF_UP)+"</html>");
   	  	  		  				 				
   	  		  				 					
   	  		  				 					
   	  		  				 					
   	  		  				 				}else
   	  		  				 				{
   	  		  				 					stockdisponible.setText("<html> Stock Offre de " +article.getLiblle()+" indisponible au "+magasin.getLibelle()+"</html>");
   	  		  				 					
   	  		  				 				}
   	  		  				 				 
   	  		  				 				 
   	  		  				 				 
   						        		 
  						        		 }
  	  		  				 			 
  	  		  				 			 else
  						        		 {

   	  		  				 				PrixMin=stockpf.getPrixUnitaire().divide(new BigDecimal(0.2).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
   	    	  		  				 		PrixMax=stockpf.getPrixUnitaire().divide(new BigDecimal(0.3).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
   	    	  		  				 		labelprixmin.setText("Prix Minimum :"+" "+PrixMin);
   	    	  		  				 	labelPrixMax.setText("Prix Maximum :"+" "+PrixMax);
   	    	  		  				 		
   	    	  		  				 	stockdisponible.setText("<html> Stock Disponible de "+article.getLiblle()+" à ce jour est : "+StockFinale.setScale(6, RoundingMode.HALF_UP)+" Stock Finale est : "+StockFinaleAnne.setScale(6, RoundingMode.HALF_UP)+"</html>");
   	  		  				 				 
  						        		 }
  	  		  				 					
  	  		  				 					
  	  		  				 					
  	  		  				 				}else
  	  		  				 				{
  	  		  				 				PrixMin=stockpf.getPrixUnitaire().divide(new BigDecimal(0.2).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
  	    	  		  				 		PrixMax=stockpf.getPrixUnitaire().divide(new BigDecimal(0.3).subtract(new BigDecimal(1)),4, RoundingMode.HALF_UP).multiply(new BigDecimal(-1));
  	    	  		  				 		labelprixmin.setText("Prix Minimum :"+" "+PrixMin);
  	    	  		  				 	labelPrixMax.setText("Prix Maximum :"+" "+PrixMax);
  	    	  		  				 		
  	    	  		  				 	stockdisponible.setText("<html> Stock Disponible de "+article.getLiblle()+" à ce jour est : "+StockFinale.setScale(6, RoundingMode.HALF_UP) +" Stock Finale est : "+StockFinaleAnne.setScale(6, RoundingMode.HALF_UP)+"</html>");
  	  		  				 				}
  	  		  				 				
  	  		  				 			
  	  		  				 		 
  	  		  				 			}else
  	  		  				 			{
  	  		  				 			stockdisponible.setText(article.getLiblle()+" n'existe pas au magasin "+magasin.getLibelle());
  	  		  				 			}
  	  		  				 			
  	  		  				 	
  	  				 			}
  				 			}
  				 			
  				 					 			
  				 		}else
  				 		{
  				 			txtcodearticle.setText("");
  				 			stockdisponible.setText("");
  				 			
  				 		}
  				 	
  			 		}
  			 		
		                            	}
		                            });
		                            AutoCompleteDecorator.decorate(comboArticle);
		                            comboArticle.setSelectedIndex(-1);
		                            
		                             checkttc = new JCheckBox("TTC");
		                             checkttc.addActionListener(new ActionListener() {
		                             	public void actionPerformed(ActionEvent arg0) {
		                             		if(checkttc.isSelected()==true)
		                             		{
		                             			
		                             			if(!txtPrix.getText().equals(""))
		                             			{
		                             				prixTTC=new BigDecimal(txtPrix.getText());
		                             				if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) 
		                             				 {
		                             					
			                             				if(checkboxSansTva.isSelected()==true)
			                             				{
				                             				txtPrix.setText(new BigDecimal(txtPrix.getText()).setScale(2, RoundingMode.CEILING)+"");

			                             				}else
			                             				{
				                             				txtPrix.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(1.2)).setScale(2, RoundingMode.CEILING)+"");

			                             				}
		                             				 }
		                             			
		                             			}

		                             		}else if(checkttc.isSelected()==false)
		                             		{
		                             			if(!txtPrix.getText().equals(""))
		                             			{
		                             				if (!utilisateur.getCodeDepot().equals(CODE_DEPOT_ELALAOUI)) 
		                             				 {
		                             					if(checkboxSansTva.isSelected()==true)
			                             				{
			                             					
				                             				txtPrix.setText(new BigDecimal(txtPrix.getText()).setScale(2, RoundingMode.CEILING)+"");
			                             					
			                             				}else
			                             				{
				                             				txtPrix.setText(new BigDecimal(txtPrix.getText()).divide(new BigDecimal(1.2),2, RoundingMode.CEILING)+"");

			                             				}
		                             				 }
		                             			
		                             			}
		                             		}
		                             		
		                             	}
		                             });
		                            checkttc.setBounds(948, 58, 67, 24);
		                            layerArticleGratuit.add(checkttc);
		                            
		                             comboBoxtherres = new JComboBox();
		                            comboBoxtherres.setSelectedIndex(-1);
		                            comboBoxtherres.setBounds(79, 94, 253, 27);
		                            layerArticleGratuit.add(comboBoxtherres);
		                            
		                            comboBoxtherres.setVisible(false);
		                            
		                            txtquantitetherres = new JTextField();
		                            txtquantitetherres.setColumns(10);
		                            txtquantitetherres.setBounds(342, 93, 110, 29);
		                            layerArticleGratuit.add(txtquantitetherres);
		                            
		                            txtquantitetherres.setVisible(false);
		                            
		                             comboBoxverres = new JComboBox();
		                            comboBoxverres.setSelectedIndex(-1);
		                            comboBoxverres.setBounds(547, 93, 266, 27);
		                            layerArticleGratuit.add(comboBoxverres);
		                            
		                            comboBoxverres.setVisible(false);
		                            
		                            txtquantiteverres = new JTextField();
		                            txtquantiteverres.setColumns(10);
		                            txtquantiteverres.setBounds(823, 93, 99, 29);
		                            layerArticleGratuit.add(txtquantiteverres);
		                            
		                            txtquantiteverres.setVisible(false);
		                            
		                             lblOffreTherre = new JLabel("Offre Therre :");
		                            lblOffreTherre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		                            lblOffreTherre.setBounds(0, 94, 87, 24);
		                            layerArticleGratuit.add(lblOffreTherre);
		                            lblOffreTherre.setVisible(false);
		                             lbloffreverres = new JLabel("Offre Verres :");
		                            lbloffreverres.setFont(new Font("Tahoma", Font.PLAIN, 12));
		                            lbloffreverres.setBounds(462, 94, 87, 24);
		                            layerArticleGratuit.add(lbloffreverres);
		                            
		                             lblOffrePromo = new JLabel("Offre Promo :");
		                            lblOffrePromo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		                            lblOffrePromo.setBounds(0, 129, 87, 24);
		                            layerArticleGratuit.add(lblOffrePromo);
		                            lblOffrePromo.setVisible(false);
		                             comboBoxPromo = new JComboBox();
		                            comboBoxPromo.setSelectedIndex(-1);
		                            comboBoxPromo.setBounds(79, 129, 253, 24);
		                            layerArticleGratuit.add(comboBoxPromo);
		                            comboBoxPromo.setVisible(false);
		                            txtquantitepromo = new JTextField();
		                            txtquantitepromo.setColumns(10);
		                            txtquantitepromo.setBounds(342, 129, 110, 26);
		                            layerArticleGratuit.add(txtquantitepromo);
		                            txtquantitepromo.setVisible(false);
		                             stockdisponible = new JLabel("");
		                             stockdisponible.setHorizontalAlignment(SwingConstants.LEFT);
		                             stockdisponible.setFont(new Font("Tahoma", Font.BOLD, 9));
		                             stockdisponible.setForeground(Color.RED);
		                            stockdisponible.setBounds(1057, 289, 475, 69);
		                            add(stockdisponible);
		                            
		                             labelmarge = new JLabel("");
		                            labelmarge.setForeground(Color.RED);
		                            labelmarge.setFont(new Font("Tahoma", Font.BOLD, 14));
		                            labelmarge.setBounds(1047, 299, 600, 30);
		                            add(labelmarge);
		                            
		                             labelprixmin = new JLabel("");
		                            labelprixmin.setForeground(new Color(50, 205, 50));
		                            labelprixmin.setFont(new Font("Tahoma", Font.BOLD, 13));
		                            labelprixmin.setBounds(1136, 213, 193, 38);
		                            add(labelprixmin);
		                            
		                             labelPrixMax = new JLabel("");
		                            labelPrixMax.setFont(new Font("Tahoma", Font.BOLD, 13));
		                            labelPrixMax.setForeground(new Color(255, 0, 0));
		                            labelPrixMax.setBounds(1339, 213, 193, 38);
		                            add(labelPrixMax);
		                            
		                             stockdisponibleoffretherres = new JLabel("");
		                            stockdisponibleoffretherres.setHorizontalAlignment(SwingConstants.LEFT);
		                            stockdisponibleoffretherres.setForeground(Color.RED);
		                            stockdisponibleoffretherres.setFont(new Font("Tahoma", Font.BOLD, 9));
		                            stockdisponibleoffretherres.setBounds(1047, 328, 600, 30);
		                            add(stockdisponibleoffretherres);
		                            
		                             stockdisponibleoffreverres = new JLabel("");
		                            stockdisponibleoffreverres.setHorizontalAlignment(SwingConstants.LEFT);
		                            stockdisponibleoffreverres.setForeground(Color.RED);
		                            stockdisponibleoffreverres.setFont(new Font("Tahoma", Font.BOLD, 9));
		                            stockdisponibleoffreverres.setBounds(1044, 385, 613, 30);
		                            add(stockdisponibleoffreverres);
		                            
		                             stockdisponiblearticlepromo = new JLabel("");
		                            stockdisponiblearticlepromo.setHorizontalAlignment(SwingConstants.LEFT);
		                            stockdisponiblearticlepromo.setForeground(Color.RED);
		                            stockdisponiblearticlepromo.setFont(new Font("Tahoma", Font.BOLD, 9));
		                            stockdisponiblearticlepromo.setBounds(1047, 411, 600, 30);
		                            add(stockdisponiblearticlepromo);
		                            
		                            JButton btnAjouterDetailTransfer = new JButton("Regler Montant HT TVA TTC");
		                            btnAjouterDetailTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String req="";
				req=req+" c.dateFacture between '2023-12-01' and '2023-12-31' and c.magasin.id=7 ";
			 listFacturePF=facturepfdao.findFacturePFByRequete(req);
			
			 
			
			 for(int i=0;i<listFacturePF.size();i++)
			 {
				 FacturePF facture=listFacturePF.get(i);
				 
				listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(facture.getId()) ;
				
				BigDecimal montantht=BigDecimal.ZERO;
				BigDecimal montanttva=BigDecimal.ZERO;
				BigDecimal montantttc=BigDecimal.ZERO;
				for(int j=0;j<listDetailFacturePF.size();j++)
				{
					
				DetailFacturePF detailFacturePF=listDetailFacturePF.get(j);
				
				montantht=montantht.add(detailFacturePF.getMontantHT());
				montanttva=montanttva.add(detailFacturePF.getMontantTVA());
				montantttc=montantttc.add(detailFacturePF.getMontantTTC());
					
				}
				facture.setMontantHT(montantht);
				facture.setMontantTVA(montanttva);
				facture.setMontantTTC(montantttc);
				facturepfdao.edit(facture);
				 
				 
			 }
				
			
			
			}
		                            });
		                            btnAjouterDetailTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		                            btnAjouterDetailTransfer.setBounds(35, 837, 179, 24);
		                            add(btnAjouterDetailTransfer);
		                            
		                             checkboxSansTva = new JCheckBox("Sans TVA");
		                            checkboxSansTva.setBounds(1047, 217, 83, 24);
		                            add(checkboxSansTva);
		                            
		                            JButton button_1 = new JButton("Lire Fichier excel");
		                            button_1.addActionListener(new ActionListener() {
		                            	public void actionPerformed(ActionEvent arg0) {
		                            		
		                            		
		                            		if(!txtlien.getText().equals(""))
							  				{
							  					
							  					 File fileName = new File(txtlien.getText());
									  				
								  		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							  					 
							  		              

							  				   try {
							  					   
							  						
							  						
							  				       FileInputStream ExcelFile = new FileInputStream(fileName);
							  				      
							  				       ExcelWBook = new XSSFWorkbook(ExcelFile);
							  						
							  								
							  								ExcelWSheet = ExcelWBook.getSheet("VENT");
							  								 
							  								
							  								
							  				///////////////////////////////////////////////////////////////// Comparer les Articles et verifier les cellules si il sont vide //////////////////////////////////////////////////////////////////
							  								
							  								 Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
							  								 
							  								   Boolean exist=false;
							  								   BigDecimal Montant=BigDecimal.ZERO;
							  								   BigDecimal Quantite=BigDecimal.ZERO;
							  								   Depot depot=mapDepot.get(combodepot.getSelectedItem());
							  									DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							  								   if(depot==null)
							  								   {
							  									   
							  									   JOptionPane.showMessageDialog(null, "Veuillez Selectionner le depot SVP");
							  									   return;
							  									   
							  								   }
							  								   
							  								 listFacturePF=facturepfdao.findByDepot(depot.getId());
							  								 
							  								 listFactureServiceProduction=factureServiceProductiondao.findAll();

							  								 boolean validation=false;
							  								 boolean validationService=false;
							  								 
							  								
							  								   for(int k=3;k<12;k++)
							  								   {
							  								   	Quantite=BigDecimal.ZERO;
							  								   	CellOldNumFacture=ExcelWSheet.getRow(k).getCell(5);
							  								  Date dateFacture=ExcelWSheet.getRow(k).getCell(2).getDateCellValue();
							  								   	CellNewNumFacture=ExcelWSheet.getRow(k).getCell(1);
							  								   	
							  								  DataFormatter formatter = new DataFormatter(Locale.US);
							  								  exist=false;
							  								   	
							  								   	
							  								   	for(int i=0;i<listFacturePF.size();i++)
							  								   	{	
							  								   		
							  								   		FacturePF facturePF=listFacturePF.get(i);
							  								   		
							  								   		
							  								   		if(!CellOldNumFacture.equals(""))
							  								   		{
							  								   		
							  								   		   	if(String.valueOf( facturePF.getId()).equals(formatter.formatCellValue(CellOldNumFacture))  )
							  										   	{
							  								   		   	exist=true;
							  								   		   	
							  								   		   	System.out.println(CellOldNumFacture +" ***** "+CellNewNumFacture+" ******* "+dateFacture);
							  								   		   	
							  									   		
							  								   		   		
							  					facturePF.setNumFacture(CellNewNumFacture.toString());
							  					facturePF.setDateFacture(dateFacture);	   			
							  								   	
							  						facturepfdao.edit(facturePF);		   			
							  									
							  						
							  						TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(facturePF.getCodeTransfer());			
							  						if (transferStockPF != null) 
							  						{
							  						transferStockPF.setDateTransfer(dateFacture);
							  						transferStockPFDAO.edit(transferStockPF);	
							  									
							  						listDetailTransferStockPF.clear();			
							  						listDetailTransferStockPF=detailTransferStockPFdao.findByTransferStockPF(transferStockPF.getId())	;	
							  							for(int d=0;d<listDetailTransferStockPF.size();d++)
							  							{
							  							
							  								DetailTransferProduitFini detailTransferProduitFini=listDetailTransferStockPF.get(d);
							  								detailTransferProduitFini.setDateTransfer(dateFacture);
							  								
							  								detailTransferStockPFdao.edit(detailTransferProduitFini);
							  								
							  								
							  								
							  							}
							  						
							  						}
							  						
							  						
							  						validation=true;
							  										   	
							  										   		
							  										   		
							  										   	}	
							  								   			
							  								   		}
							  								   		
							  							
							  								   		
							  								   	}
							  								   	
							  			
							  								   	
							  								   	

							  								   	
							  								   	
							  								   	
							  								   }
							  								   
							  								   
							  								 if(validation==true)	
										  						{
										  							
										  							JOptionPane.showMessageDialog(null, "la Modification Des Ventes à été effcetué avec Succee ");
										  							
										  						}
							  								   
							  								
							  								  
							  								   
	////////////////////////////////////////////////////////////////////////////  Factures Service   /////////////////////////////////////////////////////////////////////////////////////////////////						  								   
							  								 /*
							  									ExcelWSheet = ExcelWBook.getSheet("SERVICE");
							  								   
							  								   for(int k=2;k<6;k++)
							  								   {
							  								   	Quantite=BigDecimal.ZERO;
							  								   	CellOldNumFacture=ExcelWSheet.getRow(k).getCell(5);
							  								  Date dateFacture=ExcelWSheet.getRow(k).getCell(2).getDateCellValue();
							  								   	CellNewNumFacture=ExcelWSheet.getRow(k).getCell(1);
							  								   	
							  								  DataFormatter formatter = new DataFormatter(Locale.US);
							  								  
						  										
						  										for(int i=0;i<listFactureServiceProduction.size();i++)
							  								   	{	
							  								   		
							  								   		FactureServiceProduction factureServiceProduction=listFactureServiceProduction.get(i);
							  								   		
							  								   		
							  								   		if(!CellOldNumFacture.equals(""))
							  								   		{
							  								   		
							  								   			
							  								   			
							  								   			
							  								   			System.out.println("ID Facture Service : "+CellOldNumFacture);
							  								   			
							  								   			
							  								   			
							  								   		   	if(String.valueOf( factureServiceProduction.getId()).equals(formatter.formatCellValue(CellOldNumFacture))  )
							  										   	{
							  								   		   		
							  								   		   	exist=true;
							  								   		   	
							  								   		   	System.out.println(CellOldNumFacture +" ***** "+CellNewNumFacture+" ******* "+dateFacture);
							  								   		   	
							  									   		
////////////////////////////////////////////////////////////////////////////////////////////////////////////  Modifier date et Num Facture Service   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							  								  
							  								   		   	factureServiceProduction.setNumFacture(CellNewNumFacture.toString());   	
							  								   	factureServiceProduction.setDateFacture(dateFacture);
							  								   		   	factureServiceProductiondao.edit(factureServiceProduction);
							  								   		   	
////////////////////////////////////////////////////////////////////////////////////////////////////////////  Modifier date production   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							  								   		   	
						Production production=productionDAO.findByNumOF(factureServiceProduction.getNumOF(), factureServiceProduction.getDepot().getCode())	;  								   		   	
						production.setDate(dateFacture);	
						production.setDate_debFabPre(dateFacture);
						production.setDateFinFabPre(dateFacture);
						production.setNumFacture(CellNewNumFacture.toString());
						productionDAO.edit(production);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////  Modifier date transfert MP      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		   		TransferStockMP transfererStockMP=transferStockMPDAO.findTransferByCode(factureServiceProduction.getNumOF());

		   		transfererStockMP.setDateTransfer(dateFacture);							   	
						transferStockMPDAO.edit(transfererStockMP);
						
						int j=	transfererStockMP.getId();
						j=j+1;
						boolean existe=true;
						
						while(existe==true)
						{
							
							TransferStockMP transferStockMPTmp = transferStockMPDAO.findById(j);
							
							if(transferStockMPTmp!=null)
							{
								if(transferStockMPTmp.getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE) || transferStockMPTmp.getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF))
								{
									
									
									transferStockMPTmp.setDateTransfer(dateFacture);
									transferStockMPDAO.edit(transferStockMPTmp);
									
									existe=true;
									
								}else
								{
									existe=false;
								}
								
							}else
							{
								existe=false;
							}
							
							j=j+1;
							
						}	
						
						
						
////////////////////////////////////////////////////////////////////////////////////////////////////////////////  Modifier date transfert PF      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
			TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(factureServiceProduction.getNumOF());			
			if (transferStockPF != null) 
			{
			transferStockPF.setDateTransfer(dateFacture);
			transferStockPFDAO.edit(transferStockPF);	
						
			listDetailTransferStockPF.clear();			
			listDetailTransferStockPF=detailTransferStockPFdao.findByTransferStockPF(transferStockPF.getId())	;	
				for(int d=0;d<listDetailTransferStockPF.size();d++)
				{
				
					DetailTransferProduitFini detailTransferProduitFini=listDetailTransferStockPF.get(d);
					detailTransferProduitFini.setDateTransfer(dateFacture);
					
					detailTransferStockPFdao.edit(detailTransferProduitFini);
					
					
					
				}
					
				
				
					
					
					int t=	transferStockPF.getId();
					t=t+1;
					boolean trouve=true;	
						
						while(trouve==true)
						{
							
							TransferStockPF transferStockPFTmp = transferStockPFDAO.findById(t);	
							
							if(transferStockPFTmp!=null)
								
							{
								
								if(transferStockPFTmp.getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP))
								{
									
									transferStockPFTmp.setDateTransfer(dateFacture);
									
									
									listDetailTransferStockPF.clear();			
									listDetailTransferStockPF=detailTransferStockPFdao.findByTransferStockPF(transferStockPFTmp.getId())	;	
										for(int d=0;d<listDetailTransferStockPF.size();d++)
										{
										
											DetailTransferProduitFini detailTransferProduitFini=listDetailTransferStockPF.get(d);
											detailTransferProduitFini.setDateTransfer(dateFacture);
											
											detailTransferStockPFdao.edit(detailTransferProduitFini);
											
											
											
										}
										
										transferStockPFDAO.edit(transferStockPFTmp);		
									
								
									
										trouve=true;
									
									
									
								}else
								{
									
									trouve=false;
									
								}
								
						
								
								
							}else
							{
								trouve=false;
								
							}
							
							
							
							t=t+1;
							
						}
						
						
						
						
						
						
						
						
						
						

					
					}
				
				
				
				
				
				
				
						
						
						
			validationService=true;
						
						
						
							  										   		
							  										   	}	
							  								   			
							  								   		}
							  								   		
							  							
							  								   		
							  								   	}
						  										
						  										
						  										
						  									
							  								  
							  								   	
							  								   	
							  								  
							  								   	
							  			
							  								   	
							  								   	

							  								   	
							  								   	
							  								   	
							  								   }							   
							  								   
							  								   
							  								  
							  								*/   
							  								   
							  								   
							  								   
							  								   
							  								
							  									
							  									
							  									
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				  								   
							  								   
							  								   
							  								   
							  						if(validationService==true)	
							  						{
							  							
							  							JOptionPane.showMessageDialog(null, "la Modification Des Service à été effcetué avec Succee ");
							  							
							  						}
							  						
							  								   
							  								   
							  					
							  				       
							  				       
							  				   } catch (Exception e) {
							  					 JOptionPane.showMessageDialog(null, e.toString()); 
							  				   }


							  					 
							  			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		 
									  			
									  				
							  					
							  				}
		                            		
		                            		
		                            		
		                            	}
		                            });
		                            button_1.setBounds(216, 911, 129, 36);
		                            add(button_1);
		                            button_1.setVisible(false);
		                            txtlien = new JTextField();
		                            txtlien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		                            txtlien.setEditable(false);
		                            txtlien.setColumns(10);
		                            txtlien.setBounds(395, 911, 459, 36);
		                            add(txtlien);
		                            txtlien.setVisible(false);
		                            final JFileChooser  fileDialog = new JFileChooser();
		                            JButton button_2 = new JButton("Ouvrir");
		                            button_2.addActionListener(new ActionListener() {
		                            	public void actionPerformed(ActionEvent arg0) {
		                            		
		                            		

							  				
							  				 int returnVal = fileDialog.showOpenDialog(mainFrame);
							  	            
							  				java.io.File file = fileDialog.getSelectedFile();     
							  				
							  				txtlien.setText(file.getAbsolutePath());
							  				
							  				
							  				
							  			
		                            		
		                            		
		                            	}
		                            });
		                            button_2.setBounds(864, 911, 89, 30);
		                            add(button_2);
		                            
		                             chckbxPiece = new JCheckBox("Piece");
		                            chckbxPiece.setBounds(1057, 590, 83, 24);
		                            add(chckbxPiece);
		                            button_2.setVisible(false);
		                            btnAjouterDetailTransfer.setVisible(false);
		           
		            combosousfamille.addItemListener(new ItemListener() {
		            	@SuppressWarnings("unchecked")
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
				        						/*
				        						if(!article.getLiblle().contains("(OFFRE)"))
				        						{
				        						*/	
						        					comboArticle.addItem(article.getLiblle());
						        					 mapArticle.put(article.getLiblle(), article);
						        					 mapCodeArticle.put(article.getCodeArticle(), article);
				        							
				        						/*	
				        						}*/
				        						
				        						
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
		    		                  

		     	   int p=0;
				      while(p<listFamilleArticle.size())
				      {
				    	  
				    	  FamilleArticlePF famillearticle=listFamilleArticle.get(p);
				    	  combofamille.addItem(famillearticle.getLiblle());
				    	 
				    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
				    	  
				    	  
				    	  
				    	  
				    	  p++;
				      }	
				      
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
				      
				      
				      
				      
		    				 		
		    		/*
				      listArticleStockPF=articleDAO.findAll();
		                
		                for(int j=0;j<listArticleStockPF.size();j++)
		       		 {
		       			Articles article= listArticleStockPF.get(j);
		       			comboArticle.addItem(article.getLiblle());
		       			comboArticleGratuit.addItem(article.getLiblle());
		       			 mapArticle.put(article.getLiblle(), article);
		       			 mapCodeArticle.put(article.getCodeArticle(), article);
		       		 }
		    		*/   
				      
				      
				      
				      comboTypeBl.addItem("");
				      for(int i=0;i<listTypeBL.size();i++)
				      {
				    	TypeBL typeBL=  listTypeBL.get(i);
				    	
				    	mapTypeBL.put(typeBL.getType(), typeBL);
				    	 comboTypeBl.addItem(typeBL.getType());
				    	
				    	
				      }   
				      
				      
				      
				      
				      
				      
		    		                
	}
	

	
	void initialiserFacture()
	{
		dateChooserfacture.setCalendar(null);
		dateChooserBCommande.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);
		comboClientpf.setSelectedIndex(-1);
		txttotalmontantTTC.setText("");
		txttotalquantite.setText("");
		txttotalmontantHT.setText("");
		txttotalmontantTVA.setText("");
		comboFournisseur.setSelectedIndex(-1);
		txtnumBL.setText("");
		txtnumCommande.setText("");
		comboTypeBl.setSelectedItem(""); 
		
		
	}

	void initialiser()
	{
		 
		txtcodearticle.setText("");
		comboArticle.setSelectedIndex(-1);
		//combotypevente.setSelectedIndex(-1);
	   txtPrix.setText("");
		txtquantite.setText("");
		txtmontant.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	     combofamille.setSelectedItem("");
	     combosousfamille.setSelectedIndex(-1);
	  checkboxGratuits.setSelected(false);
	  txtquantitepacket.setText("");
	  txtquantitepacket.setEnabled(false);
	checkttc.setSelected(false);
	stockdisponible.setText("");
	labelPrixMax.setText("");
	labelprixmin.setText("");
	labelpourcentage.setVisible(false);
		
		txtPrix.setVisible(true);
		txtreduction.setVisible(true);
		txtmontant.setVisible(true);
		lblMontant.setVisible(true);
		lblReduction.setVisible(true);	
		lblPrix.setVisible(true);	
		checkttc.setVisible(true);
	
	lblOffreTherre.setVisible(false);
	comboBoxtherres.setVisible(false);
	txtquantitetherres.setVisible(false);
	lbloffreverres.setVisible(false);
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
	void initialiserGratuit()
	{
		 
		txtcodearticle.setText("");
		comboArticle.setSelectedIndex(-1);
		//combotypevente.setSelectedIndex(-1);
	   txtPrix.setText("");
		txtquantite.setText("");
		txtmontant.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	     combofamille.setSelectedItem("");
	     combosousfamille.setSelectedIndex(-1);
	 
	}
	
	void InitialiseTableau()
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleChargefixe);
		 tableArticle.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableArticle.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableArticle.getColumnModel().getColumn(2).setPreferredWidth(94);
		
	
}
	
	
	void afficher_tableDetailFacturePF(List<DetailFacturePF> listDetailFacture)
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Reduction %", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleChargefixe);
		int i=0;
		 
		while(i<listDetailFacture.size())
		{	
		DetailFacturePF detailfacturepf=listDetailFacture.get(i);
			
			Object []ligne={detailfacturepf.getSousFamille().getFamileArticlePF().getLiblle(),detailfacturepf.getSousFamille().getLiblle(), detailfacturepf.getArticle().getLiblle(),detailfacturepf.getPrixUnitaire(),detailfacturepf.getQuantite(),detailfacturepf.getReduction(), detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(), detailfacturepf.getMontantTTC()};

			modeleChargefixe.addRow(ligne);
			i++;
		}
}
	
	/// Modifier MonatntTTC , MonatntHT , MonatntTVA de la Facture egale la somme de MonatntTTC , MonatntHT , MonatntTVA de detailFacturePF
	
	void modifierttcFacturePF()
	{

		 List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
		 List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
		 Depot depot=mapDepot.get(combodepot.getSelectedItem());
		 listFacturePF=facturepfdao.findByDepot(depot.getId());
		 boolean valider=false;
		 for(int i=0; i<listFacturePF.size();i++)
		 {
			 listDetailFacturePF.clear();
			 FacturePF facturepf=listFacturePF.get(i);
			 listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(facturepf.getId()) ;
			 BigDecimal totalttc=BigDecimal.ZERO;
			 BigDecimal totalht=BigDecimal.ZERO;
			 BigDecimal totaltva=BigDecimal.ZERO;
			 
			 for(int j=0;j<listDetailFacturePF.size();j++)
			 {
				 DetailFacturePF detailfacturepf=listDetailFacturePF.get(j);
				 totalttc=totalttc.add(detailfacturepf.getMontantTTC());
				 totalht=totalht.add(detailfacturepf.getMontantHT());
				 totaltva=totaltva.add(detailfacturepf.getMontantTVA());
				 
			 }
			 
			 if(!totalttc.setScale(2).equals(BigDecimal.ZERO.setScale(2)))
			 {
				 facturepf.setMontantTTC(totalttc);
				 facturepf.setMontantHT(totalht);
				 facturepf.setMontantTVA(totaltva);
				 facturepfdao.edit(facturepf);
				 valider=true;
				 DetailCompteClient detailcompteclient=detailCompteClientdao.findByFacture(facturepf.getId());
         		detailcompteclient.setMontantDebit(totalttc);
         		detailCompteClientdao.edit(detailcompteclient);
			 }
			 
		 }
		 
		if(valider==true)
		{
			JOptionPane.showMessageDialog(null, "La Modification effectué avec succé");
		}
		
		
		
	
	}
	
	void modifierttcetHTettvaDetailFacturePF()
	{

		 List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
		 List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
		 Depot depot=mapDepot.get(combodepot.getSelectedItem());
		 listFacturePF=facturepfdao.findByDepot(depot.getId());
		 boolean valider=false;
		 for(int i=0; i<listFacturePF.size();i++)
		 {
			 listDetailFacturePF.clear();
			 FacturePF facturepf=listFacturePF.get(i);
			 listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(facturepf.getId()) ;
			 BigDecimal totalttc=BigDecimal.ZERO;
			 BigDecimal totalht=BigDecimal.ZERO;
			 BigDecimal totaltva=BigDecimal.ZERO;
			 
			 for(int j=0;j<listDetailFacturePF.size();j++)
			 {
				 DetailFacturePF detailfacturepf=listDetailFacturePF.get(j);
				 detailfacturepf.setMontantHT(detailfacturepf.getQuantite().multiply(detailfacturepf.getPrixUnitaire()));
				 detailfacturepf.setMontantTVA(detailfacturepf.getMontantHT().multiply(Constantes.TVA));
				 detailfacturepf.setMontantTTC(detailfacturepf.getMontantHT().add(detailfacturepf.getMontantTVA()));
				 detailFacturePfdao.edit(detailfacturepf);
				 valider=true;
			 }
			 
		
			 
		 }
		 
		if(valider==true)
		{
			JOptionPane.showMessageDialog(null, "La Modification effectué avec succé");
		}
		
		
		
	
	}
	
	
	
	// pour executer cette tache il faut d'abord supprimer detailtransfertpf de type vente 
	void modifierDetailTransfertPF()
	{
		 List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
		 List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
		 Depot depot=mapDepot.get(combodepot.getSelectedItem());
		 listFacturePF=facturepfdao.findByDepot(depot.getId());
		 boolean valider=false;
		 for(int i=0; i<listFacturePF.size();i++)
		 {
			 listDetailFacturePF.clear();
			 FacturePF facturepf=listFacturePF.get(i);
			 listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(facturepf.getId()) ;
			TransferStockPF transfertStockPF=transferStockPFDAO.findByCodeTransfert(facturepf.getCodeTransfer());
			 
			 for(int j=0;j<listDetailFacturePF.size();j++)
			 {
				 DetailFacturePF detailfacturepf=listDetailFacturePF.get(j);
				 DetailTransferProduitFini detailTransferProduitFini=new DetailTransferProduitFini();
				 detailTransferProduitFini.setArticle(detailfacturepf.getArticle());
				 detailTransferProduitFini.setDateTransfer(facturepf.getDateFacture());
				 detailTransferProduitFini.setPrixUnitaire(detailfacturepf.getPrixUnitaire());
				 detailTransferProduitFini.setQuantite(detailfacturepf.getQuantite());
				 detailTransferProduitFini.setSousFamille(detailfacturepf.getSousFamille());
				 detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_VENTE);
				 detailTransferProduitFini.setTransferStockPF(transfertStockPF);
				 detailTransferProduitFini.setMagasinDestination(facturepf.getMagasin());
				 detailTransferStockPFdao.add(detailTransferProduitFini);
				 valider=true;
			 }
			 
		 }
		 
		if(valider==true)
		{
			JOptionPane.showMessageDialog(null, "La Modification effectué avec succé");
		}
		
	}
	}


