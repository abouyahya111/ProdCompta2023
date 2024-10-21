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
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceTransportDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
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
import dao.daoManager.DetailFactureServiceTransportDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureServiceProductionDAO;
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
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
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


public class EtatFactureAvecOuSansICE extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	
	private DefaultTableModel	 modelefacture;
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<DetailFacturePF> listDetailFacturePF =new ArrayList<DetailFacturePF>();
	private List<DetailFacturePF> listDetailFacturePFTMP =new ArrayList<DetailFacturePF>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<StockPF> listArticleStockPF =new ArrayList<StockPF>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FacturePF> listFacturePF =new ArrayList<FacturePF>();
	
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
private DetailFacturePFDAO detailFacturePfdao;
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
	private StockPFDAO stockpfDAO;
	private CompteClientDAO compteclientdao;
	String titre="";
	 JComboBox combomagasin = new JComboBox();
	 JComboBox comboarticle = new JComboBox();
	   JComboBox comboparfamille = new JComboBox();
	 private FamilleArticlesPFDAO famillearticleDAo;
	 private FactureServiceProductionDAO factureServiceProductionDAO;
	 private DetailFactureServiceTransportDAO detailFactureServiceTransportDAO;
	 private JRadioButton radioVente;
	 JRadioButton radiofacture = new JRadioButton("Facture");
	 private JRadioButton radioService;
	 ButtonGroup group = new ButtonGroup();
	 JCheckBox prixmoyen = new JCheckBox("Calcule prix Moyen");
	 private JCheckBox avecice;
	 JCheckBox sansice = new JCheckBox("Sans ICE");
	  JRadioButton radioTransport = new JRadioButton("Transport");
	  
	  private List <Object[]> listeObjectFactureVente=new ArrayList<Object[]>();
	  private List <Object[]> listeObjectFactureService=new ArrayList<Object[]>();
	  private List <Object[]> listeObjectFactureTransport=new ArrayList<Object[]>();
	  
	  
	  
	  
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public EtatFactureAvecOuSansICE() {
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
             
             
             
            utilisateur=AuthentificationView.utilisateur;
            clientpfdao=new ClientPFDAOImpl();
         	facturepfdao=new FacturePFDAOImpl();
       depotdao=new DepotDAOImpl();
       detailFacturePfdao=new DetailFacturePFDAOImpl();
       famillearticleDAo=new FamilleArticlesPFDAOImpl();
       stockpfDAO=ProdLauncher.stockPFDAO;
       factureServiceProductionDAO=new FactureServiceProductionDAOImpl();
       detailFactureServiceTransportDAO=new DetailFactureServiceTransportDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("Etat Facture Client Avec Ou Sans ICE");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(588, 12, 882, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1524, 521);
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
					"Date ", "N° Facture","Code Client","Client","ICE","Montant HT", "Montant TVA","Montant TTC"
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
	    		
	    		
	    	  		listDetailFactureParFamille.clear();
		    		listDetailFactureParArticle.clear();
		    		listDetailFacturePF.clear();
		    		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		    		String TousFamille="";
		    		String TousArticle="";
		    		String AvecOuSansICE="";
		    		
		    		
		    		
		    		if(magasin!=null)
		    		{
		    			if(dudateChooser.getDate()!=null && audateChooser.getDate()!=null)
			    		{
		    				
			    		if(	DateUtils.nbJoursEntre(dudateChooser.getDate(), audateChooser.getDate())>=0)
			    		{
			    			
			    			
			    			if(avecice.isSelected()==true)
			    			{
			    				AvecOuSansICE=Constantes.CODE_OUI;
			    			}
			    			if(sansice.isSelected()==true)
			    			{
			    				AvecOuSansICE=Constantes.CODE_NON;
			    			}
			
			    			
				  		  if(radioVente.isSelected()==true)
				  		  {
				  			  
				  			listeObjectFactureVente=detailFacturePfdao.listeFactureVenteClientAvecOuSansICE(dudateChooser.getDate(), audateChooser.getDate(), magasin, AvecOuSansICE)  ;

				  		  afficher_tableFacturePF(listeObjectFactureVente);
				  		  
				  		  
				  		  
				  		  }else if (radioService.isSelected()==true)
				  		  {
				  			  
				  			  
				  			listeObjectFactureService=factureServiceProductionDAO.listeFactureServiceClientAvecOuSansICE(dudateChooser.getDate(), audateChooser.getDate(), magasin.getDepot(), AvecOuSansICE) ; 
				  			 afficher_tableFacturePF(listeObjectFactureService);
				  		  
				  		  
				  		  }else if (radioTransport.isSelected()==true)
				  		  {
				  			  
				  			  
					  			listeObjectFactureTransport=detailFactureServiceTransportDAO.listeFactureServiceClientAvecOuSansICE(dudateChooser.getDate(), audateChooser.getDate(), magasin, AvecOuSansICE) ; 
					  			 afficher_tableFacturePF(listeObjectFactureTransport);
				  			  
				  			  
				  		  }else
				  		  {
				  			  
				  			listeObjectFactureVente=detailFacturePfdao.listeFactureVenteClientAvecOuSansICE(dudateChooser.getDate(), audateChooser.getDate(), magasin, AvecOuSansICE)  ;
				  			listeObjectFactureService=factureServiceProductionDAO.listeFactureServiceClientAvecOuSansICE(dudateChooser.getDate(), audateChooser.getDate(), magasin.getDepot(), AvecOuSansICE) ; 
				  			listeObjectFactureTransport=detailFactureServiceTransportDAO.listeFactureServiceClientAvecOuSansICE(dudateChooser.getDate(), audateChooser.getDate(), magasin, AvecOuSansICE) ; 

				  			 afficher_tableMultiFacture(listeObjectFactureVente, listeObjectFactureService, listeObjectFactureTransport); 
				  			  
				  			  
				  		  }
				  		  
			    			
			    		}else
			    		{
			    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			    			return;
			    		}
			    		
			    		}else
			    		{
			    			JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			    			return;
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
	    lblDateFacture.setBounds(698, 13, 40, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     dudateChooser = new JDateChooser();
	     dudateChooser.setBounds(733, 11, 166, 26);
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
	     lblAu.setBounds(909, 13, 40, 24);
	     layeredPane_2.add(lblAu);
	     
	      audateChooser = new JDateChooser();
	     audateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     	
	     	}
	     });
	     audateChooser.setLocale(Locale.FRANCE);
	     audateChooser.setDateFormatString("dd/MM/yyyy");
	     audateChooser.setBounds(944, 11, 166, 26);
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
	     combomagasin = new JComboBox();
	      combomagasin.setBounds(515, 14, 173, 24);
	      layeredPane_2.add(combomagasin);
	      combomagasin.setSelectedIndex(-1);
	      combomagasin.addItem("");
	     JLabel label_1 = new JLabel("Magasin :");
	     label_1.setBounds(462, 12, 56, 24);
	     layeredPane_2.add(label_1);
	     label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     
	
	      
	       radioVente = new JRadioButton("Vente");
	      radioVente.setBackground(SystemColor.activeCaption);
	      radioVente.setFont(new Font("Tahoma", Font.BOLD, 13));
	      radioVente.setBounds(17, 24, 71, 29);
	      add(radioVente);
	      group.add(radioVente);
	       radioService = new JRadioButton("Service");
	      radioService.setBackground(SystemColor.activeCaption);
	      radioService.setFont(new Font("Tahoma", Font.BOLD, 13));
	      radioService.setBounds(107, 24, 107, 29);
	      add(radioService);
	      group.add(radioService);
	      
	       avecice = new JCheckBox("Avec ICE");
	       avecice.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
	       		
	       		if(avecice.isSelected()==true)
	       		{
	       			sansice.setSelected(false);
	       		}
	       		
	       	}
	       });
	      avecice.setFont(new Font("Tahoma", Font.BOLD, 11));
	      avecice.setBounds(345, 24, 92, 23);
	      add(avecice);
	      
	      JButton btnExporterExcel = new JButton("Exporter Excel");
	      btnExporterExcel.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      		

				
				  Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  if(magasin!=null) {
				  
				  String
				  titre="Etat Facture Client Avec Ou Sans ICE  "+mapMagasin.get(combomagasin.getSelectedItem()).getLibelle(); 
				  String titrefeuille="Etat Facture Client Avec Ou Sans ICE ";
				  ExporterTableVersExcel.tabletoexcelEtatFactureClientAvecOuSansICE(table, titre,titrefeuille);
				  
				  }else {
				  
				  
				  JOptionPane.showMessageDialog(null,
				  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
				  ERROR_MESSAGE); return;
				  
				  
				  }
				 
	    	
	      		
	      		
	      		
	      		
	      	}
	      });
	      btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      btnExporterExcel.setBounds(731, 714, 112, 24);
	      add(btnExporterExcel);
	      
	       sansice = new JCheckBox("Sans ICE");
	       sansice.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent e) {
	       		
	       		if(sansice.isSelected()==true)
	       		{
	       			avecice.setSelected(false);
	       		}
	       		
	       	}
	       });
	      sansice.setFont(new Font("Tahoma", Font.BOLD, 11));
	      sansice.setBounds(451, 24, 92, 23);
	      add(sansice);
	      
	       radioTransport = new JRadioButton("Transport");
	      radioTransport.setFont(new Font("Tahoma", Font.BOLD, 13));
	      radioTransport.setBackground(SystemColor.activeCaption);
	      radioTransport.setBounds(231, 21, 112, 29);
	      add(radioTransport);
	      group.add(radioTransport);
	      
	      
	     
	      Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	      listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
	      
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
 comboparfamille.setSelectedIndex(-1);
 comboarticle.setSelectedIndex(-1);
 group.clearSelection();
 InitialiseTableauFacture();	

	}

	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date ", "N° Facture","Client","ICE","Montant HT", "Montant TVA","Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false
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
		
		
}
	
	
	
	void afficher_tableFacturePF(List<Object[]> listDetailFacture)
	{
		
	
				


				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Date ", "N° Facture","Code Client","Client","ICE","Montant HT", "Montant TVA","Montant TTC"
						}
					) {
						boolean[] columnEditables = new boolean[] {
								false,false,false,false,false,false,false,false
						};
						
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					};
				table.setModel(modelefacture);
				int i=0;
				
				Date datefacture=null;
				String numfacture="";
				String codeclient="";
				String nomclient="";
				String ice="";
				BigDecimal ht=BigDecimal.ZERO;
				BigDecimal tva=BigDecimal.ZERO;
				BigDecimal ttc=BigDecimal.ZERO;
				while(i<listDetailFacture.size())
				{
					 datefacture=null;
					 numfacture="";
					 codeclient="";
					 nomclient="";
					 ice="";
					 ht=BigDecimal.ZERO;
					 tva=BigDecimal.ZERO;
					 ttc=BigDecimal.ZERO;
					
					 Object[] object=listDetailFacture.get(i);
				
					// BigDecimal espece=((BigDecimal) object[0]).add(((BigDecimal) object[1]));
					 
					 if(((Date) object[0])!=null)
					 {
						 datefacture=((Date) object[0]);
					 } 
					 if(((String) object[1])!=null)
					 {
						 numfacture=((String) object[1]);
					 } 
					 if(((String) object[2])!=null)
					 {
						 codeclient=((String) object[2]);
					 }
					 
					 if(((String) object[3])!=null)
					 {
						 nomclient=((String) object[3]);
					 }
					 
					 if(((String) object[4])!=null)
					 {
						 ice=((String) object[4]);
					 }
					 
					 if(((BigDecimal) object[5])!=null)
					 {
						 ht=((BigDecimal) object[5]);
					 }
					 
					 if(((BigDecimal) object[6])!=null)
					 {
						 tva=((BigDecimal) object[6]);
					 }
					 
					 if(((BigDecimal) object[7])!=null)
					 {
						 ttc=((BigDecimal) object[7]);
					 }
				
					 if(datefacture!=null)
					 {
							Object []ligne={datefacture,numfacture,codeclient,nomclient,ice,ht,tva, ttc};

							modelefacture.addRow(ligne); 
					 }
					
				
				
					i++;
				}
			
					
	
}
	
	
	void afficher_tableMultiFacture(List<Object[]> listDetailFactureVente,List<Object[]> listDetailFactureService,List<Object[]> listDetailFactureTransport)
	{
		
	
				


				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Date ", "N° Facture","Code Client","Client","ICE","Montant HT", "Montant TVA","Montant TTC"
						}
					) {
						boolean[] columnEditables = new boolean[] {
								false,false,false,false,false,false,false,false
						};
						
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					};
				table.setModel(modelefacture);
	int i=0;
	int j=0;
	int t=0;
				
				Date datefacture=null;
				String numfacture="";
				String codeclient="";
				String nomclient="";
				String ice="";
				BigDecimal ht=BigDecimal.ZERO;
				BigDecimal tva=BigDecimal.ZERO;
				BigDecimal ttc=BigDecimal.ZERO;
				while(i<listDetailFactureVente.size())
				{
					 datefacture=null;
					 numfacture="";
					 codeclient="";
					 nomclient="";
					 ice="";
					 ht=BigDecimal.ZERO;
					 tva=BigDecimal.ZERO;
					 ttc=BigDecimal.ZERO;
					
					 Object[] object=listDetailFactureVente.get(i);
				
					// BigDecimal espece=((BigDecimal) object[0]).add(((BigDecimal) object[1]));
					 
					 if(((Date) object[0])!=null)
					 {
						 datefacture=((Date) object[0]);
					 } 
					 if(((String) object[1])!=null)
					 {
						 numfacture=((String) object[1]);
					 } 
					 if(((String) object[2])!=null)
					 {
						 codeclient=((String) object[2]);
					 }
					 
					 if(((String) object[3])!=null)
					 {
						 nomclient=((String) object[3]);
					 }
					 
					 if(((String) object[4])!=null)
					 {
						 ice=((String) object[4]);
					 }
					 
					 if(((BigDecimal) object[5])!=null)
					 {
						 ht=((BigDecimal) object[5]);
					 }
					 
					 if(((BigDecimal) object[6])!=null)
					 {
						 tva=((BigDecimal) object[6]);
					 }
					 
					 if(((BigDecimal) object[7])!=null)
					 {
						 ttc=((BigDecimal) object[7]);
					 }
				
					 if(datefacture!=null)
					 {
							Object []ligne={datefacture,numfacture,codeclient,nomclient,ice,ht,tva, ttc};

							modelefacture.addRow(ligne); 
					 }
					
				
				
					i++;
				}
				
				while(j<listDetailFactureService.size())
				{
					 datefacture=null;
					 numfacture="";
					 codeclient="";
					 nomclient="";
					 ice="";
					 ht=BigDecimal.ZERO;
					 tva=BigDecimal.ZERO;
					 ttc=BigDecimal.ZERO;
					
					 Object[] object=listDetailFactureService.get(j);
				
					// BigDecimal espece=((BigDecimal) object[0]).add(((BigDecimal) object[1]));
					 
					 if(((Date) object[0])!=null)
					 {
						 datefacture=((Date) object[0]);
					 } 
					 if(((String) object[1])!=null)
					 {
						 numfacture=((String) object[1]);
					 } 
					 if(((String) object[2])!=null)
					 {
						 codeclient=((String) object[2]);
					 }
					 
					 if(((String) object[3])!=null)
					 {
						 nomclient=((String) object[3]);
					 }
					 
					 if(((String) object[4])!=null)
					 {
						 ice=((String) object[4]);
					 }
					 
					 if(((BigDecimal) object[5])!=null)
					 {
						 ht=((BigDecimal) object[5]);
					 }
					 
					 if(((BigDecimal) object[6])!=null)
					 {
						 tva=((BigDecimal) object[6]);
					 }
					 
					 if(((BigDecimal) object[7])!=null)
					 {
						 ttc=((BigDecimal) object[7]);
					 }
				
					 if(datefacture!=null)
					 {
							Object []ligne={datefacture,numfacture,codeclient,nomclient,ice,ht,tva, ttc};

							modelefacture.addRow(ligne); 
					 }
					
				
				
					j++;
				}
				
				
				while(t<listDetailFactureTransport.size())
				{
					 datefacture=null;
					 numfacture="";
					 codeclient="";
					 nomclient="";
					 ice="";
					 ht=BigDecimal.ZERO;
					 tva=BigDecimal.ZERO;
					 ttc=BigDecimal.ZERO;
					
					 Object[] object=listDetailFactureTransport.get(t);
				
					// BigDecimal espece=((BigDecimal) object[0]).add(((BigDecimal) object[1]));
					 
					 if(((Date) object[0])!=null)
					 {
						 datefacture=((Date) object[0]);
					 } 
					 if(((String) object[1])!=null)
					 {
						 numfacture=((String) object[1]);
					 } 
					 if(((String) object[2])!=null)
					 {
						 codeclient=((String) object[2]);
					 }
					 
					 if(((String) object[3])!=null)
					 {
						 nomclient=((String) object[3]);
					 }
					 
					 if(((String) object[4])!=null)
					 {
						 ice=((String) object[4]);
					 }
					 
					 if(((BigDecimal) object[5])!=null)
					 {
						 ht=((BigDecimal) object[5]);
					 }
					 
					 if(((BigDecimal) object[6])!=null)
					 {
						 tva=((BigDecimal) object[6]);
					 }
					 
					 if(((BigDecimal) object[7])!=null)
					 {
						 ttc=((BigDecimal) object[7]);
					 }
				
					 if(datefacture!=null)
					 {
							Object []ligne={datefacture,numfacture,codeclient,nomclient,ice,ht,tva, ttc};

							modelefacture.addRow(ligne); 
					 }
					
				
				
					t++;
				}
			
		
		
		
	
}	
	
	
	
	}


