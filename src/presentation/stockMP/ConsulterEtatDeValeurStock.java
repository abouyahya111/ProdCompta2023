package presentation.stockMP;

import groovy.lang.Sequence;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.Articles;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.ClientPF;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailPrixArticle;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatStockMP;
import dao.entity.EtatStockPF;
import dao.entity.EtatValeurStock;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.FicheEmploye;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.MouvementStockProduitsFini;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
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


public class ConsulterEtatDeValeurStock extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEtatStock;
	private DefaultTableModel	 modeleMouvementStock;

	private JXTable  tableEtatStock = new JXTable();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<Magasin> listMagasinPF =new ArrayList<Magasin>();
	
	private List<EtatValeurStock> listEtatStockMP =new ArrayList<EtatValeurStock>();
	
	private List<DetailTransferStockMP> listDetailTransferStockMPTmp =new ArrayList<DetailTransferStockMP>();
	
	//******************************************* Listes Pour Mouvement de Stock Mp **********************************************//
	
	
	private List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPBytypetransfer =new ArrayList<DetailTransferStockMP>();
	private List<DetailMouvementStock> listMouvementStockMP =new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficher =new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficherMouvementTmp =new ArrayList<DetailMouvementStock>();
	
	//*******************************************************************************************************************************//
	
	// ************************************************ 
	
	private List<DetailTransferStockMP> listDetailTransferStockMPInitial =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPAchat =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPCharger =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockPFEntrerMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargerSupp =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPService =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPVenteMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPAvoirMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTransfertMPPF =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPGroupBySubCategorie =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFBySousFamille=new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFInitialSousFamille=new ArrayList<DetailTransferProduitFini>();
	
	//***************************************************
	
	private List<DetailMouvementStock> listMouvementStockMPAfficherTmp =new ArrayList<DetailMouvementStock>();
	
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, Magasin> mapMagasinPF= new HashMap<>();

	private Map< String, MatierePremier> mapMP= new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP= new HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	  JComboBox combodepot = new JComboBox();
	 JComboBox combomp = new JComboBox();
	  JComboBox combomagasin = new JComboBox();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
private DetailTransferMPDAO detailTransferStockMPDAO;
private DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;

	private JTextField txtlibelle=new JTextField();
	
	
	private DepotDAO depotdao;
	 JDateChooser dateChooserdebut = new JDateChooser();
	 JDateChooser dateChooserfin = new JDateChooser();
	 private JDateChooser dateChooser = new JDateChooser();

	 JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockMPDAO stockMPDAO;
	private SubCategorieMPDAO subCategorieMPDAO;
	private CompteClientDAO compteclientdao;
	private MatierePremiereDAO MatierPremiereDAO;
	String titre="";
	 Workbook workbook = new HSSFWorkbook();
	 JLabel lblDateInitial = new JLabel("Date Initial:");
	 JDateChooser dateinitiale = new JDateChooser();
	 JButton btnTransfert = new JButton("Transfert");
	 private TransferStockMPDAO transferStockMPDAO;
	 JComboBox ComboMagasinPF = new JComboBox();
	 private ParametreDAO parametreDAO;
	 JLabel labelstockinitial = new JLabel("");
	 JLabel labelstockemballage = new JLabel("");
	 JLabel labelstocksansemballage = new JLabel("");
	  JLabel labelstockachat = new JLabel("");
	  JLabel labelachatemballage = new JLabel("");
	  JLabel labelachatsansemballage = new JLabel("");
	  JLabel labelprodentrer = new JLabel("");
	  JLabel labelprodsortie = new JLabel("");
	  JLabel labelcavente = new JLabel("");
	  JLabel labelstockavoir = new JLabel("");
	  JLabel  labelgratuite = new JLabel("");
	  JLabel labelavoiremballage = new JLabel("");
	  JLabel labelavoirsansemballage = new JLabel("");
	  JLabel  labelstockfinale = new JLabel("");
	  JLabel labelfinaleemballage = new JLabel("");
	  JLabel  labelfinalesansemballage = new JLabel("");
	  private DetailPrixArticleDAO detailPrixArticleDAO;
	  private TransferStockPFDAO transferStockPFDAO;
	  private List<EtatStockPF> listEtatStockPF =new ArrayList<EtatStockPF>();
	  private List<MouvementStockProduitsFini> listMouvementStockPFAfficherTmp =new ArrayList<MouvementStockProduitsFini>();
		private List<MouvementStockProduitsFini> listMouvementStockPFAfficher =new ArrayList<MouvementStockProduitsFini>();
		private List<DetailTransferProduitFini> listDetailTransferStockPF =new ArrayList<DetailTransferProduitFini>();
		private List<DetailTransferProduitFini> listDetailTransferStockPFGroupebyArticle =new ArrayList<DetailTransferProduitFini>();		
		private List<DetailTransferProduitFini> listDetailTransferStockPFBytypetransfer =new ArrayList<DetailTransferProduitFini>();
		private List<MouvementStockProduitsFini> listMouvementStockPF =new ArrayList<MouvementStockProduitsFini>();
		
	  private DetailTransferProduitFiniDAO detailTransferStockPFDAO;
	  
	  BigDecimal PrixMoyenFamille=BigDecimal.ZERO;
	  BigDecimal PrixMoyenVenteFamille=BigDecimal.ZERO;
	  
	  
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public ConsulterEtatDeValeurStock() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(Color.GREEN);

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1553, 1062);
      
	
        try{ 
        	
        	
        	imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	stockMPDAO=new StockMPDAOImpl();
         	mouvementStockGlobaleDAO=new MouvementStockGlobalDAOImpl();
         	MatierPremiereDAO=new MatierePremierDAOImpl();
         	detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
         	detailTransferProduitFiniDAO=new DetailTransferProduitFiniDAOImpl();
         	transferStockMPDAO=new TransferStockMPDAOImpl();
         	subCategorieMPDAO=new SubCategorieMPAOImpl();
         listMP=MatierPremiereDAO.findAll();
         parametreDAO = new ParametreDAOImpl();
     	detailPrixArticleDAO=new DetailPrixArticleDAOImpl();
     	transferStockPFDAO=new TransferStockPFDAOImpl();
     	detailTransferStockPFDAO=new DetailTransferProduitFiniDAOImpl();
     	
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableEtatStock.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
				"Article","Famille", "Sous Famille", "Quantite Initial", "Prix Initial","Montant Initial", "Quantite Achat", "Prix Achat","Montant Achat","Quantite Production Entrer", "Prix Production Entrer","Montant Production Entrer","Quantite Production Sortie", "Prix Production Sortie","Montant Production Sortie","Quantite Vente", "Prix Vente","Montant Vente","Quantite Gratuite", "Prix Gratuite","Montant Gratuite","Quantite Avoir", "Prix Avoir","Montant Avoir","Quantite Finale", "Prix Finale","Montant Finale",
       	}
       ));
       tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
       tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
       tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(118);
       tableEtatStock.getColumnModel().getColumn(6).setPreferredWidth(132);
       tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(92);
       tableEtatStock.getColumnModel().getColumn(8).setPreferredWidth(95);
       tableEtatStock.getColumnModel().getColumn(9).setPreferredWidth(99);
				  		
       tableEtatStock.setShowVerticalLines(false);
       tableEtatStock.setSelectionBackground(new Color(51, 204, 255));
       tableEtatStock.setRowHeightEnabled(true);
       tableEtatStock.setBackground(new Color(255, 255, 255));
       tableEtatStock.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableEtatStock.setColumnControlVisible(true);
       tableEtatStock.setForeground(Color.BLACK);
       tableEtatStock.setGridColor(new Color(0, 0, 255));
       tableEtatStock.setAutoCreateRowSorter(true);
       tableEtatStock.setBounds(2, 27, 411, 198);
       tableEtatStock.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableEtatStock);
				  		     	scrollPane.setBounds(10, 195, 1497, 587);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Etat de Stock");
				  		     	titledSeparator.setBounds(10, 154, 1465, 30);
				  		     	add(titledSeparator);
		      
		     	
		      
		     
		     
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
}});
		
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(874, 975, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		buttonvalider.setVisible(false);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter Etat Valeur de Stock :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 836, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		detailTransferStockMPDAO.ViderSession();
	    		listEtatStockMP.clear();
	    		listDetailTransferStockMPInitial.clear() ;
	    		String DPF="_PFD";
	    		String OPF="_PFO";
	    		BigDecimal montantInitial=new BigDecimal(0);
	    		BigDecimal quantiteTotalInitial=new BigDecimal(0);
	    		BigDecimal montantachat=new BigDecimal(0);
	    		BigDecimal quantiteTotalachat=new BigDecimal(0);
	    		BigDecimal montantVente=new BigDecimal(0);
	    		BigDecimal quantiteTotalVente=new BigDecimal(0);
	    		BigDecimal montantGratuite=new BigDecimal(0);
	    		BigDecimal montantCharger=new BigDecimal(0);
	    		BigDecimal quantiteTotalCharger=new BigDecimal(0);
	    		BigDecimal montantChargersupp=new BigDecimal(0);
	    		BigDecimal quantiteTotalChargersupp=new BigDecimal(0);
	    		BigDecimal montantService=new BigDecimal(0);
	    		BigDecimal quantiteTotalService=new BigDecimal(0);
	    		BigDecimal montantTransfertMPPF=new BigDecimal(0);
	    		BigDecimal quantiteTotalTransfertMPPF=new BigDecimal(0);
	    		BigDecimal quantiteTotalGratuite=new BigDecimal(0);
	    		BigDecimal montantAvoir=new BigDecimal(0);
	    		BigDecimal quantiteTotalAvoir=new BigDecimal(0);
	    		BigDecimal montantProductionEnter=new BigDecimal(0);
	    		BigDecimal quantiteTotalProductionEnter=new BigDecimal(0);
	    		
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		Magasin magasinPF=mapMagasinPF.get(ComboMagasinPF.getSelectedItem());
	    		SubCategorieMp subCategorieMp=subCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
	    		SubCategorieMp subCategorieMpAdhesif=subCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_ADHESIF);
	    		SubCategorieMp subCategorieMpCarton=subCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON);
	    		SubCategorieMp subCategorieMpCadeau=subCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU);
	    		if(magasin!=null && magasinPF!=null  )
	    		{	
	    			
	    			

	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	

	    			
	    		
	    	///////////////////////////////////////////////////////////////////// Liste Group Envrac /////////////////////////////////////////////////////////////////////////////////////////
	    		
	    		listDetailTransferStockMPGroupBySubCategorie=detailTransferStockMPDAO.findAllTransferStockMPBySubCategorieGroupeByMP(magasin, subCategorieMp)	;
	    		
	    		for(int i=0;i<listDetailTransferStockMPGroupBySubCategorie.size();i++)
	    		{
	    			
	    		DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPGroupBySubCategorie.get(i);	
	    		EtatValeurStock etatValeurStock=new EtatValeurStock();	
	    		etatValeurStock.setCodearticle(detailTransferStockMP.getMatierePremier().getCode());;	
	    		etatValeurStock.setArticle(detailTransferStockMP.getMatierePremier().getNom());
	    		etatValeurStock.setFamille(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
	    		etatValeurStock.setSousfamille (detailTransferStockMP.getMatierePremier().getCategorieMp().getNom());
	    		etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
	    		etatValeurStock.setPrixInitial(BigDecimal.ZERO);	
	    		etatValeurStock.setMontantInitial(BigDecimal.ZERO);
	    		etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
	    		etatValeurStock.setPrixAchat(BigDecimal.ZERO);
	    		etatValeurStock.setMontantAchat (BigDecimal.ZERO);
	    		etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
	    		etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
	    		etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
	    		etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
	    		etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
	    		etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
	    		etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
	    		etatValeurStock.setPrixVente(BigDecimal.ZERO);
	    		etatValeurStock.setMontantVente(BigDecimal.ZERO);
	    		etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
	    		etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
	    		etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
	    		etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
	    		etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
	    		etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
	    		etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
	    		etatValeurStock.setPrixFinale(BigDecimal.ZERO);
	    		etatValeurStock.setMontantFinale(BigDecimal.ZERO);
	    		
	    		listEtatStockMP.add(etatValeurStock);
	    		
	    		}
	    		
	    		
////////////////////////////////////////////////////////////////////////////////////////////// Liste Initial En Vrac /////////////////////////////////////////////////////////////////////////////////	    		
	    		
	    		
	    		listDetailTransferStockMPInitial=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_INITIAL,magasin,subCategorieMp);
	    		
	    		
	    		for(int j=0;j<listEtatStockMP.size();j++)
		    	{
	    			montantInitial=new BigDecimal(0);
		    		quantiteTotalInitial=new BigDecimal(0);
		    		
		    		EtatValeurStock etatstockmp=listEtatStockMP.get(j);	
		    		
		    	for(int k=0;k<listDetailTransferStockMPInitial.size();k++)
		    	{
		    		
		    		if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPInitial.get(k).getMatierePremier().getCode()))
		    		{
		    			

    						montantInitial=montantInitial.add(listDetailTransferStockMPInitial.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPInitial.get(k).getQuantite()));
    		    			quantiteTotalInitial=quantiteTotalInitial.add(listDetailTransferStockMPInitial.get(k).getQuantite());

		    
		    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		    			
		    		}
		    		
		    		
		    	}
		    		if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
		    		{

		    			etatstockmp.setQuantiteInitial (quantiteTotalInitial);		    			
		    			etatstockmp.setPrixInitial(montantInitial.divide(quantiteTotalInitial,6,RoundingMode.HALF_UP));
		    			etatstockmp.setMontantInitial(etatstockmp.getQuantiteInitial().multiply(etatstockmp.getPrixInitial()));
		    			listEtatStockMP.set(j, etatstockmp);
		    			
		    		}
		    		
		    	}
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		//////////////////////////////////////////////////////////////////////////// Liste Des Achat En Vrac ////////////////////////////////////////////////////////////////////////////////////////////////
	    		
	    		listDetailTransferStockMPAchat=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_ACHAT,magasin,subCategorieMp);
	
	    		for(int j=0;j<listEtatStockMP.size();j++)
		    	{
		    		montantachat=new BigDecimal(0);
		    		quantiteTotalachat=new BigDecimal(0);
		    		
		    		EtatValeurStock etatstockmp=listEtatStockMP.get(j);	
		    		
		    	for(int k=0;k<listDetailTransferStockMPAchat.size();k++)
		    	{
		    		
		    		if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPAchat.get(k).getMatierePremier().getCode()))
		    		{
		    			

    						montantachat=montantachat.add(listDetailTransferStockMPAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAchat.get(k).getQuantite()));
    		    			quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockMPAchat.get(k).getQuantite());

		    
		    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		    			
		    		}
		    		
		    		
		    	}
		    		if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
		    		{

		    			etatstockmp.setQuantiteAchat(quantiteTotalachat);		    			
		    			etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
		    			etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
		    			listEtatStockMP.set(j, etatstockmp);
		    			
		    		}
		    		
		    	}
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Liste Des production Sortie En Vrac Charger   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		
	    		
	    		listDetailTransferStockMPCharger=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_CHARGE,magasin,subCategorieMp);
	      		for(int j=0;j<listEtatStockMP.size();j++)
		    	{
	      			montantCharger=new BigDecimal(0);
	      			quantiteTotalCharger=new BigDecimal(0);
		    		
		    		EtatValeurStock etatstockmp=listEtatStockMP.get(j);	
		    		
		    	for(int k=0;k<listDetailTransferStockMPCharger.size();k++)
		    	{
		    		
		    		if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPCharger.get(k).getMatierePremier().getCode()))
		    		{
		    			BigDecimal quantite=BigDecimal.ZERO;
		    			quantite=listDetailTransferStockMPCharger.get(k).getQuantite();
		    			if(listDetailTransferStockMPCharger.get(k).getQuantiteOffre()!=null)
		    			{
		    				quantite=quantite.add(listDetailTransferStockMPCharger.get(k).getQuantiteOffre());
		    			}
		    			
		    			if(listDetailTransferStockMPCharger.get(k).getQuantiteDechet()!=null)
		    			{
		    				quantite=quantite.add(listDetailTransferStockMPCharger.get(k).getQuantiteDechet());
		    			}

    						montantCharger=montantCharger.add(listDetailTransferStockMPCharger.get(k).getPrixUnitaire().multiply(quantite));
    						
    		    			quantiteTotalCharger=quantiteTotalCharger.add(quantite);

		    
		    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		    			
		    		}
		    		
		    		
		    	}
		    		if(!montantCharger.equals(BigDecimal.ZERO) && !quantiteTotalCharger.equals(BigDecimal.ZERO))
		    		{

		    			etatstockmp.setQuantiteProductionSortie (etatstockmp.getQuantiteProductionSortie().add(quantiteTotalCharger));		    			
		    			etatstockmp.setPrixProductionSortie((etatstockmp.getMontantProductionSortie().add(montantCharger)).divide(etatstockmp.getQuantiteProductionSortie(),6,RoundingMode.HALF_UP));
		    			etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
		    			listEtatStockMP.set(j, etatstockmp);
		    			
		    		}
		    		
		    	}
	    		
	    		
	    		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Liste Des production Sortie En Vrac ChargerSupp   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		
	    		
listDetailTransferStockMPChargerSupp=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin,subCategorieMp);
for(int j=0;j<listEtatStockMP.size();j++)
{
	montantChargersupp=new BigDecimal(0);
		quantiteTotalChargersupp=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPChargerSupp.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPChargerSupp.get(k).getMatierePremier().getCode()))
{


montantChargersupp=montantChargersupp.add(listDetailTransferStockMPChargerSupp.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPChargerSupp.get(k).getQuantite()));
quantiteTotalChargersupp=quantiteTotalChargersupp.add(listDetailTransferStockMPChargerSupp.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantChargersupp.equals(BigDecimal.ZERO) && !quantiteTotalChargersupp.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteProductionSortie (etatstockmp.getQuantiteProductionSortie().add(quantiteTotalChargersupp));		    			
etatstockmp.setPrixProductionSortie((etatstockmp.getMontantProductionSortie().add(montantChargersupp)).divide(etatstockmp.getQuantiteProductionSortie(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
listEtatStockMP.set(j, etatstockmp);

}

}
   		
	    		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Liste Des production Sortie En Vrac Service   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		

listDetailTransferStockMPService=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_SERVICE,magasin,subCategorieMp);
for(int j=0;j<listEtatStockMP.size();j++)
{
montantService=new BigDecimal(0);
quantiteTotalService=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPService.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPService.get(k).getMatierePremier().getCode()))
{
	
	BigDecimal quantite=BigDecimal.ZERO;
	quantite=listDetailTransferStockMPService.get(k).getQuantite();
	if(listDetailTransferStockMPService.get(k).getQuantiteOffre()!=null)
	{
		quantite=quantite.add(listDetailTransferStockMPService.get(k).getQuantiteOffre());
	}
	
	if(listDetailTransferStockMPService.get(k).getQuantiteDechet()!=null)
	{
		quantite=quantite.add(listDetailTransferStockMPService.get(k).getQuantiteDechet());
	}


montantService=montantService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(quantite));
quantiteTotalService=quantiteTotalService.add(quantite);


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantService.equals(BigDecimal.ZERO) && !quantiteTotalService.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteProductionSortie (etatstockmp.getQuantiteProductionSortie().add(quantiteTotalService));		    			
etatstockmp.setPrixProductionSortie((etatstockmp.getMontantProductionSortie().add(montantService)).divide(etatstockmp.getQuantiteProductionSortie(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
listEtatStockMP.set(j, etatstockmp);

}

}

/////////////////////////////////////////////////////////////////////////////////////////////// Calculer Prix Moyen MP ///////////////////////////////////////////////////////////

for(int i=0;i<listEtatStockMP.size();i++)
{
	
	EtatValeurStock etatstockmp=listEtatStockMP.get(i);		
	if(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat()).compareTo(BigDecimal.ZERO)>0)
	{
		if(etatstockmp.getQuantiteProductionSortie().compareTo(BigDecimal.ZERO)>0)
		{
			etatstockmp.setPrixProductionSortie((etatstockmp.getMontantInitial().add(etatstockmp.getMontantAchat())).divide(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat()), 6, RoundingMode.HALF_UP));
			etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
		}
	
		
	}

	
}
	    		


//////////////////////////////////////////////////////////////////////////// Liste Des Ventes En Vrac ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPVenteMP=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_VENTE,magasin,subCategorieMp);

for(int j=0;j<listEtatStockMP.size();j++)
{
	montantVente=new BigDecimal(0);
	quantiteTotalVente=new BigDecimal(0);
	
	EtatValeurStock etatstockmp=listEtatStockMP.get(j);	
	
for(int k=0;k<listDetailTransferStockMPVenteMP.size();k++)
{
	
	if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPVenteMP.get(k).getMatierePremier().getCode()))
	{
		if(listDetailTransferStockMPVenteMP.get(k).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
		{
			
			quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockMPVenteMP.get(k).getQuantite());
			
 			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getId(), magasinPF.getId(),listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getId() , listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getId());

	if(detailprixarticleTmp!=null)
	{
		
		montantGratuite=montantGratuite.add(detailprixarticleTmp.getPrix().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
		
		
		
	}
		}else
		{
			montantVente=montantVente.add(listDetailTransferStockMPVenteMP.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPVenteMP.get(k).getQuantite()));
			quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockMPVenteMP.get(k).getQuantite());

		}

	

		//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		
	}
	
	
}
	if(!montantVente.equals(BigDecimal.ZERO) && !quantiteTotalVente.equals(BigDecimal.ZERO))
	{

		etatstockmp.setQuantiteVente(quantiteTotalVente);		    			
		etatstockmp.setPrixVente(montantVente.divide(quantiteTotalVente,6,RoundingMode.HALF_UP));
		etatstockmp.setMontantVente(etatstockmp.getQuantiteVente().multiply(etatstockmp.getPrixVente()));		
		
		listEtatStockMP.set(j, etatstockmp);
		
	}
	
	if(!quantiteTotalGratuite.equals(BigDecimal.ZERO))
	{
		etatstockmp.setQuantiteGratuite(quantiteTotalGratuite);
		listEtatStockMP.set(j, etatstockmp);
	}
		
	
}
	    		

////////////////////////////////////////////////////////////////////////////Liste Des Transfert En Vrac En PF ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPTransfertMPPF=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasin,subCategorieMp);

for(int j=0;j<listEtatStockMP.size();j++)
{
montantTransfertMPPF=new BigDecimal(0);
quantiteTotalTransfertMPPF=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPTransfertMPPF.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPTransfertMPPF.get(k).getMatierePremier().getCode()))
{
	
TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(listDetailTransferStockMPTransfertMPPF.get(k).getTransferStockMP().getCodeTransfer());
if(transferStockPF!=null)
{
	BigDecimal MontantVentePF=BigDecimal.ZERO;
	BigDecimal QuantiteVentePF=BigDecimal.ZERO;
	BigDecimal PrixVentePF=BigDecimal.ZERO;
	
	 List<DetailTransferProduitFini> listDetailTransferStockPFVenteTransferMPPF=new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFVentePF=new ArrayList<DetailTransferProduitFini>();
	 listDetailTransferStockPFVenteTransferMPPF=detailTransferProduitFiniDAO.findByTransferStockPF(transferStockPF.getId());
	 listDetailTransferStockPFVentePF=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), listDetailTransferStockPFVenteTransferMPPF.get(0).getArticle(), ETAT_TRANSFER_STOCK_VENTE, magasinPF, listDetailTransferStockPFVenteTransferMPPF.get(0).getSousFamille().getFamileArticlePF());
	 
	 for(int f=0;f<listDetailTransferStockPFVentePF.size();f++)
	 {
		
		 if(listDetailTransferStockPFVentePF.get(f).getArticle().getId()==listDetailTransferStockPFVenteTransferMPPF.get(0).getArticle().getId())
		 {
			 if(listDetailTransferStockPFVentePF.get(f).getSousFamille().getId()==listDetailTransferStockPFVenteTransferMPPF.get(0).getSousFamille().getId())
			 {
				 
				 MontantVentePF=MontantVentePF.add(listDetailTransferStockPFVentePF.get(f).getPrixUnitaire().multiply(listDetailTransferStockPFVentePF.get(f).getQuantite())) ;
				 QuantiteVentePF=QuantiteVentePF.add(listDetailTransferStockPFVentePF.get(f).getQuantite());
				 
			 }
			 
			 
			 
			 
		 }
		 
		 
		 
	 }
	 
	 
	 if(!MontantVentePF.equals(BigDecimal.ZERO) && !QuantiteVentePF.equals(BigDecimal.ZERO))
	 {
		 
		 PrixVentePF= MontantVentePF.divide(QuantiteVentePF, 6, RoundingMode.HALF_UP);
		 
		 
	 }
	 
	  montantTransfertMPPF=montantTransfertMPPF.add(PrixVentePF.multiply(listDetailTransferStockMPTransfertMPPF.get(k).getQuantite()));
	
	quantiteTotalTransfertMPPF=quantiteTotalTransfertMPPF.add(listDetailTransferStockMPTransfertMPPF.get(k).getQuantite());
	
}else
{
	montantTransfertMPPF=montantTransfertMPPF.add(listDetailTransferStockMPTransfertMPPF.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransfertMPPF.get(k).getQuantite()));
	quantiteTotalTransfertMPPF=quantiteTotalTransfertMPPF.add(listDetailTransferStockMPTransfertMPPF.get(k).getQuantite());
}



//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantTransfertMPPF.equals(BigDecimal.ZERO) && !quantiteTotalTransfertMPPF.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteVente(etatstockmp.getQuantiteVente().add(quantiteTotalTransfertMPPF));		    			
etatstockmp.setPrixVente((etatstockmp.getMontantVente().add(montantTransfertMPPF)).divide(etatstockmp.getQuantiteVente(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantVente(etatstockmp.getQuantiteVente().multiply(etatstockmp.getPrixVente()));
listEtatStockMP.set(j, etatstockmp);

}

}




    		
	
//////////////////////////////////////////////////////////////////////////// Liste Des Avoirs En Vrac ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPAvoirMP=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_AVOIR,magasin,subCategorieMp);

for(int j=0;j<listEtatStockMP.size();j++)
{
	montantAvoir=new BigDecimal(0);
	quantiteTotalAvoir=new BigDecimal(0);
	
	EtatValeurStock etatstockmp=listEtatStockMP.get(j);	
	
for(int k=0;k<listDetailTransferStockMPAvoirMP.size();k++)
{
	
	if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPAvoirMP.get(k).getMatierePremier().getCode()))
	{
		

			montantAvoir=montantAvoir.add(listDetailTransferStockMPAvoirMP.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAvoirMP.get(k).getQuantite()));
			quantiteTotalAvoir=quantiteTotalAvoir.add(listDetailTransferStockMPAvoirMP.get(k).getQuantite());


		//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		
	}
	
	
}
	if(!montantAvoir.equals(BigDecimal.ZERO) && !quantiteTotalAvoir.equals(BigDecimal.ZERO))
	{

		etatstockmp.setQuantiteAvoir(quantiteTotalAvoir);		    			
		etatstockmp.setPrixAvoir(montantAvoir.divide(quantiteTotalAvoir,6,RoundingMode.HALF_UP));
		etatstockmp.setMontantAvoir(etatstockmp.getQuantiteAvoir().multiply(etatstockmp.getPrixAvoir()));
		listEtatStockMP.set(j, etatstockmp);
		
	}
	
}



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




///////////////////////////////////////////////////////////////////// Liste Group CADEAU /////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPGroupBySubCategorie=detailTransferStockMPDAO.findAllTransferStockMPBySubCategorieGroupeByMP(magasin, subCategorieMpCadeau)	;

for(int i=0;i<listDetailTransferStockMPGroupBySubCategorie.size();i++)
{

DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPGroupBySubCategorie.get(i);	
EtatValeurStock etatValeurStock=new EtatValeurStock();	
etatValeurStock.setCodearticle(detailTransferStockMP.getMatierePremier().getCode());;	
etatValeurStock.setArticle(detailTransferStockMP.getMatierePremier().getNom());
etatValeurStock.setFamille(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
etatValeurStock.setSousfamille (detailTransferStockMP.getMatierePremier().getCategorieMp().getNom());
etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
etatValeurStock.setPrixInitial(BigDecimal.ZERO);	
etatValeurStock.setMontantInitial(BigDecimal.ZERO);
etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
etatValeurStock.setPrixAchat(BigDecimal.ZERO);
etatValeurStock.setMontantAchat (BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
etatValeurStock.setPrixVente(BigDecimal.ZERO);
etatValeurStock.setMontantVente(BigDecimal.ZERO);
etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
etatValeurStock.setPrixFinale(BigDecimal.ZERO);
etatValeurStock.setMontantFinale(BigDecimal.ZERO);

listEtatStockMP.add(etatValeurStock);

}


//////////////////////////////////////////////////////////////////////////////////////////////Liste Initial CADEAU /////////////////////////////////////////////////////////////////////////////////	    		


listDetailTransferStockMPInitial=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_INITIAL,magasin,subCategorieMpCadeau);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantInitial=new BigDecimal(0);
quantiteTotalInitial=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPInitial.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPInitial.get(k).getMatierePremier().getCode()))
{


montantInitial=montantInitial.add(listDetailTransferStockMPInitial.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPInitial.get(k).getQuantite()));
quantiteTotalInitial=quantiteTotalInitial.add(listDetailTransferStockMPInitial.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteInitial (quantiteTotalInitial);		    			
etatstockmp.setPrixInitial(montantInitial.divide(quantiteTotalInitial,6,RoundingMode.HALF_UP));
etatstockmp.setMontantInitial(etatstockmp.getQuantiteInitial().multiply(etatstockmp.getPrixInitial()));
listEtatStockMP.set(j, etatstockmp);

}

}















//////////////////////////////////////////////////////////////////////////// Liste Des Achat CADEAU ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPAchat=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_ACHAT,magasin,subCategorieMpCadeau);

for(int j=0;j<listEtatStockMP.size();j++)
{
montantachat=new BigDecimal(0);
quantiteTotalachat=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPAchat.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPAchat.get(k).getMatierePremier().getCode()))
{


montantachat=montantachat.add(listDetailTransferStockMPAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAchat.get(k).getQuantite()));
quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockMPAchat.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAchat(quantiteTotalachat);		    			
etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
listEtatStockMP.set(j, etatstockmp);

}

}







///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Liste Des production Sortie CADEAU Charger   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		

listDetailTransferStockMPCharger=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_CHARGE,magasin,subCategorieMpCadeau);
for(int j=0;j<listEtatStockMP.size();j++)
{
montantCharger=new BigDecimal(0);
quantiteTotalCharger=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPCharger.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPCharger.get(k).getMatierePremier().getCode()))
{
BigDecimal quantite=BigDecimal.ZERO;
quantite=listDetailTransferStockMPCharger.get(k).getQuantite();
if(listDetailTransferStockMPCharger.get(k).getQuantiteOffre()!=null)
{
quantite=quantite.add(listDetailTransferStockMPCharger.get(k).getQuantiteOffre());
}

if(listDetailTransferStockMPCharger.get(k).getQuantiteDechet()!=null)
{
quantite=quantite.add(listDetailTransferStockMPCharger.get(k).getQuantiteDechet());
}

montantCharger=montantCharger.add(listDetailTransferStockMPCharger.get(k).getPrixUnitaire().multiply(quantite));

quantiteTotalCharger=quantiteTotalCharger.add(quantite);


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantCharger.equals(BigDecimal.ZERO) && !quantiteTotalCharger.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteProductionSortie (etatstockmp.getQuantiteProductionSortie().add(quantiteTotalCharger));		    			
etatstockmp.setPrixProductionSortie((etatstockmp.getMontantProductionSortie().add(montantCharger)).divide(etatstockmp.getQuantiteProductionSortie(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
listEtatStockMP.set(j, etatstockmp);

}

}



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Liste Des production Sortie CADEAU ChargerSupp   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		

listDetailTransferStockMPChargerSupp=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin,subCategorieMpCadeau);
for(int j=0;j<listEtatStockMP.size();j++)
{
montantChargersupp=new BigDecimal(0);
quantiteTotalChargersupp=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPChargerSupp.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPChargerSupp.get(k).getMatierePremier().getCode()))
{


montantChargersupp=montantChargersupp.add(listDetailTransferStockMPChargerSupp.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPChargerSupp.get(k).getQuantite()));
quantiteTotalChargersupp=quantiteTotalChargersupp.add(listDetailTransferStockMPChargerSupp.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantChargersupp.equals(BigDecimal.ZERO) && !quantiteTotalChargersupp.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteProductionSortie (etatstockmp.getQuantiteProductionSortie().add(quantiteTotalChargersupp));		    			
etatstockmp.setPrixProductionSortie((etatstockmp.getMontantProductionSortie().add(montantChargersupp)).divide(etatstockmp.getQuantiteProductionSortie(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
listEtatStockMP.set(j, etatstockmp);

}

}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Liste Des production Sortie CADEAU Service   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		

listDetailTransferStockMPService=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_SERVICE,magasin,subCategorieMpCadeau);
for(int j=0;j<listEtatStockMP.size();j++)
{
montantService=new BigDecimal(0);
quantiteTotalService=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPService.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPService.get(k).getMatierePremier().getCode()))
{

BigDecimal quantite=BigDecimal.ZERO;
quantite=listDetailTransferStockMPService.get(k).getQuantite();
if(listDetailTransferStockMPService.get(k).getQuantiteOffre()!=null)
{
quantite=quantite.add(listDetailTransferStockMPService.get(k).getQuantiteOffre());
}

if(listDetailTransferStockMPService.get(k).getQuantiteDechet()!=null)
{
quantite=quantite.add(listDetailTransferStockMPService.get(k).getQuantiteDechet());
}


montantService=montantService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(quantite));
quantiteTotalService=quantiteTotalService.add(quantite);


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantService.equals(BigDecimal.ZERO) && !quantiteTotalService.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteProductionSortie (etatstockmp.getQuantiteProductionSortie().add(quantiteTotalService));		    			
etatstockmp.setPrixProductionSortie((etatstockmp.getMontantProductionSortie().add(montantService)).divide(etatstockmp.getQuantiteProductionSortie(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
listEtatStockMP.set(j, etatstockmp);

}

}

/////////////////////////////////////////////////////////////////////////////////////////////// Calculer Prix Moyen MP ///////////////////////////////////////////////////////////

for(int i=0;i<listEtatStockMP.size();i++)
{

EtatValeurStock etatstockmp=listEtatStockMP.get(i);		
if(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat()).compareTo(BigDecimal.ZERO)>0)
{
if(etatstockmp.getQuantiteProductionSortie().compareTo(BigDecimal.ZERO)>0)
{
etatstockmp.setPrixProductionSortie((etatstockmp.getMontantInitial().add(etatstockmp.getMontantAchat())).divide(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat()), 6, RoundingMode.HALF_UP));
etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
}


}


}



////////////////////////////////////////////////////////////////////////////Liste Des Ventes CADEAU ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPVenteMP=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_VENTE,magasin,subCategorieMpCadeau);

for(int j=0;j<listEtatStockMP.size();j++)
{
montantVente=new BigDecimal(0);
quantiteTotalVente=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPVenteMP.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPVenteMP.get(k).getMatierePremier().getCode()))
{
if(listDetailTransferStockMPVenteMP.get(k).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
{

quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockMPVenteMP.get(k).getQuantite());

DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getId(), magasinPF.getId(),listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getId() , listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getId());

if(detailprixarticleTmp!=null)
{

montantGratuite=montantGratuite.add(detailprixarticleTmp.getPrix().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));



}
}else
{
montantVente=montantVente.add(listDetailTransferStockMPVenteMP.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPVenteMP.get(k).getQuantite()));
quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockMPVenteMP.get(k).getQuantite());

}



//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantVente.equals(BigDecimal.ZERO) && !quantiteTotalVente.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteVente(quantiteTotalVente);		    			
etatstockmp.setPrixVente(montantVente.divide(quantiteTotalVente,6,RoundingMode.HALF_UP));
etatstockmp.setMontantVente(etatstockmp.getQuantiteVente().multiply(etatstockmp.getPrixVente()));		

listEtatStockMP.set(j, etatstockmp);

}

if(!quantiteTotalGratuite.equals(BigDecimal.ZERO))
{
etatstockmp.setQuantiteGratuite(quantiteTotalGratuite);
listEtatStockMP.set(j, etatstockmp);
}


}


////////////////////////////////////////////////////////////////////////////Liste Des Transfert CADEAU En PF ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPTransfertMPPF=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasin,subCategorieMpCadeau);

for(int j=0;j<listEtatStockMP.size();j++)
{
montantTransfertMPPF=new BigDecimal(0);
quantiteTotalTransfertMPPF=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPTransfertMPPF.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPTransfertMPPF.get(k).getMatierePremier().getCode()))
{


montantTransfertMPPF=montantTransfertMPPF.add(listDetailTransferStockMPTransfertMPPF.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransfertMPPF.get(k).getQuantite()));
quantiteTotalTransfertMPPF=quantiteTotalTransfertMPPF.add(listDetailTransferStockMPTransfertMPPF.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantTransfertMPPF.equals(BigDecimal.ZERO) && !quantiteTotalTransfertMPPF.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteVente(etatstockmp.getQuantiteVente().add(quantiteTotalTransfertMPPF));		    			
etatstockmp.setPrixVente((etatstockmp.getMontantVente().add(montantTransfertMPPF)).divide(etatstockmp.getQuantiteVente(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantVente(etatstockmp.getQuantiteVente().multiply(etatstockmp.getPrixVente()));
listEtatStockMP.set(j, etatstockmp);

}

}


////////////////////////////////////////////////////////////////////////////Liste Des Avoirs CADEAU ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPAvoirMP=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_AVOIR,magasin,subCategorieMpCadeau);

for(int j=0;j<listEtatStockMP.size();j++)
{
montantAvoir=new BigDecimal(0);
quantiteTotalAvoir=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPAvoirMP.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPAvoirMP.get(k).getMatierePremier().getCode()))
{


montantAvoir=montantAvoir.add(listDetailTransferStockMPAvoirMP.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAvoirMP.get(k).getQuantite()));
quantiteTotalAvoir=quantiteTotalAvoir.add(listDetailTransferStockMPAvoirMP.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantAvoir.equals(BigDecimal.ZERO) && !quantiteTotalAvoir.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAvoir(quantiteTotalAvoir);		    			
etatstockmp.setPrixAvoir(montantAvoir.divide(quantiteTotalAvoir,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAvoir(etatstockmp.getQuantiteAvoir().multiply(etatstockmp.getPrixAvoir()));
listEtatStockMP.set(j, etatstockmp);

}

}








////////////////////////////////////////////////////////////////////////////// Liste Des PF Par Sous Famille ////////////////////////////////////////////////////////////////////////////

listDetailTransferStockPFBySousFamille=detailTransferProduitFiniDAO.ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupBySousFamille(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasinPF);
	 



	 for(int i=0;i<listDetailTransferStockPFBySousFamille.size();i++)
	 {
		 

			
 		DetailTransferProduitFini detailTransferStockPF=listDetailTransferStockPFBySousFamille.get(i);	
 		
 		
 		
 		if(detailTransferStockPF.getSousFamille().getFamileArticlePF().getCode().contentEquals(Constantes.CODE_CHAARA) || detailTransferStockPF.getSousFamille().getFamileArticlePF().getCode().contentEquals(Constantes.CODE_MKARKEB))
 		{
 			 	EtatValeurStock etatValeurStock=new EtatValeurStock();	
 	 	 		etatValeurStock.setCodearticle(detailTransferStockPF.getSousFamille().getCode());;	
 	 	 		etatValeurStock.setArticle(detailTransferStockPF.getSousFamille().getLiblle());
 	 	 		etatValeurStock.setFamille(detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle());
 	 	 		etatValeurStock.setSousfamille (detailTransferStockPF.getSousFamille().getLiblle());
 	 	 		etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
 	 	 		etatValeurStock.setPrixInitial(BigDecimal.ZERO);	
 	 	 		etatValeurStock.setMontantInitial(BigDecimal.ZERO);
 	 	 		etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
 	 	 		etatValeurStock.setPrixAchat(BigDecimal.ZERO);
 	 	 		etatValeurStock.setMontantAchat (BigDecimal.ZERO);
 	 	 		etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
 	 	 		etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
 	 	 		etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
 	 	 		etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
 	 	 		etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
 	 	 		etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
 	 	 		etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
 	 	 		etatValeurStock.setPrixVente(BigDecimal.ZERO);
 	 	 		etatValeurStock.setMontantVente(BigDecimal.ZERO);
 	 	 		etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
 	 	 		etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
 	 	 		etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
 	 	 		etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
 	 	 		etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
 	 	 		etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
 	 	 		etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
 	 	 		etatValeurStock.setPrixFinale(BigDecimal.ZERO);
 	 	 		etatValeurStock.setMontantFinale(BigDecimal.ZERO);
 	 	 		
 	 	 		listEtatStockMP.add(etatValeurStock);
 				
 			
 	 		
 	 		
 		}

 		
	 }
	 
///////////////////////////////////////////////////////////////////////////////////////// Initial de sous famille 	 ////////////////////////////////////////////////////////////////////
	 String requete="";
	 requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"')";
	 listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_INITIAL, requete);
	 

	 for(int j=0;j<listEtatStockMP.size();j++)
	 {
	 	montantInitial=new BigDecimal(0);
	 	quantiteTotalInitial=new BigDecimal(0);
	 	
	 	EtatValeurStock etatstockmp=listEtatStockMP.get(j);	
	 	
	 for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
	 {
	 	
	 	if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
	 	{
	 		if(!listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle().contains("_PFO"))
	 		{
	 			montantInitial=montantInitial.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
	 			quantiteTotalInitial=quantiteTotalInitial.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());

	 		}

	 			

	 		//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
	 		
	 	}
	 	
	 	
	 }
	 	if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
	 	{

	 		etatstockmp.setQuantiteInitial(quantiteTotalInitial);		    			
	 		etatstockmp.setPrixInitial(montantInitial.divide(quantiteTotalInitial,6,RoundingMode.HALF_UP));
	 		etatstockmp.setMontantInitial(etatstockmp.getQuantiteInitial().multiply(etatstockmp.getPrixInitial()));
	 		listEtatStockMP.set(j, etatstockmp);
	 		
	 	}
	 	
	 }
	 
	 
///////////////////////////////////////////////////////////////////////////////////////// Achat de sous famille 	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_ACHAT, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantachat=new BigDecimal(0);
quantiteTotalachat=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{

	if(!listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle().contains("_PFO"))
	{
		montantachat=montantachat.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
		quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
	
	}


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAchat(quantiteTotalachat);		    			
etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
listEtatStockMP.set(j, etatstockmp);

}

}


///////////////////////////////////////////////////////////////////////////////////////// Production Entrer de sous famille 	 ////////////////////////////////////////////////////////////////////



requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantProductionEnter=new BigDecimal(0);
quantiteTotalProductionEnter=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{
	if(!listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle().contains("_PFO"))
	{
		montantProductionEnter=montantProductionEnter.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
		quantiteTotalProductionEnter=quantiteTotalProductionEnter.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());

	}



//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantProductionEnter.equals(BigDecimal.ZERO) && !quantiteTotalProductionEnter.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteProductionEntrer (quantiteTotalProductionEnter);		    			
etatstockmp.setPrixProductionEntrer(montantProductionEnter.divide(quantiteTotalProductionEnter,6,RoundingMode.HALF_UP));
etatstockmp.setMontantProductionEntrer(etatstockmp.getQuantiteProductionEntrer().multiply(etatstockmp.getPrixProductionEntrer()));
listEtatStockMP.set(j, etatstockmp);

}

}


///////////////////////////////////////////////////////////////////////////////////////// Les ventes de sous famille 	 ////////////////////////////////////////////////////////////////////



requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_VENTE, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantVente=new BigDecimal(0);
montantGratuite=new BigDecimal(0);
quantiteTotalVente=new BigDecimal(0);
quantiteTotalGratuite=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{

if(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
{
	
	if(!listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle().contains("_PFO"))
	{
		
		quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
		
			DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getId(), magasinPF.getId(),listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getId() , listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getId());

if(detailprixarticleTmp!=null)
{
	
	montantGratuite=montantGratuite.add(detailprixarticleTmp.getPrix().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
	
	
	
}	
		
	}

	
	
	
	
}else
{
	
	
	montantVente=montantVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
	quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());

}


System.out.println(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getLiblle() +" : " +listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle() +" : "+listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());

}


}
if(!montantVente.equals(BigDecimal.ZERO) && !quantiteTotalVente.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteVente (quantiteTotalVente);		    			
etatstockmp.setPrixVente (montantVente.divide(quantiteTotalVente,6,RoundingMode.HALF_UP));
etatstockmp.setMontantVente (etatstockmp.getQuantiteVente ().multiply(etatstockmp.getPrixVente ()));
listEtatStockMP.set(j, etatstockmp);

}

if(!quantiteTotalGratuite.equals(BigDecimal.ZERO))
{
	etatstockmp.setQuantiteGratuite(quantiteTotalGratuite);	
	
	etatstockmp.setPrixGratuite(montantGratuite.divide(quantiteTotalGratuite,6,RoundingMode.HALF_UP));

	
	etatstockmp.setMontantGratuite(montantGratuite);
	listEtatStockMP.set(j, etatstockmp);
	
	System.out.println(etatstockmp.getCodearticle() + " : "+etatstockmp.getFamille()+ " : "+etatstockmp.getSousfamille()+ " :  Quantite :"+quantiteTotalGratuite +"  : Prix :"+montantGratuite.divide(quantiteTotalGratuite,6,RoundingMode.HALF_UP) +" : Montant : "+montantGratuite);
	
}

}

///////////////////////////////////////////////////////////////////////////////////////// Les Avoirs de sous famille 	 ////////////////////////////////////////////////////////////////////



requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_AVOIR, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantAvoir=new BigDecimal(0);
quantiteTotalAvoir=new BigDecimal(0);


EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{

	if(!listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle().contains("_PFO"))
	{
		montantAvoir=montantAvoir.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
		quantiteTotalAvoir=quantiteTotalAvoir.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());

	}


}


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());




}
if(!montantAvoir.equals(BigDecimal.ZERO) && !quantiteTotalAvoir.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAvoir (quantiteTotalAvoir);		    			
etatstockmp.setPrixAvoir (montantAvoir.divide(quantiteTotalAvoir,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAvoir (etatstockmp.getQuantiteAvoir ().multiply(etatstockmp.getPrixAvoir ()));
listEtatStockMP.set(j, etatstockmp);

}

}

					
					  /////////////////////////////////////////////////////////////////////////////Liste Des PF Par Sous Famille Dechet ////////////////////////////////////////////////////////////////////////////
					  
					  listDetailTransferStockPFBySousFamille=detailTransferProduitFiniDAO.
					  ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupBySousFamilleArticleDechet
					  (dateChooserdebut.getDate(), dateChooserfin.getDate(), magasinPF);
					  
					  
					  for(int i=0;i<listDetailTransferStockPFBySousFamille.size();i++) {
					  
					  
					  
					  DetailTransferProduitFini
					  detailTransferStockPF=listDetailTransferStockPFBySousFamille.get(i);
					  
					  
					  
					  if(detailTransferStockPF.getArticle().getCodeArticle().contains("_PFD") ) {
					  
					  EtatValeurStock etatValeurStock=new EtatValeurStock();
					  etatValeurStock.setCodearticle(Constantes.FAMILLE_DECHET
					  +" "+detailTransferStockPF.getSousFamille().getCode());;
					  etatValeurStock.setArticle(Constantes.FAMILLE_DECHET
					  +" "+detailTransferStockPF.getSousFamille().getLiblle());
					  etatValeurStock.setFamille(Constantes.FAMILLE_DECHET
					  +" "+detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle())
					  ; etatValeurStock.setSousfamille (Constantes.FAMILLE_DECHET
					  +" "+detailTransferStockPF.getSousFamille().getLiblle());
					  etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
					  etatValeurStock.setPrixInitial(BigDecimal.ZERO);
					  etatValeurStock.setMontantInitial(BigDecimal.ZERO);
					  etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
					  etatValeurStock.setPrixAchat(BigDecimal.ZERO);
					  etatValeurStock.setMontantAchat (BigDecimal.ZERO);
					  etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
					  etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
					  etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
					  etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
					  etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
					  etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
					  etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
					  etatValeurStock.setPrixVente(BigDecimal.ZERO);
					  etatValeurStock.setMontantVente(BigDecimal.ZERO);
					  etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
					  etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
					  etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
					  etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
					  etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
					  etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
					  etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
					  etatValeurStock.setPrixFinale(BigDecimal.ZERO);
					  etatValeurStock.setMontantFinale(BigDecimal.ZERO);
					  
					  listEtatStockMP.add(etatValeurStock);
					  
					  }
					  
					  
					  }
					  
					  
					  
					  
					/*
					 * /////////////////////////////////////////////////////////////////////////////
					 * //////////// Initial de sous famille Dechet
					 * ////////////////////////////////////////////////////////////////////
					 * 
					 * 
					 * requete=" and magasinDestination.id='"+magasinPF.getId()
					 * +"' and article.codeArticle like'%"+DPF+"%'";
					 * listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.
					 * ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(
					 * dateChooserdebut.getDate(), dateChooserfin.getDate(),
					 * Constantes.ETAT_TRANSFER_STOCK_INITIAL, requete);
					 * 
					 * 
					 * for(int j=0;j<listEtatStockMP.size();j++) { montantInitial=new BigDecimal(0);
					 * quantiteTotalInitial=new BigDecimal(0);
					 * 
					 * EtatValeurStock etatstockmp=listEtatStockMP.get(j);
					 * 
					 * for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++) {
					 * 
					 * if(etatstockmp.getCodearticle().equals(Constantes.FAMILLE_DECHET
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getCode()) && etatstockmp.getFamille().equals(Constantes.FAMILLE_DECHET
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getFamileArticlePF().getLiblle()) &&
					 * etatstockmp.getSousfamille().equals(Constantes.FAMILLE_DECHET
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getLiblle())) {
					 * 
					 * 
					 * montantInitial=montantInitial.add(listDetailTransferStockPFInitialSousFamille
					 * .get(k).getPrixUnitaire().multiply(
					 * listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
					 * quantiteTotalInitial=quantiteTotalInitial.add(
					 * listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
					 * 
					 * 
					 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
					 * getMatierePremier().getNom() +
					 * " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
					 * 
					 * }
					 * 
					 * 
					 * } if(!montantInitial.equals(BigDecimal.ZERO) &&
					 * !quantiteTotalInitial.equals(BigDecimal.ZERO)) {
					 * 
					 * etatstockmp.setQuantiteInitial(quantiteTotalInitial);
					 * etatstockmp.setPrixInitial(montantInitial.divide(quantiteTotalInitial,6,
					 * RoundingMode.HALF_UP));
					 * etatstockmp.setMontantInitial(etatstockmp.getQuantiteInitial().multiply(
					 * etatstockmp.getPrixInitial())); listEtatStockMP.set(j, etatstockmp);
					 * 
					 * }
					 * 
					 * }
					 * 
					 * 
					 * /////////////////////////////////////////////////////////////////////////////
					 * //////////// Achat de sous famille Dechet
					 * ////////////////////////////////////////////////////////////////////
					 * 
					 * requete=" and magasinDestination.id='"+magasinPF.getId()
					 * +"' and article.codeArticle like'%"+DPF+"%'";
					 * listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.
					 * ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(
					 * dateChooserdebut.getDate(), dateChooserfin.getDate(),
					 * Constantes.ETAT_TRANSFER_STOCK_ACHAT, requete);
					 * 
					 * 
					 * for(int j=0;j<listEtatStockMP.size();j++) { montantachat=new BigDecimal(0);
					 * quantiteTotalachat=new BigDecimal(0);
					 * 
					 * EtatValeurStock etatstockmp=listEtatStockMP.get(j);
					 * 
					 * for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++) {
					 * 
					 * if(etatstockmp.getCodearticle().equals(Constantes.FAMILLE_DECHET
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getCode()) && etatstockmp.getFamille().equals(Constantes.FAMILLE_DECHET
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getFamileArticlePF().getLiblle()) &&
					 * etatstockmp.getSousfamille().equals(Constantes.FAMILLE_DECHET
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getLiblle())) {
					 * 
					 * 
					 * montantachat=montantachat.add(listDetailTransferStockPFInitialSousFamille.get
					 * (k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.
					 * get(k).getQuantite())); quantiteTotalachat=quantiteTotalachat.add(
					 * listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
					 * 
					 * 
					 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
					 * getMatierePremier().getNom()
					 * +" : "+listDetailTransferStockMPAchat.get(k).getQuantite());
					 * 
					 * }
					 * 
					 * 
					 * } if(!montantachat.equals(BigDecimal.ZERO) &&
					 * !quantiteTotalachat.equals(BigDecimal.ZERO)) {
					 * 
					 * etatstockmp.setQuantiteAchat(quantiteTotalachat);
					 * etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,
					 * RoundingMode.HALF_UP));
					 * etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(
					 * etatstockmp.getPrixAchat())); listEtatStockMP.set(j, etatstockmp);
					 * 
					 * }
					 * 
					 * }
					 */					  
					  
					  /////////////////////////////////////////////////////////////////////////////
					  //////////// Production Entrer de sous famille Dechet
					  ////////////////////////////////////////////////////////////////////
					  
					  
					  
					  requete=" and magasinDestination.id='"+magasinPF.getId()
					  +"' and article.codeArticle like'%"+DPF+"%'";
					  listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.
					  ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(
					  dateChooserdebut.getDate(), dateChooserfin.getDate(),
					  Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP, requete);
					  
					  
					  for(int j=0;j<listEtatStockMP.size();j++) { montantProductionEnter=new
					  BigDecimal(0); quantiteTotalProductionEnter=new BigDecimal(0);
					  
					  EtatValeurStock etatstockmp=listEtatStockMP.get(j);
					  
					  for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++) {
					  
					  if(etatstockmp.getCodearticle().equals(Constantes.FAMILLE_DECHET
					  +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					  getCode()) && etatstockmp.getFamille().equals(Constantes.FAMILLE_DECHET
					  +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					  getFamileArticlePF().getLiblle()) &&
					  etatstockmp.getSousfamille().equals(Constantes.FAMILLE_DECHET
					  +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					  getLiblle())) {
					  
					  
					  montantProductionEnter=montantProductionEnter.add(
					  listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply
					  (listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
					  quantiteTotalProductionEnter=quantiteTotalProductionEnter.add(
					  listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
					  
					  
					  //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() +" : "+listDetailTransferStockMPAchat.get(k).getQuantite());
					  
					  }
					  
					  
					  } if(!montantProductionEnter.equals(BigDecimal.ZERO) &&
					  !quantiteTotalProductionEnter.equals(BigDecimal.ZERO)) {
					  
					  etatstockmp.setQuantiteProductionEntrer (quantiteTotalProductionEnter);
					  etatstockmp.setPrixProductionEntrer(montantProductionEnter.divide(
					  quantiteTotalProductionEnter,6,RoundingMode.HALF_UP));
					  etatstockmp.setMontantProductionEntrer(etatstockmp.
					  getQuantiteProductionEntrer().multiply(etatstockmp.getPrixProductionEntrer())
					  ); listEtatStockMP.set(j, etatstockmp);
					  
					  }
					  
					  }
					  
					  
					/*
					 * /////////////////////////////////////////////////////////////////////////////
					 * //////////// Les ventes de sous famille Dechet
					 * ////////////////////////////////////////////////////////////////////
					 * 
					 * 
					 * 
					 * requete=" and magasinDestination.id='"+magasinPF.getId()
					 * +"' and article.codeArticle like'%"+DPF+"%'";
					 * listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.
					 * ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(
					 * dateChooserdebut.getDate(), dateChooserfin.getDate(),
					 * Constantes.ETAT_TRANSFER_STOCK_VENTE, requete);
					 * 
					 * 
					 * for(int j=0;j<listEtatStockMP.size();j++) { montantVente=new BigDecimal(0);
					 * quantiteTotalVente=new BigDecimal(0); quantiteTotalGratuite=new
					 * BigDecimal(0);
					 * 
					 * EtatValeurStock etatstockmp=listEtatStockMP.get(j);
					 * 
					 * for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++) {
					 * 
					 * if(etatstockmp.getCodearticle().equals(Constantes.FAMILLE_DECHET
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getCode()) && etatstockmp.getFamille().equals(Constantes.FAMILLE_DECHET
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getFamileArticlePF().getLiblle()) &&
					 * etatstockmp.getSousfamille().equals(Constantes.FAMILLE_DECHET
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getLiblle())) {
					 * 
					 * if(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().
					 * compareTo(BigDecimal.ZERO)==0) {
					 * 
					 * quantiteTotalGratuite=quantiteTotalGratuite.add(
					 * listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
					 * 
					 * }else {
					 * montantVente=montantVente.add(listDetailTransferStockPFInitialSousFamille.get
					 * (k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.
					 * get(k).getQuantite())); quantiteTotalVente=quantiteTotalVente.add(
					 * listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
					 * 
					 * }
					 * 
					 * 
					 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
					 * getMatierePremier().getNom() +
					 * " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
					 * 
					 * }
					 * 
					 * 
					 * } if(!montantVente.equals(BigDecimal.ZERO) &&
					 * !quantiteTotalVente.equals(BigDecimal.ZERO)) {
					 * 
					 * etatstockmp.setQuantiteVente (quantiteTotalVente); etatstockmp.setPrixVente
					 * (montantVente.divide(quantiteTotalVente,6,RoundingMode.HALF_UP));
					 * etatstockmp.setMontantVente (etatstockmp.getQuantiteVente
					 * ().multiply(etatstockmp.getPrixVente ())); listEtatStockMP.set(j,
					 * etatstockmp);
					 * 
					 * }
					 * 
					 * if(!quantiteTotalGratuite.equals(BigDecimal.ZERO)) {
					 * etatstockmp.setQuantiteGratuite(quantiteTotalGratuite);
					 * 
					 * listEtatStockMP.set(j, etatstockmp);
					 * 
					 * }
					 * 
					 * }
					 * 
					 * /////////////////////////////////////////////////////////////////////////////
					 * //////////// Les Avoirs de sous famille Dechet
					 * ////////////////////////////////////////////////////////////////////
					 * 
					 * 
					 * 
					 * requete=" and magasinDestination.id='"+magasinPF.getId()
					 * +"'  and article.codeArticle like'%"+DPF+"%'";
					 * listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.
					 * ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(
					 * dateChooserdebut.getDate(), dateChooserfin.getDate(),
					 * Constantes.ETAT_TRANSFER_STOCK_AVOIR, requete);
					 * 
					 * 
					 * for(int j=0;j<listEtatStockMP.size();j++) { montantAvoir=new BigDecimal(0);
					 * quantiteTotalAvoir=new BigDecimal(0);
					 * 
					 * 
					 * EtatValeurStock etatstockmp=listEtatStockMP.get(j);
					 * 
					 * for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++) {
					 * 
					 * if(etatstockmp.getCodearticle().equals(Constantes.FAMILLE_DECHET
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getCode()) && etatstockmp.getFamille().equals(Constantes.FAMILLE_DECHET
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getFamileArticlePF().getLiblle()) &&
					 * etatstockmp.getSousfamille().equals(Constantes.FAMILLE_DECHET
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getLiblle())) {
					 * 
					 * 
					 * montantAvoir=montantAvoir.add(listDetailTransferStockPFInitialSousFamille.get
					 * (k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.
					 * get(k).getQuantite())); quantiteTotalAvoir=quantiteTotalAvoir.add(
					 * listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
					 * 
					 * }
					 * 
					 * 
					 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
					 * getMatierePremier().getNom() +
					 * " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
					 * 
					 * 
					 * 
					 * 
					 * } if(!montantAvoir.equals(BigDecimal.ZERO) &&
					 * !quantiteTotalAvoir.equals(BigDecimal.ZERO)) {
					 * 
					 * etatstockmp.setQuantiteAvoir (quantiteTotalAvoir); etatstockmp.setPrixAvoir
					 * (montantAvoir.divide(quantiteTotalAvoir,6,RoundingMode.HALF_UP));
					 * etatstockmp.setMontantAvoir (etatstockmp.getQuantiteAvoir
					 * ().multiply(etatstockmp.getPrixAvoir ())); listEtatStockMP.set(j,
					 * etatstockmp);
					 * 
					 * }
					 * 
					 * }
					 * 
					 */
//////////////////////////////////////////////////////////////////////////////Liste Des PF Par Sous Famille not in (The ,EL NASS,ADMINISTRATION, Dechet , Offre,emballage)  ////////////////////////////////////////////////////////////////////////////

listDetailTransferStockPFBySousFamille=detailTransferProduitFiniDAO.ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupByArticleBySousFamille(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasinPF);




for(int i=0;i<listDetailTransferStockPFBySousFamille.size();i++)
{



DetailTransferProduitFini detailTransferStockPF=listDetailTransferStockPFBySousFamille.get(i);	



if( !detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_DECHET) && !detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_CADEAU) && !detailTransferStockPF.getSousFamille().getFamileArticlePF().getCode().contentEquals(Constantes.CODE_CHAARA) && !detailTransferStockPF.getSousFamille().getFamileArticlePF().getCode().contentEquals(Constantes.CODE_MKARKEB) && !detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_EMBALLAGE) && !detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().contentEquals(Constantes.LIBELLE_FAMILLE_ELNASS)&& !detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().contentEquals(Constantes.LIBELLE_FAMILLE_ADMINISTRATION))
{
EtatValeurStock etatValeurStock=new EtatValeurStock();	
etatValeurStock.setCodearticle(detailTransferStockPF.getArticle().getCodeArticle());	
etatValeurStock.setArticle(detailTransferStockPF.getArticle().getLiblle());
etatValeurStock.setFamille(detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle());
etatValeurStock.setSousfamille (detailTransferStockPF.getSousFamille().getLiblle());
etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
etatValeurStock.setPrixInitial(BigDecimal.ZERO);	
etatValeurStock.setMontantInitial(BigDecimal.ZERO);
etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
etatValeurStock.setPrixAchat(BigDecimal.ZERO);
etatValeurStock.setMontantAchat (BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
etatValeurStock.setPrixVente(BigDecimal.ZERO);
etatValeurStock.setMontantVente(BigDecimal.ZERO);
etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
etatValeurStock.setPrixFinale(BigDecimal.ZERO);
etatValeurStock.setMontantFinale(BigDecimal.ZERO);

listEtatStockMP.add(etatValeurStock);




}


}


///////////////////////////////////////////////////////////////////////////////////////// Initial de sous famille  not in (The ,EL NASS,ADMINISTRATION, Dechet , Offre,emballage)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code not in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"') and sousFamille.famileArticlePF.liblle not in ('"+Constantes.FAMILLE_DECHET+"', '"+Constantes.FAMILLE_CADEAU+"','"+Constantes.FAMILLE_EMBALLAGE+"' ,'"+Constantes.LIBELLE_FAMILLE_ELNASS+"','"+Constantes.LIBELLE_FAMILLE_ADMINISTRATION+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_INITIAL, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantInitial=new BigDecimal(0);
quantiteTotalInitial=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantInitial=montantInitial.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalInitial=quantiteTotalInitial.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteInitial(quantiteTotalInitial);		    			
etatstockmp.setPrixInitial(montantInitial.divide(quantiteTotalInitial,6,RoundingMode.HALF_UP));
etatstockmp.setMontantInitial(etatstockmp.getQuantiteInitial().multiply(etatstockmp.getPrixInitial()));
listEtatStockMP.set(j, etatstockmp);

}

}





///////////////////////////////////////////////////////////////////////////////////////// Achat de sous famille  not in (The ,EL NASS,ADMINISTRATION Dechet , Offre,emballage)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code not in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"') and sousFamille.famileArticlePF.liblle not in ('"+Constantes.FAMILLE_DECHET+"', '"+Constantes.FAMILLE_CADEAU+"','"+Constantes.FAMILLE_EMBALLAGE+"' ,'"+Constantes.LIBELLE_FAMILLE_ELNASS+"','"+Constantes.LIBELLE_FAMILLE_ADMINISTRATION+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_ACHAT, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantachat=new BigDecimal(0);
quantiteTotalachat=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantachat=montantachat.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAchat(quantiteTotalachat);		    			
etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
listEtatStockMP.set(j, etatstockmp);

}

}




///////////////////////////////////////////////////////////////////////////////////////// Vente de sous famille  not in (The ,EL NASS,ADMINISTRATION, Dechet , Offre,emballage)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code not in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"') and sousFamille.famileArticlePF.liblle not in ('"+Constantes.FAMILLE_DECHET+"', '"+Constantes.FAMILLE_CADEAU+"','"+Constantes.FAMILLE_EMBALLAGE+"' ,'"+Constantes.LIBELLE_FAMILLE_ELNASS+"','"+Constantes.LIBELLE_FAMILLE_ADMINISTRATION+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_VENTE, requete);

BigDecimal prixmoyenvente=new BigDecimal(0);
for(int j=0;j<listEtatStockMP.size();j++)
{
montantVente=new BigDecimal(0);
quantiteTotalVente=new BigDecimal(0);
quantiteTotalGratuite=new BigDecimal(0);
montantGratuite=new BigDecimal(0);
EtatValeurStock etatstockmp=listEtatStockMP.get(j);	
prixmoyenvente=new BigDecimal(0);
for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{

if(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
{
	quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite())	;
		DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getId(), magasinPF.getId(),listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getId() , listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getId());

if(detailprixarticleTmp!=null)
{

montantGratuite=montantGratuite.add(detailprixarticleTmp.getPrix().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
}
	
}else
{
	//montantVente=montantVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
	//quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());

	montantVente=montantVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
	prixmoyenvente=((prixmoyenvente.multiply(quantiteTotalVente)).add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()))).divide(quantiteTotalVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()),6, RoundingMode.DOWN) ;

	quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
	
	

}

//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!quantiteTotalVente.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteVente(quantiteTotalVente);		    			
etatstockmp.setPrixVente(prixmoyenvente);
etatstockmp.setMontantVente(quantiteTotalVente.multiply(prixmoyenvente));
listEtatStockMP.set(j, etatstockmp);

}

if( !quantiteTotalGratuite.equals(BigDecimal.ZERO))
{
	etatstockmp.setQuantiteGratuite(quantiteTotalGratuite);	
	etatstockmp.setPrixGratuite (montantGratuite.divide(quantiteTotalGratuite,6,RoundingMode.HALF_UP));
	etatstockmp.setMontantGratuite (etatstockmp.getQuantiteGratuite().multiply(etatstockmp.getPrixGratuite()));

	listEtatStockMP.set(j, etatstockmp);
	
}




}



///////////////////////////////////////////////////////////////////////////////////////// Les Avoirs de sous famille  not in (The ,EL NASS,ADMINISTRATION, Dechet , Offre,emballage)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code not in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"') and sousFamille.famileArticlePF.liblle not in ('"+Constantes.FAMILLE_DECHET+"', '"+Constantes.FAMILLE_CADEAU+"','"+Constantes.FAMILLE_EMBALLAGE+"' ,'"+Constantes.LIBELLE_FAMILLE_ELNASS+"','"+Constantes.LIBELLE_FAMILLE_ADMINISTRATION+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_AVOIR_R, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantAvoir=new BigDecimal(0);
quantiteTotalAvoir=new BigDecimal(0);


EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantAvoir=montantAvoir.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalAvoir=quantiteTotalAvoir.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());




//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantAvoir.equals(BigDecimal.ZERO) && !quantiteTotalAvoir.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAvoir(quantiteTotalAvoir);		    			
etatstockmp.setPrixAvoir(montantAvoir.divide(quantiteTotalAvoir,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAvoir(etatstockmp.getQuantiteAvoir().multiply(etatstockmp.getPrixAvoir()));
listEtatStockMP.set(j, etatstockmp);

}






}



//////////////////////////////////////////////////////////////////////////////Liste Des PF Par Sous Famille  in (EL NASS)  ////////////////////////////////////////////////////////////////////////////

listDetailTransferStockPFBySousFamille=detailTransferProduitFiniDAO.ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupBySousFamille(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasinPF);




for(int i=0;i<listDetailTransferStockPFBySousFamille.size();i++)
{



DetailTransferProduitFini detailTransferStockPF=listDetailTransferStockPFBySousFamille.get(i);	



if(  detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().contentEquals(Constantes.LIBELLE_FAMILLE_ELNASS))
{
EtatValeurStock etatValeurStock=new EtatValeurStock();	
etatValeurStock.setCodearticle(detailTransferStockPF.getSousFamille().getCode());	
etatValeurStock.setArticle(detailTransferStockPF.getSousFamille().getLiblle());
etatValeurStock.setFamille(detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle());
etatValeurStock.setSousfamille (detailTransferStockPF.getSousFamille().getLiblle());
etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
etatValeurStock.setPrixInitial(BigDecimal.ZERO);	
etatValeurStock.setMontantInitial(BigDecimal.ZERO);
etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
etatValeurStock.setPrixAchat(BigDecimal.ZERO);
etatValeurStock.setMontantAchat (BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
etatValeurStock.setPrixVente(BigDecimal.ZERO);
etatValeurStock.setMontantVente(BigDecimal.ZERO);
etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
etatValeurStock.setPrixFinale(BigDecimal.ZERO);
etatValeurStock.setMontantFinale(BigDecimal.ZERO);

listEtatStockMP.add(etatValeurStock);




}


}


///////////////////////////////////////////////////////////////////////////////////////// Initial de sous famille  in (EL NASS)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.liblle  in ('"+Constantes.LIBELLE_FAMILLE_ELNASS+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_INITIAL, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantInitial=new BigDecimal(0);
quantiteTotalInitial=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantInitial=montantInitial.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalInitial=quantiteTotalInitial.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteInitial(quantiteTotalInitial);		    			
etatstockmp.setPrixInitial(montantInitial.divide(quantiteTotalInitial,6,RoundingMode.HALF_UP));
etatstockmp.setMontantInitial(etatstockmp.getQuantiteInitial().multiply(etatstockmp.getPrixInitial()));
listEtatStockMP.set(j, etatstockmp);

}

}





///////////////////////////////////////////////////////////////////////////////////////// Achat de sous famille  in (EL NASS)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"'  and sousFamille.famileArticlePF.liblle  in ('"+Constantes.LIBELLE_FAMILLE_ELNASS+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_ACHAT, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantachat=new BigDecimal(0);
quantiteTotalachat=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantachat=montantachat.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAchat(quantiteTotalachat);		    			
etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
listEtatStockMP.set(j, etatstockmp);

}

}




///////////////////////////////////////////////////////////////////////////////////////// Vente de sous famille   in (EL NASS)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"'  and sousFamille.famileArticlePF.liblle  in ('"+Constantes.LIBELLE_FAMILLE_ELNASS+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_VENTE, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantVente=new BigDecimal(0);
quantiteTotalVente=new BigDecimal(0);
quantiteTotalGratuite=new BigDecimal(0);
montantGratuite=new BigDecimal(0);
EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{

if(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
{
quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite())	;
DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getId(), magasinPF.getId(),listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getId() , listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getId());

if(detailprixarticleTmp!=null)
{

montantGratuite=montantGratuite.add(detailprixarticleTmp.getPrix().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
}

}else
{
montantVente=montantVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());


}

//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantVente.equals(BigDecimal.ZERO) && !quantiteTotalVente.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteVente(quantiteTotalVente);		    			
etatstockmp.setPrixVente(montantVente.divide(quantiteTotalVente,6,RoundingMode.HALF_UP));
etatstockmp.setMontantVente(etatstockmp.getQuantiteVente().multiply(etatstockmp.getPrixVente()));
listEtatStockMP.set(j, etatstockmp);

}

if( !quantiteTotalGratuite.equals(BigDecimal.ZERO))
{
etatstockmp.setQuantiteGratuite(quantiteTotalGratuite);	
etatstockmp.setPrixGratuite (montantGratuite.divide(quantiteTotalGratuite,6,RoundingMode.HALF_UP));
etatstockmp.setMontantGratuite (etatstockmp.getQuantiteGratuite().multiply(etatstockmp.getPrixGratuite()));

listEtatStockMP.set(j, etatstockmp);

}




}



///////////////////////////////////////////////////////////////////////////////////////// Les Avoirs de sous famille   in (EL NASS)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"'  and sousFamille.famileArticlePF.liblle  in ('"+Constantes.LIBELLE_FAMILLE_ELNASS+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_AVOIR, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantAvoir=new BigDecimal(0);
quantiteTotalAvoir=new BigDecimal(0);


EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantAvoir=montantAvoir.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalAvoir=quantiteTotalAvoir.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());




//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantAvoir.equals(BigDecimal.ZERO) && !quantiteTotalAvoir.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAvoir(quantiteTotalAvoir);		    			
etatstockmp.setPrixAvoir(montantAvoir.divide(quantiteTotalAvoir,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAvoir(etatstockmp.getQuantiteAvoir().multiply(etatstockmp.getPrixAvoir()));
listEtatStockMP.set(j, etatstockmp);

}






}



//////////////////////////////////////////////////////////////////////////////Liste Des PF Par Sous Famille  in (ADMINISTRATION)  ////////////////////////////////////////////////////////////////////////////

listDetailTransferStockPFBySousFamille=detailTransferProduitFiniDAO.ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupBySousFamille(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasinPF);




for(int i=0;i<listDetailTransferStockPFBySousFamille.size();i++)
{



DetailTransferProduitFini detailTransferStockPF=listDetailTransferStockPFBySousFamille.get(i);	



if(  detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().contentEquals(Constantes.LIBELLE_FAMILLE_ADMINISTRATION))
{
EtatValeurStock etatValeurStock=new EtatValeurStock();	
etatValeurStock.setCodearticle(detailTransferStockPF.getSousFamille().getCode());	
etatValeurStock.setArticle(detailTransferStockPF.getSousFamille().getLiblle());
etatValeurStock.setFamille(detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle());
etatValeurStock.setSousfamille (detailTransferStockPF.getSousFamille().getLiblle());
etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
etatValeurStock.setPrixInitial(BigDecimal.ZERO);	
etatValeurStock.setMontantInitial(BigDecimal.ZERO);
etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
etatValeurStock.setPrixAchat(BigDecimal.ZERO);
etatValeurStock.setMontantAchat (BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
etatValeurStock.setPrixVente(BigDecimal.ZERO);
etatValeurStock.setMontantVente(BigDecimal.ZERO);
etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
etatValeurStock.setPrixFinale(BigDecimal.ZERO);
etatValeurStock.setMontantFinale(BigDecimal.ZERO);

listEtatStockMP.add(etatValeurStock);




}


}


///////////////////////////////////////////////////////////////////////////////////////// Initial de sous famille  in (ADMINISTRATION)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.liblle  in ('"+Constantes.LIBELLE_FAMILLE_ADMINISTRATION+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_INITIAL, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantInitial=new BigDecimal(0);
quantiteTotalInitial=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantInitial=montantInitial.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalInitial=quantiteTotalInitial.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteInitial(quantiteTotalInitial);		    			
etatstockmp.setPrixInitial(montantInitial.divide(quantiteTotalInitial,6,RoundingMode.HALF_UP));
etatstockmp.setMontantInitial(etatstockmp.getQuantiteInitial().multiply(etatstockmp.getPrixInitial()));
listEtatStockMP.set(j, etatstockmp);

}

}





///////////////////////////////////////////////////////////////////////////////////////// Achat de sous famille  in (ADMINISTRATION)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"'  and sousFamille.famileArticlePF.liblle  in ('"+Constantes.LIBELLE_FAMILLE_ADMINISTRATION+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_ACHAT, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantachat=new BigDecimal(0);
quantiteTotalachat=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantachat=montantachat.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAchat(quantiteTotalachat);		    			
etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
listEtatStockMP.set(j, etatstockmp);

}

}




///////////////////////////////////////////////////////////////////////////////////////// Vente de sous famille   in (ADMINISTRATION)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"'  and sousFamille.famileArticlePF.liblle  in ('"+Constantes.LIBELLE_FAMILLE_ADMINISTRATION+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_VENTE, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantVente=new BigDecimal(0);
quantiteTotalVente=new BigDecimal(0);
quantiteTotalGratuite=new BigDecimal(0);
montantGratuite=new BigDecimal(0);
EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{

if(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
{
quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite())	;
DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getId(), magasinPF.getId(),listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getId() , listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getId());

if(detailprixarticleTmp!=null)
{

montantGratuite=montantGratuite.add(detailprixarticleTmp.getPrix().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
}

}else
{
montantVente=montantVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());


}

//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantVente.equals(BigDecimal.ZERO) && !quantiteTotalVente.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteVente(quantiteTotalVente);		    			
etatstockmp.setPrixVente(montantVente.divide(quantiteTotalVente,6,RoundingMode.HALF_UP));
etatstockmp.setMontantVente(etatstockmp.getQuantiteVente().multiply(etatstockmp.getPrixVente()));
listEtatStockMP.set(j, etatstockmp);

}

if( !quantiteTotalGratuite.equals(BigDecimal.ZERO))
{
etatstockmp.setQuantiteGratuite(quantiteTotalGratuite);	
etatstockmp.setPrixGratuite (montantGratuite.divide(quantiteTotalGratuite,6,RoundingMode.HALF_UP));
etatstockmp.setMontantGratuite (etatstockmp.getQuantiteGratuite().multiply(etatstockmp.getPrixGratuite()));

listEtatStockMP.set(j, etatstockmp);

}




}



///////////////////////////////////////////////////////////////////////////////////////// Les Avoirs de sous famille   in (ADMINISTRATION)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"'  and sousFamille.famileArticlePF.liblle  in ('"+Constantes.LIBELLE_FAMILLE_ADMINISTRATION+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_AVOIR, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantAvoir=new BigDecimal(0);
quantiteTotalAvoir=new BigDecimal(0);


EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantAvoir=montantAvoir.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalAvoir=quantiteTotalAvoir.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());




//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantAvoir.equals(BigDecimal.ZERO) && !quantiteTotalAvoir.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAvoir(quantiteTotalAvoir);		    			
etatstockmp.setPrixAvoir(montantAvoir.divide(quantiteTotalAvoir,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAvoir(etatstockmp.getQuantiteAvoir().multiply(etatstockmp.getPrixAvoir()));
listEtatStockMP.set(j, etatstockmp);

}






}




					
					  ///////////////////////////////////////////////////////////////////////////// / Liste Des PF Par Sous Famille Offre  ////////////////////////////////////////////////////////////////////////////
					  
					  listDetailTransferStockPFBySousFamille=detailTransferProduitFiniDAO.ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupBySousFamilleArticleOffre(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasinPF);
					  
					  
					  for(int i=0;i<listDetailTransferStockPFBySousFamille.size();i++) {
					  
					  
					  
					  DetailTransferProduitFini
					  detailTransferStockPF=listDetailTransferStockPFBySousFamille.get(i);
					  
					  
					  
					  if(detailTransferStockPF.getArticle().getCodeArticle().contains("_PFO") &&
					  !detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().
					  contentEquals(Constantes.FAMILLE_CADEAU)) {
					  
					  EtatValeurStock etatValeurStock=new EtatValeurStock();
					  etatValeurStock.setCodearticle(Constantes.MP_STOCK_OFFRE
					  +" "+detailTransferStockPF.getSousFamille().getCode());;
					  etatValeurStock.setArticle(Constantes.MP_STOCK_OFFRE
					  +" "+detailTransferStockPF.getSousFamille().getLiblle());
					  etatValeurStock.setFamille(Constantes.MP_STOCK_OFFRE
					  +" "+detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle())
					  ; etatValeurStock.setSousfamille (Constantes.MP_STOCK_OFFRE
					  +" "+detailTransferStockPF.getSousFamille().getLiblle());
					  etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
					  etatValeurStock.setPrixInitial(BigDecimal.ZERO);
					  etatValeurStock.setMontantInitial(BigDecimal.ZERO);
					  etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
					  etatValeurStock.setPrixAchat(BigDecimal.ZERO);
					  etatValeurStock.setMontantAchat (BigDecimal.ZERO);
					  etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
					  etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
					  etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
					  etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
					  etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
					  etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
					  etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
					  etatValeurStock.setPrixVente(BigDecimal.ZERO);
					  etatValeurStock.setMontantVente(BigDecimal.ZERO);
					  etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
					  etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
					  etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
					  etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
					  etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
					  etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
					  etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
					  etatValeurStock.setPrixFinale(BigDecimal.ZERO);
					  etatValeurStock.setMontantFinale(BigDecimal.ZERO);
					  
					  listEtatStockMP.add(etatValeurStock);
					  
					  }
					  
					  
					  }
					 

					
					  
					
					  /////////////////////////////////////////////////////////////////////////////
					  //////////// Initial de sous famille Offre
					  ////////////////////////////////////////////////////////////////////
					  
					  
					  requete=" and magasinDestination.id='"+magasinPF.getId()
					  +"' and article.codeArticle like'%"+OPF+"%'";
					  listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.
					  ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(
					  dateChooserdebut.getDate(), dateChooserfin.getDate(),
					  Constantes.ETAT_TRANSFER_STOCK_INITIAL, requete);
					  
					  
					  for(int j=0;j<listEtatStockMP.size();j++) { montantInitial=new BigDecimal(0);
					  quantiteTotalInitial=new BigDecimal(0);
					  
					  EtatValeurStock etatstockmp=listEtatStockMP.get(j);
					  
					  for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++) {
					  
					  if(etatstockmp.getCodearticle().equals(Constantes.MP_STOCK_OFFRE
					  +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					  getCode()) &&
					  !listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					  getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_CADEAU) &&
					  etatstockmp.getFamille().equals(Constantes.MP_STOCK_OFFRE
					  +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					  getFamileArticlePF().getLiblle()) &&
					  etatstockmp.getSousfamille().equals(Constantes.MP_STOCK_OFFRE
					  +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					  getLiblle())) {
					  
					  
					  montantInitial=montantInitial.add(listDetailTransferStockPFInitialSousFamille
					  .get(k).getPrixUnitaire().multiply(
					  listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
					  quantiteTotalInitial=quantiteTotalInitial.add(
					  listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
					  
					  
					  //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j). getMatierePremier().getNom() +" : "+listDetailTransferStockMPAchat.get(k).getQuantite());
					  
					  }
					  
					  
					  } if(!montantInitial.equals(BigDecimal.ZERO) &&
					  !quantiteTotalInitial.equals(BigDecimal.ZERO)) {
					  
					  etatstockmp.setQuantiteInitial(quantiteTotalInitial);
					  etatstockmp.setPrixInitial(montantInitial.divide(quantiteTotalInitial,6,
					  RoundingMode.HALF_UP));
					  etatstockmp.setMontantInitial(etatstockmp.getQuantiteInitial().multiply(
					  etatstockmp.getPrixInitial())); listEtatStockMP.set(j, etatstockmp);
					  
					  }
					  
					  }
					 
					  
					  /////////////////////////////////////////////////////////////////////////////
					  //////////// Achat de sous famille Offre
					  ////////////////////////////////////////////////////////////////////
					  
					/*
					 * requete=" and magasinDestination.id='"+magasinPF.getId()
					 * +"' and article.codeArticle like'%"+OPF+"%'";
					 * listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.
					 * ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(
					 * dateChooserdebut.getDate(), dateChooserfin.getDate(),
					 * Constantes.ETAT_TRANSFER_STOCK_ACHAT, requete);
					 * 
					 * 
					 * for(int j=0;j<listEtatStockMP.size();j++) { montantachat=new BigDecimal(0);
					 * quantiteTotalachat=new BigDecimal(0);
					 * 
					 * EtatValeurStock etatstockmp=listEtatStockMP.get(j);
					 * 
					 * for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++) {
					 * 
					 * if(etatstockmp.getCodearticle().equals(Constantes.MP_STOCK_OFFRE
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getCode()) &&
					 * !listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_CADEAU) &&
					 * etatstockmp.getFamille().equals(Constantes.MP_STOCK_OFFRE
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getFamileArticlePF().getLiblle()) &&
					 * etatstockmp.getSousfamille().equals(Constantes.MP_STOCK_OFFRE
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getLiblle())) {
					 * 
					 * 
					 * montantachat=montantachat.add(listDetailTransferStockPFInitialSousFamille.get
					 * (k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.
					 * get(k).getQuantite())); quantiteTotalachat=quantiteTotalachat.add(
					 * listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
					 * 
					 * 
					 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
					 * getMatierePremier().getNom()
					 * +" : "+listDetailTransferStockMPAchat.get(k).getQuantite());
					 * 
					 * }
					 * 
					 * 
					 * } if(!montantachat.equals(BigDecimal.ZERO) &&
					 * !quantiteTotalachat.equals(BigDecimal.ZERO)) {
					 * 
					 * etatstockmp.setQuantiteAchat(quantiteTotalachat);
					 * etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,
					 * RoundingMode.HALF_UP));
					 * etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(
					 * etatstockmp.getPrixAchat())); listEtatStockMP.set(j, etatstockmp);
					 * 
					 * }
					 * 
					 * }
					 */
					  
					  /////////////////////////////////////////////////////////////////////////////
					  //////////// Production Entrer de sous famille Offre
					  ////////////////////////////////////////////////////////////////////
					  
					  
					  
					  requete=" and magasinDestination.id='"+magasinPF.getId()
					  +"' and article.codeArticle like'%"+OPF+"%'";
					  listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.
					  ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(
					  dateChooserdebut.getDate(), dateChooserfin.getDate(),
					  Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP, requete);
					  
					  
					  for(int j=0;j<listEtatStockMP.size();j++) { montantProductionEnter=new
					  BigDecimal(0); quantiteTotalProductionEnter=new BigDecimal(0);
					  
					  EtatValeurStock etatstockmp=listEtatStockMP.get(j);
					  
					  for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++) {
					  
					  if(etatstockmp.getCodearticle().equals(Constantes.MP_STOCK_OFFRE
					  +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					  getCode()) &&
					  !listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					  getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_CADEAU)&&
					  etatstockmp.getFamille().equals(Constantes.MP_STOCK_OFFRE
					  +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					  getFamileArticlePF().getLiblle()) &&
					  etatstockmp.getSousfamille().equals(Constantes.MP_STOCK_OFFRE
					  +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					  getLiblle())) {
					  
					  
					  montantProductionEnter=montantProductionEnter.add(
					  listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply
					  (listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
					  quantiteTotalProductionEnter=quantiteTotalProductionEnter.add(
					  listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
					  
					  
					  //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() +" : "+listDetailTransferStockMPAchat.get(k).getQuantite());
					  
					  }
					  
					  
					  } if(!montantProductionEnter.equals(BigDecimal.ZERO) &&
					  !quantiteTotalProductionEnter.equals(BigDecimal.ZERO)) {
					  
					  etatstockmp.setQuantiteProductionEntrer (quantiteTotalProductionEnter);
					  etatstockmp.setPrixProductionEntrer(montantProductionEnter.divide(
					  quantiteTotalProductionEnter,6,RoundingMode.HALF_UP));
					  etatstockmp.setMontantProductionEntrer(etatstockmp.
					  getQuantiteProductionEntrer().multiply(etatstockmp.getPrixProductionEntrer())
					  ); listEtatStockMP.set(j, etatstockmp);
					  
					  }
					  
					  }
					  
					  
					  /////////////////////////////////////////////////////////////////////////////
					  //////////// Les ventes de sous famille Offre
					  ////////////////////////////////////////////////////////////////////
					  
					  
					
					  requete=" and magasinDestination.id='"+magasinPF.getId()
					  +"' and article.codeArticle like'%"+OPF+"%'";
					  listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.
					  ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(
					  dateChooserdebut.getDate(), dateChooserfin.getDate(),
					  Constantes.ETAT_TRANSFER_STOCK_VENTE, requete);
					  
					  
					  for(int j=0;j<listEtatStockMP.size();j++) { montantVente=new BigDecimal(0);
					  quantiteTotalVente=new BigDecimal(0); 
					  quantiteTotalGratuite=new  BigDecimal(0);
					  
					  EtatValeurStock etatstockmp=listEtatStockMP.get(j);
					  
					  for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++) {
					  
					  if(etatstockmp.getCodearticle().equals(Constantes.MP_STOCK_OFFRE+" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille(). getCode()) &&
					  !listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_CADEAU) &&
					  etatstockmp.getFamille().equals(Constantes.MP_STOCK_OFFRE +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille(). getFamileArticlePF().getLiblle()) &&
					  etatstockmp.getSousfamille().equals(Constantes.MP_STOCK_OFFRE +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
					  {
					  
					  if(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().
					  compareTo(BigDecimal.ZERO)==0) {
					  
					  quantiteTotalGratuite=quantiteTotalGratuite.add(
					  listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
					  
					  }else {
					  montantVente=montantVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite())); quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
					  
					  }
					  
					  
					  //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() +" : "+listDetailTransferStockMPAchat.get(k).getQuantite());
					  
					  }
					  
					  
					  } if(!montantVente.equals(BigDecimal.ZERO) &&
					  !quantiteTotalVente.equals(BigDecimal.ZERO)) {
					  
					  etatstockmp.setQuantiteVente (quantiteTotalVente); etatstockmp.setPrixVente
					  (montantVente.divide(quantiteTotalVente,6,RoundingMode.HALF_UP));
					  etatstockmp.setMontantVente (etatstockmp.getQuantiteVente
					  ().multiply(etatstockmp.getPrixVente ())); listEtatStockMP.set(j,
					  etatstockmp);
					  
					  }
					  
					  if(!quantiteTotalGratuite.equals(BigDecimal.ZERO)) {
					  etatstockmp.setQuantiteGratuite(quantiteTotalGratuite);
					  
					  listEtatStockMP.set(j, etatstockmp);
					  
					  }
					  
					  }
					 
					  /////////////////////////////////////////////////////////////////////////////
					  //////////// Les Avoirs de sous famille Offre
					  ////////////////////////////////////////////////////////////////////
					  
					  
					  
					/*
					 * requete=" and magasinDestination.id='"+magasinPF.getId()
					 * +"'  and article.codeArticle like'%"+OPF+"%'";
					 * listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.
					 * ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(
					 * dateChooserdebut.getDate(), dateChooserfin.getDate(),
					 * Constantes.ETAT_TRANSFER_STOCK_AVOIR, requete);
					 * 
					 * 
					 * for(int j=0;j<listEtatStockMP.size();j++) { montantAvoir=new BigDecimal(0);
					 * quantiteTotalAvoir=new BigDecimal(0);
					 * 
					 * 
					 * EtatValeurStock etatstockmp=listEtatStockMP.get(j);
					 * 
					 * for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++) {
					 * 
					 * if(etatstockmp.getCodearticle().equals(Constantes.MP_STOCK_OFFRE
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getCode()) &&
					 * !listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_CADEAU) &&
					 * etatstockmp.getFamille().equals(Constantes.MP_STOCK_OFFRE
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getFamileArticlePF().getLiblle()) &&
					 * etatstockmp.getSousfamille().equals(Constantes.MP_STOCK_OFFRE
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getLiblle())) {
					 * 
					 * 
					 * montantAvoir=montantAvoir.add(listDetailTransferStockPFInitialSousFamille.get
					 * (k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.
					 * get(k).getQuantite())); quantiteTotalAvoir=quantiteTotalAvoir.add(
					 * listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
					 * 
					 * }
					 * 
					 * 
					 * //System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).
					 * getMatierePremier().getNom() +
					 * " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
					 * 
					 * 
					 * 
					 * 
					 * } if(!montantAvoir.equals(BigDecimal.ZERO) &&
					 * !quantiteTotalAvoir.equals(BigDecimal.ZERO)) {
					 * 
					 * etatstockmp.setQuantiteAvoir (quantiteTotalAvoir); etatstockmp.setPrixAvoir
					 * (montantAvoir.divide(quantiteTotalAvoir,6,RoundingMode.HALF_UP));
					 * etatstockmp.setMontantAvoir (etatstockmp.getQuantiteAvoir
					 * ().multiply(etatstockmp.getPrixAvoir ())); listEtatStockMP.set(j,
					 * etatstockmp);
					 * 
					 * }
					 * 
					 * }
					 * 
					 */
					 



//////////////////////////////////////////////////////////////////////////////Liste Des PF Par Sous Famille  Offre  ////////////////////////////////////////////////////////////////////////////

listDetailTransferStockPFBySousFamille=detailTransferProduitFiniDAO.ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupByArticleBySousFamille(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasinPF);




for(int i=0;i<listDetailTransferStockPFBySousFamille.size();i++)
{



DetailTransferProduitFini detailTransferStockPF=listDetailTransferStockPFBySousFamille.get(i);	



if(detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_CADEAU))
{
EtatValeurStock etatValeurStock=new EtatValeurStock();	
etatValeurStock.setCodearticle(detailTransferStockPF.getArticle().getCodeArticle());	
etatValeurStock.setArticle(detailTransferStockPF.getArticle().getLiblle());
etatValeurStock.setFamille(detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle());
etatValeurStock.setSousfamille (detailTransferStockPF.getSousFamille().getLiblle());
etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
etatValeurStock.setPrixInitial(BigDecimal.ZERO);	
etatValeurStock.setMontantInitial(BigDecimal.ZERO);
etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
etatValeurStock.setPrixAchat(BigDecimal.ZERO);
etatValeurStock.setMontantAchat (BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
etatValeurStock.setPrixVente(BigDecimal.ZERO);
etatValeurStock.setMontantVente(BigDecimal.ZERO);
etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
etatValeurStock.setPrixFinale(BigDecimal.ZERO);
etatValeurStock.setMontantFinale(BigDecimal.ZERO);

listEtatStockMP.add(etatValeurStock);

}


}

///////////////////////////////////////////////////////////////////////////////////////// Initial de sous famille Offre 	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.liblle = '"+Constantes.FAMILLE_CADEAU+"'";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_INITIAL, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantInitial=new BigDecimal(0);
quantiteTotalInitial=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantInitial=montantInitial.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalInitial=quantiteTotalInitial.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteInitial(quantiteTotalInitial);		    			
etatstockmp.setPrixInitial(montantInitial.divide(quantiteTotalInitial,6,RoundingMode.HALF_UP));
etatstockmp.setMontantInitial(etatstockmp.getQuantiteInitial().multiply(etatstockmp.getPrixInitial()));
listEtatStockMP.set(j, etatstockmp);

}

}


///////////////////////////////////////////////////////////////////////////////////////// Achat de sous famille Offre	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.liblle = '"+Constantes.FAMILLE_CADEAU+"'";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_ACHAT, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantachat=new BigDecimal(0);
quantiteTotalachat=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantachat=montantachat.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAchat(quantiteTotalachat);		    			
etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
listEtatStockMP.set(j, etatstockmp);

}

}


///////////////////////////////////////////////////////////////////////////////////////// Production Entrer de sous famille  Offre	 ////////////////////////////////////////////////////////////////////



requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.liblle = '"+Constantes.FAMILLE_CADEAU+"'";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantProductionEnter=new BigDecimal(0);
quantiteTotalProductionEnter=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantProductionEnter=montantProductionEnter.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalProductionEnter=quantiteTotalProductionEnter.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantProductionEnter.equals(BigDecimal.ZERO) && !quantiteTotalProductionEnter.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteProductionEntrer (quantiteTotalProductionEnter);		    			
etatstockmp.setPrixProductionEntrer(montantProductionEnter.divide(quantiteTotalProductionEnter,6,RoundingMode.HALF_UP));
etatstockmp.setMontantProductionEntrer(etatstockmp.getQuantiteProductionEntrer().multiply(etatstockmp.getPrixProductionEntrer()));
listEtatStockMP.set(j, etatstockmp);

}

}



///////////////////////////////////////////////////////////////////////////////////////// Les ventes de sous famille Offre	 ////////////////////////////////////////////////////////////////////



requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.liblle = '"+Constantes.FAMILLE_CADEAU+"'";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_VENTE, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantVente=new BigDecimal(0);
quantiteTotalVente=new BigDecimal(0);
quantiteTotalGratuite=new BigDecimal(0);
montantGratuite=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{

if(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
{

quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getId(), magasinPF.getId(),listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getId() , listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getId());

if(detailprixarticleTmp!=null)
{

montantGratuite=montantGratuite.add(detailprixarticleTmp.getPrix().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
}

}else
{
montantVente=montantVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());

}


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantVente.equals(BigDecimal.ZERO) && !quantiteTotalVente.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteVente (quantiteTotalVente);		    			
etatstockmp.setPrixVente (montantVente.divide(quantiteTotalVente,6,RoundingMode.HALF_UP));
etatstockmp.setMontantVente (etatstockmp.getQuantiteVente ().multiply(etatstockmp.getPrixVente ()));
listEtatStockMP.set(j, etatstockmp);

}

if(!quantiteTotalGratuite.equals(BigDecimal.ZERO))
{
etatstockmp.setQuantiteGratuite(quantiteTotalGratuite);		
etatstockmp.setPrixGratuite (montantGratuite.divide(quantiteTotalGratuite,6,RoundingMode.HALF_UP));
etatstockmp.setMontantGratuite(etatstockmp.getQuantiteGratuite().multiply(etatstockmp.getPrixGratuite()));

listEtatStockMP.set(j, etatstockmp);

}

}



///////////////////////////////////////////////////////////////////////////////////////// Les Avoirs de sous famille Offre	 ////////////////////////////////////////////////////////////////////



requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.liblle = '"+Constantes.FAMILLE_CADEAU+"'";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_AVOIR, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantAvoir=new BigDecimal(0);
quantiteTotalAvoir=new BigDecimal(0);


EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantAvoir=montantAvoir.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalAvoir=quantiteTotalAvoir.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());

}


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());




}
if(!montantAvoir.equals(BigDecimal.ZERO) && !quantiteTotalAvoir.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAvoir (quantiteTotalAvoir);		    			
etatstockmp.setPrixAvoir (montantAvoir.divide(quantiteTotalAvoir,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAvoir (etatstockmp.getQuantiteAvoir ().multiply(etatstockmp.getPrixAvoir ()));
listEtatStockMP.set(j, etatstockmp);

}

}



////////////////////////////////////////////////////////////////////////////////////////////////////////////// EMBALLAGE /////////////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////// Liste Group ADHESIF /////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPGroupBySubCategorie=detailTransferStockMPDAO.findAllTransferStockMPBySubCategorieGroupeByMP(magasin, subCategorieMpAdhesif)	;

for(int i=0;i<listDetailTransferStockMPGroupBySubCategorie.size();i++)
{
	
DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPGroupBySubCategorie.get(i);	
EtatValeurStock etatValeurStock=new EtatValeurStock();	
etatValeurStock.setCodearticle(detailTransferStockMP.getMatierePremier().getCode());;	
etatValeurStock.setArticle(detailTransferStockMP.getMatierePremier().getNom());
etatValeurStock.setFamille(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
etatValeurStock.setSousfamille (detailTransferStockMP.getMatierePremier().getCategorieMp().getNom());
etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
etatValeurStock.setPrixInitial(BigDecimal.ZERO);	
etatValeurStock.setMontantInitial(BigDecimal.ZERO);
etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
etatValeurStock.setPrixAchat(BigDecimal.ZERO);
etatValeurStock.setMontantAchat (BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
etatValeurStock.setPrixVente(BigDecimal.ZERO);
etatValeurStock.setMontantVente(BigDecimal.ZERO);
etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
etatValeurStock.setPrixFinale(BigDecimal.ZERO);
etatValeurStock.setMontantFinale(BigDecimal.ZERO);

listEtatStockMP.add(etatValeurStock);

}


////////////////////////////////////////////////////////////////////////////////////////////// Liste Initial ADHESIF /////////////////////////////////////////////////////////////////////////////////	    		


listDetailTransferStockMPInitial=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_INITIAL,magasin,subCategorieMpAdhesif);


for(int j=0;j<listEtatStockMP.size();j++)
{
	montantInitial=new BigDecimal(0);
	quantiteTotalInitial=new BigDecimal(0);
	
	EtatValeurStock etatstockmp=listEtatStockMP.get(j);	
	
for(int k=0;k<listDetailTransferStockMPInitial.size();k++)
{
	
	if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPInitial.get(k).getMatierePremier().getCode()))
	{
		

			montantInitial=montantInitial.add(listDetailTransferStockMPInitial.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPInitial.get(k).getQuantite()));
			quantiteTotalInitial=quantiteTotalInitial.add(listDetailTransferStockMPInitial.get(k).getQuantite());


		//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		
	}
	
	
}
	if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
	{

		etatstockmp.setQuantiteInitial (quantiteTotalInitial);		    			
		etatstockmp.setPrixInitial(montantInitial.divide(quantiteTotalInitial,6,RoundingMode.HALF_UP));
		etatstockmp.setMontantInitial(etatstockmp.getQuantiteInitial().multiply(etatstockmp.getPrixInitial()));
		listEtatStockMP.set(j, etatstockmp);
		
	}
	
}















//////////////////////////////////////////////////////////////////////////// Liste Des Achat  ADHESIF ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPAchat=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_ACHAT,magasin,subCategorieMpAdhesif);

for(int j=0;j<listEtatStockMP.size();j++)
{
	montantachat=new BigDecimal(0);
	quantiteTotalachat=new BigDecimal(0);
	
	EtatValeurStock etatstockmp=listEtatStockMP.get(j);	
	
for(int k=0;k<listDetailTransferStockMPAchat.size();k++)
{
	
	if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPAchat.get(k).getMatierePremier().getCode()))
	{
		

			montantachat=montantachat.add(listDetailTransferStockMPAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAchat.get(k).getQuantite()));
			quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockMPAchat.get(k).getQuantite());


		//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		
	}
	
	
}
	if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
	{

		etatstockmp.setQuantiteAchat(quantiteTotalachat);		    			
		etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
		etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
		listEtatStockMP.set(j, etatstockmp);
		
	}
	
}







///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Liste Des production Sortie  ADHESIF Charger   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		

listDetailTransferStockMPCharger=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_CHARGE,magasin,subCategorieMpAdhesif);
	for(int j=0;j<listEtatStockMP.size();j++)
{
		montantCharger=new BigDecimal(0);
		quantiteTotalCharger=new BigDecimal(0);
	
	EtatValeurStock etatstockmp=listEtatStockMP.get(j);	
	
for(int k=0;k<listDetailTransferStockMPCharger.size();k++)
{
	
	if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPCharger.get(k).getMatierePremier().getCode()))
	{
		BigDecimal quantite=BigDecimal.ZERO;
		quantite=listDetailTransferStockMPCharger.get(k).getQuantite();
		if(listDetailTransferStockMPCharger.get(k).getQuantiteOffre()!=null)
		{
			quantite=quantite.add(listDetailTransferStockMPCharger.get(k).getQuantiteOffre());
		}
		
		if(listDetailTransferStockMPCharger.get(k).getQuantiteDechet()!=null)
		{
			quantite=quantite.add(listDetailTransferStockMPCharger.get(k).getQuantiteDechet());
		}

			montantCharger=montantCharger.add(listDetailTransferStockMPCharger.get(k).getPrixUnitaire().multiply(quantite));
			
			quantiteTotalCharger=quantiteTotalCharger.add(quantite);


		//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		
	}
	
	
}
	if(!montantCharger.equals(BigDecimal.ZERO) && !quantiteTotalCharger.equals(BigDecimal.ZERO))
	{

		etatstockmp.setQuantiteProductionSortie (etatstockmp.getQuantiteProductionSortie().add(quantiteTotalCharger));		    			
		etatstockmp.setPrixProductionSortie((etatstockmp.getMontantProductionSortie().add(montantCharger)).divide(etatstockmp.getQuantiteProductionSortie(),6,RoundingMode.HALF_UP));
		etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
		listEtatStockMP.set(j, etatstockmp);
		
	}
	
}



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Liste Des production Sortie ADHESIF ChargerSupp   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		

listDetailTransferStockMPChargerSupp=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin,subCategorieMpAdhesif);
for(int j=0;j<listEtatStockMP.size();j++)
{
montantChargersupp=new BigDecimal(0);
quantiteTotalChargersupp=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPChargerSupp.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPChargerSupp.get(k).getMatierePremier().getCode()))
{


montantChargersupp=montantChargersupp.add(listDetailTransferStockMPChargerSupp.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPChargerSupp.get(k).getQuantite()));
quantiteTotalChargersupp=quantiteTotalChargersupp.add(listDetailTransferStockMPChargerSupp.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantChargersupp.equals(BigDecimal.ZERO) && !quantiteTotalChargersupp.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteProductionSortie (etatstockmp.getQuantiteProductionSortie().add(quantiteTotalChargersupp));		    			
etatstockmp.setPrixProductionSortie((etatstockmp.getMontantProductionSortie().add(montantChargersupp)).divide(etatstockmp.getQuantiteProductionSortie(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
listEtatStockMP.set(j, etatstockmp);

}

}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Liste Des production Sortie ADHESIF Service   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		

listDetailTransferStockMPService=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_SERVICE,magasin,subCategorieMpAdhesif);
for(int j=0;j<listEtatStockMP.size();j++)
{
montantService=new BigDecimal(0);
quantiteTotalService=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPService.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPService.get(k).getMatierePremier().getCode()))
{

BigDecimal quantite=BigDecimal.ZERO;
quantite=listDetailTransferStockMPService.get(k).getQuantite();
if(listDetailTransferStockMPService.get(k).getQuantiteOffre()!=null)
{
quantite=quantite.add(listDetailTransferStockMPService.get(k).getQuantiteOffre());
}

if(listDetailTransferStockMPService.get(k).getQuantiteDechet()!=null)
{
quantite=quantite.add(listDetailTransferStockMPService.get(k).getQuantiteDechet());
}


montantService=montantService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(quantite));
quantiteTotalService=quantiteTotalService.add(quantite);


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantService.equals(BigDecimal.ZERO) && !quantiteTotalService.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteProductionSortie (etatstockmp.getQuantiteProductionSortie().add(quantiteTotalService));		    			
etatstockmp.setPrixProductionSortie((etatstockmp.getMontantProductionSortie().add(montantService)).divide(etatstockmp.getQuantiteProductionSortie(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
listEtatStockMP.set(j, etatstockmp);

}

}

/////////////////////////////////////////////////////////////////////////////////////////////// Calculer Prix Moyen MP ///////////////////////////////////////////////////////////

for(int i=0;i<listEtatStockMP.size();i++)
{

EtatValeurStock etatstockmp=listEtatStockMP.get(i);		
if(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat()).compareTo(BigDecimal.ZERO)>0)
{
if(etatstockmp.getQuantiteProductionSortie().compareTo(BigDecimal.ZERO)>0)
{
etatstockmp.setPrixProductionSortie((etatstockmp.getMontantInitial().add(etatstockmp.getMontantAchat())).divide(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat()), 6, RoundingMode.HALF_UP));
etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
}


}


}



//////////////////////////////////////////////////////////////////////////// Liste Des Ventes ADHESIF ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPVenteMP=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_VENTE,magasin,subCategorieMpAdhesif);

for(int j=0;j<listEtatStockMP.size();j++)
{
montantVente=new BigDecimal(0);
quantiteTotalVente=new BigDecimal(0);
quantiteTotalGratuite=new BigDecimal(0);
EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPVenteMP.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPVenteMP.get(k).getMatierePremier().getCode()))
{
if(listDetailTransferStockMPVenteMP.get(k).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
{

quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockMPVenteMP.get(k).getQuantite());

DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getId(), magasinPF.getId(),listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getId() , listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getId());

if(detailprixarticleTmp!=null)
{

montantGratuite=montantGratuite.add(detailprixarticleTmp.getPrix().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));



}
}else
{
montantVente=montantVente.add(listDetailTransferStockMPVenteMP.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPVenteMP.get(k).getQuantite()));
quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockMPVenteMP.get(k).getQuantite());

}



//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantVente.equals(BigDecimal.ZERO) && !quantiteTotalVente.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteVente(quantiteTotalVente);		    			
etatstockmp.setPrixVente(montantVente.divide(quantiteTotalVente,6,RoundingMode.HALF_UP));
etatstockmp.setMontantVente(etatstockmp.getQuantiteVente().multiply(etatstockmp.getPrixVente()));		

listEtatStockMP.set(j, etatstockmp);

}

if(!quantiteTotalGratuite.equals(BigDecimal.ZERO))
{
etatstockmp.setQuantiteGratuite(quantiteTotalGratuite);
listEtatStockMP.set(j, etatstockmp);
}


}


////////////////////////////////////////////////////////////////////////////Liste Des Transfert ADHESIF En PF ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPTransfertMPPF=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasin,subCategorieMpAdhesif);

for(int j=0;j<listEtatStockMP.size();j++)
{
montantTransfertMPPF=new BigDecimal(0);
quantiteTotalTransfertMPPF=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPTransfertMPPF.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPTransfertMPPF.get(k).getMatierePremier().getCode()))
{


montantTransfertMPPF=montantTransfertMPPF.add(listDetailTransferStockMPTransfertMPPF.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransfertMPPF.get(k).getQuantite()));
quantiteTotalTransfertMPPF=quantiteTotalTransfertMPPF.add(listDetailTransferStockMPTransfertMPPF.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantTransfertMPPF.equals(BigDecimal.ZERO) && !quantiteTotalTransfertMPPF.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteVente(etatstockmp.getQuantiteVente().add(quantiteTotalTransfertMPPF));		    			
etatstockmp.setPrixVente((etatstockmp.getMontantVente().add(montantTransfertMPPF)).divide(etatstockmp.getQuantiteVente(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantVente(etatstockmp.getQuantiteVente().multiply(etatstockmp.getPrixVente()));
listEtatStockMP.set(j, etatstockmp);

}

}


//////////////////////////////////////////////////////////////////////////// Liste Des Avoirs ADHESIF ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPAvoirMP=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_AVOIR,magasin,subCategorieMpAdhesif);

for(int j=0;j<listEtatStockMP.size();j++)
{
montantAvoir=new BigDecimal(0);
quantiteTotalAvoir=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPAvoirMP.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPAvoirMP.get(k).getMatierePremier().getCode()))
{


montantAvoir=montantAvoir.add(listDetailTransferStockMPAvoirMP.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAvoirMP.get(k).getQuantite()));
quantiteTotalAvoir=quantiteTotalAvoir.add(listDetailTransferStockMPAvoirMP.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantAvoir.equals(BigDecimal.ZERO) && !quantiteTotalAvoir.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAvoir(quantiteTotalAvoir);		    			
etatstockmp.setPrixAvoir(montantAvoir.divide(quantiteTotalAvoir,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAvoir(etatstockmp.getQuantiteAvoir().multiply(etatstockmp.getPrixAvoir()));
listEtatStockMP.set(j, etatstockmp);

}

}

















////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////// Liste Group CARTON /////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPGroupBySubCategorie=detailTransferStockMPDAO.findAllTransferStockMPBySubCategorieGroupeByMP(magasin, subCategorieMpCarton)	;

for(int i=0;i<listDetailTransferStockMPGroupBySubCategorie.size();i++)
{

DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPGroupBySubCategorie.get(i);	
EtatValeurStock etatValeurStock=new EtatValeurStock();	
etatValeurStock.setCodearticle(detailTransferStockMP.getMatierePremier().getCode());;	
etatValeurStock.setArticle(detailTransferStockMP.getMatierePremier().getNom());
etatValeurStock.setFamille(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
etatValeurStock.setSousfamille (detailTransferStockMP.getMatierePremier().getCategorieMp().getNom());
etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
etatValeurStock.setPrixInitial(BigDecimal.ZERO);	
etatValeurStock.setMontantInitial(BigDecimal.ZERO);
etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
etatValeurStock.setPrixAchat(BigDecimal.ZERO);
etatValeurStock.setMontantAchat (BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
etatValeurStock.setPrixVente(BigDecimal.ZERO);
etatValeurStock.setMontantVente(BigDecimal.ZERO);
etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
etatValeurStock.setPrixFinale(BigDecimal.ZERO);
etatValeurStock.setMontantFinale(BigDecimal.ZERO);

listEtatStockMP.add(etatValeurStock);

}


//////////////////////////////////////////////////////////////////////////////////////////////Liste Initial CARTON /////////////////////////////////////////////////////////////////////////////////	    		


listDetailTransferStockMPInitial=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_INITIAL,magasin,subCategorieMpCarton);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantInitial=new BigDecimal(0);
quantiteTotalInitial=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPInitial.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPInitial.get(k).getMatierePremier().getCode()))
{


montantInitial=montantInitial.add(listDetailTransferStockMPInitial.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPInitial.get(k).getQuantite()));
quantiteTotalInitial=quantiteTotalInitial.add(listDetailTransferStockMPInitial.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteInitial (quantiteTotalInitial);		    			
etatstockmp.setPrixInitial(montantInitial.divide(quantiteTotalInitial,6,RoundingMode.HALF_UP));
etatstockmp.setMontantInitial(etatstockmp.getQuantiteInitial().multiply(etatstockmp.getPrixInitial()));
listEtatStockMP.set(j, etatstockmp);

}

}















//////////////////////////////////////////////////////////////////////////// Liste Des Achat  CARTON ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPAchat=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_ACHAT,magasin,subCategorieMpCarton);

for(int j=0;j<listEtatStockMP.size();j++)
{
montantachat=new BigDecimal(0);
quantiteTotalachat=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPAchat.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPAchat.get(k).getMatierePremier().getCode()))
{


montantachat=montantachat.add(listDetailTransferStockMPAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAchat.get(k).getQuantite()));
quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockMPAchat.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAchat(quantiteTotalachat);		    			
etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
listEtatStockMP.set(j, etatstockmp);

}

}







///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Liste Des production Sortie  CARTON Charger   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		

listDetailTransferStockMPCharger=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_CHARGE,magasin,subCategorieMpCarton);
for(int j=0;j<listEtatStockMP.size();j++)
{
montantCharger=new BigDecimal(0);
quantiteTotalCharger=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPCharger.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPCharger.get(k).getMatierePremier().getCode()))
{
BigDecimal quantite=BigDecimal.ZERO;
quantite=listDetailTransferStockMPCharger.get(k).getQuantite();
if(listDetailTransferStockMPCharger.get(k).getQuantiteOffre()!=null)
{
quantite=quantite.add(listDetailTransferStockMPCharger.get(k).getQuantiteOffre());
}

if(listDetailTransferStockMPCharger.get(k).getQuantiteDechet()!=null)
{
quantite=quantite.add(listDetailTransferStockMPCharger.get(k).getQuantiteDechet());
}

montantCharger=montantCharger.add(listDetailTransferStockMPCharger.get(k).getPrixUnitaire().multiply(quantite));

quantiteTotalCharger=quantiteTotalCharger.add(quantite);


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantCharger.equals(BigDecimal.ZERO) && !quantiteTotalCharger.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteProductionSortie (etatstockmp.getQuantiteProductionSortie().add(quantiteTotalCharger));		    			
etatstockmp.setPrixProductionSortie((etatstockmp.getMontantProductionSortie().add(montantCharger)).divide(etatstockmp.getQuantiteProductionSortie(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
listEtatStockMP.set(j, etatstockmp);

}

}



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Liste Des production Sortie CARTON ChargerSupp   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		

listDetailTransferStockMPChargerSupp=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin,subCategorieMpCarton);
for(int j=0;j<listEtatStockMP.size();j++)
{
montantChargersupp=new BigDecimal(0);
quantiteTotalChargersupp=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPChargerSupp.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPChargerSupp.get(k).getMatierePremier().getCode()))
{


montantChargersupp=montantChargersupp.add(listDetailTransferStockMPChargerSupp.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPChargerSupp.get(k).getQuantite()));
quantiteTotalChargersupp=quantiteTotalChargersupp.add(listDetailTransferStockMPChargerSupp.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantChargersupp.equals(BigDecimal.ZERO) && !quantiteTotalChargersupp.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteProductionSortie (etatstockmp.getQuantiteProductionSortie().add(quantiteTotalChargersupp));		    			
etatstockmp.setPrixProductionSortie((etatstockmp.getMontantProductionSortie().add(montantChargersupp)).divide(etatstockmp.getQuantiteProductionSortie(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
listEtatStockMP.set(j, etatstockmp);

}

}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Liste Des production Sortie CARTON Service   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////    		

listDetailTransferStockMPService=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_SERVICE,magasin,subCategorieMpCarton);
for(int j=0;j<listEtatStockMP.size();j++)
{
montantService=new BigDecimal(0);
quantiteTotalService=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPService.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPService.get(k).getMatierePremier().getCode()))
{

BigDecimal quantite=BigDecimal.ZERO;
quantite=listDetailTransferStockMPService.get(k).getQuantite();
if(listDetailTransferStockMPService.get(k).getQuantiteOffre()!=null)
{
quantite=quantite.add(listDetailTransferStockMPService.get(k).getQuantiteOffre());
}

if(listDetailTransferStockMPService.get(k).getQuantiteDechet()!=null)
{
quantite=quantite.add(listDetailTransferStockMPService.get(k).getQuantiteDechet());
}


montantService=montantService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(quantite));
quantiteTotalService=quantiteTotalService.add(quantite);


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantService.equals(BigDecimal.ZERO) && !quantiteTotalService.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteProductionSortie (etatstockmp.getQuantiteProductionSortie().add(quantiteTotalService));		    			
etatstockmp.setPrixProductionSortie((etatstockmp.getMontantProductionSortie().add(montantService)).divide(etatstockmp.getQuantiteProductionSortie(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
listEtatStockMP.set(j, etatstockmp);

}

}

/////////////////////////////////////////////////////////////////////////////////////////////// Calculer Prix Moyen MP ///////////////////////////////////////////////////////////

for(int i=0;i<listEtatStockMP.size();i++)
{

EtatValeurStock etatstockmp=listEtatStockMP.get(i);		
if(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat()).compareTo(BigDecimal.ZERO)>0)
{
if(etatstockmp.getQuantiteProductionSortie().compareTo(BigDecimal.ZERO)>0)
{
etatstockmp.setPrixProductionSortie((etatstockmp.getMontantInitial().add(etatstockmp.getMontantAchat())).divide(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat()), 6, RoundingMode.HALF_UP));
etatstockmp.setMontantProductionSortie(etatstockmp.getQuantiteProductionSortie().multiply(etatstockmp.getPrixProductionSortie()));
}


}


}



////////////////////////////////////////////////////////////////////////////Liste Des Ventes CARTON ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPVenteMP=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_VENTE,magasin,subCategorieMpCarton);

for(int j=0;j<listEtatStockMP.size();j++)
{
montantVente=new BigDecimal(0);
quantiteTotalVente=new BigDecimal(0);
quantiteTotalGratuite=new BigDecimal(0);
EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPVenteMP.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPVenteMP.get(k).getMatierePremier().getCode()))
{
if(listDetailTransferStockMPVenteMP.get(k).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
{

quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockMPVenteMP.get(k).getQuantite());

DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getId(), magasinPF.getId(),listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getId() , listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getId());

if(detailprixarticleTmp!=null)
{

montantGratuite=montantGratuite.add(detailprixarticleTmp.getPrix().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));



}
}else
{
montantVente=montantVente.add(listDetailTransferStockMPVenteMP.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPVenteMP.get(k).getQuantite()));
quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockMPVenteMP.get(k).getQuantite());

}



//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantVente.equals(BigDecimal.ZERO) && !quantiteTotalVente.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteVente(quantiteTotalVente);		    			
etatstockmp.setPrixVente(montantVente.divide(quantiteTotalVente,6,RoundingMode.HALF_UP));
etatstockmp.setMontantVente(etatstockmp.getQuantiteVente().multiply(etatstockmp.getPrixVente()));		

listEtatStockMP.set(j, etatstockmp);

}

if(!quantiteTotalGratuite.equals(BigDecimal.ZERO))
{
etatstockmp.setQuantiteGratuite(quantiteTotalGratuite);
listEtatStockMP.set(j, etatstockmp);
}


}


////////////////////////////////////////////////////////////////////////////Liste Des Transfert CARTON En PF ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPTransfertMPPF=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasin,subCategorieMpCarton);

for(int j=0;j<listEtatStockMP.size();j++)
{
montantTransfertMPPF=new BigDecimal(0);
quantiteTotalTransfertMPPF=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPTransfertMPPF.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPTransfertMPPF.get(k).getMatierePremier().getCode()))
{


montantTransfertMPPF=montantTransfertMPPF.add(listDetailTransferStockMPTransfertMPPF.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransfertMPPF.get(k).getQuantite()));
quantiteTotalTransfertMPPF=quantiteTotalTransfertMPPF.add(listDetailTransferStockMPTransfertMPPF.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantTransfertMPPF.equals(BigDecimal.ZERO) && !quantiteTotalTransfertMPPF.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteVente(etatstockmp.getQuantiteVente().add(quantiteTotalTransfertMPPF));		    			
etatstockmp.setPrixVente((etatstockmp.getMontantVente().add(montantTransfertMPPF)).divide(etatstockmp.getQuantiteVente(),6,RoundingMode.HALF_UP));
etatstockmp.setMontantVente(etatstockmp.getQuantiteVente().multiply(etatstockmp.getPrixVente()));
listEtatStockMP.set(j, etatstockmp);

}

}


////////////////////////////////////////////////////////////////////////////Liste Des Avoirs CARTON ////////////////////////////////////////////////////////////////////////////////////////////////

listDetailTransferStockMPAvoirMP=detailTransferStockMPDAO.ListTransferStockMPBySubCategorieEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(),  ETAT_TRANSFER_STOCK_AVOIR,magasin,subCategorieMpCarton);

for(int j=0;j<listEtatStockMP.size();j++)
{
montantAvoir=new BigDecimal(0);
quantiteTotalAvoir=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockMPAvoirMP.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockMPAvoirMP.get(k).getMatierePremier().getCode()))
{


montantAvoir=montantAvoir.add(listDetailTransferStockMPAvoirMP.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAvoirMP.get(k).getQuantite()));
quantiteTotalAvoir=quantiteTotalAvoir.add(listDetailTransferStockMPAvoirMP.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantAvoir.equals(BigDecimal.ZERO) && !quantiteTotalAvoir.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAvoir(quantiteTotalAvoir);		    			
etatstockmp.setPrixAvoir(montantAvoir.divide(quantiteTotalAvoir,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAvoir(etatstockmp.getQuantiteAvoir().multiply(etatstockmp.getPrixAvoir()));
listEtatStockMP.set(j, etatstockmp);

}

}







//////////////////////////////////////////////////////////////////////////////Liste Des PF Par Sous Famille  Emballage  ////////////////////////////////////////////////////////////////////////////

listDetailTransferStockPFBySousFamille=detailTransferProduitFiniDAO.ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupByArticleBySousFamille(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasinPF);




for(int i=0;i<listDetailTransferStockPFBySousFamille.size();i++)
{



DetailTransferProduitFini detailTransferStockPF=listDetailTransferStockPFBySousFamille.get(i);	



if(detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_EMBALLAGE))
{
EtatValeurStock etatValeurStock=new EtatValeurStock();	
etatValeurStock.setCodearticle(detailTransferStockPF.getArticle().getCodeArticle());	
etatValeurStock.setArticle(detailTransferStockPF.getArticle().getLiblle());
etatValeurStock.setFamille(detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle());
etatValeurStock.setSousfamille (detailTransferStockPF.getSousFamille().getLiblle());
etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
etatValeurStock.setPrixInitial(BigDecimal.ZERO);	
etatValeurStock.setMontantInitial(BigDecimal.ZERO);
etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
etatValeurStock.setPrixAchat(BigDecimal.ZERO);
etatValeurStock.setMontantAchat (BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
etatValeurStock.setPrixVente(BigDecimal.ZERO);
etatValeurStock.setMontantVente(BigDecimal.ZERO);
etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
etatValeurStock.setPrixFinale(BigDecimal.ZERO);
etatValeurStock.setMontantFinale(BigDecimal.ZERO);

listEtatStockMP.add(etatValeurStock);

}


}

///////////////////////////////////////////////////////////////////////////////////////// Initial de sous famille Emballage 	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.liblle = '"+Constantes.FAMILLE_EMBALLAGE+"'";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_INITIAL, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantInitial=new BigDecimal(0);
quantiteTotalInitial=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantInitial=montantInitial.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalInitial=quantiteTotalInitial.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteInitial(quantiteTotalInitial);		    			
etatstockmp.setPrixInitial(montantInitial.divide(quantiteTotalInitial,6,RoundingMode.HALF_UP));
etatstockmp.setMontantInitial(etatstockmp.getQuantiteInitial().multiply(etatstockmp.getPrixInitial()));
listEtatStockMP.set(j, etatstockmp);

}

}


///////////////////////////////////////////////////////////////////////////////////////// Achat de sous famille Emballage	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.liblle = '"+Constantes.FAMILLE_EMBALLAGE+"'";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_ACHAT, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantachat=new BigDecimal(0);
quantiteTotalachat=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantachat=montantachat.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAchat(quantiteTotalachat);		    			
etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
listEtatStockMP.set(j, etatstockmp);

}

}




///////////////////////////////////////////////////////////////////////////////////////// Les ventes de sous famille Emballage	 ////////////////////////////////////////////////////////////////////



requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.liblle = '"+Constantes.FAMILLE_EMBALLAGE+"'";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_VENTE, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantVente=new BigDecimal(0);
quantiteTotalVente=new BigDecimal(0);
quantiteTotalGratuite=new BigDecimal(0);
montantGratuite=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{

if(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
{

quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
DetailPrixArticle detailprixarticleTmp=detailPrixArticleDAO.findDetaileArticleByArticleByMagasinByfamilleBaySousfamille(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getId(), magasinPF.getId(),listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getId() , listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getId());

if(detailprixarticleTmp!=null)
{

montantGratuite=montantGratuite.add(detailprixarticleTmp.getPrix().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
}

}else
{
montantVente=montantVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());

}


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());

}


}
if(!montantVente.equals(BigDecimal.ZERO) && !quantiteTotalVente.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteVente (quantiteTotalVente);		    			
etatstockmp.setPrixVente (montantVente.divide(quantiteTotalVente,6,RoundingMode.HALF_UP));
etatstockmp.setMontantVente (etatstockmp.getQuantiteVente ().multiply(etatstockmp.getPrixVente ()));
listEtatStockMP.set(j, etatstockmp);

}

if(!quantiteTotalGratuite.equals(BigDecimal.ZERO))
{
etatstockmp.setQuantiteGratuite(quantiteTotalGratuite);		
etatstockmp.setPrixGratuite (montantGratuite.divide(quantiteTotalGratuite,6,RoundingMode.HALF_UP));
etatstockmp.setMontantGratuite (etatstockmp.getQuantiteGratuite().multiply(etatstockmp.getPrixGratuite()));

listEtatStockMP.set(j, etatstockmp);

}

}

///////////////////////////////////////////////////////////////////////////////////////// Les Avoirs de sous famille Emballage	 ////////////////////////////////////////////////////////////////////



requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.liblle = '"+Constantes.FAMILLE_EMBALLAGE+"'";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_AVOIR, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantAvoir=new BigDecimal(0);
quantiteTotalAvoir=new BigDecimal(0);


EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{


montantAvoir=montantAvoir.add(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
quantiteTotalAvoir=quantiteTotalAvoir.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());

}


//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());




}
if(!montantAvoir.equals(BigDecimal.ZERO) && !quantiteTotalAvoir.equals(BigDecimal.ZERO))
{

etatstockmp.setQuantiteAvoir (quantiteTotalAvoir);		    			
etatstockmp.setPrixAvoir (montantAvoir.divide(quantiteTotalAvoir,6,RoundingMode.HALF_UP));
etatstockmp.setMontantAvoir (etatstockmp.getQuantiteAvoir ().multiply(etatstockmp.getPrixAvoir ()));
listEtatStockMP.set(j, etatstockmp);

}

}


////////////////////////////////////////////////////////////////////////////////////////////////// Calculer le Stock Finale et  Prix Moyen ////////////////////////////////////////////////////////////////////////////////////////////////////////////


CalculerPrixMoyenFamille( magasinPF);	

BigDecimal StockInitial=BigDecimal.ZERO;
BigDecimal StockEmballage=BigDecimal.ZERO;
BigDecimal StockSansEmballage=BigDecimal.ZERO;
BigDecimal AchatTotal=BigDecimal.ZERO;
BigDecimal AchatEmballage=BigDecimal.ZERO;
BigDecimal AchatSansEmballage=BigDecimal.ZERO;
BigDecimal ProductionEntrerTotal=BigDecimal.ZERO;
BigDecimal ProductionSortieTotal=BigDecimal.ZERO;
BigDecimal ChiffreAffaireVente=BigDecimal.ZERO;
BigDecimal AVGratuite=BigDecimal.ZERO;
BigDecimal AvoirTotal=BigDecimal.ZERO;
BigDecimal AvoirEmballage=BigDecimal.ZERO;
BigDecimal AvoirSansEmballage=BigDecimal.ZERO;
BigDecimal StockTotalFinale=BigDecimal.ZERO;
BigDecimal StockTotalFinaleEmballage=BigDecimal.ZERO;
BigDecimal StockTotalFinaleSansEmballage=BigDecimal.ZERO;

for(int i=0;i<listEtatStockMP.size();i++)
{
	
	EtatValeurStock etatstockmp=listEtatStockMP.get(i);	
	
BigDecimal stockfinale=BigDecimal.ZERO;
BigDecimal prixMoyen=BigDecimal.ZERO;


StockInitial=StockInitial.add(etatstockmp.getMontantInitial());
AchatTotal=AchatTotal.add(etatstockmp.getMontantAchat());
ProductionEntrerTotal=ProductionEntrerTotal.add(etatstockmp.getMontantProductionEntrer());
ProductionSortieTotal=ProductionSortieTotal.add(etatstockmp.getMontantProductionSortie());
ChiffreAffaireVente=ChiffreAffaireVente.add(etatstockmp.getMontantVente());
AVGratuite=AVGratuite.add(etatstockmp.getMontantGratuite());
AvoirTotal=AvoirTotal.add(etatstockmp.getMontantAvoir());







	
stockfinale=(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat().add(etatstockmp.getQuantiteProductionEntrer()))).subtract(etatstockmp.getQuantiteProductionSortie().add(etatstockmp.getQuantiteVente().add(etatstockmp.getQuantiteGratuite().add(etatstockmp.getQuantiteAvoir()))));
	
if(etatstockmp.getFamille().equals(LIBELLE_FAMILLE_CHAARA) || etatstockmp.getFamille().equals(LIBELLE_FAMILLE_MKARKEB) || etatstockmp.getFamille().equals(LIBELLE_FAMILLE_ELNASS) || etatstockmp.getFamille().equals(LIBELLE_FAMILLE_ADMINISTRATION))
{
	


	

BigDecimal TotalMontant=BigDecimal.ZERO;
BigDecimal TotalQuantite=BigDecimal.ZERO;
BigDecimal TotalMontantVente=BigDecimal.ZERO;
BigDecimal TotalQuantiteVente=BigDecimal.ZERO;

PrixMoyenFamille=BigDecimal.ZERO;
PrixMoyenVenteFamille=BigDecimal.ZERO;

	for(int t=0;t<listEtatStockPF.size();t++)
	{
		
		EtatStockPF etatstockpf=listEtatStockPF.get(t);
		if(etatstockpf.getFamilleArticle().getLiblle().equals(etatstockmp.getFamille()))
		{
			if(etatstockpf.getSousFamille().getLiblle().equals( etatstockmp.getSousfamille()))
			{
				if(!etatstockpf.getArticle().getLiblle().contains(MP_STOCK_OFFRE))
				{
					TotalMontant=TotalMontant.add(etatstockpf.getMontantFinale())	;
					TotalQuantite=TotalQuantite.add(etatstockpf.getQuantiteFinale());
					
					TotalMontantVente=TotalMontantVente.add(etatstockpf.getMontantSortie())	;
					TotalQuantiteVente=TotalQuantiteVente.add(etatstockpf.getQuantiteSortie());
					
					
					
				}
				
				
				
				
				
				
			}
			
			
			
			
			
			
			
		}
		
		
		
	}
	
	
	if(TotalQuantite.compareTo(BigDecimal.ZERO)!=0 )
	{
		PrixMoyenFamille=TotalMontant.divide(TotalQuantite, 6, RoundingMode.DOWN);
		
	}
	
	if(TotalQuantiteVente.compareTo(BigDecimal.ZERO)!=0 )
	{
		PrixMoyenVenteFamille=TotalMontantVente.divide(TotalQuantiteVente, 6, RoundingMode.DOWN);
		System.out.println(etatstockmp.getSousfamille() +" : "+etatstockmp.getFamille() +" : "+TotalMontantVente +" / " +TotalQuantiteVente);
		
	}
	
	if(PrixMoyenVenteFamille.compareTo(BigDecimal.ZERO)!=0)
	{
		etatstockmp.setPrixVente(PrixMoyenVenteFamille);
		etatstockmp.setMontantFinale(etatstockmp.getQuantiteVente().multiply(etatstockmp.getPrixVente()));
	}
	
	prixMoyen=PrixMoyenFamille;	

	
	
}else if((etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat().add(etatstockmp.getQuantiteProductionEntrer()))).compareTo(BigDecimal.ZERO)!=0)
	{
		prixMoyen=(etatstockmp.getMontantInitial().add(etatstockmp.getMontantAchat().add(etatstockmp.getMontantProductionEntrer()))).divide(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat().add(etatstockmp.getQuantiteProductionEntrer())), 6, RoundingMode.HALF_UP)	;

	}
	
etatstockmp.setQuantiteFinale(stockfinale);	
etatstockmp.setPrixFinale(prixMoyen);
etatstockmp.setMontantFinale(etatstockmp.getQuantiteFinale().multiply(etatstockmp.getPrixFinale()));
listEtatStockMP.set(i, etatstockmp);	



StockTotalFinale=StockTotalFinale.add(etatstockmp.getMontantFinale());

if(etatstockmp.getFamille().equals(Constantes.FAMILLE_EMBALLAGE))
{
	StockEmballage=StockEmballage.add(etatstockmp.getMontantInitial());
	AchatEmballage=AchatEmballage.add(etatstockmp.getMontantAchat());
	AvoirEmballage=AvoirEmballage.add(etatstockmp.getMontantAvoir());
	
	StockTotalFinaleEmballage=StockTotalFinaleEmballage.add(etatstockmp.getMontantFinale());
	
	
}



	
	
}


StockSansEmballage=StockInitial.subtract(StockEmballage);
AchatSansEmballage=AchatTotal.subtract(AchatEmballage);
AvoirSansEmballage=AvoirTotal.subtract(AvoirEmballage);
StockTotalFinaleSansEmballage=StockTotalFinale.subtract(StockTotalFinaleEmballage);



for(int d=0;d<listEtatStockMP.size();d++)
{
	
EtatValeurStock etatValeurStock=listEtatStockMP.get(d);	
	
	if(etatValeurStock.getQuantiteGratuite().compareTo(BigDecimal.ZERO)>0)
	{
		

		if(etatValeurStock.getPrixAchat().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
		{
			etatValeurStock.setPrixGratuite(etatValeurStock.getPrixInitial().setScale(6));
		}else
		{
			etatValeurStock.setPrixGratuite(etatValeurStock.getPrixAchat().setScale(6));
		}
		
		
		etatValeurStock.setMontantGratuite (etatValeurStock.getQuantiteGratuite().setScale(6).multiply(etatValeurStock.getPrixGratuite().setScale(6)));;
		
		listEtatStockMP.set(d, etatValeurStock);
		
		
		
	}
	
	
	
}





	    		
	    		afficher_tableEtatStock(listEtatStockMP);
	    		

labelstockinitial.setText(StockInitial.setScale(6, RoundingMode.HALF_UP)+"");	    		
labelstockemballage.setText(StockEmballage.setScale(6, RoundingMode.HALF_UP)+"");	
labelstocksansemballage.setText(StockSansEmballage.setScale(6, RoundingMode.HALF_UP)+"");
 labelstockachat.setText(AchatTotal.setScale(6, RoundingMode.HALF_UP)+"");
labelachatemballage.setText(AchatEmballage.setScale(6, RoundingMode.HALF_UP)+"");	 
labelachatsansemballage.setText(AchatSansEmballage.setScale(6, RoundingMode.HALF_UP)+"");
labelprodentrer.setText(ProductionEntrerTotal.setScale(6, RoundingMode.HALF_UP)+"");
labelprodsortie.setText(ProductionSortieTotal.setScale(6, RoundingMode.HALF_UP)+"");
labelcavente.setText(ChiffreAffaireVente.setScale(6, RoundingMode.HALF_UP)+"");
labelgratuite.setText(AVGratuite.setScale(6, RoundingMode.HALF_UP)+"");
labelstockavoir.setText(AvoirTotal.setScale(6, RoundingMode.HALF_UP)+"");
labelavoiremballage.setText(AvoirEmballage.setScale(6, RoundingMode.HALF_UP)+"");	    		
labelavoirsansemballage.setText(AvoirSansEmballage.setScale(6, RoundingMode.HALF_UP)+"");	    
labelstockfinale.setText(StockTotalFinale.setScale(6, RoundingMode.HALF_UP)+"");    		
labelfinaleemballage.setText(StockTotalFinaleEmballage.setScale(6, RoundingMode.HALF_UP)+"");   
labelfinalesansemballage.setText(StockTotalFinaleSansEmballage.setScale(6, RoundingMode.HALF_UP)+"");
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		

	    		
	    		
	    		}
	    		else
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner Magasin MP et Magasin PF SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		
	    	
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(534, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane.setBounds(62, 57, 1384, 51);
	    add(layeredPane);
	    
	    JLabel label = new JLabel("Date Debut :");
	    label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label.setBounds(10, 11, 136, 24);
	    layeredPane.add(label);
	    
	     dateChooserdebut = new JDateChooser();
	    dateChooserdebut.setLocale(Locale.FRANCE);
	    dateChooserdebut.setDateFormatString("dd/MM/yyyy");
	    dateChooserdebut.setBounds(112, 9, 163, 26);
	    layeredPane.add(dateChooserdebut);
	    
	    JLabel label_1 = new JLabel("Date Fin :");
	    label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_1.setBounds(296, 11, 106, 24);
	    layeredPane.add(label_1);
	    
	     dateChooserfin = new JDateChooser();
	    dateChooserfin.setLocale(Locale.FRANCE);
	    dateChooserfin.setDateFormatString("dd/MM/yyyy");
	    dateChooserfin.setBounds(368, 11, 169, 26);
	    layeredPane.add(dateChooserfin);
	    int i=0;
		while(i<listMP.size())
		{
			MatierePremier mp=listMP.get(i);
			combomp.addItem(mp.getNom());
			mapMP.put(mp.getNom(), mp);
			mapCodeMP.put(mp.getCode(), mp);
			
			
			i++;
			
		}
	    
	    JLabel lblMagasinMp = new JLabel("Magasin MP  :");
	    lblMagasinMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblMagasinMp.setBounds(561, 11, 79, 26);
	    layeredPane.add(lblMagasinMp);
	    
	     combodepot = new JComboBox();
	    combodepot.setBounds(650, 11, 235, 27);
	    layeredPane.add(combodepot);
	  try {
		  
		 
          Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)util.DateUtils.getCurrentYear()+"-01-01");
          dateChooserdebut.setDate(date);
          dateChooserfin.setDate(new Date());
          
          JLabel lblMagasinPf = new JLabel("Magasin PF  :");
          lblMagasinPf.setFont(new Font("Tahoma", Font.PLAIN, 11));
          lblMagasinPf.setBounds(911, 11, 79, 26);
          layeredPane.add(lblMagasinPf);
          
           ComboMagasinPF = new JComboBox();
          ComboMagasinPF.setBounds(1000, 11, 235, 27);
          layeredPane.add(ComboMagasinPF);
		  
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	    
	    JButton button = new JButton("Initialiser");
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		

	     		
	    		combodepot.setSelectedItem("");
	    		ComboMagasinPF.setSelectedIndex(-1);
	     		dateChooserdebut.setCalendar(null);
	     		dateChooserfin.setCalendar(null);
	     		
	     		
	     	
	    		
	    	}
	    });
	    button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button.setBounds(668, 119, 107, 24);
	    add(button);
	    
	    JButton btnExporterExcel = new JButton("Exporter Excel");
	    btnExporterExcel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    		
	    		String titre="Etat de Valeur Stock  "+magasin.getLibelle();
	    		String titrefeuilleexcel="Etat de Valeur Stock  ";
	    		ExporterTableVersExcel.tabletoexcelEtatValeurStock(tableEtatStock, titre,titrefeuilleexcel,labelstockinitial.getText(),labelstockemballage.getText(),labelstocksansemballage.getText(),labelstockachat.getText(),labelachatemballage.getText(),labelachatsansemballage.getText(),labelprodentrer.getText(),labelprodsortie.getText(),labelcavente.getText(),labelgratuite.getText(),labelstockavoir.getText(),labelavoiremballage.getText(),labelavoirsansemballage.getText(),labelstockfinale.getText(),labelfinaleemballage.getText(),labelfinalesansemballage.getText());
	    		}else
	    		{

	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		}
	    		/*
	    		

	            int number = workbook.getNumberOfSheets();
	            if (number == 1) {
	                workbook.removeSheetAt(0);
	            }
	            org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet("simple");

	            org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

	            org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
	            ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
	            ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
	            ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

	            Font font1 = (Font) workbook.createFont();
	            ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
	            ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
	            ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);
	          
	            for (int j = 0; j < tableEtatStock.getColumns().size(); j++) {
	                row.createCell(j).setCellValue(tableEtatStock.getColumnName(j).toString());
	       

	            }

	            for (int i = 0; i < tableEtatStock.getRowCount(); i++) {
	                row = spreadsheet.createRow(i + 1);
	                for (int j = 0; j < tableEtatStock.getColumns().size(); j++) {
	                    if (tableEtatStock.getValueAt(i, j) != null) {
	                        row.createCell(j).setCellValue(tableEtatStock.getValueAt(i, j).toString());
	       

	                    } else {
	                        row.createCell(j).setCellValue("");
	                    }
	                }
	            }
	            int rowNum = 0;
	            for (short i = spreadsheet.getRow(0).getFirstCellNum(),
	                    end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
	                CellRangeAddress ca
	                        = new CellRangeAddress(0, rowNum,
	                                spreadsheet.getRow(0).getFirstCellNum(),
	                                spreadsheet.getRow(0).getLastCellNum());
	      
	                rowNum++;

	            }
	            for (short i = spreadsheet.getRow(0).getFirstCellNum(),
	                    end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
	                spreadsheet.autoSizeColumn(i);
	            }
	            try (FileOutputStream fileOut = new FileOutputStream("workbook.xls")) {

	                workbook.write(fileOut);
	                fileOut.flush();
	                fileOut.close();
	                Desktop.getDesktop().open(new File("workbook.xls"));

	            } catch (Exception e) {
					// TODO: handle exception
				}
	    		
	    		
	    		
	    		
	    	*/}
	    	
	
	    	
	    });
	    btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnExporterExcel.setBounds(721, 975, 130, 32);
	    btnExporterExcel.setIcon(imgExcel);
	    add(btnExporterExcel);
	    
	    JLabel lblStockInitial = new JLabel("Stock Initial :");
	    lblStockInitial.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblStockInitial.setBounds(20, 802, 95, 24);
	    add(lblStockInitial);
	    
	    JLabel lblStockEmballage = new JLabel("Stock Emballage:");
	    lblStockEmballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblStockEmballage.setBounds(20, 837, 123, 24);
	    add(lblStockEmballage);
	    
	    JLabel lblStockSansEmballage = new JLabel("Stock Sans Emballage:");
	    lblStockSansEmballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblStockSansEmballage.setBounds(20, 867, 161, 24);
	    add(lblStockSansEmballage);
	    
	     labelstockinitial = new JLabel("");
	     labelstockinitial.setForeground(Color.GREEN);
	    labelstockinitial.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelstockinitial.setBounds(125, 802, 183, 24);
	    add(labelstockinitial);
	    
	     labelstockemballage = new JLabel("");
	     labelstockemballage.setForeground(Color.GREEN);
	    labelstockemballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelstockemballage.setBounds(153, 837, 175, 24);
	    add(labelstockemballage);
	    
	     labelstocksansemballage = new JLabel("");
	     labelstocksansemballage.setForeground(Color.GREEN);
	    labelstocksansemballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelstocksansemballage.setBounds(178, 867, 150, 24);
	    add(labelstocksansemballage);
	    
	    JLabel lblStockAchat = new JLabel("Stock Achat:");
	    lblStockAchat.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblStockAchat.setBounds(353, 802, 95, 24);
	    add(lblStockAchat);
	    
	    JLabel lblStockAchatEmballage = new JLabel("Stock Achat Emballage:");
	    lblStockAchatEmballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblStockAchatEmballage.setBounds(353, 837, 161, 24);
	    add(lblStockAchatEmballage);
	    
	    JLabel lblStockAchatSans = new JLabel("Stock Achat Sans Emballage:");
	    lblStockAchatSans.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblStockAchatSans.setBounds(353, 867, 200, 24);
	    add(lblStockAchatSans);
	    
	     labelachatsansemballage = new JLabel("");
	     labelachatsansemballage.setForeground(Color.GREEN);
	    labelachatsansemballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelachatsansemballage.setBounds(551, 867, 150, 24);
	    add(labelachatsansemballage);
	    
	     labelachatemballage = new JLabel("");
	     labelachatemballage.setForeground(Color.GREEN);
	    labelachatemballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelachatemballage.setBounds(517, 837, 142, 24);
	    add(labelachatemballage);
	    
	     labelstockachat = new JLabel("");
	     labelstockachat.setForeground(Color.GREEN);
	    labelstockachat.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelstockachat.setBounds(458, 802, 183, 24);
	    add(labelstockachat);
	    
	    JLabel lblStockProdEntrer = new JLabel("Stock Prod Entrer :");
	    lblStockProdEntrer.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblStockProdEntrer.setBounds(651, 802, 142, 24);
	    add(lblStockProdEntrer);
	    
	     labelprodentrer = new JLabel("");
	     labelprodentrer.setForeground(Color.GREEN);
	    labelprodentrer.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelprodentrer.setBounds(797, 802, 150, 24);
	    add(labelprodentrer);
	    
	     labelprodsortie = new JLabel("");
	     labelprodsortie.setForeground(Color.GREEN);
	    labelprodsortie.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelprodsortie.setBounds(778, 837, 150, 24);
	    add(labelprodsortie);
	    
	    JLabel lblStockProdSrtie = new JLabel("Stock Prod Srtie:");
	    lblStockProdSrtie.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblStockProdSrtie.setBounds(647, 837, 121, 24);
	    add(lblStockProdSrtie);
	    
	    JLabel lblCa = new JLabel("CA:");
	    lblCa.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblCa.setBounds(711, 867, 48, 24);
	    add(lblCa);
	    
	     labelcavente = new JLabel("");
	     labelcavente.setForeground(Color.GREEN);
	    labelcavente.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelcavente.setBounds(764, 867, 164, 24);
	    add(labelcavente);
	    
	     labelgratuite = new JLabel("");
	     labelgratuite.setForeground(Color.GREEN);
	    labelgratuite.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelgratuite.setBounds(743, 902, 130, 24);
	    add(labelgratuite);
	    
	    JLabel lblAvGratuite = new JLabel("AV Gratuite:");
	    lblAvGratuite.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblAvGratuite.setBounds(647, 902, 86, 24);
	    add(lblAvGratuite);
	    
	    JLabel lblStockAvoir = new JLabel("Stock Avoir:");
	    lblStockAvoir.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblStockAvoir.setBounds(938, 793, 95, 24);
	    add(lblStockAvoir);
	    
	     labelstockavoir = new JLabel("");
	     labelstockavoir.setForeground(Color.GREEN);
	    labelstockavoir.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelstockavoir.setBounds(1043, 793, 130, 24);
	    add(labelstockavoir);
	    
	    JLabel lblAvoirEmballage = new JLabel("Avoir Emballage:");
	    lblAvoirEmballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblAvoirEmballage.setBounds(938, 828, 123, 24);
	    add(lblAvoirEmballage);
	    
	     labelavoiremballage = new JLabel("");
	     labelavoiremballage.setForeground(Color.GREEN);
	    labelavoiremballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelavoiremballage.setBounds(1071, 828, 130, 24);
	    add(labelavoiremballage);
	    
	     labelavoirsansemballage = new JLabel("");
	     labelavoirsansemballage.setForeground(Color.GREEN);
	    labelavoirsansemballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelavoirsansemballage.setBounds(1096, 858, 130, 24);
	    add(labelavoirsansemballage);
	    
	    JLabel lblAvoirSansEmballage = new JLabel("Avoir Sans Emballage:");
	    lblAvoirSansEmballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblAvoirSansEmballage.setBounds(938, 858, 161, 24);
	    add(lblAvoirSansEmballage);
	    
	    JLabel lblStockFinale = new JLabel("Stock Finale");
	    lblStockFinale.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblStockFinale.setBounds(1244, 793, 95, 24);
	    add(lblStockFinale);
	    
	     labelstockfinale = new JLabel("");
	     labelstockfinale.setForeground(Color.GREEN);
	    labelstockfinale.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelstockfinale.setBounds(1349, 793, 194, 24);
	    add(labelstockfinale);
	    
	    JLabel lblFinaleEmballage = new JLabel("Finale  Emballage:");
	    lblFinaleEmballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblFinaleEmballage.setBounds(1244, 828, 136, 24);
	    add(lblFinaleEmballage);
	    
	     labelfinaleemballage = new JLabel("");
	     labelfinaleemballage.setForeground(Color.GREEN);
	    labelfinaleemballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelfinaleemballage.setBounds(1390, 828, 153, 24);
	    add(labelfinaleemballage);
	    
	     labelfinalesansemballage = new JLabel("");
	     labelfinalesansemballage.setForeground(Color.GREEN);
	    labelfinalesansemballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    labelfinalesansemballage.setBounds(1402, 858, 151, 24);
	    add(labelfinalesansemballage);
	    
	    JLabel lblFinaleSansEmballage = new JLabel("Finale Sans Emballage:");
	    lblFinaleSansEmballage.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblFinaleSansEmballage.setBounds(1244, 858, 161, 24);
	    add(lblFinaleSansEmballage);
	 
	    
	    
	    
	    
	    
	    
	  
	    if(utilisateur.getLogin().equals("admin"))
			  {
		    	listMagasin =depotdao.listeMagasinByTypeMagasin(MAGASIN_CODE_TYPE_MP);
				  int k=0;
			     	 combodepot.addItem("");
			     	while (k<listMagasin.size())
			     	{
			     		Magasin magasin=listMagasin.get(k);
			     		
			     		
				     			combodepot.addItem(magasin.getLibelle());
					     		
					     		mapMagasin.put(magasin.getLibelle(), magasin);
					     	
			     		k++;
			     		
			     	}
			      
			  }else
			  {
				  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
				  if(depot!=null)
				  {
					  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_MP);
					  int k=0;
				     	 combodepot.addItem("");
				     	while (k<listMagasin.size())
				     	{
				     		Magasin magasin=listMagasin.get(k);
				     		
				     		
					     			combodepot.addItem(magasin.getLibelle());
						     		
						     		mapMagasin.put(magasin.getLibelle(), magasin);
						     	
				     		k++;
				     		
				     	}
					 
				  }
			  }
	    
	    
	      Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	      listMagasinPF=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
	      
for(int j=0;j<listMagasinPF.size();j++)
{
	Magasin magasin=listMagasinPF.get(j);
	ComboMagasinPF.addItem(magasin.getLibelle());
	mapMagasinPF.put(magasin.getLibelle(), magasin);
}

	    
	    
	    
	    
	    
	    
	    
	    
		
		}
	
	

	
	
	
	void InitialiseTableauDetailMouvementStock()
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Matiere Premiere", "Quantite Initial", "Prix Initial","Montant Initial", "Quantite Achat", "Prix Achat","Montant Achat","Quantite Sortie", "Prix Sortie","Montant Sortie","Quantite Dechet", "Prix Dechet","Montant Dechet","Quantite Offre", "Prix Offre","Montant Offre","Quantite Service", "Prix Service","Montant Service","Quantite Dechet Service", "Prix Dechet Service","Montant Dechet Service","Quantite Offre Service", "Prix Offre Service","Montant Offre Service" ,"Quantite Avoir", "Prix Avoir","Montant Avoir","Quantite Finale", "Prix Finale","Montant Finale",
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modeleEtatStock);
		 tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
	       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
	       tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
	       tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
	       tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
	       tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(118);
	       tableEtatStock.getColumnModel().getColumn(6).setPreferredWidth(132);
	       tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(92);
	       tableEtatStock.getColumnModel().getColumn(8).setPreferredWidth(95);
	       tableEtatStock.getColumnModel().getColumn(9).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(10).setPreferredWidth(92);
	       tableEtatStock.getColumnModel().getColumn(11).setPreferredWidth(95);
	       tableEtatStock.getColumnModel().getColumn(12).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(13).setPreferredWidth(92);
	       tableEtatStock.getColumnModel().getColumn(14).setPreferredWidth(95);
	       tableEtatStock.getColumnModel().getColumn(15).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(16).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(17).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(18).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(19).setPreferredWidth(92);
	       tableEtatStock.getColumnModel().getColumn(20).setPreferredWidth(95);
	       tableEtatStock.getColumnModel().getColumn(21).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(22).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(23).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(24).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(25).setPreferredWidth(92);
	       tableEtatStock.getColumnModel().getColumn(26).setPreferredWidth(95);
	      
	     
}
	
	
/*	void InitialiseTableauMouvementStock()
	{
		modeleMouvementStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date Mouvement", "Depot", "Magasin"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modeleMouvementStock);
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		
		
	
}*/
	
	
	
	void afficher_tableEtatStock(List<EtatValeurStock> listEtatStockMP)
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article","Famille", "Sous Famille", "Quantite Initial", "Prix Initial","Montant Initial", "Quantite Achat", "Prix Achat","Montant Achat","Quantite Production Entrer", "Prix Production Entrer","Montant Production Entrer","Quantite Production Sortie", "Prix Production Sortie","Montant Production Sortie","Quantite Vente", "Prix Vente","Montant Vente","Quantite Gratuite", "Prix Gratuite","Montant Gratuite","Quantite Avoir", "Prix Avoir","Montant Avoir","Quantite Finale", "Prix Finale","Montant Finale",
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modeleEtatStock);
		int i=0;
		 
		while(i<listEtatStockMP.size())
		{	
		EtatValeurStock EtatValeurStock=listEtatStockMP.get(i);
		
			
				//Object []ligne={EtatStockMP.getMp().getNom(),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteInitial().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixInitial().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantInitial().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteAchat().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixAchat().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantAchat().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteSortie().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixSortie().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantSortie().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteDechet().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixDechet().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantDechet().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteOffreProduction().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixOffreProduction().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantOffreProduction().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteDechetService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixDechetService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantDechetService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteOffreService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixOffreService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantOffreService().setScale(6, RoundingMode.HALF_UP)), NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteAvoir().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixAvoir().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantAvoir().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteFinale().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixFinale().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantFinale().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMarge().setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP)))+"%"};
				Object []ligne={EtatValeurStock.getArticle() , EtatValeurStock.getFamille() , EtatValeurStock.getSousfamille(),EtatValeurStock.getQuantiteInitial(),EtatValeurStock.getPrixInitial(),EtatValeurStock.getMontantInitial(),EtatValeurStock.getQuantiteAchat(),EtatValeurStock.getPrixAchat(),EtatValeurStock.getMontantAchat(),EtatValeurStock.getQuantiteProductionEntrer(),EtatValeurStock.getPrixProductionEntrer(),EtatValeurStock.getMontantProductionEntrer(),EtatValeurStock.getQuantiteProductionSortie(),EtatValeurStock.getPrixProductionSortie(),EtatValeurStock.getMontantProductionSortie(),EtatValeurStock.getQuantiteVente(),EtatValeurStock.getPrixVente(),EtatValeurStock.getMontantVente(),EtatValeurStock.getQuantiteGratuite(),EtatValeurStock.getPrixGratuite(),EtatValeurStock.getMontantGratuite(),EtatValeurStock.getQuantiteAvoir(),EtatValeurStock.getPrixAvoir(),EtatValeurStock.getMontantAvoir(),EtatValeurStock.getQuantiteFinale(),EtatValeurStock.getPrixFinale(),EtatValeurStock.getMontantFinale()};

				modeleEtatStock.addRow(ligne);
				

			
			i++;
		}
}
	
	
	
	
	
	
	
public void CalculerPrixMoyenFamille(  Magasin magasin)
{
	

	
	detailTransferStockPFDAO.ViderSession();
	
	
	listEtatStockPF.clear();
	 List<DetailTransferProduitFini> listDetailTransferStockPFInitial =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAchat =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAchatGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFProduction =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFEntrer =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerProduction =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirMarajne =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirMarajneGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFAvoir =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAllPFTransfer =new ArrayList<DetailTransferProduitFini>();
	

	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	BigDecimal montantInitial=new BigDecimal(0);
	BigDecimal quantiteTotalInitial=new BigDecimal(0);
	BigDecimal PrixInitial=new BigDecimal(0);
	BigDecimal montantachat=new BigDecimal(0);
	BigDecimal quantiteTotalachat=new BigDecimal(0);
	BigDecimal quantiteTotalvente=new BigDecimal(0);
	BigDecimal montantvente=new BigDecimal(0);
	BigDecimal quantiteTotalavoir=new BigDecimal(0);
	BigDecimal montantavoir=new BigDecimal(0);
	BigDecimal quantiteTotalEntrer=new BigDecimal(0);
	BigDecimal montantEntrer=new BigDecimal(0);
	BigDecimal quantiteTotalGratuit=new BigDecimal(0);
	BigDecimal montantGratuit=new BigDecimal(0);
	BigDecimal quantiteTotalproduction=new BigDecimal(0);
	BigDecimal prixmoyenproduction=new BigDecimal(0);
	BigDecimal prixmoyenachat=new BigDecimal(0);
	BigDecimal prixmoyenvente=new BigDecimal(0);
	BigDecimal prixmoyentransferentrer=new BigDecimal(0);
	BigDecimal prixmoyenavoir=new BigDecimal(0);
	BigDecimal montantproduction=new BigDecimal(0);
	
	BigDecimal quantiteTotalFinale=new BigDecimal(0);
	BigDecimal montantFinale=new BigDecimal(0);
	boolean trouve=false;
	FamilleArticlePF familleArticle=null;
	Articles article=null;

	
	
	
	if(magasin!=null)
	{
		
		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
		{
			
			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			String d2=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
			
			
		if(!d1.equals(d2))
		{
			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
			{
				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!");
				return;
			}
			
		}
		
	
		
		}
		
				    		
		if(dateChooserdebut.getDate()==null )
		{
			dateChooserfin.setCalendar(null);
		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article!=null)
		{
			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			
		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article==null)
		{
			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
		}
		
		listDetailTransferStockPFInitial=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_INITIAL,magasin, familleArticle);
		
		listDetailTransferStockPFAchat=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
		listDetailTransferStockPFAchatGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
		
		listDetailTransferStockPFProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
		listDetailTransferStockPFProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
		
		
		listDetailTransferStockPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		
		

		listDetailTransferStockPFEntrerProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
		listDetailTransferStockPFEntrerProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
		
		listDetailTransferStockPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_PF_MP,magasin, familleArticle);
		listDetailTransferStockPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_PF_MP,magasin, familleArticle);
		
		
		
		
		listDetailTransferStockPFAvoir=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR,magasin, familleArticle);
		listDetailTransferStockPFAvoirGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR,magasin, familleArticle);
		
		listDetailTransferStockPFAvoirMarajne=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR_R,magasin, familleArticle);
		listDetailTransferStockPFAvoirMarajneGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR_R,magasin, familleArticle);
		
		listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);
		
		for(int d=0;d<listDetailTransferStockPFAllPFTransfer.size();d++)
		{
			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFAllPFTransfer.get(d);
			EtatStockPF etatstock=new EtatStockPF();
			etatstock.setArticle(detailtransferstockpf.getArticle());
			etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
			etatstock.setFamilleArticle(detailtransferstockpf.getSousFamille().getFamileArticlePF());
			etatstock.setQuantiteInitial(BigDecimal.ZERO);
			etatstock.setPrixInitial(BigDecimal.ZERO);
			etatstock.setMontantInitial(BigDecimal.ZERO);
			etatstock.setQuantiteAchat(BigDecimal.ZERO);
			etatstock.setPrixAchat(BigDecimal.ZERO);
			etatstock.setMontantAchat(BigDecimal.ZERO);
			etatstock.setQuantiteProduction(BigDecimal.ZERO);
			etatstock.setPrixProduction(BigDecimal.ZERO);
			etatstock.setMontantProduction(BigDecimal.ZERO);
			etatstock.setQuantiteSortie(BigDecimal.ZERO);
			etatstock.setPrixSortie(BigDecimal.ZERO);
			etatstock.setMontantSortie(BigDecimal.ZERO);
			etatstock.setQuantiteEntrer(BigDecimal.ZERO);
			etatstock.setPrixEntrer(BigDecimal.ZERO);
			etatstock.setMontantEntrer(BigDecimal.ZERO);
			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
			etatstock.setPrixAvoir(BigDecimal.ZERO);
			etatstock.setMontantAvoir(BigDecimal.ZERO);
			etatstock.setMarge(BigDecimal.ZERO);
			etatstock.setQuantiteGratuit(BigDecimal.ZERO);
			etatstock.setPrixGratuit(BigDecimal.ZERO);
			etatstock.setMontantGratuit(BigDecimal.ZERO);
			etatstock.setQuantiteFinale(BigDecimal.ZERO);
			etatstock.setPrixFinale(BigDecimal.ZERO);
			etatstock.setMontantFinale(BigDecimal.ZERO);
			listEtatStockPF.add(etatstock);
			
		}
		
		// charger list Etat stock Articles initialiser les enregistrement des achats et ventes par zero
		for(int i=0;i<listDetailTransferStockPFInitial.size();i++)
		{
			
			
			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFInitial.get(i);
			/*
			EtatStockPF etatstock=new EtatStockPF();
			etatstock.setArticle(detailtransferstockpf.getArticle());
			etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
			etatstock.setQuantiteInitial(detailtransferstockpf.getQuantite());
			*/
		
				
			
				PrixInitial=detailtransferstockpf.getPrixUnitaire();
			
				montantInitial=detailtransferstockpf.getQuantite().multiply(detailtransferstockpf.getPrixUnitaire());
			
			   quantiteTotalInitial=detailtransferstockpf.getQuantite();
			
			/*
			etatstock.setQuantiteAchat(BigDecimal.ZERO);
			etatstock.setPrixAchat(BigDecimal.ZERO);
			etatstock.setMontantAchat(BigDecimal.ZERO);
			etatstock.setQuantiteProduction(BigDecimal.ZERO);
			etatstock.setPrixProduction(BigDecimal.ZERO);
			etatstock.setMontantProduction(BigDecimal.ZERO);
			etatstock.setQuantiteSortie(BigDecimal.ZERO);
			etatstock.setPrixSortie(BigDecimal.ZERO);
			etatstock.setMontantSortie(BigDecimal.ZERO);
			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
			etatstock.setPrixAvoir(BigDecimal.ZERO);
			etatstock.setMontantAvoir(BigDecimal.ZERO);
			
			listEtatStockPF.add(etatstock);
			*/
			if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
    		{
    			
    			for(int p=0;p<listEtatStockPF.size();p++)
    	    	{
    				if(listEtatStockPF.get(p).getArticle().equals(listDetailTransferStockPFInitial.get(i).getArticle()) && listEtatStockPF.get(p).getSousFamille().equals(listDetailTransferStockPFInitial.get(i).getSousFamille()) )
	    			{
	    				EtatStockPF etatstockpf=listEtatStockPF.get(p);
	    				etatstockpf.setQuantiteInitial(quantiteTotalInitial);
	    				etatstockpf.setPrixInitial(PrixInitial);
	    				etatstockpf.setMontantInitial(montantInitial);
	    				listEtatStockPF.set(p, etatstockpf);
	    			
	    				
	    			}
    	    	}
    			
    			
    			
    		}
			
			
			
		}
		
		
		// calculer le prix moyen et quantite achat
		
		
	for(int j=0;j<listDetailTransferStockPFAchatGroupebyPF.size();j++)
	{
		montantachat=new BigDecimal(0);
		quantiteTotalachat=new BigDecimal(0);
		prixmoyenachat= new BigDecimal(0);
		
		boolean existe=false;
			
	for(int k=0;k<listDetailTransferStockPFAchat.size();k++)
	{
		
		if(listDetailTransferStockPFAchatGroupebyPF.get(j).getArticle().equals(listDetailTransferStockPFAchat.get(k).getArticle()) && listDetailTransferStockPFAchatGroupebyPF.get(j).getSousFamille().equals(listDetailTransferStockPFAchat.get(k).getSousFamille()))
		{
			montantachat=montantachat.add(listDetailTransferStockPFAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFAchat.get(k).getQuantite()));
			prixmoyenachat=((prixmoyenachat.multiply(quantiteTotalachat)).add(listDetailTransferStockPFAchat.get(k).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFAchat.get(k).getQuantite()))).divide(quantiteTotalachat.add(listDetailTransferStockPFAchat.get(k).getQuantite()),6, RoundingMode.DOWN) ;

			quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockPFAchat.get(k).getQuantite());
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
		{
			
			for(int i=0;i<listEtatStockPF.size();i++)
	    	{
				if(listEtatStockPF.get(i).getArticle().equals(listDetailTransferStockPFAchatGroupebyPF.get(j).getArticle()) && listEtatStockPF.get(i).getSousFamille().equals(listDetailTransferStockPFAchatGroupebyPF.get(j).getSousFamille()) )
    			{
    				EtatStockPF etatstockpf=listEtatStockPF.get(i);
    				etatstockpf.setQuantiteAchat(quantiteTotalachat);
    				etatstockpf.setPrixAchat((prixmoyenachat));
    				etatstockpf.setMontantAchat(etatstockpf.getQuantiteAchat().multiply(etatstockpf.getPrixAchat()));
    				listEtatStockPF.set(i, etatstockpf);
    			
    				
    			}
	    	}
			
			
			
		}
		
	}
	
	
// calculer Quantite Production et le prix moyen
	

	
	for(int i=0;i<listDetailTransferStockPFProductionGroupebyPF.size();i++)
	{
		quantiteTotalproduction=BigDecimal.ZERO;
		montantproduction=new BigDecimal(0);
		prixmoyenproduction=BigDecimal.ZERO;
		
		for(int j=0;j<listDetailTransferStockPFProduction.size();j++)
		{
			
			if(listDetailTransferStockPFProductionGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFProduction.get(j).getArticle()) && listDetailTransferStockPFProductionGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFProduction.get(j).getSousFamille()))
			{
				
				montantproduction=montantproduction.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPFProduction.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFProduction.get(j).getQuantite())).setScale(6, RoundingMode.DOWN);
				
				prixmoyenproduction=((prixmoyenproduction.multiply(quantiteTotalproduction)).add(listDetailTransferStockPFProduction.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFProduction.get(j).getQuantite()))).divide(quantiteTotalproduction.add(listDetailTransferStockPFProduction.get(j).getQuantite()),6, RoundingMode.DOWN) ;
				quantiteTotalproduction=quantiteTotalproduction.add(listDetailTransferStockPFProduction.get(j).getQuantite());
				
			
				
			}
			
			
		}
		
		if(!montantproduction.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !quantiteTotalproduction.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFProductionGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFProductionGroupebyPF.get(i).getSousFamille()))
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteProduction(quantiteTotalproduction);
	    			etatstockpf.setPrixProduction(prixmoyenproduction);
	    			etatstockpf.setMontantProduction(quantiteTotalproduction.multiply(prixmoyenproduction));
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    			

	    			
	    		}
	    		
	    		
	    	}
			
		}
		
		
		
	}
	
	
	
	
	// calculer Quantite Vente et le prix moyen
	
	
	
	for(int i=0;i<listDetailTransferStockPFSortieGroupebyPF.size();i++)
	{
		quantiteTotalvente=BigDecimal.ZERO;
		quantiteTotalGratuit=BigDecimal.ZERO;
		montantvente=new BigDecimal(0);
		prixmoyenvente=new BigDecimal(0);
		
		for(int j=0;j<listDetailTransferStockPFSortie.size();j++)
		{
			
			if(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFSortie.get(j).getArticle()) && listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFSortie.get(j).getSousFamille()))
			{
				
				
				// la quantite Gratuité
				if(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
				{
					
					quantiteTotalGratuit=quantiteTotalGratuit.add(listDetailTransferStockPFSortie.get(j).getQuantite());
					
					
					
				}else
				{
					
					// la quantite Vente
					
					montantvente=montantvente.add(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFSortie.get(j).getQuantite()));
    				prixmoyenvente=((prixmoyenvente.multiply(quantiteTotalvente)).add(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFSortie.get(j).getQuantite()))).divide(quantiteTotalvente.add(listDetailTransferStockPFSortie.get(j).getQuantite()),6, RoundingMode.DOWN) ;

					quantiteTotalvente=quantiteTotalvente.add(listDetailTransferStockPFSortie.get(j).getQuantite());
					
					
				}
				
				
			}
			
			
		}
		
		if(!quantiteTotalvente.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille()))
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteSortie(quantiteTotalvente);
	    			etatstockpf.setPrixSortie(prixmoyenvente);
	    			etatstockpf.setMontantSortie(quantiteTotalvente.multiply(prixmoyenvente));
	    		
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    		
	    	}
			
			
		}
		
		
		if(!quantiteTotalGratuit.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille()))
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteGratuit(quantiteTotalGratuit.setScale(6));
	    			if(etatstockpf.getPrixAchat().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
	    			{
	    				etatstockpf.setPrixGratuit(etatstockpf.getPrixInitial().setScale(6));
	    			}else
	    			{
	    				etatstockpf.setPrixGratuit(etatstockpf.getPrixAchat().setScale(6));
	    			}
	    			
	    			etatstockpf.setMontantGratuit(etatstockpf.getQuantiteGratuit().setScale(6).multiply(etatstockpf.getPrixGratuit().setScale(6)));;
	    			
	    			 // ajouter la quantite gratuité et le prix gratuité(prix d'achat)
	    			
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    	}
			
			
		}
		
		
	}
	
// calculer Quantite Transfer Entrer et le prix moyen les MP Transfert EN PF vien de Production
	
	
	
	for(int i=0;i<listDetailTransferStockPFEntrerProductionGroupebyPF.size();i++)
	{
		quantiteTotalEntrer=BigDecimal.ZERO;
		montantEntrer=new BigDecimal(0);
		prixmoyentransferentrer=new BigDecimal(0);
		
		for(int j=0;j<listDetailTransferStockPFEntrerProduction.size();j++)
		{
			
			if(listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFEntrerProduction.get(j).getArticle().getId() && listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getSousFamille().getId()==listDetailTransferStockPFEntrerProduction.get(j).getSousFamille().getId())
			{
				
				montantEntrer=montantEntrer.add(listDetailTransferStockPFEntrerProduction.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFEntrerProduction.get(j).getQuantite()));
				prixmoyentransferentrer=((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(listDetailTransferStockPFEntrerProduction.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFEntrerProduction.get(j).getQuantite()))).divide(quantiteTotalEntrer.add(listDetailTransferStockPFEntrerProduction.get(j).getQuantite()),6, RoundingMode.DOWN) ;

				quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrerProduction.get(j).getQuantite());
				
				
			}
			
			
		}
		
		if(!quantiteTotalEntrer.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getSousFamille().getId())
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteEntrer(quantiteTotalEntrer);
	    			etatstockpf.setPrixEntrer(prixmoyentransferentrer);
	    			etatstockpf.setMontantEntrer(quantiteTotalEntrer.multiply(prixmoyentransferentrer));
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    		
	    	}
			
			
		}
		
	}
	
	
// calculer Quantite Transfer Entrer et le prix moyen (Les MP transfer En PF )
	
	
	
	for(int i=0;i<listDetailTransferStockPFEntrerGroupebyPF.size();i++)
	{
		quantiteTotalEntrer=BigDecimal.ZERO;
		montantEntrer=new BigDecimal(0);
		prixmoyentransferentrer=new BigDecimal(0);
		
		for(int j=0;j<listDetailTransferStockPFEntrer.size();j++)
		{
			
			if(listDetailTransferStockPFEntrerGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFEntrer.get(j).getArticle().getId() && listDetailTransferStockPFEntrerGroupebyPF.get(i).getSousFamille().getId()== listDetailTransferStockPFEntrer.get(j).getSousFamille().getId())
			{
				
				montantEntrer=montantEntrer.add(listDetailTransferStockPFEntrer.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFEntrer.get(j).getQuantite()));
				prixmoyentransferentrer=((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(listDetailTransferStockPFEntrer.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFEntrer.get(j).getQuantite()))).divide(quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(j).getQuantite()),6, RoundingMode.DOWN) ;

				quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(j).getQuantite());
				
				
			}
			
			
		}
		
		if(!quantiteTotalEntrer.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFEntrerGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFEntrerGroupebyPF.get(i).getSousFamille().getId())
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteEntrer(quantiteTotalEntrer);
	    			etatstockpf.setPrixEntrer(prixmoyentransferentrer);
	    			etatstockpf.setMontantEntrer(quantiteTotalEntrer.multiply(prixmoyentransferentrer));
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    		
	    	}
			
			
		}
		
	}    	
	
	
	
	
	
	
	
// calculer Quantite avoir et le prix moyen
	
	
	
	for(int i=0;i<listDetailTransferStockPFAvoirGroupebyPF.size();i++)
	{
		quantiteTotalavoir=BigDecimal.ZERO;
		montantavoir=new BigDecimal(0);
		prixmoyenavoir=BigDecimal.ZERO;
		for(int j=0;j<listDetailTransferStockPFAvoir.size();j++)
		{
			
			if(listDetailTransferStockPFAvoirGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFAvoir.get(j).getArticle().getId() && listDetailTransferStockPFAvoirGroupebyPF.get(i).getSousFamille().getId()== listDetailTransferStockPFAvoir.get(j).getSousFamille().getId() && listDetailTransferStockPFAvoir.get(j).getSousFamille().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_EMBALLAGE))
			{
				
				montantavoir=montantavoir.add(listDetailTransferStockPFAvoir.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoir.get(j).getQuantite()));
				prixmoyenavoir=((prixmoyenavoir.multiply(quantiteTotalavoir)).add(listDetailTransferStockPFAvoir.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFAvoir.get(j).getQuantite()))).divide(quantiteTotalavoir.add(listDetailTransferStockPFAvoir.get(j).getQuantite()),6, RoundingMode.DOWN) ;

				quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockPFAvoir.get(j).getQuantite());
				
				
			}
			
			
		}
		
		if(!quantiteTotalavoir.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFAvoirGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFAvoirGroupebyPF.get(i).getSousFamille().getId() && listEtatStockPF.get(k).getSousFamille().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_EMBALLAGE))
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteAvoir(quantiteTotalavoir);
	    			etatstockpf.setPrixAvoir(prixmoyenavoir);
	    			etatstockpf.setMontantAvoir(quantiteTotalavoir.multiply(prixmoyenavoir));
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    		
	    	}
			
			
		}
		
		
		
	}
	
	
// calculer Quantite avoir Marjane et le prix moyen
	
	
	
	for(int i=0;i<listDetailTransferStockPFAvoirMarajneGroupebyPF.size();i++)
	{
		quantiteTotalavoir=BigDecimal.ZERO;
		montantavoir=new BigDecimal(0);
		prixmoyenavoir=BigDecimal.ZERO;
		for(int j=0;j<listDetailTransferStockPFAvoirMarajne.size();j++)
		{
			
			if(listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFAvoirMarajne.get(j).getArticle()) && listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFAvoirMarajne.get(j).getSousFamille()) && listDetailTransferStockPFAvoirMarajne.get(j).getTransferStockPF().getCodeTransfer().contains("_R"))
			{
				
				montantavoir=montantavoir.add(listDetailTransferStockPFAvoirMarajne.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite()));
				prixmoyenavoir=((prixmoyenavoir.multiply(quantiteTotalavoir)).add(listDetailTransferStockPFAvoirMarajne.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite()))).divide(quantiteTotalavoir.add(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite()),6, RoundingMode.DOWN) ;

				quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite());
				
				
			}
			
			
		}
		
		if(!quantiteTotalavoir.equals(BigDecimal.ZERO))
		{
			
			
		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	{
	    		
	    	
	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getSousFamille()) )
	    		{
	    			
	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
	    			etatstockpf.setQuantiteAvoir(quantiteTotalavoir);
	    			etatstockpf.setPrixAvoir(prixmoyenavoir);
	    			etatstockpf.setMontantAvoir(quantiteTotalavoir.multiply(prixmoyenavoir));
	    			
	    			listEtatStockPF.set(k, etatstockpf);
	    		}
	    		
	    		
	    	}
			
			
		}
		
		
		
	} 	
	
	
	
	
	
	
	Calculer_Prix_Moyenne_PF(); // Calculer le Prix Moyen a partir de mouvement de stock 
	
	
	// Calculer Stock Finale
	
	
   	for(int i=0;i<listEtatStockPF.size();i++)
	{
   	 BigDecimal prixMoyen=BigDecimal.ZERO;
   	 BigDecimal QuantiteTotal=BigDecimal.ZERO;
   		   quantiteTotalFinale=BigDecimal.ZERO;
   		    montantFinale=BigDecimal.ZERO;
			EtatStockPF etatstockpf=listEtatStockPF.get(i);
			etatstockpf.setQuantiteFinale((etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer()))).subtract(etatstockpf.getQuantiteSortie().add(etatstockpf.getQuantiteAvoir()).add(etatstockpf.getQuantiteGratuit())));
			quantiteTotalFinale=quantiteTotalFinale.add(etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer())).subtract(etatstockpf.getQuantiteAvoir().add(etatstockpf.getQuantiteSortie()).add(etatstockpf.getQuantiteGratuit())));
			montantFinale=etatstockpf.getMontantInitial().add(etatstockpf.getMontantAchat().add(etatstockpf.getMontantProduction().add(etatstockpf.getMontantEntrer())));
			QuantiteTotal=etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction().add(etatstockpf.getQuantiteEntrer())));
			/*
			 * if(!QuantiteTotal.setScale(2,
			 * RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2,
			 * RoundingMode.HALF_UP))) { prixMoyen=montantFinale.divide(QuantiteTotal, 6,
			 * RoundingMode.DOWN); etatstockpf.setPrixFinale(prixMoyen.setScale(6,
			 * RoundingMode.DOWN));
			 * etatstockpf.setMontantFinale(etatstockpf.getPrixFinale().multiply(etatstockpf
			 * .getQuantiteFinale())); }else {
			 * etatstockpf.setPrixFinale(prixMoyen.setScale(6, RoundingMode.DOWN));
			 * 
			 * }
			 */
			
			if(listMouvementStockPFAfficherTmp.size()!=0)
    		{
    			for(int l=0;l<listMouvementStockPFAfficherTmp.size();l++)
    			{
    				
    				
    				
    				if(etatstockpf.getArticle().getId() ==listMouvementStockPFAfficherTmp.get(l).getArticle().getId() && etatstockpf.getSousFamille().getId()==listMouvementStockPFAfficherTmp.get(l).getSousFamille().getId())
    				{
    					
    				
    					
    					prixMoyen=listMouvementStockPFAfficherTmp.get(l).getPrixFinal();
    					//System.out.println(listMouvementStockPFAfficherTmp.get(l).getArticle().getLiblle() + listMouvementStockPFAfficherTmp.get(l).getSousFamille().getLiblle() +" : Initial "+listMouvementStockPFAfficherTmp.get(l).getInitial() + " Prix Initial: "+listMouvementStockPFAfficherTmp.get(l).getPrixInitial()+ " Achat : "+listMouvementStockPFAfficherTmp.get(l).getAchat() + " prix Achat : "+listMouvementStockPFAfficherTmp.get(l).getPrixAchat()+ " Production : "+listMouvementStockPFAfficherTmp.get(l).getEntrerProduction() + " Transfer Enter : "+listMouvementStockPFAfficherTmp.get(l).getTransferEntrer() + " Vente : "+listMouvementStockPFAfficherTmp.get(l).getVente() + " Quantite Finale : "+listMouvementStockPFAfficherTmp.get(l).getStockFinal() + "  prix Moyen : "+listMouvementStockPFAfficherTmp.get(l).getPrixFinal() ) ;
    					
    				}
    				
    			}
    			
    		}else
    		{
    			for(int l=0;l<listMouvementStockPFAfficher.size();l++)
    			{
    				if(etatstockpf.getArticle().getId() ==listMouvementStockPFAfficher.get(l).getArticle().getId() && etatstockpf.getSousFamille().getId()==listMouvementStockPFAfficher.get(l).getSousFamille().getId())
    				{
    					
    				
    					
    					prixMoyen=listMouvementStockPFAfficher.get(l).getPrixFinal();
    					//System.out.println(listMouvementStockPFAfficher.get(l).getArticle().getLiblle() + listMouvementStockPFAfficher.get(l).getSousFamille().getLiblle() +" : Initial "+listMouvementStockPFAfficher.get(l).getInitial() + " Prix Initial: "+listMouvementStockPFAfficher.get(l).getPrixInitial()+ " Achat : "+listMouvementStockPFAfficher.get(l).getAchat() + " prix Achat : "+listMouvementStockPFAfficher.get(l).getPrixAchat()+ " Production : "+listMouvementStockPFAfficher.get(l).getEntrerProduction() + " Transfer Enter : "+listMouvementStockPFAfficher.get(l).getTransferEntrer()+ " Vente : "+listMouvementStockPFAfficher.get(l).getVente() + " Quantite Finale : "+listMouvementStockPFAfficher.get(l).getStockFinal() + "  prix Moyen : "+listMouvementStockPFAfficher.get(l).getPrixFinal() ) ;
    					
    				}
    			}
    			
    		}
			
			
			if(etatstockpf.getQuantiteFinale().compareTo(BigDecimal.ZERO)==0)
			{
				prixMoyen=BigDecimal.ZERO;
			}
			
			etatstockpf.setPrixFinale(prixMoyen);
    		etatstockpf.setMontantFinale(((etatstockpf.getQuantiteFinale())).multiply(etatstockpf.getPrixFinale()));
		
			
			
			if(etatstockpf.getPrixSortie().setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,  RoundingMode.DOWN)))
			{
				etatstockpf.setMarge(BigDecimal.ZERO);
				
			}else
			{
				etatstockpf.setMarge((etatstockpf.getPrixSortie().subtract(etatstockpf.getPrixInitial())).divide(etatstockpf.getPrixSortie(), RoundingMode.DOWN));
			}
			
			
			listEtatStockPF.set(i, etatstockpf);
		
		
	}
   	

   	
   
   	
   	
	}else
	{
		

		JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}
	

	
	
 	
	
	
}
	
	
	
	
	
	
	
	
void Calculer_Prix_Moyenne_PF()
{
	

	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	listMouvementStockPF.clear();
	detailTransferStockPFDAO.ViderSession();
	boolean trouve=false;
	Articles article=null;
	Magasin magasin=mapMagasinPF.get(ComboMagasinPF.getSelectedItem());
	if(magasin!=null)
	{
		
   		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
		{
			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			String d2=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
			
		if(!d1.equals(d2))
		{
			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
			{
				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!");
				return;
			}
			
		}
		
		if(article!=null)
		{
			titre="Mouvement de Stock de "+article.getLiblle() +" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
		}else
		{
			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
		}
		
		}
		
		if(dateChooserdebut.getDate()==null )
		{
			dateChooserfin.setCalendar(null);
			titre="Mouvement de Stock de "+article.getLiblle()+" au magasin : "+magasin.getLibelle();
		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article!=null)
		{
			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			titre="Mouvement de Stock de "+article.getLiblle() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
			
		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article==null)
		{
			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ "entre "+d1 +" et "+d1;
		}
		
		listDetailTransferStockPF=detailTransferStockPFDAO.findAllTransferStockPFOrderByDateTransfer(magasin);
		listDetailTransferStockPFGroupebyArticle=detailTransferStockPFDAO.findAllTransferStockPFGroupeByDateTransferByArticle(magasin);
		listDetailTransferStockPFBytypetransfer=detailTransferStockPFDAO.findBytypetransfer(ETAT_TRANSFER_STOCK_INITIAL,magasin);
		String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_VENTE,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,Constantes.ETAT_TRANSFER_STOCK_AVOIR ,ETAT_TRANSFER_STOCK_ENTRER_MP,ETAT_TRANSFER_STOCK_ENTRER_PF_MP};
		BigDecimal achat=BigDecimal.ZERO;
		BigDecimal Prixachat=BigDecimal.ZERO;
		BigDecimal vente=BigDecimal.ZERO;
		BigDecimal TransferEntrer=BigDecimal.ZERO;
		BigDecimal PrixTransferEntrer=BigDecimal.ZERO;
		BigDecimal avoir=BigDecimal.ZERO;
		BigDecimal initial=BigDecimal.ZERO;
		BigDecimal prixinitial=BigDecimal.ZERO;
		BigDecimal production=BigDecimal.ZERO;
		BigDecimal Prixproduction=BigDecimal.ZERO;
		BigDecimal stockfinal=BigDecimal.ZERO;
		BigDecimal prixmoyenne=BigDecimal.ZERO;
		BigDecimal quantitefinale=BigDecimal.ZERO;
		for(int i=0;i<listDetailTransferStockPFGroupebyArticle.size();i++)
		{
			
			achat=new BigDecimal(0);
			vente=new BigDecimal(0);
			initial=new BigDecimal(0);
			production=new BigDecimal(0);
			stockfinal=new BigDecimal(0);
			avoir=new BigDecimal(0);
			TransferEntrer=new BigDecimal(0);
			Prixachat=new BigDecimal(0);
			 PrixTransferEntrer=new BigDecimal(0);
			 prixinitial=new BigDecimal(0);
			 Prixproduction=new BigDecimal(0);
			
			
				if(i!=0)
    			{
    				if(listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() != listDetailTransferStockPFGroupebyArticle.get(i-1).getArticle().getId())
    				{
    					prixmoyenne=new BigDecimal(0);
		    			quantitefinale=new BigDecimal(0);
    					
    				}
    				if(listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() == listDetailTransferStockPFGroupebyArticle.get(i-1).getArticle().getId())
    				{
    				if( listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId() != listDetailTransferStockPFGroupebyArticle.get(i-1).getSousFamille().getId())
    				{
    					prixmoyenne=new BigDecimal(0);
		    			quantitefinale=new BigDecimal(0);
    				}
    				}
    				
    				
    			}else
    			{
    				prixmoyenne=new BigDecimal(0);
	    			quantitefinale=new BigDecimal(0);
    			}
			 
			 
			 
			for(int j=0;j<listDetailTransferStockPF.size();j++)
			{
				

				for(int k=0;k<typetransfer.length;k++)
    			{
					
				
			if(listDetailTransferStockPFGroupebyArticle.get(i).getTransferStockPF().getDateTransfer().equals(listDetailTransferStockPF.get(j).getTransferStockPF().getDateTransfer()) 
					&& listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId()== listDetailTransferStockPF.get(j).getArticle().getId() && listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId()== listDetailTransferStockPF.get(j).getSousFamille().getId() && listDetailTransferStockPF.get(j).getTypeTransfer().equals(typetransfer[k]))	
			{
				
				if(listDetailTransferStockPF.get(j).getArticle().getCodeArticle().equals("2007"))
    			{
    				System.out.print("yes");
    			}
		
			
				
				achat=new BigDecimal(0);
    			vente=new BigDecimal(0);
    			initial=new BigDecimal(0);
    			production=new BigDecimal(0);
    			stockfinal=new BigDecimal(0);
    			avoir=new BigDecimal(0);
    			TransferEntrer=new BigDecimal(0);
    			Prixachat=new BigDecimal(0);
    			 PrixTransferEntrer=new BigDecimal(0);
    			 prixinitial=new BigDecimal(0);
    			 Prixproduction=new BigDecimal(0);
				
				
				
			if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ACHAT))
			{
				
		    	   if(!achat.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
              	   {
                  	   Prixachat=(Prixachat.multiply(achat).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(achat.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)), RoundingMode.DOWN);

              	   }else
              	   {
              		   Prixachat=BigDecimal.ZERO;
              	   }
				
				achat=achat.add(listDetailTransferStockPF.get(j).getQuantite());
			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_VENTE))
			{
				vente=vente.add(listDetailTransferStockPF.get(j).getQuantite());
				
			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_INITIAL))
			{
				 prixinitial=listDetailTransferStockPF.get(j).getPrixUnitaire();
				initial=initial.add(listDetailTransferStockPF.get(j).getQuantite());
				
			}else if(typetransfer[k].equals(TYPE_TRANSFER_PRODUIT_FINI_ENTRE))
			{
				   if(!production.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6, RoundingMode.DOWN)))
              	   {
                  	   Prixproduction=(Prixproduction.multiply(production).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(production.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)),RoundingMode.DOWN);

              	   }else
              	   {
              		   Prixachat=BigDecimal.ZERO;
              	   }
				
				production=production.add(listDetailTransferStockPF.get(j).getQuantite());	
				
				
			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR))
			{ 
				if(listDetailTransferStockPF.get(j).getSousFamille().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_EMBALLAGE))
				{
					avoir=avoir.add(listDetailTransferStockPF.get(j).getQuantite());	
				}
				
				
				
				
			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_MP))
			{ 
				   if(!TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
              	   {
                  	   PrixTransferEntrer=(PrixTransferEntrer.multiply(TransferEntrer).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)), RoundingMode.DOWN);

              	   }else
              	   {
              		 PrixTransferEntrer=BigDecimal.ZERO;
              	   }
				
				TransferEntrer=TransferEntrer.add(listDetailTransferStockPF.get(j).getQuantite());	
				
				
				
			}
			
			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_PF_MP))
			{ 
				   if(!TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
           	   {
               	   PrixTransferEntrer=(PrixTransferEntrer.multiply(TransferEntrer).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(TransferEntrer.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)), RoundingMode.DOWN);

           	   }else
           	   {
           		 PrixTransferEntrer=BigDecimal.ZERO;
           	   }
				
				TransferEntrer=TransferEntrer.add(listDetailTransferStockPF.get(j).getQuantite());	
				
				
				
			}
			
			
			
			if(!quantitefinale.setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6, RoundingMode.DOWN)))
			{
    			

				if(!quantitefinale.add(achat).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6, RoundingMode.DOWN)))
    			{
	    			
if(quantitefinale.add(achat).add(TransferEntrer).add(production).compareTo(BigDecimal.ZERO)!=0)
{
prixmoyenne=(achat.multiply(Prixachat).add(quantitefinale.multiply(prixmoyenne)).add(TransferEntrer.multiply(PrixTransferEntrer)).add(production.multiply(Prixproduction))).divide(quantitefinale.add(achat).add(TransferEntrer).add(production),6, RoundingMode.DOWN);
//System.out.println(listDetailTransferStockPF.get(j).getArticle().getLiblle() + " : "+ listDetailTransferStockPF.get(j).getSousFamille().getLiblle() +" : Prix Moyenne :"+ prixmoyenne );

}
					
    			}else
    			{
    				prixmoyenne=BigDecimal.ZERO;

    			}
				
				

				quantitefinale=(quantitefinale.add(achat).add(TransferEntrer).add(production)).subtract(vente.add(avoir));
				
			}else
			{
				
				
				if(!initial.add(achat).add(production).add(TransferEntrer).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
    			{

					prixmoyenne=(achat.multiply(Prixachat).add(initial.multiply(prixinitial)).add(PrixTransferEntrer.multiply(TransferEntrer).add(Prixproduction.multiply(production)))).divide(initial.add(achat).add(production).add(TransferEntrer), 6, RoundingMode.DOWN);
					
    			
    			}else
    			{
    				prixmoyenne=BigDecimal.ZERO;

    			}
				
				
				quantitefinale=(initial.add(achat).add(TransferEntrer).add(production)).subtract(vente.add(avoir));

				
			}
			
						    				
			}
					    			
			}
			}
			
			if(listMouvementStockPF.size()!=0)
			{
				for(int d=0;d<listMouvementStockPF.size();d++)
				{
					
					if(listMouvementStockPF.get(d).getArticle().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() && listMouvementStockPF.get(d).getSousFamille().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId())
					{
						initial=listMouvementStockPF.get(d).getStockFinal();
						prixinitial=listMouvementStockPF.get(d).getPrixFinal();
						trouve=true;
						
					}
				}
			
				
			}
			if(trouve==false)
			{
				for(int l=0;l<listDetailTransferStockPFBytypetransfer.size();l++)
				{
	if(listDetailTransferStockPFBytypetransfer.get(l).getArticle().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getId() && listDetailTransferStockPFBytypetransfer.get(l).getSousFamille().getId()== listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().getId())
	{
		initial=listDetailTransferStockPFBytypetransfer.get(l).getQuantite();
			
	}
				}
				
			
			}
			
			if(listDetailTransferStockPFGroupebyArticle.get(i).getArticle().getCodeArticle().equals("2007"))
			{
				System.out.print("yes");
			}
			
			stockfinal=(initial.add(production).add(achat).add(TransferEntrer)).subtract(vente.add(avoir));
			MouvementStockProduitsFini mouvementstockPF=new MouvementStockProduitsFini();
			mouvementstockPF.setDateStockPF(listDetailTransferStockPFGroupebyArticle.get(i).getTransferStockPF().getDateTransfer());
			mouvementstockPF.setAchat(achat);
			mouvementstockPF.setPrixAchat(Prixachat);
			mouvementstockPF.setVente(vente);
			mouvementstockPF.setAvoir(avoir);
			mouvementstockPF.setInitial(initial);
			mouvementstockPF.setPrixInitial(prixinitial);
			mouvementstockPF.setEntrerProduction(production);
			mouvementstockPF.setPrixProduction(Prixproduction);
			mouvementstockPF.setTransferEntrer(TransferEntrer);
			mouvementstockPF.setPrixTransferEntrer(PrixTransferEntrer);
			mouvementstockPF.setSousFamille(listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille());
			mouvementstockPF.setArticle(listDetailTransferStockPFGroupebyArticle.get(i).getArticle());
			
			
			
			/*
			 * if(listDetailTransferStockPFGroupebyArticle.get(i).getArticle().
			 * getCodeArticle().equals("NP1000")) {
			 * 
			 * if((mouvementstockPF.getAchat().add(mouvementstockPF.getInitial()).add(
			 * mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.
			 * getEntrerProduction())).setScale(6,
			 * RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6,
			 * RoundingMode.HALF_UP))!=0) {
			 * prixmoyenne=(mouvementstockPF.getAchat().multiply(mouvementstockPF.
			 * getPrixAchat()).add(mouvementstockPF.getInitial().multiply(mouvementstockPF.
			 * getPrixInitial())).add(mouvementstockPF.getPrixTransferEntrer().multiply(
			 * mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.getPrixProduction(
			 * ).multiply(mouvementstockPF.getEntrerProduction())))).divide(mouvementstockPF
			 * .getInitial().add(mouvementstockPF.getAchat()).add(mouvementstockPF.
			 * getEntrerProduction()).add(mouvementstockPF.getTransferEntrer()),
			 * RoundingMode.HALF_UP);
			 * 
			 * //JOptionPane.showMessageDialog(null,
			 * "Prix Moyenne calculé "+listDetailTransferStockPFGroupebyArticle.get(i).
			 * getArticle().getLiblle()); //JOptionPane.showMessageDialog(null,
			 * "Prix Moyenne calculé est : "+mouvementstockPF.getAchat()
			 * +"*"+mouvementstockPF.getPrixAchat() + "+"+mouvementstockPF.getPrixInitial()
			 * +"*"+mouvementstockPF.getInitial()
			 * +" + "+mouvementstockPF.getPrixTransferEntrer()+"*"+mouvementstockPF.
			 * getTransferEntrer() +" + "+mouvementstockPF.getPrixProduction() +" * "+
			 * mouvementstockPF.getEntrerProduction());
			 * 
			 * //JOptionPane.showMessageDialog(null,
			 * "Prix Moyenne calculé est : "+prixmoyenne); }else {
			 * prixmoyenne=BigDecimal.ZERO; //JOptionPane.showMessageDialog(null,
			 * "Prix Moyenne Zero"); }
			 * 
			 * 
			 * 
			 * 
			 * }
			 */
			
			
			/*
			 * if((mouvementstockPF.getAchat().add(mouvementstockPF.getInitial()).add(
			 * mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.
			 * getEntrerProduction())).setScale(6,
			 * RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6,
			 * RoundingMode.HALF_UP))!=0) {
			 * prixmoyenne=(mouvementstockPF.getAchat().multiply(mouvementstockPF.
			 * getPrixAchat()).add(mouvementstockPF.getInitial().multiply(mouvementstockPF.
			 * getPrixInitial())).add(mouvementstockPF.getPrixTransferEntrer().multiply(
			 * mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.getPrixProduction(
			 * ).multiply(mouvementstockPF.getEntrerProduction())))).divide(mouvementstockPF
			 * .getInitial().add(mouvementstockPF.getAchat()).add(mouvementstockPF.
			 * getEntrerProduction()).add(mouvementstockPF.getTransferEntrer()),
			 * RoundingMode.HALF_UP);
			 * 
			 * }else { prixmoyenne=BigDecimal.ZERO; }
			 */

			//System.out.println( "new "+mouvementstockPF.getArticle().getLiblle() + " : "+mouvementstockPF.getSousFamille().getLiblle() +" : Prix Moyenne :"+ prixmoyenne );

			mouvementstockPF.setPrixFinal(prixmoyenne); 
			mouvementstockPF.setStockFinal((mouvementstockPF.getInitial().add(mouvementstockPF.getAchat()).add(mouvementstockPF.getEntrerProduction()).add(mouvementstockPF.getTransferEntrer())).subtract(mouvementstockPF.getVente().add(mouvementstockPF.getAvoir())));
			
			listMouvementStockPF.add(mouvementstockPF);
			
			
			

		}
		
	
		
		
		
		// detailtransfer entre deux date et par article
		
		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && article!=null)
		{
			listMouvementStockPFAfficher.clear();
			listMouvementStockPFAfficherTmp.clear();
			
		
		for(int i=0;i<listMouvementStockPF.size();i++)	
		{
			String ddebut=sdf.format(dateChooserdebut.getDate());
			String ddebutTmp=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
			
			if(listMouvementStockPF.get(i).getDateStockPF().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut)   )
				
			{
			if(listMouvementStockPF.get(i).getArticle().getLiblle().equals(article.getLiblle()))
			{

				listMouvementStockPFAfficher.add(listMouvementStockPF.get(i));	
			}
				
			
			}
			
		}
			
		for(int j=0;j<listMouvementStockPFAfficher.size();j++)	
		{
			
			String dfin=sdf.format(dateChooserfin.getDate());
			String dfinTmp=sdf.format(listMouvementStockPFAfficher.get(j).getDateStockPF());
			if(listMouvementStockPFAfficher.get(j).getDateStockPF().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin) )
			{
			if(listMouvementStockPFAfficher.get(j).getArticle().getLiblle().equals(article.getLiblle()))
			{
				listMouvementStockPFAfficherTmp.add(listMouvementStockPFAfficher.get(j));
			}
				
			}
			
		}
		
	
			
		// detailtransfer entre deux date (date fin null) et par article 
			
		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article!=null)
		{
			listMouvementStockPFAfficherTmp.clear();
			String d1=sdf.format(dateChooserdebut.getDate());
		
			
			for(int i=0;i<listMouvementStockPF.size();i++)	
    		{
				String ddbut=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
    			if(ddbut.equals(d1) && listMouvementStockPF.get(i).getArticle().equals(article) )
    			{
    			
    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
    			
    			}
    			
    		}
			
			
			
			// detailtransfer entre deux date (date fin null)  
			
		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article==null)
		{
			
			listMouvementStockPFAfficherTmp.clear();
              
              String d1=sdf.format(dateChooserdebut.getDate());
    			
			
			for(int i=0;i<listMouvementStockPF.size();i++)	
    		{
				String ddbut=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
    			if(ddbut.equals(d1) )
    			{
    			
    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
    			
    			}
    		
    		}
			
		
			
			// detailtransfer par article
		}else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null && article!=null)
		{
			listMouvementStockPFAfficherTmp.clear();
         
           
           
			for(int i=0;i<listMouvementStockPF.size();i++)	
    		{
    			if(listMouvementStockPF.get(i).getArticle().getLiblle().equals(article.getLiblle()) )
    			{
    			
    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
    			
    				
    			}
    			
    		}
			
			
			
			
			
			// detailtransfer entre deux date  
			
		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && article==null)
		{
			listMouvementStockPFAfficher.clear();
			listMouvementStockPFAfficherTmp.clear();
		
		for(int i=0;i<listMouvementStockPF.size();i++)	
		{
			String ddebut=sdf.format(dateChooserdebut.getDate());
			String ddebutTmp=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
			if(listMouvementStockPF.get(i).getDateStockPF().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut))
			{
				listMouvementStockPFAfficher.add(listMouvementStockPF.get(i));
			
				
			}
			
		}
			
		
		
		for(int j=0;j<listMouvementStockPFAfficher.size();j++)	
		{
			
			String dfin=sdf.format(dateChooserfin.getDate());
			String dfinTmp=sdf.format(listMouvementStockPFAfficher.get(j).getDateStockPF());
			
			if(listMouvementStockPFAfficher.get(j).getDateStockPF().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin)  )
			{
			
				listMouvementStockPFAfficherTmp.add(listMouvementStockPFAfficher.get(j));
			
			}
			
		}
		
	
				    			
		}
		
	
	
	}else
	{
		JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}

	

	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}






