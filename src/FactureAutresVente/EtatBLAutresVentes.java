package FactureAutresVente;

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
import dao.daoImplManager.DetailFactureAutresVenteDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
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
import dao.daoManager.DetailFactureAutresVenteDAO;
import dao.daoManager.DetailFacturePFDAO;
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
import dao.entity.DetailFactureAutresVente;
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


public class EtatBLAutresVentes extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	
	private DefaultTableModel	 modelefacture;
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<DetailFactureAutresVente> listDetailFacturePF =new ArrayList<DetailFactureAutresVente>();
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
private DetailFactureAutresVenteDAO detailFacturePfdao;
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
	 JRadioButton radiobl = new JRadioButton("BL");
	 JRadioButton radiofacture = new JRadioButton("Facture");
	  ButtonGroup group = new ButtonGroup();
	  JButton btnExporterExcel = new JButton("Exporter Excel");
	  private ImageIcon imgExcel;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public EtatBLAutresVentes() {
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
             imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
             
             
            utilisateur=AuthentificationView.utilisateur;
            clientpfdao=new ClientPFDAOImpl();
         	facturepfdao=new FacturePFDAOImpl();
       depotdao=new DepotDAOImpl();
       detailFacturePfdao=new DetailFactureAutresVenteDAOImpl();
       famillearticleDAo=new FamilleArticlesPFDAOImpl();
     
          } catch (Exception exp){exp.printStackTrace();}
		      
		    
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
					Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
					if(magasin!=null && dudateChooser.getDate()!=null && audateChooser.getDate()!=null && comboparfamille.getSelectedIndex()==-1 && comboarticle.getSelectedIndex()==-1)
					{
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						String datedebutFacture=dateFormat.format(dudateChooser.getDate());
				  		  String dateFinFacture=dateFormat.format(audateChooser.getDate());
						
						if(listDetailFacturePF.size()!=0)
				{
							if(comboclient.getSelectedIndex()!=-1)
							{
								if(!comboclient.getSelectedItem().equals(""))
								{
									
									
								titre="Etat du chiffre d'affaires des Articles Du : "+datedebutFacture +"  Au : "+ dateFinFacture+"  de client : "+comboclient.getSelectedItem();
									
									
								}else{
									titre="Etat du chiffre d'affaires des Articles Du : "+datedebutFacture +"  Au : "+ dateFinFacture;
								}
							}else
							{
								titre="Etat du chiffre d'affaires des Articles Du : "+datedebutFacture +"  Au : "+ dateFinFacture;
							}
							
					
					Map parameters = new HashMap();
					parameters.put("titre", titre);
					parameters.put("magasin", magasin.getLibelle());
					if(radiofacture.isSelected()==true)
					{
						JasperUtils.imprimerEtatFacturesAutresVenteTypeFacture(listDetailFacturePF,parameters);
					}else if(radiobl.isSelected()==true)
					{
						JasperUtils.imprimerEtatFacturesAutresVenteTypeBL(listDetailFacturePF,parameters);
					}else
					{
						JasperUtils.imprimerEtatFacturesAutresVente(listDetailFacturePF,parameters);
					}
					
					
					
				}else
				{
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE);
					 return;
				}
					
						
					}
					
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(666, 599, 112, 24);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("                   Etat Des BL/Facture Autres Vente");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(381, 11, 899, 35);
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
					"Date BL", "N° BL","Type", "Client","Article","Prix", "Montant HT", "Montant TV","Montant TTC"
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
		    		if(magasin!=null)
		    		{
		    			if(dudateChooser.getDate()!=null && audateChooser.getDate()!=null)
			    		{
		    				
			    		if(	DateUtils.nbJoursEntre(dudateChooser.getDate(), audateChooser.getDate())>=0)
			    		{
			    			ClientPF clientpf=	mapClientPF.get(comboclient.getSelectedItem());
			    			FamilleArticlePF famillearticle=null;
			    		
			    		
			    			
			    			Articles article=null;
			    			
			    			
				  		  if(radiobl.isSelected()==true)
				  		  {
				  			  String type=Constantes.TYPE_BON_LIVRAISON;
				  			listDetailFacturePF=detailFacturePfdao.findDetailBLEntreDeuxDate(dudateChooser.getDate(), audateChooser.getDate(),magasin.getDepot(),clientpf,famillearticle,article, TousFamille,TousArticle,type);
			    			afficher_tableFacturePF(listDetailFacturePF);  
				  		  }else if (radiofacture.isSelected()==true)
				  		  {	
				  			String type=Constantes.TYPE_FACTURE;
				  			listDetailFacturePF=detailFacturePfdao.findDetailBLEntreDeuxDate(dudateChooser.getDate(), audateChooser.getDate(),magasin.getDepot(),clientpf,famillearticle,article, TousFamille,TousArticle,type);
			    			afficher_tableFacturePF(listDetailFacturePF);  
				  		  }else
				  		  {
				  			String type="";
				  			listDetailFacturePF=detailFacturePfdao.findDetailBLEntreDeuxDate(dudateChooser.getDate(), audateChooser.getDate(),magasin.getDepot(),clientpf,famillearticle,article, TousFamille,TousArticle,type);
			    			afficher_tableFacturePF(listDetailFacturePF);
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
	    btnAfficher.setBounds(524, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1524, 51);
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
	     comboclient.addItem("");
	     combomagasin = new JComboBox();
	      combomagasin.setBounds(63, 16, 196, 24);
	      layeredPane_2.add(combomagasin);
	      combomagasin.setSelectedIndex(-1);
	      combomagasin.addItem("");
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
	    
	
	      
	       radiobl = new JRadioButton("BL");
	      radiobl.setBackground(SystemColor.activeCaption);
	      radiobl.setFont(new Font("Tahoma", Font.BOLD, 13));
	      radiobl.setBounds(17, 24, 71, 29);
	      add(radiobl);
	      group.add(radiobl);
	       radiofacture = new JRadioButton("Facture");
	      radiofacture.setBackground(SystemColor.activeCaption);
	      radiofacture.setFont(new Font("Tahoma", Font.BOLD, 13));
	      radiofacture.setBounds(107, 24, 107, 29);
	      add(radiofacture);
	      group.add(radiofacture);
	      
	       btnExporterExcel = new JButton("Exporter Excel");
	       btnExporterExcel.addActionListener(new ActionListener() {
	       	public void actionPerformed(ActionEvent arg0) {
	       		
	     

		  		

	      		

				
				  Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  if(magasin!=null) {
					  
					if(listDetailFacturePF.size()!=0)
 					{
						
						  if(radiobl.isSelected()==true)
				  		  {
								
							  String titre="Etat des Factures Autres Vente BL "+magasin.getLibelle(); 
							  String titrefeuille="Etat des Factures Autres Vente BL ";
							  
							  
							  ExporterTableVersExcel.tabletoexcelEtatFactureAutresVentesTypeFacture (table, titre,titrefeuille);
							  
				  		  }else if (radiofacture.isSelected()==true)
				  		  {	
				  			
							  String titre="Etat des Factures Autres Vente Facture "+magasin.getLibelle(); 
							  String titrefeuille="Etat des Factures Autres Vente Facture ";
							  
							  
							  ExporterTableVersExcel.tabletoexcelEtatFactureAutresVentesTypeFacture (table, titre,titrefeuille); 
							  
				  		  }else
				  		  {
				  			
							  String titre="Etat des Factures Autres Vente "+magasin.getLibelle(); 
							  String titrefeuille="Etat des Factures Autres Vente ";
							  
							  
							  ExporterTableVersExcel.tabletoexcelEtatFactureAutresVentes (table, titre,titrefeuille);
							  
							  
							  
				  		  }
			
					  
 					}
				  
			
				  }else {
				  
				  
				  JOptionPane.showMessageDialog(null,
				  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
				  ERROR_MESSAGE); return;
				  
				  
				  }
				 
	    	
	      		
	      		
	      		
	      		
	      	
			  		
			  		
			  		
			  		
			  		
			  	
	      		
	      		
	      		
	      		
	      		
	      	
	       		
	       		
	       		
	       	}
	       });
	      btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      btnExporterExcel.setBounds(513, 600, 123, 24);
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
						"Date BL", "N° BL","Type", "Client","Article","Famille Article", "Sous Famille", "Montant HT", "Montant TV","Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false
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
}
	
	
	
	void afficher_tableFacturePF(List<DetailFactureAutresVente> listDetailFacture)
	{
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
			if(radiobl.isSelected()==true)
			{
				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Date BL", "N° BL","Type", "Client","Article","Prix", "Montant HT", "Montant TV","Montant TTC"
						}
					) {
						boolean[] columnEditables = new boolean[] {
								false,false,false,false,false,false,false,false,false
						};
						
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					};
				table.setModel(modelefacture);
				int i=0;
				 
				while(i<listDetailFacture.size())
				{	
				DetailFactureAutresVente detailfacturepf=listDetailFacture.get(i);
				
				String datefacture=formatter.format(detailfacturepf.getFactureautresaente().getDateFacture());
			
					
					Object []ligne={datefacture,detailfacturepf.getFactureautresaente().getNumBl(),detailfacturepf.getFactureautresaente().getType(),detailfacturepf.getFactureautresaente().getClientPF().getNom(),detailfacturepf.getDesignation(),detailfacturepf.getPrixUnitaire(), detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(), detailfacturepf.getMontantTTC()};

					modelefacture.addRow(ligne);
					i++;
				}
			}else if(radiofacture.isSelected()==true)
			{

				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Date BL", "N° Facture","Type", "Client","Article","Prix", "Montant HT", "Montant TV","Montant TTC"
						}
					) {
						boolean[] columnEditables = new boolean[] {
								false,false,false,false,false,false,false,false,false
						};
						
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					};
				table.setModel(modelefacture);
				int i=0;
				 
				while(i<listDetailFacture.size())
				{	
				DetailFactureAutresVente detailfacturepf=listDetailFacture.get(i);
				
				String datefacture=formatter.format(detailfacturepf.getFactureautresaente().getDateFacture());
					
					Object []ligne={datefacture,detailfacturepf.getFactureautresaente().getNumFacture(),detailfacturepf.getFactureautresaente().getType(),detailfacturepf.getFactureautresaente().getClientPF().getNom(),detailfacturepf.getDesignation(),detailfacturepf.getPrixUnitaire(),detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(), detailfacturepf.getMontantTTC()};

					modelefacture.addRow(ligne);
					i++;
				}
			
				
			}else
			{
				


				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Date BL", "N° BL","N° Facture","Type", "Client","Article","Prix", "Montant HT", "Montant TV","Montant TTC"
						}
					) {
						boolean[] columnEditables = new boolean[] {
								false,false,false,false,false,false,false,false,false,false
						};
						
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					};
				table.setModel(modelefacture);
				int i=0;
				
				String NumFacture="";
				 
				while(i<listDetailFacture.size())
				{	
				DetailFactureAutresVente detailfacturepf=listDetailFacture.get(i);
				
				String datefacture=formatter.format(detailfacturepf.getFactureautresaente().getDateFacture());
				
				if(detailfacturepf.getFactureautresaente().getNumFacture()!=null)
				{
					NumFacture=detailfacturepf.getFactureautresaente().getNumFacture();
				}
					
					Object []ligne={datefacture,detailfacturepf.getFactureautresaente().getNumBl(),NumFacture,detailfacturepf.getFactureautresaente().getType(),detailfacturepf.getFactureautresaente().getClientPF().getNom(),detailfacturepf.getDesignation(),detailfacturepf.getPrixUnitaire(),detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(), detailfacturepf.getMontantTTC()};

					modelefacture.addRow(ligne);
					i++;
				}
			
				
			
				
			}
			
		
	
		
	
}
	}


