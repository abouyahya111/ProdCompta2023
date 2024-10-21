package FactureAvoirClientPF;

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

import javax.print.PrintException;
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
import util.ConverterNumberToWords;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureAvoirClientPFDAOImpl;
import dao.daoImplManager.DetailFactureAvoirPFDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAvoirClientPFDAOImpl;
import dao.daoImplManager.FactureAvoirPFDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.ListTvaDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
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
import dao.daoManager.DetailFactureAvoirClientPFDAO;
import dao.daoManager.DetailFactureAvoirPFDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAvoirClientPFDAO;
import dao.daoManager.FactureAvoirPFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.ListTvaDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;
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
import dao.entity.DetailFactureAvoirClientPF;
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FactureAvoirClientPF;
import dao.entity.FactureAvoirPF;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.ListTva;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Sequenceur;
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
import java.io.IOException;
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.JToggleButton;
import javax.swing.JCheckBox;


public class ConsulterFactureAvoirClientPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;

	private JXTable  tableArticle = new JXTable();
	private JXTable table = new JXTable();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private List<DetailFactureAvoirClientPF> listDetailFactureAvoirClientPF =new ArrayList<DetailFactureAvoirClientPF>();
	private List<DetailFactureAvoirClientPF> listDetailFactureAvoirClientPFTMP =new ArrayList<DetailFactureAvoirClientPF>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<Articles> listArticleStockPF =new ArrayList<Articles>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FactureAvoirClientPF> listFactureAvoirClientPF =new ArrayList<FactureAvoirClientPF>();
	private List<FactureAvoirClientPF> listFactureAvoirClientPFTmp =new ArrayList<FactureAvoirClientPF>();
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	 private List<ListTva> listTva =new ArrayList<ListTva>();
	 private Map< String, ListTva> mapTVA= new HashMap<>();
		private Map< BigDecimal, String> mapTVAparTaux= new HashMap<>();
		
	TransferStockPF transferStock ;
	TransferStockPF transferStock_R ;
	private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	private	List<DetailTransferProduitFini> listDetailTransferProduitFini_R= new ArrayList<DetailTransferProduitFini>();
	private TransferStockPFDAO transferStockPFDAO;
	private DetailTransferProduitFiniDAO detailTransferStockPFdao;
	
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, ClientPF> mapClientPF= new HashMap<>();
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	 private ListTvaDAO listtvaDAO ;
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
	private FactureAvoirClientPFDAO factureAvoirClientpfdao;
	private FactureAvoirClientPF factureAvoirClientPF;
private DetailFactureAvoirClientPFDAO detailFactureAvoirClientPFdao;
private ClientPFDAO clientpfdao;
	private  JComboBox combofamille ;
	private JTextField txtcodearticle;
	ChargeProduction chargeproduction;
	private JTextField txtquantitepacket;
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
	 
	 private JComboBox comboClientPF= new JComboBox();
	private JTextField txtparnumfacture;
	private JRadioButton rdbtnDateFacture;
	private JDateChooser pardateChooser;
	private  JComboBox combopardepot;
	private JTextField txttotalmontantTVA;
	private JTextField txttotalmontantHT;

	private JComboBox comboparClientPF= new JComboBox();
	private StockPFDAO stockpfDAO;
	private FamilleArticlesPFDAO familleArticleDAO;
	private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
	private JTextField txtreduction;
	private   JComboBox comboparsousfamille ;
	  JComboBox comboparfamille ;
	  private JTextField txtquantite;
	  JRadioButton radiosanstva = new JRadioButton("Sans TVA");
	  JComboBox combotva = new JComboBox();
	  JRadioButton radioavectva = new JRadioButton("Avec TVA");
	  JRadioButton radioRemiseMoins = new JRadioButton("-");
	  JRadioButton radioRemisePlus = new JRadioButton("+");
	  //Group the radio buttons.
	  //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    ButtonGroup groupremise = new ButtonGroup();
	   
	    JDateChooser datefacture = new JDateChooser();
	    private Map< String, Boolean> maptransfereblfacture = new HashMap<>();
	    private static SequenceurDAO sequenceurDAO;
		private ImageIcon imgSelectAll;
		private ImageIcon imgDeselectAll;
		private ImageIcon imgImprimer;
		private Map< Integer, FactureAvoirClientPF> mapImprimer = new HashMap<>();
		JButton buttonvalider = new JButton("Valider ");
		private CompteClientDAO compteclientdao;
		private JTextField txttimbre;
		JCheckBox checkAppliquerTimbre = new JCheckBox("Appliquer Timbre");
		private JTextField txtnetapayer;
		JButton btnExporterFactureExcel = new JButton("Exporter Facture Excel");
		private ImageIcon imgExcel;
		
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterFactureAvoirClientPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1478, 1142);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
             imgDeselectAll=new ImageIcon(this.getClass().getResource("/img/allDeselect.png"));
             imgSelectAll=new ImageIcon(this.getClass().getResource("/img/allSelect.png"));
             imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
             imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
             
            utilisateur=AuthentificationView.utilisateur;
            detailTransferStockPFdao=new DetailTransferProduitFiniDAOImpl();
            transferStockPFDAO=new TransferStockPFDAOImpl();
            
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	factureAvoirClientpfdao=new FactureAvoirClientPFDAOImpl();
         	detailFactureAvoirClientPFdao=new DetailFactureAvoirClientPFDAOImpl();
         	
         	articleDAO=new ArticlesDAOImpl();
         	stockpfDAO=new StockPFDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         	 listtvaDAO=new ListTvaDAOImpl();
         	clientpfdao=new ClientPFDAOImpl();
         	familleArticleDAO=new FamilleArticlesPFDAOImpl();
         	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
         	listFamilleArticle=familleArticleDAO.findAll();
         	sequenceurDAO=new SequenceurDAOImpl();
         	
         	compteclientdao=new CompteClientDAOImpl();
          } catch (Exception exp){exp.printStackTrace();}
        tableArticle.setSortable(false);
        tableArticle.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		/*combofamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0));
       		combosousfamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
       		comboArticle.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2));
       		txtPrix.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString());
       		txtquantitepacket.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());
       		txtreduction.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString());
       		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
       	
       		
     
       		btnAjouter.setEnabled(false);*/
       		
       		

       		boolean trouve=false;
       		combofamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0));
       		combosousfamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
       		comboArticle.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2));
       		
       		//txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());
       		txtreduction.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString());
       		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
       		Articles article=mapArticle.get(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
       		if(article.getCentreCout2()!=null)
       		{
       			if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
       			{
       				trouve=true;
       			}
       		
       		}
       	
       		if(trouve==true)
       		{
       			txtPrix.setText(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()).multiply(article.getConditionnement())+"");
       			
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
       		}else
       		{
       			txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());	
       			txtPrix.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString());
       		}
       		
       		DetailFactureAvoirClientPF detailfactureavoir=listDetailFactureAvoirClientPF.get(tableArticle.getSelectedRow());
    		if(detailfactureavoir.getTva().setScale(2).equals(BigDecimal.ZERO.setScale(2)))
       		{
       			radiosanstva.setSelected(true);
       			combotva.setVisible(false);
       			combotva.setSelectedIndex(-1);
       			
       		}else
       		{
       			radioavectva.setSelected(true);
       			combotva.setVisible(true);
       			
       			combotva.setSelectedItem(mapTVAparTaux.get(detailfactureavoir.getTva().setScale(2).divide(new BigDecimal(100))));
       			
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
				  		     	scrollPane.setBounds(10, 646, 1375, 211);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 618, 1458, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 500, 1364, 93);
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
		      txtquantitepacket = new JTextField();
		      util.Utils.copycoller(txtquantitepacket);
		      
		      txtquantite = new JTextField();
			    txtquantite.setColumns(10);
			    txtquantite.setBounds(68, 55, 93, 26);
			    layeredPane.add(txtquantite);
		      
		   
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
				 				
				 			}else if(article.getCentreCout2().equals(Constantes.ARTICLE_PIECE))
				 			{
				 				
				 				txtquantite.setEnabled(true);
				 				txtquantitepacket.setEnabled(false);
				 				
				 				
				 			}
				 			
				 			
				 		  				 			
				 		}else
				 		{
				 			txtcodearticle.setText("");
				 			
				 		}
				 	
			 		}
			 		
		       	
		       	
		       	}
		       });
		      comboArticle.setBounds(736, 12, 218, 27);
		      layeredPane.add(comboArticle);
		      AutoCompleteDecorator.decorate(comboArticle);
		     	
		      
		      JLabel lblQuantit = new JLabel("Quantite Packet :");
		      lblQuantit.setBounds(170, 55, 93, 26);
		      layeredPane.add(lblQuantit);
		      
		     
		      txtquantitepacket.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			
		     			
		     		
		      		
		      	}
		      });
		      txtquantitepacket.setColumns(10);
		      txtquantitepacket.setBounds(259, 54, 101, 26);
		      layeredPane.add(txtquantitepacket);
		      
		      JLabel lblPrix = new JLabel("Prix  :");
		      lblPrix.setBounds(370, 54, 45, 26);
		      layeredPane.add(lblPrix);
		      
		      txtPrix = new JTextField();
		      txtPrix.setColumns(10);
		      txtPrix.setBounds(406, 55, 87, 26);
		      layeredPane.add(txtPrix);
		      
		      JLabel lblRemise = new JLabel("Remise :");
		      lblRemise.setBounds(503, 55, 57, 26);
		      layeredPane.add(lblRemise);
		      
		      txtreduction = new JTextField();
		      txtreduction.setColumns(10);
		      txtreduction.setBounds(652, 55, 101, 26);
		      layeredPane.add(txtreduction);
		      
		      JLabel label_8 = new JLabel("%");
		      label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		      label_8.setBounds(763, 53, 26, 26);
		      layeredPane.add(label_8);
		     
		
		 buttonvalider = new JButton("Valider ");
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
			
					
				}else if(comboClientPF.getSelectedIndex()==-1)
				{
					
					JOptionPane.showMessageDialog(null, "Veuillez choisir le Client PF SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
				}
				
				
				else if(listDetailFactureAvoirClientPF.size()==0)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer les articles à facturé SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else
				{
					
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				Magasin magasin =mapMagasin.get(combomagasin.getSelectedItem());
				ClientPF clientpf=mapClientPF.get(comboClientPF.getSelectedItem());
				 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Modifier La facture  ?", 
							"Satisfaction", JOptionPane.YES_NO_OPTION);
					 
					if(reponse == JOptionPane.YES_OPTION )
						
						
					{
						FactureAvoirClientPF factureAvoirpfTmp=listFactureAvoirClientPF.get(table.getSelectedRow());
					//	factureAvoirpfTmp.setNumFacture(txtnumfacture.getText());
						factureAvoirpfTmp.setClientPF(clientpf);
						factureAvoirpfTmp.setDepot(depot);
						factureAvoirpfTmp.setMagasin(magasin);
						factureAvoirpfTmp.setDateFacture(dateChooserfacture.getDate());
						factureAvoirpfTmp.setEtat(Constantes.ETAT_NON_REGLE);
						factureAvoirpfTmp.setType(Constantes.TYPE_BON_LIVRAISON);
						factureAvoirpfTmp.setMontantHT(new BigDecimal(txttotalmontantHT.getText()).setScale(6, RoundingMode.HALF_UP));
						factureAvoirpfTmp.setMontantTVA(new BigDecimal(txttotalmontantTVA.getText()).setScale(6, RoundingMode.HALF_UP));
						factureAvoirpfTmp.setMontantTTC((new BigDecimal(txttotalmontantTTC.getText())).setScale(6, RoundingMode.HALF_UP));	
						//factureAchatTmp.setDetailFactureAchat(listDetailFactureAchat);
						factureAvoirpfTmp.setDateModifier(new Date());
						factureAvoirpfTmp.setModifierPar(utilisateur);
					    factureAvoirClientpfdao.edit(factureAvoirpfTmp);
					    
					    
					    
					    
					    
					    
					    ////////////////////////////////// ajouter detail compte client par client facture ////////////////////////   
					   
						DetailCompteClient detailcompteclient=detailCompteClientdao.findByFactureAvoirClient(factureAvoirpfTmp.getId());
					    detailcompteclient.getCompteClient().setSolde(detailcompteclient.getCompteClient().getSolde().subtract(detailcompteclient.getMontantDebit()));
						compteclientdao.edit(detailcompteclient.getCompteClient());
						detailcompteclient.setCompteClient(clientpf.getCompteClient());
						detailcompteclient.setUtilisateurMAJ(utilisateur);
						detailcompteclient.setDateMaj(new Date());
						
						detailcompteclient.setMontantCredit (new BigDecimal(txttotalmontantTTC.getText()));
						detailcompteclient.setDateOperation(dateChooserfacture.getDate());
						if(factureAvoirpfTmp.getNumFacture()!=null)
						{
							detailcompteclient.setDesignation("Montant sur Facture Avoir N "+factureAvoirpfTmp.getNumFacture());
						}else
						{
							detailcompteclient.setDesignation("Montant sur Facture Avoir N "+factureAvoirpfTmp.getNumBL());
						}
						
						detailcompteclient.setFactureAvoirClientpf (factureAvoirpfTmp);	
					    detailCompteClientdao.edit(detailcompteclient);
					    solde=clientpf.getCompteClient().getSolde().subtract(new BigDecimal(txttotalmontantTTC.getText()));
					    clientpf.getCompteClient().setSolde(solde);
					    compteclientdao.edit(clientpf.getCompteClient());
			
					    
					    
					    for(int j=0;j<listDetailTransferProduitFini.size();j++)
					    {
					    	DetailTransferProduitFini detailtransferpf=listDetailTransferProduitFini.get(j);
					    	detailtransferpf.setDateTransfer(dateChooserfacture.getDate());
					    	detailTransferStockPFdao.edit(detailtransferpf);
					    }
					    if(transferStock!=null)
					    {
					    	   // update transfer stock produit fini 
						    transferStock.setModifierPar(utilisateur);
						    transferStock.setDateModifier(new Date());
						    transferStock.setDateTransfer(dateChooserfacture.getDate());
						  //  transferStock.setListDetailTransferProduitFini(listDetailTransferProduitFini);
						    transferStockPFDAO.edit(transferStock);
					    }
					 
					    
					    

					   
					    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					    
						//// Reglement de Stock 
					    /*
						 Date dateDebut;
						try {
							 detailTransferStockPFdao.ViderSession();
							 dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse("2018"+"-01-01");
							 listeObject=detailTransferStockPFdao.listeStockFinalePF(dateDebut, new Date(), magasin);
							
								
									for(int t=0;t<listeObject.size();t++)
									{	
										 Object[] object=listeObject.get(t);
										
								
										 StockPF stockPF=stockpfDAO.findStockByMagasinPFBySousFamille((int) object[0], magasin.getId(), (int)object[1]);
										stockPF.setStock((BigDecimal)object[2]);
										stockpfDAO.edit(stockPF);
										
									
										 
									}
								 
							 
							
							
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					   */
					    
					    
					    
					    
					    JOptionPane.showMessageDialog(null, "Facture Modifier avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					  //  initialiserFacture();
					    initialiser();
					   
					  
					    listDetailFactureAvoirClientPF.clear();
						InitialiseTableau();
						InitialiseTableauFacture();
						chargerListFacture();	
						
					}
				
				
				
				
			
				}
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(612, 878, 112, 32);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 470, 1458, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 408, 1458, 51);
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
		     					{if(listFactureAvoirClientPF.size()!=0)
		     					{
		     						 factureAvoirClientPF=listFactureAvoirClientPF.get(table.getSelectedRow()) ;
			     						
			     						combomagasin.setSelectedItem(factureAvoirClientPF.getMagasin().getLibelle());
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
		  
		  JLabel lblClientpf = new JLabel("ClientPF :");
		  lblClientpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblClientpf.setBounds(859, 13, 79, 24);
		  layeredPane_1.add(lblClientpf);
		  
		   comboClientPF = new JComboBox();
		  comboClientPF.setSelectedIndex(-1);
		  comboClientPF.setBounds(937, 14, 285, 24);
		  layeredPane_1.add(comboClientPF);
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture");
		titledSeparator_2.setBounds(10, 375, 803, 30);
		add(titledSeparator_2);
		
	  
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(498, 595, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					

					boolean trouve=false;
					//BigDecimal prix=BigDecimal.ZERO;
					BigDecimal Quantite=BigDecimal.ZERO;
					BigDecimal QuantiteTotal=BigDecimal.ZERO;
					BigDecimal QuantiteUnit=BigDecimal.ZERO;
					BigDecimal QuantitePaquet=BigDecimal.ZERO;
					BigDecimal montant=BigDecimal.ZERO;
					BigDecimal marge=BigDecimal.ZERO;
					BigDecimal TVA=BigDecimal.ZERO;
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
				         
				         
				         if(!txtquantitepacket.getText().equals(""))
		        		 {
		        			QuantitePaquet=new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()) ;
		        		 }
		        		 if(!txtquantite.getText().equals(""))
		        		 {
		        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
		        		 }
				         QuantiteTotal=QuantitePaquet.add(QuantiteUnit);
				         
				         
				         
				         
				         if(article.getCentreCout1()!=null)
				         {
				        	 BigDecimal quantite = (QuantitePaquet.add(QuantiteUnit)).setScale(3);
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
				         
				         for(int i=0;i<listDetailFactureAvoirClientPF.size();i++)
				         {
				        	 DetailFactureAvoirClientPF detailFactureAvoirPF =listDetailFactureAvoirClientPF.get(i);
				        	 if(detailFactureAvoirPF.getArticle().getId()==article.getId() && detailFactureAvoirPF.getSousFamille().getId()==sousfamille.getId())
				        	 {
				        		 trouve=true;
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
								
							     
				        	 
				        	// StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasin.getId(),sousfamille.getId());
				        	 
				        		
				        	 
				        	 DetailFactureAvoirClientPF detailFacture=new DetailFactureAvoirClientPF();
				        	 if(!txtreduction.getText().equals(""))
					          {
					        	  detailFacture.setReduction(new BigDecimal(txtreduction.getText()));
					          }else
					          {
					        	  detailFacture.setReduction(BigDecimal.ZERO);  
					          }
				        	 
					          detailFacture.setArticle(article);
					          detailFacture.setQuantite(QuantiteTotal);
					      
					           if(existe==true)
						          {
					        	   detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 6, RoundingMode.DOWN));
					        	   montant=QuantiteTotal.multiply(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 6, RoundingMode.DOWN));
						          }else
						          {
						        	  detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()).setScale(6));
						        	  montant=QuantiteTotal.multiply(new BigDecimal(txtPrix.getText()).setScale(6)); 
						          }
					           if(article.getCodeArticle().equals("1500") || article.getCodeArticle().equals("1503") || article.getCodeArticle().equals("1504") )
						          {

						          
							        detailFacture.setTva(BigDecimal.ZERO);
							        detailFacture.setSousFamille(sousfamille);
							        if(!txtreduction.getText().equals(""))
							          {
							        	
							        	if(radioRemisePlus.isSelected()==true)
							        	{
							        		 detailFacture.setMontantHT(montant.add(montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
										        detailFacture.setMontantTVA(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP).add(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
								        	  detailFacture.setMontantTTC((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).add((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

							        	}else if (radioRemiseMoins.isSelected()==true)
							        	{
							        		 detailFacture.setMontantHT(montant.subtract(montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
										        detailFacture.setMontantTVA(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP).subtract(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
								        	  detailFacture.setMontantTTC((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  
                                     	}else
                                     	{
                                     		

							        		  JOptionPane.showMessageDialog(null, "Veuillez Selectionné le type de Remise + ou - SVP","Attention",JOptionPane.ERROR_MESSAGE);
							        			return;	
                                     	}
							         
							          
							          }else
							          {
							        	  detailFacture.setMontantHT(montant);
									        detailFacture.setMontantTVA(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
							        	  detailFacture.setMontantTTC(((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP));
							          }
							          
						          }else
						          {
					          
						        detailFacture.setTva(TVA.multiply(new BigDecimal(100)));
						        detailFacture.setSousFamille(sousfamille);
						        if(!txtreduction.getText().equals(""))
						          {
						        	if(radioRemisePlus.isSelected()==true)
						        	{
						        		 detailFacture.setMontantHT(montant.add(montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
									        detailFacture.setMontantTVA(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).add(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
							        	  detailFacture.setMontantTTC((((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).add((((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

						        		
						        	}else if(radioRemiseMoins.isSelected()==true)
						        	{
						        		 detailFacture.setMontantHT(montant.subtract(montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
									        detailFacture.setMontantTVA(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).subtract(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
							      
							        	  detailFacture.setMontantTTC((((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

						        		
						        	}else
						        	{
						        		 JOptionPane.showMessageDialog(null, "Veuillez Selectionné le type de Remise + ou - SVP","Attention",JOptionPane.ERROR_MESSAGE);
						        			return;	
						        	}
						        	
						          
						          }else
						          {
						        	  detailFacture.setMontantHT(montant);
								        detailFacture.setMontantTVA(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP));
						        	  detailFacture.setMontantTTC(((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP));
						          }
						          }
					           detailFacture.setFactureAvoirClientPf(factureAvoirClientPF);
					       //    detailFacture.setDateCreation(new Date());
					           
					           detailFacture.setUtilisateur(utilisateur);
					          detailFactureAvoirClientPFdao.add(detailFacture);
					           listDetailFactureAvoirClientPF.add(detailFacture);
					           
					           /*
					           if(stockpf!=null)
					           {
					        	 
					        	   

					        	   if(stockpf.getStock().compareTo(QuantiteTotal)>=0)
					        	   {
					        		   Quantite=stockpf.getStock().subtract(QuantiteTotal);
					        		   if(Quantite.compareTo(BigDecimal.ZERO)<=0)
							        	 {
					        			   Quantite=BigDecimal.ZERO; 
					        			  // prix=stockpf.getPrixUnitaire();
							        		
							        	 }else
							        	 {
							        		 //  prix=((stockpf.getPrixUnitaire().multiply(Quantite.add(quantite))).subtract(quantite.multiply(new BigDecimal(txtPrix.getText())))).divide(Quantite, RoundingMode.HALF_UP);
 
							        	 }
					        		  
					        		   stockpf.setStock(Quantite);
							         //  stockpf.setPrixUnitaire(prix);
							           listStockPF.add(stockpf);
					        		   
					        		   
					        	   }else
					        	   {
					        		   JOptionPane.showMessageDialog(null, "la Quantité d'article est supérieur de celle de stock !!!!!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
					        		   return;
					        	   }
					        	 
					            }else
					            {
					            	 JOptionPane.showMessageDialog(null, "Article introuvable dans le stock !!!!!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
					        		   return;
					            
					            }
					           */
					           
					           
					          afficher_tableDetailFactureAchat(listDetailFactureAvoirClientPF);
					          int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO;
						        BigDecimal timber=BigDecimal.ZERO;
						        BigDecimal netapayer=BigDecimal.ZERO;
						        while(i<listDetailFactureAvoirClientPF.size())
						        {
						        	articleboisson=false;
						        	
										if(listDetailFactureAvoirClientPF.get(i).getArticle().getCentreCout2()!=null)
										{
											if(listDetailFactureAvoirClientPF.get(i).getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
											{
												articleboisson=true;
											}
											
										}
									
						        	
						        	if(articleboisson==true)
						        	{
						        		 DetailFactureAvoirClientPF detailFactureAvoirPF=listDetailFactureAvoirClientPF.get(i);
								      /*    montanttotal=  montanttotal.add(detailFactureAvoirPF.getMontantTTC().divide(detailFactureAvoirPF.getArticle().getConditionnement(),RoundingMode.HALF_UP));
								          sommequantite= sommequantite.add(detailFactureAvoirPF.getQuantite().divide(detailFactureAvoirPF.getArticle().getConditionnement(),RoundingMode.HALF_UP));
								          montanttotalHT=montanttotalHT.add(detailFactureAvoirPF.getMontantHT().divide(detailFactureAvoirPF.getArticle().getConditionnement(),RoundingMode.HALF_UP));
								          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirPF.getMontantTVA().divide(detailFactureAvoirPF.getArticle().getConditionnement(),RoundingMode.HALF_UP));*/
						        		 
						        		  montanttotal=  montanttotal.add(detailFactureAvoirPF.getMontantTTC());
								          sommequantite= sommequantite.add(detailFactureAvoirPF.getQuantite());
								          montanttotalHT=montanttotalHT.add(detailFactureAvoirPF.getMontantHT());
								          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirPF.getMontantTVA());
						        		 
						        		 
						        	}else
						        	{
						        	
						        	 DetailFactureAvoirClientPF detailFactureAvoirPF=listDetailFactureAvoirClientPF.get(i);
							          montanttotal=  montanttotal.add(detailFactureAvoirPF.getMontantTTC());
							          sommequantite= sommequantite.add(detailFactureAvoirPF.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFactureAvoirPF.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirPF.getMontantTVA());
						        	}
						            i++;
						        }
						       txttotalmontantTTC.setText(montanttotal+"");
						        txttotalquantite.setText(sommequantite+"");
						        txttotalmontantHT.setText(montanttotalHT+"");
					  			txttotalmontantTVA.setText(montanttotalTVA+"");
					  			if(checkAppliquerTimbre.isSelected()==true)
		  						{
		  							
		  							timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
									timber=timber.multiply(new BigDecimal(txttotalmontantTTC.getText()));
		  						}else
		  						{
		  							timber=BigDecimal.ZERO;
		  						}
					  			
					  			
					  			
					  			txttimbre.setText(timber.setScale(2, RoundingMode.HALF_UP)+"");
								netapayer=timber.add(new BigDecimal(txttotalmontantTTC.getText()));
					  			txtnetapayer.setText(netapayer.setScale(2, RoundingMode.HALF_UP)+"");
					  			
					  		/// ajout transfer stock PF (Mouvement Stock PF)
					  			DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
					  			detailTransferStockPF.setArticle(article);
					  			detailTransferStockPF.setSousFamille(sousfamille);
								detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
								detailTransferStockPF.setMagasinDestination(magasin);
								detailTransferStockPF.setQuantite(QuantiteTotal);
								
								if(existe==true)
								{
									detailTransferStockPF.setPrixUnitaire((detailFacture.getMontantHT().divide(detailFacture.getQuantite() , 6, RoundingMode.DOWN)));
									
								}else
								{
									detailTransferStockPF.setPrixUnitaire(detailFacture.getMontantHT().divide(detailFacture.getQuantite() , 6, RoundingMode.DOWN));
								}
								
								detailTransferStockPF.setTransferStockPF(transferStock);
								detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_AVOIR_CLIENT);
								detailTransferStockPFdao.add(detailTransferStockPF);
								listDetailTransferProduitFini.add(detailTransferStockPF);	
								
						
								
								
							//////////////////////////////////////////////////////////////////////////	
								
								
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
		   			
		   			boolean articleboisson=false;
			   		BigDecimal nouveauprix=BigDecimal.ZERO;
			   		BigDecimal coutAchat=BigDecimal.ZERO;
					BigDecimal coutStock=BigDecimal.ZERO;
					BigDecimal QuantiteTotal=BigDecimal.ZERO;
					//BigDecimal oldPrix=BigDecimal.ZERO;
					BigDecimal oldQuantite=BigDecimal.ZERO;
					BigDecimal montant=BigDecimal.ZERO;
			   		BigDecimal stocktmp=BigDecimal.ZERO;
			   		BigDecimal marge=BigDecimal.ZERO;
			   		BigDecimal TVA=BigDecimal.ZERO;
			   		BigDecimal QuantiteUnit=BigDecimal.ZERO;
					BigDecimal QuantitePaquet=BigDecimal.ZERO;
			   		
			   		if(tableArticle.getSelectedRow()!=-1)
			   		{
			   			boolean trouve=false;
						
						if(comboArticle.getSelectedItem().equals("") || comboArticle.getSelectedIndex()==-1)
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
					         
					         if(!txtquantitepacket.getText().equals(""))
			        		 {
			        			QuantitePaquet=new BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()) ;
			        		 }
			        		 if(!txtquantite.getText().equals(""))
			        		 {
			        			QuantiteUnit=new BigDecimal(txtquantite.getText()) ;
			        		 }
					         QuantiteTotal=QuantitePaquet.add(QuantiteUnit);
					         
					         if(article.getCentreCout1()!=null)
					         {
					        	 BigDecimal quantite = (QuantitePaquet.add(QuantiteUnit)).setScale(3);
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
					         
					         for(int i=0;i<listDetailFactureAvoirClientPF.size();i++)
					         {
					        	 if(i!=tableArticle.getSelectedRow())
					        	 {
					        		 
					        		 DetailFactureAvoirClientPF detailFactureAvoirpf =listDetailFactureAvoirClientPF.get(i);
						        	 if(detailFactureAvoirpf.getArticle().getId()==article.getId() && detailFactureAvoirpf.getSousFamille().getId()==sousfamille.getId())
						        	 {
						        		 trouve=true;
						        	 }
						        	  
					        		 
					        	 }
					        	 
					        	 
					         }
					         
					         if(trouve==false)
					         {
					        	 boolean existe=false;
					        	 
					        	// StockPF stockpftmp =stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()).getArticle().getId(), listFactureAvoirPF.get(table.getSelectedRow()).getMagasin().getId(), listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()).getSousFamille().getId()) ; 
					        	
					        	// coutAchat=listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()).getQuantite().multiply(listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()).getPrixUnitaire());
					        	 // coutStock=stockpftmp.getStock().multiply(stockpftmp.getPrixUnitaire());
					        	 // QuantiteTotal=listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()).getQuantite().add(stockpftmp.getStock());
					        	   
					        	 // nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotal, 6, RoundingMode.HALF_UP);
					        	//  stockpftmp.setStock(QuantiteTotal);
					        	//  stockpftmp.setPrixUnitaire(nouveauprix);
					        	 // stockpfDAO.edit(stockpftmp);
					        	 
					        	 
					        	// StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasin.getId(),sousfamille.getId());
					        	 
					        		
					            		if(article.getCentreCout2()!=null)
					            		{
					            			if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
					            			{
					            				existe=true;
					            			}
					            			
					            		}
					            		
					            	
					        		
					        	 DetailFactureAvoirClientPF detailFacture= listDetailFactureAvoirClientPF.get(tableArticle.getSelectedRow());
					        	 
					        	 
					        	 DetailTransferProduitFini detailTransferStockPF=null;
					        	 
					        	 if(listDetailTransferProduitFini.size()!=0)
					        	 {
					        		 detailTransferStockPF= listDetailTransferProduitFini.get(tableArticle.getSelectedRow());
					        		 
					        	 }
					        	
					        	
					        	 
					        	  if(!txtreduction.getText().equals(""))
						          {
						        	  detailFacture.setReduction(new BigDecimal(txtreduction.getText()));
						          }else
						          {
						        	  detailFacture.setReduction(BigDecimal.ZERO);  
						          }
					        	 
						           detailFacture.setArticle(article);
						         
						        detailFacture.setQuantite(QuantiteTotal);  
						          
						          
						          
						           if(existe==true)
						           {
						        	   detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 6, RoundingMode.DOWN));
						        	   montant=QuantiteTotal.multiply(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 6, RoundingMode.DOWN));
						        	   
						           }else
						           {
						        	   detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
						        	   montant=QuantiteTotal.multiply(new BigDecimal(txtPrix.getText()));
						           }
						          
						           if(article.getCodeArticle().equals("1500") || article.getCodeArticle().equals("1503") || article.getCodeArticle().equals("1504") )
							          {

							         
								        detailFacture.setTva(BigDecimal.ZERO);
								        detailFacture.setSousFamille(sousfamille);
								        if(!txtreduction.getText().equals(""))
								          {
								        	
								        	if(radioRemisePlus.isSelected()==true)
								        	{
								        		  detailFacture.setMontantHT(montant.add(montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											        detailFacture.setMontantTVA(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP).add(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
								        		
									        	  detailFacture.setMontantTTC((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).add((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

								        		
								        	}else if(radioRemiseMoins.isSelected()==true)
								        	{
								        		 detailFacture.setMontantHT(montant.subtract(montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											        detailFacture.setMontantTVA(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP).subtract(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
								        		
									        	  detailFacture.setMontantTTC((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

								        		
								        	}else
								        	{
								        		
								        		JOptionPane.showMessageDialog(null, "Veuillez Selectionné le type de Remise + ou - SVP","Attention",JOptionPane.ERROR_MESSAGE);
							        			return;	
								        		
								        	}
								         
								          
								          
								          }else
								          {
								        	  detailFacture.setMontantHT(montant);
										        detailFacture.setMontantTVA(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
								        	  detailFacture.setMontantTTC(((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP));
								          }
								          
							          }else
							          {
						              detailFacture.setTva(TVA.multiply(new BigDecimal(100)));
							       detailFacture.setSousFamille(sousfamille);
							       
							          if(!txtreduction.getText().equals(""))
							          {
							        	  if(radioRemisePlus.isSelected()==true)
								        	{
							        		  
							        		   detailFacture.setMontantHT(montant.add(montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
										       detailFacture.setMontantTVA(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).add(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
										 
								        	  detailFacture.setMontantTTC((((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).add((((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

								        	}else if(radioRemiseMoins.isSelected()==true)
								        		
								        	{
								        		 detailFacture.setMontantHT(montant.subtract(montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											       detailFacture.setMontantTVA(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).subtract(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											 
									        	  detailFacture.setMontantTTC((((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

								        	}else
								        	{
								        		
								        		JOptionPane.showMessageDialog(null, "Veuillez Selectionné le type de Remise + ou - SVP","Attention",JOptionPane.ERROR_MESSAGE);
							        			return;
							        			
								        	}
							        	  
							         
							          
							          
							          }else
							          {
							        	  detailFacture.setMontantHT(montant);
									       detailFacture.setMontantTVA(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP));
									 
							        	  detailFacture.setMontantTTC(((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP));
							          }
							          }
						           detailFacture.setFactureAvoirClientPf(factureAvoirClientPF);
						       //    detailFacture.setDateCreation(new Date());
						           
						           detailFacture.setUtilisateur(utilisateur);	
						           
						           if(detailTransferStockPF!=null)
						           {
						        	   
						        	   /////////////////// // Modifier Transfer Stock PF  ////////////////////////////////
								        detailTransferStockPF.setArticle(article);
								        detailTransferStockPF.setSousFamille(sousfamille);
										detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
										detailTransferStockPF.setMagasinDestination(magasin);
										 detailTransferStockPF.setQuantite(QuantiteTotal);
										  if(existe==true)
							        	   {
											  detailTransferStockPF.setPrixUnitaire((detailFacture.getMontantHT().divide(detailFacture.getQuantite() , 6, RoundingMode.DOWN)));
											 
							        	   }else
							        	   {
							        		   detailTransferStockPF.setPrixUnitaire(detailFacture.getMontantHT().divide(detailFacture.getQuantite() , 6, RoundingMode.DOWN));
							        	   }
												
										detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_AVOIR_CLIENT);
										detailTransferStockPFdao.edit(detailTransferStockPF);
										listDetailTransferProduitFini.set(tableArticle.getSelectedRow(), detailTransferStockPF);
										
										//////////////////////////////////////////////////////////////////////////////
						           }
						       
									
						
									
									/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
									
						           
						           /*
						           if(stockpf!=null)
						           {
						        	   BigDecimal quantite=BigDecimal.ZERO;
							        	  
						        	   if(stockpf.getStock().compareTo(QuantiteTotal)>=0)
						        	   {
						        		   oldQuantite=stockpf.getStock().subtract(QuantiteTotal);
						        		   if(oldQuantite.compareTo(BigDecimal.ZERO)<=0)
								        	 {
						        			   oldQuantite=BigDecimal.ZERO; 
						        			  // oldPrix=stockpf.getPrixUnitaire();
								        		
								        	 }else
								        	 {
								        		  // oldPrix=((stockpf.getPrixUnitaire().multiply(oldQuantite.add(quantite))).subtract(quantite.multiply(new BigDecimal(txtPrix.getText())))).divide(oldQuantite, RoundingMode.HALF_UP);

								        	 }
						        		 
						        		   stockpf.setStock(oldQuantite);
								           //stockpf.setPrixUnitaire(oldPrix);
								           listStockPF.add(stockpf);
						        		   
						        		   
						        	   }else
						        	   {
						        		   JOptionPane.showMessageDialog(null, "la Quantité d'article est supérieur de celle de stock !!!!!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
						        		   return;
						        	   }
						        	 
						            
							           listStockPF.set(tableArticle.getSelectedRow(), stockpf);
						           }  else
						           {

						        	   
						        	   JOptionPane.showMessageDialog(null, "Article introuvable dans le stock !!!!!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
					        		   return;
						        	   
						           }
						           
						           */
						         
						           detailFactureAvoirClientPFdao.edit(detailFacture);
						           listDetailFactureAvoirClientPF.set(tableArticle.getSelectedRow(), detailFacture);
						          afficher_tableDetailFactureAchat(listDetailFactureAvoirClientPF);
						          int i=0;
							        BigDecimal montanttotal=BigDecimal.ZERO;
							        BigDecimal sommequantite=BigDecimal.ZERO;
							        BigDecimal montanttotalHT=BigDecimal.ZERO;
							        BigDecimal montanttotalTVA=BigDecimal.ZERO;
							        BigDecimal timber=BigDecimal.ZERO;
							        BigDecimal netapayer=BigDecimal.ZERO;
							        
							        while(i<listDetailFactureAvoirClientPF.size())
							        {
							        	
							        	articleboisson=false;
							        	
											if(listDetailFactureAvoirClientPF.get(i).getArticle().getCentreCout2()!=null)
											{
												if(listDetailFactureAvoirClientPF.get(i).getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
												{
													articleboisson=true;
												}
												
											}
										
							        	
							        	if(articleboisson==true)
							        	{
							        		 DetailFactureAvoirClientPF detailFactureAvoirPF=listDetailFactureAvoirClientPF.get(i);
							        		 montanttotal=  montanttotal.add(detailFactureAvoirPF.getMontantTTC());
									          sommequantite= sommequantite.add(detailFactureAvoirPF.getQuantite());
									          montanttotalHT=montanttotalHT.add(detailFactureAvoirPF.getMontantHT());
									          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirPF.getMontantTVA());
							        	}else
							        	{
							        	
							        	 DetailFactureAvoirClientPF detailFactureAvoirPF=listDetailFactureAvoirClientPF.get(i);
								          montanttotal=  montanttotal.add(detailFactureAvoirPF.getMontantTTC());
								          sommequantite= sommequantite.add(detailFactureAvoirPF.getQuantite());
								          montanttotalHT=montanttotalHT.add(detailFactureAvoirPF.getMontantHT());
								          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirPF.getMontantTVA());
							        	}
							            i++;
							        }
							       txttotalmontantTTC.setText(montanttotal+"");
							        txttotalquantite.setText(sommequantite+"");
							        txttotalmontantHT.setText(montanttotalHT+"");
						  			txttotalmontantTVA.setText(montanttotalTVA+"");
						  			
						  			if(checkAppliquerTimbre.isSelected()==true)
			  						{
			  							
			  							timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
										timber=timber.multiply(new BigDecimal(txttotalmontantTTC.getText()));
			  						}else
			  						{
			  							timber=BigDecimal.ZERO;
			  						}
						  			
						  			
						  			
						  			txttimbre.setText(timber.setScale(2, RoundingMode.HALF_UP)+"");
									netapayer=timber.add(new BigDecimal(txttotalmontantTTC.getText()));
						  			txtnetapayer.setText(netapayer.setScale(2, RoundingMode.HALF_UP)+"");
						  			
						  			
						  			
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
				BigDecimal nouveauprix=BigDecimal.ZERO;
		   		BigDecimal coutAchat=BigDecimal.ZERO;
				BigDecimal coutStock=BigDecimal.ZERO;
				BigDecimal QuantiteTotal=BigDecimal.ZERO;
				boolean articleboisson=false;
		  		if(tableArticle.getSelectedRow()!=-1)
		  		{
		  			
		  			 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer l'article dans la facture  ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
						{
							/*
							 StockPF stockpftmp =listStockPF.get(tableArticle.getSelectedRow());
							 coutAchat=listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()).getQuantite().multiply(listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()).getPrixUnitaire());
				        	  coutStock=stockpftmp.getStock().multiply(stockpftmp.getPrixUnitaire());
				        	  QuantiteTotal=listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()).getQuantite().add(stockpftmp.getStock());
				        	   
				        	  nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotal, 6, RoundingMode.HALF_UP);
				        	  stockpftmp.setStock(QuantiteTotal);
				        	  stockpftmp.setPrixUnitaire(nouveauprix);
				        	  stockpfDAO.edit(stockpftmp);
*/
							 
                            DetailFactureAvoirClientPF detailfactureavoirpf=listDetailFactureAvoirClientPF.get(tableArticle.getSelectedRow());
					  		detailFactureAvoirClientPFdao.delete(detailfactureavoirpf.getId());
					  		listDetailFactureAvoirClientPF.remove(tableArticle.getSelectedRow());
					  		//listStockPF.set(tableArticle.getSelectedRow(),stockpftmp);
					  		if(listDetailTransferProduitFini.size()!=0)
					  		{
					  	  		detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(tableArticle.getSelectedRow()).getId());
						  		listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
					  		}
					
					  		
					 
					  		
					  		
					  		
					        afficher_tableDetailFactureAchat(listDetailFactureAvoirClientPF);
					          int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO;
						        BigDecimal timber=BigDecimal.ZERO;
						        BigDecimal netapayer=BigDecimal.ZERO;
						        
						        while(i<listDetailFactureAvoirClientPF.size())
						        {
						         	articleboisson=false;
						        	
										if(listDetailFactureAvoirClientPF.get(i).getArticle().getCentreCout2()!=null)
										{
											if(listDetailFactureAvoirClientPF.get(i).getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
											{
												articleboisson=true;
											}
											
										}
									
					        	 
						        	if(articleboisson==true)
						        	{
						        		 DetailFactureAvoirClientPF detailFactureAvoirpf=listDetailFactureAvoirClientPF.get(i);
						        		   montanttotal=  montanttotal.add(detailFactureAvoirpf.getMontantTTC());
									          sommequantite= sommequantite.add(detailFactureAvoirpf.getQuantite());
									          montanttotalHT=montanttotalHT.add(detailFactureAvoirpf.getMontantHT());
									          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirpf.getMontantTVA());
						        	}else
						        	{
						        	
						        	 DetailFactureAvoirClientPF detailFactureAvoirpf=listDetailFactureAvoirClientPF.get(i);
							          montanttotal=  montanttotal.add(detailFactureAvoirpf.getMontantTTC());
							          sommequantite= sommequantite.add(detailFactureAvoirpf.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFactureAvoirpf.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirpf.getMontantTVA());
						        	}
						            i++;
						        }
						       txttotalmontantTTC.setText(montanttotal+"");
						       txttotalquantite.setText(sommequantite+"");
					  			txttotalmontantHT.setText(montanttotalHT+"");
					  			txttotalmontantTVA.setText(montanttotalTVA+"");
					  			if(checkAppliquerTimbre.isSelected()==true)
		  						{
		  							
		  							timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
									timber=timber.multiply(new BigDecimal(txttotalmontantTTC.getText()));
		  						}else
		  						{
		  							timber=BigDecimal.ZERO;
		  						}
					  			
					  			
					  			
					  			txttimbre.setText(timber.setScale(2, RoundingMode.HALF_UP)+"");
								netapayer=timber.add(new BigDecimal(txttotalmontantTTC.getText()));
					  			txtnetapayer.setText(netapayer.setScale(2, RoundingMode.HALF_UP)+"");
					  			
					  			
					  			JOptionPane.showMessageDialog(null, "Article supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					  			initialiser();
						}
		  			
		  		}
		  		
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1395, 723, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(1109, 929, 112, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(1231, 929, 136, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(1002, 875, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(895, 875, 97, 26);
		add(lblTotalQuantite);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1395, 693, 73, 24);
		add(btnModifier);
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter les Factures d'Avoir Client PF par :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(151, 11, 1234, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1324, 218);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow()!=-1)
				{
					boolean articleboisson=false;
					if(listFactureAvoirClientPF.size()!=0)
					{
						listStockPF.clear();
						 factureAvoirClientPF=listFactureAvoirClientPF.get(table.getSelectedRow()) ;
						 
						 
						 
						 transferStock=transferStockPFDAO.findByCodeTransfert(factureAvoirClientPF.getCodeTransfer());
						 if(transferStock!=null)
						 {
							 listDetailTransferProduitFini=detailTransferStockPFdao.findByTransferStockPF(transferStock.getId());
						 }
						 
						 
						 
				
						 
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
						 if(factureAvoirClientPF.getNumFacture()!=null)
						 {
							 txtnumfacture.setText(factureAvoirClientPF.getNumFacture()); 
						 }else
						 {
							 txtnumfacture.setText(factureAvoirClientPF.getNumBL()); 
						 }
						
						dateChooserfacture.setDate(factureAvoirClientPF.getDateFacture());
						
						combodepot.setSelectedItem(factureAvoirClientPF.getDepot().getLibelle());
						
						
						combomagasin.setSelectedItem(factureAvoirClientPF.getMagasin().getLibelle());
						comboClientPF.setSelectedItem(factureAvoirClientPF.getClientPF().getNom());
						listDetailFactureAvoirClientPF=detailFactureAvoirClientPFdao.listeDetailFactureAvoirByFacture(factureAvoirClientPF.getId());
						
						 int i=0;
					        BigDecimal montanttotal=BigDecimal.ZERO;
					        BigDecimal sommequantite=BigDecimal.ZERO;
					        BigDecimal montanttotalHT=BigDecimal.ZERO;
					        BigDecimal montanttotalTVA=BigDecimal.ZERO;
					        BigDecimal timber=BigDecimal.ZERO;
					        BigDecimal netapayer=BigDecimal.ZERO;
					        while(i<listDetailFactureAvoirClientPF.size())
					        {
					        	/*
					        	 StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFactureAvoirPF.get(i).getArticle().getId(),factureAvoirPF.getMagasin().getId(),listDetailFactureAvoirPF.get(i).getSousFamille().getId());
					        	listStockPF.add(stockpf);
					        */
							/*
							 * if(listDetailFactureAvoirPF.get(i).getArticle().getCentreCout2()!=null) {
							 * if(listDetailFactureAvoirPF.get(i).getArticle().getCentreCout2().equals(
							 * Constantes.ARTICLE_PACKET)) { articleboisson=true; }
							 * 
							 * }
							 */
								
				        	 
					        /*	if(articleboisson==true)
					        	{
					        		 DetailFactureAvoirPF detailFactureAvoirpf=listDetailFactureAvoirPF.get(i);
							          montanttotal=  montanttotal.add(detailFactureAvoirpf.getMontantTTC().divide(detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP));
							          sommequantite= sommequantite.add(detailFactureAvoirpf.getQuantite().divide(detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP));
							          montanttotalHT=montanttotalHT.add(detailFactureAvoirpf.getMontantHT().divide(detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP));
							          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirpf.getMontantTVA().divide(detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP));
					        	}else
					        	{*/
					        		  DetailFactureAvoirClientPF detailFactureAvoirpf=listDetailFactureAvoirClientPF.get(i);
							          montanttotal=  montanttotal.add(detailFactureAvoirpf.getMontantTTC());
							          sommequantite= sommequantite.add(detailFactureAvoirpf.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFactureAvoirpf.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirpf.getMontantTVA());
						            
							/* } */
					        	
					            i++;
					        }
					        
					    	
					        
					        txttotalmontantTTC.setText(montanttotal+"");
					        txttotalquantite.setText(sommequantite+"");
					        txttotalmontantHT.setText(montanttotalHT+"");
				  			txttotalmontantTVA.setText(montanttotalTVA+"");
				  			
				  			if(checkAppliquerTimbre.isSelected()==true)
	  						{
	  							
	  							timber=Constantes.TIMBER.divide(new BigDecimal(100), 6, RoundingMode.HALF_UP);
								timber=timber.multiply(new BigDecimal(txttotalmontantTTC.getText()));
	  						}else
	  						{
	  							timber=BigDecimal.ZERO;
	  						}
				  			
				  			
				  			
				  			txttimbre.setText(timber.setScale(2, RoundingMode.HALF_UP)+"");
							netapayer=timber.add(new BigDecimal(txttotalmontantTTC.getText()));
				  			txtnetapayer.setText(netapayer.setScale(2, RoundingMode.HALF_UP)+"");
				  			
				  			
						afficher_tableDetailFactureAchat(listDetailFactureAvoirClientPF);
						
						
						if(factureAvoirClientPF.getType().equals(Constantes.TRANSFERE_BL_FACTURE) )
						{
							
							btnAjouter.setEnabled(false);
							btnModifier.setEnabled(false);
							btnSupprimer.setEnabled(false);
							buttonvalider.setEnabled(false);
						}else
						{
							btnAjouter.setEnabled(true);
							btnModifier.setEnabled(true);
							btnSupprimer.setEnabled(true);
							buttonvalider.setEnabled(true);
							initialiser();
						}
						
						
						
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
				"Num BL","Num Facture", "Date Facture", "Etat", "Client PF", "Depot", "Magasin", "Montant TTC","Transfere BL en Facture"
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
		
	    JButton btnAfficher = new JButton("Consulter");
	    btnAfficher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		detailFactureAvoirClientPFdao.ViderSession();
	    		if(combopardepot.getSelectedItem().equals(""))
	    		{
	    			JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    	if(comboparClientPF.getSelectedItem().equals("")
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
	    btnAfficher.setBounds(581, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLabel label_2 = new JLabel("Total Montant TVA :");
	    label_2.setBounds(1109, 901, 105, 26);
	    add(label_2);
	    
	    txttotalmontantTVA = new JTextField();
	    txttotalmontantTVA.setEditable(false);
	    txttotalmontantTVA.setColumns(10);
	    txttotalmontantTVA.setBounds(1231, 901, 136, 26);
	    add(txttotalmontantTVA);
	    
	    txttotalmontantHT = new JTextField();
	    txttotalmontantHT.setEditable(false);
	    txttotalmontantHT.setColumns(10);
	    txttotalmontantHT.setBounds(1231, 869, 136, 26);
	    add(txttotalmontantHT);
	    
	    JLabel label_5 = new JLabel("Total Montant HT :");
	    label_5.setBounds(1109, 869, 105, 26);
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
	     
	     JLabel lblClientPf = new JLabel("Client PF :");
	     lblClientPf.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     lblClientPf.setBounds(242, 11, 79, 24);
	     layeredPane_2.add(lblClientPf);
	     
	      comboparClientPF = new JComboBox();
	     comboparClientPF.setSelectedIndex(-1);
	     comboparClientPF.setBounds(320, 12, 149, 24);
	     layeredPane_2.add(comboparClientPF);
	     
	
    
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
   				 listStockPF=stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
   				 for(int i=0;i<listStockPF.size();i++)
   				 {
   					Articles article= listStockPF.get(i).getArticles();
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
        
        listClientPF=clientpfdao.findListClientByCodeDepot (Depot.getCode());
        int i=0;
        comboparClientPF.addItem("");
        while(i<listClientPF.size())
        {
       	ClientPF clientpf= listClientPF.get(i);
       	comboClientPF.addItem(clientpf.getNom());
       	comboparClientPF.addItem(clientpf.getNom());
       mapClientPF.put(clientpf.getNom(), clientpf);
       i++;
       	 
        }
        comboClientPF.setSelectedIndex(-1);
      
        
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
	     btninitialiser.setBounds(625, 595, 106, 23);
	     add(btninitialiser);
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		txtparnumfacture.setText("");
	     		comboparClientPF.setSelectedItem("");
	     		pardateChooser.setCalendar(null);
	     		combopardepot.setSelectedItem("");
	     		comboparfamille.setSelectedItem("");
	     		comboparsousfamille.setSelectedItem("");
	     	
	     	}
	     });
	     button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     button.setBounds(711, 120, 106, 23);
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
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combopardepot.addItem("");
				  combopardepot.addItem(depot.getLibelle());
				  mapparDepot.put(depot.getLibelle(), depot);
		     	
			  }
		  }
	    
	    
	    comboArticle.addItem("");
	    
	    JLabel label_13 = new JLabel("Quantite :");
	    label_13.setBounds(10, 55, 72, 26);
	    layeredPane.add(label_13);
	    
	  
	    
	     radiosanstva = new JRadioButton("Sans TVA");
	    radiosanstva.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
		  		combotva.setSelectedIndex(-1);
		 		combotva.setVisible(false);
		  		
	    		
	    	}
	    });
	    radiosanstva.setFont(new Font("Tahoma", Font.BOLD, 12));
	    radiosanstva.setBounds(790, 57, 93, 23);
	    layeredPane.add(radiosanstva);
	    group.add(radiosanstva);
	     radioavectva = new JRadioButton("Avec TVA");
	    radioavectva.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {

		  		
		  		combotva.setVisible(true);
		  		
		  	
	    		
	    	}
	    });
	    radioavectva.setFont(new Font("Tahoma", Font.BOLD, 12));
	    radioavectva.setBounds(889, 57, 93, 23);
	    layeredPane.add(radioavectva);
	    group.add(radioavectva);
	     combotva = new JComboBox();
	    combotva.setSelectedIndex(-1);
	    combotva.setBounds(988, 57, 174, 24);
	    layeredPane.add(combotva);
		 listArticleStockPF=articleDAO.findAll();
		 for(int j=0;j<listArticleStockPF.size();j++)
		 {
			Articles article= listArticleStockPF.get(j);
			comboArticle.addItem(article.getLiblle());
			 mapArticle.put(article.getLiblle(), article);
			 mapCodeArticle.put(article.getCodeArticle(), article);
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
				
				 radioRemisePlus = new JRadioButton("+");
				radioRemisePlus.setFont(new Font("Tahoma", Font.BOLD, 12));
				radioRemisePlus.setBounds(548, 58, 40, 23);
				layeredPane.add(radioRemisePlus);
				groupremise.add(radioRemisePlus);
				 radioRemiseMoins = new JRadioButton("-");
				radioRemiseMoins.setFont(new Font("Tahoma", Font.BOLD, 15));
				radioRemiseMoins.setBounds(601, 58, 40, 23);
				layeredPane.add(radioRemiseMoins);
				groupremise.add(radioRemiseMoins);
				
				JButton btnModifierPrixTransfert = new JButton("Modifier Prix Transfert");
				btnModifierPrixTransfert.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						
						for(int i=0;i<listFactureAvoirClientPF.size();i++ )
						{
							
							
							FactureAvoirClientPF factureAvoirPF=listFactureAvoirClientPF.get(i);
							
							listDetailFactureAvoirClientPF=detailFactureAvoirClientPFdao.listeDetailFactureAvoirByFacture(factureAvoirPF.getId());
							
							TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(factureAvoirPF.getCodeTransfer());
							if(transferStockPF!=null)
							{
								
								listDetailTransferProduitFini=detailTransferStockPFdao.findByTransferStockPF(transferStockPF.getId());
								
								for(int j=0;j<listDetailFactureAvoirClientPF.size();j++)
								{
									
									
								DetailFactureAvoirClientPF detailFactureAvoirPF=	listDetailFactureAvoirClientPF.get(j);
								
								for(int d=0;d<listDetailTransferProduitFini.size() ;d++)
								{
									
									DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(d);
									
									if(detailFactureAvoirPF.getArticle().getId()==detailTransferProduitFini.getArticle().getId() && detailFactureAvoirPF.getSousFamille().getId()==detailTransferProduitFini.getSousFamille().getId())
									{
										
										detailTransferProduitFini.setPrixUnitaire(detailFactureAvoirPF.getMontantHT().divide(detailFactureAvoirPF.getQuantite() , 6, RoundingMode.DOWN));	
										detailTransferStockPFdao.edit(detailTransferProduitFini);
										
									}
									
									
									
									
									
									
									
								}
									
									
									
									
									
								}
							}
					
							
		////////////////////////////////////////////////////////////// transfertPF_R //////////////////////////////////////////////////////////////
							
							
TransferStockPF transferStockPF_R=transferStockPFDAO.findByCodeTransfert(factureAvoirPF.getCodeTransfer()+"_R");
							if(transferStockPF_R!=null)
							{
								
								listDetailTransferProduitFini_R=detailTransferStockPFdao.findByTransferStockPF(transferStockPF_R.getId());	
								
								for(int j=0;j<listDetailFactureAvoirClientPF.size();j++)
								{
									
									
								DetailFactureAvoirClientPF detailFactureAvoirPF=	listDetailFactureAvoirClientPF.get(j);
								
								for(int d=0;d<listDetailTransferProduitFini_R.size() ;d++)
								{
									
									DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini_R.get(d);
									
									if(detailFactureAvoirPF.getArticle().getId()==detailTransferProduitFini.getArticle().getId() && detailFactureAvoirPF.getSousFamille().getId()==detailTransferProduitFini.getSousFamille().getId())
									{
										
										detailTransferProduitFini.setPrixUnitaire(detailFactureAvoirPF.getMontantHT().divide(detailFactureAvoirPF.getQuantite() , 6, RoundingMode.DOWN));	
										detailTransferStockPFdao.edit(detailTransferProduitFini);
										
									}
									
									
									
									
									
									
									
								}
									
									
									
									
									
								}
								
								
								
								
								
							}
								
							
							
						}
						
						
						JOptionPane.showMessageDialog(null, "ok");
						
						
						
					}
				});
				btnModifierPrixTransfert.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnModifierPrixTransfert.setBounds(372, 119, 147, 25);
				add(btnModifierPrixTransfert);
				
				JButton button_1 = new JButton("Initialiser Date");
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						

			    		
			    		datefacture.setCalendar(null);
			    		
			    	
						
						
					}
				});
				button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				button_1.setBounds(863, 378, 107, 24);
				add(button_1);
				
				JLabel label_6 = new JLabel("Date Facture:");
				label_6.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
				label_6.setBounds(979, 381, 97, 24);
				add(label_6);
				
				 datefacture = new JDateChooser();
				datefacture.setLocale(Locale.FRANCE);
				datefacture.setDateFormatString("dd/MM/yyyy");
				datefacture.setBounds(1074, 379, 151, 26);
				add(datefacture);
				
				JButton button_2 = new JButton("Transfere BL en Facture");
				button_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						

						
						
						/////////////////////////////////////////////////////////////////////////////////////// Numerotation 2020 //////////////////////////////////////////////////////////////////////
						
			               boolean transferEnBL=false;
				    		String critere="AV";
			               
				    		maptransfereblfacture.clear();
				    		if(!remplirmaptransfereblfacture())	{
								JOptionPane.showMessageDialog(null, "Il faut remplir les BL à transferer au facture", "Erreur", JOptionPane.ERROR_MESSAGE);
							} else {
								
								if(maptransfereblfacture.size()>=2)
								{
									 int reponse = JOptionPane.showConfirmDialog(null, "Voulez Vous Facturer les BL en une seule facture ?", 
												"Satisfaction", JOptionPane.YES_NO_OPTION);
										 
										if(reponse == JOptionPane.YES_OPTION )		
											
										{
											
											/// Numerotation 2019
											SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
											
											///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
										
			                             //	FacturePF facturepfTmp=listFacturePF.get(line);
			                             	 String date =""; 
			                             	 String NumFacture="";
			                             	 if(datefacture.getDate()!=null)
			                             	 {
			                             		date= dcn.format(datefacture.getDate());
			                             		//NumFacture=critere+Utils.genererCodeFactureAvoir(date);
			                             		
			                             		NumFacture=Utils.genererCodeFactureVente(date);
			                             		
			                             	 }else
			                             	 {
			                             		 JOptionPane.showMessageDialog (null, "Veuillez selectionner la date facture SVP !!!!!","Erreur", JOptionPane.ERROR_MESSAGE);
			                             		 return;
			                             	 }
			                             	 
											for(int i=0;i<listFactureAvoirClientPF.size();i++)
											{
												FactureAvoirClientPF facturepf=listFactureAvoirClientPF.get(i);
												if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
												{
													if(maptransfereblfacture.containsKey(facturepf.getNumBL()))
													{
														if(facturepf.getNumFacture()==null)
														{
															facturepf.setNumFacture(NumFacture);
															facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
															facturepf.setDateFacture(datefacture.getDate());
															
															facturepf.setEtat(ETAT_REGLE);
															
															factureAvoirClientpfdao.edit(facturepf);			
															transferEnBL=true;
														}else
														{
															facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
															facturepf.setEtat(ETAT_REGLE);
															factureAvoirClientpfdao.edit(facturepf);	
														}
														
													}
												}
													
											}
											
											
											if(transferEnBL==true)
											{
												
												if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
													
												{
													Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_ETP);
													if(sequenceur!=null)
													{
														int valeur=sequenceur.getValeur()+1;
														sequenceur.setValeur(valeur);
														sequenceurDAO.edit(sequenceur);
													}else
													{
														Sequenceur  sequenceurTmp=new Sequenceur();
														sequenceurTmp.setCode(date);
														sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
														sequenceurTmp.setValeur(1);
														sequenceurDAO.add(sequenceurTmp);
													}
													
												}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
												{
													Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_AHB);
													if(sequenceur!=null)
													{
														int valeur=sequenceur.getValeur()+1;
														sequenceur.setValeur(valeur);
														sequenceurDAO.edit(sequenceur);
													}else
													{
														Sequenceur  sequenceurTmp=new Sequenceur();
														sequenceurTmp.setCode(date);
														sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
														sequenceurTmp.setValeur(1);
														sequenceurDAO.add(sequenceurTmp);
													}
													
												}
												
												
												
												JOptionPane.showMessageDialog(null, "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
												//listFacturePFMAJ=facturepfdao.findAll();
												
												chargerListFacture();
											}else
											{
												JOptionPane.showMessageDialog(null, "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
												return;
												
											}
											
											
										}else
										{
											
											for(int i=0;i<listFactureAvoirClientPF.size();i++)
											{
												FactureAvoirClientPF facturepf=listFactureAvoirClientPF.get(i);
												
												if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
												{
													if(maptransfereblfacture.containsKey(facturepf.getNumBL()))
													{
														/// Numerotation 2019
														SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
														
														///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
														
														if(datefacture.getDate()!=null)
														{
														
															facturepf.setDateFacture(datefacture.getDate());	
															
														}
														
													
														 String date = dcn.format(facturepf.getDateFacture());
														 if(facturepf.getNumFacture()==null)
															{
															// facturepf.setNumFacture(critere+Utils.genererCodeFactureAvoir(date));
															 facturepf.setNumFacture( Utils.genererCodeFactureVente(date));
															
																facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
																facturepf.setEtat(ETAT_REGLE);
																factureAvoirClientpfdao.edit(facturepf);
																if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
																	
																{
																	Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_ETP);
																	if(sequenceur!=null)
																	{
																		int valeur=sequenceur.getValeur()+1;
																		sequenceur.setValeur(valeur);
																		sequenceurDAO.edit(sequenceur);
																	}else
																	{
																		Sequenceur  sequenceurTmp=new Sequenceur();
																		sequenceurTmp.setCode(date);
																		sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
																		sequenceurTmp.setValeur(1);
																		sequenceurDAO.add(sequenceurTmp);
																	}
																	
																}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
																{
																	Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_AHB);
																	if(sequenceur!=null)
																	{
																		int valeur=sequenceur.getValeur()+1;
																		sequenceur.setValeur(valeur);
																		sequenceurDAO.edit(sequenceur);
																	}else
																	{
																		Sequenceur  sequenceurTmp=new Sequenceur();
																		sequenceurTmp.setCode(date);
																		sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
																		sequenceurTmp.setValeur(1);
																		sequenceurDAO.add(sequenceurTmp);
																	}
																	
																}
																
																transferEnBL=true;
															}else
															{
																
																facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
																facturepf.setEtat(ETAT_REGLE);
																factureAvoirClientpfdao.edit(facturepf);
																transferEnBL=true;
																
															}
													
													}
												}
												
											}
											
											
											if(transferEnBL==true)
											{
												JOptionPane.showMessageDialog(null, "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
												//listFacturePFMAJ=facturepfdao.findAll();
												
												chargerListFacture();
											}else
											{
												JOptionPane.showMessageDialog(null, "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
												return;
												
											}
											
											
											
										}
									
									
								}else
								{
									
									
									for(int i=0;i<listFactureAvoirClientPF.size();i++)
									{
										FactureAvoirClientPF facturepf=listFactureAvoirClientPF.get(i);
										if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
										{
											
											if(maptransfereblfacture.containsKey(facturepf.getNumBL()))
											{
												/// Numerotation 2019
												SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
												
												///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
												
												
												if(datefacture.getDate()!=null)
												{
												
													facturepf.setDateFacture(datefacture.getDate());	
													
												}
												
												
											
												 String date = dcn.format(facturepf.getDateFacture());
												 
												 if(facturepf.getNumFacture()==null)
													{
													
													// facturepf.setNumFacture(critere+Utils.genererCodeFactureAvoir(date));
													 facturepf.setNumFacture(Utils.genererCodeFactureVente(date));
													 facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
														facturepf.setEtat(ETAT_REGLE);
														factureAvoirClientpfdao.edit(facturepf);
														if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
															
														{
															Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_ETP);
															if(sequenceur!=null)
															{
																int valeur=sequenceur.getValeur()+1;
																sequenceur.setValeur(valeur);
																sequenceurDAO.edit(sequenceur);
															}else
															{
																Sequenceur  sequenceurTmp=new Sequenceur();
																sequenceurTmp.setCode(date);
																sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
																sequenceurTmp.setValeur(1);
																sequenceurDAO.add(sequenceurTmp);
															}
															
														}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
														{
															Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date,  Constantes.CODE_FACTURE_VENTE_AHB);
															if(sequenceur!=null)
															{
																int valeur=sequenceur.getValeur()+1;
																sequenceur.setValeur(valeur);
																sequenceurDAO.edit(sequenceur);
															}else
															{
																Sequenceur  sequenceurTmp=new Sequenceur();
																sequenceurTmp.setCode(date);
																sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
																sequenceurTmp.setValeur(1);
																sequenceurDAO.add(sequenceurTmp);
															}
															
														}
														
														transferEnBL=true;
													 
													 
													 
													}else
													{
														
														facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
														facturepf.setEtat(ETAT_REGLE);
														factureAvoirClientpfdao.edit(facturepf);
														transferEnBL=true;
													}
												
											}
											
										}
									
									}
									
									
									if(transferEnBL==true)
									{
										JOptionPane.showMessageDialog(null, "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.INFORMATION_MESSAGE);
									//	listFacturePFMAJ=facturepfdao.findAll();
										
										chargerListFacture();
									}else
									{
										JOptionPane.showMessageDialog(null, "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
										return;
										
									}
									
								}
								
								datefacture.setCalendar(null);
									
							}
				    		
						
						
				////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
						
						
		//////////////////////////////////////////////////////////////////////////////////////// New Numerotation 2019 ///////////////////////////////////////////////				
						/*
						
						  // New Numerotation 2019
						  
						  boolean transferEnBL=false; Parametre
						  nombre_facture_par_jour=parametreDAO.findByCode(Constantes. NOMBRE_FACTURE_PAR_JOUR);
						  
						  maptransfereblfacture.clear(); 
						  if(!remplirmaptransfereblfacture()) { JOptionPane.showMessageDialog(null,
						  "Il faut remplir les BL à transferer au facture", "Erreur",
						  JOptionPane.ERROR_MESSAGE); }
						  else {
						  
						  if(maptransfereblfacture.size()>=2) { int reponse =
						  JOptionPane.showConfirmDialog(null,
						  "Voulez Vous Facturer les BL en une seule facture ?", "Satisfaction",
						  JOptionPane.YES_NO_OPTION);
						  
						  if(reponse == JOptionPane.YES_OPTION )
						  
						  {
						  
						  /// Numerotation 2019 //// 
							  SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
						  
						  /// Numerotation 2018 SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
						  
						  // FacturePF facturepfTmp=listFacturePF.get(line); 
							  String date =""; 
							  String NumFacture=""; Date d=null;
						  
						  if(datefacture.getDate()!=null) 
						  {
							  d=datefacture.getDate(); 
						  date= dcn.format(d); 
						  NumFacture=Utils.genererCodeFactureVente(d);
						  
						  if(NumFacture.equals("")) {
						  
						  d=util.DateUtils.ajoutNbJours(d, 1); 
						  Calendar cal=Calendar.getInstance();
						  cal.setTime(d);
						  
						  if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
						  d=util.DateUtils.ajoutNbJours(d, 1); 
						  } while(NumFacture.equals("")) {
						  
						  NumFacture=Utils.genererCodeFactureVente(d);
						  
						  if(!NumFacture.equals("")) { date = dcn.format(d); break;
						  
						  }else {
						  
						  d= util.DateUtils.ajoutNbJours(d, 1);
						  Calendar calTmp=Calendar.getInstance();
						  calTmp.setTime(d);
						  
						  if(calTmp.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
						  d=util.DateUtils.ajoutNbJours(d, 1); }
						  
						  
						  
						  }
						  
						  
						  };
						  
						  }
						  
						  }else { JOptionPane.showMessageDialog (null,
						  "Veuillez selectionner la date facture SVP !!!!!","Erreur",
						  JOptionPane.ERROR_MESSAGE); return; }
						  
						  
						  Calendar c = Calendar.getInstance();
						  
						  c.setTime(d); int nbrjrs=c.get(Calendar.DAY_OF_YEAR); 
						  int nbrsunday = nbrjrs / 7; 
						  int nbrjrssansweekend=nbrjrs-nbrsunday;
						  
						  
						  for(int i=0;i<listFacturePF.size();i++) { FacturePF
						  facturepf=listFacturePF.get(i);
						  if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
						  if(maptransfereblfacture.containsKey(facturepf.getNumBl())) {
						  if(facturepf.getNumFacture()==null) { facturepf.setNumFacture(NumFacture);
						  facturepf.setDateFacture(d);
						  facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
						  facturepfdao.edit(facturepf); transferEnBL=true; }else {
						  facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
						  
						  facturepfdao.edit(facturepf); }
						  
						  } }
						  
						  }
						  
						  
						  if(transferEnBL==true) {
						  
						  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
						  
						  { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
						  Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
						  valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
						  sequenceurDAO.edit(sequenceur); }else { int valeur=((nbrjrssansweekend-1) *
						  nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
						  sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
						  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
						  sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
						  
						  }else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
						  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
						  Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
						  valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
						  sequenceurDAO.edit(sequenceur); }else { int valeur=((nbrjrssansweekend-1) *
						  nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
						  sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
						  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
						  sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
						  
						  }
						  
						  
						  
						  JOptionPane.showMessageDialog(null,
						  "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.
						  INFORMATION_MESSAGE); listFacturePFMAJ=facturepfdao.findAll();
						  
						  chargerListeFacture(); }else {
						  JOptionPane.showMessageDialog(null,"Bon(s) de Livraison déja facturé"
						  ,"Erreur",JOptionPane.ERROR_MESSAGE); return;
						  
						  }
						  
						  
						  }else {
						  
						  for(int i=0;i<listFacturePF.size();i++) { FacturePF
						  facturepf=listFacturePF.get(i);
						  
						  if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
						  if(maptransfereblfacture.containsKey(facturepf.getNumBl())) { ///Numerotation 2019 /// 
							  SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
						  
						  /// Numerotation 2018 SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
						  Date d=null;
						  d=facturepf.getDateFacture(); String date = dcn.format(d);
						  if(facturepf.getNumFacture()==null) {
						  
						  Calendar c = Calendar.getInstance();
						  
						  String NumFacture=Utils.genererCodeFactureVente(d);
						  
						  if(NumFacture.equals("")) {
						  
						  d=util.DateUtils.ajoutNbJours(d, 1); Calendar cal=Calendar.getInstance();
						  cal.setTime(d);
						  
						  if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
						  d=util.DateUtils.ajoutNbJours(d, 1); }
						  
						  while(NumFacture.equals("")) {
						  
						  NumFacture=Utils.genererCodeFactureVente(d);
						  
						  if(!NumFacture.equals("")) { date = dcn.format(d);
						  facturepf.setDateFacture(d); break;
						  
						  }else {
						  
						  d= util.DateUtils.ajoutNbJours(d, 1);
						  
						  Calendar calTmp=Calendar.getInstance(); calTmp.setTime(d);
						  
						  if(calTmp.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
						  d=util.DateUtils.ajoutNbJours(d, 1); }
						  
						  }
						  
						  
						  };
						  
						  }
						  
						  
						  c.setTime(d); int nbrjrs=c.get(Calendar.DAY_OF_YEAR); int nbrsunday = nbrjrs
						  / 7; int nbrjrssansweekend=nbrjrs-nbrsunday;
						  
						  facturepf.setNumFacture(NumFacture);
						  facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
						  facturepfdao.edit(facturepf);
						  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
						  
						  { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
						  Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
						  valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
						  sequenceurDAO.edit(sequenceur); }else { int valeur=((nbrjrssansweekend-1) *
						  nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
						  sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
						  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
						  sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
						  
						  }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
						  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,Constantes.
						  CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
						  valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
						  sequenceurDAO.edit(sequenceur); } else { int valeur=((nbrjrssansweekend-1) *
						  nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
						  sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
						  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
						  sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
						  
						  }
						  
						  transferEnBL=true; } else {
						  
						  facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
						  facturepfdao.edit(facturepf); transferEnBL=true;
						  
						  }
						  
						  } }
						  
						  }
						  
						  
						  if(transferEnBL==true) { JOptionPane.showMessageDialog(null,
						  "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.
						  INFORMATION_MESSAGE); listFacturePFMAJ=facturepfdao.findAll();
						  
						  chargerListeFacture(); } else { JOptionPane.showMessageDialog(null,
						  "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
						  return;
						  
						  }
						  
						  
						  
						  }
						  
						  
						  }else {
						  
						  
						  for(int i=0;i<listFacturePF.size();i++) { FacturePF
						  facturepf=listFacturePF.get(i);
						  if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
						  
						  if(maptransfereblfacture.containsKey(facturepf.getNumBl())) {
						  
						  //SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
						  
						  
						  SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd"); Date d=null;
						  d=facturepf.getDateFacture(); 
						  String date = dcn.format(d);
						  if(facturepf.getNumFacture()==null) {
						  
						  String NumFacture=Utils.genererCodeFactureVente(d);
						  
						  if(NumFacture.equals("")) {
						  
						  d =util.DateUtils.ajoutNbJours(facturepf.getDateFacture(), 1); Calendar
						  cal=Calendar.getInstance(); cal.setTime(d);
						  
						  if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
						  d=util.DateUtils.ajoutNbJours(d, 1); } while(NumFacture.equals("")) {
						  
						  NumFacture=Utils.genererCodeFactureVente(d);
						  
						  if(!NumFacture.equals("")) { date = dcn.format(d);
						  facturepf.setDateFacture(d); break; }else {
						  
						  d= util.DateUtils.ajoutNbJours(d, 1); Calendar calTmp=Calendar.getInstance();
						  calTmp.setTime(d);
						  
						  if(calTmp.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
						  d=util.DateUtils.ajoutNbJours(d, 1); }
						  
						  }
						  
						  
						  };
						  
						  }
						  
						  Calendar c = Calendar.getInstance();
						  
						  c.setTime(d); int nbrjrs=c.get(Calendar.DAY_OF_YEAR); int nbrsunday = nbrjrs
						  / 7; int nbrjrssansweekend=nbrjrs-nbrsunday;
						  
						  facturepf.setNumFacture(NumFacture);
						  facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
						  facturepfdao.edit(facturepf);
						  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
						  
						  { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
						  Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
						  valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
						  sequenceurDAO.edit(sequenceur); }else { int valeur=((nbrjrssansweekend-1) *
						  nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
						  sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
						  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
						  sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
						  
						  }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
						  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
						  Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
						  valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
						  sequenceurDAO.edit(sequenceur); }else { int valeur=((nbrjrssansweekend-1) *
						  nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
						  sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
						  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
						  sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
						  
						  }
						  
						  transferEnBL=true;
						  
						  
						  
						  }else {
						  
						  facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
						  facturepfdao.edit(facturepf); transferEnBL=true; }
						  
						  }
						  
						  }
						  
						  }
						  
						  
						  if(transferEnBL==true) { JOptionPane.showMessageDialog(
						  null,"Bon de Livraison Transférer en Facture avec succée","Bravo",
						  JOptionPane. INFORMATION_MESSAGE); listFacturePFMAJ=facturepfdao.findAll();
						  
						  chargerListeFacture(); }else {
						  JOptionPane.showMessageDialog(null,"Bon(s) de Livraison déja facturé"
						  ,"Erreur",JOptionPane.ERROR_MESSAGE); return;
						  
						  }
						  
						  }
						  
						  datefacture.setCalendar(null);
						  
						  }
						  
						  
						  */
						 
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			  
						  
						 
						

						/*Numerotation 2019
						 * 
						 * boolean transferEnBL=false;
						 * 
						 * 
						 * maptransfereblfacture.clear(); if(!remplirmaptransfereblfacture()) {
						 * JOptionPane.showMessageDialog(null,
						 * "Il faut remplir les BL à transferer au facture", "Erreur",
						 * JOptionPane.ERROR_MESSAGE); } else {
						 * 
						 * if(maptransfereblfacture.size()>=2) { int reponse =
						 * JOptionPane.showConfirmDialog(null,
						 * "Voulez Vous Facturer les BL en une seule facture ?", "Satisfaction",
						 * JOptionPane.YES_NO_OPTION);
						 * 
						 * if(reponse == JOptionPane.YES_OPTION )
						 * 
						 * {
						 * 
						 * /// Numerotation 2019 SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
						 * 
						 * ///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
						 * 
						 * // FacturePF facturepfTmp=listFacturePF.get(line); String date =""; String
						 * NumFacture=""; if(datefacture.getDate()!=null) { date=
						 * dcn.format(datefacture.getDate());
						 * NumFacture=Utils.genererCodeFactureVente(date);
						 * 
						 * }else { JOptionPane.showMessageDialog (null,
						 * "Veuillez selectionner la date facture SVP !!!!!","Erreur",
						 * JOptionPane.ERROR_MESSAGE); return; }
						 * 
						 * for(int i=0;i<listFacturePF.size();i++) { FacturePF
						 * facturepf=listFacturePF.get(i);
						 * if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
						 * if(maptransfereblfacture.containsKey(facturepf.getNumBl())) {
						 * if(facturepf.getNumFacture()==null) {
						 * facturepf.setDateFacture(datefacture.getDate());
						 * facturepf.setNumFacture(NumFacture);
						 * facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
						 * facturepfdao.edit(facturepf); transferEnBL=true; }else {
						 * facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
						 * facturepfdao.edit(facturepf); }
						 * 
						 * } }
						 * 
						 * }
						 * 
						 * 
						 * if(transferEnBL==true) {
						 * 
						 * if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
						 * 
						 * { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
						 * Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
						 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
						 * sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
						 * Sequenceur(); sequenceurTmp.setCode(date);
						 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
						 * sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
						 * 
						 * }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
						 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
						 * Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
						 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
						 * sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
						 * Sequenceur(); sequenceurTmp.setCode(date);
						 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
						 * sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
						 * 
						 * }
						 * 
						 * 
						 * 
						 * JOptionPane.showMessageDialog(null,
						 * "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.
						 * INFORMATION_MESSAGE); listFacturePFMAJ=facturepfdao.findAll();
						 * 
						 * chargerListeFacture(); }else { JOptionPane.showMessageDialog(null,
						 * "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
						 * return;
						 * 
						 * }
						 * 
						 * 
						 * }else {
						 * 
						 * for(int i=0;i<listFacturePF.size();i++) { FacturePF
						 * facturepf=listFacturePF.get(i);
						 * 
						 * if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
						 * if(maptransfereblfacture.containsKey(facturepf.getNumBl())) { ///
						 * Numerotation 2019 SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
						 * 
						 * ///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
						 * 
						 * String date = dcn.format(facturepf.getDateFacture());
						 * if(facturepf.getNumFacture()==null) {
						 * facturepf.setNumFacture(Utils.genererCodeFactureVente(date));
						 * facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
						 * facturepfdao.edit(facturepf);
						 * if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
						 * 
						 * { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
						 * Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
						 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
						 * sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
						 * Sequenceur(); sequenceurTmp.setCode(date);
						 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
						 * sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
						 * 
						 * }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
						 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
						 * Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
						 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
						 * sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
						 * Sequenceur(); sequenceurTmp.setCode(date);
						 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
						 * sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
						 * 
						 * }
						 * 
						 * transferEnBL=true; }else {
						 * 
						 * facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
						 * facturepfdao.edit(facturepf); transferEnBL=true;
						 * 
						 * }
						 * 
						 * } }
						 * 
						 * }
						 * 
						 * 
						 * if(transferEnBL==true) { JOptionPane.showMessageDialog(null,
						 * "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.
						 * INFORMATION_MESSAGE); listFacturePFMAJ=facturepfdao.findAll();
						 * 
						 * chargerListeFacture(); }else { JOptionPane.showMessageDialog(null,
						 * "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
						 * return;
						 * 
						 * }
						 * 
						 * 
						 * 
						 * }
						 * 
						 * 
						 * }else {
						 * 
						 * 
						 * for(int i=0;i<listFacturePF.size();i++) { FacturePF
						 * facturepf=listFacturePF.get(i);
						 * if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
						 * 
						 * if(maptransfereblfacture.containsKey(facturepf.getNumBl())) { ///
						 * Numerotation 2019 SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
						 * 
						 * ///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
						 * 
						 * String date = dcn.format(facturepf.getDateFacture());
						 * if(facturepf.getNumFacture()==null) {
						 * 
						 * facturepf.setNumFacture(Utils.genererCodeFactureVente(date));
						 * facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
						 * facturepfdao.edit(facturepf);
						 * if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
						 * 
						 * { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
						 * Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
						 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
						 * sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
						 * Sequenceur(); sequenceurTmp.setCode(date);
						 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
						 * sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
						 * 
						 * }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
						 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
						 * Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
						 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
						 * sequenceurDAO.edit(sequenceur); }else { Sequenceur sequenceurTmp=new
						 * Sequenceur(); sequenceurTmp.setCode(date);
						 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
						 * sequenceurTmp.setValeur(1); sequenceurDAO.add(sequenceurTmp); }
						 * 
						 * }
						 * 
						 * transferEnBL=true;
						 * 
						 * 
						 * 
						 * }else {
						 * 
						 * facturepf.setType(Constantes.TRANSFERE_BL_FACTURE);
						 * facturepfdao.edit(facturepf); transferEnBL=true; }
						 * 
						 * }
						 * 
						 * }
						 * 
						 * }
						 * 
						 * 
						 * if(transferEnBL==true) { JOptionPane.showMessageDialog(null,
						 * "Bon de Livraison Transférer en Facture avec succée","Bravo",JOptionPane.
						 * INFORMATION_MESSAGE); listFacturePFMAJ=facturepfdao.findAll();
						 * 
						 * chargerListeFacture(); }else { JOptionPane.showMessageDialog(null,
						 * "Bon(s) de Livraison déja facturé","Erreur",JOptionPane.ERROR_MESSAGE);
						 * return;
						 * 
						 * }
						 * 
						 * }
						 * 
						 * datefacture.setCalendar(null);
						 * 
						 * }
						 */
				    		
				    		
					
						
						
						
						
					}
				});
				button_2.setFont(new Font("Tahoma", Font.BOLD, 11));
				button_2.setBounds(1234, 375, 167, 30);
				add(button_2);
				
				JButton btnDeslectionnerTout = new JButton();
				btnDeslectionnerTout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						

			     		

			     		
			     		
			     		for(int i=0;i<table.getRowCount();i++)
			     		{
			     			table.setValueAt(false, i, 8);
			     		}
			     	
			     	
					}
				});
				btnDeslectionnerTout.setToolTipText("deselectionner Tout");
				btnDeslectionnerTout.setBounds(1263, 117, 26, 26);
				 btnDeslectionnerTout.setIcon(imgDeselectAll);
				add(btnDeslectionnerTout);
				
				JButton btnSelectionnertout = new JButton();
				btnSelectionnertout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {


			    		
			    		for(int i=0;i<table.getRowCount();i++)
			     		{
			     			table.setValueAt(true, i, 8);
			     		}
			    	
			     		
			     	
					}
				});
				btnSelectionnertout.setToolTipText("Selectionner Tout");
				btnSelectionnertout.setBounds(1292, 117, 26, 26);
				 btnSelectionnertout.setIcon(imgDeselectAll);
				add(btnSelectionnertout);
				
				JButton button_3 = new JButton("Imprimer");
				button_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						

						
						mapImprimer.clear();
			    		if(!remplirMapImprimer())	{
							JOptionPane.showMessageDialog(null, "Il faut remplir les Facture à imprimer SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
						} else {
							
							boolean apercu=false;
							
							 int reponse = JOptionPane.showConfirmDialog(null, "Voulez Vous Afficher l'aperçu Avant l'impression ?", 
										"Satisfaction", JOptionPane.YES_NO_OPTION);
								 
								if(reponse == JOptionPane.YES_OPTION )		
									
								{
									apercu=true;
								}
							
							
							
							for(int k=0;k<listFactureAvoirClientPF.size();k++)
							{
								FactureAvoirClientPF facturepfTmp=listFactureAvoirClientPF.get(k);
								
								
								
								
								
								
								
								if(mapImprimer.containsKey(facturepfTmp.getId()))
								{
									
									///////////////////////////////////////////////
									
									if(facturepfTmp.getType().equals(Constantes.TRANSFERE_BL_FACTURE) )
									{
										
										
										
										
										detailFactureAvoirClientPFdao.ViderSession();
										String sommetowords="";

										List<DetailFactureAvoirClientPF> listDetailFacturePFImprimer =new ArrayList<DetailFactureAvoirClientPF>();
										List<DetailFactureAvoirClientPF> listDetailFacturePFImprimerTmp =new ArrayList<DetailFactureAvoirClientPF>();
										   List<DetailFactureAvoirClientPF> listDetailFacturePFTmp =new ArrayList<DetailFactureAvoirClientPF>();
										 listFactureAvoirClientPFTmp.clear();
										 if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
										 {
											 listDetailFacturePFTmp=detailFactureAvoirClientPFdao.listeDetailFactureAvoirByFacture(facturepfTmp.getId());
											 
										 }else
										 {
											 listFactureAvoirClientPFTmp=factureAvoirClientpfdao.findByNumFactureByDepot(facturepfTmp.getNumFacture(), facturepfTmp.getDepot());
											 listDetailFacturePFTmp=detailFactureAvoirClientPFdao.listeDetailFactureAvoirClientPFByNumFacture(facturepfTmp.getNumFacture(), facturepfTmp.getMagasin());
											 
										 }
										    BigDecimal montanttotal=BigDecimal.ZERO;
									        BigDecimal montanttotalHT=BigDecimal.ZERO;
									        BigDecimal montanttotalTVA=BigDecimal.ZERO;
									        BigDecimal netapayer=BigDecimal.ZERO;
									       
														boolean trouve=false;
														
														 if(listDetailFacturePFTmp.size()!=0)
														 {
															 
															 for(int i=0;i<listDetailFacturePFTmp.size();i++)
															 {
																 trouve=false;
																 
																 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
																 {
																	if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePFTmp.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6)) && !listDetailFacturePFTmp.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6))) 
																	{
																		trouve=true;
																		
																		listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFTmp.get(i).getQuantite()));
																		listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFTmp.get(i).getMontantHT()));
																		listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

																		listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFTmp.get(i).getMontantTVA()));
																		listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFTmp.get(i).getMontantTTC()));
																		listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFTmp.get(i).getReduction()));			
																		listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
																		
																	}
																	
																	
																 }
																 if(trouve==false)
																 {
																	 listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
																 }
																  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTTC().setScale(6, RoundingMode.DOWN)); 
														          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
														          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
																 
															 }
															 
															 if(listDetailFacturePFImprimer.size()!=0)
															 {
																 
																    montanttotal=BigDecimal.ZERO;
															         montanttotalHT=BigDecimal.ZERO;
															         montanttotalTVA=BigDecimal.ZERO;
																 
																 for(int i=0;i<listDetailFacturePFImprimer.size();i++)
																 {
																	 trouve=false;
																	 
																	 for(int j=0;j<listDetailFacturePFImprimerTmp.size();j++)
																	 {
																		if(listDetailFacturePFImprimerTmp.get(j).getArticle().equals(listDetailFacturePFImprimer.get(i).getArticle()) && listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2)) && listDetailFacturePFImprimer.get(i).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2))) 
																		{
																			trouve=true;
																			
																			listDetailFacturePFImprimerTmp.get(j).setQuantite(listDetailFacturePFImprimerTmp.get(j).getQuantite().add(listDetailFacturePFImprimer.get(i).getQuantite()));
																			listDetailFacturePFImprimerTmp.get(j).setPrixUnitaire((listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().add(listDetailFacturePFImprimer.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
																			listDetailFacturePFImprimerTmp.get(j).setMontantHT(listDetailFacturePFImprimerTmp.get(j).getMontantHT().add(listDetailFacturePFImprimer.get(i).getMontantHT()));
																			listDetailFacturePFImprimerTmp.get(j).setMontantTVA(listDetailFacturePFImprimerTmp.get(j).getMontantTVA().add(listDetailFacturePFImprimer.get(i).getMontantTVA()));
																			listDetailFacturePFImprimerTmp.get(j).setMontantTTC(listDetailFacturePFImprimerTmp.get(j).getMontantTTC().add(listDetailFacturePFImprimer.get(i).getMontantTTC()));
																			listDetailFacturePFImprimerTmp.get(j).setReduction(listDetailFacturePFImprimerTmp.get(j).getReduction().add(listDetailFacturePFImprimer.get(i).getReduction()));			
																			listDetailFacturePFImprimerTmp.set(j, listDetailFacturePFImprimerTmp.get(j));
																			
																		}
																		
																		
																	 }
																	 if(trouve==false)
																	 {
																		 listDetailFacturePFImprimerTmp.add(listDetailFacturePFImprimer.get(i)); 
																	 }
																	  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTTC().setScale( 6, RoundingMode.DOWN)); 
															          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
															          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
																	 
																 }  
															 }
															 
														
															
																
																
																
																 txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
																
															   txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
														  			txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");
														  			
														  			
													  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
													  		  	
															 String dateFacture="";
													  		   
															 
															 dateFacture=dateFormat.format(facturepfTmp.getDateFacture());
															/*
															 * if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
															 * 
															 * }else { dateFacture =facturepfTmp.getNumFacture().substring(4,
															 * 6)+"/"+facturepfTmp.getNumFacture().substring(2,
															 * 4)+"/"+20+facturepfTmp.getNumFacture().substring(0, 2); }
															 */
													  		 
																Map parameters = new HashMap();
																parameters.put("dateFacture", dateFacture);
																String totalht=String.valueOf(new BigDecimal(txttotalmontantHT.getText()));
																String totaltva=String.valueOf(new BigDecimal(txttotalmontantTVA.getText()));
																String totalttc=String.valueOf(new BigDecimal(txttotalmontantTTC.getText()));
																String timbertmp=String.valueOf(new BigDecimal(txttimbre.getText()));
																String netapayerTmp=String.valueOf(new BigDecimal(txtnetapayer.getText()));
																
																
																parameters.put("TotalHT", totalht);
																parameters.put("TotalTVA", totaltva);
																parameters.put("TotalTTC",totalttc);
																parameters.put("client", facturepfTmp.getClientPF().getNom());
																parameters.put("timber",timbertmp);
																parameters.put("netapayer",netapayerTmp);
																
																
																
																if(facturepfTmp.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepfTmp.getType().equals(Constantes.TYPE_FACTURE))
																{
																	parameters.put("NumFacture", facturepfTmp.getNumFacture());
																	parameters.put("type", "Facture Avoir N°    :");
																	
																}else if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
																{
																	parameters.put("NumFacture", facturepfTmp.getNumBL());
																	parameters.put("type", "BL N°    :");
																}
																
																
																
																parameters.put("tva", Constantes.TVA);
																if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
																{
																	
																	parameters.put("ice", Constantes.ICE_ETP);
																}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
																{
																	
																	
																	parameters.put("ice", Constantes.ICE_AHLBRAHIM);
																}
																
																parameters.put("adresse", facturepfTmp.getClientPF().getAdresse());
																
																parameters.put("code",facturepfTmp.getClientPF().getCode());
																
																if(facturepfTmp.getClientPF().getIce()!=null)
																{
																	parameters.put("iceclient",facturepfTmp.getClientPF().getIce());
																}else
																{
																	parameters.put("iceclient","");
																}
																
														
																		
																		
																		
																	
																		
																		
																
															
																
																/*}else
																{
																parameters.put("modepaiement", "");
																}*/
																
																//double totalttc=Double.valueOf(txtnetapayer.getText());
																String x=txtnetapayer.getText().replace(".", ",");
																
																sommetowords= ConverterNumberToWords.converter(x);
															
																parameters.put("NumberToWords",sommetowords);
																
																
																try {
																	
																	if(listDetailFacturePFImprimerTmp.size()!=0)
																	{
																		JasperUtils.imprimerFactureAvoirClientPF(listDetailFacturePFImprimerTmp,parameters,apercu);
																		
																	}else
																	{
																		
																		JasperUtils.imprimerFactureAvoirClientPF(listDetailFacturePFImprimer,parameters,apercu);
																	}
																	
																} catch (PrintException | IOException e1) {
																	// TODO Auto-generated catch block
																	JOptionPane.showMessageDialog(null, e1.getMessage());
																}
															
																
														 }else
														 {
															 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
														 }
															
														
										
										
										
									}
									
				
								}
								
								
							}
							
							
						}
						
						

							
					//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
			  		  	
						///////////////////////////////////////////////
						
						/*
						
						detailFacturePfdao.ViderSession();
		String sommetowords="";

		listDetailFacturePF.clear();
		listDetailFacturePFImprimer.clear();
		listFacturePFTmp.clear();
		listDetailFacturePFImprimer =new ArrayList<DetailFacturePF>();
		 listDetailFacturePF=new ArrayList<DetailFacturePF>();
		 listFacturePFTmp=new ArrayList<FacturePF>();
		 FacturePF facturepfTmp=listFacturePF.get(table.getSelectedRow());
		 listFacturePFTmp=facturepfdao.findByNumFacture(facturepfTmp.getNumFacture(),facturepfTmp.getDepot());
			for(int k=0;k<listFacturePFTmp.size();k++)
			{
				listDetailFacturePF.addAll(listFacturePFTmp.get(k).getDetailFacturePF());
			}

						boolean trouve=false;
						 if(listDetailFacturePF.size()!=0)
						 {
							 
							 for(int i=0;i<listDetailFacturePF.size();i++)
							 {
								 trouve=false;
								 
								 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
								 {
									if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePF.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2)) && !listDetailFacturePF.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2))) 
									{
										trouve=true;
										
										listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePF.get(i).getQuantite()));
										listDetailFacturePFImprimer.get(j).setPrixUnitaire((listDetailFacturePFImprimer.get(j).getPrixUnitaire().add(listDetailFacturePF.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
										listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePF.get(i).getMontantHT()));
										listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePF.get(i).getMontantTVA()));
										listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePF.get(i).getMontantTTC()));
										listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
										listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
										
									}
									
									 
								 }
								 if(trouve==false)
								 {
									 listDetailFacturePFImprimer.add(listDetailFacturePF.get(i)); 
								 }
								 
								 
							 }
							 
							 
					  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					  		  	String dateFacture=dateFormat.format(dateChooserfacture.getDate());
					  		    FacturePF facturepf=listFacturePF.get(table.getSelectedRow());
								
								Map parameters = new HashMap();
								parameters.put("dateFacture", dateFacture);
								String[]totalht=String.format("%,f", new BigDecimal(txttotalmontantHT.getText())).split(",");
								String[]totaltva=String.format("%,f",new BigDecimal(txttotalmontantTVA.getText())).split(",");
								String[]totalttc=String.format("%,f",new BigDecimal(txttotalmontantTTC.getText())).split(",");
								String[]timber=String.format("%,f",new BigDecimal(txttimbre.getText())).split(",");
								String[]netapayer=String.format("%,f",new BigDecimal(txtnetapayer.getText())).split(",");
								
								parameters.put("TotalHT", totalht[0]+","+ totalht[1].substring(0, 2));
								parameters.put("TotalTVA", totaltva[0]+","+ totaltva[1].substring(0, 2));
								parameters.put("TotalTTC",totalttc[0]+","+ totalttc[1].substring(0, 2));
								parameters.put("client", facturepf.getClientPF().getNom());
								
								if(facturepf.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepf.getType().equals(Constantes.TYPE_FACTURE))
								{
									parameters.put("NumFacture", facturepf.getNumFacture());
									parameters.put("type", "Facture N°    :");
									
								}else if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
								{
									parameters.put("NumFacture", facturepf.getNumBl());
									parameters.put("type", "BL N°    :");
								}
								
								
								parameters.put("tva", Constantes.TVA);
								if(utilisateur.getId()==1)
								{
									parameters.put("ice", Constantes.ICE_ETP);
								}else if (utilisateur.getId()==2)
								{
									parameters.put("ice", Constantes.ICE_AHLBRAHIM);
								}
								
								parameters.put("adresse", facturepf.getClientPF().getAdresse());
								parameters.put("timber",timber[0]+","+ timber[1].substring(0, 2));
								parameters.put("netapayer",netapayer[0]+","+ netapayer[1].substring(0, 2));
								parameters.put("code",facturepf.getClientPF().getCode());
								
								if(facturepf.getClientPF().getIce()!=null)
								{
									parameters.put("iceclient",facturepf.getClientPF().getIce());
								}else
								{
									parameters.put("iceclient","");
								}
								
								
								if(checkmodepaiement.isSelected()==true)
								{
									if( facturepf.getModeReglement()!=null)
									{
										parameters.put("modepaiement", facturepf.getModeReglement());
									}else
									{
										parameters.put("modepaiement", "");
									}
							
								
								}else
								{
								parameters.put("modepaiement", "");
								}
								
								//double totalttc=Double.valueOf(txtnetapayer.getText());
								String x=txtnetapayer.getText().replace(".", ",");
								
								sommetowords= ConverterNumberToWords.converter(x);
							
								parameters.put("NumberToWords",sommetowords);
								
								
								
								JasperUtils.imprimerFacturePF(listDetailFacturePFImprimer,parameters);
							
								
						 }else
						 {
							 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
						 }
							
							
					//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
			  		  	
						
					*/
						
						
				
						
					
				}});
				button_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
				button_3.setBounds(466, 878, 112, 32);
				button_3.setIcon(imgImprimer);
				add(button_3);
				
				JLabel label_7 = new JLabel("Timbre 0,25%       :");
				label_7.setBounds(1109, 954, 112, 26);
				add(label_7);
				
				txttimbre = new JTextField();
				txttimbre.setEditable(false);
				txttimbre.setColumns(10);
				txttimbre.setBounds(1231, 954, 136, 26);
				add(txttimbre);
				
				 checkAppliquerTimbre = new JCheckBox("Appliquer Timbre");
				checkAppliquerTimbre.setFont(new Font("Tahoma", Font.BOLD, 11));
				checkAppliquerTimbre.setBounds(1340, 142, 229, 30);
				add(checkAppliquerTimbre);
				
				JLabel label_14 = new JLabel("Net A Payer         :");
				label_14.setBounds(1109, 979, 112, 26);
				add(label_14);
				
				txtnetapayer = new JTextField();
				txtnetapayer.setEditable(false);
				txtnetapayer.setColumns(10);
				txtnetapayer.setBounds(1231, 979, 136, 26);
				add(txtnetapayer);
				
				  btnExporterFactureExcel = new JButton("Exporter Facture Excel");
				  btnExporterFactureExcel.addActionListener(new ActionListener() {
				  	public void actionPerformed(ActionEvent e) {
				  		

						

						
						mapImprimer.clear();
			    		if(!remplirMapImprimer())	{
							JOptionPane.showMessageDialog(null, "Il faut remplir les Facture à imprimer SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
						} else {
							
							 
							
							
							for(int k=0;k<listFactureAvoirClientPF.size();k++)
							{
								FactureAvoirClientPF facturepfTmp=listFactureAvoirClientPF.get(k);
								
								
								
								
								
								
								
								if(mapImprimer.containsKey(facturepfTmp.getId()))
								{
									
									///////////////////////////////////////////////
									
									if(facturepfTmp.getType().equals(Constantes.TRANSFERE_BL_FACTURE) )
									{
										
										
										
										
										detailFactureAvoirClientPFdao.ViderSession();
										String sommetowords="";

										List<DetailFactureAvoirClientPF> listDetailFacturePFImprimer =new ArrayList<DetailFactureAvoirClientPF>();
										List<DetailFactureAvoirClientPF> listDetailFacturePFImprimerTmp =new ArrayList<DetailFactureAvoirClientPF>();
										   List<DetailFactureAvoirClientPF> listDetailFacturePFTmp =new ArrayList<DetailFactureAvoirClientPF>();
										 listFactureAvoirClientPFTmp.clear();
										 if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
										 {
											 listDetailFacturePFTmp=detailFactureAvoirClientPFdao.listeDetailFactureAvoirByFacture(facturepfTmp.getId());
											 
										 }else
										 {
											 listFactureAvoirClientPFTmp=factureAvoirClientpfdao.findByNumFactureByDepot(facturepfTmp.getNumFacture(), facturepfTmp.getDepot());
											 listDetailFacturePFTmp=detailFactureAvoirClientPFdao.listeDetailFactureAvoirClientPFByNumFacture(facturepfTmp.getNumFacture(), facturepfTmp.getMagasin());
											 
										 }
										    BigDecimal montanttotal=BigDecimal.ZERO;
									        BigDecimal montanttotalHT=BigDecimal.ZERO;
									        BigDecimal montanttotalTVA=BigDecimal.ZERO;
									        BigDecimal netapayer=BigDecimal.ZERO;
									       
														boolean trouve=false;
														
														 if(listDetailFacturePFTmp.size()!=0)
														 {
															 
															 for(int i=0;i<listDetailFacturePFTmp.size();i++)
															 {
																 trouve=false;
																 
																 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
																 {
																	if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePFTmp.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6)) && !listDetailFacturePFTmp.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(6))) 
																	{
																		trouve=true;
																		
																		listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePFTmp.get(i).getQuantite()));
																		listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePFTmp.get(i).getMontantHT()));
																		listDetailFacturePFImprimer.get(j).setPrixUnitaire(listDetailFacturePFImprimer.get(j).getMontantHT().divide(listDetailFacturePFImprimer.get(j).getQuantite(), 6, RoundingMode.FLOOR));

																		listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePFTmp.get(i).getMontantTVA()));
																		listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePFTmp.get(i).getMontantTTC()));
																		listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePFTmp.get(i).getReduction()));			
																		listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
																		
																	}
																	
																	
																 }
																 if(trouve==false)
																 {
																	 listDetailFacturePFImprimer.add(listDetailFacturePFTmp.get(i)); 
																 }
																  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTTC().setScale(6, RoundingMode.DOWN)); 
														          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
														          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFTmp.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
																 
															 }
															 
															 if(listDetailFacturePFImprimer.size()!=0)
															 {
																 
																    montanttotal=BigDecimal.ZERO;
															         montanttotalHT=BigDecimal.ZERO;
															         montanttotalTVA=BigDecimal.ZERO;
																 
																 for(int i=0;i<listDetailFacturePFImprimer.size();i++)
																 {
																	 trouve=false;
																	 
																	 for(int j=0;j<listDetailFacturePFImprimerTmp.size();j++)
																	 {
																		if(listDetailFacturePFImprimerTmp.get(j).getArticle().equals(listDetailFacturePFImprimer.get(i).getArticle()) && listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2)) && listDetailFacturePFImprimer.get(i).getPrixUnitaire().setScale(2, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(2))) 
																		{
																			trouve=true;
																			
																			listDetailFacturePFImprimerTmp.get(j).setQuantite(listDetailFacturePFImprimerTmp.get(j).getQuantite().add(listDetailFacturePFImprimer.get(i).getQuantite()));
																			listDetailFacturePFImprimerTmp.get(j).setPrixUnitaire((listDetailFacturePFImprimerTmp.get(j).getPrixUnitaire().add(listDetailFacturePFImprimer.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
																			listDetailFacturePFImprimerTmp.get(j).setMontantHT(listDetailFacturePFImprimerTmp.get(j).getMontantHT().add(listDetailFacturePFImprimer.get(i).getMontantHT()));
																			listDetailFacturePFImprimerTmp.get(j).setMontantTVA(listDetailFacturePFImprimerTmp.get(j).getMontantTVA().add(listDetailFacturePFImprimer.get(i).getMontantTVA()));
																			listDetailFacturePFImprimerTmp.get(j).setMontantTTC(listDetailFacturePFImprimerTmp.get(j).getMontantTTC().add(listDetailFacturePFImprimer.get(i).getMontantTTC()));
																			listDetailFacturePFImprimerTmp.get(j).setReduction(listDetailFacturePFImprimerTmp.get(j).getReduction().add(listDetailFacturePFImprimer.get(i).getReduction()));			
																			listDetailFacturePFImprimerTmp.set(j, listDetailFacturePFImprimerTmp.get(j));
																			
																		}
																		
																		
																	 }
																	 if(trouve==false)
																	 {
																		 listDetailFacturePFImprimerTmp.add(listDetailFacturePFImprimer.get(i)); 
																	 }
																	  montanttotal=  montanttotal.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTTC().setScale( 6, RoundingMode.DOWN)); 
															          montanttotalHT=montanttotalHT.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantHT().setScale(6, RoundingMode.DOWN));
															          montanttotalTVA=montanttotalTVA.setScale(6, RoundingMode.DOWN).add(listDetailFacturePFImprimer.get(i).getMontantTVA().setScale(6, RoundingMode.DOWN));
																	 
																 }  
															 }
															 
														
															
																
																
																
																 txttotalmontantTTC.setText(montanttotal.setScale(2, RoundingMode.HALF_UP)+"");
																
															   txttotalmontantHT.setText(montanttotalHT.setScale(2, RoundingMode.HALF_UP)+"");
														  			txttotalmontantTVA.setText(montanttotalTVA.setScale(2, RoundingMode.HALF_UP)+"");
														  			
														  			
													  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
													  		  	
															 String dateFacture="";
													  		   
															 
															 dateFacture=dateFormat.format(facturepfTmp.getDateFacture());
															/*
															 * if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON)) {
															 * 
															 * }else { dateFacture =facturepfTmp.getNumFacture().substring(4,
															 * 6)+"/"+facturepfTmp.getNumFacture().substring(2,
															 * 4)+"/"+20+facturepfTmp.getNumFacture().substring(0, 2); }
															 */
													  		 
																Map parameters = new HashMap();
																parameters.put("dateFacture", dateFacture);
																String totalht=String.valueOf(new BigDecimal(txttotalmontantHT.getText()));
																String totaltva=String.valueOf(new BigDecimal(txttotalmontantTVA.getText()));
																String totalttc=String.valueOf(new BigDecimal(txttotalmontantTTC.getText()));
																String timbertmp=String.valueOf(new BigDecimal(txttimbre.getText()));
																String netapayerTmp=String.valueOf(new BigDecimal(txtnetapayer.getText()));
																
																
																parameters.put("TotalHT", totalht);
																parameters.put("TotalTVA", totaltva);
																parameters.put("TotalTTC",totalttc);
																parameters.put("client", facturepfTmp.getClientPF().getNom());
																parameters.put("timber",timbertmp);
																parameters.put("netapayer",netapayerTmp);
																
																
																
																if(facturepfTmp.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepfTmp.getType().equals(Constantes.TYPE_FACTURE))
																{
																	parameters.put("NumFacture", facturepfTmp.getNumFacture());
																	parameters.put("type", "Facture Avoir N°    :");
																	
																}else if(facturepfTmp.getType().equals(Constantes.TYPE_BON_LIVRAISON))
																{
																	parameters.put("NumFacture", facturepfTmp.getNumBL());
																	parameters.put("type", "BL N°    :");
																}
																
																
																
																parameters.put("tva", Constantes.TVA);
																if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
																{
																	
																	parameters.put("ice", Constantes.ICE_ETP);
																}else if (utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
																{
																	
																	
																	parameters.put("ice", Constantes.ICE_AHLBRAHIM);
																}
																
																parameters.put("adresse", facturepfTmp.getClientPF().getAdresse());
																
																parameters.put("code",facturepfTmp.getClientPF().getCode());
																
																if(facturepfTmp.getClientPF().getIce()!=null)
																{
																	parameters.put("iceclient",facturepfTmp.getClientPF().getIce());
																}else
																{
																	parameters.put("iceclient","");
																}
																
														
																		
																		
																		
																	
																		
																		
																
															
																
																/*}else
																{
																parameters.put("modepaiement", "");
																}*/
																
																//double totalttc=Double.valueOf(txtnetapayer.getText());
																String x=txtnetapayer.getText().replace(".", ",");
																
																sommetowords= ConverterNumberToWords.converter(x);
															
																parameters.put("NumberToWords",sommetowords);
																
																
																try {
																	
																	if(listDetailFacturePFImprimerTmp.size()!=0)
																	{
																		JasperUtils.ExporterFactureAvoirClientToExcel(listDetailFacturePFImprimerTmp,parameters);
																		
																	}else
																	{
																		
																		JasperUtils.ExporterFactureAvoirClientToExcel(listDetailFacturePFImprimer,parameters);
																	}
																	
																} catch (PrintException | IOException e1) {
																	// TODO Auto-generated catch block
																	JOptionPane.showMessageDialog(null, e1.getMessage());
																}
															
																
														 }else
														 {
															 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
														 }
												
										
									}
									
				
								}
								
								
							}
							
							
						}
						
						

							
					//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
			  		  	
						///////////////////////////////////////////////
						
						/*
						
						detailFacturePfdao.ViderSession();
		String sommetowords="";

		listDetailFacturePF.clear();
		listDetailFacturePFImprimer.clear();
		listFacturePFTmp.clear();
		listDetailFacturePFImprimer =new ArrayList<DetailFacturePF>();
		 listDetailFacturePF=new ArrayList<DetailFacturePF>();
		 listFacturePFTmp=new ArrayList<FacturePF>();
		 FacturePF facturepfTmp=listFacturePF.get(table.getSelectedRow());
		 listFacturePFTmp=facturepfdao.findByNumFacture(facturepfTmp.getNumFacture(),facturepfTmp.getDepot());
			for(int k=0;k<listFacturePFTmp.size();k++)
			{
				listDetailFacturePF.addAll(listFacturePFTmp.get(k).getDetailFacturePF());
			}

						boolean trouve=false;
						 if(listDetailFacturePF.size()!=0)
						 {
							 
							 for(int i=0;i<listDetailFacturePF.size();i++)
							 {
								 trouve=false;
								 
								 for(int j=0;j<listDetailFacturePFImprimer.size();j++)
								 {
									if(listDetailFacturePFImprimer.get(j).getArticle().equals(listDetailFacturePF.get(i).getArticle()) && !listDetailFacturePFImprimer.get(j).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2)) && !listDetailFacturePF.get(i).getPrixUnitaire().equals(BigDecimal.ZERO.setScale(2))) 
									{
										trouve=true;
										
										listDetailFacturePFImprimer.get(j).setQuantite(listDetailFacturePFImprimer.get(j).getQuantite().add(listDetailFacturePF.get(i).getQuantite()));
										listDetailFacturePFImprimer.get(j).setPrixUnitaire((listDetailFacturePFImprimer.get(j).getPrixUnitaire().add(listDetailFacturePF.get(i).getPrixUnitaire())).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP));
										listDetailFacturePFImprimer.get(j).setMontantHT(listDetailFacturePFImprimer.get(j).getMontantHT().add(listDetailFacturePF.get(i).getMontantHT()));
										listDetailFacturePFImprimer.get(j).setMontantTVA(listDetailFacturePFImprimer.get(j).getMontantTVA().add(listDetailFacturePF.get(i).getMontantTVA()));
										listDetailFacturePFImprimer.get(j).setMontantTTC(listDetailFacturePFImprimer.get(j).getMontantTTC().add(listDetailFacturePF.get(i).getMontantTTC()));
										listDetailFacturePFImprimer.get(j).setReduction(listDetailFacturePFImprimer.get(j).getReduction().add(listDetailFacturePF.get(i).getReduction()));			
										listDetailFacturePFImprimer.set(j, listDetailFacturePFImprimer.get(j));
										
									}
									
									 
								 }
								 if(trouve==false)
								 {
									 listDetailFacturePFImprimer.add(listDetailFacturePF.get(i)); 
								 }
								 
								 
							 }
							 
							 
					  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					  		  	String dateFacture=dateFormat.format(dateChooserfacture.getDate());
					  		    FacturePF facturepf=listFacturePF.get(table.getSelectedRow());
								
								Map parameters = new HashMap();
								parameters.put("dateFacture", dateFacture);
								String[]totalht=String.format("%,f", new BigDecimal(txttotalmontantHT.getText())).split(",");
								String[]totaltva=String.format("%,f",new BigDecimal(txttotalmontantTVA.getText())).split(",");
								String[]totalttc=String.format("%,f",new BigDecimal(txttotalmontantTTC.getText())).split(",");
								String[]timber=String.format("%,f",new BigDecimal(txttimbre.getText())).split(",");
								String[]netapayer=String.format("%,f",new BigDecimal(txtnetapayer.getText())).split(",");
								
								parameters.put("TotalHT", totalht[0]+","+ totalht[1].substring(0, 2));
								parameters.put("TotalTVA", totaltva[0]+","+ totaltva[1].substring(0, 2));
								parameters.put("TotalTTC",totalttc[0]+","+ totalttc[1].substring(0, 2));
								parameters.put("client", facturepf.getClientPF().getNom());
								
								if(facturepf.getType().equals(Constantes.TRANSFERE_BL_FACTURE) ||facturepf.getType().equals(Constantes.TYPE_FACTURE))
								{
									parameters.put("NumFacture", facturepf.getNumFacture());
									parameters.put("type", "Facture N°    :");
									
								}else if(facturepf.getType().equals(Constantes.TYPE_BON_LIVRAISON))
								{
									parameters.put("NumFacture", facturepf.getNumBl());
									parameters.put("type", "BL N°    :");
								}
								
								
								parameters.put("tva", Constantes.TVA);
								if(utilisateur.getId()==1)
								{
									parameters.put("ice", Constantes.ICE_ETP);
								}else if (utilisateur.getId()==2)
								{
									parameters.put("ice", Constantes.ICE_AHLBRAHIM);
								}
								
								parameters.put("adresse", facturepf.getClientPF().getAdresse());
								parameters.put("timber",timber[0]+","+ timber[1].substring(0, 2));
								parameters.put("netapayer",netapayer[0]+","+ netapayer[1].substring(0, 2));
								parameters.put("code",facturepf.getClientPF().getCode());
								
								if(facturepf.getClientPF().getIce()!=null)
								{
									parameters.put("iceclient",facturepf.getClientPF().getIce());
								}else
								{
									parameters.put("iceclient","");
								}
								
								
								if(checkmodepaiement.isSelected()==true)
								{
									if( facturepf.getModeReglement()!=null)
									{
										parameters.put("modepaiement", facturepf.getModeReglement());
									}else
									{
										parameters.put("modepaiement", "");
									}
							
								
								}else
								{
								parameters.put("modepaiement", "");
								}
								
								//double totalttc=Double.valueOf(txtnetapayer.getText());
								String x=txtnetapayer.getText().replace(".", ",");
								
								sommetowords= ConverterNumberToWords.converter(x);
							
								parameters.put("NumberToWords",sommetowords);
								
								
								
								JasperUtils.imprimerFacturePF(listDetailFacturePFImprimer,parameters);
							
								
						 }else
						 {
							 JOptionPane.showMessageDialog(null, "Il n'existe auccun article pour cette facture ", "Erreur", JOptionPane.ERROR_MESSAGE); 
						 }
							
							
					//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
			  		  	
						
					*/
						
						
				
						
					
				
				  		
				  		
				  	}
				  });
				btnExporterFactureExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnExporterFactureExcel.setBounds(270, 878, 167, 32);
				add(btnExporterFactureExcel);
				btnExporterFactureExcel.setIcon(imgExcel);
				
				
				btnModifierPrixTransfert.setVisible(false);
				
				
				combotva.setVisible(false);
		
		
	}
	
	
void chargerListFacture()
{
	
	boolean exist=false;
	
	ClientPF clientpf=mapClientPF.get(comboparClientPF.getSelectedItem());
	Depot depot=mapparDepot.get(combopardepot.getSelectedItem());
	//initialiserFacture();
	initialiser();
	InitialiseTableau();
	listFactureAvoirClientPF.clear();
		listFactureAvoirClientPFTmp=factureAvoirClientpfdao.findByNumFcatureClientPFDateFactureDepotEtatRegle(txtparnumfacture.getText(),clientpf, pardateChooser.getDate(), depot);
		
		if(!comboparfamille.getSelectedItem().equals(""))
		{
			
			if(!comboparsousfamille.getSelectedItem().equals(""))
			{
				
				SousFamilleArticlePF sousfamille=mapsousfamille.get(comboparsousfamille.getSelectedItem());
				
				int i=0;
				while (i<listFactureAvoirClientPFTmp.size())
				{
					FactureAvoirClientPF factureavoirpf=listFactureAvoirClientPFTmp.get(i);
					exist=false;
					listDetailFactureAvoirClientPFTMP=detailFactureAvoirClientPFdao.listeDetailFactureAvoirByFacture(factureavoirpf.getId());
					for(int j=0;j<listDetailFactureAvoirClientPFTMP.size();j++)
					{
						DetailFactureAvoirClientPF detailfactureavoirpf=listDetailFactureAvoirClientPFTMP.get(j);
						
						if(detailfactureavoirpf.getSousFamille().getLiblle().equals(sousfamille.getLiblle()))
						{
							
							exist=true;
							
						}
						
					}
					if(exist==true)
					{
						listFactureAvoirClientPF.add(factureavoirpf);
					}
					
					i++;
					
				}
				
				
				
				if(listFactureAvoirClientPF.size()==0)
				{
					//initialiserFacture();
		    		initialiser();
		    		InitialiseTableau();
		    		InitialiseTableauFacture();
				}else
				{
					afficher_tableFactureAchat(listFactureAvoirClientPF);
				}
			}else
			{
				listFactureAvoirClientPF.addAll(listFactureAvoirClientPFTmp);
				if(listFactureAvoirClientPF.size()==0)
				{
					//initialiserFacture();
					InitialiseTableauFacture();
		    		initialiser();
		    		InitialiseTableau();
				}else
				{
					
					afficher_tableFactureAchat(listFactureAvoirClientPF);
				}
			}
			
			
		
			
		}else
		{
			listFactureAvoirClientPF.addAll(listFactureAvoirClientPFTmp);
			if(listFactureAvoirClientPF.size()==0)
			{
				//initialiserFacture();
				InitialiseTableauFacture();
	    		initialiser();
	    		InitialiseTableau();
			}else
			{
				
				afficher_tableFactureAchat(listFactureAvoirClientPF);
			}
			
			
		}
		
		
		
				
	
	
	
}
	
	
	void initialiserFacture()
	{
		txtnumfacture.setText("");
		dateChooserfacture.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.removeAllItems();
		combomagasin.setSelectedIndex(-1);
		comboparClientPF.setSelectedItem("");
		
	}

	void initialiser()
	{
		txtcodearticle.setText("");
		comboArticle.setSelectedIndex(-1);
	   txtPrix.setText("");
		txtquantitepacket.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	     combofamille.setSelectedItem("");
	     combosousfamille.removeAllItems();
	     radioRemiseMoins.setSelected(false);
			radioRemisePlus.setSelected(false);
	     
		
	}
	
	void initialiserInfofactureEt()
	{
		  txttotalmontantTTC.setText("");
			txttotalquantite.setText("");
			txttotalmontantHT.setText("");
			txttotalmontantTVA.setText("");	
			txtnetapayer.setText("");	
			txttimbre.setText("");
			comboClientPF.setSelectedIndex(-1);
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
						"Num BL","Num Facture", "Date Facture", "Etat", "Client PF", "Depot", "Magasin", "Montant TTC","Transfere BL en Facture"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,	false,false,false,false,false,false,false,true
				};
				Class[] columnTypes = new Class[] {
						String.class,String.class,Date.class,String.class,String.class,String.class,String.class,BigDecimal.class, Boolean.class
					};
				  public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
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
	
	
	
	void afficher_tableDetailFactureAchat(List<DetailFactureAvoirClientPF> listDetailFacture)
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
			boolean existe=false;
		DetailFactureAvoirClientPF detailfactureAvoirPF=listDetailFacture.get(i);
			Articles article=detailfactureAvoirPF.getArticle();
			
		/*		if(article.getCentreCout2()!=null)
				{
					if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
					{
					existe=true;
					}
				}
			
			if(existe==true)
			{
				Object []ligne={detailfactureAvoirPF.getSousFamille().getFamileArticlePF().getLiblle(),detailfactureAvoirPF.getSousFamille().getLiblle(),detailfactureAvoirPF.getArticle().getLiblle(),detailfactureAvoirPF.getPrixUnitaire(),detailfactureAvoirPF.getQuantite().divide(article.getConditionnement(),RoundingMode.HALF_UP),detailfactureAvoirPF.getReduction().divide(article.getConditionnement(),RoundingMode.HALF_UP), detailfactureAvoirPF.getMontantHT().divide(article.getConditionnement(),RoundingMode.HALF_UP),detailfactureAvoirPF.getMontantTVA().divide(article.getConditionnement(),RoundingMode.HALF_UP), detailfactureAvoirPF.getMontantTTC().divide(article.getConditionnement(),RoundingMode.HALF_UP)};

				modeleArticle.addRow(ligne);
			}else
			{*/
				Object []ligne={detailfactureAvoirPF.getSousFamille().getFamileArticlePF().getLiblle(),detailfactureAvoirPF.getSousFamille().getLiblle(),detailfactureAvoirPF.getArticle().getLiblle(),detailfactureAvoirPF.getPrixUnitaire(),detailfactureAvoirPF.getQuantite(),detailfactureAvoirPF.getReduction(), detailfactureAvoirPF.getMontantHT(),detailfactureAvoirPF.getMontantTVA(), detailfactureAvoirPF.getMontantTTC()};

				modeleArticle.addRow(ligne);
			/*}*/
		
			i++;
		}
}
	
	
	void afficher_tableFactureAchat(List<FactureAvoirClientPF> listFacture)
	{
		InitialiseTableauFacture();
		int i=0;
		 
		while(i<listFacture.size())
		{	
		FactureAvoirClientPF factureAvoirPF=listFacture.get(i);
		
		Date datefacture=factureAvoirPF.getDateFacture();
			
			Object []ligne={factureAvoirPF.getNumBL(),factureAvoirPF.getNumFacture(),datefacture,factureAvoirPF.getEtat(),factureAvoirPF.getClientPF().getNom(),factureAvoirPF.getDepot().getLibelle(),factureAvoirPF.getMagasin().getLibelle(),factureAvoirPF.getMontantTTC(),false};

			modelefacture.addRow(ligne);
			i++;
		}
}
	
	boolean remplirmaptransfereblfacture(){
		boolean trouve=false;
		int i=0;
				
		for(int j=0;j<table.getRowCount();j++){
			
			boolean regle=(boolean) table.getValueAt(j, 8);
			if(regle==true ){
				
				maptransfereblfacture.put(String.valueOf(table.getValueAt(j, 0).toString()), Boolean.valueOf(table.getValueAt(j, 8).toString()));
				i++;
				trouve=true;
			}
			
		}
		return trouve;
	}
	
	
	boolean remplirMapImprimer(){
		boolean trouve=false;
		int i=0;
				
		for(int j=0;j<table.getRowCount();j++){
			
			boolean imprimer=(boolean) table.getValueAt(j, 8);
			if(imprimer==true ){
				FactureAvoirClientPF factureAvoirClientPF=listFactureAvoirClientPF.get(j);
				
				mapImprimer.put(factureAvoirClientPF.getId(), factureAvoirClientPF);
				i++;
				trouve=true;
			}
			
		}
		return trouve;
	}
	}


