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
import dao.entity.EtatAvoirFinancePF;
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


public class ConsulterEtatAvoirFinance extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEtatStock;
	private DefaultTableModel	 modeleMouvementStock;

	private JXTable  tableEtatStock = new JXTable();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	
	//**********************************************************************listes Mouvemement Stock***************************************************
	
	private List<Object[]> listDetailTransferStockPFAvoir =new ArrayList<Object[]>(); // liste des avoir sans Emballage
	private List<Object[]> listDetailTransferStockPFGratuite =new ArrayList<Object[]>(); // liste des Gratuites sans Emballage
	
	 List<DetailTransferProduitFini> listDetailTransferStockPFInitial =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAchat =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAchatGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	
	//*************************************************************************************************************************************************
	
	private List<EtatAvoirFinancePF> listEtatAvoirFinancePF =new ArrayList<EtatAvoirFinancePF>();
	private List<EtatAvoirFinancePF> listEtatAvoirFinancePFImprimer =new ArrayList<EtatAvoirFinancePF>();
	
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<SousFamilleArticlePF> listSousFamille =new ArrayList<SousFamilleArticlePF>();
	
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();

	private Map< String, Articles> mapArticle= new HashMap<>();
	private Map< String, Articles> mapCodeArticle= new HashMap<>();
	private Map< Integer, Articles> mapIdArticle= new HashMap<>();
	private Map< Integer, SousFamilleArticlePF> mapIdSousFamille= new HashMap<>();
	
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
	 private JComboBox comboArticle;
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
	private JTextField txtcodeArticle;
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterEtatAvoirFinance() {
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
         	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
         	familleArticlesDAO=new FamilleArticlesPFDAOImpl();
         	detailTransferStockPFDAO=new DetailTransferProduitFiniDAOImpl();
            listArticle=ArticleDAO.findAll();
         	listSousFamille=sousFamilleArticleDAO.findAll();
         	
       
         	
          } catch (Exception exp){exp.printStackTrace();}
      
       
        
       tableEtatStock.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
				"Code", "Article","Sous Famille", "Quantite Avoir", "prix","Total","Quantite Gratuite", "Prix", "Total","Quantite Finance","Ecart"	
      	}
       ));
       tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(92);
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
				
				 if(listEtatAvoirFinancePF.size()!=0)
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
						
					 Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
					 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						String datedebut=dateFormat.format(dateChooserdebut.getDate());
				  		  String dateFin=dateFormat.format(dateChooserfin.getDate());
					 
					    parameters.put("magasin", magasin.getLibelle());
						parameters.put("datedebut",datedebut );
						parameters.put("datefin", dateFin);
						JasperUtils.imprimerEtatAvoirFinancePF(listEtatAvoirFinancePF,parameters);
						
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
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter Etat Avoir Finance PF :");
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
	    		listEtatAvoirFinancePF.clear();
	    		
	    		Articles article=mapArticle.get(comboArticle.getSelectedItem());
	    		Magasin magasin=mapMagasin.get(combodepot.getSelectedItem());
	    		FamilleArticlePF familleArticlePF=familleArticlesDAO.findByLibelle("EMBALLAGE");
	    		listDetailTransferStockPFAvoir=detailTransferStockPFDAO.EtatAvoirFinancePF(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasin, article, familleArticlePF);
	    		listDetailTransferStockPFGratuite=detailTransferStockPFDAO.EtatGratuiteFinancePF (dateChooserdebut.getDate(), dateChooserfin.getDate(), magasin, article, familleArticlePF);
	    		
	    		listDetailTransferStockPFInitial=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_INITIAL,magasin, null);
	    		
	    		listDetailTransferStockPFAchat=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ACHAT,magasin, null);
	    		listDetailTransferStockPFAchatGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateChooserdebut.getDate(), dateChooserfin.getDate(), article, ETAT_TRANSFER_STOCK_ACHAT,magasin, null);
	
	    		
	    		
	    		
	    		for(int i=0;i<listDetailTransferStockPFAvoir.size();i++)
				{
					Object[] object=listDetailTransferStockPFAvoir.get(i);
					EtatAvoirFinancePF EtatAvoirFinancePF=new EtatAvoirFinancePF();
					
					Articles articleTmp=mapIdArticle.get((int)object[0]);
					SousFamilleArticlePF sousFamilleArticlePFTmp=mapIdSousFamille.get((int)object[1]);
					BigDecimal QuantiteAvoir=(BigDecimal)object[2];
					Double PrixAvoir=(Double)object[3];
					
					EtatAvoirFinancePF.setArticle(articleTmp);
					EtatAvoirFinancePF.setSousFamille(sousFamilleArticlePFTmp);
					EtatAvoirFinancePF.setQuantiteAvoir(QuantiteAvoir);
					EtatAvoirFinancePF.setPrixAvoir(BigDecimal.valueOf(PrixAvoir));
					EtatAvoirFinancePF.setTotalAvoir(EtatAvoirFinancePF.getQuantiteAvoir().multiply(EtatAvoirFinancePF.getPrixAvoir()));
					EtatAvoirFinancePF.setQuantiteGratuit(BigDecimal.ZERO);
					EtatAvoirFinancePF.setPrixGratuit(BigDecimal.ZERO);
					EtatAvoirFinancePF.setTotalGratuit(BigDecimal.ZERO);
					EtatAvoirFinancePF.setQuantitefinance(BigDecimal.ZERO);
					EtatAvoirFinancePF.setPrixInitial(BigDecimal.ZERO);
					EtatAvoirFinancePF.setPrixAchat(BigDecimal.ZERO);
					EtatAvoirFinancePF.setEcart(BigDecimal.ZERO);
					listEtatAvoirFinancePF.add(EtatAvoirFinancePF);
					
				}
	    		
	    		for(int j=0;j<listDetailTransferStockPFGratuite.size() ; j++)
	    		{
	    			
	    			Object[] object=listDetailTransferStockPFGratuite.get(j);
	    			Articles articleTmp=mapIdArticle.get((int)object[0]);
					SousFamilleArticlePF sousFamilleArticlePFTmp=mapIdSousFamille.get((int)object[1]);
	    			
	    			for(int k=0;k<listEtatAvoirFinancePF.size() ; k++)
	    			{
	    				
	    				EtatAvoirFinancePF EtatavoirFinancePF=listEtatAvoirFinancePF.get(k);
	    				
	    				if(EtatavoirFinancePF.getArticle().getId()==articleTmp.getId() && EtatavoirFinancePF.getSousFamille().getId()==sousFamilleArticlePFTmp.getId())
	    				{
	    					
	    					EtatavoirFinancePF.setQuantiteGratuit((BigDecimal)object[2]);
	    					
	    					
	    					
	    					
	    				}
	    				
	    				listEtatAvoirFinancePF.set(k, EtatavoirFinancePF);
	    				
	    			}
					
	    		}
	    		
	    		BigDecimal PrixInitial=new BigDecimal(0);
	    		
	    		// Prix Initial
	    		for(int i=0;i<listDetailTransferStockPFInitial.size();i++)
	    		{
	    			
	    			
	    			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFInitial.get(i);
	    			    		
	    				PrixInitial=detailtransferstockpf.getPrixUnitaire();
	    		
		    			
		    			for(int p=0;p<listEtatAvoirFinancePF.size();p++)
		    	    	{
		    				if(listEtatAvoirFinancePF.get(p).getArticle().getId()== listDetailTransferStockPFInitial.get(i).getArticle().getId() && listEtatAvoirFinancePF.get(p).getSousFamille().getId() == listDetailTransferStockPFInitial.get(i).getSousFamille().getId() )
			    			{
		    					EtatAvoirFinancePF EtatavoirFinancePF=listEtatAvoirFinancePF.get(p);
			    				
			    				EtatavoirFinancePF.setPrixInitial(PrixInitial);
			    				
			    				listEtatAvoirFinancePF.set(p, EtatavoirFinancePF);
			    			
			    				
			    			}
		    	    	}
		    			
	    		}
	    		
	    		/// Prix Achat
	    		BigDecimal montantachat=new BigDecimal(0);
	    		BigDecimal quantiteTotalachat=new BigDecimal(0);
	    		BigDecimal prixmoyenachat=new BigDecimal(0);
	    		
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
		    			
		    			for(int i=0;i<listEtatAvoirFinancePF.size();i++)
		    	    	{
		    				if(listEtatAvoirFinancePF.get(i).getArticle().getId()== listDetailTransferStockPFAchatGroupebyPF.get(j).getArticle().getId() && listEtatAvoirFinancePF.get(i).getSousFamille().getId()== listDetailTransferStockPFAchatGroupebyPF.get(j).getSousFamille().getId() )
			    			{
		    					EtatAvoirFinancePF EtatavoirFinancePF=listEtatAvoirFinancePF.get(i);
			    				
		    					EtatavoirFinancePF.setPrixAchat((prixmoyenachat));
			    				
		    					listEtatAvoirFinancePF.set(i, EtatavoirFinancePF);
			    			
			    				
			    			}
		    	    	}
		    			
		    			
		    			
		    		}
		    		
		    	}
	    		
	    		for(int d=0 ; d<listEtatAvoirFinancePF.size() ; d++)
	    		{
	    			EtatAvoirFinancePF EtatavoirFinancePF=listEtatAvoirFinancePF.get(d);
	    			
	    			
	    			if(EtatavoirFinancePF.getPrixAchat().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
	    			{
	    				EtatavoirFinancePF.setPrixGratuit(EtatavoirFinancePF.getPrixInitial().setScale(6));
	    			}else
	    			{
	    				EtatavoirFinancePF.setPrixGratuit(EtatavoirFinancePF.getPrixAchat().setScale(6));
	    			}
	    			
	    			EtatavoirFinancePF.setTotalGratuit(EtatavoirFinancePF.getQuantiteGratuit().multiply(EtatavoirFinancePF.getPrixGratuit()));
					EtatavoirFinancePF.setQuantitefinance(EtatavoirFinancePF.getQuantiteAvoir().subtract(EtatavoirFinancePF.getQuantiteGratuit()));
					EtatavoirFinancePF.setEcart(EtatavoirFinancePF.getTotalAvoir().subtract(EtatavoirFinancePF.getTotalGratuit()));
	    			
	    			
	    			
	    			
	    			
	    			listEtatAvoirFinancePF.set(d, EtatavoirFinancePF);
	    			
	    		}
		    	
		    	
	    		
	    		
	    		
	    		afficher_tableEtatStock(listEtatAvoirFinancePF);
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    	
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
	          
	           comboArticle = new JComboBox();
	          comboArticle.setBounds(1234, 14, 201, 27);
	          layeredPane.add(comboArticle);
	          
	          JLabel lblFamille = new JLabel("Article  :");
	          lblFamille.setFont(new Font("Tahoma", Font.PLAIN, 11));
	          lblFamille.setBounds(1180, 14, 57, 26);
	          layeredPane.add(lblFamille);
			  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	    JButton button = new JButton("Initialiser");
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		

	     		comboArticle.setSelectedItem("");
	     		
	     		dateChooserdebut.setCalendar(null);
	     		dateChooserfin.setCalendar(null);
	     		txtcodeArticle.setText("");
	     		
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
	    comboArticle.addItem("");
	    
	    JLabel lblCode = new JLabel("Code Article  :");
	    lblCode.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    lblCode.setBounds(971, 14, 77, 26);
	    layeredPane.add(lblCode);
	    
	    txtcodeArticle = new JTextField();
	    txtcodeArticle.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		
	    		

     			if(e.getKeyCode()==e.VK_ENTER)
		      		{
     				
     					
		      			if(!txtcodeArticle.getText().equals(""))
		      			{
		      				
		      				Articles article=mapCodeArticle.get(txtcodeArticle.getText());
				    		
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
	    txtcodeArticle.setColumns(10);
	    txtcodeArticle.setBounds(1058, 14, 112, 26);
	    layeredPane.add(txtcodeArticle);
	  for(int k=0;k<listArticle.size();k++)
	  {
		  Articles Article=listArticle.get(k);
		  comboArticle.addItem(Article.getLiblle());
		  mapArticle.put(Article.getLiblle(), Article);
		  mapCodeArticle.put(Article.getCodeArticle(), Article);
		  mapIdArticle.put(Article.getId(), Article);
		  
	  }
	  
	  for(int k=0;k<listSousFamille.size();k++)
	  {
		  SousFamilleArticlePF sousFamilleArticlePF=listSousFamille.get(k);
		  mapIdSousFamille.put(sousFamilleArticlePF.getId(), sousFamilleArticlePF);
		  
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
						"Code", "Article", "Sous Famille","Quantite Avoir", "prix","Total","Quantite Gratuite", "Prix", "Total","Quantite Finance","Ecart"	
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
		 tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(92);
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
	
	
	
	void afficher_tableEtatStock(List<EtatAvoirFinancePF> listEtatAvoirFinancePF)
	{
		modeleEtatStock =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code", "Article", "Sous Famille", "Quantite Avoir", "prix","Total","Quantite Gratuite", "Prix", "Total","Quantite Finance","Ecart"	
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
		 
		while(i<listEtatAvoirFinancePF.size())
		{	
		EtatAvoirFinancePF EtatAvoirFinancePF=listEtatAvoirFinancePF.get(i);
		
			
				Object []ligne={EtatAvoirFinancePF.getArticle().getCodeArticle() , EtatAvoirFinancePF.getArticle().getLiblle() , EtatAvoirFinancePF.getSousFamille().getLiblle() , EtatAvoirFinancePF.getQuantiteAvoir() , EtatAvoirFinancePF.getPrixAvoir() , EtatAvoirFinancePF.getTotalAvoir() , EtatAvoirFinancePF.getQuantiteGratuit() , EtatAvoirFinancePF.getPrixGratuit() , EtatAvoirFinancePF.getTotalGratuit() , EtatAvoirFinancePF.getQuantitefinance() , EtatAvoirFinancePF.getEcart()};

				modeleEtatStock.addRow(ligne);
			
			
			i++;
		}
}
	}


