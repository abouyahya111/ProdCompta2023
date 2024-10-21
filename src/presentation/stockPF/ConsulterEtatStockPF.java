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
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureAchatDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FactureAvoirClientPFDAOImpl;
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
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAvoirClientPFDAO;
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
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatStockMP;
import dao.entity.EtatStockPF;
import dao.entity.FactureAchat;
import dao.entity.FactureAvoirClientPF;
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


public class ConsulterEtatStockPF extends JLayeredPane implements Constantes{
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
	 JTextArea textArea = new JTextArea();
	 JLabel lblDateInitial = new JLabel("Date Initial :");
	 JButton btnTransfert = new JButton("Transfert");
	 JDateChooser dateinitiale = new JDateChooser();
	 FamilleArticlePF FamilleOffre;
	 private List<FactureAvoirClientPF> listFactureAvoirClientPF =new ArrayList<FactureAvoirClientPF>();
	 private FactureAvoirClientPFDAO factureAvoirClientpfdao;
	 private DetailCompteClientDAO detailCompteClientdao;
	 
	 
	 
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterEtatStockPF() {
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
         listArticle=ArticleDAO.findAll();
         	listFamilleArticlesF=familleArticlesDAO.findAll();
         	
         	FamilleOffre=familleArticlesDAO.findByLibelle(FAMILLE_CADEAU);
         	
         	factureAvoirClientpfdao=new FactureAvoirClientPFDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         	compteclientdao=new CompteClientDAOImpl();
         	
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableEtatStock.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
				"Article","Famille","Sous Famille", "Quantite Initial", "Prix Initial","Montant Initial", "Quantite Achat", "Prix Achat","Montant Achat","Quantite Production", "Prix Production","Montant Production","Quantite Transfer Entrer", "Prix Transfer Entrer","Montant Transfer Entrer","Quantite Vente", "Prix Vente","Montant Vente","Quantite Avoir", "Prix Avoir","Montant Avoir","Quantite Finale", "Prix Finale","Montant Finale","Marge"
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
				BigDecimal totalmontantavoirFournisseur=BigDecimal.ZERO;
				BigDecimal totalmontantavoirClient=BigDecimal.ZERO;
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
						 totalmontantavoirFournisseur=totalmontantavoirFournisseur.add(etatstockpf.getMontantAvoir());
						 totalmontantavoirClient=totalmontantavoirClient.add(etatstockpf.getMontantAvoirClient());
						 totalmontantgratuit=totalmontantgratuit.add(etatstockpf.getMontantGratuit());
						 totalmontantfinale=totalmontantfinale.add(etatstockpf.getMontantFinale());
						
						 
						 
						 i++;
					 }
					 
					 
					 
						Map parameters = new HashMap();


						parameters.put("totalmontantinitial",totalmontantinitial );
						parameters.put("totalmontantachat",totalmontantachat );	
						parameters.put("totalmontantproduction",totalmontantproduction);	
						parameters.put("totalmontantvente",totalmontantvente);	
						parameters.put("totalmontantavoir",totalmontantavoirFournisseur);	
						parameters.put("totalmontantavoirclient",totalmontantavoirClient);	
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
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter Etat de Stock :");
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
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirClientPF =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirClientPFGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
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
		    		
		    		
		    		listDetailTransferStockPFAvoirClientPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR_CLIENT,magasin, familleArticle);
		    		listDetailTransferStockPFAvoirClientPFGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_AVOIR_CLIENT,magasin, familleArticle);

		    		
		    		
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
		    			etatstock.setQuantiteSortiePF(BigDecimal.ZERO);
		    			etatstock.setPrixSortiePF(BigDecimal.ZERO);
		    			etatstock.setMontantSortiePF(BigDecimal.ZERO);
		    			etatstock.setQuantiteEntrer(BigDecimal.ZERO);
		    			etatstock.setPrixEntrer(BigDecimal.ZERO);
		    			etatstock.setMontantEntrer(BigDecimal.ZERO);
		    			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
		    			etatstock.setPrixAvoir(BigDecimal.ZERO);
		    			etatstock.setMontantAvoir(BigDecimal.ZERO);
		    			
		    			etatstock.setQuantiteAvoirClient(BigDecimal.ZERO);
		    			etatstock.setPrixAvoirClient(BigDecimal.ZERO);
		    			etatstock.setMontantAvoirClient(BigDecimal.ZERO);
		    			
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
		    	    			etatstockpf.setQuantiteSortiePF(quantiteTotalvente);
		    	    			etatstockpf.setPrixSortiePF(prixmoyenvente);
		    	    			etatstockpf.setMontantSortiePF(quantiteTotalvente.multiply(prixmoyenvente));
		    	    		
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
		    	    			
		    	    			etatstockpf.setPrixEntrer(((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(etatstockpf.getPrixEntrer().setScale(6, RoundingMode.DOWN).multiply(etatstockpf.getQuantiteEntrer()))).divide(quantiteTotalEntrer.add(etatstockpf.getQuantiteEntrer()),6, RoundingMode.DOWN));

		    	    			etatstockpf.setQuantiteEntrer(etatstockpf.getQuantiteEntrer().add(quantiteTotalEntrer) );
		    	    			
		    	    			etatstockpf.setMontantEntrer(etatstockpf.getQuantiteEntrer().multiply(etatstockpf.getPrixEntrer()));
		    	    			    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    	}    	
		    	
		    	
// calculer Quantite Transfer Entrer et le prix moyen (Les PF transfer En PF )
		    	
		    	
		    	
		    	for(int i=0;i<listDetailTransferStockPFPFEntrerGroupebyPF.size();i++)
		    	{
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
		    	
		
		    	
	// calculer Quantite avoir Client PF et le prix moyen
		    	
		    	
		    	
		    	for(int i=0;i<listDetailTransferStockPFAvoirClientPFGroupebyPF.size();i++)
		    	{
		    		quantiteTotalavoir=BigDecimal.ZERO;
		    		montantavoir=new BigDecimal(0);
		    		prixmoyenavoir=BigDecimal.ZERO;
		    		for(int j=0;j<listDetailTransferStockPFAvoirClientPF.size();j++)
		    		{
		    			
		    			if(listDetailTransferStockPFAvoirClientPFGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFAvoirClientPF.get(j).getArticle()) && listDetailTransferStockPFAvoirClientPFGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFAvoirClientPF.get(j).getSousFamille()))
		    			{
		    				
		    				montantavoir=montantavoir.add(listDetailTransferStockPFAvoirClientPF.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoirClientPF.get(j).getQuantite()));
		    				prixmoyenavoir=((prixmoyenavoir.multiply(quantiteTotalavoir)).add(listDetailTransferStockPFAvoirClientPF.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFAvoirClientPF.get(j).getQuantite()))).divide(quantiteTotalavoir.add(listDetailTransferStockPFAvoirClientPF.get(j).getQuantite()),6, RoundingMode.DOWN) ;

		    				quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockPFAvoirClientPF.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		if(!quantiteTotalavoir.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFAvoirClientPFGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFAvoirClientPFGroupebyPF.get(i).getSousFamille()) )
		    	    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteAvoirClient (quantiteTotalavoir);
		    	    			etatstockpf.setPrixAvoirClient (prixmoyenavoir);
		    	    			etatstockpf.setMontantAvoirClient (quantiteTotalavoir.multiply(prixmoyenavoir));
		    	    			
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
		    			etatstockpf.setQuantiteFinale((etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer()).add(etatstockpf.getQuantiteAvoirClient()))).subtract(etatstockpf.getQuantiteSortie().add(etatstockpf.getQuantiteSortiePF()).add(etatstockpf.getQuantiteAvoir()).add(etatstockpf.getQuantiteGratuit())));
		    			quantiteTotalFinale=quantiteTotalFinale.add(etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer()).add(etatstockpf.getQuantiteAvoirClient())).subtract(etatstockpf.getQuantiteAvoir().add(etatstockpf.getQuantiteSortie().add(etatstockpf.getQuantiteSortiePF())).add(etatstockpf.getQuantiteGratuit())));
		    			montantFinale=etatstockpf.getMontantInitial().add(etatstockpf.getMontantAchat().add(etatstockpf.getMontantProduction().add(etatstockpf.getMontantEntrer()).add(etatstockpf.getMontantAvoirClient())));
		    			QuantiteTotal=etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction().add(etatstockpf.getQuantiteEntrer()).add(etatstockpf.getQuantiteAvoirClient())));
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
			   	
			   	if(etatStockPF.getArticle().getCodeArticle().contains(CODE_PRODUIT_FINI_OFFRE))
			   	{
			   		etatStockPF.setFamilleArticle(FamilleOffre);
			   	}
			   	
			   	
			   		if(etatStockPF.getQuantiteInitial().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteAchat().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteAvoir().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteEntrer().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteProduction().setScale(6).equals(BigDecimal.ZERO.setScale(6)) &&  etatStockPF.getQuantiteSortie().setScale(6).equals(BigDecimal.ZERO.setScale(6)) && etatStockPF.getQuantiteFinale().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
			   		{
			   			
			   		}else
			   		{
			   			listEtatStockPFImprimer.add(etatStockPF);
			   		}
			   		
			   	}
			   	
		    	afficher_tableEtatStock(listEtatStockPFImprimer);
		    	
		    	
	    			
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
	    
	    JButton btnAjouterFactureAvoir = new JButton("Ajouter Facture Avoir Client");
	    btnAjouterFactureAvoir.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		/*
	    	listFactureAvoirClientPF=factureAvoirClientpfdao.findAll();
	    	BigDecimal solde=BigDecimal.ZERO;
	    	for(int i=0;i<listFactureAvoirClientPF.size();i++)	
	    	{
	    		
	    	FactureAvoirClientPF factureAvoirClientPF=	listFactureAvoirClientPF.get(i);

	    	
///////////////////////////////////////////////////////////////////// Supprimer Les Avoirs _R   //////////////////////////////////////////////////////////////////////////////////////////////////
	    	
	    	TransferStockPF transferStockPF_R=transferStockPFDAO.findByCodeTransfert(factureAvoirClientPF.getCodeTransfer()+"_R")	;
	    	
	    	if(transferStockPF_R!=null)
	    	{
	    		
	    		listDetailTransferProduitFini=detailTransferStockPFDAO.findByTransferStockPF(transferStockPF_R.getId())	;
	    		
	    	    for(int j=0;j<listDetailTransferProduitFini.size();j++)	
	    	    {
	    	    	
	    	    DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(j);	
	    	    	
	    	    detailTransferStockPFDAO.delete(detailTransferProduitFini.getId());
	    	    	
	    	    }
	    	    	
	    	    transferStockPFDAO.delete(transferStockPF_R.getId());	
	    	}
	    
	    	
//////////////////////////////////////////////////////////////////////////////////////  Modifier Etat Avoir Par Avoir Client  //////////////////////////////////////////////////////////////////////////////////////	    	
	    	
		TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(factureAvoirClientPF.getCodeTransfer())	;
		
		if(transferStockPF!=null)
    	{
			
	    	listDetailTransferProduitFini=detailTransferStockPFDAO.findByTransferStockPF(transferStockPF.getId())	;
    		
	        for(int j=0;j<listDetailTransferProduitFini.size();j++)	
	        {
	        	
	        DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(j);	
	        detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_AVOIR_CLIENT);
	        detailTransferStockPFDAO.edit(detailTransferProduitFini);
	        	
	        }
	        
	        transferStockPF.setStatut(ETAT_TRANSFER_STOCK_AVOIR_CLIENT);
	        	
	        transferStockPFDAO.edit(transferStockPF);
			
    	}
		

	    	
///////////////////////////////////////////////////////////////////////////////////////  Ajouter Compte Client Credit   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    		
	    		
    DetailCompteClient detailcompteclient=new DetailCompteClient();
    detailcompteclient.setCompteClient(factureAvoirClientPF.getClientPF().getCompteClient());
    detailcompteclient.setUtilisateurCreation(utilisateur);
    detailcompteclient.setDateCreation(new Date());
    detailcompteclient.setDateOperation(factureAvoirClientPF.getDateFacture());
    detailcompteclient.setMontantDebit(BigDecimal.ZERO);
    detailcompteclient.setMontantCredit(factureAvoirClientPF.getMontantTTC());
    detailcompteclient.setDesignation("Montant sur Facture Avoir N "+factureAvoirClientPF.getNumFacture());
    detailcompteclient.setFactureAvoirClientpf(factureAvoirClientPF);
    detailCompteClientdao.add(detailcompteclient);
    solde=factureAvoirClientPF.getClientPF().getCompteClient().getSolde().subtract (factureAvoirClientPF.getMontantTTC());
    factureAvoirClientPF.getClientPF().getCompteClient().setSolde(solde);
    compteclientdao.edit(factureAvoirClientPF.getClientPF().getCompteClient());

	    		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	    		
	    	
	    	
	    	
	    	
	    	}
	    		
	    	JOptionPane.showMessageDialog(null, "OK");	
	    		
	    		
	    		
	    	*/	
	    	}
	    });
	    btnAjouterFactureAvoir.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAjouterFactureAvoir.setBounds(984, 841, 196, 32);
	    add(btnAjouterFactureAvoir);
	    btnAjouterFactureAvoir.setVisible(false);
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
    		String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_VENTE,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,Constantes.ETAT_TRANSFER_STOCK_AVOIR ,ETAT_TRANSFER_STOCK_ENTRER_MP,ETAT_TRANSFER_STOCK_ENTRER_PF_MP,ETAT_TRANSFER_STOCK_AVOIR_R,ETAT_TRANSFER_STOCK_AVOIR_CLIENT,ETAT_TRANSFER_STOCK_ENTRER_PF_PF,ETAT_TRANSFER_STOCK_SORTIE_PF_PF};
    		BigDecimal achat=BigDecimal.ZERO;
    		BigDecimal Prixachat=BigDecimal.ZERO;
    		BigDecimal vente=BigDecimal.ZERO;
    		BigDecimal TransferEntrer=BigDecimal.ZERO;
    		BigDecimal PrixTransferEntrer=BigDecimal.ZERO;
    		BigDecimal avoir=BigDecimal.ZERO;
    		BigDecimal avoirClient=BigDecimal.ZERO;
    		BigDecimal PrixavoirClient=BigDecimal.ZERO;
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
    			avoirClient=new BigDecimal(0);
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
    				
    				if(listDetailTransferStockPF.get(j).getArticle().getId()==203 && listDetailTransferStockPF.get(j).getSousFamille().getId()==7)
	    			{
	    				System.out.print("yes");
	    			}
    		
    			
    				
    				achat=new BigDecimal(0);
        			vente=new BigDecimal(0);
        			initial=new BigDecimal(0);
        			production=new BigDecimal(0);
        			stockfinal=new BigDecimal(0);
        			avoir=new BigDecimal(0);
        			avoirClient=new BigDecimal(0);
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
                  		 Prixproduction=BigDecimal.ZERO;
                  	   }
    				
    				production=production.add(listDetailTransferStockPF.get(j).getQuantite());	
    				
    				
    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR))
    			{ 
    				if(listDetailTransferStockPF.get(j).getSousFamille().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_EMBALLAGE))
    				{
    					avoir=avoir.add(listDetailTransferStockPF.get(j).getQuantite());	
    				}
    				
    				
    				
    				
    			}else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_AVOIR_CLIENT))
    			{ 
    				
    				
    				  if(!avoirClient.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
                 	   {
                     	   PrixavoirClient=(PrixavoirClient.multiply(avoirClient).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPF.get(j).getPrixUnitaire()))).divide(avoirClient.setScale(6, RoundingMode.DOWN).add(listDetailTransferStockPF.get(j).getQuantite().setScale(6, RoundingMode.DOWN)), RoundingMode.DOWN);

                 	   }else
                 	   {
                 		  PrixavoirClient=BigDecimal.ZERO;
                 	   }
    				
    				
    					avoirClient=avoirClient.add(listDetailTransferStockPF.get(j).getQuantite());	
    				
    				
    				
    				
    				
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
 				
 				
 				
 			}	else if(typetransfer[k].equals(ETAT_TRANSFER_STOCK_ENTRER_PF_PF))
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
    					
		    			
if(quantitefinale.add(achat).add(TransferEntrer).add(production).add(avoirClient).compareTo(BigDecimal.ZERO)!=0)
{

	
	prixmoyenne=(achat.multiply(Prixachat).add(quantitefinale.multiply(prixmoyenne)).add(TransferEntrer.multiply(PrixTransferEntrer)).add(production.multiply(Prixproduction)).add(avoirClient.multiply(PrixavoirClient))).divide(quantitefinale.add(achat).add(TransferEntrer).add(production).add(avoirClient),6, RoundingMode.DOWN);
	//System.out.println(listDetailTransferStockPF.get(j).getArticle().getLiblle() + " : "+ listDetailTransferStockPF.get(j).getSousFamille().getLiblle() +" : Prix Moyenne :"+ prixmoyenne );

}
    					
	    			}else
	    			{
	    				prixmoyenne=BigDecimal.ZERO;

	    			}
    				
    				

    				quantitefinale=(quantitefinale.add(achat).add(TransferEntrer).add(production).add(avoirClient)).subtract(vente.add(avoir));
    				
    			}else
    			{
    				
    				
    				if(!initial.add(achat).add(production).add(TransferEntrer).add(avoirClient).setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,RoundingMode.DOWN)))
	    			{
    					
    				
    					prixmoyenne=(achat.multiply(Prixachat).add(initial.multiply(prixinitial)).add(PrixTransferEntrer.multiply(TransferEntrer).add(Prixproduction.multiply(production)).add(PrixavoirClient.multiply(avoirClient)))).divide(initial.add(achat).add(production).add(TransferEntrer).add(avoirClient), 6, RoundingMode.DOWN);
    					
	    			
	    			}else
	    			{
	    				prixmoyenne=BigDecimal.ZERO;

	    			}
    				
    				
    				quantitefinale=(initial.add(achat).add(TransferEntrer).add(production).add(avoirClient)).subtract(vente.add(avoir));

    				
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
    			
    		
    			
    			stockfinal=(initial.add(production).add(achat).add(TransferEntrer).add(avoirClient)).subtract(vente.add(avoir));
    			MouvementStockProduitsFini mouvementstockPF=new MouvementStockProduitsFini();
    			mouvementstockPF.setDateStockPF(listDetailTransferStockPFGroupebyArticle.get(i).getTransferStockPF().getDateTransfer());
    			mouvementstockPF.setAchat(achat);
    			mouvementstockPF.setPrixAchat(Prixachat);
    			mouvementstockPF.setVente(vente);
    			mouvementstockPF.setAvoir(avoir);
    			mouvementstockPF.setAvoirClientPF(avoirClient);
    			mouvementstockPF.setInitial(initial);
    			mouvementstockPF.setPrixInitial(prixinitial);
    			mouvementstockPF.setEntrerProduction(production);
    			mouvementstockPF.setPrixProduction(Prixproduction);
    			mouvementstockPF.setTransferEntrer(TransferEntrer);
    			mouvementstockPF.setPrixTransferEntrer(PrixTransferEntrer);
    			mouvementstockPF.setSousFamille(listDetailTransferStockPFGroupebyArticle.get(i).getSousFamille());
    			mouvementstockPF.setArticle(listDetailTransferStockPFGroupebyArticle.get(i).getArticle());
    			
    		/*	if(mouvementstockPF.getArticle().getLiblle().equals("EL AARAB VERT PROMO 200G") && mouvementstockPF.getSousFamille().getLiblle().equals("9371"))
    			{
    				System.out.println ( "Avoir :  "+avoir );
    				System.out.println ( "new "+mouvementstockPF.getArticle().getLiblle() + " : "+mouvementstockPF.getSousFamille().getLiblle() +" : Prix Moyenne :"+ prixmoyenne );

    			}
    			*/
    			
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
    			mouvementstockPF.setStockFinal((mouvementstockPF.getInitial().add(mouvementstockPF.getAchat()).add(mouvementstockPF.getEntrerProduction()).add(mouvementstockPF.getTransferEntrer()).add(mouvementstockPF.getAvoirClientPF())).subtract(mouvementstockPF.getVente().add(mouvementstockPF.getAvoir())));
    			
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
	
	
	void afficher_tableEtatStock(List<EtatStockPF> listEtatStockPF)
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code Article","Article","Famille","Sous Famille", "Quantite Initial", "Prix Initial","Montant Initial", "Quantite Achat", "Prix Achat","Montant Achat","Quantite Production", "Prix Production","Montant Production","Quantite Transfer Entrer", "Prix Transfer Entrer","Montant Transfer Entrer","Quantite Transfer Sortie", "Prix Transfer Sortie","Montant Transfer Sortie","Quantite Vente", "Prix Vente","Montant Vente", "Quantite Gratuit", "Prix Gratuit","Montant Gratuit","Quantite Avoir FRS", "Prix Avoir FRS","Montant Avoir FRS","Quantite Avoir CLT", "Prix Avoir CLT","Montant Avoir CLT","Quantite Finale", "Prix Finale","Montant Finale","Marge %"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
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
		
			
				//Object []ligne={EtatStockPF.getArticle().getLiblle(),EtatStockPF.getFamilleArticle().getLiblle(), EtatStockPF.getSousFamille().getLiblle(),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteInitial().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixInitial().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantInitial().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteAchat().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixAchat().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantAchat().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteProduction().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixProduction().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantProduction().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteEntrer().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixEntrer().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantEntrer().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteSortie().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixSortie().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantSortie().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteGratuit().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixGratuit().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantGratuit().setScale(6, RoundingMode.DOWN)) , NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteAvoir().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixAvoir().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantAvoir().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteFinale().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixFinale().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantFinale().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMarge().setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP))+"%"};
	
		        Object []ligne={EtatStockPF.getArticle().getCodeArticle(), EtatStockPF.getArticle().getLiblle(),EtatStockPF.getFamilleArticle().getLiblle(), EtatStockPF.getSousFamille().getLiblle(),EtatStockPF.getQuantiteInitial().setScale(6, RoundingMode.DOWN),EtatStockPF.getPrixInitial().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantInitial().setScale(6, RoundingMode.DOWN),EtatStockPF.getQuantiteAchat().setScale(6, RoundingMode.DOWN),EtatStockPF.getPrixAchat().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantAchat().setScale(6, RoundingMode.DOWN),EtatStockPF.getQuantiteProduction().setScale(6, RoundingMode.DOWN),EtatStockPF.getPrixProduction().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantProduction().setScale(6, RoundingMode.DOWN),EtatStockPF.getQuantiteEntrer().setScale(6, RoundingMode.DOWN),EtatStockPF.getPrixEntrer().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantEntrer().setScale(6, RoundingMode.DOWN),EtatStockPF.getQuantiteSortiePF().setScale(6, RoundingMode.DOWN),EtatStockPF.getPrixSortiePF().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantSortiePF().setScale(6, RoundingMode.DOWN) ,EtatStockPF.getQuantiteSortie().setScale(6, RoundingMode.DOWN),EtatStockPF.getPrixSortie().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantSortie().setScale(6, RoundingMode.DOWN),EtatStockPF.getQuantiteGratuit().setScale(6, RoundingMode.DOWN),EtatStockPF.getPrixGratuit().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantGratuit().setScale(6, RoundingMode.DOWN) , EtatStockPF.getQuantiteAvoir().setScale(6, RoundingMode.DOWN),EtatStockPF.getPrixAvoir().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantAvoir().setScale(6, RoundingMode.DOWN),EtatStockPF.getQuantiteAvoirClient().setScale(6, RoundingMode.DOWN),EtatStockPF.getPrixAvoirClient().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantAvoirClient().setScale(6, RoundingMode.DOWN),  EtatStockPF.getQuantiteFinale().setScale(6, RoundingMode.DOWN),EtatStockPF.getPrixFinale().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantFinale().setScale(6, RoundingMode.DOWN),EtatStockPF.getMarge().setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP))};

				modeleEtatStock.addRow(ligne);
			
			
			i++;
		}
}
	}


