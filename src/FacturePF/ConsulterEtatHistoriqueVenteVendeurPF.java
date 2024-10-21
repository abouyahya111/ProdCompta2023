package FacturePF;

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
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailHistoriqueVenteVendeurDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
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
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailHistoriqueVenteVendeurDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
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
import dao.entity.DetailFacturePF;
import dao.entity.DetailFacturePFParArticle;
import dao.entity.DetailFacturePFParFamille;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailHistoriqueVenteVendeur;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.EtatHistoriqueVenteVendurPF;
import dao.entity.EtatVentePF;
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


public class ConsulterEtatHistoriqueVenteVendeurPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	
	private DefaultTableModel	 modelefacture;
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<DetailHistoriqueVenteVendeur> listDetailFacturePF =new ArrayList<DetailHistoriqueVenteVendeur>();
	private List<EtatHistoriqueVenteVendurPF> listEtatVentePF =new ArrayList<EtatHistoriqueVenteVendurPF>();
	 
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<StockPF> listArticleStockPF =new ArrayList<StockPF>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	 
	
	 
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	private List<DetailFacturePFParFamille> listDetailFactureParFamille =new ArrayList<DetailFacturePFParFamille>();
	private List<DetailFacturePFParArticle> listDetailFactureParArticle =new ArrayList<DetailFacturePFParArticle>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, ClientPF> mapClientPF= new HashMap<>();
	private Map< String, ClientPF> mapClientPFparCode= new HashMap<>();
	private Map< String, Client> mapFournisseur= new HashMap<>();
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
private DetailHistoriqueVenteVendeurDAO detailHistoriqueVenteVendeurDAO;
private ClientPFDAO clientpfdao;
private ClientDAO fournisseurdao;
private SousFamilleArticlesPFDAO sousFamilleArticlesPFDAO;
JComboBox comboSousFamille = new JComboBox();
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
	private StockPFDAO stockpfDAO;
	private CompteClientDAO compteclientdao;
	String titre="";
	private FamilleArticlesPFDAO familleArticlesDAO;
	 JComboBox combomagasin = new JComboBox();
	 JComboBox comboarticle = new JComboBox();
	   JComboBox comboparfamille = new JComboBox();
	 private FamilleArticlesPFDAO famillearticleDAo;
	 JRadioButton radiobl = new JRadioButton("BL");
	 JRadioButton radiofacture = new JRadioButton("Facture");
	 private JTextField txtcodearticle;
	 FamilleArticlePF FamilleOffre;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterEtatHistoriqueVenteVendeurPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1564, 1062);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
         	familleArticlesDAO=new FamilleArticlesPFDAOImpl();
             FamilleOffre=familleArticlesDAO.findByLibelle(FAMILLE_CADEAU);
             
            utilisateur=AuthentificationView.utilisateur;
            clientpfdao=new ClientPFDAOImpl();
         	facturepfdao=new FacturePFDAOImpl();
       depotdao=new DepotDAOImpl();
       detailHistoriqueVenteVendeurDAO=new DetailHistoriqueVenteVendeurDAOImpl();
       famillearticleDAo=new FamilleArticlesPFDAOImpl();
       stockpfDAO=ProdLauncher.stockPFDAO;
       sousFamilleArticlesPFDAO=new SousFamilleArticlesPFDAOImpl();
       articleDAO=new ArticlesDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
		      
		    
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
					Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						String datedebutFacture=dateFormat.format(dudateChooser.getDate());
				  		  String dateFinFacture=dateFormat.format(audateChooser.getDate());
						
						if(listDetailFacturePF.size()!=0)
				{
							
							
					
					Map parameters = new HashMap();
					parameters.put("titre", titre);
					parameters.put("magasin", magasin.getLibelle());
					
						JasperUtils.imprimerEtatVentePF(table.getModel(),parameters);
					
					
					
					
				}else
				{
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE);
					 return;
				}
					
						

					
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(666, 657, 112, 24);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("                       Etat D'Historique Vente vendeur PF");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(305, 11, 1034, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 212, 1524, 427);
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
					"Date BL", "N° BL","Type", "Client","Article","Famille Article", "Sous Famille", "Montant HT", "Montant TV","Montant TTC"
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
	    ButtonGroup group = new ButtonGroup();
	    
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		listEtatVentePF.clear();
	    	  		listDetailFactureParFamille.clear();
		    		listDetailFactureParArticle.clear();
		    		listDetailFacturePF.clear();
		    		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		    		String TousFamille="";
		    		String TousArticle="";
		    		String requete="";
		    		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		    		if(magasin!=null)
		    		{
		    			
		    			requete=" where historiqueventevendeur.magasin.id='"+magasin.getId()+"' ";
		    			
		    		if(dudateChooser.getDate() !=null || audateChooser.getDate()!=null) {
		    			
		    			if(dudateChooser.getDate()==null && audateChooser.getDate()!=null)
		    			{
		    				dudateChooser.setDate(audateChooser.getDate());
		    			}else if(dudateChooser.getDate()!=null && audateChooser.getDate()==null)
		    			{
		    				audateChooser.setDate(dudateChooser.getDate());
		    				
		    			}else if(dudateChooser.getDate()!=null && audateChooser.getDate()!=null)
		    			{
		    				
		    				if(	DateUtils.nbJoursEntre(dudateChooser.getDate(), audateChooser.getDate())<0)
				    		{

				    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				    			return;
				    		
				    		}
		    				
		    			}
		    			
		    			String dateDu=formatter.format(dudateChooser.getDate());
		    			String dateAu=formatter.format(audateChooser.getDate());
		    			
		    			
		    			
		    			requete=requete +" and historiqueventevendeur.dateFacture between '"+dateDu +"' and  '"+dateAu+"' ";
		    			
		    		}
		    			
		    			
		    			ClientPF clientpf=	mapClientPF.get(comboclient.getSelectedItem());
		    			if(clientpf!=null)
		    			{
		    				requete=requete +" and historiqueventevendeur.clientPF.id = '"+clientpf.getId() +"'";
		    			}
		    			
		    			if(comboparfamille.getSelectedIndex()!=-1)
		    			{
		    				if(!comboparfamille.getSelectedItem().equals(""))
			    			{
			    				FamilleArticlePF famillearticle=mapfamille.get(comboparfamille.getSelectedItem());
			    				 
			    				requete=requete +" and sousFamille.famileArticlePF.id = '"+famillearticle.getId() +"'";
			    				 
			    				 
			    			}
		    			}
		    			
		    			if(comboSousFamille.getSelectedIndex()!=-1)
		    			{
		    				if(!comboSousFamille.getSelectedItem().equals(""))
			    			{
			    				SousFamilleArticlePF sousfamillearticle=mapsousfamille.get(comboSousFamille.getSelectedItem());
			    				 
			    				requete=requete +" and sousFamille.id = '"+sousfamillearticle.getId() +"'";
			    				 
			    				 
			    			}
		    			}
		    			
		    			
		    			if(comboarticle.getSelectedIndex()!=-1)
		    			{
		    				if(!comboarticle.getSelectedItem().equals(""))
			    			{
			    				Articles article=mapArticle.get(comboarticle.getSelectedItem());
			    				
			    				if(article!=null)
			    				{
			    					requete=requete +" and article.id = '"+article.getId() +"'";
			    					
			    				}
			    				
			    				
			    			}
		    				
		    			}
		    			
		    			
		    			
		    			listDetailFacturePF=detailHistoriqueVenteVendeurDAO.listeDetailFacturePFByRequete(requete);
		    			
		    			
		    			
		    			
		    			for(int i=0;i<listDetailFacturePF.size();i++)
		    			{
		    				
		    				DetailHistoriqueVenteVendeur detailFacturePF=listDetailFacturePF.get(i);
		    				
		    				EtatHistoriqueVenteVendurPF etatVentePF=new EtatHistoriqueVenteVendurPF();
		    				etatVentePF.setArticle(detailFacturePF.getArticle());
		    				
		    			 	if(detailFacturePF.getArticle().getCodeArticle().contains(CODE_PRODUIT_FINI_OFFRE))
		    			   	{
		    			 		etatVentePF.setFamilleArticle (FamilleOffre);
		    			   	}else
		    			   	{
		    			   		etatVentePF.setFamilleArticle(detailFacturePF.getSousFamille().getFamileArticlePF());
		    			   	}
		    			 	etatVentePF.setHistoriqueventevendeur (detailFacturePF.getHistoriqueventevendeur());
		    			 	etatVentePF.setSousFamille(detailFacturePF.getSousFamille());
		    			 	etatVentePF.setMontantHT(detailFacturePF.getMontantHT());
		    			 	etatVentePF.setMontantTTC(detailFacturePF.getMontantTTC());
		    			 	etatVentePF.setMontantTVA(detailFacturePF.getMontantTVA());
		    			 	etatVentePF.setPrixUnitaire(detailFacturePF.getPrixUnitaire());
		    			 	etatVentePF.setQuantite(detailFacturePF.getQuantite());
		    			 	etatVentePF.setReduction(detailFacturePF.getReduction());
		    			 	etatVentePF.setTva(detailFacturePF.getTva());
		    			 
		    				listEtatVentePF.add(etatVentePF);
		    				
		    				
		    			}
		    			
		    			
		    			
		    			
		    			
		    			
		    			afficher_tableFacturePF(listEtatVentePF);  

		    		
		    		
		    		
		    		
		    		
		    		
		    		
		    		
		    		
		    		}else
		    		{
		    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		    			return;
		    		}
		    
		
	  
	    		
	    		
	    		
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(524, 177, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1544, 94);
	    add(layeredPane_2);
	    
	    JLabel lblDateFacture = new JLabel("Du :");
	    lblDateFacture.setBounds(269, 16, 40, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     dudateChooser = new JDateChooser();
	     dudateChooser.setBounds(304, 14, 166, 26);
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
	     lblAu.setBounds(480, 16, 40, 24);
	     layeredPane_2.add(lblAu);
	     
	      audateChooser = new JDateChooser();
	     audateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     	
	     	}
	     });
	     audateChooser.setLocale(Locale.FRANCE);
	     audateChooser.setDateFormatString("dd/MM/yyyy");
	     audateChooser.setBounds(515, 14, 166, 26);
	     layeredPane_2.add(audateChooser);
	     
	     JLabel lblClient_1 = new JLabel("Client  :");
	     lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblClient_1.setBounds(694, 16, 70, 24);
	     layeredPane_2.add(lblClient_1);
	     
	      comboclient = new JComboBox();
	     comboclient.addItemListener(new ItemListener() {
	     	public void itemStateChanged(ItemEvent e) {
	     		
	     	}
	     });
	  
	     comboclient.setBounds(759, 16, 206, 24);
	     layeredPane_2.add(comboclient);
	     AutoCompleteDecorator.decorate(comboclient);
	 
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		initialiser();
	     		

	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(641, 178, 106, 23);
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
	     comboclient.addItem("");
	     
	     JLabel label = new JLabel("Famille Article :");
	     label.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     label.setBounds(975, 17, 87, 24);
	     layeredPane_2.add(label);
	     combomagasin = new JComboBox();
	      combomagasin.setBounds(63, 16, 196, 24);
	      layeredPane_2.add(combomagasin);
	      combomagasin.setSelectedIndex(-1);
	      combomagasin.addItem("");
	      
	      comboparfamille = new JComboBox();
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
			     				txtcodearticle.setText("");
			     			
			     				if(comboparfamille.getSelectedIndex()!=-1)
			     				{
			     				
			     						FamilleArticlePF famillearticle=mapfamille.get(comboparfamille.getSelectedItem());
						     			
			     						listArticleStockPF=stockpfDAO.findStockArticleByMagasinPFByFamille(magasin.getId(), famillearticle.getId());
			     						listSousFamilleArticle=sousFamilleArticlesPFDAO.listeSousFamillePFByFamilleArticlePF(famillearticle.getId());
			     						
			     						comboarticle.removeAllItems();
			     						txtcodearticle.setText("");
						     			comboarticle.addItem("");
						     			mapArticle.clear();
						     			mapCodeArticle.clear();
						     			for(int i=0;i<listArticleStockPF.size();i++)
						     			{
						     				StockPF stockpf=listArticleStockPF.get(i);
						     				comboarticle.addItem(stockpf.getArticles().getLiblle());
						     				mapArticle.put(stockpf.getArticles().getLiblle(), stockpf.getArticles());
						     				mapCodeArticle.put(stockpf.getArticles().getCodeArticle(), stockpf.getArticles());
						     				listArticle.add(stockpf.getArticles());
						     			}
						     			
						     			comboSousFamille.removeAllItems();
						     			comboSousFamille.addItem("");
						     			
						     			for(int j=0;j<listSousFamilleArticle.size();j++)
						     			{
						     				
						     				SousFamilleArticlePF sousFamilleArticlePF=listSousFamilleArticle.get(j);
						     				comboSousFamille.addItem(sousFamilleArticlePF.getLiblle());
						     				mapsousfamille.put(sousFamilleArticlePF.getLiblle(), sousFamilleArticlePF);
						     				
						     			}
						     			
						     				
						     			
			     					
			     					comboarticle.setSelectedIndex(-1);
			     					
			     				}else
			     				{
				     				comboarticle.removeAllItems();
				     				txtcodearticle.setText("");
				     				mapArticle.clear();
					     			mapCodeArticle.clear();
				     				listArticle=articleDAO.findAll();
				     				comboarticle.addItem("");
				     				for(int i=0;i<listArticle.size();i++)
					     			{
					     				Articles articles=listArticle.get(i);
					     				comboarticle.addItem(articles.getLiblle());
					     				mapArticle.put(articles.getLiblle(), articles);
					     			mapCodeArticle.put(articles.getCodeArticle(), articles);
					     			}
				     				
				     				comboarticle.setSelectedIndex(-1);
				     			}
			     				
			     				
			     			
			     			}else
			     			{
			     				comboarticle.removeAllItems();
			     				txtcodearticle.setText("");
			     				mapArticle.clear();
				     			mapCodeArticle.clear();
			     				listArticle=articleDAO.findAll();
			     				comboarticle.addItem("");
			     				for(int i=0;i<listArticle.size();i++)
				     			{
				     				Articles articles=listArticle.get(i);
				     				comboarticle.addItem(articles.getLiblle());
				     				mapArticle.put(articles.getLiblle(), articles);
				     			mapCodeArticle.put(articles.getCodeArticle(), articles);
				     			}
			     				
			     				comboarticle.setSelectedIndex(-1);
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
	     comboparfamille.setBounds(1072, 17, 152, 24);
	     layeredPane_2.add(comboparfamille);
	     AutoCompleteDecorator.decorate(comboparfamille);
	     JLabel labelarticle = new JLabel("Article :");
	     labelarticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     labelarticle.setBounds(263, 58, 70, 24);
	     layeredPane_2.add(labelarticle);
	     
	      comboarticle = new JComboBox();
	      comboarticle.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      		

	      		if(comboarticle.getSelectedIndex()!=-1)
			 		{
			 			if(!comboarticle.getSelectedItem().equals(""))
				 		{
			 				
			 				Articles article=mapArticle.get(comboarticle.getSelectedItem());
			 				if(article!=null)
			 				{
			 					txtcodearticle.setText(article.getCodeArticle());
			 				}
			 				
			 				
			 				
				 		}
			 		}
	      		
	      	
	      		
	      		
	      	}
	      });
	     comboarticle.setBounds(321, 59, 235, 24);
	     layeredPane_2.add(comboarticle);
	     AutoCompleteDecorator.decorate(comboarticle);
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
	    
	    listFamilleArticle=famillearticleDAo.findAll();
	    comboparfamille.addItem("");
	  
	     int p=0;
	      while(p<listFamilleArticle.size())
	      {
	    	  
	    	  FamilleArticlePF famillearticle=listFamilleArticle.get(p);
	    	  comboparfamille.addItem(famillearticle.getLiblle());
	    	 
	    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
	    	  p++;
	      }	
	      comboparfamille.setSelectedIndex(-1);
	      
	      JLabel lblSousFamilleArticle = new JLabel("Sous Famille Article :");
	      lblSousFamilleArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
	      lblSousFamilleArticle.setBounds(1234, 16, 124, 24);
	      layeredPane_2.add(lblSousFamilleArticle);
	      
	       comboSousFamille = new JComboBox();
	      comboSousFamille.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      		

	     		listArticle.clear();
	     		if(comboSousFamille.getSelectedIndex()!=-1)
	     		{
	     			if(!combomagasin.getSelectedItem().equals(""))
		     		{
		     			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			     		if(magasin!=null )
			     		{
			     			if(!comboSousFamille.getSelectedItem().equals(""))
			     			{
			     				comboarticle.removeAllItems();
			     				txtcodearticle.setText("");
			     				if(comboSousFamille.getSelectedIndex()!=-1)
			     				{
			     				
			     						SousFamilleArticlePF sousfamillearticle=mapsousfamille.get(comboSousFamille.getSelectedItem());
						     			
			     						listArticleStockPF=stockpfDAO.findStockArticleByMagasinPFBySousFamille (magasin.getId(), sousfamillearticle.getId());
			     						comboarticle.removeAllItems();
			     						txtcodearticle.setText("");
			     						mapArticle.clear();
						     			mapCodeArticle.clear();
						     			comboarticle.addItem("");
						     			
						     			for(int i=0;i<listArticleStockPF.size();i++)
						     			{
						     				StockPF stockpf=listArticleStockPF.get(i);
						     				comboarticle.addItem(stockpf.getArticles().getLiblle());
						     				mapArticle.put(stockpf.getArticles().getLiblle(), stockpf.getArticles());
						     				listArticle.add(stockpf.getArticles());
						     			}
			     				
			     					comboarticle.setSelectedIndex(-1);
			     					
			     				}else
			     				{
			     					///////////////////////////////////////////////////////////////////////////// 
			     					
			     					

			     		     		listArticle.clear();
			     		     		if(combomagasin.getSelectedIndex()!=-1)
			     		     		{
			     		     			if(!combomagasin.getSelectedItem().equals(""))
			     			     		{
			     			     		
			     				     		if(magasin!=null )
			     				     		{
			     				     			if(!comboparfamille.getSelectedItem().equals(""))
			     				     			{
			     				     				comboarticle.removeAllItems();
			     				     				txtcodearticle.setText("");
			     				     				if(comboparfamille.getSelectedIndex()!=-1)
			     				     				{
			     				     				
			     				     						FamilleArticlePF famillearticle=mapfamille.get(comboparfamille.getSelectedItem());
			     							     			
			     				     						listArticleStockPF=stockpfDAO.findStockArticleByMagasinPFByFamille(magasin.getId(), famillearticle.getId());
			     				     						listSousFamilleArticle=sousFamilleArticlesPFDAO.listeSousFamillePFByFamilleArticlePF(famillearticle.getId());
			     				     						
			     				     						comboarticle.removeAllItems();
			     				     						txtcodearticle.setText("");
			     				     						mapArticle.clear();
			     							     			mapCodeArticle.clear();
			     							     			comboarticle.addItem("");
			     							     			
			     							     			for(int i=0;i<listArticleStockPF.size();i++)
			     							     			{
			     							     				StockPF stockpf=listArticleStockPF.get(i);
			     							     				comboarticle.addItem(stockpf.getArticles().getLiblle());
			     							     				mapArticle.put(stockpf.getArticles().getLiblle(), stockpf.getArticles());
			     							     				mapCodeArticle.put(stockpf.getArticles().getCodeArticle(), stockpf.getArticles());
			     							     				listArticle.add(stockpf.getArticles());
			     							     			}
			     							     			
			     							     			comboSousFamille.removeAllItems();
			     							     			comboSousFamille.addItem("");
			     							     			
			     							     			for(int j=0;j<listSousFamilleArticle.size();j++)
			     							     			{
			     							     				
			     							     				SousFamilleArticlePF sousFamilleArticlePF=listSousFamilleArticle.get(j);
			     							     				comboSousFamille.addItem(sousFamilleArticlePF.getLiblle());
			     							     				mapsousfamille.put(sousFamilleArticlePF.getLiblle(), sousFamilleArticlePF);
			     							     				
			     							     			}
			     							     			
			     							     				
			     							     			
			     				     					
			     				     					comboarticle.setSelectedIndex(-1);
			     				     					
			     				     				}else
			     				     				{
			     					     				comboarticle.removeAllItems();
			     					     				txtcodearticle.setText("");
			     					     				mapArticle.clear();
			    						     			mapCodeArticle.clear();
			     					     				listArticle=articleDAO.findAll();
			     					     				for(int i=0;i<listArticle.size();i++)
			     						     			{
			     						     				Articles articles=listArticle.get(i);
			     						     				comboarticle.addItem(articles.getLiblle());
			     						     				mapArticle.put(articles.getLiblle(), articles);
			     						     			mapCodeArticle.put(articles.getCodeArticle(), articles);
			     						     			}
			     					     				
			     					     				comboarticle.setSelectedIndex(-1);
			     					     			}
			     				     				
			     				     				
			     				     			
			     				     			}else
			     				     			{
			     				     				comboarticle.removeAllItems();
			     				     				txtcodearticle.setText("");
			     				     				mapArticle.clear();
									     			mapCodeArticle.clear();
			     				     				listArticle=articleDAO.findAll();
			     				     				for(int i=0;i<listArticle.size();i++)
			     					     			{
			     					     				Articles articles=listArticle.get(i);
			     					     				comboarticle.addItem(articles.getLiblle());
			     					     				mapArticle.put(articles.getLiblle(), articles);
			     					     			mapCodeArticle.put(articles.getCodeArticle(), articles);
			     					     			}
			     				     				
			     				     				comboarticle.setSelectedIndex(-1);
			     				     			}
			     				     			
			     				     			
			     				     		}else
			     				     		{
			     				     			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			     				     			
			     				     			return;
			     				     		}
			     			     		}
			     		     		}
			     		     		
			     		     
			     		     	
			     					
			     					
			     					/////////////////////////////////////////////////////////////////////////////////
			     				}
			     			
			     			}else
			     			{

		     					///////////////////////////////////////////////////////////////////////////// 
		     					
		     					

		     		     		listArticle.clear();
		     		     		if(combomagasin.getSelectedIndex()!=-1)
		     		     		{
		     		     			if(!combomagasin.getSelectedItem().equals(""))
		     			     		{
		     			     			
		     				     		if(magasin!=null )
		     				     		{
		     				     			if(!comboparfamille.getSelectedItem().equals(""))
		     				     			{
		     				     				comboarticle.removeAllItems();
		     				     			
		     				     				if(comboparfamille.getSelectedIndex()!=-1)
		     				     				{
		     				     				
		     				     						FamilleArticlePF famillearticle=mapfamille.get(comboparfamille.getSelectedItem());
		     							     			
		     				     						listArticleStockPF=stockpfDAO.findStockArticleByMagasinPFByFamille(magasin.getId(), famillearticle.getId());
		     				     						listSousFamilleArticle=sousFamilleArticlesPFDAO.listeSousFamillePFByFamilleArticlePF(famillearticle.getId());
		     				     						
		     				     						comboarticle.removeAllItems();
		     							     			comboarticle.addItem("");
		     							     			
		     							     			for(int i=0;i<listArticleStockPF.size();i++)
		     							     			{
		     							     				StockPF stockpf=listArticleStockPF.get(i);
		     							     				comboarticle.addItem(stockpf.getArticles().getLiblle());
		     							     				mapArticle.put(stockpf.getArticles().getLiblle(), stockpf.getArticles());
		     							     				mapCodeArticle.put(stockpf.getArticles().getCodeArticle(), stockpf.getArticles());
		     							     				listArticle.add(stockpf.getArticles());
		     							     			}
		     							     			
		     							     			comboSousFamille.removeAllItems();
		     							     			comboSousFamille.addItem("");
		     							     			
		     							     			for(int j=0;j<listSousFamilleArticle.size();j++)
		     							     			{
		     							     				
		     							     				SousFamilleArticlePF sousFamilleArticlePF=listSousFamilleArticle.get(j);
		     							     				comboSousFamille.addItem(sousFamilleArticlePF.getLiblle());
		     							     				mapsousfamille.put(sousFamilleArticlePF.getLiblle(), sousFamilleArticlePF);
		     							     				
		     							     			}
		     							     			
		     							     				
		     							     			
		     				     					
		     				     					comboarticle.setSelectedIndex(-1);
		     				     					
		     				     				}else
		     				     				{
		     					     				comboarticle.removeAllItems();
		     					     				listArticle=articleDAO.findAll();
		     					     				for(int i=0;i<listArticle.size();i++)
		     						     			{
		     						     				Articles articles=listArticle.get(i);
		     						     				comboarticle.addItem(articles.getLiblle());
		     						     				mapArticle.put(articles.getLiblle(), articles);
		     						     			mapCodeArticle.put(articles.getCodeArticle(), articles);
		     						     			}
		     					     				
		     					     				comboarticle.setSelectedIndex(-1);
		     					     			}
		     				     				
		     				     				
		     				     			
		     				     			}else
		     				     			{
		     				     				comboarticle.removeAllItems();
		     				     				listArticle=articleDAO.findAll();
		     				     				for(int i=0;i<listArticle.size();i++)
		     					     			{
		     					     				Articles articles=listArticle.get(i);
		     					     				comboarticle.addItem(articles.getLiblle());
		     					     				mapArticle.put(articles.getLiblle(), articles);
		     					     			mapCodeArticle.put(articles.getCodeArticle(), articles);
		     					     			}
		     				     				
		     				     				comboarticle.setSelectedIndex(-1);
		     				     			}
		     				     			
		     				     			
		     				     		}else
		     				     		{
		     				     			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		     				     			
		     				     			return;
		     				     		}
		     			     		}
		     		     		}
		     		     		
		     		     
		     		     	
		     					
		     					
		     					/////////////////////////////////////////////////////////////////////////////////
		     				
			     				
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
	      comboSousFamille.setSelectedIndex(-1);
	      comboSousFamille.setBounds(1354, 16, 180, 24);
	      layeredPane_2.add(comboSousFamille);
	      AutoCompleteDecorator.decorate(comboSousFamille);
	      JLabel label_2 = new JLabel("Code Article :");
	      label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      label_2.setBounds(10, 56, 82, 26);
	      layeredPane_2.add(label_2);
	      
	      txtcodearticle = new JTextField();
	      txtcodearticle.addKeyListener(new KeyAdapter() {
	      	@Override
	      	public void keyPressed(KeyEvent e) {
	      		

	     		

     			if(e.getKeyCode()==e.VK_ENTER)
    		{
     				
     					
    			if(!txtcodearticle.getText().equals(""))
    			{
    				//SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
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
	      txtcodearticle.setColumns(10);
	      txtcodearticle.setBounds(79, 56, 139, 26);
	      layeredPane_2.add(txtcodearticle);
	      
	      
	      
	     
	      Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	      listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
	      
for(int j=0;j<listMagasin.size();j++)
{
	Magasin magasin=listMagasin.get(j);
	combomagasin.addItem(magasin.getLibelle());
	mapMagasin.put(magasin.getLibelle(), magasin);
}



	comboarticle.removeAllItems();
	mapArticle.clear();
	mapCodeArticle.clear();
	listArticle=articleDAO.findAll();
	comboarticle.addItem("");
	for(int d=0;d<listArticle.size();d++)
	{
		Articles articles=listArticle.get(d);
		comboarticle.addItem(articles.getLiblle());
		mapArticle.put(articles.getLiblle(), articles);
	mapCodeArticle.put(articles.getCodeArticle(), articles);
	}
	
	comboarticle.setSelectedIndex(-1);
	
	JButton button_1 = new JButton("Exporter Excel");
	button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			

      		

			
			  Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			  if(magasin!=null) {
			  
			  String
			  titre="Etat De Vente PF "+mapMagasin.get(combomagasin.getSelectedItem()).getLibelle(); 
			  String titrefeuille="Etat De Vente PF ";
			  ExporterTableVersExcel.tabletoexcelEtatVentePF(table, titre,titrefeuille);
			  
			  }else {
			  
			  
			  JOptionPane.showMessageDialog(null,
			  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
			  ERROR_MESSAGE); return;
			  
			  
			  }
			 
  	
    		
    		
    		
    		
    		
			
			
			
			
		}
	});
	button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	button_1.setBounds(798, 658, 112, 24);
	add(button_1);

		
		}
	
	


	void initialiser()
	{
		mapArticle.clear();
		mapCodeArticle.clear();
		combomagasin.setSelectedIndex(-1);
comboclient.setSelectedItem("");
dudateChooser.setCalendar(null);
 audateChooser.setCalendar(null);
 comboparfamille.setSelectedIndex(-1);

 txtcodearticle.setText("");
 comboSousFamille.removeAllItems();
 comboSousFamille.setSelectedIndex(-1);
 comboarticle.removeAllItems();
 comboarticle.addItem("");
 comboarticle.setSelectedItem("");
 InitialiseTableauFacture();	
	}

	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date BL", "N° BL","Date facture","Num facture", "Type", "Client","Article","Famille Article", "Sous Famille", "Montant HT", "Montant TV","Montant TTC"
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
		table.getColumnModel().getColumn(8).setPreferredWidth(114);
		table.getColumnModel().getColumn(9).setPreferredWidth(136);
}
	
	
	
	void afficher_tableFacturePF(List<EtatHistoriqueVenteVendurPF> listEtatVentePF)
	{
		

				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Date BL", "N° BL","Date facture","Num facture", "Type", "Client","Article","Famille Article", "Sous Famille","Quantite","Prix" ,"Montant HT", "Montant TVA","Montant TTC"
						}
					) {
						boolean[] columnEditables = new boolean[] {
								false,false,false,false,false,false,false,false,false,false,false,false,false,false
						};
						
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					};
				table.setModel(modelefacture);
				int i=0;
				 
				while(i<listEtatVentePF.size())
				{	
				EtatHistoriqueVenteVendurPF detailfacturepf=listEtatVentePF.get(i);
				
				Date datefacture=detailfacturepf.getHistoriqueventevendeur().getDateFacture();
					
					Object []ligne={detailfacturepf.getHistoriqueventevendeur().getDateBl(),detailfacturepf.getHistoriqueventevendeur().getNumBl(),detailfacturepf.getHistoriqueventevendeur().getDateFacture(),detailfacturepf.getHistoriqueventevendeur().getNumFacture(),detailfacturepf.getHistoriqueventevendeur().getType(),detailfacturepf.getHistoriqueventevendeur().getClientPF().getNom(),detailfacturepf.getArticle().getLiblle(),detailfacturepf.getFamilleArticle().getLiblle(),detailfacturepf.getSousFamille().getLiblle(),detailfacturepf.getQuantite(),detailfacturepf.getPrixUnitaire(), detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(),detailfacturepf.getMontantTTC()};

					modelefacture.addRow(ligne);
					i++;
				}
			
			
		
		
		
	
}
	}


