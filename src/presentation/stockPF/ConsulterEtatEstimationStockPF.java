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
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;

import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
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
import dao.daoManager.DetailTransferMPDAO;

import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
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
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockMP;
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


public class ConsulterEtatEstimationStockPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEtatStock;
	private DefaultTableModel	 modeleMouvementStock;

	private JXTable  tableEtatStock = new JXTable();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	
	//**********************************************************************listes Mouvemement Stock***************************************************
	
	private List<DetailTransferProduitFini> listDetailTransferStockPF =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFGroupebyArticle =new ArrayList<DetailTransferProduitFini>();
	private List<DetailTransferProduitFini> listDetailTransferStockPFBytypetransfer =new ArrayList<DetailTransferProduitFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPF =new ArrayList<MouvementStockProduitsFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPFAfficher =new ArrayList<MouvementStockProduitsFini>();
	private List<MouvementStockProduitsFini> listMouvementStockPFAfficherTmp =new ArrayList<MouvementStockProduitsFini>();
	
	
	
	//*************************************************************************************************************************************************
	
	private List<EtatStockPF> listEtatStockPF =new ArrayList<EtatStockPF>();
	private List<EtatStockPF> listEtatStockPFImprimer =new ArrayList<EtatStockPF>();
	private List<FamilleArticlePF> listFamilleArticlesF =new ArrayList<FamilleArticlePF>();
	private Map< String, FamilleArticlePF> mapFamilleArticles= new HashMap<>();
	private List<SousFamilleArticlePF> listSousFamilleArticlesF =new ArrayList<SousFamilleArticlePF>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	
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
	  JComboBox combomagasin = new JComboBox();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
private DetailTransferProduitFiniDAO detailTransferStockPFDAO;


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
	private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
	String titre="";
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterEtatEstimationStockPF() {
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
         listArticle=ArticleDAO.findAll();
         	listFamilleArticlesF=familleArticlesDAO.findAll();
         	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
       
         	
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableEtatStock.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Sous Famille", "SI", "Achat","SF", "Achat Revendu", "CA","Marge" ,"Marge Pourcentage"	      	}
       ));
       tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
       tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
       tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
       tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
       tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(118);
       tableEtatStock.getColumnModel().getColumn(6).setPreferredWidth(132);
       tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(132);
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
		      
		     	
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	/*			BigDecimal totalmontantinitial=BigDecimal.ZERO;
				BigDecimal totalmontantachat=BigDecimal.ZERO;
				BigDecimal totalmontantproduction=BigDecimal.ZERO;
				BigDecimal totalmontantvente=BigDecimal.ZERO;
				BigDecimal totalmontantavoir=BigDecimal.ZERO;
				BigDecimal totalmontantfinale=BigDecimal.ZERO;*/
				
				 if(listEtatStockPF.size()!=0)
				 {
					 
					/* int i=0;
					 while(i<listEtatStockPF.size())
					 {
						 EtatStockPF etatstockpf=listEtatStockPFImprimer.get(i);
						 totalmontantinitial=totalmontantinitial.add(etatstockpf.getMontantInitial()) ;
						 totalmontantachat=totalmontantachat.add(etatstockpf.getMontantAchat());
						 totalmontantproduction=totalmontantproduction.add(etatstockpf.getMontantProduction());
						 totalmontantvente=totalmontantvente.add(etatstockpf.getMontantSortie());	
						 totalmontantavoir=totalmontantavoir.add(etatstockpf.getMontantAvoir());
						 totalmontantfinale=totalmontantfinale.add(etatstockpf.getMontantFinale());
						 
						 
						 
						 i++;
					 }
					 	parameters.put("totalmontantinitial",totalmontantinitial );
						parameters.put("totalmontantachat",totalmontantachat );	
						parameters.put("totalmontantproduction",totalmontantproduction);	
						parameters.put("totalmontantvente",totalmontantvente);	
						parameters.put("totalmontantavoir",totalmontantavoir);	
						parameters.put("totalmontantfinale",totalmontantfinale);*/							
					 Map parameters = new HashMap();
						
						parameters.put("titre", titre);
						JasperUtils.imprimerEtatEstimationStockPF(listEtatStockPF,parameters);
						
				 } else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun Etat Stock  ", "Erreur", JOptionPane.ERROR_MESSAGE); 
					 return;
				 }
					
				
				
			}});
		
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(698, 793, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter Etat Estimation de Stock PF :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 919, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		detailTransferStockPFDAO.ViderSession();
	    		
	    		listEtatStockPFImprimer.clear();
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
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoir =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoir_R =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoir_RGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
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
	    		
	    		BigDecimal quantiteTotalproduction=new BigDecimal(0);
	    		BigDecimal montantproduction=new BigDecimal(0);
	    		
	    		BigDecimal quantiteTotalFinale=new BigDecimal(0);
	    		BigDecimal montantFinale=new BigDecimal(0);
	    		boolean trouve=false;
	    		FamilleArticlePF familleArticle=mapFamilleArticles.get(comboFamille.getSelectedItem());
	    		Articles article=null;
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		
	    		Date datedebut=DateUtils.SubstractDate(dateChooserdebut.getDate(), 1);
	    		Date datefin=DateUtils.SubstractDate(dateChooserfin.getDate(), 1);
	    		
	    		if(magasin!=null)
	    		{
	    			
	    			if(familleArticle==null)
	    			{
	    				JOptionPane.showMessageDialog(null, "Veuillez choisir une famille d'article SVP !!!");
	    				return;
	    			}
	    			
	    			listSousFamilleArticlesF=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePF(familleArticle.getId());
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
		    		
		    	
		    		
		    		}
		    		
		    				    		
		    		if(dateChooserdebut.getDate()==null )
		    		{
		    			dateChooserfin.setCalendar(null);
		    			titre="Etat Estimation de Stock de "+familleArticle.getLiblle()+" au magasin : "+magasin.getLibelle();
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && familleArticle!=null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    			titre="Etat Estimation de Stock de "+familleArticle.getLiblle() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && familleArticle!=null)
		    		{
		    			String d1=sdf.format(dateChooserdebut.getDate());
		    			String d2=sdf.format(dateChooserfin.getDate());
		    			titre="Etat Estimation de Stock de "+familleArticle.getLiblle() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d2;
		    		}
		    		
		    		
		    		
		    		
		    		
		    		listDetailTransferStockPFInitial=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_INITIAL,magasin, familleArticle);
		    		
		    		listDetailTransferStockPFAchat=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
		    		listDetailTransferStockPFAchatGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
		    		
		    		listDetailTransferStockPFProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
		    		listDetailTransferStockPFProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
		    		
		    		
		    		listDetailTransferStockPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		    		listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		    		
		    		

		    		listDetailTransferStockPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
		    		listDetailTransferStockPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
		    		
		    		
		    		
		    		listDetailTransferStockPFAvoir=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR,magasin, familleArticle);
		    		listDetailTransferStockPFAvoirGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR,magasin, familleArticle);
		    		
		    		
		    		//////////// les Avoir de l'Annee Precedente
		    		
		    		listDetailTransferStockPFAvoir_R=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, datefin, article, ETAT_TRANSFER_STOCK_AVOIR_R,magasin, familleArticle);
		    		listDetailTransferStockPFAvoir_RGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, datefin, article, ETAT_TRANSFER_STOCK_AVOIR_R,magasin, familleArticle);
		    		
		    		////////////////////////////////////////////////////////////////
		    		
		    		
		    		
		    		listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);
		    		
		    		
		    		
		    		
		    		
		    		
		    		
		    		for(int d=0;d<listSousFamilleArticlesF.size();d++)
		    		{
		    			SousFamilleArticlePF sousFamilleArticlePF=listSousFamilleArticlesF.get(d);
		    			EtatStockPF etatstock=new EtatStockPF();
		    			
		    			etatstock.setSousFamille(sousFamilleArticlePF);
		    			etatstock.setFamilleArticle(sousFamilleArticlePF.getFamileArticlePF());
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
		    			etatstock.setQuantiteAvoirAnneePrecedente(BigDecimal.ZERO);
		    			etatstock.setPrixAvoirAnneePrecedente(BigDecimal.ZERO);
		    			etatstock.setMontantAvoirAnneePrecedente(BigDecimal.ZERO);
		    			etatstock.setAchatRevendu(BigDecimal.ZERO);
		    			etatstock.setMarge(BigDecimal.ZERO);
		    			etatstock.setMargePourcentage(BigDecimal.ZERO);
		    			listEtatStockPF.add(etatstock);
		    			
		    		}
		    		
		    		
		    		
		    		
		    		
		    		// charger list Etat stock Articles initialiser les enregistrement des achats et ventes par zero
		    		
		    		
		    		
		    		for(int p=0;p<listEtatStockPF.size();p++)
	    	    	{
		    			PrixInitial=BigDecimal.ZERO;
		    			montantInitial=BigDecimal.ZERO;
		    			quantiteTotalInitial=BigDecimal.ZERO;
		    			
		    			
		    		for(int i=0;i<listDetailTransferStockPFInitial.size();i++)
		    		{
		    			boolean existe=false;
		    			
		    			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFInitial.get(i);
		    			/*
		    			EtatStockPF etatstock=new EtatStockPF();
		    			etatstock.setArticle(detailtransferstockpf.getArticle());
		    			etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
		    			etatstock.setQuantiteInitial(detailtransferstockpf.getQuantite());
		    			*/
		    		if(listEtatStockPF.get(p).getSousFamille().getId()==detailtransferstockpf.getSousFamille().getId())
		    		{
		    			
	    			
	    				PrixInitial= PrixInitial.multiply(quantiteTotalInitial).add(detailtransferstockpf.getPrixUnitaire().multiply(detailtransferstockpf.getQuantite())).divide(quantiteTotalInitial.add(detailtransferstockpf.getQuantite()), RoundingMode.HALF_DOWN) ;
	    			
	    			
	    				montantInitial= montantInitial.add(detailtransferstockpf.getQuantite().multiply(detailtransferstockpf.getPrixUnitaire()));
	    			
	    			quantiteTotalInitial=quantiteTotalInitial.add(detailtransferstockpf.getQuantite());
	    			
		    		}
		    				
		    			
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
		    /*			if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
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
			    			
			    			
			    			
			    		}*/
		    			
		    			
		    		}
		    		
		    		EtatStockPF etatstockpf=listEtatStockPF.get(p);
    				etatstockpf.setQuantiteInitial(quantiteTotalInitial);
    				etatstockpf.setPrixInitial(PrixInitial);
    				etatstockpf.setMontantInitial(montantInitial);
    				listEtatStockPF.set(p, etatstockpf);
		    		
	    	    	}
		    		
		    		
		    		// calculer le prix moyen et quantite achat
		    		
		    		for(int i=0;i<listEtatStockPF.size();i++)
	    	    	{	
		    	
		    		montantachat=new BigDecimal(0);
		    		quantiteTotalachat=new BigDecimal(0);
		    		boolean existe=false;
		    			
		    	for(int k=0;k<listDetailTransferStockPFAchat.size();k++)
		    	{
		    		
		    		if(listEtatStockPF.get(i).getSousFamille().getId()== listDetailTransferStockPFAchat.get(k).getSousFamille().getId())
		    		{
		    			montantachat=montantachat.add(listDetailTransferStockPFAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFAchat.get(k).getQuantite()));
		    			quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockPFAchat.get(k).getQuantite());
		    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		    			
		    		}
		    		
		    		
		    	}
		    		if(!quantiteTotalachat.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6,  RoundingMode.HALF_UP)))
		    		{
		    			EtatStockPF etatstockpf=listEtatStockPF.get(i);
	    				etatstockpf.setQuantiteAchat(quantiteTotalachat);
	    				etatstockpf.setPrixAchat((montantachat.divide(quantiteTotalachat,6,RoundingMode.DOWN)));
	    				etatstockpf.setMontantAchat(etatstockpf.getQuantiteAchat().multiply(etatstockpf.getPrixAchat()));
	    				listEtatStockPF.set(i, etatstockpf);
		    		}
			    				
			    		
		    	}
		    	
	    	// calculer Quantite Production et le prix moyen
		    	
		    
		    		for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
	    	    		
		    
		    		quantiteTotalproduction=BigDecimal.ZERO;
		    		montantproduction=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFProduction.size();j++)
		    		{
		    			
		    			if( listEtatStockPF.get(k).getSousFamille().getId() ==listDetailTransferStockPFProduction.get(j).getSousFamille().getId())
		    			{
		    				
		    				montantproduction=montantproduction.add(listDetailTransferStockPFProduction.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFProduction.get(j).getQuantite()));
		    				quantiteTotalproduction=quantiteTotalproduction.add(listDetailTransferStockPFProduction.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}

                               if(!montantproduction.setScale(2,RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2,RoundingMode.DOWN)) && !quantiteTotalproduction.setScale(2,RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2,RoundingMode.DOWN)))
		    		    		{
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteProduction(quantiteTotalproduction);
		    	    			etatstockpf.setPrixProduction(montantproduction.divide(quantiteTotalproduction,6,RoundingMode.DOWN));
		    	    			etatstockpf.setMontantProduction(quantiteTotalproduction.multiply(montantproduction.divide(quantiteTotalproduction,6,RoundingMode.DOWN)));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    		    		}
		    	    	
		    	    		
		    	    		
		    	    	}
		    			
		    	
		    	// calculer Quantite Vente et le prix moyen
		    	
		    	

	    		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
		    	
		    		quantiteTotalvente=BigDecimal.ZERO;
		    		montantvente=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFSortie.size();j++)
		    		{
		    			
		    			if( listEtatStockPF.get(k).getSousFamille().getId()==listDetailTransferStockPFSortie.get(j).getSousFamille().getId())
		    			{
		    				
		    				montantvente=montantvente.add(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFSortie.get(j).getQuantite()));
		    				quantiteTotalvente=quantiteTotalvente.add(listDetailTransferStockPFSortie.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		   		if(!quantiteTotalvente.setScale(2,RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2,RoundingMode.DOWN)))
				    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteSortie(quantiteTotalvente);
		    	    			etatstockpf.setPrixSortie(montantvente.divide(quantiteTotalvente,6,RoundingMode.DOWN));
		    	    			etatstockpf.setMontantSortie(quantiteTotalvente.multiply(montantvente.divide(quantiteTotalvente,6,RoundingMode.DOWN)));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    	
		    		
		    	
    	// calculer Quantite Transfer Entrer et le prix moyen
		    	
		    	
	    		   	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
		    		quantiteTotalEntrer=BigDecimal.ZERO;
		    		montantEntrer=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFEntrer.size();j++)
		    		{
		    			
		    			if( listEtatStockPF.get(k).getSousFamille().getId()==listDetailTransferStockPFEntrer.get(j).getSousFamille().getId())
		    			{
		    				
		    				montantEntrer=montantEntrer.add(listDetailTransferStockPFEntrer.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFEntrer.get(j).getQuantite()));
		    				quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    		}
		    		
		    		   		if(!quantiteTotalEntrer.setScale(2,RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2,RoundingMode.DOWN)))
				    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteEntrer(quantiteTotalEntrer);
		    	    			etatstockpf.setPrixEntrer(montantEntrer.divide(quantiteTotalEntrer,6,RoundingMode.DOWN));
		    	    			etatstockpf.setMontantEntrer(quantiteTotalEntrer.multiply(montantEntrer.divide(quantiteTotalEntrer,6,RoundingMode.DOWN)));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    		
		    	
	// calculer Quantite avoir et le prix moyen
		    	
		    	
		    	
	    		 	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
		    		quantiteTotalavoir=BigDecimal.ZERO;
		    		montantavoir=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFAvoir.size();j++)
		    		{
		    			
		    			if( listEtatStockPF.get(k).getSousFamille().getId()==listDetailTransferStockPFAvoir.get(j).getSousFamille().getId())
		    			{
		    				
		    				montantavoir=montantavoir.add(listDetailTransferStockPFAvoir.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoir.get(j).getQuantite()));
		    				quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockPFAvoir.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		   		if(!quantiteTotalavoir.setScale(2,RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2,RoundingMode.DOWN)))
				    		{
				    		
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteAvoir(quantiteTotalavoir);
		    	    			etatstockpf.setPrixAvoir(montantavoir.divide(quantiteTotalavoir,6,RoundingMode.DOWN));
		    	    			etatstockpf.setMontantAvoir(quantiteTotalavoir.multiply(montantavoir.divide(quantiteTotalavoir,6,RoundingMode.DOWN)));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
	    		 	
	    		 	
	    		 // calculer Quantite avoir et le prix moyen Annee Precedente
			    	
			    	
			    	
	    		 	for(int k=0;k<listEtatStockPF.size();k++)
	    	    	{
		    		quantiteTotalavoir=BigDecimal.ZERO;
		    		montantavoir=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFAvoir_R.size();j++)
		    		{
		    			
		    			if( listEtatStockPF.get(k).getSousFamille().getId()==listDetailTransferStockPFAvoir_R.get(j).getSousFamille().getId())
		    			{
		    				
		    				montantavoir=montantavoir.add(listDetailTransferStockPFAvoir_R.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoir_R.get(j).getQuantite()));
		    				quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockPFAvoir_R.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		   		if(!quantiteTotalavoir.setScale(2,RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2,RoundingMode.DOWN)))
				    		{
				    		
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteAvoirAnneePrecedente(quantiteTotalavoir);
		    	    			etatstockpf.setPrixAvoirAnneePrecedente(montantavoir.divide(quantiteTotalavoir,6,RoundingMode.DOWN));
		    	    			etatstockpf.setMontantAvoirAnneePrecedente(quantiteTotalavoir.multiply(montantavoir.divide(quantiteTotalavoir,6,RoundingMode.DOWN)));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
	    		 	
	    		 	CalculerprixMoyen();	
		    	
		    	
		    	// Calculer Stock Finale
		    	
		    	
			   	for(int i=0;i<listEtatStockPF.size();i++)
		    	{
			   	  BigDecimal prixMoyen=BigDecimal.ZERO;
			   		   quantiteTotalFinale=BigDecimal.ZERO;
			   		    montantFinale=BigDecimal.ZERO;
		    			EtatStockPF etatstockpf=listEtatStockPF.get(i);
		    			etatstockpf.setQuantiteFinale((etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer()))).subtract(etatstockpf.getQuantiteSortie().add(etatstockpf.getQuantiteAvoir())));
		    			quantiteTotalFinale=quantiteTotalFinale.add(etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer())).subtract(etatstockpf.getQuantiteAvoir().add(etatstockpf.getQuantiteSortie())));
		    			montantFinale=montantFinale.add((etatstockpf.getMontantInitial().add(etatstockpf.getMontantAchat().add(etatstockpf.getMontantProduction()).add(etatstockpf.getMontantEntrer()))));
		    			
		    			
		    			if(listMouvementStockPFAfficherTmp.size()!=0)
			    		{
			    			for(int l=0;l<listMouvementStockPFAfficherTmp.size();l++)
			    			{
			    				if(etatstockpf.getSousFamille().getId()== listMouvementStockPFAfficherTmp.get(l).getSousFamille().getId())
			    				{
			    					
			    					prixMoyen=listMouvementStockPFAfficherTmp.get(l).getPrixFinal();
			    					//System.out.println("Prix Moyen : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb());
			    					
			    				}
			    				
			    			}
			    			
			    		}else
			    		{
			    			for(int l=0;l<listMouvementStockPFAfficher.size();l++)
			    			{
			    				if(etatstockpf.getSousFamille().getId()== listMouvementStockPFAfficher.get(l).getSousFamille().getId())
			    				{
			    					
			    					prixMoyen=listMouvementStockPFAfficher.get(l).getPrixFinal();
			    					//System.out.println("Prix Moyen : "+listMouvementStockMPAfficherMouvementTmp.get(l).getPrixFinaldb());
			    					
			    				}
			    			}
			    			
			    		}
		    			
		    			
		    			
		    			
		    			
		    			if(!quantiteTotalFinale.setScale(2,RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2,RoundingMode.DOWN)))
		    			{
		    				etatstockpf.setPrixFinale(prixMoyen);
			    			etatstockpf.setMontantFinale(etatstockpf.getPrixFinale().multiply(etatstockpf.getQuantiteFinale()));
			    			
		    			}else
		    			{
		    				etatstockpf.setPrixFinale(BigDecimal.ZERO);
			    			etatstockpf.setMontantFinale(BigDecimal.ZERO);
			    			
		    			}
		    			
		    			/*if(etatstockpf.getPrixSortie().setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,  RoundingMode.DOWN)))
		    			{
		    				etatstockpf.setMarge(BigDecimal.ZERO);
		    				
		    			}else
		    			{
		    				etatstockpf.setMarge((etatstockpf.getPrixSortie().subtract(etatstockpf.getPrixInitial())).divide(etatstockpf.getPrixSortie(), RoundingMode.DOWN));
		    			}*/
		    			
		    			
		    			listEtatStockPF.set(i, etatstockpf);
		    		
		    		
		    	}
			  
			   	
			   	
			   	for(int k=0;k<listEtatStockPF.size();k++)
			   	{
			   		
			   	EtatStockPF etatStockPF=listEtatStockPF.get(k);
			   	
			   	etatStockPF.setAchatRevendu((etatStockPF.getMontantInitial().add(etatStockPF.getMontantAchat()).add(etatStockPF.getMontantProduction())).subtract(etatStockPF.getMontantFinale().add(etatStockPF.getMontantAvoir())));
			   	etatStockPF.setMarge(etatStockPF.getMontantSortie().subtract(etatStockPF.getAchatRevendu()));
			   	if(!etatStockPF.getMontantSortie().setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
			   	{
			   		etatStockPF.setMargePourcentage((etatStockPF.getMarge().divide(etatStockPF.getMontantSortie(), RoundingMode.HALF_DOWN)).multiply(new BigDecimal(100)));
			   	}
			   	
			   		
			   	}
			   	
			 	afficher_tableEtatStock(listEtatStockPF);
		    	
		    	
	    			
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
	    label.setBounds(201, 14, 136, 24);
	    layeredPane.add(label);
	    
	     dateChooserdebut = new JDateChooser();
	    dateChooserdebut.setLocale(Locale.FRANCE);
	    dateChooserdebut.setDateFormatString("dd/MM/yyyy");
	    dateChooserdebut.setBounds(292, 14, 163, 26);
	    layeredPane.add(dateChooserdebut);
	    
	    JLabel label_1 = new JLabel("Date Fin :");
	    label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    label_1.setBounds(465, 12, 106, 24);
	    layeredPane.add(label_1);
	    
	     dateChooserfin = new JDateChooser();
	    dateChooserfin.setLocale(Locale.FRANCE);
	    dateChooserfin.setDateFormatString("dd/MM/yyyy");
	    dateChooserfin.setBounds(539, 12, 169, 26);
	    layeredPane.add(dateChooserfin);
	
	    
	    JLabel label_3 = new JLabel("Depot  :");
	    label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    label_3.setBounds(718, 11, 45, 26);
	    layeredPane.add(label_3);
	    
	     combodepot = new JComboBox();
	    combodepot.setBounds(759, 14, 202, 27);
	    layeredPane.add(combodepot);
	    try {
			  
			 
	          Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)util.DateUtils.getCurrentYear()+"-01-01");
	          dateChooserdebut.setDate(date);
	          dateChooserfin.setDate(new Date());
	          
	           comboFamille = new JComboBox();
	          comboFamille.setBounds(1035, 14, 201, 27);
	          layeredPane.add(comboFamille);
	          
	          JLabel lblFamille = new JLabel("Famille  :");
	          lblFamille.setFont(new Font("Tahoma", Font.PLAIN, 11));
	          lblFamille.setBounds(981, 14, 57, 26);
	          layeredPane.add(lblFamille);
			  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	    JButton button = new JButton("Initialiser");
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		

	     		comboFamille.setSelectedItem("");
	     		
	     		dateChooserdebut.setCalendar(null);
	     		dateChooserfin.setCalendar(null);
	     		
	     		
	     	
	    		
	    	}
	    });
	    button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button.setBounds(668, 119, 107, 24);
	    add(button);
	    
	    JButton btnExporterExcel = new JButton("Exporter Excel");
	    btnExporterExcel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    		
	    		String titre="Etat de Stock PF "+mapMagasin.get(combodepot.getSelectedItem()).getLibelle();
	    		String titrefeuille="Etat de Stock PF ";
	    		ExporterTableVersExcel.tabletoexcel(tableEtatStock, titre,titrefeuille);
	    		}else
	    		{


	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		
	    		}
	    	}
	    });
	    btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnExporterExcel.setBounds(565, 793, 123, 32);
	    btnExporterExcel.setIcon(imgExcel);
	    add(btnExporterExcel);
	    comboFamille.addItem("");
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
	
	
	void CalculerprixMoyen()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
		listMouvementStockPF.clear();
		detailTransferStockPFDAO.ViderSession();
		boolean trouve=false;
		Articles article=null;
		FamilleArticlePF familleArticlePF=mapFamilleArticles.get(comboFamille.getSelectedItem());
	listSousFamilleArticlesF=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePF(familleArticlePF.getId());
		
		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
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
    		
    		if(article!=null)
    		{
    			//titre="Mouvement de Stock de "+article.getLiblle() +" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
    		}else
    		{
    		//	titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
    		}
    		
    		}
    		
    		if(dateChooserdebut.getDate()==null )
    		{
    			dateChooserfin.setCalendar(null);
    			//titre="Mouvement de Stock de "+article.getLiblle()+" au magasin : "+magasin.getLibelle();
    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article!=null)
    		{
    			String d1=sdf.format(dateChooserdebut.getDate());
    			//titre="Mouvement de Stock de "+article.getLiblle() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
    			
    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article==null)
    		{
    			String d1=sdf.format(dateChooserdebut.getDate());
    			//titre="Mouvement de Stock de magasin : "+magasin.getLibelle()+ "entre "+d1 +" et "+d1;
    		}
    		
    		listDetailTransferStockPF=detailTransferStockPFDAO.findAllTransferStockPFOrderByDateTransfer(magasin);
    		listDetailTransferStockPFGroupebyArticle=detailTransferStockPFDAO.findAllTransferStockPFGroupeByDateTransferByArticle(magasin);
    		listDetailTransferStockPFBytypetransfer=detailTransferStockPFDAO.findBytypetransfer(ETAT_TRANSFER_STOCK_INITIAL,magasin);
    		String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_VENTE,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,Constantes.ETAT_TRANSFER_STOCK_AVOIR ,ETAT_TRANSFER_STOCK_ENTRER_MP};
    		BigDecimal achat=BigDecimal.ZERO;
    		BigDecimal vente=BigDecimal.ZERO;
    		BigDecimal TransferEntrer=BigDecimal.ZERO;
    		BigDecimal avoir=BigDecimal.ZERO;
    		BigDecimal initial=BigDecimal.ZERO;
    		BigDecimal Prixinitial=BigDecimal.ZERO;
    		BigDecimal production=BigDecimal.ZERO;
    		BigDecimal prixAchat=BigDecimal.ZERO;
    		BigDecimal prixProduction=BigDecimal.ZERO;
    		BigDecimal prixTransfertEntrer=BigDecimal.ZERO;
    		BigDecimal stockfinal=BigDecimal.ZERO;
    		BigDecimal Montantfinal=BigDecimal.ZERO;
    		BigDecimal Quantitefinal=BigDecimal.ZERO;
    		for(int p=0;p<listSousFamilleArticlesF.size();p++)
    		{
    			
    		for(int i=0;i<listDetailTransferStockPFGroupebyArticle.size();i++)
    		{	
    			achat=new BigDecimal(0);
    			vente=new BigDecimal(0);
    			initial=new BigDecimal(0);
    			production=new BigDecimal(0);
    			stockfinal=new BigDecimal(0);
    			avoir=new BigDecimal(0);
    			TransferEntrer=new BigDecimal(0);
    			Prixinitial=new BigDecimal(0);
    			prixProduction=new BigDecimal(0);
    		    Montantfinal=new BigDecimal(0);
        		Quantitefinal=new BigDecimal(0);
        		if(listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().equals(listSousFamilleArticlesF.get(p)))
        		{
        			
    			for(int k=0;k<typetransfer.length;k++)
    			{
    				
    			for(int j=0;j<listDetailTransferStockPF.size();j++)
    			{
    				
    			if(listDetailTransferStockPFGroupebyArticle.get(i).getDateTransfer().equals(listDetailTransferStockPF.get(j).getDateTransfer()) 
    					&&  listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille().equals(listDetailTransferStockPF.get(j).getSousFamille()) && listDetailTransferStockPF.get(j).getTypeTransfer().equals(typetransfer[k]))	
    			{
    			if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ACHAT))
    			{
    				if(!achat.setScale(6, RoundingMode.HALF_UP).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.HALF_UP)).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
    				{
    					prixAchat=(prixAchat.multiply(achat).add(listDetailTransferStockPF.get(j).getQuantite().multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(achat.add(listDetailTransferStockPF.get(j).getQuantite()), RoundingMode.HALF_UP);
    				}
    				
    				achat=achat.add(listDetailTransferStockPF.get(j).getQuantite());
    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_VENTE))
    			{
    				vente=vente.add(listDetailTransferStockPF.get(j).getQuantite());
    				
    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_INITIAL))
    			{
    				if(!initial.setScale(6, RoundingMode.HALF_UP).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.HALF_UP)).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
    				{
    					Prixinitial=(Prixinitial.multiply(achat).add(listDetailTransferStockPF.get(j).getQuantite().multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(initial.add(listDetailTransferStockPF.get(j).getQuantite()), RoundingMode.HALF_UP);
    				}
    				
    				initial=initial.add(listDetailTransferStockPF.get(j).getQuantite());
    			}else if(typetransfer[k].equals(TYPE_TRANSFER_PRODUIT_FINI_ENTRE))
    			{
    				if(!production.setScale(6, RoundingMode.HALF_UP).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.HALF_UP)).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
    				{
    					prixProduction=(prixProduction.multiply(production).add(listDetailTransferStockPF.get(j).getQuantite().multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(production.add(listDetailTransferStockPF.get(j).getQuantite()), RoundingMode.HALF_UP);
    				}
    				
    				production=production.add(listDetailTransferStockPF.get(j).getQuantite());	
    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR))
    			{ 
    				avoir=avoir.add(listDetailTransferStockPF.get(j).getQuantite());	
    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_MP))
    			{ 
    				if(!TransferEntrer.setScale(6, RoundingMode.HALF_UP).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.HALF_UP)).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
    				{
    					prixTransfertEntrer=(prixTransfertEntrer.multiply(TransferEntrer).add(listDetailTransferStockPF.get(j).getQuantite().multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(TransferEntrer.add(listDetailTransferStockPF.get(j).getQuantite()), RoundingMode.HALF_UP);
    				}
    				
    				TransferEntrer=TransferEntrer.add(listDetailTransferStockPF.get(j).getQuantite());	
    			}
    						    				
    			}
    					    			
    			}
    			}
    			
    			if(listMouvementStockPF.size()!=0)
    			{
    				for(int d=0;d<listMouvementStockPF.size();d++)
    				{
    					
    					if( listMouvementStockPF.get(d).getSousFamille().equals(listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille()))
    					{
    						initial=listMouvementStockPF.get(d).getStockFinal();
    						trouve=true;
    						
    					}
    				}
    			
    				
    			}
    			if(trouve==false)
    			{
    				for(int l=0;l<listDetailTransferStockPFBytypetransfer.size();l++)
    				{
    	if(listDetailTransferStockPFBytypetransfer.get(l).getSousFamille().equals(listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille()))
    	{
    		initial=listDetailTransferStockPFBytypetransfer.get(l).getQuantite();
    			
    	}
    				}
    				
				
    			}
    			
    			stockfinal=(initial.add(production).add(achat).add(TransferEntrer)).subtract(vente.add(avoir));
    			MouvementStockProduitsFini mouvementstockPF=new MouvementStockProduitsFini();
    			mouvementstockPF.setDateStockPF(listDetailTransferStockPFGroupebyArticle.get(i).getDateTransfer());
    			mouvementstockPF.setAchat(achat);
    			mouvementstockPF.setPrixAchat(prixAchat);
    			mouvementstockPF.setVente(vente);
    			mouvementstockPF.setAvoir(avoir);
    			mouvementstockPF.setInitial(initial);
    			mouvementstockPF.setPrixInitial(Prixinitial);
    			mouvementstockPF.setEntrerProduction(production);
    			mouvementstockPF.setPrixProduction(prixProduction);
    			mouvementstockPF.setTransferEntrer(TransferEntrer);
    			mouvementstockPF.setPrixTransferEntrer(prixTransfertEntrer);
    			mouvementstockPF.setSousFamille(listSousFamilleArticlesF.get(p));
    			//mouvementstockPF.setArticle(listDetailTransferStockPFGroupebyArticle.get(i).getArticle());
    			mouvementstockPF.setStockFinal(stockfinal);
    			Montantfinal=mouvementstockPF.getInitial().multiply(mouvementstockPF.getPrixInitial().add(mouvementstockPF.getAchat().multiply(mouvementstockPF.getPrixAchat())).add(mouvementstockPF.getEntrerProduction().multiply(mouvementstockPF.getPrixProduction())).add(mouvementstockPF.getTransferEntrer().multiply(mouvementstockPF.getPrixTransferEntrer())));
    			Quantitefinal=mouvementstockPF.getInitial().add(mouvementstockPF.getEntrerProduction().add(mouvementstockPF.getAchat().add(mouvementstockPF.getTransferEntrer())));
    			
    			if(!Quantitefinal.setScale(6, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP)))
    			{
    				mouvementstockPF.setPrixFinal(Montantfinal.divide(Quantitefinal, RoundingMode.HALF_UP));
    			}else
    			{
    				mouvementstockPF.setPrixFinal(BigDecimal.ZERO);
    			}
    			
    			listMouvementStockPF.add(mouvementstockPF);
    			
    		}
    		}
    		
		}
    		
    		// detailtransfer entre deux date et par article
    		
    		if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && familleArticlePF!=null)
    		{
    			listMouvementStockPFAfficher.clear();
    			listMouvementStockPFAfficherTmp.clear();
    			
    			for(int j=0;j<listSousFamilleArticlesF.size();j++)
        		{
    				
    		for(int i=0;i<listMouvementStockPF.size();i++)	
    		{
    			String ddebut=sdf.format(dateChooserdebut.getDate());
    			String ddebutTmp=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
    			
    			if(listMouvementStockPF.get(i).getDateStockPF().after(dateChooserdebut.getDate()) ==true || ddebutTmp.equals(ddebut)   )
    				
    			{
    			if(listMouvementStockPF.get(i).getSousFamille().equals(listSousFamilleArticlesF.get(j)))
    			{

    				listMouvementStockPFAfficher.add(listMouvementStockPF.get(i));	
    			}
    				
    			
    			}
    			
    		}
        		}
    			for(int t=0;t<listSousFamilleArticlesF.size();t++)
    			{
    			
    		for(int j=0;j<listMouvementStockPFAfficher.size();j++)	
    		{
    			
    			String dfin=sdf.format(dateChooserfin.getDate());
    			String dfinTmp=sdf.format(listMouvementStockPFAfficher.get(j).getDateStockPF());
    			if(listMouvementStockPFAfficher.get(j).getDateStockPF().before(dateChooserfin.getDate())==true || dfinTmp.equals(dfin) )
    			{
    			if(listMouvementStockPFAfficher.get(j).getSousFamille().equals(listSousFamilleArticlesF.get(t)))
    			{
    				listMouvementStockPFAfficherTmp.add(listMouvementStockPFAfficher.get(j));
    			}
    				
    			}
    			
    		}
    			}
    		
    		
    			
    		// detailtransfer entre deux date (date fin null) et par article 
    			
    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && familleArticlePF!=null)
    		{
    			listMouvementStockPFAfficherTmp.clear();
    			String d1=sdf.format(dateChooserdebut.getDate());
    		
    			for(int j=0;j<listSousFamilleArticlesF.size();j++)
    			{
    			for(int i=0;i<listMouvementStockPF.size();i++)	
	    		{
    				String ddbut=sdf.format(listMouvementStockPF.get(i).getDateStockPF());
	    			if(ddbut.equals(d1) && listMouvementStockPF.get(i).getSousFamille().equals(listSousFamilleArticlesF.get(j)))
	    			{
	    			
	    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
	    			
	    			}
	    			
	    		}
    		}
    			
    			
    			// detailtransfer entre deux date (date fin null)  
    			
    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && familleArticlePF==null)
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
    		}else if(dateChooserdebut.getDate()==null && dateChooserfin.getDate()==null && familleArticlePF!=null)
    		{
    			listMouvementStockPFAfficherTmp.clear();
             
               
               for(int j=0;j<listSousFamilleArticlesF.size();j++)
               {
    			for(int i=0;i<listMouvementStockPF.size();i++)	
	    		{
	    			if(listMouvementStockPF.get(i).getSousFamille().equals(listSousFamilleArticlesF.get(j)) )
	    			{
	    			
	    				listMouvementStockPFAfficherTmp.add(listMouvementStockPF.get(i));
	    			
	    				
	    			}
	    			
	    		}
               }
    			
    			// detailtransfer entre deux date  
    			
    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()!=null && familleArticlePF==null)
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
	
	void InitialiseTableauDetailMouvementStock()
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Sous Famille", "SI", "Achat", "Production","RRR","RRR(N-1)", "SF", "Achat Revendu","CA","Marge","Marge Pourcentage %"	
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false
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
	       tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(132);
	       tableEtatStock.getColumnModel().getColumn(8).setPreferredWidth(132);
	       tableEtatStock.getColumnModel().getColumn(9).setPreferredWidth(132);
	       tableEtatStock.getColumnModel().getColumn(10).setPreferredWidth(132);
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
	
	
	
	void afficher_tableEtatStock(List<EtatStockPF> listEtatStockPF)
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Sous Famille", "SI", "Achat", "Production","RRR","RRR(N-1)", "SF", "Achat Revendu","CA","Marge","Marge Pourcentage %"	
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modeleEtatStock);
		int i=0;
		 
		while(i<listEtatStockPF.size())
		{	
		EtatStockPF EtatStockPF=listEtatStockPF.get(i);
		
			
				Object []ligne={EtatStockPF.getSousFamille().getLiblle(),EtatStockPF.getMontantInitial().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantAchat().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantProduction().setScale(6, RoundingMode.DOWN), EtatStockPF.getMontantAvoir().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantAvoirAnneePrecedente().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantFinale().setScale(6, RoundingMode.DOWN),EtatStockPF.getAchatRevendu().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantSortie().setScale(6, RoundingMode.DOWN),EtatStockPF.getMarge().setScale(6, RoundingMode.DOWN),EtatStockPF.getMargePourcentage().setScale(2, RoundingMode.DOWN)};

				modeleEtatStock.addRow(ligne);
			
			
			i++;
		}
}
	}


