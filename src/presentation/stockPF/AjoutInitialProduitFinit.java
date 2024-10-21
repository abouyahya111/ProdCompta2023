package presentation.stockPF;

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
import org.jdesktop.swingx.decorator.HighlighterFactory;

 
import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
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
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FournisseurDAO;
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
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
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
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class AjoutInitialProduitFinit extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleChargefixe;

	private JXTable  tableArticle = new JXTable();
	
	
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<Articles> listArticleTmp =new ArrayList<Articles>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<StockPF> listStockPF =new ArrayList<StockPF>();
	private List<Articles> listArticleStockPF =new ArrayList<Articles>();
	private List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	private List<SousFamilleArticlePF> listSousFamilleArticle =new ArrayList<SousFamilleArticlePF>();
	
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	
	private  JDateChooser dateChooserPF = new JDateChooser();
	TransferStockPF transferStock = new TransferStockPF();
private	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();

private Map< String, Articles> mapCodeArticle = new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, FamilleArticlePF> mapfamille= new HashMap<>();
	private Map< String, SousFamilleArticlePF> mapsousfamille= new HashMap<>();
	
	
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
	
	 private JComboBox comboArticle;

	private ArticlesDAO articleDAO;
	
	
	private TransferStockPFDAO transferStockPFDAO;
private FamilleArticlesPFDAO familleArticleDAO;
private SousFamilleArticlesPFDAO sousFamilleArticleDAO;
private StockPFDAO stockpfDAO;
	private JTextField txtcodearticle;
	ChargeProduction chargeproduction;
	private JTextField txtquantite;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	private   JComboBox combofamille = new JComboBox();
	private DepotDAO depotdao;
	
	private CompteClientDAO compteclientdao;
	private FournisseurDAO fournisseurdao;
	private static SequenceurDAO sequenceurDAO=ProdLauncher.sequenceurDAO;
	private DetailTransferProduitFiniDAO detailTransferStockPFDAO;
	 JComboBox combomagasin = new JComboBox();
	 private    JComboBox combosousfamille = new JComboBox();
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private JTextField txtPrix;
	private  JButton btnModifier ;
	private  JButton btnSupprimer = new JButton();
	 private   JComboBox comboFournisseur = new JComboBox();
	 private JTextField txtquantitepacket;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutInitialProduitFinit() {
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
         	transferStockPFDAO=new TransferStockPFDAOImpl();
         	
         	stockpfDAO=new StockPFDAOImpl();
         
         	articleDAO=new ArticlesDAOImpl();
         	
         	 detailTransferStockPFDAO=new DetailTransferProduitFiniDAOImpl();
         	
         	familleArticleDAO=new FamilleArticlesPFDAOImpl();
         	sousFamilleArticleDAO=new SousFamilleArticlesPFDAOImpl();
         	listFamilleArticle=familleArticleDAO.findAll();
         
         
         	
          } catch (Exception exp){exp.printStackTrace();}
        tableArticle.setSortable(false);
        tableArticle.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		combofamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0));
       		combosousfamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
       		comboArticle.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2));
       		txtPrix.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString());
       		txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());
       	
       		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
       	
       		
     
       		btnAjouter.setEnabled(false);
       		
       		
       		 	}
       });
        
       tableArticle.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Magasin"
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
				  		     	scrollPane.setBounds(10, 414, 1117, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 373, 1117, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 221, 1117, 108);
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
		       comboArticle.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {

		     	 		
  		     	 	if(comboArticle.getSelectedIndex()!=-1)
  			 		{
  			 			if(!comboArticle.getSelectedItem().equals(""))
  				 		{
  				 			Articles article=mapArticle.get(comboArticle.getSelectedItem());
  				 			txtcodearticle.setText(article.getCodeArticle());
  				 		
  				 					 			
  				 		}else
  				 		{
  				 			txtcodearticle.setText("");
  				 			
  				 		}
  				 	
  			 		}
  			 		
  			 		
		       	}
		       });
		      comboArticle.setBounds(796, 11, 300, 27);
		      layeredPane.add(comboArticle);
		      
		     
		     	
		      
		      JLabel lblQuantit = new JLabel("Quantite :");
		      lblQuantit.setBounds(8, 71, 72, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      util.Utils.copycoller(txtquantite);
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			
		     			
		     		
		      		
		      	}
		      });
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(63, 71, 112, 26);
		      layeredPane.add(txtquantite);
		      
		      JLabel lblPrix = new JLabel("Prix  :");
		      lblPrix.setBounds(377, 71, 45, 26);
		      layeredPane.add(lblPrix);
		      
		      txtPrix = new JTextField();
		      txtPrix.setColumns(10);
		      txtPrix.setBounds(417, 71, 140, 26);
		      layeredPane.add(txtPrix);
		      
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
	    		        			 
	    		        		}
	    		        			
	        				 
	        			 }else
	        			 {
	        				combosousfamille.removeAllItems(); 
	        				 
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
		       	public void itemStateChanged(ItemEvent e) {/*
	        		

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
			        				 listArticleStockPF=articleDAO.listeArticleBySousFamille(sousfamille.getId());
			        						// stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
			        				 
			        				 for(int i=0;i<listArticleStockPF.size();i++)
			        				 {
			        					Articles article= listArticleStockPF.get(i);
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
	        		
	        		
	        		
	        	*/}
		       });
		      combosousfamille.setSelectedIndex(-1);
		      combosousfamille.setBounds(351, 13, 160, 24);
		      layeredPane.add(combosousfamille);
		 
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal solde=BigDecimal.ZERO;
				
				BigDecimal nouveauprix=BigDecimal.ZERO;
				BigDecimal coutAchat=BigDecimal.ZERO;
				BigDecimal coutStock=BigDecimal.ZERO;
				BigDecimal QuantiteTotal=BigDecimal.ZERO;
				
				 if(combodepot.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
				}else if(combomagasin.getSelectedIndex()==-1)
				{
					JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;	
					
					
				}else if(listStockPF.size()==0)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer les articles  SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else
				{
					
				for(int i=0;i<listStockPF.size();i++)
				{
					
					StockPF stockPF=stockpfDAO.findStockByMagasinPFBySousFamille(listStockPF.get(i).getArticles().getId(), listStockPF.get(i).getMagasin().getId(),listStockPF.get(i).getSousFamille().getId());
					
					   if(stockPF!=null)
			           {
				        	  coutAchat=listStockPF.get(i).getStock().multiply(listStockPF.get(i).getPrixUnitaire());
				        	  coutStock=stockPF.getStock().multiply(stockPF.getPrixUnitaire());
				        	  QuantiteTotal=listStockPF.get(i).getStock().add(stockPF.getStock());
				        	   
				        	  nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotal, 6, RoundingMode.HALF_UP);
				        	  stockPF.setStock(QuantiteTotal);
				        	  stockPF.setPrixUnitaire(nouveauprix);
					         stockpfDAO.edit(stockPF);
					         
				           }else
			           {
				        	   // creer stock avec prix (calculer prix moyen copier traitement de terminer production )
				        	   
				        	  StockPF stockpfTmp=new StockPF(); 
				        	   
				        	  stockpfTmp.setArticles(listStockPF.get(i).getArticles());
				        	  stockpfTmp.setMagasin(listStockPF.get(i).getMagasin());
				        	  stockpfTmp.setPrixUnitaire(listStockPF.get(i).getPrixUnitaire());
				        	  stockpfTmp.setStock(listStockPF.get(i).getStock());
				        	  stockpfTmp.setStockMin(BigDecimal.ZERO);
				        	  stockpfTmp.setSousFamille(listStockPF.get(i).getSousFamille());
				        	  stockpfDAO.add(stockpfTmp);
				        	  
				        	  }
					
					
				}
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				String codeTransfert=Utils.genererCodeTransfer(depot.getCode(),ETAT_TRANSFER_STOCK_INITIAL);
				transferStock.setCodeTransfer(codeTransfert);
				transferStock.setCreerPar(utilisateur);
				transferStock.setDate(dateChooserPF.getDate());
				transferStock.setDateTransfer(dateChooserPF.getDate());
				//transferStock.setListDetailTransferProduitFini(listDetailTransferProduitFini);
				transferStock.setStatut(ETAT_TRANSFER_STOCK_INITIAL);
				transferStockPFDAO.add(transferStock);
				for(int i=0;i<listDetailTransferProduitFini.size();i++)
				{
					detailTransferStockPFDAO.add(listDetailTransferProduitFini.get(i));
				}
				listDetailTransferProduitFini.clear();
				
		    JOptionPane.showMessageDialog(null, "Stock Produit Fini Valider avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
		    initialisedepot();
		    initialiser();
		  
		    listStockPF.clear();
			InitialiseTableau();
					
				}
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(441, 689, 112, 24);
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
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(95, 30, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(161, 31, 209, 24);
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
		  label_4.setBounds(421, 30, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  	}
		  });
		  combomagasin.setBounds(501, 31, 183, 24);
		  layeredPane_1.add(combomagasin);
		  combomagasin.setSelectedIndex(-1);
		  
		  JLabel label = new JLabel("Date  :");
		  label.setBounds(765, 33, 62, 24);
		  layeredPane_1.add(label);
		  
		   dateChooserPF = new JDateChooser();
		  dateChooserPF.setLocale(Locale.FRANCE);
		  dateChooserPF.setDateFormatString("dd/MM/yyyy");
		  dateChooserPF.setBounds(822, 31, 181, 26);
		  layeredPane_1.add(dateChooserPF);
		  
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Depot");
		titledSeparator_2.setBounds(10, 11, 1117, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(341, 341, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					BigDecimal QuantiteTotal=BigDecimal.ZERO;
					BigDecimal QuantiteUnit=BigDecimal.ZERO;
					BigDecimal QuantitePaquet=BigDecimal.ZERO;
					boolean existe=false;
					
					 if(dateChooserPF.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(combodepot.getSelectedItem().equals(""))
					{
						
						JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if(combomagasin.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
						
					}else if(combofamille.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir famille d'article SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if(combosousfamille.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir sous famille d'article SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}
					else if(comboArticle.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez choisir l'article SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
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
						
					
					}else if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)<=0)
					{
						JOptionPane.showMessageDialog(null, " le prix doit etre supérieur à 0 SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else
					{
						
						Articles article=mapArticle.get(comboArticle.getSelectedItem());
						Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
						SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
						
						
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
						
						DetailTransferProduitFini detailtransferpf=detailTransferStockPFDAO.findTransferStockPFByArticleBytypetransfer(article, ETAT_TRANSFER_STOCK_INITIAL,magasin,sousfamille);
						if(detailtransferpf!=null)
						{
							
							JOptionPane.showMessageDialog(null, "l'Article déja initialiser SVP !!!!!");
							return;
						}
						
						
						for(int i=0;i<listStockPF.size();i++)
						{
							
							StockPF stockpf=listStockPF.get(i);
							
							if(stockpf.getArticles().getCodeArticle().equals(article.getCodeArticle()))
							{
								if(stockpf.getMagasin().getId()==magasin.getId())
								{
									if(stockpf.getSousFamille().equals(sousfamille))
									{
										existe=true;
									}
									
									
								}
																
							}
							
						}
						
						if(existe==false)
						{
							boolean trouve=false;
							DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
							StockPF stockpf=new StockPF();
							stockpf.setArticles(article);
							stockpf.setMagasin(magasin);
							stockpf.setStock(QuantiteTotal);	
							/*if(article.getCentreCout2()!=null)
								{
								if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
								{
									trouve=true;
								}
								}*/
							
						/*	if(trouve==true)
							{
								stockpf.setPrixUnitaire(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 2, RoundingMode.HALF_DOWN));
								
							}else
							{*/
								stockpf.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
							/*}*/
						
							stockpf.setSousFamille(sousfamille);
							stockpf.setStockMin(BigDecimal.ZERO);
							listStockPF.add(stockpf);
							detailTransferStockPF.setArticle(article);
							detailTransferStockPF.setDateTransfer(dateChooserPF.getDate());
							detailTransferStockPF.setMagasinDestination(magasin);
							detailTransferStockPF.setQuantite(QuantiteTotal);	
							
							
						/*	if(trouve==true)
							{
								detailTransferStockPF.setPrixUnitaire(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 6, RoundingMode.HALF_DOWN));
							}else
							{*/
								detailTransferStockPF.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
							/*}*/
													
							detailTransferStockPF.setTransferStockPF(transferStock);
							detailTransferStockPF.setSousFamille(sousfamille);
							detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_INITIAL);
							listDetailTransferProduitFini.add(detailTransferStockPF);
							afficher_tableStockPF(listStockPF);
							initialiser();
							
						}else
						{
							
							JOptionPane.showMessageDialog(null, "Article existe déja dans cette magasin veuillez le modifier SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
					}
					
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité et le Prix doit etre en chiffre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
				
			
		});	
		btnAjouter.setIcon(imgAjouter);
		
		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnInitialiser = new JButton("Initialiser");
		  btnInitialiser.setBounds(472, 340, 106, 23);
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
		  		
		  		initialisedepot();
		  	
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(441, 147, 106, 23);
		  add(button);
		  
		   btnModifier = new JButton();
		   btnModifier.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		try {
		   			
		   			BigDecimal QuantiteTotal=BigDecimal.ZERO;
					BigDecimal QuantiteUnit=BigDecimal.ZERO;
					BigDecimal QuantitePaquet=BigDecimal.ZERO;

			   		boolean existe=false;
	if(tableArticle.getSelectedRow()!=-1)
	{
		
		 if(dateChooserPF.getDate()==null)
			{

				JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				return;	
			}else if(combodepot.getSelectedItem().equals(""))
		{
			
			JOptionPane.showMessageDialog(null, "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}else if(combomagasin.getSelectedItem().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
			
		}else if(combofamille.getSelectedItem().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Veuillez choisir famille d'article SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}else if(combosousfamille.getSelectedItem().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Veuillez choisir sous famille d'article SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(comboArticle.getSelectedItem().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Veuillez choisir l'article SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Veuillez saisir le prix d'article SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
			
		}else if(new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)<=0)
		{
			JOptionPane.showMessageDialog(null, " le prix doit etre supérieur à 0 SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}else
		{
			
			Articles article=mapArticle.get(comboArticle.getSelectedItem());
			Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			SousFamilleArticlePF sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
			
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
			
			
			for(int i=0;i<listStockPF.size();i++)
			{
				
				StockPF stockpf=listStockPF.get(i);
				
				if(stockpf.getArticles().getCodeArticle().equals(article.getCodeArticle()) && i !=tableArticle.getSelectedRow())
				{
					if(stockpf.getMagasin().getId()==magasin.getId() && i !=tableArticle.getSelectedRow())
					{
						existe=true;
						
					}
					
					
				}
				
				
			}
			
			if(existe==false)
			{
				boolean trouve=false;
				
			StockPF stockpf=listStockPF.get(tableArticle.getSelectedRow());
			
			stockpf.setArticles(article);
			stockpf.setMagasin(magasin);
			stockpf.setStock(QuantiteTotal);	
			
			/*	if(article.getCentreCout2()!=null)
				{
					if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
					{
					trouve=true;
					}
				}*/
			
		/*	if(trouve==true)
			{
				
				stockpf.setPrixUnitaire(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 2, RoundingMode.HALF_DOWN));
				
			}else
			{*/
				stockpf.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
			/*}*/
			
			stockpf.setSousFamille(sousfamille);
			stockpf.setStockMin(BigDecimal.ZERO);
			listStockPF.set(tableArticle.getSelectedRow(), stockpf);
				
			DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(tableArticle.getSelectedRow());
			
			detailTransferStockPF.setArticle(article);
			detailTransferStockPF.setDateTransfer(dateChooserPF.getDate());
			detailTransferStockPF.setMagasinDestination(magasin);
			detailTransferStockPF.setQuantite(QuantiteTotal);
		/*	if(trouve==true)
			{
				detailTransferStockPF.setPrixUnitaire(new BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 2, RoundingMode.HALF_DOWN));
				
			}else
			{*/
				detailTransferStockPF.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
				
			/*}*/
			detailTransferStockPF.setSousFamille(sousfamille);
			detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_INITIAL);
			listDetailTransferProduitFini.set(tableArticle.getSelectedRow(), detailTransferStockPF);
			 
				initialiser();
				afficher_tableStockPF(listStockPF);
				
				
			}else
			{
				
				JOptionPane.showMessageDialog(null, "Article existe déja dans cette magasin veuillez le modifier SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				return;	
			}
			
			
			
		}
		
		
	}
				
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "La Quantité et le prix doit etre en chiffre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
		   		
		   		
		   	}
		   		
		   
		   });
		  btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  
		   btnSupprimer = new JButton();
		  btnSupprimer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {

		  		if(tableArticle.getSelectedRow()!=-1)
		  		{
		  			
		  			listStockPF.remove(tableArticle.getSelectedRow());
		  			listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
		  			initialiser();
		  			afficher_tableStockPF(listStockPF);
		  			
		  			
		  			
		  		}
		  	
		  	
		  	
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1137, 475, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1137, 445, 73, 24);
		add(btnModifier);
		
		 comboArticle.addItem("");
		 
		 JLabel lblQttPacket = new JLabel("Qtt Packet:");
		 lblQttPacket.setBounds(185, 71, 72, 26);
		 layeredPane.add(lblQttPacket);
		 
		 txtquantitepacket = new JTextField();
		 txtquantitepacket.setColumns(10);
		 txtquantitepacket.setBounds(255, 71, 112, 26);
		 layeredPane.add(txtquantitepacket);
		 listArticleTmp=articleDAO.findAll();
				// stockpfDAO.findStockArticleByMagasinPFBySousFamille(magasin.getId(), sousfamille.getId());
		 
		 for(int j=0;j<listArticleTmp.size();j++)
		 {
			Articles article= listArticleTmp.get(j);
			comboArticle.addItem(article.getLiblle());
			 mapArticle.put(article.getLiblle(), article);
			 mapCodeArticle.put(article.getCodeArticle(), article);
		 }
		
		
		
			
		}
	
void ReglerPrixInitial()
{

		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		listDetailTransferProduitFini=detailTransferStockPFDAO.findBytypetransfer(Constantes.ETAT_TRANSFER_STOCK_INITIAL, magasin);
		for(int i=0;i<listDetailTransferProduitFini.size();i++)
		{
			
			DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(i);
			if(detailTransferProduitFini.getArticle().getCentreCout2().equals(Constantes.ARTICLE_PACKET))
			{
				
				detailTransferProduitFini.setPrixUnitaire(detailTransferProduitFini.getPrixUnitaire().divide(detailTransferProduitFini.getArticle().getConditionnement() , 6, RoundingMode.HALF_DOWN));
				detailTransferStockPFDAO.edit(detailTransferProduitFini);
				
			}
			
			
		}
		
		JOptionPane.showMessageDialog(null, "Prix Modifier Avec Succé");
		
}
	
	
	
	
	void initialisedepot()
	{
		
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);
		
		}

	void initialiser()
	{
		txtcodearticle.setText("");
		comboArticle.setSelectedIndex(-1);
	   txtPrix.setText("");
		txtquantite.setText("");
		
	     btnAjouter.setEnabled(true);
         combofamille.setSelectedItem("");
		 combosousfamille.setSelectedIndex(-1);
		
	}
	
	void InitialiseTableau()
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Magasin"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false
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
	
	
	void afficher_tableStockPF(List<StockPF> listStockPF)
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Article", "Prix Unitaire", "Quantite","Magasin"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleChargefixe);
		int i=0;
		 
		while(i<listStockPF.size())
		{	
			boolean trouve=false;
		StockPF stockpf=listStockPF.get(i);
		Articles article=stockpf.getArticles();
		
			
			if(article.getCentreCout2()!=null)
			{
				if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET))
				{
				trouve=true;
				}
			}
			
		
		
		if(trouve==true)
		{
			Object []ligne={stockpf.getSousFamille().getFamileArticlePF().getLiblle(),stockpf.getSousFamille().getLiblle(),stockpf.getArticles().getLiblle(),stockpf.getPrixUnitaire(),stockpf.getStock().divide(article.getConditionnement(), 6, RoundingMode.HALF_UP),stockpf.getMagasin().getLibelle()};

			modeleChargefixe.addRow(ligne);
		}else
		{
			Object []ligne={stockpf.getSousFamille().getFamileArticlePF().getLiblle(),stockpf.getSousFamille().getLiblle(),stockpf.getArticles().getLiblle(),stockpf.getPrixUnitaire(),stockpf.getStock(),stockpf.getMagasin().getLibelle()};

			modeleChargefixe.addRow(ligne);
		}
		
			i++;
		}
}
	}


