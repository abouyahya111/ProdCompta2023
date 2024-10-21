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
import dao.daoImplManager.DetailFactureAvoirPFComparaisonDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FactureAvoirPFComparaisonDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
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
import dao.daoManager.DetailFactureAvoirPFComparaisonDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAvoirPFComparaisonDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
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
import dao.entity.DetailFactureAvoirPFComparaison;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatStockMP;
import dao.entity.EtatStockPF;
import dao.entity.EtatStockPFComparerAvoir;
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


public class ConsulterEtatStockPFComparaisonAvoir extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEtatStock;
	private DefaultTableModel	 modeleMouvementStock;

	private JXTable  tableEtatStock = new JXTable();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	private List<EtatStockPFComparerAvoir> listEtatStockPF =new ArrayList<EtatStockPFComparerAvoir>();
	private List<EtatStockPF> listEtatStockPFImprimer =new ArrayList<EtatStockPF>();
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
	private DetailFactureAvoirPFComparaisonDAO detailFactureAvoirPFComparaisonDAO;
	private FactureAvoirPFComparaisonDAO factureAvoirPFComparaisonDAO;
	 JTextArea textArea = new JTextArea();
	 JLabel lblDateInitial = new JLabel("Date Initial :");
	 JButton btnTransfert = new JButton("Transfert");
	 JDateChooser dateinitiale = new JDateChooser();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterEtatStockPFComparaisonAvoir() {
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
         	detailFactureAvoirPFComparaisonDAO=new DetailFactureAvoirPFComparaisonDAOImpl();
         	factureAvoirPFComparaisonDAO=new FactureAvoirPFComparaisonDAOImpl();
         	transferStockPFDAO=new TransferStockPFDAOImpl();
         listArticle=ArticleDAO.findAll();
         	listFamilleArticlesF=familleArticlesDAO.findAll();
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableEtatStock.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Article","Famille","Sous Famille","Quantite Gratuit", "Prix Gratuit","Montant Gratuit","Quantite Avoir", "Prix Avoir","Montant Avoir","Quantite Avoir Normal", "Prix Avoir Normal","Montant Avoir Normal","Quantite Avoir Marjan", "Prix Avoir Marjan","Montant Avoir Marjan","Quantite Difference Gratuite", "Prix Difference Gratuite","Montant Difference Gratuite","Quantite Difference Marjan", "Prix Difference Marjan","Montant Difference Marjan","Quantite Difference Total", "Prix Difference Total","Montant Difference Total"       	}
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
       tableEtatStock.getColumnModel().getColumn(10).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(11).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(12).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(13).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(14).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(15).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(16).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(17).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(18).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(19).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(20).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(21).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(22).setPreferredWidth(99);
       tableEtatStock.getColumnModel().getColumn(23).setPreferredWidth(99);
      
       
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
				BigDecimal totalmontantinitial=BigDecimal.ZERO;
				BigDecimal totalmontantachat=BigDecimal.ZERO;
				BigDecimal totalmontantproduction=BigDecimal.ZERO;
				BigDecimal totalmontantvente=BigDecimal.ZERO;
				BigDecimal totalmontantavoir=BigDecimal.ZERO;
				BigDecimal totalmontantgratuit=BigDecimal.ZERO;
				BigDecimal totalmontantfinale=BigDecimal.ZERO;
				
				 if(listEtatStockPFImprimer.size()!=0)
				 {
					 
					 int i=0;
					 while(i<listEtatStockPFImprimer.size())
					 {
						 EtatStockPF etatstockpf=listEtatStockPFImprimer.get(i);
						 totalmontantinitial=totalmontantinitial.add(etatstockpf.getMontantInitial()) ;
						 totalmontantachat=totalmontantachat.add(etatstockpf.getMontantAchat());
						 totalmontantproduction=totalmontantproduction.add(etatstockpf.getMontantProduction());
						 totalmontantvente=totalmontantvente.add(etatstockpf.getMontantSortie());	
						 totalmontantavoir=totalmontantavoir.add(etatstockpf.getMontantAvoir());
						 totalmontantgratuit=totalmontantgratuit.add(etatstockpf.getMontantGratuit());
						 totalmontantfinale=totalmontantfinale.add(etatstockpf.getMontantFinale());
						
						 
						 
						 i++;
					 }
					 
					 
					 
						Map parameters = new HashMap();


						parameters.put("totalmontantinitial",totalmontantinitial );
						parameters.put("totalmontantachat",totalmontantachat );	
						parameters.put("totalmontantproduction",totalmontantproduction);	
						parameters.put("totalmontantvente",totalmontantvente);	
						parameters.put("totalmontantavoir",totalmontantavoir);	
						parameters.put("totalmontantgratuit",totalmontantgratuit);
						parameters.put("totalmontantfinale",totalmontantfinale);							
						
						
						parameters.put("titre", titre);
						JasperUtils.imprimerEtatStockPF(listEtatStockPFImprimer,parameters);
						
				 } else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun Etat Stock  ", "Erreur", JOptionPane.ERROR_MESSAGE); 
					 return;
				 }
					
				
				
			}});
		
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(837, 793, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter Etat de Comparaison Avoir PF :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 951, 35);
		add(lblConslterLesFactures);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		detailTransferStockPFDAO.ViderSession();
	    		
	    		listEtatStockPFImprimer.clear();
	    		listEtatStockPF.clear();
	    		
	    		 List<DetailFactureAvoirPFComparaison> listDetailFactureComparaisonNormal =new ArrayList<DetailFactureAvoirPFComparaison>();
	    		 List<DetailFactureAvoirPFComparaison> listDetailFactureComparaisonNormalGroupebyPF =new ArrayList<DetailFactureAvoirPFComparaison>();
	    		 
	    		 List<DetailFactureAvoirPFComparaison> listDetailFactureComparaisonMarjan =new ArrayList<DetailFactureAvoirPFComparaison>();
	    		 List<DetailFactureAvoirPFComparaison> listDetailFactureComparaisonMarjanGroupebyPF =new ArrayList<DetailFactureAvoirPFComparaison>();
	    	
	    	
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    	
	    		
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
		    		
		    		
		    		///////////////////////////////// Les Factures Avoir Comparaison Marjan /////////////////////////////////////////////////////////
		    		
		    		listDetailFactureComparaisonMarjan=detailFactureAvoirPFComparaisonDAO.ListDetailFactureAvoirPFComparaisonEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, Constantes.STATUT_FACTURE_AVOIR_COMPARAISON_MARJAN,magasin, familleArticle);
		    		listDetailFactureComparaisonMarjanGroupebyPF=detailFactureAvoirPFComparaisonDAO.ListDetailFactureAvoirPFComparaisonEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, Constantes.STATUT_FACTURE_AVOIR_COMPARAISON_MARJAN,magasin, familleArticle);
		    		
                      ///////////////////////////////// Les Factures Avoir Comparaison Normal  /////////////////////////////////////////////////////////
		    		
		    		listDetailFactureComparaisonNormal=detailFactureAvoirPFComparaisonDAO.ListDetailFactureAvoirPFComparaisonEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, Constantes.STATUT_FACTURE_AVOIR_COMPARAISON_NORMAL,magasin, familleArticle);
		    		listDetailFactureComparaisonNormalGroupebyPF=detailFactureAvoirPFComparaisonDAO.ListDetailFactureAvoirPFComparaisonEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, Constantes.STATUT_FACTURE_AVOIR_COMPARAISON_NORMAL,magasin, familleArticle);
		    		
		    		
                        ///////////////////////////////// Les Transfert Gratuite  /////////////////////////////////////////////////////////
		    		
		    		listDetailTransferStockPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		    		listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		    		
                           ///////////////////////////////// Les Transfert Avoir Marjan  /////////////////////////////////////////////////////////
		    		
		    		listDetailTransferStockPFAvoirMarajne=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR_R,magasin, familleArticle);
		    		listDetailTransferStockPFAvoirMarajneGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR_R,magasin, familleArticle);
		    		
		    		listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);
		    		
		    		for(int d=0;d<listDetailTransferStockPFAllPFTransfer.size();d++)
		    		{
		    			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFAllPFTransfer.get(d);
		    			EtatStockPFComparerAvoir etatstock=new EtatStockPFComparerAvoir();
		    			etatstock.setArticle(detailtransferstockpf.getArticle());
		    			etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
		    			etatstock.setFamilleArticle(detailtransferstockpf.getSousFamille().getFamileArticlePF());		    			
		    			
		    			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
		    			etatstock.setPrixAvoir(BigDecimal.ZERO);
		    			etatstock.setMontantAvoir(BigDecimal.ZERO);
		    			
		    			etatstock.setQuantiteGratuit(BigDecimal.ZERO);
		    			etatstock.setPrixGratuit(BigDecimal.ZERO);
		    			etatstock.setMontantGratuit(BigDecimal.ZERO);
		    			
		    			etatstock.setQuantiteAvoirComparaisonMarjan(BigDecimal.ZERO);
		    			etatstock.setPrixAvoirComparaisonMarjan(BigDecimal.ZERO);
		    			etatstock.setMontantAvoirComparaisonMarjan(BigDecimal.ZERO);
		    			
		    			etatstock.setQuantiteAvoirComparaisonNormal(BigDecimal.ZERO);
		    			etatstock.setPrixAvoirComparaisonNormal(BigDecimal.ZERO);
		    			etatstock.setMontantAvoirComparaisonNormal(BigDecimal.ZERO);
		    			
		    			
		    			etatstock.setQuantiteDifferenceGratuite(BigDecimal.ZERO);
		    			etatstock.setPrixDifferenceGratuite(BigDecimal.ZERO);
		    			etatstock.setMontantDifferenceGratuite(BigDecimal.ZERO);
		    			
		    			etatstock.setQuantiteDifferenceMarjan(BigDecimal.ZERO);
		    			etatstock.setPrixDifferenceMarjan(BigDecimal.ZERO);
		    			etatstock.setMontantDifferenceMarjan(BigDecimal.ZERO);
		    			
		    			etatstock.setQuantiteDifferenceTotal(BigDecimal.ZERO);
		    			etatstock.setPrixDifferenceTotal(BigDecimal.ZERO);
		    			etatstock.setMontantDifferenceTotal(BigDecimal.ZERO);
		    			
		    			
		    			
		    			listEtatStockPF.add(etatstock);
		    			
		    		}
		    		
		
		    		
		    		

		    	
		    	
		    	
		    	// calculer Quantite Gratuité et le prix moyen
		    	
		    	
		    	
		    	for(int i=0;i<listDetailTransferStockPFSortieGroupebyPF.size();i++)
		    	{
		    		
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
		    					
		    					
		    					
		    				}
		    				
		    				
		    			}
		    			
		    			
		    		}
		 
		    		
		    		
		    		if(!quantiteTotalGratuit.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille()))
		    	    		{
		    	    			
		    	    			EtatStockPFComparerAvoir etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteGratuit(quantiteTotalGratuit.setScale(6));
		    	    		
		    	    			
		    	    			etatstockpf.setMontantGratuit(etatstockpf.getQuantiteGratuit().setScale(6).multiply(etatstockpf.getPrixGratuit().setScale(6)));;
		    	    			
		    	    			 // ajouter la quantite gratuité et le prix gratuité(prix d'achat)
		    	    			
		    	    			
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
		    	    			
		    	    			EtatStockPFComparerAvoir etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteAvoir(quantiteTotalavoir);
		    	    			etatstockpf.setPrixAvoir(prixmoyenavoir);
		    	    			etatstockpf.setMontantAvoir(quantiteTotalavoir.multiply(prixmoyenavoir));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    		
		    		
		    	} 
		    	
		    	
		    	
// calculer Quantite avoir Comparaison Marjane  et le prix moyen
		    	
		    	
		    	
		    	for(int i=0;i<listDetailFactureComparaisonMarjanGroupebyPF.size();i++)
		    	{
		    		quantiteTotalavoir=BigDecimal.ZERO;
		    		montantavoir=new BigDecimal(0);
		    		prixmoyenavoir=BigDecimal.ZERO;
		    		for(int j=0;j<listDetailFactureComparaisonMarjan.size();j++)
		    		{
		    			
		    			if(listDetailFactureComparaisonMarjanGroupebyPF.get(i).getArticle().equals(listDetailFactureComparaisonMarjan.get(j).getArticle()) && listDetailFactureComparaisonMarjanGroupebyPF.get(i).getSousFamille().equals(listDetailFactureComparaisonMarjan.get(j).getSousFamille()))
		    			{
		    				
		    				montantavoir=montantavoir.add(listDetailFactureComparaisonMarjan.get(j).getPrixUnitaire().multiply(listDetailFactureComparaisonMarjan.get(j).getQuantite()));
		    				prixmoyenavoir=((prixmoyenavoir.multiply(quantiteTotalavoir)).add(listDetailFactureComparaisonMarjan.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailFactureComparaisonMarjan.get(j).getQuantite()))).divide(quantiteTotalavoir.add(listDetailFactureComparaisonMarjan.get(j).getQuantite()),6, RoundingMode.DOWN) ;

		    				quantiteTotalavoir=quantiteTotalavoir.add(listDetailFactureComparaisonMarjan.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		if(!quantiteTotalavoir.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailFactureComparaisonMarjanGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailFactureComparaisonMarjanGroupebyPF.get(i).getSousFamille()) )
		    	    		{
		    	    			
		    	    			EtatStockPFComparerAvoir etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteAvoirComparaisonMarjan(quantiteTotalavoir);
		    	    			etatstockpf.setPrixAvoirComparaisonMarjan(prixmoyenavoir);
		    	    			etatstockpf.setMontantAvoirComparaisonMarjan(quantiteTotalavoir.multiply(prixmoyenavoir));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    		
		    		
		    	} 
		    	
		    	
		    	
		    	
// calculer Quantite avoir Comparaison Normal  et le prix moyen
		    	
		    	
		    	
		    	for(int i=0;i<listDetailFactureComparaisonNormalGroupebyPF.size();i++)
		    	{
		    		quantiteTotalavoir=BigDecimal.ZERO;
		    		montantavoir=new BigDecimal(0);
		    		prixmoyenavoir=BigDecimal.ZERO;
		    		for(int j=0;j<listDetailFactureComparaisonNormal.size();j++)
		    		{
		    			
		    			if(listDetailFactureComparaisonNormalGroupebyPF.get(i).getArticle().equals(listDetailFactureComparaisonNormal.get(j).getArticle()) && listDetailFactureComparaisonNormalGroupebyPF.get(i).getSousFamille().equals(listDetailFactureComparaisonNormal.get(j).getSousFamille()))
		    			{
		    				
		    				montantavoir=montantavoir.add(listDetailFactureComparaisonNormal.get(j).getPrixUnitaire().multiply(listDetailFactureComparaisonNormal.get(j).getQuantite()));
		    				prixmoyenavoir=((prixmoyenavoir.multiply(quantiteTotalavoir)).add(listDetailFactureComparaisonNormal.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailFactureComparaisonNormal.get(j).getQuantite()))).divide(quantiteTotalavoir.add(listDetailFactureComparaisonNormal.get(j).getQuantite()),6, RoundingMode.DOWN) ;

		    				quantiteTotalavoir=quantiteTotalavoir.add(listDetailFactureComparaisonNormal.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		if(!quantiteTotalavoir.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailFactureComparaisonNormalGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailFactureComparaisonNormalGroupebyPF.get(i).getSousFamille()) )
		    	    		{
		    	    			
		    	    			EtatStockPFComparerAvoir etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteAvoirComparaisonNormal(quantiteTotalavoir);
		    	    			etatstockpf.setPrixAvoirComparaisonNormal(prixmoyenavoir);
		    	    			etatstockpf.setMontantAvoirComparaisonNormal(quantiteTotalavoir.multiply(prixmoyenavoir));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    		
		    		
		    	} 
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    
		    	
		    	
		    	// Calculer Stock Finale
		    	
		    	
			   	for(int i=0;i<listEtatStockPF.size();i++)
		    	{
			   	 
		    			EtatStockPFComparerAvoir etatstockpf=listEtatStockPF.get(i);
		    			
		    			///////////////////////////////////////////////////////// La difference Gratuité //////////////////////////////////////////////////////////
		    			
		    			etatstockpf.setQuantiteDifferenceGratuite(etatstockpf.getQuantiteGratuit().subtract(etatstockpf.getQuantiteAvoirComparaisonNormal()));
		    			etatstockpf.setPrixDifferenceGratuite(etatstockpf.getPrixGratuit().subtract(etatstockpf.getPrixAvoirComparaisonNormal()));
		    			etatstockpf.setMontantDifferenceGratuite(etatstockpf.getMontantGratuit().subtract(etatstockpf.getMontantAvoirComparaisonNormal()));
		    			
		    			///////////////////////////////////////////////////////// La difference Marjane //////////////////////////////////////////////////////////
		    			
		    			etatstockpf.setQuantiteDifferenceMarjan(etatstockpf.getQuantiteAvoir().subtract(etatstockpf.getQuantiteAvoirComparaisonMarjan()));
		    			etatstockpf.setPrixDifferenceMarjan(etatstockpf.getPrixAvoir().subtract(etatstockpf.getPrixAvoirComparaisonMarjan()));
		    			etatstockpf.setMontantDifferenceMarjan(etatstockpf.getMontantAvoir().subtract(etatstockpf.getMontantAvoirComparaisonMarjan()));
		    			
		    			
                          ///////////////////////////////////////////////////////// La difference Total //////////////////////////////////////////////////////////
		    			
		    			etatstockpf.setQuantiteDifferenceTotal(etatstockpf.getQuantiteDifferenceGratuite().subtract(etatstockpf.getQuantiteDifferenceMarjan()));
		    			etatstockpf.setPrixDifferenceTotal(etatstockpf.getPrixDifferenceGratuite().subtract(etatstockpf.getPrixDifferenceMarjan()));
		    			etatstockpf.setMontantDifferenceTotal(etatstockpf.getMontantDifferenceGratuite().subtract(etatstockpf.getMontantDifferenceMarjan()));

		    			
		    			
		    			
		    			listEtatStockPF.set(i, etatstockpf);
		    		
		    		
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
	    
	    JButton btnExporterExcel = new JButton("Exporter Excel");
	    btnExporterExcel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    		
	    		String titre="Etat De Comparaison Avoir PF "+mapMagasin.get(combodepot.getSelectedItem()).getLibelle();
	    		String titrefeuille="Etat De Comparaison Avoir PF ";
	    		ExporterTableVersExcel.tabletoexcel(tableEtatStock, titre,titrefeuille);
	    		
	    		}else
	    		{


	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		
	    		}
	    	}
	    });
	    btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnExporterExcel.setBounds(704, 793, 123, 32);
	    btnExporterExcel.setIcon(imgExcel);
	    add(btnExporterExcel);
	    comboFamille.addItem("");
	    
	    JCheckBox chckbxTransfertStockFinale = new JCheckBox("Transfert Stock Finale Vers Stock Initial");
	    chckbxTransfertStockFinale.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		if(chckbxTransfertStockFinale.isSelected()==true)
	    		{
	    			
	    			btnTransfert.setVisible(true);
	    			lblDateInitial.setVisible(true);
	    			dateinitiale.setVisible(true);
	    			
	    			
	    		}else
	    		{
	    			
	    			btnTransfert.setVisible(false);
	    			lblDateInitial.setVisible(false);
	    			dateinitiale.setVisible(false);	
	    			
	    		}
	    		
	    		
	    		
	    		
	    	}
	    });
	    chckbxTransfertStockFinale.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	    chckbxTransfertStockFinale.setBounds(20, 793, 278, 23);
	    chckbxTransfertStockFinale.setVisible(false);
	    add(chckbxTransfertStockFinale);
	    
	     btnTransfert = new JButton("Transfert");
	     btnTransfert.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	     		if(magasin!=null)
	     			{
	     			TransferStockPF transferStockPF =new TransferStockPF();
	     			
	     			if(listEtatStockPFImprimer.size()!=0)
					 {
						 
						 int i=0;
						 while(i<listEtatStockPFImprimer.size())
						 {
							 EtatStockPF etatstockpf=listEtatStockPFImprimer.get(i);
							 
							DetailTransferProduitFini detailTransferProduitFini=new DetailTransferProduitFini(); 
							 detailTransferProduitFini.setArticle(etatstockpf.getArticle());
							 detailTransferProduitFini.setDateTransfer(dateinitiale.getDate());
							 detailTransferProduitFini.setMagasinDestination(magasin);
							detailTransferProduitFini.setPrixUnitaire(etatstockpf.getPrixFinale());
							detailTransferProduitFini.setQuantite(etatstockpf.getQuantiteFinale());
							detailTransferProduitFini.setSousFamille(etatstockpf.getSousFamille());
							detailTransferProduitFini.setTransferStockPF(transferStockPF);
							detailTransferProduitFini.setTypeTransfer(Constantes.ETAT_TRANSFER_STOCK_INITIAL);
							listDetailTransferProduitFini.add(detailTransferProduitFini);
							 
							 i++;
						 }
						 
						 
					
							
					 } else
					 {
						 JOptionPane.showMessageDialog(null, "Il n'existe auccun Etat Stock  ", "Erreur", JOptionPane.ERROR_MESSAGE); 
						 return;
					 }
	     			
	     			
	     			if(listDetailTransferProduitFini.size()!=0)
	     			{
	     				boolean insert=false;
	     				String codeTransfert=Utils.genererCodeTransfer(magasin.getDepot().getCode(),ETAT_TRANSFER_STOCK_INITIAL);
	     				transferStockPF.setCodeTransfer(codeTransfert);
						transferStockPF.setCreerPar(utilisateur);
						transferStockPF.setDate(new Date());
						transferStockPF.setDateTransfer(dateinitiale.getDate());
						transferStockPF.setStatut(Constantes.ETAT_TRANSFER_STOCK_INITIAL);
						transferStockPFDAO.add(transferStockPF);
	     				for(int i=0;i<listDetailTransferProduitFini.size();i++)
	     				{
	     					
	     					DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(i);
	     					detailTransferStockPFDAO.add(detailTransferProduitFini);
	     					insert=true;
	     					
	     					
	     				}
	     				
	     				if(insert==true)
	     				{
	     					JOptionPane.showMessageDialog(null, "Stock Initial Ajouter Avec Succée");
		     				return;
	     				}
	     				
	     				
	     				
	     			}
	     			
	     			
	     			
	     			
	     			
	     		}else
	     		{
	     			JOptionPane.showMessageDialog(null, "Veuillez Selectionner le magasin SVP  ", "Erreur", JOptionPane.ERROR_MESSAGE); 
					 return;
	     		}
				 
					
	     		
	     		
	     	}
	     });
	    btnTransfert.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnTransfert.setBounds(571, 793, 123, 32);
	    add(btnTransfert);
	    btnTransfert.setVisible(false);
	     lblDateInitial = new JLabel("Date Initial :");
	    lblDateInitial.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    lblDateInitial.setBounds(304, 794, 89, 24);
	    add(lblDateInitial);
	    lblDateInitial.setVisible(false);
	     dateinitiale = new JDateChooser();
	    dateinitiale.setLocale(Locale.FRANCE);
	    dateinitiale.setDateFormatString("dd/MM/yyyy");
	    dateinitiale.setBounds(395, 794, 163, 26);
	    add(dateinitiale);
	    dateinitiale.setVisible(false);
	    
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
	
	

	
	
	void afficher_tableEtatStock(List<EtatStockPFComparerAvoir> listEtatStockPF)
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article","Famille","Sous Famille","Quantite Gratuit", "Prix Gratuit","Montant Gratuit","Quantite Avoir", "Prix Avoir","Montant Avoir","Quantite Avoir Normal", "Prix Avoir Normal","Montant Avoir Normal","Quantite Avoir Marjan", "Prix Avoir Marjan","Montant Avoir Marjan","Quantite Difference Gratuite", "Prix Difference Gratuite","Montant Difference Gratuite","Quantite Difference Marjan", "Prix Difference Marjan","Montant Difference Marjan","Quantite Difference Total", "Prix Difference Total","Montant Difference Total"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableEtatStock.setModel(modeleEtatStock);
		int i=0;
		 
		while(i<listEtatStockPF.size())
		{	
		EtatStockPFComparerAvoir EtatStockPF=listEtatStockPF.get(i);
		
			
				//Object []ligne={EtatStockPF.getArticle().getLiblle(),EtatStockPF.getFamilleArticle().getLiblle(), EtatStockPF.getSousFamille().getLiblle(),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteInitial().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixInitial().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantInitial().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteAchat().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixAchat().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantAchat().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteProduction().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixProduction().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantProduction().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteEntrer().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixEntrer().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantEntrer().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteSortie().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixSortie().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantSortie().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteGratuit().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixGratuit().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantGratuit().setScale(6, RoundingMode.DOWN)) , NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteAvoir().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixAvoir().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantAvoir().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteFinale().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixFinale().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantFinale().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMarge().setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP))+"%"};
	
		        Object []ligne={EtatStockPF.getArticle().getLiblle(),EtatStockPF.getFamilleArticle().getLiblle(), EtatStockPF.getSousFamille().getLiblle(),EtatStockPF.getQuantiteGratuit(),EtatStockPF.getPrixGratuit(),EtatStockPF.getMontantGratuit(),EtatStockPF.getQuantiteAvoir(),EtatStockPF.getPrixAvoir(),EtatStockPF.getMontantAvoir(),EtatStockPF.getQuantiteAvoirComparaisonNormal(),EtatStockPF.getPrixAvoirComparaisonNormal(),EtatStockPF.getMontantAvoirComparaisonNormal(),EtatStockPF.getQuantiteAvoirComparaisonMarjan(),EtatStockPF.getPrixAvoirComparaisonMarjan(),EtatStockPF.getMontantAvoirComparaisonMarjan(),EtatStockPF.getQuantiteDifferenceGratuite(),EtatStockPF.getPrixDifferenceGratuite(),EtatStockPF.getMontantDifferenceGratuite(),EtatStockPF.getQuantiteDifferenceMarjan(),EtatStockPF.getPrixDifferenceMarjan(),EtatStockPF.getMontantDifferenceMarjan(),EtatStockPF.getQuantiteDifferenceTotal(),EtatStockPF.getPrixDifferenceTotal(),EtatStockPF.getMontantDifferenceTotal()};
				modeleEtatStock.addRow(ligne);
			
			
			i++;
		}
}
	}


