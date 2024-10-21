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
import dao.entity.Employe;
import dao.entity.FactureAutresVente;
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


public class AjoutFactureAutresVente extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleChargefixe;

	private JXTable  tableArticle = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<DetailFactureAutresVente> listDetailFactureAutresVente =new ArrayList<DetailFactureAutresVente>();
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
	private List<Client> listFournisseur =new ArrayList<Client>();
	
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	
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
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FactureAutresVenteDAO factureAutresVentedao;
	private FactureAutresVente factureAutresVente=new FactureAutresVente();
	private DetailCompteClientDAO detailCompteClientdao;
private DetailFactureAutresVenteDAO detailFactureAutresVentedao;
private ClientPFDAO clientpfdao;
private StockPFDAO stockpfDAO;
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
		  JCheckBox checkttc = new JCheckBox("TTC");
		  JLabel labelmarge = new JLabel("marge");
		  JLabel labelprixmin = new JLabel("New label");
		  JLabel labelPrixMax = new JLabel("New label");
		  BigDecimal prixTTC=BigDecimal.ZERO;
		  BigDecimal StockFinale=BigDecimal.ZERO;
		  BigDecimal StockFinaleAnne=BigDecimal.ZERO;
		  BigDecimal stockfinaleTherres=BigDecimal.ZERO;
		  BigDecimal stockfinaleVerres=BigDecimal.ZERO;
		  BigDecimal stockfinaleArticlePromo=BigDecimal.ZERO;
		  JLabel lblOffreTherre = new JLabel("Offre Therre :");
		  JComboBox comboBoxtherres = new JComboBox();
		  JLabel lbloffreverres = new JLabel("Offre Verres :");
		  JComboBox comboBoxverres = new JComboBox();
		  JLabel stockdisponibleoffre = new JLabel("");
		  JLabel stockdisponibleoffreverres = new JLabel("");
		  JComboBox comboBoxPromo = new JComboBox();
		  JLabel lblOffrePromo = new JLabel("Offre Promo :");
		  JLabel stockdisponiblearticlepromo = new JLabel("");
		  JCheckBox checkboxSansTva = new JCheckBox("Sans TVA");
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
		    private JTextField txtdesignation;
		    
	/////////////////////////////////////////////////////////////////////////	  
		  
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutFactureAutresVente() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1532, 913);
      
	
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
         	factureAutresVentedao=new FactureAutresVenteDAOImpl();
         	stockpfDAO=new StockPFDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailFactureAutresVentedao=new DetailFactureAutresVenteDAOImpl();
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
				  		     	scrollPane.setBounds(10, 465, 1162, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 424, 1162, 30);
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
				}else if(comboFournisseur.getSelectedIndex()==-1)
				{

					JOptionPane.showMessageDialog(null, "Veuillez choisir le Fournisseur SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
					
				}else if(comboClientpf.getSelectedIndex()==-1 || comboClientpf.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le Client SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
					
				}else if(listDetailFactureAutresVente.size()==0)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer les articles à facturé SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else
				{
					
					FactureVenteMP factureventetmp=factureVenteMPdao.findFactureVenteMPByNumBL(txtnumBL.getText());
					FacturePF factureventetPF=factureVentePFdao.findFactureVentePFByNumBL(txtnumBL.getText());
					if(factureventetmp!=null || factureventetPF!=null)
					{
						JOptionPane.showMessageDialog(null, "Num BL existe déja ","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					
				Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
				
				ClientPF clientPF =mapClientPF.get(comboClientpf.getSelectedItem());
				Client fournisseur=mapFournisseur.get(comboFournisseur.getSelectedItem());
				String codeTransfert=Utils.genererCodeTransfer(depot.getCode(),ETAT_TRANSFER_STOCK_VENTE);
				
			factureAutresVente.setNumBl(txtnumBL.getText());
			factureAutresVente.setClientPF(clientPF);
			factureAutresVente.setFournisseur(fournisseur);
			factureAutresVente.setDepot(depot);
			
			factureAutresVente.setDateBl(dateChooserfacture.getDate());
			factureAutresVente.setDateFacture(dateChooserfacture.getDate());
			factureAutresVente.setEtat(Constantes.ETAT_NON_REGLE);
			factureAutresVente.setType(Constantes.TYPE_BON_LIVRAISON);
			factureAutresVente.setMontantHT((new BigDecimal(txttotalmontantHT.getText())).setScale(6, RoundingMode.HALF_UP));
			factureAutresVente.setMontantTVA((new BigDecimal(txttotalmontantTVA.getText())).setScale(6, RoundingMode.HALF_UP));
			factureAutresVente.setMontantTTC((new BigDecimal(txttotalmontantTTC.getText())).setScale(6, RoundingMode.HALF_UP));	
			factureAutresVente.setCodeTransfer(codeTransfert);
			factureAutresVente.setDateSaisi(new Date());
			factureAutresVente.setEspece(BigDecimal.ZERO);
			factureAutresVente.setCheque(BigDecimal.ZERO);
			factureAutresVente.setVirement(BigDecimal.ZERO);
			factureAutresVente.setTraite(BigDecimal.ZERO);
			factureAutresVente.setCredit(BigDecimal.ZERO);
			//facturePF.setDetailFacturePF(listDetailFacturePF);
			
			factureAutresVente.setCreerPar(utilisateur);
		    factureAutresVentedao.add(factureAutresVente);
		
		  
		 for(int i=0;i<listDetailFactureAutresVente.size();i++)
		 {
			 detailFactureAutresVentedao.add(listDetailFactureAutresVente.get(i));
		 }
		   
		    ////////////////////////////////// ajouter detail compte client par client facture ////////////////////////   
		    
		    DetailCompteClient detailcompteclient=new DetailCompteClient();
		    detailcompteclient.setCompteClient(clientPF.getCompteClient());
		    detailcompteclient.setUtilisateurCreation(utilisateur);
		    detailcompteclient.setDateCreation(new Date());
		    detailcompteclient.setDateOperation(dateChooserfacture.getDate());
		    detailcompteclient.setMontantDebit(new BigDecimal(txttotalmontantTTC.getText()));
		    detailcompteclient.setMontantCredit(BigDecimal.ZERO);
		    detailcompteclient.setDesignation("Montant sur Facture Autres vente "+txtnumBL.getText());
		    detailcompteclient.setFactureAutresVente(factureAutresVente);
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
		  
					
			
			/*
			listDetailTransferProduitFini=transferStockPFDAO.findById(transferStock.getId()).getListDetailTransferProduitFini();
			listDetailTransferProduitFini.clear();
			*/
			 
		
		
			 
		    JOptionPane.showMessageDialog(null, "Facture Ajouté avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
		    initialiserFacture();
		    initialiser();
		  
		  factureAutresVente=new FactureAutresVente();
		
		  listDetailFactureAutresVente.clear();
		  
			 InitialiseTableau();
			
				}
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(385, 740, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 180, 1162, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1162, 97);
		add(layeredPane_1);
		
		JLabel lblNBl = new JLabel("N\u00B0 BL  :");
		lblNBl.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNBl.setBounds(10, 41, 89, 24);
		layeredPane_1.add(lblNBl);
		
		txtnumBL = new JTextField();
		txtnumBL.setColumns(10);
		txtnumBL.setBounds(109, 40, 183, 26);
		layeredPane_1.add(txtnumBL);
		
		JLabel label_1 = new JLabel("Date  :");
		label_1.setBounds(312, 41, 62, 24);
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
		dateChooserfacture.setBounds(373, 39, 181, 26);
		layeredPane_1.add(dateChooserfacture);
		  
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
		  
		   comboClientpf = new JComboBox();
		  comboClientpf.setSelectedIndex(-1);
		  comboClientpf.setBounds(625, 41, 183, 24);
		  layeredPane_1.add(comboClientpf);
		  
		  AutoCompleteDecorator.decorate(comboClientpf);
		  
		  comboClientpf.addItem("");
			Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
		  listClientPF=clientpfdao.findListClientByCodeDepot(depot.getCode());
		  
		  for(int i=0;i<listClientPF.size();i++)
		  {
			  
			  
			ClientPF clientPF=  listClientPF.get(i);
			comboClientpf.addItem(clientPF.getNom());
			mapClientPF.put(clientPF.getNom(), clientPF)  ;
			  
			  
		  }
		  
		  JLabel lblClient = new JLabel("Client :");
		  lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblClient.setBounds(564, 42, 56, 24);
		  layeredPane_1.add(lblClient);
		  
		  JLabel lblFournisseur = new JLabel("Fournisseur :");
		  lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblFournisseur.setBounds(837, 40, 79, 24);
		  layeredPane_1.add(lblFournisseur);
		  
		   comboFournisseur = new JComboBox();
		  comboFournisseur.setSelectedIndex(-1);
		  comboFournisseur.setBounds(915, 41, 205, 24);
		  layeredPane_1.add(comboFournisseur);
		
		 
		  
		  
		  
		  
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture Autres Vente");
		titledSeparator_2.setBounds(10, 11, 1162, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(390, 391, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					
					
						
						boolean trouve=false;
						
						
						
						
						
						
						
						if(dateChooserfacture.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(txtdesignation.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir la designation SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtquantite.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))){
							
							
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
					        	 DetailFactureAutresVente detailFacturePF =listDetailFactureAutresVente.get(i);
					        	 if(detailFacturePF.getDesignation().equals(txtdesignation.getText()) )
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
								          detailFacture.setMontantHT(prixTTC.multiply(new BigDecimal(txtquantite.getText())).setScale(6, RoundingMode.HALF_UP));
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
								          detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText())).setScale(6, RoundingMode.HALF_UP));
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
								        	 
								       
								         //  detailFacture.setTypeVente(typevente);
								        
								           detailFacture.setFactureautresaente (factureAutresVente);
								       //  detailFacture.setDateCreation(new Date());
								           
								           detailFacture.setUtilisateur(utilisateur);
								         
								           listDetailFactureAutresVente.add(detailFacture);
								          
								        
								     	  
								          
								          afficher_tableDetailFacturePF(listDetailFactureAutresVente);
								          int i=0;
									        BigDecimal montanttotal=BigDecimal.ZERO;
									        BigDecimal montanttotalHT=BigDecimal.ZERO;
									        BigDecimal montanttotalTVA=BigDecimal.ZERO;
									        BigDecimal sommequantite=BigDecimal.ZERO;
									        BigDecimal lamarge=BigDecimal.ZERO;
									        BigDecimal compteur=BigDecimal.ZERO;
									        while(i<listDetailFactureAutresVente.size())
									        {
									        
									          DetailFactureAutresVente detailFacturePF=listDetailFactureAutresVente.get(i);
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
					        
					
					
					
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité , Le Prix et la Remise doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
			}
				
			
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(521, 390, 106, 23);
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
		   			
		   		

						
				   		if(tableArticle.getSelectedRow()!=-1)
				   		{
				   			boolean trouve=false;
							
				   		 if(dateChooserfacture.getDate()==null)
							{

								JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
								return;	
							}else if(txtdesignation.getText().equals(""))
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
						        		 
						        		 DetailFactureAutresVente detailFacturePF =listDetailFactureAutresVente.get(i);
							        	 if(detailFacturePF.getDesignation().equals(txtdesignation.getText())  && !detailFacturePF.getPrixUnitaire().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) )
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
											           detailFacture.setMontantHT((prixTTC).multiply(new BigDecimal(txtquantite.getText())).setScale(6, RoundingMode.HALF_UP));
												     if(checkboxSansTva.isSelected()==true)
												     {
												    	 
												           detailFacture.setMontantTVA(BigDecimal.ZERO);

												     }else
												     {
												           detailFacture.setMontantTVA((((prixTTC).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));

												    	 
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
											           detailFacture.setMontantHT(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText())).setScale(6, RoundingMode.HALF_UP));
											           if(checkboxSansTva.isSelected()==true)
													     {
												           detailFacture.setMontantTVA(BigDecimal.ZERO);

													     }else
													     {
													           detailFacture.setMontantTVA(((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));

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
									        	 
									        	  
									         
									          
									         //  detailFacture.setTypeVente(typevente);
									          
									           detailFacture.setFactureautresaente(factureAutresVente);
									       //    detailFacture.setDateCreation(new Date());
									           
									           detailFacture.setUtilisateur(utilisateur);
									           
									           listDetailFactureAutresVente.set(tableArticle.getSelectedRow(), detailFacture);
									    
											
											
											
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
										        	
										        	 DetailFactureAutresVente detailFacturePF=listDetailFactureAutresVente.get(i);
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
									  			
									  			
									  			// Modifier transfer Stock PF
									  			
									  			
												
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
		  		
		  		boolean trouve=false;
		  		
		  		if(tableArticle.getSelectedRow()!=-1)
		  		{
		  				
		  				 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer l'article dans la facture  ?", 
									"Satisfaction", JOptionPane.YES_NO_OPTION);
							 
							if(reponse == JOptionPane.YES_OPTION )
								
								
							{
								
						
					        	 
					  		listDetailFactureAutresVente.remove(tableArticle.getSelectedRow());
					  	
					  		
					  		
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

						        	 DetailFactureAutresVente detailFacturePF=listDetailFactureAutresVente.get(i);
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
		  btnSupprimer.setBounds(1202, 495, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(899, 797, 105, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(1023, 797, 134, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(760, 738, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(653, 738, 97, 26);
		add(lblTotalQuantite);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1202, 465, 73, 24);
		add(btnModifier);
		
		JLabel lblTotalMontantHt = new JLabel("Total Montant HT :");
		lblTotalMontantHt.setBounds(899, 735, 105, 26);
		add(lblTotalMontantHt);
		
		txttotalmontantHT = new JTextField();
		txttotalmontantHT.setEditable(false);
		txttotalmontantHT.setColumns(10);
		txttotalmontantHT.setBounds(1023, 735, 134, 26);
		add(txttotalmontantHT);
		
		JLabel lblTotalMontantTva = new JLabel("Total Montant TVA :");
		lblTotalMontantTva.setBounds(899, 767, 105, 26);
		add(lblTotalMontantTva);
		
		txttotalmontantTVA = new JTextField();
		txttotalmontantTVA.setEditable(false);
		txttotalmontantTVA.setColumns(10);
		txttotalmontantTVA.setBounds(1023, 767, 134, 26);
		add(txttotalmontantTVA);
		
		                
 

		       		 
		 
				// stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
		     
		       		 
		       		   
		       		    layerArticle = new JLayeredPane();
		       		    layerArticle.setBounds(20, 221, 1152, 163);
		       		    add(layerArticle);
		       		    layerArticle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		              
		              // comboArticle.addItem("");
		               
		              
		                
               
		                JLayeredPane layerArticleGratuit = new JLayeredPane();
		                layerArticleGratuit.setBounds(0, 0, 1152, 163);
		                layerArticle.add(layerArticleGratuit);
		                layerArticleGratuit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		    		                   
		    		                   txtquantite = new JTextField();
		    		                   txtquantite.setBounds(630, 48, 99, 26);
		    		                   layerArticleGratuit.add(txtquantite);
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
		    		                   
		    		                    lblPrix = new JLabel("Prix  :");
		    		                    lblPrix.setBounds(739, 48, 36, 26);
		    		                    layerArticleGratuit.add(lblPrix);
		             
		             txtreduction = new JTextField();
		             txtreduction.setBounds(80, 103, 106, 26);
		             layerArticleGratuit.add(txtreduction);
		             txtreduction.setColumns(10);
		             
		              labelpourcentage = new JLabel("%");
		              labelpourcentage.setBounds(192, 103, 26, 26);
		              layerArticleGratuit.add(labelpourcentage);
		              labelpourcentage.setFont(new Font("Tahoma", Font.BOLD, 11));
		              
		               lblReduction = new JLabel("Remise :");
		               lblReduction.setBounds(16, 103, 57, 26);
		               layerArticleGratuit.add(lblReduction);
		               
		     
		               
		                lblMontant = new JLabel("Montant  :");
		                lblMontant.setBounds(907, 48, 64, 26);
		                layerArticleGratuit.add(lblMontant);
		                
		                txtmontant = new JTextField();
		                txtmontant.setBounds(981, 48, 125, 26);
		                layerArticleGratuit.add(txtmontant);
		                txtmontant.setEditable(false);
		                txtmontant.setColumns(10);
		                
		                txtPrix = new JTextField();
		                txtPrix.setBounds(785, 48, 112, 26);
		                layerArticleGratuit.add(txtPrix);
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
		                
		                 
		                     	
		                      
		                      JLabel labelQuantit = new JLabel("QU :");
		                      labelQuantit.setBounds(598, 48, 42, 26);
		                      layerArticleGratuit.add(labelQuantit);
		                            
		                             checkttc = new JCheckBox("TTC");
		                             checkttc.addActionListener(new ActionListener() {
		                             	public void actionPerformed(ActionEvent arg0) {
		                             		if(checkttc.isSelected()==true)
		                             		{
		                             			
		                             			if(!txtPrix.getText().equals(""))
		                             			{
		                             				
		                             				prixTTC=new BigDecimal(txtPrix.getText());
		                             				if(checkboxSansTva.isSelected()==true)
		                             				{
			                             				txtPrix.setText(new BigDecimal(txtPrix.getText()).setScale(2, RoundingMode.CEILING)+"");

		                             				}else
		                             				{
			                             				txtPrix.setText(new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(1.2)).setScale(2, RoundingMode.CEILING)+"");

		                             				}
		                             			}

		                             		}else if(checkttc.isSelected()==false)
		                             		{
		                             			if(!txtPrix.getText().equals(""))
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
		                             });
		                            checkttc.setBounds(224, 105, 67, 24);
		                            layerArticleGratuit.add(checkttc);
		                            
		                            JLabel lblDesignation = new JLabel("Designation :");
		                            lblDesignation.setBounds(10, 48, 72, 26);
		                            layerArticleGratuit.add(lblDesignation);
		                            
		                            txtdesignation = new JTextField();
		                            txtdesignation.setColumns(10);
		                            txtdesignation.setBounds(80, 48, 487, 26);
		                            layerArticleGratuit.add(txtdesignation);
		                            
		                             checkboxSansTva = new JCheckBox("Sans TVA");
		                            checkboxSansTva.setBounds(1192, 227, 83, 24);
		                            add(checkboxSansTva);
		                            final JFileChooser  fileDialog = new JFileChooser();
		    		                  

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
				      
				      
				      comboFournisseur.addItem("");
					  listFournisseur=fournisseurdao.findListClientByCodeDepot(CODE_DEPOT_TANTAN);
					  for(int t=0;t<listFournisseur.size();t++)
					  {
						 
						 Client  fournisseur=listFournisseur.get(t);
						  
						  mapFournisseur.put(fournisseur.getNom(), fournisseur);
						comboFournisseur.addItem(fournisseur.getNom());
					  }
						 	      
				      
				      
		    		                
	}
	

	
	void initialiserFacture()
	{
		dateChooserfacture.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);
		comboClientpf.setSelectedIndex(-1);
		txttotalmontantTTC.setText("");
		txttotalquantite.setText("");
		txttotalmontantHT.setText("");
		txttotalmontantTVA.setText("");
		comboFournisseur.setSelectedIndex(-1);
		txtnumBL.setText("");
		
	}

	void initialiser()
	{
		 
		
		//combotypevente.setSelectedIndex(-1);
		txtdesignation.setText("");
	   txtPrix.setText("");
		txtquantite.setText("");
		txtmontant.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	   
	
	checkttc.setSelected(false);
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
	
		checkboxSansTva.setSelected(false);
		
	}
	void initialiserGratuit()
	{
		 
		
	   txtPrix.setText("");
		txtquantite.setText("");
		txtmontant.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	     combofamille.setSelectedItem("");
	    
	 
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
	
	
	void afficher_tableDetailFacturePF(List<DetailFactureAutresVente> listDetailFacture)
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Designation", "Quantite", "Prix Unitaire","Reduction %", "Montant HT", "Montant TVA", "Montant TTC"
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
		DetailFactureAutresVente detailfactureAutresVente=listDetailFacture.get(i);
			
			Object []ligne={detailfactureAutresVente.getDesignation(),detailfactureAutresVente.getQuantite(),detailfactureAutresVente.getPrixUnitaire(),detailfactureAutresVente.getReduction(),detailfactureAutresVente.getMontantHT(),detailfactureAutresVente.getMontantTVA(),detailfactureAutresVente.getMontantTTC()};

			modeleChargefixe.addRow(ligne);
			i++;
		}
}
	

	}


