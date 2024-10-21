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
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
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
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatStockMP;
import dao.entity.EtatStockPF;
import dao.entity.EtatValeurStock;
import dao.entity.FacturePF;
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


public class ConsulterEtatCoutMoyen extends JLayeredPane implements Constantes{
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
	  
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public ConsulterEtatCoutMoyen() {
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
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableEtatStock.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
				"Article","Famille", "Sous Famille","Quantite ", "Prix Moyen","Montant"
       	}
       ));
       tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
       tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
       tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(118);
      
				  		
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
				  		     	titledSeparator.setTitle("Etat de Cout Moyen de Stock");
				  		     	titledSeparator.setBounds(10, 154, 1465, 30);
				  		     	add(titledSeparator);
		      
		     	
		      
		     
		     
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
}});
		
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(862, 824, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		buttonvalider.setVisible(false);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter Etat Cout Moyen de Stock :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(323, 11, 925, 35);
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
	    		if(magasin!=null && magasinPF!=null  )
	    		{	
	    		
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
		    			

    						montantCharger=montantCharger.add(listDetailTransferStockMPCharger.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPCharger.get(k).getQuantite()));
    		    			quantiteTotalCharger=quantiteTotalCharger.add(listDetailTransferStockMPCharger.get(k).getQuantite());

		    
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


montantService=montantService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantite()));
quantiteTotalService=quantiteTotalService.add(listDetailTransferStockMPService.get(k).getQuantite());


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


///////////////////////////////////////////////////////////////////////////////////////// Les ventes de sous famille 	 ////////////////////////////////////////////////////////////////////



requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_VENTE, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantVente=new BigDecimal(0);
quantiteTotalVente=new BigDecimal(0);
quantiteTotalGratuite=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getCode()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{

if(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
{
	
	quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());
	
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
	
	listEtatStockMP.set(j, etatstockmp);
	
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

					/*
					 * /////////////////////////////////////////////////////////////////////////////
					 * /Liste Des PF Par Sous Famille Dechet
					 * ////////////////////////////////////////////////////////////////////////////
					 * 
					 * listDetailTransferStockPFBySousFamille=detailTransferProduitFiniDAO.
					 * ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupBySousFamilleArticleDechet
					 * (dateChooserdebut.getDate(), dateChooserfin.getDate(), magasinPF);
					 * 
					 * 
					 * for(int i=0;i<listDetailTransferStockPFBySousFamille.size();i++) {
					 * 
					 * 
					 * 
					 * DetailTransferProduitFini
					 * detailTransferStockPF=listDetailTransferStockPFBySousFamille.get(i);
					 * 
					 * 
					 * 
					 * if(detailTransferStockPF.getArticle().getCodeArticle().contains("_PFD") ) {
					 * 
					 * EtatValeurStock etatValeurStock=new EtatValeurStock();
					 * etatValeurStock.setCodearticle(Constantes.FAMILLE_DECHET
					 * +" "+detailTransferStockPF.getSousFamille().getCode());;
					 * etatValeurStock.setArticle(Constantes.FAMILLE_DECHET
					 * +" "+detailTransferStockPF.getSousFamille().getLiblle());
					 * etatValeurStock.setFamille(Constantes.FAMILLE_DECHET
					 * +" "+detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle())
					 * ; etatValeurStock.setSousfamille (Constantes.FAMILLE_DECHET
					 * +" "+detailTransferStockPF.getSousFamille().getLiblle());
					 * etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
					 * etatValeurStock.setPrixInitial(BigDecimal.ZERO);
					 * etatValeurStock.setMontantInitial(BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
					 * etatValeurStock.setPrixAchat(BigDecimal.ZERO);
					 * etatValeurStock.setMontantAchat (BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
					 * etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
					 * etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
					 * etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
					 * etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
					 * etatValeurStock.setPrixVente(BigDecimal.ZERO);
					 * etatValeurStock.setMontantVente(BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
					 * etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
					 * etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
					 * etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
					 * etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
					 * etatValeurStock.setPrixFinale(BigDecimal.ZERO);
					 * etatValeurStock.setMontantFinale(BigDecimal.ZERO);
					 * 
					 * listEtatStockMP.add(etatValeurStock);
					 * 
					 * }
					 * 
					 * 
					 * }
					 * 
					 * 
					 * 
					 * 
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
					 * getCode())) {
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
					 * getCode())) {
					 * 
					 * 
					 * montantachat=montantachat.add(listDetailTransferStockPFInitialSousFamille.get
					 * (k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.
					 * get(k).getQuantite())); quantiteTotalachat=quantiteTotalachat.add(
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
					 * 
					 * 
					 * /////////////////////////////////////////////////////////////////////////////
					 * //////////// Production Entrer de sous famille Dechet
					 * ////////////////////////////////////////////////////////////////////
					 * 
					 * 
					 * 
					 * requete=" and magasinDestination.id='"+magasinPF.getId()
					 * +"' and article.codeArticle like'%"+DPF+"%'";
					 * listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.
					 * ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(
					 * dateChooserdebut.getDate(), dateChooserfin.getDate(),
					 * Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP, requete);
					 * 
					 * 
					 * for(int j=0;j<listEtatStockMP.size();j++) { montantProductionEnter=new
					 * BigDecimal(0); quantiteTotalProductionEnter=new BigDecimal(0);
					 * 
					 * EtatValeurStock etatstockmp=listEtatStockMP.get(j);
					 * 
					 * for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++) {
					 * 
					 * if(etatstockmp.getCodearticle().equals(Constantes.FAMILLE_DECHET
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getCode())) {
					 * 
					 * 
					 * montantProductionEnter=montantProductionEnter.add(
					 * listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply
					 * (listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
					 * quantiteTotalProductionEnter=quantiteTotalProductionEnter.add(
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
					 * } if(!montantProductionEnter.equals(BigDecimal.ZERO) &&
					 * !quantiteTotalProductionEnter.equals(BigDecimal.ZERO)) {
					 * 
					 * etatstockmp.setQuantiteProductionEntrer (quantiteTotalProductionEnter);
					 * etatstockmp.setPrixProductionEntrer(montantProductionEnter.divide(
					 * quantiteTotalProductionEnter,6,RoundingMode.HALF_UP));
					 * etatstockmp.setMontantProductionEntrer(etatstockmp.
					 * getQuantiteProductionEntrer().multiply(etatstockmp.getPrixProductionEntrer())
					 * ); listEtatStockMP.set(j, etatstockmp);
					 * 
					 * }
					 * 
					 * }
					 * 
					 * 
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
					 * getCode())) {
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
					 * getCode())) {
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
					 * 
					 */

//////////////////////////////////////////////////////////////////////////////Liste Des PF Par Sous Famille not in (The , Dechet , Offre,emballage)  ////////////////////////////////////////////////////////////////////////////

listDetailTransferStockPFBySousFamille=detailTransferProduitFiniDAO.ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupByArticleBySousFamille(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasinPF);




for(int i=0;i<listDetailTransferStockPFBySousFamille.size();i++)
{



DetailTransferProduitFini detailTransferStockPF=listDetailTransferStockPFBySousFamille.get(i);	



if( !detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_DECHET) && !detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_CADEAU) && !detailTransferStockPF.getSousFamille().getFamileArticlePF().getCode().contentEquals(Constantes.CODE_CHAARA) && !detailTransferStockPF.getSousFamille().getFamileArticlePF().getCode().contentEquals(Constantes.CODE_MKARKEB) && !detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_EMBALLAGE))
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


///////////////////////////////////////////////////////////////////////////////////////// Initial de sous famille  not in (The , Dechet , Offre,emballage)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code not in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"') and sousFamille.famileArticlePF.liblle not in ('"+Constantes.FAMILLE_DECHET+"', '"+Constantes.FAMILLE_CADEAU+"','"+Constantes.FAMILLE_EMBALLAGE+"')";
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





///////////////////////////////////////////////////////////////////////////////////////// Achat de sous famille  not in (The , Dechet , Offre,emballage)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code not in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"') and sousFamille.famileArticlePF.liblle not in ('"+Constantes.FAMILLE_DECHET+"', '"+Constantes.FAMILLE_CADEAU+"','"+Constantes.FAMILLE_EMBALLAGE+"')";
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




///////////////////////////////////////////////////////////////////////////////////////// Vente de sous famille  not in (The , Dechet , Offre,emballage)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code not in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"') and sousFamille.famileArticlePF.liblle not in ('"+Constantes.FAMILLE_DECHET+"', '"+Constantes.FAMILLE_CADEAU+"','"+Constantes.FAMILLE_EMBALLAGE+"')";
listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), Constantes.ETAT_TRANSFER_STOCK_VENTE, requete);


for(int j=0;j<listEtatStockMP.size();j++)
{
montantVente=new BigDecimal(0);
quantiteTotalVente=new BigDecimal(0);
quantiteTotalGratuite=new BigDecimal(0);

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{

if(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
{
	quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite())	;
	
	
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

	listEtatStockMP.set(j, etatstockmp);
	
}




}



///////////////////////////////////////////////////////////////////////////////////////// Les Avoirs de sous famille  not in (The , Dechet , Offre,emballage)	 ////////////////////////////////////////////////////////////////////

requete=" and magasinDestination.id='"+magasinPF.getId()+"' and sousFamille.famileArticlePF.code not in ('"+Constantes.CODE_CHAARA+"', '"+Constantes.CODE_MKARKEB+"') and sousFamille.famileArticlePF.liblle not in ('"+Constantes.FAMILLE_DECHET+"', '"+Constantes.FAMILLE_CADEAU+"','"+Constantes.FAMILLE_EMBALLAGE+"')";
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


					/*
					 * /////////////////////////////////////////////////////////////////////////////
					 * /Liste Des PF Par Sous Famille Offre
					 * ////////////////////////////////////////////////////////////////////////////
					 * 
					 * listDetailTransferStockPFBySousFamille=detailTransferProduitFiniDAO.
					 * ListDetailTransferStockPFEntreDeuxDatesBYPFStatutXGroupBySousFamilleArticleOffre
					 * (dateChooserdebut.getDate(), dateChooserfin.getDate(), magasinPF);
					 * 
					 * 
					 * for(int i=0;i<listDetailTransferStockPFBySousFamille.size();i++) {
					 * 
					 * 
					 * 
					 * DetailTransferProduitFini
					 * detailTransferStockPF=listDetailTransferStockPFBySousFamille.get(i);
					 * 
					 * 
					 * 
					 * if(detailTransferStockPF.getArticle().getCodeArticle().contains("_PFO") &&
					 * !detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle().
					 * contentEquals(Constantes.FAMILLE_CADEAU)) {
					 * 
					 * EtatValeurStock etatValeurStock=new EtatValeurStock();
					 * etatValeurStock.setCodearticle(Constantes.MP_STOCK_OFFRE
					 * +" "+detailTransferStockPF.getSousFamille().getCode());;
					 * etatValeurStock.setArticle(Constantes.MP_STOCK_OFFRE
					 * +" "+detailTransferStockPF.getSousFamille().getLiblle());
					 * etatValeurStock.setFamille(Constantes.MP_STOCK_OFFRE
					 * +" "+detailTransferStockPF.getSousFamille().getFamileArticlePF().getLiblle())
					 * ; etatValeurStock.setSousfamille (Constantes.MP_STOCK_OFFRE
					 * +" "+detailTransferStockPF.getSousFamille().getLiblle());
					 * etatValeurStock.setQuantiteInitial(BigDecimal.ZERO);
					 * etatValeurStock.setPrixInitial(BigDecimal.ZERO);
					 * etatValeurStock.setMontantInitial(BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteAchat(BigDecimal.ZERO);
					 * etatValeurStock.setPrixAchat(BigDecimal.ZERO);
					 * etatValeurStock.setMontantAchat (BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteProductionEntrer(BigDecimal.ZERO);
					 * etatValeurStock.setPrixProductionEntrer(BigDecimal.ZERO);
					 * etatValeurStock.setMontantProductionEntrer(BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteProductionSortie(BigDecimal.ZERO);
					 * etatValeurStock.setPrixProductionSortie(BigDecimal.ZERO);
					 * etatValeurStock.setMontantProductionSortie(BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteVente(BigDecimal.ZERO);
					 * etatValeurStock.setPrixVente(BigDecimal.ZERO);
					 * etatValeurStock.setMontantVente(BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteGratuite(BigDecimal.ZERO);
					 * etatValeurStock.setPrixGratuite(BigDecimal.ZERO);
					 * etatValeurStock.setMontantGratuite(BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteAvoir(BigDecimal.ZERO);
					 * etatValeurStock.setPrixAvoir(BigDecimal.ZERO);
					 * etatValeurStock.setMontantAvoir(BigDecimal.ZERO);
					 * etatValeurStock.setQuantiteFinale(BigDecimal.ZERO);
					 * etatValeurStock.setPrixFinale(BigDecimal.ZERO);
					 * etatValeurStock.setMontantFinale(BigDecimal.ZERO);
					 * 
					 * listEtatStockMP.add(etatValeurStock);
					 * 
					 * }
					 * 
					 * 
					 * }
					 * 
					 * 
					 * 
					 * 
					 * /////////////////////////////////////////////////////////////////////////////
					 * //////////// Initial de sous famille Offre
					 * ////////////////////////////////////////////////////////////////////
					 * 
					 * 
					 * requete=" and magasinDestination.id='"+magasinPF.getId()
					 * +"' and article.codeArticle like'%"+OPF+"%'";
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
					 * if(etatstockmp.getCodearticle().equals(Constantes.MP_STOCK_OFFRE
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getCode()) &&
					 * !listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_CADEAU)) {
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
					 * //////////// Achat de sous famille Offre
					 * ////////////////////////////////////////////////////////////////////
					 * 
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
					 * getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_CADEAU)) {
					 * 
					 * 
					 * montantachat=montantachat.add(listDetailTransferStockPFInitialSousFamille.get
					 * (k).getPrixUnitaire().multiply(listDetailTransferStockPFInitialSousFamille.
					 * get(k).getQuantite())); quantiteTotalachat=quantiteTotalachat.add(
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
					 * 
					 * 
					 * /////////////////////////////////////////////////////////////////////////////
					 * //////////// Production Entrer de sous famille Offre
					 * ////////////////////////////////////////////////////////////////////
					 * 
					 * 
					 * 
					 * requete=" and magasinDestination.id='"+magasinPF.getId()
					 * +"' and article.codeArticle like'%"+OPF+"%'";
					 * listDetailTransferStockPFInitialSousFamille=detailTransferProduitFiniDAO.
					 * ListTransferStockPFEntreDeuxDatesBySousFamilleBYPFDistinctByStatutX(
					 * dateChooserdebut.getDate(), dateChooserfin.getDate(),
					 * Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP, requete);
					 * 
					 * 
					 * for(int j=0;j<listEtatStockMP.size();j++) { montantProductionEnter=new
					 * BigDecimal(0); quantiteTotalProductionEnter=new BigDecimal(0);
					 * 
					 * EtatValeurStock etatstockmp=listEtatStockMP.get(j);
					 * 
					 * for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++) {
					 * 
					 * if(etatstockmp.getCodearticle().equals(Constantes.MP_STOCK_OFFRE
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getCode()) &&
					 * !listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_CADEAU)) {
					 * 
					 * 
					 * montantProductionEnter=montantProductionEnter.add(
					 * listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().multiply
					 * (listDetailTransferStockPFInitialSousFamille.get(k).getQuantite()));
					 * quantiteTotalProductionEnter=quantiteTotalProductionEnter.add(
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
					 * } if(!montantProductionEnter.equals(BigDecimal.ZERO) &&
					 * !quantiteTotalProductionEnter.equals(BigDecimal.ZERO)) {
					 * 
					 * etatstockmp.setQuantiteProductionEntrer (quantiteTotalProductionEnter);
					 * etatstockmp.setPrixProductionEntrer(montantProductionEnter.divide(
					 * quantiteTotalProductionEnter,6,RoundingMode.HALF_UP));
					 * etatstockmp.setMontantProductionEntrer(etatstockmp.
					 * getQuantiteProductionEntrer().multiply(etatstockmp.getPrixProductionEntrer())
					 * ); listEtatStockMP.set(j, etatstockmp);
					 * 
					 * }
					 * 
					 * }
					 * 
					 * 
					 * /////////////////////////////////////////////////////////////////////////////
					 * //////////// Les ventes de sous famille Offre
					 * ////////////////////////////////////////////////////////////////////
					 * 
					 * 
					 * 
					 * requete=" and magasinDestination.id='"+magasinPF.getId()
					 * +"' and article.codeArticle like'%"+OPF+"%'";
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
					 * if(etatstockmp.getCodearticle().equals(Constantes.MP_STOCK_OFFRE
					 * +" "+listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getCode()) &&
					 * !listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().
					 * getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_CADEAU)) {
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
					 * //////////// Les Avoirs de sous famille Offre
					 * ////////////////////////////////////////////////////////////////////
					 * 
					 * 
					 * 
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
					 * getFamileArticlePF().getLiblle().contentEquals(Constantes.FAMILLE_CADEAU)) {
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

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{

if(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
{

quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());

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

EtatValeurStock etatstockmp=listEtatStockMP.get(j);	

for(int k=0;k<listDetailTransferStockPFInitialSousFamille.size();k++)
{

if(etatstockmp.getCodearticle().equals(listDetailTransferStockPFInitialSousFamille.get(k).getArticle().getCodeArticle()) && etatstockmp.getFamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getFamileArticlePF().getLiblle()) && etatstockmp.getSousfamille().equals(listDetailTransferStockPFInitialSousFamille.get(k).getSousFamille().getLiblle()))
{

if(listDetailTransferStockPFInitialSousFamille.get(k).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
{

quantiteTotalGratuite=quantiteTotalGratuite.add(listDetailTransferStockPFInitialSousFamille.get(k).getQuantite());

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
	
prixMoyen=(etatstockmp.getMontantInitial().add(etatstockmp.getMontantAchat().add(etatstockmp.getMontantProductionEntrer()))).divide(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat().add(etatstockmp.getQuantiteProductionEntrer())), 6, RoundingMode.HALF_UP)	;
	
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

/*
StockSansEmballage=StockInitial.subtract(StockEmballage);
AchatSansEmballage=AchatTotal.subtract(AchatEmballage);
AvoirSansEmballage=AvoirTotal.subtract(AvoirEmballage);
StockTotalFinaleSansEmballage=StockTotalFinale.subtract(StockTotalFinaleEmballage);
*/
	    		
	    		afficher_tableEtatStock(listEtatStockMP);
	    		

	    		
	    		
	    		

	    		
	    		
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
	    		
	    		String titre="Etat Moyen de Stock  "+magasin.getLibelle();
	    		String titrefeuilleexcel="Etat Moyen de Stock  ";
	    		ExporterTableVersExcel.tabletoexcel(tableEtatStock, titre,titrefeuilleexcel);
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
	    btnExporterExcel.setBounds(709, 824, 130, 32);
	    btnExporterExcel.setIcon(imgExcel);
	    add(btnExporterExcel);
	 
	    
	    
	    
	    
	    
	    
	  
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
						"Article","Famille", "Sous Famille","Quantite ", "Prix Moyen","Montant"
						}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false
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
		
			
		BigDecimal Quantite=EtatValeurStock.getQuantiteInitial().add(EtatValeurStock.getQuantiteAchat().add(EtatValeurStock.getQuantiteProductionEntrer()));
		BigDecimal Montant=EtatValeurStock.getMontantInitial().add(EtatValeurStock.getMontantAchat().add(EtatValeurStock.getMontantProductionEntrer()));
		BigDecimal PrixMoyen=BigDecimal.ZERO;
		if(Quantite.compareTo(BigDecimal.ZERO)!=0)
		{
			PrixMoyen=Montant.divide(Quantite, 6, RoundingMode.HALF_UP);
			
		}
		
		
				//Object []ligne={EtatStockMP.getMp().getNom(),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteInitial().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixInitial().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantInitial().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteAchat().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixAchat().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantAchat().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteSortie().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixSortie().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantSortie().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteDechet().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixDechet().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantDechet().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteOffreProduction().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixOffreProduction().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantOffreProduction().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteDechetService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixDechetService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantDechetService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteOffreService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixOffreService().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantOffreService().setScale(6, RoundingMode.HALF_UP)), NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteAvoir().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixAvoir().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantAvoir().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getQuantiteFinale().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getPrixFinale().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMontantFinale().setScale(6, RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(EtatStockMP.getMarge().setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP)))+"%"};
				Object []ligne={EtatValeurStock.getArticle() , EtatValeurStock.getFamille() , EtatValeurStock.getSousfamille(),Quantite,PrixMoyen,Montant};

				modeleEtatStock.addRow(ligne);
				

			
			i++;
		}
}
	}






