package FactureServiceProduction;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureAchatDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailFactureAchatDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.Articles;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFacturePFParArticle;
import dao.entity.DetailFacturePFParFamille;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FactureServiceProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.TypeVente;
import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;




































import com.toedter.calendar.DateUtil;
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

import java.awt.SystemColor;


public class EtatFacturesServiceParOF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private ImageIcon imgExcel;
	private DefaultTableModel	 modelefacture;
	private JXTable table = new JXTable();
	private List<Client> listClient =new ArrayList<Client>();
	private List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
	private List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
	private List<FactureAchat> listFactureAchatPF =new ArrayList<FactureAchat>();
	private List<DetailFactureAchat> listDetailFactureAchatPF =new ArrayList<DetailFactureAchat>();
	private List<TransferStockPF> listTransferPF =new ArrayList<TransferStockPF>();
	private List<TransferStockPF> listTransferPFTmp =new ArrayList<TransferStockPF>();
	private List<DetailTransferProduitFini> listDetailTransferPF =new ArrayList<DetailTransferProduitFini>();
	private List<TransferStockMP> listTransferMP =new ArrayList<TransferStockMP>();
	private List<TransferStockMP> listTransferMPTmp =new ArrayList<TransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailFacturePF> listDetailFacturePFTMP =new ArrayList<DetailFacturePF>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<StockPF> listArticleStockPF =new ArrayList<StockPF>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FactureServiceProduction> listFactureService =new ArrayList<FactureServiceProduction>();
	private List<Production> listProduction=new ArrayList<Production>();
	private List<DetailFactureServiceProduction> listDetailFactureServiceProduction =new ArrayList<DetailFactureServiceProduction>();
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	private List<DetailFacturePFParFamille> listDetailFactureParFamille =new ArrayList<DetailFacturePFParFamille>();
	private List<DetailFacturePFParArticle> listDetailFactureParArticle =new ArrayList<DetailFacturePFParArticle>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, Client> mapClient= new HashMap<>();
	private Map< String, Client> mapClientparCode= new HashMap<>();
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
	
	private  JButton btninitialiser = new JButton();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FacturePFDAO facturepfdao;
	private FacturePF facturePF;
	private FactureAchatDAO factureAchatPFDAO;
	private DetailFactureAchatDAO detailFactureAchatPFDAO;
private DetailFacturePFDAO detailFacturePfdao;
private ClientDAO clientdao;
private ClientDAO fournisseurdao;

private   JDateChooser audateChooser = new JDateChooser();
	private  JComboBox comboclient = new JComboBox();
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	private CoutMPDAO coutMPDAO;
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	 private JButton btnSupprimer = new JButton();
	 private  JComboBox comboFournisseur = new JComboBox();
	private JRadioButton rdbtnDateFacture;
	private JDateChooser dudateChooser;
	private StockPFDAO stockpfDAO;
	private CompteClientDAO compteclientdao;
	String titre="";
	 JComboBox combomagasin = new JComboBox();
	 JComboBox comboarticle = new JComboBox();
	   JComboBox comboparfamille = new JComboBox();
	 private FamilleArticlesPFDAO famillearticleDAo;
	 JRadioButton radiobl = new JRadioButton("BL");
	 JRadioButton radiofacture = new JRadioButton("Facture");
	 JComboBox comboBox = new JComboBox();
	 private JComboBox combopardepot;
	 private JTextField txtNumfacture;
	 private FactureServiceProductionDAO factureServiceProductiondao;
	 private DetailFactureServiceProductionDAO detailfactureServiceProductiondao;
	 private ProductionDAO productionDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransferMPDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private JTextField txtdu;
	private JTextField txtau;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public EtatFacturesServiceParOF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1564, 1062);
      
	
        try{ 
        	
        	
        	 imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
             
             
             
            utilisateur=AuthentificationView.utilisateur;
            clientdao=new ClientDAOImpl();
            articleDAO=new ArticlesDAOImpl();
            coutMPDAO=new CoutMPDAOImpl();
            facturepfdao=new FacturePFDAOImpl();
            detailFacturePfdao=new DetailFacturePFDAOImpl();
        	 transferStockPFDAO =new TransferStockPFDAOImpl();
        	detailTransferProduitFiniDAO=new DetailTransferProduitFiniDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
            detailCompteClientdao=new DetailCompteClientDAOImpl();
            factureAchatPFDAO=new FactureAchatDAOImpl();
            detailFactureAchatPFDAO=new DetailFactureAchatDAOImpl();
            matierePremiereDAO=new MatierePremierDAOImpl();
       depotdao=new DepotDAOImpl();
       productionDAO=new ProductionDAOImpl();
       factureServiceProductiondao=new FactureServiceProductionDAOImpl();
       detailfactureServiceProductiondao=new DetailFactureServiceProductionDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("                       Etat Des Factures service");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(381, 11, 903, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1524, 427);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
}
		});
		
		
		scrollPane_1.setViewportView(table);
		table.setColumnControlVisible(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date BL", "N° BL","Type", "Client","Article","Famille Article", "Montant HT", "Montant TV","Montant TTC"
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
	    		

	    		
	    		if(combopardepot.getSelectedItem().equals(""))
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		
	    		if(dudateChooser.getDate()==null || audateChooser.getDate()==null)
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez choisir la date debut et la date fin ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		
	    		
	    		
	    	if(comboclient.getSelectedItem().equals("")
	    			&& txtNumfacture.getText().equals("") 
	    			&& dudateChooser.getDate()==null
	    			&& audateChooser.getDate()==null
	    			&& combopardepot.getSelectedIndex()==-1)
	    	{
	    		
	    		initialiser();
	    		
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
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1524, 51);
	    add(layeredPane_2);
	    
	    JLabel lblDateFacture = new JLabel("Du :");
	    lblDateFacture.setBounds(294, 13, 40, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     dudateChooser = new JDateChooser();
	     dudateChooser.setBounds(329, 11, 166, 26);
	     layeredPane_2.add(dudateChooser);
	     dudateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     		


	     		
	     	}
	     });
	     dudateChooser.addKeyListener(new KeyAdapter() {
	     	@Override
	     	public void keyPressed(KeyEvent e) {
	     		
	     		
	     		
	     	}
	     });
	     dudateChooser.setLocale(Locale.FRANCE);
	     dudateChooser.setDateFormatString("dd/MM/yyyy");
	     
	     JLabel lblAu = new JLabel("Au :");
	     lblAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblAu.setBounds(505, 13, 40, 24);
	     layeredPane_2.add(lblAu);
	     
	      audateChooser = new JDateChooser();
	     audateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     	
	     	}
	     });
	     audateChooser.setLocale(Locale.FRANCE);
	     audateChooser.setDateFormatString("dd/MM/yyyy");
	     audateChooser.setBounds(540, 11, 166, 26);
	     layeredPane_2.add(audateChooser);
	     
	     JLabel lblClient_1 = new JLabel("Client  :");
	     lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblClient_1.setBounds(719, 13, 70, 24);
	     layeredPane_2.add(lblClient_1);
	     
	      comboclient = new JComboBox();
	     comboclient.addItemListener(new ItemListener() {
	     	public void itemStateChanged(ItemEvent e) {
	     		
	     	}
	     });
	  
	     comboclient.setBounds(784, 13, 228, 24);
	     layeredPane_2.add(comboclient);
	     AutoCompleteDecorator.decorate(comboclient);
	 
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		initialiser();
	     		

	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(641, 120, 106, 23);
	     add(button);
	     combopardepot = new JComboBox();
	     combopardepot.setSelectedIndex(-1);
	     combopardepot.setBounds(1108, 13, 213, 24);
	     layeredPane_2.add(combopardepot);
	     
	     if(utilisateur.getLogin().equals("admin"))
	 		  {

	 		  listClient=clientdao.findAll();
	 		  

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
	 			 
	 			  
	 			  Depot depot=depotdao.findByCode(CODE_DEPOT_TANTAN);
	 			  if(depot!=null)
	 			  {
	 				 listClient=clientdao.findListClientByCodeDepot(depot.getCode());
	 				 combopardepot.addItem("");
					  combopardepot.addItem(depot.getLibelle());
					  mapparDepot.put(depot.getLibelle(), depot);
	 		     	
	 			  }
	 			  

				
			  
	 			  
	 			  
	 			  
	 		  }
	     comboclient.addItem("");
	     
	     JLabel label = new JLabel("Num Facture :");
	     label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     label.setBounds(63, 13, 97, 24);
	     layeredPane_2.add(label);
	     
	     txtNumfacture = new JTextField();
	     txtNumfacture.setColumns(10);
	     txtNumfacture.setBounds(169, 13, 102, 26);
	     layeredPane_2.add(txtNumfacture);
	     
	     JLabel label_1 = new JLabel("Depot  :");
	     label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     label_1.setBounds(1046, 12, 97, 24);
	     layeredPane_2.add(label_1);
	     
	     JButton btnExporterExcel = new JButton("Exporter Excel");
	     btnExporterExcel.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		

	    		Depot depot=mapparDepot.get(combopardepot.getSelectedItem());
	    		if(depot!=null)
	    		{
	    		
	    		String titre="Etat Des Factures Service ";
	    		String titrefeuille="Etat Des Factures Service ";
	    		ExporterTableVersExcel.tabletoexcelFactureServiceParOF(table, titre,titrefeuille);
	    		
	    		}else
	    		{


	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le depot SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		
	    		}
	    	
	     		
	     		
	     		
	     		
	     		
	     	}
	     });
	     btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     btnExporterExcel.setBounds(801, 600, 123, 32);
	     btnExporterExcel.setIcon(imgExcel);
	     add(btnExporterExcel);
	     
	     JButton btnAjouterOfFacture = new JButton("Ajouter OF Facture");
	     btnAjouterOfFacture.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		listProduction=productionDAO.findAll();
	     	listFactureService=factureServiceProductiondao.findAll();
	     		for(int i=0;i<listProduction.size();i++)
	     		{
	     			
	     			for(int j=0;j<listFactureService.size();j++)
	     			{
	     				
	     				if(i==j)
	     				{
	     					
	     				FactureServiceProduction factureServiceProduction=	listFactureService.get(j);
	     				factureServiceProduction.setNumOF(listProduction.get(i).getNumOF());
	     					factureServiceProductiondao.edit(factureServiceProduction);
	     					
	     					
	     					
	     				}
	     				
	     				
	     				
	     				
	     			}
	     			
	     			
	     			
	     		}
	     		
	     		JOptionPane.showMessageDialog(null, "OK");
	     		
	     		
	     	}
	     });
	     btnAjouterOfFacture.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     btnAjouterOfFacture.setBounds(78, 706, 138, 33);
	     btnAjouterOfFacture.setVisible(false);
	     add(btnAjouterOfFacture);
	     
	     JButton btnSupprimerFactureService = new JButton("Supprimer facture Service");
	     btnSupprimerFactureService.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		
	     		Depot depot =mapparDepot.get(combopardepot.getSelectedItem());
	     		listTransferMPTmp=transferStockMPDAO.findAll();
	     		listTransferPFTmp=transferStockPFDAO.findAll();
	     		
	     		/*
	     		listFactureAchatPF=factureAchatPFDAO.findFacturePFBetweenNumFacture(txtdu.getText());
	     		
	     		for(int i=0;i<listFactureAchatPF.size();i++)
	     		{
	     			
	     			FactureAchat factureAchatpf=listFactureAchatPF.get(i);
	     			
	     			listDetailFactureAchatPF=detailFactureAchatPFDAO.listeDetailFactureAchatByFacture(factureAchatpf.getId());
	     			for(int j=0;j<listDetailFactureAchatPF.size();j++)
	     			{
	     				
	     				detailFactureAchatPFDAO.delete(listDetailFactureAchatPF.get(j).getId());
	     				
	     				
	     			}
	     			
	     			TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(factureAchatpf.getCodeTransfer());
	     			listDetailTransferPF=detailTransferProduitFiniDAO.findByTransferStockPF(transferStockPF.getId());
	     			
	     			for(int k=0;k<listDetailTransferPF.size();k++)
	     			{
	     				
	     				detailTransferProduitFiniDAO.delete(listDetailTransferPF.get(k).getId());
	     				
	     			}
	     			
	     			transferStockPFDAO.delete(transferStockPF.getId());
	     			
	     			factureAchatPFDAO.delete(factureAchatpf.getId());
	     			
	     			
	     		}
	     		
	     		
	     		
	     		
	     		
	     		
	     			
	     		
//////////////////////////////////////////////////// Delete facture pf /////////////////////////////////////////////////////////////////////	     		
	     		
	     		listFacturePF=facturepfdao.listeFacturePFBetweenNumFacture(depot, txtdu.getText());
	     		for(int i=0;i<listFacturePF.size();i++)
	     		{
	     			
	     			FacturePF facturePF=listFacturePF.get(i);
	     			
	//////////////////////////////////////////// delete detailcompteclient and detailfacturepf /////////////////////////////////////////////////
	     			
	     			listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByFacture(facturePF.getId());
	     		    DetailCompteClient detailCompteClient=	detailCompteClientdao.findByFacture(facturePF.getId());
	     			detailCompteClientdao.delete(detailCompteClient);
	     			
	     			for(int j=0;j<listDetailFacturePF.size();j++)
	     			{
	     				
	     			detailFacturePfdao.delete(listDetailFacturePF.get(j).getId());	
	     				
	     				
	     			}
	     			
	   ////////////////////////////////////////// delete transfer and detailTransfer ///////////////////////////////////////  		
	     			
	     			TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(facturePF.getCodeTransfer());
	     			listDetailTransferPF=detailTransferProduitFiniDAO.findByTransferStockPF(transferStockPF.getId());
	     			
	     			for(int k=0;k<listDetailTransferPF.size();k++)
	     			{
	     				
	     				detailTransferProduitFiniDAO.delete(listDetailTransferPF.get(k).getId());
	     				
	     			}
	     			
	     			transferStockPFDAO.delete(transferStockPF.getId());
	     			
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     			
	     			
	     		//////////////////////////////////////////// delete facture pf /////////////////////////////////////
	     			
	     			
	     			
	     			facturepfdao.delete(facturePF.getId());
	     			
	     			
	     			
	     		}
	     		*/
///////////////////////////////////////////////////////////////////////////// delete facture service ////////////////////////////////////////////////////////////////////
	     		
	listFactureService=factureServiceProductiondao.findBetweenNumFacture() ;
	
	
	
	
	for(int i=0;i<listFactureService.size();i++)
	{
		
	FactureServiceProduction factureServiceProduction = listFactureService.get(i);	
	
	/*
		listDetailFactureServiceProduction=detailfactureServiceProductiondao.listeDetailFactureServiceProductionByFacture(factureServiceProduction.getId());
		
		for(int k=0;k<listDetailFactureServiceProduction.size();k++)
		{
			
			detailfactureServiceProductiondao.delete(listDetailFactureServiceProduction.get(k).getId());
			
			
		}
		*/
	////////////////////////////////////////// supprimer transfer MP /////////////////////////////////////////////////////////	
		
		listTransferMP.clear();
int m = 1 , x=0;
TransferStockMP transferStockMP =transferStockMPDAO.findTransferByCode(factureServiceProduction.getNumOF())	;

for(int w=0;w<listTransferMPTmp.size();w++)
{
	
	if(listTransferMPTmp.get(w).getCodeTransfer().equals(transferStockMP.getCodeTransfer()))
	{
	x=w+1;	
	TransferStockMP transferStockMPTMP=	listTransferMPTmp.get(x);
	while( transferStockMPTMP.getStatut().contains("SORTIE_PF"))
	{
		
		listTransferMP.add(listTransferMPTmp.get(x))	;
		
	x=x+1;
	if(x<listTransferMPTmp.size())
	{
		transferStockMPTMP =listTransferMPTmp.get(x);
	}
		
	
	if(transferStockMPTMP==null)
	{
		break;
	}
		
	}
		
		
		
	}
	
	
	
}
	

	

		
for(int t=0;t<listTransferMP.size();t++)	
{
	
listDetailTransferMP=detailTransferMPDAO.findByTransferStockMP(listTransferMP.get(t).getId())	;

for(int l=0;l<listDetailTransferMP.size();l++)
{
	
	detailTransferMPDAO.delete(listDetailTransferMP.get(l).getId());
	
}

transferStockMPDAO.delete(listTransferMP.get(t).getId());
	
}
		
listDetailTransferMP=detailTransferMPDAO.findByTransferStockMP(transferStockMP.getId());	

for(int h=0;h<listDetailTransferMP.size();h++)
{
	
	detailTransferMPDAO.delete(listDetailTransferMP.get(h).getId());
	
	
}
	

transferStockMPDAO.delete(transferStockMP.getId());
		
//////////////////////////////////////////////////////////////supprimer transfer PF////////////////////////////////////////////////////////////////////////////////////////	
	
TransferStockPF transferStockPF =transferStockPFDAO.findByCodeTransfert(factureServiceProduction.getNumOF());
int z=1 , l=1;

listTransferPF.clear();

TransferStockPF transferStockPFTMP =transferStockPFDAO.findById(transferStockPF.getId());

x=0;
for(int w=0;w<listTransferPFTmp.size();w++)
{
	
	if(listTransferPFTmp.get(w).getCodeTransfer().equals(transferStockPFTMP.getCodeTransfer()))
	{
	x=w+1;	
	TransferStockPF transferStockPFTMPPF=	listTransferPFTmp.get(x);
	while( transferStockPFTMPPF.getStatut().contains(ETAT_TRANSFER_STOCK_ENTRER_MP))
	{
		
		listTransferPF.add(listTransferPFTmp.get(x))	;
		
	x=x+1;
	
	if(x<listTransferPFTmp.size())
	{
		transferStockPFTMPPF =listTransferPFTmp.get(x);	
	}
	
	
	if(transferStockPFTMPPF==null)
	{
		break;
	}
		
	}
		
		
		
	}
	
	
	
}




for(int t=0;t<listTransferPF.size();t++)
{
	
TransferStockPF TransferstockPFTmp=listTransferPF.get(t)	;

listDetailTransferPF=detailTransferProduitFiniDAO.findByTransferStockPF(TransferstockPFTmp.getId());
	
for(int d=0;d<listDetailTransferPF.size();d++)
{
	
	detailTransferProduitFiniDAO.delete(listDetailTransferPF.get(d).getId());
	
}

transferStockPFDAO.delete(TransferstockPFTmp.getId());
	
}

listDetailTransferPF=detailTransferProduitFiniDAO.findByTransferStockPF(transferStockPF.getId()); 
for(int r=0;r<listDetailTransferPF.size();r++)
{
	
	detailTransferProduitFiniDAO.delete(listDetailTransferPF.get(r).getId());
	
	
}

transferStockPFDAO.delete(transferStockPF.getId());

/*
Production production=productionDAO.findByNumOF(factureServiceProduction.getNumOF(), depot.getCode());
production.setService(ETAT_OF_ANNULER);
production.setStatut(ETAT_OF_ANNULER);
productionDAO.edit(production);

factureServiceProductiondao.delete(factureServiceProduction.getId());
*/
		
}
	
	
	
	JOptionPane.showMessageDialog(null, "Modification effectué");
	     		
	 	     	}
	     });
	     btnSupprimerFactureService.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     btnSupprimerFactureService.setBounds(717, 703, 183, 33);
	     btnSupprimerFactureService.setVisible(false);
	     add(btnSupprimerFactureService);
	     
	     txtdu = new JTextField();
	     txtdu.setColumns(10);
	     txtdu.setBounds(345, 706, 102, 26);
	     txtdu.setVisible(false);
	     add(txtdu);
	     
	     JLabel lblDu = new JLabel("Du :");
	     lblDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblDu.setBounds(286, 706, 50, 24);
	     lblDu.setVisible(false);
	     add(lblDu);
	     
	     txtau = new JTextField();
	     txtau.setColumns(10);
	     txtau.setBounds(535, 706, 145, 26);
	     txtau.setVisible(false);
	     add(txtau);
	     
	     JLabel lblAu_1 = new JLabel("Au :");
	     lblAu_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblAu_1.setBounds(472, 706, 97, 24);
	     lblAu_1.setVisible(false);
	     add(lblAu_1);
	     
	    
	     
	      
	     
	    
	     int i=0;
	     while(i<listClient.size())
	     {
	    	 
	    	Client client=listClient.get(i);
	    	comboclient.addItem(client.getNom());
	    	 mapClient.put(client.getNom(), client);
	    	 
	    	 i++;
	     }
	    
	
	      
	      
	      
	     
	      Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	      listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
	      
for(int j=0;j<listMagasin.size();j++)
{
	Magasin magasin=listMagasin.get(j);
	combomagasin.addItem(magasin.getLibelle());
	mapMagasin.put(magasin.getLibelle(), magasin);
}
		
		}
	
	


	void initialiser()
	{
combodepot.setSelectedIndex(-1);
comboclient.setSelectedItem("");
dudateChooser.setCalendar(null);
 audateChooser.setCalendar(null);
txtNumfacture.setText("");
 InitialiseTableauFacture();	
	}

	
	
	void chargerListeFacture()
	{

		
		Client clientpf=mapClient.get(comboclient.getSelectedItem());
		Depot depot=mapparDepot.get(combopardepot.getSelectedItem());				
		
		listFactureService.clear();
		listFactureService=factureServiceProductiondao.findByNumFcatureClientDateFactureDepotEtatRegle(txtNumfacture.getText(),clientpf, dudateChooser.getDate(), audateChooser.getDate(), depot,Constantes.ETAT_REGLE);	
		
		//listDetailFactureServiceProduction=detailfactureServiceProductiondao.findByNumFcatureClientDateFactureDepotEtatRegle(txtNumfacture.getText(),clientpf, dudateChooser.getDate(), audateChooser.getDate(), depot);
		
	    afficher_tableFactureServiceProduction(listFactureService);
			
		
	}
	
	
	
	
	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date facture", "Num facture", "Client","Article","Quantite", "prix","Montant HT", "Montant TV","Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false, false
				};
				
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
		table.getColumnModel().getColumn(6).setPreferredWidth(114);
		table.getColumnModel().getColumn(7).setPreferredWidth(136);
		table.getColumnModel().getColumn(8).setPreferredWidth(136);
}
	
	
	
	void afficher_tableFactureServiceProduction(List<FactureServiceProduction> listFactureServiceProduction)
	{
		
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Date facture", "Num facture","Num OF", "Client", "Famille", "Sous Famille","Article","Quantite"
						}
					) {
						boolean[] columnEditables = new boolean[] {
								false,false,false,false,false,false,false,false
						};
						
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					};
				table.setModel(modelefacture);
				int i=0;
				
				
				 
				while(i<listFactureServiceProduction.size())
				{	
				
			FactureServiceProduction factureServiceProduction=	listFactureServiceProduction.get(i);
					
		TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(factureServiceProduction.getNumOF());	
		List<DetailTransferProduitFini> ListedetailTransferProduitFini= detailTransferProduitFiniDAO.findByTransferStockPF(transferStockPF.getId());
		
	Production production=productionDAO.findByNumOF(factureServiceProduction.getNumOF(),factureServiceProduction.getDepot().getCode());
					
					List<CoutMP> ListeCoutMP=productionDAO.listeCoutMP(production.getId());
																								
					Object []ligne={factureServiceProduction.getDateFacture(),factureServiceProduction.getNumFacture(),factureServiceProduction.getNumOF(), factureServiceProduction.getClient().getNom(),ListedetailTransferProduitFini.get(0).getSousFamille().getFamileArticlePF().getLiblle(),ListedetailTransferProduitFini.get(0).getSousFamille().getLiblle(),  production.getArticles().getLiblle(),production.getQuantiteReel() };

					modelefacture.addRow(ligne);	
					
					
					
				
				
				
					i++;
				}
			
}
	}


