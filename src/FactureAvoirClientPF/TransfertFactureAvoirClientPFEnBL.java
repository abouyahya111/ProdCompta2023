package FactureAvoirClientPF;

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
import dao.daoImplManager.DetailFactureAvoirClientPFDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceTransportDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAvoirClientPFDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceTransportDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.HistoriqueTransfertDetailFactureAvoirClientPFEnBLDAOImpl;
import dao.daoImplManager.HistoriqueTransfertDetailFacturePFEnBLDAOImpl;
import dao.daoImplManager.HistoriqueTransfertFactureAvoirClientPFEnBLDAOImpl;
import dao.daoImplManager.HistoriqueTransfertFacturePFEnBLDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
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
import dao.daoManager.DetailFactureAvoirClientPFDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureServiceTransportDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAvoirClientPFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceTransportDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.HistoriqueTransfertDetailFactureAvoirClientPFEnBLDAO;
import dao.daoManager.HistoriqueTransfertDetailFacturePFEnBLDAO;
import dao.daoManager.HistoriqueTransfertFactureAvoirClientPFEnBLDAO;
import dao.daoManager.HistoriqueTransfertFacturePFEnBLDAO;
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
import dao.entity.DetailFactureAvoirClientPF;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureServiceTransport;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailPrixArticle;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FactureAvoirClientPF;
import dao.entity.FacturePF;
import dao.entity.FactureServiceTransport;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.HistoriqueTransfertDetailFactureAvoirClientPFEnBL;
import dao.entity.HistoriqueTransfertDetailFacturePFEnBL;
import dao.entity.HistoriqueTransfertFactureAvoirClientPFEnBL;
import dao.entity.HistoriqueTransfertFacturePFEnBL;
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


public class TransfertFactureAvoirClientPFEnBL extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<ClientPF> listParClientPF =new ArrayList<ClientPF>();
	private List<DetailFactureAvoirClientPF> listDetailFactureAvoirClientPF =new ArrayList<DetailFactureAvoirClientPF>();
	private List<DetailFactureServiceTransport> listDetailFactureServiceTransport =new ArrayList<DetailFactureServiceTransport>();
	private List<DetailFactureAvoirClientPF> listDetailFacturePFTMP =new ArrayList<DetailFactureAvoirClientPF>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Articles> listArticleTmp =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<StockPF> listArticleStockPF =new ArrayList<StockPF>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FactureAvoirClientPF> listFacturePF =new ArrayList<FactureAvoirClientPF>();
	private List<FactureAvoirClientPF> listFacturePFTmp =new ArrayList<FactureAvoirClientPF>();
	private List<FactureServiceTransport> listFactureServiceTransport =new ArrayList<FactureServiceTransport>();
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
	private JCheckBox checkttc = new JCheckBox("TTC");
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
	private FactureAvoirClientPFDAO factureAvoirClientpfdao;
	private FactureServiceTransportDAO factureServiceTransportDAO;
	private FactureAvoirClientPF factureAvoirClientPF;
	private DetailTransferProduitFiniDAO detailTransferStockPFdao;
    private DetailFactureAvoirClientPFDAO detailFactureAvoirClientPfdao;

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
	 private JButton btnSupprimer = new JButton();
	 private  JComboBox comboFournisseur = new JComboBox();
	private JTextField txtparnumBL;
	private JRadioButton rdbtnDateFacture;
	private JDateChooser pardateChooser;
	private  JComboBox combopardepot;
	private StockPFDAO stockpfDAO;
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
	  JComboBox comboBoxtherres = new JComboBox();
	  JLabel lblOffreVerres = new JLabel("Offre Verres :");
	  JComboBox comboBoxverres = new JComboBox();
	  JLabel stockdisponibleoffretherres = new JLabel("");
	  JLabel stockdisponibleoffreverres = new JLabel("");
	  JLabel lblOffrePromo = new JLabel("Offre Promo :");
	  JComboBox comboBoxPromo = new JComboBox();
	  JLabel stockdisponiblearticlepromo = new JLabel("");
		private List<DetailFactureServiceTransport> listDetailFactureServiceTransportCalculerTTC =new ArrayList<DetailFactureServiceTransport>();
	  private List<FactureAvoirClientPF> listFacturePFOrderByDate =new ArrayList<FactureAvoirClientPF>();
	  private List<FactureAvoirClientPF> listFacturePFOrderByNumFacture =new ArrayList<FactureAvoirClientPF>();
	  private JTextField txtlien;
	 
	  
	  private JFrame mainFrame;
	  private HistoriqueTransfertFactureAvoirClientPFEnBLDAO historiqueTransfertFactureAvoirClientPFEnBLDAO;
	  private HistoriqueTransfertDetailFactureAvoirClientPFEnBLDAO historiqueTransfertDetailFactureAvoirClientPFEnBLDAO;
	  JCheckBox chckbxServiceTransport = new JCheckBox("Service Transport");
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public TransfertFactureAvoirClientPFEnBL() {
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
         	factureAvoirClientpfdao=new FactureAvoirClientPFDAOImpl();
         
         	detailFactureAvoirClientPfdao=new DetailFactureAvoirClientPFDAOImpl();
         	
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
        	historiqueTransfertFactureAvoirClientPFEnBLDAO=new HistoriqueTransfertFactureAvoirClientPFEnBLDAOImpl();
        	historiqueTransfertDetailFactureAvoirClientPFEnBLDAO=new HistoriqueTransfertDetailFactureAvoirClientPFEnBLDAOImpl();
         	for(int i=0;i<listClientPFParCode.size();i++)
         	{
         		ClientPF clientpf=listClientPFParCode.get(i);
         		mapClientPFparCode.put(clientpf.getCode(), clientpf);
         	}
         	
          } catch (Exception exp){exp.printStackTrace();}
		      
		
		  
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
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter les BL/Factures Avoir Client PF Par :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(296, 11, 1131, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 201, 1558, 533);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
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
	    		
	    		detailFactureAvoirClientPfdao.ViderSession();
	    		
	    		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	    		
	    		
	    		if(combopardepot.getSelectedItem().equals(""))
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}	
	    		
	    		
	    		
	    		if(magasin==null)
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}	
	    		
	    		
	    	if(comboparclient.getSelectedItem().equals("")
	    			&& txtparnumBL.getText().equals("") 
	    			&& pardateChooser.getDate()==null
	    			&& combopardepot.getSelectedItem().equals(""))
	    	{
	    		
	    	
	    		InitialiseTableauFacture();
	    		
	    	}else
	    	{
	    		
	    		chargerListeFacture();
	    		
	    		
	    	}
	    				
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(524, 166, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1875, 83);
	    add(layeredPane_2);
	    
	    JLabel lblNumFacture = new JLabel("Num Facture :");
	    lblNumFacture.setBounds(10, 12, 97, 24);
	    layeredPane_2.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtparnumBL = new JTextField();
	    txtparnumBL.setBounds(117, 12, 102, 26);
	    layeredPane_2.add(txtparnumBL);
	    util.Utils.copycoller(txtparnumBL);
	    txtparnumBL.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {}
	    });
	    txtparnumBL.setColumns(10);
	    
	    JLabel lblClient_1 = new JLabel("Client :");
	    lblClient_1.setBounds(229, 11, 56, 24);
	    layeredPane_2.add(lblClient_1);
	    lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    JLabel lblDateFacture = new JLabel("Au :");
	    lblDateFacture.setBounds(673, 9, 34, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     pardateChooser = new JDateChooser();
	     pardateChooser.setBounds(717, 10, 151, 26);
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
	     combopardepot.addItemListener(new ItemListener() {
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
			     				}else
			     				{
			     					combomagasin.removeAllItems();
			     					
			     				}
			     				
			     				
			     			}else
			     			{
			     				combomagasin.removeAllItems();
			     				
			     			}
			     				
	    					
	    				}
	    				
	    				
	   	 		 }
	   	 	
		
		     		
		     		
		     		
		     	
	     		
	     		
	     	}
	     });
	     combopardepot.setBounds(940, 10, 151, 24);
	     layeredPane_2.add(combopardepot);
	     combopardepot.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {}
	     });
	     combopardepot.setSelectedIndex(-1);
	     
	     JLabel lblDepot = new JLabel("Depot  :");
	     lblDepot.setBounds(878, 9, 97, 24);
	     layeredPane_2.add(lblDepot);
	     lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     
	     JLabel label_10 = new JLabel("Famille Article :");
	     label_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     label_10.setBounds(1340, 12, 87, 24);
	     layeredPane_2.add(label_10);
	     comboparsousfamille = new JComboBox();
		    
	     comboparsousfamille.setBounds(1681, 13, 151, 24);
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
	     comboparfamille.setBounds(1422, 13, 167, 24);
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
	     label_11.setBounds(1599, 13, 87, 24);
	     layeredPane_2.add(label_11);
	     
	      comboparclient = new JComboBox();
	     comboparclient.setSelectedIndex(-1);
	     comboparclient.setBounds(280, 12, 178, 24);
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
	     	
	     	
	     		
	     		
	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(641, 167, 106, 23);
	     add(button);
	     
	     JButton btnTransfereBl = new JButton("Transfere Facture En BL");
	     btnTransfereBl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*	
				if(utilisateur.getLogin().toLowerCase().equals(Constantes.ADMIN_CONNEXION.toLowerCase()))
				{
					*/
					
		               boolean transferEnBL=false;
			    		
		               
			    		maptransfereblfacture.clear();
			    		if(!remplirmaptransfereblfacture())	{
							JOptionPane.showMessageDialog(null, "Il faut remplir les factures à transferer au BL", "Erreur", JOptionPane.ERROR_MESSAGE);
						} else {
							
							if(maptransfereblfacture.size()>0)
							{
								 int reponse = JOptionPane.showConfirmDialog(null, "Voulez Vous Vraiment transferer les Facturers Suivant En BL ?", 
											"Satisfaction", JOptionPane.YES_NO_OPTION);
									 
									if(reponse == JOptionPane.YES_OPTION )		
										
									{
										
		                             	 
										for(int i=0;i<listFacturePF.size();i++)
										{
											
											listFacturePFMAJ.clear();
											FactureAvoirClientPF facturepf=listFacturePF.get(i);
											if(!facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
											{
												if(maptransfereblfacture.containsKey(facturepf.getNumBL()))
												{
													
													
													
													
													
													
												if(facturepf.getNumFacture() !=null)
												{
													
	
											
											HistoriqueTransfertFactureAvoirClientPFEnBL historiqueTransfertFacturePFEnBL=new HistoriqueTransfertFactureAvoirClientPFEnBL();			
										
											historiqueTransfertFacturePFEnBL.setAnnulerPar(facturepf.getAnnulerPar());
											historiqueTransfertFacturePFEnBL.setClientPF(facturepf.getClientPF());	
											historiqueTransfertFacturePFEnBL.setCodeTransfer(facturepf.getCodeTransfer());
											
											historiqueTransfertFacturePFEnBL.setCreerPar(facturepf.getCreerPar());
											
											historiqueTransfertFacturePFEnBL.setDateFacture(facturepf.getDateFacture());	
											historiqueTransfertFacturePFEnBL.setDateModifier(facturepf.getDateModifier());
											historiqueTransfertFacturePFEnBL.setDateSaisi(facturepf.getDateCreer());
											historiqueTransfertFacturePFEnBL.setDatetransfertenbl(new Date());
											historiqueTransfertFacturePFEnBL.setDepot(facturepf.getDepot());
											
											historiqueTransfertFacturePFEnBL.setEtat(facturepf.getEtat());
									
											historiqueTransfertFacturePFEnBL.setMagasin(facturepf.getMagasin());
											historiqueTransfertFacturePFEnBL.setModeReglement(facturepf.getModeReglement());
											historiqueTransfertFacturePFEnBL.setModifierPar(facturepf.getModifierPar());
											historiqueTransfertFacturePFEnBL.setMontantHT(facturepf.getMontantHT());
											historiqueTransfertFacturePFEnBL.setMontantTTC(facturepf.getMontantTTC());
											historiqueTransfertFacturePFEnBL.setMontantTVA(facturepf.getMontantTVA());
											historiqueTransfertFacturePFEnBL.setNumBl(facturepf.getNumBL());
											historiqueTransfertFacturePFEnBL.setNumCheque(facturepf.getNumCheque());
											historiqueTransfertFacturePFEnBL.setNumFacture(facturepf.getNumFacture());

											historiqueTransfertFacturePFEnBL.setTransfertPar(utilisateur);
											historiqueTransfertFacturePFEnBL.setType(facturepf.getType());
											
										
											historiqueTransfertFactureAvoirClientPFEnBLDAO.add(historiqueTransfertFacturePFEnBL);
											
											
											listDetailFactureAvoirClientPF=detailFactureAvoirClientPfdao.listeDetailFactureAvoirByFacture(facturepf.getId());
											
											for(int j=0;j<listDetailFactureAvoirClientPF.size();j++)
											{
												
											DetailFactureAvoirClientPF detailFacturePF=	listDetailFactureAvoirClientPF.get(j);
											
												HistoriqueTransfertDetailFactureAvoirClientPFEnBL historiqueTransfertDetailFacturePFEnBL=new HistoriqueTransfertDetailFactureAvoirClientPFEnBL();
												historiqueTransfertDetailFacturePFEnBL.setArticle(detailFacturePF.getArticle());
												historiqueTransfertDetailFacturePFEnBL.setFactureAvoirClientPF(historiqueTransfertFacturePFEnBL);
												historiqueTransfertDetailFacturePFEnBL.setMontantHT(detailFacturePF.getMontantHT());
												historiqueTransfertDetailFacturePFEnBL.setMontantTTC(detailFacturePF.getMontantTTC());
												historiqueTransfertDetailFacturePFEnBL.setMontantTVA(detailFacturePF.getMontantTVA());
												historiqueTransfertDetailFacturePFEnBL.setPrixUnitaire(detailFacturePF.getPrixUnitaire());
												historiqueTransfertDetailFacturePFEnBL.setQuantite(detailFacturePF.getQuantite());
												historiqueTransfertDetailFacturePFEnBL.setReduction(detailFacturePF.getReduction());
												historiqueTransfertDetailFacturePFEnBL.setSousFamille(detailFacturePF.getSousFamille());
												historiqueTransfertDetailFacturePFEnBL.setTva(detailFacturePF.getTva());
												historiqueTransfertDetailFacturePFEnBL.setUtilisateur(detailFacturePF.getUtilisateur());
												historiqueTransfertDetailFactureAvoirClientPFEnBLDAO.add(historiqueTransfertDetailFacturePFEnBL);
											}
											
											
											
											facturepf.setType(Constantes.TYPE_BON_LIVRAISON);
											facturepf.setEtat(ETAT_NON_REGLE);
										
											facturepf.setNumCheque(null);
										
											factureAvoirClientpfdao.edit(facturepf);
											transferEnBL=true;
											
											
											
											
											
												}
													
												}
											}
												
										}
										
										
										
										
									
										
										
										
										
										
										
										
										
										
										
										
										if(transferEnBL==true)
										{
											
											JOptionPane.showMessageDialog(null, "Les factures Transférer en BL avec succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
											
											
											chargerListeFacture();
										}else
										{
											JOptionPane.showMessageDialog(null, "Une ou Plusieur Factures pas de Numero de facture","Erreur",JOptionPane.ERROR_MESSAGE);
											return;
											
										}
										
										
									}
								
							}
							
							
								
						}
			/*		
				}else
				{
					
					JOptionPane.showMessageDialog(null, "Vous ne disposez pas des autorisations pour transferer les Factures En BL veuillez Contacter l'Administration SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
					
					
				}
		*/
		    		
				
				
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
	     btnTransfereBl.setBounds(1247, 765, 167, 30);
	     add(btnTransfereBl);
	     
	     JButton btnDeslectionnerTout = new JButton();
	     btnDeslectionnerTout.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		

	     		
	     		
	     		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(false, i, 8);
	     		}
	     	
	     	}
	     });
	     btnDeslectionnerTout.setToolTipText("deselectionner Tout");
	     btnDeslectionnerTout.setBounds(1513, 166, 26, 26);
	     btnDeslectionnerTout.setIcon(imgDeselectAll);
	     add(btnDeslectionnerTout);
	     
	     JButton btnSelectionnertout = new JButton();
	     btnSelectionnertout.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {

	    		
	    		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(true, i, 8);
	     		}
	    	
	     		
	     	}
	     });
	     btnSelectionnertout.setToolTipText("Selectionner Tout");
	     btnSelectionnertout.setBounds(1542, 166, 26, 26);
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
	  
	
		
	    listParClientPF=clientpfdao.findListClientByCodeDepot(utilisateur.getCodeDepot());
	    comboparclient.addItem("");
	    
	     datedebut = new JDateChooser();
	    datedebut.setLocale(Locale.FRANCE);
	    datedebut.setDateFormatString("dd/MM/yyyy");
	    datedebut.setBounds(512, 7, 151, 26);
	    layeredPane_2.add(datedebut);
	    
	    JLabel lblDu = new JLabel("Du :");
	    lblDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblDu.setBounds(468, 9, 34, 24);
	    layeredPane_2.add(lblDu);
	    
	    JLabel lblMagasin = new JLabel("Magasin :");
	    lblMagasin.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblMagasin.setBounds(1101, 11, 97, 24);
	    layeredPane_2.add(lblMagasin);
	    
	    combomagasin = new JComboBox();
	    combomagasin.setSelectedIndex(-1);
	    combomagasin.setBounds(1169, 12, 161, 24);
	    layeredPane_2.add(combomagasin);
	    
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
					
						if(listFacturePF.size()!=0)
						{
							
							 factureAvoirClientPF=listFacturePF.get(table.getSelectedRow()) ;
							 
							 
							 if(factureAvoirClientPF.getType().equals(Constantes.TYPE_BON_LIVRAISON) || factureAvoirClientPF.getType().equals(Constantes.TYPE_FACTURE) || factureAvoirClientPF.getType().equals(Constantes.TRANSFERE_BL_FACTURE))
								{

								 transferStock=transferStockPFDAO.findByCodeTransfert(factureAvoirClientPF.getCodeTransfer());
							
								listDetailFactureAvoirClientPF=detailFactureAvoirClientPfdao.listeDetailFactureAvoirByFacture (factureAvoirClientPF.getId());
								 for(int i=0;i<listDetailFactureAvoirClientPF.size();i++)
								 {
						        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFactureAvoirClientPF.get(i).getArticle().getId(),factureAvoirClientPF.getMagasin().getId(),listDetailFactureAvoirClientPF.get(i).getSousFamille().getId());

									 BigDecimal  stocktmp=stockpf.getStock().add(listDetailFactureAvoirClientPF.get(i).getQuantite());
									 stockpf.setStock(stocktmp);
								       
								  		stockpfDAO.edit(stockpf);
								  		DetailFactureAvoirClientPF detailfacturepf=listDetailFactureAvoirClientPF.get(i);
								  		detailFactureAvoirClientPfdao.delete(detailfacturepf.getId());
								  		
									 }
								 
								 
								 /*
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
								 */
								 if(transferStock!=null)
								 {
									 listDetailTransferProduitFini=transferStock.getListDetailTransferProduitFini();
									 for(int j=0;j<listDetailTransferProduitFini.size();j++)
									 {
										 detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(j).getId());
									 }
									 transferStockPFDAO.delete(transferStock.getId()); 
								 }
								
								 factureAvoirClientpfdao.delete(factureAvoirClientPF.getId());
								 
								 JOptionPane.showMessageDialog(null, "BL Supprimer avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
								 
								 chargerListeFacture();
								 
								}
							 
						}
						
						
						
						
					}
					
					
	    	}
	    		
	    		
	    		
	    		
	    		
	    	}
	    });
	    supprimer_facture.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    supprimer_facture.setBounds(1578, 238, 73, 24);
	    supprimer_facture.setIcon(imgSupprimer);
	  //  supprimer_facture.setEnabled(false);
	    add(supprimer_facture);
	    
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
	    for(int i=0;i<listParClientPF.size();i++)
	    {
	    	
	    ClientPF clientpf=listParClientPF.get(i);
	    comboparclient.addItem(clientpf.getNom());
	    mapParClientPF.put(clientpf.getNom(), clientpf);
	    supprimer_facture.setEnabled(false);
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
	

	
	




	
	
	void InitialiseTableauFacture()
	{
		
				
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Magasin", "Montant HT","Transfere BL en Facture"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class
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
	
	
	

	
	
	
	void chargerListeFacture()
	{
		
		InitialiseTableauFacture();
		
		boolean exist=false;
		
		ClientPF clientpf=mapParClientPF.get(comboparclient.getSelectedItem());
		Depot depot=mapparDepot.get(combopardepot.getSelectedItem());
		
		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	
		listFacturePF.clear();
		listFacturePFTmp.clear();
		listFactureServiceTransport.clear();
		
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
			
			listFacturePFTmp=factureAvoirClientpfdao.findByNumFcatureClientDateFactureDepotTypeNonBL(txtparnumBL.getText(),clientpf,datedebut.getDate(), pardateChooser.getDate(), magasin,Constantes.TYPE_BON_LIVRAISON);
		
			if(!comboparfamille.getSelectedItem().equals(""))
			{
				if(!comboparsousfamille.getSelectedItem().equals(""))
				{
					
					SousFamilleArticlePF sousfamille=mapsousfamille.get(comboparsousfamille.getSelectedItem());
				
					int i=0;
					while (i<listFacturePFTmp.size())
					{
						FactureAvoirClientPF facturePF=listFacturePFTmp.get(i);
						exist=false;
						listDetailFacturePFTMP=detailFactureAvoirClientPfdao.listeDetailFactureAvoirByFacture(facturePF.getId());
						for(int j=0;j<listDetailFacturePFTMP.size();j++)
						{
							DetailFactureAvoirClientPF detailfacturePF=listDetailFacturePFTMP.get(j);
							
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
		    		
				}else
				{
					
					afficher_tableFacturePF(listFacturePF);
				}
				
				
			}
			
		
		

			
			
		
		
	}
	
	
	
	
	void afficher_tableFacturePF(List<FactureAvoirClientPF> listFacture)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num BL", "Date Facture", "Type","Num Facture", "Client", "Depot", "Magasin", "Montant TTC","Transfere BL en Facture"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class
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
		FactureAvoirClientPF facturepf=listFacture.get(i);
		
		Date datefacture=facturepf.getDateFacture();
			
		for(int j=0;j<facturepf.getDetailFactureAvoirClientPF().size();j++)
		{
			
			MontantTTC=MontantTTC.add(facturepf.getDetailFactureAvoirClientPF().get(j).getMontantTTC());
		}
			Object []ligne={facturepf.getNumBL(),datefacture,facturepf.getType(),facturepf.getNumFacture(),facturepf.getClientPF().getNom(),facturepf.getDepot().getLibelle(),facturepf.getMagasin().getLibelle(),MontantTTC,false};

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




