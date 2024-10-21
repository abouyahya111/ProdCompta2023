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
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureAvoirMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FactureAchatMPDAOImpl;
import dao.daoImplManager.FactureAvoirMPDAOImpl;
import dao.daoImplManager.FactureAvoirPFDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
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
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFactureAvoirMPDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FactureAvoirMPDAO;
import dao.daoManager.FactureAvoirPFDAO;
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
import dao.daoManager.TransferStockMPDAO;
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
import dao.entity.DetailFactureAchatMP;
import dao.entity.DetailFactureAvoirMP;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FactureAchatMP;
import dao.entity.FactureAvoirMP;
import dao.entity.FactureAvoirPF;
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


public class AjoutFactureAvoirMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleChargefixe;

	private JXTable  tableArticle = new JXTable();
	private List<Fournisseur> listFournisseur =new ArrayList<Fournisseur>();
	private List<DetailFactureAvoirMP> listDetailFactureAvoirMP =new ArrayList<DetailFactureAvoirMP>();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<StockMP> listStockMP =new ArrayList<StockMP>();
	private List<MatierePremier> listMP =new ArrayList<MatierePremier>();
	
	
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	
	private List<TypeVente> listTypeVente =new ArrayList<TypeVente>();
	
	private TransferStockMP transferStockMP = new TransferStockMP();
	private	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	private TransferStockMPDAO transferStockMPDAO;
	
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMatierePremiere = new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	
	
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
	
	 private JComboBox comboMP;
	private ChargesDAO chargedao=new ChargesDAOImpl();
	private ChargeProductionDAO chargeproductiondao;
	private MatierePremiereDAO matierePremiereDAO;
	private FactureAvoirMPDAO factureAvoirMPdao;
	private FactureAvoirMP factureAvoirMP=new FactureAvoirMP();
	private DetailCompteClientDAO detailCompteClientdao;
private DetailFactureAvoirMPDAO detailFactureAvoirMPdao;
private DetailTransferMPDAO detailTransferMPdao;

private StockMPDAO stockMPDAO;
	private JTextField txtcodeMP;
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
	private CompteClientDAO compteclientdao;
	private FournisseurDAO fournisseurdao;
	private static SequenceurDAO sequenceurDAO=ProdLauncher.sequenceurDAO;
	
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
	private FactureAchatDAO factureAchatdao;
	private FactureAchatMPDAO factureAchatMPdao;
	private FactureAvoirPFDAO factureAvoirPFdao;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AjoutFactureAvoirMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 887);
      
	
        try{ 
        	
        	
        
             imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
        	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
             imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
             imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
           
            utilisateur=AuthentificationView.utilisateur;
        	transferStockMPDAO=new TransferStockMPDAOImpl();
         	depotdao=new DepotDAOImpl();
         	typeventedao=new TypeVenteDAOImpl();
         	factureAvoirMPdao=new FactureAvoirMPDAOImpl();
         	stockMPDAO=new StockMPDAOImpl();
         	parametredao=new ParametreDAOImpl();
         	detailFactureAvoirMPdao=new DetailFactureAvoirMPDAOImpl();
         	detailTransferMPdao=new DetailTransferMPDAOImpl();
         	matierePremiereDAO=new MatierePremierDAOImpl();
         	detailCompteClientdao=new DetailCompteClientDAOImpl();
         	compteclientdao=new CompteClientDAOImpl();
         	fournisseurdao=new FournisseurDAOImpl();
         listMP=matierePremiereDAO.findAll();
         
         factureAchatdao=new FactureAchatDAOImpl();
         factureAchatMPdao=new FactureAchatMPDAOImpl();
         factureAvoirPFdao=new FactureAvoirPFDAOImpl();
         	
          } catch (Exception exp){exp.printStackTrace();}
        tableArticle.setSortable(false);
        tableArticle.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		txtcodeMP.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0).toString());
       		comboMP.setSelectedItem(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
       		txtPrix.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2).toString());
       		txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString());
       		txtreduction.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4).toString());
       		//combotypevente.setSelectedItem((tableArticle.getValueAt(tableArticle.getSelectedRow(), 3).toString()));
       	
       		
     
       		btnAjouter.setEnabled(false);
       		
       		
       		 	}
       });
        
       tableArticle.setModel(new DefaultTableModel(
       	new Object[][] {
       	},
       	new String[] {
       			"Code MP","Matière Première", "Prix Unitaire", "Quantite","Reduction","Montant HT", "Montant TVA", "Montant TTC"
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
				  		     	scrollPane.setBounds(10, 364, 1117, 232);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Articles");
				  		     	titledSeparator.setBounds(10, 323, 1117, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 222, 1117, 55);
				  		     	add(layeredPane);
		
		  JLabel lblCodeArticle = new JLabel("Code MP :");
		  lblCodeArticle.setBounds(8, 11, 82, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JLabel lbllibelle = new JLabel("Libelle :");
		  lbllibelle.setBounds(172, 10, 57, 26);
		  layeredPane.add(lbllibelle);
		  lbllibelle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		      
		      txtcodeMP = new JTextField();
		      util.Utils.copycoller(txtcodeMP);
		      txtcodeMP.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {
	     			if(e.getKeyCode()==e.VK_ENTER)
			      		{
	     				
			      			if(!txtcodeMP.getText().equals(""))
			      			{
			      				
			      				MatierePremier mp=mapCodeMatierePremiere.get(txtcodeMP.getText().toUpperCase());
					    		
					    		if(mp!=null)
					    		{	
					    			comboMP.setSelectedItem(mp.getNom());
					    			
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
		      
		      
		      
		      txtcodeMP.setColumns(10);
		      txtcodeMP.setBounds(76, 11, 86, 26);
		      layeredPane.add(txtcodeMP);
		    
		   
		       comboMP = new JComboBox();
		       comboMP.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {

		     	 		
  		     	 	if(comboMP.getSelectedIndex()!=-1)
  			 		{
  			 			if(!comboMP.getSelectedItem().equals(""))
  				 		{
  				 			MatierePremier mp=mapMatierePremiere.get(comboMP.getSelectedItem());
  				 			txtcodeMP.setText(mp.getCode());
  				 		
  				 			
  				 		
  				 		  				 			
  				 		}else
  				 		{
  				 			txtcodeMP.setText("");
  				 			
  				 		}
  				 	
  			 		}
  			 		
  			 		
  		     	 		
  		     	 	
		       	}
		       });
		      comboMP.setBounds(223, 10, 300, 27);
		      layeredPane.add(comboMP);
		      AutoCompleteDecorator.decorate(comboMP);
		     
		     	
		      
		      JLabel lblQuantit = new JLabel("Quantite :");
		      lblQuantit.setBounds(533, 10, 72, 26);
		      layeredPane.add(lblQuantit);
		      
		      txtquantite = new JTextField();
		      util.Utils.copycoller(txtquantite);
		      txtquantite.addKeyListener(new KeyAdapter() {
		      	@Override
		      	public void keyPressed(KeyEvent e) {

	     			
		     			
		     		
		      		
		      	}
		      });
		      txtquantite.setColumns(10);
		      txtquantite.setBounds(591, 11, 112, 26);
		      layeredPane.add(txtquantite);
		      
		      JLabel lblPrix = new JLabel("Prix  :");
		      lblPrix.setBounds(713, 11, 45, 26);
		      layeredPane.add(lblPrix);
		      
		      txtPrix = new JTextField();
		      txtPrix.setColumns(10);
		      txtPrix.setBounds(753, 11, 140, 26);
		      layeredPane.add(txtPrix);
		      
		      JLabel lblReduction = new JLabel("Remise :");
		      lblReduction.setBounds(903, 11, 57, 26);
		      layeredPane.add(lblReduction);
		      
		      txtreduction = new JTextField();
		      txtreduction.setColumns(10);
		      txtreduction.setBounds(970, 11, 112, 26);
		      layeredPane.add(txtreduction);
		      
		      JLabel label_2 = new JLabel("%");
		      label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		      label_2.setBounds(1092, 11, 26, 26);
		      layeredPane.add(label_2);
		      comboMP.addItem("");
		      int p=0;
		      while(p<listMP.size())
		      {
		    	  
		    	  MatierePremier mp=listMP.get(p);
		    	  comboMP.addItem(mp.getNom());
		    	  mapMatierePremiere.put(mp.getNom(), mp);
		    	  mapCodeMatierePremiere.put(mp.getCode(), mp);
		    	  p++;
		      }
		 
		
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
					
				
					
				}else if(listDetailFactureAvoirMP.size()==0)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer les MP à facturé SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}else
				{
					
					
					
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
				Magasin magasin =mapMagasin.get(combomagasin.getSelectedItem());
				
				// chercher le num de facture doit etre unique 
				/*FactureAchat factureAchatTmp=factureAchatdao.findByNumFacture(txtnumfacture.getText());
				FactureAchatMP factureAchatMP=factureAchatMPdao.findByNumFacture(txtnumfacture.getText());
				FactureAvoirPF factureAvoirPF=factureAvoirPFdao.findByNumFacture(txtnumfacture.getText());*/
				FactureAvoirMP factureAvoirMPTMP=factureAvoirMPdao.findByNumFactureByDepot(txtnumfacture.getText(),depot);
			if(factureAvoirMPTMP==null)
			{
				Fournisseur fournisseur=mapFournisseur.get(comboFournisseur.getSelectedItem());
				 String codeTransfert=Utils.genererCodeTransfer(depot.getCode(),ETAT_TRANSFER_STOCK_ACHAT);
				
			factureAvoirMP.setNumFacture(txtnumfacture.getText());
			factureAvoirMP.setFournisseur(fournisseur);
			factureAvoirMP.setDepot(depot);
			factureAvoirMP.setMagasin(magasin);
			factureAvoirMP.setDateFacture(dateChooserfacture.getDate());
			factureAvoirMP.setEtat(Constantes.ETAT_NON_REGLE);
			factureAvoirMP.setType(Constantes.TYPE_BON_LIVRAISON);
			factureAvoirMP.setMontantHT((new BigDecimal(txttotalmontantHT.getText())).setScale(6, RoundingMode.HALF_UP));
			factureAvoirMP.setMontantTVA((new BigDecimal(txttotalmontantTVA.getText())).setScale(6, RoundingMode.HALF_UP));
			factureAvoirMP.setMontantTTC((new BigDecimal(txttotalmontantTTC.getText())).setScale(6, RoundingMode.HALF_UP));	
			//factureAvoirMP.setDetailFactureAvoirMP(listDetailFactureAvoirMP);
			factureAvoirMP.setCodeTransfer(codeTransfert);
			factureAvoirMP.setCreerPar(utilisateur);
			factureAvoirMP.setDateCreer(new Date());
		    factureAvoirMPdao.add(factureAvoirMP);
		    for(int i=0;i<listDetailFactureAvoirMP.size();i++)
			 {
				 detailFactureAvoirMPdao.add(listDetailFactureAvoirMP.get(i));
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
		    solde=clientac.getCompteClient().getSolde().add(new BigDecimal(txttotalmontantTTC.getText()));
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
			    	stockMPDAO.edit(stockMP);
		    	}else
		    	{
		    		stockMPDAO.add(listStockMP.get(i));	
		    	}
		    	
		    	i++;
		    }
		    
		/*    Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE);
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
			   
			transferStockMP.setCodeTransfer(codeTransfert);
			transferStockMP.setCreerPar(utilisateur);
			transferStockMP.setDate(new Date());
			transferStockMP.setDateTransfer(dateChooserfacture.getDate());
			//transferStockMP.setListDetailTransferMP(listDetailTransferStockMP);
			transferStockMP.setStatut(ETAT_TRANSFER_STOCK_AVOIR);
			transferStockMP.setDepot(depot);
			transferStockMPDAO.add(transferStockMP);
			for(int j=0;j<listDetailTransferStockMP.size();j++)
			{
				detailTransferMPdao.add(listDetailTransferStockMP.get(j));
			}
		    JOptionPane.showMessageDialog(null, "Facture Ajouté avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
		    initialiserFacture();
		    initialiser();
		  factureAvoirMP=new FactureAvoirMP();
		  transferStockMP=new TransferStockMP();
		  listDetailTransferStockMP.clear();
		    listDetailFactureAvoirMP.clear();
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
		buttonvalider.setBounds(475, 607, 112, 24);
		add(buttonvalider);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Informations Articles");
		titledSeparator_1.setBounds(10, 181, 1117, 30);
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
		   listFournisseur=fournisseurdao.findAll();
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
		btnAjouter.setBounds(480, 288, 107, 24);
		add(btnAjouter);
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					

					boolean trouve=false;
					//BigDecimal oldprix=BigDecimal.ZERO;
					BigDecimal odlQuantite=BigDecimal.ZERO;
					BigDecimal Montant=BigDecimal.ZERO;
					
					
					 if(dateChooserfacture.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(comboMP.getSelectedItem().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Veuillez Selectionner MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
						return;
					}else if(txtcodeMP.getText().equals(""))
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
						
						 MatierePremier mp=mapMatierePremiere.get(comboMP.getSelectedItem());
				         Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				       
				         
				         for(int i=0;i<listDetailFactureAvoirMP.size();i++)
				         {
				        	 DetailFactureAvoirMP detailFactureAvoirMP =listDetailFactureAvoirMP.get(i);
				        	 if(detailFactureAvoirMP.getMatierePremiere().getNom().equals(mp.getNom()))
				        	 {
				        		 trouve=true;
				        	 }
				         }
				         
				         if(trouve==false)
				         {
				       
				        	 
				        	 StockMP stockMP=stockMPDAO.findStockByMagasinMP(mp.getId(), magasin.getId());
				        			       	 
					        	 	 DetailFactureAvoirMP detailFactureMP=new DetailFactureAvoirMP();
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
							           Montant=new BigDecimal(txtPrix.getText()).multiply(new BigDecimal(txtquantite.getText()));
							           detailFactureMP.setMontantHT(Montant.setScale(6, RoundingMode.HALF_UP));
							           detailFactureMP.setMontantTVA(((Montant).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
							           detailFactureMP.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
							           
							          if(!txtreduction.getText().equals(""))
							          {
							        	  detailFactureMP.setMontantTTC((((Montant).add((Montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((Montant).add((Montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  
							          }else
							          {
							        	  detailFactureMP.setMontantTTC(((Montant).add((Montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));
							          }
							         
							       
							        
							          
							          detailFactureMP.setFactureAvoirMP(factureAvoirMP);
							       //    detailFacture.setDateCreation(new Date());
							           
							          detailFactureMP.setUtilisateur(utilisateur);
							          
							           listDetailFactureAvoirMP.add(detailFactureMP);
							           if(stockMP!=null)
							           {
							        	   if(stockMP.getStock().compareTo(new BigDecimal(txtquantite.getText()))>=0)
							        	   {
							        		   odlQuantite=stockMP.getStock().subtract(new BigDecimal(txtquantite.getText()));
							        		   if(odlQuantite.compareTo(BigDecimal.ZERO)<=0)
									        	 {
							        			   odlQuantite=BigDecimal.ZERO; 
									        		// oldprix=stockMP.getPrixUnitaire();
									        		
									        	 }else
									        	 {
									        		  // oldprix=((stockMP.getPrixUnitaire().multiply(odlQuantite.add(new BigDecimal(txtquantite.getText())))).subtract(new BigDecimal(txtquantite.getText()).multiply(new BigDecimal(txtPrix.getText())))).divide(odlQuantite, RoundingMode.HALF_UP);
 
									        	 }

							        		
							        		   stockMP.setStock(odlQuantite);
							        		 //  stockMP.setPrixUnitaire(oldprix);
									           listStockMP.add(stockMP);
							        		   
							        		   
							        	   }else
							        	   {
							        		   JOptionPane.showMessageDialog(null, "la Quantité MP est supérieur de celle de stock !!!!!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
							        		   return;
							        	   }
							        	 
							           
							           }else
							           {
							        	  
							        	   
							        	   JOptionPane.showMessageDialog(null, "MP introuvable dans le stock !!!!!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
						        		   return;
							        	   
							        	  }
							        	   
							        
							           
							          afficher_tableDetailFactureAvoir(listDetailFactureAvoirMP);
							          int i=0;
								        BigDecimal montanttotal=BigDecimal.ZERO;
								        BigDecimal montanttotalHT=BigDecimal.ZERO;
								        BigDecimal montanttotalTVA=BigDecimal.ZERO;
								        BigDecimal sommequantite=BigDecimal.ZERO;
								        while(i<listDetailFactureAvoirMP.size())
								        {
								          DetailFactureAvoirMP detailFactureAvoirMP=listDetailFactureAvoirMP.get(i);
								          montanttotal=  montanttotal.add(detailFactureAvoirMP.getMontantTTC());
								          sommequantite= sommequantite.add(detailFactureAvoirMP.getQuantite());
								          montanttotalHT=montanttotalHT.add(detailFactureAvoirMP.getMontantHT());
								          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirMP.getMontantTVA());
								            
								            i++;
								        }
								       txttotalmontantTTC.setText(montanttotal+"");
								        txttotalquantite.setText(sommequantite+"");
								        txttotalmontantHT.setText(montanttotalHT+"");
							  			txttotalmontantTVA.setText(montanttotalTVA+"");
							  			
							  		/// ajout transfer stock PF (Mouvement Stock PF)
							  			DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
							  			detailTransferStockMP.setMatierePremier(mp);
							  			//detailTransferStockMP.set(dateChooserfacture.getDate());
							  			detailTransferStockMP.setMagasinDestination(magasin);
							  			detailTransferStockMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
							  			detailTransferStockMP.setQuantite(new BigDecimal(txtquantite.getText()));
							  			detailTransferStockMP.setTransferStockMP(transferStockMP);
							  			//detailTransferStockMP.set(ETAT_TRANSFER_STOCK_ACHAT);
							  			//detailTransferMPdao.add(detailTransferStockMP);
										listDetailTransferStockMP.add(detailTransferStockMP);
							  			
							  			
							  			
								        initialiser(); 
					        	
					       }else
					       {
					    	   JOptionPane.showMessageDialog(null, "MP déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
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
		  btnInitialiser.setBounds(611, 287, 106, 23);
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
		   			

			   		//BigDecimal nouveauprix=BigDecimal.ZERO;
					BigDecimal coutAchat=BigDecimal.ZERO;
					BigDecimal coutStock=BigDecimal.ZERO;
					BigDecimal QuantiteTotal=BigDecimal.ZERO;
			   		BigDecimal stocktmp=BigDecimal.ZERO;
			   		BigDecimal montant=BigDecimal.ZERO;
			   		BigDecimal marge=BigDecimal.ZERO;
			   		//BigDecimal oldprix=BigDecimal.ZERO;
					BigDecimal odlQuantite=BigDecimal.ZERO;
					
					
			   		if(tableArticle.getSelectedRow()!=-1)
			   		{
			   			boolean trouve=false;
			   		 if(dateChooserfacture.getDate()==null)
						{

							JOptionPane.showMessageDialog(null, "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
							return;	
						}else if(comboMP.getSelectedItem().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner MP SVP","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}else if(txtcodeMP.getText().equals(""))
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
							
							 MatierePremier mp=mapMatierePremiere.get(comboMP.getSelectedItem());
					      Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
					     
					      
					         for(int i=0;i<listDetailFactureAvoirMP.size();i++)
					         {
					        	 if(i!=tableArticle.getSelectedRow())
					        	 {
					        		 
					        		 DetailFactureAvoirMP detailFactureAvoirMP =listDetailFactureAvoirMP.get(i);
						        	 if(detailFactureAvoirMP.getMatierePremiere().getNom().equals(mp.getNom()))
						        	 {
						        		 trouve=true;
						        	 }
						        	  
					        		 
					        	 }
					        	 
					        	 
					         }
					         
					         if(trouve==false)
					         {
					        	 
					        	 
					        	 StockMP stockMPtmp =listStockMP.get(tableArticle.getSelectedRow());
					        	
					        	// coutAchat=listDetailFactureAvoirMP.get(tableArticle.getSelectedRow()).getQuantite().multiply(listDetailFactureAvoirMP.get(tableArticle.getSelectedRow()).getPrixUnitaire());
					        	//  coutStock=stockMPtmp.getStock().multiply(stockMPtmp.getPrixUnitaire());
					        	  QuantiteTotal=listDetailFactureAvoirMP.get(tableArticle.getSelectedRow()).getQuantite().add(stockMPtmp.getStock());
					        	   
					        	//  nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotal, 6, RoundingMode.HALF_UP);
					        	  stockMPtmp.setStock(QuantiteTotal);
					        	 // stockMPtmp.setPrixUnitaire(nouveauprix);
					        	 
					        	 
					        	 
					        	 StockMP stockMP=stockMPDAO.findStockByMagasinMP(mp.getId(), magasin.getId());
					        	 
					            	
						        		 
						        		 
						        		 
						        		 
							        	 DetailFactureAvoirMP detailFacture= listDetailFactureAvoirMP.get(tableArticle.getSelectedRow());
							        	 
							        	 if(!txtreduction.getText().equals(""))
								          {
								        	  detailFacture.setReduction(new BigDecimal(txtreduction.getText()));
								          }else
								          {
								        	  detailFacture.setReduction(BigDecimal.ZERO);  
								          }
								          detailFacture.setMatierePremiere(mp);
								          detailFacture.setQuantite(new BigDecimal(txtquantite.getText()));
								           detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
								           montant=new BigDecimal(txtquantite.getText()).multiply(new BigDecimal(txtPrix.getText()));
								           
								           detailFacture.setMontantHT(montant.setScale(6, RoundingMode.HALF_UP));
									       detailFacture.setMontantTVA(((montant).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
									     
									       if(!txtreduction.getText().equals(""))
									          {
									        	  detailFacture.setMontantTTC((((montant).add((montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP)).multiply((new BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6, RoundingMode.HALF_UP))));  
									          }else
									          {
									        	  detailFacture.setMontantTTC(((montant).add((montant).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));
									          }
									       
								          
								           detailFacture.setFactureAvoirMP(factureAvoirMP);
								       //    detailFacture.setDateCreation(new Date());
								           
								           detailFacture.setUtilisateur(utilisateur);
								           if(stockMP!=null)
								           {
								        	  
								        	   if(stockMP.getStock().compareTo(new BigDecimal(txtquantite.getText()))>=0)
								        	   {
								        		   odlQuantite=stockMP.getStock().subtract(new BigDecimal(txtquantite.getText()));
								        		   if(odlQuantite.compareTo(BigDecimal.ZERO)<=0)
										        	 {
								        			   odlQuantite=BigDecimal.ZERO; 
										        		// oldprix=stockMP.getPrixUnitaire();
										        		
										        	 }else
										        	 {
										        		 //  oldprix=((stockMP.getPrixUnitaire().multiply(odlQuantite.add(new BigDecimal(txtquantite.getText())))).subtract(new BigDecimal(txtquantite.getText()).multiply(new BigDecimal(txtPrix.getText())))).divide(odlQuantite, RoundingMode.HALF_UP);
 
										        	 }
								        		 
								        		   stockMP.setStock(odlQuantite);
								        		  // stockMP.setPrixUnitaire(oldprix);
										           listStockMP.set(tableArticle.getSelectedRow(), stockMP);
								        		   
								        		   
								        	   }else
								        	   {
								        		   JOptionPane.showMessageDialog(null, "la Quantité MP est supérieur de celle de stock !!!!!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
								        		   return;
								        	   }
								        	 
									          
								           }  else
								           {
								        	   
								        	   JOptionPane.showMessageDialog(null, "MP introuvable dans le stock !!!!!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
							        		   return;
								           
								           }
								           
								           // Modifier transfer Stock PF
								  			
								  			DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(tableArticle.getSelectedRow());
											
								  			detailTransferStockMP.setMatierePremier(mp);
								  			//detailTransferStockMP.setDateTransfer(dateChooserfacture.getDate());
								  			detailTransferStockMP.setMagasinDestination(magasin);
								  			detailTransferStockMP.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
								  			detailTransferStockMP.setQuantite(new BigDecimal(txtquantite.getText()));		
								  			//detailTransferStockMP.setTypeTransfer(ETAT_TRANSFER_STOCK_ACHAT);
											listDetailTransferStockMP.set(tableArticle.getSelectedRow(), detailTransferStockMP);
								          // detailTransferMPdao.edit(detailTransferStockMP);
								           
								         
								          
								           listDetailFactureAvoirMP.set(tableArticle.getSelectedRow(), detailFacture);
								          afficher_tableDetailFactureAvoir(listDetailFactureAvoirMP);
								          int i=0;
									        BigDecimal montanttotal=BigDecimal.ZERO;
									        BigDecimal sommequantite=BigDecimal.ZERO;
									        BigDecimal montanttotalHT=BigDecimal.ZERO;
									        BigDecimal montanttotalTVA=BigDecimal.ZERO;
									        while(i<listDetailFactureAvoirMP.size())
									        {
									        	 DetailFactureAvoirMP detailFactureAvoirmp=listDetailFactureAvoirMP.get(i);
										          montanttotal=  montanttotal.add(detailFactureAvoirmp.getMontantTTC());
										          sommequantite= sommequantite.add(detailFactureAvoirmp.getQuantite());
										          montanttotalHT=montanttotalHT.add(detailFactureAvoirmp.getMontantHT());
										          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirmp.getMontantTVA());
									            
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
					JOptionPane.showMessageDialog(null, "La Quantité , le Prix et la Remise doit etre en chiffre SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
		   		
		   	}
		   		
		   
		   });
		  btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		  
		   btnSupprimer = new JButton();
		  btnSupprimer.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		BigDecimal stocktmp=BigDecimal.ZERO;
		  		if(tableArticle.getSelectedRow()!=-1)
		  		{
		  			 int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment supprimer MP dans la facture  ?", 
								"Satisfaction", JOptionPane.YES_NO_OPTION);
						 
						if(reponse == JOptionPane.YES_OPTION )
							
							
						{
							/*
							 StockPF stockpftmp =listStockPF.get(tableArticle.getSelectedRow());
				        	 stocktmp=stockpftmp.getStock().subtract(listDetailFactureAchat.get(tableArticle.getSelectedRow()).getQuantite());
				        	 stockpftmp.setStock(stocktmp);
				        	 */
				  		listDetailFactureAvoirMP.remove(tableArticle.getSelectedRow());
				  		listStockMP.remove(tableArticle.getSelectedRow());
				  		//DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(tableArticle.getSelectedRow());
				  		//detailTransferMPdao.delete(detailTransferStockMP.getId());
				  		listDetailTransferStockMP.remove(tableArticle.getSelectedRow());
				  		
				        afficher_tableDetailFactureAvoir(listDetailFactureAvoirMP);
				          int i=0;
					        BigDecimal montanttotal=BigDecimal.ZERO;
					        BigDecimal sommequantite=BigDecimal.ZERO;
					        BigDecimal montanttotalHT=BigDecimal.ZERO;
					        BigDecimal montanttotalTVA=BigDecimal.ZERO;
					        while(i<listDetailFactureAvoirMP.size())
					        {
					        	 DetailFactureAvoirMP detailFactureAvoirMP=listDetailFactureAvoirMP.get(i);
						          montanttotal=  montanttotal.add(detailFactureAvoirMP.getMontantTTC());
						          sommequantite= sommequantite.add(detailFactureAvoirMP.getQuantite());
						          montanttotalHT=montanttotalHT.add(detailFactureAvoirMP.getMontantHT());
						          montanttotalTVA=montanttotalTVA.add(detailFactureAvoirMP.getMontantTVA());
					            
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
		  btnSupprimer.setBounds(1137, 425, 73, 24);
		  btnSupprimer.setIcon(imgSupprimer);
		  add(btnSupprimer);
		
		
		JLabel lblTotalMontant = new JLabel("Total Montant TTc :");
		lblTotalMontant.setBounds(870, 671, 105, 26);
		add(lblTotalMontant);
		
		txttotalmontantTTC = new JTextField();
		txttotalmontantTTC.setEditable(false);
		txttotalmontantTTC.setColumns(10);
		txttotalmontantTTC.setBounds(994, 671, 134, 26);
		add(txttotalmontantTTC);
		
		txttotalquantite = new JTextField();
		txttotalquantite.setEditable(false);
		txttotalquantite.setColumns(10);
		txttotalquantite.setBounds(753, 608, 97, 26);
		add(txttotalquantite);
		
		JLabel lblTotalQuantite = new JLabel("Total Quantite  :");
		lblTotalQuantite.setBounds(664, 608, 79, 26);
		add(lblTotalQuantite);
	
		btnModifier.setIcon(imgModifierr);
		btnModifier.setBounds(1137, 395, 73, 24);
		add(btnModifier);
		
		JLabel lblTotalMontantHt = new JLabel("Total Montant HT :");
		lblTotalMontantHt.setBounds(870, 607, 105, 26);
		add(lblTotalMontantHt);
		
		txttotalmontantHT = new JTextField();
		txttotalmontantHT.setEditable(false);
		txttotalmontantHT.setColumns(10);
		txttotalmontantHT.setBounds(994, 609, 134, 26);
		add(txttotalmontantHT);
		
		JLabel lblTotalMontantTva = new JLabel("Total Montant TVA :");
		lblTotalMontantTva.setBounds(870, 641, 105, 26);
		add(lblTotalMontantTva);
		
		txttotalmontantTVA = new JTextField();
		txttotalmontantTVA.setEditable(false);
		txttotalmontantTVA.setColumns(10);
		txttotalmontantTVA.setBounds(994, 641, 134, 26);
		add(txttotalmontantTVA);
		
		
		
		
			
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
		txtcodeMP.setText("");
		comboMP.setSelectedIndex(-1);
	   txtPrix.setText("");
		txtquantite.setText("");
		
	     btnAjouter.setEnabled(true);
	     txtreduction.setText("");
	     
		    
			
		
	     
	     
		
	}
	
	void InitialiseTableau()
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","Matière Première", "Prix Unitaire", "Quantite","Reduction","Montant HT", "Montant TVA", "Montant TTC"
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
	
	
	void afficher_tableDetailFactureAvoir(List<DetailFactureAvoirMP> listDetailFacture)
	{
		modeleChargefixe =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code MP","Matière Première", "Prix Unitaire", "Quantite","Reduction","Montant HT", "Montant TVA", "Montant TTC"
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
		DetailFactureAvoirMP detailfactureAvoir=listDetailFacture.get(i);
			
			Object []ligne={detailfactureAvoir.getMatierePremiere().getCode(),detailfactureAvoir.getMatierePremiere().getNom(),detailfactureAvoir.getPrixUnitaire(),detailfactureAvoir.getQuantite(),detailfactureAvoir.getReduction(), detailfactureAvoir.getMontantHT(),detailfactureAvoir.getMontantTVA(),detailfactureAvoir.getMontantTTC()};

			modeleChargefixe.addRow(ligne);
			i++;
		}
}
	}


