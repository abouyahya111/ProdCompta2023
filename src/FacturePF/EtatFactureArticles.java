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


public class EtatFactureArticles extends JLayeredPane implements Constantes{
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
	 JRadioButton radiobl = new JRadioButton("BL");
	 JRadioButton radiofacture = new JRadioButton("Facture");
	 ButtonGroup group = new ButtonGroup();
	 JCheckBox prixmoyen = new JCheckBox("Calcule prix Moyen");
	 
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public EtatFactureArticles() {
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
				  		  BigDecimal prixmoyencalculer=BigDecimal.ZERO;
				  		  BigDecimal TotalQtt=BigDecimal.ZERO;
				  		 BigDecimal TotalHT=BigDecimal.ZERO;
				  		Articles article=null;
		    			if(comboarticle.getSelectedIndex()!=-1)
		    			{
		    				if(!comboarticle.getSelectedItem().equals(TOUS))
			    			{
			    				article=mapArticle.get(comboarticle.getSelectedItem());
			    			}
		    				
		    			}
				  		  
				  		  
				  		  
						
						if(listDetailFacturePF.size()!=0)
				{
						
							
					
					Map parameters = new HashMap();
					if(article!=null && prixmoyen.isSelected()==true)
					{
						
						
						for(int i=0;i<listDetailFacturePF.size();i++)
						{
							
							DetailFacturePF detailFacturePF =listDetailFacturePF.get(i);
							
							TotalQtt=TotalQtt.add(detailFacturePF.getQuantite());
							TotalHT=TotalHT.add(detailFacturePF.getMontantHT());
							
							
						}
						
						if(TotalQtt.compareTo(BigDecimal.ZERO)>0)
						{
							
							prixmoyencalculer=TotalHT.divide(TotalQtt, 6, RoundingMode.HALF_UP);
							
						}
						
						
						
					}
					parameters.put("prixmoyen", prixmoyencalculer);
					parameters.put("magasin", magasin.getLibelle());
					parameters.put("datedebut",datedebutFacture);
					parameters.put("dateFin", dateFinFacture);
					JasperUtils.imprimerEtatFactureArticle(listDetailFacturePF,parameters);
				
					
					
					
				}
					
			}}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(666, 599, 112, 24);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("                       Etat Facture Article");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(381, 11, 791, 35);
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
				"Date ", "N� Facture","Article","Famille Article","Client","Quantite", "prix","Montant HT", "Montant TV","Montant TTC"
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
			    			
				  		  if(radiobl.isSelected()==true)
				  		  {
				  			  
				  			  if(prixmoyen.isSelected()==true)
				  			  {
				  				  
				  				if(article!=null)  
				  				{
				  					
				  					String type=Constantes.TYPE_BON_LIVRAISON;
						  			listDetailFacturePF=detailFacturePfdao.findDetailBLEntreDeuxDateSansGratuite (dudateChooser.getDate(), audateChooser.getDate(),magasin,clientpf,famillearticle,article, TousFamille,TousArticle,type);
					    			afficher_tableFacturePF(listDetailFacturePF); 
				  					
				  					
				  				}else
				  				{
				  					
				  					JOptionPane.showMessageDialog(null, "Veuillez selectionner l'Article SVP pour calculer le prix Moyen !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					    			return;
				  					
				  					
				  				}
				  				  
				  				 
				  				  
				  			  }else
				  			  {
				  				String type=Constantes.TYPE_BON_LIVRAISON;
					  			listDetailFacturePF=detailFacturePfdao.findDetailBLEntreDeuxDate(dudateChooser.getDate(), audateChooser.getDate(),magasin,clientpf,famillearticle,article, TousFamille,TousArticle,type);
				    			afficher_tableFacturePF(listDetailFacturePF);   
				  				  
				  			  }
				  			
			    			
			    			
			    			
			    			
				  		  }else if (radiofacture.isSelected()==true)
				  		  {	
				  			  if(prixmoyen.isSelected()==true)
				  			  {
				  				  
				  				if(article!=null)  
				  				{
				  					
				  					String type=Constantes.TYPE_FACTURE;
						  			listDetailFacturePF=detailFacturePfdao.findDetailBLEntreDeuxDateSansGratuite(dudateChooser.getDate(), audateChooser.getDate(),magasin,clientpf,famillearticle,article, TousFamille,TousArticle,type);
					    			afficher_tableFacturePF(listDetailFacturePF); 	
				  					
				  					
				  					
				  				}else
				  				{
				  					
				  					JOptionPane.showMessageDialog(null, "Veuillez selectionner l'Article SVP pour calculer le prix Moyen !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					    			return;
				  					
				  					
				  				}  
				  				  
				  				  
				  				  
				  			  }else
				  			  {
				  				  
				  				String type=Constantes.TYPE_FACTURE;
					  			listDetailFacturePF=detailFacturePfdao.findDetailBLEntreDeuxDate(dudateChooser.getDate(), audateChooser.getDate(),magasin,clientpf,famillearticle,article, TousFamille,TousArticle,type);
				    			afficher_tableFacturePF(listDetailFacturePF);  
				  			  }
				  			  
				  			
			    			
				  		  }else
				  		  {
				  			  if(prixmoyen.isSelected()==true)
				  			  {
				  				  
				  				if(article!=null)  
				  				{
				  					
				  					String type="";
						  			listDetailFacturePF=detailFacturePfdao.findDetailBLEntreDeuxDateSansGratuite(dudateChooser.getDate(), audateChooser.getDate(),magasin,clientpf,famillearticle,article, TousFamille,TousArticle,type);
					    			afficher_tableFacturePF(listDetailFacturePF);
				  					
				  					
				  					
				  					
				  				}else
				  				{
				  					
				  					JOptionPane.showMessageDialog(null, "Veuillez selectionner l'Article SVP pour calculer le prix Moyen !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					    			return;
				  					
				  					
				  				} 
				  				  
				  				  
				  			  }else
				  			  {
				  				  
				  				String type="";
					  			listDetailFacturePF=detailFacturePfdao.findDetailBLEntreDeuxDate(dudateChooser.getDate(), audateChooser.getDate(),magasin,clientpf,famillearticle,article, TousFamille,TousArticle,type);
				    			afficher_tableFacturePF(listDetailFacturePF); 
				  				  
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
	     
	     JLabel lblClient_1 = new JLabel("Client  :");
	     lblClient_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     lblClient_1.setBounds(671, 15, 70, 24);
	     layeredPane_2.add(lblClient_1);
	     
	      comboclient = new JComboBox();
	     comboclient.addItemListener(new ItemListener() {
	     	public void itemStateChanged(ItemEvent e) {
	     		
	     	}
	     });
	  
	     comboclient.setBounds(736, 15, 206, 24);
	     
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
	     
	     JLabel label = new JLabel("Famille Article :");
	     label.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     label.setBounds(952, 16, 87, 24);
	     layeredPane_2.add(label);
	     combomagasin = new JComboBox();
	      combomagasin.setBounds(63, 16, 173, 24);
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
	     comboparfamille.setBounds(1049, 16, 139, 24);
	     layeredPane_2.add(comboparfamille);
	    
	     JLabel labelarticle = new JLabel("Article :");
	     labelarticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     labelarticle.setBounds(1198, 15, 70, 24);
	     layeredPane_2.add(labelarticle);
	     
	      comboarticle = new JComboBox();
	     comboarticle.setBounds(1250, 16, 235, 24);
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
	      comboparfamille.setSelectedIndex(-1);
	      
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
	      
	       prixmoyen = new JCheckBox("Calcule prix Moyen");
	      prixmoyen.setFont(new Font("Tahoma", Font.BOLD, 11));
	      prixmoyen.setBounds(224, 27, 162, 23);
	      add(prixmoyen);
	      
	      JButton btnExporterExcel = new JButton("Exporter Excel");
	      btnExporterExcel.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      		

				
				  Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  if(magasin!=null) {
				  
				  String
				  titre="Etat Facture Article "+mapMagasin.get(combomagasin.getSelectedItem()).getLibelle(); 
				  String titrefeuille="Etat Facture Article PF ";
				  ExporterTableVersExcel.tabletoexcelEtatFactureArticle(table, titre,titrefeuille);
				  
				  }else {
				  
				  
				  JOptionPane.showMessageDialog(null,
				  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
				  ERROR_MESSAGE); return;
				  
				  
				  }
				 
	    	
	      		
	      		
	      		
	      		
	      	}
	      });
	      btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      btnExporterExcel.setBounds(801, 600, 112, 24);
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
						"Date ", "N� Facture","Article","Famille Article","Client","Quantite", "prix","Montant HT", "Montant TV","Montant TTC"
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
		table.getColumnModel().getColumn(8).setPreferredWidth(136);
		table.getColumnModel().getColumn(9).setPreferredWidth(114);
		
}
	
	
	
	void afficher_tableFacturePF(List<DetailFacturePF> listDetailFacture)
	{
		
	
			
			if(radiobl.isSelected()==true)
			{
				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Date ", "N� BL","Article","Famille Article","Sous Famille","Client","Quantite", "prix","Montant HT", "Montant TV","Montant TTC"
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
				int i=0;
				 
				while(i<listDetailFacture.size())
				{	
				DetailFacturePF detailfacturepf=listDetailFacture.get(i);
				
				Date datefacture=detailfacturepf.getFacturePF().getDateFacture();
					
					Object []ligne={datefacture,detailfacturepf.getFacturePF().getNumBl(),detailfacturepf.getArticle().getLiblle(), detailfacturepf.getSousFamille().getFamileArticlePF().getLiblle(),detailfacturepf.getSousFamille().getLiblle(),detailfacturepf.getFacturePF().getClientPF().getNom(),detailfacturepf.getQuantite(),detailfacturepf.getPrixUnitaire(), detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(), detailfacturepf.getMontantTTC()};

					modelefacture.addRow(ligne);
					i++;
				}
			}else if(radiofacture.isSelected()==true)
			{

				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Date ", "N� Facture","Article","Famille Article","Sous Famille","Client","Quantite", "prix","Montant HT", "Montant TV","Montant TTC"
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
				int i=0;
				 
				while(i<listDetailFacture.size())
				{	
				DetailFacturePF detailfacturepf=listDetailFacture.get(i);
				
				Date datefacture=detailfacturepf.getFacturePF().getDateFacture();
					
				Object []ligne={datefacture,detailfacturepf.getFacturePF().getNumFacture(),detailfacturepf.getArticle().getLiblle(), detailfacturepf.getSousFamille().getFamileArticlePF().getLiblle(),detailfacturepf.getSousFamille().getLiblle(),detailfacturepf.getFacturePF().getClientPF().getNom(),detailfacturepf.getQuantite(),detailfacturepf.getPrixUnitaire(), detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(), detailfacturepf.getMontantTTC()};

					modelefacture.addRow(ligne);
					i++;
				}
			
				
			}else
			{
				


				
				modelefacture =new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Date ", "N� Facture","Article","Famille Article","Sous Famille","Client","Quantite", "prix","Montant HT", "Montant TV","Montant TTC"
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
				int i=0;
				 
				while(i<listDetailFacture.size())
				{	
				DetailFacturePF detailfacturepf=listDetailFacture.get(i);
				
				Date datefacture=detailfacturepf.getFacturePF().getDateFacture();
					if(detailfacturepf.getFacturePF().getNumFacture()!=null)
					{
						Object []ligne={datefacture,detailfacturepf.getFacturePF().getNumFacture(),detailfacturepf.getArticle().getLiblle(), detailfacturepf.getSousFamille().getFamileArticlePF().getLiblle(), detailfacturepf.getSousFamille().getLiblle(),detailfacturepf.getFacturePF().getClientPF().getNom(),detailfacturepf.getQuantite(),detailfacturepf.getPrixUnitaire(), detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(), detailfacturepf.getMontantTTC()};

						modelefacture.addRow(ligne);
					}else
					{
						Object []ligne={datefacture,detailfacturepf.getFacturePF().getNumBl(),detailfacturepf.getArticle().getLiblle(), detailfacturepf.getSousFamille().getFamileArticlePF().getLiblle(),detailfacturepf.getSousFamille().getLiblle(),detailfacturepf.getFacturePF().getClientPF().getNom(),detailfacturepf.getQuantite(),detailfacturepf.getPrixUnitaire(), detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(), detailfacturepf.getMontantTTC()};

						modelefacture.addRow(ligne);
						
					}
				
					i++;
				}
			
				
			
				
				
			}
			
		
		
		
	
}
	}


