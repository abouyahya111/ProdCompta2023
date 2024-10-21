package presentation.stockMP;

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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.JourneeDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
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
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.JourneeDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
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
import dao.entity.DetailMouvementStock;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.Journee;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Sequenceur;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
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


public class AjoutInitialMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMP;

	private JXTable  tableMP = new JXTable();
	
	
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<StockMP> listStockMP =new ArrayList<StockMP>();
	private List<StockMP> listStockMPTmp =new ArrayList<StockMP>();
	private List<StockMP> listStockMPAfficher =new ArrayList<StockMP>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<DetailMouvementStock> listDetailMouvementStock =new ArrayList<DetailMouvementStock>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private List<SubCategorieMp> listFamille =new ArrayList<SubCategorieMp>();
	private List<CategorieMp> listSousFamille =new ArrayList<CategorieMp>();
	private Map< Integer, BigDecimal> mapQuantiteDetailMouvement= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	private Map< String, Articles> mapArticle = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremiere= new HashMap<>();
	private Map< String, CategorieMp> mapSousFamille= new HashMap<>();
	private Map< String, SubCategorieMp> mapFamille= new HashMap<>();
	
	TransferStockMP transferStock = new TransferStockMP();
	private	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	private TransferStockMPDAO transferStockMPDAO;
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
	
	 private JComboBox combomp;

	


	 private JourneeDAO journeeDAO;
private StockMPDAO stockMPDAO;
	private JTextField txtcodemp;
	
	private JTextField txtquantite;
	private JTextField txtlibelle=new JTextField();
	JComboBox combochargefixe = new JComboBox();
	JComboBox combodepot = new JComboBox();
	
	private DepotDAO depotdao;
	private MatierePremiereDAO matierepremieredao;
	private static SequenceurDAO sequenceurDAO=new SequenceurDAOImpl();
	private MouvementStockGlobalDAO mouvementStockGlobalDAO;
	private DetailTransferMPDAO detailTransferStockMPDAO;
	 JComboBox combomagasin = new JComboBox();
	
	 private JDateChooser dateChooser = new JDateChooser();
	private ChargeFixe chargefixe=new ChargeFixe();
	private ChargeProduction chargeProductionTmp=new ChargeProduction();
	private JTextField txtPrix;
	private  JButton btnModifier ;
	private  JButton btnSupprimer = new JButton();
	 private   JComboBox comboFournisseur = new JComboBox();
	private   JDateChooser dateChooserMP ;
	 JComboBox comboFamille = new JComboBox();
	 JComboBox comboSousFamille = new JComboBox();
	 private CategorieMpDAO categorieMpDAO;
	 private SubCategorieMPDAO subCategorieMPDAO;
	 
	 
	 
	 
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutInitialMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1395, 795);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
           
            utilisateur=AuthentificationView.utilisateur;
         	depotdao=new DepotDAOImpl();
         	matierepremieredao=new MatierePremierDAOImpl();
         	stockMPDAO=new StockMPDAOImpl();
         	mouvementStockGlobalDAO=new MouvementStockGlobalDAOImpl();
        	journeeDAO=new JourneeDAOImpl();
        
         transferStockMPDAO=new TransferStockMPDAOImpl();
         detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
         categorieMpDAO=new CategorieMpDAOImpl();
         subCategorieMPDAO=new SubCategorieMPAOImpl();
         
         listFamille=subCategorieMPDAO.findAll();
         	
          } catch (Exception exp){exp.printStackTrace();}
        tableMP.setSortable(false);
        tableMP.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		
       		
       		comboFamille.setSelectedItem(tableMP.getValueAt(tableMP.getSelectedRow(), 0).toString());
       		comboSousFamille.setSelectedItem(tableMP.getValueAt(tableMP.getSelectedRow(), 1).toString());
       		txtcodemp.setText(tableMP.getValueAt(tableMP.getSelectedRow(), 2).toString());
       		combomp.setSelectedItem(tableMP.getValueAt(tableMP.getSelectedRow(), 3));
       		txtPrix.setText(tableMP.getValueAt(tableMP.getSelectedRow(), 4).toString());
       		txtquantite.setText(tableMP.getValueAt(tableMP.getSelectedRow(), 5).toString());
       		
       		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
       	
       		
     
       		btnAjouter.setEnabled(false);
       		
       		
       		 	}
       });
        
       tableMP.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Famille","Sous Famille","Code MP","Matière Première", "Prix Unitaire", "Quantite"
       	}
       ));
				  		
       tableMP.setShowVerticalLines(false);
       tableMP.setSelectionBackground(new Color(51, 204, 255));
       tableMP.setRowHeightEnabled(true);
       tableMP.setBackground(new Color(255, 255, 255));
       tableMP.setHighlighters(HighlighterFactory.createSimpleStriping());
       tableMP.setColumnControlVisible(true);
       tableMP.setForeground(Color.BLACK);
       tableMP.setGridColor(new Color(0, 0, 255));
       tableMP.setAutoCreateRowSorter(true);
       tableMP.setBounds(2, 27, 411, 198);
       tableMP.setRowHeight(20);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(tableMP);
				  		     	scrollPane.setBounds(10, 414, 1292, 315);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des MP");
				  		     	titledSeparator.setBounds(10, 373, 1292, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 221, 1292, 108);
				  		     	add(layeredPane);
		
		  JLabel lblCodeArticle = new JLabel("Code MP :");
		  lblCodeArticle.setBounds(490, 38, 72, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel lbllibelle = new JLabel("Libelle :");
		  lbllibelle.setBounds(644, 38, 57, 26);
		  layeredPane.add(lbllibelle);
		  lbllibelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		      
		      txtcodemp = new JTextField();
		      util.Utils.copycoller(txtcodemp);
		      txtcodemp.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {
	     			if(e.getKeyCode()==e.VK_ENTER)
			      		{
	     					
			      			if(!txtcodemp.getText().equals(""))
			      			{

			      			MatierePremier mp=mapCodeMP.get(txtcodemp.getText().toUpperCase());
			      			if(mp!=null)
			      			{
			      				
			      				combomp.setSelectedItem(mp.getNom());
			      				
			      			}else
			      			{
			      				 JOptionPane.showMessageDialog(null, " Code MP  incorrecte ", "Erreur", JOptionPane.ERROR_MESSAGE);
			      				
			      			}
			      			
			      			
			      			}else
			      		{
			      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			      			
			      			
			      		}
	     				
	     				
	     				
			      		}
		     			
		     			
		     			
		     			
		     			
		     		}
		      });
		      
		      
		      
		      txtcodemp.setColumns(10);
		      txtcodemp.setBounds(548, 38, 86, 26);
		      layeredPane.add(txtcodemp);
		    
		   
		       combomp = new JComboBox();
		       combomp.addItem("");
		       combomp.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {

		     	 		if(combomp.getSelectedItem()!=null)
		     	 		{
		     	 			
		     	 			if(!combomp.getSelectedItem().equals(""))
	  				 		{
	  				 			MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
	  				 			txtcodemp.setText(mp.getCode());
	  				 		
	  				 			
	  				 		
	  				 		  				 			
	  				 		}else
	  				 		{
	  				 			txtcodemp.setText("");
	  				 			
	  				 		}
		     	 		}else
  				 		{
  				 			txtcodemp.setText("");
  				 			
  				 		}
  		     	 	
  			 		
  				 	
  			 		
  			 		
  			 		
  		     	 		
  		     	 	
		       	}
		       });
		      combomp.setBounds(695, 38, 300, 27);
		      AutoCompleteDecorator.decorate(combomp);
		      layeredPane.add(combomp);
		      
		     
		     	int i=0;
		     	while(i<listMP.size())
		     	{
		     		
		     		MatierePremier mp=listMP.get(i);
		     		mapCodeMP.put(mp.getCode(), mp);
		     		mapMatierePremiere.put(mp.getNom(), mp);
		     		combomp.addItem(mp.getNom());
		     		
		     		
		     		i++;
		     	}
		      
		      JLabel lblQuantit = new JLabel("Quantite :");
		      lblQuantit.setBounds(1005, 39, 57, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      util.Utils.copycoller(txtquantite);
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			
		     			
		     		
		      		
		      	}
		      });
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(1064, 39, 82, 26);
		      layeredPane.add(txtquantite);
		      
		      JLabel lblPrix = new JLabel("Prix  :");
		      lblPrix.setBounds(1156, 39, 45, 26);
		      layeredPane.add(lblPrix);
		      
		      txtPrix = new JTextField();
		      txtPrix.setColumns(10);
		      txtPrix.setBounds(1196, 39, 86, 26);
		      layeredPane.add(txtPrix);
		      
		      JLabel lblFamille = new JLabel("Famille :");
		      lblFamille.setFont(new Font("Tahoma", Font.PLAIN, 12));
		      lblFamille.setBounds(10, 39, 56, 24);
		      layeredPane.add(lblFamille);
		      
		       comboFamille = new JComboBox();
		       comboFamille.addItemListener(new ItemListener() {
		       	public void itemStateChanged(ItemEvent e) {
		       		
		       	 if(e.getStateChange() == ItemEvent.SELECTED)
    	 		 {
		       		 
		       		 listMP.clear(); 
		       		 listSousFamille.clear();
		       		 comboSousFamille.removeAllItems();
		       		 combomp.removeAllItems();
		       		comboSousFamille.addItem("");
		       		SubCategorieMp subCategorieMp=mapFamille.get(comboFamille.getSelectedItem());
		       		
		       		if(subCategorieMp!=null)
		       		{
		       			
		       		listSousFamille=categorieMpDAO.findBySubcategorie(subCategorieMp.getId())	;
		       		
		       		
		       		
		       		for(int j=0;j<listSousFamille.size();j++)
		       		{
		       			
		       		CategorieMp categorieMp=	listSousFamille.get(j);
		       		
		       		
		       		comboSousFamille.addItem(categorieMp.getNom());
		       			
		       		mapSousFamille.put(categorieMp.getNom(), categorieMp)	;
		       			
		       			
		       		}
		       			
		       			
		       			
		       			
		       		}
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
    	 		 }
		       		
		       		
		       		
		       		
		       		
		       		
		       	}
		       });
		      comboFamille.setBounds(60, 40, 140, 24);
		      AutoCompleteDecorator.decorate(comboFamille);
		      layeredPane.add(comboFamille);
		      
		      JLabel lblSousFamille = new JLabel("Sous Famille :");
		      lblSousFamille.setFont(new Font("Tahoma", Font.PLAIN, 12));
		      lblSousFamille.setBounds(210, 39, 86, 24);
		      layeredPane.add(lblSousFamille);
		      
		       comboSousFamille = new JComboBox();
		       comboSousFamille.addItemListener(new ItemListener() {
		       	public void itemStateChanged(ItemEvent e) {
		       		

		       		 
		       		 listMP.clear(); 
		       		
		       		
		       		 combomp.removeAllItems();
		       		combomp.addItem("");
		       		
		       		CategorieMp CategorieMp=mapSousFamille.get(comboSousFamille.getSelectedItem());
		       		
		       		if(CategorieMp!=null)
		       		{
		       			
		       		listMP=matierepremieredao.listeMatierePremierByidcategorie(CategorieMp.getId())	;
		       		
		       		
		       		
		       		for(int j=0;j<listMP.size();j++)
		       		{
		       			
		       		MatierePremier mp=	listMP.get(j);
		       		
		       		
		       		combomp.addItem(mp.getNom());
		       			
		       		mapMatierePremiere.put(mp.getNom(), mp)	;
		       		mapCodeMP.put(mp.getCode(), mp)	;	
		       			
		       		}
		       			
		       			
		       			
		       			
		       		}
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
		       		 
   	 		 
		       		
		       		
		       		
		       		
		       		
		       		
		       		
		       		
		       	}
		       });
		      comboSousFamille.setBounds(284, 40, 196, 24);
		      AutoCompleteDecorator.decorate(comboSousFamille);
		      layeredPane.add(comboSousFamille);
		      
		 
		 
		
		JButton buttonvalider = new JButton("Valider ");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean existe=false;
				MouvementStockGlobal mouvementStockGlobal=null;
				
				 if(dateChooserMP.getDate()==null)
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
						
					}else if(listStockMP.size()==0)
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer les matieres premieres  SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
						return;		
						
						
					}else
					{
						
						 //////////////////////////////////////////////////////////Chercher le Journée et liste des mouvement de stock de cet journée /////////////////////////////////////////
						
					
						  Depot depot=mapDepot.get(combodepot.getSelectedItem());
						  Magasin magasin =mapMagasin.get(combomagasin.getSelectedItem());
						  /*
		               Journee journeeDestination=journeeDAO.findByDateEtatOuverte(dateChooserMP.getDate(), Constantes.ETAT_STATUT_OVERTE,depot.getId());
		               
		               
		               if( journeeDestination!=null)
		              {
		                     	 
		                      ///////// Mouvement Stock Magasin Destination ////////////////////
		                      
		                    
		               
		                      
		                 	 
		                 	 if(depot!=null )
		                 	 {
		                 		 
		                 		mouvementStockGlobal=mouvementStockGlobalDAO.findMouvementStockGlobalByDetailMouvementStock(dateChooserMP.getDate(), depot.getId(), magasin.getId());
		                      if(mouvementStockGlobal!=null)
		                       {
		                    	  listDetailMouvementStock=mouvementStockGlobal.getDetailMouvementStock();
		                    	  existe=true;
		                   }else
		                   {
		                	
		                	   ////////// creer un nouveau mouvement de stock si le magasin de stockage n'existe pas 
		                	   
		                	   existe=false;
  		   							listDetailMouvementStock.clear();
  		   						
  		   						listStockMPTmp=stockMPDAO.findAllByMagasin(magasin.getId());
  		   							MouvementStockGlobal mouvementStockGlobalTmp=new MouvementStockGlobal();
  		   							for(int k=0;k<listStockMPTmp.size();k++)
  		   							{
  		   								StockMP stockmp=listStockMPTmp.get(k);
  		   								
  		   								DetailMouvementStock detailMouvementStock=new DetailMouvementStock();
  		   								
  		   								detailMouvementStock.setDateCreation(dateChooserMP.getDate());
  		   								detailMouvementStock.setMatierePremier(stockmp.getMatierePremier());
  		   								detailMouvementStock.setInitial(stockmp.getStock());
  		   								detailMouvementStock.setUtilisateurCreation(utilisateur);
  		   								detailMouvementStock.setCharge(BigDecimal.ZERO);
  		   								detailMouvementStock.setChargeSupplementaire(BigDecimal.ZERO);
  		   								detailMouvementStock.setReception(BigDecimal.ZERO);
  		   								detailMouvementStock.setSorties(BigDecimal.ZERO);
  		   								detailMouvementStock.setStockFinaldb(stockmp.getStock());
  		   								detailMouvementStock.setTransfertEntrees(BigDecimal.ZERO);
  		   								detailMouvementStock.setTransfertSorties(BigDecimal.ZERO);
  		   						    	detailMouvementStock.setRetour(BigDecimal.ZERO);
  		   								detailMouvementStock.setMouvementStockGlobal(mouvementStockGlobalTmp);
  		   								listDetailMouvementStock.add(detailMouvementStock);
  		   									
  		   								
  		   								
  		   							}
  		   					       mouvementStockGlobalTmp.setDateCreation(dateChooserMP.getDate());
  		   					       mouvementStockGlobalTmp.setDateMouvement(dateChooserMP.getDate());
  		   				           mouvementStockGlobalTmp.setDepot(depot);
  		   			               mouvementStockGlobalTmp.setMagasin(magasin);
  		   		                   mouvementStockGlobalTmp.setUtilisateurCreation(utilisateur);
  		                       	   mouvementStockGlobalTmp.setDetailMouvementStock(listDetailMouvementStock);
  		                           mouvementStockGlobalDAO.add(mouvementStockGlobalTmp);
  		   						
  		   							
		                	   JOptionPane.showMessageDialog(null, "Stock Valider Avec succée ","Bravo",JOptionPane.INFORMATION_MESSAGE);
		                	   initialiserMP();
		                	   initialiserdepot();
		                	   InitialiseTableau();
		                	   listStockMPAfficher.clear();
		                	   listStockMP.clear();
		                   }
		                       
		                 		 
		                 	 }

		                      }
		               
		               else{
		                    	  
		                    	  JOptionPane.showMessageDialog(null, "Veuillez créer Journnée SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
		                    	  return;
		                      }
		                    	 

             
		                  ////////////////////////////////////////////////////// chercher MP dans la liste detailMouvementStock Magasin Destination et Modifier Transfere  entrer Charger et leStock Finale  ////////////////////////////////

		               if(existe==true)
		               {
		            	   
		            	   for(int j=0;j<listDetailMouvementStock.size();j++)
			               {
			               MatierePremier matierepremiere=listDetailMouvementStock.get(j).getMatierePremier();
			               DetailMouvementStock detailMouvementStockDestination=listDetailMouvementStock.get(j);
			               MatierePremier matierepremiereTmp=mapMatierePremierTmp.get(matierepremiere.getCode());
			               
			            if(matierepremiereTmp!=null)
			               {
			            	StockMP stock=stockMPDAO.findStockByMagasinMP(matierepremiereTmp.getId(), magasin.getId());

			          	  detailMouvementStockDestination.setInitial(stock.getStock());
			          	  detailMouvementStockDestination.setStockFinaldb((detailMouvementStockDestination.getInitial().add(detailMouvementStockDestination.getReception()).add(detailMouvementStockDestination.getTransfertEntrees()).subtract((detailMouvementStockDestination.getSorties().add(detailMouvementStockDestination.getTransfertSorties()).add(detailMouvementStockDestination.getCharge()).add(detailMouvementStockDestination.getChargeSupplementaire())))).add(detailMouvementStockDestination.getRetour()));

			                listDetailMouvementStock.set(j, detailMouvementStockDestination);


			              }


			              }
			                  
			                  
			                if( mouvementStockGlobal!=null)
			                {
			                	
			              	  mouvementStockGlobal.setDetailMouvementStock(listDetailMouvementStock);

			                 mouvementStockGlobalDAO.edit(mouvementStockGlobal);
			                 JOptionPane.showMessageDialog(null, "Stock Valider Avec succée ");
			                 initialiserMP();
		                	   initialiserdepot();
		                	   InitialiseTableau();
		                	   listStockMPAfficher.clear();
		                	   listStockMP.clear();

			                   }


			                  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							
							
							
							 
		            	   
		               }
		               */
		               
						String codeTransfert=Utils.genererCodeTransfer(depot.getCode(),ETAT_TRANSFER_STOCK_INITIAL);
						
						transferStock.setCodeTransfer(codeTransfert);
						transferStock.setCreerPar(utilisateur);
						transferStock.setDate(dateChooserMP.getDate());
						transferStock.setDateTransfer(dateChooserMP.getDate());
						transferStock.setDepot(depot);
						//transferStock.setListDetailTransferMP(listDetailTransferStockMP);
						transferStock.setStatut(ETAT_TRANSFER_STOCK_INITIAL);
						transferStockMPDAO.add(transferStock);
						for(int i=0;i<listDetailTransferStockMP.size();i++)
						{
							detailTransferStockMPDAO.add(listDetailTransferStockMP.get(i));
						}
						 JOptionPane.showMessageDialog(null, "Stock Valider Avec succée ");
		                 initialiserMP();
	                	   initialiserdepot();
	                	   InitialiseTableau();
	                	   listStockMPAfficher.clear();
	                	   listDetailTransferStockMP.clear();
	                	   listStockMP.clear();
		             
						
					}
				
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(441, 748, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations MP");
		titledSeparator_1.setBounds(10, 180, 1292, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 39, 1292, 97);
		add(layeredPane_1);
	
		
		JLabel label_3 = new JLabel("Depot :");
		label_3.setBounds(439, 30, 56, 24);
		layeredPane_1.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		  combodepot = new JComboBox();
		  combodepot.setBounds(505, 31, 209, 24);
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
     					listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_MP);
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
		     				
     					
     				}else
     				{
     					combomagasin.removeAllItems();
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
		  label_4.setBounds(756, 31, 56, 24);
		  layeredPane_1.add(label_4);
		  label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  combomagasin = new JComboBox();
		  combomagasin.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent e) {
		  		
		  	}
		  });
		  combomagasin.setBounds(836, 32, 183, 24);
		  layeredPane_1.add(combomagasin);
		  
		  JLabel label = new JLabel("Date  :");
		  label.setBounds(58, 33, 62, 24);
		  layeredPane_1.add(label);
		  
		   dateChooserMP = new JDateChooser();
		  dateChooserMP.setLocale(Locale.FRANCE);
		  dateChooserMP.setDateFormatString("dd/MM/yyyy");
		  dateChooserMP.setBounds(162, 30, 181, 26);
		  layeredPane_1.add(dateChooserMP);
		 
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Depot");
		titledSeparator_2.setBounds(10, 11, 1292, 30);
		add(titledSeparator_2);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(341, 341, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					
					boolean existe=false;
					BigDecimal quantitetotal=BigDecimal.ZERO;
					BigDecimal newprix=BigDecimal.ZERO;
					
					
					 if(dateChooserMP.getDate()==null)
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
							
						}else if(combomp.getSelectedItem().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner la matière première SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtcodemp.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir code MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							}else if(txtquantite.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir quantite MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							} else if( (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))
							{
								JOptionPane.showMessageDialog(null, "la quantite MP doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							}else if(txtPrix.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null, "Veuillez saisir le prix MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							}else if( (new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)<0))
							{
								JOptionPane.showMessageDialog(null, "le prix MP doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
								return;
							
							}else	
							{
								  Depot depot=mapDepot.get(combodepot.getSelectedItem());
								Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
								MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
								StockMP stockmp=stockMPDAO.findStockByMagasinMP(mp.getId(), magasin.getId());
								
								/*
								  Journee journeeDestination=journeeDAO.findByDateEtatOuverte(dateChooserMP.getDate(), Constantes.ETAT_STATUT_OVERTE,depot.getId());
								if(journeeDestination==null)
								{
									 
			                    	  JOptionPane.showMessageDialog(null, "Veuillez créer Journnée SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
			                    	  return;
								}
								*/
								
								DetailTransferStockMP detailtransferStockMP=detailTransferStockMPDAO.findAllTransferStockMPByMPInitialiser(mp, ETAT_TRANSFER_STOCK_INITIAL,depot,magasin);
								
								if(detailtransferStockMP!=null)
								{
									
									JOptionPane.showMessageDialog(null, "La Matière Première déja initialiser SVP !!!!!");
									return;
								}
								
								
								for(int j=0;j<listStockMPAfficher.size();j++)
								{
									if(listStockMPAfficher.get(j).getMatierePremier().getNom().equals(mp.getNom()))
									{
										existe=true;
									}
									
								}
								
								if(existe==false)
								{
									DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
									
										StockMP	stocktmp=new StockMP();
										stocktmp.setMatierePremier(mp);
										stocktmp.setMagasin(magasin);
										stocktmp.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
										stocktmp.setStock(new BigDecimal(txtquantite.getText()));	
										listStockMPAfficher.add(stocktmp);
									
								
								detailTransferStockMP.setMagasinDestination(magasin);
								detailTransferStockMP.setMatierePremier(mp);
								detailTransferStockMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
								detailTransferStockMP.setQuantite(new BigDecimal(txtquantite.getText()));
								detailTransferStockMP.setTransferStockMP(transferStock);
								listDetailTransferStockMP.add(detailTransferStockMP);
								
								
								
								
									
								}else
								{
									JOptionPane.showMessageDialog(null , "La Matière première existe deja dans la liste des MP veuillez le modifier SVP !!!!");
									return;
									
								}
								
								
							
									if(stockmp!=null)
									{
										
										quantitetotal=stockmp.getStock().add(new BigDecimal(txtquantite.getText()));
										
										newprix=((stockmp.getPrixUnitaire().multiply(stockmp.getStock())).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))))).divide(quantitetotal, 6, RoundingMode.HALF_UP);
										stockmp.setPrixUnitaire(newprix);
										stockmp.setStock(quantitetotal);
										listStockMP.add(stockmp);
										stockMPDAO.edit(stockmp);
								  	 
								  	    afficher_tableMP(listStockMPAfficher);
										mapMatierePremierTmp.put(mp.getCode(), mp);
										mapQuantiteDetailMouvement.put(mp.getId(), new BigDecimal(txtquantite.getText()));
										   initialiserMP();
									}else
									{
										StockMP stockmpTMP=new StockMP();
										stockmpTMP.setMagasin(magasin);
										stockmpTMP.setMatierePremier(mp);
										stockmpTMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
										stockmpTMP.setStock(new BigDecimal(txtquantite.getText()));
										stockmpTMP.setStockMin(BigDecimal.ZERO);
										stockmpTMP.setQuantiteCommande(BigDecimal.ZERO);
										listStockMP.add(stockmpTMP);
										stockMPDAO.add(stockmpTMP);
										afficher_tableMP(listStockMPAfficher);
										 mapMatierePremierTmp.put(mp.getCode(), mp);
										 mapQuantiteDetailMouvement.put(mp.getId(), new BigDecimal(txtquantite.getText()));
										 initialiserMP();
									}
									
								
								
							}
						        
							
					
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "la Quantité et le prix doit etre en chiffre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
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
		  	
		  	    initialiserMP();
		  		
		  	}
		  });
		  btnInitialiser.setIcon(imgInit);
		  btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JButton button = new JButton("Initialiser");
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		initialiserdepot();;
		  	
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(441, 147, 106, 23);
		  add(button);
		  
		   btnModifier = new JButton();
		   btnModifier.addActionListener(new ActionListener() {
		   	public void actionPerformed(ActionEvent arg0) {
		   		
		   		
		   		try {
		   			
		   			

			   		
			   		if(tableMP.getSelectedRow()!=-1)
			   		{
			   		

			   			boolean trouve=false;
			   			BigDecimal quantitetotal=BigDecimal.ZERO;
			   			BigDecimal newprix=BigDecimal.ZERO;
			   			BigDecimal oldQuantite=BigDecimal.ZERO;
			   			BigDecimal oldPrix=BigDecimal.ZERO;
			   			
			   					if(combomp.getSelectedItem().equals(""))
			   					{
			   						JOptionPane.showMessageDialog(null, "Veuillez Selectionner la matière première SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					}else if(txtcodemp.getText().equals(""))
			   					{
			   						JOptionPane.showMessageDialog(null, "Veuillez saisir code MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					}else if(txtquantite.getText().equals(""))
			   					{
			   						JOptionPane.showMessageDialog(null, "Veuillez saisir quantite MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					} else if( (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtquantite.getText()).compareTo(BigDecimal.ZERO)<0))
			   					{
			   						JOptionPane.showMessageDialog(null, "la quantite MP doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					
			   					}else if(txtPrix.getText().equals(""))
			   					{
			   						JOptionPane.showMessageDialog(null, "Veuillez saisir le prix MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					
			   					}else if( (new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)==0 )|| (new BigDecimal(txtPrix.getText()).compareTo(BigDecimal.ZERO)<0))
			   					{
			   						JOptionPane.showMessageDialog(null, "le prix MP doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			   						return;
			   					
			   					}else	
			   					{
			   						Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			   						MatierePremier mp=mapMatierePremiere.get(combomp.getSelectedItem());
			   					
			   						
			   						for(int i=0;i<listStockMPAfficher.size();i++)
			   						{
			   							
			   							if(listStockMPAfficher.get(i).getMatierePremier().getNom().equals(mp.getNom()) && i!= tableMP.getSelectedRow())
			   							{
			   								
			   								trouve=true;
			   							}
			   							
			   						}
			   						
			   						if(trouve==false)
			   						{
			   							
			   						 StockMP stockmptmp =stockMPDAO.findStockByMagasinMP(listStockMPAfficher.get(tableMP.getSelectedRow()).getMatierePremier().getId(), listStockMPAfficher.get(tableMP.getSelectedRow()).getMagasin().getId());
							        	
			   						 /// modifier list detail transfer Stock PF
			   						 DetailTransferStockMP detailtransferstockmp=listDetailTransferStockMP.get(tableMP.getSelectedRow());
			   						
			   						detailtransferstockmp.setMagasinSouce(magasin);
			   						detailtransferstockmp.setMatierePremier(mp);
			   						detailtransferstockmp.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
			   						detailtransferstockmp.setQuantite(new BigDecimal(txtquantite.getText()));
			   					
									listDetailTransferStockMP.set(tableMP.getSelectedRow(), detailtransferstockmp);
									
									////////////////////////////////////////////////////////
						        	 
						        	 oldQuantite=stockmptmp.getStock().subtract(listStockMPAfficher.get(tableMP.getSelectedRow()).getStock());
						        	 if(oldQuantite.compareTo(BigDecimal.ZERO)<=0)
						        	 {
						        		 oldQuantite=BigDecimal.ZERO; 
						        		 oldPrix=stockmptmp.getPrixUnitaire();
						        		
						        	 }else
						        	 {
							        	 oldPrix=((stockmptmp.getPrixUnitaire().multiply((listStockMPAfficher.get(tableMP.getSelectedRow()).getStock().add(oldQuantite)))).subtract((listStockMPAfficher.get(tableMP.getSelectedRow()).getStock().multiply(listStockMPAfficher.get(tableMP.getSelectedRow()).getPrixUnitaire())))).divide(oldQuantite, 6, RoundingMode.HALF_UP);
		 
						        	 }
						        	
						        	 stockmptmp.setStock(oldQuantite);
							        	
							        	
						        	 stockmptmp.setPrixUnitaire(oldPrix);
						        	
						        	 stockMPDAO.edit(stockmptmp);
						        	 mapMatierePremierTmp.remove(listStockMPAfficher.get(tableMP.getSelectedRow()).getMatierePremier().getCode());
			   						mapQuantiteDetailMouvement.remove(listStockMPAfficher.get(tableMP.getSelectedRow()).getMatierePremier().getId());
						        	
									StockMP stockmp=stockMPDAO.findStockByMagasinMP(mp.getId(), magasin.getId());
									StockMP stockMPAffichage=listStockMPAfficher.get(tableMP.getSelectedRow());
									stockMPAffichage.setMatierePremier(mp);
									stockMPAffichage.setMagasin(magasin);
									stockMPAffichage.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
									stockMPAffichage.setStock(new BigDecimal(txtquantite.getText()));
									listStockMPAfficher.set(tableMP.getSelectedRow(), stockMPAffichage);
						        	 if(stockmp!=null)
						        	 {
						        		 
						        		 quantitetotal=stockmp.getStock().add(new BigDecimal(txtquantite.getText()));
											
											newprix=((stockmp.getPrixUnitaire().multiply(stockmp.getStock())).add((new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()))))).divide(quantitetotal, 6, RoundingMode.HALF_UP);
											stockmp.setPrixUnitaire(newprix);
											stockmp.setStock(quantitetotal);
											listStockMP.set(tableMP.getSelectedRow(), stockmp);
											
											stockMPDAO.edit(stockmp);
									  	  
									  	    afficher_tableMP(listStockMPAfficher);
									  	  mapMatierePremierTmp.put(mp.getCode(), mp);
									  	mapQuantiteDetailMouvement.put(mp.getId(), new BigDecimal(txtquantite.getText()));
									    initialiserMP();
						        		 
						        	 }else
						        	 {
						        		  listStockMP.remove(tableMP.getSelectedRow()); 
						        		  StockMP stockmpTMP=new StockMP();
											stockmpTMP.setMagasin(magasin);
											stockmpTMP.setMatierePremier(mp);
											stockmpTMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
											stockmpTMP.setStock(new BigDecimal(txtquantite.getText()));
											stockmpTMP.setStockMin(BigDecimal.ZERO);
											stockmpTMP.setQuantiteCommande(BigDecimal.ZERO);
											listStockMP.add(stockmpTMP);
											stockMPDAO.add(stockmpTMP);
											
											 afficher_tableMP(listStockMPAfficher);
											 mapMatierePremierTmp.put(mp.getCode(), mp);
											 mapQuantiteDetailMouvement.put(mp.getId(), new BigDecimal(txtquantite.getText()));
											 initialiserMP();
						        	 }
						        	 
			   							
			   							
			   							
			   						}else
			   						{
			   							JOptionPane.showMessageDialog(null, "MP deja existant dans la liste des MP Veuillez le Modifier SVP !!!!");
			   							
			   							return;
			   						}
			   						
			   						
			   						
			   					}
			   				
			   		}
			   		
			   		
					
				} catch (NumberFormatException e) {
					JOptionPane.showConfirmDialog(null, "la Quantité et le Prix doit etre en chiffre SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
		   		
		   		
		   	}
		   		
		   
		   });
		  btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		 
		  
		   btnSupprimer = new JButton();
		  btnSupprimer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		BigDecimal oldPrix=BigDecimal.ZERO;
				BigDecimal oldQuantite=BigDecimal.ZERO;
		  		

		  		if(tableMP.getSelectedRow()!=-1)
		  		{
		  			 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer la matiere premiere SVP  ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
							
						{
							 StockMP stockmp =stockMPDAO.findStockByMagasinMP(listStockMPAfficher.get(tableMP.getSelectedRow()).getMatierePremier().getId(), listStockMPAfficher.get(tableMP.getSelectedRow()).getMagasin().getId());
				        	 oldQuantite=stockmp.getStock().subtract(listStockMPAfficher.get(tableMP.getSelectedRow()).getStock());
				        	 if(oldQuantite.compareTo(BigDecimal.ZERO)<=0)
				        	 {
				        		 oldQuantite=BigDecimal.ZERO; 
				        		 oldPrix=stockmp.getPrixUnitaire();
				        	 }else
				        	 {
					        	 oldPrix=((stockmp.getPrixUnitaire().multiply((listStockMPAfficher.get(tableMP.getSelectedRow()).getStock().add(oldQuantite)))).subtract((listStockMPAfficher.get(tableMP.getSelectedRow()).getStock().multiply(listStockMPAfficher.get(tableMP.getSelectedRow()).getPrixUnitaire())))).divide(oldQuantite, 6, RoundingMode.HALF_UP);
 
				        	 }
				        	 stockmp.setStock(oldQuantite);
				        	 stockmp.setPrixUnitaire(oldPrix);
							stockMPDAO.edit(stockmp);
							listStockMP.set(tableMP.getSelectedRow(), stockmp);
							listStockMPAfficher.remove(tableMP.getSelectedRow());
							mapMatierePremierTmp.remove(listStockMP.get(tableMP.getSelectedRow()).getMatierePremier().getCode());
							mapQuantiteDetailMouvement.remove(listStockMP.get(tableMP.getSelectedRow()).getMatierePremier().getId());
							listDetailTransferStockMP.remove(tableMP.getSelectedRow());
							afficher_tableMP(listStockMPAfficher);
						
							JOptionPane.showMessageDialog(null, "MP Supprimer avec succée !!!!!!!");
							 initialiserMP();
						}
		  			
		  			
		  			
		  			
		  			
		  			
		  			
		  		}
		  		
		  		
		  		
		  	
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1312, 474, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1312, 444, 73, 24);
		add(btnModifier);
		
		comboFamille.addItem("");
		for(int j=0;j<listFamille.size();j++)
		{
			
			SubCategorieMp subCategorieMp=listFamille.get(j);
			
			comboFamille.addItem(subCategorieMp.getNom());
			mapFamille.put(subCategorieMp.getNom(), subCategorieMp);
			
			
			
			
		}
		
		
			
		}
	

	
	void initialiserdepot()
	{
		dateChooserMP.setCalendar(null);
		combodepot.setSelectedIndex(-1);
		combomagasin.setSelectedIndex(-1);
		comboFournisseur.setSelectedIndex(-1);
		}

	void initialiserMP()
	{
		txtcodemp.setText("");
		combomp.setSelectedItem("");
	   txtPrix.setText("");
		txtquantite.setText("");
		
	     btnAjouter.setEnabled(true);
       
		
		 combomp.removeAllItems();
		 comboSousFamille.removeAllItems();
		 comboFamille.setSelectedItem("");
		 
		
	}
	
	void InitialiseTableau()
	{
		modeleMP =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","Matière Première", "Prix Unitaire", "Quantite"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableMP.setModel(modeleMP);
		 tableMP.getColumnModel().getColumn(0).setPreferredWidth(198);
	       tableMP.getColumnModel().getColumn(1).setPreferredWidth(87);
	       tableMP.getColumnModel().getColumn(2).setPreferredWidth(94);
		
	
}
	
	
	void afficher_tableMP(List<StockMP> listStockMP)
	{
		modeleMP =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						
						"Famille","Sous Famille","Code MP","Matière Première", "Prix Unitaire", "Quantite"
					
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableMP.setModel(modeleMP);
		int i=0;
		 
		while(i<listStockMP.size())
		{	
		StockMP stockmp=listStockMP.get(i);
			
			Object []ligne={stockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom() ,stockmp.getMatierePremier().getCategorieMp().getNom(),  stockmp.getMatierePremier().getCode(),stockmp.getMatierePremier().getNom(),stockmp.getPrixUnitaire(),stockmp.getStock()};

			modeleMP.addRow(ligne);
			i++;
		}
}
	}


