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
import java.text.ParseException;
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
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureAchatDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.ListTvaDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoImplManager.TypeVenteDAOImpl;
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
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailPrixArticleDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.ListTvaDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockPFDAO;
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
import dao.entity.DetailPrixArticle;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.ListTva;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
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


public class ConsulterFactureAchat extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;

	private JXTable  tableArticle = new JXTable();
	private JXTable table = new JXTable();
	private List<Fournisseur> listFournisseur =new ArrayList<Fournisseur>();
	private List<ListTva> listTva =new ArrayList<ListTva>();
	private List<DetailFactureAchat> listDetailFactureAchat =new ArrayList<DetailFactureAchat>();
	private List<DetailFactureAchat> listDetailFactureAchatTMP =new ArrayList<DetailFactureAchat>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<Articles> listArticleStockPF =new ArrayList<Articles>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FactureAchat> listFactureAchat =new ArrayList<FactureAchat>();
	private List<FactureAchat> listFactureAchatTmp =new ArrayList<FactureAchat>();
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	private Map< String, ListTva> mapTVA= new HashMap<>();
	private Map< BigDecimal, String> mapTVAparTaux= new HashMap<>();
	TransferStockPF transferStock ;
	private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	private	List<DetailPrixArticle> listDetailPrixArticle= new ArrayList<DetailPrixArticle>();
	private TransferStockPFDAO transferStockPFDAO;
	private DetailTransferProduitFiniDAO detailTransferStockPFdao;
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, Fournisseur> mapFournisseur= new HashMap<>();
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	
	private  JButton btninitialiser = new JButton();
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	 JComboBox combosousfamille;
	 private JComboBox comboArticle;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FactureAchatDAO factureAchatdao;
	private FactureAchat factureAchat;
private DetailFactureAchatDAO detailFactureAchatdao;
private DetailPrixArticleDAO detailPrixArticleDAO;
private FournisseurDAO fournisseurdao;
	private  JComboBox combofamille ;
	private JTextField txtcodearticle;
	ChargeProduction chargeproduction;
	private JTextField txtquantite;
	private JTextField txtnumfacture;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	 JComboBox combomagasin = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	 private JDateChooser dateChooserfacture;
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private JTextField txtPrix;
	private JTextField txttotalmontantTTC;
	private JTextField txttotalquantite;
	private  JButton btnModifier ;
	 private JButton btnSupprimer = new JButton();
	 private  JComboBox comboFournisseur = new JComboBox();
	private JTextField txtparnumfacture;
	private JRadioButton rdbtnDateFacture;
	private JDateChooser pardateChooser;
	private  JComboBox combopardepot;
	private JTextField txttotalmontantTVA;
	private JTextField txttotalmontantHT;
	private JComboBox comboparfournisseur = new JComboBox();
	private StockPFDAO stockpfDAO;
	private FamilleArticlesPFDAO familleArticleDAO;
	private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
	private JTextField txtreduction;
	private   JComboBox comboparsousfamille ;
	private  JComboBox comboparfamille ;
	private  JRadioButton radiosanstva = new JRadioButton("Sans TVA");
	private JRadioButton radioavectva = new JRadioButton("Avec TVA");
	private  JComboBox combotva = new JComboBox();
	private ListTvaDAO listtvaDAO ;
	private JTextField txtquantitepacket= new JTextField();;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterFactureAchat() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1478, 1154);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
           
             
             
             
            utilisateur=AuthentificationView.utilisateur;
            detailTransferStockPFdao=new DetailTransferProduitFiniDAOImpl();
            transferStockPFDAO=new TransferStockPFDAOImpl();
            
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	factureAchatdao=new FactureAchatDAOImpl();
         	detailFactureAchatdao=new DetailFactureAchatDAOImpl();
         	
         	articleDAO=new ArticlesDAOImpl();
         	stockpfDAO=new StockPFDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         
         	fournisseurdao=new FournisseurDAOImpl();
         	familleArticleDAO=new FamilleArticlesPFDAOImpl();
         	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
         	listFamilleArticle=familleArticleDAO.findAll();
         	  listtvaDAO=new ListTvaDAOImpl();
         	 detailPrixArticleDAO=new DetailPrixArticleDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
        tableArticle.setSortable(false);
        tableArticle.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		boolean trouve=false;
       		txtquantitepacket.setText("");
       		Articles article=mapArticle.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
       		combofamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0));
       		combosousfamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
       		comboArticle.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2));
       	
       		txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());
       		txtreduction.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString());
       		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
       	
/*
       		if(article.getCentreCout2()!=null)
       		{
       			if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
       			{
       				trouve=true;
       			}
       		
       		}
       	
       		if(trouve==true)
       		{
       			if(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).compareTo(article.getConditionnement())<0)
       			{
       				txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());
       			}else
       			{
       				txtquantitepacket.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).divide(article.getConditionnement(),6,RoundingMode.HALF_UP)+"");
       				
       				String[] part=txtquantitepacket.getText().split("\\.");
       				
       				txtquantitepacket.setText(part[0]);
       				txtquantite.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString()).subtract(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()))+"");
       			}
       			
       			txtPrix.setText(new BigDecimal( tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()).multiply(article.getConditionnement()).setScale(2, RoundingMode.DOWN)+"");
       			
       			
       		}else
       		{*/
       			txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());	
       			txtPrix.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString())+"");
       			
       /*		}
       		
       		if(trouve==true)
       		{
       			txtreduction.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString());
           		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
           		//txtmontant.setText((new BigDecimal(txtquantite.getText()).multiply(new BigDecimal(txtPrix.getText()))).add(new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()).multiply(new BigDecimal(txtPrix.getText())))+"");
           		
       		}else
       		{*/
       			txtreduction.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString());
           		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
           		//txtmontant.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 6).toString());
           		
       		/*}*/
       		
       		
       		DetailFactureAchat detailfactureachat=listDetailFactureAchat.get(tableArticle.getSelectedRow());
       		if(detailfactureachat.getTva().setScale(2).equals(BigDecimal.ZERO.setScale(2)))
       		{
       			radiosanstva.setSelected(true);
       			combotva.setVisible(false);
       			combotva.setSelectedIndex(-1);
       			
       		}else
       		{
       			radioavectva.setSelected(true);
       			combotva.setVisible(true);
       			
       			combotva.setSelectedItem(mapTVAparTaux.get(detailfactureachat.getTva().setScale(2).divide(new BigDecimal(100))));
       			
       		}
     
       		btnAjouter.setEnabled(false);
       		
       		
       		 	}
       });
        
       tableArticle.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC"
       	}
       ));
				  		
       tableArticle.setShowVerticalLines(false);
       tableArticle.setSelectionBackground(new Color(51, 204, 255));
       tableArticle.setRowHeightEnabled(true);
       tableArticle.setBackground(new Color(255, 255, 255));
       tableArticle.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableArticle.setColumnControlVisible(true);
       tableArticle.setForeground(Color.BLACK);
       tableArticle.setGridColor(new Color(0, 0, 255));
       tableArticle.setAutoCreateRowSorter(true);
       tableArticle.setBounds(2, 27, 411, 198);
       tableArticle.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableArticle);
				  		     	scrollPane.setBounds(10, 642, 1375, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 614, 1458, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 489, 1458, 93);
				  		     	add(layeredPane);
		
		  JLabel lblCodeArticle = new JLabel("Code Article :");
		  lblCodeArticle.setBounds(516, 12, 74, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel lbllibelle = new JLabel("Libelle :");
		  lbllibelle.setBounds(691, 12, 45, 26);
		  layeredPane.add(lbllibelle);
		  lbllibelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		      
		      txtcodearticle = new JTextField();
		     
		      util.Utils.copycoller(txtcodearticle);
		      txtcodearticle.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {
	     			if(e.getKeyCode()==e.VK_ENTER)
			      		{
	     				
	     					
			      			if(!txtcodearticle.getText().equals(""))
			      			{
			      				
			      				Articles article=mapCodeArticle.get(txtcodearticle.getText());
					    		
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
		      txtcodearticle.setBounds(588, 12, 93, 26);
		      layeredPane.add(txtcodearticle);
		    
		   
		       comboArticle = new JComboBox();
		       comboArticle.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {

		       		txtquantite.setText("");
	     	 		txtquantitepacket.setText("");
		     	 	if(comboArticle.getSelectedIndex()!=-1)
			 		{
			 			if(!comboArticle.getSelectedItem().equals(""))
				 		{
				 			Articles article=mapArticle.get(comboArticle.getSelectedItem());
				 			txtcodearticle.setText(article.getCodeArticle());
				 		
				 			if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
				 			{
				 				txtquantite.setEnabled(true);
				 				txtquantitepacket.setEnabled(true);
				 				
				 				if(article.getPrixAchat()!=null)
  				 				{
  				 					txtPrix.setText(article.getPrixAchat()+"");
  				 				}else
  				 				{
  				 					txtPrix.setText(new BigDecimal(0)+"");
  				 				}
				 				
				 				
				 			}else if(article.getCentreCout2().equals(Constantes.ARTICLE_PIECE))
				 			{
				 				
				 				txtquantite.setEnabled(true);
				 				txtquantitepacket.setEnabled(false);
				 				
				 				if(article.getPrixAchat()!=null)
  				 				{
  				 					txtPrix.setText(article.getPrixAchat()+"");
  				 				}else
  				 				{
  				 					txtPrix.setText(new BigDecimal(0)+"");
  				 				}
				 			}
				 			
				 			
				 		  				 			
				 		}else
				 		{
				 			txtcodearticle.setText("");
				 			
				 		}
				 	
			 		}
			 		
			 		
		     	 		
		     	 	
	       	}
		       });
		      comboArticle.setBounds(736, 12, 252, 27);
		      layeredPane.add(comboArticle);
		      AutoCompleteDecorator.decorate(comboArticle);
		     	
		      
		      JLabel lblQuantit = new JLabel("Quantite :");
		      lblQuantit.setBounds(0, 59, 62, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      util.Utils.copycoller(txtquantite);
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			
		     			
		     		
		      		
		      	}
		      });
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(66, 59, 101, 26);
		      layeredPane.add(txtquantite);
		      
		      JLabel lblPrix = new JLabel("Prix  :");
		      lblPrix.setBounds(188, 59, 45, 26);
		      layeredPane.add(lblPrix);
		      
		      txtPrix = new JTextField();
		      txtPrix.setColumns(10);
		      txtPrix.setBounds(224, 60, 87, 26);
		      layeredPane.add(txtPrix);
		      
		      JLabel lblRemise = new JLabel("Remise :");
		      lblRemise.setBounds(321, 60, 57, 26);
		      layeredPane.add(lblRemise);
		      
		      txtreduction = new JTextField();
		      txtreduction.setColumns(10);
		      txtreduction.setBounds(375, 59, 101, 26);
		      layeredPane.add(txtreduction);
		      
		      JLabel label_8 = new JLabel("%");
		      label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		      label_8.setBounds(479, 59, 26, 26);
		      layeredPane.add(label_8);
		     
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal solde=BigDecimal.ZERO;
				if(txtnumfacture.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Erreur Num Facture !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(dateChooserfacture.getDate()==null)
				{

					JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
				}else if(combodepot.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
				}else if(combomagasin.getSelectedIndex()==-1 || combomagasin.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
			
					
				}else if(comboFournisseur.getSelectedIndex()==-1)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez choisir le Fournisseur SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
				}
				
				
				else if(listDetailFactureAchat.size()==0)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer les articles à facturé SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else
				{
					
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				Magasin magasin =mapMagasin.get(combomagasin.getSelectedItem());
				Fournisseur fournisseur=mapFournisseur.get(comboFournisseur.getSelectedItem());
				 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier La facture  ?", 
							"Satisfaction", JOptionPane.YES_NO_OPTION);
					 
					if(reponse == JOptionPane.YES_OPTION )
						
						
					{
						FactureAchat factureAchatTmp=listFactureAchat.get(table.getSelectedRow());
						factureAchatTmp.setNumFacture(txtnumfacture.getText());
						factureAchatTmp.setFournisseur(fournisseur);
						factureAchatTmp.setDepot(depot);
						factureAchatTmp.setMagasin(magasin);
						factureAchatTmp.setDateFacture(dateChooserfacture.getDate());
						factureAchatTmp.setEtat(Constantes.ETAT_NON_REGLE);
						factureAchatTmp.setType(Constantes.TYPE_BON_LIVRAISON);
						factureAchatTmp.setMontantHT(new BigDecimal(txttotalmontantHT.getText()).setScale(6, RoundingMode.HALF_UP));
						factureAchatTmp.setMontantTVA(new BigDecimal(txttotalmontantTVA.getText()).setScale(6, RoundingMode.HALF_UP));
						factureAchatTmp.setMontantTTC((new BigDecimal(txttotalmontantTTC.getText())).setScale(6, RoundingMode.HALF_UP));	
						//factureAchatTmp.setDetailFactureAchat(listDetailFactureAchat);
						factureAchatTmp.setModifierPar(utilisateur);
						factureAchatTmp.setDateModifier(new Date());
					    factureAchatdao.edit(factureAchatTmp);
					    
					    ////////////////////////////////// ajouter detail compte client par client facture //////////////////////// 
					    /*
					    
					    FactureAchat factureAchat=listFactureAchat.get(table.getSelectedRow());
						DetailCompteClient detailcompteclient=detailCompteClientdao.findByFacture(factureAchat.getId());
						detailcompteclient.getCompteClient().setSolde(detailcompteclient.getCompteClient().getSolde().subtract(detailcompteclient.getMontantDebit()));
						compteclientdao.edit(detailcompteclient.getCompteClient());
						detailcompteclient.setCompteClient(clientPF.getCompteClient());
						detailcompteclient.setUtilisateurMAJ(utilisateur);
						detailcompteclient.setDateMaj(new Date());
						detailcompteclient.setFournisseur(fournisseur);
						detailcompteclient.setMontantDebit(new BigDecimal(txttotalmontantTTC.getText()));
						detailcompteclient.setDesignation("Montant sur Facture N "+facturePF.getNumFacture());
						detailcompteclient.setFacturepf(facturePF);	
					    detailCompteClientdao.edit(detailcompteclient);
					    solde=clientPF.getCompteClient().getSolde().add(new BigDecimal(txttotalmontantTTC.getText()));
					    clientPF.getCompteClient().setSolde(solde);
					    compteclientdao.edit(clientPF.getCompteClient());
					    
					    */
					    
						/*
						 * int i=0; while(i<listStockPF.size()) { if(listStockPF.get(i).getId()!=null) {
						 * 
						 * } StockPF stockPF=stockpfDAO.findById(listStockPF.get(i).getId());
						 * if(stockPF!=null) { stockPF.setStock(listStockPF.get(i).getStock());
						 * stockpfDAO.edit(stockPF); }else { stockpfDAO.add(listStockPF.get(i)); }
						 * 
						 * i++; }
						 */
					    
					    
					    
					    for(int j=0;j<listDetailTransferProduitFini.size();j++)
					    {
					    
					    	DetailTransferProduitFini detailtransferpf=listDetailTransferProduitFini.get(j);
					    	
					    	if(!detailtransferpf.getSousFamille().getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
					    	{

						    	detailtransferpf.setDateTransfer(dateChooserfacture.getDate());
						    	detailTransferStockPFdao.edit(detailtransferpf);
					    		
					    	}
					    	
					    }
					    
					    if(listDetailTransferProduitFini.size()!=0)
					    {
					    	
					    	  // update transfer stock produit fini 
						    transferStock.setModifierPar(utilisateur);
						    transferStock.setDateModifier(new Date());
						    transferStock.setDateTransfer(dateChooserfacture.getDate());
						    
						  //  transferStock.setListDetailTransferProduitFini(listDetailTransferProduitFini);
						    transferStockPFDAO.edit(transferStock);
					    	
					    }
					   
					  
					    
						//// Reglement de Stock 
						 Date dateDebut;
						try {
							 detailTransferStockPFdao.ViderSession();
							 dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse("2018"+"-01-01");
							 listeObject=detailTransferStockPFdao.listeStockFinalePF(dateDebut, new Date(), magasin);
							
									for(int t=0;t<listeObject.size();t++)
									{	
										 Object[] object=listeObject.get(t);
										
										 SousFamilleArticlePF sousFamilleArticlePFTmp=sousFamilleArticleDAO.findById((int)object[1]);
										 if(!sousFamilleArticlePFTmp.getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
										 {
											 
											 StockPF stockPF=stockpfDAO.findStockByMagasinPFBySousFamille((int) object[0], magasin.getId(), (int)object[1]);
											 
											 
											 if(stockPF!=null)
											 {
												 stockPF.setStock((BigDecimal)object[2]);
													stockpfDAO.edit(stockPF);
											 }else
											 {
												 
												 if(!((BigDecimal)object[2]).equals(BigDecimal.ZERO))
												 {
													 
													 StockPF stockPFTmp=new StockPF();
													 Articles articles=articleDAO.findById((int) object[0]);
													 SousFamilleArticlePF sousFamilleArticlePF=sousFamilleArticleDAO.findById((int)object[1]);
													 stockPFTmp.setArticles(articles);
													 stockPFTmp.setMagasin(magasin);
													 stockPFTmp.setSousFamille(sousFamilleArticlePF);
													 stockPFTmp.setStock((BigDecimal)object[2]);
													 BigDecimal prix=BigDecimal.ZERO;
													 for(int i=0;i<listDetailFactureAchat.size();i++)
													 {
														 if(listDetailFactureAchat.get(i).getArticle().getId()==(int) object[0] && listDetailFactureAchat.get(i).getSousFamille().getId()==(int)object[1])
														 {
															 stockPFTmp.setPrixUnitaire(listDetailFactureAchat.get(i).getPrixUnitaire());
														 }
														 
													 }
													
													 stockpfDAO.add(stockPFTmp);
													  
													 
												 }
												 
												
											 } 
											 
										 }
									
								
									}
								 
							
							
							
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    
					    
					    JOptionPane.showMessageDialog(null, "Facture Modifier avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					  //  initialiserFacture();
					    initialiser();
					  
					    listDetailFactureAchat.clear();
						InitialiseTableau();
						InitialiseTableauFacture();
						chargerListFacture();	
						
					}
				
				
			
				}
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(619, 918, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 448, 1458, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 386, 1458, 51);
		add(layeredPane_1);
		
		JLabel label = new JLabel("Code  :");
		label.setBounds(10, 13, 89, 24);
		layeredPane_1.add(label);
		
		txtnumfacture = new JTextField();
		txtnumfacture.setEditable(false);
		txtnumfacture.setColumns(10);
		txtnumfacture.setBounds(57, 12, 129, 26);
		layeredPane_1.add(txtnumfacture);
	
		JLabel label_1 = new JLabel("Date  :");
		label_1.setBounds(196, 13, 62, 24);
		layeredPane_1.add(label_1);
		
		 dateChooserfacture = new JDateChooser();
		dateChooserfacture.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {}
		});
		dateChooserfacture.setLocale(Locale.FRANCE);
		dateChooserfacture.setDateFormatString("dd/MM/yyyy");
		dateChooserfacture.setBounds(236, 11, 139, 26);
		layeredPane_1.add(dateChooserfacture);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(385, 13, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(433, 13, 144, 24);
		  layeredPane_1.add(combodepot);
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
		     					if(table.getSelectedRow()!=-1)
		     					{if(listFactureAchat.size()!=0)
		     					{
		     						 factureAchat=listFactureAchat.get(table.getSelectedRow()) ;
			     						
			     						combomagasin.setSelectedItem(factureAchat.getMagasin().getLibelle());
		     					}
		     						
		     						
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
		 
		  
		  JLabel label_4 = new JLabel("Magasin :");
		  label_4.setBounds(587, 13, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  		
		  	}
		  });
		  combomagasin.setBounds(641, 14, 183, 24);
		  layeredPane_1.add(combomagasin);
		  combomagasin.setSelectedIndex(-1);
		  
		  JLabel label_6 = new JLabel("Fournisseur :");
		  label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  label_6.setBounds(859, 13, 79, 24);
		  layeredPane_1.add(label_6);
		  
		   comboFournisseur = new JComboBox();
		  comboFournisseur.setSelectedIndex(-1);
		  comboFournisseur.setBounds(937, 14, 149, 24);
		  layeredPane_1.add(comboFournisseur);
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture");
		titledSeparator_2.setBounds(10, 358, 1311, 30);
		add(titledSeparator_2);
		
	  
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(498, 593, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					

					boolean trouve=false;
					BigDecimal nouveauprix=BigDecimal.ZERO;
					BigDecimal coutAchat=BigDecimal.ZERO;
					BigDecimal coutStock=BigDecimal.ZERO;
					BigDecimal QuantiteTotal=BigDecimal.ZERO;
					BigDecimal montant=BigDecimal.ZERO;
					BigDecimal marge=BigDecimal.ZERO;
					BigDecimal TVA=BigDecimal.ZERO;
					BigDecimal QuantiteUnit=BigDecimal.ZERO;
					BigDecimal QuantitePaquet=BigDecimal.ZERO;
					BigDecimal QuantiteTotalStock=BigDecimal.ZERO;
					boolean articleboisson=false;
					
					
				 if(comboArticle.getSelectedItem().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(txtcodearticle.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else if(txtquantite.getText().equals("") && txtquantitepacket.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))){
					
					
						JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					
				}else if(!txtquantitepacket.getText().equals("") &&  ((new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)<0))){
					
					
						JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					
				
				}else if(txtPrix.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Erreur de prix","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				
				}else 
				{
						
						 Articles article=mapArticle.get(comboArticle.getSelectedItem());
				         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				         SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
				         
				         if(radioavectva.isSelected()==true)
				         {
				        	 if(combotva.getSelectedIndex()!=-1)
				        	 {
				        		 ListTva listtva=mapTVA.get(combotva.getSelectedItem()); 
				        		 TVA=listtva.getTaux();
				        	 }
				        	
				         }
				         
				        
				         
				         
				         
				         if(article.getCentreCout1()!=null)
				         {
				        	 BigDecimal quantite = (new BigDecimal(txtquantite.getText())).setScale(3);
				        	 BigDecimal grammage = quantite.multiply(new BigDecimal(1000));
				        	 
				        	 
				        	if(article.getCentreCout1().equals(BigDecimal.ONE)) 
				        	{
				        		if(grammage.compareTo(BigDecimal.ZERO)!=0)
				        		{
				        			JOptionPane.showMessageDialog(null, "Grammage  "+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
				        			return;
				        		}
				        		
				        	}else
				        	{
				        		BigDecimal result=grammage.divide(article.getCentreCout1(), 6, RoundingMode.FLOOR);
				        	
				        		BigDecimal rest=result.subtract(result.setScale(0, RoundingMode.FLOOR)).movePointRight(result.scale());
				        	
				        		if(rest.compareTo(BigDecimal.ZERO)!=0)
				        		{
				        			JOptionPane.showMessageDialog(null, "Grammage d'"+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
				        			return;	
				        		}
				        		
				        	}
				        	 
				         }
				         
				         
				         for(int i=0;i<listDetailFactureAchat.size();i++)
				         {
				        	 DetailFactureAchat detailFactureAchat =listDetailFactureAchat.get(i);
				        	 if(detailFactureAchat.getArticle().getLiblle().equals(article.getLiblle()) && detailFactureAchat.getSousFamille().getLiblle().equals(sousfamille.getLiblle()))
				        	 {
				        		 trouve=true;
				        	 }
				         }
				         
				         if(trouve==false)
				         {
				        	 boolean existe=false;
				        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasin.getId(),sousfamille.getId());
				        	 
				        		
									if(article.getCentreCout2()!=null)
									{
										if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET) )
										{
											existe=true;
										}
										
									}
								
				        		
				        	 
				        	 DetailFactureAchat detailFacture=new DetailFactureAchat();
				        	 if(!txtreduction.getText().equals(""))
					          {
					        	  detailFacture.setReduction(new BigDecimal(txtreduction.getText()));
					          }else
					          {
					        	  detailFacture.setReduction(BigDecimal.ZERO);  
					          }
				        	 
					          detailFacture.setArticle(article);
					          detailFacture.setQuantite(new BigDecimal(txtquantite.getText()));
					           detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
						        montant=new BigDecimal(txtquantite.getText()).multiply(new BigDecimal(txtPrix.getText()));
						       
					           if(article.getCodeArticle().equals("1500") || article.getCodeArticle().equals("1503") || article.getCodeArticle().equals("1504") )
						          {
					        	   if(!txtreduction.getText().equals(""))
							          {
					        		   
							           detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP).subtract(montant.setScale(6, RoundingMode.HALF_UP).multiply(new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));

							          }else
							          {
								           detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP));
 
							          }
							        detailFacture.setMontantTVA((detailFacture.getMontantHT().multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
							        detailFacture.setTva(BigDecimal.ZERO);
							        detailFacture.setSousFamille(sousfamille);
							        detailFacture.setMontantTTC(detailFacture.getMontantHT().add(detailFacture.getMontantTVA()));  
							          
							          
						          }else
						          {
						        	  if(!txtreduction.getText().equals(""))
							          {
							        	  detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP).subtract(montant.setScale(6, RoundingMode.HALF_UP).multiply(new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));
 
						        		  
							          }else
							          {
							        	  detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP));

							        	  
							          }
					          
						        detailFacture.setMontantTVA((detailFacture.getMontantHT().multiply(TVA)).setScale(6, RoundingMode.HALF_UP));
						        detailFacture.setTva(TVA.multiply(new BigDecimal(100)));
						        detailFacture.setSousFamille(sousfamille);
						       
						        	  detailFacture.setMontantTTC(detailFacture.getMontantHT().add(detailFacture.getMontantTVA()));  
						       
						          }
					           detailFacture.setFactureAchat(factureAchat);
					       //    detailFacture.setDateCreation(new Date());
					           
					           detailFacture.setUtilisateur(utilisateur);
					          detailFactureAchatdao.add(detailFacture);
					           listDetailFactureAchat.add(detailFacture);
					           
					           if(!sousfamille.getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
					           {
					        	      if(stockpf!=null)
							           {
							        	   if(existe==true)
									          {
							        		   QuantiteTotal=new BigDecimal(txtquantite.getText()).multiply(article.getConditionnement().multiply(article.getConditionnementcaisse()));
							        		   coutAchat=(QuantiteTotal).multiply(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement().multiply(article.getConditionnementcaisse()) , 2, RoundingMode.DOWN));
									          }else
									          {
									        	  QuantiteTotal=new BigDecimal(txtquantite.getText());
									        	  coutAchat=QuantiteTotal.multiply(new BigDecimal(txtPrix.getText()));
									          }
								        	 
								        	  coutStock=stockpf.getStock().multiply(stockpf.getPrixUnitaire());
								        	
									        	  QuantiteTotalStock=QuantiteTotal.add(stockpf.getStock());
									         
								        	 
								        	   
								        	  nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotalStock,2, RoundingMode.DOWN);
									           stockpf.setStock(QuantiteTotalStock);
									           stockpf.setPrixUnitaire(nouveauprix);
									         
									           listStockPF.add(stockpf);
								           
								           }else
							           {
								        	   // creer stock avec prix (calculer prix moyen copier traitement de terminer production )
								        	   
								        	  StockPF stockpfTmp=new StockPF(); 
								        	   
								        	  stockpfTmp.setArticles(article);
								        	  stockpfTmp.setMagasin(magasin);
								        	  
								        	   if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
		                                       {
								        		   QuantiteTotal=new BigDecimal(txtquantite.getText()).multiply(article.getConditionnement().multiply(article.getConditionnementcaisse()));
		                                        stockpfTmp.setPrixUnitaire(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement().multiply(article.getConditionnementcaisse()),  2, RoundingMode.DOWN));
		                                       }else
		                                       {
		                                    	   QuantiteTotal=new BigDecimal(txtquantite.getText()); 
		                                       stockpfTmp.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
		                                        }
								        	   stockpfTmp.setStock(QuantiteTotal);
									         
								        	 
								        	  stockpfTmp.setStockMin(BigDecimal.ZERO);
								        	  stockpfTmp.setSousFamille(sousfamille);
								        	  stockpfDAO.add(stockpfTmp);
								        	  listStockPF.add(stockpfTmp);
								        	   
								        	  }
					        	   
					           }
					           
					     
					           
					           
					          afficher_tableDetailFactureAchat(listDetailFactureAchat);
					          int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO;
						        while(i<listDetailFactureAchat.size())
						        {
						        /*	
						        	articleboisson=false;
						        	
										if(listDetailFactureAchat.get(i).getArticle().getCentreCout2()!=null)
										{
											if(listDetailFactureAchat.get(i).getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
											{
												articleboisson=true;
											}
											
										}
									
						        	
						        	if(articleboisson==true)
						        	{
						        		 DetailFactureAchat detailFactureAchat=listDetailFactureAchat.get(i);
								          montanttotal=  montanttotal.add(detailFactureAchat.getMontantTTC().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
								          sommequantite= sommequantite.add(detailFactureAchat.getQuantite().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
								          montanttotalHT=montanttotalHT.add(detailFactureAchat.getMontantHT().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
								          montanttotalTVA=montanttotalTVA.add(detailFactureAchat.getMontantTVA().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
						        	}else
						        	{*/
						        	
						        	 DetailFactureAchat detailFactureAchat=listDetailFactureAchat.get(i);
							          montanttotal=  montanttotal.add(detailFactureAchat.getMontantTTC());
							          sommequantite= sommequantite.add(detailFactureAchat.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFactureAchat.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFactureAchat.getMontantTVA());
						        /*	}*/
						            i++;
						        }
						       txttotalmontantTTC.setText(montanttotal.setScale(6,RoundingMode.HALF_UP)+"");
						        txttotalquantite.setText(sommequantite.setScale(6,RoundingMode.HALF_UP)+"");
						        txttotalmontantHT.setText(montanttotalHT.setScale(6,RoundingMode.HALF_UP)+"");
					  			txttotalmontantTVA.setText(montanttotalTVA.setScale(6,RoundingMode.HALF_UP)+"");
					  			
					  		  if(!sousfamille.getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
					           {
					  			  
					  			/// ajout transfer stock PF (Mouvement Stock PF)
						  			DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
						  			detailTransferStockPF.setArticle(article);
						  			detailTransferStockPF.setSousFamille(sousfamille);
									detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
									detailTransferStockPF.setMagasinDestination(magasin);
									detailTransferStockPF.setQuantite(QuantiteTotal); 
									
									 if(existe==true)
							          {
										 
										 detailTransferStockPF.setPrixUnitaire((new BigDecimal(txtPrix.getText()).subtract(new BigDecimal(txtPrix.getText()).multiply(detailFacture.getReduction().divide(new BigDecimal(100), 6, RoundingMode.FLOOR)))).divide(article.getConditionnement().multiply(article.getConditionnementcaisse()) , 6, RoundingMode.FLOOR));
							        
							          }else
							          {
							        	  
							        	  detailTransferStockPF.setPrixUnitaire((new BigDecimal(txtPrix.getText()).subtract(new BigDecimal(txtPrix.getText()).multiply(detailFacture.getReduction().divide(new BigDecimal(100), 6, RoundingMode.FLOOR))))); 
							        
							          }
									
									detailTransferStockPF.setTransferStockPF(transferStock);
									detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_ACHAT);
									detailTransferStockPFdao.add(detailTransferStockPF);
									listDetailTransferProduitFini.add(detailTransferStockPF);
					  			  
					           }
					  			
					  		
					  			
						        initialiser();
								
					       }else
					       {
					    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					    	   return;
					       }
					       
				         }
				        
						
					
					
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité , Le Prix et la Remise doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
			}
				
			
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
		
		  
		   btnModifier = new JButton();
		   btnModifier.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		try {
		   			

			   		BigDecimal nouveauprix=BigDecimal.ZERO;
			   		BigDecimal coutAchat=BigDecimal.ZERO;
					BigDecimal coutStock=BigDecimal.ZERO;
					BigDecimal QuantiteTotal=BigDecimal.ZERO;
					BigDecimal oldPrix=BigDecimal.ZERO;
					BigDecimal oldQuantite=BigDecimal.ZERO;
					BigDecimal montant=BigDecimal.ZERO;
			   		BigDecimal stocktmp=BigDecimal.ZERO;
			   		BigDecimal marge=BigDecimal.ZERO;
			   		BigDecimal QuantiteTotalStock=BigDecimal.ZERO;
			   		BigDecimal TVA=BigDecimal.ZERO;
			   		BigDecimal QuantiteUnit=BigDecimal.ZERO;
					BigDecimal QuantitePaquet=BigDecimal.ZERO;
			   		boolean articleboisson=false;
			   		
			   		if(tableArticle.getSelectedRow()!=-1)
			   		{
			   			boolean trouve=false;
				   		 if(comboArticle.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if(txtcodearticle.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if(txtquantite.getText().equals("") && txtquantitepacket.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					} else if(!txtquantite.getText().equals("") && ((new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))){
						
						
							JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						
					}else if(!txtquantitepacket.getText().equals("") &&  ((new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)<0))){
							
							JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
					}else if(txtPrix.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Erreur de prix","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							}else
							{
							
							 Articles article=mapArticle.get(comboArticle.getSelectedItem());
					         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
					         SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
					         
					         
					         if(listDetailFactureAchat.get(tableArticle.getSelectedRow()).getSousFamille().getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
						      {
						    	  
						    	if(!sousfamille.getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
						    	{
						    		
						    	JOptionPane.showMessageDialog(null, "Pour Modifier Un Article par un Autre Consommable Veuillez le Supprimer et Ajouter le a Nouveau" ,"Erreur", JOptionPane.ERROR_MESSAGE);
						    	return ;
						    		
						    	}
						    	  
						    	  
						      }
						      
						      if(sousfamille.getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
						      {
						    	  if(!listDetailFactureAchat.get(tableArticle.getSelectedRow()).getSousFamille().getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
							      {
						    		  
						    		  JOptionPane.showMessageDialog(null, "Pour Modifier Un Article par un Autre Consommable Veuillez le Supprimer et Ajouter le a Nouveau" ,"Erreur", JOptionPane.ERROR_MESSAGE);
								    	return ; 
						    		  
						    		  
							      }
						    	  
						    	  
						      }
					         
					         
					         
					         
					         
					         if(radioavectva.isSelected()==true)
					         {
					        	 if(combotva.getSelectedIndex()!=-1)
					        	 {
					        		 ListTva listtva=mapTVA.get(combotva.getSelectedItem()); 
					        		 TVA=listtva.getTaux();
					        	 }
					        	
					         }
					         
					      
					         
					         if(article.getCentreCout1()!=null)
					         {
					        	 BigDecimal quantite = new BigDecimal(txtquantite.getText()).setScale(3);
					        	 BigDecimal grammage = quantite.multiply(new BigDecimal(1000));
					        	 
					        	 
					        	if(article.getCentreCout1().equals(BigDecimal.ONE)) 
					        	{
					        		if(grammage.compareTo(BigDecimal.ZERO)!=0)
					        		{
					        			JOptionPane.showMessageDialog(null, "Grammage  "+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
					        			return;
					        		}
					        		
					        	}else
					        	{
					        		BigDecimal result=grammage.divide(article.getCentreCout1(), 6, RoundingMode.FLOOR);
					        	
					        		BigDecimal rest=result.subtract(result.setScale(0, RoundingMode.FLOOR)).movePointRight(result.scale());
					        	
					        		if(rest.compareTo(BigDecimal.ZERO)!=0)
					        		{
					        			JOptionPane.showMessageDialog(null, "Grammage d'"+article.getLiblle()+" incompatible !!!!!","Attention",JOptionPane.ERROR_MESSAGE);
					        			return;	
					        		}
					        		
					        	}
					        	 
					         }
					         
					         
					         for(int i=0;i<listDetailFactureAchat.size();i++)
					         {
					        	 if(i!=tableArticle.getSelectedRow())
					        	 {
					        		 
					        		 DetailFactureAchat detailFactureAchat =listDetailFactureAchat.get(i);
						        	 if(detailFactureAchat.getArticle().getLiblle().equals(article.getLiblle()) && detailFactureAchat.getSousFamille().getLiblle().equals(sousfamille.getLiblle()))
						        	 {
						        		 trouve=true;
						        	 }
						        	  
					        		 
					        	 }
					        	 
					        	 
					         }
					         
					         if(trouve==false)
					         {
					        	 boolean existe=false;
					        		
										if(article.getCentreCout2()!=null)
										{
											if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
											{
												existe=true;
												
											}
											
										}
										
										
										   
								           if(!sousfamille.getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
								           {
								        	   
								        	   StockPF stockpftmp =listStockPF.get(tableArticle.getSelectedRow());
									        	if(existe==true)
									        	{
									        		 oldQuantite=stockpftmp.getStock().subtract(listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite().multiply(article.getConditionnement().multiply(article.getConditionnementcaisse())));
									        	}else
									        	{
									        		 oldQuantite=stockpftmp.getStock().subtract(listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite());
									        	}
									        	
									        	 if(oldQuantite.compareTo(BigDecimal.ZERO)<=0)
									        	 {
									        		 oldQuantite=BigDecimal.ZERO; 
									        		 oldPrix=stockpftmp.getPrixUnitaire();
									        		
									        	 }else
									        	 {
									        		 if(existe==true)
											        	{
											        	 oldPrix=((stockpftmp.getPrixUnitaire().multiply((listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite().multiply(article.getConditionnement().multiply(article.getConditionnementcaisse())).add(oldQuantite)))).subtract((listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite().multiply(article.getConditionnement().multiply(article.getConditionnementcaisse())).multiply(listDetailFactureAchat.get(tableArticle.getSelectedRow()).getPrixUnitaire().divide(article.getConditionnement().multiply(article.getConditionnementcaisse()), 6, RoundingMode.DOWN))))).divide(oldQuantite, 6, RoundingMode.HALF_UP);

											        	}else
											        	{
												        	 oldPrix=((stockpftmp.getPrixUnitaire().multiply((listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite().add(oldQuantite)))).subtract((listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite().multiply(listDetailFactureAchat.get(tableArticle.getSelectedRow()).getPrixUnitaire())))).divide(oldQuantite, 6, RoundingMode.HALF_UP);

											        	}
									        		 
					 
									        	 }
									        	
					 
									        	
									        	stockpftmp.setStock(oldQuantite);
									        	
									        	
									        	 stockpftmp.setPrixUnitaire(oldPrix);
									        	
									        	 stockpfDAO.edit(stockpftmp);
									        	  
								        	   
								        	   
								           }
									
								
					        	 
					        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasin.getId(),sousfamille.getId());
					        	
					        		
					        	 DetailFactureAchat detailFacture= listDetailFactureAchat.get(tableArticle.getSelectedRow());
					        	
					        	 
					        	
					        	 
					        	  if(!txtreduction.getText().equals(""))
						          {
						        	  detailFacture.setReduction(new BigDecimal(txtreduction.getText()));
						          }else
						          {
						        	  detailFacture.setReduction(BigDecimal.ZERO);  
						          }
					        	 
						          detailFacture.setArticle(article);
						        
						          detailFacture.setQuantite(new BigDecimal(txtquantite.getText()));
						       
							        detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
							         montant=new BigDecimal(txtquantite.getText()).multiply(new BigDecimal(txtPrix.getText()));
							         
						           if(article.getCodeArticle().equals("1500") || article.getCodeArticle().equals("1503") || article.getCodeArticle().equals("1504") )
							          {

						        	   if(!txtreduction.getText().equals(""))
								          {
								           detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP).subtract(montant.setScale(6, RoundingMode.HALF_UP).multiply(new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));

						        		   
								          }else
								          {
								        	  
									           detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP));
 
								          }
								       detailFacture.setMontantTVA((detailFacture.getMontantHT().multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
								       detailFacture.setTva(BigDecimal.ZERO);
								       detailFacture.setMontantTTC(detailFacture.getMontantHT().add(detailFacture.getMontantTVA()));
								       detailFacture.setSousFamille(sousfamille);
								       
								          
							          }else
							          {
							        	  if(!txtreduction.getText().equals(""))
								          {
									           detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP).subtract(montant.setScale(6, RoundingMode.HALF_UP).multiply(new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP)));

								          }else
								          {
									           detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP));
  
								        	  
								          }
							       detailFacture.setMontantTVA((detailFacture.getMontantHT().multiply(TVA)).setScale(6, RoundingMode.HALF_UP));
							       detailFacture.setTva(TVA.multiply(new BigDecimal(100)));
							       detailFacture.setMontantTTC(detailFacture.getMontantHT().add(detailFacture.getMontantTVA()));
							       detailFacture.setSousFamille(sousfamille);
							       
							          }
							       
						        
						           detailFacture.setFactureAchat(factureAchat);
						       //    detailFacture.setDateCreation(new Date());
						           
						           detailFacture.setUtilisateur(utilisateur);	
						           
						           
						           
						           if(!sousfamille.getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
						           {
						        	   DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(tableArticle.getSelectedRow());
						               
							           // Modifier Transfer Stock PF
								        detailTransferStockPF.setArticle(article);
								        detailTransferStockPF.setSousFamille(sousfamille);
										detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
										detailTransferStockPF.setMagasinDestination(magasin);
										 		
										 if(existe==true)
								          {
											 QuantiteTotal=new BigDecimal(txtquantite.getText()).multiply(article.getConditionnement().multiply(article.getConditionnementcaisse()));
											 detailTransferStockPF.setQuantite(QuantiteTotal);
											 if(!txtreduction.getText().equals(""))
									          {
												 detailTransferStockPF.setPrixUnitaire((new BigDecimal(txtPrix.getText()).subtract(new BigDecimal(txtPrix.getText()).multiply(detailFacture.getReduction().divide(new BigDecimal(100), 6, RoundingMode.FLOOR)))).divide(article.getConditionnement().multiply(article.getConditionnementcaisse()) , 6, RoundingMode.FLOOR));

									          }else
									          {
									        	  
									        	  detailTransferStockPF.setPrixUnitaire(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement().multiply(article.getConditionnementcaisse()) , 6, RoundingMode.FLOOR));   
									        	  
									          }
													
								          }else
								          {
								        	  QuantiteTotal=new BigDecimal(txtquantite.getText());
								        	  detailTransferStockPF.setQuantite(QuantiteTotal);
								        	  
								        	  if(!txtreduction.getText().equals(""))
									          {
													 detailTransferStockPF.setPrixUnitaire((new BigDecimal(txtPrix.getText()).subtract(new BigDecimal(txtPrix.getText()).multiply(detailFacture.getReduction().divide(new BigDecimal(100), 6, RoundingMode.FLOOR)))));  
								        		  
									          }else
									          {
									        	  detailTransferStockPF.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
									          }
								        	 
								        	 
								          }
										
										detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_ACHAT);
										detailTransferStockPFdao.edit(detailTransferStockPF);
										listDetailTransferProduitFini.set(tableArticle.getSelectedRow(), detailTransferStockPF);
							           
							           
							           
							           
							           
							           if(stockpf!=null)
							           {
							        	
							        	   if(existe==true)
									          {
							        		   QuantiteTotal=new BigDecimal(txtquantite.getText()).multiply(article.getConditionnement().multiply(article.getConditionnementcaisse()));
							        		   coutAchat=QuantiteTotal.multiply(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement().multiply(article.getConditionnementcaisse()) , 2, RoundingMode.DOWN));
									          }else
									          {
									        	  QuantiteTotal=new BigDecimal(txtquantite.getText());
									        	  coutAchat=QuantiteTotal.multiply(new BigDecimal(txtPrix.getText()));
									          }
								        	 
								        	  coutStock=stockpf.getStock().multiply(stockpf.getPrixUnitaire());
								        	
									        	  QuantiteTotalStock=QuantiteTotal.add(stockpf.getStock());  
									          
								        	 
								        	   
								        	  nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotalStock, 6, RoundingMode.HALF_UP);
									           stockpf.setStock(QuantiteTotalStock);
									           stockpf.setPrixUnitaire(nouveauprix);
									      
								           listStockPF.set(tableArticle.getSelectedRow(), stockpf);
							           }  else
							           {
							        	   // creer stock avec prix (calculer prix moyen copier traitement de terminer production )
							        	   
							        	   listStockPF.remove(tableArticle.getSelectedRow());
								        	  StockPF stockpfTmp=new StockPF(); 					        	   
								        	  stockpfTmp.setArticles(article);
								        	  stockpfTmp.setMagasin(magasin);
								        	 
								        	 
								        	  if(existe==true)
									          {
								        		  QuantiteTotal=new BigDecimal(txtquantite.getText()).multiply(article.getConditionnement().multiply(article.getConditionnementcaisse()));
								        		  stockpfTmp.setStock(QuantiteTotal); 
								        		  stockpfTmp.setPrixUnitaire(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement().multiply(article.getConditionnementcaisse()) , 2, RoundingMode.DOWN));
									          }else
									          {
									        	  QuantiteTotal=new BigDecimal(txtquantite.getText());
									        	  stockpfTmp.setStock(QuantiteTotal); 
									        	  stockpfTmp.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
									          }
								        	 
								        	  stockpfTmp.setStockMin(BigDecimal.ZERO);
								        	  stockpfTmp.setSousFamille(sousfamille);
								        	  stockpfDAO.add(stockpfTmp);
								        	  listStockPF.add(stockpfTmp);
							        	   
							           } 
						        	   
						           }
						           
						
						         
						           detailFactureAchatdao.edit(detailFacture);
						           listDetailFactureAchat.set(tableArticle.getSelectedRow(), detailFacture);
						          afficher_tableDetailFactureAchat(listDetailFactureAchat);
						          int i=0;
							        BigDecimal montanttotal=BigDecimal.ZERO;
							        BigDecimal sommequantite=BigDecimal.ZERO;
							        BigDecimal montanttotalHT=BigDecimal.ZERO;
							        BigDecimal montanttotalTVA=BigDecimal.ZERO;
							        while(i<listDetailFactureAchat.size())
							        {
							        /*	articleboisson=false;
							        	
											if(listDetailFactureAchat.get(i).getArticle().getCentreCout2()!=null)
											{
												if(listDetailFactureAchat.get(i).getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
												{
													articleboisson=true;
												}
												
											}
										
						        	 
							        	if(articleboisson==true)
							        	{
							        		 DetailFactureAchat detailFactureAchat=listDetailFactureAchat.get(i);
									          montanttotal=  montanttotal.add(detailFactureAchat.getMontantTTC().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
									          sommequantite= sommequantite.add(detailFactureAchat.getQuantite().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
									          montanttotalHT=montanttotalHT.add(detailFactureAchat.getMontantHT().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
									          montanttotalTVA=montanttotalTVA.add(detailFactureAchat.getMontantTVA().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
							        	}else
							        	{*/
							        								        	
							        	 DetailFactureAchat detailFactureAchat=listDetailFactureAchat.get(i);
								          montanttotal=  montanttotal.add(detailFactureAchat.getMontantTTC());
								          sommequantite= sommequantite.add(detailFactureAchat.getQuantite());
								          montanttotalHT=montanttotalHT.add(detailFactureAchat.getMontantHT());
								          montanttotalTVA=montanttotalTVA.add(detailFactureAchat.getMontantTVA());
							        /*	}*/
							            i++;
							        }
							       txttotalmontantTTC.setText(montanttotal.setScale(6, RoundingMode.HALF_UP)+"");
							        txttotalquantite.setText(sommequantite.setScale(6, RoundingMode.HALF_UP)+"");
							        txttotalmontantHT.setText(montanttotalHT.setScale(6, RoundingMode.HALF_UP)+"");
						  			txttotalmontantTVA.setText(montanttotalTVA.setScale(6, RoundingMode.HALF_UP)+"");
						  			initialiser();
									
									
						       }else
						       {
						    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						    	   return;
						       }
						       
								
					        	 
					         }
						}
			   			
			   		
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantite , le Prix et la Remise doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
		   		
		   	}
		   		
		   
		   });
		  btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  
		   btnSupprimer = new JButton();
		  btnSupprimer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  	
				BigDecimal oldPrix=BigDecimal.ZERO;
				BigDecimal oldQuantite=BigDecimal.ZERO;
				boolean articleboisson=false;
		  		if(tableArticle.getSelectedRow()!=-1)
		  		{
		  			
		  			 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer l'article dans la facture  ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
							
						{
							 StockPF stockpftmp = null ;
							
							
						if(!listDetailFactureAchat.get(tableArticle.getSelectedRow()).getSousFamille().getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
						{
							stockpftmp =listStockPF.get(tableArticle.getSelectedRow());
							
							if(stockpftmp.getArticles().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
							{
								 oldQuantite=stockpftmp.getStock().subtract(listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite().multiply(stockpftmp.getArticles().getConditionnement().multiply(stockpftmp.getArticles().getConditionnementcaisse())));
							}else
							{
								 oldQuantite=stockpftmp.getStock().subtract(listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite());
							}
														
														
											        	
											        	 if(oldQuantite.compareTo(BigDecimal.ZERO)<=0)
											        	 {
											        		 oldQuantite=BigDecimal.ZERO; 
											        		 oldPrix=stockpftmp.getPrixUnitaire();
											        	 }else
											        	 {
											        		 if(stockpftmp.getArticles().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
											        		 {
													        	 oldPrix=((stockpftmp.getPrixUnitaire().multiply((listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite().multiply(stockpftmp.getArticles().getConditionnement().multiply(stockpftmp.getArticles().getConditionnementcaisse())).add(oldQuantite)))).subtract((listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite().multiply(stockpftmp.getArticles().getConditionnement().multiply(stockpftmp.getArticles().getConditionnementcaisse())).multiply(listDetailFactureAchat.get(tableArticle.getSelectedRow()).getPrixUnitaire().divide(stockpftmp.getArticles().getConditionnement().multiply(stockpftmp.getArticles().getConditionnementcaisse()),6, RoundingMode.DOWN))))).divide(oldQuantite, 6, RoundingMode.HALF_UP);
							 
											        		 }else
											        		 {
													        	 oldPrix=((stockpftmp.getPrixUnitaire().multiply((listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite().add(oldQuantite)))).subtract((listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite().multiply(listDetailFactureAchat.get(tableArticle.getSelectedRow()).getPrixUnitaire())))).divide(oldQuantite, 6, RoundingMode.HALF_UP);
							 
											        		 }
											        		 
							 
											        	 }
											        	 stockpftmp.setStock(oldQuantite);
											        	 stockpftmp.setPrixUnitaire(oldPrix);
							
							
						}
						
						
                           
					  		
					  		if(!listDetailFactureAchat.get(tableArticle.getSelectedRow()).getSousFamille().getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
							{
					  			listStockPF.set(tableArticle.getSelectedRow(),stockpftmp);
						  		detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(tableArticle.getSelectedRow()).getId());
						  		listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
					  			
							}
					  		
					  	  DetailFactureAchat detailfactureachat=listDetailFactureAchat.get(tableArticle.getSelectedRow());
					  		detailFactureAchatdao.delete(detailfactureachat.getId());					  		
					  		listDetailFactureAchat.remove(tableArticle.getSelectedRow());
					  		
					  		
					  		
					        afficher_tableDetailFactureAchat(listDetailFactureAchat);
					          int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO;
						        while(i<listDetailFactureAchat.size())
						        {
						        /*	articleboisson=false;
						        	
										if(listDetailFactureAchat.get(i).getArticle().getCentreCout2()!=null)
										{
											if(listDetailFactureAchat.get(i).getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
											{
												articleboisson=true;
											}
											
										}
									
					        	 
						        	if(articleboisson==true)
						        	{
						        		 DetailFactureAchat detailFactureAchat=listDetailFactureAchat.get(i);
								          montanttotal=  montanttotal.add(detailFactureAchat.getMontantTTC().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
								          sommequantite= sommequantite.add(detailFactureAchat.getQuantite().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
								          montanttotalHT=montanttotalHT.add(detailFactureAchat.getMontantHT().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
								          montanttotalTVA=montanttotalTVA.add(detailFactureAchat.getMontantTVA().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
						        	}else
						        	{*/
						        	
						        	 DetailFactureAchat detailFactureAchat=listDetailFactureAchat.get(i);
							          montanttotal=  montanttotal.add(detailFactureAchat.getMontantTTC());
							          sommequantite= sommequantite.add(detailFactureAchat.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFactureAchat.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFactureAchat.getMontantTVA());
						        	/*}*/
						            i++;
						        }
						        
						       txttotalmontantTTC.setText(montanttotal.setScale(6, RoundingMode.HALF_UP)+"");
						       txttotalquantite.setText(sommequantite.setScale(6, RoundingMode.HALF_UP)+"");
					  			txttotalmontantHT.setText(montanttotalHT.setScale(6, RoundingMode.HALF_UP)+"");
					  			txttotalmontantTVA.setText(montanttotalTVA.setScale(6, RoundingMode.HALF_UP)+"");
					  			JOptionPane.showMessageDialog(null, "Article supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					  			initialiser();
						}		  			
		  			
		  		}
		  		
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1395, 701, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(1116, 975, 112, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(1221, 975, 153, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(987, 918, 119, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(902, 918, 97, 26);
		add(lblTotalQuantite);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1395, 675, 73, 24);
		add(btnModifier);
		
		JLabel lblConslterLesFactures = new JLabel("                             Consulter les Factures d'achat par :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(151, 11, 1234, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 143, 1458, 218);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow()!=-1)
				{
					boolean articleboisson=false;
					if(listFactureAchat.size()!=0)
					{
						listStockPF.clear();
						 factureAchat=listFactureAchat.get(table.getSelectedRow()) ;
						 listDetailTransferProduitFini.clear();
						 
						 transferStock=transferStockPFDAO.findByCodeTransfert(factureAchat.getCodeTransfer());
						 if(transferStock!=null)
						 {
							 listDetailTransferProduitFini=detailTransferStockPFdao.findByTransferStockPF(transferStock.getId());
						 }
						
						 
						txtnumfacture.setText(factureAchat.getNumFacture());
						dateChooserfacture.setDate(factureAchat.getDateFacture());
						
						combodepot.setSelectedItem(factureAchat.getDepot().getLibelle());
						
						
					combomagasin.setSelectedItem(factureAchat.getMagasin().getLibelle());
						comboFournisseur.setSelectedItem(factureAchat.getFournisseur().getNom());
						listDetailFactureAchat=detailFactureAchatdao.listeDetailFactureAchatByFacture(factureAchat.getId());
						
						 int i=0;
					        BigDecimal montanttotal=BigDecimal.ZERO;
					        BigDecimal sommequantite=BigDecimal.ZERO;
					        BigDecimal montanttotalHT=BigDecimal.ZERO;
					        BigDecimal montanttotalTVA=BigDecimal.ZERO;
					        while(i<listDetailFactureAchat.size())
					        {
					        	 articleboisson=false;
					        	 
					        	 if(!listDetailFactureAchat.get(i).getSousFamille().getCode().equals(Constantes.SOUS_FAMILLE_CONSOMMABLE))
					        	 {
					        		 
					        		 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFactureAchat.get(i).getArticle().getId(),factureAchat.getMagasin().getId(),listDetailFactureAchat.get(i).getSousFamille().getId());
								      if(stockpf==null)
								      {
								    	  StockPF stockpftmp=new StockPF()  ;
								    	  stockpftmp.setArticles(listDetailFactureAchat.get(i).getArticle());
								    	  stockpftmp.setMagasin(factureAchat.getMagasin());
								    	  stockpftmp.setPrixUnitaire(BigDecimal.ZERO);
								    	  stockpftmp.setSousFamille(listDetailFactureAchat.get(i).getSousFamille());
								    	  stockpftmp.setStock(BigDecimal.ZERO);
								    	  
								    	  stockpfDAO.add(stockpftmp);
								    	  listStockPF.add(stockpftmp);
								    	  
								      }else
								      {
								    	  listStockPF.add(stockpf);
								      }
						        	 
						        	 
					        	 }
					        	
					        	
					        	/*
									if(listDetailFactureAchat.get(i).getArticle().getCentreCout2()!=null)
									{
										if(listDetailFactureAchat.get(i).getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
										{
											articleboisson=true;
										}
										
									}
								
				        	 
					        	if(articleboisson==true)
					        	{
					        		 DetailFactureAchat detailFactureAchat=listDetailFactureAchat.get(i);
							          montanttotal=  montanttotal.add(detailFactureAchat.getMontantTTC().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
							          sommequantite= sommequantite.add(detailFactureAchat.getQuantite().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
							          montanttotalHT=montanttotalHT.add(detailFactureAchat.getMontantHT().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
							          montanttotalTVA=montanttotalTVA.add(detailFactureAchat.getMontantTVA().divide(detailFactureAchat.getArticle().getConditionnement(),RoundingMode.HALF_UP));
					        	}else
					        	{
					        	*/
					        	  DetailFactureAchat detailFactureAchat=listDetailFactureAchat.get(i);
						          montanttotal=  montanttotal.add(detailFactureAchat.getMontantTTC());
						          sommequantite= sommequantite.add(detailFactureAchat.getQuantite());
						          montanttotalHT=montanttotalHT.add(detailFactureAchat.getMontantHT());
						          montanttotalTVA=montanttotalTVA.add(detailFactureAchat.getMontantTVA());
					        	/*}*/
					            i++;
					        }
					       txttotalmontantTTC.setText(montanttotal.setScale(6)+"");
					        txttotalquantite.setText(sommequantite.setScale(6)+"");
					        txttotalmontantHT.setText(montanttotalHT.setScale(6)+"");
				  			txttotalmontantTVA.setText(montanttotalTVA.setScale(6)+"");
							
				  			
				  			
						afficher_tableDetailFactureAchat(listDetailFactureAchat);
						initialiser();
						
					}
					
				}
				
			}
		});
		
		
		scrollPane_1.setViewportView(table);
		table.setColumnControlVisible(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Num Facture", "Date Facture", "Etat", "Fournisseur", "Depot", "Magasin", "Montant TTC"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(121);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(111);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(114);
		table.getColumnModel().getColumn(5).setPreferredWidth(136);
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
	    		
	    		 detailFactureAchatdao.ViderSession();
	    		if(combopardepot.getSelectedItem().equals(""))
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    	if(comboparfournisseur.getSelectedItem().equals("")
	    			&& txtparnumfacture.getText().equals("") 
	    			&& pardateChooser.getDate()==null
	    			&& combopardepot.getSelectedItem().equals(""))
	    	{
	    		
	    		initialiser();
	    		initialiserFacture();
	    		InitialiseTableau();
	    		InitialiseTableauFacture();
	    		
	    	}else
	    	{
	    		
	    		chargerListFacture();
	    		
	    		
	    	}
	    				
	    		
	    	}
	    });
	    btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnAfficher.setBounds(579, 108, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLabel label_2 = new JLabel("Total Montant TVA :");
	    label_2.setBounds(1116, 944, 105, 26);
	    add(label_2);
	    
	    txttotalmontantTVA = new JTextField();
	    txttotalmontantTVA.setEditable(false);
	    txttotalmontantTVA.setColumns(10);
	    txttotalmontantTVA.setBounds(1221, 944, 153, 26);
	    add(txttotalmontantTVA);
	    
	    txttotalmontantHT = new JTextField();
	    txttotalmontantHT.setEditable(false);
	    txttotalmontantHT.setColumns(10);
	    txttotalmontantHT.setBounds(1221, 912, 153, 26);
	    add(txttotalmontantHT);
	    
	    JLabel label_5 = new JLabel("Total Montant HT :");
	    label_5.setBounds(1116, 912, 105, 26);
	    add(label_5);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1458, 51);
	    add(layeredPane_2);
	    
	    JLabel lblNumFacture = new JLabel("Num Facture :");
	    lblNumFacture.setBounds(10, 12, 97, 24);
	    layeredPane_2.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtparnumfacture = new JTextField();
	    txtparnumfacture.setBounds(116, 12, 116, 26);
	    layeredPane_2.add(txtparnumfacture);
	    util.Utils.copycoller(txtparnumfacture);
	    txtparnumfacture.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {}
	    });
	    txtparnumfacture.setColumns(10);
	    
	    JLabel lblDateFacture = new JLabel("Date Facture :");
	    lblDateFacture.setBounds(477, 11, 97, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     pardateChooser = new JDateChooser();
	     pardateChooser.setBounds(584, 10, 151, 26);
	     layeredPane_2.add(pardateChooser);
	     pardateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	     	public void propertyChange(PropertyChangeEvent arg0) {}
	     });
	     pardateChooser.addKeyListener(new KeyAdapter() {
	     	@Override
	     	public void keyPressed(KeyEvent e) {
	     		
	     		
	     		
	     	}
	     });
	     pardateChooser.setLocale(Locale.FRANCE);
	     pardateChooser.setDateFormatString("dd/MM/yyyy");
	     
	     combopardepot = new JComboBox();
	     combopardepot.setBounds(807, 12, 151, 24);
	     layeredPane_2.add(combopardepot);
	     combopardepot.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {}
	     });
	     combopardepot.setSelectedIndex(-1);
	     
	     JLabel lblDepot = new JLabel("Depot  :");
	     lblDepot.setBounds(745, 11, 97, 24);
	     layeredPane_2.add(lblDepot);
	     lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     
	     JLabel label_7 = new JLabel("Fournisseur :");
	     label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     label_7.setBounds(242, 11, 79, 24);
	     layeredPane_2.add(label_7);
	     
	      comboparfournisseur = new JComboBox();
	     comboparfournisseur.setSelectedIndex(-1);
	     comboparfournisseur.setBounds(320, 12, 149, 24);
	     layeredPane_2.add(comboparfournisseur);
	     
	
    
    JLabel label_9 = new JLabel("Famille Article :");
    label_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
    label_9.setBounds(0, 13, 87, 24);
    layeredPane.add(label_9);
    
     combofamille = new JComboBox();
    combofamille.addItemListener(new ItemListener() {
    	public void itemStateChanged(ItemEvent e) {
    		

    		
   		 if(e.getStateChange() == ItemEvent.SELECTED)
	 		 {
   			 combosousfamille.removeAllItems();
   			combosousfamille.addItem(""); 
   		FamilleArticlePF famille=mapfamille.get(combofamille.getSelectedItem());
   		if(famille!=null)
   		{
   			
   			listSousFamilleArticle=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePF(famille.getId());
   			for(int i=0;i<listSousFamilleArticle.size();i++)
   			{
   			SousFamilleArticlePF sousfamille=listSousFamilleArticle.get(i);
   			combosousfamille.addItem(sousfamille.getLiblle());
   			mapsousfamille.put(sousfamille.getLiblle(), sousfamille);
   				
   			}
   			
   		}
   			
   			 
	 		 }
	 			 
   		
   		
   	
    		
    		
    		
    		
    		
    		
    		
    	}
    });
    combofamille.setSelectedIndex(-1);
    combofamille.setBounds(82, 14, 167, 24);
    layeredPane.add(combofamille);
    
    JLabel label_10 = new JLabel("Sous Famille :");
    label_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
    label_10.setBounds(259, 13, 87, 24);
    layeredPane.add(label_10);
    
     combosousfamille = new JComboBox();
     combosousfamille.addItemListener(new ItemListener() {
     	public void itemStateChanged(ItemEvent e) {
     	

   		 if(e.getStateChange() == ItemEvent.SELECTED)
	 		 {
   			 
   		SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
   	 Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
   	 if(magasin!=null)
   	 {
   		 
   		 if(sousfamille!=null)
			 {
				 comboArticle.removeAllItems();
				 comboArticle.addItem("");
				 listDetailPrixArticle=detailPrixArticleDAO.findDetaileArticleBySousFamilleByMagasin(sousfamille.getId(),magasin.getId() );

				 for(int i=0;i<listDetailPrixArticle.size();i++)
				 {
					Articles article= listDetailPrixArticle.get(i).getArticles();
					comboArticle.addItem(article.getLiblle());
					 mapArticle.put(article.getLiblle(), article);
					 
				 }
				 
				 
				 
			 } 
   		 
   		 
   	 }
   			
   			 
	 		 }
   		
   		
   		
   	
     		
     	}
     });
    combosousfamille.setSelectedIndex(-1);
    combosousfamille.setBounds(341, 14, 167, 24);
    layeredPane.add(combosousfamille);
    
    int p=0;
    combofamille.addItem("");
    while(p<listFamilleArticle.size())
    {
  	  
  	  FamilleArticlePF famillearticle=listFamilleArticle.get(p);
  	  combofamille.addItem(famillearticle.getLiblle());
  	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
  	  p++;
    }
    
 	
        Depot Depot=depotdao.findByCode(utilisateur.getCodeDepot());
        
        listFournisseur=fournisseurdao.findFournisseurByDepot(Depot);
        int i=0;
        comboparfournisseur.addItem("");
        while(i<listFournisseur.size())
        {
       	Fournisseur fournisseur= listFournisseur.get(i);
       	comboFournisseur.addItem(fournisseur.getNom());
       	comboparfournisseur.addItem(fournisseur.getNom());
       mapFournisseur.put(fournisseur.getNom(), fournisseur);
       i++;
       	 
        }
        comboFournisseur.setSelectedIndex(-1);
      
        
        JLabel label_11 = new JLabel("Famille Article :");
        label_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_11.setBounds(968, 11, 87, 24);
        layeredPane_2.add(label_11);
        
        comboparsousfamille = new JComboBox();
        comboparsousfamille.setBounds(1309, 12, 139, 24);
        comboparsousfamille.addItem("");
        layeredPane_2.add(comboparsousfamille);
        
         comboparfamille = new JComboBox();
        comboparfamille.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		

        		
          		 if(e.getStateChange() == ItemEvent.SELECTED)
       	 		 { 
          			 comboparsousfamille.removeAllItems();
          			comboparsousfamille.addItem(""); 
          		FamilleArticlePF famille=mapfamille.get(comboparfamille.getSelectedItem());
          		if(famille!=null)
          		{
          			
          			listSousFamilleArticle=sousFamilleArticleDAO.listeSousFamillePFByFamilleArticlePF(famille.getId());
          			for(int i=0;i<listSousFamilleArticle.size();i++)
          			{
          			SousFamilleArticlePF sousfamille=listSousFamilleArticle.get(i);
          			comboparsousfamille.addItem(sousfamille.getLiblle());
          			mapsousfamille.put(sousfamille.getLiblle(), sousfamille);
          				
          			}
          			
          		}
          			
          			 
          			 
       	 		 }
       	 			 
          		
           	}
        });
       
        comboparfamille.setBounds(1050, 12, 167, 24);
        layeredPane_2.add(comboparfamille);
        
        int l=0;
	      comboparfamille.addItem("");
	      while(l<listFamilleArticle.size())
	      {
	    	  
	    	  FamilleArticlePF famillearticle=listFamilleArticle.get(l);
	    	  comboparfamille.addItem(famillearticle.getLiblle());
	    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
	    	  l++;
	      }
	     
        
        
        JLabel label_12 = new JLabel("Sous Famille :");
        label_12.setFont(new Font("Tahoma", Font.PLAIN, 12));
        label_12.setBounds(1227, 12, 87, 24);
        layeredPane_2.add(label_12);
        
       
       
	     
	     
	      btninitialiser = new JButton("Initialiser");
	     btninitialiser.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     	initialiser();
	     	}
	     });
	     btninitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     btninitialiser.setBounds(625, 593, 106, 23);
	     add(btninitialiser);
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		txtparnumfacture.setText("");
	     		comboparfournisseur.setSelectedItem("");
	     		pardateChooser.setCalendar(null);
	     		combopardepot.setSelectedItem("");
	     		comboparfamille.setSelectedItem("");
	     		comboparsousfamille.setSelectedItem("");
	     	
	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(709, 109, 106, 23);
	     add(button);
	   
	    if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
			  combopardepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		Magasin magasin=depotdao.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
		     		if(magasin!=null)
		     		{
		     			if(depot.getId()!=magasin.getDepot().getId())
			     		{
		     				combopardepot.addItem(depot.getLibelle());
				     		
		     				mapparDepot.put(depot.getLibelle(), depot);
				     	
				     		
			     			
			     		}
		     		}else
		     		{
		     			combopardepot.addItem(depot.getLibelle());
			     		
		     			mapparDepot.put(depot.getLibelle(), depot);
			     	
			     		
		     		}
		     		k++;
		     		
		     	}
		      
		  }else
		  {
			  Depot depotTmp=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depotTmp!=null)
			  {
				  combopardepot.addItem("");
				  combopardepot.addItem(depotTmp.getLibelle());
				  mapparDepot.put(depotTmp.getLibelle(), depotTmp);
		     	
			  }
		  }
	    
	    
	    comboArticle.addItem("");
	    
	     radiosanstva = new JRadioButton("Sans TVA");
	     radiosanstva.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {

		 		combotva.setSelectedIndex(-1);
		 		combotva.setVisible(false);
		 		
		 	
	     		
	     	}
	     });
	    radiosanstva.setFont(new Font("Tahoma", Font.BOLD, 12));
	    radiosanstva.setBounds(521, 59, 93, 23);
	    layeredPane.add(radiosanstva);
	    group.add(radiosanstva);
	     radioavectva = new JRadioButton("Avec TVA");
	     radioavectva.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) {
	     		

		 		
		 		combotva.setVisible(true);
		 		
		 	
	     		
	     	}
	     });
	    radioavectva.setFont(new Font("Tahoma", Font.BOLD, 12));
	    radioavectva.setBounds(616, 60, 107, 23);
	    layeredPane.add(radioavectva);
	    group.add(radioavectva);
	     combotva = new JComboBox();
	    combotva.setSelectedIndex(-1);
	    combotva.setBounds(729, 61, 218, 24);
	    layeredPane.add(combotva);
	    
	    JButton button_1 = new JButton("Modifier prix");
	    button_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		for(int i=0;i<listFactureAchat.size();i++)
	    		{
	    			
	    		TransferStockPF tarnsferpf = transferStockPFDAO.findByCodeTransfert(listFactureAchat.get(i).getCodeTransfer());
	    		for(int j=0;j<listFactureAchat.get(i).getDetailFactureAchat().size();j++)
	    		{
	    			DetailFactureAchat detailFactureAchat =listFactureAchat.get(i).getDetailFactureAchat().get(j);
	    			
	    			for(int k=0;k<tarnsferpf.getListDetailTransferProduitFini().size();k++)
	    			{
	    				
	    				DetailTransferProduitFini detailTransferProduitFini =tarnsferpf.getListDetailTransferProduitFini().get(k);
	    				
	    				if(detailFactureAchat.getArticle().getId()==detailTransferProduitFini.getArticle().getId() && detailFactureAchat.getSousFamille().getId()==detailTransferProduitFini.getSousFamille().getId())
	    				{
	    					
	    					if(detailTransferProduitFini.getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
	    					{
	    						
	    						detailTransferProduitFini.setPrixUnitaire((detailFactureAchat.getPrixUnitaire().subtract(detailFactureAchat.getPrixUnitaire().multiply(detailFactureAchat.getReduction().divide(new BigDecimal(100),6, RoundingMode.FLOOR)))).divide(detailTransferProduitFini.getArticle().getConditionnement(),6, RoundingMode.FLOOR));
	    						detailTransferStockPFdao.edit(detailTransferProduitFini);
	    						
	    						
	    					}else
		    				{
		    					detailTransferProduitFini.setPrixUnitaire(detailFactureAchat.getPrixUnitaire().subtract(detailFactureAchat.getPrixUnitaire().multiply(detailFactureAchat.getReduction().divide(new BigDecimal(100),6, RoundingMode.FLOOR))));
		    					detailTransferStockPFdao.edit(detailTransferProduitFini);
		    				}
	    					
	    					
	    				}
	    				
	    				
	    				
	    			}
	    			
	    			
	    			
	    		}
	    			
	    			
	    			
	    			
	    		}
	    		
	    		
	    		JOptionPane.showMessageDialog(null, "ok");
	    		
	    		
	    	}
	    });
	    button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    button_1.setBounds(234, 946, 112, 24);
	    button_1.setVisible(false);
	    add(button_1);
	    
	    JButton btnReglerLesPrix = new JButton("Regler Les Prix");
	    btnReglerLesPrix.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		for(int i=0;i<listFactureAchat.size();i++)
	    		{
	    			
	    		FactureAchat factureAchat=listFactureAchat.get(i);
	    		TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(factureAchat.getCodeTransfer());
	    		listDetailFactureAchat=factureAchat.getDetailFactureAchat();
	    		listDetailTransferProduitFini=detailTransferStockPFdao.findByTransferStockPF(transferStockPF.getId())	;
	    			
	    		for(int k=0;k<listDetailFactureAchat.size();k++)	
	    		{
	    			
	    		DetailFactureAchat detailFactureAchat=	listDetailFactureAchat.get(k);
	    		for(int j=0;j<listDetailTransferProduitFini.size();j++)
	    		{
	    			
	    		DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(j);
	    			if(detailFactureAchat.getArticle().getId()==detailTransferProduitFini.getArticle().getId() && detailFactureAchat.getSousFamille().getId()==detailTransferProduitFini.getSousFamille().getId())
	    			{
	    				
	    				if(detailTransferProduitFini.getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
	    				{
	    					
	    					detailTransferProduitFini.setPrixUnitaire(detailFactureAchat.getPrixUnitaire().divide(detailFactureAchat.getArticle().getConditionnement() , 6, RoundingMode.FLOOR));
	    					detailTransferStockPFdao.edit(detailTransferProduitFini);
	    					
	    					
	    				}else
	    				{
	    					detailTransferProduitFini.setPrixUnitaire(detailFactureAchat.getPrixUnitaire());
	    					detailTransferStockPFdao.edit(detailTransferProduitFini);
	    				}
	    				
	    			}
	    			
	    			
	    		}
	    			
	    		}
	    			
	    			
	    			
	    		}
	    		
	    		JOptionPane.showMessageDialog(null, "Modification Terminer");
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    		
	    	}
	    });
	    btnReglerLesPrix.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    btnReglerLesPrix.setBounds(1389, 763, 106, 23);
	    btnReglerLesPrix.setVisible(false);
	    add(btnReglerLesPrix);
		 listArticleStockPF=articleDAO.findAll();
		 for(int j=0;j<listArticleStockPF.size();j++)
		 {
			Articles article= listArticleStockPF.get(j);
			comboArticle.addItem(article.getLiblle());
			 mapArticle.put(article.getLiblle(), article);
			 mapCodeArticle.put(article.getCodeArticle(), article);
		 }
		 
		
		}
	
	
void chargerListFacture()
{
	
	boolean exist=false;
	
	Fournisseur fournisseur=mapFournisseur.get(comboparfournisseur.getSelectedItem());
	Depot depot=mapparDepot.get(combopardepot.getSelectedItem());
	//initialiserFacture();
	initialiser();
	InitialiseTableau();
	listFactureAchat.clear();
		listFactureAchatTmp=factureAchatdao.findByNumFcatureFournisseurDateFactureDepotEtatRegle(txtparnumfacture.getText(),fournisseur, pardateChooser.getDate(), depot,Constantes.ETAT_NON_REGLE);
		
		if(!comboparfamille.getSelectedItem().equals(""))
		{
			
			if(!comboparsousfamille.getSelectedItem().equals(""))
			{
				
				SousFamilleArticlePF sousfamille=mapsousfamille.get(comboparsousfamille.getSelectedItem());
				
				int i=0;
				while (i<listFactureAchatTmp.size())
				{
					FactureAchat factureachat=listFactureAchatTmp.get(i);
					exist=false;
					listDetailFactureAchatTMP=detailFactureAchatdao.listeDetailFactureAchatByFacture(factureachat.getId());
					for(int j=0;j<listDetailFactureAchatTMP.size();j++)
					{
						DetailFactureAchat detailfactureachat=listDetailFactureAchatTMP.get(j);
						
						if(detailfactureachat.getSousFamille().getLiblle().equals(sousfamille.getLiblle()))
						{
							
							exist=true;
							
						}
						
					}
					if(exist==true)
					{
						listFactureAchat.add(factureachat);
					}
					
					i++;
					
				}
				
				
				
				if(listFactureAchat.size()==0)
				{
					//initialiserFacture();
		    		initialiser();
		    		InitialiseTableau();
		    		InitialiseTableauFacture();
				}else
				{
					afficher_tableFactureAchat(listFactureAchat);
				}
			}else
			{
				listFactureAchat.addAll(listFactureAchatTmp);
				if(listFactureAchat.size()==0)
				{
					//initialiserFacture();
					InitialiseTableauFacture();
		    		initialiser();
		    		InitialiseTableau();
				}else
				{
					
					afficher_tableFactureAchat(listFactureAchat);
				}
			}
			
			
		}else
		{
			listFactureAchat.addAll(listFactureAchatTmp);
			if(listFactureAchat.size()==0)
			{
				//initialiserFacture();
				InitialiseTableauFacture();
	    		initialiser();
	    		InitialiseTableau();
			}else
			{
				
				afficher_tableFactureAchat(listFactureAchat);
			}
			
			
		}
		
		
		      listTva=listtvaDAO.findAll();
			for(int k=0;k<listTva.size();k++)
			{
				ListTva listtva=listTva.get(k);
				combotva.addItem(listtva.getCodeTva());
				mapTVA.put(listtva.getCodeTva(), listtva);
				mapTVAparTaux.put(listtva.getTaux().setScale(2), listtva.getCodeTva());
			}
			
			combotva.setSelectedIndex(-1);
			combotva.setVisible(false);
		
		
		
		
	
	
}
	
	
	void initialiserFacture()
	{
		txtnumfacture.setText("");
		dateChooserfacture.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.removeAllItems();
		combomagasin.setSelectedIndex(-1);
		comboparfournisseur.setSelectedItem("");
		
	}

	void initialiser()
	{
		txtcodearticle.setText("");
		comboArticle.setSelectedIndex(-1);
	   txtPrix.setText("");
		txtquantite.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	     combofamille.setSelectedItem("");
	     combosousfamille.removeAllItems();
	   txtquantitepacket.setText("");
	     radiosanstva.setSelected(true);
			combotva.setSelectedIndex(-1);
			combotva.setVisible(false);
		
	}
	
	void initialiserInfofactureEt()
	{
		  txttotalmontantTTC.setText("");
			txttotalquantite.setText("");
			txttotalmontantHT.setText("");
			txttotalmontantTVA.setText("");	
			comboFournisseur.setSelectedIndex(-1);
			txtnumfacture.setText("");
			combodepot.setSelectedItem("");
			combomagasin.setSelectedIndex(-1);
		
		
	}
	
	
	
	void InitialiseTableau()
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		 tableArticle.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableArticle.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableArticle.getColumnModel().getColumn(2).setPreferredWidth(94);
	       
	       initialiserInfofactureEt();
			
	
}
	
	
	void InitialiseTableauFacture()
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num Facture", "Date Facture", "Etat", "Fournisseur", "Depot", "Magasin", "Montant TTC"
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
		
	
}
	
	
	
	void afficher_tableDetailFactureAchat(List<DetailFactureAchat> listDetailFacture)
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		int i=0;
		 
		while(i<listDetailFacture.size())
		{	
			boolean trouve=false;
		DetailFactureAchat detailfactureAchat=listDetailFacture.get(i);
			Articles article=mapArticle.get(detailfactureAchat.getArticle().getLiblle());
			/*
				if(article.getCentreCout2()!=null)
				{
					if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
					{
						trouve=true;
					}
					
				}
			
			if(trouve==true)
			{
				Object []ligne={detailfactureAchat.getSousFamille().getFamileArticlePF().getLiblle(),detailfactureAchat.getSousFamille().getLiblle(),detailfactureAchat.getArticle().getLiblle(),detailfactureAchat.getPrixUnitaire(),detailfactureAchat.getQuantite().divide(article.getConditionnement(),RoundingMode.HALF_UP),detailfactureAchat.getReduction().divide(article.getConditionnement(),RoundingMode.HALF_UP), detailfactureAchat.getMontantHT().divide(article.getConditionnement(),RoundingMode.HALF_UP),detailfactureAchat.getMontantTVA().divide(article.getConditionnement(),RoundingMode.HALF_UP), detailfactureAchat.getMontantTTC().divide(article.getConditionnement(),RoundingMode.HALF_UP)};

				modeleArticle.addRow(ligne);
			}else
			{*/
				Object []ligne={detailfactureAchat.getSousFamille().getFamileArticlePF().getLiblle(),detailfactureAchat.getSousFamille().getLiblle(),detailfactureAchat.getArticle().getLiblle(),detailfactureAchat.getPrixUnitaire(),detailfactureAchat.getQuantite(),detailfactureAchat.getReduction(), detailfactureAchat.getMontantHT(),detailfactureAchat.getMontantTVA(), detailfactureAchat.getMontantTTC()};

				modeleArticle.addRow(ligne);
			/*}*/
			
			i++;
		}
}
	
	
	void ReglerPrixFactureAchat()
	{

		Depot depot=mapparDepot.get(combopardepot.getSelectedItem());
		listFactureAchat=factureAchatdao.findByDepot(depot.getId());
		
		for(int i=0;i<listFactureAchat.size();i++)
		{
			
			BigDecimal MontantTotalHT=BigDecimal.ZERO;
			BigDecimal MontantTotalTVA=BigDecimal.ZERO;
			BigDecimal MontantTotalTTC=BigDecimal.ZERO;
			listDetailFactureAchat=listFactureAchat.get(i).getDetailFactureAchat();
			
			for(int j=0;j<listDetailFactureAchat.size();j++)
			{
				
			DetailFactureAchat detailfactureachatpf=listDetailFactureAchat.get(j);	
			
				if(detailfactureachatpf.getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
				{
					
					detailfactureachatpf.setPrixUnitaire(detailfactureachatpf.getPrixUnitaire().divide(detailfactureachatpf.getArticle().getConditionnement() , 2, RoundingMode.HALF_DOWN));
					detailfactureachatpf.setMontantHT(detailfactureachatpf.getPrixUnitaire().multiply(detailfactureachatpf.getQuantite()));
					detailfactureachatpf.setMontantTVA(detailfactureachatpf.getMontantHT().multiply(detailfactureachatpf.getTva().divide(new BigDecimal(100) , 2, RoundingMode.HALF_DOWN)));
					detailfactureachatpf.setMontantTTC(detailfactureachatpf.getMontantTVA().add(detailfactureachatpf.getMontantHT()));
					detailFactureAchatdao.edit(detailfactureachatpf);
					MontantTotalHT=MontantTotalHT.add(detailfactureachatpf.getMontantHT());
					MontantTotalTVA=MontantTotalTVA.add(detailfactureachatpf.getMontantTVA());
					MontantTotalTTC=MontantTotalTTC.add(detailfactureachatpf.getMontantTTC());
					
					
				}else
				{
					MontantTotalHT=MontantTotalHT.add(detailfactureachatpf.getMontantHT());
					MontantTotalTVA=MontantTotalTVA.add(detailfactureachatpf.getMontantTVA());
					MontantTotalTTC=MontantTotalTTC.add(detailfactureachatpf.getMontantTTC());
				}
				
			}
			
			listFactureAchat.get(i).setMontantHT(MontantTotalHT);
			listFactureAchat.get(i).setMontantTVA(MontantTotalTVA);
			listFactureAchat.get(i).setMontantTTC(MontantTotalTTC);
			factureAchatdao.edit(listFactureAchat.get(i));
			
			TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(listFactureAchat.get(i).getCodeTransfer());
			listDetailTransferProduitFini=detailTransferStockPFdao.findByTransferStockPF(transferStockPF.getId());
			
			for(int k=0;k<listDetailTransferProduitFini.size();k++)
			{
			
				if(listDetailTransferProduitFini.get(k).getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
				{
					
					listDetailTransferProduitFini.get(k).setPrixUnitaire(listDetailTransferProduitFini.get(k).getPrixUnitaire().divide(listDetailTransferProduitFini.get(k).getArticle().getConditionnement() , 6, RoundingMode.HALF_DOWN));
					detailTransferStockPFdao.edit(listDetailTransferProduitFini.get(k));
					
				}
				
			}
			
			
			
			
		}
		
		JOptionPane.showMessageDialog(null, "Modification Terminer Avec Succe");
		
	
	}
	
	
	
	void afficher_tableFactureAchat(List<FactureAchat> listFacture)
	{
		modelefacture =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Num Facture", "Date Facture", "Etat", "Fournisseur", "Depot", "Magasin", "Montant TTC"
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
		int i=0;
		 
		while(i<listFacture.size())
		{	
		FactureAchat factureAchat=listFacture.get(i);
		
		Date datefacture=factureAchat.getDateFacture();
			
			Object []ligne={factureAchat.getNumFacture(),datefacture,factureAchat.getEtat(),factureAchat.getFournisseur().getNom(),factureAchat.getDepot().getLibelle(),factureAchat.getMagasin().getLibelle(),factureAchat.getMontantTTC()};

			modelefacture.addRow(ligne);
			i++;
		}
}
	}


