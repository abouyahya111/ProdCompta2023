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
import presentation.stockMP.TransfererStockMP;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureAchatDAOImpl;
import dao.daoImplManager.DetailFactureAchatMPDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatMPDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
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
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.Client;
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
import dao.entity.DetailFacturePFParArticle;
import dao.entity.DetailFacturePFParFamille;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatInitialParSousCtaegorieMP;
import dao.entity.EtatPrixMoyen;
import dao.entity.EtatPrixMoyenMP;
import dao.entity.EtatVenteParFamilleArticle;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FactureServiceProduction;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.PrixMoyenStockMP;
import dao.entity.PrixMoyenStockPF;
import dao.entity.Production;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TypeVente;
import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;




































import com.toedter.calendar.DateUtil;
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

import java.awt.SystemColor;
import javax.swing.JCheckBox;


public class MoyenStockPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	
	private DefaultTableModel	 modelefacture;
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	
	private List<EtatPrixMoyenMP> listEtatPrixMoyen =new ArrayList<EtatPrixMoyenMP>();
	private List<MatierePremier> listMatierePremiere =new ArrayList<MatierePremier>();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<Magasin> listMagasinMP =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<StockMP> listStockMP =new ArrayList<StockMP>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
	private List<DetailTransferStockMP> listDetailTransfertStockMP =new ArrayList<DetailTransferStockMP>();
	private List<PrixMoyenStockMP> listPrixMoyenStockMP =new ArrayList<PrixMoyenStockMP>();
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	private List<CategorieMp> listCategorieMP =new ArrayList<CategorieMp>();
	private List<SubCategorieMp> listSousCategorieMP =new ArrayList<SubCategorieMp>();
	private List<DetailFacturePFParFamille> listDetailFactureParFamille =new ArrayList<DetailFacturePFParFamille>();
	private List<DetailFacturePFParArticle> listDetailFactureParArticle =new ArrayList<DetailFacturePFParArticle>();
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, Magasin> mapMagasinMP= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, ClientPF> mapClientPF= new HashMap<>();
	private Map< String, ClientPF> mapClientPFparCode= new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Client> mapFournisseur= new HashMap<>();
	private Map< String, CategorieMp> mapcategorie= new HashMap<>();
	private Map< String, SubCategorieMp> mapsouscategorie= new HashMap<>();
	private Map< String, Boolean> maptransfereblfacture = new HashMap<>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	private List<CoutMP> listCoutMPTmp =new ArrayList<CoutMP>();
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgSelectAll;
	private ImageIcon imgDeselectAll;
	private ImageIcon imgExcel;
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private  JButton btninitialiser = new JButton();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private MatierePremiereDAO MatierPremierDAO;
	private FactureAchatMPDAO factureAchatMPDAO;
	private FacturePF facturePF;
private DetailFactureAchatMPDAO detailFactureAchatMPdao;
private ClientPFDAO clientpfdao;
private ClientDAO fournisseurdao;
private StockPFDAO stockpfDAO;
private   JDateChooser audateChooser = new JDateChooser();
	private  JComboBox comboclient = new JComboBox();
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	 private JButton btnSupprimer = new JButton();
	 private  JComboBox comboFournisseur = new JComboBox();
	private JRadioButton rdbtnDateFacture;
	private JDateChooser dudateChooser;
	private StockMPDAO stockMPDAO;
	private CompteClientDAO compteclientdao;
	String titre="";
	 JComboBox combomagasin = new JComboBox();
	   JComboBox comboparfamille = new JComboBox();
	   private JComboBox combofamille;
	 private CategorieMpDAO categorieDAo;
	 private SubCategorieMPDAO souscategorieDAO;
	 JRadioButton radiobl = new JRadioButton("BL");
	 JRadioButton radiofacture = new JRadioButton("Facture");
	 ButtonGroup group = new ButtonGroup();
	 JCheckBox prixmoyen = new JCheckBox("Calcule prix Moyen");
	
	 private JComboBox combosousfamille;
	 DetailTransferMPDAO detailTransferMPDAO;
		private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
		private List<StockPF> listArticleStockPF =new ArrayList<StockPF>();
		private List<Articles> listArticle =new ArrayList<Articles>();
		
		private List<DetailTransferProduitFini> listDetailFactureTmp =new ArrayList<DetailTransferProduitFini>();
		private List<PrixMoyenStockPF> listEtatprixMoyenService =new ArrayList<PrixMoyenStockPF>();
		private List<PrixMoyenStockPF> listEtatprixMoyenServiceAfficher =new ArrayList<PrixMoyenStockPF>();
		private  DetailTransferProduitFiniDAO detailTransfertPFdao;
		private List<DetailTransferProduitFini> listDetailFacture =new ArrayList<DetailTransferProduitFini>();
		private DetailFactureServiceProductionDAO detailFactureServiceProductionDAO;
		private FactureServiceProductionDAO factureServiceProductionDAO;
		 JComboBox combomagasinMP = new JComboBox();
		 private SousFamilleEnVracDAO sousFamilleEnVracDAO;
		 private FamilleArticlesPFDAO familleArticlesPFDAO;
		 ProductionDAO productionDAO;
		 private TransferStockMPDAO transferStockMPDAO;
		 private List<EtatInitialParSousCtaegorieMP> listEtatInitialMP =new ArrayList<EtatInitialParSousCtaegorieMP>();
			private List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
			private List<DetailTransferStockMP> listDetailTransferStockMPTmp =new ArrayList<DetailTransferStockMP>();
			private List <Object[]> listeObjectPrixMoyenAchat=new ArrayList<Object[]>();
			private List<EtatVenteParFamilleArticle> listEtatPrixMoyenserviceParSousFamille =new ArrayList<EtatVenteParFamilleArticle>();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public MoyenStockPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1660, 1058);
      
	
        try{ 
        	
        	
        	 imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
             
             
             
            utilisateur=AuthentificationView.utilisateur;
            clientpfdao=new ClientPFDAOImpl();
         	factureAchatMPDAO=new FactureAchatMPDAOImpl();
       depotdao=new DepotDAOImpl();
       detailFactureAchatMPdao=new DetailFactureAchatMPDAOImpl();
       categorieDAo=new CategorieMpDAOImpl();
       souscategorieDAO=new SubCategorieMPAOImpl();
       stockMPDAO=ProdLauncher.stockMPDAO;
       detailTransferMPDAO=new DetailTransferMPDAOImpl();
       MatierPremierDAO=new MatierePremierDAOImpl();
       sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
       stockpfDAO=new StockPFDAOImpl();
       detailTransfertPFdao=new DetailTransferProduitFiniDAOImpl();
       detailFactureServiceProductionDAO=new DetailFactureServiceProductionDAOImpl();
	   factureServiceProductionDAO=new FactureServiceProductionDAOImpl();
	   sousFamilleEnVracDAO=new SousFamilleEnVracDAOImpl();
	   familleArticlesPFDAO=new FamilleArticlesPFDAOImpl();
	   productionDAO=new ProductionDAOImpl();
	   transferStockMPDAO=new TransferStockMPDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
		      
		    
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
					Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
					if(magasin!=null )
					{
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						String datedebutFacture=dateFormat.format(dudateChooser.getDate());
				  		  String dateFinFacture=dateFormat.format(audateChooser.getDate());
				  		  
			
				  	
				  		  
				  		  
				  		  
						
						if(listEtatprixMoyenServiceAfficher.size()!=0)
				{
						
							
					
					Map parameters = new HashMap();
				
					
					parameters.put("magasin", magasin.getLibelle());
					parameters.put("titre", " MOYEN STOCK PF");
					parameters.put("datedebut",datedebutFacture);
					parameters.put("dateFin", dateFinFacture);
					JasperUtils.imprimerMoyenStockPF(listEtatprixMoyenServiceAfficher,parameters);
				
					
					
					
				}
					
			}}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(666, 640, 112, 24);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("                           Moyen Stock PF");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(381, 11, 880, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 195, 1591, 427);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
}
		});
		
		
		scrollPane_1.setViewportView(table);
		table.setColumnControlVisible(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Code MP","MP","Famille","Sous Famille", "Quantite MP","Prix Moyen MP","Montant HT MP", "Quantite Service","Prix Moyen Service","Montant HT Service", "Quantite Finale","Prix Moyen","Montant HT Finale"

			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(136);
		table.getColumnModel().getColumn(7).setPreferredWidth(136);
	
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(51, 204, 255));
		table.setRowHeightEnabled(true);
		table.setRowHeight(20);
		table.setGridColor(Color.BLUE);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setAutoCreateRowSorter(true);
		 //Group the radio buttons.
	    
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    		listPrixMoyenStockMP.clear();
	    		listEtatprixMoyenService.clear();
	    		listDetailFactureTmp.clear();
	    		listDetailFacture.clear();
	    		listDetailTransfertStockMP.clear();
	    	  	boolean existe=false;
		    		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		    		Magasin magasinMP=mapMagasinMP.get(combomagasinMP.getSelectedItem());
		    		String TousSubCategorie="";
		    		String Touscategorie="";
		    		String TousArticle="";
		    		String requete="";
		    		String requeteMP="";
		    		CategorieMp categorieMP=null;
	    			SubCategorieMp souscategorieMP=null;
	    			MatierePremier mp=null;
	    			
		    		if(magasin!=null && magasinMP!=null)
		    		{
		    			
		    			FamilleArticlePF famille=null;
		    			SousFamilleArticlePF sousFamille=null;
		    			
		    			requete="and  d.magasinDestination.id = '"+magasin.getId()+"' ";
		    			
		    			
		    			
		    			if(dudateChooser.getDate()!=null && audateChooser.getDate()!=null)
			    		{
		    				
			    		if(	DateUtils.nbJoursEntre(dudateChooser.getDate(), audateChooser.getDate())>=0)
			    		{
			    			
			    			String dateDu=formatter.format(dudateChooser.getDate());
			    			String dateAu=formatter.format(audateChooser.getDate());
			    			
			    			
			    			requete=requete+"and  d.transferStockPF.dateTransfer between '"+dateDu +"' and '"+dateAu+"' ";
			    			
			    			
			    			
			    			
			    			if(combofamille.getSelectedIndex()!=-1)
			    			{
			    				if(!combofamille.getSelectedItem().equals(TOUS))
				    			{
			    					famille=mapfamille.get(combofamille.getSelectedItem());
				    			}
			    				
			    			}
			    			
			    			
			    			if(combosousfamille.getSelectedIndex()!=-1)
			    			{
			    				if(!combosousfamille.getSelectedItem().equals(TOUS))
				    			{
				    				 sousFamille=mapsousfamille.get(combosousfamille.getSelectedItem());
				    			}
			    			}
			    			
			    			if(famille!=null)
			    			{
			    				requete=requete+"and  d.sousFamille.famileArticlePF.id = '"+famille.getId()+"' ";
			    				
			    			}
			    			
			    			if(sousFamille!=null)
			    			{
			    				requete=requete+"and  d.sousFamille.id = '"+sousFamille.getId()+"' ";
			    				
			    			}
			    			
			    		
			    			
			    			Articles articles=null;
			    		
			    			
			    			if(articles!=null)
			    			{
			    				
			    				requete=requete+"and  d.article.id = '"+articles.getId()+"' ";	
			    				
			    				
			    			}
			    			
			    			boolean trouve=false;
			    			BigDecimal quantite=BigDecimal.ZERO;
  		   					BigDecimal quantitefoiprix=BigDecimal.ZERO;

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  					
  							listDetailFactureTmp=detailTransfertPFdao.listeDetailTransfertPFService(requete);
  						
  							if(listDetailFactureTmp.size()!=0)
  							{
  								
  								
	  							 for(int k=0;k<listDetailFactureTmp.size();k++)
	  							 {
	  								 DetailTransferProduitFini detailTransferProduitFini=listDetailFactureTmp.get(k);
	  								 trouve=false;
	  								 
	  								MatierePremier matierePremier=null;
	  								TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(listDetailFactureTmp.get(k).getTransferStockPF().getCodeTransfer());
	  								Production production=productionDAO.findByNumOF (listDetailFactureTmp.get(k).getTransferStockPF().getCodeTransfer(), transferStockMP.getDepot().getCode());
	  								listCoutMP=productionDAO.listeCoutMP(production.getId());
	  								for(int t=0;t<listCoutMP.size();t++)
	  								{
	  									if(listCoutMP.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		  		                         {
	  										matierePremier=listCoutMP.get(t).getMatierePremier();
		  		                         }
	  									
	  									
	  								}
	  								
	  								 if(listDetailFacture.size()!=0)
	  								 {
	  									
	  									 for(int i=0;i<listDetailFacture.size();i++)
	  									 {
	  										 
	  										MatierePremier matierePremierTmp=null;
			  								TransferStockMP transferStockMPTmp=transferStockMPDAO.findTransferByCode(listDetailFacture.get(i).getTransferStockPF().getCodeTransfer());
			  								Production productionTmp=productionDAO.findByNumOF(listDetailFacture.get(i).getTransferStockPF().getCodeTransfer(), transferStockMPTmp.getDepot().getCode());
			  								listCoutMPTmp=productionDAO.listeCoutMP(productionTmp.getId());
	  										 
			  								for(int t=0;t<listCoutMPTmp.size();t++)
			  								{
			  									if(listCoutMPTmp.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				  		                         {
			  										matierePremierTmp=listCoutMPTmp.get(t).getMatierePremier();
				  		                         }
			  									
			  									
			  								}
			  								
			  								
	  										 if(listDetailFacture.get(i).getSousFamille().getId()==detailTransferProduitFini.getSousFamille().getId())
	  										 {
	  											 
	  											 if(matierePremierTmp!=null && matierePremier!=null)
	  											 {
	  												 
	  												if(matierePremierTmp.getId()==matierePremier.getId())
				  									{
		  												
		  												 trouve=true;
		  												
				  									}
	  											 }
	  											
	  											 
	  											
	  											 
	  											 
	  											 
	  											 
	  										 }
	  										 
	  									 }
	  									 
	  								 }else
	  								 {
	  									listDetailFacture.add(detailTransferProduitFini);
	  									trouve=true;
	  								 }
	  								 
	  								 if(trouve==false)
	  								 {
	  									listDetailFacture.add(detailTransferProduitFini);
	  								 }
	  							 }
	  							
	  							
	  							CalculerPrixMoyenservice();
	  						
	  							
	  							if(listDetailFacture.size()!=0)
	  							{
	  								for(int i=0;i<listDetailFacture.size();i++)
		  							{
		  								SousFamilleArticlePF sousfamilleArticle =listDetailFacture.get(i).getSousFamille();
		  								Articles article=listDetailFacture.get(i).getArticle();
		  								quantite=BigDecimal.ZERO;
		  								quantitefoiprix=BigDecimal.ZERO;
		  								MatierePremier matierePremier=null;
		  								TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(listDetailFacture.get(i).getTransferStockPF().getCodeTransfer());
		  								Production production=productionDAO.findByNumOF (listDetailFacture.get(i).getTransferStockPF().getCodeTransfer(), transferStockMP.getDepot().getCode());
		  								listCoutMP=productionDAO.listeCoutMP(production.getId());
		  								for(int t=0;t<listCoutMP.size();t++)
		  								{
		  									if(listCoutMP.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			  		                         {
		  										matierePremier=listCoutMP.get(t).getMatierePremier();
			  		                         }
		  									
		  									
		  								}
		  								
		  								
		  								
		  								
		  								
		  								
		  								
		  								
		  							for(int j=0;j<listDetailFactureTmp.size();j++)	
		  							{
		  								
		  								MatierePremier matierePremierTmp=null;
		  								TransferStockMP transferStockMPTmp=transferStockMPDAO.findTransferByCode(listDetailFactureTmp.get(j).getTransferStockPF().getCodeTransfer());
		  								Production productionTmp=productionDAO.findByNumOF(listDetailFactureTmp.get(j).getTransferStockPF().getCodeTransfer(), transferStockMPTmp.getDepot().getCode());
		  								listCoutMPTmp=productionDAO.listeCoutMP(productionTmp.getId());
		  								
		  								for(int t=0;t<listCoutMPTmp.size();t++)
		  								{
		  									if(listCoutMPTmp.get(t).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			  		                         {
		  										matierePremierTmp=listCoutMPTmp.get(t).getMatierePremier();
			  		                         }
		  									
		  									
		  								}
		  								
		  								
		  								if(listDetailFactureTmp.get(j).getSousFamille().getId()== sousfamilleArticle.getId())
		  								{
		  									 if(matierePremierTmp!=null && matierePremier!=null)
  											 {
		  										 
		  										if(matierePremierTmp.getId()==matierePremier.getId())
			  									{
			  										
			  										FactureServiceProduction factureServiceProduction=factureServiceProductionDAO.findByNumOF(listDetailFactureTmp.get(j).getTransferStockPF().getCodeTransfer());
				  									DetailFactureServiceProduction detailFactureServiceProduction= detailFactureServiceProductionDAO.DetailFactureServiceProductionByFactureByArticle(factureServiceProduction.getId(), listDetailFactureTmp.get(j).getArticle().getLiblle());
				  									
				  									quantitefoiprix=quantitefoiprix.add(detailFactureServiceProduction.getQuantite().multiply(detailFactureServiceProduction.getPrix()));
				  									quantite=quantite.add(detailFactureServiceProduction.getQuantite());
				  									
				  									
			  									}
  											 }
		  								
		  									
		  								
		  									
		  								}
		  								
		  								
		  							}
		  							
		  							PrixMoyenStockPF prixMoyenStockPF=new PrixMoyenStockPF();
		  							
		  							prixMoyenStockPF.setSousFamille(sousfamilleArticle);		  							
		  							prixMoyenStockPF.setQuantiteService(quantite);
		  							
		  							boolean trouver=false;
		  							for(int d=0;d<listEtatPrixMoyenserviceParSousFamille.size();d++)
		  							{
		  								
		  								if(listEtatPrixMoyenserviceParSousFamille.get(d).getSousFamilleArticlePF().getId()==prixMoyenStockPF.getSousFamille().getId())
		  								{
		  									
		  									prixMoyenStockPF.setPrixMoyenService(listEtatPrixMoyenserviceParSousFamille.get(d).getPrixMoyen());
		  									trouver=true;
		  									
		  								}
		  								
		  								
		  								
		  								
		  							}
		  							
		  							
		  							if(trouver==false)
		  							{
		  							
		  								prixMoyenStockPF.setPrixMoyenService(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
		  							}
		  							
		  						
		  							
		  							
		  							
		  							prixMoyenStockPF.setMontantHTService(prixMoyenStockPF.getPrixMoyenService().multiply(prixMoyenStockPF.getQuantiteService()));
		  							
		  							
		  							prixMoyenStockPF.setMp(matierePremier);
		  							prixMoyenStockPF.setQuantiteMP(BigDecimal.ZERO);
		  							prixMoyenStockPF.setPrixMoyenMP(BigDecimal.ZERO);
		  							prixMoyenStockPF.setMontantHTMP(BigDecimal.ZERO);
		  							
		  							prixMoyenStockPF.setQuantiteFinale(prixMoyenStockPF.getQuantiteService());
		  							prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenService());
		  							prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getMontantHTService());
		  							listEtatprixMoyenService.add(prixMoyenStockPF);
		  							
		  								
		  							}
	  								
	  								
	  								
					 ////////////////////////////////////////////////////////////////////////// MP /////////////////////////////////////////////////////////////////////////////////
					  				
	  								
	  								requeteMP=" d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_INITIAL+"' ";
	  				    			
	  								requeteMP=requeteMP+"and  d.magasinDestination.id = '"+magasinMP.getId()+"' ";
	  								
	  								requeteMP=requeteMP+"and  d.transferStockMP.dateTransfer between '"+dateDu +"' and '"+dateAu+"' ";

	  								
	  						///////////////////////////////////////////////////////////////////////// MP ///////////////////////////////////////////////////////////////		
	  								
	  					  	       	listDetailTransfertStockMP=detailTransferMPDAO.listeDetailTransfertMP(requeteMP);
	  					  			 for(int i=0;i<listDetailTransfertStockMP.size();i++)
	  					  			 {
	  					  				 
	  					  				DetailTransferStockMP detailTransferStockMP= listDetailTransfertStockMP.get(i);
	  					  				
	  					  			PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
	  					  			prixMoyenStockMP.setMp(detailTransferStockMP.getMatierePremier());
	  					  			prixMoyenStockMP.setPrixInitial(detailTransferStockMP.getPrixUnitaire());
	  					  			prixMoyenStockMP.setQuantiteInitial(detailTransferStockMP.getQuantite());
	  					  			prixMoyenStockMP.setMontantInitial(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
	  					  			prixMoyenStockMP.setQuantiteAchat(BigDecimal.ZERO);	
	  					  			prixMoyenStockMP.setPrixAchat(BigDecimal.ZERO);
	  					  			prixMoyenStockMP.setMontantAchat(BigDecimal.ZERO);
	  					  			prixMoyenStockMP.setQuantiteFinale(detailTransferStockMP.getQuantite());
	  					  			prixMoyenStockMP.setPrixMoyen(detailTransferStockMP.getPrixUnitaire());
	  					  			prixMoyenStockMP.setMontantHTFinale(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
	  					  			listPrixMoyenStockMP.add(prixMoyenStockMP);
	  					  				 
	  					  				 
	  					  			 }
	  					  				  
	  					  				
	  					  				listeObject=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dudateChooser.getDate(), audateChooser.getDate(),magasinMP,souscategorieMP,mp, TousSubCategorie,TousArticle, categorieMP , Touscategorie);
	  					  				
	  					  				
	  					  				
	  					  				for(int j=0;j<listeObject.size();j++)
	  					  				{
	  					  					existe=false;
	  					  					 Object[] object=listeObject.get(j);
	  					  					 
	  					  					 for(int k=0;k<listPrixMoyenStockMP.size();k++)
	  					  					 {
	  					  						 
	  					  						PrixMoyenStockMP prixMoyenStockMP=listPrixMoyenStockMP.get(k); 
	  					  						 
	  					  						 
	  					  						 
	  					  						if(object[0].toString().equals(prixMoyenStockMP.getMp().getCode()))
	  					  						{
	  					  							
	  					  							existe=true;
	  					  							
	  					  							prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
	  									  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
	  									  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
	  									  			prixMoyenStockMP.setQuantiteFinale(new BigDecimal(object[4].toString()).add(prixMoyenStockMP.getQuantiteInitial()));
	  									  			prixMoyenStockMP.setPrixMoyen((prixMoyenStockMP.getMontantInitial().add(prixMoyenStockMP.getMontantAchat())).divide(prixMoyenStockMP.getQuantiteAchat().add(prixMoyenStockMP.getQuantiteInitial()), 6, RoundingMode.HALF_UP));
	  									  			prixMoyenStockMP.setMontantHTFinale(prixMoyenStockMP.getQuantiteFinale().multiply(prixMoyenStockMP.getPrixMoyen()));
	  									  			listPrixMoyenStockMP.set(k, prixMoyenStockMP);
	  					  							
	  					  						}
	  					  						
	  					  						 
	  					  					 }
	  					  					
	  					  					 if(existe==false)
	  					  					 {
	  					  						
	  					  						MatierePremier matierePremier=MatierPremierDAO.findByCode(object[0].toString()) ;
	  					  						 
	  					  						PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
	  								  			prixMoyenStockMP.setMp(matierePremier);
	  								  			prixMoyenStockMP.setPrixInitial(BigDecimal.ZERO);
	  								  			prixMoyenStockMP.setQuantiteInitial(BigDecimal.ZERO);
	  								  			prixMoyenStockMP.setMontantInitial(BigDecimal.ZERO);
	  								  			prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
	  								  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
	  								  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
	  								  			prixMoyenStockMP.setQuantiteFinale(new BigDecimal(object[4].toString()));
	  								  			prixMoyenStockMP.setPrixMoyen(new BigDecimal(object[5].toString()));
	  								  			prixMoyenStockMP.setMontantHTFinale(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
	  								  			listPrixMoyenStockMP.add(prixMoyenStockMP); 
	  					  						 
	  					  						 
	  					  						 
	  					  						 
	  					  						 
	  					  						 
	  					  					 }
	  					  					 
	  					  					 
	  					  					 
	  					  					 
	  					  					 
	  					  					
	  					  				}	
	  								
	  								
	  						
	  			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  								
	  					  				boolean trouvesousfamille=false;
	  					  			boolean trouvemp=false;
	  					  				
	  					  			for(int i=0;i<listPrixMoyenStockMP.size();i++)	
	  					  			{
	  					  			trouvesousfamille=false;
	  					  		trouvemp=false;
	  					  			PrixMoyenStockMP prixMoyenStockMP=listPrixMoyenStockMP.get(i);	
	  					  				
	  					  			SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(prixMoyenStockMP.getMp());
	  					  			
	  					  			for(int j=0;j<listEtatprixMoyenService.size();j++)	
	  					  			{
	  					  				
	  					  			PrixMoyenStockPF prixMoyenStockPF=listEtatprixMoyenService.get(j);
	  					  				if(sousFamilleEnVrac!=null)
	  					  				{
	  					  					if(prixMoyenStockPF.getSousFamille()!=null)
	  					  					{
	  					  						
	  					  						
	  					  						if(sousFamilleEnVrac.getSousfamile().getId()==prixMoyenStockPF.getSousFamille().getId())
		  					  					{
		  					  						
		  					  						
		  					  					if(prixMoyenStockPF.getMp()!=null)
		  					  					{
		  					  						
		  					  						if(prixMoyenStockPF.getMp().getId()==sousFamilleEnVrac.getMatierePremier().getId())
		  					  						{
		  					  							
		  					  							
		  					  						prixMoyenStockPF.setMp(prixMoyenStockMP.getMp());
			  		  					  			prixMoyenStockPF.setQuantiteMP(prixMoyenStockMP.getQuantiteFinale());
			  		  					  		prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
			  		  					  	prixMoyenStockPF.setMontantHTMP(prixMoyenStockMP.getMontantHTFinale());	
			  		  					  prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenFinale().add(prixMoyenStockPF.getPrixMoyenMP()));
			  		  					prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getQuantiteFinale().multiply(prixMoyenStockPF.getPrixMoyenFinale()));
			  		  					listEtatprixMoyenService.set(j, prixMoyenStockPF);
		  					  							
			  		  				trouvesousfamille=true;
				  						
				  						
					  					trouvemp=true;	
		  					  							
		  					  							
		  					  							
		  					  							
		  					  						}
		  					  						
		  					  						
		  					  						
		  					  						
		  					  					}else
		  					  					{
		  					  						
		  					  					
		  					  						
		  					  					prixMoyenStockPF.setMp(prixMoyenStockMP.getMp());
		  		  					  			prixMoyenStockPF.setQuantiteMP(prixMoyenStockMP.getQuantiteFinale());
		  		  					  		prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
		  		  					  	prixMoyenStockPF.setMontantHTMP(prixMoyenStockMP.getMontantHTFinale());	
		  		  					  prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenFinale().add(prixMoyenStockPF.getPrixMoyenMP()));
		  		  					prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getQuantiteFinale().multiply(prixMoyenStockPF.getPrixMoyenFinale()));
		  		  					listEtatprixMoyenService.set(j, prixMoyenStockPF);
		  					  						
		  		  				trouvesousfamille=true;
			  						
			  						
				  					trouvemp=true;
		  					  					}
		  					  					
		  		  					  			
		  					  							
		  					  						
		  					  						
		  					  						
		  					  					
		  			  							
		  					  						
		  					  			
		  					  		
		  					  					}
	  					  						
	  					  						
	  					  						
	  					  						
	  					  						
	  					  							
	  					  						
	  					  			
	  					  					
	  					  					
	  					  					
	  					  					
	  					  					}
	  					  			
	  					  					
	  					  					
	  					  				}
	  					  				
	  					  				
	  					  				
	  					  				
	  					  				
	  					  				
	  					  				
	  					  				
	  					  			}
	  					  			
	  					  			if(trouvesousfamille==false && trouvemp==false)
	  					  			{
	  					  				
	  					  				
	  					  			PrixMoyenStockPF prixMoyenStockPF=new PrixMoyenStockPF();
		  							
	  					  		SousFamilleEnVrac sousFamilleEnVracTmp=sousFamilleEnVracDAO.findByMP(prixMoyenStockMP.getMp());
	  					  			if(sousFamilleEnVracTmp!=null)
	  					  			{
	  					  			prixMoyenStockPF.setSousFamille(sousFamilleEnVracTmp.getSousfamile());	
	  					  			}else
	  					  			{
	  					  			prixMoyenStockPF.setSousFamille(null);	
	  					  			}
		  								
		  							
		  								  							
		  							prixMoyenStockPF.setQuantiteService(BigDecimal.ZERO);
		  							prixMoyenStockPF.setPrixMoyenService(BigDecimal.ZERO);
		  							prixMoyenStockPF.setMontantHTService(BigDecimal.ZERO);
		  									  							
		  							prixMoyenStockPF.setMp(prixMoyenStockMP.getMp());
		  							prixMoyenStockPF.setQuantiteMP(prixMoyenStockMP.getQuantiteFinale());
		  							prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
		  							prixMoyenStockPF.setMontantHTMP(prixMoyenStockMP.getMontantHTFinale());
		  							
		  							prixMoyenStockPF.setQuantiteFinale(prixMoyenStockPF.getQuantiteMP());
		  							prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenMP());
		  							prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getMontantHTMP());
		  							listEtatprixMoyenService.add(prixMoyenStockPF);
	  					  				
	  					  			
	  					  			}
	  					  			
	  					  			}
	  					  			
	  					  				
	  				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
	  								
	  							}else
	  							{
	  								JOptionPane.showMessageDialog(null, "Accun Vente � la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
	  								listEtatprixMoyenService.clear();
	  							//	afficher_tableEtatVenteArticle(listEtatprixMoyenService);
	  								return;
	  							}
  								
  							}
  							
  							
  										  
				  			  
				  			  
				  		 
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  			  
				  		
				  		  
			    			
			    		}else
			    		{
			    			JOptionPane.showMessageDialog(null, "La date de d�but doit etre sup�rieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			    			return;
			    		}
			    		
			    		}
		    			
		    			listEtatprixMoyenServiceAfficher.clear();
		    			
		    			for(int t=0;t<listEtatprixMoyenService.size();t++)
		    			{
		    				
		    				PrixMoyenStockPF prixMoyenStockPF=listEtatprixMoyenService.get(t);
		    				if(famille!=null)
		    				{
		    					
		    				if(sousFamille!=null)	
		    				{
		    				
		    					if(prixMoyenStockPF.getSousFamille()!=null)
		    					{
		    						
		    						if(prixMoyenStockPF.getSousFamille().getId()==sousFamille.getId())
		    						{
		    							listEtatprixMoyenServiceAfficher.add(prixMoyenStockPF);
		    							
		    							
		    						}
		    						
		    						
		    					}
		    					
		    					
		    					
		    				}else
		    				{
		    					
		    					if(prixMoyenStockPF.getSousFamille()!=null)
		    					{
		    						
		    						if(prixMoyenStockPF.getSousFamille().getFamileArticlePF().getId()==famille.getId())
		    						{
		    							listEtatprixMoyenServiceAfficher.add(prixMoyenStockPF);
		    							
		    							
		    						}
		    						
		    						
		    					}
		    					
		    					
		    					
		    				}
		    					
		    					
		    					
		    				}else
		    				{
		    					
		    					listEtatprixMoyenServiceAfficher.add(prixMoyenStockPF);
		    					
		    				}
		    				
		    				
		    				
		    			}
		    			
		    			
		    			
		    			
		    			CalculerPrixMoyenMP();
		    			
		    			for(int t=0;t<listPrixMoyenStockMP.size();t++)
		    			{
		    				
		    				
		    				PrixMoyenStockMP prixMoyenStockMP=listPrixMoyenStockMP.get(t);
		    				if(prixMoyenStockMP.getMp().getCode().equals("MP_37"))
    						{
    							
    						System.out.println("yes");	
    							
    						}
		    				
		    				for(int j=0;j<listEtatprixMoyenServiceAfficher.size();j++)
		    				{
		    					
		    					PrixMoyenStockPF prixMoyenStockPF=listEtatprixMoyenServiceAfficher.get(j);
		    					
		    					
 		    					if(prixMoyenStockMP.getSousfamille()!=null)
		    					{
		    						System.out.println(prixMoyenStockMP.getMp().getCode());	
                      		    
		    						
		    						
		    						if(prixMoyenStockPF.getSousFamille()!=null)
		    						{
		    							
		    							if(prixMoyenStockPF.getSousFamille().getLiblle().equals(prixMoyenStockMP.getSousfamille()))
		    							{
		    								
		    								prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
		    								prixMoyenStockPF.setMontantHTMP(prixMoyenStockPF.getQuantiteMP().multiply(prixMoyenStockMP.getPrixMoyen()));
		    								prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenMP().add(prixMoyenStockPF.getPrixMoyenService()));
		    								prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getPrixMoyenFinale().multiply(prixMoyenStockPF.getQuantiteFinale()));
		    								listEtatprixMoyenServiceAfficher.set(j, prixMoyenStockPF);
		    							}
		    							
		    							
		    							
		    						}else
		    						{
		    							
		    							if(prixMoyenStockPF.getMp().getId()==prixMoyenStockMP.getMp().getId())
		    							{
		    								
		    								prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
		    								prixMoyenStockPF.setMontantHTMP(prixMoyenStockPF.getQuantiteMP().multiply(prixMoyenStockMP.getPrixMoyen()));
		    								prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenMP().add(prixMoyenStockPF.getPrixMoyenService()));
		    								prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getPrixMoyenFinale().multiply(prixMoyenStockPF.getQuantiteFinale()));
		    								listEtatprixMoyenServiceAfficher.set(j, prixMoyenStockPF);
		    								
		    								
		    							}
		    							
		    							
		    							
		    							
		    							
		    							
		    						}
		    						
		    						
		    						
		    						
		    						
		    					}else
		    					{
		    						
		    						if(prixMoyenStockPF.getSousFamille()==null)
		    						{
		    						
		    							if(prixMoyenStockPF.getMp().getId()==prixMoyenStockMP.getMp().getId())
		    							{
		    								
		    								
		    								prixMoyenStockPF.setPrixMoyenMP(prixMoyenStockMP.getPrixMoyen());
		    								prixMoyenStockPF.setMontantHTMP(prixMoyenStockPF.getQuantiteMP().multiply(prixMoyenStockMP.getPrixMoyen()));
		    								prixMoyenStockPF.setPrixMoyenFinale(prixMoyenStockPF.getPrixMoyenMP().add(prixMoyenStockPF.getPrixMoyenService()));
		    								prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getPrixMoyenFinale().multiply(prixMoyenStockPF.getQuantiteFinale()));
		    								listEtatprixMoyenServiceAfficher.set(j, prixMoyenStockPF);
		    								
		    								
		    							}
		    							
		    							
		    							
		    						}
		    						
		    						
		    						
		    						
		    					}
		    					
		    					
		    					
		    					
		    					
		    				}
		    				
		    				
		    				
		    				
		    				
		    				
		    			}
		    			
		    			
		    			
		    			
		    			for(int t=0;t<listEtatprixMoyenServiceAfficher.size();t++)
		    			{
		    				
		    				PrixMoyenStockPF prixMoyenStockPF=listEtatprixMoyenServiceAfficher.get(t);
		    				
		    				if(prixMoyenStockPF.getQuantiteService().compareTo(BigDecimal.ZERO)==0)
		    				{
		    					
		    					prixMoyenStockPF.setQuantiteFinale(BigDecimal.ZERO);
		    					prixMoyenStockPF.setMontantHTFinale(prixMoyenStockPF.getQuantiteFinale().multiply(prixMoyenStockPF.getPrixMoyenFinale()));
		    					
		    					listEtatprixMoyenServiceAfficher.set(t, prixMoyenStockPF);
		    					
		    					
		    				}
		    				
		    				
		    				
		    			}
		    			
		    			
		    			
		    			
		    			
		    			afficher_tableEtatPrixMoyen(listEtatprixMoyenServiceAfficher);	
		    			
		    			
		    			
		    		}else
		    		{
		    			JOptionPane.showMessageDialog(null, "Veuillez selectionner les magasin PF et MP SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		    			return;
		    		}
		    
		    		
	    	
	  
	    		
	    		
	    		
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(526, 160, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1640, 92);
	    add(layeredPane_2);
	    
	    JLabel lblDateFacture = new JLabel("Du :");
	    lblDateFacture.setBounds(374, 16, 40, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     dudateChooser = new JDateChooser();
	     dudateChooser.setBounds(409, 14, 166, 26);
	     layeredPane_2.add(dudateChooser);
	     dudateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     		


	     		
	     	}
	     });
	     dudateChooser.addKeyListener(new KeyAdapter() {
	     	@Override
	     	public void keyPressed(KeyEvent e) {
	     		
	     		
	     		
	     	}
	     });
	     dudateChooser.setLocale(Locale.FRANCE);
	     dudateChooser.setDateFormatString("dd/MM/yyyy");
	     
	     JLabel lblAu = new JLabel("Au :");
	     lblAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblAu.setBounds(585, 16, 40, 24);
	     layeredPane_2.add(lblAu);
	     
	      audateChooser = new JDateChooser();
	     audateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     	
	     	}
	     });
	     audateChooser.setLocale(Locale.FRANCE);
	     audateChooser.setDateFormatString("dd/MM/yyyy");
	     audateChooser.setBounds(620, 14, 166, 26);
	     layeredPane_2.add(audateChooser);
	 
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		initialiser();
	     		

	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(643, 161, 106, 23);
	     add(button);
	   
	     
	     if(utilisateur.getLogin().equals("admin"))
	 		  {

	 		  listClientPF=clientpfdao.findAll();
	 		  
	 		  	 		  
	 		  }else
	 		  {
	 			 
	 			  
	 			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	 			  if(depot!=null)
	 			  {
	 				 listClientPF=clientpfdao.findListClientByCodeDepot(depot.getCode());
	 					
	 		     	
	 			  }
	 		  }
	     
	     JLabel lblCategorie = new JLabel("Famille :");
	     lblCategorie.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     lblCategorie.setBounds(796, 14, 108, 24);
	     layeredPane_2.add(lblCategorie);
	     combomagasin = new JComboBox();
	      combomagasin.setBounds(191, 17, 173, 24);
	      layeredPane_2.add(combomagasin);
	      combomagasin.setSelectedIndex(-1);
	      combomagasin.addItem("");
	      
	      combofamille = new JComboBox();
	      combofamille.addItemListener(new ItemListener() {
	      	public void itemStateChanged(ItemEvent e) {


	     		listArticle.clear();
	     		if(combomagasin.getSelectedIndex()!=-1)
	     		{
	     			if(!combomagasin.getSelectedItem().equals(""))
		     		{
		     			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			     		if(magasin!=null )
			     		{
			     			
			     			
			     			
			     			if(combofamille.getSelectedIndex()!=-1  )
			     			{
			     			
			     				combosousfamille.removeAllItems();
			     			
			     				if(!combofamille.getSelectedItem().equals(""))
			     				{
			     					if(!combofamille.getSelectedItem().equals(TOUS))
			     					{
			     						FamilleArticlePF famillearticle=mapfamille.get(combofamille.getSelectedItem());
			     						
						     			listSousFamilleArticle=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePF(famillearticle.getId());
						     			if(listSousFamilleArticle.size()!=0)
						     			{
						     				combosousfamille.addItem(TOUS);
						     			}
						     			for(int i=0;i<listSousFamilleArticle.size();i++)
						     			{
						     				
						     				combosousfamille.addItem(listSousFamilleArticle.get(i).getLiblle());
						     				mapsousfamille.put(listSousFamilleArticle.get(i).getLiblle(), listSousFamilleArticle.get(i));
						     				
						     			}
			     					}
			     					
			     					combosousfamille.setSelectedIndex(-1);
			     				}else
			     				{
			     					
			     					combosousfamille.removeAllItems();
			     				}
			     			
			     			}else
			     			{
			     				
			     				combosousfamille.removeAllItems();
			     			}
			     			
			     			
			     		}else
			     		{
			     			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			     			
			     			return;
			     		}
		     		}
	     		}
	     		
	     
	     	
	      	
	      	
	      	}
	      });
	     combofamille.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {

	     	
	     	
	     	}
	     });
	     combofamille.setSelectedIndex(-1);
	     combofamille.setBounds(855, 14, 159, 24);
	     layeredPane_2.add(combofamille);
	     
	     JLabel lblMagasinPf = new JLabel("Magasin PF :");
	     lblMagasinPf.setBounds(91, 16, 67, 24);
	     layeredPane_2.add(lblMagasinPf);
	     lblMagasinPf.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     
	      
	     
	    
	     int i=0;
	     while(i<listClientPF.size())
	     {
	    	 
	    	ClientPF clientpf=listClientPF.get(i);
	    	comboclient.addItem(clientpf.getNom());
	    	 mapClientPF.put(clientpf.getNom(), clientpf);
	    	 
	    	 i++;
	     }
	

	  
	       combosousfamille = new JComboBox();
	       combosousfamille.addItemListener(new ItemListener() {
	       	public void itemStateChanged(ItemEvent e) {


	       		

	     		listArticle.clear();
	     		if(combomagasin.getSelectedIndex()!=-1)
	     		{
	     			if(!combomagasin.getSelectedItem().equals(""))
		     		{
		     			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			     		if(magasin!=null )
			     		{
			     			if(combosousfamille.getSelectedIndex()!=-1   )
			     			{
			     			
			     				
			     			
			     				if(!combosousfamille.getSelectedItem().equals(""))
			     				{
			     					if(!combosousfamille.getSelectedItem().equals(TOUS))
			     					{
			     						SousFamilleArticlePF sousfamillearticle=mapsousfamille.get(combosousfamille.getSelectedItem());
			     						
						     			listArticleStockPF=stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamillearticle.getId());
						     			if(listArticleStockPF.size()!=0)
						     			{
						     				
						     			}
						     			for(int i=0;i<listArticleStockPF.size();i++)
						     			{
						     				StockPF stockpf=listArticleStockPF.get(i);
						     				
						     				mapArticle.put(stockpf.getArticles().getLiblle(), stockpf.getArticles());
						     				listArticle.add(stockpf.getArticles());
						     			}
			     					}
			     					
			     					
			     				}else
			     				{
			     					
			     				}
			     			
			     			}else
			     			{
			     				
			     			}
			     			
			     			
			     		}else
			     		{
			     			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			     			
			     			return;
			     		}
		     		}
	     		}
	     		
	     
	     	
	       		
	       		
	       		
	       	
	       	}
	       });
	       combosousfamille.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
}
	       });
	      combosousfamille.setSelectedIndex(-1);
	      combosousfamille.setBounds(1105, 13, 181, 24);
	      layeredPane_2.add(combosousfamille);
	      
	      JLabel lblSousFamilleArticle = new JLabel("Sous Famille :");
	      lblSousFamilleArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
	      lblSousFamilleArticle.setBounds(1024, 13, 87, 24);
	      layeredPane_2.add(lblSousFamilleArticle);
	      
	      JLabel lblMagasinMp = new JLabel("Magasin MP :");
	      lblMagasinMp.setFont(new Font("Tahoma", Font.PLAIN, 12));
	      lblMagasinMp.setBounds(91, 52, 90, 24);
	      layeredPane_2.add(lblMagasinMp);
	      
	       combomagasinMP = new JComboBox();
	      combomagasinMP.setSelectedIndex(-1);
	      combomagasinMP.setBounds(191, 53, 173, 24);
	      layeredPane_2.add(combomagasinMP);
	      
	      JButton btnExporterExcel = new JButton("Exporter Excel");
	      btnExporterExcel.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      		

				
				  Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  if(magasin!=null) {
				  
				  String
				  titre="Etat Moyen Stock PF "+mapMagasin.get(combomagasin.getSelectedItem()).getLibelle(); 
				  String titrefeuille="Etat Moyen Stock PF ";
				  ExporterTableVersExcel.tabletoexcelEtatMoyenStockPF(table, titre,titrefeuille);
				  
				  }else {
				  
				  
				  JOptionPane.showMessageDialog(null,
				  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
				  ERROR_MESSAGE); return;
				  
				  
				  }
				 
	    	
	      		
	      		
	      		
	      		
	      	}
	      });
	      btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      btnExporterExcel.setBounds(801, 641, 128, 24);
	      btnExporterExcel.setIcon(imgExcel);
	      add(btnExporterExcel);
	      
	      
	      
	     
	      Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	      listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
	      
for(int j=0;j<listMagasin.size();j++)
{
	Magasin magasin=listMagasin.get(j);
	combomagasin.addItem(magasin.getLibelle());
	mapMagasin.put(magasin.getLibelle(), magasin);
}


listFamilleArticle=familleArticlesPFDAO.findAll();
combofamille.addItem("");
if(listFamilleArticle.size()!=0)
	{
	combofamille.addItem(TOUS);
	}
 int p=0;
  while(p<listFamilleArticle.size())
  {
	  
	  FamilleArticlePF FamilleArticlePF=listFamilleArticle.get(p);
	  combofamille.addItem(FamilleArticlePF.getLiblle());
	 
	  mapfamille.put(FamilleArticlePF.getLiblle(), FamilleArticlePF);
	  p++;
  }	
  combofamille.setSelectedIndex(-1);	



Depot depotMP=depotdao.findByCode(utilisateur.getCodeDepot());
listMagasinMP=depotdao.listeMagasinByTypeMagasinDepot(depotMP.getId(), Constantes.MAGASIN_CODE_TYPE_MP);

for(int j=0;j<listMagasinMP.size();j++)
{
Magasin magasin=listMagasinMP.get(j);
combomagasinMP.addItem(magasin.getLibelle());
mapMagasinMP.put(magasin.getLibelle(), magasin);
}








		
		}
	
	


	void initialiser()
	{
		combomagasin.setSelectedIndex(-1);
comboclient.setSelectedItem("");
dudateChooser.setCalendar(null);
 audateChooser.setCalendar(null);
 combofamille.setSelectedIndex(-1);
 
 group.clearSelection();
 InitialiseTableauFacture();	

	}

	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","MP", "Quantite MP","Prix Moyen MP","Montant HT MP", "Quantite Service","Prix Moyen Service","Montant HT Service", "Quantite Finale","Prix Moyen","Montant HT Finale"

				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false
				};
				
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modelefacture);
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(114);
		table.getColumnModel().getColumn(7).setPreferredWidth(114);
		table.getColumnModel().getColumn(8).setPreferredWidth(110);
		table.getColumnModel().getColumn(9).setPreferredWidth(114);
		table.getColumnModel().getColumn(10).setPreferredWidth(136);
		
}
	
	
	
	void afficher_tableEtatPrixMoyen(List<PrixMoyenStockPF> listPrixMoyenStockPF)
	{
		
	
			
				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Code MP","MP","Famille","Sous Famille", "Quantite MP","Prix Moyen MP","Montant HT MP", "Quantite Service","Prix Moyen Service","Montant HT Service", "Quantite Finale","Prix Moyen","Montant HT Finale"
						}
					) {
						boolean[] columnEditables = new boolean[] {
								false,false,false,false,false,false,false,false,false,false,false,false,false
						};
						
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					};
				table.setModel(modelefacture);
				int i=0;
				 
				while(i<listPrixMoyenStockPF.size())
				{	
					 PrixMoyenStockPF prixmoyenStockPF=listPrixMoyenStockPF.get(i);
			 if(prixmoyenStockPF.getSousFamille()==null)
				{
					
					 Object []ligne={prixmoyenStockPF.getMp().getCode(),prixmoyenStockPF.getMp().getNom() ," "," ",prixmoyenStockPF.getQuantiteMP(), prixmoyenStockPF.getPrixMoyenMP(),prixmoyenStockPF.getMontantHTMP() ,prixmoyenStockPF.getQuantiteService(),prixmoyenStockPF.getPrixMoyenService(),prixmoyenStockPF.getMontantHTService(),prixmoyenStockPF.getQuantiteFinale(),prixmoyenStockPF.getPrixMoyenFinale(),prixmoyenStockPF.getMontantHTFinale()};
						
						modelefacture.addRow(ligne);
					
				}else if(prixmoyenStockPF.getMp()==null)
				{
					
					 Object []ligne={" "," ",prixmoyenStockPF.getSousFamille().getFamileArticlePF().getLiblle(),prixmoyenStockPF.getSousFamille().getLiblle(),prixmoyenStockPF.getQuantiteMP(), prixmoyenStockPF.getPrixMoyenMP(),prixmoyenStockPF.getMontantHTMP() ,prixmoyenStockPF.getQuantiteService(),prixmoyenStockPF.getPrixMoyenService(),prixmoyenStockPF.getMontantHTService(),prixmoyenStockPF.getQuantiteFinale(),prixmoyenStockPF.getPrixMoyenFinale(),prixmoyenStockPF.getMontantHTFinale()};
						
						modelefacture.addRow(ligne);
					
				}
			 
			 
			 else
				{
					
					 Object []ligne={prixmoyenStockPF.getMp().getCode(),prixmoyenStockPF.getMp().getNom(),prixmoyenStockPF.getSousFamille().getFamileArticlePF().getLiblle(),prixmoyenStockPF.getSousFamille().getLiblle() ,prixmoyenStockPF.getQuantiteMP(), prixmoyenStockPF.getPrixMoyenMP(),prixmoyenStockPF.getMontantHTMP() ,prixmoyenStockPF.getQuantiteService(),prixmoyenStockPF.getPrixMoyenService(),prixmoyenStockPF.getMontantHTService(),prixmoyenStockPF.getQuantiteFinale(),prixmoyenStockPF.getPrixMoyenFinale(),prixmoyenStockPF.getMontantHTFinale()};
						
						modelefacture.addRow(ligne);
					
				}
				i++;
				}


			
		
		
		
	
}
	
	
	
	public void CalculerPrixMoyenMP()
	{
		
		

		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		listPrixMoyenStockMP.clear();
	  	boolean existe=false;
  		Magasin magasin=mapMagasinMP.get(combomagasinMP.getSelectedItem());
  		String TousSubCategorie="";
  		String Touscategorie="";
  		String TousArticle="";
  		String requete="";
  		if(magasin!=null)
  		{
  			
  			requete=" d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_INITIAL+"' ";
  			
  			requete=requete+"and  d.magasinDestination.id = '"+magasin.getId()+"' ";
  			
  			if(dudateChooser.getDate()!=null && audateChooser.getDate()!=null)
	    		{
  				
	    		if(	DateUtils.nbJoursEntre(dudateChooser.getDate(), audateChooser.getDate())>=0)
	    		{
	    			
	    			String dateDu=formatter.format(dudateChooser.getDate());
	    			String dateAu=formatter.format(audateChooser.getDate());
	    			
	    			
	    			requete=requete+"and  d.transferStockMP.dateTransfer between '"+dateDu +"' and '"+dateAu+"' ";
	    			
	    			
	    			CategorieMp categorieMP=null;
	    			SubCategorieMp souscategorieMP=null;
	    			
	    	
	    			
	    			
	    		
	    			
	    			if(souscategorieMP!=null)
	    			{
	    				requete=requete+"and  d.matierePremier.categorieMp.subCategorieMp.id = '"+souscategorieMP.getId()+"' ";
	    				
	    			}
	    			
	    			if(categorieMP!=null)
	    			{
	    				requete=requete+"and  d.matierePremier.categorieMp.id = '"+categorieMP.getId()+"' ";
	    				
	    			}
	    			
	    		
	    			
	    			MatierePremier mp=null;
	    		
	    			
	    			if(mp!=null)
	    			{
	    				
	    				requete=requete+"and  d.matierePremier.id = '"+mp.getId()+"' ";	
	    				
	    				
	    			}
	    			
	    			
	    			
	    			
		  	       	listDetailTransfertStockMP=detailTransferMPDAO.listeDetailTransfertMP(requete);
		  			 for(int i=0;i<listDetailTransfertStockMP.size();i++)
		  			 {
		  				 
		  				DetailTransferStockMP detailTransferStockMP= listDetailTransfertStockMP.get(i);
		  				
		  			PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
		  			prixMoyenStockMP.setMp(detailTransferStockMP.getMatierePremier());
		  			prixMoyenStockMP.setPrixInitial(detailTransferStockMP.getPrixUnitaire());
		  			prixMoyenStockMP.setQuantiteInitial(detailTransferStockMP.getQuantite());
		  			prixMoyenStockMP.setMontantInitial(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
		  			prixMoyenStockMP.setQuantiteAchat(BigDecimal.ZERO);	
		  			prixMoyenStockMP.setPrixAchat(BigDecimal.ZERO);
		  			prixMoyenStockMP.setMontantAchat(BigDecimal.ZERO);
		  			prixMoyenStockMP.setQuantiteFinale(detailTransferStockMP.getQuantite());
		  			prixMoyenStockMP.setPrixMoyen(detailTransferStockMP.getPrixUnitaire());
		  			prixMoyenStockMP.setMontantHTFinale(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
		  			
		  			
		  			SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(detailTransferStockMP.getMatierePremier());
		  			if(sousFamilleEnVrac!=null)
		  			{
		  				
		  				prixMoyenStockMP.setFamille(sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle());
		  				prixMoyenStockMP.setSousfamille(sousFamilleEnVrac.getSousfamile().getLiblle());
		  				
		  			}else
		  			{
		  				
		  				
		  				prixMoyenStockMP.setFamille(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
		  				prixMoyenStockMP.setSousfamille(detailTransferStockMP.getMatierePremier().getCategorieMp().getNom());
		  				
		  				
		  			}
		  			
		  			listPrixMoyenStockMP.add(prixMoyenStockMP);
		  				 
		  				 
		  			 }
		  			 
		  			CalculerPrixMoyenInitial()	;
	  			
	  				
	  				
	  			for(int i=0;i<listEtatInitialMP.size();i++)	
	  			{
	  				
	  				EtatInitialParSousCtaegorieMP etatInitialParSousCtaegorieMP=listEtatInitialMP.get(i);
	  				
	  			for(int j=0;j<listPrixMoyenStockMP.size();j++)	
	  			{
	  				
	  				PrixMoyenStockMP moyenStockMP=listPrixMoyenStockMP.get(j);
	  				
	  				if(etatInitialParSousCtaegorieMP.getSousFamilleArticlePF().equals(moyenStockMP.getSousfamille()))
	  				{
	  					
	  					
	  					moyenStockMP.setPrixInitial(etatInitialParSousCtaegorieMP.getPrixMoyen());
	  					
	  					moyenStockMP.setMontantInitial(etatInitialParSousCtaegorieMP.getPrixMoyen().multiply(moyenStockMP.getQuantiteInitial()));
	  					moyenStockMP.setPrixMoyen(etatInitialParSousCtaegorieMP.getPrixMoyen());
	  					moyenStockMP.setMontantHTFinale(etatInitialParSousCtaegorieMP.getPrixMoyen().multiply(moyenStockMP.getQuantiteInitial()));
	  					
	  					listPrixMoyenStockMP.set(j, moyenStockMP);
	  					
	  				}
	  				
	  				
	  				
	  			}
	  				
	  				
	  				
	  				
	  				
	  			}
		  			 
		  			 
		  			 
		  			 
		  				  
		  				
		  				listeObject=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dudateChooser.getDate(), audateChooser.getDate(),magasin,souscategorieMP,mp, TousSubCategorie,TousArticle, categorieMP , Touscategorie);
		  				
		  				
		  				
		  				for(int j=0;j<listeObject.size();j++)
		  				{
		  					existe=false;
		  					 Object[] object=listeObject.get(j);
		  					 
		  					 for(int k=0;k<listPrixMoyenStockMP.size();k++)
		  					 {
		  						 
		  						PrixMoyenStockMP prixMoyenStockMP=listPrixMoyenStockMP.get(k); 
		  						 
		  						 
		  						 
		  						if(object[0].toString().equals(prixMoyenStockMP.getMp().getCode()))
		  						{
		  							
		  							existe=true;
		  							
		  							prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
						  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
						  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
						  			prixMoyenStockMP.setQuantiteFinale(prixMoyenStockMP.getQuantiteAchat().add(prixMoyenStockMP.getQuantiteInitial()));
						  			prixMoyenStockMP.setPrixMoyen((prixMoyenStockMP.getMontantInitial().add(prixMoyenStockMP.getMontantAchat())).divide(prixMoyenStockMP.getQuantiteAchat().add(prixMoyenStockMP.getQuantiteInitial()), 6, RoundingMode.HALF_UP));
						  			prixMoyenStockMP.setMontantHTFinale(prixMoyenStockMP.getQuantiteFinale().multiply(prixMoyenStockMP.getPrixMoyen()));
						  			listPrixMoyenStockMP.set(k, prixMoyenStockMP);
		  							
		  						}
		  						
		  						 
		  					 }
		  					
		  					 if(existe==false)
		  					 {
		  						
		  						MatierePremier matierePremier=MatierPremierDAO.findByCode(object[0].toString()) ;
		  						 
		  						SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(matierePremier);
		  						
		  						
		  						
		  						PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
					  			prixMoyenStockMP.setMp(matierePremier);
					  			prixMoyenStockMP.setPrixInitial(BigDecimal.ZERO);
					  			prixMoyenStockMP.setQuantiteInitial(BigDecimal.ZERO);
					  			prixMoyenStockMP.setMontantInitial(BigDecimal.ZERO);
					  			prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
					  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
					  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
					  			prixMoyenStockMP.setQuantiteFinale(new BigDecimal(object[4].toString()));
					  			prixMoyenStockMP.setPrixMoyen(new BigDecimal(object[5].toString()));
					  			prixMoyenStockMP.setMontantHTFinale(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
					  			
					  			if(sousFamilleEnVrac!=null)
					  			{
					  				
					  				prixMoyenStockMP.setFamille(sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle());
					  				prixMoyenStockMP.setSousfamille(sousFamilleEnVrac.getSousfamile().getLiblle());
					  				
					  			}else
					  			{
					  				
					  				
					  				prixMoyenStockMP.setFamille(matierePremier.getCategorieMp().getSubCategorieMp().getNom());
					  				prixMoyenStockMP.setSousfamille(matierePremier.getCategorieMp().getNom());
					  				
					  				
					  			}
					  			
					  			
					  			listPrixMoyenStockMP.add(prixMoyenStockMP); 
		  						 
		  						 
		  						 
		  						 
		  						 
		  						 
		  					 }
		  					 
		  					 
		  					 
		  					 
		  					 
		  					
		  				}
		  				
		  				
		  				CalculerPrixMoyenAchat();
		  				
		  				
		  				for(int i=0;i<listeObjectPrixMoyenAchat.size();i++)	
			  			{
			  				
		  					 Object[] object=listeObjectPrixMoyenAchat.get(i);
							 
							 String famille="";
							 String sousFamille="";
						
							 MatierePremier matierePremier=MatierPremierDAO.findByCode(object[0].toString());
							 
							 SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(matierePremier);
							 
							 if(sousFamilleEnVrac!=null)
							 {
								 
								famille=sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle(); 
								sousFamille=sousFamilleEnVrac.getSousfamile().getLiblle();
								 
							 }else
							 {
								 
								 famille=matierePremier.getCategorieMp().getSubCategorieMp().getNom(); 
									sousFamille=matierePremier.getCategorieMp().getNom();
								 
								 
							 }
			  				
			  			for(int j=0;j<listPrixMoyenStockMP.size();j++)	
			  			{
			  				
			  				PrixMoyenStockMP moyenStockMP=listPrixMoyenStockMP.get(j);
			  				
			  				if(sousFamille.equals(moyenStockMP.getSousfamille()))
			  				{
			  					
			  					
			  					moyenStockMP.setPrixAchat (new BigDecimal(object[5].toString()));
			  					
			  					moyenStockMP.setMontantAchat(new BigDecimal(object[5].toString()).multiply(moyenStockMP.getQuantiteAchat()));
			  					
			  					moyenStockMP.setQuantiteFinale(moyenStockMP.getQuantiteAchat().add(moyenStockMP.getQuantiteInitial()));
			  					moyenStockMP.setPrixMoyen((moyenStockMP.getMontantInitial().add(moyenStockMP.getMontantAchat())).divide(moyenStockMP.getQuantiteAchat().add(moyenStockMP.getQuantiteInitial()), 6, RoundingMode.HALF_UP));
			  					moyenStockMP.setMontantHTFinale(moyenStockMP.getQuantiteFinale().multiply(moyenStockMP.getPrixMoyen()));
			  					
			  					listPrixMoyenStockMP.set(j, moyenStockMP);
			  					
			  				}
			  				
			  				
			  				
			  			}
			  				
			  				
			  				
			  				
			  				
			  			}
		  				
		  				
		  				
		  				
		  				
		  				
		  				
		  			
		  				  
		  			  
		  			  
		  		 
		  			  
		  		
		  		  
	    			
	    		}else
	    		{
	    			JOptionPane.showMessageDialog(null, "La date de d�but doit etre sup�rieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		
	    		}
  		}else
  		{
  			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
  			return;
  		}
  
  		
	

		
		
		
		
	
		
		
		
		
	}
	
	
	
	
public void CalculerPrixMoyenAchat()	
{
	
	
	

	
	
  	
	Magasin magasin=mapMagasinMP.get(combomagasinMP.getSelectedItem());
	String TousSubCategorie="";
	String Touscategorie="";
	String TousArticle="";
	if(magasin!=null)
	{
		if(dudateChooser.getDate()!=null && audateChooser.getDate()!=null)
		{
			
		if(	DateUtils.nbJoursEntre(dudateChooser.getDate(), audateChooser.getDate())>=0)
		{
			
			CategorieMp categorieMP=null;
			SubCategorieMp souscategorieMP=null;
			
		

			
		
			
			MatierePremier mp=null;
	
			
  		
  			 
  				  
  				
			listeObjectPrixMoyenAchat=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dudateChooser.getDate(), audateChooser.getDate(),magasin,souscategorieMP,mp, TousSubCategorie,TousArticle, categorieMP , Touscategorie);
  				
  				  
			
		}else
		{
			JOptionPane.showMessageDialog(null, "La date de d�but doit etre sup�rieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		}
	}else
	{
		JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}

	







	
	
	
	
	
}
	
	
public void CalculerPrixMoyenInitial()	

{
	
	

			Magasin magasin=mapMagasinMP.get(combomagasinMP.getSelectedItem());
			
			if(magasin!=null)
			{
				
			BigDecimal quantite=BigDecimal.ZERO;
			BigDecimal quantitefoiprix=BigDecimal.ZERO;
		BigDecimal quantiteOffre=BigDecimal.ZERO;
		BigDecimal quantiteOffrefoiprix=BigDecimal.ZERO;
			
			
			
				boolean trouve=false;
				
			String dateDebut=((JTextField)dudateChooser.getDateEditor().getUiComponent()).getText();
		String dateFin=((JTextField)audateChooser.getDateEditor().getUiComponent()).getText();
	if(dateDebut.equals(""))	{
		JOptionPane.showMessageDialog(null, "Il faut choisir Date D�but", "Erreur", JOptionPane.ERROR_MESSAGE);
	} else if(dateFin.equals("")){
		JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
		
	
		}else {
			
			listEtatInitialMP.clear();
			listDetailTransferStockMPTmp=detailTransferMPDAO.listeDetailTransfertMPByDateByMagasinByStatut(dudateChooser.getDate(), audateChooser.getDate(), magasin,Constantes.ETAT_TRANSFER_STOCK_INITIAL);
		
			if(listDetailTransferStockMPTmp.size()!=0)
			{
				
				
				 for(int k=0;k<listDetailTransferStockMPTmp.size();k++)
				 {
					 DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMPTmp.get(k);
					SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(detailTransferStockMP.getMatierePremier())	;
					 
					 trouve=false;
					
					 if(listDetailTransferStockMP.size()!=0)
					 {
						
						 for(int i=0;i<listDetailTransferStockMP.size();i++)
						 {
							 if(sousFamilleEnVrac!=null)
							 {
								SousFamilleEnVrac sousFamilleEnVracTmp=sousFamilleEnVracDAO.findByMP(listDetailTransferStockMP.get(i).getMatierePremier())	;
								 if(sousFamilleEnVracTmp!=null)
								 {
									 
									 if(sousFamilleEnVracTmp.getSousfamile().getId()==sousFamilleEnVrac.getSousfamile().getId())
									 {
										 
										 trouve=true;
										 
									 }
									 
									 
								 }
								 
								 
								 
								 
							 }else
							 {
								 
								 if(listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp().equals(detailTransferStockMP.getMatierePremier().getCategorieMp()))
								 {
									 trouve=true;
								 } 
								 
							 }
							 
							
							 
							 
							 
							 
						 }
						 
					 }else
					 {
						listDetailTransferStockMP.add(detailTransferStockMP);
						trouve=true;
					 }
					 
					 if(trouve==false)
					 {
						listDetailTransferStockMP.add(detailTransferStockMP);
					 }
				 }
				
				
				
			boolean existe=false;
			
			String famille="";
			String sousFamille="";
				if(listDetailTransferStockMP.size()!=0)
				{
					for(int i=0;i<listDetailTransferStockMP.size();i++)
					{
						CategorieMp sousCategorieMP =listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp();
						quantite=BigDecimal.ZERO;
						quantitefoiprix=BigDecimal.ZERO;
						
					SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(listDetailTransferStockMP.get(i).getMatierePremier())	;
					
					if(sousFamilleEnVrac==null)
					{
						famille=listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getNom();
						sousFamille=listDetailTransferStockMP.get(i).getMatierePremier().getCategorieMp().getNom();
						
					}else
					{
						
						famille=sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle();
						sousFamille=sousFamilleEnVrac.getSousfamile().getLiblle();
						
						
					}
						
						existe=false;
					for(int j=0;j<listDetailTransferStockMPTmp.size();j++)	
					{
						
						
						/*
						if(listDetailTransferStockMPTmp.get(j).getMatierePremier().getCategorieMp().equals(sousCategorieMP))
						{*/
						 
						   		if(sousFamilleEnVrac!=null)
						   		{
						   			
						   		SousFamilleEnVrac sousFamilleEnVracTmp=sousFamilleEnVracDAO.findByMP(listDetailTransferStockMPTmp.get(j).getMatierePremier())	;
						   		
						   		if(sousFamilleEnVracTmp!=null)
						   		{
						   			if(sousFamilleEnVracTmp.getSousfamile().getId()==sousFamilleEnVrac.getSousfamile().getId())
						   			{
						   			quantitefoiprix=quantitefoiprix.add(listDetailTransferStockMPTmp.get(j).getQuantite().multiply(listDetailTransferStockMPTmp.get(j).getPrixUnitaire()));
									quantite=quantite.add(listDetailTransferStockMPTmp.get(j).getQuantite());
									existe=true;
						   			}
						   	
								
								
						   		}
						   	 
						   			
						   		}else
						   		{
						   		if(listDetailTransferStockMPTmp.get(j).getMatierePremier().getCategorieMp().equals(sousCategorieMP))
						   		{
						   		quantitefoiprix=quantitefoiprix.add(listDetailTransferStockMPTmp.get(j).getQuantite().multiply(listDetailTransferStockMPTmp.get(j).getPrixUnitaire()));
								quantite=quantite.add(listDetailTransferStockMPTmp.get(j).getQuantite());
								existe=true;
						   		}
						   			
						   	
						   			
						   			
						   		}
						 
						   	
						   
							
							
							
					/*	}*/
						
						
					}
					
	
					if(existe==true)
					{
						
					
						EtatInitialParSousCtaegorieMP etatInitialParSousCtaegorieMP=new EtatInitialParSousCtaegorieMP();
						etatInitialParSousCtaegorieMP.setSousCategorie(sousCategorieMP);
						etatInitialParSousCtaegorieMP.setCategorie(sousCategorieMP.getSubCategorieMp());
						etatInitialParSousCtaegorieMP.setFamilleArticlePF(famille);
						etatInitialParSousCtaegorieMP.setSousFamilleArticlePF(sousFamille);
						etatInitialParSousCtaegorieMP.setTotalInitial(quantite);
						etatInitialParSousCtaegorieMP.setPrixMoyen(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
						etatInitialParSousCtaegorieMP.setMontant(etatInitialParSousCtaegorieMP.getPrixMoyen().multiply(etatInitialParSousCtaegorieMP.getTotalInitial()).setScale(6, RoundingMode.FLOOR));
						listEtatInitialMP.add(etatInitialParSousCtaegorieMP);
					}
					
					
				
					
						
					}
					
					
					
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Accun Vente � la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
					listEtatInitialMP.clear();
					
					return;
				}
				
			}
			
			
		}
				
			}
			

		
			
		
	
	
	
	
	
	
	
	
}



public void CalculerPrixMoyenservice()
{
	

			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			
			if(magasin!=null)
			{
				
			BigDecimal quantite=BigDecimal.ZERO;
			BigDecimal quantitefoiprix=BigDecimal.ZERO;
				boolean trouve=false;
				
			String dateDebut=((JTextField)dudateChooser.getDateEditor().getUiComponent()).getText();
		String dateFin=((JTextField)audateChooser.getDateEditor().getUiComponent()).getText();
	if(dateDebut.equals(""))	{
		JOptionPane.showMessageDialog(null, "Il faut choisir Date D�but", "Erreur", JOptionPane.ERROR_MESSAGE);
	} else if(dateFin.equals("")){
		JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
		
	
		}else {
			
			listEtatPrixMoyenserviceParSousFamille.clear();
			listDetailFactureTmp=detailTransfertPFdao.ListTransferStockPFEntreDeuxDatesService (dudateChooser.getDate(), audateChooser.getDate(),magasin);
		
			if(listDetailFactureTmp.size()!=0)
			{
				
				
				 for(int k=0;k<listDetailFactureTmp.size();k++)
				 {
					 DetailTransferProduitFini detailTransferProduitFini=listDetailFactureTmp.get(k);
					 trouve=false;
					
					 if(listDetailFacture.size()!=0)
					 {
						
						 for(int i=0;i<listDetailFacture.size();i++)
						 {
							 if(listDetailFacture.get(i).getSousFamille().equals(detailTransferProduitFini.getSousFamille()))
							 {
								 trouve=true;
							 }
							 
						 }
						 
					 }else
					 {
						listDetailFacture.add(detailTransferProduitFini);
						trouve=true;
					 }
					 
					 if(trouve==false)
					 {
						listDetailFacture.add(detailTransferProduitFini);
					 }
				 }
				
				
				
			
				
				if(listDetailFacture.size()!=0)
				{
					for(int i=0;i<listDetailFacture.size();i++)
					{
						SousFamilleArticlePF sousfamilleArticle =listDetailFacture.get(i).getSousFamille();
						quantite=BigDecimal.ZERO;
						quantitefoiprix=BigDecimal.ZERO;
						
					for(int j=0;j<listDetailFactureTmp.size();j++)	
					{
						
						
						
						if(listDetailFactureTmp.get(j).getSousFamille().equals(sousfamilleArticle))
						{
							
							FactureServiceProduction factureServiceProduction=factureServiceProductionDAO.findByNumOF(listDetailFactureTmp.get(j).getTransferStockPF().getCodeTransfer());
							DetailFactureServiceProduction detailFactureServiceProduction= detailFactureServiceProductionDAO.DetailFactureServiceProductionByFactureByArticle(factureServiceProduction.getId(), listDetailFactureTmp.get(j).getArticle().getLiblle());
							
							quantite=quantite.add(detailFactureServiceProduction.getQuantite());
							
							Production production=productionDAO.findByNumOF(detailFactureServiceProduction.getFactureService().getNumOF(),detailFactureServiceProduction.getFactureService().getDepot().getCode());
							
							List<CoutMP> ListeCoutMP=productionDAO.listeCoutMP(production.getId());
							
							
							for(int d=0;d<ListeCoutMP.size();d++)
							{
								
								CoutMP coutMP =ListeCoutMP.get(d);
							if(	coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
							{
								
								
								if(coutMP.getQuantiteOffre().compareTo(BigDecimal.ZERO)!=0)						
									
								{
									
								
									quantitefoiprix=quantitefoiprix.add((detailFactureServiceProduction.getQuantite().subtract(coutMP.getQuantiteOffre())).multiply(detailFactureServiceProduction.getPrix()));

									
		
									
								}else
								{
									quantitefoiprix=quantitefoiprix.add(detailFactureServiceProduction.getQuantite().multiply(detailFactureServiceProduction.getPrix()));

								}
								
								
								
							}
								
								
								
								
							}
							
							
							
							
							
							
						}
						
						
					}
					
					EtatVenteParFamilleArticle etatventeFamilleArticle=new EtatVenteParFamilleArticle();
					etatventeFamilleArticle.setSousFamilleArticlePF(sousfamilleArticle);
					etatventeFamilleArticle.setTotalVente(quantite);
					if(quantite.compareTo(BigDecimal.ZERO)==0)
					{
						etatventeFamilleArticle.setPrixMoyen(quantite);
					}else
					{
						etatventeFamilleArticle.setPrixMoyen(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
					}
					
					etatventeFamilleArticle.setMontant(etatventeFamilleArticle.getPrixMoyen().multiply(etatventeFamilleArticle.getTotalVente()));
					listEtatPrixMoyenserviceParSousFamille.add(etatventeFamilleArticle);
					
						
					}
					
					
					
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Accun Vente � la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
					listEtatPrixMoyenserviceParSousFamille.clear();
				
					return;
				}
				
			}
			
			
		}
				
			}
			

		
			
		
	
	
}
	
	
	
	
	
	}


