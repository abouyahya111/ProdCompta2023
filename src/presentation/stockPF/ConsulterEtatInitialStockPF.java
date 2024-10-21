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
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
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


public class ConsulterEtatInitialStockPF extends JLayeredPane implements Constantes{
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
	 
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterEtatInitialStockPF() {
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
         	
         	
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableEtatStock.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
				"Article","Famille","Sous Famille", "Quantite Initial", "Prix Initial","Montant Initial"
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
				
				 if(listEtatStockPF.size()!=0)
				 {
					 
					 int i=0;
					 while(i<listEtatStockPF.size())
					 {
						 EtatStockPF etatstockpf=listEtatStockPF.get(i);
						 totalmontantinitial=totalmontantinitial.add(etatstockpf.getMontantInitial()) ;
						
						
						 
						 
						 i++;
					 }
					 
					 
					 
						Map parameters = new HashMap();


						parameters.put("totalmontantinitial",totalmontantinitial );
													
						
						
						parameters.put("titre", titre);
						JasperUtils.imprimerEtatStockInitialPF(listEtatStockPF,parameters);
						
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
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter Etat Initial Stock PF :");
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
	    		
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAllPFTransfer =new ArrayList<DetailTransferProduitFini>();
	    		
	    	
	    		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	    		BigDecimal montantInitial=new BigDecimal(0);
	    		BigDecimal quantiteTotalInitial=new BigDecimal(0);
	    		BigDecimal PrixInitial=new BigDecimal(0);
	    		
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
		    			titre="Etat Stock Initial de "+article.getLiblle()+" au magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d2;
		    		}else
		    		{
		    			titre="Etat Stock Initial de magasin : "+magasin.getLibelle()+ " entre "+d1+ " et "+d2;
		    		}
		    		
		    		}
		    		
		    				    		
		    		if(dateChooserdebut.getDate()==null )
		    		{
		    			dateChooserfin.setCalendar(null);
		    			titre="Mouvement de Stock Initial de "+article.getLiblle()+" au magasin : "+magasin.getLibelle();
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article!=null)
		    		{
		    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
		    			titre="Mouvement de Stock Initial de "+article.getLiblle() +" au magasin : "+magasin.getLibelle()+" entre "+d1 +" et "+d1;
		    			
		    		}else if(dateChooserdebut.getDate()!=null && dateChooserfin.getDate()==null && article==null)
		    		{
		    			String d1=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
		    			titre="Mouvement de Stock Initial de magasin : "+magasin.getLibelle()+ " entre "+d1 +" et "+d1;
		    		}
		    		
		    		listDetailTransferStockPFInitial=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_INITIAL,magasin, familleArticle);
		    		
		    			    		
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
		    		
		    		
		    /*	
		///////////////////////////////////////////// Modifier la famille des articles Offre (Chaara et Mkarkeb) Par Famille Offre//////////////////////////////////////////////////////////////////////////////	   	
			   
			   	for(int k=0;k<listEtatStockPF.size();k++)
			   	{
			   		
			   	EtatStockPF etatStockPF=listEtatStockPF.get(k);
			   	
			   	if(etatStockPF.getArticle().getCodeArticle().contains(CODE_PRODUIT_FINI_OFFRE))
			   	{
			   		etatStockPF.setFamilleArticle(FamilleOffre);
			   	}
			   	
			   	listEtatStockPF.set(k, etatStockPF);
			   	
			   		
			   	}
			   	
			   	
			   	*/
			   	
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
	    		
	    		String titre="Etat Initial Stock PF "+mapMagasin.get(combodepot.getSelectedItem()).getLibelle();
	    		String titrefeuille="Etat Initial Stock PF ";
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
    		String typetransfer[]={Constantes.ETAT_TRANSFER_STOCK_ACHAT,Constantes.ETAT_TRANSFER_STOCK_VENTE,Constantes.ETAT_TRANSFER_STOCK_INITIAL,Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,Constantes.ETAT_TRANSFER_STOCK_AVOIR ,ETAT_TRANSFER_STOCK_ENTRER_MP,ETAT_TRANSFER_STOCK_ENTRER_PF_MP,ETAT_TRANSFER_STOCK_AVOIR_R};
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
	
	
	void afficher_tableEtatStock(List<EtatStockPF> listEtatStockPF)
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Article","Famille","Sous Famille", "Quantite Initial", "Prix Initial","Montant Initial"
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
		 
		while(i<listEtatStockPF.size())
		{	
		EtatStockPF EtatStockPF=listEtatStockPF.get(i);
		
			
				//Object []ligne={EtatStockPF.getArticle().getLiblle(),EtatStockPF.getFamilleArticle().getLiblle(), EtatStockPF.getSousFamille().getLiblle(),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteInitial().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixInitial().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantInitial().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteAchat().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixAchat().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantAchat().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteProduction().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixProduction().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantProduction().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteEntrer().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixEntrer().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantEntrer().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteSortie().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixSortie().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantSortie().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteGratuit().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixGratuit().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantGratuit().setScale(6, RoundingMode.DOWN)) , NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteAvoir().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixAvoir().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantAvoir().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getQuantiteFinale().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getPrixFinale().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMontantFinale().setScale(6, RoundingMode.DOWN)),NumberUtils.GroupingFormatBigDecimal(EtatStockPF.getMarge().setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP))+"%"};
	
		        Object []ligne={EtatStockPF.getArticle().getLiblle(),EtatStockPF.getFamilleArticle().getLiblle(), EtatStockPF.getSousFamille().getLiblle(),EtatStockPF.getQuantiteInitial().setScale(6, RoundingMode.DOWN),EtatStockPF.getPrixInitial().setScale(6, RoundingMode.DOWN),EtatStockPF.getMontantInitial().setScale(6, RoundingMode.DOWN)};

				modeleEtatStock.addRow(ligne);
			
			
			i++;
		}
}
	}


