package FactureAchat;

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureAvoirPFDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FactureAchatMPDAOImpl;
import dao.daoImplManager.FactureAvoirPFDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.ListTvaDAOImpl;
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
import dao.daoManager.DetailFactureAchatDAO;
import dao.daoManager.DetailFactureAvoirPFDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FactureAvoirPFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.ListTvaDAO;
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
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FactureAchatMP;
import dao.entity.FactureAvoirPF;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.ListTva;
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



























































import com.sun.java.swing.plaf.motif.MotifFileChooserUI.FilterComboBoxRenderer;
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


public class AjoutReportMConsommable extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleChargefixe;

	private JXTable  tableArticle = new JXTable();
	private List<Fournisseur> listFournisseur =new ArrayList<Fournisseur>();
	private List<DetailFactureAvoirPF> listDetailFactureAvoirpf =new ArrayList<DetailFactureAvoirPF>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<Articles> listArticleStockPF =new ArrayList<Articles>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	
	TransferStockPF transferStock = new TransferStockPF();
	private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	private TransferStockPFDAO transferStockPFDAO;
	
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	
	private Map< String, Fournisseur> mapFournisseur= new HashMap<>();
	private Map< String, Fournisseur> mapCodeFournisseur= new HashMap<>();
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
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	 private JComboBox comboArticle;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FactureAvoirPFDAO factureAvoirpfdao;
	private FactureAvoirPF factureAvoirpf=new FactureAvoirPF();
	private DetailCompteClientDAO detailCompteClientdao;
private DetailFactureAvoirPFDAO detailFactureAvoirpfdao;
private FamilleArticlesPFDAO familleArticleDAO;
private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
private StockPFDAO stockpfDAO;
	private JTextField txtcodearticle;
	ChargeProduction chargeproduction;
	private JTextField txtquantite;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private   JComboBox combofamille = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	private CompteClientDAO compteclientdao;
	private FournisseurDAO fournisseurdao;
	private static SequenceurDAO sequenceurDAO=ProdLauncher.sequenceurDAO;
	private Map< String, ListTva> mapTVA= new HashMap<>();
	private Map< BigDecimal, String> mapTVAparTaux= new HashMap<>();
	 JComboBox combomagasin = new JComboBox();
	 private    JComboBox combosousfamille = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	 private JDateChooser dateChooserfacture;
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private  JButton btnModifier ;
	private  JButton btnSupprimer = new JButton();
	 private   JComboBox comboFournisseur = new JComboBox();
	private FactureAchatDAO factureAchatdao;
	private FactureAchatMPDAO factureAchatMPdao;
	 private DetailTransferProduitFiniDAO detailTransferStockPFdao;
	 JRadioButton radiosanstva = new JRadioButton("Sans TVA");
	 JRadioButton radioavectva = new JRadioButton("Avec TVA");
	 JComboBox combotva = new JComboBox();
	 private List<ListTva> listTva =new ArrayList<ListTva>();
	 private ListTvaDAO listtvaDAO ;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutReportMConsommable() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 795);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
           
            utilisateur=AuthentificationView.utilisateur;
        	transferStockPFDAO=new TransferStockPFDAOImpl();
         	depotdao=new DepotDAOImpl();
         	
         	factureAvoirpfdao=new FactureAvoirPFDAOImpl();
         	stockpfDAO=new StockPFDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailFactureAvoirpfdao=new DetailFactureAvoirPFDAOImpl();
         
         	articleDAO=new ArticlesDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         	compteclientdao=new CompteClientDAOImpl();
         	fournisseurdao=new FournisseurDAOImpl();
         	familleArticleDAO=new FamilleArticlesPFDAOImpl();
         	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
         	listFamilleArticle=familleArticleDAO.findAll();
        	factureAchatdao=new FactureAchatDAOImpl();
        	factureAchatMPdao=new FactureAchatMPDAOImpl();
        	detailTransferStockPFdao=new DetailTransferProduitFiniDAOImpl();
        	listArticle=articleDAO.listeArticleMConsommable(Constantes.SOUS_FAMILLE_CONSOMMABLE);
        	  
         	
          } catch (Exception exp){exp.printStackTrace();}
        tableArticle.setSortable(false);
        tableArticle.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		boolean trouve=false;
       		
       		
       		comboArticle.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2));
       		
       		Articles article=mapArticle.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
       		txtcodearticle.setText(article.getCodeArticle());
       	   txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString());	
       		
       		
       		btnAjouter.setEnabled(false);
       		
       		 	}
       });
        
       tableArticle.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Famille","Sous Famille","Article","Quantite"
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
				  		     	scrollPane.setBounds(10, 414, 1117, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 373, 1117, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 221, 1117, 84);
				  		     	add(layeredPane);
		
		  JLabel lblCodeArticle = new JLabel("Code Article :");
		  lblCodeArticle.setBounds(10, 29, 82, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel lbllibelle = new JLabel("Libelle :");
		  lbllibelle.setBounds(205, 29, 57, 26);
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
		      txtcodearticle.setBounds(78, 29, 112, 26);
		      layeredPane.add(txtcodearticle);
		    
		   
		       comboArticle = new JComboBox();
		       comboArticle.addItemListener(new ItemListener() {
		       	public void itemStateChanged(ItemEvent arg0) {
		       		if(!comboArticle.getSelectedItem().equals(""))
		       		{
		       			
		       			txtcodearticle.setText(mapArticle.get(comboArticle.getSelectedItem()).getCodeArticle());
		       		}
		       		
		       		
		       		
		       		
		       	}
		       });
		       comboArticle.addInputMethodListener(new InputMethodListener() {
		       	public void caretPositionChanged(InputMethodEvent arg0) {
		       	}
		       	public void inputMethodTextChanged(InputMethodEvent arg0) {
		       		
		       		
		       
		       		
		       		
		       		
		       	}
		       });
		       comboArticle.setEditable(true);
		       comboArticle.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {}
		       });
		      comboArticle.setBounds(256, 29, 300, 27);
		      layeredPane.add(comboArticle);
		      
		   AutoCompleteDecorator.decorate(comboArticle);
		     	
		      
		      JLabel lblQuantit = new JLabel("Quantite :");
		      lblQuantit.setBounds(578, 29, 72, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      util.Utils.copycoller(txtquantite);
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			
		     			
		     		
		      		
		      	}
		      });
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(636, 29, 112, 26);
		      layeredPane.add(txtquantite);
		      
		
		 
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal solde=BigDecimal.ZERO;
				SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				 String date = dcn.format(dateChooserfacture.getDate());
				 
				 if(dateChooserfacture.getDate()==null)
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
					
				}else if(listDetailTransferProduitFini.size()==0)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer les articles à facturé SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else
				{
					
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				Magasin magasin =mapMagasin.get(combomagasin.getSelectedItem());
				
			
			 String codeTransfert=Utils.genererCodeTransfer(depot.getCode(),ETAT_TRANSFER_STOCK_RESTE);
			

	    // ajouter Transfer Stock PF (Mouvement Stock PF )
	    
		   
		transferStock.setCodeTransfer(codeTransfert);
		transferStock.setCreerPar(utilisateur);
		transferStock.setDate(new Date());
		transferStock.setDateTransfer(dateChooserfacture.getDate());
		transferStock.setListDetailTransferProduitFini(listDetailTransferProduitFini);
		transferStock.setStatut(ETAT_TRANSFER_STOCK_RESTE);
		transferStockPFDAO.add(transferStock);
		
		   for(int j=0;j<listDetailTransferProduitFini.size();j++)
			 {
			   detailTransferStockPFdao.add(listDetailTransferProduitFini.get(j));
			 }
		
		listDetailTransferProduitFini.clear();
	    
	    
	    JOptionPane.showMessageDialog(null, "Report remise Ajouté avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
	    initialiserFacture();
	    initialiser();
	  
	   
	    transferStock=new TransferStockPF();
	   
		InitialiseTableau();
			
	
					
				}
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(475, 689, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 180, 1117, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1152, 97);
		add(layeredPane_1);
		
		JLabel label_1 = new JLabel("Date  :");
		label_1.setBounds(256, 31, 47, 24);
		layeredPane_1.add(label_1);
		
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
		dateChooserfacture.setBounds(294, 29, 144, 26);
		layeredPane_1.add(dateChooserfacture);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(448, 30, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(500, 31, 183, 24);
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
		  
		  comboArticle.addItem("");
		  
		  for(int k=0;k<listArticle.size() ; k++)
		  {
			  
			  Articles article=listArticle.get(k);
			  comboArticle.addItem(article.getLiblle());
			  mapArticle.put(article.getLiblle(), article);
			  mapCodeArticle.put(article.getCodeArticle(), article);
			  
			  
		  }
		  
		  
		  
		 
		  
		  JLabel label_4 = new JLabel("Magasin :");
		  label_4.setBounds(693, 29, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  	}
		  });
		  combomagasin.setBounds(755, 30, 183, 24);
		  layeredPane_1.add(combomagasin);
		  combomagasin.setSelectedIndex(-1);
		   
		   
		
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Report MConsommable");
		titledSeparator_2.setBounds(10, 11, 1152, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(480, 338, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					

					boolean trouve=false;
					//BigDecimal oldprix=BigDecimal.ZERO;
					BigDecimal odlQuantite=BigDecimal.ZERO;
					BigDecimal Montant=BigDecimal.ZERO;
					BigDecimal QuantiteUnit=BigDecimal.ZERO;
					BigDecimal QuantitePaquet=BigDecimal.ZERO;
					BigDecimal QuantiteTotalStock=BigDecimal.ZERO;
					BigDecimal QuantiteTotal=BigDecimal.ZERO;
					BigDecimal TVA=BigDecimal.ZERO;
					boolean articleboisson=false;
					
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
						}else if(txtquantite.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))){
							
							
								JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
						}else 
						{
						
						 Articles article=mapArticle.get(comboArticle.getSelectedItem());
				         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				         SousFamilleArticlePF sousfamille=sousFamilleArticleDAO.findByCode(Constantes.SOUS_FAMILLE_CONSOMMABLE);
				         
				      
		        		 if(!txtquantite.getText().equals(""))
		        		 {
		        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
		        		 }
				         QuantiteTotal=QuantitePaquet.add(QuantiteUnit);
				         
				         
				         if(article.getCentreCout1()!=null)
				         {
				        	 BigDecimal quantite = (QuantitePaquet.add(QuantiteUnit)).setScale(3);
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
				         
				         for(int i=0;i<listDetailTransferProduitFini.size();i++)
				         {
				        	 DetailTransferProduitFini detailTransferStockPF =listDetailTransferProduitFini.get(i);
				        	 if(detailTransferStockPF.getArticle().getLiblle().equals(article.getLiblle()) && detailTransferStockPF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()))
				        	 {
				        		 trouve=true;
				        	 }
				         }
				         
				         if(trouve==false)
				         {
				   
					
							  			
							  		/// ajout transfer stock PF (Mouvement Stock PF)
							  			DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
							  			detailTransferStockPF.setArticle(article);
							  			detailTransferStockPF.setSousFamille(sousfamille);
										detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
										detailTransferStockPF.setMagasinDestination(magasin);
										detailTransferStockPF.setQuantite(QuantiteTotal);
										detailTransferStockPF.setPrixUnitaire(BigDecimal.ZERO);
										detailTransferStockPF.setTransferStockPF(transferStock);
										detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_RESTE);
										listDetailTransferProduitFini.add(detailTransferStockPF);
										
										afficher_tableDetailTransferPF(listDetailTransferProduitFini);
							  			
							  			
								        initialiser(); 
					        	
					       }else
					       {
					    	   JOptionPane.showMessageDialog(null, "Article existe déja  Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					    	   return;
					       }
					       
				         }
				        
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité , le Prix et la Remise doit etre en chiffre SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
			}
				
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(611, 337, 106, 23);
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
		  button.setBounds(441, 147, 106, 23);
		  add(button);
		  
		   btnModifier = new JButton();
		   btnModifier.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		try {
		   			

					BigDecimal QuantiteTotal=BigDecimal.ZERO;			
			   		BigDecimal montant=BigDecimal.ZERO;
			   		
					BigDecimal odlQuantite=BigDecimal.ZERO;
					BigDecimal QuantiteUnit=BigDecimal.ZERO;
					BigDecimal QuantitePaquet=BigDecimal.ZERO;
					BigDecimal QuantiteTotalStock=BigDecimal.ZERO;
					
					boolean articleboisson=false;
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
						}else if(txtquantite.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))){
							
							
								JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
						}else 
						{
							
							 Articles article=mapArticle.get(comboArticle.getSelectedItem());
					      Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
					      SousFamilleArticlePF sousfamille=sousFamilleArticleDAO.findByCode(Constantes.SOUS_FAMILLE_CONSOMMABLE);;
					      
					  
			        		 if(!txtquantite.getText().equals(""))
			        		 {
			        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
			        		 }
					         QuantiteTotal=QuantitePaquet.add(QuantiteUnit);
					      
					      
					      if(article.getCentreCout1()!=null)
					         {
					        	 BigDecimal quantite = QuantitePaquet.add(QuantiteUnit).setScale(3);
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
					      
					         for(int i=0;i<listDetailTransferProduitFini.size();i++)
					         {
					        	 if(i!=tableArticle.getSelectedRow())
					        	 {
					        		 
					        		 DetailTransferProduitFini detailTransferStockPF =listDetailTransferProduitFini.get(i);
						        	 if(detailTransferStockPF.getArticle().getLiblle().equals(article.getLiblle()) && detailTransferStockPF.getSousFamille().getLiblle().equals(sousfamille.getLiblle()))
						        	 {
						        		 trouve=true;
						        	 }
						        	  
					        		 
					        	 }
					        	 
					        	 
					         }
					         
					         if(trouve==false)
					         {
					        	 boolean existe=false;
					        	 
					       
					            		if(article.getCentreCout2()!=null)
					            		{
					            			if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
					            			{
					            				existe=true;
					            			}
					            			
					            		}
					            		
					            	
								           // Modifier transfer Stock PF
								  			
								  			DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(tableArticle.getSelectedRow());
											
											detailTransferStockPF.setArticle(article);
											detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
											detailTransferStockPF.setMagasinDestination(magasin);
											 detailTransferStockPF.setQuantite(QuantiteTotal);
											 detailTransferStockPF.setPrixUnitaire(BigDecimal.ZERO);
													
											detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_RESTE);
											listDetailTransferProduitFini.set(tableArticle.getSelectedRow(), detailTransferStockPF);
								          											
											afficher_tableDetailTransferPF(listDetailTransferProduitFini);
								         
						        		initialiser();
						        	
						        		
						        		 
						        	 }else
						       {
						    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						    	   return;
						       }
						       
					         }
					        
						}
						
			   		
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité , le Prix et la Remise doit etre en chiffre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
		   		
		   	}
		   		
		   
		   });
		  btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  
		   btnSupprimer = new JButton();
		  btnSupprimer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		BigDecimal stocktmp=BigDecimal.ZERO;
		  		boolean articleboisson=false;
		  		if(tableArticle.getSelectedRow()!=-1)
		  		{
		  			 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer l'article dans la liste  ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
							
						{
							
				  		
				  		
				  		listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
				  		
				  		afficher_tableDetailTransferPF(listDetailTransferProduitFini);
				        
					   
				  			JOptionPane.showMessageDialog(null, "Article supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
				  			initialiser();
						}
							
							
		  			
		  			
		  			
		  	
		  			
		  		}
		  		
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1137, 475, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1137, 445, 73, 24);
		add(btnModifier);
		
	
		 
			
		}
	

	
	void initialiserFacture()
	{
		dateChooserfacture.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);	
		comboFournisseur.setSelectedIndex(-1);
		
		
	}

	void initialiser()
	{
		txtcodearticle.setText("");
		comboArticle.setSelectedItem("");;
	 
		txtquantite.setText("");
		
	     btnAjouter.setEnabled(true);
	  
	}
	
	void InitialiseTableau()
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false
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
	
	
	void afficher_tableDetailTransferPF(List<DetailTransferProduitFini> listDetailTransferPF)
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleChargefixe);
		int i=0;
		 
		while(i<listDetailTransferPF.size())
		{	
			boolean existe=false;
			DetailTransferProduitFini detailTransferPF=listDetailTransferPF.get(i);
			
			
		/*		if(article.getCentreCout2()!=null)
				{
					if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
					{
					existe=true;
					}
				}
		
			if(existe==true)
			{
				Object []ligne={detailfactureAchat.getSousFamille().getFamileArticlePF().getLiblle(),detailfactureAchat.getSousFamille().getLiblle(),detailfactureAchat.getArticle().getLiblle(),detailfactureAchat.getPrixUnitaire(),detailfactureAchat.getQuantite().divide(article.getConditionnement(),RoundingMode.HALF_UP),detailfactureAchat.getReduction().divide(article.getConditionnement(),RoundingMode.HALF_UP), detailfactureAchat.getMontantHT().divide(article.getConditionnement(),RoundingMode.HALF_UP),detailfactureAchat.getMontantTVA().divide(article.getConditionnement(),RoundingMode.HALF_UP),detailfactureAchat.getMontantTTC().divide(article.getConditionnement(),RoundingMode.HALF_UP)};

				modeleChargefixe.addRow(ligne);
			}else
			{*/
				Object []ligne={detailTransferPF.getSousFamille().getFamileArticlePF().getLiblle(),detailTransferPF.getSousFamille().getLiblle(),detailTransferPF.getArticle().getLiblle(),detailTransferPF.getQuantite()};

				modeleChargefixe.addRow(ligne);
		/*	}*/
			
			i++;
		}
}
	}


