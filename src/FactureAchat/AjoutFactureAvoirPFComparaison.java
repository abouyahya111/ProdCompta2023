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
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureAvoirPFComparaisonDAOImpl;
import dao.daoImplManager.DetailFactureAvoirPFDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FactureAchatMPDAOImpl;
import dao.daoImplManager.FactureAvoirPFComparaisonDAOImpl;
import dao.daoImplManager.FactureAvoirPFDAOImpl;
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
import dao.daoManager.DetailFactureAvoirPFComparaisonDAO;
import dao.daoManager.DetailFactureAvoirPFDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FactureAvoirPFComparaisonDAO;
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
import dao.entity.CompteClient;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFactureAvoirPFComparaison;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FactureAchatMP;
import dao.entity.FactureAvoirPF;
import dao.entity.FactureAvoirPFComparaison;
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



























































import com.sun.java.swing.plaf.motif.MotifFileChooserUI.FilterComboBoxRenderer;
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
import javax.swing.JCheckBox;


public class AjoutFactureAvoirPFComparaison extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleChargefixe;

	private JXTable  tableArticle = new JXTable();
	private List<Fournisseur> listFournisseur =new ArrayList<Fournisseur>();
	private List<DetailFactureAvoirPFComparaison> listDetailFactureAvoirpfComparaison =new ArrayList<DetailFactureAvoirPFComparaison>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<Articles> listArticleStockPF =new ArrayList<Articles>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	
	

	
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	
	private Map< String, Fournisseur> mapFournisseur= new HashMap<>();
	private Map< String, Fournisseur> mapCodeFournisseur= new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	 private JComboBox comboArticle;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private ArticlesDAO articleDAO;
	private FactureAvoirPFComparaisonDAO factureAvoirpfComparaisondao;
	private FactureAvoirPFComparaison factureAvoirpfComparaison=new FactureAvoirPFComparaison();
private DetailFactureAvoirPFComparaisonDAO detailFactureAvoirpfComparaisondao;
private FamilleArticlesPFDAO familleArticleDAO;
private SousFamilleArticlesPFDAO sousFamilleArticleDAO;

	private JTextField txtcodearticle;
	ChargeProduction chargeproduction;
	private JTextField txtquantite;
	private JTextField txtnumfacture;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private   JComboBox combofamille = new JComboBox();
	private DepotDAO depotdao;
	private ParametreDAO parametredao;
	private TypeVenteDAO typeventedao;
	
	private FournisseurDAO fournisseurdao;
	private static SequenceurDAO sequenceurDAO=ProdLauncher.sequenceurDAO;
	private Map< String, ListTva> mapTVA= new HashMap<>();
	private Map< BigDecimal, String> mapTVAparTaux= new HashMap<>();
	 JComboBox combomagasin = new JComboBox();
	 private    JComboBox combosousfamille = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	 private JDateChooser dateChooserfacture;
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private JTextField txtPrix;
	private JTextField txttotalmontantTTC;
	private JTextField txttotalquantite;
	private  JButton btnModifier ;
	private  JButton btnSupprimer = new JButton();
	 private   JComboBox comboFournisseur = new JComboBox();
	private JTextField txttotalmontantHT;
	private JTextField txttotalmontantTVA;
	private JTextField txtreduction;
	private JTextField txtcodefournisseur;
private StockPFDAO stockPFDAO;


	 private JTextField txtquantitepacket;
	 JRadioButton radiosanstva = new JRadioButton("Sans TVA");
	 JRadioButton radioavectva = new JRadioButton("Avec TVA");
	 JComboBox combotva = new JComboBox();
	 private List<ListTva> listTva =new ArrayList<ListTva>();
	 private ListTvaDAO listtvaDAO ;
	 JRadioButton radioRemisePlus = new JRadioButton("+");
	 JRadioButton radioRemiseMoins = new JRadioButton("-");
	 //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    ButtonGroup groupremise = new ButtonGroup();
	    JCheckBox checkAvoirMarjane = new JCheckBox("Avoir Marjane");
	 
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutFactureAvoirPFComparaison() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 795);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
           
            utilisateur=AuthentificationView.utilisateur;
        	
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	factureAvoirpfComparaisondao=new FactureAvoirPFComparaisonDAOImpl();
         
         	parametredao=new ParametreDAOImpl();
         	detailFactureAvoirpfComparaisondao=new DetailFactureAvoirPFComparaisonDAOImpl();
         	stockPFDAO=new StockPFDAOImpl();
         	articleDAO=new ArticlesDAOImpl();
         	fournisseurdao=new FournisseurDAOImpl();
         	familleArticleDAO=new FamilleArticlesPFDAOImpl();
         	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
         	listFamilleArticle=familleArticleDAO.findAll();
        
        	  listtvaDAO=new ListTvaDAOImpl();
        	  
         	
          } catch (Exception exp){exp.printStackTrace();}
        tableArticle.setSortable(false);
        tableArticle.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
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
       		
       		DetailFactureAvoirPFComparaison detailfactureavoir=listDetailFactureAvoirpfComparaison.get(tableArticle.getSelectedRow());
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
       			"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Reduction","Montant HT", "Montant TVA", "Montant TTC"
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
				  		     	scrollPane.setBounds(10, 392, 1117, 258);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 357, 1117, 24);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 221, 1117, 91);
				  		     	add(layeredPane);
		
		  JLabel lblCodeArticle = new JLabel("Code Article :");
		  lblCodeArticle.setBounds(550, 11, 82, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel lbllibelle = new JLabel("Libelle :");
		  lbllibelle.setBounds(745, 11, 57, 26);
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
		      txtcodearticle.setBounds(618, 11, 112, 26);
		      layeredPane.add(txtcodearticle);
		    
		   
		       comboArticle = new JComboBox();
		       comboArticle.addInputMethodListener(new InputMethodListener() {
		       	public void caretPositionChanged(InputMethodEvent arg0) {
		       	}
		       	public void inputMethodTextChanged(InputMethodEvent arg0) {
		       		
		       		
		       
		       		
		       		
		       		
		       	}
		       });
		       comboArticle.setEditable(true);
		       comboArticle.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {

		     	 		
  		     	 	if(comboArticle.getSelectedIndex()!=-1)
  			 		{
  			 			if(!comboArticle.getSelectedItem().equals(""))
  				 		{
  				 			Articles article=mapArticle.get(comboArticle.getSelectedItem());
  				 			txtcodearticle.setText(article.getCodeArticle());
  				 		
  				 			boolean trouve=false;
			 					
		 						if(article.getCentreCout2()!=null)
		 						{
		 							if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
		 							{
		 							trouve=true;
		 							}
		 						}
		 					
		 					if(trouve==true)
		 					{
		 						txtquantitepacket.setText("");
		 						txtquantitepacket.setEnabled(true);
		 					}else
		 					{
		 						txtquantitepacket.setText("");
		 						txtquantitepacket.setEnabled(false);
		 					}
		 					
  				 		
  				 		  				 			
  				 		}else
  				 		{
  				 			txtcodearticle.setText("");
  				 			
  				 		}
  				 	
  			 		}
  			 		
  			 		
  		     	 		
  		     	 	
		       	}
		       });
		      comboArticle.setBounds(796, 11, 300, 27);
		      layeredPane.add(comboArticle);
		      
		   AutoCompleteDecorator.decorate(comboArticle);
		     	
		      
		      JLabel lblQuantit = new JLabel("Quantite :");
		      lblQuantit.setBounds(10, 59, 72, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      util.Utils.copycoller(txtquantite);
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			
		     			
		     		
		      		
		      	}
		      });
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(68, 59, 93, 26);
		      layeredPane.add(txtquantite);
		      
		      JLabel lblPrix = new JLabel("Prix  :");
		      lblPrix.setBounds(353, 59, 34, 26);
		      layeredPane.add(lblPrix);
		      
		      txtPrix = new JTextField();
		      txtPrix.setColumns(10);
		      txtPrix.setBounds(385, 59, 93, 26);
		      layeredPane.add(txtPrix);
		      
		      JLabel lblReduction = new JLabel("Remise :");
		      lblReduction.setBounds(488, 59, 57, 26);
		      layeredPane.add(lblReduction);
		      
		      txtreduction = new JTextField();
		      txtreduction.setColumns(10);
		      txtreduction.setBounds(628, 59, 112, 26);
		      layeredPane.add(txtreduction);
		      
		      JLabel label_2 = new JLabel("%");
		      label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		      label_2.setBounds(746, 59, 26, 26);
		      layeredPane.add(label_2);
		      
		      JLabel label_5 = new JLabel("Famille Article :");
		      label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		      label_5.setBounds(10, 12, 87, 24);
		      layeredPane.add(label_5);
		      
		       combofamille = new JComboBox();
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
	    		        			// comboArticle.removeAllItems();
	    	        				 txtcodearticle.setText("");
	    		        		}
	    		        			
	        				 
	        			 }else
	        			 {
	        				combosousfamille.removeAllItems(); 
	        				// comboArticle.removeAllItems();
	        				 txtcodearticle.setText("");
	        			 }
	        			 
	        			
	        	 
	        			
	        			 
	     	 		 }
	     	 			 
	        		
	        		
	        	}
		       });
		      combofamille.setSelectedIndex(-1);
		      combofamille.setBounds(92, 13, 167, 24);
		      layeredPane.add(combofamille);
		      
		      int p=0;
		      combofamille.addItem("");
		      while(p<listFamilleArticle.size())
		      {
		    	  
		    	  FamilleArticlePF famillearticle=listFamilleArticle.get(p);
		    	  combofamille.addItem(famillearticle.getLiblle());
		    	  mapfamille.put(famillearticle.getLiblle(), famillearticle);
		    	  p++;
		      }
		      
		      
		      JLabel label_6 = new JLabel("Sous Famille :");
		      label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		      label_6.setBounds(269, 12, 87, 24);
		      layeredPane.add(label_6);
		      
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
			        				 listStockPF=stockPFDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
			        				 for(int i=0;i<listStockPF.size();i++)
			        				 {
			        					 
			        					Articles article= listStockPF.get(i).getArticles();
			        					
			        				

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
		      combosousfamille.setSelectedIndex(-1);
		      combosousfamille.setBounds(351, 13, 160, 24);
		      layeredPane.add(combosousfamille);
		 
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal solde=BigDecimal.ZERO;
				SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				 String date = dcn.format(dateChooserfacture.getDate());
				 
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
				}else if(combomagasin.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
					
				}else if(comboFournisseur.getSelectedIndex()==-1)
				{

					JOptionPane.showMessageDialog(null, "Veuillez choisir le Fournisseur SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
					
				
					
				}else if(listDetailFactureAvoirpfComparaison.size()==0)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer les articles à facturé SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else
				{
					
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				Magasin magasin =mapMagasin.get(combomagasin.getSelectedItem());
				
				// chercher le num de facture doit etre unique 
				/*FactureAchat factureAchatTmp=factureAchatdao.findByNumFacture(txtnumfacture.getText());
				FactureAchatMP factureAchatMP=factureAchatMPdao.findByNumFacture(txtnumfacture.getText());*/
				FactureAvoirPFComparaison factureAvoirPF=factureAvoirpfComparaisondao.findByNumFactureByDepot(txtnumfacture.getText(),depot);
			
		if(factureAvoirPF==null)
		{
			
		Fournisseur fournisseur=mapFournisseur.get(comboFournisseur.getSelectedItem());
		String codeTransfert=Utils.genererCodeTransfer(depot.getCode(),ETAT_TRANSFER_STOCK_AVOIR);
		
		factureAvoirpfComparaison.setNumFacture(txtnumfacture.getText());
		factureAvoirpfComparaison.setFournisseur(fournisseur);
		factureAvoirpfComparaison.setDepot(depot);
		factureAvoirpfComparaison.setMagasin(magasin);
		factureAvoirpfComparaison.setDateFacture(dateChooserfacture.getDate());
		factureAvoirpfComparaison.setEtat(Constantes.ETAT_NON_REGLE);
		factureAvoirpfComparaison.setType(Constantes.TYPE_BON_LIVRAISON);
		factureAvoirpfComparaison.setMontantHT((new BigDecimal(txttotalmontantHT.getText())).setScale(6, RoundingMode.HALF_UP));
		factureAvoirpfComparaison.setMontantTVA((new BigDecimal(txttotalmontantTVA.getText())).setScale(6, RoundingMode.HALF_UP));
		factureAvoirpfComparaison.setMontantTTC((new BigDecimal(txttotalmontantTTC.getText())).setScale(6, RoundingMode.HALF_UP));	
		//factureAvoirpf.setDetailFactureAvoirPF(listDetailFactureAvoirpf);
		factureAvoirpfComparaison.setCodeTransfer(codeTransfert);
		factureAvoirpfComparaison.setCreerPar(utilisateur);
		factureAvoirpfComparaison.setDateCreer(new Date());
		
		if(checkAvoirMarjane.isSelected()==true)
		{
			
			factureAvoirpfComparaison.setStatut(Constantes.STATUT_FACTURE_AVOIR_COMPARAISON_MARJAN);
		}else
		{
			
			factureAvoirpfComparaison.setStatut(Constantes.STATUT_FACTURE_AVOIR_COMPARAISON_NORMAL);

			
		}
		
		
	    factureAvoirpfComparaisondao.add(factureAvoirpfComparaison);
	    for(int i=0;i<listDetailFactureAvoirpfComparaison.size();i++)
		 {
			 detailFactureAvoirpfComparaisondao.add(listDetailFactureAvoirpfComparaison.get(i));
		 }
	    
	 
	    ////////////////////////////////// ajouter detail compte client par client facture ////////////////////////   
	    /*
	    DetailCompteClient detailcompteclient=new DetailCompteClient();
	    detailcompteclient.setCompteClient(clientPF.getCompteClient());
	    detailcompteclient.setUtilisateurCreation(utilisateur);
	    detailcompteclient.setDateCreation(new Date());
	    detailcompteclient.setDateOperation(dateChooserfacture.getDate());
	    detailcompteclient.setMontantDebit(new BigDecimal(txttotalmontantTTC.getText()));
	    detailcompteclient.setMontantCredit(BigDecimal.ZERO);
	    detailcompteclient.setDesignation("Montant sur Facture N "+txtnumfacture.getText());
	    detailcompteclient.setFacturepf(facturePF);
	    detailcompteclient.setFournisseur(fournisseur);
	    detailCompteClientdao.add(detailcompteclient);
	    solde=clientPF.getCompteClient().getSolde().add(new BigDecimal(txttotalmontantTTC.getText()));
	    clientPF.getCompteClient().setSolde(solde);
	    compteclientdao.edit(clientPF.getCompteClient());
	   */
	    
	    int i=0;
	    /*
	    while(i<listStockPF.size())
	    {
	    	StockPF stockPF=stockpfDAO.findById(listStockPF.get(i).getId());
	    	if(stockPF!=null)
	    	{
	    		stockPF.setStock(listStockPF.get(i).getStock());
		    	stockpfDAO.edit(stockPF);
	    	}else
	    	{
	    		stockpfDAO.add(listStockPF.get(i));	
	    	}
	    	
	    	i++;
	    }
	    */
	   /* Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE);
	    if(sequenceur!=null)
	    {
	    	int valeur=sequenceur.getValeur()+1;
		    sequenceur.setValeur(valeur);
		    sequenceurDAO.edit(sequenceur);
	    }else
	    {
	    	Sequenceur sequenseur=new Sequenceur();
			sequenseur.setLibelle(Constantes.CODE_FACTURE_VENTE);
			sequenseur.setCode(date);
			sequenseur.setValeur(1);
			sequenceurDAO.add(sequenseur);
	    }*/
	    
	    // ajouter Transfer Stock PF (Mouvement Stock PF )
	    
			   
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
		
	
	    
	    JOptionPane.showMessageDialog(null, "Facture Ajouté avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
	    initialiserFacture();
	    initialiser();
	  
	    factureAvoirpfComparaison=new FactureAvoirPFComparaison();
	  
	    listDetailFactureAvoirpfComparaison.clear();
		InitialiseTableau();
			
		}else
		{
			
			 JOptionPane.showMessageDialog(null, "N° de Facture existe déja ","Erreur",JOptionPane.ERROR_MESSAGE);
			 return;
			
		}
					
				}
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(465, 667, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 180, 1117, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1117, 97);
		add(layeredPane_1);
		
		JLabel label = new JLabel("Code  :");
		label.setBounds(10, 13, 89, 24);
		layeredPane_1.add(label);
		
		txtnumfacture = new JTextField();
		txtnumfacture.setColumns(10);
		txtnumfacture.setBounds(109, 12, 183, 26);
		layeredPane_1.add(txtnumfacture);
		
		JLabel label_1 = new JLabel("Date  :");
		label_1.setBounds(312, 13, 62, 24);
		layeredPane_1.add(label_1);
		
		 dateChooserfacture = new JDateChooser();
		dateChooserfacture.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {/*
				if(dateChooserfacture.getDate()!=null)
				{
				SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				 String date = dcn.format(dateChooserfacture.getDate());
				
				 
				txtnumfacture.setText(Utils.genererCodeFactureVente(date));
				}
			*/}
		});
		dateChooserfacture.setLocale(Locale.FRANCE);
		dateChooserfacture.setDateFormatString("dd/MM/yyyy");
		dateChooserfacture.setBounds(427, 8, 181, 26);
		layeredPane_1.add(dateChooserfacture);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(637, 13, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(726, 13, 183, 24);
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
		  label_4.setBounds(10, 48, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  	}
		  });
		  combomagasin.setBounds(109, 49, 183, 24);
		  layeredPane_1.add(combomagasin);
		  combomagasin.setSelectedIndex(-1);
		  
		  JLabel lblFournisseur = new JLabel("Fournisseur :");
		  lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblFournisseur.setBounds(637, 49, 79, 24);
		  layeredPane_1.add(lblFournisseur);
		  
		   comboFournisseur = new JComboBox();
		   Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
		   
		   listFournisseur=fournisseurdao.findFournisseurByDepot(depot);
		   comboFournisseur.addItem("");
		     int i=0;
		     while(i<listFournisseur.size())
		     {
		    	 Fournisseur fournisseur=listFournisseur.get(i);
		    	 comboFournisseur.addItem(fournisseur.getNom());
		    	 mapCodeFournisseur.put(fournisseur.getCode(), fournisseur);
		    	 mapFournisseur.put(fournisseur.getNom(), fournisseur);
		    	 
		    	 
		    	 i++;
		     }
		   comboFournisseur.addItemListener(new ItemListener() {
		   	public void itemStateChanged(ItemEvent e) {

		  		

 	 			
   	 		 if(e.getStateChange() == ItemEvent.SELECTED)
   	 		 {
   	 			int i=0;
   	 		
   	 				if(!comboFournisseur.getSelectedItem().equals("") && comboFournisseur.getSelectedIndex()!=-1 )
    			{
    				Fournisseur fournisseur=mapFournisseur.get(comboFournisseur.getSelectedItem());
    				if(fournisseur!=null)
    				{
    					txtcodefournisseur.setText(fournisseur.getCode());
		     				
		     				
		     				
		     		}else
		     			{
		     				txtcodefournisseur.setText("");
		     				
		     			}
		     				
    					
    				}else
    				{
    					txtcodefournisseur.setText("");
    				}
    				
    				
    				
   	 		 }
   	 	
	
		   		
		   		
		   		
		   	}
		   });
		 
		  comboFournisseur.setSelectedIndex(-1);
		  comboFournisseur.setBounds(726, 49, 183, 24);
		  layeredPane_1.add(comboFournisseur);
		  AutoCompleteDecorator.decorate(comboFournisseur);
		   
		  
		  
		  txtcodefournisseur = new JTextField();
		  txtcodefournisseur.addKeyListener(new KeyAdapter() {
			  
			  
			  
			  
			  
		  	@Override
		  	public void keyPressed(KeyEvent arg0) {
		  		if(!txtcodefournisseur.getText().equals(""))
		  		{
		  			Fournisseur fournisseur=mapCodeFournisseur.get(txtcodefournisseur.getText());
		  			if(fournisseur!=null)
		  			{
		  				comboFournisseur.setSelectedItem(fournisseur.getNom());
		  				
		  			}else
		  			{
		  				JOptionPane.showMessageDialog(null, "Fournisseur introuvable !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		  				return;
		  			}
		  			
		  			
		  		}
		  		
		  		
		  	}
		  });
		  txtcodefournisseur.setColumns(10);
		  txtcodefournisseur.setBounds(427, 45, 181, 26);
		  layeredPane_1.add(txtcodefournisseur);
		  
		  JLabel lblCodeFournisseur = new JLabel("Code Fournisseur :");
		  lblCodeFournisseur.setBounds(312, 48, 105, 26);
		  layeredPane_1.add(lblCodeFournisseur);
		
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture d'Avoir");
		titledSeparator_2.setBounds(10, 11, 1117, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(479, 324, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					

					boolean trouve=false;
					//BigDecimal oldprix=BigDecimal.ZERO;
					BigDecimal odlQuantite=BigDecimal.ZERO;
					BigDecimal Montant=BigDecimal.ZERO;
					BigDecimal QuantiteUnit=BigDecimal.ZERO;
					BigDecimal QuantitePaquet=BigDecimal.ZERO;
					BigDecimal QuantiteTotalStock=BigDecimal.ZERO;
					BigDecimal QuantiteTotal=BigDecimal.ZERO;
					BigDecimal TVA=BigDecimal.ZERO;
					boolean articleboisson=false;
					
					 if(dateChooserfacture.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(comboArticle.getSelectedItem().equals(""))
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
				         
				         for(int i=0;i<listDetailFactureAvoirpfComparaison.size();i++)
				         {
				        	 DetailFactureAvoirPFComparaison detailFactureAvoirpf =listDetailFactureAvoirpfComparaison.get(i);
				        	 if(detailFactureAvoirpf.getArticle().getId()==article.getId() && detailFactureAvoirpf.getSousFamille().getId()==sousfamille.getId())
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
					
				        	 
				        	
				        	 
				        	
					        	 	 DetailFactureAvoirPFComparaison detailFacture=new DetailFactureAvoirPFComparaison();
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
							        	   Montant=QuantiteTotal.multiply(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 6, RoundingMode.DOWN));
								          }else
								          {
								        	  detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()).setScale(6));
								        	  Montant=QuantiteTotal.multiply(new BigDecimal(txtPrix.getText()).setScale(6));  
								          }
							           if(article.getCodeArticle().equals("1500") || article.getCodeArticle().equals("1503") || article.getCodeArticle().equals("1504") )
								          {
							        	   
							        	     
									          detailFacture.setTva(BigDecimal.ZERO);
									          detailFacture.setSousFamille(sousfamille);
									          if(!txtreduction.getText().equals(""))
									          {
									        	  
									        	  
									        	  if(radioRemisePlus.isSelected()==true)
									        	  {
									        		  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP).add(Montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											          detailFacture.setMontantTVA(((Montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP).add(Montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
									        		  
										        	  detailFacture.setMontantTTC((((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).add((((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  
  
									        	  }else if(radioRemiseMoins.isSelected()==true)
									        	  {
									        		  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP).subtract(Montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											          detailFacture.setMontantTVA(((Montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP).subtract(Montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
									        		  
										        	  detailFacture.setMontantTTC((((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).subtract((((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  
 
									        		  
									        	  }else
									        	  {
									        		  JOptionPane.showMessageDialog(null, "Veuillez Selectionné le type de Remise + ou - SVP","Attention",JOptionPane.ERROR_MESSAGE);
									        			return;	
									        	  }
									        		  
									        	  
									          
									          }else
									          {
									        	  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP));
										          detailFacture.setMontantTVA(((Montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
									        	  detailFacture.setMontantTTC(((Montant).add((Montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP));
									          }
									         
							        	   
								          }else
								          {
								        	   
										          detailFacture.setTva(TVA.multiply(new BigDecimal(100)));
										          detailFacture.setSousFamille(sousfamille);
										          if(!txtreduction.getText().equals(""))
										          {
										        	  if(radioRemisePlus.isSelected()==true)
										        	  {
										        		  
										        		  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP).add(Montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
												          detailFacture.setMontantTVA(((Montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).add(((Montant).multiply(TVA)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											        	  detailFacture.setMontantTTC((((Montant).add((Montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).add((((Montant).add((Montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

										        		  
										        	  }else if(radioRemiseMoins.isSelected()==true)
										        	  {
										        		  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP).subtract(Montant.multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
												          detailFacture.setMontantTVA(((Montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).subtract(((Montant).multiply(TVA)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											        	  detailFacture.setMontantTTC((((Montant).add((Montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((Montant).add((Montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

										        		  
										        	  }else
										        	  {
										        		  JOptionPane.showMessageDialog(null, "Veuillez Selectionné le type de Remise + ou - SVP","Attention",JOptionPane.ERROR_MESSAGE);
										        			return;	
										        	  }
										        	  
										          
										          
										          }else
										          {
										        	  detailFacture.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP));
											          detailFacture.setMontantTVA(((Montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP));
										        	  detailFacture.setMontantTTC(((Montant).add((Montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP));
										          }
										          
								        	  
								          }
							         
							      
							          
							           detailFacture.setFactureAvoirPf(factureAvoirpfComparaison);
							       //    detailFacture.setDateCreation(new Date());
							           
							           detailFacture.setUtilisateur(utilisateur);
							          
							           listDetailFactureAvoirpfComparaison.add(detailFacture);
							           
							           /*
							           if(stockpf!=null)
							           {
							        	
							        	
							        	   
							        	   if(stockpf.getStock().compareTo(QuantiteTotal)>=0)
							        	   {
							        		   
							        		   odlQuantite=stockpf.getStock().subtract(QuantiteTotal);
							        		   if(odlQuantite.compareTo(BigDecimal.ZERO)<=0)
									        	 {
							        			   odlQuantite=BigDecimal.ZERO; 
									        		// oldprix=stockpf.getPrixUnitaire();
									        		
									        	 }else
									        	 {
									        		 //  oldprix=((stockpf.getPrixUnitaire().multiply(odlQuantite.add(quantite))).subtract(quantite.multiply(new BigDecimal(txtPrix.getText())))).divide(odlQuantite, RoundingMode.HALF_UP);
 
									        	 }
							        		 
							        		   stockpf.setStock(odlQuantite);
									         //  stockpf.setPrixUnitaire(oldprix);
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
							        
							           
							          afficher_tableDetailFactureAchat(listDetailFactureAvoirpfComparaison);
							          int i=0;
								        BigDecimal montanttotal=BigDecimal.ZERO;
								        BigDecimal montanttotalHT=BigDecimal.ZERO;
								        BigDecimal montanttotalTVA=BigDecimal.ZERO;
								        BigDecimal sommequantite=BigDecimal.ZERO;
								        while(i<listDetailFactureAvoirpfComparaison.size())
								        {
								        	articleboisson=false;
								        	
												if(listDetailFactureAvoirpfComparaison.get(i).getArticle().getCentreCout2()!=null)
												{
													if(listDetailFactureAvoirpfComparaison.get(i).getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
													{
														articleboisson=true;
													}
													
												}
											
							        	 
								        	if(articleboisson==true)
								        	{
								        		  DetailFactureAvoirPFComparaison detailFactureAvoirpf=listDetailFactureAvoirpfComparaison.get(i);
										         /* montanttotal=  montanttotal.add(detailFactureAvoirpf.getMontantTTC().divide(detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP));
										          sommequantite= sommequantite.add(detailFactureAvoirpf.getQuantite().divide(detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP));
										          montanttotalHT=montanttotalHT.add(detailFactureAvoirpf.getMontantHT().divide(detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP));
										          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirpf.getMontantTVA().divide(detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP));*/
								        		  montanttotal=  montanttotal.add(detailFactureAvoirpf.getMontantTTC());
										          sommequantite= sommequantite.add(detailFactureAvoirpf.getQuantite());
										          montanttotalHT=montanttotalHT.add(detailFactureAvoirpf.getMontantHT());
										          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirpf.getMontantTVA());
										          
										          
								        	}else
								        	{
								        		 DetailFactureAvoirPFComparaison detailFactureAvoirpf=listDetailFactureAvoirpfComparaison.get(i);
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
							  			
							  			
							  		
										
							
							  			
							  			
								        initialiser(); 
					        	
					       }else
					       {
					    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					    	   return;
					       }
					       
				         }
				        
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité , le Prix et la Remise doit etre en chiffre SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
			}
				
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(610, 323, 106, 23);
		  add(btnInitialiser);
		  btnInitialiser.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  	
		  	    initialiser();
		  		
		  	}
		  });
		  btnInitialiser.setIcon(imgInit);
		  btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JButton button = new JButton("Initialiser");
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		initialiserFacture();
		  	
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(441, 147, 106, 23);
		  add(button);
		  
		   btnModifier = new JButton();
		   btnModifier.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		try {
		   			

			   		BigDecimal nouveauprix=BigDecimal.ZERO;
					BigDecimal coutAchat=BigDecimal.ZERO;
					BigDecimal coutStock=BigDecimal.ZERO;
					BigDecimal QuantiteTotal=BigDecimal.ZERO;
			   		BigDecimal stocktmp=BigDecimal.ZERO;
			   		BigDecimal montant=BigDecimal.ZERO;
			   		BigDecimal marge=BigDecimal.ZERO;
			   		//BigDecimal oldprix=BigDecimal.ZERO;
					BigDecimal odlQuantite=BigDecimal.ZERO;
					BigDecimal QuantiteUnit=BigDecimal.ZERO;
					BigDecimal QuantitePaquet=BigDecimal.ZERO;
					BigDecimal QuantiteTotalStock=BigDecimal.ZERO;
					
					BigDecimal TVA=BigDecimal.ZERO;
					boolean articleboisson=false;
			   		if(tableArticle.getSelectedRow()!=-1)
			   		{
			   			boolean trouve=false;
			   		 if(dateChooserfacture.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(comboArticle.getSelectedItem().equals(""))
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
					        	 BigDecimal quantite = QuantitePaquet.add(QuantiteUnit).setScale(3);
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
					      
					         for(int i=0;i<listDetailFactureAvoirpfComparaison.size();i++)
					         {
					        	 if(i!=tableArticle.getSelectedRow())
					        	 {
					        		 
					        		 DetailFactureAvoirPFComparaison detailFactureAvoirpf =listDetailFactureAvoirpfComparaison.get(i);
						        	 if(detailFactureAvoirpf.getArticle().getId()== article.getId() && detailFactureAvoirpf.getSousFamille().getId()==sousfamille.getId())
						        	 {
						        		 trouve=true;
						        	 }
						        	  
					        		 
					        	 }
					        	 
					        	 
					         }
					         
					         if(trouve==false)
					         {
					        	 boolean existe=false;
					        	 
					        	// StockPF stockpftmp =listStockPF.get(tableArticle.getSelectedRow());
					        	 
					        	 
					        	/* coutAchat=listDetailFactureAvoirpf.get(tableArticle.getSelectedRow()).getQuantite().multiply(listDetailFactureAvoirpf.get(tableArticle.getSelectedRow()).getPrixUnitaire());
					        	  coutStock=stockpftmp.getStock().multiply(stockpftmp.getPrixUnitaire());*/
					        	//  QuantiteTotalStock=listDetailFactureAvoirpf.get(tableArticle.getSelectedRow()).getQuantite().add(stockpftmp.getStock());
					        	   
					        	//  nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotal, 6, RoundingMode.HALF_UP);
					        	//  stockpftmp.setStock(QuantiteTotalStock);
					        	 // stockpftmp.setPrixUnitaire(nouveauprix);
					        	 
					        	 
					        	 
					        	// StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(), magasin.getId(),sousfamille.getId());
					        	 
					            		if(article.getCentreCout2()!=null)
					            		{
					            			if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
					            			{
					            				existe=true;
					            			}
					            			
					            		}
					            		
					            	
						        		
						        		 
							        	 DetailFactureAvoirPFComparaison detailFacture= listDetailFactureAvoirpfComparaison.get(tableArticle.getSelectedRow());
							        	 
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
								           if(article.getCodeArticle().equals("1500") || article.getCodeArticle().equals("1503") || article.getCodeArticle().equals("1504") ) // articles sans TVA
									          {
								        	   
								        	   
										          detailFacture.setTva(BigDecimal.ZERO);
										          detailFacture.setSousFamille(sousfamille);
										          if(!txtreduction.getText().equals(""))
										          {
										        	  if(radioRemisePlus.isSelected()==true)
										        	  {
										        		  detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP).add( montant.setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
												          detailFacture.setMontantTVA(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP).add( montant.setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											        	  detailFacture.setMontantTTC((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).add((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

										        	  }else if(radioRemiseMoins.isSelected()==true)
										        	  {
										        		  
										        		  detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP).subtract( montant.setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
												          detailFacture.setMontantTVA(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP).subtract( montant.setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											        	  detailFacture.setMontantTTC((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).subtract ((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  
  
										        		  
										        	  }else
										        	  {

										        		  JOptionPane.showMessageDialog(null, "Veuillez Selectionné le type de Remise + ou - SVP","Attention",JOptionPane.ERROR_MESSAGE);
										        			return;	
										        	   
										        		  
										        	  }
										        	  
										         
										          
										          
										          }else
										          {
										        	  detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP));
											          detailFacture.setMontantTVA(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
										        	  detailFacture.setMontantTTC(((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP));
										          }
										         
								        	   
									          }else
									          {
								           
								          
									       detailFacture.setSousFamille(sousfamille);
									       if(!txtreduction.getText().equals(""))
									          {
									    	   if(radioRemisePlus.isSelected()==true)
									        	  {
									    		   detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP).add( montant.setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											       detailFacture.setMontantTVA(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).add(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
										        	  detailFacture.setMontantTTC((((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).add((((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

									    		   
									        	  }else if(radioRemiseMoins.isSelected()==true)
									        	  {
									        		  detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP).subtract( montant.setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
												       detailFacture.setMontantTVA(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).subtract(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));
											        	
										        	  detailFacture.setMontantTTC((((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

									        		  
									        	  }else
									        	  {

									        		  JOptionPane.showMessageDialog(null, "Veuillez Selectionné le type de Remise + ou - SVP","Attention",JOptionPane.ERROR_MESSAGE);
									        			return;	  
									        	  }
									       
									    	   
									         
									          
									          
									          }else
									          {
									        	  detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP));
											       detailFacture.setMontantTVA(((montant).multiply(TVA)).setScale(6, RoundingMode.HALF_UP));
									        	  detailFacture.setMontantTTC(((montant).add((montant).multiply(TVA))).setScale(6, RoundingMode.HALF_UP));
									          }
									          }
								          
								           detailFacture.setFactureAvoirPf(factureAvoirpfComparaison);
								       //    detailFacture.setDateCreation(new Date());
								           
								           detailFacture.setUtilisateur(utilisateur);
								           
								           /*
								           if(stockpf!=null)
								           {
								        	  
								        		 
								        	  
								        	   
								        	 
								        	   if(stockpf.getStock().compareTo(QuantiteTotal)>=0)
								        	   {
								        		   odlQuantite=stockpf.getStock().subtract(QuantiteTotal);
								        		   if(odlQuantite.compareTo(BigDecimal.ZERO)<=0)
										        	 {
								        			   odlQuantite=BigDecimal.ZERO; 
										        		// oldprix=stockpf.getPrixUnitaire();
										        		
										        	 }else
										        	 {
										        		  // oldprix=((stockpf.getPrixUnitaire().multiply(odlQuantite.add(quantite))).subtract(quantite.multiply(new BigDecimal(txtPrix.getText())))).divide(odlQuantite, RoundingMode.HALF_UP);
 
										        	 }
								        		   
								        		  
								        		   stockpf.setStock(odlQuantite);
										          // stockpf.setPrixUnitaire(oldprix);
										           listStockPF.set(tableArticle.getSelectedRow(), stockpf);
								        		   
								        		   
								        	   }else
								        	   {
								        		   JOptionPane.showMessageDialog(null, "la Quantité d'article est supérieur de celle de stock !!!!!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
								        		   return;
								        	   }
								        	 
									          
								           }  else
								           {

								        	   
								        	   JOptionPane.showMessageDialog(null, "Article introuvable dans le stock !!!!!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
							        		   return;
								           
								           
								           }
								          */
								          
								           listDetailFactureAvoirpfComparaison.set(tableArticle.getSelectedRow(), detailFacture);
								           
								      
											
								           //
											
								          afficher_tableDetailFactureAchat(listDetailFactureAvoirpfComparaison);
								          int i=0;
									        BigDecimal montanttotal=BigDecimal.ZERO;
									        BigDecimal sommequantite=BigDecimal.ZERO;
									        BigDecimal montanttotalHT=BigDecimal.ZERO;
									        BigDecimal montanttotalTVA=BigDecimal.ZERO;
									        while(i<listDetailFactureAvoirpfComparaison.size())
									        {
									        	
									        	articleboisson=false;
									        	
													if(listDetailFactureAvoirpfComparaison.get(i).getArticle().getCentreCout2()!=null)
													{
														if(listDetailFactureAvoirpfComparaison.get(i).getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
														{
															articleboisson=true;	
														}
														
													}
												
								        	 
									        	if(articleboisson==true)
									        	{
									        		  DetailFactureAvoirPFComparaison detailFactureAvoirpf=listDetailFactureAvoirpfComparaison.get(i);
									        		  
									        		  montanttotal=  montanttotal.add(detailFactureAvoirpf.getMontantTTC());
											          sommequantite= sommequantite.add(detailFactureAvoirpf.getQuantite());
											          montanttotalHT=montanttotalHT.add(detailFactureAvoirpf.getMontantHT());
											          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirpf.getMontantTVA());
									        		  
									        		  
											  /*        montanttotal=  montanttotal.add(detailFactureAvoirpf.getMontantTTC().divide(detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP));
											          sommequantite= sommequantite.add(detailFactureAvoirpf.getQuantite().divide(detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP));
											          montanttotalHT=montanttotalHT.add(detailFactureAvoirpf.getMontantHT().divide(detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP));
											          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirpf.getMontantTVA().divide(detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP));*/
									        	}else
									        	{
									        	
									        	  DetailFactureAvoirPFComparaison detailFactureAvoir=listDetailFactureAvoirpfComparaison.get(i);
										          montanttotal=  montanttotal.add(detailFactureAvoir.getMontantTTC());
										          sommequantite= sommequantite.add(detailFactureAvoir.getQuantite());
										          montanttotalHT=montanttotalHT.add(detailFactureAvoir.getMontantHT());
										          montanttotalTVA=montanttotalTVA.add(detailFactureAvoir.getMontantTVA());
									        	}
									            i++;
									        }
									       txttotalmontantTTC.setText(montanttotal+"");
									        txttotalquantite.setText(sommequantite+"");
									        txttotalmontantHT.setText(montanttotalHT+"");
								  			txttotalmontantTVA.setText(montanttotalTVA+"");
								  			
								  			
								  			
                                                 
						        		 
						        		initialiser();
						        	
						        		
						        		 
						        	 }else
						       {
						    	   JOptionPane.showMessageDialog(null, "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						    	   return;
						       }
						       
					         }
					        
						}
						
			   		
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité , le Prix et la Remise doit etre en chiffre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
		   		
		   	}
		   		
		   
		   });
		  btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  
		   btnSupprimer = new JButton();
		  btnSupprimer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		BigDecimal stocktmp=BigDecimal.ZERO;
		  		boolean articleboisson=false;
		  		if(tableArticle.getSelectedRow()!=-1)
		  		{
		  			 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer l'article dans la facture  ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
							
						{
							/*
							 StockPF stockpftmp =listStockPF.get(tableArticle.getSelectedRow());
				        	 stocktmp=stockpftmp.getStock().subtract(listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite());
				        	 stockpftmp.setStock(stocktmp);
				        	 */
				  		listDetailFactureAvoirpfComparaison.remove(tableArticle.getSelectedRow());
				  	//	listStockPF.remove(tableArticle.getSelectedRow());
				  		
				  		
				  		
				  		
				  	
				  		
				        afficher_tableDetailFactureAchat(listDetailFactureAvoirpfComparaison);
				          int i=0;
					        BigDecimal montanttotal=BigDecimal.ZERO;
					        BigDecimal sommequantite=BigDecimal.ZERO;
					        BigDecimal montanttotalHT=BigDecimal.ZERO;
					        BigDecimal montanttotalTVA=BigDecimal.ZERO;
					        while(i<listDetailFactureAvoirpfComparaison.size())
					        {
					        	articleboisson=false;
					        	
									if(listDetailFactureAvoirpfComparaison.get(i).getArticle().getCentreCout2()!=null)
									{
										if(listDetailFactureAvoirpfComparaison.get(i).getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
										{
											articleboisson=true;
										}
										
									}
							
				        	 
					        	if(articleboisson==true)
					        	{
					        		 DetailFactureAvoirPFComparaison detailFactureAvoir=listDetailFactureAvoirpfComparaison.get(i);
							          montanttotal=  montanttotal.add(detailFactureAvoir.getMontantTTC().divide(detailFactureAvoir.getArticle().getConditionnement(),RoundingMode.HALF_UP));
							          sommequantite= sommequantite.add(detailFactureAvoir.getQuantite().divide(detailFactureAvoir.getArticle().getConditionnement(),RoundingMode.HALF_UP));
							          montanttotalHT=montanttotalHT.add(detailFactureAvoir.getMontantHT().divide(detailFactureAvoir.getArticle().getConditionnement(),RoundingMode.HALF_UP));
							          montanttotalTVA=montanttotalTVA.add(detailFactureAvoir.getMontantTVA().divide(detailFactureAvoir.getArticle().getConditionnement(),RoundingMode.HALF_UP));
					        	}else
					        	{
					        	 DetailFactureAvoirPFComparaison detailFactureAvoir=listDetailFactureAvoirpfComparaison.get(i);
						          montanttotal=  montanttotal.add(detailFactureAvoir.getMontantTTC());
						          sommequantite= sommequantite.add(detailFactureAvoir.getQuantite());
						          montanttotalHT=montanttotalHT.add(detailFactureAvoir.getMontantHT());
						          montanttotalTVA=montanttotalTVA.add(detailFactureAvoir.getMontantTVA());
					        	}
					            i++;
					        }
					       txttotalmontantTTC.setText(montanttotal+"");
					       txttotalquantite.setText(sommequantite+"");
				  			txttotalmontantHT.setText(montanttotalHT+"");
				  			txttotalmontantTVA.setText(montanttotalTVA+"");
				  			JOptionPane.showMessageDialog(null, "Article supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
				  			initialiser();
						}
							
							
		  			
		  			
		  			
		  	
		  			
		  		}
		  		
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1137, 475, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(869, 717, 105, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(984, 717, 134, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(742, 667, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(653, 667, 79, 26);
		add(lblTotalQuantite);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1137, 445, 73, 24);
		add(btnModifier);
		
		JLabel lblTotalMontantHt = new JLabel("Total Montant HT :");
		lblTotalMontantHt.setBounds(869, 655, 105, 26);
		add(lblTotalMontantHt);
		
		txttotalmontantHT = new JTextField();
		txttotalmontantHT.setEditable(false);
		txttotalmontantHT.setColumns(10);
		txttotalmontantHT.setBounds(984, 655, 134, 26);
		add(txttotalmontantHT);
		
		JLabel lblTotalMontantTva = new JLabel("Total Montant TVA :");
		lblTotalMontantTva.setBounds(869, 687, 105, 26);
		add(lblTotalMontantTva);
		
		txttotalmontantTVA = new JTextField();
		txttotalmontantTVA.setEditable(false);
		txttotalmontantTVA.setColumns(10);
		txttotalmontantTVA.setBounds(984, 687, 134, 26);
		add(txttotalmontantTVA);
		
		 comboArticle.addItem("");
		 
		  radiosanstva = new JRadioButton("Sans TVA");
		  radiosanstva.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		combotva.setSelectedIndex(-1);
		 		combotva.setVisible(false);
		  		
		  	}
		  });
		 radiosanstva.setFont(new Font("Tahoma", Font.BOLD, 12));
		 radiosanstva.setBounds(778, 62, 93, 23);
		 layeredPane.add(radiosanstva);
		 group.add(radiosanstva);
		 
		  radioavectva = new JRadioButton("Avec TVA");
		  radioavectva.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		combotva.setVisible(true);
		  		
		  	}
		  });
		 radioavectva.setFont(new Font("Tahoma", Font.BOLD, 12));
		 radioavectva.setBounds(877, 60, 93, 23);
		 layeredPane.add(radioavectva);
		 group.add(radioavectva);
		 
		  combotva = new JComboBox();
		 combotva.setSelectedIndex(-1);
		 combotva.setBounds(976, 60, 133, 24);
		 layeredPane.add(combotva);
		 
		 JLabel label_7 = new JLabel("Qtt Packet:");
		 label_7.setBounds(170, 59, 72, 26);
		 layeredPane.add(label_7);
		 
		 txtquantitepacket = new JTextField();
		 txtquantitepacket.setColumns(10);
		 txtquantitepacket.setBounds(231, 59, 112, 26);
		 layeredPane.add(txtquantitepacket);
		 listArticleStockPF=articleDAO.findAll();
				// stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
		 
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
			radioRemisePlus.setBounds(531, 59, 40, 23);
			layeredPane.add(radioRemisePlus);
			groupremise.add(radioRemisePlus);
			
			 radioRemiseMoins = new JRadioButton("-");
			radioRemiseMoins.setFont(new Font("Tahoma", Font.BOLD, 15));
			radioRemiseMoins.setBounds(584, 59, 40, 23);
			layeredPane.add(radioRemiseMoins);
			groupremise.add(radioRemiseMoins);
			
			 checkAvoirMarjane = new JCheckBox("Avoir Marjane");
			checkAvoirMarjane.setBounds(1132, 221, 97, 23);
			add(checkAvoirMarjane);
			combotva.setVisible(false);
		 
		
		
			
		}
	

	
	void initialiserFacture()
	{
		dateChooserfacture.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);
		
		txttotalmontantTTC.setText("");
		txttotalquantite.setText("");
		txttotalmontantHT.setText("");
		txttotalmontantTVA.setText("");
		comboFournisseur.setSelectedIndex(-1);
		txtnumfacture.setText("");
		txtcodefournisseur.setText("");
		
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
		     combosousfamille.setSelectedIndex(-1);
		     txtquantitepacket.setText("");
		     radiosanstva.setSelected(true);
				combotva.setSelectedIndex(-1);
				combotva.setVisible(false);
			radioRemiseMoins.setSelected(false);
			radioRemisePlus.setSelected(false);
		
	}
	
	void InitialiseTableau()
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite", "Reduction", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleChargefixe);
		 tableArticle.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableArticle.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableArticle.getColumnModel().getColumn(2).setPreferredWidth(94);
		
	
}
	
	
	void afficher_tableDetailFactureAchat(List<DetailFactureAvoirPFComparaison> listDetailFacture)
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Reduction %", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleChargefixe);
		int i=0;
		 
		while(i<listDetailFacture.size())
		{	
			boolean existe=false;
		DetailFactureAvoirPFComparaison detailfactureAchat=listDetailFacture.get(i);
			Articles article=detailfactureAchat.getArticle();
			
		/*		if(article.getCentreCout2()!=null)
				{
					if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
					{
					existe=true;
					}
				}
		
			if(existe==true)
			{
				Object []ligne={detailfactureAchat.getSousFamille().getFamileArticlePF().getLiblle(),detailfactureAchat.getSousFamille().getLiblle(),detailfactureAchat.getArticle().getLiblle(),detailfactureAchat.getPrixUnitaire(),detailfactureAchat.getQuantite().divide(article.getConditionnement(),RoundingMode.HALF_UP),detailfactureAchat.getReduction().divide(article.getConditionnement(),RoundingMode.HALF_UP), detailfactureAchat.getMontantHT().divide(article.getConditionnement(),RoundingMode.HALF_UP),detailfactureAchat.getMontantTVA().divide(article.getConditionnement(),RoundingMode.HALF_UP),detailfactureAchat.getMontantTTC().divide(article.getConditionnement(),RoundingMode.HALF_UP)};

				modeleChargefixe.addRow(ligne);
			}else
			{*/
				Object []ligne={detailfactureAchat.getSousFamille().getFamileArticlePF().getLiblle(),detailfactureAchat.getSousFamille().getLiblle(),detailfactureAchat.getArticle().getLiblle(),detailfactureAchat.getPrixUnitaire(),detailfactureAchat.getQuantite(),detailfactureAchat.getReduction(), detailfactureAchat.getMontantHT(),detailfactureAchat.getMontantTVA(),detailfactureAchat.getMontantTTC()};

				modeleChargefixe.addRow(ligne);
		/*	}*/
			
			i++;
		}
}
	}


