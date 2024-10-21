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
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
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


public class EtatFacturePFParArticle extends JLayeredPane implements Constantes{
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
	private List<FacturePF> listFacturePFTmp =new ArrayList<FacturePF>();
	private List<FacturePF> listFacturePFMAJ =new ArrayList<FacturePF>();
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	
	
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
private DetailFacturePFDAO detailFacturePfdao;
private ClientPFDAO clientpfdao;
private ClientDAO fournisseurdao;
private FamilleArticlesPFDAO familleArticleDAO;
private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
private   JDateChooser audateChooser = new JDateChooser();
	private  JComboBox comboclient = new JComboBox();
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	
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
	private JTextField txtcodearticle;
	 JComboBox combosousfamille = new JComboBox();
	  JComboBox comboArticle = new JComboBox();
	  JComboBox combomagasin = new JComboBox();
	  JComboBox combodepot = new JComboBox();
	  JButton ExporterExcel = new JButton("Exporter Excel");
	  private ImageIcon imgExcel;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public EtatFacturePFParArticle() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1617, 1062);
      
	
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
       familleArticleDAO=new FamilleArticlesPFDAOImpl();
    	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
    	listFamilleArticle=familleArticleDAO.findAll();
    	stockpfDAO=new StockPFDAOImpl();
    	detailFacturePfdao=new DetailFacturePFDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
		      
		    
		
		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			if(listDetailFacturePF.size()!=0)
			{
				Magasin magasin=listDetailFacturePF.get(0).getFacturePF().getMagasin();
				Map parameters = new HashMap();
				parameters.put("titre", titre);
				parameters.put("magasin", magasin.getLibelle());
				JasperUtils.imprimerEtatDetailFacturePFParArticle(listDetailFacturePF,parameters);
				
				
			}else
			{
				 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(524, 699, 112, 24);
		add(buttonvalider);
		  
		 
		
		JLabel lblConslterLesFactures = new JLabel("                 Etat Des Factures/BL Par Article");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(159, 11, 907, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 261, 1204, 427);
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
				"Date BL/Facture", "N° BL/Facture","Article", "Sous Famille","Quantite", "Montant HT", "Montant TV","Montant TTC"
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
	    		
	    		if(!combodepot.getSelectedItem().equals(""))
	    		{
	    			if(!combomagasin.getSelectedItem().equals(""))
	    			{
	    				
	    	    		if(dudateChooser.getDate()!=null && audateChooser.getDate()!=null)
	    	    		{
	    	    		if(	DateUtils.nbJoursEntre(dudateChooser.getDate(), audateChooser.getDate())>=0)
	    	    		{
	    	    			
	    	    			if(comboArticle.getSelectedItem().equals(""))
	    					{
	    						JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
	    						return;
	    					}else
	    					{
	    						
	    						
	    					Articles article=mapArticle.get(comboArticle.getSelectedItem())	;
	    					SousFamilleArticlePF sousfamillearticle=mapsousfamille.get(combosousfamille.getSelectedItem());
	    					Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
	    						
	    						if(article==null)
	    						{
	    							JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
		    						return;
	    						}else if(sousfamillearticle==null)
	    						{
	    							JOptionPane.showMessageDialog(null, "Veuillez Selectionner Sous Famille article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
		    						return;
	    						}else if(magasin==null)
	    						{
	    							JOptionPane.showMessageDialog(null, "Veuillez Selectionner le magasin SVP","Erreur",JOptionPane.ERROR_MESSAGE);
		    						return;
	    						}else
	    						{
	    							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    							String datedebutFacture=dateFormat.format(dudateChooser.getDate());
	    					  		  String dateFinFacture=dateFormat.format(audateChooser.getDate());
	    							titre="Les BL/Factures de l'Article : "+article.getLiblle() +" de Sous Famille : " +sousfamillearticle.getLiblle() +" Entre :"+datedebutFacture +" et "+dateFinFacture;
	    							
	    							listDetailFacturePF=detailFacturePfdao.listeDetailFacturePFByArticle(dudateChooser.getDate(), audateChooser.getDate(), article, sousfamillearticle, magasin);
	    							
	    							afficher_tableDetailFacturePF(listDetailFacturePF);
	    								
	    							
	    							
	    							
	    							
	    							
	    							
	    						}
	    					
	    						
	    						
	    					}
	    	    			
	    	    			
	    	    		}else
	    	    		{
	    	    			JOptionPane.showMessageDialog(null, "La date de début doit etre supérieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	    	    			return;
	    	    		}
	    	    		
	    	    		}else 
	    	    		{
	    	    			
	    	    			JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	    	    			return;
	    	    		
	    	    		}
	    				
	    				
	    			}
	    			
	    			
	    			
	    		}
	
	    		
	    		
	    		
	    		
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(432, 200, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(20, 54, 1194, 123);
	    add(layeredPane_2);
	    
	    JLabel lblDateFacture = new JLabel("Du :");
	    lblDateFacture.setBounds(623, 26, 40, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     dudateChooser = new JDateChooser();
	     dudateChooser.setBounds(691, 26, 139, 26);
	     layeredPane_2.add(dudateChooser);
	     dudateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     		
	     		if(dudateChooser.getDate()!=null)
	     		{
	     			comboclient.setSelectedItem("");
	     			
	     			
	     		}
	     		
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
	     lblAu.setBounds(850, 26, 40, 24);
	     layeredPane_2.add(lblAu);
	     
	      audateChooser = new JDateChooser();
	     audateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {
	     		
	     		if(audateChooser.getDate()!=null)
	     		{
	     			comboclient.setSelectedItem("");
	     			
	     			
	     		}
	     		
	     		
	     	}
	     });
	     audateChooser.setLocale(Locale.FRANCE);
	     audateChooser.setDateFormatString("dd/MM/yyyy");
	     audateChooser.setBounds(897, 26, 191, 26);
	     layeredPane_2.add(audateChooser);
	     
	     JLabel label = new JLabel("Famille Article :");
	     label.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     label.setBounds(10, 69, 87, 24);
	     layeredPane_2.add(label);
	     
	     JComboBox combofamille = new JComboBox();
	     combofamille.addItemListener(new ItemListener() {
	     	public void itemStateChanged(ItemEvent e) {

           		
          		 if(e.getStateChange() == ItemEvent.SELECTED)
{
          			 if(!combofamille.getSelectedItem().equals(""))
          			 {
          				 
          					FamilleArticlePF famille=mapfamille.get(combofamille.getSelectedItem());
		if(famille!=null)
		{
			 combosousfamille.removeAllItems();
			 combosousfamille.addItem("");
			// combosousfamille.addItem(TOUS);
			listSousFamilleArticle=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePF(famille.getId());
			for(int i=0;i<listSousFamilleArticle.size();i++)
			{
			SousFamilleArticlePF sousfamille=listSousFamilleArticle.get(i);
			combosousfamille.addItem(sousfamille.getLiblle());
			mapsousfamille.put(sousfamille.getLiblle(), sousfamille);
				
			}
			
		}else
		{
			 combosousfamille.removeAllItems();
		}
			
          				 
          			 }else
          			 {
          				//combosousfamille.removeAllItems(); 
          			 }
          			 
          			
          	 
          			
          			 
}
	 
          		
          		
          	
	     		
	     		
	     		
	     		
	     		
	     	}
	     });
	     combofamille.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		
	     		
	     		
	     		
	     		
	     	}
	     });
	     combofamille.setSelectedIndex(-1);
	     combofamille.setBounds(97, 70, 183, 24);
	     layeredPane_2.add(combofamille);
	     
	     JLabel label_1 = new JLabel("Sous Famille :");
	     label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     label_1.setBounds(308, 66, 87, 24);
	     layeredPane_2.add(label_1);
	     
	      combosousfamille = new JComboBox();
	      combosousfamille.addItemListener(new ItemListener() {
	      	public void itemStateChanged(ItemEvent e) {
	      		

        		

       		 if(e.getStateChange() == ItemEvent.SELECTED)
	 		 {
       			 if(!combosousfamille.getSelectedItem().equals(""))
       			 {
       				
       				 SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
       				 Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
       				 if(magasin!=null)
       				 {
       					 if(sousfamille!=null)
	        			 {
	        				 comboArticle.removeAllItems();
	        				 comboArticle.addItem("");
	        				
	        				 listArticleStockPF=stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
	        				 for(int i=0;i<listArticleStockPF.size();i++)
	        				 {
	        					Articles article= listArticleStockPF.get(i).getArticles();
	        					comboArticle.addItem(article.getLiblle());
	        					 mapArticle.put(article.getLiblle(), article);
	        					 
	        				 }
	        				 
	        				 
	        				 
	        			 }else
	        			 {
	        				 comboArticle.removeAllItems();
	        				 txtcodearticle.setText("");
	        			 }
       				  
       					 
       				 }
       			
       			 }else
       			 {
       				 comboArticle.removeAllItems();
       				 txtcodearticle.setText("");
       			 }
       			 
       		
       			 
	 		 }
       		
       		
       		
       	
	      		
	      	}
	      });
	     combosousfamille.setBounds(405, 70, 176, 24);
	     layeredPane_2.add(combosousfamille);
	     
	     JLabel label_2 = new JLabel("Code Article :");
	     label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     label_2.setBounds(622, 63, 82, 26);
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
	     txtcodearticle.setColumns(10);
	     txtcodearticle.setBounds(691, 63, 139, 26);
	     layeredPane_2.add(txtcodearticle);
	     
	     JLabel label_3 = new JLabel("Libelle :");
	     label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     label_3.setBounds(854, 66, 57, 26);
	     layeredPane_2.add(label_3);
	     
	      comboArticle = new JComboBox();
	      comboArticle.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		if(comboArticle.getSelectedIndex()!=-1)
			 		{
			 			if(!comboArticle.getSelectedItem().equals(""))
				 		{
			 				
			 				Articles article=mapArticle.get(comboArticle.getSelectedItem());
			 				txtcodearticle.setText(article.getCodeArticle());
			 				
			 				
				 		}
			 		}
	      		
	      	}
	      });
	     comboArticle.setSelectedIndex(-1);
	     comboArticle.setBounds(897, 66, 258, 27);
	     layeredPane_2.add(comboArticle);
	     AutoCompleteDecorator.decorate(comboArticle);
         comboArticle.setSelectedIndex(-1);
         
         JLabel label_4 = new JLabel("Depot :");
         label_4.setBounds(10, 33, 56, 24);
         layeredPane_2.add(label_4);
         label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
         
          combodepot = new JComboBox();
         combodepot.addItemListener(new ItemListener() {
         	public void itemStateChanged(ItemEvent e) {

		  		

 	 			
   	 		 if(e.getStateChange() == ItemEvent.SELECTED)
   	 		 {
   	 			int i=0;
   	 		
   	 				if(!combodepot.getSelectedItem().equals(""))
    			{
    				Depot depot=mapDepot.get(combodepot.getSelectedItem());
    				if(depot!=null)
    				{
    					listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_PF);
		     				if(listMagasin.size()!=0)
		     				{
		     					combomagasin.removeAllItems();
		     					combomagasin.addItem("");
		     					while(i<listMagasin.size())
 		     				{
 		     					Magasin magasin=listMagasin.get(i);
 		     					combomagasin.addItem(magasin.getLibelle());
 		     					mapMagasin.put(magasin.getLibelle(), magasin);
 		     					i++;
 		     				}
		     				}else
		     				{
		     					combomagasin.removeAllItems();
		     					
		     				}
		     				
		     				
		     				
		     			
		     				
		     			}else
		     			{
		     				combomagasin.removeAllItems();
		     				
		     			}
		     				
    					
    				}
    				
    				
   	 		 }
   	 	
	
         		
         	}
         });
         combodepot.setBounds(97, 29, 183, 24);
         layeredPane_2.add(combodepot);
         combodepot.setSelectedIndex(-1);
         
          combomagasin = new JComboBox();
         combomagasin.setBounds(405, 29, 183, 24);
         layeredPane_2.add(combomagasin);
         combomagasin.setSelectedIndex(-1);
         
         JLabel label_6 = new JLabel("Magasin :");
         label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
         label_6.setBounds(313, 28, 56, 24);
         layeredPane_2.add(label_6);
	 
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		initialiser();
	     		

	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(564, 201, 106, 23);
	     add(button);
	     
	     JLabel label_5 = new JLabel("Magasin :");
	     label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     label_5.setBounds(-109, 137, 56, 24);
	     add(label_5);
	   
		  if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
		     	 combodepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		Magasin magasin=depotdao.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
		     		if(magasin!=null)
		     		{
		     			if(depot.getId()!=magasin.getDepot().getId())
			     		{
			     			combodepot.addItem(depot.getLibelle());
				     		
				     		mapDepot.put(depot.getLibelle(), depot);
				     	
				     		
			     			
			     		}
		     		}else
		     		{
		     			combodepot.addItem(depot.getLibelle());
			     		
			     		mapDepot.put(depot.getLibelle(), depot);
			     	
			     		
		     		}
		     		k++;
		     		
		     	}
		      
		  }else
		  {
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		 
		  
		  combodepot.setSelectedIndex(-1);
		  
		   ExporterExcel = new JButton("Exporter Excel");
		   ExporterExcel.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		
		   		

	      		

		  		

	      		

				
				  Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  if(magasin!=null) {
					  
					if(listDetailFacturePF.size()!=0)
 					{
						
					  String
					  titre="Etat des Factures PF Par Article "+mapMagasin.get(combomagasin.getSelectedItem()).getLibelle(); 
					  String titrefeuille="Etat des Factures PF Article ";
					  
					  
					  ExporterTableVersExcel.tabletoexcelEtatFacturePFParArticle (table, titre,titrefeuille);
					  
 					}
				  
			
				  }else {
				  
				  
				  JOptionPane.showMessageDialog(null,
				  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
				  ERROR_MESSAGE); return;
				  
				  
				  }
				 
	    	
	      		
	      		
	      		
	      		
	      	
			  		
			  		
			  		
			  		
			  		
			  	
	      		
	      		
	      		
	      		
	      		
	      	
		   		
		   		
		   		
		   		
		   	}
		   });
		  ExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  ExporterExcel.setBounds(673, 700, 142, 24);
		  ExporterExcel.setIcon(imgExcel);
		  add(ExporterExcel);
	     int p=0;
	      while(p<listFamilleArticle.size())
	      {
	    	  
	    	  FamilleArticlePF famillearticle=listFamilleArticle.get(p);
	    	  combofamille.addItem(famillearticle.getLiblle());
	    	 
	    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
	    	  p++;
	      }	

		
		}
	


	void initialiser()
	{
comboclient.setSelectedItem("");
dudateChooser.setCalendar(null);
 audateChooser.setCalendar(null);
		
	}

	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date BL/Facture", "N° BL/Facture","Article", "Sous Famille","Quantite", "Montant HT", "Montant TV","Montant TTC"
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
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
		table.getColumnModel().getColumn(6).setPreferredWidth(114);
		table.getColumnModel().getColumn(7).setPreferredWidth(136);
		
}
	
	
	
	
	
	
	void afficher_tableDetailFacturePF(List<DetailFacturePF> listDeatilFacture)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Date BL/Facture", "N° BL/Facture","Article", "Sous Famille","Quantite", "Montant HT", "Montant TV","Montant TTC"
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
		 
		while(i<listDeatilFacture.size())
		{	
		DetailFacturePF detailfacturepf=listDeatilFacture.get(i);
		
		String datefacture=formatter.format(detailfacturepf.getFacturePF().getDateFacture());
		
		
			if(detailfacturepf.getFacturePF().getNumFacture()==null)
			{
				Object []ligne={datefacture,detailfacturepf.getFacturePF().getNumBl(),detailfacturepf.getArticle().getLiblle(),detailfacturepf.getSousFamille().getLiblle(),detailfacturepf.getQuantite(),detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(),detailfacturepf.getMontantTTC()};
				modelefacture.addRow(ligne);
			}else
			{
				Object []ligne={datefacture,detailfacturepf.getFacturePF().getNumFacture(),detailfacturepf.getArticle().getLiblle(),detailfacturepf.getSousFamille().getLiblle(),detailfacturepf.getQuantite(),detailfacturepf.getMontantHT(),detailfacturepf.getMontantTVA(),detailfacturepf.getMontantTTC()};
				modelefacture.addRow(ligne);
			}

			
			i++;
		}
}
	}


