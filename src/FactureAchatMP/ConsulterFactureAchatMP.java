package FactureAchatMP;

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
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureAchatMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FactureAchatMPDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TypeVenteDAOImpl;
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
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
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
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAchatMP;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FactureAchatMP;
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

import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.JToggleButton;
import javax.swing.JCheckBox;


public class ConsulterFactureAchatMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleArticle;
	private DefaultTableModel	 modelefacture;

	private JXTable  tableArticle = new JXTable();
	private JXTable table = new JXTable();
	private List<Fournisseur> listFournisseur =new ArrayList<Fournisseur>();

	private List<DetailFactureAchatMP> listDetailFactureAchatMP =new ArrayList<DetailFactureAchatMP>();
	private List<DetailFactureAchatMP> listDetailFactureAchatMPTMP =new ArrayList<DetailFactureAchatMP>();
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Depot> listparDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockMP> listStockMP =new ArrayList<StockMP>();
	private List<StockMP> listArticleStockMP =new ArrayList<StockMP>();
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	private DetailCompteClientDAO detailCompteClientdao;
	private List<FactureAchatMP> listFactureAchatMP =new ArrayList<FactureAchatMP>();
	private List<FactureAchatMP> listFactureAchatMPTmp =new ArrayList<FactureAchatMP>();
	private List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>();
	
	
	
	TransferStockMP transferStockMP ;
	private	List<DetailTransferStockMP> listDetailTransferMP= new ArrayList<DetailTransferStockMP>();
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransferStockMPdao;
	
	private Map< String, MatierePremier> mapMP = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Depot> mapparDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, TypeVente> mapTypeVente= new HashMap<>();
	private Map< String, Fournisseur> mapFournisseur= new HashMap<>();

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
	 private JComboBox combomp;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private MatierePremiereDAO matierePremierDAO;
	private FactureAchatMPDAO factureAchatMPdao;
	private FactureAchatMP factureAchatMP;
private DetailFactureAchatMPDAO detailFactureAchatMPdao;
private FournisseurDAO fournisseurdao;
	private JTextField txtcodemp;
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
	private StockMPDAO stockMPDAO;
	JCheckBox checktva = new JCheckBox("TVA");
	private JTextField txtreduction;
	 JComboBox comboFamille = new JComboBox();
	 JComboBox comboSousFamille = new JComboBox();
	 private CategorieMpDAO categorieMpDAO;
	 private SubCategorieMPDAO subCategorieMPDAO;
	 private List<SubCategorieMp> listFamille =new ArrayList<SubCategorieMp>();
		private List<CategorieMp> listSousFamille =new ArrayList<CategorieMp>();			
		private Map< String, CategorieMp> mapSousFamille= new HashMap<>();
		private Map< String, SubCategorieMp> mapFamille= new HashMap<>();
		JCheckBox checkImporterExcel = new JCheckBox("Importer Excel");
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public ConsulterFactureAchatMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1478, 1062);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
             imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
           
             
             
             
            utilisateur=AuthentificationView.utilisateur;
            detailTransferStockMPdao=new DetailTransferMPDAOImpl();
            transferStockMPDAO=new TransferStockMPDAOImpl();
            
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	factureAchatMPdao=new FactureAchatMPDAOImpl();
         	detailFactureAchatMPdao=new DetailFactureAchatMPDAOImpl();
         	
         	matierePremierDAO=new MatierePremierDAOImpl();
         	stockMPDAO=new StockMPDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         
         	fournisseurdao=new FournisseurDAOImpl();
         	
         	  categorieMpDAO=new CategorieMpDAOImpl();
              subCategorieMPDAO=new SubCategorieMPAOImpl();
              
              listFamille=subCategorieMPDAO.findAll();
              	
          } catch (Exception exp){exp.printStackTrace();}
        tableArticle.setSortable(false);
        tableArticle.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		
       		comboFamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0).toString());
       		comboSousFamille.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1).toString());
       		
       		txtcodemp.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
       		combomp.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3));
       		txtPrix.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());
       		txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5).toString());
       		txtreduction.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 6).toString());
       		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
       	
       		
     
       		btnAjouter.setEnabled(false);
       		
       		
       		 	}
       });
        
       tableArticle.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Famille","Sous Famille","Code MP","Matière Première", "Prix Unitaire", "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC"
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
				  		     	scrollPane.setBounds(10, 585, 1375, 264);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 557, 1458, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 467, 1458, 58);
				  		     	add(layeredPane);
		
		  JLabel lblCodeArticle = new JLabel("Code MP :");
		  lblCodeArticle.setBounds(483, 11, 74, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel lbllibelle = new JLabel("Libelle :");
		  lbllibelle.setBounds(658, 11, 45, 26);
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
					    			 JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
					    		
					    			
					    		}
			      				
			      				
			      		}else
			      		{
			      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code MP SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			      			
			      			
			      		}
	     				
	     				
			      		
	     				
	     				
			      		}
		     			
		     			
		     			
		     			
		     			
		     		}
		      });
		      
		      
		      
		      txtcodemp.setColumns(10);
		      txtcodemp.setBounds(555, 11, 93, 26);
		      layeredPane.add(txtcodemp);
		    
		   
		       combomp = new JComboBox();
		       combomp.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {

		     	 		
  		     	 	if(combomp.getSelectedIndex()!=-1)
  			 		{
  			 			if(!combomp.getSelectedItem().equals(""))
  				 		{
  				 			MatierePremier mp=mapMP.get(combomp.getSelectedItem());
  				 			
  				 			txtcodemp.setText(mp.getCode());
  				 			
  				 		  				 			
  				 		}else
  				 		{
  				 			txtcodemp.setText("");
  				 			
  				 		}
  				 	
  			 		}
  			 		
  			 		
  		     	 	
		       	}
		       });
		      combomp.setBounds(703, 11, 218, 27);
		      layeredPane.add(combomp);
		      AutoCompleteDecorator.decorate(combomp);
		     	
		      
		      JLabel lblQuantit = new JLabel("Quantite :");
		      lblQuantit.setBounds(931, 11, 62, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      util.Utils.copycoller(txtquantite);
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			
		     			
		     		
		      		
		      	}
		      });
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(997, 11, 101, 26);
		      layeredPane.add(txtquantite);
		      
		      JLabel lblPrix = new JLabel("Prix  :");
		      lblPrix.setBounds(1108, 11, 45, 26);
		      layeredPane.add(lblPrix);
		      
		      txtPrix = new JTextField();
		      txtPrix.setColumns(10);
		      txtPrix.setBounds(1144, 12, 87, 26);
		      layeredPane.add(txtPrix);
		      
		      JLabel lblRemise = new JLabel("Remise :");
		      lblRemise.setBounds(1241, 11, 57, 26);
		      layeredPane.add(lblRemise);
		      
		      txtreduction = new JTextField();
		      txtreduction.setColumns(10);
		      txtreduction.setBounds(1294, 11, 74, 26);
		      layeredPane.add(txtreduction);
		      
		      JLabel label_8 = new JLabel("%");
		      label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		      label_8.setBounds(1371, 11, 26, 26);
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
				
				
				else if(listDetailFactureAchatMP.size()==0)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer les MP à facturé SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
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
						FactureAchatMP factureAchatTmp=listFactureAchatMP.get(table.getSelectedRow());
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
					    factureAchatMPdao.edit(factureAchatTmp);
					    
					    transferStockMP=transferStockMPDAO.findTransferByCode(factureAchatTmp.getCodeTransfer());
					    
					    
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
					    
					    
					    
					    
					    int i=0;
					    while(i<listStockMP.size())
					    {
					    	
					    	StockMP stockMP=stockMPDAO.findById(listStockMP.get(i).getId());
					    	if(stockMP!=null)
					    	{
					    		stockMP.setStock(listStockMP.get(i).getStock());
					    		stockMP.setPrixUnitaire(listStockMP.get(i).getPrixUnitaire());
						    	stockMPDAO.edit(stockMP);
					    	}else
					    	{
					    		stockMPDAO.add(listStockMP.get(i));	
					    	}
					    	
					    	i++;
					    }
					    
					
					    // update transfer stock produit fini 
					    transferStockMP.setStatut(ETAT_TRANSFER_STOCK_ACHAT);
					    transferStockMP.setModifierPar(utilisateur);
					    transferStockMP.setDateModifier(new Date());
					    transferStockMP.setDateTransfer(dateChooserfacture.getDate());
					  //  transferStock.setListDetailTransferProduitFini(listDetailTransferProduitFini);
					    transferStockMPDAO.edit(transferStockMP);
					    
					    
					    JOptionPane.showMessageDialog(null, "Facture Modifier avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					  //  initialiserFacture();
					    initialiser();
					   
					  
					    listDetailFactureAchatMP.clear();
						InitialiseTableau();
						InitialiseTableauFacture();
						chargerListFacture();	
						
					}
				
				
			
				}
				
				
			}});
		buttonvalider.setIcon(imgValider);
		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(619, 861, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 437, 1458, 30);
		add(titledSeparator_1);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 375, 1458, 51);
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
		  AutoCompleteDecorator.decorate(comboFournisseur);
		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[]{0.0};
		gridBagLayout_1.rowHeights = new int[]{0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
		titledSeparator_2.setTitle("Information Facture");
		titledSeparator_2.setBounds(10, 342, 1311, 30);
		add(titledSeparator_2);
		
	  
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(498, 536, 107, 24);
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
					if(combomp.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez Selectionner MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(null, "Erreur de prix","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					
					}else
					{
						
						 MatierePremier mp=mapMP.get(combomp.getSelectedItem());
				         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				       
				         for(int i=0;i<listDetailFactureAchatMP.size();i++)
				         {
				        	 DetailFactureAchatMP detailFactureAchatMP =listDetailFactureAchatMP.get(i);
				        	 if(detailFactureAchatMP.getMatierePremiere().getNom().equals(mp.getNom()))
				        	 {
				        		 trouve=true;
				        	 }
				         }
				         
				         if(trouve==false)
				         {
				        	 StockMP stockMP=stockMPDAO.findStockByMagasinMP(mp.getId(), magasin.getId());
				        	 
				        		
				        	 
				        	 DetailFactureAchatMP detailFactureMP=new DetailFactureAchatMP();
				        	 if(!txtreduction.getText().equals(""))
					          {
					        	  detailFactureMP.setReduction(new BigDecimal(txtreduction.getText()));
					          }else
					          {
					        	  detailFactureMP.setReduction(BigDecimal.ZERO);  
					          }
				        	 
					          detailFactureMP.setMatierePremiere(mp);
					          detailFactureMP.setQuantite(new BigDecimal(txtquantite.getText()));
					          detailFactureMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
					           montant=new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()));
					           detailFactureMP.setMontantHT(montant);
					           if(checktva.isSelected()==true)
					           {
						           detailFactureMP.setMontantTVA(((montant).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
						           detailFactureMP.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
 
					           }else
					           {
						           detailFactureMP.setMontantTVA(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
						           detailFactureMP.setTva(BigDecimal.ZERO);
					           }
					          
						        if(!txtreduction.getText().equals(""))
						          {
						        	 if(checktva.isSelected()==true)
							           {
							        	  detailFactureMP.setMontantTTC((((montant).add((montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

							           }else
							           {
								        	  detailFactureMP.setMontantTTC((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

							           }
						          }else
						          {
						        	  if(checktva.isSelected()==true)
							           {
						        		  detailFactureMP.setMontantTTC(((montant).add((montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));
							           }else
							           {
							        	   detailFactureMP.setMontantTTC(((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP));
							           }
						        	 
						          }
					          
					           detailFactureMP.setFactureAchatMP(factureAchatMP);
					       //    detailFacture.setDateCreation(new Date());
					           
					           detailFactureMP.setUtilisateur(utilisateur);
					          detailFactureAchatMPdao.add(detailFactureMP);
					           listDetailFactureAchatMP.add(detailFactureMP);
					           
					           if(stockMP!=null)
					           {
						        	  coutAchat=new BigDecimal(txtquantite.getText()).multiply(new BigDecimal(txtPrix.getText()));
						        	  coutStock=stockMP.getStock().multiply(stockMP.getPrixUnitaire());
						        	  QuantiteTotal=new BigDecimal(txtquantite.getText()).add(stockMP.getStock());
						        	   
						        	  nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotal, 6, RoundingMode.HALF_UP);
							           stockMP.setStock(QuantiteTotal);
							           stockMP.setPrixUnitaire(nouveauprix);
							         
							           listStockMP.add(stockMP);
						           
						           }else
					           {
						        	   // creer stock avec prix (calculer prix moyen copier traitement de terminer production )
						        	   
						        	  StockMP stockMPTmp=new StockMP(); 
						        	   
						        	  stockMPTmp.setMatierePremier(mp);
						        	  stockMPTmp.setMagasin(magasin);
						        	  stockMPTmp.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
						        	  stockMPTmp.setStock(new BigDecimal(txtquantite.getText()));
						        	  stockMPTmp.setStockMin(BigDecimal.ZERO);
						        	
						        	  listStockMP.add(stockMPTmp);
						        	   
						        	  }
					           
					           
					           
					          afficher_tableDetailFactureAchat(listDetailFactureAchatMP);
					          int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO;
						        while(i<listDetailFactureAchatMP.size())
						        {
						        	 DetailFactureAchatMP detailFactureAchatMP=listDetailFactureAchatMP.get(i);
							          montanttotal=  montanttotal.add(detailFactureAchatMP.getMontantTTC());
							          sommequantite= sommequantite.add(detailFactureAchatMP.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFactureAchatMP.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFactureAchatMP.getMontantTVA());
						            
						            i++;
						        }
						       txttotalmontantTTC.setText(montanttotal+"");
						        txttotalquantite.setText(sommequantite+"");
						        txttotalmontantHT.setText(montanttotalHT+"");
					  			txttotalmontantTVA.setText(montanttotalTVA+"");
					  			
					  			
					  		/// ajout transfer stock MP (Mouvement Stock MP)
					  			DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
					  			detailTransferStockMP.setMatierePremier(mp);
					  			//detailTransferStockMP.setDateTransfer(dateChooserfacture.getDate());
					  			detailTransferStockMP.setMagasinDestination(magasin);
					  			detailTransferStockMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
					  			detailTransferStockMP.setQuantite(new BigDecimal(txtquantite.getText()));
					  			detailTransferStockMP.setTransferStockMP(transferStockMP);
					  			//detailTransferStockMP.setTypeTransfer(ETAT_TRANSFER_STOCK_ACHAT);
								detailTransferStockMPdao.add(detailTransferStockMP);
								listDetailTransferMP.add(detailTransferStockMP);
					  			
					  			
					  			
					  			
						        initialiser();
								
					      
				       
								
					       }else
					       {
					    	   JOptionPane.showMessageDialog(null, "MP déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
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
			   		if(tableArticle.getSelectedRow()!=-1)
			   		{
			   			boolean trouve=false;
						
						if(combomp.getSelectedItem().equals("") || combomp.getSelectedIndex()==-1)
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
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
							JOptionPane.showMessageDialog(null, "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						
						}else if(txtPrix.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Erreur de prix","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						
						}else
						{
							
							 MatierePremier mp=mapMP.get(combomp.getSelectedItem());
					         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
					        
					         for(int i=0;i<listDetailFactureAchatMP.size();i++)
					         {
					        	 if(i!=tableArticle.getSelectedRow())
					        	 {
					        		 
					        		 DetailFactureAchatMP detailFactureAchatMP =listDetailFactureAchatMP.get(i);
						        	 if(detailFactureAchatMP.getMatierePremiere().getNom().equals(mp.getNom()))
						        	 {
						        		 trouve=true;
						        	 }
						        	  
					        		 
					        	 }
					        	 
					        	 
					         }
					         
					         if(trouve==false)
					         {
					        	
					        	 StockMP stockMPtmp =stockMPDAO.findStockByMagasinMP(listDetailFactureAchatMP.get(tableArticle.getSelectedRow()).getMatierePremiere().getId(),listFactureAchatMP.get(table.getSelectedRow()).getMagasin().getId())  ; 
					        	
					        	 
					        	 oldQuantite=stockMPtmp.getStock().subtract(listDetailFactureAchatMP.get(tableArticle.getSelectedRow()).getQuantite());
					        	 if(oldQuantite.compareTo(BigDecimal.ZERO)<=0)
					        	 {
					        		 oldQuantite=BigDecimal.ZERO; 
					        		 oldPrix=stockMPtmp.getPrixUnitaire();
					        		
					        	 }else
					        	 {
						        	 oldPrix=((stockMPtmp.getPrixUnitaire().multiply((listDetailFactureAchatMP.get(tableArticle.getSelectedRow()).getQuantite().add(oldQuantite)))).subtract((listDetailFactureAchatMP.get(tableArticle.getSelectedRow()).getQuantite().multiply(listDetailFactureAchatMP.get(tableArticle.getSelectedRow()).getPrixUnitaire())))).divide(oldQuantite, 6, RoundingMode.HALF_UP);
	 
					        	 }
					        	
	 
					        	
					        	stockMPtmp.setStock(oldQuantite);
					        	
					        	
					        	 stockMPtmp.setPrixUnitaire(oldPrix);
					        	
					        	 stockMPDAO.edit(stockMPtmp);
					        	 
					        	 
					        	 StockMP stockMP=stockMPDAO.findStockByMagasinMP(mp.getId(), magasin.getId()) ;
					        	
					        		
					        	 DetailFactureAchatMP detailFactureMP= listDetailFactureAchatMP.get(tableArticle.getSelectedRow());
					        	 TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(factureAchatMP.getCodeTransfer());
					        	 DetailTransferStockMP detailTransferStockMP=detailTransferStockMPdao.findDetailTransferStockMPByMPByTransferMP(detailFactureMP.getMatierePremiere(), transferStockMP);
					        	 
					        	  if(!txtreduction.getText().equals(""))
						          {
					        		  detailFactureMP.setReduction(new BigDecimal(txtreduction.getText()));
						          }else
						          {
						        	  detailFactureMP.setReduction(BigDecimal.ZERO);  
						          }
					        	 
					        	  detailFactureMP.setMatierePremiere(mp);
					        	  detailFactureMP.setQuantite(new BigDecimal(txtquantite.getText()));
					        	  detailFactureMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
						           montant=new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()));
						           detailFactureMP.setMontantHT(montant);
						           if(checktva.isSelected()==true)
						           {
						        	   detailFactureMP.setMontantTVA(((montant).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
							           detailFactureMP.setTva(Constantes.TVA.multiply(new BigDecimal(100)));  
						           }else
						           {
						        	   detailFactureMP.setMontantTVA(((montant).multiply(BigDecimal.ZERO)).setScale(6, RoundingMode.HALF_UP));
							           detailFactureMP.setTva(BigDecimal.ZERO);
						           }
						           
							       
							       
							       
							       
							       
							          if(!txtreduction.getText().equals(""))
							          {
							        	  if(checktva.isSelected()==true)
								           {
								        	  detailFactureMP.setMontantTTC((((montant).add((montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  
 
								           }else
								           {
									        	  detailFactureMP.setMontantTTC((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  

								           }
							          }else
							          {
							        	  if(checktva.isSelected()==true)
								           {
							        		  detailFactureMP.setMontantTTC(((montant).add((montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));
								           }else
								           {
								        	   detailFactureMP.setMontantTTC(((montant).add((montant).multiply(BigDecimal.ZERO))).setScale(6, RoundingMode.HALF_UP));
								           }
							        	 
							          }
						          
						           detailFactureMP.setFactureAchatMP(factureAchatMP);
						       //    detailFacture.setDateCreation(new Date());
						           
						           detailFactureMP.setUtilisateur(utilisateur);	
						           
						           
						           // Modifier Transfer Stock MP
							        detailTransferStockMP.setMatierePremier(mp);
							       // detailTransferStockMP.setDateTransfer(dateChooserfacture.getDate());
							        detailTransferStockMP.setMagasinDestination(magasin);
							        detailTransferStockMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
							        detailTransferStockMP.setQuantite(new BigDecimal(txtquantite.getText()));		
							        //detailTransferStockMP.setTypeTransfer(ETAT_TRANSFER_STOCK_ACHAT);
									detailTransferStockMPdao.edit(detailTransferStockMP);
									listDetailTransferMP.set(tableArticle.getSelectedRow(), detailTransferStockMP);
						           
						           
						           
						           
						           
						           if(stockMP!=null)
						           {
						        	

							        	  coutAchat=new BigDecimal(txtquantite.getText()).multiply(new BigDecimal(txtPrix.getText()));
							        	  coutStock=stockMP.getStock().multiply(stockMP.getPrixUnitaire());
							        	  QuantiteTotal=new BigDecimal(txtquantite.getText()).add(stockMP.getStock());
							        	   
							        	  nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotal, 6, RoundingMode.HALF_UP);
								           stockMP.setStock(QuantiteTotal);
								           stockMP.setPrixUnitaire(nouveauprix);
								      
							           listStockMP.set(tableArticle.getSelectedRow(), stockMP);
						           }  else
						           {
						        	   // creer stock avec prix (calculer prix moyen copier traitement de terminer production )
						        	   
						        	   listStockMP.remove(tableArticle.getSelectedRow());
							        	  StockMP stockMPTmp=new StockMP(); 					        	   
							        	  stockMPTmp.setMatierePremier(mp);
							        	  stockMPTmp.setMagasin(magasin);
							        	  stockMPTmp.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
							        	  stockMPTmp.setStock(new BigDecimal(txtquantite.getText()));
							        	  stockMPTmp.setStockMin(BigDecimal.ZERO);
							        	 // stockMPTmp.setSousFamille(sousfamille);
							        	  listStockMP.add(stockMPTmp);
						        	   
						           }
						         
						           detailFactureAchatMPdao.edit(detailFactureMP);
						           listDetailFactureAchatMP.set(tableArticle.getSelectedRow(), detailFactureMP);
						          afficher_tableDetailFactureAchat(listDetailFactureAchatMP);
						          int i=0;
							        BigDecimal montanttotal=BigDecimal.ZERO;
							        BigDecimal sommequantite=BigDecimal.ZERO;
							        BigDecimal montanttotalHT=BigDecimal.ZERO;
							        BigDecimal montanttotalTVA=BigDecimal.ZERO;
							        while(i<listDetailFactureAchatMP.size())
							        {
							        	 DetailFactureAchatMP detailFactureAchatMP=listDetailFactureAchatMP.get(i);
								          montanttotal=  montanttotal.add(detailFactureAchatMP.getMontantTTC());
								          sommequantite= sommequantite.add(detailFactureAchatMP.getQuantite());
								          montanttotalHT=montanttotalHT.add(detailFactureAchatMP.getMontantHT());
								          montanttotalTVA=montanttotalTVA.add(detailFactureAchatMP.getMontantTVA());
							            
							            i++;
							        }
							       txttotalmontantTTC.setText(montanttotal+"");
							        txttotalquantite.setText(sommequantite+"");
							        txttotalmontantHT.setText(montanttotalHT+"");
						  			txttotalmontantTVA.setText(montanttotalTVA+"");
						  			initialiser();
									
						  			
					        	
									
						       }else
						       {
						    	   JOptionPane.showMessageDialog(null, "MP déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
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
				
		  		if(tableArticle.getSelectedRow()!=-1)
		  		{
		  			
		  			 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer MP dans la facture  ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
							
						{
							
							 StockMP stockMPtmp =listStockMP.get(tableArticle.getSelectedRow());
				        	 oldQuantite=stockMPtmp.getStock().subtract(listDetailFactureAchatMP.get(tableArticle.getSelectedRow()).getQuantite());
				        	 if(oldQuantite.compareTo(BigDecimal.ZERO)<=0)
				        	 {
				        		 oldQuantite=BigDecimal.ZERO; 
				        		 oldPrix=stockMPtmp.getPrixUnitaire();
				        	 }else
				        	 {
					        	 oldPrix=((stockMPtmp.getPrixUnitaire().multiply((listDetailFactureAchatMP.get(tableArticle.getSelectedRow()).getQuantite().add(oldQuantite)))).subtract((listDetailFactureAchatMP.get(tableArticle.getSelectedRow()).getQuantite().multiply(listDetailFactureAchatMP.get(tableArticle.getSelectedRow()).getPrixUnitaire())))).divide(oldQuantite, 6, RoundingMode.HALF_UP);
 
				        	 }
				        	 stockMPtmp.setStock(oldQuantite);
				        	 stockMPtmp.setPrixUnitaire(oldPrix);
                             DetailFactureAchatMP detailfactureachatMP=listDetailFactureAchatMP.get(tableArticle.getSelectedRow());
					  		
					  		listDetailFactureAchatMP.remove(tableArticle.getSelectedRow());
					  		detailFactureAchatMPdao.delete(detailfactureachatMP);
					  		listStockMP.set(tableArticle.getSelectedRow(),stockMPtmp);
					  		//stockMPDAO.edit(stockMPtmp);
					  		detailTransferStockMPdao.delete(listDetailTransferMP.get(tableArticle.getSelectedRow()).getId());
					  		listDetailTransferMP.remove(tableArticle.getSelectedRow());
					  		
					        afficher_tableDetailFactureAchat(listDetailFactureAchatMP);
					          int i=0;
						        BigDecimal montanttotal=BigDecimal.ZERO;
						        BigDecimal sommequantite=BigDecimal.ZERO;
						        BigDecimal montanttotalHT=BigDecimal.ZERO;
						        BigDecimal montanttotalTVA=BigDecimal.ZERO;
						        while(i<listDetailFactureAchatMP.size())
						        {
						        	 DetailFactureAchatMP detailFactureAchatMP=listDetailFactureAchatMP.get(i);
							          montanttotal=  montanttotal.add(detailFactureAchatMP.getMontantTTC());
							          sommequantite= sommequantite.add(detailFactureAchatMP.getQuantite());
							          montanttotalHT=montanttotalHT.add(detailFactureAchatMP.getMontantHT());
							          montanttotalTVA=montanttotalTVA.add(detailFactureAchatMP.getMontantTVA());
						            
						            i++;
						        }
						       txttotalmontantTTC.setText(montanttotal+"");
						       txttotalquantite.setText(sommequantite+"");
					  			txttotalmontantHT.setText(montanttotalHT+"");
					  			txttotalmontantTVA.setText(montanttotalTVA+"");
					  			JOptionPane.showMessageDialog(null, "MP supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
					  			initialiser();
						}
		  			
		  			
		  			
		  			
		  		
		  			
		  		}
		  		
		  	}
		  });
		  btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  btnSupprimer.setBounds(1395, 644, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(1116, 918, 112, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(1238, 918, 136, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(1009, 861, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(902, 861, 97, 26);
		add(lblTotalQuantite);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1395, 614, 73, 24);
		add(btnModifier);
		
		JLabel lblConslterLesFactures = new JLabel("           Consulter les Factures d'achat MP par :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(151, 11, 1234, 35);
		add(lblConslterLesFactures);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 154, 1458, 188);
		add(scrollPane_1);
		table.setSortable(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow()!=-1)
				{
					if(listFactureAchatMP.size()!=0)
					{
						listStockMP.clear();
						 factureAchatMP=listFactureAchatMP.get(table.getSelectedRow()) ;
						 
						
						
						 transferStockMP=transferStockMPDAO.findTransferByCode(factureAchatMP.getCodeTransfer());
						
						 listDetailTransferMP=detailTransferStockMPdao.findByTransferStockMP(transferStockMP.getId());
						 
						 
						txtnumfacture.setText(factureAchatMP.getNumFacture());
						dateChooserfacture.setDate(factureAchatMP.getDateFacture());
						
						combodepot.setSelectedItem(factureAchatMP.getDepot().getLibelle());
						
					
						combomagasin.setSelectedItem(factureAchatMP.getMagasin().getLibelle());
						
						
						comboFournisseur.setSelectedItem(factureAchatMP.getFournisseur().getNom());
						listDetailFactureAchatMP=detailFactureAchatMPdao.listeDetailFactureAchatByFacture(factureAchatMP.getId());
						
						 int i=0;
					        BigDecimal montanttotal=BigDecimal.ZERO;
					        BigDecimal sommequantite=BigDecimal.ZERO;
					        BigDecimal montanttotalHT=BigDecimal.ZERO;
					        BigDecimal montanttotalTVA=BigDecimal.ZERO;
					        while(i<listDetailFactureAchatMP.size())
					        {
					        	 StockMP stockMP=stockMPDAO.findStockByMagasinMP(listDetailFactureAchatMP.get(i).getMatierePremiere().getId(), factureAchatMP.getMagasin().getId());
					        	listStockMP.add(stockMP);
					        	  DetailFactureAchatMP detailFactureAchatMP=listDetailFactureAchatMP.get(i);
						          montanttotal=  montanttotal.add(detailFactureAchatMP.getMontantTTC());
						          sommequantite= sommequantite.add(detailFactureAchatMP.getQuantite());
						          montanttotalHT=montanttotalHT.add(detailFactureAchatMP.getMontantHT());
						          montanttotalTVA=montanttotalTVA.add(detailFactureAchatMP.getMontantTVA());
					            
					            i++;
					        }
					       txttotalmontantTTC.setText(montanttotal+"");
					        txttotalquantite.setText(sommequantite+"");
					        txttotalmontantHT.setText(montanttotalHT+"");
				  			txttotalmontantTVA.setText(montanttotalTVA+"");
							
				  			
				  			
						afficher_tableDetailFactureAchat(listDetailFactureAchatMP);
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
	    btnAfficher.setBounds(581, 119, 107, 24);
	    btnAfficher.setIcon(imgChercher);
	    add(btnAfficher);
	    
	    JLabel label_2 = new JLabel("Total Montant TVA :");
	    label_2.setBounds(1116, 887, 105, 26);
	    add(label_2);
	    
	    txttotalmontantTVA = new JTextField();
	    txttotalmontantTVA.setEditable(false);
	    txttotalmontantTVA.setColumns(10);
	    txttotalmontantTVA.setBounds(1238, 887, 136, 26);
	    add(txttotalmontantTVA);
	    
	    txttotalmontantHT = new JTextField();
	    txttotalmontantHT.setEditable(false);
	    txttotalmontantHT.setColumns(10);
	    txttotalmontantHT.setBounds(1238, 855, 136, 26);
	    add(txttotalmontantHT);
	    
	    JLabel label_5 = new JLabel("Total Montant HT :");
	    label_5.setBounds(1116, 855, 105, 26);
	    add(label_5);
	    
	    JLayeredPane layeredPane_2 = new JLayeredPane();
	    layeredPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    layeredPane_2.setBounds(10, 57, 1458, 51);
	    add(layeredPane_2);
	    
	    JLabel lblNumFacture = new JLabel("Num Facture :");
	    lblNumFacture.setBounds(221, 13, 97, 24);
	    layeredPane_2.add(lblNumFacture);
	    lblNumFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	    txtparnumfacture = new JTextField();
	    txtparnumfacture.setBounds(327, 13, 116, 26);
	    layeredPane_2.add(txtparnumfacture);
	    util.Utils.copycoller(txtparnumfacture);
	    txtparnumfacture.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {}
	    });
	    txtparnumfacture.setColumns(10);
	    
	    JLabel lblDateFacture = new JLabel("Date Facture :");
	    lblDateFacture.setBounds(688, 12, 97, 24);
	    layeredPane_2.add(lblDateFacture);
	    lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	    
	     pardateChooser = new JDateChooser();
	     pardateChooser.setBounds(795, 11, 151, 26);
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
	     combopardepot.setBounds(1018, 13, 151, 24);
	     layeredPane_2.add(combopardepot);
	     combopardepot.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {}
	     });
	     combopardepot.setSelectedIndex(-1);
	     
	     JLabel lblDepot = new JLabel("Depot  :");
	     lblDepot.setBounds(956, 12, 97, 24);
	     layeredPane_2.add(lblDepot);
	     lblDepot.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	     
	     JLabel label_7 = new JLabel("Fournisseur :");
	     label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
	     label_7.setBounds(453, 12, 79, 24);
	     layeredPane_2.add(label_7);
	     
	      comboparfournisseur = new JComboBox();
	     comboparfournisseur.setSelectedIndex(-1);
	     comboparfournisseur.setBounds(531, 13, 149, 24);
	     layeredPane_2.add(comboparfournisseur);
	     AutoCompleteDecorator.decorate(comboparfournisseur);
  listMP=matierePremierDAO.findAll();
  combomp.addItem("");
  
   checktva = new JCheckBox("TVA");
  checktva.setBounds(1395, 13, 57, 23);
  layeredPane.add(checktva);
  
  JLabel label_9 = new JLabel("Famille :");
  label_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
  label_9.setBounds(0, 11, 56, 24);
  layeredPane.add(label_9);
  
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
  comboFamille.setBounds(50, 12, 140, 24);
  layeredPane.add(comboFamille);
  
  JLabel label_10 = new JLabel("Sous Famille :");
  label_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
  label_10.setBounds(200, 11, 86, 24);
  layeredPane.add(label_10);
  
   comboSousFamille = new JComboBox();
   comboSousFamille.addItemListener(new ItemListener() {
   	public void itemStateChanged(ItemEvent e) {
   		
   		

   		

   		

  		 
  		 listMP.clear(); 
  		
  		
  		 combomp.removeAllItems();
  		combomp.addItem("");
  		
  		CategorieMp CategorieMp=mapSousFamille.get(comboSousFamille.getSelectedItem());
  		
  		if(CategorieMp!=null)
  		{
  			
  		listMP=matierePremierDAO.listeMatierePremierByidcategorie(CategorieMp.getId())	;
  		
  		
  		
  		for(int j=0;j<listMP.size();j++)
  		{
  			
  		MatierePremier mp=	listMP.get(j);
  		
  		
  		combomp.addItem(mp.getNom());
  			
  		mapMP.put(mp.getNom(), mp)	;
  		mapCodeMP.put(mp.getCode(), mp)	;	
  			
  		}
  			
  			
  			
  			
  		}
  		 
  		 
  	
   		
   		
   	}
   });
  comboSousFamille.setBounds(274, 12, 196, 24);
  layeredPane.add(comboSousFamille);
  for(int j=0;j<listMP.size();j++)
  {
	 MatierePremier mp= listMP.get(j);
	 combomp.addItem(mp.getNom());
	 mapMP.put(mp.getNom(), mp);
	 mapCodeMP.put(mp.getCode(), mp);
	  
  }
 	
        
        listFournisseur=fournisseurdao.findAll();
        int i=0;
        comboparfournisseur.addItem("");
        
          checkImporterExcel = new JCheckBox("Importer Excel");
        checkImporterExcel.setBounds(1208, 15, 116, 23);
        layeredPane_2.add(checkImporterExcel);
        while(i<listFournisseur.size())
        {
       	Fournisseur fournisseur= listFournisseur.get(i);
       	comboFournisseur.addItem(fournisseur.getNom());
       	comboparfournisseur.addItem(fournisseur.getNom());
       mapFournisseur.put(fournisseur.getNom(), fournisseur);
       i++;
       	 
        }
        comboFournisseur.setSelectedIndex(-1);
        
      
        
       
       
	     
	     
	      btninitialiser = new JButton("Initialiser");
	     btninitialiser.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     	initialiser();
	     	}
	     });
	     btninitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
	     btninitialiser.setBounds(625, 536, 106, 23);
	     add(btninitialiser);
	     
	     JButton button = new JButton("Initialiser");
	     button.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent arg0) {
	     		
	     		txtparnumfacture.setText("");
	     		comboparfournisseur.setSelectedItem("");
	     		pardateChooser.setCalendar(null);
	     		combopardepot.setSelectedItem("");
	     		checkImporterExcel.setSelected(false);
	     	
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
	    
	    
	    
		comboFamille.addItem("");
		for(int j=0;j<listFamille.size();j++)
		{
			
			SubCategorieMp subCategorieMp=listFamille.get(j);
			
			comboFamille.addItem(subCategorieMp.getNom());
			mapFamille.put(subCategorieMp.getNom(), subCategorieMp);
			
			
			
			
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
	listFactureAchatMP.clear();
	
	if(checkImporterExcel.isSelected()==true)
	{
		listFactureAchatMP=factureAchatMPdao.findByNumFcatureFournisseurDateFactureDepotEtatRegle(txtparnumfacture.getText(),fournisseur, pardateChooser.getDate(), depot,Constantes.ETAT_IMPORTATION_EXCEL);

	}else
	{
		listFactureAchatMP=factureAchatMPdao.findByNumFcatureFournisseurDateFactureDepotEtatRegle(txtparnumfacture.getText(),fournisseur, pardateChooser.getDate(), depot,Constantes.ETAT_NON_REGLE);

	}
		
					//initialiserFacture();
		    		initialiser();
		    		InitialiseTableau();
		    		InitialiseTableauFacture();
				
					afficher_tableFactureAchat(listFactureAchatMP);
				
			
	
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
		txtcodemp.setText("");
		combomp.setSelectedIndex(-1);
	   txtPrix.setText("");
		txtquantite.setText("");
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	     checktva.setSelected(false);
	     
	     
	     combomp.removeAllItems();
		 comboSousFamille.removeAllItems();
		 comboFamille.setSelectedItem("");  
	     
	   
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
						"Code MP","Matière Première", "Prix Unitaire", "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC"
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
	
	
	
	void afficher_tableDetailFactureAchat(List<DetailFactureAchatMP> listDetailFactureMP)
	{
		modeleArticle =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Famille","Sous Famille","Code MP","Matière Première", "Prix Unitaire", "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false,false,false,false,false,false,false,false,false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		tableArticle.setModel(modeleArticle);
		int i=0;
		 
		while(i<listDetailFactureMP.size())
		{	
		DetailFactureAchatMP detailfactureAchatMP=listDetailFactureMP.get(i);
			
			Object []ligne={detailfactureAchatMP.getMatierePremiere().getCategorieMp().getSubCategorieMp().getNom(),detailfactureAchatMP.getMatierePremiere().getCategorieMp().getNom(), detailfactureAchatMP.getMatierePremiere().getCode(),detailfactureAchatMP.getMatierePremiere().getNom(),detailfactureAchatMP.getPrixUnitaire(),detailfactureAchatMP.getQuantite(),detailfactureAchatMP.getReduction(), detailfactureAchatMP.getMontantHT(),detailfactureAchatMP.getMontantTVA(), detailfactureAchatMP.getMontantTTC()};

			modeleArticle.addRow(ligne);
			i++;
		}
}
	
	
	void afficher_tableFactureAchat(List<FactureAchatMP> listFactureMP)
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
		 
		while(i<listFactureMP.size())
		{	
		FactureAchatMP factureAchatMP=listFactureMP.get(i);
		
		Date datefacture=factureAchatMP.getDateFacture();
			
			Object []ligne={factureAchatMP.getNumFacture(),datefacture,factureAchatMP.getEtat(),factureAchatMP.getFournisseur().getNom(),factureAchatMP.getDepot().getLibelle(),factureAchatMP.getMagasin().getLibelle(),factureAchatMP.getMontantTTC()};

			modelefacture.addRow(ligne);
			i++;
		}
}
	}


