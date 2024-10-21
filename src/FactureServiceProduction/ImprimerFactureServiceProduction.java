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
import util.ConverterNumberToWords;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
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
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceProductionDAO;
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
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailPrixArticle;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
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
import dao.entity.Sequenceur;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
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
import javax.swing.JTable;


public class ImprimerFactureServiceProduction extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;
	private DefaultTableModel	 modeleProductionService;
	private JXTable  tableArticle = new JXTable();
	private JXTable table = new JXTable();
	private List<Magasin> listMagasinPF =new ArrayList<Magasin>();
	private List<Production> listProductionService =new ArrayList<Production>();
	private List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
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
	private List<FactureServiceProduction> listFactureServiceProduction =new ArrayList<FactureServiceProduction>();
	private List<FactureServiceProduction> listFactureServiceProductionTmp =new ArrayList<FactureServiceProduction>();
	private List<DetailFactureServiceProduction> listDetailFactureServiceProduction =new ArrayList<DetailFactureServiceProduction>();
	private List<FacturePF> listFacturePFMAJ =new ArrayList<FacturePF>();
	private List<Client> listClient=new ArrayList<Client>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	private Map< String, FactureServiceProduction> mapImprimer = new HashMap<>();
	private Map< String, FactureServiceProduction> mapFactureImprimer = new HashMap<>();
	private static SequenceurDAO sequenceurDAO=ProdLauncher.sequenceurDAO;
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, Client> mapClient= new HashMap<>();
	private Map< String, Client> mapParClient= new HashMap<>();
	private Map< String, Client> mapClientparCode= new HashMap<>();
	private ProductionDAO productiondao;
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
	private FactureServiceProductionDAO factureServiceProductiondao;
	private FacturePF facturePF;
	private DetailTransferProduitFiniDAO detailTransferStockPFdao;
private DetailFacturePFDAO detailFacturePfdao;
private ClientDAO clientdao;
private ImageIcon imgImprimer;
private ImageIcon imgExcel;
private FamilleArticlesPFDAO familleArticleDAO;
private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
private DetailPrixArticleDAO detailPrixArticleDAO;

TransferStockPF transferStock ;
private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
private TransferStockPFDAO transferStockPFDAO;
	ChargeProduction chargeproduction;
	private JTextField txtnumbl;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	 private JDateChooser dateChooserfacture;
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
	JCheckBox checkboxGratuits = new JCheckBox("Gratuit");
	 JLabel labelPrixMin = new JLabel("");
	 JLabel labelPrixMax = new JLabel("");
	  JLabel stockDisponible = new JLabel("");
	  JLabel labelMarge = new JLabel("");
	  JButton supprimer_facture = new JButton();
	  private DetailFactureServiceProductionDAO detailfactureserviceproductionDAO;
	  private FactureServiceProduction factureserviceproduction;
	  private JTextField txttotalmontantTTC;
	  private JTextField txtMontantTVA;
	  private JTextField txtMontantHT;
	  JButton btnSelectionnertout = new JButton();
	  JButton btnDeslectionnerTout = new JButton();
	  JDateChooser AudateChooser = new JDateChooser();
	  MatierePremiereDAO matierePremiereDAO;
	/**
	 * Launch the application.
	 */
	  
	/**
	 * Create the application.
	 */
	public ImprimerFactureServiceProduction() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1562, 1062);
      
	
        try{ 
        	
        	   imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
        	   imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
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
         	factureServiceProductiondao=new FactureServiceProductionDAOImpl();
         	detailFacturePfdao=new DetailFacturePFDAOImpl();
         	clientdao=new ClientDAOImpl();
         	articleDAO=new ArticlesDAOImpl();
         	stockpfDAO=new StockPFDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         	compteclientdao=new CompteClientDAOImpl();
         	
         	listArticle=articleDAO.findAll();
         	
        	familleArticleDAO=new FamilleArticlesPFDAOImpl();
         	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
         	listFamilleArticle=familleArticleDAO.findAll();
        	detailPrixArticleDAO=new DetailPrixArticleDAOImpl();
        	productiondao=new ProductionDAOImpl();
        	detailfactureserviceproductionDAO=new DetailFactureServiceProductionDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
        tableArticle.setSortable(false);
        tableArticle.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		
       		if(tableArticle.getSelectedRow()!=-1)
       		{

       		
       		
       		
       		
       		}
       			
       		
       		
       		}
       });
        
       tableArticle.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Num OF","Article","Quantite"
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
				  		     	scrollPane.setBounds(10, 504, 1458, 318);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Productions Service");
				  		     	titledSeparator.setBounds(10, 467, 1458, 30);
				  		     	add(titledSeparator);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 408, 1458, 51);
		add(layeredPane_1);
		
		JLabel label = new JLabel("Code  :");
		label.setBounds(237, 14, 89, 24);
		layeredPane_1.add(label);
		
		txtnumbl = new JTextField();
		txtnumbl.setEditable(false);
		txtnumbl.setColumns(10);
		txtnumbl.setBounds(284, 13, 129, 26);
		layeredPane_1.add(txtnumbl);
	
		JLabel label_1 = new JLabel("Date  :");
		label_1.setBounds(422, 15, 62, 24);
		layeredPane_1.add(label_1);
		
		 dateChooserfacture = new JDateChooser();
		dateChooserfacture.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {}
		});
		dateChooserfacture.setLocale(Locale.FRANCE);
		dateChooserfacture.setDateFormatString("dd/MM/yyyy");
		dateChooserfacture.setBounds(462, 13, 139, 26);
		layeredPane_1.add(dateChooserfacture);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(611, 15, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(659, 15, 144, 24);
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
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture");
		titledSeparator_2.setBounds(10, 375, 1458, 30);
		add(titledSeparator_2);
		
		JLabel lblConslterLesFactures = new JLabel("           Imprimer les Factures  Services par :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(296, 11, 904, 35);
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
					
					if(listFactureServiceProduction.size()!=0)
					{
						detailfactureserviceproductionDAO.ViderSession();
						listDetailFactureServiceProduction.clear();
						factureserviceproduction=listFactureServiceProduction.get(table.getSelectedRow());
						txtnumbl.setText(factureserviceproduction.getNumFacture());
					    dateChooserfacture.setDate(factureserviceproduction.getDateFacture());
					    combodepot.setSelectedItem(factureserviceproduction.getDepot().getLibelle());
				        txttotalmontantTTC.setText(factureserviceproduction.getMontantTTC().setScale(2, RoundingMode.HALF_UP)+"");
				        txtMontantHT.setText(factureserviceproduction.getMontantHT().setScale(2, RoundingMode.HALF_UP)+"");
				        txtMontantTVA.setText(factureserviceproduction.getMontantTVA().setScale(2, RoundingMode.HALF_UP)+"");
					    listDetailFactureServiceProduction=detailfactureserviceproductionDAO.listeDetailFactureServiceProductionByFacture(factureserviceproduction.getId());
					    afficher_tableDetailFactureServiceProduction(listDetailFactureServiceProduction);
						
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
					"Num Facture", "Date Facture", "Type", "Client", "Depot","Montant HT","Montant TVA", "Montant TTC","Imprimer"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(114);
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
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1458, 51);
	    add(layeredPane_2);
	    
	    JLabel lblNumFacture = new JLabel("Num Facture :");
	    lblNumFacture.setBounds(165, 11, 97, 24);
	    layeredPane_2.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtparnumBL = new JTextField();
	    txtparnumBL.setBounds(271, 11, 102, 26);
	    layeredPane_2.add(txtparnumBL);
	    util.Utils.copycoller(txtparnumBL);
	    txtparnumBL.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {}
	    });
	    txtparnumBL.setColumns(10);
	    
	    JLabel lblClient_1 = new JLabel("Client :");
	    lblClient_1.setBounds(410, 11, 56, 24);
	    layeredPane_2.add(lblClient_1);
	    lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    JLabel lblDateFacture = new JLabel("Du :");
	    lblDateFacture.setBounds(669, 11, 47, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     pardateChooser = new JDateChooser();
	     pardateChooser.setBounds(712, 9, 166, 26);
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
	     combopardepot.setBounds(1267, 14, 151, 24);
	     layeredPane_2.add(combopardepot);
	     combopardepot.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {}
	     });
	     combopardepot.setSelectedIndex(-1);
	     
	     JLabel lblDepot = new JLabel("Depot  :");
	     lblDepot.setBounds(1205, 13, 97, 24);
	     layeredPane_2.add(lblDepot);
	     lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));


	     
	      comboparclient = new JComboBox();
	     comboparclient.setSelectedIndex(-1);
	     comboparclient.setBounds(463, 11, 178, 24);
	     layeredPane_2.add(comboparclient);
	     AutoCompleteDecorator.decorate(comboparclient);
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
	
	    Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	    
	    
	     labelMarge = new JLabel("");
	    labelMarge.setForeground(Color.RED);
	    labelMarge.setFont(new Font("Tahoma", Font.BOLD, 14));
	    labelMarge.setBounds(5, 1015, 600, 30);
	    add(labelMarge);
	    
	    JButton button_1 = new JButton("Imprimer");
	    button_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		MatierePremier THERRES=matierePremiereDAO.findByCode(MATIERE_PREMIERE_THERRES);
	  			MatierePremier VERRES=matierePremiereDAO.findByCode(MATIERE_PREMIERE_VERRES);
	    		mapImprimer.clear();
	    		mapFactureImprimer.clear();
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
					
					BigDecimal TotalTTC=BigDecimal.ZERO;
					BigDecimal TotalTVA=BigDecimal.ZERO;
					BigDecimal TotalTHT=BigDecimal.ZERO;
					
					
					for(int k=0;k<listFactureServiceProduction.size();k++)
					{
						FactureServiceProduction facturepServiceProductionmp=listFactureServiceProduction.get(k);
						
						if(mapImprimer.containsKey(facturepServiceProductionmp.getNumFacture()))
						{
							 TotalTTC=BigDecimal.ZERO;
							 TotalTVA=BigDecimal.ZERO;
							 TotalTHT=BigDecimal.ZERO;
							
							if(mapFactureImprimer.get(facturepServiceProductionmp.getNumFacture())==null)
							{
								
								mapFactureImprimer.put(facturepServiceProductionmp.getNumFacture(), facturepServiceProductionmp);
								
								
								detailfactureserviceproductionDAO.ViderSession();
								listDetailFactureServiceProduction.clear();
								listDetailFactureServiceProduction=detailfactureserviceproductionDAO.findByNumFcatureClientDateFactureDepotEtatRegle(facturepServiceProductionmp.getNumFacture(), null, null, null, facturepServiceProductionmp.getDepot());
								
						 		if(listDetailFactureServiceProduction.size()!=0)
					    		{
						 			
						 			
						 		listFactureServiceProductionTmp=factureServiceProductiondao.findByNumFacture(facturepServiceProductionmp.getNumFacture());	
						 			
						 		for(int t=0;t<listFactureServiceProductionTmp.size();t++)
						 		{
						 			TotalTTC=TotalTTC.add(listFactureServiceProductionTmp.get(t).getMontantTTC());
						 			TotalTVA=TotalTVA.add(listFactureServiceProductionTmp.get(t).getMontantTVA());
						 			TotalTHT=TotalTHT.add(listFactureServiceProductionTmp.get(t).getMontantHT());
						 			
						 		}
						 		
						 		
					    			
					    			Map parameters = new HashMap();
					    			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  	String dateFacture=dateFormat.format(facturepServiceProductionmp.getDateFacture());
						  		parameters.put("dateFacture", dateFacture);
						  		
						  		if(TotalTVA.compareTo(BigDecimal.ZERO)!=0)
						  		{
						  			parameters.put("TotalTTC",TotalTTC);
							  		parameters.put("TotalTVA",TotalTVA);
							  		parameters.put("TotalTHT",TotalTHT);
						  			
						  		}else
						  		{
						  			
							  		parameters.put("TotalTHT",TotalTHT);
						  			
						  		}
						  		
						  		parameters.put("client",facturepServiceProductionmp.getClient().getNom());
						  		if(facturepServiceProductionmp.getModeReglement()!=null)
						  		{
						  			parameters.put("reglement",facturepServiceProductionmp.getModeReglement());
						  		}else
						  		{
						  			parameters.put("reglement","");	
						  		}
						  		
						  		if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
								{
									parameters.put("ice", Constantes.ICE_ETP);
								}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
								{
									parameters.put("ice", Constantes.ICE_AHLBRAHIM);
								}
						  		if(facturepServiceProductionmp.getClient().getIce()!=null)
						  		{
						  			parameters.put("iceclient",facturepServiceProductionmp.getClient().getIce());
						  		}else
						  		{
						  			parameters.put("iceclient","");
						  		}
						  		
						  		parameters.put("numfacture", facturepServiceProductionmp.getNumFacture());
						  		
						  		String sommetowords="";
						  		
						  	//double totalttc=Double.valueOf(txtnetapayer.getText());
						  		txttotalmontantTTC.setText( TotalTTC.setScale(2, RoundingMode.HALF_UP)+"");
						  		
						  		Production production=productiondao.findByNumOFStatut(facturepServiceProductionmp.getNumOF(), Constantes.ETAT_OF_TERMINER);
						  		
						  		
						  		
						  		for(int d=0;d<listDetailFactureServiceProduction.size();d++)
						  		{
						  			
						  			
						  			if(!listDetailFactureServiceProduction.get(d).getArticle().equals(THERRES.getNom()) && !listDetailFactureServiceProduction.get(d).getArticle().equals(VERRES.getNom()))
						  			{
						  				
						  				listDetailFactureServiceProduction.get(d).setArticle(Constantes.DESIGNATION_FACTURE_SERVICE_PRODUCTION+listDetailFactureServiceProduction.get(d).getArticle());
						  				
						  			}
						  			
						  			
						  			
						  			for(int t=0;t<production.getListCoutMP().size();t++)
									{
										
										CoutMP coutMP=production.getListCoutMP().get(t);
										if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
										{
											
											if(listDetailFactureServiceProduction.get(d).getArticle().equals(coutMP.getMatierePremier().getNom()))
											{
												if(coutMP.getMagasin()!=null)
												{
													
													if(coutMP.getMagasin().getId()==coutMP.getProdcutionCM().getMagasinStockage().getId() && coutMP.getProdcutionCM().getService().equals(Constantes.TRANSFERE_BL_FACTURE))
													{
										  				listDetailFactureServiceProduction.get(d).setArticle(listDetailFactureServiceProduction.get(d).getArticle()+DESIGNATION_FACTURE_OFFRE_SERVICE_PRODUCTION);
										  				listDetailFactureServiceProduction.get(d).setPrix(null);
										  				listDetailFactureServiceProduction.get(d).setMontantHT (null);
													}

												}
												
								  				 
											}
											 
											
										}
												
									}
						  			
						  			
						  			
						  			
						  			
						  		}
						  	
						  		
						  		if(TotalTVA.compareTo(BigDecimal.ZERO)!=0)
						  		{
						  			
						  			String x=String.valueOf(TotalTTC.setScale(2, RoundingMode.HALF_UP)).replace(".", ",");
									
									sommetowords= ConverterNumberToWords.converter(x);
									parameters.put("NumberToWords",sommetowords);
									
									JasperUtils.imprimerFactureService(listDetailFactureServiceProduction,parameters,apercu);
						  			
						  		}else
						  		{
						  			String x=String.valueOf(TotalTTC.setScale(2, RoundingMode.HALF_UP)).replace(".", ",");
									
									sommetowords= ConverterNumberToWords.converter(x);
									parameters.put("NumberToWords",sommetowords);
									
									JasperUtils.imprimerFactureServiceSansTVA(listDetailFactureServiceProduction,parameters,apercu);
						  			
						  		}
								
						  		
					    		}else
					    		{
					    			JOptionPane.showMessageDialog(null, "La list est vide ","Erreur",JOptionPane.ERROR_MESSAGE);
					    			return;
					    		}
								
								
								
							}
							
						
							
							
						}
						
					}
					
			
					
					
				}
	    		
	   
	    		
	    		
	    	}
	    });
	    button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button_1.setBounds(700, 852, 112, 32);
	    button_1.setIcon(imgImprimer);
	    add(button_1);
	    
	    JLabel label_2 = new JLabel("Montant TTc :");
	    label_2.setBounds(1209, 937, 112, 26);
	    add(label_2);
	    
	    txttotalmontantTTC = new JTextField();
	    txttotalmontantTTC.setEditable(false);
	    txttotalmontantTTC.setColumns(10);
	    txttotalmontantTTC.setBounds(1316, 937, 151, 26);
	    add(txttotalmontantTTC);
	    
	    txtMontantTVA = new JTextField();
	    txtMontantTVA.setEditable(false);
	    txtMontantTVA.setColumns(10);
	    txtMontantTVA.setBounds(1316, 900, 151, 26);
	    add(txtMontantTVA);
	    
	    JLabel lblMontantTva = new JLabel("Montant TVA :");
	    lblMontantTva.setBounds(1209, 900, 112, 26);
	    add(lblMontantTva);
	    
	    txtMontantHT = new JTextField();
	    txtMontantHT.setEditable(false);
	    txtMontantHT.setColumns(10);
	    txtMontantHT.setBounds(1316, 858, 151, 26);
	    add(txtMontantHT);
	    
	    JLabel lblMontantHt = new JLabel("Montant HT :");
	    lblMontantHt.setBounds(1209, 858, 112, 26);
	    add(lblMontantHt);
	    
	    JButton btnExportToExcel = new JButton("Export To Excel");
	    btnExportToExcel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		

	    		
	    		if(listDetailFactureServiceProduction.size()!=0)
	    		{
	    			/*
	    			Map parameters = new HashMap();
	    			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  		  	String dateFacture=dateFormat.format(listDetailFactureServiceProduction.get(0).getFactureService().getDateFacture());
		  		  parameters.put("dateFacture", dateFacture);
		  		parameters.put("TotalTTC",new BigDecimal(txttotalmontantTTC.getText()));
		  		parameters.put("TotalTVA",new BigDecimal(txtMontantTVA.getText()));
		  		parameters.put("TotalTHT",new BigDecimal(txtMontantHT.getText()));
		  		parameters.put("client",listDetailFactureServiceProduction.get(0).getFactureService().getClient().getNom());
		  		if(listDetailFactureServiceProduction.get(0).getFactureService().getModeReglement()!=null)
		  		{
		  			parameters.put("reglement",listDetailFactureServiceProduction.get(0).getFactureService().getModeReglement());
		  		}else
		  		{
		  			parameters.put("reglement","");	
		  		}
		  		
		  		if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
				{
					parameters.put("ice", Constantes.ICE_ETP);
				}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
				{
					parameters.put("ice", Constantes.ICE_AHLBRAHIM);
				}
		  		if(listDetailFactureServiceProduction.get(0).getFactureService().getClient().getIce()!=null)
		  		{
		  			parameters.put("iceclient",listDetailFactureServiceProduction.get(0).getFactureService().getClient().getIce());
		  		}else
		  		{
		  			parameters.put("iceclient","");
		  		}
		  		
		  		parameters.put("numfacture", txtnumbl.getText());
		  		
		  		String sommetowords="";
		  		
		  	//double totalttc=Double.valueOf(txtnetapayer.getText());
		  		txttotalmontantTTC.setText( new BigDecimal(txttotalmontantTTC.getText()).setScale(2, RoundingMode.HALF_UP)+"");
				String x=txttotalmontantTTC.getText().replace(".", ",");
				
				sommetowords= ConverterNumberToWords.converter(x);
				parameters.put("NumberToWords",sommetowords);
				
				
				
				JasperUtils.imprimerFactureServiceToExcel (listDetailFactureServiceProduction,parameters);
				*/
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////
				

    			
    			Map parameters = new HashMap();
    			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String dateFacture=dateFormat.format(listDetailFactureServiceProduction.get(0).getFactureService().getDateFacture());
	  		parameters.put("dateFacture", dateFacture);
	  		
	  		if(listDetailFactureServiceProduction.get(0).getFactureService().getClient().isTva()==true)
	  		{
	  			parameters.put("TotalTTC",new BigDecimal(txttotalmontantTTC.getText()));
		  		parameters.put("TotalTVA",new BigDecimal(txtMontantTVA.getText()));
		  		parameters.put("TotalTHT",new BigDecimal(txtMontantHT.getText()));
	  			
	  		}else
	  		{
	  			
		  		parameters.put("TotalTHT",new BigDecimal(txtMontantHT.getText()));
	  			
	  		}
	  		
	  		parameters.put("client",listDetailFactureServiceProduction.get(0).getFactureService().getClient().getNom());
	  		if(listDetailFactureServiceProduction.get(0).getFactureService().getModeReglement()!=null)
	  		{
	  			parameters.put("reglement",listDetailFactureServiceProduction.get(0).getFactureService().getModeReglement());
	  		}else
	  		{
	  			parameters.put("reglement","");	
	  		}
	  		
	  		if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
			{
				parameters.put("ice", Constantes.ICE_ETP);
			}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
			{
				parameters.put("ice", Constantes.ICE_AHLBRAHIM);
			}
	  		if(listDetailFactureServiceProduction.get(0).getFactureService().getClient().getIce()!=null)
	  		{
	  			parameters.put("iceclient",listDetailFactureServiceProduction.get(0).getFactureService().getClient().getIce());
	  		}else
	  		{
	  			parameters.put("iceclient","");
	  		}
	  		
	  		parameters.put("numfacture", txtnumbl.getText());
	  		
	  		String sommetowords="";
	  		
	  	//double totalttc=Double.valueOf(txtnetapayer.getText());
	  		txttotalmontantTTC.setText( new BigDecimal(txttotalmontantTTC.getText()).setScale(2, RoundingMode.HALF_UP)+"");
	  		
	  		if(listDetailFactureServiceProduction.get(0).getFactureService().getClient().isTva()==true)
	  		{
	  			
	  			String x=txttotalmontantTTC.getText().replace(".", ",");
				
				sommetowords= ConverterNumberToWords.converter(x);
				parameters.put("NumberToWords",sommetowords);
				
				JasperUtils.imprimerFactureServiceToExcel (listDetailFactureServiceProduction,parameters);
	  			
	  		}else
	  		{
	  			String x=txtMontantHT.getText().replace(".", ",");
				
				sommetowords= ConverterNumberToWords.converter(x);
				parameters.put("NumberToWords",sommetowords);
				
				JasperUtils.imprimerFactureServiceToExcelSansTVA (listDetailFactureServiceProduction,parameters);
	  			
	  		}
			
	  		
    		
				
				
				
				
				
				
				
				
				
		  		
	    		}else
	    		{
	    			JOptionPane.showMessageDialog(null, "La list est vide ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		
	    		
	    	
	    		
	    		
	    	}
	    });
	    btnExportToExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnExportToExcel.setBounds(483, 852, 207, 32);
	    btnExportToExcel.setIcon(imgExcel);
	    add(btnExportToExcel);
	    
	     btnSelectionnertout = new JButton();
	     btnSelectionnertout.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		

	    		
	    		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(true, i, 8);
	     		}
	    		
	    	
	     		
	     	}
	     });
	    btnSelectionnertout.setToolTipText("Selectionner Tout");
	    btnSelectionnertout.setIcon(imgSelectAll);
	    btnSelectionnertout.setBounds(1423, 117, 26, 26);
	    add(btnSelectionnertout);
	    
	     btnDeslectionnerTout = new JButton();
	     btnDeslectionnerTout.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		

	    		for(int i=0;i<table.getRowCount();i++)
	     		{
	     			table.setValueAt(false, i, 8);
	     		}
	    	
	     	}
	     });
	    btnDeslectionnerTout.setToolTipText("deselectionner Tout");
	    btnDeslectionnerTout.setIcon(imgDeselectAll);
	    btnDeslectionnerTout.setBounds(1394, 117, 26, 26);
	    add(btnDeslectionnerTout);
	    ChargerProductionService();
	    
	    listClient=clientdao.findListClientByCodeDepot(depot.getCode());
	    comboparclient.addItem("");
	    
	    JLabel lblAu = new JLabel("Au :");
	    lblAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblAu.setBounds(913, 13, 47, 24);
	    layeredPane_2.add(lblAu);
	    
	     AudateChooser = new JDateChooser();
	    AudateChooser.setLocale(Locale.FRANCE);
	    AudateChooser.setDateFormatString("dd/MM/yyyy");
	    AudateChooser.setBounds(956, 11, 166, 26);
	    layeredPane_2.add(AudateChooser);
	    for(int i=0;i<listClient.size();i++)
	    {
	    	Client client=listClient.get(i);
	    comboparclient.addItem(client.getNom());
	    	
	    mapClient.put(client.getNom(), client);
	    	
	    }

		}
	

	boolean remplirMapImprimer(){
		boolean trouve=false;
		int i=0;
				
		for(int j=0;j<table.getRowCount();j++){
			
			boolean imprimer=(boolean) table.getValueAt(j, 8);
			if(imprimer==true ){
				FactureServiceProduction factureServiceProduction=listFactureServiceProduction.get(j);
				
				if(mapImprimer.get(factureServiceProduction.getNumFacture())==null)
				{
					
					mapImprimer.put(factureServiceProduction.getNumFacture(), factureServiceProduction);
					i++;
					trouve=true;
				}
				
				
				
			}
			
		}
		return trouve;
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
	
void ChargerProductionService()
{
	listProductionService.clear();
	listProductionService=productiondao.listeProductionServiceTerminer(ETAT_OF_TERMINER, utilisateur.getCodeDepot(), ETAT_TRANSFER_STOCK_SERVICE);
	afficher_tableProductionService(listProductionService);
}
	
	
	void initialiserFacture()
	{
		txtnumbl.setText("");
		dateChooserfacture.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.removeAllItems();
		combomagasin.setSelectedIndex(-1);
		comboFournisseur.removeAllItems();
		comboFournisseur.setSelectedIndex(-1);
	}

	void initialiser()
	{

	
	
	
	}

	

	
	void InitialiseTableau()
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article","Quantite","Prix Unitaire","Montant HT"
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
						"Num Facture", "Date Facture", "Type", "Client", "Depot", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class
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

}
	
	
	
	void afficher_tableDetailFactureServiceProduction(List<DetailFactureServiceProduction> listDetailFactureServiceProduction)
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article","Quantite","Prix Unitaire","Montant HT"
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
		int i=0;
		 
		while(i<listDetailFactureServiceProduction.size())
		{	
		DetailFactureServiceProduction detailfactureServiceProduction=listDetailFactureServiceProduction.get(i);
			
			Object []ligne={detailfactureServiceProduction.getArticle() , detailfactureServiceProduction.getQuantite() , detailfactureServiceProduction.getPrix().setScale(2,RoundingMode.DOWN),detailfactureServiceProduction.getMontantHT().setScale(2, RoundingMode.HALF_UP)};

			modeleArticle.addRow(ligne);
			i++;
		}
}
	
	
	
	void chargerListeFacture()
	{

		
		Client clientpf=mapClient.get(comboparclient.getSelectedItem());
		Depot depot=mapparDepot.get(combopardepot.getSelectedItem());
		
		initialiserFacture();
		initialiser();
		InitialiseTableau();
		
		
		if(pardateChooser.getDate()!=null && AudateChooser.getDate()==null)
		{
			
			AudateChooser.setDate(pardateChooser.getDate());
		}
		
		
		if(pardateChooser.getDate()==null && AudateChooser.getDate()!=null)
		{
			
			pardateChooser.setDate(AudateChooser.getDate());
		}
		
		if(pardateChooser.getDate().compareTo( AudateChooser.getDate())>0)
		{
			JOptionPane.showMessageDialog(null, "La date Du doit etre inferieur ou égale la date Au SVP !!!");
			return;
		}
		
		
		
		
		
	
		listFactureServiceProduction.clear();
		//factureServiceProductiondao=ProdLauncher.factureServiceProductiondao;
		
		
		//listFacturePFTmp=facturepfdao.findByNumFcatureClientDateFactureDepotEtatRegle(txtparnumBL.getText(),clientpf, pardateChooser.getDate(), depot,Constantes.ETAT_REGLE);		
    	listFactureServiceProduction=factureServiceProductiondao.findByNumFcatureClientDateFactureDepotEtatRegle(txtparnumBL.getText(),clientpf, pardateChooser.getDate(),AudateChooser.getDate(), depot,Constantes.ETAT_REGLE);
		
	    afficher_tableFactureServiceProduction(listFactureServiceProduction);
			
		
	}
	
	
	
	
	void afficher_tableFactureServiceProduction(List<FactureServiceProduction> listFacture)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num Facture", "Date Facture", "Type", "Client", "Depot", "Montant HT", "Montant TVA", "Montant TTC","Imprimer"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,Date.class,String.class,String.class,String.class,String.class,String.class,String.class,Boolean.class
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
		 
		while(i<listFacture.size())
		{	
		FactureServiceProduction factureserviceproduction=listFacture.get(i);
		
		Date datefacture=factureserviceproduction.getDateFacture();
			
			Object []ligne={factureserviceproduction.getNumFacture(),datefacture,factureserviceproduction.getType(),factureserviceproduction.getClient().getNom(),factureserviceproduction.getDepot().getLibelle(),factureserviceproduction.getMontantHT().setScale(2, RoundingMode.HALF_UP),factureserviceproduction.getMontantTVA().setScale(2, RoundingMode.HALF_UP), factureserviceproduction.getMontantTTC().setScale(2, RoundingMode.HALF_UP),false};

			modelefacture.addRow(ligne);
			i++;
		}
}
	
	
	void afficher_tableProductionService(List<Production> listProductionService)
	{
		modeleProductionService =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num OF", "Article", "Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false
				};
				Class[] columnTypes = new Class[] {
						String.class,String.class,String.class
					};
				  public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		int i=0;
		 
		while(i<listProductionService.size())
		{	
		Production productionservice=listProductionService.get(i);
		
		
			
			Object []ligne={productionservice.getNumOF(),productionservice.getArticles().getLiblle(),productionservice.getQuantiteReel()};

			modeleProductionService.addRow(ligne);
			i++;
		}
}
	}


