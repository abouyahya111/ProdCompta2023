

/*
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

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
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


public class ConsulterFactureServiceProduction extends JLayeredPane implements Constantes{
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
	private List<DetailFactureServiceProduction> listDetailFactureServiceProduction =new ArrayList<DetailFactureServiceProduction>();
	private List<FacturePF> listFacturePFMAJ =new ArrayList<FacturePF>();
	private List<Client> listClient=new ArrayList<Client>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	
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
	private JTextField txttotalmontantTTC;
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
	  private JTable table_production_service;
	  private DetailFactureServiceProductionDAO detailfactureserviceproductionDAO;
	  private FactureServiceProduction factureserviceproduction;
	  JRadioButton radioespece = new JRadioButton("Esp\u00E8ce");
	  JRadioButton radioChque = new JRadioButton("Ch\u00E8que");
	//**
	 // Launch the application.
	 //*

	//**
	 // Create the application.
	//*
	public ConsulterFactureServiceProduction() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1562, 1062);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
             
             detailTransferStockPFdao=ProdLauncher.detailTransferProduitFiniDAO;
             transferStockPFDAO=ProdLauncher.transferStockPFDAO;
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=ProdLauncher.depotDAO;
         	typeventedao=ProdLauncher.typeVenteDAO;
         	factureServiceProductiondao=ProdLauncher.factureServiceProductiondao;
         	detailFacturePfdao=ProdLauncher.detailFacturePfDAO;
         	clientdao=ProdLauncher.clientDAO;
         	articleDAO=ProdLauncher.articlesDAO;
         	stockpfDAO=ProdLauncher.stockPFDAO;
         	parametredao=ProdLauncher.parametreDAO;
         	detailCompteClientdao=ProdLauncher.detailCompteClientDAO;
         	compteclientdao=ProdLauncher.compteClientDAO;
         	
         	listArticle=articleDAO.findAll();
         	
        	familleArticleDAO=ProdLauncher.familleArticlePFDAO;
         	sousFamilleArticleDAO=ProdLauncher.sousFamilleArticlePFDAO;
         	listFamilleArticle=familleArticleDAO.findAll();
        	detailPrixArticleDAO=ProdLauncher.DetailPrixArticleDAO;
        	productiondao=ProdLauncher.productionDAO;
        	detailfactureserviceproductionDAO=ProdLauncher.detailfactureServiceProductiondao;
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
				  		     	scrollPane.setBounds(10, 504, 634, 318);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Productions Service");
				  		     	titledSeparator.setBounds(10, 467, 634, 30);
				  		     	add(titledSeparator);
		      
		   
		     
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(table.getSelectedRow()!=-1)
				{
					if(!txttotalmontantTTC.getText().equals(""))
					{
						if(radioChque.isSelected()==true || radioespece.isSelected()==true)
						{
							 factureserviceproduction=listFactureServiceProduction.get(table.getSelectedRow());
								factureserviceproduction.setDateFacture(dateChooserfacture.getDate());
									
								factureserviceproduction.setMontantTTC(new BigDecimal(txttotalmontantTTC.getText()));
								factureserviceproduction.setEtat(ETAT_REGLE);
								factureserviceproduction.setModifierPar(utilisateur);
								if(radioChque.isSelected()==true)
								{
									factureserviceproduction.setModeReglement(MODE_REGLEMENT_CHEQUE);
								}else if(radioespece.isSelected()==true)
								{
									factureserviceproduction.setModeReglement(MODE_REGLEMENT_ESPECE);
								}
								//factureserviceproductionTmp.setDetailFactureServiceProduction(listDetailFactureServiceProduction);
									factureServiceProductiondao.edit(factureserviceproduction);
									JOptionPane.showMessageDialog(null, "Facture Service Production Modifier avec Succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
									chargerListeFacture();
									listDetailFactureServiceProduction.clear();
									afficher_tableDetailFactureServiceProduction(listDetailFactureServiceProduction);
									txttotalmontantTTC.setText("");
									radioespece.setSelected(false);
									radioChque.setSelected(false);
						}else
						{
							JOptionPane.showMessageDialog(null, "Veuillez selectionner le mode de reglement SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}
				
					}else
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer le Montant TTC SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}
				
					
				}else
				{
					JOptionPane.showMessageDialog(null, "La list est Vide !!!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(20, 834, 112, 24);
		add(buttonvalider);
		
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
		
		
		JLabel lblTotalMontant = new JLabel("Montant TTc :");
		lblTotalMontant.setBounds(159, 832, 112, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(266, 832, 151, 26);
		add(txttotalmontantTTC);
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter les Factures  Services par :");
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
						
						listDetailFactureServiceProduction.clear();
						
						 factureserviceproduction=listFactureServiceProduction.get(table.getSelectedRow());
						txtnumbl.setText(factureserviceproduction.getNumFacture());
					dateChooserfacture.setDate(factureserviceproduction.getDateFacture());
					combodepot.setSelectedItem(factureserviceproduction.getDepot().getLibelle());
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
					"Num Facture", "Date Facture", "Type", "Client", "Depot", "Montant TTC"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
	
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
	    lblNumFacture.setBounds(259, 13, 97, 24);
	    layeredPane_2.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtparnumBL = new JTextField();
	    txtparnumBL.setBounds(365, 13, 102, 26);
	    layeredPane_2.add(txtparnumBL);
	    util.Utils.copycoller(txtparnumBL);
	    txtparnumBL.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {}
	    });
	    txtparnumBL.setColumns(10);
	    
	    JLabel lblClient_1 = new JLabel("Client :");
	    lblClient_1.setBounds(471, 13, 56, 24);
	    layeredPane_2.add(lblClient_1);
	    lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    JLabel lblDateFacture = new JLabel("Date Facture:");
	    lblDateFacture.setBounds(714, 13, 97, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     pardateChooser = new JDateChooser();
	     pardateChooser.setBounds(821, 11, 151, 26);
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
	     combopardepot.setBounds(1044, 13, 151, 24);
	     layeredPane_2.add(combopardepot);
	     combopardepot.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {}
	     });
	     combopardepot.setSelectedIndex(-1);
	     
	     JLabel lblDepot = new JLabel("Depot  :");
	     lblDepot.setBounds(982, 12, 97, 24);
	     layeredPane_2.add(lblDepot);
	     lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));


	     
	      comboparclient = new JComboBox();
	     comboparclient.setSelectedIndex(-1);
	     comboparclient.setBounds(524, 13, 178, 24);
	     layeredPane_2.add(comboparclient);
	     
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
								 transferStockPFDAO.delete(transferStock.getId());
								 
								 
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
	    supprimer_facture.setEnabled(false);
	    JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
	    GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
	    gridBagLayout.rowWeights = new double[]{0.0};
	    gridBagLayout.rowHeights = new int[]{0};
	    gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
	    gridBagLayout.columnWidths = new int[]{0, 0, 0};
	    titledSeparator_1.setTitle("Liste Des Productions Service");
	    titledSeparator_1.setBounds(787, 466, 681, 30);
	    add(titledSeparator_1);
	    
	    JScrollPane scrollPane_2 = new JScrollPane();
	    scrollPane_2.setBounds(791, 506, 677, 315);
	    add(scrollPane_2);
	    
	    table_production_service = new JTable();
	    table_production_service.setFillsViewportHeight(true);
	    scrollPane_2.setViewportView(table_production_service);
	    table_production_service.setModel(new DefaultTableModel(
	    	new Object[][] {
	    	},
	    	new String[] {
	    		"Num OF", "Article", "Quantite"
	    	}
	    ));
	    
	    JButton btnsupprimer = new JButton();
	    btnsupprimer.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		if(tableArticle.getSelectedRow()!=-1)
	    		{
	    			if(listDetailFactureServiceProduction.size()!=0)
	    			{
	    			
	    			Production production=productiondao.findByNumOF(listDetailFactureServiceProduction.get(tableArticle.getSelectedRow()).getNumOF(), utilisateur.getCodeDepot());
	    			production.setService(Constantes.ETAT_TRANSFER_STOCK_SERVICE);
	    			productiondao.edit(production);
	    			DetailFactureServiceProduction detailfactureserviceproduction=listDetailFactureServiceProduction.get(tableArticle.getSelectedRow());
	    			detailfactureserviceproductionDAO.delete(detailfactureserviceproduction.getId());
	    			listDetailFactureServiceProduction.remove(tableArticle.getSelectedRow());
	    			afficher_tableDetailFactureServiceProduction(listDetailFactureServiceProduction);
	    			ChargerProductionService();
	    			
	    			}
	    			
	    		}
	    		
	    	}
	    });
	    btnsupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnsupprimer.setBounds(654, 519, 73, 24);
	    btnsupprimer.setIcon(imgSupprimer);
	    add(btnsupprimer);
	    
	    JButton btnAjouter = new JButton();
	    btnAjouter.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		if(table_production_service.getSelectedRow()!=-1)
	    		{
	    			if(listProductionService.size()!=0 && listDetailFactureServiceProduction.size()!=0)
	    			{
	    				
	    			Production production=productiondao.findByNumOF(listProductionService.get(table_production_service.getSelectedRow()).getNumOF(), utilisateur.getCodeDepot());
	    			production.setService(Constantes.TRANSFERE_BL_FACTURE);
	    			productiondao.edit(production);
	    			DetailFactureServiceProduction detailfactureserviceproduction=new DetailFactureServiceProduction();
	    			detailfactureserviceproduction.setArticle(production.getArticles());
	    			detailfactureserviceproduction.setNumOF(production.getNumOF());
	    			detailfactureserviceproduction.setFactureService(factureserviceproduction);
	    			detailfactureserviceproduction.setQuantite(production.getQuantiteReel());
	    			detailfactureserviceproduction.setUtilisateur(utilisateur);
	    			listDetailFactureServiceProduction.add(detailfactureserviceproduction);
	    			detailfactureserviceproductionDAO.add(detailfactureserviceproduction);
	    			afficher_tableDetailFactureServiceProduction(listDetailFactureServiceProduction);
	    			ChargerProductionService();
	    			
	    			
	    				
	    			}
	    			
	    		}
	    		
	    	}
	    });
	    btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAjouter.setBounds(1478, 519, 73, 24);
	    btnAjouter.setIcon(imgAjouter);
	    add(btnAjouter);
	    
	     radioespece = new JRadioButton("Esp\u00E8ce");
	    radioespece.setFont(new Font("Tahoma", Font.BOLD, 12));
	    radioespece.setBounds(443, 835, 73, 23);
	    add(radioespece);
	    group.add(radioespece);
	     radioChque = new JRadioButton("Ch\u00E8que");
	    radioChque.setFont(new Font("Tahoma", Font.BOLD, 12));
	    radioChque.setBounds(524, 835, 107, 23);
	    add(radioChque);
        group.add(radioChque);
	    ChargerProductionService();
	    
	    listClient=clientdao.findListClientByCodeDepot(depot.getCode());
	    
	    for(int i=0;i<listClient.size();i++)
	    {
	    	Client client=listClient.get(i);
	    comboparclient.addItem(client.getNom());
	    	
	    mapClient.put(client.getNom(), client);
	    	
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
		
		txttotalmontantTTC.setText("");
		
		
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
						"Num OF","Article","Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false
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
				"Num OF","Article","Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false
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
			
			Object []ligne={detailfactureServiceProduction.getNumOF(),detailfactureServiceProduction.getArticle().getLiblle(),detailfactureServiceProduction.getQuantite()};

			modeleArticle.addRow(ligne);
			i++;
		}
}
	
	
	
	void chargerListeFacture()
	{

		
		Client clientpf=mapParClient.get(comboparclient.getSelectedItem());
		Depot depot=mapparDepot.get(combopardepot.getSelectedItem());
		
		initialiserFacture();
		initialiser();
		InitialiseTableau();
	
		listFactureServiceProduction.clear();
		factureServiceProductiondao=ProdLauncher.factureServiceProductiondao;
		
		
		//listFacturePFTmp=facturepfdao.findByNumFcatureClientDateFactureDepotEtatRegle(txtparnumBL.getText(),clientpf, pardateChooser.getDate(), depot,Constantes.ETAT_REGLE);	
		
		listFactureServiceProduction=factureServiceProductiondao.findByNumFcatureClientDateFactureDepotEtatRegle(txtparnumBL.getText(),clientpf, pardateChooser.getDate(), depot,Constantes.ETAT_NON_REGLE);
		
	    afficher_tableFactureServiceProduction(listFactureServiceProduction);
			
		
	}
	
	
	
	
	void afficher_tableFactureServiceProduction(List<FactureServiceProduction> listFacture)
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
		int i=0;
		 
		while(i<listFacture.size())
		{	
		FactureServiceProduction factureserviceproduction=listFacture.get(i);
		
		Date datefacture=factureserviceproduction.getDateFacture();
			
			Object []ligne={factureserviceproduction.getNumFacture(),datefacture,factureserviceproduction.getType(),factureserviceproduction.getClient().getNom(),factureserviceproduction.getDepot().getLibelle(),factureserviceproduction.getMontantTTC()};

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
		table_production_service.setModel(modeleProductionService);
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
*/

