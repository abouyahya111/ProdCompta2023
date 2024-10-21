package FactureAchatMP;

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
import java.util.Collection;
import java.util.Collections;
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
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FactureAchatMPDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
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
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
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
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatInitialParSousCtaegorieMP;
import dao.entity.EtatPrixMoyen;
import dao.entity.EtatPrixMoyenMP;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.PrixMoyenStockMP;
import dao.entity.Production;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
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


public class MoyenStockMPParSousFamille extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	
	private DefaultTableModel	 modelefacture;
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	
	private List<EtatPrixMoyenMP> listEtatPrixMoyen =new ArrayList<EtatPrixMoyenMP>();
	private List<MatierePremier> listMatierePremiere =new ArrayList<MatierePremier>();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<StockMP> listStockMP =new ArrayList<StockMP>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
	private List<DetailTransferStockMP> listDetailTransfertStockMP =new ArrayList<DetailTransferStockMP>();
	private List<PrixMoyenStockMP> listPrixMoyenStockMP =new ArrayList<PrixMoyenStockMP>();
	private List<PrixMoyenStockMP> listPrixMoyenStockMPParSousFamille =new ArrayList<PrixMoyenStockMP>();
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	private List<CategorieMp> listCategorieMP =new ArrayList<CategorieMp>();
	private List<SubCategorieMp> listSousCategorieMP =new ArrayList<SubCategorieMp>();
	private List<DetailFacturePFParFamille> listDetailFactureParFamille =new ArrayList<DetailFacturePFParFamille>();
	private List<DetailFacturePFParArticle> listDetailFactureParArticle =new ArrayList<DetailFacturePFParArticle>();
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, ClientPF> mapClientPF= new HashMap<>();
	private Map< String, ClientPF> mapClientPFparCode= new HashMap<>();
	private Map< String, Client> mapFournisseur= new HashMap<>();
	private Map< String, CategorieMp> mapcategorie= new HashMap<>();
	private Map< String, SubCategorieMp> mapsouscategorie= new HashMap<>();
	private Map< String, Boolean> maptransfereblfacture = new HashMap<>();
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
	private List <Object[]> listeObjectPrixMoyenAchat=new ArrayList<Object[]>();
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
	 JComboBox comboarticle = new JComboBox();
	 private JComboBox comboMP;
	   JComboBox comboparfamille = new JComboBox();
	   private JComboBox comboparCategorie;
	 private CategorieMpDAO categorieDAo;
	 private SubCategorieMPDAO souscategorieDAO;
	 JRadioButton radiobl = new JRadioButton("BL");
	 JRadioButton radiofacture = new JRadioButton("Facture");
	 ButtonGroup group = new ButtonGroup();
	 JCheckBox prixmoyen = new JCheckBox("Calcule prix Moyen");
	 JComboBox SousFamilleCombo = new JComboBox();
	 private JComboBox SousCategorieCombo;
	 DetailTransferMPDAO detailTransferMPDAO;
	 SousFamilleEnVracDAO sousFamilleEnVracDAO;
		private List<EtatInitialParSousCtaegorieMP> listEtatInitialMP =new ArrayList<EtatInitialParSousCtaegorieMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
		private List<DetailTransferStockMP> listDetailTransferStockMPTmp =new ArrayList<DetailTransferStockMP>();
	 
	 
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public MoyenStockMPParSousFamille() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1564, 1062);
      
	
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
       sousFamilleEnVracDAO=new SousFamilleEnVracDAOImpl();
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
				  		  
			
				  	
				  		  
				  		  
				  		  
						
						if(listPrixMoyenStockMPParSousFamille.size()!=0)
				{
							
							
					BigDecimal TotalInitial=BigDecimal.ZERO;
					BigDecimal PrixMoyenInitial=BigDecimal.ZERO;
					BigDecimal TotalMontantInitial=BigDecimal.ZERO;
					BigDecimal TotalAchat=BigDecimal.ZERO;
					BigDecimal PrixMoyenAchat=BigDecimal.ZERO;
					BigDecimal TotalMontantAchat=BigDecimal.ZERO;
					BigDecimal TotalFinale=BigDecimal.ZERO;
					BigDecimal PrixMoyenFinale=BigDecimal.ZERO;
					BigDecimal TotalMontantFinale=BigDecimal.ZERO;
							
					
					for(int i=0;i<listPrixMoyenStockMPParSousFamille.size();i++)
					{
						PrixMoyenStockMP prixMoyenStockMP =listPrixMoyenStockMPParSousFamille.get(i);
						
						TotalInitial=TotalInitial.add(prixMoyenStockMP.getQuantiteInitial());
						TotalMontantInitial=TotalMontantInitial.add(prixMoyenStockMP.getMontantInitial());
						TotalAchat=TotalAchat.add(prixMoyenStockMP.getQuantiteAchat());
						TotalMontantAchat=TotalMontantAchat.add(prixMoyenStockMP.getMontantAchat());
						TotalFinale=TotalFinale.add(prixMoyenStockMP.getQuantiteFinale());
						TotalMontantFinale=TotalMontantFinale.add(prixMoyenStockMP.getMontantHTFinale());
						
						
					}
					
					if(TotalInitial.compareTo(BigDecimal.ZERO)!=0)
					{
						
						PrixMoyenInitial=TotalMontantInitial.divide(TotalInitial, 6, RoundingMode.HALF_UP);
						
					}
					
					if(TotalAchat.compareTo(BigDecimal.ZERO)!=0)
					{
						
						PrixMoyenAchat=TotalMontantAchat.divide(TotalAchat, 6, RoundingMode.HALF_UP);
						
					}
					
					if(TotalFinale.compareTo(BigDecimal.ZERO)!=0)
					{
						
						PrixMoyenFinale=TotalMontantFinale.divide(TotalFinale, 6, RoundingMode.HALF_UP);
						
					}
					
					Map parameters = new HashMap();
				
					
					parameters.put("TotalInitial", TotalInitial);
					parameters.put("TotalMontantInitial", TotalMontantInitial);
					parameters.put("TotalAchat", TotalAchat);
					parameters.put("TotalMontantAchat", TotalMontantAchat);
					parameters.put("TotalFinale", TotalFinale);
					parameters.put("TotalMontantFinale", TotalMontantFinale);
					parameters.put("PrixMoyenInitial", PrixMoyenInitial);
					parameters.put("PrixMoyenAchat", PrixMoyenAchat);
					parameters.put("PrixMoyenFinale", PrixMoyenFinale);
					
					parameters.put("magasin", magasin.getLibelle());
					parameters.put("titre", " MOYEN STOCK MP PAR SOUS FAMILLE");
					parameters.put("datedebut",datedebutFacture);
					parameters.put("dateFin", dateFinFacture);
					JasperUtils.imprimerMoyenStockMPParSousFamille(listPrixMoyenStockMPParSousFamille,parameters);
				
					
					
					
				}
					
			}}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(666, 599, 112, 24);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("              Moyen Stock MP Par Sous Famille");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(381, 11, 880, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1524, 427);
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
					"Categorie","Sous categorie","Famille","sous Famille", "Quantite Initial","Prix Initial","Montant HT", "Quantite Achat","Prix Moyen Achat","Montant HT", "Quantite Finale","Prix Moyen","Montant HT"

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
		table.getColumnModel().getColumn(8).setPreferredWidth(114);
		table.getColumnModel().getColumn(9).setPreferredWidth(136);
		table.getColumnModel().getColumn(10).setPreferredWidth(136);
		table.getColumnModel().getColumn(11).setPreferredWidth(136);
	
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
	    	  	boolean existe=false;
		    		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
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
			    			
			    			if(comboparCategorie.getSelectedIndex()!=-1)
			    			{
			    				if(!comboparCategorie.getSelectedItem().equals(TOUS))
				    			{
			    					souscategorieMP=mapsouscategorie.get(comboparCategorie.getSelectedItem());
				    			}else
				    			{
				    				TousSubCategorie=TOUS;
				    			}
			    				
			    			}
			    			
			    			
			    			if(SousCategorieCombo.getSelectedIndex()!=-1)
			    			{
			    				if(!SousCategorieCombo.getSelectedItem().equals(TOUS))
				    			{
				    				 categorieMP=mapcategorie.get(SousCategorieCombo.getSelectedItem());
				    			}else
				    			{
				    				Touscategorie=TOUS;
				    			}
			    			}
			    			
			    			if(souscategorieMP!=null)
			    			{
			    				requete=requete+"and  d.matierePremier.categorieMp.subCategorieMp.id = '"+souscategorieMP.getId()+"' ";
			    				
			    			}
			    			
			    			if(categorieMP!=null)
			    			{
			    				requete=requete+"and  d.matierePremier.categorieMp.id = '"+categorieMP.getId()+"' ";
			    				
			    			}
			    			
			    		
			    			
			    			MatierePremier mp=null;
			    			if(comboMP.getSelectedIndex()!=-1)
			    			{
			    				if(!comboMP.getSelectedItem().equals(TOUS))
				    			{
				    				mp=mapMatierePremiere.get(comboMP.getSelectedItem());
				    			}else
				    			{
				    				TousArticle=TOUS;
				    			}
			    				
			    			}
			    			
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
				  				
				  				
				  				listPrixMoyenStockMPParSousFamille.clear();
				  				
				  				boolean trouve=false;
				  				
				  				for(int d=0;d<listPrixMoyenStockMP.size();d++)
				  				{
				  					
				  					trouve=false;
				  					for(int y=0;y<listPrixMoyenStockMPParSousFamille.size();y++)
				  					{
				  						
				  						PrixMoyenStockMP moyenStockMP=listPrixMoyenStockMPParSousFamille.get(y);
				  						
				  						if(listPrixMoyenStockMP.get(d).getSousfamille().equals(listPrixMoyenStockMPParSousFamille.get(y).getSousfamille()))
				  						{
				  							trouve=true;	
				  							moyenStockMP.setQuantiteInitial(moyenStockMP.getQuantiteInitial().add(listPrixMoyenStockMP.get(d).getQuantiteInitial()));
				  							moyenStockMP.setQuantiteAchat(moyenStockMP.getQuantiteAchat().add(listPrixMoyenStockMP.get(d).getQuantiteAchat()));
				  							moyenStockMP.setQuantiteFinale(moyenStockMP.getQuantiteFinale().add(listPrixMoyenStockMP.get(d).getQuantiteFinale()));
				  							moyenStockMP.setMontantInitial(moyenStockMP.getMontantInitial().add(listPrixMoyenStockMP.get(d).getMontantInitial()));
				  							moyenStockMP.setMontantAchat(moyenStockMP.getMontantAchat().add(listPrixMoyenStockMP.get(d).getMontantAchat()));
				  							moyenStockMP.setMontantHTFinale(moyenStockMP.getMontantHTFinale().add(listPrixMoyenStockMP.get(d).getMontantHTFinale()));
				  							if(moyenStockMP.getQuantiteInitial().compareTo(BigDecimal.ZERO)!=0)
				  							{
				  								moyenStockMP.setPrixInitial(moyenStockMP.getMontantInitial().divide(moyenStockMP.getQuantiteInitial(), 6, RoundingMode.HALF_UP));
				  							}
				  							
				  							
				  							if(moyenStockMP.getQuantiteAchat().compareTo(BigDecimal.ZERO)!=0)
				  							{
				  								moyenStockMP.setPrixAchat(moyenStockMP.getMontantAchat().divide(moyenStockMP.getQuantiteAchat(), 6, RoundingMode.HALF_UP));
				  							}
				  							if(moyenStockMP.getQuantiteFinale().compareTo(BigDecimal.ZERO)!=0)
				  							{
					  							moyenStockMP.setPrixMoyen (moyenStockMP.getMontantHTFinale().divide(moyenStockMP.getQuantiteFinale(), 6, RoundingMode.HALF_UP));

				  							}
				  							
				  							listPrixMoyenStockMPParSousFamille.set(y, moyenStockMP);
				  							
				  						}
				  						
				  						
				  					}
				  					
				  					if(trouve==false)
				  					{
				  						listPrixMoyenStockMPParSousFamille.add(listPrixMoyenStockMP.get(d));
				  						
				  					}
				  					
				  					
				  					
				  				}
				  				
				  				
				  				
				  				
				  				afficher_tableEtatPrixMoyen(listPrixMoyenStockMPParSousFamille); 
				  				  
				  			  
				  			  
				  		 
				  			  
				  		
				  		  
			    			
			    		}else
			    		{
			    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			    			return;
			    		}
			    		
			    		}
		    		}else
		    		{
		    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		    			return;
		    		}
		    
		    		
	    	
	  
	    		
	    		
	    		
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(524, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1524, 51);
	    add(layeredPane_2);
	    
	    JLabel lblDateFacture = new JLabel("Du :");
	    lblDateFacture.setBounds(246, 15, 40, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     dudateChooser = new JDateChooser();
	     dudateChooser.setBounds(281, 13, 166, 26);
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
	     lblAu.setBounds(457, 15, 40, 24);
	     layeredPane_2.add(lblAu);
	     
	      audateChooser = new JDateChooser();
	     audateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     	
	     	}
	     });
	     audateChooser.setLocale(Locale.FRANCE);
	     audateChooser.setDateFormatString("dd/MM/yyyy");
	     audateChooser.setBounds(492, 13, 166, 26);
	     layeredPane_2.add(audateChooser);
	 
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		initialiser();
	     		

	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(641, 120, 106, 23);
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
	     
	     JLabel lblCategorie = new JLabel("Categorie :");
	     lblCategorie.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     lblCategorie.setBounds(668, 15, 108, 24);
	     layeredPane_2.add(lblCategorie);
	     combomagasin = new JComboBox();
	      combomagasin.setBounds(63, 16, 173, 24);
	      layeredPane_2.add(combomagasin);
	      combomagasin.setSelectedIndex(-1);
	      combomagasin.addItem("");
	      
	      comboparCategorie = new JComboBox();
	     comboparCategorie.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		listMatierePremiere.clear();
	     		if(combomagasin.getSelectedIndex()!=-1)
	     		{
	     			if(!combomagasin.getSelectedItem().equals(""))
		     		{
		     			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			     		if(magasin!=null )
			     		{
			     			if(!comboparCategorie.getSelectedItem().equals(""))
			     			{
			     				comboMP.removeAllItems();
			     				SousCategorieCombo.removeAllItems();
			     			
			     				if(comboparCategorie.getSelectedIndex()!=-1)
			     				{
			     					if(!comboparCategorie.getSelectedItem().equals(TOUS))
			     					{
			     						SubCategorieMp souscategoriemp=mapsouscategorie.get(comboparCategorie.getSelectedItem());
			     						
						     			listCategorieMP=categorieDAo.findBySubcategorie(souscategoriemp.getId());
						     			if(listCategorieMP.size()!=0)
						     			{
						     				SousCategorieCombo.addItem(TOUS);
						     			}
						     			for(int i=0;i<listCategorieMP.size();i++)
						     			{
						     				
						     				SousCategorieCombo.addItem(listCategorieMP.get(i).getNom());
						     				mapcategorie.put(listCategorieMP.get(i).getNom(), listCategorieMP.get(i));
						     				
						     			}
			     					}
			     					comboMP.setSelectedIndex(-1);
			     					SousCategorieCombo.setSelectedIndex(-1);
			     				}else
			     				{
			     					comboMP.removeAllItems();
			     					SousCategorieCombo.removeAllItems();
			     				}
			     			
			     			}else
			     			{
			     				comboMP.removeAllItems();
			     				SousCategorieCombo.removeAllItems();
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
	     comboparCategorie.setSelectedIndex(-1);
	     comboparCategorie.setBounds(736, 16, 190, 24);
	     layeredPane_2.add(comboparCategorie);
	    
	     JLabel labelarticle = new JLabel("MP :");
	     labelarticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     labelarticle.setBounds(1227, 15, 40, 24);
	     layeredPane_2.add(labelarticle);
	     
	      comboMP = new JComboBox();
	     comboMP.setBounds(1264, 16, 250, 24);
	     layeredPane_2.add(comboMP);
	     AutoCompleteDecorator.decorate(comboMP);
	     JLabel label_1 = new JLabel("Magasin :");
	     label_1.setBounds(10, 14, 56, 24);
	     layeredPane_2.add(label_1);
	     label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     
	      
	     
	    
	     int i=0;
	     while(i<listClientPF.size())
	     {
	    	 
	    	ClientPF clientpf=listClientPF.get(i);
	    	comboclient.addItem(clientpf.getNom());
	    	 mapClientPF.put(clientpf.getNom(), clientpf);
	    	 
	    	 i++;
	     }
	    
	    listSousCategorieMP=souscategorieDAO.findAll();
	    comboparCategorie.addItem("");
	    if(listSousCategorieMP.size()!=0)
			{
	    	comboparCategorie.addItem(TOUS);
			}
	     int p=0;
	      while(p<listSousCategorieMP.size())
	      {
	    	  
	    	  SubCategorieMp subcategoriemp=listSousCategorieMP.get(p);
	    	  comboparCategorie.addItem(subcategoriemp.getNom());
	    	 
	    	  mapsouscategorie.put(subcategoriemp.getNom(), subcategoriemp);
	    	  p++;
	      }	
	      comboparCategorie.setSelectedIndex(-1);
	      
	       SousCategorieCombo = new JComboBox();
	       SousCategorieCombo.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
	       		

	     		listMatierePremiere.clear();
	     		if(combomagasin.getSelectedIndex()!=-1)
	     		{
	     			if(!combomagasin.getSelectedItem().equals(""))
		     		{
		     			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			     		if(magasin!=null )
			     		{
			     			if(SousCategorieCombo.getSelectedIndex()!=-1   )
			     			{
			     				comboMP.removeAllItems();
			     				
			     			
			     				if(!SousCategorieCombo.getSelectedItem().equals(""))
			     				{
			     					if(!SousCategorieCombo.getSelectedItem().equals(TOUS))
			     					{
			     						CategorieMp categoriemp=mapcategorie.get(SousCategorieCombo.getSelectedItem());
			     						
						     			listStockMP=stockMPDAO.listeStockMPByCategorie(magasin.getId(), categoriemp.getId());
						     			if(listStockMP.size()!=0)
						     			{
						     				comboarticle.addItem(TOUS);
						     			}
						     			for(int i=0;i<listStockMP.size();i++)
						     			{
						     				StockMP stockMP=listStockMP.get(i);
						     				comboMP.addItem(stockMP.getMatierePremier().getNom());
						     				mapMatierePremiere.put(stockMP.getMatierePremier().getNom(), stockMP.getMatierePremier());
						     				listMatierePremiere.add(stockMP.getMatierePremier());
						     			}
			     					}
			     					comboMP.setSelectedIndex(-1);
			     					
			     				}else
			     				{
			     					comboMP.removeAllItems();
			     				}
			     			
			     			}else
			     			{
			     				comboMP.removeAllItems();
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
	      SousCategorieCombo.setSelectedIndex(-1);
	      SousCategorieCombo.setBounds(1036, 15, 181, 24);
	      layeredPane_2.add(SousCategorieCombo);
	      
	      JLabel lblSousFamilleArticle = new JLabel("Sous Categorie :");
	      lblSousFamilleArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
	      lblSousFamilleArticle.setBounds(936, 15, 106, 24);
	      layeredPane_2.add(lblSousFamilleArticle);
	      
	      JButton btnExporterExcel = new JButton("Exporter Excel");
	      btnExporterExcel.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      		

				
				  Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  if(magasin!=null) {
				  
				  String
				  titre="Etat Prix Moyen Achat MP Par Sous Famille"+mapMagasin.get(combomagasin.getSelectedItem()).getLibelle(); 
				  String titrefeuille="Etat Prix Moyen Achat MP Par Sous Famille ";
				  ExporterTableVersExcel.tabletoexcelEtatMoyenStockMPParSousFamille(table, titre,titrefeuille);
				  
				  }else {
				  
				  
				  JOptionPane.showMessageDialog(null,
				  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
				  ERROR_MESSAGE); return;
				  
				  
				  }
				 
	    	
	      		
	      		
	      		
	      		
	      	}
	      });
	      btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      btnExporterExcel.setBounds(801, 600, 128, 24);
	      btnExporterExcel.setIcon(imgExcel);
	      add(btnExporterExcel);
	      
	      
	      
	     
	      Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	      listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
	      
for(int j=0;j<listMagasin.size();j++)
{
	Magasin magasin=listMagasin.get(j);
	combomagasin.addItem(magasin.getLibelle());
	mapMagasin.put(magasin.getLibelle(), magasin);
}
		
		}
	
	


	void initialiser()
	{
		combomagasin.setSelectedIndex(-1);
comboclient.setSelectedItem("");
dudateChooser.setCalendar(null);
 audateChooser.setCalendar(null);
 comboparCategorie.setSelectedIndex(-1);
 comboMP.setSelectedIndex(-1);
 group.clearSelection();
 InitialiseTableauFacture();	

	}

	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","MP", "Quantite Initial","Prix Initial","Montant HT", "Quantite Achat","Prix Moyen Achat","Montant HT", "Quantite Finale","Prix Moyen","Montant HT"

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
		
}
	
	
	
	void afficher_tableEtatPrixMoyen(List<PrixMoyenStockMP> listPrixMoyenStockMP)
	{
		
	
			
				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Categorie","Sous categorie","Famille","sous Famille", "Quantite Initial","Prix Initial","Montant HT", "Quantite Achat","Prix Moyen Achat","Montant HT", "Quantite Finale","Prix Moyen","Montant HT"
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
				 
				while(i<listPrixMoyenStockMP.size())
				{	
					 PrixMoyenStockMP prixmoyenStockMP=listPrixMoyenStockMP.get(i);
				
					 Object []ligne={prixmoyenStockMP.getMp().getCategorieMp().getSubCategorieMp().getNom(),prixmoyenStockMP.getMp().getCategorieMp().getNom(),prixmoyenStockMP.getFamille(),prixmoyenStockMP.getSousfamille(), prixmoyenStockMP.getQuantiteInitial(),prixmoyenStockMP.getPrixInitial(),prixmoyenStockMP.getMontantInitial(),prixmoyenStockMP.getQuantiteAchat(),prixmoyenStockMP.getPrixAchat(),prixmoyenStockMP.getMontantAchat(),prixmoyenStockMP.getQuantiteFinale(),prixmoyenStockMP.getPrixMoyen(),prixmoyenStockMP.getMontantHTFinale()};
						
					modelefacture.addRow(ligne);
					i++;
				}


			
		
		
		
	
}
	
	
	
public void CalculerPrixMoyenAchat()	
{
	
	
	

	
	
  	
	Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
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
			
			if(comboparCategorie.getSelectedIndex()!=-1)
			{
				if(!comboparCategorie.getSelectedItem().equals(TOUS))
    			{
					souscategorieMP=mapsouscategorie.get(comboparCategorie.getSelectedItem());
    			}else
    			{
    				TousSubCategorie=TOUS;
    			}
			}
			
			
			if(SousCategorieCombo.getSelectedIndex()!=-1)
			{
				if(!SousCategorieCombo.getSelectedItem().equals(TOUS))
    			{
    				 categorieMP=mapcategorie.get(SousCategorieCombo.getSelectedItem());
    			}else
    			{
    				Touscategorie=TOUS;
    			}
			}
			
		
			
			MatierePremier mp=null;
			if(comboMP.getSelectedIndex()!=-1)
			{
				if(!comboMP.getSelectedItem().equals(TOUS))
    			{
    				mp=mapMatierePremiere.get(comboMP.getSelectedItem());
    			}else
    			{
    				TousArticle=TOUS;
    			}
				
			}
			
  		
  			 
  				  
  				
			listeObjectPrixMoyenAchat=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dudateChooser.getDate(), audateChooser.getDate(),magasin,souscategorieMP,mp, TousSubCategorie,TousArticle, categorieMP , Touscategorie);
  				
  				  
			
		}else
		{
			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
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
	
	

			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			
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
		JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
					listEtatInitialMP.clear();
					
					return;
				}
				
			}
			
			
		}
				
			}
			

		
			
		
	
	
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	}


