package FactureAutresVente;

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
import dao.daoImplManager.DetailFactureAutresVenteDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAutresVenteDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceTransportDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
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
import dao.daoManager.DetailFactureAutresVenteDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAutresVenteDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceTransportDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockPFDAO;
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
import dao.entity.DetailFactureAutresVente;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailPrixArticle;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FactureAutresVente;
import dao.entity.FacturePF;
import dao.entity.FactureServiceTransport;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Sequenceur;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockPF;
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
import java.io.File;
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.JToggleButton;
import javax.swing.JCheckBox;


public class ConsulterFactureAutresVente extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;

	private JXTable  tableArticle = new JXTable();
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<ClientPF> listParClientPF =new ArrayList<ClientPF>();
	private List<DetailFactureAutresVente> listDetailFactureAutresVente =new ArrayList<DetailFactureAutresVente>();
	private List<DetailFactureAutresVente> listDetailFactureAutresVenteTMP =new ArrayList<DetailFactureAutresVente>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Articles> listArticleTmp =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<StockPF> listArticleStockPF =new ArrayList<StockPF>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FactureAutresVente> listFactureAutresVente =new ArrayList<FactureAutresVente>();
	
	private List<FactureAutresVente> listFactureAutresVenteMAJ =new ArrayList<FactureAutresVente>();
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
	private JCheckBox checkttc = new JCheckBox("TTC");
	private  JButton btninitialiser = new JButton();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private   JComboBox comboparclient = new JComboBox();
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FactureAutresVenteDAO factureAutresVentedao;
	private FactureAutresVente factureAutresVente;
	private DetailTransferProduitFiniDAO detailTransferStockPFdao;
private DetailFactureAutresVenteDAO detailFactureAutresVentedao;
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
	  JComboBox comboBoxtherres = new JComboBox();
	  JLabel lblOffreVerres = new JLabel("Offre Verres :");
	  JComboBox comboBoxverres = new JComboBox();
	  JLabel stockdisponibleoffretherres = new JLabel("");
	  JLabel stockdisponibleoffreverres = new JLabel("");
	  JLabel lblOffrePromo = new JLabel("Offre Promo :");
	  JComboBox comboBoxPromo = new JComboBox();
	  JLabel stockdisponiblearticlepromo = new JLabel("");
	  
	  private List<FacturePF> listFacturePFOrderByDate =new ArrayList<FacturePF>();
	  private List<FacturePF> listFacturePFOrderByNumFacture =new ArrayList<FacturePF>();
	  JCheckBox checkboxSansTva = new JCheckBox("Sans TVA");
	  
	  private JFrame mainFrame;
	  private JTextField txtdesignation;
	  private List<Client> listFournisseur =new ArrayList<Client>();
/////////////////////////////////////////////////////////////// Liste Des Bl Non Facture ////////////////////////////////////////////////////////////////////////////////////////////////	  
	  
private List<FacturePF> listFacturePFBLNonFacturer =new ArrayList<FacturePF>();  
private List<FactureAutresVente> listFactureAutresVenteBLNonFacturer =new ArrayList<FactureAutresVente>(); 
private List<FactureServiceTransport> listFactureServiceTransportBLNonFacturer =new ArrayList<FactureServiceTransport>();
private List<Production> listProductionServiceLancerBLNonFacturer =new ArrayList<Production>();

private FactureServiceTransportDAO factureServiceTransportDAO;
private ProductionDAO productionDAO;
private FacturePFDAO facturepfdao;
String MessageBlNonFacturer="";


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterFactureAutresVente() {
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
             
             detailTransferStockPFdao=new DetailTransferProduitFiniDAOImpl();
             transferStockPFDAO=new TransferStockPFDAOImpl();
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	factureAutresVentedao=new FactureAutresVenteDAOImpl();
         	detailFactureAutresVentedao=new DetailFactureAutresVenteDAOImpl();
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
        	 
        	facturepfdao=new FacturePFDAOImpl();
        	factureServiceTransportDAO=new FactureServiceTransportDAOImpl();       	
        	productionDAO=new ProductionDAOImpl();
        	
        	
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
       			

                   		
                   			boolean trouve=false;
                   			
                   			
                   			
                   			
                       		
                       		checkttc.setSelected(false);
                       		
                       	
                       		    txtdesignation.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0).toString());
                       			txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1).toString());	
                       		txtPrix.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
                       		txtreduction.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString());
                       		txtmontant.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());
                       		if(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString()).compareTo(BigDecimal.ZERO)==0)
                       		{
                       			checkboxSansTva.setSelected(true);
                       		}else
                       		{
                       			checkboxSansTva.setSelected(false);
                       		}
                       		
                       		
                       		btnAjouter.setEnabled(false);
                   			
                   		
           		 		
       			
       		}
       			
       		
       		
       		}
       });
        
       tableArticle.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Designation", "Quantite","Prix Unitaire", "Reduction","Montant HT", "Montant TVA", "Montant TTC"
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
		     
		     	
		      
		      JLabel lblQuantit = new JLabel("Quantite  :");
		      lblQuantit.setBounds(581, 24, 72, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      util.Utils.copycoller(txtquantite);
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {
               		

               		if(e.getKeyCode()==e.VK_ENTER)
	{
               			
                  			try {
                   				
                   				if(!txtdesignation.getText().equals(""))
                   				{
                   	 				if(!txtPrix.getText().equals(""))
	                   				{
	                   					if(!txtquantite.getText().equals(""))
	                   					{
	                   						if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
	                   						{
	                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
      					{
	                   								
	                   								
	    		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))+"");
	    		                   						
      							
      						
      						
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
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(645, 24, 112, 26);
		      layeredPane.add(txtquantite);
		      
		       lblPrix = new JLabel("Prix       :");
		      lblPrix.setBounds(773, 24, 69, 26);
		      layeredPane.add(lblPrix);
		      
		      txtPrix = new JTextField();
		      txtPrix.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {
            		

               		if(e.getKeyCode()==e.VK_ENTER)
	{
               			
               			
                   			try {
                   				
                   				if(!txtdesignation.getText().equals(""))
                   				{
                   	 				if(!txtPrix.getText().equals(""))
	                   				{
	                   					if(!txtquantite.getText().equals(""))
	                   					{
	                   						if(new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)>0)
	                   						{
	                   							if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)>0)
      					{
	                   								
	                   							
	    		                   							txtmontant.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))+"");
	    		                   						
      							
      						
      						
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
		      txtPrix.setBounds(852, 24, 170, 26);
		      layeredPane.add(txtPrix);
		      
		    
		      
		       lblMontant = new JLabel("Montant  :");
		      lblMontant.setBounds(1039, 24, 67, 26);
		      layeredPane.add(lblMontant);
		      
		      txtmontant = new JTextField();
		      txtmontant.setEditable(false);
		      txtmontant.setColumns(10);
		      txtmontant.setBounds(1121, 24, 160, 26);
		      layeredPane.add(txtmontant);
		      
		       lblRemise = new JLabel("Remise :");
		      lblRemise.setBounds(10, 71, 57, 26);
		      layeredPane.add(lblRemise);
		      
		      txtreduction = new JTextField();
		      txtreduction.setColumns(10);
		      txtreduction.setBounds(79, 72, 101, 26);
		      layeredPane.add(txtreduction);
		      
		       labelpourcentage = new JLabel("%");
		      labelpourcentage.setFont(new Font("Tahoma", Font.BOLD, 11));
		      labelpourcentage.setBounds(186, 71, 26, 26);
		      layeredPane.add(labelpourcentage);
		      
		 
		     
		
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
				}else if(comboClientpf.getSelectedIndex()==-1 || comboClientpf.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le Client SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
					
				}else if(comboFournisseur.getSelectedIndex()==-1)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez choisir le Fournisseur SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
				}
				
				
				else if(listDetailFactureAutresVente.size()==0)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer les articles à facturé SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else
				{
					
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				ClientPF clientPF =mapClientPF.get(comboClientpf.getSelectedItem());
				Client fournisseur=mapFournisseur.get(comboFournisseur.getSelectedItem());
				 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier La facture  ?", 
							"Satisfaction", JOptionPane.YES_NO_OPTION);
					 
					if(reponse == JOptionPane.YES_OPTION )
						
					{
						
						FactureAutresVente factureAutresVenteTmp=listFactureAutresVente.get(table.getSelectedRow());
						factureAutresVenteTmp.setModifierPar(utilisateur);
						//facturePFTmp.setNumBl(txtnumbl.getText());
						factureAutresVenteTmp.setClientPF(clientPF);
						factureAutresVenteTmp.setFournisseur(fournisseur);
						factureAutresVenteTmp.setDepot(depot);
						factureAutresVenteTmp.setDateFacture(dateChooserfacture.getDate());
						if(factureAutresVenteTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
						{
							factureAutresVenteTmp.setDateBl(dateChooserfacture.getDate());
						}
						factureAutresVenteTmp.setEtat(Constantes.ETAT_NON_REGLE);
						factureAutresVenteTmp.setType(Constantes.TYPE_BON_LIVRAISON);
						factureAutresVenteTmp.setMontantHT(new BigDecimal(txttotalmontantHT.getText()).setScale(6, RoundingMode.HALF_UP));
						factureAutresVenteTmp.setMontantTVA(new BigDecimal(txttotalmontantTVA.getText()).setScale(6, RoundingMode.HALF_UP));
						factureAutresVenteTmp.setMontantTTC((new BigDecimal(txttotalmontantTTC.getText())).setScale(6, RoundingMode.HALF_UP));	
						factureAutresVenteTmp.setDateModifier(new Date());	
						factureAutresVenteTmp.setDetailFactureAutresVente(listDetailFactureAutresVente);
					    factureAutresVentedao.edit(factureAutresVenteTmp);
					    
					    ////////////////////////////////// ajouter detail compte client par client facture ////////////////////////   
					    FactureAutresVente factureAutresVente=listFactureAutresVente.get(table.getSelectedRow());
						DetailCompteClient detailcompteclient=detailCompteClientdao.findByFactureAutresVente(factureAutresVente.getId());
					    detailcompteclient.getCompteClient().setSolde(detailcompteclient.getCompteClient().getSolde().subtract(detailcompteclient.getMontantDebit()));
						compteclientdao.edit(detailcompteclient.getCompteClient());
						detailcompteclient.setCompteClient(clientPF.getCompteClient());
						detailcompteclient.setUtilisateurMAJ(utilisateur);
						detailcompteclient.setDateMaj(new Date());
						detailcompteclient.setFournisseur(fournisseur);
						detailcompteclient.setMontantDebit(new BigDecimal(txttotalmontantTTC.getText()));
						detailcompteclient.setDateOperation(dateChooserfacture.getDate());
						if(factureAutresVente.getNumFacture()!=null)
						{
							detailcompteclient.setDesignation("Montant sur Facture Autres vente "+factureAutresVente.getNumFacture());
						}else
						{
							detailcompteclient.setDesignation("Montant sur Facture Autres vente "+factureAutresVente.getNumBl());
						}
						
						detailcompteclient.setFactureAutresVente(factureAutresVente);	
					    detailCompteClientdao.edit(detailcompteclient);
					    solde=clientPF.getCompteClient().getSolde().add(new BigDecimal(txttotalmontantTTC.getText()));
					    clientPF.getCompteClient().setSolde(solde);
					    compteclientdao.edit(clientPF.getCompteClient());
					
						 
					    
					    
					    
					    JOptionPane.showMessageDialog(null, "Facture Modifier avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					    initialiserFacture();
					    initialiser();					  
					  
					    listDetailFactureAutresVente.clear();
					   
						InitialiseTableau();
						chargerListeFacture();
							
					}
				
			
				}
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(622, 916, 112, 24);
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
		layeredPane_1.setBounds(10, 408, 1458, 51);
		add(layeredPane_1);
		
		JLabel label = new JLabel("Code  :");
		label.setBounds(10, 13, 89, 24);
		layeredPane_1.add(label);
		
		txtnumbl = new JTextField();
		txtnumbl.setEditable(false);
		txtnumbl.setColumns(10);
		txtnumbl.setBounds(57, 12, 129, 26);
		layeredPane_1.add(txtnumbl);
	
		JLabel label_1 = new JLabel("Date  :");
		label_1.setBounds(196, 13, 62, 24);
		layeredPane_1.add(label_1);
		
		 dateChooserfacture = new JDateChooser();
		dateChooserfacture.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {}
		});
		dateChooserfacture.setLocale(Locale.FRANCE);
		dateChooserfacture.setDateFormatString("dd/MM/yyyy");
		dateChooserfacture.setBounds(236, 11, 139, 26);
		layeredPane_1.add(dateChooserfacture);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(385, 13, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(433, 13, 183, 24);
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
		  
		   comboClientpf = new JComboBox();
		  comboClientpf.setSelectedIndex(-1);
		  comboClientpf.setBounds(702, 13, 362, 24);
		  layeredPane_1.add(comboClientpf);
		  AutoCompleteDecorator.decorate(comboClientpf);
		  
		  JLabel lblClient = new JLabel("Client :");
		  lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblClient.setBounds(636, 12, 56, 24);
		  layeredPane_1.add(lblClient);
		  
		  JLabel label_6 = new JLabel("Fournisseur :");
		  label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  label_6.setBounds(1074, 13, 79, 24);
		  layeredPane_1.add(label_6);
		  
		   comboFournisseur = new JComboBox();
		  comboFournisseur.setSelectedIndex(-1);
		  comboFournisseur.setBounds(1152, 14, 226, 24);
		  layeredPane_1.add(comboFournisseur);
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture Autres Vente");
		titledSeparator_2.setBounds(10, 375, 889, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(495, 625, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
				
						

						boolean trouve=false;
					
						
					 if(txtdesignation.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez entrer la designation SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtquantite.getText().equals("") )
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)) ){
							
							
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
							
							
					        
					         
					       
					         
					         for(int i=0;i<listDetailFactureAutresVente.size();i++)
					         {
					        	 DetailFactureAutresVente detailFactureAutresVente =listDetailFactureAutresVente.get(i);
					        	 if(detailFactureAutresVente.getDesignation().equals(txtdesignation.getText()) )
					        	 {
					        		 trouve=true;
					        	 }
					         }
					         
					         if(trouve==false)
					         {
	
					  
					        	  
					
						        		 
						    
					        	 
					        	 
					        	 
					        	 DetailFactureAutresVente detailFacture=new DetailFactureAutresVente();
					        	 if(!txtreduction.getText().equals(""))
						          {
						        	  detailFacture.setReduction(new BigDecimal(txtreduction.getText()));
						          }else
						          {
						        	  detailFacture.setReduction(BigDecimal.ZERO);  
						          }
					        	 
						          detailFacture.setDesignation(txtdesignation.getText());
						          detailFacture.setQuantite(new BigDecimal(txtquantite.getText()));
						     if(checkttc.isSelected()==true)
						     {
						    	 detailFacture.setPrixUnitaire(prixTTC);
						           detailFacture.setMontantHT((prixTTC).multiply(new BigDecimal(txtquantite.getText())));
						           if(checkboxSansTva.isSelected()==true)
							          {
						        	   detailFacture.setMontantTVA(BigDecimal.ZERO);
								        detailFacture.setMontantTTC((((prixTTC).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP));
								       
						        	   
							          }else
							          {
							        	   detailFacture.setMontantTVA((((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
									        detailFacture.setMontantTTC((((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).add(((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));
									       
							          }
						        
							      
							        if(!txtreduction.getText().equals(""))
							          {
							        	if(checkboxSansTva.isSelected()==true)
								          {
								        	  detailFacture.setMontantTTC(((((prixTTC).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP)).subtract(((((prixTTC).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

								          }else
								          {
								        	  detailFacture.setMontantTTC(((((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).add(((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).subtract(((((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).add(((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

								          }
							          }else
							          {
							        	  if(checkboxSansTva.isSelected()==true)
								          {
								        	  detailFacture.setMontantTTC((((prixTTC).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP));

							        		  
								          }else
								          {
								        	  detailFacture.setMontantTTC((((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).add(((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));

								          }
							          }	
							        if(checkboxSansTva.isSelected()==true)
							          {
							        	detailFacture.setTva(BigDecimal.ZERO);
							          }else
							          {
							        	  detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
							          }
							        
							        
						    	 
						     }else
						     {
						    	 detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
						           detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText())));
						           if(checkboxSansTva.isSelected()==true)
							          {
						        	   
						        	    detailFacture.setMontantTVA(BigDecimal.ZERO);
								        detailFacture.setMontantTTC(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP));
								       
						        	   
							          }else
							          {
							        	    detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
									        detailFacture.setMontantTTC(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));
									       
							          }
						       
							   
							        if(!txtreduction.getText().equals(""))
							          {
							        	  if(checkboxSansTva.isSelected()==true)
								          {
								        	  detailFacture.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP)).subtract((((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

								          }else
								          {
								        	  detailFacture.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

								          }
							         
							          }else
							          {
							        	  if(checkboxSansTva.isSelected()==true)
								          {
								        	  detailFacture.setMontantTTC(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP));
 
								          }else
								          {
								        	  detailFacture.setMontantTTC(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));

								          }
							          } 
							        if(checkboxSansTva.isSelected()==true)
							          {
							        	 detailFacture.setTva(BigDecimal.ZERO);
							          }else
							          {
							        	  detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
							          }
							       
						     }
						          
						          
							        //detailFacture.setTypeVente(typevente);
						          
						           detailFacture.setFactureautresaente(factureAutresVente);
						       //    detailFacture.setDateCreation(new Date());
						           
						           detailFacture.setUtilisateur(utilisateur);
						           listDetailFactureAutresVente.add(detailFacture);
						         detailFactureAutresVentedao.add(detailFacture);
						         
						       
						           
						           
						           
						           
						          afficher_tableDetailFacturePF(listDetailFactureAutresVente);
						          int i=0;
							        BigDecimal montanttotal=BigDecimal.ZERO;
							        BigDecimal sommequantite=BigDecimal.ZERO;
							        BigDecimal montanttotalHT=BigDecimal.ZERO;
							        BigDecimal montanttotalTVA=BigDecimal.ZERO;
							      
							        while(i<listDetailFactureAutresVente.size())
							        {
							        	 DetailFactureAutresVente detailFactureAutresVente=listDetailFactureAutresVente.get(i);
								          montanttotal=  montanttotal.add(detailFactureAutresVente.getMontantTTC());
								          sommequantite= sommequantite.add(detailFactureAutresVente.getQuantite());
								          montanttotalHT=montanttotalHT.add(detailFactureAutresVente.getMontantHT());
								          montanttotalTVA=montanttotalTVA.add(detailFactureAutresVente.getMontantTVA());
								     
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
		   			
		   		
		   		
						
				   		if(tableArticle.getSelectedRow()!=-1)
				   		{
				   			boolean trouve=false;
							
						 if(txtdesignation.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez entrer la designation SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtquantite.getText().equals("") )
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0)) ){
								
								
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
					
						         
						        
						         
						         for(int i=0;i<listDetailFactureAutresVente.size();i++)
						         {
						        	 if(i!=tableArticle.getSelectedRow())
						        	 {
						        		 
						        		 DetailFactureAutresVente detailFactureAutresVente =listDetailFactureAutresVente.get(i);
							        	 if(detailFactureAutresVente.getDesignation().equals(txtdesignation.getText())  )
							        	 {
							        		 
							        			 
							        		 trouve=true;
							        		 
							        		
							        		 
							        		 }
							        	  
						        	 }
						        	 
						        	 
						         }
						         
						         if(trouve==false)
						         {
						        	 
						 
							        		 
							       
								        	 
						        	 
						        	 DetailFactureAutresVente detailFacture= listDetailFactureAutresVente.get(tableArticle.getSelectedRow());
						        	 
						        	
							        	  if(!txtreduction.getText().equals(""))
								          {
								        	  detailFacture.setReduction(new BigDecimal(txtreduction.getText()));
								          }else
								          {
								        	  detailFacture.setReduction(BigDecimal.ZERO);  
								          }
							        	  
							        
											
											
							        	 
								          detailFacture.setDesignation (txtdesignation.getText());
								          detailFacture.setQuantite(new BigDecimal(txtquantite.getText()));
								     if(checkttc.isSelected()==true)
								     {
								    	   detailFacture.setPrixUnitaire(prixTTC);
								           detailFacture.setMontantHT((prixTTC).multiply(new BigDecimal(txtquantite.getText())));
								           if(checkboxSansTva.isSelected()==true)
								           {
								        	   
								        	     detailFacture.setMontantTVA(BigDecimal.ZERO);
											       detailFacture.setTva(BigDecimal.ZERO);  
								           }else
								           {
								        	     detailFacture.setMontantTVA((((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
											       detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
								           }
									  
									      
									       
									     							       
									          if(!txtreduction.getText().equals(""))
									          {
									        	  if(checkboxSansTva.isSelected()==true)
										           {
										        	  detailFacture.setMontantTTC(((((prixTTC).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP)).subtract(((((prixTTC).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

									        		  
										           }else
										           {
											        	  detailFacture.setMontantTTC(((((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).add(((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).subtract(((((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).add(((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

										           }
									          }else
									          {
									        	  if(checkboxSansTva.isSelected()==true)
										           {
										        	  detailFacture.setMontantTTC((((prixTTC).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP));

										           }else
										           {
											        	  detailFacture.setMontantTTC((((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).add(((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));
 
										           }
									          } 
								     }else
								     {
								    	   detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
								           detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText())));
									      
								           if(checkboxSansTva.isSelected()==true)
								           {
								        	   detailFacture.setMontantTVA(BigDecimal.ZERO);
										       detailFacture.setTva(BigDecimal.ZERO); 
								        	   
								           }else
								           {
								        	   detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
										       detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
								           }
								        
									      
									       
									     							       
									          if(!txtreduction.getText().equals(""))
									          {
									        	   if(checkboxSansTva.isSelected()==true)
										           {
											        	  detailFacture.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP)).subtract((((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

										           }else
										           {
											        	  detailFacture.setMontantTTC((((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  
 
										           }
									          }else
									          {
									        	  if(checkboxSansTva.isSelected()==true)
										           {
										        	  detailFacture.setMontantTTC(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText())))).setScale(6, RoundingMode.HALF_UP));

										           }else
										           {
											        	  detailFacture.setMontantTTC(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));

										           }
									          }
								     }
								        
								          // detailFacture.setTypeVente(typevente);
								          
								           detailFacture.setFactureautresaente( factureAutresVente);
								       //    detailFacture.setDateCreation(new Date());
								           
								           detailFacture.setUtilisateur(utilisateur);	
								           
								         
								          listDetailFactureAutresVente.set(tableArticle.getSelectedRow(), detailFacture);
								          detailFactureAutresVentedao.edit(detailFacture);
						        		 
						       
							          
							          
							          afficher_tableDetailFacturePF(listDetailFactureAutresVente);
							          int i=0;
								        BigDecimal montanttotal=BigDecimal.ZERO;
								        BigDecimal sommequantite=BigDecimal.ZERO;
								        BigDecimal montanttotalHT=BigDecimal.ZERO;
								        BigDecimal montanttotalTVA=BigDecimal.ZERO;
								        
								      
								        while(i<listDetailFactureAutresVente.size())
								        {
								        	 DetailFactureAutresVente detailFactureAutresVente =listDetailFactureAutresVente.get(i);
									          montanttotal=  montanttotal.add(detailFactureAutresVente.getMontantTTC());
									          sommequantite= sommequantite.add(detailFactureAutresVente.getQuantite());
									          montanttotalHT=montanttotalHT.add(detailFactureAutresVente.getMontantHT());
									          montanttotalTVA=montanttotalTVA.add(detailFactureAutresVente.getMontantTVA());
									    
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
							
					
					        DetailFactureAutresVente detailfactureAutresVente=listDetailFactureAutresVente.get(tableArticle.getSelectedRow());
					      
				        
					       
					        
					        listDetailFactureAutresVente.remove(tableArticle.getSelectedRow());
					        detailFactureAutresVentedao.delete(detailfactureAutresVente.getId());
					  		
				  	    
					        afficher_tableDetailFacturePF(listDetailFactureAutresVente);
					          int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO;
						        BigDecimal lamarge=BigDecimal.ZERO;
						        BigDecimal compteur=BigDecimal.ZERO;
						        while(i<listDetailFactureAutresVente.size())
						        {
						        	 DetailFactureAutresVente detailFactureAutresVenteTmp=listDetailFactureAutresVente.get(i);
							          montanttotal=  montanttotal.add(detailFactureAutresVenteTmp.getMontantTTC());
							          sommequantite= sommequantite.add(detailFactureAutresVenteTmp.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFactureAutresVenteTmp.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFactureAutresVenteTmp.getMontantTVA());
							      
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
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter les BL/Factures Autres Vente Par :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(296, 11, 1053, 35);
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
					
					if(listFactureAutresVente.size()!=0)
					{
						
						 factureAutresVente=listFactureAutresVente.get(table.getSelectedRow()) ;
						 //facturePF.getType().equals(Constantes.TYPE_FACTURE) && facturePF.getEtat().equals(Constantes.ETAT_REGLE)    
						 if(factureAutresVente.getType().equals(Constantes.TYPE_FACTURE) || factureAutresVente.getType().equals(Constantes.TRANSFERE_BL_FACTURE))
						 {
							
								btnAjouter.setEnabled(false);
								btnModifier.setEnabled(false);
								btnSupprimer.setEnabled(false);
								buttonvalider.setEnabled(false);
								btninitialiser.setEnabled(false);
								//supprimer_facture.setEnabled(true);
								trouve=true;
								
							}else if(factureAutresVente.getType().equals(Constantes.TYPE_BON_LIVRAISON))
							{
								btnAjouter.setEnabled(true);
								btnModifier.setEnabled(true);
								btnSupprimer.setEnabled(true);
								buttonvalider.setEnabled(true);
								btninitialiser.setEnabled(true);
								//supprimer_facture.setEnabled(true);
								trouve=false;
							}
						 
					 
						 
						
						
						txtnumbl.setText(factureAutresVente.getNumBl());
						dateChooserfacture.setDate(factureAutresVente.getDateFacture());
						
						combodepot.setSelectedItem(factureAutresVente.getDepot().getLibelle());
						comboFournisseur.setSelectedItem(factureAutresVente.getFournisseur().getNom());
						
						
						comboClientpf.setSelectedItem(factureAutresVente.getClientPF().getNom());
						listDetailFactureAutresVente=detailFactureAutresVentedao.listeDetailFacturePFByFacture(factureAutresVente.getId());
					//	listDetailFacturePF=facturePF.getDetailFacturePF();
						 int i=0;
					        BigDecimal montanttotal=BigDecimal.ZERO;
					        BigDecimal sommequantite=BigDecimal.ZERO;
					        BigDecimal montanttotalHT=BigDecimal.ZERO;
					        BigDecimal montanttotalTVA=BigDecimal.ZERO; 
					        BigDecimal lamarge=BigDecimal.ZERO;
					        BigDecimal compteur=BigDecimal.ZERO;
					        while(i<listDetailFactureAutresVente.size())
					        {
					        	
					        	  DetailFactureAutresVente detailFactureAutresVente=listDetailFactureAutresVente.get(i);
						          montanttotal=  montanttotal.add(detailFactureAutresVente.getMontantTTC());
						          sommequantite= sommequantite.add(detailFactureAutresVente.getQuantite());
						          montanttotalHT=montanttotalHT.add(detailFactureAutresVente.getMontantHT());
						          montanttotalTVA=montanttotalTVA.add(detailFactureAutresVente.getMontantTVA());
						       
					            i++;
					        }
					       txttotalmontantTTC.setText(montanttotal+"");
					        txttotalquantite.setText(sommequantite+"");
					        txttotalmontantHT.setText(montanttotalHT+"");
				  			txttotalmontantTVA.setText(montanttotalTVA+"");
				  		
				  		
						afficher_tableDetailFacturePF(listDetailFactureAutresVente);
						
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
		});
		
		
		scrollPane_1.setViewportView(table);
		table.setColumnControlVisible(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Montant TTC","Transfere BL en Facture"
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
	    		
	    		detailFactureAutresVentedao.ViderSession();;
	    		
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
	    layeredPane_2.setBounds(10, 57, 1585, 51);
	    add(layeredPane_2);
	    
	    JLabel lblNumFacture = new JLabel("Num BL :");
	    lblNumFacture.setBounds(10, 12, 97, 24);
	    layeredPane_2.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtparnumBL = new JTextField();
	    txtparnumBL.setBounds(77, 12, 102, 26);
	    layeredPane_2.add(txtparnumBL);
	    util.Utils.copycoller(txtparnumBL);
	    txtparnumBL.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {}
	    });
	    txtparnumBL.setColumns(10);
	    
	    JLabel lblClient_1 = new JLabel("Client :");
	    lblClient_1.setBounds(189, 12, 56, 24);
	    layeredPane_2.add(lblClient_1);
	    lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    JLabel lblDateFacture = new JLabel("Au :");
	    lblDateFacture.setBounds(655, 12, 34, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     pardateChooser = new JDateChooser();
	     pardateChooser.setBounds(699, 13, 151, 26);
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
	     combopardepot.setBounds(922, 13, 151, 24);
	     layeredPane_2.add(combopardepot);
	     combopardepot.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {}
	     });
	     combopardepot.setSelectedIndex(-1);
	     
	     JLabel lblDepot = new JLabel("Depot  :");
	     lblDepot.setBounds(860, 12, 97, 24);
	     layeredPane_2.add(lblDepot);
	     lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     
	
	     
	      comboparclient = new JComboBox();
	     comboparclient.setSelectedIndex(-1);
	     comboparclient.setBounds(240, 13, 178, 24);
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
									
									/// Numerotation 2019
									SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
									
									///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
								
	                             //	FacturePF facturepfTmp=listFacturePF.get(line);
	                             	 String date =""; 
	                             	 String NumFacture="";
	                             	 if(datefacture.getDate()!=null)
	                             	 {
	                             		ChercherBLNonFacture(datefacture.getDate());	 
	                             		 if(!MessageBlNonFacturer.equals(""))
	                             		 {
	                             			 
	                             			JOptionPane.showMessageDialog(null, MessageBlNonFacturer); 
	                             			 return;
	                             			 
	                             		 }
	                             		date= dcn.format(datefacture.getDate());
	                             		NumFacture=Utils.genererCodeFactureVente(date);
	                             		
	                             	 }else
	                             	 {
	                             		 JOptionPane.showMessageDialog (null, "Veuillez selectionner la date facture SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
	                             		 return;
	                             	 }
	                             	 
									for(int i=0;i<listFactureAutresVente.size();i++)
									{
										FactureAutresVente factureAutresVente=listFactureAutresVente.get(i);
										if(factureAutresVente.getType().equals(Constantes.TYPE_BON_LIVRAISON))
										{
											if(maptransfereblfacture.containsKey(factureAutresVente.getNumBl()))
											{
												if(factureAutresVente.getNumFacture()==null)
												{
													factureAutresVente.setNumFacture(NumFacture);
													factureAutresVente.setType(Constantes.TRANSFERE_BL_FACTURE);
													factureAutresVente.setDateFacture(datefacture.getDate());
													
													factureAutresVente.setTraite(BigDecimal.ZERO);
													factureAutresVente.setCheque(BigDecimal.ZERO);
													factureAutresVente.setCredit(BigDecimal.ZERO);
													factureAutresVente.setEspece(BigDecimal.ZERO);
													factureAutresVente.setVersement(BigDecimal.ZERO);
													factureAutresVente.setVirement(BigDecimal.ZERO);
													factureAutresVente.setNumCheque("");
													factureAutresVente.setNumtraite("");
													factureAutresVente.setNumVersement("");
													
													factureAutresVentedao.edit(factureAutresVente);			
													transferEnBL=true;
												}else
												{
													factureAutresVente.setType(Constantes.TRANSFERE_BL_FACTURE);
													factureAutresVente.setTraite(BigDecimal.ZERO);
													factureAutresVente.setCheque(BigDecimal.ZERO);
													factureAutresVente.setCredit(BigDecimal.ZERO);
													factureAutresVente.setEspece(BigDecimal.ZERO);
													factureAutresVente.setVersement(BigDecimal.ZERO);
													factureAutresVente.setVirement(BigDecimal.ZERO);
													factureAutresVente.setNumCheque("");
													factureAutresVente.setNumtraite("");
													factureAutresVente.setNumVersement("");
													factureAutresVentedao.edit(factureAutresVente);	
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
											
										}
										
										
										
										JOptionPane.showMessageDialog(null, "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
										listFactureAutresVenteMAJ=factureAutresVentedao.findAll();
										
										chargerListeFacture();
									}else
									{
										JOptionPane.showMessageDialog(null, "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
										
									}
									
									
								}else
								{
									
									for(int i=0;i<listFactureAutresVente.size();i++)
									{
										FactureAutresVente factureAutreVente=listFactureAutresVente.get(i);
										
										if(factureAutreVente.getType().equals(Constantes.TYPE_BON_LIVRAISON))
										{
											if(maptransfereblfacture.containsKey(factureAutreVente.getNumBl()))
											{
												/// Numerotation 2019
												SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
												
												///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
												
												if(datefacture.getDate()!=null)
												{
												
													factureAutreVente.setDateFacture(datefacture.getDate());	
													
												}
												
											
												 String date = dcn.format(factureAutreVente.getDateFacture());
												 if(factureAutreVente.getNumFacture()==null)
													{
													 
														ChercherBLNonFacture(factureAutreVente.getDateFacture());	 
					                             		 if(!MessageBlNonFacturer.equals(""))
					                             		 {
					                             			 
					                             			JOptionPane.showMessageDialog(null, MessageBlNonFacturer); 
					                             			 return;
					                             			 
					                             		 }
													 
													 factureAutreVente.setNumFacture(Utils.genererCodeFactureVente(date));
													 factureAutreVente.setType(Constantes.TRANSFERE_BL_FACTURE);
													 factureAutreVente.setTraite(BigDecimal.ZERO);
													 factureAutreVente.setCheque(BigDecimal.ZERO);
													 factureAutreVente.setCredit(BigDecimal.ZERO);
													 factureAutreVente.setEspece(BigDecimal.ZERO);
													 factureAutreVente.setVersement(BigDecimal.ZERO);
													 factureAutreVente.setVirement(BigDecimal.ZERO);
														factureAutreVente.setNumCheque("");
														factureAutreVente.setNumtraite("");
														factureAutreVente.setNumVersement("");
														factureAutresVentedao.edit(factureAutreVente);
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
															
														}
														
														transferEnBL=true;
													}else
													{
														
														factureAutreVente.setType(Constantes.TRANSFERE_BL_FACTURE);
														factureAutreVente.setTraite(BigDecimal.ZERO);
														factureAutreVente.setCheque(BigDecimal.ZERO);
														factureAutreVente.setCredit(BigDecimal.ZERO);
														factureAutreVente.setEspece(BigDecimal.ZERO);
														factureAutreVente.setVersement(BigDecimal.ZERO);
														factureAutreVente.setVirement(BigDecimal.ZERO);
														factureAutreVente.setNumCheque("");
														factureAutreVente.setNumtraite("");
														factureAutreVente.setNumVersement("");
														factureAutresVentedao.edit(factureAutreVente);
														transferEnBL=true;
														
													}
											
											}
										}
										
									}
									
									
									if(transferEnBL==true)
									{
										JOptionPane.showMessageDialog(null, "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
										listFactureAutresVenteMAJ=factureAutresVentedao.findAll();
										
										chargerListeFacture();
									}else
									{
										JOptionPane.showMessageDialog(null, "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
										
									}
									
									
									
								}
							
							
						}else
						{
							
							
							for(int i=0;i<listFactureAutresVente.size();i++)
							{
								FactureAutresVente factureAutresVente=listFactureAutresVente.get(i);
								if(factureAutresVente.getType().equals(Constantes.TYPE_BON_LIVRAISON))
								{
									
									if(maptransfereblfacture.containsKey(factureAutresVente.getNumBl()))
									{
										/// Numerotation 2019
										SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
										
										///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
										
										
										if(datefacture.getDate()!=null)
										{
										
											factureAutresVente.setDateFacture(datefacture.getDate());	
											
										}
										
										
									
										 String date = dcn.format(factureAutresVente.getDateFacture());
										 
										 if(factureAutresVente.getNumFacture()==null)
											{
											 
												ChercherBLNonFacture(factureAutresVente.getDateFacture());	 
			                             		 if(!MessageBlNonFacturer.equals(""))
			                             		 {
			                             			 
			                             			JOptionPane.showMessageDialog(null, MessageBlNonFacturer); 
			                             			 return;
			                             			 
			                             		 }
											
											 factureAutresVente.setNumFacture(Utils.genererCodeFactureVente(date));
											 factureAutresVente.setType(Constantes.TRANSFERE_BL_FACTURE);
											 factureAutresVente.setTraite(BigDecimal.ZERO);
											 factureAutresVente.setCheque(BigDecimal.ZERO);
											 factureAutresVente.setCredit(BigDecimal.ZERO);
											 factureAutresVente.setEspece(BigDecimal.ZERO);
											 factureAutresVente.setVersement(BigDecimal.ZERO);
											 factureAutresVente.setVirement(BigDecimal.ZERO);
											 factureAutresVente.setNumCheque("");
											 factureAutresVente.setNumtraite("");
												factureAutresVente.setNumVersement("");
												factureAutresVentedao.edit(factureAutresVente);
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
													
												}
												
												transferEnBL=true;
											 
											 
											 
											}else
											{
												
												factureAutresVente.setType(Constantes.TRANSFERE_BL_FACTURE);
												factureAutresVente.setTraite(BigDecimal.ZERO);
												factureAutresVente.setCheque(BigDecimal.ZERO);
												factureAutresVente.setCredit(BigDecimal.ZERO);
												factureAutresVente.setEspece(BigDecimal.ZERO);
												factureAutresVente.setVersement(BigDecimal.ZERO);
												factureAutresVente.setVirement(BigDecimal.ZERO);
												factureAutresVente.setNumCheque("");
												factureAutresVente.setNumtraite("");
												factureAutresVente.setNumVersement("");
												factureAutresVentedao.edit(factureAutresVente);
												transferEnBL=true;
											}
										
									}
									
								}
							
							}
							
							
							if(transferEnBL==true)
							{
								JOptionPane.showMessageDialog(null, "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
								listFactureAutresVenteMAJ=factureAutresVentedao.findAll();
								
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
	     			table.setValueAt(false, i, 7);
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
	     			table.setValueAt(true, i, 7);
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
	    checkttc.setBounds(226, 73, 73, 24);
	    layeredPane.add(checkttc);
	    
	    JLabel label_4 = new JLabel("Designation :");
	    label_4.setBounds(10, 24, 72, 26);
	    layeredPane.add(label_4);
	    
	    txtdesignation = new JTextField();
	    txtdesignation.setColumns(10);
	    txtdesignation.setBounds(80, 24, 487, 26);
	    layeredPane.add(txtdesignation);
	  

		
	    listParClientPF=clientpfdao.findListClientByCodeDepot(utilisateur.getCodeDepot());
	    comboparclient.addItem("");
	    
	     datedebut = new JDateChooser();
	    datedebut.setLocale(Locale.FRANCE);
	    datedebut.setDateFormatString("dd/MM/yyyy");
	    datedebut.setBounds(494, 10, 151, 26);
	    layeredPane_2.add(datedebut);
	    
	    JLabel lblDu = new JLabel("Du :");
	    lblDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblDu.setBounds(450, 12, 34, 24);
	    layeredPane_2.add(lblDu);
	    
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
	    
	     supprimer_facture = new JButton();
	    supprimer_facture.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    	if(table.getSelectedRow()!=-1)
	    	{
	    		 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer la facture  ?", 
							"Satisfaction", JOptionPane.YES_NO_OPTION);
					 
					if(reponse == JOptionPane.YES_OPTION )
						
						
					{
					
						if(listFactureAutresVente.size()!=0)
						{
							
							 factureAutresVente=listFactureAutresVente.get(table.getSelectedRow()) ;
							 
							 
							 if(factureAutresVente.getType().equals(Constantes.TYPE_BON_LIVRAISON) || factureAutresVente.getType().equals(Constantes.TYPE_FACTURE) || factureAutresVente.getType().equals(Constantes.TRANSFERE_BL_FACTURE))
								{

								
							
								listDetailFactureAutresVente=detailFactureAutresVentedao.listeDetailFacturePFByFacture(factureAutresVente.getId());
								 for(int i=0;i<listDetailFactureAutresVente.size();i++)
								 {

								
								       
								  		
								  		DetailFactureAutresVente detailfactureAutresVente=listDetailFactureAutresVente.get(i);
								  		detailFactureAutresVentedao.delete(detailfactureAutresVente.getId());
								  		
									 }
								 DetailCompteClient detailcompteclient=detailCompteClientdao.findByFactureAutresVente(factureAutresVente.getId());
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
							
								
								 factureAutresVentedao.delete(factureAutresVente.getId());
								 
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
	    
	     checkboxSansTva = new JCheckBox("Sans TVA");
	    checkboxSansTva.setBounds(1391, 476, 83, 24);
	    add(checkboxSansTva);
	    for(int i=0;i<listParClientPF.size();i++)
	    {
	    	
	    ClientPF clientpf=listParClientPF.get(i);
	    comboparclient.addItem(clientpf.getNom());
	    mapParClientPF.put(clientpf.getNom(), clientpf);
	    supprimer_facture.setEnabled(false);
	    }
	    
	    
	    comboFournisseur.addItem("");
		  listFournisseur=fournisseurdao.findListClientByCodeDepot(CODE_DEPOT_TANTAN);
		  for(int t=0;t<listFournisseur.size();t++)
		  {
			 
			 Client  fournisseur=listFournisseur.get(t);
			  
			  mapFournisseur.put(fournisseur.getNom(), fournisseur);
			comboFournisseur.addItem(fournisseur.getNom());
		  }
		
		}
	

boolean remplirmaptransfereblfacture(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		boolean regle=(boolean) table.getValueAt(j, 7);
		if(regle==true ){
			
			maptransfereblfacture.put(String.valueOf(table.getValueAt(j, 0).toString()), Boolean.valueOf(table.getValueAt(j, 7).toString()));
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
		
		comboFournisseur.setSelectedIndex(-1);
	}

	void initialiser()
	{
		
	txtdesignation.setText("");
	   txtPrix.setText("");
		txtquantite.setText("");
		txtmontant.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	    
	     checkboxGratuits.setSelected(false);
	    
	     checkttc.setSelected(false);
	 	
		
		lblPrix.setVisible(true);
		lblMontant.setVisible(true);
		lblRemise.setVisible(true);
		labelpourcentage.setVisible(true);
		txtPrix.setVisible(true);
		txtreduction.setVisible(true);
		txtmontant.setVisible(true);
		checkttc.setVisible(true);
		checkboxSansTva.setSelected(false);
		
	}
	void initialiserPourFacture()
	{
		
	   txtPrix.setText("");
		txtquantite.setText("");
	     txtreduction.setText("");
	    txtdesignation.setText("");
	}
	
	
	void initialiserGratuit()
	{

	
	   txtPrix.setText("");
		txtquantite.setText("");
		txtmontant.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	     txtdesignation.setText("");
	   
		
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
						"Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Montant HT","Transfere BL en Facture"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class
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
	
}
	
	
	
	void afficher_tableDetailFacturePF(List<DetailFactureAutresVente> listDetailFacture)
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Designation", "Quantite", "Prix Unitaire","Reduction %", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false
				};
				
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		int i=0;
		 
		while(i<listDetailFacture.size())
		{	
		DetailFactureAutresVente detailfacturepf=listDetailFacture.get(i);
			
			Object []ligne={ detailfacturepf.getDesignation(),detailfacturepf.getQuantite(),detailfacturepf.getPrixUnitaire(),detailfacturepf.getReduction(), detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(), detailfacturepf.getMontantTTC()};

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
		listFactureAutresVente.clear();
		
		factureAutresVentedao=new FactureAutresVenteDAOImpl();
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
		
		
		
		
		
		//listFacturePFTmp=facturepfdao.findByNumFcatureClientDateFactureDepotEtatRegle(txtparnumBL.getText(),clientpf, pardateChooser.getDate(), depot,Constantes.ETAT_REGLE);	
		
		listFactureAutresVente=factureAutresVentedao.findByNumFcatureClientDateFactureDepotEtatRegle(txtparnumBL.getText(),clientpf,datedebut.getDate(), pardateChooser.getDate(), depot,Constantes.ETAT_NON_REGLE);

					
					
					
					if(listFactureAutresVente.size()==0)
					{
						//initialiserFacture();
			    		initialiser();
			    		InitialiseTableau();
			    		InitialiseTableauFacture();
					}else
					{
						afficher_tableFacturePF(listFactureAutresVente);
					}
				
				
			
				
			
			
			
		
		
	}
	
	
	
	
	void afficher_tableFacturePF(List<FactureAutresVente> listFacture)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Montant TTC","Transfere BL en Facture"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class
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
		 
		while(i<listFacture.size())
		{	
		MontantTTC=BigDecimal.ZERO;
		FactureAutresVente facturepf=listFacture.get(i);
		
		Date datefacture=facturepf.getDateFacture();
			
		for(int j=0;j<facturepf.getDetailFactureAutresVente().size();j++)
		{
			
			MontantTTC=MontantTTC.add(facturepf.getDetailFactureAutresVente().get(j).getMontantTTC());
		}
			Object []ligne={facturepf.getNumBl(),datefacture,facturepf.getType(),facturepf.getNumFacture(),facturepf.getClientPF().getNom(),facturepf.getDepot().getLibelle(),MontantTTC,false};

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
		
		listFactureAutresVenteBLNonFacturer=factureAutresVentedao.listFactureAutresVenteNonFacturer(date, depot , Constantes.TYPE_BON_LIVRAISON);
		
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




