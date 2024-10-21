package presentation.stockPF;

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
import java.util.GregorianCalendar;
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
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureAchatDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
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
import dao.daoManager.DetailFactureAchatDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
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
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatMargeArticles;
import dao.entity.EtatStockMP;
import dao.entity.EtatStockPF;
import dao.entity.FactureAchat;
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
import dao.entity.SousFamilleEnVrac;
import dao.entity.StockMP;
import dao.entity.StockPF;
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
import javax.swing.JTextArea;
import javax.swing.JTextPane;


public class ConsulterEtatMargeStockPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEtatStock;
	private DefaultTableModel	 modeleMouvementStock;

	private JXTable  tableEtatStock = new JXTable();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	private List<EtatStockPF> listEtatStockPF =new ArrayList<EtatStockPF>();
	private List<EtatStockPF> listEtatStockPFImprimer =new ArrayList<EtatStockPF>();
	private List<EtatMargeArticles> listEtatMargeArticle =new ArrayList<EtatMargeArticles>();
	private List<EtatMargeArticles> listEtatMargeArticleGlobal =new ArrayList<EtatMargeArticles>();
	private List<FamilleArticlePF> listFamilleArticlesF =new ArrayList<FamilleArticlePF>();
	private Map< String, FamilleArticlePF> mapFamilleArticles= new HashMap<>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	
	//**********************************************************************listes Mouvemement Stock***************************************************
	
	private List<DetailTransferProduitFini> listDetailTransferStockPF =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFGroupebyArticle =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFBytypetransfer =new ArrayList<DetailTransferProduitFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPF =new ArrayList<MouvementStockProduitsFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPFAfficher =new ArrayList<MouvementStockProduitsFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPFAfficherTmp =new ArrayList<MouvementStockProduitsFini>();
	
	
	
	//*************************************************************************************************************************************************
	
private List<EtatStockMP> listEtatStockMP =new ArrayList<EtatStockMP>();
	
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
	private List<DetailTransferStockMP> listDetailTransferStockMPAchatGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPSortie =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPSortieGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPAvoir =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPAvoirGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeSupp =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeSuppGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPService =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPServiceGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPVente =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPVenteGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPF =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFGroupebyMP =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFProduction =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFProductionGroupebyMP =new ArrayList<DetailTransferStockMP>();
	
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFService =new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPTransferMPPFGroupebyMPService =new ArrayList<DetailTransferStockMP>();
	
	private List<DetailTransferStockMP> listDetailTransferStockMPAllMP =new ArrayList<DetailTransferStockMP>();
	
	//***************************************************

	
	
	
	
	
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();

	private Map< String, Articles> mapArticle= new HashMap<>();
	private Map< String, Articles> mapCodeArticle= new HashMap<>();
	
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	 JComboBox combomp = new JComboBox();
	 private JComboBox comboarticle;
	  JComboBox combomagasin = new JComboBox();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
private DetailTransferProduitFiniDAO detailTransferStockPFDAO;
private DetailFactureAchatDAO detailFactureAchatdao;
	private JTextField txtlibelle=new JTextField();
	 JComboBox comboFamille = new JComboBox();
	  JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	 JDateChooser dateChooserdebut = new JDateChooser();
	 JDateChooser dateChooserfin = new JDateChooser();
	 private JDateChooser dateChooser = new JDateChooser();
private FamilleArticlesPFDAO familleArticlesDAO;
	 JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockPFDAO stockpfDAO;
	private CompteClientDAO compteclientdao;
	private ArticlesDAO ArticleDAO;
	private JTextField txtcodearticle;
	String titre="";
	private TransferStockPFDAO transferStockPFDAO;
	private FactureAchatDAO factureAchatdao;
	private DetailTransferProduitFiniDAO detailTransferStockPFdao;
	 JTextArea textArea = new JTextArea();
	 JLabel lblDateInitial = new JLabel("Date Initial :");
	 JButton btnTransfert = new JButton("Transfert");
	 JDateChooser dateinitiale = new JDateChooser();
	 private DetailTransferMPDAO detailTransferStockMPDAO;
	 private TransferStockMPDAO transferStockMPDAO;
	 private SousFamilleEnVracDAO sousFamilleEnvracDAo;
	 private StockMPDAO stockMPDAO;
	 
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterEtatMargeStockPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1511, 1062);
      
	
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
         	mouvementStockGlobaleDAO=new MouvementStockGlobalDAOImpl();
         	ArticleDAO=new ArticlesDAOImpl();
         	familleArticlesDAO=new FamilleArticlesPFDAOImpl();
         	detailTransferStockPFDAO=new DetailTransferProduitFiniDAOImpl();
         	transferStockPFDAO=new TransferStockPFDAOImpl();
        	detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	sousFamilleEnvracDAo = new SousFamilleEnVracDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
         listArticle=ArticleDAO.findAll();
         	listFamilleArticlesF=familleArticlesDAO.findAll();
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableEtatStock.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"MATIERE PREMIERE",	"Article","Famille","Sous Famille","Montant Initial", "Montant Production","Montant Achat","RRR","Montant Final", "Achat Revendu","Chiffre d'Affaire", "Marage Par Article", " % Par Article"
       	}
       ));
       tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(258);
       tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(91);
       tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(123);
       tableEtatStock.getColumnModel().getColumn(6).setPreferredWidth(118);
       tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(132);
       tableEtatStock.getColumnModel().getColumn(8).setPreferredWidth(92);
       tableEtatStock.getColumnModel().getColumn(9).setPreferredWidth(95);
       tableEtatStock.getColumnModel().getColumn(10).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(11).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(12).setPreferredWidth(99);
     
       
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
				  		     	scrollPane.setBounds(10, 195, 1465, 587);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Etat de Stock");
				  		     	titledSeparator.setBounds(10, 154, 1465, 30);
				  		     	add(titledSeparator);
		      
		     	
		JButton buttonvalider = new JButton("Imprimer Marge Par Articles");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 if(listEtatMargeArticle.size()!=0)
				 {
					 
					 
						Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
			    		
			    		if(magasin!=null )
			    		{
			    			
			    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			    			String d2=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
			    		
			    		 titre="Etat De Marge Par Articles : "+magasin.getLibelle() +" Entre : "+d1 +" Et : "+d2;
			    		
			    		
			    		}else
			    		{


			    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin  SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
			    			return;
			    		
			    		
			    		} 
					 
					 
					
						Map parameters = new HashMap();

	
						parameters.put("titre", titre);
						JasperUtils.imprimerEtatMargeParArticle(listEtatMargeArticle,parameters);
						
				 } else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun Etat Stock  ", "Erreur", JOptionPane.ERROR_MESSAGE); 
					 return;
				 }
					
				
				
			}});
		
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(837, 793, 195, 32);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter Etat Marge Par Article:");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 836, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		
	    		
	    		detailTransferStockPFDAO.ViderSession();
	    		
	    		listEtatStockPFImprimer.clear();
	    		listEtatStockPF.clear();
	    		listEtatMargeArticle.clear();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFInitial =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAchat =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAchatGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFProduction =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFPFSortie =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrer =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFPFEntrer =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    	
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerProduction =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirMarajne =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirMarajneGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoir =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAllPFTransfer =new ArrayList<DetailTransferProduitFini>();
	    		
	    	
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerArticleTransferMPPF =new ArrayList<DetailTransferProduitFini>(); 
	    		 
	    		 
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
	    		FamilleArticlePF familleArticle=mapFamilleArticles.get(comboFamille.getSelectedItem());
	    		Articles article=mapArticle.get(comboarticle.getSelectedItem());
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
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
		    			titre="Etat de Stock de "+article.getLiblle()+" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
		    		}else
		    		{
		    			titre="Etat de Stock de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
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
		    			titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d1;
		    		}
		    		
		    		listDetailTransferStockPFInitial=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_INITIAL,magasin, familleArticle);
		    		
		    		listDetailTransferStockPFAchat=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
		    		listDetailTransferStockPFAchatGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
		    		
		    		listDetailTransferStockPFProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
		    		listDetailTransferStockPFProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
		    		
		    		
		    		listDetailTransferStockPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		    		listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		    		
		    		listDetailTransferStockPFPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_SORTIE_PF_PF,magasin, familleArticle);
		    		listDetailTransferStockPFPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_SORTIE_PF_PF,magasin, familleArticle);
		    	
		    		

		    		listDetailTransferStockPFEntrerProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
		    		listDetailTransferStockPFEntrerProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
		    		
		    		listDetailTransferStockPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_PF_MP,magasin, familleArticle);
		    		listDetailTransferStockPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_PF_MP,magasin, familleArticle);
		    		
		    		listDetailTransferStockPFPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_PF_PF,magasin, familleArticle);
		    		listDetailTransferStockPFPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_PF_PF,magasin, familleArticle);
		    		
		    		
		    		
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
		    	
		    	
		    	
		    	// calculer Quantite Transfer Sortie et le prix moyen (Les PF transfer En PF )
		    	
		    	
		    	
		    	for(int i=0;i<listDetailTransferStockPFPFSortieGroupebyPF.size();i++)
		    	{
		    		quantiteTotalvente=BigDecimal.ZERO;
		    		quantiteTotalGratuit=BigDecimal.ZERO;
		    		montantvente=new BigDecimal(0);
		    		prixmoyenvente=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFPFSortie.size();j++)
		    		{
		    			
		    			if(listDetailTransferStockPFPFSortieGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFPFSortie.get(j).getArticle()) && listDetailTransferStockPFPFSortieGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFPFSortie.get(j).getSousFamille()))
		    			{
		    			 
		    					
		    					montantvente=montantvente.add(listDetailTransferStockPFPFSortie.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFPFSortie.get(j).getQuantite()));
			    				prixmoyenvente=((prixmoyenvente.multiply(quantiteTotalvente)).add(listDetailTransferStockPFPFSortie.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFPFSortie.get(j).getQuantite()))).divide(quantiteTotalvente.add(listDetailTransferStockPFPFSortie.get(j).getQuantite()),6, RoundingMode.DOWN) ;

		    					quantiteTotalvente=quantiteTotalvente.add(listDetailTransferStockPFPFSortie.get(j).getQuantite());
		    					
		    					
		    				 
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		if(!quantiteTotalvente.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFPFSortieGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFPFSortieGroupebyPF.get(i).getSousFamille()))
		    	    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteSortie(quantiteTotalvente);
		    	    			etatstockpf.setPrixSortie(prixmoyenvente);
		    	    			etatstockpf.setMontantSortie(quantiteTotalvente.multiply(prixmoyenvente));
		    	    		
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
		    	    			
		    	    			etatstockpf.setPrixEntrer(((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(etatstockpf.getPrixEntrer().setScale(6, RoundingMode.DOWN).multiply(etatstockpf.getQuantiteEntrer()))).divide(quantiteTotalEntrer.add(etatstockpf.getQuantiteEntrer()),6, RoundingMode.DOWN));

		    	    			etatstockpf.setQuantiteEntrer(etatstockpf.getQuantiteEntrer().add(quantiteTotalEntrer) );
		    	    			
		    	    			etatstockpf.setMontantEntrer(etatstockpf.getQuantiteEntrer().multiply(etatstockpf.getPrixEntrer()));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    	}
		    	
		    	
    	// calculer Quantite Transfer Entrer et le prix moyen (Les MP transfer En PF )
		    	
		    	
		    boolean existe=false;	
		    	for(int i=0;i<listDetailTransferStockPFEntrerGroupebyPF.size();i++)
		    	{
		    		
		    		existe=false;	
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
		    	    			
		    	    			etatstockpf.setPrixEntrer(((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(etatstockpf.getPrixEntrer().setScale(6, RoundingMode.DOWN).multiply(etatstockpf.getQuantiteEntrer()))).divide(quantiteTotalEntrer.add(etatstockpf.getQuantiteEntrer()),6, RoundingMode.DOWN));

		    	    			etatstockpf.setQuantiteEntrer(etatstockpf.getQuantiteEntrer().add(quantiteTotalEntrer) );
		    	    			
		    	    			etatstockpf.setMontantEntrer(etatstockpf.getQuantiteEntrer().multiply(etatstockpf.getPrixEntrer()));
		    	    			    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}

		    		
		    		
///////////////////////////////////////////////////////////////////////// Les Articles Entrer ( Transfert MM PF)/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		    		
		    		
		for(int t=0;t<listDetailTransferStockPFEntrerArticleTransferMPPF.size();t++)    
		{
			
		DetailTransferProduitFini detailTransferProduitFini=listDetailTransferStockPFEntrerArticleTransferMPPF.get(t);
		
		if(listDetailTransferStockPFEntrerGroupebyPF.get(i).getArticle().getId()== detailTransferProduitFini.getArticle().getId() && listDetailTransferStockPFEntrerGroupebyPF.get(i).getSousFamille().getId()== detailTransferProduitFini.getSousFamille().getId())
		{
			
			existe=true;
			
		}
		
		
		
			
			
			
		}
		    		
		 if(existe==false) 
		 {
			 listDetailTransferStockPFEntrerArticleTransferMPPF.add(listDetailTransferStockPFEntrerGroupebyPF.get(i));
			 
		 }
		    		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		    		
		    		
		    		
		    		
		    		
		    		
		    	}    	
		    	
		    	
		    	// calculer Quantite Transfer Entrer et le prix moyen (Les PF transfer En PF )
		    	
		    	
			      existe=false;	
			    	for(int i=0;i<listDetailTransferStockPFPFEntrerGroupebyPF.size();i++)
			    	{
			    		
			    		existe=false;	
			    		quantiteTotalEntrer=BigDecimal.ZERO;
			    		montantEntrer=new BigDecimal(0);
			    		prixmoyentransferentrer=new BigDecimal(0);
			    		
			    		for(int j=0;j<listDetailTransferStockPFPFEntrer.size();j++)
			    		{
			    			
			    			if(listDetailTransferStockPFPFEntrerGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFPFEntrer.get(j).getArticle().getId() && listDetailTransferStockPFPFEntrerGroupebyPF.get(i).getSousFamille().getId()== listDetailTransferStockPFPFEntrer.get(j).getSousFamille().getId())
			    			{
			    				
			    				montantEntrer=montantEntrer.add(listDetailTransferStockPFPFEntrer.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFPFEntrer.get(j).getQuantite()));
			    				prixmoyentransferentrer=((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(listDetailTransferStockPFPFEntrer.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFPFEntrer.get(j).getQuantite()))).divide(quantiteTotalEntrer.add(listDetailTransferStockPFPFEntrer.get(j).getQuantite()),6, RoundingMode.DOWN) ;

			    				quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFPFEntrer.get(j).getQuantite());
			    				
			    				
			    			}
			    			
			    			
			    		}
			    		
			    		if(!quantiteTotalEntrer.equals(BigDecimal.ZERO))
			    		{
			    			
			    			
			    		   	for(int k=0;k<listEtatStockPF.size();k++)
			    	    	{
			    	    		
			    	    	
			    	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFPFEntrerGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFPFEntrerGroupebyPF.get(i).getSousFamille().getId())
			    	    		{
			    	    			
			    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
			    	    			
			    	    			etatstockpf.setPrixEntrer(((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(etatstockpf.getPrixEntrer().setScale(6, RoundingMode.DOWN).multiply(etatstockpf.getQuantiteEntrer()))).divide(quantiteTotalEntrer.add(etatstockpf.getQuantiteEntrer()),6, RoundingMode.DOWN));

			    	    			etatstockpf.setQuantiteEntrer(etatstockpf.getQuantiteEntrer().add(quantiteTotalEntrer) );
			    	    			
			    	    			etatstockpf.setMontantEntrer(etatstockpf.getQuantiteEntrer().multiply(etatstockpf.getPrixEntrer()));
			    	    			    			
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
			   	
			   	for(int k=0;k<listEtatStockPF.size();k++)
			   	{
			   		
			   	EtatStockPF etatStockPF=listEtatStockPF.get(k);
			   	
			   		if(etatStockPF.getQuantiteInitial().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteAchat().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteAvoir().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteEntrer().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteProduction().setScale(6).equals(BigDecimal.ZERO.setScale(6)) &&  etatStockPF.getQuantiteSortie().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteFinale().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
			   		{
			   			
			   		}else
			   		{
			   			listEtatStockPFImprimer.add(etatStockPF);
			   		}
			   		
			   	}
			   	
		    	
		    	
		    	List<DetailTransferStockMP> listDetailTransferStockMP=new ArrayList<DetailTransferStockMP>();	    	
		    	
		    for(int u=0;u<listEtatStockPFImprimer.size();u++)
		    {
		    	
		    	EtatStockPF etatStockPF=listEtatStockPFImprimer.get(u);
		    	
		    	
		    	if(etatStockPF.getQuantiteSortie().compareTo(BigDecimal.ZERO)>0)
		    	{
		    		
		    		if(etatStockPF.getQuantiteEntrer().compareTo(BigDecimal.ZERO)>0)
		    		{
		    			
		    			for(int y=0;y<listDetailTransferStockPFEntrerArticleTransferMPPF.size();y++)
		    			{
		    				
		    				
		    				if(listDetailTransferStockPFEntrerArticleTransferMPPF.get(y).getArticle().getId()==etatStockPF.getArticle().getId() && listDetailTransferStockPFEntrerArticleTransferMPPF.get(y).getSousFamille().getId()==etatStockPF.getSousFamille().getId())
		    				{
		    					
		    					TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(listDetailTransferStockPFEntrerArticleTransferMPPF.get(y).getTransferStockPF().getCodeTransfer());
		    					if(transferStockMP!=null)
		    					{
		    						
		    						listDetailTransferStockMP=detailTransferStockMPDAO.findByTransferStockMP(transferStockMP.getId());
		    						if(listDetailTransferStockMP.size()!=0)
		    						{
			    						CalculerStockMP(listDetailTransferStockPFEntrerArticleTransferMPPF.get(y).getMagasinSouce(), listDetailTransferStockMP.get(0).getMatierePremier());

			    						
			    					for(int o=0;o<listEtatStockMP.size();o++)	
			    					{
			    						
			    						EtatStockMP etatStockMP=listEtatStockMP.get(o);
			    						
			    						if(etatStockMP.getMp().getId()==listDetailTransferStockMP.get(0).getMatierePremier().getId())
			    						{
			    							
			    							
			    							EtatMargeArticles etatMargeArticles=new EtatMargeArticles();	
			    			    			
			    				    		etatMargeArticles.setArticle(etatStockPF.getArticle());
			    				    		etatMargeArticles.setMatierePremier(listDetailTransferStockMP.get(0).getMatierePremier());
			    				    		etatMargeArticles.setFamilleArticle(etatStockPF.getFamilleArticle());
			    				    		etatMargeArticles.setSousFamille(etatStockPF.getSousFamille());	
			    				    		etatMargeArticles.setMontantInitial(etatStockMP.getMontantInitial());
			    				    		etatMargeArticles.setMontantProduction((etatStockMP.getMontantSortie().add(etatStockMP.getMontantDechet().add(etatStockMP.getMontantOffreProduction().add(etatStockMP.getMontantService().add(etatStockMP.getMontantDechetService().add(etatStockMP.getMontantOffreService())))))).multiply(new BigDecimal(-1)));
			    				    		etatMargeArticles.setMontantAchat(etatStockMP.getMontantAchat());
			    				    		etatMargeArticles.setMontantRRR2020(etatStockPF.getMontantAvoir().add(etatStockPF.getMontantGratuit()));
			    				    		etatMargeArticles.setMontantFinale(etatStockMP.getMontantFinale());
			    				    		etatMargeArticles.setAchatRevendu((etatMargeArticles.getMontantInitial().add(etatMargeArticles.getMontantProduction().add(etatMargeArticles.getMontantAchat()))).subtract(etatMargeArticles.getMontantRRR2020().add(etatMargeArticles.getMontantFinale())));
			    				    		etatMargeArticles.setMontantVente(etatStockPF.getMontantSortie());
			    				    		etatMargeArticles.setMargeArticle(etatMargeArticles.getMontantVente().subtract(etatMargeArticles.getAchatRevendu()));
			    				    		etatMargeArticles.setMargePourcentage(etatMargeArticles.getMargeArticle().divide(etatMargeArticles.getMontantVente(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
			    				    		
			    				    		listEtatMargeArticle.add(etatMargeArticles);
			    							
			    							
			    						}
			    						
			    						
			    					}
			    						
			    						
			    						
		    						}
		    						
		    						
		    						
		    						
		    						
		    						
		    						
		    					}
		    						
		    				
		    					
		    					
		    					
		    					
		    					
		    					
		    					
		    				}
		    					
		    				
		    				
		    				
		    			}
		    			
		    			
		    			
		    		}else
		    		{
		    			
		    		EtatMargeArticles etatMargeArticles=new EtatMargeArticles();	
		    			
		    		etatMargeArticles.setArticle(etatStockPF.getArticle());
		    			
		    		etatMargeArticles.setFamilleArticle(etatStockPF.getFamilleArticle());
		    		etatMargeArticles.setSousFamille(etatStockPF.getSousFamille());	
		    		etatMargeArticles.setMontantInitial(etatStockPF.getMontantInitial());
		    		etatMargeArticles.setMontantProduction(etatStockPF.getMontantProduction());
		    		etatMargeArticles.setMontantAchat(etatStockPF.getMontantAchat());
		    		etatMargeArticles.setMontantRRR2020(etatStockPF.getMontantAvoir().add(etatStockPF.getMontantGratuit()));
		    		etatMargeArticles.setMontantFinale(etatStockPF.getMontantFinale());
		    		etatMargeArticles.setAchatRevendu((etatMargeArticles.getMontantInitial().add(etatMargeArticles.getMontantProduction().add(etatMargeArticles.getMontantAchat()))).subtract(etatMargeArticles.getMontantRRR2020().add(etatMargeArticles.getMontantFinale())));
		    		etatMargeArticles.setMontantVente(etatStockPF.getMontantSortie());
		    		etatMargeArticles.setMargeArticle(etatMargeArticles.getMontantVente().subtract(etatMargeArticles.getAchatRevendu()));
		    		etatMargeArticles.setMargePourcentage(etatMargeArticles.getMargeArticle().divide(etatMargeArticles.getMontantVente(), 6, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
		    		
		    		listEtatMargeArticle.add(etatMargeArticles);
		    		
		    		}
		    		
		    		
		    		
		    		
		    	}
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    }
		    	
		    	
		    	
		    afficher_tableEtatStock(listEtatMargeArticle);	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
	    			
	    		}else
	    		{
	    			

	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
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
	    layeredPane.setBounds(10, 57, 1491, 51);
	    add(layeredPane);
	    
	    JLabel label = new JLabel("Date Debut :");
	    label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label.setBounds(10, 11, 136, 24);
	    layeredPane.add(label);
	    
	     dateChooserdebut = new JDateChooser();
	    dateChooserdebut.setLocale(Locale.FRANCE);
	    dateChooserdebut.setDateFormatString("dd/MM/yyyy");
	    dateChooserdebut.setBounds(101, 11, 163, 26);
	    layeredPane.add(dateChooserdebut);
	    
	    JLabel label_1 = new JLabel("Date Fin :");
	    label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_1.setBounds(274, 9, 106, 24);
	    layeredPane.add(label_1);
	    
	     dateChooserfin = new JDateChooser();
	    dateChooserfin.setLocale(Locale.FRANCE);
	    dateChooserfin.setDateFormatString("dd/MM/yyyy");
	    dateChooserfin.setBounds(348, 9, 169, 26);
	    layeredPane.add(dateChooserfin);
	    
	     comboarticle = new JComboBox();
	     comboarticle.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		
	 			if(!comboarticle.getSelectedItem().equals(""))
		 		{
		 			Articles article=mapArticle.get(comboarticle.getSelectedItem());
		 			txtcodearticle.setText(article.getCodeArticle());
		 			
		 		  				 			
		 		}else
		 		{
//txtcodearticle.setText(" ");
		 			
		 		}
		 	
	 		
	     	}
	     });
	    comboarticle.setBounds(998, 10, 218, 27);
	    layeredPane.add(comboarticle);
		  AutoCompleteDecorator.decorate(comboarticle);
	    comboarticle.addItem("");
	    int i=0;
		while(i<listArticle.size())
		{
			Articles article=listArticle.get(i);
			comboarticle.addItem(article.getLiblle());
			mapArticle.put(article.getLiblle(), article);
			mapCodeArticle.put(article.getCodeArticle(), article);
			
			
			i++;
			
		}
	    
	    
	    JLabel label_2 = new JLabel("Libelle :");
	    label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    label_2.setBounds(953, 10, 45, 26);
	    layeredPane.add(label_2);
	    
	    txtcodearticle = new JTextField();
	    txtcodearticle.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		

	     		

     			if(e.getKeyCode()==e.VK_ENTER)
		      		{
     				
     					
		      			if(!txtcodearticle.getText().equals(""))
		      			{
		      				Articles article=mapCodeArticle.get(txtcodearticle.getText().toUpperCase());
		      				
				    		
				    		if(article!=null)
				    		{	
				    			comboarticle.setSelectedItem(article.getLiblle());
				    			
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
	    txtcodearticle.setText("");
	    txtcodearticle.setColumns(10);
	    txtcodearticle.setBounds(850, 10, 93, 26);
	    layeredPane.add(txtcodearticle);
	    
	    JLabel lblCodeMp = new JLabel("Code Article :");
	    lblCodeMp.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblCodeMp.setBounds(778, 10, 74, 26);
	    layeredPane.add(lblCodeMp);
	    
	    JLabel label_3 = new JLabel("Depot  :");
	    label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    label_3.setBounds(527, 8, 45, 26);
	    layeredPane.add(label_3);
	    
	     combodepot = new JComboBox();
	    combodepot.setBounds(568, 11, 202, 27);
	    layeredPane.add(combodepot);
	    try {
			  
			 
	          Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)util.DateUtils.getCurrentYear()+"-01-01");
	          dateChooserdebut.setDate(date);
	          dateChooserfin.setDate(new Date());
	          
	           comboFamille = new JComboBox();
	          comboFamille.setBounds(1280, 11, 201, 27);
	          layeredPane.add(comboFamille);
	          
	          JLabel lblFamille = new JLabel("Famille  :");
	          lblFamille.setFont(new Font("Tahoma", Font.PLAIN, 11));
	          lblFamille.setBounds(1226, 11, 57, 26);
	          layeredPane.add(lblFamille);
			  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	    JButton button = new JButton("Initialiser");
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		

	     		comboFamille.setSelectedItem("");
	     		comboarticle.setSelectedItem("");
	     		txtcodearticle.setText("");
	     		dateChooserdebut.setCalendar(null);
	     		dateChooserfin.setCalendar(null);
	     		
	     		
	     	
	    		
	    	}
	    });
	    button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button.setBounds(668, 119, 107, 24);
	    add(button);
	    
	    JButton btnExporterExcel = new JButton("Exporter Excel Marge Par Articles");
	    btnExporterExcel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    		
	    		String titre="Etat De Marge Par Article "+mapMagasin.get(combodepot.getSelectedItem()).getLibelle();
	    		String titrefeuille="Etat De Marge Par Article ";
	    		ExporterTableVersExcel.tabletoexcelEtatMargeParArticle(tableEtatStock, titre,titrefeuille);
	    		
	    		}else
	    		{


	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		
	    		}
	    	}
	    });
	    btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnExporterExcel.setBounds(614, 793, 213, 32);
	    btnExporterExcel.setIcon(imgExcel);
	    add(btnExporterExcel);
	    comboFamille.addItem("");
	    
	    JButton btnExporterExcelMarge = new JButton("Exporter Excel Marge Global");
	    btnExporterExcelMarge.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		

	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    			 if(listEtatMargeArticleGlobal.size()!=0)
					 {
	    					String titre="Etat De Marge Global "+mapMagasin.get(combodepot.getSelectedItem()).getLibelle();
	    		    		String titrefeuille="Etat De Marge Global ";
	    		    		ExporterTableVersExcel.tabletoexcelEtatMargeGlobal(listEtatMargeArticleGlobal, titre,titrefeuille);
					 }
	    		
	    	
	    		
	    		}else
	    		{


	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		
	    		}
	    	
	    		
	    		
	    	}
	    });
	    btnExporterExcelMarge.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnExporterExcelMarge.setBounds(157, 793, 213, 32);
	    btnExporterExcelMarge.setIcon(imgExcel);
	    add(btnExporterExcelMarge);
	    
	    JButton btnImprimerMargeGlobal = new JButton("Imprimer Marge Global");
	    btnImprimerMargeGlobal.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {

				BigDecimal TotalInitial=BigDecimal.ZERO;
				BigDecimal TotalProduction=BigDecimal.ZERO;
				BigDecimal TotalAchat=BigDecimal.ZERO;
				BigDecimal TotalRRR=BigDecimal.ZERO;
				BigDecimal TotalRRRCredit=BigDecimal.ZERO;
				BigDecimal TotalFinale=BigDecimal.ZERO;
				BigDecimal TotalAchatRevandu=BigDecimal.ZERO;
				BigDecimal Totalvente=BigDecimal.ZERO;
				
				 if(listEtatMargeArticle.size()!=0)
				 {
					 listEtatMargeArticleGlobal.clear();
					 
					 for(int i=0;i<listEtatMargeArticle.size();i++)
					 {
						 EtatMargeArticles etatMargeArticles=listEtatMargeArticle.get(i);
						 
						 TotalInitial=TotalInitial.add(etatMargeArticles.getMontantInitial());
						 TotalProduction=TotalProduction.add(etatMargeArticles.getMontantProduction());
						 TotalAchat=TotalAchat.add(etatMargeArticles.getMontantAchat());
						 TotalRRR=TotalRRR.add(etatMargeArticles.getMontantRRR2020());
						 TotalFinale=TotalFinale.add(etatMargeArticles.getMontantFinale());
						 TotalAchatRevandu=TotalAchatRevandu.add(etatMargeArticles.getAchatRevendu());
						 Totalvente=Totalvente.add(etatMargeArticles.getMontantVente());
						 
					 }
					 
					 EtatMargeArticles etatMargeArticles=new EtatMargeArticles();
					 etatMargeArticles.setTotalInitial(TotalInitial);
					 etatMargeArticles.setTotalProduction(TotalProduction);
					 etatMargeArticles.setTotalAchat(TotalAchat);
					 etatMargeArticles.setTotalAchatRevendu(TotalAchatRevandu);
					 etatMargeArticles.setTotalRRR2020(TotalRRR);
					 etatMargeArticles.setTotalRRRCredit(TotalRRRCredit);
					 etatMargeArticles.setTotalFinale(TotalFinale);
					 etatMargeArticles.setTotalVente(Totalvente);
					 etatMargeArticles.setTotalMarge (etatMargeArticles.getTotalMarge());
					 etatMargeArticles.setTotalMargePourcentage(etatMargeArticles.getTotalMargePourcentage());
					 
					 listEtatMargeArticleGlobal.add(etatMargeArticles);
					 
					 
						Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
			    		
			    		if(magasin!=null )
			    		{
			    			
			    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
			    			String d2=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
			    		
			    		 titre="Etat De Marge Global : "+magasin.getLibelle() +" Entre : "+d1 +" Et : "+d2;
			    		
			    		
			    		}else
			    		{


			    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin  SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
			    			return;
			    		
			    		
			    		} 
					
						Map parameters = new HashMap();

	
						parameters.put("titre", titre);
						JasperUtils.imprimerEtatMargeGlobal(listEtatMargeArticleGlobal, parameters); 
						
				 } else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun Etat Stock  ", "Erreur", JOptionPane.ERROR_MESSAGE); 
					 return;
				 }
					
				
				
			
	    		
	    		
	    	}
	    });
	    btnImprimerMargeGlobal.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnImprimerMargeGlobal.setBounds(380, 793, 195, 32);
	    btnImprimerMargeGlobal.setIcon(imgImprimer);
	    add(btnImprimerMargeGlobal);
	    
	    //button_1.setVisible(false);
	    
	  for(int k=0;k<listFamilleArticlesF.size();k++)
	  {
		  FamilleArticlePF familleArticle=listFamilleArticlesF.get(k);
		  comboFamille.addItem(familleArticle.getLiblle());
		  mapFamilleArticles.put(familleArticle.getLiblle(), familleArticle);
	  }
	     if(utilisateur.getLogin().equals("admin"))
	 		  {
	 	    	listMagasin =depotdao.listeMagasinByTypeMagasin(MAGASIN_CODE_TYPE_PF);
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
	 				  listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_PF);
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
	    
	    
	    
		
		}
	
	

	
	
	

	
	void InitialiseTableauDetailMouvementStock()
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article","Famille","Sous Famille", "Quantite Initial", "Prix Initial","Montant Initial", "Quantite Achat", "Prix Achat","Montant Achat","Quantite Production", "Prix Production","Montant Production","Quantite Transfer Entrer", "Prix Transfer Entrer","Montant Transfer Entrer","Quantite Vente", "Prix Vente","Montant Vente", "Quantite Gratuit", "Prix Gratuit","Montant Gratuit","Quantite Avoir", "Prix Avoir","Montant Avoir","Quantite Finale", "Prix Finale","Montant Finale","Marge"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
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
	       tableEtatStock.getColumnModel().getColumn(10).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(10).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(11).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(12).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(13).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(14).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(15).setPreferredWidth(99);
	       tableEtatStock.getColumnModel().getColumn(16).setPreferredWidth(99);
		
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
	
	
	void Calculer_Prix_Moyenne_PF()
	{
		

		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		listMouvementStockPF.clear();
		detailTransferStockPFDAO.ViderSession();
		boolean trouve=false;
		Articles article=mapArticle.get(comboarticle.getSelectedItem());
		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
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
    		String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_VENTE,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,Constantes.ETAT_TRANSFER_STOCK_AVOIR ,ETAT_TRANSFER_STOCK_ENTRER_MP,ETAT_TRANSFER_STOCK_ENTRER_PF_MP,ETAT_TRANSFER_STOCK_AVOIR_R,ETAT_TRANSFER_STOCK_ENTRER_PF_PF,ETAT_TRANSFER_STOCK_SORTIE_PF_PF};
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
    				
    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SORTIE_PF_PF))
    			{
    				vente=vente.add(listDetailTransferStockPF.get(j).getQuantite());
    				
    			}
    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_INITIAL))
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
    				
    				
    				
    				
    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR_R))
    			{ 
    				
    					avoir=avoir.add(listDetailTransferStockPF.get(j).getQuantite());	
    				
    				
    				
    				
    				
    			}
    			
    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_MP))
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
 				
 				
 				
 			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_PF_PF))
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

				System.out.println( "new "+mouvementstockPF.getArticle().getLiblle() + " : "+mouvementstockPF.getSousFamille().getLiblle() +" : Prix Moyenne :"+ prixmoyenne );

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
	
	
	void afficher_tableEtatStock(List<EtatMargeArticles> listEtatMargeArticles)
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"MATIERE PREMIERE",	"Article","Famille","Sous Famille","STOCK INITIAL", "PRODUCTION","LES ACHATS","RRR 2020","STOCK FINAL", "ACHATS REVENDU","CHIFFRE D'AFFAIRE", "MARGE PAR ARTICLES", "% (par articles)"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modeleEtatStock);
		int i=0;
		String mp="";
		 
		while(i<listEtatMargeArticles.size())
		{	
			EtatMargeArticles etatStocEtatMargeArticles=listEtatMargeArticles.get(i);
			mp="";
		if(etatStocEtatMargeArticles.getMatierePremier()!=null)
		{
			mp=etatStocEtatMargeArticles.getMatierePremier().getNom();
		}
			
				//Object []ligne={EtatStockPF.getArticle().getLiblle(),EtatStockPF.getFamilleArticle().getLiblle(), EtatStockPF.getSousFamille().getLiblle(),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteInitial().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixInitial().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantInitial().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteAchat().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixAchat().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantAchat().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteProduction().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixProduction().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantProduction().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteEntrer().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixEntrer().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantEntrer().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteSortie().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixSortie().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantSortie().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteGratuit().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixGratuit().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantGratuit().setScale(6, RoundingMode.DOWN)) , NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteAvoir().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixAvoir().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantAvoir().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteFinale().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixFinale().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantFinale().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMarge().setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP))+"%"};
	
		        Object []ligne={mp,etatStocEtatMargeArticles.getArticle().getLiblle(),etatStocEtatMargeArticles.getFamilleArticle().getLiblle(), etatStocEtatMargeArticles.getSousFamille().getLiblle(),etatStocEtatMargeArticles.getMontantInitial().setScale(6, RoundingMode.DOWN),etatStocEtatMargeArticles.getMontantProduction().setScale(6, RoundingMode.DOWN),etatStocEtatMargeArticles.getMontantAchat().setScale(6, RoundingMode.DOWN),etatStocEtatMargeArticles.getMontantRRR2020().setScale(6, RoundingMode.DOWN),etatStocEtatMargeArticles.getMontantFinale().setScale(6, RoundingMode.DOWN),etatStocEtatMargeArticles.getAchatRevendu().setScale(6, RoundingMode.DOWN),etatStocEtatMargeArticles.getMontantVente().setScale(6, RoundingMode.DOWN),etatStocEtatMargeArticles.getMargeArticle().setScale(6, RoundingMode.DOWN),etatStocEtatMargeArticles.getMargePourcentage().setScale(2, RoundingMode.DOWN)};

				modeleEtatStock.addRow(ligne);
			
			
			i++;
		}
}
	
	
	
	public void CalculerStockMP( Magasin magasin ,MatierePremier mp)
	{
		

		detailTransferStockMPDAO.ViderSession();
		listEtatStockMP.clear();
		listDetailTransferStockMPInitial.clear() ;
		 listDetailTransferStockMPAchat.clear();
		 listDetailTransferStockMPAchatGroupebyMP.clear();
		 listDetailTransferStockMPSortie.clear();
		 listDetailTransferStockMPSortieGroupebyMP.clear();
		 listDetailTransferStockMPAvoir.clear();
		 listDetailTransferStockMPAvoirGroupebyMP.clear();
		 listDetailTransferStockMPChargeSupp.clear();
		 listDetailTransferStockMPChargeSuppGroupebyMP.clear();
		  listDetailTransferStockMPService.clear();
		 listDetailTransferStockMPServiceGroupebyMP.clear();
		 listDetailTransferStockMPVente.clear();
	     listDetailTransferStockMPVenteGroupebyMP.clear();
		 listDetailTransferStockMPAllMP.clear();
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		BigDecimal montantInitial=new BigDecimal(0);
		BigDecimal quantiteTotalInitial=new BigDecimal(0);
		BigDecimal PrixInitial=new BigDecimal(0);
		BigDecimal montantachat=new BigDecimal(0);
		BigDecimal quantiteTotalachat=new BigDecimal(0);
		BigDecimal montantavoir=new BigDecimal(0);
		BigDecimal quantiteTotalavoir=new BigDecimal(0);
		BigDecimal quantiteTotalsortie=new BigDecimal(0);
		BigDecimal montantTotalsortie=new BigDecimal(0);
		BigDecimal quantiteTotalFinale=new BigDecimal(0);
		BigDecimal montantFinale=new BigDecimal(0);
		BigDecimal montantchargesupp=new BigDecimal(0);
		BigDecimal quantiteTotalchargesupp=new BigDecimal(0);
		BigDecimal montantService=new BigDecimal(0);
		BigDecimal quantiteTotalService=new BigDecimal(0);
		BigDecimal montantVente=new BigDecimal(0);
		BigDecimal quantiteTotalVente=new BigDecimal(0);
		BigDecimal montantDechet=new BigDecimal(0);
		BigDecimal quantiteTotalDechet=new BigDecimal(0);
		BigDecimal montantDechetService=new BigDecimal(0);
		BigDecimal quantiteTotalDechetService=new BigDecimal(0);
		BigDecimal montantTransferMPPF=new BigDecimal(0);
		BigDecimal quantiteTotalTransferMPPF=new BigDecimal(0);
		BigDecimal quantiteDechetaSupprimer=new BigDecimal(0);
		BigDecimal quantiteOffreaSupprimer=new BigDecimal(0);
		BigDecimal quantiteDechetaSupprimerService=new BigDecimal(0);
		BigDecimal quantiteOffreaSupprimerService=new BigDecimal(0);
		BigDecimal montantOffreService=new BigDecimal(0);
		BigDecimal quantiteTotalOffreService=new BigDecimal(0);
		BigDecimal montantOffreProduction=new BigDecimal(0);
		BigDecimal quantiteTotalOffreProduction=new BigDecimal(0);
		
		boolean trouve=false;
		
		
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
    		
    		if(mp!=null)
    		{
    			titre="Etat Emballage "+mp.getNom() +" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
    		}else
    		{
    			titre="Etat Emballage de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
    		}
    		
    		}
    		
    		
    		if(dateChooserdebut.getDate()==null && mp!=null)
    		{
    			dateChooserfin.setCalendar(null);
    			titre="Etat Emballage de "+mp.getNom() +" au magasin : "+magasin.getLibelle();
    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
    		{
    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
    			titre="Etat Emballage de "+mp.getNom() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
    			
    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
    		{
    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
    			titre="Etat Emballage de magasin : "+magasin.getLibelle()+ "entre "+d1 +" et "+d1;
    		}	
    		
    		
    		listDetailTransferStockMPInitial=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutInitial(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_INITIAL,magasin);
    		listDetailTransferStockMPAchat=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_ACHAT,magasin);
    		
    		listDetailTransferStockMPAchatGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAchat(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_ACHAT,magasin);
    		listDetailTransferStockMPSortie=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE,magasin);
    		listDetailTransferStockMPSortieGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutCharge(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE,magasin);
    		
    		listDetailTransferStockMPAvoir=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAvoir(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_AVOIR,magasin);
    		listDetailTransferStockMPAvoirGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutAvoir(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_AVOIR,magasin);
    		
    		listDetailTransferStockMPChargeSupp=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutChargeSupp(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin);
    		listDetailTransferStockMPChargeSuppGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutChargeSupp(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_CHARGE_SUPP,magasin);
    		
    		listDetailTransferStockMPService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutService(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SERVICE,magasin);
    		listDetailTransferStockMPServiceGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutService(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SERVICE,magasin);
    		

    		listDetailTransferStockMPVente=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_VENTE,magasin);
    		listDetailTransferStockMPVenteGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_VENTE,magasin);
    		
    		listDetailTransferStockMPTransferMPPFProduction=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF,magasin);
    		listDetailTransferStockMPTransferMPPFProductionGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF,magasin);
    		
    		listDetailTransferStockMPTransferMPPF=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasin);
    		listDetailTransferStockMPTransferMPPFGroupebyMP=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_MP_PF,magasin);
    		
    		
    		
    		listDetailTransferStockMPTransferMPPFService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE,magasin);
    		listDetailTransferStockMPTransferMPPFGroupebyMPService=detailTransferStockMPDAO.ListTransferStockMPEntreDeuxDatesBYMPDistinctByStatutVente(dateChooserdebut.getDate(), dateChooserfin.getDate(), mp, ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE,magasin);
    		
    		
    		
    		listDetailTransferStockMPAllMP=	detailTransferStockMPDAO.findAllTransferStockMPGroupeByMP(magasin);
    		
    		for(int p=0;p<listDetailTransferStockMPAllMP.size();p++)
    		{
    			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPAllMP.get(p);
    			EtatStockMP etatstock=new EtatStockMP();
    			
    			etatstock.setMp(detailtransferstockmp.getMatierePremier());
    			
    			if(detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
    			{
    			
    				SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnvracDAo.findByMP(detailtransferstockmp.getMatierePremier());
    				
    				
    				if(sousFamilleEnVrac!=null)
    				{
    					
    					etatstock.setSousFamille(sousFamilleEnVrac.getSousfamile().getLiblle());
    					etatstock.setFamille(sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle());
    				}else
    				{
    					
    					etatstock.setSousFamille(detailtransferstockmp.getMatierePremier().getCategorieMp().getNom());
    					etatstock.setFamille(detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
    					
    				}
    				
    				
    				
    			}else
    			{
    				
    				etatstock.setSousFamille(detailtransferstockmp.getMatierePremier().getCategorieMp().getNom());
					etatstock.setFamille(detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
    				
    				
    			}
    			
    			
    			
    			etatstock.setQuantiteInitial(BigDecimal.ZERO);
    			etatstock.setPrixInitial(BigDecimal.ZERO);
    			etatstock.setMontantInitial(BigDecimal.ZERO);
    			etatstock.setQuantiteAchat(BigDecimal.ZERO);
    			etatstock.setPrixAchat(BigDecimal.ZERO);
    			etatstock.setMontantAchat(BigDecimal.ZERO);
    			etatstock.setQuantiteSortie(BigDecimal.ZERO);
    			etatstock.setPrixSortie(BigDecimal.ZERO);
    			etatstock.setMontantSortie(BigDecimal.ZERO);
    			etatstock.setQuantiteService(BigDecimal.ZERO);
    			etatstock.setPrixService(BigDecimal.ZERO);
    			etatstock.setMontantService(BigDecimal.ZERO);
    			etatstock.setQuantiteDechetService(BigDecimal.ZERO);
    			etatstock.setPrixDechetService(BigDecimal.ZERO);
    			etatstock.setMontantDechetService(BigDecimal.ZERO);
    			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
    			etatstock.setPrixAvoir(BigDecimal.ZERO);
    			etatstock.setMontantAvoir(BigDecimal.ZERO);
    			etatstock.setMarge(BigDecimal.ZERO);
    			etatstock.setQuantiteDechet(BigDecimal.ZERO);
    			etatstock.setPrixDechet(BigDecimal.ZERO);
    			etatstock.setMontantDechet(BigDecimal.ZERO);
    			etatstock.setQuantiteOffreService(BigDecimal.ZERO);
    			etatstock.setPrixOffreService(BigDecimal.ZERO);
    			etatstock.setMontantOffreService(BigDecimal.ZERO);
    			etatstock.setQuantiteOffreProduction(BigDecimal.ZERO);
    			etatstock.setPrixOffreProduction(BigDecimal.ZERO);
    			etatstock.setMontantOffreProduction(BigDecimal.ZERO);
    			etatstock.setQuantiteTransfertMPPF(BigDecimal.ZERO);
    			etatstock.setPrixTransfertMPPF(BigDecimal.ZERO);
    			etatstock.setMontantTransfertMPPF(BigDecimal.ZERO);
    			
    			listEtatStockMP.add(etatstock);
    			
    		}
    		
    		// charger list Etat stock MP initialiser les enregistrement des achats et ventes par zero
    		for(int i=0;i<listDetailTransferStockMPInitial.size();i++)
    		{
    			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPInitial.get(i);
    			
    			PrixInitial=detailtransferstockmp.getPrixUnitaire();
    			quantiteTotalInitial=detailtransferstockmp.getQuantite();
    			montantInitial=quantiteTotalInitial.multiply(PrixInitial);
    			
    			/*
    			EtatStockMP etatstock=new EtatStockMP();
    			etatstock.setMp(detailtransferstockmp.getMatierePremier());
    			etatstock.setQuantiteInitial(detailtransferstockmp.getQuantite());
    			etatstock.setPrixInitial(detailtransferstockmp.getPrixUnitaire());
    			etatstock.setMontantInitial(detailtransferstockmp.getQuantite().multiply(detailtransferstockmp.getPrixUnitaire()));
    			etatstock.setQuantiteAchat(BigDecimal.ZERO);
    			etatstock.setPrixAchat(BigDecimal.ZERO);
    			etatstock.setMontantAchat(BigDecimal.ZERO);
    			etatstock.setQuantiteSortie(BigDecimal.ZERO);
    			etatstock.setPrixSortie(BigDecimal.ZERO);
    			etatstock.setMontantSortie(BigDecimal.ZERO);
    			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
    			etatstock.setPrixAvoir(BigDecimal.ZERO);
    			etatstock.setMontantAvoir(BigDecimal.ZERO);
    			listEtatStockMP.add(etatstock);
    		*/
    			
    			if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
	    		{
	    			
	    			for(int p=0;p<listEtatStockMP.size();p++)
	    	    	{
	    				if(listEtatStockMP.get(p).getMp().equals(listDetailTransferStockMPInitial.get(i).getMatierePremier()))
		    			{
	    					EtatStockMP etatstockmp=listEtatStockMP.get(p);
	    					if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
	    					{
	    						etatstockmp.setQuantiteInitial(quantiteTotalInitial.setScale(0, RoundingMode.CEILING));
			    				etatstockmp.setPrixInitial(PrixInitial);
			    				etatstockmp.setMontantInitial(quantiteTotalInitial.setScale(0, RoundingMode.CEILING).multiply(PrixInitial));
			    				listEtatStockMP.set(p, etatstockmp);
	    					}else
	    					{
	    						etatstockmp.setQuantiteInitial(quantiteTotalInitial);
			    				etatstockmp.setPrixInitial(PrixInitial);
			    				etatstockmp.setMontantInitial(quantiteTotalInitial.multiply(PrixInitial));
			    				listEtatStockMP.set(p, etatstockmp);
	    					}
		    				
		    			
		    			}
	    	    	}
	    			
	    		}
    		}
    		
    		
    		// calculer le prix moyen et quantite achat
    		
    		
    	for(int j=0;j<listDetailTransferStockMPAchatGroupebyMP.size();j++)
    	{
    		montantachat=new BigDecimal(0);
    		quantiteTotalachat=new BigDecimal(0);
    		
    	for(int k=0;k<listDetailTransferStockMPAchat.size();k++)
    	{
    		
    		if(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPAchat.get(k).getMatierePremier()))
    		{
    			
    			if(listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPAchat.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
    				montantachat=montantachat.add(listDetailTransferStockMPAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAchat.get(k).getQuantite()));
	    			quantiteTotalachat=quantiteTotalachat.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPAchat.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
				}else
				{
					montantachat=montantachat.add(listDetailTransferStockMPAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAchat.get(k).getQuantite()));
	    			quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockMPAchat.get(k).getQuantite());
				}
    
    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
    			
    		}
    		
    		
    	}
    		if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
    		{
    			/*	    			
    			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
    			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
    			*/
    			
    			for(int i=0;i<listEtatStockMP.size();i++)
    	    	{
    				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier()))
	    			{
    					EtatStockMP etatstockmp=listEtatStockMP.get(i);
    					
    					if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
    					{
    						etatstockmp.setQuantiteAchat(quantiteTotalachat.setScale(0, RoundingMode.CEILING));
		    				etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
		    				listEtatStockMP.set(i, etatstockmp);
    						
    						
    					}else
    					{
    						
		    				etatstockmp.setQuantiteAchat(quantiteTotalachat);
		    				etatstockmp.setPrixAchat(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantAchat(etatstockmp.getQuantiteAchat().multiply(etatstockmp.getPrixAchat()));
		    				listEtatStockMP.set(i, etatstockmp);
    					}
    					
	    				
	    				
	    			}
    	    	}
    			
    			
    		}
    		
    	}
    		
    	// charger les new value de stock achat dans la liste Etat Stock MP	
    	/*
    	for(int i=0;i<listEtatStockMP.size();i++)
    	{
    		for(int j=0;j<listDetailTransferStockMPAchatGroupebyMP.size();j++)
    		{
    			if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier()))
    			{
    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
    				etatstockmp.setQuantiteAchat(listDetailTransferStockMPAchatGroupebyMP.get(j).getQuantite());
    				etatstockmp.setPrixAchat(listDetailTransferStockMPAchatGroupebyMP.get(j).getPrixUnitaire());
    				etatstockmp.setMontantAchat(listDetailTransferStockMPAchatGroupebyMP.get(j).getQuantite().multiply(listDetailTransferStockMPAchatGroupebyMP.get(j).getPrixUnitaire()));
    				listEtatStockMP.set(i, etatstockmp);
    				
    			}
    			
    			
    		}
    		
    	}
    	*/
    	
    	// calculer Quantite Sortie (charge)
    	
    	
    	
    	
    	for(int i=0;i<listDetailTransferStockMPSortieGroupebyMP.size();i++)
    	{
    		
    		quantiteTotalsortie=BigDecimal.ZERO;
    		montantTotalsortie=BigDecimal.ZERO;
    		quantiteTotalDechet=BigDecimal.ZERO;
    		quantiteTotalOffreProduction=BigDecimal.ZERO;
    		montantDechet=BigDecimal.ZERO;
    		montantOffreProduction=BigDecimal.ZERO;
    		
    		for(int j=0;j<listDetailTransferStockMPSortie.size();j++)
    		{
    			
    			if(listDetailTransferStockMPSortieGroupebyMP.get(i).getMatierePremier().equals(listDetailTransferStockMPSortie.get(j).getMatierePremier()))
    			{
    				if(listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPSortie.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
					{
    					quantiteTotalsortie=quantiteTotalsortie.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPSortie.get(j).getQuantite().setScale(0, RoundingMode.CEILING));
    					montantTotalsortie=montantTotalsortie.add(listDetailTransferStockMPSortie.get(j).getQuantite().setScale(0, RoundingMode.CEILING).multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));
    				
    					if(listDetailTransferStockMPSortie.get(j).getQuantiteDechet()!=null)
						{
	    					quantiteTotalDechet=quantiteTotalDechet.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPSortie.get(j).getQuantiteDechet().setScale(0, RoundingMode.CEILING));
	    					montantDechet=montantDechet.add(listDetailTransferStockMPSortie.get(j).getQuantiteDechet().setScale(0, RoundingMode.CEILING).multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));
					
						}
    					if(listDetailTransferStockMPSortie.get(j).getQuantiteOffre()!=null)
						{
    						quantiteTotalOffreProduction=quantiteTotalOffreProduction.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPSortie.get(j).getQuantiteOffre().setScale(0, RoundingMode.CEILING));
    						montantOffreProduction=montantOffreProduction.add(listDetailTransferStockMPSortie.get(j).getQuantiteOffre().setScale(0, RoundingMode.CEILING).multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));
						}
    					
					
					}else
					{
						quantiteTotalsortie=quantiteTotalsortie.add(listDetailTransferStockMPSortie.get(j).getQuantite());
						montantTotalsortie=montantTotalsortie.add(listDetailTransferStockMPSortie.get(j).getQuantite().multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));
					
						
						if(listDetailTransferStockMPSortie.get(j).getQuantiteDechet()!=null)
						{
							quantiteTotalDechet=quantiteTotalDechet.add(listDetailTransferStockMPSortie.get(j).getQuantiteDechet());
	    					montantDechet=montantDechet.add(listDetailTransferStockMPSortie.get(j).getQuantiteDechet().multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));

						}
						if(listDetailTransferStockMPSortie.get(j).getQuantiteOffre()!=null)
						{
							quantiteTotalOffreProduction=quantiteTotalOffreProduction.add(listDetailTransferStockMPSortie.get(j).getQuantiteOffre());
    						montantOffreProduction=montantOffreProduction.add(listDetailTransferStockMPSortie.get(j).getQuantiteOffre().multiply(listDetailTransferStockMPSortie.get(j).getPrixUnitaire()));

						}
						
					}
    				
    				
    			}
    			
    			
    		}
    		
	    	
    		
    		if(!quantiteTotalsortie.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !montantTotalsortie.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
    		{
    			/*
    			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPSortieGroupebyMP.get(i);
    			detailtransferstockmp.setQuantite(quantiteTotalsortie);
    			listDetailTransferStockMPSortieGroupebyMP.set(i, detailtransferstockmp);
    			*/
    			
    			// charger les new valeurs de Stock Sortie (charge) dans la liste Etat Stock 
		    	
		    	for(int k=0;k<listEtatStockMP.size();k++)
		    	{
		    
		    		if(listEtatStockMP.get(k).getMp().equals(listDetailTransferStockMPSortieGroupebyMP.get(i).getMatierePremier()))
		    		{
		    			EtatStockMP etatstockmp=listEtatStockMP.get(k);
		    			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
    					{
			    			etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantTotalsortie)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalsortie.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

		    				etatstockmp.setQuantiteSortie(quantiteTotalsortie.setScale(0, RoundingMode.CEILING));
			    			etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
			    		
			    			
    					}else
    					{
    						
			    			etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantTotalsortie)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalsortie),6,RoundingMode.HALF_UP));

    						etatstockmp.setQuantiteSortie(quantiteTotalsortie);
			    			etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));	
			    		
    					}
		    			
		    			
		    			listEtatStockMP.set(k, etatstockmp);
		    		}
		    		
		    	}
    			
    		}
    		
    		
    		
    		/// inserer la quantite dechet , prix dechet et montant dechet
    		
    		if(!quantiteTotalDechet.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !montantDechet.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
    		{
    			/*
    			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPSortieGroupebyMP.get(i);
    			detailtransferstockmp.setQuantite(quantiteTotalsortie);
    			listDetailTransferStockMPSortieGroupebyMP.set(i, detailtransferstockmp);
    			*/
    			
    			// charger les new valeurs de Stock Sortie (charge) dans la liste Etat Stock 
		    	
		    	for(int k=0;k<listEtatStockMP.size();k++)
		    	{
		    
		    		if(listEtatStockMP.get(k).getMp().equals(listDetailTransferStockMPSortieGroupebyMP.get(i).getMatierePremier()))
		    		{
		    			EtatStockMP etatstockmp=listEtatStockMP.get(k);
		    			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
    					{
		    				etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().add(quantiteTotalDechet.setScale(0, RoundingMode.CEILING)) );
			    			etatstockmp.setPrixDechet(montantDechet.divide(quantiteTotalDechet.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
			    			etatstockmp.setMontantDechet (etatstockmp.getQuantiteDechet().multiply(etatstockmp.getPrixDechet()));
			    			
			    			
    					}else
    					{
    						etatstockmp.setQuantiteDechet(etatstockmp.getQuantiteDechet().add(quantiteTotalDechet));
			    			etatstockmp.setPrixDechet(montantDechet.divide(quantiteTotalDechet,6,RoundingMode.HALF_UP));
			    			etatstockmp.setMontantDechet(etatstockmp.getQuantiteDechet().multiply(etatstockmp.getPrixDechet()));					    			
    					}
		    			
		    			
		    			listEtatStockMP.set(k, etatstockmp);
		    		}
		    		
		    		
		    	}
    			
    			
    		}
    		
    		
    		
		/// inserer la quantite Offre , prix Offre et montant Offre (production)
    		
    		if(!quantiteTotalOffreProduction.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !montantOffreProduction.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
    		{
    			/*
    			DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMPSortieGroupebyMP.get(i);
    			detailtransferstockmp.setQuantite(quantiteTotalsortie);
    			listDetailTransferStockMPSortieGroupebyMP.set(i, detailtransferstockmp);
    			*/
    			
    			// charger les new valeurs de Stock Sortie (charge) dans la liste Etat Stock 
		    	
		    	for(int k=0;k<listEtatStockMP.size();k++)
		    	{
		    
		    		if(listEtatStockMP.get(k).getMp().equals(listDetailTransferStockMPSortieGroupebyMP.get(i).getMatierePremier()))
		    		{
		    			EtatStockMP etatstockmp=listEtatStockMP.get(k);
		    			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
    					{
		    				etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction().add(quantiteTotalOffreProduction.setScale(0, RoundingMode.CEILING)));
			    			etatstockmp.setPrixOffreProduction(montantOffreProduction.divide(quantiteTotalOffreProduction.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
			    			etatstockmp.setMontantOffreProduction (etatstockmp.getQuantiteOffreProduction().multiply(etatstockmp.getPrixOffreProduction()));
			    			
    					}else
    					{
    						etatstockmp.setQuantiteOffreProduction(etatstockmp.getQuantiteOffreProduction().add(quantiteTotalOffreProduction));
			    			etatstockmp.setPrixOffreProduction(montantOffreProduction.divide(quantiteTotalOffreProduction,6,RoundingMode.HALF_UP));
			    			etatstockmp.setMontantOffreProduction(etatstockmp.getQuantiteOffreProduction().multiply(etatstockmp.getPrixOffreProduction()));					    			
    					}
		    			
		    			listEtatStockMP.set(k, etatstockmp);
		    		}
		    		
		    	}
    			
    			
    		}
    		
    		
    		
    	}
    	
    
    
    	
    	// calculer le prix moyen et quantite Avoir
		
		
    	for(int j=0;j<listDetailTransferStockMPAvoirGroupebyMP.size();j++)
    	{
    		montantavoir=new BigDecimal(0);
    		quantiteTotalavoir=new BigDecimal(0);
    		
    	for(int k=0;k<listDetailTransferStockMPAvoir.size();k++)
    	{
    		
    		if(listDetailTransferStockMPAvoirGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPAvoir.get(k).getMatierePremier()))
    		{
    			if(listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPAvoir.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
    				montantavoir=montantavoir.add(listDetailTransferStockMPAvoir.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAvoir.get(k).getQuantite()));
	    			quantiteTotalavoir=quantiteTotalavoir.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPAvoir.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
				}else
				{
					montantavoir=montantavoir.add(listDetailTransferStockMPAvoir.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPAvoir.get(k).getQuantite()));
	    			quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockMPAvoir.get(k).getQuantite());
				}
    		
    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
    			
    		}
    		
    		
    	}
    		if(!montantavoir.equals(BigDecimal.ZERO) && !quantiteTotalavoir.equals(BigDecimal.ZERO))
    		{
    			/*	    			
    			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
    			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
    			*/
    			
    			for(int i=0;i<listEtatStockMP.size();i++)
    	    	{
    				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPAvoirGroupebyMP.get(j).getMatierePremier()))
	    			{
	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
    					{
	    					etatstockmp.setQuantiteAvoir(quantiteTotalavoir.setScale(0, RoundingMode.CEILING));
		    				etatstockmp.setPrixAvoir(montantavoir.divide(quantiteTotalavoir.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantAvoir(etatstockmp.getQuantiteAvoir().multiply(etatstockmp.getPrixAvoir()));
		    				listEtatStockMP.set(i, etatstockmp);
    					}else
    					{
    						etatstockmp.setQuantiteAvoir(quantiteTotalavoir);
		    				etatstockmp.setPrixAvoir(montantavoir.divide(quantiteTotalavoir,6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantAvoir(etatstockmp.getQuantiteAvoir().multiply(etatstockmp.getPrixAvoir()));
		    				listEtatStockMP.set(i, etatstockmp);
    					}
		    			
	    				
	    			}
    	    	}
    				
    		}
    		
    	}
    	
    	
// calculer le prix moyen et quantite ChargeSupp
		
		
    	for(int j=0;j<listDetailTransferStockMPChargeSuppGroupebyMP.size();j++)
    	{
    		montantchargesupp=new BigDecimal(0);
    		quantiteTotalchargesupp=new BigDecimal(0);
    		
    	for(int k=0;k<listDetailTransferStockMPChargeSupp.size();k++)
    	{
    		
    		if(listDetailTransferStockMPChargeSuppGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPChargeSupp.get(k).getMatierePremier()))
    		{
    			if(listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPChargeSupp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
    				montantchargesupp=montantchargesupp.add(listDetailTransferStockMPChargeSupp.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPChargeSupp.get(k).getQuantite()));
	    			quantiteTotalchargesupp=quantiteTotalchargesupp.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPChargeSupp.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
				}else
				{
					montantchargesupp=montantchargesupp.add(listDetailTransferStockMPChargeSupp.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPChargeSupp.get(k).getQuantite()));
	    			quantiteTotalchargesupp=quantiteTotalchargesupp.add(listDetailTransferStockMPChargeSupp.get(k).getQuantite());
				}
    			
    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
    			
    		}
    		
    		
    	}
    		if(!montantchargesupp.equals(BigDecimal.ZERO) && !quantiteTotalchargesupp.equals(BigDecimal.ZERO))
    		{
    			/*	    			
    			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
    			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
    			*/
    			
    			for(int i=0;i<listEtatStockMP.size();i++)
    	    	{
    				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPChargeSuppGroupebyMP.get(j).getMatierePremier()))
	    			{
	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
    					{
		    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantchargesupp)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalchargesupp.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

	    					etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalchargesupp.setScale(0, RoundingMode.CEILING)));
		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
		    				listEtatStockMP.set(i, etatstockmp);
	    					
    					}else
    					{
		    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantchargesupp)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalchargesupp),6,RoundingMode.HALF_UP));

    						etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalchargesupp));
		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
		    				listEtatStockMP.set(i, etatstockmp);
    					}
	    				
	    				
	    			}
    	    	}
    				
    		}
    		
    	}
    	
    	
    	
    	
//calculer le prix moyen et quantite Service
		
		
    	for(int j=0;j<listDetailTransferStockMPServiceGroupebyMP.size();j++)
    	{
    		montantService=new BigDecimal(0);
    		quantiteTotalService=new BigDecimal(0);
    		montantDechetService=new BigDecimal(0);
    		quantiteTotalDechetService=new BigDecimal(0);
    		montantOffreService=new BigDecimal(0);
    		quantiteTotalOffreService=new BigDecimal(0);
    		
    	for(int k=0;k<listDetailTransferStockMPService.size();k++)
    	{
    		
    		if(listDetailTransferStockMPServiceGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPService.get(k).getMatierePremier()))
    		{
    			if(listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
    				montantService=montantService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantite()));
	    			quantiteTotalService=quantiteTotalService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
	    			
	    			montantDechetService=montantDechetService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteDechet()));
	    			quantiteTotalDechetService=quantiteTotalDechetService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).getQuantiteDechet().setScale(0, RoundingMode.CEILING));
	    			if(listDetailTransferStockMPService.get(k).getQuantiteOffre()!=null)
	    			{
	    				montantOffreService=montantOffreService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteOffre()));
		    			quantiteTotalOffreService=quantiteTotalOffreService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPService.get(k).getQuantiteOffre().setScale(0, RoundingMode.CEILING));
	    			}
	    			
    				
				}else
				{
					montantService=montantService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantite()));
	    			quantiteTotalService=quantiteTotalService.add(listDetailTransferStockMPService.get(k).getQuantite());
	    			
	    			montantDechetService=montantDechetService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteDechet()));
	    			quantiteTotalDechetService=quantiteTotalDechetService.add(listDetailTransferStockMPService.get(k).getQuantiteDechet());
	    			if(listDetailTransferStockMPService.get(k).getQuantiteOffre()!=null)
	    			{
	    				montantOffreService=montantOffreService.add(listDetailTransferStockMPService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPService.get(k).getQuantiteOffre()));
		    			quantiteTotalOffreService=quantiteTotalOffreService.add(listDetailTransferStockMPService.get(k).getQuantiteOffre());	
	    				
	    			}
	    			
	    			
	    			
				}
    			
    		
    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
    			
    		}
    		
    		
    	}
    	
    	
    		if(!montantService.equals(BigDecimal.ZERO) && !quantiteTotalService.equals(BigDecimal.ZERO))
    		{
    			/*	    			
    			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
    			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
    			*/
    			
    			for(int i=0;i<listEtatStockMP.size();i++)
    	    	{
    				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPServiceGroupebyMP.get(j).getMatierePremier()))
	    			{
    					
    					
	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
	    				
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
    					{
	    					etatstockmp.setQuantiteService (etatstockmp.getQuantiteService().setScale(0, RoundingMode.CEILING).add(quantiteTotalService.setScale(0, RoundingMode.CEILING)));
		    				etatstockmp.setPrixService(montantService.divide(quantiteTotalService.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantService(etatstockmp.getQuantiteService().multiply(etatstockmp.getPrixService()));
		    				
		    				
		    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING).add(quantiteTotalDechetService.setScale(0, RoundingMode.CEILING)));
		    				if((etatstockmp.getQuantiteDechetService().add(quantiteTotalDechetService)).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		    				{
			    				etatstockmp.setPrixDechetService(new BigDecimal(0));

		    				}else
		    				{
			    				etatstockmp.setPrixDechetService(montantDechetService.divide(quantiteTotalDechetService.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));

		    				}
		    				etatstockmp.setMontantDechetService (etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
		    				
		    				listEtatStockMP.set(i, etatstockmp);
    					}else
    					{
    						etatstockmp.setQuantiteService(etatstockmp.getQuantiteService().add(quantiteTotalService));
		    				etatstockmp.setPrixService (montantService.divide(quantiteTotalService,6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantService(etatstockmp.getQuantiteService().multiply(etatstockmp.getPrixService()));
		    				
		    				
		    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().add(quantiteTotalDechetService));
		    				if((etatstockmp.getQuantiteDechetService().add(quantiteTotalDechetService)).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		    				{
			    				etatstockmp.setPrixDechetService(new BigDecimal(0));

		    				}else
		    				{
			    				etatstockmp.setPrixDechetService(montantDechetService.divide(quantiteTotalDechetService,6,RoundingMode.HALF_UP));

		    				}	
		    				etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
		    				
		    				listEtatStockMP.set(i, etatstockmp);
    					}
	    				
	    				
	    			}
    	    	}
    				
    		}
    		// Ajouter la Quantite , prix et monatant Offre service
    		if(!quantiteTotalOffreService.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
    		{
    			/*	    			
    			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
    			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
    			*/
    			
    			for(int i=0;i<listEtatStockMP.size();i++)
    	    	{
    				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPServiceGroupebyMP.get(j).getMatierePremier()))
	    			{
    					
    					
	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
	    				
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
    					{
	    					etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING).add(quantiteTotalOffreService.setScale(0, RoundingMode.CEILING)));
		    				etatstockmp.setPrixOffreService(montantOffreService.divide(quantiteTotalOffreService.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantOffreService (etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
		    				
		    				
		    				listEtatStockMP.set(i, etatstockmp);
    					}else
    					{
    						etatstockmp.setQuantiteOffreService (etatstockmp.getQuantiteOffreService().add(quantiteTotalOffreService));
		    				etatstockmp.setPrixOffreService (montantOffreService.divide(quantiteTotalOffreService,6,RoundingMode.HALF_UP));
		    				etatstockmp.setMontantOffreService (etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
		    				
		    				listEtatStockMP.set(i, etatstockmp);
    					}
	    				
	    				
	    			}
    	    	}
    				
    		}
    		
    		
    	}
    	
    	
// calculer le prix moyen et quantite Vente
		
		
		
    	for(int j=0;j<listDetailTransferStockMPVenteGroupebyMP.size();j++)
    	{
    		montantVente=new BigDecimal(0);
    		quantiteTotalVente=new BigDecimal(0);
    		
    	for(int k=0;k<listDetailTransferStockMPVente.size();k++)
    	{
    		
    		if(listDetailTransferStockMPVenteGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPVente.get(k).getMatierePremier()))
    		{
    			if(listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPVente.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
    				montantVente=montantVente.add(listDetailTransferStockMPVente.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPVente.get(k).getQuantite().setScale(0, RoundingMode.CEILING)));
	    			quantiteTotalVente=quantiteTotalVente.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPVente.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
				}else
				{
					montantVente=montantVente.add(listDetailTransferStockMPVente.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPVente.get(k).getQuantite()));
	    			quantiteTotalVente=quantiteTotalVente.add(listDetailTransferStockMPVente.get(k).getQuantite());
				}
    	
    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
    			
    		}
    		
    	}
    		if(!montantVente.equals(BigDecimal.ZERO) && !quantiteTotalVente.equals(BigDecimal.ZERO))
    		{
    			/*	    			
    			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
    			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
    			*/
    			
    			for(int i=0;i<listEtatStockMP.size();i++)
    	    	{
    				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPVenteGroupebyMP.get(j).getMatierePremier()))
	    			{
	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
    					{
		    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantVente)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalVente.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

	    					etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalVente.setScale(0, RoundingMode.CEILING)) );
		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
		    				listEtatStockMP.set(i, etatstockmp);
	    					
    					}else
    					{
		    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantVente)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalVente),6,RoundingMode.HALF_UP));

    						etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalVente));
		    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
		    				listEtatStockMP.set(i, etatstockmp);	
    					}
	    				
	    				
	    			}
    	    	}
    			
    			
    			
    		}
    		
    	}
    	
    	
    	
    	
    	
    	
    	// calculer calculer le prix moyen et quantite TransferMPPF
    	
    	
	for(int j=0;j<listDetailTransferStockMPTransferMPPFGroupebyMP.size();j++)
    	{
    		montantTransferMPPF=new BigDecimal(0);
    		quantiteTotalTransferMPPF=new BigDecimal(0);
    	
    		
    		
    	for(int k=0;k<listDetailTransferStockMPTransferMPPF.size();k++)
    	{
    		
		if(listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).getMatierePremier().equals(listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier()))
    		{
    			if(listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPTransferMPPF.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
    				montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPF.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPF.get(k).getQuantite().setScale(0, RoundingMode.CEILING)));
	    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPF.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
    	
	    			
				}else
				{
					montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPF.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPF.get(k).getQuantite()));
	    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.add(listDetailTransferStockMPTransferMPPF.get(k).getQuantite());
	    			
	    
				}
    	
    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
    			
    		}
    		
    		
    	}
	
    	
    	
    		if(!montantTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !quantiteTotalTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
    		{
    				    			
    			/*listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).setQuantite(quantiteTotalTransferMPPF);
    			listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).setPrixUnitaire(montantTransferMPPF.divide(quantiteTotalTransferMPPF,6,RoundingMode.HALF_UP));*/
    			
    			
    			for(int i=0;i<listEtatStockMP.size();i++)
    	    	{
    				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPTransferMPPFGroupebyMP.get(j).getMatierePremier()))
	    			{
	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
    					{
	    					
		    				    etatstockmp.setPrixTransfertMPPF((etatstockmp.getQuantiteTransfertMPPF().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixTransfertMPPF()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteTransfertMPPF().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));

    							etatstockmp.setQuantiteTransfertMPPF(etatstockmp.getQuantiteTransfertMPPF().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)) );
			    				etatstockmp.setMontantTransfertMPPF(etatstockmp.getQuantiteTransfertMPPF().multiply(etatstockmp.getPrixTransfertMPPF()));
			    				
		    				listEtatStockMP.set(i, etatstockmp);
	    					
    					}else
    					{
    					     etatstockmp.setPrixTransfertMPPF((etatstockmp.getQuantiteTransfertMPPF().multiply(etatstockmp.getPrixTransfertMPPF()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteTransfertMPPF().add(quantiteTotalTransferMPPF),6,RoundingMode.HALF_UP));

    						etatstockmp.setQuantiteTransfertMPPF(etatstockmp.getQuantiteTransfertMPPF().add(quantiteTotalTransferMPPF));
			    				etatstockmp.setMontantTransfertMPPF(etatstockmp.getQuantiteTransfertMPPF().multiply(etatstockmp.getPrixTransfertMPPF()));
			    							
		    				
		    				listEtatStockMP.set(i, etatstockmp);	
    					}
	    				
	    				
	    			}
    	    	}
    			
    		}
    		
    	}
    	
//    	
//
//	
//	
//    	
//    	// calculer calculer le prix moyen et quantite TransferMPPFSERVICE
//    	
//    	
//    	for(int j=0;j<listDetailTransferStockMPTransferMPPFGroupebyMPService.size();j++)
//    	{
//    		montantTransferMPPF=new BigDecimal(0);
//    		quantiteTotalTransferMPPF=new BigDecimal(0);
//    		quantiteOffreaSupprimerService=new BigDecimal(0);
//    		quantiteDechetaSupprimerService=new BigDecimal(0);
//    		
//    		
//    	for(int k=0;k<listDetailTransferStockMPTransferMPPFService.size();k++)
//    	{
//    		
//    		if(listDetailTransferStockMPTransferMPPFGroupebyMPService.get(j).getMatierePremier().equals(listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier()))
//    		{
//    			if(listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPTransferMPPFService.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
//				{
//    				montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING)));
//	    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
//	    			
//	    			
//	    			// calculer la somme de quantite dechet et offre a supprimer
//	    			if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource()!=null)
//	    			{
//	    				if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
//	    				{
//	    					quantiteDechetaSupprimerService=quantiteDechetaSupprimerService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
//	    				}else if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
//	    				{
//	    					quantiteOffreaSupprimerService=quantiteOffreaSupprimerService.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite().setScale(0, RoundingMode.CEILING))	;
//	    				}
//	    				
//	    			}
//	    			////////////////////////////////////////////////////////////////////////////////// 
//	    			
//	    			
//				}else
//				{
//					montantTransferMPPF=montantTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite()));
//	    			quantiteTotalTransferMPPF=quantiteTotalTransferMPPF.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite());
//	    			
//	    			// calculer la somme de quantite dechet et offre a supprimer
//	    			
//	    			if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource()!=null)
//	    			{
//	    				
//	    				if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_DECHET))
//	    				{
//	    					quantiteDechetaSupprimerService=quantiteDechetaSupprimerService.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite());
//	    				}else if(listDetailTransferStockMPTransferMPPFService.get(k).getStockSource().equals(Constantes.MP_STOCK_OFFRE))
//	    				{
//	    					quantiteOffreaSupprimerService=quantiteOffreaSupprimerService.add(listDetailTransferStockMPTransferMPPFService.get(k).getQuantite())	;
//	    				}
//	    				
//	    			}
//	    			
//                    //////////////////////////////////////////////////////////////////////////////////
//	    			
//				}
//    	
//    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
//    			
//    		}
//    		
//    		
//    	}
//    		if(!montantTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)) && !quantiteTotalTransferMPPF.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
//    		{
//    			/*	    			
//    			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
//    			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
//    			*/
//    			
//    			for(int i=0;i<listEtatStockMP.size();i++)
//    	    	{
//    				if(listEtatStockMP.get(i).getMp().equals(listDetailTransferStockMPTransferMPPFGroupebyMPService.get(j).getMatierePremier()))
//	    			{
//	    				EtatStockMP etatstockmp=listEtatStockMP.get(i);
//	    				if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
//    					{
//	    					
//		    				    etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)),6,RoundingMode.HALF_UP));
//
//    							etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(quantiteTotalTransferMPPF.setScale(0, RoundingMode.CEILING)) );
//			    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
//			    				// supprimer la quantite dechet et quantite offre
//			    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING).subtract(quantiteDechetaSupprimerService.setScale(0, RoundingMode.CEILING)));
//			    				if(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
//			    				{
//			    					etatstockmp.setPrixDechetService(BigDecimal.ZERO);	
//			    				}
//			    				etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
//			    				
//			    				etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING).subtract(quantiteOffreaSupprimerService.setScale(0, RoundingMode.CEILING)));
//			    				if(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING).equals(BigDecimal.ZERO.setScale(0, RoundingMode.CEILING)))
//			    				{
//			    					etatstockmp.setPrixOffreService(BigDecimal.ZERO);
//			    				}
//			    				etatstockmp.setMontantOffreService(etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
//    						
//	    					
//	    				
//		    				
//		    				listEtatStockMP.set(i, etatstockmp);
//	    					
//    					}else
//    					{
//    						
//    							
//    						
//		    				etatstockmp.setPrixSortie((etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()).add(montantTransferMPPF)).divide(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF),6,RoundingMode.HALF_UP));
//
//    						   etatstockmp.setQuantiteSortie(etatstockmp.getQuantiteSortie().add(quantiteTotalTransferMPPF));
//    					
//			    				etatstockmp.setMontantSortie(etatstockmp.getQuantiteSortie().multiply(etatstockmp.getPrixSortie()));
//			    				// supprimer la quantite dechet et quantite offre
//			    				etatstockmp.setQuantiteDechetService(etatstockmp.getQuantiteDechetService().subtract(quantiteDechetaSupprimerService));
//			    				if(etatstockmp.getQuantiteDechetService().setScale(2, RoundingMode.UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.UP)))
//			    				{
//			    					etatstockmp.setPrixDechetService(BigDecimal.ZERO);	
//			    				}
//			    				etatstockmp.setMontantDechetService(etatstockmp.getQuantiteDechetService().multiply(etatstockmp.getPrixDechetService()));
//			    				
//			    				etatstockmp.setQuantiteOffreService(etatstockmp.getQuantiteOffreService().setScale(6, RoundingMode.HALF_UP).subtract(quantiteOffreaSupprimerService.setScale(6, RoundingMode.HALF_UP)));
//			    				if(etatstockmp.getQuantiteOffreService().setScale(2, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)))
//			    				{
//			    					etatstockmp.setPrixOffreService(BigDecimal.ZERO);
//			    				}
//			    				etatstockmp.setMontantOffreService(etatstockmp.getQuantiteOffreService().multiply(etatstockmp.getPrixOffreService()));
//    					
//		    				listEtatStockMP.set(i, etatstockmp);	
//    					}
//	    				
//	    				
//	    			}
//    	    	}
//    			
//    		}
//    		
//    	}
//    	
//    	
    	// Calculer le Prix Moyen a partir de mouvement de stock 
    	
	CalculerPrixMoyenFinaleParMouvementStock( magasin, mp);
    	
    
    	// calculer le stock finale et le prix moyen finale
    	
    	
     	for(int i=0;i<listEtatStockMP.size();i++)
    	{
    		
     		quantiteTotalFinale=BigDecimal.ZERO;
	   		montantFinale=BigDecimal.ZERO;
    	    BigDecimal prixMoyen=BigDecimal.ZERO;
    			EtatStockMP etatstockmp=listEtatStockMP.get(i);
    			if(etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || etatstockmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
				{
    				etatstockmp.setQuantiteFinale((etatstockmp.getQuantiteInitial().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteAchat().setScale(0, RoundingMode.CEILING))).subtract(etatstockmp.getQuantiteSortie().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteService().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteAvoir().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteOffreService().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteOffreProduction().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteDechet().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteDechetService().setScale(0, RoundingMode.CEILING)).add(etatstockmp.getQuantiteTransfertMPPF().setScale(0, RoundingMode.CEILING)))));
	    			quantiteTotalFinale=(quantiteTotalFinale.setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteInitial().setScale(0, RoundingMode.CEILING).add(etatstockmp.getQuantiteAchat().setScale(0, RoundingMode.CEILING))));
	    			montantFinale=(montantFinale.setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantInitial().setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantAchat().setScale(6,RoundingMode.HALF_UP))));
	    			
	    			if(listMouvementStockMPAfficherMouvementTmp.size()!=0)
		    		{
		    			for(int l=0;l<listMouvementStockMPAfficherMouvementTmp.size();l++)
		    			{
		    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom()))
		    				{
		    					if(listMouvementStockMPAfficherMouvementTmp.get(l).getDateCreation().compareTo(dateChooserfin.getDate())<=0 )
		    					{
		    						prixMoyen=listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb();
			    					System.out.println(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficherMouvementTmp.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficherMouvementTmp.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficherMouvementTmp.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb() ) ;

		    					}
		    									    					
		    				}
		    				
		    			}
		    			
		    		}else
		    		{
		    			for(int l=0;l<listMouvementStockMPAfficher.size();l++)
		    			{
		    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom()))
		    				{
		    					if(listMouvementStockMPAfficher.get(l).getDateCreation().compareTo(dateChooserfin.getDate())<=0 )
		    					{
		    						prixMoyen=listMouvementStockMPAfficher.get(l).getPrixFinaldb();
			    					System.out.println(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficher.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficher.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficher.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficher.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficher.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficher.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficher.get(l).getPrixFinaldb() ) ;
			    					
		    					}
		    					
		    				}
		    			}
		    			
		    		}
	    			
	    			StockMP stockmp=stockMPDAO.findStockByMagasinMP(etatstockmp.getMp().getId(),magasin.getId());
					/*
					 * if(etatstockmp.getQuantiteAchat().setScale(0,
					 * RoundingMode.CEILING).add(etatstockmp.getQuantiteInitial().setScale(0,
					 * RoundingMode.CEILING)).compareTo(BigDecimal.ZERO)>0) {
					 * prixMoyen=(etatstockmp.getMontantInitial().add(etatstockmp.getMontantAchat())
					 * ).divide(etatstockmp.getQuantiteAchat().setScale(0,
					 * RoundingMode.CEILING).add(etatstockmp.getQuantiteInitial().setScale(0,
					 * RoundingMode.CEILING)), 6, RoundingMode.HALF_UP);
					 * 
					 * }
					 */
	    			//prixMoyen=stockmp.getPrixUnitaire();
	    			etatstockmp.setPrixFinale(prixMoyen);
		    		etatstockmp.setMontantFinale(((etatstockmp.getQuantiteFinale().setScale(0, RoundingMode.CEILING))).multiply(etatstockmp.getPrixFinale().setScale(6,RoundingMode.HALF_UP)));
	    			
	    			if(etatstockmp.getPrixSortie().setScale(6, RoundingMode.HALF_UP)!=BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))
	    			{
	    				etatstockmp.setMarge((etatstockmp.getPrixSortie().subtract(etatstockmp.getPrixInitial())).divide(etatstockmp.getPrixSortie(), RoundingMode.HALF_UP));
	    			}else
	    			{
	    				etatstockmp.setMarge(BigDecimal.ZERO);
	    			}
				}else
				{
					etatstockmp.setQuantiteFinale((etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat())).subtract(etatstockmp.getQuantiteSortie().add(etatstockmp.getQuantiteService()).add(etatstockmp.getQuantiteAvoir().add(etatstockmp.getQuantiteOffreService()).add(etatstockmp.getQuantiteOffreProduction()).add(etatstockmp.getQuantiteDechet()).add(etatstockmp.getQuantiteDechetService()).add(etatstockmp.getQuantiteTransfertMPPF()))));
	    			quantiteTotalFinale=(quantiteTotalFinale.add(etatstockmp.getQuantiteInitial().add(etatstockmp.getQuantiteAchat())));
	    			montantFinale=(montantFinale.setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantInitial().setScale(6,RoundingMode.HALF_UP).add(etatstockmp.getMontantAchat().setScale(6,RoundingMode.HALF_UP))));
	    			
	    			if(listMouvementStockMPAfficherMouvementTmp.size()!=0)
		    		{
		    			for(int l=0;l<listMouvementStockMPAfficherMouvementTmp.size();l++)
		    			{
		    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom()))
		    				{
		    					if(listMouvementStockMPAfficherMouvementTmp.get(l).getDateCreation().compareTo(dateChooserfin.getDate())<=0 )
		    					{
		    						prixMoyen=listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb();
			    					System.out.println(listMouvementStockMPAfficherMouvementTmp.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficherMouvementTmp.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficherMouvementTmp.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficherMouvementTmp.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb() ) ;
			    					
		    					}
		    					
		    				}
		    				
		    			}
		    			
		    		}else
		    		{
		    			for(int l=0;l<listMouvementStockMPAfficher.size();l++)
		    			{
		    				if(etatstockmp.getMp().getNom().equals(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom()))
		    				{
		    					if(listMouvementStockMPAfficher.get(l).getDateCreation().compareTo(dateChooserfin.getDate())<=0 )
		    					{
		    						prixMoyen=listMouvementStockMPAfficher.get(l).getPrixFinaldb();
			    					System.out.println(listMouvementStockMPAfficher.get(l).getMatierePremier().getNom() +" : Initial "+listMouvementStockMPAfficher.get(l).getInitial() + " Prix Initial: "+listMouvementStockMPAfficher.get(l).getPrixInitial()+ " Achat : "+listMouvementStockMPAfficher.get(l).getAchat() + " prix Achat : "+listMouvementStockMPAfficher.get(l).getPrixAchat()+ " Production : "+listMouvementStockMPAfficher.get(l).getProduction() + " Quantite Finale : "+listMouvementStockMPAfficher.get(l).getStockFinaldb() + "  prix Moyen : "+listMouvementStockMPAfficher.get(l).getPrixFinaldb() ) ;
			    				
		    					}
		    					
		    				}
		    			}
		    			
		    		}
	    			
	    			StockMP stockmp=stockMPDAO.findStockByMagasinMP(etatstockmp.getMp().getId(),magasin.getId());
	    			
					/*
					 * if(etatstockmp.getQuantiteAchat().add(etatstockmp.getQuantiteInitial()).
					 * compareTo(BigDecimal.ZERO)>0) {
					 * prixMoyen=(etatstockmp.getMontantInitial().add(etatstockmp.getMontantAchat())
					 * ).divide(etatstockmp.getQuantiteAchat().add(etatstockmp.getQuantiteInitial())
					 * , 6, RoundingMode.HALF_UP);
					 * 
					 * }
					 */
	    			
	    			   // prixMoyen=stockmp.getPrixUnitaire();
	    				etatstockmp.setPrixFinale(prixMoyen);
		    			etatstockmp.setMontantFinale((etatstockmp.getQuantiteFinale().setScale(6, RoundingMode.HALF_UP)).multiply(etatstockmp.getPrixFinale().setScale(6,RoundingMode.HALF_UP)));
	    			
	    			if(!etatstockmp.getPrixSortie().setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,  RoundingMode.DOWN)))
	    			{
	    				etatstockmp.setMarge((etatstockmp.getPrixSortie().subtract(etatstockmp.getPrixInitial())).divide(etatstockmp.getPrixSortie(), RoundingMode.HALF_UP));
	    			}else
	    			{
	    				etatstockmp.setMarge(BigDecimal.ZERO);
	    			}
				}
    			
    			listEtatStockMP.set(i, etatstockmp);
    			
    	}
    	
    	
    	
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
	
	
		
		
		
	}
	
	
	
	
	  public void CalculerPrixMoyenFinaleParMouvementStock(Magasin magasin,MatierePremier mp)
{
		   
		  

		  
		  

	   // detailTransferStockMPDAO.ViderSession();
		listDetailTransferStockMP.clear();
		listDetailTransferStockMPGroupebyMP.clear();
		listDetailTransferStockMPBytypetransfer.clear();
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		listMouvementStockMP.clear();
		boolean trouve=false;
		
		
		if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null)
		{
			JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP !!!");
				return;
		}else
		{
			if(magasin!=null)
	    		{
	    			if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    			String d2=sdf.format(dateChooserfin.getDate());
		    			
		    		if(!d1.equals(d2))
		    		{
		    			if(dateChooserfin.getDate().compareTo(dateChooserdebut.getDate())<0)
		    			{
		    				JOptionPane.showMessageDialog(null, "date de fin doit etre supérieur au date debut SVP !!!");
		    				return;
		    			}
		    			
		    		}
		    		
		    		if(mp!=null)
		    		{
		    			titre="Mouvement de Stock de Emballage "+mp.getNom() +" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
		    		}else
		    		{
		    			titre="Mouvement de Stock Emballage de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
		    		}
		    		
		    		}
		    		
		    		if(dateChooserdebut.getDate()==null )
		    		{
		    			dateChooserfin.setCalendar(null);
		    			titre="Mouvement de Stock Emballage  magasin : "+magasin.getLibelle();
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    			titre="Mouvement de Stock Emballage de "+mp.getNom() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    			titre="Mouvement de Stock Emballage de magasin : "+magasin.getLibelle()+ "entre "+d1 +" et "+d1;
		    		}
		    		
		    		
		    		listDetailTransferStockMP=detailTransferStockMPDAO.findAllTransferStockMPOrderByDateTransfer(magasin);
		    		listDetailTransferStockMPGroupebyMP=detailTransferStockMPDAO.findAllTransferStockMPGroupeByDateTransferByMP(magasin);
		    		listDetailTransferStockMPBytypetransfer=detailTransferStockMPDAO.findBytypetransfer(ETAT_TRANSFER_STOCK_INITIAL,magasin);
		    		String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.ETAT_TRANSFER_STOCK_CHARGE,ETAT_TRANSFER_STOCK_CHARGE_SUPP,ETAT_TRANSFER_STOCK_VENTE,ETAT_TRANSFER_STOCK_AVOIR,ETAT_TRANSFER_STOCK_SERVICE,ETAT_TRANSFER_STOCK_SORTIE_PF,ETAT_TRANSFER_STOCK_SORTIE_MP_PF};
		    		BigDecimal achat=BigDecimal.ZERO;
		    		BigDecimal prixAchat=BigDecimal.ZERO;
		    		BigDecimal initial=BigDecimal.ZERO;
		    		BigDecimal prixInitial=BigDecimal.ZERO;
		    		BigDecimal production=BigDecimal.ZERO;
		    		BigDecimal avoir=BigDecimal.ZERO;
		    		
		    		BigDecimal stockfinal=BigDecimal.ZERO;
		    		BigDecimal vente=BigDecimal.ZERO;
		    		BigDecimal service=BigDecimal.ZERO;
		    		BigDecimal dechet=BigDecimal.ZERO;
		    		BigDecimal dechetService=BigDecimal.ZERO;
		    		BigDecimal offreService=BigDecimal.ZERO;
		    		BigDecimal offreProduction=BigDecimal.ZERO;
		    		BigDecimal transfert=BigDecimal.ZERO;
		    		BigDecimal prixmoyenne=BigDecimal.ZERO;
		    		BigDecimal quantitefinale=BigDecimal.ZERO;
		    		for(int i=0;i<listDetailTransferStockMPGroupebyMP.size();i++)
		    		{
		    			achat=new BigDecimal(0);
		    			prixAchat=new BigDecimal(0);
		    			initial=new BigDecimal(0);
		    			production=new BigDecimal(0);
		    			dechet=new BigDecimal(0);
		    			dechetService=new BigDecimal(0);
		    			service=new BigDecimal(0);
		    			vente=new BigDecimal(0);
		    			avoir=BigDecimal.ZERO;
		    			stockfinal=new BigDecimal(0);
		    			offreService=new BigDecimal(0);
		    			offreProduction=new BigDecimal(0);
		    			transfert=new BigDecimal(0);
		    			
		    			if(i!=0)
		    			{
		    				if(!listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().equals(listDetailTransferStockMPGroupebyMP.get(i-1).getMatierePremier()))
		    				{
		    					prixmoyenne=new BigDecimal(0);
				    			quantitefinale=new BigDecimal(0);
		    					
		    				}
		    				
		    			}else
		    			{
		    				prixmoyenne=new BigDecimal(0);
			    			quantitefinale=new BigDecimal(0);
		    			}
		    			
		    			
		    				
		    			for(int j=0;j<listDetailTransferStockMP.size();j++)
		    			{
		    				for(int k=0;k<typetransfer.length;k++)
			    			{
		    				
		    			if(listDetailTransferStockMPGroupebyMP.get(i).getTransferStockMP().getDateTransfer().equals(listDetailTransferStockMP.get(j).getTransferStockMP().getDateTransfer()) 
		    					&& listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().equals(listDetailTransferStockMP.get(j).getMatierePremier()) && listDetailTransferStockMP.get(j).getTransferStockMP().getStatut().equals(typetransfer[k]))	
		    			{
		    				
		    				achat=new BigDecimal(0);
			    			prixAchat=new BigDecimal(0);
			    			initial=new BigDecimal(0);
			    			production=new BigDecimal(0);
			    			dechet=new BigDecimal(0);
			    			dechetService=new BigDecimal(0);
			    			service=new BigDecimal(0);
			    			vente=new BigDecimal(0);
			    			avoir=BigDecimal.ZERO;
			    			stockfinal=new BigDecimal(0);
			    			offreService=new BigDecimal(0);
			    			offreProduction=new BigDecimal(0);
			    			transfert=new BigDecimal(0);
		    				if(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getId()==94)
		    				{
		    					System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom());
		    				}
		    				
		    			if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ACHAT))
		    			{
		    				
                   if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                       {
                	   if(!achat.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING)).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
                	   {
                    	   prixAchat=(prixAchat.multiply(achat).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING).multiply(listDetailTransferStockMP.get(j).getPrixUnitaire()))).divide(achat.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING)), RoundingMode.HALF_UP);

                	   }else
                	   {
                		   prixAchat=BigDecimal.ZERO;
                	   }
                	   achat=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
                	   
                        }else
                        {
                        	if(!listDetailTransferStockMP.get(j).getQuantite().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
                        	{
	                        	   prixAchat=(prixAchat.multiply(achat).add(listDetailTransferStockMP.get(j).getQuantite().multiply(listDetailTransferStockMP.get(j).getPrixUnitaire()))).divide(achat.add(listDetailTransferStockMP.get(j).getQuantite()), RoundingMode.HALF_UP);

                        	}else
                        	{
	                        	   prixAchat=BigDecimal.ZERO;

                        	}
                        	

                        	achat=listDetailTransferStockMP.get(j).getQuantite();
                        }
		    				
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_INITIAL))
		    			{
		    				   if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					    prixInitial=listDetailTransferStockMP.get(j).getPrixUnitaire();
		    						initial=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
                           }else
                           {
                        	    prixInitial=listDetailTransferStockMP.get(j).getPrixUnitaire();
                        		initial=listDetailTransferStockMP.get(j).getQuantite(); 
                           }
		    			
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE) || typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE_SUPP))
		    			{
		    				 if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					 production=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
		    					 
		    					 if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE))
			    				 {
			    					
			    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
			    					{
			    						
			    						dechet=listDetailTransferStockMP.get(j).getQuantiteDechet().setScale(0, RoundingMode.CEILING);
			    					}
			    					
			    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
			    					{
			    						
			    						offreProduction=listDetailTransferStockMP.get(j).getQuantiteOffre().setScale(0, RoundingMode.CEILING);
			    					}
			    					
			    				 }
		    					 
		    					 
                           }else
                           {
                        	  // System.out.println("listDetailTransferStockMP.get(j).getQuantite() :"+listDetailTransferStockMP.get(j).getQuantite());
                        	   production=listDetailTransferStockMP.get(j).getQuantite(); 
                        	   if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_CHARGE))
				    				 {
				    					
				    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
				    					{
				    						
				    						dechet=listDetailTransferStockMP.get(j).getQuantiteDechet();
				    					}
				    					
				    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
				    					{
				    						
				    						offreProduction=listDetailTransferStockMP.get(j).getQuantiteOffre();
				    					}
				    					 
				    				 }
                           }
		    				 
		    				
		    				 
		    			}
		    			
		    			else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_VENTE))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					vente=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
                           }else
                           {
                        	   vente=listDetailTransferStockMP.get(j).getQuantite();
                           }
		    					
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					avoir=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);
                           }else
                           {
                        	   avoir=listDetailTransferStockMP.get(j).getQuantite();
                           }
		    					
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SERVICE))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					service=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);	
		    					
			    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
			    					{
			    						
			    						dechetService=listDetailTransferStockMP.get(j).getQuantiteDechet().setScale(0, RoundingMode.CEILING);
			    					}
			    					
			    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
			    					{
			    						
			    						offreService=listDetailTransferStockMP.get(j).getQuantiteOffre().setScale(0, RoundingMode.CEILING);
			    					}
			    					 
			    				
                           }else
                           {
                        	   service=listDetailTransferStockMP.get(j).getQuantite();	
                        	   
				    					if(listDetailTransferStockMP.get(j).getQuantiteDechet()!=null)
				    					{
				    						
				    						dechetService=listDetailTransferStockMP.get(j).getQuantiteDechet();
				    					}
				    					
				    					if(listDetailTransferStockMP.get(j).getQuantiteOffre()!=null)
				    					{
				    						
				    						offreService=listDetailTransferStockMP.get(j).getQuantiteOffre();
				    					}
				    					 
                           }
		    				
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SORTIE_PF))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					transfert=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);	
		    					 
			    				
                           }else
                           {
                        	   transfert=listDetailTransferStockMP.get(j).getQuantite();	
                        	  	 
                           }
		    				
		    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_SORTIE_MP_PF))
		    			{
		    				if(listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
                           {
		    					transfert=listDetailTransferStockMP.get(j).getQuantite().setScale(0, RoundingMode.CEILING);	
		    					 
			    				
                           }else
                           {
                        	   transfert=listDetailTransferStockMP.get(j).getQuantite();	
                        	  	 
                           }
		    				
		    			}
		    			
		    			
		    			if(!quantitefinale.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
		    			{
			    			

		    				if(!quantitefinale.add(achat).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
			    			{
				    			System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : Prix Moyenne :"+ achat +" * "+prixAchat+ " + " +quantitefinale +" * "+prixmoyenne +" / "+ "("+quantitefinale +achat+" )" );

		    					prixmoyenne=(achat.multiply(prixAchat).add(quantitefinale.multiply(prixmoyenne))).divide(quantitefinale.add(achat), RoundingMode.HALF_UP);

			    			}else
			    			{
			    				prixmoyenne=BigDecimal.ZERO;

			    			}
		    				
		    				
		    				System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : quantitefinale :"+quantitefinale +" :achat "+achat +" vente: "+vente +" production :"+production + " avoir:" +avoir +" service: "+service +" transfert:"+transfert );

		    				quantitefinale=(quantitefinale.add(achat)).subtract(vente.add(production.add(avoir.add(service).add(transfert).add(offreService).add(dechetService).add(dechet).add(offreProduction))));
		    				
		    			}else
		    			{
		    				
		    				
		    				if(!initial.add(achat).setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
			    			{
				    			System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : Prix Moyenne :"+ achat +" * "+prixAchat+ " + " +initial +" * "+prixInitial +" / "+ "("+initial +" + "+achat+")" );

		    					prixmoyenne=(achat.multiply(prixAchat).add(initial.multiply(prixInitial))).divide(initial.add(achat), RoundingMode.HALF_UP);

			    			}else
			    			{
			    				prixmoyenne=BigDecimal.ZERO;

			    			}
		    				
		    				
		    				quantitefinale=(initial.add(achat)).subtract(vente.add(production.add(avoir.add(service).add(transfert).add(offreService).add(dechetService).add(dechet).add(offreProduction))));
			    			System.out.println(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier().getNom() +" : quantitefinale :"+quantitefinale +" :achat "+achat +" vente: "+vente +" production :"+production + " avoir:" +avoir +" service: "+service +" transfert:"+transfert );

		    				
		    			}
		    			

		    				
		    			}
		    			
		    			
		    			
		    			
		    			}
		    			}
		    			
		    			
		    			if(listMouvementStockMP.size()!=0)
		    			{
		    				for(int d=0;d<listMouvementStockMP.size();d++)
		    				{
		    					
		    					if(listMouvementStockMP.get(d).getMatierePremier().equals(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier()))
		    					{
		    						initial=listMouvementStockMP.get(d).getStockFinaldb();
		    						prixInitial=listMouvementStockMP.get(d).getPrixFinaldb();
		    						trouve=true;
		    						
		    					}
		    				}
		    				
		    				
		    			}
		    			if(trouve==false)
		    			{
		    				for(int l=0;l<listDetailTransferStockMPBytypetransfer.size();l++)
		    				{
		    	if(listDetailTransferStockMPBytypetransfer.get(l).getMatierePremier().equals(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier()))
		    	{
		    		initial=listDetailTransferStockMPBytypetransfer.get(l).getQuantite();
		    		
		    	}
		    	
		    				}
		    				
		    			}
		    			
		    			//stockfinal=(initial.add(achat)).subtract(vente.add(production).add(avoir).add(service).add(dechet).add(dechetService));
		    			DetailMouvementStock mouvementstockMP=new DetailMouvementStock();
		    			mouvementstockMP.setDateCreation(listDetailTransferStockMPGroupebyMP.get(i).getTransferStockMP().getDateTransfer());
		    			mouvementstockMP.setProduction(production);
		    			mouvementstockMP.setDechet(dechet);
		    			mouvementstockMP.setOffreProduction(offreProduction);
		    			mouvementstockMP.setVente(vente);
		    			mouvementstockMP.setInitial(initial);
		    			mouvementstockMP.setPrixInitial(prixInitial);
		    			mouvementstockMP.setAchat(achat);
		    			mouvementstockMP.setPrixAchat(prixAchat);
		    			mouvementstockMP.setAvoir(avoir);
		    			mouvementstockMP.setService(service);
		    			mouvementstockMP.setDechetService(dechetService);
		    			mouvementstockMP.setOffreService(offreService);
		    			mouvementstockMP.setTransfert(transfert);
		    			mouvementstockMP.setMatierePremier(listDetailTransferStockMPGroupebyMP.get(i).getMatierePremier());
					/*
					 * if(!mouvementstockMP.getInitial().add(mouvementstockMP.getAchat()).setScale(
					 * 6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,
					 * RoundingMode.HALF_UP))) {
					 * mouvementstockMP.setPrixFinaldb((mouvementstockMP.getAchat().multiply(
					 * mouvementstockMP.getPrixAchat()).add(mouvementstockMP.getInitial().multiply(
					 * mouvementstockMP.getPrixInitial()))).divide(mouvementstockMP.getInitial().add
					 * (mouvementstockMP.getAchat()), RoundingMode.HALF_UP));
					 * 
					 * }else { mouvementstockMP.setPrixFinaldb(BigDecimal.ZERO);
					 * 
					 * }
					 */
		    			mouvementstockMP.setPrixFinaldb(prixmoyenne);
		    			mouvementstockMP.setStockFinaldb((mouvementstockMP.getInitial().add(mouvementstockMP.getAchat())).subtract(mouvementstockMP.getVente().add(mouvementstockMP.getProduction().add(mouvementstockMP.getAvoir().add(mouvementstockMP.getService()).add(mouvementstockMP.getTransfert())))));
		    			listMouvementStockMP.add(mouvementstockMP);
		    			
		    		}
		    		
		    		// detailtransfer entre deux date et par article
		    		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && mp!=null)
		    		{
		    			listMouvementStockMPAfficher.clear();
		    			listMouvementStockMPAfficherMouvementTmp.clear();
		    			
		    		
		    		for(int i=0;i<listMouvementStockMP.size();i++)	
		    		{
		    			String ddebut=sdf.format(dateChooserdebut.getDate());
		    			String ddebutTmp=sdf.format(listMouvementStockMP.get(i).getDateCreation());
		    			
		    			if(listMouvementStockMP.get(i).getDateCreation().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut)   )
		    				
		    			{
		    			if(listMouvementStockMP.get(i).getMatierePremier().getNom().equals(mp.getNom()))
		    			{
		    				listMouvementStockMPAfficher.add(listMouvementStockMP.get(i));	
		    			}
		    				
		    			}
		    			
		    		}
		    			
		    		for(int j=0;j<listMouvementStockMPAfficher.size();j++)	
		    		{
		    			
		    			String dfin=sdf.format(dateChooserfin.getDate());
		    			String dfinTmp=sdf.format(listMouvementStockMPAfficher.get(j).getDateCreation());
		    			if(listMouvementStockMPAfficher.get(j).getDateCreation().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin) )
		    			{
		    			if(listMouvementStockMPAfficher.get(j).getMatierePremier().getNom().equals(mp.getNom()))
		    			{
		    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMPAfficher.get(j));
		    			}
		    				
		    			}
		    			
		    		}
		    		
		    			
		    		// detailtransfer entre deux date (date fin null) et par article 
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp!=null)
		    		{
		    			listMouvementStockMPAfficherMouvementTmp.clear();
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    		
		    			
		    			for(int i=0;i<listMouvementStockMP.size();i++)	
			    		{
		    				String ddbut=sdf.format(listMouvementStockMP.get(i).getDateCreation());
			    			if(ddbut.equals(d1) && listMouvementStockMP.get(i).getMatierePremier().equals(mp) )
			    			{
			    			
			    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    		
		    			
		    			// detailtransfer entre deux date (date fin null)  
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && mp==null)
		    		{
		    			
		    			listMouvementStockMPAfficherMouvementTmp.clear();
	                      
	                      String d1=sdf.format(dateChooserdebut.getDate());
	  	    			
		    			
		    			for(int i=0;i<listMouvementStockMP.size();i++)	
			    		{
		    				String ddbut=sdf.format(listMouvementStockMP.get(i).getDateCreation());
			    			if(ddbut.equals(d1) )
			    			{
			    			
			    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    			
		    			
		    			
		    			// detailtransfer par article
		    		}else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null && mp!=null)
		    		{
		    			listMouvementStockMPAfficherMouvementTmp.clear();
		             
		               
		               
		    			for(int i=0;i<listMouvementStockMP.size();i++)	
			    		{
			    			if(listMouvementStockMP.get(i).getMatierePremier().getNom().equals(mp.getNom()) )
			    			{
			    			
			    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
			    			
			    				
			    			}
			    			
			    		}
		    			
		    			
		    			// detailtransfer entre deux date  
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && mp==null)
		    		{
		    			listMouvementStockMPAfficher.clear();
		    			listMouvementStockMPAfficherMouvementTmp.clear();
		    		
		    		for(int i=0;i<listMouvementStockMP.size();i++)	
		    		{
		    			String ddebut=sdf.format(dateChooserdebut.getDate());
		    			String ddebutTmp=sdf.format(listMouvementStockMP.get(i).getDateCreation());
		    			if(listMouvementStockMP.get(i).getDateCreation().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut))
		    			{
		    			
		    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMP.get(i));
		    			
		    				
		    			}
		    			
		    		}
		    		for(int j=0;j<listMouvementStockMPAfficher.size();j++)	
		    		{
		    			
		    			String dfin=sdf.format(dateChooserfin.getDate());
		    			String dfinTmp=sdf.format(listMouvementStockMPAfficher.get(j).getDateCreation());
		    			
		    			if(listMouvementStockMPAfficher.get(j).getDateCreation().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin)  )
		    			{
		    			
		    				listMouvementStockMPAfficherMouvementTmp.add(listMouvementStockMPAfficher.get(j));
		    			
		    				
		    			}
		    			
		    		}
		    		
		    	/*	if(listMouvementStockMPAfficherTmp.size()!=0)
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficherTmp);
		    			
		    		}else
		    		{
		    			afficher_tableDetailMouvementStock(listMouvementStockMPAfficher);
		    		}*/
		    		
		    		}
		    			
	    		}else
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
		}

	
		
		   
		   
		   
		   
		   
		   
	
}
	}


