package FactureAchat;

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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureAchatDAOImpl;
import dao.daoImplManager.DetailFactureAvoirClientPFDAOImpl;
import dao.daoImplManager.DetailFactureAvoirPFDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoManager.ArticlesDAO;
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
import dao.daoManager.DetailFactureAvoirClientPFDAO;
import dao.daoManager.DetailFactureAvoirPFDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
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
import dao.entity.DetailFactureAvoirClientPF;
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFacturePFParArticle;
import dao.entity.DetailFacturePFParFamille;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.EtatDetailFactureAchat;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
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


public class EtatFactureAvoirPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	
	private DefaultTableModel	 modelefacture;
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<DetailFactureAvoirPF> listDetailFactureAvoir =new ArrayList<DetailFactureAvoirPF>();
	private List<EtatDetailFactureAchat> listEtatDetailFactureAchatTMP =new ArrayList<EtatDetailFactureAchat>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<StockPF> listArticleStockPF =new ArrayList<StockPF>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
	private FournisseurDAO fournisseurdao;
	private List<Fournisseur> listFournisseur =new ArrayList<Fournisseur>();
	private List<DetailFactureAvoirClientPF> listDetailFactureAvoirClientpf =new ArrayList<DetailFactureAvoirClientPF>();
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	private List<DetailFacturePFParFamille> listDetailFactureParFamille =new ArrayList<DetailFacturePFParFamille>();
	private List<DetailFacturePFParArticle> listDetailFactureParArticle =new ArrayList<DetailFacturePFParArticle>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, ClientPF> mapClientPF= new HashMap<>();
	private Map< String, ClientPF> mapClientPFparCode= new HashMap<>();
	private Map< String, Fournisseur> mapFournisseur= new HashMap<>();
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	private Map< String, Boolean> maptransfereblfacture = new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgSelectAll;
	private ImageIcon imgDeselectAll;
	
	private  JButton btninitialiser = new JButton();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FacturePFDAO facturepfdao;
	private FacturePF facturePF;
private DetailFactureAvoirPFDAO detailFactureAvoirdao;
private ClientPFDAO clientpfdao;
private ImageIcon imgExcel;

private   JDateChooser audateChooser = new JDateChooser();
	private  JComboBox comboFournisseur = new JComboBox();
	
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

	private JRadioButton rdbtnDateFacture;
	private JDateChooser dudateChooser;
	private StockPFDAO stockpfDAO;
	private CompteClientDAO compteclientdao;
	String titre="";
	 JComboBox combomagasin = new JComboBox();
	 JComboBox comboarticle = new JComboBox();
	   JComboBox comboparfamille = new JComboBox();
	 private FamilleArticlesPFDAO famillearticleDAo;
	 JRadioButton radiobl = new JRadioButton("BL");
	 JRadioButton radiofacture = new JRadioButton("Facture");
	 private Map< String, ClientPF> mapCodeClientPF= new HashMap<>();
	 JComboBox comboClient = new JComboBox();
	 JCheckBox checkClient = new JCheckBox("Client");
	 private DetailFactureAvoirClientPFDAO detailFactureAvoirClientpfdao;
	 JComboBox comboType = new JComboBox();
	 
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public EtatFactureAvoirPF() {
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
         	facturepfdao=new FacturePFDAOImpl();
       depotdao=new DepotDAOImpl();
       detailFactureAvoirdao=new DetailFactureAvoirPFDAOImpl();
       famillearticleDAo=new FamilleArticlesPFDAOImpl();
       stockpfDAO=ProdLauncher.stockPFDAO;
       fournisseurdao=new FournisseurDAOImpl();
   	detailFactureAvoirClientpfdao=new DetailFactureAvoirClientPFDAOImpl();
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
						
						if(listDetailFactureAvoir.size()!=0)
				{
							
							
						
							
					
					Map parameters = new HashMap();
					
					parameters.put("magasin", magasin.getLibelle());
					parameters.put("datedebut",datedebutFacture);
					parameters.put("dateFin", dateFinFacture);
					JasperUtils.imprimerEtatFactureAvoir (listDetailFactureAvoir,parameters);
				
					
					
					
				}else if(listDetailFactureAvoirClientpf.size()!=0)
				{
	              Map parameters = new HashMap();
					
					parameters.put("magasin", magasin.getLibelle());
					parameters.put("datedebut",datedebutFacture);
					parameters.put("dateFin", dateFinFacture);
					JasperUtils.imprimerEtatFactureAvoirClientPF (listDetailFactureAvoirClientpf,parameters);
				
					
				}
					
			}}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(666, 654, 123, 32);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("                       Etat Facture Avoir Article");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(421, 11, 1006, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 208, 1524, 427);
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
					"Date ", "N° Facture","Article","Famille Article", "Sous Famille","Fournisseur","Quantite", "prix","Montant HT Avant Reduction", "Reduction","Montant HT", "Montant TV","Montant TTC"
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
		table.getColumnModel().getColumn(8).setPreferredWidth(136);
		table.getColumnModel().getColumn(9).setPreferredWidth(136);
	
		table.setShowVerticalLines(false);
		table.setSelectionBackground(new Color(51, 204, 255));
		table.setRowHeightEnabled(true);
		table.setRowHeight(20);
		table.setGridColor(Color.BLUE);
		table.setForeground(Color.BLACK);
		table.setBackground(Color.WHITE);
		table.setAutoCreateRowSorter(true);
		 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		listEtatDetailFactureAchatTMP.clear();
	    	  		listDetailFactureParFamille.clear();
		    		listDetailFactureParArticle.clear();
		    		listDetailFactureAvoir.clear();
		    		listDetailFactureAvoirClientpf.clear();
		    		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		    		String TousFamille="";
		    		String TousArticle="";
		    		Fournisseur fournisseur=null;
		    		 ClientPF clientpf=null;
		    		
		    		if(magasin!=null)
		    		{
		    			if(dudateChooser.getDate()!=null && audateChooser.getDate()!=null)
			    		{
		    				
			    		if(	DateUtils.nbJoursEntre(dudateChooser.getDate(), audateChooser.getDate())>=0)
			    		{
			    			
			    			
			    			
			    			if(comboType.getSelectedIndex()==-1 || comboType.getSelectedItem().equals(""))
			    			{
			    				
			    				JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Client Ou Fournisseur SVP !!!!!");
			    				return;
			    				
			    			}else
			    			{
			    				
			    				if(comboType.getSelectedItem().equals("CLIENT"))
			    				{
			    					clientpf=	mapClientPF.get(comboClient.getSelectedItem());
			    					
			    					
			    				}else if (comboType.getSelectedItem().equals("FOURNISSEUR"))
			    				{
			    					
			    					fournisseur=	mapFournisseur.get(comboFournisseur.getSelectedItem());
			    					
			    				}
			    				
			    			}
			    			
			    			
			    		
			    			
			    			FamilleArticlePF famillearticle=null;
			    			if(comboparfamille.getSelectedIndex()!=-1)
			    			{
			    				if(!comboparfamille.getSelectedItem().equals(TOUS))
				    			{
				    				 famillearticle=mapfamille.get(comboparfamille.getSelectedItem());
				    			}else
				    			{
				    				TousFamille=TOUS;
				    			}
			    			}
			    		
			    			
			    			Articles article=null;
			    			if(comboarticle.getSelectedIndex()!=-1)
			    			{
			    				if(!comboarticle.getSelectedItem().equals(TOUS))
				    			{
				    				article=mapArticle.get(comboarticle.getSelectedItem());
				    			}else
				    			{
				    				TousArticle=TOUS;
				    			}
			    				
			    			}
			    			
			    			if(comboType.getSelectedItem().equals("CLIENT"))
			    			{
			    				
			    				listDetailFactureAvoirClientpf=detailFactureAvoirClientpfdao.findDetailAvoirArticleEntreDeuxDate(dudateChooser.getDate(), audateChooser.getDate(),magasin,clientpf,famillearticle,article, TousFamille,TousArticle);
			    				
			    				afficher_tableFactureAchatArticleClient (listDetailFactureAvoirClientpf);  
			    				
			    			}else if(comboType.getSelectedItem().equals("FOURNISSEUR"))
			    			{
			    				
			    				
			    				listDetailFactureAvoir=detailFactureAvoirdao.findDetailAvoirArticleEntreDeuxDate(dudateChooser.getDate(), audateChooser.getDate(),magasin,fournisseur,famillearticle,article, TousFamille,TousArticle);
				    			
						    	
				    			
				    			
				    			
				    			afficher_tableFactureAchatArticle (listDetailFactureAvoir);  
			    				
			    			}
				  			
			    			
			    			
			    			
			    			
			    			
				  		
				  		  
			    			
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
	    btnAfficher.setBounds(660, 173, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1554, 105);
	    add(layeredPane_2);
	    
	    JLabel lblDateFacture = new JLabel("Du :");
	    lblDateFacture.setBounds(321, 19, 40, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     dudateChooser = new JDateChooser();
	     dudateChooser.setBounds(356, 17, 205, 26);
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
	     lblAu.setBounds(571, 17, 40, 24);
	     layeredPane_2.add(lblAu);
	     
	      audateChooser = new JDateChooser();
	     audateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     	
	     	}
	     });
	     audateChooser.setLocale(Locale.FRANCE);
	     audateChooser.setDateFormatString("dd/MM/yyyy");
	     audateChooser.setBounds(606, 15, 205, 26);
	     layeredPane_2.add(audateChooser);
	     Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	     
	    
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		initialiser();
	     		

	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(777, 174, 106, 23);
	     add(button);
	   
	     
	     if(utilisateur.getLogin().equals("admin"))
	 		  {

	 		  listClientPF=clientpfdao.findAll();
	 		  
	 		  	 		  
	 		  }else
	 		  {
	 			 
	 			  
	 			  Depot depotTmp=depotdao.findByCode(utilisateur.getCodeDepot());
	 			  if(depotTmp!=null)
	 			  {
	 				 listClientPF=clientpfdao.findListClientByCodeDepot(depotTmp.getCode());
	 					
	 		     	
	 			  }
	 		  }
	     combomagasin = new JComboBox();
	      combomagasin.setBounds(63, 16, 223, 24);
	      layeredPane_2.add(combomagasin);
	      combomagasin.setSelectedIndex(-1);
	      combomagasin.addItem("");
	     JLabel label_1 = new JLabel("Magasin :");
	     label_1.setBounds(10, 14, 56, 24);
	     layeredPane_2.add(label_1);
	     label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     
	      
	     
	    
	
	    
	
	      
	       comboClient = new JComboBox();
	      comboClient.setBounds(1326, 20, 205, 24);
	      layeredPane_2.add(comboClient);
	      AutoCompleteDecorator.decorate(comboClient);
	      JLabel lblClient = new JLabel("Client  :");
	      lblClient.setBounds(1202, 19, 103, 24);
	      layeredPane_2.add(lblClient);
	      lblClient.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	      
	      JLabel lblFournisseur = new JLabel("Fournisseur  :");
	      lblFournisseur.setBounds(1202, 17, 103, 24);
	      layeredPane_2.add(lblFournisseur);
	      lblFournisseur.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	      
	      JButton btnExporterExcel = new JButton("Exporter Excel");
	      btnExporterExcel.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		

	     		

	    		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    		
	    		String titre="Etat Des Factures Avoir PF "+magasin.getLibelle();
	    		String titrefeuille="Etat Des Factures Avoir PF ";
	    		ExporterTableVersExcel.tabletoexcelFactureAchatPF(table, titre,titrefeuille);
	    		
	    		}else
	    		{


	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		
	    		}
	    	
	     		
	     		
	     		
	     		
	     		
	     	
	      		
	      		
	      		
	      		
	      		
	      	}
	      });
	      btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      btnExporterExcel.setBounds(820, 654, 123, 32);
	      btnExporterExcel.setIcon(imgExcel);
	      add(btnExporterExcel);
	      
	      
	      
	     
	      Depot depotIm=depotdao.findByCode(utilisateur.getCodeDepot());
	      listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depotIm.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
	      
for(int j=0;j<listMagasin.size();j++)
{
	Magasin magasin=listMagasin.get(j);
	combomagasin.addItem(magasin.getLibelle());
	mapMagasin.put(magasin.getLibelle(), magasin);
}



listClientPF=clientpfdao.findListClientByCodeDepot(depotIm.getCode());
comboClient.addItem("");

 comboFournisseur = new JComboBox();
 comboFournisseur.setBounds(1325, 19, 206, 24);
 layeredPane_2.add(comboFournisseur);
 comboFournisseur.addItemListener(new ItemListener() {
 	public void itemStateChanged(ItemEvent e) {
 		
 	}
 });
 comboFournisseur.addItem("");
 AutoCompleteDecorator.decorate(comboFournisseur);
 comboFournisseur.addItem("");
 
 JLabel lblNewLabel = new JLabel("Client / Fournisseur : ");
 lblNewLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 13));
 lblNewLabel.setBounds(832, 17, 157, 24);
 layeredPane_2.add(lblNewLabel);
 
  comboType = new JComboBox();
  comboType.addItemListener(new ItemListener() {
  	public void itemStateChanged(ItemEvent arg0) {
  		
  		
 		if(comboType.getSelectedIndex()!=-1)
  		{
  			if(!comboType.getSelectedItem().equals(""))
  	  		{
  				
  				
  				if(comboType.getSelectedItem().equals("CLIENT"))
  				{
  					
  					lblFournisseur.setVisible(false);
  		  			comboFournisseur.setVisible(false);
  		  			lblClient.setVisible(true);
  		  			comboClient.setVisible(true);
  					
  				}else if(comboType.getSelectedItem().equals("FOURNISSEUR"))
  				{
  				
  					lblFournisseur.setVisible(true);
  		  			comboFournisseur.setVisible(true);
  		  			lblClient.setVisible(false);
  		  			comboClient.setVisible(false);
  				
  				
  	  		}
  			
  			
  		}else
  		{
  			lblFournisseur.setVisible(false);
	  			comboFournisseur.setVisible(false);
	  			lblClient.setVisible(false);
	  			comboClient.setVisible(false);
  			
  			
  			
  			
  		}
  		
  		
  		
  		
  	}else
  	{
  		lblFournisseur.setVisible(false);
			comboFournisseur.setVisible(false);
			lblClient.setVisible(false);
			comboClient.setVisible(false);
  	}
  		
  		
  		
  		
  		
  	}
  });
 comboType.setSelectedIndex(-1);
 comboType.setBounds(999, 19, 181, 24);
 layeredPane_2.add(comboType);
 
 JLabel label = new JLabel("Famille Article :");
 label.setBounds(10, 70, 89, 24);
 layeredPane_2.add(label);
 label.setFont(new Font("Tahoma", Font.PLAIN, 12));
 
 comboparfamille = new JComboBox();
 comboparfamille.setBounds(99, 70, 187, 24);
 layeredPane_2.add(comboparfamille);
 comboparfamille.addActionListener(new ActionListener() {
 	public void actionPerformed(ActionEvent e) {
 		listArticle.clear();
 		if(combomagasin.getSelectedIndex()!=-1)
 		{
 			if(!combomagasin.getSelectedItem().equals(""))
		     		{
		     			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			     		if(magasin!=null )
			     		{
			     			if(!comboparfamille.getSelectedItem().equals(""))
			     			{
			     				comboarticle.removeAllItems();
			     			
			     				if(comboparfamille.getSelectedIndex()!=-1)
			     				{
			     					if(!comboparfamille.getSelectedItem().equals(TOUS))
			     					{
			     						FamilleArticlePF famillearticle=mapfamille.get(comboparfamille.getSelectedItem());
						     			listArticleStockPF=stockpfDAO.findStockArticleByMagasinPFByFamille(magasin.getId(), famillearticle.getId());
						     			if(listArticleStockPF.size()!=0)
						     			{
						     				comboarticle.addItem(TOUS);
						     			}
						     			for(int i=0;i<listArticleStockPF.size();i++)
						     			{
						     				StockPF stockpf=listArticleStockPF.get(i);
						     				comboarticle.addItem(stockpf.getArticles().getLiblle());
						     				mapArticle.put(stockpf.getArticles().getLiblle(), stockpf.getArticles());
						     				listArticle.add(stockpf.getArticles());
						     			}
			     					}
			     					comboarticle.setSelectedIndex(-1);
			     					
			     				}else
			     				{
			     					comboarticle.removeAllItems();
			     				}
			     			
			     			}else
			     			{
			     				comboarticle.removeAllItems();
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
 comboparfamille.setSelectedIndex(-1);
 comboparfamille.addItem("");
 comboparfamille.setSelectedIndex(-1);
 
  JLabel labelarticle = new JLabel("Article :");
  labelarticle.setBounds(335, 69, 70, 24);
  layeredPane_2.add(labelarticle);
  labelarticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
  
   comboarticle = new JComboBox();
   comboarticle.setBounds(393, 70, 301, 24);
   layeredPane_2.add(comboarticle);
   AutoCompleteDecorator.decorate(comboarticle);
 comboFournisseur.setVisible(true);
  int t=0;
  while(t<listClientPF.size())
  {
 	 ClientPF clientpf=listClientPF.get(t);
 	 comboClient.addItem(clientpf.getNom());
 	 mapClientPF.put(clientpf.getNom(), clientpf);
 	 mapCodeClientPF.put(clientpf.getCode(), clientpf);
 	 
 	 
 	 t++;
  }

	
	lblFournisseur.setVisible(true);
	lblClient.setVisible(false);
	comboClient.setVisible(false);
	
	
	comboType.addItem("");
	comboType.addItem("CLIENT");
	comboType.addItem("FOURNISSEUR");

	
	 listFournisseur=fournisseurdao.findFournisseurByDepot(depot);
     int i=0;
     while(i<listFournisseur.size())
     {
    	 Fournisseur fournisseur=listFournisseur.get(i);
    	 comboFournisseur.addItem(fournisseur.getNom());
    	
    	 mapFournisseur.put(fournisseur.getNom(), fournisseur);
    	 
    	 i++;
     }

	
     lblFournisseur.setVisible(false);
		comboFournisseur.setVisible(false);
		lblClient.setVisible(false);
		comboClient.setVisible(false);
		
		
	    listFamilleArticle=famillearticleDAo.findAll();
	    if(listFamilleArticle.size()!=0)
			{
	    	comboparfamille.addItem(TOUS);
			}
	     int p=0;
	      while(p<listFamilleArticle.size())
	      {
	    	  
	    	  FamilleArticlePF famillearticle=listFamilleArticle.get(p);
	    	  comboparfamille.addItem(famillearticle.getLiblle());
	    	 
	    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
	    	  p++;
	      }	
		
		
		
		}
	
	


	void initialiser()
	{
		combomagasin.setSelectedIndex(-1);
comboFournisseur.setSelectedItem("");
dudateChooser.setCalendar(null);
 audateChooser.setCalendar(null);
 comboparfamille.setSelectedIndex(-1);
 comboarticle.setSelectedIndex(-1);
 radiobl.setSelected(false);
 radiofacture.setSelected(false);
 InitialiseTableauFacture();	
	}

	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date ", "N° Facture","Article","Famille Article", "Sous Famille","Fournisseur","Quantite", "prix", "Reduction","Montant HT", "Montant TV","Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false,false
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
		table.getColumnModel().getColumn(7).setPreferredWidth(136);
		table.getColumnModel().getColumn(8).setPreferredWidth(136);
		table.getColumnModel().getColumn(9).setPreferredWidth(114);
		
}
	
	
	
	void afficher_tableFactureAchatArticle(List<DetailFactureAvoirPF> listDetailFactureAvoirPF)
	{
		
	
			
	
				


				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Date ", "N° Facture","Article","Famille Article", "Sous Famille","Fournisseur","Quantite", "prix","Montant HT Avant Reduction", "Reduction","Montant HT", "Montant TV","Montant TTC"
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
				BigDecimal MontantHtAvanReduction=BigDecimal.ZERO;
				 
				while(i<listDetailFactureAvoirPF.size())
				{	
				DetailFactureAvoirPF detailFactureAvoirPF=listDetailFactureAvoirPF.get(i);
				
				Date datefacture=detailFactureAvoirPF.getFactureAvoirPf().getDateFacture();
				
				MontantHtAvanReduction=detailFactureAvoirPF.getPrixUnitaire().multiply(detailFactureAvoirPF.getQuantite());
						Object []ligne={datefacture,detailFactureAvoirPF.getFactureAvoirPf().getNumFacture(),detailFactureAvoirPF.getArticle().getLiblle(), detailFactureAvoirPF.getSousFamille().getFamileArticlePF().getLiblle(), detailFactureAvoirPF.getSousFamille().getLiblle(), detailFactureAvoirPF.getFactureAvoirPf().getFournisseur().getNom(),detailFactureAvoirPF.getQuantite(),detailFactureAvoirPF.getPrixUnitaire(),MontantHtAvanReduction,detailFactureAvoirPF.getReduction(), detailFactureAvoirPF.getMontantHT(),detailFactureAvoirPF.getMontantTVA(), detailFactureAvoirPF.getMontantTTC()};

						modelefacture.addRow(ligne);
						
					
				
					i++;
				}
			
				
			
				
				
			}
	
	
	
	void afficher_tableFactureAchatArticleClient(List<DetailFactureAvoirClientPF> listDetailFactureAvoirPF)
	{
		
	
			
	
				


				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Date ", "N° Facture","Article","Famille Article", "Sous Famille","Client","Quantite", "prix","Montant HT Avant Reduction", "Reduction","Montant HT", "Montant TV","Montant TTC"
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
				BigDecimal MontantHtAvanReduction=BigDecimal.ZERO;
				 
				while(i<listDetailFactureAvoirPF.size())
				{	
					DetailFactureAvoirClientPF detailFactureAvoirPF=listDetailFactureAvoirPF.get(i);
				
				Date datefacture=detailFactureAvoirPF.getFactureAvoirClientPf().getDateFacture();
				
				MontantHtAvanReduction=detailFactureAvoirPF.getPrixUnitaire().multiply(detailFactureAvoirPF.getQuantite());
						Object []ligne={datefacture,detailFactureAvoirPF.getFactureAvoirClientPf().getNumFacture(),detailFactureAvoirPF.getArticle().getLiblle(), detailFactureAvoirPF.getSousFamille().getFamileArticlePF().getLiblle(), detailFactureAvoirPF.getSousFamille().getLiblle(), detailFactureAvoirPF.getFactureAvoirClientPf().getClientPF().getNom(),detailFactureAvoirPF.getQuantite(),detailFactureAvoirPF.getPrixUnitaire(),MontantHtAvanReduction,detailFactureAvoirPF.getReduction(), detailFactureAvoirPF.getMontantHT(),detailFactureAvoirPF.getMontantTVA(), detailFactureAvoirPF.getMontantTTC()};

						modelefacture.addRow(ligne);
						
					
				
					i++;
				}
			
				
			
				
				
			}
}
	


